package org.bouncycastle.cert.selector;

import com.google.common.base.Ascii;
import java.io.IOException;
import org.bouncycastle.asn1.ASN1Encoding;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.pqc.jcajce.spec.McElieceCCA2KeyGenParameterSpec;
import org.bouncycastle.util.Pack;

class MSOutlookKeyIdCalculator {

    private static abstract class GeneralDigest {
        private static final int BYTE_LENGTH = 64;
        private long byteCount;
        private byte[] xBuf;
        private int xBufOff;

        protected GeneralDigest() {
            this.xBuf = new byte[4];
            this.xBufOff = 0;
        }

        protected GeneralDigest(GeneralDigest generalDigest) {
            this.xBuf = new byte[generalDigest.xBuf.length];
            copyIn(generalDigest);
        }

        /* access modifiers changed from: protected */
        public void copyIn(GeneralDigest generalDigest) {
            byte[] bArr = generalDigest.xBuf;
            System.arraycopy(bArr, 0, this.xBuf, 0, bArr.length);
            this.xBufOff = generalDigest.xBufOff;
            this.byteCount = generalDigest.byteCount;
        }

        public void finish() {
            long j = this.byteCount << 3;
            byte b = Byte.MIN_VALUE;
            while (true) {
                update(b);
                if (this.xBufOff != 0) {
                    b = 0;
                } else {
                    processLength(j);
                    processBlock();
                    return;
                }
            }
        }

        /* access modifiers changed from: protected */
        public abstract void processBlock();

        /* access modifiers changed from: protected */
        public abstract void processLength(long j);

        /* access modifiers changed from: protected */
        public abstract void processWord(byte[] bArr, int i);

        public void reset() {
            this.byteCount = 0;
            this.xBufOff = 0;
            int i = 0;
            while (true) {
                byte[] bArr = this.xBuf;
                if (i < bArr.length) {
                    bArr[i] = 0;
                    i++;
                } else {
                    return;
                }
            }
        }

        public void update(byte b) {
            byte[] bArr = this.xBuf;
            int i = this.xBufOff;
            int i2 = i + 1;
            this.xBufOff = i2;
            bArr[i] = b;
            if (i2 == bArr.length) {
                processWord(bArr, 0);
                this.xBufOff = 0;
            }
            this.byteCount++;
        }

        public void update(byte[] bArr, int i, int i2) {
            while (this.xBufOff != 0 && i2 > 0) {
                update(bArr[i]);
                i++;
                i2--;
            }
            while (i2 > this.xBuf.length) {
                processWord(bArr, i);
                byte[] bArr2 = this.xBuf;
                i += bArr2.length;
                i2 -= bArr2.length;
                this.byteCount += (long) bArr2.length;
            }
            while (i2 > 0) {
                update(bArr[i]);
                i++;
                i2--;
            }
        }
    }

    private static class SHA1Digest extends GeneralDigest {
        private static final int DIGEST_LENGTH = 20;
        private static final int Y1 = 1518500249;
        private static final int Y2 = 1859775393;
        private static final int Y3 = -1894007588;
        private static final int Y4 = -899497514;
        private int H1;
        private int H2;
        private int H3;
        private int H4;
        private int H5;
        private int[] X = new int[80];
        private int xOff;

        public SHA1Digest() {
            reset();
        }

        private int f(int i, int i2, int i3) {
            return ((i ^ -1) & i3) | (i2 & i);
        }

        private int g(int i, int i2, int i3) {
            return (i & i3) | (i & i2) | (i2 & i3);
        }

        private int h(int i, int i2, int i3) {
            return (i ^ i2) ^ i3;
        }

        public int doFinal(byte[] bArr, int i) {
            finish();
            Pack.intToBigEndian(this.H1, bArr, i);
            Pack.intToBigEndian(this.H2, bArr, i + 4);
            Pack.intToBigEndian(this.H3, bArr, i + 8);
            Pack.intToBigEndian(this.H4, bArr, i + 12);
            Pack.intToBigEndian(this.H5, bArr, i + 16);
            reset();
            return 20;
        }

        public String getAlgorithmName() {
            return McElieceCCA2KeyGenParameterSpec.SHA1;
        }

        public int getDigestSize() {
            return 20;
        }

