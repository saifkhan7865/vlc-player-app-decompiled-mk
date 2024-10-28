package org.bouncycastle.asn1;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;

class LazyEncodedSequence extends ASN1Sequence {
    private byte[] encoded;

    LazyEncodedSequence(byte[] bArr) throws IOException {
        if (bArr != null) {
            this.encoded = bArr;
            return;
        }
        throw new NullPointerException("'encoded' cannot be null");
    }

    private synchronized void force() {
        if (this.encoded != null) {
            ASN1InputStream aSN1InputStream = new ASN1InputStream(this.encoded, true);
            try {
                ASN1EncodableVector readVector = aSN1InputStream.readVector();
                aSN1InputStream.close();
                this.elements = readVector.takeElements();
                this.encoded = null;
            } catch (IOException e) {
                throw new ASN1ParsingException("malformed ASN.1: " + e, e);
            }
        }
    }

    private synchronized byte[] getContents() {
        return this.encoded;
    }

    /* access modifiers changed from: package-private */
    public void encode(ASN1OutputStream aSN1OutputStream, boolean z) throws IOException {
        byte[] contents = getContents();
        if (contents != null) {
            aSN1OutputStream.writeEncodingDL(z, 48, contents);
        } else {
            super.toDLObject().encode(aSN1OutputStream, z);
        }
    }

    /* access modifiers changed from: package-private */
    public int encodedLength(boolean z) throws IOException {
        byte[] contents = getContents();
        return contents != null ? ASN1OutputStream.getLengthOfEncodingDL(z, contents.length) : super.toDLObject().encodedLength(z);
    }

    public ASN1Encodable getObjectAt(int i) {
        force();
        return super.getObjectAt(i);
    }

    public Enumeration getObjects() {
        byte[] contents = getContents();
        return contents != null ? new LazyConstructionEnumeration(contents) : super.getObjects();
    }

    public int hashCode() {
        force();
        return super.hashCode();
    }

    public Iterator<ASN1Encodable> iterator() {
        force();
        return super.iterator();
    }

    public int size() {
        force();
        return super.size();
    }

    /* access modifiers changed from: package-private */
    public ASN1BitString toASN1BitString() {
        return ((ASN1Sequence) toDLObject()).toASN1BitString();
    }

    /* access modifiers changed from: package-private */
    public ASN1External toASN1External() {
        return ((ASN1Sequence) toDLObject()).toASN1External();
    }

    /* access modifiers changed from: package-private */
    public ASN1OctetString toASN1OctetString() {
        return ((ASN1Sequence) toDLObject()).toASN1OctetString();
    }

    /* access modifiers changed from: package-private */
    public ASN1Set toASN1Set() {
        return ((ASN1Sequence) toDLObject()).toASN1Set();
    }

    public ASN1Encodable[] toArray() {
        force();
        return super.toArray();
    }

    /* access modifiers changed from: package-private */
    public ASN1Encodable[] toArrayInternal() {
        force();
        return super.toArrayInternal();
    }

    /* access modifiers changed from: package-private */
    public ASN1Primitive toDERObject() {
        force();
        return super.toDERObject();
    }

    /* access modifiers changed from: package-private */
    public ASN1Primitive toDLObject() {
        force();
        return super.toDLObject();
    }
}
