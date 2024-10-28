package org.bouncycastle.pqc.math.ntru;

import io.netty.handler.codec.http2.Http2CodecUtil;
import org.bouncycastle.pqc.math.ntru.parameters.NTRUParameterSet;

public abstract class Polynomial {
    public short[] coeffs;
    protected NTRUParameterSet params;

    public Polynomial(NTRUParameterSet nTRUParameterSet) {
        this.coeffs = new short[nTRUParameterSet.n()];
        this.params = nTRUParameterSet;
    }

    static short bothNegativeMask(short s, short s2) {
        return (short) ((s & s2) >>> 15);
    }

    static byte mod3(byte b) {
        return (byte) ((b & 255) % 3);
    }

    static short mod3(short s) {
        return (short) ((s & 65535) % 3);
    }

    static int modQ(int i, int i2) {
        return i % i2;
    }

    private void r2InvToRqInv(Polynomial polynomial, Polynomial polynomial2, Polynomial polynomial3, Polynomial polynomial4, Polynomial polynomial5) {
        int length = this.coeffs.length;
        for (int i = 0; i < length; i++) {
            polynomial3.coeffs[i] = (short) (-polynomial2.coeffs[i]);
        }
        for (int i2 = 0; i2 < length; i2++) {
            this.coeffs[i2] = polynomial.coeffs[i2];
        }
        polynomial4.rqMul(this, polynomial3);
        short[] sArr = polynomial4.coeffs;
        sArr[0] = (short) (sArr[0] + 2);
        polynomial5.rqMul(polynomial4, this);
        polynomial4.rqMul(polynomial5, polynomial3);
        short[] sArr2 = polynomial4.coeffs;
        sArr2[0] = (short) (sArr2[0] + 2);
        rqMul(polynomial4, polynomial5);
        polynomial4.rqMul(this, polynomial3);
        short[] sArr3 = polynomial4.coeffs;
        sArr3[0] = (short) (sArr3[0] + 2);
        polynomial5.rqMul(polynomial4, this);
        polynomial4.rqMul(polynomial5, polynomial3);
        short[] sArr4 = polynomial4.coeffs;
        sArr4[0] = (short) (sArr4[0] + 2);
        rqMul(polynomial4, polynomial5);
    }

    public abstract void lift(Polynomial polynomial);

    public void mod3PhiN() {
        int n = this.params.n();
        for (int i = 0; i < n; i++) {
            short[] sArr = this.coeffs;
            sArr[i] = mod3((short) (sArr[i] + (sArr[n - 1] * 2)));
        }
    }

    public void modQPhiN() {
        int n = this.params.n();
        for (int i = 0; i < n; i++) {
            short[] sArr = this.coeffs;
            sArr[i] = (short) (sArr[i] - sArr[n - 1]);
        }
    }

    public abstract void r2Inv(Polynomial polynomial);

