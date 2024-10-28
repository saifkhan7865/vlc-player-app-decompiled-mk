package org.bouncycastle.asn1.eac;

import java.io.IOException;
import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;

public class CertificateBody extends ASN1Object {
    private static final int CAR = 2;
    private static final int CEfD = 32;
    private static final int CExD = 64;
    private static final int CHA = 16;
    private static final int CHR = 8;
    private static final int CPI = 1;
    private static final int PK = 4;
    public static final int profileType = 127;
    public static final int requestType = 13;
    private ASN1TaggedObject certificateEffectiveDate;
    private ASN1TaggedObject certificateExpirationDate;
    private CertificateHolderAuthorization certificateHolderAuthorization;
    private ASN1TaggedObject certificateHolderReference;
    private ASN1TaggedObject certificateProfileIdentifier;
    private int certificateType = 0;
    private ASN1TaggedObject certificationAuthorityReference;
    private PublicKeyDataObject publicKey;
    ASN1InputStream seq;

    private CertificateBody(ASN1TaggedObject aSN1TaggedObject) throws IOException {
        setIso7816CertificateBody(aSN1TaggedObject);
    }

    public CertificateBody(ASN1TaggedObject aSN1TaggedObject, CertificationAuthorityReference certificationAuthorityReference2, PublicKeyDataObject publicKeyDataObject, CertificateHolderReference certificateHolderReference2, CertificateHolderAuthorization certificateHolderAuthorization2, PackedDate packedDate, PackedDate packedDate2) {
        setCertificateProfileIdentifier(aSN1TaggedObject);
        setCertificationAuthorityReference(EACTagged.create(2, certificationAuthorityReference2.getEncoded()));
        setPublicKey(publicKeyDataObject);
        setCertificateHolderReference(EACTagged.create(32, certificateHolderReference2.getEncoded()));
        setCertificateHolderAuthorization(certificateHolderAuthorization2);
        setCertificateEffectiveDate(EACTagged.create(37, packedDate.getEncoding()));
        setCertificateExpirationDate(EACTagged.create(36, packedDate2.getEncoding()));
    }

    public static CertificateBody getInstance(Object obj) throws IOException {
        if (obj instanceof CertificateBody) {
            return (CertificateBody) obj;
        }
        if (obj != null) {
            return new CertificateBody(ASN1TaggedObject.getInstance(obj, 64));
        }
        return null;
    }

    private ASN1Primitive profileToASN1Object() throws IOException {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(7);
        aSN1EncodableVector.add(this.certificateProfileIdentifier);
        aSN1EncodableVector.add(this.certificationAuthorityReference);
        aSN1EncodableVector.add(EACTagged.create(73, this.publicKey));
        aSN1EncodableVector.add(this.certificateHolderReference);
        aSN1EncodableVector.add(this.certificateHolderAuthorization);
        aSN1EncodableVector.add(this.certificateEffectiveDate);
        aSN1EncodableVector.add(this.certificateExpirationDate);
        return EACTagged.create(78, (ASN1Sequence) new DERSequence(aSN1EncodableVector));
    }

    private ASN1Primitive requestToASN1Object() throws IOException {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(3);
        aSN1EncodableVector.add(this.certificateProfileIdentifier);
        aSN1EncodableVector.add(EACTagged.create(73, this.publicKey));
        aSN1EncodableVector.add(this.certificateHolderReference);
        return EACTagged.create(78, (ASN1Sequence) new DERSequence(aSN1EncodableVector));
    }

    private void setCertificateEffectiveDate(ASN1TaggedObject aSN1TaggedObject) throws IllegalArgumentException {
        if (aSN1TaggedObject.hasTag(64, 37)) {
            this.certificateEffectiveDate = aSN1TaggedObject;
            this.certificateType |= 32;
            return;
        }
        throw new IllegalArgumentException("Not an Iso7816Tags.APPLICATION_EFFECTIVE_DATE tag :" + aSN1TaggedObject.getTagNo());
    }

    private void setCertificateExpirationDate(ASN1TaggedObject aSN1TaggedObject) throws IllegalArgumentException {
        if (aSN1TaggedObject.hasTag(64, 36)) {
            this.certificateExpirationDate = aSN1TaggedObject;
            this.certificateType |= 64;
            return;
        }
        throw new IllegalArgumentException("Not an Iso7816Tags.APPLICATION_EXPIRATION_DATE tag");
    }

    private void setCertificateHolderAuthorization(CertificateHolderAuthorization certificateHolderAuthorization2) {
        this.certificateHolderAuthorization = certificateHolderAuthorization2;
        this.certificateType |= 16;
    }

    private void setCertificateHolderReference(ASN1TaggedObject aSN1TaggedObject) throws IllegalArgumentException {
        if (aSN1TaggedObject.hasTag(64, 32)) {
            this.certificateHolderReference = aSN1TaggedObject;
            this.certificateType |= 8;
            return;
        }
        throw new IllegalArgumentException("Not an Iso7816Tags.CARDHOLDER_NAME tag");
    }

