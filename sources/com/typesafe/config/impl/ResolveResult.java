package com.typesafe.config.impl;

import com.typesafe.config.ConfigException;
import com.typesafe.config.impl.AbstractConfigValue;

final class ResolveResult<V extends AbstractConfigValue> {
    public final ResolveContext context;
    public final V value;

    private ResolveResult(ResolveContext resolveContext, V v) {
        this.context = resolveContext;
        this.value = v;
    }

    static <V extends AbstractConfigValue> ResolveResult<V> make(ResolveContext resolveContext, V v) {
        return new ResolveResult<>(resolveContext, v);
    }

    /* access modifiers changed from: package-private */
    public ResolveResult<AbstractConfigObject> asObjectResult() {
        if (this.value instanceof AbstractConfigObject) {
            ResolveResult resolveResult = this;
            return this;
        }
        throw new ConfigException.BugOrBroken("Expecting a resolve result to be an object, but it was " + this.value);
    }

    /* access modifiers changed from: package-private */
    public ResolveResult<AbstractConfigValue> asValueResult() {
        ResolveResult resolveResult = this;
        return this;
    }

    /* access modifiers changed from: package-private */
    public ResolveResult<V> popTrace() {
        return make(this.context.popTrace(), this.value);
    }

    public String toString() {
        return "ResolveResult(" + this.value + ")";
    }
}
