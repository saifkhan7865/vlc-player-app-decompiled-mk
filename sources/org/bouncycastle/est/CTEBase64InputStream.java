package org.bouncycastle.est;

import com.google.android.material.internal.ViewUtils;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

class CTEBase64InputStream extends InputStream {
    protected final byte[] data;
    protected final OutputStream dataOutputStream;
    protected boolean end;
    protected final Long max;
    protected final byte[] rawBuf;
    protected long read;
    protected int rp;
    protected final InputStream src;
    protected int wp;

    public CTEBase64InputStream(InputStream inputStream) {
        this(inputStream, (Long) null);
    }

    public CTEBase64InputStream(InputStream inputStream, Long l) {
        this.rawBuf = new byte[1024];
        this.data = new byte[ViewUtils.EDGE_TO_EDGE_FLAGS];
        this.src = inputStream;
        this.dataOutputStream = new OutputStream() {
            public void write(int i) throws IOException {
                byte[] bArr = CTEBase64InputStream.this.data;
                CTEBase64InputStream cTEBase64InputStream = CTEBase64InputStream.this;
                int i2 = cTEBase64InputStream.wp;
                cTEBase64InputStream.wp = i2 + 1;
                bArr[i2] = (byte) i;
            }
        };
        this.max = l;
    }

    public void close() throws IOException {
        this.src.close();
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x004a A[SYNTHETIC, Splitter:B:21:0x004a] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0067  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int pullFromSrc() throws java.io.IOException {
        /*
            r11 = this;
            r0 = 0
            r1 = 0
        L_0x0002:
            java.lang.Long r2 = r11.max
            r3 = -1
            if (r2 == 0) goto L_0x0012
            long r4 = r11.read
            long r6 = r2.longValue()
            int r2 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r2 <= 0) goto L_0x0012
            return r3
        L_0x0012:
            java.io.InputStream r2 = r11.src
            int r2 = r2.read()
            r4 = 33
            r5 = 10
            r6 = 1
            if (r2 >= r4) goto L_0x002f
            r4 = 13
            if (r2 == r4) goto L_0x002f
            if (r2 != r5) goto L_0x0027
            goto L_0x002f
        L_0x0027:
            if (r2 < 0) goto L_0x003f
            long r8 = r11.read
            long r8 = r8 + r6
            r11.read = r8
            goto L_0x003f
        L_0x002f:
            byte[] r4 = r11.rawBuf
            int r8 = r4.length
            if (r1 >= r8) goto L_0x006d
            int r8 = r1 + 1
            byte r9 = (byte) r2
            r4[r1] = r9
            long r9 = r11.read
            long r9 = r9 + r6
            r11.read = r9
            r1 = r8
        L_0x003f:
            if (r2 <= r3) goto L_0x0048
            byte[] r4 = r11.rawBuf
            int r4 = r4.length
            if (r1 >= r4) goto L_0x0048
            if (r2 != r5) goto L_0x0002
        L_0x0048:
            if (r1 <= 0) goto L_0x0067
            byte[] r2 = r11.rawBuf     // Catch:{ Exception -> 0x0052 }
            java.io.OutputStream r3 = r11.dataOutputStream     // Catch:{ Exception -> 0x0052 }
            org.bouncycastle.util.encoders.Base64.decode(r2, r0, r1, r3)     // Catch:{ Exception -> 0x0052 }
            goto L_0x006a
        L_0x0052:
            r0 = move-exception
            java.io.IOException r1 = new java.io.IOException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "Decode Base64 Content-Transfer-Encoding: "
            r2.<init>(r3)
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            r1.<init>(r0)
            throw r1
        L_0x0067:
            if (r2 != r3) goto L_0x006a
            return r3
        L_0x006a:
            int r0 = r11.wp
            return r0
        L_0x006d:
            java.io.IOException r0 = new java.io.IOException
            java.lang.String r1 = "Content Transfer Encoding, base64 line length > 1024"
            r0.<init>(r1)
            goto L_0x0076
        L_0x0075:
            throw r0
        L_0x0076:
            goto L_0x0075
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.est.CTEBase64InputStream.pullFromSrc():int");
    }

    public int read() throws IOException {
        if (this.rp == this.wp) {
            this.rp = 0;
            this.wp = 0;
            int pullFromSrc = pullFromSrc();
            if (pullFromSrc == -1) {
                return pullFromSrc;
            }
        }
        byte[] bArr = this.data;
        int i = this.rp;
        this.rp = i + 1;
        return bArr[i] & 255;
    }
}
