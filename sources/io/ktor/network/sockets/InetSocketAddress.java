package io.ktor.network.sockets;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\u000f\b\u0000\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003H\u0002J\t\u0010\u0011\u001a\u00020\u0005H\u0002J\u001a\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0002J\b\u0010\u0017\u001a\u00020\u0005H\u0016J\b\u0010\u0018\u001a\u00020\u0003H\u0016R\u0014\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0019"}, d2 = {"Lio/ktor/network/sockets/InetSocketAddress;", "Lio/ktor/network/sockets/SocketAddress;", "hostname", "", "port", "", "(Ljava/lang/String;I)V", "address", "Ljava/net/InetSocketAddress;", "(Ljava/net/InetSocketAddress;)V", "getAddress$ktor_network", "()Ljava/net/InetSocketAddress;", "getHostname", "()Ljava/lang/String;", "getPort", "()I", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "toString", "ktor-network"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: SocketAddressJvm.kt */
public final class InetSocketAddress extends SocketAddress {
    private final java.net.InetSocketAddress address;

    public java.net.InetSocketAddress getAddress$ktor_network() {
        return this.address;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public InetSocketAddress(java.net.InetSocketAddress inetSocketAddress) {
        super((DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(inetSocketAddress, "address");
        this.address = inetSocketAddress;
    }

    public final String getHostname() {
        String hostName = getAddress$ktor_network().getHostName();
        Intrinsics.checkNotNullExpressionValue(hostName, "address.hostName");
        return hostName;
    }

    public final int getPort() {
        return getAddress$ktor_network().getPort();
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public InetSocketAddress(String str, int i) {
        this(new java.net.InetSocketAddress(str, i));
        Intrinsics.checkNotNullParameter(str, "hostname");
    }

    public final String component1() {
        return getHostname();
    }

    public final int component2() {
        return getPort();
    }

    public final InetSocketAddress copy(String str, int i) {
        Intrinsics.checkNotNullParameter(str, "hostname");
        return new InetSocketAddress(str, i);
    }

    public static /* synthetic */ InetSocketAddress copy$default(InetSocketAddress inetSocketAddress, String str, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = inetSocketAddress.getHostname();
        }
        if ((i2 & 2) != 0) {
            i = inetSocketAddress.getPort();
        }
        return inetSocketAddress.copy(str, i);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!Intrinsics.areEqual((Object) getClass(), (Object) obj != null ? obj.getClass() : null)) {
            return false;
        }
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type io.ktor.network.sockets.InetSocketAddress");
        return Intrinsics.areEqual((Object) getAddress$ktor_network(), (Object) ((InetSocketAddress) obj).getAddress$ktor_network());
    }

    public int hashCode() {
        return getAddress$ktor_network().hashCode();
    }

    public String toString() {
        String inetSocketAddress = getAddress$ktor_network().toString();
        Intrinsics.checkNotNullExpressionValue(inetSocketAddress, "address.toString()");
        return inetSocketAddress;
    }
}
