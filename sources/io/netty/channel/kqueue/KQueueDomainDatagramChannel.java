package io.netty.channel.kqueue;

import io.netty.buffer.ByteBuf;
import io.netty.channel.AddressedEnvelope;
import io.netty.channel.Channel;
import io.netty.channel.ChannelMetadata;
import io.netty.channel.DefaultAddressedEnvelope;
import io.netty.channel.kqueue.AbstractKQueueChannel;
import io.netty.channel.unix.DomainDatagramChannel;
import io.netty.channel.unix.DomainDatagramPacket;
import io.netty.channel.unix.DomainSocketAddress;
import io.netty.channel.unix.PeerCredentials;
import io.netty.channel.unix.UnixChannelUtil;
import io.netty.util.internal.StringUtil;
import java.io.IOException;
import java.net.SocketAddress;
import kotlin.text.Typography;

public final class KQueueDomainDatagramChannel extends AbstractKQueueDatagramChannel implements DomainDatagramChannel {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String EXPECTED_TYPES = (" (expected: " + StringUtil.simpleClassName((Class<?>) DomainDatagramPacket.class) + ", " + StringUtil.simpleClassName((Class<?>) AddressedEnvelope.class) + Typography.less + StringUtil.simpleClassName((Class<?>) ByteBuf.class) + ", " + StringUtil.simpleClassName((Class<?>) DomainSocketAddress.class) + ">, " + StringUtil.simpleClassName((Class<?>) ByteBuf.class) + ')');
    private final KQueueDomainDatagramChannelConfig config;
    private volatile boolean connected;
    private volatile DomainSocketAddress local;
    private volatile DomainSocketAddress remote;

    public /* bridge */ /* synthetic */ boolean isOpen() {
        return super.isOpen();
    }

    public /* bridge */ /* synthetic */ ChannelMetadata metadata() {
        return super.metadata();
    }

    public KQueueDomainDatagramChannel() {
        this(BsdSocket.newSocketDomainDgram(), false);
    }

    public KQueueDomainDatagramChannel(int i) {
        this(new BsdSocket(i), true);
    }

    private KQueueDomainDatagramChannel(BsdSocket bsdSocket, boolean z) {
        super((Channel) null, bsdSocket, z);
        this.config = new KQueueDomainDatagramChannelConfig(this);
    }

    public KQueueDomainDatagramChannelConfig config() {
        return this.config;
    }

    /* access modifiers changed from: protected */
    public void doBind(SocketAddress socketAddress) throws Exception {
        super.doBind(socketAddress);
        this.local = (DomainSocketAddress) socketAddress;
        this.active = true;
    }

    /* access modifiers changed from: protected */
    public void doClose() throws Exception {
        super.doClose();
        this.active = false;
        this.connected = false;
        this.local = null;
        this.remote = null;
    }

    /* access modifiers changed from: protected */
    public boolean doConnect(SocketAddress socketAddress, SocketAddress socketAddress2) throws Exception {
        if (!super.doConnect(socketAddress, socketAddress2)) {
            return false;
        }
        if (socketAddress2 != null) {
            this.local = (DomainSocketAddress) socketAddress2;
        }
        this.remote = (DomainSocketAddress) socketAddress;
        this.connected = true;
        return true;
    }

