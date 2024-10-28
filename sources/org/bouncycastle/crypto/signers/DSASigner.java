package org.bouncycastle.crypto.signers;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.DSAExt;
import org.bouncycastle.crypto.params.DSAKeyParameters;
import org.bouncycastle.crypto.params.DSAParameters;
import org.bouncycastle.crypto.params.DSAPrivateKeyParameters;
import org.bouncycastle.crypto.params.DSAPublicKeyParameters;
import org.bouncycastle.util.BigIntegers;

public class DSASigner implements DSAExt {
    private final DSAKCalculator kCalculator;
    private DSAKeyParameters key;
    private SecureRandom random;

    public DSASigner() {
        this.kCalculator = new RandomDSAKCalculator();
    }

    public DSASigner(DSAKCalculator dSAKCalculator) {
        this.kCalculator = dSAKCalculator;
    }

    private BigInteger calculateE(BigInteger bigInteger, byte[] bArr) {
        if (bigInteger.bitLength() >= bArr.length * 8) {
            return new BigInteger(1, bArr);
        }
        int bitLength = bigInteger.bitLength() / 8;
        byte[] bArr2 = new byte[bitLength];
        System.arraycopy(bArr, 0, bArr2, 0, bitLength);
        return new BigInteger(1, bArr2);
    }

    private BigInteger getRandomizer(BigInteger bigInteger, SecureRandom secureRandom) {
        return BigIntegers.createRandomBigInteger(7, CryptoServicesRegistrar.getSecureRandom(secureRandom)).add(BigInteger.valueOf(128)).multiply(bigInteger);
    }

    public BigInteger[] generateSignature(byte[] bArr) {
        DSAParameters parameters = this.key.getParameters();
        BigInteger q = parameters.getQ();
        BigInteger calculateE = calculateE(q, bArr);
        BigInteger x = ((DSAPrivateKeyParameters) this.key).getX();
        if (this.kCalculator.isDeterministic()) {
            this.kCalculator.init(q, x, bArr);
        } else {
            this.kCalculator.init(q, this.random);
        }
        BigInteger nextK = this.kCalculator.nextK();
        BigInteger mod = parameters.getG().modPow(nextK.add(getRandomizer(q, this.random)), parameters.getP()).mod(q);
        return new BigInteger[]{mod, BigIntegers.modOddInverse(q, nextK).multiply(calculateE.add(x.multiply(mod))).mod(q)};
    }

    public BigInteger getOrder() {
        return this.key.getParameters().getQ();
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0032  */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0034  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void init(boolean r3, org.bouncycastle.crypto.CipherParameters r4) {
        /*
            r2 = this;
            if (r3 == 0) goto L_0x0018
            boolean r0 = r4 instanceof org.bouncycastle.crypto.params.ParametersWithRandom
            if (r0 == 0) goto L_0x0015
            org.bouncycastle.crypto.params.ParametersWithRandom r4 = (org.bouncycastle.crypto.params.ParametersWithRandom) r4
            org.bouncycastle.crypto.CipherParameters r0 = r4.getParameters()
            org.bouncycastle.crypto.params.DSAPrivateKeyParameters r0 = (org.bouncycastle.crypto.params.DSAPrivateKeyParameters) r0
            r2.key = r0
            java.security.SecureRandom r4 = r4.getRandom()
            goto L_0x001d
        L_0x0015:
            org.bouncycastle.crypto.params.DSAPrivateKeyParameters r4 = (org.bouncycastle.crypto.params.DSAPrivateKeyParameters) r4
            goto L_0x001a
        L_0x0018:
            org.bouncycastle.crypto.params.DSAPublicKeyParameters r4 = (org.bouncycastle.crypto.params.DSAPublicKeyParameters) r4
        L_0x001a:
            r2.key = r4
            r4 = 0
        L_0x001d:
            java.lang.String r0 = "DSA"
            org.bouncycastle.crypto.params.DSAKeyParameters r1 = r2.key
            org.bouncycastle.crypto.CryptoServiceProperties r0 = org.bouncycastle.crypto.signers.Utils.getDefaultProperties((java.lang.String) r0, (org.bouncycastle.crypto.params.DSAKeyParameters) r1, (boolean) r3)
            org.bouncycastle.crypto.CryptoServicesRegistrar.checkConstraints(r0)
            if (r3 == 0) goto L_0x0034
            org.bouncycastle.crypto.signers.DSAKCalculator r3 = r2.kCalculator
            boolean r3 = r3.isDeterministic()
            if (r3 != 0) goto L_0x0034
            r3 = 1
            goto L_0x0035
        L_0x0034:
            r3 = 0
        L_0x0035:
            java.security.SecureRandom r3 = r2.initSecureRandom(r3, r4)
            r2.random = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.crypto.signers.DSASigner.init(boolean, org.bouncycastle.crypto.CipherParameters):void");
    }

    /* access modifiers changed from: protected */
    public SecureRandom initSecureRandom(boolean z, SecureRandom secureRandom) {
        if (z) {
            return CryptoServicesRegistrar.getSecureRandom(secureRandom);
        }
        return null;
    }

    public boolean verifySignature(byte[] bArr, BigInteger bigInteger, BigInteger bigInteger2) {
        DSAParameters parameters = this.key.getParameters();
        BigInteger q = parameters.getQ();
        BigInteger calculateE = calculateE(q, bArr);
        BigInteger valueOf = BigInteger.valueOf(0);
        if (valueOf.compareTo(bigInteger) >= 0 || q.compareTo(bigInteger) <= 0 || valueOf.compareTo(bigInteger2) >= 0 || q.compareTo(bigInteger2) <= 0) {
            return false;
        }
        BigInteger modOddInverseVar = BigIntegers.modOddInverseVar(q, bigInteger2);
        BigInteger mod = calculateE.multiply(modOddInverseVar).mod(q);
        BigInteger mod2 = bigInteger.multiply(modOddInverseVar).mod(q);
        BigInteger p = parameters.getP();
        return parameters.getG().modPow(mod, p).multiply(((DSAPublicKeyParameters) this.key).getY().modPow(mod2, p)).mod(p).mod(q).equals(bigInteger);
    }
}
