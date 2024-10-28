package com.typesafe.config.impl;

import com.typesafe.config.ConfigException;
import com.typesafe.config.ConfigMergeable;
import com.typesafe.config.ConfigObject;
import com.typesafe.config.ConfigOrigin;
import com.typesafe.config.ConfigRenderOptions;
import com.typesafe.config.ConfigValue;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

abstract class AbstractConfigValue implements ConfigValue, MergeableValue {
    private final SimpleConfigOrigin origin;

    protected interface Modifier {
        AbstractConfigValue modifyChildMayThrow(String str, AbstractConfigValue abstractConfigValue) throws Exception;
    }

    /* access modifiers changed from: protected */
    public abstract AbstractConfigValue newCopy(ConfigOrigin configOrigin);

    /* access modifiers changed from: package-private */
    public AbstractConfigValue relativized(Path path) {
        return this;
    }

    public AbstractConfigValue toFallbackValue() {
        return this;
    }

    /* access modifiers changed from: package-private */
    public String transformToString() {
        return null;
    }

    AbstractConfigValue(ConfigOrigin configOrigin) {
        this.origin = (SimpleConfigOrigin) configOrigin;
    }

    public SimpleConfigOrigin origin() {
        return this.origin;
    }

    static class NotPossibleToResolve extends Exception {
        private static final long serialVersionUID = 1;
        private final String traceString;

        NotPossibleToResolve(ResolveContext resolveContext) {
            super("was not possible to resolve");
            this.traceString = resolveContext.traceString();
        }

        /* access modifiers changed from: package-private */
        public String traceString() {
            return this.traceString;
        }
    }

    /* access modifiers changed from: package-private */
    public ResolveResult<? extends AbstractConfigValue> resolveSubstitutions(ResolveContext resolveContext, ResolveSource resolveSource) throws NotPossibleToResolve {
        return ResolveResult.make(resolveContext, this);
    }

    /* access modifiers changed from: package-private */
    public ResolveStatus resolveStatus() {
        return ResolveStatus.RESOLVED;
    }

    protected static List<AbstractConfigValue> replaceChildInList(List<AbstractConfigValue> list, AbstractConfigValue abstractConfigValue, AbstractConfigValue abstractConfigValue2) {
        int i = 0;
        while (i < list.size() && list.get(i) != abstractConfigValue) {
            i++;
        }
        if (i != list.size()) {
            ArrayList arrayList = new ArrayList(list);
            if (abstractConfigValue2 != null) {
                arrayList.set(i, abstractConfigValue2);
            } else {
                arrayList.remove(i);
            }
            if (arrayList.isEmpty()) {
                return null;
            }
            return arrayList;
        }
        throw new ConfigException.BugOrBroken("tried to replace " + abstractConfigValue + " which is not in " + list);
    }

    protected static boolean hasDescendantInList(List<AbstractConfigValue> list, AbstractConfigValue abstractConfigValue) {
        for (AbstractConfigValue abstractConfigValue2 : list) {
            if (abstractConfigValue2 == abstractConfigValue) {
                return true;
            }
        }
        for (AbstractConfigValue next : list) {
            if ((next instanceof Container) && ((Container) next).hasDescendant(abstractConfigValue)) {
                return true;
            }
        }
        return false;
    }

    protected abstract class NoExceptionsModifier implements Modifier {
        /* access modifiers changed from: package-private */
        public abstract AbstractConfigValue modifyChild(String str, AbstractConfigValue abstractConfigValue);

        protected NoExceptionsModifier() {
        }

