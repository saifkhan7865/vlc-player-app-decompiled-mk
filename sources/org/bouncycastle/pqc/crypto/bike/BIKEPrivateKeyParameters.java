package org.bouncycastle.pqc.crypto.bike;

import org.bouncycastle.util.Arrays;

public class BIKEPrivateKeyParameters extends BIKEKeyParameters {
    private byte[] h0;
    private byte[] h1;
    private byte[] sigma;

    public BIKEPrivateKeyParameters(BIKEParameters bIKEParameters, byte[] bArr, byte[] bArr2, byte[] bArr3) {
        super(true, bIKEParameters);
        this.h0 = Arrays.clone(bArr);
        this.h1 = Arrays.clone(bArr2);
        this.sigma = Arrays.clone(bArr3);
    }

    public byte[] getEncoded() {
        return Arrays.concatenate(this.h0, this.h1, this.sigma);
    }

    /* access modifiers changed from: package-private */
    public byte[] getH0() {
        return this.h0;
    }

    /* access modifiers changed from: package-private */
    public byte[] getH1() {
        return this.h1;
    }

    /* access modifiers changed from: package-private */
    public byte[] getSigma() {
        return this.sigma;
    }
}
