package org.bouncycastle.its.jcajce;

import java.security.Provider;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.its.operator.ETSIDataEncryptor;
import org.bouncycastle.jcajce.util.DefaultJcaJceHelper;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.jcajce.util.NamedJcaJceHelper;
import org.bouncycastle.jcajce.util.ProviderJcaJceHelper;

public class JceETSIDataEncryptor implements ETSIDataEncryptor {
    private final JcaJceHelper helper;
    private byte[] key;
    private byte[] nonce;
    private final SecureRandom random;

    public static class Builder {
        private JcaJceHelper helper = new DefaultJcaJceHelper();
        private SecureRandom random;

        public JceETSIDataEncryptor build() {
            if (this.random == null) {
                this.random = new SecureRandom();
            }
            return new JceETSIDataEncryptor(this.random, this.helper);
        }

        public Builder setProvider(String str) {
            this.helper = new NamedJcaJceHelper(str);
            return this;
        }

        public Builder setProvider(Provider provider) {
            this.helper = new ProviderJcaJceHelper(provider);
            return this;
        }

        public Builder setRandom(SecureRandom secureRandom) {
            this.random = secureRandom;
            return this;
        }
    }

    private JceETSIDataEncryptor(SecureRandom secureRandom, JcaJceHelper jcaJceHelper) {
        this.random = secureRandom;
        this.helper = jcaJceHelper;
    }

    public byte[] encrypt(byte[] bArr) {
        byte[] bArr2 = new byte[16];
        this.key = bArr2;
        this.random.nextBytes(bArr2);
        byte[] bArr3 = new byte[12];
        this.nonce = bArr3;
        this.random.nextBytes(bArr3);
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(this.key, "AES");
            Cipher createCipher = this.helper.createCipher("CCM");
            createCipher.init(1, secretKeySpec, ClassUtil.getGCMSpec(this.nonce, 128));
            return createCipher.doFinal(bArr);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public byte[] getKey() {
        return this.key;
    }

    public byte[] getNonce() {
        return this.nonce;
    }
}
