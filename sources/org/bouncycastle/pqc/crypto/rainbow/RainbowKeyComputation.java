package org.bouncycastle.pqc.crypto.rainbow;

import java.security.SecureRandom;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.util.Arrays;

class RainbowKeyComputation {
    ComputeInField cf = new ComputeInField();
    private short[][][] l1_F1;
    private short[][][] l1_F2;
    private short[][][] l1_Q1;
    private short[][][] l1_Q2;
    private short[][][] l1_Q3;
    private short[][][] l1_Q5;
    private short[][][] l1_Q6;
    private short[][][] l1_Q9;
    private short[][][] l2_F1;
    private short[][][] l2_F2;
    private short[][][] l2_F3;
    private short[][][] l2_F5;
    private short[][][] l2_F6;
    private short[][][] l2_Q1;
    private short[][][] l2_Q2;
    private short[][][] l2_Q3;
    private short[][][] l2_Q5;
    private short[][][] l2_Q6;
    private short[][][] l2_Q9;
    private int o1;
    private int o2;
    private byte[] pk_seed;
    private RainbowParameters rainbowParams;
    private SecureRandom random;
    private short[][] s1;
    private byte[] sk_seed;
    private short[][] t1;
    private short[][] t2;
    private short[][] t3;
    private short[][] t4;
    private int v1;
    private Version version;

    public RainbowKeyComputation(RainbowParameters rainbowParameters, SecureRandom secureRandom) {
        this.rainbowParams = rainbowParameters;
        this.random = secureRandom;
        this.version = rainbowParameters.getVersion();
        this.v1 = this.rainbowParams.getV1();
        this.o1 = this.rainbowParams.getO1();
        this.o2 = this.rainbowParams.getO2();
    }

    public RainbowKeyComputation(RainbowParameters rainbowParameters, byte[] bArr, byte[] bArr2) {
        this.rainbowParams = rainbowParameters;
        this.random = null;
        this.version = rainbowParameters.getVersion();
        this.pk_seed = bArr;
        this.sk_seed = bArr2;
        this.v1 = this.rainbowParams.getV1();
        this.o1 = this.rainbowParams.getO1();
        this.o2 = this.rainbowParams.getO2();
    }

