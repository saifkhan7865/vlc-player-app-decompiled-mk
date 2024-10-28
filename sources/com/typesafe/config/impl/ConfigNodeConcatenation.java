package com.typesafe.config.impl;

import java.util.Collection;

final class ConfigNodeConcatenation extends ConfigNodeComplexValue {
    ConfigNodeConcatenation(Collection<AbstractConfigNode> collection) {
        super(collection);
    }

    /* access modifiers changed from: protected */
    public ConfigNodeConcatenation newNode(Collection<AbstractConfigNode> collection) {
        return new ConfigNodeConcatenation(collection);
    }
}
