package io.netty.channel.kqueue;

import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelOption;
import io.netty.channel.FixedRecvByteBufAllocator;
import io.netty.channel.MessageSizeEstimator;
import io.netty.channel.RecvByteBufAllocator;
import io.netty.channel.WriteBufferWaterMark;
import io.netty.channel.socket.DatagramChannelConfig;
import io.netty.channel.unix.UnixChannelOption;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Map;

public final class KQueueDatagramChannelConfig extends KQueueChannelConfig implements DatagramChannelConfig {
    private boolean activeOnOpen;

    public InetAddress getInterface() {
        return null;
    }

    public NetworkInterface getNetworkInterface() {
        return null;
    }

    public int getTimeToLive() {
        return -1;
    }

    public boolean isLoopbackModeDisabled() {
        return false;
    }

    KQueueDatagramChannelConfig(KQueueDatagramChannel kQueueDatagramChannel) {
        super(kQueueDatagramChannel);
        setRecvByteBufAllocator((RecvByteBufAllocator) new FixedRecvByteBufAllocator(2048));
    }

    public Map<ChannelOption<?>, Object> getOptions() {
        return getOptions(super.getOptions(), ChannelOption.SO_BROADCAST, ChannelOption.SO_RCVBUF, ChannelOption.SO_SNDBUF, ChannelOption.SO_REUSEADDR, ChannelOption.IP_MULTICAST_LOOP_DISABLED, ChannelOption.IP_MULTICAST_ADDR, ChannelOption.IP_MULTICAST_IF, ChannelOption.IP_MULTICAST_TTL, ChannelOption.IP_TOS, ChannelOption.DATAGRAM_CHANNEL_ACTIVE_ON_REGISTRATION, UnixChannelOption.SO_REUSEPORT);
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
        if (channelOption == UnixChannelOption.SO_REUSEPORT) {
            return Boolean.valueOf(isReusePort());
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
        } else if (channelOption != UnixChannelOption.SO_REUSEPORT) {
            return super.setOption(channelOption, t);
        } else {
            setReusePort(((Boolean) t).booleanValue());
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

    public boolean isReusePort() {
        try {
            return ((KQueueDatagramChannel) this.channel).socket.isReusePort();
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public KQueueDatagramChannelConfig setReusePort(boolean z) {
        try {
            ((KQueueDatagramChannel) this.channel).socket.setReusePort(z);
            return this;
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public KQueueDatagramChannelConfig setRcvAllocTransportProvidesGuess(boolean z) {
        super.setRcvAllocTransportProvidesGuess(z);
        return this;
    }

    public KQueueDatagramChannelConfig setMessageSizeEstimator(MessageSizeEstimator messageSizeEstimator) {
        super.setMessageSizeEstimator(messageSizeEstimator);
        return this;
    }

    @Deprecated
    public KQueueDatagramChannelConfig setWriteBufferLowWaterMark(int i) {
        super.setWriteBufferLowWaterMark(i);
        return this;
    }

    @Deprecated
    public KQueueDatagramChannelConfig setWriteBufferHighWaterMark(int i) {
        super.setWriteBufferHighWaterMark(i);
        return this;
    }

    public KQueueDatagramChannelConfig setWriteBufferWaterMark(WriteBufferWaterMark writeBufferWaterMark) {
        super.setWriteBufferWaterMark(writeBufferWaterMark);
        return this;
    }

    public KQueueDatagramChannelConfig setAutoClose(boolean z) {
        super.setAutoClose(z);
        return this;
    }

    public KQueueDatagramChannelConfig setAutoRead(boolean z) {
        super.setAutoRead(z);
        return this;
    }

    public KQueueDatagramChannelConfig setRecvByteBufAllocator(RecvByteBufAllocator recvByteBufAllocator) {
        super.setRecvByteBufAllocator(recvByteBufAllocator);
        return this;
    }

    public KQueueDatagramChannelConfig setWriteSpinCount(int i) {
        super.setWriteSpinCount(i);
        return this;
    }

    public KQueueDatagramChannelConfig setAllocator(ByteBufAllocator byteBufAllocator) {
        super.setAllocator(byteBufAllocator);
        return this;
    }

    public KQueueDatagramChannelConfig setConnectTimeoutMillis(int i) {
        super.setConnectTimeoutMillis(i);
        return this;
    }

    @Deprecated
    public KQueueDatagramChannelConfig setMaxMessagesPerRead(int i) {
        super.setMaxMessagesPerRead(i);
        return this;
    }

    public int getSendBufferSize() {
        try {
            return ((KQueueDatagramChannel) this.channel).socket.getSendBufferSize();
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public KQueueDatagramChannelConfig setSendBufferSize(int i) {
        try {
            ((KQueueDatagramChannel) this.channel).socket.setSendBufferSize(i);
            return this;
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public int getReceiveBufferSize() {
        try {
            return ((KQueueDatagramChannel) this.channel).socket.getReceiveBufferSize();
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public KQueueDatagramChannelConfig setReceiveBufferSize(int i) {
        try {
            ((KQueueDatagramChannel) this.channel).socket.setReceiveBufferSize(i);
            return this;
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public int getTrafficClass() {
        try {
            return ((KQueueDatagramChannel) this.channel).socket.getTrafficClass();
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public KQueueDatagramChannelConfig setTrafficClass(int i) {
        try {
            ((KQueueDatagramChannel) this.channel).socket.setTrafficClass(i);
            return this;
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public boolean isReuseAddress() {
        try {
            return ((KQueueDatagramChannel) this.channel).socket.isReuseAddress();
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public KQueueDatagramChannelConfig setReuseAddress(boolean z) {
        try {
            ((KQueueDatagramChannel) this.channel).socket.setReuseAddress(z);
            return this;
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public boolean isBroadcast() {
        try {
            return ((KQueueDatagramChannel) this.channel).socket.isBroadcast();
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public KQueueDatagramChannelConfig setBroadcast(boolean z) {
        try {
            ((KQueueDatagramChannel) this.channel).socket.setBroadcast(z);
            return this;
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public DatagramChannelConfig setLoopbackModeDisabled(boolean z) {
        throw new UnsupportedOperationException("Multicast not supported");
    }

    public KQueueDatagramChannelConfig setTimeToLive(int i) {
        throw new UnsupportedOperationException("Multicast not supported");
    }

    public KQueueDatagramChannelConfig setInterface(InetAddress inetAddress) {
        throw new UnsupportedOperationException("Multicast not supported");
    }

    public KQueueDatagramChannelConfig setNetworkInterface(NetworkInterface networkInterface) {
        throw new UnsupportedOperationException("Multicast not supported");
    }

    public KQueueDatagramChannelConfig setMaxMessagesPerWrite(int i) {
        super.setMaxMessagesPerWrite(i);
        return this;
    }
}
