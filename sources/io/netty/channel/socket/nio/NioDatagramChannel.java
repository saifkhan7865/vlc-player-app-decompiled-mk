package io.netty.channel.socket.nio;

import io.ktor.util.NioPathKt$$ExternalSyntheticApiModelOutline0;
import io.netty.buffer.ByteBuf;
import io.netty.channel.AddressedEnvelope;
import io.netty.channel.Channel;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelMetadata;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelOutboundBuffer;
import io.netty.channel.ChannelPromise;
import io.netty.channel.DefaultAddressedEnvelope;
import io.netty.channel.RecvByteBufAllocator;
import io.netty.channel.nio.AbstractNioMessageChannel;
import io.netty.channel.socket.DatagramChannel;
import io.netty.channel.socket.DatagramChannelConfig;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.InternetProtocolFamily;
import io.netty.util.UncheckedBooleanSupplier;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.SocketUtils;
import io.netty.util.internal.StringUtil;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.MembershipKey;
import java.nio.channels.UnresolvedAddressException;
import java.nio.channels.spi.SelectorProvider;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.text.Typography;

public final class NioDatagramChannel extends AbstractNioMessageChannel implements DatagramChannel {
    private static final SelectorProvider DEFAULT_SELECTOR_PROVIDER = SelectorProvider.provider();
    private static final String EXPECTED_TYPES = (" (expected: " + StringUtil.simpleClassName((Class<?>) DatagramPacket.class) + ", " + StringUtil.simpleClassName((Class<?>) AddressedEnvelope.class) + Typography.less + StringUtil.simpleClassName((Class<?>) ByteBuf.class) + ", " + StringUtil.simpleClassName((Class<?>) SocketAddress.class) + ">, " + StringUtil.simpleClassName((Class<?>) ByteBuf.class) + ')');
    private static final ChannelMetadata METADATA = new ChannelMetadata(true, 16);
    private final DatagramChannelConfig config;
    private Map<InetAddress, List<MembershipKey>> memberships;

    /* access modifiers changed from: protected */
    public boolean continueOnWriteError() {
        return true;
    }

    private static java.nio.channels.DatagramChannel newSocket(SelectorProvider selectorProvider) {
        try {
            return selectorProvider.openDatagramChannel();
        } catch (IOException e) {
            throw new ChannelException("Failed to open a socket.", e);
        }
    }

    private static java.nio.channels.DatagramChannel newSocket(SelectorProvider selectorProvider, InternetProtocolFamily internetProtocolFamily) {
        if (internetProtocolFamily == null) {
            return newSocket(selectorProvider);
        }
        checkJavaVersion();
        try {
            return selectorProvider.openDatagramChannel(ProtocolFamilyConverter.convert(internetProtocolFamily));
        } catch (IOException e) {
            throw new ChannelException("Failed to open a socket.", e);
        }
    }

    private static void checkJavaVersion() {
        if (PlatformDependent.javaVersion() < 7) {
            throw new UnsupportedOperationException("Only supported on java 7+.");
        }
    }

    public NioDatagramChannel() {
        this(newSocket(DEFAULT_SELECTOR_PROVIDER));
    }

    public NioDatagramChannel(SelectorProvider selectorProvider) {
        this(newSocket(selectorProvider));
    }

    public NioDatagramChannel(InternetProtocolFamily internetProtocolFamily) {
        this(newSocket(DEFAULT_SELECTOR_PROVIDER, internetProtocolFamily));
    }

    public NioDatagramChannel(SelectorProvider selectorProvider, InternetProtocolFamily internetProtocolFamily) {
        this(newSocket(selectorProvider, internetProtocolFamily));
    }

    public NioDatagramChannel(java.nio.channels.DatagramChannel datagramChannel) {
        super((Channel) null, datagramChannel, 1);
        this.config = new NioDatagramChannelConfig(this, datagramChannel);
    }

    public ChannelMetadata metadata() {
        return METADATA;
    }

    public DatagramChannelConfig config() {
        return this.config;
    }

    public boolean isActive() {
        java.nio.channels.DatagramChannel javaChannel = javaChannel();
        return javaChannel.isOpen() && ((((Boolean) this.config.getOption(ChannelOption.DATAGRAM_CHANNEL_ACTIVE_ON_REGISTRATION)).booleanValue() && isRegistered()) || javaChannel.socket().isBound());
    }

    public boolean isConnected() {
        return javaChannel().isConnected();
    }

    /* access modifiers changed from: protected */
    public java.nio.channels.DatagramChannel javaChannel() {
        return (java.nio.channels.DatagramChannel) super.javaChannel();
    }

    /* access modifiers changed from: protected */
    public SocketAddress localAddress0() {
        return javaChannel().socket().getLocalSocketAddress();
    }

    /* access modifiers changed from: protected */
    public SocketAddress remoteAddress0() {
        return javaChannel().socket().getRemoteSocketAddress();
    }

    /* access modifiers changed from: protected */
    public void doBind(SocketAddress socketAddress) throws Exception {
        doBind0(socketAddress);
    }

