package org.bouncycastle.asn1;

public class DERUTF8String extends ASN1UTF8String {
    public DERUTF8String(String str) {
        super(str);
    }

    DERUTF8String(byte[] bArr, boolean z) {
        super(bArr, z);
    }
}
