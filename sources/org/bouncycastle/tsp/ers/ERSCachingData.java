package org.bouncycastle.tsp.ers;

import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.operator.DigestCalculator;
import org.bouncycastle.util.Arrays;

public abstract class ERSCachingData implements ERSData {
    private Map<CacheIndex, byte[]> preCalcs = new HashMap();

    private static class CacheIndex {
        final AlgorithmIdentifier algId;
        final byte[] chainHash;

        private CacheIndex(AlgorithmIdentifier algorithmIdentifier, byte[] bArr) {
            this.algId = algorithmIdentifier;
            this.chainHash = bArr;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof CacheIndex)) {
                return false;
            }
            CacheIndex cacheIndex = (CacheIndex) obj;
            return this.algId.equals(cacheIndex.algId) && Arrays.areEqual(this.chainHash, cacheIndex.chainHash);
        }

        public int hashCode() {
            return (this.algId.hashCode() * 31) + Arrays.hashCode(this.chainHash);
        }
    }

    /* access modifiers changed from: protected */
    public abstract byte[] calculateHash(DigestCalculator digestCalculator, byte[] bArr);

    public byte[] getHash(DigestCalculator digestCalculator, byte[] bArr) {
        CacheIndex cacheIndex = new CacheIndex(digestCalculator.getAlgorithmIdentifier(), bArr);
        if (this.preCalcs.containsKey(cacheIndex)) {
            return this.preCalcs.get(cacheIndex);
        }
        byte[] calculateHash = calculateHash(digestCalculator, bArr);
        this.preCalcs.put(cacheIndex, calculateHash);
        return calculateHash;
    }
}
