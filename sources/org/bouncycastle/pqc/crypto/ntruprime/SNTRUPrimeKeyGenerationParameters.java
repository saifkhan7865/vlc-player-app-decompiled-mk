package org.bouncycastle.pqc.crypto.ntruprime;

import java.security.SecureRandom;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.KeyGenerationParameters;

public class SNTRUPrimeKeyGenerationParameters extends KeyGenerationParameters {
    private final SNTRUPrimeParameters sntrupParams;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SNTRUPrimeKeyGenerationParameters(SecureRandom secureRandom, SNTRUPrimeParameters sNTRUPrimeParameters) {
        super(secureRandom == null ? CryptoServicesRegistrar.getSecureRandom() : secureRandom, 256);
        this.sntrupParams = sNTRUPrimeParameters;
    }

    public SNTRUPrimeParameters getSntrupParams() {
        return this.sntrupParams;
    }
}
