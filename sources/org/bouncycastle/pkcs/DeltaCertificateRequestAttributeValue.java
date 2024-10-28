package org.bouncycastle.pkcs;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.pkcs.Attribute;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.Extensions;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;

public class DeltaCertificateRequestAttributeValue implements ASN1Encodable {
    private final ASN1Sequence attrSeq;
    private final Extensions extensions;
    private final AlgorithmIdentifier signatureAlgorithm;
    private final X500Name subject;
    private final SubjectPublicKeyInfo subjectPKInfo;

    DeltaCertificateRequestAttributeValue(ASN1Sequence aSN1Sequence) {
        int i;
        AlgorithmIdentifier algorithmIdentifier;
        this.attrSeq = aSN1Sequence;
        Extensions extensions2 = null;
        if (aSN1Sequence.getObjectAt(0) instanceof ASN1TaggedObject) {
            this.subject = X500Name.getInstance(ASN1TaggedObject.getInstance(aSN1Sequence.getObjectAt(0)), true);
            i = 1;
        } else {
            this.subject = null;
            i = 0;
        }
        this.subjectPKInfo = SubjectPublicKeyInfo.getInstance(aSN1Sequence.getObjectAt(i));
        int i2 = i + 1;
        if (i2 != aSN1Sequence.size()) {
            algorithmIdentifier = null;
            while (i2 < aSN1Sequence.size()) {
                ASN1TaggedObject instance = ASN1TaggedObject.getInstance(aSN1Sequence.getObjectAt(i2));
                if (instance.getTagNo() == 1) {
                    extensions2 = Extensions.getInstance(instance, false);
                } else if (instance.getTagNo() == 2) {
                    algorithmIdentifier = AlgorithmIdentifier.getInstance(instance, false);
                } else {
                    throw new IllegalArgumentException("unknown tag");
                }
                i2++;
            }
        } else {
            algorithmIdentifier = null;
        }
        this.extensions = extensions2;
        this.signatureAlgorithm = algorithmIdentifier;
    }

    public DeltaCertificateRequestAttributeValue(Attribute attribute) {
        this(ASN1Sequence.getInstance(attribute.getAttributeValues()[0]));
    }

    public Extensions getExtensions() {
        return this.extensions;
    }

    public AlgorithmIdentifier getSignatureAlgorithm() {
        return this.signatureAlgorithm;
    }

    public X500Name getSubject() {
        return this.subject;
    }

    public SubjectPublicKeyInfo getSubjectPKInfo() {
        return this.subjectPKInfo;
    }

    public ASN1Primitive toASN1Primitive() {
        return this.attrSeq;
    }
}
