package io.netty.channel.epoll;

import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelOption;
import io.netty.channel.MessageSizeEstimator;
import io.netty.channel.RecvByteBufAllocator;
import io.netty.channel.ServerChannelRecvByteBufAllocator;
import io.netty.channel.WriteBufferWaterMark;
import io.netty.channel.socket.ServerSocketChannelConfig;
import io.netty.util.NetUtil;
import io.netty.util.internal.ObjectUtil;
import java.io.IOException;
import java.util.Map;

public class EpollServerChannelConfig extends EpollChannelConfig implements ServerSocketChannelConfig {
    private volatile int backlog = NetUtil.SOMAXCONN;
    private volatile int pendingFastOpenRequestsThreshold;

    public EpollServerChannelConfig setPerformancePreferences(int i, int i2, int i3) {
        return this;
    }

    EpollServerChannelConfig(AbstractEpollChannel abstractEpollChannel) {
        super(abstractEpollChannel, new ServerChannelRecvByteBufAllocator());
    }

    public Map<ChannelOption<?>, Object> getOptions() {
        return getOptions(super.getOptions(), ChannelOption.SO_RCVBUF, ChannelOption.SO_REUSEADDR, ChannelOption.SO_BACKLOG, ChannelOption.TCP_FASTOPEN);
    }

    public <T> T getOption(ChannelOption<T> channelOption) {
        if (channelOption == ChannelOption.SO_RCVBUF) {
            return Integer.valueOf(getReceiveBufferSize());
        }
        if (channelOption == ChannelOption.SO_REUSEADDR) {
            return Boolean.valueOf(isReuseAddress());
        }
        if (channelOption == ChannelOption.SO_BACKLOG) {
            return Integer.valueOf(getBacklog());
        }
        if (channelOption == ChannelOption.TCP_FASTOPEN) {
            return Integer.valueOf(getTcpFastopen());
        }
        return super.getOption(channelOption);
    }

    public <T> boolean setOption(ChannelOption<T> channelOption, T t) {
        validate(channelOption, t);
        if (channelOption == ChannelOption.SO_RCVBUF) {
            setReceiveBufferSize(((Integer) t).intValue());
            return true;
        } else if (channelOption == ChannelOption.SO_REUSEADDR) {
            setReuseAddress(((Boolean) t).booleanValue());
            return true;
        } else if (channelOption == ChannelOption.SO_BACKLOG) {
            setBacklog(((Integer) t).intValue());
            return true;
        } else if (channelOption != ChannelOption.TCP_FASTOPEN) {
            return super.setOption(channelOption, t);
        } else {
            setTcpFastopen(((Integer) t).intValue());
            return true;
        }
    }

    public boolean isReuseAddress() {
        try {
            return ((AbstractEpollChannel) this.channel).socket.isReuseAddress();
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public EpollServerChannelConfig setReuseAddress(boolean z) {
        try {
            ((AbstractEpollChannel) this.channel).socket.setReuseAddress(z);
            return this;
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public int getReceiveBufferSize() {
        try {
            return ((AbstractEpollChannel) this.channel).socket.getReceiveBufferSize();
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public EpollServerChannelConfig setReceiveBufferSize(int i) {
        try {
            ((AbstractEpollChannel) this.channel).socket.setReceiveBufferSize(i);
            return this;
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public int getBacklog() {
        return this.backlog;
    }

    public EpollServerChannelConfig setBacklog(int i) {
        ObjectUtil.checkPositiveOrZero(i, "backlog");
        this.backlog = i;
        return this;
    }

    public int getTcpFastopen() {
        return this.pendingFastOpenRequestsThreshold;
    }

    public EpollServerChannelConfig setTcpFastopen(int i) {
        this.pendingFastOpenRequestsThreshold = ObjectUtil.checkPositiveOrZero(i, "pendingFastOpenRequestsThreshold");
        return this;
    }

    public EpollServerChannelConfig setConnectTimeoutMillis(int i) {
        super.setConnectTimeoutMillis(i);
        return this;
    }

    @Deprecated
    public EpollServerChannelConfig setMaxMessagesPerRead(int i) {
        super.setMaxMessagesPerRead(i);
        return this;
    }

    public EpollServerChannelConfig setWriteSpinCount(int i) {
        super.setWriteSpinCount(i);
        return this;
    }

    public EpollServerChannelConfig setAllocator(ByteBufAllocator byteBufAllocator) {
        super.setAllocator(byteBufAllocator);
        return this;
    }

    public EpollServerChannelConfig setRecvByteBufAllocator(RecvByteBufAllocator recvByteBufAllocator) {
        super.setRecvByteBufAllocator(recvByteBufAllocator);
        return this;
    }

    public EpollServerChannelConfig setAutoRead(boolean z) {
        super.setAutoRead(z);
        return this;
    }

    @Deprecated
    public EpollServerChannelConfig setWriteBufferHighWaterMark(int i) {
        super.setWriteBufferHighWaterMark(i);
        return this;
    }

    @Deprecated
    public EpollServerChannelConfig setWriteBufferLowWaterMark(int i) {
        super.setWriteBufferLowWaterMark(i);
        return this;
    }

    public EpollServerChannelConfig setWriteBufferWaterMark(WriteBufferWaterMark writeBufferWaterMark) {
        super.setWriteBufferWaterMark(writeBufferWaterMark);
        return this;
    }

    public EpollServerChannelConfig setMessageSizeEstimator(MessageSizeEstimator messageSizeEstimator) {
        super.setMessageSizeEstimator(messageSizeEstimator);
        return this;
    }

    public EpollServerChannelConfig setEpollMode(EpollMode epollMode) {
        super.setEpollMode(epollMode);
        return this;
    }
}
