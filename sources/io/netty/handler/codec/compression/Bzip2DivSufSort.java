package io.netty.handler.codec.compression;

final class Bzip2DivSufSort {
    private static final int BUCKET_A_SIZE = 256;
    private static final int BUCKET_B_SIZE = 65536;
    private static final int INSERTIONSORT_THRESHOLD = 8;
    private static final int[] LOG_2_TABLE = {-1, 0, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7};
    private static final int SS_BLOCKSIZE = 1024;
    private static final int STACK_SIZE = 64;
    private final int[] SA;
    private final byte[] T;
    private final int n;

    private static int BUCKET_B(int i, int i2) {
        return i | (i2 << 8);
    }

    private static int BUCKET_BSTAR(int i, int i2) {
        return (i << 8) | i2;
    }

    private static int getIDX(int i) {
        return i >= 0 ? i : i ^ -1;
    }

    Bzip2DivSufSort(byte[] bArr, int[] iArr, int i) {
        this.T = bArr;
        this.SA = iArr;
        this.n = i;
    }

    private static void swapElements(int[] iArr, int i, int[] iArr2, int i2) {
        int i3 = iArr[i];
        iArr[i] = iArr2[i2];
        iArr2[i2] = i3;
    }

    private int ssCompare(int i, int i2, int i3) {
        int[] iArr = this.SA;
        byte[] bArr = this.T;
        int i4 = iArr[i + 1] + 2;
        int i5 = iArr[i2 + 1] + 2;
        int i6 = iArr[i] + i3;
        int i7 = i3 + iArr[i2];
        while (i6 < i4 && i7 < i5 && bArr[i6] == bArr[i7]) {
            i6++;
            i7++;
        }
        if (i6 >= i4) {
            return i7 < i5 ? -1 : 0;
        }
        if (i7 < i5) {
            return (bArr[i6] & 255) - (bArr[i7] & 255);
        }
        return 1;
    }

    private int ssCompareLast(int i, int i2, int i3, int i4, int i5) {
        int[] iArr = this.SA;
        byte[] bArr = this.T;
        int i6 = iArr[i2] + i4;
        int i7 = i4 + iArr[i3];
        int i8 = iArr[i3 + 1] + 2;
        while (i6 < i5 && i7 < i8 && bArr[i6] == bArr[i7]) {
            i6++;
            i7++;
        }
        if (i6 < i5) {
            if (i7 < i8) {
                return (bArr[i6] & 255) - (bArr[i7] & 255);
            }
            return 1;
        } else if (i7 == i8) {
            return 1;
        } else {
            int i9 = i6 % i5;
            int i10 = iArr[i] + 2;
            while (i9 < i10 && i7 < i8 && bArr[i9] == bArr[i7]) {
                i9++;
                i7++;
            }
            if (i9 >= i10) {
                return i7 < i8 ? -1 : 0;
            }
            if (i7 < i8) {
                return (bArr[i9] & 255) - (bArr[i7] & 255);
            }
            return 1;
        }
    }

    private void ssInsertionSort(int i, int i2, int i3, int i4) {
        int ssCompare;
        int[] iArr = this.SA;
        for (int i5 = i3 - 2; i2 <= i5; i5--) {
            int i6 = iArr[i5];
            int i7 = i5 + 1;
            do {
                ssCompare = ssCompare(i + i6, iArr[i7] + i, i4);
                if (ssCompare <= 0) {
                    break;
                }
                do {
                    iArr[i7 - 1] = iArr[i7];
                    i7++;
                    if (i7 >= i3 || iArr[i7] >= 0) {
                    }
                    iArr[i7 - 1] = iArr[i7];
                    i7++;
                    break;
                } while (iArr[i7] >= 0);
                continue;
            } while (i3 > i7);
            if (ssCompare == 0) {
                iArr[i7] = iArr[i7] ^ -1;
            }
            iArr[i7 - 1] = i6;
        }
    }

    private void ssFixdown(int i, int i2, int i3, int i4, int i5) {
        int[] iArr = this.SA;
        byte[] bArr = this.T;
        int i6 = iArr[i3 + i4];
        byte b = bArr[iArr[i2 + i6] + i] & 255;
        while (true) {
            int i7 = i4 * 2;
            int i8 = i7 + 1;
            if (i8 >= i5) {
                break;
            }
            int i9 = i7 + 2;
            byte b2 = bArr[iArr[iArr[i3 + i8] + i2] + i] & 255;
            byte b3 = bArr[iArr[iArr[i3 + i9] + i2] + i] & 255;
            if (b2 < b3) {
                b2 = b3;
            } else {
                i9 = i8;
            }
            if (b2 <= b) {
                break;
            }
            iArr[i4 + i3] = iArr[i3 + i9];
            i4 = i9;
        }
        iArr[i3 + i4] = i6;
    }

    private void ssHeapSort(int i, int i2, int i3, int i4) {
        int i5;
        int i6 = i3;
        int[] iArr = this.SA;
        byte[] bArr = this.T;
        int i7 = i4 % 2;
        if (i7 == 0) {
            int i8 = i4 - 1;
            int i9 = (i8 / 2) + i6;
            int i10 = i6 + i8;
            if ((bArr[iArr[iArr[i9] + i2] + i] & 255) < (bArr[iArr[iArr[i10] + i2] + i] & 255)) {
                swapElements(iArr, i10, iArr, i9);
            }
            i5 = i8;
        } else {
            i5 = i4;
        }
        for (int i11 = (i5 / 2) - 1; i11 >= 0; i11--) {
            ssFixdown(i, i2, i3, i11, i5);
        }
        if (i7 == 0) {
            swapElements(iArr, i3, iArr, i6 + i5);
            ssFixdown(i, i2, i3, 0, i5);
        }
        for (int i12 = i5 - 1; i12 > 0; i12--) {
            int i13 = iArr[i6];
            int i14 = i6 + i12;
            iArr[i6] = iArr[i14];
            ssFixdown(i, i2, i3, 0, i12);
            iArr[i14] = i13;
        }
    }

    private int ssMedian3(int i, int i2, int i3, int i4, int i5) {
        int[] iArr = this.SA;
        byte[] bArr = this.T;
        byte b = bArr[iArr[iArr[i3] + i2] + i] & 255;
        byte b2 = bArr[iArr[iArr[i4] + i2] + i] & 255;
        byte b3 = bArr[i + iArr[i2 + iArr[i5]]] & 255;
        if (b <= b2) {
            int i6 = i4;
            i4 = i3;
            i3 = i6;
            byte b4 = b2;
            b2 = b;
            b = b4;
        }
        if (b > b3) {
            return b2 > b3 ? i4 : i5;
        }
        return i3;
    }

    private int ssMedian5(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        byte b;
        int i8;
        int i9;
        byte b2;
        byte b3;
        int i10;
        int i11;
        byte b4;
        int i12;
        int[] iArr = this.SA;
        byte[] bArr = this.T;
        byte b5 = bArr[iArr[iArr[i3] + i2] + i] & 255;
        byte b6 = bArr[iArr[iArr[i4] + i2] + i] & 255;
        byte b7 = bArr[iArr[iArr[i5] + i2] + i] & 255;
        byte b8 = bArr[iArr[iArr[i6] + i2] + i] & 255;
        byte b9 = bArr[iArr[iArr[i7] + i2] + i] & 255;
        if (b6 > b7) {
            i9 = i5;
            b = b6;
            i8 = i4;
        } else {
            i9 = i4;
            b = b7;
            b7 = b6;
            i8 = i5;
        }
        if (b8 > b9) {
            b3 = b9;
            b2 = b8;
            i11 = i6;
            i10 = i7;
        } else {
            b2 = b9;
            b3 = b8;
            i10 = i6;
            i11 = i7;
        }
        if (b7 > b3) {
            int i13 = i8;
            i8 = i11;
            i11 = i13;
            byte b10 = b2;
            b2 = b;
            b = b10;
        } else {
            i9 = i10;
            b7 = b3;
        }
        if (b5 > b) {
            b4 = b5;
            i12 = i3;
        } else {
            b4 = b;
            b = b5;
            i12 = i8;
            i8 = i3;
        }
        if (b > b7) {
            i9 = i8;
            b7 = b;
        } else {
            i11 = i12;
            b2 = b4;
        }
        return b2 > b7 ? i9 : i11;
    }

    private int ssPivot(int i, int i2, int i3, int i4) {
        int i5 = i4 - i3;
        int i6 = i3 + (i5 / 2);
        if (i5 > 512) {
            int i7 = i5 >> 3;
            int i8 = i7 << 1;
            int i9 = i;
            int i10 = i4 - 1;
            return ssMedian3(i, i2, ssMedian3(i9, i2, i3, i3 + i7, i3 + i8), ssMedian3(i, i2, i6 - i7, i6, i6 + i7), ssMedian3(i9, i2, i10 - i8, i10 - i7, i10));
        } else if (i5 <= 32) {
            return ssMedian3(i, i2, i3, i6, i4 - 1);
        } else {
            int i11 = i5 >> 2;
            int i12 = i4 - 1;
            return ssMedian5(i, i2, i3, i3 + i11, i6, i12 - i11, i12);
        }
    }

    private static int ssLog(int i) {
        return (65280 & i) != 0 ? LOG_2_TABLE[(i >> 8) & 255] + 8 : LOG_2_TABLE[i & 255];
    }

    private int ssSubstringPartition(int i, int i2, int i3, int i4) {
        int i5;
        int[] iArr = this.SA;
        int i6 = i2 - 1;
        while (true) {
            i6++;
            if (i6 < i3) {
                int i7 = iArr[i6];
                if (iArr[i + i7] + i4 >= iArr[i + i7 + 1] + 1) {
                    iArr[i6] = i7 ^ -1;
                }
            }
            do {
                i3--;
                if (i6 >= i3) {
                    break;
                }
                i5 = iArr[i3];
            } while (iArr[i + i5] + i4 < iArr[i5 + i + 1] + 1);
            if (i3 <= i6) {
                break;
            }
            iArr[i3] = iArr[i6];
            iArr[i6] = iArr[i3] ^ -1;
        }
        if (i2 < i6) {
            iArr[i2] = iArr[i2] ^ -1;
        }
        return i6;
    }

    private static class StackEntry {
        final int a;
        final int b;
        final int c;
        final int d;

        StackEntry(int i, int i2, int i3, int i4) {
            this.a = i;
            this.b = i2;
            this.c = i3;
            this.d = i4;
        }
    }

