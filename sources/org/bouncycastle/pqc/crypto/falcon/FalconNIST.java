package org.bouncycastle.pqc.crypto.falcon;

import java.security.SecureRandom;
import org.bouncycastle.util.Arrays;

class FalconNIST {
    int CRYPTO_BYTES;
    private int CRYPTO_PUBLICKEYBYTES;
    private int CRYPTO_SECRETKEYBYTES;
    int LOGN;
    private int N;
    int NONCELEN;
    private FalconCodec codec = new FalconCodec();
    private SecureRandom rand;

    FalconNIST(int i, int i2, SecureRandom secureRandom) {
        int i3;
        int i4;
        this.rand = secureRandom;
        this.LOGN = i;
        this.NONCELEN = i2;
        int i5 = 1 << i;
        this.N = i5;
        this.CRYPTO_PUBLICKEYBYTES = ((i5 * 14) / 8) + 1;
        if (i == 10) {
            this.CRYPTO_SECRETKEYBYTES = 2305;
            this.CRYPTO_BYTES = 1330;
            return;
        }
        if (i == 9 || i == 8) {
            i4 = i5 * 12;
        } else if (i == 7 || i == 6) {
            i4 = i5 * 14;
        } else {
            i3 = i5 * 2;
            this.CRYPTO_SECRETKEYBYTES = i3 + 1 + i5;
            this.CRYPTO_BYTES = 690;
        }
        i3 = i4 / 8;
        this.CRYPTO_SECRETKEYBYTES = i3 + 1 + i5;
        this.CRYPTO_BYTES = 690;
    }

