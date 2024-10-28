package org.bouncycastle.crypto.digests;

import com.google.common.base.Ascii;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoServicePurpose;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.ExtendedDigest;
import org.bouncycastle.crypto.Xof;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;

public final class Kangaroo {
    private static final int DIGESTLEN = 32;

    static abstract class KangarooBase implements ExtendedDigest, Xof {
        private static final int BLKSIZE = 8192;
        private static final byte[] FINAL = {-1, -1, 6};
        private static final byte[] FIRST = {3, 0, 0, 0, 0, 0, 0, 0};
        private static final byte[] INTERMEDIATE = {Ascii.VT};
        private static final byte[] SINGLE = {7};
        private final CryptoServicePurpose purpose;
        private final byte[] singleByte = new byte[1];
        private boolean squeezing;
        private final int theChainLen;
        private int theCurrNode;
        private final KangarooSponge theLeaf;
        private byte[] thePersonal;
        private int theProcessed;
        private final KangarooSponge theTree;

        KangarooBase(int i, int i2, int i3, CryptoServicePurpose cryptoServicePurpose) {
            this.theTree = new KangarooSponge(i, i2);
            this.theLeaf = new KangarooSponge(i, i2);
            this.theChainLen = i >> 2;
            buildPersonal((byte[]) null);
            this.purpose = cryptoServicePurpose;
            CryptoServicesRegistrar.checkConstraints(Utils.getDefaultProperties(this, i, cryptoServicePurpose));
        }

        private void buildPersonal(byte[] bArr) {
            int length = bArr == null ? 0 : bArr.length;
            byte[] lengthEncode = lengthEncode((long) length);
            byte[] copyOf = bArr == null ? new byte[(lengthEncode.length + length)] : Arrays.copyOf(bArr, lengthEncode.length + length);
            this.thePersonal = copyOf;
            System.arraycopy(lengthEncode, 0, copyOf, length, lengthEncode.length);
        }

        private static byte[] lengthEncode(long j) {
            byte b;
            if (j != 0) {
                b = 1;
                long j2 = j;
                while (true) {
                    j2 >>= 8;
                    if (j2 == 0) {
                        break;
                    }
                    b = (byte) (b + 1);
                }
            } else {
                b = 0;
            }
            byte[] bArr = new byte[(b + 1)];
            bArr[b] = b;
            for (int i = 0; i < b; i++) {
                bArr[i] = (byte) ((int) (j >> (((b - i) - 1) * 8)));
            }
            return bArr;
        }

        private void processData(byte[] bArr, int i, int i2) {
            if (!this.squeezing) {
                KangarooSponge kangarooSponge = this.theCurrNode == 0 ? this.theTree : this.theLeaf;
                int i3 = 8192 - this.theProcessed;
                if (i3 >= i2) {
                    kangarooSponge.absorb(bArr, i, i2);
                    this.theProcessed += i2;
                    return;
                }
                if (i3 > 0) {
                    kangarooSponge.absorb(bArr, i, i3);
                    this.theProcessed += i3;
                }
                while (i3 < i2) {
                    if (this.theProcessed == 8192) {
                        switchLeaf(true);
                    }
                    int min = Math.min(i2 - i3, 8192);
                    this.theLeaf.absorb(bArr, i + i3, min);
                    this.theProcessed += min;
                    i3 += min;
                }
                return;
            }
            throw new IllegalStateException("attempt to absorb while squeezing");
        }

        private void switchFinal() {
            switchLeaf(false);
            byte[] lengthEncode = lengthEncode((long) this.theCurrNode);
            this.theTree.absorb(lengthEncode, 0, lengthEncode.length);
            KangarooSponge kangarooSponge = this.theTree;
            byte[] bArr = FINAL;
            kangarooSponge.absorb(bArr, 0, bArr.length);
            this.theTree.padAndSwitchToSqueezingPhase();
        }

        private void switchLeaf(boolean z) {
            if (this.theCurrNode == 0) {
                KangarooSponge kangarooSponge = this.theTree;
                byte[] bArr = FIRST;
                kangarooSponge.absorb(bArr, 0, bArr.length);
            } else {
                KangarooSponge kangarooSponge2 = this.theLeaf;
                byte[] bArr2 = INTERMEDIATE;
                kangarooSponge2.absorb(bArr2, 0, bArr2.length);
                int i = this.theChainLen;
                byte[] bArr3 = new byte[i];
                this.theLeaf.squeeze(bArr3, 0, i);
                this.theTree.absorb(bArr3, 0, this.theChainLen);
                this.theLeaf.initSponge();
            }
            if (z) {
                this.theCurrNode++;
            }
            this.theProcessed = 0;
        }

