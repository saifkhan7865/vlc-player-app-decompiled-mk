package org.bouncycastle.pqc.crypto.sphincsplus;

import com.google.common.base.Ascii;
import java.lang.reflect.Array;

class HarakaSXof extends HarakaSBase {
    public HarakaSXof(byte[] bArr) {
        byte[] bArr2 = new byte[640];
        update(bArr, 0, bArr.length);
        doFinal(bArr2, 0, 640);
        int[] iArr = new int[2];
        iArr[1] = 8;
        iArr[0] = 10;
        this.haraka512_rc = (long[][]) Array.newInstance(Long.TYPE, iArr);
        int[] iArr2 = new int[2];
        iArr2[1] = 8;
        iArr2[0] = 10;
        this.haraka256_rc = (int[][]) Array.newInstance(Integer.TYPE, iArr2);
        for (int i = 0; i < 10; i++) {
            interleaveConstant32(this.haraka256_rc[i], bArr2, i << 5);
            interleaveConstant(this.haraka512_rc[i], bArr2, i << 6);
        }
    }

    public int doFinal(byte[] bArr, int i, int i2) {
        byte[] bArr2 = this.buffer;
        int i3 = this.off;
        bArr2[i3] = (byte) (bArr2[i3] ^ Ascii.US);
        byte[] bArr3 = this.buffer;
        bArr3[31] = (byte) (bArr3[31] ^ 128);
        int i4 = i2;
        while (i4 >= 32) {
            haraka512Perm(this.buffer);
            System.arraycopy(this.buffer, 0, bArr, i, 32);
            i += 32;
            i4 -= 32;
        }
        if (i4 > 0) {
            haraka512Perm(this.buffer);
            System.arraycopy(this.buffer, 0, bArr, i, i4);
        }
        reset();
        return i2;
    }

    public String getAlgorithmName() {
        return "Haraka-S";
    }

    public void update(byte b) {
        byte[] bArr = this.buffer;
        int i = this.off;
        this.off = i + 1;
        bArr[i] = (byte) (b ^ bArr[i]);
        if (this.off == 32) {
            haraka512Perm(this.buffer);
            this.off = 0;
        }
    }

    public void update(byte[] bArr, int i, int i2) {
        int i3 = (this.off + i2) >> 5;
        int i4 = i;
        for (int i5 = 0; i5 < i3; i5++) {
            while (this.off < 32) {
                byte[] bArr2 = this.buffer;
                int i6 = this.off;
                this.off = i6 + 1;
                bArr2[i6] = (byte) (bArr[i4] ^ bArr2[i6]);
                i4++;
            }
            haraka512Perm(this.buffer);
            this.off = 0;
        }
        while (i4 < i + i2) {
            byte[] bArr3 = this.buffer;
            int i7 = this.off;
            this.off = i7 + 1;
            bArr3[i7] = (byte) (bArr3[i7] ^ bArr[i4]);
            i4++;
        }
    }
}
