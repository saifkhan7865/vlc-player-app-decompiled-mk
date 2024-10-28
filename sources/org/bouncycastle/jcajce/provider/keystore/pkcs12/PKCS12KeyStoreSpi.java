package org.bouncycastle.jcajce.provider.keystore.pkcs12;

import androidx.leanback.preference.LeanbackPreferenceDialogFragment;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.KeyStoreSpi;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;
import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import org.bouncycastle.asn1.ASN1BMPString;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Encoding;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.BEROctetString;
import org.bouncycastle.asn1.BERSequence;
import org.bouncycastle.asn1.DERBMPString;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERSet;
import org.bouncycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
import org.bouncycastle.asn1.cryptopro.GOST28147Parameters;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.ntt.NTTObjectIdentifiers;
import org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.AuthenticatedSafe;
import org.bouncycastle.asn1.pkcs.CertBag;
import org.bouncycastle.asn1.pkcs.ContentInfo;
import org.bouncycastle.asn1.pkcs.EncryptedData;
import org.bouncycastle.asn1.pkcs.EncryptedPrivateKeyInfo;
import org.bouncycastle.asn1.pkcs.MacData;
import org.bouncycastle.asn1.pkcs.PBES2Parameters;
import org.bouncycastle.asn1.pkcs.PBKDF2Params;
import org.bouncycastle.asn1.pkcs.PKCS12PBEParams;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.Pfx;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.pkcs.SafeBag;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.DigestInfo;
import org.bouncycastle.asn1.x509.SubjectKeyIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.asn1.x509.X509ObjectIdentifiers;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.util.DigestFactory;
import org.bouncycastle.jcajce.BCLoadStoreParameter;
import org.bouncycastle.jcajce.PKCS12Key;
import org.bouncycastle.jcajce.PKCS12StoreParameter;
import org.bouncycastle.jcajce.provider.keystore.util.AdaptingKeyStoreSpi;
import org.bouncycastle.jcajce.provider.keystore.util.ParameterUtil;
import org.bouncycastle.jcajce.spec.GOST28147ParameterSpec;
import org.bouncycastle.jcajce.spec.PBKDF2KeySpec;
import org.bouncycastle.jcajce.util.BCJcaJceHelper;
import org.bouncycastle.jcajce.util.DefaultJcaJceHelper;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.jce.interfaces.BCKeyStore;
import org.bouncycastle.jce.interfaces.PKCS12BagAttributeCarrier;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.provider.JDKPKCS12StoreParameter;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Integers;
import org.bouncycastle.util.Properties;
import org.bouncycastle.util.Strings;
import org.bouncycastle.util.encoders.Hex;

public class PKCS12KeyStoreSpi extends KeyStoreSpi implements PKCSObjectIdentifiers, X509ObjectIdentifiers, BCKeyStore {
    static final int CERTIFICATE = 1;
    static final int KEY = 2;
    static final int KEY_PRIVATE = 0;
    static final int KEY_PUBLIC = 1;
    static final int KEY_SECRET = 2;
    private static final int MIN_ITERATIONS = 51200;
    static final int NULL = 0;
    static final String PKCS12_MAX_IT_COUNT_PROPERTY = "org.bouncycastle.pkcs12.max_it_count";
    private static final int SALT_SIZE = 20;
    static final int SEALED = 4;
    static final int SECRET = 3;
    private static final DefaultSecretKeyProvider keySizeProvider = new DefaultSecretKeyProvider();
    private ASN1ObjectIdentifier certAlgorithm;
    private CertificateFactory certFact;
    private IgnoresCaseHashtable certs = new IgnoresCaseHashtable();
    private Hashtable chainCerts = new Hashtable();
    private final JcaJceHelper helper = new BCJcaJceHelper();
    private int itCount = 102400;
    private ASN1ObjectIdentifier keyAlgorithm;
    private Hashtable keyCerts = new Hashtable();
    private IgnoresCaseHashtable keys = new IgnoresCaseHashtable();
    private IgnoresCaseHashtable localIds = new IgnoresCaseHashtable();
    private AlgorithmIdentifier macAlgorithm = new AlgorithmIdentifier(OIWObjectIdentifiers.idSHA1, DERNull.INSTANCE);
    protected SecureRandom random = CryptoServicesRegistrar.getSecureRandom();
    private int saltLength = 20;

    public static class BCPKCS12KeyStore extends AdaptingKeyStoreSpi {
        public BCPKCS12KeyStore() {
            super(new BCJcaJceHelper(), new PKCS12KeyStoreSpi(new BCJcaJceHelper(), PKCSObjectIdentifiers.pbeWithSHAAnd3_KeyTripleDES_CBC, PKCSObjectIdentifiers.pbeWithSHAAnd40BitRC2_CBC));
        }
    }

    public static class BCPKCS12KeyStore3DES extends AdaptingKeyStoreSpi {
        public BCPKCS12KeyStore3DES() {
            super(new BCJcaJceHelper(), new PKCS12KeyStoreSpi(new BCJcaJceHelper(), PKCSObjectIdentifiers.pbeWithSHAAnd3_KeyTripleDES_CBC, PKCSObjectIdentifiers.pbeWithSHAAnd3_KeyTripleDES_CBC));
        }
    }

    private class CertId {
        byte[] id;

        CertId(PublicKey publicKey) {
            this.id = PKCS12KeyStoreSpi.this.createSubjectKeyId(publicKey).getKeyIdentifier();
        }

