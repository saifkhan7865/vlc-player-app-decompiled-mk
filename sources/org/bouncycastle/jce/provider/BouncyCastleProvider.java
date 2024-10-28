package org.bouncycastle.jce.provider;

import j$.util.concurrent.ConcurrentHashMap;
import java.io.IOException;
import java.security.AccessController;
import java.security.PrivateKey;
import java.security.PrivilegedAction;
import java.security.Provider;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.bc.BCObjectIdentifiers;
import org.bouncycastle.asn1.isara.IsaraObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.crypto.CryptoServiceConstraintsException;
import org.bouncycastle.crypto.CryptoServiceProperties;
import org.bouncycastle.crypto.CryptoServicePurpose;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;
import org.bouncycastle.jcajce.provider.config.ProviderConfiguration;
import org.bouncycastle.jcajce.provider.symmetric.util.ClassUtil;
import org.bouncycastle.jcajce.provider.util.AlgorithmProvider;
import org.bouncycastle.jcajce.provider.util.AsymmetricKeyInfoConverter;
import org.bouncycastle.pqc.asn1.PQCObjectIdentifiers;
import org.bouncycastle.pqc.jcajce.provider.bike.BIKEKeyFactorySpi;
import org.bouncycastle.pqc.jcajce.provider.cmce.CMCEKeyFactorySpi;
import org.bouncycastle.pqc.jcajce.provider.dilithium.DilithiumKeyFactorySpi;
import org.bouncycastle.pqc.jcajce.provider.falcon.FalconKeyFactorySpi;
import org.bouncycastle.pqc.jcajce.provider.hqc.HQCKeyFactorySpi;
import org.bouncycastle.pqc.jcajce.provider.kyber.KyberKeyFactorySpi;
import org.bouncycastle.pqc.jcajce.provider.lms.LMSKeyFactorySpi;
import org.bouncycastle.pqc.jcajce.provider.newhope.NHKeyFactorySpi;
import org.bouncycastle.pqc.jcajce.provider.ntru.NTRUKeyFactorySpi;
import org.bouncycastle.pqc.jcajce.provider.picnic.PicnicKeyFactorySpi;
import org.bouncycastle.pqc.jcajce.provider.sphincs.Sphincs256KeyFactorySpi;
import org.bouncycastle.pqc.jcajce.provider.sphincsplus.SPHINCSPlusKeyFactorySpi;
import org.bouncycastle.pqc.jcajce.provider.xmss.XMSSKeyFactorySpi;
import org.bouncycastle.pqc.jcajce.provider.xmss.XMSSMTKeyFactorySpi;
import org.bouncycastle.util.Strings;
import org.fusesource.jansi.AnsiRenderer;

