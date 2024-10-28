package org.bouncycastle.crypto.engines;

import com.google.common.base.Ascii;
import com.google.common.primitives.SignedBytes;
import io.netty.handler.codec.http.HttpConstants;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import okio.Utf8;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.constraints.DefaultServiceProperties;
import org.bouncycastle.crypto.modes.AEADCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.crypto.signers.PSSSigner;

public class ElephantEngine implements AEADCipher {
    private final int BLOCK_SIZE;
    private final byte CRYPTO_ABYTES;
    private final byte CRYPTO_KEYBYTES = Ascii.DLE;
    private final byte CRYPTO_NPUBBYTES = Ascii.FF;
    private final int[] KeccakRhoOffsets = {0, 1, 6, 4, 3, 4, 4, 6, 7, 4, 3, 2, 3, 1, 7, 1, 5, 7, 5, 0, 2, 2, 5, 0, 6};
    private final byte[] KeccakRoundConstants = {1, -126, -118, 0, -117, 1, -127, 9, -118, -120, 9, 10, -117, -117, -119, 3, 2, Byte.MIN_VALUE};
    private final ByteArrayOutputStream aadData = new ByteArrayOutputStream();
    private byte[] ad;
    private int adOff;
    private int adlen;
    private final String algorithmName;
    private final byte[] buffer;
    private byte[] current_mask;
    private byte[] expanded_key;
    private boolean forEncryption;
    private boolean initialised;
    private byte[] inputMessage;
    private int inputOff;
    private byte lfsrIV;
    private State m_state = State.Uninitialized;
    private int nBits;
    private final int nRounds;
    private int nSBox;
    private int nb_its;
    private byte[] next_mask;
    private byte[] npub;
    private final byte[] outputMessage;
    private final ElephantParameters parameters;
    private byte[] previous_mask;
    private final byte[] previous_outputMessage;
    private final byte[] sBoxLayer = {-18, -19, -21, -32, -30, -31, -28, -17, -25, -22, -24, -27, -23, -20, -29, -26, -34, -35, -37, -48, -46, -47, -44, -33, -41, -38, -40, -43, -39, -36, -45, -42, -66, -67, -69, -80, -78, -79, -76, -65, -73, -70, -72, -75, -71, PSSSigner.TRAILER_IMPLICIT, -77, -74, Ascii.SO, 13, Ascii.VT, 0, 2, 1, 4, Ascii.SI, 7, 10, 8, 5, 9, Ascii.FF, 3, 6, 46, 45, 43, 32, HttpConstants.DOUBLE_QUOTE, 33, 36, 47, 39, 42, 40, 37, 41, HttpConstants.COMMA, 35, 38, Ascii.RS, Ascii.GS, Ascii.ESC, Ascii.DLE, Ascii.DC2, 17, Ascii.DC4, Ascii.US, Ascii.ETB, Ascii.SUB, Ascii.CAN, Ascii.NAK, Ascii.EM, Ascii.FS, 19, Ascii.SYN, 78, 77, 75, SignedBytes.MAX_POWER_OF_TWO, 66, 65, 68, 79, 71, 74, 72, 69, 73, 76, 67, 70, -2, -3, -5, -16, -14, -15, -12, -1, -9, -6, -8, -11, -7, -4, -13, -10, 126, 125, 123, 112, 114, 113, 116, Byte.MAX_VALUE, 119, 122, 120, 117, 121, 124, 115, 118, -82, -83, -85, -96, -94, -95, -92, -81, -89, -86, -88, -91, -87, -84, -93, -90, -114, -115, -117, Byte.MIN_VALUE, -126, -127, -124, -113, -121, -118, -120, -123, -119, -116, -125, -122, 94, 93, 91, 80, 82, 81, 84, 95, 87, 90, 88, 85, 89, 92, 83, 86, -98, -99, -101, -112, -110, -111, -108, -97, -105, -102, -104, -107, -103, -100, -109, -106, -50, -51, -53, -64, -62, -63, -60, -49, -57, -54, -56, -59, -55, -52, -61, -58, 62, 61, HttpConstants.SEMICOLON, 48, 50, 49, 52, Utf8.REPLACEMENT_BYTE, 55, HttpConstants.COLON, 56, 53, 57, 60, 51, 54, 110, 109, 107, 96, 98, 97, 100, 111, 103, 106, 104, 101, 105, 108, 99, 102};
    private byte[] tag;
    private final byte[] tag_buffer;

