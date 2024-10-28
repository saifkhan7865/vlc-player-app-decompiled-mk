package io.ktor.network.sockets;

import io.ktor.network.sockets.SocketOptions;
import io.ktor.util.NioPathKt$$ExternalSyntheticApiModelOutline0;
import java.net.StandardSocketOptions;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectableChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0014\u0010\u0004\u001a\u00020\u0005*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0000\u001a\f\u0010\t\u001a\u00020\u0005*\u00020\u0006H\u0000\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003¨\u0006\n"}, d2 = {"java7NetworkApisAvailable", "", "getJava7NetworkApisAvailable", "()Z", "assignOptions", "", "Ljava/nio/channels/SelectableChannel;", "options", "Lio/ktor/network/sockets/SocketOptions;", "nonBlocking", "ktor-network"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: JavaSocketOptions.kt */
public final class JavaSocketOptionsKt {
    private static final boolean java7NetworkApisAvailable;

    public static final boolean getJava7NetworkApisAvailable() {
        return java7NetworkApisAvailable;
    }

    static {
        boolean z;
        try {
            Class.forName("java.net.StandardSocketOptions");
            z = true;
        } catch (ClassNotFoundException unused) {
            z = false;
        }
        java7NetworkApisAvailable = z;
    }

    public static final void nonBlocking(SelectableChannel selectableChannel) {
        Intrinsics.checkNotNullParameter(selectableChannel, "<this>");
        selectableChannel.configureBlocking(false);
    }

