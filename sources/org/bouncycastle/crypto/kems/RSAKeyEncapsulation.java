package org.bouncycastle.crypto.kems;

import java.security.SecureRandom;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoServicePurpose;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.DerivationFunction;
import org.bouncycastle.crypto.KeyEncapsulation;
import org.bouncycastle.crypto.SecretWithEncapsulation;
import org.bouncycastle.crypto.constraints.ConstraintUtils;
import org.bouncycastle.crypto.constraints.DefaultServiceProperties;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.RSAKeyParameters;
import org.bouncycastle.util.Arrays;

public class RSAKeyEncapsulation implements KeyEncapsulation {
    private DerivationFunction kdf;
    private RSAKeyParameters key;
    private SecureRandom rnd;

    public RSAKeyEncapsulation(DerivationFunction derivationFunction, SecureRandom secureRandom) {
        this.kdf = derivationFunction;
        this.rnd = secureRandom;
    }

    public CipherParameters decrypt(byte[] bArr, int i) {
        return decrypt(bArr, 0, bArr.length, i);
    }

    public CipherParameters decrypt(byte[] bArr, int i, int i2, int i3) throws IllegalArgumentException {
        if (this.key.isPrivate()) {
            return new KeyParameter(new RSAKEMExtractor(this.key, i3, this.kdf).extractSecret(Arrays.copyOfRange(bArr, i, i2 + i)));
        }
        throw new IllegalArgumentException("Private key required for decryption");
    }

    public CipherParameters encrypt(byte[] bArr, int i) {
        return encrypt(bArr, 0, i);
    }

    public CipherParameters encrypt(byte[] bArr, int i, int i2) throws IllegalArgumentException {
        if (!this.key.isPrivate()) {
            SecretWithEncapsulation generateEncapsulated = new RSAKEMGenerator(i2, this.kdf, this.rnd).generateEncapsulated(this.key);
            byte[] encapsulation = generateEncapsulated.getEncapsulation();
            System.arraycopy(encapsulation, 0, bArr, i, encapsulation.length);
            return new KeyParameter(generateEncapsulated.getSecret());
        }
        throw new IllegalArgumentException("Public key required for encryption");
    }

    public void init(CipherParameters cipherParameters) throws IllegalArgumentException {
        if (cipherParameters instanceof RSAKeyParameters) {
            RSAKeyParameters rSAKeyParameters = (RSAKeyParameters) cipherParameters;
            this.key = rSAKeyParameters;
            CryptoServicesRegistrar.checkConstraints(new DefaultServiceProperties("RSAKem", ConstraintUtils.bitsOfSecurityFor(rSAKeyParameters.getModulus()), cipherParameters, this.key.isPrivate() ? CryptoServicePurpose.DECRYPTION : CryptoServicePurpose.ENCRYPTION));
            return;
        }
        throw new IllegalArgumentException("RSA key required");
    }
}
