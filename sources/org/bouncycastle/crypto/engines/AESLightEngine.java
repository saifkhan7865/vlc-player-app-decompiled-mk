package org.bouncycastle.crypto.engines;

import com.google.common.base.Ascii;
import com.google.common.primitives.SignedBytes;
import io.netty.handler.codec.http.HttpConstants;
import java.lang.reflect.Array;
import okio.Utf8;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.constraints.DefaultServiceProperties;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.signers.PSSSigner;
import org.bouncycastle.util.Pack;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;

public class AESLightEngine implements BlockCipher {
    private static final int BLOCK_SIZE = 16;
    private static final byte[] S = {99, 124, 119, 123, -14, 107, 111, -59, 48, 1, 103, 43, -2, -41, -85, 118, -54, -126, -55, 125, -6, 89, 71, -16, -83, -44, -94, -81, -100, -92, 114, -64, -73, -3, -109, 38, 54, Utf8.REPLACEMENT_BYTE, -9, -52, 52, -91, -27, -15, 113, -40, 49, Ascii.NAK, 4, -57, 35, -61, Ascii.CAN, -106, 5, -102, 7, Ascii.DC2, Byte.MIN_VALUE, -30, -21, 39, -78, 117, 9, -125, HttpConstants.COMMA, Ascii.SUB, Ascii.ESC, 110, 90, -96, 82, HttpConstants.SEMICOLON, -42, -77, 41, -29, 47, -124, 83, -47, 0, -19, 32, -4, -79, 91, 106, -53, -66, 57, 74, 76, 88, -49, -48, -17, -86, -5, 67, 77, 51, -123, 69, -7, 2, Byte.MAX_VALUE, 80, 60, -97, -88, 81, -93, SignedBytes.MAX_POWER_OF_TWO, -113, -110, -99, 56, -11, PSSSigner.TRAILER_IMPLICIT, -74, -38, 33, Ascii.DLE, -1, -13, -46, -51, Ascii.FF, 19, -20, 95, -105, 68, Ascii.ETB, -60, -89, 126, 61, 100, 93, Ascii.EM, 115, 96, -127, 79, -36, HttpConstants.DOUBLE_QUOTE, 42, -112, -120, 70, -18, -72, Ascii.DC4, -34, 94, Ascii.VT, -37, -32, 50, HttpConstants.COLON, 10, 73, 6, 36, 92, -62, -45, -84, 98, -111, -107, -28, 121, -25, -56, 55, 109, -115, -43, 78, -87, 108, 86, -12, -22, 101, 122, -82, 8, -70, 120, 37, 46, Ascii.FS, -90, -76, -58, -24, -35, 116, Ascii.US, 75, -67, -117, -118, 112, 62, -75, 102, 72, 3, -10, Ascii.SO, 97, 53, 87, -71, -122, -63, Ascii.GS, -98, -31, -8, -104, 17, 105, -39, -114, -108, -101, Ascii.RS, -121, -23, -50, 85, 40, -33, -116, -95, -119, 13, -65, -26, 66, 104, 65, -103, 45, Ascii.SI, -80, 84, -69, Ascii.SYN};
    private static final byte[] Si = {82, 9, 106, -43, 48, 54, -91, 56, -65, SignedBytes.MAX_POWER_OF_TWO, -93, -98, -127, -13, -41, -5, 124, -29, 57, -126, -101, 47, -1, -121, 52, -114, 67, 68, -60, -34, -23, -53, 84, 123, -108, 50, -90, -62, 35, 61, -18, 76, -107, Ascii.VT, 66, -6, -61, 78, 8, 46, -95, 102, 40, -39, 36, -78, 118, 91, -94, 73, 109, -117, -47, 37, 114, -8, -10, 100, -122, 104, -104, Ascii.SYN, -44, -92, 92, -52, 93, 101, -74, -110, 108, 112, 72, 80, -3, -19, -71, -38, 94, Ascii.NAK, 70, 87, -89, -115, -99, -124, -112, -40, -85, 0, -116, PSSSigner.TRAILER_IMPLICIT, -45, 10, -9, -28, 88, 5, -72, -77, 69, 6, -48, HttpConstants.COMMA, Ascii.RS, -113, -54, Utf8.REPLACEMENT_BYTE, Ascii.SI, 2, -63, -81, -67, 3, 1, 19, -118, 107, HttpConstants.COLON, -111, 17, 65, 79, 103, -36, -22, -105, -14, -49, -50, -16, -76, -26, 115, -106, -84, 116, HttpConstants.DOUBLE_QUOTE, -25, -83, 53, -123, -30, -7, 55, -24, Ascii.FS, 117, -33, 110, 71, -15, Ascii.SUB, 113, Ascii.GS, 41, -59, -119, 111, -73, 98, Ascii.SO, -86, Ascii.CAN, -66, Ascii.ESC, -4, 86, 62, 75, -58, -46, 121, 32, -102, -37, -64, -2, 120, -51, 90, -12, Ascii.US, -35, -88, 51, -120, 7, -57, 49, -79, Ascii.DC2, Ascii.DLE, 89, 39, Byte.MIN_VALUE, -20, 95, 96, 81, Byte.MAX_VALUE, -87, Ascii.EM, -75, 74, 13, 45, -27, 122, -97, -109, -55, -100, -17, -96, -32, HttpConstants.SEMICOLON, 77, -82, 42, -11, -80, -56, -21, -69, 60, -125, 83, -103, 97, Ascii.ETB, 43, 4, 126, -70, 119, -42, 38, -31, 105, Ascii.DC4, 99, 85, 33, Ascii.FF, 125};
    private static final int m1 = -2139062144;
    private static final int m2 = 2139062143;
    private static final int m3 = 27;
    private static final int m4 = -1061109568;
    private static final int m5 = 1061109567;
    private static final int[] rcon = {1, 2, 4, 8, 16, 32, 64, 128, 27, 54, 108, 216, 171, 77, 154, 47, 94, 188, 99, 198, MediaWrapper.META_GAIN, 53, 106, 212, 179, 125, 250, 239, 197, 145};
    private int ROUNDS;
    private int[][] WorkingKey = null;
    private boolean forEncryption;

