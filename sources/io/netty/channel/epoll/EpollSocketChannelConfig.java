package io.netty.channel.epoll;

import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelOption;
import io.netty.channel.MessageSizeEstimator;
import io.netty.channel.RecvByteBufAllocator;
import io.netty.channel.WriteBufferWaterMark;
import io.netty.channel.socket.SocketChannelConfig;
import io.netty.util.internal.PlatformDependent;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Map;

public final class EpollSocketChannelConfig extends EpollChannelConfig implements SocketChannelConfig {
    private volatile boolean allowHalfClosure;
    private volatile boolean tcpFastopen;

    public EpollSocketChannelConfig setPerformancePreferences(int i, int i2, int i3) {
        return this;
    }

    EpollSocketChannelConfig(EpollSocketChannel epollSocketChannel) {
        super(epollSocketChannel);
        if (PlatformDependent.canEnableTcpNoDelayByDefault()) {
            setTcpNoDelay(true);
        }
        calculateMaxBytesPerGatheringWrite();
    }

    public Map<ChannelOption<?>, Object> getOptions() {
        return getOptions(super.getOptions(), ChannelOption.SO_RCVBUF, ChannelOption.SO_SNDBUF, ChannelOption.TCP_NODELAY, ChannelOption.SO_KEEPALIVE, ChannelOption.SO_REUSEADDR, ChannelOption.SO_LINGER, ChannelOption.IP_TOS, ChannelOption.ALLOW_HALF_CLOSURE, EpollChannelOption.TCP_CORK, EpollChannelOption.TCP_NOTSENT_LOWAT, EpollChannelOption.TCP_KEEPCNT, EpollChannelOption.TCP_KEEPIDLE, EpollChannelOption.TCP_KEEPINTVL, EpollChannelOption.TCP_MD5SIG, EpollChannelOption.TCP_QUICKACK, EpollChannelOption.IP_TRANSPARENT, ChannelOption.TCP_FASTOPEN_CONNECT, EpollChannelOption.SO_BUSY_POLL);
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
        if (channelOption == EpollChannelOption.TCP_CORK) {
            return Boolean.valueOf(isTcpCork());
        }
        if (channelOption == EpollChannelOption.TCP_NOTSENT_LOWAT) {
            return Long.valueOf(getTcpNotSentLowAt());
        }
        if (channelOption == EpollChannelOption.TCP_KEEPIDLE) {
            return Integer.valueOf(getTcpKeepIdle());
        }
        if (channelOption == EpollChannelOption.TCP_KEEPINTVL) {
            return Integer.valueOf(getTcpKeepIntvl());
        }
        if (channelOption == EpollChannelOption.TCP_KEEPCNT) {
            return Integer.valueOf(getTcpKeepCnt());
        }
        if (channelOption == EpollChannelOption.TCP_USER_TIMEOUT) {
            return Integer.valueOf(getTcpUserTimeout());
        }
        if (channelOption == EpollChannelOption.TCP_QUICKACK) {
            return Boolean.valueOf(isTcpQuickAck());
        }
        if (channelOption == EpollChannelOption.IP_TRANSPARENT) {
            return Boolean.valueOf(isIpTransparent());
        }
        if (channelOption == ChannelOption.TCP_FASTOPEN_CONNECT) {
            return Boolean.valueOf(isTcpFastOpenConnect());
        }
        if (channelOption == EpollChannelOption.SO_BUSY_POLL) {
            return Integer.valueOf(getSoBusyPoll());
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
        } else if (channelOption == EpollChannelOption.TCP_CORK) {
            setTcpCork(((Boolean) t).booleanValue());
            return true;
        } else if (channelOption == EpollChannelOption.TCP_NOTSENT_LOWAT) {
            setTcpNotSentLowAt(((Long) t).longValue());
            return true;
        } else if (channelOption == EpollChannelOption.TCP_KEEPIDLE) {
            setTcpKeepIdle(((Integer) t).intValue());
            return true;
        } else if (channelOption == EpollChannelOption.TCP_KEEPCNT) {
            setTcpKeepCnt(((Integer) t).intValue());
            return true;
        } else if (channelOption == EpollChannelOption.TCP_KEEPINTVL) {
            setTcpKeepIntvl(((Integer) t).intValue());
            return true;
        } else if (channelOption == EpollChannelOption.TCP_USER_TIMEOUT) {
            setTcpUserTimeout(((Integer) t).intValue());
            return true;
        } else if (channelOption == EpollChannelOption.IP_TRANSPARENT) {
            setIpTransparent(((Boolean) t).booleanValue());
            return true;
        } else if (channelOption == EpollChannelOption.TCP_MD5SIG) {
            setTcpMd5Sig((Map) t);
            return true;
        } else if (channelOption == EpollChannelOption.TCP_QUICKACK) {
            setTcpQuickAck(((Boolean) t).booleanValue());
            return true;
        } else if (channelOption == ChannelOption.TCP_FASTOPEN_CONNECT) {
            setTcpFastOpenConnect(((Boolean) t).booleanValue());
            return true;
        } else if (channelOption != EpollChannelOption.SO_BUSY_POLL) {
            return super.setOption(channelOption, t);
        } else {
            setSoBusyPoll(((Integer) t).intValue());
            return true;
        }
    }

