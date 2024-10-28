package org.bouncycastle.asn1;

public class DERT61String extends ASN1T61String {
    public DERT61String(String str) {
        super(str);
    }

    public DERT61String(byte[] bArr) {
        this(bArr, true);
    }

    DERT61String(byte[] bArr, boolean z) {
        super(bArr, z);
    }
}
