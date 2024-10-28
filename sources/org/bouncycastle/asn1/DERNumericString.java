package org.bouncycastle.asn1;

public class DERNumericString extends ASN1NumericString {
    public DERNumericString(String str) {
        this(str, false);
    }

    public DERNumericString(String str, boolean z) {
        super(str, z);
    }

    DERNumericString(byte[] bArr, boolean z) {
        super(bArr, z);
    }
}
