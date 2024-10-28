package io.netty.channel.kqueue;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.AbstractChannel;
import io.netty.channel.Channel;
import io.netty.channel.ChannelConfig;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelMetadata;
import io.netty.channel.ChannelOutboundBuffer;
import io.netty.channel.ChannelPromise;
import io.netty.channel.ConnectTimeoutException;
import io.netty.channel.EventLoop;
import io.netty.channel.RecvByteBufAllocator;
import io.netty.channel.socket.ChannelInputShutdownEvent;
import io.netty.channel.socket.ChannelInputShutdownReadComplete;
import io.netty.channel.socket.SocketChannelConfig;
import io.netty.channel.unix.FileDescriptor;
import io.netty.channel.unix.UnixChannel;
import io.netty.channel.unix.UnixChannelUtil;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.Future;
import io.netty.util.internal.ObjectUtil;
import java.io.IOException;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AlreadyConnectedException;
import java.nio.channels.ConnectionPendingException;
import java.nio.channels.NotYetConnectedException;
import java.nio.channels.UnresolvedAddressException;
import java.util.concurrent.TimeUnit;

abstract class AbstractKQueueChannel extends AbstractChannel implements UnixChannel {
    private static final ChannelMetadata METADATA = new ChannelMetadata(false);
    protected volatile boolean active;
    /* access modifiers changed from: private */
    public ChannelPromise connectPromise;
    /* access modifiers changed from: private */
    public Future<?> connectTimeoutFuture;
    boolean inputClosedSeenErrorOnRead;
    private volatile SocketAddress local;
    private boolean readFilterEnabled;
    boolean readReadyRunnablePending;
    /* access modifiers changed from: private */
    public volatile SocketAddress remote;
    /* access modifiers changed from: private */
    public SocketAddress requestedRemoteAddress;
    final BsdSocket socket;
    /* access modifiers changed from: private */
    public boolean writeFilterEnabled;

    public abstract KQueueChannelConfig config();

    /* access modifiers changed from: protected */
    public abstract AbstractKQueueUnsafe newUnsafe();

    AbstractKQueueChannel(Channel channel, BsdSocket bsdSocket, boolean z) {
        super(channel);
        this.socket = (BsdSocket) ObjectUtil.checkNotNull(bsdSocket, "fd");
        this.active = z;
        if (z) {
            this.local = bsdSocket.localAddress();
            this.remote = bsdSocket.remoteAddress();
        }
    }

    AbstractKQueueChannel(Channel channel, BsdSocket bsdSocket, SocketAddress socketAddress) {
        super(channel);
        this.socket = (BsdSocket) ObjectUtil.checkNotNull(bsdSocket, "fd");
        this.active = true;
        this.remote = socketAddress;
        this.local = bsdSocket.localAddress();
    }

