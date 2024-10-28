package org.bouncycastle.crypto.engines;

import com.google.common.base.Ascii;
import com.google.common.primitives.SignedBytes;
import io.netty.handler.codec.http.HttpConstants;
import okio.Utf8;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.signers.PSSSigner;
import org.bouncycastle.util.Arrays;

public class GOST3412_2015Engine implements BlockCipher {
    protected static final int BLOCK_SIZE = 16;
    private static final byte[] PI = {-4, -18, -35, 17, -49, 110, 49, Ascii.SYN, -5, -60, -6, -38, 35, -59, 4, 77, -23, 119, -16, -37, -109, 46, -103, -70, Ascii.ETB, 54, -15, -69, Ascii.DC4, -51, 95, -63, -7, Ascii.CAN, 101, 90, -30, 92, -17, 33, -127, Ascii.FS, 60, 66, -117, 1, -114, 79, 5, -124, 2, -82, -29, 106, -113, -96, 6, Ascii.VT, -19, -104, Byte.MAX_VALUE, -44, -45, Ascii.US, -21, 52, HttpConstants.COMMA, 81, -22, -56, 72, -85, -14, 42, 104, -94, -3, HttpConstants.COLON, -50, -52, -75, 112, Ascii.SO, 86, 8, Ascii.FF, 118, Ascii.DC2, -65, 114, 19, 71, -100, -73, 93, -121, Ascii.NAK, -95, -106, 41, Ascii.DLE, 123, -102, -57, -13, -111, 120, 111, -99, -98, -78, -79, 50, 117, Ascii.EM, 61, -1, 53, -118, 126, 109, 84, -58, Byte.MIN_VALUE, -61, -67, 13, 87, -33, -11, 36, -87, 62, -88, 67, -55, -41, 121, -42, -10, 124, HttpConstants.DOUBLE_QUOTE, -71, 3, -32, Ascii.SI, -20, -34, 122, -108, -80, PSSSigner.TRAILER_IMPLICIT, -36, -24, 40, 80, 78, 51, 10, 74, -89, -105, 96, 115, Ascii.RS, 0, 98, 68, Ascii.SUB, -72, 56, -126, 100, -97, 38, 65, -83, 69, 70, -110, 39, 94, 85, 47, -116, -93, -91, 125, 105, -43, -107, HttpConstants.SEMICOLON, 7, 88, -77, SignedBytes.MAX_POWER_OF_TWO, -122, -84, Ascii.GS, -9, 48, 55, 107, -28, -120, -39, -25, -119, -31, Ascii.ESC, -125, 73, 76, Utf8.REPLACEMENT_BYTE, -8, -2, -115, 83, -86, -112, -54, -40, -123, 97, 32, 113, 103, -92, 45, 43, 9, 91, -53, -101, 37, -48, -66, -27, 108, 82, 89, -90, 116, -46, -26, -12, -76, -64, -47, 102, -81, -62, 57, 75, 99, -74};
    private static final byte[] inversePI = {-91, 45, 50, -113, Ascii.SO, 48, 56, -64, 84, -26, -98, 57, 85, 126, 82, -111, 100, 3, 87, 90, Ascii.FS, 96, 7, Ascii.CAN, 33, 114, -88, -47, 41, -58, -92, Utf8.REPLACEMENT_BYTE, -32, 39, -115, Ascii.FF, -126, -22, -82, -76, -102, 99, 73, -27, 66, -28, Ascii.NAK, -73, -56, 6, 112, -99, 65, 117, Ascii.EM, -55, -86, -4, 77, -65, 42, 115, -124, -43, -61, -81, 43, -122, -89, -79, -78, 91, 70, -45, -97, -3, -44, Ascii.SI, -100, 47, -101, 67, -17, -39, 121, -74, 83, Byte.MAX_VALUE, -63, -16, 35, -25, 37, 94, -75, Ascii.RS, -94, -33, -90, -2, -84, HttpConstants.DOUBLE_QUOTE, -7, -30, 74, PSSSigner.TRAILER_IMPLICIT, 53, -54, -18, 120, 5, 107, 81, -31, 89, -93, -14, 113, 86, 17, 106, -119, -108, 101, -116, -69, 119, 60, 123, 40, -85, -46, 49, -34, -60, 95, -52, -49, 118, HttpConstants.COMMA, -72, -40, 46, 54, -37, 105, -77, Ascii.DC4, -107, -66, 98, -95, HttpConstants.SEMICOLON, Ascii.SYN, 102, -23, 92, 108, 109, -83, 55, 97, 75, -71, -29, -70, -15, -96, -123, -125, -38, 71, -59, -80, 51, -6, -106, 111, 110, -62, -10, 80, -1, 93, -87, -114, Ascii.ETB, Ascii.ESC, -105, 125, -20, 88, -9, Ascii.US, -5, 124, 9, 13, 122, 103, 69, -121, -36, -24, 79, Ascii.GS, 78, 4, -21, -8, -13, 62, 61, -67, -118, -120, -35, -51, Ascii.VT, 19, -104, 2, -109, Byte.MIN_VALUE, -112, -48, 36, 52, -53, -19, -12, -50, -103, Ascii.DLE, 68, SignedBytes.MAX_POWER_OF_TWO, -110, HttpConstants.COLON, 1, 38, Ascii.DC2, Ascii.SUB, 72, 104, -11, -127, -117, -57, -42, 32, 10, 8, 0, 76, -41, 116};
    private int KEY_LENGTH = 32;
    private int SUB_LENGTH = (32 / 2);
    private byte[][] _gf_mul;
    private boolean forEncryption;
    private final byte[] lFactors = {-108, 32, -123, Ascii.DLE, -62, -64, 1, -5, 1, -64, -62, Ascii.DLE, -123, 32, -108, 1};
    private byte[][] subKeys;

