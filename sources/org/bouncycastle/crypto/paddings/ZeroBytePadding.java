package org.bouncycastle.crypto.paddings;

import java.security.SecureRandom;
import org.bouncycastle.crypto.InvalidCipherTextException;

public class ZeroBytePadding implements BlockCipherPadding {
    public int addPadding(byte[] bArr, int i) {
        int length = bArr.length - i;
        while (i < bArr.length) {
            bArr[i] = 0;
            i++;
        }
        return length;
    }

    public String getPaddingName() {
        return "ZeroByte";
    }

    public void init(SecureRandom secureRandom) throws IllegalArgumentException {
    }

    public int padCount(byte[] bArr) throws InvalidCipherTextException {
        int length = bArr.length;
        int i = 0;
        int i2 = -1;
        while (true) {
            length--;
            if (length < 0) {
                return i;
            }
            i2 &= ((bArr[length] & 255) - 1) >> 31;
            i -= i2;
        }
    }
}
