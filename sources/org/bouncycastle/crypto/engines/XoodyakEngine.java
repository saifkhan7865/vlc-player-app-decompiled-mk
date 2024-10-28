package org.bouncycastle.crypto.engines;

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
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;

public class XoodyakEngine implements AEADCipher {
    private byte[] K;
    private final int MAXROUNDS = 12;
    private final int NCOLUMS = 4;
    private final int NLANES = 12;
    private final int NROWS = 3;
    private final int PhaseDown = 1;
    private final int PhaseUp = 2;
    private final int[] RC = {88, 56, 960, 208, 288, 20, 96, 44, 896, 240, 416, 18};
    private int Rabsorb;
    final int Rkin = 44;
    private final int Rkout = 24;
    private final int TAGLEN = 16;
    private final ByteArrayOutputStream aadData = new ByteArrayOutputStream();
    private boolean aadFinished;
    private boolean encrypted;
    private final int f_bPrime = 48;
    private boolean forEncryption;
    private boolean initialised = false;
    private byte[] iv;
    private final ByteArrayOutputStream message = new ByteArrayOutputStream();
    private MODE mode;
    private int phase;
    private byte[] state;
    private byte[] tag;

    enum MODE {
        ModeHash,
        ModeKeyed
    }

    private void AbsorbAny(byte[] bArr, int i, int i2, int i3, int i4) {
        while (true) {
            if (this.phase != 2) {
                Up((byte[]) null, 0, 0);
            }
            int min = Math.min(i2, i3);
            Down(bArr, i, min, i4);
            i += min;
            i2 -= min;
            if (i2 != 0) {
                i4 = 0;
            } else {
                return;
            }
        }
    }

    private int ROTL32(int i, int i2) {
        return (i >>> ((32 - i2) & 31)) ^ (i << (i2 & 31));
    }

    private void Up(byte[] bArr, int i, int i2) {
        int i3;
        byte[] bArr2 = bArr;
        if (this.mode != MODE.ModeHash) {
            byte[] bArr3 = this.state;
            bArr3[47] = (byte) (bArr3[47] ^ i2);
        }
        int i4 = 12;
        int[] iArr = new int[12];
        Pack.littleEndianToInt(this.state, 0, iArr, 0, 12);
        int[] iArr2 = new int[12];
        int[] iArr3 = new int[4];
        int[] iArr4 = new int[4];
        for (int i5 = 0; i5 < i4; i5++) {
            for (int i6 = 0; i6 < 4; i6++) {
                iArr3[i6] = (iArr[index(i6, 1)] ^ iArr[index(i6, 0)]) ^ iArr[index(i6, 2)];
            }
            int i7 = 0;
            while (true) {
                i3 = 3;
                if (i7 >= 4) {
                    break;
                }
                int i8 = iArr3[3 & (i7 + 3)];
                iArr4[i7] = ROTL32(i8, 14) ^ ROTL32(i8, 5);
                i7++;
            }
            for (int i9 = 0; i9 < 4; i9++) {
                for (int i10 = 0; i10 < 3; i10++) {
                    int index = index(i9, i10);
                    iArr[index] = iArr[index] ^ iArr4[i9];
                }
            }
            for (int i11 = 0; i11 < 4; i11++) {
                iArr2[index(i11, 0)] = iArr[index(i11, 0)];
                iArr2[index(i11, 1)] = iArr[index(i11 + 3, 1)];
                iArr2[index(i11, 2)] = ROTL32(iArr[index(i11, 2)], 11);
            }
            iArr2[0] = iArr2[0] ^ this.RC[i5];
            int i12 = 0;
            while (i12 < 4) {
                int i13 = 0;
                while (i13 < i3) {
                    int i14 = i13 + 1;
                    iArr[index(i12, i13)] = ((iArr2[index(i12, i14)] ^ -1) & iArr2[index(i12, i13 + 2)]) ^ iArr2[index(i12, i13)];
                    i13 = i14;
                    i3 = 3;
                }
                i12++;
                i3 = 3;
            }
            for (int i15 = 0; i15 < 4; i15++) {
                iArr2[index(i15, 0)] = iArr[index(i15, 0)];
                iArr2[index(i15, 1)] = ROTL32(iArr[index(i15, 1)], 1);
                iArr2[index(i15, 2)] = ROTL32(iArr[index(i15 + 2, 2)], 8);
            }
            i4 = 12;
            System.arraycopy(iArr2, 0, iArr, 0, 12);
        }
        Pack.intToLittleEndian(iArr, 0, i4, this.state, 0);
        this.phase = 2;
        if (bArr2 != null) {
            System.arraycopy(this.state, 0, bArr2, 0, i);
        }
    }

