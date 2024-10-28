package org.bouncycastle.asn1.x509;

import org.bouncycastle.asn1.ASN1BitString;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;

public class SubjectAltPublicKeyInfo extends ASN1Object {
    private AlgorithmIdentifier algorithm;
    private ASN1BitString subjectAltPublicKey;

    private SubjectAltPublicKeyInfo(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() == 2) {
            this.algorithm = AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(0));
            this.subjectAltPublicKey = ASN1BitString.getInstance(aSN1Sequence.getObjectAt(1));
            return;
        }
        throw new IllegalArgumentException("extension should contain only 2 elements");
    }

    public SubjectAltPublicKeyInfo(AlgorithmIdentifier algorithmIdentifier, ASN1BitString aSN1BitString) {
        this.algorithm = algorithmIdentifier;
        this.subjectAltPublicKey = aSN1BitString;
    }

    public SubjectAltPublicKeyInfo(SubjectPublicKeyInfo subjectPublicKeyInfo) {
        this.algorithm = subjectPublicKeyInfo.getAlgorithm();
        this.subjectAltPublicKey = subjectPublicKeyInfo.getPublicKeyData();
    }

    public static SubjectAltPublicKeyInfo fromExtensions(Extensions extensions) {
        return getInstance(Extensions.getExtensionParsedValue(extensions, Extension.subjectAltPublicKeyInfo));
    }

    public static SubjectAltPublicKeyInfo getInstance(Object obj) {
        if (obj instanceof SubjectAltPublicKeyInfo) {
            return (SubjectAltPublicKeyInfo) obj;
        }
        if (obj != null) {
            return new SubjectAltPublicKeyInfo(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public static SubjectAltPublicKeyInfo getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public AlgorithmIdentifier getAlgorithm() {
        return this.algorithm;
    }

    public ASN1BitString getSubjectAltPublicKey() {
        return this.subjectAltPublicKey;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.algorithm);
        aSN1EncodableVector.add(this.subjectAltPublicKey);
        return new DERSequence(aSN1EncodableVector);
    }
}
