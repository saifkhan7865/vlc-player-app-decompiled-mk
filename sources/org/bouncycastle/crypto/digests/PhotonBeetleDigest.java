package org.bouncycastle.crypto.digests;

import com.google.common.base.Ascii;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Array;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Bytes;

public class PhotonBeetleDigest implements Digest {
    private int D = 8;
    private int DSquare = 64;
    private int Dq = 3;
    private int Dr = 7;
    private final int INITIAL_RATE_INBYTES = 16;
    private int LAST_THREE_BITS_OFFSET = 5;
    private byte[][] MixColMatrix = {new byte[]{2, 4, 2, Ascii.VT, 2, 8, 5, 6}, new byte[]{Ascii.FF, 9, 8, 13, 7, 7, 5, 2}, new byte[]{4, 4, 13, 13, 9, 4, 13, 9}, new byte[]{1, 6, 5, 1, Ascii.FF, 13, Ascii.SI, Ascii.SO}, new byte[]{Ascii.SI, Ascii.FF, 9, 13, Ascii.SO, 5, Ascii.SO, 13}, new byte[]{9, Ascii.SO, 5, Ascii.SI, 4, Ascii.FF, 9, 6}, new byte[]{Ascii.FF, 2, 2, 10, 3, 1, 1, Ascii.SO}, new byte[]{Ascii.SI, 1, 13, 10, 5, 10, 2, 3}};
    private int RATE_INBYTES = 4;
    private byte[][] RC = {new byte[]{1, 3, 7, Ascii.SO, 13, Ascii.VT, 6, Ascii.FF, 9, 2, 5, 10}, new byte[]{0, 2, 6, Ascii.SI, Ascii.FF, 10, 7, 13, 8, 3, 4, Ascii.VT}, new byte[]{2, 0, 4, 13, Ascii.SO, 8, 5, Ascii.SI, 10, 1, 6, 9}, new byte[]{6, 4, 0, 9, 10, Ascii.FF, 1, Ascii.VT, Ascii.SO, 5, 2, 13}, new byte[]{Ascii.SO, Ascii.FF, 8, 1, 2, 4, 9, 3, 6, 13, 10, 5}, new byte[]{Ascii.SI, 13, 9, 0, 3, 5, 8, 2, 7, Ascii.FF, Ascii.VT, 4}, new byte[]{13, Ascii.SI, Ascii.VT, 2, 1, 7, 10, 0, 5, Ascii.SO, 9, 6}, new byte[]{9, Ascii.VT, Ascii.SI, 6, 5, 3, Ascii.SO, 4, 1, 10, 13, 2}};
    private int ROUND = 12;
    private int S = 4;
    private int SQUEEZE_RATE_INBYTES = 16;
    private int STATE_INBYTES = 32;
    private int S_1 = 3;
    private int TAG_INBYTES = 32;
    private ByteArrayOutputStream buffer = new ByteArrayOutputStream();
    private byte[] sbox = {Ascii.FF, 5, 6, Ascii.VT, 9, 0, 10, 13, 3, Ascii.SO, Ascii.SI, 8, 4, 7, 1, 2};
    private byte[] state = new byte[32];
    private byte[][] state_2d;

