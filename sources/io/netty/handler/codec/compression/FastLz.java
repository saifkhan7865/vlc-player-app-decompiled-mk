package io.netty.handler.codec.compression;

import io.netty.buffer.ByteBuf;

final class FastLz {
    static final byte BLOCK_TYPE_COMPRESSED = 1;
    static final byte BLOCK_TYPE_NON_COMPRESSED = 0;
    static final byte BLOCK_WITHOUT_CHECKSUM = 0;
    static final byte BLOCK_WITH_CHECKSUM = 16;
    static final int CHECKSUM_OFFSET = 4;
    private static final int HASH_LOG = 13;
    private static final int HASH_MASK = 8191;
    private static final int HASH_SIZE = 8192;
    static final int LEVEL_1 = 1;
    static final int LEVEL_2 = 2;
    static final int LEVEL_AUTO = 0;
    static final int MAGIC_NUMBER = 4607066;
    static final int MAX_CHUNK_LENGTH = 65535;
    private static final int MAX_COPY = 32;
    private static final int MAX_DISTANCE = 8191;
    private static final int MAX_FARDISTANCE = 73725;
    private static final int MAX_LEN = 264;
    static final int MIN_LENGTH_TO_COMPRESSION = 32;
    private static final int MIN_RECOMENDED_LENGTH_FOR_LEVEL_2 = 65536;
    static final int OPTIONS_OFFSET = 3;

    static int calculateOutputBufferLength(int i) {
        double d = (double) i;
        Double.isNaN(d);
        return Math.max((int) (d * 1.06d), 66);
    }

