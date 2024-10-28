package org.bouncycastle.crypto.modes;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.StreamBlockCipher;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;

public class SICBlockCipher extends StreamBlockCipher implements CTRModeCipher {
    private byte[] IV;
    private final int blockSize;
    private int byteCount = 0;
    private final BlockCipher cipher;
    private byte[] counter;
    private byte[] counterOut;

    public SICBlockCipher(BlockCipher blockCipher) {
        super(blockCipher);
        this.cipher = blockCipher;
        int blockSize2 = blockCipher.getBlockSize();
        this.blockSize = blockSize2;
        this.IV = new byte[blockSize2];
        this.counter = new byte[blockSize2];
        this.counterOut = new byte[blockSize2];
    }

    private void adjustCounter(long j) {
        int i;
        int i2 = 5;
        if (j >= 0) {
            long j2 = (((long) this.byteCount) + j) / ((long) this.blockSize);
            long j3 = j2;
            if (j2 > 255) {
                while (i2 >= 1) {
                    long j4 = 1 << (i2 * 8);
                    while (j3 >= j4) {
                        incrementCounterAt(i2);
                        j3 -= j4;
                    }
                    i2--;
                }
            }
            incrementCounter((int) j3);
            i = (int) ((j + ((long) this.byteCount)) - (((long) this.blockSize) * j2));
        } else {
            long j5 = ((-j) - ((long) this.byteCount)) / ((long) this.blockSize);
            long j6 = j5;
            if (j5 > 255) {
                while (i2 >= 1) {
                    long j7 = 1 << (i2 * 8);
                    while (j6 > j7) {
                        decrementCounterAt(i2);
                        j6 -= j7;
                    }
                    i2--;
                }
            }
            for (long j8 = 0; j8 != j6; j8++) {
                decrementCounterAt(0);
            }
            int i3 = (int) (((long) this.byteCount) + j + (((long) this.blockSize) * j5));
            if (i3 >= 0) {
                this.byteCount = 0;
                return;
            } else {
                decrementCounterAt(0);
                i = this.blockSize + i3;
            }
        }
        this.byteCount = i;
    }

    private void checkCounter() {
        byte[] bArr = this.IV;
        if (bArr.length < this.blockSize) {
            int length = bArr.length - 1;
            while (length >= 0) {
                if (this.counter[length] == this.IV[length]) {
                    length--;
                } else {
                    throw new IllegalStateException("Counter in CTR/SIC mode out of range.");
                }
            }
        }
    }

    private void checkLastIncrement() {
        byte[] bArr = this.IV;
        if (bArr.length < this.blockSize && this.counter[bArr.length - 1] != bArr[bArr.length - 1]) {
            throw new IllegalStateException("Counter in CTR/SIC mode out of range.");
        }
    }

    private void decrementCounterAt(int i) {
        byte b;
        int length = this.counter.length - i;
        do {
            length--;
            if (length >= 0) {
                byte[] bArr = this.counter;
                b = (byte) (bArr[length] - 1);
                bArr[length] = b;
            } else {
                return;
            }
        } while (b == -1);
    }

    private void incrementCounter() {
        byte b;
        int length = this.counter.length;
        do {
            length--;
            if (length >= 0) {
                byte[] bArr = this.counter;
                b = (byte) (bArr[length] + 1);
                bArr[length] = b;
            } else {
                return;
            }
        } while (b == 0);
    }

    private void incrementCounter(int i) {
        byte[] bArr = this.counter;
        byte b = bArr[bArr.length - 1];
        int length = bArr.length - 1;
        bArr[length] = (byte) (bArr[length] + i);
        if (b != 0 && bArr[bArr.length - 1] < b) {
            incrementCounterAt(1);
        }
    }

    private void incrementCounterAt(int i) {
        byte b;
        int length = this.counter.length - i;
        do {
            length--;
            if (length >= 0) {
                byte[] bArr = this.counter;
                b = (byte) (bArr[length] + 1);
                bArr[length] = b;
            } else {
                return;
            }
        } while (b == 0);
    }

    public static CTRModeCipher newInstance(BlockCipher blockCipher) {
        return new SICBlockCipher(blockCipher);
    }

    /* access modifiers changed from: protected */
    public byte calculateByte(byte b) throws DataLengthException, IllegalStateException {
        int i = this.byteCount;
        if (i == 0) {
            checkLastIncrement();
            this.cipher.processBlock(this.counter, 0, this.counterOut, 0);
            byte[] bArr = this.counterOut;
            int i2 = this.byteCount;
            this.byteCount = i2 + 1;
            return (byte) (b ^ bArr[i2]);
        }
        byte[] bArr2 = this.counterOut;
        int i3 = i + 1;
        this.byteCount = i3;
        byte b2 = (byte) (b ^ bArr2[i]);
        if (i3 == this.counter.length) {
            this.byteCount = 0;
            incrementCounter();
        }
        return b2;
    }

