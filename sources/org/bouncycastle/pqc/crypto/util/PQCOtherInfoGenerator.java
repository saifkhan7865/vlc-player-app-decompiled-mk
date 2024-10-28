package org.bouncycastle.pqc.crypto.util;

import java.io.IOException;
import java.security.SecureRandom;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.EncapsulatedSecretExtractor;
import org.bouncycastle.crypto.EncapsulatedSecretGenerator;
import org.bouncycastle.crypto.SecretWithEncapsulation;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.util.DEROtherInfo;
import org.bouncycastle.pqc.crypto.KEMParameters;
import org.bouncycastle.pqc.crypto.crystals.kyber.KyberKEMExtractor;
import org.bouncycastle.pqc.crypto.crystals.kyber.KyberKEMGenerator;
import org.bouncycastle.pqc.crypto.crystals.kyber.KyberKeyGenerationParameters;
import org.bouncycastle.pqc.crypto.crystals.kyber.KyberKeyPairGenerator;
import org.bouncycastle.pqc.crypto.crystals.kyber.KyberParameters;
import org.bouncycastle.pqc.crypto.crystals.kyber.KyberPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.ntru.NTRUKEMExtractor;
import org.bouncycastle.pqc.crypto.ntru.NTRUKEMGenerator;
import org.bouncycastle.pqc.crypto.ntru.NTRUKeyGenerationParameters;
import org.bouncycastle.pqc.crypto.ntru.NTRUKeyPairGenerator;
import org.bouncycastle.pqc.crypto.ntru.NTRUParameters;
import org.bouncycastle.pqc.crypto.ntru.NTRUPrivateKeyParameters;

public class PQCOtherInfoGenerator {
    protected final DEROtherInfo.Builder otherInfoBuilder;
    protected final SecureRandom random;
    protected boolean used = false;

    public static class PartyU extends PQCOtherInfoGenerator {
        private AsymmetricCipherKeyPair aKp;
        private EncapsulatedSecretExtractor encSE;

        public PartyU(KEMParameters kEMParameters, AlgorithmIdentifier algorithmIdentifier, byte[] bArr, byte[] bArr2, SecureRandom secureRandom) {
            super(algorithmIdentifier, bArr, bArr2, secureRandom);
            EncapsulatedSecretExtractor nTRUKEMExtractor;
            if (kEMParameters instanceof KyberParameters) {
                KyberKeyPairGenerator kyberKeyPairGenerator = new KyberKeyPairGenerator();
                kyberKeyPairGenerator.init(new KyberKeyGenerationParameters(secureRandom, (KyberParameters) kEMParameters));
                AsymmetricCipherKeyPair generateKeyPair = kyberKeyPairGenerator.generateKeyPair();
                this.aKp = generateKeyPair;
                nTRUKEMExtractor = new KyberKEMExtractor((KyberPrivateKeyParameters) generateKeyPair.getPrivate());
            } else if (kEMParameters instanceof NTRUParameters) {
                NTRUKeyPairGenerator nTRUKeyPairGenerator = new NTRUKeyPairGenerator();
                nTRUKeyPairGenerator.init(new NTRUKeyGenerationParameters(secureRandom, (NTRUParameters) kEMParameters));
                AsymmetricCipherKeyPair generateKeyPair2 = nTRUKeyPairGenerator.generateKeyPair();
                this.aKp = generateKeyPair2;
                nTRUKEMExtractor = new NTRUKEMExtractor((NTRUPrivateKeyParameters) generateKeyPair2.getPrivate());
            } else {
                throw new IllegalArgumentException("unknown KEMParameters");
            }
            this.encSE = nTRUKEMExtractor;
        }

        public DEROtherInfo generate(byte[] bArr) {
            this.otherInfoBuilder.withSuppPrivInfo(this.encSE.extractSecret(bArr));
            return this.otherInfoBuilder.build();
        }

        public byte[] getSuppPrivInfoPartA() {
            return PQCOtherInfoGenerator.getEncoded(this.aKp.getPublic());
        }

        public PQCOtherInfoGenerator withSuppPubInfo(byte[] bArr) {
            this.otherInfoBuilder.withSuppPubInfo(bArr);
            return this;
        }
    }

    public static class PartyV extends PQCOtherInfoGenerator {
        private EncapsulatedSecretGenerator encSG;

        public PartyV(KEMParameters kEMParameters, AlgorithmIdentifier algorithmIdentifier, byte[] bArr, byte[] bArr2, SecureRandom secureRandom) {
            super(algorithmIdentifier, bArr, bArr2, secureRandom);
            EncapsulatedSecretGenerator nTRUKEMGenerator;
            if (kEMParameters instanceof KyberParameters) {
                nTRUKEMGenerator = new KyberKEMGenerator(secureRandom);
            } else if (kEMParameters instanceof NTRUParameters) {
                nTRUKEMGenerator = new NTRUKEMGenerator(secureRandom);
            } else {
                throw new IllegalArgumentException("unknown KEMParameters");
            }
            this.encSG = nTRUKEMGenerator;
        }

        public DEROtherInfo generate() {
            if (!this.used) {
                this.used = true;
                return this.otherInfoBuilder.build();
            }
            throw new IllegalStateException("builder already used");
        }

        public byte[] getSuppPrivInfoPartB(byte[] bArr) {
            this.used = false;
            try {
                SecretWithEncapsulation generateEncapsulated = this.encSG.generateEncapsulated(PQCOtherInfoGenerator.getPublicKey(bArr));
                this.otherInfoBuilder.withSuppPrivInfo(generateEncapsulated.getSecret());
                return generateEncapsulated.getEncapsulation();
            } catch (IOException unused) {
                throw new IllegalArgumentException("cannot decode public key");
            }
        }

        public PQCOtherInfoGenerator withSuppPubInfo(byte[] bArr) {
            this.otherInfoBuilder.withSuppPubInfo(bArr);
            return this;
        }
    }

    public PQCOtherInfoGenerator(AlgorithmIdentifier algorithmIdentifier, byte[] bArr, byte[] bArr2, SecureRandom secureRandom) {
        this.otherInfoBuilder = new DEROtherInfo.Builder(algorithmIdentifier, bArr, bArr2);
        this.random = secureRandom;
    }

    /* access modifiers changed from: private */
    public static byte[] getEncoded(AsymmetricKeyParameter asymmetricKeyParameter) {
        try {
            return SubjectPublicKeyInfoFactory.createSubjectPublicKeyInfo(asymmetricKeyParameter).getEncoded();
        } catch (IOException unused) {
            return null;
        }
    }

    /* access modifiers changed from: private */
    public static AsymmetricKeyParameter getPublicKey(byte[] bArr) throws IOException {
        return PublicKeyFactory.createKey(bArr);
    }
}