    /* renamed from: org.bouncycastle.crypto.engines.ElephantEngine$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$bouncycastle$crypto$engines$ElephantEngine$ElephantParameters;
        static final /* synthetic */ int[] $SwitchMap$org$bouncycastle$crypto$engines$ElephantEngine$State;

        /* JADX WARNING: Can't wrap try/catch for region: R(26:0|(2:1|2)|3|(2:5|6)|7|9|10|11|12|13|14|15|16|17|18|19|20|(2:21|22)|23|25|26|27|28|29|30|32) */
        /* JADX WARNING: Can't wrap try/catch for region: R(27:0|1|2|3|(2:5|6)|7|9|10|11|12|13|14|15|16|17|18|19|20|(2:21|22)|23|25|26|27|28|29|30|32) */
        /* JADX WARNING: Code restructure failed: missing block: B:33:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0033 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0060 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x007d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x0087 */
        static {
            /*
                org.bouncycastle.crypto.engines.ElephantEngine$State[] r0 = org.bouncycastle.crypto.engines.ElephantEngine.State.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$bouncycastle$crypto$engines$ElephantEngine$State = r0
                r1 = 1
                org.bouncycastle.crypto.engines.ElephantEngine$State r2 = org.bouncycastle.crypto.engines.ElephantEngine.State.EncInit     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = $SwitchMap$org$bouncycastle$crypto$engines$ElephantEngine$State     // Catch:{ NoSuchFieldError -> 0x001d }
                org.bouncycastle.crypto.engines.ElephantEngine$State r3 = org.bouncycastle.crypto.engines.ElephantEngine.State.DecInit     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                r2 = 3
                int[] r3 = $SwitchMap$org$bouncycastle$crypto$engines$ElephantEngine$State     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.bouncycastle.crypto.engines.ElephantEngine$State r4 = org.bouncycastle.crypto.engines.ElephantEngine.State.Uninitialized     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r3 = $SwitchMap$org$bouncycastle$crypto$engines$ElephantEngine$State     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.bouncycastle.crypto.engines.ElephantEngine$State r4 = org.bouncycastle.crypto.engines.ElephantEngine.State.DecFinal     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r5 = 4
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r3 = $SwitchMap$org$bouncycastle$crypto$engines$ElephantEngine$State     // Catch:{ NoSuchFieldError -> 0x003e }
                org.bouncycastle.crypto.engines.ElephantEngine$State r4 = org.bouncycastle.crypto.engines.ElephantEngine.State.EncFinal     // Catch:{ NoSuchFieldError -> 0x003e }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r5 = 5
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r3 = $SwitchMap$org$bouncycastle$crypto$engines$ElephantEngine$State     // Catch:{ NoSuchFieldError -> 0x0049 }
                org.bouncycastle.crypto.engines.ElephantEngine$State r4 = org.bouncycastle.crypto.engines.ElephantEngine.State.EncAad     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r5 = 6
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r3 = $SwitchMap$org$bouncycastle$crypto$engines$ElephantEngine$State     // Catch:{ NoSuchFieldError -> 0x0054 }
                org.bouncycastle.crypto.engines.ElephantEngine$State r4 = org.bouncycastle.crypto.engines.ElephantEngine.State.EncData     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r5 = 7
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r3 = $SwitchMap$org$bouncycastle$crypto$engines$ElephantEngine$State     // Catch:{ NoSuchFieldError -> 0x0060 }
                org.bouncycastle.crypto.engines.ElephantEngine$State r4 = org.bouncycastle.crypto.engines.ElephantEngine.State.DecData     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r5 = 8
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                int[] r3 = $SwitchMap$org$bouncycastle$crypto$engines$ElephantEngine$State     // Catch:{ NoSuchFieldError -> 0x006c }
                org.bouncycastle.crypto.engines.ElephantEngine$State r4 = org.bouncycastle.crypto.engines.ElephantEngine.State.DecAad     // Catch:{ NoSuchFieldError -> 0x006c }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x006c }
                r5 = 9
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x006c }
            L_0x006c:
                org.bouncycastle.crypto.engines.ElephantEngine$ElephantParameters[] r3 = org.bouncycastle.crypto.engines.ElephantEngine.ElephantParameters.values()
                int r3 = r3.length
                int[] r3 = new int[r3]
                $SwitchMap$org$bouncycastle$crypto$engines$ElephantEngine$ElephantParameters = r3
                org.bouncycastle.crypto.engines.ElephantEngine$ElephantParameters r4 = org.bouncycastle.crypto.engines.ElephantEngine.ElephantParameters.elephant160     // Catch:{ NoSuchFieldError -> 0x007d }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x007d }
                r3[r4] = r1     // Catch:{ NoSuchFieldError -> 0x007d }
            L_0x007d:
                int[] r1 = $SwitchMap$org$bouncycastle$crypto$engines$ElephantEngine$ElephantParameters     // Catch:{ NoSuchFieldError -> 0x0087 }
                org.bouncycastle.crypto.engines.ElephantEngine$ElephantParameters r3 = org.bouncycastle.crypto.engines.ElephantEngine.ElephantParameters.elephant176     // Catch:{ NoSuchFieldError -> 0x0087 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0087 }
                r1[r3] = r0     // Catch:{ NoSuchFieldError -> 0x0087 }
            L_0x0087:
                int[] r0 = $SwitchMap$org$bouncycastle$crypto$engines$ElephantEngine$ElephantParameters     // Catch:{ NoSuchFieldError -> 0x0091 }
                org.bouncycastle.crypto.engines.ElephantEngine$ElephantParameters r1 = org.bouncycastle.crypto.engines.ElephantEngine.ElephantParameters.elephant200     // Catch:{ NoSuchFieldError -> 0x0091 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0091 }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0091 }
            L_0x0091:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.crypto.engines.ElephantEngine.AnonymousClass1.<clinit>():void");
        }
    }

    public enum ElephantParameters {
        elephant160,
        elephant176,
        elephant200
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

    public ElephantEngine(ElephantParameters elephantParameters) {
        String str;
        int i = AnonymousClass1.$SwitchMap$org$bouncycastle$crypto$engines$ElephantEngine$ElephantParameters[elephantParameters.ordinal()];
        if (i == 1) {
            this.BLOCK_SIZE = 20;
            this.nBits = 160;
            this.nSBox = 20;
            this.nRounds = 80;
            this.lfsrIV = 117;
            this.CRYPTO_ABYTES = 8;
            str = "Elephant 160 AEAD";
        } else if (i == 2) {
            this.BLOCK_SIZE = 22;
            this.nBits = 176;
            this.nSBox = 22;
            this.nRounds = 90;
            this.lfsrIV = 69;
            this.CRYPTO_ABYTES = 8;
            str = "Elephant 176 AEAD";
        } else if (i == 3) {
            this.BLOCK_SIZE = 25;
            this.nRounds = 18;
            this.CRYPTO_ABYTES = Ascii.DLE;
            str = "Elephant 200 AEAD";
        } else {
            throw new IllegalArgumentException("Invalid parameter settings for Elephant");
        }
        this.algorithmName = str;
        this.parameters = elephantParameters;
        int i2 = this.BLOCK_SIZE;
        this.tag_buffer = new byte[i2];
        this.previous_mask = new byte[i2];
        this.current_mask = new byte[i2];
        this.next_mask = new byte[i2];
        this.buffer = new byte[i2];
        this.previous_outputMessage = new byte[i2];
        this.outputMessage = new byte[i2];
        this.initialised = false;
        reset(false);
    }

    private void KeccakP200Round(byte[] bArr, int i) {
        byte[] bArr2 = new byte[25];
        for (int i2 = 0; i2 < 5; i2++) {
            for (int i3 = 0; i3 < 5; i3++) {
                bArr2[i2] = (byte) (bArr2[i2] ^ bArr[index(i2, i3)]);
            }
        }
        int i4 = 0;
        while (i4 < 5) {
            int i5 = i4 + 1;
            bArr2[i4 + 5] = (byte) (bArr2[(i4 + 4) % 5] ^ ROL8(bArr2[i5 % 5], 1));
            i4 = i5;
        }
        for (int i6 = 0; i6 < 5; i6++) {
            for (int i7 = 0; i7 < 5; i7++) {
                int index = index(i6, i7);
                bArr[index] = (byte) (bArr[index] ^ bArr2[i6 + 5]);
            }
        }
        for (int i8 = 0; i8 < 5; i8++) {
            for (int i9 = 0; i9 < 5; i9++) {
                bArr2[index(i8, i9)] = ROL8(bArr[index(i8, i9)], this.KeccakRhoOffsets[index(i8, i9)]);
            }
        }
        for (int i10 = 0; i10 < 5; i10++) {
            for (int i11 = 0; i11 < 5; i11++) {
                bArr[index(i11, ((i10 * 2) + (i11 * 3)) % 5)] = bArr2[index(i10, i11)];
            }
        }
        for (int i12 = 0; i12 < 5; i12++) {
            int i13 = 0;
            while (i13 < 5) {
                int i14 = i13 + 1;
                bArr2[i13] = (byte) (bArr[index(i13, i12)] ^ ((bArr[index(i14 % 5, i12)] ^ -1) & bArr[index((i13 + 2) % 5, i12)]));
                i13 = i14;
            }
            for (int i15 = 0; i15 < 5; i15++) {
                bArr[index(i15, i12)] = bArr2[i15];
            }
        }
        bArr[0] = (byte) (this.KeccakRoundConstants[i] ^ bArr[0]);
    }

    private byte ROL8(byte b, int i) {
        if (i != 0) {
            byte b2 = b & 255;
            b = (b2 >>> (8 - i)) ^ (b2 << i);
        }
        return (byte) b;
    }

    private void checkAad() {
        int i = AnonymousClass1.$SwitchMap$org$bouncycastle$crypto$engines$ElephantEngine$State[this.m_state.ordinal()];
        if (i == 5) {
            throw new IllegalArgumentException(this.algorithmName + " cannot be reused for encryption");
        } else if (i == 7) {
            throw new IllegalArgumentException(this.algorithmName + " cannot process AAD when the length of the ciphertext to be processed exceeds the a block size");
        } else if (i == 8) {
            throw new IllegalArgumentException(this.algorithmName + " cannot process AAD when the length of the plaintext to be processed exceeds the a block size");
        }
    }

    private void get_c_block(byte[] bArr, byte[] bArr2, int i, int i2, int i3) {
        int i4 = this.BLOCK_SIZE;
        int i5 = i3 * i4;
        if (i5 == i2) {
            Arrays.fill(bArr, 0, i4, (byte) 0);
            bArr[0] = 1;
            return;
        }
        int i6 = i2 - i5;
        if (i4 <= i6) {
            System.arraycopy(bArr2, i, bArr, 0, i4);
            return;
        }
        if (i6 > 0) {
            System.arraycopy(bArr2, i, bArr, 0, i6);
        }
        Arrays.fill(bArr, i6, this.BLOCK_SIZE, (byte) 0);
        bArr[i6] = 1;
    }

    private int index(int i, int i2) {
        return i + (i2 * 5);
    }

    private void lfsr_step(byte[] bArr, byte[] bArr2) {
        int i = AnonymousClass1.$SwitchMap$org$bouncycastle$crypto$engines$ElephantEngine$ElephantParameters[this.parameters.ordinal()];
        if (i == 1) {
            byte b = bArr2[0];
            int i2 = (bArr2[3] & 255) << 7;
            int i3 = (bArr2[13] & 255) >>> 7;
            bArr[this.BLOCK_SIZE - 1] = (byte) (i3 ^ (i2 ^ (((b & 255) >>> 5) | ((b & 255) << 3))));
        } else if (i == 2) {
            bArr[this.BLOCK_SIZE - 1] = (byte) ((rotl(bArr2[0]) ^ ((bArr2[3] & 255) << 7)) ^ ((bArr2[19] & 255) >>> 7));
        } else if (i == 3) {
            bArr[this.BLOCK_SIZE - 1] = (byte) ((bArr2[13] << 1) ^ (rotl(bArr2[0]) ^ rotl(bArr2[2])));
        }
        System.arraycopy(bArr2, 1, bArr, 0, this.BLOCK_SIZE - 1);
    }

    private void permutation(byte[] bArr) {
        int i;
        int i2 = AnonymousClass1.$SwitchMap$org$bouncycastle$crypto$engines$ElephantEngine$ElephantParameters[this.parameters.ordinal()];
        if (i2 == 1 || i2 == 2) {
            byte b = this.lfsrIV;
            byte[] bArr2 = new byte[this.nSBox];
            for (int i3 = 0; i3 < this.nRounds; i3++) {
                bArr[0] = (byte) (bArr[0] ^ b);
                int i4 = this.nSBox - 1;
                byte b2 = b & 32;
                byte b3 = b & SignedBytes.MAX_POWER_OF_TWO;
                bArr[i4] = (byte) (bArr[i4] ^ ((byte) (((((((((b & 1) << 7) | ((b & 2) << 5)) | ((b & 4) << 3)) | ((b & 8) << 1)) | ((b & Ascii.DLE) >>> 1)) | (b2 >>> 3)) | (b3 >>> 5)) | ((b & 128) >>> 7))));
                b = (byte) (((b << 1) | ((b3 >>> 6) ^ (b2 >>> 5))) & 127);
                for (int i5 = 0; i5 < this.nSBox; i5++) {
                    bArr[i5] = this.sBoxLayer[bArr[i5] & 255];
                }
                Arrays.fill(bArr2, (byte) 0);
                int i6 = 0;
                while (true) {
                    i = this.nSBox;
                    if (i6 >= i) {
                        break;
                    }
                    for (int i7 = 0; i7 < 8; i7++) {
                        int i8 = (i6 << 3) + i7;
                        int i9 = this.nBits;
                        if (i8 != i9 - 1) {
                            i8 = ((i8 * i9) >> 2) % (i9 - 1);
                        }
                        int i10 = i8 >>> 3;
                        bArr2[i10] = (byte) (((((bArr[i6] & 255) >>> i7) & 1) << (i8 & 7)) ^ bArr2[i10]);
                    }
                    i6++;
                }
                System.arraycopy(bArr2, 0, bArr, 0, i);
            }
        } else if (i2 == 3) {
            for (int i11 = 0; i11 < this.nRounds; i11++) {
                KeccakP200Round(bArr, i11);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x00b9  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x00c4  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void processAADBytes(byte[] r8) {
        /*
            r7 = this;
            r7.checkAad()
            int r0 = r7.adOff
            r1 = -1
            r2 = 0
            if (r0 != r1) goto L_0x001b
            java.io.ByteArrayOutputStream r0 = r7.aadData
            int r0 = r0.size()
            r7.adlen = r0
            java.io.ByteArrayOutputStream r0 = r7.aadData
            byte[] r0 = r0.toByteArray()
            r7.ad = r0
            r7.adOff = r2
        L_0x001b:
            int[] r0 = org.bouncycastle.crypto.engines.ElephantEngine.AnonymousClass1.$SwitchMap$org$bouncycastle$crypto$engines$ElephantEngine$State
            org.bouncycastle.crypto.engines.ElephantEngine$State r1 = r7.m_state
            int r1 = r1.ordinal()
            r0 = r0[r1]
            r1 = 1
            r3 = 12
            if (r0 == r1) goto L_0x009d
            r4 = 2
            if (r0 == r4) goto L_0x008c
            switch(r0) {
                case 5: goto L_0x0073;
                case 6: goto L_0x0063;
                case 7: goto L_0x004a;
                case 8: goto L_0x0031;
                case 9: goto L_0x0063;
                default: goto L_0x0030;
            }
        L_0x0030:
            goto L_0x0071
        L_0x0031:
            java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = r7.algorithmName
            r0.append(r1)
            java.lang.String r1 = " cannot process AAD when the length of the plaintext to be processed exceeds the a block size"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r8.<init>(r0)
            throw r8
        L_0x004a:
            java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = r7.algorithmName
            r0.append(r1)
            java.lang.String r1 = " cannot process AAD when the length of the ciphertext to be processed exceeds the a block size"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r8.<init>(r0)
            throw r8
        L_0x0063:
            int r0 = r7.adOff
            int r3 = r7.adlen
            if (r0 != r3) goto L_0x0071
            int r0 = r7.BLOCK_SIZE
            java.util.Arrays.fill(r8, r2, r0, r2)
            r8[r2] = r1
            return
        L_0x0071:
            r3 = 0
            goto L_0x00af
        L_0x0073:
            java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = r7.algorithmName
            r0.append(r1)
            java.lang.String r1 = " cannot be reused for encryption"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r8.<init>(r0)
            throw r8
        L_0x008c:
            byte[] r0 = r7.expanded_key
            byte[] r4 = r7.current_mask
            int r5 = r7.BLOCK_SIZE
            java.lang.System.arraycopy(r0, r2, r4, r2, r5)
            byte[] r0 = r7.npub
            java.lang.System.arraycopy(r0, r2, r8, r2, r3)
            org.bouncycastle.crypto.engines.ElephantEngine$State r0 = org.bouncycastle.crypto.engines.ElephantEngine.State.DecAad
            goto L_0x00ad
        L_0x009d:
            byte[] r0 = r7.expanded_key
            byte[] r4 = r7.current_mask
            int r5 = r7.BLOCK_SIZE
            java.lang.System.arraycopy(r0, r2, r4, r2, r5)
            byte[] r0 = r7.npub
            java.lang.System.arraycopy(r0, r2, r8, r2, r3)
            org.bouncycastle.crypto.engines.ElephantEngine$State r0 = org.bouncycastle.crypto.engines.ElephantEngine.State.EncAad
        L_0x00ad:
            r7.m_state = r0
        L_0x00af:
            int r0 = r7.BLOCK_SIZE
            int r0 = r0 - r3
            int r4 = r7.adlen
            int r5 = r7.adOff
            int r4 = r4 - r5
            if (r0 > r4) goto L_0x00c4
            byte[] r1 = r7.ad
            java.lang.System.arraycopy(r1, r5, r8, r3, r0)
            int r8 = r7.adOff
            int r8 = r8 + r0
            r7.adOff = r8
            goto L_0x00f0
        L_0x00c4:
            if (r4 <= 0) goto L_0x00d0
            byte[] r6 = r7.ad
            java.lang.System.arraycopy(r6, r5, r8, r3, r4)
            int r5 = r7.adOff
            int r5 = r5 + r4
            r7.adOff = r5
        L_0x00d0:
            int r4 = r4 + r3
            int r3 = r3 + r0
            java.util.Arrays.fill(r8, r4, r3, r2)
            r8[r4] = r1
            int[] r8 = org.bouncycastle.crypto.engines.ElephantEngine.AnonymousClass1.$SwitchMap$org$bouncycastle$crypto$engines$ElephantEngine$State
            org.bouncycastle.crypto.engines.ElephantEngine$State r0 = r7.m_state
            int r0 = r0.ordinal()
            r8 = r8[r0]
            r0 = 6
            if (r8 == r0) goto L_0x00ec
            r0 = 9
            if (r8 == r0) goto L_0x00e9
            goto L_0x00f0
        L_0x00e9:
            org.bouncycastle.crypto.engines.ElephantEngine$State r8 = org.bouncycastle.crypto.engines.ElephantEngine.State.DecData
            goto L_0x00ee
        L_0x00ec:
            org.bouncycastle.crypto.engines.ElephantEngine$State r8 = org.bouncycastle.crypto.engines.ElephantEngine.State.EncData
        L_0x00ee:
            r7.m_state = r8
        L_0x00f0:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.crypto.engines.ElephantEngine.processAADBytes(byte[]):void");
    }

    private int processBytes(byte[] bArr, byte[] bArr2, int i, int i2, int i3, int i4, int i5, int i6) {
        byte[] bArr3 = bArr;
        int i7 = i3;
        int i8 = i2;
        int i9 = this.nb_its;
        int i10 = 0;
        while (i9 < i8) {
            lfsr_step(this.next_mask, this.current_mask);
            if (i9 < i7) {
                System.arraycopy(this.npub, 0, this.buffer, 0, 12);
                Arrays.fill(this.buffer, 12, this.BLOCK_SIZE, (byte) 0);
                xor_block(this.buffer, this.current_mask, 0, this.BLOCK_SIZE);
                xor_block(this.buffer, this.next_mask, 0, this.BLOCK_SIZE);
                permutation(this.buffer);
                xor_block(this.buffer, this.current_mask, 0, this.BLOCK_SIZE);
                xor_block(this.buffer, this.next_mask, 0, this.BLOCK_SIZE);
                int i11 = i9 == i7 + -1 ? i5 - (this.BLOCK_SIZE * i9) : this.BLOCK_SIZE;
                xor_block(this.buffer, bArr3, 0, i11);
                System.arraycopy(this.buffer, 0, bArr2, i, i11);
                if (this.forEncryption) {
                    System.arraycopy(this.buffer, 0, this.outputMessage, 0, i11);
                } else {
                    System.arraycopy(bArr3, 0, this.outputMessage, 0, i11);
                }
                i10 += i11;
            } else {
                byte[] bArr4 = bArr2;
                int i12 = i;
            }
            int i13 = i10;
            int i14 = i4;
            if (i9 > 0 && i9 <= i14) {
                get_c_block(this.buffer, this.previous_outputMessage, 0, i5, i9 - 1);
                xor_block(this.buffer, this.previous_mask, 0, this.BLOCK_SIZE);
                xor_block(this.buffer, this.next_mask, 0, this.BLOCK_SIZE);
                permutation(this.buffer);
                xor_block(this.buffer, this.previous_mask, 0, this.BLOCK_SIZE);
                xor_block(this.buffer, this.next_mask, 0, this.BLOCK_SIZE);
                xor_block(this.tag_buffer, this.buffer, 0, this.BLOCK_SIZE);
            }
            i9++;
            if (i9 < i6) {
                processAADBytes(this.buffer);
                xor_block(this.buffer, this.next_mask, 0, this.BLOCK_SIZE);
                permutation(this.buffer);
                xor_block(this.buffer, this.next_mask, 0, this.BLOCK_SIZE);
                xor_block(this.tag_buffer, this.buffer, 0, this.BLOCK_SIZE);
            }
            byte[] bArr5 = this.previous_mask;
            this.previous_mask = this.current_mask;
            this.current_mask = this.next_mask;
            this.next_mask = bArr5;
            System.arraycopy(this.outputMessage, 0, this.previous_outputMessage, 0, this.BLOCK_SIZE);
            i10 = i13;
        }
        return i10;
    }

    private void reset(boolean z) {
        if (z) {
            this.tag = null;
        }
        this.aadData.reset();
        Arrays.fill(this.tag_buffer, (byte) 0);
        this.inputOff = 0;
        this.nb_its = 0;
        this.adOff = -1;
    }

    private byte rotl(byte b) {
        byte b2 = b & 255;
        return (byte) ((b2 >>> 7) | (b2 << 1));
    }

    private void xor_block(byte[] bArr, byte[] bArr2, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            bArr[i3] = (byte) (bArr[i3] ^ bArr2[i3 + i]);
        }
    }

    public int doFinal(byte[] bArr, int i) throws IllegalStateException, InvalidCipherTextException {
        if (this.initialised) {
            int i2 = this.inputOff;
            boolean z = this.forEncryption;
            if ((!z || i2 + i + this.CRYPTO_ABYTES <= bArr.length) && (z || (i2 + i) - this.CRYPTO_ABYTES <= bArr.length)) {
                byte[] byteArray = this.aadData.toByteArray();
                int i3 = AnonymousClass1.$SwitchMap$org$bouncycastle$crypto$engines$ElephantEngine$State[this.m_state.ordinal()];
                if (i3 == 1 || i3 == 2) {
                    processAADBytes(this.tag_buffer);
                }
                int i4 = this.nb_its;
                int i5 = this.BLOCK_SIZE;
                int i6 = (i2 + (i4 * i5)) - (this.forEncryption ? 0 : this.CRYPTO_ABYTES);
                int length = byteArray.length;
                int i7 = i6 / i5;
                int i8 = i7 + 1;
                int i9 = (length + 12) / i5;
                int processBytes = processBytes(this.inputMessage, bArr, i, Math.max(i7 + 2, i9), i6 % i5 != 0 ? i8 : i7, i8, i6, i9 + 1) + i;
                this.tag = new byte[this.CRYPTO_ABYTES];
                xor_block(this.tag_buffer, this.expanded_key, 0, this.BLOCK_SIZE);
                permutation(this.tag_buffer);
                xor_block(this.tag_buffer, this.expanded_key, 0, this.BLOCK_SIZE);
                if (this.forEncryption) {
                    System.arraycopy(this.tag_buffer, 0, this.tag, 0, this.CRYPTO_ABYTES);
                    byte[] bArr2 = this.tag;
                    System.arraycopy(bArr2, 0, bArr, processBytes, bArr2.length);
                    i6 += this.CRYPTO_ABYTES;
                } else {
                    this.inputOff -= this.CRYPTO_ABYTES;
                    int i10 = 0;
                    while (i10 < this.CRYPTO_ABYTES) {
                        if (this.tag_buffer[i10] == this.inputMessage[this.inputOff + i10]) {
                            i10++;
                        } else {
                            throw new IllegalArgumentException("Mac does not match");
                        }
                    }
                }
                reset(false);
                return i6;
            }
            throw new OutputLengthException("output buffer is too short");
        }
        throw new IllegalArgumentException(this.algorithmName + " needs call init function before doFinal");
    }

    public String getAlgorithmName() {
        return this.algorithmName;
    }

    public int getBlockSize() {
        return this.CRYPTO_ABYTES;
    }

    public int getIVBytesSize() {
        return 12;
    }

    public int getKeyBytesSize() {
        return 16;
    }

    public byte[] getMac() {
        return this.tag;
    }

    public int getOutputSize(int i) {
        int i2 = AnonymousClass1.$SwitchMap$org$bouncycastle$crypto$engines$ElephantEngine$State[this.m_state.ordinal()];
        if (i2 != 1) {
            if (i2 == 3) {
                throw new IllegalArgumentException(this.algorithmName + " needs call init function before getUpdateOutputSize");
            } else if (i2 == 4 || i2 == 5) {
                return 0;
            } else {
                if (!(i2 == 6 || i2 == 7)) {
                    return Math.max(0, i - this.CRYPTO_ABYTES);
                }
            }
        }
        return i + this.CRYPTO_ABYTES;
    }

    public int getUpdateOutputSize(int i) {
        int i2 = AnonymousClass1.$SwitchMap$org$bouncycastle$crypto$engines$ElephantEngine$State[this.m_state.ordinal()];
        if (i2 != 1) {
            if (i2 == 3) {
                throw new IllegalArgumentException(this.algorithmName + " needs call init function before getUpdateOutputSize");
            } else if (i2 == 4 || i2 == 5) {
                return 0;
            } else {
                if (!(i2 == 6 || i2 == 7)) {
                    return Math.max(0, (i + this.inputOff) - this.CRYPTO_ABYTES);
                }
            }
        }
        return this.inputOff + i + this.CRYPTO_ABYTES;
    }

    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        this.forEncryption = z;
        if (cipherParameters instanceof ParametersWithIV) {
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            byte[] iv = parametersWithIV.getIV();
            this.npub = iv;
            if (iv == null || iv.length != 12) {
                throw new IllegalArgumentException(this.algorithmName + " requires exactly 12 bytes of IV");
            } else if (parametersWithIV.getParameters() instanceof KeyParameter) {
                byte[] key = ((KeyParameter) parametersWithIV.getParameters()).getKey();
                if (key.length == 16) {
                    byte[] bArr = new byte[this.BLOCK_SIZE];
                    this.expanded_key = bArr;
                    System.arraycopy(key, 0, bArr, 0, 16);
                    permutation(this.expanded_key);
                    CryptoServicesRegistrar.checkConstraints(new DefaultServiceProperties(getAlgorithmName(), 128, cipherParameters, Utils.getPurpose(z)));
                    this.initialised = true;
                    this.m_state = z ? State.EncInit : State.DecInit;
                    this.inputMessage = new byte[(this.BLOCK_SIZE + (z ? 0 : this.CRYPTO_ABYTES))];
                    reset(false);
                    return;
                }
                throw new IllegalArgumentException(this.algorithmName + " key must be 128 bits long");
            } else {
                throw new IllegalArgumentException(this.algorithmName + " init parameters must include a key");
            }
        } else {
            throw new IllegalArgumentException(this.algorithmName + " init parameters must include an IV");
        }
    }

    public void processAADByte(byte b) {
        this.aadData.write(b);
    }

    public void processAADBytes(byte[] bArr, int i, int i2) {
        if (i + i2 <= bArr.length) {
            this.aadData.write(bArr, i, i2);
            return;
        }
        throw new DataLengthException("input buffer too short");
    }

    public int processByte(byte b, byte[] bArr, int i) throws DataLengthException {
        return processBytes(new byte[]{b}, 0, 1, bArr, i);
    }

    public int processBytes(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws DataLengthException {
        byte[] bArr3 = bArr;
        int i4 = i;
        int i5 = i2;
        if (i4 + i5 <= bArr3.length) {
            byte[] byteArray = this.aadData.toByteArray();
            int i6 = this.inputOff;
            if ((i6 + i5) - (this.forEncryption ? 0 : this.CRYPTO_ABYTES) >= this.BLOCK_SIZE) {
                int i7 = AnonymousClass1.$SwitchMap$org$bouncycastle$crypto$engines$ElephantEngine$State[this.m_state.ordinal()];
                if (i7 == 1 || i7 == 2) {
                    processAADBytes(this.tag_buffer);
                }
                int i8 = (this.inputOff + i5) - (this.forEncryption ? 0 : this.CRYPTO_ABYTES);
                int length = byteArray.length;
                int i9 = this.BLOCK_SIZE;
                int i10 = i8 / i9;
                int i11 = i8 % i9 != 0 ? i10 : i10 - 1;
                int i12 = ((length + 12) / i9) + 1;
                int max = Math.max(i10, 1) * this.BLOCK_SIZE;
                byte[] bArr4 = new byte[max];
                System.arraycopy(this.inputMessage, 0, bArr4, 0, this.inputOff);
                int i13 = this.inputOff;
                int i14 = max - i13;
                System.arraycopy(bArr3, i4, bArr4, i13, max - i13);
                processBytes(bArr4, bArr2, i3, i10, i11 + 1, i10, i8, i12);
                int i15 = i5 - i14;
                this.inputOff = i15;
                System.arraycopy(bArr3, i4 + i14, this.inputMessage, 0, i15);
                this.nb_its += i10;
                return i10 * this.BLOCK_SIZE;
            }
            System.arraycopy(bArr3, i4, this.inputMessage, i6, i5);
            this.inputOff += i5;
            return 0;
        }
        throw new DataLengthException("input buffer too short");
    }

    public void reset() {
        reset(true);
    }
}
