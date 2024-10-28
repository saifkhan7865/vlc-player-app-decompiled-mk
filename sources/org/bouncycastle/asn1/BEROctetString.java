package org.bouncycastle.asn1;

import java.io.IOException;

public class BEROctetString extends ASN1OctetString {
    private static final int DEFAULT_SEGMENT_LIMIT = 1000;
    private final ASN1OctetString[] elements;
    private final int segmentLimit;

    public BEROctetString(byte[] bArr) {
        this(bArr, 1000);
    }

    public BEROctetString(byte[] bArr, int i) {
        this(bArr, (ASN1OctetString[]) null, i);
    }

    private BEROctetString(byte[] bArr, ASN1OctetString[] aSN1OctetStringArr, int i) {
        super(bArr);
        this.elements = aSN1OctetStringArr;
        this.segmentLimit = i;
    }

    public BEROctetString(ASN1OctetString[] aSN1OctetStringArr) {
        this(aSN1OctetStringArr, 1000);
    }

    public BEROctetString(ASN1OctetString[] aSN1OctetStringArr, int i) {
        this(flattenOctetStrings(aSN1OctetStringArr), aSN1OctetStringArr, i);
    }

    static byte[] flattenOctetStrings(ASN1OctetString[] aSN1OctetStringArr) {
        if (r0 == 0) {
            return EMPTY_OCTETS;
        }
        if (r0 == 1) {
            return aSN1OctetStringArr[0].string;
        }
        int i = 0;
        for (ASN1OctetString aSN1OctetString : aSN1OctetStringArr) {
            i += aSN1OctetString.string.length;
        }
        byte[] bArr = new byte[i];
        int i2 = 0;
        for (ASN1OctetString aSN1OctetString2 : aSN1OctetStringArr) {
            byte[] bArr2 = aSN1OctetString2.string;
            System.arraycopy(bArr2, 0, bArr, i2, bArr2.length);
            i2 += bArr2.length;
        }
        return bArr;
    }

    /* access modifiers changed from: package-private */
    public void encode(ASN1OutputStream aSN1OutputStream, boolean z) throws IOException {
        aSN1OutputStream.writeIdentifier(z, 36);
        aSN1OutputStream.write(128);
        ASN1OctetString[] aSN1OctetStringArr = this.elements;
        if (aSN1OctetStringArr != null) {
            aSN1OutputStream.writePrimitives(aSN1OctetStringArr);
        } else {
            int i = 0;
            while (i < this.string.length) {
                int min = Math.min(this.string.length - i, this.segmentLimit);
                DEROctetString.encode(aSN1OutputStream, true, this.string, i, min);
                i += min;
            }
        }
        aSN1OutputStream.write(0);
        aSN1OutputStream.write(0);
    }

    /* access modifiers changed from: package-private */
    public boolean encodeConstructed() {
        return true;
    }

    /* access modifiers changed from: package-private */
    public int encodedLength(boolean z) throws IOException {
        int i = z ? 4 : 3;
        if (this.elements != null) {
            int i2 = 0;
            while (true) {
                ASN1OctetString[] aSN1OctetStringArr = this.elements;
                if (i2 >= aSN1OctetStringArr.length) {
                    return i;
                }
                i += aSN1OctetStringArr[i2].encodedLength(true);
                i2++;
            }
        } else {
            int length = this.string.length;
            int i3 = this.segmentLimit;
            int i4 = length / i3;
            int encodedLength = i + (DEROctetString.encodedLength(true, i3) * i4);
            int length2 = this.string.length - (i4 * this.segmentLimit);
            return length2 > 0 ? encodedLength + DEROctetString.encodedLength(true, length2) : encodedLength;
        }
    }
}
