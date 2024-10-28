package com.typesafe.config.impl;

import com.typesafe.config.ConfigOrigin;
import com.typesafe.config.ConfigValue;
import com.typesafe.config.ConfigValueType;
import java.io.ObjectStreamException;
import java.io.Serializable;

final class ConfigInt extends ConfigNumber implements Serializable {
    private static final long serialVersionUID = 2;
    private final int value;

    ConfigInt(ConfigOrigin configOrigin, int i, String str) {
        super(configOrigin, str);
        this.value = i;
    }

    public ConfigValueType valueType() {
        return ConfigValueType.NUMBER;
    }

    public Integer unwrapped() {
        return Integer.valueOf(this.value);
    }

    /* access modifiers changed from: package-private */
    public String transformToString() {
        String transformToString = super.transformToString();
        return transformToString == null ? Integer.toString(this.value) : transformToString;
    }

    /* access modifiers changed from: protected */
    public long longValue() {
        return (long) this.value;
    }

    /* access modifiers changed from: protected */
    public double doubleValue() {
        return (double) this.value;
    }

    /* access modifiers changed from: protected */
    public ConfigInt newCopy(ConfigOrigin configOrigin) {
        return new ConfigInt(configOrigin, this.value, this.originalText);
    }

    private Object writeReplace() throws ObjectStreamException {
        return new SerializedConfigValue((ConfigValue) this);
    }
}