    private int encrypt(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        byte[] bArr3 = new byte[24];
        int i4 = this.encrypted ? 0 : 128;
        int i5 = i2;
        while (true) {
            if (i5 == 0 && this.encrypted) {
                return i2;
            }
            int min = Math.min(i5, 24);
            if (this.forEncryption) {
                System.arraycopy(bArr, i, bArr3, 0, min);
            }
            Up((byte[]) null, 0, i4);
            int i6 = 0;
            while (i6 < min) {
                bArr2[i3 + i6] = (byte) (bArr[i] ^ this.state[i6]);
                i6++;
                i++;
            }
            if (this.forEncryption) {
                Down(bArr3, 0, min, 0);
            } else {
                Down(bArr2, i3, min, 0);
            }
            i3 += min;
            i5 -= min;
            this.encrypted = true;
            i4 = 0;
        }
    }

    private int index(int i, int i2) {
        return ((i2 % 3) * 4) + (i % 4);
    }

    private void processAAD() {
        if (!this.aadFinished) {
            byte[] byteArray = this.aadData.toByteArray();
            AbsorbAny(byteArray, 0, byteArray.length, this.Rabsorb, 3);
            this.aadFinished = true;
        }
    }

    private void reset(boolean z) {
        if (z) {
            this.tag = null;
        }
        Arrays.fill(this.state, (byte) 0);
        this.aadFinished = false;
        this.encrypted = false;
        this.phase = 2;
        this.message.reset();
        this.aadData.reset();
        int length = this.K.length;
        int length2 = this.iv.length;
        byte[] bArr = new byte[44];
        this.mode = MODE.ModeKeyed;
        this.Rabsorb = 44;
        System.arraycopy(this.K, 0, bArr, 0, length);
        System.arraycopy(this.iv, 0, bArr, length, length2);
        int i = length + length2;
        bArr[i] = (byte) length2;
        AbsorbAny(bArr, 0, i + 1, this.Rabsorb, 2);
    }

    /* access modifiers changed from: package-private */
    public void Down(byte[] bArr, int i, int i2, int i3) {
        int i4 = 0;
        while (i4 < i2) {
            byte[] bArr2 = this.state;
            bArr2[i4] = (byte) (bArr[i] ^ bArr2[i4]);
            i4++;
            i++;
        }
        byte[] bArr3 = this.state;
        bArr3[i2] = (byte) (bArr3[i2] ^ 1);
        byte b = bArr3[47];
        if (this.mode == MODE.ModeHash) {
            i3 &= 1;
        }
        bArr3[47] = (byte) (b ^ i3);
        this.phase = 1;
    }

