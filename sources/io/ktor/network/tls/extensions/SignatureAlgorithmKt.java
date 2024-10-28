package io.ktor.network.tls.extensions;

import io.ktor.network.tls.OID;
import io.ktor.network.tls.TLSException;
import io.ktor.network.tls.extensions.HashAndSign;
import io.ktor.utils.io.core.ByteReadPacket;
import io.ktor.utils.io.core.InputPrimitivesKt;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a&\u0010\u0005\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\nH\u0000\u001a\u001c\u0010\u000b\u001a\u0004\u0018\u00010\u0002*\u00020\f2\u0006\u0010\r\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u0007\u001a\u0012\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0010H\u0000\u001a\u000e\u0010\u0011\u001a\u0004\u0018\u00010\u0002*\u00020\u0010H\u0000\"\u0017\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0012"}, d2 = {"SupportedSignatureAlgorithms", "", "Lio/ktor/network/tls/extensions/HashAndSign;", "getSupportedSignatureAlgorithms", "()Ljava/util/List;", "HashAndSign", "hashValue", "", "signValue", "oidValue", "", "byCode", "Lio/ktor/network/tls/extensions/HashAndSign$Companion;", "hash", "sign", "parseSignatureAlgorithms", "Lio/ktor/utils/io/core/ByteReadPacket;", "readHashAndSign", "ktor-network-tls"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: SignatureAlgorithm.kt */
public final class SignatureAlgorithmKt {
    private static final List<HashAndSign> SupportedSignatureAlgorithms = CollectionsKt.listOf(new HashAndSign(HashAlgorithm.SHA384, SignatureAlgorithm.ECDSA, OID.Companion.getECDSAwithSHA384Encryption()), new HashAndSign(HashAlgorithm.SHA256, SignatureAlgorithm.ECDSA, OID.Companion.getECDSAwithSHA256Encryption()), new HashAndSign(HashAlgorithm.SHA512, SignatureAlgorithm.RSA, OID.Companion.getRSAwithSHA512Encryption()), new HashAndSign(HashAlgorithm.SHA384, SignatureAlgorithm.RSA, OID.Companion.getRSAwithSHA384Encryption()), new HashAndSign(HashAlgorithm.SHA256, SignatureAlgorithm.RSA, OID.Companion.getRSAwithSHA256Encryption()), new HashAndSign(HashAlgorithm.SHA1, SignatureAlgorithm.RSA, OID.Companion.getRSAwithSHA1Encryption()));

    public static /* synthetic */ HashAndSign HashAndSign$default(byte b, byte b2, String str, int i, Object obj) {
        if ((i & 4) != 0) {
            str = null;
        }
        return HashAndSign(b, b2, str);
    }

    public static final HashAndSign HashAndSign(byte b, byte b2, String str) {
        HashAlgorithm byCode = HashAlgorithm.Companion.byCode(b);
        SignatureAlgorithm byCode2 = SignatureAlgorithm.Companion.byCode(b2);
        OID oid = null;
        if (byCode2 == null) {
            return null;
        }
        if (str != null) {
            oid = new OID(str);
        }
        return new HashAndSign(byCode, byCode2, oid);
    }

    public static final List<HashAndSign> getSupportedSignatureAlgorithms() {
        return SupportedSignatureAlgorithms;
    }

    public static final List<HashAndSign> parseSignatureAlgorithms(ByteReadPacket byteReadPacket) {
        Intrinsics.checkNotNullParameter(byteReadPacket, "<this>");
        short readShort = InputPrimitivesKt.readShort(byteReadPacket) & 65535;
        List<HashAndSign> arrayList = new ArrayList<>();
        while (byteReadPacket.getRemaining() > 0) {
            Collection collection = arrayList;
            HashAndSign readHashAndSign = readHashAndSign(byteReadPacket);
            if (readHashAndSign != null) {
                collection.add(readHashAndSign);
            }
        }
        if (((int) byteReadPacket.getRemaining()) == readShort) {
            return arrayList;
        }
        throw new TLSException("Invalid hash and sign packet size: expected " + readShort + ", actual " + arrayList.size(), (Throwable) null, 2, (DefaultConstructorMarker) null);
    }

    public static final HashAndSign readHashAndSign(ByteReadPacket byteReadPacket) {
        Intrinsics.checkNotNullParameter(byteReadPacket, "<this>");
        return byCode(HashAndSign.Companion, byteReadPacket.readByte(), byteReadPacket.readByte());
    }

    public static final HashAndSign byCode(HashAndSign.Companion companion, byte b, byte b2) {
        Object obj;
        Intrinsics.checkNotNullParameter(companion, "<this>");
        if (b2 != SignatureAlgorithm.ANON.getCode()) {
            Iterator it = SupportedSignatureAlgorithms.iterator();
            while (true) {
                if (!it.hasNext()) {
                    obj = null;
                    break;
                }
                obj = it.next();
                HashAndSign hashAndSign = (HashAndSign) obj;
                if (hashAndSign.getHash().getCode() == b && hashAndSign.getSign().getCode() == b2) {
                    break;
                }
            }
            HashAndSign hashAndSign2 = (HashAndSign) obj;
            return hashAndSign2 == null ? HashAndSign$default(b, b2, (String) null, 4, (Object) null) : hashAndSign2;
        }
        throw new IllegalStateException("Anonymous signature not allowed.".toString());
    }
}
