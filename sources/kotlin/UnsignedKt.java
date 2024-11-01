package kotlin;

import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;

@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a\u0015\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0001¢\u0006\u0002\u0010\u0004\u001a\u0015\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0002\u001a\u00020\u0003H\u0001¢\u0006\u0002\u0010\u0007\u001a\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\tH\u0001\u001a\u001f\u0010\f\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\u0001H\u0001¢\u0006\u0004\b\r\u0010\u000e\u001a\u001f\u0010\u000f\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\u0001H\u0001¢\u0006\u0004\b\u0010\u0010\u000e\u001a\u0010\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0002\u001a\u00020\tH\u0001\u001a\u0018\u0010\u0012\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00132\u0006\u0010\u000b\u001a\u00020\u0013H\u0001\u001a\u001f\u0010\u0014\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0006H\u0001¢\u0006\u0004\b\u0015\u0010\u0016\u001a\u001f\u0010\u0017\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0006H\u0001¢\u0006\u0004\b\u0018\u0010\u0016\u001a\u0010\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0013H\u0001\u001a\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0002\u001a\u00020\u0013H\u0000\u001a\u0018\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0002\u001a\u00020\u00132\u0006\u0010\u001c\u001a\u00020\tH\u0000¨\u0006\u001d"}, d2 = {"doubleToUInt", "Lkotlin/UInt;", "v", "", "(D)I", "doubleToULong", "Lkotlin/ULong;", "(D)J", "uintCompare", "", "v1", "v2", "uintDivide", "uintDivide-J1ME1BU", "(II)I", "uintRemainder", "uintRemainder-J1ME1BU", "uintToDouble", "ulongCompare", "", "ulongDivide", "ulongDivide-eb3DHEI", "(JJ)J", "ulongRemainder", "ulongRemainder-eb3DHEI", "ulongToDouble", "ulongToString", "", "base", "kotlin-stdlib"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: UnsignedUtils.kt */
public final class UnsignedKt {
    public static final int uintCompare(int i, int i2) {
        return Intrinsics.compare(i ^ Integer.MIN_VALUE, i2 ^ Integer.MIN_VALUE);
    }

    public static final int ulongCompare(long j, long j2) {
        return Intrinsics.compare(j ^ Long.MIN_VALUE, j2 ^ Long.MIN_VALUE);
    }

    /* renamed from: uintDivide-J1ME1BU  reason: not valid java name */
    public static final int m2125uintDivideJ1ME1BU(int i, int i2) {
        return UInt.m1869constructorimpl((int) ((((long) i) & 4294967295L) / (((long) i2) & 4294967295L)));
    }

    /* renamed from: uintRemainder-J1ME1BU  reason: not valid java name */
    public static final int m2126uintRemainderJ1ME1BU(int i, int i2) {
        return UInt.m1869constructorimpl((int) ((((long) i) & 4294967295L) % (((long) i2) & 4294967295L)));
    }

    /* renamed from: ulongDivide-eb3DHEI  reason: not valid java name */
    public static final long m2127ulongDivideeb3DHEI(long j, long j2) {
        if (j2 < 0) {
            return UInt$$ExternalSyntheticBackport0.m(j, j2) < 0 ? ULong.m1948constructorimpl(0) : ULong.m1948constructorimpl(1);
        }
        if (j >= 0) {
            return ULong.m1948constructorimpl(j / j2);
        }
        int i = 1;
        long j3 = ((j >>> 1) / j2) << 1;
        if (UInt$$ExternalSyntheticBackport0.m(ULong.m1948constructorimpl(j - (j3 * j2)), ULong.m1948constructorimpl(j2)) < 0) {
            i = 0;
        }
        return ULong.m1948constructorimpl(j3 + ((long) i));
    }

    /* renamed from: ulongRemainder-eb3DHEI  reason: not valid java name */
    public static final long m2128ulongRemaindereb3DHEI(long j, long j2) {
        if (j2 < 0) {
            return UInt$$ExternalSyntheticBackport0.m(j, j2) < 0 ? j : ULong.m1948constructorimpl(j - j2);
        }
        if (j >= 0) {
            return ULong.m1948constructorimpl(j % j2);
        }
        long j3 = j - ((((j >>> 1) / j2) << 1) * j2);
        if (UInt$$ExternalSyntheticBackport0.m(ULong.m1948constructorimpl(j3), ULong.m1948constructorimpl(j2)) < 0) {
            j2 = 0;
        }
        return ULong.m1948constructorimpl(j3 - j2);
    }

    public static final int doubleToUInt(double d) {
        if (Double.isNaN(d) || d <= uintToDouble(0)) {
            return 0;
        }
        if (d >= uintToDouble(-1)) {
            return -1;
        }
        if (d <= 2.147483647E9d) {
            return UInt.m1869constructorimpl((int) d);
        }
        double d2 = (double) Integer.MAX_VALUE;
        Double.isNaN(d2);
        return UInt.m1869constructorimpl(UInt.m1869constructorimpl((int) (d - d2)) + UInt.m1869constructorimpl(Integer.MAX_VALUE));
    }

    public static final long doubleToULong(double d) {
        if (Double.isNaN(d) || d <= ulongToDouble(0)) {
            return 0;
        }
        if (d >= ulongToDouble(-1)) {
            return -1;
        }
        if (d < 9.223372036854776E18d) {
            return ULong.m1948constructorimpl((long) d);
        }
        return ULong.m1948constructorimpl(ULong.m1948constructorimpl((long) (d - 9.223372036854776E18d)) - Long.MIN_VALUE);
    }

    public static final double uintToDouble(int i) {
        double d = (double) (Integer.MAX_VALUE & i);
        double d2 = (double) ((i >>> 31) << 30);
        double d3 = (double) 2;
        Double.isNaN(d2);
        Double.isNaN(d3);
        Double.isNaN(d);
        return d + (d2 * d3);
    }

    public static final double ulongToDouble(long j) {
        double d = (double) (j >>> 11);
        double d2 = (double) 2048;
        Double.isNaN(d);
        Double.isNaN(d2);
        double d3 = (double) (j & 2047);
        Double.isNaN(d3);
        return (d * d2) + d3;
    }

    public static final String ulongToString(long j) {
        return ulongToString(j, 10);
    }

    public static final String ulongToString(long j, int i) {
        if (j >= 0) {
            String l = Long.toString(j, CharsKt.checkRadix(i));
            Intrinsics.checkNotNullExpressionValue(l, "toString(...)");
            return l;
        }
        long j2 = (long) i;
        long j3 = ((j >>> 1) / j2) << 1;
        long j4 = j - (j3 * j2);
        if (j4 >= j2) {
            j4 -= j2;
            j3++;
        }
        StringBuilder sb = new StringBuilder();
        String l2 = Long.toString(j3, CharsKt.checkRadix(i));
        Intrinsics.checkNotNullExpressionValue(l2, "toString(...)");
        sb.append(l2);
        String l3 = Long.toString(j4, CharsKt.checkRadix(i));
        Intrinsics.checkNotNullExpressionValue(l3, "toString(...)");
        sb.append(l3);
        return sb.toString();
    }
}
