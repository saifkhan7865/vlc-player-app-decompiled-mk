package org.bouncycastle.asn1;

import java.io.IOException;
import org.bouncycastle.util.Arrays;

public abstract class ASN1TaggedObject extends ASN1Primitive implements ASN1TaggedObjectParser {
    private static final int DECLARED_EXPLICIT = 1;
    private static final int DECLARED_IMPLICIT = 2;
    private static final int PARSED_EXPLICIT = 3;
    private static final int PARSED_IMPLICIT = 4;
    final int explicitness;
    final ASN1Encodable obj;
    final int tagClass;
    final int tagNo;

    ASN1TaggedObject(int i, int i2, int i3, ASN1Encodable aSN1Encodable) {
        if (aSN1Encodable == null) {
            throw new NullPointerException("'obj' cannot be null");
        } else if (i2 == 0 || (i2 & 192) != i2) {
            throw new IllegalArgumentException("invalid tag class: " + i2);
        } else {
            this.explicitness = aSN1Encodable instanceof ASN1Choice ? 1 : i;
            this.tagClass = i2;
            this.tagNo = i3;
            this.obj = aSN1Encodable;
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    protected ASN1TaggedObject(boolean z, int i, int i2, ASN1Encodable aSN1Encodable) {
        this(z ? 1 : 2, i, i2, aSN1Encodable);
    }

    protected ASN1TaggedObject(boolean z, int i, ASN1Encodable aSN1Encodable) {
        this(z, 128, i, aSN1Encodable);
    }

    private static ASN1TaggedObject checkedCast(ASN1Primitive aSN1Primitive) {
        if (aSN1Primitive instanceof ASN1TaggedObject) {
            return (ASN1TaggedObject) aSN1Primitive;
        }
        throw new IllegalStateException("unexpected object: " + aSN1Primitive.getClass().getName());
    }

    static ASN1Primitive createConstructedDL(int i, int i2, ASN1EncodableVector aSN1EncodableVector) {
        return aSN1EncodableVector.size() == 1 ? new DLTaggedObject(3, i, i2, aSN1EncodableVector.get(0)) : new DLTaggedObject(4, i, i2, (ASN1Encodable) DLFactory.createSequence(aSN1EncodableVector));
    }

    static ASN1Primitive createConstructedIL(int i, int i2, ASN1EncodableVector aSN1EncodableVector) {
        return aSN1EncodableVector.size() == 1 ? new BERTaggedObject(3, i, i2, aSN1EncodableVector.get(0)) : new BERTaggedObject(4, i, i2, (ASN1Encodable) BERFactory.createSequence(aSN1EncodableVector));
    }

    static ASN1Primitive createPrimitive(int i, int i2, byte[] bArr) {
        return new DLTaggedObject(4, i, i2, (ASN1Encodable) new DEROctetString(bArr));
    }

    public static ASN1TaggedObject getInstance(Object obj2) {
        if (obj2 == null || (obj2 instanceof ASN1TaggedObject)) {
            return (ASN1TaggedObject) obj2;
        }
        if (obj2 instanceof ASN1Encodable) {
            ASN1Primitive aSN1Primitive = ((ASN1Encodable) obj2).toASN1Primitive();
            if (aSN1Primitive instanceof ASN1TaggedObject) {
                return (ASN1TaggedObject) aSN1Primitive;
            }
        } else if (obj2 instanceof byte[]) {
            try {
                return checkedCast(fromByteArray((byte[]) obj2));
            } catch (IOException e) {
                throw new IllegalArgumentException("failed to construct tagged object from byte[]: " + e.getMessage());
            }
        }
        throw new IllegalArgumentException("unknown object in getInstance: " + obj2.getClass().getName());
    }

    public static ASN1TaggedObject getInstance(Object obj2, int i) {
        if (obj2 != null) {
            ASN1TaggedObject instance = getInstance(obj2);
            if (i == instance.getTagClass()) {
                return instance;
            }
            throw new IllegalArgumentException("unexpected tag in getInstance: " + ASN1Util.getTagText(instance));
        }
        throw new NullPointerException("'obj' cannot be null");
    }

    public static ASN1TaggedObject getInstance(Object obj2, int i, int i2) {
        if (obj2 != null) {
            ASN1TaggedObject instance = getInstance(obj2);
            if (instance.hasTag(i, i2)) {
                return instance;
            }
            throw new IllegalArgumentException("unexpected tag in getInstance: " + ASN1Util.getTagText(instance));
        }
        throw new NullPointerException("'obj' cannot be null");
    }

    public static ASN1TaggedObject getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        if (128 != aSN1TaggedObject.getTagClass()) {
            throw new IllegalStateException("this method only valid for CONTEXT_SPECIFIC tags");
        } else if (z) {
            return aSN1TaggedObject.getExplicitBaseTagged();
        } else {
            throw new IllegalArgumentException("this method not valid for implicitly tagged tagged objects");
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean asn1Equals(ASN1Primitive aSN1Primitive) {
        if (!(aSN1Primitive instanceof ASN1TaggedObject)) {
            return false;
        }
        ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject) aSN1Primitive;
        if (this.tagNo != aSN1TaggedObject.tagNo || this.tagClass != aSN1TaggedObject.tagClass) {
            return false;
        }
        if (this.explicitness != aSN1TaggedObject.explicitness && isExplicit() != aSN1TaggedObject.isExplicit()) {
            return false;
        }
        ASN1Primitive aSN1Primitive2 = this.obj.toASN1Primitive();
        ASN1Primitive aSN1Primitive3 = aSN1TaggedObject.obj.toASN1Primitive();
        if (aSN1Primitive2 == aSN1Primitive3) {
            return true;
        }
        if (isExplicit()) {
            return aSN1Primitive2.asn1Equals(aSN1Primitive3);
        }
        try {
            return Arrays.areEqual(getEncoded(), aSN1TaggedObject.getEncoded());
        } catch (IOException unused) {
            return false;
        }
    }

    public ASN1Object getBaseObject() {
        ASN1Encodable aSN1Encodable = this.obj;
        return aSN1Encodable instanceof ASN1Object ? (ASN1Object) aSN1Encodable : aSN1Encodable.toASN1Primitive();
    }

    public ASN1Primitive getBaseUniversal(boolean z, int i) {
        ASN1UniversalType aSN1UniversalType = ASN1UniversalTypes.get(i);
        if (aSN1UniversalType != null) {
            return getBaseUniversal(z, aSN1UniversalType);
        }
        throw new IllegalArgumentException("unsupported UNIVERSAL tag number: " + i);
    }

    /* access modifiers changed from: package-private */
    public ASN1Primitive getBaseUniversal(boolean z, ASN1UniversalType aSN1UniversalType) {
        if (z) {
            if (isExplicit()) {
                return aSN1UniversalType.checkedCast(this.obj.toASN1Primitive());
            }
            throw new IllegalStateException("object explicit - implicit expected.");
        } else if (1 != this.explicitness) {
            ASN1Primitive aSN1Primitive = this.obj.toASN1Primitive();
            int i = this.explicitness;
            return i != 3 ? i != 4 ? aSN1UniversalType.checkedCast(aSN1Primitive) : aSN1Primitive instanceof ASN1Sequence ? aSN1UniversalType.fromImplicitConstructed((ASN1Sequence) aSN1Primitive) : aSN1UniversalType.fromImplicitPrimitive((DEROctetString) aSN1Primitive) : aSN1UniversalType.fromImplicitConstructed(rebuildConstructed(aSN1Primitive));
        } else {
            throw new IllegalStateException("object explicit - implicit expected.");
        }
    }

    public ASN1Object getExplicitBaseObject() {
        if (isExplicit()) {
            ASN1Encodable aSN1Encodable = this.obj;
            return aSN1Encodable instanceof ASN1Object ? (ASN1Object) aSN1Encodable : aSN1Encodable.toASN1Primitive();
        }
        throw new IllegalStateException("object implicit - explicit expected.");
    }

    public ASN1TaggedObject getExplicitBaseTagged() {
        if (isExplicit()) {
            return checkedCast(this.obj.toASN1Primitive());
        }
        throw new IllegalStateException("object implicit - explicit expected.");
    }

    public ASN1TaggedObject getImplicitBaseTagged(int i, int i2) {
        if (i == 0 || (i & 192) != i) {
            throw new IllegalArgumentException("invalid base tag class: " + i);
        }
        int i3 = this.explicitness;
        if (i3 != 1) {
            return i3 != 2 ? replaceTag(i, i2) : ASN1Util.checkTag(checkedCast(this.obj.toASN1Primitive()), i, i2);
        }
        throw new IllegalStateException("object explicit - implicit expected.");
    }

    public final ASN1Primitive getLoadedObject() {
        return this;
    }

    public int getTagClass() {
        return this.tagClass;
    }

    public int getTagNo() {
        return this.tagNo;
    }

    public boolean hasContextTag() {
        return this.tagClass == 128;
    }

    public boolean hasContextTag(int i) {
        return this.tagClass == 128 && this.tagNo == i;
    }

    public boolean hasTag(int i, int i2) {
        return this.tagClass == i && this.tagNo == i2;
    }

    public boolean hasTagClass(int i) {
        return this.tagClass == i;
    }

    public int hashCode() {
        return (((this.tagClass * 7919) ^ this.tagNo) ^ (isExplicit() ? 15 : 240)) ^ this.obj.toASN1Primitive().hashCode();
    }

    public boolean isExplicit() {
        int i = this.explicitness;
        return i == 1 || i == 3;
    }

    /* access modifiers changed from: package-private */
    public boolean isParsed() {
        int i = this.explicitness;
        return i == 3 || i == 4;
    }

    public ASN1Encodable parseBaseUniversal(boolean z, int i) throws IOException {
        ASN1Primitive baseUniversal = getBaseUniversal(z, i);
        return i != 3 ? i != 4 ? i != 16 ? i != 17 ? baseUniversal : ((ASN1Set) baseUniversal).parser() : ((ASN1Sequence) baseUniversal).parser() : ((ASN1OctetString) baseUniversal).parser() : ((ASN1BitString) baseUniversal).parser();
    }

    public ASN1Encodable parseExplicitBaseObject() throws IOException {
        return getExplicitBaseObject();
    }

    public ASN1TaggedObjectParser parseExplicitBaseTagged() throws IOException {
        return getExplicitBaseTagged();
    }

    public ASN1TaggedObjectParser parseImplicitBaseTagged(int i, int i2) throws IOException {
        return getImplicitBaseTagged(i, i2);
    }

    /* access modifiers changed from: package-private */
    public abstract ASN1Sequence rebuildConstructed(ASN1Primitive aSN1Primitive);

    /* access modifiers changed from: package-private */
    public abstract ASN1TaggedObject replaceTag(int i, int i2);

    /* access modifiers changed from: package-private */
    public ASN1Primitive toDERObject() {
        return new DERTaggedObject(this.explicitness, this.tagClass, this.tagNo, this.obj);
    }

    /* access modifiers changed from: package-private */
    public ASN1Primitive toDLObject() {
        return new DLTaggedObject(this.explicitness, this.tagClass, this.tagNo, this.obj);
    }

    public String toString() {
        return ASN1Util.getTagText(this.tagClass, this.tagNo) + this.obj;
    }
}
