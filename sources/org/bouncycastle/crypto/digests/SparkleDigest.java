package org.bouncycastle.crypto.digests;

import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.ExtendedDigest;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.engines.SparkleEngine;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Integers;
import org.bouncycastle.util.Pack;

public class SparkleDigest implements ExtendedDigest {
    private static final int RATE_BYTES = 16;
    private static final int RATE_WORDS = 4;
    private final int DIGEST_BYTES;
    private final int SPARKLE_STEPS_BIG;
    private final int SPARKLE_STEPS_SLIM;
    private final int STATE_WORDS;
    private String algorithmName;
    private final byte[] m_buf = new byte[16];
    private int m_bufPos = 0;
    private final int[] state;

    /* renamed from: org.bouncycastle.crypto.digests.SparkleDigest$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$bouncycastle$crypto$digests$SparkleDigest$SparkleParameters;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                org.bouncycastle.crypto.digests.SparkleDigest$SparkleParameters[] r0 = org.bouncycastle.crypto.digests.SparkleDigest.SparkleParameters.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$bouncycastle$crypto$digests$SparkleDigest$SparkleParameters = r0
                org.bouncycastle.crypto.digests.SparkleDigest$SparkleParameters r1 = org.bouncycastle.crypto.digests.SparkleDigest.SparkleParameters.ESCH256     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$bouncycastle$crypto$digests$SparkleDigest$SparkleParameters     // Catch:{ NoSuchFieldError -> 0x001d }
                org.bouncycastle.crypto.digests.SparkleDigest$SparkleParameters r1 = org.bouncycastle.crypto.digests.SparkleDigest.SparkleParameters.ESCH384     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.crypto.digests.SparkleDigest.AnonymousClass1.<clinit>():void");
        }
    }

    public static class Friend {
        /* access modifiers changed from: private */
        public static final Friend INSTANCE = new Friend();