public final class BouncyCastleProvider extends Provider implements ConfigurableProvider {
    private static final String[] ASYMMETRIC_CIPHERS = {"DSA", "DH", "EC", "RSA", "GOST", "ECGOST", "ElGamal", "DSTU4145", "GM", "EdEC", "LMS", "SPHINCSPlus", "Dilithium", "Falcon", "NTRU"};
    private static final String[] ASYMMETRIC_GENERIC = {"X509", "IES", "COMPOSITE", "EXTERNAL"};
    private static final String ASYMMETRIC_PACKAGE = "org.bouncycastle.jcajce.provider.asymmetric.";
    public static final ProviderConfiguration CONFIGURATION = new BouncyCastleProviderConfiguration();
    private static final String[] DIGESTS = {"GOST3411", "Keccak", "MD2", "MD4", "MD5", "SHA1", "RIPEMD128", "RIPEMD160", "RIPEMD256", "RIPEMD320", "SHA224", "SHA256", "SHA384", "SHA512", "SHA3", "Skein", "SM3", "Tiger", "Whirlpool", "Blake2b", "Blake2s", "DSTU7564", "Haraka", "Blake3"};
    private static final String DIGEST_PACKAGE = "org.bouncycastle.jcajce.provider.digest.";
    private static final String[] KEYSTORES = {PROVIDER_NAME, "BCFKS", "PKCS12"};
    private static final String KEYSTORE_PACKAGE = "org.bouncycastle.jcajce.provider.keystore.";
    private static final Logger LOG;
    public static final String PROVIDER_NAME = "BC";
    private static final String[] SECURE_RANDOMS = {"DRBG"};
    private static final String SECURE_RANDOM_PACKAGE = "org.bouncycastle.jcajce.provider.drbg.";
    private static final CryptoServiceProperties[] SYMMETRIC_CIPHERS = {service("AES", 256), service("ARC4", 20), service("ARIA", 256), service("Blowfish", 128), service("Camellia", 256), service("CAST5", 128), service("CAST6", 256), service("ChaCha", 128), service("DES", 56), service("DESede", 112), service("GOST28147", 128), service("Grainv1", 128), service("Grain128", 128), service("HC128", 128), service("HC256", 256), service("IDEA", 128), service("Noekeon", 128), service("RC2", 128), service("RC5", 128), service("RC6", 256), service("Rijndael", 256), service("Salsa20", 128), service("SEED", 128), service("Serpent", 256), service("Shacal2", 128), service("Skipjack", 80), service("SM4", 128), service("TEA", 128), service("Twofish", 256), service("Threefish", 128), service("VMPC", 128), service("VMPCKSA3", 128), service("XTEA", 128), service("XSalsa20", 128), service("OpenSSLPBKDF", 128), service("DSTU7624", 256), service("GOST3412_2015", 256), service("Zuc", 128)};
    private static final String[] SYMMETRIC_GENERIC = {"PBEPBKDF1", "PBEPBKDF2", "PBEPKCS12", "TLSKDF", "SCRYPT"};
    private static final String[] SYMMETRIC_MACS = {"SipHash", "SipHash128", "Poly1305"};
    private static final String SYMMETRIC_PACKAGE = "org.bouncycastle.jcajce.provider.symmetric.";
    private static String info = "BouncyCastle Security Provider v1.77";
    private static final Map keyInfoConverters = new HashMap();
    private static final Class revChkClass;
    /* access modifiers changed from: private */
    public Map<String, Provider.Service> serviceMap = new ConcurrentHashMap();

    private static class JcaCryptoService implements CryptoServiceProperties {
        private final int bitsOfSecurity;
        private final String name;

        JcaCryptoService(String str, int i) {
            this.name = str;
            this.bitsOfSecurity = i;
        }

        public int bitsOfSecurity() {
            return this.bitsOfSecurity;
        }

        public Object getParams() {
            return null;
        }

        public CryptoServicePurpose getPurpose() {
            return CryptoServicePurpose.ANY;
        }

        public String getServiceName() {
            return this.name;
        }
    }

    static {
        Class<BouncyCastleProvider> cls = BouncyCastleProvider.class;
        LOG = Logger.getLogger(cls.getName());
        revChkClass = ClassUtil.loadClass(cls, "java.security.cert.PKIXRevocationChecker");
    }

    public BouncyCastleProvider() {
        super(PROVIDER_NAME, 1.77d, info);
        AccessController.doPrivileged(new PrivilegedAction() {
            public Object run() {
                BouncyCastleProvider.this.setup();
                return null;
            }
        });
    }

    private static AsymmetricKeyInfoConverter getAsymmetricKeyInfoConverter(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        AsymmetricKeyInfoConverter asymmetricKeyInfoConverter;
        Map map = keyInfoConverters;
        synchronized (map) {
            asymmetricKeyInfoConverter = (AsymmetricKeyInfoConverter) map.get(aSN1ObjectIdentifier);
        }
        return asymmetricKeyInfoConverter;
    }

    public static PrivateKey getPrivateKey(PrivateKeyInfo privateKeyInfo) throws IOException {
        AsymmetricKeyInfoConverter asymmetricKeyInfoConverter = getAsymmetricKeyInfoConverter(privateKeyInfo.getPrivateKeyAlgorithm().getAlgorithm());
        if (asymmetricKeyInfoConverter == null) {
            return null;
        }
        return asymmetricKeyInfoConverter.generatePrivate(privateKeyInfo);
    }

    public static PublicKey getPublicKey(SubjectPublicKeyInfo subjectPublicKeyInfo) throws IOException {
        if (subjectPublicKeyInfo.getAlgorithm().getAlgorithm().on(BCObjectIdentifiers.picnic_key)) {
            return new PicnicKeyFactorySpi().generatePublic(subjectPublicKeyInfo);
        }
        AsymmetricKeyInfoConverter asymmetricKeyInfoConverter = getAsymmetricKeyInfoConverter(subjectPublicKeyInfo.getAlgorithm().getAlgorithm());
        if (asymmetricKeyInfoConverter == null) {
            return null;
        }
        return asymmetricKeyInfoConverter.generatePublic(subjectPublicKeyInfo);
    }

