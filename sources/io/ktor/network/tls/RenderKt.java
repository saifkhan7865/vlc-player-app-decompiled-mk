package io.ktor.network.tls;

import androidx.leanback.preference.LeanbackPreferenceDialogFragment;
import io.ktor.network.tls.extensions.HashAndSign;
import io.ktor.network.tls.extensions.NamedCurve;
import io.ktor.network.tls.extensions.NamedCurvesKt;
import io.ktor.network.tls.extensions.PointFormat;
import io.ktor.network.tls.extensions.PointFormatKt;
import io.ktor.network.tls.extensions.SignatureAlgorithmKt;
import io.ktor.network.tls.extensions.TLSExtensionType;
import io.ktor.utils.io.core.BytePacketBuilder;
import io.ktor.utils.io.core.ByteReadPacket;
import io.ktor.utils.io.core.Output;
import io.ktor.utils.io.core.OutputKt;
import io.ktor.utils.io.core.OutputPrimitivesKt;
import io.ktor.utils.io.core.StringsKt;
import io.ktor.utils.io.pool.ObjectPool;
import java.nio.charset.Charset;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECPoint;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.cms.CMSAttributeTableGenerator;

@Metadata(d1 = {"\u0000\u0001\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0018\u0010\u0003\u001a\u00020\u00042\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0002\u001a\u0018\u0010\b\u001a\u00020\u00042\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0006H\u0002\u001a\u0010\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\rH\u0002\u001a\u0018\u0010\u000e\u001a\u00020\u00042\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u0006H\u0002\u001a\u0018\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0000\u001a\"\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0018\u001a\u00020\u0001H\u0000\u001a\u001c\u0010\u0019\u001a\u00020\u001a*\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00132\u0006\u0010\u001d\u001a\u00020\u0001H\u0002\u001a\u001c\u0010\u001e\u001a\u00020\u001a*\u00020\u001b2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010\u001d\u001a\u00020\u0001H\u0000\u001a$\u0010!\u001a\u00020\u001a*\u00020\u001b2\u0006\u0010\"\u001a\u00020\u00132\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&H\u0000\u001a\u0014\u0010'\u001a\u00020\u001a*\u00020\u001b2\u0006\u0010(\u001a\u00020$H\u0000\u001a\u001d\u0010)\u001a\u00020\u001a*\u00020*2\u0006\u0010+\u001a\u00020,H@ø\u0001\u0000¢\u0006\u0002\u0010-\u001a\u001f\u0010.\u001a\u00020\u001a*\u00020\u001b2\f\u0010/\u001a\b\u0012\u0004\u0012\u00020100H\u0000¢\u0006\u0002\u00102\u001a>\u00103\u001a\u00020\u001a*\u00020\u001b2\u0006\u00104\u001a\u0002052\f\u00106\u001a\b\u0012\u0004\u0012\u0002070\u00062\u0006\u0010%\u001a\u00020\u00132\u0006\u00108\u001a\u00020\u00132\n\b\u0002\u00109\u001a\u0004\u0018\u00010\rH\u0000\u001a\u001c\u0010:\u001a\u00020\u001a*\u00020\u001b2\u0006\u0010;\u001a\u00020<2\u0006\u0010\u0018\u001a\u00020\u0001H\u0000\u001a\u0014\u0010=\u001a\u00020\u001a*\u00020\u001b2\u0006\u0010>\u001a\u00020\u0001H\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006?"}, d2 = {"MAX_CURVES_QUANTITY", "", "MAX_SERVER_NAME_LENGTH", "buildECCurvesExtension", "Lio/ktor/utils/io/core/ByteReadPacket;", "curves", "", "Lio/ktor/network/tls/extensions/NamedCurve;", "buildECPointFormatExtension", "formats", "Lio/ktor/network/tls/extensions/PointFormat;", "buildServerNameExtension", "name", "", "buildSignatureAlgorithmsExtension", "algorithms", "Lio/ktor/network/tls/extensions/HashAndSign;", "finished", "digest", "", "secretKey", "Ljavax/crypto/SecretKey;", "serverFinished", "handshakeHash", "length", "writeAligned", "", "Lio/ktor/utils/io/core/BytePacketBuilder;", "src", "fieldSize", "writeECPoint", "point", "Ljava/security/spec/ECPoint;", "writeEncryptedPreMasterSecret", "preSecret", "publicKey", "Ljava/security/PublicKey;", "random", "Ljava/security/SecureRandom;", "writePublicKeyUncompressed", "key", "writeRecord", "Lio/ktor/utils/io/ByteWriteChannel;", "record", "Lio/ktor/network/tls/TLSRecord;", "(Lio/ktor/utils/io/ByteWriteChannel;Lio/ktor/network/tls/TLSRecord;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "writeTLSCertificates", "certificates", "", "Ljava/security/cert/X509Certificate;", "(Lio/ktor/utils/io/core/BytePacketBuilder;[Ljava/security/cert/X509Certificate;)V", "writeTLSClientHello", "version", "Lio/ktor/network/tls/TLSVersion;", "suites", "Lio/ktor/network/tls/CipherSuite;", "sessionId", "serverName", "writeTLSHandshakeType", "type", "Lio/ktor/network/tls/TLSHandshakeType;", "writeTripleByteLength", "value", "ktor-network-tls"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: Render.kt */
public final class RenderKt {
    private static final int MAX_CURVES_QUANTITY = 16382;
    private static final int MAX_SERVER_NAME_LENGTH = 32762;

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v9, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v9, resolved type: io.ktor.network.tls.TLSRecord} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0078  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00a8 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00a9  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00c1 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00d8 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00ea A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0028  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object writeRecord(io.ktor.utils.io.ByteWriteChannel r9, io.ktor.network.tls.TLSRecord r10, kotlin.coroutines.Continuation<? super kotlin.Unit> r11) {
        /*
            boolean r0 = r11 instanceof io.ktor.network.tls.RenderKt$writeRecord$1
            if (r0 == 0) goto L_0x0014
            r0 = r11
            io.ktor.network.tls.RenderKt$writeRecord$1 r0 = (io.ktor.network.tls.RenderKt$writeRecord$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r11 = r0.label
            int r11 = r11 - r2
            r0.label = r11
            goto L_0x0019
        L_0x0014:
            io.ktor.network.tls.RenderKt$writeRecord$1 r0 = new io.ktor.network.tls.RenderKt$writeRecord$1
            r0.<init>(r11)
        L_0x0019:
            java.lang.Object r11 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 5
            r4 = 4
            r5 = 3
            r6 = 2
            r7 = 1
            if (r2 == 0) goto L_0x0078
            if (r2 == r7) goto L_0x006b
            if (r2 == r6) goto L_0x005f
            if (r2 == r5) goto L_0x0053
            if (r2 == r4) goto L_0x0043
            if (r2 != r3) goto L_0x003b
            java.lang.Object r9 = r0.L$0
            io.ktor.utils.io.ByteWriteChannel r9 = (io.ktor.utils.io.ByteWriteChannel) r9
            kotlin.ResultKt.throwOnFailure(r11)
            goto L_0x00eb
        L_0x003b:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L_0x0043:
            java.lang.Object r9 = r0.L$1
            io.ktor.network.tls.TLSRecord r9 = (io.ktor.network.tls.TLSRecord) r9
            java.lang.Object r10 = r0.L$0
            io.ktor.utils.io.ByteWriteChannel r10 = (io.ktor.utils.io.ByteWriteChannel) r10
            kotlin.ResultKt.throwOnFailure(r11)
        L_0x004e:
            r8 = r10
            r10 = r9
            r9 = r8
            goto L_0x00d9
        L_0x0053:
            java.lang.Object r9 = r0.L$1
            io.ktor.network.tls.TLSRecord r9 = (io.ktor.network.tls.TLSRecord) r9
            java.lang.Object r10 = r0.L$0
            io.ktor.utils.io.ByteWriteChannel r10 = (io.ktor.utils.io.ByteWriteChannel) r10
            kotlin.ResultKt.throwOnFailure(r11)
            goto L_0x00c2
        L_0x005f:
            java.lang.Object r9 = r0.L$1
            io.ktor.network.tls.TLSRecord r9 = (io.ktor.network.tls.TLSRecord) r9
            java.lang.Object r10 = r0.L$0
            io.ktor.utils.io.ByteWriteChannel r10 = (io.ktor.utils.io.ByteWriteChannel) r10
            kotlin.ResultKt.throwOnFailure(r11)
            goto L_0x00ac
        L_0x006b:
            java.lang.Object r9 = r0.L$1
            r10 = r9
            io.ktor.network.tls.TLSRecord r10 = (io.ktor.network.tls.TLSRecord) r10
            java.lang.Object r9 = r0.L$0
            io.ktor.utils.io.ByteWriteChannel r9 = (io.ktor.utils.io.ByteWriteChannel) r9
            kotlin.ResultKt.throwOnFailure(r11)
            goto L_0x0091
        L_0x0078:
            kotlin.ResultKt.throwOnFailure(r11)
            io.ktor.network.tls.TLSRecordType r11 = r10.getType()
            int r11 = r11.getCode()
            byte r11 = (byte) r11
            r0.L$0 = r9
            r0.L$1 = r10
            r0.label = r7
            java.lang.Object r11 = r9.writeByte(r11, r0)
            if (r11 != r1) goto L_0x0091
            return r1
        L_0x0091:
            io.ktor.network.tls.TLSVersion r11 = r10.getVersion()
            int r11 = r11.getCode()
            int r11 = r11 >> 8
            byte r11 = (byte) r11
            r0.L$0 = r9
            r0.L$1 = r10
            r0.label = r6
            java.lang.Object r11 = r9.writeByte(r11, r0)
            if (r11 != r1) goto L_0x00a9
            return r1
        L_0x00a9:
            r8 = r10
            r10 = r9
            r9 = r8
        L_0x00ac:
            io.ktor.network.tls.TLSVersion r11 = r9.getVersion()
            int r11 = r11.getCode()
            byte r11 = (byte) r11
            r0.L$0 = r10
            r0.L$1 = r9
            r0.label = r5
            java.lang.Object r11 = r10.writeByte(r11, r0)
            if (r11 != r1) goto L_0x00c2
            return r1
        L_0x00c2:
            io.ktor.utils.io.core.ByteReadPacket r11 = r9.getPacket()
            long r5 = r11.getRemaining()
            int r11 = (int) r5
            short r11 = (short) r11
            r0.L$0 = r10
            r0.L$1 = r9
            r0.label = r4
            java.lang.Object r11 = r10.writeShort(r11, r0)
            if (r11 != r1) goto L_0x004e
            return r1
        L_0x00d9:
            io.ktor.utils.io.core.ByteReadPacket r10 = r10.getPacket()
            r0.L$0 = r9
            r11 = 0
            r0.L$1 = r11
            r0.label = r3
            java.lang.Object r10 = r9.writePacket(r10, r0)
            if (r10 != r1) goto L_0x00eb
            return r1
        L_0x00eb:
            r9.flush()
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.network.tls.RenderKt.writeRecord(io.ktor.utils.io.ByteWriteChannel, io.ktor.network.tls.TLSRecord, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static final void writeTLSHandshakeType(BytePacketBuilder bytePacketBuilder, TLSHandshakeType tLSHandshakeType, int i) {
        Intrinsics.checkNotNullParameter(bytePacketBuilder, "<this>");
        Intrinsics.checkNotNullParameter(tLSHandshakeType, "type");
        if (i <= 16777215) {
            OutputPrimitivesKt.writeInt(bytePacketBuilder, (tLSHandshakeType.getCode() << 24) | i);
            return;
        }
        throw new TLSException("TLS handshake size limit exceeded: " + i, (Throwable) null, 2, (DefaultConstructorMarker) null);
    }

    public static /* synthetic */ void writeTLSClientHello$default(BytePacketBuilder bytePacketBuilder, TLSVersion tLSVersion, List list, byte[] bArr, byte[] bArr2, String str, int i, Object obj) {
        if ((i & 16) != 0) {
            str = null;
        }
        writeTLSClientHello(bytePacketBuilder, tLSVersion, list, bArr, bArr2, str);
    }

    public static final void writeTLSClientHello(BytePacketBuilder bytePacketBuilder, TLSVersion tLSVersion, List<CipherSuite> list, byte[] bArr, byte[] bArr2, String str) {
        Intrinsics.checkNotNullParameter(bytePacketBuilder, "<this>");
        Intrinsics.checkNotNullParameter(tLSVersion, "version");
        Intrinsics.checkNotNullParameter(list, "suites");
        Intrinsics.checkNotNullParameter(bArr, "random");
        Intrinsics.checkNotNullParameter(bArr2, "sessionId");
        Output output = bytePacketBuilder;
        OutputPrimitivesKt.writeShort(output, (short) tLSVersion.getCode());
        OutputKt.writeFully$default(output, bArr, 0, 0, 6, (Object) null);
        int length = bArr2.length;
        if (length < 0 || length > 255 || length > bArr2.length) {
            throw new TLSException("Illegal sessionIdLength", (Throwable) null, 2, (DefaultConstructorMarker) null);
        }
        bytePacketBuilder.writeByte((byte) length);
        int i = 0;
        OutputKt.writeFully(output, bArr2, 0, length);
        OutputPrimitivesKt.writeShort(output, (short) (list.size() * 2));
        for (CipherSuite code : list) {
            OutputPrimitivesKt.writeShort(output, code.getCode());
        }
        bytePacketBuilder.writeByte((byte) 1);
        bytePacketBuilder.writeByte((byte) 0);
        ArrayList<ByteReadPacket> arrayList = new ArrayList<>();
        Collection collection = arrayList;
        collection.add(buildSignatureAlgorithmsExtension$default((List) null, 1, (Object) null));
        collection.add(buildECCurvesExtension$default((List) null, 1, (Object) null));
        collection.add(buildECPointFormatExtension$default((List) null, 1, (Object) null));
        if (str != null) {
            collection.add(buildServerNameExtension(str));
        }
        for (ByteReadPacket remaining : arrayList) {
            i += (int) remaining.getRemaining();
        }
        OutputPrimitivesKt.writeShort(output, (short) i);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ByteReadPacket byteReadPacket = (ByteReadPacket) it.next();
            Intrinsics.checkNotNullExpressionValue(byteReadPacket, "e");
            bytePacketBuilder.writePacket(byteReadPacket);
        }
    }

    public static final void writeEncryptedPreMasterSecret(BytePacketBuilder bytePacketBuilder, byte[] bArr, PublicKey publicKey, SecureRandom secureRandom) {
        Intrinsics.checkNotNullParameter(bytePacketBuilder, "<this>");
        Intrinsics.checkNotNullParameter(bArr, "preSecret");
        Intrinsics.checkNotNullParameter(publicKey, "publicKey");
        Intrinsics.checkNotNullParameter(secureRandom, "random");
        if (bArr.length == 48) {
            Cipher instance = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            Intrinsics.checkNotNull(instance);
            instance.init(1, publicKey, secureRandom);
            byte[] doFinal = instance.doFinal(bArr);
            if (doFinal.length <= 65535) {
                Output output = bytePacketBuilder;
                OutputPrimitivesKt.writeShort(output, (short) doFinal.length);
                Intrinsics.checkNotNullExpressionValue(doFinal, "encryptedSecret");
                OutputKt.writeFully$default(output, doFinal, 0, 0, 6, (Object) null);
                return;
            }
            throw new TLSException("Encrypted premaster secret is too long", (Throwable) null, 2, (DefaultConstructorMarker) null);
        }
        throw new IllegalArgumentException("Failed requirement.".toString());
    }

    public static /* synthetic */ byte[] serverFinished$default(byte[] bArr, SecretKey secretKey, int i, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            i = 12;
        }
        return serverFinished(bArr, secretKey, i);
    }

    public static final byte[] serverFinished(byte[] bArr, SecretKey secretKey, int i) {
        Intrinsics.checkNotNullParameter(bArr, "handshakeHash");
        Intrinsics.checkNotNullParameter(secretKey, "secretKey");
        return HashesKt.PRF(secretKey, KeysKt.getSERVER_FINISHED_LABEL(), bArr, i);
    }

    public static final void writePublicKeyUncompressed(BytePacketBuilder bytePacketBuilder, PublicKey publicKey) {
        Intrinsics.checkNotNullParameter(bytePacketBuilder, "<this>");
        Intrinsics.checkNotNullParameter(publicKey, LeanbackPreferenceDialogFragment.ARG_KEY);
        if (publicKey instanceof ECPublicKey) {
            ECPublicKey eCPublicKey = (ECPublicKey) publicKey;
            int fieldSize = eCPublicKey.getParams().getCurve().getField().getFieldSize();
            ECPoint w = eCPublicKey.getW();
            Intrinsics.checkNotNullExpressionValue(w, "key.w");
            writeECPoint(bytePacketBuilder, w, fieldSize);
            return;
        }
        throw new TLSException("Unsupported public key type: " + publicKey, (Throwable) null, 2, (DefaultConstructorMarker) null);
    }

    static /* synthetic */ ByteReadPacket buildSignatureAlgorithmsExtension$default(List<HashAndSign> list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = SignatureAlgorithmKt.getSupportedSignatureAlgorithms();
        }
        return buildSignatureAlgorithmsExtension(list);
    }

    static /* synthetic */ ByteReadPacket buildECCurvesExtension$default(List<NamedCurve> list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = NamedCurvesKt.getSupportedNamedCurves();
        }
        return buildECCurvesExtension(list);
    }

    static /* synthetic */ ByteReadPacket buildECPointFormatExtension$default(List<PointFormat> list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = PointFormatKt.getSupportedPointFormats();
        }
        return buildECPointFormatExtension(list);
    }

    private static final void writeTripleByteLength(BytePacketBuilder bytePacketBuilder, int i) {
        bytePacketBuilder.writeByte((byte) ((i >>> 16) & 255));
        OutputPrimitivesKt.writeShort(bytePacketBuilder, (short) (i & 65535));
    }

    public static final void writeTLSCertificates(BytePacketBuilder bytePacketBuilder, X509Certificate[] x509CertificateArr) {
        Intrinsics.checkNotNullParameter(bytePacketBuilder, "<this>");
        Intrinsics.checkNotNullParameter(x509CertificateArr, "certificates");
        BytePacketBuilder bytePacketBuilder2 = new BytePacketBuilder((ObjectPool) null, 1, (DefaultConstructorMarker) null);
        try {
            for (X509Certificate encoded : x509CertificateArr) {
                byte[] encoded2 = encoded.getEncoded();
                Intrinsics.checkNotNull(encoded2);
                writeTripleByteLength(bytePacketBuilder2, encoded2.length);
                OutputKt.writeFully$default((Output) bytePacketBuilder2, encoded2, 0, 0, 6, (Object) null);
            }
            ByteReadPacket build = bytePacketBuilder2.build();
            writeTripleByteLength(bytePacketBuilder, (int) build.getRemaining());
            bytePacketBuilder.writePacket(build);
        } catch (Throwable th) {
            bytePacketBuilder2.release();
            throw th;
        }
    }

    public static final ByteReadPacket finished(byte[] bArr, SecretKey secretKey) {
        Intrinsics.checkNotNullParameter(bArr, CMSAttributeTableGenerator.DIGEST);
        Intrinsics.checkNotNullParameter(secretKey, "secretKey");
        BytePacketBuilder bytePacketBuilder = new BytePacketBuilder((ObjectPool) null, 1, (DefaultConstructorMarker) null);
        try {
            OutputKt.writeFully$default((Output) bytePacketBuilder, HashesKt.PRF(secretKey, KeysKt.getCLIENT_FINISHED_LABEL(), bArr, 12), 0, 0, 6, (Object) null);
            return bytePacketBuilder.build();
        } catch (Throwable th) {
            bytePacketBuilder.release();
            throw th;
        }
    }

    public static final void writeECPoint(BytePacketBuilder bytePacketBuilder, ECPoint eCPoint, int i) {
        Intrinsics.checkNotNullParameter(bytePacketBuilder, "<this>");
        Intrinsics.checkNotNullParameter(eCPoint, "point");
        BytePacketBuilder bytePacketBuilder2 = new BytePacketBuilder((ObjectPool) null, 1, (DefaultConstructorMarker) null);
        try {
            bytePacketBuilder2.writeByte((byte) 4);
            byte[] byteArray = eCPoint.getAffineX().toByteArray();
            Intrinsics.checkNotNullExpressionValue(byteArray, "point.affineX.toByteArray()");
            writeAligned(bytePacketBuilder2, byteArray, i);
            byte[] byteArray2 = eCPoint.getAffineY().toByteArray();
            Intrinsics.checkNotNullExpressionValue(byteArray2, "point.affineY.toByteArray()");
            writeAligned(bytePacketBuilder2, byteArray2, i);
            ByteReadPacket build = bytePacketBuilder2.build();
            bytePacketBuilder.writeByte((byte) ((int) build.getRemaining()));
            bytePacketBuilder.writePacket(build);
        } catch (Throwable th) {
            bytePacketBuilder2.release();
            throw th;
        }
    }

    private static final ByteReadPacket buildSignatureAlgorithmsExtension(List<HashAndSign> list) {
        BytePacketBuilder bytePacketBuilder = new BytePacketBuilder((ObjectPool) null, 1, (DefaultConstructorMarker) null);
        try {
            OutputPrimitivesKt.writeShort(bytePacketBuilder, TLSExtensionType.SIGNATURE_ALGORITHMS.getCode());
            int size = list.size() * 2;
            OutputPrimitivesKt.writeShort(bytePacketBuilder, (short) (size + 2));
            OutputPrimitivesKt.writeShort(bytePacketBuilder, (short) size);
            for (HashAndSign hashAndSign : list) {
                bytePacketBuilder.writeByte(hashAndSign.getHash().getCode());
                bytePacketBuilder.writeByte(hashAndSign.getSign().getCode());
            }
            return bytePacketBuilder.build();
        } catch (Throwable th) {
            bytePacketBuilder.release();
            throw th;
        }
    }

    private static final ByteReadPacket buildServerNameExtension(String str) {
        BytePacketBuilder bytePacketBuilder = new BytePacketBuilder((ObjectPool) null, 1, (DefaultConstructorMarker) null);
        try {
            if (str.length() < MAX_SERVER_NAME_LENGTH) {
                OutputPrimitivesKt.writeShort(bytePacketBuilder, TLSExtensionType.SERVER_NAME.getCode());
                OutputPrimitivesKt.writeShort(bytePacketBuilder, (short) (str.length() + 5));
                OutputPrimitivesKt.writeShort(bytePacketBuilder, (short) (str.length() + 3));
                bytePacketBuilder.writeByte((byte) 0);
                OutputPrimitivesKt.writeShort(bytePacketBuilder, (short) str.length());
                StringsKt.writeText$default((Output) bytePacketBuilder, (CharSequence) str, 0, 0, (Charset) null, 14, (Object) null);
                return bytePacketBuilder.build();
            }
            throw new IllegalArgumentException("Server name length limit exceeded: at most 32762 characters allowed".toString());
        } catch (Throwable th) {
            bytePacketBuilder.release();
            throw th;
        }
    }

    private static final ByteReadPacket buildECCurvesExtension(List<? extends NamedCurve> list) {
        BytePacketBuilder bytePacketBuilder = new BytePacketBuilder((ObjectPool) null, 1, (DefaultConstructorMarker) null);
        try {
            if (list.size() <= MAX_CURVES_QUANTITY) {
                OutputPrimitivesKt.writeShort(bytePacketBuilder, TLSExtensionType.ELLIPTIC_CURVES.getCode());
                int size = list.size() * 2;
                OutputPrimitivesKt.writeShort(bytePacketBuilder, (short) (size + 2));
                OutputPrimitivesKt.writeShort(bytePacketBuilder, (short) size);
                for (NamedCurve code : list) {
                    OutputPrimitivesKt.writeShort(bytePacketBuilder, code.getCode());
                }
                return bytePacketBuilder.build();
            }
            throw new IllegalArgumentException("Too many named curves provided: at most 16382 could be provided".toString());
        } catch (Throwable th) {
            bytePacketBuilder.release();
            throw th;
        }
    }

    private static final ByteReadPacket buildECPointFormatExtension(List<? extends PointFormat> list) {
        BytePacketBuilder bytePacketBuilder = new BytePacketBuilder((ObjectPool) null, 1, (DefaultConstructorMarker) null);
        try {
            OutputPrimitivesKt.writeShort(bytePacketBuilder, TLSExtensionType.EC_POINT_FORMAT.getCode());
            int size = list.size();
            OutputPrimitivesKt.writeShort(bytePacketBuilder, (short) (size + 1));
            bytePacketBuilder.writeByte((byte) size);
            for (PointFormat code : list) {
                bytePacketBuilder.writeByte(code.getCode());
            }
            return bytePacketBuilder.build();
        } catch (Throwable th) {
            bytePacketBuilder.release();
            throw th;
        }
    }

    private static final void writeAligned(BytePacketBuilder bytePacketBuilder, byte[] bArr, int i) {
        int i2 = (i + 7) >>> 3;
        int length = bArr.length;
        int i3 = 0;
        while (true) {
            if (i3 >= length) {
                i3 = -1;
                break;
            } else if (bArr[i3] != 0) {
                break;
            } else {
                i3++;
            }
        }
        int length2 = i2 - (bArr.length - i3);
        if (length2 > 0) {
            OutputKt.writeFully$default((Output) bytePacketBuilder, new byte[length2], 0, 0, 6, (Object) null);
        }
        OutputKt.writeFully((Output) bytePacketBuilder, bArr, i3, bArr.length - i3);
    }
}
