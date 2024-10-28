package com.typesafe.config;

public interface ConfigParseable {
    ConfigParseOptions options();

    ConfigOrigin origin();

    ConfigObject parse(ConfigParseOptions configParseOptions);
}
