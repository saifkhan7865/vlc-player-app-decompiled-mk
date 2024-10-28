package kotlin.comparisons;

import kotlin.Metadata;
import kotlin.UByteArray;
import kotlin.UInt$$ExternalSyntheticBackport0;
import kotlin.UIntArray;
import kotlin.ULongArray;
import kotlin.UShortArray;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000B\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0010\u001a\u001f\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0007¢\u0006\u0004\b\u0004\u0010\u0005\u001a(\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0001H\b¢\u0006\u0004\b\u0007\u0010\b\u001a#\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\n\u0010\t\u001a\u00020\n\"\u00020\u0001H\u0007¢\u0006\u0004\b\u000b\u0010\f\u001a\u001f\u0010\u0000\u001a\u00020\r2\u0006\u0010\u0002\u001a\u00020\r2\u0006\u0010\u0003\u001a\u00020\rH\u0007¢\u0006\u0004\b\u000e\u0010\u000f\u001a(\u0010\u0000\u001a\u00020\r2\u0006\u0010\u0002\u001a\u00020\r2\u0006\u0010\u0003\u001a\u00020\r2\u0006\u0010\u0006\u001a\u00020\rH\b¢\u0006\u0004\b\u0010\u0010\u0011\u001a#\u0010\u0000\u001a\u00020\r2\u0006\u0010\u0002\u001a\u00020\r2\n\u0010\t\u001a\u00020\u0012\"\u00020\rH\u0007¢\u0006\u0004\b\u0013\u0010\u0014\u001a\u001f\u0010\u0000\u001a\u00020\u00152\u0006\u0010\u0002\u001a\u00020\u00152\u0006\u0010\u0003\u001a\u00020\u0015H\u0007¢\u0006\u0004\b\u0016\u0010\u0017\u001a(\u0010\u0000\u001a\u00020\u00152\u0006\u0010\u0002\u001a\u00020\u00152\u0006\u0010\u0003\u001a\u00020\u00152\u0006\u0010\u0006\u001a\u00020\u0015H\b¢\u0006\u0004\b\u0018\u0010\u0019\u001a#\u0010\u0000\u001a\u00020\u00152\u0006\u0010\u0002\u001a\u00020\u00152\n\u0010\t\u001a\u00020\u001a\"\u00020\u0015H\u0007¢\u0006\u0004\b\u001b\u0010\u001c\u001a\u001f\u0010\u0000\u001a\u00020\u001d2\u0006\u0010\u0002\u001a\u00020\u001d2\u0006\u0010\u0003\u001a\u00020\u001dH\u0007¢\u0006\u0004\b\u001e\u0010\u001f\u001a(\u0010\u0000\u001a\u00020\u001d2\u0006\u0010\u0002\u001a\u00020\u001d2\u0006\u0010\u0003\u001a\u00020\u001d2\u0006\u0010\u0006\u001a\u00020\u001dH\b¢\u0006\u0004\b \u0010!\u001a#\u0010\u0000\u001a\u00020\u001d2\u0006\u0010\u0002\u001a\u00020\u001d2\n\u0010\t\u001a\u00020\"\"\u00020\u001dH\u0007¢\u0006\u0004\b#\u0010$\u001a\u001f\u0010%\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0007¢\u0006\u0004\b&\u0010\u0005\u001a(\u0010%\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0001H\b¢\u0006\u0004\b'\u0010\b\u001a#\u0010%\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\n\u0010\t\u001a\u00020\n\"\u00020\u0001H\u0007¢\u0006\u0004\b(\u0010\f\u001a\u001f\u0010%\u001a\u00020\r2\u0006\u0010\u0002\u001a\u00020\r2\u0006\u0010\u0003\u001a\u00020\rH\u0007¢\u0006\u0004\b)\u0010\u000f\u001a(\u0010%\u001a\u00020\r2\u0006\u0010\u0002\u001a\u00020\r2\u0006\u0010\u0003\u001a\u00020\r2\u0006\u0010\u0006\u001a\u00020\rH\b¢\u0006\u0004\b*\u0010\u0011\u001a#\u0010%\u001a\u00020\r2\u0006\u0010\u0002\u001a\u00020\r2\n\u0010\t\u001a\u00020\u0012\"\u00020\rH\u0007¢\u0006\u0004\b+\u0010\u0014\u001a\u001f\u0010%\u001a\u00020\u00152\u0006\u0010\u0002\u001a\u00020\u00152\u0006\u0010\u0003\u001a\u00020\u0015H\u0007¢\u0006\u0004\b,\u0010\u0017\u001a(\u0010%\u001a\u00020\u00152\u0006\u0010\u0002\u001a\u00020\u00152\u0006\u0010\u0003\u001a\u00020\u00152\u0006\u0010\u0006\u001a\u00020\u0015H\b¢\u0006\u0004\b-\u0010\u0019\u001a#\u0010%\u001a\u00020\u00152\u0006\u0010\u0002\u001a\u00020\u00152\n\u0010\t\u001a\u00020\u001a\"\u00020\u0015H\u0007¢\u0006\u0004\b.\u0010\u001c\u001a\u001f\u0010%\u001a\u00020\u001d2\u0006\u0010\u0002\u001a\u00020\u001d2\u0006\u0010\u0003\u001a\u00020\u001dH\u0007¢\u0006\u0004\b/\u0010\u001f\u001a(\u0010%\u001a\u00020\u001d2\u0006\u0010\u0002\u001a\u00020\u001d2\u0006\u0010\u0003\u001a\u00020\u001d2\u0006\u0010\u0006\u001a\u00020\u001dH\b¢\u0006\u0004\b0\u0010!\u001a#\u0010%\u001a\u00020\u001d2\u0006\u0010\u0002\u001a\u00020\u001d2\n\u0010\t\u001a\u00020\"\"\u00020\u001dH\u0007¢\u0006\u0004\b1\u0010$¨\u00062"}, d2 = {"maxOf", "Lkotlin/UByte;", "a", "b", "maxOf-Kr8caGY", "(BB)B", "c", "maxOf-b33U2AM", "(BBB)B", "other", "Lkotlin/UByteArray;", "maxOf-Wr6uiD8", "(B[B)B", "Lkotlin/UInt;", "maxOf-J1ME1BU", "(II)I", "maxOf-WZ9TVnA", "(III)I", "Lkotlin/UIntArray;", "maxOf-Md2H83M", "(I[I)I", "Lkotlin/ULong;", "maxOf-eb3DHEI", "(JJ)J", "maxOf-sambcqE", "(JJJ)J", "Lkotlin/ULongArray;", "maxOf-R03FKyM", "(J[J)J", "Lkotlin/UShort;", "maxOf-5PvTz6A", "(SS)S", "maxOf-VKSA0NQ", "(SSS)S", "Lkotlin/UShortArray;", "maxOf-t1qELG4", "(S[S)S", "minOf", "minOf-Kr8caGY", "minOf-b33U2AM", "minOf-Wr6uiD8", "minOf-J1ME1BU", "minOf-WZ9TVnA", "minOf-Md2H83M", "minOf-eb3DHEI", "minOf-sambcqE", "minOf-R03FKyM", "minOf-5PvTz6A", "minOf-VKSA0NQ", "minOf-t1qELG4", "kotlin-stdlib"}, k = 5, mv = {1, 9, 0}, xi = 49, xs = "kotlin/comparisons/UComparisonsKt")
/* compiled from: _UComparisons.kt */
class UComparisonsKt___UComparisonsKt {
    /* renamed from: maxOf-J1ME1BU  reason: not valid java name */
    public static final int m2168maxOfJ1ME1BU(int i, int i2) {
        return UInt$$ExternalSyntheticBackport0.m(i ^ Integer.MIN_VALUE, i2 ^ Integer.MIN_VALUE) >= 0 ? i : i2;
    }

    /* renamed from: maxOf-eb3DHEI  reason: not valid java name */
    public static final long m2176maxOfeb3DHEI(long j, long j2) {
        return UInt$$ExternalSyntheticBackport0.m(j, j2) >= 0 ? j : j2;
    }

    /* renamed from: maxOf-Kr8caGY  reason: not valid java name */
    public static final byte m2169maxOfKr8caGY(byte b, byte b2) {
        return Intrinsics.compare((int) b & 255, (int) b2 & 255) >= 0 ? b : b2;
    }

    /* renamed from: maxOf-5PvTz6A  reason: not valid java name */
    public static final short m2167maxOf5PvTz6A(short s, short s2) {
        return Intrinsics.compare((int) s & 65535, (int) 65535 & s2) >= 0 ? s : s2;
    }

    /* renamed from: maxOf-WZ9TVnA  reason: not valid java name */
    private static final int m2173maxOfWZ9TVnA(int i, int i2, int i3) {
        return UComparisonsKt.m2168maxOfJ1ME1BU(i, UComparisonsKt.m2168maxOfJ1ME1BU(i2, i3));
    }

    /* renamed from: maxOf-sambcqE  reason: not valid java name */
    private static final long m2177maxOfsambcqE(long j, long j2, long j3) {
        return UComparisonsKt.m2176maxOfeb3DHEI(j, UComparisonsKt.m2176maxOfeb3DHEI(j2, j3));
    }

    /* renamed from: maxOf-b33U2AM  reason: not valid java name */
    private static final byte m2175maxOfb33U2AM(byte b, byte b2, byte b3) {
        return UComparisonsKt.m2169maxOfKr8caGY(b, UComparisonsKt.m2169maxOfKr8caGY(b2, b3));
    }

    /* renamed from: maxOf-VKSA0NQ  reason: not valid java name */
    private static final short m2172maxOfVKSA0NQ(short s, short s2, short s3) {
        return UComparisonsKt.m2167maxOf5PvTz6A(s, UComparisonsKt.m2167maxOf5PvTz6A(s2, s3));
    }

    /* renamed from: maxOf-Md2H83M  reason: not valid java name */
    public static final int m2170maxOfMd2H83M(int i, int... iArr) {
        Intrinsics.checkNotNullParameter(iArr, "other");
        int r0 = UIntArray.m1930getSizeimpl(iArr);
        for (int i2 = 0; i2 < r0; i2++) {
            i = UComparisonsKt.m2168maxOfJ1ME1BU(i, UIntArray.m1929getpVg5ArA(iArr, i2));
        }
        return i;
    }

    /* renamed from: maxOf-R03FKyM  reason: not valid java name */
    public static final long m2171maxOfR03FKyM(long j, long... jArr) {
        Intrinsics.checkNotNullParameter(jArr, "other");
        int r0 = ULongArray.m2009getSizeimpl(jArr);
        for (int i = 0; i < r0; i++) {
            j = UComparisonsKt.m2176maxOfeb3DHEI(j, ULongArray.m2008getsVKNKU(jArr, i));
        }
        return j;
    }

    /* renamed from: maxOf-Wr6uiD8  reason: not valid java name */
    public static final byte m2174maxOfWr6uiD8(byte b, byte... bArr) {
        Intrinsics.checkNotNullParameter(bArr, "other");
        int r0 = UByteArray.m1851getSizeimpl(bArr);
        for (int i = 0; i < r0; i++) {
            b = UComparisonsKt.m2169maxOfKr8caGY(b, UByteArray.m1850getw2LRezQ(bArr, i));
        }
        return b;
    }

    /* renamed from: maxOf-t1qELG4  reason: not valid java name */
    public static final short m2178maxOft1qELG4(short s, short... sArr) {
        Intrinsics.checkNotNullParameter(sArr, "other");
        int r0 = UShortArray.m2114getSizeimpl(sArr);
        for (int i = 0; i < r0; i++) {
            s = UComparisonsKt.m2167maxOf5PvTz6A(s, UShortArray.m2113getMh2AYeg(sArr, i));
        }
        return s;
    }

    /* renamed from: minOf-J1ME1BU  reason: not valid java name */
    public static final int m2180minOfJ1ME1BU(int i, int i2) {
        return UInt$$ExternalSyntheticBackport0.m(i ^ Integer.MIN_VALUE, i2 ^ Integer.MIN_VALUE) <= 0 ? i : i2;
    }

    /* renamed from: minOf-eb3DHEI  reason: not valid java name */
    public static final long m2188minOfeb3DHEI(long j, long j2) {
        return UInt$$ExternalSyntheticBackport0.m(j, j2) <= 0 ? j : j2;
    }

    /* renamed from: minOf-Kr8caGY  reason: not valid java name */
    public static final byte m2181minOfKr8caGY(byte b, byte b2) {
        return Intrinsics.compare((int) b & 255, (int) b2 & 255) <= 0 ? b : b2;
    }

    /* renamed from: minOf-5PvTz6A  reason: not valid java name */
    public static final short m2179minOf5PvTz6A(short s, short s2) {
        return Intrinsics.compare((int) s & 65535, (int) 65535 & s2) <= 0 ? s : s2;
    }

    /* renamed from: minOf-WZ9TVnA  reason: not valid java name */
    private static final int m2185minOfWZ9TVnA(int i, int i2, int i3) {
        return UComparisonsKt.m2180minOfJ1ME1BU(i, UComparisonsKt.m2180minOfJ1ME1BU(i2, i3));
    }

    /* renamed from: minOf-sambcqE  reason: not valid java name */
    private static final long m2189minOfsambcqE(long j, long j2, long j3) {
        return UComparisonsKt.m2188minOfeb3DHEI(j, UComparisonsKt.m2188minOfeb3DHEI(j2, j3));
    }

    /* renamed from: minOf-b33U2AM  reason: not valid java name */
    private static final byte m2187minOfb33U2AM(byte b, byte b2, byte b3) {
        return UComparisonsKt.m2181minOfKr8caGY(b, UComparisonsKt.m2181minOfKr8caGY(b2, b3));
    }

    /* renamed from: minOf-VKSA0NQ  reason: not valid java name */
    private static final short m2184minOfVKSA0NQ(short s, short s2, short s3) {
        return UComparisonsKt.m2179minOf5PvTz6A(s, UComparisonsKt.m2179minOf5PvTz6A(s2, s3));
    }

    /* renamed from: minOf-Md2H83M  reason: not valid java name */
    public static final int m2182minOfMd2H83M(int i, int... iArr) {
        Intrinsics.checkNotNullParameter(iArr, "other");
        int r0 = UIntArray.m1930getSizeimpl(iArr);
        for (int i2 = 0; i2 < r0; i2++) {
            i = UComparisonsKt.m2180minOfJ1ME1BU(i, UIntArray.m1929getpVg5ArA(iArr, i2));
        }
        return i;
    }

    /* renamed from: minOf-R03FKyM  reason: not valid java name */
    public static final long m2183minOfR03FKyM(long j, long... jArr) {
        Intrinsics.checkNotNullParameter(jArr, "other");
        int r0 = ULongArray.m2009getSizeimpl(jArr);
        for (int i = 0; i < r0; i++) {
            j = UComparisonsKt.m2188minOfeb3DHEI(j, ULongArray.m2008getsVKNKU(jArr, i));
        }
        return j;
    }

    /* renamed from: minOf-Wr6uiD8  reason: not valid java name */
    public static final byte m2186minOfWr6uiD8(byte b, byte... bArr) {
        Intrinsics.checkNotNullParameter(bArr, "other");
        int r0 = UByteArray.m1851getSizeimpl(bArr);
        for (int i = 0; i < r0; i++) {
            b = UComparisonsKt.m2181minOfKr8caGY(b, UByteArray.m1850getw2LRezQ(bArr, i));
        }
        return b;
    }

    /* renamed from: minOf-t1qELG4  reason: not valid java name */
    public static final short m2190minOft1qELG4(short s, short... sArr) {
        Intrinsics.checkNotNullParameter(sArr, "other");
        int r0 = UShortArray.m2114getSizeimpl(sArr);
        for (int i = 0; i < r0; i++) {
            s = UComparisonsKt.m2179minOf5PvTz6A(s, UShortArray.m2113getMh2AYeg(sArr, i));
        }
        return s;
    }
}
