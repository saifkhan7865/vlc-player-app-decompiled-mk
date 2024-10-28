package io.netty.channel.kqueue;

import io.netty.buffer.ByteBuf;
import io.netty.channel.AddressedEnvelope;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelMetadata;
import io.netty.channel.ChannelPromise;
import io.netty.channel.DefaultAddressedEnvelope;
import io.netty.channel.kqueue.AbstractKQueueChannel;
import io.netty.channel.socket.DatagramChannel;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.InternetProtocolFamily;
import io.netty.channel.unix.UnixChannelUtil;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.StringUtil;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.channels.UnresolvedAddressException;
import kotlin.text.Typography;

public final class KQueueDatagramChannel extends AbstractKQueueDatagramChannel implements DatagramChannel {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String EXPECTED_TYPES = (" (expected: " + StringUtil.simpleClassName((Class<?>) DatagramPacket.class) + ", " + StringUtil.simpleClassName((Class<?>) AddressedEnvelope.class) + Typography.less + StringUtil.simpleClassName((Class<?>) ByteBuf.class) + ", " + StringUtil.simpleClassName((Class<?>) InetSocketAddress.class) + ">, " + StringUtil.simpleClassName((Class<?>) ByteBuf.class) + ')');
    private final KQueueDatagramChannelConfig config;
    private volatile boolean connected;

    public /* bridge */ /* synthetic */ boolean isOpen() {
        return super.isOpen();
    }

    public /* bridge */ /* synthetic */ ChannelMetadata metadata() {
        return super.metadata();
    }

    public KQueueDatagramChannel() {
        super((Channel) null, BsdSocket.newSocketDgram(), false);
        this.config = new KQueueDatagramChannelConfig(this);
    }

    public KQueueDatagramChannel(InternetProtocolFamily internetProtocolFamily) {
        super((Channel) null, BsdSocket.newSocketDgram(internetProtocolFamily), false);
        this.config = new KQueueDatagramChannelConfig(this);
    }

    public KQueueDatagramChannel(int i) {
        this(new BsdSocket(i), true);
    }

    KQueueDatagramChannel(BsdSocket bsdSocket, boolean z) {
        super((Channel) null, bsdSocket, z);
        this.config = new KQueueDatagramChannelConfig(this);
    }

    public InetSocketAddress remoteAddress() {
        return (InetSocketAddress) super.remoteAddress();
    }

    public InetSocketAddress localAddress() {
        return (InetSocketAddress) super.localAddress();
    }

    public boolean isActive() {
        return this.socket.isOpen() && ((this.config.getActiveOnOpen() && isRegistered()) || this.active);
    }

    public boolean isConnected() {
        return this.connected;
    }

    public ChannelFuture joinGroup(InetAddress inetAddress) {
        return joinGroup(inetAddress, newPromise());
    }

    public ChannelFuture joinGroup(InetAddress inetAddress, ChannelPromise channelPromise) {
        try {
            NetworkInterface networkInterface = config().getNetworkInterface();
            if (networkInterface == null) {
                networkInterface = NetworkInterface.getByInetAddress(localAddress().getAddress());
            }
            return joinGroup(inetAddress, networkInterface, (InetAddress) null, channelPromise);
        } catch (SocketException e) {
            channelPromise.setFailure(e);
            return channelPromise;
        }
    }

    public ChannelFuture joinGroup(InetSocketAddress inetSocketAddress, NetworkInterface networkInterface) {
        return joinGroup(inetSocketAddress, networkInterface, newPromise());
    }

    public ChannelFuture joinGroup(InetSocketAddress inetSocketAddress, NetworkInterface networkInterface, ChannelPromise channelPromise) {
        return joinGroup(inetSocketAddress.getAddress(), networkInterface, (InetAddress) null, channelPromise);
    }

    public ChannelFuture joinGroup(InetAddress inetAddress, NetworkInterface networkInterface, InetAddress inetAddress2) {
        return joinGroup(inetAddress, networkInterface, inetAddress2, newPromise());
    }

