package org.bouncycastle.operator.jcajce;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.AlgorithmParameters;
import java.security.GeneralSecurityException;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PSSParameterSpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
import org.bouncycastle.asn1.kisa.KISAObjectIdentifiers;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.ntt.NTTObjectIdentifiers;
import org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.RSASSAPSSparams;
import org.bouncycastle.asn1.teletrust.TeleTrusTObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.jcajce.util.AlgorithmParametersUtils;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.jcajce.util.MessageDigestUtils;
import org.bouncycastle.operator.DefaultSignatureNameFinder;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.util.Integers;

class OperatorHelper {
    private static final Map asymmetricWrapperAlgNames;
    private static final Map oids;
    private static DefaultSignatureNameFinder sigFinder = new DefaultSignatureNameFinder();
    private static final Map symmetricKeyAlgNames;
    private static final Map symmetricWrapperAlgNames;
    private static final Map symmetricWrapperKeySizes;
    private JcaJceHelper helper;

    private static class OpCertificateException extends CertificateException {
        private Throwable cause;

        public OpCertificateException(String str, Throwable th) {
            super(str);
            this.cause = th;
        }

        public Throwable getCause() {
            return this.cause;
        }
    }

    static {
        HashMap hashMap = new HashMap();
        oids = hashMap;
        HashMap hashMap2 = new HashMap();
        asymmetricWrapperAlgNames = hashMap2;
        HashMap hashMap3 = new HashMap();
        symmetricWrapperAlgNames = hashMap3;
        HashMap hashMap4 = new HashMap();
        symmetricKeyAlgNames = hashMap4;
        HashMap hashMap5 = new HashMap();
        symmetricWrapperKeySizes = hashMap5;
        hashMap.put(OIWObjectIdentifiers.idSHA1, "SHA1");
        hashMap.put(NISTObjectIdentifiers.id_sha224, "SHA224");
        hashMap.put(NISTObjectIdentifiers.id_sha256, "SHA256");
        hashMap.put(NISTObjectIdentifiers.id_sha384, "SHA384");
        hashMap.put(NISTObjectIdentifiers.id_sha512, "SHA512");
        hashMap.put(TeleTrusTObjectIdentifiers.ripemd128, "RIPEMD128");
        hashMap.put(TeleTrusTObjectIdentifiers.ripemd160, "RIPEMD160");
        hashMap.put(TeleTrusTObjectIdentifiers.ripemd256, "RIPEMD256");
        hashMap2.put(PKCSObjectIdentifiers.rsaEncryption, "RSA/ECB/PKCS1Padding");
        hashMap2.put(OIWObjectIdentifiers.elGamalAlgorithm, "Elgamal/ECB/PKCS1Padding");
        hashMap2.put(PKCSObjectIdentifiers.id_RSAES_OAEP, "RSA/ECB/OAEPPadding");
        hashMap2.put(CryptoProObjectIdentifiers.gostR3410_2001, "ECGOST3410");
        hashMap3.put(PKCSObjectIdentifiers.id_alg_CMS3DESwrap, "DESEDEWrap");
        hashMap3.put(PKCSObjectIdentifiers.id_alg_CMSRC2wrap, "RC2Wrap");
        hashMap3.put(NISTObjectIdentifiers.id_aes128_wrap, "AESWrap");
        hashMap3.put(NISTObjectIdentifiers.id_aes192_wrap, "AESWrap");
        hashMap3.put(NISTObjectIdentifiers.id_aes256_wrap, "AESWrap");
        hashMap3.put(NTTObjectIdentifiers.id_camellia128_wrap, "CamelliaWrap");
        hashMap3.put(NTTObjectIdentifiers.id_camellia192_wrap, "CamelliaWrap");
        hashMap3.put(NTTObjectIdentifiers.id_camellia256_wrap, "CamelliaWrap");
        hashMap3.put(KISAObjectIdentifiers.id_npki_app_cmsSeed_wrap, "SEEDWrap");
        hashMap3.put(PKCSObjectIdentifiers.des_EDE3_CBC, "DESede");
        hashMap5.put(PKCSObjectIdentifiers.id_alg_CMS3DESwrap, Integers.valueOf(192));
        hashMap5.put(NISTObjectIdentifiers.id_aes128_wrap, Integers.valueOf(128));
        hashMap5.put(NISTObjectIdentifiers.id_aes192_wrap, Integers.valueOf(192));
        hashMap5.put(NISTObjectIdentifiers.id_aes256_wrap, Integers.valueOf(256));
        hashMap5.put(NTTObjectIdentifiers.id_camellia128_wrap, Integers.valueOf(128));
        hashMap5.put(NTTObjectIdentifiers.id_camellia192_wrap, Integers.valueOf(192));
        hashMap5.put(NTTObjectIdentifiers.id_camellia256_wrap, Integers.valueOf(256));
        hashMap5.put(KISAObjectIdentifiers.id_npki_app_cmsSeed_wrap, Integers.valueOf(128));
        hashMap5.put(PKCSObjectIdentifiers.des_EDE3_CBC, Integers.valueOf(192));
        hashMap4.put(NISTObjectIdentifiers.aes, "AES");
        hashMap4.put(NISTObjectIdentifiers.id_aes128_CBC, "AES");
        hashMap4.put(NISTObjectIdentifiers.id_aes192_CBC, "AES");
        hashMap4.put(NISTObjectIdentifiers.id_aes256_CBC, "AES");
        hashMap4.put(PKCSObjectIdentifiers.des_EDE3_CBC, "DESede");
        hashMap4.put(PKCSObjectIdentifiers.RC2_CBC, "RC2");
    }

