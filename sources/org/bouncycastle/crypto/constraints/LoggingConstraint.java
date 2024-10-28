package org.bouncycastle.crypto.constraints;

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bouncycastle.crypto.CryptoServiceProperties;

public class LoggingConstraint extends ServicesConstraint {
    protected LoggingConstraint(Set<String> set) {
        super(set);
    }

    public void check(CryptoServiceProperties cryptoServiceProperties) {
        if (!isException(cryptoServiceProperties.getServiceName()) && LOG.isLoggable(Level.INFO)) {
            Logger logger = LOG;
            logger.info("service " + cryptoServiceProperties.getServiceName() + " referenced [" + cryptoServiceProperties.getServiceName() + ", " + cryptoServiceProperties.bitsOfSecurity() + ", " + cryptoServiceProperties.getPurpose());
        }
    }
}
