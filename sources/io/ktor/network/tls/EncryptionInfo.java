package io.ktor.network.tls;

import java.security.PrivateKey;
import java.security.PublicKey;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0006HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b¨\u0006\u0018"}, d2 = {"Lio/ktor/network/tls/EncryptionInfo;", "", "serverPublic", "Ljava/security/PublicKey;", "clientPublic", "clientPrivate", "Ljava/security/PrivateKey;", "(Ljava/security/PublicKey;Ljava/security/PublicKey;Ljava/security/PrivateKey;)V", "getClientPrivate", "()Ljava/security/PrivateKey;", "getClientPublic", "()Ljava/security/PublicKey;", "getServerPublic", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "", "ktor-network-tls"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: EncryptionInfo.kt */
public final class EncryptionInfo {
    private final PrivateKey clientPrivate;
    private final PublicKey clientPublic;
    private final PublicKey serverPublic;

    public static /* synthetic */ EncryptionInfo copy$default(EncryptionInfo encryptionInfo, PublicKey publicKey, PublicKey publicKey2, PrivateKey privateKey, int i, Object obj) {
        if ((i & 1) != 0) {
            publicKey = encryptionInfo.serverPublic;
        }
        if ((i & 2) != 0) {
            publicKey2 = encryptionInfo.clientPublic;
        }
        if ((i & 4) != 0) {
            privateKey = encryptionInfo.clientPrivate;
        }
        return encryptionInfo.copy(publicKey, publicKey2, privateKey);
    }

    public final PublicKey component1() {
        return this.serverPublic;
    }

    public final PublicKey component2() {
        return this.clientPublic;
    }

    public final PrivateKey component3() {
        return this.clientPrivate;
    }

    public final EncryptionInfo copy(PublicKey publicKey, PublicKey publicKey2, PrivateKey privateKey) {
        Intrinsics.checkNotNullParameter(publicKey, "serverPublic");
        Intrinsics.checkNotNullParameter(publicKey2, "clientPublic");
        Intrinsics.checkNotNullParameter(privateKey, "clientPrivate");
        return new EncryptionInfo(publicKey, publicKey2, privateKey);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof EncryptionInfo)) {
            return false;
        }
        EncryptionInfo encryptionInfo = (EncryptionInfo) obj;
        return Intrinsics.areEqual((Object) this.serverPublic, (Object) encryptionInfo.serverPublic) && Intrinsics.areEqual((Object) this.clientPublic, (Object) encryptionInfo.clientPublic) && Intrinsics.areEqual((Object) this.clientPrivate, (Object) encryptionInfo.clientPrivate);
    }

    public int hashCode() {
        return (((this.serverPublic.hashCode() * 31) + this.clientPublic.hashCode()) * 31) + this.clientPrivate.hashCode();
    }

    public String toString() {
        return "EncryptionInfo(serverPublic=" + this.serverPublic + ", clientPublic=" + this.clientPublic + ", clientPrivate=" + this.clientPrivate + ')';
    }

    public EncryptionInfo(PublicKey publicKey, PublicKey publicKey2, PrivateKey privateKey) {
        Intrinsics.checkNotNullParameter(publicKey, "serverPublic");
        Intrinsics.checkNotNullParameter(publicKey2, "clientPublic");
        Intrinsics.checkNotNullParameter(privateKey, "clientPrivate");
        this.serverPublic = publicKey;
        this.clientPublic = publicKey2;
        this.clientPrivate = privateKey;
    }

    public final PublicKey getServerPublic() {
        return this.serverPublic;
    }

    public final PublicKey getClientPublic() {
        return this.clientPublic;
    }

    public final PrivateKey getClientPrivate() {
        return this.clientPrivate;
    }
}
