package org.bouncycastle.crypto.paddings;

import java.security.SecureRandom;
import org.bouncycastle.crypto.InvalidCipherTextException;

public class TBCPadding implements BlockCipherPadding {
    public int addPadding(byte[] bArr, int i) {
        int length = bArr.length - i;
        int i2 = 255;
        if (i <= 0 ? (bArr[bArr.length - 1] & 1) != 0 : (bArr[i - 1] & 1) != 0) {
            i2 = 0;
        }
        byte b = (byte) i2;
        while (i < bArr.length) {
            bArr[i] = b;
            i++;
        }
        return length;
    }

    public String getPaddingName() {
        return "TBC";
    }

    public void init(SecureRandom secureRandom) throws IllegalArgumentException {
    }

    public int padCount(byte[] bArr) throws InvalidCipherTextException {
        int length = bArr.length - 1;
        byte b = bArr[length] & 255;
        int i = 1;
        int i2 = -1;
        while (true) {
            length--;
            if (length < 0) {
                return i;
            }
            i2 &= (((bArr[length] & 255) ^ b) - 1) >> 31;
            i -= i2;
        }
    }
}