    private void calculate_F_from_Q() {
        this.l1_F1 = RainbowUtil.cloneArray(this.l1_Q1);
        this.l1_F2 = new short[this.o1][][];
        for (int i = 0; i < this.o1; i++) {
            this.l1_F2[i] = this.cf.addMatrixTranspose(this.l1_Q1[i]);
            short[][][] sArr = this.l1_F2;
            sArr[i] = this.cf.multiplyMatrix(sArr[i], this.t1);
            short[][][] sArr2 = this.l1_F2;
            sArr2[i] = this.cf.addMatrix(sArr2[i], this.l1_Q2[i]);
        }
        int i2 = this.o2;
        this.l2_F2 = new short[i2][][];
        this.l2_F3 = new short[i2][][];
        this.l2_F5 = new short[i2][][];
        this.l2_F6 = new short[i2][][];
        this.l2_F1 = RainbowUtil.cloneArray(this.l2_Q1);
        for (int i3 = 0; i3 < this.o2; i3++) {
            short[][] addMatrixTranspose = this.cf.addMatrixTranspose(this.l2_Q1[i3]);
            this.l2_F2[i3] = this.cf.multiplyMatrix(addMatrixTranspose, this.t1);
            short[][][] sArr3 = this.l2_F2;
            sArr3[i3] = this.cf.addMatrix(sArr3[i3], this.l2_Q2[i3]);
            this.l2_F3[i3] = this.cf.multiplyMatrix(addMatrixTranspose, this.t4);
            short[][] multiplyMatrix = this.cf.multiplyMatrix(this.l2_Q2[i3], this.t3);
            short[][][] sArr4 = this.l2_F3;
            sArr4[i3] = this.cf.addMatrix(sArr4[i3], multiplyMatrix);
            short[][][] sArr5 = this.l2_F3;
            sArr5[i3] = this.cf.addMatrix(sArr5[i3], this.l2_Q3[i3]);
            short[][] addMatrix = this.cf.addMatrix(this.cf.multiplyMatrix(this.l2_Q1[i3], this.t1), this.l2_Q2[i3]);
            short[][] transpose = this.cf.transpose(this.t1);
            this.l2_F5[i3] = this.cf.multiplyMatrix(transpose, addMatrix);
            short[][][] sArr6 = this.l2_F5;
            sArr6[i3] = this.cf.addMatrix(sArr6[i3], this.l2_Q5[i3]);
            short[][][] sArr7 = this.l2_F5;
            sArr7[i3] = this.cf.to_UT(sArr7[i3]);
            this.l2_F6[i3] = this.cf.multiplyMatrix(transpose, this.l2_F3[i3]);
            ComputeInField computeInField = this.cf;
            short[][] multiplyMatrix2 = computeInField.multiplyMatrix(computeInField.transpose(this.l2_Q2[i3]), this.t4);
            short[][][] sArr8 = this.l2_F6;
            sArr8[i3] = this.cf.addMatrix(sArr8[i3], multiplyMatrix2);
            short[][] multiplyMatrix3 = this.cf.multiplyMatrix(this.cf.addMatrixTranspose(this.l2_Q5[i3]), this.t3);
            short[][][] sArr9 = this.l2_F6;
            sArr9[i3] = this.cf.addMatrix(sArr9[i3], multiplyMatrix3);
            short[][][] sArr10 = this.l2_F6;
            sArr10[i3] = this.cf.addMatrix(sArr10[i3], this.l2_Q6[i3]);
        }
    }

    private void calculate_Q_from_F() {
        short[][] transpose = this.cf.transpose(this.t1);
        short[][] transpose2 = this.cf.transpose(this.t2);
        this.l1_Q1 = RainbowUtil.cloneArray(this.l1_F1);
        this.l1_Q2 = new short[this.o1][][];
        for (int i = 0; i < this.o1; i++) {
            this.l1_Q2[i] = this.cf.addMatrixTranspose(this.l1_F1[i]);
            short[][][] sArr = this.l1_Q2;
            sArr[i] = this.cf.multiplyMatrix(sArr[i], this.t1);
            short[][][] sArr2 = this.l1_Q2;
            sArr2[i] = this.cf.addMatrix(sArr2[i], this.l1_F2[i]);
        }
        calculate_l1_Q3569(transpose, transpose2);
        int i2 = this.o2;
        this.l2_Q2 = new short[i2][][];
        this.l2_Q3 = new short[i2][][];
        this.l2_Q5 = new short[i2][][];
        this.l2_Q6 = new short[i2][][];
        this.l2_Q1 = RainbowUtil.cloneArray(this.l2_F1);
        for (int i3 = 0; i3 < this.o2; i3++) {
            short[][] addMatrixTranspose = this.cf.addMatrixTranspose(this.l2_F1[i3]);
            this.l2_Q2[i3] = this.cf.multiplyMatrix(addMatrixTranspose, this.t1);
            short[][][] sArr3 = this.l2_Q2;
            sArr3[i3] = this.cf.addMatrix(sArr3[i3], this.l2_F2[i3]);
            this.l2_Q3[i3] = this.cf.multiplyMatrix(addMatrixTranspose, this.t2);
            short[][] multiplyMatrix = this.cf.multiplyMatrix(this.l2_F2[i3], this.t3);
            short[][][] sArr4 = this.l2_Q3;
            sArr4[i3] = this.cf.addMatrix(sArr4[i3], multiplyMatrix);
            short[][][] sArr5 = this.l2_Q3;
            sArr5[i3] = this.cf.addMatrix(sArr5[i3], this.l2_F3[i3]);
            this.l2_Q5[i3] = this.cf.multiplyMatrix(transpose, this.cf.addMatrix(this.cf.multiplyMatrix(this.l2_F1[i3], this.t1), this.l2_F2[i3]));
            short[][][] sArr6 = this.l2_Q5;
            sArr6[i3] = this.cf.addMatrix(sArr6[i3], this.l2_F5[i3]);
            short[][][] sArr7 = this.l2_Q5;
            sArr7[i3] = this.cf.to_UT(sArr7[i3]);
            this.l2_Q6[i3] = this.cf.multiplyMatrix(transpose, this.l2_Q3[i3]);
            ComputeInField computeInField = this.cf;
            short[][] multiplyMatrix2 = computeInField.multiplyMatrix(computeInField.transpose(this.l2_F2[i3]), this.t2);
            short[][][] sArr8 = this.l2_Q6;
            sArr8[i3] = this.cf.addMatrix(sArr8[i3], multiplyMatrix2);
            short[][] multiplyMatrix3 = this.cf.multiplyMatrix(this.cf.addMatrixTranspose(this.l2_F5[i3]), this.t3);
            short[][][] sArr9 = this.l2_Q6;
            sArr9[i3] = this.cf.addMatrix(sArr9[i3], multiplyMatrix3);
            short[][][] sArr10 = this.l2_Q6;
            sArr10[i3] = this.cf.addMatrix(sArr10[i3], this.l2_F6[i3]);
        }
        calculate_l2_Q9(transpose2);
    }

