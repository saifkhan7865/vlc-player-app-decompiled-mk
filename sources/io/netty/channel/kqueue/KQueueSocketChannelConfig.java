package io.netty.channel.kqueue;

import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelOption;
import io.netty.channel.MessageSizeEstimator;
import io.netty.channel.RecvByteBufAllocator;
import io.netty.channel.WriteBufferWaterMark;
import io.netty.channel.socket.SocketChannelConfig;
import io.netty.util.internal.PlatformDependent;
import java.io.IOException;
import java.util.Map;

public final class KQueueSocketChannelConfig extends KQueueChannelConfig implements SocketChannelConfig {
    private volatile boolean allowHalfClosure;
    private volatile boolean tcpFastopen;

    public KQueueSocketChannelConfig setPerformancePreferences(int i, int i2, int i3) {
        return this;
    }

    KQueueSocketChannelConfig(KQueueSocketChannel kQueueSocketChannel) {
        super(kQueueSocketChannel);
        if (PlatformDependent.canEnableTcpNoDelayByDefault()) {
            setTcpNoDelay(true);
        }
        calculateMaxBytesPerGatheringWrite();
    }

    public Map<ChannelOption<?>, Object> getOptions() {
        return getOptions(super.getOptions(), ChannelOption.SO_RCVBUF, ChannelOption.SO_SNDBUF, ChannelOption.TCP_NODELAY, ChannelOption.SO_KEEPALIVE, ChannelOption.SO_REUSEADDR, ChannelOption.SO_LINGER, ChannelOption.IP_TOS, ChannelOption.ALLOW_HALF_CLOSURE, KQueueChannelOption.SO_SNDLOWAT, KQueueChannelOption.TCP_NOPUSH);
    }

    public <T> T getOption(ChannelOption<T> channelOption) {
        if (channelOption == ChannelOption.SO_RCVBUF) {
            return Integer.valueOf(getReceiveBufferSize());
        }
        if (channelOption == ChannelOption.SO_SNDBUF) {
            return Integer.valueOf(getSendBufferSize());
        }
        if (channelOption == ChannelOption.TCP_NODELAY) {
            return Boolean.valueOf(isTcpNoDelay());
        }
        if (channelOption == ChannelOption.SO_KEEPALIVE) {
            return Boolean.valueOf(isKeepAlive());
        }
        if (channelOption == ChannelOption.SO_REUSEADDR) {
            return Boolean.valueOf(isReuseAddress());
        }
        if (channelOption == ChannelOption.SO_LINGER) {
            return Integer.valueOf(getSoLinger());
        }
        if (channelOption == ChannelOption.IP_TOS) {
            return Integer.valueOf(getTrafficClass());
        }
        if (channelOption == ChannelOption.ALLOW_HALF_CLOSURE) {
            return Boolean.valueOf(isAllowHalfClosure());
        }
        if (channelOption == KQueueChannelOption.SO_SNDLOWAT) {
            return Integer.valueOf(getSndLowAt());
        }
        if (channelOption == KQueueChannelOption.TCP_NOPUSH) {
            return Boolean.valueOf(isTcpNoPush());
        }
        if (channelOption == ChannelOption.TCP_FASTOPEN_CONNECT) {
            return Boolean.valueOf(isTcpFastOpenConnect());
        }
        return super.getOption(channelOption);
    }

    public <T> boolean setOption(ChannelOption<T> channelOption, T t) {
        validate(channelOption, t);
        if (channelOption == ChannelOption.SO_RCVBUF) {
            setReceiveBufferSize(((Integer) t).intValue());
            return true;
        } else if (channelOption == ChannelOption.SO_SNDBUF) {
            setSendBufferSize(((Integer) t).intValue());
            return true;
        } else if (channelOption == ChannelOption.TCP_NODELAY) {
            setTcpNoDelay(((Boolean) t).booleanValue());
            return true;
        } else if (channelOption == ChannelOption.SO_KEEPALIVE) {
            setKeepAlive(((Boolean) t).booleanValue());
            return true;
        } else if (channelOption == ChannelOption.SO_REUSEADDR) {
            setReuseAddress(((Boolean) t).booleanValue());
            return true;
        } else if (channelOption == ChannelOption.SO_LINGER) {
            setSoLinger(((Integer) t).intValue());
            return true;
        } else if (channelOption == ChannelOption.IP_TOS) {
            setTrafficClass(((Integer) t).intValue());
            return true;
        } else if (channelOption == ChannelOption.ALLOW_HALF_CLOSURE) {
            setAllowHalfClosure(((Boolean) t).booleanValue());
            return true;
        } else if (channelOption == KQueueChannelOption.SO_SNDLOWAT) {
            setSndLowAt(((Integer) t).intValue());
            return true;
        } else if (channelOption == KQueueChannelOption.TCP_NOPUSH) {
            setTcpNoPush(((Boolean) t).booleanValue());
            return true;
        } else if (channelOption != ChannelOption.TCP_FASTOPEN_CONNECT) {
            return super.setOption(channelOption, t);
        } else {
            setTcpFastOpenConnect(((Boolean) t).booleanValue());
            return true;
        }
    }

