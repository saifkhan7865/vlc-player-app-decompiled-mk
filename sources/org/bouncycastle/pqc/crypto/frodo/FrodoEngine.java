package org.bouncycastle.pqc.crypto.frodo;

import io.netty.handler.codec.http2.Http2CodecUtil;
import java.security.SecureRandom;
import org.bouncycastle.crypto.Xof;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;

class FrodoEngine {
    private static final int len_chi = 16;
    private static final int len_chi_bytes = 2;
    private static final int len_seedA = 128;
    private static final int len_seedA_bytes = 16;
    private static final int len_z = 128;
    private static final int len_z_bytes = 16;
    private static final int mbar = 8;
    static final int nbar = 8;
    private final int B;
    private final int D;
    private final short[] T_chi;
    private final Xof digest;
    private final FrodoMatrixGenerator gen;
    private final int len_ct_bytes;
    private final int len_k;
    private final int len_k_bytes;
    private final int len_mu;
    private final int len_mu_bytes;
    private final int len_pk_bytes;
    private final int len_pkh;
    private final int len_pkh_bytes;
    private final int len_s;
    private final int len_s_bytes;
    private final int len_seedSE;
    private final int len_seedSE_bytes;
    private final int len_sk_bytes;
    private final int len_ss;
    private final int len_ss_bytes;
    private final int n;
    private final int q;

    public FrodoEngine(int i, int i2, int i3, short[] sArr, Xof xof, FrodoMatrixGenerator frodoMatrixGenerator) {
        this.n = i;
        this.D = i2;
        this.q = 1 << i2;
        this.B = i3;
        int i4 = i3 * 64;
        this.len_mu = i4;
        this.len_seedSE = i4;
        this.len_s = i4;
        this.len_k = i4;
        this.len_pkh = i4;
        this.len_ss = i4;
        this.len_mu_bytes = i4 / 8;
        this.len_seedSE_bytes = i4 / 8;
        int i5 = i4 / 8;
        this.len_s_bytes = i5;
        this.len_k_bytes = i4 / 8;
        int i6 = i4 / 8;
        this.len_pkh_bytes = i6;
        this.len_ss_bytes = i4 / 8;
        int i7 = ((i2 * i) * 8) / 8;
        this.len_ct_bytes = ((i2 * 64) / 8) + i7;
        int i8 = i7 + 16;
        this.len_pk_bytes = i8;
        this.len_sk_bytes = i5 + i8 + (i * 16) + i6;
        this.T_chi = sArr;
        this.digest = xof;
        this.gen = frodoMatrixGenerator;
    }

    private byte[] ctselect(byte[] bArr, byte[] bArr2, short s) {
        byte[] bArr3 = new byte[bArr.length];
        for (int i = 0; i < bArr.length; i++) {
            bArr3[i] = (byte) (((s ^ -1) & bArr[i] & 255) | (bArr2[i] & s & 255));
        }
        return bArr3;
    }

    private short ctverify(short[] sArr, short[] sArr2, short[] sArr3, short[] sArr4) {
        short s = 0;
        for (short s2 = 0; s2 < sArr.length; s2 = (short) (s2 + 1)) {
            s = (short) (s | (sArr[s2] ^ sArr3[s2]));
        }
        for (short s3 = 0; s3 < sArr2.length; s3 = (short) (s3 + 1)) {
            s = (short) ((sArr2[s3] ^ sArr4[s3]) | s);
        }
        return s == 0 ? (short) 0 : -1;
    }

    private byte[] decode(short[] sArr) {
        int i = this.B;
        short s = (short) ((1 << i) - 1);
        short s2 = (short) ((1 << this.D) - 1);
        byte[] bArr = new byte[(i * 8)];
        int i2 = 0;
        for (int i3 = 0; i3 < 8; i3++) {
            long j = 0;
            for (int i4 = 0; i4 < 8; i4++) {
                int i5 = this.D;
                int i6 = this.B;
                j |= ((long) (((short) (((sArr[i2] & s2) + (1 << ((i5 - i6) - 1))) >> (i5 - i6))) & s)) << (i6 * i4);
                i2++;
            }
            int i7 = 0;
            while (true) {
                int i8 = this.B;
                if (i7 >= i8) {
                    break;
                }
                bArr[(i8 * i3) + i7] = (byte) ((int) ((j >> (i7 * 8)) & 255));
                i7++;
            }
        }
        return bArr;
    }

