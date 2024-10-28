package org.bouncycastle.pqc.crypto.frodo;

import org.bouncycastle.crypto.digests.SHAKEDigest;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.Pack;

abstract class FrodoMatrixGenerator {
    int n;
    int q;

    static class Aes128MatrixGenerator extends FrodoMatrixGenerator {
        public Aes128MatrixGenerator(int i, int i2) {
            super(i, i2);
        }

        /* access modifiers changed from: package-private */
        public short[] genMatrix(byte[] bArr) {
            short[] sArr = new short[(this.n * this.n)];
            byte[] bArr2 = new byte[16];
            byte[] bArr3 = new byte[16];
            AESEngine aESEngine = new AESEngine();
            aESEngine.init(true, new KeyParameter(bArr));
            for (int i = 0; i < this.n; i++) {
                Pack.shortToLittleEndian((short) i, bArr2, 0);
                for (int i2 = 0; i2 < this.n; i2 += 8) {
                    Pack.shortToLittleEndian((short) i2, bArr2, 2);
                    aESEngine.processBlock(bArr2, 0, bArr3, 0);
                    for (int i3 = 0; i3 < 8; i3++) {
                        sArr[(this.n * i) + i2 + i3] = (short) (Pack.littleEndianToShort(bArr3, i3 * 2) & (this.q - 1));
                    }
                }
            }
            return sArr;
        }
    }

    static class Shake128MatrixGenerator extends FrodoMatrixGenerator {
        public Shake128MatrixGenerator(int i, int i2) {
            super(i, i2);
        }

        /* access modifiers changed from: package-private */
        public short[] genMatrix(byte[] bArr) {
            short[] sArr = new short[(this.n * this.n)];
            int i = (this.n * 16) / 8;
            byte[] bArr2 = new byte[i];
            int length = bArr.length + 2;
            byte[] bArr3 = new byte[length];
            System.arraycopy(bArr, 0, bArr3, 2, bArr.length);
            SHAKEDigest sHAKEDigest = new SHAKEDigest(128);
            for (short s = 0; s < this.n; s = (short) (s + 1)) {
                Pack.shortToLittleEndian(s, bArr3, 0);
                sHAKEDigest.update(bArr3, 0, length);
                sHAKEDigest.doFinal(bArr2, 0, i);
                for (short s2 = 0; s2 < this.n; s2 = (short) (s2 + 1)) {
                    sArr[(this.n * s) + s2] = (short) (Pack.littleEndianToShort(bArr2, s2 * 2) & (this.q - 1));
                }
            }
            return sArr;
        }
    }

    public FrodoMatrixGenerator(int i, int i2) {
        this.n = i;
        this.q = i2;
    }

    /* access modifiers changed from: package-private */
    public abstract short[] genMatrix(byte[] bArr);
}
