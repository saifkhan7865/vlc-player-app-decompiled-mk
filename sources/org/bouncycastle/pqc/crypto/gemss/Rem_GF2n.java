package org.bouncycastle.pqc.crypto.gemss;

abstract class Rem_GF2n {
    protected int ki;
    protected int ki64;
    protected long mask;

    public static class REM192_SPECIALIZED_TRINOMIAL_GF2X extends Rem_GF2n {
        private final int k3;
        private final int k364;
        private final int ki_k3;

        REM192_SPECIALIZED_TRINOMIAL_GF2X(int i, int i2, int i3, int i4, long j) {
            this.k3 = i;
            this.ki = i2;
            this.ki64 = i3;
            this.k364 = i4;
            this.mask = j;
            this.ki_k3 = i2 - i;
        }

        public void rem_gf2n(long[] jArr, int i, long[] jArr2) {
            long j = (jArr2[2] >>> this.ki) ^ (jArr2[3] << this.ki64);
            long j2 = (jArr2[3] >>> this.ki) ^ (jArr2[4] << this.ki64);
            long j3 = (jArr2[4] >>> this.ki) ^ (jArr2[5] << this.ki64);
            int i2 = this.k364;
            long j4 = (jArr2[1] ^ j2) ^ (j >>> i2);
            int i3 = this.k3;
            jArr[i + 1] = j4 ^ (j2 << i3);
            jArr[i + 2] = (((j2 >>> i2) ^ (jArr2[2] ^ j3)) ^ (j3 << i3)) & this.mask;
            long j5 = j ^ (j3 >>> this.ki_k3);
            jArr[i] = (j5 << this.k3) ^ (jArr2[0] ^ j5);
        }

        public void rem_gf2n_xor(long[] jArr, int i, long[] jArr2) {
            long j = (jArr2[2] >>> this.ki) ^ (jArr2[3] << this.ki64);
            long j2 = (jArr2[3] >>> this.ki) ^ (jArr2[4] << this.ki64);
            long j3 = (jArr2[4] >>> this.ki) ^ (jArr2[5] << this.ki64);
            int i2 = i + 1;
            long j4 = jArr[i2];
            int i3 = this.k364;
            long j5 = (jArr2[1] ^ j2) ^ (j >>> i3);
            int i4 = this.k3;
            jArr[i2] = j4 ^ (j5 ^ (j2 << i4));
            int i5 = i + 2;
            jArr[i5] = ((((j2 >>> i3) ^ (jArr2[2] ^ j3)) ^ (j3 << i4)) & this.mask) ^ jArr[i5];
            long j6 = j ^ (j3 >>> this.ki_k3);
            jArr[i] = ((j6 << this.k3) ^ (jArr2[0] ^ j6)) ^ jArr[i];
        }
    }

    public static class REM288_SPECIALIZED_TRINOMIAL_GF2X extends Rem_GF2n {
        private final int k3;
        private final int k364;
        private final int k364ki;
        private final int k3_ki;

        public REM288_SPECIALIZED_TRINOMIAL_GF2X(int i, int i2, int i3, int i4, long j) {
            this.k3 = i;
            this.ki = i2;
            this.ki64 = i3;
            this.k364 = i4;
            this.mask = j;
            this.k364ki = i4 + i2;
            this.k3_ki = i - i2;
        }

        public void rem_gf2n(long[] jArr, int i, long[] jArr2) {
            long j = (jArr2[5] >>> this.ki) ^ (jArr2[6] << this.ki64);
            long j2 = (jArr2[6] >>> this.ki) ^ (jArr2[7] << this.ki64);
            jArr[i + 2] = ((jArr2[2] ^ j2) ^ (j >>> this.k364)) ^ (j2 << this.k3);
            long j3 = (jArr2[7] >>> this.ki) ^ (jArr2[8] << this.ki64);
            jArr[i + 3] = ((j2 >>> this.k364) ^ (jArr2[3] ^ j3)) ^ (j3 << this.k3);
            long j4 = jArr2[8] >>> this.ki;
            long j5 = (((jArr2[4] >>> this.ki) ^ (jArr2[5] << this.ki64)) ^ (j3 >>> this.k364ki)) ^ (j4 << this.k3_ki);
            long j6 = j3 >>> this.k364;
            jArr[i + 4] = ((j4 << this.k3) ^ (j6 ^ (jArr2[4] ^ j4))) & this.mask;
            int i2 = this.k3;
            jArr[i] = (jArr2[0] ^ j5) ^ (j5 << i2);
            jArr[i + 1] = ((j << i2) ^ (jArr2[1] ^ j)) ^ (j5 >>> this.k364);
        }

        public void rem_gf2n_xor(long[] jArr, int i, long[] jArr2) {
            long j = (jArr2[5] >>> this.ki) ^ (jArr2[6] << this.ki64);
            long j2 = (jArr2[6] >>> this.ki) ^ (jArr2[7] << this.ki64);
            int i2 = i + 2;
            jArr[i2] = jArr[i2] ^ (((jArr2[2] ^ j2) ^ (j >>> this.k364)) ^ (j2 << this.k3));
            long j3 = (jArr2[7] >>> this.ki) ^ (jArr2[8] << this.ki64);
            int i3 = i + 3;
            jArr[i3] = (((j2 >>> this.k364) ^ (jArr2[3] ^ j3)) ^ (j3 << this.k3)) ^ jArr[i3];
            long j4 = jArr2[8] >>> this.ki;
            int i4 = i + 4;
            jArr[i4] = jArr[i4] ^ ((((jArr2[4] ^ j4) ^ (j3 >>> this.k364)) ^ (j4 << this.k3)) & this.mask);
            long j5 = j3 >>> this.k364ki;
            long j6 = (j4 << this.k3_ki) ^ (j5 ^ ((jArr2[4] >>> this.ki) ^ (jArr2[5] << this.ki64)));
            long j7 = jArr[i];
            int i5 = this.k3;
            jArr[i] = j7 ^ ((jArr2[0] ^ j6) ^ (j6 << i5));
            int i6 = i + 1;
            jArr[i6] = (((j << i5) ^ (jArr2[1] ^ j)) ^ (j6 >>> this.k364)) ^ jArr[i6];
        }
    }

