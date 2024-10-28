package org.bouncycastle.tsp.ers;

import org.bouncycastle.operator.DigestCalculator;

public class ERSByteData extends ERSCachingData {
    private final byte[] content;

    public ERSByteData(byte[] bArr) {
        this.content = bArr;
    }

    /* access modifiers changed from: protected */
    public byte[] calculateHash(DigestCalculator digestCalculator, byte[] bArr) {
        byte[] calculateDigest = ERSUtil.calculateDigest(digestCalculator, this.content);
        return bArr != null ? ERSUtil.concatPreviousHashes(digestCalculator, bArr, calculateDigest) : calculateDigest;
    }
}