        private void switchSingle() {
            this.theTree.absorb(SINGLE, 0, 1);
            this.theTree.padAndSwitchToSqueezingPhase();
        }

        private void switchToSqueezing() {
            byte[] bArr = this.thePersonal;
            processData(bArr, 0, bArr.length);
            if (this.theCurrNode == 0) {
                switchSingle();
            } else {
                switchFinal();
            }
        }

        public int doFinal(byte[] bArr, int i) {
            return doFinal(bArr, i, getDigestSize());
        }

        public int doFinal(byte[] bArr, int i, int i2) {
            if (!this.squeezing) {
                int doOutput = doOutput(bArr, i, i2);
                reset();
                return doOutput;
            }
            throw new IllegalStateException("Already outputting");
        }

        public int doOutput(byte[] bArr, int i, int i2) {
            if (!this.squeezing) {
                switchToSqueezing();
            }
            if (i2 >= 0) {
                this.theTree.squeeze(bArr, i, i2);
                return i2;
            }
            throw new IllegalArgumentException("Invalid output length");
        }

        public int getByteLength() {
            return this.theTree.theRateBytes;
        }

        public int getDigestSize() {
            return this.theChainLen >> 1;
        }

        public void init(KangarooParameters kangarooParameters) {
            buildPersonal(kangarooParameters.getPersonalisation());
            reset();
        }

        public void reset() {
            this.theTree.initSponge();
            this.theLeaf.initSponge();
            this.theCurrNode = 0;
            this.theProcessed = 0;
            this.squeezing = false;
        }

        public void update(byte b) {
            byte[] bArr = this.singleByte;
            bArr[0] = b;
            update(bArr, 0, 1);
        }

        public void update(byte[] bArr, int i, int i2) {
            processData(bArr, i, i2);
        }
    }

    public static class KangarooParameters implements CipherParameters {
        /* access modifiers changed from: private */
        public byte[] thePersonal;

        public static class Builder {
            private byte[] thePersonal;

            public KangarooParameters build() {
                KangarooParameters kangarooParameters = new KangarooParameters();
                byte[] bArr = this.thePersonal;
                if (bArr != null) {
                    byte[] unused = kangarooParameters.thePersonal = bArr;
                }
                return kangarooParameters;
            }

            public Builder setPersonalisation(byte[] bArr) {
                this.thePersonal = Arrays.clone(bArr);
                return this;
            }
        }

        public byte[] getPersonalisation() {
            return Arrays.clone(this.thePersonal);
        }
    }

    private static class KangarooSponge {
        private static long[] KeccakRoundConstants = {1, 32898, -9223372036854742902L, -9223372034707259392L, 32907, 2147483649L, -9223372034707259263L, -9223372036854743031L, 138, 136, 2147516425L, 2147483658L, 2147516555L, -9223372036854775669L, -9223372036854742903L, -9223372036854743037L, -9223372036854743038L, -9223372036854775680L, 32778, -9223372034707292150L, -9223372034707259263L, -9223372036854742912L, 2147483649L, -9223372034707259384L};
        private int bytesInQueue;
        private boolean squeezing;
        private final byte[] theQueue;
        /* access modifiers changed from: private */
        public final int theRateBytes;
        private final int theRounds;
        private final long[] theState = new long[25];

        KangarooSponge(int i, int i2) {
            int i3 = (1600 - (i << 1)) >> 3;
            this.theRateBytes = i3;
            this.theRounds = i2;
            this.theQueue = new byte[i3];
            initSponge();
        }

        private void KangarooAbsorb(byte[] bArr, int i) {
            int i2 = this.theRateBytes >> 3;
            for (int i3 = 0; i3 < i2; i3++) {
                long[] jArr = this.theState;
                jArr[i3] = jArr[i3] ^ Pack.littleEndianToLong(bArr, i);
                i += 8;
            }
            KangarooPermutation();
        }

        private void KangarooExtract() {
            Pack.longToLittleEndian(this.theState, 0, this.theRateBytes >> 3, this.theQueue, 0);
        }

