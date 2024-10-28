package com.typesafe.config.impl;

import com.typesafe.config.ConfigException;
import com.typesafe.config.ConfigOrigin;
import com.typesafe.config.ConfigRenderOptions;
import com.typesafe.config.ConfigValueType;
import com.typesafe.config.impl.AbstractConfigValue;
import com.typesafe.config.impl.ResolveSource;
import java.util.Collection;
import java.util.Collections;

final class ConfigReference extends AbstractConfigValue implements Unmergeable {
    private final SubstitutionExpression expr;
    private final int prefixLength;

    /* access modifiers changed from: protected */
    public boolean ignoresFallbacks() {
        return false;
    }

    ConfigReference(ConfigOrigin configOrigin, SubstitutionExpression substitutionExpression) {
        this(configOrigin, substitutionExpression, 0);
    }

    private ConfigReference(ConfigOrigin configOrigin, SubstitutionExpression substitutionExpression, int i) {
        super(configOrigin);
        this.expr = substitutionExpression;
        this.prefixLength = i;
    }

    private ConfigException.NotResolved notResolved() {
        return new ConfigException.NotResolved("need to Config#resolve(), see the API docs for Config#resolve(); substitution not resolved: " + this);
    }

    public ConfigValueType valueType() {
        throw notResolved();
    }

    public Object unwrapped() {
        throw notResolved();
    }

    /* access modifiers changed from: protected */
    public ConfigReference newCopy(ConfigOrigin configOrigin) {
        return new ConfigReference(configOrigin, this.expr, this.prefixLength);
    }

    public Collection<ConfigReference> unmergedValues() {
        return Collections.singleton(this);
    }

    /* access modifiers changed from: package-private */
    public ResolveResult<? extends AbstractConfigValue> resolveSubstitutions(ResolveContext resolveContext, ResolveSource resolveSource) {
        V v;
        ResolveContext addCycleMarker = resolveContext.addCycleMarker(this);
        try {
            ResolveSource.ResultWithPath lookupSubst = resolveSource.lookupSubst(addCycleMarker, this.expr, this.prefixLength);
            addCycleMarker = lookupSubst.result.context;
            if (lookupSubst.result.value != null) {
                if (ConfigImpl.traceSubstitutionsEnabled()) {
                    int depth = addCycleMarker.depth();
                    ConfigImpl.trace(depth, "recursively resolving " + lookupSubst + " which was the resolution of " + this.expr + " against " + resolveSource);
                }
                ResolveSource resolveSource2 = new ResolveSource((AbstractConfigObject) lookupSubst.pathFromRoot.last(), lookupSubst.pathFromRoot);
                if (ConfigImpl.traceSubstitutionsEnabled()) {
                    int depth2 = addCycleMarker.depth();
                    ConfigImpl.trace(depth2, "will recursively resolve against " + resolveSource2);
                }
                ResolveResult<? extends AbstractConfigValue> resolve = addCycleMarker.resolve(lookupSubst.result.value, resolveSource2);
                v = resolve.value;
                addCycleMarker = resolve.context;
            } else {
                v = (AbstractConfigValue) resolveContext.options().getResolver().lookup(this.expr.path().render());
            }
        } catch (AbstractConfigValue.NotPossibleToResolve e) {
            if (ConfigImpl.traceSubstitutionsEnabled()) {
                int depth3 = addCycleMarker.depth();
                ConfigImpl.trace(depth3, "not possible to resolve " + this.expr + ", cycle involved: " + e.traceString());
            }
            if (this.expr.optional()) {
                v = null;
            } else {
                SimpleConfigOrigin origin = origin();
                throw new ConfigException.UnresolvedSubstitution((ConfigOrigin) origin, this.expr + " was part of a cycle of substitutions involving " + e.traceString(), (Throwable) e);
            }
        }
        if (v != null || this.expr.optional()) {
            return ResolveResult.make(addCycleMarker.removeCycleMarker(this), v);
        }
        if (addCycleMarker.options().getAllowUnresolved()) {
            return ResolveResult.make(addCycleMarker.removeCycleMarker(this), this);
        }
        throw new ConfigException.UnresolvedSubstitution(origin(), this.expr.toString());
    }

    /* access modifiers changed from: package-private */
    public ResolveStatus resolveStatus() {
        return ResolveStatus.UNRESOLVED;
    }

    /* access modifiers changed from: package-private */
    public ConfigReference relativized(Path path) {
        SubstitutionExpression substitutionExpression = this.expr;
        return new ConfigReference(origin(), substitutionExpression.changePath(substitutionExpression.path().prepend(path)), this.prefixLength + path.length());
    }

    /* access modifiers changed from: protected */
    public boolean canEqual(Object obj) {
        return obj instanceof ConfigReference;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ConfigReference) || !canEqual(obj) || !this.expr.equals(((ConfigReference) obj).expr)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.expr.hashCode();
    }

    /* access modifiers changed from: protected */
    public void render(StringBuilder sb, int i, boolean z, ConfigRenderOptions configRenderOptions) {
        sb.append(this.expr.toString());
    }

    /* access modifiers changed from: package-private */
    public SubstitutionExpression expression() {
        return this.expr;
    }
}
