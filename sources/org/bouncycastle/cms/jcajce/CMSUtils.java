package org.bouncycastle.cms.jcajce;

import java.io.IOException;
import java.security.AlgorithmParameters;
import java.security.Key;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.cms.IssuerAndSerialNumber;
import org.bouncycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
import org.bouncycastle.asn1.iso.ISOIECObjectIdentifiers;
import org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.rosstandart.RosstandartObjectIdentifiers;
import org.bouncycastle.asn1.sec.SECObjectIdentifiers;
import org.bouncycastle.asn1.x509.Certificate;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x9.X9ObjectIdentifiers;
import org.bouncycastle.cms.CMSAlgorithm;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.jcajce.util.AlgorithmParametersUtils;
import org.bouncycastle.jcajce.util.AnnotatedPrivateKey;
import org.bouncycastle.operator.GenericKey;

class CMSUtils {
    private static final Map asymmetricWrapperAlgNames;
    private static final Set ecAlgs;
    private static final Set gostAlgs;
    private static final Set mqvAlgs;
    private static Map<ASN1ObjectIdentifier, String> wrapAlgNames;

    static {
        HashSet hashSet = new HashSet();
        mqvAlgs = hashSet;
        HashSet hashSet2 = new HashSet();
        ecAlgs = hashSet2;
        HashSet hashSet3 = new HashSet();
        gostAlgs = hashSet3;
        HashMap hashMap = new HashMap();
        asymmetricWrapperAlgNames = hashMap;
        HashMap hashMap2 = new HashMap();
        wrapAlgNames = hashMap2;
        hashMap2.put(CMSAlgorithm.AES128_WRAP, "AESWRAP");
        wrapAlgNames.put(CMSAlgorithm.AES192_WRAP, "AESWRAP");
        wrapAlgNames.put(CMSAlgorithm.AES256_WRAP, "AESWRAP");
        hashSet.add(X9ObjectIdentifiers.mqvSinglePass_sha1kdf_scheme);
        hashSet.add(SECObjectIdentifiers.mqvSinglePass_sha224kdf_scheme);
        hashSet.add(SECObjectIdentifiers.mqvSinglePass_sha256kdf_scheme);
        hashSet.add(SECObjectIdentifiers.mqvSinglePass_sha384kdf_scheme);
        hashSet.add(SECObjectIdentifiers.mqvSinglePass_sha512kdf_scheme);
        hashSet2.add(X9ObjectIdentifiers.dhSinglePass_cofactorDH_sha1kdf_scheme);
        hashSet2.add(X9ObjectIdentifiers.dhSinglePass_stdDH_sha1kdf_scheme);
        hashSet2.add(SECObjectIdentifiers.dhSinglePass_cofactorDH_sha224kdf_scheme);
        hashSet2.add(SECObjectIdentifiers.dhSinglePass_stdDH_sha224kdf_scheme);
        hashSet2.add(SECObjectIdentifiers.dhSinglePass_cofactorDH_sha256kdf_scheme);
        hashSet2.add(SECObjectIdentifiers.dhSinglePass_stdDH_sha256kdf_scheme);
        hashSet2.add(SECObjectIdentifiers.dhSinglePass_cofactorDH_sha384kdf_scheme);
        hashSet2.add(SECObjectIdentifiers.dhSinglePass_stdDH_sha384kdf_scheme);
        hashSet2.add(SECObjectIdentifiers.dhSinglePass_cofactorDH_sha512kdf_scheme);
        hashSet2.add(SECObjectIdentifiers.dhSinglePass_stdDH_sha512kdf_scheme);
        hashSet3.add(CryptoProObjectIdentifiers.gostR3410_2001_CryptoPro_ESDH);
        hashSet3.add(CryptoProObjectIdentifiers.gostR3410_2001);
        hashSet3.add(RosstandartObjectIdentifiers.id_tc26_agreement_gost_3410_12_256);
        hashSet3.add(RosstandartObjectIdentifiers.id_tc26_agreement_gost_3410_12_512);
        hashSet3.add(RosstandartObjectIdentifiers.id_tc26_gost_3410_12_256);
        hashSet3.add(RosstandartObjectIdentifiers.id_tc26_gost_3410_12_512);
        hashMap.put(PKCSObjectIdentifiers.rsaEncryption, "RSA/ECB/PKCS1Padding");
        hashMap.put(OIWObjectIdentifiers.elGamalAlgorithm, "Elgamal/ECB/PKCS1Padding");
        hashMap.put(PKCSObjectIdentifiers.id_RSAES_OAEP, "RSA/ECB/OAEPPadding");
        hashMap.put(CryptoProObjectIdentifiers.gostR3410_2001, "ECGOST3410");
        hashMap.put(ISOIECObjectIdentifiers.id_kem_rsa, "RSA-KTS-KEM-KWS");
    }

    CMSUtils() {
    }

