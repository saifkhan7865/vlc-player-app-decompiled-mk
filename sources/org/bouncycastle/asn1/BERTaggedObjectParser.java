package org.bouncycastle.asn1;

import java.io.IOException;

class BERTaggedObjectParser implements ASN1TaggedObjectParser {
    final ASN1StreamParser _parser;
    final int _tagClass;
    final int _tagNo;

    BERTaggedObjectParser(int i, int i2, ASN1StreamParser aSN1StreamParser) {
        this._tagClass = i;
        this._tagNo = i2;
        this._parser = aSN1StreamParser;
    }

    public ASN1Primitive getLoadedObject() throws IOException {
        return this._parser.loadTaggedIL(this._tagClass, this._tagNo);
    }

    public int getTagClass() {
        return this._tagClass;
    }

    public int getTagNo() {
        return this._tagNo;
    }

    public boolean hasContextTag() {
        return this._tagClass == 128;
    }

    public boolean hasContextTag(int i) {
        return this._tagClass == 128 && this._tagNo == i;
    }

    public boolean hasTag(int i, int i2) {
        return this._tagClass == i && this._tagNo == i2;
    }

    public boolean hasTagClass(int i) {
        return this._tagClass == i;
    }

    public ASN1Encodable parseBaseUniversal(boolean z, int i) throws IOException {
        return z ? this._parser.parseObject(i) : this._parser.parseImplicitConstructedIL(i);
    }

    public ASN1Encodable parseExplicitBaseObject() throws IOException {
        return this._parser.readObject();
    }

    public ASN1TaggedObjectParser parseExplicitBaseTagged() throws IOException {
        return this._parser.parseTaggedObject();
    }

    public ASN1TaggedObjectParser parseImplicitBaseTagged(int i, int i2) throws IOException {
        return new BERTaggedObjectParser(i, i2, this._parser);
    }

    public ASN1Primitive toASN1Primitive() {
        try {
            return getLoadedObject();
        } catch (IOException e) {
            throw new ASN1ParsingException(e.getMessage());
        }
    }
}
