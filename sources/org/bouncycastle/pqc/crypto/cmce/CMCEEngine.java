package org.bouncycastle.pqc.crypto.cmce;

import androidx.core.internal.view.SupportMenu;
import com.google.common.primitives.SignedBytes;
import io.netty.handler.codec.http2.Http2CodecUtil;
import java.lang.reflect.Array;
import java.security.SecureRandom;
import org.bouncycastle.crypto.digests.SHAKEDigest;
import org.bouncycastle.util.Arrays;

class CMCEEngine {
    private int COND_BYTES;
    private int GFBITS;
    private int GFMASK;
    private int IRR_BYTES;
    private int PK_NCOLS;
    private int PK_NROWS;
    private int PK_ROW_BYTES;
    private int SYND_BYTES;
    private int SYS_N;
    private int SYS_T;
    private BENES benes;
    private boolean countErrorIndices;
    private final int defaultKeySize;
    private GF gf;
    private int[] poly;
    private boolean usePadding;
    private boolean usePivots;

    public CMCEEngine(int i, int i2, int i3, int[] iArr, boolean z, int i4) {
        BENES benes2;
        this.usePivots = z;
        this.SYS_N = i2;
        this.SYS_T = i3;
        this.GFBITS = i;
        this.poly = iArr;
        this.defaultKeySize = i4;
        this.IRR_BYTES = i3 * 2;
        boolean z2 = true;
        this.COND_BYTES = (1 << (i - 4)) * ((i * 2) - 1);
        int i5 = i3 * i;
        this.PK_NROWS = i5;
        int i6 = i2 - i5;
        this.PK_NCOLS = i6;
        this.PK_ROW_BYTES = (i6 + 7) / 8;
        this.SYND_BYTES = (i5 + 7) / 8;
        this.GFMASK = (1 << i) - 1;
        if (i == 12) {
            this.gf = new GF12();
            benes2 = new BENES12(this.SYS_N, this.SYS_T, this.GFBITS);
        } else {
            this.gf = new GF13();
            benes2 = new BENES13(this.SYS_N, this.SYS_T, this.GFBITS);
        }
        this.benes = benes2;
        this.usePadding = this.SYS_T % 8 != 0;
        this.countErrorIndices = (1 << this.GFBITS) <= this.SYS_N ? false : z2;
    }

    private void bm(short[] sArr, short[] sArr2) {
        int i;
        int i2 = this.SYS_T;
        short[] sArr3 = new short[(i2 + 1)];
        short[] sArr4 = new short[(i2 + 1)];
        short s = 1;
        short[] sArr5 = new short[(i2 + 1)];
        int i3 = 0;
        for (int i4 = 0; i4 < this.SYS_T + 1; i4++) {
            sArr5[i4] = 0;
            sArr4[i4] = 0;
        }
        sArr4[0] = 1;
        sArr5[1] = 1;
        short s2 = 0;
        short s3 = 0;
        short s4 = 1;
        while (s2 < this.SYS_T * 2) {
            int i5 = 0;
            for (int i6 = 0; i6 <= min(s2, this.SYS_T); i6++) {
                i5 ^= this.gf.gf_mul_ext(sArr4[i6], sArr2[s2 - i6]);
            }
            short gf_reduce = this.gf.gf_reduce(i5);
            short s5 = (short) (((short) (((short) (((short) (gf_reduce - 1)) >> 15)) & s)) - s);
            short s6 = (short) (((short) (((short) (((short) (((short) (s2 - (s3 * 2))) >> 15)) & s)) - s)) & s5);
            for (int i7 = 0; i7 <= this.SYS_T; i7++) {
                sArr3[i7] = sArr4[i7];
            }
            short gf_frac = this.gf.gf_frac(s4, gf_reduce);
            int i8 = 0;
            while (true) {
                i = this.SYS_T;
                if (i8 > i) {
                    break;
                }
                sArr4[i8] = (short) ((this.gf.gf_mul(gf_frac, sArr5[i8]) & s5) ^ sArr4[i8]);
                i8++;
            }
            short s7 = s6 ^ -1;
            int i9 = s2 + 1;
            s3 = (short) (((i9 - s3) & s6) | (s3 & s7));
            for (int i10 = i - 1; i10 >= 0; i10--) {
                sArr5[i10 + 1] = (short) ((sArr5[i10] & s7) | (sArr3[i10] & s6));
            }
            sArr5[0] = 0;
            s4 = (short) ((s7 & s4) | (gf_reduce & s6));
            s2 = (short) i9;
            s = 1;
        }
        while (true) {
            int i11 = this.SYS_T;
            if (i3 <= i11) {
                sArr[i3] = sArr4[i11 - i3];
                i3++;
            } else {
                return;
            }
        }
    }

