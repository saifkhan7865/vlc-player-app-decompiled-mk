package org.bouncycastle.crypto.digests;

import org.bouncycastle.crypto.CryptoServiceProperties;
import org.bouncycastle.crypto.CryptoServicePurpose;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.util.Memoable;
import org.bouncycastle.util.Pack;

public class SM3Digest extends GeneralDigest {
    private static final int BLOCK_SIZE = 16;
    private static final int DIGEST_LENGTH = 32;
    private static final int[] T = new int[64];
    private int[] V;
    private int[] W;
    private int[] inwords;
    private int xOff;

    static {
        int i;
        int i2 = 0;
        while (true) {
            if (i2 >= 16) {
                break;
            }
            T[i2] = (2043430169 >>> (32 - i2)) | (2043430169 << i2);
            i2++;
        }
        for (i = 16; i < 64; i++) {
            int i3 = i % 32;
            T[i] = (2055708042 >>> (32 - i3)) | (2055708042 << i3);
        }
    }

    public SM3Digest() {
        this(CryptoServicePurpose.ANY);
    }

    public SM3Digest(CryptoServicePurpose cryptoServicePurpose) {
        super(cryptoServicePurpose);
        this.V = new int[8];
        this.inwords = new int[16];
        this.W = new int[68];
        CryptoServicesRegistrar.checkConstraints(cryptoServiceProperties());
        reset();
    }

    public SM3Digest(SM3Digest sM3Digest) {
        super((GeneralDigest) sM3Digest);
        this.V = new int[8];
        this.inwords = new int[16];
        this.W = new int[68];
        CryptoServicesRegistrar.checkConstraints(cryptoServiceProperties());
        copyIn(sM3Digest);
    }

    private int FF0(int i, int i2, int i3) {
        return (i ^ i2) ^ i3;
    }

    private int FF1(int i, int i2, int i3) {
        return (i & i3) | (i & i2) | (i2 & i3);
    }

    private int GG0(int i, int i2, int i3) {
        return (i ^ i2) ^ i3;
    }

    private int GG1(int i, int i2, int i3) {
        return ((i ^ -1) & i3) | (i2 & i);
    }

    private int P0(int i) {
        return (i ^ ((i << 9) | (i >>> 23))) ^ ((i << 17) | (i >>> 15));
    }

    private int P1(int i) {
        return (i ^ ((i << 15) | (i >>> 17))) ^ ((i << 23) | (i >>> 9));
    }

    private void copyIn(SM3Digest sM3Digest) {
        int[] iArr = sM3Digest.V;
        int[] iArr2 = this.V;
        System.arraycopy(iArr, 0, iArr2, 0, iArr2.length);
        int[] iArr3 = sM3Digest.inwords;
        int[] iArr4 = this.inwords;
        System.arraycopy(iArr3, 0, iArr4, 0, iArr4.length);
        this.xOff = sM3Digest.xOff;
    }

    public Memoable copy() {
        return new SM3Digest(this);
    }

    /* access modifiers changed from: protected */
    public CryptoServiceProperties cryptoServiceProperties() {
        return Utils.getDefaultProperties(this, 256, this.purpose);
    }

    public int doFinal(byte[] bArr, int i) {
        finish();
        Pack.intToBigEndian(this.V, bArr, i);
        reset();
        return 32;
    }

    public String getAlgorithmName() {
        return "SM3";
    }

    public int getDigestSize() {
        return 32;
    }

