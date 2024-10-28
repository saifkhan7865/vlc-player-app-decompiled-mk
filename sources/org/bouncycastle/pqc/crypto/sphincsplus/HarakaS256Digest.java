package org.bouncycastle.pqc.crypto.sphincsplus;

import org.bouncycastle.crypto.Digest;

class HarakaS256Digest extends HarakaSBase implements Digest {
    public HarakaS256Digest(HarakaSXof harakaSXof) {
        this.haraka256_rc = harakaSXof.haraka256_rc;
    }

    public int doFinal(byte[] bArr, int i) {
        byte[] bArr2 = new byte[32];
        haraka256Perm(bArr2);
        xor(bArr2, 0, this.buffer, 0, bArr, i, 32);
        reset();
        return bArr.length;
    }

    public String getAlgorithmName() {
        return "HarakaS-256";
    }

    public int getDigestSize() {
        return 32;
    }

    public void reset() {
        super.reset();
    }

    public void update(byte b) {
        if (this.off <= 31) {
            byte[] bArr = this.buffer;
            int i = this.off;
            this.off = i + 1;
            bArr[i] = b;
            return;
        }
        throw new IllegalArgumentException("total input cannot be more than 32 bytes");
    }

    public void update(byte[] bArr, int i, int i2) {
        if (this.off <= 32 - i2) {
            System.arraycopy(bArr, i, this.buffer, this.off, i2);
            this.off += i2;
            return;
        }
        throw new IllegalArgumentException("total input cannot be more than 32 bytes");
    }
}