        private Friend() {
        }
    }

    public enum SparkleParameters {
        ESCH256,
        ESCH384
    }

    public SparkleDigest(SparkleParameters sparkleParameters) {
        int i = AnonymousClass1.$SwitchMap$org$bouncycastle$crypto$digests$SparkleDigest$SparkleParameters[sparkleParameters.ordinal()];
        if (i == 1) {
            this.algorithmName = "ESCH-256";
            this.DIGEST_BYTES = 32;
            this.SPARKLE_STEPS_SLIM = 7;
            this.SPARKLE_STEPS_BIG = 11;
            this.STATE_WORDS = 12;
        } else if (i == 2) {
            this.algorithmName = "ESCH-384";
            this.DIGEST_BYTES = 48;
            this.SPARKLE_STEPS_SLIM = 8;
            this.SPARKLE_STEPS_BIG = 12;
            this.STATE_WORDS = 16;
        } else {
            throw new IllegalArgumentException("Invalid definition of SCHWAEMM instance");
        }
        this.state = new int[this.STATE_WORDS];
    }

    private static int ELL(int i) {
        return (i & 65535) ^ Integers.rotateRight(i, 16);
    }

    private void processBlock(byte[] bArr, int i, int i2) {
        int littleEndianToInt = Pack.littleEndianToInt(bArr, i);
        int littleEndianToInt2 = Pack.littleEndianToInt(bArr, i + 4);
        int littleEndianToInt3 = Pack.littleEndianToInt(bArr, i + 8);
        int littleEndianToInt4 = Pack.littleEndianToInt(bArr, i + 12);
        int ELL = ELL(littleEndianToInt ^ littleEndianToInt3);
        int ELL2 = ELL(littleEndianToInt2 ^ littleEndianToInt4);
        int[] iArr = this.state;
        iArr[0] = (littleEndianToInt ^ ELL2) ^ iArr[0];
        iArr[1] = (littleEndianToInt2 ^ ELL) ^ iArr[1];
        iArr[2] = iArr[2] ^ (littleEndianToInt3 ^ ELL2);
        iArr[3] = (littleEndianToInt4 ^ ELL) ^ iArr[3];
        iArr[4] = iArr[4] ^ ELL2;
        iArr[5] = iArr[5] ^ ELL;
        if (this.STATE_WORDS == 16) {
            iArr[6] = iArr[6] ^ ELL2;
            iArr[7] = ELL ^ iArr[7];
            SparkleEngine.sparkle_opt16(Friend.INSTANCE, this.state, i2);
            return;
        }
        SparkleEngine.sparkle_opt12(Friend.INSTANCE, this.state, i2);
    }

    public int doFinal(byte[] bArr, int i) {
        if (i <= bArr.length - this.DIGEST_BYTES) {
            int i2 = this.m_bufPos;
            if (i2 < 16) {
                int[] iArr = this.state;
                int i3 = (this.STATE_WORDS >> 1) - 1;
                iArr[i3] = iArr[i3] ^ 16777216;
                this.m_buf[i2] = Byte.MIN_VALUE;
                while (true) {
                    int i4 = this.m_bufPos + 1;
                    this.m_bufPos = i4;
                    if (i4 >= 16) {
                        break;
                    }
                    this.m_buf[i4] = 0;
                }
            } else {
                int[] iArr2 = this.state;
                int i5 = (this.STATE_WORDS >> 1) - 1;
                iArr2[i5] = iArr2[i5] ^ 33554432;
            }
            processBlock(this.m_buf, 0, this.SPARKLE_STEPS_BIG);
            Pack.intToLittleEndian(this.state, 0, 4, bArr, i);
            if (this.STATE_WORDS == 16) {
                SparkleEngine.sparkle_opt16(Friend.INSTANCE, this.state, this.SPARKLE_STEPS_SLIM);
                Pack.intToLittleEndian(this.state, 0, 4, bArr, i + 16);
                SparkleEngine.sparkle_opt16(Friend.INSTANCE, this.state, this.SPARKLE_STEPS_SLIM);
                Pack.intToLittleEndian(this.state, 0, 4, bArr, i + 32);
            } else {
                SparkleEngine.sparkle_opt12(Friend.INSTANCE, this.state, this.SPARKLE_STEPS_SLIM);
                Pack.intToLittleEndian(this.state, 0, 4, bArr, i + 16);
            }
            reset();
            return this.DIGEST_BYTES;
        }
        throw new OutputLengthException(this.algorithmName + " input buffer too short");
    }

    public String getAlgorithmName() {
        return this.algorithmName;
    }

    public int getByteLength() {
        return 16;
    }

    public int getDigestSize() {
        return this.DIGEST_BYTES;
    }

    public void reset() {
        Arrays.fill(this.state, 0);
        Arrays.fill(this.m_buf, (byte) 0);
        this.m_bufPos = 0;
    }

    public void update(byte b) {
        if (this.m_bufPos == 16) {
            processBlock(this.m_buf, 0, this.SPARKLE_STEPS_SLIM);
            this.m_bufPos = 0;
        }
        byte[] bArr = this.m_buf;
        int i = this.m_bufPos;
        this.m_bufPos = i + 1;
        bArr[i] = b;
    }

    public void update(byte[] bArr, int i, int i2) {
        if (i > bArr.length - i2) {
            throw new DataLengthException(this.algorithmName + " input buffer too short");
        } else if (i2 >= 1) {
            int i3 = this.m_bufPos;
            int i4 = 16 - i3;
            if (i2 <= i4) {
                System.arraycopy(bArr, i, this.m_buf, i3, i2);
                this.m_bufPos += i2;
                return;
            }
            if (i3 > 0) {
                System.arraycopy(bArr, i, this.m_buf, i3, i4);
                processBlock(this.m_buf, 0, this.SPARKLE_STEPS_SLIM);
            } else {
                i4 = 0;
            }
            while (true) {
                int i5 = i2 - i4;
                if (i5 > 16) {
                    processBlock(bArr, i + i4, this.SPARKLE_STEPS_SLIM);
                    i4 += 16;
                } else {
                    System.arraycopy(bArr, i + i4, this.m_buf, 0, i5);
                    this.m_bufPos = i5;
                    return;
                }
            }
        }
    }
}
