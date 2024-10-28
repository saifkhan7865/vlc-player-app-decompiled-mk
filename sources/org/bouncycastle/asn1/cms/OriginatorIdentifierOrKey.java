package org.bouncycastle.asn1.cms;

import org.bouncycastle.asn1.ASN1Choice;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.x509.SubjectKeyIdentifier;

public class OriginatorIdentifierOrKey extends ASN1Object implements ASN1Choice {
    private ASN1Encodable id;

    public OriginatorIdentifierOrKey(ASN1OctetString aSN1OctetString) {
        this(new SubjectKeyIdentifier(aSN1OctetString.getOctets()));
    }

    public OriginatorIdentifierOrKey(ASN1Primitive aSN1Primitive) {
        this.id = aSN1Primitive;
    }

    public OriginatorIdentifierOrKey(IssuerAndSerialNumber issuerAndSerialNumber) {
        this.id = issuerAndSerialNumber;
    }

    public OriginatorIdentifierOrKey(OriginatorPublicKey originatorPublicKey) {
        this.id = new DERTaggedObject(false, 1, (ASN1Encodable) originatorPublicKey);
    }

    public OriginatorIdentifierOrKey(SubjectKeyIdentifier subjectKeyIdentifier) {
        this.id = new DERTaggedObject(false, 0, (ASN1Encodable) subjectKeyIdentifier);
    }

    public static OriginatorIdentifierOrKey getInstance(Object obj) {
        if (obj == null || (obj instanceof OriginatorIdentifierOrKey)) {
            return (OriginatorIdentifierOrKey) obj;
        }
        if (obj instanceof IssuerAndSerialNumber) {
            return new OriginatorIdentifierOrKey((IssuerAndSerialNumber) obj);
        }
        if (obj instanceof ASN1Sequence) {
            return new OriginatorIdentifierOrKey(IssuerAndSerialNumber.getInstance(obj));
        }
        if (obj instanceof ASN1TaggedObject) {
            ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject) obj;
            if (aSN1TaggedObject.hasContextTag(0)) {
                return new OriginatorIdentifierOrKey(SubjectKeyIdentifier.getInstance(aSN1TaggedObject, false));
            }
            if (aSN1TaggedObject.hasContextTag(1)) {
                return new OriginatorIdentifierOrKey(OriginatorPublicKey.getInstance(aSN1TaggedObject, false));
            }
        }
        throw new IllegalArgumentException("Invalid OriginatorIdentifierOrKey: " + obj.getClass().getName());
    }

    public static OriginatorIdentifierOrKey getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        if (z) {
            return getInstance(aSN1TaggedObject.getExplicitBaseObject());
        }
        throw new IllegalArgumentException("Can't implicitly tag OriginatorIdentifierOrKey");
    }

    public ASN1Encodable getId() {
        return this.id;
    }

    public IssuerAndSerialNumber getIssuerAndSerialNumber() {
        ASN1Encodable aSN1Encodable = this.id;
        if (aSN1Encodable instanceof IssuerAndSerialNumber) {
            return (IssuerAndSerialNumber) aSN1Encodable;
        }
        return null;
    }

    public OriginatorPublicKey getOriginatorKey() {
        ASN1Encodable aSN1Encodable = this.id;
        if (!(aSN1Encodable instanceof ASN1TaggedObject)) {
            return null;
        }
        ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject) aSN1Encodable;
        if (aSN1TaggedObject.hasContextTag(1)) {
            return OriginatorPublicKey.getInstance(aSN1TaggedObject, false);
        }
        return null;
    }

    public SubjectKeyIdentifier getSubjectKeyIdentifier() {
        ASN1Encodable aSN1Encodable = this.id;
        if (!(aSN1Encodable instanceof ASN1TaggedObject)) {
            return null;
        }
        ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject) aSN1Encodable;
        if (aSN1TaggedObject.hasContextTag(0)) {
            return SubjectKeyIdentifier.getInstance(aSN1TaggedObject, false);
        }
        return null;
    }

    public ASN1Primitive toASN1Primitive() {
        return this.id.toASN1Primitive();
    }
}