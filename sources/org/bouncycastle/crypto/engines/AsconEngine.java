package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.constraints.DefaultServiceProperties;
import org.bouncycastle.crypto.modes.AEADCipher;
import org.bouncycastle.crypto.params.AEADParameters;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Longs;
import org.bouncycastle.util.Pack;

public class AsconEngine implements AEADCipher {
    private final int ASCON_AEAD_RATE;
    private final long ASCON_IV;
    private final int CRYPTO_ABYTES;
    private final int CRYPTO_KEYBYTES;
    private long K0;
    private long K1;
    private long K2;
    private long N0;
    private long N1;
    private final String algorithmName;
    private final AsconParameters asconParameters;
    private byte[] initialAssociatedText;
    private final byte[] m_buf;
    private int m_bufPos = 0;
    private final int m_bufferSizeDecrypt;
    private State m_state = State.Uninitialized;
    private byte[] mac;
    private final int nr;
    private long x0;
    private long x1;
    private long x2;
    private long x3;
    private long x4;

    /* renamed from: org.bouncycastle.crypto.engines.AsconEngine$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$bouncycastle$crypto$engines$AsconEngine$AsconParameters;
        static final /* synthetic */ int[] $SwitchMap$org$bouncycastle$crypto$engines$AsconEngine$State;

