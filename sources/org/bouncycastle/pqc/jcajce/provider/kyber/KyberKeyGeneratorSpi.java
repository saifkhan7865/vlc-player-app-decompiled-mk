package org.bouncycastle.pqc.jcajce.provider.kyber;

import java.security.InvalidAlgorithmParameterException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.KeyGeneratorSpi;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.security.auth.DestroyFailedException;
import org.bouncycastle.crypto.SecretWithEncapsulation;
import org.bouncycastle.jcajce.SecretKeyWithEncapsulation;
import org.bouncycastle.jcajce.spec.KEMExtractSpec;
import org.bouncycastle.jcajce.spec.KEMGenerateSpec;
import org.bouncycastle.pqc.crypto.crystals.kyber.KyberKEMExtractor;
import org.bouncycastle.pqc.crypto.crystals.kyber.KyberKEMGenerator;
import org.bouncycastle.pqc.crypto.crystals.kyber.KyberParameters;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Strings;

public class KyberKeyGeneratorSpi extends KeyGeneratorSpi {
    private KEMExtractSpec extSpec;
    private KEMGenerateSpec genSpec;
    private KyberParameters kyberParameters;
    private SecureRandom random;

    public static class Kyber1024 extends KyberKeyGeneratorSpi {
        public Kyber1024() {
            super(KyberParameters.kyber1024);
        }
    }

    public static class Kyber512 extends KyberKeyGeneratorSpi {
        public Kyber512() {
            super(KyberParameters.kyber512);
        }
    }

    public static class Kyber768 extends KyberKeyGeneratorSpi {
        public Kyber768() {
            super(KyberParameters.kyber768);
        }
    }

    public KyberKeyGeneratorSpi() {
        this((KyberParameters) null);
    }

    protected KyberKeyGeneratorSpi(KyberParameters kyberParameters2) {
        this.kyberParameters = kyberParameters2;
    }

    /* access modifiers changed from: protected */
    public SecretKey engineGenerateKey() {
        KEMGenerateSpec kEMGenerateSpec = this.genSpec;
        if (kEMGenerateSpec != null) {
            SecretWithEncapsulation generateEncapsulated = new KyberKEMGenerator(this.random).generateEncapsulated(((BCKyberPublicKey) kEMGenerateSpec.getPublicKey()).getKeyParams());
            byte[] secret = generateEncapsulated.getSecret();
            byte[] copyOfRange = Arrays.copyOfRange(secret, 0, (this.genSpec.getKeySize() + 7) / 8);
            Arrays.clear(secret);
            SecretKeyWithEncapsulation secretKeyWithEncapsulation = new SecretKeyWithEncapsulation(new SecretKeySpec(copyOfRange, this.genSpec.getKeyAlgorithmName()), generateEncapsulated.getEncapsulation());
            try {
                generateEncapsulated.destroy();
                return secretKeyWithEncapsulation;
            } catch (DestroyFailedException unused) {
                throw new IllegalStateException("key cleanup failed");
            }
        } else {
            KyberKEMExtractor kyberKEMExtractor = new KyberKEMExtractor(((BCKyberPrivateKey) this.extSpec.getPrivateKey()).getKeyParams());
            byte[] encapsulation = this.extSpec.getEncapsulation();
            byte[] extractSecret = kyberKEMExtractor.extractSecret(encapsulation);
            byte[] copyOfRange2 = Arrays.copyOfRange(extractSecret, 0, (this.extSpec.getKeySize() + 7) / 8);
            Arrays.clear(extractSecret);
            SecretKeyWithEncapsulation secretKeyWithEncapsulation2 = new SecretKeyWithEncapsulation(new SecretKeySpec(copyOfRange2, this.extSpec.getKeyAlgorithmName()), encapsulation);
            Arrays.clear(copyOfRange2);
            return secretKeyWithEncapsulation2;
        }
    }

    /* access modifiers changed from: protected */
    public void engineInit(int i, SecureRandom secureRandom) {
        throw new UnsupportedOperationException("Operation not supported");
    }

    /* access modifiers changed from: protected */
    public void engineInit(SecureRandom secureRandom) {
        throw new UnsupportedOperationException("Operation not supported");
    }

    /* access modifiers changed from: protected */
    public void engineInit(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidAlgorithmParameterException {
        this.random = secureRandom;
        if (algorithmParameterSpec instanceof KEMGenerateSpec) {
            this.genSpec = (KEMGenerateSpec) algorithmParameterSpec;
            this.extSpec = null;
            KyberParameters kyberParameters2 = this.kyberParameters;
            if (kyberParameters2 != null) {
                String upperCase = Strings.toUpperCase(kyberParameters2.getName());
                if (!upperCase.equals(this.genSpec.getPublicKey().getAlgorithm())) {
                    throw new InvalidAlgorithmParameterException("key generator locked to " + upperCase);
                }
            }
        } else if (algorithmParameterSpec instanceof KEMExtractSpec) {
            this.genSpec = null;
            this.extSpec = (KEMExtractSpec) algorithmParameterSpec;
            KyberParameters kyberParameters3 = this.kyberParameters;
            if (kyberParameters3 != null) {
                String upperCase2 = Strings.toUpperCase(kyberParameters3.getName());
                if (!upperCase2.equals(this.extSpec.getPrivateKey().getAlgorithm())) {
                    throw new InvalidAlgorithmParameterException("key generator locked to " + upperCase2);
                }
            }
        } else {
            throw new InvalidAlgorithmParameterException("unknown spec");
        }
    }
}
