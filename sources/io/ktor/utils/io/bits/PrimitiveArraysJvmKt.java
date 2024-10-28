package io.ktor.utils.io.bits;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import io.ktor.utils.io.core.internal.NumbersKt;
import io.netty.handler.codec.rtsp.RtspHeaders;
import java.nio.ByteBuffer;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000H\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0013\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0002\b\u0004\n\u0002\u0010\u0015\n\u0002\b\u0004\n\u0002\u0010\u0016\n\u0002\b\u0004\n\u0002\u0010\u0017\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0000\u001a;\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\t\u0010\n\u001a;\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\t\u0010\f\u001a;\u0010\r\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u000e2\b\b\u0002\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u000f\u0010\u0010\u001a;\u0010\r\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u000e2\b\b\u0002\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u000f\u0010\u0011\u001a;\u0010\u0012\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00132\b\b\u0002\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0014\u0010\u0015\u001a;\u0010\u0012\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u00132\b\b\u0002\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0014\u0010\u0016\u001a;\u0010\u0017\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00182\b\b\u0002\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0019\u0010\u001a\u001a;\u0010\u0017\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u00182\b\b\u0002\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0019\u0010\u001b\u001a;\u0010\u001c\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u001d2\b\b\u0002\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001e\u0010\u001f\u001a;\u0010\u001c\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u001d2\b\b\u0002\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001e\u0010 \u001a;\u0010!\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\"\u001a\u00020\u00062\b\b\u0002\u0010#\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b$\u0010\n\u001a;\u0010!\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u000b2\u0006\u0010\"\u001a\u00020\u00062\b\b\u0002\u0010#\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b$\u0010\f\u001a;\u0010%\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\"\u001a\u00020\u000e2\b\b\u0002\u0010#\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b&\u0010\u0010\u001a;\u0010%\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u000b2\u0006\u0010\"\u001a\u00020\u000e2\b\b\u0002\u0010#\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b&\u0010\u0011\u001a;\u0010'\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\"\u001a\u00020\u00132\b\b\u0002\u0010#\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b(\u0010\u0015\u001a;\u0010'\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u000b2\u0006\u0010\"\u001a\u00020\u00132\b\b\u0002\u0010#\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b(\u0010\u0016\u001a;\u0010)\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\"\u001a\u00020\u00182\b\b\u0002\u0010#\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b*\u0010\u001a\u001a;\u0010)\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u000b2\u0006\u0010\"\u001a\u00020\u00182\b\b\u0002\u0010#\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b*\u0010\u001b\u001a;\u0010+\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\"\u001a\u00020\u001d2\b\b\u0002\u0010#\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b,\u0010\u001f\u001a;\u0010+\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u000b2\u0006\u0010\"\u001a\u00020\u001d2\b\b\u0002\u0010#\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b,\u0010 \u001a\u0015\u0010-\u001a\u00020.*\u00020.2\u0006\u0010\u0003\u001a\u00020\u0004H\b\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006/"}, d2 = {"loadDoubleArray", "", "Lio/ktor/utils/io/bits/Memory;", "offset", "", "destination", "", "destinationOffset", "count", "loadDoubleArray-9zorpBc", "(Ljava/nio/ByteBuffer;I[DII)V", "", "(Ljava/nio/ByteBuffer;J[DII)V", "loadFloatArray", "", "loadFloatArray-9zorpBc", "(Ljava/nio/ByteBuffer;I[FII)V", "(Ljava/nio/ByteBuffer;J[FII)V", "loadIntArray", "", "loadIntArray-9zorpBc", "(Ljava/nio/ByteBuffer;I[III)V", "(Ljava/nio/ByteBuffer;J[III)V", "loadLongArray", "", "loadLongArray-9zorpBc", "(Ljava/nio/ByteBuffer;I[JII)V", "(Ljava/nio/ByteBuffer;J[JII)V", "loadShortArray", "", "loadShortArray-9zorpBc", "(Ljava/nio/ByteBuffer;I[SII)V", "(Ljava/nio/ByteBuffer;J[SII)V", "storeDoubleArray", "source", "sourceOffset", "storeDoubleArray-9zorpBc", "storeFloatArray", "storeFloatArray-9zorpBc", "storeIntArray", "storeIntArray-9zorpBc", "storeLongArray", "storeLongArray-9zorpBc", "storeShortArray", "storeShortArray-9zorpBc", "withOffset", "Ljava/nio/ByteBuffer;", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: PrimitiveArraysJvm.kt */
public final class PrimitiveArraysJvmKt {
    /* renamed from: loadShortArray-9zorpBc  reason: not valid java name */
    public static final void m1630loadShortArray9zorpBc(ByteBuffer byteBuffer, long j, short[] sArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(byteBuffer, "$this$loadShortArray");
        Intrinsics.checkNotNullParameter(sArr, RtspHeaders.Values.DESTINATION);
        if (j < 2147483647L) {
            m1629loadShortArray9zorpBc(byteBuffer, (int) j, sArr, i, i2);
        } else {
            NumbersKt.failLongToIntConversion(j, TypedValues.CycleType.S_WAVE_OFFSET);
            throw new KotlinNothingValueException();
        }
    }

    /* renamed from: loadIntArray-9zorpBc  reason: not valid java name */
    public static final void m1622loadIntArray9zorpBc(ByteBuffer byteBuffer, long j, int[] iArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(byteBuffer, "$this$loadIntArray");
        Intrinsics.checkNotNullParameter(iArr, RtspHeaders.Values.DESTINATION);
        if (j < 2147483647L) {
            m1621loadIntArray9zorpBc(byteBuffer, (int) j, iArr, i, i2);
        } else {
            NumbersKt.failLongToIntConversion(j, TypedValues.CycleType.S_WAVE_OFFSET);
            throw new KotlinNothingValueException();
        }
    }

    /* renamed from: loadShortArray-9zorpBc$default  reason: not valid java name */
    public static /* synthetic */ void m1631loadShortArray9zorpBc$default(ByteBuffer byteBuffer, int i, short[] sArr, int i2, int i3, int i4, Object obj) {
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = sArr.length - i2;
        }
        m1629loadShortArray9zorpBc(byteBuffer, i, sArr, i2, i3);
    }

    /* renamed from: loadLongArray-9zorpBc  reason: not valid java name */
    public static final void m1626loadLongArray9zorpBc(ByteBuffer byteBuffer, long j, long[] jArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(byteBuffer, "$this$loadLongArray");
        Intrinsics.checkNotNullParameter(jArr, RtspHeaders.Values.DESTINATION);
        if (j < 2147483647L) {
            m1625loadLongArray9zorpBc(byteBuffer, (int) j, jArr, i, i2);
        } else {
            NumbersKt.failLongToIntConversion(j, TypedValues.CycleType.S_WAVE_OFFSET);
            throw new KotlinNothingValueException();
        }
    }

    /* renamed from: loadShortArray-9zorpBc$default  reason: not valid java name */
    public static /* synthetic */ void m1632loadShortArray9zorpBc$default(ByteBuffer byteBuffer, long j, short[] sArr, int i, int i2, int i3, Object obj) {
        int i4 = (i3 & 4) != 0 ? 0 : i;
        if ((i3 & 8) != 0) {
            i2 = sArr.length - i4;
        }
        m1630loadShortArray9zorpBc(byteBuffer, j, sArr, i4, i2);
    }

    /* renamed from: loadFloatArray-9zorpBc  reason: not valid java name */
    public static final void m1618loadFloatArray9zorpBc(ByteBuffer byteBuffer, long j, float[] fArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(byteBuffer, "$this$loadFloatArray");
        Intrinsics.checkNotNullParameter(fArr, RtspHeaders.Values.DESTINATION);
        if (j < 2147483647L) {
            m1617loadFloatArray9zorpBc(byteBuffer, (int) j, fArr, i, i2);
        } else {
            NumbersKt.failLongToIntConversion(j, TypedValues.CycleType.S_WAVE_OFFSET);
            throw new KotlinNothingValueException();
        }
    }

    /* renamed from: loadIntArray-9zorpBc$default  reason: not valid java name */
    public static /* synthetic */ void m1623loadIntArray9zorpBc$default(ByteBuffer byteBuffer, int i, int[] iArr, int i2, int i3, int i4, Object obj) {
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = iArr.length - i2;
        }
        m1621loadIntArray9zorpBc(byteBuffer, i, iArr, i2, i3);
    }

    /* renamed from: loadDoubleArray-9zorpBc  reason: not valid java name */
    public static final void m1614loadDoubleArray9zorpBc(ByteBuffer byteBuffer, long j, double[] dArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(byteBuffer, "$this$loadDoubleArray");
        Intrinsics.checkNotNullParameter(dArr, RtspHeaders.Values.DESTINATION);
        if (j < 2147483647L) {
            m1613loadDoubleArray9zorpBc(byteBuffer, (int) j, dArr, i, i2);
        } else {
            NumbersKt.failLongToIntConversion(j, TypedValues.CycleType.S_WAVE_OFFSET);
            throw new KotlinNothingValueException();
        }
    }

    /* renamed from: loadIntArray-9zorpBc$default  reason: not valid java name */
    public static /* synthetic */ void m1624loadIntArray9zorpBc$default(ByteBuffer byteBuffer, long j, int[] iArr, int i, int i2, int i3, Object obj) {
        int i4 = (i3 & 4) != 0 ? 0 : i;
        if ((i3 & 8) != 0) {
            i2 = iArr.length - i4;
        }
        m1622loadIntArray9zorpBc(byteBuffer, j, iArr, i4, i2);
    }

    /* renamed from: storeShortArray-9zorpBc  reason: not valid java name */
    public static final void m1650storeShortArray9zorpBc(ByteBuffer byteBuffer, long j, short[] sArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(byteBuffer, "$this$storeShortArray");
        Intrinsics.checkNotNullParameter(sArr, "source");
        if (j < 2147483647L) {
            m1649storeShortArray9zorpBc(byteBuffer, (int) j, sArr, i, i2);
        } else {
            NumbersKt.failLongToIntConversion(j, TypedValues.CycleType.S_WAVE_OFFSET);
            throw new KotlinNothingValueException();
        }
    }

    /* renamed from: loadLongArray-9zorpBc$default  reason: not valid java name */
    public static /* synthetic */ void m1627loadLongArray9zorpBc$default(ByteBuffer byteBuffer, int i, long[] jArr, int i2, int i3, int i4, Object obj) {
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = jArr.length - i2;
        }
        m1625loadLongArray9zorpBc(byteBuffer, i, jArr, i2, i3);
    }

    /* renamed from: storeIntArray-9zorpBc  reason: not valid java name */
    public static final void m1642storeIntArray9zorpBc(ByteBuffer byteBuffer, long j, int[] iArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(byteBuffer, "$this$storeIntArray");
        Intrinsics.checkNotNullParameter(iArr, "source");
        if (j < 2147483647L) {
            m1641storeIntArray9zorpBc(byteBuffer, (int) j, iArr, i, i2);
        } else {
            NumbersKt.failLongToIntConversion(j, TypedValues.CycleType.S_WAVE_OFFSET);
            throw new KotlinNothingValueException();
        }
    }

    /* renamed from: loadLongArray-9zorpBc$default  reason: not valid java name */
    public static /* synthetic */ void m1628loadLongArray9zorpBc$default(ByteBuffer byteBuffer, long j, long[] jArr, int i, int i2, int i3, Object obj) {
        int i4 = (i3 & 4) != 0 ? 0 : i;
        if ((i3 & 8) != 0) {
            i2 = jArr.length - i4;
        }
        m1626loadLongArray9zorpBc(byteBuffer, j, jArr, i4, i2);
    }

    /* renamed from: storeLongArray-9zorpBc  reason: not valid java name */
    public static final void m1646storeLongArray9zorpBc(ByteBuffer byteBuffer, long j, long[] jArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(byteBuffer, "$this$storeLongArray");
        Intrinsics.checkNotNullParameter(jArr, "source");
        if (j < 2147483647L) {
            m1645storeLongArray9zorpBc(byteBuffer, (int) j, jArr, i, i2);
        } else {
            NumbersKt.failLongToIntConversion(j, TypedValues.CycleType.S_WAVE_OFFSET);
            throw new KotlinNothingValueException();
        }
    }

    /* renamed from: storeFloatArray-9zorpBc  reason: not valid java name */
    public static final void m1638storeFloatArray9zorpBc(ByteBuffer byteBuffer, long j, float[] fArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(byteBuffer, "$this$storeFloatArray");
        Intrinsics.checkNotNullParameter(fArr, "source");
        if (j < 2147483647L) {
            m1637storeFloatArray9zorpBc(byteBuffer, (int) j, fArr, i, i2);
        } else {
            NumbersKt.failLongToIntConversion(j, TypedValues.CycleType.S_WAVE_OFFSET);
            throw new KotlinNothingValueException();
        }
    }

    /* renamed from: loadFloatArray-9zorpBc$default  reason: not valid java name */
    public static /* synthetic */ void m1619loadFloatArray9zorpBc$default(ByteBuffer byteBuffer, int i, float[] fArr, int i2, int i3, int i4, Object obj) {
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = fArr.length - i2;
        }
        m1617loadFloatArray9zorpBc(byteBuffer, i, fArr, i2, i3);
    }

    /* renamed from: loadFloatArray-9zorpBc$default  reason: not valid java name */
    public static /* synthetic */ void m1620loadFloatArray9zorpBc$default(ByteBuffer byteBuffer, long j, float[] fArr, int i, int i2, int i3, Object obj) {
        int i4 = (i3 & 4) != 0 ? 0 : i;
        if ((i3 & 8) != 0) {
            i2 = fArr.length - i4;
        }
        m1618loadFloatArray9zorpBc(byteBuffer, j, fArr, i4, i2);
    }

    /* renamed from: storeDoubleArray-9zorpBc  reason: not valid java name */
    public static final void m1634storeDoubleArray9zorpBc(ByteBuffer byteBuffer, long j, double[] dArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(byteBuffer, "$this$storeDoubleArray");
        Intrinsics.checkNotNullParameter(dArr, "source");
        if (j < 2147483647L) {
            m1633storeDoubleArray9zorpBc(byteBuffer, (int) j, dArr, i, i2);
        } else {
            NumbersKt.failLongToIntConversion(j, TypedValues.CycleType.S_WAVE_OFFSET);
            throw new KotlinNothingValueException();
        }
    }

    /* renamed from: loadDoubleArray-9zorpBc$default  reason: not valid java name */
    public static /* synthetic */ void m1615loadDoubleArray9zorpBc$default(ByteBuffer byteBuffer, int i, double[] dArr, int i2, int i3, int i4, Object obj) {
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = dArr.length - i2;
        }
        m1613loadDoubleArray9zorpBc(byteBuffer, i, dArr, i2, i3);
    }

    private static final ByteBuffer withOffset(ByteBuffer byteBuffer, int i) {
        ByteBuffer duplicate = byteBuffer.duplicate();
        Intrinsics.checkNotNull(duplicate);
        duplicate.position(i);
        return duplicate;
    }

    /* renamed from: loadDoubleArray-9zorpBc$default  reason: not valid java name */
    public static /* synthetic */ void m1616loadDoubleArray9zorpBc$default(ByteBuffer byteBuffer, long j, double[] dArr, int i, int i2, int i3, Object obj) {
        int i4 = (i3 & 4) != 0 ? 0 : i;
        if ((i3 & 8) != 0) {
            i2 = dArr.length - i4;
        }
        m1614loadDoubleArray9zorpBc(byteBuffer, j, dArr, i4, i2);
    }

    /* renamed from: storeDoubleArray-9zorpBc$default  reason: not valid java name */
    public static /* synthetic */ void m1635storeDoubleArray9zorpBc$default(ByteBuffer byteBuffer, int i, double[] dArr, int i2, int i3, int i4, Object obj) {
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = dArr.length - i2;
        }
        m1633storeDoubleArray9zorpBc(byteBuffer, i, dArr, i2, i3);
    }

    /* renamed from: storeDoubleArray-9zorpBc$default  reason: not valid java name */
    public static /* synthetic */ void m1636storeDoubleArray9zorpBc$default(ByteBuffer byteBuffer, long j, double[] dArr, int i, int i2, int i3, Object obj) {
        int i4 = (i3 & 4) != 0 ? 0 : i;
        if ((i3 & 8) != 0) {
            i2 = dArr.length - i4;
        }
        m1634storeDoubleArray9zorpBc(byteBuffer, j, dArr, i4, i2);
    }

    /* renamed from: storeFloatArray-9zorpBc$default  reason: not valid java name */
    public static /* synthetic */ void m1639storeFloatArray9zorpBc$default(ByteBuffer byteBuffer, int i, float[] fArr, int i2, int i3, int i4, Object obj) {
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = fArr.length - i2;
        }
        m1637storeFloatArray9zorpBc(byteBuffer, i, fArr, i2, i3);
    }

    /* renamed from: storeFloatArray-9zorpBc$default  reason: not valid java name */
    public static /* synthetic */ void m1640storeFloatArray9zorpBc$default(ByteBuffer byteBuffer, long j, float[] fArr, int i, int i2, int i3, Object obj) {
        int i4 = (i3 & 4) != 0 ? 0 : i;
        if ((i3 & 8) != 0) {
            i2 = fArr.length - i4;
        }
        m1638storeFloatArray9zorpBc(byteBuffer, j, fArr, i4, i2);
    }

    /* renamed from: storeIntArray-9zorpBc$default  reason: not valid java name */
    public static /* synthetic */ void m1643storeIntArray9zorpBc$default(ByteBuffer byteBuffer, int i, int[] iArr, int i2, int i3, int i4, Object obj) {
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = iArr.length - i2;
        }
        m1641storeIntArray9zorpBc(byteBuffer, i, iArr, i2, i3);
    }

    /* renamed from: storeIntArray-9zorpBc$default  reason: not valid java name */
    public static /* synthetic */ void m1644storeIntArray9zorpBc$default(ByteBuffer byteBuffer, long j, int[] iArr, int i, int i2, int i3, Object obj) {
        int i4 = (i3 & 4) != 0 ? 0 : i;
        if ((i3 & 8) != 0) {
            i2 = iArr.length - i4;
        }
        m1642storeIntArray9zorpBc(byteBuffer, j, iArr, i4, i2);
    }

    /* renamed from: storeLongArray-9zorpBc$default  reason: not valid java name */
    public static /* synthetic */ void m1647storeLongArray9zorpBc$default(ByteBuffer byteBuffer, int i, long[] jArr, int i2, int i3, int i4, Object obj) {
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = jArr.length - i2;
        }
        m1645storeLongArray9zorpBc(byteBuffer, i, jArr, i2, i3);
    }

    /* renamed from: storeLongArray-9zorpBc$default  reason: not valid java name */
    public static /* synthetic */ void m1648storeLongArray9zorpBc$default(ByteBuffer byteBuffer, long j, long[] jArr, int i, int i2, int i3, Object obj) {
        int i4 = (i3 & 4) != 0 ? 0 : i;
        if ((i3 & 8) != 0) {
            i2 = jArr.length - i4;
        }
        m1646storeLongArray9zorpBc(byteBuffer, j, jArr, i4, i2);
    }

    /* renamed from: storeShortArray-9zorpBc$default  reason: not valid java name */
    public static /* synthetic */ void m1651storeShortArray9zorpBc$default(ByteBuffer byteBuffer, int i, short[] sArr, int i2, int i3, int i4, Object obj) {
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = sArr.length - i2;
        }
        m1649storeShortArray9zorpBc(byteBuffer, i, sArr, i2, i3);
    }

    /* renamed from: storeShortArray-9zorpBc$default  reason: not valid java name */
    public static /* synthetic */ void m1652storeShortArray9zorpBc$default(ByteBuffer byteBuffer, long j, short[] sArr, int i, int i2, int i3, Object obj) {
        int i4 = (i3 & 4) != 0 ? 0 : i;
        if ((i3 & 8) != 0) {
            i2 = sArr.length - i4;
        }
        m1650storeShortArray9zorpBc(byteBuffer, j, sArr, i4, i2);
    }

    /* renamed from: loadShortArray-9zorpBc  reason: not valid java name */
    public static final void m1629loadShortArray9zorpBc(ByteBuffer byteBuffer, int i, short[] sArr, int i2, int i3) {
        Intrinsics.checkNotNullParameter(byteBuffer, "$this$loadShortArray");
        Intrinsics.checkNotNullParameter(sArr, RtspHeaders.Values.DESTINATION);
        ByteBuffer duplicate = byteBuffer.duplicate();
        Intrinsics.checkNotNull(duplicate);
        duplicate.position(i);
        duplicate.asShortBuffer().get(sArr, i2, i3);
    }

    /* renamed from: loadIntArray-9zorpBc  reason: not valid java name */
    public static final void m1621loadIntArray9zorpBc(ByteBuffer byteBuffer, int i, int[] iArr, int i2, int i3) {
        Intrinsics.checkNotNullParameter(byteBuffer, "$this$loadIntArray");
        Intrinsics.checkNotNullParameter(iArr, RtspHeaders.Values.DESTINATION);
        ByteBuffer duplicate = byteBuffer.duplicate();
        Intrinsics.checkNotNull(duplicate);
        duplicate.position(i);
        duplicate.asIntBuffer().get(iArr, i2, i3);
    }

    /* renamed from: loadLongArray-9zorpBc  reason: not valid java name */
    public static final void m1625loadLongArray9zorpBc(ByteBuffer byteBuffer, int i, long[] jArr, int i2, int i3) {
        Intrinsics.checkNotNullParameter(byteBuffer, "$this$loadLongArray");
        Intrinsics.checkNotNullParameter(jArr, RtspHeaders.Values.DESTINATION);
        ByteBuffer duplicate = byteBuffer.duplicate();
        Intrinsics.checkNotNull(duplicate);
        duplicate.position(i);
        duplicate.asLongBuffer().get(jArr, i2, i3);
    }

    /* renamed from: loadFloatArray-9zorpBc  reason: not valid java name */
    public static final void m1617loadFloatArray9zorpBc(ByteBuffer byteBuffer, int i, float[] fArr, int i2, int i3) {
        Intrinsics.checkNotNullParameter(byteBuffer, "$this$loadFloatArray");
        Intrinsics.checkNotNullParameter(fArr, RtspHeaders.Values.DESTINATION);
        ByteBuffer duplicate = byteBuffer.duplicate();
        Intrinsics.checkNotNull(duplicate);
        duplicate.position(i);
        duplicate.asFloatBuffer().get(fArr, i2, i3);
    }

    /* renamed from: loadDoubleArray-9zorpBc  reason: not valid java name */
    public static final void m1613loadDoubleArray9zorpBc(ByteBuffer byteBuffer, int i, double[] dArr, int i2, int i3) {
        Intrinsics.checkNotNullParameter(byteBuffer, "$this$loadDoubleArray");
        Intrinsics.checkNotNullParameter(dArr, RtspHeaders.Values.DESTINATION);
        ByteBuffer duplicate = byteBuffer.duplicate();
        Intrinsics.checkNotNull(duplicate);
        duplicate.position(i);
        duplicate.asDoubleBuffer().get(dArr, i2, i3);
    }

    /* renamed from: storeShortArray-9zorpBc  reason: not valid java name */
    public static final void m1649storeShortArray9zorpBc(ByteBuffer byteBuffer, int i, short[] sArr, int i2, int i3) {
        Intrinsics.checkNotNullParameter(byteBuffer, "$this$storeShortArray");
        Intrinsics.checkNotNullParameter(sArr, "source");
        ByteBuffer duplicate = byteBuffer.duplicate();
        Intrinsics.checkNotNull(duplicate);
        duplicate.position(i);
        duplicate.asShortBuffer().put(sArr, i2, i3);
    }

    /* renamed from: storeIntArray-9zorpBc  reason: not valid java name */
    public static final void m1641storeIntArray9zorpBc(ByteBuffer byteBuffer, int i, int[] iArr, int i2, int i3) {
        Intrinsics.checkNotNullParameter(byteBuffer, "$this$storeIntArray");
        Intrinsics.checkNotNullParameter(iArr, "source");
        ByteBuffer duplicate = byteBuffer.duplicate();
        Intrinsics.checkNotNull(duplicate);
        duplicate.position(i);
        duplicate.asIntBuffer().put(iArr, i2, i3);
    }

    /* renamed from: storeLongArray-9zorpBc  reason: not valid java name */
    public static final void m1645storeLongArray9zorpBc(ByteBuffer byteBuffer, int i, long[] jArr, int i2, int i3) {
        Intrinsics.checkNotNullParameter(byteBuffer, "$this$storeLongArray");
        Intrinsics.checkNotNullParameter(jArr, "source");
        ByteBuffer duplicate = byteBuffer.duplicate();
        Intrinsics.checkNotNull(duplicate);
        duplicate.position(i);
        duplicate.asLongBuffer().put(jArr, i2, i3);
    }

    /* renamed from: storeFloatArray-9zorpBc  reason: not valid java name */
    public static final void m1637storeFloatArray9zorpBc(ByteBuffer byteBuffer, int i, float[] fArr, int i2, int i3) {
        Intrinsics.checkNotNullParameter(byteBuffer, "$this$storeFloatArray");
        Intrinsics.checkNotNullParameter(fArr, "source");
        ByteBuffer duplicate = byteBuffer.duplicate();
        Intrinsics.checkNotNull(duplicate);
        duplicate.position(i);
        duplicate.asFloatBuffer().put(fArr, i2, i3);
    }

    /* renamed from: storeDoubleArray-9zorpBc  reason: not valid java name */
    public static final void m1633storeDoubleArray9zorpBc(ByteBuffer byteBuffer, int i, double[] dArr, int i2, int i3) {
        Intrinsics.checkNotNullParameter(byteBuffer, "$this$storeDoubleArray");
        Intrinsics.checkNotNullParameter(dArr, "source");
        ByteBuffer duplicate = byteBuffer.duplicate();
        Intrinsics.checkNotNull(duplicate);
        duplicate.position(i);
        duplicate.asDoubleBuffer().put(dArr, i2, i3);
    }
}
