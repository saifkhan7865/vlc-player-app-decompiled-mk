package com.typesafe.config.impl;

import java.util.Collection;

enum ResolveStatus {
    UNRESOLVED,
    RESOLVED;

    static final ResolveStatus fromValues(Collection<? extends AbstractConfigValue> collection) {
        for (AbstractConfigValue resolveStatus : collection) {
            ResolveStatus resolveStatus2 = resolveStatus.resolveStatus();
            ResolveStatus resolveStatus3 = UNRESOLVED;
            if (resolveStatus2 == resolveStatus3) {
                return resolveStatus3;
            }
        }
        return RESOLVED;
    }

    static final ResolveStatus fromBoolean(boolean z) {
        return z ? RESOLVED : UNRESOLVED;
    }
}
