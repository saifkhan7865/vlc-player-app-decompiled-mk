package io.netty.channel.epoll;

import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelOption;
import io.netty.channel.FixedRecvByteBufAllocator;
import io.netty.channel.MessageSizeEstimator;
import io.netty.channel.RecvByteBufAllocator;
import io.netty.channel.WriteBufferWaterMark;
import io.netty.channel.socket.DatagramChannelConfig;
import io.netty.util.internal.ObjectUtil;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Map;

public final class EpollDatagramChannelConfig extends EpollChannelConfig implements DatagramChannelConfig {
    private boolean activeOnOpen;
    private volatile boolean gro;
    private volatile int maxDatagramSize;

    EpollDatagramChannelConfig(EpollDatagramChannel epollDatagramChannel) {
        super(epollDatagramChannel);
        setRecvByteBufAllocator((RecvByteBufAllocator) new FixedRecvByteBufAllocator(2048));
    }

    public Map<ChannelOption<?>, Object> getOptions() {
        return getOptions(super.getOptions(), ChannelOption.SO_BROADCAST, ChannelOption.SO_RCVBUF, ChannelOption.SO_SNDBUF, ChannelOption.SO_REUSEADDR, ChannelOption.IP_MULTICAST_LOOP_DISABLED, ChannelOption.IP_MULTICAST_ADDR, ChannelOption.IP_MULTICAST_IF, ChannelOption.IP_MULTICAST_TTL, ChannelOption.IP_TOS, ChannelOption.DATAGRAM_CHANNEL_ACTIVE_ON_REGISTRATION, EpollChannelOption.SO_REUSEPORT, EpollChannelOption.IP_FREEBIND, EpollChannelOption.IP_TRANSPARENT, EpollChannelOption.IP_RECVORIGDSTADDR, EpollChannelOption.MAX_DATAGRAM_PAYLOAD_SIZE, EpollChannelOption.UDP_GRO);
    }

    public <T> T getOption(ChannelOption<T> channelOption) {
        if (channelOption == ChannelOption.SO_BROADCAST) {
            return Boolean.valueOf(isBroadcast());
        }
        if (channelOption == ChannelOption.SO_RCVBUF) {
            return Integer.valueOf(getReceiveBufferSize());
        }
        if (channelOption == ChannelOption.SO_SNDBUF) {
            return Integer.valueOf(getSendBufferSize());
        }
        if (channelOption == ChannelOption.SO_REUSEADDR) {
            return Boolean.valueOf(isReuseAddress());
        }
        if (channelOption == ChannelOption.IP_MULTICAST_LOOP_DISABLED) {
            return Boolean.valueOf(isLoopbackModeDisabled());
        }
        if (channelOption == ChannelOption.IP_MULTICAST_ADDR) {
            return getInterface();
        }
        if (channelOption == ChannelOption.IP_MULTICAST_IF) {
            return getNetworkInterface();
        }
        if (channelOption == ChannelOption.IP_MULTICAST_TTL) {
            return Integer.valueOf(getTimeToLive());
        }
        if (channelOption == ChannelOption.IP_TOS) {
            return Integer.valueOf(getTrafficClass());
        }
        if (channelOption == ChannelOption.DATAGRAM_CHANNEL_ACTIVE_ON_REGISTRATION) {
            return Boolean.valueOf(this.activeOnOpen);
        }
        if (channelOption == EpollChannelOption.SO_REUSEPORT) {
            return Boolean.valueOf(isReusePort());
        }
        if (channelOption == EpollChannelOption.IP_TRANSPARENT) {
            return Boolean.valueOf(isIpTransparent());
        }
        if (channelOption == EpollChannelOption.IP_FREEBIND) {
            return Boolean.valueOf(isFreeBind());
        }
        if (channelOption == EpollChannelOption.IP_RECVORIGDSTADDR) {
            return Boolean.valueOf(isIpRecvOrigDestAddr());
        }
        if (channelOption == EpollChannelOption.MAX_DATAGRAM_PAYLOAD_SIZE) {
            return Integer.valueOf(getMaxDatagramPayloadSize());
        }
        if (channelOption == EpollChannelOption.UDP_GRO) {
            return Boolean.valueOf(isUdpGro());
        }
        return super.getOption(channelOption);
    }