        private void KangarooPermutation() {
            long[] jArr = this.theState;
            long j = jArr[0];
            long j2 = jArr[1];
            long j3 = jArr[2];
            long j4 = jArr[3];
            long j5 = jArr[4];
            long j6 = jArr[5];
            long j7 = jArr[6];
            long j8 = jArr[7];
            long j9 = jArr[8];
            long j10 = jArr[9];
            long j11 = jArr[10];
            long j12 = jArr[11];
            long j13 = jArr[12];
            long j14 = jArr[13];
            long j15 = jArr[14];
            long j16 = jArr[15];
            long j17 = jArr[16];
            long j18 = jArr[17];
            long j19 = jArr[18];
            long j20 = jArr[19];
            long j21 = jArr[20];
            long j22 = jArr[21];
            long j23 = jArr[22];
            long j24 = jArr[23];
            long j25 = jArr[24];
            int length = KeccakRoundConstants.length - this.theRounds;
            int i = 0;
            while (i < this.theRounds) {
                long j26 = (((j ^ j6) ^ j11) ^ j16) ^ j21;
                long j27 = (((j2 ^ j7) ^ j12) ^ j17) ^ j22;
                long j28 = (((j3 ^ j8) ^ j13) ^ j18) ^ j23;
                long j29 = (((j4 ^ j9) ^ j14) ^ j19) ^ j24;
                long j30 = (((j5 ^ j10) ^ j15) ^ j20) ^ j25;
                long j31 = ((j27 << 1) | (j27 >>> -1)) ^ j30;
                long j32 = ((j28 << 1) | (j28 >>> -1)) ^ j26;
                long j33 = ((j29 << 1) | (j29 >>> -1)) ^ j27;
                long j34 = ((j30 << 1) | (j30 >>> -1)) ^ j28;
                long j35 = ((j26 << 1) | (j26 >>> -1)) ^ j29;
                long j36 = j ^ j31;
                long j37 = j6 ^ j31;
                long j38 = j11 ^ j31;
                long j39 = j16 ^ j31;
                long j40 = j21 ^ j31;
                long j41 = j2 ^ j32;
                long j42 = j7 ^ j32;
                long j43 = j12 ^ j32;
                long j44 = j17 ^ j32;
                long j45 = j22 ^ j32;
                long j46 = j3 ^ j33;
                long j47 = j8 ^ j33;
                long j48 = j13 ^ j33;
                long j49 = j18 ^ j33;
                long j50 = j23 ^ j33;
                long j51 = j4 ^ j34;
                long j52 = j9 ^ j34;
                long j53 = j14 ^ j34;
                long j54 = j19 ^ j34;
                long j55 = j24 ^ j34;
                long j56 = j5 ^ j35;
                long j57 = j10 ^ j35;
                long j58 = j15 ^ j35;
                long j59 = j20 ^ j35;
                long j60 = j25 ^ j35;
                long j61 = (j41 << 1) | (j41 >>> 63);
                long j62 = (j42 << 44) | (j42 >>> 20);
                long j63 = (j57 << 20) | (j57 >>> 44);
                long j64 = (j50 << 61) | (j50 >>> 3);
                long j65 = (j58 << 39) | (j58 >>> 25);
                long j66 = (j40 << 18) | (j40 >>> 46);
                long j67 = (j46 << 62) | (j46 >>> 2);
                long j68 = (j48 << 43) | (j48 >>> 21);
                long j69 = (j53 << 25) | (j53 >>> 39);
                long j70 = (j59 << 8) | (j59 >>> 56);
                long j71 = (j55 << 56) | (j55 >>> 8);
                long j72 = (j39 << 41) | (j39 >>> 23);
                long j73 = (j56 << 27) | (j56 >>> 37);
                long j74 = (j60 << 14) | (j60 >>> 50);
                long j75 = (j45 << 2) | (j45 >>> 62);
                long j76 = (j52 << 55) | (j52 >>> 9);
                long j77 = (j44 << 45) | (j44 >>> 19);
                long j78 = (j37 << 36) | (j37 >>> 28);
                long j79 = (j51 << 28) | (j51 >>> 36);
                long j80 = (j54 << 21) | (j54 >>> 43);
                long j81 = (j49 << 15) | (j49 >>> 49);
                long j82 = (j43 << 10) | (j43 >>> 54);
                long j83 = (j47 << 6) | (j47 >>> 58);
                long j84 = (j38 << 3) | (j38 >>> 61);
                long j85 = j36 ^ ((j62 ^ -1) & j68);
                long j86 = j62 ^ ((j68 ^ -1) & j80);
                long j87 = j68 ^ ((j80 ^ -1) & j74);
                long j88 = j80 ^ ((j74 ^ -1) & j36);
                long j89 = j74 ^ ((j36 ^ -1) & j62);
                long j90 = j79 ^ ((j63 ^ -1) & j84);
                long j91 = j63 ^ ((j84 ^ -1) & j77);
                long j92 = j84 ^ ((j77 ^ -1) & j64);
                long j93 = j77 ^ ((j64 ^ -1) & j79);
                j10 = j64 ^ ((j79 ^ -1) & j63);
                long j94 = ((j83 ^ -1) & j69) ^ j61;
                long j95 = j83 ^ ((j69 ^ -1) & j70);
                long j96 = j69 ^ ((j70 ^ -1) & j66);
                long j97 = j70 ^ ((j66 ^ -1) & j61);
                long j98 = j66 ^ ((j61 ^ -1) & j83);
                long j99 = j73 ^ ((j78 ^ -1) & j82);
                long j100 = j78 ^ ((j82 ^ -1) & j81);
                long j101 = j82 ^ ((j81 ^ -1) & j71);
                long j102 = j81 ^ ((j71 ^ -1) & j73);
                long j103 = j71 ^ ((j73 ^ -1) & j78);
                long j104 = j67 ^ ((j76 ^ -1) & j65);
                long j105 = j76 ^ ((j65 ^ -1) & j72);
                long j106 = j65 ^ ((j72 ^ -1) & j75);
                long j107 = j75 ^ ((j67 ^ -1) & j76);
                i++;
                j22 = j105;
                j24 = j72 ^ ((j75 ^ -1) & j67);
                j16 = j99;
                j8 = j92;
                j11 = j94;
                j4 = j88;
                j19 = j102;
                j18 = j101;
                j12 = j95;
                j23 = j106;
                j15 = j98;
                j2 = j86;
                long j108 = j90;
                j7 = j91;
                j25 = j107;
                j3 = j87;
                j13 = j96;
                j14 = j97;
                j20 = j103;
                j5 = j89;
                j = j85 ^ KeccakRoundConstants[length + i];
                j9 = j93;
                j17 = j100;
                j21 = j104;
                j6 = j108;
            }
            jArr[0] = j;
            jArr[1] = j2;
            jArr[2] = j3;
            jArr[3] = j4;
            jArr[4] = j5;
            jArr[5] = j6;
            jArr[6] = j7;
            jArr[7] = j8;
            jArr[8] = j9;
            jArr[9] = j10;
            jArr[10] = j11;
            jArr[11] = j12;
            jArr[12] = j13;
            jArr[13] = j14;
            jArr[14] = j15;
            jArr[15] = j16;
            jArr[16] = j17;
            jArr[17] = j18;
            jArr[18] = j19;
            jArr[19] = j20;
            jArr[20] = j21;
            jArr[21] = j22;
            jArr[22] = j23;
            jArr[23] = j24;
            jArr[24] = j25;
        }

