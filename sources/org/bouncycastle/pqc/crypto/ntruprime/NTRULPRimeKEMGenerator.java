package org.bouncycastle.pqc.crypto.ntruprime;

import java.security.SecureRandom;
import org.bouncycastle.crypto.EncapsulatedSecretGenerator;
import org.bouncycastle.crypto.SecretWithEncapsulation;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.pqc.crypto.util.SecretWithEncapsulationImpl;
import org.bouncycastle.util.Arrays;

public class NTRULPRimeKEMGenerator implements EncapsulatedSecretGenerator {
    private final SecureRandom random;

    public NTRULPRimeKEMGenerator(SecureRandom secureRandom) {
        this.random = secureRandom;
    }

    public SecretWithEncapsulation generateEncapsulated(AsymmetricKeyParameter asymmetricKeyParameter) {
        NTRULPRimePublicKeyParameters nTRULPRimePublicKeyParameters = (NTRULPRimePublicKeyParameters) asymmetricKeyParameter;
        NTRULPRimeParameters parameters = nTRULPRimePublicKeyParameters.getParameters();
        int p = parameters.getP();
        int q = parameters.getQ();
        int w = parameters.getW();
        int roundedPolynomialBytes = parameters.getRoundedPolynomialBytes();
        int tau0 = parameters.getTau0();
        int tau1 = parameters.getTau1();
        byte[] hashWithPrefix = Utils.getHashWithPrefix(new byte[]{4}, nTRULPRimePublicKeyParameters.getEncoded());
        byte[] bArr = new byte[256];
        Utils.getRandomInputs(this.random, bArr);
        byte[] bArr2 = new byte[32];
        Utils.getEncodedInputs(bArr2, bArr);
        short[] sArr = new short[p];
        Utils.getRoundedDecodedPolynomial(sArr, nTRULPRimePublicKeyParameters.getRoundEncA(), p, q);
        short[] sArr2 = new short[p];
        Utils.generatePolynomialInRQFromSeed(sArr2, nTRULPRimePublicKeyParameters.getSeed(), p, q);
        byte[] hashWithPrefix2 = Utils.getHashWithPrefix(new byte[]{5}, bArr2);
        int[] iArr = new int[p];
        Utils.expand(iArr, Arrays.copyOfRange(hashWithPrefix2, 0, hashWithPrefix2.length / 2));
        byte[] bArr3 = new byte[p];
        Utils.sortGenerateShortPolynomial(bArr3, iArr, p, w);
        short[] sArr3 = new short[p];
        Utils.multiplicationInRQ(sArr3, sArr2, bArr3, p, q);
        short[] sArr4 = new short[p];
        Utils.roundPolynomial(sArr4, sArr3);
        byte[] bArr4 = new byte[roundedPolynomialBytes];
        Utils.getRoundedEncodedPolynomial(bArr4, sArr4, p, q);
        short[] sArr5 = new short[p];
        Utils.multiplicationInRQ(sArr5, sArr, bArr3, p, q);
        byte[] bArr5 = new byte[256];
        Utils.top(bArr5, sArr5, bArr, q, tau0, tau1);
        byte[] bArr6 = new byte[128];
        Utils.getTopEncodedPolynomial(bArr6, bArr5);
        byte[] bArr7 = new byte[(32 + (hashWithPrefix.length / 2))];
        System.arraycopy(bArr2, 0, bArr7, 0, 32);
        System.arraycopy(hashWithPrefix, 0, bArr7, 32, hashWithPrefix.length / 2);
        byte[] hashWithPrefix3 = Utils.getHashWithPrefix(new byte[]{2}, bArr7);
        int i = roundedPolynomialBytes + 128;
        int length = (hashWithPrefix3.length / 2) + i;
        byte[] bArr8 = new byte[length];
        System.arraycopy(bArr4, 0, bArr8, 0, roundedPolynomialBytes);
        System.arraycopy(bArr6, 0, bArr8, roundedPolynomialBytes, 128);
        System.arraycopy(hashWithPrefix3, 0, bArr8, i, hashWithPrefix3.length / 2);
        byte[] bArr9 = new byte[(32 + length)];
        System.arraycopy(bArr2, 0, bArr9, 0, 32);
        System.arraycopy(bArr8, 0, bArr9, 32, length);
        return new SecretWithEncapsulationImpl(Arrays.copyOfRange(Utils.getHashWithPrefix(new byte[]{1}, bArr9), 0, parameters.getSessionKeySize() / 8), bArr8);
    }
}
