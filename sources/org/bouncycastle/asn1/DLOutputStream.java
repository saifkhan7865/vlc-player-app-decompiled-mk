package org.bouncycastle.asn1;

import java.io.IOException;
import java.io.OutputStream;

class DLOutputStream extends ASN1OutputStream {
    DLOutputStream(OutputStream outputStream) {
        super(outputStream);
    }

    /* access modifiers changed from: package-private */
    public DLOutputStream getDLSubStream() {
        return this;
    }

    /* access modifiers changed from: package-private */
    public void writeElements(ASN1Encodable[] aSN1EncodableArr) throws IOException {
        for (ASN1Encodable aSN1Primitive : aSN1EncodableArr) {
            aSN1Primitive.toASN1Primitive().toDLObject().encode(this, true);
        }
    }

    /* access modifiers changed from: package-private */
    public void writePrimitive(ASN1Primitive aSN1Primitive, boolean z) throws IOException {
        aSN1Primitive.toDLObject().encode(this, z);
    }

    /* access modifiers changed from: package-private */
    public void writePrimitives(ASN1Primitive[] aSN1PrimitiveArr) throws IOException {
        for (ASN1Primitive dLObject : aSN1PrimitiveArr) {
            dLObject.toDLObject().encode(this, true);
        }
    }
}
