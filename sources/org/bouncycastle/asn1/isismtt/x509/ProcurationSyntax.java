package org.bouncycastle.asn1.isismtt.x509;

import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1PrintableString;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERPrintableString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.x500.DirectoryString;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.IssuerSerial;

public class ProcurationSyntax extends ASN1Object {
    private IssuerSerial certRef;
    private String country;
    private GeneralName thirdPerson;
    private DirectoryString typeOfSubstitution;

    public ProcurationSyntax(String str, DirectoryString directoryString, GeneralName generalName) {
        this.country = str;
        this.typeOfSubstitution = directoryString;
        this.thirdPerson = generalName;
        this.certRef = null;
    }

    public ProcurationSyntax(String str, DirectoryString directoryString, IssuerSerial issuerSerial) {
        this.country = str;
        this.typeOfSubstitution = directoryString;
        this.thirdPerson = null;
        this.certRef = issuerSerial;
    }

    private ProcurationSyntax(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() < 1 || aSN1Sequence.size() > 3) {
            throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.size());
        }
        Enumeration objects = aSN1Sequence.getObjects();
        while (objects.hasMoreElements()) {
            ASN1TaggedObject instance = ASN1TaggedObject.getInstance(objects.nextElement(), 128);
            int tagNo = instance.getTagNo();
            if (tagNo == 1) {
                this.country = ASN1PrintableString.getInstance(instance, true).getString();
            } else if (tagNo == 2) {
                this.typeOfSubstitution = DirectoryString.getInstance(instance, true);
            } else if (tagNo == 3) {
                ASN1Object explicitBaseObject = instance.getExplicitBaseObject();
                if (explicitBaseObject instanceof ASN1TaggedObject) {
                    this.thirdPerson = GeneralName.getInstance(explicitBaseObject);
                } else {
                    this.certRef = IssuerSerial.getInstance(explicitBaseObject);
                }
            } else {
                throw new IllegalArgumentException("Bad tag number: " + instance.getTagNo());
            }
        }
    }

    public static ProcurationSyntax getInstance(Object obj) {
        if (obj == null || (obj instanceof ProcurationSyntax)) {
            return (ProcurationSyntax) obj;
        }
        if (obj instanceof ASN1Sequence) {
            return new ProcurationSyntax((ASN1Sequence) obj);
        }
        throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
    }

    public IssuerSerial getCertRef() {
        return this.certRef;
    }

    public String getCountry() {
        return this.country;
    }

    public GeneralName getThirdPerson() {
        return this.thirdPerson;
    }

    public DirectoryString getTypeOfSubstitution() {
        return this.typeOfSubstitution;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(3);
        if (this.country != null) {
            aSN1EncodableVector.add(new DERTaggedObject(true, 1, (ASN1Encodable) new DERPrintableString(this.country, true)));
        }
        if (this.typeOfSubstitution != null) {
            aSN1EncodableVector.add(new DERTaggedObject(true, 2, (ASN1Encodable) this.typeOfSubstitution));
        }
        aSN1EncodableVector.add(this.thirdPerson != null ? new DERTaggedObject(true, 3, (ASN1Encodable) this.thirdPerson) : new DERTaggedObject(true, 3, (ASN1Encodable) this.certRef));
        return new DERSequence(aSN1EncodableVector);
    }
}
