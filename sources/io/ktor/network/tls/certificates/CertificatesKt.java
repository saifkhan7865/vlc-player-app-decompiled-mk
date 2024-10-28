package io.ktor.network.tls.certificates;

import io.ktor.network.tls.OID;
import io.ktor.utils.io.core.BytePacketBuilder;
import io.ktor.utils.io.core.ByteReadPacket;
import io.ktor.utils.io.core.CloseableJVMKt;
import io.ktor.utils.io.core.Output;
import io.ktor.utils.io.core.OutputKt;
import io.ktor.utils.io.core.OutputPrimitivesKt;
import io.ktor.utils.io.core.StringsKt;
import io.ktor.utils.io.pool.ObjectPool;
import j$.time.Instant;
import j$.time.ZoneOffset;
import j$.time.format.DateTimeFormatter;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.nio.charset.Charset;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.security.auth.x500.X500Principal;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.Duration;
import kotlin.time.DurationKt;
import kotlin.time.DurationUnit;

@Metadata(d1 = {"\u0000ª\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0019\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0010\u0012\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\u001aU\u0010\f\u001a\u00020\u000b2\n\b\u0002\u0010\u0001\u001a\u0004\u0018\u00010\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\t¢\u0006\u0004\b\f\u0010\r\u001aq\u0010\u001e\u001a\u00020\u001b2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0016\u001a\u00020\u00152\b\b\u0002\u0010\n\u001a\u00020\t2\u000e\b\u0002\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00020\u00172\u000e\b\u0002\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00190\u0017H\u0000ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001c\u0010\u001d\u001am\u0010\f\u001a\u00020\u000b*\u00020\u000b2\n\b\u0002\u0010\u0001\u001a\u0004\u0018\u00010\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\u001f\u001a\u00020\u00022\b\b\u0002\u0010 \u001a\u00020\u00022\b\b\u0002\u0010\n\u001a\u00020\t¢\u0006\u0004\b\f\u0010!\u001a'\u0010$\u001a\u00020\u000b*\u00020\u000b2\n\b\u0002\u0010\u0001\u001a\u0004\u0018\u00010\u00002\b\b\u0002\u0010#\u001a\u00020\"¢\u0006\u0004\b$\u0010%\u001ai\u0010+\u001a\u00020**\u00020&2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010(\u001a\u00020'2\u0006\u0010)\u001a\u00020'2\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00020\u00172\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00190\u00172\b\b\u0002\u0010\n\u001a\u00020\tH\u0002¢\u0006\u0004\b+\u0010,\u001a,\u00100\u001a\u00020**\u00020&2\u0017\u0010/\u001a\u0013\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020*0-¢\u0006\u0002\b.H\u0002¢\u0006\u0004\b0\u00101\u001a\u0013\u00102\u001a\u00020**\u00020&H\u0002¢\u0006\u0004\b2\u00103\u001a\u0013\u00104\u001a\u00020**\u00020&H\u0002¢\u0006\u0004\b4\u00103\u001a/\u00105\u001a\u00020**\u00020&2\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00020\u00172\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00190\u0017H\u0002¢\u0006\u0004\b5\u00106\u001a\u0013\u00107\u001a\u00020**\u00020&H\u0002¢\u0006\u0004\b7\u00103\u001a\u001b\u00108\u001a\u00020**\u00020&2\u0006\u0010\u0003\u001a\u00020\u0002H\u0002¢\u0006\u0004\b8\u00109\u001a4\u0010<\u001a\u00020**\u00020&2\u0006\u0010:\u001a\u00020\u00072\u0017\u0010;\u001a\u0013\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020*0-¢\u0006\u0002\b.H\u0002¢\u0006\u0004\b<\u0010=\u001a\u001b\u0010?\u001a\u00020**\u00020&2\u0006\u0010>\u001a\u00020\u000eH\u0002¢\u0006\u0004\b?\u0010@\u001aq\u0010A\u001a\u00020**\u00020&2\u0006\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010(\u001a\u00020'2\u0006\u0010)\u001a\u00020'2\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00020\u00172\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00190\u00172\u0006\u0010\u0014\u001a\u00020\u00132\b\b\u0002\u0010\n\u001a\u00020\tH\u0002¢\u0006\u0004\bA\u0010B\u001a\u001d\u0010D\u001a\u00020**\u00020&2\b\b\u0002\u0010C\u001a\u00020\u0007H\u0002¢\u0006\u0004\bD\u0010E\u001a,\u0010G\u001a\u00020**\u00020&2\u0017\u0010F\u001a\u0013\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020*0-¢\u0006\u0002\b.H\u0002¢\u0006\u0004\bG\u00101\u001a%\u0010K\u001a\u00020**\u00020&2\u0006\u0010I\u001a\u00020H2\b\b\u0002\u0010J\u001a\u00020\u0007H\u0002¢\u0006\u0004\bK\u0010L\u001a\u001b\u0010N\u001a\u00020**\u00020&2\u0006\u0010M\u001a\u00020'H\u0002¢\u0006\u0004\bN\u0010O\u001a\u001b\u0010P\u001a\u00020**\u00020&2\u0006\u0010M\u001a\u00020'H\u0002¢\u0006\u0004\bP\u0010O\u001a%\u0010S\u001a\u00020**\u00020&2\u0006\u0010Q\u001a\u00020\u00022\b\b\u0002\u0010R\u001a\u00020\u0007H\u0002¢\u0006\u0004\bS\u0010T\u001a\u0013\u0010U\u001a\u00020**\u00020&H\u0002¢\u0006\u0004\bU\u00103\u001a,\u0010V\u001a\u00020**\u00020&2\u0017\u0010F\u001a\u0013\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020*0-¢\u0006\u0002\b.H\u0002¢\u0006\u0004\bV\u00101\u001a\u001b\u0010Y\u001a\u00020**\u00020&2\u0006\u0010X\u001a\u00020WH\u0002¢\u0006\u0004\bY\u0010Z\u001a\u001b\u0010Y\u001a\u00020**\u00020&2\u0006\u0010X\u001a\u00020[H\u0002¢\u0006\u0004\bY\u0010\\\u001a\u001b\u0010_\u001a\u00020**\u00020&2\u0006\u0010^\u001a\u00020]H\u0002¢\u0006\u0004\b_\u0010`\u001a\u001b\u0010_\u001a\u00020**\u00020&2\u0006\u0010^\u001a\u00020\u0007H\u0002¢\u0006\u0004\b_\u0010E\u001a\u001b\u0010b\u001a\u00020**\u00020&2\u0006\u0010a\u001a\u00020\u0007H\u0002¢\u0006\u0004\bb\u0010E\u001a+\u0010g\u001a\u00020**\u00020&2\u0006\u0010c\u001a\u00020\u00072\u0006\u0010d\u001a\u00020\u00072\u0006\u0010f\u001a\u00020eH\u0002¢\u0006\u0004\bg\u0010h\u001a\u0013\u0010i\u001a\u00020\u0007*\u00020\u0007H\u0002¢\u0006\u0004\bi\u0010j\u001a\u001b\u0010k\u001a\u00020**\u00020&2\u0006\u0010^\u001a\u00020eH\u0002¢\u0006\u0004\bk\u0010l\u001a\u0016\u0010n\u001a\u00020m*\u00020eH\u0002ø\u0001\u0001¢\u0006\u0004\bn\u0010o\u001a\u001b\u0010p\u001a\u00020**\u00020&2\u0006\u0010^\u001a\u00020\u0007H\u0002¢\u0006\u0004\bp\u0010E\"\u001a\u0010q\u001a\u00020\u000e8\u0000X\u0004¢\u0006\f\n\u0004\bq\u0010r\u001a\u0004\bs\u0010t\"\u0014\u0010u\u001a\u00020\u000e8\u0002X\u0004¢\u0006\u0006\n\u0004\bu\u0010r\"\u001b\u0010y\u001a\b\u0012\u0004\u0012\u00020v0\u0017*\u00020\u000b8F¢\u0006\u0006\u001a\u0004\bw\u0010x\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006z"}, d2 = {"Ljava/io/File;", "file", "", "algorithm", "keyAlias", "keyPassword", "jksPassword", "", "keySizeInBits", "Lio/ktor/network/tls/certificates/KeyType;", "keyType", "Ljava/security/KeyStore;", "generateCertificate", "(Ljava/io/File;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILio/ktor/network/tls/certificates/KeyType;)Ljava/security/KeyStore;", "Ljavax/security/auth/x500/X500Principal;", "subject", "issuer", "Ljava/security/PublicKey;", "publicKey", "Ljava/security/KeyPair;", "signerKeyPair", "Lkotlin/time/Duration;", "validityDuration", "", "domains", "Ljava/net/InetAddress;", "ipAddresses", "Ljava/security/cert/X509Certificate;", "generateX509Certificate-Tu6dINM", "(Ljavax/security/auth/x500/X500Principal;Ljavax/security/auth/x500/X500Principal;Ljava/security/PublicKey;Ljava/security/KeyPair;Ljava/lang/String;JLio/ktor/network/tls/certificates/KeyType;Ljava/util/List;Ljava/util/List;)Ljava/security/cert/X509Certificate;", "generateX509Certificate", "caKeyAlias", "caPassword", "(Ljava/security/KeyStore;Ljava/io/File;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Lio/ktor/network/tls/certificates/KeyType;)Ljava/security/KeyStore;", "", "password", "trustStore", "(Ljava/security/KeyStore;Ljava/io/File;[C)Ljava/security/KeyStore;", "Lio/ktor/utils/io/core/BytePacketBuilder;", "j$/time/Instant", "validFrom", "validUntil", "", "writeX509Info", "(Lio/ktor/utils/io/core/BytePacketBuilder;Ljava/lang/String;Ljavax/security/auth/x500/X500Principal;Ljavax/security/auth/x500/X500Principal;Ljava/security/PublicKey;Lj$/time/Instant;Lj$/time/Instant;Ljava/util/List;Ljava/util/List;Lio/ktor/network/tls/certificates/KeyType;)V", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "content", "extKeyUsage", "(Lio/ktor/utils/io/core/BytePacketBuilder;Lkotlin/jvm/functions/Function1;)V", "clientAuth", "(Lio/ktor/utils/io/core/BytePacketBuilder;)V", "serverAuth", "subjectAlternativeNames", "(Lio/ktor/utils/io/core/BytePacketBuilder;Ljava/util/List;Ljava/util/List;)V", "caExtension", "writeAlgorithmIdentifier", "(Lio/ktor/utils/io/core/BytePacketBuilder;Ljava/lang/String;)V", "id", "builder", "writeX509Extension", "(Lio/ktor/utils/io/core/BytePacketBuilder;ILkotlin/jvm/functions/Function1;)V", "dName", "writeX500Principal", "(Lio/ktor/utils/io/core/BytePacketBuilder;Ljavax/security/auth/x500/X500Principal;)V", "writeCertificate", "(Lio/ktor/utils/io/core/BytePacketBuilder;Ljavax/security/auth/x500/X500Principal;Ljavax/security/auth/x500/X500Principal;Ljava/security/PublicKey;Ljava/lang/String;Lj$/time/Instant;Lj$/time/Instant;Ljava/util/List;Ljava/util/List;Ljava/security/KeyPair;Lio/ktor/network/tls/certificates/KeyType;)V", "v", "writeVersion", "(Lio/ktor/utils/io/core/BytePacketBuilder;I)V", "block", "writeDerOctetString", "", "array", "unused", "writeDerBitString", "(Lio/ktor/utils/io/core/BytePacketBuilder;[BI)V", "date", "writeDerUTCTime", "(Lio/ktor/utils/io/core/BytePacketBuilder;Lj$/time/Instant;)V", "writeDerGeneralizedTime", "s", "type", "writeDerUTF8String", "(Lio/ktor/utils/io/core/BytePacketBuilder;Ljava/lang/String;I)V", "writeDerNull", "writeDerSequence", "Lio/ktor/network/tls/OID;", "identifier", "writeDerObjectIdentifier", "(Lio/ktor/utils/io/core/BytePacketBuilder;Lio/ktor/network/tls/OID;)V", "", "(Lio/ktor/utils/io/core/BytePacketBuilder;[I)V", "Ljava/math/BigInteger;", "value", "writeAsnInt", "(Lio/ktor/utils/io/core/BytePacketBuilder;Ljava/math/BigInteger;)V", "length", "writeDerLength", "kind", "typeIdentifier", "", "simpleType", "writeDerType", "(Lio/ktor/utils/io/core/BytePacketBuilder;IIZ)V", "derLength", "(I)I", "writeDerBoolean", "(Lio/ktor/utils/io/core/BytePacketBuilder;Z)V", "Lkotlin/UByte;", "toUByte", "(Z)B", "writeDerInt", "DEFAULT_PRINCIPAL", "Ljavax/security/auth/x500/X500Principal;", "getDEFAULT_PRINCIPAL", "()Ljavax/security/auth/x500/X500Principal;", "DEFAULT_CA_PRINCIPAL", "Ljavax/net/ssl/TrustManager;", "getTrustManagers", "(Ljava/security/KeyStore;)Ljava/util/List;", "trustManagers", "ktor-network-tls-certificates"}, k = 2, mv = {1, 8, 0})
/* compiled from: Certificates.kt */
public final class CertificatesKt {
    /* access modifiers changed from: private */
    public static final X500Principal DEFAULT_CA_PRINCIPAL = new X500Principal("CN=localhostCA, OU=Kotlin, O=JetBrains, C=RU");
    private static final X500Principal DEFAULT_PRINCIPAL = new X500Principal("CN=localhost, OU=Kotlin, O=JetBrains, C=RU");

