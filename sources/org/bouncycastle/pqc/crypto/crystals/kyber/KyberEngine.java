package org.bouncycastle.pqc.crypto.crystals.kyber;

import java.security.SecureRandom;
import org.bouncycastle.util.Arrays;

class KyberEngine {
    private static final int KyberEta2 = 2;
    private static final int KyberIndCpaMsgBytes = 32;
    public static final int KyberN = 256;
    public static final int KyberPolyBytes = 384;
    public static final int KyberQ = 3329;
    public static final int KyberQinv = 62209;
    private static final int KyberSharedSecretBytes = 32;
    public static final int KyberSymBytes = 32;
    private final int CryptoBytes;
    private final int CryptoCipherTextBytes;
    private final int CryptoPublicKeyBytes;
    private final int CryptoSecretKeyBytes;
    private final int KyberCipherTextBytes;
    private final int KyberEta1;
    private final int KyberIndCpaBytes;
    private final int KyberIndCpaPublicKeyBytes;
    private final int KyberIndCpaSecretKeyBytes;
    private final int KyberK;
    private final int KyberPolyCompressedBytes;
    private final int KyberPolyVecBytes;
    private final int KyberPolyVecCompressedBytes;
    private final int KyberPublicKeyBytes;
    private final int KyberSecretKeyBytes;
    private KyberIndCpa indCpa;
    private SecureRandom random;
    private final int sessionKeyLength;
    private final Symmetric symmetric;

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0065  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x006b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public KyberEngine(int r5, boolean r6) {
        /*
            r4 = this;
            r4.<init>()
            r4.KyberK = r5
            r0 = 128(0x80, float:1.794E-43)
            r1 = 3
            r2 = 2
            r3 = 32
            if (r5 == r2) goto L_0x0037
            if (r5 == r1) goto L_0x0034
            r0 = 4
            if (r5 != r0) goto L_0x001b
            r4.KyberEta1 = r2
            r0 = 160(0xa0, float:2.24E-43)
            r4.KyberPolyCompressedBytes = r0
            int r0 = r5 * 352
            goto L_0x003d
        L_0x001b:
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "K: "
            r0.<init>(r1)
            r0.append(r5)
            java.lang.String r5 = " is not supported for Crystals Kyber"
            r0.append(r5)
            java.lang.String r5 = r0.toString()
            r6.<init>(r5)
            throw r6
        L_0x0034:
            r4.KyberEta1 = r2
            goto L_0x0039
        L_0x0037:
            r4.KyberEta1 = r1
        L_0x0039:
            r4.KyberPolyCompressedBytes = r0
            int r0 = r5 * 320
        L_0x003d:
            r4.KyberPolyVecCompressedBytes = r0
            r4.sessionKeyLength = r3
            int r5 = r5 * 384
            r4.KyberPolyVecBytes = r5
            int r0 = r5 + 32
            r4.KyberIndCpaPublicKeyBytes = r0
            r4.KyberIndCpaSecretKeyBytes = r5
            int r1 = r4.KyberPolyVecCompressedBytes
            int r2 = r4.KyberPolyCompressedBytes
            int r1 = r1 + r2
            r4.KyberIndCpaBytes = r1
            r4.KyberPublicKeyBytes = r0
            int r5 = r5 + r0
            int r5 = r5 + 64
            r4.KyberSecretKeyBytes = r5
            r4.KyberCipherTextBytes = r1
            r4.CryptoBytes = r3
            r4.CryptoSecretKeyBytes = r5
            r4.CryptoPublicKeyBytes = r0
            r4.CryptoCipherTextBytes = r1
            if (r6 == 0) goto L_0x006b
            org.bouncycastle.pqc.crypto.crystals.kyber.Symmetric$AesSymmetric r5 = new org.bouncycastle.pqc.crypto.crystals.kyber.Symmetric$AesSymmetric
            r5.<init>()
            goto L_0x0070
        L_0x006b:
            org.bouncycastle.pqc.crypto.crystals.kyber.Symmetric$ShakeSymmetric r5 = new org.bouncycastle.pqc.crypto.crystals.kyber.Symmetric$ShakeSymmetric
            r5.<init>()
        L_0x0070:
            r4.symmetric = r5
            org.bouncycastle.pqc.crypto.crystals.kyber.KyberIndCpa r5 = new org.bouncycastle.pqc.crypto.crystals.kyber.KyberIndCpa
            r5.<init>(r4)
            r4.indCpa = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.pqc.crypto.crystals.kyber.KyberEngine.<init>(int, boolean):void");
    }

    private void cmov(byte[] bArr, byte[] bArr2, int i, boolean z) {
        if (z) {
            System.arraycopy(bArr2, 0, bArr, 0, i);
        } else {
            System.arraycopy(bArr, 0, bArr, 0, i);
        }
    }

    public static int getKyberEta2() {
        return 2;
    }

    public static int getKyberIndCpaMsgBytes() {
        return 32;
    }

    public byte[][] generateKemKeyPair() {
        byte[][] generateKeyPair = this.indCpa.generateKeyPair();
        int i = this.KyberIndCpaSecretKeyBytes;
        byte[] bArr = new byte[i];
        System.arraycopy(generateKeyPair[1], 0, bArr, 0, i);
        byte[] bArr2 = new byte[32];
        this.symmetric.hash_h(bArr2, generateKeyPair[0], 0);
        byte[] bArr3 = new byte[32];
        this.random.nextBytes(bArr3);
        int i2 = this.KyberIndCpaPublicKeyBytes;
        byte[] bArr4 = new byte[i2];
        System.arraycopy(generateKeyPair[0], 0, bArr4, 0, i2);
        int i3 = i2 - 32;
        return new byte[][]{Arrays.copyOfRange(bArr4, 0, i3), Arrays.copyOfRange(bArr4, i3, i2), bArr, bArr2, bArr3};
    }

    public int getCryptoBytes() {
        return this.CryptoBytes;
    }

    public int getCryptoCipherTextBytes() {
        return this.CryptoCipherTextBytes;
    }

    public int getCryptoPublicKeyBytes() {
        return this.CryptoPublicKeyBytes;
    }

    public int getCryptoSecretKeyBytes() {
        return this.CryptoSecretKeyBytes;
    }

    public int getKyberCipherTextBytes() {
        return this.KyberCipherTextBytes;
    }

    public int getKyberEta1() {
        return this.KyberEta1;
    }

    public int getKyberIndCpaBytes() {
        return this.KyberIndCpaBytes;
    }

    public int getKyberIndCpaPublicKeyBytes() {
        return this.KyberIndCpaPublicKeyBytes;
    }

    public int getKyberIndCpaSecretKeyBytes() {
        return this.KyberIndCpaSecretKeyBytes;
    }

    public int getKyberK() {
        return this.KyberK;
    }

    public int getKyberPolyCompressedBytes() {
        return this.KyberPolyCompressedBytes;
    }

    public int getKyberPolyVecBytes() {
        return this.KyberPolyVecBytes;
    }

    public int getKyberPolyVecCompressedBytes() {
        return this.KyberPolyVecCompressedBytes;
    }

    public int getKyberPublicKeyBytes() {
        return this.KyberPublicKeyBytes;
    }

    public int getKyberSecretKeyBytes() {
        return this.KyberSecretKeyBytes;
    }

    public void getRandomBytes(byte[] bArr) {
        this.random.nextBytes(bArr);
    }

    public Symmetric getSymmetric() {
        return this.symmetric;
    }

    public void init(SecureRandom secureRandom) {
        this.random = secureRandom;
    }

    public byte[] kemDecrypt(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[64];
        byte[] bArr4 = new byte[64];
        byte[] copyOfRange = Arrays.copyOfRange(bArr2, this.KyberIndCpaSecretKeyBytes, bArr2.length);
        System.arraycopy(this.indCpa.decrypt(bArr, bArr2), 0, bArr3, 0, 32);
        System.arraycopy(bArr2, this.KyberSecretKeyBytes - 64, bArr3, 32, 32);
        this.symmetric.hash_g(bArr4, bArr3);
        this.symmetric.hash_h(bArr4, bArr, 32);
        int i = this.KyberSecretKeyBytes;
        cmov(bArr4, Arrays.copyOfRange(bArr2, i - 32, i), 32, !Arrays.constantTimeAreEqual(bArr, this.indCpa.encrypt(Arrays.copyOfRange(bArr3, 0, 32), copyOfRange, Arrays.copyOfRange(bArr4, 32, 64))));
        return Arrays.copyOfRange(bArr4, 0, this.sessionKeyLength);
    }

    public byte[][] kemEncrypt(byte[] bArr) {
        byte[] bArr2 = new byte[64];
        byte[] bArr3 = new byte[64];
        byte[] bArr4 = new byte[32];
        this.random.nextBytes(bArr4);
        System.arraycopy(bArr4, 0, bArr2, 0, 32);
        this.symmetric.hash_h(bArr2, bArr, 32);
        this.symmetric.hash_g(bArr3, bArr2);
        byte[] encrypt = this.indCpa.encrypt(Arrays.copyOfRange(bArr2, 0, 32), bArr, Arrays.copyOfRange(bArr3, 32, 64));
        int i = this.sessionKeyLength;
        byte[] bArr5 = new byte[i];
        System.arraycopy(bArr3, 0, bArr5, 0, i);
        return new byte[][]{bArr5, encrypt};
    }
}