    public int getReceiveBufferSize() {
        try {
            return ((KQueueSocketChannel) this.channel).socket.getReceiveBufferSize();
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public int getSendBufferSize() {
        try {
            return ((KQueueSocketChannel) this.channel).socket.getSendBufferSize();
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public int getSoLinger() {
        try {
            return ((KQueueSocketChannel) this.channel).socket.getSoLinger();
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public int getTrafficClass() {
        try {
            return ((KQueueSocketChannel) this.channel).socket.getTrafficClass();
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public boolean isKeepAlive() {
        try {
            return ((KQueueSocketChannel) this.channel).socket.isKeepAlive();
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public boolean isReuseAddress() {
        try {
            return ((KQueueSocketChannel) this.channel).socket.isReuseAddress();
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public boolean isTcpNoDelay() {
        try {
            return ((KQueueSocketChannel) this.channel).socket.isTcpNoDelay();
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public int getSndLowAt() {
        try {
            return ((KQueueSocketChannel) this.channel).socket.getSndLowAt();
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public void setSndLowAt(int i) {
        try {
            ((KQueueSocketChannel) this.channel).socket.setSndLowAt(i);
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public boolean isTcpNoPush() {
        try {
            return ((KQueueSocketChannel) this.channel).socket.isTcpNoPush();
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public void setTcpNoPush(boolean z) {
        try {
            ((KQueueSocketChannel) this.channel).socket.setTcpNoPush(z);
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public KQueueSocketChannelConfig setKeepAlive(boolean z) {
        try {
            ((KQueueSocketChannel) this.channel).socket.setKeepAlive(z);
            return this;
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public KQueueSocketChannelConfig setReceiveBufferSize(int i) {
        try {
            ((KQueueSocketChannel) this.channel).socket.setReceiveBufferSize(i);
            return this;
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public KQueueSocketChannelConfig setReuseAddress(boolean z) {
        try {
            ((KQueueSocketChannel) this.channel).socket.setReuseAddress(z);
            return this;
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public KQueueSocketChannelConfig setSendBufferSize(int i) {
        try {
            ((KQueueSocketChannel) this.channel).socket.setSendBufferSize(i);
            calculateMaxBytesPerGatheringWrite();
            return this;
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public KQueueSocketChannelConfig setSoLinger(int i) {
        try {
            ((KQueueSocketChannel) this.channel).socket.setSoLinger(i);
            return this;
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public KQueueSocketChannelConfig setTcpNoDelay(boolean z) {
        try {
            ((KQueueSocketChannel) this.channel).socket.setTcpNoDelay(z);
            return this;
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public KQueueSocketChannelConfig setTrafficClass(int i) {
        try {
            ((KQueueSocketChannel) this.channel).socket.setTrafficClass(i);
            return this;
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public boolean isAllowHalfClosure() {
        return this.allowHalfClosure;
    }

    public KQueueSocketChannelConfig setTcpFastOpenConnect(boolean z) {
        this.tcpFastopen = z;
        return this;
    }

    public boolean isTcpFastOpenConnect() {
        return this.tcpFastopen;
    }

    public KQueueSocketChannelConfig setRcvAllocTransportProvidesGuess(boolean z) {
        super.setRcvAllocTransportProvidesGuess(z);
        return this;
    }

    public KQueueSocketChannelConfig setAllowHalfClosure(boolean z) {
        this.allowHalfClosure = z;
        return this;
    }

    public KQueueSocketChannelConfig setConnectTimeoutMillis(int i) {
        super.setConnectTimeoutMillis(i);
        return this;
    }

    @Deprecated
    public KQueueSocketChannelConfig setMaxMessagesPerRead(int i) {
        super.setMaxMessagesPerRead(i);
        return this;
    }

    public KQueueSocketChannelConfig setWriteSpinCount(int i) {
        super.setWriteSpinCount(i);
        return this;
    }

    public KQueueSocketChannelConfig setAllocator(ByteBufAllocator byteBufAllocator) {
        super.setAllocator(byteBufAllocator);
        return this;
    }

    public KQueueSocketChannelConfig setRecvByteBufAllocator(RecvByteBufAllocator recvByteBufAllocator) {
        super.setRecvByteBufAllocator(recvByteBufAllocator);
        return this;
    }

    public KQueueSocketChannelConfig setAutoRead(boolean z) {
        super.setAutoRead(z);
        return this;
    }

    public KQueueSocketChannelConfig setAutoClose(boolean z) {
        super.setAutoClose(z);
        return this;
    }

    @Deprecated
    public KQueueSocketChannelConfig setWriteBufferHighWaterMark(int i) {
        super.setWriteBufferHighWaterMark(i);
        return this;
    }

    @Deprecated
    public KQueueSocketChannelConfig setWriteBufferLowWaterMark(int i) {
        super.setWriteBufferLowWaterMark(i);
        return this;
    }

    public KQueueSocketChannelConfig setWriteBufferWaterMark(WriteBufferWaterMark writeBufferWaterMark) {
        super.setWriteBufferWaterMark(writeBufferWaterMark);
        return this;
    }

    public KQueueSocketChannelConfig setMessageSizeEstimator(MessageSizeEstimator messageSizeEstimator) {
        super.setMessageSizeEstimator(messageSizeEstimator);
        return this;
    }

    private void calculateMaxBytesPerGatheringWrite() {
        int sendBufferSize = getSendBufferSize() << 1;
        if (sendBufferSize > 0) {
            setMaxBytesPerGatheringWrite((long) sendBufferSize);
        }
    }
}
