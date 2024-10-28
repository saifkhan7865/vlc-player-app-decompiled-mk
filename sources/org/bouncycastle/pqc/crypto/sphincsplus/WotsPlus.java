package org.bouncycastle.pqc.crypto.sphincsplus;

import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;

class WotsPlus {
    private final SPHINCSPlusEngine engine;
    private final int w;

    WotsPlus(SPHINCSPlusEngine sPHINCSPlusEngine) {
        this.engine = sPHINCSPlusEngine;
        this.w = sPHINCSPlusEngine.WOTS_W;
    }

    /* access modifiers changed from: package-private */
    public void base_w(byte[] bArr, int i, int i2, int[] iArr, int i3, int i4) {
        int i5 = 0;
        int i6 = 0;
        byte b = 0;
        while (i5 < i4) {
            if (i6 == 0) {
                i6 += 8;
                int i7 = i + 1;
                b = bArr[i];
                i = i7;
            }
            i6 -= this.engine.WOTS_LOGW;
            iArr[i3] = (b >>> i6) & (i2 - 1);
            i5++;
            i3++;
        }
    }

    /* access modifiers changed from: package-private */
    public byte[] chain(byte[] bArr, int i, int i2, byte[] bArr2, ADRS adrs) {
        if (i2 == 0) {
            return Arrays.clone(bArr);
        }
        if (i + i2 > this.w - 1) {
            return null;
        }
        for (int i3 = 0; i3 < i2; i3++) {
            adrs.setHashAddress(i + i3);
            bArr = this.engine.F(bArr2, adrs, bArr);
        }
        return bArr;
    }

    public byte[] pkFromSig(byte[] bArr, byte[] bArr2, byte[] bArr3, ADRS adrs) {
        ADRS adrs2 = adrs;
        ADRS adrs3 = new ADRS(adrs2);
        int[] iArr = new int[this.engine.WOTS_LEN];
        base_w(bArr2, 0, this.w, iArr, 0, this.engine.WOTS_LEN1);
        int i = 0;
        for (int i2 = 0; i2 < this.engine.WOTS_LEN1; i2++) {
            i += (this.w - 1) - iArr[i2];
        }
        base_w(Pack.intToBigEndian(i << (8 - ((this.engine.WOTS_LEN2 * this.engine.WOTS_LOGW) % 8))), 4 - (((this.engine.WOTS_LEN2 * this.engine.WOTS_LOGW) + 7) / 8), this.w, iArr, this.engine.WOTS_LEN1, this.engine.WOTS_LEN2);
        byte[] bArr4 = new byte[this.engine.N];
        byte[][] bArr5 = new byte[this.engine.WOTS_LEN][];
        for (int i3 = 0; i3 < this.engine.WOTS_LEN; i3++) {
            adrs2.setChainAddress(i3);
            System.arraycopy(bArr, this.engine.N * i3, bArr4, 0, this.engine.N);
            int i4 = iArr[i3];
            bArr5[i3] = chain(bArr4, i4, (this.w - 1) - i4, bArr3, adrs);
        }
        adrs3.setType(1);
        adrs3.setKeyPairAddress(adrs.getKeyPairAddress());
        return this.engine.T_l(bArr3, adrs3, Arrays.concatenate(bArr5));
    }

    /* access modifiers changed from: package-private */
    public byte[] pkGen(byte[] bArr, byte[] bArr2, ADRS adrs) {
        ADRS adrs2 = new ADRS(adrs);
        byte[][] bArr3 = new byte[this.engine.WOTS_LEN][];
        for (int i = 0; i < this.engine.WOTS_LEN; i++) {
            ADRS adrs3 = new ADRS(adrs);
            adrs3.setType(5);
            adrs3.setKeyPairAddress(adrs.getKeyPairAddress());
            adrs3.setChainAddress(i);
            adrs3.setHashAddress(0);
            byte[] PRF = this.engine.PRF(bArr2, bArr, adrs3);
            adrs3.setType(0);
            adrs3.setKeyPairAddress(adrs.getKeyPairAddress());
            adrs3.setChainAddress(i);
            adrs3.setHashAddress(0);
            bArr3[i] = chain(PRF, 0, this.w - 1, bArr2, adrs3);
        }
        adrs2.setType(1);
        adrs2.setKeyPairAddress(adrs.getKeyPairAddress());
        return this.engine.T_l(bArr2, adrs2, Arrays.concatenate(bArr3));
    }

    public byte[] sign(byte[] bArr, byte[] bArr2, byte[] bArr3, ADRS adrs) {
        ADRS adrs2 = new ADRS(adrs);
        int[] iArr = new int[this.engine.WOTS_LEN];
        base_w(bArr, 0, this.w, iArr, 0, this.engine.WOTS_LEN1);
        int i = 0;
        for (int i2 = 0; i2 < this.engine.WOTS_LEN1; i2++) {
            i += (this.w - 1) - iArr[i2];
        }
        if (this.engine.WOTS_LOGW % 8 != 0) {
            i <<= 8 - ((this.engine.WOTS_LEN2 * this.engine.WOTS_LOGW) % 8);
        }
        base_w(Pack.intToBigEndian(i), 4 - (((this.engine.WOTS_LEN2 * this.engine.WOTS_LOGW) + 7) / 8), this.w, iArr, this.engine.WOTS_LEN1, this.engine.WOTS_LEN2);
        byte[][] bArr4 = new byte[this.engine.WOTS_LEN][];
        for (int i3 = 0; i3 < this.engine.WOTS_LEN; i3++) {
            adrs2.setType(5);
            adrs2.setKeyPairAddress(adrs.getKeyPairAddress());
            adrs2.setChainAddress(i3);
            adrs2.setHashAddress(0);
            byte[] PRF = this.engine.PRF(bArr3, bArr2, adrs2);
            adrs2.setType(0);
            adrs2.setKeyPairAddress(adrs.getKeyPairAddress());
            adrs2.setChainAddress(i3);
            adrs2.setHashAddress(0);
            bArr4[i3] = chain(PRF, 0, iArr[i3], bArr3, adrs2);
        }
        return Arrays.concatenate(bArr4);
    }
}
