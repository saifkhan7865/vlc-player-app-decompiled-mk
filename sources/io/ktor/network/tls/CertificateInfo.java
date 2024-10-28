package io.ktor.network.tls;

import io.ktor.network.tls.extensions.HashAndSign;
import java.util.Set;
import javax.security.auth.x500.X500Principal;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\t\b\u0000\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\u0010\nR\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0019\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0012"}, d2 = {"Lio/ktor/network/tls/CertificateInfo;", "", "types", "", "hashAndSign", "", "Lio/ktor/network/tls/extensions/HashAndSign;", "authorities", "", "Ljavax/security/auth/x500/X500Principal;", "([B[Lio/ktor/network/tls/extensions/HashAndSign;Ljava/util/Set;)V", "getAuthorities", "()Ljava/util/Set;", "getHashAndSign", "()[Lio/ktor/network/tls/extensions/HashAndSign;", "[Lio/ktor/network/tls/extensions/HashAndSign;", "getTypes", "()[B", "ktor-network-tls"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: CertificateInfo.kt */
public final class CertificateInfo {
    private final Set<X500Principal> authorities;
    private final HashAndSign[] hashAndSign;
    private final byte[] types;

    public CertificateInfo(byte[] bArr, HashAndSign[] hashAndSignArr, Set<X500Principal> set) {
        Intrinsics.checkNotNullParameter(bArr, "types");
        Intrinsics.checkNotNullParameter(hashAndSignArr, "hashAndSign");
        Intrinsics.checkNotNullParameter(set, "authorities");
        this.types = bArr;
        this.hashAndSign = hashAndSignArr;
        this.authorities = set;
    }

    public final byte[] getTypes() {
        return this.types;
    }

    public final HashAndSign[] getHashAndSign() {
        return this.hashAndSign;
    }

    public final Set<X500Principal> getAuthorities() {
        return this.authorities;
    }
}