    public PhotonBeetleDigest() {
        int[] iArr = new int[2];
        iArr[1] = 8;
        iArr[0] = 8;
        this.state_2d = (byte[][]) Array.newInstance(Byte.TYPE, iArr);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v8, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v10, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v11, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v12, resolved type: byte} */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=int, for r7v4, types: [byte] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void PHOTON_Permutation() {
        /*
            r12 = this;
            r0 = 0
            r1 = 0
        L_0x0002:
            int r2 = r12.DSquare
            if (r1 >= r2) goto L_0x0026
            byte[][] r2 = r12.state_2d
            int r3 = r12.Dq
            int r3 = r1 >>> r3
            r2 = r2[r3]
            int r3 = r12.Dr
            r3 = r3 & r1
            byte[] r4 = r12.state
            int r5 = r1 >> 1
            byte r4 = r4[r5]
            r4 = r4 & 255(0xff, float:3.57E-43)
            r5 = r1 & 1
            int r5 = r5 * 4
            int r4 = r4 >>> r5
            r4 = r4 & 15
            byte r4 = (byte) r4
            r2[r3] = r4
            int r1 = r1 + 1
            goto L_0x0002
        L_0x0026:
            r1 = 0
        L_0x0027:
            int r2 = r12.ROUND
            if (r1 >= r2) goto L_0x00e6
            r2 = 0
        L_0x002c:
            int r3 = r12.D
            if (r2 >= r3) goto L_0x0043
            byte[][] r3 = r12.state_2d
            r3 = r3[r2]
            byte r4 = r3[r0]
            byte[][] r5 = r12.RC
            r5 = r5[r2]
            byte r5 = r5[r1]
            r4 = r4 ^ r5
            byte r4 = (byte) r4
            r3[r0] = r4
            int r2 = r2 + 1
            goto L_0x002c
        L_0x0043:
            r2 = 0
        L_0x0044:
            int r3 = r12.D
            if (r2 >= r3) goto L_0x005f
            r3 = 0
        L_0x0049:
            int r4 = r12.D
            if (r3 >= r4) goto L_0x005c
            byte[][] r4 = r12.state_2d
            r4 = r4[r2]
            byte[] r5 = r12.sbox
            byte r6 = r4[r3]
            byte r5 = r5[r6]
            r4[r3] = r5
            int r3 = r3 + 1
            goto L_0x0049
        L_0x005c:
            int r2 = r2 + 1
            goto L_0x0044
        L_0x005f:
            r2 = 1
            r3 = 1
        L_0x0061:
            int r4 = r12.D
            if (r3 >= r4) goto L_0x0089
            byte[][] r5 = r12.state_2d
            r5 = r5[r3]
            byte[] r6 = r12.state
            java.lang.System.arraycopy(r5, r0, r6, r0, r4)
            byte[] r4 = r12.state
            byte[][] r5 = r12.state_2d
            r5 = r5[r3]
            int r6 = r12.D
            int r6 = r6 - r3
            java.lang.System.arraycopy(r4, r3, r5, r0, r6)
            byte[] r4 = r12.state
            byte[][] r5 = r12.state_2d
            r5 = r5[r3]
            int r6 = r12.D
            int r6 = r6 - r3
            java.lang.System.arraycopy(r4, r0, r5, r6, r3)
            int r3 = r3 + 1
            goto L_0x0061
        L_0x0089:
            r3 = 0
        L_0x008a:
            int r4 = r12.D
            if (r3 >= r4) goto L_0x00e2
            r4 = 0
        L_0x008f:
            int r5 = r12.D
            if (r4 >= r5) goto L_0x00cd
            r5 = 0
            r6 = 0
        L_0x0095:
            int r7 = r12.D
            if (r5 >= r7) goto L_0x00c6
            byte[][] r7 = r12.MixColMatrix
            r7 = r7[r4]
            byte r7 = r7[r5]
            byte[][] r8 = r12.state_2d
            r8 = r8[r5]
            byte r8 = r8[r3]
            r9 = 0
            r10 = 0
        L_0x00a7:
            int r11 = r12.S
            if (r9 >= r11) goto L_0x00bf
            int r11 = r8 >>> r9
            r11 = r11 & r2
            if (r11 == 0) goto L_0x00b1
            r10 = r10 ^ r7
        L_0x00b1:
            int r11 = r12.S_1
            int r11 = r7 >>> r11
            r11 = r11 & r2
            int r7 = r7 << 1
            if (r11 == 0) goto L_0x00bc
            r7 = r7 ^ 3
        L_0x00bc:
            int r9 = r9 + 1
            goto L_0x00a7
        L_0x00bf:
            r7 = r10 & 15
            r6 = r6 ^ r7
            byte r6 = (byte) r6
            int r5 = r5 + 1
            goto L_0x0095
        L_0x00c6:
            byte[] r5 = r12.state
            r5[r4] = r6
            int r4 = r4 + 1
            goto L_0x008f
        L_0x00cd:
            r4 = 0
        L_0x00ce:
            int r5 = r12.D
            if (r4 >= r5) goto L_0x00df
            byte[][] r5 = r12.state_2d
            r5 = r5[r4]
            byte[] r6 = r12.state
            byte r6 = r6[r4]
            r5[r3] = r6
            int r4 = r4 + 1
            goto L_0x00ce
        L_0x00df:
            int r3 = r3 + 1
            goto L_0x008a
        L_0x00e2:
            int r1 = r1 + 1
            goto L_0x0027
        L_0x00e6:
            int r1 = r12.DSquare
            if (r0 >= r1) goto L_0x0112
            byte[] r1 = r12.state
            int r2 = r0 >>> 1
            byte[][] r3 = r12.state_2d
            int r4 = r12.Dq
            int r5 = r0 >>> r4
            r5 = r3[r5]
            int r6 = r12.Dr
            r7 = r0 & r6
            byte r5 = r5[r7]
            r5 = r5 & 15
            int r4 = r0 >>> r4
            r3 = r3[r4]
            int r4 = r0 + 1
            r4 = r4 & r6
            byte r3 = r3[r4]
            r3 = r3 & 15
            int r3 = r3 << 4
            r3 = r3 | r5
            byte r3 = (byte) r3
            r1[r2] = r3
            int r0 = r0 + 2
            goto L_0x00e6
        L_0x0112:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.crypto.digests.PhotonBeetleDigest.PHOTON_Permutation():void");
    }

    public int doFinal(byte[] bArr, int i) {
        if (i + 32 <= bArr.length) {
            byte[] byteArray = this.buffer.toByteArray();
            int length = byteArray.length;
            int i2 = 1;
            if (length == 0) {
                byte[] bArr2 = this.state;
                int i3 = this.STATE_INBYTES - 1;
                bArr2[i3] = (byte) ((1 << this.LAST_THREE_BITS_OFFSET) ^ bArr2[i3]);
            } else {
                byte[] bArr3 = this.state;
                if (length <= 16) {
                    System.arraycopy(byteArray, 0, bArr3, 0, length);
                    if (length < 16) {
                        byte[] bArr4 = this.state;
                        bArr4[length] = (byte) (bArr4[length] ^ 1);
                    }
                    byte[] bArr5 = this.state;
                    int i4 = this.STATE_INBYTES - 1;
                    byte b = bArr5[i4];
                    if (length >= 16) {
                        i2 = 2;
                    }
                    bArr5[i4] = (byte) ((i2 << this.LAST_THREE_BITS_OFFSET) ^ b);
                } else {
                    System.arraycopy(byteArray, 0, bArr3, 0, 16);
                    int i5 = length - 16;
                    int i6 = this.RATE_INBYTES;
                    int i7 = ((i5 + i6) - 1) / i6;
                    int i8 = 0;
                    while (true) {
                        int i9 = i7 - 1;
                        PHOTON_Permutation();
                        if (i8 >= i9) {
                            break;
                        }
                        int i10 = this.RATE_INBYTES;
                        Bytes.xorTo(i10, byteArray, (i8 * i10) + 16, this.state, 0);
                        i8++;
                    }
                    int i11 = this.RATE_INBYTES;
                    int i12 = i5 - (i8 * i11);
                    Bytes.xorTo(i12, byteArray, (i8 * i11) + 16, this.state, 0);
                    int i13 = this.RATE_INBYTES;
                    if (i12 < i13) {
                        byte[] bArr6 = this.state;
                        bArr6[i12] = (byte) (bArr6[i12] ^ 1);
                    }
                    byte[] bArr7 = this.state;
                    int i14 = this.STATE_INBYTES - 1;
                    byte b2 = bArr7[i14];
                    if (i5 % i13 != 0) {
                        i2 = 2;
                    }
                    bArr7[i14] = (byte) ((i2 << this.LAST_THREE_BITS_OFFSET) ^ b2);
                }
            }
            PHOTON_Permutation();
            System.arraycopy(this.state, 0, bArr, i, this.SQUEEZE_RATE_INBYTES);
            PHOTON_Permutation();
            byte[] bArr8 = this.state;
            int i15 = this.SQUEEZE_RATE_INBYTES;
            System.arraycopy(bArr8, 0, bArr, i + i15, this.TAG_INBYTES - i15);
            return this.TAG_INBYTES;
        }
        throw new OutputLengthException("output buffer is too short");
    }

    public String getAlgorithmName() {
        return "Photon-Beetle Hash";
    }

    public int getDigestSize() {
        return this.TAG_INBYTES;
    }

    public void reset() {
        this.buffer.reset();
        Arrays.fill(this.state, (byte) 0);
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
