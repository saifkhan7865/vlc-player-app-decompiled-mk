package io.netty.handler.pcap;

import androidx.lifecycle.LifecycleKt$$ExternalSyntheticBackportWithForwarding0;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.channel.socket.DatagramChannel;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.pcap.TCPPacket;
import io.netty.util.NetUtil;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.concurrent.atomic.AtomicReference;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

public final class PcapWriteHandler extends ChannelDuplexHandler implements Closeable {
    private final boolean captureZeroByte;
    private ChannelType channelType;
    private InetSocketAddress handlerAddr;
    private InetSocketAddress initiatorAddr;
    private boolean isServerPipeline;
    private final InternalLogger logger;
    private final OutputStream outputStream;
    private PcapWriter pCapWriter;
    private int receiveSegmentNumber;
    private int sendSegmentNumber;
    private final boolean sharedOutputStream;
    private final AtomicReference<State> state;
    private final boolean writePcapGlobalHeader;

    private enum ChannelType {
        TCP,
        UDP
    }

    @Deprecated
    public PcapWriteHandler(OutputStream outputStream2) {
        this(outputStream2, false, true);
    }

    @Deprecated
    public PcapWriteHandler(OutputStream outputStream2, boolean z, boolean z2) {
        this.logger = InternalLoggerFactory.getInstance((Class<?>) PcapWriteHandler.class);
        this.sendSegmentNumber = 1;
        this.receiveSegmentNumber = 1;
        this.state = new AtomicReference<>(State.INIT);
        this.outputStream = (OutputStream) ObjectUtil.checkNotNull(outputStream2, "OutputStream");
        this.captureZeroByte = z;
        this.writePcapGlobalHeader = z2;
        this.sharedOutputStream = false;
    }

    private PcapWriteHandler(Builder builder, OutputStream outputStream2) {
        this.logger = InternalLoggerFactory.getInstance((Class<?>) PcapWriteHandler.class);
        this.sendSegmentNumber = 1;
        this.receiveSegmentNumber = 1;
        this.state = new AtomicReference<>(State.INIT);
        this.outputStream = outputStream2;
        this.captureZeroByte = builder.captureZeroByte;
        this.sharedOutputStream = builder.sharedOutputStream;
        this.writePcapGlobalHeader = builder.writePcapGlobalHeader;
        this.channelType = builder.channelType;
        this.handlerAddr = builder.handlerAddr;
        this.initiatorAddr = builder.initiatorAddr;
        this.isServerPipeline = builder.isServerPipeline;
    }

    public static void writeGlobalHeader(OutputStream outputStream2) throws IOException {
        PcapHeaders.writeGlobalHeader(outputStream2);
    }

    /* JADX INFO: finally extract failed */
    private void initializeIfNecessary(ChannelHandlerContext channelHandlerContext) throws Exception {
        if (this.state.get() == State.INIT) {
            this.pCapWriter = new PcapWriter(this);
            if (this.channelType == null) {
                if (channelHandlerContext.channel() instanceof SocketChannel) {
                    this.channelType = ChannelType.TCP;
                    if (channelHandlerContext.channel().parent() instanceof ServerSocketChannel) {
                        this.isServerPipeline = true;
                        this.initiatorAddr = (InetSocketAddress) channelHandlerContext.channel().remoteAddress();
                        this.handlerAddr = getLocalAddress(channelHandlerContext.channel(), this.initiatorAddr);
                    } else {
                        this.isServerPipeline = false;
                        this.handlerAddr = (InetSocketAddress) channelHandlerContext.channel().remoteAddress();
                        this.initiatorAddr = getLocalAddress(channelHandlerContext.channel(), this.handlerAddr);
                    }
                } else if (channelHandlerContext.channel() instanceof DatagramChannel) {
                    this.channelType = ChannelType.UDP;
                    if (((DatagramChannel) channelHandlerContext.channel()).isConnected()) {
                        this.handlerAddr = (InetSocketAddress) channelHandlerContext.channel().remoteAddress();
                        this.initiatorAddr = getLocalAddress(channelHandlerContext.channel(), this.handlerAddr);
                    }
                }
            }
            if (this.channelType == ChannelType.TCP) {
                this.logger.debug("Initiating Fake TCP 3-Way Handshake");
                ByteBuf buffer = channelHandlerContext.alloc().buffer();
                try {
                    TCPPacket.writePacket(buffer, (ByteBuf) null, 0, 0, this.initiatorAddr.getPort(), this.handlerAddr.getPort(), TCPPacket.TCPFlag.SYN);
                    completeTCPWrite(this.initiatorAddr, this.handlerAddr, buffer, channelHandlerContext.alloc(), channelHandlerContext);
                    TCPPacket.writePacket(buffer, (ByteBuf) null, 0, 1, this.handlerAddr.getPort(), this.initiatorAddr.getPort(), TCPPacket.TCPFlag.SYN, TCPPacket.TCPFlag.ACK);
                    completeTCPWrite(this.handlerAddr, this.initiatorAddr, buffer, channelHandlerContext.alloc(), channelHandlerContext);
                    TCPPacket.writePacket(buffer, (ByteBuf) null, 1, 1, this.initiatorAddr.getPort(), this.handlerAddr.getPort(), TCPPacket.TCPFlag.ACK);
                    completeTCPWrite(this.initiatorAddr, this.handlerAddr, buffer, channelHandlerContext.alloc(), channelHandlerContext);
                    buffer.release();
                    this.logger.debug("Finished Fake TCP 3-Way Handshake");
                } catch (Throwable th) {
                    buffer.release();
                    throw th;
                }
            }
            this.state.set(State.WRITING);
        }
    }

