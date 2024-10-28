package org.bouncycastle.pqc.jcajce.provider.frodo;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.pqc.crypto.frodo.FrodoKeyGenerationParameters;
import org.bouncycastle.pqc.crypto.frodo.FrodoKeyPairGenerator;
import org.bouncycastle.pqc.crypto.frodo.FrodoParameters;
import org.bouncycastle.pqc.crypto.frodo.FrodoPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.frodo.FrodoPublicKeyParameters;
import org.bouncycastle.pqc.jcajce.provider.util.SpecUtil;
import org.bouncycastle.pqc.jcajce.spec.FrodoParameterSpec;
import org.bouncycastle.util.Strings;

public class FrodoKeyPairGeneratorSpi extends KeyPairGenerator {
    private static Map parameters;
    FrodoKeyPairGenerator engine = new FrodoKeyPairGenerator();
    boolean initialised = false;
    FrodoKeyGenerationParameters param;
    SecureRandom random = CryptoServicesRegistrar.getSecureRandom();

    static {
        HashMap hashMap = new HashMap();
        parameters = hashMap;
        hashMap.put("frodokem19888r3", FrodoParameters.frodokem640aes);
        parameters.put("frodokem19888shaker3", FrodoParameters.frodokem640shake);
        parameters.put("frodokem31296r3", FrodoParameters.frodokem976aes);
        parameters.put("frodokem31296shaker3", FrodoParameters.frodokem976shake);
        parameters.put("frodokem43088r3", FrodoParameters.frodokem1344aes);
        parameters.put("frodokem43088shaker3", FrodoParameters.frodokem1344shake);
        parameters.put(FrodoParameterSpec.frodokem640aes.getName(), FrodoParameters.frodokem640aes);
        parameters.put(FrodoParameterSpec.frodokem640shake.getName(), FrodoParameters.frodokem640shake);
        parameters.put(FrodoParameterSpec.frodokem976aes.getName(), FrodoParameters.frodokem976aes);
        parameters.put(FrodoParameterSpec.frodokem976shake.getName(), FrodoParameters.frodokem976shake);
        parameters.put(FrodoParameterSpec.frodokem1344aes.getName(), FrodoParameters.frodokem1344aes);
        parameters.put(FrodoParameterSpec.frodokem1344shake.getName(), FrodoParameters.frodokem1344shake);
    }

    public FrodoKeyPairGeneratorSpi() {
        super("Frodo");
    }

    private static String getNameFromParams(AlgorithmParameterSpec algorithmParameterSpec) {
        return algorithmParameterSpec instanceof FrodoParameterSpec ? ((FrodoParameterSpec) algorithmParameterSpec).getName() : Strings.toLowerCase(SpecUtil.getNameFrom(algorithmParameterSpec));
    }

    public KeyPair generateKeyPair() {
        if (!this.initialised) {
            FrodoKeyGenerationParameters frodoKeyGenerationParameters = new FrodoKeyGenerationParameters(this.random, FrodoParameters.frodokem1344shake);
            this.param = frodoKeyGenerationParameters;
            this.engine.init(frodoKeyGenerationParameters);
            this.initialised = true;
        }
        AsymmetricCipherKeyPair generateKeyPair = this.engine.generateKeyPair();
        return new KeyPair(new BCFrodoPublicKey((FrodoPublicKeyParameters) generateKeyPair.getPublic()), new BCFrodoPrivateKey((FrodoPrivateKeyParameters) generateKeyPair.getPrivate()));
    }

    public void initialize(int i, SecureRandom secureRandom) {
        throw new IllegalArgumentException("use AlgorithmParameterSpec");
    }

    public void initialize(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidAlgorithmParameterException {
        String nameFromParams = getNameFromParams(algorithmParameterSpec);
        if (nameFromParams != null) {
            FrodoKeyGenerationParameters frodoKeyGenerationParameters = new FrodoKeyGenerationParameters(secureRandom, (FrodoParameters) parameters.get(nameFromParams));
            this.param = frodoKeyGenerationParameters;
            this.engine.init(frodoKeyGenerationParameters);
            this.initialised = true;
            return;
        }
        throw new InvalidAlgorithmParameterException("invalid ParameterSpec: " + algorithmParameterSpec);
    }
}
