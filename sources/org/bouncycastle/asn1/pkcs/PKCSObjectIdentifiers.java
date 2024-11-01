package org.bouncycastle.asn1.pkcs;

import okhttp3.internal.cache.DiskLruCache;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.fusesource.jansi.AnsiConsole;
import org.videolan.resources.Constants;
import org.videolan.vlc.gui.dialogs.NetworkServerDialog;

public interface PKCSObjectIdentifiers {
    public static final ASN1ObjectIdentifier RC2_CBC;
    public static final ASN1ObjectIdentifier bagtypes;
    public static final ASN1ObjectIdentifier canNotDecryptAny;
    public static final ASN1ObjectIdentifier certBag;
    public static final ASN1ObjectIdentifier certTypes;
    public static final ASN1ObjectIdentifier crlBag;
    public static final ASN1ObjectIdentifier crlTypes;
    public static final ASN1ObjectIdentifier data = new ASN1ObjectIdentifier("1.2.840.113549.1.7.1").intern();
    public static final ASN1ObjectIdentifier des_EDE3_CBC;
    public static final ASN1ObjectIdentifier dhKeyAgreement;
    public static final ASN1ObjectIdentifier digestAlgorithm;
    public static final ASN1ObjectIdentifier digestedData = new ASN1ObjectIdentifier("1.2.840.113549.1.7.5").intern();
    public static final ASN1ObjectIdentifier encryptedData = new ASN1ObjectIdentifier("1.2.840.113549.1.7.6").intern();
    public static final ASN1ObjectIdentifier encryptionAlgorithm;
    public static final ASN1ObjectIdentifier envelopedData = new ASN1ObjectIdentifier("1.2.840.113549.1.7.3").intern();
    public static final ASN1ObjectIdentifier id_PBES2;
    public static final ASN1ObjectIdentifier id_PBKDF2;
    public static final ASN1ObjectIdentifier id_PBMAC1;
    public static final ASN1ObjectIdentifier id_RSAES_OAEP;
    public static final ASN1ObjectIdentifier id_RSASSA_PSS;
    public static final ASN1ObjectIdentifier id_aa;
    public static final ASN1ObjectIdentifier id_aa_asymmDecryptKeyID;
    public static final ASN1ObjectIdentifier id_aa_cmsAlgorithmProtect;
    public static final ASN1ObjectIdentifier id_aa_commitmentType;
    public static final ASN1ObjectIdentifier id_aa_communityIdentifiers;
    public static final ASN1ObjectIdentifier id_aa_contentHint;
    public static final ASN1ObjectIdentifier id_aa_contentIdentifier;
    public static final ASN1ObjectIdentifier id_aa_contentReference;
    public static final ASN1ObjectIdentifier id_aa_decryptKeyID;
    public static final ASN1ObjectIdentifier id_aa_encrypKeyPref;
    public static final ASN1ObjectIdentifier id_aa_ets_archiveTimestamp;
    public static final ASN1ObjectIdentifier id_aa_ets_certCRLTimestamp;
    public static final ASN1ObjectIdentifier id_aa_ets_certValues;
    public static final ASN1ObjectIdentifier id_aa_ets_certificateRefs;
    public static final ASN1ObjectIdentifier id_aa_ets_commitmentType;
    public static final ASN1ObjectIdentifier id_aa_ets_contentTimestamp;
    public static final ASN1ObjectIdentifier id_aa_ets_escTimeStamp;
    public static final ASN1ObjectIdentifier id_aa_ets_otherSigCert;
    public static final ASN1ObjectIdentifier id_aa_ets_revocationRefs;
    public static final ASN1ObjectIdentifier id_aa_ets_revocationValues;
    public static final ASN1ObjectIdentifier id_aa_ets_sigPolicyId;
    public static final ASN1ObjectIdentifier id_aa_ets_signerAttr;
    public static final ASN1ObjectIdentifier id_aa_ets_signerLocation;
    public static final ASN1ObjectIdentifier id_aa_implCompressAlgs;
    public static final ASN1ObjectIdentifier id_aa_implCryptoAlgs;
    public static final ASN1ObjectIdentifier id_aa_msgSigDigest;
    public static final ASN1ObjectIdentifier id_aa_otherSigCert;
    public static final ASN1ObjectIdentifier id_aa_receiptRequest;
    public static final ASN1ObjectIdentifier id_aa_sigPolicyId;
    public static final ASN1ObjectIdentifier id_aa_signatureTimeStampToken;
    public static final ASN1ObjectIdentifier id_aa_signerLocation;
    public static final ASN1ObjectIdentifier id_aa_signingCertificate;
    public static final ASN1ObjectIdentifier id_aa_signingCertificateV2;
    public static final ASN1ObjectIdentifier id_alg;
    public static final ASN1ObjectIdentifier id_alg_AEADChaCha20Poly1305;
    public static final ASN1ObjectIdentifier id_alg_CMS3DESwrap;
    public static final ASN1ObjectIdentifier id_alg_CMSRC2wrap;
    public static final ASN1ObjectIdentifier id_alg_ESDH;
    public static final ASN1ObjectIdentifier id_alg_PWRI_KEK;
    public static final ASN1ObjectIdentifier id_alg_SSDH;
    public static final ASN1ObjectIdentifier id_alg_hkdf_with_sha256;
    public static final ASN1ObjectIdentifier id_alg_hkdf_with_sha384;
    public static final ASN1ObjectIdentifier id_alg_hkdf_with_sha512;
    public static final ASN1ObjectIdentifier id_alg_hss_lms_hashsig;
    public static final ASN1ObjectIdentifier id_alg_zlibCompress;
    public static final ASN1ObjectIdentifier id_ct;
    public static final ASN1ObjectIdentifier id_ct_TSTInfo;
    public static final ASN1ObjectIdentifier id_ct_authData;
    public static final ASN1ObjectIdentifier id_ct_authEnvelopedData;
    public static final ASN1ObjectIdentifier id_ct_compressedData;
    public static final ASN1ObjectIdentifier id_ct_timestampedData;
    public static final ASN1ObjectIdentifier id_cti;
    public static final ASN1ObjectIdentifier id_cti_ets_proofOfApproval;
    public static final ASN1ObjectIdentifier id_cti_ets_proofOfCreation;
    public static final ASN1ObjectIdentifier id_cti_ets_proofOfDelivery;
    public static final ASN1ObjectIdentifier id_cti_ets_proofOfOrigin;
    public static final ASN1ObjectIdentifier id_cti_ets_proofOfReceipt;
    public static final ASN1ObjectIdentifier id_cti_ets_proofOfSender;
    public static final ASN1ObjectIdentifier id_hmacWithSHA1;
    public static final ASN1ObjectIdentifier id_hmacWithSHA224;
    public static final ASN1ObjectIdentifier id_hmacWithSHA256;
    public static final ASN1ObjectIdentifier id_hmacWithSHA384;
    public static final ASN1ObjectIdentifier id_hmacWithSHA512;
    public static final ASN1ObjectIdentifier id_hmacWithSHA512_224;
    public static final ASN1ObjectIdentifier id_hmacWithSHA512_256;
    public static final ASN1ObjectIdentifier id_mgf1;
    public static final ASN1ObjectIdentifier id_pSpecified;
    public static final ASN1ObjectIdentifier id_rsa_KEM;
    public static final ASN1ObjectIdentifier id_smime;
    public static final String id_spq = "1.2.840.113549.1.9.16.5";
    public static final ASN1ObjectIdentifier id_spq_ets_unotice = new ASN1ObjectIdentifier("1.2.840.113549.1.9.16.5.2");
    public static final ASN1ObjectIdentifier id_spq_ets_uri = new ASN1ObjectIdentifier("1.2.840.113549.1.9.16.5.1");
    public static final ASN1ObjectIdentifier keyBag;
    public static final ASN1ObjectIdentifier md2;
    public static final ASN1ObjectIdentifier md2WithRSAEncryption;
    public static final ASN1ObjectIdentifier md4;
    public static final ASN1ObjectIdentifier md4WithRSAEncryption;
    public static final ASN1ObjectIdentifier md5;
    public static final ASN1ObjectIdentifier md5WithRSAEncryption;
    public static final ASN1ObjectIdentifier pbeWithMD2AndDES_CBC;
    public static final ASN1ObjectIdentifier pbeWithMD2AndRC2_CBC;
    public static final ASN1ObjectIdentifier pbeWithMD5AndDES_CBC;
    public static final ASN1ObjectIdentifier pbeWithMD5AndRC2_CBC;
    public static final ASN1ObjectIdentifier pbeWithSHA1AndDES_CBC;
    public static final ASN1ObjectIdentifier pbeWithSHA1AndRC2_CBC;
    public static final ASN1ObjectIdentifier pbeWithSHAAnd128BitRC2_CBC;
    public static final ASN1ObjectIdentifier pbeWithSHAAnd128BitRC4;
    public static final ASN1ObjectIdentifier pbeWithSHAAnd2_KeyTripleDES_CBC;
    public static final ASN1ObjectIdentifier pbeWithSHAAnd3_KeyTripleDES_CBC;
    public static final ASN1ObjectIdentifier pbeWithSHAAnd40BitRC2_CBC;
    public static final ASN1ObjectIdentifier pbeWithSHAAnd40BitRC4;
    public static final ASN1ObjectIdentifier pbewithSHAAnd40BitRC2_CBC;
    public static final ASN1ObjectIdentifier pkcs8ShroudedKeyBag;
    public static final ASN1ObjectIdentifier pkcs_1;
    public static final ASN1ObjectIdentifier pkcs_12;
    public static final ASN1ObjectIdentifier pkcs_12PbeIds;
    public static final ASN1ObjectIdentifier pkcs_3;
    public static final ASN1ObjectIdentifier pkcs_5;
    public static final ASN1ObjectIdentifier pkcs_7 = new ASN1ObjectIdentifier("1.2.840.113549.1.7").intern();
    public static final ASN1ObjectIdentifier pkcs_9;
    public static final ASN1ObjectIdentifier pkcs_9_at_binarySigningTime;
    public static final ASN1ObjectIdentifier pkcs_9_at_challengePassword;
    public static final ASN1ObjectIdentifier pkcs_9_at_contentType;
    public static final ASN1ObjectIdentifier pkcs_9_at_counterSignature;
    public static final ASN1ObjectIdentifier pkcs_9_at_emailAddress;
    public static final ASN1ObjectIdentifier pkcs_9_at_extendedCertificateAttributes;
    public static final ASN1ObjectIdentifier pkcs_9_at_extensionRequest;
    public static final ASN1ObjectIdentifier pkcs_9_at_friendlyName;
    public static final ASN1ObjectIdentifier pkcs_9_at_localKeyId;
    public static final ASN1ObjectIdentifier pkcs_9_at_messageDigest;
    public static final ASN1ObjectIdentifier pkcs_9_at_signingDescription;
    public static final ASN1ObjectIdentifier pkcs_9_at_signingTime;
    public static final ASN1ObjectIdentifier pkcs_9_at_smimeCapabilities;
    public static final ASN1ObjectIdentifier pkcs_9_at_unstructuredAddress;
    public static final ASN1ObjectIdentifier pkcs_9_at_unstructuredName;
    public static final ASN1ObjectIdentifier preferSignedData;
    public static final ASN1ObjectIdentifier rc4;
    public static final ASN1ObjectIdentifier rsaEncryption;
    public static final ASN1ObjectIdentifier sMIMECapabilitiesVersions;
    public static final ASN1ObjectIdentifier safeContentsBag;
    public static final ASN1ObjectIdentifier sdsiCertificate;
    public static final ASN1ObjectIdentifier secretBag;
    public static final ASN1ObjectIdentifier sha1WithRSAEncryption;
    public static final ASN1ObjectIdentifier sha224WithRSAEncryption;
    public static final ASN1ObjectIdentifier sha256WithRSAEncryption;
    public static final ASN1ObjectIdentifier sha384WithRSAEncryption;
    public static final ASN1ObjectIdentifier sha512WithRSAEncryption;
    public static final ASN1ObjectIdentifier sha512_224WithRSAEncryption;
    public static final ASN1ObjectIdentifier sha512_256WithRSAEncryption;
    public static final ASN1ObjectIdentifier signedAndEnvelopedData = new ASN1ObjectIdentifier("1.2.840.113549.1.7.4").intern();
    public static final ASN1ObjectIdentifier signedData = new ASN1ObjectIdentifier("1.2.840.113549.1.7.2").intern();
    public static final ASN1ObjectIdentifier srsaOAEPEncryptionSET;
    public static final ASN1ObjectIdentifier x509Certificate;
    public static final ASN1ObjectIdentifier x509Crl;
    public static final ASN1ObjectIdentifier x509certType;

