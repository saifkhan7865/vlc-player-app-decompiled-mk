package io.ktor.utils.io.charsets;

import androidx.tvprovider.media.tv.TvContractCompat;
import io.ktor.utils.io.bits.Memory;
import io.ktor.utils.io.core.Buffer;
import io.ktor.utils.io.core.BufferPrimitivesKt;
import io.ktor.utils.io.core.BytePacketBuilder;
import io.ktor.utils.io.core.Input;
import io.ktor.utils.io.core.StringsKt;
import io.ktor.utils.io.core.internal.ChunkBuffer;
import io.ktor.utils.io.core.internal.UnsafeKt;
import io.ktor.utils.io.pool.ObjectPool;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.nio.charset.MalformedInputException;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0001\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\r\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u001a*\u0010\u0015\u001a\u00020\u0001*\u00060\nj\u0002`\u000b2\u0006\u0010\u0016\u001a\u00020\u00172\n\u0010\u0018\u001a\u00060\u0019j\u0002`\u001a2\u0006\u0010\u001b\u001a\u00020\u0001\u001a6\u0010\u001c\u001a\u00020\u0001*\u00060\nj\u0002`\u000b2\u0006\u0010\u0016\u001a\u00020\u001d2\n\u0010\u001e\u001a\u00060\u0019j\u0002`\u001a2\u0006\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010\u001b\u001a\u00020\u0001H\u0000\u001a\u001e\u0010!\u001a\u00020\u0012*\u00060\nj\u0002`\u000b2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\"\u001a\u00020\u0001\u001a \u0010#\u001a\u00020\u0012*\u00060\nj\u0002`\u000b2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\"\u001a\u00020\u0001H\u0002\u001a \u0010$\u001a\u00020\u0012*\u00060\nj\u0002`\u000b2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\"\u001a\u00020\u0001H\u0002\u001a\u0018\u0010%\u001a\u00020 *\u00060\u000ej\u0002`\u000f2\u0006\u0010\u0018\u001a\u00020\u001dH\u0000\u001a0\u0010&\u001a\u00020\u0001*\u00060\u000ej\u0002`\u000f2\u0006\u0010\u0016\u001a\u00020'2\u0006\u0010(\u001a\u00020\u00012\u0006\u0010)\u001a\u00020\u00012\u0006\u0010\u0018\u001a\u00020\u001dH\u0000\u001a*\u0010*\u001a\u00020+*\u00060\u000ej\u0002`\u000f2\u0006\u0010\u0016\u001a\u00020'2\b\b\u0002\u0010(\u001a\u00020\u00012\b\b\u0002\u0010)\u001a\u00020\u0001\u001a,\u0010,\u001a\u00020+*\u00060\u000ej\u0002`\u000f2\u0006\u0010\u0016\u001a\u00020'2\b\b\u0002\u0010(\u001a\u00020\u00012\b\b\u0002\u0010)\u001a\u00020\u0001H\u0000\u001a(\u0010-\u001a\u00020+*\u00060\u000ej\u0002`\u000f2\u0006\u0010\u0016\u001a\u00020'2\u0006\u0010(\u001a\u00020\u00012\u0006\u0010)\u001a\u00020\u0001H\u0002\u001a\u001e\u0010.\u001a\u00020/*\u00060\u000ej\u0002`\u000f2\u0006\u0010\u0016\u001a\u0002002\u0006\u0010\u0018\u001a\u000201\u001a\f\u00102\u001a\u00020/*\u000203H\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000\"\u0016\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005X\u0004¢\u0006\u0002\n\u0000\"\u001d\u0010\u0007\u001a\u00060\bj\u0002`\t*\u00060\nj\u0002`\u000b8F¢\u0006\u0006\u001a\u0004\b\f\u0010\r\"\u001d\u0010\u0007\u001a\u00060\bj\u0002`\t*\u00060\u000ej\u0002`\u000f8F¢\u0006\u0006\u001a\u0004\b\f\u0010\u0010\"\u0019\u0010\u0011\u001a\u00020\u0012*\u00060\bj\u0002`\t8F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014*\n\u00104\"\u00020\b2\u00020\b*\n\u00105\"\u00020\n2\u00020\n*\n\u00106\"\u00020\u000e2\u00020\u000e*\n\u00107\"\u0002082\u000208¨\u00069"}, d2 = {"DECODE_CHAR_BUFFER_SIZE", "", "EmptyByteBuffer", "Ljava/nio/ByteBuffer;", "EmptyCharBuffer", "Ljava/nio/CharBuffer;", "kotlin.jvm.PlatformType", "charset", "Ljava/nio/charset/Charset;", "Lio/ktor/utils/io/charsets/Charset;", "Ljava/nio/charset/CharsetDecoder;", "Lio/ktor/utils/io/charsets/CharsetDecoder;", "getCharset", "(Ljava/nio/charset/CharsetDecoder;)Ljava/nio/charset/Charset;", "Ljava/nio/charset/CharsetEncoder;", "Lio/ktor/utils/io/charsets/CharsetEncoder;", "(Ljava/nio/charset/CharsetEncoder;)Ljava/nio/charset/Charset;", "name", "", "getName", "(Ljava/nio/charset/Charset;)Ljava/lang/String;", "decode", "input", "Lio/ktor/utils/io/core/Input;", "dst", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "max", "decodeBuffer", "Lio/ktor/utils/io/core/Buffer;", "out", "lastBuffer", "", "decodeExactBytes", "inputLength", "decodeImplByteBuffer", "decodeImplSlow", "encodeComplete", "encodeImpl", "", "fromIndex", "toIndex", "encodeToByteArray", "", "encodeToByteArrayImpl1", "encodeToByteArraySlow", "encodeUTF8", "", "Lio/ktor/utils/io/core/ByteReadPacket;", "Lio/ktor/utils/io/core/Output;", "throwExceptionWrapped", "Ljava/nio/charset/CoderResult;", "Charset", "CharsetDecoder", "CharsetEncoder", "Charsets", "Lkotlin/text/Charsets;", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: CharsetJVM.kt */
public final class CharsetJVMKt {
    private static final int DECODE_CHAR_BUFFER_SIZE = 8192;
    private static final ByteBuffer EmptyByteBuffer;
    private static final CharBuffer EmptyCharBuffer = CharBuffer.allocate(0);

    public static /* synthetic */ void Charset$annotations() {
    }

    public static final String getName(Charset charset) {
        Intrinsics.checkNotNullParameter(charset, "<this>");
        String name = charset.name();
        Intrinsics.checkNotNullExpressionValue(name, "name()");
        return name;
    }

    public static final Charset getCharset(CharsetEncoder charsetEncoder) {
        Intrinsics.checkNotNullParameter(charsetEncoder, "<this>");
        Charset charset = charsetEncoder.charset();
        Intrinsics.checkNotNullExpressionValue(charset, "charset()");
        return charset;
    }

    public static final byte[] encodeToByteArray(CharsetEncoder charsetEncoder, CharSequence charSequence, int i, int i2) {
        Intrinsics.checkNotNullParameter(charsetEncoder, "<this>");
        Intrinsics.checkNotNullParameter(charSequence, TvContractCompat.PARAM_INPUT);
        if (!(charSequence instanceof String)) {
            return encodeToByteArraySlow(charsetEncoder, charSequence, i, i2);
        }
        if (i == 0 && i2 == charSequence.length()) {
            byte[] bytes = ((String) charSequence).getBytes(charsetEncoder.charset());
            Intrinsics.checkNotNullExpressionValue(bytes, "input as java.lang.String).getBytes(charset())");
            return bytes;
        }
        String substring = ((String) charSequence).substring(i, i2);
        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
        Intrinsics.checkNotNull(substring, "null cannot be cast to non-null type java.lang.String");
        byte[] bytes2 = substring.getBytes(charsetEncoder.charset());
        Intrinsics.checkNotNullExpressionValue(bytes2, "input.substring(fromInde…ring).getBytes(charset())");
        return bytes2;
    }

    private static final byte[] encodeToByteArraySlow(CharsetEncoder charsetEncoder, CharSequence charSequence, int i, int i2) {
        ByteBuffer encode = charsetEncoder.encode(CharBuffer.wrap(charSequence, i, i2));
        byte[] bArr = null;
        if (encode.hasArray() && encode.arrayOffset() == 0) {
            byte[] array = encode.array();
            if (array.length == encode.remaining()) {
                bArr = array;
            }
        }
        if (bArr != null) {
            return bArr;
        }
        byte[] bArr2 = new byte[encode.remaining()];
        encode.get(bArr2);
        return bArr2;
    }

    public static /* synthetic */ byte[] encodeToByteArray$default(CharsetEncoder charsetEncoder, CharSequence charSequence, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = charSequence.length();
        }
        return encodeToByteArray(charsetEncoder, charSequence, i, i2);
    }

    public static final int encodeImpl(CharsetEncoder charsetEncoder, CharSequence charSequence, int i, int i2, Buffer buffer) {
        Intrinsics.checkNotNullParameter(charsetEncoder, "<this>");
        Intrinsics.checkNotNullParameter(charSequence, TvContractCompat.PARAM_INPUT);
        Intrinsics.checkNotNullParameter(buffer, "dst");
        CharBuffer wrap = CharBuffer.wrap(charSequence, i, i2);
        int remaining = wrap.remaining();
        ByteBuffer r5 = buffer.m155getMemorySK3TCg8();
        int writePosition = buffer.getWritePosition();
        int limit = buffer.getLimit() - writePosition;
        ByteBuffer r52 = Memory.m1519slice87lwejk(r5, writePosition, limit);
        CoderResult encode = charsetEncoder.encode(wrap, r52, false);
        if (encode.isMalformed() || encode.isUnmappable()) {
            Intrinsics.checkNotNullExpressionValue(encode, "result");
            throwExceptionWrapped(encode);
        }
        if (r52.limit() == limit) {
            buffer.commitWritten(r52.position());
            return remaining - wrap.remaining();
        }
        throw new IllegalStateException("Buffer's limit change is not allowed".toString());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:122:?, code lost:
        r28.afterHeadWrite();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:123:0x0268, code lost:
        r0 = r25.position() - r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:124:0x026a, code lost:
        if (r0 < 0) goto L_0x027f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:126:0x026e, code lost:
        if (r0 > r22) goto L_0x027f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:127:0x0270, code lost:
        r4 = r20;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:129:?, code lost:
        r4.commitWritten(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:130:0x0275, code lost:
        r4.release(io.ktor.utils.io.core.internal.ChunkBuffer.Companion.getPool());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:131:0x027e, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:132:0x027f, code lost:
        r4 = r20;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:134:?, code lost:
        io.ktor.utils.io.internal.jvm.ErrorsKt.wrongBufferPositionChangeError(r0, 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:135:0x028a, code lost:
        throw new kotlin.KotlinNothingValueException();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:?, code lost:
        r28.afterHeadWrite();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void encodeUTF8(java.nio.charset.CharsetEncoder r26, io.ktor.utils.io.core.ByteReadPacket r27, io.ktor.utils.io.core.Output r28) {
        /*
            r0 = r26
            r1 = r27
            r2 = r28
            java.lang.String r3 = "size 0 is greater than buffer's remaining capacity "
            java.lang.String r4 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r4)
            java.lang.String r4 = "input"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r4)
            java.lang.String r4 = "dst"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r4)
            java.nio.charset.Charset r4 = getCharset((java.nio.charset.CharsetEncoder) r26)
            java.nio.charset.Charset r5 = kotlin.text.Charsets.UTF_8
            if (r4 != r5) goto L_0x0023
            r2.writePacket(r1)
            return
        L_0x0023:
            io.ktor.utils.io.core.internal.ChunkBuffer$Companion r4 = io.ktor.utils.io.core.internal.ChunkBuffer.Companion
            io.ktor.utils.io.pool.ObjectPool r4 = r4.getPool()
            java.lang.Object r4 = r4.borrow()
            io.ktor.utils.io.core.internal.ChunkBuffer r4 = (io.ktor.utils.io.core.internal.ChunkBuffer) r4
            r5 = r4
            io.ktor.utils.io.core.Buffer r5 = (io.ktor.utils.io.core.Buffer) r5     // Catch:{ all -> 0x02ba }
            int r6 = r5.getLimit()     // Catch:{ all -> 0x02ba }
            int r5 = r5.getWritePosition()     // Catch:{ all -> 0x02ba }
            int r6 = r6 - r5
            if (r6 < 0) goto L_0x02a4
            java.nio.ByteBuffer r3 = r4.m155getMemorySK3TCg8()     // Catch:{ all -> 0x02ba }
            java.nio.ByteBuffer r3 = r3.duplicate()     // Catch:{ all -> 0x02ba }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3)     // Catch:{ all -> 0x02ba }
            int r5 = r4.getWritePosition()     // Catch:{ all -> 0x02ba }
            int r7 = r4.getLimit()     // Catch:{ all -> 0x02ba }
            r3.limit(r7)     // Catch:{ all -> 0x02ba }
            r3.position(r5)     // Catch:{ all -> 0x02ba }
            java.nio.CharBuffer r7 = r3.asCharBuffer()     // Catch:{ all -> 0x02ba }
        L_0x005a:
            long r8 = r27.getRemaining()     // Catch:{ all -> 0x02ba }
            r10 = 0
            java.lang.String r12 = "Buffer's limit change is not allowed"
            java.lang.String r13 = "cr"
            r15 = 1
            int r16 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r16 <= 0) goto L_0x0202
            r7.clear()     // Catch:{ all -> 0x01fd }
            io.ktor.utils.io.core.internal.ChunkBuffer r8 = r1.prepareReadHead$ktor_io(r15)     // Catch:{ all -> 0x01fd }
            if (r8 != 0) goto L_0x0074
            goto L_0x0202
        L_0x0074:
            r9 = r8
            io.ktor.utils.io.core.Buffer r9 = (io.ktor.utils.io.core.Buffer) r9     // Catch:{ all -> 0x01fd }
            java.nio.ByteBuffer r10 = r9.m155getMemorySK3TCg8()     // Catch:{ all -> 0x01fd }
            int r11 = r9.getReadPosition()     // Catch:{ all -> 0x01fd }
            int r14 = r9.getWritePosition()     // Catch:{ all -> 0x01fd }
            r15 = r11
            r17 = 0
            r18 = 0
            r19 = 0
        L_0x008a:
            if (r15 >= r14) goto L_0x0160
            r20 = r4
            byte r4 = r10.get(r15)     // Catch:{ all -> 0x02a0 }
            r21 = r10
            r10 = r4 & 255(0xff, float:3.57E-43)
            r22 = r6
            r6 = r4 & 128(0x80, float:1.794E-43)
            r23 = -1
            if (r6 != 0) goto L_0x00c3
            if (r17 != 0) goto L_0x00ba
            char r4 = (char) r10     // Catch:{ all -> 0x02a0 }
            boolean r6 = r7.hasRemaining()     // Catch:{ all -> 0x02a0 }
            if (r6 == 0) goto L_0x00b0
            r7.put(r4)     // Catch:{ all -> 0x02a0 }
            r25 = r3
            r24 = r5
            goto L_0x0152
        L_0x00b0:
            int r15 = r15 - r11
            r9.discardExact(r15)     // Catch:{ all -> 0x02a0 }
            r25 = r3
            r24 = r5
            goto L_0x016e
        L_0x00ba:
            io.ktor.utils.io.core.internal.UTF8Kt.malformedByteCount(r17)     // Catch:{ all -> 0x02a0 }
            kotlin.KotlinNothingValueException r0 = new kotlin.KotlinNothingValueException     // Catch:{ all -> 0x02a0 }
            r0.<init>()     // Catch:{ all -> 0x02a0 }
            throw r0     // Catch:{ all -> 0x02a0 }
        L_0x00c3:
            if (r17 != 0) goto L_0x00f4
            r4 = 128(0x80, float:1.794E-43)
            r25 = r3
            r24 = r5
            r6 = r17
            r5 = 1
        L_0x00ce:
            r3 = 7
            if (r5 >= r3) goto L_0x00df
            r3 = r10 & r4
            if (r3 == 0) goto L_0x00df
            r3 = r4 ^ -1
            r10 = r10 & r3
            int r4 = r4 >> 1
            int r6 = r6 + 1
            int r5 = r5 + 1
            goto L_0x00ce
        L_0x00df:
            int r3 = r6 + -1
            int r4 = r14 - r15
            if (r6 <= r4) goto L_0x00ed
            int r15 = r15 - r11
            r9.discardExact(r15)     // Catch:{ all -> 0x02a0 }
            r23 = r6
            goto L_0x016e
        L_0x00ed:
            r17 = r3
            r19 = r6
            r18 = r10
            goto L_0x0152
        L_0x00f4:
            r25 = r3
            r24 = r5
            int r3 = r18 << 6
            r4 = r4 & 127(0x7f, float:1.78E-43)
            r3 = r3 | r4
            int r17 = r17 + -1
            if (r17 != 0) goto L_0x0150
            boolean r4 = io.ktor.utils.io.core.internal.UTF8Kt.isBmpCodePoint(r3)     // Catch:{ all -> 0x02a0 }
            if (r4 == 0) goto L_0x011b
            char r3 = (char) r3     // Catch:{ all -> 0x02a0 }
            boolean r4 = r7.hasRemaining()     // Catch:{ all -> 0x02a0 }
            if (r4 == 0) goto L_0x0112
        L_0x010e:
            r7.put(r3)     // Catch:{ all -> 0x02a0 }
            goto L_0x013b
        L_0x0112:
            int r15 = r15 - r11
            int r15 = r15 - r19
            r3 = 1
            int r15 = r15 + r3
            r9.discardExact(r15)     // Catch:{ all -> 0x02a0 }
            goto L_0x016e
        L_0x011b:
            boolean r4 = io.ktor.utils.io.core.internal.UTF8Kt.isValidCodePoint(r3)     // Catch:{ all -> 0x02a0 }
            if (r4 == 0) goto L_0x0147
            int r4 = io.ktor.utils.io.core.internal.UTF8Kt.highSurrogate(r3)     // Catch:{ all -> 0x02a0 }
            char r4 = (char) r4     // Catch:{ all -> 0x02a0 }
            boolean r5 = r7.hasRemaining()     // Catch:{ all -> 0x02a0 }
            if (r5 == 0) goto L_0x013e
            r7.put(r4)     // Catch:{ all -> 0x02a0 }
            int r3 = io.ktor.utils.io.core.internal.UTF8Kt.lowSurrogate(r3)     // Catch:{ all -> 0x02a0 }
            char r3 = (char) r3     // Catch:{ all -> 0x02a0 }
            boolean r4 = r7.hasRemaining()     // Catch:{ all -> 0x02a0 }
            if (r4 == 0) goto L_0x013e
            goto L_0x010e
        L_0x013b:
            r18 = 0
            goto L_0x0152
        L_0x013e:
            int r15 = r15 - r11
            int r15 = r15 - r19
            r3 = 1
            int r15 = r15 + r3
            r9.discardExact(r15)     // Catch:{ all -> 0x02a0 }
            goto L_0x016e
        L_0x0147:
            io.ktor.utils.io.core.internal.UTF8Kt.malformedCodePoint(r3)     // Catch:{ all -> 0x02a0 }
            kotlin.KotlinNothingValueException r0 = new kotlin.KotlinNothingValueException     // Catch:{ all -> 0x02a0 }
            r0.<init>()     // Catch:{ all -> 0x02a0 }
            throw r0     // Catch:{ all -> 0x02a0 }
        L_0x0150:
            r18 = r3
        L_0x0152:
            int r15 = r15 + 1
            r4 = r20
            r10 = r21
            r6 = r22
            r5 = r24
            r3 = r25
            goto L_0x008a
        L_0x0160:
            r25 = r3
            r20 = r4
            r24 = r5
            r22 = r6
            int r14 = r14 - r11
            r9.discardExact(r14)     // Catch:{ all -> 0x02a0 }
            r23 = 0
        L_0x016e:
            int r3 = r8.getReadPosition()     // Catch:{ all -> 0x02a0 }
            r1.setHeadPosition(r3)     // Catch:{ all -> 0x02a0 }
            r7.flip()     // Catch:{ all -> 0x02a0 }
            boolean r3 = r7.hasRemaining()     // Catch:{ all -> 0x02a0 }
            if (r3 == 0) goto L_0x01f0
            r3 = 0
            r4 = 1
            io.ktor.utils.io.core.internal.ChunkBuffer r5 = io.ktor.utils.io.core.internal.UnsafeKt.prepareWriteHead(r2, r4, r3)     // Catch:{ all -> 0x02a0 }
            r3 = 1
        L_0x0185:
            r4 = r5
            io.ktor.utils.io.core.Buffer r4 = (io.ktor.utils.io.core.Buffer) r4     // Catch:{ all -> 0x01eb }
            java.nio.ByteBuffer r6 = r4.m155getMemorySK3TCg8()     // Catch:{ all -> 0x01eb }
            int r8 = r4.getWritePosition()     // Catch:{ all -> 0x01eb }
            int r9 = r4.getLimit()     // Catch:{ all -> 0x01eb }
            int r9 = r9 - r8
            java.nio.ByteBuffer r6 = io.ktor.utils.io.bits.Memory.m1519slice87lwejk((java.nio.ByteBuffer) r6, (int) r8, (int) r9)     // Catch:{ all -> 0x01eb }
            r8 = 0
            java.nio.charset.CoderResult r10 = r0.encode(r7, r6, r8)     // Catch:{ all -> 0x01eb }
            boolean r8 = r10.isUnmappable()     // Catch:{ all -> 0x01eb }
            if (r8 != 0) goto L_0x01aa
            boolean r8 = r10.isMalformed()     // Catch:{ all -> 0x01eb }
            if (r8 == 0) goto L_0x01b0
        L_0x01aa:
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r10, r13)     // Catch:{ all -> 0x01eb }
            throwExceptionWrapped(r10)     // Catch:{ all -> 0x01eb }
        L_0x01b0:
            boolean r8 = r10.isOverflow()     // Catch:{ all -> 0x01eb }
            if (r8 == 0) goto L_0x01bf
            boolean r8 = r6.hasRemaining()     // Catch:{ all -> 0x01eb }
            if (r8 == 0) goto L_0x01bf
            r8 = 1
            int r3 = r3 + r8
            goto L_0x01c0
        L_0x01bf:
            r3 = 1
        L_0x01c0:
            int r8 = r6.limit()     // Catch:{ all -> 0x01eb }
            if (r8 != r9) goto L_0x01e1
            int r6 = r6.position()     // Catch:{ all -> 0x01eb }
            r4.commitWritten(r6)     // Catch:{ all -> 0x01eb }
            boolean r4 = r7.hasRemaining()     // Catch:{ all -> 0x01eb }
            if (r4 == 0) goto L_0x01d5
            r8 = r3
            goto L_0x01d6
        L_0x01d5:
            r8 = 0
        L_0x01d6:
            if (r8 <= 0) goto L_0x01dd
            io.ktor.utils.io.core.internal.ChunkBuffer r5 = io.ktor.utils.io.core.internal.UnsafeKt.prepareWriteHead(r2, r8, r5)     // Catch:{ all -> 0x01eb }
            goto L_0x0185
        L_0x01dd:
            r28.afterHeadWrite()     // Catch:{ all -> 0x02a0 }
            goto L_0x01f0
        L_0x01e1:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch:{ all -> 0x01eb }
            java.lang.String r1 = r12.toString()     // Catch:{ all -> 0x01eb }
            r0.<init>(r1)     // Catch:{ all -> 0x01eb }
            throw r0     // Catch:{ all -> 0x01eb }
        L_0x01eb:
            r0 = move-exception
            r28.afterHeadWrite()     // Catch:{ all -> 0x02a0 }
            throw r0     // Catch:{ all -> 0x02a0 }
        L_0x01f0:
            if (r23 <= 0) goto L_0x01f3
            goto L_0x020a
        L_0x01f3:
            r4 = r20
            r6 = r22
            r5 = r24
            r3 = r25
            goto L_0x005a
        L_0x01fd:
            r0 = move-exception
            r20 = r4
            goto L_0x02bb
        L_0x0202:
            r25 = r3
            r20 = r4
            r24 = r5
            r22 = r6
        L_0x020a:
            r7.clear()     // Catch:{ all -> 0x02a0 }
            r7.flip()     // Catch:{ all -> 0x02a0 }
            r1 = 0
            r3 = 1
            io.ktor.utils.io.core.internal.ChunkBuffer r1 = io.ktor.utils.io.core.internal.UnsafeKt.prepareWriteHead(r2, r3, r1)     // Catch:{ all -> 0x02a0 }
            r3 = 1
        L_0x0217:
            r4 = r1
            io.ktor.utils.io.core.Buffer r4 = (io.ktor.utils.io.core.Buffer) r4     // Catch:{ all -> 0x0299 }
            java.nio.ByteBuffer r5 = r4.m155getMemorySK3TCg8()     // Catch:{ all -> 0x0299 }
            int r6 = r4.getWritePosition()     // Catch:{ all -> 0x0299 }
            int r8 = r4.getLimit()     // Catch:{ all -> 0x0299 }
            int r8 = r8 - r6
            java.nio.ByteBuffer r5 = io.ktor.utils.io.bits.Memory.m1519slice87lwejk((java.nio.ByteBuffer) r5, (int) r6, (int) r8)     // Catch:{ all -> 0x0299 }
            r6 = 1
            java.nio.charset.CoderResult r9 = r0.encode(r7, r5, r6)     // Catch:{ all -> 0x0299 }
            boolean r6 = r9.isMalformed()     // Catch:{ all -> 0x0299 }
            if (r6 != 0) goto L_0x023c
            boolean r6 = r9.isUnmappable()     // Catch:{ all -> 0x0299 }
            if (r6 == 0) goto L_0x0242
        L_0x023c:
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, r13)     // Catch:{ all -> 0x0299 }
            throwExceptionWrapped(r9)     // Catch:{ all -> 0x0299 }
        L_0x0242:
            boolean r6 = r9.isOverflow()     // Catch:{ all -> 0x0299 }
            if (r6 == 0) goto L_0x024b
            r6 = 1
            int r3 = r3 + r6
            goto L_0x024d
        L_0x024b:
            r6 = 1
            r3 = 0
        L_0x024d:
            int r9 = r5.limit()     // Catch:{ all -> 0x0299 }
            if (r9 != r8) goto L_0x028b
            int r5 = r5.position()     // Catch:{ all -> 0x0299 }
            r4.commitWritten(r5)     // Catch:{ all -> 0x0299 }
            if (r3 <= 0) goto L_0x0261
            io.ktor.utils.io.core.internal.ChunkBuffer r1 = io.ktor.utils.io.core.internal.UnsafeKt.prepareWriteHead(r2, r3, r1)     // Catch:{ all -> 0x0299 }
            goto L_0x0217
        L_0x0261:
            r28.afterHeadWrite()     // Catch:{ all -> 0x02a0 }
            int r0 = r25.position()     // Catch:{ all -> 0x02a0 }
            int r0 = r0 - r24
            if (r0 < 0) goto L_0x027f
            r6 = r22
            if (r0 > r6) goto L_0x027f
            r4 = r20
            r4.commitWritten(r0)     // Catch:{ all -> 0x02ba }
            io.ktor.utils.io.core.internal.ChunkBuffer$Companion r0 = io.ktor.utils.io.core.internal.ChunkBuffer.Companion
            io.ktor.utils.io.pool.ObjectPool r0 = r0.getPool()
            r4.release(r0)
            return
        L_0x027f:
            r4 = r20
            r1 = 0
            io.ktor.utils.io.internal.jvm.ErrorsKt.wrongBufferPositionChangeError(r0, r1)     // Catch:{ all -> 0x02ba }
            kotlin.KotlinNothingValueException r0 = new kotlin.KotlinNothingValueException     // Catch:{ all -> 0x02ba }
            r0.<init>()     // Catch:{ all -> 0x02ba }
            throw r0     // Catch:{ all -> 0x02ba }
        L_0x028b:
            r4 = r20
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0297 }
            java.lang.String r1 = r12.toString()     // Catch:{ all -> 0x0297 }
            r0.<init>(r1)     // Catch:{ all -> 0x0297 }
            throw r0     // Catch:{ all -> 0x0297 }
        L_0x0297:
            r0 = move-exception
            goto L_0x029c
        L_0x0299:
            r0 = move-exception
            r4 = r20
        L_0x029c:
            r28.afterHeadWrite()     // Catch:{ all -> 0x02ba }
            throw r0     // Catch:{ all -> 0x02ba }
        L_0x02a0:
            r0 = move-exception
            r4 = r20
            goto L_0x02bb
        L_0x02a4:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x02ba }
            r0.<init>(r3)     // Catch:{ all -> 0x02ba }
            r0.append(r6)     // Catch:{ all -> 0x02ba }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x02ba }
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException     // Catch:{ all -> 0x02ba }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x02ba }
            r1.<init>(r0)     // Catch:{ all -> 0x02ba }
            throw r1     // Catch:{ all -> 0x02ba }
        L_0x02ba:
            r0 = move-exception
        L_0x02bb:
            io.ktor.utils.io.core.internal.ChunkBuffer$Companion r1 = io.ktor.utils.io.core.internal.ChunkBuffer.Companion
            io.ktor.utils.io.pool.ObjectPool r1 = r1.getPool()
            r4.release(r1)
            goto L_0x02c6
        L_0x02c5:
            throw r0
        L_0x02c6:
            goto L_0x02c5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.charsets.CharsetJVMKt.encodeUTF8(java.nio.charset.CharsetEncoder, io.ktor.utils.io.core.ByteReadPacket, io.ktor.utils.io.core.Output):void");
    }

    public static /* synthetic */ byte[] encodeToByteArrayImpl1$default(CharsetEncoder charsetEncoder, CharSequence charSequence, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = charSequence.length();
        }
        return encodeToByteArrayImpl1(charsetEncoder, charSequence, i, i2);
    }

    public static /* synthetic */ int decodeBuffer$default(CharsetDecoder charsetDecoder, Buffer buffer, Appendable appendable, boolean z, int i, int i2, Object obj) {
        if ((i2 & 8) != 0) {
            i = Integer.MAX_VALUE;
        }
        return decodeBuffer(charsetDecoder, buffer, appendable, z, i);
    }

    public static final byte[] encodeToByteArrayImpl1(CharsetEncoder charsetEncoder, CharSequence charSequence, int i, int i2) {
        BytePacketBuilder bytePacketBuilder;
        byte[] readBytes$default;
        Intrinsics.checkNotNullParameter(charsetEncoder, "<this>");
        Intrinsics.checkNotNullParameter(charSequence, TvContractCompat.PARAM_INPUT);
        if (i >= i2) {
            return UnsafeKt.EmptyByteArray;
        }
        ChunkBuffer borrow = ChunkBuffer.Companion.getPool().borrow();
        try {
            int encodeImpl = i + encodeImpl(charsetEncoder, charSequence, i, i2, borrow);
            if (encodeImpl == i2) {
                Buffer buffer = borrow;
                int writePosition = buffer.getWritePosition() - buffer.getReadPosition();
                readBytes$default = new byte[writePosition];
                Intrinsics.checkNotNull(borrow, "null cannot be cast to non-null type io.ktor.utils.io.core.Buffer");
                BufferPrimitivesKt.readFully((Buffer) borrow, readBytes$default, 0, writePosition);
            } else {
                bytePacketBuilder = new BytePacketBuilder((ObjectPool) null, 1, (DefaultConstructorMarker) null);
                bytePacketBuilder.appendSingleChunk$ktor_io(borrow.duplicate());
                EncodingKt.encodeToImpl(charsetEncoder, bytePacketBuilder, charSequence, encodeImpl, i2);
                readBytes$default = StringsKt.readBytes$default(bytePacketBuilder.build(), 0, 1, (Object) null);
            }
            borrow.release(ChunkBuffer.Companion.getPool());
            return readBytes$default;
        } catch (Throwable th) {
            borrow.release(ChunkBuffer.Companion.getPool());
            throw th;
        }
    }

    public static final Charset getCharset(CharsetDecoder charsetDecoder) {
        Intrinsics.checkNotNullParameter(charsetDecoder, "<this>");
        Charset charset = charsetDecoder.charset();
        Intrinsics.checkNotNull(charset);
        return charset;
    }

    /* JADX WARNING: Removed duplicated region for block: B:76:0x012b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final int decode(java.nio.charset.CharsetDecoder r12, io.ktor.utils.io.core.Input r13, java.lang.Appendable r14, int r15) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r12, r0)
            java.lang.String r0 = "input"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r13, r0)
            java.lang.String r0 = "dst"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r14, r0)
            r0 = 8192(0x2000, float:1.14794E-41)
            java.nio.CharBuffer r1 = java.nio.CharBuffer.allocate(r0)
            r2 = 1
            io.ktor.utils.io.core.internal.ChunkBuffer r3 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadFirstHead(r13, r2)
            r4 = 0
            if (r3 != 0) goto L_0x001f
            goto L_0x00ed
        L_0x001f:
            r5 = 1
            r6 = 0
            r7 = 1
        L_0x0022:
            r8 = r3
            io.ktor.utils.io.core.Buffer r8 = (io.ktor.utils.io.core.Buffer) r8     // Catch:{ all -> 0x0128 }
            int r9 = r8.getWritePosition()     // Catch:{ all -> 0x0128 }
            int r8 = r8.getReadPosition()     // Catch:{ all -> 0x0128 }
            int r9 = r9 - r8
            if (r9 < r5) goto L_0x00ba
            r5 = r3
            io.ktor.utils.io.core.Buffer r5 = (io.ktor.utils.io.core.Buffer) r5     // Catch:{ all -> 0x00af }
            int r8 = r15 - r6
            if (r8 != 0) goto L_0x0039
            r5 = 0
            goto L_0x0096
        L_0x0039:
            java.nio.ByteBuffer r9 = r5.m155getMemorySK3TCg8()     // Catch:{ all -> 0x00af }
            int r10 = r5.getReadPosition()     // Catch:{ all -> 0x00af }
            int r11 = r5.getWritePosition()     // Catch:{ all -> 0x00af }
            int r11 = r11 - r10
            java.nio.ByteBuffer r9 = io.ktor.utils.io.bits.Memory.m1519slice87lwejk((java.nio.ByteBuffer) r9, (int) r10, (int) r11)     // Catch:{ all -> 0x00af }
            r1.clear()     // Catch:{ all -> 0x00af }
            if (r8 >= r0) goto L_0x0052
            r1.limit(r8)     // Catch:{ all -> 0x00af }
        L_0x0052:
            java.nio.charset.CoderResult r8 = r12.decode(r9, r1, r4)     // Catch:{ all -> 0x00af }
            r1.flip()     // Catch:{ all -> 0x00af }
            int r10 = r1.remaining()     // Catch:{ all -> 0x00af }
            int r6 = r6 + r10
            r10 = r1
            java.lang.CharSequence r10 = (java.lang.CharSequence) r10     // Catch:{ all -> 0x00af }
            r14.append(r10)     // Catch:{ all -> 0x00af }
            boolean r10 = r8.isMalformed()     // Catch:{ all -> 0x00af }
            if (r10 != 0) goto L_0x0070
            boolean r10 = r8.isUnmappable()     // Catch:{ all -> 0x00af }
            if (r10 == 0) goto L_0x0078
        L_0x0070:
            java.lang.String r10 = "rc"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, r10)     // Catch:{ all -> 0x00af }
            throwExceptionWrapped(r8)     // Catch:{ all -> 0x00af }
        L_0x0078:
            boolean r8 = r8.isUnderflow()     // Catch:{ all -> 0x00af }
            if (r8 == 0) goto L_0x0087
            boolean r8 = r9.hasRemaining()     // Catch:{ all -> 0x00af }
            if (r8 == 0) goto L_0x0087
            int r7 = r7 + 1
            goto L_0x0088
        L_0x0087:
            r7 = 1
        L_0x0088:
            int r8 = r9.limit()     // Catch:{ all -> 0x00af }
            if (r8 != r11) goto L_0x00a3
            int r8 = r9.position()     // Catch:{ all -> 0x00af }
            r5.discardExact(r8)     // Catch:{ all -> 0x00af }
            r5 = r7
        L_0x0096:
            r8 = r3
            io.ktor.utils.io.core.Buffer r8 = (io.ktor.utils.io.core.Buffer) r8     // Catch:{ all -> 0x0128 }
            int r9 = r8.getWritePosition()     // Catch:{ all -> 0x0128 }
            int r8 = r8.getReadPosition()     // Catch:{ all -> 0x0128 }
            int r9 = r9 - r8
            goto L_0x00ba
        L_0x00a3:
            java.lang.String r12 = "Buffer's limit change is not allowed"
            java.lang.IllegalStateException r14 = new java.lang.IllegalStateException     // Catch:{ all -> 0x00af }
            java.lang.String r12 = r12.toString()     // Catch:{ all -> 0x00af }
            r14.<init>(r12)     // Catch:{ all -> 0x00af }
            throw r14     // Catch:{ all -> 0x00af }
        L_0x00af:
            r12 = move-exception
            r14 = r3
            io.ktor.utils.io.core.Buffer r14 = (io.ktor.utils.io.core.Buffer) r14     // Catch:{ all -> 0x0128 }
            r14.getWritePosition()     // Catch:{ all -> 0x0128 }
            r14.getReadPosition()     // Catch:{ all -> 0x0128 }
            throw r12     // Catch:{ all -> 0x0128 }
        L_0x00ba:
            if (r9 != 0) goto L_0x00c4
            io.ktor.utils.io.core.internal.ChunkBuffer r8 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadNextHead(r13, r3)     // Catch:{ all -> 0x00c1 }
            goto L_0x00e0
        L_0x00c1:
            r12 = move-exception
            r2 = 0
            goto L_0x0129
        L_0x00c4:
            if (r9 < r5) goto L_0x00d9
            r8 = r3
            io.ktor.utils.io.core.Buffer r8 = (io.ktor.utils.io.core.Buffer) r8     // Catch:{ all -> 0x00c1 }
            int r9 = r8.getCapacity()     // Catch:{ all -> 0x00c1 }
            int r8 = r8.getLimit()     // Catch:{ all -> 0x00c1 }
            int r9 = r9 - r8
            r8 = 8
            if (r9 >= r8) goto L_0x00d7
            goto L_0x00d9
        L_0x00d7:
            r8 = r3
            goto L_0x00e0
        L_0x00d9:
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r13, r3)     // Catch:{ all -> 0x00c1 }
            io.ktor.utils.io.core.internal.ChunkBuffer r8 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadFirstHead(r13, r5)     // Catch:{ all -> 0x00c1 }
        L_0x00e0:
            if (r8 != 0) goto L_0x00e3
            goto L_0x00e7
        L_0x00e3:
            r3 = r8
            if (r5 > 0) goto L_0x0022
            r4 = 1
        L_0x00e7:
            if (r4 == 0) goto L_0x00ec
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r13, r3)
        L_0x00ec:
            r4 = r6
        L_0x00ed:
            r1.clear()
            int r13 = r15 - r4
            if (r13 == 0) goto L_0x0127
            if (r13 >= r0) goto L_0x00f9
            r1.limit(r13)
        L_0x00f9:
            java.nio.ByteBuffer r13 = EmptyByteBuffer
            java.nio.charset.CoderResult r13 = r12.decode(r13, r1, r2)
            r1.flip()
            int r3 = r1.remaining()
            int r4 = r4 + r3
            r3 = r1
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3
            r14.append(r3)
            boolean r3 = r13.isUnmappable()
            if (r3 != 0) goto L_0x0119
            boolean r3 = r13.isMalformed()
            if (r3 == 0) goto L_0x0121
        L_0x0119:
            java.lang.String r3 = "cr"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r13, r3)
            throwExceptionWrapped(r13)
        L_0x0121:
            boolean r13 = r13.isOverflow()
            if (r13 != 0) goto L_0x00ed
        L_0x0127:
            return r4
        L_0x0128:
            r12 = move-exception
        L_0x0129:
            if (r2 == 0) goto L_0x012e
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r13, r3)
        L_0x012e:
            goto L_0x0130
        L_0x012f:
            throw r12
        L_0x0130:
            goto L_0x012f
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.charsets.CharsetJVMKt.decode(java.nio.charset.CharsetDecoder, io.ktor.utils.io.core.Input, java.lang.Appendable, int):int");
    }

    public static final String decodeExactBytes(CharsetDecoder charsetDecoder, Input input, int i) {
        Intrinsics.checkNotNullParameter(charsetDecoder, "<this>");
        Intrinsics.checkNotNullParameter(input, TvContractCompat.PARAM_INPUT);
        if (i == 0) {
            return "";
        }
        if (input.getHeadEndExclusive() - input.getHeadPosition() < i) {
            return decodeImplSlow(charsetDecoder, input, i);
        }
        if (!input.m160getHeadMemorySK3TCg8().hasArray()) {
            return decodeImplByteBuffer(charsetDecoder, input, i);
        }
        ByteBuffer r0 = input.m160getHeadMemorySK3TCg8();
        byte[] array = r0.array();
        Intrinsics.checkNotNullExpressionValue(array, "bb.array()");
        Charset charset = charsetDecoder.charset();
        Intrinsics.checkNotNullExpressionValue(charset, "charset()");
        String str = new String(array, r0.arrayOffset() + r0.position() + input.getHead().getReadPosition(), i, charset);
        input.discardExact(i);
        return str;
    }

    private static final String decodeImplByteBuffer(CharsetDecoder charsetDecoder, Input input, int i) {
        CharBuffer allocate = CharBuffer.allocate(i);
        ByteBuffer r5 = Memory.m1519slice87lwejk(input.m160getHeadMemorySK3TCg8(), input.getHead().getReadPosition(), i);
        CoderResult decode = charsetDecoder.decode(r5, allocate, true);
        if (decode.isMalformed() || decode.isUnmappable()) {
            Intrinsics.checkNotNullExpressionValue(decode, "rc");
            throwExceptionWrapped(decode);
        }
        allocate.flip();
        input.discardExact(r5.position());
        String charBuffer = allocate.toString();
        Intrinsics.checkNotNullExpressionValue(charBuffer, "cb.toString()");
        return charBuffer;
    }

    /* JADX WARNING: Removed duplicated region for block: B:81:0x0151  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final java.lang.String decodeImplSlow(java.nio.charset.CharsetDecoder r17, io.ktor.utils.io.core.Input r18, int r19) {
        /*
            r0 = r17
            r1 = r18
            r2 = r19
            java.nio.CharBuffer r3 = java.nio.CharBuffer.allocate(r19)
            r4 = 1
            io.ktor.utils.io.core.internal.ChunkBuffer r5 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadFirstHead(r1, r4)
            java.lang.String r6 = "rc"
            if (r5 != 0) goto L_0x0017
            r9 = r2
            r7 = 0
            goto L_0x00f7
        L_0x0017:
            r9 = r2
            r8 = 1
            r10 = 0
            r11 = 1
        L_0x001b:
            r12 = r5
            io.ktor.utils.io.core.Buffer r12 = (io.ktor.utils.io.core.Buffer) r12     // Catch:{ all -> 0x014e }
            int r13 = r12.getWritePosition()     // Catch:{ all -> 0x014e }
            int r12 = r12.getReadPosition()     // Catch:{ all -> 0x014e }
            int r13 = r13 - r12
            if (r13 < r8) goto L_0x00c2
            r8 = r5
            io.ktor.utils.io.core.Buffer r8 = (io.ktor.utils.io.core.Buffer) r8     // Catch:{ all -> 0x00b7 }
            boolean r12 = r3.hasRemaining()     // Catch:{ all -> 0x00b7 }
            if (r12 == 0) goto L_0x00a8
            if (r9 != 0) goto L_0x0036
            goto L_0x00a8
        L_0x0036:
            java.nio.ByteBuffer r10 = r8.m155getMemorySK3TCg8()     // Catch:{ all -> 0x00b7 }
            int r12 = r8.getReadPosition()     // Catch:{ all -> 0x00b7 }
            int r13 = r8.getWritePosition()     // Catch:{ all -> 0x00b7 }
            int r13 = r13 - r12
            java.nio.ByteBuffer r10 = io.ktor.utils.io.bits.Memory.m1519slice87lwejk((java.nio.ByteBuffer) r10, (int) r12, (int) r13)     // Catch:{ all -> 0x00b7 }
            int r12 = r10.limit()     // Catch:{ all -> 0x00b7 }
            int r14 = r10.position()     // Catch:{ all -> 0x00b7 }
            int r15 = r12 - r14
            if (r15 < r9) goto L_0x0055
            r15 = 1
            goto L_0x0056
        L_0x0055:
            r15 = 0
        L_0x0056:
            if (r15 == 0) goto L_0x005d
            int r7 = r14 + r9
            r10.limit(r7)     // Catch:{ all -> 0x00b7 }
        L_0x005d:
            java.nio.charset.CoderResult r7 = r0.decode(r10, r3, r15)     // Catch:{ all -> 0x00b7 }
            boolean r16 = r7.isMalformed()     // Catch:{ all -> 0x00b7 }
            if (r16 != 0) goto L_0x006d
            boolean r16 = r7.isUnmappable()     // Catch:{ all -> 0x00b7 }
            if (r16 == 0) goto L_0x0073
        L_0x006d:
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r6)     // Catch:{ all -> 0x00b7 }
            throwExceptionWrapped(r7)     // Catch:{ all -> 0x00b7 }
        L_0x0073:
            boolean r7 = r7.isUnderflow()     // Catch:{ all -> 0x00b7 }
            if (r7 == 0) goto L_0x0082
            boolean r7 = r10.hasRemaining()     // Catch:{ all -> 0x00b7 }
            if (r7 == 0) goto L_0x0082
            int r11 = r11 + 1
            goto L_0x0083
        L_0x0082:
            r11 = 1
        L_0x0083:
            r10.limit(r12)     // Catch:{ all -> 0x00b7 }
            int r7 = r10.position()     // Catch:{ all -> 0x00b7 }
            int r7 = r7 - r14
            int r9 = r9 - r7
            int r7 = r10.limit()     // Catch:{ all -> 0x00b7 }
            if (r7 != r13) goto L_0x009c
            int r7 = r10.position()     // Catch:{ all -> 0x00b7 }
            r8.discardExact(r7)     // Catch:{ all -> 0x00b7 }
            r8 = r11
            r10 = r15
            goto L_0x00a9
        L_0x009c:
            java.lang.String r0 = "Buffer's limit change is not allowed"
            java.lang.IllegalStateException r2 = new java.lang.IllegalStateException     // Catch:{ all -> 0x00b7 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x00b7 }
            r2.<init>(r0)     // Catch:{ all -> 0x00b7 }
            throw r2     // Catch:{ all -> 0x00b7 }
        L_0x00a8:
            r8 = 0
        L_0x00a9:
            r7 = r5
            io.ktor.utils.io.core.Buffer r7 = (io.ktor.utils.io.core.Buffer) r7     // Catch:{ all -> 0x014e }
            int r12 = r7.getWritePosition()     // Catch:{ all -> 0x014e }
            int r7 = r7.getReadPosition()     // Catch:{ all -> 0x014e }
            int r13 = r12 - r7
            goto L_0x00c2
        L_0x00b7:
            r0 = move-exception
            r2 = r5
            io.ktor.utils.io.core.Buffer r2 = (io.ktor.utils.io.core.Buffer) r2     // Catch:{ all -> 0x014e }
            r2.getWritePosition()     // Catch:{ all -> 0x014e }
            r2.getReadPosition()     // Catch:{ all -> 0x014e }
            throw r0     // Catch:{ all -> 0x014e }
        L_0x00c2:
            if (r13 != 0) goto L_0x00cd
            io.ktor.utils.io.core.internal.ChunkBuffer r7 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadNextHead(r1, r5)     // Catch:{ all -> 0x00c9 }
            goto L_0x00e9
        L_0x00c9:
            r0 = move-exception
            r4 = 0
            goto L_0x014f
        L_0x00cd:
            if (r13 < r8) goto L_0x00e2
            r7 = r5
            io.ktor.utils.io.core.Buffer r7 = (io.ktor.utils.io.core.Buffer) r7     // Catch:{ all -> 0x00c9 }
            int r12 = r7.getCapacity()     // Catch:{ all -> 0x00c9 }
            int r7 = r7.getLimit()     // Catch:{ all -> 0x00c9 }
            int r12 = r12 - r7
            r7 = 8
            if (r12 >= r7) goto L_0x00e0
            goto L_0x00e2
        L_0x00e0:
            r7 = r5
            goto L_0x00e9
        L_0x00e2:
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r1, r5)     // Catch:{ all -> 0x00c9 }
            io.ktor.utils.io.core.internal.ChunkBuffer r7 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadFirstHead(r1, r8)     // Catch:{ all -> 0x00c9 }
        L_0x00e9:
            if (r7 != 0) goto L_0x00ed
            r7 = 0
            goto L_0x00f1
        L_0x00ed:
            r5 = r7
            if (r8 > 0) goto L_0x001b
            r7 = 1
        L_0x00f1:
            if (r7 == 0) goto L_0x00f6
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r1, r5)
        L_0x00f6:
            r7 = r10
        L_0x00f7:
            boolean r1 = r3.hasRemaining()
            if (r1 == 0) goto L_0x0117
            if (r7 != 0) goto L_0x0117
            java.nio.ByteBuffer r1 = EmptyByteBuffer
            java.nio.charset.CoderResult r0 = r0.decode(r1, r3, r4)
            boolean r1 = r0.isMalformed()
            if (r1 != 0) goto L_0x0111
            boolean r1 = r0.isUnmappable()
            if (r1 == 0) goto L_0x0117
        L_0x0111:
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r6)
            throwExceptionWrapped(r0)
        L_0x0117:
            if (r9 > 0) goto L_0x0130
            if (r9 < 0) goto L_0x0128
            r3.flip()
            java.lang.String r0 = r3.toString()
            java.lang.String r1 = "cb.toString()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            return r0
        L_0x0128:
            java.lang.AssertionError r0 = new java.lang.AssertionError
            java.lang.String r1 = "remainingInputBytes < 0"
            r0.<init>(r1)
            throw r0
        L_0x0130:
            java.io.EOFException r0 = new java.io.EOFException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r3 = "Not enough bytes available: had only "
            r1.<init>(r3)
            int r3 = r2 - r9
            r1.append(r3)
            java.lang.String r3 = " instead of "
            r1.append(r3)
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x014e:
            r0 = move-exception
        L_0x014f:
            if (r4 == 0) goto L_0x0154
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r1, r5)
        L_0x0154:
            goto L_0x0156
        L_0x0155:
            throw r0
        L_0x0156:
            goto L_0x0155
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.charsets.CharsetJVMKt.decodeImplSlow(java.nio.charset.CharsetDecoder, io.ktor.utils.io.core.Input, int):java.lang.String");
    }

    private static final void throwExceptionWrapped(CoderResult coderResult) {
        try {
            coderResult.throwException();
        } catch (MalformedInputException e) {
            String message = e.getMessage();
            if (message == null) {
                message = "Failed to decode bytes";
            }
            throw new MalformedInputException(message);
        }
    }

    static {
        ByteBuffer allocate = ByteBuffer.allocate(0);
        Intrinsics.checkNotNull(allocate);
        EmptyByteBuffer = allocate;
    }

    public static final boolean encodeComplete(CharsetEncoder charsetEncoder, Buffer buffer) {
        Intrinsics.checkNotNullParameter(charsetEncoder, "<this>");
        Intrinsics.checkNotNullParameter(buffer, "dst");
        ByteBuffer r0 = buffer.m155getMemorySK3TCg8();
        int writePosition = buffer.getWritePosition();
        int limit = buffer.getLimit() - writePosition;
        ByteBuffer r02 = Memory.m1519slice87lwejk(r0, writePosition, limit);
        CoderResult encode = charsetEncoder.encode(EmptyCharBuffer, r02, true);
        if (encode.isMalformed() || encode.isUnmappable()) {
            Intrinsics.checkNotNullExpressionValue(encode, "result");
            throwExceptionWrapped(encode);
        }
        boolean isUnderflow = encode.isUnderflow();
        if (r02.limit() == limit) {
            buffer.commitWritten(r02.position());
            return isUnderflow;
        }
        throw new IllegalStateException("Buffer's limit change is not allowed".toString());
    }

    /* JADX INFO: finally extract failed */
    public static final int decodeBuffer(CharsetDecoder charsetDecoder, Buffer buffer, Appendable appendable, boolean z, int i) {
        Intrinsics.checkNotNullParameter(charsetDecoder, "<this>");
        Intrinsics.checkNotNullParameter(buffer, TvContractCompat.PARAM_INPUT);
        Intrinsics.checkNotNullParameter(appendable, "out");
        ByteBuffer r9 = buffer.m155getMemorySK3TCg8();
        int readPosition = buffer.getReadPosition();
        int writePosition = buffer.getWritePosition() - readPosition;
        ByteBuffer r92 = Memory.m1519slice87lwejk(r9, readPosition, writePosition);
        ChunkBuffer borrow = ChunkBuffer.Companion.getPool().borrow();
        CharBuffer asCharBuffer = borrow.m155getMemorySK3TCg8().asCharBuffer();
        int i2 = 0;
        while (r92.hasRemaining() && i2 < i) {
            try {
                int min = Math.min(asCharBuffer.capacity(), i - i2);
                asCharBuffer.clear();
                asCharBuffer.limit(min);
                CoderResult decode = charsetDecoder.decode(r92, asCharBuffer, z);
                if (decode.isMalformed() || decode.isUnmappable()) {
                    Intrinsics.checkNotNullExpressionValue(decode, "result");
                    throwExceptionWrapped(decode);
                }
                i2 += min;
            } catch (Throwable th) {
                borrow.release(ChunkBuffer.Companion.getPool());
                throw th;
            }
        }
        borrow.release(ChunkBuffer.Companion.getPool());
        if (r92.limit() == writePosition) {
            buffer.discardExact(r92.position());
            return i2;
        }
        throw new IllegalStateException("Buffer's limit change is not allowed".toString());
    }
}
