package org.bouncycastle.crypto.engines;

import java.math.BigInteger;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoServicePurpose;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.constraints.ConstraintUtils;
import org.bouncycastle.crypto.constraints.DefaultServiceProperties;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.crypto.params.RSAKeyParameters;
import org.bouncycastle.util.Arrays;

class RSACoreEngine {
    private boolean forEncryption;
    private RSAKeyParameters key;

    RSACoreEngine() {
    }

    private CryptoServicePurpose getPurpose(boolean z, boolean z2) {
        boolean z3 = true;
        boolean z4 = z && z2;
        boolean z5 = !z && z2;
        if (z || z2) {
            z3 = false;
        }
        return z4 ? CryptoServicePurpose.SIGNING : z5 ? CryptoServicePurpose.ENCRYPTION : z3 ? CryptoServicePurpose.VERIFYING : CryptoServicePurpose.DECRYPTION;
    }

    public BigInteger convertInput(byte[] bArr, int i, int i2) {
        if (i2 > getInputBlockSize() + 1) {
            throw new DataLengthException("input too large for RSA cipher.");
        } else if (i2 != getInputBlockSize() + 1 || this.forEncryption) {
            if (!(i == 0 && i2 == bArr.length)) {
                byte[] bArr2 = new byte[i2];
                System.arraycopy(bArr, i, bArr2, 0, i2);
                bArr = bArr2;
            }
            BigInteger bigInteger = new BigInteger(1, bArr);
            if (bigInteger.compareTo(this.key.getModulus()) < 0) {
                return bigInteger;
            }
            throw new DataLengthException("input too large for RSA cipher.");
        } else {
            throw new DataLengthException("input too large for RSA cipher.");
        }
    }

    public byte[] convertOutput(BigInteger bigInteger) {
        byte[] bArr;
        byte[] byteArray = bigInteger.toByteArray();
        if (!this.forEncryption) {
            if (byteArray[0] == 0) {
                int length = byteArray.length - 1;
                bArr = new byte[length];
                System.arraycopy(byteArray, 1, bArr, 0, length);
            } else {
                int length2 = byteArray.length;
                bArr = new byte[length2];
                System.arraycopy(byteArray, 0, bArr, 0, length2);
            }
            Arrays.fill(byteArray, (byte) 0);
            return bArr;
        } else if (byteArray[0] == 0 && byteArray.length > getOutputBlockSize()) {
            int length3 = byteArray.length - 1;
            byte[] bArr2 = new byte[length3];
            System.arraycopy(byteArray, 1, bArr2, 0, length3);
            return bArr2;
        } else if (byteArray.length >= getOutputBlockSize()) {
            return byteArray;
        } else {
            int outputBlockSize = getOutputBlockSize();
            byte[] bArr3 = new byte[outputBlockSize];
            System.arraycopy(byteArray, 0, bArr3, outputBlockSize - byteArray.length, byteArray.length);
            return bArr3;
        }
    }

    public int getInputBlockSize() {
        int bitLength = (this.key.getModulus().bitLength() + 7) / 8;
        return this.forEncryption ? bitLength - 1 : bitLength;
    }

    public int getOutputBlockSize() {
        int bitLength = (this.key.getModulus().bitLength() + 7) / 8;
        return this.forEncryption ? bitLength : bitLength - 1;
    }

    public void init(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof ParametersWithRandom) {
            cipherParameters = ((ParametersWithRandom) cipherParameters).getParameters();
        }
        this.key = (RSAKeyParameters) cipherParameters;
        this.forEncryption = z;
        int bitsOfSecurityFor = ConstraintUtils.bitsOfSecurityFor(this.key.getModulus());
        RSAKeyParameters rSAKeyParameters = this.key;
        CryptoServicesRegistrar.checkConstraints(new DefaultServiceProperties("RSA", bitsOfSecurityFor, rSAKeyParameters, getPurpose(rSAKeyParameters.isPrivate(), z)));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0006, code lost:
        r0 = (org.bouncycastle.crypto.params.RSAPrivateCrtKeyParameters) r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.math.BigInteger processBlock(java.math.BigInteger r9) {
        /*
            r8 = this;
            org.bouncycastle.crypto.params.RSAKeyParameters r0 = r8.key
            boolean r1 = r0 instanceof org.bouncycastle.crypto.params.RSAPrivateCrtKeyParameters
            if (r1 == 0) goto L_0x005d
            org.bouncycastle.crypto.params.RSAPrivateCrtKeyParameters r0 = (org.bouncycastle.crypto.params.RSAPrivateCrtKeyParameters) r0
            java.math.BigInteger r1 = r0.getPublicExponent()
            if (r1 == 0) goto L_0x005d
            java.math.BigInteger r2 = r0.getP()
            java.math.BigInteger r3 = r0.getQ()
            java.math.BigInteger r4 = r0.getDP()
            java.math.BigInteger r5 = r0.getDQ()
            java.math.BigInteger r6 = r0.getQInv()
            java.math.BigInteger r7 = r9.remainder(r2)
            java.math.BigInteger r4 = r7.modPow(r4, r2)
            java.math.BigInteger r7 = r9.remainder(r3)
            java.math.BigInteger r5 = r7.modPow(r5, r3)
            java.math.BigInteger r4 = r4.subtract(r5)
            java.math.BigInteger r4 = r4.multiply(r6)
            java.math.BigInteger r2 = r4.mod(r2)
            java.math.BigInteger r2 = r2.multiply(r3)
            java.math.BigInteger r2 = r2.add(r5)
            java.math.BigInteger r0 = r0.getModulus()
            java.math.BigInteger r0 = r2.modPow(r1, r0)
            boolean r9 = r0.equals(r9)
            if (r9 == 0) goto L_0x0055
            return r2
        L_0x0055:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "RSA engine faulty decryption/signing detected"
            r9.<init>(r0)
            throw r9
        L_0x005d:
            org.bouncycastle.crypto.params.RSAKeyParameters r0 = r8.key
            java.math.BigInteger r0 = r0.getExponent()
            org.bouncycastle.crypto.params.RSAKeyParameters r1 = r8.key
            java.math.BigInteger r1 = r1.getModulus()
            java.math.BigInteger r9 = r9.modPow(r0, r1)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.crypto.engines.RSACoreEngine.processBlock(java.math.BigInteger):java.math.BigInteger");
    }
}
