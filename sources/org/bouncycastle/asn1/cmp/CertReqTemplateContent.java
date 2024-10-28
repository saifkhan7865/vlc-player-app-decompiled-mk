package org.bouncycastle.asn1.cmp;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.crmf.CertTemplate;

public class CertReqTemplateContent extends ASN1Object {
    private final CertTemplate certTemplate;
    private final ASN1Sequence keySpec;

    private CertReqTemplateContent(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() == 1 || aSN1Sequence.size() == 2) {
            this.certTemplate = CertTemplate.getInstance(aSN1Sequence.getObjectAt(0));
            this.keySpec = aSN1Sequence.size() > 1 ? ASN1Sequence.getInstance(aSN1Sequence.getObjectAt(1)) : null;
            return;
        }
        throw new IllegalArgumentException("expected sequence size of 1 or 2");
    }

    public CertReqTemplateContent(CertTemplate certTemplate2, ASN1Sequence aSN1Sequence) {
        this.certTemplate = certTemplate2;
        this.keySpec = aSN1Sequence;
    }

    public static CertReqTemplateContent getInstance(Object obj) {
        if (obj instanceof CertReqTemplateContent) {
            return (CertReqTemplateContent) obj;
        }
        if (obj != null) {
            return new CertReqTemplateContent(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public CertTemplate getCertTemplate() {
        return this.certTemplate;
    }

    public ASN1Sequence getKeySpec() {
        return this.keySpec;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(2);
        aSN1EncodableVector.add(this.certTemplate);
        ASN1Sequence aSN1Sequence = this.keySpec;
        if (aSN1Sequence != null) {
            aSN1EncodableVector.add(aSN1Sequence);
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
