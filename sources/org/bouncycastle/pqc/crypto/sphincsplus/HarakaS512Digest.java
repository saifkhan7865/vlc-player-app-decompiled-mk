package org.bouncycastle.pqc.crypto.sphincsplus;

import org.bouncycastle.crypto.Digest;

class HarakaS512Digest extends HarakaSBase implements Digest {
    public HarakaS512Digest(HarakaSXof harakaSXof) {
        this.haraka512_rc = harakaSXof.haraka512_rc;
    }

    public int doFinal(byte[] bArr, int i) {
        byte[] bArr2 = new byte[64];
        haraka512Perm(bArr2);
        byte[] bArr3 = bArr2;
        byte[] bArr4 = bArr;
        xor(bArr3, 8, this.buffer, 8, bArr4, i, 8);
        xor(bArr3, 24, this.buffer, 24, bArr4, i + 8, 16);
        xor(bArr3, 48, this.buffer, 48, bArr4, i + 24, 8);
        reset();
        return 64;
    }

    public String getAlgorithmName() {
        return "HarakaS-512";
    }

    public int getDigestSize() {
        return 32;
    }

    public void reset() {
        super.reset();
    }

    public void update(byte b) {
        if (this.off <= 63) {
            byte[] bArr = this.buffer;
            int i = this.off;
            this.off = i + 1;
            bArr[i] = b;
            return;
        }
        throw new IllegalArgumentException("total input cannot be more than 64 bytes");
    }

    public void update(byte[] bArr, int i, int i2) {
        if (this.off <= 64 - i2) {
            System.arraycopy(bArr, i, this.buffer, this.off, i2);
            this.off += i2;
            return;
        }
        throw new IllegalArgumentException("total input cannot be more than 64 bytes");
    }
}
