package org.bouncycastle.crypto.constraints;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;
import org.bouncycastle.crypto.CryptoServicesConstraints;
import org.bouncycastle.util.Strings;

public abstract class ServicesConstraint implements CryptoServicesConstraints {
    protected static final Logger LOG = Logger.getLogger(ServicesConstraint.class.getName());
    private final Set<String> exceptions;

    protected ServicesConstraint(Set<String> set) {
        if (set.isEmpty()) {
            this.exceptions = Collections.EMPTY_SET;
            return;
        }
        this.exceptions = new HashSet(set.size());
        for (String obj : set) {
            this.exceptions.add(Strings.toUpperCase(obj.toString()));
        }
        Utils.addAliases(this.exceptions);
    }

    /* access modifiers changed from: protected */
    public boolean isException(String str) {
        if (this.exceptions.isEmpty()) {
            return false;
        }
        return this.exceptions.contains(Strings.toUpperCase(str));
    }
}
