package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.constraints.DefaultServiceProperties;
import org.bouncycastle.crypto.params.KeyParameter;

public class DESedeEngine extends DESBase implements BlockCipher {
    protected static final int BLOCK_SIZE = 8;
    private boolean forEncryption;
    private int[] workingKey1 = null;
    private int[] workingKey2 = null;
    private int[] workingKey3 = null;

    public DESedeEngine() {
        CryptoServicesRegistrar.checkConstraints(new DefaultServiceProperties(getAlgorithmName(), bitsOfSecurity()));
    }

    private int bitsOfSecurity() {
        int[] iArr = this.workingKey1;
        return (iArr == null || iArr != this.workingKey3) ? 112 : 80;
    }

    public String getAlgorithmName() {
        return "DESede";
    }

    public int getBlockSize() {
        return 8;
    }

    public void init(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof KeyParameter) {
            byte[] key = ((KeyParameter) cipherParameters).getKey();
            if (key.length == 24 || key.length == 16) {
                this.forEncryption = z;
                byte[] bArr = new byte[8];
                System.arraycopy(key, 0, bArr, 0, 8);
                this.workingKey1 = generateWorkingKey(z, bArr);
                byte[] bArr2 = new byte[8];
                System.arraycopy(key, 8, bArr2, 0, 8);
                this.workingKey2 = generateWorkingKey(!z, bArr2);
                if (key.length == 24) {
                    byte[] bArr3 = new byte[8];
                    System.arraycopy(key, 16, bArr3, 0, 8);
                    this.workingKey3 = generateWorkingKey(z, bArr3);
                } else {
                    this.workingKey3 = this.workingKey1;
                }
                CryptoServicesRegistrar.checkConstraints(new DefaultServiceProperties(getAlgorithmName(), bitsOfSecurity(), cipherParameters, Utils.getPurpose(this.forEncryption)));
                return;
            }
            throw new IllegalArgumentException("key size must be 16 or 24 bytes.");
        }
        throw new IllegalArgumentException("invalid parameter passed to DESede init - " + cipherParameters.getClass().getName());
    }

    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        int[] iArr = this.workingKey1;
        if (iArr == null) {
            throw new IllegalStateException("DESede engine not initialised");
        } else if (i + 8 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        } else if (i2 + 8 <= bArr2.length) {
            byte[] bArr3 = new byte[8];
            if (this.forEncryption) {
                byte[] bArr4 = bArr3;
                desFunc(iArr, bArr, i, bArr4, 0);
                byte[] bArr5 = bArr3;
                desFunc(this.workingKey2, bArr5, 0, bArr4, 0);
                desFunc(this.workingKey3, bArr5, 0, bArr2, i2);
            } else {
                byte[] bArr6 = bArr3;
                desFunc(this.workingKey3, bArr, i, bArr6, 0);
                byte[] bArr7 = bArr3;
                desFunc(this.workingKey2, bArr7, 0, bArr6, 0);
                desFunc(this.workingKey1, bArr7, 0, bArr2, i2);
            }
            return 8;
        } else {
            throw new OutputLengthException("output buffer too short");
        }
    }

    public void reset() {
    }
}
