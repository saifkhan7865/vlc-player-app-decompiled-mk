package org.bouncycastle.jcajce.provider.drbg;

import java.security.SecureRandom;
import org.bouncycastle.crypto.prng.EntropySource;
import org.bouncycastle.crypto.prng.EntropySourceProvider;

class IncrementalEntropySourceProvider implements EntropySourceProvider {
    /* access modifiers changed from: private */
    public final boolean predictionResistant;
    /* access modifiers changed from: private */
    public final SecureRandom random;

    public IncrementalEntropySourceProvider(SecureRandom secureRandom, boolean z) {
        this.random = secureRandom;
        this.predictionResistant = z;
    }

    /* access modifiers changed from: private */
    public static void sleep(long j) throws InterruptedException {
        if (j != 0) {
            Thread.sleep(j);
        }
    }

    public EntropySource get(int i) {
        return new IncrementalEntropySource(i) {
            final int numBytes;
            final /* synthetic */ int val$bitsRequired;

            {
                this.val$bitsRequired = r2;
                this.numBytes = (r2 + 7) / 8;
            }

            public int entropySize() {
                return this.val$bitsRequired;
            }

            public byte[] getEntropy() {
                try {
                    return getEntropy(0);
                } catch (InterruptedException unused) {
                    Thread.currentThread().interrupt();
                    throw new IllegalStateException("initial entropy fetch interrupted");
                }
            }

            public byte[] getEntropy(long j) throws InterruptedException {
                int i;
                int i2 = this.numBytes;
                byte[] bArr = new byte[i2];
                int i3 = 0;
                while (true) {
                    i = this.numBytes;
                    if (i3 >= i / 8) {
                        break;
                    }
                    IncrementalEntropySourceProvider.sleep(j);
                    byte[] generateSeed = IncrementalEntropySourceProvider.this.random.generateSeed(8);
                    System.arraycopy(generateSeed, 0, bArr, i3 * 8, generateSeed.length);
                    i3++;
                }
                int i4 = i - ((i / 8) * 8);
                if (i4 != 0) {
                    IncrementalEntropySourceProvider.sleep(j);
                    byte[] generateSeed2 = IncrementalEntropySourceProvider.this.random.generateSeed(i4);
                    System.arraycopy(generateSeed2, 0, bArr, i2 - generateSeed2.length, generateSeed2.length);
                }
                return bArr;
            }

            public boolean isPredictionResistant() {
                return IncrementalEntropySourceProvider.this.predictionResistant;
            }
        };
    }
}
