package org.bouncycastle.jcajce.spec;

import java.security.spec.AlgorithmParameterSpec;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;

public class KEMParameterSpec extends KTSParameterSpec {
    public KEMParameterSpec(String str) {
        this(str, 256);
    }

    public KEMParameterSpec(String str, int i) {
        super(str, i, (AlgorithmParameterSpec) null, (AlgorithmIdentifier) null, (byte[]) null);
    }

    public int getKeySizeInBits() {
        return getKeySize();
    }
}
