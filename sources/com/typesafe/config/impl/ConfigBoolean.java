package com.typesafe.config.impl;

import com.typesafe.config.ConfigOrigin;
import com.typesafe.config.ConfigValue;
import com.typesafe.config.ConfigValueType;
import java.io.ObjectStreamException;
import java.io.Serializable;

final class ConfigBoolean extends AbstractConfigValue implements Serializable {
    private static final long serialVersionUID = 2;
    private final boolean value;

    ConfigBoolean(ConfigOrigin configOrigin, boolean z) {
        super(configOrigin);
        this.value = z;
    }

    public ConfigValueType valueType() {
        return ConfigValueType.BOOLEAN;
    }

    public Boolean unwrapped() {
        return Boolean.valueOf(this.value);
    }

    /* access modifiers changed from: package-private */
    public String transformToString() {
        return this.value ? "true" : "false";
    }

    /* access modifiers changed from: protected */
    public ConfigBoolean newCopy(ConfigOrigin configOrigin) {
        return new ConfigBoolean(configOrigin, this.value);
    }

    private Object writeReplace() throws ObjectStreamException {
        return new SerializedConfigValue((ConfigValue) this);
    }
}
