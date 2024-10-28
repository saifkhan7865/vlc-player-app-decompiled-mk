package org.bouncycastle.pqc.crypto.crystals.kyber;

import com.google.common.base.Ascii;

class Poly {
    private short[] coeffs = new short[256];
    private KyberEngine engine;
    private int eta1;
    private int eta2;
    private int polyCompressedBytes;
    private Symmetric symmetric;

    public Poly(KyberEngine kyberEngine) {
        this.engine = kyberEngine;
        this.polyCompressedBytes = kyberEngine.getKyberPolyCompressedBytes();
        this.eta1 = kyberEngine.getKyberEta1();
        this.eta2 = KyberEngine.getKyberEta2();
        this.symmetric = kyberEngine.getSymmetric();
    }

    public static void baseMultMontgomery(Poly poly, Poly poly2, Poly poly3) {
        Poly poly4 = poly2;
        Poly poly5 = poly3;
        for (int i = 0; i < 64; i++) {
            int i2 = i * 4;
            int i3 = i2 + 1;
            int i4 = i + 64;
            Ntt.baseMult(poly, i2, poly4.getCoeffIndex(i2), poly4.getCoeffIndex(i3), poly5.getCoeffIndex(i2), poly5.getCoeffIndex(i3), Ntt.nttZetas[i4]);
            int i5 = i2 + 2;
            int i6 = i2 + 3;
            Ntt.baseMult(poly, i5, poly4.getCoeffIndex(i5), poly4.getCoeffIndex(i6), poly5.getCoeffIndex(i5), poly5.getCoeffIndex(i6), (short) (Ntt.nttZetas[i4] * -1));
        }
    }

    public void addCoeffs(Poly poly) {
        for (int i = 0; i < 256; i++) {
            setCoeffIndex(i, (short) (getCoeffIndex(i) + poly.getCoeffIndex(i)));
        }
    }

    public byte[] compressPoly() {
        int i = 8;
        byte[] bArr = new byte[8];
        byte[] bArr2 = new byte[this.polyCompressedBytes];
        conditionalSubQ();
        int i2 = this.polyCompressedBytes;
        if (i2 == 128) {
            int i3 = 0;
            int i4 = 0;
            while (i3 < 32) {
                int i5 = 0;
                while (i5 < i) {
                    bArr[i5] = (byte) ((((getCoeffIndex((i3 * 8) + i5) << 4) + 1664) / KyberEngine.KyberQ) & 15);
                    i5++;
                    i = 8;
                }
                bArr2[i4] = (byte) (bArr[0] | (bArr[1] << 4));
                bArr2[i4 + 1] = (byte) (bArr[2] | (bArr[3] << 4));
                bArr2[i4 + 2] = (byte) (bArr[4] | (bArr[5] << 4));
                bArr2[i4 + 3] = (byte) (bArr[6] | (bArr[7] << 4));
                i4 += 4;
                i3++;
                i = 8;
            }
        } else if (i2 == 160) {
            int i6 = 0;
            int i7 = 0;
            for (int i8 = 32; i6 < i8; i8 = 32) {
                for (int i9 = 0; i9 < 8; i9++) {
                    bArr[i9] = (byte) ((((getCoeffIndex((i6 * 8) + i9) << 5) + 1664) / KyberEngine.KyberQ) & 31);
                }
                bArr2[i7] = (byte) (bArr[0] | (bArr[1] << 5));
                bArr2[i7 + 1] = (byte) ((bArr[1] >> 3) | (bArr[2] << 2) | (bArr[3] << 7));
                bArr2[i7 + 2] = (byte) ((bArr[3] >> 1) | (bArr[4] << 4));
                bArr2[i7 + 3] = (byte) ((bArr[4] >> 4) | (bArr[5] << 1) | (bArr[6] << 6));
                bArr2[i7 + 4] = (byte) ((bArr[6] >> 2) | (bArr[7] << 3));
                i7 += 5;
                i6++;
            }
        } else {
            throw new RuntimeException("PolyCompressedBytes is neither 128 or 160!");
        }
        return bArr2;
    }

    public void conditionalSubQ() {
        for (int i = 0; i < 256; i++) {
            setCoeffIndex(i, Reduce.conditionalSubQ(getCoeffIndex(i)));
        }
    }

    public void convertToMont() {
        for (int i = 0; i < 256; i++) {
            setCoeffIndex(i, Reduce.montgomeryReduce(getCoeffIndex(i) * 1353));
        }
    }

