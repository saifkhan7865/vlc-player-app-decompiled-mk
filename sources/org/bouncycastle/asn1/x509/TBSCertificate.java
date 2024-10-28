package org.bouncycastle.asn1.x509;

import org.bouncycastle.asn1.ASN1BitString;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.util.Properties;

public class TBSCertificate extends ASN1Object {
    Time endDate;
    Extensions extensions;
    X500Name issuer;
    ASN1BitString issuerUniqueId;
    ASN1Sequence seq;
    ASN1Integer serialNumber;
    AlgorithmIdentifier signature;
    Time startDate;
    X500Name subject;
    SubjectPublicKeyInfo subjectPublicKeyInfo;
    ASN1BitString subjectUniqueId;
    ASN1Integer version;

    /* JADX WARNING: Removed duplicated region for block: B:20:0x00b5  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private TBSCertificate(org.bouncycastle.asn1.ASN1Sequence r10) {
        /*
            r9 = this;
            r9.<init>()
            r9.seq = r10
            r0 = 0
            org.bouncycastle.asn1.ASN1Encodable r1 = r10.getObjectAt(r0)
            boolean r1 = r1 instanceof org.bouncycastle.asn1.ASN1TaggedObject
            r2 = 1
            if (r1 == 0) goto L_0x001d
            org.bouncycastle.asn1.ASN1Encodable r1 = r10.getObjectAt(r0)
            org.bouncycastle.asn1.ASN1TaggedObject r1 = (org.bouncycastle.asn1.ASN1TaggedObject) r1
            org.bouncycastle.asn1.ASN1Integer r1 = org.bouncycastle.asn1.ASN1Integer.getInstance(r1, r2)
            r9.version = r1
            r1 = 0
            goto L_0x0027
        L_0x001d:
            org.bouncycastle.asn1.ASN1Integer r1 = new org.bouncycastle.asn1.ASN1Integer
            r3 = 0
            r1.<init>((long) r3)
            r9.version = r1
            r1 = -1
        L_0x0027:
            org.bouncycastle.asn1.ASN1Integer r3 = r9.version
            boolean r3 = r3.hasValue((int) r0)
            r4 = 2
            if (r3 == 0) goto L_0x0033
            r3 = 1
        L_0x0031:
            r5 = 0
            goto L_0x0048
        L_0x0033:
            org.bouncycastle.asn1.ASN1Integer r3 = r9.version
            boolean r3 = r3.hasValue((int) r2)
            if (r3 == 0) goto L_0x003e
            r3 = 0
            r5 = 1
            goto L_0x0048
        L_0x003e:
            org.bouncycastle.asn1.ASN1Integer r3 = r9.version
            boolean r3 = r3.hasValue((int) r4)
            if (r3 == 0) goto L_0x0106
            r3 = 0
            goto L_0x0031
        L_0x0048:
            int r6 = r1 + 1
            org.bouncycastle.asn1.ASN1Encodable r6 = r10.getObjectAt(r6)
            org.bouncycastle.asn1.ASN1Integer r6 = org.bouncycastle.asn1.ASN1Integer.getInstance(r6)
            r9.serialNumber = r6
            int r6 = r1 + 2
            org.bouncycastle.asn1.ASN1Encodable r6 = r10.getObjectAt(r6)
            org.bouncycastle.asn1.x509.AlgorithmIdentifier r6 = org.bouncycastle.asn1.x509.AlgorithmIdentifier.getInstance(r6)
            r9.signature = r6
            int r6 = r1 + 3
            org.bouncycastle.asn1.ASN1Encodable r6 = r10.getObjectAt(r6)
            org.bouncycastle.asn1.x500.X500Name r6 = org.bouncycastle.asn1.x500.X500Name.getInstance(r6)
            r9.issuer = r6
            int r6 = r1 + 4
            org.bouncycastle.asn1.ASN1Encodable r6 = r10.getObjectAt(r6)
            org.bouncycastle.asn1.ASN1Sequence r6 = (org.bouncycastle.asn1.ASN1Sequence) r6
            org.bouncycastle.asn1.ASN1Encodable r7 = r6.getObjectAt(r0)
            org.bouncycastle.asn1.x509.Time r7 = org.bouncycastle.asn1.x509.Time.getInstance(r7)
            r9.startDate = r7
            org.bouncycastle.asn1.ASN1Encodable r6 = r6.getObjectAt(r2)
            org.bouncycastle.asn1.x509.Time r6 = org.bouncycastle.asn1.x509.Time.getInstance(r6)
            r9.endDate = r6
            int r6 = r1 + 5
            org.bouncycastle.asn1.ASN1Encodable r6 = r10.getObjectAt(r6)
            org.bouncycastle.asn1.x500.X500Name r6 = org.bouncycastle.asn1.x500.X500Name.getInstance(r6)
            r9.subject = r6
            int r1 = r1 + 6
            org.bouncycastle.asn1.ASN1Encodable r6 = r10.getObjectAt(r1)
            org.bouncycastle.asn1.x509.SubjectPublicKeyInfo r6 = org.bouncycastle.asn1.x509.SubjectPublicKeyInfo.getInstance(r6)
            r9.subjectPublicKeyInfo = r6
            int r6 = r10.size()
            int r6 = r6 - r1
            int r6 = r6 - r2
            if (r6 == 0) goto L_0x00b3
            if (r3 != 0) goto L_0x00ab
            goto L_0x00b3
        L_0x00ab:
            java.lang.IllegalArgumentException r10 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "version 1 certificate contains extra data"
            r10.<init>(r0)
            throw r10
        L_0x00b3:
            if (r6 <= 0) goto L_0x0105
            int r3 = r1 + r6
            org.bouncycastle.asn1.ASN1Encodable r3 = r10.getObjectAt(r3)
            org.bouncycastle.asn1.ASN1TaggedObject r3 = (org.bouncycastle.asn1.ASN1TaggedObject) r3
            int r7 = r3.getTagNo()
            if (r7 == r2) goto L_0x00fc
            if (r7 == r4) goto L_0x00f5
            r8 = 3
            if (r7 != r8) goto L_0x00dd
            if (r5 != 0) goto L_0x00d5
            org.bouncycastle.asn1.ASN1Sequence r3 = org.bouncycastle.asn1.ASN1Sequence.getInstance(r3, r2)
            org.bouncycastle.asn1.x509.Extensions r3 = org.bouncycastle.asn1.x509.Extensions.getInstance(r3)
            r9.extensions = r3
            goto L_0x0102
        L_0x00d5:
            java.lang.IllegalArgumentException r10 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "version 2 certificate cannot contain extensions"
            r10.<init>(r0)
            throw r10
        L_0x00dd:
            java.lang.IllegalArgumentException r10 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Unknown tag encountered in structure: "
            r0.<init>(r1)
            int r1 = r3.getTagNo()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r10.<init>(r0)
            throw r10
        L_0x00f5:
            org.bouncycastle.asn1.ASN1BitString r3 = org.bouncycastle.asn1.ASN1BitString.getInstance(r3, r0)
            r9.subjectUniqueId = r3
            goto L_0x0102
        L_0x00fc:
            org.bouncycastle.asn1.ASN1BitString r3 = org.bouncycastle.asn1.ASN1BitString.getInstance(r3, r0)
            r9.issuerUniqueId = r3
        L_0x0102:
            int r6 = r6 + -1
            goto L_0x00b3
        L_0x0105:
            return
        L_0x0106:
            java.lang.IllegalArgumentException r10 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "version number not recognised"
            r10.<init>(r0)
            goto L_0x010f
        L_0x010e:
            throw r10
        L_0x010f:
            goto L_0x010e
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.asn1.x509.TBSCertificate.<init>(org.bouncycastle.asn1.ASN1Sequence):void");
    }

    public static TBSCertificate getInstance(Object obj) {
        if (obj instanceof TBSCertificate) {
            return (TBSCertificate) obj;
        }
        if (obj != null) {
            return new TBSCertificate(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public static TBSCertificate getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public Time getEndDate() {
        return this.endDate;
    }

    public Extensions getExtensions() {
        return this.extensions;
    }

    public X500Name getIssuer() {
        return this.issuer;
    }

    public ASN1BitString getIssuerUniqueId() {
        return this.issuerUniqueId;
    }

    public ASN1Integer getSerialNumber() {
        return this.serialNumber;
    }

    public AlgorithmIdentifier getSignature() {
        return this.signature;
    }

    public Time getStartDate() {
        return this.startDate;
    }

    public X500Name getSubject() {
        return this.subject;
    }

    public SubjectPublicKeyInfo getSubjectPublicKeyInfo() {
        return this.subjectPublicKeyInfo;
    }

    public ASN1BitString getSubjectUniqueId() {
        return this.subjectUniqueId;
    }

    public ASN1Integer getVersion() {
        return this.version;
    }

    public int getVersionNumber() {
        return this.version.intValueExact() + 1;
    }

    public ASN1Primitive toASN1Primitive() {
        if (Properties.getPropertyValue("org.bouncycastle.x509.allow_non-der_tbscert") == null) {
            return this.seq;
        }
        if (Properties.isOverrideSet("org.bouncycastle.x509.allow_non-der_tbscert")) {
            return this.seq;
        }
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        if (!this.version.hasValue(0)) {
            aSN1EncodableVector.add(new DERTaggedObject(true, 0, (ASN1Encodable) this.version));
        }
        aSN1EncodableVector.add(this.serialNumber);
        aSN1EncodableVector.add(this.signature);
        aSN1EncodableVector.add(this.issuer);
        ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector(2);
        aSN1EncodableVector2.add(this.startDate);
        aSN1EncodableVector2.add(this.endDate);
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector2));
        ASN1Encodable aSN1Encodable = this.subject;
        if (aSN1Encodable == null) {
            aSN1Encodable = new DERSequence();
        }
        aSN1EncodableVector.add(aSN1Encodable);
        aSN1EncodableVector.add(this.subjectPublicKeyInfo);
        ASN1BitString aSN1BitString = this.issuerUniqueId;
        if (aSN1BitString != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 1, (ASN1Encodable) aSN1BitString));
        }
        ASN1BitString aSN1BitString2 = this.subjectUniqueId;
        if (aSN1BitString2 != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 2, (ASN1Encodable) aSN1BitString2));
        }
        Extensions extensions2 = this.extensions;
        if (extensions2 != null) {
            aSN1EncodableVector.add(new DERTaggedObject(true, 3, (ASN1Encodable) extensions2));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