    public <T> boolean setOption(ChannelOption<T> channelOption, T t) {
        validate(channelOption, t);
        if (channelOption == ChannelOption.SO_BROADCAST) {
            setBroadcast(((Boolean) t).booleanValue());
            return true;
        } else if (channelOption == ChannelOption.SO_RCVBUF) {
            setReceiveBufferSize(((Integer) t).intValue());
            return true;
        } else if (channelOption == ChannelOption.SO_SNDBUF) {
            setSendBufferSize(((Integer) t).intValue());
            return true;
        } else if (channelOption == ChannelOption.SO_REUSEADDR) {
            setReuseAddress(((Boolean) t).booleanValue());
            return true;
        } else if (channelOption == ChannelOption.IP_MULTICAST_LOOP_DISABLED) {
            setLoopbackModeDisabled(((Boolean) t).booleanValue());
            return true;
        } else if (channelOption == ChannelOption.IP_MULTICAST_ADDR) {
            setInterface((InetAddress) t);
            return true;
        } else if (channelOption == ChannelOption.IP_MULTICAST_IF) {
            setNetworkInterface((NetworkInterface) t);
            return true;
        } else if (channelOption == ChannelOption.IP_MULTICAST_TTL) {
            setTimeToLive(((Integer) t).intValue());
            return true;
        } else if (channelOption == ChannelOption.IP_TOS) {
            setTrafficClass(((Integer) t).intValue());
            return true;
        } else if (channelOption == ChannelOption.DATAGRAM_CHANNEL_ACTIVE_ON_REGISTRATION) {
            setActiveOnOpen(((Boolean) t).booleanValue());
            return true;
        } else if (channelOption == EpollChannelOption.SO_REUSEPORT) {
            setReusePort(((Boolean) t).booleanValue());
            return true;
        } else if (channelOption == EpollChannelOption.IP_FREEBIND) {
            setFreeBind(((Boolean) t).booleanValue());
            return true;
        } else if (channelOption == EpollChannelOption.IP_TRANSPARENT) {
            setIpTransparent(((Boolean) t).booleanValue());
            return true;
        } else if (channelOption == EpollChannelOption.IP_RECVORIGDSTADDR) {
            setIpRecvOrigDestAddr(((Boolean) t).booleanValue());
            return true;
        } else if (channelOption == EpollChannelOption.MAX_DATAGRAM_PAYLOAD_SIZE) {
            setMaxDatagramPayloadSize(((Integer) t).intValue());
            return true;
        } else if (channelOption != EpollChannelOption.UDP_GRO) {
            return super.setOption(channelOption, t);
        } else {
            setUdpGro(((Boolean) t).booleanValue());
            return true;
        }
    }

    private void setActiveOnOpen(boolean z) {
        if (!this.channel.isRegistered()) {
            this.activeOnOpen = z;
            return;
        }
        throw new IllegalStateException("Can only changed before channel was registered");
    }

    /* access modifiers changed from: package-private */
    public boolean getActiveOnOpen() {
        return this.activeOnOpen;
    }

    public EpollDatagramChannelConfig setMessageSizeEstimator(MessageSizeEstimator messageSizeEstimator) {
        super.setMessageSizeEstimator(messageSizeEstimator);
        return this;
    }

    @Deprecated
    public EpollDatagramChannelConfig setWriteBufferLowWaterMark(int i) {
        super.setWriteBufferLowWaterMark(i);
        return this;
    }

    @Deprecated
    public EpollDatagramChannelConfig setWriteBufferHighWaterMark(int i) {
        super.setWriteBufferHighWaterMark(i);
        return this;
    }

    public EpollDatagramChannelConfig setWriteBufferWaterMark(WriteBufferWaterMark writeBufferWaterMark) {
        super.setWriteBufferWaterMark(writeBufferWaterMark);
        return this;
    }

    public EpollDatagramChannelConfig setAutoClose(boolean z) {
        super.setAutoClose(z);
        return this;
    }

    public EpollDatagramChannelConfig setAutoRead(boolean z) {
        super.setAutoRead(z);
        return this;
    }

    public EpollDatagramChannelConfig setRecvByteBufAllocator(RecvByteBufAllocator recvByteBufAllocator) {
        super.setRecvByteBufAllocator(recvByteBufAllocator);
        return this;
    }

    public EpollDatagramChannelConfig setWriteSpinCount(int i) {
        super.setWriteSpinCount(i);
        return this;
    }

    public EpollDatagramChannelConfig setAllocator(ByteBufAllocator byteBufAllocator) {
        super.setAllocator(byteBufAllocator);
        return this;
    }

    public EpollDatagramChannelConfig setConnectTimeoutMillis(int i) {
        super.setConnectTimeoutMillis(i);
        return this;
    }

    @Deprecated
    public EpollDatagramChannelConfig setMaxMessagesPerRead(int i) {
        super.setMaxMessagesPerRead(i);
        return this;
    }

