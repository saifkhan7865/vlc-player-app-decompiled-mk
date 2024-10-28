package org.bouncycastle.crypto.util;

import com.google.common.base.Ascii;
import java.math.BigInteger;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Strings;

class SSHBuffer {
    private final byte[] buffer;
    private int pos = 0;

    public SSHBuffer(byte[] bArr) {
        this.buffer = bArr;
    }

    public SSHBuffer(byte[] bArr, byte[] bArr2) {
        int i = 0;
        this.buffer = bArr2;
        while (i != bArr.length) {
            if (bArr[i] == bArr2[i]) {
                i++;
            } else {
                throw new IllegalArgumentException("magic-number incorrect");
            }
        }
        this.pos += bArr.length;
    }

    public byte[] getBuffer() {
        return Arrays.clone(this.buffer);
    }

    public boolean hasRemaining() {
        return this.pos < this.buffer.length;
    }

    public BigInteger readBigNumPositive() {
        int readU32 = readU32();
        int i = this.pos;
        int i2 = i + readU32;
        byte[] bArr = this.buffer;
        if (i2 <= bArr.length) {
            int i3 = readU32 + i;
            this.pos = i3;
            return new BigInteger(1, Arrays.copyOfRange(bArr, i, i3));
        }
        throw new IllegalArgumentException("not enough data for big num");
    }

    public byte[] readBlock() {
        int readU32 = readU32();
        if (readU32 == 0) {
            return new byte[0];
        }
        int i = this.pos;
        byte[] bArr = this.buffer;
        if (i <= bArr.length - readU32) {
            int i2 = readU32 + i;
            this.pos = i2;
            return Arrays.copyOfRange(bArr, i, i2);
        }
        throw new IllegalArgumentException("not enough data for block");
    }

    public byte[] readPaddedBlock() {
        return readPaddedBlock(8);
    }

    public byte[] readPaddedBlock(int i) {
        byte b;
        int readU32 = readU32();
        if (readU32 == 0) {
            return new byte[0];
        }
        int i2 = this.pos;
        byte[] bArr = this.buffer;
        if (i2 > bArr.length - readU32) {
            throw new IllegalArgumentException("not enough data for block");
        } else if (readU32 % i == 0) {
            int i3 = i2 + readU32;
            this.pos = i3;
            if (readU32 > 0 && (b = bArr[i3 - 1] & 255) > 0 && b < i) {
                i3 -= b;
                int i4 = 1;
                int i5 = i3;
                while (i4 <= b) {
                    if (i4 == (this.buffer[i5] & 255)) {
                        i4++;
                        i5++;
                    } else {
                        throw new IllegalArgumentException("incorrect padding");
                    }
                }
            }
            return Arrays.copyOfRange(this.buffer, i2, i3);
        } else {
            throw new IllegalArgumentException("missing padding");
        }
    }

    public String readString() {
        return Strings.fromByteArray(readBlock());
    }

    public int readU32() {
        int i = this.pos;
        byte[] bArr = this.buffer;
        if (i <= bArr.length - 4) {
            byte b = ((bArr[i + 1] & 255) << Ascii.DLE) | ((bArr[i] & 255) << Ascii.CAN);
            this.pos = i + 4;
            return (bArr[i + 3] & 255) | b | ((bArr[i + 2] & 255) << 8);
        }
        throw new IllegalArgumentException("4 bytes for U32 exceeds buffer.");
    }

    public void skipBlock() {
        int readU32 = readU32();
        int i = this.pos;
        if (i <= this.buffer.length - readU32) {
            this.pos = i + readU32;
            return;
        }
        throw new IllegalArgumentException("not enough data for block");
    }
}
