package org.bouncycastle.pqc.crypto.bike;

import java.security.SecureRandom;
import org.bouncycastle.crypto.digests.SHA3Digest;
import org.bouncycastle.crypto.digests.SHAKEDigest;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Bytes;

class BIKEEngine {
    private int L_BYTE;
    private int R2_BYTE;
    private int R_BYTE;
    private final BIKERing bikeRing;
    private int hw;
    private int nbIter;
    private int r;
    private int t;
    private int tau;
    private int w;

    public BIKEEngine(int i, int i2, int i3, int i4, int i5, int i6) {
        this.r = i;
        this.w = i2;
        this.t = i3;
        this.nbIter = i5;
        this.tau = i6;
        this.hw = i2 / 2;
        this.L_BYTE = i4 / 8;
        this.R_BYTE = (i + 7) >>> 3;
        this.R2_BYTE = ((i * 2) + 7) >>> 3;
        this.bikeRing = new BIKERing(i);
    }

    private void BFIter(byte[] bArr, byte[] bArr2, int i, int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4, byte[] bArr3, byte[] bArr4, byte[] bArr5) {
        byte[] bArr6 = bArr;
        byte[] bArr7 = bArr5;
        ctrAll(iArr3, bArr, bArr7);
        byte b = bArr7[0] & 255;
        byte b2 = (byte) (((b - i) >> 31) + 1);
        bArr2[0] = (byte) (bArr2[0] ^ b2);
        bArr3[0] = b2;
        bArr4[0] = (byte) (((b - (i - this.tau)) >> 31) + 1);
        int i2 = 1;
        while (true) {
            int i3 = this.r;
            if (i2 >= i3) {
                break;
            }
            byte b3 = bArr7[i2] & 255;
            int i4 = i3 - i2;
            byte b4 = (byte) (((b3 - i) >> 31) + 1);
            bArr2[i4] = (byte) (bArr2[i4] ^ b4);
            bArr3[i2] = b4;
            bArr4[i2] = (byte) (((b3 - (i - this.tau)) >> 31) + 1);
            i2++;
        }
        ctrAll(iArr4, bArr, bArr7);
        byte b5 = bArr7[0] & 255;
        int i5 = this.r;
        byte b6 = (byte) (((b5 - i) >> 31) + 1);
        bArr2[i5] = (byte) (bArr2[i5] ^ b6);
        bArr3[i5] = b6;
        bArr4[i5] = (byte) (((b5 - (i - this.tau)) >> 31) + 1);
        int i6 = 1;
        while (true) {
            int i7 = this.r;
            if (i6 >= i7) {
                break;
            }
            byte b7 = bArr7[i6] & 255;
            int i8 = (i7 + i7) - i6;
            byte b8 = (byte) (((b7 - i) >> 31) + 1);
            bArr2[i8] = (byte) (bArr2[i8] ^ b8);
            bArr3[i7 + i6] = b8;
            bArr4[i7 + i6] = (byte) (((b7 - (i - this.tau)) >> 31) + 1);
            i6++;
        }
        for (int i9 = 0; i9 < this.r * 2; i9++) {
            recomputeSyndrome(bArr, i9, iArr, iArr2, bArr3[i9] != 0);
        }
    }

