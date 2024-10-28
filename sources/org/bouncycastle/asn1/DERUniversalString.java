package org.bouncycastle.asn1;

public class DERUniversalString extends ASN1UniversalString {
    public DERUniversalString(byte[] bArr) {
        this(bArr, true);
    }

    DERUniversalString(byte[] bArr, boolean z) {
        super(bArr, z);
    }
}