        /* access modifiers changed from: protected */
        public void processBlock() {
            for (int i = 16; i < 80; i++) {
                int[] iArr = this.X;
                int i2 = ((iArr[i - 3] ^ iArr[i - 8]) ^ iArr[i - 14]) ^ iArr[i - 16];
                iArr[i] = (i2 >>> 31) | (i2 << 1);
            }
            int i3 = this.H1;
            int i4 = this.H2;
            int i5 = this.H3;
            int i6 = this.H4;
            int i7 = this.H5;
            int i8 = 0;
            for (int i9 = 0; i9 < 4; i9++) {
                int f = i7 + ((i3 << 5) | (i3 >>> 27)) + f(i4, i5, i6) + this.X[i8] + Y1;
                int i10 = (i4 >>> 2) | (i4 << 30);
                int f2 = i6 + ((f << 5) | (f >>> 27)) + f(i3, i10, i5) + this.X[i8 + 1] + Y1;
                int i11 = (i3 >>> 2) | (i3 << 30);
                int f3 = i5 + ((f2 << 5) | (f2 >>> 27)) + f(f, i11, i10) + this.X[i8 + 2] + Y1;
                i7 = (f >>> 2) | (f << 30);
                int i12 = i8 + 4;
                i4 = i10 + ((f3 << 5) | (f3 >>> 27)) + f(f2, i7, i11) + this.X[i8 + 3] + Y1;
                i6 = (f2 >>> 2) | (f2 << 30);
                i8 += 5;
                i3 = i11 + ((i4 << 5) | (i4 >>> 27)) + f(f3, i6, i7) + this.X[i12] + Y1;
                i5 = (f3 >>> 2) | (f3 << 30);
            }
            for (int i13 = 0; i13 < 4; i13++) {
                int h = i7 + ((i3 << 5) | (i3 >>> 27)) + h(i4, i5, i6) + this.X[i8] + Y2;
                int i14 = (i4 >>> 2) | (i4 << 30);
                int h2 = i6 + ((h << 5) | (h >>> 27)) + h(i3, i14, i5) + this.X[i8 + 1] + Y2;
                int i15 = (i3 >>> 2) | (i3 << 30);
                int h3 = i5 + ((h2 << 5) | (h2 >>> 27)) + h(h, i15, i14) + this.X[i8 + 2] + Y2;
                i7 = (h >>> 2) | (h << 30);
                int i16 = i8 + 4;
                i4 = i14 + ((h3 << 5) | (h3 >>> 27)) + h(h2, i7, i15) + this.X[i8 + 3] + Y2;
                i6 = (h2 >>> 2) | (h2 << 30);
                i8 += 5;
                i3 = i15 + ((i4 << 5) | (i4 >>> 27)) + h(h3, i6, i7) + this.X[i16] + Y2;
                i5 = (h3 >>> 2) | (h3 << 30);
            }
            for (int i17 = 0; i17 < 4; i17++) {
                int g = i7 + ((i3 << 5) | (i3 >>> 27)) + g(i4, i5, i6) + this.X[i8] + Y3;
                int i18 = (i4 >>> 2) | (i4 << 30);
                int g2 = i6 + ((g << 5) | (g >>> 27)) + g(i3, i18, i5) + this.X[i8 + 1] + Y3;
                int i19 = (i3 >>> 2) | (i3 << 30);
                int g3 = i5 + ((g2 << 5) | (g2 >>> 27)) + g(g, i19, i18) + this.X[i8 + 2] + Y3;
                i7 = (g >>> 2) | (g << 30);
                int i20 = i8 + 4;
                i4 = i18 + ((g3 << 5) | (g3 >>> 27)) + g(g2, i7, i19) + this.X[i8 + 3] + Y3;
                i6 = (g2 >>> 2) | (g2 << 30);
                i8 += 5;
                i3 = i19 + ((i4 << 5) | (i4 >>> 27)) + g(g3, i6, i7) + this.X[i20] + Y3;
                i5 = (g3 >>> 2) | (g3 << 30);
            }
            for (int i21 = 0; i21 <= 3; i21++) {
                int h4 = i7 + ((i3 << 5) | (i3 >>> 27)) + h(i4, i5, i6) + this.X[i8] + Y4;
                int i22 = (i4 >>> 2) | (i4 << 30);
                int h5 = i6 + ((h4 << 5) | (h4 >>> 27)) + h(i3, i22, i5) + this.X[i8 + 1] + Y4;
                int i23 = (i3 >>> 2) | (i3 << 30);
                int h6 = i5 + ((h5 << 5) | (h5 >>> 27)) + h(h4, i23, i22) + this.X[i8 + 2] + Y4;
                i7 = (h4 >>> 2) | (h4 << 30);
                int i24 = i8 + 4;
                i4 = i22 + ((h6 << 5) | (h6 >>> 27)) + h(h5, i7, i23) + this.X[i8 + 3] + Y4;
                i6 = (h5 >>> 2) | (h5 << 30);
                i8 += 5;
                i3 = i23 + ((i4 << 5) | (i4 >>> 27)) + h(h6, i6, i7) + this.X[i24] + Y4;
                i5 = (h6 >>> 2) | (h6 << 30);
            }
            this.H1 += i3;
            this.H2 += i4;
            this.H3 += i5;
            this.H4 += i6;
            this.H5 += i7;
            this.xOff = 0;
            for (int i25 = 0; i25 < 16; i25++) {
                this.X[i25] = 0;
            }
        }

        /* access modifiers changed from: protected */
        public void processLength(long j) {
            if (this.xOff > 14) {
                processBlock();
            }
            int[] iArr = this.X;
            iArr[14] = (int) (j >>> 32);
            iArr[15] = (int) j;
        }

        /* access modifiers changed from: protected */
        public void processWord(byte[] bArr, int i) {
            int i2 = (bArr[i + 3] & 255) | (bArr[i] << Ascii.CAN) | ((bArr[i + 1] & 255) << Ascii.DLE) | ((bArr[i + 2] & 255) << 8);
            int[] iArr = this.X;
            int i3 = this.xOff;
            iArr[i3] = i2;
            int i4 = i3 + 1;
            this.xOff = i4;
            if (i4 == 16) {
                processBlock();
            }
        }

        public void reset() {
            super.reset();
            this.H1 = 1732584193;
            this.H2 = -271733879;
            this.H3 = -1732584194;
            this.H4 = 271733878;
            this.H5 = -1009589776;
            this.xOff = 0;
            int i = 0;
            while (true) {
                int[] iArr = this.X;
                if (i != iArr.length) {
                    iArr[i] = 0;
                    i++;
                } else {
                    return;
                }
            }
        }
    }

    MSOutlookKeyIdCalculator() {
    }

    static byte[] calculateKeyId(SubjectPublicKeyInfo subjectPublicKeyInfo) {
        SHA1Digest sHA1Digest = new SHA1Digest();
        byte[] bArr = new byte[sHA1Digest.getDigestSize()];
        try {
            byte[] encoded = subjectPublicKeyInfo.getEncoded(ASN1Encoding.DER);
            sHA1Digest.update(encoded, 0, encoded.length);
            sHA1Digest.doFinal(bArr, 0);
            return bArr;
        } catch (IOException unused) {
            return new byte[0];
        }
    }
}
