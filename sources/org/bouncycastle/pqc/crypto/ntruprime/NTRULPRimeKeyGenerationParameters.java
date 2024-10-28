package org.bouncycastle.pqc.crypto.ntruprime;

import java.security.SecureRandom;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.KeyGenerationParameters;

public class NTRULPRimeKeyGenerationParameters extends KeyGenerationParameters {
    private final NTRULPRimeParameters ntrulprParams;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public NTRULPRimeKeyGenerationParameters(SecureRandom secureRandom, NTRULPRimeParameters nTRULPRimeParameters) {
        super(secureRandom == null ? CryptoServicesRegistrar.getSecureRandom() : secureRandom, 256);
        this.ntrulprParams = nTRULPRimeParameters;
    }

    public NTRULPRimeParameters getNtrulprParams() {
        return this.ntrulprParams;
    }
}
