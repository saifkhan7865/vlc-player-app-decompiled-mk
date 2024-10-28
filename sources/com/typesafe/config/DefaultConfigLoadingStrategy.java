package com.typesafe.config;

public class DefaultConfigLoadingStrategy implements ConfigLoadingStrategy {
    public Config parseApplicationConfig(ConfigParseOptions configParseOptions) {
        return ConfigFactory.parseApplicationReplacement(configParseOptions).orElseGet(new DefaultConfigLoadingStrategy$$ExternalSyntheticLambda0(configParseOptions));
    }
}