        /* JADX WARNING: Can't wrap try/catch for region: R(23:0|(2:1|2)|3|(2:5|6)|7|9|10|11|12|13|14|15|16|17|18|19|20|21|23|24|25|26|(3:27|28|30)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(26:0|1|2|3|(2:5|6)|7|9|10|11|12|13|14|15|16|17|18|19|20|21|23|24|25|26|27|28|30) */
        /* JADX WARNING: Can't wrap try/catch for region: R(27:0|1|2|3|5|6|7|9|10|11|12|13|14|15|16|17|18|19|20|21|23|24|25|26|27|28|30) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0033 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0071 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x007b */
        static {
            /*
                org.bouncycastle.crypto.engines.AsconEngine$State[] r0 = org.bouncycastle.crypto.engines.AsconEngine.State.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$bouncycastle$crypto$engines$AsconEngine$State = r0
                r1 = 1
                org.bouncycastle.crypto.engines.AsconEngine$State r2 = org.bouncycastle.crypto.engines.AsconEngine.State.DecInit     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = $SwitchMap$org$bouncycastle$crypto$engines$AsconEngine$State     // Catch:{ NoSuchFieldError -> 0x001d }
                org.bouncycastle.crypto.engines.AsconEngine$State r3 = org.bouncycastle.crypto.engines.AsconEngine.State.EncInit     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                r2 = 3
                int[] r3 = $SwitchMap$org$bouncycastle$crypto$engines$AsconEngine$State     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.bouncycastle.crypto.engines.AsconEngine$State r4 = org.bouncycastle.crypto.engines.AsconEngine.State.DecAad     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r3 = $SwitchMap$org$bouncycastle$crypto$engines$AsconEngine$State     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.bouncycastle.crypto.engines.AsconEngine$State r4 = org.bouncycastle.crypto.engines.AsconEngine.State.EncAad     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r5 = 4
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r3 = $SwitchMap$org$bouncycastle$crypto$engines$AsconEngine$State     // Catch:{ NoSuchFieldError -> 0x003e }
                org.bouncycastle.crypto.engines.AsconEngine$State r4 = org.bouncycastle.crypto.engines.AsconEngine.State.EncFinal     // Catch:{ NoSuchFieldError -> 0x003e }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r5 = 5
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r3 = $SwitchMap$org$bouncycastle$crypto$engines$AsconEngine$State     // Catch:{ NoSuchFieldError -> 0x0049 }
                org.bouncycastle.crypto.engines.AsconEngine$State r4 = org.bouncycastle.crypto.engines.AsconEngine.State.DecData     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r5 = 6
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r3 = $SwitchMap$org$bouncycastle$crypto$engines$AsconEngine$State     // Catch:{ NoSuchFieldError -> 0x0054 }
                org.bouncycastle.crypto.engines.AsconEngine$State r4 = org.bouncycastle.crypto.engines.AsconEngine.State.EncData     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r5 = 7
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r3 = $SwitchMap$org$bouncycastle$crypto$engines$AsconEngine$State     // Catch:{ NoSuchFieldError -> 0x0060 }
                org.bouncycastle.crypto.engines.AsconEngine$State r4 = org.bouncycastle.crypto.engines.AsconEngine.State.DecFinal     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r5 = 8
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                org.bouncycastle.crypto.engines.AsconEngine$AsconParameters[] r3 = org.bouncycastle.crypto.engines.AsconEngine.AsconParameters.values()
                int r3 = r3.length
                int[] r3 = new int[r3]
                $SwitchMap$org$bouncycastle$crypto$engines$AsconEngine$AsconParameters = r3
                org.bouncycastle.crypto.engines.AsconEngine$AsconParameters r4 = org.bouncycastle.crypto.engines.AsconEngine.AsconParameters.ascon80pq     // Catch:{ NoSuchFieldError -> 0x0071 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0071 }
                r3[r4] = r1     // Catch:{ NoSuchFieldError -> 0x0071 }
            L_0x0071:
                int[] r1 = $SwitchMap$org$bouncycastle$crypto$engines$AsconEngine$AsconParameters     // Catch:{ NoSuchFieldError -> 0x007b }
                org.bouncycastle.crypto.engines.AsconEngine$AsconParameters r3 = org.bouncycastle.crypto.engines.AsconEngine.AsconParameters.ascon128a     // Catch:{ NoSuchFieldError -> 0x007b }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x007b }
                r1[r3] = r0     // Catch:{ NoSuchFieldError -> 0x007b }
            L_0x007b:
                int[] r0 = $SwitchMap$org$bouncycastle$crypto$engines$AsconEngine$AsconParameters     // Catch:{ NoSuchFieldError -> 0x0085 }
                org.bouncycastle.crypto.engines.AsconEngine$AsconParameters r1 = org.bouncycastle.crypto.engines.AsconEngine.AsconParameters.ascon128     // Catch:{ NoSuchFieldError -> 0x0085 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0085 }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0085 }
            L_0x0085:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.crypto.engines.AsconEngine.AnonymousClass1.<clinit>():void");
        }
    }

    public enum AsconParameters {
        ascon80pq,
        ascon128a,
        ascon128
    }

    private enum State {
        Uninitialized,
        EncInit,
        EncAad,
        EncData,
        EncFinal,
        DecInit,
        DecAad,
        DecData,
        DecFinal
    }

    public AsconEngine(AsconParameters asconParameters2) {
        String str;
        this.asconParameters = asconParameters2;
        int i = AnonymousClass1.$SwitchMap$org$bouncycastle$crypto$engines$AsconEngine$AsconParameters[asconParameters2.ordinal()];
        int i2 = 8;
        if (i == 1) {
            this.CRYPTO_KEYBYTES = 20;
            this.CRYPTO_ABYTES = 16;
            this.ASCON_AEAD_RATE = 8;
            this.ASCON_IV = -6899501409222262784L;
            str = "Ascon-80pq AEAD";
        } else if (i == 2) {
            this.CRYPTO_KEYBYTES = 16;
            this.CRYPTO_ABYTES = 16;
            this.ASCON_AEAD_RATE = 16;
            this.ASCON_IV = -9187330011336540160L;
            str = "Ascon-128a AEAD";
        } else if (i == 3) {
            this.CRYPTO_KEYBYTES = 16;
            this.CRYPTO_ABYTES = 16;
            this.ASCON_AEAD_RATE = 8;
            this.ASCON_IV = -9205344418435956736L;
            str = "Ascon-128 AEAD";
        } else {
            throw new IllegalArgumentException("invalid parameter setting for ASCON AEAD");
        }
        this.algorithmName = str;
        int i3 = this.ASCON_AEAD_RATE;
        this.nr = i3 == 8 ? 6 : i2;
        int i4 = i3 + this.CRYPTO_ABYTES;
        this.m_bufferSizeDecrypt = i4;
        this.m_buf = new byte[i4];
    }

    private void P(int i) {
        if (i >= 8) {
            if (i == 12) {
                ROUND(240);
                ROUND(225);
                ROUND(210);
                ROUND(195);
            }
            ROUND(180);
            ROUND(165);
        }
        ROUND(150);
        ROUND(135);
        ROUND(120);
        ROUND(105);
        ROUND(90);
        ROUND(75);
    }

    private long PAD(int i) {
        return 128 << (56 - (i << 3));
    }

    private void ROUND(long j) {
        long j2 = this.x0;
        long j3 = this.x1;
        long j4 = this.x2;
        long j5 = this.x3;
        long j6 = this.x4;
        long j7 = ((((j2 ^ j3) ^ j4) ^ j5) ^ j) ^ ((((j2 ^ j4) ^ j6) ^ j) & j3);
        long j8 = ((((j2 ^ j4) ^ j5) ^ j6) ^ j) ^ (((j3 ^ j4) ^ j) & (j3 ^ j5));
        long j9 = (((j3 ^ j4) ^ j6) ^ j) ^ (j5 & j6);
        long j10 = ((j4 ^ (j2 ^ j3)) ^ j) ^ ((j2 ^ -1) & (j5 ^ j6));
        long j11 = ((j2 ^ j6) & j3) ^ ((j5 ^ j3) ^ j6);
        this.x0 = (Longs.rotateRight(j7, 19) ^ j7) ^ Longs.rotateRight(j7, 28);
        long j12 = j8;
        this.x1 = Longs.rotateRight(j12, 61) ^ (Longs.rotateRight(j12, 39) ^ j12);
        this.x2 = ((Longs.rotateRight(j9, 1) ^ j9) ^ Longs.rotateRight(j9, 6)) ^ -1;
        this.x3 = (Longs.rotateRight(j10, 10) ^ j10) ^ Longs.rotateRight(j10, 17);
        this.x4 = Longs.rotateRight(j11, 41) ^ (Longs.rotateRight(j11, 7) ^ j11);
    }

    private void ascon_aeadinit() {
        long j = this.ASCON_IV;
        this.x0 = j;
        if (this.CRYPTO_KEYBYTES == 20) {
            this.x0 = j ^ this.K0;
        }
        this.x1 = this.K1;
        this.x2 = this.K2;
        this.x3 = this.N0;
        this.x4 = this.N1;
        P(12);
        if (this.CRYPTO_KEYBYTES == 20) {
            this.x2 ^= this.K0;
        }
        this.x3 ^= this.K1;
        this.x4 ^= this.K2;
    }

    private void checkAAD() {
        State state;
        int i = AnonymousClass1.$SwitchMap$org$bouncycastle$crypto$engines$AsconEngine$State[this.m_state.ordinal()];
        if (i == 1) {
            state = State.DecAad;
        } else if (i == 2) {
            state = State.EncAad;
        } else if (i != 3 && i != 4) {
            if (i != 5) {
                throw new IllegalStateException(getAlgorithmName() + " needs to be initialized");
            }
            throw new IllegalStateException(getAlgorithmName() + " cannot be reused for encryption");
        } else {
            return;
        }
        this.m_state = state;
    }

    private boolean checkData() {
        switch (AnonymousClass1.$SwitchMap$org$bouncycastle$crypto$engines$AsconEngine$State[this.m_state.ordinal()]) {
            case 1:
            case 3:
                finishAAD(State.DecData);
                return false;
            case 2:
            case 4:
                finishAAD(State.EncData);
                return true;
            case 5:
                throw new IllegalStateException(getAlgorithmName() + " cannot be reused for encryption");
            case 6:
                return false;
            case 7:
                return true;
            default:
                throw new IllegalStateException(getAlgorithmName() + " needs to be initialized");
        }
    }

    private void finishAAD(State state) {
        int i = AnonymousClass1.$SwitchMap$org$bouncycastle$crypto$engines$AsconEngine$State[this.m_state.ordinal()];
        if (i == 3 || i == 4) {
            byte[] bArr = this.m_buf;
            int i2 = this.m_bufPos;
            bArr[i2] = Byte.MIN_VALUE;
            if (i2 >= 8) {
                this.x0 ^= Pack.bigEndianToLong(bArr, 0);
                this.x1 = ((-1 << (56 - ((this.m_bufPos - 8) << 3))) & Pack.bigEndianToLong(this.m_buf, 8)) ^ this.x1;
            } else {
                this.x0 = ((-1 << (56 - (this.m_bufPos << 3))) & Pack.bigEndianToLong(bArr, 0)) ^ this.x0;
            }
            P(this.nr);
        }
        this.x4 ^= 1;
        this.m_bufPos = 0;
        this.m_state = state;
    }

    private void finishData(State state) {
        long j;
        long j2;
        int i = AnonymousClass1.$SwitchMap$org$bouncycastle$crypto$engines$AsconEngine$AsconParameters[this.asconParameters.ordinal()];
        if (i == 1) {
            long j3 = this.x1;
            long j4 = this.K1;
            this.x1 = j3 ^ ((this.K0 << 32) | (j4 >> 32));
            long j5 = this.x2;
            long j6 = j4 << 32;
            long j7 = this.K2;
            this.x2 = j5 ^ (j6 | (j7 >> 32));
            j2 = this.x3;
            j = j7 << 32;
        } else if (i == 2) {
            this.x2 ^= this.K1;
            j2 = this.x3;
            j = this.K2;
        } else if (i == 3) {
            this.x1 ^= this.K1;
            this.x2 ^= this.K2;
            P(12);
            this.x3 ^= this.K1;
            this.x4 ^= this.K2;
            this.m_state = state;
        } else {
            throw new IllegalStateException();
        }
        this.x3 = j2 ^ j;
        P(12);
        this.x3 ^= this.K1;
        this.x4 ^= this.K2;
        this.m_state = state;
    }

    private void processBufferAAD(byte[] bArr, int i) {
        this.x0 ^= Pack.bigEndianToLong(bArr, i);
        if (this.ASCON_AEAD_RATE == 16) {
            this.x1 = Pack.bigEndianToLong(bArr, i + 8) ^ this.x1;
        }
        P(this.nr);
    }

    private void processBufferDecrypt(byte[] bArr, int i, byte[] bArr2, int i2) {
        if (this.ASCON_AEAD_RATE + i2 <= bArr2.length) {
            long bigEndianToLong = Pack.bigEndianToLong(bArr, i);
            Pack.longToBigEndian(this.x0 ^ bigEndianToLong, bArr2, i2);
            this.x0 = bigEndianToLong;
            if (this.ASCON_AEAD_RATE == 16) {
                long bigEndianToLong2 = Pack.bigEndianToLong(bArr, i + 8);
                Pack.longToBigEndian(this.x1 ^ bigEndianToLong2, bArr2, i2 + 8);
                this.x1 = bigEndianToLong2;
            }
            P(this.nr);
            return;
        }
        throw new OutputLengthException("output buffer too short");
    }

    private void processBufferEncrypt(byte[] bArr, int i, byte[] bArr2, int i2) {
        if (this.ASCON_AEAD_RATE + i2 <= bArr2.length) {
            long bigEndianToLong = this.x0 ^ Pack.bigEndianToLong(bArr, i);
            this.x0 = bigEndianToLong;
            Pack.longToBigEndian(bigEndianToLong, bArr2, i2);
            if (this.ASCON_AEAD_RATE == 16) {
                long bigEndianToLong2 = Pack.bigEndianToLong(bArr, i + 8) ^ this.x1;
                this.x1 = bigEndianToLong2;
                Pack.longToBigEndian(bigEndianToLong2, bArr2, i2 + 8);
            }
            P(this.nr);
            return;
        }
        throw new OutputLengthException("output buffer too short");
    }

    private void processFinalDecrypt(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        if (i2 >= 8) {
            long bigEndianToLong = Pack.bigEndianToLong(bArr, i);
            long j = this.x0 ^ bigEndianToLong;
            this.x0 = j;
            Pack.longToBigEndian(j, bArr2, i3);
            this.x0 = bigEndianToLong;
            int i4 = i + 8;
            int i5 = i3 + 8;
            int i6 = i2 - 8;
            this.x1 ^= PAD(i6);
            if (i6 != 0) {
                long littleEndianToLong_High = Pack.littleEndianToLong_High(bArr, i4, i6);
                long j2 = this.x1 ^ littleEndianToLong_High;
                this.x1 = j2;
                Pack.longToLittleEndian_High(j2, bArr2, i5, i6);
                this.x1 = littleEndianToLong_High ^ (this.x1 & (-1 >>> (i6 << 3)));
            }
        } else {
            this.x0 ^= PAD(i2);
            if (i2 != 0) {
                long littleEndianToLong_High2 = Pack.littleEndianToLong_High(bArr, i, i2);
                long j3 = this.x0 ^ littleEndianToLong_High2;
                this.x0 = j3;
                Pack.longToLittleEndian_High(j3, bArr2, i3, i2);
                this.x0 = littleEndianToLong_High2 ^ (this.x0 & (-1 >>> (i2 << 3)));
            }
        }
        finishData(State.DecFinal);
    }

    private void processFinalEncrypt(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        long j;
        if (i2 >= 8) {
            long bigEndianToLong = this.x0 ^ Pack.bigEndianToLong(bArr, i);
            this.x0 = bigEndianToLong;
            Pack.longToBigEndian(bigEndianToLong, bArr2, i3);
            int i4 = i + 8;
            i3 += 8;
            i2 -= 8;
            long PAD = this.x1 ^ PAD(i2);
            this.x1 = PAD;
            if (i2 != 0) {
                j = Pack.littleEndianToLong_High(bArr, i4, i2) ^ PAD;
                this.x1 = j;
            }
            finishData(State.EncFinal);
        }
        long PAD2 = this.x0 ^ PAD(i2);
        this.x0 = PAD2;
        if (i2 != 0) {
            j = Pack.littleEndianToLong_High(bArr, i, i2) ^ PAD2;
            this.x0 = j;
        }
        finishData(State.EncFinal);
        Pack.longToLittleEndian_High(j, bArr2, i3, i2);
        finishData(State.EncFinal);
    }

    private void reset(boolean z) {
        if (z) {
            this.mac = null;
        }
        Arrays.clear(this.m_buf);
        this.m_bufPos = 0;
        switch (AnonymousClass1.$SwitchMap$org$bouncycastle$crypto$engines$AsconEngine$State[this.m_state.ordinal()]) {
            case 1:
            case 2:
                break;
            case 3:
            case 6:
            case 8:
                this.m_state = State.DecInit;
                break;
            case 4:
            case 5:
            case 7:
                this.m_state = State.EncFinal;
                return;
            default:
                throw new IllegalStateException(getAlgorithmName() + " needs to be initialized");
        }
        ascon_aeadinit();
        byte[] bArr = this.initialAssociatedText;
        if (bArr != null) {
            processAADBytes(bArr, 0, bArr.length);
        }
    }

    public int doFinal(byte[] bArr, int i) throws IllegalStateException, InvalidCipherTextException, DataLengthException {
        int i2;
        if (checkData()) {
            int i3 = this.m_bufPos;
            i2 = this.CRYPTO_ABYTES + i3;
            if (i + i2 <= bArr.length) {
                processFinalEncrypt(this.m_buf, 0, i3, bArr, i);
                byte[] bArr2 = new byte[this.CRYPTO_ABYTES];
                this.mac = bArr2;
                Pack.longToBigEndian(this.x3, bArr2, 0);
                Pack.longToBigEndian(this.x4, this.mac, 8);
                System.arraycopy(this.mac, 0, bArr, i + this.m_bufPos, this.CRYPTO_ABYTES);
                reset(false);
            } else {
                throw new OutputLengthException("output buffer too short");
            }
        } else {
            int i4 = this.m_bufPos;
            int i5 = this.CRYPTO_ABYTES;
            if (i4 >= i5) {
                i2 = i4 - i5;
                this.m_bufPos = i2;
                if (i + i2 <= bArr.length) {
                    processFinalDecrypt(this.m_buf, 0, i2, bArr, i);
                    this.x3 ^= Pack.bigEndianToLong(this.m_buf, this.m_bufPos);
                    long bigEndianToLong = this.x4 ^ Pack.bigEndianToLong(this.m_buf, this.m_bufPos + 8);
                    this.x4 = bigEndianToLong;
                    if ((bigEndianToLong | this.x3) == 0) {
                        reset(true);
                    } else {
                        throw new InvalidCipherTextException("mac check in " + getAlgorithmName() + " failed");
                    }
                } else {
                    throw new OutputLengthException("output buffer too short");
                }
            } else {
                throw new InvalidCipherTextException("data too short");
            }
        }
        return i2;
    }

    public String getAlgorithmName() {
        return this.algorithmName;
    }

    public String getAlgorithmVersion() {
        return "v1.2";
    }

    public int getIVBytesSize() {
        return this.CRYPTO_ABYTES;
    }

    public int getKeyBytesSize() {
        return this.CRYPTO_KEYBYTES;
    }

    public byte[] getMac() {
        return this.mac;
    }

    public int getOutputSize(int i) {
        int max = Math.max(0, i);
        int i2 = AnonymousClass1.$SwitchMap$org$bouncycastle$crypto$engines$AsconEngine$State[this.m_state.ordinal()];
        if (i2 == 1 || i2 == 3) {
            return Math.max(0, max - this.CRYPTO_ABYTES);
        }
        if (i2 != 5) {
            if (i2 != 6) {
                if (i2 != 7) {
                    if (i2 != 8) {
                        return max + this.CRYPTO_ABYTES;
                    }
                }
            }
            return Math.max(0, (max + this.m_bufPos) - this.CRYPTO_ABYTES);
        }
        return max + this.m_bufPos + this.CRYPTO_ABYTES;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0020, code lost:
        if (r1 != 8) goto L_0x0032;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int getUpdateOutputSize(int r4) {
        /*
            r3 = this;
            r0 = 0
            int r4 = java.lang.Math.max(r0, r4)
            int[] r1 = org.bouncycastle.crypto.engines.AsconEngine.AnonymousClass1.$SwitchMap$org$bouncycastle$crypto$engines$AsconEngine$State
            org.bouncycastle.crypto.engines.AsconEngine$State r2 = r3.m_state
            int r2 = r2.ordinal()
            r1 = r1[r2]
            r2 = 1
            if (r1 == r2) goto L_0x002b
            r2 = 3
            if (r1 == r2) goto L_0x002b
            r2 = 5
            if (r1 == r2) goto L_0x0027
            r2 = 6
            if (r1 == r2) goto L_0x0023
            r2 = 7
            if (r1 == r2) goto L_0x0027
            r2 = 8
            if (r1 == r2) goto L_0x0023
            goto L_0x0032
        L_0x0023:
            int r1 = r3.m_bufPos
            int r4 = r4 + r1
            goto L_0x002b
        L_0x0027:
            int r0 = r3.m_bufPos
            int r4 = r4 + r0
            goto L_0x0032
        L_0x002b:
            int r1 = r3.CRYPTO_ABYTES
            int r4 = r4 - r1
            int r4 = java.lang.Math.max(r0, r4)
        L_0x0032:
            int r0 = r3.ASCON_AEAD_RATE
            int r0 = r4 % r0
            int r4 = r4 - r0
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.crypto.engines.AsconEngine.getUpdateOutputSize(int):int");
    }

    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        byte[] bArr;
        KeyParameter keyParameter;
        long bigEndianToLong;
        if (cipherParameters instanceof AEADParameters) {
            AEADParameters aEADParameters = (AEADParameters) cipherParameters;
            keyParameter = aEADParameters.getKey();
            bArr = aEADParameters.getNonce();
            this.initialAssociatedText = aEADParameters.getAssociatedText();
            int macSize = aEADParameters.getMacSize();
            if (macSize != this.CRYPTO_ABYTES * 8) {
                throw new IllegalArgumentException("Invalid value for MAC size: " + macSize);
            }
        } else if (cipherParameters instanceof ParametersWithIV) {
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            keyParameter = (KeyParameter) parametersWithIV.getParameters();
            bArr = parametersWithIV.getIV();
            this.initialAssociatedText = null;
        } else {
            throw new IllegalArgumentException("invalid parameters passed to Ascon");
        }
        if (keyParameter == null) {
            throw new IllegalArgumentException("Ascon Init parameters must include a key");
        } else if (bArr == null || bArr.length != this.CRYPTO_ABYTES) {
            throw new IllegalArgumentException(this.asconParameters + " requires exactly " + this.CRYPTO_ABYTES + " bytes of IV");
        } else {
            byte[] key = keyParameter.getKey();
            if (key.length == this.CRYPTO_KEYBYTES) {
                CryptoServicesRegistrar.checkConstraints(new DefaultServiceProperties(getAlgorithmName(), 128, cipherParameters, Utils.getPurpose(z)));
                this.N0 = Pack.bigEndianToLong(bArr, 0);
                this.N1 = Pack.bigEndianToLong(bArr, 8);
                int i = this.CRYPTO_KEYBYTES;
                if (i == 16) {
                    this.K1 = Pack.bigEndianToLong(key, 0);
                    bigEndianToLong = Pack.bigEndianToLong(key, 8);
                } else if (i == 20) {
                    this.K0 = (long) Pack.bigEndianToInt(key, 0);
                    this.K1 = Pack.bigEndianToLong(key, 4);
                    bigEndianToLong = Pack.bigEndianToLong(key, 12);
                } else {
                    throw new IllegalStateException();
                }
                this.K2 = bigEndianToLong;
                this.m_state = z ? State.EncInit : State.DecInit;
                reset(true);
                return;
            }
            throw new IllegalArgumentException(this.asconParameters + " key must be " + this.CRYPTO_KEYBYTES + " bytes long");
        }
    }

    public void processAADByte(byte b) {
        checkAAD();
        byte[] bArr = this.m_buf;
        int i = this.m_bufPos;
        bArr[i] = b;
        int i2 = i + 1;
        this.m_bufPos = i2;
        if (i2 == this.ASCON_AEAD_RATE) {
            processBufferAAD(bArr, 0);
        }
    }

    public void processAADBytes(byte[] bArr, int i, int i2) {
        if (i + i2 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        } else if (i2 > 0) {
            checkAAD();
            int i3 = this.m_bufPos;
            if (i3 > 0) {
                int i4 = this.ASCON_AEAD_RATE - i3;
                if (i2 < i4) {
                    System.arraycopy(bArr, i, this.m_buf, i3, i2);
                    this.m_bufPos += i2;
                    return;
                }
                System.arraycopy(bArr, i, this.m_buf, i3, i4);
                i += i4;
                i2 -= i4;
                processBufferAAD(this.m_buf, 0);
            }
            while (i2 >= this.ASCON_AEAD_RATE) {
                processBufferAAD(bArr, i);
                int i5 = this.ASCON_AEAD_RATE;
                i += i5;
                i2 -= i5;
            }
            System.arraycopy(bArr, i, this.m_buf, 0, i2);
            this.m_bufPos = i2;
        }
    }

    public int processByte(byte b, byte[] bArr, int i) throws DataLengthException {
        return processBytes(new byte[]{b}, 0, 1, bArr, i);
    }

    public int processBytes(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws DataLengthException {
        int i4;
        if (i + i2 <= bArr.length) {
            if (checkData()) {
                int i5 = this.m_bufPos;
                if (i5 > 0) {
                    int i6 = this.ASCON_AEAD_RATE - i5;
                    if (i2 < i6) {
                        System.arraycopy(bArr, i, this.m_buf, i5, i2);
                    } else {
                        System.arraycopy(bArr, i, this.m_buf, i5, i6);
                        i += i6;
                        i2 -= i6;
                        processBufferEncrypt(this.m_buf, 0, bArr2, i3);
                        i4 = this.ASCON_AEAD_RATE;
                    }
                } else {
                    i4 = 0;
                }
                while (r9 >= this.ASCON_AEAD_RATE) {
                    processBufferEncrypt(bArr, r8, bArr2, i3 + i4);
                    int i7 = this.ASCON_AEAD_RATE;
                    i = r8 + i7;
                    i2 = r9 - i7;
                    i4 += i7;
                }
                System.arraycopy(bArr, r8, this.m_buf, 0, r9);
                this.m_bufPos = r9;
                return i4;
            }
            int i8 = this.m_bufferSizeDecrypt;
            int i9 = this.m_bufPos;
            int i10 = i8 - i9;
            if (i2 < i10) {
                System.arraycopy(bArr, i, this.m_buf, i9, i2);
            } else {
                int i11 = 0;
                do {
                    int i12 = this.m_bufPos;
                    int i13 = this.ASCON_AEAD_RATE;
                    if (i12 >= i13) {
                        processBufferDecrypt(this.m_buf, 0, bArr2, i3 + i11);
                        int i14 = this.m_bufPos;
                        int i15 = this.ASCON_AEAD_RATE;
                        int i16 = i14 - i15;
                        this.m_bufPos = i16;
                        byte[] bArr3 = this.m_buf;
                        System.arraycopy(bArr3, i15, bArr3, 0, i16);
                        int i17 = this.ASCON_AEAD_RATE;
                        i11 += i17;
                        i10 += i17;
                    } else {
                        int i18 = i13 - i12;
                        System.arraycopy(bArr, i, this.m_buf, i12, i18);
                        r8 = i + i18;
                        r9 = i2 - i18;
                        processBufferDecrypt(this.m_buf, 0, bArr2, i3 + i11);
                        int i19 = i11 + this.ASCON_AEAD_RATE;
                        while (r9 >= this.m_bufferSizeDecrypt) {
                            processBufferDecrypt(bArr, r8, bArr2, i3 + i4);
                            int i20 = this.ASCON_AEAD_RATE;
                            r8 += i20;
                            r9 -= i20;
                            i19 = i4 + i20;
                        }
                        System.arraycopy(bArr, r8, this.m_buf, 0, r9);
                        this.m_bufPos = r9;
                        return i4;
                    }
                } while (i2 >= i10);
                System.arraycopy(bArr, i, this.m_buf, this.m_bufPos, i2);
                this.m_bufPos += i2;
                return i11;
            }
            this.m_bufPos += i2;
            return 0;
        }
        throw new DataLengthException("input buffer too short");
    }

    public void reset() {
        reset(true);
    }
}