    OperatorHelper(JcaJceHelper jcaJceHelper) {
        this.helper = jcaJceHelper;
    }

    static String getDigestName(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        String digestName = MessageDigestUtils.getDigestName(aSN1ObjectIdentifier);
        int indexOf = digestName.indexOf(45);
        if (indexOf <= 0 || digestName.startsWith("SHA3")) {
            return digestName;
        }
        return digestName.substring(0, indexOf) + digestName.substring(indexOf + 1);
    }

    private static String getSignatureName(AlgorithmIdentifier algorithmIdentifier) {
        return sigFinder.getAlgorithmName(algorithmIdentifier);
    }

    private boolean notDefaultPSSParams(ASN1Sequence aSN1Sequence) throws GeneralSecurityException {
        if (aSN1Sequence == null || aSN1Sequence.size() == 0) {
            return false;
        }
        RSASSAPSSparams instance = RSASSAPSSparams.getInstance(aSN1Sequence);
        if (!instance.getMaskGenAlgorithm().getAlgorithm().equals((ASN1Primitive) PKCSObjectIdentifiers.id_mgf1) || !instance.getHashAlgorithm().equals(AlgorithmIdentifier.getInstance(instance.getMaskGenAlgorithm().getParameters()))) {
            return true;
        }
        return instance.getSaltLength().intValue() != createDigest(instance.getHashAlgorithm()).getDigestLength();
    }

    public X509Certificate convertCertificate(X509CertificateHolder x509CertificateHolder) throws CertificateException {
        try {
            return (X509Certificate) this.helper.createCertificateFactory("X.509").generateCertificate(new ByteArrayInputStream(x509CertificateHolder.getEncoded()));
        } catch (IOException e) {
            throw new OpCertificateException("cannot get encoded form of certificate: " + e.getMessage(), e);
        } catch (NoSuchProviderException e2) {
            throw new OpCertificateException("cannot find factory provider: " + e2.getMessage(), e2);
        }
    }

