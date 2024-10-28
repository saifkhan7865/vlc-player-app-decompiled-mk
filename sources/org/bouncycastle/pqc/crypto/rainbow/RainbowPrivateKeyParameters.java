package org.bouncycastle.pqc.crypto.rainbow;

import org.bouncycastle.util.Arrays;

public class RainbowPrivateKeyParameters extends RainbowKeyParameters {
    final short[][][] l1_F1;
    final short[][][] l1_F2;
    final short[][][] l2_F1;
    final short[][][] l2_F2;
    final short[][][] l2_F3;
    final short[][][] l2_F5;
    final short[][][] l2_F6;
    private byte[] pk_encoded;
    private final byte[] pk_seed;
    final short[][] s1;
    final byte[] sk_seed;
    final short[][] t1;
    final short[][] t3;
    final short[][] t4;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public RainbowPrivateKeyParameters(org.bouncycastle.pqc.crypto.rainbow.RainbowParameters r19, byte[] r20) {
        /*
            r18 = this;
            r0 = r18
            r1 = r19
            r2 = r20
            r3 = 1
            r0.<init>(r3, r1)
            org.bouncycastle.pqc.crypto.rainbow.Version r4 = r19.getVersion()
            org.bouncycastle.pqc.crypto.rainbow.Version r5 = org.bouncycastle.pqc.crypto.rainbow.Version.COMPRESSED
            r6 = 0
            if (r4 != r5) goto L_0x006b
            int r3 = r19.getLen_pkseed()
            byte[] r3 = org.bouncycastle.util.Arrays.copyOfRange((byte[]) r2, (int) r6, (int) r3)
            r0.pk_seed = r3
            int r4 = r19.getLen_pkseed()
            int r5 = r19.getLen_pkseed()
            int r6 = r19.getLen_skseed()
            int r5 = r5 + r6
            byte[] r2 = org.bouncycastle.util.Arrays.copyOfRange((byte[]) r2, (int) r4, (int) r5)
            r0.sk_seed = r2
            org.bouncycastle.pqc.crypto.rainbow.RainbowKeyComputation r4 = new org.bouncycastle.pqc.crypto.rainbow.RainbowKeyComputation
            r4.<init>(r1, r3, r2)
            org.bouncycastle.pqc.crypto.rainbow.RainbowPrivateKeyParameters r1 = r4.generatePrivateKey()
            byte[] r2 = r1.pk_encoded
            r0.pk_encoded = r2
            short[][] r2 = r1.s1
            r0.s1 = r2
            short[][] r2 = r1.t1
            r0.t1 = r2
            short[][] r2 = r1.t3
            r0.t3 = r2
            short[][] r2 = r1.t4
            r0.t4 = r2
            short[][][] r2 = r1.l1_F1
            r0.l1_F1 = r2
            short[][][] r2 = r1.l1_F2
            r0.l1_F2 = r2
            short[][][] r2 = r1.l2_F1
            r0.l2_F1 = r2
            short[][][] r2 = r1.l2_F2
            r0.l2_F2 = r2
            short[][][] r2 = r1.l2_F3
            r0.l2_F3 = r2
            short[][][] r2 = r1.l2_F5
            r0.l2_F5 = r2
            short[][][] r1 = r1.l2_F6
            r0.l2_F6 = r1
            goto L_0x018f
        L_0x006b:
            int r4 = r19.getV1()
            int r5 = r19.getO1()
            int r7 = r19.getO2()
            r8 = 2
            int[] r9 = new int[r8]
            r9[r3] = r7
            r9[r6] = r5
            java.lang.Class r10 = java.lang.Short.TYPE
            java.lang.Object r9 = java.lang.reflect.Array.newInstance(r10, r9)
            short[][] r9 = (short[][]) r9
            r0.s1 = r9
            int[] r10 = new int[r8]
            r10[r3] = r5
            r10[r6] = r4
            java.lang.Class r11 = java.lang.Short.TYPE
            java.lang.Object r10 = java.lang.reflect.Array.newInstance(r11, r10)
            short[][] r10 = (short[][]) r10
            r0.t1 = r10
            int[] r11 = new int[r8]
            r11[r3] = r7
            r11[r6] = r4
            java.lang.Class r12 = java.lang.Short.TYPE
            java.lang.Object r11 = java.lang.reflect.Array.newInstance(r12, r11)
            short[][] r11 = (short[][]) r11
            r0.t4 = r11
            int[] r12 = new int[r8]
            r12[r3] = r7
            r12[r6] = r5
            java.lang.Class r13 = java.lang.Short.TYPE
            java.lang.Object r12 = java.lang.reflect.Array.newInstance(r13, r12)
            short[][] r12 = (short[][]) r12
            r0.t3 = r12
            r13 = 3
            int[] r14 = new int[r13]
            r14[r8] = r4
            r14[r3] = r4
            r14[r6] = r5
            java.lang.Class r15 = java.lang.Short.TYPE
            java.lang.Object r14 = java.lang.reflect.Array.newInstance(r15, r14)
            short[][][] r14 = (short[][][]) r14
            r0.l1_F1 = r14
            int[] r15 = new int[r13]
            r15[r8] = r5
            r15[r3] = r4
            r15[r6] = r5
            java.lang.Class r6 = java.lang.Short.TYPE
            java.lang.Object r6 = java.lang.reflect.Array.newInstance(r6, r15)
            short[][][] r6 = (short[][][]) r6
            r0.l1_F2 = r6
            int[] r15 = new int[r13]
            r15[r8] = r4
            r15[r3] = r4
            r3 = 0
            r15[r3] = r7
            java.lang.Class r3 = java.lang.Short.TYPE
            java.lang.Object r3 = java.lang.reflect.Array.newInstance(r3, r15)
            short[][][] r3 = (short[][][]) r3
            r0.l2_F1 = r3
            int[] r15 = new int[r13]
            r15[r8] = r5
            r16 = 1
            r15[r16] = r4
            r8 = 0
            r15[r8] = r7
            java.lang.Class r8 = java.lang.Short.TYPE
            java.lang.Object r8 = java.lang.reflect.Array.newInstance(r8, r15)
            short[][][] r8 = (short[][][]) r8
            r0.l2_F2 = r8
            int[] r15 = new int[r13]
            r17 = 2
            r15[r17] = r7
            r15[r16] = r4
            r4 = 0
            r15[r4] = r7
            java.lang.Class r4 = java.lang.Short.TYPE
            java.lang.Object r4 = java.lang.reflect.Array.newInstance(r4, r15)
            short[][][] r4 = (short[][][]) r4
            r0.l2_F3 = r4
            int[] r15 = new int[r13]
            r15[r17] = r5
            r15[r16] = r5
            r13 = 0
            r15[r13] = r7
            java.lang.Class r13 = java.lang.Short.TYPE
            java.lang.Object r13 = java.lang.reflect.Array.newInstance(r13, r15)
            short[][][] r13 = (short[][][]) r13
            r0.l2_F5 = r13
            r15 = 3
            int[] r15 = new int[r15]
            r15[r17] = r7
            r15[r16] = r5
            r5 = 0
            r15[r5] = r7
            java.lang.Class r7 = java.lang.Short.TYPE
            java.lang.Object r7 = java.lang.reflect.Array.newInstance(r7, r15)
            short[][][] r7 = (short[][][]) r7
            r0.l2_F6 = r7
            r15 = 0
            r0.pk_seed = r15
            int r1 = r19.getLen_skseed()
            byte[] r1 = org.bouncycastle.util.Arrays.copyOfRange((byte[]) r2, (int) r5, (int) r1)
            r0.sk_seed = r1
            int r1 = r1.length
            int r5 = org.bouncycastle.pqc.crypto.rainbow.RainbowUtil.loadEncoded(r9, r2, r1)
            int r1 = r1 + r5
            int r5 = org.bouncycastle.pqc.crypto.rainbow.RainbowUtil.loadEncoded(r10, r2, r1)
            int r1 = r1 + r5
            int r5 = org.bouncycastle.pqc.crypto.rainbow.RainbowUtil.loadEncoded(r11, r2, r1)
            int r1 = r1 + r5
            int r5 = org.bouncycastle.pqc.crypto.rainbow.RainbowUtil.loadEncoded(r12, r2, r1)
            int r1 = r1 + r5
            r5 = 1
            int r9 = org.bouncycastle.pqc.crypto.rainbow.RainbowUtil.loadEncoded(r14, r2, r1, r5)
            int r1 = r1 + r9
            r9 = 0
            int r6 = org.bouncycastle.pqc.crypto.rainbow.RainbowUtil.loadEncoded(r6, r2, r1, r9)
            int r1 = r1 + r6
            int r3 = org.bouncycastle.pqc.crypto.rainbow.RainbowUtil.loadEncoded(r3, r2, r1, r5)
            int r1 = r1 + r3
            int r3 = org.bouncycastle.pqc.crypto.rainbow.RainbowUtil.loadEncoded(r8, r2, r1, r9)
            int r1 = r1 + r3
            int r3 = org.bouncycastle.pqc.crypto.rainbow.RainbowUtil.loadEncoded(r4, r2, r1, r9)
            int r1 = r1 + r3
            int r3 = org.bouncycastle.pqc.crypto.rainbow.RainbowUtil.loadEncoded(r13, r2, r1, r5)
            int r1 = r1 + r3
            int r3 = org.bouncycastle.pqc.crypto.rainbow.RainbowUtil.loadEncoded(r7, r2, r1, r9)
            int r1 = r1 + r3
            int r3 = r2.length
            byte[] r1 = org.bouncycastle.util.Arrays.copyOfRange((byte[]) r2, (int) r1, (int) r3)
            r0.pk_encoded = r1
        L_0x018f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.pqc.crypto.rainbow.RainbowPrivateKeyParameters.<init>(org.bouncycastle.pqc.crypto.rainbow.RainbowParameters, byte[]):void");
    }

    RainbowPrivateKeyParameters(RainbowParameters rainbowParameters, byte[] bArr, byte[] bArr2, byte[] bArr3) {
        super(true, rainbowParameters);
        RainbowPrivateKeyParameters generatePrivateKey = new RainbowKeyComputation(rainbowParameters, bArr, bArr2).generatePrivateKey();
        this.pk_seed = bArr;
        this.pk_encoded = bArr3;
        this.sk_seed = bArr2;
        this.s1 = generatePrivateKey.s1;
        this.t1 = generatePrivateKey.t1;
        this.t3 = generatePrivateKey.t3;
        this.t4 = generatePrivateKey.t4;
        this.l1_F1 = generatePrivateKey.l1_F1;
        this.l1_F2 = generatePrivateKey.l1_F2;
        this.l2_F1 = generatePrivateKey.l2_F1;
        this.l2_F2 = generatePrivateKey.l2_F2;
        this.l2_F3 = generatePrivateKey.l2_F3;
        this.l2_F5 = generatePrivateKey.l2_F5;
        this.l2_F6 = generatePrivateKey.l2_F6;
    }

    RainbowPrivateKeyParameters(RainbowParameters rainbowParameters, byte[] bArr, short[][] sArr, short[][] sArr2, short[][] sArr3, short[][] sArr4, short[][][] sArr5, short[][][] sArr6, short[][][] sArr7, short[][][] sArr8, short[][][] sArr9, short[][][] sArr10, short[][][] sArr11, byte[] bArr2) {
        super(true, rainbowParameters);
        this.pk_seed = null;
        this.pk_encoded = bArr2;
        this.sk_seed = (byte[]) bArr.clone();
        this.s1 = RainbowUtil.cloneArray(sArr);
        this.t1 = RainbowUtil.cloneArray(sArr2);
        this.t3 = RainbowUtil.cloneArray(sArr3);
        this.t4 = RainbowUtil.cloneArray(sArr4);
        this.l1_F1 = RainbowUtil.cloneArray(sArr5);
        this.l1_F2 = RainbowUtil.cloneArray(sArr6);
        this.l2_F1 = RainbowUtil.cloneArray(sArr7);
        this.l2_F2 = RainbowUtil.cloneArray(sArr8);
        this.l2_F3 = RainbowUtil.cloneArray(sArr9);
        this.l2_F5 = RainbowUtil.cloneArray(sArr10);
        this.l2_F6 = RainbowUtil.cloneArray(sArr11);
    }

    public byte[] getEncoded() {
        return getParameters().getVersion() == Version.COMPRESSED ? Arrays.concatenate(this.pk_seed, this.sk_seed) : Arrays.concatenate(getPrivateKey(), this.pk_encoded);
    }

    /* access modifiers changed from: package-private */
    public short[][][] getL1_F1() {
        return RainbowUtil.cloneArray(this.l1_F1);
    }

    /* access modifiers changed from: package-private */
    public short[][][] getL1_F2() {
        return RainbowUtil.cloneArray(this.l1_F2);
    }

    /* access modifiers changed from: package-private */
    public short[][][] getL2_F1() {
        return RainbowUtil.cloneArray(this.l2_F1);
    }

    /* access modifiers changed from: package-private */
    public short[][][] getL2_F2() {
        return RainbowUtil.cloneArray(this.l2_F2);
    }

    /* access modifiers changed from: package-private */
    public short[][][] getL2_F3() {
        return RainbowUtil.cloneArray(this.l2_F3);
    }

    /* access modifiers changed from: package-private */
    public short[][][] getL2_F5() {
        return RainbowUtil.cloneArray(this.l2_F5);
    }

    /* access modifiers changed from: package-private */
    public short[][][] getL2_F6() {
        return RainbowUtil.cloneArray(this.l2_F6);
    }

    public byte[] getPrivateKey() {
        return getParameters().getVersion() == Version.COMPRESSED ? Arrays.concatenate(this.pk_seed, this.sk_seed) : Arrays.concatenate(Arrays.concatenate(Arrays.concatenate(Arrays.concatenate(Arrays.concatenate(Arrays.concatenate(Arrays.concatenate(Arrays.concatenate(Arrays.concatenate(Arrays.concatenate(Arrays.concatenate(this.sk_seed, RainbowUtil.getEncoded(this.s1)), RainbowUtil.getEncoded(this.t1)), RainbowUtil.getEncoded(this.t4)), RainbowUtil.getEncoded(this.t3)), RainbowUtil.getEncoded(this.l1_F1, true)), RainbowUtil.getEncoded(this.l1_F2, false)), RainbowUtil.getEncoded(this.l2_F1, true)), RainbowUtil.getEncoded(this.l2_F2, false)), RainbowUtil.getEncoded(this.l2_F3, false)), RainbowUtil.getEncoded(this.l2_F5, true)), RainbowUtil.getEncoded(this.l2_F6, false));
    }

    public byte[] getPublicKey() {
        return this.pk_encoded;
    }

    /* access modifiers changed from: package-private */
    public short[][] getS1() {
        return RainbowUtil.cloneArray(this.s1);
    }

    /* access modifiers changed from: package-private */
    public byte[] getSk_seed() {
        return Arrays.clone(this.sk_seed);
    }

    /* access modifiers changed from: package-private */
    public short[][] getT1() {
        return RainbowUtil.cloneArray(this.t1);
    }

    /* access modifiers changed from: package-private */
    public short[][] getT3() {
        return RainbowUtil.cloneArray(this.t3);
    }

    /* access modifiers changed from: package-private */
    public short[][] getT4() {
        return RainbowUtil.cloneArray(this.t4);
    }
}
