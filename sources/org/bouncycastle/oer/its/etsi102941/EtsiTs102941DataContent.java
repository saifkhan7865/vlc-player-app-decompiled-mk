package org.bouncycastle.oer.its.etsi102941;

import org.bouncycastle.asn1.ASN1Choice;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERTaggedObject;

public class EtsiTs102941DataContent extends ASN1Object implements ASN1Choice {
    public static final int authorizationRequest = 2;
    public static final int authorizationResponse = 3;
    public static final int authorizationValidationRequest = 7;
    public static final int authorizationValidationResponse = 8;
    public static final int caCertificateRequest = 9;
    public static final int certificateRevocationList = 4;
    public static final int certificateTrustListRca = 6;
    public static final int certificateTrustListTlm = 5;
    public static final int doubleSignedlinkCertificateRca = 12;
    public static final int enrolmentRequest = 0;
    public static final int enrolmentResponse = 1;
    public static final int linkCertificateTlm = 10;
    public static final int singleSignedLinkCertificateRca = 11;
    private final int choice;
    private final ASN1Encodable etsiTs102941DataContent;

    public EtsiTs102941DataContent(int i, ASN1Encodable aSN1Encodable) {
        this.choice = i;
        this.etsiTs102941DataContent = aSN1Encodable;
    }

    private EtsiTs102941DataContent(ASN1TaggedObject aSN1TaggedObject) {
        ASN1Encodable instance;
        int tagNo = aSN1TaggedObject.getTagNo();
        this.choice = tagNo;
        switch (tagNo) {
            case 0:
                instance = InnerEcRequestSignedForPop.getInstance(aSN1TaggedObject.getExplicitBaseObject());
                break;
            case 1:
                instance = InnerEcResponse.getInstance(aSN1TaggedObject.getExplicitBaseObject());
                break;
            case 2:
                instance = InnerAtRequest.getInstance(aSN1TaggedObject.getExplicitBaseObject());
                break;
            case 3:
                instance = InnerAtResponse.getInstance(aSN1TaggedObject.getExplicitBaseObject());
                break;
            case 5:
                instance = ToBeSignedTlmCtl.getInstance(aSN1TaggedObject.getExplicitBaseObject());
                break;
            case 6:
                instance = ToBeSignedRcaCtl.getInstance(aSN1TaggedObject.getExplicitBaseObject());
                break;
            case 7:
                instance = AuthorizationValidationRequest.getInstance(aSN1TaggedObject.getExplicitBaseObject());
                break;
            case 8:
                instance = AuthorizationValidationResponse.getInstance(aSN1TaggedObject.getExplicitBaseObject());
                break;
            case 9:
                instance = CaCertificateRequest.getInstance(aSN1TaggedObject.getExplicitBaseObject());
                break;
            default:
                throw new IllegalArgumentException("choice not implemented " + tagNo);
        }
        this.etsiTs102941DataContent = instance;
    }

    public static EtsiTs102941DataContent getInstance(Object obj) {
        if (obj instanceof EtsiTs102941DataContent) {
            return (EtsiTs102941DataContent) obj;
        }
        if (obj != null) {
            return new EtsiTs102941DataContent(ASN1TaggedObject.getInstance(obj, 128));
        }
        return null;
    }

    public int getChoice() {
        return this.choice;
    }

    public ASN1Encodable getEtsiTs102941DataContent() {
        return this.etsiTs102941DataContent;
    }

    public ASN1Primitive toASN1Primitive() {
        return new DERTaggedObject(this.choice, this.etsiTs102941DataContent);
    }
}