    private void ssMultiKeyIntroSort(int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int i7;
        byte b;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        Bzip2DivSufSort bzip2DivSufSort = this;
        int i14 = i;
        int[] iArr = bzip2DivSufSort.SA;
        byte[] bArr = bzip2DivSufSort.T;
        StackEntry[] stackEntryArr = new StackEntry[64];
        int i15 = i3;
        int i16 = i4;
        int ssLog = ssLog(i3 - i2);
        int i17 = 0;
        byte b2 = 0;
        int i18 = i2;
        while (true) {
            int i19 = i15 - i18;
            if (i19 <= 8) {
                if (1 < i19) {
                    bzip2DivSufSort.ssInsertionSort(i14, i18, i15, i16);
                }
                if (i17 != 0) {
                    i17--;
                    StackEntry stackEntry = stackEntryArr[i17];
                    int i20 = stackEntry.a;
                    int i21 = stackEntry.b;
                    int i22 = stackEntry.c;
                    ssLog = stackEntry.d;
                    i18 = i20;
                    i15 = i21;
                    i16 = i22;
                } else {
                    return;
                }
            } else {
                int i23 = ssLog - 1;
                if (ssLog == 0) {
                    bzip2DivSufSort.ssHeapSort(i16, i14, i18, i19);
                }
                if (i23 < 0) {
                    byte b3 = bArr[iArr[iArr[i18] + i14] + i16] & 255;
                    int i24 = i18;
                    i18++;
                    while (i18 < i15) {
                        b2 = bArr[iArr[iArr[i18] + i14] + i16] & 255;
                        if (b2 != b3) {
                            if (1 < i18 - i24) {
                                break;
                            }
                            i24 = i18;
                            b3 = b2;
                        }
                        i18++;
                    }
                    if ((bArr[(iArr[iArr[i24] + i14] + i16) - 1] & 255) < b3) {
                        i24 = bzip2DivSufSort.ssSubstringPartition(i14, i24, i18, i16);
                    }
                    int i25 = i18 - i24;
                    int i26 = i15 - i18;
                    if (i25 <= i26) {
                        if (1 < i25) {
                            stackEntryArr[i17] = new StackEntry(i18, i15, i16, -1);
                            i16++;
                            i13 = ssLog(i25);
                            i17++;
                            int i27 = i13;
                            i15 = i18;
                            i18 = i24;
                            ssLog = i27;
                        }
                    } else if (1 < i26) {
                        stackEntryArr[i17] = new StackEntry(i24, i18, i16 + 1, ssLog(i25));
                        i17++;
                    } else {
                        i16++;
                        i13 = ssLog(i25);
                        int i272 = i13;
                        i15 = i18;
                        i18 = i24;
                        ssLog = i272;
                    }
                    ssLog = -1;
                } else {
                    int ssPivot = bzip2DivSufSort.ssPivot(i16, i14, i18, i15);
                    byte b4 = bArr[iArr[iArr[ssPivot] + i14] + i16] & 255;
                    swapElements(iArr, i18, iArr, ssPivot);
                    int i28 = i18 + 1;
                    while (i28 < i15) {
                        b2 = bArr[iArr[iArr[i28] + i14] + i16] & 255;
                        if (b2 != b4) {
                            break;
                        }
                        i28++;
                    }
                    if (i28 < i15 && b2 < b4) {
                        i5 = i28;
                        while (true) {
                            i28++;
                            if (i28 >= i15 || (b2 = bArr[iArr[iArr[i28] + i14] + i16] & 255) > b4) {
                                break;
                            } else if (b2 == b4) {
                                swapElements(iArr, i28, iArr, i5);
                                i5++;
                            }
                        }
                    } else {
                        i5 = i28;
                    }
                    int i29 = i15 - 1;
                    while (i28 < i29) {
                        b2 = bArr[iArr[iArr[i29] + i14] + i16] & 255;
                        if (b2 != b4) {
                            break;
                        }
                        i29--;
                    }
                    if (i28 < i29 && b2 > b4) {
                        i6 = ssLog;
                        i7 = i29;
                        while (true) {
                            i29--;
                            if (i28 >= i29 || (b2 = bArr[iArr[iArr[i29] + i14] + i16] & 255) < b4) {
                                break;
                            } else if (b2 == b4) {
                                swapElements(iArr, i29, iArr, i7);
                                i7--;
                            }
                        }
                    } else {
                        i6 = ssLog;
                        i7 = i29;
                    }
                    while (i28 < i29) {
                        swapElements(iArr, i28, iArr, i29);
                        while (true) {
                            i28++;
                            if (i28 < i29 && (b2 = bArr[iArr[iArr[i28] + i14] + i16] & 255) <= b4) {
                                if (b2 == b4) {
                                    swapElements(iArr, i28, iArr, i5);
                                    i5++;
                                }
                            }
                        }
                        while (true) {
                            i29--;
                            if (i28 < i29 && (b2 = bArr[iArr[iArr[i29] + i14] + i16] & 255) >= b4) {
                                if (b2 == b4) {
                                    swapElements(iArr, i29, iArr, i7);
                                    i7--;
                                }
                            }
                        }
                    }
                    if (i5 <= i7) {
                        int i30 = i28 - 1;
                        b = b2;
                        int i31 = i5 - i18;
                        int i32 = i28 - i5;
                        if (i31 > i32) {
                            i31 = i32;
                        }
                        int i33 = i23;
                        int i34 = i18;
                        int i35 = i28;
                        int i36 = i28 - i31;
                        while (i31 > 0) {
                            swapElements(iArr, i34, iArr, i36);
                            i31--;
                            i34++;
                            i36++;
                        }
                        int i37 = i7 - i30;
                        int i38 = (i15 - i7) - 1;
                        if (i37 <= i38) {
                            i38 = i37;
                        }
                        int i39 = i15 - i38;
                        int i40 = i35;
                        while (i38 > 0) {
                            swapElements(iArr, i40, iArr, i39);
                            i38--;
                            i40++;
                            i39++;
                        }
                        int i41 = i18 + i32;
                        int i42 = i15 - i37;
                        int ssSubstringPartition = b4 <= (bArr[(iArr[iArr[i41] + i14] + i16) + -1] & 255) ? i41 : bzip2DivSufSort.ssSubstringPartition(i14, i41, i42, i16);
                        int i43 = i41 - i18;
                        int i44 = i15 - i42;
                        if (i43 <= i44) {
                            int i45 = i42 - ssSubstringPartition;
                            if (i44 <= i45) {
                                int i46 = i17 + 1;
                                stackEntryArr[i17] = new StackEntry(ssSubstringPartition, i42, i16 + 1, ssLog(i45));
                                i17 += 2;
                                i12 = i33;
                                stackEntryArr[i46] = new StackEntry(i42, i15, i16, i12);
                            } else {
                                i12 = i33;
                                if (i43 <= i45) {
                                    int i47 = i17 + 1;
                                    stackEntryArr[i17] = new StackEntry(i42, i15, i16, i12);
                                    i17 += 2;
                                    stackEntryArr[i47] = new StackEntry(ssSubstringPartition, i42, i16 + 1, ssLog(i45));
                                } else {
                                    int i48 = i17 + 1;
                                    stackEntryArr[i17] = new StackEntry(i42, i15, i16, i12);
                                    i9 = i17 + 2;
                                    stackEntryArr[i48] = new StackEntry(i18, i41, i16, i12);
                                    i10 = i16 + 1;
                                    ssLog = ssLog(i45);
                                }
                            }
                            b2 = b;
                            i15 = i41;
                            ssLog = i12;
                        } else {
                            int i49 = i33;
                            int i50 = i42 - ssSubstringPartition;
                            if (i43 <= i50) {
                                int i51 = i17 + 1;
                                stackEntryArr[i17] = new StackEntry(ssSubstringPartition, i42, i16 + 1, ssLog(i50));
                                i11 = i17 + 2;
                                stackEntryArr[i51] = new StackEntry(i18, i41, i16, i49);
                            } else if (i44 <= i50) {
                                int i52 = i17 + 1;
                                stackEntryArr[i17] = new StackEntry(i18, i41, i16, i49);
                                i11 = i17 + 2;
                                stackEntryArr[i52] = new StackEntry(ssSubstringPartition, i42, i16 + 1, ssLog(i50));
                            } else {
                                int i53 = i17 + 1;
                                stackEntryArr[i17] = new StackEntry(i18, i41, i16, i49);
                                i9 = i17 + 2;
                                stackEntryArr[i53] = new StackEntry(i42, i15, i16, i49);
                                i10 = i16 + 1;
                                ssLog = ssLog(i50);
                                bzip2DivSufSort = this;
                            }
                            bzip2DivSufSort = this;
                            i18 = i42;
                            ssLog = i49;
                        }
                        i15 = i42;
                        i18 = ssSubstringPartition;
                    } else {
                        b = b2;
                        if ((bArr[(iArr[iArr[i18] + i14] + i16) - 1] & 255) < b4) {
                            bzip2DivSufSort = this;
                            i18 = bzip2DivSufSort.ssSubstringPartition(i14, i18, i15, i16);
                            i8 = ssLog(i15 - i18);
                        } else {
                            bzip2DivSufSort = this;
                            i8 = i6;
                        }
                        i16++;
                    }
                    b2 = b;
                }
            }
        }
    }

    private static void ssBlockSwap(int[] iArr, int i, int[] iArr2, int i2, int i3) {
        while (i3 > 0) {
            swapElements(iArr, i, iArr2, i2);
            i3--;
            i++;
            i2++;
        }
    }

    private void ssMergeForward(int i, int[] iArr, int i2, int i3, int i4, int i5, int i6) {
        int i7;
        int[] iArr2 = this.SA;
        int i8 = i4 - i3;
        int i9 = (i2 + i8) - 1;
        ssBlockSwap(iArr, i2, iArr2, i3, i8);
        int i10 = iArr2[i3];
        while (true) {
            int ssCompare = ssCompare(iArr[i2] + i, iArr2[i4] + i, i6);
            if (ssCompare < 0) {
                while (true) {
                    i7 = i3 + 1;
                    iArr2[i3] = iArr[i2];
                    if (i9 <= i2) {
                        iArr[i2] = i10;
                        return;
                    }
                    int i11 = i2 + 1;
                    iArr[i2] = iArr2[i7];
                    if (iArr[i11] >= 0) {
                        i2 = i11;
                        break;
                    } else {
                        i2 = i11;
                        i3 = i7;
                    }
                }
            } else if (ssCompare > 0) {
                while (true) {
                    i7 = i3 + 1;
                    iArr2[i3] = iArr2[i4];
                    int i12 = i4 + 1;
                    iArr2[i4] = iArr2[i7];
                    if (i5 <= i12) {
                        while (i2 < i9) {
                            int i13 = i7 + 1;
                            iArr2[i7] = iArr[i2];
                            iArr[i2] = iArr2[i13];
                            i7 = i13;
                            i2++;
                        }
                        iArr2[i7] = iArr[i2];
                        iArr[i2] = i10;
                        return;
                    } else if (iArr2[i12] >= 0) {
                        i4 = i12;
                        break;
                    } else {
                        i4 = i12;
                        i3 = i7;
                    }
                }
            } else {
                iArr2[i4] = iArr2[i4] ^ -1;
                while (true) {
                    int i14 = i3 + 1;
                    iArr2[i3] = iArr[i2];
                    if (i9 <= i2) {
                        iArr[i2] = i10;
                        return;
                    }
                    int i15 = i2 + 1;
                    iArr[i2] = iArr2[i14];
                    if (iArr[i15] >= 0) {
                        while (true) {
                            int i16 = i14 + 1;
                            iArr2[i14] = iArr2[i4];
                            int i17 = i4 + 1;
                            iArr2[i4] = iArr2[i16];
                            if (i5 <= i17) {
                                while (i15 < i9) {
                                    int i18 = i16 + 1;
                                    iArr2[i16] = iArr[i15];
                                    iArr[i15] = iArr2[i18];
                                    i15++;
                                    i16 = i18;
                                }
                                iArr2[i16] = iArr[i15];
                                iArr[i15] = i10;
                                return;
                            } else if (iArr2[i17] >= 0) {
                                i4 = i17;
                                int i19 = i15;
                                i3 = i16;
                                i2 = i19;
                                break;
                            } else {
                                i4 = i17;
                                i14 = i16;
                            }
                        }
                    } else {
                        i2 = i15;
                        i3 = i14;
                    }
                }
            }
            i3 = i7;
        }
    }

