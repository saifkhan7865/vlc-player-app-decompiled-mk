package org.bouncycastle.asn1;

import java.io.IOException;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Strings;

public abstract class ASN1VisibleString extends ASN1Primitive implements ASN1String {
    static final ASN1UniversalType TYPE = new ASN1UniversalType(ASN1VisibleString.class, 26) {
        /* access modifiers changed from: package-private */
        public ASN1Primitive fromImplicitPrimitive(DEROctetString dEROctetString) {
            return ASN1VisibleString.createPrimitive(dEROctetString.getOctets());
        }
    };
    final byte[] contents;

    ASN1VisibleString(String str) {
        this.contents = Strings.toByteArray(str);
    }

    ASN1VisibleString(byte[] bArr, boolean z) {
        this.contents = z ? Arrays.clone(bArr) : bArr;
    }

    static ASN1VisibleString createPrimitive(byte[] bArr) {
        return new DERVisibleString(bArr, false);
    }

    public static ASN1VisibleString getInstance(Object obj) {
        if (obj == null || (obj instanceof ASN1VisibleString)) {
            return (ASN1VisibleString) obj;
        }
        if (obj instanceof ASN1Encodable) {
            ASN1Primitive aSN1Primitive = ((ASN1Encodable) obj).toASN1Primitive();
            if (aSN1Primitive instanceof ASN1VisibleString) {
                return (ASN1VisibleString) aSN1Primitive;
            }
        }
        if (obj instanceof byte[]) {
            try {
                return (ASN1VisibleString) TYPE.fromByteArray((byte[]) obj);
            } catch (Exception e) {
                throw new IllegalArgumentException("encoding error in getInstance: " + e.toString());
            }
        } else {
            throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
        }
    }

    public static ASN1VisibleString getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return (ASN1VisibleString) TYPE.getContextInstance(aSN1TaggedObject, z);
    }

    /* access modifiers changed from: package-private */
    public final boolean asn1Equals(ASN1Primitive aSN1Primitive) {
        if (!(aSN1Primitive instanceof ASN1VisibleString)) {
            return false;
        }
        return Arrays.areEqual(this.contents, ((ASN1VisibleString) aSN1Primitive).contents);
    }

    /* access modifiers changed from: package-private */
    public final void encode(ASN1OutputStream aSN1OutputStream, boolean z) throws IOException {
        aSN1OutputStream.writeEncodingDL(z, 26, this.contents);
    }

    /* access modifiers changed from: package-private */
    public final boolean encodeConstructed() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public final int encodedLength(boolean z) {
        return ASN1OutputStream.getLengthOfEncodingDL(z, this.contents.length);
    }

    public final byte[] getOctets() {
        return Arrays.clone(this.contents);
    }

    public final String getString() {
        return Strings.fromByteArray(this.contents);
    }

    public final int hashCode() {
        return Arrays.hashCode(this.contents);
    }

    public String toString() {
        return getString();
    }
}
