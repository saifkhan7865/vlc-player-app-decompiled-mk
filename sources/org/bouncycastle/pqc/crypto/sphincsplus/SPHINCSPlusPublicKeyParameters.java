package org.bouncycastle.pqc.crypto.sphincsplus;

import org.bouncycastle.util.Arrays;

public class SPHINCSPlusPublicKeyParameters extends SPHINCSPlusKeyParameters {
    private final PK pk;

    SPHINCSPlusPublicKeyParameters(SPHINCSPlusParameters sPHINCSPlusParameters, PK pk2) {
        super(false, sPHINCSPlusParameters);
        this.pk = pk2;
    }

    public SPHINCSPlusPublicKeyParameters(SPHINCSPlusParameters sPHINCSPlusParameters, byte[] bArr) {
        super(false, sPHINCSPlusParameters);
        int n = sPHINCSPlusParameters.getN();
        int i = n * 2;
        if (bArr.length == i) {
            this.pk = new PK(Arrays.copyOfRange(bArr, 0, n), Arrays.copyOfRange(bArr, n, i));
            return;
        }
        throw new IllegalArgumentException("public key encoding does not match parameters");
    }

    public byte[] getEncoded() {
        return Arrays.concatenate(this.pk.seed, this.pk.root);
    }

    public byte[] getRoot() {
        return Arrays.clone(this.pk.root);
    }

    public byte[] getSeed() {
        return Arrays.clone(this.pk.seed);
    }
}