    public static final void assignOptions(SelectableChannel selectableChannel, SocketOptions socketOptions) {
        Intrinsics.checkNotNullParameter(selectableChannel, "<this>");
        Intrinsics.checkNotNullParameter(socketOptions, "options");
        Integer num = null;
        if (selectableChannel instanceof SocketChannel) {
            if (!TypeOfService.m1451equalsimpl0(socketOptions.m1445getTypeOfServicezieKYfw(), TypeOfService.Companion.m1461getUNDEFINEDzieKYfw())) {
                if (java7NetworkApisAvailable) {
                    SocketChannel unused = ((SocketChannel) selectableChannel).setOption(NioPathKt$$ExternalSyntheticApiModelOutline0.m(), Integer.valueOf(socketOptions.m1445getTypeOfServicezieKYfw() & 255));
                } else {
                    ((SocketChannel) selectableChannel).socket().setTrafficClass(socketOptions.m1445getTypeOfServicezieKYfw() & 255);
                }
            }
            if (socketOptions.getReuseAddress()) {
                if (java7NetworkApisAvailable) {
                    SocketChannel unused2 = ((SocketChannel) selectableChannel).setOption(StandardSocketOptions.SO_REUSEADDR, true);
                } else {
                    ((SocketChannel) selectableChannel).socket().setReuseAddress(true);
                }
            }
            if (socketOptions.getReusePort()) {
                SocketOptionsPlatformCapabilities.INSTANCE.setReusePort((SocketChannel) selectableChannel);
            }
            if (socketOptions instanceof SocketOptions.PeerSocketOptions) {
                SocketOptions.PeerSocketOptions peerSocketOptions = (SocketOptions.PeerSocketOptions) socketOptions;
                Integer valueOf = Integer.valueOf(peerSocketOptions.getReceiveBufferSize());
                if (valueOf.intValue() <= 0) {
                    valueOf = null;
                }
                if (valueOf != null) {
                    int intValue = valueOf.intValue();
                    if (java7NetworkApisAvailable) {
                        SocketChannel unused3 = ((SocketChannel) selectableChannel).setOption(StandardSocketOptions.SO_RCVBUF, Integer.valueOf(intValue));
                    } else {
                        ((SocketChannel) selectableChannel).socket().setReceiveBufferSize(intValue);
                    }
                }
                Integer valueOf2 = Integer.valueOf(peerSocketOptions.getSendBufferSize());
                if (valueOf2.intValue() <= 0) {
                    valueOf2 = null;
                }
                if (valueOf2 != null) {
                    int intValue2 = valueOf2.intValue();
                    if (java7NetworkApisAvailable) {
                        SocketChannel unused4 = ((SocketChannel) selectableChannel).setOption(NioPathKt$$ExternalSyntheticApiModelOutline0.m$1(), Integer.valueOf(intValue2));
                    } else {
                        ((SocketChannel) selectableChannel).socket().setSendBufferSize(intValue2);
                    }
                }
            }
            if (socketOptions instanceof SocketOptions.TCPClientSocketOptions) {
                SocketOptions.TCPClientSocketOptions tCPClientSocketOptions = (SocketOptions.TCPClientSocketOptions) socketOptions;
                Integer valueOf3 = Integer.valueOf(tCPClientSocketOptions.getLingerSeconds());
                if (valueOf3.intValue() < 0) {
                    valueOf3 = null;
                }
                if (valueOf3 != null) {
                    int intValue3 = valueOf3.intValue();
                    if (java7NetworkApisAvailable) {
                        SocketChannel unused5 = ((SocketChannel) selectableChannel).setOption(NioPathKt$$ExternalSyntheticApiModelOutline0.m$2(), Integer.valueOf(intValue3));
                    } else {
                        ((SocketChannel) selectableChannel).socket().setSoLinger(true, intValue3);
                    }
                }
                Boolean keepAlive = tCPClientSocketOptions.getKeepAlive();
                if (keepAlive != null) {
                    boolean booleanValue = keepAlive.booleanValue();
                    if (java7NetworkApisAvailable) {
                        SocketChannel unused6 = ((SocketChannel) selectableChannel).setOption(StandardSocketOptions.SO_KEEPALIVE, keepAlive);
                    } else {
                        ((SocketChannel) selectableChannel).socket().setKeepAlive(booleanValue);
                    }
                }
                if (java7NetworkApisAvailable) {
                    SocketChannel unused7 = ((SocketChannel) selectableChannel).setOption(StandardSocketOptions.TCP_NODELAY, Boolean.valueOf(tCPClientSocketOptions.getNoDelay()));
                } else {
                    ((SocketChannel) selectableChannel).socket().setTcpNoDelay(tCPClientSocketOptions.getNoDelay());
                }
            }
        }
        if (selectableChannel instanceof ServerSocketChannel) {
            if (socketOptions.getReuseAddress()) {
                if (java7NetworkApisAvailable) {
                    ServerSocketChannel unused8 = ((ServerSocketChannel) selectableChannel).setOption(StandardSocketOptions.SO_REUSEADDR, true);
                } else {
                    ((ServerSocketChannel) selectableChannel).socket().setReuseAddress(true);
                }
            }
            if (socketOptions.getReusePort()) {
                SocketOptionsPlatformCapabilities.INSTANCE.setReusePort((ServerSocketChannel) selectableChannel);
            }
        }
        if (selectableChannel instanceof DatagramChannel) {
            if (!TypeOfService.m1451equalsimpl0(socketOptions.m1445getTypeOfServicezieKYfw(), TypeOfService.Companion.m1461getUNDEFINEDzieKYfw())) {
                if (java7NetworkApisAvailable) {
                    DatagramChannel unused9 = ((DatagramChannel) selectableChannel).setOption(NioPathKt$$ExternalSyntheticApiModelOutline0.m(), Integer.valueOf(socketOptions.m1445getTypeOfServicezieKYfw() & 255));
                } else {
                    ((DatagramChannel) selectableChannel).socket().setTrafficClass(socketOptions.m1445getTypeOfServicezieKYfw() & 255);
                }
            }
            if (socketOptions.getReuseAddress()) {
                if (java7NetworkApisAvailable) {
                    DatagramChannel unused10 = ((DatagramChannel) selectableChannel).setOption(StandardSocketOptions.SO_REUSEADDR, true);
                } else {
                    ((DatagramChannel) selectableChannel).socket().setReuseAddress(true);
                }
            }
            if (socketOptions.getReusePort()) {
                SocketOptionsPlatformCapabilities.INSTANCE.setReusePort((DatagramChannel) selectableChannel);
            }
            if (socketOptions instanceof SocketOptions.UDPSocketOptions) {
                if (java7NetworkApisAvailable) {
                    DatagramChannel unused11 = ((DatagramChannel) selectableChannel).setOption(StandardSocketOptions.SO_BROADCAST, Boolean.valueOf(((SocketOptions.UDPSocketOptions) socketOptions).getBroadcast()));
                } else {
                    ((DatagramChannel) selectableChannel).socket().setBroadcast(((SocketOptions.UDPSocketOptions) socketOptions).getBroadcast());
                }
            }
            if (socketOptions instanceof SocketOptions.PeerSocketOptions) {
                SocketOptions.PeerSocketOptions peerSocketOptions2 = (SocketOptions.PeerSocketOptions) socketOptions;
                Integer valueOf4 = Integer.valueOf(peerSocketOptions2.getReceiveBufferSize());
                if (valueOf4.intValue() <= 0) {
                    valueOf4 = null;
                }
                if (valueOf4 != null) {
                    int intValue4 = valueOf4.intValue();
                    if (java7NetworkApisAvailable) {
                        DatagramChannel unused12 = ((DatagramChannel) selectableChannel).setOption(StandardSocketOptions.SO_RCVBUF, Integer.valueOf(intValue4));
                    } else {
                        ((DatagramChannel) selectableChannel).socket().setReceiveBufferSize(intValue4);
                    }
                }
                Integer valueOf5 = Integer.valueOf(peerSocketOptions2.getSendBufferSize());
                if (valueOf5.intValue() > 0) {
                    num = valueOf5;
                }
                if (num != null) {
                    int intValue5 = num.intValue();
                    if (java7NetworkApisAvailable) {
                        DatagramChannel unused13 = ((DatagramChannel) selectableChannel).setOption(NioPathKt$$ExternalSyntheticApiModelOutline0.m$1(), Integer.valueOf(intValue5));
                    } else {
                        ((DatagramChannel) selectableChannel).socket().setSendBufferSize(intValue5);
                    }
                }
            }
        }
    }
}
