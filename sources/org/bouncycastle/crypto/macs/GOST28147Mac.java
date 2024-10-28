package org.bouncycastle.crypto.macs;

import com.google.common.base.Ascii;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoServicePurpose;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.constraints.DefaultServiceProperties;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.crypto.params.ParametersWithSBox;
import org.bouncycastle.util.Pack;

public class GOST28147Mac implements Mac {
    private static final int BLOCK_SIZE = 8;
    private static final int MAC_SIZE = 4;
    private byte[] S;
    private byte[] buf;
    private int bufOff;
    private boolean firstStep;
    private byte[] mac;
    private byte[] macIV;
    private final CryptoServicePurpose purpose;
    private int[] workingKey;

    public GOST28147Mac() {
        this(CryptoServicePurpose.AUTHENTICATION);
    }

    public GOST28147Mac(CryptoServicePurpose cryptoServicePurpose) {
        this.firstStep = true;
        this.workingKey = null;
        this.macIV = null;
        this.S = new byte[]{9, 6, 3, 2, 8, Ascii.VT, 1, 7, 10, 4, Ascii.SO, Ascii.SI, Ascii.FF, 0, 13, 5, 3, 7, Ascii.SO, 9, 8, 10, Ascii.SI, 0, 5, 2, 6, Ascii.FF, Ascii.VT, 4, 13, 1, Ascii.SO, 4, 6, 2, Ascii.VT, 3, 13, 8, Ascii.FF, Ascii.SI, 5, 10, 0, 7, 1, 9, Ascii.SO, 7, 10, Ascii.FF, 13, 1, 3, 9, 0, 2, Ascii.VT, 4, Ascii.SI, 8, 5, 6, Ascii.VT, 5, 1, 9, 8, 13, Ascii.SI, 0, Ascii.SO, 4, 2, 3, Ascii.FF, 7, 10, 6, 3, 10, 13, Ascii.FF, 1, 2, 0, Ascii.VT, 7, 5, 9, 4, 8, Ascii.SI, Ascii.SO, 6, 1, 13, 2, 9, 7, 10, 6, 0, 8, Ascii.FF, 4, 5, Ascii.SI, 3, Ascii.VT, Ascii.SO, Ascii.VT, 10, Ascii.SI, 5, 0, Ascii.FF, Ascii.SO, 8, 6, 2, 3, 9, 1, 7, 13, 4};
        this.purpose = cryptoServicePurpose;
        this.mac = new byte[8];
        this.buf = new byte[8];
        this.bufOff = 0;
    }

    private static void CM5func(byte[] bArr, int i, byte[] bArr2, byte[] bArr3) {
        for (int i2 = 0; i2 < 8; i2++) {
            bArr3[i2] = (byte) (bArr[i + i2] ^ bArr2[i2]);
        }
    }

    private int[] generateWorkingKey(byte[] bArr) {
        if (bArr.length == 32) {
            int[] iArr = new int[8];
            for (int i = 0; i != 8; i++) {
                iArr[i] = Pack.littleEndianToInt(bArr, i * 4);
            }
            return iArr;
        }
        throw new IllegalArgumentException("Key length invalid. Key needs to be 32 byte - 256 bit!!!");
    }

    private void gost28147MacFunc(int[] iArr, byte[] bArr, int i, byte[] bArr2, int i2) {
        int littleEndianToInt = Pack.littleEndianToInt(bArr, i);
        int littleEndianToInt2 = Pack.littleEndianToInt(bArr, i + 4);
        for (int i3 = 0; i3 < 2; i3++) {
            int i4 = 0;
            while (i4 < 8) {
                i4++;
                int i5 = littleEndianToInt;
                littleEndianToInt = littleEndianToInt2 ^ gost28147_mainStep(littleEndianToInt, iArr[i4]);
                littleEndianToInt2 = i5;
            }
        }
        Pack.intToLittleEndian(littleEndianToInt, bArr2, i2);
        Pack.intToLittleEndian(littleEndianToInt2, bArr2, i2 + 4);
    }

    private int gost28147_mainStep(int i, int i2) {
        int i3 = i2 + i;
        byte[] bArr = this.S;
        int i4 = bArr[i3 & 15] + (bArr[((i3 >> 4) & 15) + 16] << 4) + (bArr[((i3 >> 8) & 15) + 32] << 8) + (bArr[((i3 >> 12) & 15) + 48] << Ascii.FF) + (bArr[((i3 >> 16) & 15) + 64] << Ascii.DLE) + (bArr[((i3 >> 20) & 15) + 80] << Ascii.DC4) + (bArr[((i3 >> 24) & 15) + 96] << Ascii.CAN) + (bArr[((i3 >> 28) & 15) + 112] << Ascii.FS);
        return (i4 << 11) | (i4 >>> 21);
    }