    static void cbrecursion(byte[] bArr, long j, long j2, short[] sArr, int i, long j3, long j4, int[] iArr) {
        long j5;
        int i2 = i;
        long j6 = j4;
        int[] iArr2 = iArr;
        long j7 = 1;
        if (j3 == 1) {
            int i3 = (int) (j >> 3);
            bArr[i3] = (byte) ((get_q_short(iArr2, i2) << ((int) (j & 7))) ^ bArr[i3]);
            return;
        }
        if (sArr != null) {
            for (long j8 = 0; j8 < j6; j8++) {
                int i4 = (int) j8;
                iArr2[i4] = sArr[(int) (j8 ^ 1)] | ((sArr[i4] ^ 1) << 16);
            }
        } else {
            for (long j9 = 0; j9 < j6; j9++) {
                long j10 = (long) i2;
                iArr2[(int) j9] = ((get_q_short(iArr2, (int) (j10 + j9)) ^ 1) << 16) | get_q_short(iArr2, (int) (j10 + (j9 ^ 1)));
            }
        }
        int i5 = (int) j6;
        int i6 = 0;
        sort32(iArr2, 0, i5);
        for (long j11 = 0; j11 < j6; j11++) {
            int i7 = (int) j11;
            int i8 = 65535 & iArr2[i7];
            if (j11 >= ((long) i8)) {
                i7 = i8;
            }
            iArr2[(int) (j6 + j11)] = (i8 << 16) | i7;
        }
        for (long j12 = 0; j12 < j6; j12++) {
            int i9 = (int) j12;
            iArr2[i9] = (int) (((long) (iArr2[i9] << 16)) | j12);
        }
        sort32(iArr2, 0, i5);
        long j13 = 0;
        while (j13 < j6) {
            int i10 = (int) j13;
            iArr2[i10] = (iArr2[i10] << 16) + (iArr2[(int) (j6 + j13)] >> 16);
            j13++;
            i6 = 0;
        }
        sort32(iArr2, i6, i5);
        long j14 = 0;
        if (j3 <= 10) {
            while (j14 < j6) {
                int i11 = (int) (j6 + j14);
                iArr2[i11] = ((iArr2[(int) j14] & 65535) << 10) | (iArr2[i11] & 1023);
                j14++;
            }
            long j15 = 1;
            while (j15 < j3 - j7) {
                for (long j16 = 0; j16 < j6; j16 += j7) {
                    iArr2[(int) j16] = (int) (((long) ((iArr2[(int) (j6 + j16)] & -1024) << 6)) | j16);
                }
                sort32(iArr2, 0, i5);
                for (long j17 = 0; j17 < j6; j17++) {
                    int i12 = (int) j17;
                    iArr2[i12] = iArr2[(int) (j6 + j17)] | (iArr2[i12] << 20);
                }
                sort32(iArr2, 0, i5);
                for (long j18 = 0; j18 < j6; j18++) {
                    int i13 = iArr2[(int) j18];
                    int i14 = 1048575 & i13;
                    int i15 = (int) (j6 + j18);
                    int i16 = (i13 & 1047552) | (iArr2[i15] & 1023);
                    if (i14 >= i16) {
                        i14 = i16;
                    }
                    iArr2[i15] = i14;
                }
                j15++;
                j7 = 1;
            }
            long j19 = j7;
            for (long j20 = 0; j20 < j6; j20 += j19) {
                int i17 = (int) (j6 + j20);
                iArr2[i17] = iArr2[i17] & 1023;
            }
        } else {
            while (j14 < j6) {
                int i18 = (int) (j6 + j14);
                iArr2[i18] = (iArr2[(int) j14] << 16) | (iArr2[i18] & 65535);
                j14++;
            }
            long j21 = 1;
            for (long j22 = 1; j21 < j3 - j22; j22 = 1) {
                for (long j23 = 0; j23 < j6; j23++) {
                    iArr2[(int) j23] = (int) (((long) (iArr2[(int) (j6 + j23)] & SupportMenu.CATEGORY_MASK)) | j23);
                }
                sort32(iArr2, 0, i5);
                for (long j24 = 0; j24 < j6; j24++) {
                    int i19 = (int) j24;
                    iArr2[i19] = (iArr2[i19] << 16) | (iArr2[(int) (j6 + j24)] & 65535);
                }
                if (j21 < j3 - 2) {
                    for (long j25 = 0; j25 < j6; j25++) {
                        int i20 = (int) (j6 + j25);
                        iArr2[i20] = (iArr2[(int) j25] & SupportMenu.CATEGORY_MASK) | (iArr2[i20] >> 16);
                    }
                    sort32(iArr2, i5, (int) (j6 * 2));
                    for (long j26 = 0; j26 < j6; j26++) {
                        int i21 = (int) (j6 + j26);
                        iArr2[i21] = (iArr2[i21] << 16) | (iArr2[(int) j26] & 65535);
                    }
                }
                sort32(iArr2, 0, i5);
                for (long j27 = 0; j27 < j6; j27++) {
                    int i22 = (int) (j6 + j27);
                    int i23 = iArr2[i22];
                    int i24 = (iArr2[(int) j27] & 65535) | (i23 & SupportMenu.CATEGORY_MASK);
                    if (i24 < i23) {
                        iArr2[i22] = i24;
                    }
                }
                j21++;
            }
            for (long j28 = 0; j28 < j6; j28++) {
                int i25 = (int) (j6 + j28);
                iArr2[i25] = iArr2[i25] & 65535;
            }
        }
        long j29 = 0;
        if (sArr != null) {
            while (j29 < j6) {
                int i26 = (int) j29;
                iArr2[i26] = (int) (((long) (sArr[i26] << 16)) + j29);
                j29++;
            }
        } else {
            while (j29 < j6) {
                iArr2[(int) j29] = (int) (((long) (get_q_short(iArr2, (int) (((long) i2) + j29)) << 16)) + j29);
                j29++;
            }
        }
        sort32(iArr2, 0, i5);
        long j30 = j;
        long j31 = 0;
        while (true) {
            j5 = j6 / 2;
            if (j31 >= j5) {
                break;
            }
            long j32 = j31 * 2;
            long j33 = j6 + j32;
            int i27 = (int) j33;
            int i28 = iArr2[i27] & 1;
            int i29 = (int) (((long) i28) + j32);
            int i30 = i28;
            int i31 = (int) (j30 >> 3);
            bArr[i31] = (byte) (bArr[i31] ^ (i30 << ((int) (j30 & 7))));
            j30 += j2;
            iArr2[i27] = (iArr2[(int) j32] << 16) | i29;
            iArr2[(int) (j33 + 1)] = (iArr2[(int) (j32 + 1)] << 16) | (i29 ^ 1);
            j31++;
        }
        long j34 = j6 * 2;
        sort32(iArr2, i5, (int) j34);
        long j35 = j3 * 2;
        long j36 = j30 + ((j35 - 3) * j2 * j5);
        long j37 = 0;
        while (j37 < j5) {
            long j38 = j37 * 2;
            long j39 = j34;
            long j40 = j6 + j38;
            int i32 = iArr2[(int) j40];
            int i33 = i32 & 1;
            int i34 = (int) (((long) i33) + j38);
            int i35 = (int) (j36 >> 3);
            bArr[i35] = (byte) (bArr[i35] ^ (i33 << ((int) (j36 & 7))));
            j36 += j2;
            iArr2[(int) j38] = (i32 & 65535) | (i34 << 16);
            iArr2[(int) (j38 + 1)] = ((i34 ^ 1) << 16) | (iArr2[(int) (j40 + 1)] & 65535);
            j35 = j35;
            j34 = j39;
            j37++;
            j6 = j4;
        }
        sort32(iArr2, 0, i5);
        long j41 = j36 - (((j35 - 2) * j2) * j5);
        short[] sArr2 = new short[(i5 * 4)];
        long j42 = j34;
        for (long j43 = 0; j43 < j42; j43++) {
            long j44 = j43 * 2;
            int i36 = iArr2[(int) j43];
            sArr2[(int) j44] = (short) i36;
            sArr2[(int) (j44 + 1)] = (short) ((i36 & SupportMenu.CATEGORY_MASK) >> 16);
        }
        for (long j45 = 0; j45 < j5; j45++) {
            long j46 = j45 * 2;
            sArr2[(int) j45] = (short) ((iArr2[(int) j46] & 65535) >>> 1);
            sArr2[(int) (j45 + j5)] = (short) ((iArr2[(int) (j46 + 1)] & 65535) >>> 1);
        }
        for (long j47 = 0; j47 < j5; j47++) {
            long j48 = j47 * 2;
            iArr2[(int) (j4 + (j4 / 4) + j47)] = (sArr2[(int) (j48 + 1)] << 16) | sArr2[(int) j48];
        }
        long j49 = j4 + (j4 / 4);
        byte[] bArr2 = bArr;
        long j50 = j2 * 2;
        long j51 = j3 - 1;
        cbrecursion(bArr2, j41, j50, (short[]) null, ((int) j49) * 2, j51, j5, iArr);
        cbrecursion(bArr2, j41 + j2, j50, (short[]) null, (int) ((j49 * 2) + j5), j51, j5, iArr);
    }

