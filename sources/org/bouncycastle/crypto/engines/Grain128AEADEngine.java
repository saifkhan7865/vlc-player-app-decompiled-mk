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
import org.bouncycastle.util.Pack;

public class Grain128AEADEngine implements AEADCipher {
    private static final int STATE_SIZE = 4;
    private ErasableOutputStream aadData = new ErasableOutputStream();
    private boolean aadFinished = false;
    private int[] authAcc;
    private int[] authSr;
    private boolean initialised = false;
    private int[] lfsr;
    private byte[] mac;
    private int[] nfsr;
    private byte[] workingIV;
    private byte[] workingKey;

    private static final class ErasableOutputStream extends ByteArrayOutputStream {
        public byte[] getBuf() {
            return this.buf;
        }
    }

    private void accumulate() {
        int[] iArr = this.authAcc;
        int i = iArr[0];
        int[] iArr2 = this.authSr;
        iArr[0] = i ^ iArr2[0];
        iArr[1] = iArr[1] ^ iArr2[1];
    }

    private void authShift(int i) {
        int[] iArr = this.authSr;
        int i2 = iArr[1];
        iArr[0] = (iArr[0] >>> 1) | (i2 << 31);
        iArr[1] = (i << 31) | (i2 >>> 1);
    }

    private void doProcessAADBytes(byte[] bArr, int i, int i2) {
        int i3;
        byte[] bArr2;
        if (i2 < 128) {
            bArr2 = new byte[(i2 + 1)];
            bArr2[0] = (byte) i2;
            i3 = 0;
        } else {
            int len_length = len_length(i2);
            byte[] bArr3 = new byte[(len_length + 1 + i2)];
            bArr3[0] = (byte) (len_length | 128);
            int i4 = i2;
            int i5 = 0;
            while (i5 < len_length) {
                i5++;
                bArr3[i5] = (byte) i4;
                i4 >>>= 8;
            }
            byte[] bArr4 = bArr3;
            i3 = len_length;
            bArr2 = bArr4;
        }
        for (int i6 = 0; i6 < i2; i6++) {
            bArr2[1 + i3 + i6] = bArr[i + i6];
        }
        for (byte b : bArr2) {
            for (int i7 = 0; i7 < 8; i7++) {
                this.nfsr = shift(this.nfsr, (getOutputNFSR() ^ this.lfsr[0]) & 1);
                this.lfsr = shift(this.lfsr, getOutputLFSR() & 1);
                int i8 = -((b >> i7) & 1);
                int[] iArr = this.authAcc;
                int i9 = iArr[0];
                int[] iArr2 = this.authSr;
                iArr[0] = i9 ^ (iArr2[0] & i8);
                iArr[1] = (i8 & iArr2[1]) ^ iArr[1];
                authShift(getOutput());
                this.nfsr = shift(this.nfsr, (getOutputNFSR() ^ this.lfsr[0]) & 1);
                this.lfsr = shift(this.lfsr, getOutputLFSR() & 1);
            }
        }
    }

    private byte[] getKeyStream(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        int i4 = i2;
        for (int i5 = 0; i5 < i4; i5++) {
            byte b = bArr[i + i5];
            byte b2 = 0;
            for (int i6 = 0; i6 < 8; i6++) {
                int output = getOutput();
                this.nfsr = shift(this.nfsr, (getOutputNFSR() ^ this.lfsr[0]) & 1);
                this.lfsr = shift(this.lfsr, getOutputLFSR() & 1);
                int i7 = (b >> i6) & 1;
                b2 = (byte) (b2 | ((output ^ i7) << i6));
                int i8 = -i7;
                int[] iArr = this.authAcc;
                int i9 = iArr[0];
                int[] iArr2 = this.authSr;
                iArr[0] = i9 ^ (iArr2[0] & i8);
                iArr[1] = (i8 & iArr2[1]) ^ iArr[1];
                authShift(getOutput());
                this.nfsr = shift(this.nfsr, (getOutputNFSR() ^ this.lfsr[0]) & 1);
                this.lfsr = shift(this.lfsr, getOutputLFSR() & 1);
            }
            bArr2[i3 + i5] = b2;
        }
        return bArr2;
    }