    public void decompressPoly(byte[] bArr) {
        byte b = 4;
        byte b2 = 1;
        if (this.engine.getKyberPolyCompressedBytes() == 128) {
            int i = 0;
            for (int i2 = 0; i2 < 128; i2++) {
                int i3 = i2 * 2;
                setCoeffIndex(i3, (short) (((((short) (bArr[i] & Ascii.SI)) * 3329) + 8) >> 4));
                setCoeffIndex(i3 + 1, (short) (((((short) ((bArr[i] & 255) >> 4)) * 3329) + 8) >> 4));
                i++;
            }
        } else if (this.engine.getKyberPolyCompressedBytes() == 160) {
            int i4 = 0;
            int i5 = 0;
            while (i4 < 32) {
                byte b3 = bArr[i5];
                byte b4 = bArr[i5 + 1];
                byte b5 = bArr[i5 + 2];
                byte b6 = bArr[i5 + 3];
                int i6 = (b6 & 255) << b;
                byte b7 = bArr[i5 + 4];
                byte[] bArr2 = {(byte) (b3 & 255), (byte) (((b3 & 255) >> 5) | ((b4 & 255) << 3)), (byte) ((b4 & 255) >> 2), (byte) (((b4 & 255) >> 7) | ((b5 & 255) << b2)), (byte) (i6 | ((b5 & 255) >> b)), (byte) ((b6 & 255) >> b2), (byte) (((b7 & 255) << 2) | ((b6 & 255) >> 6)), (byte) ((b7 & 255) >> 3)};
                i5 += 5;
                for (int i7 = 0; i7 < 8; i7++) {
                    setCoeffIndex((i4 * 8) + i7, (short) ((((bArr2[i7] & Ascii.US) * 3329) + 16) >> 5));
                }
                i4++;
                b = 4;
                b2 = 1;
            }
        } else {
            throw new RuntimeException("PolyCompressedBytes is neither 128 or 160!");
        }
    }

    public void fromBytes(byte[] bArr) {
        for (int i = 0; i < 128; i++) {
            int i2 = i * 2;
            int i3 = i * 3;
            int i4 = i3 + 1;
            setCoeffIndex(i2, (short) (((bArr[i3] & 255) | ((bArr[i4] & 255) << 8)) & 4095));
            setCoeffIndex(i2 + 1, (short) ((int) ((((long) ((bArr[i4] & 255) >> 4)) | ((long) ((bArr[i3 + 2] & 255) << 4))) & 4095)));
        }
    }

    public void fromMsg(byte[] bArr) {
        if (bArr.length == 32) {
            for (int i = 0; i < 32; i++) {
                for (int i2 = 0; i2 < 8; i2++) {
                    setCoeffIndex((i * 8) + i2, (short) (((short) (((short) (((bArr[i] & 255) >> i2) & 1)) * -1)) & 1665));
                }
            }
            return;
        }
        throw new RuntimeException("KYBER_INDCPA_MSGBYTES must be equal to KYBER_N/8 bytes!");
    }

    public short getCoeffIndex(int i) {
        return this.coeffs[i];
    }

    public short[] getCoeffs() {
        return this.coeffs;
    }

    public void getEta1Noise(byte[] bArr, byte b) {
        byte[] bArr2 = new byte[((this.eta1 * 256) / 4)];
        this.symmetric.prf(bArr2, bArr, b);
        CBD.kyberCBD(this, bArr2, this.eta1);
    }

    public void getEta2Noise(byte[] bArr, byte b) {
        byte[] bArr2 = new byte[((this.eta2 * 256) / 4)];
        this.symmetric.prf(bArr2, bArr, b);
        CBD.kyberCBD(this, bArr2, this.eta2);
    }

    public void polyInverseNttToMont() {
        setCoeffs(Ntt.invNtt(getCoeffs()));
    }

    public void polyNtt() {
        setCoeffs(Ntt.ntt(getCoeffs()));
        reduce();
    }

    public void polySubtract(Poly poly) {
        for (int i = 0; i < 256; i++) {
            setCoeffIndex(i, (short) (poly.getCoeffIndex(i) - getCoeffIndex(i)));
        }
    }

    public void reduce() {
        for (int i = 0; i < 256; i++) {
            setCoeffIndex(i, Reduce.barretReduce(getCoeffIndex(i)));
        }
    }

    public void setCoeffIndex(int i, short s) {
        this.coeffs[i] = s;
    }

    public void setCoeffs(short[] sArr) {
        this.coeffs = sArr;
    }

    public byte[] toBytes() {
        byte[] bArr = new byte[KyberEngine.KyberPolyBytes];
        conditionalSubQ();
        for (int i = 0; i < 128; i++) {
            int i2 = i * 2;
            short coeffIndex = getCoeffIndex(i2);
            short coeffIndex2 = getCoeffIndex(i2 + 1);
            int i3 = i * 3;
            bArr[i3] = (byte) coeffIndex;
            bArr[i3 + 1] = (byte) ((coeffIndex >> 8) | (coeffIndex2 << 4));
            bArr[i3 + 2] = (byte) (coeffIndex2 >> 4);
        }
        return bArr;
    }

    public byte[] toMsg() {
        byte[] bArr = new byte[KyberEngine.getKyberIndCpaMsgBytes()];
        conditionalSubQ();
        for (int i = 0; i < 32; i++) {
            bArr[i] = 0;
            for (int i2 = 0; i2 < 8; i2++) {
                bArr[i] = (byte) (((byte) (((short) (((((short) (getCoeffIndex((i * 8) + i2) << 1)) + 1664) / KyberEngine.KyberQ) & 1)) << i2)) | bArr[i]);
            }
        }
        return bArr;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("[");
        int i = 0;
        while (true) {
            short[] sArr = this.coeffs;
            if (i < sArr.length) {
                stringBuffer.append(sArr[i]);
                if (i != this.coeffs.length - 1) {
                    stringBuffer.append(", ");
                }
                i++;
            } else {
                stringBuffer.append("]");
                return stringBuffer.toString();
            }
        }
    }
}
