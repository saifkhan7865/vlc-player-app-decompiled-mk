package org.bouncycastle.pqc.crypto.falcon;

class FalconFFT {
    FPREngine fpr = new FPREngine();

    FalconFFT() {
    }

    /* access modifiers changed from: package-private */
    public void FFT(FalconFPR[] falconFPRArr, int i, int i2) {
        int i3 = i2;
        int i4 = 1;
        int i5 = (1 << i3) >> 1;
        int i6 = 2;
        int i7 = i5;
        int i8 = 1;
        while (i8 < i3) {
            int i9 = i7 >> 1;
            int i10 = i6 >> 1;
            int i11 = 0;
            int i12 = 0;
            while (i11 < i10) {
                int i13 = (i6 + i11) << i4;
                FalconFPR falconFPR = this.fpr.fpr_gm_tab[i13];
                FalconFPR falconFPR2 = this.fpr.fpr_gm_tab[i13 + i4];
                int i14 = i12;
                for (int i15 = i12 + i9; i14 < i15; i15 = i15) {
                    int i16 = i + i14;
                    FalconFPR falconFPR3 = falconFPRArr[i16];
                    int i17 = i16 + i5;
                    FalconFPR falconFPR4 = falconFPRArr[i17];
                    int i18 = i16 + i9;
                    int i19 = i9;
                    int i20 = i18 + i5;
                    int i21 = i5;
                    ComplexNumberWrapper FPC_MUL = FPC_MUL(falconFPRArr[i18], falconFPRArr[i20], falconFPR, falconFPR2);
                    FalconFPR falconFPR5 = FPC_MUL.re;
                    FalconFPR falconFPR6 = FPC_MUL.im;
                    int i22 = i10;
                    ComplexNumberWrapper FPC_ADD = FPC_ADD(falconFPR3, falconFPR4, falconFPR5, falconFPR6);
                    falconFPRArr[i16] = FPC_ADD.re;
                    falconFPRArr[i17] = FPC_ADD.im;
                    ComplexNumberWrapper FPC_SUB = FPC_SUB(falconFPR3, falconFPR4, falconFPR5, falconFPR6);
                    falconFPRArr[i18] = FPC_SUB.re;
                    falconFPRArr[i20] = FPC_SUB.im;
                    i14++;
                    int i23 = i2;
                    i9 = i19;
                    i5 = i21;
                    i10 = i22;
                }
                int i24 = i5;
                int i25 = i9;
                int i26 = i10;
                i11++;
                i12 += i7;
                int i27 = i2;
                i4 = 1;
            }
            int i28 = i5;
            i8++;
            i6 <<= 1;
            i3 = i2;
            i7 = i9;
            i4 = 1;
        }
    }

    /* access modifiers changed from: package-private */
    public ComplexNumberWrapper FPC_ADD(FalconFPR falconFPR, FalconFPR falconFPR2, FalconFPR falconFPR3, FalconFPR falconFPR4) {
        return new ComplexNumberWrapper(this.fpr.fpr_add(falconFPR, falconFPR3), this.fpr.fpr_add(falconFPR2, falconFPR4));
    }

    /* access modifiers changed from: package-private */
    public ComplexNumberWrapper FPC_DIV(FalconFPR falconFPR, FalconFPR falconFPR2, FalconFPR falconFPR3, FalconFPR falconFPR4) {
        FPREngine fPREngine = this.fpr;
        FalconFPR fpr_inv = this.fpr.fpr_inv(fPREngine.fpr_add(fPREngine.fpr_sqr(falconFPR3), this.fpr.fpr_sqr(falconFPR4)));
        FalconFPR fpr_mul = this.fpr.fpr_mul(falconFPR3, fpr_inv);
        FPREngine fPREngine2 = this.fpr;
        FalconFPR fpr_mul2 = fPREngine2.fpr_mul(fPREngine2.fpr_neg(falconFPR4), fpr_inv);
        FPREngine fPREngine3 = this.fpr;
        FalconFPR fpr_sub = fPREngine3.fpr_sub(fPREngine3.fpr_mul(falconFPR, fpr_mul), this.fpr.fpr_mul(falconFPR2, fpr_mul2));
        FPREngine fPREngine4 = this.fpr;
        return new ComplexNumberWrapper(fpr_sub, fPREngine4.fpr_add(fPREngine4.fpr_mul(falconFPR, fpr_mul2), this.fpr.fpr_mul(falconFPR2, fpr_mul)));
    }