    private void calculate_Q_from_F_cyclic() {
        short[][] transpose = this.cf.transpose(this.t1);
        short[][] transpose2 = this.cf.transpose(this.t2);
        calculate_l1_Q3569(transpose, transpose2);
        calculate_l2_Q9(transpose2);
    }

    private void calculate_l1_Q3569(short[][] sArr, short[][] sArr2) {
        int i = this.o1;
        this.l1_Q3 = new short[i][][];
        this.l1_Q5 = new short[i][][];
        this.l1_Q6 = new short[i][][];
        this.l1_Q9 = new short[i][][];
        for (int i2 = 0; i2 < this.o1; i2++) {
            short[][] multiplyMatrix = this.cf.multiplyMatrix(this.l1_F2[i2], this.t3);
            this.l1_Q3[i2] = this.cf.addMatrixTranspose(this.l1_F1[i2]);
            short[][][] sArr3 = this.l1_Q3;
            sArr3[i2] = this.cf.multiplyMatrix(sArr3[i2], this.t2);
            short[][][] sArr4 = this.l1_Q3;
            sArr4[i2] = this.cf.addMatrix(sArr4[i2], multiplyMatrix);
            this.l1_Q5[i2] = this.cf.multiplyMatrix(this.l1_F1[i2], this.t1);
            short[][][] sArr5 = this.l1_Q5;
            sArr5[i2] = this.cf.addMatrix(sArr5[i2], this.l1_F2[i2]);
            short[][][] sArr6 = this.l1_Q5;
            sArr6[i2] = this.cf.multiplyMatrix(sArr, sArr6[i2]);
            short[][][] sArr7 = this.l1_Q5;
            sArr7[i2] = this.cf.to_UT(sArr7[i2]);
            ComputeInField computeInField = this.cf;
            short[][] multiplyMatrix2 = computeInField.multiplyMatrix(computeInField.transpose(this.l1_F2[i2]), this.t2);
            this.l1_Q6[i2] = this.cf.multiplyMatrix(sArr, this.l1_Q3[i2]);
            short[][][] sArr8 = this.l1_Q6;
            sArr8[i2] = this.cf.addMatrix(sArr8[i2], multiplyMatrix2);
            this.l1_Q9[i2] = this.cf.addMatrix(this.cf.multiplyMatrix(this.l1_F1[i2], this.t2), multiplyMatrix);
            short[][][] sArr9 = this.l1_Q9;
            sArr9[i2] = this.cf.multiplyMatrix(sArr2, sArr9[i2]);
            short[][][] sArr10 = this.l1_Q9;
            sArr10[i2] = this.cf.to_UT(sArr10[i2]);
        }
    }

