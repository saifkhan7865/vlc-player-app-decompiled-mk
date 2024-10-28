package org.bouncycastle.pqc.crypto.crystals.kyber;

import java.security.SecureRandom;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;

public class KyberKeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
    private KyberParameters kyberParams;
    private SecureRandom random;

    private AsymmetricCipherKeyPair genKeyPair() {
        KyberEngine engine = this.kyberParams.getEngine();
        engine.init(this.random);
        byte[][] generateKemKeyPair = engine.generateKemKeyPair();
        return new AsymmetricCipherKeyPair((AsymmetricKeyParameter) new KyberPublicKeyParameters(this.kyberParams, generateKemKeyPair[0], generateKemKeyPair[1]), (AsymmetricKeyParameter) new KyberPrivateKeyParameters(this.kyberParams, generateKemKeyPair[2], generateKemKeyPair[3], generateKemKeyPair[4], generateKemKeyPair[0], generateKemKeyPair[1]));
    }

    private void initialize(KeyGenerationParameters keyGenerationParameters) {
        this.kyberParams = ((KyberKeyGenerationParameters) keyGenerationParameters).getParameters();
        this.random = keyGenerationParameters.getRandom();
    }

    public AsymmetricCipherKeyPair generateKeyPair() {
        return genKeyPair();
    }

    public void init(KeyGenerationParameters keyGenerationParameters) {
        initialize(keyGenerationParameters);
    }
}
