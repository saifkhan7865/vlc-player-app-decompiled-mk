package org.bouncycastle.jcajce.provider.asymmetric.edec;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import org.bouncycastle.crypto.DerivationFunction;
import org.bouncycastle.crypto.RawAgreement;
import org.bouncycastle.crypto.agreement.X25519Agreement;
import org.bouncycastle.crypto.agreement.X448Agreement;
import org.bouncycastle.crypto.agreement.XDHUnifiedAgreement;
import org.bouncycastle.crypto.agreement.kdf.ConcatenationKDFGenerator;
import org.bouncycastle.crypto.generators.KDF2BytesGenerator;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.X25519PrivateKeyParameters;
import org.bouncycastle.crypto.params.X448PrivateKeyParameters;
import org.bouncycastle.crypto.params.XDHUPrivateParameters;
import org.bouncycastle.crypto.params.XDHUPublicParameters;
import org.bouncycastle.crypto.util.DigestFactory;
import org.bouncycastle.jcajce.provider.asymmetric.util.BaseAgreementSpi;
import org.bouncycastle.jcajce.spec.DHUParameterSpec;
import org.bouncycastle.jcajce.spec.UserKeyingMaterialSpec;
import org.bouncycastle.jcajce.spec.XDHParameterSpec;
import org.bouncycastle.util.Properties;

public class KeyAgreementSpi extends BaseAgreementSpi {
    private RawAgreement agreement;
    private DHUParameterSpec dhuSpec;
    private byte[] result;

    public static final class X25519 extends KeyAgreementSpi {
        public X25519() {
            super(XDHParameterSpec.X25519);
        }
    }

    public static class X25519UwithSHA256CKDF extends KeyAgreementSpi {
        public X25519UwithSHA256CKDF() {
            super("X25519UwithSHA256CKDF", new ConcatenationKDFGenerator(DigestFactory.createSHA256()));
        }
    }

    public static class X25519UwithSHA256KDF extends KeyAgreementSpi {
        public X25519UwithSHA256KDF() {
            super("X25519UwithSHA256KDF", new KDF2BytesGenerator(DigestFactory.createSHA256()));
        }
    }

    public static final class X25519withSHA256CKDF extends KeyAgreementSpi {
        public X25519withSHA256CKDF() {
            super("X25519withSHA256CKDF", new ConcatenationKDFGenerator(DigestFactory.createSHA256()));
        }
    }

    public static final class X25519withSHA256KDF extends KeyAgreementSpi {
        public X25519withSHA256KDF() {
            super("X25519withSHA256KDF", new KDF2BytesGenerator(DigestFactory.createSHA256()));
        }
    }

    public static class X25519withSHA384CKDF extends KeyAgreementSpi {
        public X25519withSHA384CKDF() {
            super("X25519withSHA384CKDF", new ConcatenationKDFGenerator(DigestFactory.createSHA384()));
        }
    }

    public static class X25519withSHA512CKDF extends KeyAgreementSpi {
        public X25519withSHA512CKDF() {
            super("X25519withSHA512CKDF", new ConcatenationKDFGenerator(DigestFactory.createSHA512()));
        }
    }

    public static final class X448 extends KeyAgreementSpi {
        public X448() {
            super(XDHParameterSpec.X448);
        }
    }

    public static class X448UwithSHA512CKDF extends KeyAgreementSpi {
        public X448UwithSHA512CKDF() {
            super("X448UwithSHA512CKDF", new ConcatenationKDFGenerator(DigestFactory.createSHA512()));
        }
    }

    public static class X448UwithSHA512KDF extends KeyAgreementSpi {
        public X448UwithSHA512KDF() {
            super("X448UwithSHA512KDF", new KDF2BytesGenerator(DigestFactory.createSHA512()));
        }
    }

    public static final class X448withSHA256CKDF extends KeyAgreementSpi {
        public X448withSHA256CKDF() {
            super("X448withSHA256CKDF", new ConcatenationKDFGenerator(DigestFactory.createSHA256()));
        }
    }

    public static class X448withSHA384CKDF extends KeyAgreementSpi {
        public X448withSHA384CKDF() {
            super("X448withSHA384CKDF", new ConcatenationKDFGenerator(DigestFactory.createSHA384()));
        }
    }

    public static final class X448withSHA512CKDF extends KeyAgreementSpi {
        public X448withSHA512CKDF() {
            super("X448withSHA512CKDF", new ConcatenationKDFGenerator(DigestFactory.createSHA512()));
        }
    }

    public static final class X448withSHA512KDF extends KeyAgreementSpi {
        public X448withSHA512KDF() {
            super("X448withSHA512KDF", new KDF2BytesGenerator(DigestFactory.createSHA512()));
        }
    }

