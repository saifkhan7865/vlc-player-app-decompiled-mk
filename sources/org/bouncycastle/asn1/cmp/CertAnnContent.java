package org.bouncycastle.asn1.cmp;

import java.io.IOException;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.x509.AttributeCertificate;
import org.bouncycastle.asn1.x509.Certificate;

public class CertAnnContent extends CMPCertificate {
    public CertAnnContent(int i, ASN1Object aSN1Object) {
        super(i, aSN1Object);
    }

    public CertAnnContent(AttributeCertificate attributeCertificate) {
        super(attributeCertificate);
    }

    public CertAnnContent(Certificate certificate) {
        super(certificate);
    }

    public static CertAnnContent getInstance(Object obj) {
        if (obj == null || (obj instanceof CertAnnContent)) {
            return (CertAnnContent) obj;
        }
        if (obj instanceof CMPCertificate) {
            try {
                return getInstance(((CMPCertificate) obj).getEncoded());
            } catch (IOException e) {
                throw new IllegalArgumentException(e.getMessage(), e);
            }
        } else {
            if (obj instanceof byte[]) {
                try {
                    obj = ASN1Primitive.fromByteArray((byte[]) obj);
                } catch (IOException unused) {
                    throw new IllegalArgumentException("Invalid encoding in CertAnnContent");
                }
            }
            if (obj instanceof ASN1Sequence) {
                return new CertAnnContent(Certificate.getInstance(obj));
            }
            if (obj instanceof ASN1TaggedObject) {
                ASN1TaggedObject instance = ASN1TaggedObject.getInstance(obj, 128);
                return new CertAnnContent(instance.getTagNo(), instance.getExplicitBaseObject());
            }
            throw new IllegalArgumentException("Invalid object: " + obj.getClass().getName());
        }
    }

    public static CertAnnContent getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        if (aSN1TaggedObject == null) {
            return null;
        }
        if (z) {
            return getInstance(aSN1TaggedObject.getExplicitBaseObject());
        }
        throw new IllegalArgumentException("tag must be explicit");
    }
}
