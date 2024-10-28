package org.bouncycastle.crypto.constraints;

import java.util.Collections;
import java.util.Set;
import org.bouncycastle.crypto.CryptoServiceConstraintsException;
import org.bouncycastle.crypto.CryptoServiceProperties;

public class BitsOfSecurityConstraint extends ServicesConstraint {
    private final int requiredBitsOfSecurity;

    public BitsOfSecurityConstraint(int i) {
        super(Collections.EMPTY_SET);
        this.requiredBitsOfSecurity = i;
    }

    public BitsOfSecurityConstraint(int i, Set<String> set) {
        super(set);
        this.requiredBitsOfSecurity = i;
    }

    public void check(CryptoServiceProperties cryptoServiceProperties) {
        if (!isException(cryptoServiceProperties.getServiceName()) && cryptoServiceProperties.bitsOfSecurity() < this.requiredBitsOfSecurity) {
            throw new CryptoServiceConstraintsException("service does not provide " + this.requiredBitsOfSecurity + " bits of security only " + cryptoServiceProperties.bitsOfSecurity());
        }
    }
}
