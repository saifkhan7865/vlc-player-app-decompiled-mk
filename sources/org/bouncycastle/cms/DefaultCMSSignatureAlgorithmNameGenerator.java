package org.bouncycastle.cms;

import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.bc.BCObjectIdentifiers;
import org.bouncycastle.asn1.bsi.BSIObjectIdentifiers;
import org.bouncycastle.asn1.cms.CMSObjectIdentifiers;
import org.bouncycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
import org.bouncycastle.asn1.eac.EACObjectIdentifiers;
import org.bouncycastle.asn1.edec.EdECObjectIdentifiers;
import org.bouncycastle.asn1.gm.GMObjectIdentifiers;
import org.bouncycastle.asn1.misc.MiscObjectIdentifiers;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.rosstandart.RosstandartObjectIdentifiers;
import org.bouncycastle.asn1.teletrust.TeleTrusTObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.X509ObjectIdentifiers;
import org.bouncycastle.asn1.x9.X9ObjectIdentifiers;
import org.bouncycastle.jcajce.spec.EdDSAParameterSpec;

public class DefaultCMSSignatureAlgorithmNameGenerator implements CMSSignatureAlgorithmNameGenerator {
    private final Map digestAlgs;
    private final Map encryptionAlgs;
    private final Map simpleAlgs;