    static boolean isSoErrorZero(BsdSocket bsdSocket) {
        try {
            return bsdSocket.getSoError() == 0;
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public final FileDescriptor fd() {
        return this.socket;
    }

    public boolean isActive() {
        return this.active;
    }

    public ChannelMetadata metadata() {
        return METADATA;
    }

    /* access modifiers changed from: protected */
    public void doClose() throws Exception {
        this.active = false;
        this.inputClosedSeenErrorOnRead = true;
        this.socket.close();
    }

    /* access modifiers changed from: protected */
    public void doDisconnect() throws Exception {
        doClose();
    }

    /* access modifiers changed from: package-private */
    public void resetCachedAddresses() {
        this.local = this.socket.localAddress();
        this.remote = this.socket.remoteAddress();
    }

    /* access modifiers changed from: protected */
    public boolean isCompatible(EventLoop eventLoop) {
        return eventLoop instanceof KQueueEventLoop;
    }

    public boolean isOpen() {
        return this.socket.isOpen();
    }

    /* access modifiers changed from: protected */
    public void doDeregister() throws Exception {
        ((KQueueEventLoop) eventLoop()).remove(this);
        this.readFilterEnabled = false;
        this.writeFilterEnabled = false;
    }

    /* access modifiers changed from: package-private */
    public void unregisterFilters() throws Exception {
        readFilter(false);
        writeFilter(false);
        clearRdHup0();
    }

    /* access modifiers changed from: private */
    public void clearRdHup0() {
        evSet0(Native.EVFILT_SOCK, Native.EV_DELETE_DISABLE, Native.NOTE_RDHUP);
    }

    /* access modifiers changed from: protected */
    public final void doBeginRead() throws Exception {
        AbstractKQueueUnsafe abstractKQueueUnsafe = (AbstractKQueueUnsafe) unsafe();
        abstractKQueueUnsafe.readPending = true;
        readFilter(true);
        if (abstractKQueueUnsafe.maybeMoreDataToRead) {
            abstractKQueueUnsafe.executeReadReadyRunnable(config());
        }
    }

    /* access modifiers changed from: protected */
    public void doRegister() throws Exception {
        this.readReadyRunnablePending = false;
        ((KQueueEventLoop) eventLoop()).add(this);
        if (this.writeFilterEnabled) {
            evSet0(Native.EVFILT_WRITE, Native.EV_ADD_CLEAR_ENABLE);
        }
        if (this.readFilterEnabled) {
            evSet0(Native.EVFILT_READ, Native.EV_ADD_CLEAR_ENABLE);
        }
        evSet0(Native.EVFILT_SOCK, Native.EV_ADD, Native.NOTE_RDHUP);
    }

    /* access modifiers changed from: protected */
    public final ByteBuf newDirectBuffer(ByteBuf byteBuf) {
        return newDirectBuffer(byteBuf, byteBuf);
    }

    /* access modifiers changed from: protected */
    public final ByteBuf newDirectBuffer(Object obj, ByteBuf byteBuf) {
        int readableBytes = byteBuf.readableBytes();
        if (readableBytes == 0) {
            ReferenceCountUtil.release(obj);
            return Unpooled.EMPTY_BUFFER;
        }
        ByteBufAllocator alloc = alloc();
        if (alloc.isDirectBufferPooled()) {
            return newDirectBuffer0(obj, byteBuf, alloc, readableBytes);
        }
        ByteBuf threadLocalDirectBuffer = ByteBufUtil.threadLocalDirectBuffer();
        if (threadLocalDirectBuffer == null) {
            return newDirectBuffer0(obj, byteBuf, alloc, readableBytes);
        }
        threadLocalDirectBuffer.writeBytes(byteBuf, byteBuf.readerIndex(), readableBytes);
        ReferenceCountUtil.safeRelease(obj);
        return threadLocalDirectBuffer;
    }

    private static ByteBuf newDirectBuffer0(Object obj, ByteBuf byteBuf, ByteBufAllocator byteBufAllocator, int i) {
        ByteBuf directBuffer = byteBufAllocator.directBuffer(i);
        directBuffer.writeBytes(byteBuf, byteBuf.readerIndex(), i);
        ReferenceCountUtil.safeRelease(obj);
        return directBuffer;
    }

    protected static void checkResolvable(InetSocketAddress inetSocketAddress) {
        if (inetSocketAddress.isUnresolved()) {
            throw new UnresolvedAddressException();
        }
    }

    /* access modifiers changed from: protected */
    public final int doReadBytes(ByteBuf byteBuf) throws Exception {
        int i;
        int writerIndex = byteBuf.writerIndex();
        unsafe().recvBufAllocHandle().attemptedBytesRead(byteBuf.writableBytes());
        if (byteBuf.hasMemoryAddress()) {
            i = this.socket.readAddress(byteBuf.memoryAddress(), writerIndex, byteBuf.capacity());
        } else {
            ByteBuffer internalNioBuffer = byteBuf.internalNioBuffer(writerIndex, byteBuf.writableBytes());
            i = this.socket.read(internalNioBuffer, internalNioBuffer.position(), internalNioBuffer.limit());
        }
        if (i > 0) {
            byteBuf.writerIndex(writerIndex + i);
        }
        return i;
    }

    /* access modifiers changed from: protected */
    public final int doWriteBytes(ChannelOutboundBuffer channelOutboundBuffer, ByteBuf byteBuf) throws Exception {
        if (byteBuf.hasMemoryAddress()) {
            int writeAddress = this.socket.writeAddress(byteBuf.memoryAddress(), byteBuf.readerIndex(), byteBuf.writerIndex());
            if (writeAddress <= 0) {
                return Integer.MAX_VALUE;
            }
            channelOutboundBuffer.removeBytes((long) writeAddress);
            return 1;
        }
        ByteBuffer internalNioBuffer = byteBuf.nioBufferCount() == 1 ? byteBuf.internalNioBuffer(byteBuf.readerIndex(), byteBuf.readableBytes()) : byteBuf.nioBuffer();
        int write = this.socket.write(internalNioBuffer, internalNioBuffer.position(), internalNioBuffer.limit());
        if (write <= 0) {
            return Integer.MAX_VALUE;
        }
        internalNioBuffer.position(internalNioBuffer.position() + write);
        channelOutboundBuffer.removeBytes((long) write);
        return 1;
    }

    /* access modifiers changed from: package-private */
    public final boolean shouldBreakReadReady(ChannelConfig channelConfig) {
        return this.socket.isInputShutdown() && (this.inputClosedSeenErrorOnRead || !isAllowHalfClosure(channelConfig));
    }

    /* access modifiers changed from: private */
    public static boolean isAllowHalfClosure(ChannelConfig channelConfig) {
        if (channelConfig instanceof KQueueDomainSocketChannelConfig) {
            return ((KQueueDomainSocketChannelConfig) channelConfig).isAllowHalfClosure();
        }
        return (channelConfig instanceof SocketChannelConfig) && ((SocketChannelConfig) channelConfig).isAllowHalfClosure();
    }

    /* access modifiers changed from: package-private */
    public final void clearReadFilter() {
        if (isRegistered()) {
            EventLoop eventLoop = eventLoop();
            final AbstractKQueueUnsafe abstractKQueueUnsafe = (AbstractKQueueUnsafe) unsafe();
            if (eventLoop.inEventLoop()) {
                abstractKQueueUnsafe.clearReadFilter0();
            } else {
                eventLoop.execute(new Runnable() {
                    public void run() {
                        if (!abstractKQueueUnsafe.readPending && !AbstractKQueueChannel.this.config().isAutoRead()) {
                            abstractKQueueUnsafe.clearReadFilter0();
                        }
                    }
                });
            }
        } else {
            this.readFilterEnabled = false;
        }
    }

    /* access modifiers changed from: package-private */
    public void readFilter(boolean z) throws IOException {
        if (this.readFilterEnabled != z) {
            this.readFilterEnabled = z;
            evSet(Native.EVFILT_READ, z ? Native.EV_ADD_CLEAR_ENABLE : Native.EV_DELETE_DISABLE);
        }
    }

    /* access modifiers changed from: package-private */
    public void writeFilter(boolean z) throws IOException {
        if (this.writeFilterEnabled != z) {
            this.writeFilterEnabled = z;
            evSet(Native.EVFILT_WRITE, z ? Native.EV_ADD_CLEAR_ENABLE : Native.EV_DELETE_DISABLE);
        }
    }

    private void evSet(short s, short s2) {
        if (isRegistered()) {
            evSet0(s, s2);
        }
    }

    private void evSet0(short s, short s2) {
        evSet0(s, s2, 0);
    }

    private void evSet0(short s, short s2, int i) {
        if (isOpen()) {
            ((KQueueEventLoop) eventLoop()).evSet(this, s, s2, i);
        }
    }

    public abstract class AbstractKQueueUnsafe extends AbstractChannel.AbstractUnsafe {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private KQueueRecvByteAllocatorHandle allocHandle;
        boolean maybeMoreDataToRead;
        boolean readPending;
        private final Runnable readReadyRunnable = new Runnable() {
            public void run() {
                AbstractKQueueChannel.this.readReadyRunnablePending = false;
                AbstractKQueueUnsafe abstractKQueueUnsafe = AbstractKQueueUnsafe.this;
                abstractKQueueUnsafe.readReady(abstractKQueueUnsafe.recvBufAllocHandle());
            }
        };

        /* access modifiers changed from: package-private */
        public abstract void readReady(KQueueRecvByteAllocatorHandle kQueueRecvByteAllocatorHandle);

        static {
            Class<AbstractKQueueChannel> cls = AbstractKQueueChannel.class;
        }

        public /* bridge */ /* synthetic */ void close(ChannelPromise channelPromise) {
            super.close(channelPromise);
        }

        public AbstractKQueueUnsafe() {
            super();
        }

        /* access modifiers changed from: package-private */
        public final void readReady(long j) {
            KQueueRecvByteAllocatorHandle recvBufAllocHandle = recvBufAllocHandle();
            recvBufAllocHandle.numberBytesPending(j);
            readReady(recvBufAllocHandle);
        }

        /* access modifiers changed from: package-private */
        public final void readReadyBefore() {
            this.maybeMoreDataToRead = false;
        }

        /* access modifiers changed from: package-private */
        public final void readReadyFinally(ChannelConfig channelConfig) {
            boolean z;
            this.maybeMoreDataToRead = this.allocHandle.maybeMoreDataToRead();
            if (this.allocHandle.isReadEOF() || ((z = this.readPending) && this.maybeMoreDataToRead)) {
                executeReadReadyRunnable(channelConfig);
            } else if (!z && !channelConfig.isAutoRead()) {
                clearReadFilter0();
            }
        }

        /* access modifiers changed from: package-private */
        public final boolean failConnectPromise(Throwable th) {
            if (AbstractKQueueChannel.this.connectPromise == null) {
                return false;
            }
            ChannelPromise access$000 = AbstractKQueueChannel.this.connectPromise;
            ChannelPromise unused = AbstractKQueueChannel.this.connectPromise = null;
            if (!(th instanceof ConnectException)) {
                th = new ConnectException("failed to connect").initCause(th);
            }
            if (!access$000.tryFailure(th)) {
                return false;
            }
            closeIfClosed();
            return true;
        }

        /* access modifiers changed from: package-private */
        public final void writeReady() {
            if (AbstractKQueueChannel.this.connectPromise != null) {
                finishConnect();
            } else if (!AbstractKQueueChannel.this.socket.isOutputShutdown()) {
                super.flush0();
            }
        }

        /* access modifiers changed from: package-private */
        public void shutdownInput(boolean z) {
            if (z && AbstractKQueueChannel.this.connectPromise != null) {
                finishConnect();
            }
            if (!AbstractKQueueChannel.this.socket.isInputShutdown()) {
                if (AbstractKQueueChannel.isAllowHalfClosure(AbstractKQueueChannel.this.config())) {
                    try {
                        AbstractKQueueChannel.this.socket.shutdown(true, false);
                    } catch (IOException unused) {
                        fireEventAndClose(ChannelInputShutdownEvent.INSTANCE);
                        return;
                    } catch (NotYetConnectedException unused2) {
                    }
                    clearReadFilter0();
                    AbstractKQueueChannel.this.pipeline().fireUserEventTriggered(ChannelInputShutdownEvent.INSTANCE);
                    return;
                }
                close(voidPromise());
            } else if (!z && !AbstractKQueueChannel.this.inputClosedSeenErrorOnRead) {
                AbstractKQueueChannel.this.inputClosedSeenErrorOnRead = true;
                AbstractKQueueChannel.this.pipeline().fireUserEventTriggered(ChannelInputShutdownReadComplete.INSTANCE);
            }
        }

        /* access modifiers changed from: package-private */
        public final void readEOF() {
            KQueueRecvByteAllocatorHandle recvBufAllocHandle = recvBufAllocHandle();
            recvBufAllocHandle.readEOF();
            if (AbstractKQueueChannel.this.isActive()) {
                readReady(recvBufAllocHandle);
            } else {
                shutdownInput(true);
            }
            AbstractKQueueChannel.this.clearRdHup0();
        }

        public KQueueRecvByteAllocatorHandle recvBufAllocHandle() {
            if (this.allocHandle == null) {
                this.allocHandle = new KQueueRecvByteAllocatorHandle((RecvByteBufAllocator.ExtendedHandle) super.recvBufAllocHandle());
            }
            return this.allocHandle;
        }

        /* access modifiers changed from: protected */
        public final void flush0() {
            if (!AbstractKQueueChannel.this.writeFilterEnabled) {
                super.flush0();
            }
        }

        /* access modifiers changed from: package-private */
        public final void executeReadReadyRunnable(ChannelConfig channelConfig) {
            if (!AbstractKQueueChannel.this.readReadyRunnablePending && AbstractKQueueChannel.this.isActive() && !AbstractKQueueChannel.this.shouldBreakReadReady(channelConfig)) {
                AbstractKQueueChannel.this.readReadyRunnablePending = true;
                AbstractKQueueChannel.this.eventLoop().execute(this.readReadyRunnable);
            }
        }

        /* access modifiers changed from: protected */
        public final void clearReadFilter0() {
            try {
                this.readPending = false;
                AbstractKQueueChannel.this.readFilter(false);
            } catch (IOException e) {
                AbstractKQueueChannel.this.pipeline().fireExceptionCaught(e);
                AbstractKQueueChannel.this.unsafe().close(AbstractKQueueChannel.this.unsafe().voidPromise());
            }
        }

        private void fireEventAndClose(Object obj) {
            AbstractKQueueChannel.this.pipeline().fireUserEventTriggered(obj);
            close(voidPromise());
        }

        public void connect(final SocketAddress socketAddress, SocketAddress socketAddress2, ChannelPromise channelPromise) {
            if (channelPromise.setUncancellable() && ensureOpen(channelPromise)) {
                try {
                    if (AbstractKQueueChannel.this.connectPromise == null) {
                        boolean isActive = AbstractKQueueChannel.this.isActive();
                        if (AbstractKQueueChannel.this.doConnect(socketAddress, socketAddress2)) {
                            fulfillConnectPromise(channelPromise, isActive);
                            return;
                        }
                        ChannelPromise unused = AbstractKQueueChannel.this.connectPromise = channelPromise;
                        SocketAddress unused2 = AbstractKQueueChannel.this.requestedRemoteAddress = socketAddress;
                        int connectTimeoutMillis = AbstractKQueueChannel.this.config().getConnectTimeoutMillis();
                        if (connectTimeoutMillis > 0) {
                            AbstractKQueueChannel abstractKQueueChannel = AbstractKQueueChannel.this;
                            Future unused3 = abstractKQueueChannel.connectTimeoutFuture = abstractKQueueChannel.eventLoop().schedule((Runnable) new Runnable() {
                                public void run() {
                                    ChannelPromise access$000 = AbstractKQueueChannel.this.connectPromise;
                                    if (access$000 != null && !access$000.isDone()) {
                                        if (access$000.tryFailure(new ConnectTimeoutException("connection timed out: " + socketAddress))) {
                                            AbstractKQueueUnsafe abstractKQueueUnsafe = AbstractKQueueUnsafe.this;
                                            abstractKQueueUnsafe.close(abstractKQueueUnsafe.voidPromise());
                                        }
                                    }
                                }
                            }, (long) connectTimeoutMillis, TimeUnit.MILLISECONDS);
                        }
                        channelPromise.addListener(new ChannelFutureListener() {
                            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                                if (channelFuture.isCancelled()) {
                                    if (AbstractKQueueChannel.this.connectTimeoutFuture != null) {
                                        AbstractKQueueChannel.this.connectTimeoutFuture.cancel(false);
                                    }
                                    ChannelPromise unused = AbstractKQueueChannel.this.connectPromise = null;
                                    AbstractKQueueUnsafe abstractKQueueUnsafe = AbstractKQueueUnsafe.this;
                                    abstractKQueueUnsafe.close(abstractKQueueUnsafe.voidPromise());
                                }
                            }
                        });
                        return;
                    }
                    throw new ConnectionPendingException();
                } catch (Throwable th) {
                    closeIfClosed();
                    channelPromise.tryFailure(annotateConnectException(th, socketAddress));
                }
            }
        }

        private void fulfillConnectPromise(ChannelPromise channelPromise, boolean z) {
            if (channelPromise != null) {
                AbstractKQueueChannel.this.active = true;
                boolean isActive = AbstractKQueueChannel.this.isActive();
                boolean trySuccess = channelPromise.trySuccess();
                if (!z && isActive) {
                    AbstractKQueueChannel.this.pipeline().fireChannelActive();
                }
                if (!trySuccess) {
                    close(voidPromise());
                }
            }
        }

        private void fulfillConnectPromise(ChannelPromise channelPromise, Throwable th) {
            if (channelPromise != null) {
                channelPromise.tryFailure(th);
                closeIfClosed();
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:14:0x0049, code lost:
            if (io.netty.channel.kqueue.AbstractKQueueChannel.access$500(r5.this$0) == null) goto L_0x0029;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:15:0x004c, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:7:0x001e, code lost:
            if (io.netty.channel.kqueue.AbstractKQueueChannel.access$500(r5.this$0) != null) goto L_0x0020;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:8:0x0020, code lost:
            io.netty.channel.kqueue.AbstractKQueueChannel.access$500(r5.this$0).cancel(false);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:9:0x0029, code lost:
            io.netty.channel.kqueue.AbstractKQueueChannel.access$002(r5.this$0, (io.netty.channel.ChannelPromise) null);
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void finishConnect() {
            /*
                r5 = this;
                r0 = 0
                r1 = 0
                io.netty.channel.kqueue.AbstractKQueueChannel r2 = io.netty.channel.kqueue.AbstractKQueueChannel.this     // Catch:{ all -> 0x002f }
                boolean r2 = r2.isActive()     // Catch:{ all -> 0x002f }
                boolean r3 = r5.doFinishConnect()     // Catch:{ all -> 0x002f }
                if (r3 != 0) goto L_0x000f
                return
            L_0x000f:
                io.netty.channel.kqueue.AbstractKQueueChannel r3 = io.netty.channel.kqueue.AbstractKQueueChannel.this     // Catch:{ all -> 0x002f }
                io.netty.channel.ChannelPromise r3 = r3.connectPromise     // Catch:{ all -> 0x002f }
                r5.fulfillConnectPromise((io.netty.channel.ChannelPromise) r3, (boolean) r2)     // Catch:{ all -> 0x002f }
                io.netty.channel.kqueue.AbstractKQueueChannel r2 = io.netty.channel.kqueue.AbstractKQueueChannel.this
                io.netty.util.concurrent.Future r2 = r2.connectTimeoutFuture
                if (r2 == 0) goto L_0x0029
            L_0x0020:
                io.netty.channel.kqueue.AbstractKQueueChannel r2 = io.netty.channel.kqueue.AbstractKQueueChannel.this
                io.netty.util.concurrent.Future r2 = r2.connectTimeoutFuture
                r2.cancel(r0)
            L_0x0029:
                io.netty.channel.kqueue.AbstractKQueueChannel r0 = io.netty.channel.kqueue.AbstractKQueueChannel.this
                io.netty.channel.ChannelPromise unused = r0.connectPromise = r1
                goto L_0x004c
            L_0x002f:
                r2 = move-exception
                io.netty.channel.kqueue.AbstractKQueueChannel r3 = io.netty.channel.kqueue.AbstractKQueueChannel.this     // Catch:{ all -> 0x004d }
                io.netty.channel.ChannelPromise r3 = r3.connectPromise     // Catch:{ all -> 0x004d }
                io.netty.channel.kqueue.AbstractKQueueChannel r4 = io.netty.channel.kqueue.AbstractKQueueChannel.this     // Catch:{ all -> 0x004d }
                java.net.SocketAddress r4 = r4.requestedRemoteAddress     // Catch:{ all -> 0x004d }
                java.lang.Throwable r2 = r5.annotateConnectException(r2, r4)     // Catch:{ all -> 0x004d }
                r5.fulfillConnectPromise((io.netty.channel.ChannelPromise) r3, (java.lang.Throwable) r2)     // Catch:{ all -> 0x004d }
                io.netty.channel.kqueue.AbstractKQueueChannel r2 = io.netty.channel.kqueue.AbstractKQueueChannel.this
                io.netty.util.concurrent.Future r2 = r2.connectTimeoutFuture
                if (r2 == 0) goto L_0x0029
                goto L_0x0020
            L_0x004c:
                return
            L_0x004d:
                r2 = move-exception
                io.netty.channel.kqueue.AbstractKQueueChannel r3 = io.netty.channel.kqueue.AbstractKQueueChannel.this
                io.netty.util.concurrent.Future r3 = r3.connectTimeoutFuture
                if (r3 == 0) goto L_0x005f
                io.netty.channel.kqueue.AbstractKQueueChannel r3 = io.netty.channel.kqueue.AbstractKQueueChannel.this
                io.netty.util.concurrent.Future r3 = r3.connectTimeoutFuture
                r3.cancel(r0)
            L_0x005f:
                io.netty.channel.kqueue.AbstractKQueueChannel r0 = io.netty.channel.kqueue.AbstractKQueueChannel.this
                io.netty.channel.ChannelPromise unused = r0.connectPromise = r1
                goto L_0x0066
            L_0x0065:
                throw r2
            L_0x0066:
                goto L_0x0065
            */
            throw new UnsupportedOperationException("Method not decompiled: io.netty.channel.kqueue.AbstractKQueueChannel.AbstractKQueueUnsafe.finishConnect():void");
        }

        private boolean doFinishConnect() throws Exception {
            if (AbstractKQueueChannel.this.socket.finishConnect()) {
                AbstractKQueueChannel.this.writeFilter(false);
                if (AbstractKQueueChannel.this.requestedRemoteAddress instanceof InetSocketAddress) {
                    AbstractKQueueChannel abstractKQueueChannel = AbstractKQueueChannel.this;
                    SocketAddress unused = abstractKQueueChannel.remote = UnixChannelUtil.computeRemoteAddr((InetSocketAddress) abstractKQueueChannel.requestedRemoteAddress, AbstractKQueueChannel.this.socket.remoteAddress());
                }
                SocketAddress unused2 = AbstractKQueueChannel.this.requestedRemoteAddress = null;
                return true;
            }
            AbstractKQueueChannel.this.writeFilter(true);
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public void doBind(SocketAddress socketAddress) throws Exception {
        if (socketAddress instanceof InetSocketAddress) {
            checkResolvable((InetSocketAddress) socketAddress);
        }
        this.socket.bind(socketAddress);
        this.local = this.socket.localAddress();
    }

    /* access modifiers changed from: protected */
    public boolean doConnect(SocketAddress socketAddress, SocketAddress socketAddress2) throws Exception {
        if (socketAddress2 instanceof InetSocketAddress) {
            checkResolvable((InetSocketAddress) socketAddress2);
        }
        InetSocketAddress inetSocketAddress = socketAddress instanceof InetSocketAddress ? (InetSocketAddress) socketAddress : null;
        if (inetSocketAddress != null) {
            checkResolvable(inetSocketAddress);
        }
        if (this.remote == null) {
            if (socketAddress2 != null) {
                this.socket.bind(socketAddress2);
            }
            boolean doConnect0 = doConnect0(socketAddress, socketAddress2);
            if (doConnect0) {
                if (inetSocketAddress != null) {
                    socketAddress = UnixChannelUtil.computeRemoteAddr(inetSocketAddress, this.socket.remoteAddress());
                }
                this.remote = socketAddress;
            }
            this.local = this.socket.localAddress();
            return doConnect0;
        }
        throw new AlreadyConnectedException();
    }

    /* access modifiers changed from: protected */
    public boolean doConnect0(SocketAddress socketAddress, SocketAddress socketAddress2) throws Exception {
        try {
            boolean connect = this.socket.connect(socketAddress);
            if (!connect) {
                writeFilter(true);
            }
            return connect;
        } catch (Throwable th) {
            doClose();
            throw th;
        }
    }

    /* access modifiers changed from: protected */
    public SocketAddress localAddress0() {
        return this.local;
    }

    /* access modifiers changed from: protected */
    public SocketAddress remoteAddress0() {
        return this.remote;
    }
}
