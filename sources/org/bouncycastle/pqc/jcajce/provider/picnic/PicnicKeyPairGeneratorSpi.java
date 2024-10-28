package org.bouncycastle.pqc.jcajce.provider.picnic;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.pqc.crypto.picnic.PicnicKeyGenerationParameters;
import org.bouncycastle.pqc.crypto.picnic.PicnicKeyPairGenerator;
import org.bouncycastle.pqc.crypto.picnic.PicnicParameters;
import org.bouncycastle.pqc.crypto.picnic.PicnicPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.picnic.PicnicPublicKeyParameters;
import org.bouncycastle.pqc.jcajce.provider.util.SpecUtil;
import org.bouncycastle.pqc.jcajce.spec.PicnicParameterSpec;
import org.bouncycastle.util.Strings;

public class PicnicKeyPairGeneratorSpi extends KeyPairGenerator {
    private static Map parameters;
    PicnicKeyPairGenerator engine = new PicnicKeyPairGenerator();
    boolean initialised = false;
    PicnicKeyGenerationParameters param;
    SecureRandom random = CryptoServicesRegistrar.getSecureRandom();

    static {
        HashMap hashMap = new HashMap();
        parameters = hashMap;
        hashMap.put(PicnicParameterSpec.picnicl1fs.getName(), PicnicParameters.picnicl1fs);
        parameters.put(PicnicParameterSpec.picnicl1ur.getName(), PicnicParameters.picnicl1ur);
        parameters.put(PicnicParameterSpec.picnicl3fs.getName(), PicnicParameters.picnicl3fs);
        parameters.put(PicnicParameterSpec.picnicl3ur.getName(), PicnicParameters.picnicl3ur);
        parameters.put(PicnicParameterSpec.picnicl5fs.getName(), PicnicParameters.picnicl5fs);
        parameters.put(PicnicParameterSpec.picnicl5ur.getName(), PicnicParameters.picnicl5ur);
        parameters.put(PicnicParameterSpec.picnic3l1.getName(), PicnicParameters.picnic3l1);
        parameters.put(PicnicParameterSpec.picnic3l3.getName(), PicnicParameters.picnic3l3);
        parameters.put(PicnicParameterSpec.picnic3l5.getName(), PicnicParameters.picnic3l5);
        parameters.put(PicnicParameterSpec.picnicl1full.getName(), PicnicParameters.picnicl1full);
        parameters.put(PicnicParameterSpec.picnicl3full.getName(), PicnicParameters.picnicl3full);
        parameters.put(PicnicParameterSpec.picnicl5full.getName(), PicnicParameters.picnicl5full);
    }

    public PicnicKeyPairGeneratorSpi() {
        super("Picnic");
    }

    private static String getNameFromParams(AlgorithmParameterSpec algorithmParameterSpec) {
        return algorithmParameterSpec instanceof PicnicParameterSpec ? ((PicnicParameterSpec) algorithmParameterSpec).getName() : Strings.toLowerCase(SpecUtil.getNameFrom(algorithmParameterSpec));
    }

    public KeyPair generateKeyPair() {
        if (!this.initialised) {
            PicnicKeyGenerationParameters picnicKeyGenerationParameters = new PicnicKeyGenerationParameters(this.random, PicnicParameters.picnicl3ur);
            this.param = picnicKeyGenerationParameters;
            this.engine.init(picnicKeyGenerationParameters);
            this.initialised = true;
        }
        AsymmetricCipherKeyPair generateKeyPair = this.engine.generateKeyPair();
        return new KeyPair(new BCPicnicPublicKey((PicnicPublicKeyParameters) generateKeyPair.getPublic()), new BCPicnicPrivateKey((PicnicPrivateKeyParameters) generateKeyPair.getPrivate()));
    }

    public void initialize(int i, SecureRandom secureRandom) {
        throw new IllegalArgumentException("use AlgorithmParameterSpec");
    }

    public void initialize(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidAlgorithmParameterException {
        String nameFromParams = getNameFromParams(algorithmParameterSpec);
        if (nameFromParams != null) {
            PicnicKeyGenerationParameters picnicKeyGenerationParameters = new PicnicKeyGenerationParameters(secureRandom, (PicnicParameters) parameters.get(nameFromParams));
            this.param = picnicKeyGenerationParameters;
            this.engine.init(picnicKeyGenerationParameters);
            this.initialised = true;
            return;
        }
        throw new InvalidAlgorithmParameterException("invalid ParameterSpec: " + algorithmParameterSpec);
    }
}