    private void doBind0(SocketAddress socketAddress) throws Exception {
        if (PlatformDependent.javaVersion() >= 7) {
            SocketUtils.bind(javaChannel(), socketAddress);
        } else {
            javaChannel().socket().bind(socketAddress);
        }
    }

    /* access modifiers changed from: protected */
    public boolean doConnect(SocketAddress socketAddress, SocketAddress socketAddress2) throws Exception {
        if (socketAddress2 != null) {
            doBind0(socketAddress2);
        }
        try {
            javaChannel().connect(socketAddress);
            return true;
        } catch (Throwable th) {
            doClose();
            throw th;
        }
    }

    /* access modifiers changed from: protected */
    public void doFinishConnect() throws Exception {
        throw new Error();
    }

    /* access modifiers changed from: protected */
    public void doDisconnect() throws Exception {
        javaChannel().disconnect();
    }

    /* access modifiers changed from: protected */
    public void doClose() throws Exception {
        javaChannel().close();
    }

    /* access modifiers changed from: protected */
    public int doReadMessages(List<Object> list) throws Exception {
        java.nio.channels.DatagramChannel javaChannel = javaChannel();
        DatagramChannelConfig config2 = config();
        RecvByteBufAllocator.Handle recvBufAllocHandle = unsafe().recvBufAllocHandle();
        ByteBuf allocate = recvBufAllocHandle.allocate(config2.getAllocator());
        recvBufAllocHandle.attemptedBytesRead(allocate.writableBytes());
        try {
            ByteBuffer internalNioBuffer = allocate.internalNioBuffer(allocate.writerIndex(), allocate.writableBytes());
            int position = internalNioBuffer.position();
            InetSocketAddress inetSocketAddress = (InetSocketAddress) javaChannel.receive(internalNioBuffer);
            if (inetSocketAddress == null) {
                allocate.release();
                return 0;
            }
            recvBufAllocHandle.lastBytesRead(internalNioBuffer.position() - position);
            list.add(new DatagramPacket(allocate.writerIndex(allocate.writerIndex() + recvBufAllocHandle.lastBytesRead()), localAddress(), inetSocketAddress));
            return 1;
        } catch (Throwable th) {
            allocate.release();
            throw th;
        }
    }

