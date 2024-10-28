package org.bouncycastle.pqc.jcajce.provider.dilithium;

import java.io.ByteArrayOutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.AlgorithmParameterSpec;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.pqc.crypto.crystals.dilithium.DilithiumParameters;
import org.bouncycastle.pqc.crypto.crystals.dilithium.DilithiumPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.crystals.dilithium.DilithiumSigner;
import org.bouncycastle.util.Strings;

public class SignatureSpi extends Signature {
    private ByteArrayOutputStream bOut = new ByteArrayOutputStream();
    private DilithiumParameters parameters;
    private SecureRandom random;
    private DilithiumSigner signer;

    public static class Base extends SignatureSpi {
        public Base() {
            super(new DilithiumSigner());
        }
    }

    public static class Base2 extends SignatureSpi {
        public Base2() {
            super(new DilithiumSigner(), DilithiumParameters.dilithium2);
        }
    }

    public static class Base3 extends SignatureSpi {
        public Base3() {
            super(new DilithiumSigner(), DilithiumParameters.dilithium3);
        }
    }

    public static class Base5 extends SignatureSpi {
        public Base5() throws NoSuchAlgorithmException {
            super(new DilithiumSigner(), DilithiumParameters.dilithium5);
        }
    }

    protected SignatureSpi(DilithiumSigner dilithiumSigner) {
        super("Dilithium");
        this.signer = dilithiumSigner;
        this.parameters = null;
    }

    protected SignatureSpi(DilithiumSigner dilithiumSigner, DilithiumParameters dilithiumParameters) {
        super(Strings.toUpperCase(dilithiumParameters.getName()));
        this.signer = dilithiumSigner;
        this.parameters = dilithiumParameters;
    }

    /* access modifiers changed from: protected */
    public Object engineGetParameter(String str) {
        throw new UnsupportedOperationException("engineSetParameter unsupported");
    }

    /* access modifiers changed from: protected */
    public void engineInitSign(PrivateKey privateKey) throws InvalidKeyException {
        if (privateKey instanceof BCDilithiumPrivateKey) {
            BCDilithiumPrivateKey bCDilithiumPrivateKey = (BCDilithiumPrivateKey) privateKey;
            DilithiumPrivateKeyParameters keyParams = bCDilithiumPrivateKey.getKeyParams();
            DilithiumParameters dilithiumParameters = this.parameters;
            if (dilithiumParameters != null) {
                String upperCase = Strings.toUpperCase(dilithiumParameters.getName());
                if (!upperCase.equals(bCDilithiumPrivateKey.getAlgorithm())) {
                    throw new InvalidKeyException("signature configured for " + upperCase);
                }
            }
            SecureRandom secureRandom = this.random;
            if (secureRandom != null) {
                this.signer.init(true, new ParametersWithRandom(keyParams, secureRandom));
            } else {
                this.signer.init(true, keyParams);
            }
        } else {
            throw new InvalidKeyException("unknown private key passed to Dilithium");
        }
    }

    /* access modifiers changed from: protected */
    public void engineInitSign(PrivateKey privateKey, SecureRandom secureRandom) throws InvalidKeyException {
        this.random = secureRandom;
        engineInitSign(privateKey);
    }

    /* access modifiers changed from: protected */
    public void engineInitVerify(PublicKey publicKey) throws InvalidKeyException {
        if (!(publicKey instanceof BCDilithiumPublicKey)) {
            try {
                publicKey = new BCDilithiumPublicKey(SubjectPublicKeyInfo.getInstance(publicKey.getEncoded()));
            } catch (Exception e) {
                throw new InvalidKeyException("unknown public key passed to Dilithium: " + e.getMessage(), e);
            }
        }
        BCDilithiumPublicKey bCDilithiumPublicKey = (BCDilithiumPublicKey) publicKey;
        DilithiumParameters dilithiumParameters = this.parameters;
        if (dilithiumParameters != null) {
            String upperCase = Strings.toUpperCase(dilithiumParameters.getName());
            if (!upperCase.equals(bCDilithiumPublicKey.getAlgorithm())) {
                throw new InvalidKeyException("signature configured for " + upperCase);
            }
        }
        this.signer.init(false, bCDilithiumPublicKey.getKeyParams());
    }

    /* access modifiers changed from: protected */
    public void engineSetParameter(String str, Object obj) {
        throw new UnsupportedOperationException("engineSetParameter unsupported");
    }

    /* access modifiers changed from: protected */
    public void engineSetParameter(AlgorithmParameterSpec algorithmParameterSpec) {
        throw new UnsupportedOperationException("engineSetParameter unsupported");
    }

    /* access modifiers changed from: protected */
    public byte[] engineSign() throws SignatureException {
        try {
            byte[] byteArray = this.bOut.toByteArray();
            this.bOut.reset();
            return this.signer.generateSignature(byteArray);
        } catch (Exception e) {
            throw new SignatureException(e.toString());
        }
    }

    /* access modifiers changed from: protected */
    public void engineUpdate(byte b) throws SignatureException {
        this.bOut.write(b);
    }

    /* access modifiers changed from: protected */
    public void engineUpdate(byte[] bArr, int i, int i2) throws SignatureException {
        this.bOut.write(bArr, i, i2);
    }

    /* access modifiers changed from: protected */
    public boolean engineVerify(byte[] bArr) throws SignatureException {
        byte[] byteArray = this.bOut.toByteArray();
        this.bOut.reset();
        return this.signer.verifySignature(byteArray, bArr);
    }
}