    private void loadAlgorithms(String str, String[] strArr) {
        for (int i = 0; i != strArr.length; i++) {
            loadServiceClass(str, strArr[i]);
        }
    }

    private void loadAlgorithms(String str, CryptoServiceProperties[] cryptoServicePropertiesArr) {
        for (int i = 0; i != cryptoServicePropertiesArr.length; i++) {
            CryptoServiceProperties cryptoServiceProperties = cryptoServicePropertiesArr[i];
            try {
                CryptoServicesRegistrar.checkConstraints(cryptoServiceProperties);
                loadServiceClass(str, cryptoServiceProperties.getServiceName());
            } catch (CryptoServiceConstraintsException unused) {
                Logger logger = LOG;
                if (logger.isLoggable(Level.FINE)) {
                    logger.fine("service for " + cryptoServiceProperties.getServiceName() + " ignored due to constraints");
                }
            }
        }
    }

    private void loadPQCKeys() {
        addKeyInfoConverter(BCObjectIdentifiers.sphincsPlus, new SPHINCSPlusKeyFactorySpi());
        addKeyInfoConverter(BCObjectIdentifiers.sphincsPlus_sha2_128s_r3, new SPHINCSPlusKeyFactorySpi());
        addKeyInfoConverter(BCObjectIdentifiers.sphincsPlus_sha2_128f_r3, new SPHINCSPlusKeyFactorySpi());
        addKeyInfoConverter(BCObjectIdentifiers.sphincsPlus_shake_128s_r3, new SPHINCSPlusKeyFactorySpi());
        addKeyInfoConverter(BCObjectIdentifiers.sphincsPlus_shake_128f_r3, new SPHINCSPlusKeyFactorySpi());
        addKeyInfoConverter(BCObjectIdentifiers.sphincsPlus_haraka_128s_r3, new SPHINCSPlusKeyFactorySpi());
        addKeyInfoConverter(BCObjectIdentifiers.sphincsPlus_haraka_128f_r3, new SPHINCSPlusKeyFactorySpi());
        addKeyInfoConverter(BCObjectIdentifiers.sphincsPlus_sha2_192s_r3, new SPHINCSPlusKeyFactorySpi());
        addKeyInfoConverter(BCObjectIdentifiers.sphincsPlus_sha2_192f_r3, new SPHINCSPlusKeyFactorySpi());
        addKeyInfoConverter(BCObjectIdentifiers.sphincsPlus_shake_192s_r3, new SPHINCSPlusKeyFactorySpi());
        addKeyInfoConverter(BCObjectIdentifiers.sphincsPlus_shake_192f_r3, new SPHINCSPlusKeyFactorySpi());
        addKeyInfoConverter(BCObjectIdentifiers.sphincsPlus_haraka_192s_r3, new SPHINCSPlusKeyFactorySpi());
        addKeyInfoConverter(BCObjectIdentifiers.sphincsPlus_haraka_192f_r3, new SPHINCSPlusKeyFactorySpi());
        addKeyInfoConverter(BCObjectIdentifiers.sphincsPlus_sha2_256s_r3, new SPHINCSPlusKeyFactorySpi());
        addKeyInfoConverter(BCObjectIdentifiers.sphincsPlus_sha2_256f_r3, new SPHINCSPlusKeyFactorySpi());
        addKeyInfoConverter(BCObjectIdentifiers.sphincsPlus_shake_256s_r3, new SPHINCSPlusKeyFactorySpi());
        addKeyInfoConverter(BCObjectIdentifiers.sphincsPlus_shake_256f_r3, new SPHINCSPlusKeyFactorySpi());
        addKeyInfoConverter(BCObjectIdentifiers.sphincsPlus_haraka_256s_r3, new SPHINCSPlusKeyFactorySpi());
        addKeyInfoConverter(BCObjectIdentifiers.sphincsPlus_haraka_256f_r3, new SPHINCSPlusKeyFactorySpi());
        addKeyInfoConverter(BCObjectIdentifiers.sphincsPlus_sha2_128s_r3_simple, new SPHINCSPlusKeyFactorySpi());
        addKeyInfoConverter(BCObjectIdentifiers.sphincsPlus_sha2_128f_r3_simple, new SPHINCSPlusKeyFactorySpi());
        addKeyInfoConverter(BCObjectIdentifiers.sphincsPlus_shake_128s_r3_simple, new SPHINCSPlusKeyFactorySpi());
        addKeyInfoConverter(BCObjectIdentifiers.sphincsPlus_shake_128f_r3_simple, new SPHINCSPlusKeyFactorySpi());
        addKeyInfoConverter(BCObjectIdentifiers.sphincsPlus_haraka_128s_r3_simple, new SPHINCSPlusKeyFactorySpi());
        addKeyInfoConverter(BCObjectIdentifiers.sphincsPlus_haraka_128f_r3_simple, new SPHINCSPlusKeyFactorySpi());
        addKeyInfoConverter(BCObjectIdentifiers.sphincsPlus_sha2_192s_r3_simple, new SPHINCSPlusKeyFactorySpi());
        addKeyInfoConverter(BCObjectIdentifiers.sphincsPlus_sha2_192f_r3_simple, new SPHINCSPlusKeyFactorySpi());
        addKeyInfoConverter(BCObjectIdentifiers.sphincsPlus_shake_192s_r3_simple, new SPHINCSPlusKeyFactorySpi());
        addKeyInfoConverter(BCObjectIdentifiers.sphincsPlus_shake_192f_r3_simple, new SPHINCSPlusKeyFactorySpi());
        addKeyInfoConverter(BCObjectIdentifiers.sphincsPlus_haraka_192s_r3_simple, new SPHINCSPlusKeyFactorySpi());
        addKeyInfoConverter(BCObjectIdentifiers.sphincsPlus_haraka_192f_r3_simple, new SPHINCSPlusKeyFactorySpi());
        addKeyInfoConverter(BCObjectIdentifiers.sphincsPlus_sha2_256s_r3_simple, new SPHINCSPlusKeyFactorySpi());
        addKeyInfoConverter(BCObjectIdentifiers.sphincsPlus_sha2_256f_r3_simple, new SPHINCSPlusKeyFactorySpi());
        addKeyInfoConverter(BCObjectIdentifiers.sphincsPlus_shake_256s_r3_simple, new SPHINCSPlusKeyFactorySpi());
        addKeyInfoConverter(BCObjectIdentifiers.sphincsPlus_shake_256f_r3_simple, new SPHINCSPlusKeyFactorySpi());
        addKeyInfoConverter(BCObjectIdentifiers.sphincsPlus_haraka_256s_r3_simple, new SPHINCSPlusKeyFactorySpi());
        addKeyInfoConverter(BCObjectIdentifiers.sphincsPlus_haraka_256f_r3_simple, new SPHINCSPlusKeyFactorySpi());
        addKeyInfoConverter(BCObjectIdentifiers.sphincsPlus_sha2_128s, new SPHINCSPlusKeyFactorySpi());
        addKeyInfoConverter(BCObjectIdentifiers.sphincsPlus_sha2_192s, new SPHINCSPlusKeyFactorySpi());
        addKeyInfoConverter(BCObjectIdentifiers.sphincsPlus_sha2_256s, new SPHINCSPlusKeyFactorySpi());
        addKeyInfoConverter(new ASN1ObjectIdentifier("1.3.9999.6.4.10"), new SPHINCSPlusKeyFactorySpi());
        addKeyInfoConverter(BCObjectIdentifiers.sphincsPlus_shake_128f, new SPHINCSPlusKeyFactorySpi());
        addKeyInfoConverter(BCObjectIdentifiers.sphincsPlus_shake_192f, new SPHINCSPlusKeyFactorySpi());
        addKeyInfoConverter(BCObjectIdentifiers.sphincsPlus_shake_256f, new SPHINCSPlusKeyFactorySpi());
        addKeyInfoConverter(PQCObjectIdentifiers.sphincs256, new Sphincs256KeyFactorySpi());
        addKeyInfoConverter(PQCObjectIdentifiers.newHope, new NHKeyFactorySpi());
        addKeyInfoConverter(PQCObjectIdentifiers.xmss, new XMSSKeyFactorySpi());
        addKeyInfoConverter(IsaraObjectIdentifiers.id_alg_xmss, new XMSSKeyFactorySpi());
        addKeyInfoConverter(PQCObjectIdentifiers.xmss_mt, new XMSSMTKeyFactorySpi());
        addKeyInfoConverter(IsaraObjectIdentifiers.id_alg_xmssmt, new XMSSMTKeyFactorySpi());
        addKeyInfoConverter(PKCSObjectIdentifiers.id_alg_hss_lms_hashsig, new LMSKeyFactorySpi());
        addKeyInfoConverter(BCObjectIdentifiers.picnic_key, new PicnicKeyFactorySpi());
        addKeyInfoConverter(BCObjectIdentifiers.falcon_512, new FalconKeyFactorySpi());
        addKeyInfoConverter(BCObjectIdentifiers.falcon_1024, new FalconKeyFactorySpi());
        addKeyInfoConverter(BCObjectIdentifiers.dilithium2, new DilithiumKeyFactorySpi());
        addKeyInfoConverter(BCObjectIdentifiers.dilithium3, new DilithiumKeyFactorySpi());
        addKeyInfoConverter(BCObjectIdentifiers.dilithium5, new DilithiumKeyFactorySpi());
        addKeyInfoConverter(BCObjectIdentifiers.dilithium2_aes, new DilithiumKeyFactorySpi());
        addKeyInfoConverter(BCObjectIdentifiers.dilithium3_aes, new DilithiumKeyFactorySpi());
        addKeyInfoConverter(BCObjectIdentifiers.dilithium5_aes, new DilithiumKeyFactorySpi());
        addKeyInfoConverter(BCObjectIdentifiers.kyber512, new KyberKeyFactorySpi());
        addKeyInfoConverter(BCObjectIdentifiers.kyber768, new KyberKeyFactorySpi());
        addKeyInfoConverter(BCObjectIdentifiers.kyber1024, new KyberKeyFactorySpi());
        addKeyInfoConverter(BCObjectIdentifiers.mceliece348864_r3, new CMCEKeyFactorySpi());
        addKeyInfoConverter(BCObjectIdentifiers.mceliece460896_r3, new CMCEKeyFactorySpi());
        addKeyInfoConverter(BCObjectIdentifiers.mceliece6688128_r3, new CMCEKeyFactorySpi());
        addKeyInfoConverter(BCObjectIdentifiers.mceliece6960119_r3, new CMCEKeyFactorySpi());
        addKeyInfoConverter(BCObjectIdentifiers.mceliece8192128_r3, new CMCEKeyFactorySpi());
        addKeyInfoConverter(BCObjectIdentifiers.bike128, new BIKEKeyFactorySpi());
        addKeyInfoConverter(BCObjectIdentifiers.bike192, new BIKEKeyFactorySpi());
        addKeyInfoConverter(BCObjectIdentifiers.bike256, new BIKEKeyFactorySpi());
        addKeyInfoConverter(BCObjectIdentifiers.hqc128, new HQCKeyFactorySpi());
        addKeyInfoConverter(BCObjectIdentifiers.hqc192, new HQCKeyFactorySpi());
        addKeyInfoConverter(BCObjectIdentifiers.hqc256, new HQCKeyFactorySpi());
        addKeyInfoConverter(BCObjectIdentifiers.kyber1024, new KyberKeyFactorySpi());
        addKeyInfoConverter(BCObjectIdentifiers.kyber512_aes, new KyberKeyFactorySpi());
        addKeyInfoConverter(BCObjectIdentifiers.kyber768_aes, new KyberKeyFactorySpi());
        addKeyInfoConverter(BCObjectIdentifiers.kyber1024_aes, new KyberKeyFactorySpi());
        addKeyInfoConverter(BCObjectIdentifiers.ntruhps2048509, new NTRUKeyFactorySpi());
        addKeyInfoConverter(BCObjectIdentifiers.ntruhps2048677, new NTRUKeyFactorySpi());
        addKeyInfoConverter(BCObjectIdentifiers.ntruhps4096821, new NTRUKeyFactorySpi());
        addKeyInfoConverter(BCObjectIdentifiers.ntruhrss701, new NTRUKeyFactorySpi());
    }

