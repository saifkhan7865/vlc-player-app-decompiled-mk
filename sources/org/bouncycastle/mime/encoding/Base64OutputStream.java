package org.bouncycastle.mime.encoding;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.bouncycastle.util.encoders.Base64Encoder;

public class Base64OutputStream extends FilterOutputStream {
    private static final Base64Encoder ENCODER = new Base64Encoder();
    private static final int INBUF_SIZE = 54;
    private static final int OUTBUF_SIZE = 74;
    private final byte[] inBuf = new byte[54];
    private int inPos;
    private final byte[] outBuf;

    public Base64OutputStream(OutputStream outputStream) {
        super(outputStream);
        byte[] bArr = new byte[74];
        this.outBuf = bArr;
        this.inPos = 0;
        bArr[72] = 13;
        bArr[73] = 10;
    }

    private void encodeBlock(byte[] bArr, int i) throws IOException {
        ENCODER.encode(bArr, i, 54, this.outBuf, 0);
        this.out.write(this.outBuf, 0, 74);
    }

    public void close() throws IOException {
        int i = this.inPos;
        if (i > 0) {
            int encode = ENCODER.encode(this.inBuf, 0, i, this.outBuf, 0);
            this.inPos = 0;
            byte[] bArr = this.outBuf;
            bArr[encode] = 13;
            bArr[encode + 1] = 10;
            this.out.write(this.outBuf, 0, encode + 2);
        }
        this.out.close();
    }

    public void write(int i) throws IOException {
        byte[] bArr = this.inBuf;
        int i2 = this.inPos;
        int i3 = i2 + 1;
        this.inPos = i3;
        bArr[i2] = (byte) i;
        if (i3 == 54) {
            encodeBlock(bArr, 0);
            this.inPos = 0;
        }
    }

    public void write(byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        int i3 = this.inPos;
        int i4 = 54 - i3;
        if (i2 < i4) {
            System.arraycopy(bArr, i, this.inBuf, i3, i2);
            this.inPos += i2;
            return;
        }
        if (i3 > 0) {
            System.arraycopy(bArr, i, this.inBuf, i3, i4);
            encodeBlock(this.inBuf, 0);
        } else {
            i4 = 0;
        }
        while (true) {
            int i5 = i2 - i4;
            if (i5 >= 54) {
                encodeBlock(bArr, i + i4);
                i4 += 54;
            } else {
                System.arraycopy(bArr, i + i4, this.inBuf, 0, i5);
                this.inPos = i5;
                return;
            }
        }
    }
}
