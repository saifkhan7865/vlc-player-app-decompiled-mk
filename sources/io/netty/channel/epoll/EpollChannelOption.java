package io.netty.channel.epoll;

import io.netty.channel.ChannelOption;
import io.netty.channel.unix.UnixChannelOption;
import java.net.InetAddress;
import java.util.Map;

public final class EpollChannelOption<T> extends UnixChannelOption<T> {
    public static final ChannelOption<EpollMode> EPOLL_MODE;
    public static final ChannelOption<Boolean> IP_FREEBIND = valueOf("IP_FREEBIND");
    public static final ChannelOption<Boolean> IP_RECVORIGDSTADDR = valueOf("IP_RECVORIGDSTADDR");
    public static final ChannelOption<Boolean> IP_TRANSPARENT = valueOf("IP_TRANSPARENT");
    public static final ChannelOption<Integer> MAX_DATAGRAM_PAYLOAD_SIZE = valueOf("MAX_DATAGRAM_PAYLOAD_SIZE");
    public static final ChannelOption<Integer> SO_BUSY_POLL;
    public static final ChannelOption<Boolean> TCP_CORK;
    public static final ChannelOption<Integer> TCP_DEFER_ACCEPT;
    @Deprecated
    public static final ChannelOption<Integer> TCP_FASTOPEN = ChannelOption.TCP_FASTOPEN;
    @Deprecated
    public static final ChannelOption<Boolean> TCP_FASTOPEN_CONNECT = ChannelOption.TCP_FASTOPEN_CONNECT;
    public static final ChannelOption<Integer> TCP_KEEPCNT;
    public static final ChannelOption<Integer> TCP_KEEPIDLE;
    public static final ChannelOption<Integer> TCP_KEEPINTVL;
    public static final ChannelOption<Map<InetAddress, byte[]>> TCP_MD5SIG = valueOf("TCP_MD5SIG");
    public static final ChannelOption<Long> TCP_NOTSENT_LOWAT;
    public static final ChannelOption<Boolean> TCP_QUICKACK;
    public static final ChannelOption<Integer> TCP_USER_TIMEOUT;
    public static final ChannelOption<Boolean> UDP_GRO = valueOf("UDP_GRO");

    static {
        Class<EpollChannelOption> cls = EpollChannelOption.class;
        TCP_CORK = valueOf(cls, "TCP_CORK");
        TCP_NOTSENT_LOWAT = valueOf(cls, "TCP_NOTSENT_LOWAT");
        TCP_KEEPIDLE = valueOf(cls, "TCP_KEEPIDLE");
        TCP_KEEPINTVL = valueOf(cls, "TCP_KEEPINTVL");
        TCP_KEEPCNT = valueOf(cls, "TCP_KEEPCNT");
        TCP_USER_TIMEOUT = valueOf(cls, "TCP_USER_TIMEOUT");
        TCP_DEFER_ACCEPT = ChannelOption.valueOf(cls, "TCP_DEFER_ACCEPT");
        TCP_QUICKACK = valueOf(cls, "TCP_QUICKACK");
        SO_BUSY_POLL = valueOf(cls, "SO_BUSY_POLL");
        EPOLL_MODE = ChannelOption.valueOf(cls, "EPOLL_MODE");
    }

    private EpollChannelOption() {
    }
}