        /* access modifiers changed from: private */
        public void absorb(byte[] bArr, int i, int i2) {
            int i3;
            if (!this.squeezing) {
                int i4 = 0;
                while (i4 < i2) {
                    int i5 = this.bytesInQueue;
                    if (i5 != 0 || i4 > i2 - this.theRateBytes) {
                        int min = Math.min(this.theRateBytes - i5, i2 - i4);
                        System.arraycopy(bArr, i + i4, this.theQueue, this.bytesInQueue, min);
                        int i6 = this.bytesInQueue + min;
                        this.bytesInQueue = i6;
                        i4 += min;
                        if (i6 == this.theRateBytes) {
                            KangarooAbsorb(this.theQueue, 0);
                            this.bytesInQueue = 0;
                        }
                    } else {
                        do {
                            KangarooAbsorb(bArr, i + i4);
                            i3 = this.theRateBytes;
                            i4 += i3;
                        } while (i4 <= i2 - i3);
                    }
                }
                return;
            }
            throw new IllegalStateException("attempt to absorb while squeezing");
        }

        /* access modifiers changed from: private */
        public void initSponge() {
            Arrays.fill(this.theState, 0);
            Arrays.fill(this.theQueue, (byte) 0);
            this.bytesInQueue = 0;
            this.squeezing = false;
        }

