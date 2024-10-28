package org.bouncycastle.pqc.crypto.crystals.kyber;

import org.bouncycastle.util.Arrays;

class PolyVec {
    private KyberEngine engine;
    private int kyberK;
    private int polyVecBytes;
    Poly[] vec;

    public PolyVec() throws Exception {
        throw new Exception("Requires Parameter");
    }

    public PolyVec(KyberEngine kyberEngine) {
        this.engine = kyberEngine;
        this.kyberK = kyberEngine.getKyberK();
        this.polyVecBytes = kyberEngine.getKyberPolyVecBytes();
        this.vec = new Poly[this.kyberK];
        for (int i = 0; i < this.kyberK; i++) {
            this.vec[i] = new Poly(kyberEngine);
        }
    }

    public static void pointwiseAccountMontgomery(Poly poly, PolyVec polyVec, PolyVec polyVec2, KyberEngine kyberEngine) {
        Poly poly2 = new Poly(kyberEngine);
        Poly.baseMultMontgomery(poly, polyVec.getVectorIndex(0), polyVec2.getVectorIndex(0));
        for (int i = 1; i < kyberEngine.getKyberK(); i++) {
            Poly.baseMultMontgomery(poly2, polyVec.getVectorIndex(i), polyVec2.getVectorIndex(i));
            poly.addCoeffs(poly2);
        }
        poly.reduce();
    }

    public void addPoly(PolyVec polyVec) {
        for (int i = 0; i < this.kyberK; i++) {
            getVectorIndex(i).addCoeffs(polyVec.getVectorIndex(i));
        }
    }

    public byte[] compressPolyVec() {
        conditionalSubQ();
        byte[] bArr = new byte[this.engine.getKyberPolyVecCompressedBytes()];
        short s = 8;
        if (this.engine.getKyberPolyVecCompressedBytes() == this.kyberK * DilithiumEngine.DilithiumPolyT1PackedBytes) {
            short[] sArr = new short[4];
            int i = 0;
            for (int i2 = 0; i2 < this.kyberK; i2++) {
                for (int i3 = 0; i3 < 64; i3++) {
                    for (int i4 = 0; i4 < 4; i4++) {
                        sArr[i4] = (short) ((((getVectorIndex(i2).getCoeffIndex((i3 * 4) + i4) << 10) + 1664) / KyberEngine.KyberQ) & 1023);
                    }
                    short s2 = sArr[0];
                    bArr[i] = (byte) s2;
                    short s3 = sArr[1];
                    bArr[i + 1] = (byte) ((s2 >> 8) | (s3 << 2));
                    int i5 = s3 >> 6;
                    short s4 = sArr[2];
                    bArr[i + 2] = (byte) (i5 | (s4 << 4));
                    int i6 = s4 >> 4;
                    short s5 = sArr[3];
                    bArr[i + 3] = (byte) (i6 | (s5 << 6));
                    bArr[i + 4] = (byte) (s5 >> 2);
                    i += 5;
                }
            }
        } else if (this.engine.getKyberPolyVecCompressedBytes() == this.kyberK * 352) {
            short[] sArr2 = new short[8];
            int i7 = 0;
            int i8 = 0;
            while (i7 < this.kyberK) {
                int i9 = 0;
                while (i9 < 32) {
                    for (int i10 = 0; i10 < s; i10++) {
                        sArr2[i10] = (short) ((((getVectorIndex(i7).getCoeffIndex((i9 * 8) + i10) << 11) + 1664) / KyberEngine.KyberQ) & 2047);
                    }
                    short s6 = sArr2[0];
                    bArr[i8] = (byte) s6;
                    short s7 = sArr2[1];
                    bArr[i8 + 1] = (byte) ((s6 >> s) | (s7 << 3));
                    short s8 = sArr2[2];
                    bArr[i8 + 2] = (byte) ((s7 >> 5) | (s8 << 6));
                    bArr[i8 + 3] = (byte) (s8 >> 2);
                    int i11 = s8 >> 10;
                    short s9 = sArr2[3];
                    bArr[i8 + 4] = (byte) (i11 | (s9 << 1));
                    short s10 = sArr2[4];
                    bArr[i8 + 5] = (byte) ((s9 >> 7) | (s10 << 4));
                    short s11 = sArr2[5];
                    bArr[i8 + 6] = (byte) ((s10 >> 4) | (s11 << 7));
                    bArr[i8 + 7] = (byte) (s11 >> 1);
                    int i12 = s11 >> 9;
                    short s12 = sArr2[6];
                    bArr[i8 + 8] = (byte) (i12 | (s12 << 2));
                    int i13 = s12 >> 6;
                    short s13 = sArr2[7];
                    bArr[i8 + 9] = (byte) (i13 | (s13 << 5));
                    bArr[i8 + 10] = (byte) (s13 >> 3);
                    i8 += 11;
                    i9++;
                    s = 8;
                }
                i7++;
                s = 8;
            }
        } else {
            throw new RuntimeException("Kyber PolyVecCompressedBytes neither 320 * KyberK or 352 * KyberK!");
        }
        return bArr;
    }

    public void conditionalSubQ() {
        for (int i = 0; i < this.kyberK; i++) {
            getVectorIndex(i).conditionalSubQ();
        }
    }