    private void setCertificateProfileIdentifier(ASN1TaggedObject aSN1TaggedObject) throws IllegalArgumentException {
        if (aSN1TaggedObject.hasTag(64, 41)) {
            this.certificateProfileIdentifier = aSN1TaggedObject;
            this.certificateType |= 1;
            return;
        }
        throw new IllegalArgumentException("Not an Iso7816Tags.INTERCHANGE_PROFILE tag :" + aSN1TaggedObject.getTagNo());
    }

    private void setCertificationAuthorityReference(ASN1TaggedObject aSN1TaggedObject) throws IllegalArgumentException {
        if (aSN1TaggedObject.hasTag(64, 2)) {
            this.certificationAuthorityReference = aSN1TaggedObject;
            this.certificateType |= 2;
            return;
        }
        throw new IllegalArgumentException("Not an Iso7816Tags.ISSUER_IDENTIFICATION_NUMBER tag");
    }

    private void setIso7816CertificateBody(ASN1TaggedObject aSN1TaggedObject) throws IOException {
        if (aSN1TaggedObject.hasTag(64, 78)) {
            Enumeration objects = ASN1Sequence.getInstance(aSN1TaggedObject.getBaseUniversal(false, 16)).getObjects();
            while (objects.hasMoreElements()) {
                ASN1TaggedObject instance = ASN1TaggedObject.getInstance(objects.nextElement(), 64);
                int tagNo = instance.getTagNo();
                if (tagNo == 2) {
                    setCertificationAuthorityReference(instance);
                } else if (tagNo == 32) {
                    setCertificateHolderReference(instance);
                } else if (tagNo == 41) {
                    setCertificateProfileIdentifier(instance);
                } else if (tagNo == 73) {
                    setPublicKey(PublicKeyDataObject.getInstance(instance.getBaseUniversal(false, 16)));
                } else if (tagNo == 76) {
                    setCertificateHolderAuthorization(new CertificateHolderAuthorization(instance));
                } else if (tagNo == 36) {
                    setCertificateExpirationDate(instance);
                } else if (tagNo == 37) {
                    setCertificateEffectiveDate(instance);
                } else {
                    this.certificateType = 0;
                    throw new IOException("Not a valid iso7816 ASN1TaggedObject tag " + instance.getTagNo());
                }
            }
            return;
        }
        throw new IOException("Bad tag : not an iso7816 CERTIFICATE_CONTENT_TEMPLATE");
    }

    private void setPublicKey(PublicKeyDataObject publicKeyDataObject) {
        this.publicKey = PublicKeyDataObject.getInstance(publicKeyDataObject);
        this.certificateType |= 4;
    }

    public PackedDate getCertificateEffectiveDate() {
        if ((this.certificateType & 32) == 32) {
            return new PackedDate(ASN1OctetString.getInstance(this.certificateEffectiveDate.getBaseUniversal(false, 4)).getOctets());
        }
        return null;
    }

    public PackedDate getCertificateExpirationDate() throws IOException {
        if ((this.certificateType & 64) == 64) {
            return new PackedDate(ASN1OctetString.getInstance(this.certificateExpirationDate.getBaseUniversal(false, 4)).getOctets());
        }
        throw new IOException("certificate Expiration Date not set");
    }

    public CertificateHolderAuthorization getCertificateHolderAuthorization() throws IOException {
        if ((this.certificateType & 16) == 16) {
            return this.certificateHolderAuthorization;
        }
        throw new IOException("Certificate Holder Authorisation not set");
    }

    public CertificateHolderReference getCertificateHolderReference() {
        return new CertificateHolderReference(ASN1OctetString.getInstance(this.certificateHolderReference.getBaseUniversal(false, 4)).getOctets());
    }

    public ASN1TaggedObject getCertificateProfileIdentifier() {
        return this.certificateProfileIdentifier;
    }

    public int getCertificateType() {
        return this.certificateType;
    }

    public CertificationAuthorityReference getCertificationAuthorityReference() throws IOException {
        if ((this.certificateType & 2) == 2) {
            return new CertificationAuthorityReference(ASN1OctetString.getInstance(this.certificationAuthorityReference.getBaseUniversal(false, 4)).getOctets());
        }
        throw new IOException("Certification authority reference not set");
    }

    public PublicKeyDataObject getPublicKey() {
        return this.publicKey;
    }

    public ASN1Primitive toASN1Primitive() {
        try {
            int i = this.certificateType;
            if (i == 127) {
                return profileToASN1Object();
            }
            if (i == 13) {
                return requestToASN1Object();
            }
            return null;
        } catch (IOException unused) {
            return null;
        }
    }
}
