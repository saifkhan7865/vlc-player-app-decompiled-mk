package io.ktor.network.tls.extensions;

import io.ktor.network.tls.OID;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\b\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0007HÆ\u0003J)\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001J\t\u0010\u001c\u001a\u00020\fHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001e"}, d2 = {"Lio/ktor/network/tls/extensions/HashAndSign;", "", "hash", "Lio/ktor/network/tls/extensions/HashAlgorithm;", "sign", "Lio/ktor/network/tls/extensions/SignatureAlgorithm;", "oid", "Lio/ktor/network/tls/OID;", "(Lio/ktor/network/tls/extensions/HashAlgorithm;Lio/ktor/network/tls/extensions/SignatureAlgorithm;Lio/ktor/network/tls/OID;)V", "getHash", "()Lio/ktor/network/tls/extensions/HashAlgorithm;", "name", "", "getName", "()Ljava/lang/String;", "getOid", "()Lio/ktor/network/tls/OID;", "getSign", "()Lio/ktor/network/tls/extensions/SignatureAlgorithm;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "Companion", "ktor-network-tls"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: SignatureAlgorithm.kt */
public final class HashAndSign {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final HashAlgorithm hash;
    private final String name;
    private final OID oid;
    private final SignatureAlgorithm sign;

    public static /* synthetic */ HashAndSign copy$default(HashAndSign hashAndSign, HashAlgorithm hashAlgorithm, SignatureAlgorithm signatureAlgorithm, OID oid2, int i, Object obj) {
        if ((i & 1) != 0) {
            hashAlgorithm = hashAndSign.hash;
        }
        if ((i & 2) != 0) {
            signatureAlgorithm = hashAndSign.sign;
        }
        if ((i & 4) != 0) {
            oid2 = hashAndSign.oid;
        }
        return hashAndSign.copy(hashAlgorithm, signatureAlgorithm, oid2);
    }

    public final HashAlgorithm component1() {
        return this.hash;
    }

    public final SignatureAlgorithm component2() {
        return this.sign;
    }

    public final OID component3() {
        return this.oid;
    }

    public final HashAndSign copy(HashAlgorithm hashAlgorithm, SignatureAlgorithm signatureAlgorithm, OID oid2) {
        Intrinsics.checkNotNullParameter(hashAlgorithm, "hash");
        Intrinsics.checkNotNullParameter(signatureAlgorithm, "sign");
        return new HashAndSign(hashAlgorithm, signatureAlgorithm, oid2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof HashAndSign)) {
            return false;
        }
        HashAndSign hashAndSign = (HashAndSign) obj;
        return this.hash == hashAndSign.hash && this.sign == hashAndSign.sign && Intrinsics.areEqual((Object) this.oid, (Object) hashAndSign.oid);
    }

    public int hashCode() {
        int hashCode = ((this.hash.hashCode() * 31) + this.sign.hashCode()) * 31;
        OID oid2 = this.oid;
        return hashCode + (oid2 == null ? 0 : oid2.hashCode());
    }

    public String toString() {
        return "HashAndSign(hash=" + this.hash + ", sign=" + this.sign + ", oid=" + this.oid + ')';
    }

    public HashAndSign(HashAlgorithm hashAlgorithm, SignatureAlgorithm signatureAlgorithm, OID oid2) {
        Intrinsics.checkNotNullParameter(hashAlgorithm, "hash");
        Intrinsics.checkNotNullParameter(signatureAlgorithm, "sign");
        this.hash = hashAlgorithm;
        this.sign = signatureAlgorithm;
        this.oid = oid2;
        this.name = hashAlgorithm.name() + "with" + signatureAlgorithm.name();
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ HashAndSign(HashAlgorithm hashAlgorithm, SignatureAlgorithm signatureAlgorithm, OID oid2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(hashAlgorithm, signatureAlgorithm, (i & 4) != 0 ? null : oid2);
    }

    public final HashAlgorithm getHash() {
        return this.hash;
    }

    public final OID getOid() {
        return this.oid;
    }

    public final SignatureAlgorithm getSign() {
        return this.sign;
    }

    public final String getName() {
        return this.name;
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lio/ktor/network/tls/extensions/HashAndSign$Companion;", "", "()V", "ktor-network-tls"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: SignatureAlgorithm.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
