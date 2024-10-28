package org.bouncycastle.asn1;

import java.io.IOException;
import java.io.OutputStream;

public abstract class BERGenerator extends ASN1Generator {
    private boolean _isExplicit;
    private int _tagNo;
    private boolean _tagged = false;

    protected BERGenerator(OutputStream outputStream) {
        super(outputStream);
    }

    protected BERGenerator(OutputStream outputStream, int i, boolean z) {
        super(outputStream);
        this._isExplicit = z;
        this._tagNo = i;
    }

    private void writeHdr(int i) throws IOException {
        this._out.write(i);
        this._out.write(128);
    }

    public OutputStream getRawOutputStream() {
        return this._out;
    }

    /* access modifiers changed from: protected */
    public void writeBEREnd() throws IOException {
        this._out.write(0);
        this._out.write(0);
        if (this._tagged && this._isExplicit) {
            this._out.write(0);
            this._out.write(0);
        }
    }

    /* access modifiers changed from: protected */
    public void writeBERHeader(int i) throws IOException {
        if (this._tagged) {
            int i2 = this._tagNo;
            int i3 = i2 | 128;
            if (this._isExplicit) {
                writeHdr(i2 | 160);
            } else if ((i & 32) != 0) {
                i = i2 | 160;
            } else {
                writeHdr(i3);
                return;
            }
        }
        writeHdr(i);
    }
}
