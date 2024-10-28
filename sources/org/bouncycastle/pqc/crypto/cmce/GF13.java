package org.bouncycastle.pqc.crypto.cmce;

import org.bouncycastle.math.raw.Interleave;

final class GF13 extends GF {
    GF13() {
    }

    private int gf_mul_ext_par(short s, short s2, short s3, short s4) {
        int i = (s2 & 1) * s;
        int i2 = (s4 & 1) * s3;
        for (int i3 = 1; i3 < 13; i3++) {
            int i4 = 1 << i3;
            i ^= (s2 & i4) * s;
            i2 ^= (i4 & s4) * s3;
        }
        return i ^ i2;
    }

    private short gf_sq2(short s) {
        return gf_reduce(Interleave.expand16to32(gf_reduce(Interleave.expand16to32(s))));
    }

    private short gf_sq2mul(short s, short s2) {
        long j = (long) s;
        long j2 = (long) s2;
        long j3 = (j2 << 18) * (64 & j);
        long j4 = j ^ (j << 21);
        long j5 = ((j2 << 15) * (j4 & 8589934624L)) ^ (((((j3 ^ ((268435457 & j4) * j2)) ^ ((j2 << 3) * (536870914 & j4))) ^ ((j2 << 6) * (1073741828 & j4))) ^ ((j2 << 9) * (2147483656L & j4))) ^ ((j2 << 12) * (4294967312L & j4)));
        long j6 = 2305834213120671744L & j5;
        long j7 = j5 ^ ((j6 >>> 26) ^ (((j6 >>> 18) ^ (j6 >>> 20)) ^ (j6 >>> 24)));
        long j8 = 8796025913344L & j7;
        return gf_reduce(67108863 & ((int) (j7 ^ ((j8 >>> 26) ^ (((j8 >>> 18) ^ (j8 >>> 20)) ^ (j8 >>> 24))))));
    }

    private short gf_sqmul(short s, short s2) {
        long j = (long) s;
        long j2 = (long) s2;
        long j3 = (j2 << 6) * (64 & j);
        long j4 = j ^ (j << 7);
        long j5 = ((j2 << 5) * (j4 & 524320)) ^ (((((j3 ^ ((16385 & j4) * j2)) ^ ((j2 << 1) * (32770 & j4))) ^ ((j2 << 2) * (65540 & j4))) ^ ((j2 << 3) * (131080 & j4))) ^ ((j2 << 4) * (262160 & j4)));
        long j6 = 137371844608L & j5;
        return gf_reduce(67108863 & ((int) (j5 ^ ((j6 >>> 26) ^ (((j6 >>> 18) ^ (j6 >>> 20)) ^ (j6 >>> 24))))));
    }

    /* access modifiers changed from: protected */
    public short gf_frac(short s, short s2) {
        short gf_sqmul = gf_sqmul(s, s);
        short gf_sq2mul = gf_sq2mul(gf_sqmul, gf_sqmul);
        return gf_sqmul(gf_sq2mul(gf_sq2(gf_sq2mul(gf_sq2(gf_sq2mul), gf_sq2mul)), gf_sq2mul), s2);
    }

    /* access modifiers changed from: protected */
    public short gf_inv(short s) {
        return gf_frac(s, 1);
    }

    /* access modifiers changed from: protected */
    public short gf_mul(short s, short s2) {
        int i = (s2 & 1) * s;
        for (int i2 = 1; i2 < 13; i2++) {
            i ^= ((1 << i2) & s2) * s;
        }
        return gf_reduce(i);
    }

    /* access modifiers changed from: protected */
    public int gf_mul_ext(short s, short s2) {
        int i = (s2 & 1) * s;
        for (int i2 = 1; i2 < 13; i2++) {
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
            for (int i9 : iArr3) {
                int i10 = (i7 - i2) + i9;
                iArr2[i10] = iArr2[i10] ^ i8;
            }
        }
        for (int i11 = 0; i11 < i2; i11++) {
            sArr[i11] = gf_reduce(iArr2[i11]);
        }
    }

    /* access modifiers changed from: protected */
    public short gf_reduce(int i) {
        int i2 = i & 8191;
        int i3 = i >>> 13;
        int i4 = ((i3 << 4) ^ (i3 << 3)) ^ (i3 << 1);
        int i5 = i4 >>> 13;
        return (short) ((((i3 ^ i2) ^ i5) ^ (i4 & 8191)) ^ (((i5 << 4) ^ (i5 << 3)) ^ (i5 << 1)));
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
            for (int i6 : iArr) {
                int i7 = (i4 - i) + i6;
                iArr2[i7] = iArr2[i7] ^ i5;
            }
        }
        for (int i8 = 0; i8 < i; i8++) {
            sArr[i8] = gf_reduce(iArr2[i8]);
        }
    }
}
