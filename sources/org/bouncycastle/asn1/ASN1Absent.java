package org.bouncycastle.asn1;

import java.io.IOException;

public class ASN1Absent extends ASN1Primitive {
    public static final ASN1Absent INSTANCE = new ASN1Absent();

    private ASN1Absent() {
    }

    /* access modifiers changed from: package-private */
    public boolean asn1Equals(ASN1Primitive aSN1Primitive) {
        return aSN1Primitive == this;
    }

    /* access modifiers changed from: package-private */
    public void encode(ASN1OutputStream aSN1OutputStream, boolean z) throws IOException {
    }

    /* access modifiers changed from: package-private */
    public boolean encodeConstructed() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public int encodedLength(boolean z) throws IOException {
        return 0;
    }

    public int hashCode() {
        return 0;
    }
}
