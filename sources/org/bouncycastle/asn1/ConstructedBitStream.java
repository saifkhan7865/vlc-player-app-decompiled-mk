package org.bouncycastle.asn1;

import java.io.IOException;
import java.io.InputStream;

class ConstructedBitStream extends InputStream {
    private ASN1BitStringParser _currentParser;
    private InputStream _currentStream;
    private boolean _first = true;
    private final boolean _octetAligned;
    private int _padBits = 0;
    private final ASN1StreamParser _parser;

    ConstructedBitStream(ASN1StreamParser aSN1StreamParser, boolean z) {
        this._parser = aSN1StreamParser;
        this._octetAligned = z;
    }

    private ASN1BitStringParser getNextParser() throws IOException {
        ASN1Encodable readObject = this._parser.readObject();
        if (readObject == null) {
            if (!this._octetAligned || this._padBits == 0) {
                return null;
            }
            throw new IOException("expected octet-aligned bitstring, but found padBits: " + this._padBits);
        } else if (!(readObject instanceof ASN1BitStringParser)) {
            throw new IOException("unknown object encountered: " + readObject.getClass());
        } else if (this._padBits == 0) {
            return (ASN1BitStringParser) readObject;
        } else {
            throw new IOException("only the last nested bitstring can have padding");
        }
    }

    /* access modifiers changed from: package-private */
    public int getPadBits() {
        return this._padBits;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0020  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int read() throws java.io.IOException {
        /*
            r3 = this;
            java.io.InputStream r0 = r3._currentStream
            r1 = -1
            if (r0 != 0) goto L_0x0017
            boolean r0 = r3._first
            if (r0 != 0) goto L_0x000a
            return r1
        L_0x000a:
            org.bouncycastle.asn1.ASN1BitStringParser r0 = r3.getNextParser()
            r3._currentParser = r0
            if (r0 != 0) goto L_0x0013
            return r1
        L_0x0013:
            r2 = 0
            r3._first = r2
            goto L_0x0034
        L_0x0017:
            java.io.InputStream r0 = r3._currentStream
            int r0 = r0.read()
            if (r0 < 0) goto L_0x0020
            return r0
        L_0x0020:
            org.bouncycastle.asn1.ASN1BitStringParser r0 = r3._currentParser
            int r0 = r0.getPadBits()
            r3._padBits = r0
            org.bouncycastle.asn1.ASN1BitStringParser r0 = r3.getNextParser()
            r3._currentParser = r0
            if (r0 != 0) goto L_0x0034
            r0 = 0
            r3._currentStream = r0
            return r1
        L_0x0034:
            java.io.InputStream r0 = r0.getBitStream()
            r3._currentStream = r0
            goto L_0x0017
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.asn1.ConstructedBitStream.read():int");
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0028  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x002c A[EDGE_INSN: B:20:0x002c->B:15:0x002c ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x002b A[SYNTHETIC] */
    public int read(byte[] r6, int r7, int r8) throws java.io.IOException {
        /*
            r5 = this;
            java.io.InputStream r0 = r5._currentStream
            r1 = 0
            r2 = -1
            if (r0 != 0) goto L_0x001c
            boolean r0 = r5._first
            if (r0 != 0) goto L_0x000b
            return r2
        L_0x000b:
            org.bouncycastle.asn1.ASN1BitStringParser r0 = r5.getNextParser()
            r5._currentParser = r0
            if (r0 != 0) goto L_0x0014
            return r2
        L_0x0014:
            r5._first = r1
        L_0x0016:
            java.io.InputStream r0 = r0.getBitStream()
            r5._currentStream = r0
        L_0x001c:
            java.io.InputStream r0 = r5._currentStream
            int r3 = r7 + r1
            int r4 = r8 - r1
            int r0 = r0.read(r6, r3, r4)
            if (r0 < 0) goto L_0x002c
            int r1 = r1 + r0
            if (r1 != r8) goto L_0x001c
            return r1
        L_0x002c:
            org.bouncycastle.asn1.ASN1BitStringParser r0 = r5._currentParser
            int r0 = r0.getPadBits()
            r5._padBits = r0
            org.bouncycastle.asn1.ASN1BitStringParser r0 = r5.getNextParser()
            r5._currentParser = r0
            if (r0 != 0) goto L_0x0016
            r6 = 0
            r5._currentStream = r6
            r6 = 1
            if (r1 >= r6) goto L_0x0043
            goto L_0x0044
        L_0x0043:
            r2 = r1
        L_0x0044:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.asn1.ConstructedBitStream.read(byte[], int, int):int");
    }
}