        /* access modifiers changed from: private */
        public void padAndSwitchToSqueezingPhase() {
            int i = this.bytesInQueue;
            while (true) {
                int i2 = this.theRateBytes;
                if (i < i2) {
                    this.theQueue[i] = 0;
                    i++;
                } else {
                    byte[] bArr = this.theQueue;
                    int i3 = i2 - 1;
                    bArr[i3] = (byte) (bArr[i3] ^ 128);
                    KangarooAbsorb(bArr, 0);
                    KangarooExtract();
                    this.bytesInQueue = this.theRateBytes;
                    this.squeezing = true;
                    return;
                }
            }
        }

        /* access modifiers changed from: private */
        public void squeeze(byte[] bArr, int i, int i2) {
            if (!this.squeezing) {
                padAndSwitchToSqueezingPhase();
            }
            int i3 = 0;
            while (i3 < i2) {
                if (this.bytesInQueue == 0) {
                    KangarooPermutation();
                    KangarooExtract();
                    this.bytesInQueue = this.theRateBytes;
                }
                int min = Math.min(this.bytesInQueue, i2 - i3);
                System.arraycopy(this.theQueue, this.theRateBytes - this.bytesInQueue, bArr, i + i3, min);
                this.bytesInQueue -= min;
                i3 += min;
            }
        }
    }

    public static class KangarooTwelve extends KangarooBase {
        public KangarooTwelve() {
            this(32, CryptoServicePurpose.ANY);
        }

        public KangarooTwelve(int i, CryptoServicePurpose cryptoServicePurpose) {
            super(128, 12, i, cryptoServicePurpose);
        }

        public KangarooTwelve(CryptoServicePurpose cryptoServicePurpose) {
            this(32, cryptoServicePurpose);
        }

        public /* bridge */ /* synthetic */ int doFinal(byte[] bArr, int i) {
            return super.doFinal(bArr, i);
        }

        public /* bridge */ /* synthetic */ int doFinal(byte[] bArr, int i, int i2) {
            return super.doFinal(bArr, i, i2);
        }

        public /* bridge */ /* synthetic */ int doOutput(byte[] bArr, int i, int i2) {
            return super.doOutput(bArr, i, i2);
        }

        public String getAlgorithmName() {
            return "KangarooTwelve";
        }

        public /* bridge */ /* synthetic */ int getByteLength() {
            return super.getByteLength();
        }

        public /* bridge */ /* synthetic */ int getDigestSize() {
            return super.getDigestSize();
        }

        public /* bridge */ /* synthetic */ void init(KangarooParameters kangarooParameters) {
            super.init(kangarooParameters);
        }

        public /* bridge */ /* synthetic */ void reset() {
            super.reset();
        }

        public /* bridge */ /* synthetic */ void update(byte b) {
            super.update(b);
        }

        public /* bridge */ /* synthetic */ void update(byte[] bArr, int i, int i2) {
            super.update(bArr, i, i2);
        }
    }

    public static class MarsupilamiFourteen extends KangarooBase {
        public MarsupilamiFourteen() {
            this(32, CryptoServicePurpose.ANY);
        }

        public MarsupilamiFourteen(int i, CryptoServicePurpose cryptoServicePurpose) {
            super(256, 14, i, cryptoServicePurpose);
        }

        public MarsupilamiFourteen(CryptoServicePurpose cryptoServicePurpose) {
            this(32, cryptoServicePurpose);
        }

        public /* bridge */ /* synthetic */ int doFinal(byte[] bArr, int i) {
            return super.doFinal(bArr, i);
        }

        public /* bridge */ /* synthetic */ int doFinal(byte[] bArr, int i, int i2) {
            return super.doFinal(bArr, i, i2);
        }

        public /* bridge */ /* synthetic */ int doOutput(byte[] bArr, int i, int i2) {
            return super.doOutput(bArr, i, i2);
        }

        public String getAlgorithmName() {
            return "MarsupilamiFourteen";
        }

        public /* bridge */ /* synthetic */ int getByteLength() {
            return super.getByteLength();
        }

        public /* bridge */ /* synthetic */ int getDigestSize() {
            return super.getDigestSize();
        }

        public /* bridge */ /* synthetic */ void init(KangarooParameters kangarooParameters) {
            super.init(kangarooParameters);
        }

        public /* bridge */ /* synthetic */ void reset() {
            super.reset();
        }

        public /* bridge */ /* synthetic */ void update(byte b) {
            super.update(b);
        }

        public /* bridge */ /* synthetic */ void update(byte[] bArr, int i, int i2) {
            super.update(bArr, i, i2);
        }
    }
}