    private void BFIter2(byte[] bArr, byte[] bArr2, int i, int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4, byte[] bArr3) {
        byte[] bArr4 = bArr;
        byte[] bArr5 = bArr3;
        int[] iArr5 = new int[(this.r * 2)];
        ctrAll(iArr3, bArr, bArr5);
        int i2 = (((bArr5[0] & 255) - i) >> 31) + 1;
        bArr2[0] = (byte) (bArr2[0] ^ ((byte) i2));
        iArr5[0] = i2;
        int i3 = 1;
        while (true) {
            int i4 = this.r;
            if (i3 >= i4) {
                break;
            }
            int i5 = (((bArr5[i3] & 255) - i) >> 31) + 1;
            int i6 = i4 - i3;
            bArr2[i6] = (byte) (bArr2[i6] ^ ((byte) i5));
            iArr5[i3] = i5;
            i3++;
        }
        ctrAll(iArr4, bArr, bArr5);
        int i7 = (((bArr5[0] & 255) - i) >> 31) + 1;
        int i8 = this.r;
        bArr2[i8] = (byte) (bArr2[i8] ^ ((byte) i7));
        iArr5[i8] = i7;
        int i9 = 1;
        while (true) {
            int i10 = this.r;
            if (i9 >= i10) {
                break;
            }
            int i11 = (((bArr5[i9] & 255) - i) >> 31) + 1;
            int i12 = (i10 + i10) - i9;
            bArr2[i12] = (byte) (bArr2[i12] ^ ((byte) i11));
            iArr5[i10 + i9] = i11;
            i9++;
        }
        for (int i13 = 0; i13 < this.r * 2; i13++) {
            recomputeSyndrome(bArr, i13, iArr, iArr2, iArr5[i13] == 1);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v0, resolved type: int[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v3, resolved type: int} */
    /* JADX WARNING: type inference failed for: r10v0 */
    /* JADX WARNING: type inference failed for: r4v2, types: [boolean] */
    /* JADX WARNING: type inference failed for: r4v3 */
    /* JADX WARNING: type inference failed for: r4v4 */
    /* JADX WARNING: type inference failed for: r10v1, types: [boolean] */
    /* JADX WARNING: type inference failed for: r10v2 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void BFMaskedIter(byte[] r13, byte[] r14, byte[] r15, int r16, int[] r17, int[] r18, int[] r19, int[] r20) {
        /*
            r12 = this;
            r6 = r12
            r7 = r13
            r0 = r14
            r1 = r16
            int r2 = r6.r
            int r2 = r2 * 2
            int[] r8 = new int[r2]
            r9 = 0
            r2 = 0
        L_0x000d:
            int r3 = r6.r
            r10 = 1
            if (r2 >= r3) goto L_0x002b
            byte r3 = r15[r2]
            if (r3 != r10) goto L_0x0026
            r3 = r19
            int r4 = r12.ctr(r3, r13, r2)
            if (r4 < r1) goto L_0x001f
            goto L_0x0020
        L_0x001f:
            r10 = 0
        L_0x0020:
            r12.updateNewErrorIndex(r14, r2, r10)
            r8[r2] = r10
            goto L_0x0028
        L_0x0026:
            r3 = r19
        L_0x0028:
            int r2 = r2 + 1
            goto L_0x000d
        L_0x002b:
            r2 = 0
        L_0x002c:
            int r3 = r6.r
            if (r2 >= r3) goto L_0x0051
            int r3 = r3 + r2
            byte r3 = r15[r3]
            if (r3 != r10) goto L_0x004c
            r3 = r20
            int r4 = r12.ctr(r3, r13, r2)
            if (r4 < r1) goto L_0x003f
            r4 = 1
            goto L_0x0040
        L_0x003f:
            r4 = 0
        L_0x0040:
            int r5 = r6.r
            int r5 = r5 + r2
            r12.updateNewErrorIndex(r14, r5, r4)
            int r5 = r6.r
            int r5 = r5 + r2
            r8[r5] = r4
            goto L_0x004e
        L_0x004c:
            r3 = r20
        L_0x004e:
            int r2 = r2 + 1
            goto L_0x002c
        L_0x0051:
            r11 = 0
        L_0x0052:
            int r0 = r6.r
            int r0 = r0 * 2
            if (r11 >= r0) goto L_0x006c
            r0 = r8[r11]
            if (r0 != r10) goto L_0x005e
            r5 = 1
            goto L_0x005f
        L_0x005e:
            r5 = 0
        L_0x005f:
            r0 = r12
            r1 = r13
            r2 = r11
            r3 = r17
            r4 = r18
            r0.recomputeSyndrome(r1, r2, r3, r4, r5)
            int r11 = r11 + 1
            goto L_0x0052
        L_0x006c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.pqc.crypto.bike.BIKEEngine.BFMaskedIter(byte[], byte[], byte[], int, int[], int[], int[], int[]):void");
    }

    private byte[] BGFDecoder(byte[] bArr, int[] iArr, int[] iArr2) {
        byte[] bArr2 = new byte[(this.r * 2)];
        int[] columnFromCompactVersion = getColumnFromCompactVersion(iArr);
        int[] columnFromCompactVersion2 = getColumnFromCompactVersion(iArr2);
        int i = this.r;
        byte[] bArr3 = new byte[(i * 2)];
        byte[] bArr4 = bArr;
        byte[] bArr5 = bArr2;
        byte[] bArr6 = new byte[(i * 2)];
        byte[] bArr7 = new byte[i];
        byte[] bArr8 = bArr3;
        BFIter(bArr4, bArr5, threshold(BIKEUtils.getHammingWeight(bArr), this.r), iArr, iArr2, columnFromCompactVersion, columnFromCompactVersion2, bArr3, bArr6, bArr7);
        int[] iArr3 = iArr;
        int[] iArr4 = iArr2;
        int[] iArr5 = columnFromCompactVersion;
        int[] iArr6 = columnFromCompactVersion2;
        BFMaskedIter(bArr4, bArr5, bArr8, ((this.hw + 1) / 2) + 1, iArr3, iArr4, iArr5, iArr6);
        BFMaskedIter(bArr4, bArr5, bArr6, ((this.hw + 1) / 2) + 1, iArr3, iArr4, iArr5, iArr6);
        for (int i2 = 1; i2 < this.nbIter; i2++) {
            Arrays.fill(bArr8, (byte) 0);
            BFIter2(bArr, bArr2, threshold(BIKEUtils.getHammingWeight(bArr), this.r), iArr, iArr2, columnFromCompactVersion, columnFromCompactVersion2, bArr7);
        }
        if (BIKEUtils.getHammingWeight(bArr) == 0) {
            return bArr2;
        }
        return null;
    }

    private byte[] computeSyndrome(byte[] bArr, byte[] bArr2) {
        long[] create = this.bikeRing.create();
        long[] create2 = this.bikeRing.create();
        this.bikeRing.decodeBytes(bArr, create);
        this.bikeRing.decodeBytes(bArr2, create2);
        this.bikeRing.multiply(create, create2, create);
        return this.bikeRing.encodeBitsTransposed(create);
    }

    private void convertToCompact(int[] iArr, byte[] bArr) {
        int i;
        int i2 = 0;
        int i3 = 0;
        while (i2 < this.R_BYTE) {
            int i4 = 0;
            while (i4 < 8 && (i = (i2 * 8) + i4) != this.r) {
                int i5 = (bArr[i2] >> i4) & 1;
                int i6 = -i5;
                iArr[i3] = (i & i6) | ((i6 ^ -1) & iArr[i3]);
                i3 = (i3 + i5) % this.hw;
                i4++;
            }
            i2++;
        }
    }

    private int ctr(int[] iArr, byte[] bArr, int i) {
        int i2 = this.hw - 4;
        int i3 = 0;
        int i4 = 0;
        while (i3 <= i2) {
            int i5 = this.r;
            int i6 = (iArr[i3] + i) - i5;
            int i7 = (iArr[i3 + 1] + i) - i5;
            int i8 = (iArr[i3 + 2] + i) - i5;
            int i9 = (iArr[i3 + 3] + i) - i5;
            i4 = i4 + (bArr[i6 + ((i6 >> 31) & i5)] & 255) + (bArr[i7 + ((i7 >> 31) & i5)] & 255) + (bArr[i8 + ((i8 >> 31) & i5)] & 255) + (bArr[i9 + (i5 & (i9 >> 31))] & 255);
            i3 += 4;
        }
        while (i3 < this.hw) {
            int i10 = this.r;
            int i11 = (iArr[i3] + i) - i10;
            i4 += bArr[i11 + (i10 & (i11 >> 31))] & 255;
            i3++;
        }
        return i4;
    }

    private void ctrAll(int[] iArr, byte[] bArr, byte[] bArr2) {
        int i = iArr[0];
        int i2 = this.r - i;
        System.arraycopy(bArr, i, bArr2, 0, i2);
        System.arraycopy(bArr, 0, bArr2, i2, i);
        for (int i3 = 1; i3 < this.hw; i3++) {
            int i4 = iArr[i3];
            int i5 = this.r - i4;
            int i6 = i5 - 4;
            int i7 = 0;
            while (i7 <= i6) {
                int i8 = i4 + i7;
                bArr2[i7] = (byte) (bArr2[i7] + (bArr[i8] & 255));
                int i9 = i7 + 1;
                bArr2[i9] = (byte) (bArr2[i9] + (bArr[i8 + 1] & 255));
                int i10 = i7 + 2;
                bArr2[i10] = (byte) (bArr2[i10] + (bArr[i8 + 2] & 255));
                int i11 = i7 + 3;
                bArr2[i11] = (byte) (bArr2[i11] + (bArr[i8 + 3] & 255));
                i7 += 4;
            }
            while (i7 < i5) {
                bArr2[i7] = (byte) (bArr2[i7] + (bArr[i4 + i7] & 255));
                i7++;
            }
            int i12 = this.r - 4;
            int i13 = i5;
            while (i13 <= i12) {
                bArr2[i13] = (byte) (bArr2[i13] + (bArr[i13 - i5] & 255));
                int i14 = i13 + 1;
                bArr2[i14] = (byte) (bArr2[i14] + (bArr[i14 - i5] & 255));
                int i15 = i13 + 2;
                bArr2[i15] = (byte) (bArr2[i15] + (bArr[i15 - i5] & 255));
                int i16 = i13 + 3;
                bArr2[i16] = (byte) (bArr2[i16] + (bArr[i16 - i5] & 255));
                i13 += 4;
            }
            while (i13 < this.r) {
                bArr2[i13] = (byte) (bArr2[i13] + (bArr[i13 - i5] & 255));
                i13++;
            }
        }
    }

    private byte[] functionH(byte[] bArr) {
        byte[] bArr2 = new byte[(this.R_BYTE * 2)];
        SHAKEDigest sHAKEDigest = new SHAKEDigest(256);
        sHAKEDigest.update(bArr, 0, bArr.length);
        BIKEUtils.generateRandomByteArray(bArr2, this.r * 2, this.t, sHAKEDigest);
        return bArr2;
    }

    private void functionK(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
        byte[] bArr5 = new byte[48];
        SHA3Digest sHA3Digest = new SHA3Digest((int) KyberEngine.KyberPolyBytes);
        sHA3Digest.update(bArr, 0, bArr.length);
        sHA3Digest.update(bArr2, 0, bArr2.length);
        sHA3Digest.update(bArr3, 0, bArr3.length);
        sHA3Digest.doFinal(bArr5, 0);
        System.arraycopy(bArr5, 0, bArr4, 0, this.L_BYTE);
    }

    private void functionL(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        byte[] bArr4 = new byte[48];
        SHA3Digest sHA3Digest = new SHA3Digest((int) KyberEngine.KyberPolyBytes);
        sHA3Digest.update(bArr, 0, bArr.length);
        sHA3Digest.update(bArr2, 0, bArr2.length);
        sHA3Digest.doFinal(bArr4, 0);
        System.arraycopy(bArr4, 0, bArr3, 0, this.L_BYTE);
    }

    private int[] getColumnFromCompactVersion(int[] iArr) {
        int[] iArr2 = new int[this.hw];
        int i = 0;
        if (iArr[0] != 0) {
            while (true) {
                int i2 = this.hw;
                if (i >= i2) {
                    break;
                }
                iArr2[i] = this.r - iArr[(i2 - 1) - i];
                i++;
            }
        } else {
            iArr2[0] = 0;
            int i3 = 1;
            while (true) {
                int i4 = this.hw;
                if (i3 >= i4) {
                    break;
                }
                iArr2[i3] = this.r - iArr[i4 - i3];
                i3++;
            }
        }
        return iArr2;
    }

    private void recomputeSyndrome(byte[] bArr, int i, int[] iArr, int[] iArr2, boolean z) {
        int i2 = 0;
        if (i < this.r) {
            while (i2 < this.hw) {
                int i3 = iArr[i2];
                if (i3 <= i) {
                    int i4 = i - i3;
                    bArr[i4] = bArr[i4] ^ z ? (byte) 1 : 0;
                } else {
                    int i5 = (this.r + i) - i3;
                    bArr[i5] = bArr[i5] ^ z ? (byte) 1 : 0;
                }
                i2++;
            }
            return;
        }
        while (i2 < this.hw) {
            int i6 = iArr2[i2];
            int i7 = this.r;
            if (i6 <= i - i7) {
                int i8 = (i - i7) - i6;
                bArr[i8] = bArr[i8] ^ z ? (byte) 1 : 0;
            } else {
                int i9 = (i7 - i6) + (i - i7);
                bArr[i9] = bArr[i9] ^ z ? (byte) 1 : 0;
            }
            i2++;
        }
    }

    private void splitEBytes(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        byte b = this.r & 7;
        int i = 0;
        System.arraycopy(bArr, 0, bArr2, 0, this.R_BYTE - 1);
        int i2 = this.R_BYTE;
        byte b2 = bArr[i2 - 1];
        byte b3 = (byte) (-1 << b);
        bArr2[i2 - 1] = (byte) ((b3 ^ -1) & b2);
        byte b4 = (byte) (b2 & b3);
        while (true) {
            int i3 = this.R_BYTE;
            if (i < i3) {
                byte b5 = bArr[i3 + i];
                bArr3[i] = (byte) (((b4 & 255) >>> b) | (b5 << (8 - b)));
                i++;
                b4 = b5;
            } else {
                return;
            }
        }
    }

    private int threshold(int i, int i2) {
        if (i2 == 12323) {
            return thresholdFromParameters(i, 0.0069722d, 13.53d, 36);
        }
        if (i2 == 24659) {
            return thresholdFromParameters(i, 0.005265d, 15.2588d, 52);
        }
        if (i2 == 40973) {
            return thresholdFromParameters(i, 0.00402312d, 17.8785d, 69);
        }
        throw new IllegalArgumentException();
    }

    private static int thresholdFromParameters(int i, double d, double d2, int i2) {
        double d3 = (double) i;
        Double.isNaN(d3);
        return Math.max(i2, (int) Math.floor((d * d3) + d2));
    }

    private void updateNewErrorIndex(byte[] bArr, int i, boolean z) {
        int i2;
        if (!(i == 0 || i == (i2 = this.r))) {
            i = i > i2 ? ((i2 * 2) - i) + i2 : i2 - i;
        }
        bArr[i] = z ^ bArr[i] ? (byte) 1 : 0;
    }

    public void decaps(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5, byte[] bArr6) {
        int i = this.hw;
        int[] iArr = new int[i];
        int[] iArr2 = new int[i];
        convertToCompact(iArr, bArr2);
        convertToCompact(iArr2, bArr3);
        byte[] BGFDecoder = BGFDecoder(computeSyndrome(bArr5, bArr2), iArr, iArr2);
        byte[] bArr7 = new byte[(this.R_BYTE * 2)];
        BIKEUtils.fromBitArrayToByteArray(bArr7, BGFDecoder, 0, this.r * 2);
        int i2 = this.R_BYTE;
        byte[] bArr8 = new byte[i2];
        byte[] bArr9 = new byte[i2];
        splitEBytes(bArr7, bArr8, bArr9);
        byte[] bArr10 = new byte[this.L_BYTE];
        functionL(bArr8, bArr9, bArr10);
        Bytes.xorTo(this.L_BYTE, bArr6, bArr10);
        byte[] functionH = functionH(bArr10);
        int i3 = this.R2_BYTE;
        if (Arrays.areEqual(bArr7, 0, i3, functionH, 0, i3)) {
            functionK(bArr10, bArr5, bArr6, bArr);
        } else {
            functionK(bArr4, bArr5, bArr6, bArr);
        }
    }

    public void encaps(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, SecureRandom secureRandom) {
        byte[] bArr5 = new byte[this.L_BYTE];
        secureRandom.nextBytes(bArr5);
        byte[] functionH = functionH(bArr5);
        int i = this.R_BYTE;
        byte[] bArr6 = new byte[i];
        byte[] bArr7 = new byte[i];
        splitEBytes(functionH, bArr6, bArr7);
        long[] create = this.bikeRing.create();
        long[] create2 = this.bikeRing.create();
        this.bikeRing.decodeBytes(bArr6, create);
        this.bikeRing.decodeBytes(bArr7, create2);
        long[] create3 = this.bikeRing.create();
        this.bikeRing.decodeBytes(bArr4, create3);
        this.bikeRing.multiply(create3, create2, create3);
        this.bikeRing.add(create3, create, create3);
        this.bikeRing.encodeBytes(create3, bArr);
        functionL(bArr6, bArr7, bArr2);
        Bytes.xorTo(this.L_BYTE, bArr5, bArr2);
        functionK(bArr5, bArr, bArr2, bArr3);
    }

    public void genKeyPair(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, SecureRandom secureRandom) {
        byte[] bArr5 = new byte[64];
        secureRandom.nextBytes(bArr5);
        SHAKEDigest sHAKEDigest = new SHAKEDigest(256);
        sHAKEDigest.update(bArr5, 0, this.L_BYTE);
        BIKEUtils.generateRandomByteArray(bArr, this.r, this.hw, sHAKEDigest);
        BIKEUtils.generateRandomByteArray(bArr2, this.r, this.hw, sHAKEDigest);
        long[] create = this.bikeRing.create();
        long[] create2 = this.bikeRing.create();
        this.bikeRing.decodeBytes(bArr, create);
        this.bikeRing.decodeBytes(bArr2, create2);
        long[] create3 = this.bikeRing.create();
        this.bikeRing.inv(create, create3);
        this.bikeRing.multiply(create3, create2, create3);
        this.bikeRing.encodeBytes(create3, bArr4);
        System.arraycopy(bArr5, this.L_BYTE, bArr3, 0, bArr3.length);
    }

    public int getSessionKeySize() {
        return this.L_BYTE;
    }
}
