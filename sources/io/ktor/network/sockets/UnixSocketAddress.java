package io.ktor.network.sockets;

import java.lang.reflect.Method;
import java.net.SocketAddress;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.vlc.ArtworkProvider;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u000f\b\u0000\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\f\u001a\u00020\u0003H\u0002J\u0010\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u0003H\u0016R\u0014\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lio/ktor/network/sockets/UnixSocketAddress;", "Lio/ktor/network/sockets/SocketAddress;", "path", "", "(Ljava/lang/String;)V", "address", "Ljava/net/SocketAddress;", "(Ljava/net/SocketAddress;)V", "getAddress$ktor_network", "()Ljava/net/SocketAddress;", "getPath", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "Companion", "ktor-network"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: SocketAddressJvm.kt */
public final class UnixSocketAddress extends SocketAddress {
    private static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final Class<?> unixDomainSocketAddressClass;
    private final SocketAddress address;

    public static /* synthetic */ UnixSocketAddress copy$default(UnixSocketAddress unixSocketAddress, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = unixSocketAddress.getPath();
        }
        return unixSocketAddress.copy(str);
    }

    public SocketAddress getAddress$ktor_network() {
        return this.address;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public UnixSocketAddress(SocketAddress socketAddress) {
        super((DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(socketAddress, "address");
        this.address = socketAddress;
        if (!Intrinsics.areEqual((Object) getAddress$ktor_network().getClass().getName(), (Object) SocketAddressJvmKt.UNIX_DOMAIN_SOCKET_ADDRESS_CLASS)) {
            throw new IllegalStateException("address should be java.net.UnixDomainSocketAddress".toString());
        }
    }

    public final String getPath() {
        Method method = Companion.checkSupportForUnixDomainSockets().getMethod("getPath", (Class[]) null);
        Intrinsics.checkNotNullExpressionValue(method, "checkSupportForUnixDomai…ts().getMethod(\"getPath\")");
        return method.invoke(getAddress$ktor_network(), (Object[]) null).toString();
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public UnixSocketAddress(java.lang.String r6) {
        /*
            r5 = this;
            java.lang.String r0 = "path"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            io.ktor.network.sockets.UnixSocketAddress$Companion r0 = Companion
            java.lang.Class r0 = r0.checkSupportForUnixDomainSockets()
            r1 = 1
            java.lang.Class[] r2 = new java.lang.Class[r1]
            java.lang.Class<java.lang.String> r3 = java.lang.String.class
            r4 = 0
            r2[r4] = r3
            java.lang.String r3 = "of"
            java.lang.reflect.Method r0 = r0.getMethod(r3, r2)
            java.lang.Object[] r1 = new java.lang.Object[r1]
            r1[r4] = r6
            r6 = 0
            java.lang.Object r6 = r0.invoke(r6, r1)
            java.lang.String r0 = "null cannot be cast to non-null type java.net.SocketAddress"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r6, r0)
            java.net.SocketAddress r6 = (java.net.SocketAddress) r6
            r5.<init>((java.net.SocketAddress) r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.network.sockets.UnixSocketAddress.<init>(java.lang.String):void");
    }

    public final String component1() {
        return getPath();
    }

    public final UnixSocketAddress copy(String str) {
        Intrinsics.checkNotNullParameter(str, ArtworkProvider.PATH);
        return new UnixSocketAddress(str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!Intrinsics.areEqual((Object) getClass(), (Object) obj != null ? obj.getClass() : null)) {
            return false;
        }
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type io.ktor.network.sockets.UnixSocketAddress");
        return Intrinsics.areEqual((Object) getAddress$ktor_network(), (Object) ((UnixSocketAddress) obj).getAddress$ktor_network());
    }

    public int hashCode() {
        return getAddress$ktor_network().hashCode();
    }

    public String toString() {
        return getAddress$ktor_network().toString();
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\f\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0004H\u0002R\u0014\u0010\u0003\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lio/ktor/network/sockets/UnixSocketAddress$Companion;", "", "()V", "unixDomainSocketAddressClass", "Ljava/lang/Class;", "checkSupportForUnixDomainSockets", "ktor-network"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: SocketAddressJvm.kt */
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* access modifiers changed from: private */
        public final Class<?> checkSupportForUnixDomainSockets() {
            Class<?> access$getUnixDomainSocketAddressClass$cp = UnixSocketAddress.unixDomainSocketAddressClass;
            if (access$getUnixDomainSocketAddressClass$cp != null) {
                return access$getUnixDomainSocketAddressClass$cp;
            }
            throw new IllegalStateException("Unix domain sockets are unsupported before Java 16.".toString());
        }
    }

    static {
        Class<?> cls = null;
        try {
            cls = Class.forName(SocketAddressJvmKt.UNIX_DOMAIN_SOCKET_ADDRESS_CLASS);
        } catch (ClassNotFoundException unused) {
        }
        unixDomainSocketAddressClass = cls;
    }
}