    /* access modifiers changed from: protected */
    public void doDisconnect() throws Exception {
        doClose();
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00d4 A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:27:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean doWriteMessage(java.lang.Object r11) throws java.lang.Exception {
        /*
            r10 = this;
            boolean r0 = r11 instanceof io.netty.channel.AddressedEnvelope
            if (r0 == 0) goto L_0x0013
            io.netty.channel.AddressedEnvelope r11 = (io.netty.channel.AddressedEnvelope) r11
            java.lang.Object r0 = r11.content()
            io.netty.buffer.ByteBuf r0 = (io.netty.buffer.ByteBuf) r0
            java.net.SocketAddress r11 = r11.recipient()
            io.netty.channel.unix.DomainSocketAddress r11 = (io.netty.channel.unix.DomainSocketAddress) r11
            goto L_0x0017
        L_0x0013:
            r0 = r11
            io.netty.buffer.ByteBuf r0 = (io.netty.buffer.ByteBuf) r0
            r11 = 0
        L_0x0017:
            int r1 = r0.readableBytes()
            r2 = 1
            if (r1 != 0) goto L_0x001f
            return r2
        L_0x001f:
            boolean r1 = r0.hasMemoryAddress()
            r3 = 0
            if (r1 == 0) goto L_0x0056
            long r5 = r0.memoryAddress()
            if (r11 != 0) goto L_0x003c
            io.netty.channel.kqueue.BsdSocket r11 = r10.socket
            int r1 = r0.readerIndex()
            int r0 = r0.writerIndex()
            int r11 = r11.writeAddress(r5, r1, r0)
            goto L_0x00cc
        L_0x003c:
            io.netty.channel.kqueue.BsdSocket r4 = r10.socket
            int r7 = r0.readerIndex()
            int r8 = r0.writerIndex()
            java.lang.String r11 = r11.path()
            java.nio.charset.Charset r0 = io.netty.util.CharsetUtil.UTF_8
            byte[] r9 = r11.getBytes(r0)
            int r11 = r4.sendToAddressDomainSocket(r5, r7, r8, r9)
            goto L_0x00cc
        L_0x0056:
            int r1 = r0.nioBufferCount()
            if (r1 <= r2) goto L_0x0097
            io.netty.channel.EventLoop r1 = r10.eventLoop()
            io.netty.channel.kqueue.KQueueEventLoop r1 = (io.netty.channel.kqueue.KQueueEventLoop) r1
            io.netty.channel.unix.IovArray r1 = r1.cleanArray()
            int r4 = r0.readerIndex()
            int r5 = r0.readableBytes()
            r1.add((io.netty.buffer.ByteBuf) r0, (int) r4, (int) r5)
            int r0 = r1.count()
            if (r11 != 0) goto L_0x0082
            io.netty.channel.kqueue.BsdSocket r11 = r10.socket
            long r4 = r1.memoryAddress(r3)
            long r0 = r11.writevAddresses(r4, r0)
            goto L_0x00cd
        L_0x0082:
            io.netty.channel.kqueue.BsdSocket r4 = r10.socket
            long r5 = r1.memoryAddress(r3)
            java.lang.String r11 = r11.path()
            java.nio.charset.Charset r1 = io.netty.util.CharsetUtil.UTF_8
            byte[] r11 = r11.getBytes(r1)
            int r11 = r4.sendToAddressesDomainSocket(r5, r0, r11)
            goto L_0x00cc
        L_0x0097:
            int r1 = r0.readerIndex()
            int r4 = r0.readableBytes()
            java.nio.ByteBuffer r0 = r0.internalNioBuffer(r1, r4)
            if (r11 != 0) goto L_0x00b4
            io.netty.channel.kqueue.BsdSocket r11 = r10.socket
            int r1 = r0.position()
            int r4 = r0.limit()
            int r11 = r11.write(r0, r1, r4)
            goto L_0x00cc
        L_0x00b4:
            io.netty.channel.kqueue.BsdSocket r1 = r10.socket
            int r4 = r0.position()
            int r5 = r0.limit()
            java.lang.String r11 = r11.path()
            java.nio.charset.Charset r6 = io.netty.util.CharsetUtil.UTF_8
            byte[] r11 = r11.getBytes(r6)
            int r11 = r1.sendToDomainSocket(r0, r4, r5, r11)
        L_0x00cc:
            long r0 = (long) r11
        L_0x00cd:
            r4 = 0
            int r11 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r11 <= 0) goto L_0x00d4
            goto L_0x00d5
        L_0x00d4:
            r2 = 0
        L_0x00d5:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.channel.kqueue.KQueueDomainDatagramChannel.doWriteMessage(java.lang.Object):boolean");
    }

    /* access modifiers changed from: protected */
    public Object filterOutboundMessage(Object obj) {
        if (obj instanceof DomainDatagramPacket) {
            DomainDatagramPacket domainDatagramPacket = (DomainDatagramPacket) obj;
            ByteBuf byteBuf = (ByteBuf) domainDatagramPacket.content();
            return UnixChannelUtil.isBufferCopyNeededForWrite(byteBuf) ? new DomainDatagramPacket(newDirectBuffer(domainDatagramPacket, byteBuf), (DomainSocketAddress) domainDatagramPacket.recipient()) : obj;
        } else if (obj instanceof ByteBuf) {
            ByteBuf byteBuf2 = (ByteBuf) obj;
            return UnixChannelUtil.isBufferCopyNeededForWrite(byteBuf2) ? newDirectBuffer(byteBuf2) : byteBuf2;
        } else {
            if (obj instanceof AddressedEnvelope) {
                AddressedEnvelope addressedEnvelope = (AddressedEnvelope) obj;
                if ((addressedEnvelope.content() instanceof ByteBuf) && (addressedEnvelope.recipient() == null || (addressedEnvelope.recipient() instanceof DomainSocketAddress))) {
                    ByteBuf byteBuf3 = (ByteBuf) addressedEnvelope.content();
                    return UnixChannelUtil.isBufferCopyNeededForWrite(byteBuf3) ? new DefaultAddressedEnvelope(newDirectBuffer(addressedEnvelope, byteBuf3), (DomainSocketAddress) addressedEnvelope.recipient()) : addressedEnvelope;
                }
            }
            throw new UnsupportedOperationException("unsupported message type: " + StringUtil.simpleClassName(obj) + EXPECTED_TYPES);
        }
    }

    public boolean isActive() {
        return this.socket.isOpen() && ((this.config.getActiveOnOpen() && isRegistered()) || this.active);
    }

    public boolean isConnected() {
        return this.connected;
    }

    public DomainSocketAddress localAddress() {
        return (DomainSocketAddress) super.localAddress();
    }

    /* access modifiers changed from: protected */
    public DomainSocketAddress localAddress0() {
        return this.local;
    }

    /* access modifiers changed from: protected */
    public AbstractKQueueChannel.AbstractKQueueUnsafe newUnsafe() {
        return new KQueueDomainDatagramChannelUnsafe();
    }

    public PeerCredentials peerCredentials() throws IOException {
        return this.socket.getPeerCredentials();
    }

    public DomainSocketAddress remoteAddress() {
        return (DomainSocketAddress) super.remoteAddress();
    }

    /* access modifiers changed from: protected */
    public DomainSocketAddress remoteAddress0() {
        return this.remote;
    }

    final class KQueueDomainDatagramChannelUnsafe extends AbstractKQueueChannel.AbstractKQueueUnsafe {
        static final /* synthetic */ boolean $assertionsDisabled = false;

        static {
            Class<KQueueDomainDatagramChannel> cls = KQueueDomainDatagramChannel.class;
        }

        KQueueDomainDatagramChannelUnsafe() {
            super();
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Removed duplicated region for block: B:34:0x00dc A[SYNTHETIC, Splitter:B:34:0x00dc] */
        /* JADX WARNING: Removed duplicated region for block: B:40:0x00ea A[Catch:{ all -> 0x00e0 }] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void readReady(io.netty.channel.kqueue.KQueueRecvByteAllocatorHandle r12) {
            /*
                r11 = this;
                io.netty.channel.kqueue.KQueueDomainDatagramChannel r0 = io.netty.channel.kqueue.KQueueDomainDatagramChannel.this
                io.netty.channel.kqueue.KQueueDomainDatagramChannelConfig r0 = r0.config()
                io.netty.channel.kqueue.KQueueDomainDatagramChannel r1 = io.netty.channel.kqueue.KQueueDomainDatagramChannel.this
                boolean r1 = r1.shouldBreakReadReady(r0)
                if (r1 == 0) goto L_0x0012
                r11.clearReadFilter0()
                return
            L_0x0012:
                io.netty.channel.kqueue.KQueueDomainDatagramChannel r1 = io.netty.channel.kqueue.KQueueDomainDatagramChannel.this
                io.netty.channel.ChannelPipeline r1 = r1.pipeline()
                io.netty.buffer.ByteBufAllocator r2 = r0.getAllocator()
                r12.reset(r0)
                r11.readReadyBefore()
                r3 = 0
                io.netty.channel.kqueue.KQueueDomainDatagramChannel r4 = io.netty.channel.kqueue.KQueueDomainDatagramChannel.this     // Catch:{ all -> 0x00d7 }
                boolean r4 = r4.isConnected()     // Catch:{ all -> 0x00d7 }
            L_0x0029:
                io.netty.buffer.ByteBuf r5 = r12.allocate(r2)     // Catch:{ all -> 0x00d7 }
                int r6 = r5.writableBytes()     // Catch:{ all -> 0x00d5 }
                r12.attemptedBytesRead(r6)     // Catch:{ all -> 0x00d5 }
                if (r4 == 0) goto L_0x005c
                io.netty.channel.kqueue.KQueueDomainDatagramChannel r6 = io.netty.channel.kqueue.KQueueDomainDatagramChannel.this     // Catch:{ all -> 0x00d5 }
                int r6 = r6.doReadBytes(r5)     // Catch:{ all -> 0x00d5 }
                r12.lastBytesRead(r6)     // Catch:{ all -> 0x00d5 }
                int r6 = r12.lastBytesRead()     // Catch:{ all -> 0x00d5 }
                if (r6 > 0) goto L_0x004a
                r5.release()     // Catch:{ all -> 0x00d5 }
                goto L_0x00e2
            L_0x004a:
                io.netty.channel.unix.DomainDatagramPacket r6 = new io.netty.channel.unix.DomainDatagramPacket     // Catch:{ all -> 0x00d5 }
                java.net.SocketAddress r7 = r11.localAddress()     // Catch:{ all -> 0x00d5 }
                io.netty.channel.unix.DomainSocketAddress r7 = (io.netty.channel.unix.DomainSocketAddress) r7     // Catch:{ all -> 0x00d5 }
                java.net.SocketAddress r8 = r11.remoteAddress()     // Catch:{ all -> 0x00d5 }
                io.netty.channel.unix.DomainSocketAddress r8 = (io.netty.channel.unix.DomainSocketAddress) r8     // Catch:{ all -> 0x00d5 }
                r6.<init>(r5, r7, r8)     // Catch:{ all -> 0x00d5 }
                goto L_0x00c2
            L_0x005c:
                boolean r6 = r5.hasMemoryAddress()     // Catch:{ all -> 0x00d5 }
                if (r6 == 0) goto L_0x0077
                io.netty.channel.kqueue.KQueueDomainDatagramChannel r6 = io.netty.channel.kqueue.KQueueDomainDatagramChannel.this     // Catch:{ all -> 0x00d5 }
                io.netty.channel.kqueue.BsdSocket r6 = r6.socket     // Catch:{ all -> 0x00d5 }
                long r7 = r5.memoryAddress()     // Catch:{ all -> 0x00d5 }
                int r9 = r5.writerIndex()     // Catch:{ all -> 0x00d5 }
                int r10 = r5.capacity()     // Catch:{ all -> 0x00d5 }
                io.netty.channel.unix.DomainDatagramSocketAddress r6 = r6.recvFromAddressDomainSocket(r7, r9, r10)     // Catch:{ all -> 0x00d5 }
                goto L_0x0093
            L_0x0077:
                int r6 = r5.writerIndex()     // Catch:{ all -> 0x00d5 }
                int r7 = r5.writableBytes()     // Catch:{ all -> 0x00d5 }
                java.nio.ByteBuffer r6 = r5.internalNioBuffer(r6, r7)     // Catch:{ all -> 0x00d5 }
                io.netty.channel.kqueue.KQueueDomainDatagramChannel r7 = io.netty.channel.kqueue.KQueueDomainDatagramChannel.this     // Catch:{ all -> 0x00d5 }
                io.netty.channel.kqueue.BsdSocket r7 = r7.socket     // Catch:{ all -> 0x00d5 }
                int r8 = r6.position()     // Catch:{ all -> 0x00d5 }
                int r9 = r6.limit()     // Catch:{ all -> 0x00d5 }
                io.netty.channel.unix.DomainDatagramSocketAddress r6 = r7.recvFromDomainSocket(r6, r8, r9)     // Catch:{ all -> 0x00d5 }
            L_0x0093:
                if (r6 != 0) goto L_0x009d
                r2 = -1
                r12.lastBytesRead(r2)     // Catch:{ all -> 0x00d5 }
                r5.release()     // Catch:{ all -> 0x00d5 }
                goto L_0x00e2
            L_0x009d:
                io.netty.channel.unix.DomainDatagramSocketAddress r7 = r6.localAddress()     // Catch:{ all -> 0x00d5 }
                if (r7 != 0) goto L_0x00a9
                java.net.SocketAddress r7 = r11.localAddress()     // Catch:{ all -> 0x00d5 }
                io.netty.channel.unix.DomainSocketAddress r7 = (io.netty.channel.unix.DomainSocketAddress) r7     // Catch:{ all -> 0x00d5 }
            L_0x00a9:
                int r8 = r6.receivedAmount()     // Catch:{ all -> 0x00d5 }
                r12.lastBytesRead(r8)     // Catch:{ all -> 0x00d5 }
                int r8 = r5.writerIndex()     // Catch:{ all -> 0x00d5 }
                int r9 = r12.lastBytesRead()     // Catch:{ all -> 0x00d5 }
                int r8 = r8 + r9
                r5.writerIndex(r8)     // Catch:{ all -> 0x00d5 }
                io.netty.channel.unix.DomainDatagramPacket r8 = new io.netty.channel.unix.DomainDatagramPacket     // Catch:{ all -> 0x00d5 }
                r8.<init>(r5, r7, r6)     // Catch:{ all -> 0x00d5 }
                r6 = r8
            L_0x00c2:
                r7 = 1
                r12.incMessagesRead(r7)     // Catch:{ all -> 0x00d5 }
                r7 = 0
                r11.readPending = r7     // Catch:{ all -> 0x00d5 }
                r1.fireChannelRead(r6)     // Catch:{ all -> 0x00d5 }
                io.netty.util.UncheckedBooleanSupplier r5 = io.netty.util.UncheckedBooleanSupplier.TRUE_SUPPLIER     // Catch:{ all -> 0x00d7 }
                boolean r5 = r12.continueReading(r5)     // Catch:{ all -> 0x00d7 }
                if (r5 != 0) goto L_0x0029
                goto L_0x00e2
            L_0x00d5:
                r2 = move-exception
                goto L_0x00d9
            L_0x00d7:
                r2 = move-exception
                r5 = r3
            L_0x00d9:
                r3 = r2
                if (r5 == 0) goto L_0x00e2
                r5.release()     // Catch:{ all -> 0x00e0 }
                goto L_0x00e2
            L_0x00e0:
                r12 = move-exception
                goto L_0x00f1
            L_0x00e2:
                r12.readComplete()     // Catch:{ all -> 0x00e0 }
                r1.fireChannelReadComplete()     // Catch:{ all -> 0x00e0 }
                if (r3 == 0) goto L_0x00ed
                r1.fireExceptionCaught(r3)     // Catch:{ all -> 0x00e0 }
            L_0x00ed:
                r11.readReadyFinally(r0)
                return
            L_0x00f1:
                r11.readReadyFinally(r0)
                goto L_0x00f6
            L_0x00f5:
                throw r12
            L_0x00f6:
                goto L_0x00f5
            */
            throw new UnsupportedOperationException("Method not decompiled: io.netty.channel.kqueue.KQueueDomainDatagramChannel.KQueueDomainDatagramChannelUnsafe.readReady(io.netty.channel.kqueue.KQueueRecvByteAllocatorHandle):void");
        }
    }
}