    /* access modifiers changed from: package-private */
    public byte[] crypto_sign(boolean z, byte[] bArr, byte[] bArr2, int i, int i2, byte[] bArr3, int i3) {
        int i4;
        int i5;
        int i6;
        byte[] bArr4 = bArr;
        int i7 = this.N;
        byte[] bArr5 = new byte[i7];
        byte[] bArr6 = new byte[i7];
        byte[] bArr7 = new byte[i7];
        byte[] bArr8 = new byte[i7];
        short[] sArr = new short[i7];
        short[] sArr2 = new short[i7];
        byte[] bArr9 = new byte[this.NONCELEN];
        SHAKE256 shake256 = new SHAKE256();
        FalconSign falconSign = new FalconSign();
        FalconVrfy falconVrfy = new FalconVrfy();
        FalconCommon falconCommon = new FalconCommon();
        FalconCodec falconCodec = this.codec;
        int i8 = this.LOGN;
        byte b = falconCodec.max_fg_bits[this.LOGN];
        FalconCommon falconCommon2 = falconCommon;
        int i9 = i8;
        SHAKE256 shake2562 = shake256;
        byte b2 = b;
        byte[] bArr10 = new byte[48];
        byte[] bArr11 = bArr9;
        int trim_i8_decode = falconCodec.trim_i8_decode(bArr5, 0, i9, b2, bArr3, i3, this.CRYPTO_SECRETKEYBYTES);
        if (trim_i8_decode != 0) {
            FalconCodec falconCodec2 = this.codec;
            byte[] bArr12 = bArr6;
            byte[] bArr13 = bArr3;
            short[] sArr3 = sArr2;
            int trim_i8_decode2 = falconCodec2.trim_i8_decode(bArr12, 0, this.LOGN, falconCodec2.max_fg_bits[this.LOGN], bArr13, i3 + trim_i8_decode, this.CRYPTO_SECRETKEYBYTES - trim_i8_decode);
            if (trim_i8_decode2 != 0) {
                int i10 = trim_i8_decode + trim_i8_decode2;
                FalconCodec falconCodec3 = this.codec;
                byte[] bArr14 = bArr7;
                byte[] bArr15 = bArr3;
                int trim_i8_decode3 = falconCodec3.trim_i8_decode(bArr14, 0, this.LOGN, falconCodec3.max_FG_bits[this.LOGN], bArr15, i3 + i10, this.CRYPTO_SECRETKEYBYTES - i10);
                if (trim_i8_decode3 == 0) {
                    throw new IllegalArgumentException("F decode failed");
                } else if (i10 + trim_i8_decode3 == this.CRYPTO_SECRETKEYBYTES - 1) {
                    int i11 = this.LOGN;
                    short[] sArr4 = sArr;
                    byte[] bArr16 = bArr8;
                    int i12 = i11;
                    byte[] bArr17 = bArr7;
                    byte[] bArr18 = bArr6;
                    if (falconVrfy.complete_private(bArr8, 0, bArr5, 0, bArr6, 0, bArr7, 0, i12, new short[(this.N * 2)], 0)) {
                        byte[] bArr19 = bArr11;
                        this.rand.nextBytes(bArr19);
                        shake2562.inner_shake256_init();
                        SHAKE256 shake2563 = shake2562;
                        shake2563.inner_shake256_inject(bArr19, 0, this.NONCELEN);
                        shake2563.inner_shake256_inject(bArr2, i, i2);
                        shake2563.i_shake256_flip();
                        short[] sArr5 = sArr3;
                        falconCommon2.hash_to_point_vartime(shake2563, sArr5, 0, this.LOGN);
                        byte[] bArr20 = bArr10;
                        this.rand.nextBytes(bArr20);
                        shake2563.inner_shake256_init();
                        shake2563.inner_shake256_inject(bArr20, 0, 48);
                        shake2563.i_shake256_flip();
                        byte[] bArr21 = bArr5;
                        short[] sArr6 = sArr5;
                        byte[] bArr22 = bArr17;
                        byte[] bArr23 = bArr19;
                        falconSign.sign_dyn(sArr4, 0, shake2563, bArr21, 0, bArr18, 0, bArr22, 0, bArr16, 0, sArr6, 0, this.LOGN, new FalconFPR[(this.N * 10)], 0);
                        int i13 = (this.CRYPTO_BYTES - 2) - this.NONCELEN;
                        byte[] bArr24 = new byte[i13];
                        if (z) {
                            int i14 = this.LOGN;
                            i5 = 0;
                            bArr24[0] = (byte) (i14 + 32);
                            i4 = 1;
                            int comp_encode = this.codec.comp_encode(bArr24, 1, i13 - 1, sArr4, 0, i14);
                            if (comp_encode != 0) {
                                i6 = comp_encode + 1;
                            } else {
                                throw new IllegalStateException("signature failed to generate");
                            }
                        } else {
                            i5 = 0;
                            i4 = 1;
                            i6 = this.codec.comp_encode(bArr24, 0, i13, sArr4, 0, this.LOGN);
                            if (i6 == 0) {
                                throw new IllegalStateException("signature failed to generate");
                            }
                        }
                        bArr4[i5] = (byte) (this.LOGN + 48);
                        System.arraycopy(bArr23, i5, bArr4, i4, this.NONCELEN);
                        System.arraycopy(bArr24, i5, bArr4, this.NONCELEN + i4, i6);
                        return Arrays.copyOfRange(bArr4, i5, this.NONCELEN + i4 + i6);
                    }
                    throw new IllegalStateException("complete_private failed");
                } else {
                    throw new IllegalStateException("full key not used");
                }
            } else {
                throw new IllegalStateException("g decode failed");
            }
        } else {
            throw new IllegalStateException("f decode failed");
        }
    }

