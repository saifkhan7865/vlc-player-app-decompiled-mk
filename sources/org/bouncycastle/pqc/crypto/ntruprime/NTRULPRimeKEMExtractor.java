package org.bouncycastle.pqc.crypto.ntruprime;

import org.bouncycastle.crypto.EncapsulatedSecretExtractor;
import org.bouncycastle.util.Arrays;

public class NTRULPRimeKEMExtractor implements EncapsulatedSecretExtractor {
    private final NTRULPRimePrivateKeyParameters privateKey;

    public NTRULPRimeKEMExtractor(NTRULPRimePrivateKeyParameters nTRULPRimePrivateKeyParameters) {
        this.privateKey = nTRULPRimePrivateKeyParameters;
    }

    public byte[] extractSecret(byte[] bArr) {
        byte[] bArr2 = bArr;
        NTRULPRimeParameters parameters = this.privateKey.getParameters();
        int p = parameters.getP();
        int q = parameters.getQ();
        int w = parameters.getW();
        int roundedPolynomialBytes = parameters.getRoundedPolynomialBytes();
        int tau0 = parameters.getTau0();
        int tau1 = parameters.getTau1();
        int tau2 = parameters.getTau2();
        int tau3 = parameters.getTau3();
        byte[] bArr3 = new byte[p];
        Utils.getDecodedSmallPolynomial(bArr3, this.privateKey.getEncoded(), p);
        byte[] bArr4 = new byte[roundedPolynomialBytes];
        System.arraycopy(bArr2, 0, bArr4, 0, roundedPolynomialBytes);
        short[] sArr = new short[p];
        Utils.getRoundedDecodedPolynomial(sArr, bArr4, p, q);
        int i = tau1;
        byte[] bArr5 = new byte[128];
        System.arraycopy(bArr2, roundedPolynomialBytes, bArr5, 0, 128);
        int i2 = tau0;
        byte[] bArr6 = new byte[256];
        Utils.getTopDecodedPolynomial(bArr6, bArr5);
        short[] sArr2 = new short[p];
        Utils.multiplicationInRQ(sArr2, sArr, bArr3, p, q);
        byte[] bArr7 = new byte[256];
        byte[] bArr8 = bArr7;
        Utils.right(bArr7, sArr2, bArr6, q, w, tau2, tau3);
        byte[] bArr9 = new byte[32];
        Utils.getEncodedInputs(bArr9, bArr8);
        int publicKeyBytes = parameters.getPublicKeyBytes() - 32;
        byte[] bArr10 = new byte[publicKeyBytes];
        System.arraycopy(this.privateKey.getPk(), 32, bArr10, 0, publicKeyBytes);
        short[] sArr3 = new short[p];
        Utils.getRoundedDecodedPolynomial(sArr3, bArr10, p, q);
        byte[] bArr11 = new byte[32];
        System.arraycopy(this.privateKey.getPk(), 0, bArr11, 0, 32);
        short[] sArr4 = new short[p];
        Utils.generatePolynomialInRQFromSeed(sArr4, bArr11, p, q);
        byte[] hashWithPrefix = Utils.getHashWithPrefix(new byte[]{5}, bArr9);
        int[] iArr = new int[p];
        Utils.expand(iArr, Arrays.copyOfRange(hashWithPrefix, 0, hashWithPrefix.length / 2));
        byte[] bArr12 = new byte[p];
        Utils.sortGenerateShortPolynomial(bArr12, iArr, p, w);
        short[] sArr5 = new short[p];
        Utils.multiplicationInRQ(sArr5, sArr4, bArr12, p, q);
        short[] sArr6 = new short[p];
        Utils.roundPolynomial(sArr6, sArr5);
        Utils.getRoundedEncodedPolynomial(new byte[roundedPolynomialBytes], sArr6, p, q);
        short[] sArr7 = new short[p];
        Utils.multiplicationInRQ(sArr7, sArr3, bArr12, p, q);
        byte[] bArr13 = bArr9;
        Utils.top(new byte[256], sArr7, bArr8, q, i2, i);
        Utils.getTopEncodedPolynomial(new byte[128], bArr6);
        byte[] bArr14 = new byte[(this.privateKey.getHash().length + 32)];
        System.arraycopy(bArr13, 0, bArr14, 0, 32);
        System.arraycopy(this.privateKey.getHash(), 0, bArr14, 32, this.privateKey.getHash().length);
        byte[] hashWithPrefix2 = Utils.getHashWithPrefix(new byte[]{2}, bArr14);
        int i3 = roundedPolynomialBytes + 128;
        int length = (hashWithPrefix2.length / 2) + i3;
        byte[] bArr15 = new byte[length];
        System.arraycopy(bArr4, 0, bArr15, 0, roundedPolynomialBytes);
        System.arraycopy(bArr5, 0, bArr15, roundedPolynomialBytes, 128);
        System.arraycopy(hashWithPrefix2, 0, bArr15, i3, hashWithPrefix2.length / 2);
        Utils.updateDiffMask(bArr13, this.privateKey.getRho(), Arrays.areEqual(bArr, bArr15) ? 0 : -1);
        byte[] bArr16 = new byte[(32 + length)];
        System.arraycopy(bArr13, 0, bArr16, 0, 32);
        System.arraycopy(bArr15, 0, bArr16, 32, length);
        return Arrays.copyOfRange(Utils.getHashWithPrefix(new byte[]{1}, bArr16), 0, parameters.getSessionKeySize() / 8);
    }

    public int getEncapsulationLength() {
        return this.privateKey.getParameters().getRoundedPolynomialBytes() + 160;
    }
}
