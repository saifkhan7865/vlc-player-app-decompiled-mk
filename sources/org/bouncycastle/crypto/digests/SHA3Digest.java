package org.bouncycastle.crypto.digests;

import org.bouncycastle.crypto.CryptoServicePurpose;

public class SHA3Digest extends KeccakDigest {
    public SHA3Digest() {
        this(256, CryptoServicePurpose.ANY);
    }

    public SHA3Digest(int i) {
        super(checkBitLength(i), CryptoServicePurpose.ANY);
    }

    public SHA3Digest(int i, CryptoServicePurpose cryptoServicePurpose) {
        super(checkBitLength(i), cryptoServicePurpose);
    }

    public SHA3Digest(CryptoServicePurpose cryptoServicePurpose) {
        this(256, cryptoServicePurpose);
    }

    public SHA3Digest(SHA3Digest sHA3Digest) {
        super((KeccakDigest) sHA3Digest);
    }

    private static int checkBitLength(int i) {
        if (i == 224 || i == 256 || i == 384 || i == 512) {
            return i;
        }
        throw new IllegalArgumentException("'bitLength' " + i + " not supported for SHA-3");
    }

    public int doFinal(byte[] bArr, int i) {
        absorbBits(2, 2);
        return super.doFinal(bArr, i);
    }

    /* access modifiers changed from: protected */
    public int doFinal(byte[] bArr, int i, byte b, int i2) {
        if (i2 < 0 || i2 > 7) {
            throw new IllegalArgumentException("'partialBits' must be in the range [0,7]");
        }
        int i3 = (b & ((1 << i2) - 1)) | (2 << i2);
        int i4 = i2 + 2;
        if (i4 >= 8) {
            absorb((byte) i3);
            i4 = i2 - 6;
            i3 >>>= 8;
        }
        return super.doFinal(bArr, i, (byte) i3, i4);
    }

    public String getAlgorithmName() {
        return "SHA3-" + this.fixedOutputLength;
    }
}
