package com.typesafe.config;

public interface ConfigValue extends ConfigMergeable {

    /* renamed from: com.typesafe.config.ConfigValue$-CC  reason: invalid class name */
    public final /* synthetic */ class CC {
    }

    Config atKey(String str);

    Config atPath(String str);

    ConfigOrigin origin();

    String render();

    String render(ConfigRenderOptions configRenderOptions);

    Object unwrapped();

    ConfigValueType valueType();

    ConfigValue withFallback(ConfigMergeable configMergeable);

    ConfigValue withOrigin(ConfigOrigin configOrigin);
}
