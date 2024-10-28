package org.bouncycastle.crypto.digests;

import java.lang.reflect.Array;
import org.bouncycastle.crypto.CryptoServicePurpose;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Bytes;

public class Haraka512Digest extends HarakaBase {
    private final byte[] buffer;
    private int off;
    private final CryptoServicePurpose purpose;

    public Haraka512Digest() {
        this(CryptoServicePurpose.ANY);
    }

    public Haraka512Digest(CryptoServicePurpose cryptoServicePurpose) {
        this.purpose = cryptoServicePurpose;
        this.buffer = new byte[64];
    }

    public Haraka512Digest(Haraka512Digest haraka512Digest) {
        this.purpose = haraka512Digest.purpose;
        this.buffer = Arrays.clone(haraka512Digest.buffer);
        this.off = haraka512Digest.off;
    }

    private int haraka512256(byte[] bArr, byte[] bArr2, int i) {
        byte[] bArr3 = bArr;
        byte[] bArr4 = bArr2;
        int i2 = i;
        int[] iArr = new int[2];
        iArr[1] = 16;
        iArr[0] = 4;
        byte[][] bArr5 = (byte[][]) Array.newInstance(Byte.TYPE, iArr);
        int[] iArr2 = new int[2];
        iArr2[1] = 16;
        iArr2[0] = 4;
        byte[][] bArr6 = (byte[][]) Array.newInstance(Byte.TYPE, iArr2);
        System.arraycopy(bArr3, 0, bArr5[0], 0, 16);
        System.arraycopy(bArr3, 16, bArr5[1], 0, 16);
        System.arraycopy(bArr3, 32, bArr5[2], 0, 16);
        System.arraycopy(bArr3, 48, bArr5[3], 0, 16);
        bArr5[0] = aesEnc(bArr5[0], RC[0]);
        bArr5[1] = aesEnc(bArr5[1], RC[1]);
        bArr5[2] = aesEnc(bArr5[2], RC[2]);
        bArr5[3] = aesEnc(bArr5[3], RC[3]);
        bArr5[0] = aesEnc(bArr5[0], RC[4]);
        bArr5[1] = aesEnc(bArr5[1], RC[5]);
        bArr5[2] = aesEnc(bArr5[2], RC[6]);
        bArr5[3] = aesEnc(bArr5[3], RC[7]);
        mix512(bArr5, bArr6);
        bArr5[0] = aesEnc(bArr6[0], RC[8]);
        bArr5[1] = aesEnc(bArr6[1], RC[9]);
        bArr5[2] = aesEnc(bArr6[2], RC[10]);
        bArr5[3] = aesEnc(bArr6[3], RC[11]);
        bArr5[0] = aesEnc(bArr5[0], RC[12]);
        bArr5[1] = aesEnc(bArr5[1], RC[13]);
        bArr5[2] = aesEnc(bArr5[2], RC[14]);
        bArr5[3] = aesEnc(bArr5[3], RC[15]);
        mix512(bArr5, bArr6);
        bArr5[0] = aesEnc(bArr6[0], RC[16]);
        bArr5[1] = aesEnc(bArr6[1], RC[17]);
        bArr5[2] = aesEnc(bArr6[2], RC[18]);
        bArr5[3] = aesEnc(bArr6[3], RC[19]);
        bArr5[0] = aesEnc(bArr5[0], RC[20]);
        bArr5[1] = aesEnc(bArr5[1], RC[21]);
        bArr5[2] = aesEnc(bArr5[2], RC[22]);
        bArr5[3] = aesEnc(bArr5[3], RC[23]);
        mix512(bArr5, bArr6);
        bArr5[0] = aesEnc(bArr6[0], RC[24]);
        bArr5[1] = aesEnc(bArr6[1], RC[25]);
        bArr5[2] = aesEnc(bArr6[2], RC[26]);
        bArr5[3] = aesEnc(bArr6[3], RC[27]);
        bArr5[0] = aesEnc(bArr5[0], RC[28]);
        bArr5[1] = aesEnc(bArr5[1], RC[29]);
        bArr5[2] = aesEnc(bArr5[2], RC[30]);
        bArr5[3] = aesEnc(bArr5[3], RC[31]);
        mix512(bArr5, bArr6);
        bArr5[0] = aesEnc(bArr6[0], RC[32]);
        bArr5[1] = aesEnc(bArr6[1], RC[33]);
        bArr5[2] = aesEnc(bArr6[2], RC[34]);
        bArr5[3] = aesEnc(bArr6[3], RC[35]);
        bArr5[0] = aesEnc(bArr5[0], RC[36]);
        bArr5[1] = aesEnc(bArr5[1], RC[37]);
        bArr5[2] = aesEnc(bArr5[2], RC[38]);
        bArr5[3] = aesEnc(bArr5[3], RC[39]);
        mix512(bArr5, bArr6);
        byte[] bArr7 = bArr;
        Bytes.xor(16, bArr6[0], 0, bArr7, 0, bArr5[0], 0);
        Bytes.xor(16, bArr6[1], 0, bArr7, 16, bArr5[1], 0);
        Bytes.xor(16, bArr6[2], 0, bArr7, 32, bArr5[2], 0);
        Bytes.xor(16, bArr6[3], 0, bArr7, 48, bArr5[3], 0);
        System.arraycopy(bArr5[0], 8, bArr4, i2, 8);
        System.arraycopy(bArr5[1], 8, bArr4, i2 + 8, 8);
        System.arraycopy(bArr5[2], 0, bArr4, i2 + 16, 8);
        System.arraycopy(bArr5[3], 0, bArr4, i2 + 24, 8);
        return 32;
    }

