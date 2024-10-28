package org.bouncycastle.cert;

import java.io.IOException;
import org.bouncycastle.asn1.ASN1BitString;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Encoding;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.x509.Certificate;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.ExtensionsGenerator;

public class DeltaCertificateTool {
    public static X509CertificateHolder extractDeltaCertificate(X509CertificateHolder x509CertificateHolder) {
        ASN1Sequence instance = ASN1Sequence.getInstance(x509CertificateHolder.getExtension(Extension.deltaCertificateDescriptor).getParsedValue());
        ASN1Sequence instance2 = ASN1Sequence.getInstance(x509CertificateHolder.toASN1Structure().getTBSCertificate().toASN1Primitive());
        ASN1Encodable[] array = instance2.toArray();
        array[0] = instance2.getObjectAt(0);
        array[1] = ASN1Integer.getInstance(instance.getObjectAt(0));
        ASN1Encodable objectAt = instance.getObjectAt(1);
        int i = 2;
        while (objectAt instanceof ASN1TaggedObject) {
            ASN1TaggedObject instance3 = ASN1TaggedObject.getInstance(objectAt);
            int tagNo = instance3.getTagNo();
            if (tagNo == 0) {
                array[2] = ASN1Sequence.getInstance(instance3, false);
            } else if (tagNo == 1) {
                array[3] = ASN1Sequence.getInstance(instance3, true);
            } else if (tagNo == 2) {
                array[4] = ASN1Sequence.getInstance(instance3, false);
            } else if (tagNo == 3) {
                array[5] = ASN1Sequence.getInstance((ASN1TaggedObject) objectAt, true);
            }
            ASN1Encodable objectAt2 = instance.getObjectAt(i);
            i++;
            objectAt = objectAt2;
        }
        array[6] = objectAt;
        if (array[2] == null) {
            array[2] = instance2.getObjectAt(2);
        }
        if (array[3] == null) {
            array[3] = instance2.getObjectAt(3);
        }
        if (array[4] == null) {
            array[4] = instance2.getObjectAt(4);
        }
        if (array[5] == null) {
            array[5] = instance2.getObjectAt(5);
        }
        ExtensionsGenerator extractExtensions = extractExtensions(instance2);
        if (i < instance.size() - 1) {
            ASN1TaggedObject instance4 = ASN1TaggedObject.getInstance(instance.getObjectAt(i));
            if (instance4.getTagNo() == 4) {
                ASN1Sequence instance5 = ASN1Sequence.getInstance(instance4, false);
                for (int i2 = 0; i2 != instance5.size(); i2++) {
                    extractExtensions.replaceExtension(Extension.getInstance(instance5.getObjectAt(i2)));
                }
                array[7] = new DERTaggedObject(3, extractExtensions.generate());
            } else {
                throw new IllegalArgumentException("malformed delta extension");
            }
        } else if (!extractExtensions.isEmpty()) {
            array[7] = new DERTaggedObject(3, extractExtensions.generate());
        } else {
            array[7] = null;
        }
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(7);
        for (int i3 = 0; i3 != array.length; i3++) {
            ASN1Encodable aSN1Encodable = array[i3];
            if (aSN1Encodable != null) {
                aSN1EncodableVector.add(aSN1Encodable);
            }
        }
        ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector();
        aSN1EncodableVector2.add(new DERSequence(aSN1EncodableVector));
        aSN1EncodableVector2.add(ASN1Sequence.getInstance(array[2]));
        aSN1EncodableVector2.add(ASN1BitString.getInstance(instance.getObjectAt(instance.size() - 1)));
        return new X509CertificateHolder(Certificate.getInstance(new DERSequence(aSN1EncodableVector2)));
    }

    private static ExtensionsGenerator extractExtensions(ASN1Sequence aSN1Sequence) {
        ASN1ObjectIdentifier aSN1ObjectIdentifier = Extension.deltaCertificateDescriptor;
        ASN1Sequence instance = ASN1Sequence.getInstance(ASN1TaggedObject.getInstance(aSN1Sequence.getObjectAt(aSN1Sequence.size() - 1)), true);
        ExtensionsGenerator extensionsGenerator = new ExtensionsGenerator();
        for (int i = 0; i != instance.size(); i++) {
            Extension instance2 = Extension.getInstance(instance.getObjectAt(i));
            if (!aSN1ObjectIdentifier.equals((ASN1Primitive) instance2.getExtnId())) {
                extensionsGenerator.addExtension(instance2);
            }
        }
        return extensionsGenerator;
    }

    public static Extension makeDeltaCertificateExtension(boolean z, X509CertificateHolder x509CertificateHolder) throws IOException {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(new ASN1Integer(x509CertificateHolder.getSerialNumber()));
        aSN1EncodableVector.add(new DERTaggedObject(false, 0, (ASN1Encodable) x509CertificateHolder.getSignatureAlgorithm()));
        aSN1EncodableVector.add(new DERTaggedObject(false, 1, (ASN1Encodable) x509CertificateHolder.getIssuer()));
        ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector(2);
        aSN1EncodableVector2.add(x509CertificateHolder.toASN1Structure().getStartDate());
        aSN1EncodableVector2.add(x509CertificateHolder.toASN1Structure().getEndDate());
        aSN1EncodableVector.add(new DERTaggedObject(false, 2, (ASN1Encodable) new DERSequence(aSN1EncodableVector2)));
        aSN1EncodableVector.add(new DERTaggedObject(false, 3, (ASN1Encodable) x509CertificateHolder.getSubject()));
        aSN1EncodableVector.add(x509CertificateHolder.getSubjectPublicKeyInfo());
        if (x509CertificateHolder.getExtensions() != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 4, (ASN1Encodable) x509CertificateHolder.getExtensions()));
        }
        aSN1EncodableVector.add(new DERBitString(x509CertificateHolder.getSignature()));
        return new Extension(Extension.deltaCertificateDescriptor, z, new DERSequence(aSN1EncodableVector).getEncoded(ASN1Encoding.DER));
    }
}
