package io.netty.channel.epoll;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.channel.AddressedEnvelope;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelMetadata;
import io.netty.channel.ChannelOutboundBuffer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.ChannelPromise;
import io.netty.channel.DefaultAddressedEnvelope;
import io.netty.channel.epoll.AbstractEpollChannel;
import io.netty.channel.epoll.NativeDatagramPacketArray;
import io.netty.channel.socket.DatagramChannel;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.InternetProtocolFamily;
import io.netty.channel.unix.Errors;
import io.netty.channel.unix.SegmentedDatagramPacket;
import io.netty.channel.unix.UnixChannelUtil;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.UncheckedBooleanSupplier;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.RecyclableArrayList;
import io.netty.util.internal.StringUtil;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.PortUnreachableException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.UnresolvedAddressException;
import kotlin.text.Typography;

public final class EpollDatagramChannel extends AbstractEpollChannel implements DatagramChannel {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String EXPECTED_TYPES = (" (expected: " + StringUtil.simpleClassName((Class<?>) DatagramPacket.class) + ", " + StringUtil.simpleClassName((Class<?>) AddressedEnvelope.class) + Typography.less + StringUtil.simpleClassName((Class<?>) ByteBuf.class) + ", " + StringUtil.simpleClassName((Class<?>) InetSocketAddress.class) + ">, " + StringUtil.simpleClassName((Class<?>) ByteBuf.class) + ')');
    private static final ChannelMetadata METADATA = new ChannelMetadata(true, 16);
    private final EpollDatagramChannelConfig config;
    private volatile boolean connected;

    public /* bridge */ /* synthetic */ boolean isOpen() {
        return super.isOpen();
    }

    public static boolean isSegmentedDatagramPacketSupported() {
        return Epoll.isAvailable() && Native.IS_SUPPORTING_SENDMMSG && Native.IS_SUPPORTING_UDP_SEGMENT;
    }

    public EpollDatagramChannel() {
        this((InternetProtocolFamily) null);
    }

    public EpollDatagramChannel(InternetProtocolFamily internetProtocolFamily) {
        this(LinuxSocket.newSocketDgram(internetProtocolFamily), false);
    }

    public EpollDatagramChannel(int i) {
        this(new LinuxSocket(i), true);
    }

    private EpollDatagramChannel(LinuxSocket linuxSocket, boolean z) {
        super((Channel) null, linuxSocket, z);
        this.config = new EpollDatagramChannelConfig(this);
    }

    public InetSocketAddress remoteAddress() {
        return (InetSocketAddress) super.remoteAddress();
    }

    public InetSocketAddress localAddress() {
        return (InetSocketAddress) super.localAddress();
    }

    public ChannelMetadata metadata() {
        return METADATA;
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
        } catch (IOException e) {
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
        if (eventLoop().inEventLoop()) {
            joinGroup0(inetAddress, networkInterface, inetAddress2, channelPromise);
        } else {
            final InetAddress inetAddress3 = inetAddress;
            final NetworkInterface networkInterface2 = networkInterface;
            final InetAddress inetAddress4 = inetAddress2;
            final ChannelPromise channelPromise2 = channelPromise;
            eventLoop().execute(new Runnable() {
                public void run() {
                    EpollDatagramChannel.this.joinGroup0(inetAddress3, networkInterface2, inetAddress4, channelPromise2);
                }
            });
        }
        return channelPromise;
    }

    /* access modifiers changed from: private */
    public void joinGroup0(InetAddress inetAddress, NetworkInterface networkInterface, InetAddress inetAddress2, ChannelPromise channelPromise) {
        try {
            this.socket.joinGroup(inetAddress, networkInterface, inetAddress2);
            channelPromise.setSuccess();
        } catch (IOException e) {
            channelPromise.setFailure(e);
        }
    }

    public ChannelFuture leaveGroup(InetAddress inetAddress) {
        return leaveGroup(inetAddress, newPromise());
    }

