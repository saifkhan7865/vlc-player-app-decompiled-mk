package io.ktor.utils.io.core;

import io.netty.handler.codec.rtsp.RtspHeaders;
import java.nio.ByteBuffer;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000X\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0013\n\u0002\u0010\u0014\n\u0002\u0010\u0015\n\u0002\u0010\u0016\n\u0002\u0010\u0017\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a/\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0001ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0007\u0010\b\u001a/\u0010\u0000\u001a\u00020\t*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\tø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0007\u0010\n\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\u0006\u001a\u00020\u0001\u001a&\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u0001\u001a&\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u000f2\b\b\u0002\u0010\u000e\u001a\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u0001\u001a&\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u00102\b\b\u0002\u0010\u000e\u001a\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u0001\u001a&\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u00112\b\b\u0002\u0010\u000e\u001a\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u0001\u001a&\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u00122\b\b\u0002\u0010\u000e\u001a\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u0001\u001a&\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u00132\b\b\u0002\u0010\u000e\u001a\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u0001\u001a/\u0010\u0014\u001a\u00020\u0015*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0001ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0016\u0010\u0017\u001a/\u0010\u0014\u001a\u00020\u0015*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\tø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0016\u0010\u0018\u001a\u001c\u0010\u0014\u001a\u00020\u0015*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\u0006\u001a\u00020\u0001\u001a&\u0010\u0014\u001a\u00020\u0015*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u0001\u001a&\u0010\u0014\u001a\u00020\u0015*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u000f2\b\b\u0002\u0010\u000e\u001a\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u0001\u001a&\u0010\u0014\u001a\u00020\u0015*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u00102\b\b\u0002\u0010\u000e\u001a\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u0001\u001a&\u0010\u0014\u001a\u00020\u0015*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u00112\b\b\u0002\u0010\u000e\u001a\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u0001\u001a&\u0010\u0014\u001a\u00020\u0015*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u00122\b\b\u0002\u0010\u000e\u001a\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u0001\u001a&\u0010\u0014\u001a\u00020\u0015*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u00132\b\b\u0002\u0010\u000e\u001a\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u0001\u001aj\u0010\u0019\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00012K\u0010\u001b\u001aG\u0012\u0013\u0012\u00110\f¢\u0006\f\b\u001d\u0012\b\b\u001e\u0012\u0004\b\b(\u001f\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u001d\u0012\b\b\u001e\u0012\u0004\b\b( \u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u001d\u0012\b\b\u001e\u0012\u0004\b\b(!\u0012\u0004\u0012\u00020\u00150\u001cH\b\u001a\u0001\u0010\u0019\u001a\u00020\t*\u00020\u00022\u0006\u0010\u001a\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\t2`\u0010\u001b\u001a\\\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u001d\u0012\b\b\u001e\u0012\u0004\b\b(\u001f\u0012\u0013\u0012\u00110\t¢\u0006\f\b\u001d\u0012\b\b\u001e\u0012\u0004\b\b(#\u0012\u0013\u0012\u00110\t¢\u0006\f\b\u001d\u0012\b\b\u001e\u0012\u0004\b\b( \u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u001d\u0012\b\b\u001e\u0012\u0004\b\b(!\u0012\u0004\u0012\u00020\u00150\"H\bø\u0001\u0001\u001ar\u0010$\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00012\u0006\u0010%\u001a\u00020\u00012K\u0010\u001b\u001aG\u0012\u0013\u0012\u00110\f¢\u0006\f\b\u001d\u0012\b\b\u001e\u0012\u0004\b\b(\u001f\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u001d\u0012\b\b\u001e\u0012\u0004\b\b( \u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u001d\u0012\b\b\u001e\u0012\u0004\b\b(!\u0012\u0004\u0012\u00020\u00150\u001cH\b\u001a\r\u0010&\u001a\u00020\u0015*\u00020\u0001H\b\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006'"}, d2 = {"readAvailable", "", "Lio/ktor/utils/io/core/Input;", "destination", "Lio/ktor/utils/io/bits/Memory;", "destinationOffset", "length", "readAvailable-UAd2zVI", "(Lio/ktor/utils/io/core/Input;Ljava/nio/ByteBuffer;II)I", "", "(Lio/ktor/utils/io/core/Input;Ljava/nio/ByteBuffer;JJ)J", "dst", "Lio/ktor/utils/io/core/Buffer;", "", "offset", "", "", "", "", "", "readFully", "", "readFully-UAd2zVI", "(Lio/ktor/utils/io/core/Input;Ljava/nio/ByteBuffer;II)V", "(Lio/ktor/utils/io/core/Input;Ljava/nio/ByteBuffer;JJ)V", "readFullyBytesTemplate", "initialDstOffset", "readBlock", "Lkotlin/Function3;", "Lkotlin/ParameterName;", "name", "src", "dstOffset", "count", "Lkotlin/Function4;", "srcOffset", "readFullyTemplate", "componentSize", "requireNoRemaining", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: InputArrays.kt */
public final class InputArraysKt {
    public static /* synthetic */ void readFully$default(Input input, byte[] bArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = bArr.length - i;
        }
        readFully(input, bArr, i, i2);
    }

    public static /* synthetic */ void readFully$default(Input input, short[] sArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = sArr.length - i;
        }
        readFully(input, sArr, i, i2);
    }

    public static /* synthetic */ void readFully$default(Input input, int[] iArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = iArr.length - i;
        }
        readFully(input, iArr, i, i2);
    }

    public static /* synthetic */ void readFully$default(Input input, long[] jArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = jArr.length - i;
        }
        readFully(input, jArr, i, i2);
    }

    public static /* synthetic */ void readFully$default(Input input, float[] fArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = fArr.length - i;
        }
        readFully(input, fArr, i, i2);
    }

    public static /* synthetic */ void readFully$default(Input input, double[] dArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = dArr.length - i;
        }
        readFully(input, dArr, i, i2);
    }

    /* renamed from: readFully-UAd2zVI  reason: not valid java name */
    public static final void m1690readFullyUAd2zVI(Input input, ByteBuffer byteBuffer, int i, int i2) {
        Intrinsics.checkNotNullParameter(input, "$this$readFully");
        Intrinsics.checkNotNullParameter(byteBuffer, RtspHeaders.Values.DESTINATION);
        m1691readFullyUAd2zVI(input, byteBuffer, (long) i, (long) i2);
    }

    /* renamed from: readFully-UAd2zVI  reason: not valid java name */
    public static final void m1691readFullyUAd2zVI(Input input, ByteBuffer byteBuffer, long j, long j2) {
        Intrinsics.checkNotNullParameter(input, "$this$readFully");
        Intrinsics.checkNotNullParameter(byteBuffer, RtspHeaders.Values.DESTINATION);
        if (m1689readAvailableUAd2zVI(input, byteBuffer, j, j2) != j2) {
            StringsKt.prematureEndOfStream(j2);
            throw new KotlinNothingValueException();
        }
    }

    public static /* synthetic */ int readAvailable$default(Input input, byte[] bArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = bArr.length - i;
        }
        return readAvailable(input, bArr, i, i2);
    }

    public static /* synthetic */ int readAvailable$default(Input input, short[] sArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = sArr.length - i;
        }
        return readAvailable(input, sArr, i, i2);
    }

    public static /* synthetic */ int readAvailable$default(Input input, int[] iArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = iArr.length - i;
        }
        return readAvailable(input, iArr, i, i2);
    }

    public static /* synthetic */ int readAvailable$default(Input input, long[] jArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = jArr.length - i;
        }
        return readAvailable(input, jArr, i, i2);
    }

    public static /* synthetic */ int readAvailable$default(Input input, float[] fArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = fArr.length - i;
        }
        return readAvailable(input, fArr, i, i2);
    }

    public static /* synthetic */ int readAvailable$default(Input input, double[] dArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = dArr.length - i;
        }
        return readAvailable(input, dArr, i, i2);
    }

    /* renamed from: readAvailable-UAd2zVI  reason: not valid java name */
    public static final int m1688readAvailableUAd2zVI(Input input, ByteBuffer byteBuffer, int i, int i2) {
        Intrinsics.checkNotNullParameter(input, "$this$readAvailable");
        Intrinsics.checkNotNullParameter(byteBuffer, RtspHeaders.Values.DESTINATION);
        return (int) m1689readAvailableUAd2zVI(input, byteBuffer, (long) i, (long) i2);
    }

    private static final void requireNoRemaining(int i) {
        if (i > 0) {
            StringsKt.prematureEndOfStream(i);
            throw new KotlinNothingValueException();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0045  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void readFully(io.ktor.utils.io.core.Input r5, byte[] r6, int r7, int r8) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            java.lang.String r0 = "dst"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            r0 = 1
            io.ktor.utils.io.core.internal.ChunkBuffer r1 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadFirstHead(r5, r0)
            if (r1 != 0) goto L_0x0012
            goto L_0x0036
        L_0x0012:
            r2 = r1
            io.ktor.utils.io.core.Buffer r2 = (io.ktor.utils.io.core.Buffer) r2     // Catch:{ all -> 0x0042 }
            int r3 = r2.getWritePosition()     // Catch:{ all -> 0x0042 }
            int r4 = r2.getReadPosition()     // Catch:{ all -> 0x0042 }
            int r3 = r3 - r4
            int r3 = java.lang.Math.min(r8, r3)     // Catch:{ all -> 0x0042 }
            io.ktor.utils.io.core.BufferPrimitivesKt.readFully((io.ktor.utils.io.core.Buffer) r2, (byte[]) r6, (int) r7, (int) r3)     // Catch:{ all -> 0x0042 }
            int r8 = r8 - r3
            int r7 = r7 + r3
            if (r8 <= 0) goto L_0x0033
            io.ktor.utils.io.core.internal.ChunkBuffer r1 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadNextHead(r5, r1)     // Catch:{ all -> 0x0030 }
            if (r1 != 0) goto L_0x0012
            goto L_0x0036
        L_0x0030:
            r6 = move-exception
            r0 = 0
            goto L_0x0043
        L_0x0033:
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r5, r1)
        L_0x0036:
            if (r8 > 0) goto L_0x0039
            return
        L_0x0039:
            io.ktor.utils.io.core.StringsKt.prematureEndOfStream((int) r8)
            kotlin.KotlinNothingValueException r5 = new kotlin.KotlinNothingValueException
            r5.<init>()
            throw r5
        L_0x0042:
            r6 = move-exception
        L_0x0043:
            if (r0 == 0) goto L_0x0048
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r5, r1)
        L_0x0048:
            goto L_0x004a
        L_0x0049:
            throw r6
        L_0x004a:
            goto L_0x0049
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.core.InputArraysKt.readFully(io.ktor.utils.io.core.Input, byte[], int, int):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:44:0x0099  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void readFully(io.ktor.utils.io.core.Input r6, short[] r7, int r8, int r9) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            java.lang.String r0 = "dst"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            r0 = 1
            io.ktor.utils.io.core.internal.ChunkBuffer r1 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadFirstHead(r6, r0)
            if (r1 != 0) goto L_0x0013
            goto L_0x008a
        L_0x0013:
            r2 = 1
        L_0x0014:
            r3 = r1
            io.ktor.utils.io.core.Buffer r3 = (io.ktor.utils.io.core.Buffer) r3     // Catch:{ all -> 0x0096 }
            int r4 = r3.getWritePosition()     // Catch:{ all -> 0x0096 }
            int r3 = r3.getReadPosition()     // Catch:{ all -> 0x0096 }
            int r4 = r4 - r3
            r3 = 0
            if (r4 < r2) goto L_0x0058
            r2 = r1
            io.ktor.utils.io.core.Buffer r2 = (io.ktor.utils.io.core.Buffer) r2     // Catch:{ all -> 0x004d }
            int r4 = r2.getWritePosition()     // Catch:{ all -> 0x004d }
            int r5 = r2.getReadPosition()     // Catch:{ all -> 0x004d }
            int r4 = r4 - r5
            r5 = 2
            int r4 = r4 / r5
            int r4 = java.lang.Math.min(r9, r4)     // Catch:{ all -> 0x004d }
            io.ktor.utils.io.core.BufferPrimitivesKt.readFully((io.ktor.utils.io.core.Buffer) r2, (short[]) r7, (int) r8, (int) r4)     // Catch:{ all -> 0x004d }
            int r9 = r9 - r4
            int r8 = r8 + r4
            if (r9 <= 0) goto L_0x003e
            r2 = 2
            goto L_0x003f
        L_0x003e:
            r2 = 0
        L_0x003f:
            r4 = r1
            io.ktor.utils.io.core.Buffer r4 = (io.ktor.utils.io.core.Buffer) r4     // Catch:{ all -> 0x0096 }
            int r5 = r4.getWritePosition()     // Catch:{ all -> 0x0096 }
            int r4 = r4.getReadPosition()     // Catch:{ all -> 0x0096 }
            int r4 = r5 - r4
            goto L_0x0058
        L_0x004d:
            r7 = move-exception
            r8 = r1
            io.ktor.utils.io.core.Buffer r8 = (io.ktor.utils.io.core.Buffer) r8     // Catch:{ all -> 0x0096 }
            r8.getWritePosition()     // Catch:{ all -> 0x0096 }
            r8.getReadPosition()     // Catch:{ all -> 0x0096 }
            throw r7     // Catch:{ all -> 0x0096 }
        L_0x0058:
            if (r4 != 0) goto L_0x0062
            io.ktor.utils.io.core.internal.ChunkBuffer r4 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadNextHead(r6, r1)     // Catch:{ all -> 0x005f }
            goto L_0x007e
        L_0x005f:
            r7 = move-exception
            r0 = 0
            goto L_0x0097
        L_0x0062:
            if (r4 < r2) goto L_0x0077
            r4 = r1
            io.ktor.utils.io.core.Buffer r4 = (io.ktor.utils.io.core.Buffer) r4     // Catch:{ all -> 0x005f }
            int r5 = r4.getCapacity()     // Catch:{ all -> 0x005f }
            int r4 = r4.getLimit()     // Catch:{ all -> 0x005f }
            int r5 = r5 - r4
            r4 = 8
            if (r5 >= r4) goto L_0x0075
            goto L_0x0077
        L_0x0075:
            r4 = r1
            goto L_0x007e
        L_0x0077:
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r6, r1)     // Catch:{ all -> 0x005f }
            io.ktor.utils.io.core.internal.ChunkBuffer r4 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadFirstHead(r6, r2)     // Catch:{ all -> 0x005f }
        L_0x007e:
            if (r4 != 0) goto L_0x0082
            r0 = 0
            goto L_0x0085
        L_0x0082:
            r1 = r4
            if (r2 > 0) goto L_0x0014
        L_0x0085:
            if (r0 == 0) goto L_0x008a
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r6, r1)
        L_0x008a:
            if (r9 > 0) goto L_0x008d
            return
        L_0x008d:
            io.ktor.utils.io.core.StringsKt.prematureEndOfStream((int) r9)
            kotlin.KotlinNothingValueException r6 = new kotlin.KotlinNothingValueException
            r6.<init>()
            throw r6
        L_0x0096:
            r7 = move-exception
        L_0x0097:
            if (r0 == 0) goto L_0x009c
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r6, r1)
        L_0x009c:
            goto L_0x009e
        L_0x009d:
            throw r7
        L_0x009e:
            goto L_0x009d
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.core.InputArraysKt.readFully(io.ktor.utils.io.core.Input, short[], int, int):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:44:0x0099  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void readFully(io.ktor.utils.io.core.Input r6, int[] r7, int r8, int r9) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            java.lang.String r0 = "dst"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            r0 = 1
            io.ktor.utils.io.core.internal.ChunkBuffer r1 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadFirstHead(r6, r0)
            if (r1 != 0) goto L_0x0013
            goto L_0x008a
        L_0x0013:
            r2 = 1
        L_0x0014:
            r3 = r1
            io.ktor.utils.io.core.Buffer r3 = (io.ktor.utils.io.core.Buffer) r3     // Catch:{ all -> 0x0096 }
            int r4 = r3.getWritePosition()     // Catch:{ all -> 0x0096 }
            int r3 = r3.getReadPosition()     // Catch:{ all -> 0x0096 }
            int r4 = r4 - r3
            r3 = 0
            if (r4 < r2) goto L_0x0058
            r2 = r1
            io.ktor.utils.io.core.Buffer r2 = (io.ktor.utils.io.core.Buffer) r2     // Catch:{ all -> 0x004d }
            int r4 = r2.getWritePosition()     // Catch:{ all -> 0x004d }
            int r5 = r2.getReadPosition()     // Catch:{ all -> 0x004d }
            int r4 = r4 - r5
            r5 = 4
            int r4 = r4 / r5
            int r4 = java.lang.Math.min(r9, r4)     // Catch:{ all -> 0x004d }
            io.ktor.utils.io.core.BufferPrimitivesKt.readFully((io.ktor.utils.io.core.Buffer) r2, (int[]) r7, (int) r8, (int) r4)     // Catch:{ all -> 0x004d }
            int r9 = r9 - r4
            int r8 = r8 + r4
            if (r9 <= 0) goto L_0x003e
            r2 = 4
            goto L_0x003f
        L_0x003e:
            r2 = 0
        L_0x003f:
            r4 = r1
            io.ktor.utils.io.core.Buffer r4 = (io.ktor.utils.io.core.Buffer) r4     // Catch:{ all -> 0x0096 }
            int r5 = r4.getWritePosition()     // Catch:{ all -> 0x0096 }
            int r4 = r4.getReadPosition()     // Catch:{ all -> 0x0096 }
            int r4 = r5 - r4
            goto L_0x0058
        L_0x004d:
            r7 = move-exception
            r8 = r1
            io.ktor.utils.io.core.Buffer r8 = (io.ktor.utils.io.core.Buffer) r8     // Catch:{ all -> 0x0096 }
            r8.getWritePosition()     // Catch:{ all -> 0x0096 }
            r8.getReadPosition()     // Catch:{ all -> 0x0096 }
            throw r7     // Catch:{ all -> 0x0096 }
        L_0x0058:
            if (r4 != 0) goto L_0x0062
            io.ktor.utils.io.core.internal.ChunkBuffer r4 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadNextHead(r6, r1)     // Catch:{ all -> 0x005f }
            goto L_0x007e
        L_0x005f:
            r7 = move-exception
            r0 = 0
            goto L_0x0097
        L_0x0062:
            if (r4 < r2) goto L_0x0077
            r4 = r1
            io.ktor.utils.io.core.Buffer r4 = (io.ktor.utils.io.core.Buffer) r4     // Catch:{ all -> 0x005f }
            int r5 = r4.getCapacity()     // Catch:{ all -> 0x005f }
            int r4 = r4.getLimit()     // Catch:{ all -> 0x005f }
            int r5 = r5 - r4
            r4 = 8
            if (r5 >= r4) goto L_0x0075
            goto L_0x0077
        L_0x0075:
            r4 = r1
            goto L_0x007e
        L_0x0077:
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r6, r1)     // Catch:{ all -> 0x005f }
            io.ktor.utils.io.core.internal.ChunkBuffer r4 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadFirstHead(r6, r2)     // Catch:{ all -> 0x005f }
        L_0x007e:
            if (r4 != 0) goto L_0x0082
            r0 = 0
            goto L_0x0085
        L_0x0082:
            r1 = r4
            if (r2 > 0) goto L_0x0014
        L_0x0085:
            if (r0 == 0) goto L_0x008a
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r6, r1)
        L_0x008a:
            if (r9 > 0) goto L_0x008d
            return
        L_0x008d:
            io.ktor.utils.io.core.StringsKt.prematureEndOfStream((int) r9)
            kotlin.KotlinNothingValueException r6 = new kotlin.KotlinNothingValueException
            r6.<init>()
            throw r6
        L_0x0096:
            r7 = move-exception
        L_0x0097:
            if (r0 == 0) goto L_0x009c
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r6, r1)
        L_0x009c:
            goto L_0x009e
        L_0x009d:
            throw r7
        L_0x009e:
            goto L_0x009d
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.core.InputArraysKt.readFully(io.ktor.utils.io.core.Input, int[], int, int):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:44:0x0099  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void readFully(io.ktor.utils.io.core.Input r7, long[] r8, int r9, int r10) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            java.lang.String r0 = "dst"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)
            r0 = 1
            io.ktor.utils.io.core.internal.ChunkBuffer r1 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadFirstHead(r7, r0)
            if (r1 != 0) goto L_0x0013
            goto L_0x008a
        L_0x0013:
            r2 = 1
        L_0x0014:
            r3 = r1
            io.ktor.utils.io.core.Buffer r3 = (io.ktor.utils.io.core.Buffer) r3     // Catch:{ all -> 0x0096 }
            int r4 = r3.getWritePosition()     // Catch:{ all -> 0x0096 }
            int r3 = r3.getReadPosition()     // Catch:{ all -> 0x0096 }
            int r4 = r4 - r3
            r3 = 0
            r5 = 8
            if (r4 < r2) goto L_0x005a
            r2 = r1
            io.ktor.utils.io.core.Buffer r2 = (io.ktor.utils.io.core.Buffer) r2     // Catch:{ all -> 0x004f }
            int r4 = r2.getWritePosition()     // Catch:{ all -> 0x004f }
            int r6 = r2.getReadPosition()     // Catch:{ all -> 0x004f }
            int r4 = r4 - r6
            int r4 = r4 / r5
            int r4 = java.lang.Math.min(r10, r4)     // Catch:{ all -> 0x004f }
            io.ktor.utils.io.core.BufferPrimitivesKt.readFully((io.ktor.utils.io.core.Buffer) r2, (long[]) r8, (int) r9, (int) r4)     // Catch:{ all -> 0x004f }
            int r10 = r10 - r4
            int r9 = r9 + r4
            if (r10 <= 0) goto L_0x0040
            r2 = 8
            goto L_0x0041
        L_0x0040:
            r2 = 0
        L_0x0041:
            r4 = r1
            io.ktor.utils.io.core.Buffer r4 = (io.ktor.utils.io.core.Buffer) r4     // Catch:{ all -> 0x0096 }
            int r6 = r4.getWritePosition()     // Catch:{ all -> 0x0096 }
            int r4 = r4.getReadPosition()     // Catch:{ all -> 0x0096 }
            int r4 = r6 - r4
            goto L_0x005a
        L_0x004f:
            r8 = move-exception
            r9 = r1
            io.ktor.utils.io.core.Buffer r9 = (io.ktor.utils.io.core.Buffer) r9     // Catch:{ all -> 0x0096 }
            r9.getWritePosition()     // Catch:{ all -> 0x0096 }
            r9.getReadPosition()     // Catch:{ all -> 0x0096 }
            throw r8     // Catch:{ all -> 0x0096 }
        L_0x005a:
            if (r4 != 0) goto L_0x0064
            io.ktor.utils.io.core.internal.ChunkBuffer r4 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadNextHead(r7, r1)     // Catch:{ all -> 0x0061 }
            goto L_0x007e
        L_0x0061:
            r8 = move-exception
            r0 = 0
            goto L_0x0097
        L_0x0064:
            if (r4 < r2) goto L_0x0077
            r4 = r1
            io.ktor.utils.io.core.Buffer r4 = (io.ktor.utils.io.core.Buffer) r4     // Catch:{ all -> 0x0061 }
            int r6 = r4.getCapacity()     // Catch:{ all -> 0x0061 }
            int r4 = r4.getLimit()     // Catch:{ all -> 0x0061 }
            int r6 = r6 - r4
            if (r6 >= r5) goto L_0x0075
            goto L_0x0077
        L_0x0075:
            r4 = r1
            goto L_0x007e
        L_0x0077:
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r7, r1)     // Catch:{ all -> 0x0061 }
            io.ktor.utils.io.core.internal.ChunkBuffer r4 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadFirstHead(r7, r2)     // Catch:{ all -> 0x0061 }
        L_0x007e:
            if (r4 != 0) goto L_0x0082
            r0 = 0
            goto L_0x0085
        L_0x0082:
            r1 = r4
            if (r2 > 0) goto L_0x0014
        L_0x0085:
            if (r0 == 0) goto L_0x008a
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r7, r1)
        L_0x008a:
            if (r10 > 0) goto L_0x008d
            return
        L_0x008d:
            io.ktor.utils.io.core.StringsKt.prematureEndOfStream((int) r10)
            kotlin.KotlinNothingValueException r7 = new kotlin.KotlinNothingValueException
            r7.<init>()
            throw r7
        L_0x0096:
            r8 = move-exception
        L_0x0097:
            if (r0 == 0) goto L_0x009c
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r7, r1)
        L_0x009c:
            goto L_0x009e
        L_0x009d:
            throw r8
        L_0x009e:
            goto L_0x009d
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.core.InputArraysKt.readFully(io.ktor.utils.io.core.Input, long[], int, int):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:44:0x0099  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void readFully(io.ktor.utils.io.core.Input r6, float[] r7, int r8, int r9) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            java.lang.String r0 = "dst"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            r0 = 1
            io.ktor.utils.io.core.internal.ChunkBuffer r1 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadFirstHead(r6, r0)
            if (r1 != 0) goto L_0x0013
            goto L_0x008a
        L_0x0013:
            r2 = 1
        L_0x0014:
            r3 = r1
            io.ktor.utils.io.core.Buffer r3 = (io.ktor.utils.io.core.Buffer) r3     // Catch:{ all -> 0x0096 }
            int r4 = r3.getWritePosition()     // Catch:{ all -> 0x0096 }
            int r3 = r3.getReadPosition()     // Catch:{ all -> 0x0096 }
            int r4 = r4 - r3
            r3 = 0
            if (r4 < r2) goto L_0x0058
            r2 = r1
            io.ktor.utils.io.core.Buffer r2 = (io.ktor.utils.io.core.Buffer) r2     // Catch:{ all -> 0x004d }
            int r4 = r2.getWritePosition()     // Catch:{ all -> 0x004d }
            int r5 = r2.getReadPosition()     // Catch:{ all -> 0x004d }
            int r4 = r4 - r5
            r5 = 4
            int r4 = r4 / r5
            int r4 = java.lang.Math.min(r9, r4)     // Catch:{ all -> 0x004d }
            io.ktor.utils.io.core.BufferPrimitivesKt.readFully((io.ktor.utils.io.core.Buffer) r2, (float[]) r7, (int) r8, (int) r4)     // Catch:{ all -> 0x004d }
            int r9 = r9 - r4
            int r8 = r8 + r4
            if (r9 <= 0) goto L_0x003e
            r2 = 4
            goto L_0x003f
        L_0x003e:
            r2 = 0
        L_0x003f:
            r4 = r1
            io.ktor.utils.io.core.Buffer r4 = (io.ktor.utils.io.core.Buffer) r4     // Catch:{ all -> 0x0096 }
            int r5 = r4.getWritePosition()     // Catch:{ all -> 0x0096 }
            int r4 = r4.getReadPosition()     // Catch:{ all -> 0x0096 }
            int r4 = r5 - r4
            goto L_0x0058
        L_0x004d:
            r7 = move-exception
            r8 = r1
            io.ktor.utils.io.core.Buffer r8 = (io.ktor.utils.io.core.Buffer) r8     // Catch:{ all -> 0x0096 }
            r8.getWritePosition()     // Catch:{ all -> 0x0096 }
            r8.getReadPosition()     // Catch:{ all -> 0x0096 }
            throw r7     // Catch:{ all -> 0x0096 }
        L_0x0058:
            if (r4 != 0) goto L_0x0062
            io.ktor.utils.io.core.internal.ChunkBuffer r4 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadNextHead(r6, r1)     // Catch:{ all -> 0x005f }
            goto L_0x007e
        L_0x005f:
            r7 = move-exception
            r0 = 0
            goto L_0x0097
        L_0x0062:
            if (r4 < r2) goto L_0x0077
            r4 = r1
            io.ktor.utils.io.core.Buffer r4 = (io.ktor.utils.io.core.Buffer) r4     // Catch:{ all -> 0x005f }
            int r5 = r4.getCapacity()     // Catch:{ all -> 0x005f }
            int r4 = r4.getLimit()     // Catch:{ all -> 0x005f }
            int r5 = r5 - r4
            r4 = 8
            if (r5 >= r4) goto L_0x0075
            goto L_0x0077
        L_0x0075:
            r4 = r1
            goto L_0x007e
        L_0x0077:
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r6, r1)     // Catch:{ all -> 0x005f }
            io.ktor.utils.io.core.internal.ChunkBuffer r4 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadFirstHead(r6, r2)     // Catch:{ all -> 0x005f }
        L_0x007e:
            if (r4 != 0) goto L_0x0082
            r0 = 0
            goto L_0x0085
        L_0x0082:
            r1 = r4
            if (r2 > 0) goto L_0x0014
        L_0x0085:
            if (r0 == 0) goto L_0x008a
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r6, r1)
        L_0x008a:
            if (r9 > 0) goto L_0x008d
            return
        L_0x008d:
            io.ktor.utils.io.core.StringsKt.prematureEndOfStream((int) r9)
            kotlin.KotlinNothingValueException r6 = new kotlin.KotlinNothingValueException
            r6.<init>()
            throw r6
        L_0x0096:
            r7 = move-exception
        L_0x0097:
            if (r0 == 0) goto L_0x009c
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r6, r1)
        L_0x009c:
            goto L_0x009e
        L_0x009d:
            throw r7
        L_0x009e:
            goto L_0x009d
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.core.InputArraysKt.readFully(io.ktor.utils.io.core.Input, float[], int, int):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:44:0x0099  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void readFully(io.ktor.utils.io.core.Input r7, double[] r8, int r9, int r10) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            java.lang.String r0 = "dst"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)
            r0 = 1
            io.ktor.utils.io.core.internal.ChunkBuffer r1 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadFirstHead(r7, r0)
            if (r1 != 0) goto L_0x0013
            goto L_0x008a
        L_0x0013:
            r2 = 1
        L_0x0014:
            r3 = r1
            io.ktor.utils.io.core.Buffer r3 = (io.ktor.utils.io.core.Buffer) r3     // Catch:{ all -> 0x0096 }
            int r4 = r3.getWritePosition()     // Catch:{ all -> 0x0096 }
            int r3 = r3.getReadPosition()     // Catch:{ all -> 0x0096 }
            int r4 = r4 - r3
            r3 = 0
            r5 = 8
            if (r4 < r2) goto L_0x005a
            r2 = r1
            io.ktor.utils.io.core.Buffer r2 = (io.ktor.utils.io.core.Buffer) r2     // Catch:{ all -> 0x004f }
            int r4 = r2.getWritePosition()     // Catch:{ all -> 0x004f }
            int r6 = r2.getReadPosition()     // Catch:{ all -> 0x004f }
            int r4 = r4 - r6
            int r4 = r4 / r5
            int r4 = java.lang.Math.min(r10, r4)     // Catch:{ all -> 0x004f }
            io.ktor.utils.io.core.BufferPrimitivesKt.readFully((io.ktor.utils.io.core.Buffer) r2, (double[]) r8, (int) r9, (int) r4)     // Catch:{ all -> 0x004f }
            int r10 = r10 - r4
            int r9 = r9 + r4
            if (r10 <= 0) goto L_0x0040
            r2 = 8
            goto L_0x0041
        L_0x0040:
            r2 = 0
        L_0x0041:
            r4 = r1
            io.ktor.utils.io.core.Buffer r4 = (io.ktor.utils.io.core.Buffer) r4     // Catch:{ all -> 0x0096 }
            int r6 = r4.getWritePosition()     // Catch:{ all -> 0x0096 }
            int r4 = r4.getReadPosition()     // Catch:{ all -> 0x0096 }
            int r4 = r6 - r4
            goto L_0x005a
        L_0x004f:
            r8 = move-exception
            r9 = r1
            io.ktor.utils.io.core.Buffer r9 = (io.ktor.utils.io.core.Buffer) r9     // Catch:{ all -> 0x0096 }
            r9.getWritePosition()     // Catch:{ all -> 0x0096 }
            r9.getReadPosition()     // Catch:{ all -> 0x0096 }
            throw r8     // Catch:{ all -> 0x0096 }
        L_0x005a:
            if (r4 != 0) goto L_0x0064
            io.ktor.utils.io.core.internal.ChunkBuffer r4 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadNextHead(r7, r1)     // Catch:{ all -> 0x0061 }
            goto L_0x007e
        L_0x0061:
            r8 = move-exception
            r0 = 0
            goto L_0x0097
        L_0x0064:
            if (r4 < r2) goto L_0x0077
            r4 = r1
            io.ktor.utils.io.core.Buffer r4 = (io.ktor.utils.io.core.Buffer) r4     // Catch:{ all -> 0x0061 }
            int r6 = r4.getCapacity()     // Catch:{ all -> 0x0061 }
            int r4 = r4.getLimit()     // Catch:{ all -> 0x0061 }
            int r6 = r6 - r4
            if (r6 >= r5) goto L_0x0075
            goto L_0x0077
        L_0x0075:
            r4 = r1
            goto L_0x007e
        L_0x0077:
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r7, r1)     // Catch:{ all -> 0x0061 }
            io.ktor.utils.io.core.internal.ChunkBuffer r4 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadFirstHead(r7, r2)     // Catch:{ all -> 0x0061 }
        L_0x007e:
            if (r4 != 0) goto L_0x0082
            r0 = 0
            goto L_0x0085
        L_0x0082:
            r1 = r4
            if (r2 > 0) goto L_0x0014
        L_0x0085:
            if (r0 == 0) goto L_0x008a
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r7, r1)
        L_0x008a:
            if (r10 > 0) goto L_0x008d
            return
        L_0x008d:
            io.ktor.utils.io.core.StringsKt.prematureEndOfStream((int) r10)
            kotlin.KotlinNothingValueException r7 = new kotlin.KotlinNothingValueException
            r7.<init>()
            throw r7
        L_0x0096:
            r8 = move-exception
        L_0x0097:
            if (r0 == 0) goto L_0x009c
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r7, r1)
        L_0x009c:
            goto L_0x009e
        L_0x009d:
            throw r8
        L_0x009e:
            goto L_0x009d
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.core.InputArraysKt.readFully(io.ktor.utils.io.core.Input, double[], int, int):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0044  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void readFully(io.ktor.utils.io.core.Input r5, io.ktor.utils.io.core.Buffer r6, int r7) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            java.lang.String r0 = "dst"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            r0 = 1
            io.ktor.utils.io.core.internal.ChunkBuffer r1 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadFirstHead(r5, r0)
            if (r1 != 0) goto L_0x0012
            goto L_0x0035
        L_0x0012:
            r2 = r1
            io.ktor.utils.io.core.Buffer r2 = (io.ktor.utils.io.core.Buffer) r2     // Catch:{ all -> 0x0041 }
            int r3 = r2.getWritePosition()     // Catch:{ all -> 0x0041 }
            int r4 = r2.getReadPosition()     // Catch:{ all -> 0x0041 }
            int r3 = r3 - r4
            int r3 = java.lang.Math.min(r7, r3)     // Catch:{ all -> 0x0041 }
            io.ktor.utils.io.core.BufferPrimitivesKt.readFully(r2, r6, r3)     // Catch:{ all -> 0x0041 }
            int r7 = r7 - r3
            if (r7 <= 0) goto L_0x0032
            io.ktor.utils.io.core.internal.ChunkBuffer r1 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadNextHead(r5, r1)     // Catch:{ all -> 0x002f }
            if (r1 != 0) goto L_0x0012
            goto L_0x0035
        L_0x002f:
            r6 = move-exception
            r0 = 0
            goto L_0x0042
        L_0x0032:
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r5, r1)
        L_0x0035:
            if (r7 > 0) goto L_0x0038
            return
        L_0x0038:
            io.ktor.utils.io.core.StringsKt.prematureEndOfStream((int) r7)
            kotlin.KotlinNothingValueException r5 = new kotlin.KotlinNothingValueException
            r5.<init>()
            throw r5
        L_0x0041:
            r6 = move-exception
        L_0x0042:
            if (r0 == 0) goto L_0x0047
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r5, r1)
        L_0x0047:
            goto L_0x0049
        L_0x0048:
            throw r6
        L_0x0049:
            goto L_0x0048
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.core.InputArraysKt.readFully(io.ktor.utils.io.core.Input, io.ktor.utils.io.core.Buffer, int):void");
    }

    public static /* synthetic */ void readFully$default(Input input, Buffer buffer, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = buffer.getLimit() - buffer.getWritePosition();
        }
        readFully(input, buffer, i);
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x003d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final int readAvailable(io.ktor.utils.io.core.Input r6, byte[] r7, int r8, int r9) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            java.lang.String r0 = "dst"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            r0 = 1
            io.ktor.utils.io.core.internal.ChunkBuffer r1 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadFirstHead(r6, r0)
            if (r1 != 0) goto L_0x0013
            r2 = r9
            goto L_0x0038
        L_0x0013:
            r2 = r9
        L_0x0014:
            r3 = r1
            io.ktor.utils.io.core.Buffer r3 = (io.ktor.utils.io.core.Buffer) r3     // Catch:{ all -> 0x003a }
            int r4 = r3.getWritePosition()     // Catch:{ all -> 0x003a }
            int r5 = r3.getReadPosition()     // Catch:{ all -> 0x003a }
            int r4 = r4 - r5
            int r4 = java.lang.Math.min(r2, r4)     // Catch:{ all -> 0x003a }
            io.ktor.utils.io.core.BufferPrimitivesKt.readFully((io.ktor.utils.io.core.Buffer) r3, (byte[]) r7, (int) r8, (int) r4)     // Catch:{ all -> 0x003a }
            int r2 = r2 - r4
            int r8 = r8 + r4
            if (r2 <= 0) goto L_0x0035
            io.ktor.utils.io.core.internal.ChunkBuffer r1 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadNextHead(r6, r1)     // Catch:{ all -> 0x0032 }
            if (r1 != 0) goto L_0x0014
            goto L_0x0038
        L_0x0032:
            r7 = move-exception
            r0 = 0
            goto L_0x003b
        L_0x0035:
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r6, r1)
        L_0x0038:
            int r9 = r9 - r2
            return r9
        L_0x003a:
            r7 = move-exception
        L_0x003b:
            if (r0 == 0) goto L_0x0040
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r6, r1)
        L_0x0040:
            goto L_0x0042
        L_0x0041:
            throw r7
        L_0x0042:
            goto L_0x0041
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.core.InputArraysKt.readAvailable(io.ktor.utils.io.core.Input, byte[], int, int):int");
    }

    /* JADX WARNING: Removed duplicated region for block: B:43:0x0091  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final int readAvailable(io.ktor.utils.io.core.Input r7, short[] r8, int r9, int r10) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            java.lang.String r0 = "dst"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)
            r0 = 1
            io.ktor.utils.io.core.internal.ChunkBuffer r1 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadFirstHead(r7, r0)
            if (r1 != 0) goto L_0x0014
            r3 = r10
            goto L_0x008c
        L_0x0014:
            r3 = r10
            r2 = 1
        L_0x0016:
            r4 = r1
            io.ktor.utils.io.core.Buffer r4 = (io.ktor.utils.io.core.Buffer) r4     // Catch:{ all -> 0x008e }
            int r5 = r4.getWritePosition()     // Catch:{ all -> 0x008e }
            int r4 = r4.getReadPosition()     // Catch:{ all -> 0x008e }
            int r5 = r5 - r4
            r4 = 0
            if (r5 < r2) goto L_0x005a
            r2 = r1
            io.ktor.utils.io.core.Buffer r2 = (io.ktor.utils.io.core.Buffer) r2     // Catch:{ all -> 0x004f }
            int r5 = r2.getWritePosition()     // Catch:{ all -> 0x004f }
            int r6 = r2.getReadPosition()     // Catch:{ all -> 0x004f }
            int r5 = r5 - r6
            r6 = 2
            int r5 = r5 / r6
            int r5 = java.lang.Math.min(r3, r5)     // Catch:{ all -> 0x004f }
            io.ktor.utils.io.core.BufferPrimitivesKt.readFully((io.ktor.utils.io.core.Buffer) r2, (short[]) r8, (int) r9, (int) r5)     // Catch:{ all -> 0x004f }
            int r3 = r3 - r5
            int r9 = r9 + r5
            if (r3 <= 0) goto L_0x0040
            r2 = 2
            goto L_0x0041
        L_0x0040:
            r2 = 0
        L_0x0041:
            r5 = r1
            io.ktor.utils.io.core.Buffer r5 = (io.ktor.utils.io.core.Buffer) r5     // Catch:{ all -> 0x008e }
            int r6 = r5.getWritePosition()     // Catch:{ all -> 0x008e }
            int r5 = r5.getReadPosition()     // Catch:{ all -> 0x008e }
            int r5 = r6 - r5
            goto L_0x005a
        L_0x004f:
            r8 = move-exception
            r9 = r1
            io.ktor.utils.io.core.Buffer r9 = (io.ktor.utils.io.core.Buffer) r9     // Catch:{ all -> 0x008e }
            r9.getWritePosition()     // Catch:{ all -> 0x008e }
            r9.getReadPosition()     // Catch:{ all -> 0x008e }
            throw r8     // Catch:{ all -> 0x008e }
        L_0x005a:
            if (r5 != 0) goto L_0x0064
            io.ktor.utils.io.core.internal.ChunkBuffer r5 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadNextHead(r7, r1)     // Catch:{ all -> 0x0061 }
            goto L_0x0080
        L_0x0061:
            r8 = move-exception
            r0 = 0
            goto L_0x008f
        L_0x0064:
            if (r5 < r2) goto L_0x0079
            r5 = r1
            io.ktor.utils.io.core.Buffer r5 = (io.ktor.utils.io.core.Buffer) r5     // Catch:{ all -> 0x0061 }
            int r6 = r5.getCapacity()     // Catch:{ all -> 0x0061 }
            int r5 = r5.getLimit()     // Catch:{ all -> 0x0061 }
            int r6 = r6 - r5
            r5 = 8
            if (r6 >= r5) goto L_0x0077
            goto L_0x0079
        L_0x0077:
            r5 = r1
            goto L_0x0080
        L_0x0079:
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r7, r1)     // Catch:{ all -> 0x0061 }
            io.ktor.utils.io.core.internal.ChunkBuffer r5 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadFirstHead(r7, r2)     // Catch:{ all -> 0x0061 }
        L_0x0080:
            if (r5 != 0) goto L_0x0084
            r0 = 0
            goto L_0x0087
        L_0x0084:
            r1 = r5
            if (r2 > 0) goto L_0x0016
        L_0x0087:
            if (r0 == 0) goto L_0x008c
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r7, r1)
        L_0x008c:
            int r10 = r10 - r3
            return r10
        L_0x008e:
            r8 = move-exception
        L_0x008f:
            if (r0 == 0) goto L_0x0094
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r7, r1)
        L_0x0094:
            goto L_0x0096
        L_0x0095:
            throw r8
        L_0x0096:
            goto L_0x0095
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.core.InputArraysKt.readAvailable(io.ktor.utils.io.core.Input, short[], int, int):int");
    }

    /* JADX WARNING: Removed duplicated region for block: B:43:0x0091  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final int readAvailable(io.ktor.utils.io.core.Input r7, int[] r8, int r9, int r10) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            java.lang.String r0 = "dst"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)
            r0 = 1
            io.ktor.utils.io.core.internal.ChunkBuffer r1 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadFirstHead(r7, r0)
            if (r1 != 0) goto L_0x0014
            r3 = r10
            goto L_0x008c
        L_0x0014:
            r3 = r10
            r2 = 1
        L_0x0016:
            r4 = r1
            io.ktor.utils.io.core.Buffer r4 = (io.ktor.utils.io.core.Buffer) r4     // Catch:{ all -> 0x008e }
            int r5 = r4.getWritePosition()     // Catch:{ all -> 0x008e }
            int r4 = r4.getReadPosition()     // Catch:{ all -> 0x008e }
            int r5 = r5 - r4
            r4 = 0
            if (r5 < r2) goto L_0x005a
            r2 = r1
            io.ktor.utils.io.core.Buffer r2 = (io.ktor.utils.io.core.Buffer) r2     // Catch:{ all -> 0x004f }
            int r5 = r2.getWritePosition()     // Catch:{ all -> 0x004f }
            int r6 = r2.getReadPosition()     // Catch:{ all -> 0x004f }
            int r5 = r5 - r6
            r6 = 4
            int r5 = r5 / r6
            int r5 = java.lang.Math.min(r3, r5)     // Catch:{ all -> 0x004f }
            io.ktor.utils.io.core.BufferPrimitivesKt.readFully((io.ktor.utils.io.core.Buffer) r2, (int[]) r8, (int) r9, (int) r5)     // Catch:{ all -> 0x004f }
            int r3 = r3 - r5
            int r9 = r9 + r5
            if (r3 <= 0) goto L_0x0040
            r2 = 4
            goto L_0x0041
        L_0x0040:
            r2 = 0
        L_0x0041:
            r5 = r1
            io.ktor.utils.io.core.Buffer r5 = (io.ktor.utils.io.core.Buffer) r5     // Catch:{ all -> 0x008e }
            int r6 = r5.getWritePosition()     // Catch:{ all -> 0x008e }
            int r5 = r5.getReadPosition()     // Catch:{ all -> 0x008e }
            int r5 = r6 - r5
            goto L_0x005a
        L_0x004f:
            r8 = move-exception
            r9 = r1
            io.ktor.utils.io.core.Buffer r9 = (io.ktor.utils.io.core.Buffer) r9     // Catch:{ all -> 0x008e }
            r9.getWritePosition()     // Catch:{ all -> 0x008e }
            r9.getReadPosition()     // Catch:{ all -> 0x008e }
            throw r8     // Catch:{ all -> 0x008e }
        L_0x005a:
            if (r5 != 0) goto L_0x0064
            io.ktor.utils.io.core.internal.ChunkBuffer r5 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadNextHead(r7, r1)     // Catch:{ all -> 0x0061 }
            goto L_0x0080
        L_0x0061:
            r8 = move-exception
            r0 = 0
            goto L_0x008f
        L_0x0064:
            if (r5 < r2) goto L_0x0079
            r5 = r1
            io.ktor.utils.io.core.Buffer r5 = (io.ktor.utils.io.core.Buffer) r5     // Catch:{ all -> 0x0061 }
            int r6 = r5.getCapacity()     // Catch:{ all -> 0x0061 }
            int r5 = r5.getLimit()     // Catch:{ all -> 0x0061 }
            int r6 = r6 - r5
            r5 = 8
            if (r6 >= r5) goto L_0x0077
            goto L_0x0079
        L_0x0077:
            r5 = r1
            goto L_0x0080
        L_0x0079:
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r7, r1)     // Catch:{ all -> 0x0061 }
            io.ktor.utils.io.core.internal.ChunkBuffer r5 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadFirstHead(r7, r2)     // Catch:{ all -> 0x0061 }
        L_0x0080:
            if (r5 != 0) goto L_0x0084
            r0 = 0
            goto L_0x0087
        L_0x0084:
            r1 = r5
            if (r2 > 0) goto L_0x0016
        L_0x0087:
            if (r0 == 0) goto L_0x008c
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r7, r1)
        L_0x008c:
            int r10 = r10 - r3
            return r10
        L_0x008e:
            r8 = move-exception
        L_0x008f:
            if (r0 == 0) goto L_0x0094
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r7, r1)
        L_0x0094:
            goto L_0x0096
        L_0x0095:
            throw r8
        L_0x0096:
            goto L_0x0095
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.core.InputArraysKt.readAvailable(io.ktor.utils.io.core.Input, int[], int, int):int");
    }

    /* JADX WARNING: Removed duplicated region for block: B:43:0x0091  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final int readAvailable(io.ktor.utils.io.core.Input r8, long[] r9, int r10, int r11) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)
            java.lang.String r0 = "dst"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r0)
            r0 = 1
            io.ktor.utils.io.core.internal.ChunkBuffer r1 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadFirstHead(r8, r0)
            if (r1 != 0) goto L_0x0014
            r3 = r11
            goto L_0x008c
        L_0x0014:
            r3 = r11
            r2 = 1
        L_0x0016:
            r4 = r1
            io.ktor.utils.io.core.Buffer r4 = (io.ktor.utils.io.core.Buffer) r4     // Catch:{ all -> 0x008e }
            int r5 = r4.getWritePosition()     // Catch:{ all -> 0x008e }
            int r4 = r4.getReadPosition()     // Catch:{ all -> 0x008e }
            int r5 = r5 - r4
            r4 = 0
            r6 = 8
            if (r5 < r2) goto L_0x005c
            r2 = r1
            io.ktor.utils.io.core.Buffer r2 = (io.ktor.utils.io.core.Buffer) r2     // Catch:{ all -> 0x0051 }
            int r5 = r2.getWritePosition()     // Catch:{ all -> 0x0051 }
            int r7 = r2.getReadPosition()     // Catch:{ all -> 0x0051 }
            int r5 = r5 - r7
            int r5 = r5 / r6
            int r5 = java.lang.Math.min(r3, r5)     // Catch:{ all -> 0x0051 }
            io.ktor.utils.io.core.BufferPrimitivesKt.readFully((io.ktor.utils.io.core.Buffer) r2, (long[]) r9, (int) r10, (int) r5)     // Catch:{ all -> 0x0051 }
            int r3 = r3 - r5
            int r10 = r10 + r5
            if (r3 <= 0) goto L_0x0042
            r2 = 8
            goto L_0x0043
        L_0x0042:
            r2 = 0
        L_0x0043:
            r5 = r1
            io.ktor.utils.io.core.Buffer r5 = (io.ktor.utils.io.core.Buffer) r5     // Catch:{ all -> 0x008e }
            int r7 = r5.getWritePosition()     // Catch:{ all -> 0x008e }
            int r5 = r5.getReadPosition()     // Catch:{ all -> 0x008e }
            int r5 = r7 - r5
            goto L_0x005c
        L_0x0051:
            r9 = move-exception
            r10 = r1
            io.ktor.utils.io.core.Buffer r10 = (io.ktor.utils.io.core.Buffer) r10     // Catch:{ all -> 0x008e }
            r10.getWritePosition()     // Catch:{ all -> 0x008e }
            r10.getReadPosition()     // Catch:{ all -> 0x008e }
            throw r9     // Catch:{ all -> 0x008e }
        L_0x005c:
            if (r5 != 0) goto L_0x0066
            io.ktor.utils.io.core.internal.ChunkBuffer r5 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadNextHead(r8, r1)     // Catch:{ all -> 0x0063 }
            goto L_0x0080
        L_0x0063:
            r9 = move-exception
            r0 = 0
            goto L_0x008f
        L_0x0066:
            if (r5 < r2) goto L_0x0079
            r5 = r1
            io.ktor.utils.io.core.Buffer r5 = (io.ktor.utils.io.core.Buffer) r5     // Catch:{ all -> 0x0063 }
            int r7 = r5.getCapacity()     // Catch:{ all -> 0x0063 }
            int r5 = r5.getLimit()     // Catch:{ all -> 0x0063 }
            int r7 = r7 - r5
            if (r7 >= r6) goto L_0x0077
            goto L_0x0079
        L_0x0077:
            r5 = r1
            goto L_0x0080
        L_0x0079:
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r8, r1)     // Catch:{ all -> 0x0063 }
            io.ktor.utils.io.core.internal.ChunkBuffer r5 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadFirstHead(r8, r2)     // Catch:{ all -> 0x0063 }
        L_0x0080:
            if (r5 != 0) goto L_0x0084
            r0 = 0
            goto L_0x0087
        L_0x0084:
            r1 = r5
            if (r2 > 0) goto L_0x0016
        L_0x0087:
            if (r0 == 0) goto L_0x008c
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r8, r1)
        L_0x008c:
            int r11 = r11 - r3
            return r11
        L_0x008e:
            r9 = move-exception
        L_0x008f:
            if (r0 == 0) goto L_0x0094
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r8, r1)
        L_0x0094:
            goto L_0x0096
        L_0x0095:
            throw r9
        L_0x0096:
            goto L_0x0095
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.core.InputArraysKt.readAvailable(io.ktor.utils.io.core.Input, long[], int, int):int");
    }

    /* JADX WARNING: Removed duplicated region for block: B:43:0x0091  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final int readAvailable(io.ktor.utils.io.core.Input r7, float[] r8, int r9, int r10) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            java.lang.String r0 = "dst"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)
            r0 = 1
            io.ktor.utils.io.core.internal.ChunkBuffer r1 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadFirstHead(r7, r0)
            if (r1 != 0) goto L_0x0014
            r3 = r10
            goto L_0x008c
        L_0x0014:
            r3 = r10
            r2 = 1
        L_0x0016:
            r4 = r1
            io.ktor.utils.io.core.Buffer r4 = (io.ktor.utils.io.core.Buffer) r4     // Catch:{ all -> 0x008e }
            int r5 = r4.getWritePosition()     // Catch:{ all -> 0x008e }
            int r4 = r4.getReadPosition()     // Catch:{ all -> 0x008e }
            int r5 = r5 - r4
            r4 = 0
            if (r5 < r2) goto L_0x005a
            r2 = r1
            io.ktor.utils.io.core.Buffer r2 = (io.ktor.utils.io.core.Buffer) r2     // Catch:{ all -> 0x004f }
            int r5 = r2.getWritePosition()     // Catch:{ all -> 0x004f }
            int r6 = r2.getReadPosition()     // Catch:{ all -> 0x004f }
            int r5 = r5 - r6
            r6 = 4
            int r5 = r5 / r6
            int r5 = java.lang.Math.min(r3, r5)     // Catch:{ all -> 0x004f }
            io.ktor.utils.io.core.BufferPrimitivesKt.readFully((io.ktor.utils.io.core.Buffer) r2, (float[]) r8, (int) r9, (int) r5)     // Catch:{ all -> 0x004f }
            int r3 = r3 - r5
            int r9 = r9 + r5
            if (r3 <= 0) goto L_0x0040
            r2 = 4
            goto L_0x0041
        L_0x0040:
            r2 = 0
        L_0x0041:
            r5 = r1
            io.ktor.utils.io.core.Buffer r5 = (io.ktor.utils.io.core.Buffer) r5     // Catch:{ all -> 0x008e }
            int r6 = r5.getWritePosition()     // Catch:{ all -> 0x008e }
            int r5 = r5.getReadPosition()     // Catch:{ all -> 0x008e }
            int r5 = r6 - r5
            goto L_0x005a
        L_0x004f:
            r8 = move-exception
            r9 = r1
            io.ktor.utils.io.core.Buffer r9 = (io.ktor.utils.io.core.Buffer) r9     // Catch:{ all -> 0x008e }
            r9.getWritePosition()     // Catch:{ all -> 0x008e }
            r9.getReadPosition()     // Catch:{ all -> 0x008e }
            throw r8     // Catch:{ all -> 0x008e }
        L_0x005a:
            if (r5 != 0) goto L_0x0064
            io.ktor.utils.io.core.internal.ChunkBuffer r5 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadNextHead(r7, r1)     // Catch:{ all -> 0x0061 }
            goto L_0x0080
        L_0x0061:
            r8 = move-exception
            r0 = 0
            goto L_0x008f
        L_0x0064:
            if (r5 < r2) goto L_0x0079
            r5 = r1
            io.ktor.utils.io.core.Buffer r5 = (io.ktor.utils.io.core.Buffer) r5     // Catch:{ all -> 0x0061 }
            int r6 = r5.getCapacity()     // Catch:{ all -> 0x0061 }
            int r5 = r5.getLimit()     // Catch:{ all -> 0x0061 }
            int r6 = r6 - r5
            r5 = 8
            if (r6 >= r5) goto L_0x0077
            goto L_0x0079
        L_0x0077:
            r5 = r1
            goto L_0x0080
        L_0x0079:
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r7, r1)     // Catch:{ all -> 0x0061 }
            io.ktor.utils.io.core.internal.ChunkBuffer r5 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadFirstHead(r7, r2)     // Catch:{ all -> 0x0061 }
        L_0x0080:
            if (r5 != 0) goto L_0x0084
            r0 = 0
            goto L_0x0087
        L_0x0084:
            r1 = r5
            if (r2 > 0) goto L_0x0016
        L_0x0087:
            if (r0 == 0) goto L_0x008c
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r7, r1)
        L_0x008c:
            int r10 = r10 - r3
            return r10
        L_0x008e:
            r8 = move-exception
        L_0x008f:
            if (r0 == 0) goto L_0x0094
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r7, r1)
        L_0x0094:
            goto L_0x0096
        L_0x0095:
            throw r8
        L_0x0096:
            goto L_0x0095
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.core.InputArraysKt.readAvailable(io.ktor.utils.io.core.Input, float[], int, int):int");
    }

    /* JADX WARNING: Removed duplicated region for block: B:43:0x0091  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final int readAvailable(io.ktor.utils.io.core.Input r8, double[] r9, int r10, int r11) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)
            java.lang.String r0 = "dst"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r0)
            r0 = 1
            io.ktor.utils.io.core.internal.ChunkBuffer r1 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadFirstHead(r8, r0)
            if (r1 != 0) goto L_0x0014
            r3 = r11
            goto L_0x008c
        L_0x0014:
            r3 = r11
            r2 = 1
        L_0x0016:
            r4 = r1
            io.ktor.utils.io.core.Buffer r4 = (io.ktor.utils.io.core.Buffer) r4     // Catch:{ all -> 0x008e }
            int r5 = r4.getWritePosition()     // Catch:{ all -> 0x008e }
            int r4 = r4.getReadPosition()     // Catch:{ all -> 0x008e }
            int r5 = r5 - r4
            r4 = 0
            r6 = 8
            if (r5 < r2) goto L_0x005c
            r2 = r1
            io.ktor.utils.io.core.Buffer r2 = (io.ktor.utils.io.core.Buffer) r2     // Catch:{ all -> 0x0051 }
            int r5 = r2.getWritePosition()     // Catch:{ all -> 0x0051 }
            int r7 = r2.getReadPosition()     // Catch:{ all -> 0x0051 }
            int r5 = r5 - r7
            int r5 = r5 / r6
            int r5 = java.lang.Math.min(r3, r5)     // Catch:{ all -> 0x0051 }
            io.ktor.utils.io.core.BufferPrimitivesKt.readFully((io.ktor.utils.io.core.Buffer) r2, (double[]) r9, (int) r10, (int) r5)     // Catch:{ all -> 0x0051 }
            int r3 = r3 - r5
            int r10 = r10 + r5
            if (r3 <= 0) goto L_0x0042
            r2 = 8
            goto L_0x0043
        L_0x0042:
            r2 = 0
        L_0x0043:
            r5 = r1
            io.ktor.utils.io.core.Buffer r5 = (io.ktor.utils.io.core.Buffer) r5     // Catch:{ all -> 0x008e }
            int r7 = r5.getWritePosition()     // Catch:{ all -> 0x008e }
            int r5 = r5.getReadPosition()     // Catch:{ all -> 0x008e }
            int r5 = r7 - r5
            goto L_0x005c
        L_0x0051:
            r9 = move-exception
            r10 = r1
            io.ktor.utils.io.core.Buffer r10 = (io.ktor.utils.io.core.Buffer) r10     // Catch:{ all -> 0x008e }
            r10.getWritePosition()     // Catch:{ all -> 0x008e }
            r10.getReadPosition()     // Catch:{ all -> 0x008e }
            throw r9     // Catch:{ all -> 0x008e }
        L_0x005c:
            if (r5 != 0) goto L_0x0066
            io.ktor.utils.io.core.internal.ChunkBuffer r5 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadNextHead(r8, r1)     // Catch:{ all -> 0x0063 }
            goto L_0x0080
        L_0x0063:
            r9 = move-exception
            r0 = 0
            goto L_0x008f
        L_0x0066:
            if (r5 < r2) goto L_0x0079
            r5 = r1
            io.ktor.utils.io.core.Buffer r5 = (io.ktor.utils.io.core.Buffer) r5     // Catch:{ all -> 0x0063 }
            int r7 = r5.getCapacity()     // Catch:{ all -> 0x0063 }
            int r5 = r5.getLimit()     // Catch:{ all -> 0x0063 }
            int r7 = r7 - r5
            if (r7 >= r6) goto L_0x0077
            goto L_0x0079
        L_0x0077:
            r5 = r1
            goto L_0x0080
        L_0x0079:
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r8, r1)     // Catch:{ all -> 0x0063 }
            io.ktor.utils.io.core.internal.ChunkBuffer r5 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadFirstHead(r8, r2)     // Catch:{ all -> 0x0063 }
        L_0x0080:
            if (r5 != 0) goto L_0x0084
            r0 = 0
            goto L_0x0087
        L_0x0084:
            r1 = r5
            if (r2 > 0) goto L_0x0016
        L_0x0087:
            if (r0 == 0) goto L_0x008c
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r8, r1)
        L_0x008c:
            int r11 = r11 - r3
            return r11
        L_0x008e:
            r9 = move-exception
        L_0x008f:
            if (r0 == 0) goto L_0x0094
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r8, r1)
        L_0x0094:
            goto L_0x0096
        L_0x0095:
            throw r9
        L_0x0096:
            goto L_0x0095
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.core.InputArraysKt.readAvailable(io.ktor.utils.io.core.Input, double[], int, int):int");
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x003c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final int readAvailable(io.ktor.utils.io.core.Input r6, io.ktor.utils.io.core.Buffer r7, int r8) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            java.lang.String r0 = "dst"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            r0 = 1
            io.ktor.utils.io.core.internal.ChunkBuffer r1 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadFirstHead(r6, r0)
            if (r1 != 0) goto L_0x0013
            r2 = r8
            goto L_0x0037
        L_0x0013:
            r2 = r8
        L_0x0014:
            r3 = r1
            io.ktor.utils.io.core.Buffer r3 = (io.ktor.utils.io.core.Buffer) r3     // Catch:{ all -> 0x0039 }
            int r4 = r3.getWritePosition()     // Catch:{ all -> 0x0039 }
            int r5 = r3.getReadPosition()     // Catch:{ all -> 0x0039 }
            int r4 = r4 - r5
            int r4 = java.lang.Math.min(r2, r4)     // Catch:{ all -> 0x0039 }
            io.ktor.utils.io.core.BufferPrimitivesKt.readFully(r3, r7, r4)     // Catch:{ all -> 0x0039 }
            int r2 = r2 - r4
            if (r2 <= 0) goto L_0x0034
            io.ktor.utils.io.core.internal.ChunkBuffer r1 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadNextHead(r6, r1)     // Catch:{ all -> 0x0031 }
            if (r1 != 0) goto L_0x0014
            goto L_0x0037
        L_0x0031:
            r7 = move-exception
            r0 = 0
            goto L_0x003a
        L_0x0034:
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r6, r1)
        L_0x0037:
            int r8 = r8 - r2
            return r8
        L_0x0039:
            r7 = move-exception
        L_0x003a:
            if (r0 == 0) goto L_0x003f
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r6, r1)
        L_0x003f:
            goto L_0x0041
        L_0x0040:
            throw r7
        L_0x0041:
            goto L_0x0040
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.core.InputArraysKt.readAvailable(io.ktor.utils.io.core.Input, io.ktor.utils.io.core.Buffer, int):int");
    }

    public static /* synthetic */ int readAvailable$default(Input input, Buffer buffer, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = buffer.getLimit() - buffer.getWritePosition();
        }
        return readAvailable(input, buffer, i);
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x0079  */
    /* renamed from: readAvailable-UAd2zVI  reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final long m1689readAvailableUAd2zVI(io.ktor.utils.io.core.Input r18, java.nio.ByteBuffer r19, long r20, long r22) {
        /*
            r1 = r18
            java.lang.String r0 = "$this$readAvailable"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r0)
            java.lang.String r0 = "destination"
            r10 = r19
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r0)
            r11 = 1
            io.ktor.utils.io.core.internal.ChunkBuffer r0 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadFirstHead(r1, r11)
            if (r0 != 0) goto L_0x0018
            r6 = r22
            goto L_0x0061
        L_0x0018:
            r14 = r20
            r6 = r22
            r8 = r0
        L_0x001d:
            r0 = r8
            io.ktor.utils.io.core.Buffer r0 = (io.ktor.utils.io.core.Buffer) r0     // Catch:{ all -> 0x0074 }
            int r2 = r0.getWritePosition()     // Catch:{ all -> 0x0074 }
            int r3 = r0.getReadPosition()     // Catch:{ all -> 0x0074 }
            int r2 = r2 - r3
            long r2 = (long) r2     // Catch:{ all -> 0x0074 }
            long r2 = java.lang.Math.min(r6, r2)     // Catch:{ all -> 0x0074 }
            int r9 = (int) r2     // Catch:{ all -> 0x0074 }
            java.nio.ByteBuffer r2 = r0.m155getMemorySK3TCg8()     // Catch:{ all -> 0x0074 }
            int r3 = r0.getReadPosition()     // Catch:{ all -> 0x0074 }
            long r4 = (long) r3
            long r11 = (long) r9
            r3 = r19
            r16 = r6
            r6 = r11
            r13 = r8
            r10 = r9
            r8 = r14
            io.ktor.utils.io.bits.Memory.m1511copyToJT6ljtQ((java.nio.ByteBuffer) r2, (java.nio.ByteBuffer) r3, (long) r4, (long) r6, (long) r8)     // Catch:{ all -> 0x0072 }
            r0.discardExact(r10)     // Catch:{ all -> 0x0072 }
            long r6 = r16 - r11
            long r14 = r14 + r11
            r2 = 0
            int r0 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r0 <= 0) goto L_0x005e
            io.ktor.utils.io.core.internal.ChunkBuffer r8 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadNextHead(r1, r13)     // Catch:{ all -> 0x005b }
            if (r8 != 0) goto L_0x0057
            goto L_0x0061
        L_0x0057:
            r10 = r19
            r11 = 1
            goto L_0x001d
        L_0x005b:
            r0 = move-exception
            r11 = 0
            goto L_0x0077
        L_0x005e:
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r1, r13)
        L_0x0061:
            long r2 = r22 - r6
            r4 = 0
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r0 != 0) goto L_0x0071
            boolean r0 = r18.getEndOfInput()
            if (r0 == 0) goto L_0x0071
            r2 = -1
        L_0x0071:
            return r2
        L_0x0072:
            r0 = move-exception
            goto L_0x0076
        L_0x0074:
            r0 = move-exception
            r13 = r8
        L_0x0076:
            r11 = 1
        L_0x0077:
            if (r11 == 0) goto L_0x007c
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r1, r13)
        L_0x007c:
            goto L_0x007e
        L_0x007d:
            throw r0
        L_0x007e:
            goto L_0x007d
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.core.InputArraysKt.m1689readAvailableUAd2zVI(io.ktor.utils.io.core.Input, java.nio.ByteBuffer, long, long):long");
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0047  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final int readFullyBytesTemplate(io.ktor.utils.io.core.Input r6, int r7, int r8, kotlin.jvm.functions.Function3<? super io.ktor.utils.io.core.Buffer, ? super java.lang.Integer, ? super java.lang.Integer, kotlin.Unit> r9) {
        /*
            r0 = 1
            io.ktor.utils.io.core.internal.ChunkBuffer r1 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadFirstHead(r6, r0)
            if (r1 != 0) goto L_0x0008
            goto L_0x003f
        L_0x0008:
            r2 = r1
            io.ktor.utils.io.core.Buffer r2 = (io.ktor.utils.io.core.Buffer) r2     // Catch:{ all -> 0x0040 }
            int r3 = r2.getWritePosition()     // Catch:{ all -> 0x0040 }
            int r4 = r2.getReadPosition()     // Catch:{ all -> 0x0040 }
            int r3 = r3 - r4
            int r3 = java.lang.Math.min(r8, r3)     // Catch:{ all -> 0x0040 }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r7)     // Catch:{ all -> 0x0040 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x0040 }
            r9.invoke(r2, r4, r5)     // Catch:{ all -> 0x0040 }
            int r8 = r8 - r3
            int r7 = r7 + r3
            if (r8 <= 0) goto L_0x0033
            r2 = 0
            io.ktor.utils.io.core.internal.ChunkBuffer r3 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadNextHead(r6, r1)     // Catch:{ all -> 0x0031 }
            if (r3 != 0) goto L_0x002f
            goto L_0x0034
        L_0x002f:
            r1 = r3
            goto L_0x0008
        L_0x0031:
            r7 = move-exception
            goto L_0x0042
        L_0x0033:
            r2 = 1
        L_0x0034:
            kotlin.jvm.internal.InlineMarker.finallyStart(r0)
            if (r2 == 0) goto L_0x003c
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r6, r1)
        L_0x003c:
            kotlin.jvm.internal.InlineMarker.finallyEnd(r0)
        L_0x003f:
            return r8
        L_0x0040:
            r7 = move-exception
            r2 = 1
        L_0x0042:
            kotlin.jvm.internal.InlineMarker.finallyStart(r0)
            if (r2 == 0) goto L_0x004a
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r6, r1)
        L_0x004a:
            kotlin.jvm.internal.InlineMarker.finallyEnd(r0)
            goto L_0x004f
        L_0x004e:
            throw r7
        L_0x004f:
            goto L_0x004e
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.core.InputArraysKt.readFullyBytesTemplate(io.ktor.utils.io.core.Input, int, int, kotlin.jvm.functions.Function3):int");
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0062  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final long readFullyBytesTemplate(io.ktor.utils.io.core.Input r8, long r9, long r11, kotlin.jvm.functions.Function4<? super io.ktor.utils.io.bits.Memory, ? super java.lang.Long, ? super java.lang.Long, ? super java.lang.Integer, kotlin.Unit> r13) {
        /*
            r0 = 1
            io.ktor.utils.io.core.internal.ChunkBuffer r1 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadFirstHead(r8, r0)
            if (r1 != 0) goto L_0x0008
            goto L_0x005a
        L_0x0008:
            r2 = r1
            io.ktor.utils.io.core.Buffer r2 = (io.ktor.utils.io.core.Buffer) r2     // Catch:{ all -> 0x005b }
            int r3 = r2.getWritePosition()     // Catch:{ all -> 0x005b }
            int r4 = r2.getReadPosition()     // Catch:{ all -> 0x005b }
            int r3 = r3 - r4
            long r3 = (long) r3     // Catch:{ all -> 0x005b }
            long r3 = java.lang.Math.min(r11, r3)     // Catch:{ all -> 0x005b }
            int r4 = (int) r3     // Catch:{ all -> 0x005b }
            java.nio.ByteBuffer r3 = r2.m155getMemorySK3TCg8()     // Catch:{ all -> 0x005b }
            io.ktor.utils.io.bits.Memory r3 = io.ktor.utils.io.bits.Memory.m1508boximpl(r3)     // Catch:{ all -> 0x005b }
            int r5 = r2.getReadPosition()     // Catch:{ all -> 0x005b }
            long r5 = (long) r5     // Catch:{ all -> 0x005b }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x005b }
            java.lang.Long r6 = java.lang.Long.valueOf(r9)     // Catch:{ all -> 0x005b }
            java.lang.Integer r7 = java.lang.Integer.valueOf(r4)     // Catch:{ all -> 0x005b }
            r13.invoke(r3, r5, r6, r7)     // Catch:{ all -> 0x005b }
            r2.discardExact(r4)     // Catch:{ all -> 0x005b }
            long r2 = (long) r4
            long r11 = r11 - r2
            long r9 = r9 + r2
            r2 = 0
            int r4 = (r11 > r2 ? 1 : (r11 == r2 ? 0 : -1))
            if (r4 <= 0) goto L_0x004e
            r2 = 0
            io.ktor.utils.io.core.internal.ChunkBuffer r3 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadNextHead(r8, r1)     // Catch:{ all -> 0x004c }
            if (r3 != 0) goto L_0x004a
            goto L_0x004f
        L_0x004a:
            r1 = r3
            goto L_0x0008
        L_0x004c:
            r9 = move-exception
            goto L_0x005d
        L_0x004e:
            r2 = 1
        L_0x004f:
            kotlin.jvm.internal.InlineMarker.finallyStart(r0)
            if (r2 == 0) goto L_0x0057
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r8, r1)
        L_0x0057:
            kotlin.jvm.internal.InlineMarker.finallyEnd(r0)
        L_0x005a:
            return r11
        L_0x005b:
            r9 = move-exception
            r2 = 1
        L_0x005d:
            kotlin.jvm.internal.InlineMarker.finallyStart(r0)
            if (r2 == 0) goto L_0x0065
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r8, r1)
        L_0x0065:
            kotlin.jvm.internal.InlineMarker.finallyEnd(r0)
            goto L_0x006a
        L_0x0069:
            throw r9
        L_0x006a:
            goto L_0x0069
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.core.InputArraysKt.readFullyBytesTemplate(io.ktor.utils.io.core.Input, long, long, kotlin.jvm.functions.Function4):long");
    }

    /* JADX WARNING: Removed duplicated region for block: B:44:0x00a0  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final int readFullyTemplate(io.ktor.utils.io.core.Input r7, int r8, int r9, int r10, kotlin.jvm.functions.Function3<? super io.ktor.utils.io.core.Buffer, ? super java.lang.Integer, ? super java.lang.Integer, kotlin.Unit> r11) {
        /*
            r0 = 1
            io.ktor.utils.io.core.internal.ChunkBuffer r1 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadFirstHead(r7, r0)
            if (r1 != 0) goto L_0x0009
            goto L_0x0098
        L_0x0009:
            r2 = 1
        L_0x000a:
            r3 = r1
            io.ktor.utils.io.core.Buffer r3 = (io.ktor.utils.io.core.Buffer) r3     // Catch:{ all -> 0x0099 }
            int r4 = r3.getWritePosition()     // Catch:{ all -> 0x0099 }
            int r3 = r3.getReadPosition()     // Catch:{ all -> 0x0099 }
            int r4 = r4 - r3
            r3 = 0
            if (r4 < r2) goto L_0x0061
            r2 = r1
            io.ktor.utils.io.core.Buffer r2 = (io.ktor.utils.io.core.Buffer) r2     // Catch:{ all -> 0x0050 }
            int r4 = r2.getWritePosition()     // Catch:{ all -> 0x0050 }
            int r5 = r2.getReadPosition()     // Catch:{ all -> 0x0050 }
            int r4 = r4 - r5
            int r4 = r4 / r10
            int r4 = java.lang.Math.min(r9, r4)     // Catch:{ all -> 0x0050 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r8)     // Catch:{ all -> 0x0050 }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r4)     // Catch:{ all -> 0x0050 }
            r11.invoke(r2, r5, r6)     // Catch:{ all -> 0x0050 }
            int r9 = r9 - r4
            int r8 = r8 + r4
            if (r9 <= 0) goto L_0x003b
            r2 = r10
            goto L_0x003c
        L_0x003b:
            r2 = 0
        L_0x003c:
            kotlin.jvm.internal.InlineMarker.finallyStart(r0)     // Catch:{ all -> 0x0099 }
            r4 = r1
            io.ktor.utils.io.core.Buffer r4 = (io.ktor.utils.io.core.Buffer) r4     // Catch:{ all -> 0x0099 }
            int r5 = r4.getWritePosition()     // Catch:{ all -> 0x0099 }
            int r4 = r4.getReadPosition()     // Catch:{ all -> 0x0099 }
            int r4 = r5 - r4
            kotlin.jvm.internal.InlineMarker.finallyEnd(r0)     // Catch:{ all -> 0x0099 }
            goto L_0x0061
        L_0x0050:
            r8 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r0)     // Catch:{ all -> 0x0099 }
            r9 = r1
            io.ktor.utils.io.core.Buffer r9 = (io.ktor.utils.io.core.Buffer) r9     // Catch:{ all -> 0x0099 }
            r9.getWritePosition()     // Catch:{ all -> 0x0099 }
            r9.getReadPosition()     // Catch:{ all -> 0x0099 }
            kotlin.jvm.internal.InlineMarker.finallyEnd(r0)     // Catch:{ all -> 0x0099 }
            throw r8     // Catch:{ all -> 0x0099 }
        L_0x0061:
            if (r4 != 0) goto L_0x006a
            io.ktor.utils.io.core.internal.ChunkBuffer r4 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadNextHead(r7, r1)     // Catch:{ all -> 0x0068 }
            goto L_0x0086
        L_0x0068:
            r8 = move-exception
            goto L_0x009b
        L_0x006a:
            if (r4 < r2) goto L_0x007f
            r4 = r1
            io.ktor.utils.io.core.Buffer r4 = (io.ktor.utils.io.core.Buffer) r4     // Catch:{ all -> 0x0068 }
            int r5 = r4.getCapacity()     // Catch:{ all -> 0x0068 }
            int r4 = r4.getLimit()     // Catch:{ all -> 0x0068 }
            int r5 = r5 - r4
            r4 = 8
            if (r5 >= r4) goto L_0x007d
            goto L_0x007f
        L_0x007d:
            r4 = r1
            goto L_0x0086
        L_0x007f:
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r7, r1)     // Catch:{ all -> 0x0068 }
            io.ktor.utils.io.core.internal.ChunkBuffer r4 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadFirstHead(r7, r2)     // Catch:{ all -> 0x0068 }
        L_0x0086:
            if (r4 != 0) goto L_0x0089
            goto L_0x008d
        L_0x0089:
            r1 = r4
            if (r2 > 0) goto L_0x000a
            r3 = 1
        L_0x008d:
            kotlin.jvm.internal.InlineMarker.finallyStart(r0)
            if (r3 == 0) goto L_0x0095
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r7, r1)
        L_0x0095:
            kotlin.jvm.internal.InlineMarker.finallyEnd(r0)
        L_0x0098:
            return r9
        L_0x0099:
            r8 = move-exception
            r3 = 1
        L_0x009b:
            kotlin.jvm.internal.InlineMarker.finallyStart(r0)
            if (r3 == 0) goto L_0x00a3
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r7, r1)
        L_0x00a3:
            kotlin.jvm.internal.InlineMarker.finallyEnd(r0)
            goto L_0x00a8
        L_0x00a7:
            throw r8
        L_0x00a8:
            goto L_0x00a7
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.core.InputArraysKt.readFullyTemplate(io.ktor.utils.io.core.Input, int, int, int, kotlin.jvm.functions.Function3):int");
    }
}
