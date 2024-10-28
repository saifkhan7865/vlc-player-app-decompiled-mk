package com.typesafe.config.parser;

import com.typesafe.config.ConfigValue;

public interface ConfigDocument {
    boolean hasPath(String str);

    String render();

    ConfigDocument withValue(String str, ConfigValue configValue);

    ConfigDocument withValueText(String str, String str2);

    ConfigDocument withoutPath(String str);
}
