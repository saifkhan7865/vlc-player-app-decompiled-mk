package org.bouncycastle.asn1;

public class DERIA5String extends ASN1IA5String {
    public DERIA5String(String str) {
        this(str, false);
    }

    public DERIA5String(String str, boolean z) {
        super(str, z);
    }

    DERIA5String(byte[] bArr, boolean z) {
        super(bArr, z);
    }
}
