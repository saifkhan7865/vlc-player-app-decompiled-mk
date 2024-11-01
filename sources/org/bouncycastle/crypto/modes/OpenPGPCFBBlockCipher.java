package org.bouncycastle.crypto.modes;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;

public class OpenPGPCFBBlockCipher implements BlockCipher {
    private byte[] FR;
    private byte[] FRE;
    private byte[] IV;
    private int blockSize;
    private BlockCipher cipher;
    private int count;
    private boolean forEncryption;

    public OpenPGPCFBBlockCipher(BlockCipher blockCipher) {
        this.cipher = blockCipher;
        int blockSize2 = blockCipher.getBlockSize();
        this.blockSize = blockSize2;
        this.IV = new byte[blockSize2];
        this.FR = new byte[blockSize2];
        this.FRE = new byte[blockSize2];
    }

    private int decryptBlock(byte[] bArr, int i, byte[] bArr2, int i2) throws DataLengthException, IllegalStateException {
        int i3;
        int i4 = this.blockSize;
        if (i + i4 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        } else if (i2 + i4 <= bArr2.length) {
            int i5 = this.count;
            int i6 = 2;
            int i7 = 0;
            if (i5 > i4) {
                byte b = bArr[i];
                this.FR[i4 - 2] = b;
                bArr2[i2] = encryptByte(b, i4 - 2);
                byte b2 = bArr[i + 1];
                byte[] bArr3 = this.FR;
                int i8 = this.blockSize;
                bArr3[i8 - 1] = b2;
                bArr2[i2 + 1] = encryptByte(b2, i8 - 1);
                this.cipher.processBlock(this.FR, 0, this.FRE, 0);
                while (i6 < this.blockSize) {
                    byte b3 = bArr[i + i6];
                    int i9 = i6 - 2;
                    this.FR[i9] = b3;
                    bArr2[i2 + i6] = encryptByte(b3, i9);
                    i6++;
                }
            } else {
                if (i5 != 0) {
                    if (i5 == i4) {
                        this.cipher.processBlock(this.FR, 0, this.FRE, 0);
                        byte b4 = bArr[i];
                        byte b5 = bArr[i + 1];
                        bArr2[i2] = encryptByte(b4, 0);
                        bArr2[i2 + 1] = encryptByte(b5, 1);
                        byte[] bArr4 = this.FR;
                        System.arraycopy(bArr4, 2, bArr4, 0, this.blockSize - 2);
                        byte[] bArr5 = this.FR;
                        int i10 = this.blockSize;
                        bArr5[i10 - 2] = b4;
                        bArr5[i10 - 1] = b5;
                        this.cipher.processBlock(bArr5, 0, this.FRE, 0);
                        while (true) {
                            i3 = this.blockSize;
                            if (i6 >= i3) {
                                break;
                            }
                            byte b6 = bArr[i + i6];
                            int i11 = i6 - 2;
                            this.FR[i11] = b6;
                            bArr2[i2 + i6] = encryptByte(b6, i11);
                            i6++;
                        }
                    }
                } else {
                    this.cipher.processBlock(this.FR, 0, this.FRE, 0);
                    while (true) {
                        i3 = this.blockSize;
                        if (i7 >= i3) {
                            break;
                        }
                        int i12 = i + i7;
                        this.FR[i7] = bArr[i12];
                        bArr2[i2 + i7] = encryptByte(bArr[i12], i7);
                        i7++;
                    }
                }
                this.count += i3;
            }
            return this.blockSize;
        } else {
            throw new OutputLengthException("output buffer too short");
        }
    }

    private int encryptBlock(byte[] bArr, int i, byte[] bArr2, int i2) throws DataLengthException, IllegalStateException {
        int i3;
        int i4 = this.blockSize;
        if (i + i4 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        } else if (i2 + i4 <= bArr2.length) {
            int i5 = this.count;
            int i6 = 2;
            int i7 = 0;
            if (i5 > i4) {
                byte[] bArr3 = this.FR;
                int i8 = i4 - 2;
                byte encryptByte = encryptByte(bArr[i], i4 - 2);
                bArr2[i2] = encryptByte;
                bArr3[i8] = encryptByte;
                byte[] bArr4 = this.FR;
                int i9 = this.blockSize;
                int i10 = i9 - 1;
                byte encryptByte2 = encryptByte(bArr[i + 1], i9 - 1);
                bArr2[i2 + 1] = encryptByte2;
                bArr4[i10] = encryptByte2;
                this.cipher.processBlock(this.FR, 0, this.FRE, 0);
                while (i6 < this.blockSize) {
                    byte[] bArr5 = this.FR;
                    int i11 = i6 - 2;
                    byte encryptByte3 = encryptByte(bArr[i + i6], i11);
                    bArr2[i2 + i6] = encryptByte3;
                    bArr5[i11] = encryptByte3;
                    i6++;
                }
            } else {
                if (i5 != 0) {
                    if (i5 == i4) {
                        this.cipher.processBlock(this.FR, 0, this.FRE, 0);
                        bArr2[i2] = encryptByte(bArr[i], 0);
                        bArr2[i2 + 1] = encryptByte(bArr[i + 1], 1);
                        byte[] bArr6 = this.FR;
                        System.arraycopy(bArr6, 2, bArr6, 0, this.blockSize - 2);
                        System.arraycopy(bArr2, i2, this.FR, this.blockSize - 2, 2);
                        this.cipher.processBlock(this.FR, 0, this.FRE, 0);
                        while (true) {
                            i3 = this.blockSize;
                            if (i6 >= i3) {
                                break;
                            }
                            byte[] bArr7 = this.FR;
                            int i12 = i6 - 2;
                            byte encryptByte4 = encryptByte(bArr[i + i6], i12);
                            bArr2[i2 + i6] = encryptByte4;
                            bArr7[i12] = encryptByte4;
                            i6++;
                        }
                    }
                } else {
                    this.cipher.processBlock(this.FR, 0, this.FRE, 0);
                    while (true) {
                        i3 = this.blockSize;
                        if (i7 >= i3) {
                            break;
                        }
                        byte[] bArr8 = this.FR;
                        byte encryptByte5 = encryptByte(bArr[i + i7], i7);
                        bArr2[i2 + i7] = encryptByte5;
                        bArr8[i7] = encryptByte5;
                        i7++;
                    }
                }
                this.count += i3;
            }
            return this.blockSize;
        } else {
            throw new OutputLengthException("output buffer too short");
        }
    }

    private byte encryptByte(byte b, int i) {
        return (byte) (b ^ this.FRE[i]);
    }

    public String getAlgorithmName() {
        return this.cipher.getAlgorithmName() + "/OpenPGPCFB";
    }

    public int getBlockSize() {
        return this.cipher.getBlockSize();
    }

    public BlockCipher getUnderlyingCipher() {
        return this.cipher;
    }

    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        this.forEncryption = z;
        reset();
        this.cipher.init(true, cipherParameters);
    }

    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) throws DataLengthException, IllegalStateException {
        return this.forEncryption ? encryptBlock(bArr, i, bArr2, i2) : decryptBlock(bArr, i, bArr2, i2);
    }

    public void reset() {
        this.count = 0;
        byte[] bArr = this.IV;
        byte[] bArr2 = this.FR;
        System.arraycopy(bArr, 0, bArr2, 0, bArr2.length);
        this.cipher.reset();
    }
}