    private void recursiveInit(CipherParameters cipherParameters) throws IllegalArgumentException {
        CipherParameters cipherParameters2;
        if (cipherParameters != null) {
            if (cipherParameters instanceof ParametersWithSBox) {
                ParametersWithSBox parametersWithSBox = (ParametersWithSBox) cipherParameters;
                System.arraycopy(parametersWithSBox.getSBox(), 0, this.S, 0, parametersWithSBox.getSBox().length);
                cipherParameters2 = parametersWithSBox.getParameters();
            } else if (cipherParameters instanceof KeyParameter) {
                this.workingKey = generateWorkingKey(((KeyParameter) cipherParameters).getKey());
                cipherParameters2 = null;
            } else if (cipherParameters instanceof ParametersWithIV) {
                ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
                byte[] iv = parametersWithIV.getIV();
                byte[] bArr = this.mac;
                System.arraycopy(iv, 0, bArr, 0, bArr.length);
                this.macIV = parametersWithIV.getIV();
                cipherParameters2 = parametersWithIV.getParameters();
            } else {
                throw new IllegalArgumentException("invalid parameter passed to GOST28147 init - " + cipherParameters.getClass().getName());
            }
            recursiveInit(cipherParameters2);
        }
    }

    public int doFinal(byte[] bArr, int i) throws DataLengthException, IllegalStateException {
        while (true) {
            int i2 = this.bufOff;
            if (i2 >= 8) {
                break;
            }
            this.buf[i2] = 0;
            this.bufOff = i2 + 1;
        }
        byte[] bArr2 = this.buf;
        byte[] bArr3 = new byte[bArr2.length];
        if (this.firstStep) {
            this.firstStep = false;
            System.arraycopy(bArr2, 0, bArr3, 0, this.mac.length);
        } else {
            CM5func(bArr2, 0, this.mac, bArr3);
        }
        gost28147MacFunc(this.workingKey, bArr3, 0, this.mac, 0);
        byte[] bArr4 = this.mac;
        System.arraycopy(bArr4, (bArr4.length / 2) - 4, bArr, i, 4);
        reset();
        return 4;
    }

    public String getAlgorithmName() {
        return "GOST28147Mac";
    }

    public int getMacSize() {
        return 4;
    }

    public void init(CipherParameters cipherParameters) throws IllegalArgumentException {
        reset();
        this.buf = new byte[8];
        this.macIV = null;
        recursiveInit(cipherParameters);
        CryptoServicesRegistrar.checkConstraints(new DefaultServiceProperties(getAlgorithmName(), 178, cipherParameters, this.purpose));
    }

    public void reset() {
        int i = 0;
        while (true) {
            byte[] bArr = this.buf;
            if (i < bArr.length) {
                bArr[i] = 0;
                i++;
            } else {
                this.bufOff = 0;
                this.firstStep = true;
                return;
            }
        }
    }

    public void update(byte b) throws IllegalStateException {
        int i = this.bufOff;
        byte[] bArr = this.buf;
        if (i == bArr.length) {
            byte[] bArr2 = new byte[bArr.length];
            if (this.firstStep) {
                this.firstStep = false;
                byte[] bArr3 = this.macIV;
                if (bArr3 != null) {
                    CM5func(bArr, 0, bArr3, bArr2);
                } else {
                    System.arraycopy(bArr, 0, bArr2, 0, this.mac.length);
                }
            } else {
                CM5func(bArr, 0, this.mac, bArr2);
            }
            gost28147MacFunc(this.workingKey, bArr2, 0, this.mac, 0);
            this.bufOff = 0;
        }
        byte[] bArr4 = this.buf;
        int i2 = this.bufOff;
        this.bufOff = i2 + 1;
        bArr4[i2] = b;
    }

    public void update(byte[] bArr, int i, int i2) throws DataLengthException, IllegalStateException {
        if (i2 >= 0) {
            int i3 = this.bufOff;
            int i4 = 8 - i3;
            if (i2 > i4) {
                System.arraycopy(bArr, i, this.buf, i3, i4);
                byte[] bArr2 = this.buf;
                byte[] bArr3 = new byte[bArr2.length];
                if (this.firstStep) {
                    this.firstStep = false;
                    byte[] bArr4 = this.macIV;
                    if (bArr4 != null) {
                        CM5func(bArr2, 0, bArr4, bArr3);
                    } else {
                        System.arraycopy(bArr2, 0, bArr3, 0, this.mac.length);
                    }
                } else {
                    CM5func(bArr2, 0, this.mac, bArr3);
                }
                gost28147MacFunc(this.workingKey, bArr3, 0, this.mac, 0);
                this.bufOff = 0;
                i2 -= i4;
                i += i4;
                while (i2 > 8) {
                    CM5func(bArr, i, this.mac, bArr3);
                    gost28147MacFunc(this.workingKey, bArr3, 0, this.mac, 0);
                    i2 -= 8;
                    i += 8;
                }
            }
            System.arraycopy(bArr, i, this.buf, this.bufOff, i2);
            this.bufOff += i2;
            return;
        }
        throw new IllegalArgumentException("Can't have a negative input length!");
    }
}
