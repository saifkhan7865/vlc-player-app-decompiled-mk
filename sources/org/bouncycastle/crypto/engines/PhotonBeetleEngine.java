package org.bouncycastle.crypto.engines;

import com.google.common.base.Ascii;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Array;
import org.bouncycastle.asn1.BERTags;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.constraints.DefaultServiceProperties;
import org.bouncycastle.crypto.modes.AEADCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;

public class PhotonBeetleEngine implements AEADCipher {
    private byte[] A;
    private final int CRYPTO_KEYBYTES = 16;
    private final int CRYPTO_NPUBBYTES = 16;
    private final int D = 8;
    private final int DSquare = 64;
    private final int Dq = 3;
    private final int Dr = 7;
    private byte[] K;
    private final int LAST_THREE_BITS_OFFSET;
    private final byte[][] MixColMatrix = {new byte[]{2, 4, 2, Ascii.VT, 2, 8, 5, 6}, new byte[]{Ascii.FF, 9, 8, 13, 7, 7, 5, 2}, new byte[]{4, 4, 13, 13, 9, 4, 13, 9}, new byte[]{1, 6, 5, 1, Ascii.FF, 13, Ascii.SI, Ascii.SO}, new byte[]{Ascii.SI, Ascii.FF, 9, 13, Ascii.SO, 5, Ascii.SO, 13}, new byte[]{9, Ascii.SO, 5, Ascii.SI, 4, Ascii.FF, 9, 6}, new byte[]{Ascii.FF, 2, 2, 10, 3, 1, 1, Ascii.SO}, new byte[]{Ascii.SI, 1, 13, 10, 5, 10, 2, 3}};
    private byte[] N;
    private final int RATE_INBYTES;
    private final int RATE_INBYTES_HALF;
    private final byte[][] RC = {new byte[]{1, 3, 7, Ascii.SO, 13, Ascii.VT, 6, Ascii.FF, 9, 2, 5, 10}, new byte[]{0, 2, 6, Ascii.SI, Ascii.FF, 10, 7, 13, 8, 3, 4, Ascii.VT}, new byte[]{2, 0, 4, 13, Ascii.SO, 8, 5, Ascii.SI, 10, 1, 6, 9}, new byte[]{6, 4, 0, 9, 10, Ascii.FF, 1, Ascii.VT, Ascii.SO, 5, 2, 13}, new byte[]{Ascii.SO, Ascii.FF, 8, 1, 2, 4, 9, 3, 6, 13, 10, 5}, new byte[]{Ascii.SI, 13, 9, 0, 3, 5, 8, 2, 7, Ascii.FF, Ascii.VT, 4}, new byte[]{13, Ascii.SI, Ascii.VT, 2, 1, 7, 10, 0, 5, Ascii.SO, 9, 6}, new byte[]{9, Ascii.VT, Ascii.SI, 6, 5, 3, Ascii.SO, 4, 1, 10, 13, 2}};
    private final int ROUND = 12;
    private final int S = 4;
    private final int STATE_INBYTES;
    private final int S_1 = 3;
    private byte[] T;
    private final int TAG_INBYTES = 16;
    private final ByteArrayOutputStream aadData = new ByteArrayOutputStream();
    private boolean encrypted;
    private boolean forEncryption;
    private boolean initialised;
    private boolean input_empty;
    private final ByteArrayOutputStream message = new ByteArrayOutputStream();
    private final byte[] sbox = {Ascii.FF, 5, 6, Ascii.VT, 9, 0, 10, 13, 3, Ascii.SO, Ascii.SI, 8, 4, 7, 1, 2};
    private byte[] state;
    private byte[][] state_2d;

