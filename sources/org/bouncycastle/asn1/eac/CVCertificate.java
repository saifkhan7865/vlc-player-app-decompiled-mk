package org.bouncycastle.asn1.eac;

import java.io.IOException;
import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1ParsingException;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.util.Arrays;

public class CVCertificate extends ASN1Object {
    private static int bodyValid = 1;
    private static int signValid = 2;
    private CertificateBody certificateBody;
    private byte[] signature;
    private int valid;

    public CVCertificate(ASN1InputStream aSN1InputStream) throws IOException {
        initFrom(aSN1InputStream);
    }

    private CVCertificate(ASN1TaggedObject aSN1TaggedObject) throws IOException {
        setPrivateData(aSN1TaggedObject);
    }

    public CVCertificate(CertificateBody certificateBody2, byte[] bArr) throws IOException {
        this.certificateBody = certificateBody2;
        this.signature = Arrays.clone(bArr);
        this.valid = this.valid | bodyValid | signValid;
    }

    public static CVCertificate getInstance(Object obj) {
        if (obj instanceof CVCertificate) {
            return (CVCertificate) obj;
        }
        if (obj == null) {
            return null;
        }
        try {
            return new CVCertificate(ASN1TaggedObject.getInstance(obj, 64));
        } catch (IOException e) {
            throw new ASN1ParsingException("unable to parse data: " + e.getMessage(), e);
        }
    }

    private void initFrom(ASN1InputStream aSN1InputStream) throws IOException {
        while (true) {
            ASN1Primitive readObject = aSN1InputStream.readObject();
            if (readObject == null) {
                return;
            }
            if (readObject instanceof ASN1TaggedObject) {
                setPrivateData((ASN1TaggedObject) readObject);
            } else {
                throw new IOException("Invalid Input Stream for creating an Iso7816CertificateStructure");
            }
        }
    }

    private void setPrivateData(ASN1TaggedObject aSN1TaggedObject) throws IOException {
        int i;
        int i2;
        this.valid = 0;
        if (aSN1TaggedObject.hasTag(64, 33)) {
            Enumeration objects = ASN1Sequence.getInstance(aSN1TaggedObject.getBaseUniversal(false, 16)).getObjects();
            while (objects.hasMoreElements()) {
                Object nextElement = objects.nextElement();
                if (nextElement instanceof ASN1TaggedObject) {
                    ASN1TaggedObject instance = ASN1TaggedObject.getInstance(nextElement, 64);
                    int tagNo = instance.getTagNo();
                    if (tagNo == 55) {
                        this.signature = ASN1OctetString.getInstance(instance.getBaseUniversal(false, 4)).getOctets();
                        i2 = this.valid;
                        i = signValid;
                    } else if (tagNo == 78) {
                        this.certificateBody = CertificateBody.getInstance(instance);
                        i2 = this.valid;
                        i = bodyValid;
                    } else {
                        throw new IOException("Invalid tag, not an Iso7816CertificateStructure :" + instance.getTagNo());
                    }
                    this.valid = i2 | i;
                } else {
                    throw new IOException("Invalid Object, not an Iso7816CertificateStructure");
                }
            }
            if (this.valid != (signValid | bodyValid)) {
                throw new IOException("invalid CARDHOLDER_CERTIFICATE :" + aSN1TaggedObject.getTagNo());
            }
            return;
        }
        throw new IOException("not a CARDHOLDER_CERTIFICATE :" + aSN1TaggedObject.getTagNo());
    }

    public CertificationAuthorityReference getAuthorityReference() throws IOException {
        return this.certificateBody.getCertificationAuthorityReference();
    }

    public CertificateBody getBody() {
        return this.certificateBody;
    }

    public int getCertificateType() {
        return this.certificateBody.getCertificateType();
    }

    public PackedDate getEffectiveDate() throws IOException {
        return this.certificateBody.getCertificateEffectiveDate();
    }

    public PackedDate getExpirationDate() throws IOException {
        return this.certificateBody.getCertificateExpirationDate();
    }

    public ASN1ObjectIdentifier getHolderAuthorization() throws IOException {
        return this.certificateBody.getCertificateHolderAuthorization().getOid();
    }

    public Flags getHolderAuthorizationRights() throws IOException {
        return new Flags(this.certificateBody.getCertificateHolderAuthorization().getAccessRights() & 31);
    }

    public int getHolderAuthorizationRole() throws IOException {
        return this.certificateBody.getCertificateHolderAuthorization().getAccessRights() & 192;
    }

    public CertificateHolderReference getHolderReference() throws IOException {
        return this.certificateBody.getCertificateHolderReference();
    }

    public int getRole() throws IOException {
        return this.certificateBody.getCertificateHolderAuthorization().getAccessRights();
    }

    public byte[] getSignature() {
        return Arrays.clone(this.signature);
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(2);
        aSN1EncodableVector.add(this.certificateBody);
        aSN1EncodableVector.add(EACTagged.create(55, this.signature));
        return EACTagged.create(33, (ASN1Sequence) new DERSequence(aSN1EncodableVector));
    }
}
