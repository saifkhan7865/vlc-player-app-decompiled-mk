package com.typesafe.config.impl;

import com.typesafe.config.ConfigException;
import com.typesafe.config.ConfigList;
import com.typesafe.config.ConfigMergeable;
import com.typesafe.config.ConfigOrigin;
import com.typesafe.config.ConfigRenderOptions;
import com.typesafe.config.ConfigValue;
import com.typesafe.config.impl.AbstractConfigValue;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

final class ConfigDelayedMergeObject extends AbstractConfigObject implements Unmergeable, ReplaceableMergeStack {
    private final List<AbstractConfigValue> stack;

    /* JADX WARNING: Removed duplicated region for block: B:7:0x001e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    ConfigDelayedMergeObject(com.typesafe.config.ConfigOrigin r2, java.util.List<com.typesafe.config.impl.AbstractConfigValue> r3) {
        /*
            r1 = this;
            r1.<init>(r2)
            r1.stack = r3
            boolean r2 = r3.isEmpty()
            if (r2 != 0) goto L_0x003e
            r2 = 0
            java.lang.Object r2 = r3.get(r2)
            boolean r2 = r2 instanceof com.typesafe.config.impl.AbstractConfigObject
            if (r2 == 0) goto L_0x0036
            java.util.Iterator r2 = r3.iterator()
        L_0x0018:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x0035
            java.lang.Object r3 = r2.next()
            com.typesafe.config.impl.AbstractConfigValue r3 = (com.typesafe.config.impl.AbstractConfigValue) r3
            boolean r0 = r3 instanceof com.typesafe.config.impl.ConfigDelayedMerge
            if (r0 != 0) goto L_0x002d
            boolean r3 = r3 instanceof com.typesafe.config.impl.ConfigDelayedMergeObject
            if (r3 != 0) goto L_0x002d
            goto L_0x0018
        L_0x002d:
            com.typesafe.config.ConfigException$BugOrBroken r2 = new com.typesafe.config.ConfigException$BugOrBroken
            java.lang.String r3 = "placed nested DelayedMerge in a ConfigDelayedMergeObject, should have consolidated stack"
            r2.<init>(r3)
            throw r2
        L_0x0035:
            return
        L_0x0036:
            com.typesafe.config.ConfigException$BugOrBroken r2 = new com.typesafe.config.ConfigException$BugOrBroken
            java.lang.String r3 = "created a delayed merge object not guaranteed to be an object"
            r2.<init>(r3)
            throw r2
        L_0x003e:
            com.typesafe.config.ConfigException$BugOrBroken r2 = new com.typesafe.config.ConfigException$BugOrBroken
            java.lang.String r3 = "creating empty delayed merge object"
            r2.<init>(r3)
            goto L_0x0047
        L_0x0046:
            throw r2
        L_0x0047:
            goto L_0x0046
        */
        throw new UnsupportedOperationException("Method not decompiled: com.typesafe.config.impl.ConfigDelayedMergeObject.<init>(com.typesafe.config.ConfigOrigin, java.util.List):void");
    }

    /* access modifiers changed from: protected */
    public ConfigDelayedMergeObject newCopy(ResolveStatus resolveStatus, ConfigOrigin configOrigin) {
        if (resolveStatus == resolveStatus()) {
            return new ConfigDelayedMergeObject(configOrigin, this.stack);
        }
        throw new ConfigException.BugOrBroken("attempt to create resolved ConfigDelayedMergeObject");
    }

    /* access modifiers changed from: package-private */
    public ResolveResult<? extends AbstractConfigObject> resolveSubstitutions(ResolveContext resolveContext, ResolveSource resolveSource) throws AbstractConfigValue.NotPossibleToResolve {
        return ConfigDelayedMerge.resolveSubstitutions(this, this.stack, resolveContext, resolveSource).asObjectResult();
    }

    public AbstractConfigValue makeReplacement(ResolveContext resolveContext, int i) {
        return ConfigDelayedMerge.makeReplacement(resolveContext, this.stack, i);
    }

    /* access modifiers changed from: package-private */
    public ResolveStatus resolveStatus() {
        return ResolveStatus.UNRESOLVED;
    }

    public AbstractConfigValue replaceChild(AbstractConfigValue abstractConfigValue, AbstractConfigValue abstractConfigValue2) {
        List<AbstractConfigValue> replaceChildInList = replaceChildInList(this.stack, abstractConfigValue, abstractConfigValue2);
        if (replaceChildInList == null) {
            return null;
        }
        return new ConfigDelayedMergeObject(origin(), replaceChildInList);
    }

    public boolean hasDescendant(AbstractConfigValue abstractConfigValue) {
        return hasDescendantInList(this.stack, abstractConfigValue);
    }

    /* access modifiers changed from: package-private */
    public ConfigDelayedMergeObject relativized(Path path) {
        ArrayList arrayList = new ArrayList();
        for (AbstractConfigValue relativized : this.stack) {
            arrayList.add(relativized.relativized(path));
        }
        return new ConfigDelayedMergeObject(origin(), arrayList);
    }

    /* access modifiers changed from: protected */
    public boolean ignoresFallbacks() {
        return ConfigDelayedMerge.stackIgnoresFallbacks(this.stack);
    }

    /* access modifiers changed from: protected */
    public final ConfigDelayedMergeObject mergedWithTheUnmergeable(Unmergeable unmergeable) {
        requireNotIgnoringFallbacks();
        return (ConfigDelayedMergeObject) mergedWithTheUnmergeable(this.stack, unmergeable);
    }

    /* access modifiers changed from: protected */
    public final ConfigDelayedMergeObject mergedWithObject(AbstractConfigObject abstractConfigObject) {
        return mergedWithNonObject((AbstractConfigValue) abstractConfigObject);
    }

    /* access modifiers changed from: protected */
    public final ConfigDelayedMergeObject mergedWithNonObject(AbstractConfigValue abstractConfigValue) {
        requireNotIgnoringFallbacks();
        return (ConfigDelayedMergeObject) mergedWithNonObject(this.stack, abstractConfigValue);
    }

    public ConfigDelayedMergeObject withFallback(ConfigMergeable configMergeable) {
        return (ConfigDelayedMergeObject) super.withFallback(configMergeable);
    }

    public ConfigDelayedMergeObject withOnlyKey(String str) {
        throw notResolved();
    }

    public ConfigDelayedMergeObject withoutKey(String str) {
        throw notResolved();
    }

    /* access modifiers changed from: protected */
    public AbstractConfigObject withOnlyPathOrNull(Path path) {
        throw notResolved();
    }

    /* access modifiers changed from: package-private */
    public AbstractConfigObject withOnlyPath(Path path) {
        throw notResolved();
    }

    /* access modifiers changed from: package-private */
    public AbstractConfigObject withoutPath(Path path) {
        throw notResolved();
    }

    public ConfigDelayedMergeObject withValue(String str, ConfigValue configValue) {
        throw notResolved();
    }

    /* access modifiers changed from: package-private */
    public ConfigDelayedMergeObject withValue(Path path, ConfigValue configValue) {
        throw notResolved();
    }

    public Collection<AbstractConfigValue> unmergedValues() {
        return this.stack;
    }

    /* access modifiers changed from: protected */
    public boolean canEqual(Object obj) {
        return obj instanceof ConfigDelayedMergeObject;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ConfigDelayedMergeObject) || !canEqual(obj)) {
            return false;
        }
        List<AbstractConfigValue> list = this.stack;
        List<AbstractConfigValue> list2 = ((ConfigDelayedMergeObject) obj).stack;
        if (list == list2 || list.equals(list2)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.stack.hashCode();
    }

    /* access modifiers changed from: protected */
    public void render(StringBuilder sb, int i, boolean z, String str, ConfigRenderOptions configRenderOptions) {
        ConfigDelayedMerge.render(this.stack, sb, i, z, str, configRenderOptions);
    }

    /* access modifiers changed from: protected */
    public void render(StringBuilder sb, int i, boolean z, ConfigRenderOptions configRenderOptions) {
        render(sb, i, z, (String) null, configRenderOptions);
    }

    private static ConfigException notResolved() {
        return new ConfigException.NotResolved("need to Config#resolve() before using this object, see the API docs for Config#resolve()");
    }

    public Map<String, Object> unwrapped() {
        throw notResolved();
    }

    public AbstractConfigValue get(Object obj) {
        throw notResolved();
    }

    public boolean containsKey(Object obj) {
        throw notResolved();
    }

    public boolean containsValue(Object obj) {
        throw notResolved();
    }

    public Set<Map.Entry<String, ConfigValue>> entrySet() {
        throw notResolved();
    }

    public boolean isEmpty() {
        throw notResolved();
    }

    public Set<String> keySet() {
        throw notResolved();
    }

    public int size() {
        throw notResolved();
    }

    public Collection<ConfigValue> values() {
        throw notResolved();
    }

    /* access modifiers changed from: protected */
    public AbstractConfigValue attemptPeekWithPartialResolve(String str) {
        for (AbstractConfigValue next : this.stack) {
            if (next instanceof AbstractConfigObject) {
                AbstractConfigValue attemptPeekWithPartialResolve = ((AbstractConfigObject) next).attemptPeekWithPartialResolve(str);
                if (attemptPeekWithPartialResolve != null) {
                    if (attemptPeekWithPartialResolve.ignoresFallbacks()) {
                        return attemptPeekWithPartialResolve;
                    }
                } else if (next instanceof Unmergeable) {
                    throw new ConfigException.BugOrBroken("should not be reached: unmergeable object returned null value");
                }
            } else if (next instanceof Unmergeable) {
                throw new ConfigException.NotResolved("Key '" + str + "' is not available at '" + origin().description() + "' because value at '" + next.origin().description() + "' has not been resolved and may turn out to contain or hide '" + str + "'. Be sure to Config#resolve() before using a config object.");
            } else if (next.resolveStatus() == ResolveStatus.UNRESOLVED) {
                if (next instanceof ConfigList) {
                    return null;
                }
                throw new ConfigException.BugOrBroken("Expecting a list here, not " + next);
            } else if (next.ignoresFallbacks()) {
                return null;
            } else {
                throw new ConfigException.BugOrBroken("resolved non-object should ignore fallbacks");
            }
        }
        throw new ConfigException.BugOrBroken("Delayed merge stack does not contain any unmergeable values");
    }
}