    public void channelActive(ChannelHandlerContext channelHandlerContext) throws Exception {
        initializeIfNecessary(channelHandlerContext);
        super.channelActive(channelHandlerContext);
    }

    public void channelRead(ChannelHandlerContext channelHandlerContext, Object obj) throws Exception {
        if (this.state.get() == State.INIT) {
            initializeIfNecessary(channelHandlerContext);
        }
        if (this.state.get() == State.WRITING) {
            if (this.channelType == ChannelType.TCP) {
                handleTCP(channelHandlerContext, obj, false);
            } else if (this.channelType == ChannelType.UDP) {
                handleUDP(channelHandlerContext, obj);
            } else {
                logDiscard();
            }
        }
        super.channelRead(channelHandlerContext, obj);
    }

    public void write(ChannelHandlerContext channelHandlerContext, Object obj, ChannelPromise channelPromise) throws Exception {
        if (this.state.get() == State.INIT) {
            initializeIfNecessary(channelHandlerContext);
        }
        if (this.state.get() == State.WRITING) {
            if (this.channelType == ChannelType.TCP) {
                handleTCP(channelHandlerContext, obj, true);
            } else if (this.channelType == ChannelType.UDP) {
                handleUDP(channelHandlerContext, obj);
            } else {
                logDiscard();
            }
        }
        super.write(channelHandlerContext, obj, channelPromise);
    }

