package org.bouncycastle.pqc.crypto.gemss;

import java.security.SecureRandom;
import java.util.Arrays;

class Pointer {
    protected long[] array;
    protected int cp;

    public Pointer() {
        this.cp = 0;
    }

    public Pointer(int i) {
        this.array = new long[i];
        this.cp = 0;
    }

    public Pointer(Pointer pointer) {
        this.array = pointer.array;
        this.cp = pointer.cp;
    }

    public Pointer(Pointer pointer, int i) {
        this.array = pointer.array;
        this.cp = pointer.cp + i;
    }

    public void changeIndex(int i) {
        this.cp = i;
    }

    public void changeIndex(Pointer pointer) {
        this.array = pointer.array;
        this.cp = pointer.cp;
    }

    public void changeIndex(Pointer pointer, int i) {
        this.array = pointer.array;
        this.cp = pointer.cp + i;
    }

    public void copyFrom(int i, Pointer pointer, int i2, int i3) {
        System.arraycopy(pointer.array, pointer.cp + i2, this.array, this.cp + i, i3);
    }

    public void copyFrom(Pointer pointer, int i) {
        System.arraycopy(pointer.array, pointer.cp, this.array, this.cp, i);
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x001f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void fill(int r10, byte[] r11, int r12, int r13) {
        /*
            r9 = this;
            int r0 = r9.cp
            int r0 = r0 + r10
            r10 = 0
            r1 = 0
        L_0x0005:
            long[] r2 = r9.array
            int r3 = r2.length
            if (r0 >= r3) goto L_0x001a
            int r3 = r1 + 8
            if (r3 > r13) goto L_0x001a
            long r4 = org.bouncycastle.util.Pack.littleEndianToLong(r11, r12)
            r2[r0] = r4
            int r12 = r12 + 8
            int r0 = r0 + 1
            r1 = r3
            goto L_0x0005
        L_0x001a:
            if (r1 >= r13) goto L_0x0040
            int r3 = r2.length
            if (r0 >= r3) goto L_0x0040
            r3 = 0
            r2[r0] = r3
        L_0x0023:
            r2 = 8
            if (r10 >= r2) goto L_0x0040
            if (r1 >= r13) goto L_0x0040
            long[] r2 = r9.array
            r3 = r2[r0]
            byte r5 = r11[r12]
            long r5 = (long) r5
            r7 = 255(0xff, double:1.26E-321)
            long r5 = r5 & r7
            int r7 = r10 << 3
            long r5 = r5 << r7
            long r3 = r3 | r5
            r2[r0] = r3
            int r10 = r10 + 1
            int r12 = r12 + 1
            int r1 = r1 + 1
            goto L_0x0023
        L_0x0040:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.pqc.crypto.gemss.Pointer.fill(int, byte[], int, int):void");
    }

    public void fillRandom(int i, SecureRandom secureRandom, int i2) {
        byte[] bArr = new byte[i2];
        secureRandom.nextBytes(bArr);
        fill(i, bArr, 0, i2);
    }

    public long get() {
        return this.array[this.cp];
    }

    public long get(int i) {
        return this.array[this.cp + i];
    }

    public long[] getArray() {
        return this.array;
    }

    public int getD_for_not0_or_plus(int i, int i2) {
        int i3 = this.cp;
        int i4 = 0;
        long j = 0;
        while (i2 > 0) {
            int i5 = i3 + 1;
            long j2 = this.array[i3];
            int i6 = 1;
            while (i6 < i) {
                j2 |= this.array[i5];
                i6++;
                i5++;
            }
            j |= GeMSSUtils.ORBITS_UINT(j2);
            i4 = (int) (((long) i4) + j);
            i2--;
            i3 = i5;
        }
        return i4;
    }

    public long getDotProduct(int i, Pointer pointer, int i2, int i3) {
        int i4 = i + this.cp;
        int i5 = i2 + pointer.cp;
        int i6 = i4 + 1;
        int i7 = i5 + 1;
        long j = this.array[i4] & pointer.array[i5];
        int i8 = 1;
        while (i8 < i3) {
            j ^= this.array[i6] & pointer.array[i7];
            i8++;
            i7++;
            i6++;
        }
        return j;
    }

    public int getIndex() {
        return this.cp;
    }

    public int getLength() {
        return this.array.length - this.cp;
    }

    public void indexReset() {
        this.cp = 0;
    }

    public int is0_gf2n(int i, int i2) {
        long j = get(i);
        for (int i3 = 1; i3 < i2; i3++) {
            j |= get(i + i3);
        }
        return (int) GeMSSUtils.NORBITS_UINT(j);
    }

    public int isEqual_nocst_gf2(Pointer pointer, int i) {
        int i2 = pointer.cp;
        int i3 = this.cp;
        int i4 = 0;
        while (i4 < i) {
            int i5 = i3 + 1;
            int i6 = i2 + 1;
            if (this.array[i3] != pointer.array[i2]) {
                return 0;
            }
            i4++;
            i2 = i6;
            i3 = i5;
        }
        return 1;
    }

    public void move(int i) {
        this.cp += i;
    }

    public void moveIncremental() {
        this.cp++;
    }

    public int searchDegree(int i, int i2, int i3) {
        while (is0_gf2n(i * i3, i3) != 0 && i >= i2) {
            i--;
        }
        return i;
    }

    public void set(int i, long j) {
        this.array[this.cp + i] = j;
    }

    public void set(long j) {
        this.array[this.cp] = j;
    }

    public void set1_gf2n(int i, int i2) {
        int i3 = this.cp + i;
        int i4 = i3 + 1;
        this.array[i3] = 1;
        int i5 = 1;
        while (i5 < i2) {
            this.array[i4] = 0;
            i5++;
            i4++;
        }
    }

    public void setAnd(int i, long j) {
        long[] jArr = this.array;
        int i2 = this.cp + i;
        jArr[i2] = j & jArr[i2];
    }

    public void setAnd(long j) {
        long[] jArr = this.array;
        int i = this.cp;
        jArr[i] = j & jArr[i];
    }

    public void setClear(int i) {
        this.array[this.cp + i] = 0;
    }

    public void setRangeClear(int i, int i2) {
        int i3 = i + this.cp;
        Arrays.fill(this.array, i3, i2 + i3, 0);
    }

    public void setRangeFromXor(int i, Pointer pointer, int i2, Pointer pointer2, int i3, int i4) {
        int i5 = i + this.cp;
        int i6 = i2 + pointer.cp;
        int i7 = i3 + pointer2.cp;
        int i8 = 0;
        while (i8 < i4) {
            this.array[i5] = pointer.array[i6] ^ pointer2.array[i7];
            i8++;
            i5++;
            i7++;
            i6++;
        }
    }

    public void setRangeFromXor(Pointer pointer, Pointer pointer2, int i) {
        int i2 = this.cp;
        int i3 = pointer.cp;
        int i4 = pointer2.cp;
        int i5 = 0;
        while (i5 < i) {
            this.array[i2] = pointer.array[i3] ^ pointer2.array[i4];
            i5++;
            i2++;
            i4++;
            i3++;
        }
    }

    public void setRangeFromXorAndMask_xor(Pointer pointer, Pointer pointer2, long j, int i) {
        Pointer pointer3 = pointer;
        Pointer pointer4 = pointer2;
        int i2 = this.cp;
        int i3 = pointer3.cp;
        int i4 = pointer4.cp;
        int i5 = 0;
        int i6 = i;
        while (i5 < i6) {
            long[] jArr = this.array;
            long[] jArr2 = pointer3.array;
            long j2 = jArr2[i3];
            long[] jArr3 = pointer4.array;
            long j3 = (j2 ^ jArr3[i4]) & j;
            jArr[i2] = j3;
            jArr2[i3] = j3 ^ jArr2[i3];
            jArr3[i4] = jArr3[i4] ^ jArr[i2];
            i5++;
            i4++;
            i2++;
            i3++;
        }
    }

    public void setRangePointerUnion(PointerUnion pointerUnion, int i) {
        if (pointerUnion.remainder == 0) {
            System.arraycopy(pointerUnion.array, pointerUnion.cp, this.array, this.cp, i);
            return;
        }
        int i2 = (8 - pointerUnion.remainder) << 3;
        int i3 = pointerUnion.remainder << 3;
        int i4 = this.cp;
        int i5 = pointerUnion.cp;
        int i6 = 0;
        while (i6 < i) {
            i5++;
            this.array[i4] = (pointerUnion.array[i5] >>> i3) ^ (pointerUnion.array[i5] << i2);
            i6++;
            i4++;
        }
    }

    public void setRangePointerUnion(PointerUnion pointerUnion, int i, int i2) {
        PointerUnion pointerUnion2 = pointerUnion;
        int i3 = i;
        int i4 = i2 & 63;
        int i5 = 64 - i4;
        int i6 = this.cp;
        int i7 = pointerUnion2.cp;
        int i8 = 0;
        if (pointerUnion2.remainder == 0) {
            while (i8 < i3) {
                i7++;
                this.array[i6] = (pointerUnion2.array[i7] >>> i4) ^ (pointerUnion2.array[i7] << i5);
                i8++;
                i6++;
            }
            return;
        }
        int i9 = pointerUnion2.remainder << 3;
        int i10 = (8 - pointerUnion2.remainder) << 3;
        while (i8 < i3) {
            int i11 = i7 + 1;
            this.array[i6] = (((pointerUnion2.array[i7] >>> i9) | (pointerUnion2.array[i11] << i10)) >>> i4) ^ (((pointerUnion2.array[i11] >>> i9) | (pointerUnion2.array[i7 + 2] << i10)) << i5);
            i8++;
            i6++;
            i7 = i11;
        }
    }

    public void setRangePointerUnion_Check(PointerUnion pointerUnion, int i, int i2) {
        PointerUnion pointerUnion2 = pointerUnion;
        int i3 = i;
        int i4 = i2 & 63;
        int i5 = 64 - i4;
        int i6 = this.cp;
        int i7 = pointerUnion2.cp;
        int i8 = 0;
        if (pointerUnion2.remainder == 0) {
            while (i8 < i3 && i7 < pointerUnion2.array.length - 1) {
                i7++;
                this.array[i6] = (pointerUnion2.array[i7] >>> i4) ^ (pointerUnion2.array[i7] << i5);
                i8++;
                i6++;
            }
            if (i8 < i3) {
                this.array[i6] = pointerUnion2.array[i7] >>> i4;
                return;
            }
            return;
        }
        int i9 = pointerUnion2.remainder << 3;
        int i10 = (8 - pointerUnion2.remainder) << 3;
        while (i8 < i3 && i7 < pointerUnion2.array.length - 2) {
            int i11 = i7 + 1;
            this.array[i6] = (((pointerUnion2.array[i7] >>> i9) | (pointerUnion2.array[i11] << i10)) >>> i4) ^ (((pointerUnion2.array[i11] >>> i9) | (pointerUnion2.array[i7 + 2] << i10)) << i5);
            i8++;
            i6++;
            i7 = i11;
        }
        if (i8 < i3) {
            int i12 = i7 + 1;
            this.array[i6] = ((pointerUnion2.array[i12] >>> i9) << i5) ^ (((pointerUnion2.array[i12] << i10) | (pointerUnion2.array[i7] >>> i9)) >>> i4);
        }
    }

    public void setRangeRotate(int i, Pointer pointer, int i2, int i3, int i4) {
        int i5 = 64 - i4;
        int i6 = i + this.cp;
        int i7 = i2 + pointer.cp;
        int i8 = 0;
        while (i8 < i3) {
            long[] jArr = this.array;
            long[] jArr2 = pointer.array;
            i7++;
            jArr[i6] = (jArr2[i7] >>> i5) ^ (jArr2[i7] << i4);
            i8++;
            i6++;
        }
    }

    public int setRange_xi(long j, int i, int i2) {
        int i3 = 0;
        while (i3 < i2) {
            this.array[this.cp + i] = -((j >>> i3) & 1);
            i3++;
            i++;
        }
        return i;
    }

    public void setXor(int i, long j) {
        long[] jArr = this.array;
        int i2 = this.cp + i;
        jArr[i2] = j ^ jArr[i2];
    }

    public void setXor(long j) {
        long[] jArr = this.array;
        int i = this.cp;
        jArr[i] = j ^ jArr[i];
    }

    public void setXorMatrix(Pointer pointer, int i, int i2) {
        int i3 = this.cp;
        for (int i4 = 0; i4 < i2; i4++) {
            int i5 = i3;
            int i6 = 0;
            while (i6 < i) {
                long[] jArr = this.array;
                long j = jArr[i5];
                long[] jArr2 = pointer.array;
                int i7 = pointer.cp;
                pointer.cp = i7 + 1;
                jArr[i5] = j ^ jArr2[i7];
                i6++;
                i5++;
            }
        }
        this.cp += i;
    }

    public void setXorMatrix_NoMove(Pointer pointer, int i, int i2) {
        int i3 = this.cp;
        for (int i4 = 0; i4 < i2; i4++) {
            int i5 = i3;
            int i6 = 0;
            while (i6 < i) {
                long[] jArr = this.array;
                long j = jArr[i5];
                long[] jArr2 = pointer.array;
                int i7 = pointer.cp;
                pointer.cp = i7 + 1;
                jArr[i5] = j ^ jArr2[i7];
                i6++;
                i5++;
            }
        }
    }

    public void setXorRange(int i, Pointer pointer, int i2, int i3) {
        int i4 = i + this.cp;
        int i5 = i2 + pointer.cp;
        int i6 = 0;
        while (i6 < i3) {
            long[] jArr = this.array;
            jArr[i4] = jArr[i4] ^ pointer.array[i5];
            i6++;
            i4++;
            i5++;
        }
    }

    public void setXorRange(int i, PointerUnion pointerUnion, int i2, int i3) {
        PointerUnion pointerUnion2 = pointerUnion;
        int i4 = i3;
        int i5 = i + this.cp;
        int i6 = i2 + pointerUnion2.cp;
        int i7 = 0;
        if (pointerUnion2.remainder == 0) {
            while (i7 < i4) {
                long[] jArr = this.array;
                jArr[i5] = jArr[i5] ^ pointerUnion2.array[i6];
                i7++;
                i5++;
                i6++;
            }
            return;
        }
        int i8 = pointerUnion2.remainder << 3;
        int i9 = (8 - pointerUnion2.remainder) << 3;
        while (i7 < i4) {
            long[] jArr2 = this.array;
            i6++;
            jArr2[i5] = jArr2[i5] ^ ((pointerUnion2.array[i6] >>> i8) | (pointerUnion2.array[i6] << i9));
            i7++;
            i5++;
        }
    }

    public void setXorRange(Pointer pointer, int i) {
        int i2 = this.cp;
        int i3 = pointer.cp;
        int i4 = 0;
        while (i4 < i) {
            long[] jArr = this.array;
            jArr[i2] = jArr[i2] ^ pointer.array[i3];
            i4++;
            i2++;
            i3++;
        }
    }

    public void setXorRange(Pointer pointer, int i, int i2) {
        int i3 = this.cp;
        int i4 = i + pointer.cp;
        int i5 = 0;
        while (i5 < i2) {
            long[] jArr = this.array;
            jArr[i3] = jArr[i3] ^ pointer.array[i4];
            i5++;
            i3++;
            i4++;
        }
    }

    public void setXorRangeAndMask(Pointer pointer, int i, long j) {
        int i2 = this.cp;
        int i3 = pointer.cp;
        int i4 = 0;
        while (i4 < i) {
            long[] jArr = this.array;
            jArr[i2] = jArr[i2] ^ (pointer.array[i3] & j);
            i4++;
            i2++;
            i3++;
        }
    }

    public void setXorRangeAndMaskMove(Pointer pointer, int i, long j) {
        int i2 = this.cp;
        int i3 = 0;
        while (i3 < i) {
            long[] jArr = this.array;
            long j2 = jArr[i2];
            long[] jArr2 = pointer.array;
            int i4 = pointer.cp;
            pointer.cp = i4 + 1;
            jArr[i2] = j2 ^ (jArr2[i4] & j);
            i3++;
            i2++;
        }
    }

    public void setXorRangeXor(int i, Pointer pointer, int i2, Pointer pointer2, int i3, int i4) {
        Pointer pointer3 = pointer;
        Pointer pointer4 = pointer2;
        int i5 = i + this.cp;
        int i6 = i2 + pointer3.cp;
        int i7 = i3 + pointer4.cp;
        int i8 = 0;
        int i9 = i4;
        while (i8 < i9) {
            long[] jArr = this.array;
            jArr[i5] = (pointer3.array[i6] ^ pointer4.array[i7]) ^ jArr[i5];
            i8++;
            i5++;
            i7++;
            i6++;
        }
    }

    public void setXorRange_SelfMove(Pointer pointer, int i) {
        int i2 = pointer.cp;
        int i3 = 0;
        while (i3 < i) {
            long[] jArr = this.array;
            int i4 = this.cp;
            this.cp = i4 + 1;
            jArr[i4] = jArr[i4] ^ pointer.array[i2];
            i3++;
            i2++;
        }
    }

    public void swap(Pointer pointer) {
        long[] jArr = pointer.array;
        int i = pointer.cp;
        pointer.array = this.array;
        pointer.cp = this.cp;
        this.array = jArr;
        this.cp = i;
    }

    public byte[] toBytes(int i) {
        byte[] bArr = new byte[i];
        for (int i2 = 0; i2 < i; i2++) {
            bArr[i2] = (byte) ((int) (this.array[this.cp + (i2 >>> 3)] >>> ((i2 & 7) << 3)));
        }
        return bArr;
    }
}