    public static class REM384_SPECIALIZED358_TRINOMIAL_GF2X extends Rem_GF2n {
        private final int k3;
        private final int k364;
        private final int k364ki;
        private final int k3_ki;

        public REM384_SPECIALIZED358_TRINOMIAL_GF2X(int i, int i2, int i3, int i4, long j) {
            this.k3 = i;
            this.ki = i2;
            this.ki64 = i3;
            this.k364 = i4;
            this.mask = j;
            this.k364ki = i4 + i2;
            this.k3_ki = i - i2;
        }

        public void rem_gf2n(long[] jArr, int i, long[] jArr2) {
            long j = (jArr2[6] >>> this.ki) ^ (jArr2[7] << this.ki64);
            long j2 = (jArr2[7] >>> this.ki) ^ (jArr2[8] << this.ki64);
            jArr[i + 2] = ((jArr2[2] ^ j2) ^ (j >>> this.k364)) ^ (j2 << this.k3);
            long j3 = (jArr2[8] >>> this.ki) ^ (jArr2[9] << this.ki64);
            jArr[i + 3] = ((j2 >>> this.k364) ^ (jArr2[3] ^ j3)) ^ (j3 << this.k3);
            long j4 = (jArr2[9] >>> this.ki) ^ (jArr2[10] << this.ki64);
            jArr[i + 4] = ((j3 >>> this.k364) ^ (jArr2[4] ^ j4)) ^ (j4 << this.k3);
            long j5 = (jArr2[10] >>> this.ki) ^ (jArr2[11] << this.ki64);
            long j6 = (((jArr2[5] >>> this.ki) ^ (jArr2[6] << this.ki64)) ^ (j4 >>> this.k364ki)) ^ (j5 << this.k3_ki);
            jArr[i + 5] = ((j4 >>> this.k364) ^ (j5 ^ jArr2[5])) & this.mask;
            int i2 = this.k3;
            jArr[i] = (jArr2[0] ^ j6) ^ (j6 << i2);
            jArr[i + 1] = (j << i2) ^ ((jArr2[1] ^ j) ^ (j6 >>> this.k364));
        }

        public void rem_gf2n_xor(long[] jArr, int i, long[] jArr2) {
            long j = (jArr2[6] >>> this.ki) ^ (jArr2[7] << this.ki64);
            long j2 = (jArr2[7] >>> this.ki) ^ (jArr2[8] << this.ki64);
            int i2 = i + 2;
            jArr[i2] = jArr[i2] ^ (((jArr2[2] ^ j2) ^ (j >>> this.k364)) ^ (j2 << this.k3));
            long j3 = (jArr2[8] >>> this.ki) ^ (jArr2[9] << this.ki64);
            int i3 = i + 3;
            jArr[i3] = (((j2 >>> this.k364) ^ (jArr2[3] ^ j3)) ^ (j3 << this.k3)) ^ jArr[i3];
            long j4 = (jArr2[9] >>> this.ki) ^ (jArr2[10] << this.ki64);
            int i4 = i + 4;
            jArr[i4] = (((j3 >>> this.k364) ^ (jArr2[4] ^ j4)) ^ (j4 << this.k3)) ^ jArr[i4];
            long j5 = (jArr2[10] >>> this.ki) ^ (jArr2[11] << this.ki64);
            int i5 = i + 5;
            jArr[i5] = jArr[i5] ^ (((jArr2[5] ^ j5) ^ (j4 >>> this.k364)) & this.mask);
            long j6 = ((j4 >>> this.k364ki) ^ ((jArr2[5] >>> this.ki) ^ (jArr2[6] << this.ki64))) ^ (j5 << this.k3_ki);
            long j7 = jArr[i];
            int i6 = this.k3;
            jArr[i] = j7 ^ ((jArr2[0] ^ j6) ^ (j6 << i6));
            int i7 = i + 1;
            long j8 = j << i6;
            jArr[i7] = (j8 ^ ((j6 >>> this.k364) ^ (jArr2[1] ^ j))) ^ jArr[i7];
        }
    }

    public static class REM384_SPECIALIZED_TRINOMIAL_GF2X extends Rem_GF2n {
        private final int k3;
        private final int k364;
        private final int k364ki;
        private final int k3_ki;

        public REM384_SPECIALIZED_TRINOMIAL_GF2X(int i, int i2, int i3, int i4, long j) {
            this.k3 = i;
            this.ki = i2;
            this.ki64 = i3;
            this.k364 = i4;
            this.mask = j;
            this.k364ki = i4 + i2;
            this.k3_ki = i - i2;
        }

