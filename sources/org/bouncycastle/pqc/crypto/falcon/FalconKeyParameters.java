package org.bouncycastle.pqc.crypto.falcon;

import org.bouncycastle.crypto.params.AsymmetricKeyParameter;

public class FalconKeyParameters extends AsymmetricKeyParameter {
    private final FalconParameters params;

    public FalconKeyParameters(boolean z, FalconParameters falconParameters) {
        super(z);
        this.params = falconParameters;
    }

    public FalconParameters getParameters() {
        return this.params;
    }
}
