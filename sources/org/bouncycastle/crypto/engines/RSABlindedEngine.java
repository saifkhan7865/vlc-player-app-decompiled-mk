package org.bouncycastle.crypto.engines;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.crypto.AsymmetricBlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.crypto.params.RSAKeyParameters;
import org.bouncycastle.crypto.params.RSAPrivateCrtKeyParameters;

public class RSABlindedEngine implements AsymmetricBlockCipher {
    private static final BigInteger ONE = BigInteger.valueOf(1);
    private RSACoreEngine core = new RSACoreEngine();
    private RSAKeyParameters key;
    private SecureRandom random;

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0006, code lost:
        r0 = (org.bouncycastle.crypto.params.RSAPrivateCrtKeyParameters) r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.math.BigInteger processInput(java.math.BigInteger r6) {
        /*
            r5 = this;
            org.bouncycastle.crypto.params.RSAKeyParameters r0 = r5.key
            boolean r1 = r0 instanceof org.bouncycastle.crypto.params.RSAPrivateCrtKeyParameters
            if (r1 == 0) goto L_0x003d
            org.bouncycastle.crypto.params.RSAPrivateCrtKeyParameters r0 = (org.bouncycastle.crypto.params.RSAPrivateCrtKeyParameters) r0
            java.math.BigInteger r1 = r0.getPublicExponent()
            if (r1 == 0) goto L_0x003d
            java.math.BigInteger r0 = r0.getModulus()
            java.math.BigInteger r2 = ONE
            java.math.BigInteger r3 = r0.subtract(r2)
            java.security.SecureRandom r4 = r5.random
            java.math.BigInteger r2 = org.bouncycastle.util.BigIntegers.createRandomInRange(r2, r3, r4)
            java.math.BigInteger r1 = r2.modPow(r1, r0)
            java.math.BigInteger r2 = org.bouncycastle.util.BigIntegers.modOddInverse(r0, r2)
            java.math.BigInteger r6 = r1.multiply(r6)
            java.math.BigInteger r6 = r6.mod(r0)
            org.bouncycastle.crypto.engines.RSACoreEngine r1 = r5.core
            java.math.BigInteger r6 = r1.processBlock(r6)
            java.math.BigInteger r6 = r2.multiply(r6)
            java.math.BigInteger r6 = r6.mod(r0)
            return r6
        L_0x003d:
            org.bouncycastle.crypto.engines.RSACoreEngine r0 = r5.core
            java.math.BigInteger r6 = r0.processBlock(r6)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.crypto.engines.RSABlindedEngine.processInput(java.math.BigInteger):java.math.BigInteger");
    }

    public int getInputBlockSize() {
        return this.core.getInputBlockSize();
    }

    public int getOutputBlockSize() {
        return this.core.getOutputBlockSize();
    }

    public void init(boolean z, CipherParameters cipherParameters) {
        SecureRandom secureRandom;
        this.core.init(z, cipherParameters);
        if (cipherParameters instanceof ParametersWithRandom) {
            ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
            RSAKeyParameters rSAKeyParameters = (RSAKeyParameters) parametersWithRandom.getParameters();
            this.key = rSAKeyParameters;
            if (rSAKeyParameters instanceof RSAPrivateCrtKeyParameters) {
                secureRandom = parametersWithRandom.getRandom();
            }
            this.random = null;
            return;
        }
        RSAKeyParameters rSAKeyParameters2 = (RSAKeyParameters) cipherParameters;
        this.key = rSAKeyParameters2;
        if (rSAKeyParameters2 instanceof RSAPrivateCrtKeyParameters) {
            secureRandom = CryptoServicesRegistrar.getSecureRandom();
        }
        this.random = null;
        return;
        this.random = secureRandom;
    }

    public byte[] processBlock(byte[] bArr, int i, int i2) {
        if (this.key != null) {
            return this.core.convertOutput(processInput(this.core.convertInput(bArr, i, i2)));
        }
        throw new IllegalStateException("RSA engine not initialised");
    }
}
