package org.bouncycastle.crypto.paddings;

import java.security.SecureRandom;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.openssl.PEMParser;

public class PKCS7Padding implements BlockCipherPadding {
    public int addPadding(byte[] bArr, int i) {
        byte length = (byte) (bArr.length - i);
        while (i < bArr.length) {
            bArr[i] = length;
            i++;
        }
        return length;
    }

    public String getPaddingName() {
        return PEMParser.TYPE_PKCS7;
    }

    public void init(SecureRandom secureRandom) throws IllegalArgumentException {
    }

    public int padCount(byte[] bArr) throws InvalidCipherTextException {
        byte b = bArr[bArr.length - 1];
        byte b2 = b & 255;
        int length = bArr.length - b2;
        int i = ((b2 - 1) | length) >> 31;
        for (int i2 = 0; i2 < bArr.length; i2++) {
            i |= (bArr[i2] ^ b) & (((i2 - length) >> 31) ^ -1);
        }
        if (i == 0) {
            return b2;
        }
        throw new InvalidCipherTextException("pad block corrupted");
    }
}