    private short[] encode(byte[] bArr) {
        int i;
        short[] sArr = new short[64];
        int i2 = 0;
        byte b = 1;
        for (int i3 = 0; i3 < 8; i3++) {
            for (int i4 = 0; i4 < 8; i4++) {
                int i5 = 0;
                int i6 = 0;
                while (true) {
                    i = this.B;
                    if (i5 >= i) {
                        break;
                    }
                    i6 += (1 << i5) * ((bArr[i2] & b) == b ? 1 : 0);
                    b = (byte) (b << 1);
                    if (b == 0) {
                        i2++;
                        b = 1;
                    }
                    i5++;
                }
                sArr[(i3 * 8) + i4] = (short) (i6 * (this.q / (1 << i)));
            }
        }
        return sArr;
    }

    private short[] matrix_add(short[] sArr, short[] sArr2, int i, int i2) {
        int i3 = this.q - 1;
        short[] sArr3 = new short[(i * i2)];
        for (int i4 = 0; i4 < i; i4++) {
            for (int i5 = 0; i5 < i2; i5++) {
                int i6 = (i4 * i2) + i5;
                sArr3[i6] = (short) ((sArr[i6] + sArr2[i6]) & i3);
            }
        }
        return sArr3;
    }

    private short[] matrix_mul(short[] sArr, int i, int i2, short[] sArr2, int i3, int i4) {
        int i5 = this.q - 1;
        short[] sArr3 = new short[(i * i4)];
        for (int i6 = 0; i6 < i; i6++) {
            for (int i7 = 0; i7 < i4; i7++) {
                int i8 = 0;
                for (int i9 = 0; i9 < i2; i9++) {
                    i8 += sArr[(i6 * i2) + i9] * sArr2[(i9 * i4) + i7];
                }
                sArr3[(i6 * i4) + i7] = (short) (i8 & i5);
            }
        }
        return sArr3;
    }

    private short[] matrix_sub(short[] sArr, short[] sArr2, int i, int i2) {
        int i3 = this.q - 1;
        short[] sArr3 = new short[(i * i2)];
        for (int i4 = 0; i4 < i; i4++) {
            for (int i5 = 0; i5 < i2; i5++) {
                int i6 = (i4 * i2) + i5;
                sArr3[i6] = (short) ((sArr[i6] - sArr2[i6]) & i3);
            }
        }
        return sArr3;
    }

    private short[] matrix_transpose(short[] sArr, int i, int i2) {
        short[] sArr2 = new short[(i * i2)];
        for (int i3 = 0; i3 < i2; i3++) {
            for (int i4 = 0; i4 < i; i4++) {
                sArr2[(i3 * i) + i4] = sArr[(i4 * i2) + i3];
            }
        }
        return sArr2;
    }

    private byte[] pack(short[] sArr) {
        short[] sArr2 = sArr;
        int length = sArr2.length;
        int i = (this.D * length) / 8;
        byte[] bArr = new byte[i];
        short s = 0;
        short s2 = 0;
        byte b = 0;
        short s3 = 0;
        while (s < i && (s2 < length || (s2 == length && b > 0))) {
            byte b2 = 0;
            while (b2 < 8) {
                int i2 = 8 - b2;
                int min = Math.min(i2, b);
                int i3 = b - min;
                bArr[s] = (byte) (bArr[s] + (((byte) (((short) ((1 << min) - 1)) & (s3 >> i3))) << (i2 - min)));
                b2 = (byte) (b2 + min);
                b = (byte) i3;
                if (b == 0) {
                    if (s2 >= length) {
                        break;
                    }
                    short s4 = sArr2[s2];
                    s2 = (short) (s2 + 1);
                    s3 = s4;
                    b = (byte) this.D;
                }
            }
            if (b2 == 8) {
                s = (short) (s + 1);
            }
        }
        return bArr;
    }

    private short sample(short s) {
        short s2 = s & 65535;
        short s3 = (short) (s2 >>> 1);
        int i = 0;
        short s4 = 0;
        while (true) {
            short[] sArr = this.T_chi;
            if (i >= sArr.length) {
                break;
            }
            if (s3 > sArr[i]) {
                s4 = (short) (s4 + 1);
            }
            i++;
        }
        return s2 % 2 == 1 ? (short) ((s4 * -1) & 65535) : s4;
    }

