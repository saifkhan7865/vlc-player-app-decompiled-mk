package org.bouncycastle.pqc.crypto.rainbow;

import java.lang.reflect.Array;
import java.security.SecureRandom;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.pqc.crypto.MessageSigner;
import org.bouncycastle.util.Arrays;

public class RainbowSigner implements MessageSigner {
    private static final int MAXITS = 65536;
    private ComputeInField cf = new ComputeInField();
    private Digest hashAlgo;
    private RainbowKeyParameters key;
    private SecureRandom random;
    int signableDocumentLength;
    private Version version;

    /* renamed from: org.bouncycastle.pqc.crypto.rainbow.RainbowSigner$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$bouncycastle$pqc$crypto$rainbow$Version;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                org.bouncycastle.pqc.crypto.rainbow.Version[] r0 = org.bouncycastle.pqc.crypto.rainbow.Version.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$bouncycastle$pqc$crypto$rainbow$Version = r0
                org.bouncycastle.pqc.crypto.rainbow.Version r1 = org.bouncycastle.pqc.crypto.rainbow.Version.CLASSIC     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$bouncycastle$pqc$crypto$rainbow$Version     // Catch:{ NoSuchFieldError -> 0x001d }
                org.bouncycastle.pqc.crypto.rainbow.Version r1 = org.bouncycastle.pqc.crypto.rainbow.Version.CIRCUMZENITHAL     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$bouncycastle$pqc$crypto$rainbow$Version     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.bouncycastle.pqc.crypto.rainbow.Version r1 = org.bouncycastle.pqc.crypto.rainbow.Version.COMPRESSED     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.pqc.crypto.rainbow.RainbowSigner.AnonymousClass1.<clinit>():void");
        }
    }

    private byte[] genSignature(byte[] bArr) {
        short[][] sArr;
        byte[] bArr2;
        byte[] bArr3 = bArr;
        byte[] bArr4 = new byte[this.hashAlgo.getDigestSize()];
        this.hashAlgo.update(bArr3, 0, bArr3.length);
        this.hashAlgo.doFinal(bArr4, 0);
        int v1 = this.key.getParameters().getV1();
        int o1 = this.key.getParameters().getO1();
        int o2 = this.key.getParameters().getO2();
        int m = this.key.getParameters().getM();
        int n = this.key.getParameters().getN();
        RainbowPrivateKeyParameters rainbowPrivateKeyParameters = (RainbowPrivateKeyParameters) this.key;
        this.random = new RainbowDRBG(RainbowUtil.hash(this.hashAlgo, rainbowPrivateKeyParameters.sk_seed, bArr4, new byte[this.hashAlgo.getDigestSize()]), rainbowPrivateKeyParameters.getParameters().getHash_algo());
        short[] sArr2 = new short[v1];
        short[][] sArr3 = null;
        short[] sArr4 = new short[o1];
        short[] sArr5 = new short[o2];
        int[] iArr = new int[2];
        iArr[1] = o1;
        iArr[0] = o2;
        short[][] sArr6 = (short[][]) Array.newInstance(Short.TYPE, iArr);
        int[] iArr2 = new int[2];
        iArr2[1] = o2;
        iArr2[0] = o2;
        short[][] sArr7 = (short[][]) Array.newInstance(Short.TYPE, iArr2);
        byte[] bArr5 = new byte[rainbowPrivateKeyParameters.getParameters().getLen_salt()];
        int i = n;
        short[] sArr8 = new short[o1];
        short[] sArr9 = new short[o2];
        short[] sArr10 = new short[m];
        int i2 = 0;
        short[][] sArr11 = null;
        while (sArr11 == null && i2 < 65536) {
            byte[] bArr6 = new byte[v1];
            this.random.nextBytes(bArr6);
            int i3 = 0;
            while (true) {
                bArr2 = bArr4;
                if (i3 >= v1) {
                    break;
                }
                sArr2[i3] = (short) (bArr6[i3] & 255);
                i3++;
                bArr4 = bArr2;
            }
            int[] iArr3 = new int[2];
            iArr3[1] = o1;
            iArr3[0] = o1;
            short[][] sArr12 = (short[][]) Array.newInstance(Short.TYPE, iArr3);
            for (int i4 = 0; i4 < v1; i4++) {
                int i5 = 0;
                while (i5 < o1) {
                    byte[] bArr7 = bArr5;
                    int i6 = 0;
                    while (i6 < o1) {
                        int i7 = m;
                        short[][] sArr13 = sArr7;
                        short multElem = GF2Field.multElem(rainbowPrivateKeyParameters.l1_F2[i5][i4][i6], sArr2[i4]);
                        short[] sArr14 = sArr12[i5];
                        sArr14[i6] = GF2Field.addElem(sArr14[i6], multElem);
                        i6++;
                        m = i7;
                        sArr7 = sArr13;
                        sArr6 = sArr6;
                    }
                    short[][] sArr15 = sArr7;
                    int i8 = m;
                    short[][] sArr16 = sArr6;
                    i5++;
                    bArr5 = bArr7;
                }
                short[][] sArr17 = sArr7;
                int i9 = m;
                short[][] sArr18 = sArr6;
                byte[] bArr8 = bArr5;
            }
            int i10 = m;
            short[][] sArr19 = sArr6;
            byte[] bArr9 = bArr5;
            sArr11 = this.cf.inverse(sArr12);
            i2++;
            bArr4 = bArr2;
            sArr7 = sArr7;
        }
        byte[] bArr10 = bArr4;
        short[][] sArr20 = sArr7;
        int i11 = m;
        short[][] sArr21 = sArr6;
        byte[] bArr11 = bArr5;
        for (int i12 = 0; i12 < o1; i12++) {
            sArr4[i12] = this.cf.multiplyMatrix_quad(rainbowPrivateKeyParameters.l1_F1[i12], sArr2);
        }
        for (int i13 = 0; i13 < v1; i13++) {
            for (int i14 = 0; i14 < o2; i14++) {
                sArr5[i14] = this.cf.multiplyMatrix_quad(rainbowPrivateKeyParameters.l2_F1[i14], sArr2);
                for (int i15 = 0; i15 < o1; i15++) {
                    short multElem2 = GF2Field.multElem(rainbowPrivateKeyParameters.l2_F2[i14][i13][i15], sArr2[i13]);
                    short[] sArr22 = sArr21[i14];
                    sArr22[i15] = GF2Field.addElem(sArr22[i15], multElem2);
                }
                for (int i16 = 0; i16 < o2; i16++) {
                    short multElem3 = GF2Field.multElem(rainbowPrivateKeyParameters.l2_F3[i14][i13][i16], sArr2[i13]);
                    short[] sArr23 = sArr20[i14];
                    sArr23[i16] = GF2Field.addElem(sArr23[i16], multElem3);
                }
            }
        }
        int i17 = i11;
        byte[] bArr12 = new byte[i17];
        short[] sArr24 = sArr8;
        short[] sArr25 = null;
        while (sArr25 == null && i2 < 65536) {
            int[] iArr4 = new int[2];
            iArr4[1] = o2;
            iArr4[0] = o2;
            short[][] sArr26 = (short[][]) Array.newInstance(Short.TYPE, iArr4);
            byte[] bArr13 = bArr11;
            this.random.nextBytes(bArr13);
            short[] makeMessageRepresentative = makeMessageRepresentative(RainbowUtil.hash(this.hashAlgo, bArr10, bArr13, bArr12));
            byte[] bArr14 = bArr12;
            short[] sArr27 = sArr10;
            System.arraycopy(this.cf.addVect(Arrays.copyOf(makeMessageRepresentative, o1), this.cf.multiplyMatrix(rainbowPrivateKeyParameters.s1, Arrays.copyOfRange(makeMessageRepresentative, o1, i17))), 0, sArr27, 0, o1);
            System.arraycopy(makeMessageRepresentative, o1, sArr27, o1, o2);
            short[] multiplyMatrix = this.cf.multiplyMatrix(sArr11, this.cf.addVect(sArr4, Arrays.copyOf(sArr27, o1)));
            short[][] sArr28 = sArr21;
            short[] multiplyMatrix2 = this.cf.multiplyMatrix(sArr28, multiplyMatrix);
            short[] sArr29 = sArr4;
            int i18 = 0;
            while (true) {
                sArr = sArr11;
                if (i18 >= o2) {
                    break;
                }
                sArr9[i18] = this.cf.multiplyMatrix_quad(rainbowPrivateKeyParameters.l2_F5[i18], multiplyMatrix);
                i18++;
                sArr11 = sArr;
                sArr28 = sArr28;
            }
            sArr21 = sArr28;
            short[] sArr30 = sArr9;
            short[] addVect = this.cf.addVect(this.cf.addVect(this.cf.addVect(multiplyMatrix2, sArr30), sArr5), Arrays.copyOfRange(sArr27, o1, i17));
            for (int i19 = 0; i19 < o1; i19++) {
                int i20 = 0;
                while (true) {
                    int i21 = i17;
                    if (i20 >= o2) {
                        break;
                    }
                    int i22 = 0;
                    while (i22 < o2) {
                        short[] sArr31 = sArr5;
                        short[] sArr32 = sArr30;
                        short multElem4 = GF2Field.multElem(rainbowPrivateKeyParameters.l2_F6[i20][i19][i22], multiplyMatrix[i19]);
                        short[] sArr33 = sArr26[i20];
                        sArr33[i22] = GF2Field.addElem(sArr33[i22], multElem4);
                        i22++;
                        sArr5 = sArr31;
                        sArr30 = sArr32;
                        multiplyMatrix = multiplyMatrix;
                    }
                    short[] sArr34 = multiplyMatrix;
                    short[] sArr35 = sArr5;
                    short[] sArr36 = sArr30;
                    i20++;
                    i17 = i21;
                }
                short[] sArr37 = multiplyMatrix;
                short[] sArr38 = sArr5;
                short[] sArr39 = sArr30;
            }
            short[] sArr40 = multiplyMatrix;
            short[] sArr41 = sArr5;
            short[] sArr42 = sArr30;
            sArr25 = this.cf.solveEquation(this.cf.addMatrix(sArr26, sArr20), addVect);
            i2++;
            sArr11 = sArr;
            bArr12 = bArr14;
            sArr4 = sArr29;
            i17 = i17;
            sArr9 = sArr42;
            sArr24 = sArr40;
            sArr10 = sArr27;
        }
        if (sArr25 == null) {
            sArr25 = new short[o2];
        }
        short[] addVect2 = this.cf.addVect(this.cf.addVect(sArr2, this.cf.multiplyMatrix(rainbowPrivateKeyParameters.t1, sArr24)), this.cf.multiplyMatrix(rainbowPrivateKeyParameters.t4, sArr25));
        short[] addVect3 = this.cf.addVect(sArr24, this.cf.multiplyMatrix(rainbowPrivateKeyParameters.t3, sArr25));
        short[] copyOf = Arrays.copyOf(addVect2, i);
        System.arraycopy(addVect3, 0, copyOf, v1, o1);
        System.arraycopy(sArr25, 0, copyOf, o1 + v1, o2);
        if (i2 != 65536) {
            return Arrays.concatenate(RainbowUtil.convertArray(copyOf), bArr11);
        }
        throw new IllegalStateException("unable to generate signature - LES not solvable");
    }

    private short[] makeMessageRepresentative(byte[] bArr) {
        int i = this.signableDocumentLength;
        short[] sArr = new short[i];
        int i2 = 0;
        int i3 = 0;
        while (i2 < bArr.length) {
            sArr[i2] = (short) (bArr[i3] & 255);
            i3++;
            i2++;
            if (i2 >= i) {
                break;
            }
        }
        return sArr;
    }

    public byte[] generateSignature(byte[] bArr) {
        return genSignature(bArr);
    }

    public void init(boolean z, CipherParameters cipherParameters) {
        RainbowKeyParameters rainbowKeyParameters;
        if (z) {
            if (cipherParameters instanceof ParametersWithRandom) {
                ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
                this.random = parametersWithRandom.getRandom();
                rainbowKeyParameters = (RainbowKeyParameters) parametersWithRandom.getParameters();
            } else {
                rainbowKeyParameters = (RainbowKeyParameters) cipherParameters;
                SecureRandom secureRandom = CryptoServicesRegistrar.getSecureRandom();
                byte[] bArr = new byte[rainbowKeyParameters.getParameters().getLen_skseed()];
                secureRandom.nextBytes(bArr);
                this.random = new RainbowDRBG(bArr, rainbowKeyParameters.getParameters().getHash_algo());
            }
            this.version = rainbowKeyParameters.getParameters().getVersion();
            this.key = rainbowKeyParameters;
        } else {
            RainbowKeyParameters rainbowKeyParameters2 = (RainbowKeyParameters) cipherParameters;
            this.key = rainbowKeyParameters2;
            this.version = rainbowKeyParameters2.getParameters().getVersion();
        }
        this.signableDocumentLength = this.key.getDocLength();
        this.hashAlgo = this.key.getParameters().getHash_algo();
    }

    public boolean verifySignature(byte[] bArr, byte[] bArr2) {
        short[] sArr;
        byte[] bArr3 = new byte[this.hashAlgo.getDigestSize()];
        this.hashAlgo.update(bArr, 0, bArr.length);
        this.hashAlgo.doFinal(bArr3, 0);
        int m = this.key.getParameters().getM();
        int n = this.key.getParameters().getN();
        RainbowPublicMap rainbowPublicMap = new RainbowPublicMap(this.key.getParameters());
        short[] makeMessageRepresentative = makeMessageRepresentative(RainbowUtil.hash(this.hashAlgo, bArr3, Arrays.copyOfRange(bArr2, n, bArr2.length), new byte[m]));
        short[] convertArray = RainbowUtil.convertArray(Arrays.copyOfRange(bArr2, 0, n));
        int i = AnonymousClass1.$SwitchMap$org$bouncycastle$pqc$crypto$rainbow$Version[this.version.ordinal()];
        if (i == 1) {
            sArr = rainbowPublicMap.publicMap((RainbowPublicKeyParameters) this.key, convertArray);
        } else if (i == 2 || i == 3) {
            sArr = rainbowPublicMap.publicMap_cyclic((RainbowPublicKeyParameters) this.key, convertArray);
        } else {
            throw new IllegalArgumentException("No valid version. Please choose one of the following: classic, circumzenithal, compressed");
        }
        return RainbowUtil.equals(makeMessageRepresentative, sArr);
    }
}