    public int getReceiveBufferSize() {
        try {
            return ((EpollSocketChannel) this.channel).socket.getReceiveBufferSize();
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public int getSendBufferSize() {
        try {
            return ((EpollSocketChannel) this.channel).socket.getSendBufferSize();
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public int getSoLinger() {
        try {
            return ((EpollSocketChannel) this.channel).socket.getSoLinger();
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public int getTrafficClass() {
        try {
            return ((EpollSocketChannel) this.channel).socket.getTrafficClass();
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public boolean isKeepAlive() {
        try {
            return ((EpollSocketChannel) this.channel).socket.isKeepAlive();
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public boolean isReuseAddress() {
        try {
            return ((EpollSocketChannel) this.channel).socket.isReuseAddress();
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public boolean isTcpNoDelay() {
        try {
            return ((EpollSocketChannel) this.channel).socket.isTcpNoDelay();
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public boolean isTcpCork() {
        try {
            return ((EpollSocketChannel) this.channel).socket.isTcpCork();
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public int getSoBusyPoll() {
        try {
            return ((EpollSocketChannel) this.channel).socket.getSoBusyPoll();
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public long getTcpNotSentLowAt() {
        try {
            return ((EpollSocketChannel) this.channel).socket.getTcpNotSentLowAt();
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public int getTcpKeepIdle() {
        try {
            return ((EpollSocketChannel) this.channel).socket.getTcpKeepIdle();
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public int getTcpKeepIntvl() {
        try {
            return ((EpollSocketChannel) this.channel).socket.getTcpKeepIntvl();
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public int getTcpKeepCnt() {
        try {
            return ((EpollSocketChannel) this.channel).socket.getTcpKeepCnt();
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public int getTcpUserTimeout() {
        try {
            return ((EpollSocketChannel) this.channel).socket.getTcpUserTimeout();
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public EpollSocketChannelConfig setKeepAlive(boolean z) {
        try {
            ((EpollSocketChannel) this.channel).socket.setKeepAlive(z);
            return this;
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public EpollSocketChannelConfig setReceiveBufferSize(int i) {
        try {
            ((EpollSocketChannel) this.channel).socket.setReceiveBufferSize(i);
            return this;
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public EpollSocketChannelConfig setReuseAddress(boolean z) {
        try {
            ((EpollSocketChannel) this.channel).socket.setReuseAddress(z);
            return this;
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public EpollSocketChannelConfig setSendBufferSize(int i) {
        try {
            ((EpollSocketChannel) this.channel).socket.setSendBufferSize(i);
            calculateMaxBytesPerGatheringWrite();
            return this;
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public EpollSocketChannelConfig setSoLinger(int i) {
        try {
            ((EpollSocketChannel) this.channel).socket.setSoLinger(i);
            return this;
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public EpollSocketChannelConfig setTcpNoDelay(boolean z) {
        try {
            ((EpollSocketChannel) this.channel).socket.setTcpNoDelay(z);
            return this;
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public EpollSocketChannelConfig setTcpCork(boolean z) {
        try {
            ((EpollSocketChannel) this.channel).socket.setTcpCork(z);
            return this;
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public EpollSocketChannelConfig setSoBusyPoll(int i) {
        try {
            ((EpollSocketChannel) this.channel).socket.setSoBusyPoll(i);
            return this;
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public EpollSocketChannelConfig setTcpNotSentLowAt(long j) {
        try {
            ((EpollSocketChannel) this.channel).socket.setTcpNotSentLowAt(j);
            return this;
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public EpollSocketChannelConfig setTrafficClass(int i) {
        try {
            ((EpollSocketChannel) this.channel).socket.setTrafficClass(i);
            return this;
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public EpollSocketChannelConfig setTcpKeepIdle(int i) {
        try {
            ((EpollSocketChannel) this.channel).socket.setTcpKeepIdle(i);
            return this;
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public EpollSocketChannelConfig setTcpKeepIntvl(int i) {
        try {
            ((EpollSocketChannel) this.channel).socket.setTcpKeepIntvl(i);
            return this;
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    @Deprecated
    public EpollSocketChannelConfig setTcpKeepCntl(int i) {
        return setTcpKeepCnt(i);
    }

    public EpollSocketChannelConfig setTcpKeepCnt(int i) {
        try {
            ((EpollSocketChannel) this.channel).socket.setTcpKeepCnt(i);
            return this;
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public EpollSocketChannelConfig setTcpUserTimeout(int i) {
        try {
            ((EpollSocketChannel) this.channel).socket.setTcpUserTimeout(i);
            return this;
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public boolean isIpTransparent() {
        try {
            return ((EpollSocketChannel) this.channel).socket.isIpTransparent();
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public EpollSocketChannelConfig setIpTransparent(boolean z) {
        try {
            ((EpollSocketChannel) this.channel).socket.setIpTransparent(z);
            return this;
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public EpollSocketChannelConfig setTcpMd5Sig(Map<InetAddress, byte[]> map) {
        try {
            ((EpollSocketChannel) this.channel).setTcpMd5Sig(map);
            return this;
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public EpollSocketChannelConfig setTcpQuickAck(boolean z) {
        try {
            ((EpollSocketChannel) this.channel).socket.setTcpQuickAck(z);
            return this;
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public boolean isTcpQuickAck() {
        try {
            return ((EpollSocketChannel) this.channel).socket.isTcpQuickAck();
        } catch (IOException e) {
            throw new ChannelException((Throwable) e);
        }
    }

    public EpollSocketChannelConfig setTcpFastOpenConnect(boolean z) {
        this.tcpFastopen = z;
        return this;
    }

    public boolean isTcpFastOpenConnect() {
        return this.tcpFastopen;
    }

    public boolean isAllowHalfClosure() {
        return this.allowHalfClosure;
    }

    public EpollSocketChannelConfig setAllowHalfClosure(boolean z) {
        this.allowHalfClosure = z;
        return this;
    }

    public EpollSocketChannelConfig setConnectTimeoutMillis(int i) {
        super.setConnectTimeoutMillis(i);
        return this;
    }

    @Deprecated
    public EpollSocketChannelConfig setMaxMessagesPerRead(int i) {
        super.setMaxMessagesPerRead(i);
        return this;
    }

    public EpollSocketChannelConfig setWriteSpinCount(int i) {
        super.setWriteSpinCount(i);
        return this;
    }

    public EpollSocketChannelConfig setAllocator(ByteBufAllocator byteBufAllocator) {
        super.setAllocator(byteBufAllocator);
        return this;
    }

    public EpollSocketChannelConfig setRecvByteBufAllocator(RecvByteBufAllocator recvByteBufAllocator) {
        super.setRecvByteBufAllocator(recvByteBufAllocator);
        return this;
    }

    public EpollSocketChannelConfig setAutoRead(boolean z) {
        super.setAutoRead(z);
        return this;
    }

    public EpollSocketChannelConfig setAutoClose(boolean z) {
        super.setAutoClose(z);
        return this;
    }

    @Deprecated
    public EpollSocketChannelConfig setWriteBufferHighWaterMark(int i) {
        super.setWriteBufferHighWaterMark(i);
        return this;
    }

    @Deprecated
    public EpollSocketChannelConfig setWriteBufferLowWaterMark(int i) {
        super.setWriteBufferLowWaterMark(i);
        return this;
    }

    public EpollSocketChannelConfig setWriteBufferWaterMark(WriteBufferWaterMark writeBufferWaterMark) {
        super.setWriteBufferWaterMark(writeBufferWaterMark);
        return this;
    }

    public EpollSocketChannelConfig setMessageSizeEstimator(MessageSizeEstimator messageSizeEstimator) {
        super.setMessageSizeEstimator(messageSizeEstimator);
        return this;
    }

    public EpollSocketChannelConfig setEpollMode(EpollMode epollMode) {
        super.setEpollMode(epollMode);
        return this;
    }

    private void calculateMaxBytesPerGatheringWrite() {
        int sendBufferSize = getSendBufferSize() << 1;
        if (sendBufferSize > 0) {
            setMaxBytesPerGatheringWrite((long) sendBufferSize);
        }
    }
}