        public void rem_gf2n(long[] jArr, int i, long[] jArr2) {
            long j = (jArr2[7] >>> this.ki) ^ (jArr2[8] << this.ki64);
            long j2 = (jArr2[8] >>> this.ki) ^ (jArr2[9] << this.ki64);
            long j3 = (jArr2[9] >>> this.ki) ^ (jArr2[10] << this.ki64);
            long j4 = (jArr2[10] >>> this.ki) ^ (jArr2[11] << this.ki64);
            long j5 = (((jArr2[5] >>> this.ki) ^ (jArr2[6] << this.ki64)) ^ (j2 >>> this.k364ki)) ^ (j3 << this.k3_ki);
            long j6 = (((jArr2[6] >>> this.ki) ^ (jArr2[7] << this.ki64)) ^ (j3 >>> this.k364ki)) ^ (j4 << this.k3_ki);
            jArr[i] = jArr2[0] ^ j5;
            int i2 = this.k3;
            jArr[i + 1] = (jArr2[1] ^ j6) ^ (j5 << i2);
            int i3 = this.k364;
            jArr[i + 2] = ((jArr2[2] ^ j) ^ (j5 >>> i3)) ^ (j6 << i2);
            jArr[i + 3] = ((jArr2[3] ^ j2) ^ (j6 >>> i3)) ^ (j << i2);
            jArr[i + 4] = ((j >>> i3) ^ (j3 ^ jArr2[4])) ^ (j2 << i2);
            jArr[i + 5] = ((j2 >>> i3) ^ (jArr2[5] ^ j4)) & this.mask;
        }

        public void rem_gf2n_xor(long[] jArr, int i, long[] jArr2) {
            long j = (jArr2[7] >>> this.ki) ^ (jArr2[8] << this.ki64);
            long j2 = (jArr2[8] >>> this.ki) ^ (jArr2[9] << this.ki64);
            long j3 = (jArr2[9] >>> this.ki) ^ (jArr2[10] << this.ki64);
            long j4 = (jArr2[10] >>> this.ki) ^ (jArr2[11] << this.ki64);
            long j5 = (((jArr2[5] >>> this.ki) ^ (jArr2[6] << this.ki64)) ^ (j2 >>> this.k364ki)) ^ (j3 << this.k3_ki);
            long j6 = (((jArr2[6] >>> this.ki) ^ (jArr2[7] << this.ki64)) ^ (j3 >>> this.k364ki)) ^ (j4 << this.k3_ki);
            jArr[i] = jArr[i] ^ (jArr2[0] ^ j5);
            int i2 = i + 1;
            long j7 = jArr[i2];
            int i3 = this.k3;
            jArr[i2] = j7 ^ ((jArr2[1] ^ j6) ^ (j5 << i3));
            int i4 = i + 2;
            long j8 = jArr[i4];
            int i5 = this.k364;
            jArr[i4] = j8 ^ (((jArr2[2] ^ j) ^ (j5 >>> i5)) ^ (j6 << i3));
            int i6 = i + 3;
            jArr[i6] = jArr[i6] ^ (((jArr2[3] ^ j2) ^ (j6 >>> i5)) ^ (j << i3));
            int i7 = i + 4;
            jArr[i7] = (((j >>> i5) ^ (j3 ^ jArr2[4])) ^ (j2 << i3)) ^ jArr[i7];
            int i8 = i + 5;
            jArr[i8] = (((j2 >>> i5) ^ (j4 ^ jArr2[5])) & this.mask) ^ jArr[i8];
        }
    }

    public static class REM384_TRINOMIAL_GF2X extends Rem_GF2n {
        private final int k3;
        private final int k364;
        private final int ki_k3;

        public REM384_TRINOMIAL_GF2X(int i, int i2, int i3, int i4, long j) {
            this.k3 = i;
            this.ki = i2;
            this.ki64 = i3;
            this.k364 = i4;
            this.mask = j;
            this.ki_k3 = i2 - i;
        }

        public void rem_gf2n(long[] jArr, int i, long[] jArr2) {
            long j = (jArr2[5] >>> this.ki) ^ (jArr2[6] << this.ki64);
            long j2 = (jArr2[6] >>> this.ki) ^ (jArr2[7] << this.ki64);
            long j3 = (jArr2[7] >>> this.ki) ^ (jArr2[8] << this.ki64);
            long j4 = (jArr2[8] >>> this.ki) ^ (jArr2[9] << this.ki64);
            long j5 = (jArr2[9] >>> this.ki) ^ (jArr2[10] << this.ki64);
            long j6 = (jArr2[10] >>> this.ki) ^ (jArr2[11] << this.ki64);
            long j7 = (j6 >>> this.ki_k3) ^ j;
            int i2 = this.k3;
            jArr[i] = (jArr2[0] ^ j7) ^ (j7 << i2);
            int i3 = this.k364;
            jArr[i + 1] = ((jArr2[1] ^ j2) ^ (j >>> i3)) ^ (j2 << i2);
            jArr[i + 2] = ((jArr2[2] ^ j3) ^ (j2 >>> i3)) ^ (j3 << i2);
            jArr[i + 3] = ((jArr2[3] ^ j4) ^ (j3 >>> i3)) ^ (j4 << i2);
            jArr[i + 4] = ((jArr2[4] ^ j5) ^ (j4 >>> i3)) ^ (j5 << i2);
            jArr[i + 5] = ((j6 << i2) ^ ((jArr2[5] ^ j6) ^ (j5 >>> i3))) & this.mask;
        }