    public ChannelFuture leaveGroup(InetAddress inetAddress, ChannelPromise channelPromise) {
        try {
            return leaveGroup(inetAddress, NetworkInterface.getByInetAddress(localAddress().getAddress()), (InetAddress) null, channelPromise);
        } catch (IOException e) {
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
        if (eventLoop().inEventLoop()) {
            leaveGroup0(inetAddress, networkInterface, inetAddress2, channelPromise);
        } else {
            final InetAddress inetAddress3 = inetAddress;
            final NetworkInterface networkInterface2 = networkInterface;
            final InetAddress inetAddress4 = inetAddress2;
            final ChannelPromise channelPromise2 = channelPromise;
            eventLoop().execute(new Runnable() {
                public void run() {
                    EpollDatagramChannel.this.leaveGroup0(inetAddress3, networkInterface2, inetAddress4, channelPromise2);
                }
            });
        }
        return channelPromise;
    }

    /* access modifiers changed from: private */
    public void leaveGroup0(InetAddress inetAddress, NetworkInterface networkInterface, InetAddress inetAddress2, ChannelPromise channelPromise) {
        try {
            this.socket.leaveGroup(inetAddress, networkInterface, inetAddress2);
            channelPromise.setSuccess();
        } catch (IOException e) {
            channelPromise.setFailure(e);
        }
    }

    public ChannelFuture block(InetAddress inetAddress, NetworkInterface networkInterface, InetAddress inetAddress2) {
        return block(inetAddress, networkInterface, inetAddress2, newPromise());
    }

    public ChannelFuture block(InetAddress inetAddress, NetworkInterface networkInterface, InetAddress inetAddress2, ChannelPromise channelPromise) {
        ObjectUtil.checkNotNull(inetAddress, "multicastAddress");
        ObjectUtil.checkNotNull(inetAddress2, "sourceToBlock");
        ObjectUtil.checkNotNull(networkInterface, "networkInterface");
        channelPromise.setFailure(new UnsupportedOperationException("Multicast block not supported"));
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
    public AbstractEpollChannel.AbstractEpollUnsafe newUnsafe() {
        return new EpollDatagramChannelUnsafe();
    }

    /* access modifiers changed from: protected */
    public void doBind(SocketAddress socketAddress) throws Exception {
        if (socketAddress instanceof InetSocketAddress) {
            InetSocketAddress inetSocketAddress = (InetSocketAddress) socketAddress;
            if (inetSocketAddress.getAddress().isAnyLocalAddress() && (inetSocketAddress.getAddress() instanceof Inet4Address) && this.socket.family() == InternetProtocolFamily.IPv6) {
                socketAddress = new InetSocketAddress(LinuxSocket.INET6_ANY, inetSocketAddress.getPort());
            }
        }
        super.doBind(socketAddress);
        this.active = true;
    }

    /* access modifiers changed from: protected */
    public void doWrite(ChannelOutboundBuffer channelOutboundBuffer) throws Exception {
        int maxMessagesPerWrite = maxMessagesPerWrite();
        loop0:
        while (true) {
            if (maxMessagesPerWrite <= 0) {
                break;
            }
            Object current = channelOutboundBuffer.current();
            if (current == null) {
                break;
            }
            try {
                if ((Native.IS_SUPPORTING_SENDMMSG && channelOutboundBuffer.size() > 1) || (channelOutboundBuffer.current() instanceof SegmentedDatagramPacket)) {
                    NativeDatagramPacketArray cleanDatagramPacketArray = cleanDatagramPacketArray();
                    cleanDatagramPacketArray.add(channelOutboundBuffer, isConnected(), maxMessagesPerWrite);
                    int count = cleanDatagramPacketArray.count();
                    if (count >= 1) {
                        int sendmmsg = this.socket.sendmmsg(cleanDatagramPacketArray.packets(), 0, count);
                        if (sendmmsg == 0) {
                            break;
                        }
                        for (int i = 0; i < sendmmsg; i++) {
                            channelOutboundBuffer.remove();
                        }
                        maxMessagesPerWrite -= sendmmsg;
                    }
                }
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

    private boolean doWriteMessage(Object obj) throws Exception {
        InetSocketAddress inetSocketAddress;
        ByteBuf byteBuf;
        if (obj instanceof AddressedEnvelope) {
            AddressedEnvelope addressedEnvelope = (AddressedEnvelope) obj;
            byteBuf = (ByteBuf) addressedEnvelope.content();
            inetSocketAddress = (InetSocketAddress) addressedEnvelope.recipient();
        } else {
            byteBuf = (ByteBuf) obj;
            inetSocketAddress = null;
        }
        if (byteBuf.readableBytes() != 0 && doWriteOrSendBytes(byteBuf, inetSocketAddress, false) <= 0) {
            return false;
        }
        return true;
    }

    private static void checkUnresolved(AddressedEnvelope<?, ?> addressedEnvelope) {
        if ((addressedEnvelope.recipient() instanceof InetSocketAddress) && ((InetSocketAddress) addressedEnvelope.recipient()).isUnresolved()) {
            throw new UnresolvedAddressException();
        }
    }

    /* access modifiers changed from: protected */
    public Object filterOutboundMessage(Object obj) {
        if (obj instanceof SegmentedDatagramPacket) {
            if (Native.IS_SUPPORTING_UDP_SEGMENT) {
                SegmentedDatagramPacket segmentedDatagramPacket = (SegmentedDatagramPacket) obj;
                checkUnresolved(segmentedDatagramPacket);
                ByteBuf byteBuf = (ByteBuf) segmentedDatagramPacket.content();
                return UnixChannelUtil.isBufferCopyNeededForWrite(byteBuf) ? segmentedDatagramPacket.replace(newDirectBuffer(segmentedDatagramPacket, byteBuf)) : obj;
            }
            throw new UnsupportedOperationException("unsupported message type: " + StringUtil.simpleClassName(obj) + EXPECTED_TYPES);
        } else if (obj instanceof DatagramPacket) {
            DatagramPacket datagramPacket = (DatagramPacket) obj;
            checkUnresolved(datagramPacket);
            ByteBuf byteBuf2 = (ByteBuf) datagramPacket.content();
            return UnixChannelUtil.isBufferCopyNeededForWrite(byteBuf2) ? new DatagramPacket(newDirectBuffer(datagramPacket, byteBuf2), (InetSocketAddress) datagramPacket.recipient()) : obj;
        } else if (obj instanceof ByteBuf) {
            ByteBuf byteBuf3 = (ByteBuf) obj;
            return UnixChannelUtil.isBufferCopyNeededForWrite(byteBuf3) ? newDirectBuffer(byteBuf3) : byteBuf3;
        } else {
            if (obj instanceof AddressedEnvelope) {
                AddressedEnvelope addressedEnvelope = (AddressedEnvelope) obj;
                checkUnresolved(addressedEnvelope);
                if ((addressedEnvelope.content() instanceof ByteBuf) && (addressedEnvelope.recipient() == null || (addressedEnvelope.recipient() instanceof InetSocketAddress))) {
                    ByteBuf byteBuf4 = (ByteBuf) addressedEnvelope.content();
                    return UnixChannelUtil.isBufferCopyNeededForWrite(byteBuf4) ? new DefaultAddressedEnvelope(newDirectBuffer(addressedEnvelope, byteBuf4), (InetSocketAddress) addressedEnvelope.recipient()) : addressedEnvelope;
                }
            }
            throw new UnsupportedOperationException("unsupported message type: " + StringUtil.simpleClassName(obj) + EXPECTED_TYPES);
        }
    }

    public EpollDatagramChannelConfig config() {
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

    final class EpollDatagramChannelUnsafe extends AbstractEpollChannel.AbstractEpollUnsafe {
        static final /* synthetic */ boolean $assertionsDisabled = false;

        static {
            Class<EpollDatagramChannel> cls = EpollDatagramChannel.class;
        }

        EpollDatagramChannelUnsafe() {
            super();
        }

        /* access modifiers changed from: package-private */
        public void epollInReady() {
            boolean isConnected;
            int i;
            boolean z;
            EpollDatagramChannelConfig config = EpollDatagramChannel.this.config();
            if (EpollDatagramChannel.this.shouldBreakEpollInReady(config)) {
                clearEpollIn0();
                return;
            }
            EpollRecvByteAllocatorHandle recvBufAllocHandle = recvBufAllocHandle();
            recvBufAllocHandle.edgeTriggered(EpollDatagramChannel.this.isFlagSet(Native.EPOLLET));
            ChannelPipeline pipeline = EpollDatagramChannel.this.pipeline();
            ByteBufAllocator allocator = config.getAllocator();
            recvBufAllocHandle.reset(config);
            epollInBefore();
            try {
                isConnected = EpollDatagramChannel.this.isConnected();
                do {
                    int maxDatagramPayloadSize = EpollDatagramChannel.this.config().getMaxDatagramPayloadSize();
                    ByteBuf allocate = recvBufAllocHandle.allocate(allocator);
                    if (Native.IS_SUPPORTING_RECVMMSG) {
                        i = maxDatagramPayloadSize == 0 ? 1 : allocate.writableBytes() / maxDatagramPayloadSize;
                    } else {
                        i = 0;
                    }
                    if (i <= 1) {
                        if (isConnected) {
                            if (!config.isUdpGro()) {
                                z = EpollDatagramChannel.this.connectedRead(recvBufAllocHandle, allocate, maxDatagramPayloadSize);
                            }
                        }
                        EpollDatagramChannel epollDatagramChannel = EpollDatagramChannel.this;
                        z = epollDatagramChannel.recvmsg(recvBufAllocHandle, epollDatagramChannel.cleanDatagramPacketArray(), allocate);
                    } else {
                        EpollDatagramChannel epollDatagramChannel2 = EpollDatagramChannel.this;
                        z = epollDatagramChannel2.scatteringRead(recvBufAllocHandle, epollDatagramChannel2.cleanDatagramPacketArray(), allocate, maxDatagramPayloadSize, i);
                    }
                    if (!z) {
                        break;
                    }
                    this.readPending = false;
                } while (recvBufAllocHandle.continueReading(UncheckedBooleanSupplier.TRUE_SUPPLIER));
                th = null;
            } catch (Errors.NativeIoException e) {
                if (isConnected) {
                    throw EpollDatagramChannel.this.translateForConnected(e);
                }
                throw e;
            } catch (Throwable th) {
                th = th;
            }
            try {
                recvBufAllocHandle.readComplete();
                pipeline.fireChannelReadComplete();
                if (th != null) {
                    pipeline.fireExceptionCaught(th);
                }
            } finally {
                epollInFinally(config);
            }
        }
    }

    /* access modifiers changed from: private */
    public boolean connectedRead(EpollRecvByteAllocatorHandle epollRecvByteAllocatorHandle, ByteBuf byteBuf, int i) throws Exception {
        int i2;
        int i3;
        if (i != 0) {
            try {
                i2 = Math.min(byteBuf.writableBytes(), i);
            } catch (Throwable th) {
                if (byteBuf != null) {
                    byteBuf.release();
                }
                throw th;
            }
        } else {
            i2 = byteBuf.writableBytes();
        }
        epollRecvByteAllocatorHandle.attemptedBytesRead(i2);
        int writerIndex = byteBuf.writerIndex();
        if (byteBuf.hasMemoryAddress()) {
            i3 = this.socket.recvAddress(byteBuf.memoryAddress(), writerIndex, writerIndex + i2);
        } else {
            ByteBuffer internalNioBuffer = byteBuf.internalNioBuffer(writerIndex, i2);
            i3 = this.socket.recv(internalNioBuffer, internalNioBuffer.position(), internalNioBuffer.limit());
        }
        if (i3 <= 0) {
            epollRecvByteAllocatorHandle.lastBytesRead(i3);
            if (byteBuf == null) {
                return false;
            }
            byteBuf.release();
            return false;
        }
        byteBuf.writerIndex(writerIndex + i3);
        if (i <= 0) {
            i2 = i3;
        }
        epollRecvByteAllocatorHandle.lastBytesRead(i2);
        DatagramPacket datagramPacket = new DatagramPacket(byteBuf, localAddress(), remoteAddress());
        epollRecvByteAllocatorHandle.incMessagesRead(1);
        pipeline().fireChannelRead(datagramPacket);
        return true;
    }

    /* access modifiers changed from: private */
    public IOException translateForConnected(Errors.NativeIoException nativeIoException) {
        if (nativeIoException.expectedErr() != Errors.ERROR_ECONNREFUSED_NEGATIVE) {
            return nativeIoException;
        }
        PortUnreachableException portUnreachableException = new PortUnreachableException(nativeIoException.getMessage());
        portUnreachableException.initCause(nativeIoException);
        return portUnreachableException;
    }

    private static void addDatagramPacketToOut(DatagramPacket datagramPacket, RecyclableArrayList recyclableArrayList) {
        if (datagramPacket instanceof SegmentedDatagramPacket) {
            SegmentedDatagramPacket segmentedDatagramPacket = (SegmentedDatagramPacket) datagramPacket;
            ByteBuf byteBuf = (ByteBuf) segmentedDatagramPacket.content();
            InetSocketAddress inetSocketAddress = (InetSocketAddress) segmentedDatagramPacket.recipient();
            InetSocketAddress inetSocketAddress2 = (InetSocketAddress) segmentedDatagramPacket.sender();
            int segmentSize = segmentedDatagramPacket.segmentSize();
            do {
                recyclableArrayList.add(new DatagramPacket(byteBuf.readRetainedSlice(Math.min(byteBuf.readableBytes(), segmentSize)), inetSocketAddress, inetSocketAddress2));
            } while (byteBuf.isReadable());
            segmentedDatagramPacket.release();
            return;
        }
        recyclableArrayList.add(datagramPacket);
    }

    private static void releaseAndRecycle(ByteBuf byteBuf, RecyclableArrayList recyclableArrayList) {
        if (byteBuf != null) {
            byteBuf.release();
        }
        if (recyclableArrayList != null) {
            for (int i = 0; i < recyclableArrayList.size(); i++) {
                ReferenceCountUtil.release(recyclableArrayList.get(i));
            }
            recyclableArrayList.recycle();
        }
    }

    private static void processPacket(ChannelPipeline channelPipeline, EpollRecvByteAllocatorHandle epollRecvByteAllocatorHandle, int i, DatagramPacket datagramPacket) {
        epollRecvByteAllocatorHandle.lastBytesRead(Math.max(1, i));
        epollRecvByteAllocatorHandle.incMessagesRead(1);
        channelPipeline.fireChannelRead(datagramPacket);
    }

    private static void processPacketList(ChannelPipeline channelPipeline, EpollRecvByteAllocatorHandle epollRecvByteAllocatorHandle, int i, RecyclableArrayList recyclableArrayList) {
        int size = recyclableArrayList.size();
        epollRecvByteAllocatorHandle.lastBytesRead(Math.max(1, i));
        epollRecvByteAllocatorHandle.incMessagesRead(size);
        for (int i2 = 0; i2 < size; i2++) {
            channelPipeline.fireChannelRead(recyclableArrayList.set(i2, Unpooled.EMPTY_BUFFER));
        }
    }

    /* access modifiers changed from: private */
    public boolean recvmsg(EpollRecvByteAllocatorHandle epollRecvByteAllocatorHandle, NativeDatagramPacketArray nativeDatagramPacketArray, ByteBuf byteBuf) throws IOException {
        RecyclableArrayList recyclableArrayList = null;
        try {
            int writableBytes = byteBuf.writableBytes();
            nativeDatagramPacketArray.addWritable(byteBuf, byteBuf.writerIndex(), writableBytes);
            epollRecvByteAllocatorHandle.attemptedBytesRead(writableBytes);
            NativeDatagramPacketArray.NativeDatagramPacket nativeDatagramPacket = nativeDatagramPacketArray.packets()[0];
            int recvmsg = this.socket.recvmsg(nativeDatagramPacket);
            if (!nativeDatagramPacket.hasSender()) {
                epollRecvByteAllocatorHandle.lastBytesRead(-1);
                releaseAndRecycle(byteBuf, (RecyclableArrayList) null);
                return false;
            }
            byteBuf.writerIndex(recvmsg);
            DatagramPacket newDatagramPacket = nativeDatagramPacket.newDatagramPacket(byteBuf, localAddress());
            if (!(newDatagramPacket instanceof SegmentedDatagramPacket)) {
                processPacket(pipeline(), epollRecvByteAllocatorHandle, recvmsg, newDatagramPacket);
            } else {
                RecyclableArrayList newInstance = RecyclableArrayList.newInstance();
                try {
                    addDatagramPacketToOut(newDatagramPacket, newInstance);
                    processPacketList(pipeline(), epollRecvByteAllocatorHandle, recvmsg, newInstance);
                    newInstance.recycle();
                } catch (Throwable th) {
                    th = th;
                    recyclableArrayList = newInstance;
                    releaseAndRecycle(byteBuf, recyclableArrayList);
                    throw th;
                }
            }
            releaseAndRecycle(byteBuf, (RecyclableArrayList) null);
            return true;
        } catch (Throwable th2) {
            th = th2;
            releaseAndRecycle(byteBuf, recyclableArrayList);
            throw th;
        }
    }

    /* access modifiers changed from: private */
    public boolean scatteringRead(EpollRecvByteAllocatorHandle epollRecvByteAllocatorHandle, NativeDatagramPacketArray nativeDatagramPacketArray, ByteBuf byteBuf, int i, int i2) throws IOException {
        RecyclableArrayList recyclableArrayList = null;
        try {
            int writerIndex = byteBuf.writerIndex();
            int i3 = 0;
            int i4 = 0;
            while (true) {
                if (i4 >= i2) {
                    break;
                } else if (!nativeDatagramPacketArray.addWritable(byteBuf, writerIndex, i)) {
                    break;
                } else {
                    i4++;
                    writerIndex += i;
                }
            }
            epollRecvByteAllocatorHandle.attemptedBytesRead(writerIndex - byteBuf.writerIndex());
            NativeDatagramPacketArray.NativeDatagramPacket[] packets = nativeDatagramPacketArray.packets();
            int recvmmsg = this.socket.recvmmsg(packets, 0, nativeDatagramPacketArray.count());
            if (recvmmsg == 0) {
                epollRecvByteAllocatorHandle.lastBytesRead(-1);
                releaseAndRecycle(byteBuf, (RecyclableArrayList) null);
                return false;
            }
            InetSocketAddress localAddress = localAddress();
            int i5 = recvmmsg * i;
            byteBuf.writerIndex(byteBuf.writerIndex() + i5);
            if (recvmmsg == 1) {
                DatagramPacket newDatagramPacket = packets[0].newDatagramPacket(byteBuf, localAddress);
                if (!(newDatagramPacket instanceof SegmentedDatagramPacket)) {
                    processPacket(pipeline(), epollRecvByteAllocatorHandle, i, newDatagramPacket);
                    releaseAndRecycle(byteBuf, (RecyclableArrayList) null);
                    return true;
                }
            }
            RecyclableArrayList newInstance = RecyclableArrayList.newInstance();
            while (i3 < recvmmsg) {
                try {
                    DatagramPacket newDatagramPacket2 = packets[i3].newDatagramPacket(byteBuf, localAddress);
                    byteBuf.skipBytes(i);
                    addDatagramPacketToOut(newDatagramPacket2, newInstance);
                    i3++;
                } catch (Throwable th) {
                    th = th;
                    recyclableArrayList = newInstance;
                    releaseAndRecycle(byteBuf, recyclableArrayList);
                    throw th;
                }
            }
            byteBuf.release();
            try {
                processPacketList(pipeline(), epollRecvByteAllocatorHandle, i5, newInstance);
                newInstance.recycle();
                releaseAndRecycle((ByteBuf) null, (RecyclableArrayList) null);
                return true;
            } catch (Throwable th2) {
                th = th2;
                byteBuf = null;
                recyclableArrayList = newInstance;
                releaseAndRecycle(byteBuf, recyclableArrayList);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            releaseAndRecycle(byteBuf, recyclableArrayList);
            throw th;
        }
    }

    /* access modifiers changed from: private */
    public NativeDatagramPacketArray cleanDatagramPacketArray() {
        return ((EpollEventLoop) eventLoop()).cleanDatagramPacketArray();
    }
}
