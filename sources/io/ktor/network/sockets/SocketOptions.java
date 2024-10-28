package io.ktor.network.sockets;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.FrameMetricsAggregator;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010%\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 #2\u00020\u0001:\u0006\"#$%&'B\u001d\b\u0004\u0012\u0014\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0003¢\u0006\u0002\u0010\u0004J\r\u0010\u0017\u001a\u00020\u0018H\u0000¢\u0006\u0002\b\u0019J\r\u0010\u001a\u001a\u00020\u0000H ¢\u0006\u0002\b\u001bJ\u0010\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u0000H\u0014J\r\u0010\u001f\u001a\u00020 H\u0000¢\u0006\u0002\b!R\"\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u001a\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u00020\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\n\"\u0004\b\u000f\u0010\fR%\u0010\u0010\u001a\u00020\u0011X\u000eø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0010\n\u0002\u0010\u0016\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015\u0001\u0003\u0018( \u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006)"}, d2 = {"Lio/ktor/network/sockets/SocketOptions;", "", "customOptions", "", "(Ljava/util/Map;)V", "getCustomOptions", "()Ljava/util/Map;", "reuseAddress", "", "getReuseAddress", "()Z", "setReuseAddress", "(Z)V", "reusePort", "getReusePort", "setReusePort", "typeOfService", "Lio/ktor/network/sockets/TypeOfService;", "getTypeOfService-zieKYfw", "()B", "setTypeOfService-SNCuOGA", "(B)V", "B", "acceptor", "Lio/ktor/network/sockets/SocketOptions$AcceptorOptions;", "acceptor$ktor_network", "copy", "copy$ktor_network", "copyCommon", "", "from", "peer", "Lio/ktor/network/sockets/SocketOptions$PeerSocketOptions;", "peer$ktor_network", "AcceptorOptions", "Companion", "GeneralSocketOptions", "PeerSocketOptions", "TCPClientSocketOptions", "UDPSocketOptions", "Lio/ktor/network/sockets/SocketOptions$GeneralSocketOptions;", "ktor-network"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: SocketOptions.kt */
public abstract class SocketOptions {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final Map<Object, Object> customOptions;
    private boolean reuseAddress;
    private boolean reusePort;
    private byte typeOfService;

    public /* synthetic */ SocketOptions(Map map, DefaultConstructorMarker defaultConstructorMarker) {
        this(map);
    }

    public abstract SocketOptions copy$ktor_network();

    private SocketOptions(Map<Object, Object> map) {
        this.customOptions = map;
        this.typeOfService = TypeOfService.Companion.m1461getUNDEFINEDzieKYfw();
    }

    /* access modifiers changed from: protected */
    public final Map<Object, Object> getCustomOptions() {
        return this.customOptions;
    }

    /* access modifiers changed from: protected */
    public void copyCommon(SocketOptions socketOptions) {
        Intrinsics.checkNotNullParameter(socketOptions, TypedValues.TransitionType.S_FROM);
        this.typeOfService = socketOptions.typeOfService;
        this.reuseAddress = socketOptions.reuseAddress;
        this.reusePort = socketOptions.reusePort;
    }

    public final PeerSocketOptions peer$ktor_network() {
        PeerSocketOptions peerSocketOptions = new PeerSocketOptions(new HashMap(this.customOptions));
        copyCommon(this);
        return peerSocketOptions;
    }

