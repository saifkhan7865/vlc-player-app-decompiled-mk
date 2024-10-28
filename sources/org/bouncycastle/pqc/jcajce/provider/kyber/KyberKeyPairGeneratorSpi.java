package org.bouncycastle.pqc.jcajce.provider.kyber;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.pqc.crypto.crystals.kyber.KyberKeyGenerationParameters;
import org.bouncycastle.pqc.crypto.crystals.kyber.KyberKeyPairGenerator;
import org.bouncycastle.pqc.crypto.crystals.kyber.KyberParameters;
import org.bouncycastle.pqc.crypto.crystals.kyber.KyberPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.crystals.kyber.KyberPublicKeyParameters;
import org.bouncycastle.pqc.jcajce.provider.util.SpecUtil;
import org.bouncycastle.pqc.jcajce.spec.KyberParameterSpec;
import org.bouncycastle.util.Strings;

public class KyberKeyPairGeneratorSpi extends KeyPairGenerator {
    private static Map parameters;
    KyberKeyPairGenerator engine;
    boolean initialised;
    private KyberParameters kyberParameters;
    KyberKeyGenerationParameters param;
    SecureRandom random;

    public static class Kyber1024 extends KyberKeyPairGeneratorSpi {
        public Kyber1024() {
            super(KyberParameters.kyber1024);
        }
    }

    public static class Kyber512 extends KyberKeyPairGeneratorSpi {
        public Kyber512() {
            super(KyberParameters.kyber512);
        }
    }

    public static class Kyber768 extends KyberKeyPairGeneratorSpi {
        public Kyber768() {
            super(KyberParameters.kyber768);
        }
    }

    static {
        HashMap hashMap = new HashMap();
        parameters = hashMap;
        hashMap.put(KyberParameterSpec.kyber512.getName(), KyberParameters.kyber512);
        parameters.put(KyberParameterSpec.kyber768.getName(), KyberParameters.kyber768);
        parameters.put(KyberParameterSpec.kyber1024.getName(), KyberParameters.kyber1024);
    }

    public KyberKeyPairGeneratorSpi() {
        super("KYBER");
        this.engine = new KyberKeyPairGenerator();
        this.random = CryptoServicesRegistrar.getSecureRandom();
        this.initialised = false;
        this.kyberParameters = null;
    }

    protected KyberKeyPairGeneratorSpi(KyberParameters kyberParameters2) {
        super(Strings.toUpperCase(kyberParameters2.getName()));
        this.engine = new KyberKeyPairGenerator();
        this.random = CryptoServicesRegistrar.getSecureRandom();
        this.initialised = false;
        this.kyberParameters = kyberParameters2;
    }

    private static String getNameFromParams(AlgorithmParameterSpec algorithmParameterSpec) {
        return algorithmParameterSpec instanceof KyberParameterSpec ? ((KyberParameterSpec) algorithmParameterSpec).getName() : Strings.toLowerCase(SpecUtil.getNameFrom(algorithmParameterSpec));
    }

    public KeyPair generateKeyPair() {
        if (!this.initialised) {
            KyberParameters kyberParameters2 = this.kyberParameters;
            if (kyberParameters2 != null) {
                this.param = new KyberKeyGenerationParameters(this.random, kyberParameters2);
            } else {
                this.param = new KyberKeyGenerationParameters(this.random, KyberParameters.kyber1024);
            }
            this.engine.init(this.param);
            this.initialised = true;
        }
        AsymmetricCipherKeyPair generateKeyPair = this.engine.generateKeyPair();
        return new KeyPair(new BCKyberPublicKey((KyberPublicKeyParameters) generateKeyPair.getPublic()), new BCKyberPrivateKey((KyberPrivateKeyParameters) generateKeyPair.getPrivate()));
    }

    public void initialize(int i, SecureRandom secureRandom) {
        throw new IllegalArgumentException("use AlgorithmParameterSpec");
    }

    public void initialize(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidAlgorithmParameterException {
        String nameFromParams = getNameFromParams(algorithmParameterSpec);
        if (nameFromParams == null || !parameters.containsKey(nameFromParams)) {
            throw new InvalidAlgorithmParameterException("invalid ParameterSpec: " + algorithmParameterSpec);
        }
        KyberParameters kyberParameters2 = (KyberParameters) parameters.get(nameFromParams);
        this.param = new KyberKeyGenerationParameters(secureRandom, kyberParameters2);
        if (this.kyberParameters == null || kyberParameters2.getName().equals(this.kyberParameters.getName())) {
            this.engine.init(this.param);
            this.initialised = true;
            return;
        }
        throw new InvalidAlgorithmParameterException("key pair generator locked to " + Strings.toUpperCase(this.kyberParameters.getName()));
    }
}