    /* access modifiers changed from: protected */
    public boolean doWriteMessage(Object obj, ChannelOutboundBuffer channelOutboundBuffer) throws Exception {
        SocketAddress socketAddress;
        ByteBuf byteBuf;
        ByteBuffer byteBuffer;
        int i;
        if (obj instanceof AddressedEnvelope) {
            AddressedEnvelope addressedEnvelope = (AddressedEnvelope) obj;
            socketAddress = addressedEnvelope.recipient();
            byteBuf = (ByteBuf) addressedEnvelope.content();
        } else {
            byteBuf = (ByteBuf) obj;
            socketAddress = null;
        }
        int readableBytes = byteBuf.readableBytes();
        if (readableBytes == 0) {
            return true;
        }
        if (byteBuf.nioBufferCount() == 1) {
            byteBuffer = byteBuf.internalNioBuffer(byteBuf.readerIndex(), readableBytes);
        } else {
            byteBuffer = byteBuf.nioBuffer(byteBuf.readerIndex(), readableBytes);
        }
        if (socketAddress != null) {
            i = javaChannel().send(byteBuffer, socketAddress);
        } else {
            i = javaChannel().write(byteBuffer);
        }
        if (i > 0) {
            return true;
        }
        return false;
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
            if (isSingleDirectBuffer(byteBuf)) {
                return datagramPacket;
            }
            return new DatagramPacket(newDirectBuffer(datagramPacket, byteBuf), (InetSocketAddress) datagramPacket.recipient());
        } else if (obj instanceof ByteBuf) {
            ByteBuf byteBuf2 = (ByteBuf) obj;
            if (isSingleDirectBuffer(byteBuf2)) {
                return byteBuf2;
            }
            return newDirectBuffer(byteBuf2);
        } else {
            if (obj instanceof AddressedEnvelope) {
                AddressedEnvelope addressedEnvelope = (AddressedEnvelope) obj;
                checkUnresolved(addressedEnvelope);
                if (addressedEnvelope.content() instanceof ByteBuf) {
                    ByteBuf byteBuf3 = (ByteBuf) addressedEnvelope.content();
                    if (isSingleDirectBuffer(byteBuf3)) {
                        return addressedEnvelope;
                    }
                    return new DefaultAddressedEnvelope(newDirectBuffer(addressedEnvelope, byteBuf3), addressedEnvelope.recipient());
                }
            }
            throw new UnsupportedOperationException("unsupported message type: " + StringUtil.simpleClassName(obj) + EXPECTED_TYPES);
        }
    }

    private static boolean isSingleDirectBuffer(ByteBuf byteBuf) {
        return byteBuf.isDirect() && byteBuf.nioBufferCount() == 1;
    }

    public InetSocketAddress localAddress() {
        return (InetSocketAddress) super.localAddress();
    }

    public InetSocketAddress remoteAddress() {
        return (InetSocketAddress) super.remoteAddress();
    }

    public ChannelFuture joinGroup(InetAddress inetAddress) {
        return joinGroup(inetAddress, newPromise());
    }

    public ChannelFuture joinGroup(InetAddress inetAddress, ChannelPromise channelPromise) {
        try {
            NetworkInterface networkInterface = this.config.getNetworkInterface();
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
        MembershipKey membershipKey;
        List list;
        checkJavaVersion();
        ObjectUtil.checkNotNull(inetAddress, "multicastAddress");
        ObjectUtil.checkNotNull(networkInterface, "networkInterface");
        if (inetAddress2 == null) {
            try {
                membershipKey = javaChannel().join(inetAddress, networkInterface);
            } catch (Throwable th) {
                channelPromise.setFailure(th);
            }
        } else {
            membershipKey = javaChannel().join(inetAddress, networkInterface, inetAddress2);
        }
        synchronized (this) {
            Map<InetAddress, List<MembershipKey>> map = this.memberships;
            if (map == null) {
                this.memberships = new HashMap();
                list = null;
            } else {
                list = map.get(inetAddress);
            }
            if (list == null) {
                list = new ArrayList();
                this.memberships.put(inetAddress, list);
            }
            list.add(membershipKey);
        }
        channelPromise.setSuccess();
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
        List list;
        checkJavaVersion();
        ObjectUtil.checkNotNull(inetAddress, "multicastAddress");
        ObjectUtil.checkNotNull(networkInterface, "networkInterface");
        synchronized (this) {
            Map<InetAddress, List<MembershipKey>> map = this.memberships;
            if (!(map == null || (list = map.get(inetAddress)) == null)) {
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    MembershipKey m = NioPathKt$$ExternalSyntheticApiModelOutline0.m(it.next());
                    if (networkInterface.equals(NioPathKt$$ExternalSyntheticApiModelOutline0.m(m)) && ((inetAddress2 == null && NioPathKt$$ExternalSyntheticApiModelOutline0.m(m) == null) || (inetAddress2 != null && inetAddress2.equals(NioPathKt$$ExternalSyntheticApiModelOutline0.m(m))))) {
                        NioPathKt$$ExternalSyntheticApiModelOutline0.m(m);
                        it.remove();
                    }
                }
                if (list.isEmpty()) {
                    this.memberships.remove(inetAddress);
                }
            }
        }
        channelPromise.setSuccess();
        return channelPromise;
    }

    public ChannelFuture block(InetAddress inetAddress, NetworkInterface networkInterface, InetAddress inetAddress2) {
        return block(inetAddress, networkInterface, inetAddress2, newPromise());
    }

    public ChannelFuture block(InetAddress inetAddress, NetworkInterface networkInterface, InetAddress inetAddress2, ChannelPromise channelPromise) {
        checkJavaVersion();
        ObjectUtil.checkNotNull(inetAddress, "multicastAddress");
        ObjectUtil.checkNotNull(inetAddress2, "sourceToBlock");
        ObjectUtil.checkNotNull(networkInterface, "networkInterface");
        synchronized (this) {
            Map<InetAddress, List<MembershipKey>> map = this.memberships;
            if (map != null) {
                for (Object m : map.get(inetAddress)) {
                    MembershipKey m2 = NioPathKt$$ExternalSyntheticApiModelOutline0.m(m);
                    if (networkInterface.equals(NioPathKt$$ExternalSyntheticApiModelOutline0.m(m2))) {
                        try {
                            MembershipKey unused = m2.block(inetAddress2);
                        } catch (IOException e) {
                            channelPromise.setFailure(e);
                        }
                    }
                }
            }
        }
        channelPromise.setSuccess();
        return channelPromise;
    }

    public ChannelFuture block(InetAddress inetAddress, InetAddress inetAddress2) {
        return block(inetAddress, inetAddress2, newPromise());
    }

    public ChannelFuture block(InetAddress inetAddress, InetAddress inetAddress2, ChannelPromise channelPromise) {
        try {
            return block(inetAddress, NetworkInterface.getByInetAddress(localAddress().getAddress()), inetAddress2, channelPromise);
        } catch (SocketException e) {
            channelPromise.setFailure(e);
            return channelPromise;
        }
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public void setReadPending(boolean z) {
        super.setReadPending(z);
    }

    /* access modifiers changed from: package-private */
    public void clearReadPending0() {
        clearReadPending();
    }

    /* access modifiers changed from: protected */
    public boolean closeOnReadError(Throwable th) {
        if (th instanceof SocketException) {
            return false;
        }
        return super.closeOnReadError(th);
    }

    /* access modifiers changed from: protected */
    public boolean continueReading(RecvByteBufAllocator.Handle handle) {
        if (handle instanceof RecvByteBufAllocator.ExtendedHandle) {
            return ((RecvByteBufAllocator.ExtendedHandle) handle).continueReading(UncheckedBooleanSupplier.TRUE_SUPPLIER);
        }
        return handle.continueReading();
    }
}