    private short[] sample_matrix(short[] sArr, int i, int i2, int i3) {
        short[] sArr2 = new short[(i2 * i3)];
        for (int i4 = 0; i4 < i2; i4++) {
            for (int i5 = 0; i5 < i3; i5++) {
                int i6 = (i4 * i3) + i5;
                sArr2[i6] = sample(sArr[i6 + i]);
            }
        }
        return sArr2;
    }

    private short[] unpack(byte[] bArr, int i, int i2) {
        byte[] bArr2 = bArr;
        int i3 = i * i2;
        short[] sArr = new short[i3];
        short s = 0;
        short s2 = 0;
        byte b = 0;
        byte b2 = 0;
        while (s < i3 && (s2 < bArr2.length || (s2 == bArr2.length && b > 0))) {
            byte b3 = 0;
            while (true) {
                int i4 = this.D;
                if (b3 >= i4) {
                    break;
                }
                int min = Math.min(i4 - b3, b);
                short s3 = (short) (((1 << min) - 1) & 65535);
                sArr[s] = (short) (((sArr[s] & 65535) + ((((byte) ((((b2 & 255) >>> ((b & 255) - min)) & (s3 & 65535)) & Http2CodecUtil.MAX_UNSIGNED_BYTE)) & 255) << ((this.D - (b3 & 255)) - min))) & 65535);
                b3 = (byte) (b3 + min);
                byte b4 = (byte) (b - min);
                byte b5 = (byte) (((s3 << b4) ^ -1) & b2);
                if (b4 == 0) {
                    if (s2 >= bArr2.length) {
                        b2 = b5;
                        b = b4;
                        break;
                    }
                    byte b6 = bArr2[s2];
                    s2 = (short) (s2 + 1);
                    b = 8;
                    b2 = b6;
                } else {
                    b2 = b5;
                    b = b4;
                }
            }
            if (b3 == this.D) {
                s = (short) (s + 1);
            }
        }
        return sArr;
    }

    public int getCipherTextSize() {
        return this.len_ct_bytes;
    }

    public int getPrivateKeySize() {
        return this.len_sk_bytes;
    }

    public int getPublicKeySize() {
        return this.len_pk_bytes;
    }

    public int getSessionKeySize() {
        return this.len_ss_bytes;
    }

