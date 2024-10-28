package org.bouncycastle.pqc.jcajce.provider.ntru;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.pqc.crypto.ntru.NTRUKeyGenerationParameters;
import org.bouncycastle.pqc.crypto.ntru.NTRUKeyPairGenerator;
import org.bouncycastle.pqc.crypto.ntru.NTRUParameters;
import org.bouncycastle.pqc.crypto.ntru.NTRUPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.ntru.NTRUPublicKeyParameters;
import org.bouncycastle.pqc.jcajce.provider.util.SpecUtil;
import org.bouncycastle.pqc.jcajce.spec.NTRUParameterSpec;
import org.bouncycastle.util.Strings;

public class NTRUKeyPairGeneratorSpi extends KeyPairGenerator {
    private static Map parameters;
    NTRUKeyPairGenerator engine = new NTRUKeyPairGenerator();
    boolean initialised = false;
    NTRUKeyGenerationParameters param;
    SecureRandom random = CryptoServicesRegistrar.getSecureRandom();

    static {
        HashMap hashMap = new HashMap();
        parameters = hashMap;
        hashMap.put(NTRUParameterSpec.ntruhps2048509.getName(), NTRUParameters.ntruhps2048509);
        parameters.put(NTRUParameterSpec.ntruhps2048677.getName(), NTRUParameters.ntruhps2048677);
        parameters.put(NTRUParameterSpec.ntruhps4096821.getName(), NTRUParameters.ntruhps4096821);
        parameters.put(NTRUParameterSpec.ntruhrss701.getName(), NTRUParameters.ntruhrss701);
    }

    public NTRUKeyPairGeneratorSpi() {
        super("NTRU");
    }

    private static String getNameFromParams(AlgorithmParameterSpec algorithmParameterSpec) {
        return algorithmParameterSpec instanceof NTRUParameterSpec ? ((NTRUParameterSpec) algorithmParameterSpec).getName() : Strings.toLowerCase(SpecUtil.getNameFrom(algorithmParameterSpec));
    }

    public KeyPair generateKeyPair() {
        if (!this.initialised) {
            NTRUKeyGenerationParameters nTRUKeyGenerationParameters = new NTRUKeyGenerationParameters(this.random, NTRUParameters.ntruhps2048509);
            this.param = nTRUKeyGenerationParameters;
            this.engine.init(nTRUKeyGenerationParameters);
            this.initialised = true;
        }
        AsymmetricCipherKeyPair generateKeyPair = this.engine.generateKeyPair();
        return new KeyPair(new BCNTRUPublicKey((NTRUPublicKeyParameters) generateKeyPair.getPublic()), new BCNTRUPrivateKey((NTRUPrivateKeyParameters) generateKeyPair.getPrivate()));
    }

    public void initialize(int i, SecureRandom secureRandom) {
        throw new IllegalArgumentException("use AlgorithmParameterSpec");
    }

    public void initialize(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidAlgorithmParameterException {
        String nameFromParams = getNameFromParams(algorithmParameterSpec);
        if (nameFromParams != null) {
            NTRUKeyGenerationParameters nTRUKeyGenerationParameters = new NTRUKeyGenerationParameters(secureRandom, (NTRUParameters) parameters.get(nameFromParams));
            this.param = nTRUKeyGenerationParameters;
            this.engine.init(nTRUKeyGenerationParameters);
            this.initialised = true;
            return;
        }
        throw new InvalidAlgorithmParameterException("invalid ParameterSpec: " + algorithmParameterSpec);
    }
}
