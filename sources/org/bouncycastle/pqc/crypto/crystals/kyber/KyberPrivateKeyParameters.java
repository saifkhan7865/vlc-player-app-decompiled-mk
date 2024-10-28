package org.bouncycastle.pqc.crypto.crystals.kyber;

import org.bouncycastle.util.Arrays;

public class KyberPrivateKeyParameters extends KyberKeyParameters {
    final byte[] hpk;
    final byte[] nonce;
    final byte[] rho;
    final byte[] s;
    final byte[] t;

    public KyberPrivateKeyParameters(KyberParameters kyberParameters, byte[] bArr) {
        super(true, kyberParameters);
        KyberEngine engine = kyberParameters.getEngine();
        this.s = Arrays.copyOfRange(bArr, 0, engine.getKyberIndCpaSecretKeyBytes());
        int kyberIndCpaSecretKeyBytes = engine.getKyberIndCpaSecretKeyBytes();
        this.t = Arrays.copyOfRange(bArr, kyberIndCpaSecretKeyBytes, (engine.getKyberIndCpaPublicKeyBytes() + kyberIndCpaSecretKeyBytes) - 32);
        int kyberIndCpaPublicKeyBytes = kyberIndCpaSecretKeyBytes + (engine.getKyberIndCpaPublicKeyBytes() - 32);
        int i = kyberIndCpaPublicKeyBytes + 32;
        this.rho = Arrays.copyOfRange(bArr, kyberIndCpaPublicKeyBytes, i);
        int i2 = kyberIndCpaPublicKeyBytes + 64;
        this.hpk = Arrays.copyOfRange(bArr, i, i2);
        this.nonce = Arrays.copyOfRange(bArr, i2, kyberIndCpaPublicKeyBytes + 96);
    }

    public KyberPrivateKeyParameters(KyberParameters kyberParameters, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5) {
        super(true, kyberParameters);
        this.s = Arrays.clone(bArr);
        this.hpk = Arrays.clone(bArr2);
        this.nonce = Arrays.clone(bArr3);
        this.t = Arrays.clone(bArr4);
        this.rho = Arrays.clone(bArr5);
    }

    public byte[] getEncoded() {
        return Arrays.concatenate(new byte[][]{this.s, this.t, this.rho, this.hpk, this.nonce});
    }

    public byte[] getHPK() {
        return Arrays.clone(this.hpk);
    }

    public byte[] getNonce() {
        return Arrays.clone(this.nonce);
    }

    public byte[] getPrivateKey() {
        return getEncoded();
    }

    public byte[] getPublicKey() {
        return KyberPublicKeyParameters.getEncoded(this.t, this.rho);
    }

    public KyberPublicKeyParameters getPublicKeyParameters() {
        return new KyberPublicKeyParameters(getParameters(), this.t, this.rho);
    }

    public byte[] getRho() {
        return Arrays.clone(this.rho);
    }

    public byte[] getS() {
        return Arrays.clone(this.s);
    }

    public byte[] getT() {
        return Arrays.clone(this.t);
    }
}
