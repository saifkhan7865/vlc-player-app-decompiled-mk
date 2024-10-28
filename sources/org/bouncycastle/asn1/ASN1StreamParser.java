package org.bouncycastle.asn1;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ASN1StreamParser {
    private final InputStream _in;
    private final int _limit;
    private final byte[][] tmpBuffers;

    public ASN1StreamParser(InputStream inputStream) {
        this(inputStream, StreamUtil.findLimit(inputStream));
    }

    public ASN1StreamParser(InputStream inputStream, int i) {
        this(inputStream, i, new byte[11][]);
    }

    ASN1StreamParser(InputStream inputStream, int i, byte[][] bArr) {
        this._in = inputStream;
        this._limit = i;
        this.tmpBuffers = bArr;
    }

    public ASN1StreamParser(byte[] bArr) {
        this(new ByteArrayInputStream(bArr), bArr.length);
    }

    private void set00Check(boolean z) {
        InputStream inputStream = this._in;
        if (inputStream instanceof IndefiniteLengthInputStream) {
            ((IndefiniteLengthInputStream) inputStream).setEofOn00(z);
        }
    }

    /* access modifiers changed from: package-private */
    public ASN1Encodable implParseObject(int i) throws IOException {
        boolean z = false;
        set00Check(false);
        int readTagNumber = ASN1InputStream.readTagNumber(this._in, i);
        int readLength = ASN1InputStream.readLength(this._in, this._limit, readTagNumber == 3 || readTagNumber == 4 || readTagNumber == 16 || readTagNumber == 17 || readTagNumber == 8);
        if (readLength >= 0) {
            DefiniteLengthInputStream definiteLengthInputStream = new DefiniteLengthInputStream(this._in, readLength, this._limit);
            if ((i & BERTags.FLAGS) == 0) {
                return parseImplicitPrimitive(readTagNumber, definiteLengthInputStream);
            }
            ASN1StreamParser aSN1StreamParser = new ASN1StreamParser(definiteLengthInputStream, definiteLengthInputStream.getLimit(), this.tmpBuffers);
            int i2 = i & 192;
            if (i2 == 0) {
                return aSN1StreamParser.parseImplicitConstructedDL(readTagNumber);
            }
            if ((i & 32) != 0) {
                z = true;
            }
            return new DLTaggedObjectParser(i2, readTagNumber, z, aSN1StreamParser);
        } else if ((i & 32) != 0) {
            ASN1StreamParser aSN1StreamParser2 = new ASN1StreamParser(new IndefiniteLengthInputStream(this._in, this._limit), this._limit, this.tmpBuffers);
            int i3 = i & 192;
            return i3 != 0 ? new BERTaggedObjectParser(i3, readTagNumber, aSN1StreamParser2) : aSN1StreamParser2.parseImplicitConstructedIL(readTagNumber);
        } else {
            throw new IOException("indefinite-length primitive encoding encountered");
        }
    }

    /* access modifiers changed from: package-private */
    public ASN1Primitive loadTaggedDL(int i, int i2, boolean z) throws IOException {
        return !z ? ASN1TaggedObject.createPrimitive(i, i2, ((DefiniteLengthInputStream) this._in).toByteArray()) : ASN1TaggedObject.createConstructedDL(i, i2, readVector());
    }

    /* access modifiers changed from: package-private */
    public ASN1Primitive loadTaggedIL(int i, int i2) throws IOException {
        return ASN1TaggedObject.createConstructedIL(i, i2, readVector());
    }

    /* access modifiers changed from: package-private */
    public ASN1Encodable parseImplicitConstructedDL(int i) throws IOException {
        if (i == 3) {
            return new BERBitStringParser(this);
        }
        if (i == 4) {
            return new BEROctetStringParser(this);
        }
        if (i == 8) {
            return new DERExternalParser(this);
        }
        if (i == 16) {
            return new DLSequenceParser(this);
        }
        if (i == 17) {
            return new DLSetParser(this);
        }
        throw new ASN1Exception("unknown DL object encountered: 0x" + Integer.toHexString(i));
    }

    /* access modifiers changed from: package-private */
    public ASN1Encodable parseImplicitConstructedIL(int i) throws IOException {
        if (i == 3) {
            return new BERBitStringParser(this);
        }
        if (i == 4) {
            return new BEROctetStringParser(this);
        }
        if (i == 8) {
            return new DERExternalParser(this);
        }
        if (i == 16) {
            return new BERSequenceParser(this);
        }
        if (i == 17) {
            return new BERSetParser(this);
        }
        throw new ASN1Exception("unknown BER object encountered: 0x" + Integer.toHexString(i));
    }

    /* access modifiers changed from: package-private */
    public ASN1Encodable parseImplicitPrimitive(int i) throws IOException {
        return parseImplicitPrimitive(i, (DefiniteLengthInputStream) this._in);
    }

    /* access modifiers changed from: package-private */
    public ASN1Encodable parseImplicitPrimitive(int i, DefiniteLengthInputStream definiteLengthInputStream) throws IOException {
        if (i == 3) {
            return new DLBitStringParser(definiteLengthInputStream);
        }
        if (i == 4) {
            return new DEROctetStringParser(definiteLengthInputStream);
        }
        if (i == 8) {
            throw new ASN1Exception("externals must use constructed encoding (see X.690 8.18)");
        } else if (i == 16) {
            throw new ASN1Exception("sets must use constructed encoding (see X.690 8.11.1/8.12.1)");
        } else if (i != 17) {
            try {
                return ASN1InputStream.createPrimitiveDERObject(i, definiteLengthInputStream, this.tmpBuffers);
            } catch (IllegalArgumentException e) {
                throw new ASN1Exception("corrupted stream detected", e);
            }
        } else {
            throw new ASN1Exception("sequences must use constructed encoding (see X.690 8.9.1/8.10.1)");
        }
    }

    /* access modifiers changed from: package-private */
    public ASN1Encodable parseObject(int i) throws IOException {
        if (i < 0 || i > 30) {
            throw new IllegalArgumentException("invalid universal tag number: " + i);
        }
        int read = this._in.read();
        if (read < 0) {
            return null;
        }
        if ((read & -33) == i) {
            return implParseObject(read);
        }
        throw new IOException("unexpected identifier encountered: " + read);
    }

    /* access modifiers changed from: package-private */
    public ASN1TaggedObjectParser parseTaggedObject() throws IOException {
        int read = this._in.read();
        if (read < 0) {
            return null;
        }
        if ((read & 192) != 0) {
            return (ASN1TaggedObjectParser) implParseObject(read);
        }
        throw new ASN1Exception("no tagged object found");
    }

    public ASN1Encodable readObject() throws IOException {
        int read = this._in.read();
        if (read < 0) {
            return null;
        }
        return implParseObject(read);
    }

    /* access modifiers changed from: package-private */
    public ASN1EncodableVector readVector() throws IOException {
        int read = this._in.read();
        if (read < 0) {
            return new ASN1EncodableVector(0);
        }
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        do {
            ASN1Encodable implParseObject = implParseObject(read);
            aSN1EncodableVector.add(implParseObject instanceof InMemoryRepresentable ? ((InMemoryRepresentable) implParseObject).getLoadedObject() : implParseObject.toASN1Primitive());
            read = this._in.read();
        } while (read >= 0);
        return aSN1EncodableVector;
    }
}
