package org.bouncycastle.crypto.engines;

import androidx.core.view.accessibility.AccessibilityEventCompat;
import com.google.common.base.Ascii;
import io.netty.handler.codec.http.HttpConstants;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.constraints.DefaultServiceProperties;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.Pack;

public class DESEngine extends DESBase implements BlockCipher {
    protected static final int BLOCK_SIZE = 8;
    private static final int[] SP1 = {16843776, 0, 65536, 16843780, 16842756, 66564, 4, 65536, 1024, 16843776, 16843780, 1024, 16778244, 16842756, 16777216, 4, 1028, 16778240, 16778240, 66560, 66560, 16842752, 16842752, 16778244, 65540, 16777220, 16777220, 65540, 0, 1028, 66564, 16777216, 65536, 16843780, 4, 16842752, 16843776, 16777216, 16777216, 1024, 16842756, 65536, 66560, 16777220, 1024, 4, 16778244, 66564, 16843780, 65540, 16842752, 16778244, 16777220, 1028, 66564, 16843776, 1028, 16778240, 16778240, 0, 65540, 66560, 0, 16842756};
    private static final int[] SP2 = {-2146402272, -2147450880, 32768, 1081376, 1048576, 32, -2146435040, -2147450848, -2147483616, -2146402272, -2146402304, Integer.MIN_VALUE, -2147450880, 1048576, 32, -2146435040, 1081344, 1048608, -2147450848, 0, Integer.MIN_VALUE, 32768, 1081376, -2146435072, 1048608, -2147483616, 0, 1081344, 32800, -2146402304, -2146435072, 32800, 0, 1081376, -2146435040, 1048576, -2147450848, -2146435072, -2146402304, 32768, -2146435072, -2147450880, 32, -2146402272, 1081376, 32, 32768, Integer.MIN_VALUE, 32800, -2146402304, 1048576, -2147483616, 1048608, -2147450848, -2147483616, 1048608, 1081344, 0, -2147450880, 32800, Integer.MIN_VALUE, -2146435040, -2146402272, 1081344};
    private static final int[] SP3 = {520, 134349312, 0, 134348808, 134218240, 0, 131592, 134218240, 131080, 134217736, 134217736, 131072, 134349320, 131080, 134348800, 520, 134217728, 8, 134349312, 512, 131584, 134348800, 134348808, 131592, 134218248, 131584, 131072, 134218248, 8, 134349320, 512, 134217728, 134349312, 134217728, 131080, 520, 131072, 134349312, 134218240, 0, 512, 131080, 134349320, 134218240, 134217736, 512, 0, 134348808, 134218248, 131072, 134217728, 134349320, 8, 131592, 131584, 134217736, 134348800, 134218248, 520, 134348800, 131592, 8, 134348808, 131584};
    private static final int[] SP4 = {8396801, 8321, 8321, 128, 8396928, 8388737, 8388609, 8193, 0, 8396800, 8396800, 8396929, 129, 0, 8388736, 8388609, 1, 8192, 8388608, 8396801, 128, 8388608, 8193, 8320, 8388737, 1, 8320, 8388736, 8192, 8396928, 8396929, 129, 8388736, 8388609, 8396800, 8396929, 129, 0, 0, 8396800, 8320, 8388736, 8388737, 1, 8396801, 8321, 8321, 128, 8396929, 129, 1, 8192, 8388609, 8193, 8396928, 8388737, 8193, 8320, 8388608, 8396801, 128, 8388608, 8192, 8396928};
    private static final int[] SP5 = {256, 34078976, 34078720, 1107296512, 524288, 256, 1073741824, 34078720, 1074266368, 524288, 33554688, 1074266368, 1107296512, 1107820544, 524544, 1073741824, 33554432, 1074266112, 1074266112, 0, 1073742080, 1107820800, 1107820800, 33554688, 1107820544, 1073742080, 0, 1107296256, 34078976, 33554432, 1107296256, 524544, 524288, 1107296512, 256, 33554432, 1073741824, 34078720, 1107296512, 1074266368, 33554688, 1073741824, 1107820544, 34078976, 1074266368, 256, 33554432, 1107820544, 1107820800, 524544, 1107296256, 1107820800, 34078720, 0, 1074266112, 1107296256, 524544, 33554688, 1073742080, 524288, 0, 1074266112, 34078976, 1073742080};
    private static final int[] SP6 = {536870928, 541065216, 16384, 541081616, 541065216, 16, 541081616, 4194304, 536887296, 4210704, 4194304, 536870928, 4194320, 536887296, PKIFailureInfo.duplicateCertReq, 16400, 0, 4194320, 536887312, 16384, 4210688, 536887312, 16, 541065232, 541065232, 0, 4210704, 541081600, 16400, 4210688, 541081600, PKIFailureInfo.duplicateCertReq, 536887296, 16, 541065232, 4210688, 541081616, 4194304, 16400, 536870928, 4194304, 536887296, PKIFailureInfo.duplicateCertReq, 16400, 536870928, 541081616, 4210688, 541065216, 4210704, 541081600, 0, 541065232, 16, 16384, 541065216, 4210704, 16384, 4194320, 536887312, 0, 541081600, PKIFailureInfo.duplicateCertReq, 4194320, 536887312};
    private static final int[] SP7 = {2097152, 69206018, 67110914, 0, 2048, 67110914, 2099202, 69208064, 69208066, 2097152, 0, 67108866, 2, AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL, 69206018, 2050, 67110912, 2099202, 2097154, 67110912, 67108866, 69206016, 69208064, 2097154, 69206016, 2048, 2050, 69208066, 2099200, 2, AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL, 2099200, AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL, 2099200, 2097152, 67110914, 67110914, 69206018, 69206018, 2, 2097154, AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL, 67110912, 2097152, 69208064, 2050, 2099202, 69208064, 2050, 67108866, 69208066, 69206016, 2099200, 0, 2, 69208066, 0, 2099202, 69206016, 2048, 67108866, 67110912, 2048, 2097154};
    private static final int[] SP8 = {268439616, 4096, 262144, 268701760, 268435456, 268439616, 64, 268435456, 262208, 268697600, 268701760, 266240, 268701696, 266304, 4096, 64, 268697600, 268435520, 268439552, 4160, 266240, 262208, 268697664, 268701696, 4160, 0, 0, 268697664, 268435520, 268439552, 266304, 262144, 266304, 262144, 268701696, 4096, 64, 268697664, 4096, 266304, 268439552, 64, 268435520, 268697600, 268697664, 268435456, 262144, 268439616, 0, 268701760, 262208, 268435520, 268697600, 268439552, 268439616, 0, 268701760, 266240, 266240, 4160, 4160, 262208, 268435456, 268701696};
    private static final int[] bigbyte = {8388608, 4194304, 2097152, 1048576, 524288, 262144, 131072, 65536, 32768, 16384, 8192, 4096, 2048, 1024, 512, 256, 128, 64, 32, 16, 8, 4, 2, 1};
    private static final short[] bytebit = {128, 64, 32, 16, 8, 4, 2, 1};
    private static final byte[] pc1 = {56, 48, 40, 32, Ascii.CAN, Ascii.DLE, 8, 0, 57, 49, 41, 33, Ascii.EM, 17, 9, 1, HttpConstants.COLON, 50, 42, HttpConstants.DOUBLE_QUOTE, Ascii.SUB, Ascii.DC2, 10, 2, HttpConstants.SEMICOLON, 51, 43, 35, 62, 54, 46, 38, Ascii.RS, Ascii.SYN, Ascii.SO, 6, 61, 53, 45, 37, Ascii.GS, Ascii.NAK, 13, 5, 60, 52, HttpConstants.COMMA, 36, Ascii.FS, Ascii.DC4, Ascii.FF, 4, Ascii.ESC, 19, Ascii.VT, 3};
    private static final byte[] pc2 = {13, Ascii.DLE, 10, Ascii.ETB, 0, 4, 2, Ascii.ESC, Ascii.SO, 5, Ascii.DC4, 9, Ascii.SYN, Ascii.DC2, Ascii.VT, 3, Ascii.EM, 7, Ascii.SI, 6, Ascii.SUB, 19, Ascii.FF, 1, 40, 51, Ascii.RS, 36, 46, 54, Ascii.GS, 39, 50, HttpConstants.COMMA, 32, 47, 43, 48, 38, 55, 33, 52, 45, 41, 49, 35, Ascii.FS, Ascii.US};
    private static final byte[] totrot = {1, 2, 4, 6, 8, 10, Ascii.FF, Ascii.SO, Ascii.SI, 17, 19, Ascii.NAK, Ascii.ETB, Ascii.EM, Ascii.ESC, Ascii.FS};
    private boolean forEncryption;
    private int[] workingKey = null;

