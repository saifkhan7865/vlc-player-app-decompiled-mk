package com.typesafe.config.impl;

import com.typesafe.config.ConfigIncluder;
import com.typesafe.config.ConfigIncluderClasspath;
import com.typesafe.config.ConfigIncluderFile;
import com.typesafe.config.ConfigIncluderURL;

interface FullIncluder extends ConfigIncluder, ConfigIncluderFile, ConfigIncluderURL, ConfigIncluderClasspath {
}