        public final AbstractConfigValue modifyChildMayThrow(String str, AbstractConfigValue abstractConfigValue) throws Exception {
            try {
                return modifyChild(str, abstractConfigValue);
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e2) {
                throw new ConfigException.BugOrBroken("Unexpected exception", e2);
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean ignoresFallbacks() {
        return resolveStatus() == ResolveStatus.RESOLVED;
    }

    /* access modifiers changed from: protected */
    public AbstractConfigValue withFallbacksIgnored() {
        if (ignoresFallbacks()) {
            return this;
        }
        throw new ConfigException.BugOrBroken("value class doesn't implement forced fallback-ignoring " + this);
    }

    /* access modifiers changed from: protected */
    public final void requireNotIgnoringFallbacks() {
        if (ignoresFallbacks()) {
            throw new ConfigException.BugOrBroken("method should not have been called with ignoresFallbacks=true " + getClass().getSimpleName());
        }
    }

    /* access modifiers changed from: protected */
    public AbstractConfigValue constructDelayedMerge(ConfigOrigin configOrigin, List<AbstractConfigValue> list) {
        return new ConfigDelayedMerge(configOrigin, list);
    }

    /* access modifiers changed from: protected */
    public final AbstractConfigValue mergedWithTheUnmergeable(Collection<AbstractConfigValue> collection, Unmergeable unmergeable) {
        requireNotIgnoringFallbacks();
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(collection);
        arrayList.addAll(unmergeable.unmergedValues());
        return constructDelayedMerge(AbstractConfigObject.mergeOrigins((Collection<? extends AbstractConfigValue>) arrayList), arrayList);
    }

    private final AbstractConfigValue delayMerge(Collection<AbstractConfigValue> collection, AbstractConfigValue abstractConfigValue) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(collection);
        arrayList.add(abstractConfigValue);
        return constructDelayedMerge(AbstractConfigObject.mergeOrigins((Collection<? extends AbstractConfigValue>) arrayList), arrayList);
    }

    /* access modifiers changed from: protected */
    public final AbstractConfigValue mergedWithObject(Collection<AbstractConfigValue> collection, AbstractConfigObject abstractConfigObject) {
        requireNotIgnoringFallbacks();
        if (!(this instanceof AbstractConfigObject)) {
            return mergedWithNonObject(collection, abstractConfigObject);
        }
        throw new ConfigException.BugOrBroken("Objects must reimplement mergedWithObject");
    }

    /* access modifiers changed from: protected */
    public final AbstractConfigValue mergedWithNonObject(Collection<AbstractConfigValue> collection, AbstractConfigValue abstractConfigValue) {
        requireNotIgnoringFallbacks();
        if (resolveStatus() == ResolveStatus.RESOLVED) {
            return withFallbacksIgnored();
        }
        return delayMerge(collection, abstractConfigValue);
    }

    /* access modifiers changed from: protected */
    public AbstractConfigValue mergedWithTheUnmergeable(Unmergeable unmergeable) {
        requireNotIgnoringFallbacks();
        return mergedWithTheUnmergeable(Collections.singletonList(this), unmergeable);
    }

    /* access modifiers changed from: protected */
    public AbstractConfigValue mergedWithObject(AbstractConfigObject abstractConfigObject) {
        requireNotIgnoringFallbacks();
        return mergedWithObject(Collections.singletonList(this), abstractConfigObject);
    }

    /* access modifiers changed from: protected */
    public AbstractConfigValue mergedWithNonObject(AbstractConfigValue abstractConfigValue) {
        requireNotIgnoringFallbacks();
        return mergedWithNonObject(Collections.singletonList(this), abstractConfigValue);
    }

    public AbstractConfigValue withOrigin(ConfigOrigin configOrigin) {
        if (this.origin == configOrigin) {
            return this;
        }
        return newCopy(configOrigin);
    }

    public AbstractConfigValue withFallback(ConfigMergeable configMergeable) {
        if (ignoresFallbacks()) {
            return this;
        }
        ConfigValue fallbackValue = ((MergeableValue) configMergeable).toFallbackValue();
        if (fallbackValue instanceof Unmergeable) {
            return mergedWithTheUnmergeable((Unmergeable) fallbackValue);
        }
        if (fallbackValue instanceof AbstractConfigObject) {
            return mergedWithObject((AbstractConfigObject) fallbackValue);
        }
        return mergedWithNonObject((AbstractConfigValue) fallbackValue);
    }

    /* access modifiers changed from: protected */
    public boolean canEqual(Object obj) {
        return obj instanceof ConfigValue;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ConfigValue) || !canEqual(obj)) {
            return false;
        }
        ConfigValue configValue = (ConfigValue) obj;
        if (valueType() != configValue.valueType() || !ConfigImplUtil.equalsHandlingNull(unwrapped(), configValue.unwrapped())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        Object unwrapped = unwrapped();
        if (unwrapped == null) {
            return 0;
        }
        return unwrapped.hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        render(sb, 0, true, (String) null, ConfigRenderOptions.concise());
        return getClass().getSimpleName() + "(" + sb.toString() + ")";
    }

    protected static void indent(StringBuilder sb, int i, ConfigRenderOptions configRenderOptions) {
        if (configRenderOptions.getFormatted()) {
            while (i > 0) {
                sb.append("    ");
                i--;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void render(StringBuilder sb, int i, boolean z, String str, ConfigRenderOptions configRenderOptions) {
        String str2;
        if (str != null) {
            if (configRenderOptions.getJson()) {
                str2 = ConfigImplUtil.renderJsonString(str);
            } else {
                str2 = ConfigImplUtil.renderStringUnquotedIfPossible(str);
            }
            sb.append(str2);
            if (configRenderOptions.getJson()) {
                if (configRenderOptions.getFormatted()) {
                    sb.append(" : ");
                } else {
                    sb.append(":");
                }
            } else if (!(this instanceof ConfigObject)) {
                sb.append("=");
            } else if (configRenderOptions.getFormatted()) {
                sb.append(' ');
            }
        }
        render(sb, i, z, configRenderOptions);
    }

    /* access modifiers changed from: protected */
    public void render(StringBuilder sb, int i, boolean z, ConfigRenderOptions configRenderOptions) {
        sb.append(unwrapped().toString());
    }

    public final String render() {
        return render(ConfigRenderOptions.defaults());
    }

    public final String render(ConfigRenderOptions configRenderOptions) {
        StringBuilder sb = new StringBuilder();
        render(sb, 0, true, (String) null, configRenderOptions);
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public SimpleConfig atKey(ConfigOrigin configOrigin, String str) {
        return new SimpleConfigObject(configOrigin, Collections.singletonMap(str, this)).toConfig();
    }

    public SimpleConfig atKey(String str) {
        return atKey(SimpleConfigOrigin.newSimple("atKey(" + str + ")"), str);
    }

    /* access modifiers changed from: package-private */
    public SimpleConfig atPath(ConfigOrigin configOrigin, Path path) {
        SimpleConfig atKey = atKey(configOrigin, path.last());
        for (Path parent = path.parent(); parent != null; parent = parent.parent()) {
            atKey = atKey.atKey(configOrigin, parent.last());
        }
        return atKey;
    }

    public SimpleConfig atPath(String str) {
        return atPath(SimpleConfigOrigin.newSimple("atPath(" + str + ")"), Path.newPath(str));
    }
}