    private static void controlbitsfrompermutation(byte[] bArr, short[] sArr, long j, long j2) {
        byte[] bArr2 = bArr;
        long j3 = j2;
        long j4 = 2;
        int[] iArr = new int[((int) (j3 * 2))];
        int i = (int) j3;
        short[] sArr2 = new short[i];
        while (true) {
            short s = 0;
            for (int i2 = 0; ((long) i2) < (((((j * j4) - 1) * j3) / j4) + 7) / 8; i2++) {
                bArr2[i2] = 0;
            }
            int i3 = i;
            short[] sArr3 = sArr2;
            int[] iArr2 = iArr;
            cbrecursion(bArr, 0, 1, sArr, 0, j, j2, iArr);
            for (int i4 = 0; ((long) i4) < j3; i4++) {
                sArr3[i4] = (short) i4;
            }
            short[] sArr4 = sArr3;
            int i5 = 0;
            for (int i6 = 0; ((long) i6) < j; i6++) {
                layer(sArr4, bArr2, i5, i6, i3);
                i5 = (int) (((long) i5) + (j3 >> 4));
            }
            for (int i7 = (int) (j - 2); i7 >= 0; i7--) {
                layer(sArr4, bArr2, i5, i7, i3);
                i5 = (int) (((long) i5) + (j3 >> 4));
            }
            int i8 = 0;
            while (((long) i8) < j3) {
                i8++;
                s = (short) (s | (sArr[i8] ^ sArr4[i8]));
            }
            if (s != 0) {
                sArr2 = sArr4;
                i = i3;
                iArr = iArr2;
                j4 = 2;
            } else {
                return;
            }
        }
    }

    private static int ctz(long j) {
        long j2 = j ^ -1;
        long j3 = 72340172838076673L;
        long j4 = 0;
        for (int i = 0; i < 8; i++) {
            j3 &= j2 >>> i;
            j4 += j3;
        }
        long j5 = 578721382704613384L & j4;
        long j6 = j5 | (j5 >>> 1);
        long j7 = j6 | (j6 >>> 2);
        long j8 = j4 >>> 8;
        long j9 = j4 + (j8 & j7);
        for (int i2 = 2; i2 < 8; i2++) {
            j7 &= j7 >>> 8;
            j8 >>>= 8;
            j9 += j8 & j7;
        }
        return ((int) j9) & 255;
    }

    private int decrypt(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        int i;
        int i2;
        int i3 = this.SYS_T;
        short[] sArr = new short[(i3 + 1)];
        int i4 = this.SYS_N;
        short[] sArr2 = new short[i4];
        short[] sArr3 = new short[(i3 * 2)];
        short[] sArr4 = new short[(i3 * 2)];
        short[] sArr5 = new short[(i3 + 1)];
        short[] sArr6 = new short[i4];
        byte[] bArr4 = new byte[(i4 / 8)];
        int i5 = 0;
        while (true) {
            i = this.SYND_BYTES;
            if (i5 >= i) {
                break;
            }
            bArr4[i5] = bArr3[i5];
            i5++;
        }
        while (i < this.SYS_N / 8) {
            bArr4[i] = 0;
            i++;
        }
        int i6 = 0;
        while (true) {
            i2 = this.SYS_T;
            if (i6 >= i2) {
                break;
            }
            sArr[i6] = Utils.load_gf(bArr2, (i6 * 2) + 40, this.GFMASK);
            i6++;
        }
        sArr[i2] = 1;
        this.benes.support_gen(sArr2, bArr2);
        synd(sArr3, sArr, sArr2, bArr4);
        bm(sArr5, sArr3);
        root(sArr6, sArr5, sArr2);
        for (int i7 = 0; i7 < this.SYS_N / 8; i7++) {
            bArr[i7] = 0;
        }
        int i8 = 0;
        for (int i9 = 0; i9 < this.SYS_N; i9++) {
            short gf_iszero = (short) (this.gf.gf_iszero(sArr6[i9]) & 1);
            int i10 = i9 / 8;
            bArr[i10] = (byte) (bArr[i10] | (gf_iszero << (i9 % 8)));
            i8 += gf_iszero;
        }
        synd(sArr4, sArr, sArr2, bArr);
        short s = this.SYS_T ^ i8;
        for (int i11 = 0; i11 < this.SYS_T * 2; i11++) {
            s |= sArr3[i11] ^ sArr4[i11];
        }
        return (((s - 1) >> 15) & 1) ^ 1;
    }

    private void encrypt(byte[] bArr, byte[] bArr2, byte[] bArr3, SecureRandom secureRandom) {
        generate_error_vector(bArr3, secureRandom);
        syndrome(bArr, bArr2, bArr3);
    }

