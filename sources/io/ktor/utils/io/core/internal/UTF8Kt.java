package io.ktor.utils.io.core.internal;

import io.ktor.utils.io.core.Buffer;
import java.io.EOFException;
import java.nio.ByteBuffer;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.UShort;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.CharCompanionObject;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import org.bouncycastle.asn1.BERTags;

@Metadata(d1 = {"\u0000d\n\u0000\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\u0001\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0002\b\u0014\u001a\u0010\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\u0001H\u0000\u001a\u0011\u0010\b\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\u0001H\b\u001a\u0018\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0000\u001a_\u0010\u000e\u001a\u00020\u000f2\n\u0010\u0010\u001a\u00060\u0011j\u0002`\u00122\u0006\u0010\u0013\u001a\u00020\u00012$\u0010\u0014\u001a \b\u0001\u0012\u0004\u0012\u00020\u0001\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00170\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u00180\u00152\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u001b0\u001aH@ø\u0001\u0000¢\u0006\u0002\u0010\u001c\u001a\u0010\u0010\u001d\u001a\u00020\u00012\u0006\u0010\u001e\u001a\u00020\u0001H\u0001\u001a\u0010\u0010\u001f\u001a\u00020\u000f2\u0006\u0010\u001e\u001a\u00020\u0001H\u0001\u001a\u0010\u0010 \u001a\u00020\u000f2\u0006\u0010\n\u001a\u00020\u0001H\u0001\u001a\u0010\u0010!\u001a\u00020\u00012\u0006\u0010\u001e\u001a\u00020\u0001H\u0001\u001a\u0010\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u0001H\u0001\u001a\u0010\u0010%\u001a\u00020#2\u0006\u0010&\u001a\u00020\u0001H\u0001\u001a\u0010\u0010'\u001a\u00020#2\u0006\u0010(\u001a\u00020\u0001H\u0002\u001a$\u0010)\u001a\u00020\u000f*\u00020*2\u0012\u0010+\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u000f0\u001aH\bø\u0001\u0001\u001a$\u0010,\u001a\u00020\u0001*\u00020*2\u0012\u0010+\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u000f0\u001aH\bø\u0001\u0001\u001aA\u0010-\u001a\u00020.*\u00020/2\u0006\u00100\u001a\u0002012\u0006\u00102\u001a\u00020\u00012\u0006\u00103\u001a\u00020\u00012\u0006\u00104\u001a\u00020\u00012\u0006\u00105\u001a\u00020\u0001H\u0000ø\u0001\u0002ø\u0001\u0000¢\u0006\u0004\b6\u00107\u001aQ\u00108\u001a\u00020.*\u00020/2\u0006\u00100\u001a\u0002012\u0006\u00109\u001a\u00020\u00012\u0006\u0010:\u001a\u00020\u00012\u0006\u00102\u001a\u00020\u00012\u0006\u0010;\u001a\u00020\u00012\u0006\u0010<\u001a\u00020\u00012\u0006\u00104\u001a\u00020\u0001H\u0002ø\u0001\u0002ø\u0001\u0000¢\u0006\u0004\b=\u0010>\u001aQ\u0010?\u001a\u00020.*\u00020/2\u0006\u00100\u001a\u0002012\u0006\u00109\u001a\u00020\u00012\u0006\u0010:\u001a\u00020\u00012\u0006\u00102\u001a\u00020\u00012\u0006\u0010;\u001a\u00020\u00012\u0006\u0010<\u001a\u00020\u00012\u0006\u00104\u001a\u00020\u0001H\u0002ø\u0001\u0002ø\u0001\u0000¢\u0006\u0004\b@\u0010>\u001a*\u0010A\u001a\u00020\u0001*\u00020/2\u0006\u0010B\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\u0001H\bø\u0001\u0002ø\u0001\u0000¢\u0006\u0004\bC\u0010D\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\u0002\u0012\n\u0002\b\u0019\n\u0005\b20\u0001\n\u0005\b¡\u001e0\u0001¨\u0006E"}, d2 = {"HighSurrogateMagic", "", "MaxCodePoint", "MinHighSurrogate", "MinLowSurrogate", "MinSupplementary", "byteCountUtf8", "firstByte", "charactersSize", "v", "codePoint", "high", "", "low", "decodeUTF8LineLoopSuspend", "", "out", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "limit", "nextChunk", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "Lio/ktor/utils/io/core/Input;", "", "afterRead", "Lkotlin/Function1;", "", "(Ljava/lang/Appendable;ILkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "highSurrogate", "cp", "isBmpCodePoint", "isValidCodePoint", "lowSurrogate", "malformedByteCount", "", "byteCount", "malformedCodePoint", "value", "prematureEndOfStreamUtf", "size", "decodeASCII", "Lio/ktor/utils/io/core/Buffer;", "consumer", "decodeUTF8", "encodeUTF8", "Lio/ktor/utils/io/core/internal/EncodeResult;", "Lio/ktor/utils/io/bits/Memory;", "text", "", "from", "to", "dstOffset", "dstLimit", "encodeUTF8-lBXzO7A", "(Ljava/nio/ByteBuffer;Ljava/lang/CharSequence;IIII)I", "encodeUTF8Stage1", "index1", "lastCharIndex", "resultPosition1", "resultLimit", "encodeUTF8Stage1-Vm9B2pQ", "(Ljava/nio/ByteBuffer;Ljava/lang/CharSequence;IIIIII)I", "encodeUTF8Stage2", "encodeUTF8Stage2-Vm9B2pQ", "putUtf8Char", "offset", "putUtf8Char-62zg_DM", "(Ljava/nio/ByteBuffer;II)I", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: UTF8.kt */
public final class UTF8Kt {
    private static final int HighSurrogateMagic = 55232;
    private static final int MaxCodePoint = 1114111;
    private static final int MinHighSurrogate = 55296;
    private static final int MinLowSurrogate = 56320;
    private static final int MinSupplementary = 65536;

    public static final int byteCountUtf8(int i) {
        int i2 = 0;
        int i3 = 128;
        for (int i4 = 1; i4 < 7 && (i & i3) != 0; i4++) {
            i &= i3 ^ -1;
            i3 >>= 1;
            i2++;
        }
        return i2;
    }

    public static final int codePoint(char c, char c2) {
        return ((c - 55232) << 10) | (c2 - CharCompanionObject.MIN_LOW_SURROGATE);
    }

    public static final int highSurrogate(int i) {
        return (i >>> 10) + 55232;
    }

    public static final boolean isBmpCodePoint(int i) {
        return (i >>> 16) == 0;
    }

    public static final boolean isValidCodePoint(int i) {
        return i <= MaxCodePoint;
    }

    public static final int lowSurrogate(int i) {
        return (i & 1023) + 56320;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:104:0x0212, code lost:
        r0.discardExact(((r15 - r18) - r7.element) + r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:120:0x025b, code lost:
        r6 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:122:?, code lost:
        r8.element = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:127:0x026a, code lost:
        r5.element = r6 ? 1 : 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:130:0x0271, code lost:
        r8.element = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:147:0x029e, code lost:
        r0.discardExact(((r15 - r18) - r7.element) + (r6 ? 1 : 0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:163:0x0317, code lost:
        r10.element = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:164:0x031b, code lost:
        if (r5.element <= 0) goto L_0x0322;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:165:0x031d, code lost:
        r0.discardExact(r5.element);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:167:0x0324, code lost:
        if (r8.element == false) goto L_0x0328;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:168:0x0326, code lost:
        r0 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:169:0x0328, code lost:
        r0 = kotlin.ranges.RangesKt.coerceAtLeast(r10.element, 1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:170:0x032f, code lost:
        r10.element = r0;
        r0 = r10.element;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:173:?, code lost:
        r2 = r21;
        r5 = r2.getWritePosition() - r2.getReadPosition();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:?, code lost:
        r8.element = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x015b, code lost:
        r0.discardExact(r15 - r18);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x0160, code lost:
        r10 = r22;
        r2 = -1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0163, code lost:
        r4 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x01fa, code lost:
        r6 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:?, code lost:
        r8.element = true;
     */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0060  */
    /* JADX WARNING: Removed duplicated region for block: B:144:0x0299 A[Catch:{ all -> 0x0147, all -> 0x0342 }] */
    /* JADX WARNING: Removed duplicated region for block: B:208:0x03d7  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x00ad  */
    /* JADX WARNING: Removed duplicated region for block: B:212:0x03e0  */
    /* JADX WARNING: Removed duplicated region for block: B:222:0x03f4  */
    /* JADX WARNING: Removed duplicated region for block: B:234:0x025b A[EDGE_INSN: B:234:0x025b->B:120:0x025b ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x00ba  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object decodeUTF8LineLoopSuspend(java.lang.Appendable r28, int r29, kotlin.jvm.functions.Function2<? super java.lang.Integer, ? super kotlin.coroutines.Continuation<? super io.ktor.utils.io.core.Input>, ? extends java.lang.Object> r30, kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> r31, kotlin.coroutines.Continuation<? super java.lang.Boolean> r32) {
        /*
            r0 = r32
            boolean r1 = r0 instanceof io.ktor.utils.io.core.internal.UTF8Kt$decodeUTF8LineLoopSuspend$1
            if (r1 == 0) goto L_0x0016
            r1 = r0
            io.ktor.utils.io.core.internal.UTF8Kt$decodeUTF8LineLoopSuspend$1 r1 = (io.ktor.utils.io.core.internal.UTF8Kt$decodeUTF8LineLoopSuspend$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0016
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L_0x001b
        L_0x0016:
            io.ktor.utils.io.core.internal.UTF8Kt$decodeUTF8LineLoopSuspend$1 r1 = new io.ktor.utils.io.core.internal.UTF8Kt$decodeUTF8LineLoopSuspend$1
            r1.<init>(r0)
        L_0x001b:
            java.lang.Object r0 = r1.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r1.label
            r5 = 1
            if (r3 == 0) goto L_0x0060
            if (r3 != r5) goto L_0x0058
            int r3 = r1.I$0
            java.lang.Object r6 = r1.L$6
            kotlin.jvm.internal.Ref$BooleanRef r6 = (kotlin.jvm.internal.Ref.BooleanRef) r6
            java.lang.Object r7 = r1.L$5
            kotlin.jvm.internal.Ref$BooleanRef r7 = (kotlin.jvm.internal.Ref.BooleanRef) r7
            java.lang.Object r8 = r1.L$4
            kotlin.jvm.internal.Ref$IntRef r8 = (kotlin.jvm.internal.Ref.IntRef) r8
            java.lang.Object r9 = r1.L$3
            kotlin.jvm.internal.Ref$IntRef r9 = (kotlin.jvm.internal.Ref.IntRef) r9
            java.lang.Object r10 = r1.L$2
            kotlin.jvm.functions.Function1 r10 = (kotlin.jvm.functions.Function1) r10
            java.lang.Object r11 = r1.L$1
            kotlin.jvm.functions.Function2 r11 = (kotlin.jvm.functions.Function2) r11
            java.lang.Object r12 = r1.L$0
            java.lang.Appendable r12 = (java.lang.Appendable) r12
            kotlin.ResultKt.throwOnFailure(r0)
            r26 = r7
            r7 = r1
            r1 = r3
            r3 = r11
            r11 = r9
            r9 = r26
            r27 = r8
            r8 = r6
            r6 = r10
            r10 = r27
            goto L_0x00b2
        L_0x0058:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0060:
            kotlin.ResultKt.throwOnFailure(r0)
            kotlin.jvm.internal.Ref$IntRef r0 = new kotlin.jvm.internal.Ref$IntRef
            r0.<init>()
            kotlin.jvm.internal.Ref$IntRef r3 = new kotlin.jvm.internal.Ref$IntRef
            r3.<init>()
            r3.element = r5
            kotlin.jvm.internal.Ref$BooleanRef r6 = new kotlin.jvm.internal.Ref$BooleanRef
            r6.<init>()
            kotlin.jvm.internal.Ref$BooleanRef r7 = new kotlin.jvm.internal.Ref$BooleanRef
            r7.<init>()
            r11 = r0
            r10 = r3
            r9 = r6
            r8 = r7
            r0 = r28
            r3 = r30
            r6 = r31
            r7 = r1
            r1 = r29
        L_0x0086:
            boolean r12 = r8.element
            if (r12 != 0) goto L_0x00b7
            int r12 = r10.element
            if (r12 == 0) goto L_0x00b7
            int r12 = r10.element
            java.lang.Integer r12 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r12)
            r7.L$0 = r0
            r7.L$1 = r3
            r7.L$2 = r6
            r7.L$3 = r11
            r7.L$4 = r10
            r7.L$5 = r9
            r7.L$6 = r8
            r7.I$0 = r1
            r7.label = r5
            java.lang.Object r12 = r3.invoke(r12, r7)
            if (r12 != r2) goto L_0x00ad
            return r2
        L_0x00ad:
            r26 = r12
            r12 = r0
            r0 = r26
        L_0x00b2:
            r13 = r0
            io.ktor.utils.io.core.Input r13 = (io.ktor.utils.io.core.Input) r13
            if (r13 != 0) goto L_0x00ba
        L_0x00b7:
            r4 = 0
            goto L_0x03db
        L_0x00ba:
            long r14 = r13.getRemaining()
            io.ktor.utils.io.core.internal.ChunkBuffer r0 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadFirstHead(r13, r5)
            if (r0 != 0) goto L_0x00d2
            r16 = r2
            r28 = r3
            r17 = r6
            r29 = r7
            r2 = r13
            r30 = r14
            r4 = 0
            goto L_0x03a5
        L_0x00d2:
            r4 = r0
            r0 = 1
        L_0x00d4:
            r16 = r4
            io.ktor.utils.io.core.Buffer r16 = (io.ktor.utils.io.core.Buffer) r16     // Catch:{ all -> 0x03d1 }
            int r17 = r16.getWritePosition()     // Catch:{ all -> 0x03d1 }
            int r16 = r16.getReadPosition()     // Catch:{ all -> 0x03d1 }
            int r5 = r17 - r16
            if (r5 < r0) goto L_0x035b
            r0 = r4
            io.ktor.utils.io.core.Buffer r0 = (io.ktor.utils.io.core.Buffer) r0     // Catch:{ all -> 0x0344 }
            kotlin.jvm.internal.Ref$IntRef r5 = new kotlin.jvm.internal.Ref$IntRef     // Catch:{ all -> 0x0344 }
            r5.<init>()     // Catch:{ all -> 0x0344 }
            r16 = r2
            kotlin.jvm.internal.Ref$IntRef r2 = new kotlin.jvm.internal.Ref$IntRef     // Catch:{ all -> 0x0344 }
            r2.<init>()     // Catch:{ all -> 0x0344 }
            r28 = r3
            kotlin.jvm.internal.Ref$IntRef r3 = new kotlin.jvm.internal.Ref$IntRef     // Catch:{ all -> 0x0344 }
            r3.<init>()     // Catch:{ all -> 0x0344 }
            r29 = r7
            kotlin.jvm.internal.Ref$IntRef r7 = new kotlin.jvm.internal.Ref$IntRef     // Catch:{ all -> 0x0344 }
            r7.<init>()     // Catch:{ all -> 0x0344 }
            r17 = r6
            java.nio.ByteBuffer r6 = r0.m155getMemorySK3TCg8()     // Catch:{ all -> 0x0344 }
            int r18 = r0.getReadPosition()     // Catch:{ all -> 0x0344 }
            r30 = r14
            int r14 = r0.getWritePosition()     // Catch:{ all -> 0x0344 }
            r15 = r18
        L_0x0113:
            if (r15 >= r14) goto L_0x0306
            r19 = r13
            byte r13 = r6.get(r15)     // Catch:{ all -> 0x0302 }
            r20 = r6
            r6 = r13 & 255(0xff, float:3.57E-43)
            r21 = r4
            r4 = r13 & 128(0x80, float:1.794E-43)
            r22 = r10
            java.lang.String r10 = " exceeded"
            r23 = r13
            java.lang.String r13 = "Too many characters in line: limit "
            r24 = r14
            r14 = 13
            r25 = -1
            if (r4 != 0) goto L_0x0199
            int r4 = r2.element     // Catch:{ all -> 0x0342 }
            if (r4 != 0) goto L_0x018e
            char r4 = (char) r6
            if (r4 != r14) goto L_0x014b
            boolean r4 = r9.element     // Catch:{ all -> 0x0147 }
            if (r4 == 0) goto L_0x0142
            r6 = 1
            r8.element = r6     // Catch:{ all -> 0x0342 }
            goto L_0x015b
        L_0x0142:
            r6 = 1
            r9.element = r6     // Catch:{ all -> 0x0342 }
            goto L_0x02f3
        L_0x0147:
            r0 = move-exception
            r6 = 1
            goto L_0x0349
        L_0x014b:
            r6 = 1
            r14 = 10
            if (r4 != r14) goto L_0x0155
            r8.element = r6     // Catch:{ all -> 0x0342 }
            r5.element = r6     // Catch:{ all -> 0x0342 }
            goto L_0x015b
        L_0x0155:
            boolean r14 = r9.element     // Catch:{ all -> 0x0342 }
            if (r14 == 0) goto L_0x0166
            r8.element = r6     // Catch:{ all -> 0x0342 }
        L_0x015b:
            int r15 = r15 - r18
            r0.discardExact(r15)     // Catch:{ all -> 0x0342 }
        L_0x0160:
            r10 = r22
            r2 = -1
        L_0x0163:
            r4 = 0
            goto L_0x0317
        L_0x0166:
            int r6 = r11.element     // Catch:{ all -> 0x0342 }
            if (r6 == r1) goto L_0x0176
            int r6 = r11.element     // Catch:{ all -> 0x0342 }
            r10 = 1
            int r6 = r6 + r10
            r11.element = r6     // Catch:{ all -> 0x0342 }
            char r4 = (char) r4     // Catch:{ all -> 0x0342 }
            r12.append(r4)     // Catch:{ all -> 0x0342 }
            goto L_0x02f3
        L_0x0176:
            io.ktor.utils.io.charsets.TooLongLineException r0 = new io.ktor.utils.io.charsets.TooLongLineException     // Catch:{ all -> 0x0342 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0342 }
            r2.<init>()     // Catch:{ all -> 0x0342 }
            r2.append(r13)     // Catch:{ all -> 0x0342 }
            r2.append(r1)     // Catch:{ all -> 0x0342 }
            r2.append(r10)     // Catch:{ all -> 0x0342 }
            java.lang.String r1 = r2.toString()     // Catch:{ all -> 0x0342 }
            r0.<init>(r1)     // Catch:{ all -> 0x0342 }
            throw r0     // Catch:{ all -> 0x0342 }
        L_0x018e:
            int r0 = r2.element     // Catch:{ all -> 0x0342 }
            malformedByteCount(r0)     // Catch:{ all -> 0x0342 }
            kotlin.KotlinNothingValueException r0 = new kotlin.KotlinNothingValueException     // Catch:{ all -> 0x0342 }
            r0.<init>()     // Catch:{ all -> 0x0342 }
            throw r0     // Catch:{ all -> 0x0342 }
        L_0x0199:
            int r4 = r2.element     // Catch:{ all -> 0x0342 }
            if (r4 != 0) goto L_0x01d6
            r3.element = r6     // Catch:{ all -> 0x0342 }
            r4 = 128(0x80, float:1.794E-43)
            r6 = 1
        L_0x01a2:
            r10 = 7
            if (r6 >= r10) goto L_0x01bc
            int r10 = r3.element     // Catch:{ all -> 0x0342 }
            r10 = r10 & r4
            if (r10 == 0) goto L_0x01bc
            int r10 = r3.element     // Catch:{ all -> 0x0342 }
            r13 = r4 ^ -1
            r10 = r10 & r13
            r3.element = r10     // Catch:{ all -> 0x0342 }
            int r4 = r4 >> 1
            int r10 = r2.element     // Catch:{ all -> 0x0342 }
            r13 = 1
            int r10 = r10 + r13
            r2.element = r10     // Catch:{ all -> 0x0342 }
            int r6 = r6 + 1
            goto L_0x01a2
        L_0x01bc:
            int r4 = r2.element     // Catch:{ all -> 0x0342 }
            r7.element = r4     // Catch:{ all -> 0x0342 }
            int r4 = r2.element     // Catch:{ all -> 0x0342 }
            int r4 = r4 + -1
            r2.element = r4     // Catch:{ all -> 0x0342 }
            int r4 = r7.element     // Catch:{ all -> 0x0342 }
            int r14 = r24 - r15
            if (r4 <= r14) goto L_0x02f3
            int r15 = r15 - r18
            r0.discardExact(r15)     // Catch:{ all -> 0x0342 }
            int r2 = r7.element     // Catch:{ all -> 0x0342 }
            r10 = r22
            goto L_0x0163
        L_0x01d6:
            int r4 = r3.element     // Catch:{ all -> 0x0342 }
            int r4 = r4 << 6
            r6 = r23 & 127(0x7f, float:1.78E-43)
            r4 = r4 | r6
            r3.element = r4     // Catch:{ all -> 0x0342 }
            int r4 = r2.element     // Catch:{ all -> 0x0342 }
            int r4 = r4 + -1
            r2.element = r4     // Catch:{ all -> 0x0342 }
            int r4 = r2.element     // Catch:{ all -> 0x0342 }
            if (r4 != 0) goto L_0x02f3
            int r4 = r3.element     // Catch:{ all -> 0x0342 }
            boolean r4 = isBmpCodePoint(r4)     // Catch:{ all -> 0x0342 }
            if (r4 == 0) goto L_0x0246
            int r4 = r3.element     // Catch:{ all -> 0x0342 }
            char r4 = (char) r4
            if (r4 != r14) goto L_0x0202
            boolean r4 = r9.element     // Catch:{ all -> 0x0147 }
            if (r4 == 0) goto L_0x01fe
            r6 = 1
            r8.element = r6     // Catch:{ all -> 0x0342 }
            goto L_0x0212
        L_0x01fe:
            r6 = 1
            r9.element = r6     // Catch:{ all -> 0x0342 }
            goto L_0x022b
        L_0x0202:
            r6 = 1
            r14 = 10
            if (r4 != r14) goto L_0x020c
            r8.element = r6     // Catch:{ all -> 0x0342 }
            r5.element = r6     // Catch:{ all -> 0x0342 }
            goto L_0x0212
        L_0x020c:
            boolean r14 = r9.element     // Catch:{ all -> 0x0342 }
            if (r14 == 0) goto L_0x021d
            r8.element = r6     // Catch:{ all -> 0x0342 }
        L_0x0212:
            int r15 = r15 - r18
            int r2 = r7.element     // Catch:{ all -> 0x0342 }
            int r15 = r15 - r2
            int r15 = r15 + r6
            r0.discardExact(r15)     // Catch:{ all -> 0x0342 }
            goto L_0x0160
        L_0x021d:
            int r6 = r11.element     // Catch:{ all -> 0x0342 }
            if (r6 == r1) goto L_0x022e
            int r6 = r11.element     // Catch:{ all -> 0x0342 }
            r10 = 1
            int r6 = r6 + r10
            r11.element = r6     // Catch:{ all -> 0x0342 }
        L_0x0227:
            char r4 = (char) r4     // Catch:{ all -> 0x0342 }
            r12.append(r4)     // Catch:{ all -> 0x0342 }
        L_0x022b:
            r4 = 0
            goto L_0x02b5
        L_0x022e:
            io.ktor.utils.io.charsets.TooLongLineException r0 = new io.ktor.utils.io.charsets.TooLongLineException     // Catch:{ all -> 0x0342 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0342 }
            r2.<init>()     // Catch:{ all -> 0x0342 }
            r2.append(r13)     // Catch:{ all -> 0x0342 }
            r2.append(r1)     // Catch:{ all -> 0x0342 }
            r2.append(r10)     // Catch:{ all -> 0x0342 }
            java.lang.String r1 = r2.toString()     // Catch:{ all -> 0x0342 }
            r0.<init>(r1)     // Catch:{ all -> 0x0342 }
            throw r0     // Catch:{ all -> 0x0342 }
        L_0x0246:
            int r4 = r3.element     // Catch:{ all -> 0x0342 }
            boolean r4 = isValidCodePoint(r4)     // Catch:{ all -> 0x0342 }
            if (r4 == 0) goto L_0x02e8
            int r4 = r3.element     // Catch:{ all -> 0x0342 }
            int r4 = highSurrogate(r4)     // Catch:{ all -> 0x0342 }
            char r4 = (char) r4
            if (r4 != r14) goto L_0x0263
            boolean r4 = r9.element     // Catch:{ all -> 0x0147 }
            if (r4 == 0) goto L_0x025f
        L_0x025b:
            r6 = 1
            r8.element = r6     // Catch:{ all -> 0x0342 }
            goto L_0x029e
        L_0x025f:
            r6 = 1
            r9.element = r6     // Catch:{ all -> 0x0342 }
            goto L_0x0281
        L_0x0263:
            r6 = 1
            r14 = 10
            if (r4 != r14) goto L_0x026d
            r8.element = r6     // Catch:{ all -> 0x0342 }
        L_0x026a:
            r5.element = r6     // Catch:{ all -> 0x0342 }
            goto L_0x029e
        L_0x026d:
            boolean r14 = r9.element     // Catch:{ all -> 0x0342 }
            if (r14 == 0) goto L_0x0274
        L_0x0271:
            r8.element = r6     // Catch:{ all -> 0x0342 }
            goto L_0x029e
        L_0x0274:
            int r14 = r11.element     // Catch:{ all -> 0x0342 }
            if (r14 == r1) goto L_0x02d0
            int r14 = r11.element     // Catch:{ all -> 0x0342 }
            int r14 = r14 + r6
            r11.element = r14     // Catch:{ all -> 0x0342 }
            char r4 = (char) r4     // Catch:{ all -> 0x0342 }
            r12.append(r4)     // Catch:{ all -> 0x0342 }
        L_0x0281:
            int r4 = r3.element     // Catch:{ all -> 0x0342 }
            int r4 = lowSurrogate(r4)     // Catch:{ all -> 0x0342 }
            char r4 = (char) r4
            r6 = 13
            if (r4 != r6) goto L_0x0291
            boolean r4 = r9.element     // Catch:{ all -> 0x0147 }
            if (r4 == 0) goto L_0x01fe
            goto L_0x025b
        L_0x0291:
            r6 = 1
            r14 = 10
            if (r4 != r14) goto L_0x0299
            r8.element = r6     // Catch:{ all -> 0x0342 }
            goto L_0x026a
        L_0x0299:
            boolean r14 = r9.element     // Catch:{ all -> 0x0342 }
            if (r14 == 0) goto L_0x02a9
            goto L_0x0271
        L_0x029e:
            int r15 = r15 - r18
            int r2 = r7.element     // Catch:{ all -> 0x0342 }
            int r15 = r15 - r2
            int r15 = r15 + r6
            r0.discardExact(r15)     // Catch:{ all -> 0x0342 }
            goto L_0x0160
        L_0x02a9:
            int r6 = r11.element     // Catch:{ all -> 0x0342 }
            if (r6 == r1) goto L_0x02b8
            int r6 = r11.element     // Catch:{ all -> 0x0342 }
            r10 = 1
            int r6 = r6 + r10
            r11.element = r6     // Catch:{ all -> 0x0342 }
            goto L_0x0227
        L_0x02b5:
            r3.element = r4     // Catch:{ all -> 0x0342 }
            goto L_0x02f4
        L_0x02b8:
            io.ktor.utils.io.charsets.TooLongLineException r0 = new io.ktor.utils.io.charsets.TooLongLineException     // Catch:{ all -> 0x0342 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0342 }
            r2.<init>()     // Catch:{ all -> 0x0342 }
            r2.append(r13)     // Catch:{ all -> 0x0342 }
            r2.append(r1)     // Catch:{ all -> 0x0342 }
            r2.append(r10)     // Catch:{ all -> 0x0342 }
            java.lang.String r1 = r2.toString()     // Catch:{ all -> 0x0342 }
            r0.<init>(r1)     // Catch:{ all -> 0x0342 }
            throw r0     // Catch:{ all -> 0x0342 }
        L_0x02d0:
            io.ktor.utils.io.charsets.TooLongLineException r0 = new io.ktor.utils.io.charsets.TooLongLineException     // Catch:{ all -> 0x0342 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0342 }
            r2.<init>()     // Catch:{ all -> 0x0342 }
            r2.append(r13)     // Catch:{ all -> 0x0342 }
            r2.append(r1)     // Catch:{ all -> 0x0342 }
            r2.append(r10)     // Catch:{ all -> 0x0342 }
            java.lang.String r1 = r2.toString()     // Catch:{ all -> 0x0342 }
            r0.<init>(r1)     // Catch:{ all -> 0x0342 }
            throw r0     // Catch:{ all -> 0x0342 }
        L_0x02e8:
            int r0 = r3.element     // Catch:{ all -> 0x0342 }
            malformedCodePoint(r0)     // Catch:{ all -> 0x0342 }
            kotlin.KotlinNothingValueException r0 = new kotlin.KotlinNothingValueException     // Catch:{ all -> 0x0342 }
            r0.<init>()     // Catch:{ all -> 0x0342 }
            throw r0     // Catch:{ all -> 0x0342 }
        L_0x02f3:
            r4 = 0
        L_0x02f4:
            int r15 = r15 + 1
            r13 = r19
            r6 = r20
            r4 = r21
            r10 = r22
            r14 = r24
            goto L_0x0113
        L_0x0302:
            r0 = move-exception
            r21 = r4
            goto L_0x0349
        L_0x0306:
            r21 = r4
            r22 = r10
            r19 = r13
            r24 = r14
            r4 = 0
            int r14 = r24 - r18
            r0.discardExact(r14)     // Catch:{ all -> 0x0342 }
            r10 = r22
            r2 = 0
        L_0x0317:
            r10.element = r2     // Catch:{ all -> 0x0342 }
            int r2 = r5.element     // Catch:{ all -> 0x0342 }
            if (r2 <= 0) goto L_0x0322
            int r2 = r5.element     // Catch:{ all -> 0x0342 }
            r0.discardExact(r2)     // Catch:{ all -> 0x0342 }
        L_0x0322:
            boolean r0 = r8.element     // Catch:{ all -> 0x0342 }
            if (r0 == 0) goto L_0x0328
            r0 = 0
            goto L_0x032f
        L_0x0328:
            int r0 = r10.element     // Catch:{ all -> 0x0342 }
            r2 = 1
            int r0 = kotlin.ranges.RangesKt.coerceAtLeast((int) r0, (int) r2)     // Catch:{ all -> 0x0342 }
        L_0x032f:
            r10.element = r0     // Catch:{ all -> 0x0342 }
            int r0 = r10.element     // Catch:{ all -> 0x0342 }
            r2 = r21
            io.ktor.utils.io.core.Buffer r2 = (io.ktor.utils.io.core.Buffer) r2     // Catch:{ all -> 0x0354 }
            int r3 = r2.getWritePosition()     // Catch:{ all -> 0x0354 }
            int r2 = r2.getReadPosition()     // Catch:{ all -> 0x0354 }
            int r5 = r3 - r2
            goto L_0x036a
        L_0x0342:
            r0 = move-exception
            goto L_0x0349
        L_0x0344:
            r0 = move-exception
            r21 = r4
            r19 = r13
        L_0x0349:
            r4 = r21
            io.ktor.utils.io.core.Buffer r4 = (io.ktor.utils.io.core.Buffer) r4     // Catch:{ all -> 0x0354 }
            r4.getWritePosition()     // Catch:{ all -> 0x0354 }
            r4.getReadPosition()     // Catch:{ all -> 0x0354 }
            throw r0     // Catch:{ all -> 0x0354 }
        L_0x0354:
            r0 = move-exception
            r2 = r19
            r3 = r21
            goto L_0x03d4
        L_0x035b:
            r16 = r2
            r28 = r3
            r21 = r4
            r17 = r6
            r29 = r7
            r19 = r13
            r30 = r14
            r4 = 0
        L_0x036a:
            if (r5 != 0) goto L_0x0378
            r2 = r19
            r3 = r21
            io.ktor.utils.io.core.internal.ChunkBuffer r5 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadNextHead(r2, r3)     // Catch:{ all -> 0x0375 }
            goto L_0x0398
        L_0x0375:
            r0 = move-exception
            goto L_0x03d5
        L_0x0378:
            r2 = r19
            r3 = r21
            if (r5 < r0) goto L_0x0391
            r5 = r3
            io.ktor.utils.io.core.Buffer r5 = (io.ktor.utils.io.core.Buffer) r5     // Catch:{ all -> 0x0375 }
            int r6 = r5.getCapacity()     // Catch:{ all -> 0x0375 }
            int r5 = r5.getLimit()     // Catch:{ all -> 0x0375 }
            int r6 = r6 - r5
            r5 = 8
            if (r6 >= r5) goto L_0x038f
            goto L_0x0391
        L_0x038f:
            r5 = r3
            goto L_0x0398
        L_0x0391:
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r2, r3)     // Catch:{ all -> 0x0375 }
            io.ktor.utils.io.core.internal.ChunkBuffer r5 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadFirstHead(r2, r0)     // Catch:{ all -> 0x0375 }
        L_0x0398:
            if (r5 != 0) goto L_0x039c
            r6 = 0
            goto L_0x03a0
        L_0x039c:
            if (r0 > 0) goto L_0x03c2
            r3 = r5
            r6 = 1
        L_0x03a0:
            if (r6 == 0) goto L_0x03a5
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r2, r3)
        L_0x03a5:
            long r2 = r2.getRemaining()
            r6 = r30
            long r14 = r6 - r2
            int r0 = (int) r14
            java.lang.Integer r0 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r0)
            r13 = r17
            r13.invoke(r0)
            r3 = r28
            r7 = r29
            r0 = r12
            r6 = r13
            r2 = r16
            r5 = 1
            goto L_0x0086
        L_0x03c2:
            r3 = r28
            r7 = r29
            r14 = r30
            r13 = r2
            r4 = r5
            r2 = r16
            r6 = r17
            r5 = 1
            goto L_0x00d4
        L_0x03d1:
            r0 = move-exception
            r3 = r4
            r2 = r13
        L_0x03d4:
            r4 = 1
        L_0x03d5:
            if (r4 == 0) goto L_0x03da
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r2, r3)
        L_0x03da:
            throw r0
        L_0x03db:
            int r0 = r10.element
            r1 = 1
            if (r0 > r1) goto L_0x03f4
            boolean r0 = r9.element
            if (r0 == 0) goto L_0x03e6
            r8.element = r1
        L_0x03e6:
            int r0 = r11.element
            if (r0 > 0) goto L_0x03ee
            boolean r0 = r8.element
            if (r0 == 0) goto L_0x03ef
        L_0x03ee:
            r4 = 1
        L_0x03ef:
            java.lang.Boolean r0 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r4)
            return r0
        L_0x03f4:
            int r0 = r10.element
            prematureEndOfStreamUtf(r0)
            kotlin.KotlinNothingValueException r0 = new kotlin.KotlinNothingValueException
            r0.<init>()
            goto L_0x0400
        L_0x03ff:
            throw r0
        L_0x0400:
            goto L_0x03ff
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.core.internal.UTF8Kt.decodeUTF8LineLoopSuspend(java.lang.Appendable, int, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private static final Void prematureEndOfStreamUtf(int i) {
        throw new EOFException("Premature end of stream: expected " + i + " bytes to decode UTF-8 char");
    }

    /* renamed from: encodeUTF8-lBXzO7A  reason: not valid java name */
    public static final int m1762encodeUTF8lBXzO7A(ByteBuffer byteBuffer, CharSequence charSequence, int i, int i2, int i3, int i4) {
        Intrinsics.checkNotNullParameter(byteBuffer, "$this$encodeUTF8");
        Intrinsics.checkNotNullParameter(charSequence, "text");
        int min = Math.min(i2, i + 65535);
        int coerceAtMost = RangesKt.coerceAtMost(i4, 65535);
        int i5 = i;
        int i6 = i3;
        while (i6 < coerceAtMost && i5 < min) {
            int i7 = i5 + 1;
            char charAt = charSequence.charAt(i5);
            char c = charAt & CharCompanionObject.MAX_VALUE;
            if ((charAt & 65408) != 0) {
                return m1763encodeUTF8Stage1Vm9B2pQ(byteBuffer, charSequence, i5, min, i, i6, coerceAtMost, i3);
            }
            byteBuffer.put(i6, (byte) c);
            i5 = i7;
            i6++;
        }
        return EncodeResult.m1754constructorimpl(UShort.m2055constructorimpl((short) (i5 - i)), UShort.m2055constructorimpl((short) (i6 - i3)));
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v2, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v6, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v8, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v9, resolved type: char} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* renamed from: encodeUTF8Stage1-Vm9B2pQ  reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final int m1763encodeUTF8Stage1Vm9B2pQ(java.nio.ByteBuffer r11, java.lang.CharSequence r12, int r13, int r14, int r15, int r16, int r17, int r18) {
        /*
            r0 = r11
            r1 = r12
            r3 = r14
            int r2 = r17 + -3
            r4 = r13
            r5 = r16
        L_0x0008:
            int r6 = r2 - r5
            if (r6 <= 0) goto L_0x00c1
            if (r4 < r3) goto L_0x0010
            goto L_0x00c1
        L_0x0010:
            int r6 = r4 + 1
            char r7 = r12.charAt(r4)
            boolean r8 = java.lang.Character.isHighSurrogate(r7)
            r9 = 63
            if (r8 == 0) goto L_0x003a
            if (r6 == r3) goto L_0x0036
            char r8 = r12.charAt(r6)
            boolean r8 = java.lang.Character.isLowSurrogate(r8)
            if (r8 != 0) goto L_0x002b
            goto L_0x0036
        L_0x002b:
            int r4 = r4 + 2
            char r6 = r12.charAt(r6)
            int r7 = codePoint(r7, r6)
            goto L_0x003b
        L_0x0036:
            r4 = r6
            r7 = 63
            goto L_0x003b
        L_0x003a:
            r4 = r6
        L_0x003b:
            r6 = 128(0x80, float:1.794E-43)
            if (r7 < 0) goto L_0x0047
            if (r7 >= r6) goto L_0x0047
            byte r6 = (byte) r7
            r11.put(r5, r6)
            r6 = 1
            goto L_0x00b5
        L_0x0047:
            r8 = 2048(0x800, float:2.87E-42)
            if (r6 > r7) goto L_0x0062
            if (r7 >= r8) goto L_0x0062
            int r8 = r7 >> 6
            r8 = r8 & 31
            r8 = r8 | 192(0xc0, float:2.69E-43)
            byte r8 = (byte) r8
            r11.put(r5, r8)
            int r8 = r5 + 1
            r7 = r7 & 63
            r6 = r6 | r7
            byte r6 = (byte) r6
            r11.put(r8, r6)
            r6 = 2
            goto L_0x00b5
        L_0x0062:
            r10 = 65536(0x10000, float:9.18355E-41)
            if (r8 > r7) goto L_0x0087
            if (r7 >= r10) goto L_0x0087
            int r8 = r7 >> 12
            r8 = r8 & 15
            r8 = r8 | 224(0xe0, float:3.14E-43)
            byte r8 = (byte) r8
            r11.put(r5, r8)
            int r8 = r5 + 1
            int r10 = r7 >> 6
            r9 = r9 & r10
            r9 = r9 | r6
            byte r9 = (byte) r9
            r11.put(r8, r9)
            int r8 = r5 + 2
            r7 = r7 & 63
            r6 = r6 | r7
            byte r6 = (byte) r6
            r11.put(r8, r6)
            r6 = 3
            goto L_0x00b5
        L_0x0087:
            if (r10 > r7) goto L_0x00b8
            r8 = 1114112(0x110000, float:1.561203E-39)
            if (r7 >= r8) goto L_0x00b8
            int r8 = r7 >> 18
            r8 = r8 & 7
            r8 = r8 | 240(0xf0, float:3.36E-43)
            byte r8 = (byte) r8
            r11.put(r5, r8)
            int r8 = r5 + 1
            int r10 = r7 >> 12
            r10 = r10 & r9
            r10 = r10 | r6
            byte r10 = (byte) r10
            r11.put(r8, r10)
            int r8 = r5 + 2
            int r10 = r7 >> 6
            r9 = r9 & r10
            r9 = r9 | r6
            byte r9 = (byte) r9
            r11.put(r8, r9)
            int r8 = r5 + 3
            r7 = r7 & 63
            r6 = r6 | r7
            byte r6 = (byte) r6
            r11.put(r8, r6)
            r6 = 4
        L_0x00b5:
            int r5 = r5 + r6
            goto L_0x0008
        L_0x00b8:
            malformedCodePoint(r7)
            kotlin.KotlinNothingValueException r0 = new kotlin.KotlinNothingValueException
            r0.<init>()
            throw r0
        L_0x00c1:
            if (r5 != r2) goto L_0x00d1
            r0 = r11
            r1 = r12
            r2 = r4
            r3 = r14
            r4 = r15
            r6 = r17
            r7 = r18
            int r0 = m1764encodeUTF8Stage2Vm9B2pQ(r0, r1, r2, r3, r4, r5, r6, r7)
            return r0
        L_0x00d1:
            int r4 = r4 - r15
            short r0 = (short) r4
            short r0 = kotlin.UShort.m2055constructorimpl(r0)
            int r5 = r5 - r18
            short r1 = (short) r5
            short r1 = kotlin.UShort.m2055constructorimpl(r1)
            int r0 = io.ktor.utils.io.core.internal.EncodeResult.m1754constructorimpl(r0, r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.core.internal.UTF8Kt.m1763encodeUTF8Stage1Vm9B2pQ(java.nio.ByteBuffer, java.lang.CharSequence, int, int, int, int, int, int):int");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v1, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v2, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v4, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v5, resolved type: char} */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00eb, code lost:
        throw new kotlin.KotlinNothingValueException();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00fe, code lost:
        return io.ktor.utils.io.core.internal.EncodeResult.m1754constructorimpl(kotlin.UShort.m2055constructorimpl((short) (r3 - r20)), kotlin.UShort.m2055constructorimpl((short) (r4 - r23)));
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* renamed from: encodeUTF8Stage2-Vm9B2pQ  reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final int m1764encodeUTF8Stage2Vm9B2pQ(java.nio.ByteBuffer r16, java.lang.CharSequence r17, int r18, int r19, int r20, int r21, int r22, int r23) {
        /*
            r0 = r16
            r1 = r17
            r2 = r19
            r3 = r18
            r4 = r21
        L_0x000a:
            int r5 = r22 - r4
            if (r5 <= 0) goto L_0x00ec
            if (r3 < r2) goto L_0x0012
            goto L_0x00ec
        L_0x0012:
            int r6 = r3 + 1
            char r7 = r1.charAt(r3)
            boolean r8 = java.lang.Character.isHighSurrogate(r7)
            r9 = 63
            if (r8 != 0) goto L_0x0022
            r3 = r6
            goto L_0x003d
        L_0x0022:
            if (r6 == r2) goto L_0x003a
            char r8 = r1.charAt(r6)
            boolean r8 = java.lang.Character.isLowSurrogate(r8)
            if (r8 != 0) goto L_0x002f
            goto L_0x003a
        L_0x002f:
            int r3 = r3 + 2
            char r6 = r1.charAt(r6)
            int r7 = codePoint(r7, r6)
            goto L_0x003d
        L_0x003a:
            r3 = r6
            r7 = 63
        L_0x003d:
            r8 = 1114112(0x110000, float:1.561203E-39)
            r10 = 3
            r11 = 65536(0x10000, float:9.18355E-41)
            r12 = 2048(0x800, float:2.87E-42)
            r13 = 2
            r14 = 1
            r15 = 128(0x80, float:1.794E-43)
            if (r14 > r7) goto L_0x004e
            if (r7 >= r15) goto L_0x004e
            r6 = 1
            goto L_0x005f
        L_0x004e:
            if (r15 > r7) goto L_0x0054
            if (r7 >= r12) goto L_0x0054
            r6 = 2
            goto L_0x005f
        L_0x0054:
            if (r12 > r7) goto L_0x005a
            if (r7 >= r11) goto L_0x005a
            r6 = 3
            goto L_0x005f
        L_0x005a:
            if (r11 > r7) goto L_0x00e3
            if (r7 >= r8) goto L_0x00e3
            r6 = 4
        L_0x005f:
            if (r6 <= r5) goto L_0x0065
            int r3 = r3 + -1
            goto L_0x00ec
        L_0x0065:
            if (r7 < 0) goto L_0x006f
            if (r7 >= r15) goto L_0x006f
            byte r5 = (byte) r7
            r0.put(r4, r5)
            r6 = 1
            goto L_0x00d7
        L_0x006f:
            if (r15 > r7) goto L_0x0088
            if (r7 >= r12) goto L_0x0088
            int r5 = r7 >> 6
            r5 = r5 & 31
            r5 = r5 | 192(0xc0, float:2.69E-43)
            byte r5 = (byte) r5
            r0.put(r4, r5)
            int r5 = r4 + 1
            r6 = r7 & 63
            r6 = r6 | r15
            byte r6 = (byte) r6
            r0.put(r5, r6)
            r6 = 2
            goto L_0x00d7
        L_0x0088:
            if (r12 > r7) goto L_0x00ab
            if (r7 >= r11) goto L_0x00ab
            int r5 = r7 >> 12
            r5 = r5 & 15
            r5 = r5 | 224(0xe0, float:3.14E-43)
            byte r5 = (byte) r5
            r0.put(r4, r5)
            int r5 = r4 + 1
            int r6 = r7 >> 6
            r6 = r6 & r9
            r6 = r6 | r15
            byte r6 = (byte) r6
            r0.put(r5, r6)
            int r5 = r4 + 2
            r6 = r7 & 63
            r6 = r6 | r15
            byte r6 = (byte) r6
            r0.put(r5, r6)
            r6 = 3
            goto L_0x00d7
        L_0x00ab:
            if (r11 > r7) goto L_0x00da
            if (r7 >= r8) goto L_0x00da
            int r5 = r7 >> 18
            r5 = r5 & 7
            r5 = r5 | 240(0xf0, float:3.36E-43)
            byte r5 = (byte) r5
            r0.put(r4, r5)
            int r5 = r4 + 1
            int r6 = r7 >> 12
            r6 = r6 & r9
            r6 = r6 | r15
            byte r6 = (byte) r6
            r0.put(r5, r6)
            int r5 = r4 + 2
            int r6 = r7 >> 6
            r6 = r6 & r9
            r6 = r6 | r15
            byte r6 = (byte) r6
            r0.put(r5, r6)
            int r5 = r4 + 3
            r6 = r7 & 63
            r6 = r6 | r15
            byte r6 = (byte) r6
            r0.put(r5, r6)
            r6 = 4
        L_0x00d7:
            int r4 = r4 + r6
            goto L_0x000a
        L_0x00da:
            malformedCodePoint(r7)
            kotlin.KotlinNothingValueException r0 = new kotlin.KotlinNothingValueException
            r0.<init>()
            throw r0
        L_0x00e3:
            malformedCodePoint(r7)
            kotlin.KotlinNothingValueException r0 = new kotlin.KotlinNothingValueException
            r0.<init>()
            throw r0
        L_0x00ec:
            int r3 = r3 - r20
            short r0 = (short) r3
            short r0 = kotlin.UShort.m2055constructorimpl(r0)
            int r4 = r4 - r23
            short r1 = (short) r4
            short r1 = kotlin.UShort.m2055constructorimpl(r1)
            int r0 = io.ktor.utils.io.core.internal.EncodeResult.m1754constructorimpl(r0, r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.core.internal.UTF8Kt.m1764encodeUTF8Stage2Vm9B2pQ(java.nio.ByteBuffer, java.lang.CharSequence, int, int, int, int, int, int):int");
    }

    private static final int charactersSize(int i) {
        if (1 <= i && i < 128) {
            return 1;
        }
        if (128 <= i && i < 2048) {
            return 2;
        }
        if (2048 <= i && i < 65536) {
            return 3;
        }
        if (65536 <= i && i < 1114112) {
            return 4;
        }
        malformedCodePoint(i);
        throw new KotlinNothingValueException();
    }

    public static final Void malformedByteCount(int i) {
        throw new MalformedUTF8InputException("Expected " + i + " more character bytes");
    }

    public static final Void malformedCodePoint(int i) {
        throw new IllegalArgumentException("Malformed code-point " + i + " found");
    }

    public static final boolean decodeASCII(Buffer buffer, Function1<? super Character, Boolean> function1) {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        Intrinsics.checkNotNullParameter(function1, "consumer");
        ByteBuffer r0 = buffer.m155getMemorySK3TCg8();
        int readPosition = buffer.getReadPosition();
        int writePosition = buffer.getWritePosition();
        for (int i = readPosition; i < writePosition; i++) {
            byte b = r0.get(i);
            byte b2 = b & 255;
            if ((b & 128) == 128 || !function1.invoke(Character.valueOf((char) b2)).booleanValue()) {
                buffer.discardExact(i - readPosition);
                return false;
            }
        }
        buffer.discardExact(writePosition - readPosition);
        return true;
    }

    public static final int decodeUTF8(Buffer buffer, Function1<? super Character, Boolean> function1) {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        Intrinsics.checkNotNullParameter(function1, "consumer");
        ByteBuffer r0 = buffer.m155getMemorySK3TCg8();
        int readPosition = buffer.getReadPosition();
        int writePosition = buffer.getWritePosition();
        int i = 0;
        byte b = 0;
        int i2 = 0;
        for (int i3 = readPosition; i3 < writePosition; i3++) {
            byte b2 = r0.get(i3);
            byte b3 = b2 & 255;
            if ((b2 & 128) != 0) {
                if (i == 0) {
                    b = b3;
                    int i4 = 128;
                    for (int i5 = 1; i5 < 7 && (b & i4) != 0; i5++) {
                        b &= i4 ^ -1;
                        i4 >>= 1;
                        i++;
                    }
                    int i6 = i - 1;
                    if (i > writePosition - i3) {
                        buffer.discardExact(i3 - readPosition);
                        return i;
                    }
                    int i7 = i6;
                    i2 = i;
                    i = i7;
                } else {
                    b = (b << 6) | (b2 & Byte.MAX_VALUE);
                    i--;
                    if (i != 0) {
                        continue;
                    } else {
                        if (isBmpCodePoint(b)) {
                            if (!function1.invoke(Character.valueOf((char) b)).booleanValue()) {
                                buffer.discardExact(((i3 - readPosition) - i2) + 1);
                                return -1;
                            }
                        } else if (!isValidCodePoint(b)) {
                            malformedCodePoint(b);
                            throw new KotlinNothingValueException();
                        } else if (!function1.invoke(Character.valueOf((char) highSurrogate(b))).booleanValue() || !function1.invoke(Character.valueOf((char) lowSurrogate(b))).booleanValue()) {
                            buffer.discardExact(((i3 - readPosition) - i2) + 1);
                            return -1;
                        }
                        b = 0;
                    }
                }
            } else if (i != 0) {
                malformedByteCount(i);
                throw new KotlinNothingValueException();
            } else if (!function1.invoke(Character.valueOf((char) b3)).booleanValue()) {
                buffer.discardExact(i3 - readPosition);
                return -1;
            }
        }
        buffer.discardExact(writePosition - readPosition);
        return 0;
    }

    /* renamed from: putUtf8Char-62zg_DM  reason: not valid java name */
    public static final int m1765putUtf8Char62zg_DM(ByteBuffer byteBuffer, int i, int i2) {
        Intrinsics.checkNotNullParameter(byteBuffer, "$this$putUtf8Char");
        if (i2 >= 0 && i2 < 128) {
            byteBuffer.put(i, (byte) i2);
            return 1;
        } else if (128 <= i2 && i2 < 2048) {
            byteBuffer.put(i, (byte) (((i2 >> 6) & 31) | 192));
            byteBuffer.put(i + 1, (byte) ((i2 & 63) | 128));
            return 2;
        } else if (2048 <= i2 && i2 < 65536) {
            byteBuffer.put(i, (byte) (((i2 >> 12) & 15) | BERTags.FLAGS));
            byteBuffer.put(i + 1, (byte) (((i2 >> 6) & 63) | 128));
            byteBuffer.put(i + 2, (byte) ((i2 & 63) | 128));
            return 3;
        } else if (65536 > i2 || i2 >= 1114112) {
            malformedCodePoint(i2);
            throw new KotlinNothingValueException();
        } else {
            byteBuffer.put(i, (byte) (((i2 >> 18) & 7) | 240));
            byteBuffer.put(i + 1, (byte) (((i2 >> 12) & 63) | 128));
            byteBuffer.put(i + 2, (byte) (((i2 >> 6) & 63) | 128));
            byteBuffer.put(i + 3, (byte) ((i2 & 63) | 128));
            return 4;
        }
    }
}
