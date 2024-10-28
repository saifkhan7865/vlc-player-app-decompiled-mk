package org.bouncycastle.crypto.engines;

import io.netty.handler.codec.http2.Http2CodecUtil;
import java.io.ByteArrayOutputStream;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.constraints.DefaultServiceProperties;
import org.bouncycastle.crypto.modes.AEADCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.Pack;

public class ISAPEngine implements AEADCipher {
    final int CRYPTO_KEYBYTES = 16;
    final int CRYPTO_NPUBBYTES = 16;
    private ISAP_AEAD ISAPAEAD;
    final int ISAP_STATE_SZ = 40;
    /* access modifiers changed from: private */
    public int ISAP_rH;
    /* access modifiers changed from: private */
    public int ISAP_rH_SZ;
    private ByteArrayOutputStream aadData = new ByteArrayOutputStream();
    private byte[] ad;
    private String algorithmName;
    private byte[] c;
    private boolean forEncryption;
    private boolean initialised;
    /* access modifiers changed from: private */
    public byte[] k;
    private byte[] mac;
    private final ByteArrayOutputStream message = new ByteArrayOutputStream();
    /* access modifiers changed from: private */
    public byte[] npub;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    /* renamed from: org.bouncycastle.crypto.engines.ISAPEngine$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$bouncycastle$crypto$engines$ISAPEngine$IsapType;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                org.bouncycastle.crypto.engines.ISAPEngine$IsapType[] r0 = org.bouncycastle.crypto.engines.ISAPEngine.IsapType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$bouncycastle$crypto$engines$ISAPEngine$IsapType = r0
                org.bouncycastle.crypto.engines.ISAPEngine$IsapType r1 = org.bouncycastle.crypto.engines.ISAPEngine.IsapType.ISAP_A_128A     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$bouncycastle$crypto$engines$ISAPEngine$IsapType     // Catch:{ NoSuchFieldError -> 0x001d }
                org.bouncycastle.crypto.engines.ISAPEngine$IsapType r1 = org.bouncycastle.crypto.engines.ISAPEngine.IsapType.ISAP_K_128A     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$bouncycastle$crypto$engines$ISAPEngine$IsapType     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.bouncycastle.crypto.engines.ISAPEngine$IsapType r1 = org.bouncycastle.crypto.engines.ISAPEngine.IsapType.ISAP_A_128     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$org$bouncycastle$crypto$engines$ISAPEngine$IsapType     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.bouncycastle.crypto.engines.ISAPEngine$IsapType r1 = org.bouncycastle.crypto.engines.ISAPEngine.IsapType.ISAP_K_128     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.crypto.engines.ISAPEngine.AnonymousClass1.<clinit>():void");
        }
    }

    public abstract class ISAPAEAD_A implements ISAP_AEAD {
        protected long ISAP_IV1_64;
        protected long ISAP_IV2_64;
        protected long ISAP_IV3_64;
        protected long[] k64;
        protected long[] npub64;
        protected long t0;
        protected long t1;
        protected long t2;
        protected long t3;
        protected long t4;
        protected long x0;
        protected long x1;
        protected long x2;
        protected long x3;
        protected long x4;

        public ISAPAEAD_A() {
            int unused = ISAPEngine.this.ISAP_rH = 64;
            int unused2 = ISAPEngine.this.ISAP_rH_SZ = (ISAPEngine.this.ISAP_rH + 7) >> 3;
        }

        private long ROTR(long j, long j2) {
            return (j << ((int) (64 - j2))) | (j >>> ((int) j2));
        }

        private int getLongSize(int i) {
            return (i >>> 3) + ((i & 7) != 0 ? 1 : 0);
        }

        /* access modifiers changed from: protected */
        public void ABSORB_MAC(byte[] bArr, int i) {
            int length = bArr.length >> 3;
            long[] jArr = new long[length];
            Pack.littleEndianToLong(bArr, 0, jArr, 0, length);
            int i2 = 0;
            while (i >= ISAPEngine.this.ISAP_rH_SZ) {
                this.x0 ^= U64BIG(jArr[i2]);
                P12();
                i -= ISAPEngine.this.ISAP_rH_SZ;
                i2++;
            }
            for (int i3 = 0; i3 < i; i3++) {
                this.x0 ^= (((long) bArr[(i2 << 3) + i3]) & 255) << ((7 - i3) << 3);
            }
            this.x0 = (128 << ((7 - i) << 3)) ^ this.x0;
            P12();
        }

        public void P12() {
            ROUND(240);
            ROUND(225);
            ROUND(210);
            ROUND(195);
            ROUND(180);
            ROUND(165);
            P6();
        }

        /* access modifiers changed from: protected */
        public void P6() {
            ROUND(150);
            ROUND(135);
            ROUND(120);
            ROUND(105);
            ROUND(90);
            ROUND(75);
        }

        /* access modifiers changed from: protected */
        public abstract void PX1();

        /* access modifiers changed from: protected */
        public abstract void PX2();

        /* access modifiers changed from: protected */
        public void ROUND(long j) {
            long j2 = this.x0;
            long j3 = this.x1;
            long j4 = this.x2;
            long j5 = this.x3;
            long j6 = this.x4;
            long j7 = ((((j2 ^ j3) ^ j4) ^ j5) ^ j) ^ ((((j2 ^ j4) ^ j6) ^ j) & j3);
            this.t0 = j7;
            this.t1 = ((((j2 ^ j4) ^ j5) ^ j6) ^ j) ^ (((j3 ^ j4) ^ j) & (j3 ^ j5));
            this.t2 = (((j3 ^ j4) ^ j6) ^ j) ^ (j5 & j6);
            this.t3 = ((j4 ^ (j2 ^ j3)) ^ j) ^ ((j2 ^ -1) & (j5 ^ j6));
            this.t4 = ((j2 ^ j6) & j3) ^ ((j3 ^ j5) ^ j6);
            this.x0 = (ROTR(j7, 19) ^ j7) ^ ROTR(this.t0, 28);
            long j8 = this.t1;
            this.x1 = (j8 ^ ROTR(j8, 39)) ^ ROTR(this.t1, 61);
            long j9 = this.t2;
            this.x2 = ((j9 ^ ROTR(j9, 1)) ^ ROTR(this.t2, 6)) ^ -1;
            long j10 = this.t3;
            this.x3 = (j10 ^ ROTR(j10, 10)) ^ ROTR(this.t3, 17);
            long j11 = this.t4;
            this.x4 = (j11 ^ ROTR(j11, 7)) ^ ROTR(this.t4, 41);
        }

