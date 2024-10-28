package org.bouncycastle.asn1;

import com.google.common.base.Ascii;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.bouncycastle.util.Arrays;

public abstract class ASN1BitString extends ASN1Primitive implements ASN1String, ASN1BitStringParser {
    static final ASN1UniversalType TYPE = new ASN1UniversalType(ASN1BitString.class, 3) {
        /* access modifiers changed from: package-private */
        public ASN1Primitive fromImplicitConstructed(ASN1Sequence aSN1Sequence) {
            return aSN1Sequence.toASN1BitString();
        }

        /* access modifiers changed from: package-private */
        public ASN1Primitive fromImplicitPrimitive(DEROctetString dEROctetString) {
            return ASN1BitString.createPrimitive(dEROctetString.getOctets());
        }
    };
    private static final char[] table = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    final byte[] contents;

    ASN1BitString(byte b, int i) {
        if (i > 7 || i < 0) {
            throw new IllegalArgumentException("pad bits cannot be greater than 7 or less than 0");
        }
        this.contents = new byte[]{(byte) i, b};
    }

    ASN1BitString(byte[] bArr, int i) {
        if (bArr == null) {
            throw new NullPointerException("'data' cannot be null");
        } else if (bArr.length == 0 && i != 0) {
            throw new IllegalArgumentException("zero length data with non-zero pad bits");
        } else if (i > 7 || i < 0) {
            throw new IllegalArgumentException("pad bits cannot be greater than 7 or less than 0");
        } else {
            this.contents = Arrays.prepend(bArr, (byte) i);
        }
    }

    ASN1BitString(byte[] bArr, boolean z) {
        if (z) {
            if (bArr == null) {
                throw new NullPointerException("'contents' cannot be null");
            } else if (bArr.length >= 1) {
                byte b = bArr[0] & 255;
                if (b > 0) {
                    if (bArr.length < 2) {
                        throw new IllegalArgumentException("zero length data with non-zero pad bits");
                    } else if (b > 7) {
                        throw new IllegalArgumentException("pad bits cannot be greater than 7 or less than 0");
                    }
                }
            } else {
                throw new IllegalArgumentException("'contents' cannot be empty");
            }
        }
        this.contents = bArr;
    }

    static ASN1BitString createPrimitive(byte[] bArr) {
        int length = bArr.length;
        if (length >= 1) {
            byte b = bArr[0] & 255;
            if (b > 0) {
                if (b > 7 || length < 2) {
                    throw new IllegalArgumentException("invalid pad bits detected");
                }
                byte b2 = bArr[length - 1];
                if (b2 != ((byte) ((255 << b) & b2))) {
                    return new DLBitString(bArr, false);
                }
            }
            return new DERBitString(bArr, false);
        }
        throw new IllegalArgumentException("truncated BIT STRING detected");
    }

    protected static byte[] getBytes(int i) {
        if (i == 0) {
            return new byte[0];
        }
        int i2 = 4;
        int i3 = 3;
        while (i3 >= 1 && ((255 << (i3 * 8)) & i) == 0) {
            i2--;
            i3--;
        }
        byte[] bArr = new byte[i2];
        for (int i4 = 0; i4 < i2; i4++) {
            bArr[i4] = (byte) ((i >> (i4 * 8)) & 255);
        }
        return bArr;
    }

    public static ASN1BitString getInstance(Object obj) {
        if (obj == null || (obj instanceof ASN1BitString)) {
            return (ASN1BitString) obj;
        }
        if (obj instanceof ASN1Encodable) {
            ASN1Primitive aSN1Primitive = ((ASN1Encodable) obj).toASN1Primitive();
            if (aSN1Primitive instanceof ASN1BitString) {
                return (ASN1BitString) aSN1Primitive;
            }
        } else if (obj instanceof byte[]) {
            try {
                return (ASN1BitString) TYPE.fromByteArray((byte[]) obj);
            } catch (IOException e) {
                throw new IllegalArgumentException("failed to construct BIT STRING from byte[]: " + e.getMessage());
            }
        }
        throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
    }

