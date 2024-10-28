package org.bouncycastle.pqc.crypto.falcon;

class FalconSign {
    FalconCommon common = new FalconCommon();
    FalconFFT fft = new FalconFFT();
    FPREngine fpr = new FPREngine();

    FalconSign() {
    }

    private static int MKN(int i) {
        return 1 << i;
    }

    /* access modifiers changed from: package-private */
    public int do_sign_dyn(SamplerZ samplerZ, SamplerCtx samplerCtx, short[] sArr, int i, byte[] bArr, int i2, byte[] bArr2, int i3, byte[] bArr3, int i4, byte[] bArr4, int i5, short[] sArr2, int i6, int i7, FalconFPR[] falconFPRArr, int i8) {
        int i9 = i7;
        FalconFPR[] falconFPRArr2 = falconFPRArr;
        int i10 = i8;
        int MKN = MKN(i7);
        int i11 = i10 + MKN;
        int i12 = i11 + MKN;
        int i13 = i12 + MKN;
        FalconFPR[] falconFPRArr3 = falconFPRArr;
        int i14 = i7;
        smallints_to_fpr(falconFPRArr3, i11, bArr, i2, i14);
        smallints_to_fpr(falconFPRArr3, i8, bArr2, i3, i14);
        smallints_to_fpr(falconFPRArr3, i13, bArr3, i4, i14);
        smallints_to_fpr(falconFPRArr3, i12, bArr4, i5, i14);
        this.fft.FFT(falconFPRArr2, i11, i9);
        this.fft.FFT(falconFPRArr2, i10, i9);
        this.fft.FFT(falconFPRArr2, i13, i9);
        this.fft.FFT(falconFPRArr2, i12, i9);
        this.fft.poly_neg(falconFPRArr2, i11, i9);
        this.fft.poly_neg(falconFPRArr2, i13, i9);
        int i15 = i13 + MKN;
        int i16 = i15 + MKN;
        System.arraycopy(falconFPRArr2, i11, falconFPRArr2, i15, MKN);
        this.fft.poly_mulselfadj_fft(falconFPRArr2, i15, i9);
        System.arraycopy(falconFPRArr2, i10, falconFPRArr2, i16, MKN);
        FalconFPR[] falconFPRArr4 = falconFPRArr;
        this.fft.poly_muladj_fft(falconFPRArr3, i16, falconFPRArr4, i12, i14);
        this.fft.poly_mulselfadj_fft(falconFPRArr2, i10, i9);
        this.fft.poly_add(falconFPRArr3, i8, falconFPRArr4, i15, i14);
        System.arraycopy(falconFPRArr2, i11, falconFPRArr2, i15, MKN);
        int i17 = i11;
        this.fft.poly_muladj_fft(falconFPRArr3, i17, falconFPRArr4, i13, i14);
        int i18 = i16;
        this.fft.poly_add(falconFPRArr3, i17, falconFPRArr4, i18, i14);
        this.fft.poly_mulselfadj_fft(falconFPRArr2, i12, i9);
        System.arraycopy(falconFPRArr2, i13, falconFPRArr2, i16, MKN);
        this.fft.poly_mulselfadj_fft(falconFPRArr2, i16, i9);
        this.fft.poly_add(falconFPRArr3, i12, falconFPRArr4, i18, i14);
        int i19 = i16 + MKN;
        int i20 = 0;
        while (i20 < MKN) {
            falconFPRArr2[i16 + i20] = this.fpr.fpr_of((long) sArr2[i6 + i20]);
            i20++;
            i19 = i19;
        }
        int i21 = i19;
        this.fft.FFT(falconFPRArr2, i16, i9);
        FalconFPR falconFPR = this.fpr.fpr_inverse_of_q;
        int i22 = i21;
        System.arraycopy(falconFPRArr2, i16, falconFPRArr2, i22, MKN);
        FalconFPR[] falconFPRArr5 = falconFPRArr;
        int i23 = i21;
        int i24 = i12;
        FalconFPR falconFPR2 = falconFPR;
        int i25 = i7;
        this.fft.poly_mul_fft(falconFPRArr, i22, falconFPRArr5, i15, i25);
        this.fft.poly_mulconst(falconFPRArr2, i23, this.fpr.fpr_neg(falconFPR2), i9);
        int i26 = i13;
        this.fft.poly_mul_fft(falconFPRArr, i16, falconFPRArr5, i26, i25);
        this.fft.poly_mulconst(falconFPRArr2, i16, falconFPR2, i9);
        int i27 = MKN * 2;
        System.arraycopy(falconFPRArr2, i16, falconFPRArr2, i13, i27);
        int i28 = i16;
        int i29 = i13;
        int i30 = i27;
        int i31 = i24;
        int i32 = i11;
        ffSampling_fft_dyntree(samplerZ, samplerCtx, falconFPRArr5, i26, falconFPRArr, i15, falconFPRArr, i8, falconFPRArr, i11, falconFPRArr, i31, i7, i7, falconFPRArr, i28);
        FalconFPR[] falconFPRArr6 = falconFPRArr;
        int i33 = i15;
        int i34 = i29;
        System.arraycopy(falconFPRArr6, i34, falconFPRArr6, i33, i30);
        FalconFPR[] falconFPRArr7 = falconFPRArr;
        int i35 = i7;
        smallints_to_fpr(falconFPRArr7, i32, bArr, i2, i35);
        smallints_to_fpr(falconFPRArr7, i8, bArr2, i3, i35);
        smallints_to_fpr(falconFPRArr7, i34, bArr3, i4, i35);
        FalconFPR[] falconFPRArr8 = falconFPRArr;
        int i36 = i7;
        smallints_to_fpr(falconFPRArr8, i31, bArr4, i5, i36);
        int i37 = i7;
        int i38 = i32;
        this.fft.FFT(falconFPRArr6, i38, i37);
        this.fft.FFT(falconFPRArr6, i8, i37);
        this.fft.FFT(falconFPRArr6, i34, i37);
        int i39 = i31;
        this.fft.FFT(falconFPRArr6, i39, i37);
        this.fft.poly_neg(falconFPRArr6, i38, i37);
        this.fft.poly_neg(falconFPRArr6, i34, i37);
        int i40 = MKN;
        int i41 = i23;
        int i42 = i41 + i40;
        System.arraycopy(falconFPRArr6, i33, falconFPRArr6, i41, i40);
        int i43 = i28;
        System.arraycopy(falconFPRArr6, i43, falconFPRArr6, i42, i40);
        FalconFPR[] falconFPRArr9 = falconFPRArr;
        this.fft.poly_mul_fft(falconFPRArr8, i41, falconFPRArr9, i8, i36);
        this.fft.poly_mul_fft(falconFPRArr8, i42, falconFPRArr9, i39, i36);
        this.fft.poly_add(falconFPRArr8, i41, falconFPRArr9, i42, i36);
        System.arraycopy(falconFPRArr6, i33, falconFPRArr6, i42, i40);
        this.fft.poly_mul_fft(falconFPRArr8, i42, falconFPRArr9, i38, i36);
        System.arraycopy(falconFPRArr6, i41, falconFPRArr6, i33, i40);
        int i44 = i43;
        this.fft.poly_mul_fft(falconFPRArr8, i44, falconFPRArr9, i34, i36);
        this.fft.poly_add(falconFPRArr8, i44, falconFPRArr9, i42, i36);
        this.fft.iFFT(falconFPRArr6, i33, i37);
        this.fft.iFFT(falconFPRArr6, i43, i37);
        short[] sArr3 = new short[i40];
        int i45 = 0;
        int i46 = 0;
        for (int i47 = 0; i47 < i40; i47++) {
            int fpr_rint = (sArr2[i6 + i47] & 65535) - ((int) this.fpr.fpr_rint(falconFPRArr6[i33 + i47]));
            i45 += fpr_rint * fpr_rint;
            i46 |= i45;
            sArr3[i47] = (short) fpr_rint;
        }
        int i48 = (-(i46 >>> 31)) | i45;
        short[] sArr4 = new short[i40];
        for (int i49 = 0; i49 < i40; i49++) {
            sArr4[i49] = (short) ((int) (-this.fpr.fpr_rint(falconFPRArr6[i43 + i49])));
        }
        if (this.common.is_short_half(i48, sArr4, 0, i37) == 0) {
            return 0;
        }
        System.arraycopy(sArr4, 0, sArr, i, i40);
        return 1;
    }

