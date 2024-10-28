package org.bouncycastle.tsp.ers;

class IndexedHash {
    final byte[] digest;
    final int order;

    IndexedHash(int i, byte[] bArr) {
        this.order = i;
        this.digest = bArr;
    }
}
