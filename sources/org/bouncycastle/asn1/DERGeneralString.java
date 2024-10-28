package org.bouncycastle.asn1;

public class DERGeneralString extends ASN1GeneralString {
    public DERGeneralString(String str) {
        super(str);
    }

    DERGeneralString(byte[] bArr, boolean z) {
        super(bArr, z);
    }
}
