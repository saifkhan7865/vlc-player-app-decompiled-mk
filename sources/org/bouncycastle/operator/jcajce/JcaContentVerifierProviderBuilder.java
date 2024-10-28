package org.bouncycastle.operator.jcajce;

import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.security.Provider;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
import org.bouncycastle.asn1.ASN1BitString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.misc.MiscObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateHolder;
import org.bouncycastle.jcajce.CompositePublicKey;
import org.bouncycastle.jcajce.io.OutputStreamFactory;
import org.bouncycastle.jcajce.util.DefaultJcaJceHelper;
import org.bouncycastle.jcajce.util.NamedJcaJceHelper;
import org.bouncycastle.jcajce.util.ProviderJcaJceHelper;
import org.bouncycastle.operator.ContentVerifier;
import org.bouncycastle.operator.ContentVerifierProvider;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.RawContentVerifier;
import org.bouncycastle.operator.RuntimeOperatorException;
import org.bouncycastle.util.io.TeeOutputStream;

public class JcaContentVerifierProviderBuilder {
    /* access modifiers changed from: private */
    public OperatorHelper helper = new OperatorHelper(new DefaultJcaJceHelper());

    private static class CompositeVerifier implements ContentVerifier {
        private Signature[] sigs;
        private OutputStream stream;

        public CompositeVerifier(Signature[] signatureArr) throws OperatorCreationException {
            this.sigs = signatureArr;
            int i = 0;
            while (i < signatureArr.length && signatureArr[i] == null) {
                i++;
            }
            if (i != signatureArr.length) {
                OutputStream createStream = OutputStreamFactory.createStream(signatureArr[i]);
                while (true) {
                    this.stream = createStream;
                    do {
                        i++;
                        if (i == signatureArr.length) {
                            return;
                        }
                    } while (signatureArr[i] == null);
                    createStream = new TeeOutputStream(this.stream, OutputStreamFactory.createStream(signatureArr[i]));
                }
            } else {
                throw new OperatorCreationException("no matching signature found in composite");
            }
        }

        public AlgorithmIdentifier getAlgorithmIdentifier() {
            return new AlgorithmIdentifier(MiscObjectIdentifiers.id_alg_composite);
        }

        public OutputStream getOutputStream() {
            return this.stream;
        }

        public boolean verify(byte[] bArr) {
            try {
                ASN1Sequence instance = ASN1Sequence.getInstance(bArr);
                boolean z = false;
                for (int i = 0; i != instance.size(); i++) {
                    Signature signature = this.sigs[i];
                    if (signature != null && !signature.verify(ASN1BitString.getInstance(instance.getObjectAt(i)).getBytes())) {
                        z = true;
                    }
                }
                return !z;
            } catch (SignatureException e) {
                throw new RuntimeOperatorException("exception obtaining signature: " + e.getMessage(), e);
            }
        }
    }

    private static class RawSigVerifier extends SigVerifier implements RawContentVerifier {
        private Signature rawSignature;

        RawSigVerifier(AlgorithmIdentifier algorithmIdentifier, Signature signature, Signature signature2) {
            super(algorithmIdentifier, signature);
            this.rawSignature = signature2;
        }

        public boolean verify(byte[] bArr) {
            try {
                return super.verify(bArr);
            } finally {
                try {
                    this.rawSignature.verify(bArr);
                } catch (Exception unused) {
                }
            }
        }

        public boolean verify(byte[] bArr, byte[] bArr2) {
            try {
                this.rawSignature.update(bArr);
                boolean verify = this.rawSignature.verify(bArr2);
                try {
                    this.rawSignature.verify(bArr2);
                } catch (Exception unused) {
                }
                return verify;
            } catch (SignatureException e) {
                throw new RuntimeOperatorException("exception obtaining raw signature: " + e.getMessage(), e);
            } catch (Throwable th) {
                try {
                    this.rawSignature.verify(bArr2);
                } catch (Exception unused2) {
                }
                throw th;
            }
        }
    }