    public DESEngine() {
        CryptoServicesRegistrar.checkConstraints(new DefaultServiceProperties(getAlgorithmName(), 56));
    }

    /* access modifiers changed from: protected */
    public void desFunc(int[] iArr, byte[] bArr, int i, byte[] bArr2, int i2) {
        byte[] bArr3 = bArr2;
        int i3 = i2;
        int bigEndianToInt = Pack.bigEndianToInt(bArr, i);
        int bigEndianToInt2 = Pack.bigEndianToInt(bArr, i + 4);
        int i4 = ((bigEndianToInt >>> 4) ^ bigEndianToInt2) & 252645135;
        int i5 = bigEndianToInt2 ^ i4;
        int i6 = bigEndianToInt ^ (i4 << 4);
        int i7 = ((i6 >>> 16) ^ i5) & 65535;
        int i8 = i5 ^ i7;
        int i9 = i6 ^ (i7 << 16);
        int i10 = ((i8 >>> 2) ^ i9) & 858993459;
        int i11 = i9 ^ i10;
        int i12 = i8 ^ (i10 << 2);
        int i13 = ((i12 >>> 8) ^ i11) & 16711935;
        int i14 = i11 ^ i13;
        int i15 = i12 ^ (i13 << 8);
        int i16 = (i15 >>> 31) | (i15 << 1);
        int i17 = (i14 ^ i16) & -1431655766;
        int i18 = i14 ^ i17;
        int i19 = i16 ^ i17;
        int i20 = (i18 >>> 31) | (i18 << 1);
        for (int i21 = 0; i21 < 8; i21++) {
            int i22 = i21 * 4;
            int i23 = ((i19 << 28) | (i19 >>> 4)) ^ iArr[i22];
            int[] iArr2 = SP7;
            int i24 = iArr2[i23 & 63];
            int[] iArr3 = SP5;
            int i25 = i24 | iArr3[(i23 >>> 8) & 63];
            int[] iArr4 = SP3;
            int i26 = i25 | iArr4[(i23 >>> 16) & 63];
            int[] iArr5 = SP1;
            int i27 = iArr5[(i23 >>> 24) & 63] | i26;
            int i28 = iArr[i22 + 1] ^ i19;
            int[] iArr6 = SP8;
            int i29 = i27 | iArr6[i28 & 63];
            int[] iArr7 = SP6;
            int i30 = i29 | iArr7[(i28 >>> 8) & 63];
            int[] iArr8 = SP4;
            int i31 = i30 | iArr8[(i28 >>> 16) & 63];
            int[] iArr9 = SP2;
            i20 ^= i31 | iArr9[(i28 >>> 24) & 63];
            int i32 = ((i20 << 28) | (i20 >>> 4)) ^ iArr[i22 + 2];
            int i33 = iArr5[(i32 >>> 24) & 63];
            int i34 = iArr[i22 + 3] ^ i20;
            i19 ^= ((((i33 | ((iArr2[i32 & 63] | iArr3[(i32 >>> 8) & 63]) | iArr4[(i32 >>> 16) & 63])) | iArr6[i34 & 63]) | iArr7[(i34 >>> 8) & 63]) | iArr8[(i34 >>> 16) & 63]) | iArr9[(i34 >>> 24) & 63];
        }
        int i35 = (i19 >>> 1) | (i19 << 31);
        int i36 = (i20 ^ i35) & -1431655766;
        int i37 = i20 ^ i36;
        int i38 = i35 ^ i36;
        int i39 = (i37 >>> 1) | (i37 << 31);
        int i40 = ((i39 >>> 8) ^ i38) & 16711935;
        int i41 = i38 ^ i40;
        int i42 = i39 ^ (i40 << 8);
        int i43 = ((i42 >>> 2) ^ i41) & 858993459;
        int i44 = i41 ^ i43;
        int i45 = i42 ^ (i43 << 2);
        int i46 = ((i44 >>> 16) ^ i45) & 65535;
        int i47 = i45 ^ i46;
        int i48 = i44 ^ (i46 << 16);
        int i49 = ((i48 >>> 4) ^ i47) & 252645135;
        Pack.intToBigEndian(i48 ^ (i49 << 4), bArr3, i3);
        Pack.intToBigEndian(i47 ^ i49, bArr3, i3 + 4);
    }