    /* access modifiers changed from: protected */
    public void processBlock() {
        int i;
        int i2 = 0;
        while (true) {
            if (i2 >= 16) {
                break;
            }
            this.W[i2] = this.inwords[i2];
            i2++;
        }
        for (int i3 = 16; i3 < 68; i3++) {
            int[] iArr = this.W;
            int i4 = iArr[i3 - 3];
            int i5 = (i4 >>> 17) | (i4 << 15);
            int i6 = iArr[i3 - 13];
            iArr[i3] = (P1(i5 ^ (iArr[i3 - 16] ^ iArr[i3 - 9])) ^ ((i6 >>> 25) | (i6 << 7))) ^ this.W[i3 - 6];
        }
        int[] iArr2 = this.V;
        int i7 = iArr2[0];
        int i8 = iArr2[1];
        int i9 = iArr2[2];
        int i10 = iArr2[3];
        int i11 = iArr2[4];
        int i12 = iArr2[5];
        int i13 = iArr2[6];
        int i14 = iArr2[7];
        int i15 = i13;
        int i16 = 0;
        for (i = 16; i16 < i; i = 16) {
            int i17 = (i7 << 12) | (i7 >>> 20);
            int i18 = i17 + i11 + T[i16];
            int i19 = (i18 << 7) | (i18 >>> 25);
            int[] iArr3 = this.W;
            int i20 = iArr3[i16];
            int i21 = (i8 << 9) | (i8 >>> 23);
            int i22 = (i12 << 19) | (i12 >>> 13);
            i16++;
            i12 = i11;
            i11 = P0(GG0(i11, i12, i15) + i14 + i19 + i20);
            i10 = i9;
            i9 = i21;
            i14 = i15;
            i15 = i22;
            i8 = i7;
            i7 = FF0(i7, i8, i9) + i10 + (i19 ^ i17) + (i20 ^ iArr3[i16 + 4]);
        }
        int i23 = i14;
        int i24 = i11;
        int i25 = i15;
        int i26 = i10;
        int i27 = i9;
        int i28 = i8;
        int i29 = i7;
        int i30 = 16;
        while (i30 < 64) {
            int i31 = (i29 << 12) | (i29 >>> 20);
            int i32 = i31 + i24 + T[i30];
            int i33 = (i32 << 7) | (i32 >>> 25);
            int[] iArr4 = this.W;
            int i34 = iArr4[i30];
            int i35 = (i28 >>> 23) | (i28 << 9);
            int i36 = (i12 << 19) | (i12 >>> 13);
            i30++;
            i12 = i24;
            i24 = P0(GG1(i24, i12, i25) + i23 + i33 + i34);
            i26 = i27;
            i27 = i35;
            i28 = i29;
            i29 = FF1(i29, i28, i27) + i26 + (i33 ^ i31) + (i34 ^ iArr4[i30 + 4]);
            int i37 = i36;
            i23 = i25;
            i25 = i37;
        }
        int[] iArr5 = this.V;
        iArr5[0] = i29 ^ iArr5[0];
        iArr5[1] = iArr5[1] ^ i28;
        iArr5[2] = iArr5[2] ^ i27;
        iArr5[3] = iArr5[3] ^ i26;
        iArr5[4] = iArr5[4] ^ i24;
        iArr5[5] = iArr5[5] ^ i12;
        iArr5[6] = i25 ^ iArr5[6];
        iArr5[7] = iArr5[7] ^ i23;
        this.xOff = 0;
    }

    /* access modifiers changed from: protected */
    public void processLength(long j) {
        int i = this.xOff;
        if (i > 14) {
            this.inwords[i] = 0;
            this.xOff = i + 1;
            processBlock();
        }
        while (true) {
            int i2 = this.xOff;
            if (i2 < 14) {
                this.inwords[i2] = 0;
                this.xOff = i2 + 1;
            } else {
                int[] iArr = this.inwords;
                iArr[i2] = (int) (j >>> 32);
                this.xOff = i2 + 2;
                iArr[i2 + 1] = (int) j;
                return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void processWord(byte[] bArr, int i) {
        int[] iArr = this.inwords;
        int i2 = this.xOff;
        this.xOff = i2 + 1;
        iArr[i2] = Pack.bigEndianToInt(bArr, i);
        if (this.xOff >= 16) {
            processBlock();
        }
    }

    public void reset() {
        super.reset();
        int[] iArr = this.V;
        iArr[0] = 1937774191;
        iArr[1] = 1226093241;
        iArr[2] = 388252375;
        iArr[3] = -628488704;
        iArr[4] = -1452330820;
        iArr[5] = 372324522;
        iArr[6] = -477237683;
        iArr[7] = -1325724082;
        this.xOff = 0;
    }

    public void reset(Memoable memoable) {
        SM3Digest sM3Digest = (SM3Digest) memoable;
        super.copyIn(sM3Digest);
        copyIn(sM3Digest);
    }
}
