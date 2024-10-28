package com.typesafe.config.impl;

import com.typesafe.config.ConfigOrigin;
import com.typesafe.config.ConfigValue;
import com.typesafe.config.ConfigValueType;
import java.io.ObjectStreamException;
import java.io.Serializable;

final class ConfigLong extends ConfigNumber implements Serializable {
    private static final long serialVersionUID = 2;
    private final long value;

    ConfigLong(ConfigOrigin configOrigin, long j, String str) {
        super(configOrigin, str);
        this.value = j;
    }

    public ConfigValueType valueType() {
        return ConfigValueType.NUMBER;
    }

    public Long unwrapped() {
        return Long.valueOf(this.value);
    }

    /* access modifiers changed from: package-private */
    public String transformToString() {
        String transformToString = super.transformToString();
        return transformToString == null ? Long.toString(this.value) : transformToString;
    }

    /* access modifiers changed from: protected */
    public long longValue() {
        return this.value;
    }

    /* access modifiers changed from: protected */
    public double doubleValue() {
        return (double) this.value;
    }

    /* access modifiers changed from: protected */
    public ConfigLong newCopy(ConfigOrigin configOrigin) {
        return new ConfigLong(configOrigin, this.value, this.originalText);
    }

    private Object writeReplace() throws ObjectStreamException {
        return new SerializedConfigValue((ConfigValue) this);
    }
}
