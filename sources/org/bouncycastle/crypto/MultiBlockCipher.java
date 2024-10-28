package org.bouncycastle.crypto;

public interface MultiBlockCipher extends BlockCipher {
    int getMultiBlockSize();

    int processBlocks(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws DataLengthException, IllegalStateException;
}