    /* renamed from: org.bouncycastle.crypto.engines.PhotonBeetleEngine$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$bouncycastle$crypto$engines$PhotonBeetleEngine$PhotonBeetleParameters;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                org.bouncycastle.crypto.engines.PhotonBeetleEngine$PhotonBeetleParameters[] r0 = org.bouncycastle.crypto.engines.PhotonBeetleEngine.PhotonBeetleParameters.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$bouncycastle$crypto$engines$PhotonBeetleEngine$PhotonBeetleParameters = r0
                org.bouncycastle.crypto.engines.PhotonBeetleEngine$PhotonBeetleParameters r1 = org.bouncycastle.crypto.engines.PhotonBeetleEngine.PhotonBeetleParameters.pb32     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$bouncycastle$crypto$engines$PhotonBeetleEngine$PhotonBeetleParameters     // Catch:{ NoSuchFieldError -> 0x001d }
                org.bouncycastle.crypto.engines.PhotonBeetleEngine$PhotonBeetleParameters r1 = org.bouncycastle.crypto.engines.PhotonBeetleEngine.PhotonBeetleParameters.pb128     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.crypto.engines.PhotonBeetleEngine.AnonymousClass1.<clinit>():void");
        }
    }

    public enum PhotonBeetleParameters {
        pb32,
        pb128
    }

    public PhotonBeetleEngine(PhotonBeetleParameters photonBeetleParameters) {
        int i;
        int i2;
        int i3 = AnonymousClass1.$SwitchMap$org$bouncycastle$crypto$engines$PhotonBeetleEngine$PhotonBeetleParameters[photonBeetleParameters.ordinal()];
        if (i3 == 1) {
            i2 = 32;
            i = BERTags.FLAGS;
        } else if (i3 != 2) {
            i2 = 0;
            i = 0;
        } else {
            i2 = 128;
            i = 128;
        }
        int i4 = i2 + 7;
        this.RATE_INBYTES = i4 >>> 3;
        this.RATE_INBYTES_HALF = i4 >>> 4;
        int i5 = i2 + i;
        int i6 = (i5 + 7) >>> 3;
        this.STATE_INBYTES = i6;
        this.LAST_THREE_BITS_OFFSET = (i5 - ((i6 - 1) << 3)) - 3;
        this.initialised = false;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v6, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v8, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v9, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v10, resolved type: byte} */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=int, for r10v2, types: [byte] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void PHOTON_Permutation() {
        /*
            r15 = this;
            r0 = 0
            r1 = 0
        L_0x0002:
            r2 = 64
            r3 = 4
            if (r1 >= r2) goto L_0x0025
            byte[][] r2 = r15.state_2d
            int r4 = r1 >>> 3
            r2 = r2[r4]
            r4 = r1 & 7
            byte[] r5 = r15.state
            int r6 = r1 >> 1
            byte r5 = r5[r6]
            r5 = r5 & 255(0xff, float:3.57E-43)
            r6 = r1 & 1
            int r6 = r6 * 4
            int r3 = r5 >>> r6
            r3 = r3 & 15
            byte r3 = (byte) r3
            r2[r4] = r3
            int r1 = r1 + 1
            goto L_0x0002
        L_0x0025:
            r1 = 0
        L_0x0026:
            r4 = 12
            if (r1 >= r4) goto L_0x00cf
            r4 = 0
        L_0x002b:
            r5 = 8
            if (r4 >= r5) goto L_0x0042
            byte[][] r5 = r15.state_2d
            r5 = r5[r4]
            byte r6 = r5[r0]
            byte[][] r7 = r15.RC
            r7 = r7[r4]
            byte r7 = r7[r1]
            r6 = r6 ^ r7
            byte r6 = (byte) r6
            r5[r0] = r6
            int r4 = r4 + 1
            goto L_0x002b
        L_0x0042:
            r4 = 0
        L_0x0043:
            if (r4 >= r5) goto L_0x005a
            r6 = 0
        L_0x0046:
            if (r6 >= r5) goto L_0x0057
            byte[][] r7 = r15.state_2d
            r7 = r7[r4]
            byte[] r8 = r15.sbox
            byte r9 = r7[r6]
            byte r8 = r8[r9]
            r7[r6] = r8
            int r6 = r6 + 1
            goto L_0x0046
        L_0x0057:
            int r4 = r4 + 1
            goto L_0x0043
        L_0x005a:
            r4 = 1
            r6 = 1
        L_0x005c:
            if (r6 >= r5) goto L_0x007e
            byte[][] r7 = r15.state_2d
            r7 = r7[r6]
            byte[] r8 = r15.state
            java.lang.System.arraycopy(r7, r0, r8, r0, r5)
            byte[] r7 = r15.state
            byte[][] r8 = r15.state_2d
            r8 = r8[r6]
            int r9 = 8 - r6
            java.lang.System.arraycopy(r7, r6, r8, r0, r9)
            byte[] r7 = r15.state
            byte[][] r8 = r15.state_2d
            r8 = r8[r6]
            java.lang.System.arraycopy(r7, r0, r8, r9, r6)
            int r6 = r6 + 1
            goto L_0x005c
        L_0x007e:
            r6 = 0
        L_0x007f:
            if (r6 >= r5) goto L_0x00cb
            r7 = 0
        L_0x0082:
            if (r7 >= r5) goto L_0x00b8
            r8 = 0
            r9 = 0
        L_0x0086:
            if (r8 >= r5) goto L_0x00b1
            byte[][] r10 = r15.MixColMatrix
            r10 = r10[r7]
            byte r10 = r10[r8]
            byte[][] r11 = r15.state_2d
            r11 = r11[r8]
            byte r11 = r11[r6]
            r12 = 0
            r13 = 0
        L_0x0096:
            if (r12 >= r3) goto L_0x00aa
            int r14 = r11 >>> r12
            r14 = r14 & r4
            if (r14 == 0) goto L_0x009e
            r13 = r13 ^ r10
        L_0x009e:
            int r14 = r10 >>> 3
            r14 = r14 & r4
            int r10 = r10 << 1
            if (r14 == 0) goto L_0x00a7
            r10 = r10 ^ 3
        L_0x00a7:
            int r12 = r12 + 1
            goto L_0x0096
        L_0x00aa:
            r10 = r13 & 15
            r9 = r9 ^ r10
            byte r9 = (byte) r9
            int r8 = r8 + 1
            goto L_0x0086
        L_0x00b1:
            byte[] r8 = r15.state
            r8[r7] = r9
            int r7 = r7 + 1
            goto L_0x0082
        L_0x00b8:
            r7 = 0
        L_0x00b9:
            if (r7 >= r5) goto L_0x00c8
            byte[][] r8 = r15.state_2d
            r8 = r8[r7]
            byte[] r9 = r15.state
            byte r9 = r9[r7]
            r8[r6] = r9
            int r7 = r7 + 1
            goto L_0x00b9
        L_0x00c8:
            int r6 = r6 + 1
            goto L_0x007f
        L_0x00cb:
            int r1 = r1 + 1
            goto L_0x0026
        L_0x00cf:
            if (r0 >= r2) goto L_0x00f1
            byte[] r1 = r15.state
            int r4 = r0 >>> 1
            byte[][] r5 = r15.state_2d
            int r6 = r0 >>> 3
            r5 = r5[r6]
            r6 = r0 & 7
            byte r6 = r5[r6]
            r6 = r6 & 15
            int r7 = r0 + 1
            r7 = r7 & 7
            byte r5 = r5[r7]
            r5 = r5 & 15
            int r5 = r5 << r3
            r5 = r5 | r6
            byte r5 = (byte) r5
            r1[r4] = r5
            int r0 = r0 + 2
            goto L_0x00cf
        L_0x00f1:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.crypto.engines.PhotonBeetleEngine.PHOTON_Permutation():void");
    }

    private void XOR(byte[] bArr, int i, int i2) {
        int i3 = 0;
        while (i3 < i2) {
            byte[] bArr2 = this.state;
            bArr2[i3] = (byte) (bArr[i] ^ bArr2[i3]);
            i3++;
            i++;
        }
    }

    private void reset(boolean z) {
        if (z) {
            this.T = null;
        }
        this.input_empty = true;
        this.aadData.reset();
        this.message.reset();
        byte[] bArr = this.K;
        System.arraycopy(bArr, 0, this.state, 0, bArr.length);
        byte[] bArr2 = this.N;
        System.arraycopy(bArr2, 0, this.state, this.K.length, bArr2.length);
        this.encrypted = false;
    }

    private void rhoohr(byte[] bArr, int i, byte[] bArr2, int i2, int i3) {
        int i4;
        int i5 = 0;
        byte[] bArr3 = this.state_2d[0];
        int min = Math.min(i3, this.RATE_INBYTES_HALF);
        int i6 = 0;
        while (true) {
            i4 = this.RATE_INBYTES_HALF;
            if (i6 >= i4 - 1) {
                break;
            }
            byte[] bArr4 = this.state;
            int i7 = i6 + 1;
            bArr3[i6] = (byte) (((bArr4[i7] & 1) << 7) | ((bArr4[i6] & 255) >>> 1));
            i6 = i7;
        }
        byte[] bArr5 = this.state;
        bArr3[i4 - 1] = (byte) (((bArr5[i6] & 255) >>> 1) | ((bArr5[0] & 1) << 7));
        while (i5 < min) {
            bArr[i5 + i] = (byte) (bArr2[i5 + i2] ^ this.state[this.RATE_INBYTES_HALF + i5]);
            i5++;
        }
        while (i5 < i3) {
            bArr[i5 + i] = (byte) (bArr2[i5 + i2] ^ bArr3[i5 - this.RATE_INBYTES_HALF]);
            i5++;
        }
        if (this.forEncryption) {
            XOR(bArr2, i2, i3);
        } else {
            XOR(bArr, i2, i3);
        }
    }

    private byte select(boolean z, boolean z2, byte b, byte b2) {
        if (z && z2) {
            return 1;
        }
        if (z) {
            return 2;
        }
        return z2 ? b : b2;
    }

    public int doFinal(byte[] bArr, int i) throws IllegalStateException, InvalidCipherTextException {
        byte[] bArr2 = bArr;
        if (this.initialised) {
            int size = this.message.size();
            boolean z = this.forEncryption;
            int i2 = size - (z ? 0 : 16);
            if ((!z || i2 + 16 + i <= bArr2.length) && (z || i2 + i <= bArr2.length)) {
                byte[] byteArray = this.message.toByteArray();
                byte[] byteArray2 = this.aadData.toByteArray();
                this.A = byteArray2;
                int length = byteArray2.length;
                if (!(length == 0 && i2 == 0)) {
                    this.input_empty = false;
                }
                byte select = select(i2 != 0, length % this.RATE_INBYTES == 0, (byte) 3, (byte) 4);
                byte select2 = select(length != 0, i2 % this.RATE_INBYTES == 0, (byte) 5, (byte) 6);
                if (length != 0) {
                    int i3 = this.RATE_INBYTES;
                    int i4 = ((length + i3) - 1) / i3;
                    int i5 = 0;
                    while (true) {
                        int i6 = i4 - 1;
                        PHOTON_Permutation();
                        if (i5 >= i6) {
                            break;
                        }
                        byte[] bArr3 = this.A;
                        int i7 = this.RATE_INBYTES;
                        XOR(bArr3, i5 * i7, i7);
                        i5++;
                    }
                    int i8 = this.RATE_INBYTES;
                    int i9 = length - (i5 * i8);
                    XOR(this.A, i5 * i8, i9);
                    if (i9 < this.RATE_INBYTES) {
                        byte[] bArr4 = this.state;
                        bArr4[i9] = (byte) (bArr4[i9] ^ 1);
                    }
                    byte[] bArr5 = this.state;
                    int i10 = this.STATE_INBYTES - 1;
                    bArr5[i10] = (byte) ((select << this.LAST_THREE_BITS_OFFSET) ^ bArr5[i10]);
                }
                if (i2 != 0) {
                    int i11 = this.RATE_INBYTES;
                    int i12 = ((i2 + i11) - 1) / i11;
                    int i13 = 0;
                    while (true) {
                        int i14 = i12 - 1;
                        PHOTON_Permutation();
                        if (i13 >= i14) {
                            break;
                        }
                        int i15 = this.RATE_INBYTES;
                        rhoohr(bArr, i + (i13 * i15), byteArray, i13 * i15, i15);
                        i13++;
                    }
                    int i16 = this.RATE_INBYTES;
                    int i17 = i2 - (i13 * i16);
                    rhoohr(bArr, i + (i13 * i16), byteArray, i13 * i16, i17);
                    if (i17 < this.RATE_INBYTES) {
                        byte[] bArr6 = this.state;
                        bArr6[i17] = (byte) (bArr6[i17] ^ 1);
                    }
                    byte[] bArr7 = this.state;
                    int i18 = this.STATE_INBYTES - 1;
                    bArr7[i18] = (byte) (bArr7[i18] ^ (select2 << this.LAST_THREE_BITS_OFFSET));
                }
                int i19 = i + i2;
                if (this.input_empty) {
                    byte[] bArr8 = this.state;
                    int i20 = this.STATE_INBYTES - 1;
                    bArr8[i20] = (byte) (bArr8[i20] ^ (1 << this.LAST_THREE_BITS_OFFSET));
                }
                PHOTON_Permutation();
                byte[] bArr9 = new byte[16];
                this.T = bArr9;
                System.arraycopy(this.state, 0, bArr9, 0, 16);
                if (this.forEncryption) {
                    System.arraycopy(this.T, 0, bArr2, i19, 16);
                    i2 += 16;
                } else {
                    int i21 = 0;
                    while (i21 < 16) {
                        if (this.T[i21] == byteArray[i2 + i21]) {
                            i21++;
                        } else {
                            throw new IllegalArgumentException("Mac does not match");
                        }
                    }
                }
                reset(false);
                return i2;
            }
            throw new OutputLengthException("output buffer too short");
        }
        throw new IllegalArgumentException("Need call init function before encryption/decryption");
    }

    public String getAlgorithmName() {
        return "Photon-Beetle AEAD";
    }

    public int getBlockSize() {
        return this.RATE_INBYTES;
    }

    public int getIVBytesSize() {
        return 16;
    }

    public int getKeyBytesSize() {
        return 16;
    }

    public byte[] getMac() {
        return this.T;
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
            this.N = iv;
            if (iv == null || iv.length != 16) {
                throw new IllegalArgumentException("Photon-Beetle AEAD requires exactly 16 bytes of IV");
            } else if (parametersWithIV.getParameters() instanceof KeyParameter) {
                byte[] key = ((KeyParameter) parametersWithIV.getParameters()).getKey();
                this.K = key;
                if (key.length == 16) {
                    CryptoServicesRegistrar.checkConstraints(new DefaultServiceProperties(getAlgorithmName(), 128, cipherParameters, Utils.getPurpose(z)));
                    this.state = new byte[this.STATE_INBYTES];
                    int[] iArr = new int[2];
                    iArr[1] = 8;
                    iArr[0] = 8;
                    this.state_2d = (byte[][]) Array.newInstance(Byte.TYPE, iArr);
                    this.T = new byte[16];
                    this.initialised = true;
                    reset(false);
                    return;
                }
                throw new IllegalArgumentException("Photon-Beetle AEAD key must be 128 bits long");
            } else {
                throw new IllegalArgumentException("Photon-Beetle AEAD init parameters must include a key");
            }
        } else {
            throw new IllegalArgumentException("Photon-Beetle AEAD init parameters must include an IV");
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
        if (i + i2 <= bArr.length) {
            this.message.write(bArr, i, i2);
            return 0;
        }
        throw new DataLengthException("input buffer too short");
    }

    public void reset() {
        if (this.initialised) {
            reset(true);
            return;
        }
        throw new IllegalArgumentException("Need call init function before encryption/decryption");
    }
}
