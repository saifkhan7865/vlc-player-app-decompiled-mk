package org.bouncycastle.pqc.crypto.saber;

import com.google.common.base.Ascii;
import io.netty.handler.codec.http2.Http2CodecUtil;
import okio.Utf8;

class Utils {
    private final int SABER_EP;
    private final int SABER_ET;
    private final int SABER_KEYBYTES;
    private final int SABER_L;
    private final int SABER_N;
    private final int SABER_POLYBYTES;
    private final boolean usingEffectiveMasking;

    public Utils(SABEREngine sABEREngine) {
        this.SABER_N = sABEREngine.getSABER_N();
        this.SABER_L = sABEREngine.getSABER_L();
        this.SABER_ET = sABEREngine.getSABER_ET();
        this.SABER_POLYBYTES = sABEREngine.getSABER_POLYBYTES();
        this.SABER_EP = sABEREngine.getSABER_EP();
        this.SABER_KEYBYTES = sABEREngine.getSABER_KEYBYTES();
        this.usingEffectiveMasking = sABEREngine.usingEffectiveMasking;
    }

    private void BS2POLq(byte[] bArr, int i, short[] sArr) {
        short s = 0;
        if (!this.usingEffectiveMasking) {
            while (s < this.SABER_N / 8) {
                short s2 = (short) (s * 8);
                int i2 = ((short) (s * 13)) + i;
                byte b = bArr[i2 + 1];
                sArr[s2] = (short) ((bArr[i2] & 255) | ((b & Ascii.US) << 8));
                int i3 = ((b >> 5) & 7) | ((bArr[i2 + 2] & 255) << 3);
                byte b2 = bArr[i2 + 3];
                sArr[s2 + 1] = (short) (i3 | ((b2 & 3) << Ascii.VT));
                byte b3 = bArr[i2 + 4];
                sArr[s2 + 2] = (short) (((b2 >> 2) & 63) | ((b3 & Byte.MAX_VALUE) << 6));
                int i4 = ((b3 >> 7) & 1) | ((bArr[i2 + 5] & 255) << 1);
                byte b4 = bArr[i2 + 6];
                sArr[s2 + 3] = (short) (i4 | ((b4 & Ascii.SI) << 9));
                int i5 = ((b4 >> 4) & 15) | ((bArr[i2 + 7] & 255) << 4);
                byte b5 = bArr[i2 + 8];
                sArr[s2 + 4] = (short) (i5 | ((b5 & 1) << Ascii.FF));
                byte b6 = bArr[i2 + 9];
                sArr[s2 + 5] = (short) (((b5 >> 1) & 127) | ((b6 & Utf8.REPLACEMENT_BYTE) << 7));
                int i6 = ((b6 >> 6) & 3) | ((bArr[i2 + 10] & 255) << 2);
                byte b7 = bArr[i2 + 11];
                sArr[s2 + 6] = (short) (i6 | ((b7 & 7) << 10));
                sArr[s2 + 7] = (short) (((bArr[i2 + 12] & 255) << 5) | ((b7 >> 3) & 31));
                s = (short) (s + 1);
            }
            return;
        }
        while (s < this.SABER_N / 2) {
            short s3 = (short) (s * 2);
            int i7 = ((short) (s * 3)) + i;
            byte b8 = bArr[i7 + 1];
            sArr[s3] = (short) ((bArr[i7] & 255) | ((b8 & Ascii.SI) << 8));
            sArr[s3 + 1] = (short) (((bArr[i7 + 2] & 255) << 4) | ((b8 >> 4) & 15));
            s = (short) (s + 1);
        }
    }

    private void POLp2BS(byte[] bArr, int i, short[] sArr) {
        for (short s = 0; s < this.SABER_N / 4; s = (short) (s + 1)) {
            short s2 = (short) (s * 4);
            int i2 = ((short) (s * 5)) + i;
            short s3 = sArr[s2];
            bArr[i2] = (byte) (s3 & Http2CodecUtil.MAX_UNSIGNED_BYTE);
            short s4 = sArr[s2 + 1];
            bArr[i2 + 1] = (byte) (((s3 >> 8) & 3) | ((s4 & 63) << 2));
            short s5 = sArr[s2 + 2];
            bArr[i2 + 2] = (byte) (((s4 >> 6) & 15) | ((s5 & 15) << 4));
            short s6 = sArr[s2 + 3];
            bArr[i2 + 3] = (byte) (((s5 >> 4) & 63) | ((s6 & 3) << 6));
            bArr[i2 + 4] = (byte) ((s6 >> 2) & 255);
        }
    }