    private void handleTCP(ChannelHandlerContext channelHandlerContext, Object obj, boolean z) {
        InetSocketAddress inetSocketAddress;
        InetSocketAddress inetSocketAddress2;
        InetSocketAddress inetSocketAddress3;
        InetSocketAddress inetSocketAddress4;
        Object obj2 = obj;
        if (obj2 instanceof ByteBuf) {
            ByteBuf byteBuf = (ByteBuf) obj2;
            if (byteBuf.readableBytes() != 0 || this.captureZeroByte) {
                ByteBufAllocator alloc = channelHandlerContext.alloc();
                ByteBuf duplicate = byteBuf.duplicate();
                ByteBuf buffer = alloc.buffer();
                int readableBytes = duplicate.readableBytes();
                if (z) {
                    try {
                        if (this.isServerPipeline) {
                            inetSocketAddress4 = this.handlerAddr;
                            inetSocketAddress3 = this.initiatorAddr;
                        } else {
                            inetSocketAddress4 = this.initiatorAddr;
                            inetSocketAddress3 = this.handlerAddr;
                        }
                        InetSocketAddress inetSocketAddress5 = inetSocketAddress4;
                        InetSocketAddress inetSocketAddress6 = inetSocketAddress3;
                        TCPPacket.writePacket(buffer, duplicate, this.sendSegmentNumber, this.receiveSegmentNumber, inetSocketAddress5.getPort(), inetSocketAddress6.getPort(), TCPPacket.TCPFlag.ACK);
                        completeTCPWrite(inetSocketAddress5, inetSocketAddress6, buffer, alloc, channelHandlerContext);
                        logTCP(true, readableBytes, this.sendSegmentNumber, this.receiveSegmentNumber, inetSocketAddress5, inetSocketAddress6, false);
                        int i = this.sendSegmentNumber + readableBytes;
                        this.sendSegmentNumber = i;
                        TCPPacket.writePacket(buffer, (ByteBuf) null, this.receiveSegmentNumber, i, inetSocketAddress6.getPort(), inetSocketAddress5.getPort(), TCPPacket.TCPFlag.ACK);
                        completeTCPWrite(inetSocketAddress6, inetSocketAddress5, buffer, alloc, channelHandlerContext);
                        logTCP(true, readableBytes, this.sendSegmentNumber, this.receiveSegmentNumber, inetSocketAddress6, inetSocketAddress5, true);
                    } catch (Throwable th) {
                        buffer.release();
                        throw th;
                    }
                } else {
                    if (this.isServerPipeline) {
                        inetSocketAddress2 = this.initiatorAddr;
                        inetSocketAddress = this.handlerAddr;
                    } else {
                        inetSocketAddress2 = this.handlerAddr;
                        inetSocketAddress = this.initiatorAddr;
                    }
                    InetSocketAddress inetSocketAddress7 = inetSocketAddress2;
                    InetSocketAddress inetSocketAddress8 = inetSocketAddress;
                    TCPPacket.writePacket(buffer, duplicate, this.receiveSegmentNumber, this.sendSegmentNumber, inetSocketAddress7.getPort(), inetSocketAddress8.getPort(), TCPPacket.TCPFlag.ACK);
                    completeTCPWrite(inetSocketAddress7, inetSocketAddress8, buffer, alloc, channelHandlerContext);
                    logTCP(false, readableBytes, this.receiveSegmentNumber, this.sendSegmentNumber, inetSocketAddress7, inetSocketAddress8, false);
                    int i2 = this.receiveSegmentNumber + readableBytes;
                    this.receiveSegmentNumber = i2;
                    TCPPacket.writePacket(buffer, (ByteBuf) null, this.sendSegmentNumber, i2, inetSocketAddress8.getPort(), inetSocketAddress7.getPort(), TCPPacket.TCPFlag.ACK);
                    completeTCPWrite(inetSocketAddress8, inetSocketAddress7, buffer, alloc, channelHandlerContext);
                    logTCP(false, readableBytes, this.sendSegmentNumber, this.receiveSegmentNumber, inetSocketAddress8, inetSocketAddress7, true);
                }
                buffer.release();
                return;
            }
            this.logger.debug("Discarding Zero Byte TCP Packet. isWriteOperation {}", (Object) Boolean.valueOf(z));
            return;
        }
        this.logger.debug("Discarding Pcap Write for TCP Object: {}", obj2);
    }

    private void completeTCPWrite(InetSocketAddress inetSocketAddress, InetSocketAddress inetSocketAddress2, ByteBuf byteBuf, ByteBufAllocator byteBufAllocator, ChannelHandlerContext channelHandlerContext) {
        ByteBuf buffer = byteBufAllocator.buffer();
        ByteBuf buffer2 = byteBufAllocator.buffer();
        ByteBuf buffer3 = byteBufAllocator.buffer();
        try {
            if ((inetSocketAddress.getAddress() instanceof Inet4Address) && (inetSocketAddress2.getAddress() instanceof Inet4Address)) {
                IPPacket.writeTCPv4(buffer, byteBuf, NetUtil.ipv4AddressToInt((Inet4Address) inetSocketAddress.getAddress()), NetUtil.ipv4AddressToInt((Inet4Address) inetSocketAddress2.getAddress()));
                EthernetPacket.writeIPv4(buffer2, buffer);
            } else if (!(inetSocketAddress.getAddress() instanceof Inet6Address) || !(inetSocketAddress2.getAddress() instanceof Inet6Address)) {
                this.logger.error("Source and Destination IP Address versions are not same. Source Address: {}, Destination Address: {}", inetSocketAddress.getAddress(), inetSocketAddress2.getAddress());
                buffer.release();
                buffer2.release();
                buffer3.release();
                return;
            } else {
                IPPacket.writeTCPv6(buffer, byteBuf, inetSocketAddress.getAddress().getAddress(), inetSocketAddress2.getAddress().getAddress());
                EthernetPacket.writeIPv6(buffer2, buffer);
            }
            this.pCapWriter.writePacket(buffer3, buffer2);
        } catch (IOException e) {
            this.logger.error("Caught Exception While Writing Packet into Pcap", (Throwable) e);
            channelHandlerContext.fireExceptionCaught(e);
        } catch (Throwable th) {
            buffer.release();
            buffer2.release();
            buffer3.release();
            throw th;
        }
        buffer.release();
        buffer2.release();
        buffer3.release();
    }

