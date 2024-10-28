package org.bouncycastle.pqc.crypto.falcon;

class SHAKE256 {
    long[] A = new long[25];
    private long[] RC = {1, 32898, -9223372036854742902L, -9223372034707259392L, 32907, 2147483649L, -9223372034707259263L, -9223372036854743031L, 138, 136, 2147516425L, 2147483658L, 2147516555L, -9223372036854775669L, -9223372036854742903L, -9223372036854743037L, -9223372036854743038L, -9223372036854775680L, 32778, -9223372034707292150L, -9223372034707259263L, -9223372036854742912L, 2147483649L, -9223372034707259384L};
    byte[] dbuf = new byte[200];
    long dptr = 0;

    SHAKE256() {
    }

    /* access modifiers changed from: package-private */
    public void i_shake256_flip() {
        int i = (int) this.dptr;
        long[] jArr = this.A;
        int i2 = i >> 3;
        jArr[i2] = jArr[i2] ^ (31 << ((i & 7) << 3));
        jArr[16] = jArr[16] ^ Long.MIN_VALUE;
        this.dptr = 136;
    }

    /* access modifiers changed from: package-private */
    public void inner_shake256_extract(byte[] bArr, int i, int i2) {
        int i3 = (int) this.dptr;
        while (i2 > 0) {
            if (i3 == 136) {
                process_block(this.A);
                i3 = 0;
            }
            int i4 = 136 - i3;
            if (i4 > i2) {
                i4 = i2;
            }
            i2 -= i4;
            while (true) {
                int i5 = i4 - 1;
                if (i4 > 0) {
                    bArr[i] = (byte) ((int) (this.A[i3 >> 3] >>> ((i3 & 7) << 3)));
                    i3++;
                    i++;
                    i4 = i5;
                }
            }
        }
        this.dptr = (long) i3;
    }