    /* access modifiers changed from: package-private */
    public ComplexNumberWrapper FPC_INV(FalconFPR falconFPR, FalconFPR falconFPR2) {
        FPREngine fPREngine = this.fpr;
        FalconFPR fpr_inv = this.fpr.fpr_inv(fPREngine.fpr_add(fPREngine.fpr_sqr(falconFPR), this.fpr.fpr_sqr(falconFPR2)));
        FalconFPR fpr_mul = this.fpr.fpr_mul(falconFPR, fpr_inv);
        FPREngine fPREngine2 = this.fpr;
        return new ComplexNumberWrapper(fpr_mul, fPREngine2.fpr_mul(fPREngine2.fpr_neg(falconFPR2), fpr_inv));
    }

    /* access modifiers changed from: package-private */
    public ComplexNumberWrapper FPC_MUL(FalconFPR falconFPR, FalconFPR falconFPR2, FalconFPR falconFPR3, FalconFPR falconFPR4) {
        FPREngine fPREngine = this.fpr;
        FalconFPR fpr_sub = fPREngine.fpr_sub(fPREngine.fpr_mul(falconFPR, falconFPR3), this.fpr.fpr_mul(falconFPR2, falconFPR4));
        FPREngine fPREngine2 = this.fpr;
        return new ComplexNumberWrapper(fpr_sub, fPREngine2.fpr_add(fPREngine2.fpr_mul(falconFPR, falconFPR4), this.fpr.fpr_mul(falconFPR2, falconFPR3)));
    }

    /* access modifiers changed from: package-private */
    public ComplexNumberWrapper FPC_SQR(FalconFPR falconFPR, FalconFPR falconFPR2) {
        FPREngine fPREngine = this.fpr;
        FalconFPR fpr_sub = fPREngine.fpr_sub(fPREngine.fpr_sqr(falconFPR), this.fpr.fpr_sqr(falconFPR2));
        FPREngine fPREngine2 = this.fpr;
        return new ComplexNumberWrapper(fpr_sub, fPREngine2.fpr_double(fPREngine2.fpr_mul(falconFPR, falconFPR2)));
    }

    /* access modifiers changed from: package-private */
    public ComplexNumberWrapper FPC_SUB(FalconFPR falconFPR, FalconFPR falconFPR2, FalconFPR falconFPR3, FalconFPR falconFPR4) {
        return new ComplexNumberWrapper(this.fpr.fpr_sub(falconFPR, falconFPR3), this.fpr.fpr_sub(falconFPR2, falconFPR4));
    }