    public static final class XDH extends KeyAgreementSpi {
        public XDH() {
            super("XDH");
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    KeyAgreementSpi(String str) {
        super(Properties.isOverrideSet(Properties.EMULATE_ORACLE) ? "XDH" : str, (DerivationFunction) null);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    KeyAgreementSpi(String str, DerivationFunction derivationFunction) {
        super(Properties.isOverrideSet(Properties.EMULATE_ORACLE) ? "XDH" : str, derivationFunction);
    }

    private RawAgreement getAgreement(String str) throws InvalidKeyException {
        if (this.kaAlgorithm.equals("XDH") || this.kaAlgorithm.startsWith(str)) {
            int indexOf = this.kaAlgorithm.indexOf(85);
            boolean startsWith = str.startsWith(XDHParameterSpec.X448);
            return indexOf > 0 ? startsWith ? new XDHUnifiedAgreement(new X448Agreement()) : new XDHUnifiedAgreement(new X25519Agreement()) : startsWith ? new X448Agreement() : new X25519Agreement();
        }
        throw new InvalidKeyException("inappropriate key for " + this.kaAlgorithm);
    }

    /* access modifiers changed from: protected */
    public byte[] doCalcSecret() {
        return this.result;
    }

    /* access modifiers changed from: protected */
    public void doInitFromKey(Key key, AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidKeyException, InvalidAlgorithmParameterException {
        String str;
        if (key instanceof PrivateKey) {
            AsymmetricKeyParameter generatePrivateKeyParameter = EdECUtil.generatePrivateKeyParameter((PrivateKey) key);
            if (generatePrivateKeyParameter instanceof X25519PrivateKeyParameters) {
                str = XDHParameterSpec.X25519;
            } else if (generatePrivateKeyParameter instanceof X448PrivateKeyParameters) {
                str = XDHParameterSpec.X448;
            } else {
                throw new InvalidKeyException("unsupported private key type");
            }
            this.agreement = getAgreement(str);
            this.ukmParameters = null;
            if (algorithmParameterSpec instanceof DHUParameterSpec) {
                if (this.kaAlgorithm.indexOf(85) >= 0) {
                    DHUParameterSpec dHUParameterSpec = (DHUParameterSpec) algorithmParameterSpec;
                    this.dhuSpec = dHUParameterSpec;
                    this.ukmParameters = dHUParameterSpec.getUserKeyingMaterial();
                    this.agreement.init(new XDHUPrivateParameters(generatePrivateKeyParameter, ((BCXDHPrivateKey) this.dhuSpec.getEphemeralPrivateKey()).engineGetKeyParameters(), ((BCXDHPublicKey) this.dhuSpec.getEphemeralPublicKey()).engineGetKeyParameters()));
                } else {
                    throw new InvalidAlgorithmParameterException("agreement algorithm not DHU based");
                }
            } else if (algorithmParameterSpec != null) {
                this.agreement.init(generatePrivateKeyParameter);
                if (!(algorithmParameterSpec instanceof UserKeyingMaterialSpec)) {
                    throw new InvalidAlgorithmParameterException("unknown ParameterSpec");
                } else if (this.kdf != null) {
                    this.ukmParameters = ((UserKeyingMaterialSpec) algorithmParameterSpec).getUserKeyingMaterial();
                } else {
                    throw new InvalidAlgorithmParameterException("no KDF specified for UserKeyingMaterialSpec");
                }
            } else {
                this.agreement.init(generatePrivateKeyParameter);
            }
            if (this.kdf != null && this.ukmParameters == null) {
                this.ukmParameters = new byte[0];
                return;
            }
            return;
        }
        throw new InvalidKeyException("private XDH key required");
    }

    /* access modifiers changed from: protected */
    public Key engineDoPhase(Key key, boolean z) throws InvalidKeyException, IllegalStateException {
        if (!(key instanceof PublicKey)) {
            throw new InvalidKeyException("public XDH key required");
        } else if (this.agreement == null) {
            throw new IllegalStateException(this.kaAlgorithm + " not initialised.");
        } else if (z) {
            AsymmetricKeyParameter generatePublicKeyParameter = EdECUtil.generatePublicKeyParameter((PublicKey) key);
            byte[] bArr = new byte[this.agreement.getAgreementSize()];
            this.result = bArr;
            DHUParameterSpec dHUParameterSpec = this.dhuSpec;
            if (dHUParameterSpec != null) {
                this.agreement.calculateAgreement(new XDHUPublicParameters(generatePublicKeyParameter, ((BCXDHPublicKey) dHUParameterSpec.getOtherPartyEphemeralKey()).engineGetKeyParameters()), this.result, 0);
                return null;
            }
            this.agreement.calculateAgreement(generatePublicKeyParameter, bArr, 0);
            return null;
        } else {
            throw new IllegalStateException(this.kaAlgorithm + " can only be between two parties.");
        }
    }
}