    /* access modifiers changed from: package-private */
    public void inner_shake256_init() {
        this.dptr = 0;
        int i = 0;
        while (true) {
            long[] jArr = this.A;
            if (i < jArr.length) {
                jArr[i] = 0;
                i++;
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void inner_shake256_inject(byte[] bArr, int i, int i2) {
        long j = this.dptr;
        int i3 = i;
        int i4 = i2;
        while (i4 > 0) {
            long j2 = 136 - j;
            long j3 = (long) i4;
            if (j2 > j3) {
                j2 = j3;
            }
            long j4 = 0;
            while (j4 < j2) {
                long j5 = j4 + j;
                long[] jArr = this.A;
                int i5 = (int) (j5 >> 3);
                jArr[i5] = jArr[i5] ^ ((((long) bArr[((int) j4) + i3]) & 255) << ((int) ((j5 & 7) << 3)));
                j4++;
                j3 = j3;
            }
            j += j2;
            i3 = (int) (((long) i3) + j2);
            i4 = (int) (j3 - j2);
            if (j == 136) {
                process_block(this.A);
                j = 0;
            }
        }
        this.dptr = j;
    }

    /* access modifiers changed from: package-private */
    public void process_block(long[] jArr) {
        long j = -1;
        jArr[1] = jArr[1] ^ -1;
        jArr[2] = jArr[2] ^ -1;
        jArr[8] = jArr[8] ^ -1;
        jArr[12] = jArr[12] ^ -1;
        jArr[17] = jArr[17] ^ -1;
        jArr[20] = jArr[20] ^ -1;
        int i = 0;
        while (i < 24) {
            long j2 = jArr[1];
            long j3 = jArr[6];
            long j4 = jArr[11];
            long j5 = jArr[16];
            long j6 = jArr[21];
            long j7 = (j2 ^ j3) ^ (j6 ^ (j4 ^ j5));
            long j8 = jArr[4];
            long j9 = jArr[9];
            long j10 = jArr[14];
            long j11 = jArr[19];
            long j12 = jArr[24];
            long j13 = (((j7 << 1) | (j7 >>> 63)) ^ j12) ^ ((j8 ^ j9) ^ (j10 ^ j11));
            long j14 = jArr[2];
            long j15 = jArr[7];
            long j16 = jArr[12];
            long j17 = jArr[17];
            long j18 = jArr[22];
            long j19 = (j14 ^ j15) ^ (j18 ^ (j16 ^ j17));
            long j20 = jArr[0];
            long j21 = jArr[5];
            long j22 = jArr[10];
            long j23 = jArr[15];
            long j24 = jArr[20];
            long j25 = (((j19 << 1) | (j19 >>> 63)) ^ j24) ^ ((j20 ^ j21) ^ (j22 ^ j23));
            long j26 = jArr[3];
            long j27 = jArr[8];
            long j28 = jArr[13];
            long j29 = jArr[18];
            long j30 = jArr[23];
            long j31 = (j26 ^ j27) ^ (j30 ^ (j28 ^ j29));
            long j32 = (((j31 << 1) | (j31 >>> 63)) ^ j6) ^ ((j2 ^ j3) ^ (j4 ^ j5));
            long j33 = (j8 ^ j9) ^ (j12 ^ (j10 ^ j11));
            long j34 = (((j33 << 1) | (j33 >>> 63)) ^ j18) ^ ((j14 ^ j15) ^ (j16 ^ j17));
            long j35 = (j20 ^ j21) ^ (j24 ^ (j22 ^ j23));
            long j36 = (((j35 << 1) | (j35 >>> 63)) ^ j30) ^ ((j26 ^ j27) ^ (j28 ^ j29));
            long j37 = j20 ^ j13;
            jArr[0] = j37;
            long j38 = j21 ^ j13;
            jArr[5] = j38;
            long j39 = j22 ^ j13;
            jArr[10] = j39;
            long j40 = j23 ^ j13;
            jArr[15] = j40;
            long j41 = j24 ^ j13;
            jArr[20] = j41;
            long j42 = j2 ^ j25;
            jArr[1] = j42;
            long j43 = j3 ^ j25;
            jArr[6] = j43;
            long j44 = j4 ^ j25;
            jArr[11] = j44;
            long j45 = j5 ^ j25;
            jArr[16] = j45;
            long j46 = j6 ^ j25;
            jArr[21] = j46;
            long j47 = j14 ^ j32;
            jArr[2] = j47;
            long j48 = j15 ^ j32;
            jArr[7] = j48;
            long j49 = j16 ^ j32;
            jArr[12] = j49;
            long j50 = j17 ^ j32;
            jArr[17] = j50;
            long j51 = j18 ^ j32;
            jArr[22] = j51;
            long j52 = j26 ^ j34;
            jArr[3] = j52;
            long j53 = j27 ^ j34;
            jArr[8] = j53;
            long j54 = j28 ^ j34;
            jArr[13] = j54;
            long j55 = j29 ^ j34;
            jArr[18] = j55;
            long j56 = j30 ^ j34;
            jArr[23] = j56;
            long j57 = j8 ^ j36;
            jArr[4] = j57;
            long j58 = j9 ^ j36;
            jArr[9] = j58;
            long j59 = j10 ^ j36;
            jArr[14] = j59;
            long j60 = j11 ^ j36;
            jArr[19] = j60;
            long j61 = j12 ^ j36;
            jArr[24] = j61;
            long j62 = (j38 << 36) | (j38 >>> 28);
            jArr[5] = j62;
            long j63 = (j39 << 3) | (j39 >>> 61);
            jArr[10] = j63;
            long j64 = (j40 << 41) | (j40 >>> 23);
            jArr[15] = j64;
            long j65 = (j41 << 18) | (j41 >>> 46);
            jArr[20] = j65;
            long j66 = (j42 << 1) | (j42 >>> 63);
            jArr[1] = j66;
            long j67 = (j43 << 44) | (j43 >>> 20);
            jArr[6] = j67;
            long j68 = (j44 << 10) | (j44 >>> 54);
            jArr[11] = j68;
            long j69 = (j45 << 45) | (j45 >>> 19);
            jArr[16] = j69;
            long j70 = (j46 << 2) | (j46 >>> 62);
            jArr[21] = j70;
            long j71 = (j47 << 62) | (j47 >>> 2);
            jArr[2] = j71;
            long j72 = (j48 << 6) | (j48 >>> 58);
            jArr[7] = j72;
            long j73 = (j49 << 43) | (j49 >>> 21);
            jArr[12] = j73;
            long j74 = (j50 << 15) | (j50 >>> 49);
            jArr[17] = j74;
            long j75 = (j51 << 61) | (j51 >>> 3);
            jArr[22] = j75;
            long j76 = (j52 << 28) | (j52 >>> 36);
            jArr[3] = j76;
            long j77 = (j53 << 55) | (j53 >>> 9);
            jArr[8] = j77;
            long j78 = (j54 << 25) | (j54 >>> 39);
            jArr[13] = j78;
            long j79 = (j55 << 21) | (j55 >>> 43);
            jArr[18] = j79;
            long j80 = (j56 << 56) | (j56 >>> 8);
            jArr[23] = j80;
            long j81 = (j57 << 27) | (j57 >>> 37);
            jArr[4] = j81;
            long j82 = (j58 << 20) | (j58 >>> 44);
            jArr[9] = j82;
            long j83 = (j59 << 39) | (j59 >>> 25);
            jArr[14] = j83;
            long j84 = (j60 << 8) | (j60 >>> 56);
            jArr[19] = j84;
            long j85 = (j61 << 14) | (j61 >>> 50);
            jArr[24] = j85;
            long j86 = j37 ^ (j67 | j73);
            long j87 = j67 ^ ((j73 ^ j) | j79);
            long j88 = j73 ^ (j79 & j85);
            long j89 = j79 ^ (j85 | j37);
            long j90 = j85 ^ (j37 & j67);
            jArr[0] = j86;
            jArr[6] = j87;
            jArr[12] = j88;
            jArr[18] = j89;
            jArr[24] = j90;
            long j91 = j76 ^ (j82 | j63);
            long j92 = j82 ^ (j63 & j69);
            long j93 = j63 ^ (j69 | (j75 ^ j));
            long j94 = j69 ^ (j75 | j76);
            long j95 = j75 ^ (j76 & j82);
            jArr[3] = j91;
            jArr[9] = j92;
            jArr[10] = j93;
            jArr[16] = j94;
            jArr[22] = j95;
            long j96 = j84 ^ j;
            long j97 = j66 ^ (j72 | j78);
            long j98 = j72 ^ (j78 & j84);
            long j99 = j78 ^ (j96 & j65);
            long j100 = j96 ^ (j65 | j66);
            long j101 = j65 ^ (j66 & j72);
            jArr[1] = j97;
            jArr[7] = j98;
            jArr[13] = j99;
            jArr[19] = j100;
            jArr[20] = j101;
            long j102 = j74 ^ j;
            long j103 = j81 ^ (j62 & j68);
            long j104 = j62 ^ (j68 | j74);
            long j105 = j68 ^ (j102 | j80);
            long j106 = j102 ^ (j80 & j81);
            long j107 = j80 ^ (j81 | j62);
            jArr[4] = j103;
            jArr[5] = j104;
            jArr[11] = j105;
            jArr[17] = j106;
            jArr[23] = j107;
            long j108 = j77 ^ j;
            long j109 = j71 ^ (j108 & j83);
            long j110 = j108 ^ (j83 | j64);
            long j111 = j83 ^ (j64 & j70);
            long j112 = j64 ^ (j70 | j71);
            long j113 = j70 ^ (j71 & j77);
            jArr[2] = j109;
            jArr[8] = j110;
            jArr[14] = j111;
            jArr[15] = j112;
            jArr[21] = j113;
            long[] jArr2 = this.RC;
            long j114 = j86 ^ jArr2[i];
            jArr[0] = j114;
            long j115 = (j87 ^ j92) ^ (j110 ^ (j98 ^ j104));
            long j116 = (((j115 << 1) | (j115 >>> 63)) ^ j113) ^ ((j90 ^ j95) ^ (j101 ^ j107));
            long j117 = (j88 ^ j93) ^ (j111 ^ (j99 ^ j105));
            long j118 = (((j117 << 1) | (j117 >>> 63)) ^ j109) ^ ((j114 ^ j91) ^ (j97 ^ j103));
            long j119 = (j89 ^ j94) ^ (j112 ^ (j100 ^ j106));
            long j120 = (((j119 << 1) | (j119 >>> 63)) ^ j110) ^ ((j87 ^ j92) ^ (j98 ^ j104));
            long j121 = (j90 ^ j95) ^ (j113 ^ (j101 ^ j107));
            long j122 = (((j121 << 1) | (j121 >>> 63)) ^ j111) ^ ((j88 ^ j93) ^ (j99 ^ j105));
            long j123 = (j114 ^ j91) ^ (j109 ^ (j97 ^ j103));
            long j124 = (((j123 << 1) | (j123 >>> 63)) ^ j112) ^ ((j89 ^ j94) ^ (j100 ^ j106));
            long j125 = j114 ^ j116;
            jArr[0] = j125;
            long j126 = j91 ^ j116;
            jArr[3] = j126;
            long j127 = j97 ^ j116;
            jArr[1] = j127;
            long j128 = j103 ^ j116;
            jArr[4] = j128;
            long j129 = j109 ^ j116;
            jArr[2] = j129;
            long j130 = j87 ^ j118;
            jArr[6] = j130;
            long j131 = j92 ^ j118;
            jArr[9] = j131;
            long j132 = j98 ^ j118;
            jArr[7] = j132;
            long j133 = j104 ^ j118;
            jArr[5] = j133;
            long j134 = j110 ^ j118;
            jArr[8] = j134;
            long j135 = j88 ^ j120;
            jArr[12] = j135;
            long j136 = j93 ^ j120;
            jArr[10] = j136;
            long j137 = j99 ^ j120;
            jArr[13] = j137;
            long j138 = j105 ^ j120;
            jArr[11] = j138;
            long j139 = j111 ^ j120;
            jArr[14] = j139;
            long j140 = j89 ^ j122;
            jArr[18] = j140;
            long j141 = j94 ^ j122;
            jArr[16] = j141;
            long j142 = j100 ^ j122;
            jArr[19] = j142;
            long j143 = j106 ^ j122;
            jArr[17] = j143;
            long j144 = j112 ^ j122;
            jArr[15] = j144;
            long j145 = j90 ^ j124;
            jArr[24] = j145;
            long j146 = j95 ^ j124;
            jArr[22] = j146;
            long j147 = j101 ^ j124;
            jArr[20] = j147;
            long j148 = j107 ^ j124;
            jArr[23] = j148;
            long j149 = j113 ^ j124;
            jArr[21] = j149;
            long j150 = (j126 << 36) | (j126 >>> 28);
            jArr[3] = j150;
            long j151 = (j127 << 3) | (j127 >>> 61);
            jArr[1] = j151;
            long j152 = (j128 << 41) | (j128 >>> 23);
            jArr[4] = j152;
            long j153 = (j129 << 18) | (j129 >>> 46);
            jArr[2] = j153;
            long j154 = (j130 << 1) | (j130 >>> 63);
            jArr[6] = j154;
            long j155 = (j131 << 44) | (j131 >>> 20);
            jArr[9] = j155;
            long j156 = (j132 << 10) | (j132 >>> 54);
            jArr[7] = j156;
            long j157 = (j133 << 45) | (j133 >>> 19);
            jArr[5] = j157;
            long j158 = (j134 << 2) | (j134 >>> 62);
            jArr[8] = j158;
            long j159 = (j135 << 62) | (j135 >>> 2);
            jArr[12] = j159;
            long j160 = (j136 << 6) | (j136 >>> 58);
            jArr[10] = j160;
            long j161 = (j137 << 43) | (j137 >>> 21);
            jArr[13] = j161;
            long j162 = (j138 << 15) | (j138 >>> 49);
            jArr[11] = j162;
            long j163 = (j139 << 61) | (j139 >>> 3);
            jArr[14] = j163;
            long j164 = (j140 << 28) | (j140 >>> 36);
            jArr[18] = j164;
            long j165 = (j141 << 55) | (j141 >>> 9);
            jArr[16] = j165;
            long j166 = (j142 << 25) | (j142 >>> 39);
            jArr[19] = j166;
            long j167 = (j143 << 21) | (j143 >>> 43);
            jArr[17] = j167;
            long j168 = (j144 << 56) | (j144 >>> 8);
            jArr[15] = j168;
            long j169 = (j145 << 27) | (j145 >>> 37);
            jArr[24] = j169;
            long j170 = (j146 << 20) | (j146 >>> 44);
            jArr[22] = j170;
            long j171 = (j147 << 39) | (j147 >>> 25);
            jArr[20] = j171;
            long j172 = (j148 << 8) | (j148 >>> 56);
            jArr[23] = j172;
            long j173 = (j149 << 14) | (j149 >>> 50);
            jArr[21] = j173;
            long j174 = j125 ^ (j155 | j161);
            long j175 = j155 ^ ((j161 ^ -1) | j167);
            long j176 = j161 ^ (j167 & j173);
            long j177 = j167 ^ (j173 | j125);
            long j178 = j173 ^ (j125 & j155);
            jArr[0] = j174;
            jArr[9] = j175;
            jArr[13] = j176;
            jArr[17] = j177;
            jArr[21] = j178;
            long j179 = j164 ^ (j170 | j151);
            long j180 = j170 ^ (j151 & j157);
            long j181 = j151 ^ (j157 | (j163 ^ -1));
            long j182 = j157 ^ (j163 | j164);
            long j183 = j163 ^ (j164 & j170);
            jArr[18] = j179;
            jArr[22] = j180;
            jArr[1] = j181;
            jArr[5] = j182;
            jArr[14] = j183;
            long j184 = j172 ^ -1;
            long j185 = j154 ^ (j160 | j166);
            long j186 = j160 ^ (j166 & j172);
            long j187 = j166 ^ (j184 & j153);
            long j188 = j184 ^ (j153 | j154);
            long j189 = j153 ^ (j154 & j160);
            jArr[6] = j185;
            jArr[10] = j186;
            jArr[19] = j187;
            jArr[23] = j188;
            jArr[2] = j189;
            long j190 = j162 ^ -1;
            long j191 = j169 ^ (j150 & j156);
            long j192 = j150 ^ (j156 | j162);
            long j193 = j156 ^ (j190 | j168);
            long j194 = j190 ^ (j168 & j169);
            long j195 = j168 ^ (j169 | j150);
            jArr[24] = j191;
            jArr[3] = j192;
            jArr[7] = j193;
            jArr[11] = j194;
            jArr[15] = j195;
            long j196 = j165 ^ -1;
            long j197 = j159 ^ (j196 & j171);
            long j198 = j196 ^ (j171 | j152);
            long j199 = j171 ^ (j152 & j158);
            long j200 = j152 ^ (j158 | j159);
            long j201 = j158 ^ (j159 & j165);
            jArr[12] = j197;
            jArr[16] = j198;
            jArr[20] = j199;
            jArr[4] = j200;
            jArr[8] = j201;
            jArr[0] = j174 ^ jArr2[i + 1];
            jArr[5] = j179;
            jArr[18] = j194;
            jArr[11] = j186;
            jArr[10] = j185;
            jArr[6] = j180;
            jArr[22] = j199;
            jArr[20] = j197;
            jArr[12] = j187;
            jArr[19] = j195;
            jArr[15] = j191;
            jArr[24] = j201;
            jArr[8] = j182;
            jArr[1] = j175;
            jArr[9] = j183;
            jArr[14] = j189;
            jArr[2] = j176;
            jArr[13] = j188;
            jArr[23] = j200;
            jArr[4] = j178;
            jArr[21] = j198;
            jArr[16] = j192;
            jArr[3] = j177;
            jArr[17] = j193;
            jArr[7] = j181;
            i += 2;
            j = -1;
        }
        jArr[1] = jArr[1] ^ -1;
        jArr[2] = jArr[2] ^ -1;
        jArr[8] = jArr[8] ^ -1;
        jArr[12] = jArr[12] ^ -1;
        jArr[17] = jArr[17] ^ -1;
        jArr[20] = jArr[20] ^ -1;
    }
}