    /* access modifiers changed from: package-private */
    public void iFFT(FalconFPR[] falconFPRArr, int i, int i2) {
        int i3;
        int i4 = 1;
        int i5 = 1 << i2;
        int i6 = i5 >> 1;
        int i7 = i2;
        int i8 = i5;
        int i9 = 1;
        while (true) {
            i3 = 0;
            if (i7 <= i4) {
                break;
            }
            i8 >>= i4;
            int i10 = i9 << 1;
            int i11 = 0;
            while (i3 < i6) {
                int i12 = (i8 + i11) << i4;
                FalconFPR falconFPR = this.fpr.fpr_gm_tab[i12];
                FPREngine fPREngine = this.fpr;
                FalconFPR fpr_neg = fPREngine.fpr_neg(fPREngine.fpr_gm_tab[i12 + i4]);
                int i13 = i3;
                for (int i14 = i3 + i9; i13 < i14; i14 = i14) {
                    int i15 = i + i13;
                    FalconFPR falconFPR2 = falconFPRArr[i15];
                    int i16 = i15 + i6;
                    FalconFPR falconFPR3 = falconFPRArr[i16];
                    int i17 = i15 + i9;
                    int i18 = i8;
                    FalconFPR falconFPR4 = falconFPRArr[i17];
                    int i19 = i17 + i6;
                    int i20 = i6;
                    FalconFPR falconFPR5 = falconFPRArr[i19];
                    int i21 = i9;
                    ComplexNumberWrapper FPC_ADD = FPC_ADD(falconFPR2, falconFPR3, falconFPR4, falconFPR5);
                    falconFPRArr[i15] = FPC_ADD.re;
                    falconFPRArr[i16] = FPC_ADD.im;
                    ComplexNumberWrapper FPC_SUB = FPC_SUB(falconFPR2, falconFPR3, falconFPR4, falconFPR5);
                    ComplexNumberWrapper FPC_MUL = FPC_MUL(FPC_SUB.re, FPC_SUB.im, falconFPR, fpr_neg);
                    falconFPRArr[i17] = FPC_MUL.re;
                    falconFPRArr[i19] = FPC_MUL.im;
                    i13++;
                    i8 = i18;
                    i6 = i20;
                    i9 = i21;
                }
                int i22 = i6;
                int i23 = i8;
                int i24 = i9;
                i11++;
                i3 += i10;
                i4 = 1;
            }
            int i25 = i6;
            int i26 = i8;
            i7--;
            i9 = i10;
            i4 = 1;
        }
        if (i2 > 0) {
            FalconFPR falconFPR6 = this.fpr.fpr_p2_tab[i2];
            while (i3 < i5) {
                int i27 = i + i3;
                falconFPRArr[i27] = this.fpr.fpr_mul(falconFPRArr[i27], falconFPR6);
                i3++;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void poly_LDL_fft(FalconFPR[] falconFPRArr, int i, FalconFPR[] falconFPRArr2, int i2, FalconFPR[] falconFPRArr3, int i3, int i4) {
        int i5 = (1 << i4) >> 1;
        for (int i6 = 0; i6 < i5; i6++) {
            int i7 = i + i6;
            FalconFPR falconFPR = falconFPRArr[i7];
            FalconFPR falconFPR2 = falconFPRArr[i7 + i5];
            int i8 = i2 + i6;
            FalconFPR falconFPR3 = falconFPRArr2[i8];
            int i9 = i8 + i5;
            FalconFPR falconFPR4 = falconFPRArr2[i9];
            int i10 = i3 + i6;
            FalconFPR falconFPR5 = falconFPRArr3[i10];
            int i11 = i10 + i5;
            FalconFPR falconFPR6 = falconFPRArr3[i11];
            ComplexNumberWrapper FPC_DIV = FPC_DIV(falconFPR3, falconFPR4, falconFPR, falconFPR2);
            FalconFPR falconFPR7 = FPC_DIV.re;
            FalconFPR falconFPR8 = FPC_DIV.im;
            ComplexNumberWrapper FPC_MUL = FPC_MUL(falconFPR7, falconFPR8, falconFPR3, this.fpr.fpr_neg(falconFPR4));
            ComplexNumberWrapper FPC_SUB = FPC_SUB(falconFPR5, falconFPR6, FPC_MUL.re, FPC_MUL.im);
            falconFPRArr3[i10] = FPC_SUB.re;
            falconFPRArr3[i11] = FPC_SUB.im;
            falconFPRArr2[i8] = falconFPR7;
            falconFPRArr2[i9] = this.fpr.fpr_neg(falconFPR8);
        }
    }

    /* access modifiers changed from: package-private */
    public void poly_LDLmv_fft(FalconFPR[] falconFPRArr, int i, FalconFPR[] falconFPRArr2, int i2, FalconFPR[] falconFPRArr3, int i3, FalconFPR[] falconFPRArr4, int i4, FalconFPR[] falconFPRArr5, int i5, int i6) {
        int i7 = (1 << i6) >> 1;
        for (int i8 = 0; i8 < i7; i8++) {
            int i9 = i3 + i8;
            FalconFPR falconFPR = falconFPRArr3[i9];
            FalconFPR falconFPR2 = falconFPRArr3[i9 + i7];
            int i10 = i4 + i8;
            FalconFPR falconFPR3 = falconFPRArr4[i10];
            FalconFPR falconFPR4 = falconFPRArr4[i10 + i7];
            int i11 = i5 + i8;
            FalconFPR falconFPR5 = falconFPRArr5[i11];
            FalconFPR falconFPR6 = falconFPRArr5[i11 + i7];
            ComplexNumberWrapper FPC_DIV = FPC_DIV(falconFPR3, falconFPR4, falconFPR, falconFPR2);
            FalconFPR falconFPR7 = FPC_DIV.re;
            FalconFPR falconFPR8 = FPC_DIV.im;
            ComplexNumberWrapper FPC_MUL = FPC_MUL(falconFPR7, falconFPR8, falconFPR3, this.fpr.fpr_neg(falconFPR4));
            ComplexNumberWrapper FPC_SUB = FPC_SUB(falconFPR5, falconFPR6, FPC_MUL.re, FPC_MUL.im);
            int i12 = i + i8;
            falconFPRArr[i12] = FPC_SUB.re;
            falconFPRArr[i12 + i7] = FPC_SUB.im;
            int i13 = i2 + i8;
            falconFPRArr2[i13] = falconFPR7;
            falconFPRArr2[i13 + i7] = this.fpr.fpr_neg(falconFPR8);
        }
    }

    /* access modifiers changed from: package-private */
    public void poly_add(FalconFPR[] falconFPRArr, int i, FalconFPR[] falconFPRArr2, int i2, int i3) {
        int i4 = 1 << i3;
        for (int i5 = 0; i5 < i4; i5++) {
            int i6 = i + i5;
            falconFPRArr[i6] = this.fpr.fpr_add(falconFPRArr[i6], falconFPRArr2[i2 + i5]);
        }
    }

    /* access modifiers changed from: package-private */
    public void poly_add_muladj_fft(FalconFPR[] falconFPRArr, int i, FalconFPR[] falconFPRArr2, int i2, FalconFPR[] falconFPRArr3, int i3, FalconFPR[] falconFPRArr4, int i4, FalconFPR[] falconFPRArr5, int i5, int i6) {
        int i7 = (1 << i6) >> 1;
        for (int i8 = 0; i8 < i7; i8++) {
            int i9 = i2 + i8;
            FalconFPR falconFPR = falconFPRArr2[i9];
            FalconFPR falconFPR2 = falconFPRArr2[i9 + i7];
            int i10 = i3 + i8;
            FalconFPR falconFPR3 = falconFPRArr3[i10];
            FalconFPR falconFPR4 = falconFPRArr3[i10 + i7];
            int i11 = i4 + i8;
            FalconFPR falconFPR5 = falconFPRArr4[i11];
            FalconFPR falconFPR6 = falconFPRArr4[i11 + i7];
            int i12 = i5 + i8;
            FalconFPR falconFPR7 = falconFPRArr5[i12];
            FalconFPR falconFPR8 = falconFPRArr5[i12 + i7];
            ComplexNumberWrapper FPC_MUL = FPC_MUL(falconFPR, falconFPR2, falconFPR5, this.fpr.fpr_neg(falconFPR6));
            FalconFPR falconFPR9 = FPC_MUL.re;
            FalconFPR falconFPR10 = FPC_MUL.im;
            ComplexNumberWrapper FPC_MUL2 = FPC_MUL(falconFPR3, falconFPR4, falconFPR7, this.fpr.fpr_neg(falconFPR8));
            FalconFPR falconFPR11 = FPC_MUL2.re;
            FalconFPR falconFPR12 = FPC_MUL2.im;
            int i13 = i + i8;
            falconFPRArr[i13] = this.fpr.fpr_add(falconFPR9, falconFPR11);
            falconFPRArr[i13 + i7] = this.fpr.fpr_add(falconFPR10, falconFPR12);
        }
    }

    /* access modifiers changed from: package-private */
    public void poly_adj_fft(FalconFPR[] falconFPRArr, int i, int i2) {
        int i3 = 1 << i2;
        for (int i4 = i3 >> 1; i4 < i3; i4++) {
            int i5 = i + i4;
            falconFPRArr[i5] = this.fpr.fpr_neg(falconFPRArr[i5]);
        }
    }

    /* access modifiers changed from: package-private */
    public void poly_div_autoadj_fft(FalconFPR[] falconFPRArr, int i, FalconFPR[] falconFPRArr2, int i2, int i3) {
        int i4 = (1 << i3) >> 1;
        for (int i5 = 0; i5 < i4; i5++) {
            FalconFPR fpr_inv = this.fpr.fpr_inv(falconFPRArr2[i2 + i5]);
            int i6 = i + i5;
            falconFPRArr[i6] = this.fpr.fpr_mul(falconFPRArr[i6], fpr_inv);
            int i7 = i6 + i4;
            falconFPRArr[i7] = this.fpr.fpr_mul(falconFPRArr[i7], fpr_inv);
        }
    }

    /* access modifiers changed from: package-private */
    public void poly_div_fft(FalconFPR[] falconFPRArr, int i, FalconFPR[] falconFPRArr2, int i2, int i3) {
        int i4 = (1 << i3) >> 1;
        for (int i5 = 0; i5 < i4; i5++) {
            int i6 = i + i5;
            int i7 = i6 + i4;
            int i8 = i2 + i5;
            ComplexNumberWrapper FPC_DIV = FPC_DIV(falconFPRArr[i6], falconFPRArr[i7], falconFPRArr2[i8], falconFPRArr2[i8 + i4]);
            falconFPRArr[i6] = FPC_DIV.re;
            falconFPRArr[i7] = FPC_DIV.im;
        }
    }

    /* access modifiers changed from: package-private */
    public void poly_invnorm2_fft(FalconFPR[] falconFPRArr, int i, FalconFPR[] falconFPRArr2, int i2, FalconFPR[] falconFPRArr3, int i3, int i4) {
        int i5 = (1 << i4) >> 1;
        for (int i6 = 0; i6 < i5; i6++) {
            int i7 = i2 + i6;
            FalconFPR falconFPR = falconFPRArr2[i7];
            FalconFPR falconFPR2 = falconFPRArr2[i7 + i5];
            int i8 = i3 + i6;
            FalconFPR falconFPR3 = falconFPRArr3[i8];
            FalconFPR falconFPR4 = falconFPRArr3[i8 + i5];
            FPREngine fPREngine = this.fpr;
            FalconFPR fpr_add = fPREngine.fpr_add(fPREngine.fpr_sqr(falconFPR), this.fpr.fpr_sqr(falconFPR2));
            FPREngine fPREngine2 = this.fpr;
            falconFPRArr[i + i6] = fPREngine.fpr_inv(fPREngine.fpr_add(fpr_add, fPREngine2.fpr_add(fPREngine2.fpr_sqr(falconFPR3), this.fpr.fpr_sqr(falconFPR4))));
        }
    }

    /* access modifiers changed from: package-private */
    public void poly_merge_fft(FalconFPR[] falconFPRArr, int i, FalconFPR[] falconFPRArr2, int i2, FalconFPR[] falconFPRArr3, int i3, int i4) {
        int i5 = 1 << i4;
        int i6 = i5 >> 1;
        int i7 = i5 >> 2;
        falconFPRArr[i] = falconFPRArr2[i2];
        falconFPRArr[i + i6] = falconFPRArr3[i3];
        for (int i8 = 0; i8 < i7; i8++) {
            int i9 = i2 + i8;
            FalconFPR falconFPR = falconFPRArr2[i9];
            FalconFPR falconFPR2 = falconFPRArr2[i9 + i7];
            int i10 = i3 + i8;
            int i11 = (i8 + i6) << 1;
            ComplexNumberWrapper FPC_MUL = FPC_MUL(falconFPRArr3[i10], falconFPRArr3[i10 + i7], this.fpr.fpr_gm_tab[i11], this.fpr.fpr_gm_tab[i11 + 1]);
            FalconFPR falconFPR3 = FPC_MUL.re;
            FalconFPR falconFPR4 = FPC_MUL.im;
            ComplexNumberWrapper FPC_ADD = FPC_ADD(falconFPR, falconFPR2, falconFPR3, falconFPR4);
            FalconFPR falconFPR5 = FPC_ADD.re;
            FalconFPR falconFPR6 = FPC_ADD.im;
            int i12 = (i8 << 1) + i;
            falconFPRArr[i12] = falconFPR5;
            falconFPRArr[i12 + i6] = falconFPR6;
            ComplexNumberWrapper FPC_SUB = FPC_SUB(falconFPR, falconFPR2, falconFPR3, falconFPR4);
            FalconFPR falconFPR7 = FPC_SUB.re;
            FalconFPR falconFPR8 = FPC_SUB.im;
            int i13 = i12 + 1;
            falconFPRArr[i13] = falconFPR7;
            falconFPRArr[i13 + i6] = falconFPR8;
        }
    }

    /* access modifiers changed from: package-private */
    public void poly_mul_autoadj_fft(FalconFPR[] falconFPRArr, int i, FalconFPR[] falconFPRArr2, int i2, int i3) {
        int i4 = (1 << i3) >> 1;
        for (int i5 = 0; i5 < i4; i5++) {
            int i6 = i + i5;
            int i7 = i2 + i5;
            falconFPRArr[i6] = this.fpr.fpr_mul(falconFPRArr[i6], falconFPRArr2[i7]);
            int i8 = i6 + i4;
            falconFPRArr[i8] = this.fpr.fpr_mul(falconFPRArr[i8], falconFPRArr2[i7]);
        }
    }

    /* access modifiers changed from: package-private */
    public void poly_mul_fft(FalconFPR[] falconFPRArr, int i, FalconFPR[] falconFPRArr2, int i2, int i3) {
        int i4 = (1 << i3) >> 1;
        for (int i5 = 0; i5 < i4; i5++) {
            int i6 = i + i5;
            int i7 = i6 + i4;
            int i8 = i2 + i5;
            ComplexNumberWrapper FPC_MUL = FPC_MUL(falconFPRArr[i6], falconFPRArr[i7], falconFPRArr2[i8], falconFPRArr2[i8 + i4]);
            falconFPRArr[i6] = FPC_MUL.re;
            falconFPRArr[i7] = FPC_MUL.im;
        }
    }

    /* access modifiers changed from: package-private */
    public void poly_muladj_fft(FalconFPR[] falconFPRArr, int i, FalconFPR[] falconFPRArr2, int i2, int i3) {
        int i4 = (1 << i3) >> 1;
        for (int i5 = 0; i5 < i4; i5++) {
            int i6 = i + i5;
            int i7 = i6 + i4;
            int i8 = i2 + i5;
            ComplexNumberWrapper FPC_MUL = FPC_MUL(falconFPRArr[i6], falconFPRArr[i7], falconFPRArr2[i8], this.fpr.fpr_neg(falconFPRArr2[i8 + i4]));
            falconFPRArr[i6] = FPC_MUL.re;
            falconFPRArr[i7] = FPC_MUL.im;
        }
    }

    /* access modifiers changed from: package-private */
    public void poly_mulconst(FalconFPR[] falconFPRArr, int i, FalconFPR falconFPR, int i2) {
        int i3 = 1 << i2;
        for (int i4 = 0; i4 < i3; i4++) {
            int i5 = i + i4;
            falconFPRArr[i5] = this.fpr.fpr_mul(falconFPRArr[i5], falconFPR);
        }
    }

    /* access modifiers changed from: package-private */
    public void poly_mulselfadj_fft(FalconFPR[] falconFPRArr, int i, int i2) {
        int i3 = (1 << i2) >> 1;
        for (int i4 = 0; i4 < i3; i4++) {
            int i5 = i + i4;
            FalconFPR falconFPR = falconFPRArr[i5];
            int i6 = i5 + i3;
            FalconFPR falconFPR2 = falconFPRArr[i6];
            FPREngine fPREngine = this.fpr;
            falconFPRArr[i5] = fPREngine.fpr_add(fPREngine.fpr_sqr(falconFPR), this.fpr.fpr_sqr(falconFPR2));
            falconFPRArr[i6] = this.fpr.fpr_zero;
        }
    }

    /* access modifiers changed from: package-private */
    public void poly_neg(FalconFPR[] falconFPRArr, int i, int i2) {
        int i3 = 1 << i2;
        for (int i4 = 0; i4 < i3; i4++) {
            int i5 = i + i4;
            falconFPRArr[i5] = this.fpr.fpr_neg(falconFPRArr[i5]);
        }
    }

    /* access modifiers changed from: package-private */
    public void poly_split_fft(FalconFPR[] falconFPRArr, int i, FalconFPR[] falconFPRArr2, int i2, FalconFPR[] falconFPRArr3, int i3, int i4) {
        int i5 = 1 << i4;
        int i6 = i5 >> 1;
        int i7 = i5 >> 2;
        falconFPRArr[i] = falconFPRArr3[i3];
        falconFPRArr2[i2] = falconFPRArr3[i3 + i6];
        for (int i8 = 0; i8 < i7; i8++) {
            int i9 = i3 + (i8 << 1);
            FalconFPR falconFPR = falconFPRArr3[i9];
            FalconFPR falconFPR2 = falconFPRArr3[i9 + i6];
            int i10 = i9 + 1;
            FalconFPR falconFPR3 = falconFPRArr3[i10];
            FalconFPR falconFPR4 = falconFPRArr3[i10 + i6];
            ComplexNumberWrapper FPC_ADD = FPC_ADD(falconFPR, falconFPR2, falconFPR3, falconFPR4);
            FalconFPR falconFPR5 = FPC_ADD.re;
            FalconFPR falconFPR6 = FPC_ADD.im;
            int i11 = i + i8;
            falconFPRArr[i11] = this.fpr.fpr_half(falconFPR5);
            falconFPRArr[i11 + i7] = this.fpr.fpr_half(falconFPR6);
            ComplexNumberWrapper FPC_SUB = FPC_SUB(falconFPR, falconFPR2, falconFPR3, falconFPR4);
            FalconFPR falconFPR7 = FPC_SUB.re;
            FalconFPR falconFPR8 = FPC_SUB.im;
            int i12 = (i8 + i6) << 1;
            FalconFPR falconFPR9 = this.fpr.fpr_gm_tab[i12];
            FPREngine fPREngine = this.fpr;
            ComplexNumberWrapper FPC_MUL = FPC_MUL(falconFPR7, falconFPR8, falconFPR9, fPREngine.fpr_neg(fPREngine.fpr_gm_tab[i12 + 1]));
            FalconFPR falconFPR10 = FPC_MUL.re;
            FalconFPR falconFPR11 = FPC_MUL.im;
            int i13 = i2 + i8;
            falconFPRArr2[i13] = this.fpr.fpr_half(falconFPR10);
            falconFPRArr2[i13 + i7] = this.fpr.fpr_half(falconFPR11);
        }
    }

    /* access modifiers changed from: package-private */
    public void poly_sub(FalconFPR[] falconFPRArr, int i, FalconFPR[] falconFPRArr2, int i2, int i3) {
        int i4 = 1 << i3;
        for (int i5 = 0; i5 < i4; i5++) {
            int i6 = i + i5;
            falconFPRArr[i6] = this.fpr.fpr_sub(falconFPRArr[i6], falconFPRArr2[i2 + i5]);
        }
    }
}
