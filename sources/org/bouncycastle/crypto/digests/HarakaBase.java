package org.bouncycastle.crypto.digests;

import com.google.common.base.Ascii;
import com.google.common.primitives.SignedBytes;
import io.netty.handler.codec.http.HttpConstants;
import okio.Utf8;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.signers.PSSSigner;
import org.bouncycastle.util.Bytes;

public abstract class HarakaBase implements Digest {
    protected static final int DIGEST_SIZE = 32;
    static final byte[][] RC = {new byte[]{-99, 123, -127, 117, -16, -2, -59, -78, 10, -64, 32, -26, 76, 112, -124, 6}, new byte[]{Ascii.ETB, -9, 8, 47, -92, 107, Ascii.SI, 100, 107, -96, -13, -120, -31, -76, 102, -117}, new byte[]{Ascii.DC4, -111, 2, -97, 96, -99, 2, -49, -104, -124, -14, 83, 45, -34, 2, 52}, new byte[]{121, 79, 91, -3, -81, PSSSigner.TRAILER_IMPLICIT, -13, -69, 8, 79, 123, 46, -26, -22, -42, Ascii.SO}, new byte[]{68, 112, 57, -66, Ascii.FS, -51, -18, 121, -117, 68, 114, 72, -53, -80, -49, -53}, new byte[]{123, 5, -118, 43, -19, 53, 83, -115, -73, 50, -112, 110, -18, -51, -22, 126}, new byte[]{Ascii.ESC, -17, 79, -38, 97, 39, 65, -30, -48, 124, 46, 94, 67, -113, -62, 103}, new byte[]{HttpConstants.SEMICOLON, Ascii.VT, -57, Ascii.US, -30, -3, 95, 103, 7, -52, -54, -81, -80, -39, 36, 41}, new byte[]{-18, 101, -44, -71, -54, -113, -37, -20, -23, Byte.MAX_VALUE, -122, -26, -15, 99, 77, -85}, new byte[]{51, 126, 3, -83, 79, SignedBytes.MAX_POWER_OF_TWO, 42, 91, 100, -51, -73, -44, -124, -65, 48, Ascii.FS}, new byte[]{0, -104, -10, -115, 46, -117, 2, 105, -65, 35, Ascii.ETB, -108, -71, Ascii.VT, -52, -78}, new byte[]{-118, 45, -99, 92, -56, -98, -86, 74, 114, 85, 111, -34, -90, 120, 4, -6}, new byte[]{-44, -97, Ascii.DC2, 41, 46, 79, -6, Ascii.SO, Ascii.DC2, 42, 119, 107, 43, -97, -76, -33}, new byte[]{-18, Ascii.DC2, 106, -69, -82, 17, -42, 50, 54, -94, 73, -12, 68, 3, -95, Ascii.RS}, new byte[]{-90, -20, -88, -100, -55, 0, -106, 95, -124, 0, 5, 75, -120, 73, 4, -81}, new byte[]{-20, -109, -27, 39, -29, -57, -94, 120, 79, -100, Ascii.EM, -99, -40, 94, 2, 33}, new byte[]{115, 1, -44, -126, -51, 46, 40, -71, -73, -55, 89, -89, -8, -86, HttpConstants.COLON, -65}, new byte[]{107, 125, 48, Ascii.DLE, -39, -17, -14, 55, Ascii.ETB, -80, -122, 97, 13, 112, 96, 98}, new byte[]{-58, -102, -4, -10, 83, -111, -62, -127, 67, 4, 48, 33, -62, 69, -54, 90}, new byte[]{HttpConstants.COLON, -108, -47, 54, -24, -110, -81, HttpConstants.COMMA, -69, 104, 107, HttpConstants.DOUBLE_QUOTE, 60, -105, 35, -110}, new byte[]{-76, 113, Ascii.DLE, -27, 88, -71, -70, 108, -21, -122, 88, HttpConstants.DOUBLE_QUOTE, 56, -110, -65, -45}, new byte[]{-115, Ascii.DC2, -31, 36, -35, -3, 61, -109, 119, -58, -16, -82, -27, 60, -122, -37}, new byte[]{-79, Ascii.DC2, HttpConstants.DOUBLE_QUOTE, -53, -29, -115, -28, -125, -100, -96, -21, -1, 104, 98, 96, -69}, new byte[]{125, -9, 43, -57, 78, Ascii.SUB, -71, 45, -100, -47, -28, -30, -36, -45, 75, 115}, new byte[]{78, -110, -77, HttpConstants.COMMA, -60, Ascii.NAK, Ascii.DC4, 75, 67, Ascii.ESC, 48, 97, -61, 71, -69, 67}, new byte[]{-103, 104, -21, Ascii.SYN, -35, 49, -78, 3, -10, -17, 7, -25, -88, 117, -89, -37}, new byte[]{HttpConstants.COMMA, 71, -54, 126, 2, 35, 94, -114, 119, 89, 117, 60, 75, 97, -13, 109}, new byte[]{-7, Ascii.ETB, -122, -72, -71, -27, Ascii.ESC, 109, 119, 125, -34, -42, Ascii.ETB, 90, -89, -51}, new byte[]{93, -18, 70, -87, -99, 6, 108, -99, -86, -23, -88, 107, -16, 67, 107, -20}, new byte[]{-63, 39, -13, HttpConstants.SEMICOLON, 89, 17, 83, -94, 43, 51, 87, -7, 80, 105, Ascii.RS, -53}, new byte[]{-39, -48, Ascii.SO, 96, 83, 3, -19, -28, -100, 97, -38, 0, 117, Ascii.FF, -18, HttpConstants.COMMA}, new byte[]{80, -93, -92, 99, PSSSigner.TRAILER_IMPLICIT, -70, -69, Byte.MIN_VALUE, -85, Ascii.FF, -23, -106, -95, -91, -79, -16}, new byte[]{57, -54, -115, -109, 48, -34, 13, -85, -120, 41, -106, 94, 2, -79, 61, -82}, new byte[]{66, -76, 117, 46, -88, -13, Ascii.DC4, -120, Ascii.VT, -92, 84, -43, 56, -113, -69, Ascii.ETB}, new byte[]{-10, Ascii.SYN, 10, 54, 121, -73, -74, -82, -41, Byte.MAX_VALUE, 66, 95, 91, -118, -69, 52}, new byte[]{-34, -81, -70, -1, Ascii.CAN, 89, -50, 67, 56, 84, -27, -53, 65, 82, -10, 38}, new byte[]{120, -55, -98, -125, -9, -100, -54, -94, 106, 2, -13, -71, 84, -102, -23, 76}, new byte[]{53, Ascii.DC2, -112, HttpConstants.DOUBLE_QUOTE, 40, 110, -64, SignedBytes.MAX_POWER_OF_TWO, -66, -9, -33, Ascii.ESC, Ascii.SUB, -91, 81, -82}, new byte[]{-49, 89, -90, 72, Ascii.SI, PSSSigner.TRAILER_IMPLICIT, 115, -63, 43, -46, 126, -70, 60, 97, -63, -96}, new byte[]{-95, -99, -59, -23, -3, -67, -42, 74, -120, -126, 40, 2, 3, -52, 106, 117}};
    private static final byte[][] S = {new byte[]{99, 124, 119, 123, -14, 107, 111, -59, 48, 1, 103, 43, -2, -41, -85, 118}, new byte[]{-54, -126, -55, 125, -6, 89, 71, -16, -83, -44, -94, -81, -100, -92, 114, -64}, new byte[]{-73, -3, -109, 38, 54, Utf8.REPLACEMENT_BYTE, -9, -52, 52, -91, -27, -15, 113, -40, 49, Ascii.NAK}, new byte[]{4, -57, 35, -61, Ascii.CAN, -106, 5, -102, 7, Ascii.DC2, Byte.MIN_VALUE, -30, -21, 39, -78, 117}, new byte[]{9, -125, HttpConstants.COMMA, Ascii.SUB, Ascii.ESC, 110, 90, -96, 82, HttpConstants.SEMICOLON, -42, -77, 41, -29, 47, -124}, new byte[]{83, -47, 0, -19, 32, -4, -79, 91, 106, -53, -66, 57, 74, 76, 88, -49}, new byte[]{-48, -17, -86, -5, 67, 77, 51, -123, 69, -7, 2, Byte.MAX_VALUE, 80, 60, -97, -88}, new byte[]{81, -93, SignedBytes.MAX_POWER_OF_TWO, -113, -110, -99, 56, -11, PSSSigner.TRAILER_IMPLICIT, -74, -38, 33, Ascii.DLE, -1, -13, -46}, new byte[]{-51, Ascii.FF, 19, -20, 95, -105, 68, Ascii.ETB, -60, -89, 126, 61, 100, 93, Ascii.EM, 115}, new byte[]{96, -127, 79, -36, HttpConstants.DOUBLE_QUOTE, 42, -112, -120, 70, -18, -72, Ascii.DC4, -34, 94, Ascii.VT, -37}, new byte[]{-32, 50, HttpConstants.COLON, 10, 73, 6, 36, 92, -62, -45, -84, 98, -111, -107, -28, 121}, new byte[]{-25, -56, 55, 109, -115, -43, 78, -87, 108, 86, -12, -22, 101, 122, -82, 8}, new byte[]{-70, 120, 37, 46, Ascii.FS, -90, -76, -58, -24, -35, 116, Ascii.US, 75, -67, -117, -118}, new byte[]{112, 62, -75, 102, 72, 3, -10, Ascii.SO, 97, 53, 87, -71, -122, -63, Ascii.GS, -98}, new byte[]{-31, -8, -104, 17, 105, -39, -114, -108, -101, Ascii.RS, -121, -23, -50, 85, 40, -33}, new byte[]{-116, -95, -119, 13, -65, -26, 66, 104, 65, -103, 45, Ascii.SI, -80, 84, -69, Ascii.SYN}};

