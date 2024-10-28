package org.bouncycastle.asn1;

public class DERGraphicString extends ASN1GraphicString {
    public DERGraphicString(byte[] bArr) {
        this(bArr, true);
    }

    DERGraphicString(byte[] bArr, boolean z) {
        super(bArr, z);
    }
}
