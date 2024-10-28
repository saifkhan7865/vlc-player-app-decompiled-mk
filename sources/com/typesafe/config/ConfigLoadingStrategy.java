package com.typesafe.config;

public interface ConfigLoadingStrategy {
    Config parseApplicationConfig(ConfigParseOptions configParseOptions);
}
