package org.bouncycastle.pqc.crypto.crystals.dilithium;

import org.bouncycastle.util.Arrays;

class Packing {
    Packing() {
    }

    static byte[] packPublicKey(PolyVecK polyVecK, DilithiumEngine dilithiumEngine) {
        byte[] bArr = new byte[(dilithiumEngine.getCryptoPublicKeyBytes() - 32)];
        for (int i = 0; i < dilithiumEngine.getDilithiumK(); i++) {
            System.arraycopy(polyVecK.getVectorIndex(i).polyt1Pack(), 0, bArr, i * DilithiumEngine.DilithiumPolyT1PackedBytes, DilithiumEngine.DilithiumPolyT1PackedBytes);
        }
        return bArr;
    }

    static byte[][] packSecretKey(byte[] bArr, byte[] bArr2, byte[] bArr3, PolyVecK polyVecK, PolyVecL polyVecL, PolyVecK polyVecK2, DilithiumEngine dilithiumEngine) {
        byte[][] bArr4 = new byte[6][];
        bArr4[0] = bArr;
        bArr4[1] = bArr3;
        bArr4[2] = bArr2;
        bArr4[3] = new byte[(dilithiumEngine.getDilithiumL() * dilithiumEngine.getDilithiumPolyEtaPackedBytes())];
        for (int i = 0; i < dilithiumEngine.getDilithiumL(); i++) {
            polyVecL.getVectorIndex(i).polyEtaPack(bArr4[3], dilithiumEngine.getDilithiumPolyEtaPackedBytes() * i);
        }
        bArr4[4] = new byte[(dilithiumEngine.getDilithiumK() * dilithiumEngine.getDilithiumPolyEtaPackedBytes())];
        for (int i2 = 0; i2 < dilithiumEngine.getDilithiumK(); i2++) {
            polyVecK2.getVectorIndex(i2).polyEtaPack(bArr4[4], dilithiumEngine.getDilithiumPolyEtaPackedBytes() * i2);
        }
        bArr4[5] = new byte[(dilithiumEngine.getDilithiumK() * 416)];
        for (int i3 = 0; i3 < dilithiumEngine.getDilithiumK(); i3++) {
            polyVecK.getVectorIndex(i3).polyt0Pack(bArr4[5], i3 * 416);
        }
        return bArr4;
    }

    static byte[] packSignature(byte[] bArr, PolyVecL polyVecL, PolyVecK polyVecK, DilithiumEngine dilithiumEngine) {
        byte[] bArr2 = new byte[dilithiumEngine.getCryptoBytes()];
        System.arraycopy(bArr, 0, bArr2, 0, dilithiumEngine.getDilithiumCTilde());
        int dilithiumCTilde = dilithiumEngine.getDilithiumCTilde();
        for (int i = 0; i < dilithiumEngine.getDilithiumL(); i++) {
            System.arraycopy(polyVecL.getVectorIndex(i).zPack(), 0, bArr2, (dilithiumEngine.getDilithiumPolyZPackedBytes() * i) + dilithiumCTilde, dilithiumEngine.getDilithiumPolyZPackedBytes());
        }
        int dilithiumL = dilithiumCTilde + (dilithiumEngine.getDilithiumL() * dilithiumEngine.getDilithiumPolyZPackedBytes());
        for (int i2 = 0; i2 < dilithiumEngine.getDilithiumOmega() + dilithiumEngine.getDilithiumK(); i2++) {
            bArr2[dilithiumL + i2] = 0;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < dilithiumEngine.getDilithiumK(); i4++) {
            for (int i5 = 0; i5 < 256; i5++) {
                if (polyVecK.getVectorIndex(i4).getCoeffIndex(i5) != 0) {
                    bArr2[i3 + dilithiumL] = (byte) i5;
                    i3++;
                }
            }
            bArr2[dilithiumEngine.getDilithiumOmega() + dilithiumL + i4] = (byte) i3;
        }
        return bArr2;
    }

    static PolyVecK unpackPublicKey(PolyVecK polyVecK, byte[] bArr, DilithiumEngine dilithiumEngine) {
        int i = 0;
        while (i < dilithiumEngine.getDilithiumK()) {
            Poly vectorIndex = polyVecK.getVectorIndex(i);
            int i2 = i * DilithiumEngine.DilithiumPolyT1PackedBytes;
            i++;
            vectorIndex.polyt1Unpack(Arrays.copyOfRange(bArr, i2, (i * DilithiumEngine.DilithiumPolyT1PackedBytes) + 32));
        }
        return polyVecK;
    }

    static void unpackSecretKey(PolyVecK polyVecK, PolyVecL polyVecL, PolyVecK polyVecK2, byte[] bArr, byte[] bArr2, byte[] bArr3, DilithiumEngine dilithiumEngine) {
        for (int i = 0; i < dilithiumEngine.getDilithiumL(); i++) {
            polyVecL.getVectorIndex(i).polyEtaUnpack(bArr2, dilithiumEngine.getDilithiumPolyEtaPackedBytes() * i);
        }
        for (int i2 = 0; i2 < dilithiumEngine.getDilithiumK(); i2++) {
            polyVecK2.getVectorIndex(i2).polyEtaUnpack(bArr3, dilithiumEngine.getDilithiumPolyEtaPackedBytes() * i2);
        }
        for (int i3 = 0; i3 < dilithiumEngine.getDilithiumK(); i3++) {
            polyVecK.getVectorIndex(i3).polyt0Unpack(bArr, i3 * 416);
        }
    }

