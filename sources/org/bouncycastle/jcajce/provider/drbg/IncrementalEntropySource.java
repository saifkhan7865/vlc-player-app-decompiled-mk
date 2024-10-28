package org.bouncycastle.jcajce.provider.drbg;

import org.bouncycastle.crypto.prng.EntropySource;

interface IncrementalEntropySource extends EntropySource {
    byte[] getEntropy(long j) throws InterruptedException;
}
