package io.netty.channel.kqueue;

import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelOption;
import io.netty.channel.MessageSizeEstimator;
import io.netty.channel.RecvByteBufAllocator;
import io.netty.channel.WriteBufferWaterMark;
import io.netty.channel.unix.UnixChannelOption;
import java.io.IOException;
import java.util.Map;

public class KQueueServerSocketChannelConfig extends KQueueServerChannelConfig {
    public KQueueServerSocketChannelConfig setPerformancePreferences(int i, int i2, int i3) {
        return this;
    }

    KQueueServerSocketChannelConfig(KQueueServerSocketChannel kQueueServerSocketChannel) {
        super(kQueueServerSocketChannel);
        setReuseAddress(true);
    }

    public Map<ChannelOption<?>, Object> getOptions() {
        return getOptions(super.getOptions(), UnixChannelOption.SO_REUSEPORT, KQueueChannelOption.SO_ACCEPTFILTER);
    }

    public <T> T getOption(ChannelOption<T> channelOption) {
        if (channelOption == UnixChannelOption.SO_REUSEPORT) {
            return Boolean.valueOf(isReusePort());
        }
        if (channelOption == KQueueChannelOption.SO_ACCEPTFILTER) {
            return getAcceptFilter();
        }
        return super.getOption(channelOption);
    }

    public <T> boolean setOption(ChannelOption<T> channelOption, T t) {
        validate(channelOption, t);
        if (channelOption == UnixChannelOption.SO_REUSEPORT) {
            setReusePort(((Boolean) t).booleanValue());
            return true;
        } else if (channelOption != KQueueChannelOption.SO_ACCEPTFILTER) {
            return super.setOption(channelOption, t);
        } else {
            setAcceptFilter((AcceptFilter) t);
            return true;
        }
    }

    public KQueueServerSocketChannelConfig setReusePort(boolean z) {
        try {
            ((KQueueServerSocketChannel) this.channel).socket.setReusePort(z);
            return this;
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public boolean isReusePort() {
        try {
            return ((KQueueServerSocketChannel) this.channel).socket.isReusePort();
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public KQueueServerSocketChannelConfig setAcceptFilter(AcceptFilter acceptFilter) {
        try {
            ((KQueueServerSocketChannel) this.channel).socket.setAcceptFilter(acceptFilter);
            return this;
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public AcceptFilter getAcceptFilter() {
        try {
            return ((KQueueServerSocketChannel) this.channel).socket.getAcceptFilter();
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public KQueueServerSocketChannelConfig setRcvAllocTransportProvidesGuess(boolean z) {
        super.setRcvAllocTransportProvidesGuess(z);
        return this;
    }

    public KQueueServerSocketChannelConfig setReuseAddress(boolean z) {
        super.setReuseAddress(z);
        return this;
    }

    public KQueueServerSocketChannelConfig setReceiveBufferSize(int i) {
        super.setReceiveBufferSize(i);
        return this;
    }

    public KQueueServerSocketChannelConfig setBacklog(int i) {
        super.setBacklog(i);
        return this;
    }

    public KQueueServerSocketChannelConfig setTcpFastOpen(boolean z) {
        super.setTcpFastOpen(z);
        return this;
    }

    public KQueueServerSocketChannelConfig setConnectTimeoutMillis(int i) {
        super.setConnectTimeoutMillis(i);
        return this;
    }

    @Deprecated
    public KQueueServerSocketChannelConfig setMaxMessagesPerRead(int i) {
        super.setMaxMessagesPerRead(i);
        return this;
    }

    public KQueueServerSocketChannelConfig setWriteSpinCount(int i) {
        super.setWriteSpinCount(i);
        return this;
    }

    public KQueueServerSocketChannelConfig setAllocator(ByteBufAllocator byteBufAllocator) {
        super.setAllocator(byteBufAllocator);
        return this;
    }

    public KQueueServerSocketChannelConfig setRecvByteBufAllocator(RecvByteBufAllocator recvByteBufAllocator) {
        super.setRecvByteBufAllocator(recvByteBufAllocator);
        return this;
    }

    public KQueueServerSocketChannelConfig setAutoRead(boolean z) {
        super.setAutoRead(z);
        return this;
    }

    @Deprecated
    public KQueueServerSocketChannelConfig setWriteBufferHighWaterMark(int i) {
        super.setWriteBufferHighWaterMark(i);
        return this;
    }

    @Deprecated
    public KQueueServerSocketChannelConfig setWriteBufferLowWaterMark(int i) {
        super.setWriteBufferLowWaterMark(i);
        return this;
    }

    public KQueueServerSocketChannelConfig setWriteBufferWaterMark(WriteBufferWaterMark writeBufferWaterMark) {
        super.setWriteBufferWaterMark(writeBufferWaterMark);
        return this;
    }

    public KQueueServerSocketChannelConfig setMessageSizeEstimator(MessageSizeEstimator messageSizeEstimator) {
        super.setMessageSizeEstimator(messageSizeEstimator);
        return this;
    }
}
