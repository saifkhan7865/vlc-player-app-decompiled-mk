package org.bouncycastle.tsp.ers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import org.bouncycastle.operator.DigestCalculator;

public class ERSFileData extends ERSCachingData {
    private final File content;

    public ERSFileData(File file) throws FileNotFoundException {
        if (file.isDirectory()) {
            throw new IllegalArgumentException("directory not allowed as ERSFileData");
        } else if (!file.exists()) {
            throw new FileNotFoundException(file.getAbsolutePath() + " does not exist");
        } else if (file.canRead()) {
            this.content = file;
        } else {
            throw new FileNotFoundException(file.getAbsolutePath() + " is not readable");
        }
    }

    /* access modifiers changed from: protected */
    public byte[] calculateHash(DigestCalculator digestCalculator, byte[] bArr) {
        try {
            FileInputStream fileInputStream = new FileInputStream(this.content);
            byte[] calculateDigest = ERSUtil.calculateDigest(digestCalculator, (InputStream) fileInputStream);
            fileInputStream.close();
            return bArr != null ? ERSUtil.concatPreviousHashes(digestCalculator, bArr, calculateDigest) : calculateDigest;
        } catch (IOException unused) {
            throw new IllegalStateException("unable to process " + this.content.getAbsolutePath());
        }
    }
}