    public void kem_dec(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        byte[] bArr4 = bArr2;
        byte[] bArr5 = bArr3;
        int i = ((this.n * 8) * this.D) / 8;
        byte[] copyOfRange = Arrays.copyOfRange(bArr4, 0, i);
        byte[] copyOfRange2 = Arrays.copyOfRange(bArr4, i, ((this.D * 64) / 8) + i);
        int i2 = this.len_s_bytes;
        byte[] copyOfRange3 = Arrays.copyOfRange(bArr5, 0, i2);
        int i3 = i2 + 16;
        byte[] copyOfRange4 = Arrays.copyOfRange(bArr5, i2, i3);
        int i4 = (((this.D * this.n) * 8) / 8) + i3;
        byte[] copyOfRange5 = Arrays.copyOfRange(bArr5, i3, i4);
        int i5 = ((this.n * 128) / 8) + i4;
        byte[] copyOfRange6 = Arrays.copyOfRange(bArr5, i4, i5);
        short[] sArr = new short[(this.n * 8)];
        for (int i6 = 0; i6 < 8; i6++) {
            int i7 = 0;
            while (true) {
                int i8 = this.n;
                if (i7 >= i8) {
                    break;
                }
                sArr[(i6 * i8) + i7] = Pack.littleEndianToShort(copyOfRange6, (i8 * i6 * 2) + (i7 * 2));
                i7++;
            }
        }
        short[] matrix_transpose = matrix_transpose(sArr, 8, this.n);
        byte[] copyOfRange7 = Arrays.copyOfRange(bArr5, i5, this.len_pkh_bytes + i5);
        short[] unpack = unpack(copyOfRange, 8, this.n);
        short[] unpack2 = unpack(copyOfRange2, 8, 8);
        int i9 = this.n;
        short[] sArr2 = unpack2;
        short[] sArr3 = unpack;
        byte[] decode = decode(matrix_sub(sArr2, matrix_mul(unpack, 8, i9, matrix_transpose, i9, 8), 8, 8));
        byte[] bArr6 = new byte[(this.len_seedSE_bytes + this.len_k_bytes)];
        this.digest.update(copyOfRange7, 0, this.len_pkh_bytes);
        this.digest.update(decode, 0, this.len_mu_bytes);
        this.digest.doFinal(bArr6, 0, this.len_seedSE_bytes + this.len_k_bytes);
        int i10 = this.len_seedSE_bytes;
        byte[] copyOfRange8 = Arrays.copyOfRange(bArr6, i10, this.len_k_bytes + i10);
        int i11 = ((this.n * 16) + 64) * 2;
        byte[] bArr7 = new byte[i11];
        this.digest.update((byte) -106);
        this.digest.update(bArr6, 0, this.len_seedSE_bytes);
        this.digest.doFinal(bArr7, 0, i11);
        int i12 = (this.n * 16) + 64;
        short[] sArr4 = new short[i12];
        for (int i13 = 0; i13 < i12; i13++) {
            sArr4[i13] = Pack.littleEndianToShort(bArr7, i13 * 2);
        }
        short[] sample_matrix = sample_matrix(sArr4, 0, 8, this.n);
        int i14 = this.n;
        short[] sample_matrix2 = sample_matrix(sArr4, i14 * 8, 8, i14);
        short[] genMatrix = this.gen.genMatrix(copyOfRange4);
        int i15 = this.n;
        int i16 = i15;
        short[] sArr5 = sample_matrix2;
        short[] sArr6 = genMatrix;
        byte[] bArr8 = decode;
        short[] matrix_add = matrix_add(matrix_mul(sample_matrix, 8, i15, sArr6, i16, i16), sArr5, 8, this.n);
        short[] sample_matrix3 = sample_matrix(sArr4, this.n * 16, 8, 8);
        short[] unpack3 = unpack(copyOfRange5, this.n, 8);
        int i17 = this.n;
        byte[] ctselect = ctselect(copyOfRange8, copyOfRange3, ctverify(sArr3, sArr2, matrix_add, matrix_add(matrix_add(matrix_mul(sample_matrix, 8, i17, unpack3, i17, 8), sample_matrix3, 8, 8), encode(bArr8), 8, 8)));
        this.digest.update(copyOfRange, 0, copyOfRange.length);
        this.digest.update(copyOfRange2, 0, copyOfRange2.length);
        this.digest.update(ctselect, 0, ctselect.length);
        this.digest.doFinal(bArr, 0, this.len_ss_bytes);
    }

    public void kem_enc(byte[] bArr, byte[] bArr2, byte[] bArr3, SecureRandom secureRandom) {
        byte[] bArr4 = bArr3;
        byte[] copyOfRange = Arrays.copyOfRange(bArr4, 0, 16);
        byte[] copyOfRange2 = Arrays.copyOfRange(bArr4, 16, this.len_pk_bytes);
        byte[] bArr5 = new byte[this.len_mu_bytes];
        secureRandom.nextBytes(bArr5);
        byte[] bArr6 = new byte[this.len_pkh_bytes];
        this.digest.update(bArr4, 0, this.len_pk_bytes);
        this.digest.doFinal(bArr6, 0, this.len_pkh_bytes);
        byte[] bArr7 = new byte[(this.len_seedSE + this.len_k)];
        this.digest.update(bArr6, 0, this.len_pkh_bytes);
        this.digest.update(bArr5, 0, this.len_mu_bytes);
        this.digest.doFinal(bArr7, 0, this.len_seedSE_bytes + this.len_k_bytes);
        byte[] copyOfRange3 = Arrays.copyOfRange(bArr7, 0, this.len_seedSE_bytes);
        int i = this.len_seedSE_bytes;
        byte[] copyOfRange4 = Arrays.copyOfRange(bArr7, i, this.len_k_bytes + i);
        int i2 = ((this.n * 16) + 64) * 2;
        byte[] bArr8 = new byte[i2];
        this.digest.update((byte) -106);
        this.digest.update(copyOfRange3, 0, copyOfRange3.length);
        this.digest.doFinal(bArr8, 0, i2);
        int i3 = i2 / 2;
        short[] sArr = new short[i3];
        for (int i4 = 0; i4 < i3; i4++) {
            sArr[i4] = Pack.littleEndianToShort(bArr8, i4 * 2);
        }
        short[] sample_matrix = sample_matrix(sArr, 0, 8, this.n);
        int i5 = this.n;
        short[] sample_matrix2 = sample_matrix(sArr, i5 * 8, 8, i5);
        short[] genMatrix = this.gen.genMatrix(copyOfRange);
        int i6 = this.n;
        byte[] pack = pack(matrix_add(matrix_mul(sample_matrix, 8, i6, genMatrix, i6, i6), sample_matrix2, 8, this.n));
        short[] sample_matrix3 = sample_matrix(sArr, this.n * 16, 8, 8);
        short[] unpack = unpack(copyOfRange2, this.n, 8);
        int i7 = this.n;
        byte[] pack2 = pack(matrix_add(matrix_add(matrix_mul(sample_matrix, 8, i7, unpack, i7, 8), sample_matrix3, 8, 8), encode(bArr5), 8, 8));
        System.arraycopy(Arrays.concatenate(pack, pack2), 0, bArr, 0, this.len_ct_bytes);
        this.digest.update(pack, 0, pack.length);
        this.digest.update(pack2, 0, pack2.length);
        this.digest.update(copyOfRange4, 0, this.len_k_bytes);
        this.digest.doFinal(bArr2, 0, this.len_s_bytes);
    }

