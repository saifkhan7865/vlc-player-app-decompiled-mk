package org.bouncycastle.asn1;

import java.io.IOException;
import org.bouncycastle.util.Arrays;

public abstract class ASN1BMPString extends ASN1Primitive implements ASN1String {
    static final ASN1UniversalType TYPE = new ASN1UniversalType(ASN1BMPString.class, 30) {
        /* access modifiers changed from: package-private */
        public ASN1Primitive fromImplicitPrimitive(DEROctetString dEROctetString) {
            return ASN1BMPString.createPrimitive(dEROctetString.getOctets());
        }
    };
    final char[] string;

    ASN1BMPString(String str) {
        if (str != null) {
            this.string = str.toCharArray();
            return;
        }
        throw new NullPointerException("'string' cannot be null");
    }

    ASN1BMPString(byte[] bArr) {
        if (bArr != null) {
            int length = bArr.length;
            if ((length & 1) == 0) {
                int i = length / 2;
                char[] cArr = new char[i];
                for (int i2 = 0; i2 != i; i2++) {
                    int i3 = i2 * 2;
                    cArr[i2] = (char) ((bArr[i3 + 1] & 255) | (bArr[i3] << 8));
                }
                this.string = cArr;
                return;
            }
            throw new IllegalArgumentException("malformed BMPString encoding encountered");
        }
        throw new NullPointerException("'string' cannot be null");
    }

    ASN1BMPString(char[] cArr) {
        if (cArr != null) {
            this.string = cArr;
            return;
        }
        throw new NullPointerException("'string' cannot be null");
    }

    static ASN1BMPString createPrimitive(byte[] bArr) {
        return new DERBMPString(bArr);
    }

    static ASN1BMPString createPrimitive(char[] cArr) {
        return new DERBMPString(cArr);
    }

    public static ASN1BMPString getInstance(Object obj) {
        if (obj == null || (obj instanceof ASN1BMPString)) {
            return (ASN1BMPString) obj;
        }
        if (obj instanceof ASN1Encodable) {
            ASN1Primitive aSN1Primitive = ((ASN1Encodable) obj).toASN1Primitive();
            if (aSN1Primitive instanceof ASN1BMPString) {
                return (ASN1BMPString) aSN1Primitive;
            }
        }
        if (obj instanceof byte[]) {
            try {
                return (ASN1BMPString) TYPE.fromByteArray((byte[]) obj);
            } catch (Exception e) {
                throw new IllegalArgumentException("encoding error in getInstance: " + e.toString());
            }
        } else {
            throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
        }
    }

    public static ASN1BMPString getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return (ASN1BMPString) TYPE.getContextInstance(aSN1TaggedObject, z);
    }

    /* access modifiers changed from: package-private */
    public final boolean asn1Equals(ASN1Primitive aSN1Primitive) {
        if (!(aSN1Primitive instanceof ASN1BMPString)) {
            return false;
        }
        return Arrays.areEqual(this.string, ((ASN1BMPString) aSN1Primitive).string);
    }

    /* access modifiers changed from: package-private */
    public final void encode(ASN1OutputStream aSN1OutputStream, boolean z) throws IOException {
        int length = this.string.length;
        aSN1OutputStream.writeIdentifier(z, 30);
        aSN1OutputStream.writeDL(length * 2);
        byte[] bArr = new byte[8];
        int i = length & -4;
        int i2 = 0;
        while (i2 < i) {
            char[] cArr = this.string;
            char c = cArr[i2];
            char c2 = cArr[i2 + 1];
            char c3 = cArr[i2 + 2];
            char c4 = cArr[i2 + 3];
            i2 += 4;
            bArr[0] = (byte) (c >> 8);
            bArr[1] = (byte) c;
            bArr[2] = (byte) (c2 >> 8);
            bArr[3] = (byte) c2;
            bArr[4] = (byte) (c3 >> 8);
            bArr[5] = (byte) c3;
            bArr[6] = (byte) (c4 >> 8);
            bArr[7] = (byte) c4;
            aSN1OutputStream.write(bArr, 0, 8);
        }
        if (i2 < length) {
            int i3 = 0;
            do {
                char c5 = this.string[i2];
                i2++;
                int i4 = i3 + 1;
                bArr[i3] = (byte) (c5 >> 8);
                i3 += 2;
                bArr[i4] = (byte) c5;
            } while (i2 < length);
            aSN1OutputStream.write(bArr, 0, i3);
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean encodeConstructed() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public final int encodedLength(boolean z) {
        return ASN1OutputStream.getLengthOfEncodingDL(z, this.string.length * 2);
    }

    public final String getString() {
        return new String(this.string);
    }

    public final int hashCode() {
        return Arrays.hashCode(this.string);
    }

    public String toString() {
        return getString();
    }
}
