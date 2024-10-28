package org.bouncycastle.cert.dane;

import java.io.OutputStream;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.operator.DigestCalculator;

public class TruncatingDigestCalculator implements DigestCalculator {
    private final DigestCalculator baseCalculator;
    private final int length;

    public TruncatingDigestCalculator(DigestCalculator digestCalculator) {
        this(digestCalculator, 28);
    }

    public TruncatingDigestCalculator(DigestCalculator digestCalculator, int i) {
        this.baseCalculator = digestCalculator;
        this.length = i;
    }

    public AlgorithmIdentifier getAlgorithmIdentifier() {
        return this.baseCalculator.getAlgorithmIdentifier();
    }

    public byte[] getDigest() {
        int i = this.length;
        byte[] bArr = new byte[i];
        System.arraycopy(this.baseCalculator.getDigest(), 0, bArr, 0, i);
        return bArr;
    }

    public OutputStream getOutputStream() {
        return this.baseCalculator.getOutputStream();
    }
}