    public int getSendBufferSize() {
        try {
            return ((EpollDatagramChannel) this.channel).socket.getSendBufferSize();
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public EpollDatagramChannelConfig setSendBufferSize(int i) {
        try {
            ((EpollDatagramChannel) this.channel).socket.setSendBufferSize(i);
            return this;
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public int getReceiveBufferSize() {
        try {
            return ((EpollDatagramChannel) this.channel).socket.getReceiveBufferSize();
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public EpollDatagramChannelConfig setReceiveBufferSize(int i) {
        try {
            ((EpollDatagramChannel) this.channel).socket.setReceiveBufferSize(i);
            return this;
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public int getTrafficClass() {
        try {
            return ((EpollDatagramChannel) this.channel).socket.getTrafficClass();
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public EpollDatagramChannelConfig setTrafficClass(int i) {
        try {
            ((EpollDatagramChannel) this.channel).socket.setTrafficClass(i);
            return this;
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public boolean isReuseAddress() {
        try {
            return ((EpollDatagramChannel) this.channel).socket.isReuseAddress();
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public EpollDatagramChannelConfig setReuseAddress(boolean z) {
        try {
            ((EpollDatagramChannel) this.channel).socket.setReuseAddress(z);
            return this;
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public boolean isBroadcast() {
        try {
            return ((EpollDatagramChannel) this.channel).socket.isBroadcast();
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public EpollDatagramChannelConfig setBroadcast(boolean z) {
        try {
            ((EpollDatagramChannel) this.channel).socket.setBroadcast(z);
            return this;
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public boolean isLoopbackModeDisabled() {
        try {
            return ((EpollDatagramChannel) this.channel).socket.isLoopbackModeDisabled();
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public DatagramChannelConfig setLoopbackModeDisabled(boolean z) {
        try {
            ((EpollDatagramChannel) this.channel).socket.setLoopbackModeDisabled(z);
            return this;
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public int getTimeToLive() {
        try {
            return ((EpollDatagramChannel) this.channel).socket.getTimeToLive();
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public EpollDatagramChannelConfig setTimeToLive(int i) {
        try {
            ((EpollDatagramChannel) this.channel).socket.setTimeToLive(i);
            return this;
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public InetAddress getInterface() {
        try {
            return ((EpollDatagramChannel) this.channel).socket.getInterface();
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public EpollDatagramChannelConfig setInterface(InetAddress inetAddress) {
        try {
            ((EpollDatagramChannel) this.channel).socket.setInterface(inetAddress);
            return this;
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public NetworkInterface getNetworkInterface() {
        try {
            return ((EpollDatagramChannel) this.channel).socket.getNetworkInterface();
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public EpollDatagramChannelConfig setNetworkInterface(NetworkInterface networkInterface) {
        try {
            ((EpollDatagramChannel) this.channel).socket.setNetworkInterface(networkInterface);
            return this;
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public EpollDatagramChannelConfig setEpollMode(EpollMode epollMode) {
        super.setEpollMode(epollMode);
        return this;
    }

    public boolean isReusePort() {
        try {
            return ((EpollDatagramChannel) this.channel).socket.isReusePort();
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public EpollDatagramChannelConfig setReusePort(boolean z) {
        try {
            ((EpollDatagramChannel) this.channel).socket.setReusePort(z);
            return this;
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public boolean isIpTransparent() {
        try {
            return ((EpollDatagramChannel) this.channel).socket.isIpTransparent();
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public EpollDatagramChannelConfig setIpTransparent(boolean z) {
        try {
            ((EpollDatagramChannel) this.channel).socket.setIpTransparent(z);
            return this;
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public boolean isFreeBind() {
        try {
            return ((EpollDatagramChannel) this.channel).socket.isIpFreeBind();
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public EpollDatagramChannelConfig setFreeBind(boolean z) {
        try {
            ((EpollDatagramChannel) this.channel).socket.setIpFreeBind(z);
            return this;
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public boolean isIpRecvOrigDestAddr() {
        try {
            return ((EpollDatagramChannel) this.channel).socket.isIpRecvOrigDestAddr();
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public EpollDatagramChannelConfig setIpRecvOrigDestAddr(boolean z) {
        try {
            ((EpollDatagramChannel) this.channel).socket.setIpRecvOrigDestAddr(z);
            return this;
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public EpollDatagramChannelConfig setMaxDatagramPayloadSize(int i) {
        this.maxDatagramSize = ObjectUtil.checkPositiveOrZero(i, "maxDatagramSize");
        return this;
    }

    public int getMaxDatagramPayloadSize() {
        return this.maxDatagramSize;
    }

    public EpollDatagramChannelConfig setUdpGro(boolean z) {
        try {
            ((EpollDatagramChannel) this.channel).socket.setUdpGro(z);
            this.gro = z;
            return this;
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public boolean isUdpGro() {
        return this.gro;
    }

    public EpollDatagramChannelConfig setMaxMessagesPerWrite(int i) {
        super.setMaxMessagesPerWrite(i);
        return this;
    }
}
