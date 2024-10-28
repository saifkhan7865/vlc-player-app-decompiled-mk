package org.bouncycastle.oer.its.etsi102941;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.oer.its.etsi102941.basetypes.CertificateFormat;
import org.bouncycastle.oer.its.etsi102941.basetypes.CertificateSubjectAttributes;
import org.bouncycastle.oer.its.etsi102941.basetypes.PublicKeys;
import org.bouncycastle.util.Arrays;

public class InnerEcRequest extends ASN1Object {
    private final CertificateFormat certificateFormat;
    private final ASN1OctetString itsId;
    private final PublicKeys publicKeys;
    private final CertificateSubjectAttributes requestedSubjectAttributes;

    public static class Builder {
        private CertificateFormat certificateFormat;
        private ASN1OctetString itsId;
        private PublicKeys publicKeys;
        private CertificateSubjectAttributes requestedSubjectAttributes;

        public InnerEcRequest createInnerEcRequest() {
            return new InnerEcRequest(this.itsId, this.certificateFormat, this.publicKeys, this.requestedSubjectAttributes);
        }

        public Builder setCertificateFormat(CertificateFormat certificateFormat2) {
            this.certificateFormat = certificateFormat2;
            return this;
        }

        public Builder setItsId(ASN1OctetString aSN1OctetString) {
            this.itsId = aSN1OctetString;
            return this;
        }

        public Builder setItsId(byte[] bArr) {
            this.itsId = new DEROctetString(Arrays.clone(bArr));
            return this;
        }

        public Builder setPublicKeys(PublicKeys publicKeys2) {
            this.publicKeys = publicKeys2;
            return this;
        }

        public Builder setRequestedSubjectAttributes(CertificateSubjectAttributes certificateSubjectAttributes) {
            this.requestedSubjectAttributes = certificateSubjectAttributes;
            return this;
        }
    }

    public InnerEcRequest(ASN1OctetString aSN1OctetString, CertificateFormat certificateFormat2, PublicKeys publicKeys2, CertificateSubjectAttributes certificateSubjectAttributes) {
        this.itsId = aSN1OctetString;
        this.certificateFormat = certificateFormat2;
        this.publicKeys = publicKeys2;
        this.requestedSubjectAttributes = certificateSubjectAttributes;
    }

    private InnerEcRequest(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() == 4) {
            this.itsId = ASN1OctetString.getInstance(aSN1Sequence.getObjectAt(0));
            this.certificateFormat = CertificateFormat.getInstance(aSN1Sequence.getObjectAt(1));
            this.publicKeys = PublicKeys.getInstance(aSN1Sequence.getObjectAt(2));
            this.requestedSubjectAttributes = CertificateSubjectAttributes.getInstance(aSN1Sequence.getObjectAt(3));
            return;
        }
        throw new IllegalArgumentException("expected sequence size of 4");
    }

    public static Builder builder() {
        return new Builder();
    }

    public static InnerEcRequest getInstance(Object obj) {
        if (obj instanceof InnerEcRequest) {
            return (InnerEcRequest) obj;
        }
        if (obj != null) {
            return new InnerEcRequest(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public CertificateFormat getCertificateFormat() {
        return this.certificateFormat;
    }

    public ASN1OctetString getItsId() {
        return this.itsId;
    }

    public PublicKeys getPublicKeys() {
        return this.publicKeys;
    }

    public CertificateSubjectAttributes getRequestedSubjectAttributes() {
        return this.requestedSubjectAttributes;
    }

    public ASN1Primitive toASN1Primitive() {
        return new DERSequence(new ASN1Encodable[]{this.itsId, this.certificateFormat, this.publicKeys, this.requestedSubjectAttributes});
    }
}
