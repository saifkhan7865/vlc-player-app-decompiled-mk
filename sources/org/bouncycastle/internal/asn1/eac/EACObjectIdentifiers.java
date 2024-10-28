package org.bouncycastle.internal.asn1.eac;

import okhttp3.internal.cache.DiskLruCache;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.videolan.resources.Constants;

public interface EACObjectIdentifiers {
    public static final ASN1ObjectIdentifier bsi_de;
    public static final ASN1ObjectIdentifier id_CA;
    public static final ASN1ObjectIdentifier id_CA_DH;
    public static final ASN1ObjectIdentifier id_CA_DH_3DES_CBC_CBC;
    public static final ASN1ObjectIdentifier id_CA_ECDH;
    public static final ASN1ObjectIdentifier id_CA_ECDH_3DES_CBC_CBC;
    public static final ASN1ObjectIdentifier id_EAC_ePassport;
    public static final ASN1ObjectIdentifier id_PK;
    public static final ASN1ObjectIdentifier id_PK_DH;
    public static final ASN1ObjectIdentifier id_PK_ECDH;
    public static final ASN1ObjectIdentifier id_TA;
    public static final ASN1ObjectIdentifier id_TA_ECDSA;
    public static final ASN1ObjectIdentifier id_TA_ECDSA_SHA_1;
    public static final ASN1ObjectIdentifier id_TA_ECDSA_SHA_224;
    public static final ASN1ObjectIdentifier id_TA_ECDSA_SHA_256;
    public static final ASN1ObjectIdentifier id_TA_ECDSA_SHA_384;
    public static final ASN1ObjectIdentifier id_TA_ECDSA_SHA_512;
    public static final ASN1ObjectIdentifier id_TA_RSA;
    public static final ASN1ObjectIdentifier id_TA_RSA_PSS_SHA_1;
    public static final ASN1ObjectIdentifier id_TA_RSA_PSS_SHA_256;
    public static final ASN1ObjectIdentifier id_TA_RSA_PSS_SHA_512;
    public static final ASN1ObjectIdentifier id_TA_RSA_v1_5_SHA_1;
    public static final ASN1ObjectIdentifier id_TA_RSA_v1_5_SHA_256;
    public static final ASN1ObjectIdentifier id_TA_RSA_v1_5_SHA_512;

    static {
        ASN1ObjectIdentifier aSN1ObjectIdentifier = new ASN1ObjectIdentifier("0.4.0.127.0.7");
        bsi_de = aSN1ObjectIdentifier;
        ASN1ObjectIdentifier branch = aSN1ObjectIdentifier.branch("2.2.1");
        id_PK = branch;
        id_PK_DH = branch.branch(DiskLruCache.VERSION_1);
        id_PK_ECDH = branch.branch("2");
        ASN1ObjectIdentifier branch2 = aSN1ObjectIdentifier.branch("2.2.3");
        id_CA = branch2;
        ASN1ObjectIdentifier branch3 = branch2.branch(DiskLruCache.VERSION_1);
        id_CA_DH = branch3;
        id_CA_DH_3DES_CBC_CBC = branch3.branch(DiskLruCache.VERSION_1);
        ASN1ObjectIdentifier branch4 = branch2.branch("2");
        id_CA_ECDH = branch4;
        id_CA_ECDH_3DES_CBC_CBC = branch4.branch(DiskLruCache.VERSION_1);
        ASN1ObjectIdentifier branch5 = aSN1ObjectIdentifier.branch("2.2.2");
        id_TA = branch5;
        ASN1ObjectIdentifier branch6 = branch5.branch(DiskLruCache.VERSION_1);
        id_TA_RSA = branch6;
        id_TA_RSA_v1_5_SHA_1 = branch6.branch(DiskLruCache.VERSION_1);
        id_TA_RSA_v1_5_SHA_256 = branch6.branch("2");
        id_TA_RSA_PSS_SHA_1 = branch6.branch("3");
        id_TA_RSA_PSS_SHA_256 = branch6.branch("4");
        id_TA_RSA_v1_5_SHA_512 = branch6.branch("5");
        id_TA_RSA_PSS_SHA_512 = branch6.branch(Constants.GROUP_VIDEOS_NAME);
        ASN1ObjectIdentifier branch7 = branch5.branch("2");
        id_TA_ECDSA = branch7;
        id_TA_ECDSA_SHA_1 = branch7.branch(DiskLruCache.VERSION_1);
        id_TA_ECDSA_SHA_224 = branch7.branch("2");
        id_TA_ECDSA_SHA_256 = branch7.branch("3");
        id_TA_ECDSA_SHA_384 = branch7.branch("4");
        id_TA_ECDSA_SHA_512 = branch7.branch("5");
        id_EAC_ePassport = aSN1ObjectIdentifier.branch("3.1.2.1");
    }
}