    public GOST3412_2015Engine() {
        byte[][] bArr = null;
        this.subKeys = null;
        this._gf_mul = init_gf256_mul_table();
    }

    private void C(byte[] bArr, int i) {
        Arrays.clear(bArr);
        bArr[15] = (byte) i;
        L(bArr);
    }

    private void F(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        byte[] LSX = LSX(bArr, bArr2);
        X(LSX, bArr3);
        System.arraycopy(bArr2, 0, bArr3, 0, this.SUB_LENGTH);
        System.arraycopy(LSX, 0, bArr2, 0, this.SUB_LENGTH);
    }

    private void GOST3412_2015Func(byte[] bArr, int i, byte[] bArr2, int i2) {
        byte[][] bArr3;
        byte[] bArr4 = new byte[16];
        System.arraycopy(bArr, i, bArr4, 0, 16);
        int i3 = 9;
        if (this.forEncryption) {
            for (int i4 = 0; i4 < 9; i4++) {
                bArr4 = Arrays.copyOf(LSX(this.subKeys[i4], bArr4), 16);
            }
            X(bArr4, this.subKeys[9]);
        } else {
            while (true) {
                bArr3 = this.subKeys;
                if (i3 <= 0) {
                    break;
                }
                bArr4 = Arrays.copyOf(XSL(bArr3[i3], bArr4), 16);
                i3--;
            }
            X(bArr4, bArr3[0]);
        }
        System.arraycopy(bArr4, 0, bArr2, i2, 16);
    }

    private void L(byte[] bArr) {
        for (int i = 0; i < 16; i++) {
            R(bArr);
        }
    }

    private byte[] LSX(byte[] bArr, byte[] bArr2) {
        byte[] copyOf = Arrays.copyOf(bArr, bArr.length);
        X(copyOf, bArr2);
        S(copyOf);
        L(copyOf);
        return copyOf;
    }

    private void R(byte[] bArr) {
        byte l = l(bArr);
        System.arraycopy(bArr, 0, bArr, 1, 15);
        bArr[0] = l;
    }

    private void S(byte[] bArr) {
        for (int i = 0; i < bArr.length; i++) {
            bArr[i] = PI[unsignedByte(bArr[i])];
        }
    }

    private void X(byte[] bArr, byte[] bArr2) {
        for (int i = 0; i < bArr.length; i++) {
            bArr[i] = (byte) (bArr[i] ^ bArr2[i]);
        }
    }

    private byte[] XSL(byte[] bArr, byte[] bArr2) {
        byte[] copyOf = Arrays.copyOf(bArr, bArr.length);
        X(copyOf, bArr2);
        inverseL(copyOf);
        inverseS(copyOf);
        return copyOf;
    }

