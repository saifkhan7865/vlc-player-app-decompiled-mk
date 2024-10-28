package org.bouncycastle.pqc.crypto.gemss;

import org.bouncycastle.util.Arrays;

public class GeMSSPublicKeyParameters extends GeMSSKeyParameters {
    private final byte[] pk;

    public GeMSSPublicKeyParameters(GeMSSParameters geMSSParameters, byte[] bArr) {
        super(false, geMSSParameters);
        byte[] bArr2 = new byte[bArr.length];
        this.pk = bArr2;
        System.arraycopy(bArr, 0, bArr2, 0, bArr2.length);
    }

    public byte[] getEncoded() {
        return Arrays.clone(this.pk);
    }

    public byte[] getPK() {
        return this.pk;
    }
}
