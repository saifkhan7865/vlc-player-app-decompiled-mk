package org.bouncycastle.tsp.ers;

import org.bouncycastle.operator.DigestCalculator;

public interface ERSData {
    byte[] getHash(DigestCalculator digestCalculator, byte[] bArr);
}
