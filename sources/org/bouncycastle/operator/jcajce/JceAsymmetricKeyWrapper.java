package org.bouncycastle.operator.jcajce;

import java.security.AlgorithmParameters;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Provider;
import java.security.ProviderException;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.security.interfaces.ECPublicKey;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidParameterSpecException;
import java.security.spec.MGF1ParameterSpec;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.SecretKey;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
import org.bouncycastle.asn1.cryptopro.Gost2814789EncryptedKey;
import org.bouncycastle.asn1.cryptopro.GostR3410KeyTransport;
import org.bouncycastle.asn1.cryptopro.GostR3410TransportParameters;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.RSAESOAEPparams;
import org.bouncycastle.asn1.rosstandart.RosstandartObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.jcajce.spec.GOST28147WrapParameterSpec;
import org.bouncycastle.jcajce.spec.UserKeyingMaterialSpec;
import org.bouncycastle.jcajce.util.DefaultJcaJceHelper;
import org.bouncycastle.jcajce.util.NamedJcaJceHelper;
import org.bouncycastle.jcajce.util.ProviderJcaJceHelper;
import org.bouncycastle.operator.AsymmetricKeyWrapper;
import org.bouncycastle.operator.GenericKey;
import org.bouncycastle.operator.OperatorException;
import org.bouncycastle.pqc.crypto.sphincs.SPHINCSKeyParameters;
import org.bouncycastle.pqc.jcajce.spec.McElieceCCA2KeyGenParameterSpec;
import org.bouncycastle.util.Arrays;

public class JceAsymmetricKeyWrapper extends AsymmetricKeyWrapper {
    private static final Map digests;
    private static final Set gostAlgs;
    private Map extraMappings;
    private OperatorHelper helper;
    private PublicKey publicKey;
    private SecureRandom random;

