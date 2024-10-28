package org.bouncycastle.cert.crmf.jcajce;

import java.io.IOException;
import java.security.AlgorithmParameterGenerator;
import java.security.AlgorithmParameters;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.InvalidParameterSpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.RC2ParameterSpec;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Null;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.iana.IANAObjectIdentifiers;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.asn1.x9.X9ObjectIdentifiers;
import org.bouncycastle.cert.crmf.CRMFException;
import org.bouncycastle.cms.CMSAlgorithm;
import org.bouncycastle.jcajce.util.AlgorithmParametersUtils;
import org.bouncycastle.jcajce.util.JcaJceHelper;

class CRMFHelper {
    protected static final Map BASE_CIPHER_NAMES;
    protected static final Map CIPHER_ALG_NAMES;
    protected static final Map DIGEST_ALG_NAMES;
    protected static final Map KEY_ALG_NAMES;
    protected static final Map MAC_ALG_NAMES;
    private JcaJceHelper helper;

    interface JCECallback {
        Object doInJCE() throws CRMFException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidParameterSpecException, NoSuchAlgorithmException, NoSuchPaddingException, NoSuchProviderException;
    }

    static {
        HashMap hashMap = new HashMap();
        BASE_CIPHER_NAMES = hashMap;
        HashMap hashMap2 = new HashMap();
        CIPHER_ALG_NAMES = hashMap2;
        HashMap hashMap3 = new HashMap();
        DIGEST_ALG_NAMES = hashMap3;
        HashMap hashMap4 = new HashMap();
        KEY_ALG_NAMES = hashMap4;
        HashMap hashMap5 = new HashMap();
        MAC_ALG_NAMES = hashMap5;
        hashMap.put(PKCSObjectIdentifiers.des_EDE3_CBC, "DESEDE");
        hashMap.put(NISTObjectIdentifiers.id_aes128_CBC, "AES");
        hashMap.put(NISTObjectIdentifiers.id_aes192_CBC, "AES");
        hashMap.put(NISTObjectIdentifiers.id_aes256_CBC, "AES");
        hashMap2.put(CMSAlgorithm.DES_EDE3_CBC, "DESEDE/CBC/PKCS5Padding");
        hashMap2.put(CMSAlgorithm.AES128_CBC, "AES/CBC/PKCS5Padding");
        hashMap2.put(CMSAlgorithm.AES192_CBC, "AES/CBC/PKCS5Padding");
        hashMap2.put(CMSAlgorithm.AES256_CBC, "AES/CBC/PKCS5Padding");
        hashMap2.put(new ASN1ObjectIdentifier(PKCSObjectIdentifiers.rsaEncryption.getId()), "RSA/ECB/PKCS1Padding");
        hashMap3.put(OIWObjectIdentifiers.idSHA1, "SHA1");
        hashMap3.put(NISTObjectIdentifiers.id_sha224, "SHA224");
        hashMap3.put(NISTObjectIdentifiers.id_sha256, "SHA256");
        hashMap3.put(NISTObjectIdentifiers.id_sha384, "SHA384");
        hashMap3.put(NISTObjectIdentifiers.id_sha512, "SHA512");
        hashMap5.put(IANAObjectIdentifiers.hmacSHA1, "HMACSHA1");
        hashMap5.put(PKCSObjectIdentifiers.id_hmacWithSHA1, "HMACSHA1");
        hashMap5.put(PKCSObjectIdentifiers.id_hmacWithSHA224, "HMACSHA224");
        hashMap5.put(PKCSObjectIdentifiers.id_hmacWithSHA256, "HMACSHA256");
        hashMap5.put(PKCSObjectIdentifiers.id_hmacWithSHA384, "HMACSHA384");
        hashMap5.put(PKCSObjectIdentifiers.id_hmacWithSHA512, "HMACSHA512");
        hashMap4.put(PKCSObjectIdentifiers.rsaEncryption, "RSA");
        hashMap4.put(X9ObjectIdentifiers.id_dsa, "DSA");
    }