    public static final X500Principal getDEFAULT_PRINCIPAL() {
        return DEFAULT_PRINCIPAL;
    }

    public static /* synthetic */ KeyStore generateCertificate$default(File file, String str, String str2, String str3, String str4, int i, KeyType keyType, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            file = null;
        }
        if ((i2 & 2) != 0) {
            str = "SHA1withRSA";
        }
        String str5 = str;
        if ((i2 & 4) != 0) {
            str2 = "mykey";
        }
        String str6 = str2;
        if ((i2 & 8) != 0) {
            str3 = "changeit";
        }
        String str7 = str3;
        String str8 = (i2 & 16) != 0 ? str7 : str4;
        int i3 = (i2 & 32) != 0 ? 1024 : i;
        if ((i2 & 64) != 0) {
            keyType = KeyType.Server;
        }
        return generateCertificate(file, str5, str6, str7, str8, i3, keyType);
    }

    public static final KeyStore generateCertificate(File file, String str, String str2, String str3, String str4, int i, KeyType keyType) {
        Intrinsics.checkNotNullParameter(str, "algorithm");
        Intrinsics.checkNotNullParameter(str2, "keyAlias");
        Intrinsics.checkNotNullParameter(str3, "keyPassword");
        Intrinsics.checkNotNullParameter(str4, "jksPassword");
        Intrinsics.checkNotNullParameter(keyType, "keyType");
        KeyStore buildKeyStore = BuildersKt.buildKeyStore(new CertificatesKt$generateCertificate$keyStore$1(str2, str, str3, i, keyType));
        buildKeyStore.setCertificateEntry(str2 + "Cert", buildKeyStore.getCertificate(str2));
        if (file != null) {
            BuildersKt.saveToFile(buildKeyStore, file, str4);
        }
        return buildKeyStore;
    }

    /* renamed from: generateX509Certificate-Tu6dINM$default  reason: not valid java name */
    public static /* synthetic */ X509Certificate m1474generateX509CertificateTu6dINM$default(X500Principal x500Principal, X500Principal x500Principal2, PublicKey publicKey, KeyPair keyPair, String str, long j, KeyType keyType, List list, List list2, int i, Object obj) {
        long j2;
        KeyType keyType2;
        int i2 = i;
        if ((i2 & 32) != 0) {
            Duration.Companion companion = Duration.Companion;
            j2 = DurationKt.toDuration(3, DurationUnit.DAYS);
        } else {
            j2 = j;
        }
        if ((i2 & 64) != 0) {
            keyType2 = KeyType.Server;
        } else {
            keyType2 = keyType;
        }
        return m1473generateX509CertificateTu6dINM(x500Principal, x500Principal2, publicKey, keyPair, str, j2, keyType2, (i2 & 128) != 0 ? CollectionsKt.listOf("127.0.0.1", "localhost") : list, (i2 & 256) != 0 ? CollectionsKt.listOf(Inet4Address.getByName("127.0.0.1")) : list2);
    }

    /* renamed from: generateX509Certificate-Tu6dINM  reason: not valid java name */
    public static final X509Certificate m1473generateX509CertificateTu6dINM(X500Principal x500Principal, X500Principal x500Principal2, PublicKey publicKey, KeyPair keyPair, String str, long j, KeyType keyType, List<String> list, List<? extends InetAddress> list2) {
        Intrinsics.checkNotNullParameter(x500Principal, "subject");
        Intrinsics.checkNotNullParameter(x500Principal2, "issuer");
        Intrinsics.checkNotNullParameter(publicKey, "publicKey");
        Intrinsics.checkNotNullParameter(keyPair, "signerKeyPair");
        Intrinsics.checkNotNullParameter(str, "algorithm");
        Intrinsics.checkNotNullParameter(keyType, "keyType");
        Intrinsics.checkNotNullParameter(list, "domains");
        Intrinsics.checkNotNullParameter(list2, "ipAddresses");
        Instant now = Instant.now();
        BytePacketBuilder bytePacketBuilder = new BytePacketBuilder((ObjectPool) null, 1, (DefaultConstructorMarker) null);
        try {
            j$.time.Duration ofSeconds = j$.time.Duration.ofSeconds(Duration.m1013getInWholeSecondsimpl(j), (long) Duration.m1015getNanosecondsComponentimpl(j));
            Intrinsics.checkNotNullExpressionValue(ofSeconds, "toJavaDuration-LRDsOJo");
            Instant plus = now.plus(ofSeconds);
            Intrinsics.checkNotNullExpressionValue(now, "now");
            Intrinsics.checkNotNullExpressionValue(plus, "plus(validityDuration.toJavaDuration())");
            writeCertificate(bytePacketBuilder, x500Principal2, x500Principal, publicKey, str, now, plus, list, list2, keyPair, keyType);
            Certificate generateCertificate = CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(StringsKt.readBytes$default(bytePacketBuilder.build(), 0, 1, (Object) null)));
            generateCertificate.verify(keyPair.getPublic());
            Intrinsics.checkNotNull(generateCertificate, "null cannot be cast to non-null type java.security.cert.X509Certificate");
            return (X509Certificate) generateCertificate;
        } catch (Throwable th) {
            bytePacketBuilder.release();
            throw th;
        }
    }

    public static /* synthetic */ KeyStore generateCertificate$default(KeyStore keyStore, File file, String str, String str2, String str3, String str4, int i, String str5, String str6, KeyType keyType, int i2, Object obj) {
        KeyType keyType2;
        int i3 = i2;
        File file2 = (i3 & 1) != 0 ? null : file;
        String str7 = (i3 & 2) != 0 ? "SHA1withRSA" : str;
        String str8 = "mykey";
        String str9 = (i3 & 4) != 0 ? str8 : str2;
        String str10 = "changeit";
        String str11 = (i3 & 8) != 0 ? str10 : str3;
        String str12 = (i3 & 16) != 0 ? str11 : str4;
        int i4 = (i3 & 32) != 0 ? 1024 : i;
        if ((i3 & 64) == 0) {
            str8 = str5;
        }
        if ((i3 & 128) == 0) {
            str10 = str6;
        }
        if ((i3 & 256) != 0) {
            keyType2 = KeyType.Server;
        } else {
            keyType2 = keyType;
        }
        return generateCertificate(keyStore, file2, str7, str9, str11, str12, i4, str8, str10, keyType2);
    }

    public static final KeyStore generateCertificate(KeyStore keyStore, File file, String str, String str2, String str3, String str4, int i, String str5, String str6, KeyType keyType) {
        KeyStore keyStore2 = keyStore;
        File file2 = file;
        String str7 = str4;
        String str8 = str5;
        Intrinsics.checkNotNullParameter(keyStore, "<this>");
        Intrinsics.checkNotNullParameter(str, "algorithm");
        String str9 = str2;
        Intrinsics.checkNotNullParameter(str9, "keyAlias");
        String str10 = str3;
        Intrinsics.checkNotNullParameter(str10, "keyPassword");
        Intrinsics.checkNotNullParameter(str7, "jksPassword");
        Intrinsics.checkNotNullParameter(str8, "caKeyAlias");
        Intrinsics.checkNotNullParameter(str6, "caPassword");
        KeyType keyType2 = keyType;
        Intrinsics.checkNotNullParameter(keyType2, "keyType");
        Certificate certificate = keyStore.getCertificate(str8);
        PublicKey publicKey = certificate.getPublicKey();
        char[] charArray = str6.toCharArray();
        Intrinsics.checkNotNullExpressionValue(charArray, "this as java.lang.String).toCharArray()");
        Key key = keyStore.getKey(str8, charArray);
        Intrinsics.checkNotNull(key, "null cannot be cast to non-null type java.security.PrivateKey");
        KeyStore buildKeyStore = BuildersKt.buildKeyStore(new CertificatesKt$generateCertificate$keyStore$2(str9, str, str10, i, keyType2, new KeyPair(publicKey, (PrivateKey) key), certificate));
        if (file2 != null) {
            BuildersKt.saveToFile(buildKeyStore, file, str7);
        }
        return buildKeyStore;
    }

    public static /* synthetic */ KeyStore trustStore$default(KeyStore keyStore, File file, char[] cArr, int i, Object obj) {
        if ((i & 1) != 0) {
            file = null;
        }
        if ((i & 2) != 0) {
            cArr = "changeit".toCharArray();
            Intrinsics.checkNotNullExpressionValue(cArr, "this as java.lang.String).toCharArray()");
        }
        return trustStore(keyStore, file, cArr);
    }

    public static final KeyStore trustStore(KeyStore keyStore, File file, char[] cArr) {
        File parentFile;
        Intrinsics.checkNotNullParameter(keyStore, "<this>");
        Intrinsics.checkNotNullParameter(cArr, "password");
        KeyStore instance = KeyStore.getInstance("JKS");
        Intrinsics.checkNotNull(instance);
        instance.load((InputStream) null, (char[]) null);
        Enumeration<String> aliases = keyStore.aliases();
        Intrinsics.checkNotNullExpressionValue(aliases, "aliases()");
        ArrayList<T> list = Collections.list(aliases);
        Intrinsics.checkNotNullExpressionValue(list, "list(this)");
        for (T t : list) {
            Certificate certificate = keyStore.getCertificate(t);
            Intrinsics.checkNotNullExpressionValue(certificate, "getCertificate(alias)");
            instance.setCertificateEntry(t, certificate);
        }
        if (!(file == null || (parentFile = file.getParentFile()) == null)) {
            parentFile.mkdirs();
        }
        if (file != null) {
            Closeable fileOutputStream = new FileOutputStream(file);
            try {
                instance.store((FileOutputStream) fileOutputStream, cArr);
                Unit unit = Unit.INSTANCE;
                fileOutputStream.close();
            } catch (Throwable th) {
                CloseableJVMKt.addSuppressedInternal(th, th);
            }
        }
        return instance;
        throw th;
    }

    public static final List<TrustManager> getTrustManagers(KeyStore keyStore) {
        Intrinsics.checkNotNullParameter(keyStore, "<this>");
        TrustManagerFactory instance = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        instance.init(keyStore);
        TrustManager[] trustManagers = instance.getTrustManagers();
        Intrinsics.checkNotNullExpressionValue(trustManagers, "getInstance(TrustManager…Managers) }.trustManagers");
        return ArraysKt.toList((T[]) (Object[]) trustManagers);
    }

    static /* synthetic */ void writeX509Info$default(BytePacketBuilder bytePacketBuilder, String str, X500Principal x500Principal, X500Principal x500Principal2, PublicKey publicKey, Instant instant, Instant instant2, List list, List list2, KeyType keyType, int i, Object obj) {
        KeyType keyType2;
        if ((i & 256) != 0) {
            keyType2 = KeyType.Server;
        } else {
            keyType2 = keyType;
        }
        writeX509Info(bytePacketBuilder, str, x500Principal, x500Principal2, publicKey, instant, instant2, list, list2, keyType2);
    }

    private static final void writeX509Info(BytePacketBuilder bytePacketBuilder, String str, X500Principal x500Principal, X500Principal x500Principal2, PublicKey publicKey, Instant instant, Instant instant2, List<String> list, List<? extends InetAddress> list2, KeyType keyType) {
        BytePacketBuilder bytePacketBuilder2 = bytePacketBuilder;
        writeDerSequence(bytePacketBuilder, new CertificatesKt$writeX509Info$1(new BigInteger(64, new SecureRandom()), str, x500Principal, x500Principal2, publicKey, instant, instant2, keyType, list, list2));
    }

    /* access modifiers changed from: private */
    public static final void extKeyUsage(BytePacketBuilder bytePacketBuilder, Function1<? super BytePacketBuilder, Unit> function1) {
        writeDerSequence(bytePacketBuilder, new CertificatesKt$extKeyUsage$1(function1));
    }

    /* access modifiers changed from: private */
    public static final void clientAuth(BytePacketBuilder bytePacketBuilder) {
        writeDerSequence(bytePacketBuilder, CertificatesKt$clientAuth$1.INSTANCE);
    }

    /* access modifiers changed from: private */
    public static final void serverAuth(BytePacketBuilder bytePacketBuilder) {
        writeDerSequence(bytePacketBuilder, CertificatesKt$serverAuth$1.INSTANCE);
    }

    /* access modifiers changed from: private */
    public static final void subjectAlternativeNames(BytePacketBuilder bytePacketBuilder, List<String> list, List<? extends InetAddress> list2) {
        writeDerSequence(bytePacketBuilder, new CertificatesKt$subjectAlternativeNames$1(list, list2));
    }

    /* access modifiers changed from: private */
    public static final void caExtension(BytePacketBuilder bytePacketBuilder) {
        writeDerSequence(bytePacketBuilder, CertificatesKt$caExtension$1.INSTANCE);
    }

    /* access modifiers changed from: private */
    public static final void writeAlgorithmIdentifier(BytePacketBuilder bytePacketBuilder, String str) {
        writeDerSequence(bytePacketBuilder, new CertificatesKt$writeAlgorithmIdentifier$1(str));
    }

    /* access modifiers changed from: private */
    public static final void writeX509Extension(BytePacketBuilder bytePacketBuilder, int i, Function1<? super BytePacketBuilder, Unit> function1) {
        bytePacketBuilder.writeByte((byte) (i | 128));
        BytePacketBuilder bytePacketBuilder2 = new BytePacketBuilder((ObjectPool) null, 1, (DefaultConstructorMarker) null);
        try {
            function1.invoke(bytePacketBuilder2);
            ByteReadPacket build = bytePacketBuilder2.build();
            writeDerLength(bytePacketBuilder, (int) build.getRemaining());
            bytePacketBuilder.writePacket(build);
        } catch (Throwable th) {
            bytePacketBuilder2.release();
            throw th;
        }
    }

    /* access modifiers changed from: private */
    public static final void writeX500Principal(BytePacketBuilder bytePacketBuilder, X500Principal x500Principal) {
        byte[] encoded = x500Principal.getEncoded();
        Intrinsics.checkNotNullExpressionValue(encoded, "dName.encoded");
        OutputKt.writeFully$default((Output) bytePacketBuilder, encoded, 0, 0, 6, (Object) null);
    }

    static /* synthetic */ void writeCertificate$default(BytePacketBuilder bytePacketBuilder, X500Principal x500Principal, X500Principal x500Principal2, PublicKey publicKey, String str, Instant instant, Instant instant2, List list, List list2, KeyPair keyPair, KeyType keyType, int i, Object obj) {
        KeyType keyType2;
        if ((i & 512) != 0) {
            keyType2 = KeyType.Server;
        } else {
            keyType2 = keyType;
        }
        writeCertificate(bytePacketBuilder, x500Principal, x500Principal2, publicKey, str, instant, instant2, list, list2, keyPair, keyType2);
    }

    private static final void writeCertificate(BytePacketBuilder bytePacketBuilder, X500Principal x500Principal, X500Principal x500Principal2, PublicKey publicKey, String str, Instant instant, Instant instant2, List<String> list, List<? extends InetAddress> list2, KeyPair keyPair, KeyType keyType) {
        if (instant.compareTo(instant2) < 0) {
            BytePacketBuilder bytePacketBuilder2 = new BytePacketBuilder((ObjectPool) null, 1, (DefaultConstructorMarker) null);
            try {
                writeX509Info(bytePacketBuilder2, str, x500Principal, x500Principal2, publicKey, instant, instant2, list, list2, keyType);
                byte[] readBytes$default = StringsKt.readBytes$default(bytePacketBuilder2.build(), 0, 1, (Object) null);
                Signature instance = Signature.getInstance(str);
                instance.initSign(keyPair.getPrivate());
                instance.update(readBytes$default);
                BytePacketBuilder bytePacketBuilder3 = bytePacketBuilder;
                writeDerSequence(bytePacketBuilder, new CertificatesKt$writeCertificate$2(readBytes$default, instance.sign(), str));
            } catch (Throwable th) {
                bytePacketBuilder2.release();
                throw th;
            }
        } else {
            throw new IllegalArgumentException("validFrom must be before validUntil".toString());
        }
    }

    static /* synthetic */ void writeVersion$default(BytePacketBuilder bytePacketBuilder, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 2;
        }
        writeVersion(bytePacketBuilder, i);
    }

    /* access modifiers changed from: private */
    public static final void writeVersion(BytePacketBuilder bytePacketBuilder, int i) {
        writeDerType(bytePacketBuilder, 2, 0, false);
        BytePacketBuilder bytePacketBuilder2 = new BytePacketBuilder((ObjectPool) null, 1, (DefaultConstructorMarker) null);
        try {
            writeAsnInt(bytePacketBuilder2, i);
            ByteReadPacket build = bytePacketBuilder2.build();
            writeDerLength(bytePacketBuilder, (int) build.getRemaining());
            bytePacketBuilder.writePacket(build);
        } catch (Throwable th) {
            bytePacketBuilder2.release();
            throw th;
        }
    }

    static /* synthetic */ void writeDerBitString$default(BytePacketBuilder bytePacketBuilder, byte[] bArr, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        writeDerBitString(bytePacketBuilder, bArr, i);
    }

    private static final void writeDerBitString(BytePacketBuilder bytePacketBuilder, byte[] bArr, int i) {
        if (i < 0 || i >= 8) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        writeDerType(bytePacketBuilder, 0, 3, true);
        writeDerLength(bytePacketBuilder, bArr.length + 1);
        bytePacketBuilder.writeByte((byte) i);
        OutputKt.writeFully$default((Output) bytePacketBuilder, bArr, 0, 0, 6, (Object) null);
    }

    /* access modifiers changed from: private */
    public static final void writeDerUTCTime(BytePacketBuilder bytePacketBuilder, Instant instant) {
        String format = DateTimeFormatter.ofPattern("yyMMddHHmmss'Z'").format(instant.atZone(ZoneOffset.UTC));
        Intrinsics.checkNotNullExpressionValue(format, "ofPattern(\"yyMMddHHmmss'…e.atZone(ZoneOffset.UTC))");
        writeDerUTF8String(bytePacketBuilder, format, 23);
    }

    /* access modifiers changed from: private */
    public static final void writeDerGeneralizedTime(BytePacketBuilder bytePacketBuilder, Instant instant) {
        String format = DateTimeFormatter.ofPattern("yyyyMMddHHmmss'Z'").format(instant.atZone(ZoneOffset.UTC));
        Intrinsics.checkNotNullExpressionValue(format, "ofPattern(\"yyyyMMddHHmms…e.atZone(ZoneOffset.UTC))");
        writeDerUTF8String(bytePacketBuilder, format, 24);
    }

    static /* synthetic */ void writeDerUTF8String$default(BytePacketBuilder bytePacketBuilder, String str, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 12;
        }
        writeDerUTF8String(bytePacketBuilder, str, i);
    }

    /* access modifiers changed from: private */
    public static final void writeDerNull(BytePacketBuilder bytePacketBuilder) {
        OutputPrimitivesKt.writeShort(bytePacketBuilder, 1280);
    }

    /* access modifiers changed from: private */
    public static final void writeDerObjectIdentifier(BytePacketBuilder bytePacketBuilder, OID oid) {
        writeDerObjectIdentifier(bytePacketBuilder, oid.getAsArray());
    }

    private static final void writeDerObjectIdentifier(BytePacketBuilder bytePacketBuilder, int[] iArr) {
        int i;
        int i2 = 2;
        if (iArr.length >= 2) {
            int i3 = iArr[0];
            if (i3 < 0 || i3 >= 3) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            } else if (i3 == 2 || ((i = iArr[1]) >= 0 && i < 40)) {
                BytePacketBuilder bytePacketBuilder2 = new BytePacketBuilder((ObjectPool) null, 1, (DefaultConstructorMarker) null);
                try {
                    writeDerInt(bytePacketBuilder2, (iArr[0] * 40) + iArr[1]);
                    int lastIndex = ArraysKt.getLastIndex(iArr);
                    if (2 <= lastIndex) {
                        while (true) {
                            writeDerInt(bytePacketBuilder2, iArr[i2]);
                            if (i2 == lastIndex) {
                                break;
                            }
                            i2++;
                        }
                    }
                    ByteReadPacket build = bytePacketBuilder2.build();
                    writeDerType(bytePacketBuilder, 0, 6, true);
                    writeDerLength(bytePacketBuilder, (int) build.getRemaining());
                    bytePacketBuilder.writePacket(build);
                } catch (Throwable th) {
                    bytePacketBuilder2.release();
                    throw th;
                }
            } else {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
        } else {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
    }

    /* access modifiers changed from: private */
    public static final void writeAsnInt(BytePacketBuilder bytePacketBuilder, BigInteger bigInteger) {
        writeDerType(bytePacketBuilder, 0, 2, true);
        byte[] byteArray = bigInteger.toByteArray();
        writeDerLength(bytePacketBuilder, byteArray.length);
        Intrinsics.checkNotNullExpressionValue(byteArray, "encoded");
        OutputKt.writeFully$default((Output) bytePacketBuilder, byteArray, 0, 0, 6, (Object) null);
    }

    private static final void writeAsnInt(BytePacketBuilder bytePacketBuilder, int i) {
        boolean z = true;
        writeDerType(bytePacketBuilder, 0, 2, true);
        BytePacketBuilder bytePacketBuilder2 = new BytePacketBuilder((ObjectPool) null, 1, (DefaultConstructorMarker) null);
        for (int i2 = 0; i2 < 4; i2++) {
            int i3 = (i >>> ((3 - i2) * 8)) & 255;
            if (i3 != 0 || !z) {
                try {
                    bytePacketBuilder2.writeByte((byte) i3);
                    z = false;
                } catch (Throwable th) {
                    bytePacketBuilder2.release();
                    throw th;
                }
            }
        }
        ByteReadPacket build = bytePacketBuilder2.build();
        writeDerLength(bytePacketBuilder, (int) build.getRemaining());
        bytePacketBuilder.writePacket(build);
    }

    /* access modifiers changed from: private */
    public static final void writeDerLength(BytePacketBuilder bytePacketBuilder, int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        } else if (i <= 127) {
            bytePacketBuilder.writeByte((byte) i);
        } else if (i <= 255) {
            bytePacketBuilder.writeByte((byte) -127);
            bytePacketBuilder.writeByte((byte) i);
        } else if (i <= 65535) {
            bytePacketBuilder.writeByte((byte) -126);
            bytePacketBuilder.writeByte((byte) (i >>> 8));
            bytePacketBuilder.writeByte((byte) i);
        } else if (i <= 16777215) {
            bytePacketBuilder.writeByte((byte) -125);
            bytePacketBuilder.writeByte((byte) (i >>> 16));
            bytePacketBuilder.writeByte((byte) (255 & (i >>> 8)));
            bytePacketBuilder.writeByte((byte) i);
        } else {
            bytePacketBuilder.writeByte((byte) -124);
            bytePacketBuilder.writeByte((byte) (i >>> 24));
            bytePacketBuilder.writeByte((byte) ((i >>> 16) & 255));
            bytePacketBuilder.writeByte((byte) (255 & (i >>> 8)));
            bytePacketBuilder.writeByte((byte) i);
        }
    }

    private static final void writeDerType(BytePacketBuilder bytePacketBuilder, int i, int i2, boolean z) {
        if (i < 0 || i >= 4) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        } else if (i2 >= 0) {
            int i3 = 0;
            if (i2 < 0 || i2 >= 31) {
                int i4 = (i << 6) | 31;
                if (!z) {
                    i3 = 32;
                }
                bytePacketBuilder.writeByte((byte) (i4 | i3));
                writeDerInt(bytePacketBuilder, i2);
                return;
            }
            int i5 = (i << 6) | i2;
            if (!z) {
                i3 = 32;
            }
            bytePacketBuilder.writeByte((byte) (i5 | i3));
        } else {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
    }

    private static final int derLength(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        } else if (i == 0) {
            return 0;
        } else {
            int i2 = 1;
            int i3 = 127;
            while ((i & i3) != i) {
                i3 |= i3 << 7;
                i2++;
            }
            return i2;
        }
    }

    /* access modifiers changed from: private */
    public static final void writeDerBoolean(BytePacketBuilder bytePacketBuilder, boolean z) {
        writeDerType(bytePacketBuilder, 0, 1, true);
        writeDerLength(bytePacketBuilder, 1);
        bytePacketBuilder.writeByte(toUByte(z));
    }

    private static final byte toUByte(boolean z) {
        if (z) {
            return UByte.m1792constructorimpl((byte) 255);
        }
        return UByte.m1792constructorimpl((byte) 0);
    }

    private static final void writeDerInt(BytePacketBuilder bytePacketBuilder, int i) {
        if (i >= 0) {
            int derLength = derLength(i);
            for (int i2 = 0; i2 < derLength; i2++) {
                int i3 = (i >> (((derLength - i2) - 1) * 7)) & 127;
                if (i2 == derLength - 1) {
                    bytePacketBuilder.writeByte((byte) i3);
                } else {
                    bytePacketBuilder.writeByte((byte) (i3 | 128));
                }
            }
            return;
        }
        throw new IllegalArgumentException("Failed requirement.".toString());
    }

    /* access modifiers changed from: private */
    public static final void writeDerOctetString(BytePacketBuilder bytePacketBuilder, Function1<? super BytePacketBuilder, Unit> function1) {
        BytePacketBuilder bytePacketBuilder2 = new BytePacketBuilder((ObjectPool) null, 1, (DefaultConstructorMarker) null);
        try {
            function1.invoke(bytePacketBuilder2);
            ByteReadPacket build = bytePacketBuilder2.build();
            writeDerType(bytePacketBuilder, 0, 4, true);
            writeDerLength(bytePacketBuilder, (int) build.getRemaining());
            bytePacketBuilder.writePacket(build);
        } catch (Throwable th) {
            bytePacketBuilder2.release();
            throw th;
        }
    }

    private static final void writeDerUTF8String(BytePacketBuilder bytePacketBuilder, String str, int i) {
        BytePacketBuilder bytePacketBuilder2 = new BytePacketBuilder((ObjectPool) null, 1, (DefaultConstructorMarker) null);
        try {
            StringsKt.writeText$default((Output) bytePacketBuilder2, (CharSequence) str, 0, 0, (Charset) null, 14, (Object) null);
            ByteReadPacket build = bytePacketBuilder2.build();
            writeDerType(bytePacketBuilder, 0, i, true);
            writeDerLength(bytePacketBuilder, (int) build.getRemaining());
            bytePacketBuilder.writePacket(build);
        } catch (Throwable th) {
            bytePacketBuilder2.release();
            throw th;
        }
    }

    /* access modifiers changed from: private */
    public static final void writeDerSequence(BytePacketBuilder bytePacketBuilder, Function1<? super BytePacketBuilder, Unit> function1) {
        BytePacketBuilder bytePacketBuilder2 = new BytePacketBuilder((ObjectPool) null, 1, (DefaultConstructorMarker) null);
        try {
            function1.invoke(bytePacketBuilder2);
            ByteReadPacket build = bytePacketBuilder2.build();
            writeDerType(bytePacketBuilder, 0, 16, false);
            writeDerLength(bytePacketBuilder, (int) build.getRemaining());
            bytePacketBuilder.writePacket(build);
        } catch (Throwable th) {
            bytePacketBuilder2.release();
            throw th;
        }
    }
}
