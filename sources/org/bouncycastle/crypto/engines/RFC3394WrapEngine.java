package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.Wrapper;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.util.Arrays;

public class RFC3394WrapEngine implements Wrapper {
    private static final byte[] DEFAULT_IV = {-90, -90, -90, -90, -90, -90, -90, -90};
    private final BlockCipher engine;
    private boolean forWrapping;
    private final byte[] iv;
    private KeyParameter param;
    private final boolean wrapCipherMode;

    public RFC3394WrapEngine(BlockCipher blockCipher) {
        this(blockCipher, false);
    }

    public RFC3394WrapEngine(BlockCipher blockCipher, boolean z) {
        this.iv = new byte[8];
        this.param = null;
        this.forWrapping = true;
        this.engine = blockCipher;
        this.wrapCipherMode = !z;
    }

    public String getAlgorithmName() {
        return this.engine.getAlgorithmName();
    }

    public void init(boolean z, CipherParameters cipherParameters) {
        this.forWrapping = z;
        if (cipherParameters instanceof ParametersWithRandom) {
            cipherParameters = ((ParametersWithRandom) cipherParameters).getParameters();
        }
        if (cipherParameters instanceof KeyParameter) {
            this.param = (KeyParameter) cipherParameters;
            System.arraycopy(DEFAULT_IV, 0, this.iv, 0, 8);
        } else if (cipherParameters instanceof ParametersWithIV) {
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            byte[] iv2 = parametersWithIV.getIV();
            if (iv2.length == 8) {
                this.param = (KeyParameter) parametersWithIV.getParameters();
                System.arraycopy(iv2, 0, this.iv, 0, 8);
                return;
            }
            throw new IllegalArgumentException("IV not equal to 8");
        }
    }

    public byte[] unwrap(byte[] bArr, int i, int i2) throws InvalidCipherTextException {
        byte[] bArr2;
        byte[] bArr3 = bArr;
        int i3 = i;
        int i4 = i2;
        if (this.forWrapping) {
            throw new IllegalStateException("not set for unwrapping");
        } else if (i4 >= 16) {
            int i5 = i4 / 8;
            if (i5 * 8 == i4) {
                this.engine.init(!this.wrapCipherMode, this.param);
                byte[] bArr4 = this.iv;
                byte[] bArr5 = new byte[(i4 - bArr4.length)];
                byte[] bArr6 = new byte[bArr4.length];
                int i6 = 8;
                byte[] bArr7 = new byte[(bArr4.length + 8)];
                int i7 = i5 - 1;
                if (i7 == 1) {
                    this.engine.processBlock(bArr3, i3, bArr7, 0);
                    System.arraycopy(bArr7, 0, bArr6, 0, this.iv.length);
                    System.arraycopy(bArr7, this.iv.length, bArr5, 0, 8);
                } else {
                    System.arraycopy(bArr3, i3, bArr6, 0, bArr4.length);
                    byte[] bArr8 = this.iv;
                    System.arraycopy(bArr3, bArr8.length + i3, bArr5, 0, i4 - bArr8.length);
                    int i8 = 5;
                    while (i8 >= 0) {
                        int i9 = i7;
                        while (i9 >= 1) {
                            System.arraycopy(bArr6, 0, bArr7, 0, this.iv.length);
                            int i10 = (i9 - 1) * 8;
                            System.arraycopy(bArr5, i10, bArr7, this.iv.length, i6);
                            int i11 = (i7 * i8) + i9;
                            int i12 = 1;
                            while (i11 != 0) {
                                int length = this.iv.length - i12;
                                bArr7[length] = (byte) (bArr7[length] ^ ((byte) i11));
                                i11 >>>= 8;
                                i12++;
                            }
                            this.engine.processBlock(bArr7, 0, bArr7, 0);
                            System.arraycopy(bArr7, 0, bArr6, 0, 8);
                            System.arraycopy(bArr7, 8, bArr5, i10, 8);
                            i9--;
                            i6 = 8;
                        }
                        i8--;
                        i6 = 8;
                    }
                }
                if (i7 != 1) {
                    if (!Arrays.constantTimeAreEqual(bArr6, this.iv)) {
                        throw new InvalidCipherTextException("checksum failed");
                    }
                } else if (!Arrays.constantTimeAreEqual(bArr6, this.iv)) {
                    System.arraycopy(bArr3, i3, bArr6, 0, this.iv.length);
                    byte[] bArr9 = this.iv;
                    System.arraycopy(bArr3, i3 + bArr9.length, bArr5, 0, i4 - bArr9.length);
                    int i13 = 5;
                    while (true) {
                        bArr2 = this.iv;
                        if (i13 < 0) {
                            break;
                        }
                        System.arraycopy(bArr6, 0, bArr7, 0, bArr2.length);
                        System.arraycopy(bArr5, 0, bArr7, this.iv.length, 8);
                        int i14 = (i7 * i13) + 1;
                        int i15 = 1;
                        while (i14 != 0) {
                            int length2 = this.iv.length - i15;
                            bArr7[length2] = (byte) (((byte) i14) ^ bArr7[length2]);
                            i14 >>>= 8;
                            i15++;
                        }
                        this.engine.processBlock(bArr7, 0, bArr7, 0);
                        System.arraycopy(bArr7, 0, bArr6, 0, 8);
                        System.arraycopy(bArr7, 8, bArr5, 0, 8);
                        i13--;
                    }
                    if (!Arrays.constantTimeAreEqual(bArr6, bArr2)) {
                        throw new InvalidCipherTextException("checksum failed");
                    }
                }
                return bArr5;
            }
            throw new InvalidCipherTextException("unwrap data must be a multiple of 8 bytes");
        } else {
            throw new InvalidCipherTextException("unwrap data too short");
        }
    }

    public byte[] wrap(byte[] bArr, int i, int i2) {
        if (!this.forWrapping) {
            throw new IllegalStateException("not set for wrapping");
        } else if (i2 >= 8) {
            int i3 = i2 / 8;
            if (i3 * 8 == i2) {
                this.engine.init(this.wrapCipherMode, this.param);
                byte[] bArr2 = this.iv;
                byte[] bArr3 = new byte[(bArr2.length + i2)];
                System.arraycopy(bArr2, 0, bArr3, 0, bArr2.length);
                System.arraycopy(bArr, i, bArr3, this.iv.length, i2);
                if (i3 == 1) {
                    this.engine.processBlock(bArr3, 0, bArr3, 0);
                } else {
                    byte[] bArr4 = new byte[(this.iv.length + 8)];
                    for (int i4 = 0; i4 != 6; i4++) {
                        for (int i5 = 1; i5 <= i3; i5++) {
                            System.arraycopy(bArr3, 0, bArr4, 0, this.iv.length);
                            int i6 = i5 * 8;
                            System.arraycopy(bArr3, i6, bArr4, this.iv.length, 8);
                            this.engine.processBlock(bArr4, 0, bArr4, 0);
                            int i7 = (i3 * i4) + i5;
                            int i8 = 1;
                            while (i7 != 0) {
                                int length = this.iv.length - i8;
                                bArr4[length] = (byte) (((byte) i7) ^ bArr4[length]);
                                i7 >>>= 8;
                                i8++;
                            }
                            System.arraycopy(bArr4, 0, bArr3, 0, 8);
                            System.arraycopy(bArr4, 8, bArr3, i6, 8);
                        }
                    }
                }
                return bArr3;
            }
            throw new DataLengthException("wrap data must be a multiple of 8 bytes");
        } else {
            throw new DataLengthException("wrap data must be at least 8 bytes");
        }
    }
}
