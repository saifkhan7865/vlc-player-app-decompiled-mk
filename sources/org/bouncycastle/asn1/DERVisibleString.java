package org.bouncycastle.asn1;

public class DERVisibleString extends ASN1VisibleString {
    public DERVisibleString(String str) {
        super(str);
    }

    DERVisibleString(byte[] bArr, boolean z) {
        super(bArr, z);
    }
}