    private int getOutput() {
        int[] iArr = this.nfsr;
        int i = iArr[0];
        int i2 = i >>> 12;
        int i3 = iArr[1];
        int i4 = iArr[2];
        int i5 = i4 >>> 9;
        int i6 = i4 >>> 25;
        int i7 = i4 >>> 31;
        int[] iArr2 = this.lfsr;
        int i8 = iArr2[0];
        int i9 = iArr2[1];
        int i10 = iArr2[2];
        int i11 = i10 >>> 29;
        int i12 = (i8 >>> 20) & (i8 >>> 13);
        return (((i4 ^ (((((((((i12 ^ ((i8 >>> 8) & i2)) ^ (i7 & (i9 >>> 10))) ^ ((i9 >>> 28) & (i10 >>> 15))) ^ ((i2 & i7) & (i10 >>> 30))) ^ i11) ^ (i >>> 2)) ^ (i >>> 15)) ^ (i3 >>> 4)) ^ (i3 >>> 13))) ^ i5) ^ i6) & 1;
    }

    private int getOutputLFSR() {
        int[] iArr = this.lfsr;
        int i = iArr[0];
        int i2 = iArr[2];
        return (iArr[3] ^ ((((i ^ (i >>> 7)) ^ (iArr[1] >>> 6)) ^ (i2 >>> 6)) ^ (i2 >>> 17))) & 1;
    }

    private int getOutputNFSR() {
        int[] iArr = this.nfsr;
        int i = iArr[0];
        int i2 = i >>> 25;
        int i3 = i >>> 27;
        int i4 = iArr[1];
        int i5 = i4 >>> 8;
        int i6 = i4 >>> 16;
        int i7 = i4 >>> 24;
        int i8 = i4 >>> 27;
        int i9 = iArr[2];
        int i10 = i9 >>> 1;
        int i11 = i9 >>> 3;
        int i12 = i9 >>> 4;
        int i13 = i9 >>> 18;
        int i14 = i9 >>> 20;
        int i15 = i9 >>> 27;
        int i16 = i9 >>> 29;
        return (((((((((((iArr[3] ^ (((i ^ (i >>> 26)) ^ i7) ^ i15)) ^ ((i >>> 3) & i11)) ^ ((i >>> 11) & (i >>> 13))) ^ ((i >>> 17) & (i >>> 18))) ^ (i3 & i8)) ^ (i5 & i6)) ^ ((i4 >>> 29) & i10)) ^ (i12 & i14)) ^ (((i >>> 22) & (i >>> 24)) & i2)) ^ (((i9 >>> 6) & (i9 >>> 14)) & i13)) ^ ((((i9 >>> 24) & (i9 >>> 28)) & i16) & (i9 >>> 31))) & 1;
    }

    private void initGrain() {
        for (int i = 0; i < 320; i++) {
            int output = getOutput();
            this.nfsr = shift(this.nfsr, ((getOutputNFSR() ^ this.lfsr[0]) ^ output) & 1);
            this.lfsr = shift(this.lfsr, (output ^ getOutputLFSR()) & 1);
        }
        for (int i2 = 0; i2 < 8; i2++) {
            for (int i3 = 0; i3 < 8; i3++) {
                int output2 = getOutput();
                this.nfsr = shift(this.nfsr, (((getOutputNFSR() ^ this.lfsr[0]) ^ output2) ^ (this.workingKey[i2] >> i3)) & 1);
                this.lfsr = shift(this.lfsr, ((output2 ^ getOutputLFSR()) ^ (this.workingKey[i2 + 8] >> i3)) & 1);
            }
        }
        for (int i4 = 0; i4 < 2; i4++) {
            for (int i5 = 0; i5 < 32; i5++) {
                int output3 = getOutput();
                this.nfsr = shift(this.nfsr, (getOutputNFSR() ^ this.lfsr[0]) & 1);
                this.lfsr = shift(this.lfsr, getOutputLFSR() & 1);
                int[] iArr = this.authAcc;
                iArr[i4] = (output3 << i5) | iArr[i4];
            }
        }
        for (int i6 = 0; i6 < 2; i6++) {
            for (int i7 = 0; i7 < 32; i7++) {
                int output4 = getOutput();
                this.nfsr = shift(this.nfsr, (getOutputNFSR() ^ this.lfsr[0]) & 1);
                this.lfsr = shift(this.lfsr, getOutputLFSR() & 1);
                int[] iArr2 = this.authSr;
                iArr2[i6] = (output4 << i7) | iArr2[i6];
            }
        }
        this.initialised = true;
    }

    private static int len_length(int i) {
        if ((i & 255) == i) {
            return 1;
        }
        if ((65535 & i) == i) {
            return 2;
        }
        return (16777215 & i) == i ? 3 : 4;
    }

    private void reset(boolean z) {
        if (z) {
            this.mac = null;
        }
        this.aadData.reset();
        this.aadFinished = false;
        setKey(this.workingKey, this.workingIV);
        initGrain();
    }