    static {
        HashSet hashSet = new HashSet();
        gostAlgs = hashSet;
        hashSet.add(CryptoProObjectIdentifiers.gostR3410_2001_CryptoPro_ESDH);
        hashSet.add(CryptoProObjectIdentifiers.gostR3410_2001);
        hashSet.add(RosstandartObjectIdentifiers.id_tc26_agreement_gost_3410_12_256);
        hashSet.add(RosstandartObjectIdentifiers.id_tc26_agreement_gost_3410_12_512);
        hashSet.add(RosstandartObjectIdentifiers.id_tc26_gost_3410_12_256);
        hashSet.add(RosstandartObjectIdentifiers.id_tc26_gost_3410_12_512);
        HashMap hashMap = new HashMap();
        digests = hashMap;
        hashMap.put("SHA1", new AlgorithmIdentifier(OIWObjectIdentifiers.idSHA1, DERNull.INSTANCE));
        hashMap.put(McElieceCCA2KeyGenParameterSpec.SHA1, new AlgorithmIdentifier(OIWObjectIdentifiers.idSHA1, DERNull.INSTANCE));
        hashMap.put("SHA224", new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha224, DERNull.INSTANCE));
        hashMap.put(McElieceCCA2KeyGenParameterSpec.SHA224, new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha224, DERNull.INSTANCE));
        hashMap.put("SHA256", new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha256, DERNull.INSTANCE));
        hashMap.put("SHA-256", new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha256, DERNull.INSTANCE));
        hashMap.put("SHA384", new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha384, DERNull.INSTANCE));
        hashMap.put(McElieceCCA2KeyGenParameterSpec.SHA384, new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha384, DERNull.INSTANCE));
        hashMap.put("SHA512", new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha512, DERNull.INSTANCE));
        hashMap.put("SHA-512", new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha512, DERNull.INSTANCE));
        hashMap.put("SHA512/224", new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha512_224, DERNull.INSTANCE));
        hashMap.put("SHA-512/224", new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha512_224, DERNull.INSTANCE));
        hashMap.put("SHA-512(224)", new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha512_224, DERNull.INSTANCE));
        hashMap.put("SHA512/256", new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha512_256, DERNull.INSTANCE));
        hashMap.put(SPHINCSKeyParameters.SHA512_256, new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha512_256, DERNull.INSTANCE));
        hashMap.put("SHA-512(256)", new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha512_256, DERNull.INSTANCE));
    }

    public JceAsymmetricKeyWrapper(AlgorithmParameters algorithmParameters, PublicKey publicKey2) throws InvalidParameterSpecException {
        super(extractAlgID(publicKey2, algorithmParameters.getParameterSpec(AlgorithmParameterSpec.class)));
        this.helper = new OperatorHelper(new DefaultJcaJceHelper());
        this.extraMappings = new HashMap();
        this.publicKey = publicKey2;
    }

    public JceAsymmetricKeyWrapper(PublicKey publicKey2) {
        super(SubjectPublicKeyInfo.getInstance(publicKey2.getEncoded()).getAlgorithm());
        this.helper = new OperatorHelper(new DefaultJcaJceHelper());
        this.extraMappings = new HashMap();
        this.publicKey = publicKey2;
    }

    public JceAsymmetricKeyWrapper(X509Certificate x509Certificate) {
        this(x509Certificate.getPublicKey());
    }

    public JceAsymmetricKeyWrapper(AlgorithmParameterSpec algorithmParameterSpec, PublicKey publicKey2) {
        super(extractAlgID(publicKey2, algorithmParameterSpec));
        this.helper = new OperatorHelper(new DefaultJcaJceHelper());
        this.extraMappings = new HashMap();
        this.publicKey = publicKey2;
    }

    public JceAsymmetricKeyWrapper(AlgorithmIdentifier algorithmIdentifier, PublicKey publicKey2) {
        super(algorithmIdentifier);
        this.helper = new OperatorHelper(new DefaultJcaJceHelper());
        this.extraMappings = new HashMap();
        this.publicKey = publicKey2;
    }

    private static AlgorithmIdentifier extractAlgID(PublicKey publicKey2, AlgorithmParameterSpec algorithmParameterSpec) {
        if (algorithmParameterSpec instanceof OAEPParameterSpec) {
            OAEPParameterSpec oAEPParameterSpec = (OAEPParameterSpec) algorithmParameterSpec;
            if (!oAEPParameterSpec.getMGFAlgorithm().equals(OAEPParameterSpec.DEFAULT.getMGFAlgorithm())) {
                throw new IllegalArgumentException("unknown MGF: " + oAEPParameterSpec.getMGFAlgorithm());
            } else if (oAEPParameterSpec.getPSource() instanceof PSource.PSpecified) {
                return new AlgorithmIdentifier(PKCSObjectIdentifiers.id_RSAES_OAEP, new RSAESOAEPparams(getDigest(oAEPParameterSpec.getDigestAlgorithm()), new AlgorithmIdentifier(PKCSObjectIdentifiers.id_mgf1, getDigest(((MGF1ParameterSpec) oAEPParameterSpec.getMGFParameters()).getDigestAlgorithm())), new AlgorithmIdentifier(PKCSObjectIdentifiers.id_pSpecified, new DEROctetString(((PSource.PSpecified) oAEPParameterSpec.getPSource()).getValue()))));
            } else {
                throw new IllegalArgumentException("unknown PSource: " + oAEPParameterSpec.getPSource().getAlgorithm());
            }
        } else {
            throw new IllegalArgumentException("unknown spec: " + algorithmParameterSpec.getClass().getName());
        }
    }

    private static AlgorithmIdentifier getDigest(String str) {
        AlgorithmIdentifier algorithmIdentifier = (AlgorithmIdentifier) digests.get(str);
        if (algorithmIdentifier != null) {
            return algorithmIdentifier;
        }
        throw new IllegalArgumentException("unknown digest name: " + str);
    }

    static boolean isGOST(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return gostAlgs.contains(aSN1ObjectIdentifier);
    }

    public byte[] generateWrappedKey(GenericKey genericKey) throws OperatorException {
        AlgorithmParameters algorithmParameters;
        if (isGOST(getAlgorithmIdentifier().getAlgorithm())) {
            try {
                this.random = CryptoServicesRegistrar.getSecureRandom(this.random);
                KeyPairGenerator createKeyPairGenerator = this.helper.createKeyPairGenerator(getAlgorithmIdentifier().getAlgorithm());
                createKeyPairGenerator.initialize(((ECPublicKey) this.publicKey).getParams(), this.random);
                KeyPair generateKeyPair = createKeyPairGenerator.generateKeyPair();
                byte[] bArr = new byte[8];
                this.random.nextBytes(bArr);
                SubjectPublicKeyInfo instance = SubjectPublicKeyInfo.getInstance(generateKeyPair.getPublic().getEncoded());
                GostR3410TransportParameters gostR3410TransportParameters = instance.getAlgorithm().getAlgorithm().on(RosstandartObjectIdentifiers.id_tc26) ? new GostR3410TransportParameters(RosstandartObjectIdentifiers.id_tc26_gost_28147_param_Z, instance, bArr) : new GostR3410TransportParameters(CryptoProObjectIdentifiers.id_Gost28147_89_CryptoPro_A_ParamSet, instance, bArr);
                KeyAgreement createKeyAgreement = this.helper.createKeyAgreement(getAlgorithmIdentifier().getAlgorithm());
                createKeyAgreement.init(generateKeyPair.getPrivate(), new UserKeyingMaterialSpec(gostR3410TransportParameters.getUkm()));
                createKeyAgreement.doPhase(this.publicKey, true);
                SecretKey generateSecret = createKeyAgreement.generateSecret(CryptoProObjectIdentifiers.id_Gost28147_89_CryptoPro_KeyWrap.getId());
                byte[] encoded = OperatorUtils.getJceKey(genericKey).getEncoded();
                Cipher createCipher = this.helper.createCipher(CryptoProObjectIdentifiers.id_Gost28147_89_CryptoPro_KeyWrap);
                createCipher.init(3, generateSecret, new GOST28147WrapParameterSpec(gostR3410TransportParameters.getEncryptionParamSet(), gostR3410TransportParameters.getUkm()));
                byte[] wrap = createCipher.wrap(new SecretKeySpec(encoded, "GOST"));
                return new GostR3410KeyTransport(new Gost2814789EncryptedKey(Arrays.copyOfRange(wrap, 0, 32), Arrays.copyOfRange(wrap, 32, 36)), gostR3410TransportParameters).getEncoded();
            } catch (Exception e) {
                throw new OperatorException("exception wrapping key: " + e.getMessage(), e);
            }
        } else {
            Cipher createAsymmetricWrapper = this.helper.createAsymmetricWrapper(getAlgorithmIdentifier().getAlgorithm(), this.extraMappings);
            byte[] bArr2 = null;
            try {
                algorithmParameters = !getAlgorithmIdentifier().getAlgorithm().equals((ASN1Primitive) OIWObjectIdentifiers.elGamalAlgorithm) ? this.helper.createAlgorithmParameters(getAlgorithmIdentifier()) : null;
                if (algorithmParameters != null) {
                    try {
                        createAsymmetricWrapper.init(3, this.publicKey, algorithmParameters, this.random);
                    } catch (IllegalStateException | UnsupportedOperationException | GeneralSecurityException | InvalidKeyException | ProviderException unused) {
                    }
                } else {
                    createAsymmetricWrapper.init(3, this.publicKey, this.random);
                }
                bArr2 = createAsymmetricWrapper.wrap(OperatorUtils.getJceKey(genericKey));
            } catch (IllegalStateException | UnsupportedOperationException | GeneralSecurityException | InvalidKeyException | ProviderException unused2) {
                algorithmParameters = null;
            }
            if (bArr2 != null) {
                return bArr2;
            }
            if (algorithmParameters != null) {
                try {
                    createAsymmetricWrapper.init(1, this.publicKey, algorithmParameters, this.random);
                } catch (InvalidKeyException e2) {
                    throw new OperatorException("unable to encrypt contents key", e2);
                } catch (GeneralSecurityException e3) {
                    throw new OperatorException("unable to encrypt contents key", e3);
                }
            } else {
                createAsymmetricWrapper.init(1, this.publicKey, this.random);
            }
            return createAsymmetricWrapper.doFinal(OperatorUtils.getJceKey(genericKey).getEncoded());
        }
    }

    public JceAsymmetricKeyWrapper setAlgorithmMapping(ASN1ObjectIdentifier aSN1ObjectIdentifier, String str) {
        this.extraMappings.put(aSN1ObjectIdentifier, str);
        return this;
    }

    public JceAsymmetricKeyWrapper setProvider(String str) {
        this.helper = new OperatorHelper(new NamedJcaJceHelper(str));
        return this;
    }

    public JceAsymmetricKeyWrapper setProvider(Provider provider) {
        this.helper = new OperatorHelper(new ProviderJcaJceHelper(provider));
        return this;
    }

    public JceAsymmetricKeyWrapper setSecureRandom(SecureRandom secureRandom) {
        this.random = secureRandom;
        return this;
    }
}