    public void kem_keypair(byte[] bArr, byte[] bArr2, SecureRandom secureRandom) {
        byte[] bArr3 = new byte[(this.len_s_bytes + this.len_seedSE_bytes + 16)];
        secureRandom.nextBytes(bArr3);
        byte[] copyOfRange = Arrays.copyOfRange(bArr3, 0, this.len_s_bytes);
        int i = this.len_s_bytes;
        byte[] copyOfRange2 = Arrays.copyOfRange(bArr3, i, this.len_seedSE_bytes + i);
        int i2 = this.len_s_bytes;
        int i3 = this.len_seedSE_bytes;
        byte[] copyOfRange3 = Arrays.copyOfRange(bArr3, i2 + i3, i2 + i3 + 16);
        byte[] bArr4 = new byte[16];
        this.digest.update(copyOfRange3, 0, copyOfRange3.length);
        this.digest.doFinal(bArr4, 0, 16);
        short[] genMatrix = this.gen.genMatrix(bArr4);
        int i4 = this.n * 32;
        byte[] bArr5 = new byte[i4];
        this.digest.update((byte) 95);
        this.digest.update(copyOfRange2, 0, copyOfRange2.length);
        this.digest.doFinal(bArr5, 0, i4);
        int i5 = this.n * 16;
        short[] sArr = new short[i5];
        for (int i6 = 0; i6 < i5; i6++) {
            sArr[i6] = Pack.littleEndianToShort(bArr5, i6 * 2);
        }
        short[] sample_matrix = sample_matrix(sArr, 0, 8, this.n);
        short[] matrix_transpose = matrix_transpose(sample_matrix, 8, this.n);
        int i7 = this.n;
        short[] sample_matrix2 = sample_matrix(sArr, i7 * 8, i7, 8);
        int i8 = this.n;
        System.arraycopy(Arrays.concatenate(bArr4, pack(matrix_add(matrix_mul(genMatrix, i8, i8, matrix_transpose, i8, 8), sample_matrix2, this.n, 8))), 0, bArr, 0, this.len_pk_bytes);
        int i9 = this.len_pkh_bytes;
        byte[] bArr6 = new byte[i9];
        this.digest.update(bArr, 0, bArr.length);
        this.digest.doFinal(bArr6, 0, i9);
        System.arraycopy(Arrays.concatenate(copyOfRange, bArr), 0, bArr2, 0, this.len_s_bytes + this.len_pk_bytes);
        for (int i10 = 0; i10 < 8; i10++) {
            int i11 = 0;
            while (true) {
                int i12 = this.n;
                if (i11 >= i12) {
                    break;
                }
                System.arraycopy(Pack.shortToLittleEndian(sample_matrix[(i12 * i10) + i11]), 0, bArr2, this.len_s_bytes + this.len_pk_bytes + (this.n * i10 * 2) + (i11 * 2), 2);
                i11++;
            }
        }
        int i13 = this.len_sk_bytes;
        int i14 = this.len_pkh_bytes;
        System.arraycopy(bArr6, 0, bArr2, i13 - i14, i14);
    }
}