    private static class SigVerifier implements ContentVerifier {
        private final AlgorithmIdentifier algorithm;
        private final Signature signature;
        protected final OutputStream stream;

        SigVerifier(AlgorithmIdentifier algorithmIdentifier, Signature signature2) {
            this.algorithm = algorithmIdentifier;
            this.signature = signature2;
            this.stream = OutputStreamFactory.createStream(signature2);
        }

        public AlgorithmIdentifier getAlgorithmIdentifier() {
            return this.algorithm;
        }

        public OutputStream getOutputStream() {
            OutputStream outputStream = this.stream;
            if (outputStream != null) {
                return outputStream;
            }
            throw new IllegalStateException("verifier not initialised");
        }

        public boolean verify(byte[] bArr) {
            try {
                return this.signature.verify(bArr);
            } catch (SignatureException e) {
                throw new RuntimeOperatorException("exception obtaining signature: " + e.getMessage(), e);
            }
        }
    }

    /* access modifiers changed from: private */
    public ContentVerifier createCompositeVerifier(AlgorithmIdentifier algorithmIdentifier, PublicKey publicKey) throws OperatorCreationException {
        int i = 0;
        if (publicKey instanceof CompositePublicKey) {
            List<PublicKey> publicKeys = ((CompositePublicKey) publicKey).getPublicKeys();
            ASN1Sequence instance = ASN1Sequence.getInstance(algorithmIdentifier.getParameters());
            Signature[] signatureArr = new Signature[instance.size()];
            while (i != instance.size()) {
                AlgorithmIdentifier instance2 = AlgorithmIdentifier.getInstance(instance.getObjectAt(i));
                if (publicKeys.get(i) != null) {
                    signatureArr[i] = createSignature(instance2, publicKeys.get(i));
                } else {
                    signatureArr[i] = null;
                }
                i++;
            }
            return new CompositeVerifier(signatureArr);
        }
        ASN1Sequence instance3 = ASN1Sequence.getInstance(algorithmIdentifier.getParameters());
        Signature[] signatureArr2 = new Signature[instance3.size()];
        while (i != instance3.size()) {
            try {
                signatureArr2[i] = createSignature(AlgorithmIdentifier.getInstance(instance3.getObjectAt(i)), publicKey);
            } catch (Exception unused) {
                signatureArr2[i] = null;
            }
            i++;
        }
        return new CompositeVerifier(signatureArr2);
    }

    /* access modifiers changed from: private */
    public Signature createRawSig(AlgorithmIdentifier algorithmIdentifier, PublicKey publicKey) {
        try {
            Signature createRawSignature = this.helper.createRawSignature(algorithmIdentifier);
            if (createRawSignature == null) {
                return createRawSignature;
            }
            createRawSignature.initVerify(publicKey);
            return createRawSignature;
        } catch (Exception unused) {
            return null;
        }
    }

    /* access modifiers changed from: private */
    public Signature createSignature(AlgorithmIdentifier algorithmIdentifier, PublicKey publicKey) throws OperatorCreationException {
        try {
            Signature createSignature = this.helper.createSignature(algorithmIdentifier);
            createSignature.initVerify(publicKey);
            return createSignature;
        } catch (GeneralSecurityException e) {
            throw new OperatorCreationException("exception on setup: " + e, e);
        }
    }

