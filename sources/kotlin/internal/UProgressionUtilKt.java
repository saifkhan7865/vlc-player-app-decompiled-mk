package kotlin.internal;

import kotlin.Metadata;
import kotlin.UInt;
import kotlin.UInt$$ExternalSyntheticBackport0;
import kotlin.ULong;

@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\u001a'\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0001H\u0002¢\u0006\u0004\b\u0005\u0010\u0006\u001a'\u0010\u0000\u001a\u00020\u00072\u0006\u0010\u0002\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u0007H\u0002¢\u0006\u0004\b\b\u0010\t\u001a'\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u000eH\u0001¢\u0006\u0004\b\u000f\u0010\u0006\u001a'\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u0010H\u0001¢\u0006\u0004\b\u0011\u0010\t¨\u0006\u0012"}, d2 = {"differenceModulo", "Lkotlin/UInt;", "a", "b", "c", "differenceModulo-WZ9TVnA", "(III)I", "Lkotlin/ULong;", "differenceModulo-sambcqE", "(JJJ)J", "getProgressionLastElement", "start", "end", "step", "", "getProgressionLastElement-Nkh28Cs", "", "getProgressionLastElement-7ftBX0g", "kotlin-stdlib"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: UProgressionUtil.kt */
public final class UProgressionUtilKt {
    /* renamed from: differenceModulo-WZ9TVnA  reason: not valid java name */
    private static final int m2192differenceModuloWZ9TVnA(int i, int i2, int i3) {
        int m$2 = UInt$$ExternalSyntheticBackport0.m$2(i, i3);
        int m$22 = UInt$$ExternalSyntheticBackport0.m$2(i2, i3);
        int m$9 = UInt$$ExternalSyntheticBackport0.m(m$2 ^ Integer.MIN_VALUE, m$22 ^ Integer.MIN_VALUE);
        int r1 = UInt.m1869constructorimpl(m$2 - m$22);
        return m$9 >= 0 ? r1 : UInt.m1869constructorimpl(r1 + i3);
    }

    /* renamed from: differenceModulo-sambcqE  reason: not valid java name */
    private static final long m2193differenceModulosambcqE(long j, long j2, long j3) {
        long m = UInt$$ExternalSyntheticBackport0.m(j, j3);
        long m2 = UInt$$ExternalSyntheticBackport0.m(j2, j3);
        int m3 = UInt$$ExternalSyntheticBackport0.m(m, m2);
        long r1 = ULong.m1948constructorimpl(m - m2);
        return m3 >= 0 ? r1 : ULong.m1948constructorimpl(r1 + j3);
    }

    /* renamed from: getProgressionLastElement-Nkh28Cs  reason: not valid java name */
    public static final int m2195getProgressionLastElementNkh28Cs(int i, int i2, int i3) {
        if (i3 > 0) {
            if (UInt$$ExternalSyntheticBackport0.m(i ^ Integer.MIN_VALUE, i2 ^ Integer.MIN_VALUE) >= 0) {
                return i2;
            }
            return UInt.m1869constructorimpl(i2 - m2192differenceModuloWZ9TVnA(i2, i, UInt.m1869constructorimpl(i3)));
        } else if (i3 < 0) {
            return UInt$$ExternalSyntheticBackport0.m(i ^ Integer.MIN_VALUE, i2 ^ Integer.MIN_VALUE) <= 0 ? i2 : UInt.m1869constructorimpl(i2 + m2192differenceModuloWZ9TVnA(i, i2, UInt.m1869constructorimpl(-i3)));
        } else {
            throw new IllegalArgumentException("Step is zero.");
        }
    }

    /* renamed from: getProgressionLastElement-7ftBX0g  reason: not valid java name */
    public static final long m2194getProgressionLastElement7ftBX0g(long j, long j2, long j3) {
        if (j3 > 0) {
            if (UInt$$ExternalSyntheticBackport0.m(j, j2) >= 0) {
                return j2;
            }
            return ULong.m1948constructorimpl(j2 - m2193differenceModulosambcqE(j2, j, ULong.m1948constructorimpl(j3)));
        } else if (j3 >= 0) {
            throw new IllegalArgumentException("Step is zero.");
        } else if (UInt$$ExternalSyntheticBackport0.m(j, j2) <= 0) {
            return j2;
        } else {
            return ULong.m1948constructorimpl(j2 + m2193differenceModulosambcqE(j, j2, ULong.m1948constructorimpl(-j3)));
        }
    }
}
