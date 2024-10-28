package com.typesafe.config;

import com.typesafe.config.impl.ConfigBeanImpl;

public class ConfigBeanFactory {
    public static <T> T create(Config config, Class<T> cls) {
        return ConfigBeanImpl.createInternal(config, cls);
    }
}
