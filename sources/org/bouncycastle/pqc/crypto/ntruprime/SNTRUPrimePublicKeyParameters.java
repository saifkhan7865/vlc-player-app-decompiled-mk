package org.bouncycastle.pqc.crypto.ntruprime;

import org.bouncycastle.util.Arrays;

public class SNTRUPrimePublicKeyParameters extends SNTRUPrimeKeyParameters {
    private final byte[] encH;

    public SNTRUPrimePublicKeyParameters(SNTRUPrimeParameters sNTRUPrimeParameters, byte[] bArr) {
        super(false, sNTRUPrimeParameters);
        this.encH = Arrays.clone(bArr);
    }

    /* access modifiers changed from: package-private */
    public byte[] getEncH() {
        return this.encH;
    }

    public byte[] getEncoded() {
        return Arrays.clone(this.encH);
    }
}