    /* access modifiers changed from: package-private */
    public int do_sign_tree(SamplerZ samplerZ, SamplerCtx samplerCtx, short[] sArr, int i, FalconFPR[] falconFPRArr, int i2, short[] sArr2, int i3, int i4, FalconFPR[] falconFPRArr2, int i5) {
        int i6 = i4;
        FalconFPR[] falconFPRArr3 = falconFPRArr2;
        int i7 = i5;
        int MKN = MKN(i4);
        int i8 = i7 + MKN;
        int skoff_b00 = i2 + skoff_b00(i6);
        int skoff_b01 = i2 + skoff_b01(i6);
        int skoff_b10 = i2 + skoff_b10(i6);
        int skoff_b11 = i2 + skoff_b11(i6);
        int skoff_tree = i2 + skoff_tree(i6);
        for (int i9 = 0; i9 < MKN; i9++) {
            falconFPRArr3[i7 + i9] = this.fpr.fpr_of((long) sArr2[i3 + i9]);
        }
        this.fft.FFT(falconFPRArr3, i7, i6);
        FalconFPR falconFPR = this.fpr.fpr_inverse_of_q;
        System.arraycopy(falconFPRArr3, i7, falconFPRArr3, i8, MKN);
        FalconFPR[] falconFPRArr4 = falconFPRArr;
        int i10 = i4;
        this.fft.poly_mul_fft(falconFPRArr2, i8, falconFPRArr4, skoff_b01, i10);
        this.fft.poly_mulconst(falconFPRArr3, i8, this.fpr.fpr_neg(falconFPR), i6);
        this.fft.poly_mul_fft(falconFPRArr2, i5, falconFPRArr4, skoff_b11, i10);
        this.fft.poly_mulconst(falconFPRArr3, i7, falconFPR, i6);
        int i11 = i8 + MKN;
        int i12 = i11 + MKN;
        int i13 = i12;
        int i14 = i11;
        int i15 = i8;
        ffSampling_fft(samplerZ, samplerCtx, falconFPRArr2, i11, falconFPRArr2, i12, falconFPRArr, skoff_tree, falconFPRArr2, i5, falconFPRArr2, i15, i4, falconFPRArr2, i12 + MKN);
        FalconFPR[] falconFPRArr5 = falconFPRArr2;
        int i16 = i5;
        int i17 = i14;
        int i18 = MKN;
        System.arraycopy(falconFPRArr5, i17, falconFPRArr5, i16, i18);
        int i19 = i13;
        int i20 = i15;
        System.arraycopy(falconFPRArr5, i19, falconFPRArr5, i20, i18);
        FalconFPR[] falconFPRArr6 = falconFPRArr2;
        FalconFPR[] falconFPRArr7 = falconFPRArr;
        int i21 = i4;
        this.fft.poly_mul_fft(falconFPRArr6, i17, falconFPRArr7, skoff_b00, i21);
        this.fft.poly_mul_fft(falconFPRArr6, i19, falconFPRArr7, skoff_b10, i21);
        this.fft.poly_add(falconFPRArr6, i17, falconFPRArr2, i19, i21);
        System.arraycopy(falconFPRArr5, i16, falconFPRArr5, i19, i18);
        FalconFPR[] falconFPRArr8 = falconFPRArr;
        this.fft.poly_mul_fft(falconFPRArr6, i19, falconFPRArr8, skoff_b01, i21);
        System.arraycopy(falconFPRArr5, i17, falconFPRArr5, i16, i18);
        int i22 = i20;
        this.fft.poly_mul_fft(falconFPRArr6, i22, falconFPRArr8, skoff_b11, i21);
        this.fft.poly_add(falconFPRArr6, i22, falconFPRArr2, i19, i21);
        int i23 = i4;
        this.fft.iFFT(falconFPRArr5, i16, i23);
        this.fft.iFFT(falconFPRArr5, i20, i23);
        short[] sArr3 = new short[i18];
        int i24 = 0;
        int i25 = 0;
        for (int i26 = 0; i26 < i18; i26++) {
            int fpr_rint = (sArr2[i3 + i26] & 65535) - ((int) this.fpr.fpr_rint(falconFPRArr5[i16 + i26]));
            i24 += fpr_rint * fpr_rint;
            i25 |= i24;
            sArr3[i26] = (short) fpr_rint;
        }
        int i27 = (-(i25 >>> 31)) | i24;
        short[] sArr4 = new short[i18];
        for (int i28 = 0; i28 < i18; i28++) {
            sArr4[i28] = (short) ((int) (-this.fpr.fpr_rint(falconFPRArr5[i20 + i28])));
        }
        if (this.common.is_short_half(i27, sArr4, 0, i23) == 0) {
            return 0;
        }
        System.arraycopy(sArr4, 0, sArr, i, i18);
        System.arraycopy(sArr3, 0, falconFPRArr5, i16, i18);
        return 1;
    }