    public static ASN1BitString getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return (ASN1BitString) TYPE.getContextInstance(aSN1TaggedObject, z);
    }

    protected static int getPadBits(int i) {
        int i2;
        int i3 = 3;
        while (true) {
            if (i3 < 0) {
                i2 = 0;
                break;
            }
            if (i3 != 0) {
                int i4 = i >> (i3 * 8);
                if (i4 != 0) {
                    i2 = i4 & 255;
                    break;
                }
            } else if (i != 0) {
                i2 = i & 255;
                break;
            }
            i3--;
        }
        if (i2 == 0) {
            return 0;
        }
        int i5 = 1;
        while (true) {
            i2 <<= 1;
            if ((i2 & 255) == 0) {
                return 8 - i5;
            }
            i5++;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean asn1Equals(ASN1Primitive aSN1Primitive) {
        if (!(aSN1Primitive instanceof ASN1BitString)) {
            return false;
        }
        byte[] bArr = this.contents;
        byte[] bArr2 = ((ASN1BitString) aSN1Primitive).contents;
        int length = bArr.length;
        if (bArr2.length != length) {
            return false;
        }
        if (length == 1) {
            return true;
        }
        int i = length - 1;
        for (int i2 = 0; i2 < i; i2++) {
            if (bArr[i2] != bArr2[i2]) {
                return false;
            }
        }
        int i3 = 255 << (bArr[0] & 255);
        return ((byte) (bArr[i] & i3)) == ((byte) (bArr2[i] & i3));
    }

    public InputStream getBitStream() throws IOException {
        byte[] bArr = this.contents;
        return new ByteArrayInputStream(bArr, 1, bArr.length - 1);
    }

    public byte[] getBytes() {
        byte[] bArr = this.contents;
        if (bArr.length == 1) {
            return ASN1OctetString.EMPTY_OCTETS;
        }
        byte[] copyOfRange = Arrays.copyOfRange(bArr, 1, bArr.length);
        int length = copyOfRange.length - 1;
        copyOfRange[length] = (byte) (((byte) (255 << (bArr[0] & 255))) & copyOfRange[length]);
        return copyOfRange;
    }

    public ASN1Primitive getLoadedObject() {
        return toASN1Primitive();
    }

    public InputStream getOctetStream() throws IOException {
        byte b = this.contents[0] & 255;
        if (b == 0) {
            return getBitStream();
        }
        throw new IOException("expected octet-aligned bitstring, but found padBits: " + b);
    }

    public byte[] getOctets() {
        byte[] bArr = this.contents;
        if (bArr[0] == 0) {
            return Arrays.copyOfRange(bArr, 1, bArr.length);
        }
        throw new IllegalStateException("attempt to get non-octet aligned data from BIT STRING");
    }

    public int getPadBits() {
        return this.contents[0] & 255;
    }

    public String getString() {
        try {
            byte[] encoded = getEncoded();
            StringBuffer stringBuffer = new StringBuffer((encoded.length * 2) + 1);
            stringBuffer.append('#');
            for (int i = 0; i != encoded.length; i++) {
                byte b = encoded[i];
                char[] cArr = table;
                stringBuffer.append(cArr[(b >>> 4) & 15]);
                stringBuffer.append(cArr[b & Ascii.SI]);
            }
            return stringBuffer.toString();
        } catch (IOException e) {
            throw new ASN1ParsingException("Internal error encoding BitString: " + e.getMessage(), e);
        }
    }

    public int hashCode() {
        byte[] bArr = this.contents;
        if (bArr.length < 2) {
            return 1;
        }
        int length = bArr.length - 1;
        int i = 255 << (bArr[0] & 255);
        return (Arrays.hashCode(bArr, 0, length) * 257) ^ ((byte) (i & bArr[length]));
    }

    public int intValue() {
        int min = Math.min(5, this.contents.length - 1);
        int i = 0;
        for (int i2 = 1; i2 < min; i2++) {
            i |= (255 & this.contents[i2]) << ((i2 - 1) * 8);
        }
        if (1 > min || min >= 5) {
            return i;
        }
        byte[] bArr = this.contents;
        return i | ((((byte) (bArr[min] & (255 << (bArr[0] & 255)))) & 255) << ((min - 1) * 8));
    }

    public ASN1BitStringParser parser() {
        return this;
    }

    /* access modifiers changed from: package-private */
    public ASN1Primitive toDERObject() {
        return new DERBitString(this.contents, false);
    }

    /* access modifiers changed from: package-private */
    public ASN1Primitive toDLObject() {
        return new DLBitString(this.contents, false);
    }

    public String toString() {
        return getString();
    }
}