    /* access modifiers changed from: package-private */
    public byte[][] crypto_sign_keypair(byte[] bArr, int i, byte[] bArr2, int i2) {
        byte[] bArr3 = bArr;
        byte[] bArr4 = bArr2;
        int i3 = this.N;
        byte[] bArr5 = new byte[i3];
        byte[] bArr6 = new byte[i3];
        byte[] bArr7 = new byte[i3];
        short[] sArr = new short[i3];
        byte[] bArr8 = new byte[48];
        SHAKE256 shake256 = new SHAKE256();
        FalconKeyGen falconKeyGen = new FalconKeyGen();
        this.rand.nextBytes(bArr8);
        shake256.inner_shake256_init();
        shake256.inner_shake256_inject(bArr8, 0, 48);
        shake256.i_shake256_flip();
        falconKeyGen.keygen(shake256, bArr5, 0, bArr6, 0, bArr7, 0, (byte[]) null, 0, sArr, 0, this.LOGN);
        int i4 = this.LOGN;
        bArr4[i2] = (byte) (i4 + 80);
        FalconCodec falconCodec = this.codec;
        int i5 = i2 + 1;
        int trim_i8_encode = falconCodec.trim_i8_encode(bArr2, i5, this.CRYPTO_SECRETKEYBYTES - 1, bArr5, 0, i4, falconCodec.max_fg_bits[this.LOGN]);
        if (trim_i8_encode != 0) {
            int i6 = trim_i8_encode + 1;
            byte[] copyOfRange = Arrays.copyOfRange(bArr4, i5, i6);
            FalconCodec falconCodec2 = this.codec;
            int i7 = i2 + i6;
            int trim_i8_encode2 = falconCodec2.trim_i8_encode(bArr2, i7, this.CRYPTO_SECRETKEYBYTES - i6, bArr6, 0, this.LOGN, falconCodec2.max_fg_bits[this.LOGN]);
            if (trim_i8_encode2 != 0) {
                int i8 = i6 + trim_i8_encode2;
                byte[] copyOfRange2 = Arrays.copyOfRange(bArr4, i7, i8);
                FalconCodec falconCodec3 = this.codec;
                int i9 = i2 + i8;
                int trim_i8_encode3 = falconCodec3.trim_i8_encode(bArr2, i9, this.CRYPTO_SECRETKEYBYTES - i8, bArr7, 0, this.LOGN, falconCodec3.max_FG_bits[this.LOGN]);
                if (trim_i8_encode3 != 0) {
                    int i10 = i8 + trim_i8_encode3;
                    byte[] copyOfRange3 = Arrays.copyOfRange(bArr4, i9, i10);
                    if (i10 == this.CRYPTO_SECRETKEYBYTES) {
                        int i11 = this.LOGN;
                        bArr3[i] = (byte) i11;
                        if (this.codec.modq_encode(bArr, i + 1, this.CRYPTO_PUBLICKEYBYTES - 1, sArr, 0, i11) == this.CRYPTO_PUBLICKEYBYTES - 1) {
                            return new byte[][]{Arrays.copyOfRange(bArr3, 1, bArr3.length), copyOfRange, copyOfRange2, copyOfRange3};
                        }
                        throw new IllegalStateException("public key encoding failed");
                    }
                    throw new IllegalStateException("secret key encoding failed");
                }
                throw new IllegalStateException("F encode failed");
            }
            throw new IllegalStateException("g encode failed");
        }
        throw new IllegalStateException("f encode failed");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x008a, code lost:
        if (r0.codec.comp_decode(r15, 0, r0.LOGN, r21, 0, r9) != r9) goto L_0x00be;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x006e, code lost:
        if (r0.codec.comp_decode(r4, 0, r14, r21, 1, r9) != r9) goto L_0x0070;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int crypto_sign_open(boolean r20, byte[] r21, byte[] r22, byte[] r23, byte[] r24, int r25) {
        /*
            r19 = this;
            r0 = r19
            r5 = r21
            r8 = r23
            int r1 = r0.N
            short[] r7 = new short[r1]
            short[] r6 = new short[r1]
            short[] r4 = new short[r1]
            org.bouncycastle.pqc.crypto.falcon.SHAKE256 r3 = new org.bouncycastle.pqc.crypto.falcon.SHAKE256
            r3.<init>()
            org.bouncycastle.pqc.crypto.falcon.FalconVrfy r2 = new org.bouncycastle.pqc.crypto.falcon.FalconVrfy
            r2.<init>()
            org.bouncycastle.pqc.crypto.falcon.FalconCommon r1 = new org.bouncycastle.pqc.crypto.falcon.FalconCommon
            r1.<init>()
            org.bouncycastle.pqc.crypto.falcon.FalconCodec r9 = r0.codec
            int r12 = r0.LOGN
            int r10 = r0.CRYPTO_PUBLICKEYBYTES
            r15 = 1
            int r16 = r10 + -1
            r11 = 0
            r10 = r7
            r13 = r24
            r14 = r25
            r17 = r1
            r1 = 1
            r15 = r16
            int r9 = r9.modq_decode(r10, r11, r12, r13, r14, r15)
            int r10 = r0.CRYPTO_PUBLICKEYBYTES
            int r10 = r10 - r1
            r12 = -1
            if (r9 == r10) goto L_0x003c
            return r12
        L_0x003c:
            int r9 = r0.LOGN
            r13 = 0
            r2.to_ntt_monty(r7, r13, r9)
            int r9 = r5.length
            int r10 = r8.length
            if (r20 == 0) goto L_0x0071
            if (r9 < r1) goto L_0x0070
            byte r11 = r5[r13]
            int r14 = r0.LOGN
            int r15 = r14 + 32
            byte r15 = (byte) r15
            if (r11 == r15) goto L_0x0052
            goto L_0x0070
        L_0x0052:
            org.bouncycastle.pqc.crypto.falcon.FalconCodec r11 = r0.codec
            int r9 = r9 - r1
            r15 = 0
            r16 = 1
            r18 = r17
            r1 = r11
            r11 = r2
            r2 = r4
            r20 = r3
            r3 = r15
            r15 = r4
            r4 = r14
            r5 = r21
            r14 = r6
            r6 = r16
            r16 = r7
            r7 = r9
            int r1 = r1.comp_decode(r2, r3, r4, r5, r6, r7)
            if (r1 == r9) goto L_0x008d
        L_0x0070:
            return r12
        L_0x0071:
            r11 = r2
            r20 = r3
            r15 = r4
            r14 = r6
            r16 = r7
            r18 = r17
            if (r9 < r1) goto L_0x00be
            org.bouncycastle.pqc.crypto.falcon.FalconCodec r1 = r0.codec
            int r4 = r0.LOGN
            r6 = 0
            r3 = 0
            r2 = r15
            r5 = r21
            r7 = r9
            int r1 = r1.comp_decode(r2, r3, r4, r5, r6, r7)
            if (r1 == r9) goto L_0x008d
            goto L_0x00be
        L_0x008d:
            r20.inner_shake256_init()
            int r1 = r0.NONCELEN
            r3 = r20
            r2 = r22
            r3.inner_shake256_inject(r2, r13, r1)
            r3.inner_shake256_inject(r8, r13, r10)
            r3.i_shake256_flip()
            int r1 = r0.LOGN
            r2 = r18
            r2.hash_to_point_vartime(r3, r14, r13, r1)
            int r9 = r0.LOGN
            int r1 = r0.N
            short[] r10 = new short[r1]
            r1 = 0
            r4 = 0
            r6 = 0
            r8 = 0
            r2 = r11
            r3 = r14
            r5 = r15
            r7 = r16
            r11 = r1
            int r1 = r2.verify_raw(r3, r4, r5, r6, r7, r8, r9, r10, r11)
            if (r1 != 0) goto L_0x00bd
            return r12
        L_0x00bd:
            return r13
        L_0x00be:
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.pqc.crypto.falcon.FalconNIST.crypto_sign_open(boolean, byte[], byte[], byte[], byte[], int):int");
    }
}