    private void loadServiceClass(String str, String str2) {
        Class loadClass = ClassUtil.loadClass(BouncyCastleProvider.class, str + str2 + "$Mappings");
        if (loadClass != null) {
            try {
                ((AlgorithmProvider) loadClass.newInstance()).configure(this);
            } catch (Exception e) {
                throw new InternalError("cannot create instance of " + str + str2 + "$Mappings : " + e);
            }
        }
    }

    private static CryptoServiceProperties service(String str, int i) {
        return new JcaCryptoService(str, i);
    }

    /* access modifiers changed from: private */
    public void setup() {
        String str;
        String str2;
        loadAlgorithms(DIGEST_PACKAGE, DIGESTS);
        loadAlgorithms(SYMMETRIC_PACKAGE, SYMMETRIC_GENERIC);
        loadAlgorithms(SYMMETRIC_PACKAGE, SYMMETRIC_MACS);
        loadAlgorithms(SYMMETRIC_PACKAGE, SYMMETRIC_CIPHERS);
        loadAlgorithms(ASYMMETRIC_PACKAGE, ASYMMETRIC_GENERIC);
        loadAlgorithms(ASYMMETRIC_PACKAGE, ASYMMETRIC_CIPHERS);
        loadAlgorithms(KEYSTORE_PACKAGE, KEYSTORES);
        loadAlgorithms(SECURE_RANDOM_PACKAGE, SECURE_RANDOMS);
        loadPQCKeys();
        put("X509Store.CERTIFICATE/COLLECTION", "org.bouncycastle.jce.provider.X509StoreCertCollection");
        put("X509Store.ATTRIBUTECERTIFICATE/COLLECTION", "org.bouncycastle.jce.provider.X509StoreAttrCertCollection");
        put("X509Store.CRL/COLLECTION", "org.bouncycastle.jce.provider.X509StoreCRLCollection");
        put("X509Store.CERTIFICATEPAIR/COLLECTION", "org.bouncycastle.jce.provider.X509StoreCertPairCollection");
        put("X509Store.CERTIFICATE/LDAP", "org.bouncycastle.jce.provider.X509StoreLDAPCerts");
        put("X509Store.CRL/LDAP", "org.bouncycastle.jce.provider.X509StoreLDAPCRLs");
        put("X509Store.ATTRIBUTECERTIFICATE/LDAP", "org.bouncycastle.jce.provider.X509StoreLDAPAttrCerts");
        put("X509Store.CERTIFICATEPAIR/LDAP", "org.bouncycastle.jce.provider.X509StoreLDAPCertPairs");
        put("X509StreamParser.CERTIFICATE", "org.bouncycastle.jce.provider.X509CertParser");
        put("X509StreamParser.ATTRIBUTECERTIFICATE", "org.bouncycastle.jce.provider.X509AttrCertParser");
        put("X509StreamParser.CRL", "org.bouncycastle.jce.provider.X509CRLParser");
        put("X509StreamParser.CERTIFICATEPAIR", "org.bouncycastle.jce.provider.X509CertPairParser");
        put("Cipher.BROKENPBEWITHMD5ANDDES", "org.bouncycastle.jce.provider.BrokenJCEBlockCipher$BrokePBEWithMD5AndDES");
        put("Cipher.BROKENPBEWITHSHA1ANDDES", "org.bouncycastle.jce.provider.BrokenJCEBlockCipher$BrokePBEWithSHA1AndDES");
        put("Cipher.OLDPBEWITHSHAANDTWOFISH-CBC", "org.bouncycastle.jce.provider.BrokenJCEBlockCipher$OldPBEWithSHAAndTwofish");
        Class cls = revChkClass;
        put("CertPathValidator.RFC3281", "org.bouncycastle.jce.provider.PKIXAttrCertPathValidatorSpi");
        put("CertPathBuilder.RFC3281", "org.bouncycastle.jce.provider.PKIXAttrCertPathBuilderSpi");
        if (cls != null) {
            str2 = "org.bouncycastle.jce.provider.PKIXCertPathValidatorSpi_8";
            put("CertPathValidator.RFC3280", str2);
            str = "org.bouncycastle.jce.provider.PKIXCertPathBuilderSpi_8";
        } else {
            str2 = "org.bouncycastle.jce.provider.PKIXCertPathValidatorSpi";
            put("CertPathValidator.RFC3280", str2);
            str = "org.bouncycastle.jce.provider.PKIXCertPathBuilderSpi";
        }
        put("CertPathBuilder.RFC3280", str);
        put("CertPathValidator.PKIX", str2);
        put("CertPathBuilder.PKIX", str);
        put("CertStore.Collection", "org.bouncycastle.jce.provider.CertStoreCollectionSpi");
        put("CertStore.LDAP", "org.bouncycastle.jce.provider.X509LDAPCertStoreSpi");
        put("CertStore.Multi", "org.bouncycastle.jce.provider.MultiCertStoreSpi");
        put("Alg.Alias.CertStore.X509LDAP", "LDAP");
        getService("SecureRandom", "DEFAULT");
    }