    CRMFHelper(JcaJceHelper jcaJceHelper) {
        this.helper = jcaJceHelper;
    }

    static Object execute(JCECallback jCECallback) throws CRMFException {
        try {
            return jCECallback.doInJCE();
        } catch (NoSuchAlgorithmException e) {
            throw new CRMFException("can't find algorithm.", e);
        } catch (InvalidKeyException e2) {
            throw new CRMFException("key invalid in message.", e2);
        } catch (NoSuchProviderException e3) {
            throw new CRMFException("can't find provider.", e3);
        } catch (NoSuchPaddingException e4) {
            throw new CRMFException("required padding not supported.", e4);
        } catch (InvalidAlgorithmParameterException e5) {
            throw new CRMFException("algorithm parameters invalid.", e5);
        } catch (InvalidParameterSpecException e6) {
            throw new CRMFException("MAC algorithm parameter spec invalid.", e6);
        }
    }

    /* access modifiers changed from: package-private */
    public AlgorithmParameterGenerator createAlgorithmParameterGenerator(ASN1ObjectIdentifier aSN1ObjectIdentifier) throws GeneralSecurityException {
        String str = (String) BASE_CIPHER_NAMES.get(aSN1ObjectIdentifier);
        if (str != null) {
            try {
                return this.helper.createAlgorithmParameterGenerator(str);
            } catch (NoSuchAlgorithmException unused) {
            }
        }
        return this.helper.createAlgorithmParameterGenerator(aSN1ObjectIdentifier.getId());
    }

