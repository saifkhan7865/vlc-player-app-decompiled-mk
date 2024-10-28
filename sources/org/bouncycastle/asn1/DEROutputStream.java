package org.bouncycastle.asn1;

import java.io.IOException;
import java.io.OutputStream;

class DEROutputStream extends DLOutputStream {
    DEROutputStream(OutputStream outputStream) {
        super(outputStream);
    }

    /* access modifiers changed from: package-private */
    public DEROutputStream getDERSubStream() {
        return this;
    }

    /* access modifiers changed from: package-private */
    public void writeElements(ASN1Encodable[] aSN1EncodableArr) throws IOException {
        for (ASN1Encodable aSN1Primitive : aSN1EncodableArr) {
            aSN1Primitive.toASN1Primitive().toDERObject().encode(this, true);
        }
    }

    /* access modifiers changed from: package-private */
    public void writePrimitive(ASN1Primitive aSN1Primitive, boolean z) throws IOException {
        aSN1Primitive.toDERObject().encode(this, z);
    }

    /* access modifiers changed from: package-private */
    public void writePrimitives(ASN1Primitive[] aSN1PrimitiveArr) throws IOException {
        for (ASN1Primitive dERObject : aSN1PrimitiveArr) {
            dERObject.toDERObject().encode(this, true);
        }
    }
}