    public void addAlgorithm(String str, String str2) {
        if (!containsKey(str)) {
            put(str, str2);
            return;
        }
        throw new IllegalStateException("duplicate provider key (" + str + ") found");
    }

    public void addAlgorithm(String str, String str2, Map<String, String> map) {
        addAlgorithm(str, str2);
        addAttributes(str, map);
    }

    public void addAlgorithm(String str, ASN1ObjectIdentifier aSN1ObjectIdentifier, String str2) {
        addAlgorithm(str + "." + aSN1ObjectIdentifier, str2);
        addAlgorithm(str + ".OID." + aSN1ObjectIdentifier, str2);
    }

    public void addAlgorithm(String str, ASN1ObjectIdentifier aSN1ObjectIdentifier, String str2, Map<String, String> map) {
        addAlgorithm(str, aSN1ObjectIdentifier, str2);
        addAttributes(str + "." + aSN1ObjectIdentifier, map);
        addAttributes(str + ".OID." + aSN1ObjectIdentifier, map);
    }

    public void addAttributes(String str, Map<String, String> map) {
        put(str + " ImplementedIn", "Software");
        for (String next : map.keySet()) {
            String str2 = str + AnsiRenderer.CODE_TEXT_SEPARATOR + next;
            if (!containsKey(str2)) {
                put(str2, map.get(next));
            } else {
                throw new IllegalStateException("duplicate provider attribute key (" + str2 + ") found");
            }
        }
    }

