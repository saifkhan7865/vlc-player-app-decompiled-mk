package org.bouncycastle.pqc.crypto.gemss;

import java.security.SecureRandom;

class PointerUnion extends Pointer {
    protected int remainder;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PointerUnion(int i) {
        super((i >>> 3) + ((i & 7) != 0 ? 1 : 0));
        this.remainder = 0;
    }

    public PointerUnion(Pointer pointer) {
        super(pointer);
        this.remainder = 0;
    }

    public PointerUnion(PointerUnion pointerUnion) {
        super((Pointer) pointerUnion);
        this.remainder = pointerUnion.remainder;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PointerUnion(byte[] bArr) {
        super((bArr.length >> 3) + ((bArr.length & 7) != 0 ? 1 : 0));
        int i = 0;
        int i2 = 0;
        while (i < bArr.length && i2 < this.array.length) {
            int i3 = 0;
            while (i3 < 8 && i < bArr.length) {
                long[] jArr = this.array;
                jArr[i2] = jArr[i2] | ((((long) bArr[i]) & 255) << (i3 << 3));
                i3++;
                i++;
            }
            i2++;
        }
        this.remainder = 0;
    }

    public void changeIndex(PointerUnion pointerUnion) {
        this.array = pointerUnion.array;
        this.cp = pointerUnion.cp;
        this.remainder = pointerUnion.remainder;
    }

    public void fill(int i, byte[] bArr, int i2, int i3) {
        if (this.remainder != 0) {
            int i4 = this.cp + i;
            int i5 = this.remainder;
            long[] jArr = this.array;
            jArr[i4] = jArr[i4] & (-1 ^ (-1 << (i5 << 3)));
            int i6 = 0;
            while (i5 < 8 && i6 < i3) {
                long[] jArr2 = this.array;
                jArr2[i4] = jArr2[i4] | ((((long) bArr[i2]) & 255) << (i5 << 3));
                i2++;
                i6++;
                i5++;
            }
            i++;
            i3 -= 8 - this.remainder;
        }
        super.fill(i, bArr, i2, i3);
    }

    public void fillBytes(int i, byte[] bArr, int i2, int i3) {
        int i4 = i + this.remainder;
        int i5 = this.cp + (i4 >>> 3);
        int i6 = i4 & 7;
        if (i6 != 0) {
            long[] jArr = this.array;
            jArr[i5] = jArr[i5] & (-1 ^ (-1 << (i6 << 3)));
            int i7 = 0;
            while (i6 < 8 && i7 < i3) {
                long[] jArr2 = this.array;
                jArr2[i5] = jArr2[i5] | ((((long) bArr[i2]) & 255) << (i6 << 3));
                i2++;
                i7++;
                i6++;
            }
            i5++;
            i3 -= i7;
        }
        super.fill(i5 - this.cp, bArr, i2, i3);
    }

    public void fillRandomBytes(int i, SecureRandom secureRandom, int i2) {
        byte[] bArr = new byte[i2];
        secureRandom.nextBytes(bArr);
        fillBytes(i, bArr, 0, i2);
    }

    public long get() {
        return this.remainder == 0 ? this.array[this.cp] : (this.array[this.cp] >>> (this.remainder << 3)) | (this.array[this.cp + 1] << ((8 - this.remainder) << 3));
    }

    public long get(int i) {
        return this.remainder == 0 ? this.array[this.cp + i] : (this.array[this.cp + i] >>> (this.remainder << 3)) | (this.array[(this.cp + i) + 1] << ((8 - this.remainder) << 3));
    }

    public byte getByte() {
        return (byte) ((int) (this.array[this.cp] >>> (this.remainder << 3)));
    }

    public byte getByte(int i) {
        int i2 = this.cp;
        int i3 = this.remainder;
        int i4 = (i3 + i) & 7;
        return (byte) ((int) (this.array[i2 + ((i + i3) >>> 3)] >>> (i4 << 3)));
    }

    public long getWithCheck() {
        if (this.cp >= this.array.length) {
            return 0;
        }
        return this.remainder == 0 ? this.array[this.cp] : this.cp == this.array.length + -1 ? this.array[this.cp] >>> (this.remainder << 3) : (this.array[this.cp] >>> (this.remainder << 3)) | (this.array[this.cp + 1] << ((8 - this.remainder) << 3));
    }

    public long getWithCheck(int i) {
        int i2 = i + this.cp;
        if (i2 >= this.array.length) {
            return 0;
        }
        return this.remainder == 0 ? this.array[i2] : i2 == this.array.length + -1 ? this.array[i2] >>> (this.remainder << 3) : (this.array[i2] >>> (this.remainder << 3)) | (this.array[i2 + 1] << ((8 - this.remainder) << 3));
    }

    public void indexReset() {
        this.cp = 0;
        this.remainder = 0;
    }

    public void moveNextByte() {
        this.remainder++;
        this.cp += this.remainder >>> 3;
        this.remainder &= 7;
    }

    public void moveNextBytes(int i) {
        this.remainder += i;
        this.cp += this.remainder >>> 3;
        this.remainder &= 7;
    }

    public void set(int i, long j) {
        int i2 = this.remainder;
        if (i2 == 0) {
            super.setXor(i, j);
            return;
        }
        int i3 = i2 << 3;
        int i4 = (8 - i2) << 3;
        this.array[this.cp + i] = (j << i3) | (this.array[this.cp + i] & (-1 >>> i4));
        this.array[this.cp + i + 1] = ((-1 << i3) & this.array[this.cp + i + 1]) | (j >>> i4);
    }

    public void setAnd(int i, long j) {
        int i2 = this.remainder;
        if (i2 == 0) {
            super.setAnd(i, j);
            return;
        }
        int i3 = i2 << 3;
        int i4 = (8 - i2) << 3;
        long[] jArr = this.array;
        int i5 = this.cp + i;
        jArr[i5] = jArr[i5] & ((j << i3) | (-1 >>> i4));
        long[] jArr2 = this.array;
        int i6 = this.cp + i + 1;
        jArr2[i6] = ((j >>> i4) | (-1 << i3)) & jArr2[i6];
    }

    public void setAndByte(int i, long j) {
        int i2 = i + this.remainder + (this.cp << 3);
        int i3 = i2 >>> 3;
        long[] jArr = this.array;
        int i4 = (i2 & 7) << 3;
        jArr[i3] = (((j & 255) << i4) | ((255 << i4) ^ -1)) & jArr[i3];
    }

    public void setAndThenXorByte(int i, long j, long j2) {
        int i2 = i + this.remainder + (this.cp << 3);
        int i3 = i2 >>> 3;
        long[] jArr = this.array;
        int i4 = (i2 & 7) << 3;
        jArr[i3] = (((j & 255) << i4) | ((255 << i4) ^ -1)) & jArr[i3];
        long[] jArr2 = this.array;
        jArr2[i3] = ((j2 & 255) << i4) ^ jArr2[i3];
    }

    public void setByte(int i) {
        this.array[this.cp] = ((((long) i) & 255) << (this.remainder << 3)) | (this.array[this.cp] & (-1 >>> ((8 - this.remainder) << 3)));
    }

    public void setByteIndex(int i) {
        this.remainder = i & 7;
        this.cp = i >>> 3;
    }

    public void setRangeClear(int i, int i2) {
        if (this.remainder == 0) {
            super.setRangeClear(i, i2);
            return;
        }
        long[] jArr = this.array;
        int i3 = this.cp + i;
        jArr[i3] = jArr[i3] & (-1 >>> ((8 - this.remainder) << 3));
        super.setRangeClear(i + 1, i2);
        long[] jArr2 = this.array;
        int i4 = this.cp + i2 + 1;
        jArr2[i4] = jArr2[i4] & (-1 << (this.remainder << 3));
    }

    public void setXor(int i, long j) {
        if (this.remainder == 0) {
            super.setXor(i, j);
            return;
        }
        long[] jArr = this.array;
        int i2 = this.cp + i;
        jArr[i2] = jArr[i2] ^ (j << (this.remainder << 3));
        long[] jArr2 = this.array;
        int i3 = this.cp + i + 1;
        jArr2[i3] = (j >>> ((8 - this.remainder) << 3)) ^ jArr2[i3];
    }

    public void setXor(long j) {
        if (this.remainder == 0) {
            super.setXor(j);
            return;
        }
        long[] jArr = this.array;
        int i = this.cp;
        jArr[i] = jArr[i] ^ (j << (this.remainder << 3));
        long[] jArr2 = this.array;
        int i2 = this.cp + 1;
        jArr2[i2] = (j >>> ((8 - this.remainder) << 3)) ^ jArr2[i2];
    }

    public void setXorByte(int i) {
        long[] jArr = this.array;
        int i2 = this.cp;
        jArr[i2] = jArr[i2] ^ ((((long) i) & 255) << (this.remainder << 3));
    }

    public void setXorRangeAndMask(Pointer pointer, int i, long j) {
        Pointer pointer2 = pointer;
        if (this.remainder == 0) {
            super.setXorRangeAndMask(pointer, i, j);
            return;
        }
        int i2 = this.cp;
        int i3 = pointer2.cp;
        int i4 = this.remainder;
        int i5 = i4 << 3;
        int i6 = (8 - i4) << 3;
        int i7 = 0;
        int i8 = i;
        while (i7 < i8) {
            int i9 = i3 + 1;
            long j2 = pointer2.array[i3] & j;
            long[] jArr = this.array;
            jArr[i2] = jArr[i2] ^ (j2 << i5);
            long[] jArr2 = this.array;
            i2++;
            jArr2[i2] = (j2 >>> i6) ^ jArr2[i2];
            i7++;
            i3 = i9;
        }
    }

    public byte[] toBytes(int i) {
        byte[] bArr = new byte[i];
        int i2 = this.remainder;
        while (true) {
            int i3 = this.remainder;
            if (i2 >= i + i3) {
                return bArr;
            }
            bArr[i2 - i3] = (byte) ((int) (this.array[this.cp + (i2 >>> 3)] >>> ((i2 & 7) << 3)));
            i2++;
        }
    }

    public int toBytesMove(byte[] bArr, int i, int i2) {
        int i3 = 0;
        while (i3 < i2) {
            int i4 = i + 1;
            long j = this.array[this.cp];
            int i5 = this.remainder;
            int i6 = i5 + 1;
            this.remainder = i6;
            bArr[i] = (byte) ((int) (j >>> (i5 << 3)));
            if (i6 == 8) {
                this.remainder = 0;
                this.cp++;
            }
            i3++;
            i = i4;
        }
        return i;
    }
}
