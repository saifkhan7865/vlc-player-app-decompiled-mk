package org.bouncycastle.pqc.crypto.sphincsplus;

import java.util.LinkedList;
import org.bouncycastle.util.Arrays;

class Fors {
    SPHINCSPlusEngine engine;

    public Fors(SPHINCSPlusEngine sPHINCSPlusEngine) {
        this.engine = sPHINCSPlusEngine;
    }

    static int[] message_to_idxs(byte[] bArr, int i, int i2) {
        int[] iArr = new int[i];
        int i3 = 0;
        for (int i4 = 0; i4 < i; i4++) {
            iArr[i4] = 0;
            for (int i5 = 0; i5 < i2; i5++) {
                iArr[i4] = iArr[i4] ^ (((bArr[i3 >> 3] >> (i3 & 7)) & 1) << i5);
                i3++;
            }
        }
        return iArr;
    }

    public byte[] pkFromSig(SIG_FORS[] sig_forsArr, byte[] bArr, byte[] bArr2, ADRS adrs) {
        byte[] bArr3 = bArr2;
        ADRS adrs2 = adrs;
        int i = 2;
        byte[][] bArr4 = new byte[2][];
        byte[][] bArr5 = new byte[this.engine.K][];
        int i2 = this.engine.T;
        int[] message_to_idxs = message_to_idxs(bArr, this.engine.K, this.engine.A);
        int i3 = 0;
        while (i3 < this.engine.K) {
            int i4 = message_to_idxs[i3];
            byte[] sk = sig_forsArr[i3].getSK();
            adrs2.setTreeHeight(0);
            int i5 = (i3 * i2) + i4;
            adrs2.setTreeIndex(i5);
            bArr4[0] = this.engine.F(bArr3, adrs2, sk);
            byte[][] authPath = sig_forsArr[i3].getAuthPath();
            adrs2.setTreeIndex(i5);
            int i6 = 0;
            while (i6 < this.engine.A) {
                int i7 = i6 + 1;
                adrs2.setTreeHeight(i7);
                if ((i4 / (1 << i6)) % i == 0) {
                    adrs2.setTreeIndex(adrs.getTreeIndex() / i);
                    bArr4[1] = this.engine.H(bArr3, adrs2, bArr4[0], authPath[i6]);
                } else {
                    adrs2.setTreeIndex((adrs.getTreeIndex() - 1) / 2);
                    bArr4[1] = this.engine.H(bArr3, adrs2, authPath[i6], bArr4[0]);
                }
                bArr4[0] = bArr4[1];
                i6 = i7;
                i = 2;
            }
            bArr5[i3] = bArr4[0];
            i3++;
            i = 2;
        }
        ADRS adrs3 = new ADRS(adrs2);
        adrs3.setType(4);
        adrs3.setKeyPairAddress(adrs.getKeyPairAddress());
        return this.engine.T_l(bArr3, adrs3, Arrays.concatenate(bArr5));
    }

    public SIG_FORS[] sign(byte[] bArr, byte[] bArr2, byte[] bArr3, ADRS adrs) {
        Fors fors = this;
        ADRS adrs2 = new ADRS(adrs);
        int[] message_to_idxs = message_to_idxs(bArr, fors.engine.K, fors.engine.A);
        SIG_FORS[] sig_forsArr = new SIG_FORS[fors.engine.K];
        int i = fors.engine.T;
        int i2 = 0;
        int i3 = 0;
        while (i3 < fors.engine.K) {
            int i4 = message_to_idxs[i3];
            adrs2.setType(6);
            adrs2.setKeyPairAddress(adrs.getKeyPairAddress());
            adrs2.setTreeHeight(i2);
            int i5 = i3 * i;
            adrs2.setTreeIndex(i5 + i4);
            byte[] PRF = fors.engine.PRF(bArr3, bArr2, adrs2);
            adrs2.changeType(3);
            byte[][] bArr4 = new byte[fors.engine.A][];
            int i6 = 0;
            while (i6 < fors.engine.A) {
                int i7 = 1 << i6;
                int i8 = i6;
                byte[][] bArr5 = bArr4;
                bArr5[i8] = treehash(bArr2, i5 + (((i4 / i7) ^ 1) * i7), i8, bArr3, adrs2);
                i6 = i8 + 1;
                byte[] bArr6 = bArr2;
                PRF = PRF;
                bArr4 = bArr5;
                fors = this;
            }
            sig_forsArr[i3] = new SIG_FORS(PRF, bArr4);
            i3++;
            i2 = 0;
            fors = this;
        }
        return sig_forsArr;
    }

    /* access modifiers changed from: package-private */
    public byte[] treehash(byte[] bArr, int i, int i2, byte[] bArr2, ADRS adrs) {
        LinkedList linkedList = new LinkedList();
        int i3 = 1 << i2;
        if (i % i3 != 0) {
            return null;
        }
        ADRS adrs2 = new ADRS(adrs);
        for (int i4 = 0; i4 < i3; i4++) {
            adrs2.setType(6);
            adrs2.setKeyPairAddress(adrs.getKeyPairAddress());
            adrs2.setTreeHeight(0);
            adrs2.setTreeIndex(i + i4);
            byte[] PRF = this.engine.PRF(bArr2, bArr, adrs2);
            adrs2.changeType(3);
            byte[] F = this.engine.F(bArr2, adrs2, PRF);
            adrs2.setTreeHeight(1);
            while (!linkedList.isEmpty() && ((NodeEntry) linkedList.get(0)).nodeHeight == adrs2.getTreeHeight()) {
                adrs2.setTreeIndex((adrs2.getTreeIndex() - 1) / 2);
                F = this.engine.H(bArr2, adrs2, ((NodeEntry) linkedList.remove(0)).nodeValue, F);
                adrs2.setTreeHeight(adrs2.getTreeHeight() + 1);
            }
            linkedList.add(0, new NodeEntry(F, adrs2.getTreeHeight()));
        }
        return ((NodeEntry) linkedList.get(0)).nodeValue;
    }
}