    public AESLightEngine() {
        int[][] iArr = null;
        CryptoServicesRegistrar.checkConstraints(new DefaultServiceProperties(getAlgorithmName(), bitsOfSecurity()));
    }

    private static int FFmulX(int i) {
        return (((i & m1) >>> 7) * 27) ^ ((m2 & i) << 1);
    }

    private static int FFmulX2(int i) {
        int i2 = i & m4;
        int i3 = i2 ^ (i2 >>> 1);
        return (i3 >>> 5) ^ (((m5 & i) << 2) ^ (i3 >>> 2));
    }

    private int bitsOfSecurity() {
        int[][] iArr = this.WorkingKey;
        if (iArr == null) {
            return 256;
        }
        return (iArr.length - 7) << 5;
    }

    private void decryptBlock(byte[] bArr, int i, byte[] bArr2, int i2, int[][] iArr) {
        byte[] bArr3 = bArr;
        byte[] bArr4 = bArr2;
        int i3 = i2;
        int littleEndianToInt = Pack.littleEndianToInt(bArr, i);
        int littleEndianToInt2 = Pack.littleEndianToInt(bArr3, i + 4);
        int littleEndianToInt3 = Pack.littleEndianToInt(bArr3, i + 8);
        int littleEndianToInt4 = Pack.littleEndianToInt(bArr3, i + 12);
        int i4 = this.ROUNDS;
        int[] iArr2 = iArr[i4];
        char c = 0;
        int i5 = littleEndianToInt ^ iArr2[0];
        int i6 = 1;
        int i7 = littleEndianToInt2 ^ iArr2[1];
        int i8 = littleEndianToInt3 ^ iArr2[2];
        int i9 = i4 - 1;
        int i10 = littleEndianToInt4 ^ iArr2[3];
        while (true) {
            byte[] bArr5 = Si;
            if (i9 > i6) {
                int inv_mcol = inv_mcol((((bArr5[i5 & 255] & 255) ^ ((bArr5[(i10 >> 8) & 255] & 255) << 8)) ^ ((bArr5[(i8 >> 16) & 255] & 255) << Ascii.DLE)) ^ (bArr5[(i7 >> 24) & 255] << Ascii.CAN)) ^ iArr[i9][c];
                int inv_mcol2 = inv_mcol((((bArr5[i7 & 255] & 255) ^ ((bArr5[(i5 >> 8) & 255] & 255) << 8)) ^ ((bArr5[(i10 >> 16) & 255] & 255) << Ascii.DLE)) ^ (bArr5[(i8 >> 24) & 255] << Ascii.CAN)) ^ iArr[i9][i6];
                int inv_mcol3 = inv_mcol(((((bArr5[(i7 >> 8) & 255] & 255) << 8) ^ (bArr5[i8 & 255] & 255)) ^ ((bArr5[(i5 >> 16) & 255] & 255) << Ascii.DLE)) ^ (bArr5[(i10 >> 24) & 255] << Ascii.CAN)) ^ iArr[i9][2];
                int i11 = i9 - 1;
                int inv_mcol4 = inv_mcol((((bArr5[i10 & 255] & 255) ^ ((bArr5[(i8 >> 8) & 255] & 255) << 8)) ^ ((bArr5[(i7 >> 16) & 255] & 255) << Ascii.DLE)) ^ (bArr5[(i5 >> 24) & 255] << Ascii.CAN)) ^ iArr[i9][3];
                int inv_mcol5 = inv_mcol((((bArr5[inv_mcol & 255] & 255) ^ ((bArr5[(inv_mcol4 >> 8) & 255] & 255) << 8)) ^ ((bArr5[(inv_mcol3 >> 16) & 255] & 255) << Ascii.DLE)) ^ (bArr5[(inv_mcol2 >> 24) & 255] << Ascii.CAN)) ^ iArr[i11][c];
                int inv_mcol6 = inv_mcol((((bArr5[inv_mcol2 & 255] & 255) ^ ((bArr5[(inv_mcol >> 8) & 255] & 255) << 8)) ^ ((bArr5[(inv_mcol4 >> 16) & 255] & 255) << Ascii.DLE)) ^ (bArr5[(inv_mcol3 >> 24) & 255] << Ascii.CAN)) ^ iArr[i11][1];
                int i12 = (bArr5[(inv_mcol2 >> 8) & 255] & 255) << 8;
                i9 -= 2;
                i10 = inv_mcol((((bArr5[inv_mcol4 & 255] & 255) ^ ((bArr5[(inv_mcol3 >> 8) & 255] & 255) << 8)) ^ ((bArr5[(inv_mcol2 >> 16) & 255] & 255) << Ascii.DLE)) ^ (bArr5[(inv_mcol >> 24) & 255] << Ascii.CAN)) ^ iArr[i11][3];
                i5 = inv_mcol5;
                i7 = inv_mcol6;
                i8 = inv_mcol(((i12 ^ (bArr5[inv_mcol3 & 255] & 255)) ^ ((bArr5[(inv_mcol >> 16) & 255] & 255) << Ascii.DLE)) ^ (bArr5[(inv_mcol4 >> 24) & 255] << Ascii.CAN)) ^ iArr[i11][2];
                c = 0;
                i6 = 1;
            } else {
                int inv_mcol7 = inv_mcol((((bArr5[i5 & 255] & 255) ^ ((bArr5[(i10 >> 8) & 255] & 255) << 8)) ^ ((bArr5[(i8 >> 16) & 255] & 255) << Ascii.DLE)) ^ (bArr5[(i7 >> 24) & 255] << Ascii.CAN)) ^ iArr[i9][0];
                int inv_mcol8 = inv_mcol((((bArr5[i7 & 255] & 255) ^ ((bArr5[(i5 >> 8) & 255] & 255) << 8)) ^ ((bArr5[(i10 >> 16) & 255] & 255) << Ascii.DLE)) ^ (bArr5[(i8 >> 24) & 255] << Ascii.CAN)) ^ iArr[i9][1];
                int inv_mcol9 = inv_mcol((((bArr5[i8 & 255] & 255) ^ ((bArr5[(i7 >> 8) & 255] & 255) << 8)) ^ ((bArr5[(i5 >> 16) & 255] & 255) << Ascii.DLE)) ^ (bArr5[(i10 >> 24) & 255] << Ascii.CAN)) ^ iArr[i9][2];
                int inv_mcol10 = inv_mcol((((bArr5[i10 & 255] & 255) ^ ((bArr5[(i8 >> 8) & 255] & 255) << 8)) ^ ((bArr5[(i7 >> 16) & 255] & 255) << Ascii.DLE)) ^ (bArr5[(i5 >> 24) & 255] << Ascii.CAN)) ^ iArr[i9][3];
                int[] iArr3 = iArr[0];
                byte b = ((((bArr5[inv_mcol7 & 255] & 255) ^ ((bArr5[(inv_mcol10 >> 8) & 255] & 255) << 8)) ^ ((bArr5[(inv_mcol9 >> 16) & 255] & 255) << Ascii.DLE)) ^ (bArr5[(inv_mcol8 >> 24) & 255] << Ascii.CAN)) ^ iArr3[0];
                byte b2 = ((((bArr5[inv_mcol8 & 255] & 255) ^ ((bArr5[(inv_mcol7 >> 8) & 255] & 255) << 8)) ^ ((bArr5[(inv_mcol10 >> 16) & 255] & 255) << Ascii.DLE)) ^ (bArr5[(inv_mcol9 >> 24) & 255] << Ascii.CAN)) ^ iArr3[1];
                byte b3 = ((((bArr5[inv_mcol9 & 255] & 255) ^ ((bArr5[(inv_mcol8 >> 8) & 255] & 255) << 8)) ^ ((bArr5[(inv_mcol7 >> 16) & 255] & 255) << Ascii.DLE)) ^ (bArr5[(inv_mcol10 >> 24) & 255] << Ascii.CAN)) ^ iArr3[2];
                Pack.intToLittleEndian((int) b, bArr4, i3);
                Pack.intToLittleEndian((int) b2, bArr4, i3 + 4);
                Pack.intToLittleEndian((int) b3, bArr4, i3 + 8);
                Pack.intToLittleEndian((int) ((((bArr5[inv_mcol10 & 255] & 255) ^ ((bArr5[(inv_mcol9 >> 8) & 255] & 255) << 8)) ^ ((bArr5[(inv_mcol8 >> 16) & 255] & 255) << Ascii.DLE)) ^ (bArr5[(inv_mcol7 >> 24) & 255] << Ascii.CAN)) ^ iArr3[3], bArr4, i3 + 12);
                return;
            }
        }
    }

