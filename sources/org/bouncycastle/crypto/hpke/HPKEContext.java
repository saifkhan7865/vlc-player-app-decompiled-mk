package org.bouncycastle.crypto.hpke;

import org.bouncycastle.crypto.InvalidCipherTextException;

public class HPKEContext {
    protected final AEAD aead;
    protected final byte[] exporterSecret;
    protected final HKDF hkdf;
    protected final byte[] suiteId;

    HPKEContext(AEAD aead2, HKDF hkdf2, byte[] bArr, byte[] bArr2) {
        this.aead = aead2;
        this.hkdf = hkdf2;
        this.exporterSecret = bArr;
        this.suiteId = bArr2;
    }

    public byte[] expand(byte[] bArr, byte[] bArr2, int i) {
        return this.hkdf.Expand(bArr, bArr2, i);
    }

    public byte[] export(byte[] bArr, int i) {
        return this.hkdf.LabeledExpand(this.exporterSecret, this.suiteId, "sec", bArr, i);
    }

    public byte[] extract(byte[] bArr, byte[] bArr2) {
        return this.hkdf.Extract(bArr, bArr2);
    }

    public byte[] open(byte[] bArr, byte[] bArr2) throws InvalidCipherTextException {
        return this.aead.open(bArr, bArr2);
    }

    public byte[] open(byte[] bArr, byte[] bArr2, int i, int i2) throws InvalidCipherTextException {
        return this.aead.open(bArr, bArr2, i, i2);
    }

    public byte[] seal(byte[] bArr, byte[] bArr2) throws InvalidCipherTextException {
        return this.aead.seal(bArr, bArr2);
    }

    public byte[] seal(byte[] bArr, byte[] bArr2, int i, int i2) throws InvalidCipherTextException {
        return this.aead.seal(bArr, bArr2, i, i2);
    }
}