    /* access modifiers changed from: package-private */
    public void r2Inv(Polynomial polynomial, Polynomial polynomial2, Polynomial polynomial3, Polynomial polynomial4, Polynomial polynomial5) {
        int i;
        Polynomial polynomial6 = polynomial2;
        Polynomial polynomial7 = polynomial3;
        Polynomial polynomial8 = polynomial4;
        Polynomial polynomial9 = polynomial5;
        int length = this.coeffs.length;
        short s = 0;
        polynomial9.coeffs[0] = 1;
        for (int i2 = 0; i2 < length; i2++) {
            polynomial6.coeffs[i2] = 1;
        }
        int i3 = 0;
        while (true) {
            i = length - 1;
            if (i3 >= i) {
                break;
            }
            short[] sArr = polynomial.coeffs;
            polynomial7.coeffs[(length - 2) - i3] = (short) ((sArr[i] ^ sArr[i3]) & 1);
            i3++;
        }
        polynomial7.coeffs[i] = 0;
        int i4 = 0;
        short s2 = 1;
        for (short s3 = 1; i4 < (i * 2) - s3; s3 = 1) {
            for (int i5 = i; i5 > 0; i5--) {
                short[] sArr2 = polynomial8.coeffs;
                sArr2[i5] = sArr2[i5 - 1];
            }
            polynomial8.coeffs[s] = s;
            short s4 = polynomial7.coeffs[s];
            short s5 = (short) (polynomial6.coeffs[s] & s4);
            int i6 = -s2;
            short bothNegativeMask = bothNegativeMask((short) i6, (short) (-s4));
            s2 = (short) (((short) (s2 ^ ((i6 ^ s2) & bothNegativeMask))) + s3);
            for (int i7 = 0; i7 < length; i7++) {
                short[] sArr3 = polynomial6.coeffs;
                short s6 = sArr3[i7];
                short[] sArr4 = polynomial7.coeffs;
                short s7 = (short) (bothNegativeMask & (s6 ^ sArr4[i7]));
                sArr3[i7] = (short) (s6 ^ s7);
                sArr4[i7] = (short) (s7 ^ sArr4[i7]);
                short[] sArr5 = polynomial8.coeffs;
                short s8 = sArr5[i7];
                short[] sArr6 = polynomial9.coeffs;
                short s9 = (short) ((sArr6[i7] ^ s8) & bothNegativeMask);
                sArr5[i7] = (short) (s8 ^ s9);
                sArr6[i7] = (short) (sArr6[i7] ^ s9);
            }
            for (int i8 = 0; i8 < length; i8++) {
                short[] sArr7 = polynomial7.coeffs;
                sArr7[i8] = (short) (sArr7[i8] ^ (polynomial6.coeffs[i8] & s5));
            }
            for (int i9 = 0; i9 < length; i9++) {
                short[] sArr8 = polynomial9.coeffs;
                sArr8[i9] = (short) (sArr8[i9] ^ (polynomial8.coeffs[i9] & s5));
            }
            int i10 = 0;
            while (i10 < i) {
                short[] sArr9 = polynomial7.coeffs;
                int i11 = i10 + 1;
                sArr9[i10] = sArr9[i11];
                i10 = i11;
            }
            polynomial7.coeffs[i] = 0;
            i4++;
            s = 0;
        }
        int i12 = 0;
        while (true) {
            short[] sArr10 = this.coeffs;
            if (i12 < i) {
                sArr10[i12] = polynomial8.coeffs[(length - 2) - i12];
                i12++;
            } else {
                sArr10[i] = 0;
                return;
            }
        }
    }

    public abstract void rqInv(Polynomial polynomial);

    /* access modifiers changed from: package-private */
    public void rqInv(Polynomial polynomial, Polynomial polynomial2, Polynomial polynomial3, Polynomial polynomial4, Polynomial polynomial5) {
        polynomial2.r2Inv(polynomial);
        r2InvToRqInv(polynomial2, polynomial, polynomial3, polynomial4, polynomial5);
    }

    public void rqMul(Polynomial polynomial, Polynomial polynomial2) {
        int i;
        int length = this.coeffs.length;
        int i2 = 0;
        while (i2 < length) {
            this.coeffs[i2] = 0;
            for (int i3 = 1; i3 < length - i2; i3++) {
                short[] sArr = this.coeffs;
                sArr[i2] = (short) (sArr[i2] + (polynomial.coeffs[i2 + i3] * polynomial2.coeffs[length - i3]));
            }
            int i4 = 0;
            while (true) {
                i = i2 + 1;
                if (i4 >= i) {
                    break;
                }
                short[] sArr2 = this.coeffs;
                sArr2[i2] = (short) (sArr2[i2] + (polynomial.coeffs[i2 - i4] * polynomial2.coeffs[i4]));
                i4++;
            }
            i2 = i;
        }
    }

    public void rqSumZeroFromBytes(byte[] bArr) {
        int length = this.coeffs.length;
        sqFromBytes(bArr);
        int i = length - 1;
        this.coeffs[i] = 0;
        for (int i2 = 0; i2 < this.params.packDegree(); i2++) {
            short[] sArr = this.coeffs;
            sArr[i] = (short) (sArr[i] - sArr[i2]);
        }
    }

