package org.bouncycastle.asn1;

import java.io.IOException;

class DLTaggedObjectParser extends BERTaggedObjectParser {
    private final boolean _constructed;

    DLTaggedObjectParser(int i, int i2, boolean z, ASN1StreamParser aSN1StreamParser) {
        super(i, i2, aSN1StreamParser);
        this._constructed = z;
    }

    public ASN1Primitive getLoadedObject() throws IOException {
        return this._parser.loadTaggedDL(this._tagClass, this._tagNo, this._constructed);
    }

    public ASN1Encodable parseBaseUniversal(boolean z, int i) throws IOException {
        if (!z) {
            return this._constructed ? this._parser.parseImplicitConstructedDL(i) : this._parser.parseImplicitPrimitive(i);
        }
        if (this._constructed) {
            return this._parser.parseObject(i);
        }
        throw new IOException("Explicit tags must be constructed (see X.690 8.14.2)");
    }

    public ASN1Encodable parseExplicitBaseObject() throws IOException {
        if (this._constructed) {
            return this._parser.readObject();
        }
        throw new IOException("Explicit tags must be constructed (see X.690 8.14.2)");
    }

    public ASN1TaggedObjectParser parseExplicitBaseTagged() throws IOException {
        if (this._constructed) {
            return this._parser.parseTaggedObject();
        }
        throw new IOException("Explicit tags must be constructed (see X.690 8.14.2)");
    }

    public ASN1TaggedObjectParser parseImplicitBaseTagged(int i, int i2) throws IOException {
        return new DLTaggedObjectParser(i, i2, this._constructed, this._parser);
    }
}