    private void POLq2BS(byte[] bArr, int i, short[] sArr) {
        short s = 0;
        if (!this.usingEffectiveMasking) {
            while (s < this.SABER_N / 8) {
                short s2 = (short) (s * 8);
                int i2 = ((short) (s * 13)) + i;
                short s3 = sArr[s2];
                bArr[i2] = (byte) (s3 & Http2CodecUtil.MAX_UNSIGNED_BYTE);
                short s4 = sArr[s2 + 1];
                bArr[i2 + 1] = (byte) (((s3 >> 8) & 31) | ((s4 & 7) << 5));
                bArr[i2 + 2] = (byte) ((s4 >> 3) & 255);
                short s5 = sArr[s2 + 2];
                bArr[i2 + 3] = (byte) (((s4 >> 11) & 3) | ((s5 & 63) << 2));
                short s6 = sArr[s2 + 3];
                bArr[i2 + 4] = (byte) (((s5 >> 6) & 127) | ((s6 & 1) << 7));
                bArr[i2 + 5] = (byte) ((s6 >> 1) & 255);
                short s7 = sArr[s2 + 4];
                bArr[i2 + 6] = (byte) (((s6 >> 9) & 15) | ((s7 & 15) << 4));
                bArr[i2 + 7] = (byte) ((s7 >> 4) & 255);
                short s8 = sArr[s2 + 5];
                bArr[i2 + 8] = (byte) (((s7 >> 12) & 1) | ((s8 & 127) << 1));
                short s9 = sArr[s2 + 6];
                bArr[i2 + 9] = (byte) (((s8 >> 7) & 63) | ((s9 & 3) << 6));
                bArr[i2 + 10] = (byte) ((s9 >> 2) & 255);
                short s10 = sArr[s2 + 7];
                bArr[i2 + 11] = (byte) (((s9 >> 10) & 7) | ((s10 & 31) << 3));
                bArr[i2 + 12] = (byte) ((s10 >> 5) & 255);
                s = (short) (s + 1);
            }
            return;
        }
        while (s < this.SABER_N / 2) {
            short s11 = (short) (s * 2);
            int i3 = ((short) (s * 3)) + i;
            short s12 = sArr[s11];
            bArr[i3] = (byte) (s12 & Http2CodecUtil.MAX_UNSIGNED_BYTE);
            short s13 = sArr[s11 + 1];
            bArr[i3 + 1] = (byte) (((s12 >> 8) & 15) | ((s13 & 15) << 4));
            bArr[i3 + 2] = (byte) ((s13 >> 4) & 255);
            s = (short) (s + 1);
        }
    }

    public void BS2POLT(byte[] bArr, int i, short[] sArr) {
        int i2 = this.SABER_ET;
        short s = 0;
        if (i2 == 3) {
            while (s < this.SABER_N / 8) {
                short s2 = (short) (s * 8);
                int i3 = ((short) (s * 3)) + i;
                byte b = bArr[i3];
                sArr[s2] = (short) (b & 7);
                sArr[s2 + 1] = (short) ((b >> 3) & 7);
                byte b2 = bArr[i3 + 1];
                sArr[s2 + 2] = (short) (((b >> 6) & 3) | ((b2 & 1) << 2));
                sArr[s2 + 3] = (short) ((b2 >> 1) & 7);
                sArr[s2 + 4] = (short) ((b2 >> 4) & 7);
                byte b3 = bArr[i3 + 2];
                sArr[s2 + 5] = (short) (((b2 >> 7) & 1) | ((b3 & 3) << 1));
                sArr[s2 + 6] = (short) ((b3 >> 2) & 7);
                sArr[s2 + 7] = (short) ((b3 >> 5) & 7);
                s = (short) (s + 1);
            }
        } else if (i2 == 4) {
            while (s < this.SABER_N / 2) {
                short s3 = (short) (s * 2);
                byte b4 = bArr[i + s];
                sArr[s3] = (short) (b4 & Ascii.SI);
                sArr[s3 + 1] = (short) ((b4 >> 4) & 15);
                s = (short) (s + 1);
            }
        } else if (i2 == 6) {
            while (s < this.SABER_N / 4) {
                short s4 = (short) (s * 4);
                int i4 = ((short) (s * 3)) + i;
                byte b5 = bArr[i4];
                sArr[s4] = (short) (b5 & Utf8.REPLACEMENT_BYTE);
                byte b6 = bArr[i4 + 1];
                sArr[s4 + 1] = (short) (((b5 >> 6) & 3) | ((b6 & Ascii.SI) << 2));
                byte b7 = bArr[i4 + 2];
                sArr[s4 + 2] = (short) (((b6 & 255) >> 4) | ((b7 & 3) << 4));
                sArr[s4 + 3] = (short) ((b7 & 255) >> 2);
                s = (short) (s + 1);
            }
        }
    }