    /* access modifiers changed from: package-private */
    public void expand_privkey(FalconFPR[] falconFPRArr, int i, byte[] bArr, int i2, byte[] bArr2, int i3, byte[] bArr3, int i4, byte[] bArr4, int i5, int i6, FalconFPR[] falconFPRArr2, int i7) {
        FalconFPR[] falconFPRArr3 = falconFPRArr;
        int i8 = i6;
        FalconFPR[] falconFPRArr4 = falconFPRArr2;
        int i9 = i7;
        int MKN = MKN(i6);
        int skoff_b00 = i + skoff_b00(i8);
        int skoff_b01 = i + skoff_b01(i8);
        int skoff_b10 = i + skoff_b10(i8);
        int skoff_b11 = i + skoff_b11(i8);
        FalconFPR[] falconFPRArr5 = falconFPRArr;
        int skoff_tree = i + skoff_tree(i8);
        int i10 = i6;
        smallints_to_fpr(falconFPRArr5, skoff_b01, bArr, i2, i10);
        smallints_to_fpr(falconFPRArr5, skoff_b00, bArr2, i3, i10);
        smallints_to_fpr(falconFPRArr5, skoff_b11, bArr3, i4, i10);
        int i11 = i6;
        smallints_to_fpr(falconFPRArr, skoff_b10, bArr4, i5, i11);
        this.fft.FFT(falconFPRArr3, skoff_b01, i8);
        this.fft.FFT(falconFPRArr3, skoff_b00, i8);
        this.fft.FFT(falconFPRArr3, skoff_b11, i8);
        this.fft.FFT(falconFPRArr3, skoff_b10, i8);
        this.fft.poly_neg(falconFPRArr3, skoff_b01, i8);
        this.fft.poly_neg(falconFPRArr3, skoff_b11, i8);
        int i12 = i9 + MKN;
        int i13 = i12 + MKN;
        int i14 = i13 + MKN;
        System.arraycopy(falconFPRArr3, skoff_b00, falconFPRArr4, i9, MKN);
        this.fft.poly_mulselfadj_fft(falconFPRArr4, i9, i8);
        System.arraycopy(falconFPRArr3, skoff_b01, falconFPRArr4, i14, MKN);
        this.fft.poly_mulselfadj_fft(falconFPRArr4, i14, i8);
        FalconFPR[] falconFPRArr6 = falconFPRArr2;
        this.fft.poly_add(falconFPRArr6, i7, falconFPRArr2, i14, i11);
        System.arraycopy(falconFPRArr3, skoff_b00, falconFPRArr4, i12, MKN);
        FalconFPR[] falconFPRArr7 = falconFPRArr;
        this.fft.poly_muladj_fft(falconFPRArr6, i12, falconFPRArr7, skoff_b10, i11);
        System.arraycopy(falconFPRArr3, skoff_b01, falconFPRArr4, i14, MKN);
        this.fft.poly_muladj_fft(falconFPRArr6, i14, falconFPRArr7, skoff_b11, i11);
        FalconFPR[] falconFPRArr8 = falconFPRArr2;
        int i15 = i14;
        this.fft.poly_add(falconFPRArr6, i12, falconFPRArr8, i15, i11);
        System.arraycopy(falconFPRArr3, skoff_b10, falconFPRArr4, i13, MKN);
        this.fft.poly_mulselfadj_fft(falconFPRArr4, i13, i8);
        System.arraycopy(falconFPRArr3, skoff_b11, falconFPRArr4, i14, MKN);
        this.fft.poly_mulselfadj_fft(falconFPRArr4, i14, i8);
        this.fft.poly_add(falconFPRArr6, i13, falconFPRArr8, i15, i11);
        int i16 = i13;
        int i17 = i12;
        ffLDL_fft(falconFPRArr5, skoff_tree, falconFPRArr2, i7, falconFPRArr2, i17, falconFPRArr2, i16, i6, falconFPRArr4, i14);
        ffLDL_binary_normalize(falconFPRArr3, skoff_tree, i8, i8);
    }