    public void decompressPolyVec(byte[] bArr) {
        byte b = 3;
        byte b2 = 6;
        short s = 8;
        byte b3 = 2;
        byte b4 = 4;
        if (this.engine.getKyberPolyVecCompressedBytes() == this.kyberK * DilithiumEngine.DilithiumPolyT1PackedBytes) {
            int i = 0;
            for (int i2 = 0; i2 < this.kyberK; i2++) {
                for (int i3 = 0; i3 < 64; i3++) {
                    byte b5 = bArr[i + 1];
                    byte b6 = bArr[i + 2];
                    byte b7 = bArr[i + 3];
                    short[] sArr = {(short) ((bArr[i] & 255) | ((short) ((b5 & 255) << 8))), (short) (((b5 & 255) >> 2) | ((short) ((b6 & 255) << 6))), (short) (((b6 & 255) >> 4) | ((short) ((b7 & 255) << 4))), (short) (((b7 & 255) >> 6) | ((short) ((bArr[i + 4] & 255) << 2)))};
                    i += 5;
                    for (int i4 = 0; i4 < 4; i4++) {
                        this.vec[i2].setCoeffIndex((i3 * 4) + i4, (short) ((((sArr[i4] & 1023) * 3329) + 512) >> 10));
                    }
                }
            }
        } else if (this.engine.getKyberPolyVecCompressedBytes() == this.kyberK * 352) {
            int i5 = 0;
            int i6 = 0;
            while (i5 < this.kyberK) {
                int i7 = 0;
                while (i7 < 32) {
                    byte b8 = bArr[i6 + 1];
                    byte b9 = bArr[i6 + 2];
                    short s2 = ((b9 & 255) >> b2) | (((short) (bArr[i6 + 3] & 255)) << b3);
                    byte b10 = bArr[i6 + 4];
                    byte b11 = bArr[i6 + 5];
                    int i8 = ((short) (b11 & 255)) << 7;
                    byte b12 = bArr[i6 + 6];
                    int i9 = ((short) (b12 & 255)) << b4;
                    short s3 = ((b12 & 255) >> 7) | (((short) (bArr[i6 + 7] & 255)) << 1);
                    byte b13 = bArr[i6 + 8];
                    int i10 = (b13 & 255) >> b3;
                    byte b14 = bArr[i6 + 9];
                    short[] sArr2 = {(short) ((bArr[i6] & 255) | (((short) (b8 & 255)) << s)), (short) (((b8 & 255) >> b) | (((short) (b9 & 255)) << 5)), (short) (((short) ((b10 & 255) << 10)) | s2), (short) (i8 | ((b10 & 255) >> 1)), (short) (i9 | ((b11 & 255) >> b4)), (short) (((short) ((b13 & 255) << 9)) | s3), (short) ((((short) (b14 & 255)) << b2) | i10), (short) (((b14 & 255) >> 5) | (((short) (bArr[i6 + 10] & 255)) << 3))};
                    i6 += 11;
                    for (int i11 = 0; i11 < 8; i11++) {
                        this.vec[i5].setCoeffIndex((i7 * 8) + i11, (short) ((((sArr2[i11] & 2047) * 3329) + 1024) >> 11));
                    }
                    i7++;
                    b = 3;
                    b2 = 6;
                    s = 8;
                    b3 = 2;
                    b4 = 4;
                }
                i5++;
                b = 3;
                b2 = 6;
                s = 8;
                b3 = 2;
                b4 = 4;
            }
        } else {
            throw new RuntimeException("Kyber PolyVecCompressedBytes neither 320 * KyberK or 352 * KyberK!");
        }
    }

    public void fromBytes(byte[] bArr) {
        int i = 0;
        while (i < this.kyberK) {
            Poly vectorIndex = getVectorIndex(i);
            int i2 = i * KyberEngine.KyberPolyBytes;
            i++;
            vectorIndex.fromBytes(Arrays.copyOfRange(bArr, i2, i * KyberEngine.KyberPolyBytes));
        }
    }

    public Poly getVectorIndex(int i) {
        return this.vec[i];
    }

    public void polyVecInverseNttToMont() {
        for (int i = 0; i < this.kyberK; i++) {
            getVectorIndex(i).polyInverseNttToMont();
        }
    }

    public void polyVecNtt() {
        for (int i = 0; i < this.kyberK; i++) {
            getVectorIndex(i).polyNtt();
        }
    }

    public void reducePoly() {
        for (int i = 0; i < this.kyberK; i++) {
            getVectorIndex(i).reduce();
        }
    }

    public byte[] toBytes() {
        byte[] bArr = new byte[this.polyVecBytes];
        for (int i = 0; i < this.kyberK; i++) {
            System.arraycopy(this.vec[i].toBytes(), 0, bArr, i * KyberEngine.KyberPolyBytes, KyberEngine.KyberPolyBytes);
        }
        return bArr;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("[");
        for (int i = 0; i < this.kyberK; i++) {
            stringBuffer.append(this.vec[i].toString());
            if (i != this.kyberK - 1) {
                stringBuffer.append(", ");
            }
        }
        stringBuffer.append("]");
        return stringBuffer.toString();
    }
}