    static byte[] aesEnc(byte[] bArr, byte[] bArr2) {
        byte[] mixColumns = mixColumns(shiftRows(subBytes(bArr)));
        Bytes.xorTo(16, bArr2, mixColumns);
        return mixColumns;
    }

    private static byte[] mixColumns(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length];
        int i = 0;
        for (int i2 = 0; i2 < 4; i2++) {
            int i3 = i2 * 4;
            int i4 = i3 + 1;
            int i5 = i3 + 2;
            int i6 = i3 + 3;
            bArr2[i] = (byte) ((((mulX(bArr[i3]) ^ mulX(bArr[i4])) ^ bArr[i4]) ^ bArr[i5]) ^ bArr[i6]);
            bArr2[i + 1] = (byte) ((((bArr[i3] ^ mulX(bArr[i4])) ^ mulX(bArr[i5])) ^ bArr[i5]) ^ bArr[i6]);
            int i7 = i + 3;
            bArr2[i + 2] = (byte) ((((bArr[i3] ^ bArr[i4]) ^ mulX(bArr[i5])) ^ mulX(bArr[i6])) ^ bArr[i6]);
            i += 4;
            bArr2[i7] = (byte) ((((mulX(bArr[i3]) ^ bArr[i3]) ^ bArr[i4]) ^ bArr[i5]) ^ mulX(bArr[i6]));
        }
        return bArr2;
    }

    static byte mulX(byte b) {
        return (byte) ((((b & 128) >> 7) * 27) ^ ((b & Byte.MAX_VALUE) << 1));
    }

    static byte sBox(byte b) {
        return S[(b & 255) >>> 4][b & Ascii.SI];
    }

    static byte[] shiftRows(byte[] bArr) {
        return new byte[]{bArr[0], bArr[5], bArr[10], bArr[15], bArr[4], bArr[9], bArr[14], bArr[3], bArr[8], bArr[13], bArr[2], bArr[7], bArr[12], bArr[1], bArr[6], bArr[11]};
    }

    static byte[] subBytes(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length];
        bArr2[0] = sBox(bArr[0]);
        bArr2[1] = sBox(bArr[1]);
        bArr2[2] = sBox(bArr[2]);
        bArr2[3] = sBox(bArr[3]);
        bArr2[4] = sBox(bArr[4]);
        bArr2[5] = sBox(bArr[5]);
        bArr2[6] = sBox(bArr[6]);
        bArr2[7] = sBox(bArr[7]);
        bArr2[8] = sBox(bArr[8]);
        bArr2[9] = sBox(bArr[9]);
        bArr2[10] = sBox(bArr[10]);
        bArr2[11] = sBox(bArr[11]);
        bArr2[12] = sBox(bArr[12]);
        bArr2[13] = sBox(bArr[13]);
        bArr2[14] = sBox(bArr[14]);
        bArr2[15] = sBox(bArr[15]);
        return bArr2;
    }

    public int getDigestSize() {
        return 32;
    }
}
