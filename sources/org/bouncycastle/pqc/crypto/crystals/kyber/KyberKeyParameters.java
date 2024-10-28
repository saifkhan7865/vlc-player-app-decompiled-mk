package org.bouncycastle.pqc.crypto.crystals.kyber;

import org.bouncycastle.crypto.params.AsymmetricKeyParameter;

public class KyberKeyParameters extends AsymmetricKeyParameter {
    private KyberParameters params;

    public KyberKeyParameters(boolean z, KyberParameters kyberParameters) {
        super(z);
        this.params = kyberParameters;
    }

    public KyberParameters getParameters() {
        return this.params;
    }
}