    public DefaultCMSSignatureAlgorithmNameGenerator() {
        HashMap hashMap = new HashMap();
        this.encryptionAlgs = hashMap;
        HashMap hashMap2 = new HashMap();
        this.digestAlgs = hashMap2;
        HashMap hashMap3 = new HashMap();
        this.simpleAlgs = hashMap3;
        addEntries(NISTObjectIdentifiers.dsa_with_sha224, "SHA224", "DSA");
        addEntries(NISTObjectIdentifiers.dsa_with_sha256, "SHA256", "DSA");
        addEntries(NISTObjectIdentifiers.dsa_with_sha384, "SHA384", "DSA");
        addEntries(NISTObjectIdentifiers.dsa_with_sha512, "SHA512", "DSA");
        addEntries(NISTObjectIdentifiers.id_dsa_with_sha3_224, "SHA3-224", "DSA");
        addEntries(NISTObjectIdentifiers.id_dsa_with_sha3_256, "SHA3-256", "DSA");
        addEntries(NISTObjectIdentifiers.id_dsa_with_sha3_384, "SHA3-384", "DSA");
        addEntries(NISTObjectIdentifiers.id_dsa_with_sha3_512, "SHA3-512", "DSA");
        addEntries(NISTObjectIdentifiers.id_rsassa_pkcs1_v1_5_with_sha3_224, "SHA3-224", "RSA");
        addEntries(NISTObjectIdentifiers.id_rsassa_pkcs1_v1_5_with_sha3_256, "SHA3-256", "RSA");
        addEntries(NISTObjectIdentifiers.id_rsassa_pkcs1_v1_5_with_sha3_384, "SHA3-384", "RSA");
        addEntries(NISTObjectIdentifiers.id_rsassa_pkcs1_v1_5_with_sha3_512, "SHA3-512", "RSA");
        addEntries(NISTObjectIdentifiers.id_ecdsa_with_sha3_224, "SHA3-224", "ECDSA");
        addEntries(NISTObjectIdentifiers.id_ecdsa_with_sha3_256, "SHA3-256", "ECDSA");
        addEntries(NISTObjectIdentifiers.id_ecdsa_with_sha3_384, "SHA3-384", "ECDSA");
        addEntries(NISTObjectIdentifiers.id_ecdsa_with_sha3_512, "SHA3-512", "ECDSA");
        addEntries(OIWObjectIdentifiers.dsaWithSHA1, "SHA1", "DSA");
        addEntries(OIWObjectIdentifiers.md4WithRSA, "MD4", "RSA");
        addEntries(OIWObjectIdentifiers.md4WithRSAEncryption, "MD4", "RSA");
        addEntries(OIWObjectIdentifiers.md5WithRSA, "MD5", "RSA");
        addEntries(OIWObjectIdentifiers.sha1WithRSA, "SHA1", "RSA");
        addEntries(PKCSObjectIdentifiers.md2WithRSAEncryption, "MD2", "RSA");
        addEntries(PKCSObjectIdentifiers.md4WithRSAEncryption, "MD4", "RSA");
        addEntries(PKCSObjectIdentifiers.md5WithRSAEncryption, "MD5", "RSA");
        addEntries(PKCSObjectIdentifiers.sha1WithRSAEncryption, "SHA1", "RSA");
        addEntries(PKCSObjectIdentifiers.sha224WithRSAEncryption, "SHA224", "RSA");
        addEntries(PKCSObjectIdentifiers.sha256WithRSAEncryption, "SHA256", "RSA");
        addEntries(PKCSObjectIdentifiers.sha384WithRSAEncryption, "SHA384", "RSA");
        addEntries(PKCSObjectIdentifiers.sha512WithRSAEncryption, "SHA512", "RSA");
        addEntries(PKCSObjectIdentifiers.sha512_224WithRSAEncryption, "SHA512(224)", "RSA");
        addEntries(PKCSObjectIdentifiers.sha512_256WithRSAEncryption, "SHA512(256)", "RSA");
        addEntries(NISTObjectIdentifiers.id_rsassa_pkcs1_v1_5_with_sha3_224, "SHA3-224", "RSA");
        addEntries(NISTObjectIdentifiers.id_rsassa_pkcs1_v1_5_with_sha3_256, "SHA3-256", "RSA");
        addEntries(NISTObjectIdentifiers.id_rsassa_pkcs1_v1_5_with_sha3_384, "SHA3-384", "RSA");
        addEntries(NISTObjectIdentifiers.id_rsassa_pkcs1_v1_5_with_sha3_512, "SHA3-512", "RSA");
        addEntries(CMSObjectIdentifiers.id_RSASSA_PSS_SHAKE128, "SHAKE128", "RSAPSS");
        addEntries(CMSObjectIdentifiers.id_RSASSA_PSS_SHAKE256, "SHAKE256", "RSAPSS");
        addEntries(TeleTrusTObjectIdentifiers.rsaSignatureWithripemd128, "RIPEMD128", "RSA");
        addEntries(TeleTrusTObjectIdentifiers.rsaSignatureWithripemd160, "RIPEMD160", "RSA");
        addEntries(TeleTrusTObjectIdentifiers.rsaSignatureWithripemd256, "RIPEMD256", "RSA");
        addEntries(X9ObjectIdentifiers.ecdsa_with_SHA1, "SHA1", "ECDSA");
        addEntries(X9ObjectIdentifiers.ecdsa_with_SHA224, "SHA224", "ECDSA");
        addEntries(X9ObjectIdentifiers.ecdsa_with_SHA256, "SHA256", "ECDSA");
        addEntries(X9ObjectIdentifiers.ecdsa_with_SHA384, "SHA384", "ECDSA");
        addEntries(X9ObjectIdentifiers.ecdsa_with_SHA512, "SHA512", "ECDSA");
        addEntries(CMSObjectIdentifiers.id_ecdsa_with_shake128, "SHAKE128", "ECDSA");
        addEntries(CMSObjectIdentifiers.id_ecdsa_with_shake256, "SHAKE256", "ECDSA");
        String str = "DSA";
        addEntries(X9ObjectIdentifiers.id_dsa_with_sha1, "SHA1", str);
        addEntries(EACObjectIdentifiers.id_TA_ECDSA_SHA_1, "SHA1", "ECDSA");
        addEntries(EACObjectIdentifiers.id_TA_ECDSA_SHA_224, "SHA224", "ECDSA");
        addEntries(EACObjectIdentifiers.id_TA_ECDSA_SHA_256, "SHA256", "ECDSA");
        addEntries(EACObjectIdentifiers.id_TA_ECDSA_SHA_384, "SHA384", "ECDSA");
        addEntries(EACObjectIdentifiers.id_TA_ECDSA_SHA_512, "SHA512", "ECDSA");
        addEntries(EACObjectIdentifiers.id_TA_RSA_v1_5_SHA_1, "SHA1", "RSA");
        addEntries(EACObjectIdentifiers.id_TA_RSA_v1_5_SHA_256, "SHA256", "RSA");
        addEntries(EACObjectIdentifiers.id_TA_RSA_PSS_SHA_1, "SHA1", "RSAandMGF1");
        addEntries(EACObjectIdentifiers.id_TA_RSA_PSS_SHA_256, "SHA256", "RSAandMGF1");
        addEntries(BSIObjectIdentifiers.ecdsa_plain_SHA1, "SHA1", "PLAIN-ECDSA");
        addEntries(BSIObjectIdentifiers.ecdsa_plain_SHA224, "SHA224", "PLAIN-ECDSA");
        addEntries(BSIObjectIdentifiers.ecdsa_plain_SHA256, "SHA256", "PLAIN-ECDSA");
        addEntries(BSIObjectIdentifiers.ecdsa_plain_SHA384, "SHA384", "PLAIN-ECDSA");
        addEntries(BSIObjectIdentifiers.ecdsa_plain_SHA512, "SHA512", "PLAIN-ECDSA");
        addEntries(BSIObjectIdentifiers.ecdsa_plain_RIPEMD160, "RIPEMD160", "PLAIN-ECDSA");
        addEntries(BSIObjectIdentifiers.ecdsa_plain_SHA3_224, "SHA3-224", "PLAIN-ECDSA");
        addEntries(BSIObjectIdentifiers.ecdsa_plain_SHA3_256, "SHA3-256", "PLAIN-ECDSA");
        addEntries(BSIObjectIdentifiers.ecdsa_plain_SHA3_384, "SHA3-384", "PLAIN-ECDSA");
        String str2 = "SHA3-512";
        addEntries(BSIObjectIdentifiers.ecdsa_plain_SHA3_512, str2, "PLAIN-ECDSA");
        addEntries(GMObjectIdentifiers.sm2sign_with_sha256, "SHA256", "SM2");
        addEntries(GMObjectIdentifiers.sm2sign_with_sm3, "SM3", "SM2");
        addEntries(BCObjectIdentifiers.sphincs256_with_SHA512, "SHA512", "SPHINCS256");
        addEntries(BCObjectIdentifiers.sphincs256_with_SHA3_512, str2, "SPHINCS256");
        addEntries(BCObjectIdentifiers.picnic_with_shake256, "SHAKE256", "Picnic");
        addEntries(BCObjectIdentifiers.picnic_with_sha512, "SHA512", "Picnic");
        addEntries(BCObjectIdentifiers.picnic_with_sha3_512, str2, "Picnic");
        HashMap hashMap4 = hashMap;
        hashMap4.put(X9ObjectIdentifiers.id_dsa, str);
        hashMap4.put(PKCSObjectIdentifiers.rsaEncryption, "RSA");
        hashMap4.put(TeleTrusTObjectIdentifiers.teleTrusTRSAsignatureAlgorithm, "RSA");
        hashMap4.put(X509ObjectIdentifiers.id_ea_rsa, "RSA");
        hashMap4.put(PKCSObjectIdentifiers.id_RSASSA_PSS, "RSAandMGF1");
        hashMap4.put(CryptoProObjectIdentifiers.gostR3410_94, "GOST3410");
        hashMap4.put(CryptoProObjectIdentifiers.gostR3410_2001, "ECGOST3410");
        hashMap4.put(new ASN1ObjectIdentifier("1.3.6.1.4.1.5849.1.6.2"), "ECGOST3410");
        hashMap4.put(new ASN1ObjectIdentifier("1.3.6.1.4.1.5849.1.1.5"), "GOST3410");
        hashMap4.put(RosstandartObjectIdentifiers.id_tc26_gost_3410_12_256, "ECGOST3410-2012-256");
        hashMap4.put(RosstandartObjectIdentifiers.id_tc26_gost_3410_12_512, "ECGOST3410-2012-512");
        hashMap4.put(CryptoProObjectIdentifiers.gostR3411_94_with_gostR3410_2001, "ECGOST3410");
        hashMap4.put(CryptoProObjectIdentifiers.gostR3411_94_with_gostR3410_94, "GOST3410");
        hashMap4.put(RosstandartObjectIdentifiers.id_tc26_signwithdigest_gost_3410_12_256, "ECGOST3410-2012-256");
        hashMap4.put(RosstandartObjectIdentifiers.id_tc26_signwithdigest_gost_3410_12_512, "ECGOST3410-2012-512");
        hashMap4.put(X9ObjectIdentifiers.id_ecPublicKey, "ECDSA");
        HashMap hashMap5 = hashMap2;
        hashMap5.put(PKCSObjectIdentifiers.md2, "MD2");
        hashMap5.put(PKCSObjectIdentifiers.md4, "MD4");
        hashMap5.put(PKCSObjectIdentifiers.md5, "MD5");
        hashMap5.put(OIWObjectIdentifiers.idSHA1, "SHA1");
        hashMap5.put(NISTObjectIdentifiers.id_sha224, "SHA224");
        hashMap5.put(NISTObjectIdentifiers.id_sha256, "SHA256");
        hashMap5.put(NISTObjectIdentifiers.id_sha384, "SHA384");
        hashMap5.put(NISTObjectIdentifiers.id_sha512, "SHA512");
        hashMap5.put(NISTObjectIdentifiers.id_sha512_224, "SHA512(224)");
        hashMap5.put(NISTObjectIdentifiers.id_sha512_256, "SHA512(256)");
        hashMap5.put(NISTObjectIdentifiers.id_shake128, "SHAKE128");
        hashMap5.put(NISTObjectIdentifiers.id_shake256, "SHAKE256");
        hashMap5.put(NISTObjectIdentifiers.id_sha3_224, "SHA3-224");
        hashMap5.put(NISTObjectIdentifiers.id_sha3_256, "SHA3-256");
        hashMap5.put(NISTObjectIdentifiers.id_sha3_384, "SHA3-384");
        hashMap5.put(NISTObjectIdentifiers.id_sha3_512, str2);
        hashMap5.put(TeleTrusTObjectIdentifiers.ripemd128, "RIPEMD128");
        hashMap5.put(TeleTrusTObjectIdentifiers.ripemd160, "RIPEMD160");
        hashMap5.put(TeleTrusTObjectIdentifiers.ripemd256, "RIPEMD256");
        hashMap5.put(CryptoProObjectIdentifiers.gostR3411, "GOST3411");
        hashMap5.put(new ASN1ObjectIdentifier("1.3.6.1.4.1.5849.1.2.1"), "GOST3411");
        hashMap5.put(RosstandartObjectIdentifiers.id_tc26_gost_3411_12_256, "GOST3411-2012-256");
        hashMap5.put(RosstandartObjectIdentifiers.id_tc26_gost_3411_12_512, "GOST3411-2012-512");
        hashMap5.put(GMObjectIdentifiers.sm3, "SM3");
        HashMap hashMap6 = hashMap3;
        hashMap6.put(EdECObjectIdentifiers.id_Ed25519, EdDSAParameterSpec.Ed25519);
        hashMap6.put(EdECObjectIdentifiers.id_Ed448, EdDSAParameterSpec.Ed448);
        hashMap6.put(PKCSObjectIdentifiers.id_alg_hss_lms_hashsig, "LMS");
        hashMap6.put(MiscObjectIdentifiers.id_alg_composite, "COMPOSITE");
        hashMap6.put(BCObjectIdentifiers.falcon_512, "Falcon-512");
        hashMap6.put(BCObjectIdentifiers.falcon_1024, "Falcon-1024");
        hashMap6.put(BCObjectIdentifiers.dilithium2, "Dilithium2");
        hashMap6.put(BCObjectIdentifiers.dilithium3, "Dilithium3");
        hashMap6.put(BCObjectIdentifiers.dilithium5, "Dilithium5");
        hashMap6.put(BCObjectIdentifiers.sphincsPlus_sha2_128s, "SPHINCS+-SHA2-128s");
        hashMap6.put(BCObjectIdentifiers.sphincsPlus_sha2_128f, "SPHINCS+-SHA2-128f");
        hashMap6.put(BCObjectIdentifiers.sphincsPlus_sha2_192s, "SPHINCS+-SHA2-192s");
        hashMap6.put(BCObjectIdentifiers.sphincsPlus_sha2_192f, "SPHINCS+-SHA2-192f");
        hashMap6.put(BCObjectIdentifiers.sphincsPlus_sha2_256s, "SPHINCS+-SHA2-256s");
        hashMap6.put(BCObjectIdentifiers.sphincsPlus_sha2_256f, "SPHINCS+-SHA2-256f");
        hashMap6.put(BCObjectIdentifiers.sphincsPlus_shake_128s, "SPHINCS+-SHAKE-128s");
        hashMap6.put(BCObjectIdentifiers.sphincsPlus_shake_128f, "SPHINCS+-SHAKE-128f");
        hashMap6.put(BCObjectIdentifiers.sphincsPlus_shake_192s, "SPHINCS+-SHAKE-192s");
        hashMap6.put(BCObjectIdentifiers.sphincsPlus_shake_192f, "SPHINCS+-SHAKE-192f");
        hashMap6.put(BCObjectIdentifiers.sphincsPlus_shake_256s, "SPHINCS+-SHAKE-256s");
        hashMap6.put(BCObjectIdentifiers.sphincsPlus_shake_256f, "SPHINCS+-SHAKE-256f");
        hashMap6.put(BCObjectIdentifiers.dilithium2, "Dilithium2");
        hashMap6.put(BCObjectIdentifiers.dilithium3, "Dilithium3");
        hashMap6.put(BCObjectIdentifiers.dilithium5, "Dilithium5");
        hashMap6.put(BCObjectIdentifiers.picnic_signature, "Picnic");
    }

