package org.bouncycastle.pqc.crypto.crystals.kyber;

final class CBD {
    CBD() {
    }

    private static long convertByteTo24BitUnsignedInt(byte[] bArr, int i) {
        return (((long) (bArr[i + 2] & 255)) << 16) | ((long) (bArr[i] & 255)) | (((long) (bArr[i + 1] & 255)) << 8);
    }

    private static long convertByteTo32BitUnsignedInt(byte[] bArr, int i) {
        return (((long) (bArr[i + 3] & 255)) << 24) | ((long) (bArr[i] & 255)) | (((long) (bArr[i + 1] & 255)) << 8) | (((long) (bArr[i + 2] & 255)) << 16);
    }

    public static void kyberCBD(Poly poly, byte[] bArr, int i) {
        if (i != 3) {
            for (int i2 = 0; i2 < 32; i2++) {
                long convertByteTo32BitUnsignedInt = convertByteTo32BitUnsignedInt(bArr, i2 * 4);
                long j = (convertByteTo32BitUnsignedInt & 1431655765) + ((convertByteTo32BitUnsignedInt >> 1) & 1431655765);
                for (int i3 = 0; i3 < 8; i3++) {
                    int i4 = i3 * 4;
                    poly.setCoeffIndex((i2 * 8) + i3, (short) (((short) ((int) ((j >> i4) & 3))) - ((short) ((int) (3 & (j >> (i4 + i)))))));
                }
            }
            return;
        }
        for (int i5 = 0; i5 < 64; i5++) {
            long convertByteTo24BitUnsignedInt = convertByteTo24BitUnsignedInt(bArr, i5 * 3);
            long j2 = (convertByteTo24BitUnsignedInt & 2396745) + ((convertByteTo24BitUnsignedInt >> 1) & 2396745) + ((convertByteTo24BitUnsignedInt >> 2) & 2396745);
            for (int i6 = 0; i6 < 4; i6++) {
                int i7 = i6 * 6;
                poly.setCoeffIndex((i5 * 4) + i6, (short) (((short) ((int) ((j2 >> i7) & 7))) - ((short) ((int) (7 & (j2 >> (i7 + 3)))))));
            }
        }
    }
}
