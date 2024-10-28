package com.typesafe.config;

public interface ConfigIncludeContext {
    ConfigParseOptions parseOptions();

    ConfigParseable relativeTo(String str);

    ConfigIncludeContext setParseOptions(ConfigParseOptions configParseOptions);
}