    private void ssMergeBackward(int i, int[] iArr, int i2, int i3, int i4, int i5, int i6) {
        boolean z;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        int[] iArr2 = iArr;
        int i16 = i2;
        int i17 = i3;
        int i18 = i4;
        int[] iArr3 = this.SA;
        int i19 = i5 - i18;
        ssBlockSwap(iArr2, i16, iArr3, i18, i19);
        int i20 = (i16 + i19) - 1;
        int i21 = iArr2[i20];
        if (i21 < 0) {
            i7 = i + (i21 ^ -1);
            z = true;
        } else {
            i7 = i + i21;
            z = false;
        }
        int i22 = i18 - 1;
        int i23 = iArr3[i22];
        if (i23 < 0) {
            z |= true;
            i23 ^= -1;
        }
        int i24 = i + i23;
        int i25 = i5 - 1;
        int i26 = iArr3[i25];
        int i27 = i6;
        while (true) {
            int ssCompare = ssCompare(i7, i24, i27);
            if (ssCompare > 0) {
                if (z && true) {
                    while (true) {
                        i14 = i25 - 1;
                        iArr3[i25] = iArr2[i20];
                        i15 = i20 - 1;
                        iArr2[i20] = iArr3[i14];
                        if (iArr2[i15] >= 0) {
                            break;
                        }
                        i20 = i15;
                        i25 = i14;
                    }
                    z = !z;
                    i20 = i15;
                    i25 = i14;
                }
                int i28 = i25 - 1;
                iArr3[i25] = iArr2[i20];
                if (i20 <= i16) {
                    iArr2[i20] = i26;
                    return;
                }
                int i29 = i20 - 1;
                iArr2[i20] = iArr3[i28];
                int i30 = iArr2[i29];
                if (i30 < 0) {
                    z |= true;
                    i30 ^= -1;
                }
                int i31 = i + i30;
                i20 = i29;
                i25 = i28;
                i7 = i31;
            } else if (ssCompare < 0) {
                if (z && true) {
                    while (true) {
                        i12 = i25 - 1;
                        iArr3[i25] = iArr3[i22];
                        i13 = i22 - 1;
                        iArr3[i22] = iArr3[i12];
                        if (iArr3[i13] >= 0) {
                            break;
                        }
                        i22 = i13;
                        i25 = i12;
                    }
                    z ^= true;
                    i22 = i13;
                    i25 = i12;
                }
                int i32 = i25 - 1;
                iArr3[i25] = iArr3[i22];
                int i33 = i22 - 1;
                iArr3[i22] = iArr3[i32];
                if (i33 < i17) {
                    while (i16 < i20) {
                        int i34 = i32 - 1;
                        iArr3[i32] = iArr2[i20];
                        iArr2[i20] = iArr3[i34];
                        i32 = i34;
                        i20--;
                    }
                    iArr3[i32] = iArr2[i20];
                    iArr2[i20] = i26;
                    return;
                }
                int i35 = iArr3[i33];
                if (i35 < 0) {
                    z |= true;
                    i35 ^= -1;
                }
                int i36 = i32;
                i24 = i + i35;
                i22 = i33;
                i25 = i36;
            } else {
                if (z && true) {
                    while (true) {
                        i10 = i25 - 1;
                        iArr3[i25] = iArr2[i20];
                        i11 = i20 - 1;
                        iArr2[i20] = iArr3[i10];
                        if (iArr2[i11] >= 0) {
                            break;
                        }
                        i20 = i11;
                        i25 = i10;
                    }
                    z = !z;
                    i20 = i11;
                    i25 = i10;
                }
                int i37 = i25 - 1;
                iArr3[i25] = iArr2[i20] ^ -1;
                if (i20 <= i16) {
                    iArr2[i20] = i26;
                    return;
                }
                int i38 = i20 - 1;
                iArr2[i20] = iArr3[i37];
                if (z && true) {
                    while (true) {
                        i8 = i37 - 1;
                        iArr3[i37] = iArr3[i22];
                        i9 = i22 - 1;
                        iArr3[i22] = iArr3[i8];
                        if (iArr3[i9] >= 0) {
                            break;
                        }
                        i22 = i9;
                        i37 = i8;
                    }
                    z ^= true;
                    i22 = i9;
                    i37 = i8;
                }
                int i39 = i37 - 1;
                iArr3[i37] = iArr3[i22];
                int i40 = i22 - 1;
                iArr3[i22] = iArr3[i39];
                if (i40 < i17) {
                    while (i16 < i38) {
                        int i41 = i39 - 1;
                        iArr3[i39] = iArr2[i38];
                        iArr2[i38] = iArr3[i41];
                        i39 = i41;
                        i38--;
                    }
                    iArr3[i39] = iArr2[i38];
                    iArr2[i38] = i26;
                    return;
                }
                int i42 = iArr2[i38];
                if (i42 < 0) {
                    z |= true;
                    i42 ^= -1;
                }
                int i43 = i + i42;
                int i44 = iArr3[i40];
                if (i44 < 0) {
                    z |= true;
                    i44 ^= -1;
                }
                i24 = i + i44;
                int i45 = i40;
                i7 = i43;
                i22 = i45;
                int i46 = i39;
                i20 = i38;
                i25 = i46;
            }
        }
    }

    private void ssMergeCheckEqual(int i, int i2, int i3) {
        int[] iArr = this.SA;
        if (iArr[i3] >= 0 && ssCompare(getIDX(iArr[i3 - 1]) + i, i + iArr[i3], i2) == 0) {
            iArr[i3] = iArr[i3] ^ -1;
        }
    }

    private void ssMerge(int i, int i2, int i3, int i4, int[] iArr, int i5, int i6, int i7) {
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14 = i;
        int i15 = i6;
        int i16 = i7;
        int[] iArr2 = this.SA;
        StackEntry[] stackEntryArr = new StackEntry[64];
        int i17 = i2;
        int i18 = i3;
        int i19 = i4;
        int i20 = 0;
        int i21 = 0;
        while (true) {
            int i22 = i19 - i18;
            if (i22 <= i15) {
                if (i17 >= i18 || i18 >= i19) {
                    i13 = i19;
                } else {
                    i13 = i19;
                    ssMergeBackward(i, iArr, i5, i17, i18, i19, i7);
                }
                if ((i20 & 1) != 0) {
                    ssMergeCheckEqual(i14, i16, i17);
                }
                if ((i20 & 2) != 0) {
                    ssMergeCheckEqual(i14, i16, i13);
                }
                if (i21 != 0) {
                    i21--;
                    StackEntry stackEntry = stackEntryArr[i21];
                    i17 = stackEntry.a;
                    i18 = stackEntry.b;
                    i8 = stackEntry.c;
                    i9 = stackEntry.d;
                } else {
                    return;
                }
            } else {
                int i23 = i19;
                int i24 = i18 - i17;
                if (i24 <= i15) {
                    if (i17 < i18) {
                        ssMergeForward(i, iArr, i5, i17, i18, i23, i7);
                    }
                    if ((i20 & 1) != 0) {
                        ssMergeCheckEqual(i14, i16, i17);
                    }
                    if ((i20 & 2) != 0) {
                        ssMergeCheckEqual(i14, i16, i23);
                    }
                    if (i21 != 0) {
                        i21--;
                        StackEntry stackEntry2 = stackEntryArr[i21];
                        i17 = stackEntry2.a;
                        i18 = stackEntry2.b;
                        i8 = stackEntry2.c;
                        i9 = stackEntry2.d;
                    } else {
                        return;
                    }
                } else {
                    int min = Math.min(i24, i22);
                    int i25 = min >> 1;
                    int i26 = 0;
                    while (min > 0) {
                        if (ssCompare(getIDX(iArr2[i18 + i26 + i25]) + i14, getIDX(iArr2[((i18 - i26) - i25) - 1]) + i14, i16) < 0) {
                            i26 += i25 + 1;
                            i25 -= (min & 1) ^ 1;
                        }
                        min = i25;
                        i25 = min >> 1;
                    }
                    if (i26 > 0) {
                        int i27 = i18 - i26;
                        ssBlockSwap(iArr2, i27, iArr2, i18, i26);
                        int i28 = i18 + i26;
                        if (i28 < i23) {
                            if (iArr2[i28] < 0) {
                                i12 = i18;
                                while (iArr2[i12 - 1] < 0) {
                                    i12--;
                                }
                                iArr2[i28] = iArr2[i28] ^ -1;
                            } else {
                                i12 = i18;
                            }
                            i10 = i18;
                            while (iArr2[i10] < 0) {
                                i10++;
                            }
                            i19 = i12;
                            i11 = 1;
                        } else {
                            i10 = i18;
                            i19 = i10;
                            i11 = 0;
                        }
                        if (i19 - i17 <= i23 - i10) {
                            stackEntryArr[i21] = new StackEntry(i10, i28, i23, (i11 & 1) | (i20 & 2));
                            i20 &= 1;
                            i18 = i27;
                            i21++;
                        } else {
                            if (i19 == i18 && i18 == i10) {
                                i11 <<= 1;
                            }
                            stackEntryArr[i21] = new StackEntry(i17, i27, i19, (i20 & 1) | (i11 & 2));
                            i20 = (i20 & 2) | (i11 & 1);
                            i18 = i28;
                            i21++;
                            i17 = i10;
                            i19 = i23;
                        }
                    } else {
                        if ((i20 & 1) != 0) {
                            ssMergeCheckEqual(i14, i16, i17);
                        }
                        ssMergeCheckEqual(i14, i16, i18);
                        if ((i20 & 2) != 0) {
                            ssMergeCheckEqual(i14, i16, i23);
                        }
                        if (i21 != 0) {
                            i21--;
                            StackEntry stackEntry3 = stackEntryArr[i21];
                            i17 = stackEntry3.a;
                            i18 = stackEntry3.b;
                            i8 = stackEntry3.c;
                            i9 = stackEntry3.d;
                        } else {
                            return;
                        }
                    }
                }
            }
            i20 = i9;
        }
    }

    private void subStringSort(int i, int i2, int i3, int[] iArr, int i4, int i5, int i6, boolean z, int i7) {
        int i8;
        int[] iArr2;
        int i9;
        int i10 = i;
        int i11 = i3;
        int i12 = i6;
        int[] iArr3 = this.SA;
        int i13 = z ? i2 + 1 : i2;
        int i14 = i13;
        int i15 = 0;
        while (true) {
            int i16 = i14 + 1024;
            if (i16 >= i11) {
                break;
            }
            ssMultiKeyIntroSort(i10, i14, i16, i12);
            int i17 = i11 - i16;
            int i18 = i5;
            if (i17 <= i18) {
                iArr2 = iArr;
                i8 = i4;
                i9 = i18;
            } else {
                i9 = i17;
                i8 = i16;
                iArr2 = iArr3;
            }
            int i19 = i14;
            int i20 = i15;
            int i21 = 1024;
            while ((i20 & 1) != 0) {
                int i22 = i19 - i21;
                ssMerge(i, i22, i19, i19 + i21, iArr2, i8, i9, i6);
                i21 <<= 1;
                i20 >>>= 1;
                int i23 = i5;
                i19 = i22;
                i16 = i16;
            }
            i15++;
            i14 = i16;
        }
        ssMultiKeyIntroSort(i10, i14, i11, i12);
        int i24 = i14;
        int i25 = 1024;
        for (int i26 = i15; i26 != 0; i26 >>= 1) {
            if ((i26 & 1) != 0) {
                int i27 = i24 - i25;
                ssMerge(i, i27, i24, i3, iArr, i4, i5, i6);
                i24 = i27;
            }
            i25 <<= 1;
        }
        if (z) {
            int i28 = iArr3[i13 - 1];
            int i29 = 1;
            while (i13 < i11) {
                int i30 = iArr3[i13];
                if (i30 >= 0 && (i29 = ssCompareLast(i, i10 + i28, i10 + i30, i6, i7)) <= 0) {
                    break;
                }
                iArr3[i13 - 1] = iArr3[i13];
                i13++;
            }
            if (i29 == 0) {
                iArr3[i13] = iArr3[i13] ^ -1;
            }
            iArr3[i13 - 1] = i28;
        }
    }

    private int trGetC(int i, int i2, int i3, int i4) {
        int i5 = i2 + i4;
        return i5 < i3 ? this.SA[i5] : this.SA[i + (((i2 - i) + i4) % (i3 - i))];
    }

    private void trFixdown(int i, int i2, int i3, int i4, int i5, int i6) {
        int[] iArr = this.SA;
        int i7 = iArr[i4 + i5];
        int trGetC = trGetC(i, i2, i3, i7);
        while (true) {
            int i8 = i5 * 2;
            int i9 = i8 + 1;
            if (i9 >= i6) {
                break;
            }
            int i10 = i8 + 2;
            int trGetC2 = trGetC(i, i2, i3, iArr[i4 + i9]);
            int trGetC3 = trGetC(i, i2, i3, iArr[i4 + i10]);
            if (trGetC2 < trGetC3) {
                trGetC2 = trGetC3;
            } else {
                i10 = i9;
            }
            if (trGetC2 <= trGetC) {
                break;
            }
            iArr[i5 + i4] = iArr[i4 + i10];
            i5 = i10;
        }
        iArr[i4 + i5] = i7;
    }

