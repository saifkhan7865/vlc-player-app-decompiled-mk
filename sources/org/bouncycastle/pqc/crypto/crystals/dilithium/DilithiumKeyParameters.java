package org.bouncycastle.pqc.crypto.crystals.dilithium;

import org.bouncycastle.crypto.params.AsymmetricKeyParameter;

public class DilithiumKeyParameters extends AsymmetricKeyParameter {
    private final DilithiumParameters params;

    public DilithiumKeyParameters(boolean z, DilithiumParameters dilithiumParameters) {
        super(z);
        this.params = dilithiumParameters;
    }

    public DilithiumParameters getParameters() {
        return this.params;
    }
}
