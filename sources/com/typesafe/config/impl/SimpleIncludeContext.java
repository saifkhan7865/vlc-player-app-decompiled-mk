package com.typesafe.config.impl;

import com.typesafe.config.ConfigIncludeContext;
import com.typesafe.config.ConfigParseOptions;
import com.typesafe.config.ConfigParseable;
import com.typesafe.config.ConfigSyntax;

class SimpleIncludeContext implements ConfigIncludeContext {
    private final ConfigParseOptions options;
    private final Parseable parseable;

    SimpleIncludeContext(Parseable parseable2) {
        this.parseable = parseable2;
        this.options = SimpleIncluder.clearForInclude(parseable2.options());
    }

    private SimpleIncludeContext(Parseable parseable2, ConfigParseOptions configParseOptions) {
        this.parseable = parseable2;
        this.options = configParseOptions;
    }

    /* access modifiers changed from: package-private */
    public SimpleIncludeContext withParseable(Parseable parseable2) {
        if (parseable2 == this.parseable) {
            return this;
        }
        return new SimpleIncludeContext(parseable2);
    }

    public ConfigParseable relativeTo(String str) {
        if (ConfigImpl.traceLoadsEnabled()) {
            ConfigImpl.trace("Looking for '" + str + "' relative to " + this.parseable);
        }
        Parseable parseable2 = this.parseable;
        if (parseable2 != null) {
            return parseable2.relativeTo(str);
        }
        return null;
    }

    public ConfigParseOptions parseOptions() {
        return this.options;
    }

    public ConfigIncludeContext setParseOptions(ConfigParseOptions configParseOptions) {
        return new SimpleIncludeContext(this.parseable, configParseOptions.setSyntax((ConfigSyntax) null).setOriginDescription((String) null));
    }
}
