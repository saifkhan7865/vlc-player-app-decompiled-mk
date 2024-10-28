package io.ktor.network.tls;

import io.ktor.network.tls.extensions.HashAndSign;
import io.ktor.network.tls.extensions.NamedCurve;
import io.ktor.network.tls.extensions.SignatureAlgorithmKt;
import io.ktor.utils.io.core.ByteReadPacket;
import io.ktor.utils.io.core.Input;
import io.ktor.utils.io.core.InputPrimitivesKt;
import io.ktor.utils.io.core.StringsKt;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.security.spec.ECPublicKeySpec;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.security.auth.x500.X500Principal;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\u0018\u0002\n\u0000\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0002\u001a\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0000\u001a\f\u0010\n\u001a\u00020\u000b*\u00020\fH\u0002¨\u0006\r"}, d2 = {"generateECKeys", "Lio/ktor/network/tls/EncryptionInfo;", "curve", "Lio/ktor/network/tls/extensions/NamedCurve;", "serverPoint", "Ljava/security/spec/ECPoint;", "readClientCertificateRequest", "Lio/ktor/network/tls/CertificateInfo;", "packet", "Lio/ktor/utils/io/core/ByteReadPacket;", "generateClientSeed", "", "Ljava/security/SecureRandom;", "ktor-network-tls"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: TLSClientHandshake.kt */
public final class TLSClientHandshakeKt {
    /* access modifiers changed from: private */
    public static final byte[] generateClientSeed(SecureRandom secureRandom) {
        byte[] bArr = new byte[32];
        secureRandom.nextBytes(bArr);
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        bArr[0] = (byte) ((int) (currentTimeMillis >> 24));
        bArr[1] = (byte) ((int) (currentTimeMillis >> 16));
        bArr[2] = (byte) ((int) (currentTimeMillis >> 8));
        bArr[3] = (byte) ((int) currentTimeMillis);
        return bArr;
    }

    /* access modifiers changed from: private */
    public static final EncryptionInfo generateECKeys(NamedCurve namedCurve, ECPoint eCPoint) {
        KeyPairGenerator instance = KeyPairGenerator.getInstance("EC");
        Intrinsics.checkNotNull(instance);
        instance.initialize(new ECGenParameterSpec(namedCurve.name()));
        KeyPair generateKeyPair = instance.generateKeyPair();
        Intrinsics.checkNotNull(generateKeyPair);
        PublicKey publicKey = generateKeyPair.getPublic();
        Intrinsics.checkNotNull(publicKey, "null cannot be cast to non-null type java.security.interfaces.ECPublicKey");
        KeyFactory instance2 = KeyFactory.getInstance("EC");
        Intrinsics.checkNotNull(instance2);
        ECParameterSpec params = ((ECPublicKey) publicKey).getParams();
        Intrinsics.checkNotNull(params);
        PublicKey generatePublic = instance2.generatePublic(new ECPublicKeySpec(eCPoint, params));
        Intrinsics.checkNotNull(generatePublic);
        PublicKey publicKey2 = generateKeyPair.getPublic();
        Intrinsics.checkNotNullExpressionValue(publicKey2, "clientKeys.public");
        PrivateKey privateKey = generateKeyPair.getPrivate();
        Intrinsics.checkNotNullExpressionValue(privateKey, "clientKeys.private");
        return new EncryptionInfo(generatePublic, publicKey2, privateKey);
    }

    public static final CertificateInfo readClientCertificateRequest(ByteReadPacket byteReadPacket) {
        Intrinsics.checkNotNullParameter(byteReadPacket, "packet");
        byte[] readBytes = StringsKt.readBytes(byteReadPacket, (int) byteReadPacket.readByte() & 255);
        Input input = byteReadPacket;
        List arrayList = new ArrayList();
        int readShort = (InputPrimitivesKt.readShort(input) & 65535) / 2;
        for (int i = 0; i < readShort; i++) {
            Collection collection = arrayList;
            HashAndSign byCode = SignatureAlgorithmKt.byCode(HashAndSign.Companion, byteReadPacket.readByte(), byteReadPacket.readByte());
            if (byCode != null) {
                collection.add(byCode);
            }
        }
        short readShort2 = InputPrimitivesKt.readShort(input) & 65535;
        Set linkedHashSet = new LinkedHashSet();
        int i2 = 0;
        while (i2 < readShort2) {
            short readShort3 = InputPrimitivesKt.readShort(input) & 65535;
            i2 += readShort3 + 2;
            linkedHashSet.add(new X500Principal(StringsKt.readBytes(byteReadPacket, (int) readShort3)));
        }
        CertificateInfo certificateInfo = new CertificateInfo(readBytes, (HashAndSign[]) arrayList.toArray(new HashAndSign[0]), linkedHashSet);
        if (byteReadPacket.getEndOfInput()) {
            return certificateInfo;
        }
        throw new IllegalStateException("Check failed.".toString());
    }
}
