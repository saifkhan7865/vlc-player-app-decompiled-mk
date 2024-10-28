package org.bouncycastle.asn1;

public class DERBMPString extends ASN1BMPString {
    public DERBMPString(String str) {
        super(str);
    }

    DERBMPString(byte[] bArr) {
        super(bArr);
    }

    DERBMPString(char[] cArr) {
        super(cArr);
    }
}
