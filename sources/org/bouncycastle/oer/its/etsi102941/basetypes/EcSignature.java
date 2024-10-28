package org.bouncycastle.oer.its.etsi102941.basetypes;

import org.bouncycastle.asn1.ASN1Choice;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.oer.its.etsi103097.EtsiTs103097DataEncrypted;
import org.bouncycastle.oer.its.etsi103097.EtsiTs103097DataSignedExternalPayload;

public class EcSignature extends ASN1Object implements ASN1Choice {
    public static final int ecSignature = 1;
    public static final int encryptedEcSignature = 0;
    private final ASN1Encodable _ecSignature;
    private final int choice;

    public EcSignature(int i, ASN1Encodable aSN1Encodable) {
        this.choice = i;
        this._ecSignature = aSN1Encodable;
    }

    private EcSignature(ASN1TaggedObject aSN1TaggedObject) {
        ASN1Encodable instance;
        int tagNo = aSN1TaggedObject.getTagNo();
        this.choice = tagNo;
        if (tagNo == 0) {
            instance = EtsiTs103097DataEncrypted.getInstance(aSN1TaggedObject.getExplicitBaseObject());
        } else if (tagNo == 1) {
            instance = EtsiTs103097DataSignedExternalPayload.getInstance(aSN1TaggedObject.getExplicitBaseObject());
        } else {
            throw new IllegalArgumentException("invalid choice value " + tagNo);
        }
        this._ecSignature = instance;
    }

    public static EcSignature getInstance(Object obj) {
        if (obj instanceof EcSignature) {
            return (EcSignature) obj;
        }
        if (obj != null) {
            return new EcSignature(ASN1TaggedObject.getInstance(obj, 128));
        }
        return null;
    }

    public int getChoice() {
        return this.choice;
    }

    public ASN1Encodable getEcSignature() {
        return this._ecSignature;
    }

    public ASN1Primitive toASN1Primitive() {
        return new DERTaggedObject(this.choice, this._ecSignature);
    }
}
