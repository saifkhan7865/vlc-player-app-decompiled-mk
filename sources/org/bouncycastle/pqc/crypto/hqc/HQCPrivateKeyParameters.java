package org.bouncycastle.pqc.crypto.hqc;

import org.bouncycastle.util.Arrays;

public class HQCPrivateKeyParameters extends HQCKeyParameters {
    private byte[] sk;

    public HQCPrivateKeyParameters(HQCParameters hQCParameters, byte[] bArr) {
        super(true, hQCParameters);
        this.sk = Arrays.clone(bArr);
    }

    public byte[] getEncoded() {
        return Arrays.clone(this.sk);
    }

    public byte[] getPrivateKey() {
        return Arrays.clone(this.sk);
    }
}