    private void calculate_l2_Q9(short[][] sArr) {
        this.l2_Q9 = new short[this.o2][][];
        for (int i = 0; i < this.o2; i++) {
            this.l2_Q9[i] = this.cf.multiplyMatrix(this.l2_F1[i], this.t2);
            short[][] multiplyMatrix = this.cf.multiplyMatrix(this.l2_F2[i], this.t3);
            short[][][] sArr2 = this.l2_Q9;
            sArr2[i] = this.cf.addMatrix(sArr2[i], multiplyMatrix);
            short[][][] sArr3 = this.l2_Q9;
            sArr3[i] = this.cf.addMatrix(sArr3[i], this.l2_F3[i]);
            short[][][] sArr4 = this.l2_Q9;
            sArr4[i] = this.cf.multiplyMatrix(sArr, sArr4[i]);
            short[][] addMatrix = this.cf.addMatrix(this.cf.multiplyMatrix(this.l2_F5[i], this.t3), this.l2_F6[i]);
            ComputeInField computeInField = this.cf;
            short[][] multiplyMatrix2 = computeInField.multiplyMatrix(computeInField.transpose(this.t3), addMatrix);
            short[][][] sArr5 = this.l2_Q9;
            sArr5[i] = this.cf.addMatrix(sArr5[i], multiplyMatrix2);
            short[][][] sArr6 = this.l2_Q9;
            sArr6[i] = this.cf.to_UT(sArr6[i]);
        }
    }

    private void calculate_t4() {
        this.t4 = this.cf.addMatrix(this.cf.multiplyMatrix(this.t1, this.t3), this.t2);
    }

    private void genKeyMaterial() {
        byte[] bArr = new byte[this.rainbowParams.getLen_skseed()];
        this.sk_seed = bArr;
        this.random.nextBytes(bArr);
        RainbowDRBG rainbowDRBG = new RainbowDRBG(this.sk_seed, this.rainbowParams.getHash_algo());
        generate_S_and_T(rainbowDRBG);
        int i = this.o1;
        int i2 = this.v1;
        this.l1_F1 = RainbowUtil.generate_random(rainbowDRBG, i, i2, i2, true);
        int i3 = this.o1;
        this.l1_F2 = RainbowUtil.generate_random(rainbowDRBG, i3, this.v1, i3, false);
        int i4 = this.o2;
        int i5 = this.v1;
        this.l2_F1 = RainbowUtil.generate_random(rainbowDRBG, i4, i5, i5, true);
        this.l2_F2 = RainbowUtil.generate_random(rainbowDRBG, this.o2, this.v1, this.o1, false);
        int i6 = this.o2;
        this.l2_F3 = RainbowUtil.generate_random(rainbowDRBG, i6, this.v1, i6, false);
        int i7 = this.o2;
        int i8 = this.o1;
        this.l2_F5 = RainbowUtil.generate_random(rainbowDRBG, i7, i8, i8, true);
        int i9 = this.o2;
        this.l2_F6 = RainbowUtil.generate_random(rainbowDRBG, i9, this.o1, i9, false);
        calculate_Q_from_F();
        calculate_t4();
        this.l1_Q1 = this.cf.obfuscate_l1_polys(this.s1, this.l2_Q1, this.l1_Q1);
        this.l1_Q2 = this.cf.obfuscate_l1_polys(this.s1, this.l2_Q2, this.l1_Q2);
        this.l1_Q3 = this.cf.obfuscate_l1_polys(this.s1, this.l2_Q3, this.l1_Q3);
        this.l1_Q5 = this.cf.obfuscate_l1_polys(this.s1, this.l2_Q5, this.l1_Q5);
        this.l1_Q6 = this.cf.obfuscate_l1_polys(this.s1, this.l2_Q6, this.l1_Q6);
        this.l1_Q9 = this.cf.obfuscate_l1_polys(this.s1, this.l2_Q9, this.l1_Q9);
    }

