package io.netty.handler.ssl;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.AbstractCoalescingBufferQueue;
import io.netty.channel.Channel;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelOutboundBuffer;
import io.netty.channel.ChannelOutboundHandler;
import io.netty.channel.ChannelPromise;
import io.netty.channel.unix.UnixChannel;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.DecoderException;
import io.netty.handler.codec.UnsupportedMessageTypeException;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.DefaultPromise;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.FutureListener;
import io.netty.util.concurrent.GenericFutureListener;
import io.netty.util.concurrent.ImmediateExecutor;
import io.netty.util.concurrent.Promise;
import io.netty.util.concurrent.PromiseNotifier;
import io.netty.util.concurrent.ScheduledFuture;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.ThrowableUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SocketChannel;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLEngineResult;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLSession;

public class SslHandler extends ByteToMessageDecoder implements ChannelOutboundHandler {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final Pattern IGNORABLE_CLASS_IN_STACK = Pattern.compile("^.*(?:Socket|Datagram|Sctp|Udt)Channel.*$");
    private static final Pattern IGNORABLE_ERROR_MESSAGE = Pattern.compile("^.*(?:connection.*(?:reset|closed|abort|broken)|broken.*pipe).*$", 2);
    private static final int MAX_PLAINTEXT_LENGTH = 16384;
    private static final int STATE_CLOSE_NOTIFY = 64;
    private static final int STATE_FIRE_CHANNEL_READ = 256;
    private static final int STATE_FLUSHED_BEFORE_HANDSHAKE = 2;
    private static final int STATE_HANDSHAKE_STARTED = 8;
    private static final int STATE_NEEDS_FLUSH = 16;
    private static final int STATE_OUTBOUND_CLOSED = 32;
    private static final int STATE_PROCESS_TASK = 128;
    private static final int STATE_READ_DURING_HANDSHAKE = 4;
    private static final int STATE_SENT_FIRST_MESSAGE = 1;
    private static final int STATE_UNWRAP_REENTRY = 512;
    /* access modifiers changed from: private */
    public static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) SslHandler.class);
    private volatile long closeNotifyFlushTimeoutMillis;
    /* access modifiers changed from: private */
    public volatile long closeNotifyReadTimeoutMillis;
    /* access modifiers changed from: private */
    public volatile ChannelHandlerContext ctx;
    private final Executor delegatedTaskExecutor;
    /* access modifiers changed from: private */
    public final SSLEngine engine;
    /* access modifiers changed from: private */
    public final SslEngineType engineType;
    private Promise<Channel> handshakePromise;
    private volatile long handshakeTimeoutMillis;
    private final boolean jdkCompatibilityMode;
    private int packetLength;
    private SslHandlerCoalescingBufferQueue pendingUnencryptedWrites;
    /* access modifiers changed from: private */
    public final ByteBuffer[] singleBuffer;
    /* access modifiers changed from: private */
    public final LazyChannelPromise sslClosePromise;
    private final SslTasksRunner sslTaskRunner;
    private final SslTasksRunner sslTaskRunnerForUnwrap;
    private final boolean startTls;
    private short state;
    volatile int wrapDataSize;

    private enum SslEngineType {
        TCNATIVE(true, ByteToMessageDecoder.COMPOSITE_CUMULATOR) {
            /* access modifiers changed from: package-private */
            public SSLEngineResult unwrap(SslHandler sslHandler, ByteBuf byteBuf, int i, ByteBuf byteBuf2) throws SSLException {
                SSLEngineResult sSLEngineResult;
                int nioBufferCount = byteBuf.nioBufferCount();
                int writerIndex = byteBuf2.writerIndex();
                if (nioBufferCount > 1) {
                    ReferenceCountedOpenSslEngine referenceCountedOpenSslEngine = (ReferenceCountedOpenSslEngine) sslHandler.engine;
                    try {
                        sslHandler.singleBuffer[0] = SslHandler.toByteBuffer(byteBuf2, writerIndex, byteBuf2.writableBytes());
                        sSLEngineResult = referenceCountedOpenSslEngine.unwrap(byteBuf.nioBuffers(byteBuf.readerIndex(), i), sslHandler.singleBuffer);
                    } finally {
                        sslHandler.singleBuffer[0] = null;
                    }
                } else {
                    sSLEngineResult = sslHandler.engine.unwrap(SslHandler.toByteBuffer(byteBuf, byteBuf.readerIndex(), i), SslHandler.toByteBuffer(byteBuf2, writerIndex, byteBuf2.writableBytes()));
                }
                byteBuf2.writerIndex(writerIndex + sSLEngineResult.bytesProduced());
                return sSLEngineResult;
            }

            /* access modifiers changed from: package-private */
            public ByteBuf allocateWrapBuffer(SslHandler sslHandler, ByteBufAllocator byteBufAllocator, int i, int i2) {
                return byteBufAllocator.directBuffer(((ReferenceCountedOpenSslEngine) sslHandler.engine).calculateOutNetBufSize(i, i2));
            }

            /* access modifiers changed from: package-private */
            public int calculateRequiredOutBufSpace(SslHandler sslHandler, int i, int i2) {
                return ((ReferenceCountedOpenSslEngine) sslHandler.engine).calculateMaxLengthForWrap(i, i2);
            }

            /* access modifiers changed from: package-private */
            public int calculatePendingData(SslHandler sslHandler, int i) {
                int sslPending = ((ReferenceCountedOpenSslEngine) sslHandler.engine).sslPending();
                return sslPending > 0 ? sslPending : i;
            }

            /* access modifiers changed from: package-private */
            public boolean jdkCompatibilityMode(SSLEngine sSLEngine) {
                return ((ReferenceCountedOpenSslEngine) sSLEngine).jdkCompatibilityMode;
            }
        },
        CONSCRYPT(true, ByteToMessageDecoder.COMPOSITE_CUMULATOR) {
            /* access modifiers changed from: package-private */
            public int calculatePendingData(SslHandler sslHandler, int i) {
                return i;
            }

            /* access modifiers changed from: package-private */
            public boolean jdkCompatibilityMode(SSLEngine sSLEngine) {
                return true;
            }

            /* access modifiers changed from: package-private */
            public SSLEngineResult unwrap(SslHandler sslHandler, ByteBuf byteBuf, int i, ByteBuf byteBuf2) throws SSLException {
                SSLEngineResult sSLEngineResult;
                int nioBufferCount = byteBuf.nioBufferCount();
                int writerIndex = byteBuf2.writerIndex();
                if (nioBufferCount > 1) {
                    try {
                        sslHandler.singleBuffer[0] = SslHandler.toByteBuffer(byteBuf2, writerIndex, byteBuf2.writableBytes());
                        sSLEngineResult = ((ConscryptAlpnSslEngine) sslHandler.engine).unwrap(byteBuf.nioBuffers(byteBuf.readerIndex(), i), sslHandler.singleBuffer);
                    } finally {
                        sslHandler.singleBuffer[0] = null;
                    }
                } else {
                    sSLEngineResult = sslHandler.engine.unwrap(SslHandler.toByteBuffer(byteBuf, byteBuf.readerIndex(), i), SslHandler.toByteBuffer(byteBuf2, writerIndex, byteBuf2.writableBytes()));
                }
                byteBuf2.writerIndex(writerIndex + sSLEngineResult.bytesProduced());
                return sSLEngineResult;
            }

            /* access modifiers changed from: package-private */
            public ByteBuf allocateWrapBuffer(SslHandler sslHandler, ByteBufAllocator byteBufAllocator, int i, int i2) {
                return byteBufAllocator.directBuffer(((ConscryptAlpnSslEngine) sslHandler.engine).calculateOutNetBufSize(i, i2));
            }

            /* access modifiers changed from: package-private */
            public int calculateRequiredOutBufSpace(SslHandler sslHandler, int i, int i2) {
                return ((ConscryptAlpnSslEngine) sslHandler.engine).calculateRequiredOutBufSpace(i, i2);
            }
        },
        JDK(false, ByteToMessageDecoder.MERGE_CUMULATOR) {
            /* access modifiers changed from: package-private */
            public int calculatePendingData(SslHandler sslHandler, int i) {
                return i;
            }

            /* access modifiers changed from: package-private */
            public boolean jdkCompatibilityMode(SSLEngine sSLEngine) {
                return true;
            }

            /* access modifiers changed from: package-private */
            /* JADX WARNING: Code restructure failed: missing block: B:2:0x002e, code lost:
                r4 = r4.position() - r5;
             */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public javax.net.ssl.SSLEngineResult unwrap(io.netty.handler.ssl.SslHandler r3, io.netty.buffer.ByteBuf r4, int r5, io.netty.buffer.ByteBuf r6) throws javax.net.ssl.SSLException {
                /*
                    r2 = this;
                    int r0 = r6.writerIndex()
                    int r1 = r4.readerIndex()
                    java.nio.ByteBuffer r4 = io.netty.handler.ssl.SslHandler.toByteBuffer(r4, r1, r5)
                    int r5 = r4.position()
                    javax.net.ssl.SSLEngine r3 = r3.engine
                    int r1 = r6.writableBytes()
                    java.nio.ByteBuffer r1 = io.netty.handler.ssl.SslHandler.toByteBuffer(r6, r0, r1)
                    javax.net.ssl.SSLEngineResult r3 = r3.unwrap(r4, r1)
                    int r1 = r3.bytesProduced()
                    int r0 = r0 + r1
                    r6.writerIndex(r0)
                    int r6 = r3.bytesConsumed()
                    if (r6 != 0) goto L_0x004b
                    int r4 = r4.position()
                    int r4 = r4 - r5
                    int r5 = r3.bytesConsumed()
                    if (r4 == r5) goto L_0x004b
                    javax.net.ssl.SSLEngineResult r5 = new javax.net.ssl.SSLEngineResult
                    javax.net.ssl.SSLEngineResult$Status r6 = r3.getStatus()
                    javax.net.ssl.SSLEngineResult$HandshakeStatus r0 = r3.getHandshakeStatus()
                    int r3 = r3.bytesProduced()
                    r5.<init>(r6, r0, r4, r3)
                    return r5
                L_0x004b:
                    return r3
                */
                throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.ssl.SslHandler.SslEngineType.AnonymousClass3.unwrap(io.netty.handler.ssl.SslHandler, io.netty.buffer.ByteBuf, int, io.netty.buffer.ByteBuf):javax.net.ssl.SSLEngineResult");
            }

            /* access modifiers changed from: package-private */
            public ByteBuf allocateWrapBuffer(SslHandler sslHandler, ByteBufAllocator byteBufAllocator, int i, int i2) {
                return byteBufAllocator.heapBuffer(Math.max(i, sslHandler.engine.getSession().getPacketBufferSize()));
            }

            /* access modifiers changed from: package-private */
            public int calculateRequiredOutBufSpace(SslHandler sslHandler, int i, int i2) {
                return sslHandler.engine.getSession().getPacketBufferSize();
            }
        };
        
        final ByteToMessageDecoder.Cumulator cumulator;
        final boolean wantsDirectBuffer;

        /* access modifiers changed from: package-private */
        public abstract ByteBuf allocateWrapBuffer(SslHandler sslHandler, ByteBufAllocator byteBufAllocator, int i, int i2);

        /* access modifiers changed from: package-private */
        public abstract int calculatePendingData(SslHandler sslHandler, int i);

        /* access modifiers changed from: package-private */
        public abstract int calculateRequiredOutBufSpace(SslHandler sslHandler, int i, int i2);

        /* access modifiers changed from: package-private */
        public abstract boolean jdkCompatibilityMode(SSLEngine sSLEngine);

        /* access modifiers changed from: package-private */
        public abstract SSLEngineResult unwrap(SslHandler sslHandler, ByteBuf byteBuf, int i, ByteBuf byteBuf2) throws SSLException;

        static SslEngineType forEngine(SSLEngine sSLEngine) {
            if (sSLEngine instanceof ReferenceCountedOpenSslEngine) {
                return TCNATIVE;
            }
            return sSLEngine instanceof ConscryptAlpnSslEngine ? CONSCRYPT : JDK;
        }

        private SslEngineType(boolean z, ByteToMessageDecoder.Cumulator cumulator2) {
            this.wantsDirectBuffer = z;
            this.cumulator = cumulator2;
        }
    }

    public SslHandler(SSLEngine sSLEngine) {
        this(sSLEngine, false);
    }

    public SslHandler(SSLEngine sSLEngine, boolean z) {
        this(sSLEngine, z, ImmediateExecutor.INSTANCE);
    }

    public SslHandler(SSLEngine sSLEngine, Executor executor) {
        this(sSLEngine, false, executor);
    }

    public SslHandler(SSLEngine sSLEngine, boolean z, Executor executor) {
        this.singleBuffer = new ByteBuffer[1];
        this.sslTaskRunnerForUnwrap = new SslTasksRunner(true);
        this.sslTaskRunner = new SslTasksRunner(false);
        this.handshakePromise = new LazyChannelPromise();
        this.sslClosePromise = new LazyChannelPromise();
        this.handshakeTimeoutMillis = 10000;
        this.closeNotifyFlushTimeoutMillis = 3000;
        this.wrapDataSize = 16384;
        this.engine = (SSLEngine) ObjectUtil.checkNotNull(sSLEngine, "engine");
        this.delegatedTaskExecutor = (Executor) ObjectUtil.checkNotNull(executor, "delegatedTaskExecutor");
        SslEngineType forEngine = SslEngineType.forEngine(sSLEngine);
        this.engineType = forEngine;
        this.startTls = z;
        this.jdkCompatibilityMode = forEngine.jdkCompatibilityMode(sSLEngine);
        setCumulator(forEngine.cumulator);
    }

    public long getHandshakeTimeoutMillis() {
        return this.handshakeTimeoutMillis;
    }

    public void setHandshakeTimeout(long j, TimeUnit timeUnit) {
        ObjectUtil.checkNotNull(timeUnit, "unit");
        setHandshakeTimeoutMillis(timeUnit.toMillis(j));
    }

    public void setHandshakeTimeoutMillis(long j) {
        this.handshakeTimeoutMillis = ObjectUtil.checkPositiveOrZero(j, "handshakeTimeoutMillis");
    }

    public final void setWrapDataSize(int i) {
        this.wrapDataSize = i;
    }

    @Deprecated
    public long getCloseNotifyTimeoutMillis() {
        return getCloseNotifyFlushTimeoutMillis();
    }

    @Deprecated
    public void setCloseNotifyTimeout(long j, TimeUnit timeUnit) {
        setCloseNotifyFlushTimeout(j, timeUnit);
    }

    @Deprecated
    public void setCloseNotifyTimeoutMillis(long j) {
        setCloseNotifyFlushTimeoutMillis(j);
    }

    public final long getCloseNotifyFlushTimeoutMillis() {
        return this.closeNotifyFlushTimeoutMillis;
    }

    public final void setCloseNotifyFlushTimeout(long j, TimeUnit timeUnit) {
        setCloseNotifyFlushTimeoutMillis(timeUnit.toMillis(j));
    }

    public final void setCloseNotifyFlushTimeoutMillis(long j) {
        this.closeNotifyFlushTimeoutMillis = ObjectUtil.checkPositiveOrZero(j, "closeNotifyFlushTimeoutMillis");
    }

    public final long getCloseNotifyReadTimeoutMillis() {
        return this.closeNotifyReadTimeoutMillis;
    }

    public final void setCloseNotifyReadTimeout(long j, TimeUnit timeUnit) {
        setCloseNotifyReadTimeoutMillis(timeUnit.toMillis(j));
    }

    public final void setCloseNotifyReadTimeoutMillis(long j) {
        this.closeNotifyReadTimeoutMillis = ObjectUtil.checkPositiveOrZero(j, "closeNotifyReadTimeoutMillis");
    }

    public SSLEngine engine() {
        return this.engine;
    }

    public String applicationProtocol() {
        SSLEngine engine2 = engine();
        if (!(engine2 instanceof ApplicationProtocolAccessor)) {
            return null;
        }
        return ((ApplicationProtocolAccessor) engine2).getNegotiatedApplicationProtocol();
    }

    public Future<Channel> handshakeFuture() {
        return this.handshakePromise;
    }

    @Deprecated
    public ChannelFuture close() {
        return closeOutbound();
    }

    @Deprecated
    public ChannelFuture close(ChannelPromise channelPromise) {
        return closeOutbound(channelPromise);
    }

    public ChannelFuture closeOutbound() {
        return closeOutbound(this.ctx.newPromise());
    }

    public ChannelFuture closeOutbound(final ChannelPromise channelPromise) {
        ChannelHandlerContext channelHandlerContext = this.ctx;
        if (channelHandlerContext.executor().inEventLoop()) {
            closeOutbound0(channelPromise);
        } else {
            channelHandlerContext.executor().execute(new Runnable() {
                public void run() {
                    SslHandler.this.closeOutbound0(channelPromise);
                }
            });
        }
        return channelPromise;
    }

    /* access modifiers changed from: private */
    public void closeOutbound0(ChannelPromise channelPromise) {
        setState(32);
        this.engine.closeOutbound();
        try {
            flush(this.ctx, channelPromise);
        } catch (Exception e) {
            if (!channelPromise.tryFailure(e)) {
                logger.warn("{} flush() raised a masked exception.", this.ctx.channel(), e);
            }
        }
    }

    public Future<Channel> sslCloseFuture() {
        return this.sslClosePromise;
    }

    public void handlerRemoved0(ChannelHandlerContext channelHandlerContext) throws Exception {
        try {
            SslHandlerCoalescingBufferQueue sslHandlerCoalescingBufferQueue = this.pendingUnencryptedWrites;
            if (sslHandlerCoalescingBufferQueue != null && !sslHandlerCoalescingBufferQueue.isEmpty()) {
                this.pendingUnencryptedWrites.releaseAndFailAll(channelHandlerContext, new ChannelException("Pending write on removal of SslHandler"));
            }
            SSLException sSLException = null;
            this.pendingUnencryptedWrites = null;
            if (!this.handshakePromise.isDone()) {
                sSLException = new SSLHandshakeException("SslHandler removed before handshake completed");
                if (this.handshakePromise.tryFailure(sSLException)) {
                    channelHandlerContext.fireUserEventTriggered(new SslHandshakeCompletionEvent(sSLException));
                }
            }
            if (!this.sslClosePromise.isDone()) {
                if (sSLException == null) {
                    sSLException = new SSLException("SslHandler removed before SSLEngine was closed");
                }
                notifyClosePromise(sSLException);
            }
        } finally {
            ReferenceCountUtil.release(this.engine);
        }
    }

    public void bind(ChannelHandlerContext channelHandlerContext, SocketAddress socketAddress, ChannelPromise channelPromise) throws Exception {
        channelHandlerContext.bind(socketAddress, channelPromise);
    }

    public void connect(ChannelHandlerContext channelHandlerContext, SocketAddress socketAddress, SocketAddress socketAddress2, ChannelPromise channelPromise) throws Exception {
        channelHandlerContext.connect(socketAddress, socketAddress2, channelPromise);
    }

    public void deregister(ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise) throws Exception {
        channelHandlerContext.deregister(channelPromise);
    }

    public void disconnect(ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise) throws Exception {
        closeOutboundAndChannel(channelHandlerContext, channelPromise, true);
    }

    public void close(ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise) throws Exception {
        closeOutboundAndChannel(channelHandlerContext, channelPromise, false);
    }

    public void read(ChannelHandlerContext channelHandlerContext) throws Exception {
        if (!this.handshakePromise.isDone()) {
            setState(4);
        }
        channelHandlerContext.read();
    }

    private static IllegalStateException newPendingWritesNullException() {
        return new IllegalStateException("pendingUnencryptedWrites is null, handlerRemoved0 called?");
    }

    public void write(ChannelHandlerContext channelHandlerContext, Object obj, ChannelPromise channelPromise) throws Exception {
        if (!(obj instanceof ByteBuf)) {
            UnsupportedMessageTypeException unsupportedMessageTypeException = new UnsupportedMessageTypeException(obj, (Class<?>[]) new Class[]{ByteBuf.class});
            ReferenceCountUtil.safeRelease(obj);
            channelPromise.setFailure(unsupportedMessageTypeException);
            return;
        }
        SslHandlerCoalescingBufferQueue sslHandlerCoalescingBufferQueue = this.pendingUnencryptedWrites;
        if (sslHandlerCoalescingBufferQueue == null) {
            ReferenceCountUtil.safeRelease(obj);
            channelPromise.setFailure(newPendingWritesNullException());
            return;
        }
        sslHandlerCoalescingBufferQueue.add((ByteBuf) obj, channelPromise);
    }

    public void flush(ChannelHandlerContext channelHandlerContext) throws Exception {
        if (this.startTls && !isStateSet(1)) {
            setState(1);
            this.pendingUnencryptedWrites.writeAndRemoveAll(channelHandlerContext);
            forceFlush(channelHandlerContext);
            startHandshakeProcessing(true);
        } else if (!isStateSet(128)) {
            try {
                wrapAndFlush(channelHandlerContext);
            } catch (Throwable th) {
                setHandshakeFailure(channelHandlerContext, th);
                PlatformDependent.throwException(th);
            }
        }
    }

    private void wrapAndFlush(ChannelHandlerContext channelHandlerContext) throws SSLException {
        if (this.pendingUnencryptedWrites.isEmpty()) {
            this.pendingUnencryptedWrites.add(Unpooled.EMPTY_BUFFER, channelHandlerContext.newPromise());
        }
        if (!this.handshakePromise.isDone()) {
            setState(2);
        }
        try {
            wrap(channelHandlerContext, false);
        } finally {
            forceFlush(channelHandlerContext);
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:104:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x00d9, code lost:
        if (r5 != 5) goto L_0x00e9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x00db, code lost:
        readIfNeeded(r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x00de, code lost:
        if (r4 == null) goto L_0x00e3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x00e0, code lost:
        r4.release();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x00e3, code lost:
        if (r12 == false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x00e5, code lost:
        setState(16);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x0103, code lost:
        throw new java.lang.IllegalStateException("Unknown handshake status: " + r7.getHandshakeStatus());
     */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x0137  */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x013c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void wrap(io.netty.channel.ChannelHandlerContext r11, boolean r12) throws javax.net.ssl.SSLException {
        /*
            r10 = this;
            io.netty.buffer.ByteBufAllocator r0 = r11.alloc()
            r1 = 16
            r2 = 0
            int r3 = r10.wrapDataSize     // Catch:{ all -> 0x0134 }
            r4 = r2
        L_0x000a:
            boolean r5 = r11.isRemoved()     // Catch:{ all -> 0x0131 }
            if (r5 != 0) goto L_0x0126
            io.netty.channel.ChannelPromise r5 = r11.newPromise()     // Catch:{ all -> 0x0131 }
            if (r3 <= 0) goto L_0x001d
            io.netty.handler.ssl.SslHandler$SslHandlerCoalescingBufferQueue r6 = r10.pendingUnencryptedWrites     // Catch:{ all -> 0x0131 }
            io.netty.buffer.ByteBuf r6 = r6.remove(r0, r3, r5)     // Catch:{ all -> 0x0131 }
            goto L_0x0023
        L_0x001d:
            io.netty.handler.ssl.SslHandler$SslHandlerCoalescingBufferQueue r6 = r10.pendingUnencryptedWrites     // Catch:{ all -> 0x0131 }
            io.netty.buffer.ByteBuf r6 = r6.removeFirst(r5)     // Catch:{ all -> 0x0131 }
        L_0x0023:
            if (r6 != 0) goto L_0x0027
            goto L_0x0126
        L_0x0027:
            int r7 = r6.readableBytes()     // Catch:{ all -> 0x0131 }
            r8 = 16384(0x4000, float:2.2959E-41)
            if (r7 <= r8) goto L_0x004d
            int r7 = r6.readableBytes()     // Catch:{ all -> 0x0131 }
            int r8 = r7 / 16384
            int r9 = r7 % 16384
            if (r9 == 0) goto L_0x003b
            int r8 = r8 + 1
        L_0x003b:
            if (r4 != 0) goto L_0x0046
            int r9 = r6.nioBufferCount()     // Catch:{ all -> 0x0131 }
            int r9 = r9 + r8
            io.netty.buffer.ByteBuf r4 = r10.allocateOutNetBuf(r11, r7, r9)     // Catch:{ all -> 0x0131 }
        L_0x0046:
            javax.net.ssl.SSLEngine r7 = r10.engine     // Catch:{ all -> 0x0131 }
            javax.net.ssl.SSLEngineResult r7 = r10.wrapMultiple(r0, r7, r6, r4)     // Catch:{ all -> 0x0131 }
            goto L_0x0061
        L_0x004d:
            if (r4 != 0) goto L_0x005b
            int r7 = r6.readableBytes()     // Catch:{ all -> 0x0131 }
            int r8 = r6.nioBufferCount()     // Catch:{ all -> 0x0131 }
            io.netty.buffer.ByteBuf r4 = r10.allocateOutNetBuf(r11, r7, r8)     // Catch:{ all -> 0x0131 }
        L_0x005b:
            javax.net.ssl.SSLEngine r7 = r10.engine     // Catch:{ all -> 0x0131 }
            javax.net.ssl.SSLEngineResult r7 = r10.wrap(r0, r7, r6, r4)     // Catch:{ all -> 0x0131 }
        L_0x0061:
            boolean r8 = r6.isReadable()     // Catch:{ all -> 0x0131 }
            if (r8 == 0) goto L_0x006e
            io.netty.handler.ssl.SslHandler$SslHandlerCoalescingBufferQueue r8 = r10.pendingUnencryptedWrites     // Catch:{ all -> 0x0131 }
            r8.addFirst((io.netty.buffer.ByteBuf) r6, (io.netty.channel.ChannelPromise) r5)     // Catch:{ all -> 0x0131 }
            r5 = r2
            goto L_0x0071
        L_0x006e:
            r6.release()     // Catch:{ all -> 0x0131 }
        L_0x0071:
            boolean r6 = r4.isReadable()     // Catch:{ all -> 0x0131 }
            if (r6 == 0) goto L_0x0082
            if (r5 == 0) goto L_0x007d
            r11.write(r4, r5)     // Catch:{ all -> 0x0134 }
            goto L_0x0080
        L_0x007d:
            r11.write(r4)     // Catch:{ all -> 0x0134 }
        L_0x0080:
            r4 = r2
            goto L_0x0089
        L_0x0082:
            if (r5 == 0) goto L_0x0089
            io.netty.buffer.ByteBuf r6 = io.netty.buffer.Unpooled.EMPTY_BUFFER     // Catch:{ all -> 0x0131 }
            r11.write(r6, r5)     // Catch:{ all -> 0x0131 }
        L_0x0089:
            javax.net.ssl.SSLEngineResult$Status r5 = r7.getStatus()     // Catch:{ all -> 0x0131 }
            javax.net.ssl.SSLEngineResult$Status r6 = javax.net.ssl.SSLEngineResult.Status.CLOSED     // Catch:{ all -> 0x0131 }
            if (r5 != r6) goto L_0x00c0
            io.netty.handler.ssl.SslHandler$SslHandlerCoalescingBufferQueue r0 = r10.pendingUnencryptedWrites     // Catch:{ all -> 0x0131 }
            boolean r0 = r0.isEmpty()     // Catch:{ all -> 0x0131 }
            if (r0 != 0) goto L_0x00b5
            io.netty.util.concurrent.Promise<io.netty.channel.Channel> r0 = r10.handshakePromise     // Catch:{ all -> 0x0131 }
            java.lang.Throwable r0 = r0.cause()     // Catch:{ all -> 0x0131 }
            if (r0 != 0) goto L_0x00b0
            io.netty.handler.ssl.SslHandler$LazyChannelPromise r0 = r10.sslClosePromise     // Catch:{ all -> 0x0131 }
            java.lang.Throwable r0 = r0.cause()     // Catch:{ all -> 0x0131 }
            if (r0 != 0) goto L_0x00b0
            io.netty.handler.ssl.SslClosedEngineException r0 = new io.netty.handler.ssl.SslClosedEngineException     // Catch:{ all -> 0x0131 }
            java.lang.String r2 = "SSLEngine closed already"
            r0.<init>(r2)     // Catch:{ all -> 0x0131 }
        L_0x00b0:
            io.netty.handler.ssl.SslHandler$SslHandlerCoalescingBufferQueue r2 = r10.pendingUnencryptedWrites     // Catch:{ all -> 0x0131 }
            r2.releaseAndFailAll(r11, r0)     // Catch:{ all -> 0x0131 }
        L_0x00b5:
            if (r4 == 0) goto L_0x00ba
            r4.release()
        L_0x00ba:
            if (r12 == 0) goto L_0x00bf
            r10.setState(r1)
        L_0x00bf:
            return
        L_0x00c0:
            int[] r5 = io.netty.handler.ssl.SslHandler.AnonymousClass11.$SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus     // Catch:{ all -> 0x0131 }
            javax.net.ssl.SSLEngineResult$HandshakeStatus r6 = r7.getHandshakeStatus()     // Catch:{ all -> 0x0131 }
            int r6 = r6.ordinal()     // Catch:{ all -> 0x0131 }
            r5 = r5[r6]     // Catch:{ all -> 0x0131 }
            r6 = 1
            if (r5 == r6) goto L_0x0120
            r6 = 2
            if (r5 == r6) goto L_0x011b
            r6 = 3
            if (r5 == r6) goto L_0x011b
            r6 = 4
            if (r5 == r6) goto L_0x0104
            r0 = 5
            if (r5 != r0) goto L_0x00e9
            r10.readIfNeeded(r11)     // Catch:{ all -> 0x0131 }
            if (r4 == 0) goto L_0x00e3
            r4.release()
        L_0x00e3:
            if (r12 == 0) goto L_0x00e8
            r10.setState(r1)
        L_0x00e8:
            return
        L_0x00e9:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0131 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0131 }
            r0.<init>()     // Catch:{ all -> 0x0131 }
            java.lang.String r2 = "Unknown handshake status: "
            r0.append(r2)     // Catch:{ all -> 0x0131 }
            javax.net.ssl.SSLEngineResult$HandshakeStatus r2 = r7.getHandshakeStatus()     // Catch:{ all -> 0x0131 }
            r0.append(r2)     // Catch:{ all -> 0x0131 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0131 }
            r11.<init>(r0)     // Catch:{ all -> 0x0131 }
            throw r11     // Catch:{ all -> 0x0131 }
        L_0x0104:
            int r5 = r7.bytesProduced()     // Catch:{ all -> 0x0131 }
            if (r5 <= 0) goto L_0x000a
            io.netty.handler.ssl.SslHandler$SslHandlerCoalescingBufferQueue r5 = r10.pendingUnencryptedWrites     // Catch:{ all -> 0x0131 }
            boolean r5 = r5.isEmpty()     // Catch:{ all -> 0x0131 }
            if (r5 == 0) goto L_0x000a
            io.netty.handler.ssl.SslHandler$SslHandlerCoalescingBufferQueue r5 = r10.pendingUnencryptedWrites     // Catch:{ all -> 0x0131 }
            io.netty.buffer.ByteBuf r6 = io.netty.buffer.Unpooled.EMPTY_BUFFER     // Catch:{ all -> 0x0131 }
            r5.add(r6)     // Catch:{ all -> 0x0131 }
            goto L_0x000a
        L_0x011b:
            r10.setHandshakeSuccess()     // Catch:{ all -> 0x0131 }
            goto L_0x000a
        L_0x0120:
            boolean r5 = r10.runDelegatedTasks(r12)     // Catch:{ all -> 0x0131 }
            if (r5 != 0) goto L_0x000a
        L_0x0126:
            if (r4 == 0) goto L_0x012b
            r4.release()
        L_0x012b:
            if (r12 == 0) goto L_0x0130
            r10.setState(r1)
        L_0x0130:
            return
        L_0x0131:
            r11 = move-exception
            r2 = r4
            goto L_0x0135
        L_0x0134:
            r11 = move-exception
        L_0x0135:
            if (r2 == 0) goto L_0x013a
            r2.release()
        L_0x013a:
            if (r12 == 0) goto L_0x013f
            r10.setState(r1)
        L_0x013f:
            goto L_0x0141
        L_0x0140:
            throw r11
        L_0x0141:
            goto L_0x0140
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.ssl.SslHandler.wrap(io.netty.channel.ChannelHandlerContext, boolean):void");
    }

    /* renamed from: io.netty.handler.ssl.SslHandler$11  reason: invalid class name */
    static /* synthetic */ class AnonymousClass11 {
        static final /* synthetic */ int[] $SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus;

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                javax.net.ssl.SSLEngineResult$HandshakeStatus[] r0 = javax.net.ssl.SSLEngineResult.HandshakeStatus.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus = r0
                javax.net.ssl.SSLEngineResult$HandshakeStatus r1 = javax.net.ssl.SSLEngineResult.HandshakeStatus.NEED_TASK     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus     // Catch:{ NoSuchFieldError -> 0x001d }
                javax.net.ssl.SSLEngineResult$HandshakeStatus r1 = javax.net.ssl.SSLEngineResult.HandshakeStatus.FINISHED     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus     // Catch:{ NoSuchFieldError -> 0x0028 }
                javax.net.ssl.SSLEngineResult$HandshakeStatus r1 = javax.net.ssl.SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus     // Catch:{ NoSuchFieldError -> 0x0033 }
                javax.net.ssl.SSLEngineResult$HandshakeStatus r1 = javax.net.ssl.SSLEngineResult.HandshakeStatus.NEED_WRAP     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus     // Catch:{ NoSuchFieldError -> 0x003e }
                javax.net.ssl.SSLEngineResult$HandshakeStatus r1 = javax.net.ssl.SSLEngineResult.HandshakeStatus.NEED_UNWRAP     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.ssl.SslHandler.AnonymousClass11.<clinit>():void");
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x005f, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x009d, code lost:
        if (setHandshakeSuccess() == false) goto L_0x00ac;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x009f, code lost:
        if (r11 == false) goto L_0x00ac;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00a7, code lost:
        if (r9.pendingUnencryptedWrites.isEmpty() != false) goto L_0x00ac;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00a9, code lost:
        wrap(r10, true);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00ac, code lost:
        if (r2 == null) goto L_0x00b1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00ae, code lost:
        r2.release();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00b1, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean wrapNonAppData(final io.netty.channel.ChannelHandlerContext r10, boolean r11) throws javax.net.ssl.SSLException {
        /*
            r9 = this;
            io.netty.buffer.ByteBufAllocator r0 = r10.alloc()
            r1 = 0
            r2 = r1
        L_0x0006:
            boolean r3 = r10.isRemoved()     // Catch:{ all -> 0x00d8 }
            r4 = 0
            if (r3 != 0) goto L_0x00d2
            r3 = 1
            if (r2 != 0) goto L_0x0016
            r5 = 2048(0x800, float:2.87E-42)
            io.netty.buffer.ByteBuf r2 = r9.allocateOutNetBuf(r10, r5, r3)     // Catch:{ all -> 0x00d8 }
        L_0x0016:
            javax.net.ssl.SSLEngine r5 = r9.engine     // Catch:{ all -> 0x00d8 }
            io.netty.buffer.ByteBuf r6 = io.netty.buffer.Unpooled.EMPTY_BUFFER     // Catch:{ all -> 0x00d8 }
            javax.net.ssl.SSLEngineResult r5 = r9.wrap(r0, r5, r6, r2)     // Catch:{ all -> 0x00d8 }
            int r6 = r5.bytesProduced()     // Catch:{ all -> 0x00d8 }
            if (r6 <= 0) goto L_0x0038
            io.netty.channel.ChannelFuture r6 = r10.write(r2)     // Catch:{ all -> 0x00d8 }
            io.netty.handler.ssl.SslHandler$2 r7 = new io.netty.handler.ssl.SslHandler$2     // Catch:{ all -> 0x00d8 }
            r7.<init>(r10)     // Catch:{ all -> 0x00d8 }
            r6.addListener(r7)     // Catch:{ all -> 0x00d8 }
            if (r11 == 0) goto L_0x0037
            r6 = 16
            r9.setState(r6)     // Catch:{ all -> 0x00d8 }
        L_0x0037:
            r2 = r1
        L_0x0038:
            javax.net.ssl.SSLEngineResult$HandshakeStatus r6 = r5.getHandshakeStatus()     // Catch:{ all -> 0x00d8 }
            int[] r7 = io.netty.handler.ssl.SslHandler.AnonymousClass11.$SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus     // Catch:{ all -> 0x00d8 }
            int r8 = r6.ordinal()     // Catch:{ all -> 0x00d8 }
            r7 = r7[r8]     // Catch:{ all -> 0x00d8 }
            if (r7 == r3) goto L_0x00b2
            r8 = 2
            if (r7 == r8) goto L_0x0099
            r8 = 3
            if (r7 == r8) goto L_0x007b
            r3 = 4
            if (r7 == r3) goto L_0x00b9
            r3 = 5
            if (r7 != r3) goto L_0x0060
            if (r11 != 0) goto L_0x005a
            int r3 = r9.unwrapNonAppData(r10)     // Catch:{ all -> 0x00d8 }
            if (r3 > 0) goto L_0x00b9
        L_0x005a:
            if (r2 == 0) goto L_0x005f
            r2.release()
        L_0x005f:
            return r4
        L_0x0060:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException     // Catch:{ all -> 0x00d8 }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ all -> 0x00d8 }
            r11.<init>()     // Catch:{ all -> 0x00d8 }
            java.lang.String r0 = "Unknown handshake status: "
            r11.append(r0)     // Catch:{ all -> 0x00d8 }
            javax.net.ssl.SSLEngineResult$HandshakeStatus r0 = r5.getHandshakeStatus()     // Catch:{ all -> 0x00d8 }
            r11.append(r0)     // Catch:{ all -> 0x00d8 }
            java.lang.String r11 = r11.toString()     // Catch:{ all -> 0x00d8 }
            r10.<init>(r11)     // Catch:{ all -> 0x00d8 }
            throw r10     // Catch:{ all -> 0x00d8 }
        L_0x007b:
            boolean r0 = r9.setHandshakeSuccess()     // Catch:{ all -> 0x00d8 }
            if (r0 == 0) goto L_0x008e
            if (r11 == 0) goto L_0x008e
            io.netty.handler.ssl.SslHandler$SslHandlerCoalescingBufferQueue r0 = r9.pendingUnencryptedWrites     // Catch:{ all -> 0x00d8 }
            boolean r0 = r0.isEmpty()     // Catch:{ all -> 0x00d8 }
            if (r0 != 0) goto L_0x008e
            r9.wrap(r10, r3)     // Catch:{ all -> 0x00d8 }
        L_0x008e:
            if (r11 != 0) goto L_0x0093
            r9.unwrapNonAppData(r10)     // Catch:{ all -> 0x00d8 }
        L_0x0093:
            if (r2 == 0) goto L_0x0098
            r2.release()
        L_0x0098:
            return r3
        L_0x0099:
            boolean r0 = r9.setHandshakeSuccess()     // Catch:{ all -> 0x00d8 }
            if (r0 == 0) goto L_0x00ac
            if (r11 == 0) goto L_0x00ac
            io.netty.handler.ssl.SslHandler$SslHandlerCoalescingBufferQueue r11 = r9.pendingUnencryptedWrites     // Catch:{ all -> 0x00d8 }
            boolean r11 = r11.isEmpty()     // Catch:{ all -> 0x00d8 }
            if (r11 != 0) goto L_0x00ac
            r9.wrap(r10, r3)     // Catch:{ all -> 0x00d8 }
        L_0x00ac:
            if (r2 == 0) goto L_0x00b1
            r2.release()
        L_0x00b1:
            return r4
        L_0x00b2:
            boolean r3 = r9.runDelegatedTasks(r11)     // Catch:{ all -> 0x00d8 }
            if (r3 != 0) goto L_0x00b9
            goto L_0x00d2
        L_0x00b9:
            int r3 = r5.bytesProduced()     // Catch:{ all -> 0x00d8 }
            if (r3 != 0) goto L_0x00c4
            javax.net.ssl.SSLEngineResult$HandshakeStatus r3 = javax.net.ssl.SSLEngineResult.HandshakeStatus.NEED_TASK     // Catch:{ all -> 0x00d8 }
            if (r6 == r3) goto L_0x00c4
            goto L_0x00d2
        L_0x00c4:
            int r3 = r5.bytesConsumed()     // Catch:{ all -> 0x00d8 }
            if (r3 != 0) goto L_0x0006
            javax.net.ssl.SSLEngineResult$HandshakeStatus r3 = r5.getHandshakeStatus()     // Catch:{ all -> 0x00d8 }
            javax.net.ssl.SSLEngineResult$HandshakeStatus r5 = javax.net.ssl.SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING     // Catch:{ all -> 0x00d8 }
            if (r3 != r5) goto L_0x0006
        L_0x00d2:
            if (r2 == 0) goto L_0x00d7
            r2.release()
        L_0x00d7:
            return r4
        L_0x00d8:
            r10 = move-exception
            if (r2 == 0) goto L_0x00de
            r2.release()
        L_0x00de:
            goto L_0x00e0
        L_0x00df:
            throw r10
        L_0x00e0:
            goto L_0x00df
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.ssl.SslHandler.wrapNonAppData(io.netty.channel.ChannelHandlerContext, boolean):boolean");
    }

    private SSLEngineResult wrapMultiple(ByteBufAllocator byteBufAllocator, SSLEngine sSLEngine, ByteBuf byteBuf, ByteBuf byteBuf2) throws SSLException {
        SSLEngineResult wrap;
        SSLEngineResult sSLEngineResult = null;
        while (true) {
            int min = Math.min(16384, byteBuf.readableBytes());
            int calculateRequiredOutBufSpace = this.engineType.calculateRequiredOutBufSpace(this, min, byteBuf.nioBufferCount());
            if (!byteBuf2.isWritable(calculateRequiredOutBufSpace)) {
                if (sSLEngineResult != null) {
                    return sSLEngineResult;
                }
                byteBuf2.ensureWritable(calculateRequiredOutBufSpace);
            }
            ByteBuf readSlice = byteBuf.readSlice(min);
            wrap = wrap(byteBufAllocator, sSLEngine, readSlice, byteBuf2);
            if (wrap.getStatus() == SSLEngineResult.Status.CLOSED) {
                break;
            }
            if (readSlice.isReadable()) {
                byteBuf.readerIndex(byteBuf.readerIndex() - readSlice.readableBytes());
            }
            if (byteBuf.readableBytes() <= 0) {
                break;
            }
            sSLEngineResult = wrap;
        }
        return wrap;
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0070 A[Catch:{ all -> 0x0086 }, LOOP:0: B:18:0x0045->B:21:0x0070, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0082  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x007c A[EDGE_INSN: B:33:0x007c->B:22:0x007c ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private javax.net.ssl.SSLEngineResult wrap(io.netty.buffer.ByteBufAllocator r7, javax.net.ssl.SSLEngine r8, io.netty.buffer.ByteBuf r9, io.netty.buffer.ByteBuf r10) throws javax.net.ssl.SSLException {
        /*
            r6 = this;
            r0 = 0
            r1 = 0
            int r2 = r9.readerIndex()     // Catch:{ all -> 0x0088 }
            int r3 = r9.readableBytes()     // Catch:{ all -> 0x0088 }
            boolean r4 = r9.isDirect()     // Catch:{ all -> 0x0088 }
            if (r4 != 0) goto L_0x002b
            io.netty.handler.ssl.SslHandler$SslEngineType r4 = r6.engineType     // Catch:{ all -> 0x0088 }
            boolean r4 = r4.wantsDirectBuffer     // Catch:{ all -> 0x0088 }
            if (r4 != 0) goto L_0x0017
            goto L_0x002b
        L_0x0017:
            io.netty.buffer.ByteBuf r7 = r7.directBuffer(r3)     // Catch:{ all -> 0x0088 }
            r7.writeBytes((io.netty.buffer.ByteBuf) r9, (int) r2, (int) r3)     // Catch:{ all -> 0x0086 }
            java.nio.ByteBuffer[] r2 = r6.singleBuffer     // Catch:{ all -> 0x0086 }
            int r4 = r7.readerIndex()     // Catch:{ all -> 0x0086 }
            java.nio.ByteBuffer r3 = r7.internalNioBuffer(r4, r3)     // Catch:{ all -> 0x0086 }
            r2[r0] = r3     // Catch:{ all -> 0x0086 }
            goto L_0x0045
        L_0x002b:
            boolean r7 = r9 instanceof io.netty.buffer.CompositeByteBuf     // Catch:{ all -> 0x0088 }
            if (r7 != 0) goto L_0x0040
            int r7 = r9.nioBufferCount()     // Catch:{ all -> 0x0088 }
            r4 = 1
            if (r7 != r4) goto L_0x0040
            java.nio.ByteBuffer[] r7 = r6.singleBuffer     // Catch:{ all -> 0x0088 }
            java.nio.ByteBuffer r2 = r9.internalNioBuffer(r2, r3)     // Catch:{ all -> 0x0088 }
            r7[r0] = r2     // Catch:{ all -> 0x0088 }
            r2 = r7
            goto L_0x0044
        L_0x0040:
            java.nio.ByteBuffer[] r2 = r9.nioBuffers()     // Catch:{ all -> 0x0088 }
        L_0x0044:
            r7 = r1
        L_0x0045:
            int r3 = r10.writerIndex()     // Catch:{ all -> 0x0086 }
            int r4 = r10.writableBytes()     // Catch:{ all -> 0x0086 }
            java.nio.ByteBuffer r3 = toByteBuffer(r10, r3, r4)     // Catch:{ all -> 0x0086 }
            javax.net.ssl.SSLEngineResult r3 = r8.wrap(r2, r3)     // Catch:{ all -> 0x0086 }
            int r4 = r3.bytesConsumed()     // Catch:{ all -> 0x0086 }
            r9.skipBytes(r4)     // Catch:{ all -> 0x0086 }
            int r4 = r10.writerIndex()     // Catch:{ all -> 0x0086 }
            int r5 = r3.bytesProduced()     // Catch:{ all -> 0x0086 }
            int r4 = r4 + r5
            r10.writerIndex(r4)     // Catch:{ all -> 0x0086 }
            javax.net.ssl.SSLEngineResult$Status r4 = r3.getStatus()     // Catch:{ all -> 0x0086 }
            javax.net.ssl.SSLEngineResult$Status r5 = javax.net.ssl.SSLEngineResult.Status.BUFFER_OVERFLOW     // Catch:{ all -> 0x0086 }
            if (r4 != r5) goto L_0x007c
            javax.net.ssl.SSLSession r3 = r8.getSession()     // Catch:{ all -> 0x0086 }
            int r3 = r3.getPacketBufferSize()     // Catch:{ all -> 0x0086 }
            r10.ensureWritable(r3)     // Catch:{ all -> 0x0086 }
            goto L_0x0045
        L_0x007c:
            java.nio.ByteBuffer[] r8 = r6.singleBuffer
            r8[r0] = r1
            if (r7 == 0) goto L_0x0085
            r7.release()
        L_0x0085:
            return r3
        L_0x0086:
            r8 = move-exception
            goto L_0x008a
        L_0x0088:
            r8 = move-exception
            r7 = r1
        L_0x008a:
            java.nio.ByteBuffer[] r9 = r6.singleBuffer
            r9[r0] = r1
            if (r7 == 0) goto L_0x0093
            r7.release()
        L_0x0093:
            goto L_0x0095
        L_0x0094:
            throw r8
        L_0x0095:
            goto L_0x0094
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.ssl.SslHandler.wrap(io.netty.buffer.ByteBufAllocator, javax.net.ssl.SSLEngine, io.netty.buffer.ByteBuf, io.netty.buffer.ByteBuf):javax.net.ssl.SSLEngineResult");
    }

    public void channelInactive(ChannelHandlerContext channelHandlerContext) throws Exception {
        boolean z = this.handshakePromise.cause() != null;
        ClosedChannelException closedChannelException = new ClosedChannelException();
        if (isStateSet(8) && !this.handshakePromise.isDone()) {
            ThrowableUtil.addSuppressed((Throwable) closedChannelException, (Throwable) StacklessSSLHandshakeException.newInstance("Connection closed while SSL/TLS handshake was in progress", SslHandler.class, "channelInactive"));
        }
        setHandshakeFailure(channelHandlerContext, closedChannelException, !isStateSet(32), isStateSet(8), false);
        notifyClosePromise(closedChannelException);
        try {
            super.channelInactive(channelHandlerContext);
        } catch (DecoderException e) {
            if (!z || !(e.getCause() instanceof SSLException)) {
                throw e;
            }
        }
    }

    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable th) throws Exception {
        if (ignoreException(th)) {
            InternalLogger internalLogger = logger;
            if (internalLogger.isDebugEnabled()) {
                internalLogger.debug("{} Swallowing a harmless 'connection reset by peer / broken pipe' error that occurred while writing close_notify in response to the peer's close_notify", channelHandlerContext.channel(), th);
            }
            if (channelHandlerContext.channel().isActive()) {
                channelHandlerContext.close();
                return;
            }
            return;
        }
        channelHandlerContext.fireExceptionCaught(th);
    }

    private boolean ignoreException(Throwable th) {
        if (!(th instanceof SSLException) && (th instanceof IOException) && this.sslClosePromise.isDone()) {
            String message = th.getMessage();
            if (message != null && IGNORABLE_ERROR_MESSAGE.matcher(message).matches()) {
                return true;
            }
            for (StackTraceElement stackTraceElement : th.getStackTrace()) {
                String className = stackTraceElement.getClassName();
                String methodName = stackTraceElement.getMethodName();
                if (!className.startsWith("io.netty.") && "read".equals(methodName)) {
                    if (IGNORABLE_CLASS_IN_STACK.matcher(className).matches()) {
                        return true;
                    }
                    try {
                        Class<?> loadClass = PlatformDependent.getClassLoader(getClass()).loadClass(className);
                        if (!SocketChannel.class.isAssignableFrom(loadClass)) {
                            if (!DatagramChannel.class.isAssignableFrom(loadClass)) {
                                if (PlatformDependent.javaVersion() >= 7 && "com.sun.nio.sctp.SctpChannel".equals(loadClass.getSuperclass().getName())) {
                                }
                            }
                        }
                        return true;
                    } catch (Throwable th2) {
                        InternalLogger internalLogger = logger;
                        if (internalLogger.isDebugEnabled()) {
                            internalLogger.debug("Unexpected exception while loading class {} classname {}", getClass(), className, th2);
                        }
                    }
                }
            }
        }
        return false;
    }

    public static boolean isEncrypted(ByteBuf byteBuf) {
        if (byteBuf.readableBytes() >= 5) {
            return SslUtils.getEncryptedPacketLength(byteBuf, byteBuf.readerIndex()) != -2;
        }
        throw new IllegalArgumentException("buffer must have at least 5 readable bytes");
    }

    private void decodeJdkCompatible(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws NotSslRecordException {
        int i = this.packetLength;
        if (i <= 0) {
            int readableBytes = byteBuf.readableBytes();
            if (readableBytes >= 5) {
                int encryptedPacketLength = SslUtils.getEncryptedPacketLength(byteBuf, byteBuf.readerIndex());
                if (encryptedPacketLength == -2) {
                    NotSslRecordException notSslRecordException = new NotSslRecordException("not an SSL/TLS record: " + ByteBufUtil.hexDump(byteBuf));
                    byteBuf.skipBytes(byteBuf.readableBytes());
                    setHandshakeFailure(channelHandlerContext, notSslRecordException);
                    throw notSslRecordException;
                } else if (encryptedPacketLength != -1) {
                    if (encryptedPacketLength > readableBytes) {
                        this.packetLength = encryptedPacketLength;
                        return;
                    }
                    i = encryptedPacketLength;
                } else {
                    return;
                }
            } else {
                return;
            }
        } else if (byteBuf.readableBytes() < i) {
            return;
        }
        this.packetLength = 0;
        try {
            unwrap(channelHandlerContext, byteBuf, i);
        } catch (Throwable th) {
            handleUnwrapThrowable(channelHandlerContext, th);
        }
    }

    private void decodeNonJdkCompatible(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) {
        try {
            unwrap(channelHandlerContext, byteBuf, byteBuf.readableBytes());
        } catch (Throwable th) {
            handleUnwrapThrowable(channelHandlerContext, th);
        }
    }

    /* access modifiers changed from: private */
    public void handleUnwrapThrowable(ChannelHandlerContext channelHandlerContext, Throwable th) {
        try {
            if (this.handshakePromise.tryFailure(th)) {
                channelHandlerContext.fireUserEventTriggered(new SslHandshakeCompletionEvent(th));
            }
            if (this.pendingUnencryptedWrites != null) {
                wrapAndFlush(channelHandlerContext);
            }
        } catch (SSLException e) {
            logger.debug("SSLException during trying to call SSLEngine.wrap(...) because of an previous SSLException, ignoring...", (Throwable) e);
        } finally {
            ChannelHandlerContext channelHandlerContext2 = channelHandlerContext;
            Throwable th2 = th;
            setHandshakeFailure(channelHandlerContext2, th2, true, false, true);
        }
        PlatformDependent.throwException(th);
    }

    /* access modifiers changed from: protected */
    public void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws SSLException {
        if (!isStateSet(128)) {
            if (this.jdkCompatibilityMode) {
                decodeJdkCompatible(channelHandlerContext, byteBuf);
            } else {
                decodeNonJdkCompatible(channelHandlerContext, byteBuf);
            }
        }
    }

    public void channelReadComplete(ChannelHandlerContext channelHandlerContext) throws Exception {
        channelReadComplete0(channelHandlerContext);
    }

    /* access modifiers changed from: private */
    public void channelReadComplete0(ChannelHandlerContext channelHandlerContext) {
        discardSomeReadBytes();
        flushIfNeeded(channelHandlerContext);
        readIfNeeded(channelHandlerContext);
        clearState(256);
        channelHandlerContext.fireChannelReadComplete();
    }

    private void readIfNeeded(ChannelHandlerContext channelHandlerContext) {
        if (channelHandlerContext.channel().config().isAutoRead()) {
            return;
        }
        if (!isStateSet(256) || !this.handshakePromise.isDone()) {
            channelHandlerContext.read();
        }
    }

    private void flushIfNeeded(ChannelHandlerContext channelHandlerContext) {
        if (isStateSet(16)) {
            forceFlush(channelHandlerContext);
        }
    }

    /* access modifiers changed from: private */
    public int unwrapNonAppData(ChannelHandlerContext channelHandlerContext) throws SSLException {
        return unwrap(channelHandlerContext, Unpooled.EMPTY_BUFFER, 0);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0045, code lost:
        if (setHandshakeSuccess() == false) goto L_0x0047;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x00d3, code lost:
        if (r13 != javax.net.ssl.SSLEngineResult.HandshakeStatus.NEED_UNWRAP) goto L_0x00d8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x00d5, code lost:
        readIfNeeded(r17);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x00eb, code lost:
        if (r7 != false) goto L_0x00ed;
     */
    /* JADX WARNING: Removed duplicated region for block: B:101:0x00d0 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x0106  */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x010b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int unwrap(io.netty.channel.ChannelHandlerContext r17, io.netty.buffer.ByteBuf r18, int r19) throws javax.net.ssl.SSLException {
        /*
            r16 = this;
            r1 = r16
            r2 = r17
            r0 = r18
            r3 = r19
            io.netty.buffer.ByteBuf r4 = r1.allocate(r2, r3)
            r6 = r3
            r7 = 0
            r8 = 0
            r9 = 0
        L_0x0010:
            r10 = 0
            io.netty.handler.ssl.SslHandler$SslEngineType r11 = r1.engineType     // Catch:{ all -> 0x0103 }
            javax.net.ssl.SSLEngineResult r11 = r11.unwrap(r1, r0, r6, r4)     // Catch:{ all -> 0x0103 }
            javax.net.ssl.SSLEngineResult$Status r12 = r11.getStatus()     // Catch:{ all -> 0x0103 }
            javax.net.ssl.SSLEngineResult$HandshakeStatus r13 = r11.getHandshakeStatus()     // Catch:{ all -> 0x0103 }
            int r14 = r11.bytesProduced()     // Catch:{ all -> 0x0103 }
            int r11 = r11.bytesConsumed()     // Catch:{ all -> 0x0103 }
            r0.skipBytes(r11)     // Catch:{ all -> 0x0103 }
            int r6 = r6 - r11
            javax.net.ssl.SSLEngineResult$HandshakeStatus r15 = javax.net.ssl.SSLEngineResult.HandshakeStatus.FINISHED     // Catch:{ all -> 0x0103 }
            r5 = 1
            if (r13 == r15) goto L_0x0034
            javax.net.ssl.SSLEngineResult$HandshakeStatus r15 = javax.net.ssl.SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING     // Catch:{ all -> 0x0103 }
            if (r13 != r15) goto L_0x0050
        L_0x0034:
            boolean r15 = r4.isReadable()     // Catch:{ all -> 0x0103 }
            if (r15 == 0) goto L_0x0041
            boolean r15 = r16.setHandshakeSuccessUnwrapMarkReentry()     // Catch:{ all -> 0x0103 }
            if (r15 == 0) goto L_0x0047
            goto L_0x004e
        L_0x0041:
            boolean r15 = r16.setHandshakeSuccess()     // Catch:{ all -> 0x0103 }
            if (r15 != 0) goto L_0x004e
        L_0x0047:
            javax.net.ssl.SSLEngineResult$HandshakeStatus r15 = javax.net.ssl.SSLEngineResult.HandshakeStatus.FINISHED     // Catch:{ all -> 0x0103 }
            if (r13 != r15) goto L_0x004c
            goto L_0x004e
        L_0x004c:
            r15 = 0
            goto L_0x004f
        L_0x004e:
            r15 = 1
        L_0x004f:
            r7 = r7 | r15
        L_0x0050:
            boolean r15 = r4.isReadable()     // Catch:{ all -> 0x0103 }
            if (r15 == 0) goto L_0x0070
            r15 = 256(0x100, float:3.59E-43)
            r1.setState(r15)     // Catch:{ all -> 0x0103 }
            r15 = 512(0x200, float:7.175E-43)
            boolean r15 = r1.isStateSet(r15)     // Catch:{ all -> 0x0103 }
            if (r15 == 0) goto L_0x006c
            r1.executeChannelRead(r2, r4)     // Catch:{ all -> 0x0068 }
            r9 = 1
            goto L_0x006f
        L_0x0068:
            r0 = move-exception
            r9 = 1
            goto L_0x0104
        L_0x006c:
            r2.fireChannelRead(r4)     // Catch:{ all -> 0x0103 }
        L_0x006f:
            r4 = r10
        L_0x0070:
            javax.net.ssl.SSLEngineResult$Status r15 = javax.net.ssl.SSLEngineResult.Status.CLOSED     // Catch:{ all -> 0x0103 }
            if (r12 != r15) goto L_0x0076
            r8 = 1
            goto L_0x0098
        L_0x0076:
            javax.net.ssl.SSLEngineResult$Status r15 = javax.net.ssl.SSLEngineResult.Status.BUFFER_OVERFLOW     // Catch:{ all -> 0x0103 }
            if (r12 != r15) goto L_0x0098
            if (r4 == 0) goto L_0x007f
            r4.release()     // Catch:{ all -> 0x0103 }
        L_0x007f:
            javax.net.ssl.SSLEngine r11 = r1.engine     // Catch:{ all -> 0x0103 }
            javax.net.ssl.SSLSession r11 = r11.getSession()     // Catch:{ all -> 0x0103 }
            int r11 = r11.getApplicationBufferSize()     // Catch:{ all -> 0x0103 }
            io.netty.handler.ssl.SslHandler$SslEngineType r12 = r1.engineType     // Catch:{ all -> 0x0103 }
            if (r11 >= r14) goto L_0x008e
            goto L_0x008f
        L_0x008e:
            int r11 = r11 - r14
        L_0x008f:
            int r11 = r12.calculatePendingData(r1, r11)     // Catch:{ all -> 0x0103 }
            io.netty.buffer.ByteBuf r4 = r1.allocate(r2, r11)     // Catch:{ all -> 0x0103 }
            goto L_0x00ca
        L_0x0098:
            javax.net.ssl.SSLEngineResult$HandshakeStatus r15 = javax.net.ssl.SSLEngineResult.HandshakeStatus.NEED_TASK     // Catch:{ all -> 0x0103 }
            if (r13 != r15) goto L_0x00a4
            boolean r15 = r1.runDelegatedTasks(r5)     // Catch:{ all -> 0x0103 }
            if (r15 != 0) goto L_0x00b1
            r7 = 0
            goto L_0x00d8
        L_0x00a4:
            javax.net.ssl.SSLEngineResult$HandshakeStatus r15 = javax.net.ssl.SSLEngineResult.HandshakeStatus.NEED_WRAP     // Catch:{ all -> 0x0103 }
            if (r13 != r15) goto L_0x00b1
            boolean r15 = r1.wrapNonAppData(r2, r5)     // Catch:{ all -> 0x0103 }
            if (r15 == 0) goto L_0x00b1
            if (r6 != 0) goto L_0x00b1
            goto L_0x00d8
        L_0x00b1:
            javax.net.ssl.SSLEngineResult$Status r15 = javax.net.ssl.SSLEngineResult.Status.BUFFER_UNDERFLOW     // Catch:{ all -> 0x0103 }
            if (r12 == r15) goto L_0x00d1
            javax.net.ssl.SSLEngineResult$HandshakeStatus r12 = javax.net.ssl.SSLEngineResult.HandshakeStatus.NEED_TASK     // Catch:{ all -> 0x0103 }
            if (r13 == r12) goto L_0x00c4
            if (r11 != 0) goto L_0x00bd
            if (r14 == 0) goto L_0x00d1
        L_0x00bd:
            if (r6 != 0) goto L_0x00c4
            javax.net.ssl.SSLEngineResult$HandshakeStatus r11 = javax.net.ssl.SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING     // Catch:{ all -> 0x0103 }
            if (r13 != r11) goto L_0x00c4
            goto L_0x00d1
        L_0x00c4:
            if (r4 != 0) goto L_0x00ca
            io.netty.buffer.ByteBuf r4 = r1.allocate(r2, r6)     // Catch:{ all -> 0x0103 }
        L_0x00ca:
            boolean r11 = r17.isRemoved()     // Catch:{ all -> 0x0103 }
            if (r11 == 0) goto L_0x0010
            goto L_0x00d8
        L_0x00d1:
            javax.net.ssl.SSLEngineResult$HandshakeStatus r0 = javax.net.ssl.SSLEngineResult.HandshakeStatus.NEED_UNWRAP     // Catch:{ all -> 0x0103 }
            if (r13 != r0) goto L_0x00d8
            r16.readIfNeeded(r17)     // Catch:{ all -> 0x0103 }
        L_0x00d8:
            r0 = 2
            boolean r11 = r1.isStateSet(r0)     // Catch:{ all -> 0x0103 }
            if (r11 == 0) goto L_0x00eb
            io.netty.util.concurrent.Promise<io.netty.channel.Channel> r11 = r1.handshakePromise     // Catch:{ all -> 0x0103 }
            boolean r11 = r11.isDone()     // Catch:{ all -> 0x0103 }
            if (r11 == 0) goto L_0x00eb
            r1.clearState(r0)     // Catch:{ all -> 0x0103 }
            goto L_0x00ed
        L_0x00eb:
            if (r7 == 0) goto L_0x00f0
        L_0x00ed:
            r1.wrap(r2, r5)     // Catch:{ all -> 0x0103 }
        L_0x00f0:
            if (r4 == 0) goto L_0x00f5
            r4.release()
        L_0x00f5:
            if (r8 == 0) goto L_0x0100
            if (r9 == 0) goto L_0x00fd
            r16.executeNotifyClosePromise(r17)
            goto L_0x0100
        L_0x00fd:
            r1.notifyClosePromise(r10)
        L_0x0100:
            int r0 = r3 - r6
            return r0
        L_0x0103:
            r0 = move-exception
        L_0x0104:
            if (r4 == 0) goto L_0x0109
            r4.release()
        L_0x0109:
            if (r8 == 0) goto L_0x0114
            if (r9 == 0) goto L_0x0111
            r16.executeNotifyClosePromise(r17)
            goto L_0x0114
        L_0x0111:
            r1.notifyClosePromise(r10)
        L_0x0114:
            goto L_0x0116
        L_0x0115:
            throw r0
        L_0x0116:
            goto L_0x0115
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.ssl.SslHandler.unwrap(io.netty.channel.ChannelHandlerContext, io.netty.buffer.ByteBuf, int):int");
    }

    private boolean setHandshakeSuccessUnwrapMarkReentry() {
        boolean z = !isStateSet(512);
        if (z) {
            setState(512);
        }
        try {
            return setHandshakeSuccess();
        } finally {
            if (z) {
                clearState(512);
            }
        }
    }

    private void executeNotifyClosePromise(ChannelHandlerContext channelHandlerContext) {
        try {
            channelHandlerContext.executor().execute(new Runnable() {
                public void run() {
                    SslHandler.this.notifyClosePromise((Throwable) null);
                }
            });
        } catch (RejectedExecutionException e) {
            notifyClosePromise(e);
        }
    }

    private void executeChannelRead(final ChannelHandlerContext channelHandlerContext, final ByteBuf byteBuf) {
        try {
            channelHandlerContext.executor().execute(new Runnable() {
                public void run() {
                    channelHandlerContext.fireChannelRead(byteBuf);
                }
            });
        } catch (RejectedExecutionException e) {
            byteBuf.release();
            throw e;
        }
    }

    /* access modifiers changed from: private */
    public static ByteBuffer toByteBuffer(ByteBuf byteBuf, int i, int i2) {
        if (byteBuf.nioBufferCount() == 1) {
            return byteBuf.internalNioBuffer(i, i2);
        }
        return byteBuf.nioBuffer(i, i2);
    }

    private static boolean inEventLoop(Executor executor) {
        return (executor instanceof EventExecutor) && ((EventExecutor) executor).inEventLoop();
    }

    private boolean runDelegatedTasks(boolean z) {
        if (this.delegatedTaskExecutor == ImmediateExecutor.INSTANCE || inEventLoop(this.delegatedTaskExecutor)) {
            while (true) {
                Runnable delegatedTask = this.engine.getDelegatedTask();
                if (delegatedTask == null) {
                    return true;
                }
                setState(128);
                if (delegatedTask instanceof AsyncRunnable) {
                    try {
                        AsyncTaskCompletionHandler asyncTaskCompletionHandler = new AsyncTaskCompletionHandler(z);
                        ((AsyncRunnable) delegatedTask).run(asyncTaskCompletionHandler);
                        boolean resumeLater = asyncTaskCompletionHandler.resumeLater();
                        if (resumeLater) {
                            if (!resumeLater) {
                                clearState(128);
                            }
                            return false;
                        } else if (!resumeLater) {
                        }
                    } finally {
                        clearState(128);
                    }
                } else {
                    try {
                        delegatedTask.run();
                    } finally {
                        clearState(128);
                    }
                }
            }
        } else {
            executeDelegatedTask(z);
            return false;
        }
    }

    /* access modifiers changed from: private */
    public SslTasksRunner getTaskRunner(boolean z) {
        return z ? this.sslTaskRunnerForUnwrap : this.sslTaskRunner;
    }

    private void executeDelegatedTask(boolean z) {
        executeDelegatedTask(getTaskRunner(z));
    }

    /* access modifiers changed from: private */
    public void executeDelegatedTask(SslTasksRunner sslTasksRunner) {
        setState(128);
        try {
            this.delegatedTaskExecutor.execute(sslTasksRunner);
        } catch (RejectedExecutionException e) {
            clearState(128);
            throw e;
        }
    }

    private final class AsyncTaskCompletionHandler implements Runnable {
        boolean didRun;
        private final boolean inUnwrap;
        boolean resumeLater;

        AsyncTaskCompletionHandler(boolean z) {
            this.inUnwrap = z;
        }

        public void run() {
            this.didRun = true;
            if (this.resumeLater) {
                SslHandler.this.getTaskRunner(this.inUnwrap).runComplete();
            }
        }

        /* access modifiers changed from: package-private */
        public boolean resumeLater() {
            if (this.didRun) {
                return false;
            }
            this.resumeLater = true;
            return true;
        }
    }

    private final class SslTasksRunner implements Runnable {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final boolean inUnwrap;
        private final Runnable runCompleteTask = new Runnable() {
            public void run() {
                SslTasksRunner.this.runComplete();
            }
        };

        static {
            Class<SslHandler> cls = SslHandler.class;
        }

        SslTasksRunner(boolean z) {
            this.inUnwrap = z;
        }

        private void taskError(Throwable th) {
            if (this.inUnwrap) {
                try {
                    SslHandler sslHandler = SslHandler.this;
                    sslHandler.handleUnwrapThrowable(sslHandler.ctx, th);
                } catch (Throwable th2) {
                    safeExceptionCaught(th2);
                }
            } else {
                SslHandler sslHandler2 = SslHandler.this;
                sslHandler2.setHandshakeFailure(sslHandler2.ctx, th);
                SslHandler sslHandler3 = SslHandler.this;
                sslHandler3.forceFlush(sslHandler3.ctx);
            }
        }

        /* access modifiers changed from: private */
        public void safeExceptionCaught(Throwable th) {
            try {
                SslHandler sslHandler = SslHandler.this;
                sslHandler.exceptionCaught(sslHandler.ctx, wrapIfNeeded(th));
            } catch (Throwable th2) {
                SslHandler.this.ctx.fireExceptionCaught(th2);
            }
        }

        private Throwable wrapIfNeeded(Throwable th) {
            return (this.inUnwrap && !(th instanceof DecoderException)) ? new DecoderException(th) : th;
        }

        private void tryDecodeAgain() {
            try {
                SslHandler sslHandler = SslHandler.this;
                sslHandler.channelRead(sslHandler.ctx, Unpooled.EMPTY_BUFFER);
            } catch (Throwable th) {
                SslHandler.this.channelReadComplete0(SslHandler.this.ctx);
                throw th;
            }
            SslHandler sslHandler2 = SslHandler.this;
            sslHandler2.channelReadComplete0(sslHandler2.ctx);
        }

        /* access modifiers changed from: private */
        public void resumeOnEventExecutor() {
            SslHandler.this.clearState(128);
            try {
                int i = AnonymousClass11.$SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus[SslHandler.this.engine.getHandshakeStatus().ordinal()];
                if (i == 1) {
                    SslHandler.this.executeDelegatedTask(this);
                } else if (i == 2 || i == 3) {
                    boolean unused = SslHandler.this.setHandshakeSuccess();
                    try {
                        SslHandler sslHandler = SslHandler.this;
                        sslHandler.wrap(sslHandler.ctx, this.inUnwrap);
                        if (this.inUnwrap) {
                            SslHandler sslHandler2 = SslHandler.this;
                            int unused2 = sslHandler2.unwrapNonAppData(sslHandler2.ctx);
                        }
                        SslHandler sslHandler3 = SslHandler.this;
                        sslHandler3.forceFlush(sslHandler3.ctx);
                        tryDecodeAgain();
                    } catch (Throwable th) {
                        taskError(th);
                    }
                } else if (i == 4) {
                    try {
                        SslHandler sslHandler4 = SslHandler.this;
                        if (!sslHandler4.wrapNonAppData(sslHandler4.ctx, false) && this.inUnwrap) {
                            SslHandler sslHandler5 = SslHandler.this;
                            int unused3 = sslHandler5.unwrapNonAppData(sslHandler5.ctx);
                        }
                        SslHandler sslHandler6 = SslHandler.this;
                        sslHandler6.forceFlush(sslHandler6.ctx);
                        tryDecodeAgain();
                    } catch (Throwable th2) {
                        taskError(th2);
                    }
                } else if (i == 5) {
                    SslHandler sslHandler7 = SslHandler.this;
                    int unused4 = sslHandler7.unwrapNonAppData(sslHandler7.ctx);
                    tryDecodeAgain();
                } else {
                    throw new AssertionError();
                }
            } catch (SSLException e) {
                SslHandler sslHandler8 = SslHandler.this;
                sslHandler8.handleUnwrapThrowable(sslHandler8.ctx, e);
            } catch (Throwable th3) {
                safeExceptionCaught(th3);
            }
        }

        /* access modifiers changed from: package-private */
        public void runComplete() {
            SslHandler.this.ctx.executor().execute(new Runnable() {
                public void run() {
                    SslTasksRunner.this.resumeOnEventExecutor();
                }
            });
        }

        public void run() {
            try {
                Runnable delegatedTask = SslHandler.this.engine.getDelegatedTask();
                if (delegatedTask != null) {
                    if (delegatedTask instanceof AsyncRunnable) {
                        ((AsyncRunnable) delegatedTask).run(this.runCompleteTask);
                        return;
                    }
                    delegatedTask.run();
                    runComplete();
                }
            } catch (Throwable th) {
                handleException(th);
            }
        }

        private void handleException(final Throwable th) {
            EventExecutor executor = SslHandler.this.ctx.executor();
            if (executor.inEventLoop()) {
                SslHandler.this.clearState(128);
                safeExceptionCaught(th);
                return;
            }
            try {
                executor.execute(new Runnable() {
                    public void run() {
                        SslHandler.this.clearState(128);
                        SslTasksRunner.this.safeExceptionCaught(th);
                    }
                });
            } catch (RejectedExecutionException unused) {
                SslHandler.this.clearState(128);
                SslHandler.this.ctx.fireExceptionCaught(th);
            }
        }
    }

    /* access modifiers changed from: private */
    public boolean setHandshakeSuccess() {
        boolean z = !this.handshakePromise.isDone() && this.handshakePromise.trySuccess(this.ctx.channel());
        if (z) {
            InternalLogger internalLogger = logger;
            if (internalLogger.isDebugEnabled()) {
                SSLSession session = this.engine.getSession();
                internalLogger.debug("{} HANDSHAKEN: protocol:{} cipher suite:{}", this.ctx.channel(), session.getProtocol(), session.getCipherSuite());
            }
            this.ctx.fireUserEventTriggered(SslHandshakeCompletionEvent.SUCCESS);
        }
        if (isStateSet(4)) {
            clearState(4);
            if (!this.ctx.channel().config().isAutoRead()) {
                this.ctx.read();
            }
        }
        return z;
    }

    /* access modifiers changed from: private */
    public void setHandshakeFailure(ChannelHandlerContext channelHandlerContext, Throwable th) {
        setHandshakeFailure(channelHandlerContext, th, true, true, false);
    }

    private void setHandshakeFailure(ChannelHandlerContext channelHandlerContext, Throwable th, boolean z, boolean z2, boolean z3) {
        String message;
        try {
            setState(32);
            this.engine.closeOutbound();
            if (z) {
                this.engine.closeInbound();
            }
        } catch (SSLException e) {
            InternalLogger internalLogger = logger;
            if (internalLogger.isDebugEnabled() && ((message = e.getMessage()) == null || (!message.contains("possible truncation attack") && !message.contains("closing inbound before receiving peer's close_notify")))) {
                internalLogger.debug("{} SSLEngine.closeInbound() raised an exception.", channelHandlerContext.channel(), e);
            }
        } catch (Throwable th2) {
            releaseAndFailAll(channelHandlerContext, th);
            throw th2;
        }
        if (this.handshakePromise.tryFailure(th) || z3) {
            SslUtils.handleHandshakeFailure(channelHandlerContext, th, z2);
        }
        releaseAndFailAll(channelHandlerContext, th);
    }

    /* access modifiers changed from: private */
    public void setHandshakeFailureTransportFailure(ChannelHandlerContext channelHandlerContext, Throwable th) {
        try {
            SSLException sSLException = new SSLException("failure when writing TLS control frames", th);
            releaseAndFailAll(channelHandlerContext, sSLException);
            if (this.handshakePromise.tryFailure(sSLException)) {
                channelHandlerContext.fireUserEventTriggered(new SslHandshakeCompletionEvent(sSLException));
            }
        } finally {
            channelHandlerContext.close();
        }
    }

    /* access modifiers changed from: private */
    public void releaseAndFailAll(ChannelHandlerContext channelHandlerContext, Throwable th) {
        SslHandlerCoalescingBufferQueue sslHandlerCoalescingBufferQueue = this.pendingUnencryptedWrites;
        if (sslHandlerCoalescingBufferQueue != null) {
            sslHandlerCoalescingBufferQueue.releaseAndFailAll(channelHandlerContext, th);
        }
    }

    /* access modifiers changed from: private */
    public void notifyClosePromise(Throwable th) {
        if (th == null) {
            if (this.sslClosePromise.trySuccess(this.ctx.channel())) {
                this.ctx.fireUserEventTriggered(SslCloseCompletionEvent.SUCCESS);
            }
        } else if (this.sslClosePromise.tryFailure(th)) {
            this.ctx.fireUserEventTriggered(new SslCloseCompletionEvent(th));
        }
    }

    private void closeOutboundAndChannel(ChannelHandlerContext channelHandlerContext, final ChannelPromise channelPromise, boolean z) throws Exception {
        boolean isStateSet;
        setState(32);
        this.engine.closeOutbound();
        if (channelHandlerContext.channel().isActive()) {
            ChannelPromise newPromise = channelHandlerContext.newPromise();
            try {
                flush(channelHandlerContext, newPromise);
                if (isStateSet) {
                    this.sslClosePromise.addListener((GenericFutureListener) new FutureListener<Channel>() {
                        public void operationComplete(Future<Channel> future) {
                            channelPromise.setSuccess();
                        }
                    });
                }
            } finally {
                if (!isStateSet(64)) {
                    setState(64);
                    safeClose(channelHandlerContext, newPromise, (ChannelPromise) PromiseNotifier.cascade(false, channelHandlerContext.newPromise(), channelPromise));
                } else {
                    this.sslClosePromise.addListener((GenericFutureListener) new FutureListener<Channel>() {
                        public void operationComplete(Future<Channel> future) {
                            channelPromise.setSuccess();
                        }
                    });
                }
            }
        } else if (z) {
            channelHandlerContext.disconnect(channelPromise);
        } else {
            channelHandlerContext.close(channelPromise);
        }
    }

    private void flush(ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise) throws Exception {
        SslHandlerCoalescingBufferQueue sslHandlerCoalescingBufferQueue = this.pendingUnencryptedWrites;
        if (sslHandlerCoalescingBufferQueue != null) {
            sslHandlerCoalescingBufferQueue.add(Unpooled.EMPTY_BUFFER, channelPromise);
        } else {
            channelPromise.setFailure(newPendingWritesNullException());
        }
        flush(channelHandlerContext);
    }

    public void handlerAdded(ChannelHandlerContext channelHandlerContext) throws Exception {
        this.ctx = channelHandlerContext;
        Channel channel = channelHandlerContext.channel();
        this.pendingUnencryptedWrites = new SslHandlerCoalescingBufferQueue(channel, 16);
        setOpensslEngineSocketFd(channel);
        boolean equals = Boolean.TRUE.equals(channel.config().getOption(ChannelOption.TCP_FASTOPEN_CONNECT));
        boolean isActive = channel.isActive();
        if (isActive || equals) {
            startHandshakeProcessing(isActive);
            if (equals) {
                ChannelOutboundBuffer outboundBuffer = channel.unsafe().outboundBuffer();
                if (outboundBuffer == null || outboundBuffer.totalPendingWriteBytes() > 0) {
                    setState(16);
                }
            }
        }
    }

    private void startHandshakeProcessing(boolean z) {
        if (!isStateSet(8)) {
            setState(8);
            if (this.engine.getUseClientMode()) {
                handshake(z);
            }
            applyHandshakeTimeout();
        } else if (isStateSet(16)) {
            forceFlush(this.ctx);
        }
    }

    public Future<Channel> renegotiate() {
        ChannelHandlerContext channelHandlerContext = this.ctx;
        if (channelHandlerContext != null) {
            return renegotiate(channelHandlerContext.executor().newPromise());
        }
        throw new IllegalStateException();
    }

    public Future<Channel> renegotiate(final Promise<Channel> promise) {
        ObjectUtil.checkNotNull(promise, "promise");
        ChannelHandlerContext channelHandlerContext = this.ctx;
        if (channelHandlerContext != null) {
            EventExecutor executor = channelHandlerContext.executor();
            if (!executor.inEventLoop()) {
                executor.execute(new Runnable() {
                    public void run() {
                        SslHandler.this.renegotiateOnEventLoop(promise);
                    }
                });
                return promise;
            }
            renegotiateOnEventLoop(promise);
            return promise;
        }
        throw new IllegalStateException();
    }

    /* access modifiers changed from: private */
    public void renegotiateOnEventLoop(Promise<Channel> promise) {
        Promise<Channel> promise2 = this.handshakePromise;
        if (!promise2.isDone()) {
            PromiseNotifier.cascade(promise2, promise);
            return;
        }
        this.handshakePromise = promise;
        handshake(true);
        applyHandshakeTimeout();
    }

    private void handshake(boolean z) {
        if (this.engine.getHandshakeStatus() == SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING && !this.handshakePromise.isDone()) {
            ChannelHandlerContext channelHandlerContext = this.ctx;
            try {
                this.engine.beginHandshake();
                wrapNonAppData(channelHandlerContext, false);
                if (!z) {
                    return;
                }
            } catch (Throwable th) {
                if (z) {
                    forceFlush(channelHandlerContext);
                }
                throw th;
            }
            forceFlush(channelHandlerContext);
        }
    }

    private void applyHandshakeTimeout() {
        final Promise<Channel> promise = this.handshakePromise;
        final long j = this.handshakeTimeoutMillis;
        if (j > 0 && !promise.isDone()) {
            final ScheduledFuture<?> schedule = this.ctx.executor().schedule((Runnable) new Runnable() {
                public void run() {
                    if (!promise.isDone()) {
                        SslHandshakeTimeoutException sslHandshakeTimeoutException = new SslHandshakeTimeoutException("handshake timed out after " + j + "ms");
                        try {
                            if (promise.tryFailure(sslHandshakeTimeoutException)) {
                                SslUtils.handleHandshakeFailure(SslHandler.this.ctx, sslHandshakeTimeoutException, true);
                            }
                        } finally {
                            SslHandler sslHandler = SslHandler.this;
                            sslHandler.releaseAndFailAll(sslHandler.ctx, sslHandshakeTimeoutException);
                        }
                    }
                }
            }, j, TimeUnit.MILLISECONDS);
            promise.addListener(new FutureListener<Channel>() {
                public void operationComplete(Future<Channel> future) throws Exception {
                    schedule.cancel(false);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void forceFlush(ChannelHandlerContext channelHandlerContext) {
        clearState(16);
        channelHandlerContext.flush();
    }

    private void setOpensslEngineSocketFd(Channel channel) {
        if (channel instanceof UnixChannel) {
            SSLEngine sSLEngine = this.engine;
            if (sSLEngine instanceof ReferenceCountedOpenSslEngine) {
                ((ReferenceCountedOpenSslEngine) sSLEngine).bioSetFd(((UnixChannel) channel).fd().intValue());
            }
        }
    }

    public void channelActive(ChannelHandlerContext channelHandlerContext) throws Exception {
        setOpensslEngineSocketFd(channelHandlerContext.channel());
        if (!this.startTls) {
            startHandshakeProcessing(true);
        }
        channelHandlerContext.fireChannelActive();
    }

    private void safeClose(final ChannelHandlerContext channelHandlerContext, final ChannelFuture channelFuture, final ChannelPromise channelPromise) {
        if (!channelHandlerContext.channel().isActive()) {
            channelHandlerContext.close(channelPromise);
            return;
        }
        final ScheduledFuture<?> scheduledFuture = null;
        if (!channelFuture.isDone()) {
            long j = this.closeNotifyFlushTimeoutMillis;
            if (j > 0) {
                scheduledFuture = channelHandlerContext.executor().schedule((Runnable) new Runnable() {
                    public void run() {
                        if (!channelFuture.isDone()) {
                            SslHandler.logger.warn("{} Last write attempt timed out; force-closing the connection.", (Object) channelHandlerContext.channel());
                            ChannelHandlerContext channelHandlerContext = channelHandlerContext;
                            SslHandler.addCloseListener(channelHandlerContext.close(channelHandlerContext.newPromise()), channelPromise);
                        }
                    }
                }, j, TimeUnit.MILLISECONDS);
            }
        }
        channelFuture.addListener(new ChannelFutureListener() {
            public void operationComplete(ChannelFuture channelFuture) {
                Future future = scheduledFuture;
                if (future != null) {
                    future.cancel(false);
                }
                final long access$2600 = SslHandler.this.closeNotifyReadTimeoutMillis;
                if (access$2600 <= 0) {
                    ChannelHandlerContext channelHandlerContext = channelHandlerContext;
                    SslHandler.addCloseListener(channelHandlerContext.close(channelHandlerContext.newPromise()), channelPromise);
                    return;
                }
                final ScheduledFuture<?> schedule = !SslHandler.this.sslClosePromise.isDone() ? channelHandlerContext.executor().schedule((Runnable) new Runnable() {
                    public void run() {
                        if (!SslHandler.this.sslClosePromise.isDone()) {
                            SslHandler.logger.debug("{} did not receive close_notify in {}ms; force-closing the connection.", channelHandlerContext.channel(), Long.valueOf(access$2600));
                            SslHandler.addCloseListener(channelHandlerContext.close(channelHandlerContext.newPromise()), channelPromise);
                        }
                    }
                }, access$2600, TimeUnit.MILLISECONDS) : null;
                SslHandler.this.sslClosePromise.addListener((GenericFutureListener) new FutureListener<Channel>() {
                    public void operationComplete(Future<Channel> future) throws Exception {
                        Future future2 = schedule;
                        if (future2 != null) {
                            future2.cancel(false);
                        }
                        SslHandler.addCloseListener(channelHandlerContext.close(channelHandlerContext.newPromise()), channelPromise);
                    }
                });
            }
        });
    }

    /* access modifiers changed from: private */
    public static void addCloseListener(ChannelFuture channelFuture, ChannelPromise channelPromise) {
        PromiseNotifier.cascade(false, channelFuture, channelPromise);
    }

    private ByteBuf allocate(ChannelHandlerContext channelHandlerContext, int i) {
        ByteBufAllocator alloc = channelHandlerContext.alloc();
        if (this.engineType.wantsDirectBuffer) {
            return alloc.directBuffer(i);
        }
        return alloc.buffer(i);
    }

    private ByteBuf allocateOutNetBuf(ChannelHandlerContext channelHandlerContext, int i, int i2) {
        return this.engineType.allocateWrapBuffer(this, channelHandlerContext.alloc(), i, i2);
    }

    private boolean isStateSet(int i) {
        return (this.state & i) == i;
    }

    private void setState(int i) {
        this.state = (short) (i | this.state);
    }

    /* access modifiers changed from: private */
    public void clearState(int i) {
        this.state = (short) ((i ^ -1) & this.state);
    }

    private final class SslHandlerCoalescingBufferQueue extends AbstractCoalescingBufferQueue {
        /* access modifiers changed from: protected */
        public ByteBuf removeEmptyValue() {
            return null;
        }

        SslHandlerCoalescingBufferQueue(Channel channel, int i) {
            super(channel, i);
        }

        /* access modifiers changed from: protected */
        public ByteBuf compose(ByteBufAllocator byteBufAllocator, ByteBuf byteBuf, ByteBuf byteBuf2) {
            int i = SslHandler.this.wrapDataSize;
            if (!(byteBuf instanceof CompositeByteBuf)) {
                return SslHandler.attemptCopyToCumulation(byteBuf, byteBuf2, i) ? byteBuf : copyAndCompose(byteBufAllocator, byteBuf, byteBuf2);
            }
            CompositeByteBuf compositeByteBuf = (CompositeByteBuf) byteBuf;
            int numComponents = compositeByteBuf.numComponents();
            if (numComponents == 0 || !SslHandler.attemptCopyToCumulation(compositeByteBuf.internalComponent(numComponents - 1), byteBuf2, i)) {
                compositeByteBuf.addComponent(true, byteBuf2);
            }
            return compositeByteBuf;
        }

        /* access modifiers changed from: protected */
        public ByteBuf composeFirst(ByteBufAllocator byteBufAllocator, ByteBuf byteBuf) {
            ByteBuf byteBuf2;
            if (!(byteBuf instanceof CompositeByteBuf)) {
                return byteBuf;
            }
            CompositeByteBuf compositeByteBuf = (CompositeByteBuf) byteBuf;
            if (SslHandler.this.engineType.wantsDirectBuffer) {
                byteBuf2 = byteBufAllocator.directBuffer(compositeByteBuf.readableBytes());
            } else {
                byteBuf2 = byteBufAllocator.heapBuffer(compositeByteBuf.readableBytes());
            }
            try {
                byteBuf2.writeBytes((ByteBuf) compositeByteBuf);
            } catch (Throwable th) {
                byteBuf2.release();
                PlatformDependent.throwException(th);
            }
            compositeByteBuf.release();
            return byteBuf2;
        }
    }

    /* access modifiers changed from: private */
    public static boolean attemptCopyToCumulation(ByteBuf byteBuf, ByteBuf byteBuf2, int i) {
        int readableBytes = byteBuf2.readableBytes();
        int capacity = byteBuf.capacity();
        if (i - byteBuf.readableBytes() < readableBytes || ((!byteBuf.isWritable(readableBytes) || capacity < i) && (capacity >= i || !ByteBufUtil.ensureWritableSuccess(byteBuf.ensureWritable(readableBytes, false))))) {
            return false;
        }
        byteBuf.writeBytes(byteBuf2);
        byteBuf2.release();
        return true;
    }

    private final class LazyChannelPromise extends DefaultPromise<Channel> {
        private LazyChannelPromise() {
        }

        /* access modifiers changed from: protected */
        public EventExecutor executor() {
            if (SslHandler.this.ctx != null) {
                return SslHandler.this.ctx.executor();
            }
            throw new IllegalStateException();
        }

        /* access modifiers changed from: protected */
        public void checkDeadLock() {
            if (SslHandler.this.ctx != null) {
                super.checkDeadLock();
            }
        }
    }
}