        CertId(byte[] bArr) {
            this.id = bArr;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof CertId)) {
                return false;
            }
            return Arrays.areEqual(this.id, ((CertId) obj).id);
        }

        public int hashCode() {
            return Arrays.hashCode(this.id);
        }
    }

    public static class DefPKCS12KeyStore extends AdaptingKeyStoreSpi {
        public DefPKCS12KeyStore() {
            super(new DefaultJcaJceHelper(), new PKCS12KeyStoreSpi(new DefaultJcaJceHelper(), PKCSObjectIdentifiers.pbeWithSHAAnd3_KeyTripleDES_CBC, PKCSObjectIdentifiers.pbeWithSHAAnd40BitRC2_CBC));
        }
    }

    public static class DefPKCS12KeyStore3DES extends AdaptingKeyStoreSpi {
        public DefPKCS12KeyStore3DES() {
            super(new DefaultJcaJceHelper(), new PKCS12KeyStoreSpi(new DefaultJcaJceHelper(), PKCSObjectIdentifiers.pbeWithSHAAnd3_KeyTripleDES_CBC, PKCSObjectIdentifiers.pbeWithSHAAnd3_KeyTripleDES_CBC));
        }
    }

    private static class DefaultSecretKeyProvider {
        private final Map KEY_SIZES;

        DefaultSecretKeyProvider() {
            HashMap hashMap = new HashMap();
            hashMap.put(new ASN1ObjectIdentifier("1.2.840.113533.7.66.10"), Integers.valueOf(128));
            hashMap.put(PKCSObjectIdentifiers.des_EDE3_CBC, Integers.valueOf(192));
            hashMap.put(NISTObjectIdentifiers.id_aes128_CBC, Integers.valueOf(128));
            hashMap.put(NISTObjectIdentifiers.id_aes192_CBC, Integers.valueOf(192));
            hashMap.put(NISTObjectIdentifiers.id_aes256_CBC, Integers.valueOf(256));
            hashMap.put(NTTObjectIdentifiers.id_camellia128_cbc, Integers.valueOf(128));
            hashMap.put(NTTObjectIdentifiers.id_camellia192_cbc, Integers.valueOf(192));
            hashMap.put(NTTObjectIdentifiers.id_camellia256_cbc, Integers.valueOf(256));
            hashMap.put(CryptoProObjectIdentifiers.gostR28147_gcfb, Integers.valueOf(256));
            this.KEY_SIZES = Collections.unmodifiableMap(hashMap);
        }

        public int getKeySize(AlgorithmIdentifier algorithmIdentifier) {
            Integer num = (Integer) this.KEY_SIZES.get(algorithmIdentifier.getAlgorithm());
            if (num != null) {
                return num.intValue();
            }
            return -1;
        }
    }

    private static class IgnoresCaseHashtable {
        /* access modifiers changed from: private */
        public Hashtable keys;
        private Hashtable orig;

        private IgnoresCaseHashtable() {
            this.orig = new Hashtable();
            this.keys = new Hashtable();
        }

        public Enumeration elements() {
            return this.orig.elements();
        }

        public Object get(String str) {
            String str2 = (String) this.keys.get(str == null ? null : Strings.toLowerCase(str));
            if (str2 == null) {
                return null;
            }
            return this.orig.get(str2);
        }

        public Enumeration keys() {
            return this.orig.keys();
        }

        public void put(String str, Object obj) {
            String lowerCase = str == null ? null : Strings.toLowerCase(str);
            String str2 = (String) this.keys.get(lowerCase);
            if (str2 != null) {
                this.orig.remove(str2);
            }
            this.keys.put(lowerCase, str);
            this.orig.put(str, obj);
        }

        public Object remove(String str) {
            String str2 = (String) this.keys.remove(str == null ? null : Strings.toLowerCase(str));
            if (str2 == null) {
                return null;
            }
            return this.orig.remove(str2);
        }

        public int size() {
            return this.orig.size();
        }
    }

    public PKCS12KeyStoreSpi(JcaJceHelper jcaJceHelper, ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1ObjectIdentifier aSN1ObjectIdentifier2) {
        this.keyAlgorithm = aSN1ObjectIdentifier;
        this.certAlgorithm = aSN1ObjectIdentifier2;
        try {
            this.certFact = jcaJceHelper.createCertificateFactory("X.509");
        } catch (Exception e) {
            throw new IllegalArgumentException("can't create cert factory - " + e.toString());
        }
    }

    private byte[] calculatePbeMac(ASN1ObjectIdentifier aSN1ObjectIdentifier, byte[] bArr, int i, char[] cArr, boolean z, byte[] bArr2) throws Exception {
        PBEParameterSpec pBEParameterSpec = new PBEParameterSpec(bArr, i);
        Mac createMac = this.helper.createMac(aSN1ObjectIdentifier.getId());
        createMac.init(new PKCS12Key(cArr, z), pBEParameterSpec);
        createMac.update(bArr2);
        return createMac.doFinal();
    }

    private Cipher createCipher(int i, char[] cArr, AlgorithmIdentifier algorithmIdentifier) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, NoSuchProviderException {
        SecretKey secretKey;
        AlgorithmParameterSpec algorithmParameterSpec;
        PBES2Parameters instance = PBES2Parameters.getInstance(algorithmIdentifier.getParameters());
        PBKDF2Params instance2 = PBKDF2Params.getInstance(instance.getKeyDerivationFunc().getParameters());
        AlgorithmIdentifier instance3 = AlgorithmIdentifier.getInstance(instance.getEncryptionScheme());
        SecretKeyFactory createSecretKeyFactory = this.helper.createSecretKeyFactory(instance.getKeyDerivationFunc().getAlgorithm().getId());
        if (instance2.isDefaultPrf()) {
            secretKey = createSecretKeyFactory.generateSecret(new PBEKeySpec(cArr, instance2.getSalt(), validateIterationCount(instance2.getIterationCount()), keySizeProvider.getKeySize(instance3)));
        } else {
            secretKey = createSecretKeyFactory.generateSecret(new PBKDF2KeySpec(cArr, instance2.getSalt(), validateIterationCount(instance2.getIterationCount()), keySizeProvider.getKeySize(instance3), instance2.getPrf()));
        }
        Cipher createCipher = this.helper.createCipher(instance.getEncryptionScheme().getAlgorithm().getId());
        ASN1Encodable parameters = instance.getEncryptionScheme().getParameters();
        if (parameters instanceof ASN1OctetString) {
            algorithmParameterSpec = new IvParameterSpec(ASN1OctetString.getInstance(parameters).getOctets());
        } else {
            GOST28147Parameters instance4 = GOST28147Parameters.getInstance(parameters);
            algorithmParameterSpec = new GOST28147ParameterSpec(instance4.getEncryptionParamSet(), instance4.getIV());
        }
        createCipher.init(i, secretKey, algorithmParameterSpec);
        return createCipher;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0074, code lost:
        if (r4 == false) goto L_0x0076;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private org.bouncycastle.asn1.pkcs.SafeBag createSafeBag(java.lang.String r8, java.security.cert.Certificate r9) throws java.security.cert.CertificateEncodingException {
        /*
            r7 = this;
            org.bouncycastle.asn1.pkcs.CertBag r0 = new org.bouncycastle.asn1.pkcs.CertBag
            org.bouncycastle.asn1.ASN1ObjectIdentifier r1 = x509Certificate
            org.bouncycastle.asn1.DEROctetString r2 = new org.bouncycastle.asn1.DEROctetString
            byte[] r3 = r9.getEncoded()
            r2.<init>((byte[]) r3)
            r0.<init>(r1, r2)
            org.bouncycastle.asn1.ASN1EncodableVector r1 = new org.bouncycastle.asn1.ASN1EncodableVector
            r1.<init>()
            boolean r2 = r9 instanceof org.bouncycastle.jce.interfaces.PKCS12BagAttributeCarrier
            if (r2 == 0) goto L_0x0076
            r2 = r9
            org.bouncycastle.jce.interfaces.PKCS12BagAttributeCarrier r2 = (org.bouncycastle.jce.interfaces.PKCS12BagAttributeCarrier) r2
            org.bouncycastle.asn1.ASN1ObjectIdentifier r3 = pkcs_9_at_friendlyName
            org.bouncycastle.asn1.ASN1Encodable r3 = r2.getBagAttribute(r3)
            org.bouncycastle.asn1.ASN1BMPString r3 = (org.bouncycastle.asn1.ASN1BMPString) r3
            if (r3 == 0) goto L_0x0030
            java.lang.String r3 = r3.getString()
            boolean r3 = r3.equals(r8)
            if (r3 != 0) goto L_0x003c
        L_0x0030:
            if (r8 == 0) goto L_0x003c
            org.bouncycastle.asn1.ASN1ObjectIdentifier r3 = pkcs_9_at_friendlyName
            org.bouncycastle.asn1.DERBMPString r4 = new org.bouncycastle.asn1.DERBMPString
            r4.<init>((java.lang.String) r8)
            r2.setBagAttribute(r3, r4)
        L_0x003c:
            java.util.Enumeration r3 = r2.getBagAttributeKeys()
            r4 = 0
        L_0x0041:
            boolean r5 = r3.hasMoreElements()
            if (r5 == 0) goto L_0x0074
            java.lang.Object r5 = r3.nextElement()
            org.bouncycastle.asn1.ASN1ObjectIdentifier r5 = (org.bouncycastle.asn1.ASN1ObjectIdentifier) r5
            org.bouncycastle.asn1.ASN1ObjectIdentifier r6 = org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers.pkcs_9_at_localKeyId
            boolean r6 = r5.equals((org.bouncycastle.asn1.ASN1Primitive) r6)
            if (r6 == 0) goto L_0x0056
            goto L_0x0041
        L_0x0056:
            org.bouncycastle.asn1.ASN1EncodableVector r4 = new org.bouncycastle.asn1.ASN1EncodableVector
            r4.<init>()
            r4.add(r5)
            org.bouncycastle.asn1.DERSet r6 = new org.bouncycastle.asn1.DERSet
            org.bouncycastle.asn1.ASN1Encodable r5 = r2.getBagAttribute(r5)
            r6.<init>((org.bouncycastle.asn1.ASN1Encodable) r5)
            r4.add(r6)
            org.bouncycastle.asn1.DERSequence r5 = new org.bouncycastle.asn1.DERSequence
            r5.<init>((org.bouncycastle.asn1.ASN1EncodableVector) r4)
            r1.add(r5)
            r4 = 1
            goto L_0x0041
        L_0x0074:
            if (r4 != 0) goto L_0x0095
        L_0x0076:
            org.bouncycastle.asn1.ASN1EncodableVector r2 = new org.bouncycastle.asn1.ASN1EncodableVector
            r2.<init>()
            org.bouncycastle.asn1.ASN1ObjectIdentifier r3 = pkcs_9_at_friendlyName
            r2.add(r3)
            org.bouncycastle.asn1.DERSet r3 = new org.bouncycastle.asn1.DERSet
            org.bouncycastle.asn1.DERBMPString r4 = new org.bouncycastle.asn1.DERBMPString
            r4.<init>((java.lang.String) r8)
            r3.<init>((org.bouncycastle.asn1.ASN1Encodable) r4)
            r2.add(r3)
            org.bouncycastle.asn1.DERSequence r8 = new org.bouncycastle.asn1.DERSequence
            r8.<init>((org.bouncycastle.asn1.ASN1EncodableVector) r2)
            r1.add(r8)
        L_0x0095:
            boolean r8 = r9 instanceof java.security.cert.X509Certificate
            if (r8 == 0) goto L_0x010e
            java.security.cert.X509Certificate r9 = (java.security.cert.X509Certificate) r9
            byte[] r8 = r9.getTBSCertificate()
            org.bouncycastle.asn1.x509.TBSCertificate r8 = org.bouncycastle.asn1.x509.TBSCertificate.getInstance(r8)
            org.bouncycastle.asn1.x509.Extensions r8 = r8.getExtensions()
            if (r8 == 0) goto L_0x00f2
            org.bouncycastle.asn1.ASN1ObjectIdentifier r9 = org.bouncycastle.asn1.x509.Extension.extendedKeyUsage
            org.bouncycastle.asn1.x509.Extension r8 = r8.getExtension(r9)
            if (r8 == 0) goto L_0x00d8
            org.bouncycastle.asn1.ASN1EncodableVector r9 = new org.bouncycastle.asn1.ASN1EncodableVector
            r9.<init>()
            org.bouncycastle.asn1.ASN1ObjectIdentifier r2 = org.bouncycastle.asn1.misc.MiscObjectIdentifiers.id_oracle_pkcs12_trusted_key_usage
            r9.add(r2)
            org.bouncycastle.asn1.DERSet r2 = new org.bouncycastle.asn1.DERSet
            org.bouncycastle.asn1.ASN1Encodable r8 = r8.getParsedValue()
            org.bouncycastle.asn1.x509.ExtendedKeyUsage r8 = org.bouncycastle.asn1.x509.ExtendedKeyUsage.getInstance(r8)
            org.bouncycastle.asn1.x509.KeyPurposeId[] r8 = r8.getUsages()
            r2.<init>((org.bouncycastle.asn1.ASN1Encodable[]) r8)
            r9.add(r2)
            org.bouncycastle.asn1.DERSequence r8 = new org.bouncycastle.asn1.DERSequence
            r8.<init>((org.bouncycastle.asn1.ASN1EncodableVector) r9)
            r1.add(r8)
            goto L_0x010e
        L_0x00d8:
            org.bouncycastle.asn1.ASN1EncodableVector r8 = new org.bouncycastle.asn1.ASN1EncodableVector
            r8.<init>()
            org.bouncycastle.asn1.ASN1ObjectIdentifier r9 = org.bouncycastle.asn1.misc.MiscObjectIdentifiers.id_oracle_pkcs12_trusted_key_usage
            r8.add(r9)
            org.bouncycastle.asn1.DERSet r9 = new org.bouncycastle.asn1.DERSet
            org.bouncycastle.asn1.x509.KeyPurposeId r2 = org.bouncycastle.asn1.x509.KeyPurposeId.anyExtendedKeyUsage
            r9.<init>((org.bouncycastle.asn1.ASN1Encodable) r2)
            r8.add(r9)
            org.bouncycastle.asn1.DERSequence r9 = new org.bouncycastle.asn1.DERSequence
            r9.<init>((org.bouncycastle.asn1.ASN1EncodableVector) r8)
            goto L_0x010b
        L_0x00f2:
            org.bouncycastle.asn1.ASN1EncodableVector r8 = new org.bouncycastle.asn1.ASN1EncodableVector
            r8.<init>()
            org.bouncycastle.asn1.ASN1ObjectIdentifier r9 = org.bouncycastle.asn1.misc.MiscObjectIdentifiers.id_oracle_pkcs12_trusted_key_usage
            r8.add(r9)
            org.bouncycastle.asn1.DERSet r9 = new org.bouncycastle.asn1.DERSet
            org.bouncycastle.asn1.x509.KeyPurposeId r2 = org.bouncycastle.asn1.x509.KeyPurposeId.anyExtendedKeyUsage
            r9.<init>((org.bouncycastle.asn1.ASN1Encodable) r2)
            r8.add(r9)
            org.bouncycastle.asn1.DERSequence r9 = new org.bouncycastle.asn1.DERSequence
            r9.<init>((org.bouncycastle.asn1.ASN1EncodableVector) r8)
        L_0x010b:
            r1.add(r9)
        L_0x010e:
            org.bouncycastle.asn1.pkcs.SafeBag r8 = new org.bouncycastle.asn1.pkcs.SafeBag
            org.bouncycastle.asn1.ASN1ObjectIdentifier r9 = certBag
            org.bouncycastle.asn1.ASN1Primitive r0 = r0.toASN1Primitive()
            org.bouncycastle.asn1.DERSet r2 = new org.bouncycastle.asn1.DERSet
            r2.<init>((org.bouncycastle.asn1.ASN1EncodableVector) r1)
            r8.<init>(r9, r0, r2)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.jcajce.provider.keystore.pkcs12.PKCS12KeyStoreSpi.createSafeBag(java.lang.String, java.security.cert.Certificate):org.bouncycastle.asn1.pkcs.SafeBag");
    }

    /* access modifiers changed from: private */
    public SubjectKeyIdentifier createSubjectKeyId(PublicKey publicKey) {
        try {
            return new SubjectKeyIdentifier(getDigest(SubjectPublicKeyInfo.getInstance(publicKey.getEncoded())));
        } catch (Exception unused) {
            throw new RuntimeException("error creating key");
        }
    }

    private void doStore(OutputStream outputStream, char[] cArr, boolean z) throws IOException {
        String str;
        ContentInfo contentInfo;
        OutputStream outputStream2 = outputStream;
        char[] cArr2 = cArr;
        int size = this.keys.size();
        String str2 = ASN1Encoding.BER;
        if (size == 0) {
            if (cArr2 == null) {
                Enumeration keys2 = this.certs.keys();
                ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
                while (keys2.hasMoreElements()) {
                    try {
                        String str3 = (String) keys2.nextElement();
                        aSN1EncodableVector.add(createSafeBag(str3, (Certificate) this.certs.get(str3)));
                    } catch (CertificateEncodingException e) {
                        throw new IOException("Error encoding certificate: " + e.toString());
                    }
                }
                ASN1ObjectIdentifier aSN1ObjectIdentifier = PKCSObjectIdentifiers.data;
                if (z) {
                    DEROctetString dEROctetString = new DEROctetString(new DERSequence(aSN1EncodableVector).getEncoded());
                    new Pfx(new ContentInfo(PKCSObjectIdentifiers.data, new DEROctetString(new DERSequence((ASN1Encodable) contentInfo).getEncoded())), (MacData) null).encodeTo(outputStream2, ASN1Encoding.DER);
                    return;
                }
                contentInfo = new ContentInfo(aSN1ObjectIdentifier, new BEROctetString(new BERSequence(aSN1EncodableVector).getEncoded()));
                new Pfx(new ContentInfo(PKCSObjectIdentifiers.data, new BEROctetString(new BERSequence((ASN1Encodable) contentInfo).getEncoded())), (MacData) null).encodeTo(outputStream2, str2);
                return;
            }
        } else if (cArr2 == null) {
            throw new NullPointerException("no password supplied for PKCS#12 KeyStore");
        }
        ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector();
        Enumeration keys3 = this.keys.keys();
        while (keys3.hasMoreElements()) {
            byte[] bArr = new byte[20];
            this.random.nextBytes(bArr);
            String str4 = (String) keys3.nextElement();
            PrivateKey privateKey = (PrivateKey) this.keys.get(str4);
            PKCS12PBEParams pKCS12PBEParams = new PKCS12PBEParams(bArr, MIN_ITERATIONS);
            EncryptedPrivateKeyInfo encryptedPrivateKeyInfo = new EncryptedPrivateKeyInfo(new AlgorithmIdentifier(this.keyAlgorithm, pKCS12PBEParams.toASN1Primitive()), wrapKey(this.keyAlgorithm.getId(), privateKey, pKCS12PBEParams, cArr2));
            ASN1EncodableVector aSN1EncodableVector3 = new ASN1EncodableVector();
            if (privateKey instanceof PKCS12BagAttributeCarrier) {
                PKCS12BagAttributeCarrier pKCS12BagAttributeCarrier = (PKCS12BagAttributeCarrier) privateKey;
                ASN1BMPString aSN1BMPString = (ASN1BMPString) pKCS12BagAttributeCarrier.getBagAttribute(pkcs_9_at_friendlyName);
                if (aSN1BMPString == null || !aSN1BMPString.getString().equals(str4)) {
                    pKCS12BagAttributeCarrier.setBagAttribute(pkcs_9_at_friendlyName, new DERBMPString(str4));
                }
                if (pKCS12BagAttributeCarrier.getBagAttribute(pkcs_9_at_localKeyId) == null) {
                    pKCS12BagAttributeCarrier.setBagAttribute(pkcs_9_at_localKeyId, createSubjectKeyId(engineGetCertificate(str4).getPublicKey()));
                }
                Enumeration bagAttributeKeys = pKCS12BagAttributeCarrier.getBagAttributeKeys();
                boolean z2 = false;
                while (bagAttributeKeys.hasMoreElements()) {
                    ASN1ObjectIdentifier aSN1ObjectIdentifier2 = (ASN1ObjectIdentifier) bagAttributeKeys.nextElement();
                    ASN1EncodableVector aSN1EncodableVector4 = new ASN1EncodableVector();
                    aSN1EncodableVector4.add(aSN1ObjectIdentifier2);
                    aSN1EncodableVector4.add(new DERSet(pKCS12BagAttributeCarrier.getBagAttribute(aSN1ObjectIdentifier2)));
                    aSN1EncodableVector3.add(new DERSequence(aSN1EncodableVector4));
                    z2 = true;
                }
                if (z2) {
                    aSN1EncodableVector2.add(new SafeBag(pkcs8ShroudedKeyBag, encryptedPrivateKeyInfo.toASN1Primitive(), new DERSet(aSN1EncodableVector3)));
                }
            }
            ASN1EncodableVector aSN1EncodableVector5 = new ASN1EncodableVector();
            Certificate engineGetCertificate = engineGetCertificate(str4);
            aSN1EncodableVector5.add(pkcs_9_at_localKeyId);
            aSN1EncodableVector5.add(new DERSet((ASN1Encodable) createSubjectKeyId(engineGetCertificate.getPublicKey())));
            aSN1EncodableVector3.add(new DERSequence(aSN1EncodableVector5));
            ASN1EncodableVector aSN1EncodableVector6 = new ASN1EncodableVector();
            aSN1EncodableVector6.add(pkcs_9_at_friendlyName);
            aSN1EncodableVector6.add(new DERSet((ASN1Encodable) new DERBMPString(str4)));
            aSN1EncodableVector3.add(new DERSequence(aSN1EncodableVector6));
            aSN1EncodableVector2.add(new SafeBag(pkcs8ShroudedKeyBag, encryptedPrivateKeyInfo.toASN1Primitive(), new DERSet(aSN1EncodableVector3)));
        }
        BEROctetString bEROctetString = new BEROctetString(new DERSequence(aSN1EncodableVector2).getEncoded(ASN1Encoding.DER));
        byte[] bArr2 = new byte[20];
        this.random.nextBytes(bArr2);
        ASN1EncodableVector aSN1EncodableVector7 = new ASN1EncodableVector();
        AlgorithmIdentifier algorithmIdentifier = new AlgorithmIdentifier(this.certAlgorithm, new PKCS12PBEParams(bArr2, MIN_ITERATIONS).toASN1Primitive());
        Hashtable hashtable = new Hashtable();
        Enumeration keys4 = this.keys.keys();
        while (keys4.hasMoreElements()) {
            try {
                String str5 = (String) keys4.nextElement();
                Certificate engineGetCertificate2 = engineGetCertificate(str5);
                Enumeration enumeration = keys4;
                CertBag certBag = new CertBag(x509Certificate, new DEROctetString(engineGetCertificate2.getEncoded()));
                ASN1EncodableVector aSN1EncodableVector8 = new ASN1EncodableVector();
                if (engineGetCertificate2 instanceof PKCS12BagAttributeCarrier) {
                    PKCS12BagAttributeCarrier pKCS12BagAttributeCarrier2 = (PKCS12BagAttributeCarrier) engineGetCertificate2;
                    ASN1BMPString aSN1BMPString2 = (ASN1BMPString) pKCS12BagAttributeCarrier2.getBagAttribute(pkcs_9_at_friendlyName);
                    if (aSN1BMPString2 == null || !aSN1BMPString2.getString().equals(str5)) {
                        pKCS12BagAttributeCarrier2.setBagAttribute(pkcs_9_at_friendlyName, new DERBMPString(str5));
                    }
                    if (pKCS12BagAttributeCarrier2.getBagAttribute(pkcs_9_at_localKeyId) == null) {
                        pKCS12BagAttributeCarrier2.setBagAttribute(pkcs_9_at_localKeyId, createSubjectKeyId(engineGetCertificate2.getPublicKey()));
                    }
                    Enumeration bagAttributeKeys2 = pKCS12BagAttributeCarrier2.getBagAttributeKeys();
                    boolean z3 = false;
                    while (bagAttributeKeys2.hasMoreElements()) {
                        ASN1ObjectIdentifier aSN1ObjectIdentifier3 = (ASN1ObjectIdentifier) bagAttributeKeys2.nextElement();
                        Enumeration enumeration2 = bagAttributeKeys2;
                        ASN1EncodableVector aSN1EncodableVector9 = new ASN1EncodableVector();
                        aSN1EncodableVector9.add(aSN1ObjectIdentifier3);
                        aSN1EncodableVector9.add(new DERSet(pKCS12BagAttributeCarrier2.getBagAttribute(aSN1ObjectIdentifier3)));
                        aSN1EncodableVector8.add(new DERSequence(aSN1EncodableVector9));
                        bagAttributeKeys2 = enumeration2;
                        str2 = str2;
                        z3 = true;
                    }
                    str = str2;
                    if (!z3) {
                    }
                    aSN1EncodableVector7.add(new SafeBag(certBag, certBag.toASN1Primitive(), new DERSet(aSN1EncodableVector8)));
                    hashtable.put(engineGetCertificate2, engineGetCertificate2);
                    char[] cArr3 = cArr;
                    keys4 = enumeration;
                    str2 = str;
                } else {
                    str = str2;
                }
                ASN1EncodableVector aSN1EncodableVector10 = new ASN1EncodableVector();
                aSN1EncodableVector10.add(pkcs_9_at_localKeyId);
                aSN1EncodableVector10.add(new DERSet((ASN1Encodable) createSubjectKeyId(engineGetCertificate2.getPublicKey())));
                aSN1EncodableVector8.add(new DERSequence(aSN1EncodableVector10));
                ASN1EncodableVector aSN1EncodableVector11 = new ASN1EncodableVector();
                aSN1EncodableVector11.add(pkcs_9_at_friendlyName);
                aSN1EncodableVector11.add(new DERSet((ASN1Encodable) new DERBMPString(str5)));
                aSN1EncodableVector8.add(new DERSequence(aSN1EncodableVector11));
                aSN1EncodableVector7.add(new SafeBag(certBag, certBag.toASN1Primitive(), new DERSet(aSN1EncodableVector8)));
                hashtable.put(engineGetCertificate2, engineGetCertificate2);
                char[] cArr32 = cArr;
                keys4 = enumeration;
                str2 = str;
            } catch (CertificateEncodingException e2) {
                throw new IOException("Error encoding certificate: " + e2.toString());
            }
        }
        String str6 = str2;
        Enumeration keys5 = this.certs.keys();
        while (keys5.hasMoreElements()) {
            try {
                String str7 = (String) keys5.nextElement();
                Certificate certificate = (Certificate) this.certs.get(str7);
                if (this.keys.get(str7) == null) {
                    aSN1EncodableVector7.add(createSafeBag(str7, certificate));
                    hashtable.put(certificate, certificate);
                }
            } catch (CertificateEncodingException e3) {
                throw new IOException("Error encoding certificate: " + e3.toString());
            }
        }
        Set usedCertificateSet = getUsedCertificateSet();
        Enumeration keys6 = this.chainCerts.keys();
        while (keys6.hasMoreElements()) {
            try {
                Certificate certificate2 = (Certificate) this.chainCerts.get((CertId) keys6.nextElement());
                if (usedCertificateSet.contains(certificate2)) {
                    if (hashtable.get(certificate2) == null) {
                        CertBag certBag2 = new CertBag(x509Certificate, new DEROctetString(certificate2.getEncoded()));
                        ASN1EncodableVector aSN1EncodableVector12 = new ASN1EncodableVector();
                        if (certificate2 instanceof PKCS12BagAttributeCarrier) {
                            PKCS12BagAttributeCarrier pKCS12BagAttributeCarrier3 = (PKCS12BagAttributeCarrier) certificate2;
                            Enumeration bagAttributeKeys3 = pKCS12BagAttributeCarrier3.getBagAttributeKeys();
                            while (bagAttributeKeys3.hasMoreElements()) {
                                ASN1ObjectIdentifier aSN1ObjectIdentifier4 = (ASN1ObjectIdentifier) bagAttributeKeys3.nextElement();
                                if (!aSN1ObjectIdentifier4.equals((ASN1Primitive) PKCSObjectIdentifiers.pkcs_9_at_localKeyId)) {
                                    ASN1EncodableVector aSN1EncodableVector13 = new ASN1EncodableVector();
                                    aSN1EncodableVector13.add(aSN1ObjectIdentifier4);
                                    aSN1EncodableVector13.add(new DERSet(pKCS12BagAttributeCarrier3.getBagAttribute(aSN1ObjectIdentifier4)));
                                    aSN1EncodableVector12.add(new DERSequence(aSN1EncodableVector13));
                                    hashtable = hashtable;
                                }
                            }
                        }
                        Hashtable hashtable2 = hashtable;
                        aSN1EncodableVector7.add(new SafeBag(certBag, certBag2.toASN1Primitive(), new DERSet(aSN1EncodableVector12)));
                        hashtable = hashtable2;
                    }
                }
            } catch (CertificateEncodingException e4) {
                throw new IOException("Error encoding certificate: " + e4.toString());
            }
        }
        ContentInfo contentInfo2 = new ContentInfo(data, new BEROctetString(new AuthenticatedSafe(new ContentInfo[]{new ContentInfo(data, bEROctetString), new ContentInfo(encryptedData, new EncryptedData(data, algorithmIdentifier, new BEROctetString(cryptData(true, algorithmIdentifier, cArr, false, new DERSequence(aSN1EncodableVector7).getEncoded(ASN1Encoding.DER)))).toASN1Primitive())}).getEncoded(z ? ASN1Encoding.DER : str6)));
        byte[] bArr3 = new byte[this.saltLength];
        this.random.nextBytes(bArr3);
        try {
            new Pfx(contentInfo2, new MacData(new DigestInfo(this.macAlgorithm, calculatePbeMac(this.macAlgorithm.getAlgorithm(), bArr3, this.itCount, cArr, false, ((ASN1OctetString) contentInfo2.getContent()).getOctets())), bArr3, this.itCount)).encodeTo(outputStream2, z ? ASN1Encoding.DER : str6);
        } catch (Exception e5) {
            throw new IOException("error constructing MAC: " + e5.toString());
        }
    }

    private static byte[] getDigest(SubjectPublicKeyInfo subjectPublicKeyInfo) {
        Digest createSHA1 = DigestFactory.createSHA1();
        byte[] bArr = new byte[createSHA1.getDigestSize()];
        byte[] bytes = subjectPublicKeyInfo.getPublicKeyData().getBytes();
        createSHA1.update(bytes, 0, bytes.length);
        createSHA1.doFinal(bArr, 0);
        return bArr;
    }

    private Set getUsedCertificateSet() {
        HashSet hashSet = new HashSet();
        Enumeration keys2 = this.keys.keys();
        while (keys2.hasMoreElements()) {
            Certificate[] engineGetCertificateChain = engineGetCertificateChain((String) keys2.nextElement());
            for (int i = 0; i != engineGetCertificateChain.length; i++) {
                hashSet.add(engineGetCertificateChain[i]);
            }
        }
        Enumeration keys3 = this.certs.keys();
        while (keys3.hasMoreElements()) {
            hashSet.add(engineGetCertificate((String) keys3.nextElement()));
        }
        return hashSet;
    }

    private void processKeyBag(SafeBag safeBag) throws IOException {
        PrivateKey privateKey = BouncyCastleProvider.getPrivateKey(PrivateKeyInfo.getInstance(safeBag.getBagValue()));
        PKCS12BagAttributeCarrier pKCS12BagAttributeCarrier = (PKCS12BagAttributeCarrier) privateKey;
        Enumeration objects = safeBag.getBagAttributes().getObjects();
        ASN1OctetString aSN1OctetString = null;
        String str = null;
        while (objects.hasMoreElements()) {
            ASN1Sequence instance = ASN1Sequence.getInstance(objects.nextElement());
            ASN1ObjectIdentifier instance2 = ASN1ObjectIdentifier.getInstance(instance.getObjectAt(0));
            ASN1Set instance3 = ASN1Set.getInstance(instance.getObjectAt(1));
            if (instance3.size() > 0) {
                ASN1Primitive aSN1Primitive = (ASN1Primitive) instance3.getObjectAt(0);
                ASN1Encodable bagAttribute = pKCS12BagAttributeCarrier.getBagAttribute(instance2);
                if (bagAttribute == null) {
                    pKCS12BagAttributeCarrier.setBagAttribute(instance2, aSN1Primitive);
                } else if (!bagAttribute.toASN1Primitive().equals(aSN1Primitive)) {
                    throw new IOException("attempt to add existing attribute with different value");
                }
                if (instance2.equals((ASN1Primitive) pkcs_9_at_friendlyName)) {
                    str = ((ASN1BMPString) aSN1Primitive).getString();
                    this.keys.put(str, privateKey);
                } else if (instance2.equals((ASN1Primitive) pkcs_9_at_localKeyId)) {
                    aSN1OctetString = (ASN1OctetString) aSN1Primitive;
                }
            }
        }
        String str2 = new String(Hex.encode(aSN1OctetString.getOctets()));
        if (str == null) {
            this.keys.put(str2, privateKey);
        } else {
            this.localIds.put(str, str2);
        }
    }

    private boolean processShroudedKeyBag(SafeBag safeBag, char[] cArr, boolean z) throws IOException {
        String str;
        ASN1Primitive aSN1Primitive;
        EncryptedPrivateKeyInfo instance = EncryptedPrivateKeyInfo.getInstance(safeBag.getBagValue());
        PrivateKey unwrapKey = unwrapKey(instance.getEncryptionAlgorithm(), instance.getEncryptedData(), cArr, z);
        ASN1OctetString aSN1OctetString = null;
        if (safeBag.getBagAttributes() != null) {
            Enumeration objects = safeBag.getBagAttributes().getObjects();
            str = null;
            ASN1OctetString aSN1OctetString2 = null;
            while (objects.hasMoreElements()) {
                ASN1Sequence aSN1Sequence = (ASN1Sequence) objects.nextElement();
                ASN1ObjectIdentifier aSN1ObjectIdentifier = (ASN1ObjectIdentifier) aSN1Sequence.getObjectAt(0);
                ASN1Set aSN1Set = (ASN1Set) aSN1Sequence.getObjectAt(1);
                if (aSN1Set.size() > 0) {
                    aSN1Primitive = (ASN1Primitive) aSN1Set.getObjectAt(0);
                    if (unwrapKey instanceof PKCS12BagAttributeCarrier) {
                        PKCS12BagAttributeCarrier pKCS12BagAttributeCarrier = (PKCS12BagAttributeCarrier) unwrapKey;
                        ASN1Encodable bagAttribute = pKCS12BagAttributeCarrier.getBagAttribute(aSN1ObjectIdentifier);
                        if (bagAttribute == null) {
                            pKCS12BagAttributeCarrier.setBagAttribute(aSN1ObjectIdentifier, aSN1Primitive);
                        } else if (!bagAttribute.toASN1Primitive().equals(aSN1Primitive)) {
                            throw new IOException("attempt to add existing attribute with different value");
                        }
                    }
                } else {
                    aSN1Primitive = null;
                }
                if (aSN1ObjectIdentifier.equals((ASN1Primitive) pkcs_9_at_friendlyName)) {
                    str = ((ASN1BMPString) aSN1Primitive).getString();
                    this.keys.put(str, unwrapKey);
                } else if (aSN1ObjectIdentifier.equals((ASN1Primitive) pkcs_9_at_localKeyId)) {
                    aSN1OctetString2 = (ASN1OctetString) aSN1Primitive;
                }
            }
            aSN1OctetString = aSN1OctetString2;
        } else {
            str = null;
        }
        if (aSN1OctetString != null) {
            String str2 = new String(Hex.encode(aSN1OctetString.getOctets()));
            if (str == null) {
                this.keys.put(str2, unwrapKey);
            } else {
                this.localIds.put(str, str2);
            }
            return false;
        }
        this.keys.put("unmarked", unwrapKey);
        return true;
    }

    private int validateIterationCount(BigInteger bigInteger) {
        int intValue = bigInteger.intValue();
        if (intValue >= 0) {
            BigInteger asBigInteger = Properties.asBigInteger(PKCS12_MAX_IT_COUNT_PROPERTY);
            if (asBigInteger == null || asBigInteger.intValue() >= intValue) {
                return intValue;
            }
            throw new IllegalStateException("iteration count " + intValue + " greater than " + asBigInteger.intValue());
        }
        throw new IllegalStateException("negative iteration count found");
    }

    /* access modifiers changed from: protected */
    public byte[] cryptData(boolean z, AlgorithmIdentifier algorithmIdentifier, char[] cArr, boolean z2, byte[] bArr) throws IOException {
        ASN1ObjectIdentifier algorithm = algorithmIdentifier.getAlgorithm();
        int i = z ? 1 : 2;
        if (algorithm.on(PKCSObjectIdentifiers.pkcs_12PbeIds)) {
            PKCS12PBEParams instance = PKCS12PBEParams.getInstance(algorithmIdentifier.getParameters());
            try {
                PBEParameterSpec pBEParameterSpec = new PBEParameterSpec(instance.getIV(), instance.getIterations().intValue());
                PKCS12Key pKCS12Key = new PKCS12Key(cArr, z2);
                Cipher createCipher = this.helper.createCipher(algorithm.getId());
                createCipher.init(i, pKCS12Key, pBEParameterSpec);
                return createCipher.doFinal(bArr);
            } catch (Exception e) {
                throw new IOException("exception decrypting data - " + e.toString());
            }
        } else if (algorithm.equals((ASN1Primitive) PKCSObjectIdentifiers.id_PBES2)) {
            try {
                return createCipher(i, cArr, algorithmIdentifier).doFinal(bArr);
            } catch (Exception e2) {
                throw new IOException("exception decrypting data - " + e2.toString());
            }
        } else {
            throw new IOException("unknown PBE algorithm: " + algorithm);
        }
    }

    public Enumeration engineAliases() {
        Hashtable hashtable = new Hashtable();
        Enumeration keys2 = this.certs.keys();
        while (keys2.hasMoreElements()) {
            hashtable.put(keys2.nextElement(), "cert");
        }
        Enumeration keys3 = this.keys.keys();
        while (keys3.hasMoreElements()) {
            String str = (String) keys3.nextElement();
            if (hashtable.get(str) == null) {
                hashtable.put(str, LeanbackPreferenceDialogFragment.ARG_KEY);
            }
        }
        return hashtable.keys();
    }

    public boolean engineContainsAlias(String str) {
        return (this.certs.get(str) == null && this.keys.get(str) == null) ? false : true;
    }

    public void engineDeleteEntry(String str) throws KeyStoreException {
        String str2;
        Certificate certificate;
        Certificate certificate2 = (Certificate) this.certs.remove(str);
        if (certificate2 != null) {
            this.chainCerts.remove(new CertId(certificate2.getPublicKey()));
        }
        if (((Key) this.keys.remove(str)) != null && (str2 = (String) this.localIds.remove(str)) != null && (certificate = (Certificate) this.keyCerts.remove(str2)) != null) {
            this.chainCerts.remove(new CertId(certificate.getPublicKey()));
        }
    }

    public Certificate engineGetCertificate(String str) {
        if (str != null) {
            Certificate certificate = (Certificate) this.certs.get(str);
            if (certificate != null) {
                return certificate;
            }
            String str2 = (String) this.localIds.get(str);
            return (Certificate) (str2 != null ? this.keyCerts.get(str2) : this.keyCerts.get(str));
        }
        throw new IllegalArgumentException("null alias passed to getCertificate.");
    }

    public String engineGetCertificateAlias(Certificate certificate) {
        Enumeration elements = this.certs.elements();
        Enumeration keys2 = this.certs.keys();
        while (elements.hasMoreElements()) {
            String str = (String) keys2.nextElement();
            if (((Certificate) elements.nextElement()).equals(certificate)) {
                return str;
            }
        }
        Enumeration elements2 = this.keyCerts.elements();
        Enumeration keys3 = this.keyCerts.keys();
        while (elements2.hasMoreElements()) {
            String str2 = (String) keys3.nextElement();
            if (((Certificate) elements2.nextElement()).equals(certificate)) {
                return str2;
            }
        }
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0026, code lost:
        r3 = org.bouncycastle.asn1.x509.AuthorityKeyIdentifier.getInstance(org.bouncycastle.asn1.ASN1OctetString.getInstance(r3).getOctets()).getKeyIdentifier();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.security.cert.Certificate[] engineGetCertificateChain(java.lang.String r9) {
        /*
            r8 = this;
            if (r9 == 0) goto L_0x00a8
            boolean r0 = r8.engineIsKeyEntry(r9)
            r1 = 0
            if (r0 != 0) goto L_0x000a
            return r1
        L_0x000a:
            java.security.cert.Certificate r9 = r8.engineGetCertificate(r9)
            if (r9 == 0) goto L_0x00a7
            java.util.Vector r0 = new java.util.Vector
            r0.<init>()
        L_0x0015:
            if (r9 == 0) goto L_0x0093
            r2 = r9
            java.security.cert.X509Certificate r2 = (java.security.cert.X509Certificate) r2
            org.bouncycastle.asn1.ASN1ObjectIdentifier r3 = org.bouncycastle.asn1.x509.Extension.authorityKeyIdentifier
            java.lang.String r3 = r3.getId()
            byte[] r3 = r2.getExtensionValue(r3)
            if (r3 == 0) goto L_0x0046
            org.bouncycastle.asn1.ASN1OctetString r3 = org.bouncycastle.asn1.ASN1OctetString.getInstance(r3)
            byte[] r3 = r3.getOctets()
            org.bouncycastle.asn1.x509.AuthorityKeyIdentifier r3 = org.bouncycastle.asn1.x509.AuthorityKeyIdentifier.getInstance(r3)
            byte[] r3 = r3.getKeyIdentifier()
            if (r3 == 0) goto L_0x0046
            java.util.Hashtable r4 = r8.chainCerts
            org.bouncycastle.jcajce.provider.keystore.pkcs12.PKCS12KeyStoreSpi$CertId r5 = new org.bouncycastle.jcajce.provider.keystore.pkcs12.PKCS12KeyStoreSpi$CertId
            r5.<init>((byte[]) r3)
            java.lang.Object r3 = r4.get(r5)
            java.security.cert.Certificate r3 = (java.security.cert.Certificate) r3
            goto L_0x0047
        L_0x0046:
            r3 = r1
        L_0x0047:
            if (r3 != 0) goto L_0x0084
            java.security.Principal r4 = r2.getIssuerDN()
            java.security.Principal r5 = r2.getSubjectDN()
            boolean r5 = r4.equals(r5)
            if (r5 != 0) goto L_0x0084
            java.util.Hashtable r5 = r8.chainCerts
            java.util.Enumeration r5 = r5.keys()
        L_0x005d:
            boolean r6 = r5.hasMoreElements()
            if (r6 == 0) goto L_0x0084
            java.util.Hashtable r6 = r8.chainCerts
            java.lang.Object r7 = r5.nextElement()
            java.lang.Object r6 = r6.get(r7)
            java.security.cert.X509Certificate r6 = (java.security.cert.X509Certificate) r6
            java.security.Principal r7 = r6.getSubjectDN()
            boolean r7 = r7.equals(r4)
            if (r7 == 0) goto L_0x005d
            java.security.PublicKey r7 = r6.getPublicKey()     // Catch:{ Exception -> 0x0082 }
            r2.verify(r7)     // Catch:{ Exception -> 0x0082 }
            r3 = r6
            goto L_0x0084
        L_0x0082:
            goto L_0x005d
        L_0x0084:
            boolean r2 = r0.contains(r9)
            if (r2 == 0) goto L_0x008c
        L_0x008a:
            r9 = r1
            goto L_0x0015
        L_0x008c:
            r0.addElement(r9)
            if (r3 == r9) goto L_0x008a
            r9 = r3
            goto L_0x0015
        L_0x0093:
            int r9 = r0.size()
            java.security.cert.Certificate[] r1 = new java.security.cert.Certificate[r9]
            r2 = 0
        L_0x009a:
            if (r2 == r9) goto L_0x00a7
            java.lang.Object r3 = r0.elementAt(r2)
            java.security.cert.Certificate r3 = (java.security.cert.Certificate) r3
            r1[r2] = r3
            int r2 = r2 + 1
            goto L_0x009a
        L_0x00a7:
            return r1
        L_0x00a8:
            java.lang.IllegalArgumentException r9 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "null alias passed to getCertificateChain."
            r9.<init>(r0)
            goto L_0x00b1
        L_0x00b0:
            throw r9
        L_0x00b1:
            goto L_0x00b0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.jcajce.provider.keystore.pkcs12.PKCS12KeyStoreSpi.engineGetCertificateChain(java.lang.String):java.security.cert.Certificate[]");
    }

    public Date engineGetCreationDate(String str) {
        if (str == null) {
            throw new NullPointerException("alias == null");
        } else if (this.keys.get(str) == null && this.certs.get(str) == null) {
            return null;
        } else {
            return new Date();
        }
    }

    public Key engineGetKey(String str, char[] cArr) throws NoSuchAlgorithmException, UnrecoverableKeyException {
        if (str != null) {
            return (Key) this.keys.get(str);
        }
        throw new IllegalArgumentException("null alias passed to getKey.");
    }

    public boolean engineIsCertificateEntry(String str) {
        return this.certs.get(str) != null && this.keys.get(str) == null;
    }

    public boolean engineIsKeyEntry(String str) {
        return this.keys.get(str) != null;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v3, resolved type: org.bouncycastle.asn1.ASN1Primitive} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v9, resolved type: org.bouncycastle.asn1.ASN1OctetString} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v14, resolved type: org.bouncycastle.asn1.ASN1OctetString} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0109  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x0278  */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x0295  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void engineLoad(java.io.InputStream r17, char[] r18) throws java.io.IOException {
        /*
            r16 = this;
            r8 = r16
            r0 = r17
            r9 = r18
            if (r0 != 0) goto L_0x0009
            return
        L_0x0009:
            java.io.BufferedInputStream r1 = new java.io.BufferedInputStream
            r1.<init>(r0)
            r0 = 10
            r1.mark(r0)
            int r0 = r1.read()
            if (r0 < 0) goto L_0x040a
            r2 = 48
            if (r0 != r2) goto L_0x0402
            r1.reset()
            org.bouncycastle.asn1.ASN1InputStream r0 = new org.bouncycastle.asn1.ASN1InputStream
            r0.<init>((java.io.InputStream) r1)
            org.bouncycastle.asn1.ASN1Primitive r0 = r0.readObject()     // Catch:{ Exception -> 0x03f7 }
            org.bouncycastle.asn1.pkcs.Pfx r0 = org.bouncycastle.asn1.pkcs.Pfx.getInstance(r0)     // Catch:{ Exception -> 0x03f7 }
            org.bouncycastle.asn1.pkcs.ContentInfo r10 = r0.getAuthSafe()
            java.util.Vector r11 = new java.util.Vector
            r11.<init>()
            org.bouncycastle.asn1.pkcs.MacData r1 = r0.getMacData()
            r12 = 1
            r13 = 0
            if (r1 == 0) goto L_0x00d7
            if (r9 == 0) goto L_0x00cf
            org.bouncycastle.asn1.pkcs.MacData r0 = r0.getMacData()
            org.bouncycastle.asn1.x509.DigestInfo r14 = r0.getMac()
            org.bouncycastle.asn1.x509.AlgorithmIdentifier r1 = r14.getAlgorithmId()
            r8.macAlgorithm = r1
            byte[] r15 = r0.getSalt()
            java.math.BigInteger r0 = r0.getIterationCount()
            int r0 = r8.validateIterationCount(r0)
            r8.itCount = r0
            int r0 = r15.length
            r8.saltLength = r0
            org.bouncycastle.asn1.ASN1Encodable r0 = r10.getContent()
            org.bouncycastle.asn1.ASN1OctetString r0 = (org.bouncycastle.asn1.ASN1OctetString) r0
            byte[] r0 = r0.getOctets()
            org.bouncycastle.asn1.x509.AlgorithmIdentifier r1 = r8.macAlgorithm     // Catch:{ IOException -> 0x00cd, Exception -> 0x00b4 }
            org.bouncycastle.asn1.ASN1ObjectIdentifier r2 = r1.getAlgorithm()     // Catch:{ IOException -> 0x00cd, Exception -> 0x00b4 }
            int r4 = r8.itCount     // Catch:{ IOException -> 0x00cd, Exception -> 0x00b4 }
            r6 = 0
            r1 = r16
            r3 = r15
            r5 = r18
            r7 = r0
            byte[] r1 = r1.calculatePbeMac(r2, r3, r4, r5, r6, r7)     // Catch:{ IOException -> 0x00cd, Exception -> 0x00b4 }
            byte[] r14 = r14.getDigest()     // Catch:{ IOException -> 0x00cd, Exception -> 0x00b4 }
            boolean r1 = org.bouncycastle.util.Arrays.constantTimeAreEqual((byte[]) r1, (byte[]) r14)     // Catch:{ IOException -> 0x00cd, Exception -> 0x00b4 }
            if (r1 != 0) goto L_0x00ed
            int r1 = r9.length     // Catch:{ IOException -> 0x00cd, Exception -> 0x00b4 }
            java.lang.String r7 = "PKCS12 key store mac invalid - wrong password or corrupted file."
            if (r1 > 0) goto L_0x00ad
            org.bouncycastle.asn1.x509.AlgorithmIdentifier r1 = r8.macAlgorithm     // Catch:{ IOException -> 0x00cd, Exception -> 0x00b4 }
            org.bouncycastle.asn1.ASN1ObjectIdentifier r2 = r1.getAlgorithm()     // Catch:{ IOException -> 0x00cd, Exception -> 0x00b4 }
            int r4 = r8.itCount     // Catch:{ IOException -> 0x00cd, Exception -> 0x00b4 }
            r6 = 1
            r1 = r16
            r3 = r15
            r5 = r18
            r15 = r7
            r7 = r0
            byte[] r0 = r1.calculatePbeMac(r2, r3, r4, r5, r6, r7)     // Catch:{ IOException -> 0x00cd, Exception -> 0x00b4 }
            boolean r0 = org.bouncycastle.util.Arrays.constantTimeAreEqual((byte[]) r0, (byte[]) r14)     // Catch:{ IOException -> 0x00cd, Exception -> 0x00b4 }
            if (r0 == 0) goto L_0x00a7
            r0 = 1
            goto L_0x00ee
        L_0x00a7:
            java.io.IOException r0 = new java.io.IOException     // Catch:{ IOException -> 0x00cd, Exception -> 0x00b4 }
            r0.<init>(r15)     // Catch:{ IOException -> 0x00cd, Exception -> 0x00b4 }
            throw r0     // Catch:{ IOException -> 0x00cd, Exception -> 0x00b4 }
        L_0x00ad:
            r15 = r7
            java.io.IOException r0 = new java.io.IOException     // Catch:{ IOException -> 0x00cd, Exception -> 0x00b4 }
            r0.<init>(r15)     // Catch:{ IOException -> 0x00cd, Exception -> 0x00b4 }
            throw r0     // Catch:{ IOException -> 0x00cd, Exception -> 0x00b4 }
        L_0x00b4:
            r0 = move-exception
            java.io.IOException r1 = new java.io.IOException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "error constructing MAC: "
            r2.<init>(r3)
            java.lang.String r0 = r0.toString()
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            r1.<init>(r0)
            throw r1
        L_0x00cd:
            r0 = move-exception
            throw r0
        L_0x00cf:
            java.lang.NullPointerException r0 = new java.lang.NullPointerException
            java.lang.String r1 = "no password supplied when one expected"
            r0.<init>(r1)
            throw r0
        L_0x00d7:
            if (r9 == 0) goto L_0x00ed
            int r0 = r9.length
            if (r0 == 0) goto L_0x00ed
            java.lang.String r0 = "org.bouncycastle.pkcs12.ignore_useless_passwd"
            boolean r0 = org.bouncycastle.util.Properties.isOverrideSet(r0)
            if (r0 == 0) goto L_0x00e5
            goto L_0x00ed
        L_0x00e5:
            java.io.IOException r0 = new java.io.IOException
            java.lang.String r1 = "password supplied for keystore that does not require one"
            r0.<init>(r1)
            throw r0
        L_0x00ed:
            r0 = 0
        L_0x00ee:
            org.bouncycastle.jcajce.provider.keystore.pkcs12.PKCS12KeyStoreSpi$IgnoresCaseHashtable r1 = new org.bouncycastle.jcajce.provider.keystore.pkcs12.PKCS12KeyStoreSpi$IgnoresCaseHashtable
            r7 = 0
            r1.<init>()
            r8.keys = r1
            org.bouncycastle.jcajce.provider.keystore.pkcs12.PKCS12KeyStoreSpi$IgnoresCaseHashtable r1 = new org.bouncycastle.jcajce.provider.keystore.pkcs12.PKCS12KeyStoreSpi$IgnoresCaseHashtable
            r1.<init>()
            r8.localIds = r1
            org.bouncycastle.asn1.ASN1ObjectIdentifier r1 = r10.getContentType()
            org.bouncycastle.asn1.ASN1ObjectIdentifier r2 = data
            boolean r1 = r1.equals((org.bouncycastle.asn1.ASN1Primitive) r2)
            if (r1 == 0) goto L_0x0278
            org.bouncycastle.asn1.ASN1Encodable r1 = r10.getContent()
            org.bouncycastle.asn1.ASN1OctetString r1 = org.bouncycastle.asn1.ASN1OctetString.getInstance(r1)
            byte[] r1 = r1.getOctets()
            org.bouncycastle.asn1.pkcs.AuthenticatedSafe r1 = org.bouncycastle.asn1.pkcs.AuthenticatedSafe.getInstance(r1)
            org.bouncycastle.asn1.pkcs.ContentInfo[] r10 = r1.getContentInfo()
            r14 = 0
            r15 = 0
        L_0x011f:
            int r1 = r10.length
            if (r14 == r1) goto L_0x0279
            r1 = r10[r14]
            org.bouncycastle.asn1.ASN1ObjectIdentifier r1 = r1.getContentType()
            org.bouncycastle.asn1.ASN1ObjectIdentifier r2 = data
            boolean r1 = r1.equals((org.bouncycastle.asn1.ASN1Primitive) r2)
            if (r1 == 0) goto L_0x01a6
            r1 = r10[r14]
            org.bouncycastle.asn1.ASN1Encodable r1 = r1.getContent()
            org.bouncycastle.asn1.ASN1OctetString r1 = org.bouncycastle.asn1.ASN1OctetString.getInstance(r1)
            byte[] r1 = r1.getOctets()
            org.bouncycastle.asn1.ASN1Sequence r1 = org.bouncycastle.asn1.ASN1Sequence.getInstance(r1)
            r2 = 0
        L_0x0143:
            int r3 = r1.size()
            if (r2 == r3) goto L_0x0274
            org.bouncycastle.asn1.ASN1Encodable r3 = r1.getObjectAt(r2)
            org.bouncycastle.asn1.pkcs.SafeBag r3 = org.bouncycastle.asn1.pkcs.SafeBag.getInstance(r3)
            org.bouncycastle.asn1.ASN1ObjectIdentifier r4 = r3.getBagId()
            org.bouncycastle.asn1.ASN1ObjectIdentifier r5 = pkcs8ShroudedKeyBag
            boolean r4 = r4.equals((org.bouncycastle.asn1.ASN1Primitive) r5)
            if (r4 == 0) goto L_0x0163
            boolean r3 = r8.processShroudedKeyBag(r3, r9, r0)
            r15 = r3
            goto L_0x01a3
        L_0x0163:
            org.bouncycastle.asn1.ASN1ObjectIdentifier r4 = r3.getBagId()
            org.bouncycastle.asn1.ASN1ObjectIdentifier r5 = certBag
            boolean r4 = r4.equals((org.bouncycastle.asn1.ASN1Primitive) r5)
            if (r4 == 0) goto L_0x0173
            r11.addElement(r3)
            goto L_0x01a3
        L_0x0173:
            org.bouncycastle.asn1.ASN1ObjectIdentifier r4 = r3.getBagId()
            org.bouncycastle.asn1.ASN1ObjectIdentifier r5 = keyBag
            boolean r4 = r4.equals((org.bouncycastle.asn1.ASN1Primitive) r5)
            if (r4 == 0) goto L_0x0183
            r8.processKeyBag(r3)
            goto L_0x01a3
        L_0x0183:
            java.io.PrintStream r4 = java.lang.System.out
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r6 = "extra in data "
            r5.<init>(r6)
            org.bouncycastle.asn1.ASN1ObjectIdentifier r6 = r3.getBagId()
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            r4.println(r5)
            java.io.PrintStream r4 = java.lang.System.out
            java.lang.String r3 = org.bouncycastle.asn1.util.ASN1Dump.dumpAsString(r3)
            r4.println(r3)
        L_0x01a3:
            int r2 = r2 + 1
            goto L_0x0143
        L_0x01a6:
            r1 = r10[r14]
            org.bouncycastle.asn1.ASN1ObjectIdentifier r1 = r1.getContentType()
            org.bouncycastle.asn1.ASN1ObjectIdentifier r2 = encryptedData
            boolean r1 = r1.equals((org.bouncycastle.asn1.ASN1Primitive) r2)
            if (r1 == 0) goto L_0x023c
            r1 = r10[r14]
            org.bouncycastle.asn1.ASN1Encodable r1 = r1.getContent()
            org.bouncycastle.asn1.pkcs.EncryptedData r1 = org.bouncycastle.asn1.pkcs.EncryptedData.getInstance(r1)
            org.bouncycastle.asn1.x509.AlgorithmIdentifier r3 = r1.getEncryptionAlgorithm()
            org.bouncycastle.asn1.ASN1OctetString r1 = r1.getContent()
            byte[] r6 = r1.getOctets()
            r2 = 0
            r1 = r16
            r4 = r18
            r5 = r0
            byte[] r1 = r1.cryptData(r2, r3, r4, r5, r6)
            org.bouncycastle.asn1.ASN1Sequence r1 = org.bouncycastle.asn1.ASN1Sequence.getInstance(r1)
            r2 = 0
        L_0x01d9:
            int r3 = r1.size()
            if (r2 == r3) goto L_0x0274
            org.bouncycastle.asn1.ASN1Encodable r3 = r1.getObjectAt(r2)
            org.bouncycastle.asn1.pkcs.SafeBag r3 = org.bouncycastle.asn1.pkcs.SafeBag.getInstance(r3)
            org.bouncycastle.asn1.ASN1ObjectIdentifier r4 = r3.getBagId()
            org.bouncycastle.asn1.ASN1ObjectIdentifier r5 = certBag
            boolean r4 = r4.equals((org.bouncycastle.asn1.ASN1Primitive) r5)
            if (r4 == 0) goto L_0x01f7
            r11.addElement(r3)
            goto L_0x0239
        L_0x01f7:
            org.bouncycastle.asn1.ASN1ObjectIdentifier r4 = r3.getBagId()
            org.bouncycastle.asn1.ASN1ObjectIdentifier r5 = pkcs8ShroudedKeyBag
            boolean r4 = r4.equals((org.bouncycastle.asn1.ASN1Primitive) r5)
            if (r4 == 0) goto L_0x0209
            boolean r3 = r8.processShroudedKeyBag(r3, r9, r0)
            r15 = r3
            goto L_0x0239
        L_0x0209:
            org.bouncycastle.asn1.ASN1ObjectIdentifier r4 = r3.getBagId()
            org.bouncycastle.asn1.ASN1ObjectIdentifier r5 = keyBag
            boolean r4 = r4.equals((org.bouncycastle.asn1.ASN1Primitive) r5)
            if (r4 == 0) goto L_0x0219
            r8.processKeyBag(r3)
            goto L_0x0239
        L_0x0219:
            java.io.PrintStream r4 = java.lang.System.out
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r6 = "extra in encryptedData "
            r5.<init>(r6)
            org.bouncycastle.asn1.ASN1ObjectIdentifier r6 = r3.getBagId()
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            r4.println(r5)
            java.io.PrintStream r4 = java.lang.System.out
            java.lang.String r3 = org.bouncycastle.asn1.util.ASN1Dump.dumpAsString(r3)
            r4.println(r3)
        L_0x0239:
            int r2 = r2 + 1
            goto L_0x01d9
        L_0x023c:
            java.io.PrintStream r1 = java.lang.System.out
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "extra "
            r2.<init>(r3)
            r4 = r10[r14]
            org.bouncycastle.asn1.ASN1ObjectIdentifier r4 = r4.getContentType()
            java.lang.String r4 = r4.getId()
            r2.append(r4)
            java.lang.String r2 = r2.toString()
            r1.println(r2)
            java.io.PrintStream r1 = java.lang.System.out
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>(r3)
            r3 = r10[r14]
            org.bouncycastle.asn1.ASN1Encodable r3 = r3.getContent()
            java.lang.String r3 = org.bouncycastle.asn1.util.ASN1Dump.dumpAsString(r3)
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r1.println(r2)
        L_0x0274:
            int r14 = r14 + 1
            goto L_0x011f
        L_0x0278:
            r15 = 0
        L_0x0279:
            org.bouncycastle.jcajce.provider.keystore.pkcs12.PKCS12KeyStoreSpi$IgnoresCaseHashtable r0 = new org.bouncycastle.jcajce.provider.keystore.pkcs12.PKCS12KeyStoreSpi$IgnoresCaseHashtable
            r0.<init>()
            r8.certs = r0
            java.util.Hashtable r0 = new java.util.Hashtable
            r0.<init>()
            r8.chainCerts = r0
            java.util.Hashtable r0 = new java.util.Hashtable
            r0.<init>()
            r8.keyCerts = r0
            r0 = 0
        L_0x028f:
            int r1 = r11.size()
            if (r0 == r1) goto L_0x03f6
            java.lang.Object r1 = r11.elementAt(r0)
            org.bouncycastle.asn1.pkcs.SafeBag r1 = (org.bouncycastle.asn1.pkcs.SafeBag) r1
            org.bouncycastle.asn1.ASN1Encodable r2 = r1.getBagValue()
            org.bouncycastle.asn1.pkcs.CertBag r2 = org.bouncycastle.asn1.pkcs.CertBag.getInstance(r2)
            org.bouncycastle.asn1.ASN1ObjectIdentifier r3 = r2.getCertId()
            org.bouncycastle.asn1.ASN1ObjectIdentifier r4 = x509Certificate
            boolean r3 = r3.equals((org.bouncycastle.asn1.ASN1Primitive) r4)
            if (r3 == 0) goto L_0x03de
            java.io.ByteArrayInputStream r3 = new java.io.ByteArrayInputStream     // Catch:{ Exception -> 0x03d3 }
            org.bouncycastle.asn1.ASN1Encodable r2 = r2.getCertValue()     // Catch:{ Exception -> 0x03d3 }
            org.bouncycastle.asn1.ASN1OctetString r2 = (org.bouncycastle.asn1.ASN1OctetString) r2     // Catch:{ Exception -> 0x03d3 }
            byte[] r2 = r2.getOctets()     // Catch:{ Exception -> 0x03d3 }
            r3.<init>(r2)     // Catch:{ Exception -> 0x03d3 }
            java.security.cert.CertificateFactory r2 = r8.certFact     // Catch:{ Exception -> 0x03d3 }
            java.security.cert.Certificate r2 = r2.generateCertificate(r3)     // Catch:{ Exception -> 0x03d3 }
            org.bouncycastle.asn1.ASN1Set r3 = r1.getBagAttributes()
            if (r3 == 0) goto L_0x0374
            org.bouncycastle.asn1.ASN1Set r1 = r1.getBagAttributes()
            java.util.Enumeration r1 = r1.getObjects()
            r3 = r7
            r4 = r3
        L_0x02d4:
            boolean r5 = r1.hasMoreElements()
            if (r5 == 0) goto L_0x0376
            java.lang.Object r5 = r1.nextElement()
            org.bouncycastle.asn1.ASN1Sequence r5 = org.bouncycastle.asn1.ASN1Sequence.getInstance(r5)
            org.bouncycastle.asn1.ASN1Encodable r6 = r5.getObjectAt(r13)
            org.bouncycastle.asn1.ASN1ObjectIdentifier r6 = org.bouncycastle.asn1.ASN1ObjectIdentifier.getInstance(r6)
            org.bouncycastle.asn1.ASN1Encodable r5 = r5.getObjectAt(r12)
            org.bouncycastle.asn1.ASN1Set r5 = org.bouncycastle.asn1.ASN1Set.getInstance(r5)
            int r9 = r5.size()
            if (r9 <= 0) goto L_0x02d4
            org.bouncycastle.asn1.ASN1Encodable r9 = r5.getObjectAt(r13)
            org.bouncycastle.asn1.ASN1Primitive r9 = (org.bouncycastle.asn1.ASN1Primitive) r9
            boolean r10 = r2 instanceof org.bouncycastle.jce.interfaces.PKCS12BagAttributeCarrier
            if (r10 == 0) goto L_0x0357
            r10 = r2
            org.bouncycastle.jce.interfaces.PKCS12BagAttributeCarrier r10 = (org.bouncycastle.jce.interfaces.PKCS12BagAttributeCarrier) r10
            org.bouncycastle.asn1.ASN1Encodable r14 = r10.getBagAttribute(r6)
            if (r14 == 0) goto L_0x034a
            org.bouncycastle.asn1.ASN1ObjectIdentifier r5 = pkcs_9_at_localKeyId
            boolean r5 = r6.equals((org.bouncycastle.asn1.ASN1Primitive) r5)
            if (r5 == 0) goto L_0x0337
            r5 = r9
            org.bouncycastle.asn1.ASN1OctetString r5 = (org.bouncycastle.asn1.ASN1OctetString) r5
            byte[] r5 = r5.getOctets()
            java.lang.String r5 = org.bouncycastle.util.encoders.Hex.toHexString(r5)
            org.bouncycastle.jcajce.provider.keystore.pkcs12.PKCS12KeyStoreSpi$IgnoresCaseHashtable r10 = r8.keys
            java.util.Hashtable r10 = r10.keys
            boolean r10 = r10.containsKey(r5)
            if (r10 != 0) goto L_0x0337
            org.bouncycastle.jcajce.provider.keystore.pkcs12.PKCS12KeyStoreSpi$IgnoresCaseHashtable r10 = r8.localIds
            java.util.Hashtable r10 = r10.keys
            boolean r5 = r10.containsKey(r5)
            if (r5 != 0) goto L_0x0337
            goto L_0x02d4
        L_0x0337:
            org.bouncycastle.asn1.ASN1Primitive r5 = r14.toASN1Primitive()
            boolean r5 = r5.equals((org.bouncycastle.asn1.ASN1Primitive) r9)
            if (r5 == 0) goto L_0x0342
            goto L_0x0357
        L_0x0342:
            java.io.IOException r0 = new java.io.IOException
            java.lang.String r1 = "attempt to add existing attribute with different value"
            r0.<init>(r1)
            throw r0
        L_0x034a:
            int r14 = r5.size()
            if (r14 <= r12) goto L_0x0354
            r10.setBagAttribute(r6, r5)
            goto L_0x0357
        L_0x0354:
            r10.setBagAttribute(r6, r9)
        L_0x0357:
            org.bouncycastle.asn1.ASN1ObjectIdentifier r5 = pkcs_9_at_friendlyName
            boolean r5 = r6.equals((org.bouncycastle.asn1.ASN1Primitive) r5)
            if (r5 == 0) goto L_0x0367
            org.bouncycastle.asn1.ASN1BMPString r9 = (org.bouncycastle.asn1.ASN1BMPString) r9
            java.lang.String r3 = r9.getString()
            goto L_0x02d4
        L_0x0367:
            org.bouncycastle.asn1.ASN1ObjectIdentifier r5 = pkcs_9_at_localKeyId
            boolean r5 = r6.equals((org.bouncycastle.asn1.ASN1Primitive) r5)
            if (r5 == 0) goto L_0x02d4
            r4 = r9
            org.bouncycastle.asn1.ASN1OctetString r4 = (org.bouncycastle.asn1.ASN1OctetString) r4
            goto L_0x02d4
        L_0x0374:
            r3 = r7
            r4 = r3
        L_0x0376:
            java.util.Hashtable r1 = r8.chainCerts
            org.bouncycastle.jcajce.provider.keystore.pkcs12.PKCS12KeyStoreSpi$CertId r5 = new org.bouncycastle.jcajce.provider.keystore.pkcs12.PKCS12KeyStoreSpi$CertId
            java.security.PublicKey r6 = r2.getPublicKey()
            r5.<init>((java.security.PublicKey) r6)
            r1.put(r5, r2)
            if (r15 == 0) goto L_0x03b4
            java.util.Hashtable r1 = r8.keyCerts
            boolean r1 = r1.isEmpty()
            if (r1 == 0) goto L_0x03cf
            java.lang.String r1 = new java.lang.String
            java.security.PublicKey r3 = r2.getPublicKey()
            org.bouncycastle.asn1.x509.SubjectKeyIdentifier r3 = r8.createSubjectKeyId(r3)
            byte[] r3 = r3.getKeyIdentifier()
            byte[] r3 = org.bouncycastle.util.encoders.Hex.encode(r3)
            r1.<init>(r3)
            java.util.Hashtable r3 = r8.keyCerts
            r3.put(r1, r2)
            org.bouncycastle.jcajce.provider.keystore.pkcs12.PKCS12KeyStoreSpi$IgnoresCaseHashtable r2 = r8.keys
            java.lang.String r3 = "unmarked"
            java.lang.Object r3 = r2.remove(r3)
            r2.put(r1, r3)
            goto L_0x03cf
        L_0x03b4:
            if (r4 == 0) goto L_0x03c8
            java.lang.String r1 = new java.lang.String
            byte[] r4 = r4.getOctets()
            byte[] r4 = org.bouncycastle.util.encoders.Hex.encode(r4)
            r1.<init>(r4)
            java.util.Hashtable r4 = r8.keyCerts
            r4.put(r1, r2)
        L_0x03c8:
            if (r3 == 0) goto L_0x03cf
            org.bouncycastle.jcajce.provider.keystore.pkcs12.PKCS12KeyStoreSpi$IgnoresCaseHashtable r1 = r8.certs
            r1.put(r3, r2)
        L_0x03cf:
            int r0 = r0 + 1
            goto L_0x028f
        L_0x03d3:
            r0 = move-exception
            java.lang.RuntimeException r1 = new java.lang.RuntimeException
            java.lang.String r0 = r0.toString()
            r1.<init>(r0)
            throw r1
        L_0x03de:
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r3 = "Unsupported certificate type: "
            r1.<init>(r3)
            org.bouncycastle.asn1.ASN1ObjectIdentifier r2 = r2.getCertId()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x03f6:
            return
        L_0x03f7:
            r0 = move-exception
            java.io.IOException r1 = new java.io.IOException
            java.lang.String r0 = r0.getMessage()
            r1.<init>(r0)
            throw r1
        L_0x0402:
            java.io.IOException r0 = new java.io.IOException
            java.lang.String r1 = "stream does not represent a PKCS12 key store"
            r0.<init>(r1)
            throw r0
        L_0x040a:
            java.io.EOFException r0 = new java.io.EOFException
            java.lang.String r1 = "no data in keystore stream"
            r0.<init>(r1)
            goto L_0x0413
        L_0x0412:
            throw r0
        L_0x0413:
            goto L_0x0412
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.jcajce.provider.keystore.pkcs12.PKCS12KeyStoreSpi.engineLoad(java.io.InputStream, char[]):void");
    }

    public void engineLoad(KeyStore.LoadStoreParameter loadStoreParameter) throws IOException, NoSuchAlgorithmException, CertificateException {
        if (loadStoreParameter == null) {
            engineLoad((InputStream) null, (char[]) null);
        } else if (loadStoreParameter instanceof BCLoadStoreParameter) {
            engineLoad(((BCLoadStoreParameter) loadStoreParameter).getInputStream(), ParameterUtil.extractPassword(loadStoreParameter));
        } else {
            throw new IllegalArgumentException("no support for 'param' of type " + loadStoreParameter.getClass().getName());
        }
    }

    public boolean engineProbe(InputStream inputStream) throws IOException {
        return false;
    }

    public void engineSetCertificateEntry(String str, Certificate certificate) throws KeyStoreException {
        if (this.keys.get(str) == null) {
            this.certs.put(str, certificate);
            this.chainCerts.put(new CertId(certificate.getPublicKey()), certificate);
            return;
        }
        throw new KeyStoreException("There is a key entry with the name " + str + ".");
    }

    public void engineSetKeyEntry(String str, Key key, char[] cArr, Certificate[] certificateArr) throws KeyStoreException {
        boolean z = key instanceof PrivateKey;
        if (!z) {
            throw new KeyStoreException("PKCS12 does not support non-PrivateKeys");
        } else if (!z || certificateArr != null) {
            if (this.keys.get(str) != null) {
                engineDeleteEntry(str);
            }
            this.keys.put(str, key);
            if (certificateArr != null) {
                this.certs.put(str, certificateArr[0]);
                for (int i = 0; i != certificateArr.length; i++) {
                    this.chainCerts.put(new CertId(certificateArr[i].getPublicKey()), certificateArr[i]);
                }
            }
        } else {
            throw new KeyStoreException("no certificate chain for private key");
        }
    }

    public void engineSetKeyEntry(String str, byte[] bArr, Certificate[] certificateArr) throws KeyStoreException {
        throw new RuntimeException("operation not supported");
    }

    public int engineSize() {
        Hashtable hashtable = new Hashtable();
        Enumeration keys2 = this.certs.keys();
        while (keys2.hasMoreElements()) {
            hashtable.put(keys2.nextElement(), "cert");
        }
        Enumeration keys3 = this.keys.keys();
        while (keys3.hasMoreElements()) {
            String str = (String) keys3.nextElement();
            if (hashtable.get(str) == null) {
                hashtable.put(str, LeanbackPreferenceDialogFragment.ARG_KEY);
            }
        }
        return hashtable.size();
    }

    public void engineStore(OutputStream outputStream, char[] cArr) throws IOException {
        doStore(outputStream, cArr, false);
    }

    public void engineStore(KeyStore.LoadStoreParameter loadStoreParameter) throws IOException, NoSuchAlgorithmException, CertificateException {
        PKCS12StoreParameter pKCS12StoreParameter;
        char[] cArr;
        if (loadStoreParameter != null) {
            boolean z = loadStoreParameter instanceof PKCS12StoreParameter;
            if (z || (loadStoreParameter instanceof JDKPKCS12StoreParameter)) {
                if (z) {
                    pKCS12StoreParameter = (PKCS12StoreParameter) loadStoreParameter;
                } else {
                    JDKPKCS12StoreParameter jDKPKCS12StoreParameter = (JDKPKCS12StoreParameter) loadStoreParameter;
                    pKCS12StoreParameter = new PKCS12StoreParameter(jDKPKCS12StoreParameter.getOutputStream(), loadStoreParameter.getProtectionParameter(), jDKPKCS12StoreParameter.isUseDEREncoding());
                }
                KeyStore.ProtectionParameter protectionParameter = loadStoreParameter.getProtectionParameter();
                if (protectionParameter == null) {
                    cArr = null;
                } else if (protectionParameter instanceof KeyStore.PasswordProtection) {
                    cArr = ((KeyStore.PasswordProtection) protectionParameter).getPassword();
                } else {
                    throw new IllegalArgumentException("No support for protection parameter of type " + protectionParameter.getClass().getName());
                }
                doStore(pKCS12StoreParameter.getOutputStream(), cArr, pKCS12StoreParameter.isForDEREncoding());
                return;
            }
            throw new IllegalArgumentException("No support for 'param' of type " + loadStoreParameter.getClass().getName());
        }
        throw new IllegalArgumentException("'param' arg cannot be null");
    }

    public void setRandom(SecureRandom secureRandom) {
        this.random = secureRandom;
    }

    /* access modifiers changed from: protected */
    public PrivateKey unwrapKey(AlgorithmIdentifier algorithmIdentifier, byte[] bArr, char[] cArr, boolean z) throws IOException {
        ASN1ObjectIdentifier algorithm = algorithmIdentifier.getAlgorithm();
        try {
            if (algorithm.on(PKCSObjectIdentifiers.pkcs_12PbeIds)) {
                PKCS12PBEParams instance = PKCS12PBEParams.getInstance(algorithmIdentifier.getParameters());
                PBEParameterSpec pBEParameterSpec = new PBEParameterSpec(instance.getIV(), validateIterationCount(instance.getIterations()));
                Cipher createCipher = this.helper.createCipher(algorithm.getId());
                createCipher.init(4, new PKCS12Key(cArr, z), pBEParameterSpec);
                return (PrivateKey) createCipher.unwrap(bArr, "", 2);
            } else if (algorithm.equals((ASN1Primitive) PKCSObjectIdentifiers.id_PBES2)) {
                return (PrivateKey) createCipher(4, cArr, algorithmIdentifier).unwrap(bArr, "", 2);
            } else {
                throw new IOException("exception unwrapping private key - cannot recognise: " + algorithm);
            }
        } catch (Exception e) {
            throw new IOException("exception unwrapping private key - " + e.toString());
        }
    }

    /* access modifiers changed from: protected */
    public byte[] wrapKey(String str, Key key, PKCS12PBEParams pKCS12PBEParams, char[] cArr) throws IOException {
        PBEKeySpec pBEKeySpec = new PBEKeySpec(cArr);
        try {
            SecretKeyFactory createSecretKeyFactory = this.helper.createSecretKeyFactory(str);
            PBEParameterSpec pBEParameterSpec = new PBEParameterSpec(pKCS12PBEParams.getIV(), pKCS12PBEParams.getIterations().intValue());
            Cipher createCipher = this.helper.createCipher(str);
            createCipher.init(3, createSecretKeyFactory.generateSecret(pBEKeySpec), pBEParameterSpec);
            return createCipher.wrap(key);
        } catch (Exception e) {
            throw new IOException("exception encrypting data - " + e.toString());
        }
    }
}
