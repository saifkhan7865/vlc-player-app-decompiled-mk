package io.ktor.network.tls;

import com.google.common.base.Ascii;
import io.ktor.network.tls.extensions.NamedCurve;
import io.ktor.network.tls.extensions.TLSExtension;
import io.ktor.network.tls.extensions.TLSExtensionType;
import io.ktor.utils.io.core.BytePacketBuilder;
import io.ktor.utils.io.core.ByteReadPacket;
import io.ktor.utils.io.core.Input;
import io.ktor.utils.io.core.InputArraysKt;
import io.ktor.utils.io.core.InputPrimitivesKt;
import io.ktor.utils.io.core.Output;
import io.ktor.utils.io.core.OutputKt;
import io.ktor.utils.io.core.StringsKt;
import io.ktor.utils.io.pool.ObjectPool;
import io.netty.handler.codec.http2.Http2CodecUtil;
import java.io.ByteArrayInputStream;
import java.math.BigInteger;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.spec.ECPoint;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000F\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\f\u0010\u0002\u001a\u00020\u0003*\u00020\u0004H\u0000\u001a\u0014\u0010\u0005\u001a\u00020\u0006*\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0001H\u0000\u001a\u0015\u0010\b\u001a\u00020\u0001*\u00020\tH@ø\u0001\u0000¢\u0006\u0002\u0010\n\u001a\u0012\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f*\u00020\u0004H\u0000\u001a\f\u0010\u000e\u001a\u00020\u000f*\u00020\u0004H\u0000\u001a\u0015\u0010\u0010\u001a\u00020\u0011*\u00020\tH@ø\u0001\u0000¢\u0006\u0002\u0010\n\u001a\f\u0010\u0012\u001a\u00020\u0013*\u00020\u0004H\u0000\u001a\u0015\u0010\u0014\u001a\u00020\u0015*\u00020\tH@ø\u0001\u0000¢\u0006\u0002\u0010\n\u001a\f\u0010\u0014\u001a\u00020\u0015*\u00020\u0004H\u0002\u001a\f\u0010\u0016\u001a\u00020\u0001*\u00020\u0004H\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u0017"}, d2 = {"MAX_TLS_FRAME_SIZE", "", "readCurveParams", "Lio/ktor/network/tls/extensions/NamedCurve;", "Lio/ktor/utils/io/core/ByteReadPacket;", "readECPoint", "Ljava/security/spec/ECPoint;", "fieldSize", "readShortCompatible", "Lio/ktor/utils/io/ByteReadChannel;", "(Lio/ktor/utils/io/ByteReadChannel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readTLSCertificate", "", "Ljava/security/cert/Certificate;", "readTLSHandshake", "Lio/ktor/network/tls/TLSHandshake;", "readTLSRecord", "Lio/ktor/network/tls/TLSRecord;", "readTLSServerHello", "Lio/ktor/network/tls/TLSServerHello;", "readTLSVersion", "Lio/ktor/network/tls/TLSVersion;", "readTripleByteLength", "ktor-network-tls"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: Parser.kt */
public final class ParserKt {
    private static final int MAX_TLS_FRAME_SIZE = 18432;

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* compiled from: Parser.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(9:0|1|2|3|4|5|6|7|9) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        static {
            /*
                io.ktor.network.tls.ServerKeyExchangeType[] r0 = io.ktor.network.tls.ServerKeyExchangeType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                io.ktor.network.tls.ServerKeyExchangeType r1 = io.ktor.network.tls.ServerKeyExchangeType.NamedCurve     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                io.ktor.network.tls.ServerKeyExchangeType r1 = io.ktor.network.tls.ServerKeyExchangeType.ExplicitPrime     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                io.ktor.network.tls.ServerKeyExchangeType r1 = io.ktor.network.tls.ServerKeyExchangeType.ExplicitChar     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.ktor.network.tls.ParserKt.WhenMappings.<clinit>():void");
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x006c  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x009a A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00ab A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00ac  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00c0  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00d8  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0027  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object readTLSRecord(io.ktor.utils.io.ByteReadChannel r9, kotlin.coroutines.Continuation<? super io.ktor.network.tls.TLSRecord> r10) {
        /*
            boolean r0 = r10 instanceof io.ktor.network.tls.ParserKt$readTLSRecord$1
            if (r0 == 0) goto L_0x0014
            r0 = r10
            io.ktor.network.tls.ParserKt$readTLSRecord$1 r0 = (io.ktor.network.tls.ParserKt$readTLSRecord$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r10 = r0.label
            int r10 = r10 - r2
            r0.label = r10
            goto L_0x0019
        L_0x0014:
            io.ktor.network.tls.ParserKt$readTLSRecord$1 r0 = new io.ktor.network.tls.ParserKt$readTLSRecord$1
            r0.<init>(r10)
        L_0x0019:
            java.lang.Object r10 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 4
            r4 = 3
            r5 = 1
            r6 = 2
            if (r2 == 0) goto L_0x006c
            if (r2 == r5) goto L_0x0060
            if (r2 == r6) goto L_0x0054
            if (r2 == r4) goto L_0x0044
            if (r2 != r3) goto L_0x003c
            java.lang.Object r9 = r0.L$1
            io.ktor.network.tls.TLSVersion r9 = (io.ktor.network.tls.TLSVersion) r9
            java.lang.Object r0 = r0.L$0
            io.ktor.network.tls.TLSRecordType r0 = (io.ktor.network.tls.TLSRecordType) r0
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x00d0
        L_0x003c:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L_0x0044:
            java.lang.Object r9 = r0.L$2
            io.ktor.network.tls.TLSVersion r9 = (io.ktor.network.tls.TLSVersion) r9
            java.lang.Object r2 = r0.L$1
            io.ktor.network.tls.TLSRecordType r2 = (io.ktor.network.tls.TLSRecordType) r2
            java.lang.Object r4 = r0.L$0
            io.ktor.utils.io.ByteReadChannel r4 = (io.ktor.utils.io.ByteReadChannel) r4
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x00b1
        L_0x0054:
            java.lang.Object r9 = r0.L$1
            io.ktor.network.tls.TLSRecordType r9 = (io.ktor.network.tls.TLSRecordType) r9
            java.lang.Object r2 = r0.L$0
            io.ktor.utils.io.ByteReadChannel r2 = (io.ktor.utils.io.ByteReadChannel) r2
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x009b
        L_0x0060:
            java.lang.Object r9 = r0.L$1
            io.ktor.network.tls.TLSRecordType$Companion r9 = (io.ktor.network.tls.TLSRecordType.Companion) r9
            java.lang.Object r2 = r0.L$0
            io.ktor.utils.io.ByteReadChannel r2 = (io.ktor.utils.io.ByteReadChannel) r2
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x0082
        L_0x006c:
            kotlin.ResultKt.throwOnFailure(r10)
            io.ktor.network.tls.TLSRecordType$Companion r10 = io.ktor.network.tls.TLSRecordType.Companion
            r0.L$0 = r9
            r0.L$1 = r10
            r0.label = r5
            java.lang.Object r2 = r9.readByte(r0)
            if (r2 != r1) goto L_0x007e
            return r1
        L_0x007e:
            r8 = r2
            r2 = r9
            r9 = r10
            r10 = r8
        L_0x0082:
            java.lang.Number r10 = (java.lang.Number) r10
            byte r10 = r10.byteValue()
            r10 = r10 & 255(0xff, float:3.57E-43)
            io.ktor.network.tls.TLSRecordType r9 = r9.byCode(r10)
            r0.L$0 = r2
            r0.L$1 = r9
            r0.label = r6
            java.lang.Object r10 = readTLSVersion(r2, r0)
            if (r10 != r1) goto L_0x009b
            return r1
        L_0x009b:
            io.ktor.network.tls.TLSVersion r10 = (io.ktor.network.tls.TLSVersion) r10
            r0.L$0 = r2
            r0.L$1 = r9
            r0.L$2 = r10
            r0.label = r4
            java.lang.Object r4 = readShortCompatible(r2, r0)
            if (r4 != r1) goto L_0x00ac
            return r1
        L_0x00ac:
            r8 = r2
            r2 = r9
            r9 = r10
            r10 = r4
            r4 = r8
        L_0x00b1:
            java.lang.Number r10 = (java.lang.Number) r10
            int r10 = r10.intValue()
            r5 = 65535(0xffff, float:9.1834E-41)
            r10 = r10 & r5
            r5 = 18432(0x4800, float:2.5829E-41)
            r7 = 0
            if (r10 > r5) goto L_0x00d8
            r0.L$0 = r2
            r0.L$1 = r9
            r0.L$2 = r7
            r0.label = r3
            java.lang.Object r10 = r4.readPacket(r10, r0)
            if (r10 != r1) goto L_0x00cf
            return r1
        L_0x00cf:
            r0 = r2
        L_0x00d0:
            io.ktor.utils.io.core.ByteReadPacket r10 = (io.ktor.utils.io.core.ByteReadPacket) r10
            io.ktor.network.tls.TLSRecord r1 = new io.ktor.network.tls.TLSRecord
            r1.<init>(r0, r9, r10)
            return r1
        L_0x00d8:
            io.ktor.network.tls.TLSException r9 = new io.ktor.network.tls.TLSException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Illegal TLS frame size: "
            r0.<init>(r1)
            r0.append(r10)
            java.lang.String r10 = r0.toString()
            r9.<init>(r10, r7, r6, r7)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.network.tls.ParserKt.readTLSRecord(io.ktor.utils.io.ByteReadChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static final TLSHandshake readTLSHandshake(ByteReadPacket byteReadPacket) {
        Intrinsics.checkNotNullParameter(byteReadPacket, "<this>");
        TLSHandshake tLSHandshake = new TLSHandshake();
        int readInt = InputPrimitivesKt.readInt(byteReadPacket);
        tLSHandshake.setType(TLSHandshakeType.Companion.byCode(readInt >>> 24));
        int i = readInt & 16777215;
        BytePacketBuilder bytePacketBuilder = new BytePacketBuilder((ObjectPool) null, 1, (DefaultConstructorMarker) null);
        try {
            OutputKt.writeFully$default((Output) bytePacketBuilder, StringsKt.readBytes(byteReadPacket, i), 0, 0, 6, (Object) null);
            tLSHandshake.setPacket(bytePacketBuilder.build());
            return tLSHandshake;
        } catch (Throwable th) {
            bytePacketBuilder.release();
            throw th;
        }
    }

    public static final TLSServerHello readTLSServerHello(ByteReadPacket byteReadPacket) {
        ByteReadPacket byteReadPacket2 = byteReadPacket;
        Intrinsics.checkNotNullParameter(byteReadPacket2, "<this>");
        TLSVersion readTLSVersion = readTLSVersion(byteReadPacket);
        byte[] bArr = new byte[32];
        Input input = byteReadPacket2;
        InputArraysKt.readFully$default(input, bArr, 0, 0, 6, (Object) null);
        byte readByte = byteReadPacket.readByte() & 255;
        if (readByte <= 32) {
            byte[] bArr2 = new byte[32];
            InputArraysKt.readFully(input, bArr2, 0, (int) readByte);
            short readShort = InputPrimitivesKt.readShort(input);
            short readByte2 = (short) (((short) byteReadPacket.readByte()) & Http2CodecUtil.MAX_UNSIGNED_BYTE);
            if (readByte2 != 0) {
                throw new TLSException("Unsupported TLS compression method " + readByte2 + " (only null 0 compression method is supported)", (Throwable) null, 2, (DefaultConstructorMarker) null);
            } else if (((int) byteReadPacket.getRemaining()) == 0) {
                return new TLSServerHello(readTLSVersion, bArr, bArr2, readShort, readByte2, (List) null, 32, (DefaultConstructorMarker) null);
            } else {
                short readShort2 = InputPrimitivesKt.readShort(input) & 65535;
                if (((int) byteReadPacket.getRemaining()) == readShort2) {
                    List arrayList = new ArrayList();
                    while (byteReadPacket.getRemaining() > 0) {
                        short readShort3 = InputPrimitivesKt.readShort(input) & 65535;
                        Collection collection = arrayList;
                        TLSExtensionType byCode = TLSExtensionType.Companion.byCode(InputPrimitivesKt.readShort(input) & 65535);
                        BytePacketBuilder bytePacketBuilder = new BytePacketBuilder((ObjectPool) null, 1, (DefaultConstructorMarker) null);
                        try {
                            OutputKt.writeFully$default((Output) bytePacketBuilder, StringsKt.readBytes(byteReadPacket2, (int) readShort3), 0, 0, 6, (Object) null);
                            collection.add(new TLSExtension(byCode, readShort3, bytePacketBuilder.build()));
                        } catch (Throwable th) {
                            bytePacketBuilder.release();
                            throw th;
                        }
                    }
                    return new TLSServerHello(readTLSVersion, bArr, bArr2, readShort, readByte2, arrayList);
                }
                throw new TLSException("Invalid extensions size: requested " + readShort2 + ", available " + byteReadPacket.getRemaining(), (Throwable) null, 2, (DefaultConstructorMarker) null);
            }
        } else {
            throw new TLSException("sessionId length limit of 32 bytes exceeded: " + readByte + " specified", (Throwable) null, 2, (DefaultConstructorMarker) null);
        }
    }

    public static final NamedCurve readCurveParams(ByteReadPacket byteReadPacket) {
        Intrinsics.checkNotNullParameter(byteReadPacket, "<this>");
        int i = WhenMappings.$EnumSwitchMapping$0[ServerKeyExchangeType.Companion.byCode(byteReadPacket.readByte() & 255).ordinal()];
        if (i == 1) {
            NamedCurve fromCode = NamedCurve.Companion.fromCode(InputPrimitivesKt.readShort(byteReadPacket));
            if (fromCode != null) {
                return fromCode;
            }
            throw new TLSException("Unknown EC id", (Throwable) null, 2, (DefaultConstructorMarker) null);
        } else if (i == 2) {
            throw new IllegalStateException("ExplicitPrime server key exchange type is not yet supported".toString());
        } else if (i != 3) {
            throw new NoWhenBranchMatchedException();
        } else {
            throw new IllegalStateException("ExplicitChar server key exchange type is not yet supported".toString());
        }
    }

    public static final List<Certificate> readTLSCertificate(ByteReadPacket byteReadPacket) {
        Intrinsics.checkNotNullParameter(byteReadPacket, "<this>");
        int readTripleByteLength = readTripleByteLength(byteReadPacket);
        ArrayList arrayList = new ArrayList();
        CertificateFactory instance = CertificateFactory.getInstance("X.509");
        Intrinsics.checkNotNull(instance);
        int i = 0;
        while (i < readTripleByteLength) {
            int readTripleByteLength2 = readTripleByteLength(byteReadPacket);
            if (readTripleByteLength2 > readTripleByteLength - i) {
                throw new TLSException("Certificate length is too big", (Throwable) null, 2, (DefaultConstructorMarker) null);
            } else if (((long) readTripleByteLength2) <= byteReadPacket.getRemaining()) {
                byte[] bArr = new byte[readTripleByteLength2];
                InputArraysKt.readFully$default((Input) byteReadPacket, bArr, 0, 0, 6, (Object) null);
                i += readTripleByteLength2 + 3;
                arrayList.add(instance.generateCertificate(new ByteArrayInputStream(bArr)));
            } else {
                throw new TLSException("Certificate length is too big", (Throwable) null, 2, (DefaultConstructorMarker) null);
            }
        }
        return arrayList;
    }

    public static final ECPoint readECPoint(ByteReadPacket byteReadPacket, int i) {
        Intrinsics.checkNotNullParameter(byteReadPacket, "<this>");
        byte readByte = byteReadPacket.readByte() & 255;
        if (byteReadPacket.readByte() == 4) {
            int i2 = (readByte - 1) / 2;
            if (((i + 7) >>> 3) == i2) {
                return new ECPoint(new BigInteger(1, StringsKt.readBytes(byteReadPacket, i2)), new BigInteger(1, StringsKt.readBytes(byteReadPacket, i2)));
            }
            throw new TLSException("Invalid point component length", (Throwable) null, 2, (DefaultConstructorMarker) null);
        }
        throw new TLSException("Point should be uncompressed", (Throwable) null, 2, (DefaultConstructorMarker) null);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object readTLSVersion(io.ktor.utils.io.ByteReadChannel r5, kotlin.coroutines.Continuation<? super io.ktor.network.tls.TLSVersion> r6) {
        /*
            boolean r0 = r6 instanceof io.ktor.network.tls.ParserKt$readTLSVersion$1
            if (r0 == 0) goto L_0x0014
            r0 = r6
            io.ktor.network.tls.ParserKt$readTLSVersion$1 r0 = (io.ktor.network.tls.ParserKt$readTLSVersion$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L_0x0019
        L_0x0014:
            io.ktor.network.tls.ParserKt$readTLSVersion$1 r0 = new io.ktor.network.tls.ParserKt$readTLSVersion$1
            r0.<init>(r6)
        L_0x0019:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0036
            if (r2 != r3) goto L_0x002e
            java.lang.Object r5 = r0.L$0
            io.ktor.network.tls.TLSVersion$Companion r5 = (io.ktor.network.tls.TLSVersion.Companion) r5
            kotlin.ResultKt.throwOnFailure(r6)
            goto L_0x0049
        L_0x002e:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x0036:
            kotlin.ResultKt.throwOnFailure(r6)
            io.ktor.network.tls.TLSVersion$Companion r6 = io.ktor.network.tls.TLSVersion.Companion
            r0.L$0 = r6
            r0.label = r3
            java.lang.Object r5 = readShortCompatible(r5, r0)
            if (r5 != r1) goto L_0x0046
            return r1
        L_0x0046:
            r4 = r6
            r6 = r5
            r5 = r4
        L_0x0049:
            java.lang.Number r6 = (java.lang.Number) r6
            int r6 = r6.intValue()
            r0 = 65535(0xffff, float:9.1834E-41)
            r6 = r6 & r0
            io.ktor.network.tls.TLSVersion r5 = r5.byCode(r6)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.network.tls.ParserKt.readTLSVersion(io.ktor.utils.io.ByteReadChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private static final TLSVersion readTLSVersion(ByteReadPacket byteReadPacket) {
        return TLSVersion.Companion.byCode(InputPrimitivesKt.readShort(byteReadPacket) & 65535);
    }

    public static final int readTripleByteLength(ByteReadPacket byteReadPacket) {
        Intrinsics.checkNotNullParameter(byteReadPacket, "<this>");
        return (InputPrimitivesKt.readShort(byteReadPacket) & 65535) | ((byteReadPacket.readByte() & 255) << Ascii.DLE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x003f  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0062 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0063  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object readShortCompatible(io.ktor.utils.io.ByteReadChannel r6, kotlin.coroutines.Continuation<? super java.lang.Integer> r7) {
        /*
            boolean r0 = r7 instanceof io.ktor.network.tls.ParserKt$readShortCompatible$1
            if (r0 == 0) goto L_0x0014
            r0 = r7
            io.ktor.network.tls.ParserKt$readShortCompatible$1 r0 = (io.ktor.network.tls.ParserKt$readShortCompatible$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L_0x0019
        L_0x0014:
            io.ktor.network.tls.ParserKt$readShortCompatible$1 r0 = new io.ktor.network.tls.ParserKt$readShortCompatible$1
            r0.<init>(r7)
        L_0x0019:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x003f
            if (r2 == r4) goto L_0x0037
            if (r2 != r3) goto L_0x002f
            int r6 = r0.I$0
            kotlin.ResultKt.throwOnFailure(r7)
            goto L_0x0066
        L_0x002f:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x0037:
            java.lang.Object r6 = r0.L$0
            io.ktor.utils.io.ByteReadChannel r6 = (io.ktor.utils.io.ByteReadChannel) r6
            kotlin.ResultKt.throwOnFailure(r7)
            goto L_0x004d
        L_0x003f:
            kotlin.ResultKt.throwOnFailure(r7)
            r0.L$0 = r6
            r0.label = r4
            java.lang.Object r7 = r6.readByte(r0)
            if (r7 != r1) goto L_0x004d
            return r1
        L_0x004d:
            java.lang.Number r7 = (java.lang.Number) r7
            byte r7 = r7.byteValue()
            r7 = r7 & 255(0xff, float:3.57E-43)
            r2 = 0
            r0.L$0 = r2
            r0.I$0 = r7
            r0.label = r3
            java.lang.Object r6 = r6.readByte(r0)
            if (r6 != r1) goto L_0x0063
            return r1
        L_0x0063:
            r5 = r7
            r7 = r6
            r6 = r5
        L_0x0066:
            java.lang.Number r7 = (java.lang.Number) r7
            byte r7 = r7.byteValue()
            r7 = r7 & 255(0xff, float:3.57E-43)
            int r6 = r6 << 8
            int r6 = r6 + r7
            java.lang.Integer r6 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r6)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.network.tls.ParserKt.readShortCompatible(io.ktor.utils.io.ByteReadChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