    public ContentVerifierProvider build(final PublicKey publicKey) throws OperatorCreationException {
        return new ContentVerifierProvider() {
            public ContentVerifier get(AlgorithmIdentifier algorithmIdentifier) throws OperatorCreationException {
                if (algorithmIdentifier.getAlgorithm().equals((ASN1Primitive) MiscObjectIdentifiers.id_alg_composite)) {
                    return JcaContentVerifierProviderBuilder.this.createCompositeVerifier(algorithmIdentifier, publicKey);
                }
                PublicKey publicKey = publicKey;
                if (publicKey instanceof CompositePublicKey) {
                    List<PublicKey> publicKeys = ((CompositePublicKey) publicKey).getPublicKeys();
                    int i = 0;
                    while (i != publicKeys.size()) {
                        try {
                            Signature access$300 = JcaContentVerifierProviderBuilder.this.createSignature(algorithmIdentifier, publicKeys.get(i));
                            Signature access$200 = JcaContentVerifierProviderBuilder.this.createRawSig(algorithmIdentifier, publicKeys.get(i));
                            return access$200 != null ? new RawSigVerifier(algorithmIdentifier, access$300, access$200) : new SigVerifier(algorithmIdentifier, access$300);
                        } catch (OperatorCreationException unused) {
                            i++;
                        }
                    }
                    throw new OperatorCreationException("no matching algorithm found for key");
                }
                Signature access$3002 = JcaContentVerifierProviderBuilder.this.createSignature(algorithmIdentifier, publicKey);
                Signature access$2002 = JcaContentVerifierProviderBuilder.this.createRawSig(algorithmIdentifier, publicKey);
                return access$2002 != null ? new RawSigVerifier(algorithmIdentifier, access$3002, access$2002) : new SigVerifier(algorithmIdentifier, access$3002);
            }

            public X509CertificateHolder getAssociatedCertificate() {
                return null;
            }

            public boolean hasAssociatedCertificate() {
                return false;
            }
        };
    }

    public ContentVerifierProvider build(final X509Certificate x509Certificate) throws OperatorCreationException {
        try {
            final JcaX509CertificateHolder jcaX509CertificateHolder = new JcaX509CertificateHolder(x509Certificate);
            return new ContentVerifierProvider() {
                public ContentVerifier get(AlgorithmIdentifier algorithmIdentifier) throws OperatorCreationException {
                    if (algorithmIdentifier.getAlgorithm().equals((ASN1Primitive) MiscObjectIdentifiers.id_alg_composite)) {
                        return JcaContentVerifierProviderBuilder.this.createCompositeVerifier(algorithmIdentifier, x509Certificate.getPublicKey());
                    }
                    try {
                        Signature createSignature = JcaContentVerifierProviderBuilder.this.helper.createSignature(algorithmIdentifier);
                        createSignature.initVerify(x509Certificate.getPublicKey());
                        Signature access$200 = JcaContentVerifierProviderBuilder.this.createRawSig(algorithmIdentifier, x509Certificate.getPublicKey());
                        return access$200 != null ? new RawSigVerifier(algorithmIdentifier, createSignature, access$200) : new SigVerifier(algorithmIdentifier, createSignature);
                    } catch (GeneralSecurityException e) {
                        throw new OperatorCreationException("exception on setup: " + e, e);
                    }
                }

                public X509CertificateHolder getAssociatedCertificate() {
                    return jcaX509CertificateHolder;
                }

                public boolean hasAssociatedCertificate() {
                    return true;
                }
            };
        } catch (CertificateEncodingException e) {
            throw new OperatorCreationException("cannot process certificate: " + e.getMessage(), e);
        }
    }

    public ContentVerifierProvider build(SubjectPublicKeyInfo subjectPublicKeyInfo) throws OperatorCreationException {
        return build(this.helper.convertPublicKey(subjectPublicKeyInfo));
    }

    public ContentVerifierProvider build(X509CertificateHolder x509CertificateHolder) throws OperatorCreationException, CertificateException {
        return build(this.helper.convertCertificate(x509CertificateHolder));
    }

    public JcaContentVerifierProviderBuilder setProvider(String str) {
        this.helper = new OperatorHelper(new NamedJcaJceHelper(str));
        return this;
    }

    public JcaContentVerifierProviderBuilder setProvider(Provider provider) {
        this.helper = new OperatorHelper(new ProviderJcaJceHelper(provider));
        return this;
    }
}