    /* access modifiers changed from: package-private */
    public AlgorithmParameters createAlgorithmParameters(ASN1ObjectIdentifier aSN1ObjectIdentifier) throws NoSuchAlgorithmException, NoSuchProviderException {
        String str = (String) BASE_CIPHER_NAMES.get(aSN1ObjectIdentifier);
        if (str != null) {
            try {
                return this.helper.createAlgorithmParameters(str);
            } catch (NoSuchAlgorithmException unused) {
            }
        }
        return this.helper.createAlgorithmParameters(aSN1ObjectIdentifier.getId());
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x0011 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public javax.crypto.Cipher createCipher(org.bouncycastle.asn1.ASN1ObjectIdentifier r4) throws org.bouncycastle.cert.crmf.CRMFException {
        /*
            r3 = this;
            java.util.Map r0 = CIPHER_ALG_NAMES     // Catch:{ GeneralSecurityException -> 0x001c }
            java.lang.Object r0 = r0.get(r4)     // Catch:{ GeneralSecurityException -> 0x001c }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ GeneralSecurityException -> 0x001c }
            if (r0 == 0) goto L_0x0011
            org.bouncycastle.jcajce.util.JcaJceHelper r1 = r3.helper     // Catch:{ NoSuchAlgorithmException -> 0x0011 }
            javax.crypto.Cipher r4 = r1.createCipher(r0)     // Catch:{ NoSuchAlgorithmException -> 0x0011 }
            return r4
        L_0x0011:
            org.bouncycastle.jcajce.util.JcaJceHelper r0 = r3.helper     // Catch:{ GeneralSecurityException -> 0x001c }
            java.lang.String r4 = r4.getId()     // Catch:{ GeneralSecurityException -> 0x001c }
            javax.crypto.Cipher r4 = r0.createCipher(r4)     // Catch:{ GeneralSecurityException -> 0x001c }
            return r4
        L_0x001c:
            r4 = move-exception
            org.bouncycastle.cert.crmf.CRMFException r0 = new org.bouncycastle.cert.crmf.CRMFException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "cannot create cipher: "
            r1.<init>(r2)
            java.lang.String r2 = r4.getMessage()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1, r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.cert.crmf.jcajce.CRMFHelper.createCipher(org.bouncycastle.asn1.ASN1ObjectIdentifier):javax.crypto.Cipher");
    }

    /* access modifiers changed from: package-private */
    public Cipher createContentCipher(final Key key, final AlgorithmIdentifier algorithmIdentifier) throws CRMFException {
        return (Cipher) execute(new JCECallback() {
            public Object doInJCE() throws CRMFException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidParameterSpecException, NoSuchAlgorithmException, NoSuchPaddingException, NoSuchProviderException {
                Cipher createCipher = CRMFHelper.this.createCipher(algorithmIdentifier.getAlgorithm());
                ASN1Primitive aSN1Primitive = (ASN1Primitive) algorithmIdentifier.getParameters();
                ASN1ObjectIdentifier algorithm = algorithmIdentifier.getAlgorithm();
                if (aSN1Primitive != null && !(aSN1Primitive instanceof ASN1Null)) {
                    try {
                        AlgorithmParameters createAlgorithmParameters = CRMFHelper.this.createAlgorithmParameters(algorithmIdentifier.getAlgorithm());
                        AlgorithmParametersUtils.loadParameters(createAlgorithmParameters, aSN1Primitive);
                        createCipher.init(2, key, createAlgorithmParameters);
                    } catch (IOException e) {
                        throw new CRMFException("error decoding algorithm parameters.", e);
                    } catch (NoSuchAlgorithmException e2) {
                        if (algorithm.equals((ASN1Primitive) CMSAlgorithm.DES_EDE3_CBC) || algorithm.equals((ASN1Primitive) CMSAlgorithm.IDEA_CBC) || algorithm.equals((ASN1Primitive) CMSAlgorithm.AES128_CBC) || algorithm.equals((ASN1Primitive) CMSAlgorithm.AES192_CBC) || algorithm.equals((ASN1Primitive) CMSAlgorithm.AES256_CBC)) {
                            createCipher.init(2, key, new IvParameterSpec(ASN1OctetString.getInstance(aSN1Primitive).getOctets()));
                        } else {
                            throw e2;
                        }
                    }
                } else if (algorithm.equals((ASN1Primitive) CMSAlgorithm.DES_EDE3_CBC) || algorithm.equals((ASN1Primitive) CMSAlgorithm.IDEA_CBC) || algorithm.equals((ASN1Primitive) CMSAlgorithm.CAST5_CBC)) {
                    createCipher.init(2, key, new IvParameterSpec(new byte[8]));
                } else {
                    createCipher.init(2, key);
                }
                return createCipher;
            }
        });
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x0011 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.security.MessageDigest createDigest(org.bouncycastle.asn1.ASN1ObjectIdentifier r4) throws org.bouncycastle.cert.crmf.CRMFException {
        /*
            r3 = this;
            java.util.Map r0 = DIGEST_ALG_NAMES     // Catch:{ GeneralSecurityException -> 0x001c }
            java.lang.Object r0 = r0.get(r4)     // Catch:{ GeneralSecurityException -> 0x001c }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ GeneralSecurityException -> 0x001c }
            if (r0 == 0) goto L_0x0011
            org.bouncycastle.jcajce.util.JcaJceHelper r1 = r3.helper     // Catch:{ NoSuchAlgorithmException -> 0x0011 }
            java.security.MessageDigest r4 = r1.createMessageDigest(r0)     // Catch:{ NoSuchAlgorithmException -> 0x0011 }
            return r4
        L_0x0011:
            org.bouncycastle.jcajce.util.JcaJceHelper r0 = r3.helper     // Catch:{ GeneralSecurityException -> 0x001c }
            java.lang.String r4 = r4.getId()     // Catch:{ GeneralSecurityException -> 0x001c }
            java.security.MessageDigest r4 = r0.createMessageDigest(r4)     // Catch:{ GeneralSecurityException -> 0x001c }
            return r4
        L_0x001c:
            r4 = move-exception
            org.bouncycastle.cert.crmf.CRMFException r0 = new org.bouncycastle.cert.crmf.CRMFException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "cannot create cipher: "
            r1.<init>(r2)
            java.lang.String r2 = r4.getMessage()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1, r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.cert.crmf.jcajce.CRMFHelper.createDigest(org.bouncycastle.asn1.ASN1ObjectIdentifier):java.security.MessageDigest");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x0011 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.security.KeyFactory createKeyFactory(org.bouncycastle.asn1.ASN1ObjectIdentifier r4) throws org.bouncycastle.cert.crmf.CRMFException {
        /*
            r3 = this;
            java.util.Map r0 = KEY_ALG_NAMES     // Catch:{ GeneralSecurityException -> 0x001c }
            java.lang.Object r0 = r0.get(r4)     // Catch:{ GeneralSecurityException -> 0x001c }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ GeneralSecurityException -> 0x001c }
            if (r0 == 0) goto L_0x0011
            org.bouncycastle.jcajce.util.JcaJceHelper r1 = r3.helper     // Catch:{ NoSuchAlgorithmException -> 0x0011 }
            java.security.KeyFactory r4 = r1.createKeyFactory(r0)     // Catch:{ NoSuchAlgorithmException -> 0x0011 }
            return r4
        L_0x0011:
            org.bouncycastle.jcajce.util.JcaJceHelper r0 = r3.helper     // Catch:{ GeneralSecurityException -> 0x001c }
            java.lang.String r4 = r4.getId()     // Catch:{ GeneralSecurityException -> 0x001c }
            java.security.KeyFactory r4 = r0.createKeyFactory(r4)     // Catch:{ GeneralSecurityException -> 0x001c }
            return r4
        L_0x001c:
            r4 = move-exception
            org.bouncycastle.cert.crmf.CRMFException r0 = new org.bouncycastle.cert.crmf.CRMFException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "cannot create cipher: "
            r1.<init>(r2)
            java.lang.String r2 = r4.getMessage()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1, r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.cert.crmf.jcajce.CRMFHelper.createKeyFactory(org.bouncycastle.asn1.ASN1ObjectIdentifier):java.security.KeyFactory");
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x0011 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public javax.crypto.KeyGenerator createKeyGenerator(org.bouncycastle.asn1.ASN1ObjectIdentifier r4) throws org.bouncycastle.cert.crmf.CRMFException {
        /*
            r3 = this;
            java.util.Map r0 = BASE_CIPHER_NAMES     // Catch:{ GeneralSecurityException -> 0x001c }
            java.lang.Object r0 = r0.get(r4)     // Catch:{ GeneralSecurityException -> 0x001c }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ GeneralSecurityException -> 0x001c }
            if (r0 == 0) goto L_0x0011
            org.bouncycastle.jcajce.util.JcaJceHelper r1 = r3.helper     // Catch:{ NoSuchAlgorithmException -> 0x0011 }
            javax.crypto.KeyGenerator r4 = r1.createKeyGenerator(r0)     // Catch:{ NoSuchAlgorithmException -> 0x0011 }
            return r4
        L_0x0011:
            org.bouncycastle.jcajce.util.JcaJceHelper r0 = r3.helper     // Catch:{ GeneralSecurityException -> 0x001c }
            java.lang.String r4 = r4.getId()     // Catch:{ GeneralSecurityException -> 0x001c }
            javax.crypto.KeyGenerator r4 = r0.createKeyGenerator(r4)     // Catch:{ GeneralSecurityException -> 0x001c }
            return r4
        L_0x001c:
            r4 = move-exception
            org.bouncycastle.cert.crmf.CRMFException r0 = new org.bouncycastle.cert.crmf.CRMFException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "cannot create key generator: "
            r1.<init>(r2)
            java.lang.String r2 = r4.getMessage()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1, r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.cert.crmf.jcajce.CRMFHelper.createKeyGenerator(org.bouncycastle.asn1.ASN1ObjectIdentifier):javax.crypto.KeyGenerator");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x0011 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public javax.crypto.Mac createMac(org.bouncycastle.asn1.ASN1ObjectIdentifier r4) throws org.bouncycastle.cert.crmf.CRMFException {
        /*
            r3 = this;
            java.util.Map r0 = MAC_ALG_NAMES     // Catch:{ GeneralSecurityException -> 0x001c }
            java.lang.Object r0 = r0.get(r4)     // Catch:{ GeneralSecurityException -> 0x001c }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ GeneralSecurityException -> 0x001c }
            if (r0 == 0) goto L_0x0011
            org.bouncycastle.jcajce.util.JcaJceHelper r1 = r3.helper     // Catch:{ NoSuchAlgorithmException -> 0x0011 }
            javax.crypto.Mac r4 = r1.createMac(r0)     // Catch:{ NoSuchAlgorithmException -> 0x0011 }
            return r4
        L_0x0011:
            org.bouncycastle.jcajce.util.JcaJceHelper r0 = r3.helper     // Catch:{ GeneralSecurityException -> 0x001c }
            java.lang.String r4 = r4.getId()     // Catch:{ GeneralSecurityException -> 0x001c }
            javax.crypto.Mac r4 = r0.createMac(r4)     // Catch:{ GeneralSecurityException -> 0x001c }
            return r4
        L_0x001c:
            r4 = move-exception
            org.bouncycastle.cert.crmf.CRMFException r0 = new org.bouncycastle.cert.crmf.CRMFException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "cannot create mac: "
            r1.<init>(r2)
            java.lang.String r2 = r4.getMessage()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1, r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.cert.crmf.jcajce.CRMFHelper.createMac(org.bouncycastle.asn1.ASN1ObjectIdentifier):javax.crypto.Mac");
    }

    /* access modifiers changed from: package-private */
    public AlgorithmParameters generateParameters(ASN1ObjectIdentifier aSN1ObjectIdentifier, SecretKey secretKey, SecureRandom secureRandom) throws CRMFException {
        try {
            AlgorithmParameterGenerator createAlgorithmParameterGenerator = createAlgorithmParameterGenerator(aSN1ObjectIdentifier);
            if (aSN1ObjectIdentifier.equals((ASN1Primitive) CMSAlgorithm.RC2_CBC)) {
                byte[] bArr = new byte[8];
                secureRandom.nextBytes(bArr);
                createAlgorithmParameterGenerator.init(new RC2ParameterSpec(secretKey.getEncoded().length * 8, bArr), secureRandom);
            }
            return createAlgorithmParameterGenerator.generateParameters();
        } catch (InvalidAlgorithmParameterException e) {
            throw new CRMFException("parameters generation error: " + e, e);
        } catch (NoSuchAlgorithmException unused) {
            return null;
        } catch (GeneralSecurityException e2) {
            throw new CRMFException("exception creating algorithm parameter generator: " + e2, e2);
        }
    }

    /* access modifiers changed from: package-private */
    public AlgorithmIdentifier getAlgorithmIdentifier(ASN1ObjectIdentifier aSN1ObjectIdentifier, AlgorithmParameters algorithmParameters) throws CRMFException {
        ASN1Encodable aSN1Encodable;
        if (algorithmParameters != null) {
            try {
                aSN1Encodable = AlgorithmParametersUtils.extractParameters(algorithmParameters);
            } catch (IOException e) {
                throw new CRMFException("cannot encode parameters: " + e.getMessage(), e);
            }
        } else {
            aSN1Encodable = DERNull.INSTANCE;
        }
        return new AlgorithmIdentifier(aSN1ObjectIdentifier, aSN1Encodable);
    }

    /* access modifiers changed from: package-private */
    public PublicKey toPublicKey(SubjectPublicKeyInfo subjectPublicKeyInfo) throws CRMFException {
        try {
            return createKeyFactory(subjectPublicKeyInfo.getAlgorithm().getAlgorithm()).generatePublic(new X509EncodedKeySpec(subjectPublicKeyInfo.getEncoded()));
        } catch (Exception e) {
            throw new CRMFException("invalid key: " + e.getMessage(), e);
        }
    }
}
