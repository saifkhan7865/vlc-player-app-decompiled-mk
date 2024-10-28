package org.bouncycastle.tsp.ers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import org.bouncycastle.operator.DigestCalculator;

public class ERSInputStreamData extends ERSCachingData {
    private final InputStream content;

    public ERSInputStreamData(File file) throws FileNotFoundException {
        if (!file.isDirectory()) {
            this.content = new FileInputStream(file);
            return;
        }
        throw new IllegalArgumentException("directory not allowed");
    }

    public ERSInputStreamData(InputStream inputStream) {
        this.content = inputStream;
    }

    /* access modifiers changed from: protected */
    public byte[] calculateHash(DigestCalculator digestCalculator, byte[] bArr) {
        byte[] calculateDigest = ERSUtil.calculateDigest(digestCalculator, this.content);
        return bArr != null ? ERSUtil.concatPreviousHashes(digestCalculator, bArr, calculateDigest) : calculateDigest;
    }
}
