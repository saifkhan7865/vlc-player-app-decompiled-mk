package com.typesafe.config.impl;

import com.typesafe.config.ConfigOrigin;
import com.typesafe.config.ConfigValue;
import com.typesafe.config.ConfigValueType;
import java.io.ObjectStreamException;
import java.io.Serializable;

final class ConfigDouble extends ConfigNumber implements Serializable {
    private static final long serialVersionUID = 2;
    private final double value;

    ConfigDouble(ConfigOrigin configOrigin, double d, String str) {
        super(configOrigin, str);
        this.value = d;
    }

    public ConfigValueType valueType() {
        return ConfigValueType.NUMBER;
    }

    public Double unwrapped() {
        return Double.valueOf(this.value);
    }

    /* access modifiers changed from: package-private */
    public String transformToString() {
        String transformToString = super.transformToString();
        return transformToString == null ? Double.toString(this.value) : transformToString;
    }

    /* access modifiers changed from: protected */
    public long longValue() {
        return (long) this.value;
    }

    /* access modifiers changed from: protected */
    public double doubleValue() {
        return this.value;
    }

    /* access modifiers changed from: protected */
    public ConfigDouble newCopy(ConfigOrigin configOrigin) {
        return new ConfigDouble(configOrigin, this.value, this.originalText);
    }

    private Object writeReplace() throws ObjectStreamException {
        return new SerializedConfigValue((ConfigValue) this);
    }
}