    /* access modifiers changed from: package-private */
    public void ffLDL_binary_normalize(FalconFPR[] falconFPRArr, int i, int i2, int i3) {
        int MKN = MKN(i3);
        if (MKN == 1) {
            FPREngine fPREngine = this.fpr;
            falconFPRArr[i] = fPREngine.fpr_mul(fPREngine.fpr_sqrt(falconFPRArr[i]), this.fpr.fpr_inv_sigma[i2]);
            return;
        }
        int i4 = i + MKN;
        int i5 = i3 - 1;
        ffLDL_binary_normalize(falconFPRArr, i4, i2, i5);
        ffLDL_binary_normalize(falconFPRArr, i4 + ffLDL_treesize(i5), i2, i5);
    }

    /* access modifiers changed from: package-private */
    public void ffLDL_fft(FalconFPR[] falconFPRArr, int i, FalconFPR[] falconFPRArr2, int i2, FalconFPR[] falconFPRArr3, int i3, FalconFPR[] falconFPRArr4, int i4, int i5, FalconFPR[] falconFPRArr5, int i6) {
        FalconFPR[] falconFPRArr6 = falconFPRArr2;
        int i7 = i2;
        FalconFPR[] falconFPRArr7 = falconFPRArr5;
        int i8 = i6;
        int MKN = MKN(i5);
        if (MKN == 1) {
            falconFPRArr[i] = falconFPRArr6[i7];
            return;
        }
        int i9 = MKN >> 1;
        int i10 = i8 + MKN;
        int i11 = i8 + (MKN << 1);
        System.arraycopy(falconFPRArr6, i7, falconFPRArr7, i8, MKN);
        this.fft.poly_LDLmv_fft(falconFPRArr5, i10, falconFPRArr, i, falconFPRArr2, i2, falconFPRArr3, i3, falconFPRArr4, i4, i5);
        FalconFPR[] falconFPRArr8 = falconFPRArr5;
        FalconFPR[] falconFPRArr9 = falconFPRArr5;
        int i12 = i11;
        int i13 = i10;
        FalconFPR[] falconFPRArr10 = falconFPRArr5;
        int i14 = MKN;
        int i15 = i5;
        this.fft.poly_split_fft(falconFPRArr8, i11, falconFPRArr9, i11 + i9, falconFPRArr10, i6, i15);
        int i16 = i8 + i9;
        this.fft.poly_split_fft(falconFPRArr8, i6, falconFPRArr9, i16, falconFPRArr10, i13, i15);
        System.arraycopy(falconFPRArr7, i12, falconFPRArr7, i13, i14);
        int i17 = i + i14;
        int i18 = i5 - 1;
        FalconFPR[] falconFPRArr11 = falconFPRArr;
        int i19 = i18;
        FalconFPR[] falconFPRArr12 = falconFPRArr5;
        int i20 = i12;
        ffLDL_fft_inner(falconFPRArr11, i17, falconFPRArr9, i13, falconFPRArr10, i13 + i9, i19, falconFPRArr12, i20);
        ffLDL_fft_inner(falconFPRArr11, i17 + ffLDL_treesize(i18), falconFPRArr9, i6, falconFPRArr10, i16, i19, falconFPRArr12, i20);
    }

    /* access modifiers changed from: package-private */
    public void ffLDL_fft_inner(FalconFPR[] falconFPRArr, int i, FalconFPR[] falconFPRArr2, int i2, FalconFPR[] falconFPRArr3, int i3, int i4, FalconFPR[] falconFPRArr4, int i5) {
        int MKN = MKN(i4);
        if (MKN == 1) {
            falconFPRArr[i] = falconFPRArr2[i2];
            return;
        }
        int i6 = MKN >> 1;
        this.fft.poly_LDLmv_fft(falconFPRArr4, i5, falconFPRArr, i, falconFPRArr2, i2, falconFPRArr3, i3, falconFPRArr2, i2, i4);
        int i7 = i3 + i6;
        int i8 = i4;
        this.fft.poly_split_fft(falconFPRArr3, i3, falconFPRArr3, i7, falconFPRArr2, i2, i8);
        int i9 = i2 + i6;
        this.fft.poly_split_fft(falconFPRArr2, i2, falconFPRArr2, i9, falconFPRArr4, i5, i8);
        int i10 = i + MKN;
        int i11 = i4 - 1;
        FalconFPR[] falconFPRArr5 = falconFPRArr;
        int i12 = i11;
        FalconFPR[] falconFPRArr6 = falconFPRArr4;
        int i13 = i5;
        ffLDL_fft_inner(falconFPRArr5, i10, falconFPRArr3, i3, falconFPRArr3, i7, i12, falconFPRArr6, i13);
        ffLDL_fft_inner(falconFPRArr5, i10 + ffLDL_treesize(i11), falconFPRArr2, i2, falconFPRArr2, i9, i12, falconFPRArr6, i13);
    }

    /* access modifiers changed from: package-private */
    public int ffLDL_treesize(int i) {
        return (i + 1) << i;
    }