    /* JADX WARNING: Unknown top exception splitter block from list: {B:14:0x0030=Splitter:B:14:0x0030, B:32:0x00b5=Splitter:B:32:0x00b5} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void handleUDP(io.netty.channel.ChannelHandlerContext r11, java.lang.Object r12) {
        /*
            r10 = this;
            io.netty.buffer.ByteBufAllocator r0 = r11.alloc()
            io.netty.buffer.ByteBuf r0 = r0.buffer()
            boolean r1 = r12 instanceof io.netty.channel.socket.DatagramPacket     // Catch:{ all -> 0x00fd }
            r2 = 2
            r3 = 1
            r4 = 0
            r5 = 3
            java.lang.String r6 = "Writing UDP Data of {} Bytes, Src Addr {}, Dst Addr {}"
            java.lang.String r7 = "Discarding Zero Byte UDP Packet"
            if (r1 == 0) goto L_0x0087
            r1 = r12
            io.netty.channel.socket.DatagramPacket r1 = (io.netty.channel.socket.DatagramPacket) r1     // Catch:{ all -> 0x00fd }
            java.lang.Object r1 = r1.content()     // Catch:{ all -> 0x00fd }
            io.netty.buffer.ByteBuf r1 = (io.netty.buffer.ByteBuf) r1     // Catch:{ all -> 0x00fd }
            int r1 = r1.readableBytes()     // Catch:{ all -> 0x00fd }
            if (r1 != 0) goto L_0x0030
            boolean r1 = r10.captureZeroByte     // Catch:{ all -> 0x00fd }
            if (r1 != 0) goto L_0x0030
            io.netty.util.internal.logging.InternalLogger r11 = r10.logger     // Catch:{ all -> 0x00fd }
            r11.debug((java.lang.String) r7)     // Catch:{ all -> 0x00fd }
            r0.release()
            return
        L_0x0030:
            io.netty.channel.socket.DatagramPacket r12 = (io.netty.channel.socket.DatagramPacket) r12     // Catch:{ all -> 0x00fd }
            io.netty.channel.socket.DatagramPacket r12 = r12.duplicate()     // Catch:{ all -> 0x00fd }
            java.net.SocketAddress r1 = r12.sender()     // Catch:{ all -> 0x00fd }
            java.net.InetSocketAddress r1 = (java.net.InetSocketAddress) r1     // Catch:{ all -> 0x00fd }
            java.net.SocketAddress r7 = r12.recipient()     // Catch:{ all -> 0x00fd }
            java.net.InetSocketAddress r7 = (java.net.InetSocketAddress) r7     // Catch:{ all -> 0x00fd }
            if (r1 != 0) goto L_0x004c
            io.netty.channel.Channel r1 = r11.channel()     // Catch:{ all -> 0x00fd }
            java.net.InetSocketAddress r1 = getLocalAddress(r1, r7)     // Catch:{ all -> 0x00fd }
        L_0x004c:
            r8 = r1
            io.netty.util.internal.logging.InternalLogger r1 = r10.logger     // Catch:{ all -> 0x00fd }
            java.lang.Object r9 = r12.content()     // Catch:{ all -> 0x00fd }
            io.netty.buffer.ByteBuf r9 = (io.netty.buffer.ByteBuf) r9     // Catch:{ all -> 0x00fd }
            int r9 = r9.readableBytes()     // Catch:{ all -> 0x00fd }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ all -> 0x00fd }
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch:{ all -> 0x00fd }
            r5[r4] = r9     // Catch:{ all -> 0x00fd }
            r5[r3] = r8     // Catch:{ all -> 0x00fd }
            r5[r2] = r7     // Catch:{ all -> 0x00fd }
            r1.debug((java.lang.String) r6, (java.lang.Object[]) r5)     // Catch:{ all -> 0x00fd }
            java.lang.Object r12 = r12.content()     // Catch:{ all -> 0x00fd }
            io.netty.buffer.ByteBuf r12 = (io.netty.buffer.ByteBuf) r12     // Catch:{ all -> 0x00fd }
            int r1 = r8.getPort()     // Catch:{ all -> 0x00fd }
            int r2 = r7.getPort()     // Catch:{ all -> 0x00fd }
            io.netty.handler.pcap.UDPPacket.writePacket(r0, r12, r1, r2)     // Catch:{ all -> 0x00fd }
            io.netty.buffer.ByteBufAllocator r5 = r11.alloc()     // Catch:{ all -> 0x00fd }
            r1 = r10
            r2 = r8
            r3 = r7
            r4 = r0
            r6 = r11
            r1.completeUDPWrite(r2, r3, r4, r5, r6)     // Catch:{ all -> 0x00fd }
            goto L_0x00f9
        L_0x0087:
            boolean r1 = r12 instanceof io.netty.buffer.ByteBuf     // Catch:{ all -> 0x00fd }
            if (r1 == 0) goto L_0x00f2
            io.netty.channel.Channel r1 = r11.channel()     // Catch:{ all -> 0x00fd }
            boolean r1 = r1 instanceof io.netty.channel.socket.DatagramChannel     // Catch:{ all -> 0x00fd }
            if (r1 == 0) goto L_0x009f
            io.netty.channel.Channel r1 = r11.channel()     // Catch:{ all -> 0x00fd }
            io.netty.channel.socket.DatagramChannel r1 = (io.netty.channel.socket.DatagramChannel) r1     // Catch:{ all -> 0x00fd }
            boolean r1 = r1.isConnected()     // Catch:{ all -> 0x00fd }
            if (r1 == 0) goto L_0x00f2
        L_0x009f:
            r1 = r12
            io.netty.buffer.ByteBuf r1 = (io.netty.buffer.ByteBuf) r1     // Catch:{ all -> 0x00fd }
            int r1 = r1.readableBytes()     // Catch:{ all -> 0x00fd }
            if (r1 != 0) goto L_0x00b5
            boolean r1 = r10.captureZeroByte     // Catch:{ all -> 0x00fd }
            if (r1 != 0) goto L_0x00b5
            io.netty.util.internal.logging.InternalLogger r11 = r10.logger     // Catch:{ all -> 0x00fd }
            r11.debug((java.lang.String) r7)     // Catch:{ all -> 0x00fd }
            r0.release()
            return
        L_0x00b5:
            io.netty.buffer.ByteBuf r12 = (io.netty.buffer.ByteBuf) r12     // Catch:{ all -> 0x00fd }
            io.netty.buffer.ByteBuf r12 = r12.duplicate()     // Catch:{ all -> 0x00fd }
            io.netty.util.internal.logging.InternalLogger r1 = r10.logger     // Catch:{ all -> 0x00fd }
            int r7 = r12.readableBytes()     // Catch:{ all -> 0x00fd }
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)     // Catch:{ all -> 0x00fd }
            java.net.InetSocketAddress r8 = r10.initiatorAddr     // Catch:{ all -> 0x00fd }
            java.net.InetSocketAddress r9 = r10.handlerAddr     // Catch:{ all -> 0x00fd }
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch:{ all -> 0x00fd }
            r5[r4] = r7     // Catch:{ all -> 0x00fd }
            r5[r3] = r8     // Catch:{ all -> 0x00fd }
            r5[r2] = r9     // Catch:{ all -> 0x00fd }
            r1.debug((java.lang.String) r6, (java.lang.Object[]) r5)     // Catch:{ all -> 0x00fd }
            java.net.InetSocketAddress r1 = r10.initiatorAddr     // Catch:{ all -> 0x00fd }
            int r1 = r1.getPort()     // Catch:{ all -> 0x00fd }
            java.net.InetSocketAddress r2 = r10.handlerAddr     // Catch:{ all -> 0x00fd }
            int r2 = r2.getPort()     // Catch:{ all -> 0x00fd }
            io.netty.handler.pcap.UDPPacket.writePacket(r0, r12, r1, r2)     // Catch:{ all -> 0x00fd }
            java.net.InetSocketAddress r2 = r10.initiatorAddr     // Catch:{ all -> 0x00fd }
            java.net.InetSocketAddress r3 = r10.handlerAddr     // Catch:{ all -> 0x00fd }
            io.netty.buffer.ByteBufAllocator r5 = r11.alloc()     // Catch:{ all -> 0x00fd }
            r1 = r10
            r4 = r0
            r6 = r11
            r1.completeUDPWrite(r2, r3, r4, r5, r6)     // Catch:{ all -> 0x00fd }
            goto L_0x00f9
        L_0x00f2:
            io.netty.util.internal.logging.InternalLogger r11 = r10.logger     // Catch:{ all -> 0x00fd }
            java.lang.String r1 = "Discarding Pcap Write for UDP Object: {}"
            r11.debug((java.lang.String) r1, (java.lang.Object) r12)     // Catch:{ all -> 0x00fd }
        L_0x00f9:
            r0.release()
            return
        L_0x00fd:
            r11 = move-exception
            r0.release()
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.pcap.PcapWriteHandler.handleUDP(io.netty.channel.ChannelHandlerContext, java.lang.Object):void");
    }

    private void completeUDPWrite(InetSocketAddress inetSocketAddress, InetSocketAddress inetSocketAddress2, ByteBuf byteBuf, ByteBufAllocator byteBufAllocator, ChannelHandlerContext channelHandlerContext) {
        ByteBuf buffer = byteBufAllocator.buffer();
        ByteBuf buffer2 = byteBufAllocator.buffer();
        ByteBuf buffer3 = byteBufAllocator.buffer();
        try {
            if ((inetSocketAddress.getAddress() instanceof Inet4Address) && (inetSocketAddress2.getAddress() instanceof Inet4Address)) {
                IPPacket.writeUDPv4(buffer, byteBuf, NetUtil.ipv4AddressToInt((Inet4Address) inetSocketAddress.getAddress()), NetUtil.ipv4AddressToInt((Inet4Address) inetSocketAddress2.getAddress()));
                EthernetPacket.writeIPv4(buffer2, buffer);
            } else if (!(inetSocketAddress.getAddress() instanceof Inet6Address) || !(inetSocketAddress2.getAddress() instanceof Inet6Address)) {
                this.logger.error("Source and Destination IP Address versions are not same. Source Address: {}, Destination Address: {}", inetSocketAddress.getAddress(), inetSocketAddress2.getAddress());
                buffer.release();
                buffer2.release();
                buffer3.release();
                return;
            } else {
                IPPacket.writeUDPv6(buffer, byteBuf, inetSocketAddress.getAddress().getAddress(), inetSocketAddress2.getAddress().getAddress());
                EthernetPacket.writeIPv6(buffer2, buffer);
            }
            this.pCapWriter.writePacket(buffer3, buffer2);
        } catch (IOException e) {
            this.logger.error("Caught Exception While Writing Packet into Pcap", (Throwable) e);
            channelHandlerContext.fireExceptionCaught(e);
        } catch (Throwable th) {
            buffer.release();
            buffer2.release();
            buffer3.release();
            throw th;
        }
        buffer.release();
        buffer2.release();
        buffer3.release();
    }

    private static InetSocketAddress getLocalAddress(Channel channel, InetSocketAddress inetSocketAddress) {
        InetSocketAddress inetSocketAddress2 = (InetSocketAddress) channel.localAddress();
        if (inetSocketAddress != null && inetSocketAddress2.getAddress().isAnyLocalAddress()) {
            if ((inetSocketAddress2.getAddress() instanceof Inet4Address) && (inetSocketAddress.getAddress() instanceof Inet6Address)) {
                return new InetSocketAddress(WildcardAddressHolder.wildcard6, inetSocketAddress2.getPort());
            }
            if ((inetSocketAddress2.getAddress() instanceof Inet6Address) && (inetSocketAddress.getAddress() instanceof Inet4Address)) {
                return new InetSocketAddress(WildcardAddressHolder.wildcard4, inetSocketAddress2.getPort());
            }
        }
        return inetSocketAddress2;
    }

    /* JADX INFO: finally extract failed */
    public void handlerRemoved(ChannelHandlerContext channelHandlerContext) throws Exception {
        if (this.channelType == ChannelType.TCP) {
            this.logger.debug("Starting Fake TCP FIN+ACK Flow to close connection");
            ByteBufAllocator alloc = channelHandlerContext.alloc();
            ByteBuf buffer = alloc.buffer();
            try {
                TCPPacket.writePacket(buffer, (ByteBuf) null, this.sendSegmentNumber, this.receiveSegmentNumber, this.initiatorAddr.getPort(), this.handlerAddr.getPort(), TCPPacket.TCPFlag.FIN, TCPPacket.TCPFlag.ACK);
                completeTCPWrite(this.initiatorAddr, this.handlerAddr, buffer, alloc, channelHandlerContext);
                TCPPacket.writePacket(buffer, (ByteBuf) null, this.receiveSegmentNumber, this.sendSegmentNumber, this.handlerAddr.getPort(), this.initiatorAddr.getPort(), TCPPacket.TCPFlag.FIN, TCPPacket.TCPFlag.ACK);
                completeTCPWrite(this.handlerAddr, this.initiatorAddr, buffer, alloc, channelHandlerContext);
                TCPPacket.writePacket(buffer, (ByteBuf) null, this.sendSegmentNumber + 1, this.receiveSegmentNumber + 1, this.initiatorAddr.getPort(), this.handlerAddr.getPort(), TCPPacket.TCPFlag.ACK);
                completeTCPWrite(this.initiatorAddr, this.handlerAddr, buffer, alloc, channelHandlerContext);
                buffer.release();
                this.logger.debug("Finished Fake TCP FIN+ACK Flow to close connection");
            } catch (Throwable th) {
                buffer.release();
                throw th;
            }
        }
        close();
        super.handlerRemoved(channelHandlerContext);
    }

