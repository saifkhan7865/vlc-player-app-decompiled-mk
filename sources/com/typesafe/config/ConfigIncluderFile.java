package com.typesafe.config;

import java.io.File;

public interface ConfigIncluderFile {
    ConfigObject includeFile(ConfigIncludeContext configIncludeContext, File file);
}