    /* access modifiers changed from: package-private */
    public void ffSampling_fft(SamplerZ samplerZ, SamplerCtx samplerCtx, FalconFPR[] falconFPRArr, int i, FalconFPR[] falconFPRArr2, int i2, FalconFPR[] falconFPRArr3, int i3, FalconFPR[] falconFPRArr4, int i4, FalconFPR[] falconFPRArr5, int i5, int i6, FalconFPR[] falconFPRArr6, int i7) {
        SamplerZ samplerZ2 = samplerZ;
        SamplerCtx samplerCtx2 = samplerCtx;
        FalconFPR[] falconFPRArr7 = falconFPRArr5;
        int i8 = i5;
        int i9 = i6;
        int i10 = i7;
        if (i9 == 2) {
            FalconFPR falconFPR = falconFPRArr7[i8];
            int i11 = i8 + 2;
            FalconFPR falconFPR2 = falconFPRArr7[i11];
            int i12 = i8 + 1;
            FalconFPR falconFPR3 = falconFPRArr7[i12];
            int i13 = i8 + 3;
            FalconFPR falconFPR4 = falconFPRArr7[i13];
            FalconFPR fpr_add = this.fpr.fpr_add(falconFPR, falconFPR3);
            FalconFPR fpr_add2 = this.fpr.fpr_add(falconFPR2, falconFPR4);
            FalconFPR fpr_half = this.fpr.fpr_half(fpr_add);
            FalconFPR fpr_half2 = this.fpr.fpr_half(fpr_add2);
            FalconFPR fpr_sub = this.fpr.fpr_sub(falconFPR, falconFPR3);
            FalconFPR fpr_sub2 = this.fpr.fpr_sub(falconFPR2, falconFPR4);
            FPREngine fPREngine = this.fpr;
            FalconFPR fpr_mul = fPREngine.fpr_mul(fPREngine.fpr_add(fpr_sub, fpr_sub2), this.fpr.fpr_invsqrt8);
            FPREngine fPREngine2 = this.fpr;
            FalconFPR fpr_mul2 = fPREngine2.fpr_mul(fPREngine2.fpr_sub(fpr_sub2, fpr_sub), this.fpr.fpr_invsqrt8);
            FalconFPR falconFPR5 = falconFPRArr3[i3 + 11];
            FalconFPR fpr_of = this.fpr.fpr_of((long) samplerZ2.sample(samplerCtx2, fpr_mul, falconFPR5));
            int i14 = i11;
            FalconFPR fpr_of2 = this.fpr.fpr_of((long) samplerZ2.sample(samplerCtx2, fpr_mul2, falconFPR5));
            FalconFPR fpr_sub3 = this.fpr.fpr_sub(fpr_mul, fpr_of);
            FalconFPR fpr_sub4 = this.fpr.fpr_sub(fpr_mul2, fpr_of2);
            FalconFPR falconFPR6 = falconFPRArr3[i3 + 8];
            FalconFPR falconFPR7 = falconFPRArr3[i3 + 9];
            FPREngine fPREngine3 = this.fpr;
            int i15 = i13;
            FalconFPR fpr_sub5 = fPREngine3.fpr_sub(fPREngine3.fpr_mul(fpr_sub3, falconFPR6), this.fpr.fpr_mul(fpr_sub4, falconFPR7));
            FPREngine fPREngine4 = this.fpr;
            FalconFPR fpr_add3 = fPREngine4.fpr_add(fPREngine4.fpr_mul(fpr_sub3, falconFPR7), this.fpr.fpr_mul(fpr_sub4, falconFPR6));
            FalconFPR fpr_add4 = this.fpr.fpr_add(fpr_sub5, fpr_half);
            FalconFPR fpr_add5 = this.fpr.fpr_add(fpr_add3, fpr_half2);
            FalconFPR falconFPR8 = falconFPRArr3[i3 + 10];
            FalconFPR fpr_of3 = this.fpr.fpr_of((long) samplerZ2.sample(samplerCtx2, fpr_add4, falconFPR8));
            FalconFPR fpr_of4 = this.fpr.fpr_of((long) samplerZ2.sample(samplerCtx2, fpr_add5, falconFPR8));
            FPREngine fPREngine5 = this.fpr;
            FalconFPR fpr_mul3 = fPREngine5.fpr_mul(fPREngine5.fpr_sub(fpr_of, fpr_of2), this.fpr.fpr_invsqrt2);
            FPREngine fPREngine6 = this.fpr;
            FalconFPR fpr_mul4 = fPREngine6.fpr_mul(fPREngine6.fpr_add(fpr_of, fpr_of2), this.fpr.fpr_invsqrt2);
            FalconFPR fpr_add6 = this.fpr.fpr_add(fpr_of3, fpr_mul3);
            falconFPRArr2[i2] = fpr_add6;
            FalconFPR fpr_add7 = this.fpr.fpr_add(fpr_of4, fpr_mul4);
            falconFPRArr2[i2 + 2] = fpr_add7;
            FalconFPR fpr_sub6 = this.fpr.fpr_sub(fpr_of3, fpr_mul3);
            falconFPRArr2[i2 + 1] = fpr_sub6;
            FalconFPR fpr_sub7 = this.fpr.fpr_sub(fpr_of4, fpr_mul4);
            falconFPRArr2[i2 + 3] = fpr_sub7;
            FalconFPR fpr_sub8 = this.fpr.fpr_sub(falconFPRArr7[i5], fpr_add6);
            FalconFPR fpr_sub9 = this.fpr.fpr_sub(falconFPRArr7[i12], fpr_sub6);
            FalconFPR fpr_sub10 = this.fpr.fpr_sub(falconFPRArr7[i14], fpr_add7);
            FalconFPR fpr_sub11 = this.fpr.fpr_sub(falconFPRArr7[i15], fpr_sub7);
            FalconFPR falconFPR9 = falconFPRArr3[i3];
            FalconFPR falconFPR10 = falconFPRArr3[i3 + 2];
            FPREngine fPREngine7 = this.fpr;
            FalconFPR fpr_sub12 = fPREngine7.fpr_sub(fPREngine7.fpr_mul(fpr_sub8, falconFPR9), this.fpr.fpr_mul(fpr_sub10, falconFPR10));
            FPREngine fPREngine8 = this.fpr;
            FalconFPR fpr_add8 = fPREngine8.fpr_add(fPREngine8.fpr_mul(fpr_sub8, falconFPR10), this.fpr.fpr_mul(fpr_sub10, falconFPR9));
            FalconFPR falconFPR11 = falconFPRArr3[i3 + 1];
            FalconFPR falconFPR12 = falconFPRArr3[i3 + 3];
            FPREngine fPREngine9 = this.fpr;
            FalconFPR fpr_sub13 = fPREngine9.fpr_sub(fPREngine9.fpr_mul(fpr_sub9, falconFPR11), this.fpr.fpr_mul(fpr_sub11, falconFPR12));
            FPREngine fPREngine10 = this.fpr;
            FalconFPR fpr_add9 = fPREngine10.fpr_add(fPREngine10.fpr_mul(fpr_sub9, falconFPR12), this.fpr.fpr_mul(fpr_sub11, falconFPR11));
            FalconFPR fpr_add10 = this.fpr.fpr_add(fpr_sub12, falconFPRArr4[i4]);
            FalconFPR fpr_add11 = this.fpr.fpr_add(fpr_sub13, falconFPRArr4[i4 + 1]);
            FalconFPR fpr_add12 = this.fpr.fpr_add(fpr_add8, falconFPRArr4[i4 + 2]);
            FalconFPR fpr_add13 = this.fpr.fpr_add(fpr_add9, falconFPRArr4[i4 + 3]);
            FalconFPR fpr_add14 = this.fpr.fpr_add(fpr_add10, fpr_add11);
            FalconFPR fpr_add15 = this.fpr.fpr_add(fpr_add12, fpr_add13);
            FalconFPR fpr_half3 = this.fpr.fpr_half(fpr_add14);
            FalconFPR fpr_half4 = this.fpr.fpr_half(fpr_add15);
            FalconFPR fpr_sub14 = this.fpr.fpr_sub(fpr_add10, fpr_add11);
            FalconFPR fpr_sub15 = this.fpr.fpr_sub(fpr_add12, fpr_add13);
            FPREngine fPREngine11 = this.fpr;
            FalconFPR fpr_mul5 = fPREngine11.fpr_mul(fPREngine11.fpr_add(fpr_sub14, fpr_sub15), this.fpr.fpr_invsqrt8);
            FPREngine fPREngine12 = this.fpr;
            FalconFPR fpr_mul6 = fPREngine12.fpr_mul(fPREngine12.fpr_sub(fpr_sub15, fpr_sub14), this.fpr.fpr_invsqrt8);
            FalconFPR falconFPR13 = falconFPRArr3[i3 + 7];
            FalconFPR fpr_of5 = this.fpr.fpr_of((long) samplerZ2.sample(samplerCtx2, fpr_mul5, falconFPR13));
            FalconFPR fpr_of6 = this.fpr.fpr_of((long) samplerZ2.sample(samplerCtx2, fpr_mul6, falconFPR13));
            FalconFPR fpr_sub16 = this.fpr.fpr_sub(fpr_mul5, fpr_of5);
            FalconFPR fpr_sub17 = this.fpr.fpr_sub(fpr_mul6, fpr_of6);
            FalconFPR falconFPR14 = falconFPRArr3[i3 + 4];
            FalconFPR falconFPR15 = falconFPRArr3[i3 + 5];
            FPREngine fPREngine13 = this.fpr;
            FalconFPR fpr_sub18 = fPREngine13.fpr_sub(fPREngine13.fpr_mul(fpr_sub16, falconFPR14), this.fpr.fpr_mul(fpr_sub17, falconFPR15));
            FPREngine fPREngine14 = this.fpr;
            FalconFPR fpr_add16 = fPREngine14.fpr_add(fPREngine14.fpr_mul(fpr_sub16, falconFPR15), this.fpr.fpr_mul(fpr_sub17, falconFPR14));
            FalconFPR fpr_add17 = this.fpr.fpr_add(fpr_sub18, fpr_half3);
            FalconFPR fpr_add18 = this.fpr.fpr_add(fpr_add16, fpr_half4);
            FalconFPR falconFPR16 = falconFPRArr3[i3 + 6];
            FalconFPR fpr_of7 = this.fpr.fpr_of((long) samplerZ2.sample(samplerCtx2, fpr_add17, falconFPR16));
            FalconFPR fpr_of8 = this.fpr.fpr_of((long) samplerZ2.sample(samplerCtx2, fpr_add18, falconFPR16));
            FPREngine fPREngine15 = this.fpr;
            FalconFPR fpr_mul7 = fPREngine15.fpr_mul(fPREngine15.fpr_sub(fpr_of5, fpr_of6), this.fpr.fpr_invsqrt2);
            FPREngine fPREngine16 = this.fpr;
            FalconFPR fpr_mul8 = fPREngine16.fpr_mul(fPREngine16.fpr_add(fpr_of5, fpr_of6), this.fpr.fpr_invsqrt2);
            falconFPRArr[i] = this.fpr.fpr_add(fpr_of7, fpr_mul7);
            falconFPRArr[i + 2] = this.fpr.fpr_add(fpr_of8, fpr_mul8);
            falconFPRArr[i + 1] = this.fpr.fpr_sub(fpr_of7, fpr_mul7);
            falconFPRArr[i + 3] = this.fpr.fpr_sub(fpr_of8, fpr_mul8);
        } else if (i9 == 1) {
            FalconFPR falconFPR17 = falconFPRArr7[i5];
            FalconFPR falconFPR18 = falconFPRArr7[i5 + 1];
            FalconFPR falconFPR19 = falconFPRArr3[i3 + 3];
            FalconFPR fpr_of9 = this.fpr.fpr_of((long) samplerZ2.sample(samplerCtx2, falconFPR17, falconFPR19));
            falconFPRArr2[i2] = fpr_of9;
            FalconFPR fpr_of10 = this.fpr.fpr_of((long) samplerZ2.sample(samplerCtx2, falconFPR18, falconFPR19));
            falconFPRArr2[i2 + 1] = fpr_of10;
            FalconFPR fpr_sub19 = this.fpr.fpr_sub(falconFPR17, fpr_of9);
            FalconFPR fpr_sub20 = this.fpr.fpr_sub(falconFPR18, fpr_of10);
            FalconFPR falconFPR20 = falconFPRArr3[i3];
            FalconFPR falconFPR21 = falconFPRArr3[i3 + 1];
            FPREngine fPREngine17 = this.fpr;
            FalconFPR fpr_sub21 = fPREngine17.fpr_sub(fPREngine17.fpr_mul(fpr_sub19, falconFPR20), this.fpr.fpr_mul(fpr_sub20, falconFPR21));
            FPREngine fPREngine18 = this.fpr;
            FalconFPR fpr_add19 = fPREngine18.fpr_add(fPREngine18.fpr_mul(fpr_sub19, falconFPR21), this.fpr.fpr_mul(fpr_sub20, falconFPR20));
            FalconFPR fpr_add20 = this.fpr.fpr_add(fpr_sub21, falconFPRArr4[i4]);
            FalconFPR fpr_add21 = this.fpr.fpr_add(fpr_add19, falconFPRArr4[i4 + 1]);
            FalconFPR falconFPR22 = falconFPRArr3[i3 + 2];
            falconFPRArr[i] = this.fpr.fpr_of((long) samplerZ2.sample(samplerCtx2, fpr_add20, falconFPR22));
            falconFPRArr[i + 1] = this.fpr.fpr_of((long) samplerZ2.sample(samplerCtx2, fpr_add21, falconFPR22));
        } else {
            int i16 = 1 << i9;
            int i17 = i16 >> 1;
            int i18 = i3 + i16;
            int i19 = i9 - 1;
            int ffLDL_treesize = i18 + ffLDL_treesize(i19);
            int i20 = i2 + i17;
            this.fft.poly_split_fft(falconFPRArr2, i2, falconFPRArr2, i20, falconFPRArr5, i5, i6);
            int i21 = i10 + i17;
            int i22 = i10 + i16;
            FalconFPR[] falconFPRArr8 = falconFPRArr6;
            int i23 = i7;
            FalconFPR[] falconFPRArr9 = falconFPRArr6;
            int i24 = i21;
            int i25 = i19;
            FalconFPR[] falconFPRArr10 = falconFPRArr6;
            ffSampling_fft(samplerZ, samplerCtx, falconFPRArr8, i23, falconFPRArr9, i24, falconFPRArr3, ffLDL_treesize, falconFPRArr2, i2, falconFPRArr2, i20, i25, falconFPRArr10, i22);
            int i26 = i6;
            this.fft.poly_merge_fft(falconFPRArr2, i2, falconFPRArr8, i23, falconFPRArr9, i24, i26);
            System.arraycopy(falconFPRArr5, i5, falconFPRArr10, i7, i16);
            FalconFPR[] falconFPRArr11 = falconFPRArr6;
            int i27 = i7;
            int i28 = i6;
            this.fft.poly_sub(falconFPRArr11, i27, falconFPRArr2, i2, i28);
            this.fft.poly_mul_fft(falconFPRArr11, i27, falconFPRArr3, i3, i28);
            this.fft.poly_add(falconFPRArr11, i27, falconFPRArr4, i4, i28);
            int i29 = i + i17;
            FalconFPR[] falconFPRArr12 = falconFPRArr6;
            this.fft.poly_split_fft(falconFPRArr, i, falconFPRArr, i29, falconFPRArr12, i7, i26);
            ffSampling_fft(samplerZ, samplerCtx, falconFPRArr6, i7, falconFPRArr12, i21, falconFPRArr3, i18, falconFPRArr, i, falconFPRArr, i29, i25, falconFPRArr10, i22);
            this.fft.poly_merge_fft(falconFPRArr, i, falconFPRArr6, i7, falconFPRArr6, i21, i6);
        }
    }

