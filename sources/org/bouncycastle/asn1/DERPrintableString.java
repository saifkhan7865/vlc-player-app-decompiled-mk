package org.bouncycastle.asn1;

public class DERPrintableString extends ASN1PrintableString {
    public DERPrintableString(String str) {
        this(str, false);
    }

    public DERPrintableString(String str, boolean z) {
        super(str, z);
    }

    DERPrintableString(byte[] bArr, boolean z) {
        super(bArr, z);
    }
}
