package org.bouncycastle.crypto.generators;

import java.math.BigInteger;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.bouncycastle.crypto.CryptoServicePurpose;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.constraints.ConstraintUtils;
import org.bouncycastle.crypto.constraints.DefaultServiceProperties;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.DHParameters;
import org.bouncycastle.crypto.params.ElGamalKeyGenerationParameters;
import org.bouncycastle.crypto.params.ElGamalParameters;
import org.bouncycastle.crypto.params.ElGamalPrivateKeyParameters;
import org.bouncycastle.crypto.params.ElGamalPublicKeyParameters;

public class ElGamalKeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
    private ElGamalKeyGenerationParameters param;

    public AsymmetricCipherKeyPair generateKeyPair() {
        DHKeyGeneratorHelper dHKeyGeneratorHelper = DHKeyGeneratorHelper.INSTANCE;
        ElGamalParameters parameters = this.param.getParameters();
        DHParameters dHParameters = new DHParameters(parameters.getP(), parameters.getG(), (BigInteger) null, parameters.getL());
        BigInteger calculatePrivate = dHKeyGeneratorHelper.calculatePrivate(dHParameters, this.param.getRandom());
        return new AsymmetricCipherKeyPair((AsymmetricKeyParameter) new ElGamalPublicKeyParameters(dHKeyGeneratorHelper.calculatePublic(dHParameters, calculatePrivate), parameters), (AsymmetricKeyParameter) new ElGamalPrivateKeyParameters(calculatePrivate, parameters));
    }

    public void init(KeyGenerationParameters keyGenerationParameters) {
        ElGamalKeyGenerationParameters elGamalKeyGenerationParameters = (ElGamalKeyGenerationParameters) keyGenerationParameters;
        this.param = elGamalKeyGenerationParameters;
        CryptoServicesRegistrar.checkConstraints(new DefaultServiceProperties("ElGamalKeyGen", ConstraintUtils.bitsOfSecurityFor(elGamalKeyGenerationParameters.getParameters().getP()), this.param.getParameters(), CryptoServicePurpose.KEYGEN));
    }
}
