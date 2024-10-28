package com.typesafe.config.impl;

import com.typesafe.config.ConfigException;
import com.typesafe.config.ConfigMergeable;
import com.typesafe.config.ConfigObject;
import com.typesafe.config.ConfigOrigin;
import com.typesafe.config.ConfigRenderOptions;
import com.typesafe.config.ConfigValueType;
import com.typesafe.config.impl.AbstractConfigValue;
import com.typesafe.config.impl.ConfigString;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

final class ConfigConcatenation extends AbstractConfigValue implements Unmergeable, Container {
    private final List<AbstractConfigValue> pieces;

    /* access modifiers changed from: protected */
    public boolean ignoresFallbacks() {
        return false;
    }

    ConfigConcatenation(ConfigOrigin configOrigin, List<AbstractConfigValue> list) {
        super(configOrigin);
        this.pieces = list;
        if (list.size() >= 2) {
            boolean z = false;
            for (AbstractConfigValue next : list) {
                if (next instanceof ConfigConcatenation) {
                    throw new ConfigException.BugOrBroken("ConfigConcatenation should never be nested: " + this);
                } else if (next instanceof Unmergeable) {
                    z = true;
                }
            }
            if (!z) {
                throw new ConfigException.BugOrBroken("Created concatenation without an unmergeable in it: " + this);
            }
            return;
        }
        throw new ConfigException.BugOrBroken("Created concatenation with less than 2 items: " + this);
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
    public ConfigConcatenation newCopy(ConfigOrigin configOrigin) {
        return new ConfigConcatenation(configOrigin, this.pieces);
    }

    public Collection<ConfigConcatenation> unmergedValues() {
        return Collections.singleton(this);
    }

    private static boolean isIgnoredWhitespace(AbstractConfigValue abstractConfigValue) {
        return (abstractConfigValue instanceof ConfigString) && !((ConfigString) abstractConfigValue).wasQuoted();
    }

    private static void join(ArrayList<AbstractConfigValue> arrayList, AbstractConfigValue abstractConfigValue) {
        AbstractConfigValue abstractConfigValue2 = arrayList.get(arrayList.size() - 1);
        if ((abstractConfigValue2 instanceof ConfigObject) && (abstractConfigValue instanceof SimpleConfigList)) {
            abstractConfigValue2 = DefaultTransformer.transform(abstractConfigValue2, ConfigValueType.LIST);
        } else if ((abstractConfigValue2 instanceof SimpleConfigList) && (abstractConfigValue instanceof ConfigObject)) {
            abstractConfigValue = DefaultTransformer.transform(abstractConfigValue, ConfigValueType.LIST);
        }
        boolean z = abstractConfigValue2 instanceof ConfigObject;
        if (!z || !(abstractConfigValue instanceof ConfigObject)) {
            boolean z2 = abstractConfigValue2 instanceof SimpleConfigList;
            if (z2 && (abstractConfigValue instanceof SimpleConfigList)) {
                abstractConfigValue2 = ((SimpleConfigList) abstractConfigValue2).concatenate((SimpleConfigList) abstractConfigValue);
            } else if ((!z2 && !z) || !isIgnoredWhitespace(abstractConfigValue)) {
                if ((abstractConfigValue2 instanceof ConfigConcatenation) || (abstractConfigValue instanceof ConfigConcatenation)) {
                    throw new ConfigException.BugOrBroken("unflattened ConfigConcatenation");
                } else if ((abstractConfigValue2 instanceof Unmergeable) || (abstractConfigValue instanceof Unmergeable)) {
                    abstractConfigValue2 = null;
                } else {
                    String transformToString = abstractConfigValue2.transformToString();
                    String transformToString2 = abstractConfigValue.transformToString();
                    if (transformToString == null || transformToString2 == null) {
                        SimpleConfigOrigin origin = abstractConfigValue2.origin();
                        throw new ConfigException.WrongType(origin, "Cannot concatenate object or list with a non-object-or-list, " + abstractConfigValue2 + " and " + abstractConfigValue + " are not compatible");
                    }
                    ConfigOrigin mergeOrigins = SimpleConfigOrigin.mergeOrigins(abstractConfigValue2.origin(), abstractConfigValue.origin());
                    abstractConfigValue2 = new ConfigString.Quoted(mergeOrigins, transformToString + transformToString2);
                }
            }
        } else {
            abstractConfigValue2 = abstractConfigValue.withFallback((ConfigMergeable) abstractConfigValue2);
        }
        if (abstractConfigValue2 == null) {
            arrayList.add(abstractConfigValue);
            return;
        }
        arrayList.remove(arrayList.size() - 1);
        arrayList.add(abstractConfigValue2);
    }

    static List<AbstractConfigValue> consolidate(List<AbstractConfigValue> list) {
        if (list.size() < 2) {
            return list;
        }
        ArrayList<AbstractConfigValue> arrayList = new ArrayList<>(list.size());
        for (AbstractConfigValue next : list) {
            if (next instanceof ConfigConcatenation) {
                arrayList.addAll(((ConfigConcatenation) next).pieces);
            } else {
                arrayList.add(next);
            }
        }
        ArrayList arrayList2 = new ArrayList(arrayList.size());
        for (AbstractConfigValue abstractConfigValue : arrayList) {
            if (arrayList2.isEmpty()) {
                arrayList2.add(abstractConfigValue);
            } else {
                join(arrayList2, abstractConfigValue);
            }
        }
        return arrayList2;
    }

    static AbstractConfigValue concatenate(List<AbstractConfigValue> list) {
        List<AbstractConfigValue> consolidate = consolidate(list);
        if (consolidate.isEmpty()) {
            return null;
        }
        if (consolidate.size() == 1) {
            return consolidate.get(0);
        }
        return new ConfigConcatenation(SimpleConfigOrigin.mergeOrigins((List<? extends AbstractConfigValue>) consolidate), consolidate);
    }

    /* access modifiers changed from: package-private */
    public ResolveResult<? extends AbstractConfigValue> resolveSubstitutions(ResolveContext resolveContext, ResolveSource resolveSource) throws AbstractConfigValue.NotPossibleToResolve {
        if (ConfigImpl.traceSubstitutionsEnabled()) {
            int depth = resolveContext.depth();
            int i = depth + 2;
            ConfigImpl.trace(depth + 1, "concatenation has " + this.pieces.size() + " pieces:");
            int i2 = 0;
            for (AbstractConfigValue abstractConfigValue : this.pieces) {
                ConfigImpl.trace(i, i2 + ": " + abstractConfigValue);
                i2++;
            }
        }
        ArrayList arrayList = new ArrayList(this.pieces.size());
        ResolveContext resolveContext2 = resolveContext;
        for (AbstractConfigValue resolve : this.pieces) {
            Path restrictToChild = resolveContext2.restrictToChild();
            ResolveResult<? extends AbstractConfigValue> resolve2 = resolveContext2.unrestricted().resolve(resolve, resolveSource);
            V v = resolve2.value;
            resolveContext2 = resolve2.context.restrict(restrictToChild);
            if (ConfigImpl.traceSubstitutionsEnabled()) {
                int depth2 = resolveContext.depth();
                ConfigImpl.trace(depth2, "resolved concat piece to " + v);
            }
            if (v != null) {
                arrayList.add(v);
            }
        }
        List<AbstractConfigValue> consolidate = consolidate(arrayList);
        if (consolidate.size() > 1 && resolveContext.options().getAllowUnresolved()) {
            return ResolveResult.make(resolveContext2, new ConfigConcatenation(origin(), consolidate));
        }
        if (consolidate.isEmpty()) {
            return ResolveResult.make(resolveContext2, null);
        }
        if (consolidate.size() == 1) {
            return ResolveResult.make(resolveContext2, consolidate.get(0));
        }
        throw new ConfigException.BugOrBroken("Bug in the library; resolved list was joined to too many values: " + consolidate);
    }

    /* access modifiers changed from: package-private */
    public ResolveStatus resolveStatus() {
        return ResolveStatus.UNRESOLVED;
    }

    public ConfigConcatenation replaceChild(AbstractConfigValue abstractConfigValue, AbstractConfigValue abstractConfigValue2) {
        List<AbstractConfigValue> replaceChildInList = replaceChildInList(this.pieces, abstractConfigValue, abstractConfigValue2);
        if (replaceChildInList == null) {
            return null;
        }
        return new ConfigConcatenation(origin(), replaceChildInList);
    }

    public boolean hasDescendant(AbstractConfigValue abstractConfigValue) {
        return hasDescendantInList(this.pieces, abstractConfigValue);
    }

    /* access modifiers changed from: package-private */
    public ConfigConcatenation relativized(Path path) {
        ArrayList arrayList = new ArrayList();
        for (AbstractConfigValue relativized : this.pieces) {
            arrayList.add(relativized.relativized(path));
        }
        return new ConfigConcatenation(origin(), arrayList);
    }

    /* access modifiers changed from: protected */
    public boolean canEqual(Object obj) {
        return obj instanceof ConfigConcatenation;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ConfigConcatenation) || !canEqual(obj) || !this.pieces.equals(((ConfigConcatenation) obj).pieces)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.pieces.hashCode();
    }

    /* access modifiers changed from: protected */
    public void render(StringBuilder sb, int i, boolean z, ConfigRenderOptions configRenderOptions) {
        for (AbstractConfigValue render : this.pieces) {
            render.render(sb, i, z, configRenderOptions);
        }
    }
}
