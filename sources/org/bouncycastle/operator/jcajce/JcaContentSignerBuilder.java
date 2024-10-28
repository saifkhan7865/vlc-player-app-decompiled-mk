package org.bouncycastle.operator.jcajce;

import java.io.IOException;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.MGF1ParameterSpec;
import java.security.spec.PSSParameterSpec;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Encoding;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.misc.MiscObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.pkcs.RSASSAPSSparams;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.jcajce.CompositePrivateKey;
import org.bouncycastle.jcajce.io.OutputStreamFactory;
import org.bouncycastle.jcajce.spec.CompositeAlgorithmSpec;
import org.bouncycastle.jcajce.util.DefaultJcaJceHelper;
import org.bouncycastle.jcajce.util.NamedJcaJceHelper;
import org.bouncycastle.jcajce.util.ProviderJcaJceHelper;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.DefaultDigestAlgorithmIdentifierFinder;
import org.bouncycastle.operator.DefaultSignatureAlgorithmIdentifierFinder;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.RuntimeOperatorException;
import org.bouncycastle.util.Strings;
import org.bouncycastle.util.io.TeeOutputStream;

public class JcaContentSignerBuilder {
    private static final Set isAlgIdFromPrivate;
    private OperatorHelper helper = new OperatorHelper(new DefaultJcaJceHelper());
    private SecureRandom random;
    /* access modifiers changed from: private */
    public AlgorithmIdentifier sigAlgId;
    private AlgorithmParameterSpec sigAlgSpec;
    private final String signatureAlgorithm;

    static {
        HashSet hashSet = new HashSet();
        isAlgIdFromPrivate = hashSet;
        hashSet.add("DILITHIUM");
        hashSet.add("SPHINCS+");
        hashSet.add("SPHINCSPlus");
    }

    public JcaContentSignerBuilder(String str) {
        this.signatureAlgorithm = str;
    }

    public JcaContentSignerBuilder(String str, AlgorithmParameterSpec algorithmParameterSpec) {
        AlgorithmIdentifier algorithmIdentifier;
        this.signatureAlgorithm = str;
        if (algorithmParameterSpec instanceof PSSParameterSpec) {
            PSSParameterSpec pSSParameterSpec = (PSSParameterSpec) algorithmParameterSpec;
            this.sigAlgSpec = pSSParameterSpec;
            algorithmIdentifier = new AlgorithmIdentifier(PKCSObjectIdentifiers.id_RSASSA_PSS, createPSSParams(pSSParameterSpec));
        } else if (algorithmParameterSpec instanceof CompositeAlgorithmSpec) {
            CompositeAlgorithmSpec compositeAlgorithmSpec = (CompositeAlgorithmSpec) algorithmParameterSpec;
            this.sigAlgSpec = compositeAlgorithmSpec;
            algorithmIdentifier = new AlgorithmIdentifier(MiscObjectIdentifiers.id_alg_composite, createCompParams(compositeAlgorithmSpec));
        } else {
            StringBuilder sb = new StringBuilder("unknown sigParamSpec: ");
            sb.append(algorithmParameterSpec == null ? AbstractJsonLexerKt.NULL : algorithmParameterSpec.getClass().getName());
            throw new IllegalArgumentException(sb.toString());
        }
        this.sigAlgId = algorithmIdentifier;
    }

    private ContentSigner buildComposite(CompositePrivateKey compositePrivateKey) throws OperatorCreationException {
        try {
            List<PrivateKey> privateKeys = compositePrivateKey.getPrivateKeys();
            ASN1Sequence instance = ASN1Sequence.getInstance(this.sigAlgId.getParameters());
            int size = instance.size();
            Signature[] signatureArr = new Signature[size];
            for (int i = 0; i != instance.size(); i++) {
                Signature createSignature = this.helper.createSignature(AlgorithmIdentifier.getInstance(instance.getObjectAt(i)));
                signatureArr[i] = createSignature;
                if (this.random != null) {
                    createSignature.initSign(privateKeys.get(i), this.random);
                } else {
                    createSignature.initSign(privateKeys.get(i));
                }
            }
            OutputStream createStream = OutputStreamFactory.createStream(signatureArr[0]);
            int i2 = 1;
            while (i2 != size) {
                i2++;
                createStream = new TeeOutputStream(createStream, OutputStreamFactory.createStream(signatureArr[i2]));
            }
            return new ContentSigner(createStream, signatureArr) {
                OutputStream stream;
                final /* synthetic */ OutputStream val$sigStream;
                final /* synthetic */ Signature[] val$sigs;

                {
                    this.val$sigStream = r2;
                    this.val$sigs = r3;
                    this.stream = r2;
                }

                public AlgorithmIdentifier getAlgorithmIdentifier() {
                    return JcaContentSignerBuilder.this.sigAlgId;
                }

                public OutputStream getOutputStream() {
                    return this.stream;
                }

                public byte[] getSignature() {
                    try {
                        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
                        for (int i = 0; i != this.val$sigs.length; i++) {
                            aSN1EncodableVector.add(new DERBitString(this.val$sigs[i].sign()));
                        }
                        return new DERSequence(aSN1EncodableVector).getEncoded(ASN1Encoding.DER);
                    } catch (IOException e) {
                        throw new RuntimeOperatorException("exception encoding signature: " + e.getMessage(), e);
                    } catch (SignatureException e2) {
                        throw new RuntimeOperatorException("exception obtaining signature: " + e2.getMessage(), e2);
                    }
                }
            };
        } catch (GeneralSecurityException e) {
            throw new OperatorCreationException("cannot create signer: " + e.getMessage(), e);
        }
    }

