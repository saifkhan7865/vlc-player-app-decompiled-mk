package com.typesafe.config.parser;

import com.typesafe.config.ConfigParseOptions;
import com.typesafe.config.impl.Parseable;
import java.io.File;
import java.io.Reader;

public final class ConfigDocumentFactory {
    public static ConfigDocument parseReader(Reader reader, ConfigParseOptions configParseOptions) {
        return Parseable.newReader(reader, configParseOptions).parseConfigDocument();
    }

    public static ConfigDocument parseReader(Reader reader) {
        return parseReader(reader, ConfigParseOptions.defaults());
    }

    public static ConfigDocument parseFile(File file, ConfigParseOptions configParseOptions) {
        return Parseable.newFile(file, configParseOptions).parseConfigDocument();
    }

    public static ConfigDocument parseFile(File file) {
        return parseFile(file, ConfigParseOptions.defaults());
    }

    public static ConfigDocument parseString(String str, ConfigParseOptions configParseOptions) {
        return Parseable.newString(str, configParseOptions).parseConfigDocument();
    }

    public static ConfigDocument parseString(String str) {
        return parseString(str, ConfigParseOptions.defaults());
    }
}