    public final AcceptorOptions acceptor$ktor_network() {
        AcceptorOptions acceptorOptions = new AcceptorOptions(new HashMap(this.customOptions));
        acceptorOptions.copyCommon(this);
        return acceptorOptions;
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u0001B\u001b\u0012\u0014\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003¢\u0006\u0002\u0010\u0005J\r\u0010\u0006\u001a\u00020\u0000H\u0010¢\u0006\u0002\b\u0007¨\u0006\b"}, d2 = {"Lio/ktor/network/sockets/SocketOptions$GeneralSocketOptions;", "Lio/ktor/network/sockets/SocketOptions;", "customOptions", "", "", "(Ljava/util/Map;)V", "copy", "copy$ktor_network", "ktor-network"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: SocketOptions.kt */
    private static final class GeneralSocketOptions extends SocketOptions {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public GeneralSocketOptions(Map<Object, Object> map) {
            super(map, (DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(map, "customOptions");
        }

        public GeneralSocketOptions copy$ktor_network() {
            GeneralSocketOptions generalSocketOptions = new GeneralSocketOptions(new HashMap(getCustomOptions()));
            generalSocketOptions.copyCommon(this);
            return generalSocketOptions;
        }
    }

    /* renamed from: getTypeOfService-zieKYfw  reason: not valid java name */
    public final byte m1445getTypeOfServicezieKYfw() {
        return this.typeOfService;
    }

    /* renamed from: setTypeOfService-SNCuOGA  reason: not valid java name */
    public final void m1446setTypeOfServiceSNCuOGA(byte b) {
        this.typeOfService = b;
    }

    public final boolean getReuseAddress() {
        return this.reuseAddress;
    }

    public final void setReuseAddress(boolean z) {
        this.reuseAddress = z;
    }

    public final boolean getReusePort() {
        return this.reusePort;
    }

    public final void setReusePort(boolean z) {
        this.reusePort = z;
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u001d\b\u0000\u0012\u0014\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003¢\u0006\u0002\u0010\u0005J\r\u0010\f\u001a\u00020\u0000H\u0010¢\u0006\u0002\b\rR\u001a\u0010\u0006\u001a\u00020\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000b¨\u0006\u000e"}, d2 = {"Lio/ktor/network/sockets/SocketOptions$AcceptorOptions;", "Lio/ktor/network/sockets/SocketOptions;", "customOptions", "", "", "(Ljava/util/Map;)V", "backlogSize", "", "getBacklogSize", "()I", "setBacklogSize", "(I)V", "copy", "copy$ktor_network", "ktor-network"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: SocketOptions.kt */
    public static final class AcceptorOptions extends SocketOptions {
        private int backlogSize = FrameMetricsAggregator.EVERY_DURATION;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public AcceptorOptions(Map<Object, Object> map) {
            super(map, (DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(map, "customOptions");
        }

        public final int getBacklogSize() {
            return this.backlogSize;
        }

        public final void setBacklogSize(int i) {
            this.backlogSize = i;
        }

        public AcceptorOptions copy$ktor_network() {
            AcceptorOptions acceptorOptions = new AcceptorOptions(new HashMap(getCustomOptions()));
            acceptorOptions.copyCommon(this);
            return acceptorOptions;
        }
    }

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B\u001d\b\u0000\u0012\u0014\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003¢\u0006\u0002\u0010\u0005J\r\u0010\u000f\u001a\u00020\u0000H\u0010¢\u0006\u0002\b\u0010J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0001H\u0014J\r\u0010\u0014\u001a\u00020\u0015H\u0000¢\u0006\u0002\b\u0016J\r\u0010\u0017\u001a\u00020\u0018H\u0000¢\u0006\u0002\b\u0019R\u001a\u0010\u0006\u001a\u00020\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\t\"\u0004\b\u000e\u0010\u000b¨\u0006\u001a"}, d2 = {"Lio/ktor/network/sockets/SocketOptions$PeerSocketOptions;", "Lio/ktor/network/sockets/SocketOptions;", "customOptions", "", "", "(Ljava/util/Map;)V", "receiveBufferSize", "", "getReceiveBufferSize", "()I", "setReceiveBufferSize", "(I)V", "sendBufferSize", "getSendBufferSize", "setSendBufferSize", "copy", "copy$ktor_network", "copyCommon", "", "from", "tcp", "Lio/ktor/network/sockets/SocketOptions$TCPClientSocketOptions;", "tcp$ktor_network", "udp", "Lio/ktor/network/sockets/SocketOptions$UDPSocketOptions;", "udp$ktor_network", "ktor-network"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: SocketOptions.kt */
    public static class PeerSocketOptions extends SocketOptions {
        private int receiveBufferSize = -1;
        private int sendBufferSize = -1;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public PeerSocketOptions(Map<Object, Object> map) {
            super(map, (DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(map, "customOptions");
        }

        public final int getSendBufferSize() {
            return this.sendBufferSize;
        }

        public final void setSendBufferSize(int i) {
            this.sendBufferSize = i;
        }

        public final int getReceiveBufferSize() {
            return this.receiveBufferSize;
        }

        public final void setReceiveBufferSize(int i) {
            this.receiveBufferSize = i;
        }

        /* access modifiers changed from: protected */
        public void copyCommon(SocketOptions socketOptions) {
            Intrinsics.checkNotNullParameter(socketOptions, TypedValues.TransitionType.S_FROM);
            SocketOptions.super.copyCommon(socketOptions);
            if (socketOptions instanceof PeerSocketOptions) {
                PeerSocketOptions peerSocketOptions = (PeerSocketOptions) socketOptions;
                this.sendBufferSize = peerSocketOptions.sendBufferSize;
                this.receiveBufferSize = peerSocketOptions.receiveBufferSize;
            }
        }

        public PeerSocketOptions copy$ktor_network() {
            PeerSocketOptions peerSocketOptions = new PeerSocketOptions(new HashMap(getCustomOptions()));
            peerSocketOptions.copyCommon(this);
            return peerSocketOptions;
        }

        public final TCPClientSocketOptions tcp$ktor_network() {
            TCPClientSocketOptions tCPClientSocketOptions = new TCPClientSocketOptions(new HashMap(getCustomOptions()));
            copyCommon(this);
            return tCPClientSocketOptions;
        }

        public final UDPSocketOptions udp$ktor_network() {
            UDPSocketOptions uDPSocketOptions = new UDPSocketOptions(new HashMap(getCustomOptions()));
            copyCommon(this);
            return uDPSocketOptions;
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001d\b\u0000\u0012\u0014\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003¢\u0006\u0002\u0010\u0005J\r\u0010\f\u001a\u00020\u0000H\u0010¢\u0006\u0002\b\rJ\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0014R\u001a\u0010\u0006\u001a\u00020\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000b¨\u0006\u0012"}, d2 = {"Lio/ktor/network/sockets/SocketOptions$UDPSocketOptions;", "Lio/ktor/network/sockets/SocketOptions$PeerSocketOptions;", "customOptions", "", "", "(Ljava/util/Map;)V", "broadcast", "", "getBroadcast", "()Z", "setBroadcast", "(Z)V", "copy", "copy$ktor_network", "copyCommon", "", "from", "Lio/ktor/network/sockets/SocketOptions;", "ktor-network"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: SocketOptions.kt */
    public static final class UDPSocketOptions extends PeerSocketOptions {
        private boolean broadcast;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public UDPSocketOptions(Map<Object, Object> map) {
            super(map);
            Intrinsics.checkNotNullParameter(map, "customOptions");
        }

        public final boolean getBroadcast() {
            return this.broadcast;
        }

        public final void setBroadcast(boolean z) {
            this.broadcast = z;
        }

        /* access modifiers changed from: protected */
        public void copyCommon(SocketOptions socketOptions) {
            Intrinsics.checkNotNullParameter(socketOptions, TypedValues.TransitionType.S_FROM);
            super.copyCommon(socketOptions);
            if (socketOptions instanceof UDPSocketOptions) {
                this.broadcast = ((UDPSocketOptions) socketOptions).broadcast;
            }
        }

        public UDPSocketOptions copy$ktor_network() {
            UDPSocketOptions uDPSocketOptions = new UDPSocketOptions(new HashMap(getCustomOptions()));
            uDPSocketOptions.copyCommon(this);
            return uDPSocketOptions;
        }
    }

    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001d\b\u0000\u0012\u0014\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003¢\u0006\u0002\u0010\u0005J\r\u0010\u001e\u001a\u00020\u0000H\u0010¢\u0006\u0002\b\u001fJ\u0010\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#H\u0014R\u001e\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u000e¢\u0006\u0010\n\u0002\u0010\f\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\r\u001a\u00020\u000eX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0018\u001a\u00020\u0019X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001d¨\u0006$"}, d2 = {"Lio/ktor/network/sockets/SocketOptions$TCPClientSocketOptions;", "Lio/ktor/network/sockets/SocketOptions$PeerSocketOptions;", "customOptions", "", "", "(Ljava/util/Map;)V", "keepAlive", "", "getKeepAlive", "()Ljava/lang/Boolean;", "setKeepAlive", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "lingerSeconds", "", "getLingerSeconds", "()I", "setLingerSeconds", "(I)V", "noDelay", "getNoDelay", "()Z", "setNoDelay", "(Z)V", "socketTimeout", "", "getSocketTimeout", "()J", "setSocketTimeout", "(J)V", "copy", "copy$ktor_network", "copyCommon", "", "from", "Lio/ktor/network/sockets/SocketOptions;", "ktor-network"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: SocketOptions.kt */
    public static final class TCPClientSocketOptions extends PeerSocketOptions {
        private Boolean keepAlive;
        private int lingerSeconds = -1;
        private boolean noDelay = true;
        private long socketTimeout = Long.MAX_VALUE;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public TCPClientSocketOptions(Map<Object, Object> map) {
            super(map);
            Intrinsics.checkNotNullParameter(map, "customOptions");
        }

        public final boolean getNoDelay() {
            return this.noDelay;
        }

        public final void setNoDelay(boolean z) {
            this.noDelay = z;
        }

        public final int getLingerSeconds() {
            return this.lingerSeconds;
        }

        public final void setLingerSeconds(int i) {
            this.lingerSeconds = i;
        }

        public final Boolean getKeepAlive() {
            return this.keepAlive;
        }

        public final void setKeepAlive(Boolean bool) {
            this.keepAlive = bool;
        }

        public final long getSocketTimeout() {
            return this.socketTimeout;
        }

        public final void setSocketTimeout(long j) {
            this.socketTimeout = j;
        }

        /* access modifiers changed from: protected */
        public void copyCommon(SocketOptions socketOptions) {
            Intrinsics.checkNotNullParameter(socketOptions, TypedValues.TransitionType.S_FROM);
            super.copyCommon(socketOptions);
            if (socketOptions instanceof TCPClientSocketOptions) {
                TCPClientSocketOptions tCPClientSocketOptions = (TCPClientSocketOptions) socketOptions;
                this.noDelay = tCPClientSocketOptions.noDelay;
                this.lingerSeconds = tCPClientSocketOptions.lingerSeconds;
                this.keepAlive = tCPClientSocketOptions.keepAlive;
            }
        }

        public TCPClientSocketOptions copy$ktor_network() {
            TCPClientSocketOptions tCPClientSocketOptions = new TCPClientSocketOptions(new HashMap(getCustomOptions()));
            tCPClientSocketOptions.copyCommon(this);
            return tCPClientSocketOptions;
        }
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\r\u0010\u0003\u001a\u00020\u0004H\u0000¢\u0006\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lio/ktor/network/sockets/SocketOptions$Companion;", "", "()V", "create", "Lio/ktor/network/sockets/SocketOptions;", "create$ktor_network", "ktor-network"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: SocketOptions.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final SocketOptions create$ktor_network() {
            return new GeneralSocketOptions(new HashMap());
        }
    }
}
