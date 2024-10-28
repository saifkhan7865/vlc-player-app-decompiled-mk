package org.bouncycastle.pqc.jcajce.provider.falcon;

import java.io.ByteArrayOutputStream;
import java.security.InvalidKeyException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.AlgorithmParameterSpec;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.pqc.crypto.falcon.FalconParameters;
import org.bouncycastle.pqc.crypto.falcon.FalconPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.falcon.FalconSigner;
import org.bouncycastle.util.Strings;

public class SignatureSpi extends Signature {
    private ByteArrayOutputStream bOut = new ByteArrayOutputStream();
    private FalconParameters parameters;
    private SecureRandom random;
    private FalconSigner signer;

    public static class Base extends SignatureSpi {
        public Base() {
            super(new FalconSigner());
        }
    }

    public static class Falcon1024 extends SignatureSpi {
        public Falcon1024() {
            super(new FalconSigner(), FalconParameters.falcon_1024);
        }
    }

    public static class Falcon512 extends SignatureSpi {
        public Falcon512() {
            super(new FalconSigner(), FalconParameters.falcon_512);
        }
    }

    protected SignatureSpi(FalconSigner falconSigner) {
        super("FALCON");
        this.signer = falconSigner;
        this.parameters = null;
    }

    protected SignatureSpi(FalconSigner falconSigner, FalconParameters falconParameters) {
        super(Strings.toUpperCase(falconParameters.getName()));
        this.parameters = falconParameters;
        this.signer = falconSigner;
    }

    /* access modifiers changed from: protected */
    public Object engineGetParameter(String str) {
        throw new UnsupportedOperationException("engineSetParameter unsupported");
    }

    /* access modifiers changed from: protected */
    public void engineInitSign(PrivateKey privateKey) throws InvalidKeyException {
        if (privateKey instanceof BCFalconPrivateKey) {
            BCFalconPrivateKey bCFalconPrivateKey = (BCFalconPrivateKey) privateKey;
            FalconPrivateKeyParameters keyParams = bCFalconPrivateKey.getKeyParams();
            FalconParameters falconParameters = this.parameters;
            if (falconParameters != null) {
                String upperCase = Strings.toUpperCase(falconParameters.getName());
                if (!upperCase.equals(bCFalconPrivateKey.getAlgorithm())) {
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
            throw new InvalidKeyException("unknown private key passed to Falcon");
        }
    }

    /* access modifiers changed from: protected */
    public void engineInitSign(PrivateKey privateKey, SecureRandom secureRandom) throws InvalidKeyException {
        this.random = secureRandom;
        engineInitSign(privateKey);
    }

    /* access modifiers changed from: protected */
    public void engineInitVerify(PublicKey publicKey) throws InvalidKeyException {
        if (!(publicKey instanceof BCFalconPublicKey)) {
            try {
                publicKey = new BCFalconPublicKey(SubjectPublicKeyInfo.getInstance(publicKey.getEncoded()));
            } catch (Exception e) {
                throw new InvalidKeyException("unknown public key passed to Falcon: " + e.getMessage(), e);
            }
        }
        BCFalconPublicKey bCFalconPublicKey = (BCFalconPublicKey) publicKey;
        FalconParameters falconParameters = this.parameters;
        if (falconParameters != null) {
            String upperCase = Strings.toUpperCase(falconParameters.getName());
            if (!upperCase.equals(bCFalconPublicKey.getAlgorithm())) {
                throw new InvalidKeyException("signature configured for " + upperCase);
            }
        }
        this.signer.init(false, bCFalconPublicKey.getKeyParams());
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
