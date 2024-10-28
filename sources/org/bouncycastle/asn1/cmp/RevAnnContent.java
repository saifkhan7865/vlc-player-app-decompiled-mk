package org.bouncycastle.asn1.cmp;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1GeneralizedTime;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.crmf.CertId;
import org.bouncycastle.asn1.x509.Extensions;

public class RevAnnContent extends ASN1Object {
    private final ASN1GeneralizedTime badSinceDate;
    private final CertId certId;
    private Extensions crlDetails;
    private final PKIStatus status;
    private final ASN1GeneralizedTime willBeRevokedAt;

    private RevAnnContent(ASN1Sequence aSN1Sequence) {
        this.status = PKIStatus.getInstance(aSN1Sequence.getObjectAt(0));
        this.certId = CertId.getInstance(aSN1Sequence.getObjectAt(1));
        this.willBeRevokedAt = ASN1GeneralizedTime.getInstance(aSN1Sequence.getObjectAt(2));
        this.badSinceDate = ASN1GeneralizedTime.getInstance(aSN1Sequence.getObjectAt(3));
        if (aSN1Sequence.size() > 4) {
            this.crlDetails = Extensions.getInstance(aSN1Sequence.getObjectAt(4));
        }
    }

    public RevAnnContent(PKIStatus pKIStatus, CertId certId2, ASN1GeneralizedTime aSN1GeneralizedTime, ASN1GeneralizedTime aSN1GeneralizedTime2) {
        this(pKIStatus, certId2, aSN1GeneralizedTime, aSN1GeneralizedTime2, (Extensions) null);
    }

    public RevAnnContent(PKIStatus pKIStatus, CertId certId2, ASN1GeneralizedTime aSN1GeneralizedTime, ASN1GeneralizedTime aSN1GeneralizedTime2, Extensions extensions) {
        this.status = pKIStatus;
        this.certId = certId2;
        this.willBeRevokedAt = aSN1GeneralizedTime;
        this.badSinceDate = aSN1GeneralizedTime2;
        this.crlDetails = extensions;
    }

    public static RevAnnContent getInstance(Object obj) {
        if (obj instanceof RevAnnContent) {
            return (RevAnnContent) obj;
        }
        if (obj != null) {
            return new RevAnnContent(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public ASN1GeneralizedTime getBadSinceDate() {
        return this.badSinceDate;
    }

    public CertId getCertId() {
        return this.certId;
    }

    public Extensions getCrlDetails() {
        return this.crlDetails;
    }

    public PKIStatus getStatus() {
        return this.status;
    }

    public ASN1GeneralizedTime getWillBeRevokedAt() {
        return this.willBeRevokedAt;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(5);
        aSN1EncodableVector.add(this.status);
        aSN1EncodableVector.add(this.certId);
        aSN1EncodableVector.add(this.willBeRevokedAt);
        aSN1EncodableVector.add(this.badSinceDate);
        Extensions extensions = this.crlDetails;
        if (extensions != null) {
            aSN1EncodableVector.add(extensions);
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
