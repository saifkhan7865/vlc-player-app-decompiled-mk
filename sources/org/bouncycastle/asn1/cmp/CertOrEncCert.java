package org.bouncycastle.asn1.cmp;

import org.bouncycastle.asn1.ASN1Choice;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.ASN1Util;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.crmf.EncryptedKey;
import org.bouncycastle.asn1.crmf.EncryptedValue;

public class CertOrEncCert extends ASN1Object implements ASN1Choice {
    private CMPCertificate certificate;
    private EncryptedKey encryptedCert;

    private CertOrEncCert(ASN1TaggedObject aSN1TaggedObject) {
        if (aSN1TaggedObject.hasContextTag(0)) {
            this.certificate = CMPCertificate.getInstance(aSN1TaggedObject.getExplicitBaseObject());
        } else if (aSN1TaggedObject.hasContextTag(1)) {
            this.encryptedCert = EncryptedKey.getInstance(aSN1TaggedObject.getExplicitBaseObject());
        } else {
            throw new IllegalArgumentException("unknown tag: " + ASN1Util.getTagText(aSN1TaggedObject));
        }
    }

    public CertOrEncCert(CMPCertificate cMPCertificate) {
        if (cMPCertificate != null) {
            this.certificate = cMPCertificate;
            return;
        }
        throw new IllegalArgumentException("'certificate' cannot be null");
    }

    public CertOrEncCert(EncryptedKey encryptedKey) {
        if (encryptedKey != null) {
            this.encryptedCert = encryptedKey;
            return;
        }
        throw new IllegalArgumentException("'encryptedCert' cannot be null");
    }

    public CertOrEncCert(EncryptedValue encryptedValue) {
        if (encryptedValue != null) {
            this.encryptedCert = new EncryptedKey(encryptedValue);
            return;
        }
        throw new IllegalArgumentException("'encryptedCert' cannot be null");
    }

    public static CertOrEncCert getInstance(Object obj) {
        if (obj instanceof CertOrEncCert) {
            return (CertOrEncCert) obj;
        }
        if (obj instanceof ASN1TaggedObject) {
            return new CertOrEncCert(ASN1TaggedObject.getInstance(obj, 128));
        }
        return null;
    }

    public CMPCertificate getCertificate() {
        return this.certificate;
    }

    public EncryptedKey getEncryptedCert() {
        return this.encryptedCert;
    }

    public boolean hasEncryptedCertificate() {
        return this.encryptedCert != null;
    }

    public ASN1Primitive toASN1Primitive() {
        return this.certificate != null ? new DERTaggedObject(true, 0, (ASN1Encodable) this.certificate) : new DERTaggedObject(true, 1, (ASN1Encodable) this.encryptedCert);
    }
}
