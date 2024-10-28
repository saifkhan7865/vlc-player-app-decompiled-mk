package org.bouncycastle.its.jcajce;

import java.security.PrivateKey;
import java.security.Provider;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import org.bouncycastle.its.operator.ETSIDataDecryptor;
import org.bouncycastle.jcajce.spec.IESKEMParameterSpec;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.jcajce.util.NamedJcaJceHelper;
import org.bouncycastle.jcajce.util.ProviderJcaJceHelper;
import org.bouncycastle.util.Arrays;

public class JcaETSIDataDecryptor implements ETSIDataDecryptor {
    private final JcaJceHelper helper;
    private final PrivateKey privateKey;
    private final byte[] recipientHash;
    private SecretKey secretKey = null;

    public static class Builder {
        private final PrivateKey key;
        private JcaJceHelper provider;
        private final byte[] recipientHash;

        public Builder(PrivateKey privateKey, byte[] bArr) {
            this.key = privateKey;
            this.recipientHash = Arrays.clone(bArr);
        }

        public JcaETSIDataDecryptor build() {
            return new JcaETSIDataDecryptor(this.key, this.recipientHash, this.provider);
        }

        public Builder provider(String str) {
            this.provider = new NamedJcaJceHelper(str);
            return this;
        }

        public Builder provider(Provider provider2) {
            this.provider = new ProviderJcaJceHelper(provider2);
            return this;
        }
    }

    JcaETSIDataDecryptor(PrivateKey privateKey2, byte[] bArr, JcaJceHelper jcaJceHelper) {
        this.privateKey = privateKey2;
        this.helper = jcaJceHelper;
        this.recipientHash = bArr;
    }

    public static Builder builder(PrivateKey privateKey2, byte[] bArr) {
        return new Builder(privateKey2, bArr);
    }

    public byte[] decrypt(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        try {
            Cipher createCipher = this.helper.createCipher("ETSIKEMwithSHA256");
            createCipher.init(4, this.privateKey, new IESKEMParameterSpec(this.recipientHash));
            this.secretKey = (SecretKey) createCipher.unwrap(bArr, "AES", 3);
            Cipher createCipher2 = this.helper.createCipher("CCM");
            createCipher2.init(2, this.secretKey, ClassUtil.getGCMSpec(bArr3, 128));
            return createCipher2.doFinal(bArr2);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public byte[] getKey() {
        SecretKey secretKey2 = this.secretKey;
        if (secretKey2 != null) {
            return secretKey2.getEncoded();
        }
        throw new IllegalStateException("no secret key recovered");
    }
}