    public int doFinal(byte[] bArr, int i) throws IllegalStateException, InvalidCipherTextException {
        int i2;
        if (this.initialised) {
            byte[] byteArray = this.message.toByteArray();
            int size = this.message.size();
            boolean z = this.forEncryption;
            if ((!z || size + 16 + i <= bArr.length) && (z || (size - 16) + i <= bArr.length)) {
                processAAD();
                if (this.forEncryption) {
                    encrypt(byteArray, 0, size, bArr, i);
                    byte[] bArr2 = new byte[16];
                    this.tag = bArr2;
                    Up(bArr2, 16, 64);
                    System.arraycopy(this.tag, 0, bArr, i + size, 16);
                    i2 = size + 16;
                } else {
                    i2 = size - 16;
                    encrypt(byteArray, 0, i2, bArr, i);
                    byte[] bArr3 = new byte[16];
                    this.tag = bArr3;
                    Up(bArr3, 16, 64);
                    int i3 = i2;
                    int i4 = 0;
                    while (i4 < 16) {
                        int i5 = i3 + 1;
                        if (this.tag[i4] == byteArray[i3]) {
                            i4++;
                            i3 = i5;
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
        return "Xoodyak AEAD";
    }

    public int getBlockSize() {
        return 24;
    }

    public int getIVBytesSize() {
        return 16;
    }

    public int getKeyBytesSize() {
        return 16;
    }

    public byte[] getMac() {
        return this.tag;
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
            byte[] iv2 = parametersWithIV.getIV();
            this.iv = iv2;
            if (iv2 == null || iv2.length != 16) {
                throw new IllegalArgumentException("Xoodyak requires exactly 16 bytes of IV");
            } else if (parametersWithIV.getParameters() instanceof KeyParameter) {
                byte[] key = ((KeyParameter) parametersWithIV.getParameters()).getKey();
                this.K = key;
                if (key.length == 16) {
                    CryptoServicesRegistrar.checkConstraints(new DefaultServiceProperties(getAlgorithmName(), 128, cipherParameters, Utils.getPurpose(z)));
                    this.state = new byte[48];
                    this.tag = new byte[16];
                    this.initialised = true;
                    reset();
                    return;
                }
                throw new IllegalArgumentException("Xoodyak key must be 128 bits long");
            } else {
                throw new IllegalArgumentException("Xoodyak init parameters must include a key");
            }
        } else {
            throw new IllegalArgumentException("Xoodyak init parameters must include an IV");
        }
    }

    public void processAADByte(byte b) {
        if (this.aadFinished) {
            StringBuilder sb = new StringBuilder("AAD cannot be added after reading a full block(");
            sb.append(getBlockSize());
            sb.append(" bytes) of input for ");
            sb.append(this.forEncryption ? "encryption" : "decryption");
            throw new IllegalArgumentException(sb.toString());
        }
        this.aadData.write(b);
    }

    public void processAADBytes(byte[] bArr, int i, int i2) {
        if (this.aadFinished) {
            StringBuilder sb = new StringBuilder("AAD cannot be added after reading a full block(");
            sb.append(getBlockSize());
            sb.append(" bytes) of input for ");
            sb.append(this.forEncryption ? "encryption" : "decryption");
            throw new IllegalArgumentException(sb.toString());
        } else if (i + i2 <= bArr.length) {
            this.aadData.write(bArr, i, i2);
        } else {
            throw new DataLengthException("input buffer too short");
        }
    }

    public int processByte(byte b, byte[] bArr, int i) throws DataLengthException {
        return processBytes(new byte[]{b}, 0, 1, bArr, i);
    }

    public int processBytes(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws DataLengthException {
        if (!this.initialised) {
            throw new IllegalArgumentException("Need call init function before encryption/decryption");
        } else if (this.mode != MODE.ModeKeyed) {
            throw new IllegalArgumentException("Xoodyak has not been initialised");
        } else if (i + i2 <= bArr.length) {
            this.message.write(bArr, i, i2);
            int size = this.message.size() - (this.forEncryption ? 0 : 16);
            if (size < getBlockSize()) {
                return 0;
            }
            byte[] byteArray = this.message.toByteArray();
            int blockSize = (size / getBlockSize()) * getBlockSize();
            if (blockSize + i3 <= bArr2.length) {
                processAAD();
                encrypt(byteArray, 0, blockSize, bArr2, i3);
                this.message.reset();
                this.message.write(byteArray, blockSize, byteArray.length - blockSize);
                return blockSize;
            }
            throw new OutputLengthException("output buffer is too short");
        } else {
            throw new DataLengthException("input buffer too short");
        }
    }

    public void reset() {
        if (this.initialised) {
            reset(true);
            return;
        }
        throw new IllegalArgumentException("Need call init function before encryption/decryption");
    }
}
