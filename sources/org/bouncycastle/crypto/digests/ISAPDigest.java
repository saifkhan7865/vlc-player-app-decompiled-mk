package org.bouncycastle.crypto.digests;

import java.io.ByteArrayOutputStream;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.util.Pack;

public class ISAPDigest implements Digest {
    private ByteArrayOutputStream buffer = new ByteArrayOutputStream();
    private long t0;
    private long t1;
    private long t2;
    private long t3;
    private long t4;
    private long x0;
    private long x1;
    private long x2;
    private long x3;
    private long x4;

    private void P12() {
        ROUND(240);
        ROUND(225);
        ROUND(210);
        ROUND(195);
        ROUND(180);
        ROUND(165);
        ROUND(150);
        ROUND(135);
        ROUND(120);
        ROUND(105);
        ROUND(90);
        ROUND(75);
    }

    private long ROTR(long j, long j2) {
        return (j << ((int) (64 - j2))) | (j >>> ((int) j2));
    }

    private void ROUND(long j) {
        long j2 = this.x0;
        long j3 = this.x1;
        long j4 = this.x2;
        long j5 = this.x3;
        long j6 = this.x4;
        long j7 = ((((j2 ^ j3) ^ j4) ^ j5) ^ j) ^ ((((j2 ^ j4) ^ j6) ^ j) & j3);
        this.t0 = j7;
        this.t1 = ((((j2 ^ j4) ^ j5) ^ j6) ^ j) ^ (((j3 ^ j4) ^ j) & (j3 ^ j5));
        this.t2 = (((j3 ^ j4) ^ j6) ^ j) ^ (j5 & j6);
        this.t3 = ((j4 ^ (j2 ^ j3)) ^ j) ^ ((j2 ^ -1) & (j5 ^ j6));
        this.t4 = ((j2 ^ j6) & j3) ^ ((j3 ^ j5) ^ j6);
        this.x0 = (ROTR(j7, 19) ^ j7) ^ ROTR(this.t0, 28);
        long j8 = this.t1;
        this.x1 = (j8 ^ ROTR(j8, 39)) ^ ROTR(this.t1, 61);
        long j9 = this.t2;
        this.x2 = ((j9 ^ ROTR(j9, 1)) ^ ROTR(this.t2, 6)) ^ -1;
        long j10 = this.t3;
        this.x3 = (j10 ^ ROTR(j10, 10)) ^ ROTR(this.t3, 17);
        long j11 = this.t4;
        this.x4 = (j11 ^ ROTR(j11, 7)) ^ ROTR(this.t4, 41);
    }

    /* access modifiers changed from: protected */
    public long U64BIG(long j) {
        return (ROTR(j, 56) & 1095216660735L) | (ROTR(j, 8) & -72057589759737856L) | (ROTR(j, 24) & 71776119077928960L) | (ROTR(j, 40) & 280375465148160L);
    }

    public int doFinal(byte[] bArr, int i) {
        if (i + 32 <= bArr.length) {
            this.t4 = 0;
            this.t3 = 0;
            this.t2 = 0;
            this.t1 = 0;
            this.t0 = 0;
            this.x0 = -1255492011513352131L;
            this.x1 = -8380609354527731710L;
            this.x2 = -5437372128236807582L;
            this.x3 = 4834782570098516968L;
            this.x4 = 3787428097924915520L;
            byte[] byteArray = this.buffer.toByteArray();
            int length = byteArray.length;
            int i2 = length >> 3;
            long[] jArr = new long[i2];
            int i3 = 0;
            Pack.littleEndianToLong(byteArray, 0, jArr, 0, i2);
            int i4 = 0;
            while (length >= 8) {
                this.x0 ^= U64BIG(jArr[i4]);
                P12();
                length -= 8;
                i4++;
            }
            long j = this.x0;
            int i5 = (7 - length) << 3;
            long j2 = 128;
            while (true) {
                this.x0 = j ^ (j2 << i5);
                if (length <= 0) {
                    break;
                }
                j = this.x0;
                length--;
                j2 = ((long) byteArray[(i4 << 3) + length]) & 255;
                i5 = (7 - length) << 3;
            }
            P12();
            long[] jArr2 = new long[4];
            while (true) {
                long U64BIG = U64BIG(this.x0);
                if (i3 < 3) {
                    jArr2[i3] = U64BIG;
                    P12();
                    i3++;
                } else {
                    jArr2[i3] = U64BIG;
                    Pack.longToLittleEndian(jArr2, bArr, i);
                    this.buffer.reset();
                    return 32;
                }
            }
        } else {
            throw new OutputLengthException("output buffer is too short");
        }
    }

    public String getAlgorithmName() {
        return "ISAP Hash";
    }

    public int getDigestSize() {
        return 32;
    }

    public void reset() {
        this.buffer.reset();
    }

    public void update(byte b) {
        this.buffer.write(b);
    }

    public void update(byte[] bArr, int i, int i2) {
        if (i + i2 <= bArr.length) {
            this.buffer.write(bArr, i, i2);
            return;
        }
        throw new DataLengthException("input buffer too short");
    }
}
