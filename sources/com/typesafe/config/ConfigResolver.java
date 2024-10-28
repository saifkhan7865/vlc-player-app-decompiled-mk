package com.typesafe.config;

public interface ConfigResolver {
    ConfigValue lookup(String str);

    ConfigResolver withFallback(ConfigResolver configResolver);
}
