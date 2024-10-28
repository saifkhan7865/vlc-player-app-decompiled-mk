package org.bouncycastle.asn1.cmc;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;

public class PKIResponse extends ASN1Object {
    public static final TaggedContentInfo[] EMPTY_CMS_SEQUENCE = new TaggedContentInfo[0];
    public static final OtherMsg[] EMPTY_OTHER_MSG = new OtherMsg[0];
    private final ASN1Sequence cmsSequence;
    private final ASN1Sequence controlSequence;
    private final ASN1Sequence otherMsgSequence;

    private PKIResponse(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() == 3) {
            this.controlSequence = ASN1Sequence.getInstance(aSN1Sequence.getObjectAt(0));
            this.cmsSequence = ASN1Sequence.getInstance(aSN1Sequence.getObjectAt(1));
            this.otherMsgSequence = ASN1Sequence.getInstance(aSN1Sequence.getObjectAt(2));
            return;
        }
        throw new IllegalArgumentException("incorrect sequence size");
    }

    public PKIResponse(TaggedAttribute taggedAttribute) {
        this.controlSequence = new DERSequence((ASN1Encodable) taggedAttribute);
        this.cmsSequence = new DERSequence();
        this.otherMsgSequence = new DERSequence();
    }

    public PKIResponse(TaggedAttribute[] taggedAttributeArr, TaggedContentInfo[] taggedContentInfoArr, OtherMsg[] otherMsgArr) {
        this.controlSequence = new DERSequence((ASN1Encodable[]) taggedAttributeArr);
        this.cmsSequence = new DERSequence((ASN1Encodable[]) taggedContentInfoArr);
        this.otherMsgSequence = new DERSequence((ASN1Encodable[]) otherMsgArr);
    }

    public static PKIResponse getInstance(Object obj) {
        if (obj instanceof PKIResponse) {
            return (PKIResponse) obj;
        }
        if (obj != null) {
            return new PKIResponse(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public static PKIResponse getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public ASN1Sequence getCmsSequence() {
        return this.cmsSequence;
    }

    public ASN1Sequence getControlSequence() {
        return this.controlSequence;
    }

    public ASN1Sequence getOtherMsgSequence() {
        return this.otherMsgSequence;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(3);
        aSN1EncodableVector.add(this.controlSequence);
        aSN1EncodableVector.add(this.cmsSequence);
        aSN1EncodableVector.add(this.otherMsgSequence);
        return new DERSequence(aSN1EncodableVector);
    }
}
