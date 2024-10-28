package org.bouncycastle.pqc.crypto.crystals.dilithium;

import java.security.SecureRandom;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;

public class DilithiumKeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
    private DilithiumParameters dilithiumParams;
    private SecureRandom random;

    private AsymmetricCipherKeyPair genKeyPair() {
        byte[][] generateKeyPair = this.dilithiumParams.getEngine(this.random).generateKeyPair();
        return new AsymmetricCipherKeyPair((AsymmetricKeyParameter) new DilithiumPublicKeyParameters(this.dilithiumParams, generateKeyPair[0], generateKeyPair[6]), (AsymmetricKeyParameter) new DilithiumPrivateKeyParameters(this.dilithiumParams, generateKeyPair[0], generateKeyPair[1], generateKeyPair[2], generateKeyPair[3], generateKeyPair[4], generateKeyPair[5], generateKeyPair[6]));
    }

    private void initialize(KeyGenerationParameters keyGenerationParameters) {
        this.dilithiumParams = ((DilithiumKeyGenerationParameters) keyGenerationParameters).getParameters();
        this.random = keyGenerationParameters.getRandom();
    }

    public AsymmetricCipherKeyPair generateKeyPair() {
        return genKeyPair();
    }

    public void init(KeyGenerationParameters keyGenerationParameters) {
        initialize(keyGenerationParameters);
    }
}