    /* access modifiers changed from: package-private */
    public void ffSampling_fft_dyntree(SamplerZ samplerZ, SamplerCtx samplerCtx, FalconFPR[] falconFPRArr, int i, FalconFPR[] falconFPRArr2, int i2, FalconFPR[] falconFPRArr3, int i3, FalconFPR[] falconFPRArr4, int i4, FalconFPR[] falconFPRArr5, int i5, int i6, int i7, FalconFPR[] falconFPRArr6, int i8) {
        SamplerZ samplerZ2 = samplerZ;
        SamplerCtx samplerCtx2 = samplerCtx;
        FalconFPR[] falconFPRArr7 = falconFPRArr2;
        int i9 = i2;
        FalconFPR[] falconFPRArr8 = falconFPRArr3;
        int i10 = i3;
        FalconFPR[] falconFPRArr9 = falconFPRArr4;
        int i11 = i4;
        FalconFPR[] falconFPRArr10 = falconFPRArr5;
        int i12 = i5;
        FalconFPR[] falconFPRArr11 = falconFPRArr6;
        int i13 = i8;
        if (i7 == 0) {
            FalconFPR falconFPR = falconFPRArr8[i10];
            FPREngine fPREngine = this.fpr;
            FalconFPR fpr_mul = fPREngine.fpr_mul(fPREngine.fpr_sqrt(falconFPR), this.fpr.fpr_inv_sigma[i6]);
            falconFPRArr[i] = this.fpr.fpr_of((long) samplerZ2.sample(samplerCtx2, falconFPRArr[i], fpr_mul));
            falconFPRArr7[i9] = this.fpr.fpr_of((long) samplerZ2.sample(samplerCtx2, falconFPRArr7[i9], fpr_mul));
            return;
        }
        int i14 = 1 << i7;
        int i15 = i14 >> 1;
        int i16 = i14;
        int i17 = i13;
        FalconFPR[] falconFPRArr12 = falconFPRArr11;
        int i18 = i12;
        int i19 = i7;
        this.fft.poly_LDL_fft(falconFPRArr3, i3, falconFPRArr4, i4, falconFPRArr5, i5, i19);
        int i20 = i17 + i15;
        FalconFPR[] falconFPRArr13 = falconFPRArr6;
        int i21 = i8;
        FalconFPR[] falconFPRArr14 = falconFPRArr6;
        int i22 = i20;
        this.fft.poly_split_fft(falconFPRArr13, i21, falconFPRArr14, i22, falconFPRArr3, i3, i19);
        System.arraycopy(falconFPRArr12, i17, falconFPRArr8, i10, i16);
        this.fft.poly_split_fft(falconFPRArr13, i21, falconFPRArr14, i22, falconFPRArr5, i5, i19);
        FalconFPR[] falconFPRArr15 = falconFPRArr5;
        System.arraycopy(falconFPRArr12, i17, falconFPRArr15, i18, i16);
        FalconFPR[] falconFPRArr16 = falconFPRArr4;
        int i23 = i4;
        int i24 = i18;
        System.arraycopy(falconFPRArr16, i23, falconFPRArr12, i17, i16);
        System.arraycopy(falconFPRArr8, i10, falconFPRArr16, i23, i15);
        int i25 = i23 + i15;
        int i26 = i25;
        System.arraycopy(falconFPRArr15, i24, falconFPRArr16, i25, i15);
        int i27 = i17 + i16;
        int i28 = i27;
        int i29 = i27 + i15;
        int i30 = i29;
        this.fft.poly_split_fft(falconFPRArr6, i27, falconFPRArr6, i29, falconFPRArr2, i2, i7);
        int i31 = i7 - 1;
        int i32 = i27;
        FalconFPR[] falconFPRArr17 = falconFPRArr6;
        FalconFPR[] falconFPRArr18 = falconFPRArr6;
        ffSampling_fft_dyntree(samplerZ, samplerCtx, falconFPRArr17, i27, falconFPRArr18, i30, falconFPRArr15, i5, falconFPRArr5, i24 + i15, falconFPRArr4, i26, i6, i31, falconFPRArr6, i27 + i16);
        int i33 = i16;
        int i34 = i8 + (i33 << 1);
        FalconFPR[] falconFPRArr19 = falconFPRArr6;
        this.fft.poly_merge_fft(falconFPRArr19, i34, falconFPRArr17, i32, falconFPRArr18, i30, i7);
        FalconFPR[] falconFPRArr20 = falconFPRArr2;
        int i35 = i2;
        FalconFPR[] falconFPRArr21 = falconFPRArr6;
        int i36 = i32;
        System.arraycopy(falconFPRArr20, i35, falconFPRArr21, i36, i33);
        int i37 = i7;
        this.fft.poly_sub(falconFPRArr19, i36, falconFPRArr17, i34, i37);
        System.arraycopy(falconFPRArr21, i34, falconFPRArr20, i35, i33);
        this.fft.poly_mul_fft(falconFPRArr19, i8, falconFPRArr17, i36, i37);
        this.fft.poly_add(falconFPRArr, i, falconFPRArr17, i8, i37);
        this.fft.poly_split_fft(falconFPRArr6, i8, falconFPRArr17, i20, falconFPRArr, i, i7);
        SamplerZ samplerZ3 = samplerZ;
        SamplerCtx samplerCtx3 = samplerCtx;
        FalconFPR[] falconFPRArr22 = falconFPRArr6;
        int i38 = i8;
        FalconFPR[] falconFPRArr23 = falconFPRArr6;
        ffSampling_fft_dyntree(samplerZ3, samplerCtx3, falconFPRArr22, i38, falconFPRArr23, i20, falconFPRArr3, i3, falconFPRArr3, i3 + i15, falconFPRArr4, i4, i6, i31, falconFPRArr6, i28);
        this.fft.poly_merge_fft(falconFPRArr, i, falconFPRArr6, i8, falconFPRArr6, i20, i7);
    }

