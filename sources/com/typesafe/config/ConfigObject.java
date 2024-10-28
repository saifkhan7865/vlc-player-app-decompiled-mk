package com.typesafe.config;

import java.util.Map;

public interface ConfigObject extends ConfigValue, Map<String, ConfigValue> {

    /* renamed from: com.typesafe.config.ConfigObject$-CC  reason: invalid class name */
    public final /* synthetic */ class CC {
    }

    ConfigValue get(Object obj);

    Config toConfig();

    Map<String, Object> unwrapped();

    ConfigObject withFallback(ConfigMergeable configMergeable);

    ConfigObject withOnlyKey(String str);

    ConfigObject withOrigin(ConfigOrigin configOrigin);

    ConfigObject withValue(String str, ConfigValue configValue);

    ConfigObject withoutKey(String str);
}
