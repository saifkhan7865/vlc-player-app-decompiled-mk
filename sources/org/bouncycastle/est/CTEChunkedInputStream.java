package org.bouncycastle.est;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class CTEChunkedInputStream extends InputStream {
    int chunkLen = 0;
    private InputStream src;

    public CTEChunkedInputStream(InputStream inputStream) {
        this.src = inputStream;
    }

    private String readEOL() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (true) {
            int read = this.src.read();
            if (read != -1) {
                byteArrayOutputStream.write(read & 255);
                if (read == 10) {
                    break;
                }
            } else if (byteArrayOutputStream.size() == 0) {
                return null;
            }
        }
        return byteArrayOutputStream.toString().trim();
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0019  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0018 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int read() throws java.io.IOException {
        /*
            r4 = this;
            int r0 = r4.chunkLen
            r1 = -1
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r0 != r2) goto L_0x0008
            return r1
        L_0x0008:
            if (r0 != 0) goto L_0x002d
        L_0x000a:
            java.lang.String r0 = r4.readEOL()
            if (r0 == 0) goto L_0x0016
            int r3 = r0.length()
            if (r3 == 0) goto L_0x000a
        L_0x0016:
            if (r0 != 0) goto L_0x0019
            return r1
        L_0x0019:
            java.lang.String r0 = r0.trim()
            r3 = 16
            int r0 = java.lang.Integer.parseInt(r0, r3)
            r4.chunkLen = r0
            if (r0 != 0) goto L_0x002d
            r4.readEOL()
            r4.chunkLen = r2
            return r1
        L_0x002d:
            java.io.InputStream r0 = r4.src
            int r0 = r0.read()
            int r1 = r4.chunkLen
            int r1 = r1 + -1
            r4.chunkLen = r1
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.est.CTEChunkedInputStream.read():int");
    }
}
