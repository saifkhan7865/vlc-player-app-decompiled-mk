package org.bouncycastle.jcajce.provider.digest;

import org.bouncycastle.asn1.misc.MiscObjectIdentifiers;
import org.bouncycastle.crypto.digests.Blake3Digest;
import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;

public class Blake3 {

    public static class Blake3_256 extends BCMessageDigest implements Cloneable {
        public Blake3_256() {
            super(new Blake3Digest(256));
        }

        public Object clone() throws CloneNotSupportedException {
            Blake3_256 blake3_256 = (Blake3_256) super.clone();
            blake3_256.digest = new Blake3Digest((Blake3Digest) this.digest);
            return blake3_256;
        }
    }

    public static class Mappings extends DigestAlgorithmProvider {
        private static final String PREFIX = Blake3.class.getName();

        public void configure(ConfigurableProvider configurableProvider) {
            configurableProvider.addAlgorithm("MessageDigest.BLAKE3-256", PREFIX + "$Blake3_256");
            StringBuilder sb = new StringBuilder("Alg.Alias.MessageDigest.");
            sb.append(MiscObjectIdentifiers.blake3_256);
            configurableProvider.addAlgorithm(sb.toString(), "BLAKE3-256");
        }
    }

    private Blake3() {
    }
}
