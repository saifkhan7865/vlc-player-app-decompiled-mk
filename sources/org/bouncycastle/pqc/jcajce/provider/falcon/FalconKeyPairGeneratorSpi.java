package org.bouncycastle.pqc.jcajce.provider.falcon;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.pqc.crypto.falcon.FalconKeyGenerationParameters;
import org.bouncycastle.pqc.crypto.falcon.FalconKeyPairGenerator;
import org.bouncycastle.pqc.crypto.falcon.FalconParameters;
import org.bouncycastle.pqc.crypto.falcon.FalconPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.falcon.FalconPublicKeyParameters;
import org.bouncycastle.pqc.jcajce.provider.util.SpecUtil;
import org.bouncycastle.pqc.jcajce.spec.FalconParameterSpec;
import org.bouncycastle.util.Strings;

public class FalconKeyPairGeneratorSpi extends KeyPairGenerator {
    private static Map parameters;
    FalconKeyPairGenerator engine;
    private final FalconParameters falconParameters;
    boolean initialised;
    FalconKeyGenerationParameters param;
    SecureRandom random;

    public static class Falcon1024 extends FalconKeyPairGeneratorSpi {
        public Falcon1024() {
            super(FalconParameters.falcon_1024);
        }
    }

    public static class Falcon512 extends FalconKeyPairGeneratorSpi {
        public Falcon512() {
            super(FalconParameters.falcon_512);
        }
    }

    static {
        HashMap hashMap = new HashMap();
        parameters = hashMap;
        hashMap.put(FalconParameterSpec.falcon_512.getName(), FalconParameters.falcon_512);
        parameters.put(FalconParameterSpec.falcon_1024.getName(), FalconParameters.falcon_1024);
    }

    public FalconKeyPairGeneratorSpi() {
        super("FALCON");
        this.engine = new FalconKeyPairGenerator();
        this.random = CryptoServicesRegistrar.getSecureRandom();
        this.initialised = false;
        this.falconParameters = null;
    }

    protected FalconKeyPairGeneratorSpi(FalconParameters falconParameters2) {
        super(falconParameters2.getName());
        this.engine = new FalconKeyPairGenerator();
        this.random = CryptoServicesRegistrar.getSecureRandom();
        this.initialised = false;
        this.falconParameters = falconParameters2;
    }

    private static String getNameFromParams(AlgorithmParameterSpec algorithmParameterSpec) {
        return algorithmParameterSpec instanceof FalconParameterSpec ? ((FalconParameterSpec) algorithmParameterSpec).getName() : Strings.toLowerCase(SpecUtil.getNameFrom(algorithmParameterSpec));
    }

    public KeyPair generateKeyPair() {
        if (!this.initialised) {
            FalconParameters falconParameters2 = this.falconParameters;
            if (falconParameters2 != null) {
                this.param = new FalconKeyGenerationParameters(this.random, falconParameters2);
            } else {
                this.param = new FalconKeyGenerationParameters(this.random, FalconParameters.falcon_512);
            }
            this.engine.init(this.param);
            this.initialised = true;
        }
        AsymmetricCipherKeyPair generateKeyPair = this.engine.generateKeyPair();
        return new KeyPair(new BCFalconPublicKey((FalconPublicKeyParameters) generateKeyPair.getPublic()), new BCFalconPrivateKey((FalconPrivateKeyParameters) generateKeyPair.getPrivate()));
    }

    public void initialize(int i, SecureRandom secureRandom) {
        throw new IllegalArgumentException("use AlgorithmParameterSpec");
    }

    public void initialize(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidAlgorithmParameterException {
        String nameFromParams = getNameFromParams(algorithmParameterSpec);
        if (nameFromParams == null || !parameters.containsKey(nameFromParams)) {
            throw new InvalidAlgorithmParameterException("invalid ParameterSpec: " + algorithmParameterSpec);
        }
        FalconParameters falconParameters2 = (FalconParameters) parameters.get(nameFromParams);
        this.param = new FalconKeyGenerationParameters(secureRandom, falconParameters2);
        if (this.falconParameters == null || falconParameters2.getName().equals(this.falconParameters.getName())) {
            this.engine.init(this.param);
            this.initialised = true;
            return;
        }
        throw new InvalidAlgorithmParameterException("key pair generator locked to " + Strings.toUpperCase(this.falconParameters.getName()));
    }
}
