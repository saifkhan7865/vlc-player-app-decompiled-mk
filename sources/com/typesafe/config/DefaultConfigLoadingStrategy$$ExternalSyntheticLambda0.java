package com.typesafe.config;

import java.util.function.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DefaultConfigLoadingStrategy$$ExternalSyntheticLambda0 implements Supplier {
    public final /* synthetic */ ConfigParseOptions f$0;

    public /* synthetic */ DefaultConfigLoadingStrategy$$ExternalSyntheticLambda0(ConfigParseOptions configParseOptions) {
        this.f$0 = configParseOptions;
    }

    public final Object get() {
        return ConfigFactory.parseResourcesAnySyntax("application", this.f$0);
    }
}
