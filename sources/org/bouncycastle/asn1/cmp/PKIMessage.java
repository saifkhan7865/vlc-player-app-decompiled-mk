package org.bouncycastle.asn1.cmp;

import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1BitString;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;

public class PKIMessage extends ASN1Object {
    private final PKIBody body;
    private final ASN1Sequence extraCerts;
    private final PKIHeader header;
    private final ASN1BitString protection;

    private PKIMessage(ASN1Sequence aSN1Sequence) {
        Enumeration objects = aSN1Sequence.getObjects();
        this.header = PKIHeader.getInstance(objects.nextElement());
        this.body = PKIBody.getInstance(objects.nextElement());
        ASN1BitString aSN1BitString = null;
        ASN1Sequence aSN1Sequence2 = null;
        while (objects.hasMoreElements()) {
            ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject) objects.nextElement();
            if (aSN1TaggedObject.getTagNo() == 0) {
                aSN1BitString = ASN1BitString.getInstance(aSN1TaggedObject, true);
            } else {
                aSN1Sequence2 = ASN1Sequence.getInstance(aSN1TaggedObject, true);
            }
        }
        this.protection = aSN1BitString;
        this.extraCerts = aSN1Sequence2;
    }

    public PKIMessage(PKIHeader pKIHeader, PKIBody pKIBody) {
        this(pKIHeader, pKIBody, (ASN1BitString) null, (CMPCertificate[]) null);
    }

    public PKIMessage(PKIHeader pKIHeader, PKIBody pKIBody, ASN1BitString aSN1BitString) {
        this(pKIHeader, pKIBody, aSN1BitString, (CMPCertificate[]) null);
    }

    public PKIMessage(PKIHeader pKIHeader, PKIBody pKIBody, ASN1BitString aSN1BitString, CMPCertificate[] cMPCertificateArr) {
        this.header = pKIHeader;
        this.body = pKIBody;
        this.protection = aSN1BitString;
        this.extraCerts = cMPCertificateArr != null ? new DERSequence((ASN1Encodable[]) cMPCertificateArr) : null;
    }

    private void addOptional(ASN1EncodableVector aSN1EncodableVector, int i, ASN1Encodable aSN1Encodable) {
        if (aSN1Encodable != null) {
            aSN1EncodableVector.add(new DERTaggedObject(true, i, aSN1Encodable));
        }
    }

    public static PKIMessage getInstance(Object obj) {
        if (obj instanceof PKIMessage) {
            return (PKIMessage) obj;
        }
        if (obj != null) {
            return new PKIMessage(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public PKIBody getBody() {
        return this.body;
    }

    public CMPCertificate[] getExtraCerts() {
        ASN1Sequence aSN1Sequence = this.extraCerts;
        if (aSN1Sequence == null) {
            return null;
        }
        int size = aSN1Sequence.size();
        CMPCertificate[] cMPCertificateArr = new CMPCertificate[size];
        for (int i = 0; i < size; i++) {
            cMPCertificateArr[i] = CMPCertificate.getInstance(this.extraCerts.getObjectAt(i));
        }
        return cMPCertificateArr;
    }

    public PKIHeader getHeader() {
        return this.header;
    }

    public ASN1BitString getProtection() {
        return this.protection;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(4);
        aSN1EncodableVector.add(this.header);
        aSN1EncodableVector.add(this.body);
        addOptional(aSN1EncodableVector, 0, this.protection);
        addOptional(aSN1EncodableVector, 1, this.extraCerts);
        return new DERSequence(aSN1EncodableVector);
    }
}