    public byte[] rqSumZeroToBytes(int i) {
        return sqToBytes(i);
    }

    public void rqToS3(Polynomial polynomial) {
        int length = this.coeffs.length;
        for (int i = 0; i < length; i++) {
            this.coeffs[i] = (short) modQ(polynomial.coeffs[i] & 65535, this.params.q());
            short[] sArr = this.coeffs;
            sArr[i] = (short) (sArr[i] + (((short) (this.coeffs[i] >>> (this.params.logQ() - 1))) << (1 - (this.params.logQ() & 1))));
        }
        mod3PhiN();
    }

    public void s3FromBytes(byte[] bArr) {
        int length = this.coeffs.length;
        for (int i = 0; i < this.params.packDegree() / 5; i++) {
            byte b = bArr[i];
            short[] sArr = this.coeffs;
            int i2 = i * 5;
            sArr[i2] = (short) b;
            byte b2 = b & 255;
            sArr[i2 + 1] = (short) ((b2 * 171) >>> 9);
            sArr[i2 + 2] = (short) ((b2 * 57) >>> 9);
            sArr[i2 + 3] = (short) ((b2 * 19) >>> 9);
            sArr[i2 + 4] = (short) ((b2 * 203) >>> 14);
        }
        if (this.params.packDegree() > (this.params.packDegree() / 5) * 5) {
            int packDegree = this.params.packDegree() / 5;
            byte b3 = bArr[packDegree];
            int i3 = 0;
            while (true) {
                int i4 = (packDegree * 5) + i3;
                if (i4 >= this.params.packDegree()) {
                    break;
                }
                this.coeffs[i4] = (short) b3;
                b3 = (byte) (((b3 & 255) * 171) >> 9);
                i3++;
            }
        }
        this.coeffs[length - 1] = 0;
        mod3PhiN();
    }

    public abstract void s3Inv(Polynomial polynomial);

    /* access modifiers changed from: package-private */
    public void s3Inv(Polynomial polynomial, Polynomial polynomial2, Polynomial polynomial3, Polynomial polynomial4, Polynomial polynomial5) {
        int i;
        Polynomial polynomial6 = polynomial2;
        Polynomial polynomial7 = polynomial3;
        Polynomial polynomial8 = polynomial4;
        Polynomial polynomial9 = polynomial5;
        int length = this.coeffs.length;
        short s = 0;
        polynomial9.coeffs[0] = 1;
        for (int i2 = 0; i2 < length; i2++) {
            polynomial6.coeffs[i2] = 1;
        }
        int i3 = 0;
        while (true) {
            i = length - 1;
            if (i3 >= i) {
                break;
            }
            short[] sArr = polynomial.coeffs;
            polynomial7.coeffs[(length - 2) - i3] = mod3((short) ((sArr[i3] & 3) + ((sArr[i] & 3) * 2)));
            i3++;
        }
        polynomial7.coeffs[i] = 0;
        int i4 = 0;
        short s2 = 1;
        for (short s3 = 1; i4 < (i * 2) - s3; s3 = 1) {
            for (int i5 = i; i5 > 0; i5--) {
                short[] sArr2 = polynomial8.coeffs;
                sArr2[i5] = sArr2[i5 - 1];
            }
            polynomial8.coeffs[s] = s;
            short mod3 = (short) mod3((byte) (polynomial7.coeffs[s] * 2 * polynomial6.coeffs[s]));
            int i6 = -s2;
            short bothNegativeMask = bothNegativeMask((short) i6, (short) (-polynomial7.coeffs[s]));
            s2 = (short) (((short) (s2 ^ ((i6 ^ s2) & bothNegativeMask))) + s3);
            for (int i7 = 0; i7 < length; i7++) {
                short[] sArr3 = polynomial6.coeffs;
                short s4 = sArr3[i7];
                short[] sArr4 = polynomial7.coeffs;
                short s5 = (short) (bothNegativeMask & (s4 ^ sArr4[i7]));
                sArr3[i7] = (short) (s4 ^ s5);
                sArr4[i7] = (short) (s5 ^ sArr4[i7]);
                short[] sArr5 = polynomial8.coeffs;
                short s6 = sArr5[i7];
                short[] sArr6 = polynomial9.coeffs;
                short s7 = (short) ((sArr6[i7] ^ s6) & bothNegativeMask);
                sArr5[i7] = (short) (s6 ^ s7);
                sArr6[i7] = (short) (sArr6[i7] ^ s7);
            }
            for (int i8 = 0; i8 < length; i8++) {
                short[] sArr7 = polynomial7.coeffs;
                sArr7[i8] = (short) mod3((byte) (sArr7[i8] + (polynomial6.coeffs[i8] * mod3)));
            }
            for (int i9 = 0; i9 < length; i9++) {
                short[] sArr8 = polynomial9.coeffs;
                sArr8[i9] = (short) mod3((byte) (sArr8[i9] + (polynomial8.coeffs[i9] * mod3)));
            }
            int i10 = 0;
            while (i10 < i) {
                short[] sArr9 = polynomial7.coeffs;
                int i11 = i10 + 1;
                sArr9[i10] = sArr9[i11];
                i10 = i11;
            }
            polynomial7.coeffs[i] = 0;
            i4++;
            s = 0;
        }
        short s8 = polynomial6.coeffs[0];
        for (int i12 = 0; i12 < i; i12++) {
            this.coeffs[i12] = (short) mod3((byte) (polynomial8.coeffs[(length - 2) - i12] * s8));
        }
        this.coeffs[i] = 0;
    }