    private void mix512(byte[][] bArr, byte[][] bArr2) {
        System.arraycopy(bArr[0], 12, bArr2[0], 0, 4);
        System.arraycopy(bArr[2], 12, bArr2[0], 4, 4);
        System.arraycopy(bArr[1], 12, bArr2[0], 8, 4);
        System.arraycopy(bArr[3], 12, bArr2[0], 12, 4);
        System.arraycopy(bArr[2], 0, bArr2[1], 0, 4);
        System.arraycopy(bArr[0], 0, bArr2[1], 4, 4);
        System.arraycopy(bArr[3], 0, bArr2[1], 8, 4);
        System.arraycopy(bArr[1], 0, bArr2[1], 12, 4);
        System.arraycopy(bArr[2], 4, bArr2[2], 0, 4);
        System.arraycopy(bArr[0], 4, bArr2[2], 4, 4);
        System.arraycopy(bArr[3], 4, bArr2[2], 8, 4);
        System.arraycopy(bArr[1], 4, bArr2[2], 12, 4);
        System.arraycopy(bArr[0], 8, bArr2[3], 0, 4);
        System.arraycopy(bArr[2], 8, bArr2[3], 4, 4);
        System.arraycopy(bArr[1], 8, bArr2[3], 8, 4);
        System.arraycopy(bArr[3], 8, bArr2[3], 12, 4);
    }

    public int doFinal(byte[] bArr, int i) {
        if (this.off != 64) {
            throw new IllegalStateException("input must be exactly 64 bytes");
        } else if (bArr.length - i >= 32) {
            int haraka512256 = haraka512256(this.buffer, bArr, i);
            reset();
            return haraka512256;
        } else {
            throw new IllegalArgumentException("output too short to receive digest");
        }
    }

    public String getAlgorithmName() {
        return "Haraka-512";
    }

    public void reset() {
        this.off = 0;
        Arrays.clear(this.buffer);
    }

    public void update(byte b) {
        int i = this.off;
        if (i <= 63) {
            byte[] bArr = this.buffer;
            this.off = i + 1;
            bArr[i] = b;
            return;
        }
        throw new IllegalArgumentException("total input cannot be more than 64 bytes");
    }

    public void update(byte[] bArr, int i, int i2) {
        int i3 = this.off;
        if (i3 <= 64 - i2) {
            System.arraycopy(bArr, i, this.buffer, i3, i2);
            this.off += i2;
            return;
        }
        throw new IllegalArgumentException("total input cannot be more than 64 bytes");
    }
}