    private void encryptBlock(byte[] bArr, int i, byte[] bArr2, int i2, int[][] iArr) {
        byte[] bArr3 = bArr;
        byte[] bArr4 = bArr2;
        int i3 = i2;
        int littleEndianToInt = Pack.littleEndianToInt(bArr, i);
        int littleEndianToInt2 = Pack.littleEndianToInt(bArr3, i + 4);
        int littleEndianToInt3 = Pack.littleEndianToInt(bArr3, i + 8);
        int littleEndianToInt4 = Pack.littleEndianToInt(bArr3, i + 12);
        char c = 0;
        int[] iArr2 = iArr[0];
        int i4 = littleEndianToInt ^ iArr2[0];
        int i5 = littleEndianToInt2 ^ iArr2[1];
        int i6 = littleEndianToInt3 ^ iArr2[2];
        int i7 = littleEndianToInt4 ^ iArr2[3];
        int i8 = 1;
        for (int i9 = 1; i8 < this.ROUNDS - i9; i9 = 1) {
            byte[] bArr5 = S;
            int mcol = mcol((((bArr5[i4 & 255] & 255) ^ ((bArr5[(i5 >> 8) & 255] & 255) << 8)) ^ ((bArr5[(i6 >> 16) & 255] & 255) << Ascii.DLE)) ^ (bArr5[(i7 >> 24) & 255] << Ascii.CAN)) ^ iArr[i8][c];
            int mcol2 = mcol((((bArr5[i5 & 255] & 255) ^ ((bArr5[(i6 >> 8) & 255] & 255) << 8)) ^ ((bArr5[(i7 >> 16) & 255] & 255) << Ascii.DLE)) ^ (bArr5[(i4 >> 24) & 255] << Ascii.CAN)) ^ iArr[i8][i9];
            int mcol3 = mcol(((((bArr5[(i7 >> 8) & 255] & 255) << 8) ^ (bArr5[i6 & 255] & 255)) ^ ((bArr5[(i4 >> 16) & 255] & 255) << Ascii.DLE)) ^ (bArr5[(i5 >> 24) & 255] << Ascii.CAN)) ^ iArr[i8][2];
            int i10 = i8 + 1;
            int mcol4 = mcol((((bArr5[i7 & 255] & 255) ^ ((bArr5[(i4 >> 8) & 255] & 255) << 8)) ^ ((bArr5[(i5 >> 16) & 255] & 255) << Ascii.DLE)) ^ (bArr5[(i6 >> 24) & 255] << Ascii.CAN)) ^ iArr[i8][3];
            int mcol5 = mcol((((bArr5[mcol & 255] & 255) ^ ((bArr5[(mcol2 >> 8) & 255] & 255) << 8)) ^ ((bArr5[(mcol3 >> 16) & 255] & 255) << Ascii.DLE)) ^ (bArr5[(mcol4 >> 24) & 255] << Ascii.CAN)) ^ iArr[i10][c];
            int mcol6 = mcol((((bArr5[mcol2 & 255] & 255) ^ ((bArr5[(mcol3 >> 8) & 255] & 255) << 8)) ^ ((bArr5[(mcol4 >> 16) & 255] & 255) << Ascii.DLE)) ^ (bArr5[(mcol >> 24) & 255] << Ascii.CAN)) ^ iArr[i10][1];
            int i11 = (bArr5[(mcol4 >> 8) & 255] & 255) << 8;
            i8 += 2;
            i7 = mcol((((bArr5[mcol4 & 255] & 255) ^ ((bArr5[(mcol >> 8) & 255] & 255) << 8)) ^ ((bArr5[(mcol2 >> 16) & 255] & 255) << Ascii.DLE)) ^ (bArr5[(mcol3 >> 24) & 255] << Ascii.CAN)) ^ iArr[i10][3];
            i4 = mcol5;
            i5 = mcol6;
            i6 = mcol(((i11 ^ (bArr5[mcol3 & 255] & 255)) ^ ((bArr5[(mcol >> 16) & 255] & 255) << Ascii.DLE)) ^ (bArr5[(mcol2 >> 24) & 255] << Ascii.CAN)) ^ iArr[i10][2];
            c = 0;
        }
        byte[] bArr6 = S;
        int mcol7 = mcol((((bArr6[i4 & 255] & 255) ^ ((bArr6[(i5 >> 8) & 255] & 255) << 8)) ^ ((bArr6[(i6 >> 16) & 255] & 255) << Ascii.DLE)) ^ (bArr6[(i7 >> 24) & 255] << Ascii.CAN)) ^ iArr[i8][0];
        int mcol8 = mcol((((bArr6[i5 & 255] & 255) ^ ((bArr6[(i6 >> 8) & 255] & 255) << 8)) ^ ((bArr6[(i7 >> 16) & 255] & 255) << Ascii.DLE)) ^ (bArr6[(i4 >> 24) & 255] << Ascii.CAN)) ^ iArr[i8][1];
        int mcol9 = mcol((((bArr6[i6 & 255] & 255) ^ ((bArr6[(i7 >> 8) & 255] & 255) << 8)) ^ ((bArr6[(i4 >> 16) & 255] & 255) << Ascii.DLE)) ^ (bArr6[(i5 >> 24) & 255] << Ascii.CAN)) ^ iArr[i8][2];
        int mcol10 = mcol((((bArr6[i7 & 255] & 255) ^ ((bArr6[(i4 >> 8) & 255] & 255) << 8)) ^ ((bArr6[(i5 >> 16) & 255] & 255) << Ascii.DLE)) ^ (bArr6[(i6 >> 24) & 255] << Ascii.CAN)) ^ iArr[i8][3];
        int[] iArr3 = iArr[i8 + 1];
        byte b = ((((bArr6[mcol7 & 255] & 255) ^ ((bArr6[(mcol8 >> 8) & 255] & 255) << 8)) ^ ((bArr6[(mcol9 >> 16) & 255] & 255) << Ascii.DLE)) ^ (bArr6[(mcol10 >> 24) & 255] << Ascii.CAN)) ^ iArr3[0];
        byte b2 = ((((bArr6[mcol8 & 255] & 255) ^ ((bArr6[(mcol9 >> 8) & 255] & 255) << 8)) ^ ((bArr6[(mcol10 >> 16) & 255] & 255) << Ascii.DLE)) ^ (bArr6[(mcol7 >> 24) & 255] << Ascii.CAN)) ^ iArr3[1];
        int i12 = iArr3[2];
        byte b3 = ((((bArr6[mcol10 & 255] & 255) ^ ((bArr6[(mcol7 >> 8) & 255] & 255) << 8)) ^ ((bArr6[(mcol8 >> 16) & 255] & 255) << Ascii.DLE)) ^ (bArr6[(mcol9 >> 24) & 255] << Ascii.CAN)) ^ iArr3[3];
        Pack.intToLittleEndian((int) b, bArr4, i3);
        Pack.intToLittleEndian((int) b2, bArr4, i3 + 4);
        Pack.intToLittleEndian((int) i12 ^ ((((bArr6[mcol9 & 255] & 255) ^ ((bArr6[(mcol10 >> 8) & 255] & 255) << 8)) ^ ((bArr6[(mcol7 >> 16) & 255] & 255) << Ascii.DLE)) ^ (bArr6[(mcol8 >> 24) & 255] << Ascii.CAN)), bArr4, i3 + 8);
        Pack.intToLittleEndian((int) b3, bArr4, i3 + 12);
    }

