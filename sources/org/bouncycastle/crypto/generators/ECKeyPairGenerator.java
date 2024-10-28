package org.bouncycastle.crypto.generators;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.bouncycastle.crypto.CryptoServicePurpose;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.constraints.ConstraintUtils;
import org.bouncycastle.crypto.constraints.DefaultServiceProperties;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECKeyGenerationParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.math.ec.ECConstants;
import org.bouncycastle.math.ec.ECMultiplier;
import org.bouncycastle.math.ec.FixedPointCombMultiplier;
import org.bouncycastle.math.ec.WNafUtil;
import org.bouncycastle.util.BigIntegers;

public class ECKeyPairGenerator implements AsymmetricCipherKeyPairGenerator, ECConstants {
    private final String name;
    ECDomainParameters params;
    SecureRandom random;

    public ECKeyPairGenerator() {
        this("ECKeyGen");
    }

    protected ECKeyPairGenerator(String str) {
        this.name = str;
    }

    /* access modifiers changed from: protected */
    public ECMultiplier createBasePointMultiplier() {
        return new FixedPointCombMultiplier();
    }

    public AsymmetricCipherKeyPair generateKeyPair() {
        BigInteger n = this.params.getN();
        int bitLength = n.bitLength();
        int i = bitLength >>> 2;
        while (true) {
            BigInteger createRandomBigInteger = BigIntegers.createRandomBigInteger(bitLength, this.random);
            if (!isOutOfRangeD(createRandomBigInteger, n) && WNafUtil.getNafWeight(createRandomBigInteger) >= i) {
                return new AsymmetricCipherKeyPair((AsymmetricKeyParameter) new ECPublicKeyParameters(createBasePointMultiplier().multiply(this.params.getG(), createRandomBigInteger), this.params), (AsymmetricKeyParameter) new ECPrivateKeyParameters(createRandomBigInteger, this.params));
            }
        }
    }

    public void init(KeyGenerationParameters keyGenerationParameters) {
        ECKeyGenerationParameters eCKeyGenerationParameters = (ECKeyGenerationParameters) keyGenerationParameters;
        this.random = eCKeyGenerationParameters.getRandom();
        ECDomainParameters domainParameters = eCKeyGenerationParameters.getDomainParameters();
        this.params = domainParameters;
        CryptoServicesRegistrar.checkConstraints(new DefaultServiceProperties(this.name, ConstraintUtils.bitsOfSecurityFor(domainParameters.getCurve()), eCKeyGenerationParameters.getDomainParameters(), CryptoServicePurpose.KEYGEN));
    }

    /* access modifiers changed from: protected */
    public boolean isOutOfRangeD(BigInteger bigInteger, BigInteger bigInteger2) {
        return bigInteger.compareTo(ONE) < 0 || bigInteger.compareTo(bigInteger2) >= 0;
    }
}
