package org.bouncycastle.crypto.modes;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.DefaultMultiBlockCipher;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.Arrays;

public class CBCBlockCipher extends DefaultMultiBlockCipher implements CBCModeCipher {
    private byte[] IV;
    private int blockSize;
    private byte[] cbcNextV;
    private byte[] cbcV;
    private BlockCipher cipher;
    private boolean encrypting;

    public CBCBlockCipher(BlockCipher blockCipher) {
        this.cipher = blockCipher;
        int blockSize2 = blockCipher.getBlockSize();
        this.blockSize = blockSize2;
        this.IV = new byte[blockSize2];
        this.cbcV = new byte[blockSize2];
        this.cbcNextV = new byte[blockSize2];
    }

    private int decryptBlock(byte[] bArr, int i, byte[] bArr2, int i2) throws DataLengthException, IllegalStateException {
        int i3 = this.blockSize;
        if (i + i3 <= bArr.length) {
            System.arraycopy(bArr, i, this.cbcNextV, 0, i3);
            int processBlock = this.cipher.processBlock(bArr, i, bArr2, i2);
            for (int i4 = 0; i4 < this.blockSize; i4++) {
                int i5 = i2 + i4;
                bArr2[i5] = (byte) (bArr2[i5] ^ this.cbcV[i4]);
            }
            byte[] bArr3 = this.cbcV;
            this.cbcV = this.cbcNextV;
            this.cbcNextV = bArr3;
            return processBlock;
        }
        throw new DataLengthException("input buffer too short");
    }

    private int encryptBlock(byte[] bArr, int i, byte[] bArr2, int i2) throws DataLengthException, IllegalStateException {
        if (this.blockSize + i <= bArr.length) {
            for (int i3 = 0; i3 < this.blockSize; i3++) {
                byte[] bArr3 = this.cbcV;
                bArr3[i3] = (byte) (bArr3[i3] ^ bArr[i + i3]);
            }
            int processBlock = this.cipher.processBlock(this.cbcV, 0, bArr2, i2);
            byte[] bArr4 = this.cbcV;
            System.arraycopy(bArr2, i2, bArr4, 0, bArr4.length);
            return processBlock;
        }
        throw new DataLengthException("input buffer too short");
    }

    public static CBCModeCipher newInstance(BlockCipher blockCipher) {
        return new CBCBlockCipher(blockCipher);
    }

    public String getAlgorithmName() {
        return this.cipher.getAlgorithmName() + "/CBC";
    }

    public int getBlockSize() {
        return this.cipher.getBlockSize();
    }

    public BlockCipher getUnderlyingCipher() {
        return this.cipher;
    }

    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        boolean z2 = this.encrypting;
        this.encrypting = z;
        if (cipherParameters instanceof ParametersWithIV) {
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            byte[] iv = parametersWithIV.getIV();
            if (iv.length == this.blockSize) {
                System.arraycopy(iv, 0, this.IV, 0, iv.length);
                cipherParameters = parametersWithIV.getParameters();
            } else {
                throw new IllegalArgumentException("initialisation vector must be the same length as block size");
            }
        } else {
            Arrays.fill(this.IV, (byte) 0);
        }
        reset();
        if (cipherParameters != null) {
            this.cipher.init(z, cipherParameters);
        } else if (z2 != z) {
            throw new IllegalArgumentException("cannot change encrypting state without providing key.");
        }
    }

    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) throws DataLengthException, IllegalStateException {
        return this.encrypting ? encryptBlock(bArr, i, bArr2, i2) : decryptBlock(bArr, i, bArr2, i2);
    }

    public void reset() {
        byte[] bArr = this.IV;
        System.arraycopy(bArr, 0, this.cbcV, 0, bArr.length);
        Arrays.fill(this.cbcNextV, (byte) 0);
        this.cipher.reset();
    }
}
