package org.fusesource.jansi.io;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class FastBufferedOutputStream extends FilterOutputStream {
    protected final byte[] buf = new byte[8192];
    protected int count;

    public FastBufferedOutputStream(OutputStream outputStream) {
        super(outputStream);
    }

    public void write(int i) throws IOException {
        if (this.count >= this.buf.length) {
            flushBuffer();
        }
        byte[] bArr = this.buf;
        int i2 = this.count;
        this.count = i2 + 1;
        bArr[i2] = (byte) i;
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        byte[] bArr2 = this.buf;
        if (i2 >= bArr2.length) {
            flushBuffer();
            this.out.write(bArr, i, i2);
            return;
        }
        if (i2 > bArr2.length - this.count) {
            flushBuffer();
        }
        System.arraycopy(bArr, i, this.buf, this.count, i2);
        this.count += i2;
    }

    private void flushBuffer() throws IOException {
        if (this.count > 0) {
            this.out.write(this.buf, 0, this.count);
            this.count = 0;
        }
    }

    public void flush() throws IOException {
        flushBuffer();
        this.out.flush();
    }
}