    public String getAlgorithmName() {
        return this.cipher.getAlgorithmName() + "/SIC";
    }

    public int getBlockSize() {
        return this.cipher.getBlockSize();
    }

    public long getPosition() {
        byte[] bArr = this.counter;
        int length = bArr.length;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 0, bArr2, 0, length);
        int i = length - 1;
        while (i >= 1) {
            byte[] bArr3 = this.IV;
            int i2 = i < bArr3.length ? (bArr2[i] & 255) - (bArr3[i] & 255) : bArr2[i] & 255;
            if (i2 < 0) {
                int i3 = i - 1;
                bArr2[i3] = (byte) (bArr2[i3] - 1);
                i2 += 256;
            }
            bArr2[i] = (byte) i2;
            i--;
        }
        return (Pack.bigEndianToLong(bArr2, length - 8) * ((long) this.blockSize)) + ((long) this.byteCount);
    }

    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        if (cipherParameters instanceof ParametersWithIV) {
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            byte[] clone = Arrays.clone(parametersWithIV.getIV());
            this.IV = clone;
            int i = this.blockSize;
            if (i >= clone.length) {
                int i2 = 8;
                if (8 > i / 2) {
                    i2 = i / 2;
                }
                if (i - clone.length <= i2) {
                    if (parametersWithIV.getParameters() != null) {
                        this.cipher.init(true, parametersWithIV.getParameters());
                    }
                    reset();
                    return;
                }
                throw new IllegalArgumentException("CTR/SIC mode requires IV of at least: " + (this.blockSize - i2) + " bytes.");
            }
            throw new IllegalArgumentException("CTR/SIC mode requires IV no greater than: " + this.blockSize + " bytes.");
        }
        throw new IllegalArgumentException("CTR/SIC mode requires ParametersWithIV");
    }

    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) throws DataLengthException, IllegalStateException {
        if (this.byteCount != 0) {
            processBytes(bArr, i, this.blockSize, bArr2, i2);
        } else {
            int i3 = this.blockSize;
            if (i + i3 > bArr.length) {
                throw new DataLengthException("input buffer too small");
            } else if (i3 + i2 <= bArr2.length) {
                this.cipher.processBlock(this.counter, 0, this.counterOut, 0);
                for (int i4 = 0; i4 < this.blockSize; i4++) {
                    bArr2[i2 + i4] = (byte) (bArr[i + i4] ^ this.counterOut[i4]);
                }
                incrementCounter();
            } else {
                throw new OutputLengthException("output buffer too short");
            }
        }
        return this.blockSize;
    }

    public int processBytes(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws DataLengthException {
        byte b;
        if (i + i2 > bArr.length) {
            throw new DataLengthException("input buffer too small");
        } else if (i3 + i2 <= bArr2.length) {
            for (int i4 = 0; i4 < i2; i4++) {
                int i5 = this.byteCount;
                if (i5 == 0) {
                    checkLastIncrement();
                    this.cipher.processBlock(this.counter, 0, this.counterOut, 0);
                    byte b2 = bArr[i + i4];
                    byte[] bArr3 = this.counterOut;
                    int i6 = this.byteCount;
                    this.byteCount = i6 + 1;
                    b = (byte) (b2 ^ bArr3[i6]);
                } else {
                    byte b3 = bArr[i + i4];
                    byte[] bArr4 = this.counterOut;
                    int i7 = i5 + 1;
                    this.byteCount = i7;
                    b = (byte) (bArr4[i5] ^ b3);
                    if (i7 == this.counter.length) {
                        this.byteCount = 0;
                        incrementCounter();
                    }
                }
                bArr2[i3 + i4] = b;
            }
            return i2;
        } else {
            throw new OutputLengthException("output buffer too short");
        }
    }

    public void reset() {
        Arrays.fill(this.counter, (byte) 0);
        byte[] bArr = this.IV;
        System.arraycopy(bArr, 0, this.counter, 0, bArr.length);
        this.cipher.reset();
        this.byteCount = 0;
    }

    public long seekTo(long j) {
        reset();
        return skip(j);
    }

    public long skip(long j) {
        adjustCounter(j);
        checkCounter();
        this.cipher.processBlock(this.counter, 0, this.counterOut, 0);
        return j;
    }
}
