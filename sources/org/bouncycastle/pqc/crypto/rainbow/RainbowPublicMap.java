package org.bouncycastle.pqc.crypto.rainbow;

import java.lang.reflect.Array;
import org.bouncycastle.util.Arrays;

class RainbowPublicMap {
    private ComputeInField cf = new ComputeInField();
    private final int num_gf_elements = 256;
    private RainbowParameters params;

    public RainbowPublicMap(RainbowParameters rainbowParameters) {
        this.params = rainbowParameters;
    }

    private short[] add_and_reduce(short[][] sArr) {
        int m = this.params.getM();
        short[] sArr2 = new short[m];
        for (int i = 0; i < 8; i++) {
            int pow = (int) Math.pow(2.0d, (double) i);
            short[] sArr3 = new short[m];
            for (int i2 = pow; i2 < 256; i2 += pow * 2) {
                for (int i3 = 0; i3 < pow; i3++) {
                    sArr3 = this.cf.addVect(sArr3, sArr[i2 + i3]);
                }
            }
            ComputeInField computeInField = this.cf;
            sArr2 = computeInField.addVect(sArr2, computeInField.multVect((short) pow, sArr3));
        }
        return sArr2;
    }

    private short[][] compute_accumulator(short[] sArr, short[] sArr2, short[][][] sArr3, int i) {
        int[] iArr = new int[2];
        iArr[1] = i;
        iArr[0] = 256;
        short[][] sArr4 = (short[][]) Array.newInstance(Short.TYPE, iArr);
        int length = sArr2.length;
        short[][] sArr5 = sArr3[0];
        if (length == sArr5.length && sArr.length == sArr5[0].length && sArr3.length == i) {
            for (int i2 = 0; i2 < sArr2.length; i2++) {
                short[] multVect = this.cf.multVect(sArr2[i2], sArr);
                for (int i3 = 0; i3 < sArr.length; i3++) {
                    for (int i4 = 0; i4 < sArr3.length; i4++) {
                        short s = multVect[i3];
                        if (s != 0) {
                            short[] sArr6 = sArr4[s];
                            sArr6[i4] = GF2Field.addElem(sArr6[i4], sArr3[i4][i2][i3]);
                        }
                    }
                }
            }
            return sArr4;
        }
        throw new RuntimeException("Accumulator calculation not possible!");
    }

    public short[] publicMap(RainbowPublicKeyParameters rainbowPublicKeyParameters, short[] sArr) {
        return add_and_reduce(compute_accumulator(sArr, sArr, rainbowPublicKeyParameters.pk, this.params.getM()));
    }

    public short[] publicMap_cyclic(RainbowPublicKeyParameters rainbowPublicKeyParameters, short[] sArr) {
        RainbowPublicKeyParameters rainbowPublicKeyParameters2 = rainbowPublicKeyParameters;
        short[] sArr2 = sArr;
        int v1 = this.params.getV1();
        int o1 = this.params.getO1();
        int o2 = this.params.getO2();
        int[] iArr = new int[2];
        iArr[1] = o1 + o2;
        iArr[0] = 256;
        short[][] sArr3 = (short[][]) Array.newInstance(Short.TYPE, iArr);
        short[] copyOfRange = Arrays.copyOfRange(sArr2, 0, v1);
        int i = v1 + o1;
        short[] copyOfRange2 = Arrays.copyOfRange(sArr2, v1, i);
        short[] copyOfRange3 = Arrays.copyOfRange(sArr2, i, sArr2.length);
        RainbowDRBG rainbowDRBG = new RainbowDRBG(rainbowPublicKeyParameters2.pk_seed, rainbowPublicKeyParameters.getParameters().getHash_algo());
        short[][] addMatrix = this.cf.addMatrix(this.cf.addMatrix(this.cf.addMatrix(this.cf.addMatrix(this.cf.addMatrix(compute_accumulator(copyOfRange, copyOfRange, RainbowUtil.generate_random(rainbowDRBG, o1, v1, v1, true), o1), compute_accumulator(copyOfRange2, copyOfRange, RainbowUtil.generate_random(rainbowDRBG, o1, v1, o1, false), o1)), compute_accumulator(copyOfRange3, copyOfRange, rainbowPublicKeyParameters2.l1_Q3, o1)), compute_accumulator(copyOfRange2, copyOfRange2, rainbowPublicKeyParameters2.l1_Q5, o1)), compute_accumulator(copyOfRange3, copyOfRange2, rainbowPublicKeyParameters2.l1_Q6, o1)), compute_accumulator(copyOfRange3, copyOfRange3, rainbowPublicKeyParameters2.l1_Q9, o1));
        short[][] addMatrix2 = this.cf.addMatrix(this.cf.addMatrix(this.cf.addMatrix(this.cf.addMatrix(this.cf.addMatrix(compute_accumulator(copyOfRange, copyOfRange, RainbowUtil.generate_random(rainbowDRBG, o2, v1, v1, true), o2), compute_accumulator(copyOfRange2, copyOfRange, RainbowUtil.generate_random(rainbowDRBG, o2, v1, o1, false), o2)), compute_accumulator(copyOfRange3, copyOfRange, RainbowUtil.generate_random(rainbowDRBG, o2, v1, o2, false), o2)), compute_accumulator(copyOfRange2, copyOfRange2, RainbowUtil.generate_random(rainbowDRBG, o2, o1, o1, true), o2)), compute_accumulator(copyOfRange3, copyOfRange2, RainbowUtil.generate_random(rainbowDRBG, o2, o1, o2, false), o2)), compute_accumulator(copyOfRange3, copyOfRange3, rainbowPublicKeyParameters2.l2_Q9, o2));
        for (int i2 = 0; i2 < 256; i2++) {
            sArr3[i2] = Arrays.concatenate(addMatrix[i2], addMatrix2[i2]);
        }
        return add_and_reduce(sArr3);
    }
}
