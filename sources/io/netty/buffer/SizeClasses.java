package io.netty.buffer;

import java.lang.reflect.Array;

abstract class SizeClasses implements SizeClassesMetric {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int INDEX_IDX = 0;
    private static final int LOG2DELTA_IDX = 2;
    private static final int LOG2GROUP_IDX = 1;
    private static final int LOG2_DELTA_LOOKUP_IDX = 6;
    private static final int LOG2_MAX_LOOKUP_SIZE = 12;
    static final int LOG2_QUANTUM = 4;
    private static final int LOG2_SIZE_CLASS_GROUP = 2;
    private static final int NDELTA_IDX = 3;
    private static final int PAGESIZE_IDX = 4;
    private static final int SUBPAGE_IDX = 5;
    private static final byte no = 0;
    private static final byte yes = 1;
    protected final int chunkSize;
    protected final int directMemoryCacheAlignment;
    final int lookupMaxSize;
    final int nPSizes;
    final int nSizes;
    final int nSubpages;
    private final int[] pageIdx2sizeTab;
    protected final int pageShifts;
    protected final int pageSize;
    private final int[] size2idxTab;
    private final int[] sizeIdx2sizeTab;
    final int smallMaxSizeIdx;

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0003, code lost:
        r0 = (r2 - 1) & r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int alignSizeIfNeeded(int r1, int r2) {
        /*
            if (r2 > 0) goto L_0x0003
            return r1
        L_0x0003:
            int r0 = r2 + -1
            r0 = r0 & r1
            if (r0 != 0) goto L_0x0009
            goto L_0x000b
        L_0x0009:
            int r1 = r1 + r2
            int r1 = r1 - r0
        L_0x000b:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.buffer.SizeClasses.alignSizeIfNeeded(int, int):int");
    }

    private static int calculateSize(int i, int i2, int i3) {
        return (1 << i) + (i2 << i3);
    }

    public int sizeIdx2sizeCompute(int i) {
        int i2 = i >> 2;
        int i3 = i & 3;
        int i4 = i2 == 0 ? 0 : 32 << i2;
        if (i2 == 0) {
            i2 = 1;
        }
        return i4 + ((i3 + 1) << (i2 + 3));
    }

    protected SizeClasses(int i, int i2, int i3, int i4) {
        int i5 = i2;
        int i6 = i3;
        int i7 = i4;
        int[] iArr = new int[2];
        iArr[1] = 7;
        iArr[0] = (PoolThreadCache.log2(i3) - 5) << 2;
        short[][] sArr = (short[][]) Array.newInstance(Short.TYPE, iArr);
        int i8 = 0;
        int i9 = 0;
        int i10 = 0;
        while (i8 < 4) {
            short[] newSizeClass = newSizeClass(i9, 4, 4, i8, i5);
            sArr[i9] = newSizeClass;
            i10 = sizeOf(newSizeClass, i7);
            i8++;
            i9++;
        }
        int i11 = 6;
        int i12 = 4;
        while (i10 < i6) {
            int i13 = 1;
            while (i13 <= 4 && i10 < i6) {
                short[] newSizeClass2 = newSizeClass(i9, i11, i12, i13, i5);
                sArr[i9] = newSizeClass2;
                i10 = sizeOf(newSizeClass2, i7);
                i13++;
                i9++;
            }
            i11++;
            i12++;
        }
        int i14 = 0;
        int i15 = 0;
        int i16 = 0;
        int i17 = 0;
        for (int i18 = 0; i18 < i9; i18++) {
            short[] sArr2 = sArr[i18];
            i16 = sArr2[4] == 1 ? i16 + 1 : i16;
            if (sArr2[5] == 1) {
                i17++;
                i14 = i18;
            }
            if (sArr2[6] != 0) {
                i15 = sizeOf(sArr2, i7);
            }
        }
        this.smallMaxSizeIdx = i14;
        this.lookupMaxSize = i15;
        this.nPSizes = i16;
        this.nSubpages = i17;
        this.nSizes = i9;
        this.pageSize = i;
        this.pageShifts = i5;
        this.chunkSize = i6;
        this.directMemoryCacheAlignment = i7;
        this.sizeIdx2sizeTab = newIdx2SizeTab(sArr, i9, i7);
        this.pageIdx2sizeTab = newPageIdx2sizeTab(sArr, i9, i16, i7);
        this.size2idxTab = newSize2idxTab(i15, sArr);
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0020  */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0022  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0026  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0029  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x002c  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0031  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0033  */
    /* JADX WARNING: Removed duplicated region for block: B:7:0x0016  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0018  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static short[] newSizeClass(int r7, int r8, int r9, int r10, int r11) {
        /*
            r0 = 0
            r1 = 1
            if (r9 < r11) goto L_0x0006
        L_0x0004:
            r2 = 1
            goto L_0x0014
        L_0x0006:
            int r2 = r1 << r11
            int r3 = calculateSize(r8, r10, r9)
            int r4 = r3 / r2
            int r4 = r4 * r2
            if (r3 != r4) goto L_0x0013
            goto L_0x0004
        L_0x0013:
            r2 = 0
        L_0x0014:
            if (r10 != 0) goto L_0x0018
            r3 = 0
            goto L_0x001c
        L_0x0018:
            int r3 = io.netty.buffer.PoolThreadCache.log2(r10)
        L_0x001c:
            int r4 = r1 << r3
            if (r4 >= r10) goto L_0x0022
            r4 = 1
            goto L_0x0023
        L_0x0022:
            r4 = 0
        L_0x0023:
            int r3 = r3 + r9
            if (r3 != r8) goto L_0x0029
            int r3 = r8 + 1
            goto L_0x002a
        L_0x0029:
            r3 = r8
        L_0x002a:
            if (r3 != r8) goto L_0x002d
            r4 = 1
        L_0x002d:
            r5 = 2
            int r11 = r11 + r5
            if (r3 >= r11) goto L_0x0033
            r11 = 1
            goto L_0x0034
        L_0x0033:
            r11 = 0
        L_0x0034:
            r6 = 12
            if (r3 < r6) goto L_0x003f
            if (r3 != r6) goto L_0x003d
            if (r4 != 0) goto L_0x003d
            goto L_0x003f
        L_0x003d:
            r3 = 0
            goto L_0x0040
        L_0x003f:
            r3 = r9
        L_0x0040:
            short r7 = (short) r7
            short r8 = (short) r8
            short r9 = (short) r9
            short r10 = (short) r10
            short r3 = (short) r3
            r4 = 7
            short[] r4 = new short[r4]
            r4[r0] = r7
            r4[r1] = r8
            r4[r5] = r9
            r7 = 3
            r4[r7] = r10
            r7 = 4
            r4[r7] = r2
            r7 = 5
            r4[r7] = r11
            r7 = 6
            r4[r7] = r3
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.buffer.SizeClasses.newSizeClass(int, int, int, int, int):short[]");
    }

    private static int[] newIdx2SizeTab(short[][] sArr, int i, int i2) {
        int[] iArr = new int[i];
        for (int i3 = 0; i3 < i; i3++) {
            iArr[i3] = sizeOf(sArr[i3], i2);
        }
        return iArr;
    }

    private static int sizeOf(short[] sArr, int i) {
        return alignSizeIfNeeded(calculateSize(sArr[1], sArr[3], sArr[2]), i);
    }

    private static int[] newPageIdx2sizeTab(short[][] sArr, int i, int i2, int i3) {
        int[] iArr = new int[i2];
        int i4 = 0;
        for (int i5 = 0; i5 < i; i5++) {
            short[] sArr2 = sArr[i5];
            if (sArr2[4] == 1) {
                iArr[i4] = sizeOf(sArr2, i3);
                i4++;
            }
        }
        return iArr;
    }

    private static int[] newSize2idxTab(int i, short[][] sArr) {
        int[] iArr = new int[(i >> 4)];
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i2 <= i) {
            int i5 = 1 << (sArr[i3][2] - 4);
            while (i2 <= i) {
                int i6 = i5 - 1;
                if (i5 <= 0) {
                    break;
                }
                iArr[i4] = i3;
                i5 = i6;
                int i7 = (i4 + 2) << 4;
                i4++;
                i2 = i7;
            }
            i3++;
        }
        return iArr;
    }

    public int sizeIdx2size(int i) {
        return this.sizeIdx2sizeTab[i];
    }

    public long pageIdx2size(int i) {
        return (long) this.pageIdx2sizeTab[i];
    }

    public long pageIdx2sizeCompute(int i) {
        int i2 = i >> 2;
        int i3 = i & 3;
        long j = i2 == 0 ? 0 : (1 << (this.pageShifts + 1)) << i2;
        if (i2 == 0) {
            i2 = 1;
        }
        return j + ((long) ((i3 + 1) << ((i2 + this.pageShifts) - 1)));
    }

    public int size2SizeIdx(int i) {
        int i2 = 0;
        if (i == 0) {
            return 0;
        }
        if (i > this.chunkSize) {
            return this.nSizes;
        }
        int alignSizeIfNeeded = alignSizeIfNeeded(i, this.directMemoryCacheAlignment);
        int i3 = 4;
        if (alignSizeIfNeeded <= this.lookupMaxSize) {
            return this.size2idxTab[(alignSizeIfNeeded - 1) >> 4];
        }
        int log2 = PoolThreadCache.log2((alignSizeIfNeeded << 1) - 1);
        if (log2 >= 7) {
            i2 = log2 - 6;
        }
        int i4 = i2 << 2;
        if (log2 >= 7) {
            i3 = log2 - 3;
        }
        return i4 + (((alignSizeIfNeeded - 1) >> i3) & 3);
    }

    public int pages2pageIdx(int i) {
        return pages2pageIdxCompute(i, false);
    }

    public int pages2pageIdxFloor(int i) {
        return pages2pageIdxCompute(i, true);
    }

    private int pages2pageIdxCompute(int i, boolean z) {
        int i2 = i << this.pageShifts;
        if (i2 > this.chunkSize) {
            return this.nPSizes;
        }
        int log2 = PoolThreadCache.log2((i2 << 1) - 1);
        int i3 = this.pageShifts;
        int i4 = ((log2 < i3 + 2 ? 0 : log2 - (i3 + 2)) << 2) + (((i2 - 1) >> (log2 < i3 + 3 ? i3 : log2 - 3)) & 3);
        return (!z || this.pageIdx2sizeTab[i4] <= (i << i3)) ? i4 : i4 - 1;
    }

    public int normalizeSize(int i) {
        if (i == 0) {
            return this.sizeIdx2sizeTab[0];
        }
        int alignSizeIfNeeded = alignSizeIfNeeded(i, this.directMemoryCacheAlignment);
        if (alignSizeIfNeeded <= this.lookupMaxSize) {
            return this.sizeIdx2sizeTab[this.size2idxTab[(alignSizeIfNeeded - 1) >> 4]];
        }
        return normalizeSizeCompute(alignSizeIfNeeded);
    }

    private static int normalizeSizeCompute(int i) {
        int log2 = PoolThreadCache.log2((i << 1) - 1);
        int i2 = (1 << (log2 < 7 ? 4 : log2 - 3)) - 1;
        return (i + i2) & (i2 ^ -1);
    }
}