    private void trHeapSort(int i, int i2, int i3, int i4, int i5) {
        int i6;
        int i7 = i;
        int i8 = i2;
        int i9 = i3;
        int i10 = i4;
        int[] iArr = this.SA;
        int i11 = i5 % 2;
        if (i11 == 0) {
            int i12 = i5 - 1;
            int i13 = (i12 / 2) + i10;
            int i14 = i10 + i12;
            if (trGetC(i7, i8, i9, iArr[i13]) < trGetC(i7, i8, i9, iArr[i14])) {
                swapElements(iArr, i14, iArr, i13);
            }
            i6 = i12;
        } else {
            i6 = i5;
        }
        for (int i15 = (i6 / 2) - 1; i15 >= 0; i15--) {
            trFixdown(i, i2, i3, i4, i15, i6);
        }
        if (i11 == 0) {
            swapElements(iArr, i10, iArr, i10 + i6);
            trFixdown(i, i2, i3, i4, 0, i6);
        }
        for (int i16 = i6 - 1; i16 > 0; i16--) {
            int i17 = iArr[i10];
            int i18 = i10 + i16;
            iArr[i10] = iArr[i18];
            trFixdown(i, i2, i3, i4, 0, i16);
            iArr[i18] = i17;
        }
    }

    private void trInsertionSort(int i, int i2, int i3, int i4, int i5) {
        int trGetC;
        int[] iArr = this.SA;
        for (int i6 = i4 + 1; i6 < i5; i6++) {
            int i7 = iArr[i6];
            int i8 = i6 - 1;
            do {
                trGetC = trGetC(i, i2, i3, i7) - trGetC(i, i2, i3, iArr[i8]);
                if (trGetC >= 0) {
                    break;
                }
                do {
                    iArr[i8 + 1] = iArr[i8];
                    i8--;
                    if (i4 > i8 || iArr[i8] >= 0) {
                    }
                    iArr[i8 + 1] = iArr[i8];
                    i8--;
                    break;
                } while (iArr[i8] >= 0);
                continue;
            } while (i8 >= i4);
            if (trGetC == 0) {
                iArr[i8] = iArr[i8] ^ -1;
            }
            iArr[i8 + 1] = i7;
        }
    }

    private static int trLog(int i) {
        return (-65536 & i) != 0 ? (-16777216 & i) != 0 ? LOG_2_TABLE[(i >> 24) & 255] + 24 : LOG_2_TABLE[(i >> 16) & 271] : (65280 & i) != 0 ? LOG_2_TABLE[(i >> 8) & 255] + 8 : LOG_2_TABLE[i & 255];
    }

    private int trMedian3(int i, int i2, int i3, int i4, int i5, int i6) {
        int[] iArr = this.SA;
        int trGetC = trGetC(i, i2, i3, iArr[i4]);
        int trGetC2 = trGetC(i, i2, i3, iArr[i5]);
        int trGetC3 = trGetC(i, i2, i3, iArr[i6]);
        if (trGetC <= trGetC2) {
            int i7 = i5;
            i5 = i4;
            i4 = i7;
            int i8 = trGetC2;
            trGetC2 = trGetC;
            trGetC = i8;
        }
        if (trGetC > trGetC3) {
            return trGetC2 > trGetC3 ? i5 : i6;
        }
        return i4;
    }

    private int trMedian5(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        int[] iArr = this.SA;
        int trGetC = trGetC(i, i2, i3, iArr[i4]);
        int trGetC2 = trGetC(i, i2, i3, iArr[i5]);
        int trGetC3 = trGetC(i, i2, i3, iArr[i6]);
        int trGetC4 = trGetC(i, i2, i3, iArr[i7]);
        int trGetC5 = trGetC(i, i2, i3, iArr[i8]);
        if (trGetC2 > trGetC3) {
            int i9 = i6;
            i6 = i5;
            i5 = i9;
            int i10 = trGetC3;
            trGetC3 = trGetC2;
            trGetC2 = i10;
        }
        if (trGetC4 > trGetC5) {
            int i11 = trGetC4;
            trGetC4 = trGetC5;
            trGetC5 = i11;
        } else {
            int i12 = i8;
            i8 = i7;
            i7 = i12;
        }
        if (trGetC2 > trGetC4) {
            int i13 = trGetC3;
            trGetC3 = trGetC5;
            trGetC5 = i13;
            int i14 = i7;
            i7 = i6;
            i6 = i14;
        } else {
            i5 = i8;
            trGetC2 = trGetC4;
        }
        if (trGetC > trGetC3) {
            int i15 = i6;
            i6 = i4;
            i4 = i15;
            int i16 = trGetC3;
            trGetC3 = trGetC;
            trGetC = i16;
        }
        if (trGetC > trGetC2) {
            i5 = i4;
            trGetC2 = trGetC;
        } else {
            i7 = i6;
            trGetC5 = trGetC3;
        }
        return trGetC5 > trGetC2 ? i5 : i7;
    }

    private int trPivot(int i, int i2, int i3, int i4, int i5) {
        int i6 = i5 - i4;
        int i7 = i4 + (i6 / 2);
        if (i6 > 512) {
            int i8 = i6 >> 3;
            int i9 = i8 << 1;
            int i10 = i;
            int i11 = i5 - 1;
            return trMedian3(i, i2, i3, trMedian3(i10, i2, i3, i4, i4 + i8, i4 + i9), trMedian3(i, i2, i3, i7 - i8, i7, i7 + i8), trMedian3(i10, i2, i3, i11 - i9, i11 - i8, i11));
        } else if (i6 <= 32) {
            return trMedian3(i, i2, i3, i4, i7, i5 - 1);
        } else {
            int i12 = i6 >> 2;
            int i13 = i5 - 1;
            return trMedian5(i, i2, i3, i4, i4 + i12, i7, i13 - i12, i13);
        }
    }

