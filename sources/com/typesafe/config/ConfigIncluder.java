package com.typesafe.config;

public interface ConfigIncluder {
    ConfigObject include(ConfigIncludeContext configIncludeContext, String str);

    ConfigIncluder withFallback(ConfigIncluder configIncluder);
}