    private void genKeyMaterial_cyclic() {
        byte[] bArr = new byte[this.rainbowParams.getLen_skseed()];
        this.sk_seed = bArr;
        this.random.nextBytes(bArr);
        byte[] bArr2 = new byte[this.rainbowParams.getLen_pkseed()];
        this.pk_seed = bArr2;
        this.random.nextBytes(bArr2);
        genPrivateKeyMaterial_cyclic();
        calculate_Q_from_F_cyclic();
        this.l1_Q3 = this.cf.obfuscate_l1_polys(this.s1, this.l2_Q3, this.l1_Q3);
        this.l1_Q5 = this.cf.obfuscate_l1_polys(this.s1, this.l2_Q5, this.l1_Q5);
        this.l1_Q6 = this.cf.obfuscate_l1_polys(this.s1, this.l2_Q6, this.l1_Q6);
        this.l1_Q9 = this.cf.obfuscate_l1_polys(this.s1, this.l2_Q9, this.l1_Q9);
    }

    private void genPrivateKeyMaterial_cyclic() {
        RainbowDRBG rainbowDRBG = new RainbowDRBG(this.sk_seed, this.rainbowParams.getHash_algo());
        RainbowDRBG rainbowDRBG2 = new RainbowDRBG(this.pk_seed, this.rainbowParams.getHash_algo());
        generate_S_and_T(rainbowDRBG);
        calculate_t4();
        generate_B1_and_B2(rainbowDRBG2);
        this.l1_Q1 = this.cf.obfuscate_l1_polys(this.s1, this.l2_Q1, this.l1_Q1);
        this.l1_Q2 = this.cf.obfuscate_l1_polys(this.s1, this.l2_Q2, this.l1_Q2);
        calculate_F_from_Q();
    }

    private void generate_B1_and_B2(SecureRandom secureRandom) {
        int i = this.o1;
        int i2 = this.v1;
        this.l1_Q1 = RainbowUtil.generate_random(secureRandom, i, i2, i2, true);
        int i3 = this.o1;
        this.l1_Q2 = RainbowUtil.generate_random(secureRandom, i3, this.v1, i3, false);
        int i4 = this.o2;
        int i5 = this.v1;
        this.l2_Q1 = RainbowUtil.generate_random(secureRandom, i4, i5, i5, true);
        this.l2_Q2 = RainbowUtil.generate_random(secureRandom, this.o2, this.v1, this.o1, false);
        int i6 = this.o2;
        this.l2_Q3 = RainbowUtil.generate_random(secureRandom, i6, this.v1, i6, false);
        int i7 = this.o2;
        int i8 = this.o1;
        this.l2_Q5 = RainbowUtil.generate_random(secureRandom, i7, i8, i8, true);
        int i9 = this.o2;
        this.l2_Q6 = RainbowUtil.generate_random(secureRandom, i9, this.o1, i9, false);
    }

    private void generate_S_and_T(SecureRandom secureRandom) {
        this.s1 = RainbowUtil.generate_random_2d(secureRandom, this.o1, this.o2);
        this.t1 = RainbowUtil.generate_random_2d(secureRandom, this.v1, this.o1);
        this.t2 = RainbowUtil.generate_random_2d(secureRandom, this.v1, this.o2);
        this.t3 = RainbowUtil.generate_random_2d(secureRandom, this.o1, this.o2);
    }