    public ChannelFuture joinGroup(InetAddress inetAddress, NetworkInterface networkInterface, InetAddress inetAddress2, ChannelPromise channelPromise) {
        ObjectUtil.checkNotNull(inetAddress, "multicastAddress");
        ObjectUtil.checkNotNull(networkInterface, "networkInterface");
        channelPromise.setFailure(new UnsupportedOperationException("Multicast not supported"));
        return channelPromise;
    }

    public ChannelFuture leaveGroup(InetAddress inetAddress) {
        return leaveGroup(inetAddress, newPromise());
    }

    public ChannelFuture leaveGroup(InetAddress inetAddress, ChannelPromise channelPromise) {
        try {
            return leaveGroup(inetAddress, NetworkInterface.getByInetAddress(localAddress().getAddress()), (InetAddress) null, channelPromise);
        } catch (SocketException e) {
            channelPromise.setFailure(e);
            return channelPromise;
        }
    }

    public ChannelFuture leaveGroup(InetSocketAddress inetSocketAddress, NetworkInterface networkInterface) {
        return leaveGroup(inetSocketAddress, networkInterface, newPromise());
    }

    public ChannelFuture leaveGroup(InetSocketAddress inetSocketAddress, NetworkInterface networkInterface, ChannelPromise channelPromise) {
        return leaveGroup(inetSocketAddress.getAddress(), networkInterface, (InetAddress) null, channelPromise);
    }

    public ChannelFuture leaveGroup(InetAddress inetAddress, NetworkInterface networkInterface, InetAddress inetAddress2) {
        return leaveGroup(inetAddress, networkInterface, inetAddress2, newPromise());
    }

    public ChannelFuture leaveGroup(InetAddress inetAddress, NetworkInterface networkInterface, InetAddress inetAddress2, ChannelPromise channelPromise) {
        ObjectUtil.checkNotNull(inetAddress, "multicastAddress");
        ObjectUtil.checkNotNull(networkInterface, "networkInterface");
        channelPromise.setFailure(new UnsupportedOperationException("Multicast not supported"));
        return channelPromise;
    }

    public ChannelFuture block(InetAddress inetAddress, NetworkInterface networkInterface, InetAddress inetAddress2) {
        return block(inetAddress, networkInterface, inetAddress2, newPromise());
    }

    public ChannelFuture block(InetAddress inetAddress, NetworkInterface networkInterface, InetAddress inetAddress2, ChannelPromise channelPromise) {
        ObjectUtil.checkNotNull(inetAddress, "multicastAddress");
        ObjectUtil.checkNotNull(inetAddress2, "sourceToBlock");
        ObjectUtil.checkNotNull(networkInterface, "networkInterface");
        channelPromise.setFailure(new UnsupportedOperationException("Multicast not supported"));
        return channelPromise;
    }

    public ChannelFuture block(InetAddress inetAddress, InetAddress inetAddress2) {
        return block(inetAddress, inetAddress2, newPromise());
    }

    public ChannelFuture block(InetAddress inetAddress, InetAddress inetAddress2, ChannelPromise channelPromise) {
        try {
            return block(inetAddress, NetworkInterface.getByInetAddress(localAddress().getAddress()), inetAddress2, channelPromise);
        } catch (Throwable th) {
            channelPromise.setFailure(th);
            return channelPromise;
        }
    }

    /* access modifiers changed from: protected */
    public AbstractKQueueChannel.AbstractKQueueUnsafe newUnsafe() {
        return new KQueueDatagramChannelUnsafe();
    }