    public void BS2POLVECp(byte[] bArr, short[][] sArr) {
        for (byte b = 0; b < this.SABER_L; b = (byte) (b + 1)) {
            BS2POLp(bArr, ((this.SABER_EP * this.SABER_N) / 8) * b, sArr[b]);
        }
    }

    public void BS2POLVECq(byte[] bArr, int i, short[][] sArr) {
        for (byte b = 0; b < this.SABER_L; b = (byte) (b + 1)) {
            BS2POLq(bArr, (this.SABER_POLYBYTES * b) + i, sArr[b]);
        }
    }

    public void BS2POLmsg(byte[] bArr, short[] sArr) {
        for (byte b = 0; b < this.SABER_KEYBYTES; b = (byte) (b + 1)) {
            for (byte b2 = 0; b2 < 8; b2 = (byte) (b2 + 1)) {
                sArr[(b * 8) + b2] = (short) ((bArr[b] >> b2) & 1);
            }
        }
    }

    public void BS2POLp(byte[] bArr, int i, short[] sArr) {
        for (short s = 0; s < this.SABER_N / 4; s = (short) (s + 1)) {
            short s2 = (short) (s * 4);
            int i2 = ((short) (s * 5)) + i;
            byte b = bArr[i2 + 1];
            sArr[s2] = (short) ((bArr[i2] & 255) | ((b & 3) << 8));
            byte b2 = bArr[i2 + 2];
            sArr[s2 + 1] = (short) (((b >> 2) & 63) | ((b2 & Ascii.SI) << 6));
            byte b3 = bArr[i2 + 3];
            sArr[s2 + 2] = (short) (((b2 >> 4) & 15) | ((b3 & Utf8.REPLACEMENT_BYTE) << 4));
            sArr[s2 + 3] = (short) (((bArr[i2 + 4] & 255) << 2) | ((b3 >> 6) & 3));
        }
    }

    public void POLT2BS(byte[] bArr, int i, short[] sArr) {
        int i2 = this.SABER_ET;
        short s = 0;
        if (i2 == 3) {
            while (s < this.SABER_N / 8) {
                short s2 = (short) (s * 8);
                int i3 = ((short) (s * 3)) + i;
                short s3 = (sArr[s2] & 7) | ((sArr[s2 + 1] & 7) << 3);
                short s4 = sArr[s2 + 2];
                bArr[i3] = (byte) (s3 | ((s4 & 3) << 6));
                int i4 = ((s4 >> 2) & 1) | ((sArr[s2 + 3] & 7) << 1) | ((sArr[s2 + 4] & 7) << 4);
                short s5 = sArr[s2 + 5];
                bArr[i3 + 1] = (byte) (i4 | ((s5 & 1) << 7));
                bArr[i3 + 2] = (byte) (((sArr[s2 + 7] & 7) << 5) | ((s5 >> 1) & 3) | ((sArr[s2 + 6] & 7) << 2));
                s = (short) (s + 1);
            }
        } else if (i2 == 4) {
            while (s < this.SABER_N / 2) {
                short s6 = (short) (s * 2);
                bArr[i + s] = (byte) (((sArr[s6 + 1] & 15) << 4) | (sArr[s6] & 15));
                s = (short) (s + 1);
            }
        } else if (i2 == 6) {
            while (s < this.SABER_N / 4) {
                short s7 = (short) (s * 4);
                int i5 = ((short) (s * 3)) + i;
                short s8 = sArr[s7 + 1];
                bArr[i5] = (byte) ((sArr[s7] & 63) | ((s8 & 3) << 6));
                short s9 = sArr[s7 + 2];
                bArr[i5 + 1] = (byte) (((s8 >> 2) & 15) | ((s9 & 15) << 4));
                bArr[i5 + 2] = (byte) (((sArr[s7 + 3] & 63) << 2) | ((s9 >> 4) & 3));
                s = (short) (s + 1);
            }
        }
    }

    public void POLVECp2BS(byte[] bArr, short[][] sArr) {
        for (byte b = 0; b < this.SABER_L; b = (byte) (b + 1)) {
            POLp2BS(bArr, ((this.SABER_EP * this.SABER_N) / 8) * b, sArr[b]);
        }
    }

    public void POLVECq2BS(byte[] bArr, short[][] sArr) {
        for (byte b = 0; b < this.SABER_L; b = (byte) (b + 1)) {
            POLq2BS(bArr, this.SABER_POLYBYTES * b, sArr[b]);
        }
    }

    public void POLmsg2BS(byte[] bArr, short[] sArr) {
        for (byte b = 0; b < this.SABER_KEYBYTES; b = (byte) (b + 1)) {
            for (byte b2 = 0; b2 < 8; b2 = (byte) (b2 + 1)) {
                bArr[b] = (byte) (bArr[b] | ((sArr[(b * 8) + b2] & 1) << b2));
            }
        }
    }
}
