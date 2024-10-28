package com.typesafe.config;

import com.typesafe.config.impl.ConfigImpl;
import java.net.URL;

public final class ConfigOriginFactory {
    private ConfigOriginFactory() {
    }

    public static ConfigOrigin newSimple() {
        return newSimple((String) null);
    }

    public static ConfigOrigin newSimple(String str) {
        return ConfigImpl.newSimpleOrigin(str);
    }

    public static ConfigOrigin newFile(String str) {
        return ConfigImpl.newFileOrigin(str);
    }

    public static ConfigOrigin newURL(URL url) {
        return ConfigImpl.newURLOrigin(url);
    }
}
