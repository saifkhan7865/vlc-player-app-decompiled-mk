package org.bouncycastle.pqc.crypto.cmce;

import io.netty.handler.codec.http2.Http2CodecUtil;
import org.bouncycastle.util.Pack;

class Utils {
    Utils() {
    }

    static short bitrev(short s, int i) {
        short s2 = (short) (((s & 65280) >> 8) | ((s & Http2CodecUtil.MAX_UNSIGNED_BYTE) << 8));
        short s3 = (short) (((s2 & 61680) >> 4) | ((s2 & 3855) << 4));
        short s4 = (short) (((s3 & 52428) >> 2) | ((s3 & 13107) << 2));
        short s5 = (short) (((s4 & 43690) >> 1) | ((s4 & 21845) << 1));
        return (short) (i == 12 ? s5 >> 4 : s5 >> 3);
    }

    static int load4(byte[] bArr, int i) {
        return Pack.littleEndianToInt(bArr, i);
    }

    static long load8(byte[] bArr, int i) {
        return Pack.littleEndianToLong(bArr, i);
    }

    static short load_gf(byte[] bArr, int i, int i2) {
        return (short) (Pack.littleEndianToShort(bArr, i) & i2);
    }

    static void store8(byte[] bArr, int i, long j) {
        bArr[i] = (byte) ((int) (j & 255));
        bArr[i + 1] = (byte) ((int) ((j >> 8) & 255));
        bArr[i + 2] = (byte) ((int) ((j >> 16) & 255));
        bArr[i + 3] = (byte) ((int) ((j >> 24) & 255));
        bArr[i + 4] = (byte) ((int) ((j >> 32) & 255));
        bArr[i + 5] = (byte) ((int) ((j >> 40) & 255));
        bArr[i + 6] = (byte) ((int) ((j >> 48) & 255));
        bArr[i + 7] = (byte) ((int) ((j >> 56) & 255));
    }

    static void store_gf(byte[] bArr, int i, short s) {
        bArr[i] = (byte) (s & Http2CodecUtil.MAX_UNSIGNED_BYTE);
        bArr[i + 1] = (byte) (s >> 8);
    }
}
