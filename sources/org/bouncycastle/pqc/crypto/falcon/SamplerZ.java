package org.bouncycastle.pqc.crypto.falcon;

import com.google.common.base.Ascii;

class SamplerZ {
    FPREngine fpr = new FPREngine();

    SamplerZ() {
    }

    /* access modifiers changed from: package-private */
    public int BerExp(FalconRNG falconRNG, FalconFPR falconFPR, FalconFPR falconFPR2) {
        int prng_get_u8;
        FPREngine fPREngine = this.fpr;
        int fpr_trunc = (int) fPREngine.fpr_trunc(fPREngine.fpr_mul(falconFPR, fPREngine.fpr_inv_log2));
        FPREngine fPREngine2 = this.fpr;
        long fpr_expm_p63 = ((this.fpr.fpr_expm_p63(fPREngine2.fpr_sub(falconFPR, fPREngine2.fpr_mul(fPREngine2.fpr_of((long) fpr_trunc), this.fpr.fpr_log2)), falconFPR2) << 1) - 1) >>> (((fpr_trunc ^ 63) & (-((63 - fpr_trunc) >>> 31))) ^ fpr_trunc);
        int i = 64;
        do {
            i -= 8;
            prng_get_u8 = (falconRNG.prng_get_u8() & 255) - (((int) (fpr_expm_p63 >>> i)) & 255);
            if (prng_get_u8 != 0) {
                break;
            }
        } while (i > 0);
        return prng_get_u8 >>> 31;
    }

    /* access modifiers changed from: package-private */
    public int gaussian0_sampler(FalconRNG falconRNG) {
        int[] iArr = {10745844, 3068844, 3741698, 5559083, 1580863, 8248194, 2260429, 13669192, 2736639, 708981, 4421575, 10046180, 169348, 7122675, 4136815, 30538, 13063405, 7650655, 4132, 14505003, 7826148, 417, 16768101, 11363290, 31, 8444042, 8086568, 1, 12844466, 265321, 0, 1232676, 13644283, 0, 38047, 9111839, 0, 870, 6138264, 0, 14, 12545723, 0, 0, 3104126, 0, 0, 28824, 0, 0, 198, 0, 0, 1};
        long prng_get_u64 = falconRNG.prng_get_u64();
        int i = ((int) prng_get_u64) & 16777215;
        int i2 = 16777215 & ((int) (prng_get_u64 >>> 24));
        int prng_get_u8 = ((falconRNG.prng_get_u8() & 255) << Ascii.DLE) | ((int) (prng_get_u64 >>> 48));
        int i3 = 0;
        for (int i4 = 0; i4 < 54; i4 += 3) {
            int i5 = iArr[i4 + 2];
            i3 += ((prng_get_u8 - iArr[i4]) - (((i2 - iArr[i4 + 1]) - ((i - i5) >>> 31)) >>> 31)) >>> 31;
        }
        return i3;
    }

    /* access modifiers changed from: package-private */
    public int sample(SamplerCtx samplerCtx, FalconFPR falconFPR, FalconFPR falconFPR2) {
        return sampler(samplerCtx, falconFPR, falconFPR2);
    }

    /* access modifiers changed from: package-private */
    public int sampler(SamplerCtx samplerCtx, FalconFPR falconFPR, FalconFPR falconFPR2) {
        int gaussian0_sampler;
        int i;
        FalconFPR fpr_mul;
        FPREngine fPREngine;
        int fpr_floor = (int) this.fpr.fpr_floor(falconFPR);
        FPREngine fPREngine2 = this.fpr;
        FalconFPR fpr_sub = fPREngine2.fpr_sub(falconFPR, fPREngine2.fpr_of((long) fpr_floor));
        FPREngine fPREngine3 = this.fpr;
        FalconFPR fpr_half = fPREngine3.fpr_half(fPREngine3.fpr_sqr(falconFPR2));
        FalconFPR fpr_mul2 = this.fpr.fpr_mul(falconFPR2, samplerCtx.sigma_min);
        do {
            gaussian0_sampler = gaussian0_sampler(samplerCtx.p);
            byte prng_get_u8 = samplerCtx.p.prng_get_u8() & 1;
            i = prng_get_u8 + (((prng_get_u8 << 1) - 1) * gaussian0_sampler);
            FPREngine fPREngine4 = this.fpr;
            fpr_mul = fPREngine4.fpr_mul(fPREngine4.fpr_sqr(fPREngine4.fpr_sub(fPREngine4.fpr_of((long) i), fpr_sub)), fpr_half);
            fPREngine = this.fpr;
        } while (BerExp(samplerCtx.p, fPREngine.fpr_sub(fpr_mul, fPREngine.fpr_mul(fPREngine.fpr_of((long) (gaussian0_sampler * gaussian0_sampler)), this.fpr.fpr_inv_2sqrsigma0)), fpr_mul2) == 0);
        return fpr_floor + i;
    }
}