    private void generateSubKeys(byte[] bArr) {
        int i;
        if (bArr.length == this.KEY_LENGTH) {
            this.subKeys = new byte[10][];
            for (int i2 = 0; i2 < 10; i2++) {
                this.subKeys[i2] = new byte[this.SUB_LENGTH];
            }
            int i3 = this.SUB_LENGTH;
            byte[] bArr2 = new byte[i3];
            byte[] bArr3 = new byte[i3];
            int i4 = 0;
            while (true) {
                i = this.SUB_LENGTH;
                if (i4 >= i) {
                    break;
                }
                byte[][] bArr4 = this.subKeys;
                byte[] bArr5 = bArr4[0];
                byte b = bArr[i4];
                bArr2[i4] = b;
                bArr5[i4] = b;
                byte[] bArr6 = bArr4[1];
                byte b2 = bArr[i + i4];
                bArr3[i4] = b2;
                bArr6[i4] = b2;
                i4++;
            }
            byte[] bArr7 = new byte[i];
            for (int i5 = 1; i5 < 5; i5++) {
                for (int i6 = 1; i6 <= 8; i6++) {
                    C(bArr7, ((i5 - 1) * 8) + i6);
                    F(bArr7, bArr2, bArr3);
                }
                int i7 = i5 * 2;
                System.arraycopy(bArr2, 0, this.subKeys[i7], 0, this.SUB_LENGTH);
                System.arraycopy(bArr3, 0, this.subKeys[i7 + 1], 0, this.SUB_LENGTH);
            }
            return;
        }
        throw new IllegalArgumentException("Key length invalid. Key needs to be 32 byte - 256 bit!!!");
    }

    private static byte[][] init_gf256_mul_table() {
        byte[][] bArr = new byte[256][];
        for (int i = 0; i < 256; i++) {
            bArr[i] = new byte[256];
            for (int i2 = 0; i2 < 256; i2++) {
                bArr[i][i2] = kuz_mul_gf256_slow((byte) i, (byte) i2);
            }
        }
        return bArr;
    }

    private void inverseL(byte[] bArr) {
        for (int i = 0; i < 16; i++) {
            inverseR(bArr);
        }
    }

    private void inverseR(byte[] bArr) {
        byte[] bArr2 = new byte[16];
        System.arraycopy(bArr, 1, bArr2, 0, 15);
        bArr2[15] = bArr[0];
        byte l = l(bArr2);
        System.arraycopy(bArr, 1, bArr, 0, 15);
        bArr[15] = l;
    }

    private void inverseS(byte[] bArr) {
        for (int i = 0; i < bArr.length; i++) {
            bArr[i] = inversePI[unsignedByte(bArr[i])];
        }
    }

    private static byte kuz_mul_gf256_slow(byte b, byte b2) {
        byte b3 = 0;
        for (byte b4 = 0; b4 < 8 && b != 0 && b2 != 0; b4 = (byte) (b4 + 1)) {
            if ((b2 & 1) != 0) {
                b3 = (byte) (b3 ^ b);
            }
            byte b5 = (byte) (b & 128);
            b = (byte) (b << 1);
            if (b5 != 0) {
                b = (byte) (b ^ 195);
            }
            b2 = (byte) (b2 >> 1);
        }
        return b3;
    }

    private byte l(byte[] bArr) {
        byte b = bArr[15];
        for (int i = 14; i >= 0; i--) {
            b = (byte) (b ^ this._gf_mul[unsignedByte(bArr[i])][unsignedByte(this.lFactors[i])]);
        }
        return b;
    }

    private int unsignedByte(byte b) {
        return b & 255;
    }

    public String getAlgorithmName() {
        return "GOST3412_2015";
    }

    public int getBlockSize() {
        return 16;
    }

    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        if (cipherParameters instanceof KeyParameter) {
            this.forEncryption = z;
            generateSubKeys(((KeyParameter) cipherParameters).getKey());
        } else if (cipherParameters != null) {
            throw new IllegalArgumentException("invalid parameter passed to GOST3412_2015 init - " + cipherParameters.getClass().getName());
        }
    }

    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) throws DataLengthException, IllegalStateException {
        if (this.subKeys == null) {
            throw new IllegalStateException("GOST3412_2015 engine not initialised");
        } else if (i + 16 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        } else if (i2 + 16 <= bArr2.length) {
            GOST3412_2015Func(bArr, i, bArr2, i2);
            return 16;
        } else {
            throw new OutputLengthException("output buffer too short");
        }
    }

    public void reset() {
    }
}