    private short eval(short[] sArr, short s) {
        int i = this.SYS_T;
        short s2 = sArr[i];
        for (int i2 = i - 1; i2 >= 0; i2--) {
            s2 = (short) (this.gf.gf_mul(s2, s) ^ sArr[i2]);
        }
        return s2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:47:0x000a A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void generate_error_vector(byte[] r11, java.security.SecureRandom r12) {
        /*
            r10 = this;
            int r0 = r10.SYS_T
            int r1 = r0 * 2
            short[] r1 = new short[r1]
            short[] r2 = new short[r0]
            byte[] r0 = new byte[r0]
        L_0x000a:
            boolean r3 = r10.countErrorIndices
            r4 = 0
            if (r3 == 0) goto L_0x0047
            int r3 = r10.SYS_T
            int r3 = r3 * 4
            byte[] r3 = new byte[r3]
            r12.nextBytes(r3)
            r5 = 0
        L_0x0019:
            int r6 = r10.SYS_T
            int r6 = r6 * 2
            if (r5 >= r6) goto L_0x002c
            int r6 = r5 * 2
            int r7 = r10.GFMASK
            short r6 = org.bouncycastle.pqc.crypto.cmce.Utils.load_gf(r3, r6, r7)
            r1[r5] = r6
            int r5 = r5 + 1
            goto L_0x0019
        L_0x002c:
            r3 = 0
            r5 = 0
        L_0x002e:
            int r6 = r10.SYS_T
            int r7 = r6 * 2
            if (r3 >= r7) goto L_0x0044
            if (r5 >= r6) goto L_0x0044
            short r6 = r1[r3]
            int r7 = r10.SYS_N
            if (r6 >= r7) goto L_0x0041
            int r7 = r5 + 1
            r2[r5] = r6
            r5 = r7
        L_0x0041:
            int r3 = r3 + 1
            goto L_0x002e
        L_0x0044:
            if (r5 >= r6) goto L_0x0062
            goto L_0x000a
        L_0x0047:
            int r3 = r10.SYS_T
            int r3 = r3 * 2
            byte[] r3 = new byte[r3]
            r12.nextBytes(r3)
            r5 = 0
        L_0x0051:
            int r6 = r10.SYS_T
            if (r5 >= r6) goto L_0x0062
            int r6 = r5 * 2
            int r7 = r10.GFMASK
            short r6 = org.bouncycastle.pqc.crypto.cmce.Utils.load_gf(r3, r6, r7)
            r2[r5] = r6
            int r5 = r5 + 1
            goto L_0x0051
        L_0x0062:
            r3 = 1
            r5 = 1
            r6 = 0
        L_0x0065:
            int r7 = r10.SYS_T
            if (r5 >= r7) goto L_0x007c
            if (r6 == r3) goto L_0x007c
            r7 = 0
        L_0x006c:
            if (r7 >= r5) goto L_0x0079
            short r8 = r2[r5]
            short r9 = r2[r7]
            if (r8 != r9) goto L_0x0076
            r6 = 1
            goto L_0x0079
        L_0x0076:
            int r7 = r7 + 1
            goto L_0x006c
        L_0x0079:
            int r5 = r5 + 1
            goto L_0x0065
        L_0x007c:
            if (r6 != 0) goto L_0x000a
            r12 = 0
        L_0x007f:
            int r1 = r10.SYS_T
            if (r12 >= r1) goto L_0x008f
            short r1 = r2[r12]
            r1 = r1 & 7
            int r1 = r3 << r1
            byte r1 = (byte) r1
            r0[r12] = r1
            int r12 = r12 + 1
            goto L_0x007f
        L_0x008f:
            r12 = 0
        L_0x0090:
            int r1 = r10.SYS_N
            int r1 = r1 / 8
            if (r12 >= r1) goto L_0x00ba
            r11[r12] = r4
            r1 = 0
        L_0x0099:
            int r3 = r10.SYS_T
            if (r1 >= r3) goto L_0x00b6
            short r3 = r2[r1]
            int r3 = r3 >> 3
            short r3 = (short) r3
            byte r3 = same_mask32(r12, r3)
            short r3 = (short) r3
            r3 = r3 & 255(0xff, float:3.57E-43)
            short r3 = (short) r3
            byte r5 = r11[r12]
            byte r6 = r0[r1]
            r3 = r3 & r6
            r3 = r3 | r5
            byte r3 = (byte) r3
            r11[r12] = r3
            int r1 = r1 + 1
            goto L_0x0099
        L_0x00b6:
            int r12 = r12 + 1
            short r12 = (short) r12
            goto L_0x0090
        L_0x00ba:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.pqc.crypto.cmce.CMCEEngine.generate_error_vector(byte[], java.security.SecureRandom):void");
    }

    private int generate_irr_poly(short[] sArr) {
        int i;
        int i2 = this.SYS_T;
        int i3 = 2;
        int[] iArr = new int[2];
        iArr[1] = i2;
        iArr[0] = i2 + 1;
        short[][] sArr2 = (short[][]) Array.newInstance(Short.TYPE, iArr);
        sArr2[0][0] = 1;
        System.arraycopy(sArr, 0, sArr2[1], 0, this.SYS_T);
        int[] iArr2 = new int[((this.SYS_T * 2) - 1)];
        while (true) {
            i = this.SYS_T;
            if (i3 >= i) {
                break;
            }
            this.gf.gf_sqr_poly(i, this.poly, sArr2[i3], sArr2[i3 >>> 1], iArr2);
            this.gf.gf_mul_poly(this.SYS_T, this.poly, sArr2[i3 + 1], sArr2[i3], sArr, iArr2);
            i3 += 2;
        }
        if (i3 == i) {
            this.gf.gf_sqr_poly(i, this.poly, sArr2[i3], sArr2[i3 >>> 1], iArr2);
        }
        int i4 = 0;
        while (true) {
            int i5 = this.SYS_T;
            if (i4 < i5) {
                int i6 = i4 + 1;
                for (int i7 = i6; i7 < this.SYS_T; i7++) {
                    short gf_iszero = this.gf.gf_iszero(sArr2[i4][i4]);
                    for (int i8 = i4; i8 < this.SYS_T + 1; i8++) {
                        short[] sArr3 = sArr2[i8];
                        sArr3[i4] = (short) (sArr3[i4] ^ ((short) (sArr3[i7] & gf_iszero)));
                    }
                }
                short s = sArr2[i4][i4];
                if (s == 0) {
                    return -1;
                }
                short gf_inv = this.gf.gf_inv(s);
                for (int i9 = i4; i9 < this.SYS_T + 1; i9++) {
                    short[] sArr4 = sArr2[i9];
                    sArr4[i4] = this.gf.gf_mul(sArr4[i4], gf_inv);
                }
                for (int i10 = 0; i10 < this.SYS_T; i10++) {
                    if (i10 != i4) {
                        short s2 = sArr2[i4][i10];
                        for (int i11 = i4; i11 <= this.SYS_T; i11++) {
                            short[] sArr5 = sArr2[i11];
                            sArr5[i10] = (short) (sArr5[i10] ^ this.gf.gf_mul(sArr5[i4], s2));
                        }
                    }
                }
                i4 = i6;
            } else {
                System.arraycopy(sArr2[i5], 0, sArr, 0, i5);
                return 0;
            }
        }
    }

    static short get_q_short(int[] iArr, int i) {
        int i2 = i / 2;
        return (short) (i % 2 == 0 ? iArr[i2] : (iArr[i2] & SupportMenu.CATEGORY_MASK) >> 16);
    }

    private static void layer(short[] sArr, byte[] bArr, int i, int i2, int i3) {
        int i4 = 1 << i2;
        int i5 = 0;
        for (int i6 = 0; i6 < i3; i6 += i4 * 2) {
            for (int i7 = 0; i7 < i4; i7++) {
                int i8 = i6 + i7;
                short s = sArr[i8];
                int i9 = i8 + i4;
                short s2 = (sArr[i9] ^ s) & (-((bArr[(i5 >> 3) + i] >> (i5 & 7)) & 1));
                sArr[i8] = (short) (s ^ s2);
                sArr[i9] = (short) (sArr[i9] ^ s2);
                i5++;
            }
        }
    }

    private static int min(short s, int i) {
        return s < i ? s : i;
    }

    private int mov_columns(byte[][] bArr, short[] sArr, long[] jArr) {
        long j;
        byte[] bArr2;
        long[] jArr2 = new long[64];
        int i = 32;
        long[] jArr3 = new long[32];
        byte[] bArr3 = new byte[9];
        int i2 = this.PK_NROWS - 32;
        int i3 = i2 / 8;
        int i4 = i2 % 8;
        char c = 0;
        if (this.usePadding) {
            for (int i5 = 0; i5 < 32; i5++) {
                for (int i6 = 0; i6 < 9; i6++) {
                    bArr3[i6] = bArr[i2 + i5][i3 + i6];
                }
                int i7 = 0;
                while (i7 < 8) {
                    int i8 = i7 + 1;
                    bArr3[i7] = (byte) (((bArr3[i7] & 255) >> i4) | (bArr3[i8] << (8 - i4)));
                    i7 = i8;
                }
                jArr2[i5] = Utils.load8(bArr3, 0);
            }
        } else {
            for (int i9 = 0; i9 < 32; i9++) {
                jArr2[i9] = Utils.load8(bArr[i2 + i9], i3);
            }
        }
        long j2 = 0;
        jArr[0] = 0;
        int i10 = 0;
        while (i10 < 32) {
            long j3 = jArr2[i10];
            int i11 = i10 + 1;
            for (int i12 = i11; i12 < 32; i12++) {
                j3 |= jArr2[i12];
            }
            if (j3 == j2) {
                return -1;
            }
            int ctz = ctz(j3);
            long j4 = (long) ctz;
            jArr3[i10] = j4;
            jArr[c] = jArr[c] | (1 << ((int) j4));
            for (int i13 = i11; i13 < 32; i13++) {
                long j5 = jArr2[i10];
                jArr2[i10] = j5 ^ (jArr2[i13] & (((j5 >> ctz) & 1) - 1));
            }
            int i14 = i11;
            while (i14 < 32) {
                long j6 = jArr2[i14];
                jArr2[i14] = j6 ^ (jArr2[i10] & (-((j6 >> ctz) & 1)));
                i14++;
                ctz = ctz;
                c = 0;
            }
            i10 = i11;
            j2 = 0;
        }
        int i15 = 0;
        while (i15 < 32) {
            int i16 = i15 + 1;
            int i17 = i16;
            while (i17 < 64) {
                int i18 = i2 + i15;
                int i19 = i2 + i17;
                long same_mask64 = same_mask64((short) i17, (short) ((int) jArr3[i15])) & ((long) (sArr[i18] ^ sArr[i19]));
                sArr[i18] = (short) ((int) (((long) sArr[i18]) ^ same_mask64));
                sArr[i19] = (short) ((int) (same_mask64 ^ ((long) sArr[i19])));
                i17++;
                bArr3 = bArr3;
            }
            i15 = i16;
        }
        byte[] bArr4 = bArr3;
        int i20 = 0;
        while (i20 < this.PK_NROWS) {
            if (this.usePadding) {
                for (int i21 = 0; i21 < 9; i21++) {
                    bArr4[i21] = bArr[i20][i3 + i21];
                }
                int i22 = 0;
                while (i22 < 8) {
                    int i23 = i22 + 1;
                    bArr4[i22] = (byte) (((bArr4[i22] & 255) >> i4) | (bArr4[i23] << (8 - i4)));
                    i22 = i23;
                }
                bArr2 = bArr4;
                j = Utils.load8(bArr2, 0);
            } else {
                bArr2 = bArr4;
                j = Utils.load8(bArr[i20], i3);
            }
            int i24 = 0;
            while (i24 < i) {
                long j7 = jArr3[i24];
                long j8 = ((j >> i24) ^ (j >> ((int) j7))) & 1;
                j = (j8 << i24) ^ ((j8 << ((int) j7)) ^ j);
                i24++;
                i = 32;
            }
            if (this.usePadding) {
                Utils.store8(bArr2, 0, j);
                byte[] bArr5 = bArr[i20];
                int i25 = i3 + 8;
                int i26 = 8 - i4;
                bArr5[i25] = (byte) ((((bArr5[i25] & 255) >>> i4) << i4) | ((bArr2[7] & 255) >>> i26));
                bArr5[i3] = (byte) (((bArr2[0] & 255) << i4) | (((bArr5[i3] & 255) << i26) >>> i26));
                for (int i27 = 7; i27 >= 1; i27--) {
                    bArr[i20][i3 + i27] = (byte) (((bArr2[i27] & 255) << i4) | ((bArr2[i27 - 1] & 255) >>> i26));
                }
            } else {
                Utils.store8(bArr[i20], i3, j);
            }
            i20++;
            bArr4 = bArr2;
            i = 32;
        }
        return 0;
    }

    private int pk_gen(byte[] bArr, byte[] bArr2, int[] iArr, short[] sArr, long[] jArr) {
        int i;
        int i2;
        byte[] bArr3 = bArr;
        short[] sArr2 = sArr;
        int i3 = this.SYS_T;
        short[] sArr3 = new short[(i3 + 1)];
        sArr3[i3] = 1;
        for (int i4 = 0; i4 < this.SYS_T; i4++) {
            sArr3[i4] = Utils.load_gf(bArr2, (i4 * 2) + 40, this.GFMASK);
        }
        int i5 = 1 << this.GFBITS;
        long[] jArr2 = new long[i5];
        for (int i6 = 0; i6 < (1 << this.GFBITS); i6++) {
            long j = (long) iArr[i6];
            jArr2[i6] = j;
            long j2 = j << 31;
            jArr2[i6] = j2;
            long j3 = j2 | ((long) i6);
            jArr2[i6] = j3;
            jArr2[i6] = j3 & Long.MAX_VALUE;
        }
        sort64(jArr2, 0, i5);
        for (int i7 = 1; i7 < (1 << this.GFBITS); i7++) {
            if ((jArr2[i7 - 1] >> 31) == (jArr2[i7] >> 31)) {
                return -1;
            }
        }
        short[] sArr4 = new short[this.SYS_N];
        for (int i8 = 0; i8 < (1 << this.GFBITS); i8++) {
            sArr2[i8] = (short) ((int) (jArr2[i8] & ((long) this.GFMASK)));
        }
        int i9 = 0;
        while (true) {
            i = this.SYS_N;
            if (i9 >= i) {
                break;
            }
            sArr4[i9] = Utils.bitrev(sArr2[i9], this.GFBITS);
            i9++;
        }
        short[] sArr5 = new short[i];
        root(sArr5, sArr3, sArr4);
        int i10 = 0;
        while (true) {
            i2 = this.SYS_N;
            if (i10 >= i2) {
                break;
            }
            sArr5[i10] = this.gf.gf_inv(sArr5[i10]);
            i10++;
        }
        int i11 = this.PK_NROWS;
        int[] iArr2 = new int[2];
        iArr2[1] = i2 / 8;
        iArr2[0] = i11;
        byte[][] bArr4 = (byte[][]) Array.newInstance(Byte.TYPE, iArr2);
        for (int i12 = 0; i12 < this.PK_NROWS; i12++) {
            for (int i13 = 0; i13 < this.SYS_N / 8; i13++) {
                bArr4[i12][i13] = 0;
            }
        }
        int i14 = 0;
        while (i14 < this.SYS_T) {
            for (int i15 = 0; i15 < this.SYS_N; i15 += 8) {
                int i16 = 0;
                while (true) {
                    int i17 = this.GFBITS;
                    if (i16 >= i17) {
                        break;
                    }
                    bArr4[(i17 * i14) + i16][i15 / 8] = (byte) (((byte) (((byte) (((byte) (((byte) (((byte) (((byte) (((byte) (((byte) (((byte) (((byte) (((byte) (((byte) (((byte) (((byte) ((sArr5[i15 + 7] >>> i16) & 1)) << 1)) | ((sArr5[i15 + 6] >>> i16) & 1))) << 1)) | ((sArr5[i15 + 5] >>> i16) & 1))) << 1)) | ((sArr5[i15 + 4] >>> i16) & 1))) << 1)) | ((sArr5[i15 + 3] >>> i16) & 1))) << 1)) | ((sArr5[i15 + 2] >>> i16) & 1))) << 1)) | ((sArr5[i15 + 1] >>> i16) & 1))) << 1)) | ((sArr5[i15] >>> i16) & 1));
                    i16++;
                }
            }
            for (int i18 = 0; i18 < this.SYS_N; i18++) {
                sArr5[i18] = this.gf.gf_mul(sArr5[i18], sArr4[i18]);
            }
            i14++;
        }
        byte b = 0;
        while (true) {
            int i19 = this.PK_NROWS;
            if (b < i19) {
                i14 = b >>> 3;
                byte b2 = b & 7;
                if (!this.usePivots || b != i19 - 32) {
                    long[] jArr3 = jArr;
                } else if (mov_columns(bArr4, sArr2, jArr) != 0) {
                    return -1;
                }
                int i20 = b + 1;
                for (int i21 = i20; i21 < this.PK_NROWS; i21++) {
                    byte b3 = (byte) (-((byte) (((byte) (((byte) (bArr4[b][i14] ^ bArr4[i21][i14])) >> b2)) & 1)));
                    for (int i22 = 0; i22 < this.SYS_N / 8; i22++) {
                        byte[] bArr5 = bArr4[b];
                        bArr5[i22] = (byte) (bArr5[i22] ^ (bArr4[i21][i22] & b3));
                    }
                }
                if (((bArr4[b][i14] >> b2) & 1) == 0) {
                    return -1;
                }
                for (int i23 = 0; i23 < this.PK_NROWS; i23++) {
                    if (i23 != b) {
                        byte b4 = (byte) (-((byte) (((byte) (bArr4[i23][i14] >> b2)) & 1)));
                        for (int i24 = 0; i24 < this.SYS_N / 8; i24++) {
                            byte[] bArr6 = bArr4[i23];
                            bArr6[i24] = (byte) (bArr6[i24] ^ (bArr4[b][i24] & b4));
                        }
                    }
                }
                b = i20;
            } else if (bArr3 == null) {
                return 0;
            } else {
                if (this.usePadding) {
                    int i25 = i19 % 8;
                    if (i25 == 0) {
                        System.arraycopy(bArr4[i14], (i19 - 1) / 8, bArr3, 0, this.SYS_N / 8);
                        int i26 = this.SYS_N / 8;
                        return 0;
                    }
                    int i27 = 0;
                    int i28 = 0;
                    while (true) {
                        int i29 = this.PK_NROWS;
                        if (i27 >= i29) {
                            return 0;
                        }
                        int i30 = (i29 - 1) / 8;
                        while (i30 < (this.SYS_N / 8) - 1) {
                            byte[] bArr7 = bArr4[i27];
                            i30++;
                            bArr3[i28] = (byte) ((bArr7[i30] << (8 - i25)) | ((bArr7[i30] & 255) >>> i25));
                            i28++;
                        }
                        bArr3[i28] = (byte) ((bArr4[i27][i30] & 255) >>> i25);
                        i27++;
                        i28++;
                    }
                } else {
                    int i31 = ((this.SYS_N - i19) + 7) / 8;
                    int i32 = 0;
                    while (true) {
                        int i33 = this.PK_NROWS;
                        if (i32 >= i33) {
                            return 0;
                        }
                        System.arraycopy(bArr4[i32], i33 / 8, bArr3, i31 * i32, i31);
                        i32++;
                    }
                }
            }
        }
    }

    private void root(short[] sArr, short[] sArr2, short[] sArr3) {
        for (int i = 0; i < this.SYS_N; i++) {
            sArr[i] = eval(sArr2, sArr3[i]);
        }
    }

    private static byte same_mask32(short s, short s2) {
        return (byte) ((-(((s ^ s2) - 1) >>> 31)) & 255);
    }

    private static long same_mask64(short s, short s2) {
        return -((((long) (s ^ s2)) - 1) >>> 63);
    }

    private static void sort32(int[] iArr, int i, int i2) {
        int i3 = i2 - i;
        if (i3 >= 2) {
            int i4 = 1;
            while (i4 < i3 - i4) {
                i4 += i4;
            }
            for (int i5 = i4; i5 > 0; i5 >>>= 1) {
                int i6 = 0;
                for (int i7 = 0; i7 < i3 - i5; i7++) {
                    if ((i7 & i5) == 0) {
                        int i8 = i + i7;
                        int i9 = i8 + i5;
                        int i10 = iArr[i9];
                        int i11 = iArr[i8];
                        int i12 = i10 ^ i11;
                        int i13 = i10 - i11;
                        int i14 = ((((i10 ^ i13) & i12) ^ i13) >> 31) & i12;
                        iArr[i8] = i11 ^ i14;
                        iArr[i9] = iArr[i9] ^ i14;
                    }
                }
                for (int i15 = i4; i15 > i5; i15 >>>= 1) {
                    while (i6 < i3 - i15) {
                        if ((i6 & i5) == 0) {
                            int i16 = i + i6;
                            int i17 = i16 + i5;
                            int i18 = iArr[i17];
                            for (int i19 = i15; i19 > i5; i19 >>>= 1) {
                                int i20 = i16 + i19;
                                int i21 = iArr[i20];
                                int i22 = i21 ^ i18;
                                int i23 = i21 - i18;
                                int i24 = i22 & ((i23 ^ ((i23 ^ i21) & i22)) >> 31);
                                i18 ^= i24;
                                iArr[i20] = i21 ^ i24;
                            }
                            iArr[i17] = i18;
                        }
                        i6++;
                    }
                }
            }
        }
    }

    private static void sort64(long[] jArr, int i, int i2) {
        int i3 = i2 - i;
        if (i3 >= 2) {
            int i4 = 1;
            while (i4 < i3 - i4) {
                i4 += i4;
            }
            for (int i5 = i4; i5 > 0; i5 >>>= 1) {
                int i6 = 0;
                for (int i7 = 0; i7 < i3 - i5; i7++) {
                    if ((i7 & i5) == 0) {
                        int i8 = i + i7;
                        int i9 = i8 + i5;
                        long j = jArr[i9];
                        long j2 = jArr[i8];
                        long j3 = (j ^ j2) & (-((j - j2) >>> 63));
                        jArr[i8] = j2 ^ j3;
                        jArr[i9] = jArr[i9] ^ j3;
                    }
                }
                for (int i10 = i4; i10 > i5; i10 >>>= 1) {
                    while (i6 < i3 - i10) {
                        if ((i6 & i5) == 0) {
                            int i11 = i + i6;
                            int i12 = i11 + i5;
                            long j4 = jArr[i12];
                            for (int i13 = i10; i13 > i5; i13 >>>= 1) {
                                int i14 = i11 + i13;
                                long j5 = jArr[i14];
                                long j6 = (-((j5 - j4) >>> 63)) & (j4 ^ j5);
                                j4 ^= j6;
                                jArr[i14] = j5 ^ j6;
                            }
                            jArr[i12] = j4;
                        }
                        i6++;
                    }
                }
            }
        }
    }

    private void synd(short[] sArr, short[] sArr2, short[] sArr3, byte[] bArr) {
        short s = sArr3[0];
        short eval = eval(sArr2, s);
        GF gf2 = this.gf;
        short gf_inv = (short) ((-((short) (bArr[0] & 1))) & gf2.gf_inv(gf2.gf_sq(eval)));
        sArr[0] = gf_inv;
        for (int i = 1; i < this.SYS_T * 2; i++) {
            gf_inv = this.gf.gf_mul(gf_inv, s);
            sArr[i] = gf_inv;
        }
        for (int i2 = 1; i2 < this.SYS_N; i2++) {
            short s2 = sArr3[i2];
            short eval2 = eval(sArr2, s2);
            GF gf3 = this.gf;
            short gf_inv2 = gf3.gf_inv(gf3.gf_sq(eval2));
            short gf_mul = this.gf.gf_mul(gf_inv2, (short) ((bArr[i2 / 8] >> (i2 % 8)) & 1));
            sArr[0] = (short) (sArr[0] ^ gf_mul);
            for (int i3 = 1; i3 < this.SYS_T * 2; i3++) {
                gf_mul = this.gf.gf_mul(gf_mul, s2);
                sArr[i3] = (short) (sArr[i3] ^ gf_mul);
            }
        }
    }

    private void syndrome(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        short[] sArr = new short[(this.SYS_N / 8)];
        int i = this.PK_NROWS % 8;
        for (int i2 = 0; i2 < this.SYND_BYTES; i2++) {
            bArr[i2] = 0;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < this.PK_NROWS; i4++) {
            for (int i5 = 0; i5 < this.SYS_N / 8; i5++) {
                sArr[i5] = 0;
            }
            int i6 = 0;
            while (true) {
                int i7 = this.PK_ROW_BYTES;
                if (i6 >= i7) {
                    break;
                }
                sArr[((this.SYS_N / 8) - i7) + i6] = (short) bArr2[i3 + i6];
                i6++;
            }
            if (this.usePadding) {
                for (int i8 = (this.SYS_N / 8) - 1; i8 >= (this.SYS_N / 8) - this.PK_ROW_BYTES; i8--) {
                    sArr[i8] = (short) ((((sArr[i8] & Http2CodecUtil.MAX_UNSIGNED_BYTE) << i) | ((sArr[i8 - 1] & Http2CodecUtil.MAX_UNSIGNED_BYTE) >>> (8 - i))) & 255);
                }
            }
            int i9 = i4 / 8;
            int i10 = i4 % 8;
            sArr[i9] = (short) (sArr[i9] | (1 << i10));
            byte b = 0;
            for (int i11 = 0; i11 < this.SYS_N / 8; i11++) {
                b = (byte) (b ^ (sArr[i11] & bArr3[i11]));
            }
            byte b2 = (byte) ((b >>> 4) ^ b);
            byte b3 = (byte) (b2 ^ (b2 >>> 2));
            bArr[i9] = (byte) ((((byte) (1 & ((byte) (b3 ^ (b3 >>> 1))))) << i10) | bArr[i9]);
            i3 += this.PK_ROW_BYTES;
        }
    }

    /* access modifiers changed from: package-private */
    public int check_c_padding(byte[] bArr) {
        return ((byte) ((((byte) (((byte) ((bArr[this.SYND_BYTES - 1] & 255) >>> (this.PK_NROWS % 8))) - 1)) & 255) >>> 7)) - 1;
    }

    /* access modifiers changed from: package-private */
    public int check_pk_padding(byte[] bArr) {
        byte b = 0;
        for (int i = 0; i < this.PK_NROWS; i++) {
            int i2 = this.PK_ROW_BYTES;
            b = (byte) (b | bArr[((i * i2) + i2) - 1]);
        }
        return ((byte) ((((byte) (((byte) ((b & 255) >>> (this.PK_NCOLS % 8))) - 1)) & 255) >>> 7)) - 1;
    }

    public byte[] decompress_private_key(byte[] bArr) {
        int i;
        byte[] bArr2 = bArr;
        byte[] bArr3 = new byte[getPrivateKeySize()];
        System.arraycopy(bArr2, 0, bArr3, 0, bArr2.length);
        int i2 = (this.SYS_N / 8) + ((1 << this.GFBITS) * 4) + this.IRR_BYTES;
        int i3 = i2 + 32;
        byte[] bArr4 = new byte[i3];
        SHAKEDigest sHAKEDigest = new SHAKEDigest(256);
        sHAKEDigest.update(SignedBytes.MAX_POWER_OF_TWO);
        sHAKEDigest.update(bArr2, 0, 32);
        sHAKEDigest.doFinal(bArr4, 0, i3);
        if (bArr2.length <= 40) {
            short[] sArr = new short[this.SYS_T];
            int i4 = this.IRR_BYTES;
            byte[] bArr5 = new byte[i4];
            int i5 = i2 - i4;
            for (int i6 = 0; i6 < this.SYS_T; i6++) {
                sArr[i6] = Utils.load_gf(bArr4, (i6 * 2) + i5, this.GFMASK);
            }
            generate_irr_poly(sArr);
            for (int i7 = 0; i7 < this.SYS_T; i7++) {
                Utils.store_gf(bArr5, i7 * 2, sArr[i7]);
            }
            System.arraycopy(bArr5, 0, bArr3, 40, this.IRR_BYTES);
        }
        int length = bArr2.length;
        int i8 = this.IRR_BYTES;
        if (length <= i8 + 40) {
            int i9 = this.GFBITS;
            int[] iArr = new int[(1 << i9)];
            short[] sArr2 = new short[(1 << i9)];
            int i10 = (i2 - i8) - ((1 << i9) * 4);
            int i11 = 0;
            while (true) {
                i = this.GFBITS;
                if (i11 >= (1 << i)) {
                    break;
                }
                iArr[i11] = Utils.load4(bArr4, (i11 * 4) + i10);
                i11++;
            }
            if (this.usePivots) {
                pk_gen((byte[]) null, bArr3, iArr, sArr2, new long[]{0});
            } else {
                int i12 = 1 << i;
                long[] jArr = new long[i12];
                for (int i13 = 0; i13 < (1 << this.GFBITS); i13++) {
                    long j = (long) iArr[i13];
                    jArr[i13] = j;
                    long j2 = j << 31;
                    jArr[i13] = j2;
                    long j3 = j2 | ((long) i13);
                    jArr[i13] = j3;
                    jArr[i13] = j3 & Long.MAX_VALUE;
                }
                sort64(jArr, 0, i12);
                for (int i14 = 0; i14 < (1 << this.GFBITS); i14++) {
                    sArr2[i14] = (short) ((int) (jArr[i14] & ((long) this.GFMASK)));
                }
            }
            int i15 = this.COND_BYTES;
            byte[] bArr6 = new byte[i15];
            int i16 = this.GFBITS;
            controlbitsfrompermutation(bArr6, sArr2, (long) i16, (long) (1 << i16));
            System.arraycopy(bArr6, 0, bArr3, this.IRR_BYTES + 40, i15);
        }
        int privateKeySize = getPrivateKeySize();
        int i17 = this.SYS_N;
        System.arraycopy(bArr4, 0, bArr3, privateKeySize - (i17 / 8), i17 / 8);
        return bArr3;
    }

    public byte[] generate_public_key_from_private_key(byte[] bArr) {
        byte[] bArr2 = new byte[getPublicKeySize()];
        int i = this.GFBITS;
        short[] sArr = new short[(1 << i)];
        long[] jArr = {0};
        int[] iArr = new int[(1 << i)];
        int i2 = (this.SYS_N / 8) + ((1 << i) * 4);
        byte[] bArr3 = new byte[i2];
        int i3 = ((i2 - 32) - this.IRR_BYTES) - ((1 << i) * 4);
        SHAKEDigest sHAKEDigest = new SHAKEDigest(256);
        sHAKEDigest.update(SignedBytes.MAX_POWER_OF_TWO);
        sHAKEDigest.update(bArr, 0, 32);
        sHAKEDigest.doFinal(bArr3, 0, i2);
        for (int i4 = 0; i4 < (1 << this.GFBITS); i4++) {
            iArr[i4] = Utils.load4(bArr3, (i4 * 4) + i3);
        }
        pk_gen(bArr2, bArr, iArr, sArr, jArr);
        return bArr2;
    }

    public int getCipherTextSize() {
        return this.SYND_BYTES;
    }

    public int getCondBytes() {
        return this.COND_BYTES;
    }

    public int getDefaultSessionKeySize() {
        return this.defaultKeySize;
    }

    public int getIrrBytes() {
        return this.IRR_BYTES;
    }

    public int getPrivateKeySize() {
        return this.COND_BYTES + this.IRR_BYTES + (this.SYS_N / 8) + 40;
    }

    public int getPublicKeySize() {
        if (!this.usePadding) {
            return (this.PK_NROWS * this.PK_NCOLS) / 8;
        }
        int i = this.PK_NROWS;
        return i * ((this.SYS_N / 8) - ((i - 1) / 8));
    }

    public int kem_dec(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        int i = this.SYS_N;
        byte[] bArr4 = new byte[(i / 8)];
        int i2 = (i / 8) + 1 + this.SYND_BYTES;
        byte[] bArr5 = new byte[i2];
        int check_c_padding = this.usePadding ? check_c_padding(bArr2) : 0;
        short decrypt = (short) (((short) (((short) (((short) ((byte) decrypt(bArr4, bArr3, bArr2))) - 1)) >> 8)) & Http2CodecUtil.MAX_UNSIGNED_BYTE);
        bArr5[0] = (byte) (decrypt & 1);
        int i3 = 0;
        while (i3 < this.SYS_N / 8) {
            int i4 = i3 + 1;
            bArr5[i4] = (byte) ((bArr4[i3] & decrypt) | ((decrypt ^ -1) & bArr3[i3 + 40 + this.IRR_BYTES + this.COND_BYTES]));
            i3 = i4;
        }
        for (int i5 = 0; i5 < this.SYND_BYTES; i5++) {
            bArr5[(this.SYS_N / 8) + 1 + i5] = bArr2[i5];
        }
        SHAKEDigest sHAKEDigest = new SHAKEDigest(256);
        sHAKEDigest.update(bArr5, 0, i2);
        sHAKEDigest.doFinal(bArr, 0, bArr.length);
        if (!this.usePadding) {
            return 0;
        }
        byte b = (byte) check_c_padding;
        for (int i6 = 0; i6 < bArr.length; i6++) {
            bArr[i6] = (byte) (bArr[i6] | b);
        }
        return check_c_padding;
    }

    public int kem_enc(byte[] bArr, byte[] bArr2, byte[] bArr3, SecureRandom secureRandom) {
        int i = this.SYS_N / 8;
        byte[] bArr4 = new byte[i];
        int check_pk_padding = this.usePadding ? check_pk_padding(bArr3) : 0;
        encrypt(bArr, bArr3, bArr4, secureRandom);
        SHAKEDigest sHAKEDigest = new SHAKEDigest(256);
        sHAKEDigest.update((byte) 1);
        sHAKEDigest.update(bArr4, 0, i);
        sHAKEDigest.update(bArr, 0, bArr.length);
        sHAKEDigest.doFinal(bArr2, 0, bArr2.length);
        if (!this.usePadding) {
            return 0;
        }
        byte b = (byte) (((byte) check_pk_padding) ^ 255);
        for (int i2 = 0; i2 < this.SYND_BYTES; i2++) {
            bArr[i2] = (byte) (bArr[i2] & b);
        }
        for (int i3 = 0; i3 < 32; i3++) {
            bArr2[i3] = (byte) (bArr2[i3] & b);
        }
        return check_pk_padding;
    }

    public void kem_keypair(byte[] bArr, byte[] bArr2, SecureRandom secureRandom) {
        SHAKEDigest sHAKEDigest;
        byte[] bArr3;
        int i;
        int i2;
        short[] sArr;
        int i3;
        long j;
        byte[] bArr4 = bArr2;
        int i4 = 32;
        byte[] bArr5 = new byte[32];
        int i5 = 0;
        byte[] bArr6 = {SignedBytes.MAX_POWER_OF_TWO};
        secureRandom.nextBytes(bArr5);
        int i6 = (this.SYS_N / 8) + ((1 << this.GFBITS) * 4) + (this.SYS_T * 2);
        int i7 = i6 + 32;
        byte[] bArr7 = new byte[i7];
        long[] jArr = {0};
        SHAKEDigest sHAKEDigest2 = new SHAKEDigest(256);
        byte[] bArr8 = bArr5;
        while (true) {
            sHAKEDigest2.update(bArr6, i5, 1);
            sHAKEDigest2.update(bArr5, i5, bArr5.length);
            sHAKEDigest2.doFinal(bArr7, i5, i7);
            byte[] copyOfRange = Arrays.copyOfRange(bArr7, i6, i6 + 32);
            System.arraycopy(bArr8, i5, bArr4, i5, i4);
            byte[] copyOfRange2 = Arrays.copyOfRange(copyOfRange, i5, i4);
            int i8 = this.SYS_T;
            short[] sArr2 = new short[i8];
            int i9 = i6 - (i8 * 2);
            for (int i10 = 0; i10 < this.SYS_T; i10++) {
                sArr2[i10] = Utils.load_gf(bArr7, (i10 * 2) + i9, this.GFMASK);
            }
            if (generate_irr_poly(sArr2) != -1) {
                for (int i11 = 0; i11 < this.SYS_T; i11++) {
                    Utils.store_gf(bArr4, 40 + (i11 * 2), sArr2[i11]);
                }
                int i12 = this.GFBITS;
                int[] iArr = new int[(1 << i12)];
                i = i9 - ((1 << i12) * 4);
                int i13 = 0;
                while (true) {
                    i2 = this.GFBITS;
                    if (i13 >= (1 << i2)) {
                        break;
                    }
                    iArr[i13] = Utils.load4(bArr7, (i13 * 4) + i);
                    i13++;
                }
                sArr = new short[(1 << i2)];
                bArr3 = copyOfRange;
                sHAKEDigest = sHAKEDigest2;
                if (pk_gen(bArr, bArr2, iArr, sArr, jArr) != -1) {
                    break;
                }
            } else {
                bArr3 = copyOfRange;
                sHAKEDigest = sHAKEDigest2;
            }
            bArr8 = copyOfRange2;
            bArr5 = bArr3;
            sHAKEDigest2 = sHAKEDigest;
            i4 = 32;
            i5 = 0;
        }
        int i14 = this.COND_BYTES;
        byte[] bArr9 = new byte[i14];
        int i15 = this.GFBITS;
        controlbitsfrompermutation(bArr9, sArr, (long) i15, (long) (1 << i15));
        System.arraycopy(bArr9, 0, bArr4, this.IRR_BYTES + 40, i14);
        int i16 = this.SYS_N;
        System.arraycopy(bArr7, i - (i16 / 8), bArr4, bArr4.length - (i16 / 8), i16 / 8);
        if (!this.usePivots) {
            j = 4294967295L;
            i3 = 32;
        } else {
            i3 = 32;
            j = jArr[0];
        }
        Utils.store8(bArr4, i3, j);
    }
}
