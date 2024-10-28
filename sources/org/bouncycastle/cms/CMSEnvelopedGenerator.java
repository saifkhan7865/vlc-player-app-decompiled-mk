package org.bouncycastle.cms;

import java.util.ArrayList;
import java.util.List;
import org.bouncycastle.asn1.cms.OriginatorInfo;
import org.bouncycastle.asn1.kisa.KISAObjectIdentifiers;
import org.bouncycastle.asn1.misc.MiscObjectIdentifiers;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.ntt.NTTObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.sec.SECObjectIdentifiers;
import org.bouncycastle.asn1.x9.X9ObjectIdentifiers;

public class CMSEnvelopedGenerator {
    public static final String AES128_CBC = NISTObjectIdentifiers.id_aes128_CBC.getId();
    public static final String AES128_WRAP = NISTObjectIdentifiers.id_aes128_wrap.getId();
    public static final String AES192_CBC = NISTObjectIdentifiers.id_aes192_CBC.getId();
    public static final String AES192_WRAP = NISTObjectIdentifiers.id_aes192_wrap.getId();
    public static final String AES256_CBC = NISTObjectIdentifiers.id_aes256_CBC.getId();
    public static final String AES256_WRAP = NISTObjectIdentifiers.id_aes256_wrap.getId();
    public static final String CAMELLIA128_CBC = NTTObjectIdentifiers.id_camellia128_cbc.getId();
    public static final String CAMELLIA128_WRAP = NTTObjectIdentifiers.id_camellia128_wrap.getId();
    public static final String CAMELLIA192_CBC = NTTObjectIdentifiers.id_camellia192_cbc.getId();
    public static final String CAMELLIA192_WRAP = NTTObjectIdentifiers.id_camellia192_wrap.getId();
    public static final String CAMELLIA256_CBC = NTTObjectIdentifiers.id_camellia256_cbc.getId();
    public static final String CAMELLIA256_WRAP = NTTObjectIdentifiers.id_camellia256_wrap.getId();
    public static final String CAST5_CBC = MiscObjectIdentifiers.cast5CBC.getId();
    public static final String DES_EDE3_CBC = PKCSObjectIdentifiers.des_EDE3_CBC.getId();
    public static final String DES_EDE3_WRAP = PKCSObjectIdentifiers.id_alg_CMS3DESwrap.getId();
    public static final String ECDH_SHA1KDF = X9ObjectIdentifiers.dhSinglePass_stdDH_sha1kdf_scheme.getId();
    public static final String ECMQV_SHA1KDF = X9ObjectIdentifiers.mqvSinglePass_sha1kdf_scheme.getId();
    public static final String ECMQV_SHA224KDF = SECObjectIdentifiers.mqvSinglePass_sha224kdf_scheme.getId();
    public static final String ECMQV_SHA256KDF = SECObjectIdentifiers.mqvSinglePass_sha256kdf_scheme.getId();
    public static final String ECMQV_SHA384KDF = SECObjectIdentifiers.mqvSinglePass_sha384kdf_scheme.getId();
    public static final String ECMQV_SHA512KDF = SECObjectIdentifiers.mqvSinglePass_sha512kdf_scheme.getId();
    public static final String IDEA_CBC = MiscObjectIdentifiers.as_sys_sec_alg_ideaCBC.getId();
    public static final String RC2_CBC = PKCSObjectIdentifiers.RC2_CBC.getId();
    public static final String SEED_CBC = KISAObjectIdentifiers.id_seedCBC.getId();
    public static final String SEED_WRAP = KISAObjectIdentifiers.id_npki_app_cmsSeed_wrap.getId();
    protected OriginatorInfo originatorInfo;
    final List recipientInfoGenerators = new ArrayList();
    protected CMSAttributeTableGenerator unprotectedAttributeGenerator = null;

    protected CMSEnvelopedGenerator() {
    }

    public void addRecipientInfoGenerator(RecipientInfoGenerator recipientInfoGenerator) {
        this.recipientInfoGenerators.add(recipientInfoGenerator);
    }

    public void setOriginatorInfo(OriginatorInformation originatorInformation) {
        this.originatorInfo = originatorInformation.toASN1Structure();
    }

    public void setUnprotectedAttributeGenerator(CMSAttributeTableGenerator cMSAttributeTableGenerator) {
        this.unprotectedAttributeGenerator = cMSAttributeTableGenerator;
    }
}
