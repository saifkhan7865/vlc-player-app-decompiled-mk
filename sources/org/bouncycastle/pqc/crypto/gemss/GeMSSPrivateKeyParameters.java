package org.bouncycastle.pqc.crypto.gemss;

import org.bouncycastle.util.Arrays;

public class GeMSSPrivateKeyParameters extends GeMSSKeyParameters {
    final byte[] sk;

    public GeMSSPrivateKeyParameters(GeMSSParameters geMSSParameters, byte[] bArr) {
        super(false, geMSSParameters);
        byte[] bArr2 = new byte[bArr.length];
        this.sk = bArr2;
        System.arraycopy(bArr, 0, bArr2, 0, bArr2.length);
    }

    public byte[] getEncoded() {
        return Arrays.clone(this.sk);
    }
}
