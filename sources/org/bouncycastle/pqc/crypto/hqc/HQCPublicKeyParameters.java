package org.bouncycastle.pqc.crypto.hqc;

import org.bouncycastle.util.Arrays;

public class HQCPublicKeyParameters extends HQCKeyParameters {
    private byte[] pk;

    public HQCPublicKeyParameters(HQCParameters hQCParameters, byte[] bArr) {
        super(true, hQCParameters);
        this.pk = Arrays.clone(bArr);
    }

    public byte[] getEncoded() {
        return getPublicKey();
    }

    public byte[] getPublicKey() {
        return Arrays.clone(this.pk);
    }
}