    private void lsUpdateGroup(int i, int i2, int i3) {
        int i4;
        int[] iArr = this.SA;
        while (i2 < i3) {
            if (iArr[i2] >= 0) {
                int i5 = i2;
                do {
                    iArr[iArr[i5] + i] = i5;
                    i5++;
                    if (i5 >= i3 || iArr[i5] < 0) {
                        iArr[i2] = i2 - i5;
                    }
                    iArr[iArr[i5] + i] = i5;
                    i5++;
                    break;
                } while (iArr[i5] < 0);
                iArr[i2] = i2 - i5;
                if (i3 > i5) {
                    i2 = i5;
                } else {
                    return;
                }
            }
            int i6 = i2;
            while (true) {
                iArr[i6] = iArr[i6] ^ -1;
                i4 = i6 + 1;
                if (iArr[i4] >= 0) {
                    break;
                }
                i6 = i4;
            }
            do {
                iArr[iArr[i2] + i] = i4;
                i2++;
            } while (i2 <= i4);
            i2 = i6 + 2;
        }
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    /* JADX WARNING: Removed duplicated region for block: B:106:0x01bf  */
    /* JADX WARNING: Removed duplicated region for block: B:135:0x0133 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:141:0x011a A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0105  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x0115  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x0123  */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x0135  */
    private void lsIntroSort(int r21, int r22, int r23, int r24, int r25) {
        /*
            r20 = this;
            r6 = r20
            r7 = r21
            r8 = r22
            r9 = r23
            int[] r10 = r6.SA
            r0 = 64
            io.netty.handler.codec.compression.Bzip2DivSufSort$StackEntry[] r11 = new io.netty.handler.codec.compression.Bzip2DivSufSort.StackEntry[r0]
            int r0 = r25 - r24
            int r0 = trLog(r0)
            r13 = r24
            r14 = r25
            r15 = 0
            r16 = 0
        L_0x001b:
            int r5 = r14 - r13
            r1 = 8
            r17 = -1
            r4 = 1
            if (r5 > r1) goto L_0x0049
            if (r4 >= r5) goto L_0x0037
            r0 = r20
            r1 = r21
            r2 = r22
            r3 = r23
            r4 = r13
            r5 = r14
            r0.trInsertionSort(r1, r2, r3, r4, r5)
            r6.lsUpdateGroup(r7, r13, r14)
            goto L_0x003b
        L_0x0037:
            if (r5 != r4) goto L_0x003b
            r10[r13] = r17
        L_0x003b:
            if (r15 != 0) goto L_0x003e
            return
        L_0x003e:
            int r15 = r15 + -1
            r0 = r11[r15]
            int r13 = r0.a
            int r14 = r0.b
            int r0 = r0.c
            goto L_0x001b
        L_0x0049:
            int r3 = r0 + -1
            if (r0 != 0) goto L_0x008c
            r0 = r20
            r1 = r21
            r2 = r22
            r3 = r23
            r4 = r13
            r0.trHeapSort(r1, r2, r3, r4, r5)
            int r0 = r14 + -1
        L_0x005b:
            if (r13 >= r0) goto L_0x007b
            r1 = r10[r0]
            int r1 = r6.trGetC(r7, r8, r9, r1)
            int r0 = r0 + -1
        L_0x0065:
            if (r13 > r0) goto L_0x0078
            r2 = r10[r0]
            int r2 = r6.trGetC(r7, r8, r9, r2)
            if (r2 != r1) goto L_0x0078
            r2 = r10[r0]
            r2 = r2 ^ -1
            r10[r0] = r2
            int r0 = r0 + -1
            goto L_0x0065
        L_0x0078:
            r16 = r1
            goto L_0x005b
        L_0x007b:
            r6.lsUpdateGroup(r7, r13, r14)
            if (r15 != 0) goto L_0x0081
            return
        L_0x0081:
            int r15 = r15 + -1
            r0 = r11[r15]
            int r13 = r0.a
            int r14 = r0.b
            int r0 = r0.c
            goto L_0x001b
        L_0x008c:
            r0 = r20
            r1 = r21
            r2 = r22
            r5 = r3
            r3 = r23
            r12 = 1
            r4 = r13
            r18 = r5
            r5 = r14
            int r0 = r0.trPivot(r1, r2, r3, r4, r5)
            swapElements(r10, r13, r10, r0)
            r0 = r10[r13]
            int r0 = r6.trGetC(r7, r8, r9, r0)
            int r1 = r13 + 1
        L_0x00a9:
            if (r1 >= r14) goto L_0x00b8
            r2 = r10[r1]
            int r2 = r6.trGetC(r7, r8, r9, r2)
            if (r2 != r0) goto L_0x00ba
            int r1 = r1 + 1
            r16 = r2
            goto L_0x00a9
        L_0x00b8:
            r2 = r16
        L_0x00ba:
            if (r1 >= r14) goto L_0x00d3
            if (r2 >= r0) goto L_0x00d3
            r3 = r2
            r2 = r1
        L_0x00c0:
            int r1 = r1 + r12
            if (r1 >= r14) goto L_0x00d5
            r3 = r10[r1]
            int r3 = r6.trGetC(r7, r8, r9, r3)
            if (r3 > r0) goto L_0x00d5
            if (r3 != r0) goto L_0x00c0
            swapElements(r10, r1, r10, r2)
            int r2 = r2 + 1
            goto L_0x00c0
        L_0x00d3:
            r3 = r2
            r2 = r1
        L_0x00d5:
            int r4 = r14 + -1
        L_0x00d7:
            if (r1 >= r4) goto L_0x00e4
            r3 = r10[r4]
            int r3 = r6.trGetC(r7, r8, r9, r3)
            if (r3 != r0) goto L_0x00e4
            int r4 = r4 + -1
            goto L_0x00d7
        L_0x00e4:
            if (r1 >= r4) goto L_0x0100
            if (r3 <= r0) goto L_0x0100
            r5 = r4
        L_0x00e9:
            int r4 = r4 + -1
            if (r1 >= r4) goto L_0x00fd
            r3 = r10[r4]
            int r3 = r6.trGetC(r7, r8, r9, r3)
            if (r3 < r0) goto L_0x00fd
            if (r3 != r0) goto L_0x00e9
            swapElements(r10, r4, r10, r5)
            int r5 = r5 + -1
            goto L_0x00e9
        L_0x00fd:
            r16 = r3
            goto L_0x0103
        L_0x0100:
            r16 = r3
            r5 = r4
        L_0x0103:
            if (r1 >= r4) goto L_0x0133
            swapElements(r10, r1, r10, r4)
        L_0x0108:
            int r1 = r1 + r12
            if (r1 >= r4) goto L_0x011f
            r3 = r10[r1]
            int r3 = r6.trGetC(r7, r8, r9, r3)
            if (r3 > r0) goto L_0x011d
            if (r3 != r0) goto L_0x011a
            swapElements(r10, r1, r10, r2)
            int r2 = r2 + 1
        L_0x011a:
            r16 = r3
            goto L_0x0108
        L_0x011d:
            r16 = r3
        L_0x011f:
            int r4 = r4 + -1
            if (r1 >= r4) goto L_0x0103
            r3 = r10[r4]
            int r3 = r6.trGetC(r7, r8, r9, r3)
            if (r3 < r0) goto L_0x00fd
            if (r3 != r0) goto L_0x011d
            swapElements(r10, r4, r10, r5)
            int r5 = r5 + -1
            goto L_0x011d
        L_0x0133:
            if (r2 > r5) goto L_0x01bf
            int r0 = r1 + -1
            int r3 = r2 - r13
            int r2 = r1 - r2
            if (r3 <= r2) goto L_0x013e
            r3 = r2
        L_0x013e:
            int r4 = r1 - r3
            r12 = r13
        L_0x0141:
            if (r3 <= 0) goto L_0x014f
            swapElements(r10, r12, r10, r4)
            int r3 = r3 + -1
            r19 = 1
            int r12 = r12 + 1
            int r4 = r4 + 1
            goto L_0x0141
        L_0x014f:
            r19 = 1
            int r0 = r5 - r0
            int r3 = r14 - r5
            int r3 = r3 + -1
            if (r0 <= r3) goto L_0x015a
            goto L_0x015b
        L_0x015a:
            r3 = r0
        L_0x015b:
            int r4 = r14 - r3
        L_0x015d:
            if (r3 <= 0) goto L_0x0169
            swapElements(r10, r1, r10, r4)
            int r3 = r3 + -1
            int r1 = r1 + 1
            int r4 = r4 + 1
            goto L_0x015d
        L_0x0169:
            int r1 = r13 + r2
            int r0 = r14 - r0
            int r2 = r1 + -1
            r3 = r13
        L_0x0170:
            if (r3 >= r1) goto L_0x017a
            r4 = r10[r3]
            int r4 = r4 + r7
            r10[r4] = r2
            int r3 = r3 + 1
            goto L_0x0170
        L_0x017a:
            if (r0 >= r14) goto L_0x0189
            int r2 = r0 + -1
            r3 = r1
        L_0x017f:
            if (r3 >= r0) goto L_0x0189
            r4 = r10[r3]
            int r4 = r4 + r7
            r10[r4] = r2
            int r3 = r3 + 1
            goto L_0x017f
        L_0x0189:
            int r2 = r0 - r1
            r3 = 1
            if (r2 != r3) goto L_0x0190
            r10[r1] = r17
        L_0x0190:
            int r2 = r1 - r13
            int r3 = r14 - r0
            if (r2 > r3) goto L_0x01aa
            if (r13 >= r1) goto L_0x01a6
            int r2 = r15 + 1
            io.netty.handler.codec.compression.Bzip2DivSufSort$StackEntry r3 = new io.netty.handler.codec.compression.Bzip2DivSufSort$StackEntry
            r4 = r18
            r5 = 0
            r3.<init>(r0, r14, r4, r5)
            r11[r15] = r3
            r14 = r1
            goto L_0x01b9
        L_0x01a6:
            r4 = r18
            r13 = r0
            goto L_0x01bc
        L_0x01aa:
            r4 = r18
            r5 = 0
            if (r0 >= r14) goto L_0x01bb
            int r2 = r15 + 1
            io.netty.handler.codec.compression.Bzip2DivSufSort$StackEntry r3 = new io.netty.handler.codec.compression.Bzip2DivSufSort$StackEntry
            r3.<init>(r13, r1, r4, r5)
            r11[r15] = r3
            r13 = r0
        L_0x01b9:
            r15 = r2
            goto L_0x01bc
        L_0x01bb:
            r14 = r1
        L_0x01bc:
            r0 = r4
            goto L_0x001b
        L_0x01bf:
            r5 = 0
            if (r15 != 0) goto L_0x01c3
            return
        L_0x01c3:
            int r15 = r15 + -1
            r0 = r11[r15]
            int r13 = r0.a
            int r14 = r0.b
            int r0 = r0.c
            goto L_0x001b
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.codec.compression.Bzip2DivSufSort.lsIntroSort(int, int, int, int, int):void");
    }

    private void lsSort(int i, int i2, int i3) {
        int i4;
        int[] iArr = this.SA;
        int i5 = i3 + i;
        while (true) {
            int i6 = 0;
            if ((-i2) < iArr[0]) {
                int i7 = 0;
                int i8 = 0;
                do {
                    int i9 = iArr[i8];
                    if (i9 < 0) {
                        i8 -= i9;
                        i7 += i9;
                        continue;
                    } else {
                        if (i7 != 0) {
                            iArr[i8 + i7] = i7;
                            i4 = 0;
                        } else {
                            i4 = i7;
                        }
                        int i10 = iArr[i9 + i] + 1;
                        lsIntroSort(i, i5, i + i2, i8, i10);
                        i7 = i4;
                        i8 = i10;
                        continue;
                    }
                } while (i8 < i2);
                if (i7 != 0) {
                    iArr[i8 + i7] = i7;
                }
                int i11 = i5 - i;
                if (i2 < i11) {
                    do {
                        int i12 = iArr[i6];
                        if (i12 < 0) {
                            i6 -= i12;
                            continue;
                        } else {
                            int i13 = iArr[i12 + i] + 1;
                            while (i6 < i13) {
                                iArr[iArr[i6] + i] = i6;
                                i6++;
                            }
                            i6 = i13;
                            continue;
                        }
                    } while (i6 < i2);
                    return;
                }
                i5 += i11;
            } else {
                return;
            }
        }
    }

    private static class PartitionResult {
        final int first;
        final int last;

        PartitionResult(int i, int i2) {
            this.first = i;
            this.last = i2;
        }
    }

    private PartitionResult trPartition(int i, int i2, int i3, int i4, int i5, int i6) {
        int i7;
        int i8;
        int trGetC;
        int trGetC2;
        int trGetC3;
        int[] iArr = this.SA;
        int i9 = 0;
        int i10 = i4;
        while (i10 < i5) {
            i9 = trGetC(i, i2, i3, iArr[i10]);
            if (i9 != i6) {
                break;
            }
            i10++;
        }
        if (i10 < i5 && i9 < i6) {
            i7 = i10;
            while (true) {
                i10++;
                if (i10 >= i5 || (i9 = trGetC(i, i2, i3, iArr[i10])) > i6) {
                    break;
                } else if (i9 == i6) {
                    swapElements(iArr, i10, iArr, i7);
                    i7++;
                }
            }
        } else {
            i7 = i10;
        }
        int i11 = i5 - 1;
        while (i10 < i11) {
            i9 = trGetC(i, i2, i3, iArr[i11]);
            if (i9 != i6) {
                break;
            }
            i11--;
        }
        if (i10 < i11 && i9 > i6) {
            i8 = i11;
            while (true) {
                i11--;
                if (i10 >= i11 || (trGetC3 = trGetC(i, i2, i3, iArr[i11])) < i6) {
                    break;
                } else if (trGetC3 == i6) {
                    swapElements(iArr, i11, iArr, i8);
                    i8--;
                }
            }
        } else {
            i8 = i11;
        }
        while (i10 < i11) {
            swapElements(iArr, i10, iArr, i11);
            while (true) {
                i10++;
                if (i10 < i11 && (trGetC2 = trGetC(i, i2, i3, iArr[i10])) <= i6) {
                    if (trGetC2 == i6) {
                        swapElements(iArr, i10, iArr, i7);
                        i7++;
                    }
                }
            }
            while (true) {
                i11--;
                if (i10 < i11 && (trGetC = trGetC(i, i2, i3, iArr[i11])) >= i6) {
                    if (trGetC == i6) {
                        swapElements(iArr, i11, iArr, i8);
                        i8--;
                    }
                }
            }
        }
        if (i7 <= i8) {
            int i12 = i10 - 1;
            int i13 = i7 - i4;
            int i14 = i10 - i7;
            if (i13 > i14) {
                i13 = i14;
            }
            int i15 = i10 - i13;
            int i16 = i4;
            while (i13 > 0) {
                swapElements(iArr, i16, iArr, i15);
                i13--;
                i16++;
                i15++;
            }
            int i17 = i8 - i12;
            int i18 = (i5 - i8) - 1;
            if (i17 <= i18) {
                i18 = i17;
            }
            int i19 = i5 - i18;
            while (i18 > 0) {
                swapElements(iArr, i10, iArr, i19);
                i18--;
                i10++;
                i19++;
            }
            i4 += i14;
            i5 -= i17;
        }
        return new PartitionResult(i4, i5);
    }

    private void trCopy(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        int[] iArr = this.SA;
        int i8 = i5 - 1;
        int i9 = i4 - 1;
        while (i3 <= i9) {
            int i10 = iArr[i3] - i7;
            if (i10 < 0) {
                i10 += i2 - i;
            }
            int i11 = i + i10;
            if (iArr[i11] == i8) {
                i9++;
                iArr[i9] = i10;
                iArr[i11] = i9;
            }
            i3++;
        }
        int i12 = i6 - 1;
        int i13 = i9 + 1;
        while (i13 < i5) {
            int i14 = iArr[i12] - i7;
            if (i14 < 0) {
                i14 += i2 - i;
            }
            int i15 = i + i14;
            if (iArr[i15] == i8) {
                i5--;
                iArr[i5] = i14;
                iArr[i15] = i5;
            }
            i12--;
        }
    }

    private void trIntroSort(int i, int i2, int i3, int i4, int i5, TRBudget tRBudget, int i6) {
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int[] iArr;
        int i13;
        int i14;
        int i15;
        int i16;
        int i17;
        int i18;
        int i19;
        int i20;
        int i21;
        int i22;
        int i23;
        int i24;
        int i25;
        int i26;
        int i27;
        int i28;
        int i29;
        int i30;
        int i31;
        int i32 = i;
        int i33 = i3;
        TRBudget tRBudget2 = tRBudget;
        int i34 = i6;
        int[] iArr2 = this.SA;
        StackEntry[] stackEntryArr = new StackEntry[64];
        int i35 = i2;
        int i36 = i4;
        int i37 = i5;
        int trLog = trLog(i5 - i4);
        int i38 = 0;
        int i39 = 0;
        while (true) {
            if (trLog >= 0) {
                i7 = i38;
                int i40 = i36;
                int i41 = i37;
                int i42 = i41 - i40;
                if (i42 > 8) {
                    int i43 = trLog - 1;
                    if (trLog != 0) {
                        int i44 = trLog;
                        int i45 = i40;
                        int i46 = i41;
                        int i47 = i43;
                        int i48 = i45;
                        int i49 = i42;
                        int i50 = i3;
                        i36 = i48;
                        swapElements(iArr2, i36, iArr2, trPivot(i, i35, i3, i48, i46));
                        int trGetC = trGetC(i32, i35, i50, iArr2[i36]);
                        int i51 = i36 + 1;
                        while (true) {
                            if (i51 < i46) {
                                i8 = trGetC(i32, i35, i50, iArr2[i51]);
                                if (i8 != trGetC) {
                                    break;
                                }
                                i51++;
                                i39 = i8;
                            } else {
                                i8 = i39;
                                break;
                            }
                        }
                        if (i51 < i46 && i8 < trGetC) {
                            i9 = i8;
                            int i52 = 1;
                            i10 = i51;
                            while (true) {
                                i51 += i52;
                                if (i51 >= i46 || (i9 = trGetC(i32, i35, i50, iArr2[i51])) > trGetC) {
                                    break;
                                }
                                if (i9 == trGetC) {
                                    swapElements(iArr2, i51, iArr2, i10);
                                    i10++;
                                }
                                i52 = 1;
                            }
                        } else {
                            i9 = i8;
                            i10 = i51;
                        }
                        int i53 = i46 - 1;
                        while (i51 < i53) {
                            i9 = trGetC(i32, i35, i50, iArr2[i53]);
                            if (i9 != trGetC) {
                                break;
                            }
                            i53--;
                        }
                        if (i51 >= i53 || i9 <= trGetC) {
                            i12 = i10;
                            i39 = i9;
                            i11 = i53;
                        } else {
                            int i54 = i10;
                            int i55 = i53;
                            while (true) {
                                i53--;
                                if (i51 >= i53 || (i9 = trGetC(i32, i35, i50, iArr2[i53])) < trGetC) {
                                    i39 = i9;
                                    i11 = i55;
                                    i12 = i54;
                                } else if (i9 == trGetC) {
                                    swapElements(iArr2, i53, iArr2, i55);
                                    i55--;
                                }
                            }
                            i39 = i9;
                            i11 = i55;
                            i12 = i54;
                        }
                        while (i51 < i53) {
                            swapElements(iArr2, i51, iArr2, i53);
                            while (true) {
                                i51++;
                                int i56 = i53;
                                if (i51 < i53) {
                                    int trGetC2 = trGetC(i32, i35, i50, iArr2[i51]);
                                    if (trGetC2 > trGetC) {
                                        i39 = trGetC2;
                                        i53 = i56;
                                        break;
                                    }
                                    if (trGetC2 == trGetC) {
                                        swapElements(iArr2, i51, iArr2, i12);
                                        i12++;
                                    }
                                    i39 = trGetC2;
                                    i53 = i56;
                                } else {
                                    break;
                                }
                            }
                            while (true) {
                                i53--;
                                int i57 = i51;
                                if (i51 < i53) {
                                    int trGetC3 = trGetC(i32, i35, i50, iArr2[i53]);
                                    if (trGetC3 < trGetC) {
                                        i39 = trGetC3;
                                        i51 = i57;
                                        break;
                                    }
                                    if (trGetC3 == trGetC) {
                                        swapElements(iArr2, i53, iArr2, i11);
                                        i11--;
                                    }
                                    i39 = trGetC3;
                                    i51 = i57;
                                } else {
                                    break;
                                }
                            }
                        }
                        if (i12 <= i11) {
                            int i58 = i51 - 1;
                            int i59 = i12 - i36;
                            int i60 = i51 - i12;
                            if (i59 > i60) {
                                i59 = i60;
                            }
                            int i61 = i51;
                            int i62 = i51 - i59;
                            int i63 = i59;
                            int i64 = i36;
                            while (i63 > 0) {
                                swapElements(iArr2, i64, iArr2, i62);
                                i63--;
                                i64++;
                                i62++;
                            }
                            int i65 = i11 - i58;
                            int i66 = (i46 - i11) - 1;
                            if (i65 <= i66) {
                                i66 = i65;
                            }
                            int i67 = i66;
                            int i68 = i46 - i66;
                            int i69 = i61;
                            while (i67 > 0) {
                                swapElements(iArr2, i69, iArr2, i68);
                                i67--;
                                i69++;
                                i68++;
                            }
                            int i70 = i36 + i60;
                            int i71 = i46 - i65;
                            int trLog2 = iArr2[iArr2[i70] + i32] != trGetC ? trLog(i71 - i70) : -1;
                            int i72 = i70 - 1;
                            for (int i73 = i36; i73 < i70; i73++) {
                                iArr2[iArr2[i73] + i32] = i72;
                            }
                            if (i71 < i46) {
                                int i74 = i71 - 1;
                                for (int i75 = i70; i75 < i71; i75++) {
                                    iArr2[iArr2[i75] + i32] = i74;
                                }
                            }
                            int i76 = i70 - i36;
                            int i77 = i46 - i71;
                            if (i76 <= i77) {
                                int i78 = i71 - i70;
                                if (i77 <= i78) {
                                    iArr = iArr2;
                                    if (1 < i76) {
                                        stackEntryArr[i7] = new StackEntry(i35 + 1, i70, i71, trLog2);
                                        i13 = i47;
                                        stackEntryArr[i7 + 1] = new StackEntry(i35, i71, i46, i13);
                                        int i79 = i3;
                                        i22 = i7 + 2;
                                    } else {
                                        i13 = i47;
                                        if (1 < i77) {
                                            i14 = i7 + 1;
                                            stackEntryArr[i7] = new StackEntry(i35 + 1, i70, i71, trLog2);
                                            int i80 = i3;
                                        } else if (1 < i78) {
                                            i15 = i35 + 1;
                                            int i81 = i3;
                                            i16 = trLog2;
                                            i18 = i70;
                                            i17 = i7;
                                            iArr2 = iArr;
                                            i37 = i71;
                                        } else if (i7 != 0) {
                                            i22 = i7 - 1;
                                            StackEntry stackEntry = stackEntryArr[i22];
                                            i35 = stackEntry.a;
                                            i36 = stackEntry.b;
                                            i70 = stackEntry.c;
                                            i21 = stackEntry.d;
                                            int i82 = i3;
                                            iArr2 = iArr;
                                        } else {
                                            return;
                                        }
                                    }
                                } else {
                                    iArr = iArr2;
                                    i13 = i47;
                                    if (i76 <= i78) {
                                        if (1 < i76) {
                                            stackEntryArr[i7] = new StackEntry(i35, i71, i46, i13);
                                            stackEntryArr[i7 + 1] = new StackEntry(i35 + 1, i70, i71, trLog2);
                                            int i83 = i3;
                                            i22 = i7 + 2;
                                        } else if (1 < i78) {
                                            i17 = i7 + 1;
                                            stackEntryArr[i7] = new StackEntry(i35, i71, i46, i13);
                                            i19 = i35 + 1;
                                            int i84 = i3;
                                            i16 = trLog2;
                                            i18 = i70;
                                            iArr2 = iArr;
                                            i37 = i71;
                                        } else {
                                            int i85 = i3;
                                            i36 = i71;
                                            i70 = i46;
                                            i21 = i13;
                                            i22 = i7;
                                            iArr2 = iArr;
                                        }
                                    } else if (1 < i78) {
                                        stackEntryArr[i7] = new StackEntry(i35, i71, i46, i13);
                                        i20 = i7 + 2;
                                        stackEntryArr[i7 + 1] = new StackEntry(i35, i36, i70, i13);
                                        i23 = i35 + 1;
                                        int i86 = i3;
                                        i16 = trLog2;
                                        i17 = i20;
                                        i18 = i70;
                                        iArr2 = iArr;
                                        i37 = i71;
                                    } else {
                                        i22 = i7 + 1;
                                        stackEntryArr[i7] = new StackEntry(i35, i71, i46, i13);
                                        int i87 = i3;
                                    }
                                }
                                i21 = i13;
                                iArr2 = iArr;
                            } else {
                                iArr = iArr2;
                                i13 = i47;
                                int i88 = i71 - i70;
                                if (i76 <= i88) {
                                    if (1 < i77) {
                                        stackEntryArr[i7] = new StackEntry(i35 + 1, i70, i71, trLog2);
                                        stackEntryArr[i7 + 1] = new StackEntry(i35, i36, i70, i13);
                                        int i89 = i3;
                                        i14 = i7 + 2;
                                    } else if (1 < i76) {
                                        i22 = i7 + 1;
                                        stackEntryArr[i7] = new StackEntry(i35 + 1, i70, i71, trLog2);
                                        int i872 = i3;
                                        i21 = i13;
                                        iArr2 = iArr;
                                    } else if (1 < i88) {
                                        i15 = i35 + 1;
                                        int i812 = i3;
                                        i16 = trLog2;
                                        i18 = i70;
                                        i17 = i7;
                                        iArr2 = iArr;
                                        i37 = i71;
                                    } else {
                                        i14 = i7 + 1;
                                        stackEntryArr[i7] = new StackEntry(i35, i36, i46, i13);
                                        int i90 = i3;
                                    }
                                } else if (i77 <= i88) {
                                    if (1 < i77) {
                                        stackEntryArr[i7] = new StackEntry(i35, i36, i70, i13);
                                        stackEntryArr[i7 + 1] = new StackEntry(i35 + 1, i70, i71, trLog2);
                                        int i91 = i3;
                                        i36 = i71;
                                        i14 = i7 + 2;
                                    } else if (1 < i88) {
                                        i17 = i7 + 1;
                                        stackEntryArr[i7] = new StackEntry(i35, i36, i70, i13);
                                        i19 = i35 + 1;
                                        int i842 = i3;
                                        i16 = trLog2;
                                        i18 = i70;
                                        iArr2 = iArr;
                                        i37 = i71;
                                    } else {
                                        int i92 = i3;
                                        i21 = i13;
                                        i22 = i7;
                                        iArr2 = iArr;
                                    }
                                } else if (1 < i88) {
                                    stackEntryArr[i7] = new StackEntry(i35, i36, i70, i13);
                                    i20 = i7 + 2;
                                    stackEntryArr[i7 + 1] = new StackEntry(i35, i71, i46, i13);
                                    i23 = i35 + 1;
                                    int i862 = i3;
                                    i16 = trLog2;
                                    i17 = i20;
                                    i18 = i70;
                                    iArr2 = iArr;
                                    i37 = i71;
                                } else {
                                    i14 = i7 + 1;
                                    stackEntryArr[i7] = new StackEntry(i35, i36, i70, i13);
                                    int i802 = i3;
                                }
                                i70 = i46;
                                i21 = i13;
                                iArr2 = iArr;
                            }
                            i36 = i71;
                            i70 = i46;
                            i21 = i13;
                            iArr2 = iArr;
                        } else {
                            int[] iArr3 = iArr2;
                            if (!tRBudget2.update(i34, i49)) {
                                break;
                            }
                            i35++;
                            int i93 = i3;
                            i37 = i46;
                            i38 = i7;
                            iArr2 = iArr3;
                            trLog = i44;
                        }
                    } else if (!tRBudget2.update(i34, i42)) {
                        break;
                    } else {
                        int i94 = i41;
                        trHeapSort(i, i35, i3, i40, i42);
                        int i95 = i94 - 1;
                        while (i40 < i95) {
                            int i96 = i3;
                            int i97 = i40;
                            int trGetC4 = trGetC(i32, i35, i96, iArr2[i95]);
                            i95--;
                            while (i97 <= i95 && trGetC(i32, i35, i96, iArr2[i95]) == trGetC4) {
                                iArr2[i95] = iArr2[i95] ^ -1;
                                i95--;
                            }
                            i39 = trGetC4;
                            i40 = i97;
                        }
                        int i98 = i40;
                        int i99 = i3;
                        i36 = i98;
                        i37 = i94;
                        i38 = i7;
                        trLog = -3;
                    }
                } else if (!tRBudget2.update(i34, i42)) {
                    break;
                } else {
                    i24 = -3;
                    int i100 = i41;
                    trInsertionSort(i, i35, i3, i40, i41);
                    i25 = i40;
                    i37 = i100;
                    i38 = i7;
                }
            } else if (trLog != -1) {
                int i101 = i38;
                int i102 = i36;
                int i103 = i37;
                if (trLog == -2) {
                    int i104 = i101 - 1;
                    trCopy(i, i3, i102, stackEntryArr[i104].b, stackEntryArr[i104].c, i103, i35 - i32);
                    if (i104 != 0) {
                        i38 = i101 - 2;
                        StackEntry stackEntry2 = stackEntryArr[i38];
                        i35 = stackEntry2.a;
                        i25 = stackEntry2.b;
                        i37 = stackEntry2.c;
                        i24 = stackEntry2.d;
                    } else {
                        return;
                    }
                } else {
                    if (iArr2[i102] >= 0) {
                        do {
                            iArr2[iArr2[i102] + i32] = i102;
                            i102++;
                            if (i102 >= i103) {
                                break;
                            }
                        } while (iArr2[i102] < 0);
                    }
                    if (i102 < i103) {
                        int i105 = i102;
                        while (true) {
                            iArr2[i105] = iArr2[i105] ^ -1;
                            i26 = i105 + 1;
                            i27 = iArr2[i26];
                            if (i27 >= 0) {
                                break;
                            }
                            i105 = i26;
                        }
                        trLog = iArr2[i32 + i27] != iArr2[i27 + i35] ? trLog((i26 - i102) + 1) : -1;
                        i37 = i105 + 2;
                        if (i37 < i103) {
                            int i106 = i105 + 1;
                            for (int i107 = i102; i107 < i37; i107++) {
                                iArr2[iArr2[i107] + i32] = i106;
                            }
                        }
                        int i108 = i103 - i37;
                        if (i37 - i102 <= i108) {
                            i28 = i101 + 1;
                            stackEntryArr[i101] = new StackEntry(i35, i37, i103, -3);
                            i35++;
                            int i109 = i3;
                            i36 = i102;
                        } else if (1 < i108) {
                            i38 = i101 + 1;
                            stackEntryArr[i101] = new StackEntry(i35 + 1, i102, i37, trLog);
                            trLog = -3;
                            int i110 = i3;
                            int i111 = i103;
                            i36 = i37;
                            i37 = i111;
                        } else {
                            i35++;
                            int i112 = i3;
                            i36 = i102;
                            i28 = i101;
                        }
                    } else if (i101 != 0) {
                        i38 = i101 - 1;
                        StackEntry stackEntry3 = stackEntryArr[i38];
                        i35 = stackEntry3.a;
                        i25 = stackEntry3.b;
                        i37 = stackEntry3.c;
                        i24 = stackEntry3.d;
                    } else {
                        return;
                    }
                }
            } else if (!tRBudget2.update(i34, i37 - i36)) {
                i7 = i38;
                break;
            } else {
                int i113 = i35 - 1;
                int i114 = i37 - 1;
                int i115 = i38;
                int i116 = i37;
                int i117 = i36;
                int i118 = i36;
                int i119 = i116;
                int i120 = i113;
                PartitionResult trPartition = trPartition(i, i113, i3, i117, i119, i114);
                int i121 = trPartition.first;
                int i122 = trPartition.last;
                if (i118 < i121 || i122 < i119) {
                    if (i121 < i119) {
                        int i123 = i121 - 1;
                        for (int i124 = i118; i124 < i121; i124++) {
                            iArr2[iArr2[i124] + i32] = i123;
                        }
                    }
                    if (i122 < i119) {
                        int i125 = i122 - 1;
                        for (int i126 = i121; i126 < i122; i126++) {
                            iArr2[iArr2[i126] + i32] = i125;
                        }
                    }
                    stackEntryArr[i115] = new StackEntry(0, i121, i122, 0);
                    int i127 = i115 + 2;
                    stackEntryArr[i115 + 1] = new StackEntry(i120, i118, i119, -2);
                    int i128 = i121 - i118;
                    int i129 = i119 - i122;
                    if (i128 <= i129) {
                        if (1 < i128) {
                            stackEntryArr[i127] = new StackEntry(i35, i122, i119, trLog(i129));
                            int trLog3 = trLog(i128);
                            i37 = i121;
                            i38 = i115 + 3;
                            i25 = i118;
                            i24 = trLog3;
                        } else if (1 < i129) {
                            i24 = trLog(i129);
                            i38 = i127;
                            i37 = i119;
                            i25 = i122;
                        } else if (i127 != 0) {
                            i38 = i115 + 1;
                            StackEntry stackEntry4 = stackEntryArr[i38];
                            i29 = stackEntry4.a;
                            i30 = stackEntry4.b;
                            i37 = stackEntry4.c;
                            i31 = stackEntry4.d;
                        } else {
                            return;
                        }
                    } else if (1 < i129) {
                        stackEntryArr[i127] = new StackEntry(i35, i118, i121, trLog(i128));
                        int trLog4 = trLog(i129);
                        i37 = i119;
                        i38 = i115 + 3;
                        i25 = i122;
                        i24 = trLog4;
                    } else if (1 < i128) {
                        i24 = trLog(i128);
                        i37 = i121;
                        i38 = i127;
                        i25 = i118;
                    } else if (i127 != 0) {
                        i38 = i115 + 1;
                        StackEntry stackEntry5 = stackEntryArr[i38];
                        i29 = stackEntry5.a;
                        i30 = stackEntry5.b;
                        i37 = stackEntry5.c;
                        i31 = stackEntry5.d;
                    } else {
                        return;
                    }
                } else {
                    while (i118 < i119) {
                        iArr2[iArr2[i118] + i32] = i118;
                        i118++;
                    }
                    if (i115 != 0) {
                        i38 = i115 - 1;
                        StackEntry stackEntry6 = stackEntryArr[i38];
                        i29 = stackEntry6.a;
                        i30 = stackEntry6.b;
                        i37 = stackEntry6.c;
                        i31 = stackEntry6.d;
                    } else {
                        return;
                    }
                }
                i24 = i31;
                i35 = i29;
                i25 = i30;
            }
            int i130 = i3;
        }
        for (int i131 = 0; i131 < i7; i131++) {
            if (stackEntryArr[i131].d == -3) {
                lsUpdateGroup(i32, stackEntryArr[i131].b, stackEntryArr[i131].c);
            }
        }
    }

    private static class TRBudget {
        int budget;
        int chance;

        TRBudget(int i, int i2) {
            this.budget = i;
            this.chance = i2;
        }

        /* access modifiers changed from: package-private */
        public boolean update(int i, int i2) {
            int i3 = this.budget - i2;
            this.budget = i3;
            if (i3 <= 0) {
                int i4 = this.chance - 1;
                this.chance = i4;
                if (i4 == 0) {
                    return false;
                }
                this.budget = i3 + i;
            }
            return true;
        }
    }

    private void trSort(int i, int i2, int i3) {
        int i4 = i2;
        int[] iArr = this.SA;
        if ((-i4) < iArr[0]) {
            TRBudget tRBudget = new TRBudget(i4, ((trLog(i2) * 2) / 3) + 1);
            int i5 = 0;
            do {
                int i6 = iArr[i5];
                if (i6 < 0) {
                    i5 -= i6;
                    continue;
                } else {
                    int i7 = iArr[i + i6] + 1;
                    if (1 < i7 - i5) {
                        trIntroSort(i, i + i3, i + i4, i5, i7, tRBudget, i2);
                        if (tRBudget.chance == 0) {
                            if (i5 > 0) {
                                iArr[0] = -i5;
                            }
                            lsSort(i, i2, i3);
                            return;
                        }
                    }
                    i5 = i7;
                    continue;
                }
            } while (i5 < i4);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0027, code lost:
        r16 = true;
     */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x0229  */
    /* JADX WARNING: Removed duplicated region for block: B:154:0x0216 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x008d  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00ba  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int sortTypeBstar(int[] r30, int[] r31) {
        /*
            r29 = this;
            r10 = r29
            byte[] r11 = r10.T
            int[] r12 = r10.SA
            int r13 = r10.n
            r0 = 256(0x100, float:3.59E-43)
            int[] r1 = new int[r0]
            r14 = 1
            r2 = 1
        L_0x000e:
            r15 = 0
            r9 = 255(0xff, float:3.57E-43)
            if (r2 >= r13) goto L_0x0027
            int r3 = r2 + -1
            byte r3 = r11[r3]
            byte r4 = r11[r2]
            if (r3 == r4) goto L_0x0024
            r2 = r3 & 255(0xff, float:3.57E-43)
            r3 = r4 & 255(0xff, float:3.57E-43)
            if (r2 <= r3) goto L_0x0027
            r16 = 0
            goto L_0x0029
        L_0x0024:
            int r2 = r2 + 1
            goto L_0x000e
        L_0x0027:
            r16 = 1
        L_0x0029:
            int r17 = r13 + -1
            byte r2 = r11[r17]
            r3 = r2 & 255(0xff, float:3.57E-43)
            byte r4 = r11[r15]
            r5 = r4 & 255(0xff, float:3.57E-43)
            if (r3 < r5) goto L_0x003e
            if (r2 != r4) goto L_0x003a
            if (r16 == 0) goto L_0x003a
            goto L_0x003e
        L_0x003a:
            r2 = r13
            r3 = r17
            goto L_0x0072
        L_0x003e:
            if (r16 != 0) goto L_0x004e
            int r2 = BUCKET_BSTAR(r3, r5)
            r3 = r31[r2]
            int r3 = r3 + r14
            r31[r2] = r3
            int r2 = r13 + -1
            r12[r2] = r17
            goto L_0x0058
        L_0x004e:
            int r2 = BUCKET_B(r3, r5)
            r3 = r31[r2]
            int r3 = r3 + r14
            r31[r2] = r3
            r2 = r13
        L_0x0058:
            int r3 = r13 + -2
        L_0x005a:
            if (r3 < 0) goto L_0x0072
            byte r4 = r11[r3]
            r4 = r4 & r9
            int r5 = r3 + 1
            byte r5 = r11[r5]
            r5 = r5 & r9
            if (r4 > r5) goto L_0x0072
            int r4 = BUCKET_B(r4, r5)
            r5 = r31[r4]
            int r5 = r5 + r14
            r31[r4] = r5
            int r3 = r3 + -1
            goto L_0x005a
        L_0x0072:
            if (r3 < 0) goto L_0x00bc
        L_0x0074:
            byte r4 = r11[r3]
            r4 = r4 & r9
            r5 = r30[r4]
            int r5 = r5 + r14
            r30[r4] = r5
            int r4 = r3 + -1
            if (r4 < 0) goto L_0x008b
            byte r5 = r11[r4]
            r5 = r5 & r9
            byte r6 = r11[r3]
            r6 = r6 & r9
            if (r5 >= r6) goto L_0x0089
            goto L_0x008b
        L_0x0089:
            r3 = r4
            goto L_0x0074
        L_0x008b:
            if (r4 < 0) goto L_0x00ba
            byte r5 = r11[r4]
            r5 = r5 & r9
            byte r6 = r11[r3]
            r6 = r6 & r9
            int r5 = BUCKET_BSTAR(r5, r6)
            r6 = r31[r5]
            int r6 = r6 + r14
            r31[r5] = r6
            int r2 = r2 + -1
            r12[r2] = r4
            int r3 = r3 + -2
        L_0x00a2:
            if (r3 < 0) goto L_0x0072
            byte r4 = r11[r3]
            r4 = r4 & r9
            int r5 = r3 + 1
            byte r5 = r11[r5]
            r5 = r5 & r9
            if (r4 > r5) goto L_0x0072
            int r4 = BUCKET_B(r4, r5)
            r5 = r31[r4]
            int r5 = r5 + r14
            r31[r4] = r5
            int r3 = r3 + -1
            goto L_0x00a2
        L_0x00ba:
            r3 = r4
            goto L_0x0072
        L_0x00bc:
            int r8 = r13 - r2
            if (r8 != 0) goto L_0x00c9
            r0 = 0
        L_0x00c1:
            if (r0 >= r13) goto L_0x00c8
            r12[r0] = r0
            int r0 = r0 + 1
            goto L_0x00c1
        L_0x00c8:
            return r15
        L_0x00c9:
            r18 = -1
            r2 = 0
            r3 = -1
            r4 = 0
        L_0x00ce:
            if (r2 >= r0) goto L_0x00fb
            r5 = r30[r2]
            int r5 = r5 + r3
            int r3 = r3 + r4
            r30[r2] = r3
            int r3 = BUCKET_B(r2, r2)
            r3 = r31[r3]
            int r5 = r5 + r3
            int r3 = r2 + 1
            r6 = r3
        L_0x00e0:
            if (r6 >= r0) goto L_0x00f8
            int r7 = BUCKET_BSTAR(r2, r6)
            r7 = r31[r7]
            int r4 = r4 + r7
            int r7 = r2 << 8
            r7 = r7 | r6
            r31[r7] = r4
            int r7 = BUCKET_B(r2, r6)
            r7 = r31[r7]
            int r5 = r5 + r7
            int r6 = r6 + 1
            goto L_0x00e0
        L_0x00f8:
            r2 = r3
            r3 = r5
            goto L_0x00ce
        L_0x00fb:
            int r19 = r13 - r8
            int r2 = r8 + -2
        L_0x00ff:
            if (r2 < 0) goto L_0x011a
            int r3 = r19 + r2
            r3 = r12[r3]
            byte r4 = r11[r3]
            r4 = r4 & r9
            int r3 = r3 + r14
            byte r3 = r11[r3]
            r3 = r3 & r9
            int r3 = BUCKET_BSTAR(r4, r3)
            r4 = r31[r3]
            int r4 = r4 - r14
            r31[r3] = r4
            r12[r4] = r2
            int r2 = r2 + -1
            goto L_0x00ff
        L_0x011a:
            int r2 = r19 + r8
            int r2 = r2 - r14
            r2 = r12[r2]
            byte r3 = r11[r2]
            r3 = r3 & r9
            int r2 = r2 + r14
            byte r2 = r11[r2]
            r2 = r2 & r9
            int r2 = BUCKET_BSTAR(r3, r2)
            r3 = r31[r2]
            int r3 = r3 - r14
            r31[r2] = r3
            int r7 = r8 + -1
            r12[r3] = r7
            int r2 = r8 * 2
            int r2 = r13 - r2
            if (r2 > r0) goto L_0x0140
            r21 = r1
            r20 = 256(0x100, float:3.59E-43)
            r22 = 0
            goto L_0x0146
        L_0x0140:
            r20 = r2
            r22 = r8
            r21 = r12
        L_0x0146:
            r0 = r8
            r6 = 255(0xff, float:3.57E-43)
        L_0x0149:
            if (r0 <= 0) goto L_0x01a1
            r3 = r0
            r5 = 255(0xff, float:3.57E-43)
        L_0x014e:
            if (r6 >= r5) goto L_0x0195
            int r0 = BUCKET_BSTAR(r6, r5)
            r23 = r31[r0]
            int r0 = r3 - r23
            if (r14 >= r0) goto L_0x0181
            r0 = r12[r23]
            if (r0 != r7) goto L_0x0161
            r24 = 1
            goto L_0x0163
        L_0x0161:
            r24 = 0
        L_0x0163:
            r25 = 2
            r0 = r29
            r1 = r19
            r2 = r23
            r4 = r21
            r26 = r5
            r5 = r22
            r27 = r6
            r6 = r20
            r28 = r7
            r7 = r25
            r15 = r8
            r8 = r24
            r9 = r13
            r0.subStringSort(r1, r2, r3, r4, r5, r6, r7, r8, r9)
            goto L_0x0188
        L_0x0181:
            r26 = r5
            r27 = r6
            r28 = r7
            r15 = r8
        L_0x0188:
            int r5 = r26 + -1
            r8 = r15
            r3 = r23
            r6 = r27
            r7 = r28
            r9 = 255(0xff, float:3.57E-43)
            r15 = 0
            goto L_0x014e
        L_0x0195:
            r27 = r6
            r28 = r7
            r15 = r8
            int r6 = r27 + -1
            r0 = r3
            r9 = 255(0xff, float:3.57E-43)
            r15 = 0
            goto L_0x0149
        L_0x01a1:
            r28 = r7
            r15 = r8
        L_0x01a4:
            if (r7 < 0) goto L_0x01de
            r0 = r12[r7]
            if (r0 < 0) goto L_0x01c4
            r0 = r7
        L_0x01ab:
            r1 = r12[r0]
            int r8 = r15 + r1
            r12[r8] = r0
            int r1 = r0 + -1
            if (r1 < 0) goto L_0x01bc
            r2 = r12[r1]
            if (r2 >= 0) goto L_0x01ba
            goto L_0x01bc
        L_0x01ba:
            r0 = r1
            goto L_0x01ab
        L_0x01bc:
            int r2 = r1 - r7
            r12[r0] = r2
            if (r1 > 0) goto L_0x01c3
            goto L_0x01de
        L_0x01c3:
            r7 = r1
        L_0x01c4:
            r0 = r7
        L_0x01c5:
            r1 = r12[r0]
            r1 = r1 ^ -1
            r12[r0] = r1
            int r8 = r15 + r1
            r12[r8] = r7
            int r1 = r0 + -1
            r2 = r12[r1]
            if (r2 < 0) goto L_0x01dc
            int r8 = r15 + r2
            r12[r8] = r7
            int r7 = r0 + -2
            goto L_0x01a4
        L_0x01dc:
            r0 = r1
            goto L_0x01c5
        L_0x01de:
            r10.trSort(r15, r15, r14)
            byte r0 = r11[r17]
            r1 = r0 & 255(0xff, float:3.57E-43)
            r2 = 0
            byte r2 = r11[r2]
            r3 = r2 & 255(0xff, float:3.57E-43)
            if (r1 < r3) goto L_0x01f7
            if (r0 != r2) goto L_0x01f1
            if (r16 == 0) goto L_0x01f1
            goto L_0x01f7
        L_0x01f1:
            r8 = r15
            r13 = r17
        L_0x01f4:
            r1 = 255(0xff, float:3.57E-43)
            goto L_0x0216
        L_0x01f7:
            if (r16 != 0) goto L_0x0202
            int r8 = r15 + -1
            int r0 = r15 + r8
            r0 = r12[r0]
            r12[r0] = r17
            goto L_0x0203
        L_0x0202:
            r8 = r15
        L_0x0203:
            int r13 = r13 + -2
        L_0x0205:
            if (r13 < 0) goto L_0x01f4
            byte r0 = r11[r13]
            r1 = 255(0xff, float:3.57E-43)
            r0 = r0 & r1
            int r2 = r13 + 1
            byte r2 = r11[r2]
            r2 = r2 & r1
            if (r0 > r2) goto L_0x0216
            int r13 = r13 + -1
            goto L_0x0205
        L_0x0216:
            if (r13 < 0) goto L_0x0240
        L_0x0218:
            int r13 = r13 + -1
            if (r13 < 0) goto L_0x0227
            byte r0 = r11[r13]
            r0 = r0 & r1
            int r2 = r13 + 1
            byte r2 = r11[r2]
            r2 = r2 & r1
            if (r0 < r2) goto L_0x0227
            goto L_0x0218
        L_0x0227:
            if (r13 < 0) goto L_0x0216
            int r8 = r8 + -1
            int r0 = r15 + r8
            r0 = r12[r0]
            r12[r0] = r13
        L_0x0231:
            int r13 = r13 + -1
            if (r13 < 0) goto L_0x0216
            byte r0 = r11[r13]
            r0 = r0 & r1
            int r2 = r13 + 1
            byte r2 = r11[r2]
            r2 = r2 & r1
            if (r0 > r2) goto L_0x0216
            goto L_0x0231
        L_0x0240:
            r7 = r28
            r9 = 255(0xff, float:3.57E-43)
        L_0x0244:
            if (r9 < 0) goto L_0x0290
            r0 = 255(0xff, float:3.57E-43)
        L_0x0248:
            if (r9 >= r0) goto L_0x0270
            int r2 = BUCKET_B(r9, r0)
            r2 = r31[r2]
            int r2 = r17 - r2
            int r3 = BUCKET_B(r9, r0)
            int r17 = r17 + 1
            r31[r3] = r17
            int r3 = BUCKET_BSTAR(r9, r0)
            r3 = r31[r3]
            r17 = r2
        L_0x0262:
            if (r3 > r7) goto L_0x026d
            r2 = r12[r7]
            r12[r17] = r2
            int r17 = r17 + -1
            int r7 = r7 + -1
            goto L_0x0262
        L_0x026d:
            int r0 = r0 + -1
            goto L_0x0248
        L_0x0270:
            int r0 = BUCKET_B(r9, r9)
            r0 = r31[r0]
            int r0 = r17 - r0
            int r2 = BUCKET_B(r9, r9)
            int r17 = r17 + 1
            r31[r2] = r17
            if (r9 >= r1) goto L_0x028b
            int r2 = r9 + 1
            int r2 = BUCKET_BSTAR(r9, r2)
            int r0 = r0 + r14
            r31[r2] = r0
        L_0x028b:
            r17 = r30[r9]
            int r9 = r9 + -1
            goto L_0x0244
        L_0x0290:
            return r15
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.codec.compression.Bzip2DivSufSort.sortTypeBstar(int[], int[]):int");
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r4v5, types: [byte] */
    /* JADX WARNING: type inference failed for: r14v5, types: [byte] */
    /* JADX WARNING: type inference failed for: r9v0, types: [byte] */
    /* JADX WARNING: type inference failed for: r10v1, types: [byte] */
    /* JADX WARNING: type inference failed for: r10v4, types: [byte] */
    /* JADX WARNING: type inference failed for: r11v0, types: [byte] */
    /* JADX WARNING: type inference failed for: r9v6, types: [byte] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int constructBWT(int[] r13, int[] r14) {
        /*
            r12 = this;
            byte[] r0 = r12.T
            int[] r1 = r12.SA
            int r2 = r12.n
            r3 = 0
            r4 = 254(0xfe, float:3.56E-43)
            r5 = 0
            r6 = 0
        L_0x000b:
            r7 = -1
            if (r4 < 0) goto L_0x0063
            int r5 = r4 + 1
            int r6 = BUCKET_BSTAR(r4, r5)
            r6 = r14[r6]
            r5 = r13[r5]
            r7 = 0
            r8 = -1
        L_0x001a:
            if (r6 > r5) goto L_0x005e
            r9 = r1[r5]
            if (r9 < 0) goto L_0x0057
            int r10 = r9 + -1
            if (r10 >= 0) goto L_0x0026
            int r10 = r2 + -1
        L_0x0026:
            byte r11 = r0[r10]
            r11 = r11 & 255(0xff, float:3.57E-43)
            if (r11 > r4) goto L_0x005b
            r9 = r9 ^ -1
            r1[r5] = r9
            if (r10 <= 0) goto L_0x003c
            int r9 = r10 + -1
            byte r9 = r0[r9]
            r9 = r9 & 255(0xff, float:3.57E-43)
            if (r9 <= r11) goto L_0x003c
            r10 = r10 ^ -1
        L_0x003c:
            if (r8 != r11) goto L_0x0043
            int r7 = r7 + -1
            r1[r7] = r10
            goto L_0x005b
        L_0x0043:
            if (r8 < 0) goto L_0x004b
            int r8 = BUCKET_B(r8, r4)
            r14[r8] = r7
        L_0x004b:
            int r7 = BUCKET_B(r11, r4)
            r7 = r14[r7]
            int r7 = r7 + -1
            r1[r7] = r10
            r8 = r11
            goto L_0x005b
        L_0x0057:
            r9 = r9 ^ -1
            r1[r5] = r9
        L_0x005b:
            int r5 = r5 + -1
            goto L_0x001a
        L_0x005e:
            int r4 = r4 + -1
            r5 = r7
            r6 = r8
            goto L_0x000b
        L_0x0063:
            r14 = -1
        L_0x0064:
            if (r3 >= r2) goto L_0x00b0
            r4 = r1[r3]
            if (r4 < 0) goto L_0x009b
            int r8 = r4 + -1
            if (r8 >= 0) goto L_0x0070
            int r8 = r2 + -1
        L_0x0070:
            byte r9 = r0[r8]
            r9 = r9 & 255(0xff, float:3.57E-43)
            int r10 = r8 + 1
            byte r10 = r0[r10]
            r10 = r10 & 255(0xff, float:3.57E-43)
            if (r9 < r10) goto L_0x009d
            if (r8 <= 0) goto L_0x0088
            int r10 = r8 + -1
            byte r10 = r0[r10]
            r10 = r10 & 255(0xff, float:3.57E-43)
            if (r10 >= r9) goto L_0x0088
            r8 = r8 ^ -1
        L_0x0088:
            if (r9 != r6) goto L_0x008f
            int r5 = r5 + 1
            r1[r5] = r8
            goto L_0x009d
        L_0x008f:
            if (r6 == r7) goto L_0x0093
            r13[r6] = r5
        L_0x0093:
            r5 = r13[r9]
            int r5 = r5 + 1
            r1[r5] = r8
            r6 = r9
            goto L_0x009d
        L_0x009b:
            r4 = r4 ^ -1
        L_0x009d:
            if (r4 != 0) goto L_0x00a7
            int r14 = r2 + -1
            byte r14 = r0[r14]
            r1[r3] = r14
            r14 = r3
            goto L_0x00ad
        L_0x00a7:
            int r4 = r4 + -1
            byte r4 = r0[r4]
            r1[r3] = r4
        L_0x00ad:
            int r3 = r3 + 1
            goto L_0x0064
        L_0x00b0:
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.codec.compression.Bzip2DivSufSort.constructBWT(int[], int[]):int");
    }

    public int bwt() {
        int[] iArr = this.SA;
        byte[] bArr = this.T;
        int i = this.n;
        int[] iArr2 = new int[256];
        int[] iArr3 = new int[65536];
        if (i == 0) {
            return 0;
        }
        if (i == 1) {
            iArr[0] = bArr[0];
            return 0;
        } else if (sortTypeBstar(iArr2, iArr3) > 0) {
            return constructBWT(iArr2, iArr3);
        } else {
            return 0;
        }
    }
}
