package com.typesafe.config;

public interface ConfigMergeable {
    ConfigMergeable withFallback(ConfigMergeable configMergeable);
}
