package org.bouncycastle.pqc.crypto.falcon;

class FalconConversions {
    FalconConversions() {
    }

    private int toUnsignedInt(byte b) {
        return b & 255;
    }

    private long toUnsignedLong(byte b) {
        return ((long) b) & 255;
    }

    /* access modifiers changed from: package-private */
    public int bytes_to_int(byte[] bArr, int i) {
        return (toUnsignedInt(bArr[i + 3]) << 24) | toUnsignedInt(bArr[i]) | (toUnsignedInt(bArr[i + 1]) << 8) | (toUnsignedInt(bArr[i + 2]) << 16);
    }

    /* access modifiers changed from: package-private */
    public int[] bytes_to_int_array(byte[] bArr, int i, int i2) {
        int[] iArr = new int[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            iArr[i3] = bytes_to_int(bArr, (i3 * 4) + i);
        }
        return iArr;
    }

    /* access modifiers changed from: package-private */
    public long bytes_to_long(byte[] bArr, int i) {
        return (toUnsignedLong(bArr[i + 7]) << 56) | toUnsignedLong(bArr[i]) | (toUnsignedLong(bArr[i + 1]) << 8) | (toUnsignedLong(bArr[i + 2]) << 16) | (toUnsignedLong(bArr[i + 3]) << 24) | (toUnsignedLong(bArr[i + 4]) << 32) | (toUnsignedLong(bArr[i + 5]) << 40) | (toUnsignedLong(bArr[i + 6]) << 48);
    }

    /* access modifiers changed from: package-private */
    public byte[] int_to_bytes(int i) {
        return new byte[]{(byte) i, (byte) (i >>> 8), (byte) (i >>> 16), (byte) (i >>> 24)};
    }

    /* access modifiers changed from: package-private */
    public byte[] long_to_bytes(long j) {
        return new byte[]{(byte) ((int) j), (byte) ((int) (j >>> 8)), (byte) ((int) (j >>> 16)), (byte) ((int) (j >>> 24)), (byte) ((int) (j >>> 32)), (byte) ((int) (j >>> 40)), (byte) ((int) (j >>> 48)), (byte) ((int) (j >>> 56))};
    }
}
