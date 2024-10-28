package org.bouncycastle.asn1;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import okhttp3.HttpUrl;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Iterable;

public abstract class ASN1Set extends ASN1Primitive implements Iterable<ASN1Encodable> {
    static final ASN1UniversalType TYPE = new ASN1UniversalType(ASN1Set.class, 17) {
        /* access modifiers changed from: package-private */
        public ASN1Primitive fromImplicitConstructed(ASN1Sequence aSN1Sequence) {
            return aSN1Sequence.toASN1Set();
        }
    };
    protected final ASN1Encodable[] elements;
    protected ASN1Encodable[] sortedElements;

    protected ASN1Set() {
        ASN1Encodable[] aSN1EncodableArr = ASN1EncodableVector.EMPTY_ELEMENTS;
        this.elements = aSN1EncodableArr;
        this.sortedElements = aSN1EncodableArr;
    }

    protected ASN1Set(ASN1Encodable aSN1Encodable) {
        if (aSN1Encodable != null) {
            ASN1Encodable[] aSN1EncodableArr = {aSN1Encodable};
            this.elements = aSN1EncodableArr;
            this.sortedElements = aSN1EncodableArr;
            return;
        }
        throw new NullPointerException("'element' cannot be null");
    }

    protected ASN1Set(ASN1EncodableVector aSN1EncodableVector, boolean z) {
        ASN1Encodable[] aSN1EncodableArr;
        if (aSN1EncodableVector != null) {
            if (!z || aSN1EncodableVector.size() < 2) {
                aSN1EncodableArr = aSN1EncodableVector.takeElements();
            } else {
                aSN1EncodableArr = aSN1EncodableVector.copyElements();
                sort(aSN1EncodableArr);
            }
            this.elements = aSN1EncodableArr;
            if (!z && aSN1EncodableArr.length >= 2) {
                aSN1EncodableArr = null;
            }
            this.sortedElements = aSN1EncodableArr;
            return;
        }
        throw new NullPointerException("'elementVector' cannot be null");
    }

    ASN1Set(boolean z, ASN1Encodable[] aSN1EncodableArr) {
        this.elements = aSN1EncodableArr;
        if (!z && aSN1EncodableArr.length >= 2) {
            aSN1EncodableArr = null;
        }
        this.sortedElements = aSN1EncodableArr;
    }

    protected ASN1Set(ASN1Encodable[] aSN1EncodableArr, boolean z) {
        if (!Arrays.isNullOrContainsNull(aSN1EncodableArr)) {
            ASN1Encodable[] cloneElements = ASN1EncodableVector.cloneElements(aSN1EncodableArr);
            if (z && cloneElements.length >= 2) {
                sort(cloneElements);
            }
            this.elements = cloneElements;
            if (!z && cloneElements.length >= 2) {
                aSN1EncodableArr = null;
            }
            this.sortedElements = aSN1EncodableArr;
            return;
        }
        throw new NullPointerException("'elements' cannot be null, or contain null");
    }

    ASN1Set(ASN1Encodable[] aSN1EncodableArr, ASN1Encodable[] aSN1EncodableArr2) {
        this.elements = aSN1EncodableArr;
        this.sortedElements = aSN1EncodableArr2;
    }

    private static byte[] getDEREncoded(ASN1Encodable aSN1Encodable) {
        try {
            return aSN1Encodable.toASN1Primitive().getEncoded(ASN1Encoding.DER);
        } catch (IOException unused) {
            throw new IllegalArgumentException("cannot encode object added to SET");
        }
    }

    public static ASN1Set getInstance(Object obj) {
        if (obj == null || (obj instanceof ASN1Set)) {
            return (ASN1Set) obj;
        }
        if (obj instanceof ASN1Encodable) {
            ASN1Primitive aSN1Primitive = ((ASN1Encodable) obj).toASN1Primitive();
            if (aSN1Primitive instanceof ASN1Set) {
                return (ASN1Set) aSN1Primitive;
            }
        } else if (obj instanceof byte[]) {
            try {
                return (ASN1Set) TYPE.fromByteArray((byte[]) obj);
            } catch (IOException e) {
                throw new IllegalArgumentException("failed to construct set from byte[]: " + e.getMessage());
            }
        }
        throw new IllegalArgumentException("unknown object in getInstance: " + obj.getClass().getName());
    }

