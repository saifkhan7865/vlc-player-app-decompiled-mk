package org.bouncycastle.pqc.crypto.crystals.dilithium;

import java.security.SecureRandom;
import org.bouncycastle.util.Arrays;

public class DilithiumPrivateKeyParameters extends DilithiumKeyParameters {
    final byte[] k;
    final byte[] rho;
    final byte[] s1;
    final byte[] s2;
    final byte[] t0;
    private final byte[] t1;
    final byte[] tr;

    public DilithiumPrivateKeyParameters(DilithiumParameters dilithiumParameters, byte[] bArr, DilithiumPublicKeyParameters dilithiumPublicKeyParameters) {
        super(true, dilithiumParameters);
        DilithiumEngine engine = dilithiumParameters.getEngine((SecureRandom) null);
        this.rho = Arrays.copyOfRange(bArr, 0, 32);
        this.k = Arrays.copyOfRange(bArr, 32, 64);
        this.tr = Arrays.copyOfRange(bArr, 64, 128);
        int dilithiumL = (engine.getDilithiumL() * engine.getDilithiumPolyEtaPackedBytes()) + 128;
        this.s1 = Arrays.copyOfRange(bArr, 128, dilithiumL);
        int dilithiumK = (engine.getDilithiumK() * engine.getDilithiumPolyEtaPackedBytes()) + dilithiumL;
        this.s2 = Arrays.copyOfRange(bArr, dilithiumL, dilithiumK);
        this.t0 = Arrays.copyOfRange(bArr, dilithiumK, (engine.getDilithiumK() * 416) + dilithiumK);
        if (dilithiumPublicKeyParameters != null) {
            this.t1 = dilithiumPublicKeyParameters.getT1();
        } else {
            this.t1 = null;
        }
    }

    public DilithiumPrivateKeyParameters(DilithiumParameters dilithiumParameters, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5, byte[] bArr6, byte[] bArr7) {
        super(true, dilithiumParameters);
        this.rho = Arrays.clone(bArr);
        this.k = Arrays.clone(bArr2);
        this.tr = Arrays.clone(bArr3);
        this.s1 = Arrays.clone(bArr4);
        this.s2 = Arrays.clone(bArr5);
        this.t0 = Arrays.clone(bArr6);
        this.t1 = Arrays.clone(bArr7);
    }

    public byte[] getEncoded() {
        return Arrays.concatenate(new byte[][]{this.rho, this.k, this.tr, this.s1, this.s2, this.t0});
    }

    public byte[] getK() {
        return Arrays.clone(this.k);
    }

    public byte[] getPrivateKey() {
        return getEncoded();
    }

    public byte[] getPublicKey() {
        return DilithiumPublicKeyParameters.getEncoded(this.rho, this.t1);
    }

    public DilithiumPublicKeyParameters getPublicKeyParameters() {
        return new DilithiumPublicKeyParameters(getParameters(), this.rho, this.t1);
    }

    public byte[] getRho() {
        return Arrays.clone(this.rho);
    }

    public byte[] getS1() {
        return Arrays.clone(this.s1);
    }

    public byte[] getS2() {
        return Arrays.clone(this.s2);
    }

    public byte[] getT0() {
        return Arrays.clone(this.t0);
    }

    public byte[] getT1() {
        return Arrays.clone(this.t1);
    }

    public byte[] getTr() {
        return Arrays.clone(this.tr);
    }
}