    public void addKeyInfoConverter(ASN1ObjectIdentifier aSN1ObjectIdentifier, AsymmetricKeyInfoConverter asymmetricKeyInfoConverter) {
        Map map = keyInfoConverters;
        synchronized (map) {
            map.put(aSN1ObjectIdentifier, asymmetricKeyInfoConverter);
        }
    }

    public AsymmetricKeyInfoConverter getKeyInfoConverter(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return (AsymmetricKeyInfoConverter) keyInfoConverters.get(aSN1ObjectIdentifier);
    }

    public final Provider.Service getService(final String str, final String str2) {
        final String str3 = str + "." + Strings.toUpperCase(str2);
        Provider.Service service = this.serviceMap.get(str3);
        if (service == null) {
            synchronized (this) {
                service = (Provider.Service) (!this.serviceMap.containsKey(str3) ? AccessController.doPrivileged(new PrivilegedAction<Provider.Service>() {
                    public Provider.Service run() {
                        Provider.Service access$101 = BouncyCastleProvider.super.getService(str, str2);
                        if (access$101 == null) {
                            return null;
                        }
                        BouncyCastleProvider.this.serviceMap.put(str3, access$101);
                        BouncyCastleProvider bouncyCastleProvider = BouncyCastleProvider.this;
                        Object unused = BouncyCastleProvider.super.remove(access$101.getType() + "." + access$101.getAlgorithm());
                        BouncyCastleProvider.super.putService(access$101);
                        return access$101;
                    }
                }) : this.serviceMap.get(str3));
            }
        }
        return service;
    }

    public boolean hasAlgorithm(String str, String str2) {
        if (!containsKey(str + "." + str2)) {
            StringBuilder sb = new StringBuilder("Alg.Alias.");
            sb.append(str);
            sb.append(".");
            sb.append(str2);
            return containsKey(sb.toString());
        }
    }

    public void setParameter(String str, Object obj) {
        ProviderConfiguration providerConfiguration = CONFIGURATION;
        synchronized (providerConfiguration) {
            ((BouncyCastleProviderConfiguration) providerConfiguration).setParameter(str, obj);
        }
    }
}