    public PublicKey convertPublicKey(SubjectPublicKeyInfo subjectPublicKeyInfo) throws OperatorCreationException {
        try {
            return this.helper.createKeyFactory(subjectPublicKeyInfo.getAlgorithm().getAlgorithm().getId()).generatePublic(new X509EncodedKeySpec(subjectPublicKeyInfo.getEncoded()));
        } catch (IOException e) {
            throw new OperatorCreationException("cannot get encoded form of key: " + e.getMessage(), e);
        } catch (NoSuchAlgorithmException e2) {
            throw new OperatorCreationException("cannot create key factory: " + e2.getMessage(), e2);
        } catch (NoSuchProviderException e3) {
            throw new OperatorCreationException("cannot find factory provider: " + e3.getMessage(), e3);
        } catch (InvalidKeySpecException e4) {
            throw new OperatorCreationException("cannot create key factory: " + e4.getMessage(), e4);
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0040 A[SYNTHETIC, Splitter:B:13:0x0040] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.security.AlgorithmParameters createAlgorithmParameters(org.bouncycastle.asn1.x509.AlgorithmIdentifier r5) throws org.bouncycastle.operator.OperatorCreationException {
        /*
            r4 = this;
            org.bouncycastle.asn1.ASN1ObjectIdentifier r0 = r5.getAlgorithm()
            org.bouncycastle.asn1.ASN1ObjectIdentifier r1 = org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers.rsaEncryption
            boolean r0 = r0.equals((org.bouncycastle.asn1.ASN1Primitive) r1)
            r1 = 0
            if (r0 == 0) goto L_0x000e
            return r1
        L_0x000e:
            org.bouncycastle.asn1.ASN1ObjectIdentifier r0 = r5.getAlgorithm()
            org.bouncycastle.asn1.ASN1ObjectIdentifier r2 = org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers.id_RSAES_OAEP
            boolean r0 = r0.equals((org.bouncycastle.asn1.ASN1Primitive) r2)
            java.lang.String r2 = "cannot create algorithm parameters: "
            if (r0 == 0) goto L_0x003d
            org.bouncycastle.jcajce.util.JcaJceHelper r0 = r4.helper     // Catch:{ NoSuchAlgorithmException -> 0x003c, NoSuchProviderException -> 0x0025 }
            java.lang.String r3 = "OAEP"
            java.security.AlgorithmParameters r0 = r0.createAlgorithmParameters(r3)     // Catch:{ NoSuchAlgorithmException -> 0x003c, NoSuchProviderException -> 0x0025 }
            goto L_0x003e
        L_0x0025:
            r5 = move-exception
            org.bouncycastle.operator.OperatorCreationException r0 = new org.bouncycastle.operator.OperatorCreationException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>(r2)
            java.lang.String r2 = r5.getMessage()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1, r5)
            throw r0
        L_0x003c:
        L_0x003d:
            r0 = r1
        L_0x003e:
            if (r0 != 0) goto L_0x0067
            org.bouncycastle.jcajce.util.JcaJceHelper r0 = r4.helper     // Catch:{ NoSuchAlgorithmException -> 0x0066, NoSuchProviderException -> 0x004f }
            org.bouncycastle.asn1.ASN1ObjectIdentifier r3 = r5.getAlgorithm()     // Catch:{ NoSuchAlgorithmException -> 0x0066, NoSuchProviderException -> 0x004f }
            java.lang.String r3 = r3.getId()     // Catch:{ NoSuchAlgorithmException -> 0x0066, NoSuchProviderException -> 0x004f }
            java.security.AlgorithmParameters r0 = r0.createAlgorithmParameters(r3)     // Catch:{ NoSuchAlgorithmException -> 0x0066, NoSuchProviderException -> 0x004f }
            goto L_0x0067
        L_0x004f:
            r5 = move-exception
            org.bouncycastle.operator.OperatorCreationException r0 = new org.bouncycastle.operator.OperatorCreationException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>(r2)
            java.lang.String r2 = r5.getMessage()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1, r5)
            throw r0
        L_0x0066:
            return r1
        L_0x0067:
            org.bouncycastle.asn1.ASN1Encodable r5 = r5.getParameters()     // Catch:{ IOException -> 0x0077 }
            org.bouncycastle.asn1.ASN1Primitive r5 = r5.toASN1Primitive()     // Catch:{ IOException -> 0x0077 }
            byte[] r5 = r5.getEncoded()     // Catch:{ IOException -> 0x0077 }
            r0.init(r5)     // Catch:{ IOException -> 0x0077 }
            return r0
        L_0x0077:
            r5 = move-exception
            org.bouncycastle.operator.OperatorCreationException r0 = new org.bouncycastle.operator.OperatorCreationException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "cannot initialise algorithm parameters: "
            r1.<init>(r2)
            java.lang.String r2 = r5.getMessage()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1, r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.operator.jcajce.OperatorHelper.createAlgorithmParameters(org.bouncycastle.asn1.x509.AlgorithmIdentifier):java.security.AlgorithmParameters");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x0032 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public javax.crypto.Cipher createAsymmetricWrapper(org.bouncycastle.asn1.ASN1ObjectIdentifier r3, java.util.Map r4) throws org.bouncycastle.operator.OperatorCreationException {
        /*
            r2 = this;
            boolean r0 = r4.isEmpty()     // Catch:{ GeneralSecurityException -> 0x003d }
            if (r0 != 0) goto L_0x000d
            java.lang.Object r4 = r4.get(r3)     // Catch:{ GeneralSecurityException -> 0x003d }
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ GeneralSecurityException -> 0x003d }
            goto L_0x000e
        L_0x000d:
            r4 = 0
        L_0x000e:
            if (r4 != 0) goto L_0x0018
            java.util.Map r4 = asymmetricWrapperAlgNames     // Catch:{ GeneralSecurityException -> 0x003d }
            java.lang.Object r4 = r4.get(r3)     // Catch:{ GeneralSecurityException -> 0x003d }
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ GeneralSecurityException -> 0x003d }
        L_0x0018:
            if (r4 == 0) goto L_0x0032
            org.bouncycastle.jcajce.util.JcaJceHelper r0 = r2.helper     // Catch:{ NoSuchAlgorithmException -> 0x0021 }
            javax.crypto.Cipher r3 = r0.createCipher(r4)     // Catch:{ NoSuchAlgorithmException -> 0x0021 }
            return r3
        L_0x0021:
            java.lang.String r0 = "RSA/ECB/PKCS1Padding"
            boolean r4 = r4.equals(r0)     // Catch:{ GeneralSecurityException -> 0x003d }
            if (r4 == 0) goto L_0x0032
            org.bouncycastle.jcajce.util.JcaJceHelper r4 = r2.helper     // Catch:{ NoSuchAlgorithmException -> 0x0032 }
            java.lang.String r0 = "RSA/NONE/PKCS1Padding"
            javax.crypto.Cipher r3 = r4.createCipher(r0)     // Catch:{ NoSuchAlgorithmException -> 0x0032 }
            return r3
        L_0x0032:
            org.bouncycastle.jcajce.util.JcaJceHelper r4 = r2.helper     // Catch:{ GeneralSecurityException -> 0x003d }
            java.lang.String r3 = r3.getId()     // Catch:{ GeneralSecurityException -> 0x003d }
            javax.crypto.Cipher r3 = r4.createCipher(r3)     // Catch:{ GeneralSecurityException -> 0x003d }
            return r3
        L_0x003d:
            r3 = move-exception
            org.bouncycastle.operator.OperatorCreationException r4 = new org.bouncycastle.operator.OperatorCreationException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "cannot create cipher: "
            r0.<init>(r1)
            java.lang.String r1 = r3.getMessage()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r4.<init>(r0, r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.operator.jcajce.OperatorHelper.createAsymmetricWrapper(org.bouncycastle.asn1.ASN1ObjectIdentifier, java.util.Map):javax.crypto.Cipher");
    }

    /* access modifiers changed from: package-private */
    public Cipher createCipher(ASN1ObjectIdentifier aSN1ObjectIdentifier) throws OperatorCreationException {
        try {
            return this.helper.createCipher(aSN1ObjectIdentifier.getId());
        } catch (GeneralSecurityException e) {
            throw new OperatorCreationException("cannot create cipher: " + e.getMessage(), e);
        }
    }

    /* access modifiers changed from: package-private */
    public MessageDigest createDigest(AlgorithmIdentifier algorithmIdentifier) throws GeneralSecurityException {
        JcaJceHelper jcaJceHelper;
        String digestName;
        try {
            if (algorithmIdentifier.getAlgorithm().equals((ASN1Primitive) NISTObjectIdentifiers.id_shake256_len)) {
                jcaJceHelper = this.helper;
                digestName = "SHAKE256-" + ASN1Integer.getInstance(algorithmIdentifier.getParameters()).getValue();
            } else if (algorithmIdentifier.getAlgorithm().equals((ASN1Primitive) NISTObjectIdentifiers.id_shake128_len)) {
                return this.helper.createMessageDigest("SHAKE128-" + ASN1Integer.getInstance(algorithmIdentifier.getParameters()).getValue());
            } else {
                jcaJceHelper = this.helper;
                digestName = MessageDigestUtils.getDigestName(algorithmIdentifier.getAlgorithm());
            }
            return jcaJceHelper.createMessageDigest(digestName);
        } catch (NoSuchAlgorithmException e) {
            Map map = oids;
            if (map.get(algorithmIdentifier.getAlgorithm()) != null) {
                return this.helper.createMessageDigest((String) map.get(algorithmIdentifier.getAlgorithm()));
            }
            throw e;
        }
    }

    /* access modifiers changed from: package-private */
    public KeyAgreement createKeyAgreement(ASN1ObjectIdentifier aSN1ObjectIdentifier) throws OperatorCreationException {
        try {
            return this.helper.createKeyAgreement(aSN1ObjectIdentifier.getId());
        } catch (GeneralSecurityException e) {
            throw new OperatorCreationException("cannot create key agreement: " + e.getMessage(), e);
        }
    }

    /* access modifiers changed from: package-private */
    public KeyPairGenerator createKeyPairGenerator(ASN1ObjectIdentifier aSN1ObjectIdentifier) throws CMSException {
        try {
            return this.helper.createKeyPairGenerator(aSN1ObjectIdentifier.getId());
        } catch (GeneralSecurityException e) {
            throw new CMSException("cannot create key agreement: " + e.getMessage(), e);
        }
    }

    /* access modifiers changed from: package-private */
    public Signature createRawSignature(AlgorithmIdentifier algorithmIdentifier) {
        try {
            String signatureName = getSignatureName(algorithmIdentifier);
            String str = "NONE" + signatureName.substring(signatureName.indexOf("WITH"));
            Signature createSignature = this.helper.createSignature(str);
            if (algorithmIdentifier.getAlgorithm().equals((ASN1Primitive) PKCSObjectIdentifiers.id_RSASSA_PSS)) {
                AlgorithmParameters createAlgorithmParameters = this.helper.createAlgorithmParameters(str);
                AlgorithmParametersUtils.loadParameters(createAlgorithmParameters, algorithmIdentifier.getParameters());
                createSignature.setParameter((PSSParameterSpec) createAlgorithmParameters.getParameterSpec(PSSParameterSpec.class));
            }
            return createSignature;
        } catch (Exception unused) {
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public Signature createSignature(AlgorithmIdentifier algorithmIdentifier) throws GeneralSecurityException {
        Signature signature;
        String signatureName = getSignatureName(algorithmIdentifier);
        try {
            signature = this.helper.createSignature(signatureName);
        } catch (NoSuchAlgorithmException e) {
            if (signatureName.endsWith("WITHRSAANDMGF1")) {
                signature = this.helper.createSignature(signatureName.substring(0, signatureName.indexOf(87)) + "WITHRSASSA-PSS");
            } else {
                throw e;
            }
        }
        if (algorithmIdentifier.getAlgorithm().equals((ASN1Primitive) PKCSObjectIdentifiers.id_RSASSA_PSS)) {
            ASN1Sequence instance = ASN1Sequence.getInstance(algorithmIdentifier.getParameters());
            if (notDefaultPSSParams(instance)) {
                try {
                    AlgorithmParameters createAlgorithmParameters = this.helper.createAlgorithmParameters("PSS");
                    createAlgorithmParameters.init(instance.getEncoded());
                    signature.setParameter(createAlgorithmParameters.getParameterSpec(PSSParameterSpec.class));
                } catch (IOException e2) {
                    throw new GeneralSecurityException("unable to process PSS parameters: " + e2.getMessage());
                }
            }
        }
        return signature;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x0011 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public javax.crypto.Cipher createSymmetricWrapper(org.bouncycastle.asn1.ASN1ObjectIdentifier r4) throws org.bouncycastle.operator.OperatorCreationException {
        /*
            r3 = this;
            java.util.Map r0 = symmetricWrapperAlgNames     // Catch:{ GeneralSecurityException -> 0x001c }
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
            org.bouncycastle.operator.OperatorCreationException r0 = new org.bouncycastle.operator.OperatorCreationException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "cannot create cipher: "
            r1.<init>(r2)
            java.lang.String r2 = r4.getMessage()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1, r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.operator.jcajce.OperatorHelper.createSymmetricWrapper(org.bouncycastle.asn1.ASN1ObjectIdentifier):javax.crypto.Cipher");
    }

    /* access modifiers changed from: package-private */
    public String getKeyAlgorithmName(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        String str = (String) symmetricKeyAlgNames.get(aSN1ObjectIdentifier);
        return str != null ? str : aSN1ObjectIdentifier.getId();
    }

    /* access modifiers changed from: package-private */
    public int getKeySizeInBits(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return ((Integer) symmetricWrapperKeySizes.get(aSN1ObjectIdentifier)).intValue();
    }

    /* access modifiers changed from: package-private */
    public String getWrappingAlgorithmName(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return (String) symmetricWrapperAlgNames.get(aSN1ObjectIdentifier);
    }
}
