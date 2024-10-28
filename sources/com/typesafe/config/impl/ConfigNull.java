package com.typesafe.config.impl;

import com.typesafe.config.ConfigOrigin;
import com.typesafe.config.ConfigRenderOptions;
import com.typesafe.config.ConfigValue;
import com.typesafe.config.ConfigValueType;
import java.io.ObjectStreamException;
import java.io.Serializable;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

final class ConfigNull extends AbstractConfigValue implements Serializable {
    private static final long serialVersionUID = 2;

    public Object unwrapped() {
        return null;
    }

    ConfigNull(ConfigOrigin configOrigin) {
        super(configOrigin);
    }

    public ConfigValueType valueType() {
        return ConfigValueType.NULL;
    }

    /* access modifiers changed from: package-private */
    public String transformToString() {
        return AbstractJsonLexerKt.NULL;
    }

    /* access modifiers changed from: protected */
    public void render(StringBuilder sb, int i, boolean z, ConfigRenderOptions configRenderOptions) {
        sb.append(AbstractJsonLexerKt.NULL);
    }

    /* access modifiers changed from: protected */
    public ConfigNull newCopy(ConfigOrigin configOrigin) {
        return new ConfigNull(configOrigin);
    }

    private Object writeReplace() throws ObjectStreamException {
        return new SerializedConfigValue((ConfigValue) this);
    }
}
