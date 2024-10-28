package org.bouncycastle.pqc.crypto.frodo;

import java.security.SecureRandom;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;

public class FrodoKeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
    private int B;
    private int D;
    private FrodoKeyGenerationParameters frodoParams;
    private int n;
    private SecureRandom random;

    private AsymmetricCipherKeyPair genKeyPair() {
        FrodoEngine engine = this.frodoParams.getParameters().getEngine();
        byte[] bArr = new byte[engine.getPrivateKeySize()];
        byte[] bArr2 = new byte[engine.getPublicKeySize()];
        engine.kem_keypair(bArr2, bArr, this.random);
        return new AsymmetricCipherKeyPair((AsymmetricKeyParameter) new FrodoPublicKeyParameters(this.frodoParams.getParameters(), bArr2), (AsymmetricKeyParameter) new FrodoPrivateKeyParameters(this.frodoParams.getParameters(), bArr));
    }

    private void initialize(KeyGenerationParameters keyGenerationParameters) {
        this.frodoParams = (FrodoKeyGenerationParameters) keyGenerationParameters;
        this.random = keyGenerationParameters.getRandom();
        this.n = this.frodoParams.getParameters().getN();
        this.D = this.frodoParams.getParameters().getD();
        this.B = this.frodoParams.getParameters().getB();
    }

    public AsymmetricCipherKeyPair generateKeyPair() {
        return genKeyPair();
    }

    public void init(KeyGenerationParameters keyGenerationParameters) {
        initialize(keyGenerationParameters);
    }
}
