package org.bouncycastle.pqc.crypto.ntruprime;

import org.bouncycastle.crypto.params.AsymmetricKeyParameter;

public class SNTRUPrimeKeyParameters extends AsymmetricKeyParameter {
    private final SNTRUPrimeParameters params;

    public SNTRUPrimeKeyParameters(boolean z, SNTRUPrimeParameters sNTRUPrimeParameters) {
        super(z);
        this.params = sNTRUPrimeParameters;
    }

    public SNTRUPrimeParameters getParameters() {
        return this.params;
    }
}
