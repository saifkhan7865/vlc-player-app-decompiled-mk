package com.typesafe.config.impl;

import com.typesafe.config.ConfigOrigin;
import com.typesafe.config.ConfigRenderOptions;
import com.typesafe.config.ConfigValue;
import com.typesafe.config.ConfigValueType;
import java.io.ObjectStreamException;
import java.io.Serializable;

abstract class ConfigString extends AbstractConfigValue implements Serializable {
    private static final long serialVersionUID = 2;
    protected final String value;

    protected ConfigString(ConfigOrigin configOrigin, String str) {
        super(configOrigin);
        this.value = str;
    }

    static final class Quoted extends ConfigString {
        public /* bridge */ /* synthetic */ Object unwrapped() {
            return ConfigString.super.unwrapped();
        }

        Quoted(ConfigOrigin configOrigin, String str) {
            super(configOrigin, str);
        }

        /* access modifiers changed from: protected */
        public Quoted newCopy(ConfigOrigin configOrigin) {
            return new Quoted(configOrigin, this.value);
        }

        private Object writeReplace() throws ObjectStreamException {
            return new SerializedConfigValue((ConfigValue) this);
        }
    }

    static final class Unquoted extends ConfigString {
        public /* bridge */ /* synthetic */ Object unwrapped() {
            return ConfigString.super.unwrapped();
        }

        Unquoted(ConfigOrigin configOrigin, String str) {
            super(configOrigin, str);
        }

        /* access modifiers changed from: protected */
        public Unquoted newCopy(ConfigOrigin configOrigin) {
            return new Unquoted(configOrigin, this.value);
        }

        private Object writeReplace() throws ObjectStreamException {
            return new SerializedConfigValue((ConfigValue) this);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean wasQuoted() {
        return this instanceof Quoted;
    }

    public ConfigValueType valueType() {
        return ConfigValueType.STRING;
    }

    public String unwrapped() {
        return this.value;
    }

    /* access modifiers changed from: package-private */
    public String transformToString() {
        return this.value;
    }

    /* access modifiers changed from: protected */
    public void render(StringBuilder sb, int i, boolean z, ConfigRenderOptions configRenderOptions) {
        String str;
        if (configRenderOptions.getJson()) {
            str = ConfigImplUtil.renderJsonString(this.value);
        } else {
            str = ConfigImplUtil.renderStringUnquotedIfPossible(this.value);
        }
        sb.append(str);
    }
}
