package com.typesafe.config;

public interface ConfigIncluderClasspath {
    ConfigObject includeResources(ConfigIncludeContext configIncludeContext, String str);
}