    private int[][] generateWorkingKey(byte[] bArr, boolean z) {
        byte[] bArr2 = bArr;
        int length = bArr2.length;
        if (length < 16 || length > 32 || (length & 7) != 0) {
            throw new IllegalArgumentException("Key length not 128/192/256 bits.");
        }
        int i = length >>> 2;
        this.ROUNDS = i + 6;
        int[] iArr = new int[2];
        iArr[1] = 4;
        iArr[0] = i + 7;
        int[][] iArr2 = (int[][]) Array.newInstance(Integer.TYPE, iArr);
        int i2 = 8;
        char c = 3;
        if (i == 4) {
            int littleEndianToInt = Pack.littleEndianToInt(bArr2, 0);
            iArr2[0][0] = littleEndianToInt;
            int littleEndianToInt2 = Pack.littleEndianToInt(bArr2, 4);
            iArr2[0][1] = littleEndianToInt2;
            int littleEndianToInt3 = Pack.littleEndianToInt(bArr2, 8);
            iArr2[0][2] = littleEndianToInt3;
            int littleEndianToInt4 = Pack.littleEndianToInt(bArr2, 12);
            iArr2[0][3] = littleEndianToInt4;
            for (int i3 = 1; i3 <= 10; i3++) {
                littleEndianToInt ^= subWord(shift(littleEndianToInt4, 8)) ^ rcon[i3 - 1];
                int[] iArr3 = iArr2[i3];
                iArr3[0] = littleEndianToInt;
                littleEndianToInt2 ^= littleEndianToInt;
                iArr3[1] = littleEndianToInt2;
                littleEndianToInt3 ^= littleEndianToInt2;
                iArr3[2] = littleEndianToInt3;
                littleEndianToInt4 ^= littleEndianToInt3;
                iArr3[3] = littleEndianToInt4;
            }
        } else if (i == 6) {
            int littleEndianToInt5 = Pack.littleEndianToInt(bArr2, 0);
            iArr2[0][0] = littleEndianToInt5;
            int littleEndianToInt6 = Pack.littleEndianToInt(bArr2, 4);
            iArr2[0][1] = littleEndianToInt6;
            int littleEndianToInt7 = Pack.littleEndianToInt(bArr2, 8);
            iArr2[0][2] = littleEndianToInt7;
            int littleEndianToInt8 = Pack.littleEndianToInt(bArr2, 12);
            iArr2[0][3] = littleEndianToInt8;
            int littleEndianToInt9 = Pack.littleEndianToInt(bArr2, 16);
            int littleEndianToInt10 = Pack.littleEndianToInt(bArr2, 20);
            int i4 = 1;
            int i5 = 1;
            while (true) {
                int[] iArr4 = iArr2[i4];
                iArr4[0] = littleEndianToInt9;
                iArr4[1] = littleEndianToInt10;
                int subWord = littleEndianToInt5 ^ (subWord(shift(littleEndianToInt10, 8)) ^ i5);
                int[] iArr5 = iArr2[i4];
                iArr5[2] = subWord;
                int i6 = littleEndianToInt6 ^ subWord;
                iArr5[3] = i6;
                int i7 = littleEndianToInt7 ^ i6;
                int[] iArr6 = iArr2[i4 + 1];
                iArr6[0] = i7;
                int i8 = littleEndianToInt8 ^ i7;
                iArr6[1] = i8;
                int i9 = littleEndianToInt9 ^ i8;
                iArr6[2] = i9;
                int i10 = littleEndianToInt10 ^ i9;
                iArr6[3] = i10;
                i5 <<= 2;
                littleEndianToInt5 = subWord ^ (subWord(shift(i10, 8)) ^ (i5 << 1));
                int[] iArr7 = iArr2[i4 + 2];
                iArr7[0] = littleEndianToInt5;
                littleEndianToInt6 = i6 ^ littleEndianToInt5;
                iArr7[1] = littleEndianToInt6;
                littleEndianToInt7 = i7 ^ littleEndianToInt6;
                iArr7[2] = littleEndianToInt7;
                littleEndianToInt8 = i8 ^ littleEndianToInt7;
                iArr7[3] = littleEndianToInt8;
                i4 += 3;
                if (i4 >= 13) {
                    break;
                }
                littleEndianToInt9 = i9 ^ littleEndianToInt8;
                littleEndianToInt10 = i10 ^ littleEndianToInt9;
            }
        } else if (i == 8) {
            int littleEndianToInt11 = Pack.littleEndianToInt(bArr2, 0);
            iArr2[0][0] = littleEndianToInt11;
            int littleEndianToInt12 = Pack.littleEndianToInt(bArr2, 4);
            iArr2[0][1] = littleEndianToInt12;
            int littleEndianToInt13 = Pack.littleEndianToInt(bArr2, 8);
            iArr2[0][2] = littleEndianToInt13;
            int littleEndianToInt14 = Pack.littleEndianToInt(bArr2, 12);
            iArr2[0][3] = littleEndianToInt14;
            int littleEndianToInt15 = Pack.littleEndianToInt(bArr2, 16);
            iArr2[1][0] = littleEndianToInt15;
            int littleEndianToInt16 = Pack.littleEndianToInt(bArr2, 20);
            iArr2[1][1] = littleEndianToInt16;
            int littleEndianToInt17 = Pack.littleEndianToInt(bArr2, 24);
            iArr2[1][2] = littleEndianToInt17;
            int littleEndianToInt18 = Pack.littleEndianToInt(bArr2, 28);
            iArr2[1][3] = littleEndianToInt18;
            int i11 = 1;
            int i12 = 2;
            while (true) {
                int subWord2 = subWord(shift(littleEndianToInt18, i2)) ^ i11;
                i11 <<= 1;
                littleEndianToInt11 ^= subWord2;
                int[] iArr8 = iArr2[i12];
                iArr8[0] = littleEndianToInt11;
                littleEndianToInt12 ^= littleEndianToInt11;
                iArr8[1] = littleEndianToInt12;
                littleEndianToInt13 ^= littleEndianToInt12;
                iArr8[2] = littleEndianToInt13;
                littleEndianToInt14 ^= littleEndianToInt13;
                iArr8[c] = littleEndianToInt14;
                int i13 = i12 + 1;
                if (i13 >= 15) {
                    break;
                }
                littleEndianToInt15 ^= subWord(littleEndianToInt14);
                int[] iArr9 = iArr2[i13];
                iArr9[0] = littleEndianToInt15;
                littleEndianToInt16 ^= littleEndianToInt15;
                iArr9[1] = littleEndianToInt16;
                littleEndianToInt17 ^= littleEndianToInt16;
                iArr9[2] = littleEndianToInt17;
                littleEndianToInt18 ^= littleEndianToInt17;
                iArr9[3] = littleEndianToInt18;
                i12 += 2;
                i2 = 8;
                c = 3;
            }
        } else {
            throw new IllegalStateException("Should never get here");
        }
        if (!z) {
            for (int i14 = 1; i14 < this.ROUNDS; i14++) {
                for (int i15 = 0; i15 < 4; i15++) {
                    int[] iArr10 = iArr2[i14];
                    iArr10[i15] = inv_mcol(iArr10[i15]);
                }
            }
        }
        return iArr2;
    }

