package org.bouncycastle.pqc.crypto.cmce;

import androidx.core.view.InputDeviceCompat;
import java.lang.reflect.Array;

class BENES12 extends BENES {
    public BENES12(int i, int i2, int i3) {
        super(i, i2, i3);
    }

    private void apply_benes(byte[] bArr, byte[] bArr2, int i) {
        int i2;
        int i3;
        long[] jArr = new long[64];
        long[] jArr2 = new long[64];
        for (int i4 = 0; i4 < 64; i4++) {
            jArr[i4] = Utils.load8(bArr, i4 * 8);
        }
        int i5 = 256;
        if (i == 0) {
            i2 = (this.SYS_T * 2) + 40;
        } else {
            i2 = (this.SYS_T * 2) + 40 + (((this.GFBITS * 2) - 2) * 256);
            i5 = InputDeviceCompat.SOURCE_ANY;
        }
        transpose_64x64(jArr, jArr);
        int i6 = 0;
        while (true) {
            if (i6 > 5) {
                break;
            }
            for (int i7 = 0; i7 < 64; i7++) {
                jArr2[i7] = (long) Utils.load4(bArr2, (i7 * 4) + i2);
            }
            transpose_64x64(jArr2, jArr2);
            layerBenes(jArr, jArr2, i6);
            i2 += i5;
            i6++;
        }
        transpose_64x64(jArr, jArr);
        for (int i8 = 0; i8 <= 5; i8++) {
            for (int i9 = 0; i9 < 32; i9++) {
                jArr2[i9] = Utils.load8(bArr2, (i9 * 8) + i2);
            }
            layerBenes(jArr, jArr2, i8);
            i2 += i5;
        }
        for (int i10 = 4; i10 >= 0; i10--) {
            for (int i11 = 0; i11 < 32; i11++) {
                jArr2[i11] = Utils.load8(bArr2, (i11 * 8) + i2);
            }
            layerBenes(jArr, jArr2, i10);
            i2 += i5;
        }
        transpose_64x64(jArr, jArr);
        for (i3 = 5; i3 >= 0; i3--) {
            for (int i12 = 0; i12 < 64; i12++) {
                jArr2[i12] = (long) Utils.load4(bArr2, (i12 * 4) + i2);
            }
            transpose_64x64(jArr2, jArr2);
            layerBenes(jArr, jArr2, i3);
            i2 += i5;
        }
        transpose_64x64(jArr, jArr);
        for (int i13 = 0; i13 < 64; i13++) {
            Utils.store8(bArr, i13 * 8, jArr[i13]);
        }
    }

    static void layerBenes(long[] jArr, long[] jArr2, int i) {
        int i2 = 1 << i;
        int i3 = 0;
        for (int i4 = 0; i4 < 64; i4 += i2 * 2) {
            int i5 = i4;
            while (i5 < i4 + i2) {
                long j = jArr[i5];
                int i6 = i5 + i2;
                long j2 = (jArr[i6] ^ j) & jArr2[i3];
                jArr[i5] = j ^ j2;
                jArr[i6] = jArr[i6] ^ j2;
                i5++;
                i3++;
            }
        }
    }

    public void support_gen(short[] sArr, byte[] bArr) {
        int i = this.GFBITS;
        int[] iArr = new int[2];
        iArr[1] = (1 << this.GFBITS) / 8;
        iArr[0] = i;
        byte[][] bArr2 = (byte[][]) Array.newInstance(Byte.TYPE, iArr);
        for (int i2 = 0; i2 < this.GFBITS; i2++) {
            for (int i3 = 0; i3 < (1 << this.GFBITS) / 8; i3++) {
                bArr2[i2][i3] = 0;
            }
        }
        for (int i4 = 0; i4 < (1 << this.GFBITS); i4++) {
            short bitrev = Utils.bitrev((short) i4, this.GFBITS);
            for (int i5 = 0; i5 < this.GFBITS; i5++) {
                byte[] bArr3 = bArr2[i5];
                int i6 = i4 / 8;
                bArr3[i6] = (byte) (bArr3[i6] | (((bitrev >> i5) & 1) << (i4 % 8)));
            }
        }
        for (int i7 = 0; i7 < this.GFBITS; i7++) {
            apply_benes(bArr2[i7], bArr, 0);
        }
        for (int i8 = 0; i8 < this.SYS_N; i8++) {
            sArr[i8] = 0;
            for (int i9 = this.GFBITS - 1; i9 >= 0; i9--) {
                short s = (short) (sArr[i8] << 1);
                sArr[i8] = s;
                sArr[i8] = (short) (s | ((bArr2[i9][i8 / 8] >> (i8 % 8)) & 1));
            }
        }
    }
}
