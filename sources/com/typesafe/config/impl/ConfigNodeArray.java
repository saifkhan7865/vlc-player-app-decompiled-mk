package com.typesafe.config.impl;

import java.util.Collection;

final class ConfigNodeArray extends ConfigNodeComplexValue {
    ConfigNodeArray(Collection<AbstractConfigNode> collection) {
        super(collection);
    }

    /* access modifiers changed from: protected */
    public ConfigNodeArray newNode(Collection<AbstractConfigNode> collection) {
        return new ConfigNodeArray(collection);
    }
}
