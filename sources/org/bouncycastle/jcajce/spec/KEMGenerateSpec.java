package org.bouncycastle.jcajce.spec;

import java.security.PublicKey;
import java.security.spec.AlgorithmParameterSpec;

public class KEMGenerateSpec implements AlgorithmParameterSpec {
    private final String keyAlgorithmName;
    private final int keySizeInBits;
    private final PublicKey publicKey;

    public KEMGenerateSpec(PublicKey publicKey2, String str) {
        this(publicKey2, str, 256);
    }

    public KEMGenerateSpec(PublicKey publicKey2, String str, int i) {
        this.publicKey = publicKey2;
        this.keyAlgorithmName = str;
        this.keySizeInBits = i;
    }

    public String getKeyAlgorithmName() {
        return this.keyAlgorithmName;
    }

    public int getKeySize() {
        return this.keySizeInBits;
    }

    public PublicKey getPublicKey() {
        return this.publicKey;
    }
}
