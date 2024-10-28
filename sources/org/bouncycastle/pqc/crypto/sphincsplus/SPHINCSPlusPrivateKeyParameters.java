package org.bouncycastle.pqc.crypto.sphincsplus;

import org.bouncycastle.util.Arrays;

public class SPHINCSPlusPrivateKeyParameters extends SPHINCSPlusKeyParameters {
    final PK pk;
    final SK sk;

    SPHINCSPlusPrivateKeyParameters(SPHINCSPlusParameters sPHINCSPlusParameters, SK sk2, PK pk2) {
        super(true, sPHINCSPlusParameters);
        this.sk = sk2;
        this.pk = pk2;
    }

    public SPHINCSPlusPrivateKeyParameters(SPHINCSPlusParameters sPHINCSPlusParameters, byte[] bArr) {
        super(true, sPHINCSPlusParameters);
        int n = sPHINCSPlusParameters.getN();
        int i = n * 4;
        if (bArr.length == i) {
            int i2 = n * 2;
            this.sk = new SK(Arrays.copyOfRange(bArr, 0, n), Arrays.copyOfRange(bArr, n, i2));
            int i3 = n * 3;
            this.pk = new PK(Arrays.copyOfRange(bArr, i2, i3), Arrays.copyOfRange(bArr, i3, i));
            return;
        }
        throw new IllegalArgumentException("private key encoding does not match parameters");
    }

    public SPHINCSPlusPrivateKeyParameters(SPHINCSPlusParameters sPHINCSPlusParameters, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
        super(true, sPHINCSPlusParameters);
        this.sk = new SK(bArr, bArr2);
        this.pk = new PK(bArr3, bArr4);
    }

    public byte[] getEncoded() {
        return Arrays.concatenate(new byte[][]{this.sk.seed, this.sk.prf, this.pk.seed, this.pk.root});
    }

    public byte[] getEncodedPublicKey() {
        return Arrays.concatenate(this.pk.seed, this.pk.root);
    }

    public byte[] getPrf() {
        return Arrays.clone(this.sk.prf);
    }

    public byte[] getPublicKey() {
        return Arrays.concatenate(this.pk.seed, this.pk.root);
    }

    public byte[] getPublicSeed() {
        return Arrays.clone(this.pk.seed);
    }

    public byte[] getRoot() {
        return Arrays.clone(this.pk.root);
    }

    public byte[] getSeed() {
        return Arrays.clone(this.sk.seed);
    }
}