    /* JADX INFO: finally extract failed */
    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable th) throws Exception {
        if (this.channelType == ChannelType.TCP) {
            ByteBuf buffer = channelHandlerContext.alloc().buffer();
            try {
                TCPPacket.writePacket(buffer, (ByteBuf) null, this.sendSegmentNumber, this.receiveSegmentNumber, this.initiatorAddr.getPort(), this.handlerAddr.getPort(), TCPPacket.TCPFlag.RST, TCPPacket.TCPFlag.ACK);
                completeTCPWrite(this.initiatorAddr, this.handlerAddr, buffer, channelHandlerContext.alloc(), channelHandlerContext);
                buffer.release();
                this.logger.debug("Sent Fake TCP RST to close connection");
            } catch (Throwable th2) {
                buffer.release();
                throw th2;
            }
        }
        close();
        channelHandlerContext.fireExceptionCaught(th);
    }

    private void logTCP(boolean z, int i, int i2, int i3, InetSocketAddress inetSocketAddress, InetSocketAddress inetSocketAddress2, boolean z2) {
        if (!this.logger.isDebugEnabled()) {
            return;
        }
        if (z2) {
            this.logger.debug("Writing TCP ACK, isWriteOperation {}, Segment Number {}, Ack Number {}, Src Addr {}, Dst Addr {}", Boolean.valueOf(z), Integer.valueOf(i2), Integer.valueOf(i3), inetSocketAddress2, inetSocketAddress);
            return;
        }
        this.logger.debug("Writing TCP Data of {} Bytes, isWriteOperation {}, Segment Number {}, Ack Number {}, Src Addr {}, Dst Addr {}", Integer.valueOf(i), Boolean.valueOf(z), Integer.valueOf(i2), Integer.valueOf(i3), inetSocketAddress, inetSocketAddress2);
    }

    /* access modifiers changed from: package-private */
    public OutputStream outputStream() {
        return this.outputStream;
    }

    /* access modifiers changed from: package-private */
    public boolean sharedOutputStream() {
        return this.sharedOutputStream;
    }

    public boolean isWriting() {
        return this.state.get() == State.WRITING;
    }

    /* access modifiers changed from: package-private */
    public State state() {
        return this.state.get();
    }

    public void pause() {
        if (!LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(this.state, State.WRITING, State.PAUSED)) {
            throw new IllegalStateException("State must be 'STARTED' to pause but current state is: " + this.state);
        }
    }

    public void resume() {
        if (!LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(this.state, State.PAUSED, State.WRITING)) {
            throw new IllegalStateException("State must be 'PAUSED' to resume but current state is: " + this.state);
        }
    }

    /* access modifiers changed from: package-private */
    public void markClosed() {
        if (this.state.get() != State.CLOSED) {
            this.state.set(State.CLOSED);
        }
    }

    /* access modifiers changed from: package-private */
    public PcapWriter pCapWriter() {
        return this.pCapWriter;
    }

    private void logDiscard() {
        this.logger.warn("Discarding pcap write because channel type is unknown. The channel this handler is registered on is not a SocketChannel or DatagramChannel, so the inference does not work. Please call forceTcpChannel or forceUdpChannel before registering the handler.");
    }

    public String toString() {
        return "PcapWriteHandler{captureZeroByte=" + this.captureZeroByte + ", writePcapGlobalHeader=" + this.writePcapGlobalHeader + ", sharedOutputStream=" + this.sharedOutputStream + ", sendSegmentNumber=" + this.sendSegmentNumber + ", receiveSegmentNumber=" + this.receiveSegmentNumber + ", channelType=" + this.channelType + ", initiatorAddr=" + this.initiatorAddr + ", handlerAddr=" + this.handlerAddr + ", isServerPipeline=" + this.isServerPipeline + ", state=" + this.state + AbstractJsonLexerKt.END_OBJ;
    }

    public void close() throws IOException {
        if (this.state.get() == State.CLOSED) {
            this.logger.debug("PcapWriterHandler is already closed");
            return;
        }
        this.pCapWriter.close();
        markClosed();
        this.logger.debug("PcapWriterHandler is now closed");
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        /* access modifiers changed from: private */
        public boolean captureZeroByte;
        /* access modifiers changed from: private */
        public ChannelType channelType;
        /* access modifiers changed from: private */
        public InetSocketAddress handlerAddr;
        /* access modifiers changed from: private */
        public InetSocketAddress initiatorAddr;
        /* access modifiers changed from: private */
        public boolean isServerPipeline;
        /* access modifiers changed from: private */
        public boolean sharedOutputStream;
        /* access modifiers changed from: private */
        public boolean writePcapGlobalHeader;

        private Builder() {
            this.writePcapGlobalHeader = true;
        }

        public Builder captureZeroByte(boolean z) {
            this.captureZeroByte = z;
            return this;
        }

        public Builder sharedOutputStream(boolean z) {
            this.sharedOutputStream = z;
            return this;
        }

        public Builder writePcapGlobalHeader(boolean z) {
            this.writePcapGlobalHeader = z;
            return this;
        }

        public Builder forceTcpChannel(InetSocketAddress inetSocketAddress, InetSocketAddress inetSocketAddress2, boolean z) {
            this.channelType = ChannelType.TCP;
            this.handlerAddr = (InetSocketAddress) ObjectUtil.checkNotNull(inetSocketAddress, "serverAddress");
            this.initiatorAddr = (InetSocketAddress) ObjectUtil.checkNotNull(inetSocketAddress2, "clientAddress");
            this.isServerPipeline = z;
            return this;
        }

        public Builder forceUdpChannel(InetSocketAddress inetSocketAddress, InetSocketAddress inetSocketAddress2) {
            this.channelType = ChannelType.UDP;
            this.handlerAddr = (InetSocketAddress) ObjectUtil.checkNotNull(inetSocketAddress2, "remoteAddress");
            this.initiatorAddr = (InetSocketAddress) ObjectUtil.checkNotNull(inetSocketAddress, "localAddress");
            return this;
        }

        public PcapWriteHandler build(OutputStream outputStream) {
            ObjectUtil.checkNotNull(outputStream, "outputStream");
            return new PcapWriteHandler(this, outputStream);
        }
    }

    private static final class WildcardAddressHolder {
        static final InetAddress wildcard4;
        static final InetAddress wildcard6;

        private WildcardAddressHolder() {
        }

        static {
            try {
                wildcard4 = InetAddress.getByAddress(new byte[4]);
                wildcard6 = InetAddress.getByAddress(new byte[16]);
            } catch (UnknownHostException e) {
                throw new AssertionError(e);
            }
        }
    }
}
