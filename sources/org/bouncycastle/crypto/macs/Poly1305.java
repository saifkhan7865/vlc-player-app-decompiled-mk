package org.bouncycastle.crypto.macs;

import androidx.core.view.accessibility.AccessibilityEventCompat;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.Pack;

public class Poly1305 implements Mac {
    private static final int BLOCK_SIZE = 16;
    private final BlockCipher cipher;
    private final byte[] currentBlock;
    private int currentBlockOffset;
    private int h0;
    private int h1;
    private int h2;
    private int h3;
    private int h4;
    private int k0;
    private int k1;
    private int k2;
    private int k3;
    private int r0;
    private int r1;
    private int r2;
    private int r3;
    private int r4;
    private int s1;
    private int s2;
    private int s3;
    private int s4;
    private final byte[] singleByte;

    public Poly1305() {
        this.singleByte = new byte[1];
        this.currentBlock = new byte[16];
        this.currentBlockOffset = 0;
        this.cipher = null;
    }

    public Poly1305(BlockCipher blockCipher) {
        this.singleByte = new byte[1];
        this.currentBlock = new byte[16];
        this.currentBlockOffset = 0;
        if (blockCipher.getBlockSize() == 16) {
            this.cipher = blockCipher;
            return;
        }
        throw new IllegalArgumentException("Poly1305 requires a 128 bit block cipher.");
    }

    private static final long mul32x32_64(int i, int i2) {
        return (((long) i) & 4294967295L) * ((long) i2);
    }

    private void processBlock() {
        int i = this.currentBlockOffset;
        if (i < 16) {
            this.currentBlock[i] = 1;
            for (int i2 = i + 1; i2 < 16; i2++) {
                this.currentBlock[i2] = 0;
            }
        }
        long littleEndianToInt = (long) Pack.littleEndianToInt(this.currentBlock, 0);
        long j = littleEndianToInt & 4294967295L;
        long littleEndianToInt2 = ((long) Pack.littleEndianToInt(this.currentBlock, 4)) & 4294967295L;
        long littleEndianToInt3 = ((long) Pack.littleEndianToInt(this.currentBlock, 8)) & 4294967295L;
        long littleEndianToInt4 = 4294967295L & ((long) Pack.littleEndianToInt(this.currentBlock, 12));
        int i3 = (int) (((long) this.h0) + (littleEndianToInt & 67108863));
        this.h0 = i3;
        this.h1 = (int) (((long) this.h1) + ((((littleEndianToInt2 << 32) | j) >>> 26) & 67108863));
        this.h2 = (int) (((long) this.h2) + (((littleEndianToInt2 | (littleEndianToInt3 << 32)) >>> 20) & 67108863));
        this.h3 = (int) (((long) this.h3) + ((((littleEndianToInt4 << 32) | littleEndianToInt3) >>> 14) & 67108863));
        int i4 = (int) (((long) this.h4) + (littleEndianToInt4 >>> 8));
        this.h4 = i4;
        if (this.currentBlockOffset == 16) {
            this.h4 = i4 + 16777216;
        }
        long mul32x32_64 = mul32x32_64(i3, this.r0) + mul32x32_64(this.h1, this.s4) + mul32x32_64(this.h2, this.s3) + mul32x32_64(this.h3, this.s2) + mul32x32_64(this.h4, this.s1);
        long mul32x32_642 = mul32x32_64(this.h0, this.r1) + mul32x32_64(this.h1, this.r0) + mul32x32_64(this.h2, this.s4) + mul32x32_64(this.h3, this.s3) + mul32x32_64(this.h4, this.s2);
        long mul32x32_643 = mul32x32_64(this.h0, this.r2) + mul32x32_64(this.h1, this.r1) + mul32x32_64(this.h2, this.r0) + mul32x32_64(this.h3, this.s4) + mul32x32_64(this.h4, this.s3);
        long mul32x32_644 = mul32x32_64(this.h0, this.r3) + mul32x32_64(this.h1, this.r2) + mul32x32_64(this.h2, this.r1) + mul32x32_64(this.h3, this.r0) + mul32x32_64(this.h4, this.s4);
        long j2 = mul32x32_642 + (mul32x32_64 >>> 26);
        long j3 = mul32x32_643 + (j2 >>> 26);
        this.h2 = ((int) j3) & 67108863;
        long j4 = mul32x32_644 + (j3 >>> 26);
        this.h3 = ((int) j4) & 67108863;
        long mul32x32_645 = mul32x32_64(this.h0, this.r4) + mul32x32_64(this.h1, this.r3) + mul32x32_64(this.h2, this.r2) + mul32x32_64(this.h3, this.r1) + mul32x32_64(this.h4, this.r0) + (j4 >>> 26);
        this.h4 = ((int) mul32x32_645) & 67108863;
        int i5 = (((int) mul32x32_64) & 67108863) + (((int) (mul32x32_645 >>> 26)) * 5);
        this.h1 = (((int) j2) & 67108863) + (i5 >>> 26);
        this.h0 = i5 & 67108863;
    }

