package io.netty.channel.epoll;

import io.netty.buffer.ByteBuf;
import io.netty.channel.AddressedEnvelope;
import io.netty.channel.Channel;
import io.netty.channel.ChannelMetadata;
import io.netty.channel.ChannelOutboundBuffer;
import io.netty.channel.DefaultAddressedEnvelope;
import io.netty.channel.epoll.AbstractEpollChannel;
import io.netty.channel.unix.DomainDatagramChannel;
import io.netty.channel.unix.DomainDatagramPacket;
import io.netty.channel.unix.DomainSocketAddress;
import io.netty.channel.unix.PeerCredentials;
import io.netty.channel.unix.UnixChannelUtil;
import io.netty.util.internal.StringUtil;
import java.io.IOException;
import java.net.SocketAddress;
import kotlin.text.Typography;

public final class EpollDomainDatagramChannel extends AbstractEpollChannel implements DomainDatagramChannel {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String EXPECTED_TYPES = (" (expected: " + StringUtil.simpleClassName((Class<?>) DomainDatagramPacket.class) + ", " + StringUtil.simpleClassName((Class<?>) AddressedEnvelope.class) + Typography.less + StringUtil.simpleClassName((Class<?>) ByteBuf.class) + ", " + StringUtil.simpleClassName((Class<?>) DomainSocketAddress.class) + ">, " + StringUtil.simpleClassName((Class<?>) ByteBuf.class) + ')');
    private static final ChannelMetadata METADATA = new ChannelMetadata(true, 16);
    private final EpollDomainDatagramChannelConfig config;
    private volatile boolean connected;
    private volatile DomainSocketAddress local;
    private volatile DomainSocketAddress remote;

    public /* bridge */ /* synthetic */ boolean isOpen() {
        return super.isOpen();
    }

    public EpollDomainDatagramChannel() {
        this(LinuxSocket.newSocketDomainDgram(), false);
    }

    public EpollDomainDatagramChannel(int i) {
        this(new LinuxSocket(i), true);
    }

    private EpollDomainDatagramChannel(LinuxSocket linuxSocket, boolean z) {
        super((Channel) null, linuxSocket, z);
        this.config = new EpollDomainDatagramChannelConfig(this);
    }

