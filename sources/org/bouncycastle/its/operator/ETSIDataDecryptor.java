package org.bouncycastle.its.operator;

public interface ETSIDataDecryptor {
    byte[] decrypt(byte[] bArr, byte[] bArr2, byte[] bArr3);

    byte[] getKey();
}