    private void setKey(byte[] bArr, byte[] bArr2) {
        if (bArr.length == 32) {
            int i = 16;
            if (this.cipher == null || (bArr2 != null && bArr2.length == 16)) {
                int littleEndianToInt = Pack.littleEndianToInt(bArr, 0);
                int littleEndianToInt2 = Pack.littleEndianToInt(bArr, 4);
                int littleEndianToInt3 = Pack.littleEndianToInt(bArr, 8);
                int littleEndianToInt4 = Pack.littleEndianToInt(bArr, 12);
                this.r0 = 67108863 & littleEndianToInt;
                int i2 = ((littleEndianToInt >>> 26) | (littleEndianToInt2 << 6)) & 67108611;
                this.r1 = i2;
                int i3 = ((littleEndianToInt2 >>> 20) | (littleEndianToInt3 << 12)) & 67092735;
                this.r2 = i3;
                int i4 = ((littleEndianToInt3 >>> 14) | (littleEndianToInt4 << 18)) & 66076671;
                this.r3 = i4;
                int i5 = (littleEndianToInt4 >>> 8) & 1048575;
                this.r4 = i5;
                this.s1 = i2 * 5;
                this.s2 = i3 * 5;
                this.s3 = i4 * 5;
                this.s4 = i5 * 5;
                BlockCipher blockCipher = this.cipher;
                if (blockCipher != null) {
                    byte[] bArr3 = new byte[16];
                    blockCipher.init(true, new KeyParameter(bArr, 16, 16));
                    this.cipher.processBlock(bArr2, 0, bArr3, 0);
                    bArr = bArr3;
                    i = 0;
                }
                this.k0 = Pack.littleEndianToInt(bArr, i);
                this.k1 = Pack.littleEndianToInt(bArr, i + 4);
                this.k2 = Pack.littleEndianToInt(bArr, i + 8);
                this.k3 = Pack.littleEndianToInt(bArr, i + 12);
                return;
            }
            throw new IllegalArgumentException("Poly1305 requires a 128 bit IV.");
        }
        throw new IllegalArgumentException("Poly1305 key must be 256 bits.");
    }

