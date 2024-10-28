package com.typesafe.config;

import java.util.List;

public interface ConfigList extends List<ConfigValue>, ConfigValue {

    /* renamed from: com.typesafe.config.ConfigList$-CC  reason: invalid class name */
    public final /* synthetic */ class CC {
    }

    List<Object> unwrapped();

    ConfigList withOrigin(ConfigOrigin configOrigin);
}