    public AsymmetricCipherKeyPair genKeyPairCircumzenithal() {
        genKeyMaterial_cyclic();
        RainbowPublicKeyParameters rainbowPublicKeyParameters = new RainbowPublicKeyParameters(this.rainbowParams, this.pk_seed, this.l1_Q3, this.l1_Q5, this.l1_Q6, this.l1_Q9, this.l2_Q9);
        RainbowParameters rainbowParameters = this.rainbowParams;
        byte[] bArr = this.sk_seed;
        short[][] sArr = this.s1;
        short[][] sArr2 = this.t1;
        short[][] sArr3 = this.t3;
        short[][] sArr4 = this.t4;
        short[][][] sArr5 = this.l1_F1;
        short[][][] sArr6 = this.l1_F2;
        short[][][] sArr7 = this.l2_F1;
        short[][][] sArr8 = this.l2_F2;
        short[][][] sArr9 = this.l2_F3;
        short[][][] sArr10 = this.l2_F5;
        return new AsymmetricCipherKeyPair((AsymmetricKeyParameter) rainbowPublicKeyParameters, (AsymmetricKeyParameter) new RainbowPrivateKeyParameters(rainbowParameters, bArr, sArr, sArr2, sArr3, sArr4, sArr5, sArr6, sArr7, sArr8, sArr9, sArr10, this.l2_F6, rainbowPublicKeyParameters.getEncoded()));
    }

    public AsymmetricCipherKeyPair genKeyPairClassical() {
        genKeyMaterial();
        RainbowPublicKeyParameters rainbowPublicKeyParameters = new RainbowPublicKeyParameters(this.rainbowParams, this.l1_Q1, this.l1_Q2, this.l1_Q3, this.l1_Q5, this.l1_Q6, this.l1_Q9, this.l2_Q1, this.l2_Q2, this.l2_Q3, this.l2_Q5, this.l2_Q6, this.l2_Q9);
        RainbowParameters rainbowParameters = this.rainbowParams;
        byte[] bArr = this.sk_seed;
        short[][] sArr = this.s1;
        short[][] sArr2 = this.t1;
        short[][] sArr3 = this.t3;
        short[][] sArr4 = this.t4;
        short[][][] sArr5 = this.l1_F1;
        short[][][] sArr6 = this.l1_F2;
        short[][][] sArr7 = this.l2_F1;
        short[][][] sArr8 = this.l2_F2;
        short[][][] sArr9 = this.l2_F3;
        short[][][] sArr10 = this.l2_F5;
        return new AsymmetricCipherKeyPair((AsymmetricKeyParameter) rainbowPublicKeyParameters, (AsymmetricKeyParameter) new RainbowPrivateKeyParameters(rainbowParameters, bArr, sArr, sArr2, sArr3, sArr4, sArr5, sArr6, sArr7, sArr8, sArr9, sArr10, this.l2_F6, rainbowPublicKeyParameters.getEncoded()));
    }

    public AsymmetricCipherKeyPair genKeyPairCompressed() {
        genKeyMaterial_cyclic();
        RainbowPublicKeyParameters rainbowPublicKeyParameters = new RainbowPublicKeyParameters(this.rainbowParams, this.pk_seed, this.l1_Q3, this.l1_Q5, this.l1_Q6, this.l1_Q9, this.l2_Q9);
        return new AsymmetricCipherKeyPair((AsymmetricKeyParameter) rainbowPublicKeyParameters, (AsymmetricKeyParameter) new RainbowPrivateKeyParameters(this.rainbowParams, this.pk_seed, this.sk_seed, rainbowPublicKeyParameters.getEncoded()));
    }

    /* access modifiers changed from: package-private */
    public RainbowPrivateKeyParameters generatePrivateKey() {
        this.sk_seed = Arrays.clone(this.sk_seed);
        this.pk_seed = Arrays.clone(this.pk_seed);
        genPrivateKeyMaterial_cyclic();
        return new RainbowPrivateKeyParameters(this.rainbowParams, this.sk_seed, this.s1, this.t1, this.t3, this.t4, this.l1_F1, this.l1_F2, this.l2_F1, this.l2_F2, this.l2_F3, this.l2_F5, this.l2_F6, (byte[]) null);
    }
}