    public int doFinal(byte[] bArr, int i) throws DataLengthException, IllegalStateException {
        if (i + 16 <= bArr.length) {
            if (this.currentBlockOffset > 0) {
                processBlock();
            }
            int i2 = this.h1;
            int i3 = this.h0;
            int i4 = i2 + (i3 >>> 26);
            int i5 = this.h2 + (i4 >>> 26);
            int i6 = this.h3 + (i5 >>> 26);
            int i7 = i5 & 67108863;
            int i8 = this.h4 + (i6 >>> 26);
            int i9 = i6 & 67108863;
            int i10 = (i3 & 67108863) + ((i8 >>> 26) * 5);
            int i11 = i8 & 67108863;
            int i12 = (i4 & 67108863) + (i10 >>> 26);
            int i13 = i10 & 67108863;
            int i14 = i13 + 5;
            int i15 = (i14 >>> 26) + i12;
            int i16 = (i15 >>> 26) + i7;
            int i17 = (i16 >>> 26) + i9;
            int i18 = 67108863 & i17;
            int i19 = ((i17 >>> 26) + i11) - AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
            int i20 = (i19 >>> 31) - 1;
            int i21 = i20 ^ -1;
            int i22 = (i13 & i21) | (i14 & 67108863 & i20);
            this.h0 = i22;
            int i23 = (i12 & i21) | (i15 & 67108863 & i20);
            this.h1 = i23;
            int i24 = (i7 & i21) | (i16 & 67108863 & i20);
            this.h2 = i24;
            int i25 = (i18 & i20) | (i9 & i21);
            this.h3 = i25;
            int i26 = (i11 & i21) | (i19 & i20);
            this.h4 = i26;
            long j = (((long) (i22 | (i23 << 26))) & 4294967295L) + (((long) this.k0) & 4294967295L);
            long j2 = (((long) ((i23 >>> 6) | (i24 << 20))) & 4294967295L) + (((long) this.k1) & 4294967295L);
            long j3 = (((long) ((i24 >>> 12) | (i25 << 14))) & 4294967295L) + (((long) this.k2) & 4294967295L);
            Pack.intToLittleEndian((int) j, bArr, i);
            long j4 = j2 + (j >>> 32);
            Pack.intToLittleEndian((int) j4, bArr, i + 4);
            long j5 = j3 + (j4 >>> 32);
            Pack.intToLittleEndian((int) j5, bArr, i + 8);
            Pack.intToLittleEndian((int) ((((long) ((i25 >>> 18) | (i26 << 8))) & 4294967295L) + (4294967295L & ((long) this.k3)) + (j5 >>> 32)), bArr, i + 12);
            reset();
            return 16;
        }
        throw new OutputLengthException("Output buffer is too short.");
    }

    public String getAlgorithmName() {
        if (this.cipher == null) {
            return "Poly1305";
        }
        return "Poly1305-" + this.cipher.getAlgorithmName();
    }

    public int getMacSize() {
        return 16;
    }

    public void init(CipherParameters cipherParameters) throws IllegalArgumentException {
        byte[] bArr;
        if (this.cipher == null) {
            bArr = null;
        } else if (cipherParameters instanceof ParametersWithIV) {
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            bArr = parametersWithIV.getIV();
            cipherParameters = parametersWithIV.getParameters();
        } else {
            throw new IllegalArgumentException("Poly1305 requires an IV when used with a block cipher.");
        }
        if (cipherParameters instanceof KeyParameter) {
            setKey(((KeyParameter) cipherParameters).getKey(), bArr);
            reset();
            return;
        }
        throw new IllegalArgumentException("Poly1305 requires a key.");
    }

    public void reset() {
        this.currentBlockOffset = 0;
        this.h4 = 0;
        this.h3 = 0;
        this.h2 = 0;
        this.h1 = 0;
        this.h0 = 0;
    }

    public void update(byte b) throws IllegalStateException {
        byte[] bArr = this.singleByte;
        bArr[0] = b;
        update(bArr, 0, 1);
    }

    public void update(byte[] bArr, int i, int i2) throws DataLengthException, IllegalStateException {
        int i3 = 0;
        while (i2 > i3) {
            if (this.currentBlockOffset == 16) {
                processBlock();
                this.currentBlockOffset = 0;
            }
            int min = Math.min(i2 - i3, 16 - this.currentBlockOffset);
            System.arraycopy(bArr, i3 + i, this.currentBlock, this.currentBlockOffset, min);
            i3 += min;
            this.currentBlockOffset += min;
        }
    }
}