    public static ASN1Set getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return (ASN1Set) TYPE.getContextInstance(aSN1TaggedObject, z);
    }

    private static boolean lessThanOrEqual(byte[] bArr, byte[] bArr2) {
        byte b = bArr[0] & 223;
        byte b2 = bArr2[0] & 223;
        if (b != b2) {
            return b < b2;
        }
        int min = Math.min(bArr.length, bArr2.length) - 1;
        for (int i = 1; i < min; i++) {
            byte b3 = bArr[i];
            byte b4 = bArr2[i];
            if (b3 != b4) {
                return (b3 & 255) < (b4 & 255);
            }
        }
        return (bArr[min] & 255) <= (bArr2[min] & 255);
    }

    private static void sort(ASN1Encodable[] aSN1EncodableArr) {
        int i;
        int length = aSN1EncodableArr.length;
        if (length >= 2) {
            ASN1Encodable aSN1Encodable = aSN1EncodableArr[0];
            ASN1Encodable aSN1Encodable2 = aSN1EncodableArr[1];
            byte[] dEREncoded = getDEREncoded(aSN1Encodable);
            byte[] dEREncoded2 = getDEREncoded(aSN1Encodable2);
            if (lessThanOrEqual(dEREncoded2, dEREncoded)) {
                ASN1Encodable aSN1Encodable3 = aSN1Encodable2;
                aSN1Encodable2 = aSN1Encodable;
                aSN1Encodable = aSN1Encodable3;
                byte[] bArr = dEREncoded2;
                dEREncoded2 = dEREncoded;
                dEREncoded = bArr;
            }
            for (int i2 = 2; i2 < length; i2++) {
                ASN1Encodable aSN1Encodable4 = aSN1EncodableArr[i2];
                byte[] dEREncoded3 = getDEREncoded(aSN1Encodable4);
                if (lessThanOrEqual(dEREncoded2, dEREncoded3)) {
                    aSN1EncodableArr[i2 - 2] = aSN1Encodable;
                    aSN1Encodable = aSN1Encodable2;
                    dEREncoded = dEREncoded2;
                    aSN1Encodable2 = aSN1Encodable4;
                    dEREncoded2 = dEREncoded3;
                } else if (lessThanOrEqual(dEREncoded, dEREncoded3)) {
                    aSN1EncodableArr[i2 - 2] = aSN1Encodable;
                    aSN1Encodable = aSN1Encodable4;
                    dEREncoded = dEREncoded3;
                } else {
                    int i3 = i2 - 1;
                    while (true) {
                        i = i3 - 1;
                        if (i <= 0) {
                            break;
                        }
                        ASN1Encodable aSN1Encodable5 = aSN1EncodableArr[i3 - 2];
                        if (lessThanOrEqual(getDEREncoded(aSN1Encodable5), dEREncoded3)) {
                            break;
                        }
                        aSN1EncodableArr[i] = aSN1Encodable5;
                        i3 = i;
                    }
                    aSN1EncodableArr[i] = aSN1Encodable4;
                }
            }
            aSN1EncodableArr[length - 2] = aSN1Encodable;
            aSN1EncodableArr[length - 1] = aSN1Encodable2;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean asn1Equals(ASN1Primitive aSN1Primitive) {
        if (!(aSN1Primitive instanceof ASN1Set)) {
            return false;
        }
        ASN1Set aSN1Set = (ASN1Set) aSN1Primitive;
        int size = size();
        if (aSN1Set.size() != size) {
            return false;
        }
        DERSet dERSet = (DERSet) toDERObject();
        DERSet dERSet2 = (DERSet) aSN1Set.toDERObject();
        for (int i = 0; i < size; i++) {
            ASN1Primitive aSN1Primitive2 = dERSet.elements[i].toASN1Primitive();
            ASN1Primitive aSN1Primitive3 = dERSet2.elements[i].toASN1Primitive();
            if (aSN1Primitive2 != aSN1Primitive3 && !aSN1Primitive2.asn1Equals(aSN1Primitive3)) {
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean encodeConstructed() {
        return true;
    }

    public ASN1Encodable getObjectAt(int i) {
        return this.elements[i];
    }

    public Enumeration getObjects() {
        return new Enumeration() {
            private int pos = 0;

            public boolean hasMoreElements() {
                return this.pos < ASN1Set.this.elements.length;
            }

            public Object nextElement() {
                if (this.pos < ASN1Set.this.elements.length) {
                    ASN1Encodable[] aSN1EncodableArr = ASN1Set.this.elements;
                    int i = this.pos;
                    this.pos = i + 1;
                    return aSN1EncodableArr[i];
                }
                throw new NoSuchElementException();
            }
        };
    }

    public int hashCode() {
        int length = this.elements.length;
        int i = length + 1;
        while (true) {
            length--;
            if (length < 0) {
                return i;
            }
            i += this.elements[length].toASN1Primitive().hashCode();
        }
    }

    public Iterator<ASN1Encodable> iterator() {
        return new Arrays.Iterator(toArray());
    }

    public ASN1SetParser parser() {
        final int size = size();
        return new ASN1SetParser() {
            private int pos = 0;

            public ASN1Primitive getLoadedObject() {
                return ASN1Set.this;
            }

            public ASN1Encodable readObject() throws IOException {
                if (size == this.pos) {
                    return null;
                }
                ASN1Encodable[] aSN1EncodableArr = ASN1Set.this.elements;
                int i = this.pos;
                this.pos = i + 1;
                ASN1Encodable aSN1Encodable = aSN1EncodableArr[i];
                return aSN1Encodable instanceof ASN1Sequence ? ((ASN1Sequence) aSN1Encodable).parser() : aSN1Encodable instanceof ASN1Set ? ((ASN1Set) aSN1Encodable).parser() : aSN1Encodable;
            }

            public ASN1Primitive toASN1Primitive() {
                return ASN1Set.this;
            }
        };
    }

    public int size() {
        return this.elements.length;
    }

    public ASN1Encodable[] toArray() {
        return ASN1EncodableVector.cloneElements(this.elements);
    }

    /* access modifiers changed from: package-private */
    public ASN1Primitive toDERObject() {
        if (this.sortedElements == null) {
            ASN1Encodable[] aSN1EncodableArr = (ASN1Encodable[]) this.elements.clone();
            this.sortedElements = aSN1EncodableArr;
            sort(aSN1EncodableArr);
        }
        return new DERSet(true, this.sortedElements);
    }

    /* access modifiers changed from: package-private */
    public ASN1Primitive toDLObject() {
        return new DLSet(this.elements, this.sortedElements);
    }

    public String toString() {
        int size = size();
        if (size == 0) {
            return HttpUrl.PATH_SEGMENT_ENCODE_SET_URI;
        }
        StringBuffer stringBuffer = new StringBuffer("[");
        int i = 0;
        while (true) {
            stringBuffer.append(this.elements[i]);
            i++;
            if (i >= size) {
                stringBuffer.append(AbstractJsonLexerKt.END_LIST);
                return stringBuffer.toString();
            }
            stringBuffer.append(", ");
        }
    }
}