    static PrivateKey cleanPrivateKey(PrivateKey privateKey) {
        return privateKey instanceof AnnotatedPrivateKey ? cleanPrivateKey(((AnnotatedPrivateKey) privateKey).getKey()) : privateKey;
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:20:0x002e */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static javax.crypto.Cipher createAsymmetricWrapper(org.bouncycastle.jcajce.util.JcaJceHelper r1, org.bouncycastle.asn1.ASN1ObjectIdentifier r2, java.util.Map r3) throws org.bouncycastle.operator.OperatorCreationException {
        /*
            boolean r0 = r3.isEmpty()     // Catch:{ GeneralSecurityException -> 0x0037 }
            if (r0 != 0) goto L_0x000d
            java.lang.Object r3 = r3.get(r2)     // Catch:{ GeneralSecurityException -> 0x0037 }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ GeneralSecurityException -> 0x0037 }
            goto L_0x000e
        L_0x000d:
            r3 = 0
        L_0x000e:
            if (r3 != 0) goto L_0x0018
            java.util.Map r3 = asymmetricWrapperAlgNames     // Catch:{ GeneralSecurityException -> 0x0037 }
            java.lang.Object r3 = r3.get(r2)     // Catch:{ GeneralSecurityException -> 0x0037 }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ GeneralSecurityException -> 0x0037 }
        L_0x0018:
            if (r3 == 0) goto L_0x002e
            javax.crypto.Cipher r1 = r1.createCipher(r3)     // Catch:{ NoSuchAlgorithmException -> 0x001f }
            return r1
        L_0x001f:
            java.lang.String r0 = "RSA/ECB/PKCS1Padding"
            boolean r3 = r3.equals(r0)     // Catch:{ GeneralSecurityException -> 0x0037 }
            if (r3 == 0) goto L_0x002e
            java.lang.String r3 = "RSA/NONE/PKCS1Padding"
            javax.crypto.Cipher r1 = r1.createCipher(r3)     // Catch:{ NoSuchAlgorithmException -> 0x002e }
            return r1
        L_0x002e:
            java.lang.String r2 = r2.getId()     // Catch:{ GeneralSecurityException -> 0x0037 }
            javax.crypto.Cipher r1 = r1.createCipher(r2)     // Catch:{ GeneralSecurityException -> 0x0037 }
            return r1
        L_0x0037:
            r1 = move-exception
            org.bouncycastle.operator.OperatorCreationException r2 = new org.bouncycastle.operator.OperatorCreationException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r0 = "cannot create cipher: "
            r3.<init>(r0)
            java.lang.String r0 = r1.getMessage()
            r3.append(r0)
            java.lang.String r3 = r3.toString()
            r2.<init>(r3, r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.cms.jcajce.CMSUtils.createAsymmetricWrapper(org.bouncycastle.jcajce.util.JcaJceHelper, org.bouncycastle.asn1.ASN1ObjectIdentifier, java.util.Map):javax.crypto.Cipher");
    }

    static EnvelopedDataHelper createContentHelper(String str) {
        return str != null ? new EnvelopedDataHelper(new NamedJcaJceExtHelper(str)) : new EnvelopedDataHelper(new DefaultJcaJceExtHelper());
    }

    static EnvelopedDataHelper createContentHelper(Provider provider) {
        return provider != null ? new EnvelopedDataHelper(new ProviderJcaJceExtHelper(provider)) : new EnvelopedDataHelper(new DefaultJcaJceExtHelper());
    }

    static ASN1Encodable extractParameters(AlgorithmParameters algorithmParameters) throws CMSException {
        try {
            return AlgorithmParametersUtils.extractParameters(algorithmParameters);
        } catch (IOException e) {
            throw new CMSException("cannot extract parameters: " + e.getMessage(), e);
        }
    }

    static IssuerAndSerialNumber getIssuerAndSerialNumber(X509Certificate x509Certificate) throws CertificateEncodingException {
        return new IssuerAndSerialNumber(Certificate.getInstance(x509Certificate.getEncoded()).getIssuer(), x509Certificate.getSerialNumber());
    }

    static Key getJceKey(GenericKey genericKey) {
        if (genericKey.getRepresentation() instanceof Key) {
            return (Key) genericKey.getRepresentation();
        }
        if (genericKey.getRepresentation() instanceof byte[]) {
            return new SecretKeySpec((byte[]) genericKey.getRepresentation(), "ENC");
        }
        throw new IllegalArgumentException("unknown generic key type");
    }

    public static int getKekSize(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        if (aSN1ObjectIdentifier.equals((ASN1Primitive) CMSAlgorithm.AES256_WRAP)) {
            return 32;
        }
        if (aSN1ObjectIdentifier.equals((ASN1Primitive) CMSAlgorithm.AES128_WRAP)) {
            return 16;
        }
        if (aSN1ObjectIdentifier.equals((ASN1Primitive) CMSAlgorithm.AES192_WRAP)) {
            return 24;
        }
        throw new IllegalArgumentException("unknown wrap algorithm");
    }

    static byte[] getSubjectKeyId(X509Certificate x509Certificate) {
        byte[] extensionValue = x509Certificate.getExtensionValue(Extension.subjectKeyIdentifier.getId());
        if (extensionValue != null) {
            return ASN1OctetString.getInstance(ASN1OctetString.getInstance(extensionValue).getOctets()).getOctets();
        }
        return null;
    }

    static String getWrapAlgorithmName(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return wrapAlgNames.get(aSN1ObjectIdentifier);
    }

    static boolean isEC(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return ecAlgs.contains(aSN1ObjectIdentifier);
    }

    static boolean isGOST(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return gostAlgs.contains(aSN1ObjectIdentifier);
    }

    static boolean isMQV(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return mqvAlgs.contains(aSN1ObjectIdentifier);
    }

    static boolean isRFC2631(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return aSN1ObjectIdentifier.equals((ASN1Primitive) PKCSObjectIdentifiers.id_alg_ESDH) || aSN1ObjectIdentifier.equals((ASN1Primitive) PKCSObjectIdentifiers.id_alg_SSDH);
    }

    static void loadParameters(AlgorithmParameters algorithmParameters, ASN1Encodable aSN1Encodable) throws CMSException {
        try {
            AlgorithmParametersUtils.loadParameters(algorithmParameters, aSN1Encodable);
        } catch (IOException e) {
            throw new CMSException("error encoding algorithm parameters.", e);
        }
    }
}
