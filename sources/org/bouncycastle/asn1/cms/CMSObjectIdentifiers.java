package org.bouncycastle.asn1.cms;

import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;

public interface CMSObjectIdentifiers {
    public static final ASN1ObjectIdentifier authEnvelopedData = PKCSObjectIdentifiers.id_ct_authEnvelopedData;
    public static final ASN1ObjectIdentifier authenticatedData = PKCSObjectIdentifiers.id_ct_authData;
    public static final ASN1ObjectIdentifier compressedData = PKCSObjectIdentifiers.id_ct_compressedData;
    public static final ASN1ObjectIdentifier data = PKCSObjectIdentifiers.data;
    public static final ASN1ObjectIdentifier digestedData = PKCSObjectIdentifiers.digestedData;
    public static final ASN1ObjectIdentifier encryptedData = PKCSObjectIdentifiers.encryptedData;
    public static final ASN1ObjectIdentifier envelopedData = PKCSObjectIdentifiers.envelopedData;
    public static final ASN1ObjectIdentifier id_RSASSA_PSS_SHAKE128;
    public static final ASN1ObjectIdentifier id_RSASSA_PSS_SHAKE256;
    public static final ASN1ObjectIdentifier id_alg;
    public static final ASN1ObjectIdentifier id_ecdsa_with_shake128;
    public static final ASN1ObjectIdentifier id_ecdsa_with_shake256;
    public static final ASN1ObjectIdentifier id_ori;
    public static final ASN1ObjectIdentifier id_ori_kem;
    public static final ASN1ObjectIdentifier id_ri;
    public static final ASN1ObjectIdentifier id_ri_ocsp_response;
    public static final ASN1ObjectIdentifier id_ri_scvp;
    public static final ASN1ObjectIdentifier signedAndEnvelopedData = PKCSObjectIdentifiers.signedAndEnvelopedData;
    public static final ASN1ObjectIdentifier signedData = PKCSObjectIdentifiers.signedData;
    public static final ASN1ObjectIdentifier timestampedData = PKCSObjectIdentifiers.id_ct_timestampedData;
    public static final ASN1ObjectIdentifier zlibCompress = PKCSObjectIdentifiers.id_alg_zlibCompress;

    static {
        ASN1ObjectIdentifier aSN1ObjectIdentifier = new ASN1ObjectIdentifier("1.3.6.1.5.5.7.16");
        id_ri = aSN1ObjectIdentifier;
        id_ri_ocsp_response = aSN1ObjectIdentifier.branch("2");
        id_ri_scvp = aSN1ObjectIdentifier.branch("4");
        ASN1ObjectIdentifier aSN1ObjectIdentifier2 = new ASN1ObjectIdentifier("1.3.6.1.5.5.7.6");
        id_alg = aSN1ObjectIdentifier2;
        id_RSASSA_PSS_SHAKE128 = aSN1ObjectIdentifier2.branch("30");
        id_RSASSA_PSS_SHAKE256 = aSN1ObjectIdentifier2.branch("31");
        id_ecdsa_with_shake128 = aSN1ObjectIdentifier2.branch("32");
        id_ecdsa_with_shake256 = aSN1ObjectIdentifier2.branch("33");
        ASN1ObjectIdentifier aSN1ObjectIdentifier3 = new ASN1ObjectIdentifier("1.2.840.113549.1.9.16.13");
        id_ori = aSN1ObjectIdentifier3;
        id_ori_kem = aSN1ObjectIdentifier3.branch("3");
    }
}
