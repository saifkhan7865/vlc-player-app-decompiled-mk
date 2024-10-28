package org.bouncycastle.pqc.crypto.sphincsplus;

import java.security.SecureRandom;
import org.bouncycastle.crypto.KeyGenerationParameters;

public class SPHINCSPlusKeyGenerationParameters extends KeyGenerationParameters {
    private final SPHINCSPlusParameters parameters;

    public SPHINCSPlusKeyGenerationParameters(SecureRandom secureRandom, SPHINCSPlusParameters sPHINCSPlusParameters) {
        super(secureRandom, -1);
        this.parameters = sPHINCSPlusParameters;
    }

    /* access modifiers changed from: package-private */
    public SPHINCSPlusParameters getParameters() {
        return this.parameters;
    }
}