    private void setKey(byte[] bArr, byte[] bArr2) {
        bArr2[12] = -1;
        bArr2[13] = -1;
        bArr2[14] = -1;
        bArr2[15] = Byte.MAX_VALUE;
        this.workingKey = bArr;
        this.workingIV = bArr2;
        Pack.littleEndianToInt(bArr, 0, this.nfsr);
        Pack.littleEndianToInt(this.workingIV, 0, this.lfsr);
    }

    private int[] shift(int[] iArr, int i) {
        int i2 = iArr[1];
        iArr[0] = (iArr[0] >>> 1) | (i2 << 31);
        int i3 = i2 >>> 1;
        int i4 = iArr[2];
        iArr[1] = i3 | (i4 << 31);
        int i5 = iArr[3];
        iArr[2] = (i4 >>> 1) | (i5 << 31);
        iArr[3] = (i << 31) | (i5 >>> 1);
        return iArr;
    }

    public int doFinal(byte[] bArr, int i) throws IllegalStateException, InvalidCipherTextException {
        if (!this.aadFinished) {
            doProcessAADBytes(this.aadData.getBuf(), 0, this.aadData.size());
            this.aadFinished = true;
        }
        accumulate();
        byte[] intToLittleEndian = Pack.intToLittleEndian(this.authAcc);
        this.mac = intToLittleEndian;
        System.arraycopy(intToLittleEndian, 0, bArr, i, intToLittleEndian.length);
        reset(false);
        return this.mac.length;
    }

    public String getAlgorithmName() {
        return "Grain-128AEAD";
    }

    public byte[] getMac() {
        return this.mac;
    }

    public int getOutputSize(int i) {
        return i + 8;
    }

    public int getUpdateOutputSize(int i) {
        return i;
    }

    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        if (cipherParameters instanceof ParametersWithIV) {
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            byte[] iv = parametersWithIV.getIV();
            if (iv == null || iv.length != 12) {
                throw new IllegalArgumentException("Grain-128AEAD requires exactly 12 bytes of IV");
            } else if (parametersWithIV.getParameters() instanceof KeyParameter) {
                byte[] key = ((KeyParameter) parametersWithIV.getParameters()).getKey();
                if (key.length == 16) {
                    CryptoServicesRegistrar.checkConstraints(new DefaultServiceProperties(getAlgorithmName(), 128, cipherParameters, Utils.getPurpose(z)));
                    byte[] bArr = new byte[16];
                    this.workingIV = bArr;
                    this.workingKey = new byte[16];
                    this.lfsr = new int[4];
                    this.nfsr = new int[4];
                    this.authAcc = new int[2];
                    this.authSr = new int[2];
                    System.arraycopy(iv, 0, bArr, 0, iv.length);
                    System.arraycopy(key, 0, this.workingKey, 0, key.length);
                    reset();
                    return;
                }
                throw new IllegalArgumentException("Grain-128AEAD key must be 128 bits long");
            } else {
                throw new IllegalArgumentException("Grain-128AEAD init parameters must include a key");
            }
        } else {
            throw new IllegalArgumentException("Grain-128AEAD init parameters must include an IV");
        }
    }

    public void processAADByte(byte b) {
        if (!this.aadFinished) {
            this.aadData.write(b);
            return;
        }
        throw new IllegalStateException("associated data must be added before plaintext/ciphertext");
    }

    public void processAADBytes(byte[] bArr, int i, int i2) {
        if (!this.aadFinished) {
            this.aadData.write(bArr, i, i2);
            return;
        }
        throw new IllegalStateException("associated data must be added before plaintext/ciphertext");
    }

    public int processByte(byte b, byte[] bArr, int i) throws DataLengthException {
        return processBytes(new byte[]{b}, 0, 1, bArr, i);
    }

    public int processBytes(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws DataLengthException {
        if (this.initialised) {
            if (!this.aadFinished) {
                doProcessAADBytes(this.aadData.getBuf(), 0, this.aadData.size());
                this.aadFinished = true;
            }
            if (i + i2 > bArr.length) {
                throw new DataLengthException("input buffer too short");
            } else if (i3 + i2 <= bArr2.length) {
                getKeyStream(bArr, i, i2, bArr2, i3);
                return i2;
            } else {
                throw new OutputLengthException("output buffer too short");
            }
        } else {
            throw new IllegalStateException(getAlgorithmName() + " not initialised");
        }
    }

    public void reset() {
        reset(true);
    }
}