    public void s3Mul(Polynomial polynomial, Polynomial polynomial2) {
        rqMul(polynomial, polynomial2);
        mod3PhiN();
    }

    public byte[] s3ToBytes(int i) {
        byte[] bArr = new byte[i];
        byte b = 0;
        for (int i2 = 0; i2 < this.params.packDegree() / 5; i2++) {
            short[] sArr = this.coeffs;
            int i3 = i2 * 5;
            bArr[i2] = (byte) (((((byte) (((((byte) (((((byte) (((((byte) (sArr[i3 + 4] & Http2CodecUtil.MAX_UNSIGNED_BYTE)) * 3) + sArr[i3 + 3]) & 255)) * 3) + sArr[i3 + 2]) & 255)) * 3) + sArr[i3 + 1]) & 255)) * 3) + sArr[i3]) & 255);
        }
        if (this.params.packDegree() > (this.params.packDegree() / 5) * 5) {
            int packDegree = this.params.packDegree() / 5;
            int i4 = packDegree * 5;
            for (int packDegree2 = (this.params.packDegree() - i4) - 1; packDegree2 >= 0; packDegree2--) {
                b = (byte) (((b * 3) + this.coeffs[i4 + packDegree2]) & 255);
            }
            bArr[packDegree] = b;
        }
        return bArr;
    }

    public abstract void sqFromBytes(byte[] bArr);

    public void sqMul(Polynomial polynomial, Polynomial polynomial2) {
        rqMul(polynomial, polynomial2);
        modQPhiN();
    }

    public abstract byte[] sqToBytes(int i);

    public void trinaryZqToZ3() {
        int length = this.coeffs.length;
        for (int i = 0; i < length; i++) {
            short[] sArr = this.coeffs;
            sArr[i] = (short) modQ(sArr[i] & 65535, this.params.q());
            short[] sArr2 = this.coeffs;
            short s = sArr2[i];
            sArr2[i] = (short) ((s ^ (s >>> (this.params.logQ() - 1))) & 3);
        }
    }

    public void z3ToZq() {
        int length = this.coeffs.length;
        for (int i = 0; i < length; i++) {
            short[] sArr = this.coeffs;
            short s = sArr[i];
            sArr[i] = (short) (s | ((-(s >>> 1)) & (this.params.q() - 1)));
        }
    }
}
