package org.bouncycastle.crypto.digests;

import java.io.ByteArrayOutputStream;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.ExtendedDigest;
import org.bouncycastle.crypto.OutputLengthException;

public class AsconDigest implements ExtendedDigest {
    private final int ASCON_PB_ROUNDS;
    private final int CRYPTO_BYTES = 32;
    private final String algorithmName;
    AsconParameters asconParameters;
    private final ByteArrayOutputStream buffer = new ByteArrayOutputStream();
    private long x0;
    private long x1;
    private long x2;
    private long x3;
    private long x4;

    /* renamed from: org.bouncycastle.crypto.digests.AsconDigest$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$bouncycastle$crypto$digests$AsconDigest$AsconParameters;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                org.bouncycastle.crypto.digests.AsconDigest$AsconParameters[] r0 = org.bouncycastle.crypto.digests.AsconDigest.AsconParameters.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$bouncycastle$crypto$digests$AsconDigest$AsconParameters = r0
                org.bouncycastle.crypto.digests.AsconDigest$AsconParameters r1 = org.bouncycastle.crypto.digests.AsconDigest.AsconParameters.AsconHash     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$bouncycastle$crypto$digests$AsconDigest$AsconParameters     // Catch:{ NoSuchFieldError -> 0x001d }
                org.bouncycastle.crypto.digests.AsconDigest$AsconParameters r1 = org.bouncycastle.crypto.digests.AsconDigest.AsconParameters.AsconHashA     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.crypto.digests.AsconDigest.AnonymousClass1.<clinit>():void");
        }
    }

    public enum AsconParameters {
        AsconHash,
        AsconHashA
    }

    public AsconDigest(AsconParameters asconParameters2) {
        String str;
        this.asconParameters = asconParameters2;
        int i = AnonymousClass1.$SwitchMap$org$bouncycastle$crypto$digests$AsconDigest$AsconParameters[asconParameters2.ordinal()];
        if (i == 1) {
            this.ASCON_PB_ROUNDS = 12;
            str = "Ascon-Hash";
        } else if (i == 2) {
            this.ASCON_PB_ROUNDS = 8;
            str = "Ascon-HashA";
        } else {
            throw new IllegalArgumentException("Invalid parameter settings for Ascon Hash");
        }
        this.algorithmName = str;
        reset();
    }

    private long LOADBYTES(byte[] bArr, int i, int i2) {
        long j = 0;
        for (int i3 = 0; i3 < i2; i3++) {
            j |= (((long) bArr[i3 + i]) & 255) << ((7 - i3) << 3);
        }
        return j;
    }

    private void P(int i) {
        if (i == 12) {
            ROUND(240);
            ROUND(225);
            ROUND(210);
            ROUND(195);
        }
        if (i >= 8) {
            ROUND(180);
            ROUND(165);
        }
        ROUND(150);
        ROUND(135);
        ROUND(120);
        ROUND(105);
        ROUND(90);
        ROUND(75);
    }

    private long PAD(int i) {
        return 128 << (56 - (i << 3));
    }

    private long ROR(long j, int i) {
        return (j << (64 - i)) | (j >>> i);
    }

    private void ROUND(long j) {
        long j2 = this.x0;
        long j3 = this.x1;
        long j4 = this.x2;
        long j5 = this.x3;
        long j6 = this.x4;
        long j7 = ((((j2 ^ j3) ^ j4) ^ j5) ^ j) ^ ((((j2 ^ j4) ^ j6) ^ j) & j3);
        long j8 = ((((j2 ^ j4) ^ j5) ^ j6) ^ j) ^ (((j3 ^ j4) ^ j) & (j3 ^ j5));
        long j9 = (((j3 ^ j4) ^ j6) ^ j) ^ (j5 & j6);
        long j10 = ((j4 ^ (j2 ^ j3)) ^ j) ^ ((j2 ^ -1) & (j5 ^ j6));
        long j11 = ((j2 ^ j6) & j3) ^ ((j5 ^ j3) ^ j6);
        this.x0 = (ROR(j7, 19) ^ j7) ^ ROR(j7, 28);
        long j12 = j8;
        this.x1 = ROR(j12, 61) ^ (ROR(j12, 39) ^ j12);
        this.x2 = ((ROR(j9, 1) ^ j9) ^ ROR(j9, 6)) ^ -1;
        this.x3 = (ROR(j10, 10) ^ j10) ^ ROR(j10, 17);
        this.x4 = ROR(j11, 41) ^ (ROR(j11, 7) ^ j11);
    }

    private void STOREBYTES(byte[] bArr, int i, long j, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            bArr[i3 + i] = (byte) ((int) (j >>> ((7 - i3) << 3)));
        }
    }

    public int doFinal(byte[] bArr, int i) {
        long j;
        if (i + 32 <= bArr.length) {
            byte[] byteArray = this.buffer.toByteArray();
            int size = this.buffer.size();
            int i2 = 0;
            while (true) {
                j = this.x0;
                if (size < 8) {
                    break;
                }
                this.x0 = j ^ LOADBYTES(byteArray, i2, 8);
                P(this.ASCON_PB_ROUNDS);
                i2 += 8;
                size -= 8;
            }
            long LOADBYTES = j ^ LOADBYTES(byteArray, i2, size);
            this.x0 = LOADBYTES;
            this.x0 = PAD(size) ^ LOADBYTES;
            P(12);
            int i3 = 32;
            while (true) {
                long j2 = this.x0;
                if (i3 > 8) {
                    STOREBYTES(bArr, i, j2, 8);
                    P(this.ASCON_PB_ROUNDS);
                    i += 8;
                    i3 -= 8;
                } else {
                    STOREBYTES(bArr, i, j2, i3);
                    reset();
                    return 32;
                }
            }
        } else {
            throw new OutputLengthException("output buffer is too short");
        }
    }

    public String getAlgorithmName() {
        return this.algorithmName;
    }

    public int getByteLength() {
        return 8;
    }

    public int getDigestSize() {
        return 32;
    }

    public void reset() {
        long j;
        this.buffer.reset();
        int i = AnonymousClass1.$SwitchMap$org$bouncycastle$crypto$digests$AsconDigest$AsconParameters[this.asconParameters.ordinal()];
        if (i == 1) {
            this.x0 = -1255492011513352131L;
            this.x1 = -8380609354527731710L;
            this.x2 = -5437372128236807582L;
            this.x3 = 4834782570098516968L;
            j = 3787428097924915520L;
        } else if (i == 2) {
            this.x0 = 92044056785660070L;
            this.x1 = 8326807761760157607L;
            this.x2 = 3371194088139667532L;
            this.x3 = -2956994353054992515L;
            j = -6828509670848688761L;
        } else {
            return;
        }
        this.x4 = j;
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
