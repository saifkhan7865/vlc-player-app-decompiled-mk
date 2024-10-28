package org.bouncycastle.pqc.crypto.ntruprime;

import org.bouncycastle.crypto.params.AsymmetricKeyParameter;

public class NTRULPRimeKeyParameters extends AsymmetricKeyParameter {
    private final NTRULPRimeParameters params;

    public NTRULPRimeKeyParameters(boolean z, NTRULPRimeParameters nTRULPRimeParameters) {
        super(z);
        this.params = nTRULPRimeParameters;
    }

    public NTRULPRimeParameters getParameters() {
        return this.params;
    }
}