    /* access modifiers changed from: protected */
    public int[] generateWorkingKey(boolean z, byte[] bArr) {
        int i;
        int[] iArr = new int[32];
        boolean[] zArr = new boolean[56];
        boolean[] zArr2 = new boolean[56];
        int i2 = 0;
        while (true) {
            boolean z2 = true;
            if (i2 >= 56) {
                break;
            }
            byte b = pc1[i2];
            if ((bytebit[b & 7] & bArr[b >>> 3]) == 0) {
                z2 = false;
            }
            zArr[i2] = z2;
            i2++;
        }
        for (int i3 = 0; i3 < 16; i3++) {
            int i4 = z ? i3 << 1 : (15 - i3) << 1;
            int i5 = i4 + 1;
            iArr[i5] = 0;
            iArr[i4] = 0;
            int i6 = 0;
            while (true) {
                if (i6 >= 28) {
                    break;
                }
                int i7 = totrot[i3] + i6;
                if (i7 < 28) {
                    zArr2[i6] = zArr[i7];
                } else {
                    zArr2[i6] = zArr[i7 - 28];
                }
                i6++;
            }
            for (i = 28; i < 56; i++) {
                int i8 = totrot[i3] + i;
                if (i8 < 56) {
                    zArr2[i] = zArr[i8];
                } else {
                    zArr2[i] = zArr[i8 - 28];
                }
            }
            for (int i9 = 0; i9 < 24; i9++) {
                byte[] bArr2 = pc2;
                if (zArr2[bArr2[i9]]) {
                    iArr[i4] = iArr[i4] | bigbyte[i9];
                }
                if (zArr2[bArr2[i9 + 24]]) {
                    iArr[i5] = iArr[i5] | bigbyte[i9];
                }
            }
        }
        for (int i10 = 0; i10 != 32; i10 += 2) {
            int i11 = iArr[i10];
            int i12 = i10 + 1;
            int i13 = iArr[i12];
            iArr[i10] = ((16515072 & i13) >>> 10) | ((i11 & 16515072) << 6) | ((i11 & 4032) << 10) | ((i13 & 4032) >>> 6);
            iArr[i12] = ((i11 & 63) << 16) | ((i11 & 258048) << 12) | ((258048 & i13) >>> 4) | (i13 & 63);
        }
        return iArr;
    }

    public String getAlgorithmName() {
        return "DES";
    }

    public int getBlockSize() {
        return 8;
    }

    public void init(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof KeyParameter) {
            KeyParameter keyParameter = (KeyParameter) cipherParameters;
            if (keyParameter.getKeyLength() <= 8) {
                this.forEncryption = z;
                this.workingKey = generateWorkingKey(z, keyParameter.getKey());
                CryptoServicesRegistrar.checkConstraints(new DefaultServiceProperties(getAlgorithmName(), 56, cipherParameters, Utils.getPurpose(this.forEncryption)));
                return;
            }
            throw new IllegalArgumentException("DES key too long - should be 8 bytes");
        }
        throw new IllegalArgumentException("invalid parameter passed to DES init - " + cipherParameters.getClass().getName());
    }

    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        int[] iArr = this.workingKey;
        if (iArr == null) {
            throw new IllegalStateException("DES engine not initialised");
        } else if (i + 8 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        } else if (i2 + 8 <= bArr2.length) {
            desFunc(iArr, bArr, i, bArr2, i2);
            return 8;
        } else {
            throw new OutputLengthException("output buffer too short");
        }
    }

    public void reset() {
    }
}