    static {
        ASN1ObjectIdentifier aSN1ObjectIdentifier = new ASN1ObjectIdentifier("1.2.840.113549.1.1");
        pkcs_1 = aSN1ObjectIdentifier;
        rsaEncryption = aSN1ObjectIdentifier.branch(DiskLruCache.VERSION_1);
        md2WithRSAEncryption = aSN1ObjectIdentifier.branch("2");
        md4WithRSAEncryption = aSN1ObjectIdentifier.branch("3");
        md5WithRSAEncryption = aSN1ObjectIdentifier.branch("4");
        sha1WithRSAEncryption = aSN1ObjectIdentifier.branch("5");
        srsaOAEPEncryptionSET = aSN1ObjectIdentifier.branch(Constants.GROUP_VIDEOS_NAME);
        id_RSAES_OAEP = aSN1ObjectIdentifier.branch("7");
        id_mgf1 = aSN1ObjectIdentifier.branch("8");
        id_pSpecified = aSN1ObjectIdentifier.branch("9");
        id_RSASSA_PSS = aSN1ObjectIdentifier.branch("10");
        sha256WithRSAEncryption = aSN1ObjectIdentifier.branch("11");
        sha384WithRSAEncryption = aSN1ObjectIdentifier.branch("12");
        sha512WithRSAEncryption = aSN1ObjectIdentifier.branch("13");
        sha224WithRSAEncryption = aSN1ObjectIdentifier.branch("14");
        sha512_224WithRSAEncryption = aSN1ObjectIdentifier.branch("15");
        sha512_256WithRSAEncryption = aSN1ObjectIdentifier.branch(AnsiConsole.JANSI_COLORS_16);
        String str = AnsiConsole.JANSI_COLORS_16;
        ASN1ObjectIdentifier aSN1ObjectIdentifier2 = new ASN1ObjectIdentifier("1.2.840.113549.1.3");
        pkcs_3 = aSN1ObjectIdentifier2;
        dhKeyAgreement = aSN1ObjectIdentifier2.branch(DiskLruCache.VERSION_1);
        ASN1ObjectIdentifier aSN1ObjectIdentifier3 = new ASN1ObjectIdentifier("1.2.840.113549.1.5");
        pkcs_5 = aSN1ObjectIdentifier3;
        pbeWithMD2AndDES_CBC = aSN1ObjectIdentifier3.branch(DiskLruCache.VERSION_1);
        pbeWithMD2AndRC2_CBC = aSN1ObjectIdentifier3.branch("4");
        pbeWithMD5AndDES_CBC = aSN1ObjectIdentifier3.branch("3");
        pbeWithMD5AndRC2_CBC = aSN1ObjectIdentifier3.branch(Constants.GROUP_VIDEOS_NAME);
        pbeWithSHA1AndDES_CBC = aSN1ObjectIdentifier3.branch("10");
        pbeWithSHA1AndRC2_CBC = aSN1ObjectIdentifier3.branch("11");
        id_PBKDF2 = aSN1ObjectIdentifier3.branch("12");
        id_PBES2 = aSN1ObjectIdentifier3.branch("13");
        id_PBMAC1 = aSN1ObjectIdentifier3.branch("14");
        ASN1ObjectIdentifier aSN1ObjectIdentifier4 = new ASN1ObjectIdentifier("1.2.840.113549.3");
        encryptionAlgorithm = aSN1ObjectIdentifier4;
        des_EDE3_CBC = aSN1ObjectIdentifier4.branch("7");
        RC2_CBC = aSN1ObjectIdentifier4.branch("2");
        rc4 = aSN1ObjectIdentifier4.branch("4");
        ASN1ObjectIdentifier aSN1ObjectIdentifier5 = new ASN1ObjectIdentifier("1.2.840.113549.2");
        digestAlgorithm = aSN1ObjectIdentifier5;
        md2 = aSN1ObjectIdentifier5.branch("2");
        md4 = aSN1ObjectIdentifier5.branch("4");
        md5 = aSN1ObjectIdentifier5.branch("5");
        id_hmacWithSHA1 = aSN1ObjectIdentifier5.branch("7").intern();
        id_hmacWithSHA224 = aSN1ObjectIdentifier5.branch("8").intern();
        id_hmacWithSHA256 = aSN1ObjectIdentifier5.branch("9").intern();
        id_hmacWithSHA384 = aSN1ObjectIdentifier5.branch("10").intern();
        id_hmacWithSHA512 = aSN1ObjectIdentifier5.branch("11").intern();
        id_hmacWithSHA512_224 = aSN1ObjectIdentifier5.branch("12").intern();
        id_hmacWithSHA512_256 = aSN1ObjectIdentifier5.branch("13").intern();
        ASN1ObjectIdentifier aSN1ObjectIdentifier6 = new ASN1ObjectIdentifier("1.2.840.113549.1.9");
        pkcs_9 = aSN1ObjectIdentifier6;
        pkcs_9_at_emailAddress = aSN1ObjectIdentifier6.branch(DiskLruCache.VERSION_1).intern();
        pkcs_9_at_unstructuredName = aSN1ObjectIdentifier6.branch("2").intern();
        pkcs_9_at_contentType = aSN1ObjectIdentifier6.branch("3").intern();
        pkcs_9_at_messageDigest = aSN1ObjectIdentifier6.branch("4").intern();
        pkcs_9_at_signingTime = aSN1ObjectIdentifier6.branch("5").intern();
        pkcs_9_at_counterSignature = aSN1ObjectIdentifier6.branch(Constants.GROUP_VIDEOS_NAME).intern();
        pkcs_9_at_challengePassword = aSN1ObjectIdentifier6.branch("7").intern();
        pkcs_9_at_unstructuredAddress = aSN1ObjectIdentifier6.branch("8").intern();
        pkcs_9_at_extendedCertificateAttributes = aSN1ObjectIdentifier6.branch("9").intern();
        pkcs_9_at_signingDescription = aSN1ObjectIdentifier6.branch("13").intern();
        pkcs_9_at_extensionRequest = aSN1ObjectIdentifier6.branch("14").intern();
        String str2 = "15";
        pkcs_9_at_smimeCapabilities = aSN1ObjectIdentifier6.branch(str2).intern();
        ASN1ObjectIdentifier intern = aSN1ObjectIdentifier6.branch(str).intern();
        id_smime = intern;
        String str3 = str2;
        pkcs_9_at_binarySigningTime = aSN1ObjectIdentifier6.branch("16.2.46").intern();
        pkcs_9_at_friendlyName = aSN1ObjectIdentifier6.branch("20").intern();
        pkcs_9_at_localKeyId = aSN1ObjectIdentifier6.branch("21").intern();
        x509certType = aSN1ObjectIdentifier6.branch("22.1");
        ASN1ObjectIdentifier branch = aSN1ObjectIdentifier6.branch(NetworkServerDialog.SFTP_DEFAULT_PORT);
        certTypes = branch;
        x509Certificate = branch.branch(DiskLruCache.VERSION_1).intern();
        sdsiCertificate = branch.branch("2").intern();
        String str4 = NetworkServerDialog.SFTP_DEFAULT_PORT;
        ASN1ObjectIdentifier branch2 = aSN1ObjectIdentifier6.branch("23");
        crlTypes = branch2;
        x509Crl = branch2.branch(DiskLruCache.VERSION_1).intern();
        id_aa_cmsAlgorithmProtect = aSN1ObjectIdentifier6.branch("52").intern();
        preferSignedData = aSN1ObjectIdentifier6.branch("15.1");
        canNotDecryptAny = aSN1ObjectIdentifier6.branch("15.2");
        sMIMECapabilitiesVersions = aSN1ObjectIdentifier6.branch("15.3");
        ASN1ObjectIdentifier aSN1ObjectIdentifier7 = new ASN1ObjectIdentifier("1.2.840.113549.1.9.16.1");
        id_ct = aSN1ObjectIdentifier7;
        id_ct_authData = aSN1ObjectIdentifier7.branch("2");
        id_ct_TSTInfo = aSN1ObjectIdentifier7.branch("4");
        id_ct_compressedData = aSN1ObjectIdentifier7.branch("9");
        id_ct_authEnvelopedData = aSN1ObjectIdentifier7.branch("23");
        id_ct_timestampedData = aSN1ObjectIdentifier7.branch("31");
        ASN1ObjectIdentifier branch3 = intern.branch("3");
        id_alg = branch3;
        id_alg_ESDH = branch3.branch("5");
        id_alg_CMS3DESwrap = branch3.branch(Constants.GROUP_VIDEOS_NAME);
        id_alg_CMSRC2wrap = branch3.branch("7");
        id_alg_zlibCompress = branch3.branch("8");
        id_alg_PWRI_KEK = branch3.branch("9");
        id_alg_SSDH = branch3.branch("10");
        id_rsa_KEM = branch3.branch("14");
        id_alg_hss_lms_hashsig = branch3.branch("17");
        id_alg_AEADChaCha20Poly1305 = branch3.branch("18");
        id_alg_hkdf_with_sha256 = branch3.branch("28");
        id_alg_hkdf_with_sha384 = branch3.branch("29");
        id_alg_hkdf_with_sha512 = branch3.branch("30");
        ASN1ObjectIdentifier aSN1ObjectIdentifier8 = new ASN1ObjectIdentifier("1.2.840.113549.1.9.16.6");
        id_cti = aSN1ObjectIdentifier8;
        id_cti_ets_proofOfOrigin = aSN1ObjectIdentifier8.branch(DiskLruCache.VERSION_1);
        id_cti_ets_proofOfReceipt = aSN1ObjectIdentifier8.branch("2");
        id_cti_ets_proofOfDelivery = aSN1ObjectIdentifier8.branch("3");
        id_cti_ets_proofOfSender = aSN1ObjectIdentifier8.branch("4");
        id_cti_ets_proofOfApproval = aSN1ObjectIdentifier8.branch("5");
        id_cti_ets_proofOfCreation = aSN1ObjectIdentifier8.branch(Constants.GROUP_VIDEOS_NAME);
        ASN1ObjectIdentifier aSN1ObjectIdentifier9 = new ASN1ObjectIdentifier("1.2.840.113549.1.9.16.2");
        id_aa = aSN1ObjectIdentifier9;
        id_aa_receiptRequest = aSN1ObjectIdentifier9.branch(DiskLruCache.VERSION_1);
        id_aa_contentHint = aSN1ObjectIdentifier9.branch("4");
        id_aa_msgSigDigest = aSN1ObjectIdentifier9.branch("5");
        id_aa_contentReference = aSN1ObjectIdentifier9.branch("10");
        id_aa_encrypKeyPref = aSN1ObjectIdentifier9.branch("11");
        id_aa_signingCertificate = aSN1ObjectIdentifier9.branch("12");
        id_aa_signingCertificateV2 = aSN1ObjectIdentifier9.branch("47");
        id_aa_contentIdentifier = aSN1ObjectIdentifier9.branch("7");
        id_aa_signatureTimeStampToken = aSN1ObjectIdentifier9.branch("14");
        ASN1ObjectIdentifier branch4 = aSN1ObjectIdentifier9.branch(str3);
        id_aa_ets_sigPolicyId = branch4;
        ASN1ObjectIdentifier branch5 = aSN1ObjectIdentifier9.branch(str);
        id_aa_ets_commitmentType = branch5;
        ASN1ObjectIdentifier branch6 = aSN1ObjectIdentifier9.branch("17");
        id_aa_ets_signerLocation = branch6;
        id_aa_ets_signerAttr = aSN1ObjectIdentifier9.branch("18");
        ASN1ObjectIdentifier branch7 = aSN1ObjectIdentifier9.branch("19");
        id_aa_ets_otherSigCert = branch7;
        id_aa_ets_contentTimestamp = aSN1ObjectIdentifier9.branch("20");
        id_aa_ets_certificateRefs = aSN1ObjectIdentifier9.branch("21");
        id_aa_ets_revocationRefs = aSN1ObjectIdentifier9.branch(str4);
        id_aa_ets_certValues = aSN1ObjectIdentifier9.branch("23");
        id_aa_ets_revocationValues = aSN1ObjectIdentifier9.branch("24");
        id_aa_ets_escTimeStamp = aSN1ObjectIdentifier9.branch("25");
        id_aa_ets_certCRLTimestamp = aSN1ObjectIdentifier9.branch("26");
        id_aa_ets_archiveTimestamp = aSN1ObjectIdentifier9.branch("27");
        id_aa_decryptKeyID = aSN1ObjectIdentifier9.branch("37");
        id_aa_implCryptoAlgs = aSN1ObjectIdentifier9.branch("38");
        id_aa_asymmDecryptKeyID = aSN1ObjectIdentifier9.branch("54");
        id_aa_implCompressAlgs = aSN1ObjectIdentifier9.branch("43");
        id_aa_communityIdentifiers = aSN1ObjectIdentifier9.branch("40");
        id_aa_sigPolicyId = branch4;
        id_aa_commitmentType = branch5;
        id_aa_signerLocation = branch6;
        id_aa_otherSigCert = branch7;
        ASN1ObjectIdentifier aSN1ObjectIdentifier10 = new ASN1ObjectIdentifier("1.2.840.113549.1.12");
        pkcs_12 = aSN1ObjectIdentifier10;
        ASN1ObjectIdentifier branch8 = aSN1ObjectIdentifier10.branch("10.1");
        bagtypes = branch8;
        keyBag = branch8.branch(DiskLruCache.VERSION_1);
        pkcs8ShroudedKeyBag = branch8.branch("2");
        certBag = branch8.branch("3");
        crlBag = branch8.branch("4");
        secretBag = branch8.branch("5");
        safeContentsBag = branch8.branch(Constants.GROUP_VIDEOS_NAME);
        ASN1ObjectIdentifier branch9 = aSN1ObjectIdentifier10.branch(DiskLruCache.VERSION_1);
        pkcs_12PbeIds = branch9;
        pbeWithSHAAnd128BitRC4 = branch9.branch(DiskLruCache.VERSION_1);
        pbeWithSHAAnd40BitRC4 = branch9.branch("2");
        pbeWithSHAAnd3_KeyTripleDES_CBC = branch9.branch("3");
        pbeWithSHAAnd2_KeyTripleDES_CBC = branch9.branch("4");
        pbeWithSHAAnd128BitRC2_CBC = branch9.branch("5");
        pbeWithSHAAnd40BitRC2_CBC = branch9.branch(Constants.GROUP_VIDEOS_NAME);
        pbewithSHAAnd40BitRC2_CBC = branch9.branch(Constants.GROUP_VIDEOS_NAME);
    }
}
