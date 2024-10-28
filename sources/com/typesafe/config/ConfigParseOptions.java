package com.typesafe.config;

import com.typesafe.config.impl.ConfigImplUtil;

public final class ConfigParseOptions {
    final boolean allowMissing;
    final ClassLoader classLoader;
    final ConfigIncluder includer;
    final String originDescription;
    final ConfigSyntax syntax;

    private ConfigParseOptions(ConfigSyntax configSyntax, String str, boolean z, ConfigIncluder configIncluder, ClassLoader classLoader2) {
        this.syntax = configSyntax;
        this.originDescription = str;
        this.allowMissing = z;
        this.includer = configIncluder;
        this.classLoader = classLoader2;
    }

    public static ConfigParseOptions defaults() {
        return new ConfigParseOptions((ConfigSyntax) null, (String) null, true, (ConfigIncluder) null, (ClassLoader) null);
    }

    public ConfigParseOptions setSyntax(ConfigSyntax configSyntax) {
        if (this.syntax == configSyntax) {
            return this;
        }
        return new ConfigParseOptions(configSyntax, this.originDescription, this.allowMissing, this.includer, this.classLoader);
    }

    public ConfigParseOptions setSyntaxFromFilename(String str) {
        return setSyntax(ConfigImplUtil.syntaxFromExtension(str));
    }

    public ConfigSyntax getSyntax() {
        return this.syntax;
    }

    public ConfigParseOptions setOriginDescription(String str) {
        String str2 = this.originDescription;
        if (str2 == str) {
            return this;
        }
        if (str2 != null && str != null && str2.equals(str)) {
            return this;
        }
        return new ConfigParseOptions(this.syntax, str, this.allowMissing, this.includer, this.classLoader);
    }

    public String getOriginDescription() {
        return this.originDescription;
    }

    /* access modifiers changed from: package-private */
    public ConfigParseOptions withFallbackOriginDescription(String str) {
        return this.originDescription == null ? setOriginDescription(str) : this;
    }

    public ConfigParseOptions setAllowMissing(boolean z) {
        if (this.allowMissing == z) {
            return this;
        }
        return new ConfigParseOptions(this.syntax, this.originDescription, z, this.includer, this.classLoader);
    }

    public boolean getAllowMissing() {
        return this.allowMissing;
    }

    public ConfigParseOptions setIncluder(ConfigIncluder configIncluder) {
        if (this.includer == configIncluder) {
            return this;
        }
        return new ConfigParseOptions(this.syntax, this.originDescription, this.allowMissing, configIncluder, this.classLoader);
    }

    public ConfigParseOptions prependIncluder(ConfigIncluder configIncluder) {
        if (configIncluder != null) {
            ConfigIncluder configIncluder2 = this.includer;
            if (configIncluder2 == configIncluder) {
                return this;
            }
            if (configIncluder2 != null) {
                return setIncluder(configIncluder.withFallback(configIncluder2));
            }
            return setIncluder(configIncluder);
        }
        throw new NullPointerException("null includer passed to prependIncluder");
    }

    public ConfigParseOptions appendIncluder(ConfigIncluder configIncluder) {
        if (configIncluder != null) {
            ConfigIncluder configIncluder2 = this.includer;
            if (configIncluder2 == configIncluder) {
                return this;
            }
            if (configIncluder2 != null) {
                return setIncluder(configIncluder2.withFallback(configIncluder));
            }
            return setIncluder(configIncluder);
        }
        throw new NullPointerException("null includer passed to appendIncluder");
    }

    public ConfigIncluder getIncluder() {
        return this.includer;
    }

    public ConfigParseOptions setClassLoader(ClassLoader classLoader2) {
        if (this.classLoader == classLoader2) {
            return this;
        }
        return new ConfigParseOptions(this.syntax, this.originDescription, this.allowMissing, this.includer, classLoader2);
    }

    public ClassLoader getClassLoader() {
        ClassLoader classLoader2 = this.classLoader;
        return classLoader2 == null ? Thread.currentThread().getContextClassLoader() : classLoader2;
    }
}
