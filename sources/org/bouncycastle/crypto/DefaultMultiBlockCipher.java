package org.bouncycastle.crypto;

public abstract class DefaultMultiBlockCipher implements MultiBlockCipher {
    protected DefaultMultiBlockCipher() {
    }

    public int getMultiBlockSize() {
        return getBlockSize();
    }

    public int processBlocks(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws DataLengthException, IllegalStateException {
        int multiBlockSize = getMultiBlockSize();
        int i4 = 0;
        for (int i5 = 0; i5 != i2; i5++) {
            i4 += processBlock(bArr, i, bArr2, i3 + i4);
            i += multiBlockSize;
        }
        return i4;
    }
}