    private static ASN1Sequence createCompParams(CompositeAlgorithmSpec compositeAlgorithmSpec) {
        ASN1Encodable createPSSParams;
        DefaultSignatureAlgorithmIdentifierFinder defaultSignatureAlgorithmIdentifierFinder = new DefaultSignatureAlgorithmIdentifierFinder();
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        List<String> algorithmNames = compositeAlgorithmSpec.getAlgorithmNames();
        List<AlgorithmParameterSpec> parameterSpecs = compositeAlgorithmSpec.getParameterSpecs();
        for (int i = 0; i != algorithmNames.size(); i++) {
            AlgorithmParameterSpec algorithmParameterSpec = parameterSpecs.get(i);
            if (algorithmParameterSpec == null) {
                createPSSParams = defaultSignatureAlgorithmIdentifierFinder.find(algorithmNames.get(i));
            } else if (algorithmParameterSpec instanceof PSSParameterSpec) {
                createPSSParams = createPSSParams((PSSParameterSpec) algorithmParameterSpec);
            } else {
                throw new IllegalArgumentException("unrecognized parameterSpec");
            }
            aSN1EncodableVector.add(createPSSParams);
        }
        return new DERSequence(aSN1EncodableVector);
    }

    private static RSASSAPSSparams createPSSParams(PSSParameterSpec pSSParameterSpec) {
        DefaultDigestAlgorithmIdentifierFinder defaultDigestAlgorithmIdentifierFinder = new DefaultDigestAlgorithmIdentifierFinder();
        AlgorithmIdentifier find = defaultDigestAlgorithmIdentifierFinder.find(pSSParameterSpec.getDigestAlgorithm());
        if (find.getParameters() == null) {
            find = new AlgorithmIdentifier(find.getAlgorithm(), DERNull.INSTANCE);
        }
        AlgorithmIdentifier find2 = defaultDigestAlgorithmIdentifierFinder.find(((MGF1ParameterSpec) pSSParameterSpec.getMGFParameters()).getDigestAlgorithm());
        if (find2.getParameters() == null) {
            find2 = new AlgorithmIdentifier(find2.getAlgorithm(), DERNull.INSTANCE);
        }
        return new RSASSAPSSparams(find, new AlgorithmIdentifier(PKCSObjectIdentifiers.id_mgf1, find2), new ASN1Integer((long) pSSParameterSpec.getSaltLength()), new ASN1Integer((long) pSSParameterSpec.getTrailerField()));
    }

    public ContentSigner build(PrivateKey privateKey) throws OperatorCreationException {
        if (privateKey instanceof CompositePrivateKey) {
            return buildComposite((CompositePrivateKey) privateKey);
        }
        try {
            if (this.sigAlgSpec == null) {
                if (isAlgIdFromPrivate.contains(Strings.toUpperCase(this.signatureAlgorithm))) {
                    this.sigAlgId = PrivateKeyInfo.getInstance(privateKey.getEncoded()).getPrivateKeyAlgorithm();
                } else {
                    this.sigAlgId = new DefaultSignatureAlgorithmIdentifierFinder().find(this.signatureAlgorithm);
                }
                this.sigAlgSpec = null;
            }
            AlgorithmIdentifier algorithmIdentifier = this.sigAlgId;
            Signature createSignature = this.helper.createSignature(algorithmIdentifier);
            SecureRandom secureRandom = this.random;
            if (secureRandom != null) {
                createSignature.initSign(privateKey, secureRandom);
            } else {
                createSignature.initSign(privateKey);
            }
            return new ContentSigner(createSignature, algorithmIdentifier) {
                private OutputStream stream;
                final /* synthetic */ Signature val$sig;
                final /* synthetic */ AlgorithmIdentifier val$signatureAlgId;

                {
                    this.val$sig = r2;
                    this.val$signatureAlgId = r3;
                    this.stream = OutputStreamFactory.createStream(r2);
                }

                public AlgorithmIdentifier getAlgorithmIdentifier() {
                    return this.val$signatureAlgId;
                }

                public OutputStream getOutputStream() {
                    return this.stream;
                }

                public byte[] getSignature() {
                    try {
                        return this.val$sig.sign();
                    } catch (SignatureException e) {
                        throw new RuntimeOperatorException("exception obtaining signature: " + e.getMessage(), e);
                    }
                }
            };
        } catch (GeneralSecurityException e) {
            throw new OperatorCreationException("cannot create signer: " + e.getMessage(), e);
        }
    }

    public JcaContentSignerBuilder setProvider(String str) {
        this.helper = new OperatorHelper(new NamedJcaJceHelper(str));
        return this;
    }

    public JcaContentSignerBuilder setProvider(Provider provider) {
        this.helper = new OperatorHelper(new ProviderJcaJceHelper(provider));
        return this;
    }

    public JcaContentSignerBuilder setSecureRandom(SecureRandom secureRandom) {
        this.random = secureRandom;
        return this;
    }
}