    /* JADX WARNING: type inference failed for: r9v0, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r7v7, types: [byte] */
    /* JADX WARNING: type inference failed for: r3v7, types: [byte] */
    /* JADX WARNING: type inference failed for: r3v12, types: [byte] */
    /* JADX WARNING: type inference failed for: r5v5, types: [byte] */
    /* JADX WARNING: type inference failed for: r2v10, types: [byte] */
    /* JADX WARNING: type inference failed for: r6v1, types: [byte] */
    /* JADX WARNING: type inference failed for: r6v3, types: [byte] */
    /* JADX WARNING: type inference failed for: r5v10, types: [byte] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=int, for r2v10, types: [byte] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static boolean unpackSignature(org.bouncycastle.pqc.crypto.crystals.dilithium.PolyVecL r7, org.bouncycastle.pqc.crypto.crystals.dilithium.PolyVecK r8, byte[] r9, org.bouncycastle.pqc.crypto.crystals.dilithium.DilithiumEngine r10) {
        /*
            int r0 = r10.getDilithiumCTilde()
            r1 = 0
            r2 = 0
        L_0x0006:
            int r3 = r10.getDilithiumL()
            if (r2 >= r3) goto L_0x0028
            org.bouncycastle.pqc.crypto.crystals.dilithium.Poly r3 = r7.getVectorIndex(r2)
            int r4 = r10.getDilithiumPolyZPackedBytes()
            int r4 = r4 * r2
            int r4 = r4 + r0
            int r2 = r2 + 1
            int r5 = r10.getDilithiumPolyZPackedBytes()
            int r5 = r5 * r2
            int r5 = r5 + r0
            byte[] r4 = org.bouncycastle.util.Arrays.copyOfRange((byte[]) r9, (int) r4, (int) r5)
            r3.zUnpack(r4)
            goto L_0x0006
        L_0x0028:
            int r7 = r10.getDilithiumL()
            int r2 = r10.getDilithiumPolyZPackedBytes()
            int r7 = r7 * r2
            int r0 = r0 + r7
            r7 = 0
            r2 = 0
        L_0x0035:
            int r3 = r10.getDilithiumK()
            r4 = 1
            if (r7 >= r3) goto L_0x00a1
            r3 = 0
        L_0x003d:
            r5 = 256(0x100, float:3.59E-43)
            if (r3 >= r5) goto L_0x004b
            org.bouncycastle.pqc.crypto.crystals.dilithium.Poly r5 = r8.getVectorIndex(r7)
            r5.setCoeffIndex(r3, r1)
            int r3 = r3 + 1
            goto L_0x003d
        L_0x004b:
            int r3 = r10.getDilithiumOmega()
            int r3 = r3 + r0
            int r3 = r3 + r7
            byte r3 = r9[r3]
            r3 = r3 & 255(0xff, float:3.57E-43)
            if (r3 < r2) goto L_0x00a0
            int r3 = r10.getDilithiumOmega()
            int r3 = r3 + r0
            int r3 = r3 + r7
            byte r3 = r9[r3]
            r3 = r3 & 255(0xff, float:3.57E-43)
            int r5 = r10.getDilithiumOmega()
            if (r3 <= r5) goto L_0x0068
            goto L_0x00a0
        L_0x0068:
            r3 = r2
        L_0x0069:
            int r5 = r10.getDilithiumOmega()
            int r5 = r5 + r0
            int r5 = r5 + r7
            byte r5 = r9[r5]
            r5 = r5 & 255(0xff, float:3.57E-43)
            if (r3 >= r5) goto L_0x0095
            if (r3 <= r2) goto L_0x0085
            int r5 = r0 + r3
            byte r6 = r9[r5]
            r6 = r6 & 255(0xff, float:3.57E-43)
            int r5 = r5 - r4
            byte r5 = r9[r5]
            r5 = r5 & 255(0xff, float:3.57E-43)
            if (r6 > r5) goto L_0x0085
            return r1
        L_0x0085:
            org.bouncycastle.pqc.crypto.crystals.dilithium.Poly r5 = r8.getVectorIndex(r7)
            int r6 = r0 + r3
            byte r6 = r9[r6]
            r6 = r6 & 255(0xff, float:3.57E-43)
            r5.setCoeffIndex(r6, r4)
            int r3 = r3 + 1
            goto L_0x0069
        L_0x0095:
            int r2 = r10.getDilithiumOmega()
            int r2 = r2 + r0
            int r2 = r2 + r7
            byte r2 = r9[r2]
            int r7 = r7 + 1
            goto L_0x0035
        L_0x00a0:
            return r1
        L_0x00a1:
            int r7 = r10.getDilithiumOmega()
            if (r2 >= r7) goto L_0x00b3
            int r7 = r0 + r2
            byte r7 = r9[r7]
            r7 = r7 & 255(0xff, float:3.57E-43)
            if (r7 == 0) goto L_0x00b0
            return r1
        L_0x00b0:
            int r2 = r2 + 1
            goto L_0x00a1
        L_0x00b3:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.pqc.crypto.crystals.dilithium.Packing.unpackSignature(org.bouncycastle.pqc.crypto.crystals.dilithium.PolyVecL, org.bouncycastle.pqc.crypto.crystals.dilithium.PolyVecK, byte[], org.bouncycastle.pqc.crypto.crystals.dilithium.DilithiumEngine):boolean");
    }
}
