package org.bouncycastle.cms.jcajce;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Provider;
import java.util.HashSet;
import java.util.Set;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import org.bouncycastle.asn1.ASN1Encoding;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.cms.ecc.ECCCMSSharedInfo;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x9.X9ObjectIdentifiers;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.KeyAgreeRecipient;
import org.bouncycastle.operator.DefaultSecretKeySizeProvider;
import org.bouncycastle.operator.SecretKeySizeProvider;
import org.bouncycastle.util.Pack;

public abstract class JceKeyAgreeRecipient implements KeyAgreeRecipient {
    private static KeyMaterialGenerator ecc_cms_Generator = new RFC5753KeyMaterialGenerator();
    private static KeyMaterialGenerator old_ecc_cms_Generator = new KeyMaterialGenerator() {
        public byte[] generateKDFMaterial(AlgorithmIdentifier algorithmIdentifier, int i, byte[] bArr) {
            try {
                return new ECCCMSSharedInfo(new AlgorithmIdentifier(algorithmIdentifier.getAlgorithm(), DERNull.INSTANCE), bArr, Pack.intToBigEndian(i)).getEncoded(ASN1Encoding.DER);
            } catch (IOException e) {
                throw new IllegalStateException("Unable to create KDF material: " + e);
            }
        }
    };
    private static final Set possibleOldMessages;
    private static KeyMaterialGenerator simple_ecc_cmsGenerator = new KeyMaterialGenerator() {
        public byte[] generateKDFMaterial(AlgorithmIdentifier algorithmIdentifier, int i, byte[] bArr) {
            return bArr;
        }
    };
    protected EnvelopedDataHelper contentHelper;
    protected EnvelopedDataHelper helper;
    private SecretKeySizeProvider keySizeProvider = new DefaultSecretKeySizeProvider();
    private AlgorithmIdentifier privKeyAlgID = null;
    private PrivateKey recipientKey;

    static {
        HashSet hashSet = new HashSet();
        possibleOldMessages = hashSet;
        hashSet.add(X9ObjectIdentifiers.dhSinglePass_stdDH_sha1kdf_scheme);
        hashSet.add(X9ObjectIdentifiers.mqvSinglePass_sha1kdf_scheme);
    }

