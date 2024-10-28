package io.ktor.utils.io.bits;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import io.ktor.utils.io.core.internal.NumbersKt;
import java.nio.ByteBuffer;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00004\n\u0000\n\u0002\u0010\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\f\n\u0002\u0010\n\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0015\u001a\"\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0005\u0010\u0006\u001a\"\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0007H\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0005\u0010\b\u001a\"\u0010\t\u001a\u00020\n*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u000b\u0010\f\u001a\"\u0010\t\u001a\u00020\n*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0007H\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u000b\u0010\r\u001a\"\u0010\u000e\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u000f\u0010\u0010\u001a\"\u0010\u000e\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0007H\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u000f\u0010\u0011\u001a\"\u0010\u0012\u001a\u00020\u0007*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0013\u0010\u0014\u001a\"\u0010\u0012\u001a\u00020\u0007*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0007H\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0013\u0010\u0015\u001a\"\u0010\u0016\u001a\u00020\u0017*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0018\u0010\u0019\u001a\"\u0010\u0016\u001a\u00020\u0017*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0007H\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0018\u0010\u001a\u001a*\u0010\u001b\u001a\u00020\u001c*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u0001H\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001e\u0010\u001f\u001a*\u0010\u001b\u001a\u00020\u001c*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\u0001H\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001e\u0010 \u001a*\u0010!\u001a\u00020\u001c*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\nH\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\"\u0010#\u001a*\u0010!\u001a\u00020\u001c*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\nH\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\"\u0010$\u001a*\u0010%\u001a\u00020\u001c*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u0004H\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b&\u0010'\u001a*\u0010%\u001a\u00020\u001c*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\u0004H\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b&\u0010(\u001a*\u0010)\u001a\u00020\u001c*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u0007H\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b*\u0010+\u001a*\u0010)\u001a\u00020\u001c*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\u0007H\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b*\u0010,\u001a*\u0010-\u001a\u00020\u001c*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u0017H\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b.\u0010/\u001a*\u0010-\u001a\u00020\u001c*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\u0017H\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b.\u00100\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u00061"}, d2 = {"loadDoubleAt", "", "Lio/ktor/utils/io/bits/Memory;", "offset", "", "loadDoubleAt-eY85DW0", "(Ljava/nio/ByteBuffer;I)D", "", "(Ljava/nio/ByteBuffer;J)D", "loadFloatAt", "", "loadFloatAt-eY85DW0", "(Ljava/nio/ByteBuffer;I)F", "(Ljava/nio/ByteBuffer;J)F", "loadIntAt", "loadIntAt-eY85DW0", "(Ljava/nio/ByteBuffer;I)I", "(Ljava/nio/ByteBuffer;J)I", "loadLongAt", "loadLongAt-eY85DW0", "(Ljava/nio/ByteBuffer;I)J", "(Ljava/nio/ByteBuffer;J)J", "loadShortAt", "", "loadShortAt-eY85DW0", "(Ljava/nio/ByteBuffer;I)S", "(Ljava/nio/ByteBuffer;J)S", "storeDoubleAt", "", "value", "storeDoubleAt-62zg_DM", "(Ljava/nio/ByteBuffer;ID)V", "(Ljava/nio/ByteBuffer;JD)V", "storeFloatAt", "storeFloatAt-62zg_DM", "(Ljava/nio/ByteBuffer;IF)V", "(Ljava/nio/ByteBuffer;JF)V", "storeIntAt", "storeIntAt-62zg_DM", "(Ljava/nio/ByteBuffer;II)V", "(Ljava/nio/ByteBuffer;JI)V", "storeLongAt", "storeLongAt-62zg_DM", "(Ljava/nio/ByteBuffer;IJ)V", "(Ljava/nio/ByteBuffer;JJ)V", "storeShortAt", "storeShortAt-62zg_DM", "(Ljava/nio/ByteBuffer;IS)V", "(Ljava/nio/ByteBuffer;JS)V", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: MemoryPrimitivesJvm.kt */
public final class MemoryPrimitivesJvmKt {
    /* renamed from: loadShortAt-eY85DW0  reason: not valid java name */
    public static final short m1549loadShortAteY85DW0(ByteBuffer byteBuffer, int i) {
        Intrinsics.checkNotNullParameter(byteBuffer, "$this$loadShortAt");
        return byteBuffer.getShort(i);
    }

    /* renamed from: loadShortAt-eY85DW0  reason: not valid java name */
    public static final short m1550loadShortAteY85DW0(ByteBuffer byteBuffer, long j) {
        Intrinsics.checkNotNullParameter(byteBuffer, "$this$loadShortAt");
        if (j < 2147483647L) {
            return byteBuffer.getShort((int) j);
        }
        NumbersKt.failLongToIntConversion(j, TypedValues.CycleType.S_WAVE_OFFSET);
        throw new KotlinNothingValueException();
    }

    /* renamed from: loadIntAt-eY85DW0  reason: not valid java name */
    public static final int m1545loadIntAteY85DW0(ByteBuffer byteBuffer, int i) {
        Intrinsics.checkNotNullParameter(byteBuffer, "$this$loadIntAt");
        return byteBuffer.getInt(i);
    }

    /* renamed from: loadIntAt-eY85DW0  reason: not valid java name */
    public static final int m1546loadIntAteY85DW0(ByteBuffer byteBuffer, long j) {
        Intrinsics.checkNotNullParameter(byteBuffer, "$this$loadIntAt");
        if (j < 2147483647L) {
            return byteBuffer.getInt((int) j);
        }
        NumbersKt.failLongToIntConversion(j, TypedValues.CycleType.S_WAVE_OFFSET);
        throw new KotlinNothingValueException();
    }

    /* renamed from: loadLongAt-eY85DW0  reason: not valid java name */
    public static final long m1547loadLongAteY85DW0(ByteBuffer byteBuffer, int i) {
        Intrinsics.checkNotNullParameter(byteBuffer, "$this$loadLongAt");
        return byteBuffer.getLong(i);
    }

    /* renamed from: loadLongAt-eY85DW0  reason: not valid java name */
    public static final long m1548loadLongAteY85DW0(ByteBuffer byteBuffer, long j) {
        Intrinsics.checkNotNullParameter(byteBuffer, "$this$loadLongAt");
        if (j < 2147483647L) {
            return byteBuffer.getLong((int) j);
        }
        NumbersKt.failLongToIntConversion(j, TypedValues.CycleType.S_WAVE_OFFSET);
        throw new KotlinNothingValueException();
    }

    /* renamed from: loadFloatAt-eY85DW0  reason: not valid java name */
    public static final float m1543loadFloatAteY85DW0(ByteBuffer byteBuffer, int i) {
        Intrinsics.checkNotNullParameter(byteBuffer, "$this$loadFloatAt");
        return byteBuffer.getFloat(i);
    }

    /* renamed from: loadFloatAt-eY85DW0  reason: not valid java name */
    public static final float m1544loadFloatAteY85DW0(ByteBuffer byteBuffer, long j) {
        Intrinsics.checkNotNullParameter(byteBuffer, "$this$loadFloatAt");
        if (j < 2147483647L) {
            return byteBuffer.getFloat((int) j);
        }
        NumbersKt.failLongToIntConversion(j, TypedValues.CycleType.S_WAVE_OFFSET);
        throw new KotlinNothingValueException();
    }

    /* renamed from: loadDoubleAt-eY85DW0  reason: not valid java name */
    public static final double m1541loadDoubleAteY85DW0(ByteBuffer byteBuffer, int i) {
        Intrinsics.checkNotNullParameter(byteBuffer, "$this$loadDoubleAt");
        return byteBuffer.getDouble(i);
    }

    /* renamed from: loadDoubleAt-eY85DW0  reason: not valid java name */
    public static final double m1542loadDoubleAteY85DW0(ByteBuffer byteBuffer, long j) {
        Intrinsics.checkNotNullParameter(byteBuffer, "$this$loadDoubleAt");
        if (j < 2147483647L) {
            return byteBuffer.getDouble((int) j);
        }
        NumbersKt.failLongToIntConversion(j, TypedValues.CycleType.S_WAVE_OFFSET);
        throw new KotlinNothingValueException();
    }

    /* renamed from: storeIntAt-62zg_DM  reason: not valid java name */
    public static final void m1555storeIntAt62zg_DM(ByteBuffer byteBuffer, int i, int i2) {
        Intrinsics.checkNotNullParameter(byteBuffer, "$this$storeIntAt");
        byteBuffer.putInt(i, i2);
    }

    /* renamed from: storeShortAt-62zg_DM  reason: not valid java name */
    public static final void m1559storeShortAt62zg_DM(ByteBuffer byteBuffer, int i, short s) {
        Intrinsics.checkNotNullParameter(byteBuffer, "$this$storeShortAt");
        byteBuffer.putShort(i, s);
    }

    /* renamed from: storeLongAt-62zg_DM  reason: not valid java name */
    public static final void m1557storeLongAt62zg_DM(ByteBuffer byteBuffer, int i, long j) {
        Intrinsics.checkNotNullParameter(byteBuffer, "$this$storeLongAt");
        byteBuffer.putLong(i, j);
    }

    /* renamed from: storeFloatAt-62zg_DM  reason: not valid java name */
    public static final void m1553storeFloatAt62zg_DM(ByteBuffer byteBuffer, int i, float f) {
        Intrinsics.checkNotNullParameter(byteBuffer, "$this$storeFloatAt");
        byteBuffer.putFloat(i, f);
    }

    /* renamed from: storeDoubleAt-62zg_DM  reason: not valid java name */
    public static final void m1551storeDoubleAt62zg_DM(ByteBuffer byteBuffer, int i, double d) {
        Intrinsics.checkNotNullParameter(byteBuffer, "$this$storeDoubleAt");
        byteBuffer.putDouble(i, d);
    }

    /* renamed from: storeIntAt-62zg_DM  reason: not valid java name */
    public static final void m1556storeIntAt62zg_DM(ByteBuffer byteBuffer, long j, int i) {
        Intrinsics.checkNotNullParameter(byteBuffer, "$this$storeIntAt");
        if (j < 2147483647L) {
            byteBuffer.putInt((int) j, i);
        } else {
            NumbersKt.failLongToIntConversion(j, TypedValues.CycleType.S_WAVE_OFFSET);
            throw new KotlinNothingValueException();
        }
    }

    /* renamed from: storeShortAt-62zg_DM  reason: not valid java name */
    public static final void m1560storeShortAt62zg_DM(ByteBuffer byteBuffer, long j, short s) {
        Intrinsics.checkNotNullParameter(byteBuffer, "$this$storeShortAt");
        if (j < 2147483647L) {
            byteBuffer.putShort((int) j, s);
        } else {
            NumbersKt.failLongToIntConversion(j, TypedValues.CycleType.S_WAVE_OFFSET);
            throw new KotlinNothingValueException();
        }
    }

    /* renamed from: storeLongAt-62zg_DM  reason: not valid java name */
    public static final void m1558storeLongAt62zg_DM(ByteBuffer byteBuffer, long j, long j2) {
        Intrinsics.checkNotNullParameter(byteBuffer, "$this$storeLongAt");
        if (j < 2147483647L) {
            byteBuffer.putLong((int) j, j2);
        } else {
            NumbersKt.failLongToIntConversion(j, TypedValues.CycleType.S_WAVE_OFFSET);
            throw new KotlinNothingValueException();
        }
    }

    /* renamed from: storeFloatAt-62zg_DM  reason: not valid java name */
    public static final void m1554storeFloatAt62zg_DM(ByteBuffer byteBuffer, long j, float f) {
        Intrinsics.checkNotNullParameter(byteBuffer, "$this$storeFloatAt");
        if (j < 2147483647L) {
            byteBuffer.putFloat((int) j, f);
        } else {
            NumbersKt.failLongToIntConversion(j, TypedValues.CycleType.S_WAVE_OFFSET);
            throw new KotlinNothingValueException();
        }
    }

    /* renamed from: storeDoubleAt-62zg_DM  reason: not valid java name */
    public static final void m1552storeDoubleAt62zg_DM(ByteBuffer byteBuffer, long j, double d) {
        Intrinsics.checkNotNullParameter(byteBuffer, "$this$storeDoubleAt");
        if (j < 2147483647L) {
            byteBuffer.putDouble((int) j, d);
        } else {
            NumbersKt.failLongToIntConversion(j, TypedValues.CycleType.S_WAVE_OFFSET);
            throw new KotlinNothingValueException();
        }
    }
}
