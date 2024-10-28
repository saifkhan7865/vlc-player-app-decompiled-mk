package org.bouncycastle.pqc.jcajce.provider.dilithium;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.pqc.crypto.crystals.dilithium.DilithiumKeyGenerationParameters;
import org.bouncycastle.pqc.crypto.crystals.dilithium.DilithiumKeyPairGenerator;
import org.bouncycastle.pqc.crypto.crystals.dilithium.DilithiumParameters;
import org.bouncycastle.pqc.crypto.crystals.dilithium.DilithiumPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.crystals.dilithium.DilithiumPublicKeyParameters;
import org.bouncycastle.pqc.jcajce.provider.util.SpecUtil;
import org.bouncycastle.pqc.jcajce.spec.DilithiumParameterSpec;
import org.bouncycastle.util.Strings;

public class DilithiumKeyPairGeneratorSpi extends KeyPairGenerator {
    private static Map parameters;
    private final DilithiumParameters dilithiumParameters;
    DilithiumKeyPairGenerator engine;
    boolean initialised;
    DilithiumKeyGenerationParameters param;
    SecureRandom random;

    public static class Base2 extends DilithiumKeyPairGeneratorSpi {
        public Base2() throws NoSuchAlgorithmException {
            super(DilithiumParameters.dilithium2);
        }
    }

    public static class Base3 extends DilithiumKeyPairGeneratorSpi {
        public Base3() throws NoSuchAlgorithmException {
            super(DilithiumParameters.dilithium3);
        }
    }

    public static class Base5 extends DilithiumKeyPairGeneratorSpi {
        public Base5() throws NoSuchAlgorithmException {
            super(DilithiumParameters.dilithium5);
        }
    }

    static {
        HashMap hashMap = new HashMap();
        parameters = hashMap;
        hashMap.put(DilithiumParameterSpec.dilithium2.getName(), DilithiumParameters.dilithium2);
        parameters.put(DilithiumParameterSpec.dilithium3.getName(), DilithiumParameters.dilithium3);
        parameters.put(DilithiumParameterSpec.dilithium5.getName(), DilithiumParameters.dilithium5);
    }

    public DilithiumKeyPairGeneratorSpi() {
        super("DILITHIUM");
        this.engine = new DilithiumKeyPairGenerator();
        this.random = CryptoServicesRegistrar.getSecureRandom();
        this.initialised = false;
        this.dilithiumParameters = null;
    }

    protected DilithiumKeyPairGeneratorSpi(DilithiumParameters dilithiumParameters2) {
        super(Strings.toUpperCase(dilithiumParameters2.getName()));
        this.engine = new DilithiumKeyPairGenerator();
        this.random = CryptoServicesRegistrar.getSecureRandom();
        this.initialised = false;
        this.dilithiumParameters = dilithiumParameters2;
    }

    private static String getNameFromParams(AlgorithmParameterSpec algorithmParameterSpec) {
        return algorithmParameterSpec instanceof DilithiumParameterSpec ? ((DilithiumParameterSpec) algorithmParameterSpec).getName() : Strings.toLowerCase(SpecUtil.getNameFrom(algorithmParameterSpec));
    }

    public KeyPair generateKeyPair() {
        if (!this.initialised) {
            DilithiumParameters dilithiumParameters2 = this.dilithiumParameters;
            if (dilithiumParameters2 != null) {
                this.param = new DilithiumKeyGenerationParameters(this.random, dilithiumParameters2);
            } else {
                this.param = new DilithiumKeyGenerationParameters(this.random, DilithiumParameters.dilithium3);
            }
            this.engine.init(this.param);
            this.initialised = true;
        }
        AsymmetricCipherKeyPair generateKeyPair = this.engine.generateKeyPair();
        return new KeyPair(new BCDilithiumPublicKey((DilithiumPublicKeyParameters) generateKeyPair.getPublic()), new BCDilithiumPrivateKey((DilithiumPrivateKeyParameters) generateKeyPair.getPrivate()));
    }

    public void initialize(int i, SecureRandom secureRandom) {
        throw new IllegalArgumentException("use AlgorithmParameterSpec");
    }

    public void initialize(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidAlgorithmParameterException {
        String nameFromParams = getNameFromParams(algorithmParameterSpec);
        if (nameFromParams == null || !parameters.containsKey(nameFromParams)) {
            throw new InvalidAlgorithmParameterException("invalid ParameterSpec: " + algorithmParameterSpec);
        }
        DilithiumParameters dilithiumParameters2 = (DilithiumParameters) parameters.get(nameFromParams);
        this.param = new DilithiumKeyGenerationParameters(secureRandom, dilithiumParameters2);
        if (this.dilithiumParameters == null || dilithiumParameters2.getName().equals(this.dilithiumParameters.getName())) {
            this.engine.init(this.param);
            this.initialised = true;
            return;
        }
        throw new InvalidAlgorithmParameterException("key pair generator locked to " + Strings.toUpperCase(this.dilithiumParameters.getName()));
    }
}