    private static int inv_mcol(int i) {
        int shift = shift(i, 8) ^ i;
        int FFmulX = i ^ FFmulX(shift);
        int FFmulX2 = shift ^ FFmulX2(FFmulX);
        return FFmulX ^ (FFmulX2 ^ shift(FFmulX2, 16));
    }

    private static int mcol(int i) {
        int shift = shift(i, 8);
        int i2 = i ^ shift;
        return FFmulX(i2) ^ (shift ^ shift(i2, 16));
    }

    private static int shift(int i, int i2) {
        return (i << (-i2)) | (i >>> i2);
    }

    private static int subWord(int i) {
        byte[] bArr = S;
        return (bArr[(i >> 24) & 255] << Ascii.CAN) | (bArr[i & 255] & 255) | ((bArr[(i >> 8) & 255] & 255) << 8) | ((bArr[(i >> 16) & 255] & 255) << Ascii.DLE);
    }

    public String getAlgorithmName() {
        return "AES";
    }

    public int getBlockSize() {
        return 16;
    }

    public void init(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof KeyParameter) {
            this.WorkingKey = generateWorkingKey(((KeyParameter) cipherParameters).getKey(), z);
            this.forEncryption = z;
            CryptoServicesRegistrar.checkConstraints(new DefaultServiceProperties(getAlgorithmName(), bitsOfSecurity(), cipherParameters, Utils.getPurpose(z)));
            return;
        }
        throw new IllegalArgumentException("invalid parameter passed to AES init - " + cipherParameters.getClass().getName());
    }

    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        int[][] iArr = this.WorkingKey;
        if (iArr == null) {
            throw new IllegalStateException("AES engine not initialised");
        } else if (i > bArr.length - 16) {
            throw new DataLengthException("input buffer too short");
        } else if (i2 <= bArr2.length - 16) {
            if (this.forEncryption) {
                encryptBlock(bArr, i, bArr2, i2, iArr);
            } else {
                decryptBlock(bArr, i, bArr2, i2, iArr);
            }
            return 16;
        } else {
            throw new OutputLengthException("output buffer too short");
        }
    }

    public void reset() {
    }
}
