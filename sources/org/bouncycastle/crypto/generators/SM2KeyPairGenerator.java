package org.bouncycastle.crypto.generators;

import java.math.BigInteger;
import org.bouncycastle.util.BigIntegers;

public class SM2KeyPairGenerator extends ECKeyPairGenerator {
    public SM2KeyPairGenerator() {
        super("SM2KeyGen");
    }

    /* access modifiers changed from: protected */
    public boolean isOutOfRangeD(BigInteger bigInteger, BigInteger bigInteger2) {
        return bigInteger.compareTo(ONE) < 0 || bigInteger.compareTo(bigInteger2.subtract(BigIntegers.ONE)) >= 0;
    }
}
