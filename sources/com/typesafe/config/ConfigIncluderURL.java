package com.typesafe.config;

import java.net.URL;

public interface ConfigIncluderURL {
    ConfigObject includeURL(ConfigIncludeContext configIncludeContext, URL url);
}