    /* JADX WARNING: Removed duplicated region for block: B:108:0x02ce  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x009b  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0181  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x018f  */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x01aa  */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x01ea  */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x01f6  */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x0202  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static int compress(io.netty.buffer.ByteBuf r27, int r28, int r29, io.netty.buffer.ByteBuf r30, int r31, int r32) {
        /*
            r0 = r27
            r1 = r29
            r2 = r30
            r3 = r31
            r4 = 2
            r5 = 1
            if (r32 != 0) goto L_0x0014
            r6 = 65536(0x10000, float:9.18355E-41)
            if (r1 >= r6) goto L_0x0012
            r6 = 1
            goto L_0x0016
        L_0x0012:
            r6 = 2
            goto L_0x0016
        L_0x0014:
            r6 = r32
        L_0x0016:
            int r7 = r1 + -2
            int r8 = r1 + -12
            r9 = 8192(0x2000, float:1.14794E-41)
            int[] r10 = new int[r9]
            r11 = 4
            r12 = 0
            if (r1 >= r11) goto L_0x0044
            if (r1 == 0) goto L_0x0043
            int r4 = r1 + -1
            byte r4 = (byte) r4
            r2.setByte(r3, r4)
            int r4 = r1 + -1
            r6 = 1
        L_0x002d:
            if (r12 > r4) goto L_0x0040
            int r7 = r6 + 1
            int r6 = r6 + r3
            int r8 = r12 + 1
            int r9 = r28 + r12
            byte r9 = r0.getByte(r9)
            r2.setByte(r6, r9)
            r6 = r7
            r12 = r8
            goto L_0x002d
        L_0x0040:
            int r0 = r1 + 1
            return r0
        L_0x0043:
            return r12
        L_0x0044:
            r11 = 0
        L_0x0045:
            if (r11 >= r9) goto L_0x004c
            r10[r11] = r12
            int r11 = r11 + 1
            goto L_0x0045
        L_0x004c:
            r9 = 31
            r2.setByte(r3, r9)
            int r11 = r3 + 1
            byte r13 = r27.getByte(r28)
            r2.setByte(r11, r13)
            int r11 = r3 + 2
            int r13 = r28 + 1
            byte r13 = r0.getByte(r13)
            r2.setByte(r11, r13)
            r13 = 2
            r14 = 3
            r15 = 2
        L_0x0068:
            if (r13 >= r8) goto L_0x036e
            r16 = 1
            r18 = 0
            if (r6 != r4) goto L_0x0092
            int r12 = r28 + r13
            byte r9 = r0.getByte(r12)
            int r11 = r12 + -1
            byte r4 = r0.getByte(r11)
            if (r9 != r4) goto L_0x0092
            int r4 = readU16(r0, r11)
            int r12 = r12 + 1
            int r9 = readU16(r0, r12)
            if (r4 != r9) goto L_0x0092
            int r4 = r13 + 3
            int r9 = r13 + 2
            r21 = r16
            r11 = 1
            goto L_0x0097
        L_0x0092:
            r4 = r13
            r21 = r18
            r9 = 0
            r11 = 0
        L_0x0097:
            r23 = 8191(0x1fff, double:4.047E-320)
            if (r11 != 0) goto L_0x0181
            int r9 = r28 + r4
            int r11 = hashFunction(r0, r9)
            r12 = r10[r11]
            int r5 = r13 - r12
            r25 = r7
            r26 = r8
            long r7 = (long) r5
            r10[r11] = r13
            int r5 = (r7 > r18 ? 1 : (r7 == r18 ? 0 : -1))
            if (r5 == 0) goto L_0x0146
            r5 = 1
            if (r6 != r5) goto L_0x00b9
            int r5 = (r7 > r23 ? 1 : (r7 == r23 ? 0 : -1))
            if (r5 < 0) goto L_0x00c0
            goto L_0x0146
        L_0x00b9:
            r21 = 73725(0x11ffd, double:3.6425E-319)
            int r5 = (r7 > r21 ? 1 : (r7 == r21 ? 0 : -1))
            if (r5 >= 0) goto L_0x0146
        L_0x00c0:
            int r5 = r12 + 1
            int r11 = r28 + r12
            byte r11 = r0.getByte(r11)
            int r21 = r4 + 1
            byte r9 = r0.getByte(r9)
            if (r11 != r9) goto L_0x0146
            int r9 = r12 + 2
            int r5 = r28 + r5
            byte r5 = r0.getByte(r5)
            int r11 = r4 + 2
            int r1 = r28 + r21
            byte r1 = r0.getByte(r1)
            if (r5 != r1) goto L_0x0146
            int r1 = r12 + 3
            int r5 = r28 + r9
            byte r5 = r0.getByte(r5)
            int r9 = r4 + 3
            int r11 = r28 + r11
            byte r11 = r0.getByte(r11)
            if (r5 == r11) goto L_0x00f5
            goto L_0x0146
        L_0x00f5:
            r5 = 2
            if (r6 != r5) goto L_0x0142
            int r5 = (r7 > r23 ? 1 : (r7 == r23 ? 0 : -1))
            if (r5 < 0) goto L_0x0142
            int r4 = r4 + 4
            int r5 = r28 + r9
            byte r5 = r0.getByte(r5)
            int r9 = r12 + 4
            int r1 = r28 + r1
            byte r1 = r0.getByte(r1)
            if (r5 != r1) goto L_0x0124
            int r1 = r28 + r4
            byte r1 = r0.getByte(r1)
            int r4 = r12 + 5
            int r5 = r28 + r9
            byte r5 = r0.getByte(r5)
            if (r1 == r5) goto L_0x011f
            goto L_0x0124
        L_0x011f:
            r1 = 5
            r9 = r4
            r21 = r7
            goto L_0x0186
        L_0x0124:
            int r1 = r14 + 1
            int r4 = r3 + r14
            int r5 = r13 + 1
            int r7 = r28 + r13
            byte r7 = r0.getByte(r7)
            r2.setByte(r4, r7)
            int r15 = r15 + 1
            r4 = 32
            if (r15 != r4) goto L_0x0172
            int r14 = r14 + 2
            int r1 = r1 + r3
            r4 = 31
            r2.setByte(r1, r4)
            goto L_0x0163
        L_0x0142:
            r9 = r1
            r21 = r7
            goto L_0x0185
        L_0x0146:
            int r1 = r14 + 1
            int r4 = r3 + r14
            int r5 = r13 + 1
            int r7 = r28 + r13
            byte r7 = r0.getByte(r7)
            r2.setByte(r4, r7)
            int r15 = r15 + 1
            r4 = 32
            if (r15 != r4) goto L_0x0172
            int r14 = r14 + 2
            int r1 = r1 + r3
            r4 = 31
            r2.setByte(r1, r4)
        L_0x0163:
            r1 = r29
            r13 = r5
            r7 = r25
            r8 = r26
            r4 = 2
            r5 = 1
            r9 = 31
            r12 = 0
            r15 = 0
            goto L_0x0068
        L_0x0172:
            r14 = r1
            r13 = r5
            r7 = r25
            r8 = r26
            r4 = 2
            r5 = 1
            r9 = 31
            r12 = 0
        L_0x017d:
            r1 = r29
            goto L_0x0068
        L_0x0181:
            r25 = r7
            r26 = r8
        L_0x0185:
            r1 = 3
        L_0x0186:
            int r1 = r1 + r13
            long r4 = r21 - r16
            r7 = 8
            int r8 = (r4 > r18 ? 1 : (r4 == r18 ? 0 : -1))
            if (r8 != 0) goto L_0x01aa
            int r8 = r28 + r1
            r11 = 1
            int r8 = r8 - r11
            byte r8 = r0.getByte(r8)
            r11 = r25
        L_0x0199:
            if (r1 >= r11) goto L_0x01e8
            int r12 = r9 + 1
            int r9 = r28 + r9
            byte r9 = r0.getByte(r9)
            if (r9 == r8) goto L_0x01a6
            goto L_0x01e8
        L_0x01a6:
            int r1 = r1 + 1
            r9 = r12
            goto L_0x0199
        L_0x01aa:
            r11 = r25
            r8 = 0
        L_0x01ad:
            if (r8 >= r7) goto L_0x01cc
            int r12 = r9 + 1
            int r9 = r28 + r9
            byte r9 = r0.getByte(r9)
            int r16 = r1 + 1
            int r1 = r28 + r1
            byte r1 = r0.getByte(r1)
            if (r9 == r1) goto L_0x01c6
            r9 = r12
            r1 = r16
            r8 = 1
            goto L_0x01cd
        L_0x01c6:
            int r8 = r8 + 1
            r9 = r12
            r1 = r16
            goto L_0x01ad
        L_0x01cc:
            r8 = 0
        L_0x01cd:
            if (r8 != 0) goto L_0x01e8
        L_0x01cf:
            if (r1 >= r11) goto L_0x01e8
            int r8 = r9 + 1
            int r9 = r28 + r9
            byte r9 = r0.getByte(r9)
            int r12 = r1 + 1
            int r1 = r28 + r1
            byte r1 = r0.getByte(r1)
            if (r9 == r1) goto L_0x01e5
            r1 = r12
            goto L_0x01e8
        L_0x01e5:
            r9 = r8
            r1 = r12
            goto L_0x01cf
        L_0x01e8:
            if (r15 == 0) goto L_0x01f6
            int r8 = r3 + r14
            int r8 = r8 - r15
            r9 = 1
            int r8 = r8 - r9
            int r15 = r15 + -1
            byte r9 = (byte) r15
            r2.setByte(r8, r9)
            goto L_0x01f8
        L_0x01f6:
            int r14 = r14 + -1
        L_0x01f8:
            int r8 = r1 + -3
            int r9 = r8 - r13
            r15 = 7
            r16 = 255(0xff, double:1.26E-321)
            r12 = 2
            if (r6 != r12) goto L_0x02ce
            int r20 = (r4 > r23 ? 1 : (r4 == r23 ? 0 : -1))
            if (r20 >= 0) goto L_0x025a
            if (r9 >= r15) goto L_0x0226
            int r12 = r14 + 1
            int r13 = r3 + r14
            int r9 = r9 << 5
            r23 = r10
            long r9 = (long) r9
            long r18 = r4 >>> r7
            long r9 = r9 + r18
            int r7 = (int) r9
            byte r7 = (byte) r7
            r2.setByte(r13, r7)
            int r14 = r14 + 2
            int r7 = r3 + r12
            long r4 = r4 & r16
            int r5 = (int) r4
            byte r4 = (byte) r5
            r2.setByte(r7, r4)
            goto L_0x028a
        L_0x0226:
            r23 = r10
            int r10 = r14 + 1
            int r14 = r14 + r3
            long r21 = r4 >>> r7
            r18 = 224(0xe0, double:1.107E-321)
            long r12 = r21 + r18
            int r7 = (int) r12
            byte r7 = (byte) r7
            r2.setByte(r14, r7)
            int r9 = r9 + -7
        L_0x0238:
            r7 = 255(0xff, float:3.57E-43)
            if (r9 < r7) goto L_0x0247
            int r7 = r10 + 1
            int r10 = r10 + r3
            r12 = -1
            r2.setByte(r10, r12)
            int r9 = r9 + -255
            r10 = r7
            goto L_0x0238
        L_0x0247:
            int r7 = r10 + 1
            int r12 = r3 + r10
            byte r9 = (byte) r9
            r2.setByte(r12, r9)
            int r14 = r10 + 2
            int r7 = r7 + r3
            long r4 = r4 & r16
            int r5 = (int) r4
            byte r4 = (byte) r5
            r2.setByte(r7, r4)
            goto L_0x028a
        L_0x025a:
            r23 = r10
            r4 = 8192(0x2000, double:4.0474E-320)
            if (r9 >= r15) goto L_0x028e
            long r21 = r21 - r4
            int r4 = r14 + 1
            int r5 = r3 + r14
            int r9 = r9 << 5
            r10 = 31
            int r9 = r9 + r10
            byte r9 = (byte) r9
            r2.setByte(r5, r9)
            int r5 = r14 + 2
            int r4 = r4 + r3
            r9 = -1
            r2.setByte(r4, r9)
            int r4 = r14 + 3
            int r5 = r5 + r3
            long r9 = r21 >>> r7
            int r7 = (int) r9
            byte r7 = (byte) r7
            r2.setByte(r5, r7)
            int r14 = r14 + 4
            int r4 = r4 + r3
            long r9 = r21 & r16
            int r5 = (int) r9
            byte r5 = (byte) r5
            r2.setByte(r4, r5)
        L_0x028a:
            r25 = r11
            goto L_0x0342
        L_0x028e:
            long r21 = r21 - r4
            int r4 = r14 + 1
            int r5 = r3 + r14
            r10 = -1
            r2.setByte(r5, r10)
            int r9 = r9 + -7
            r5 = 255(0xff, float:3.57E-43)
        L_0x029c:
            if (r9 < r5) goto L_0x02a9
            int r12 = r4 + 1
            int r4 = r4 + r3
            r2.setByte(r4, r10)
            int r9 = r9 + -255
            r4 = r12
            r10 = -1
            goto L_0x029c
        L_0x02a9:
            int r5 = r4 + 1
            int r10 = r3 + r4
            byte r9 = (byte) r9
            r2.setByte(r10, r9)
            int r9 = r4 + 2
            int r5 = r5 + r3
            r10 = -1
            r2.setByte(r5, r10)
            int r5 = r4 + 3
            int r9 = r9 + r3
            long r12 = r21 >>> r7
            int r7 = (int) r12
            byte r7 = (byte) r7
            r2.setByte(r9, r7)
            int r14 = r4 + 4
            int r4 = r3 + r5
            long r9 = r21 & r16
            int r5 = (int) r9
            byte r5 = (byte) r5
            r2.setByte(r4, r5)
            goto L_0x028a
        L_0x02ce:
            r23 = r10
            r10 = 262(0x106, float:3.67E-43)
            if (r9 <= r10) goto L_0x0300
        L_0x02d4:
            if (r9 <= r10) goto L_0x0300
            int r12 = r14 + 1
            int r13 = r3 + r14
            long r21 = r4 >>> r7
            r25 = r11
            r18 = 224(0xe0, double:1.107E-321)
            long r10 = r21 + r18
            int r11 = (int) r10
            byte r10 = (byte) r11
            r2.setByte(r13, r10)
            int r10 = r14 + 2
            int r11 = r3 + r12
            r12 = -3
            r2.setByte(r11, r12)
            int r14 = r14 + 3
            int r10 = r10 + r3
            long r11 = r4 & r16
            int r12 = (int) r11
            byte r11 = (byte) r12
            r2.setByte(r10, r11)
            int r9 = r9 + -262
            r11 = r25
            r10 = 262(0x106, float:3.67E-43)
            goto L_0x02d4
        L_0x0300:
            r25 = r11
            if (r9 >= r15) goto L_0x0320
            int r10 = r14 + 1
            int r11 = r3 + r14
            int r9 = r9 << 5
            long r12 = (long) r9
            long r18 = r4 >>> r7
            long r12 = r12 + r18
            int r7 = (int) r12
            byte r7 = (byte) r7
            r2.setByte(r11, r7)
            int r14 = r14 + 2
            int r7 = r3 + r10
            long r4 = r4 & r16
            int r5 = (int) r4
            byte r4 = (byte) r5
            r2.setByte(r7, r4)
            goto L_0x0342
        L_0x0320:
            int r10 = r14 + 1
            int r11 = r3 + r14
            long r12 = r4 >>> r7
            r18 = 224(0xe0, double:1.107E-321)
            long r12 = r12 + r18
            int r7 = (int) r12
            byte r7 = (byte) r7
            r2.setByte(r11, r7)
            int r7 = r14 + 2
            int r10 = r10 + r3
            int r9 = r9 + -7
            byte r9 = (byte) r9
            r2.setByte(r10, r9)
            int r14 = r14 + 3
            int r7 = r7 + r3
            long r4 = r4 & r16
            int r5 = (int) r4
            byte r4 = (byte) r5
            r2.setByte(r7, r4)
        L_0x0342:
            int r4 = r28 + r8
            int r4 = hashFunction(r0, r4)
            int r5 = r1 + -2
            r23[r4] = r8
            int r4 = r28 + r5
            int r4 = hashFunction(r0, r4)
            int r13 = r1 + -1
            r23[r4] = r5
            int r1 = r14 + 1
            int r4 = r3 + r14
            r5 = 31
            r2.setByte(r4, r5)
            r14 = r1
            r10 = r23
            r7 = r25
            r8 = r26
            r4 = 2
            r5 = 1
            r9 = 31
            r12 = 0
            r15 = 0
            goto L_0x017d
        L_0x036e:
            r1 = 1
            int r4 = r29 + -1
        L_0x0371:
            if (r13 > r4) goto L_0x0396
            int r1 = r14 + 1
            int r5 = r3 + r14
            int r7 = r13 + 1
            int r8 = r28 + r13
            byte r8 = r0.getByte(r8)
            r2.setByte(r5, r8)
            int r15 = r15 + 1
            r5 = 32
            if (r15 != r5) goto L_0x0393
            int r14 = r14 + 2
            int r1 = r1 + r3
            r5 = 31
            r2.setByte(r1, r5)
            r13 = r7
            r15 = 0
            goto L_0x0371
        L_0x0393:
            r14 = r1
            r13 = r7
            goto L_0x0371
        L_0x0396:
            if (r15 == 0) goto L_0x03a3
            int r0 = r3 + r14
            int r0 = r0 - r15
            r1 = 1
            int r0 = r0 - r1
            int r15 = r15 - r1
            byte r1 = (byte) r15
            r2.setByte(r0, r1)
            goto L_0x03a5
        L_0x03a3:
            int r14 = r14 + -1
        L_0x03a5:
            r0 = 2
            if (r6 != r0) goto L_0x03b2
            byte r0 = r30.getByte(r31)
            r1 = 32
            r0 = r0 | r1
            r2.setByte(r3, r0)
        L_0x03b2:
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.codec.compression.FastLz.compress(io.netty.buffer.ByteBuf, int, int, io.netty.buffer.ByteBuf, int, int):int");
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0090  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x009d  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00e2 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00e3  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static int decompress(io.netty.buffer.ByteBuf r30, int r31, int r32, io.netty.buffer.ByteBuf r33, int r34, int r35) {
        /*
            r0 = r30
            r1 = r32
            r2 = r33
            r3 = r35
            byte r4 = r30.getByte(r31)
            r5 = 5
            int r4 = r4 >> r5
            r6 = 1
            int r4 = r4 + r6
            r7 = 0
            if (r4 == r6) goto L_0x0038
            r8 = 2
            if (r4 != r8) goto L_0x0017
            goto L_0x0038
        L_0x0017:
            io.netty.handler.codec.compression.DecompressionException r0 = new io.netty.handler.codec.compression.DecompressionException
            java.lang.Integer r1 = java.lang.Integer.valueOf(r4)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r6)
            java.lang.Integer r3 = java.lang.Integer.valueOf(r8)
            r4 = 3
            java.lang.Object[] r4 = new java.lang.Object[r4]
            r4[r7] = r1
            r4[r6] = r2
            r4[r8] = r3
            java.lang.String r1 = "invalid level: %d (expected: %d or %d)"
            java.lang.String r1 = java.lang.String.format(r1, r4)
            r0.<init>((java.lang.String) r1)
            throw r0
        L_0x0038:
            byte r8 = r30.getByte(r31)
            r8 = r8 & 31
            long r8 = (long) r8
            r10 = 0
            r11 = 1
            r12 = 1
        L_0x0042:
            long r13 = r8 >> r5
            r15 = 31
            long r15 = r15 & r8
            r17 = 8
            long r15 = r15 << r17
            r18 = 32
            r20 = 0
            r22 = 1
            int r24 = (r8 > r18 ? 1 : (r8 == r18 ? 0 : -1))
            if (r24 < 0) goto L_0x016c
            long r13 = r13 - r22
            r24 = r8
            long r7 = (long) r10
            long r5 = r7 - r15
            int r6 = (int) r5
            r26 = 6
            int r28 = (r13 > r26 ? 1 : (r13 == r26 ? 0 : -1))
            if (r28 != 0) goto L_0x008a
            r9 = 1
            if (r4 != r9) goto L_0x0074
            int r9 = r11 + 1
            int r11 = r31 + r11
            short r11 = r0.getUnsignedByte(r11)
            r27 = r6
            long r5 = (long) r11
            long r13 = r13 + r5
            r11 = r9
            goto L_0x008c
        L_0x0074:
            r27 = r6
        L_0x0076:
            int r5 = r11 + 1
            int r6 = r31 + r11
            short r6 = r0.getUnsignedByte(r6)
            r9 = r12
            long r11 = (long) r6
            long r13 = r13 + r11
            r11 = 255(0xff, float:3.57E-43)
            if (r6 == r11) goto L_0x0087
            r11 = r5
            goto L_0x008d
        L_0x0087:
            r11 = r5
            r12 = r9
            goto L_0x0076
        L_0x008a:
            r27 = r6
        L_0x008c:
            r9 = r12
        L_0x008d:
            r5 = 1
            if (r4 != r5) goto L_0x009d
            int r5 = r11 + 1
            int r6 = r31 + r11
            short r6 = r0.getUnsignedByte(r6)
            int r6 = r27 - r6
            r27 = r4
            goto L_0x00d2
        L_0x009d:
            int r5 = r11 + 1
            int r6 = r31 + r11
            short r6 = r0.getUnsignedByte(r6)
            int r12 = r27 - r6
            r27 = r4
            r4 = 255(0xff, float:3.57E-43)
            if (r6 != r4) goto L_0x00d1
            r28 = 7936(0x1f00, double:3.921E-320)
            int r4 = (r15 > r28 ? 1 : (r15 == r28 ? 0 : -1))
            if (r4 != 0) goto L_0x00d1
            int r4 = r11 + 2
            int r5 = r31 + r5
            short r5 = r0.getUnsignedByte(r5)
            int r5 = r5 << 8
            long r5 = (long) r5
            int r11 = r11 + 3
            int r4 = r31 + r4
            short r4 = r0.getUnsignedByte(r4)
            r15 = r11
            long r11 = (long) r4
            long r5 = r5 + r11
            long r5 = r7 - r5
            r11 = 8191(0x1fff, double:4.047E-320)
            long r5 = r5 - r11
            int r6 = (int) r5
            r5 = r15
            goto L_0x00d2
        L_0x00d1:
            r6 = r12
        L_0x00d2:
            long r7 = r7 + r13
            r11 = 3
            long r7 = r7 + r11
            long r11 = (long) r3
            int r4 = (r7 > r11 ? 1 : (r7 == r11 ? 0 : -1))
            if (r4 <= 0) goto L_0x00dd
            r4 = 0
            return r4
        L_0x00dd:
            r4 = 0
            int r7 = r6 + -1
            if (r7 >= 0) goto L_0x00e3
            return r4
        L_0x00e3:
            if (r5 >= r1) goto L_0x00f2
            int r4 = r5 + 1
            int r5 = r31 + r5
            short r5 = r0.getUnsignedByte(r5)
            long r7 = (long) r5
            r5 = r4
            r12 = r9
            r8 = r7
            goto L_0x00f5
        L_0x00f2:
            r8 = r24
            r12 = 0
        L_0x00f5:
            if (r6 != r10) goto L_0x0123
            int r4 = r34 + r6
            r7 = 1
            int r4 = r4 - r7
            byte r4 = r2.getByte(r4)
            int r6 = r10 + 1
            int r11 = r34 + r10
            r2.setByte(r11, r4)
            int r11 = r10 + 2
            int r6 = r34 + r6
            r2.setByte(r6, r4)
            int r10 = r10 + 3
            int r6 = r34 + r11
            r2.setByte(r6, r4)
        L_0x0114:
            int r6 = (r13 > r20 ? 1 : (r13 == r20 ? 0 : -1))
            if (r6 == 0) goto L_0x0169
            int r6 = r10 + 1
            int r10 = r34 + r10
            r2.setByte(r10, r4)
            long r13 = r13 - r22
            r10 = r6
            goto L_0x0114
        L_0x0123:
            r7 = 1
            int r4 = r6 + -1
            int r11 = r10 + 1
            int r15 = r34 + r10
            int r4 = r34 + r4
            byte r4 = r2.getByte(r4)
            r2.setByte(r15, r4)
            int r4 = r10 + 2
            int r11 = r34 + r11
            int r15 = r6 + 1
            int r7 = r34 + r6
            byte r7 = r2.getByte(r7)
            r2.setByte(r11, r7)
            int r10 = r10 + 3
            int r4 = r34 + r4
            int r6 = r6 + 2
            int r7 = r34 + r15
            byte r7 = r2.getByte(r7)
            r2.setByte(r4, r7)
        L_0x0151:
            int r4 = (r13 > r20 ? 1 : (r13 == r20 ? 0 : -1))
            if (r4 == 0) goto L_0x0169
            int r4 = r10 + 1
            int r7 = r34 + r10
            int r10 = r6 + 1
            int r6 = r34 + r6
            byte r6 = r2.getByte(r6)
            r2.setByte(r7, r6)
            long r13 = r13 - r22
            r6 = r10
            r10 = r4
            goto L_0x0151
        L_0x0169:
            r11 = r5
            r4 = 0
            goto L_0x01c5
        L_0x016c:
            r27 = r4
            r24 = r8
            long r8 = r24 + r22
            long r4 = (long) r10
            long r4 = r4 + r8
            long r6 = (long) r3
            int r12 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r12 <= 0) goto L_0x017b
            r4 = 0
            return r4
        L_0x017b:
            r4 = 0
            long r5 = (long) r11
            long r5 = r5 + r8
            long r7 = (long) r1
            int r9 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r9 <= 0) goto L_0x0184
            return r4
        L_0x0184:
            int r5 = r10 + 1
            int r6 = r34 + r10
            int r7 = r11 + 1
            int r8 = r31 + r11
            byte r8 = r0.getByte(r8)
            r2.setByte(r6, r8)
            r8 = r24
        L_0x0195:
            int r6 = (r8 > r20 ? 1 : (r8 == r20 ? 0 : -1))
            if (r6 == 0) goto L_0x01ad
            int r6 = r5 + 1
            int r5 = r34 + r5
            int r10 = r7 + 1
            int r7 = r31 + r7
            byte r7 = r0.getByte(r7)
            r2.setByte(r5, r7)
            long r8 = r8 - r22
            r5 = r6
            r7 = r10
            goto L_0x0195
        L_0x01ad:
            if (r7 >= r1) goto L_0x01b1
            r6 = 1
            goto L_0x01b2
        L_0x01b1:
            r6 = 0
        L_0x01b2:
            if (r6 == 0) goto L_0x01c2
            int r8 = r7 + 1
            int r7 = r31 + r7
            short r7 = r0.getUnsignedByte(r7)
            long r9 = (long) r7
            r12 = r6
            r11 = r8
            r8 = r9
            r10 = r5
            goto L_0x01c5
        L_0x01c2:
            r10 = r5
            r12 = r6
            r11 = r7
        L_0x01c5:
            if (r12 != 0) goto L_0x01c8
            return r10
        L_0x01c8:
            r4 = r27
            r5 = 5
            r6 = 1
            r7 = 0
            goto L_0x0042
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.codec.compression.FastLz.decompress(io.netty.buffer.ByteBuf, int, int, io.netty.buffer.ByteBuf, int, int):int");
    }

    private static int hashFunction(ByteBuf byteBuf, int i) {
        int readU16 = readU16(byteBuf, i);
        return ((readU16(byteBuf, i + 1) ^ (readU16 >> 3)) ^ readU16) & 8191;
    }

    private static int readU16(ByteBuf byteBuf, int i) {
        int i2 = i + 1;
        if (i2 >= byteBuf.readableBytes()) {
            return byteBuf.getUnsignedByte(i);
        }
        return byteBuf.getUnsignedByte(i) | (byteBuf.getUnsignedByte(i2) << 8);
    }

    private FastLz() {
    }
}
