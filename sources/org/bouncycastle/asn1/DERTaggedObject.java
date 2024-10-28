package org.bouncycastle.asn1;

import java.io.IOException;

public class DERTaggedObject extends ASN1TaggedObject {
    DERTaggedObject(int i, int i2, int i3, ASN1Encodable aSN1Encodable) {
        super(i, i2, i3, aSN1Encodable);
    }

    public DERTaggedObject(int i, int i2, ASN1Encodable aSN1Encodable) {
        super(true, i, i2, aSN1Encodable);
    }

    public DERTaggedObject(int i, ASN1Encodable aSN1Encodable) {
        super(true, i, aSN1Encodable);
    }

    public DERTaggedObject(boolean z, int i, int i2, ASN1Encodable aSN1Encodable) {
        super(z, i, i2, aSN1Encodable);
    }

    public DERTaggedObject(boolean z, int i, ASN1Encodable aSN1Encodable) {
        super(z, i, aSN1Encodable);
    }

    /* access modifiers changed from: package-private */
    public void encode(ASN1OutputStream aSN1OutputStream, boolean z) throws IOException {
        ASN1Primitive dERObject = this.obj.toASN1Primitive().toDERObject();
        boolean isExplicit = isExplicit();
        if (z) {
            int i = this.tagClass;
            if (isExplicit || dERObject.encodeConstructed()) {
                i |= 32;
            }
            aSN1OutputStream.writeIdentifier(true, i, this.tagNo);
        }
        if (isExplicit) {
            aSN1OutputStream.writeDL(dERObject.encodedLength(true));
        }
        dERObject.encode(aSN1OutputStream.getDERSubStream(), isExplicit);
    }

    /* access modifiers changed from: package-private */
    public boolean encodeConstructed() {
        return isExplicit() || this.obj.toASN1Primitive().toDERObject().encodeConstructed();
    }

    /* access modifiers changed from: package-private */
    public int encodedLength(boolean z) throws IOException {
        ASN1Primitive dERObject = this.obj.toASN1Primitive().toDERObject();
        boolean isExplicit = isExplicit();
        int encodedLength = dERObject.encodedLength(isExplicit);
        if (isExplicit) {
            encodedLength += ASN1OutputStream.getLengthOfDL(encodedLength);
        }
        return encodedLength + (z ? ASN1OutputStream.getLengthOfIdentifier(this.tagNo) : 0);
    }

    /* access modifiers changed from: package-private */
    public ASN1Sequence rebuildConstructed(ASN1Primitive aSN1Primitive) {
        return new DERSequence((ASN1Encodable) aSN1Primitive);
    }

    /* access modifiers changed from: package-private */
    public ASN1TaggedObject replaceTag(int i, int i2) {
        return new DERTaggedObject(this.explicitness, i, i2, this.obj);
    }

    /* access modifiers changed from: package-private */
    public ASN1Primitive toDERObject() {
        return this;
    }

    /* access modifiers changed from: package-private */
    public ASN1Primitive toDLObject() {
        return this;
    }
}
