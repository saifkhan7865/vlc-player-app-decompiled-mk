package com.typesafe.config;

import com.typesafe.config.impl.ConfigImplUtil;
import java.util.List;

public final class ConfigUtil {
    private ConfigUtil() {
    }

    public static String quoteString(String str) {
        return ConfigImplUtil.renderJsonString(str);
    }

    public static String joinPath(String... strArr) {
        return ConfigImplUtil.joinPath(strArr);
    }

    public static String joinPath(List<String> list) {
        return ConfigImplUtil.joinPath(list);
    }

    public static List<String> splitPath(String str) {
        return ConfigImplUtil.splitPath(str);
    }
}
