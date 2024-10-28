package org.bouncycastle.crypto.modes.gcm;

import java.lang.reflect.Array;
import org.bouncycastle.util.Pack;

public class Tables8kGCMMultiplier implements GCMMultiplier {
    private byte[] H;
    private long[][][] T;

    public void init(byte[] bArr) {
        if (this.T == null) {
            int[] iArr = new int[3];
            iArr[2] = 2;
            iArr[1] = 256;
            iArr[0] = 2;
            this.T = (long[][][]) Array.newInstance(Long.TYPE, iArr);
        } else if (GCMUtil.areEqual(this.H, bArr) != 0) {
            return;
        }
        byte[] bArr2 = new byte[16];
        this.H = bArr2;
        GCMUtil.copy(bArr, bArr2);
        for (int i = 0; i < 2; i++) {
            long[][][] jArr = this.T;
            long[][] jArr2 = jArr[i];
            if (i == 0) {
                GCMUtil.asLongs(this.H, jArr2[1]);
                long[] jArr3 = jArr2[1];
                GCMUtil.multiplyP7(jArr3, jArr3);
            } else {
                GCMUtil.multiplyP8(jArr[i - 1][1], jArr2[1]);
            }
            for (int i2 = 2; i2 < 256; i2 += 2) {
                GCMUtil.divideP(jArr2[i2 >> 1], jArr2[i2]);
                GCMUtil.xor(jArr2[i2], jArr2[1], jArr2[i2 + 1]);
            }
        }
    }

    public void multiplyH(byte[] bArr) {
        byte[] bArr2 = bArr;
        long[][][] jArr = this.T;
        long[][] jArr2 = jArr[0];
        long[][] jArr3 = jArr[1];
        long[] jArr4 = jArr2[bArr2[14] & 255];
        long[] jArr5 = jArr3[bArr2[15] & 255];
        long j = jArr4[0] ^ jArr5[0];
        long j2 = jArr5[1] ^ jArr4[1];
        for (int i = 12; i >= 0; i -= 2) {
            long[] jArr6 = jArr2[bArr2[i] & 255];
            long[] jArr7 = jArr3[bArr2[i + 1] & 255];
            long j3 = j2 << 48;
            j2 = (jArr6[1] ^ jArr7[1]) ^ ((j2 >>> 16) | (j << 48));
            j = (((((j >>> 16) ^ (jArr6[0] ^ jArr7[0])) ^ j3) ^ (j3 >>> 1)) ^ (j3 >>> 2)) ^ (j3 >>> 7);
        }
        Pack.longToBigEndian(j, bArr2, 0);
        Pack.longToBigEndian(j2, bArr2, 8);
    }
}