    /* access modifiers changed from: package-private */
    public void sign_dyn(short[] sArr, int i, SHAKE256 shake256, byte[] bArr, int i2, byte[] bArr2, int i3, byte[] bArr3, int i4, byte[] bArr4, int i5, short[] sArr2, int i6, int i7, FalconFPR[] falconFPRArr, int i8) {
        SamplerCtx samplerCtx;
        SamplerZ samplerZ;
        do {
            samplerCtx = r0;
            SamplerCtx samplerCtx2 = new SamplerCtx();
            samplerZ = r3;
            SamplerZ samplerZ2 = new SamplerZ();
            samplerCtx2.sigma_min = this.fpr.fpr_sigma_min[i7];
            samplerCtx2.p.prng_init(shake256);
        } while (do_sign_dyn(samplerZ, samplerCtx, sArr, i, bArr, i2, bArr2, i3, bArr3, i4, bArr4, i5, sArr2, i6, i7, falconFPRArr, i8) == 0);
    }

    /* access modifiers changed from: package-private */
    public void sign_tree(short[] sArr, int i, SHAKE256 shake256, FalconFPR[] falconFPRArr, int i2, short[] sArr2, int i3, int i4, FalconFPR[] falconFPRArr2, int i5) {
        SamplerCtx samplerCtx;
        SamplerZ samplerZ;
        do {
            samplerCtx = new SamplerCtx();
            samplerZ = new SamplerZ();
            samplerCtx.sigma_min = this.fpr.fpr_sigma_min[i4];
            samplerCtx.p.prng_init(shake256);
        } while (do_sign_tree(samplerZ, samplerCtx, sArr, i, falconFPRArr, i2, sArr2, i3, i4, falconFPRArr2, i5) == 0);
    }

    /* access modifiers changed from: package-private */
    public int skoff_b00(int i) {
        return 0;
    }

    /* access modifiers changed from: package-private */
    public int skoff_b01(int i) {
        return MKN(i);
    }

    /* access modifiers changed from: package-private */
    public int skoff_b10(int i) {
        return MKN(i) * 2;
    }

    /* access modifiers changed from: package-private */
    public int skoff_b11(int i) {
        return MKN(i) * 3;
    }

    /* access modifiers changed from: package-private */
    public int skoff_tree(int i) {
        return MKN(i) * 4;
    }

    /* access modifiers changed from: package-private */
    public void smallints_to_fpr(FalconFPR[] falconFPRArr, int i, byte[] bArr, int i2, int i3) {
        int MKN = MKN(i3);
        for (int i4 = 0; i4 < MKN; i4++) {
            falconFPRArr[i + i4] = this.fpr.fpr_of((long) bArr[i2 + i4]);
        }
    }
}