    public JceKeyAgreeRecipient(PrivateKey privateKey) {
        EnvelopedDataHelper envelopedDataHelper = new EnvelopedDataHelper(new DefaultJcaJceExtHelper());
        this.helper = envelopedDataHelper;
        this.contentHelper = envelopedDataHelper;
        this.recipientKey = CMSUtils.cleanPrivateKey(privateKey);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v6, resolved type: byte[]} */
    /* JADX WARNING: type inference failed for: r2v0 */
    /* JADX WARNING: type inference failed for: r2v1, types: [java.security.spec.AlgorithmParameterSpec] */
    /* JADX WARNING: type inference failed for: r2v10 */
    /* JADX WARNING: type inference failed for: r2v11 */
    /* JADX WARNING: type inference failed for: r2v12 */
    /* JADX WARNING: type inference failed for: r2v13 */
    /* JADX WARNING: type inference failed for: r2v16 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private javax.crypto.SecretKey calculateAgreedWrapKey(org.bouncycastle.asn1.x509.AlgorithmIdentifier r6, org.bouncycastle.asn1.x509.AlgorithmIdentifier r7, java.security.PublicKey r8, org.bouncycastle.asn1.ASN1OctetString r9, java.security.PrivateKey r10, org.bouncycastle.cms.jcajce.KeyMaterialGenerator r11) throws org.bouncycastle.cms.CMSException, java.security.GeneralSecurityException, java.io.IOException {
        /*
            r5 = this;
            java.security.PrivateKey r10 = org.bouncycastle.cms.jcajce.CMSUtils.cleanPrivateKey(r10)
            org.bouncycastle.asn1.ASN1ObjectIdentifier r0 = r6.getAlgorithm()
            boolean r0 = org.bouncycastle.cms.jcajce.CMSUtils.isMQV(r0)
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L_0x007e
            byte[] r9 = r9.getOctets()
            org.bouncycastle.asn1.cms.ecc.MQVuserKeyingMaterial r9 = org.bouncycastle.asn1.cms.ecc.MQVuserKeyingMaterial.getInstance(r9)
            org.bouncycastle.asn1.x509.SubjectPublicKeyInfo r0 = new org.bouncycastle.asn1.x509.SubjectPublicKeyInfo
            org.bouncycastle.asn1.x509.AlgorithmIdentifier r3 = r5.getPrivateKeyAlgorithmIdentifier()
            org.bouncycastle.asn1.cms.OriginatorPublicKey r4 = r9.getEphemeralPublicKey()
            org.bouncycastle.asn1.ASN1BitString r4 = r4.getPublicKeyData()
            r0.<init>((org.bouncycastle.asn1.x509.AlgorithmIdentifier) r3, (org.bouncycastle.asn1.ASN1BitString) r4)
            java.security.spec.X509EncodedKeySpec r3 = new java.security.spec.X509EncodedKeySpec
            byte[] r0 = r0.getEncoded()
            r3.<init>(r0)
            org.bouncycastle.cms.jcajce.EnvelopedDataHelper r0 = r5.helper
            org.bouncycastle.asn1.ASN1ObjectIdentifier r4 = r6.getAlgorithm()
            java.security.KeyFactory r0 = r0.createKeyFactory(r4)
            java.security.PublicKey r0 = r0.generatePublic(r3)
            org.bouncycastle.cms.jcajce.EnvelopedDataHelper r3 = r5.helper
            org.bouncycastle.asn1.ASN1ObjectIdentifier r6 = r6.getAlgorithm()
            javax.crypto.KeyAgreement r6 = r3.createKeyAgreement(r6)
            org.bouncycastle.asn1.ASN1OctetString r3 = r9.getAddedukm()
            if (r3 == 0) goto L_0x0058
            org.bouncycastle.asn1.ASN1OctetString r9 = r9.getAddedukm()
            byte[] r2 = r9.getOctets()
        L_0x0058:
            org.bouncycastle.cms.jcajce.KeyMaterialGenerator r9 = old_ecc_cms_Generator
            if (r11 != r9) goto L_0x0066
            org.bouncycastle.operator.SecretKeySizeProvider r11 = r5.keySizeProvider
            int r11 = r11.getKeySize((org.bouncycastle.asn1.x509.AlgorithmIdentifier) r7)
            byte[] r2 = r9.generateKDFMaterial(r7, r11, r2)
        L_0x0066:
            org.bouncycastle.jcajce.spec.MQVParameterSpec r9 = new org.bouncycastle.jcajce.spec.MQVParameterSpec
            r9.<init>((java.security.PrivateKey) r10, (java.security.PublicKey) r0, (byte[]) r2)
            r6.init(r10, r9)
            r6.doPhase(r8, r1)
            org.bouncycastle.asn1.ASN1ObjectIdentifier r7 = r7.getAlgorithm()
            java.lang.String r7 = r7.getId()
            javax.crypto.SecretKey r6 = r6.generateSecret(r7)
            return r6
        L_0x007e:
            org.bouncycastle.cms.jcajce.EnvelopedDataHelper r0 = r5.helper
            org.bouncycastle.asn1.ASN1ObjectIdentifier r3 = r6.getAlgorithm()
            javax.crypto.KeyAgreement r0 = r0.createKeyAgreement(r3)
            org.bouncycastle.asn1.ASN1ObjectIdentifier r3 = r6.getAlgorithm()
            boolean r3 = org.bouncycastle.cms.jcajce.CMSUtils.isEC(r3)
            if (r3 == 0) goto L_0x00b2
            org.bouncycastle.operator.SecretKeySizeProvider r6 = r5.keySizeProvider
            int r6 = r6.getKeySize((org.bouncycastle.asn1.x509.AlgorithmIdentifier) r7)
            if (r9 == 0) goto L_0x00a8
            byte[] r9 = r9.getOctets()
            byte[] r6 = r11.generateKDFMaterial(r7, r6, r9)
            org.bouncycastle.jcajce.spec.UserKeyingMaterialSpec r2 = new org.bouncycastle.jcajce.spec.UserKeyingMaterialSpec
            r2.<init>(r6)
            goto L_0x00dd
        L_0x00a8:
            byte[] r6 = r11.generateKDFMaterial(r7, r6, r2)
            org.bouncycastle.jcajce.spec.UserKeyingMaterialSpec r2 = new org.bouncycastle.jcajce.spec.UserKeyingMaterialSpec
            r2.<init>(r6)
            goto L_0x00dd
        L_0x00b2:
            org.bouncycastle.asn1.ASN1ObjectIdentifier r11 = r6.getAlgorithm()
            boolean r11 = org.bouncycastle.cms.jcajce.CMSUtils.isRFC2631(r11)
            if (r11 == 0) goto L_0x00c8
            if (r9 == 0) goto L_0x00dd
            org.bouncycastle.jcajce.spec.UserKeyingMaterialSpec r2 = new org.bouncycastle.jcajce.spec.UserKeyingMaterialSpec
            byte[] r6 = r9.getOctets()
            r2.<init>(r6)
            goto L_0x00dd
        L_0x00c8:
            org.bouncycastle.asn1.ASN1ObjectIdentifier r11 = r6.getAlgorithm()
            boolean r11 = org.bouncycastle.cms.jcajce.CMSUtils.isGOST(r11)
            if (r11 == 0) goto L_0x00f0
            if (r9 == 0) goto L_0x00dd
            org.bouncycastle.jcajce.spec.UserKeyingMaterialSpec r2 = new org.bouncycastle.jcajce.spec.UserKeyingMaterialSpec
            byte[] r6 = r9.getOctets()
            r2.<init>(r6)
        L_0x00dd:
            r0.init(r10, r2)
            r0.doPhase(r8, r1)
            org.bouncycastle.asn1.ASN1ObjectIdentifier r6 = r7.getAlgorithm()
            java.lang.String r6 = r6.getId()
            javax.crypto.SecretKey r6 = r0.generateSecret(r6)
            return r6
        L_0x00f0:
            org.bouncycastle.cms.CMSException r7 = new org.bouncycastle.cms.CMSException
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r9 = "Unknown key agreement algorithm: "
            r8.<init>(r9)
            org.bouncycastle.asn1.ASN1ObjectIdentifier r6 = r6.getAlgorithm()
            r8.append(r6)
            java.lang.String r6 = r8.toString()
            r7.<init>(r6)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.cms.jcajce.JceKeyAgreeRecipient.calculateAgreedWrapKey(org.bouncycastle.asn1.x509.AlgorithmIdentifier, org.bouncycastle.asn1.x509.AlgorithmIdentifier, java.security.PublicKey, org.bouncycastle.asn1.ASN1OctetString, java.security.PrivateKey, org.bouncycastle.cms.jcajce.KeyMaterialGenerator):javax.crypto.SecretKey");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0099, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x009a, code lost:
        r8 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x00a5, code lost:
        if (possibleOldMessages.contains(r10.getAlgorithm()) != false) goto L_0x00a7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x00c0, code lost:
        return unwrapSessionKey(r0.getAlgorithm(), calculateAgreedWrapKey(r10, r0, r12, r13, r9.recipientKey, old_ecc_cms_Generator), r11.getAlgorithm(), r14);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x00c1, code lost:
        if (r13 != null) goto L_0x00c3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x00dc, code lost:
        return unwrapSessionKey(r0.getAlgorithm(), calculateAgreedWrapKey(r10, r0, r12, r13, r9.recipientKey, simple_ecc_cmsGenerator), r11.getAlgorithm(), r14);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00de, code lost:
        throw r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00df, code lost:
        r10 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00e7, code lost:
        throw new org.bouncycastle.cms.CMSException("originator key invalid.", r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00e8, code lost:
        r10 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00f0, code lost:
        throw new org.bouncycastle.cms.CMSException("required padding not supported.", r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00f1, code lost:
        r10 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00f9, code lost:
        throw new org.bouncycastle.cms.CMSException("originator key spec invalid.", r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00fa, code lost:
        r10 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0102, code lost:
        throw new org.bouncycastle.cms.CMSException("key invalid in message.", r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0103, code lost:
        r10 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x010b, code lost:
        throw new org.bouncycastle.cms.CMSException("can't find algorithm.", r10);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x00dd */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00df A[ExcHandler: Exception (r10v5 'e' java.lang.Exception A[CUSTOM_DECLARE]), Splitter:B:0:0x0000] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00e8 A[ExcHandler: NoSuchPaddingException (r10v4 'e' javax.crypto.NoSuchPaddingException A[CUSTOM_DECLARE]), Splitter:B:0:0x0000] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00f1 A[ExcHandler: InvalidKeySpecException (r10v3 'e' java.security.spec.InvalidKeySpecException A[CUSTOM_DECLARE]), Splitter:B:0:0x0000] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0103 A[ExcHandler: NoSuchAlgorithmException (r10v1 'e' java.security.NoSuchAlgorithmException A[CUSTOM_DECLARE]), Splitter:B:0:0x0000] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.security.Key extractSecretKey(org.bouncycastle.asn1.x509.AlgorithmIdentifier r10, org.bouncycastle.asn1.x509.AlgorithmIdentifier r11, org.bouncycastle.asn1.x509.SubjectPublicKeyInfo r12, org.bouncycastle.asn1.ASN1OctetString r13, byte[] r14) throws org.bouncycastle.cms.CMSException {
        /*
            r9 = this;
            org.bouncycastle.asn1.ASN1Encodable r0 = r10.getParameters()     // Catch:{ NoSuchAlgorithmException -> 0x0103, InvalidKeyException -> 0x00fa, InvalidKeySpecException -> 0x00f1, NoSuchPaddingException -> 0x00e8, Exception -> 0x00df }
            org.bouncycastle.asn1.x509.AlgorithmIdentifier r0 = org.bouncycastle.asn1.x509.AlgorithmIdentifier.getInstance(r0)     // Catch:{ NoSuchAlgorithmException -> 0x0103, InvalidKeyException -> 0x00fa, InvalidKeySpecException -> 0x00f1, NoSuchPaddingException -> 0x00e8, Exception -> 0x00df }
            java.security.spec.X509EncodedKeySpec r1 = new java.security.spec.X509EncodedKeySpec     // Catch:{ NoSuchAlgorithmException -> 0x0103, InvalidKeyException -> 0x00fa, InvalidKeySpecException -> 0x00f1, NoSuchPaddingException -> 0x00e8, Exception -> 0x00df }
            byte[] r2 = r12.getEncoded()     // Catch:{ NoSuchAlgorithmException -> 0x0103, InvalidKeyException -> 0x00fa, InvalidKeySpecException -> 0x00f1, NoSuchPaddingException -> 0x00e8, Exception -> 0x00df }
            r1.<init>(r2)     // Catch:{ NoSuchAlgorithmException -> 0x0103, InvalidKeyException -> 0x00fa, InvalidKeySpecException -> 0x00f1, NoSuchPaddingException -> 0x00e8, Exception -> 0x00df }
            org.bouncycastle.cms.jcajce.EnvelopedDataHelper r2 = r9.helper     // Catch:{ NoSuchAlgorithmException -> 0x0103, InvalidKeyException -> 0x00fa, InvalidKeySpecException -> 0x00f1, NoSuchPaddingException -> 0x00e8, Exception -> 0x00df }
            org.bouncycastle.asn1.x509.AlgorithmIdentifier r12 = r12.getAlgorithm()     // Catch:{ NoSuchAlgorithmException -> 0x0103, InvalidKeyException -> 0x00fa, InvalidKeySpecException -> 0x00f1, NoSuchPaddingException -> 0x00e8, Exception -> 0x00df }
            org.bouncycastle.asn1.ASN1ObjectIdentifier r12 = r12.getAlgorithm()     // Catch:{ NoSuchAlgorithmException -> 0x0103, InvalidKeyException -> 0x00fa, InvalidKeySpecException -> 0x00f1, NoSuchPaddingException -> 0x00e8, Exception -> 0x00df }
            java.security.KeyFactory r12 = r2.createKeyFactory(r12)     // Catch:{ NoSuchAlgorithmException -> 0x0103, InvalidKeyException -> 0x00fa, InvalidKeySpecException -> 0x00f1, NoSuchPaddingException -> 0x00e8, Exception -> 0x00df }
            java.security.PublicKey r12 = r12.generatePublic(r1)     // Catch:{ NoSuchAlgorithmException -> 0x0103, InvalidKeyException -> 0x00fa, InvalidKeySpecException -> 0x00f1, NoSuchPaddingException -> 0x00e8, Exception -> 0x00df }
            java.security.PrivateKey r6 = r9.recipientKey     // Catch:{ InvalidKeyException -> 0x0099, NoSuchAlgorithmException -> 0x0103, InvalidKeySpecException -> 0x00f1, NoSuchPaddingException -> 0x00e8, Exception -> 0x00df }
            org.bouncycastle.cms.jcajce.KeyMaterialGenerator r7 = ecc_cms_Generator     // Catch:{ InvalidKeyException -> 0x0099, NoSuchAlgorithmException -> 0x0103, InvalidKeySpecException -> 0x00f1, NoSuchPaddingException -> 0x00e8, Exception -> 0x00df }
            r1 = r9
            r2 = r10
            r3 = r0
            r4 = r12
            r5 = r13
            javax.crypto.SecretKey r1 = r1.calculateAgreedWrapKey(r2, r3, r4, r5, r6, r7)     // Catch:{ InvalidKeyException -> 0x0099, NoSuchAlgorithmException -> 0x0103, InvalidKeySpecException -> 0x00f1, NoSuchPaddingException -> 0x00e8, Exception -> 0x00df }
            org.bouncycastle.asn1.ASN1ObjectIdentifier r2 = r0.getAlgorithm()     // Catch:{ InvalidKeyException -> 0x0099, NoSuchAlgorithmException -> 0x0103, InvalidKeySpecException -> 0x00f1, NoSuchPaddingException -> 0x00e8, Exception -> 0x00df }
            org.bouncycastle.asn1.ASN1ObjectIdentifier r3 = org.bouncycastle.asn1.cryptopro.CryptoProObjectIdentifiers.id_Gost28147_89_None_KeyWrap     // Catch:{ InvalidKeyException -> 0x0099, NoSuchAlgorithmException -> 0x0103, InvalidKeySpecException -> 0x00f1, NoSuchPaddingException -> 0x00e8, Exception -> 0x00df }
            boolean r2 = r2.equals((org.bouncycastle.asn1.ASN1Primitive) r3)     // Catch:{ InvalidKeyException -> 0x0099, NoSuchAlgorithmException -> 0x0103, InvalidKeySpecException -> 0x00f1, NoSuchPaddingException -> 0x00e8, Exception -> 0x00df }
            if (r2 != 0) goto L_0x0056
            org.bouncycastle.asn1.ASN1ObjectIdentifier r2 = r0.getAlgorithm()     // Catch:{ InvalidKeyException -> 0x0099, NoSuchAlgorithmException -> 0x0103, InvalidKeySpecException -> 0x00f1, NoSuchPaddingException -> 0x00e8, Exception -> 0x00df }
            org.bouncycastle.asn1.ASN1ObjectIdentifier r3 = org.bouncycastle.asn1.cryptopro.CryptoProObjectIdentifiers.id_Gost28147_89_CryptoPro_KeyWrap     // Catch:{ InvalidKeyException -> 0x0099, NoSuchAlgorithmException -> 0x0103, InvalidKeySpecException -> 0x00f1, NoSuchPaddingException -> 0x00e8, Exception -> 0x00df }
            boolean r2 = r2.equals((org.bouncycastle.asn1.ASN1Primitive) r3)     // Catch:{ InvalidKeyException -> 0x0099, NoSuchAlgorithmException -> 0x0103, InvalidKeySpecException -> 0x00f1, NoSuchPaddingException -> 0x00e8, Exception -> 0x00df }
            if (r2 == 0) goto L_0x0049
            goto L_0x0056
        L_0x0049:
            org.bouncycastle.asn1.ASN1ObjectIdentifier r2 = r0.getAlgorithm()     // Catch:{ InvalidKeyException -> 0x0099, NoSuchAlgorithmException -> 0x0103, InvalidKeySpecException -> 0x00f1, NoSuchPaddingException -> 0x00e8, Exception -> 0x00df }
            org.bouncycastle.asn1.ASN1ObjectIdentifier r3 = r11.getAlgorithm()     // Catch:{ InvalidKeyException -> 0x0099, NoSuchAlgorithmException -> 0x0103, InvalidKeySpecException -> 0x00f1, NoSuchPaddingException -> 0x00e8, Exception -> 0x00df }
            java.security.Key r10 = r9.unwrapSessionKey(r2, r1, r3, r14)     // Catch:{ InvalidKeyException -> 0x0099, NoSuchAlgorithmException -> 0x0103, InvalidKeySpecException -> 0x00f1, NoSuchPaddingException -> 0x00e8, Exception -> 0x00df }
            return r10
        L_0x0056:
            org.bouncycastle.asn1.cryptopro.Gost2814789EncryptedKey r2 = org.bouncycastle.asn1.cryptopro.Gost2814789EncryptedKey.getInstance(r14)     // Catch:{ InvalidKeyException -> 0x0099, NoSuchAlgorithmException -> 0x0103, InvalidKeySpecException -> 0x00f1, NoSuchPaddingException -> 0x00e8, Exception -> 0x00df }
            org.bouncycastle.asn1.ASN1Encodable r3 = r0.getParameters()     // Catch:{ InvalidKeyException -> 0x0099, NoSuchAlgorithmException -> 0x0103, InvalidKeySpecException -> 0x00f1, NoSuchPaddingException -> 0x00e8, Exception -> 0x00df }
            org.bouncycastle.asn1.cryptopro.Gost2814789KeyWrapParameters r3 = org.bouncycastle.asn1.cryptopro.Gost2814789KeyWrapParameters.getInstance(r3)     // Catch:{ InvalidKeyException -> 0x0099, NoSuchAlgorithmException -> 0x0103, InvalidKeySpecException -> 0x00f1, NoSuchPaddingException -> 0x00e8, Exception -> 0x00df }
            org.bouncycastle.cms.jcajce.EnvelopedDataHelper r4 = r9.helper     // Catch:{ InvalidKeyException -> 0x0099, NoSuchAlgorithmException -> 0x0103, InvalidKeySpecException -> 0x00f1, NoSuchPaddingException -> 0x00e8, Exception -> 0x00df }
            org.bouncycastle.asn1.ASN1ObjectIdentifier r5 = r0.getAlgorithm()     // Catch:{ InvalidKeyException -> 0x0099, NoSuchAlgorithmException -> 0x0103, InvalidKeySpecException -> 0x00f1, NoSuchPaddingException -> 0x00e8, Exception -> 0x00df }
            javax.crypto.Cipher r4 = r4.createCipher(r5)     // Catch:{ InvalidKeyException -> 0x0099, NoSuchAlgorithmException -> 0x0103, InvalidKeySpecException -> 0x00f1, NoSuchPaddingException -> 0x00e8, Exception -> 0x00df }
            org.bouncycastle.jcajce.spec.GOST28147WrapParameterSpec r5 = new org.bouncycastle.jcajce.spec.GOST28147WrapParameterSpec     // Catch:{ InvalidKeyException -> 0x0099, NoSuchAlgorithmException -> 0x0103, InvalidKeySpecException -> 0x00f1, NoSuchPaddingException -> 0x00e8, Exception -> 0x00df }
            org.bouncycastle.asn1.ASN1ObjectIdentifier r3 = r3.getEncryptionParamSet()     // Catch:{ InvalidKeyException -> 0x0099, NoSuchAlgorithmException -> 0x0103, InvalidKeySpecException -> 0x00f1, NoSuchPaddingException -> 0x00e8, Exception -> 0x00df }
            byte[] r6 = r13.getOctets()     // Catch:{ InvalidKeyException -> 0x0099, NoSuchAlgorithmException -> 0x0103, InvalidKeySpecException -> 0x00f1, NoSuchPaddingException -> 0x00e8, Exception -> 0x00df }
            r5.<init>((org.bouncycastle.asn1.ASN1ObjectIdentifier) r3, (byte[]) r6)     // Catch:{ InvalidKeyException -> 0x0099, NoSuchAlgorithmException -> 0x0103, InvalidKeySpecException -> 0x00f1, NoSuchPaddingException -> 0x00e8, Exception -> 0x00df }
            r3 = 4
            r4.init(r3, r1, r5)     // Catch:{ InvalidKeyException -> 0x0099, NoSuchAlgorithmException -> 0x0103, InvalidKeySpecException -> 0x00f1, NoSuchPaddingException -> 0x00e8, Exception -> 0x00df }
            byte[] r1 = r2.getEncryptedKey()     // Catch:{ InvalidKeyException -> 0x0099, NoSuchAlgorithmException -> 0x0103, InvalidKeySpecException -> 0x00f1, NoSuchPaddingException -> 0x00e8, Exception -> 0x00df }
            byte[] r2 = r2.getMacKey()     // Catch:{ InvalidKeyException -> 0x0099, NoSuchAlgorithmException -> 0x0103, InvalidKeySpecException -> 0x00f1, NoSuchPaddingException -> 0x00e8, Exception -> 0x00df }
            byte[] r1 = org.bouncycastle.util.Arrays.concatenate((byte[]) r1, (byte[]) r2)     // Catch:{ InvalidKeyException -> 0x0099, NoSuchAlgorithmException -> 0x0103, InvalidKeySpecException -> 0x00f1, NoSuchPaddingException -> 0x00e8, Exception -> 0x00df }
            org.bouncycastle.cms.jcajce.EnvelopedDataHelper r2 = r9.helper     // Catch:{ InvalidKeyException -> 0x0099, NoSuchAlgorithmException -> 0x0103, InvalidKeySpecException -> 0x00f1, NoSuchPaddingException -> 0x00e8, Exception -> 0x00df }
            org.bouncycastle.asn1.ASN1ObjectIdentifier r3 = r11.getAlgorithm()     // Catch:{ InvalidKeyException -> 0x0099, NoSuchAlgorithmException -> 0x0103, InvalidKeySpecException -> 0x00f1, NoSuchPaddingException -> 0x00e8, Exception -> 0x00df }
            java.lang.String r2 = r2.getBaseCipherName(r3)     // Catch:{ InvalidKeyException -> 0x0099, NoSuchAlgorithmException -> 0x0103, InvalidKeySpecException -> 0x00f1, NoSuchPaddingException -> 0x00e8, Exception -> 0x00df }
            r3 = 3
            java.security.Key r10 = r4.unwrap(r1, r2, r3)     // Catch:{ InvalidKeyException -> 0x0099, NoSuchAlgorithmException -> 0x0103, InvalidKeySpecException -> 0x00f1, NoSuchPaddingException -> 0x00e8, Exception -> 0x00df }
            return r10
        L_0x0099:
            r1 = move-exception
            r8 = r1
            java.util.Set r1 = possibleOldMessages     // Catch:{ NoSuchAlgorithmException -> 0x0103, InvalidKeyException -> 0x00fa, InvalidKeySpecException -> 0x00f1, NoSuchPaddingException -> 0x00e8, Exception -> 0x00df }
            org.bouncycastle.asn1.ASN1ObjectIdentifier r2 = r10.getAlgorithm()     // Catch:{ NoSuchAlgorithmException -> 0x0103, InvalidKeyException -> 0x00fa, InvalidKeySpecException -> 0x00f1, NoSuchPaddingException -> 0x00e8, Exception -> 0x00df }
            boolean r1 = r1.contains(r2)     // Catch:{ NoSuchAlgorithmException -> 0x0103, InvalidKeyException -> 0x00fa, InvalidKeySpecException -> 0x00f1, NoSuchPaddingException -> 0x00e8, Exception -> 0x00df }
            if (r1 == 0) goto L_0x00c1
            java.security.PrivateKey r6 = r9.recipientKey     // Catch:{ NoSuchAlgorithmException -> 0x0103, InvalidKeyException -> 0x00fa, InvalidKeySpecException -> 0x00f1, NoSuchPaddingException -> 0x00e8, Exception -> 0x00df }
            org.bouncycastle.cms.jcajce.KeyMaterialGenerator r7 = old_ecc_cms_Generator     // Catch:{ NoSuchAlgorithmException -> 0x0103, InvalidKeyException -> 0x00fa, InvalidKeySpecException -> 0x00f1, NoSuchPaddingException -> 0x00e8, Exception -> 0x00df }
            r1 = r9
            r2 = r10
            r3 = r0
            r4 = r12
            r5 = r13
            javax.crypto.SecretKey r10 = r1.calculateAgreedWrapKey(r2, r3, r4, r5, r6, r7)     // Catch:{ NoSuchAlgorithmException -> 0x0103, InvalidKeyException -> 0x00fa, InvalidKeySpecException -> 0x00f1, NoSuchPaddingException -> 0x00e8, Exception -> 0x00df }
            org.bouncycastle.asn1.ASN1ObjectIdentifier r12 = r0.getAlgorithm()     // Catch:{ NoSuchAlgorithmException -> 0x0103, InvalidKeyException -> 0x00fa, InvalidKeySpecException -> 0x00f1, NoSuchPaddingException -> 0x00e8, Exception -> 0x00df }
            org.bouncycastle.asn1.ASN1ObjectIdentifier r11 = r11.getAlgorithm()     // Catch:{ NoSuchAlgorithmException -> 0x0103, InvalidKeyException -> 0x00fa, InvalidKeySpecException -> 0x00f1, NoSuchPaddingException -> 0x00e8, Exception -> 0x00df }
            java.security.Key r10 = r9.unwrapSessionKey(r12, r10, r11, r14)     // Catch:{ NoSuchAlgorithmException -> 0x0103, InvalidKeyException -> 0x00fa, InvalidKeySpecException -> 0x00f1, NoSuchPaddingException -> 0x00e8, Exception -> 0x00df }
            return r10
        L_0x00c1:
            if (r13 == 0) goto L_0x00de
            java.security.PrivateKey r6 = r9.recipientKey     // Catch:{ InvalidKeyException -> 0x00dd, NoSuchAlgorithmException -> 0x0103, InvalidKeySpecException -> 0x00f1, NoSuchPaddingException -> 0x00e8, Exception -> 0x00df }
            org.bouncycastle.cms.jcajce.KeyMaterialGenerator r7 = simple_ecc_cmsGenerator     // Catch:{ InvalidKeyException -> 0x00dd, NoSuchAlgorithmException -> 0x0103, InvalidKeySpecException -> 0x00f1, NoSuchPaddingException -> 0x00e8, Exception -> 0x00df }
            r1 = r9
            r2 = r10
            r3 = r0
            r4 = r12
            r5 = r13
            javax.crypto.SecretKey r10 = r1.calculateAgreedWrapKey(r2, r3, r4, r5, r6, r7)     // Catch:{ InvalidKeyException -> 0x00dd, NoSuchAlgorithmException -> 0x0103, InvalidKeySpecException -> 0x00f1, NoSuchPaddingException -> 0x00e8, Exception -> 0x00df }
            org.bouncycastle.asn1.ASN1ObjectIdentifier r12 = r0.getAlgorithm()     // Catch:{ InvalidKeyException -> 0x00dd, NoSuchAlgorithmException -> 0x0103, InvalidKeySpecException -> 0x00f1, NoSuchPaddingException -> 0x00e8, Exception -> 0x00df }
            org.bouncycastle.asn1.ASN1ObjectIdentifier r11 = r11.getAlgorithm()     // Catch:{ InvalidKeyException -> 0x00dd, NoSuchAlgorithmException -> 0x0103, InvalidKeySpecException -> 0x00f1, NoSuchPaddingException -> 0x00e8, Exception -> 0x00df }
            java.security.Key r10 = r9.unwrapSessionKey(r12, r10, r11, r14)     // Catch:{ InvalidKeyException -> 0x00dd, NoSuchAlgorithmException -> 0x0103, InvalidKeySpecException -> 0x00f1, NoSuchPaddingException -> 0x00e8, Exception -> 0x00df }
            return r10
        L_0x00dd:
            throw r8     // Catch:{ NoSuchAlgorithmException -> 0x0103, InvalidKeyException -> 0x00fa, InvalidKeySpecException -> 0x00f1, NoSuchPaddingException -> 0x00e8, Exception -> 0x00df }
        L_0x00de:
            throw r8     // Catch:{ NoSuchAlgorithmException -> 0x0103, InvalidKeyException -> 0x00fa, InvalidKeySpecException -> 0x00f1, NoSuchPaddingException -> 0x00e8, Exception -> 0x00df }
        L_0x00df:
            r10 = move-exception
            org.bouncycastle.cms.CMSException r11 = new org.bouncycastle.cms.CMSException
            java.lang.String r12 = "originator key invalid."
            r11.<init>(r12, r10)
            throw r11
        L_0x00e8:
            r10 = move-exception
            org.bouncycastle.cms.CMSException r11 = new org.bouncycastle.cms.CMSException
            java.lang.String r12 = "required padding not supported."
            r11.<init>(r12, r10)
            throw r11
        L_0x00f1:
            r10 = move-exception
            org.bouncycastle.cms.CMSException r11 = new org.bouncycastle.cms.CMSException
            java.lang.String r12 = "originator key spec invalid."
            r11.<init>(r12, r10)
            throw r11
        L_0x00fa:
            r10 = move-exception
            org.bouncycastle.cms.CMSException r11 = new org.bouncycastle.cms.CMSException
            java.lang.String r12 = "key invalid in message."
            r11.<init>(r12, r10)
            throw r11
        L_0x0103:
            r10 = move-exception
            org.bouncycastle.cms.CMSException r11 = new org.bouncycastle.cms.CMSException
            java.lang.String r12 = "can't find algorithm."
            r11.<init>(r12, r10)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.cms.jcajce.JceKeyAgreeRecipient.extractSecretKey(org.bouncycastle.asn1.x509.AlgorithmIdentifier, org.bouncycastle.asn1.x509.AlgorithmIdentifier, org.bouncycastle.asn1.x509.SubjectPublicKeyInfo, org.bouncycastle.asn1.ASN1OctetString, byte[]):java.security.Key");
    }

    public AlgorithmIdentifier getPrivateKeyAlgorithmIdentifier() {
        if (this.privKeyAlgID == null) {
            this.privKeyAlgID = PrivateKeyInfo.getInstance(this.recipientKey.getEncoded()).getPrivateKeyAlgorithm();
        }
        return this.privKeyAlgID;
    }

    public JceKeyAgreeRecipient setContentProvider(String str) {
        this.contentHelper = CMSUtils.createContentHelper(str);
        return this;
    }

    public JceKeyAgreeRecipient setContentProvider(Provider provider) {
        this.contentHelper = CMSUtils.createContentHelper(provider);
        return this;
    }

    public JceKeyAgreeRecipient setPrivateKeyAlgorithmIdentifier(AlgorithmIdentifier algorithmIdentifier) {
        this.privKeyAlgID = algorithmIdentifier;
        return this;
    }

    public JceKeyAgreeRecipient setProvider(String str) {
        EnvelopedDataHelper envelopedDataHelper = new EnvelopedDataHelper(new NamedJcaJceExtHelper(str));
        this.helper = envelopedDataHelper;
        this.contentHelper = envelopedDataHelper;
        return this;
    }

    public JceKeyAgreeRecipient setProvider(Provider provider) {
        EnvelopedDataHelper envelopedDataHelper = new EnvelopedDataHelper(new ProviderJcaJceExtHelper(provider));
        this.helper = envelopedDataHelper;
        this.contentHelper = envelopedDataHelper;
        return this;
    }

    /* access modifiers changed from: protected */
    public Key unwrapSessionKey(ASN1ObjectIdentifier aSN1ObjectIdentifier, SecretKey secretKey, ASN1ObjectIdentifier aSN1ObjectIdentifier2, byte[] bArr) throws CMSException, InvalidKeyException, NoSuchAlgorithmException {
        Cipher createCipher = this.helper.createCipher(aSN1ObjectIdentifier);
        createCipher.init(4, secretKey);
        return createCipher.unwrap(bArr, this.helper.getBaseCipherName(aSN1ObjectIdentifier2), 3);
    }
}