    /* access modifiers changed from: protected */
    public void doBind(SocketAddress socketAddress) throws Exception {
        super.doBind(socketAddress);
        this.active = true;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00cd A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:27:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean doWriteMessage(java.lang.Object r13) throws java.lang.Exception {
        /*
            r12 = this;
            boolean r0 = r13 instanceof io.netty.channel.AddressedEnvelope
            if (r0 == 0) goto L_0x0013
            io.netty.channel.AddressedEnvelope r13 = (io.netty.channel.AddressedEnvelope) r13
            java.lang.Object r0 = r13.content()
            io.netty.buffer.ByteBuf r0 = (io.netty.buffer.ByteBuf) r0
            java.net.SocketAddress r13 = r13.recipient()
            java.net.InetSocketAddress r13 = (java.net.InetSocketAddress) r13
            goto L_0x0017
        L_0x0013:
            r0 = r13
            io.netty.buffer.ByteBuf r0 = (io.netty.buffer.ByteBuf) r0
            r13 = 0
        L_0x0017:
            int r1 = r0.readableBytes()
            r2 = 1
            if (r1 != 0) goto L_0x001f
            return r2
        L_0x001f:
            boolean r1 = r0.hasMemoryAddress()
            r3 = 0
            if (r1 == 0) goto L_0x0053
            long r5 = r0.memoryAddress()
            if (r13 != 0) goto L_0x003c
            io.netty.channel.kqueue.BsdSocket r13 = r12.socket
            int r1 = r0.readerIndex()
            int r0 = r0.writerIndex()
            int r13 = r13.writeAddress(r5, r1, r0)
            goto L_0x00c5
        L_0x003c:
            io.netty.channel.kqueue.BsdSocket r4 = r12.socket
            int r7 = r0.readerIndex()
            int r8 = r0.writerIndex()
            java.net.InetAddress r9 = r13.getAddress()
            int r10 = r13.getPort()
            int r13 = r4.sendToAddress(r5, r7, r8, r9, r10)
            goto L_0x00c5
        L_0x0053:
            int r1 = r0.nioBufferCount()
            if (r1 <= r2) goto L_0x0092
            io.netty.channel.EventLoop r1 = r12.eventLoop()
            io.netty.channel.kqueue.KQueueEventLoop r1 = (io.netty.channel.kqueue.KQueueEventLoop) r1
            io.netty.channel.unix.IovArray r1 = r1.cleanArray()
            int r4 = r0.readerIndex()
            int r5 = r0.readableBytes()
            r1.add((io.netty.buffer.ByteBuf) r0, (int) r4, (int) r5)
            int r9 = r1.count()
            if (r13 != 0) goto L_0x007f
            io.netty.channel.kqueue.BsdSocket r13 = r12.socket
            long r0 = r1.memoryAddress(r3)
            long r0 = r13.writevAddresses(r0, r9)
            goto L_0x00c6
        L_0x007f:
            io.netty.channel.kqueue.BsdSocket r6 = r12.socket
            long r7 = r1.memoryAddress(r3)
            java.net.InetAddress r10 = r13.getAddress()
            int r11 = r13.getPort()
            int r13 = r6.sendToAddresses(r7, r9, r10, r11)
            goto L_0x00c5
        L_0x0092:
            int r1 = r0.readerIndex()
            int r4 = r0.readableBytes()
            java.nio.ByteBuffer r6 = r0.internalNioBuffer(r1, r4)
            if (r13 != 0) goto L_0x00af
            io.netty.channel.kqueue.BsdSocket r13 = r12.socket
            int r0 = r6.position()
            int r1 = r6.limit()
            int r13 = r13.write(r6, r0, r1)
            goto L_0x00c5
        L_0x00af:
            io.netty.channel.kqueue.BsdSocket r5 = r12.socket
            int r7 = r6.position()
            int r8 = r6.limit()
            java.net.InetAddress r9 = r13.getAddress()
            int r10 = r13.getPort()
            int r13 = r5.sendTo(r6, r7, r8, r9, r10)
        L_0x00c5:
            long r0 = (long) r13
        L_0x00c6:
            r4 = 0
            int r13 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r13 <= 0) goto L_0x00cd
            goto L_0x00ce
        L_0x00cd:
            r2 = 0
        L_0x00ce:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.channel.kqueue.KQueueDatagramChannel.doWriteMessage(java.lang.Object):boolean");
    }

    private static void checkUnresolved(AddressedEnvelope<?, ?> addressedEnvelope) {
        if ((addressedEnvelope.recipient() instanceof InetSocketAddress) && ((InetSocketAddress) addressedEnvelope.recipient()).isUnresolved()) {
            throw new UnresolvedAddressException();
        }
    }

    /* access modifiers changed from: protected */
    public Object filterOutboundMessage(Object obj) {
        if (obj instanceof DatagramPacket) {
            DatagramPacket datagramPacket = (DatagramPacket) obj;
            checkUnresolved(datagramPacket);
            ByteBuf byteBuf = (ByteBuf) datagramPacket.content();
            return UnixChannelUtil.isBufferCopyNeededForWrite(byteBuf) ? new DatagramPacket(newDirectBuffer(datagramPacket, byteBuf), (InetSocketAddress) datagramPacket.recipient()) : obj;
        } else if (obj instanceof ByteBuf) {
            ByteBuf byteBuf2 = (ByteBuf) obj;
            return UnixChannelUtil.isBufferCopyNeededForWrite(byteBuf2) ? newDirectBuffer(byteBuf2) : byteBuf2;
        } else {
            if (obj instanceof AddressedEnvelope) {
                AddressedEnvelope addressedEnvelope = (AddressedEnvelope) obj;
                checkUnresolved(addressedEnvelope);
                if ((addressedEnvelope.content() instanceof ByteBuf) && (addressedEnvelope.recipient() == null || (addressedEnvelope.recipient() instanceof InetSocketAddress))) {
                    ByteBuf byteBuf3 = (ByteBuf) addressedEnvelope.content();
                    return UnixChannelUtil.isBufferCopyNeededForWrite(byteBuf3) ? new DefaultAddressedEnvelope(newDirectBuffer(addressedEnvelope, byteBuf3), (InetSocketAddress) addressedEnvelope.recipient()) : addressedEnvelope;
                }
            }
            throw new UnsupportedOperationException("unsupported message type: " + StringUtil.simpleClassName(obj) + EXPECTED_TYPES);
        }
    }

    public KQueueDatagramChannelConfig config() {
        return this.config;
    }

    /* access modifiers changed from: protected */
    public void doDisconnect() throws Exception {
        this.socket.disconnect();
        this.active = false;
        this.connected = false;
        resetCachedAddresses();
    }

    /* access modifiers changed from: protected */
    public boolean doConnect(SocketAddress socketAddress, SocketAddress socketAddress2) throws Exception {
        if (!super.doConnect(socketAddress, socketAddress2)) {
            return false;
        }
        this.connected = true;
        return true;
    }

    /* access modifiers changed from: protected */
    public void doClose() throws Exception {
        super.doClose();
        this.connected = false;
    }

    final class KQueueDatagramChannelUnsafe extends AbstractKQueueChannel.AbstractKQueueUnsafe {
        static final /* synthetic */ boolean $assertionsDisabled = false;

        static {
            Class<KQueueDatagramChannel> cls = KQueueDatagramChannel.class;
        }

        KQueueDatagramChannelUnsafe() {
            super();
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Removed duplicated region for block: B:43:0x00f4 A[SYNTHETIC, Splitter:B:43:0x00f4] */
        /* JADX WARNING: Removed duplicated region for block: B:49:0x0102 A[Catch:{ all -> 0x00f8 }] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void readReady(io.netty.channel.kqueue.KQueueRecvByteAllocatorHandle r12) {
            /*
                r11 = this;
                io.netty.channel.kqueue.KQueueDatagramChannel r0 = io.netty.channel.kqueue.KQueueDatagramChannel.this
                io.netty.channel.kqueue.KQueueDatagramChannelConfig r0 = r0.config()
                io.netty.channel.kqueue.KQueueDatagramChannel r1 = io.netty.channel.kqueue.KQueueDatagramChannel.this
                boolean r1 = r1.shouldBreakReadReady(r0)
                if (r1 == 0) goto L_0x0012
                r11.clearReadFilter0()
                return
            L_0x0012:
                io.netty.channel.kqueue.KQueueDatagramChannel r1 = io.netty.channel.kqueue.KQueueDatagramChannel.this
                io.netty.channel.ChannelPipeline r1 = r1.pipeline()
                io.netty.buffer.ByteBufAllocator r2 = r0.getAllocator()
                r12.reset(r0)
                r11.readReadyBefore()
                r3 = 0
                io.netty.channel.kqueue.KQueueDatagramChannel r4 = io.netty.channel.kqueue.KQueueDatagramChannel.this     // Catch:{ all -> 0x00ef }
                boolean r4 = r4.isConnected()     // Catch:{ all -> 0x00ef }
            L_0x0029:
                io.netty.buffer.ByteBuf r5 = r12.allocate(r2)     // Catch:{ all -> 0x00ef }
                int r6 = r5.writableBytes()     // Catch:{ all -> 0x00ed }
                r12.attemptedBytesRead(r6)     // Catch:{ all -> 0x00ed }
                if (r4 == 0) goto L_0x0074
                io.netty.channel.kqueue.KQueueDatagramChannel r6 = io.netty.channel.kqueue.KQueueDatagramChannel.this     // Catch:{ NativeIoException -> 0x005d }
                int r6 = r6.doReadBytes(r5)     // Catch:{ NativeIoException -> 0x005d }
                r12.lastBytesRead(r6)     // Catch:{ NativeIoException -> 0x005d }
                int r6 = r12.lastBytesRead()     // Catch:{ all -> 0x00ed }
                if (r6 > 0) goto L_0x004a
                r5.release()     // Catch:{ all -> 0x00ed }
                goto L_0x00fa
            L_0x004a:
                io.netty.channel.socket.DatagramPacket r6 = new io.netty.channel.socket.DatagramPacket     // Catch:{ all -> 0x00ed }
                java.net.SocketAddress r7 = r11.localAddress()     // Catch:{ all -> 0x00ed }
                java.net.InetSocketAddress r7 = (java.net.InetSocketAddress) r7     // Catch:{ all -> 0x00ed }
                java.net.SocketAddress r8 = r11.remoteAddress()     // Catch:{ all -> 0x00ed }
                java.net.InetSocketAddress r8 = (java.net.InetSocketAddress) r8     // Catch:{ all -> 0x00ed }
                r6.<init>(r5, r7, r8)     // Catch:{ all -> 0x00ed }
                goto L_0x00da
            L_0x005d:
                r2 = move-exception
                int r3 = r2.expectedErr()     // Catch:{ all -> 0x00ed }
                int r4 = io.netty.channel.unix.Errors.ERROR_ECONNREFUSED_NEGATIVE     // Catch:{ all -> 0x00ed }
                if (r3 != r4) goto L_0x0073
                java.net.PortUnreachableException r3 = new java.net.PortUnreachableException     // Catch:{ all -> 0x00ed }
                java.lang.String r4 = r2.getMessage()     // Catch:{ all -> 0x00ed }
                r3.<init>(r4)     // Catch:{ all -> 0x00ed }
                r3.initCause(r2)     // Catch:{ all -> 0x00ed }
                throw r3     // Catch:{ all -> 0x00ed }
            L_0x0073:
                throw r2     // Catch:{ all -> 0x00ed }
            L_0x0074:
                boolean r6 = r5.hasMemoryAddress()     // Catch:{ all -> 0x00ed }
                if (r6 == 0) goto L_0x008f
                io.netty.channel.kqueue.KQueueDatagramChannel r6 = io.netty.channel.kqueue.KQueueDatagramChannel.this     // Catch:{ all -> 0x00ed }
                io.netty.channel.kqueue.BsdSocket r6 = r6.socket     // Catch:{ all -> 0x00ed }
                long r7 = r5.memoryAddress()     // Catch:{ all -> 0x00ed }
                int r9 = r5.writerIndex()     // Catch:{ all -> 0x00ed }
                int r10 = r5.capacity()     // Catch:{ all -> 0x00ed }
                io.netty.channel.unix.DatagramSocketAddress r6 = r6.recvFromAddress(r7, r9, r10)     // Catch:{ all -> 0x00ed }
                goto L_0x00ab
            L_0x008f:
                int r6 = r5.writerIndex()     // Catch:{ all -> 0x00ed }
                int r7 = r5.writableBytes()     // Catch:{ all -> 0x00ed }
                java.nio.ByteBuffer r6 = r5.internalNioBuffer(r6, r7)     // Catch:{ all -> 0x00ed }
                io.netty.channel.kqueue.KQueueDatagramChannel r7 = io.netty.channel.kqueue.KQueueDatagramChannel.this     // Catch:{ all -> 0x00ed }
                io.netty.channel.kqueue.BsdSocket r7 = r7.socket     // Catch:{ all -> 0x00ed }
                int r8 = r6.position()     // Catch:{ all -> 0x00ed }
                int r9 = r6.limit()     // Catch:{ all -> 0x00ed }
                io.netty.channel.unix.DatagramSocketAddress r6 = r7.recvFrom(r6, r8, r9)     // Catch:{ all -> 0x00ed }
            L_0x00ab:
                if (r6 != 0) goto L_0x00b5
                r2 = -1
                r12.lastBytesRead(r2)     // Catch:{ all -> 0x00ed }
                r5.release()     // Catch:{ all -> 0x00ed }
                goto L_0x00fa
            L_0x00b5:
                io.netty.channel.unix.DatagramSocketAddress r7 = r6.localAddress()     // Catch:{ all -> 0x00ed }
                if (r7 != 0) goto L_0x00c1
                java.net.SocketAddress r7 = r11.localAddress()     // Catch:{ all -> 0x00ed }
                java.net.InetSocketAddress r7 = (java.net.InetSocketAddress) r7     // Catch:{ all -> 0x00ed }
            L_0x00c1:
                int r8 = r6.receivedAmount()     // Catch:{ all -> 0x00ed }
                r12.lastBytesRead(r8)     // Catch:{ all -> 0x00ed }
                int r8 = r5.writerIndex()     // Catch:{ all -> 0x00ed }
                int r9 = r12.lastBytesRead()     // Catch:{ all -> 0x00ed }
                int r8 = r8 + r9
                r5.writerIndex(r8)     // Catch:{ all -> 0x00ed }
                io.netty.channel.socket.DatagramPacket r8 = new io.netty.channel.socket.DatagramPacket     // Catch:{ all -> 0x00ed }
                r8.<init>(r5, r7, r6)     // Catch:{ all -> 0x00ed }
                r6 = r8
            L_0x00da:
                r7 = 1
                r12.incMessagesRead(r7)     // Catch:{ all -> 0x00ed }
                r7 = 0
                r11.readPending = r7     // Catch:{ all -> 0x00ed }
                r1.fireChannelRead(r6)     // Catch:{ all -> 0x00ed }
                io.netty.util.UncheckedBooleanSupplier r5 = io.netty.util.UncheckedBooleanSupplier.TRUE_SUPPLIER     // Catch:{ all -> 0x00ef }
                boolean r5 = r12.continueReading(r5)     // Catch:{ all -> 0x00ef }
                if (r5 != 0) goto L_0x0029
                goto L_0x00fa
            L_0x00ed:
                r2 = move-exception
                goto L_0x00f1
            L_0x00ef:
                r2 = move-exception
                r5 = r3
            L_0x00f1:
                r3 = r2
                if (r5 == 0) goto L_0x00fa
                r5.release()     // Catch:{ all -> 0x00f8 }
                goto L_0x00fa
            L_0x00f8:
                r12 = move-exception
                goto L_0x0109
            L_0x00fa:
                r12.readComplete()     // Catch:{ all -> 0x00f8 }
                r1.fireChannelReadComplete()     // Catch:{ all -> 0x00f8 }
                if (r3 == 0) goto L_0x0105
                r1.fireExceptionCaught(r3)     // Catch:{ all -> 0x00f8 }
            L_0x0105:
                r11.readReadyFinally(r0)
                return
            L_0x0109:
                r11.readReadyFinally(r0)
                goto L_0x010e
            L_0x010d:
                throw r12
            L_0x010e:
                goto L_0x010d
            */
            throw new UnsupportedOperationException("Method not decompiled: io.netty.channel.kqueue.KQueueDatagramChannel.KQueueDatagramChannelUnsafe.readReady(io.netty.channel.kqueue.KQueueRecvByteAllocatorHandle):void");
        }
    }
}
