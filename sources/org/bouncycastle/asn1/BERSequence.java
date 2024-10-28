package org.bouncycastle.asn1;

import java.io.IOException;

public class BERSequence extends ASN1Sequence {
    public BERSequence() {
    }

    public BERSequence(ASN1Encodable aSN1Encodable) {
        super(aSN1Encodable);
    }

    public BERSequence(ASN1EncodableVector aSN1EncodableVector) {
        super(aSN1EncodableVector);
    }

    public BERSequence(ASN1Encodable[] aSN1EncodableArr) {
        super(aSN1EncodableArr);
    }

    /* access modifiers changed from: package-private */
    public void encode(ASN1OutputStream aSN1OutputStream, boolean z) throws IOException {
        aSN1OutputStream.writeEncodingIL(z, 48, this.elements);
    }

    /* access modifiers changed from: package-private */
    public int encodedLength(boolean z) throws IOException {
        int i = z ? 4 : 3;
        for (ASN1Encodable aSN1Primitive : this.elements) {
            i += aSN1Primitive.toASN1Primitive().encodedLength(true);
        }
        return i;
    }

    /* access modifiers changed from: package-private */
    public ASN1BitString toASN1BitString() {
        return new BERBitString(getConstructedBitStrings());
    }

    /* access modifiers changed from: package-private */
    public ASN1External toASN1External() {
        return ((ASN1Sequence) toDLObject()).toASN1External();
    }

    /* access modifiers changed from: package-private */
    public ASN1OctetString toASN1OctetString() {
        return new BEROctetString(getConstructedOctetStrings());
    }

    /* access modifiers changed from: package-private */
    public ASN1Set toASN1Set() {
        return new BERSet(false, toArrayInternal());
    }
}
