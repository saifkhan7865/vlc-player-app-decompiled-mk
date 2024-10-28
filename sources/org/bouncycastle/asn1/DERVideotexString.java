package org.bouncycastle.asn1;

public class DERVideotexString extends ASN1VideotexString {
    public DERVideotexString(byte[] bArr) {
        this(bArr, true);
    }

    DERVideotexString(byte[] bArr, boolean z) {
        super(bArr, z);
    }
}