        /* access modifiers changed from: protected */
        public long U64BIG(long j) {
            return (ROTR(j, 56) & 1095216660735L) | (ROTR(j, 8) & -72057589759737856L) | (ROTR(j, 24) & 71776119077928960L) | (ROTR(j, 40) & 280375465148160L);
        }

        public void init() {
            this.npub64 = new long[getLongSize(ISAPEngine.this.npub.length)];
            byte[] access$200 = ISAPEngine.this.npub;
            long[] jArr = this.npub64;
            Pack.littleEndianToLong(access$200, 0, jArr, 0, jArr.length);
            long[] jArr2 = this.npub64;
            jArr2[0] = U64BIG(jArr2[0]);
            long[] jArr3 = this.npub64;
            jArr3[1] = U64BIG(jArr3[1]);
            this.k64 = new long[getLongSize(ISAPEngine.this.k.length)];
            byte[] access$300 = ISAPEngine.this.k;
            long[] jArr4 = this.k64;
            Pack.littleEndianToLong(access$300, 0, jArr4, 0, jArr4.length);
            long[] jArr5 = this.k64;
            jArr5[0] = U64BIG(jArr5[0]);
            long[] jArr6 = this.k64;
            jArr6[1] = U64BIG(jArr6[1]);
            reset();
        }

        public void isap_enc(byte[] bArr, int i, int i2, byte[] bArr2, int i3, int i4) {
            int i5 = i2 >> 3;
            long[] jArr = new long[i5];
            Pack.littleEndianToLong(bArr, i, jArr, 0, i5);
            long[] jArr2 = new long[i5];
            int i6 = 0;
            while (i2 >= ISAPEngine.this.ISAP_rH_SZ) {
                jArr2[i6] = U64BIG(this.x0) ^ jArr[i6];
                PX1();
                i6++;
                i2 -= ISAPEngine.this.ISAP_rH_SZ;
            }
            Pack.longToLittleEndian(jArr2, 0, i5, bArr2, i3);
            byte[] longToLittleEndian = Pack.longToLittleEndian(this.x0);
            while (i2 > 0) {
                int i7 = i6 << 3;
                byte b = longToLittleEndian[ISAPEngine.this.ISAP_rH_SZ - i2];
                i2--;
                bArr2[((i7 + i3) + i2) - 1] = (byte) (bArr[(i7 + i) + i2] ^ b);
            }
        }

        public void isap_mac(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, int i3) {
            long[] jArr = this.npub64;
            this.x0 = jArr[0];
            this.x1 = jArr[1];
            this.x2 = this.ISAP_IV1_64;
            this.x4 = 0;
            this.x3 = 0;
            P12();
            ABSORB_MAC(bArr, i);
            this.x4 ^= 1;
            ABSORB_MAC(bArr2, i2);
            Pack.longToLittleEndian(U64BIG(this.x0), bArr3, 0);
            Pack.longToLittleEndian(U64BIG(this.x1), bArr3, 8);
            long j = this.x2;
            long j2 = this.x3;
            long j3 = this.x4;
            isap_rk(this.ISAP_IV2_64, bArr3, 16);
            this.x2 = j;
            this.x3 = j2;
            this.x4 = j3;
            P12();
            Pack.longToLittleEndian(U64BIG(this.x0), bArr3, i3);
            Pack.longToLittleEndian(U64BIG(this.x1), bArr3, i3 + 8);
        }

        public void isap_rk(long j, byte[] bArr, int i) {
            long[] jArr = this.k64;
            this.x0 = jArr[0];
            this.x1 = jArr[1];
            this.x2 = j;
            this.x4 = 0;
            this.x3 = 0;
            P12();
            for (int i2 = 0; i2 < (i << 3) - 1; i2++) {
                this.x0 ^= (((long) (((bArr[i2 >>> 3] >>> (7 - (i2 & 7))) & 1) << 7)) & 255) << 56;
                PX2();
            }
            this.x0 ^= (((long) bArr[i - 1]) & 1) << 63;
            P12();
        }

