package com.typesafe.config.impl;

import com.typesafe.config.ConfigException;
import com.typesafe.config.ConfigMergeable;
import com.typesafe.config.ConfigObject;
import com.typesafe.config.ConfigOrigin;
import com.typesafe.config.ConfigRenderOptions;
import com.typesafe.config.ConfigValue;
import com.typesafe.config.ConfigValueType;
import com.typesafe.config.impl.AbstractConfigValue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

abstract class AbstractConfigObject extends AbstractConfigValue implements ConfigObject, Container {
    private final SimpleConfig config = new SimpleConfig(this);

    /* access modifiers changed from: package-private */
    public abstract AbstractConfigValue attemptPeekWithPartialResolve(String str);

    public abstract AbstractConfigValue get(Object obj);

    /* access modifiers changed from: protected */
    public abstract AbstractConfigObject mergedWithObject(AbstractConfigObject abstractConfigObject);

    /* access modifiers changed from: protected */
    public abstract AbstractConfigObject newCopy(ResolveStatus resolveStatus, ConfigOrigin configOrigin);

    /* access modifiers changed from: package-private */
    public abstract AbstractConfigObject relativized(Path path);

    /* access modifiers changed from: protected */
    public abstract void render(StringBuilder sb, int i, boolean z, ConfigRenderOptions configRenderOptions);

    /* access modifiers changed from: package-private */
    public abstract ResolveResult<? extends AbstractConfigObject> resolveSubstitutions(ResolveContext resolveContext, ResolveSource resolveSource) throws AbstractConfigValue.NotPossibleToResolve;

    public AbstractConfigObject toFallbackValue() {
        return this;
    }

    public /* bridge */ /* synthetic */ Object unwrapped() {
        return unwrapped();
    }

    public abstract AbstractConfigObject withOnlyKey(String str);

    /* access modifiers changed from: package-private */
    public abstract AbstractConfigObject withOnlyPath(Path path);

    /* access modifiers changed from: protected */
    public abstract AbstractConfigObject withOnlyPathOrNull(Path path);

    /* access modifiers changed from: package-private */
    public abstract AbstractConfigObject withValue(Path path, ConfigValue configValue);

    public abstract AbstractConfigObject withValue(String str, ConfigValue configValue);

    public abstract AbstractConfigObject withoutKey(String str);

    /* access modifiers changed from: package-private */
    public abstract AbstractConfigObject withoutPath(Path path);

    protected AbstractConfigObject(ConfigOrigin configOrigin) {
        super(configOrigin);
    }

    public SimpleConfig toConfig() {
        return this.config;
    }

    /* access modifiers changed from: protected */
    public final AbstractConfigValue peekAssumingResolved(String str, Path path) {
        try {
            return attemptPeekWithPartialResolve(str);
        } catch (ConfigException.NotResolved e) {
            throw ConfigImpl.improveNotResolved(path, e);
        }
    }

    /* access modifiers changed from: protected */
    public AbstractConfigValue peekPath(Path path) {
        return peekPath(this, path);
    }

    private static AbstractConfigValue peekPath(AbstractConfigObject abstractConfigObject, Path path) {
        try {
            Path remainder = path.remainder();
            AbstractConfigValue attemptPeekWithPartialResolve = abstractConfigObject.attemptPeekWithPartialResolve(path.first());
            if (remainder == null) {
                return attemptPeekWithPartialResolve;
            }
            if (attemptPeekWithPartialResolve instanceof AbstractConfigObject) {
                return peekPath((AbstractConfigObject) attemptPeekWithPartialResolve, remainder);
            }
            return null;
        } catch (ConfigException.NotResolved e) {
            throw ConfigImpl.improveNotResolved(path, e);
        }
    }

    public ConfigValueType valueType() {
        return ConfigValueType.OBJECT;
    }

    /* access modifiers changed from: protected */
    public AbstractConfigObject newCopy(ConfigOrigin configOrigin) {
        return newCopy(resolveStatus(), configOrigin);
    }

    /* access modifiers changed from: protected */
    public AbstractConfigObject constructDelayedMerge(ConfigOrigin configOrigin, List<AbstractConfigValue> list) {
        return new ConfigDelayedMergeObject(configOrigin, list);
    }

    public AbstractConfigObject withFallback(ConfigMergeable configMergeable) {
        return (AbstractConfigObject) super.withFallback(configMergeable);
    }

    static ConfigOrigin mergeOrigins(Collection<? extends AbstractConfigValue> collection) {
        if (!collection.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            SimpleConfigOrigin simpleConfigOrigin = null;
            int i = 0;
            for (AbstractConfigValue abstractConfigValue : collection) {
                if (simpleConfigOrigin == null) {
                    simpleConfigOrigin = abstractConfigValue.origin();
                }
                if (!(abstractConfigValue instanceof AbstractConfigObject) || ((AbstractConfigObject) abstractConfigValue).resolveStatus() != ResolveStatus.RESOLVED || !((ConfigObject) abstractConfigValue).isEmpty()) {
                    arrayList.add(abstractConfigValue.origin());
                    i++;
                }
            }
            if (i == 0) {
                arrayList.add(simpleConfigOrigin);
            }
            return SimpleConfigOrigin.mergeOrigins((Collection<? extends ConfigOrigin>) arrayList);
        }
        throw new ConfigException.BugOrBroken("can't merge origins on empty list");
    }

    static ConfigOrigin mergeOrigins(AbstractConfigObject... abstractConfigObjectArr) {
        return mergeOrigins((Collection<? extends AbstractConfigValue>) Arrays.asList(abstractConfigObjectArr));
    }

    private static UnsupportedOperationException weAreImmutable(String str) {
        return new UnsupportedOperationException("ConfigObject is immutable, you can't call Map." + str);
    }

    public void clear() {
        throw weAreImmutable("clear");
    }

    public ConfigValue put(String str, ConfigValue configValue) {
        throw weAreImmutable("put");
    }

    public void putAll(Map<? extends String, ? extends ConfigValue> map) {
        throw weAreImmutable("putAll");
    }

    public ConfigValue remove(Object obj) {
        throw weAreImmutable("remove");
    }

    public AbstractConfigObject withOrigin(ConfigOrigin configOrigin) {
        return (AbstractConfigObject) super.withOrigin(configOrigin);
    }
}
