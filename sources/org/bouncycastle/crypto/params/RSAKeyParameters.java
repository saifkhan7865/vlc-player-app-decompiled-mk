package org.bouncycastle.crypto.params;

import java.math.BigInteger;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.math.Primes;
import org.bouncycastle.util.BigIntegers;
import org.bouncycastle.util.Properties;

public class RSAKeyParameters extends AsymmetricKeyParameter {
    private static final BigInteger ONE = BigInteger.valueOf(1);
    private static final BigInteger SMALL_PRIMES_PRODUCT = new BigInteger("8138e8a0fcf3a4e84a771d40fd305d7f4aa59306d7251de54d98af8fe95729a1f73d893fa424cd2edc8636a6c3285e022b0e3866a565ae8108eed8591cd4fe8d2ce86165a978d719ebf647f362d33fca29cd179fb42401cbaf3df0c614056f9c8f3cfd51e474afb6bc6974f78db8aba8e9e517fded658591ab7502bd41849462f", 16);
    private static final BigIntegers.Cache validated = new BigIntegers.Cache();
    private BigInteger exponent;
    private BigInteger modulus;

    public RSAKeyParameters(boolean z, BigInteger bigInteger, BigInteger bigInteger2) {
        this(z, bigInteger, bigInteger2, false);
    }

    public RSAKeyParameters(boolean z, BigInteger bigInteger, BigInteger bigInteger2, boolean z2) {
        super(z);
        if (z || (bigInteger2.intValue() & 1) != 0) {
            this.modulus = !validated.contains(bigInteger) ? validate(bigInteger, z2) : bigInteger;
            this.exponent = bigInteger2;
            return;
        }
        throw new IllegalArgumentException("RSA publicExponent is even");
    }

    private static int getMRIterations(int i) {
        if (i >= 1536) {
            return 3;
        }
        if (i >= 1024) {
            return 4;
        }
        return i >= 512 ? 7 : 50;
    }

    private BigInteger validate(BigInteger bigInteger, boolean z) {
        if (!z) {
            if ((bigInteger.intValue() & 1) == 0) {
                throw new IllegalArgumentException("RSA modulus is even");
            } else if (Properties.isOverrideSet("org.bouncycastle.rsa.allow_unsafe_mod")) {
                return bigInteger;
            } else {
                if (Properties.asInteger("org.bouncycastle.rsa.max_size", 15360) < bigInteger.bitLength()) {
                    throw new IllegalArgumentException("modulus value out of range");
                } else if (bigInteger.gcd(SMALL_PRIMES_PRODUCT).equals(ONE)) {
                    int asInteger = Properties.asInteger("org.bouncycastle.rsa.max_mr_tests", getMRIterations(bigInteger.bitLength() / 2));
                    if (asInteger > 0 && !Primes.enhancedMRProbablePrimeTest(bigInteger, CryptoServicesRegistrar.getSecureRandom(), asInteger).isProvablyComposite()) {
                        throw new IllegalArgumentException("RSA modulus is not composite");
                    }
                } else {
                    throw new IllegalArgumentException("RSA modulus has a small prime factor");
                }
            }
        }
        validated.add(bigInteger);
        return bigInteger;
    }

    public BigInteger getExponent() {
        return this.exponent;
    }

    public BigInteger getModulus() {
        return this.modulus;
    }
}
