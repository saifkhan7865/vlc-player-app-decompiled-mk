package org.bouncycastle.pqc.crypto.cmce;

import org.bouncycastle.math.raw.Interleave;

final class GF12 extends GF {
    GF12() {
    }

    private int gf_mul_ext_par(short s, short s2, short s3, short s4) {
        int i = (s2 & 1) * s;
        int i2 = (s4 & 1) * s3;
        for (int i3 = 1; i3 < 12; i3++) {
            int i4 = 1 << i3;
            i ^= (s2 & i4) * s;
            i2 ^= (i4 & s4) * s3;
        }
        return i ^ i2;
    }

    /* access modifiers changed from: protected */
    public short gf_frac(short s, short s2) {
        return gf_mul(gf_inv(s), s2);
    }

    /* access modifiers changed from: protected */
    public short gf_inv(short s) {
        short gf_mul = gf_mul(gf_sq(s), s);
        short gf_mul2 = gf_mul(gf_sq(gf_sq(gf_mul)), gf_mul);
        return gf_sq(gf_mul(gf_sq(gf_mul(gf_sq(gf_sq(gf_mul(gf_sq(gf_sq(gf_sq(gf_sq(gf_mul2)))), gf_mul2))), gf_mul)), s));
    }

    /* access modifiers changed from: protected */
    public short gf_mul(short s, short s2) {
        int i = (s2 & 1) * s;
        for (int i2 = 1; i2 < 12; i2++) {
            i ^= ((1 << i2) & s2) * s;
        }
        return gf_reduce(i);
    }

    /* access modifiers changed from: protected */
    public int gf_mul_ext(short s, short s2) {
        int i = (s2 & 1) * s;
        for (int i2 = 1; i2 < 12; i2++) {
            i ^= ((1 << i2) & s2) * s;
        }
        return i;
    }

    /* access modifiers changed from: protected */
    public void gf_mul_poly(int i, int[] iArr, short[] sArr, short[] sArr2, short[] sArr3, int[] iArr2) {
        int i2 = i;
        int[] iArr3 = iArr;
        iArr2[0] = gf_mul_ext(sArr2[0], sArr3[0]);
        for (int i3 = 1; i3 < i2; i3++) {
            int i4 = i3 + i3;
            iArr2[i4 - 1] = 0;
            short s = sArr2[i3];
            short s2 = sArr3[i3];
            for (int i5 = 0; i5 < i3; i5++) {
                int i6 = i3 + i5;
                iArr2[i6] = iArr2[i6] ^ gf_mul_ext_par(s, sArr3[i5], sArr2[i5], s2);
            }
            iArr2[i4] = gf_mul_ext(s, s2);
        }
        for (int i7 = (i2 - 1) * 2; i7 >= i2; i7--) {
            int i8 = iArr2[i7];
            for (int i9 = 0; i9 < iArr3.length - 1; i9++) {
                int i10 = (i7 - i2) + iArr3[i9];
                iArr2[i10] = iArr2[i10] ^ i8;
            }
            int i11 = i7 - i2;
            iArr2[i11] = (i8 << 1) ^ iArr2[i11];
        }
        for (int i12 = 0; i12 < i2; i12++) {
            sArr[i12] = gf_reduce(iArr2[i12]);
        }
    }

    /* access modifiers changed from: protected */
    public short gf_reduce(int i) {
        return (short) ((i >>> 21) ^ ((((i & 4095) ^ (i >>> 12)) ^ ((2093056 & i) >>> 9)) ^ ((14680064 & i) >>> 18)));
    }

    /* access modifiers changed from: protected */
    public short gf_sq(short s) {
        return gf_reduce(Interleave.expand16to32(s));
    }

    /* access modifiers changed from: protected */
    public int gf_sq_ext(short s) {
        return Interleave.expand16to32(s);
    }

    /* access modifiers changed from: protected */
    public void gf_sqr_poly(int i, int[] iArr, short[] sArr, short[] sArr2, int[] iArr2) {
        iArr2[0] = gf_sq_ext(sArr2[0]);
        for (int i2 = 1; i2 < i; i2++) {
            int i3 = i2 + i2;
            iArr2[i3 - 1] = 0;
            iArr2[i3] = gf_sq_ext(sArr2[i2]);
        }
        for (int i4 = (i - 1) * 2; i4 >= i; i4--) {
            int i5 = iArr2[i4];
            for (int i6 = 0; i6 < iArr.length - 1; i6++) {
                int i7 = (i4 - i) + iArr[i6];
                iArr2[i7] = iArr2[i7] ^ i5;
            }
            int i8 = i4 - i;
            iArr2[i8] = (i5 << 1) ^ iArr2[i8];
        }
        for (int i9 = 0; i9 < i; i9++) {
            sArr[i9] = gf_reduce(iArr2[i9]);
        }
    }
}