    private void addEntries(ASN1ObjectIdentifier aSN1ObjectIdentifier, String str, String str2) {
        this.digestAlgs.put(aSN1ObjectIdentifier, str);
        this.encryptionAlgs.put(aSN1ObjectIdentifier, str2);
    }

    private String getDigestAlgName(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        String str = (String) this.digestAlgs.get(aSN1ObjectIdentifier);
        return str != null ? str : aSN1ObjectIdentifier.getId();
    }

    private String getEncryptionAlgName(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        String str = (String) this.encryptionAlgs.get(aSN1ObjectIdentifier);
        return str != null ? str : aSN1ObjectIdentifier.getId();
    }

    public String getSignatureName(AlgorithmIdentifier algorithmIdentifier, AlgorithmIdentifier algorithmIdentifier2) {
        ASN1ObjectIdentifier algorithm = algorithmIdentifier2.getAlgorithm();
        String str = (String) this.simpleAlgs.get(algorithm);
        if (str != null) {
            return str;
        }
        if (algorithm.on(BCObjectIdentifiers.sphincsPlus)) {
            return "SPHINCSPlus";
        }
        String digestAlgName = getDigestAlgName(algorithm);
        if (!digestAlgName.equals(algorithm.getId())) {
            return digestAlgName + "with" + getEncryptionAlgName(algorithm);
        }
        return getDigestAlgName(algorithmIdentifier.getAlgorithm()) + "with" + getEncryptionAlgName(algorithm);
    }

    /* access modifiers changed from: protected */
    public void setSigningDigestAlgorithmMapping(ASN1ObjectIdentifier aSN1ObjectIdentifier, String str) {
        this.digestAlgs.put(aSN1ObjectIdentifier, str);
    }

    /* access modifiers changed from: protected */
    public void setSigningEncryptionAlgorithmMapping(ASN1ObjectIdentifier aSN1ObjectIdentifier, String str) {
        this.encryptionAlgs.put(aSN1ObjectIdentifier, str);
    }
}