    public EpollDomainDatagramChannelConfig config() {
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
    public void doWrite(ChannelOutboundBuffer channelOutboundBuffer) throws Exception {
        int maxMessagesPerWrite = maxMessagesPerWrite();
        loop0:
        while (maxMessagesPerWrite > 0) {
            Object current = channelOutboundBuffer.current();
            if (current == null) {
                break;
            }
            try {
                int writeSpinCount = config().getWriteSpinCount();
                while (writeSpinCount > 0) {
                    if (doWriteMessage(current)) {
                        channelOutboundBuffer.remove();
                        maxMessagesPerWrite--;
                    } else {
                        writeSpinCount--;
                    }
                }
                break loop0;
            } catch (IOException e) {
                maxMessagesPerWrite--;
                channelOutboundBuffer.remove(e);
            }
        }
        if (channelOutboundBuffer.isEmpty()) {
            clearFlag(Native.EPOLLOUT);
        } else {
            setFlag(Native.EPOLLOUT);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x00d4 A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:27:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean doWriteMessage(java.lang.Object r11) throws java.lang.Exception {
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
            io.netty.channel.epoll.LinuxSocket r11 = r10.socket
            int r1 = r0.readerIndex()
            int r0 = r0.writerIndex()
            int r11 = r11.sendAddress(r5, r1, r0)
            goto L_0x00cc
        L_0x003c:
            io.netty.channel.epoll.LinuxSocket r4 = r10.socket
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
            io.netty.channel.epoll.EpollEventLoop r1 = (io.netty.channel.epoll.EpollEventLoop) r1
            io.netty.channel.unix.IovArray r1 = r1.cleanIovArray()
            int r4 = r0.readerIndex()
            int r5 = r0.readableBytes()
            r1.add((io.netty.buffer.ByteBuf) r0, (int) r4, (int) r5)
            int r0 = r1.count()
            if (r11 != 0) goto L_0x0082
            io.netty.channel.epoll.LinuxSocket r11 = r10.socket
            long r4 = r1.memoryAddress(r3)
            long r0 = r11.writevAddresses(r4, r0)
            goto L_0x00cd
        L_0x0082:
            io.netty.channel.epoll.LinuxSocket r4 = r10.socket
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
            io.netty.channel.epoll.LinuxSocket r11 = r10.socket
            int r1 = r0.position()
            int r4 = r0.limit()
            int r11 = r11.send(r0, r1, r4)
            goto L_0x00cc
        L_0x00b4:
            io.netty.channel.epoll.LinuxSocket r1 = r10.socket
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
        throw new UnsupportedOperationException("Method not decompiled: io.netty.channel.epoll.EpollDomainDatagramChannel.doWriteMessage(java.lang.Object):boolean");
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

    public ChannelMetadata metadata() {
        return METADATA;
    }

    /* access modifiers changed from: protected */
    public AbstractEpollChannel.AbstractEpollUnsafe newUnsafe() {
        return new EpollDomainDatagramChannelUnsafe();
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

    final class EpollDomainDatagramChannelUnsafe extends AbstractEpollChannel.AbstractEpollUnsafe {
        static final /* synthetic */ boolean $assertionsDisabled = false;

        static {
            Class<EpollDomainDatagramChannel> cls = EpollDomainDatagramChannel.class;
        }

        EpollDomainDatagramChannelUnsafe() {
            super();
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Removed duplicated region for block: B:34:0x00eb A[SYNTHETIC, Splitter:B:34:0x00eb] */
        /* JADX WARNING: Removed duplicated region for block: B:40:0x00f9 A[Catch:{ all -> 0x00ef }] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void epollInReady() {
            /*
                r12 = this;
                io.netty.channel.epoll.EpollDomainDatagramChannel r0 = io.netty.channel.epoll.EpollDomainDatagramChannel.this
                io.netty.channel.epoll.EpollDomainDatagramChannelConfig r0 = r0.config()
                io.netty.channel.epoll.EpollDomainDatagramChannel r1 = io.netty.channel.epoll.EpollDomainDatagramChannel.this
                boolean r1 = r1.shouldBreakEpollInReady(r0)
                if (r1 == 0) goto L_0x0012
                r12.clearEpollIn0()
                return
            L_0x0012:
                io.netty.channel.epoll.EpollRecvByteAllocatorHandle r1 = r12.recvBufAllocHandle()
                io.netty.channel.epoll.EpollDomainDatagramChannel r2 = io.netty.channel.epoll.EpollDomainDatagramChannel.this
                int r3 = io.netty.channel.epoll.Native.EPOLLET
                boolean r2 = r2.isFlagSet(r3)
                r1.edgeTriggered(r2)
                io.netty.channel.epoll.EpollDomainDatagramChannel r2 = io.netty.channel.epoll.EpollDomainDatagramChannel.this
                io.netty.channel.ChannelPipeline r2 = r2.pipeline()
                io.netty.buffer.ByteBufAllocator r3 = r0.getAllocator()
                r1.reset(r0)
                r12.epollInBefore()
                r4 = 0
                io.netty.channel.epoll.EpollDomainDatagramChannel r5 = io.netty.channel.epoll.EpollDomainDatagramChannel.this     // Catch:{ all -> 0x00e6 }
                boolean r5 = r5.isConnected()     // Catch:{ all -> 0x00e6 }
            L_0x0038:
                io.netty.buffer.ByteBuf r6 = r1.allocate(r3)     // Catch:{ all -> 0x00e6 }
                int r7 = r6.writableBytes()     // Catch:{ all -> 0x00e4 }
                r1.attemptedBytesRead(r7)     // Catch:{ all -> 0x00e4 }
                if (r5 == 0) goto L_0x006b
                io.netty.channel.epoll.EpollDomainDatagramChannel r7 = io.netty.channel.epoll.EpollDomainDatagramChannel.this     // Catch:{ all -> 0x00e4 }
                int r7 = r7.doReadBytes(r6)     // Catch:{ all -> 0x00e4 }
                r1.lastBytesRead(r7)     // Catch:{ all -> 0x00e4 }
                int r7 = r1.lastBytesRead()     // Catch:{ all -> 0x00e4 }
                if (r7 > 0) goto L_0x0059
                r6.release()     // Catch:{ all -> 0x00e4 }
                goto L_0x00f1
            L_0x0059:
                io.netty.channel.unix.DomainDatagramPacket r7 = new io.netty.channel.unix.DomainDatagramPacket     // Catch:{ all -> 0x00e4 }
                java.net.SocketAddress r8 = r12.localAddress()     // Catch:{ all -> 0x00e4 }
                io.netty.channel.unix.DomainSocketAddress r8 = (io.netty.channel.unix.DomainSocketAddress) r8     // Catch:{ all -> 0x00e4 }
                java.net.SocketAddress r9 = r12.remoteAddress()     // Catch:{ all -> 0x00e4 }
                io.netty.channel.unix.DomainSocketAddress r9 = (io.netty.channel.unix.DomainSocketAddress) r9     // Catch:{ all -> 0x00e4 }
                r7.<init>(r6, r8, r9)     // Catch:{ all -> 0x00e4 }
                goto L_0x00d1
            L_0x006b:
                boolean r7 = r6.hasMemoryAddress()     // Catch:{ all -> 0x00e4 }
                if (r7 == 0) goto L_0x0086
                io.netty.channel.epoll.EpollDomainDatagramChannel r7 = io.netty.channel.epoll.EpollDomainDatagramChannel.this     // Catch:{ all -> 0x00e4 }
                io.netty.channel.epoll.LinuxSocket r7 = r7.socket     // Catch:{ all -> 0x00e4 }
                long r8 = r6.memoryAddress()     // Catch:{ all -> 0x00e4 }
                int r10 = r6.writerIndex()     // Catch:{ all -> 0x00e4 }
                int r11 = r6.capacity()     // Catch:{ all -> 0x00e4 }
                io.netty.channel.unix.DomainDatagramSocketAddress r7 = r7.recvFromAddressDomainSocket(r8, r10, r11)     // Catch:{ all -> 0x00e4 }
                goto L_0x00a2
            L_0x0086:
                int r7 = r6.writerIndex()     // Catch:{ all -> 0x00e4 }
                int r8 = r6.writableBytes()     // Catch:{ all -> 0x00e4 }
                java.nio.ByteBuffer r7 = r6.internalNioBuffer(r7, r8)     // Catch:{ all -> 0x00e4 }
                io.netty.channel.epoll.EpollDomainDatagramChannel r8 = io.netty.channel.epoll.EpollDomainDatagramChannel.this     // Catch:{ all -> 0x00e4 }
                io.netty.channel.epoll.LinuxSocket r8 = r8.socket     // Catch:{ all -> 0x00e4 }
                int r9 = r7.position()     // Catch:{ all -> 0x00e4 }
                int r10 = r7.limit()     // Catch:{ all -> 0x00e4 }
                io.netty.channel.unix.DomainDatagramSocketAddress r7 = r8.recvFromDomainSocket(r7, r9, r10)     // Catch:{ all -> 0x00e4 }
            L_0x00a2:
                if (r7 != 0) goto L_0x00ac
                r3 = -1
                r1.lastBytesRead(r3)     // Catch:{ all -> 0x00e4 }
                r6.release()     // Catch:{ all -> 0x00e4 }
                goto L_0x00f1
            L_0x00ac:
                io.netty.channel.unix.DomainDatagramSocketAddress r8 = r7.localAddress()     // Catch:{ all -> 0x00e4 }
                if (r8 != 0) goto L_0x00b8
                java.net.SocketAddress r8 = r12.localAddress()     // Catch:{ all -> 0x00e4 }
                io.netty.channel.unix.DomainSocketAddress r8 = (io.netty.channel.unix.DomainSocketAddress) r8     // Catch:{ all -> 0x00e4 }
            L_0x00b8:
                int r9 = r7.receivedAmount()     // Catch:{ all -> 0x00e4 }
                r1.lastBytesRead(r9)     // Catch:{ all -> 0x00e4 }
                int r9 = r6.writerIndex()     // Catch:{ all -> 0x00e4 }
                int r10 = r1.lastBytesRead()     // Catch:{ all -> 0x00e4 }
                int r9 = r9 + r10
                r6.writerIndex(r9)     // Catch:{ all -> 0x00e4 }
                io.netty.channel.unix.DomainDatagramPacket r9 = new io.netty.channel.unix.DomainDatagramPacket     // Catch:{ all -> 0x00e4 }
                r9.<init>(r6, r8, r7)     // Catch:{ all -> 0x00e4 }
                r7 = r9
            L_0x00d1:
                r8 = 1
                r1.incMessagesRead(r8)     // Catch:{ all -> 0x00e4 }
                r8 = 0
                r12.readPending = r8     // Catch:{ all -> 0x00e4 }
                r2.fireChannelRead(r7)     // Catch:{ all -> 0x00e4 }
                io.netty.util.UncheckedBooleanSupplier r6 = io.netty.util.UncheckedBooleanSupplier.TRUE_SUPPLIER     // Catch:{ all -> 0x00e6 }
                boolean r6 = r1.continueReading(r6)     // Catch:{ all -> 0x00e6 }
                if (r6 != 0) goto L_0x0038
                goto L_0x00f1
            L_0x00e4:
                r3 = move-exception
                goto L_0x00e8
            L_0x00e6:
                r3 = move-exception
                r6 = r4
            L_0x00e8:
                r4 = r3
                if (r6 == 0) goto L_0x00f1
                r6.release()     // Catch:{ all -> 0x00ef }
                goto L_0x00f1
            L_0x00ef:
                r1 = move-exception
                goto L_0x0100
            L_0x00f1:
                r1.readComplete()     // Catch:{ all -> 0x00ef }
                r2.fireChannelReadComplete()     // Catch:{ all -> 0x00ef }
                if (r4 == 0) goto L_0x00fc
                r2.fireExceptionCaught(r4)     // Catch:{ all -> 0x00ef }
            L_0x00fc:
                r12.epollInFinally(r0)
                return
            L_0x0100:
                r12.epollInFinally(r0)
                goto L_0x0105
            L_0x0104:
                throw r1
            L_0x0105:
                goto L_0x0104
            */
            throw new UnsupportedOperationException("Method not decompiled: io.netty.channel.epoll.EpollDomainDatagramChannel.EpollDomainDatagramChannelUnsafe.epollInReady():void");
        }
    }
}
