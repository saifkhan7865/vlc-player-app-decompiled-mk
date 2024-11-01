package io.netty.handler.codec.compression;

final class Bzip2HuffmanAllocator {
    private static int first(int[] iArr, int i, int i2) {
        int length = iArr.length;
        int length2 = iArr.length - 2;
        int i3 = i;
        while (i3 >= i2 && iArr[i3] % length > i) {
            length2 = i3;
            i3 -= (i - i3) + 1;
        }
        int max = Math.max(i2 - 1, i3);
        while (length2 > max + 1) {
            int i4 = (max + length2) >>> 1;
            if (iArr[i4] % length > i) {
                length2 = i4;
            } else {
                max = i4;
            }
        }
        return length2;
    }

    private static void setExtendedParentPointers(int[] iArr) {
        int i;
        int i2;
        int length = iArr.length;
        int i3 = 0;
        iArr[0] = iArr[0] + iArr[1];
        int i4 = 2;
        for (int i5 = 1; i5 < length - 1; i5++) {
            if (i4 >= length || iArr[i3] < (i = iArr[i4])) {
                i = iArr[i3];
                iArr[i3] = i5;
                i3++;
            } else {
                i4++;
            }
            if (i4 >= length || (i3 < i5 && iArr[i3] < iArr[i4])) {
                i2 = i + iArr[i3];
                iArr[i3] = i5 + length;
                i3++;
            } else {
                i2 = i + iArr[i4];
                i4++;
            }
            iArr[i5] = i2;
        }
    }

    private static int findNodesToRelocate(int[] iArr, int i) {
        int length = iArr.length - 2;
        for (int i2 = 1; i2 < i - 1 && length > 1; i2++) {
            length = first(iArr, length - 1, 0);
        }
        return length;
    }

    private static void allocateNodeLengths(int[] iArr) {
        int i = 2;
        int length = iArr.length - 2;
        int i2 = 1;
        int length2 = iArr.length - 1;
        while (i > 0) {
            int first = first(iArr, length - 1, 0);
            int i3 = length - first;
            int i4 = i - i3;
            while (i4 > 0) {
                iArr[length2] = i2;
                i4--;
                length2--;
            }
            i = i3 << 1;
            i2++;
            length = first;
        }
    }

    private static void allocateNodeLengthsWithRelocation(int[] iArr, int i, int i2) {
        int i3;
        int i4;
        int i5 = 2;
        int length = iArr.length - 2;
        int length2 = iArr.length - 1;
        if (i2 != 1) {
            i5 = 1;
        }
        int i6 = i2 == 1 ? i - 2 : i;
        int i7 = i5 << 1;
        while (i7 > 0) {
            if (length <= i) {
                i3 = length;
            } else {
                i3 = first(iArr, length - 1, i);
            }
            if (i5 >= i2) {
                i4 = Math.min(i6, 1 << (i5 - i2));
            } else if (i5 == i2 - 1) {
                if (iArr[i3] == length) {
                    i3++;
                }
                i4 = 1;
            } else {
                i4 = 0;
            }
            int i8 = (length - i3) + i4;
            int i9 = i7 - i8;
            while (i9 > 0) {
                iArr[length2] = i5;
                i9--;
                length2--;
            }
            i6 -= i4;
            i7 = i8 << 1;
            i5++;
            length = i3;
        }
    }

    static void allocateHuffmanCodeLengths(int[] iArr, int i) {
        int length = iArr.length;
        if (length != 1) {
            if (length != 2) {
                setExtendedParentPointers(iArr);
                int findNodesToRelocate = findNodesToRelocate(iArr, i);
                if (iArr[0] % iArr.length >= findNodesToRelocate) {
                    allocateNodeLengths(iArr);
                    return;
                } else {
                    allocateNodeLengthsWithRelocation(iArr, findNodesToRelocate, i - (32 - Integer.numberOfLeadingZeros(findNodesToRelocate - 1)));
                    return;
                }
            } else {
                iArr[1] = 1;
            }
        }
        iArr[0] = 1;
    }

    private Bzip2HuffmanAllocator() {
    }
}