        public void rem_gf2n_xor(long[] jArr, int i, long[] jArr2) {
            long j = (jArr2[5] >>> this.ki) ^ (jArr2[6] << this.ki64);
            long j2 = (jArr2[6] >>> this.ki) ^ (jArr2[7] << this.ki64);
            long j3 = (jArr2[7] >>> this.ki) ^ (jArr2[8] << this.ki64);
            long j4 = (jArr2[8] >>> this.ki) ^ (jArr2[9] << this.ki64);
            long j5 = (jArr2[9] >>> this.ki) ^ (jArr2[10] << this.ki64);
            long j6 = (jArr2[10] >>> this.ki) ^ (jArr2[11] << this.ki64);
            long j7 = (j6 >>> this.ki_k3) ^ j;
            long j8 = jArr[i];
            int i2 = this.k3;
            jArr[i] = j8 ^ ((jArr2[0] ^ j7) ^ (j7 << i2));
            int i3 = i + 1;
            long j9 = jArr[i3];
            int i4 = this.k364;
            jArr[i3] = j9 ^ (((jArr2[1] ^ j2) ^ (j >>> i4)) ^ (j2 << i2));
            int i5 = i + 2;
            jArr[i5] = jArr[i5] ^ (((jArr2[2] ^ j3) ^ (j2 >>> i4)) ^ (j3 << i2));
            int i6 = i + 3;
            jArr[i6] = jArr[i6] ^ (((jArr2[3] ^ j4) ^ (j3 >>> i4)) ^ (j4 << i2));
            int i7 = i + 4;
            jArr[i7] = jArr[i7] ^ (((jArr2[4] ^ j5) ^ (j4 >>> i4)) ^ (j5 << i2));
            int i8 = i + 5;
            jArr[i8] = (((j6 << i2) ^ ((jArr2[5] ^ j6) ^ (j5 >>> i4))) & this.mask) ^ jArr[i8];
        }
    }

    public static class REM402_SPECIALIZED_TRINOMIAL_GF2X extends Rem_GF2n {
        private final int k3;
        private final int k364;

        public REM402_SPECIALIZED_TRINOMIAL_GF2X(int i, int i2, int i3, int i4, long j) {
            this.k3 = i;
            this.ki = i2;
            this.ki64 = i3;
            this.k364 = i4;
            this.mask = j;
        }

        public void rem_gf2n(long[] jArr, int i, long[] jArr2) {
            long j = (jArr2[9] >>> this.ki) ^ (jArr2[10] << this.ki64);
            long j2 = (jArr2[10] >>> this.ki) ^ (jArr2[11] << this.ki64);
            long j3 = (jArr2[11] >>> this.ki) ^ (jArr2[12] << this.ki64);
            long j4 = jArr2[12] >>> this.ki;
            long j5 = (((j >>> 39) ^ (j2 << 25)) ^ (jArr2[6] >>> this.ki)) ^ (jArr2[7] << this.ki64);
            long j6 = (((j2 >>> 39) ^ (j3 << 25)) ^ (jArr2[7] >>> this.ki)) ^ (jArr2[8] << this.ki64);
            long j7 = (((j3 >>> 39) ^ (j4 << 25)) ^ (jArr2[8] >>> this.ki)) ^ (jArr2[9] << this.ki64);
            jArr[i] = jArr2[0] ^ j5;
            jArr[i + 1] = jArr2[1] ^ j6;
            int i2 = this.k3;
            jArr[i + 2] = (jArr2[2] ^ j7) ^ (j5 << i2);
            long j8 = j4;
            int i3 = this.k364;
            jArr[i + 3] = ((jArr2[3] ^ j) ^ (j5 >>> i3)) ^ (j6 << i2);
            jArr[i + 4] = ((j2 ^ jArr2[4]) ^ (j6 >>> i3)) ^ (j7 << i2);
            jArr[i + 5] = ((jArr2[5] ^ j3) ^ (j7 >>> i3)) ^ (j << i2);
            jArr[i + 6] = ((j >>> i3) ^ (jArr2[6] ^ j8)) & this.mask;
        }

        public void rem_gf2n_xor(long[] jArr, int i, long[] jArr2) {
            long j = (jArr2[9] >>> this.ki) ^ (jArr2[10] << this.ki64);
            long j2 = (jArr2[10] >>> this.ki) ^ (jArr2[11] << this.ki64);
            long j3 = (jArr2[11] >>> this.ki) ^ (jArr2[12] << this.ki64);
            long j4 = jArr2[12] >>> this.ki;
            long j5 = (((j >>> 39) ^ (j2 << 25)) ^ (jArr2[6] >>> this.ki)) ^ (jArr2[7] << this.ki64);
            long j6 = (((j2 >>> 39) ^ (j3 << 25)) ^ (jArr2[7] >>> this.ki)) ^ (jArr2[8] << this.ki64);
            long j7 = (((j3 >>> 39) ^ (j4 << 25)) ^ (jArr2[8] >>> this.ki)) ^ (jArr2[9] << this.ki64);
            jArr[i] = jArr[i] ^ (jArr2[0] ^ j5);
            int i2 = i + 1;
            jArr[i2] = jArr[i2] ^ (jArr2[1] ^ j6);
            int i3 = i + 2;
            long j8 = jArr[i3];
            int i4 = this.k3;
            jArr[i3] = j8 ^ ((jArr2[2] ^ j7) ^ (j5 << i4));
            int i5 = i + 3;
            long j9 = jArr[i5];
            long j10 = j4;
            int i6 = this.k364;
            jArr[i5] = j9 ^ (((jArr2[3] ^ j) ^ (j5 >>> i6)) ^ (j6 << i4));
            int i7 = i + 4;
            jArr[i7] = (((jArr2[4] ^ j2) ^ (j6 >>> i6)) ^ (j7 << i4)) ^ jArr[i7];
            int i8 = i + 5;
            jArr[i8] = jArr[i8] ^ (((j3 ^ jArr2[5]) ^ (j7 >>> i6)) ^ (j << i4));
            int i9 = i + 6;
            jArr[i9] = (((j >>> i6) ^ (jArr2[6] ^ j10)) & this.mask) ^ jArr[i9];
        }
    }