        public void reset() {
            isap_rk(this.ISAP_IV3_64, ISAPEngine.this.npub, 16);
            long[] jArr = this.npub64;
            this.x3 = jArr[0];
            this.x4 = jArr[1];
            PX1();
        }
    }

    private class ISAPAEAD_A_128 extends ISAPAEAD_A {
        public ISAPAEAD_A_128() {
            super();
            this.ISAP_IV1_64 = 108156764298152972L;
            this.ISAP_IV2_64 = 180214358336080908L;
            this.ISAP_IV3_64 = 252271952374008844L;
        }

        /* access modifiers changed from: protected */
        public void PX1() {
            P12();
        }

        /* access modifiers changed from: protected */
        public void PX2() {
            P12();
        }
    }

    private class ISAPAEAD_A_128A extends ISAPAEAD_A {
        public ISAPAEAD_A_128A() {
            super();
            this.ISAP_IV1_64 = 108156764297430540L;
            this.ISAP_IV2_64 = 180214358335358476L;
            this.ISAP_IV3_64 = 252271952373286412L;
        }

        /* access modifiers changed from: protected */
        public void PX1() {
            P6();
        }

        /* access modifiers changed from: protected */
        public void PX2() {
            ROUND(75);
        }
    }

    private abstract class ISAPAEAD_K implements ISAP_AEAD {
        protected short[] C = new short[5];
        protected short[] E = new short[25];
        protected short[] ISAP_IV1_16;
        protected short[] ISAP_IV2_16;
        protected short[] ISAP_IV3_16;
        final int ISAP_STATE_SZ_CRYPTO_NPUBBYTES = 24;
        private final int[] KeccakF400RoundConstants = {1, 32898, 32906, 32768, 32907, 1, 32897, 32777, 138, 136, 32777, 10, 32907, 139, 32905, 32771, 32770, 128, 32778, 10};
        protected short[] SX = new short[25];
        protected short[] iv16;
        protected short[] k16;

        public ISAPAEAD_K() {
            int unused = ISAPEngine.this.ISAP_rH = 144;
            int unused2 = ISAPEngine.this.ISAP_rH_SZ = (ISAPEngine.this.ISAP_rH + 7) >> 3;
        }

        private short ROL16(short s, int i) {
            short s2 = s & 65535;
            return (short) ((s2 >>> (16 - i)) ^ (s2 << i));
        }

        private void byteToShort(byte[] bArr, short[] sArr, int i) {
            for (int i2 = 0; i2 < i; i2++) {
                sArr[i2] = Pack.littleEndianToShort(bArr, i2 << 1);
            }
        }

        private void byteToShortXor(byte[] bArr, short[] sArr, int i) {
            for (int i2 = 0; i2 < i; i2++) {
                sArr[i2] = (short) (sArr[i2] ^ Pack.littleEndianToShort(bArr, i2 << 1));
            }
        }

        private void shortToByte(short[] sArr, byte[] bArr, int i) {
            for (int i2 = 0; i2 < 8; i2++) {
                Pack.shortToLittleEndian(sArr[i2], bArr, (i2 << 1) + i);
            }
        }

        /* access modifiers changed from: protected */
        public void ABSORB_MAC(short[] sArr, byte[] bArr, int i, short[] sArr2, short[] sArr3) {
            int i2 = 0;
            int i3 = 0;
            while (i > ISAPEngine.this.ISAP_rH_SZ) {
                byteToShortXor(bArr, sArr, ISAPEngine.this.ISAP_rH_SZ >> 1);
                i3 += ISAPEngine.this.ISAP_rH_SZ;
                i -= ISAPEngine.this.ISAP_rH_SZ;
                PermuteRoundsHX(sArr, sArr2, sArr3);
            }
            if (i == ISAPEngine.this.ISAP_rH_SZ) {
                byteToShortXor(bArr, sArr, ISAPEngine.this.ISAP_rH_SZ >> 1);
                PermuteRoundsHX(sArr, sArr2, sArr3);
                sArr[0] = (short) (sArr[0] ^ 128);
            } else {
                while (i2 < i) {
                    int i4 = i2 >> 1;
                    sArr[i4] = (short) (((bArr[i3] & 255) << ((i2 & 1) << 3)) ^ sArr[i4]);
                    i2++;
                    i3++;
                }
                int i5 = i >> 1;
                sArr[i5] = (short) ((128 << ((i & 1) << 3)) ^ sArr[i5]);
            }
            PermuteRoundsHX(sArr, sArr2, sArr3);
        }

        /* access modifiers changed from: protected */
        public abstract void PermuteRoundsBX(short[] sArr, short[] sArr2, short[] sArr3);

        /* access modifiers changed from: protected */
        public abstract void PermuteRoundsHX(short[] sArr, short[] sArr2, short[] sArr3);

        /* access modifiers changed from: protected */
        public abstract void PermuteRoundsKX(short[] sArr, short[] sArr2, short[] sArr3);

        public void init() {
            this.k16 = new short[(ISAPEngine.this.k.length >> 1)];
            byte[] access$300 = ISAPEngine.this.k;
            short[] sArr = this.k16;
            byteToShort(access$300, sArr, sArr.length);
            this.iv16 = new short[(ISAPEngine.this.npub.length >> 1)];
            byte[] access$200 = ISAPEngine.this.npub;
            short[] sArr2 = this.iv16;
            byteToShort(access$200, sArr2, sArr2.length);
            reset();
        }

        public void isap_enc(byte[] bArr, int i, int i2, byte[] bArr2, int i3, int i4) {
            int i5;
            while (true) {
                i5 = 0;
                if (i2 < ISAPEngine.this.ISAP_rH_SZ) {
                    break;
                }
                while (i5 < ISAPEngine.this.ISAP_rH_SZ) {
                    bArr2[i3] = (byte) (bArr[i] ^ (this.SX[i5 >> 1] >>> ((i5 & 1) << 3)));
                    i5++;
                    i3++;
                    i++;
                }
                i2 -= ISAPEngine.this.ISAP_rH_SZ;
                PermuteRoundsKX(this.SX, this.E, this.C);
            }
            while (i5 < i2) {
                bArr2[i3] = (byte) (bArr[i] ^ (this.SX[i5 >> 1] >>> ((i5 & 1) << 3)));
                i5++;
                i3++;
                i++;
            }
        }

        public void isap_mac(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, int i3) {
            byte[] bArr4 = bArr3;
            int i4 = i3;
            short[] sArr = new short[25];
            this.SX = sArr;
            System.arraycopy(this.iv16, 0, sArr, 0, 8);
            System.arraycopy(this.ISAP_IV1_16, 0, this.SX, 8, 4);
            PermuteRoundsHX(this.SX, this.E, this.C);
            ABSORB_MAC(this.SX, bArr, i, this.E, this.C);
            short[] sArr2 = this.SX;
            sArr2[24] = (short) (sArr2[24] ^ Http2CodecUtil.MAX_WEIGHT);
            ABSORB_MAC(sArr2, bArr2, i2, this.E, this.C);
            shortToByte(this.SX, bArr3, i4);
            isap_rk(this.ISAP_IV2_16, bArr3, 16, this.SX, 16, this.C);
            PermuteRoundsHX(this.SX, this.E, this.C);
            shortToByte(this.SX, bArr3, i4);
        }

        public void isap_rk(short[] sArr, byte[] bArr, int i, short[] sArr2, int i2, short[] sArr3) {
            short[] sArr4 = new short[25];
            short[] sArr5 = new short[25];
            int i3 = 8;
            System.arraycopy(this.k16, 0, sArr4, 0, 8);
            System.arraycopy(sArr, 0, sArr4, 8, 4);
            PermuteRoundsKX(sArr4, sArr5, sArr3);
            for (int i4 = 0; i4 < (i << 3) - 1; i4++) {
                sArr4[0] = (short) (sArr4[0] ^ (((bArr[i4 >> 3] >>> (7 - (i4 & 7))) & 1) << 7));
                PermuteRoundsBX(sArr4, sArr5, sArr3);
            }
            sArr4[0] = (short) (sArr4[0] ^ ((bArr[i - 1] & 1) << 7));
            PermuteRoundsKX(sArr4, sArr5, sArr3);
            if (i2 == 24) {
                i3 = 17;
            }
            System.arraycopy(sArr4, 0, sArr2, 0, i3);
        }

        /* access modifiers changed from: protected */
        public void prepareThetaX(short[] sArr, short[] sArr2) {
            sArr2[0] = (short) ((((sArr[0] ^ sArr[5]) ^ sArr[10]) ^ sArr[15]) ^ sArr[20]);
            sArr2[1] = (short) ((((sArr[1] ^ sArr[6]) ^ sArr[11]) ^ sArr[16]) ^ sArr[21]);
            sArr2[2] = (short) ((((sArr[2] ^ sArr[7]) ^ sArr[12]) ^ sArr[17]) ^ sArr[22]);
            sArr2[3] = (short) ((((sArr[3] ^ sArr[8]) ^ sArr[13]) ^ sArr[18]) ^ sArr[23]);
            sArr2[4] = (short) (sArr[24] ^ (((sArr[4] ^ sArr[9]) ^ sArr[14]) ^ sArr[19]));
        }

        public void reset() {
            this.SX = new short[25];
            this.E = new short[25];
            this.C = new short[5];
            isap_rk(this.ISAP_IV3_16, ISAPEngine.this.npub, 16, this.SX, 24, this.C);
            System.arraycopy(this.iv16, 0, this.SX, 17, 8);
            PermuteRoundsKX(this.SX, this.E, this.C);
        }

        /* access modifiers changed from: protected */
        public void rounds12X(short[] sArr, short[] sArr2, short[] sArr3) {
            prepareThetaX(sArr, sArr3);
            rounds_8_18(sArr, sArr2, sArr3);
        }

        /* access modifiers changed from: protected */
        public void rounds_12_18(short[] sArr, short[] sArr2, short[] sArr3) {
            thetaRhoPiChiIotaPrepareTheta(12, sArr, sArr2, sArr3);
            thetaRhoPiChiIotaPrepareTheta(13, sArr2, sArr, sArr3);
            thetaRhoPiChiIotaPrepareTheta(14, sArr, sArr2, sArr3);
            thetaRhoPiChiIotaPrepareTheta(15, sArr2, sArr, sArr3);
            thetaRhoPiChiIotaPrepareTheta(16, sArr, sArr2, sArr3);
            thetaRhoPiChiIotaPrepareTheta(17, sArr2, sArr, sArr3);
            thetaRhoPiChiIotaPrepareTheta(18, sArr, sArr2, sArr3);
            thetaRhoPiChiIota(sArr2, sArr, sArr3);
        }

        /* access modifiers changed from: protected */
        public void rounds_4_18(short[] sArr, short[] sArr2, short[] sArr3) {
            thetaRhoPiChiIotaPrepareTheta(4, sArr, sArr2, sArr3);
            thetaRhoPiChiIotaPrepareTheta(5, sArr2, sArr, sArr3);
            thetaRhoPiChiIotaPrepareTheta(6, sArr, sArr2, sArr3);
            thetaRhoPiChiIotaPrepareTheta(7, sArr2, sArr, sArr3);
            rounds_8_18(sArr, sArr2, sArr3);
        }

        /* access modifiers changed from: protected */
        public void rounds_8_18(short[] sArr, short[] sArr2, short[] sArr3) {
            thetaRhoPiChiIotaPrepareTheta(8, sArr, sArr2, sArr3);
            thetaRhoPiChiIotaPrepareTheta(9, sArr2, sArr, sArr3);
            thetaRhoPiChiIotaPrepareTheta(10, sArr, sArr2, sArr3);
            thetaRhoPiChiIotaPrepareTheta(11, sArr2, sArr, sArr3);
            rounds_12_18(sArr, sArr2, sArr3);
        }

        /* access modifiers changed from: protected */
        public void thetaRhoPiChiIota(short[] sArr, short[] sArr2, short[] sArr3) {
            short ROL16 = (short) (sArr3[4] ^ ROL16(sArr3[1], 1));
            short ROL162 = (short) (sArr3[0] ^ ROL16(sArr3[2], 1));
            short ROL163 = (short) (sArr3[1] ^ ROL16(sArr3[3], 1));
            short ROL164 = (short) (sArr3[2] ^ ROL16(sArr3[4], 1));
            short ROL165 = (short) (sArr3[3] ^ ROL16(sArr3[0], 1));
            short s = (short) (sArr[0] ^ ROL16);
            sArr[0] = s;
            short s2 = (short) (sArr[6] ^ ROL162);
            sArr[6] = s2;
            short ROL166 = ROL16(s2, 12);
            short s3 = (short) (sArr[12] ^ ROL163);
            sArr[12] = s3;
            short ROL167 = ROL16(s3, 11);
            short s4 = (short) (sArr[18] ^ ROL164);
            sArr[18] = s4;
            short ROL168 = ROL16(s4, 5);
            short s5 = (short) (sArr[24] ^ ROL165);
            sArr[24] = s5;
            short ROL169 = ROL16(s5, 14);
            sArr2[0] = (short) ((s ^ ((ROL166 ^ -1) & ROL167)) ^ this.KeccakF400RoundConstants[19]);
            sArr2[1] = (short) (((ROL167 ^ -1) & ROL168) ^ ROL166);
            sArr2[2] = (short) (((ROL168 ^ -1) & ROL169) ^ ROL167);
            sArr2[3] = (short) (((ROL169 ^ -1) & s) ^ ROL168);
            sArr2[4] = (short) (((s ^ -1) & ROL166) ^ ROL169);
            short s6 = (short) (sArr[3] ^ ROL164);
            sArr[3] = s6;
            short ROL1610 = ROL16(s6, 12);
            short s7 = (short) (sArr[9] ^ ROL165);
            sArr[9] = s7;
            short ROL1611 = ROL16(s7, 4);
            short s8 = (short) (sArr[10] ^ ROL16);
            sArr[10] = s8;
            short ROL1612 = ROL16(s8, 3);
            short s9 = (short) (sArr[16] ^ ROL162);
            sArr[16] = s9;
            short ROL1613 = ROL16(s9, 13);
            short s10 = (short) (sArr[22] ^ ROL163);
            sArr[22] = s10;
            short ROL1614 = ROL16(s10, 13);
            sArr2[5] = (short) (ROL1610 ^ ((ROL1611 ^ -1) & ROL1612));
            sArr2[6] = (short) (((ROL1612 ^ -1) & ROL1613) ^ ROL1611);
            sArr2[7] = (short) (((ROL1613 ^ -1) & ROL1614) ^ ROL1612);
            sArr2[8] = (short) (((ROL1614 ^ -1) & ROL1610) ^ ROL1613);
            sArr2[9] = (short) (((ROL1610 ^ -1) & ROL1611) ^ ROL1614);
            short s11 = (short) (sArr[1] ^ ROL162);
            sArr[1] = s11;
            short ROL1615 = ROL16(s11, 1);
            short s12 = (short) (sArr[7] ^ ROL163);
            sArr[7] = s12;
            short ROL1616 = ROL16(s12, 6);
            short s13 = (short) (sArr[13] ^ ROL164);
            sArr[13] = s13;
            short ROL1617 = ROL16(s13, 9);
            short s14 = (short) (sArr[19] ^ ROL165);
            sArr[19] = s14;
            short ROL1618 = ROL16(s14, 8);
            short s15 = (short) (sArr[20] ^ ROL16);
            sArr[20] = s15;
            short ROL1619 = ROL16(s15, 2);
            sArr2[10] = (short) (((ROL1616 ^ -1) & ROL1617) ^ ROL1615);
            sArr2[11] = (short) (((ROL1617 ^ -1) & ROL1618) ^ ROL1616);
            sArr2[12] = (short) (ROL1617 ^ ((ROL1618 ^ -1) & ROL1619));
            sArr2[13] = (short) (((ROL1619 ^ -1) & ROL1615) ^ ROL1618);
            sArr2[14] = (short) (((ROL1615 ^ -1) & ROL1616) ^ ROL1619);
            short s16 = (short) (sArr[4] ^ ROL165);
            sArr[4] = s16;
            short ROL1620 = ROL16(s16, 11);
            short s17 = (short) (sArr[5] ^ ROL16);
            sArr[5] = s17;
            short ROL1621 = ROL16(s17, 4);
            short s18 = (short) (sArr[11] ^ ROL162);
            sArr[11] = s18;
            short ROL1622 = ROL16(s18, 10);
            short s19 = (short) (sArr[17] ^ ROL163);
            sArr[17] = s19;
            short ROL1623 = ROL16(s19, 15);
            short s20 = (short) (sArr[23] ^ ROL164);
            sArr[23] = s20;
            short ROL1624 = ROL16(s20, 8);
            sArr2[15] = (short) (((ROL1621 ^ -1) & ROL1622) ^ ROL1620);
            sArr2[16] = (short) (((ROL1622 ^ -1) & ROL1623) ^ ROL1621);
            sArr2[17] = (short) (ROL1622 ^ ((ROL1623 ^ -1) & ROL1624));
            sArr2[18] = (short) (((ROL1624 ^ -1) & ROL1620) ^ ROL1623);
            sArr2[19] = (short) ((ROL1621 & (ROL1620 ^ -1)) ^ ROL1624);
            short s21 = (short) (sArr[2] ^ ROL163);
            sArr[2] = s21;
            short ROL1625 = ROL16(s21, 14);
            short s22 = (short) (sArr[8] ^ ROL164);
            sArr[8] = s22;
            short ROL1626 = ROL16(s22, 7);
            short s23 = (short) (sArr[14] ^ ROL165);
            sArr[14] = s23;
            short ROL1627 = ROL16(s23, 7);
            short s24 = (short) (ROL16 ^ sArr[15]);
            sArr[15] = s24;
            short ROL1628 = ROL16(s24, 9);
            short s25 = (short) (ROL162 ^ sArr[21]);
            sArr[21] = s25;
            short ROL1629 = ROL16(s25, 2);
            sArr2[20] = (short) (((ROL1626 ^ -1) & ROL1627) ^ ROL1625);
            sArr2[21] = (short) (((ROL1627 ^ -1) & ROL1628) ^ ROL1626);
            sArr2[22] = (short) (ROL1627 ^ ((ROL1628 ^ -1) & ROL1629));
            sArr2[23] = (short) (ROL1628 ^ ((ROL1629 ^ -1) & ROL1625));
            sArr2[24] = (short) (((ROL1625 ^ -1) & ROL1626) ^ ROL1629);
        }

        /* access modifiers changed from: protected */
        public void thetaRhoPiChiIotaPrepareTheta(int i, short[] sArr, short[] sArr2, short[] sArr3) {
            short ROL16 = (short) (sArr3[4] ^ ROL16(sArr3[1], 1));
            short ROL162 = (short) (sArr3[0] ^ ROL16(sArr3[2], 1));
            short ROL163 = (short) (sArr3[1] ^ ROL16(sArr3[3], 1));
            short ROL164 = (short) (sArr3[2] ^ ROL16(sArr3[4], 1));
            short ROL165 = (short) (sArr3[3] ^ ROL16(sArr3[0], 1));
            short s = (short) (sArr[0] ^ ROL16);
            sArr[0] = s;
            short s2 = (short) (sArr[6] ^ ROL162);
            sArr[6] = s2;
            short ROL166 = ROL16(s2, 12);
            short s3 = (short) (sArr[12] ^ ROL163);
            sArr[12] = s3;
            short ROL167 = ROL16(s3, 11);
            short s4 = (short) (sArr[18] ^ ROL164);
            sArr[18] = s4;
            short ROL168 = ROL16(s4, 5);
            short s5 = (short) (sArr[24] ^ ROL165);
            sArr[24] = s5;
            short ROL169 = ROL16(s5, 14);
            short s6 = (short) ((s ^ ((ROL166 ^ -1) & ROL167)) ^ this.KeccakF400RoundConstants[i]);
            sArr2[0] = s6;
            sArr3[0] = s6;
            short s7 = (short) (((ROL167 ^ -1) & ROL168) ^ ROL166);
            sArr2[1] = s7;
            sArr3[1] = s7;
            short s8 = (short) (((ROL168 ^ -1) & ROL169) ^ ROL167);
            sArr2[2] = s8;
            sArr3[2] = s8;
            short s9 = (short) (((ROL169 ^ -1) & s) ^ ROL168);
            sArr2[3] = s9;
            sArr3[3] = s9;
            short s10 = (short) (((s ^ -1) & ROL166) ^ ROL169);
            sArr2[4] = s10;
            sArr3[4] = s10;
            short s11 = (short) (sArr[3] ^ ROL164);
            sArr[3] = s11;
            short ROL1610 = ROL16(s11, 12);
            short s12 = (short) (sArr[9] ^ ROL165);
            sArr[9] = s12;
            short ROL1611 = ROL16(s12, 4);
            short s13 = (short) (sArr[10] ^ ROL16);
            sArr[10] = s13;
            short ROL1612 = ROL16(s13, 3);
            short s14 = (short) (sArr[16] ^ ROL162);
            sArr[16] = s14;
            short ROL1613 = ROL16(s14, 13);
            short s15 = (short) (sArr[22] ^ ROL163);
            sArr[22] = s15;
            short ROL1614 = ROL16(s15, 13);
            short s16 = (short) (ROL1610 ^ ((ROL1611 ^ -1) & ROL1612));
            sArr2[5] = s16;
            sArr3[0] = (short) (sArr3[0] ^ s16);
            short s17 = (short) (((ROL1612 ^ -1) & ROL1613) ^ ROL1611);
            sArr2[6] = s17;
            sArr3[1] = (short) (sArr3[1] ^ s17);
            short s18 = (short) (ROL1612 ^ ((ROL1613 ^ -1) & ROL1614));
            sArr2[7] = s18;
            sArr3[2] = (short) (sArr3[2] ^ s18);
            short s19 = (short) (ROL1613 ^ ((ROL1614 ^ -1) & ROL1610));
            sArr2[8] = s19;
            sArr3[3] = (short) (sArr3[3] ^ s19);
            short s20 = (short) (((ROL1610 ^ -1) & ROL1611) ^ ROL1614);
            sArr2[9] = s20;
            sArr3[4] = (short) (s20 ^ sArr3[4]);
            short s21 = (short) (sArr[1] ^ ROL162);
            sArr[1] = s21;
            short ROL1615 = ROL16(s21, 1);
            short s22 = (short) (sArr[7] ^ ROL163);
            sArr[7] = s22;
            short ROL1616 = ROL16(s22, 6);
            short s23 = (short) (sArr[13] ^ ROL164);
            sArr[13] = s23;
            short ROL1617 = ROL16(s23, 9);
            short s24 = (short) (sArr[19] ^ ROL165);
            sArr[19] = s24;
            short ROL1618 = ROL16(s24, 8);
            short s25 = (short) (sArr[20] ^ ROL16);
            sArr[20] = s25;
            short ROL1619 = ROL16(s25, 2);
            short s26 = (short) (ROL1615 ^ ((ROL1616 ^ -1) & ROL1617));
            sArr2[10] = s26;
            sArr3[0] = (short) (sArr3[0] ^ s26);
            short s27 = (short) (((ROL1617 ^ -1) & ROL1618) ^ ROL1616);
            sArr2[11] = s27;
            sArr3[1] = (short) (sArr3[1] ^ s27);
            short s28 = (short) (ROL1617 ^ ((ROL1618 ^ -1) & ROL1619));
            sArr2[12] = s28;
            sArr3[2] = (short) (s28 ^ sArr3[2]);
            short s29 = (short) (((ROL1619 ^ -1) & ROL1615) ^ ROL1618);
            sArr2[13] = s29;
            sArr3[3] = (short) (s29 ^ sArr3[3]);
            short s30 = (short) (((ROL1615 ^ -1) & ROL1616) ^ ROL1619);
            sArr2[14] = s30;
            sArr3[4] = (short) (s30 ^ sArr3[4]);
            short s31 = (short) (sArr[4] ^ ROL165);
            sArr[4] = s31;
            short ROL1620 = ROL16(s31, 11);
            short s32 = (short) (sArr[5] ^ ROL16);
            sArr[5] = s32;
            short ROL1621 = ROL16(s32, 4);
            short s33 = (short) (sArr[11] ^ ROL162);
            sArr[11] = s33;
            short ROL1622 = ROL16(s33, 10);
            short s34 = (short) (sArr[17] ^ ROL163);
            sArr[17] = s34;
            short ROL1623 = ROL16(s34, 15);
            short s35 = (short) (sArr[23] ^ ROL164);
            sArr[23] = s35;
            short ROL1624 = ROL16(s35, 8);
            short s36 = (short) (((ROL1621 ^ -1) & ROL1622) ^ ROL1620);
            sArr2[15] = s36;
            sArr3[0] = (short) (sArr3[0] ^ s36);
            short s37 = (short) (((ROL1622 ^ -1) & ROL1623) ^ ROL1621);
            sArr2[16] = s37;
            sArr3[1] = (short) (sArr3[1] ^ s37);
            short s38 = (short) (ROL1622 ^ ((ROL1623 ^ -1) & ROL1624));
            sArr2[17] = s38;
            sArr3[2] = (short) (s38 ^ sArr3[2]);
            short s39 = (short) (((ROL1624 ^ -1) & ROL1620) ^ ROL1623);
            sArr2[18] = s39;
            sArr3[3] = (short) (s39 ^ sArr3[3]);
            short s40 = (short) (((ROL1620 ^ -1) & ROL1621) ^ ROL1624);
            sArr2[19] = s40;
            sArr3[4] = (short) (s40 ^ sArr3[4]);
            short s41 = (short) (sArr[2] ^ ROL163);
            sArr[2] = s41;
            short ROL1625 = ROL16(s41, 14);
            short s42 = (short) (ROL164 ^ sArr[8]);
            sArr[8] = s42;
            short ROL1626 = ROL16(s42, 7);
            short s43 = (short) (ROL165 ^ sArr[14]);
            sArr[14] = s43;
            short ROL1627 = ROL16(s43, 7);
            short s44 = (short) (ROL16 ^ sArr[15]);
            sArr[15] = s44;
            short ROL1628 = ROL16(s44, 9);
            short s45 = (short) (ROL162 ^ sArr[21]);
            sArr[21] = s45;
            short ROL1629 = ROL16(s45, 2);
            short s46 = (short) (((ROL1626 ^ -1) & ROL1627) ^ ROL1625);
            sArr2[20] = s46;
            sArr3[0] = (short) (s46 ^ sArr3[0]);
            short s47 = (short) (((ROL1627 ^ -1) & ROL1628) ^ ROL1626);
            sArr2[21] = s47;
            sArr3[1] = (short) (s47 ^ sArr3[1]);
            short s48 = (short) (((ROL1628 ^ -1) & ROL1629) ^ ROL1627);
            sArr2[22] = s48;
            sArr3[2] = (short) (s48 ^ sArr3[2]);
            short s49 = (short) (ROL1628 ^ ((ROL1629 ^ -1) & ROL1625));
            sArr2[23] = s49;
            sArr3[3] = (short) (s49 ^ sArr3[3]);
            short s50 = (short) (((ROL1625 ^ -1) & ROL1626) ^ ROL1629);
            sArr2[24] = s50;
            sArr3[4] = (short) (s50 ^ sArr3[4]);
        }
    }

    private class ISAPAEAD_K_128 extends ISAPAEAD_K {
        public ISAPAEAD_K_128() {
            super();
            this.ISAP_IV1_16 = new short[]{-32767, 400, 3092, 3084};
            this.ISAP_IV2_16 = new short[]{-32766, 400, 3092, 3084};
            this.ISAP_IV3_16 = new short[]{-32765, 400, 3092, 3084};
        }

        /* access modifiers changed from: protected */
        public void PermuteRoundsBX(short[] sArr, short[] sArr2, short[] sArr3) {
            rounds12X(sArr, sArr2, sArr3);
        }

        /* access modifiers changed from: protected */
        public void PermuteRoundsHX(short[] sArr, short[] sArr2, short[] sArr3) {
            prepareThetaX(sArr, sArr3);
            thetaRhoPiChiIotaPrepareTheta(0, sArr, sArr2, sArr3);
            thetaRhoPiChiIotaPrepareTheta(1, sArr2, sArr, sArr3);
            thetaRhoPiChiIotaPrepareTheta(2, sArr, sArr2, sArr3);
            thetaRhoPiChiIotaPrepareTheta(3, sArr2, sArr, sArr3);
            rounds_4_18(sArr, sArr2, sArr3);
        }

        /* access modifiers changed from: protected */
        public void PermuteRoundsKX(short[] sArr, short[] sArr2, short[] sArr3) {
            rounds12X(sArr, sArr2, sArr3);
        }
    }

    private class ISAPAEAD_K_128A extends ISAPAEAD_K {
        public ISAPAEAD_K_128A() {
            super();
            this.ISAP_IV1_16 = new short[]{-32767, 400, 272, 2056};
            this.ISAP_IV2_16 = new short[]{-32766, 400, 272, 2056};
            this.ISAP_IV3_16 = new short[]{-32765, 400, 272, 2056};
        }

        /* access modifiers changed from: protected */
        public void PermuteRoundsBX(short[] sArr, short[] sArr2, short[] sArr3) {
            prepareThetaX(sArr, sArr3);
            thetaRhoPiChiIotaPrepareTheta(19, sArr, sArr2, sArr3);
            System.arraycopy(sArr2, 0, sArr, 0, sArr2.length);
        }

        /* access modifiers changed from: protected */
        public void PermuteRoundsHX(short[] sArr, short[] sArr2, short[] sArr3) {
            prepareThetaX(sArr, sArr3);
            rounds_4_18(sArr, sArr2, sArr3);
        }

        /* access modifiers changed from: protected */
        public void PermuteRoundsKX(short[] sArr, short[] sArr2, short[] sArr3) {
            prepareThetaX(sArr, sArr3);
            rounds_12_18(sArr, sArr2, sArr3);
        }
    }

    private interface ISAP_AEAD {
        void init();

        void isap_enc(byte[] bArr, int i, int i2, byte[] bArr2, int i3, int i4);

        void isap_mac(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, int i3);

        void reset();
    }

    public enum IsapType {
        ISAP_A_128A,
        ISAP_K_128A,
        ISAP_A_128,
        ISAP_K_128
    }

    public ISAPEngine(IsapType isapType) {
        String str;
        int i = AnonymousClass1.$SwitchMap$org$bouncycastle$crypto$engines$ISAPEngine$IsapType[isapType.ordinal()];
        if (i == 1) {
            this.ISAPAEAD = new ISAPAEAD_A_128A();
            str = "ISAP-A-128A AEAD";
        } else if (i == 2) {
            this.ISAPAEAD = new ISAPAEAD_K_128A();
            str = "ISAP-K-128A AEAD";
        } else if (i == 3) {
            this.ISAPAEAD = new ISAPAEAD_A_128();
            str = "ISAP-A-128 AEAD";
        } else if (i == 4) {
            this.ISAPAEAD = new ISAPAEAD_K_128();
            str = "ISAP-K-128 AEAD";
        } else {
            return;
        }
        this.algorithmName = str;
    }

    public int doFinal(byte[] bArr, int i) throws IllegalStateException, InvalidCipherTextException {
        byte[] bArr2 = bArr;
        int i2 = i;
        if (this.initialised) {
            int i3 = 0;
            if (this.forEncryption) {
                byte[] byteArray = this.message.toByteArray();
                int length = byteArray.length;
                int i4 = i2 + length;
                if (i4 + 16 <= bArr2.length) {
                    this.ISAPAEAD.isap_enc(byteArray, 0, length, bArr, i, bArr2.length);
                    this.outputStream.write(bArr2, i2, length);
                    this.ad = this.aadData.toByteArray();
                    byte[] byteArray2 = this.outputStream.toByteArray();
                    this.c = byteArray2;
                    byte[] bArr3 = new byte[16];
                    this.mac = bArr3;
                    ISAP_AEAD isap_aead = this.ISAPAEAD;
                    byte[] bArr4 = this.ad;
                    isap_aead.isap_mac(bArr4, bArr4.length, byteArray2, byteArray2.length, bArr3, 0);
                    System.arraycopy(this.mac, 0, bArr2, i4, 16);
                    return length + 16;
                }
                throw new OutputLengthException("output buffer is too short");
            }
            this.ad = this.aadData.toByteArray();
            byte[] byteArray3 = this.message.toByteArray();
            this.c = byteArray3;
            byte[] bArr5 = new byte[16];
            this.mac = bArr5;
            int length2 = byteArray3.length - bArr5.length;
            if (length2 + i2 <= bArr2.length) {
                ISAP_AEAD isap_aead2 = this.ISAPAEAD;
                byte[] bArr6 = this.ad;
                isap_aead2.isap_mac(bArr6, bArr6.length, byteArray3, length2, bArr5, 0);
                this.ISAPAEAD.reset();
                while (i3 < 16) {
                    if (this.mac[i3] == this.c[length2 + i3]) {
                        i3++;
                    } else {
                        throw new IllegalArgumentException("Mac does not match");
                    }
                }
                this.ISAPAEAD.isap_enc(this.c, 0, length2, bArr, i, bArr2.length);
                return length2;
            }
            throw new OutputLengthException("output buffer is too short");
        }
        throw new IllegalArgumentException("Need call init function before encryption/decryption");
    }

    public String getAlgorithmName() {
        return this.algorithmName;
    }

    public int getBlockSize() {
        return this.ISAP_rH_SZ;
    }

    public int getIVBytesSize() {
        return 16;
    }

    public int getKeyBytesSize() {
        return 16;
    }

    public byte[] getMac() {
        return this.mac;
    }

    public int getOutputSize(int i) {
        return i + 16;
    }

    public int getUpdateOutputSize(int i) {
        return i;
    }

    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        this.forEncryption = z;
        if (cipherParameters instanceof ParametersWithIV) {
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            byte[] iv = parametersWithIV.getIV();
            if (iv == null || iv.length != 16) {
                throw new IllegalArgumentException("ISAP AEAD requires exactly 12 bytes of IV");
            } else if (parametersWithIV.getParameters() instanceof KeyParameter) {
                byte[] key = ((KeyParameter) parametersWithIV.getParameters()).getKey();
                if (key.length == 16) {
                    CryptoServicesRegistrar.checkConstraints(new DefaultServiceProperties(getAlgorithmName(), 128, cipherParameters, Utils.getPurpose(z)));
                    byte[] bArr = new byte[iv.length];
                    this.npub = bArr;
                    this.k = new byte[key.length];
                    System.arraycopy(iv, 0, bArr, 0, iv.length);
                    System.arraycopy(key, 0, this.k, 0, key.length);
                    this.ISAPAEAD.init();
                    this.initialised = true;
                    reset();
                    return;
                }
                throw new IllegalArgumentException("ISAP AEAD key must be 128 bits long");
            } else {
                throw new IllegalArgumentException("ISAP AEAD init parameters must include a key");
            }
        } else {
            throw new IllegalArgumentException("ISAP AEAD init parameters must include an IV");
        }
    }

    public void processAADByte(byte b) {
        this.aadData.write(b);
    }

    public void processAADBytes(byte[] bArr, int i, int i2) {
        if (i + i2 > bArr.length) {
            throw new DataLengthException("input buffer too short".concat(this.forEncryption ? "encryption" : "decryption"));
        }
        this.aadData.write(bArr, i, i2);
    }

    public int processByte(byte b, byte[] bArr, int i) throws DataLengthException {
        return processBytes(new byte[]{b}, 0, 1, bArr, i);
    }

    public int processBytes(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws DataLengthException {
        if (!this.initialised) {
            throw new IllegalArgumentException("Need call init function before encryption/decryption");
        } else if (i + i2 <= bArr.length) {
            this.message.write(bArr, i, i2);
            if (!this.forEncryption || this.message.size() < this.ISAP_rH_SZ) {
                return 0;
            }
            int size = this.message.size();
            int i4 = this.ISAP_rH_SZ;
            int i5 = (size / i4) * i4;
            if (i3 + i5 <= bArr2.length) {
                byte[] byteArray = this.message.toByteArray();
                this.ISAPAEAD.isap_enc(byteArray, 0, i5, bArr2, i3, bArr2.length);
                this.outputStream.write(bArr2, i3, i5);
                this.message.reset();
                this.message.write(byteArray, i5, byteArray.length - i5);
                return i5;
            }
            throw new OutputLengthException("output buffer is too short");
        } else {
            throw new DataLengthException("input buffer too short");
        }
    }

    public void reset() {
        if (this.initialised) {
            this.aadData.reset();
            this.ISAPAEAD.reset();
            this.message.reset();
            this.outputStream.reset();
            return;
        }
        throw new IllegalArgumentException("Need call init function before encryption/decryption");
    }
}
