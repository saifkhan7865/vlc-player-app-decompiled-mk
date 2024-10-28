package org.bouncycastle.pqc.crypto.picnic;

import java.lang.reflect.Array;
import java.security.SecureRandom;
import java.util.logging.Logger;
import org.bouncycastle.crypto.Xof;
import org.bouncycastle.math.raw.Bits;
import org.bouncycastle.pqc.crypto.picnic.Signature;
import org.bouncycastle.pqc.crypto.picnic.Signature2;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Integers;
import org.bouncycastle.util.Pack;

class PicnicEngine {
    private static final Logger LOG = Logger.getLogger(PicnicEngine.class.getName());
    protected static final int LOWMC_MAX_AND_GATES = 1144;
    protected static final int LOWMC_MAX_KEY_BITS = 256;
    private static final int LOWMC_MAX_STATE_SIZE = 64;
    protected static final int LOWMC_MAX_WORDS = 16;
    private static final int MAX_AUX_BYTES = 176;
    private static final int MAX_DIGEST_SIZE = 64;
    private static final int PICNIC_MAX_LOWMC_BLOCK_SIZE = 32;
    private static final int TRANSFORM_FS = 0;
    private static final int TRANSFORM_INVALID = 255;
    private static final int TRANSFORM_UR = 1;
    private static final int WORD_SIZE_BITS = 32;
    protected static final int saltSizeBytes = 32;
    private final int CRYPTO_BYTES;
    private final int CRYPTO_PUBLICKEYBYTES;
    private final int CRYPTO_SECRETKEYBYTES;
    protected final int UnruhGWithInputBytes;
    protected final int UnruhGWithoutInputBytes;
    protected final int andSizeBytes;
    protected final Xof digest;
    protected final int digestSizeBytes;
    protected final LowmcConstants lowmcConstants;
    protected final int numMPCParties;
    protected final int numMPCRounds;
    protected final int numOpenedRounds;
    protected final int numRounds;
    protected final int numSboxes;
    private final int parameters;
    protected final int pqSecurityLevel;
    protected final int seedSizeBytes;
    private int signatureLength;
    protected final int stateSizeBits;
    protected final int stateSizeBytes;
    protected final int stateSizeWords;
    private final int transform;

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x00c4, code lost:
        r0.digestSizeBytes = 64;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x00d7, code lost:
        r0.digestSizeBytes = 48;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x00ea, code lost:
        r0.digestSizeBytes = 32;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x00ec, code lost:
        r0.numOpenedRounds = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x00f6, code lost:
        switch(r1) {
            case 1: goto L_0x0160;
            case 2: goto L_0x0156;
            case 3: goto L_0x014e;
            case 4: goto L_0x0146;
            case 5: goto L_0x013e;
            case 6: goto L_0x0136;
            case 7: goto L_0x012b;
            case 8: goto L_0x0123;
            case 9: goto L_0x011b;
            case 10: goto L_0x0110;
            case 11: goto L_0x0108;
            case 12: goto L_0x0100;
            default: goto L_0x00f9;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x00f9, code lost:
        r2 = -1;
        r0.CRYPTO_SECRETKEYBYTES = -1;
        r0.CRYPTO_PUBLICKEYBYTES = -1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0100, code lost:
        r0.CRYPTO_SECRETKEYBYTES = 97;
        r0.CRYPTO_PUBLICKEYBYTES = 65;
        r2 = 126286;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0108, code lost:
        r0.CRYPTO_SECRETKEYBYTES = 73;
        r0.CRYPTO_PUBLICKEYBYTES = 49;
        r2 = 71179;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0110, code lost:
        r0.CRYPTO_SECRETKEYBYTES = 52;
        r0.CRYPTO_PUBLICKEYBYTES = 35;
        r2 = 32061;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x011b, code lost:
        r0.CRYPTO_SECRETKEYBYTES = 97;
        r0.CRYPTO_PUBLICKEYBYTES = 65;
        r2 = 61028;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0123, code lost:
        r0.CRYPTO_SECRETKEYBYTES = 73;
        r0.CRYPTO_PUBLICKEYBYTES = 49;
        r2 = 35028;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x012b, code lost:
        r0.CRYPTO_SECRETKEYBYTES = 52;
        r0.CRYPTO_PUBLICKEYBYTES = 35;
        r2 = 14612;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0136, code lost:
        r0.CRYPTO_SECRETKEYBYTES = 97;
        r0.CRYPTO_PUBLICKEYBYTES = 65;
        r2 = 209526;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x013e, code lost:
        r0.CRYPTO_SECRETKEYBYTES = 97;
        r0.CRYPTO_PUBLICKEYBYTES = 65;
        r2 = 132876;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0146, code lost:
        r0.CRYPTO_SECRETKEYBYTES = 73;
        r0.CRYPTO_PUBLICKEYBYTES = 49;
        r2 = 121857;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x014e, code lost:
        r0.CRYPTO_SECRETKEYBYTES = 73;
        r0.CRYPTO_PUBLICKEYBYTES = 49;
        r2 = 76784;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0156, code lost:
        r0.CRYPTO_SECRETKEYBYTES = 49;
        r0.CRYPTO_PUBLICKEYBYTES = 33;
        r2 = 53965;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0160, code lost:
        r0.CRYPTO_SECRETKEYBYTES = 49;
        r0.CRYPTO_PUBLICKEYBYTES = 33;
        r2 = 34036;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0169, code lost:
        r0.CRYPTO_BYTES = r2;
        r2 = org.bouncycastle.pqc.crypto.picnic.Utils.numBytes((r0.numSboxes * 3) * r0.numRounds);
        r0.andSizeBytes = r2;
        r3 = org.bouncycastle.pqc.crypto.picnic.Utils.numBytes(r0.stateSizeBits);
        r0.stateSizeBytes = r3;
        r4 = org.bouncycastle.pqc.crypto.picnic.Utils.numBytes(r0.pqSecurityLevel * 2);
        r0.seedSizeBytes = r4;
        r7 = r0.stateSizeBits;
        r0.stateSizeWords = (r7 + 31) / 32;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0192, code lost:
        switch(r1) {
            case 1: goto L_0x019c;
            case 2: goto L_0x0198;
            case 3: goto L_0x019c;
            case 4: goto L_0x0198;
            case 5: goto L_0x019c;
            case 6: goto L_0x0198;
            case 7: goto L_0x019c;
            case 8: goto L_0x019c;
            case 9: goto L_0x019c;
            case 10: goto L_0x019c;
            case 11: goto L_0x019c;
            case 12: goto L_0x019c;
            default: goto L_0x0195;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0195, code lost:
        r0.transform = 255;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0198, code lost:
        r0.transform = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x019c, code lost:
        r0.transform = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x01a1, code lost:
        if (r0.transform != 1) goto L_0x01aa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x01a3, code lost:
        r4 = r4 + r2;
        r0.UnruhGWithoutInputBytes = r4;
        r0.UnruhGWithInputBytes = r4 + r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x01aa, code lost:
        r0.UnruhGWithoutInputBytes = 0;
        r0.UnruhGWithInputBytes = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x01ae, code lost:
        if (r7 == 128) goto L_0x01bb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x01b0, code lost:
        if (r7 != 129) goto L_0x01b3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x01b3, code lost:
        r1 = new org.bouncycastle.crypto.digests.SHAKEDigest(256);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x01bb, code lost:
        r1 = new org.bouncycastle.crypto.digests.SHAKEDigest(128);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x01c0, code lost:
        r0.digest = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x01c2, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    PicnicEngine(int r17, org.bouncycastle.pqc.crypto.picnic.LowmcConstants r18) {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            r16.<init>()
            r2 = r18
            r0.lowmcConstants = r2
            r0.parameters = r1
            r3 = 329(0x149, float:4.61E-43)
            r4 = 219(0xdb, float:3.07E-43)
            r5 = 255(0xff, float:3.57E-43)
            r7 = 16
            r8 = 129(0x81, float:1.81E-43)
            r9 = 48
            r10 = 192(0xc0, float:2.69E-43)
            r11 = 96
            r13 = 32
            r14 = 4
            r15 = 128(0x80, float:1.794E-43)
            r12 = 3
            r2 = 64
            r6 = 0
            switch(r1) {
                case 1: goto L_0x00da;
                case 2: goto L_0x00da;
                case 3: goto L_0x00c7;
                case 4: goto L_0x00c7;
                case 5: goto L_0x00b0;
                case 6: goto L_0x00b0;
                case 7: goto L_0x0099;
                case 8: goto L_0x0084;
                case 9: goto L_0x006d;
                case 10: goto L_0x005d;
                case 11: goto L_0x004f;
                case 12: goto L_0x003d;
                default: goto L_0x0029;
            }
        L_0x0029:
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "unknown parameter set "
            r3.<init>(r4)
            r3.append(r1)
            java.lang.String r1 = r3.toString()
            r2.<init>(r1)
            throw r2
        L_0x003d:
            r0.pqSecurityLevel = r15
            r0.stateSizeBits = r5
            r3 = 438(0x1b6, float:6.14E-43)
            r0.numMPCRounds = r3
            r0.numMPCParties = r12
            r3 = 85
            r0.numSboxes = r3
            r0.numRounds = r14
            goto L_0x00c4
        L_0x004f:
            r0.pqSecurityLevel = r11
            r0.stateSizeBits = r10
            r0.numMPCRounds = r3
            r0.numMPCParties = r12
            r0.numSboxes = r2
            r0.numRounds = r14
            goto L_0x00d7
        L_0x005d:
            r0.pqSecurityLevel = r2
            r0.stateSizeBits = r8
            r0.numMPCRounds = r4
            r0.numMPCParties = r12
            r2 = 43
            r0.numSboxes = r2
            r0.numRounds = r14
            goto L_0x00ea
        L_0x006d:
            r0.pqSecurityLevel = r15
            r0.stateSizeBits = r5
            r3 = 601(0x259, float:8.42E-43)
            r0.numMPCRounds = r3
            r3 = 68
            r0.numOpenedRounds = r3
            r0.numMPCParties = r7
            r3 = 85
            r0.numSboxes = r3
            r0.numRounds = r14
            r0.digestSizeBytes = r2
            goto L_0x00ee
        L_0x0084:
            r0.pqSecurityLevel = r11
            r0.stateSizeBits = r10
            r3 = 419(0x1a3, float:5.87E-43)
            r0.numMPCRounds = r3
            r3 = 52
            r0.numOpenedRounds = r3
            r0.numMPCParties = r7
            r0.numSboxes = r2
            r0.numRounds = r14
            r0.digestSizeBytes = r9
            goto L_0x00ee
        L_0x0099:
            r0.pqSecurityLevel = r2
            r0.stateSizeBits = r8
            r2 = 250(0xfa, float:3.5E-43)
            r0.numMPCRounds = r2
            r2 = 36
            r0.numOpenedRounds = r2
            r0.numMPCParties = r7
            r2 = 43
            r0.numSboxes = r2
            r0.numRounds = r14
            r0.digestSizeBytes = r13
            goto L_0x00ee
        L_0x00b0:
            r0.pqSecurityLevel = r15
            r3 = 256(0x100, float:3.59E-43)
            r0.stateSizeBits = r3
            r3 = 438(0x1b6, float:6.14E-43)
            r0.numMPCRounds = r3
            r0.numMPCParties = r12
            r7 = 10
            r0.numSboxes = r7
            r3 = 38
            r0.numRounds = r3
        L_0x00c4:
            r0.digestSizeBytes = r2
            goto L_0x00ec
        L_0x00c7:
            r7 = 10
            r0.pqSecurityLevel = r11
            r0.stateSizeBits = r10
            r0.numMPCRounds = r3
            r0.numMPCParties = r12
            r0.numSboxes = r7
            r2 = 30
            r0.numRounds = r2
        L_0x00d7:
            r0.digestSizeBytes = r9
            goto L_0x00ec
        L_0x00da:
            r7 = 10
            r0.pqSecurityLevel = r2
            r0.stateSizeBits = r15
            r0.numMPCRounds = r4
            r0.numMPCParties = r12
            r0.numSboxes = r7
            r2 = 20
            r0.numRounds = r2
        L_0x00ea:
            r0.digestSizeBytes = r13
        L_0x00ec:
            r0.numOpenedRounds = r6
        L_0x00ee:
            r2 = 65
            r3 = 97
            r4 = 73
            r7 = 49
            switch(r1) {
                case 1: goto L_0x0160;
                case 2: goto L_0x0156;
                case 3: goto L_0x014e;
                case 4: goto L_0x0146;
                case 5: goto L_0x013e;
                case 6: goto L_0x0136;
                case 7: goto L_0x012b;
                case 8: goto L_0x0123;
                case 9: goto L_0x011b;
                case 10: goto L_0x0110;
                case 11: goto L_0x0108;
                case 12: goto L_0x0100;
                default: goto L_0x00f9;
            }
        L_0x00f9:
            r2 = -1
            r0.CRYPTO_SECRETKEYBYTES = r2
            r0.CRYPTO_PUBLICKEYBYTES = r2
            goto L_0x0169
        L_0x0100:
            r0.CRYPTO_SECRETKEYBYTES = r3
            r0.CRYPTO_PUBLICKEYBYTES = r2
            r2 = 126286(0x1ed4e, float:1.76964E-40)
            goto L_0x0169
        L_0x0108:
            r0.CRYPTO_SECRETKEYBYTES = r4
            r0.CRYPTO_PUBLICKEYBYTES = r7
            r2 = 71179(0x1160b, float:9.9743E-41)
            goto L_0x0169
        L_0x0110:
            r2 = 52
            r0.CRYPTO_SECRETKEYBYTES = r2
            r2 = 35
            r0.CRYPTO_PUBLICKEYBYTES = r2
            r2 = 32061(0x7d3d, float:4.4927E-41)
            goto L_0x0169
        L_0x011b:
            r0.CRYPTO_SECRETKEYBYTES = r3
            r0.CRYPTO_PUBLICKEYBYTES = r2
            r2 = 61028(0xee64, float:8.5518E-41)
            goto L_0x0169
        L_0x0123:
            r0.CRYPTO_SECRETKEYBYTES = r4
            r0.CRYPTO_PUBLICKEYBYTES = r7
            r2 = 35028(0x88d4, float:4.9085E-41)
            goto L_0x0169
        L_0x012b:
            r2 = 52
            r0.CRYPTO_SECRETKEYBYTES = r2
            r2 = 35
            r0.CRYPTO_PUBLICKEYBYTES = r2
            r2 = 14612(0x3914, float:2.0476E-41)
            goto L_0x0169
        L_0x0136:
            r0.CRYPTO_SECRETKEYBYTES = r3
            r0.CRYPTO_PUBLICKEYBYTES = r2
            r2 = 209526(0x33276, float:2.93608E-40)
            goto L_0x0169
        L_0x013e:
            r0.CRYPTO_SECRETKEYBYTES = r3
            r0.CRYPTO_PUBLICKEYBYTES = r2
            r2 = 132876(0x2070c, float:1.86199E-40)
            goto L_0x0169
        L_0x0146:
            r0.CRYPTO_SECRETKEYBYTES = r4
            r0.CRYPTO_PUBLICKEYBYTES = r7
            r2 = 121857(0x1dc01, float:1.70758E-40)
            goto L_0x0169
        L_0x014e:
            r0.CRYPTO_SECRETKEYBYTES = r4
            r0.CRYPTO_PUBLICKEYBYTES = r7
            r2 = 76784(0x12bf0, float:1.07597E-40)
            goto L_0x0169
        L_0x0156:
            r0.CRYPTO_SECRETKEYBYTES = r7
            r2 = 33
            r0.CRYPTO_PUBLICKEYBYTES = r2
            r2 = 53965(0xd2cd, float:7.5621E-41)
            goto L_0x0169
        L_0x0160:
            r0.CRYPTO_SECRETKEYBYTES = r7
            r2 = 33
            r0.CRYPTO_PUBLICKEYBYTES = r2
            r2 = 34036(0x84f4, float:4.7695E-41)
        L_0x0169:
            r0.CRYPTO_BYTES = r2
            int r2 = r0.numSboxes
            int r2 = r2 * 3
            int r3 = r0.numRounds
            int r2 = r2 * r3
            int r2 = org.bouncycastle.pqc.crypto.picnic.Utils.numBytes(r2)
            r0.andSizeBytes = r2
            int r3 = r0.stateSizeBits
            int r3 = org.bouncycastle.pqc.crypto.picnic.Utils.numBytes(r3)
            r0.stateSizeBytes = r3
            int r4 = r0.pqSecurityLevel
            int r4 = r4 * 2
            int r4 = org.bouncycastle.pqc.crypto.picnic.Utils.numBytes(r4)
            r0.seedSizeBytes = r4
            int r7 = r0.stateSizeBits
            int r9 = r7 + 31
            int r9 = r9 / r13
            r0.stateSizeWords = r9
            switch(r1) {
                case 1: goto L_0x019c;
                case 2: goto L_0x0198;
                case 3: goto L_0x019c;
                case 4: goto L_0x0198;
                case 5: goto L_0x019c;
                case 6: goto L_0x0198;
                case 7: goto L_0x019c;
                case 8: goto L_0x019c;
                case 9: goto L_0x019c;
                case 10: goto L_0x019c;
                case 11: goto L_0x019c;
                case 12: goto L_0x019c;
                default: goto L_0x0195;
            }
        L_0x0195:
            r0.transform = r5
            goto L_0x019e
        L_0x0198:
            r1 = 1
            r0.transform = r1
            goto L_0x019e
        L_0x019c:
            r0.transform = r6
        L_0x019e:
            int r1 = r0.transform
            r5 = 1
            if (r1 != r5) goto L_0x01aa
            int r4 = r4 + r2
            r0.UnruhGWithoutInputBytes = r4
            int r4 = r4 + r3
            r0.UnruhGWithInputBytes = r4
            goto L_0x01ae
        L_0x01aa:
            r0.UnruhGWithoutInputBytes = r6
            r0.UnruhGWithInputBytes = r6
        L_0x01ae:
            if (r7 == r15) goto L_0x01bb
            if (r7 != r8) goto L_0x01b3
            goto L_0x01bb
        L_0x01b3:
            org.bouncycastle.crypto.digests.SHAKEDigest r1 = new org.bouncycastle.crypto.digests.SHAKEDigest
            r2 = 256(0x100, float:3.59E-43)
            r1.<init>((int) r2)
            goto L_0x01c0
        L_0x01bb:
            org.bouncycastle.crypto.digests.SHAKEDigest r1 = new org.bouncycastle.crypto.digests.SHAKEDigest
            r1.<init>((int) r15)
        L_0x01c0:
            r0.digest = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.pqc.crypto.picnic.PicnicEngine.<init>(int, org.bouncycastle.pqc.crypto.picnic.LowmcConstants):void");
    }

    private void Commit(byte[] bArr, int i, View view, byte[] bArr2) {
        this.digest.update((byte) 4);
        this.digest.update(bArr, i, this.seedSizeBytes);
        this.digest.doFinal(bArr2, 0, this.digestSizeBytes);
        this.digest.update((byte) 0);
        this.digest.update(bArr2, 0, this.digestSizeBytes);
        this.digest.update(Pack.intToLittleEndian(view.inputShare), 0, this.stateSizeBytes);
        this.digest.update(view.communicatedBits, 0, this.andSizeBytes);
        this.digest.update(Pack.intToLittleEndian(view.outputShare), 0, this.stateSizeBytes);
        this.digest.doFinal(bArr2, 0, this.digestSizeBytes);
    }

    private void G(int i, byte[] bArr, int i2, View view, byte[] bArr2) {
        int i3 = this.seedSizeBytes + this.andSizeBytes;
        this.digest.update((byte) 5);
        this.digest.update(bArr, i2, this.seedSizeBytes);
        this.digest.doFinal(bArr2, 0, this.digestSizeBytes);
        this.digest.update(bArr2, 0, this.digestSizeBytes);
        if (i == 2) {
            this.digest.update(Pack.intToLittleEndian(view.inputShare), 0, this.stateSizeBytes);
            i3 += this.stateSizeBytes;
        }
        this.digest.update(view.communicatedBits, 0, this.andSizeBytes);
        this.digest.update(Pack.intToLittleEndian(i3), 0, 2);
        this.digest.doFinal(bArr2, 0, i3);
    }

    private void H3(int[] iArr, int[] iArr2, View[][] viewArr, byte[][][] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[][][] bArr5) {
        this.digest.update((byte) 1);
        byte[] bArr6 = new byte[(this.stateSizeWords * 4)];
        for (int i = 0; i < this.numMPCRounds; i++) {
            for (int i2 = 0; i2 < 3; i2++) {
                Pack.intToLittleEndian(viewArr[i][i2].outputShare, bArr6, 0);
                this.digest.update(bArr6, 0, this.stateSizeBytes);
            }
        }
        implH3(iArr, iArr2, bArr, bArr2, bArr3, bArr4, bArr5);
    }

    private void H3(int[] iArr, int[] iArr2, int[][][] iArr3, byte[][][] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[][][] bArr5) {
        this.digest.update((byte) 1);
        byte[] bArr6 = new byte[(this.stateSizeWords * 4)];
        for (int i = 0; i < this.numMPCRounds; i++) {
            for (int i2 = 0; i2 < 3; i2++) {
                Pack.intToLittleEndian(iArr3[i][i2], bArr6, 0);
                this.digest.update(bArr6, 0, this.stateSizeBytes);
            }
        }
        implH3(iArr, iArr2, bArr, bArr2, bArr3, bArr4, bArr5);
    }

    private void HCP(byte[] bArr, int[] iArr, int[] iArr2, byte[][] bArr2, byte[] bArr3, byte[] bArr4, int[] iArr3, int[] iArr4, byte[] bArr5) {
        for (int i = 0; i < this.numMPCRounds; i++) {
            this.digest.update(bArr2[i], 0, this.digestSizeBytes);
        }
        byte[] bArr6 = new byte[32];
        this.digest.update(bArr3, 0, this.digestSizeBytes);
        this.digest.update(bArr4, 0, 32);
        updateDigest(iArr3, bArr6);
        updateDigest(iArr4, bArr6);
        this.digest.update(bArr5, 0, bArr5.length);
        this.digest.doFinal(bArr, 0, this.digestSizeBytes);
        if (iArr != null && iArr2 != null) {
            expandChallengeHash(bArr, iArr, iArr2);
        }
    }

    private void LowMCEnc(int[] iArr, int[] iArr2, int[] iArr3) {
        int[] iArr4 = new int[16];
        if (iArr != iArr2) {
            System.arraycopy(iArr, 0, iArr2, 0, this.stateSizeWords);
        }
        KMatricesWithPointer KMatrix = this.lowmcConstants.KMatrix(this, 0);
        matrix_mul(iArr4, iArr3, KMatrix.getData(), KMatrix.getMatrixPointer());
        xor_array(iArr2, iArr2, iArr4, 0);
        for (int i = 1; i <= this.numRounds; i++) {
            KMatricesWithPointer KMatrix2 = this.lowmcConstants.KMatrix(this, i);
            matrix_mul(iArr4, iArr3, KMatrix2.getData(), KMatrix2.getMatrixPointer());
            substitution(iArr2);
            int i2 = i - 1;
            KMatricesWithPointer LMatrix = this.lowmcConstants.LMatrix(this, i2);
            matrix_mul(iArr2, iArr2, LMatrix.getData(), LMatrix.getMatrixPointer());
            KMatricesWithPointer RConstant = this.lowmcConstants.RConstant(this, i2);
            xor_array(iArr2, iArr2, RConstant.getData(), RConstant.getMatrixPointer());
            xor_array(iArr2, iArr2, iArr4, 0);
        }
    }

    static int appendUnique(int[] iArr, int i, int i2) {
        if (i2 == 0) {
            iArr[i2] = i;
        } else {
            for (int i3 = 0; i3 < i2; i3++) {
                if (iArr[i3] == i) {
                    return i2;
                }
            }
            iArr[i2] = i;
        }
        return i2 + 1;
    }

    private boolean arePaddingBitsZero(byte[] bArr, int i) {
        int numBytes = Utils.numBytes(i);
        while (i < numBytes * 8) {
            if (Utils.getBit(bArr, i) != 0) {
                return false;
            }
            i++;
        }
        return true;
    }

    private boolean arePaddingBitsZero(int[] iArr, int i) {
        if ((i & 31) == 0) {
            return true;
        }
        return (iArr[i >>> 5] & (Utils.getTrailingBitsMask(i) ^ -1)) == 0;
    }

    private void aux_mpc_AND(int i, int i2, int i3, Tape tape) {
        int i4 = this.numMPCParties - 1;
        Utils.setBit(tape.tapes[i4], tape.pos - 1, (byte) ((((i & i2) ^ (Utils.parity16(tape.tapesToWord()) ^ Utils.getBit(tape.tapes[i4], tape.pos - 1))) ^ i3) & 255));
    }

    static int bitsToChunks(int i, byte[] bArr, int i2, int[] iArr) {
        int i3 = i2 * 8;
        if (i > i3) {
            return 0;
        }
        int i4 = i3 / i;
        for (int i5 = 0; i5 < i4; i5++) {
            iArr[i5] = 0;
            for (int i6 = 0; i6 < i; i6++) {
                iArr[i5] = iArr[i5] + (Utils.getBit(bArr, (i5 * i) + i6) << i6);
            }
        }
        return i4;
    }

    private void commit(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, int i, int i2) {
        this.digest.update(bArr2, 0, this.seedSizeBytes);
        if (bArr3 != null) {
            this.digest.update(bArr3, 0, this.andSizeBytes);
        }
        this.digest.update(bArr4, 0, 32);
        this.digest.update(Pack.intToLittleEndian(i), 0, 2);
        this.digest.update(Pack.intToLittleEndian(i2), 0, 2);
        this.digest.doFinal(bArr, 0, this.digestSizeBytes);
    }

    private void commit_h(byte[] bArr, byte[][] bArr2) {
        for (int i = 0; i < this.numMPCParties; i++) {
            this.digest.update(bArr2[i], 0, this.digestSizeBytes);
        }
        this.digest.doFinal(bArr, 0, this.digestSizeBytes);
    }

    private void commit_v(byte[] bArr, byte[] bArr2, Msg msg) {
        this.digest.update(bArr2, 0, this.stateSizeBytes);
        for (int i = 0; i < this.numMPCParties; i++) {
            this.digest.update(msg.msgs[i], 0, Utils.numBytes(msg.pos));
        }
        this.digest.doFinal(bArr, 0, this.digestSizeBytes);
    }

    private void computeSaltAndRootSeed(byte[] bArr, int[] iArr, int[] iArr2, int[] iArr3, byte[] bArr2) {
        byte[] bArr3 = new byte[32];
        updateDigest(iArr, bArr3);
        this.digest.update(bArr2, 0, bArr2.length);
        updateDigest(iArr2, bArr3);
        updateDigest(iArr3, bArr3);
        Pack.shortToLittleEndian((short) this.stateSizeBits, bArr3, 0);
        this.digest.update(bArr3, 0, 2);
        this.digest.doFinal(bArr, 0, bArr.length);
    }

    private byte[] computeSeeds(int[] iArr, int[] iArr2, int[] iArr3, byte[] bArr) {
        byte[] bArr2 = new byte[((this.seedSizeBytes * this.numMPCParties * this.numMPCRounds) + 32)];
        byte[] bArr3 = new byte[32];
        updateDigest(iArr, bArr3);
        this.digest.update(bArr, 0, bArr.length);
        updateDigest(iArr2, bArr3);
        updateDigest(iArr3, bArr3);
        this.digest.update(Pack.intToLittleEndian(this.stateSizeBits), 0, 2);
        this.digest.doFinal(bArr2, 0, (this.seedSizeBytes * this.numMPCParties * this.numMPCRounds) + 32);
        return bArr2;
    }

    private boolean contains(int[] iArr, int i, int i2) {
        for (int i3 = 0; i3 < i; i3++) {
            if (iArr[i3] == i2) {
                return true;
            }
        }
        return false;
    }

    private int countNonZeroChallenges(byte[] bArr, int i) {
        int i2;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        while (true) {
            int i6 = i3 + 16;
            i2 = this.numMPCRounds;
            if (i6 > i2) {
                break;
            }
            int littleEndianToInt = Pack.littleEndianToInt(bArr, (i3 >>> 2) + i);
            int i7 = littleEndianToInt >>> 1;
            i4 |= littleEndianToInt & i7;
            i5 += Integers.bitCount((littleEndianToInt ^ i7) & 1431655765);
            i3 = i6;
        }
        int i8 = (i2 - i3) * 2;
        if (i8 > 0) {
            int littleEndianToInt_Low = Pack.littleEndianToInt_Low(bArr, i + (i3 >>> 2), (i8 + 7) / 8) & Utils.getTrailingBitsMask(i8);
            int i9 = littleEndianToInt_Low >>> 1;
            i4 |= littleEndianToInt_Low & i9;
            i5 += Integers.bitCount((littleEndianToInt_Low ^ i9) & 1431655765);
        }
        if ((i4 & 1431655765) == 0) {
            return i5;
        }
        return -1;
    }

    private boolean createRandomTape(byte[] bArr, int i, byte[] bArr2, int i2, int i3, byte[] bArr3, int i4) {
        if (i4 < this.digestSizeBytes) {
            return false;
        }
        this.digest.update((byte) 2);
        this.digest.update(bArr, i, this.seedSizeBytes);
        this.digest.doFinal(bArr3, 0, this.digestSizeBytes);
        this.digest.update(bArr3, 0, this.digestSizeBytes);
        this.digest.update(bArr2, 0, 32);
        this.digest.update(Pack.intToLittleEndian(i2), 0, 2);
        this.digest.update(Pack.intToLittleEndian(i3), 0, 2);
        this.digest.update(Pack.intToLittleEndian(i4), 0, 2);
        this.digest.doFinal(bArr3, 0, i4);
        return true;
    }

    private void createRandomTapes(Tape tape, byte[][] bArr, int i, byte[] bArr2, int i2) {
        int i3 = this.andSizeBytes * 2;
        for (int i4 = 0; i4 < this.numMPCParties; i4++) {
            this.digest.update(bArr[i4 + i], 0, this.seedSizeBytes);
            this.digest.update(bArr2, 0, 32);
            this.digest.update(Pack.intToLittleEndian(i2), 0, 2);
            this.digest.update(Pack.intToLittleEndian(i4), 0, 2);
            this.digest.doFinal(tape.tapes[i4], 0, i3);
        }
    }

    private int deserializeSignature(Signature signature, byte[] bArr, int i, int i2) {
        int countNonZeroChallenges;
        Signature.Proof[] proofArr = signature.proofs;
        byte[] bArr2 = signature.challengeBits;
        int numBytes = Utils.numBytes(this.numMPCRounds * 2);
        if (i < numBytes || (countNonZeroChallenges = countNonZeroChallenges(bArr, i2)) < 0) {
            return -1;
        }
        int i3 = this.numMPCRounds;
        int i4 = numBytes + 32 + (((this.seedSizeBytes * 2) + this.andSizeBytes + this.digestSizeBytes) * i3) + (this.stateSizeBytes * countNonZeroChallenges);
        if (this.transform == 1) {
            i4 = i4 + (this.UnruhGWithInputBytes * (i3 - countNonZeroChallenges)) + (this.UnruhGWithoutInputBytes * countNonZeroChallenges);
        }
        if (i != i4) {
            Logger logger = LOG;
            logger.fine("sigBytesLen = " + i + ", expected bytesRequired = " + i4);
            return -1;
        }
        System.arraycopy(bArr, i2, bArr2, 0, numBytes);
        int i5 = i2 + numBytes;
        System.arraycopy(bArr, i5, signature.salt, 0, 32);
        int i6 = i5 + 32;
        for (int i7 = 0; i7 < this.numMPCRounds; i7++) {
            int challenge = getChallenge(bArr2, i7);
            System.arraycopy(bArr, i6, proofArr[i7].view3Commitment, 0, this.digestSizeBytes);
            int i8 = i6 + this.digestSizeBytes;
            if (this.transform == 1) {
                int i9 = challenge == 0 ? this.UnruhGWithInputBytes : this.UnruhGWithoutInputBytes;
                System.arraycopy(bArr, i8, proofArr[i7].view3UnruhG, 0, i9);
                i8 += i9;
            }
            System.arraycopy(bArr, i8, proofArr[i7].communicatedBits, 0, this.andSizeBytes);
            int i10 = i8 + this.andSizeBytes;
            System.arraycopy(bArr, i10, proofArr[i7].seed1, 0, this.seedSizeBytes);
            int i11 = i10 + this.seedSizeBytes;
            System.arraycopy(bArr, i11, proofArr[i7].seed2, 0, this.seedSizeBytes);
            i6 = i11 + this.seedSizeBytes;
            if (challenge == 1 || challenge == 2) {
                Pack.littleEndianToInt(bArr, i6, proofArr[i7].inputShare, 0, this.stateSizeBytes / 4);
                if (this.stateSizeBits == 129) {
                    proofArr[i7].inputShare[this.stateSizeWords - 1] = bArr[(this.stateSizeBytes + i6) - 1] & 255;
                }
                i6 += this.stateSizeBytes;
                if (!arePaddingBitsZero(proofArr[i7].inputShare, this.stateSizeBits)) {
                    return -1;
                }
            }
        }
        return 0;
    }

    private int deserializeSignature2(Signature2 signature2, byte[] bArr, int i, int i2) {
        Logger logger;
        String str;
        int i3 = this.digestSizeBytes + 32;
        if (bArr.length < i3) {
            return -1;
        }
        System.arraycopy(bArr, i2, signature2.challengeHash, 0, this.digestSizeBytes);
        int i4 = i2 + this.digestSizeBytes;
        System.arraycopy(bArr, i4, signature2.salt, 0, 32);
        int i5 = i4 + 32;
        expandChallengeHash(signature2.challengeHash, signature2.challengeC, signature2.challengeP);
        signature2.iSeedInfoLen = new Tree(this, this.numMPCRounds, this.seedSizeBytes).revealSeedsSize(signature2.challengeC, this.numOpenedRounds);
        int i6 = i3 + signature2.iSeedInfoLen;
        signature2.cvInfoLen = new Tree(this, this.numMPCRounds, this.digestSizeBytes).openMerkleTreeSize(getMissingLeavesList(signature2.challengeC), this.numMPCRounds - this.numOpenedRounds);
        int i7 = i6 + signature2.cvInfoLen;
        int revealSeedsSize = new Tree(this, this.numMPCParties, this.seedSizeBytes).revealSeedsSize(new int[1], 1);
        for (int i8 = 0; i8 < this.numMPCRounds; i8++) {
            if (contains(signature2.challengeC, this.numOpenedRounds, i8)) {
                if (signature2.challengeP[indexOf(signature2.challengeC, this.numOpenedRounds, i8)] != this.numMPCParties - 1) {
                    i7 += this.andSizeBytes;
                }
                i7 = i7 + revealSeedsSize + this.stateSizeBytes + this.andSizeBytes + this.digestSizeBytes;
            }
        }
        if (i != i7) {
            logger = LOG;
            str = "sigLen = " + i + ", expected bytesRequired = " + i7;
        } else {
            signature2.iSeedInfo = new byte[signature2.iSeedInfoLen];
            System.arraycopy(bArr, i5, signature2.iSeedInfo, 0, signature2.iSeedInfoLen);
            int i9 = i5 + signature2.iSeedInfoLen;
            signature2.cvInfo = new byte[signature2.cvInfoLen];
            System.arraycopy(bArr, i9, signature2.cvInfo, 0, signature2.cvInfoLen);
            int i10 = i9 + signature2.cvInfoLen;
            for (int i11 = 0; i11 < this.numMPCRounds; i11++) {
                if (contains(signature2.challengeC, this.numOpenedRounds, i11)) {
                    signature2.proofs[i11] = new Signature2.Proof2(this);
                    signature2.proofs[i11].seedInfoLen = revealSeedsSize;
                    signature2.proofs[i11].seedInfo = new byte[signature2.proofs[i11].seedInfoLen];
                    System.arraycopy(bArr, i10, signature2.proofs[i11].seedInfo, 0, signature2.proofs[i11].seedInfoLen);
                    int i12 = i10 + signature2.proofs[i11].seedInfoLen;
                    if (signature2.challengeP[indexOf(signature2.challengeC, this.numOpenedRounds, i11)] != this.numMPCParties - 1) {
                        System.arraycopy(bArr, i12, signature2.proofs[i11].aux, 0, this.andSizeBytes);
                        i12 += this.andSizeBytes;
                        if (!arePaddingBitsZero(signature2.proofs[i11].aux, this.numRounds * 3 * this.numSboxes)) {
                            logger = LOG;
                            str = "failed while deserializing aux bits";
                        }
                    }
                    System.arraycopy(bArr, i12, signature2.proofs[i11].input, 0, this.stateSizeBytes);
                    int i13 = i12 + this.stateSizeBytes;
                    int i14 = this.andSizeBytes;
                    System.arraycopy(bArr, i13, signature2.proofs[i11].msgs, 0, i14);
                    int i15 = i13 + i14;
                    if (!arePaddingBitsZero(signature2.proofs[i11].msgs, this.numRounds * 3 * this.numSboxes)) {
                        logger = LOG;
                        str = "failed while deserializing msgs bits";
                    } else {
                        System.arraycopy(bArr, i15, signature2.proofs[i11].C, 0, this.digestSizeBytes);
                        i10 = i15 + this.digestSizeBytes;
                    }
                }
            }
            return 0;
        }
        logger.fine(str);
        return -1;
    }

    private void expandChallengeHash(byte[] bArr, int[] iArr, int[] iArr2) {
        int ceil_log2 = Utils.ceil_log2(this.numMPCRounds);
        int ceil_log22 = Utils.ceil_log2(this.numMPCParties);
        int[] iArr3 = new int[((this.digestSizeBytes * 8) / Math.min(ceil_log2, ceil_log22))];
        byte[] bArr2 = new byte[64];
        System.arraycopy(bArr, 0, bArr2, 0, this.digestSizeBytes);
        int i = 0;
        while (i < this.numOpenedRounds) {
            int bitsToChunks = bitsToChunks(ceil_log2, bArr2, this.digestSizeBytes, iArr3);
            for (int i2 = 0; i2 < bitsToChunks; i2++) {
                int i3 = iArr3[i2];
                if (i3 < this.numMPCRounds) {
                    i = appendUnique(iArr, i3, i);
                }
                if (i == this.numOpenedRounds) {
                    break;
                }
            }
            this.digest.update((byte) 1);
            this.digest.update(bArr2, 0, this.digestSizeBytes);
            this.digest.doFinal(bArr2, 0, this.digestSizeBytes);
        }
        int i4 = 0;
        while (i4 < this.numOpenedRounds) {
            int bitsToChunks2 = bitsToChunks(ceil_log22, bArr2, this.digestSizeBytes, iArr3);
            for (int i5 = 0; i5 < bitsToChunks2; i5++) {
                int i6 = iArr3[i5];
                if (i6 < this.numMPCParties) {
                    iArr2[i4] = i6;
                    i4++;
                }
                if (i4 == this.numOpenedRounds) {
                    break;
                }
            }
            this.digest.update((byte) 1);
            this.digest.update(bArr2, 0, this.digestSizeBytes);
            this.digest.doFinal(bArr2, 0, this.digestSizeBytes);
        }
    }

    static int extend(int i) {
        return (i - 1) ^ -1;
    }

    private void getAuxBits(byte[] bArr, Tape tape) {
        byte[] bArr2 = tape.tapes[this.numMPCParties - 1];
        int i = this.stateSizeBits;
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < this.numRounds; i4++) {
            i2 += i;
            int i5 = 0;
            while (i5 < i) {
                Utils.setBit(bArr, i3, Utils.getBit(bArr2, i2));
                i5++;
                i3++;
                i2++;
            }
        }
    }

    private int[] getMissingLeavesList(int[] iArr) {
        int[] iArr2 = new int[(this.numMPCRounds - this.numOpenedRounds)];
        int i = 0;
        for (int i2 = 0; i2 < this.numMPCRounds; i2++) {
            if (!contains(iArr, this.numOpenedRounds, i2)) {
                iArr2[i] = i2;
                i++;
            }
        }
        return iArr2;
    }

    private void implH3(int[] iArr, int[] iArr2, byte[][][] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[][][] bArr5) {
        byte[] bArr6 = bArr2;
        byte[] bArr7 = bArr4;
        byte[] bArr8 = new byte[this.digestSizeBytes];
        bArr6[Utils.numBytes(this.numMPCRounds * 2) - 1] = 0;
        for (int i = 0; i < this.numMPCRounds; i++) {
            for (int i2 = 0; i2 < 3; i2++) {
                this.digest.update(bArr[i][i2], 0, this.digestSizeBytes);
            }
        }
        if (this.transform == 1) {
            for (int i3 = 0; i3 < this.numMPCRounds; i3++) {
                int i4 = 0;
                while (i4 < 3) {
                    this.digest.update(bArr5[i3][i4], 0, i4 == 2 ? this.UnruhGWithInputBytes : this.UnruhGWithoutInputBytes);
                    i4++;
                }
            }
        }
        this.digest.update(Pack.intToLittleEndian(iArr), 0, this.stateSizeBytes);
        this.digest.update(Pack.intToLittleEndian(iArr2), 0, this.stateSizeBytes);
        this.digest.update(bArr3, 0, 32);
        this.digest.update(bArr7, 0, bArr7.length);
        this.digest.doFinal(bArr8, 0, this.digestSizeBytes);
        boolean z = true;
        int i5 = 0;
        while (z) {
            for (int i6 = 0; i6 < this.digestSizeBytes; i6++) {
                byte b = bArr8[i6];
                int i7 = 0;
                while (true) {
                    if (i7 >= 8) {
                        break;
                    }
                    int i8 = (b >>> (6 - i7)) & 3;
                    if (i8 < 3) {
                        setChallenge(bArr6, i5, i8);
                        i5++;
                        if (i5 == this.numMPCRounds) {
                            z = false;
                            break;
                        }
                    }
                    i7 += 2;
                }
                if (!z) {
                    break;
                }
            }
            if (z) {
                this.digest.update((byte) 1);
                this.digest.update(bArr8, 0, this.digestSizeBytes);
                this.digest.doFinal(bArr8, 0, this.digestSizeBytes);
            } else {
                return;
            }
        }
    }

    static int indexOf(int[] iArr, int i, int i2) {
        for (int i3 = 0; i3 < i; i3++) {
            if (iArr[i3] == i2) {
                return i3;
            }
        }
        return -1;
    }

    static boolean is_picnic3(int i) {
        return i == 7 || i == 8 || i == 9;
    }

    private int mpc_AND(int i, int i2, int i3, int i4, Tape tape, Msg msg) {
        int extend = ((i3 & extend(i2)) ^ (i4 & extend(i))) ^ tape.tapesToWord();
        if (msg.unopened >= 0) {
            extend = Utils.setBit(extend, msg.unopened, (int) Utils.getBit(msg.msgs[msg.unopened], msg.pos));
        }
        wordToMsgs(extend, msg);
        return (i & i2) ^ Utils.parity16(extend);
    }

    private void mpc_AND(int[] iArr, int[] iArr2, int[] iArr3, Tape tape, View[] viewArr) {
        Tape tape2 = tape;
        byte bit = Utils.getBit(tape2.tapes[0], tape2.pos);
        byte bit2 = Utils.getBit(tape2.tapes[1], tape2.pos);
        byte bit3 = Utils.getBit(tape2.tapes[2], tape2.pos);
        int i = iArr[0];
        int i2 = iArr2[1];
        int i3 = iArr[1];
        int i4 = iArr2[0];
        iArr3[0] = (((i & i4) ^ ((i & i2) ^ (i3 & i4))) ^ bit) ^ bit2;
        int i5 = iArr2[2];
        int i6 = iArr[2];
        iArr3[1] = (bit2 ^ ((i2 & i3) ^ ((i3 & i5) ^ (i6 & i2)))) ^ bit3;
        iArr3[2] = bit ^ ((((iArr2[0] & i6) ^ (iArr[0] & i5)) ^ (i5 & i6)) ^ bit3);
        Utils.setBit(viewArr[0].communicatedBits, tape2.pos, (byte) iArr3[0]);
        Utils.setBit(viewArr[1].communicatedBits, tape2.pos, (byte) iArr3[1]);
        Utils.setBit(viewArr[2].communicatedBits, tape2.pos, (byte) iArr3[2]);
        tape2.pos++;
    }

    private void mpc_LowMC(Tape tape, View[] viewArr, int[] iArr, int[] iArr2) {
        View[] viewArr2 = viewArr;
        int[] iArr3 = iArr2;
        Arrays.fill(iArr3, 0, iArr3.length, 0);
        int i = this.stateSizeWords;
        mpc_xor_constant(iArr2, i * 3, iArr, 0, i);
        KMatricesWithPointer KMatrix = this.lowmcConstants.KMatrix(this, 0);
        for (int i2 = 0; i2 < 3; i2++) {
            matrix_mul_offset(iArr2, i2 * this.stateSizeWords, viewArr2[i2].inputShare, 0, KMatrix.getData(), KMatrix.getMatrixPointer());
        }
        mpc_xor(iArr3, iArr3, 3);
        for (int i3 = 1; i3 <= this.numRounds; i3++) {
            KMatricesWithPointer KMatrix2 = this.lowmcConstants.KMatrix(this, i3);
            for (int i4 = 0; i4 < 3; i4++) {
                matrix_mul_offset(iArr2, i4 * this.stateSizeWords, viewArr2[i4].inputShare, 0, KMatrix2.getData(), KMatrix2.getMatrixPointer());
            }
            mpc_substitution(iArr3, tape, viewArr2);
            int i5 = i3 - 1;
            KMatricesWithPointer LMatrix = this.lowmcConstants.LMatrix(this, i5);
            int i6 = this.stateSizeWords;
            mpc_matrix_mul(iArr2, i6 * 3, iArr2, i6 * 3, LMatrix.getData(), LMatrix.getMatrixPointer(), 3);
            KMatricesWithPointer RConstant = this.lowmcConstants.RConstant(this, i5);
            mpc_xor_constant(iArr2, this.stateSizeWords * 3, RConstant.getData(), RConstant.getMatrixPointer(), this.stateSizeWords);
            mpc_xor(iArr3, iArr3, 3);
        }
        for (int i7 = 0; i7 < 3; i7++) {
            System.arraycopy(iArr3, (i7 + 3) * this.stateSizeWords, viewArr2[i7].outputShare, 0, this.stateSizeWords);
        }
    }

    private void mpc_matrix_mul(int[] iArr, int i, int[] iArr2, int i2, int[] iArr3, int i3, int i4) {
        int i5 = 0;
        int i6 = i4;
        while (true) {
            if (i5 < i6) {
                int i7 = this.stateSizeWords;
                matrix_mul_offset(iArr, i + (i5 * i7), iArr2, i2 + (i7 * i5), iArr3, i3);
                i5++;
            } else {
                return;
            }
        }
    }

    private void mpc_sbox(int[] iArr, int[] iArr2, Tape tape, Msg msg) {
        int[] iArr3 = iArr;
        for (int i = 0; i < this.numSboxes * 3; i += 3) {
            int i2 = i + 2;
            int bitFromWordArray = Utils.getBitFromWordArray(iArr3, i2);
            int i3 = iArr2[i2];
            int i4 = i + 1;
            int bitFromWordArray2 = Utils.getBitFromWordArray(iArr3, i4);
            int i5 = iArr2[i4];
            int bitFromWordArray3 = Utils.getBitFromWordArray(iArr3, i);
            int i6 = iArr2[i];
            Tape tape2 = tape;
            Msg msg2 = msg;
            int mpc_AND = mpc_AND(bitFromWordArray, bitFromWordArray2, i3, i5, tape2, msg2);
            int mpc_AND2 = mpc_AND(bitFromWordArray2, bitFromWordArray3, i5, i6, tape2, msg2);
            int i7 = bitFromWordArray ^ bitFromWordArray2;
            Utils.setBitInWordArray(iArr3, i2, bitFromWordArray ^ mpc_AND2);
            Utils.setBitInWordArray(iArr3, i4, mpc_AND(bitFromWordArray3, bitFromWordArray, i6, i3, tape2, msg2) ^ i7);
            Utils.setBitInWordArray(iArr3, i, (i7 ^ bitFromWordArray3) ^ mpc_AND);
        }
    }

    private void mpc_substitution(int[] iArr, Tape tape, View[] viewArr) {
        int[] iArr2 = iArr;
        int[] iArr3 = new int[3];
        int[] iArr4 = new int[3];
        int[] iArr5 = new int[3];
        int[] iArr6 = new int[3];
        int[] iArr7 = new int[3];
        int[] iArr8 = new int[3];
        int i = 0;
        while (i < this.numSboxes * 3) {
            for (int i2 = 0; i2 < 3; i2++) {
                int i3 = ((i2 + 3) * this.stateSizeWords * 32) + i;
                iArr3[i2] = Utils.getBitFromWordArray(iArr2, i3 + 2);
                iArr4[i2] = Utils.getBitFromWordArray(iArr2, i3 + 1);
                iArr5[i2] = Utils.getBitFromWordArray(iArr2, i3);
            }
            Tape tape2 = tape;
            int i4 = i;
            View[] viewArr2 = viewArr;
            mpc_AND(iArr3, iArr4, iArr6, tape2, viewArr2);
            mpc_AND(iArr4, iArr5, iArr7, tape2, viewArr2);
            mpc_AND(iArr5, iArr3, iArr8, tape2, viewArr2);
            for (int i5 = 0; i5 < 3; i5++) {
                int i6 = ((i5 + 3) * this.stateSizeWords * 32) + i4;
                Utils.setBitInWordArray(iArr2, i6 + 2, iArr3[i5] ^ iArr7[i5]);
                Utils.setBitInWordArray(iArr2, i6 + 1, (iArr3[i5] ^ iArr4[i5]) ^ iArr8[i5]);
                Utils.setBitInWordArray(iArr2, i6, ((iArr3[i5] ^ iArr4[i5]) ^ iArr5[i5]) ^ iArr6[i5]);
            }
            i = i4 + 3;
        }
    }

    private void mpc_xor(int[] iArr, int[] iArr2, int i) {
        int i2 = this.stateSizeWords * i;
        for (int i3 = 0; i3 < i2; i3++) {
            int i4 = (this.stateSizeWords * i) + i3;
            iArr[i4] = iArr[i4] ^ iArr2[i3];
        }
    }

    private void mpc_xor_constant(int[] iArr, int i, int[] iArr2, int i2, int i3) {
        for (int i4 = 0; i4 < i3; i4++) {
            int i5 = i4 + i;
            iArr[i5] = iArr[i5] ^ iArr2[i4 + i2];
        }
    }

    private void mpc_xor_constant_verify(int[] iArr, int[] iArr2, int i, int i2, int i3) {
        int i4;
        if (i3 == 0) {
            i4 = this.stateSizeWords * 2;
        } else if (i3 == 2) {
            i4 = this.stateSizeWords * 3;
        } else {
            return;
        }
        for (int i5 = 0; i5 < i2; i5++) {
            int i6 = i5 + i4;
            iArr[i6] = iArr[i6] ^ iArr2[i5 + i];
        }
    }

    private void picnic_keygen(byte[] bArr, byte[] bArr2, byte[] bArr3, SecureRandom secureRandom) {
        int[] iArr = new int[(bArr3.length / 4)];
        int[] iArr2 = new int[(bArr.length / 4)];
        int[] iArr3 = new int[(bArr2.length / 4)];
        secureRandom.nextBytes(bArr3);
        Pack.littleEndianToInt(bArr3, 0, iArr);
        Utils.zeroTrailingBits(iArr, this.stateSizeBits);
        secureRandom.nextBytes(bArr);
        Pack.littleEndianToInt(bArr, 0, iArr2);
        Utils.zeroTrailingBits(iArr2, this.stateSizeBits);
        LowMCEnc(iArr2, iArr3, iArr);
        Pack.intToLittleEndian(iArr, bArr3, 0);
        Pack.intToLittleEndian(iArr2, bArr, 0);
        Pack.intToLittleEndian(iArr3, bArr2, 0);
    }

    private void picnic_read_public_key(int[] iArr, int[] iArr2, byte[] bArr) {
        int i = this.stateSizeBytes;
        int i2 = i + 1;
        int i3 = i / 4;
        Pack.littleEndianToInt(bArr, 1, iArr, 0, i3);
        Pack.littleEndianToInt(bArr, i2, iArr2, 0, i3);
        if (i3 < this.stateSizeWords) {
            int i4 = i3 * 4;
            int i5 = this.stateSizeBytes - i4;
            iArr[i3] = Pack.littleEndianToInt_Low(bArr, i4 + 1, i5);
            iArr2[i3] = Pack.littleEndianToInt_Low(bArr, i2 + i4, i5);
        }
    }

    private boolean picnic_sign(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        int serializeSignature2;
        int i = this.stateSizeWords;
        int[] iArr = new int[i];
        int[] iArr2 = new int[i];
        int[] iArr3 = new int[i];
        int i2 = this.stateSizeBytes;
        int i3 = i2 + 1;
        int i4 = (i2 * 2) + 1;
        int i5 = i2 / 4;
        Pack.littleEndianToInt(bArr, 1, iArr, 0, i5);
        Pack.littleEndianToInt(bArr, i3, iArr2, 0, i5);
        Pack.littleEndianToInt(bArr, i4, iArr3, 0, i5);
        if (i5 < this.stateSizeWords) {
            int i6 = i5 * 4;
            int i7 = this.stateSizeBytes - i6;
            iArr[i5] = Pack.littleEndianToInt_Low(bArr, i6 + 1, i7);
            iArr2[i5] = Pack.littleEndianToInt_Low(bArr, i3 + i6, i7);
            iArr3[i5] = Pack.littleEndianToInt_Low(bArr, i4 + i6, i7);
        }
        if (!is_picnic3(this.parameters)) {
            Signature signature = new Signature(this);
            if (sign_picnic1(iArr, iArr2, iArr3, bArr2, signature) != 0) {
                LOG.fine("Failed to create signature");
                return false;
            }
            serializeSignature2 = serializeSignature(signature, bArr3, bArr2.length + 4);
            if (serializeSignature2 < 0) {
                LOG.fine("Failed to serialize signature");
                return false;
            }
        } else {
            Signature2 signature2 = new Signature2(this);
            if (!sign_picnic3(iArr, iArr2, iArr3, bArr2, signature2)) {
                LOG.fine("Failed to create signature");
                return false;
            }
            serializeSignature2 = serializeSignature2(signature2, bArr3, bArr2.length + 4);
            if (serializeSignature2 < 0) {
                LOG.fine("Failed to serialize signature");
                return false;
            }
        }
        this.signatureLength = serializeSignature2;
        Pack.intToLittleEndian(serializeSignature2, bArr3, 0);
        return true;
    }

    private int picnic_verify(byte[] bArr, byte[] bArr2, byte[] bArr3, int i) {
        Logger logger;
        String str;
        int i2 = this.stateSizeWords;
        int[] iArr = new int[i2];
        int[] iArr2 = new int[i2];
        picnic_read_public_key(iArr, iArr2, bArr);
        if (is_picnic3(this.parameters)) {
            Signature2 signature2 = new Signature2(this);
            if (deserializeSignature2(signature2, bArr3, i, bArr2.length + 4) == 0) {
                return verify_picnic3(signature2, iArr, iArr2, bArr2);
            }
            logger = LOG;
            str = "Error couldn't deserialize signature (2)!";
        } else {
            Signature signature = new Signature(this);
            if (deserializeSignature(signature, bArr3, i, bArr2.length + 4) == 0) {
                return verify(signature, iArr, iArr2, bArr2);
            }
            logger = LOG;
            str = "Error couldn't deserialize signature!";
        }
        logger.fine(str);
        return -1;
    }

    private int picnic_write_private_key(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
        int i = this.stateSizeBytes;
        int i2 = (i * 3) + 1;
        if (bArr4.length < i2) {
            LOG.fine("Failed writing private key!");
            return -1;
        }
        bArr4[0] = (byte) this.parameters;
        System.arraycopy(bArr, 0, bArr4, 1, i);
        int i3 = this.stateSizeBytes;
        System.arraycopy(bArr2, 0, bArr4, i3 + 1, i3);
        int i4 = this.stateSizeBytes;
        System.arraycopy(bArr3, 0, bArr4, (i4 * 2) + 1, i4);
        return i2;
    }

    private int picnic_write_public_key(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        int i = this.stateSizeBytes;
        int i2 = (i * 2) + 1;
        if (bArr3.length < i2) {
            LOG.fine("Failed writing public key!");
            return -1;
        }
        bArr3[0] = (byte) this.parameters;
        System.arraycopy(bArr, 0, bArr3, 1, i);
        int i3 = this.stateSizeBytes;
        System.arraycopy(bArr2, 0, bArr3, i3 + 1, i3);
        return i2;
    }

    private int serializeSignature2(Signature2 signature2, byte[] bArr, int i) {
        int i2 = this.digestSizeBytes + 32 + signature2.iSeedInfoLen + signature2.cvInfoLen;
        for (int i3 = 0; i3 < this.numMPCRounds; i3++) {
            if (contains(signature2.challengeC, this.numOpenedRounds, i3)) {
                int i4 = signature2.challengeP[indexOf(signature2.challengeC, this.numOpenedRounds, i3)];
                int i5 = i2 + signature2.proofs[i3].seedInfoLen;
                if (i4 != this.numMPCParties - 1) {
                    i5 += this.andSizeBytes;
                }
                i2 = i5 + this.stateSizeBytes + this.andSizeBytes + this.digestSizeBytes;
            }
        }
        if (bArr.length < i2) {
            return -1;
        }
        System.arraycopy(signature2.challengeHash, 0, bArr, i, this.digestSizeBytes);
        int i6 = this.digestSizeBytes + i;
        System.arraycopy(signature2.salt, 0, bArr, i6, 32);
        int i7 = i6 + 32;
        System.arraycopy(signature2.iSeedInfo, 0, bArr, i7, signature2.iSeedInfoLen);
        int i8 = i7 + signature2.iSeedInfoLen;
        System.arraycopy(signature2.cvInfo, 0, bArr, i8, signature2.cvInfoLen);
        int i9 = i8 + signature2.cvInfoLen;
        for (int i10 = 0; i10 < this.numMPCRounds; i10++) {
            if (contains(signature2.challengeC, this.numOpenedRounds, i10)) {
                System.arraycopy(signature2.proofs[i10].seedInfo, 0, bArr, i9, signature2.proofs[i10].seedInfoLen);
                int i11 = i9 + signature2.proofs[i10].seedInfoLen;
                if (signature2.challengeP[indexOf(signature2.challengeC, this.numOpenedRounds, i10)] != this.numMPCParties - 1) {
                    System.arraycopy(signature2.proofs[i10].aux, 0, bArr, i11, this.andSizeBytes);
                    i11 += this.andSizeBytes;
                }
                System.arraycopy(signature2.proofs[i10].input, 0, bArr, i11, this.stateSizeBytes);
                int i12 = i11 + this.stateSizeBytes;
                System.arraycopy(signature2.proofs[i10].msgs, 0, bArr, i12, this.andSizeBytes);
                int i13 = i12 + this.andSizeBytes;
                System.arraycopy(signature2.proofs[i10].C, 0, bArr, i13, this.digestSizeBytes);
                i9 = i13 + this.digestSizeBytes;
            }
        }
        return i9 - i;
    }

    private void setChallenge(byte[] bArr, int i, int i2) {
        int i3 = i * 2;
        Utils.setBit(bArr, i3, (byte) (i2 & 1));
        Utils.setBit(bArr, i3 + 1, (byte) ((i2 >>> 1) & 1));
    }

    private int sign_picnic1(int[] iArr, int[] iArr2, int[] iArr3, byte[] bArr, Signature signature) {
        byte[][] bArr2;
        byte[] bArr3;
        Signature signature2 = signature;
        int i = this.numMPCRounds;
        int i2 = 2;
        int[] iArr4 = new int[2];
        char c = 1;
        iArr4[1] = 3;
        char c2 = 0;
        iArr4[0] = i;
        View[][] viewArr = (View[][]) Array.newInstance(View.class, iArr4);
        int i3 = this.numMPCRounds;
        int i4 = this.numMPCParties;
        int[] iArr5 = new int[3];
        iArr5[2] = this.digestSizeBytes;
        iArr5[1] = i4;
        iArr5[0] = i3;
        byte[][][] bArr4 = (byte[][][]) Array.newInstance(Byte.TYPE, iArr5);
        int i5 = this.numMPCRounds;
        int[] iArr6 = new int[3];
        iArr6[2] = this.UnruhGWithInputBytes;
        iArr6[1] = 3;
        iArr6[0] = i5;
        byte[][][] bArr5 = (byte[][][]) Array.newInstance(Byte.TYPE, iArr6);
        byte[] computeSeeds = computeSeeds(iArr, iArr2, iArr3, bArr);
        int i6 = this.numMPCParties * this.seedSizeBytes;
        System.arraycopy(computeSeeds, this.numMPCRounds * i6, signature2.salt, 0, 32);
        Tape tape = new Tape(this);
        int i7 = this.stateSizeBytes;
        int max = Math.max(i7 * 9, i7 + this.andSizeBytes);
        byte[] bArr6 = new byte[max];
        int i8 = 0;
        while (i8 < this.numMPCRounds) {
            viewArr[i8][c2] = new View(this);
            viewArr[i8][c] = new View(this);
            viewArr[i8][i2] = new View(this);
            int i9 = 0;
            while (i9 < i2) {
                byte[][][] bArr7 = bArr5;
                String str = "createRandomTape failed";
                int i10 = i9;
                int i11 = i8;
                byte[] bArr8 = bArr6;
                int i12 = max;
                byte[][][] bArr9 = bArr4;
                Tape tape2 = tape;
                byte[] bArr10 = computeSeeds;
                if (!createRandomTape(computeSeeds, (this.seedSizeBytes * i9) + (i6 * i8), signature2.salt, i11, i10, bArr8, this.stateSizeBytes + this.andSizeBytes)) {
                    LOG.fine(str);
                    return -1;
                }
                int[] iArr7 = viewArr[i11][i10].inputShare;
                Pack.littleEndianToInt(bArr8, 0, iArr7);
                Utils.zeroTrailingBits(iArr7, this.stateSizeBits);
                System.arraycopy(bArr8, this.stateSizeBytes, tape2.tapes[i10], 0, this.andSizeBytes);
                i9 = i10 + 1;
                i8 = i11;
                bArr6 = bArr8;
                tape = tape2;
                bArr5 = bArr7;
                max = i12;
                bArr4 = bArr9;
                computeSeeds = bArr10;
                i2 = 2;
            }
            int i13 = i8;
            byte[] bArr11 = bArr6;
            int i14 = max;
            byte[] bArr12 = computeSeeds;
            byte[][][] bArr13 = bArr4;
            byte[][][] bArr14 = bArr5;
            String str2 = "createRandomTape failed";
            Tape tape3 = tape;
            int i15 = i6 * i13;
            if (!createRandomTape(bArr12, i15 + (this.seedSizeBytes * 2), signature2.salt, i13, 2, tape3.tapes[2], this.andSizeBytes)) {
                LOG.fine(str2);
                return -1;
            }
            xor_three(viewArr[i13][2].inputShare, iArr, viewArr[i13][0].inputShare, viewArr[i13][1].inputShare);
            tape3.pos = 0;
            int[] littleEndianToInt = Pack.littleEndianToInt(bArr11, 0, i14 / 4);
            mpc_LowMC(tape3, viewArr[i13], iArr3, littleEndianToInt);
            Pack.intToLittleEndian(littleEndianToInt, bArr11, 0);
            int[] iArr8 = new int[16];
            xor_three(iArr8, viewArr[i13][0].outputShare, viewArr[i13][1].outputShare, viewArr[i13][2].outputShare);
            if (!subarrayEquals(iArr8, iArr2, this.stateSizeWords)) {
                LOG.fine("Simulation failed; output does not match public key (round = " + i13 + ")");
                return -1;
            }
            byte[] bArr15 = bArr12;
            Commit(bArr15, i15, viewArr[i13][0], bArr13[i13][0]);
            Commit(bArr15, this.seedSizeBytes + i15, viewArr[i13][1], bArr13[i13][1]);
            Commit(bArr15, (this.seedSizeBytes * 2) + i15, viewArr[i13][2], bArr13[i13][2]);
            if (this.transform == 1) {
                bArr3 = bArr15;
                G(0, bArr15, i15, viewArr[i13][0], bArr14[i13][0]);
                byte[] bArr16 = bArr3;
                G(1, bArr16, i15 + this.seedSizeBytes, viewArr[i13][1], bArr14[i13][1]);
                G(2, bArr16, i15 + (this.seedSizeBytes * 2), viewArr[i13][2], bArr14[i13][2]);
            } else {
                bArr3 = bArr15;
            }
            i8 = i13 + 1;
            bArr6 = bArr11;
            tape = tape3;
            bArr5 = bArr14;
            computeSeeds = bArr3;
            max = i14;
            bArr4 = bArr13;
            i2 = 2;
            c = 1;
            c2 = 0;
        }
        byte[] bArr17 = computeSeeds;
        byte[][][] bArr18 = bArr4;
        byte[][][] bArr19 = bArr5;
        int[] iArr9 = iArr2;
        int[] iArr10 = iArr3;
        H3(iArr2, iArr3, viewArr, bArr18, signature2.challengeBits, signature2.salt, bArr, bArr19);
        for (int i16 = 0; i16 < this.numMPCRounds; i16++) {
            Signature.Proof proof = signature2.proofs[i16];
            int challenge = getChallenge(signature2.challengeBits, i16);
            int i17 = i6 * i16;
            View[] viewArr2 = viewArr[i16];
            byte[][] bArr20 = bArr18[i16];
            if (this.transform != 1) {
                bArr2 = null;
                byte[][] bArr21 = null;
            } else {
                bArr2 = bArr19[i16];
            }
            prove(proof, challenge, bArr17, i17, viewArr2, bArr20, bArr2);
        }
        return 0;
    }

    private boolean sign_picnic3(int[] iArr, int[] iArr2, int[] iArr3, byte[] bArr, Signature2 signature2) {
        int i;
        int i2;
        int i3;
        int i4;
        Signature2 signature22 = signature2;
        int i5 = this.seedSizeBytes + 32;
        byte[] bArr2 = new byte[i5];
        computeSaltAndRootSeed(bArr2, iArr, iArr2, iArr3, bArr);
        byte[] copyOfRange = Arrays.copyOfRange(bArr2, 32, i5);
        signature22.salt = Arrays.copyOfRange(bArr2, 0, 32);
        Tree tree = new Tree(this, this.numMPCRounds, this.seedSizeBytes);
        tree.generateSeeds(copyOfRange, signature22.salt, 0);
        byte[][] leaves = tree.getLeaves();
        int leavesOffset = tree.getLeavesOffset();
        int i6 = this.numMPCRounds;
        Tape[] tapeArr = new Tape[i6];
        Tree[] treeArr = new Tree[i6];
        int i7 = 0;
        while (true) {
            i = this.numMPCRounds;
            if (i7 >= i) {
                break;
            }
            tapeArr[i7] = new Tape(this);
            Tree tree2 = new Tree(this, this.numMPCParties, this.seedSizeBytes);
            treeArr[i7] = tree2;
            tree2.generateSeeds(leaves[i7 + leavesOffset], signature22.salt, i7);
            createRandomTapes(tapeArr[i7], treeArr[i7].getLeaves(), treeArr[i7].getLeavesOffset(), signature22.salt, i7);
            i7++;
        }
        int[] iArr4 = new int[2];
        iArr4[1] = this.stateSizeWords * 4;
        iArr4[0] = i;
        byte[][] bArr3 = (byte[][]) Array.newInstance(Byte.TYPE, iArr4);
        byte[] bArr4 = new byte[MAX_AUX_BYTES];
        int i8 = 0;
        while (true) {
            i2 = this.numMPCRounds;
            if (i8 >= i2) {
                break;
            }
            tapeArr[i8].computeAuxTape(bArr3[i8]);
            i8++;
        }
        int i9 = this.numMPCParties;
        int[] iArr5 = new int[3];
        iArr5[2] = this.digestSizeBytes;
        iArr5[1] = i9;
        iArr5[0] = i2;
        byte[][][] bArr5 = (byte[][][]) Array.newInstance(Byte.TYPE, iArr5);
        int i10 = 0;
        while (true) {
            i3 = this.numMPCRounds;
            if (i10 >= i3) {
                break;
            }
            int i11 = 0;
            while (true) {
                i4 = this.numMPCParties;
                if (i11 >= i4 - 1) {
                    break;
                }
                int i12 = i11;
                commit(bArr5[i10][i11], treeArr[i10].getLeaf(i11), (byte[]) null, signature22.salt, i10, i12);
                i11 = i12 + 1;
                i10 = i10;
            }
            int i13 = i10;
            int i14 = i4 - 1;
            getAuxBits(bArr4, tapeArr[i13]);
            commit(bArr5[i13][i14], treeArr[i13].getLeaf(i14), bArr4, signature22.salt, i13, i14);
            i10 = i13 + 1;
        }
        Msg[] msgArr = new Msg[i3];
        int[] iArr6 = new int[this.stateSizeBits];
        int i15 = 0;
        while (true) {
            int i16 = this.numMPCRounds;
            if (i15 < i16) {
                msgArr[i15] = new Msg(this);
                int[] littleEndianToInt = Pack.littleEndianToInt(bArr3[i15], 0, this.stateSizeWords);
                xor_array(littleEndianToInt, littleEndianToInt, iArr, 0);
                int[] iArr7 = littleEndianToInt;
                int i17 = i15;
                int[] iArr8 = iArr6;
                if (simulateOnline(littleEndianToInt, tapeArr[i15], iArr6, msgArr[i15], iArr3, iArr2) != 0) {
                    LOG.fine("MPC simulation failed, aborting signature");
                    return false;
                }
                Pack.intToLittleEndian(iArr7, bArr3[i17], 0);
                i15 = i17 + 1;
                iArr6 = iArr8;
            } else {
                int[] iArr9 = new int[2];
                iArr9[1] = this.digestSizeBytes;
                iArr9[0] = i16;
                byte[][] bArr6 = (byte[][]) Array.newInstance(Byte.TYPE, iArr9);
                int i18 = this.numMPCRounds;
                int[] iArr10 = new int[2];
                iArr10[1] = this.digestSizeBytes;
                iArr10[0] = i18;
                byte[][] bArr7 = (byte[][]) Array.newInstance(Byte.TYPE, iArr10);
                for (int i19 = 0; i19 < this.numMPCRounds; i19++) {
                    commit_h(bArr6[i19], bArr5[i19]);
                    commit_v(bArr7[i19], bArr3[i19], msgArr[i19]);
                }
                Tree tree3 = new Tree(this, this.numMPCRounds, this.digestSizeBytes);
                tree3.buildMerkleTree(bArr7, signature22.salt);
                signature22.challengeC = new int[this.numOpenedRounds];
                signature22.challengeP = new int[this.numOpenedRounds];
                signature22.challengeHash = new byte[this.digestSizeBytes];
                Msg[] msgArr2 = msgArr;
                Tape[] tapeArr2 = tapeArr;
                HCP(signature22.challengeHash, signature22.challengeC, signature22.challengeP, bArr6, tree3.nodes[0], signature22.salt, iArr2, iArr3, bArr);
                int[] iArr11 = new int[1];
                signature22.cvInfo = tree3.openMerkleTree(getMissingLeavesList(signature22.challengeC), this.numMPCRounds - this.numOpenedRounds, iArr11);
                signature22.cvInfoLen = iArr11[0];
                signature22.iSeedInfo = new byte[(this.numMPCRounds * this.seedSizeBytes)];
                signature22.iSeedInfoLen = tree.revealSeeds(signature22.challengeC, this.numOpenedRounds, signature22.iSeedInfo, this.numMPCRounds * this.seedSizeBytes);
                signature22.proofs = new Signature2.Proof2[this.numMPCRounds];
                for (int i20 = 0; i20 < this.numMPCRounds; i20++) {
                    if (contains(signature22.challengeC, this.numOpenedRounds, i20)) {
                        signature22.proofs[i20] = new Signature2.Proof2(this);
                        int indexOf = indexOf(signature22.challengeC, this.numOpenedRounds, i20);
                        signature22.proofs[i20].seedInfo = new byte[(this.numMPCParties * this.seedSizeBytes)];
                        signature22.proofs[i20].seedInfoLen = treeArr[i20].revealSeeds(new int[]{signature22.challengeP[indexOf]}, 1, signature22.proofs[i20].seedInfo, this.numMPCParties * this.seedSizeBytes);
                        if (signature22.challengeP[indexOf] != this.numMPCParties - 1) {
                            getAuxBits(signature22.proofs[i20].aux, tapeArr2[i20]);
                        }
                        System.arraycopy(bArr3[i20], 0, signature22.proofs[i20].input, 0, this.stateSizeBytes);
                        System.arraycopy(msgArr2[i20].msgs[signature22.challengeP[indexOf]], 0, signature22.proofs[i20].msgs, 0, this.andSizeBytes);
                        System.arraycopy(bArr5[i20][signature22.challengeP[indexOf]], 0, signature22.proofs[i20].C, 0, this.digestSizeBytes);
                    }
                }
                return true;
            }
        }
    }

    private int simulateOnline(int[] iArr, Tape tape, int[] iArr2, Msg msg, int[] iArr3, int[] iArr4) {
        int[] iArr5 = new int[16];
        int[] iArr6 = new int[16];
        KMatricesWithPointer KMatrix = this.lowmcConstants.KMatrix(this, 0);
        matrix_mul(iArr5, iArr, KMatrix.getData(), KMatrix.getMatrixPointer());
        xor_array(iArr6, iArr5, iArr3, 0);
        for (int i = 1; i <= this.numRounds; i++) {
            tapesToWords(iArr2, tape);
            mpc_sbox(iArr6, iArr2, tape, msg);
            int i2 = i - 1;
            KMatricesWithPointer LMatrix = this.lowmcConstants.LMatrix(this, i2);
            matrix_mul(iArr6, iArr6, LMatrix.getData(), LMatrix.getMatrixPointer());
            KMatricesWithPointer RConstant = this.lowmcConstants.RConstant(this, i2);
            xor_array(iArr6, iArr6, RConstant.getData(), RConstant.getMatrixPointer());
            KMatricesWithPointer KMatrix2 = this.lowmcConstants.KMatrix(this, i);
            matrix_mul(iArr5, iArr, KMatrix2.getData(), KMatrix2.getMatrixPointer());
            xor_array(iArr6, iArr5, iArr6, 0);
        }
        return !subarrayEquals(iArr6, iArr4, this.stateSizeWords) ? -1 : 0;
    }

    private static boolean subarrayEquals(byte[] bArr, byte[] bArr2, int i) {
        if (bArr.length < i || bArr2.length < i) {
            return false;
        }
        for (int i2 = 0; i2 < i; i2++) {
            if (bArr[i2] != bArr2[i2]) {
                return false;
            }
        }
        return true;
    }

    private static boolean subarrayEquals(int[] iArr, int[] iArr2, int i) {
        if (iArr.length < i || iArr2.length < i) {
            return false;
        }
        for (int i2 = 0; i2 < i; i2++) {
            if (iArr[i2] != iArr2[i2]) {
                return false;
            }
        }
        return true;
    }

    private void substitution(int[] iArr) {
        for (int i = 0; i < this.numSboxes * 3; i += 3) {
            int i2 = i + 2;
            int bitFromWordArray = Utils.getBitFromWordArray(iArr, i2);
            int i3 = i + 1;
            int bitFromWordArray2 = Utils.getBitFromWordArray(iArr, i3);
            int bitFromWordArray3 = Utils.getBitFromWordArray(iArr, i);
            Utils.setBitInWordArray(iArr, i2, (bitFromWordArray2 & bitFromWordArray3) ^ bitFromWordArray);
            int i4 = bitFromWordArray ^ bitFromWordArray2;
            Utils.setBitInWordArray(iArr, i3, (bitFromWordArray & bitFromWordArray3) ^ i4);
            Utils.setBitInWordArray(iArr, i, (i4 ^ bitFromWordArray3) ^ (bitFromWordArray & bitFromWordArray2));
        }
    }

    private void tapesToWords(int[] iArr, Tape tape) {
        for (int i = 0; i < this.stateSizeBits; i++) {
            iArr[i] = tape.tapesToWord();
        }
    }

    private void updateDigest(int[] iArr, byte[] bArr) {
        Pack.intToLittleEndian(iArr, bArr, 0);
        this.digest.update(bArr, 0, this.stateSizeBytes);
    }

    private int verify(Signature signature, int[] iArr, int[] iArr2, byte[] bArr) {
        Signature signature2 = signature;
        int i = this.numMPCRounds;
        int i2 = this.numMPCParties;
        int[] iArr3 = new int[3];
        iArr3[2] = this.digestSizeBytes;
        iArr3[1] = i2;
        iArr3[0] = i;
        byte[][][] bArr2 = (byte[][][]) Array.newInstance(Byte.TYPE, iArr3);
        int i3 = this.numMPCRounds;
        int[] iArr4 = new int[3];
        iArr4[2] = this.UnruhGWithInputBytes;
        iArr4[1] = 3;
        iArr4[0] = i3;
        byte[][][] bArr3 = (byte[][][]) Array.newInstance(Byte.TYPE, iArr4);
        int i4 = this.numMPCRounds;
        int[] iArr5 = new int[3];
        iArr5[2] = this.stateSizeBytes;
        iArr5[1] = 3;
        iArr5[0] = i4;
        int[][][] iArr6 = (int[][][]) Array.newInstance(Integer.TYPE, iArr5);
        Signature.Proof[] proofArr = signature2.proofs;
        byte[] bArr4 = signature2.challengeBits;
        int i5 = this.stateSizeBytes;
        byte[] bArr5 = new byte[Math.max(i5 * 6, i5 + this.andSizeBytes)];
        Tape tape = new Tape(this);
        int i6 = this.numMPCRounds;
        View[] viewArr = new View[i6];
        View[] viewArr2 = new View[i6];
        int i7 = 0;
        while (true) {
            int i8 = this.numMPCRounds;
            if (i7 < i8) {
                viewArr[i7] = new View(this);
                View view = new View(this);
                viewArr2[i7] = view;
                Signature.Proof proof = proofArr[i7];
                View view2 = viewArr[i7];
                String str = "Invalid signature. Did not verify";
                View view3 = view2;
                int i9 = i7;
                View[] viewArr3 = viewArr2;
                View[] viewArr4 = viewArr;
                Tape tape2 = tape;
                byte[] bArr6 = bArr5;
                byte[] bArr7 = bArr4;
                Signature.Proof[] proofArr2 = proofArr;
                if (!verifyProof(proof, view3, view, getChallenge(bArr4, i7), signature2.salt, i9, bArr5, iArr2, tape2)) {
                    LOG.fine(str);
                    return -1;
                }
                int i10 = i9;
                int challenge = getChallenge(bArr7, i10);
                Commit(proofArr2[i10].seed1, 0, viewArr4[i10], bArr2[i10][challenge]);
                int i11 = (challenge + 1) % 3;
                Commit(proofArr2[i10].seed2, 0, viewArr3[i10], bArr2[i10][i11]);
                int i12 = (challenge + 2) % 3;
                System.arraycopy(proofArr2[i10].view3Commitment, 0, bArr2[i10][i12], 0, this.digestSizeBytes);
                if (this.transform == 1) {
                    G(challenge, proofArr2[i10].seed1, 0, viewArr4[i10], bArr3[i10][challenge]);
                    G(i11, proofArr2[i10].seed2, 0, viewArr3[i10], bArr3[i10][i11]);
                    System.arraycopy(proofArr2[i10].view3UnruhG, 0, bArr3[i10][i12], 0, challenge == 0 ? this.UnruhGWithInputBytes : this.UnruhGWithoutInputBytes);
                }
                iArr6[i10][challenge] = viewArr4[i10].outputShare;
                iArr6[i10][i11] = viewArr3[i10].outputShare;
                int[] iArr7 = new int[this.stateSizeWords];
                xor_three(iArr7, viewArr4[i10].outputShare, viewArr3[i10].outputShare, iArr);
                iArr6[i10][i12] = iArr7;
                i7 = i10 + 1;
                bArr4 = bArr7;
                viewArr = viewArr4;
                tape = tape2;
                viewArr2 = viewArr3;
                bArr5 = bArr6;
                proofArr = proofArr2;
            } else {
                int[] iArr8 = iArr;
                String str2 = "Invalid signature. Did not verify";
                byte[] bArr8 = bArr4;
                byte[] bArr9 = new byte[Utils.numBytes(i8 * 2)];
                H3(iArr, iArr2, iArr6, bArr2, bArr9, signature2.salt, bArr, bArr3);
                if (subarrayEquals(bArr8, bArr9, Utils.numBytes(this.numMPCRounds * 2))) {
                    return 0;
                }
                LOG.fine(str2);
                return -1;
            }
        }
    }

    private int verify_picnic3(Signature2 signature2, int[] iArr, int[] iArr2, byte[] bArr) {
        int verifyMerkleTree;
        Logger logger;
        String str;
        Tree tree;
        Tree[] treeArr;
        byte[] bArr2;
        int i;
        Tree tree2;
        int i2;
        int i3;
        Signature2 signature22 = signature2;
        int i4 = this.numMPCRounds;
        int i5 = this.numMPCParties;
        int[] iArr3 = new int[3];
        iArr3[2] = this.digestSizeBytes;
        iArr3[1] = i5;
        iArr3[0] = i4;
        byte[][][] bArr3 = (byte[][][]) Array.newInstance(Byte.TYPE, iArr3);
        int i6 = this.numMPCRounds;
        int[] iArr4 = new int[2];
        iArr4[1] = this.digestSizeBytes;
        iArr4[0] = i6;
        byte[][] bArr4 = (byte[][]) Array.newInstance(Byte.TYPE, iArr4);
        int i7 = this.numMPCRounds;
        int[] iArr5 = new int[2];
        iArr5[1] = this.digestSizeBytes;
        iArr5[0] = i7;
        byte[][] bArr5 = (byte[][]) Array.newInstance(Byte.TYPE, iArr5);
        Msg[] msgArr = new Msg[this.numMPCRounds];
        Tree tree3 = new Tree(this, this.numMPCRounds, this.digestSizeBytes);
        byte[] bArr6 = new byte[64];
        int i8 = this.numMPCRounds;
        Tree[] treeArr2 = new Tree[i8];
        Tape[] tapeArr = new Tape[i8];
        Tree tree4 = new Tree(this, this.numMPCRounds, this.seedSizeBytes);
        if (tree4.reconstructSeeds(signature22.challengeC, this.numOpenedRounds, signature22.iSeedInfo, signature22.iSeedInfoLen, signature22.salt, 0) != 0) {
            return -1;
        }
        int i9 = 0;
        while (true) {
            if (i9 < this.numMPCRounds) {
                if (!contains(signature22.challengeC, this.numOpenedRounds, i9)) {
                    Tree tree5 = new Tree(this, this.numMPCParties, this.seedSizeBytes);
                    treeArr2[i9] = tree5;
                    tree5.generateSeeds(tree4.getLeaf(i9), signature22.salt, i9);
                } else {
                    treeArr2[i9] = new Tree(this, this.numMPCParties, this.seedSizeBytes);
                    if (treeArr2[i9].reconstructSeeds(new int[]{signature22.challengeP[indexOf(signature22.challengeC, this.numOpenedRounds, i9)]}, 1, signature22.proofs[i9].seedInfo, signature22.proofs[i9].seedInfoLen, signature22.salt, i9) != 0) {
                        logger = LOG;
                        str = "Failed to reconstruct seeds for round " + i9;
                        break;
                    }
                }
                i9++;
            } else {
                int i10 = this.numMPCParties - 1;
                byte[] bArr7 = new byte[MAX_AUX_BYTES];
                int i11 = 0;
                while (i11 < this.numMPCRounds) {
                    Tape tape = new Tape(this);
                    tapeArr[i11] = tape;
                    byte[] bArr8 = bArr6;
                    byte[] bArr9 = bArr7;
                    Tape[] tapeArr2 = tapeArr;
                    int i12 = i11;
                    createRandomTapes(tape, treeArr2[i11].getLeaves(), treeArr2[i11].getLeavesOffset(), signature22.salt, i12);
                    if (!contains(signature22.challengeC, this.numOpenedRounds, i12)) {
                        tapeArr2[i12].computeAuxTape((byte[]) null);
                        int i13 = 0;
                        while (i13 < i10) {
                            int i14 = i12;
                            commit(bArr3[i12][i13], treeArr2[i12].getLeaf(i13), (byte[]) null, signature22.salt, i12, i13);
                            i13++;
                            treeArr2 = treeArr2;
                        }
                        i = i12;
                        treeArr = treeArr2;
                        byte[] bArr10 = bArr9;
                        getAuxBits(bArr10, tapeArr2[i]);
                        bArr2 = bArr10;
                        commit(bArr3[i][i10], treeArr[i].getLeaf(i10), bArr10, signature22.salt, i12, i10);
                        tree = tree3;
                    } else {
                        i = i12;
                        treeArr = treeArr2;
                        bArr2 = bArr9;
                        int i15 = signature22.challengeP[indexOf(signature22.challengeC, this.numOpenedRounds, i)];
                        int i16 = 0;
                        while (i16 < i10) {
                            if (i16 != i15) {
                                i2 = i16;
                                tree2 = tree3;
                                i3 = i15;
                                commit(bArr3[i][i16], treeArr[i].getLeaf(i16), (byte[]) null, signature22.salt, i, i2);
                            } else {
                                i2 = i16;
                                tree2 = tree3;
                                i3 = i15;
                            }
                            i16 = i2 + 1;
                            i15 = i3;
                            tree3 = tree2;
                        }
                        tree = tree3;
                        int i17 = i15;
                        if (i10 != i17) {
                            commit(bArr3[i][i10], treeArr[i].getLeaf(i10), signature22.proofs[i].aux, signature22.salt, i, i10);
                        }
                        System.arraycopy(signature22.proofs[i].C, 0, bArr3[i][i17], 0, this.digestSizeBytes);
                    }
                    i11 = i + 1;
                    bArr7 = bArr2;
                    tapeArr = tapeArr2;
                    treeArr2 = treeArr;
                    bArr6 = bArr8;
                    tree3 = tree;
                }
                Tape[] tapeArr3 = tapeArr;
                Tree tree6 = tree3;
                byte[] bArr11 = bArr6;
                for (int i18 = 0; i18 < this.numMPCRounds; i18++) {
                    commit_h(bArr4[i18], bArr3[i18]);
                }
                int[] iArr6 = new int[this.stateSizeBits];
                int i19 = 0;
                while (true) {
                    int i20 = this.numMPCRounds;
                    if (i19 < i20) {
                        msgArr[i19] = new Msg(this);
                        if (contains(signature22.challengeC, this.numOpenedRounds, i19)) {
                            int i21 = signature22.challengeP[indexOf(signature22.challengeC, this.numOpenedRounds, i19)];
                            if (i21 != i10) {
                                tapeArr3[i19].setAuxBits(signature22.proofs[i19].aux);
                            }
                            System.arraycopy(signature22.proofs[i19].msgs, 0, msgArr[i19].msgs[i21], 0, this.andSizeBytes);
                            Arrays.fill(tapeArr3[i19].tapes[i21], (byte) 0);
                            msgArr[i19].unopened = i21;
                            byte[] bArr12 = new byte[(this.stateSizeWords * 4)];
                            System.arraycopy(signature22.proofs[i19].input, 0, bArr12, 0, signature22.proofs[i19].input.length);
                            int i22 = this.stateSizeWords;
                            int[] iArr7 = new int[i22];
                            Pack.littleEndianToInt(bArr12, 0, iArr7, 0, i22);
                            if (simulateOnline(iArr7, tapeArr3[i19], iArr6, msgArr[i19], iArr2, iArr) != 0) {
                                logger = LOG;
                                str = "MPC simulation failed for round " + i19 + ", signature invalid";
                                break;
                            }
                            commit_v(bArr5[i19], signature22.proofs[i19].input, msgArr[i19]);
                        } else {
                            bArr5[i19] = null;
                        }
                        i19++;
                    } else {
                        Tree tree7 = tree6;
                        if (tree7.addMerkleNodes(getMissingLeavesList(signature22.challengeC), i20 - this.numOpenedRounds, signature22.cvInfo, signature22.cvInfoLen) != 0 || (verifyMerkleTree = tree7.verifyMerkleTree(bArr5, signature22.salt)) != 0) {
                            return -1;
                        }
                        HCP(bArr11, (int[]) null, (int[]) null, bArr4, tree7.nodes[0], signature22.salt, iArr, iArr2, bArr);
                        if (subarrayEquals(signature22.challengeHash, bArr11, this.digestSizeBytes)) {
                            return verifyMerkleTree;
                        }
                        logger = LOG;
                        str = "Challenge does not match, signature invalid";
                    }
                }
            }
        }
        logger.fine(str);
        return -1;
    }

    private void wordToMsgs(int i, Msg msg) {
        for (int i2 = 0; i2 < this.numMPCParties; i2++) {
            Utils.setBit(msg.msgs[i2], msg.pos, (byte) Utils.getBit(i, i2));
        }
        msg.pos++;
    }

    private void xor_three(int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4) {
        for (int i = 0; i < this.stateSizeWords; i++) {
            iArr[i] = (iArr2[i] ^ iArr3[i]) ^ iArr4[i];
        }
    }

    /* access modifiers changed from: protected */
    public void aux_mpc_sbox(int[] iArr, int[] iArr2, Tape tape) {
        for (int i = 0; i < this.numSboxes * 3; i += 3) {
            int i2 = i + 2;
            int bitFromWordArray = Utils.getBitFromWordArray(iArr, i2);
            int i3 = i + 1;
            int bitFromWordArray2 = Utils.getBitFromWordArray(iArr, i3);
            int bitFromWordArray3 = Utils.getBitFromWordArray(iArr, i);
            int bitFromWordArray4 = Utils.getBitFromWordArray(iArr2, i2);
            int bitFromWordArray5 = Utils.getBitFromWordArray(iArr2, i3);
            aux_mpc_AND(bitFromWordArray, bitFromWordArray2, ((Utils.getBitFromWordArray(iArr2, i) ^ bitFromWordArray) ^ bitFromWordArray2) ^ bitFromWordArray3, tape);
            aux_mpc_AND(bitFromWordArray2, bitFromWordArray3, bitFromWordArray4 ^ bitFromWordArray, tape);
            aux_mpc_AND(bitFromWordArray3, bitFromWordArray, (bitFromWordArray5 ^ bitFromWordArray) ^ bitFromWordArray2, tape);
        }
    }

    public void crypto_sign(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        if (picnic_sign(bArr3, bArr2, bArr)) {
            System.arraycopy(bArr2, 0, bArr, 4, bArr2.length);
        }
    }

    public void crypto_sign_keypair(byte[] bArr, byte[] bArr2, SecureRandom secureRandom) {
        int i = this.stateSizeWords;
        byte[] bArr3 = new byte[(i * 4)];
        byte[] bArr4 = new byte[(i * 4)];
        byte[] bArr5 = new byte[(i * 4)];
        picnic_keygen(bArr3, bArr4, bArr5, secureRandom);
        picnic_write_public_key(bArr4, bArr3, bArr);
        picnic_write_private_key(bArr5, bArr4, bArr3, bArr2);
    }

    public boolean crypto_sign_open(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        if (picnic_verify(bArr3, Arrays.copyOfRange(bArr2, 4, bArr.length + 4), bArr2, Pack.littleEndianToInt(bArr2, 0)) == -1) {
            return false;
        }
        System.arraycopy(bArr2, 4, bArr, 0, bArr.length);
        return true;
    }

    /* access modifiers changed from: package-private */
    public int getChallenge(byte[] bArr, int i) {
        return Utils.getCrumbAligned(bArr, i);
    }

    public int getPublicKeySize() {
        return this.CRYPTO_PUBLICKEYBYTES;
    }

    public int getSecretKeySize() {
        return this.CRYPTO_SECRETKEYBYTES;
    }

    public int getSignatureSize(int i) {
        return this.CRYPTO_BYTES + i;
    }

    public int getTrueSignatureSize() {
        return this.signatureLength;
    }

    /* access modifiers changed from: protected */
    public void matrix_mul(int[] iArr, int[] iArr2, int[] iArr3, int i) {
        matrix_mul_offset(iArr, 0, iArr2, 0, iArr3, i);
    }

    /* access modifiers changed from: protected */
    public void matrix_mul_offset(int[] iArr, int i, int[] iArr2, int i2, int[] iArr3, int i3) {
        int[] iArr4 = new int[16];
        int i4 = this.stateSizeWords;
        iArr4[i4 - 1] = 0;
        int i5 = this.stateSizeBits;
        int i6 = i5 / 32;
        int i7 = (i4 * 32) - i5;
        int bitPermuteStepSimple = Bits.bitPermuteStepSimple(Bits.bitPermuteStepSimple(Bits.bitPermuteStepSimple(-1 >>> i7, 1431655765, 1), 858993459, 2), 252645135, 4);
        for (int i8 = 0; i8 < this.stateSizeBits; i8++) {
            int i9 = 0;
            for (int i10 = 0; i10 < i6; i10++) {
                i9 ^= iArr3[i3 + ((this.stateSizeWords * i8) + i10)] & iArr2[i2 + i10];
            }
            if (i7 > 0) {
                i9 ^= (iArr3[i3 + ((this.stateSizeWords * i8) + i6)] & iArr2[i2 + i6]) & bitPermuteStepSimple;
            }
            Utils.setBit(iArr4, i8, Utils.parity32(i9));
        }
        int[] iArr5 = iArr;
        int i11 = i;
        System.arraycopy(iArr4, 0, iArr, i, this.stateSizeWords);
    }

    /* access modifiers changed from: package-private */
    public void mpc_AND_verify(int[] iArr, int[] iArr2, int[] iArr3, Tape tape, View view, View view2) {
        byte bit = Utils.getBit(tape.tapes[0], tape.pos);
        byte bit2 = Utils.getBit(tape.tapes[1], tape.pos);
        int i = iArr[0];
        int i2 = iArr[1];
        int i3 = iArr2[0];
        iArr3[0] = ((((i2 & i3) ^ (iArr2[1] & i)) ^ (i & i3)) ^ bit) ^ bit2;
        Utils.setBit(view.communicatedBits, tape.pos, (byte) iArr3[0]);
        iArr3[1] = Utils.getBit(view2.communicatedBits, tape.pos);
        tape.pos++;
    }

    /* access modifiers changed from: package-private */
    public void mpc_LowMC_verify(View view, View view2, Tape tape, int[] iArr, int[] iArr2, int i) {
        View view3 = view;
        View view4 = view2;
        int[] iArr3 = iArr;
        Arrays.fill(iArr3, 0, iArr3.length, 0);
        int[] iArr4 = iArr;
        mpc_xor_constant_verify(iArr4, iArr2, 0, this.stateSizeWords, i);
        KMatricesWithPointer KMatrix = this.lowmcConstants.KMatrix(this, 0);
        matrix_mul_offset(iArr4, 0, view3.inputShare, 0, KMatrix.getData(), KMatrix.getMatrixPointer());
        matrix_mul_offset(iArr4, this.stateSizeWords, view4.inputShare, 0, KMatrix.getData(), KMatrix.getMatrixPointer());
        mpc_xor(iArr3, iArr3, 2);
        for (int i2 = 1; i2 <= this.numRounds; i2++) {
            KMatricesWithPointer KMatrix2 = this.lowmcConstants.KMatrix(this, i2);
            int[] iArr5 = iArr;
            matrix_mul_offset(iArr5, 0, view3.inputShare, 0, KMatrix2.getData(), KMatrix2.getMatrixPointer());
            matrix_mul_offset(iArr5, this.stateSizeWords, view4.inputShare, 0, KMatrix2.getData(), KMatrix2.getMatrixPointer());
            mpc_substitution_verify(iArr3, tape, view3, view4);
            int i3 = i2 - 1;
            KMatricesWithPointer LMatrix = this.lowmcConstants.LMatrix(this, i3);
            int i4 = this.stateSizeWords;
            int[] iArr6 = iArr;
            mpc_matrix_mul(iArr6, i4 * 2, iArr, i4 * 2, LMatrix.getData(), LMatrix.getMatrixPointer(), 2);
            KMatricesWithPointer RConstant = this.lowmcConstants.RConstant(this, i3);
            mpc_xor_constant_verify(iArr6, RConstant.getData(), RConstant.getMatrixPointer(), this.stateSizeWords, i);
            mpc_xor(iArr3, iArr3, 2);
        }
        System.arraycopy(iArr3, this.stateSizeWords * 2, view3.outputShare, 0, this.stateSizeWords);
        System.arraycopy(iArr3, this.stateSizeWords * 3, view4.outputShare, 0, this.stateSizeWords);
    }

    /* access modifiers changed from: package-private */
    public void mpc_substitution_verify(int[] iArr, Tape tape, View view, View view2) {
        int[] iArr2 = iArr;
        int[] iArr3 = new int[2];
        int[] iArr4 = new int[2];
        int[] iArr5 = new int[2];
        int[] iArr6 = new int[2];
        int[] iArr7 = new int[2];
        int[] iArr8 = new int[2];
        int i = 0;
        while (i < this.numSboxes * 3) {
            for (int i2 = 0; i2 < 2; i2++) {
                int i3 = ((i2 + 2) * this.stateSizeWords * 32) + i;
                iArr3[i2] = Utils.getBitFromWordArray(iArr2, i3 + 2);
                iArr4[i2] = Utils.getBitFromWordArray(iArr2, i3 + 1);
                iArr5[i2] = Utils.getBitFromWordArray(iArr2, i3);
            }
            Tape tape2 = tape;
            View view3 = view;
            int i4 = i;
            View view4 = view2;
            mpc_AND_verify(iArr3, iArr4, iArr6, tape2, view3, view4);
            mpc_AND_verify(iArr4, iArr5, iArr7, tape2, view3, view4);
            mpc_AND_verify(iArr5, iArr3, iArr8, tape2, view3, view4);
            for (int i5 = 0; i5 < 2; i5++) {
                int i6 = ((i5 + 2) * this.stateSizeWords * 32) + i4;
                Utils.setBitInWordArray(iArr2, i6 + 2, iArr3[i5] ^ iArr7[i5]);
                Utils.setBitInWordArray(iArr2, i6 + 1, (iArr3[i5] ^ iArr4[i5]) ^ iArr8[i5]);
                Utils.setBitInWordArray(iArr2, i6, ((iArr3[i5] ^ iArr4[i5]) ^ iArr5[i5]) ^ iArr6[i5]);
            }
            i = i4 + 3;
        }
    }

    /* access modifiers changed from: package-private */
    public void prove(Signature.Proof proof, int i, byte[] bArr, int i2, View[] viewArr, byte[][] bArr2, byte[][] bArr3) {
        if (i == 0) {
            System.arraycopy(bArr, i2, proof.seed1, 0, this.seedSizeBytes);
            System.arraycopy(bArr, i2 + this.seedSizeBytes, proof.seed2, 0, this.seedSizeBytes);
        } else if (i == 1) {
            System.arraycopy(bArr, this.seedSizeBytes + i2, proof.seed1, 0, this.seedSizeBytes);
            System.arraycopy(bArr, i2 + (this.seedSizeBytes * 2), proof.seed2, 0, this.seedSizeBytes);
        } else if (i == 2) {
            System.arraycopy(bArr, (this.seedSizeBytes * 2) + i2, proof.seed1, 0, this.seedSizeBytes);
            System.arraycopy(bArr, i2, proof.seed2, 0, this.seedSizeBytes);
        } else {
            LOG.fine("Invalid challenge");
            throw new IllegalArgumentException("challenge");
        }
        if (i == 1 || i == 2) {
            System.arraycopy(viewArr[2].inputShare, 0, proof.inputShare, 0, this.stateSizeWords);
        }
        System.arraycopy(viewArr[(i + 1) % 3].communicatedBits, 0, proof.communicatedBits, 0, this.andSizeBytes);
        int i3 = (i + 2) % 3;
        System.arraycopy(bArr2[i3], 0, proof.view3Commitment, 0, this.digestSizeBytes);
        if (this.transform == 1) {
            System.arraycopy(bArr3[i3], 0, proof.view3UnruhG, 0, i == 0 ? this.UnruhGWithInputBytes : this.UnruhGWithoutInputBytes);
        }
    }

    /* access modifiers changed from: package-private */
    public int serializeSignature(Signature signature, byte[] bArr, int i) {
        Signature.Proof[] proofArr = signature.proofs;
        byte[] bArr2 = signature.challengeBits;
        int i2 = this.numMPCRounds;
        int numBytes = Utils.numBytes(this.numMPCRounds * 2) + 32 + (((this.seedSizeBytes * 2) + this.stateSizeBytes + this.andSizeBytes + this.digestSizeBytes) * i2);
        if (this.transform == 1) {
            numBytes += this.UnruhGWithoutInputBytes * i2;
        }
        if (this.CRYPTO_BYTES < numBytes) {
            return -1;
        }
        System.arraycopy(bArr2, 0, bArr, i, Utils.numBytes(i2 * 2));
        int numBytes2 = Utils.numBytes(this.numMPCRounds * 2) + i;
        System.arraycopy(signature.salt, 0, bArr, numBytes2, 32);
        int i3 = numBytes2 + 32;
        for (int i4 = 0; i4 < this.numMPCRounds; i4++) {
            int challenge = getChallenge(bArr2, i4);
            System.arraycopy(proofArr[i4].view3Commitment, 0, bArr, i3, this.digestSizeBytes);
            int i5 = i3 + this.digestSizeBytes;
            if (this.transform == 1) {
                int i6 = challenge == 0 ? this.UnruhGWithInputBytes : this.UnruhGWithoutInputBytes;
                System.arraycopy(proofArr[i4].view3UnruhG, 0, bArr, i5, i6);
                i5 += i6;
            }
            System.arraycopy(proofArr[i4].communicatedBits, 0, bArr, i5, this.andSizeBytes);
            int i7 = i5 + this.andSizeBytes;
            System.arraycopy(proofArr[i4].seed1, 0, bArr, i7, this.seedSizeBytes);
            int i8 = i7 + this.seedSizeBytes;
            System.arraycopy(proofArr[i4].seed2, 0, bArr, i8, this.seedSizeBytes);
            i3 = i8 + this.seedSizeBytes;
            if (challenge == 1 || challenge == 2) {
                Pack.intToLittleEndian(proofArr[i4].inputShare, 0, this.stateSizeWords, bArr, i3);
                i3 += this.stateSizeBytes;
            }
        }
        return i3 - i;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0072  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x00ca  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x011c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean verifyProof(org.bouncycastle.pqc.crypto.picnic.Signature.Proof r19, org.bouncycastle.pqc.crypto.picnic.View r20, org.bouncycastle.pqc.crypto.picnic.View r21, int r22, byte[] r23, int r24, byte[] r25, int[] r26, org.bouncycastle.pqc.crypto.picnic.Tape r27) {
        /*
            r18 = this;
            r8 = r18
            r9 = r19
            r10 = r20
            r11 = r21
            r12 = r22
            r13 = r25
            r14 = r27
            byte[] r0 = r9.communicatedBits
            byte[] r1 = r11.communicatedBits
            int r2 = r8.andSizeBytes
            r15 = 0
            java.lang.System.arraycopy(r0, r15, r1, r15, r2)
            r14.pos = r15
            r7 = 1
            if (r12 == 0) goto L_0x00d4
            if (r12 == r7) goto L_0x0084
            r0 = 2
            if (r12 == r0) goto L_0x002b
            java.util.logging.Logger r0 = LOG
            java.lang.String r1 = "Invalid Challenge!"
            r0.fine(r1)
            goto L_0x012e
        L_0x002b:
            byte[] r1 = r9.seed1
            byte[][] r0 = r14.tapes
            r6 = r0[r15]
            int r5 = r8.andSizeBytes
            r2 = 0
            r16 = 2
            r0 = r18
            r3 = r23
            r4 = r24
            r17 = r5
            r5 = r16
            r16 = 1
            r7 = r17
            boolean r0 = r0.createRandomTape(r1, r2, r3, r4, r5, r6, r7)
            int[] r1 = r9.inputShare
            int[] r2 = r10.inputShare
            int r3 = r8.stateSizeWords
            java.lang.System.arraycopy(r1, r15, r2, r15, r3)
            if (r0 == 0) goto L_0x006d
            byte[] r1 = r9.seed2
            int r0 = r8.stateSizeBytes
            int r2 = r8.andSizeBytes
            int r7 = r0 + r2
            r2 = 0
            r5 = 0
            r0 = r18
            r3 = r23
            r4 = r24
            r6 = r25
            boolean r0 = r0.createRandomTape(r1, r2, r3, r4, r5, r6, r7)
            if (r0 == 0) goto L_0x006d
            r7 = 1
            goto L_0x006e
        L_0x006d:
            r7 = 0
        L_0x006e:
            if (r7 != 0) goto L_0x0072
            goto L_0x012c
        L_0x0072:
            int[] r0 = r11.inputShare
            org.bouncycastle.util.Pack.littleEndianToInt((byte[]) r13, (int) r15, (int[]) r0)
            int r0 = r8.stateSizeBytes
            byte[][] r1 = r14.tapes
            r1 = r1[r16]
            int r2 = r8.andSizeBytes
            java.lang.System.arraycopy(r13, r0, r1, r15, r2)
            goto L_0x012c
        L_0x0084:
            r16 = 1
            byte[] r1 = r9.seed1
            int r0 = r8.stateSizeBytes
            int r2 = r8.andSizeBytes
            int r7 = r0 + r2
            r2 = 0
            r5 = 1
            r0 = r18
            r3 = r23
            r4 = r24
            r6 = r25
            boolean r0 = r0.createRandomTape(r1, r2, r3, r4, r5, r6, r7)
            int[] r1 = r10.inputShare
            org.bouncycastle.util.Pack.littleEndianToInt((byte[]) r13, (int) r15, (int[]) r1)
            int r1 = r8.stateSizeBytes
            byte[][] r2 = r14.tapes
            r2 = r2[r15]
            int r3 = r8.andSizeBytes
            java.lang.System.arraycopy(r13, r1, r2, r15, r3)
            if (r0 == 0) goto L_0x00c6
            byte[] r1 = r9.seed2
            byte[][] r0 = r14.tapes
            r6 = r0[r16]
            int r7 = r8.andSizeBytes
            r2 = 0
            r5 = 2
            r0 = r18
            r3 = r23
            r4 = r24
            boolean r0 = r0.createRandomTape(r1, r2, r3, r4, r5, r6, r7)
            if (r0 == 0) goto L_0x00c6
            r7 = 1
            goto L_0x00c7
        L_0x00c6:
            r7 = 0
        L_0x00c7:
            if (r7 != 0) goto L_0x00ca
            goto L_0x012c
        L_0x00ca:
            int[] r0 = r9.inputShare
            int[] r1 = r11.inputShare
            int r2 = r8.stateSizeWords
            java.lang.System.arraycopy(r0, r15, r1, r15, r2)
            goto L_0x012c
        L_0x00d4:
            r16 = 1
            byte[] r1 = r9.seed1
            int r0 = r8.stateSizeBytes
            int r2 = r8.andSizeBytes
            int r7 = r0 + r2
            r2 = 0
            r5 = 0
            r0 = r18
            r3 = r23
            r4 = r24
            r6 = r25
            boolean r0 = r0.createRandomTape(r1, r2, r3, r4, r5, r6, r7)
            int[] r1 = r10.inputShare
            org.bouncycastle.util.Pack.littleEndianToInt((byte[]) r13, (int) r15, (int[]) r1)
            int r1 = r8.stateSizeBytes
            byte[][] r2 = r14.tapes
            r2 = r2[r15]
            int r3 = r8.andSizeBytes
            java.lang.System.arraycopy(r13, r1, r2, r15, r3)
            if (r0 == 0) goto L_0x0118
            byte[] r1 = r9.seed2
            int r0 = r8.stateSizeBytes
            int r2 = r8.andSizeBytes
            int r7 = r0 + r2
            r2 = 0
            r5 = 1
            r0 = r18
            r3 = r23
            r4 = r24
            r6 = r25
            boolean r0 = r0.createRandomTape(r1, r2, r3, r4, r5, r6, r7)
            if (r0 == 0) goto L_0x0118
            r7 = 1
            goto L_0x0119
        L_0x0118:
            r7 = 0
        L_0x0119:
            if (r7 != 0) goto L_0x011c
            goto L_0x012c
        L_0x011c:
            int[] r0 = r11.inputShare
            org.bouncycastle.util.Pack.littleEndianToInt((byte[]) r13, (int) r15, (int[]) r0)
            int r0 = r8.stateSizeBytes
            byte[][] r1 = r14.tapes
            r1 = r1[r16]
            int r2 = r8.andSizeBytes
            java.lang.System.arraycopy(r13, r0, r1, r15, r2)
        L_0x012c:
            if (r7 != 0) goto L_0x0136
        L_0x012e:
            java.util.logging.Logger r0 = LOG
            java.lang.String r1 = "Failed to generate random tapes, signature verification will fail (but signature may actually be valid)"
            r0.fine(r1)
            return r15
        L_0x0136:
            int[] r0 = r10.inputShare
            int r1 = r8.stateSizeBits
            org.bouncycastle.pqc.crypto.picnic.Utils.zeroTrailingBits(r0, r1)
            int[] r0 = r11.inputShare
            int r1 = r8.stateSizeBits
            org.bouncycastle.pqc.crypto.picnic.Utils.zeroTrailingBits(r0, r1)
            int r0 = r13.length
            int r0 = r0 / 4
            int[] r4 = org.bouncycastle.util.Pack.littleEndianToInt((byte[]) r13, (int) r15, (int) r0)
            r0 = r18
            r1 = r20
            r2 = r21
            r3 = r27
            r5 = r26
            r6 = r22
            r0.mpc_LowMC_verify(r1, r2, r3, r4, r5, r6)
            return r16
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.pqc.crypto.picnic.PicnicEngine.verifyProof(org.bouncycastle.pqc.crypto.picnic.Signature$Proof, org.bouncycastle.pqc.crypto.picnic.View, org.bouncycastle.pqc.crypto.picnic.View, int, byte[], int, byte[], int[], org.bouncycastle.pqc.crypto.picnic.Tape):boolean");
    }

    /* access modifiers changed from: protected */
    public void xor_array(int[] iArr, int[] iArr2, int[] iArr3, int i) {
        for (int i2 = 0; i2 < this.stateSizeWords; i2++) {
            iArr[i2] = iArr2[i2] ^ iArr3[i2 + i];
        }
    }
}