    public static class REM544_PENTANOMIAL_GF2X extends Rem_GF2n {
        private final int k1;
        private final int k164;
        private final int k2;
        private final int k264;
        private final int k3;
        private final int k364;
        private final int ki_k1;
        private final int ki_k2;
        private final int ki_k3;

        public REM544_PENTANOMIAL_GF2X(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, long j) {
            this.k1 = i;
            this.k2 = i2;
            this.k3 = i3;
            this.ki = i4;
            this.ki64 = i5;
            this.k164 = i6;
            this.k264 = i7;
            this.k364 = i8;
            this.mask = j;
            this.ki_k3 = i4 - i3;
            this.ki_k2 = i4 - i2;
            this.ki_k1 = i4 - i;
        }

        /* JADX WARNING: type inference failed for: r19v0, types: [long[]] */
        /* JADX WARNING: Unknown variable types count: 1 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void rem_gf2n(long[] r19, int r20, long[] r21) {
            /*
                r18 = this;
                r0 = r18
                r1 = 16
                r2 = r21[r1]
                int r4 = r0.ki
                long r2 = r2 >>> r4
                r4 = 8
                r5 = r21[r4]
                int r7 = r0.ki
                long r5 = r5 >>> r7
                r7 = 9
                r8 = r21[r7]
                int r10 = r0.ki64
                long r8 = r8 << r10
                long r5 = r5 ^ r8
                r7 = r21[r7]
                int r9 = r0.ki
                long r7 = r7 >>> r9
                r9 = 10
                r10 = r21[r9]
                int r12 = r0.ki64
                long r10 = r10 << r12
                long r7 = r7 ^ r10
                int r10 = r20 + 1
                r11 = 1
                r11 = r21[r11]
                long r11 = r11 ^ r7
                int r13 = r0.k164
                long r13 = r5 >>> r13
                long r11 = r11 ^ r13
                int r13 = r0.k1
                long r14 = r7 << r13
                long r11 = r11 ^ r14
                int r14 = r0.k264
                long r14 = r5 >>> r14
                long r11 = r11 ^ r14
                int r14 = r0.k2
                long r15 = r7 << r14
                long r11 = r11 ^ r15
                int r15 = r0.k364
                long r15 = r5 >>> r15
                long r11 = r11 ^ r15
                int r15 = r0.k3
                long r16 = r7 << r15
                long r11 = r11 ^ r16
                r19[r10] = r11
                int r10 = r0.ki_k3
                long r10 = r2 >>> r10
                int r12 = r0.ki_k2
                long r16 = r2 >>> r12
                long r10 = r10 ^ r16
                int r12 = r0.ki_k1
                long r16 = r2 >>> r12
                long r10 = r10 ^ r16
                long r5 = r5 ^ r10
                r10 = 0
                r10 = r21[r10]
                long r10 = r10 ^ r5
                long r12 = r5 << r13
                long r10 = r10 ^ r12
                long r12 = r5 << r14
                long r10 = r10 ^ r12
                long r5 = r5 << r15
                long r5 = r5 ^ r10
                r19[r20] = r5
                r5 = r21[r9]
                int r9 = r0.ki
                long r5 = r5 >>> r9
                r9 = 11
                r10 = r21[r9]
                int r12 = r0.ki64
                long r10 = r10 << r12
                long r5 = r5 ^ r10
                int r10 = r20 + 2
                r11 = 2
                r11 = r21[r11]
                long r11 = r11 ^ r5
                int r13 = r0.k164
                long r13 = r7 >>> r13
                long r11 = r11 ^ r13
                int r13 = r0.k1
                long r13 = r5 << r13
                long r11 = r11 ^ r13
                int r13 = r0.k264
                long r13 = r7 >>> r13
                long r11 = r11 ^ r13
                int r13 = r0.k2
                long r13 = r5 << r13
                long r11 = r11 ^ r13
                int r13 = r0.k364
                long r7 = r7 >>> r13
                long r7 = r7 ^ r11
                int r11 = r0.k3
                long r11 = r5 << r11
                long r7 = r7 ^ r11
                r19[r10] = r7
                r7 = r21[r9]
                int r9 = r0.ki
                long r7 = r7 >>> r9
                r9 = 12
                r10 = r21[r9]
                int r12 = r0.ki64
                long r10 = r10 << r12
                long r7 = r7 ^ r10
                int r10 = r20 + 3
                r11 = 3
                r11 = r21[r11]
                long r11 = r11 ^ r7
                int r13 = r0.k164
                long r13 = r5 >>> r13
                long r11 = r11 ^ r13
                int r13 = r0.k1
                long r13 = r7 << r13
                long r11 = r11 ^ r13
                int r13 = r0.k264
                long r13 = r5 >>> r13
                long r11 = r11 ^ r13
                int r13 = r0.k2
                long r13 = r7 << r13
                long r11 = r11 ^ r13
                int r13 = r0.k364
                long r5 = r5 >>> r13
                long r5 = r5 ^ r11
                int r11 = r0.k3
                long r11 = r7 << r11
                long r5 = r5 ^ r11
                r19[r10] = r5
                r5 = r21[r9]
                int r9 = r0.ki
                long r5 = r5 >>> r9
                r9 = 13
                r10 = r21[r9]
                int r12 = r0.ki64
                long r10 = r10 << r12
                long r5 = r5 ^ r10
                int r10 = r20 + 4
                r11 = 4
                r11 = r21[r11]
                long r11 = r11 ^ r5
                int r13 = r0.k164
                long r13 = r7 >>> r13
                long r11 = r11 ^ r13
                int r13 = r0.k1
                long r13 = r5 << r13
                long r11 = r11 ^ r13
                int r13 = r0.k264
                long r13 = r7 >>> r13
                long r11 = r11 ^ r13
                int r13 = r0.k2
                long r13 = r5 << r13
                long r11 = r11 ^ r13
                int r13 = r0.k364
                long r7 = r7 >>> r13
                long r7 = r7 ^ r11
                int r11 = r0.k3
                long r11 = r5 << r11
                long r7 = r7 ^ r11
                r19[r10] = r7
                r7 = r21[r9]
                int r9 = r0.ki
                long r7 = r7 >>> r9
                r9 = 14
                r10 = r21[r9]
                int r12 = r0.ki64
                long r10 = r10 << r12
                long r7 = r7 ^ r10
                int r10 = r20 + 5
                r11 = 5
                r11 = r21[r11]
                long r11 = r11 ^ r7
                int r13 = r0.k164
                long r13 = r5 >>> r13
                long r11 = r11 ^ r13
                int r13 = r0.k1
                long r13 = r7 << r13
                long r11 = r11 ^ r13
                int r13 = r0.k264
                long r13 = r5 >>> r13
                long r11 = r11 ^ r13
                int r13 = r0.k2
                long r13 = r7 << r13
                long r11 = r11 ^ r13
                int r13 = r0.k364
                long r5 = r5 >>> r13
                long r5 = r5 ^ r11
                int r11 = r0.k3
                long r11 = r7 << r11
                long r5 = r5 ^ r11
                r19[r10] = r5
                r5 = r21[r9]
                int r9 = r0.ki
                long r5 = r5 >>> r9
                r9 = 15
                r10 = r21[r9]
                int r12 = r0.ki64
                long r10 = r10 << r12
                long r5 = r5 ^ r10
                int r10 = r20 + 6
                r11 = 6
                r11 = r21[r11]
                long r11 = r11 ^ r5
                int r13 = r0.k164
                long r13 = r7 >>> r13
                long r11 = r11 ^ r13
                int r13 = r0.k1
                long r13 = r5 << r13
                long r11 = r11 ^ r13
                int r13 = r0.k264
                long r13 = r7 >>> r13
                long r11 = r11 ^ r13
                int r13 = r0.k2
                long r13 = r5 << r13
                long r11 = r11 ^ r13
                int r13 = r0.k364
                long r7 = r7 >>> r13
                long r7 = r7 ^ r11
                int r11 = r0.k3
                long r11 = r5 << r11
                long r7 = r7 ^ r11
                r19[r10] = r7
                r7 = r21[r9]
                int r9 = r0.ki
                long r7 = r7 >>> r9
                r9 = r21[r1]
                int r1 = r0.ki64
                long r9 = r9 << r1
                long r7 = r7 ^ r9
                int r1 = r20 + 7
                r9 = 7
                r9 = r21[r9]
                long r9 = r9 ^ r7
                int r11 = r0.k164
                long r12 = r5 >>> r11
                long r9 = r9 ^ r12
                int r12 = r0.k1
                long r13 = r7 << r12
                long r9 = r9 ^ r13
                int r13 = r0.k264
                long r14 = r5 >>> r13
                long r9 = r9 ^ r14
                int r14 = r0.k2
                long r15 = r7 << r14
                long r9 = r9 ^ r15
                int r15 = r0.k364
                long r5 = r5 >>> r15
                long r5 = r5 ^ r9
                int r9 = r0.k3
                long r16 = r7 << r9
                long r5 = r5 ^ r16
                r19[r1] = r5
                int r1 = r20 + 8
                r4 = r21[r4]
                long r4 = r4 ^ r2
                long r10 = r7 >>> r11
                long r4 = r4 ^ r10
                long r10 = r2 << r12
                long r4 = r4 ^ r10
                long r10 = r7 >>> r13
                long r4 = r4 ^ r10
                long r10 = r2 << r14
                long r4 = r4 ^ r10
                long r6 = r7 >>> r15
                long r4 = r4 ^ r6
                long r2 = r2 << r9
                long r2 = r2 ^ r4
                long r4 = r0.mask
                long r2 = r2 & r4
                r19[r1] = r2
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.pqc.crypto.gemss.Rem_GF2n.REM544_PENTANOMIAL_GF2X.rem_gf2n(long[], int, long[]):void");
        }

        public void rem_gf2n_xor(long[] jArr, int i, long[] jArr2) {
            long j = jArr2[16] >>> this.ki;
            long j2 = (jArr2[8] >>> this.ki) ^ (jArr2[9] << this.ki64);
            long j3 = (jArr2[9] >>> this.ki) ^ (jArr2[10] << this.ki64);
            int i2 = i + 1;
            long j4 = jArr[i2];
            long j5 = (jArr2[1] ^ j3) ^ (j2 >>> this.k164);
            int i3 = this.k1;
            int i4 = this.k2;
            int i5 = this.k3;
            jArr[i2] = j4 ^ (((((j5 ^ (j3 << i3)) ^ (j2 >>> this.k264)) ^ (j3 << i4)) ^ (j2 >>> this.k364)) ^ (j3 << i5));
            long j6 = j2 ^ (((j >>> this.ki_k3) ^ (j >>> this.ki_k2)) ^ (j >>> this.ki_k1));
            jArr[i] = ((j6 << i5) ^ (((jArr2[0] ^ j6) ^ (j6 << i3)) ^ (j6 << i4))) ^ jArr[i];
            long j7 = (jArr2[10] >>> this.ki) ^ (jArr2[11] << this.ki64);
            int i6 = i + 2;
            jArr[i6] = (((j3 >>> this.k364) ^ (((((jArr2[2] ^ j7) ^ (j3 >>> this.k164)) ^ (j7 << this.k1)) ^ (j3 >>> this.k264)) ^ (j7 << this.k2))) ^ (j7 << this.k3)) ^ jArr[i6];
            long j8 = (jArr2[11] >>> this.ki) ^ (jArr2[12] << this.ki64);
            int i7 = i + 3;
            jArr[i7] = (((j7 >>> this.k364) ^ (((((jArr2[3] ^ j8) ^ (j7 >>> this.k164)) ^ (j8 << this.k1)) ^ (j7 >>> this.k264)) ^ (j8 << this.k2))) ^ (j8 << this.k3)) ^ jArr[i7];
            long j9 = (jArr2[12] >>> this.ki) ^ (jArr2[13] << this.ki64);
            int i8 = i + 4;
            jArr[i8] = (((j8 >>> this.k364) ^ (((((jArr2[4] ^ j9) ^ (j8 >>> this.k164)) ^ (j9 << this.k1)) ^ (j8 >>> this.k264)) ^ (j9 << this.k2))) ^ (j9 << this.k3)) ^ jArr[i8];
            long j10 = (jArr2[13] >>> this.ki) ^ (jArr2[14] << this.ki64);
            int i9 = i + 5;
            jArr[i9] = (((j9 >>> this.k364) ^ (((((jArr2[5] ^ j10) ^ (j9 >>> this.k164)) ^ (j10 << this.k1)) ^ (j9 >>> this.k264)) ^ (j10 << this.k2))) ^ (j10 << this.k3)) ^ jArr[i9];
            long j11 = (jArr2[14] >>> this.ki) ^ (jArr2[15] << this.ki64);
            int i10 = i + 6;
            jArr[i10] = (((j10 >>> this.k364) ^ (((((jArr2[6] ^ j11) ^ (j10 >>> this.k164)) ^ (j11 << this.k1)) ^ (j10 >>> this.k264)) ^ (j11 << this.k2))) ^ (j11 << this.k3)) ^ jArr[i10];
            long j12 = (jArr2[15] >>> this.ki) ^ (jArr2[16] << this.ki64);
            int i11 = i + 7;
            long j13 = jArr[i11];
            int i12 = this.k164;
            long j14 = (jArr2[7] ^ j12) ^ (j11 >>> i12);
            int i13 = this.k1;
            int i14 = this.k264;
            int i15 = this.k2;
            long j15 = ((j14 ^ (j12 << i13)) ^ (j11 >>> i14)) ^ (j12 << i15);
            int i16 = i15;
            int i17 = this.k364;
            long j16 = (j11 >>> i17) ^ j15;
            int i18 = this.k3;
            jArr[i11] = (j16 ^ (j12 << i18)) ^ j13;
            int i19 = i + 8;
            long j17 = j << i18;
            jArr[i19] = ((j17 ^ ((j12 >>> i17) ^ (((((jArr2[8] ^ j) ^ (j12 >>> i12)) ^ (j << i13)) ^ (j12 >>> i14)) ^ (j << i16)))) & this.mask) ^ jArr[i19];
        }
    }

    public static class REM544_PENTANOMIAL_K3_IS_128_GF2X extends Rem_GF2n {
        private final int k1;
        private final int k164;
        private final int k2;
        private final int k264;

        public REM544_PENTANOMIAL_K3_IS_128_GF2X(int i, int i2, int i3, int i4, int i5, int i6, long j) {
            this.k1 = i;
            this.k2 = i2;
            this.ki = i3;
            this.ki64 = i4;
            this.k164 = i5;
            this.k264 = i6;
            this.mask = j;
        }

        public void rem_gf2n(long[] jArr, int i, long[] jArr2) {
            long j = (jArr2[10] >>> this.ki) ^ (jArr2[11] << this.ki64);
            long j2 = (jArr2[11] >>> this.ki) ^ (jArr2[12] << this.ki64);
            long j3 = (jArr2[12] >>> this.ki) ^ (jArr2[13] << this.ki64);
            jArr[i + 4] = (((((jArr2[4] ^ j3) ^ j) ^ (j2 >>> this.k164)) ^ (j3 << this.k1)) ^ (j2 >>> this.k264)) ^ (j3 << this.k2);
            long j4 = (jArr2[13] >>> this.ki) ^ (jArr2[14] << this.ki64);
            jArr[i + 5] = (((((jArr2[5] ^ j4) ^ j2) ^ (j3 >>> this.k164)) ^ (j4 << this.k1)) ^ (j3 >>> this.k264)) ^ (j4 << this.k2);
            long j5 = (jArr2[14] >>> this.ki) ^ (jArr2[15] << this.ki64);
            jArr[i + 6] = ((((j3 ^ (jArr2[6] ^ j5)) ^ (j4 >>> this.k164)) ^ (j5 << this.k1)) ^ (j4 >>> this.k264)) ^ (j5 << this.k2);
            long j6 = (jArr2[15] >>> this.ki) ^ (jArr2[16] << this.ki64);
            jArr[i + 7] = ((((j4 ^ (jArr2[7] ^ j6)) ^ (j5 >>> this.k164)) ^ (j6 << this.k1)) ^ (j5 >>> this.k264)) ^ (j6 << this.k2);
            long j7 = jArr2[16] >>> this.ki;
            long j8 = j2;
            jArr[i + 8] = this.mask & ((((((jArr2[8] ^ j7) ^ j5) ^ (j6 >>> this.k164)) ^ (j7 << this.k1)) ^ (j6 >>> this.k264)) ^ (j7 << this.k2));
            long j9 = (((jArr2[8] ^ j5) >>> this.ki) ^ ((jArr2[9] ^ j6) << this.ki64)) ^ (jArr2[16] >>> this.k264);
            long j10 = ((j6 ^ jArr2[9]) >>> this.ki) ^ ((j7 ^ jArr2[10]) << this.ki64);
            int i2 = this.k1;
            long j11 = (jArr2[0] ^ j9) ^ (j9 << i2);
            int i3 = this.k2;
            jArr[i] = j11 ^ (j9 << i3);
            int i4 = this.k164;
            long j12 = ((jArr2[1] ^ j10) ^ (j9 >>> i4)) ^ (j10 << i2);
            int i5 = this.k264;
            jArr[i + 1] = (j12 ^ (j9 >>> i5)) ^ (j10 << i3);
            jArr[i + 2] = ((((j9 ^ (jArr2[2] ^ j)) ^ (j10 >>> i4)) ^ (j << i2)) ^ (j10 >>> i5)) ^ (j << i3);
            jArr[i + 3] = ((j >>> i5) ^ (((j10 ^ (jArr2[3] ^ j8)) ^ (j >>> i4)) ^ (j8 << i2))) ^ (j8 << i3);
        }

        public void rem_gf2n_xor(long[] jArr, int i, long[] jArr2) {
            long j = (jArr2[10] >>> this.ki) ^ (jArr2[11] << this.ki64);
            long j2 = (jArr2[11] >>> this.ki) ^ (jArr2[12] << this.ki64);
            long j3 = (jArr2[12] >>> this.ki) ^ (jArr2[13] << this.ki64);
            int i2 = i + 4;
            jArr[i2] = jArr[i2] ^ ((((((jArr2[4] ^ j3) ^ j) ^ (j2 >>> this.k164)) ^ (j3 << this.k1)) ^ (j2 >>> this.k264)) ^ (j3 << this.k2));
            long j4 = (jArr2[13] >>> this.ki) ^ (jArr2[14] << this.ki64);
            int i3 = i + 5;
            jArr[i3] = jArr[i3] ^ ((((((jArr2[5] ^ j4) ^ j2) ^ (j3 >>> this.k164)) ^ (j4 << this.k1)) ^ (j3 >>> this.k264)) ^ (j4 << this.k2));
            long j5 = (jArr2[14] >>> this.ki) ^ (jArr2[15] << this.ki64);
            int i4 = i + 6;
            jArr[i4] = ((((((jArr2[6] ^ j5) ^ j3) ^ (j4 >>> this.k164)) ^ (j5 << this.k1)) ^ (j4 >>> this.k264)) ^ (j5 << this.k2)) ^ jArr[i4];
            long j6 = (jArr2[15] >>> this.ki) ^ (jArr2[16] << this.ki64);
            int i5 = i + 7;
            jArr[i5] = ((((((jArr2[7] ^ j6) ^ j4) ^ (j5 >>> this.k164)) ^ (j6 << this.k1)) ^ (j5 >>> this.k264)) ^ (j6 << this.k2)) ^ jArr[i5];
            long j7 = jArr2[16] >>> this.ki;
            int i6 = i + 8;
            long j8 = j2;
            jArr[i6] = (((((((jArr2[8] ^ j7) ^ j5) ^ (j6 >>> this.k164)) ^ (j7 << this.k1)) ^ (j6 >>> this.k264)) ^ (j7 << this.k2)) & this.mask) ^ jArr[i6];
            long j9 = (((jArr2[8] ^ j5) >>> this.ki) ^ ((jArr2[9] ^ j6) << this.ki64)) ^ (jArr2[16] >>> this.k264);
            long j10 = ((j6 ^ jArr2[9]) >>> this.ki) ^ ((j7 ^ jArr2[10]) << this.ki64);
            long j11 = jArr[i];
            int i7 = this.k1;
            long j12 = (jArr2[0] ^ j9) ^ (j9 << i7);
            int i8 = this.k2;
            jArr[i] = j11 ^ (j12 ^ (j9 << i8));
            int i9 = i + 1;
            long j13 = jArr[i9];
            int i10 = this.k164;
            long j14 = ((jArr2[1] ^ j10) ^ (j9 >>> i10)) ^ (j10 << i7);
            int i11 = this.k264;
            jArr[i9] = j13 ^ ((j14 ^ (j9 >>> i11)) ^ (j10 << i8));
            int i12 = i + 2;
            jArr[i12] = (((((j9 ^ (jArr2[2] ^ j)) ^ (j10 >>> i10)) ^ (j << i7)) ^ (j10 >>> i11)) ^ (j << i8)) ^ jArr[i12];
            int i13 = i + 3;
            jArr[i13] = (((j >>> i11) ^ (((j10 ^ (jArr2[3] ^ j8)) ^ (j >>> i10)) ^ (j8 << i7))) ^ (j8 << i8)) ^ jArr[i13];
        }
    }

    Rem_GF2n() {
    }

    public abstract void rem_gf2n(long[] jArr, int i, long[] jArr2);

    public abstract void rem_gf2n_xor(long[] jArr, int i, long[] jArr2);
}
