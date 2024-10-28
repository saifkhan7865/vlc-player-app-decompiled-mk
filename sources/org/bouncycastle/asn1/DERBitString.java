package org.bouncycastle.asn1;

import java.io.IOException;

public class DERBitString extends ASN1BitString {
    public DERBitString(byte b, int i) {
        super(b, i);
    }

    public DERBitString(int i) {
        super(getBytes(i), getPadBits(i));
    }

    public DERBitString(ASN1Encodable aSN1Encodable) throws IOException {
        super(aSN1Encodable.toASN1Primitive().getEncoded(ASN1Encoding.DER), 0);
    }

    public DERBitString(byte[] bArr) {
        this(bArr, 0);
    }

    public DERBitString(byte[] bArr, int i) {
        super(bArr, i);
    }

    DERBitString(byte[] bArr, boolean z) {
        super(bArr, z);
    }

    public static DERBitString convert(ASN1BitString aSN1BitString) {
        return (DERBitString) aSN1BitString.toDERObject();
    }

    static DERBitString fromOctetString(ASN1OctetString aSN1OctetString) {
        return new DERBitString(aSN1OctetString.getOctets(), true);
    }

    /* access modifiers changed from: package-private */
    public void encode(ASN1OutputStream aSN1OutputStream, boolean z) throws IOException {
        int length = this.contents.length - 1;
        byte b = this.contents[length];
        byte b2 = (byte) ((255 << (this.contents[0] & 255)) & this.contents[length]);
        if (b == b2) {
            aSN1OutputStream.writeEncodingDL(z, 3, this.contents);
            return;
        }
        aSN1OutputStream.writeEncodingDL(z, 3, this.contents, 0, length, b2);
    }

    /* access modifiers changed from: package-private */
    public boolean encodeConstructed() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public int encodedLength(boolean z) {
        return ASN1OutputStream.getLengthOfEncodingDL(z, this.contents.length);
    }

    /* access modifiers changed from: package-private */
    public ASN1Primitive toDERObject() {
        return this;
    }

    /* access modifiers changed from: package-private */
    public ASN1Primitive toDLObject() {
        return this;
    }
}
