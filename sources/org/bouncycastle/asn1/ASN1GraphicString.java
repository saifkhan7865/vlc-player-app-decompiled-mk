package org.bouncycastle.asn1;

import java.io.IOException;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Strings;

public abstract class ASN1GraphicString extends ASN1Primitive implements ASN1String {
    static final ASN1UniversalType TYPE = new ASN1UniversalType(ASN1GraphicString.class, 25) {
        /* access modifiers changed from: package-private */
        public ASN1Primitive fromImplicitPrimitive(DEROctetString dEROctetString) {
            return ASN1GraphicString.createPrimitive(dEROctetString.getOctets());
        }
    };
    final byte[] contents;

    ASN1GraphicString(byte[] bArr, boolean z) {
        if (bArr != null) {
            this.contents = z ? Arrays.clone(bArr) : bArr;
            return;
        }
        throw new NullPointerException("'contents' cannot be null");
    }

    static ASN1GraphicString createPrimitive(byte[] bArr) {
        return new DERGraphicString(bArr, false);
    }

    public static ASN1GraphicString getInstance(Object obj) {
        if (obj == null || (obj instanceof ASN1GraphicString)) {
            return (ASN1GraphicString) obj;
        }
        if (obj instanceof ASN1Encodable) {
            ASN1Primitive aSN1Primitive = ((ASN1Encodable) obj).toASN1Primitive();
            if (aSN1Primitive instanceof ASN1GraphicString) {
                return (ASN1GraphicString) aSN1Primitive;
            }
        }
        if (obj instanceof byte[]) {
            try {
                return (ASN1GraphicString) TYPE.fromByteArray((byte[]) obj);
            } catch (Exception e) {
                throw new IllegalArgumentException("encoding error in getInstance: " + e.toString());
            }
        } else {
            throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
        }
    }

    public static ASN1GraphicString getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return (ASN1GraphicString) TYPE.getContextInstance(aSN1TaggedObject, z);
    }

    /* access modifiers changed from: package-private */
    public final boolean asn1Equals(ASN1Primitive aSN1Primitive) {
        if (!(aSN1Primitive instanceof ASN1GraphicString)) {
            return false;
        }
        return Arrays.areEqual(this.contents, ((ASN1GraphicString) aSN1Primitive).contents);
    }

    /* access modifiers changed from: package-private */
    public final void encode(ASN1OutputStream aSN1OutputStream, boolean z) throws IOException {
        aSN1OutputStream.writeEncodingDL(z, 25, this.contents);
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
}
