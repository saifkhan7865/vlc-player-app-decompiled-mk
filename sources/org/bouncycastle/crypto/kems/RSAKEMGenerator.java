package org.bouncycastle.crypto.kems;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.crypto.CryptoServicePurpose;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.DerivationFunction;
import org.bouncycastle.crypto.EncapsulatedSecretGenerator;
import org.bouncycastle.crypto.SecretWithEncapsulation;
import org.bouncycastle.crypto.constraints.ConstraintUtils;
import org.bouncycastle.crypto.constraints.DefaultServiceProperties;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.KDFParameters;
import org.bouncycastle.crypto.params.RSAKeyParameters;
import org.bouncycastle.util.BigIntegers;

public class RSAKEMGenerator implements EncapsulatedSecretGenerator {
    private static final BigInteger ONE = BigInteger.valueOf(1);
    private static final BigInteger ZERO = BigInteger.valueOf(0);
    private DerivationFunction kdf;
    private final int keyLen;
    private SecureRandom rnd;

    public RSAKEMGenerator(int i, DerivationFunction derivationFunction, SecureRandom secureRandom) {
        this.keyLen = i;
        this.kdf = derivationFunction;
        this.rnd = secureRandom;
    }

    static byte[] generateKey(DerivationFunction derivationFunction, BigInteger bigInteger, BigInteger bigInteger2, int i) {
        derivationFunction.init(new KDFParameters(BigIntegers.asUnsignedByteArray((bigInteger.bitLength() + 7) / 8, bigInteger2), (byte[]) null));
        byte[] bArr = new byte[i];
        derivationFunction.generateBytes(bArr, 0, i);
        return bArr;
    }

    public SecretWithEncapsulation generateEncapsulated(AsymmetricKeyParameter asymmetricKeyParameter) {
        RSAKeyParameters rSAKeyParameters = (RSAKeyParameters) asymmetricKeyParameter;
        if (!rSAKeyParameters.isPrivate()) {
            CryptoServicesRegistrar.checkConstraints(new DefaultServiceProperties("RSAKem", ConstraintUtils.bitsOfSecurityFor(rSAKeyParameters.getModulus()), rSAKeyParameters, CryptoServicePurpose.ENCRYPTION));
            BigInteger modulus = rSAKeyParameters.getModulus();
            BigInteger exponent = rSAKeyParameters.getExponent();
            BigInteger createRandomInRange = BigIntegers.createRandomInRange(ZERO, modulus.subtract(ONE), this.rnd);
            return new SecretWithEncapsulationImpl(generateKey(this.kdf, modulus, createRandomInRange, this.keyLen), BigIntegers.asUnsignedByteArray((modulus.bitLength() + 7) / 8, createRandomInRange.modPow(exponent, modulus)));
        }
        throw new IllegalArgumentException("public key required for encryption");
    }
}
