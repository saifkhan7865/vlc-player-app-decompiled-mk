package com.typesafe.config.impl;

import com.typesafe.config.ConfigException;
import com.typesafe.config.ConfigMergeable;
import com.typesafe.config.ConfigOrigin;
import com.typesafe.config.ConfigRenderOptions;
import com.typesafe.config.ConfigValueType;
import com.typesafe.config.impl.AbstractConfigValue;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.fusesource.jansi.AnsiRenderer;

final class ConfigDelayedMerge extends AbstractConfigValue implements Unmergeable, ReplaceableMergeStack {
    private final List<AbstractConfigValue> stack;

    /* JADX WARNING: Removed duplicated region for block: B:5:0x0015  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    ConfigDelayedMerge(com.typesafe.config.ConfigOrigin r2, java.util.List<com.typesafe.config.impl.AbstractConfigValue> r3) {
        /*
            r1 = this;
            r1.<init>(r2)
            r1.stack = r3
            boolean r2 = r3.isEmpty()
            if (r2 != 0) goto L_0x002d
            java.util.Iterator r2 = r3.iterator()
        L_0x000f:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x002c
            java.lang.Object r3 = r2.next()
            com.typesafe.config.impl.AbstractConfigValue r3 = (com.typesafe.config.impl.AbstractConfigValue) r3
            boolean r0 = r3 instanceof com.typesafe.config.impl.ConfigDelayedMerge
            if (r0 != 0) goto L_0x0024
            boolean r3 = r3 instanceof com.typesafe.config.impl.ConfigDelayedMergeObject
            if (r3 != 0) goto L_0x0024
            goto L_0x000f
        L_0x0024:
            com.typesafe.config.ConfigException$BugOrBroken r2 = new com.typesafe.config.ConfigException$BugOrBroken
            java.lang.String r3 = "placed nested DelayedMerge in a ConfigDelayedMerge, should have consolidated stack"
            r2.<init>(r3)
            throw r2
        L_0x002c:
            return
        L_0x002d:
            com.typesafe.config.ConfigException$BugOrBroken r2 = new com.typesafe.config.ConfigException$BugOrBroken
            java.lang.String r3 = "creating empty delayed merge value"
            r2.<init>(r3)
            goto L_0x0036
        L_0x0035:
            throw r2
        L_0x0036:
            goto L_0x0035
        */
        throw new UnsupportedOperationException("Method not decompiled: com.typesafe.config.impl.ConfigDelayedMerge.<init>(com.typesafe.config.ConfigOrigin, java.util.List):void");
    }

    public ConfigValueType valueType() {
        throw new ConfigException.NotResolved("called valueType() on value with unresolved substitutions, need to Config#resolve() first, see API docs");
    }

    public Object unwrapped() {
        throw new ConfigException.NotResolved("called unwrapped() on value with unresolved substitutions, need to Config#resolve() first, see API docs");
    }

    /* access modifiers changed from: package-private */
    public ResolveResult<? extends AbstractConfigValue> resolveSubstitutions(ResolveContext resolveContext, ResolveSource resolveSource) throws AbstractConfigValue.NotPossibleToResolve {
        return resolveSubstitutions(this, this.stack, resolveContext, resolveSource);
    }

    static ResolveResult<? extends AbstractConfigValue> resolveSubstitutions(ReplaceableMergeStack replaceableMergeStack, List<AbstractConfigValue> list, ResolveContext resolveContext, ResolveSource resolveSource) throws AbstractConfigValue.NotPossibleToResolve {
        ResolveSource resolveSource2;
        if (ConfigImpl.traceSubstitutionsEnabled()) {
            int depth = resolveContext.depth();
            ConfigImpl.trace(depth, "delayed merge stack has " + list.size() + " items:");
            int i = 0;
            for (AbstractConfigValue abstractConfigValue : list) {
                ConfigImpl.trace(resolveContext.depth() + 1, i + ": " + abstractConfigValue);
                i++;
            }
        }
        V v = null;
        ResolveContext resolveContext2 = resolveContext;
        int i2 = 0;
        for (AbstractConfigValue next : list) {
            if (!(next instanceof ReplaceableMergeStack)) {
                if (next instanceof Unmergeable) {
                    AbstractConfigValue makeReplacement = replaceableMergeStack.makeReplacement(resolveContext, i2 + 1);
                    if (ConfigImpl.traceSubstitutionsEnabled()) {
                        int depth2 = resolveContext2.depth();
                        ConfigImpl.trace(depth2, "remainder portion: " + makeReplacement);
                    }
                    if (ConfigImpl.traceSubstitutionsEnabled()) {
                        ConfigImpl.trace(resolveContext2.depth(), "building sourceForEnd");
                    }
                    ResolveSource replaceWithinCurrentParent = resolveSource.replaceWithinCurrentParent((AbstractConfigValue) replaceableMergeStack, makeReplacement);
                    if (ConfigImpl.traceSubstitutionsEnabled()) {
                        int depth3 = resolveContext2.depth();
                        ConfigImpl.trace(depth3, "  sourceForEnd before reset parents but after replace: " + replaceWithinCurrentParent);
                    }
                    resolveSource2 = replaceWithinCurrentParent.resetParents();
                } else {
                    if (ConfigImpl.traceSubstitutionsEnabled()) {
                        ConfigImpl.trace(resolveContext2.depth(), "will resolve end against the original source with parent pushed");
                    }
                    resolveSource2 = resolveSource.pushParent(replaceableMergeStack);
                }
                if (ConfigImpl.traceSubstitutionsEnabled()) {
                    int depth4 = resolveContext2.depth();
                    ConfigImpl.trace(depth4, "sourceForEnd      =" + resolveSource2);
                }
                if (ConfigImpl.traceSubstitutionsEnabled()) {
                    int depth5 = resolveContext2.depth();
                    StringBuilder sb = new StringBuilder("Resolving highest-priority item in delayed merge ");
                    sb.append(next);
                    sb.append(" against ");
                    sb.append(resolveSource2);
                    sb.append(" endWasRemoved=");
                    sb.append(resolveSource != resolveSource2);
                    ConfigImpl.trace(depth5, sb.toString());
                }
                ResolveResult<? extends AbstractConfigValue> resolve = resolveContext2.resolve(next, resolveSource2);
                V v2 = resolve.value;
                resolveContext2 = resolve.context;
                if (v2 != null) {
                    if (v == null) {
                        v = v2;
                    } else {
                        if (ConfigImpl.traceSubstitutionsEnabled()) {
                            ConfigImpl.trace(resolveContext2.depth() + 1, "merging " + v + " with fallback " + v2);
                        }
                        v = v.withFallback((ConfigMergeable) v2);
                    }
                }
                i2++;
                if (ConfigImpl.traceSubstitutionsEnabled()) {
                    int depth6 = resolveContext2.depth();
                    ConfigImpl.trace(depth6, "stack merged, yielding: " + v);
                }
            } else {
                throw new ConfigException.BugOrBroken("A delayed merge should not contain another one: " + replaceableMergeStack);
            }
        }
        return ResolveResult.make(resolveContext2, v);
    }

    public AbstractConfigValue makeReplacement(ResolveContext resolveContext, int i) {
        return makeReplacement(resolveContext, this.stack, i);
    }

    static AbstractConfigValue makeReplacement(ResolveContext resolveContext, List<AbstractConfigValue> list, int i) {
        List<AbstractConfigValue> subList = list.subList(i, list.size());
        AbstractConfigValue abstractConfigValue = null;
        if (subList.isEmpty()) {
            if (ConfigImpl.traceSubstitutionsEnabled()) {
                ConfigImpl.trace(resolveContext.depth(), "Nothing else in the merge stack, replacing with null");
            }
            return null;
        }
        for (AbstractConfigValue next : subList) {
            if (abstractConfigValue != null) {
                next = abstractConfigValue.withFallback((ConfigMergeable) next);
            }
            abstractConfigValue = next;
        }
        return abstractConfigValue;
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
        return new ConfigDelayedMerge(origin(), replaceChildInList);
    }

    public boolean hasDescendant(AbstractConfigValue abstractConfigValue) {
        return hasDescendantInList(this.stack, abstractConfigValue);
    }

    /* access modifiers changed from: package-private */
    public ConfigDelayedMerge relativized(Path path) {
        ArrayList arrayList = new ArrayList();
        for (AbstractConfigValue relativized : this.stack) {
            arrayList.add(relativized.relativized(path));
        }
        return new ConfigDelayedMerge(origin(), arrayList);
    }

    static boolean stackIgnoresFallbacks(List<AbstractConfigValue> list) {
        return list.get(list.size() - 1).ignoresFallbacks();
    }

    /* access modifiers changed from: protected */
    public boolean ignoresFallbacks() {
        return stackIgnoresFallbacks(this.stack);
    }

    /* access modifiers changed from: protected */
    public AbstractConfigValue newCopy(ConfigOrigin configOrigin) {
        return new ConfigDelayedMerge(configOrigin, this.stack);
    }

    /* access modifiers changed from: protected */
    public final ConfigDelayedMerge mergedWithTheUnmergeable(Unmergeable unmergeable) {
        return (ConfigDelayedMerge) mergedWithTheUnmergeable(this.stack, unmergeable);
    }

    /* access modifiers changed from: protected */
    public final ConfigDelayedMerge mergedWithObject(AbstractConfigObject abstractConfigObject) {
        return (ConfigDelayedMerge) mergedWithObject(this.stack, abstractConfigObject);
    }

    /* access modifiers changed from: protected */
    public ConfigDelayedMerge mergedWithNonObject(AbstractConfigValue abstractConfigValue) {
        return (ConfigDelayedMerge) mergedWithNonObject(this.stack, abstractConfigValue);
    }

    public Collection<AbstractConfigValue> unmergedValues() {
        return this.stack;
    }

    /* access modifiers changed from: protected */
    public boolean canEqual(Object obj) {
        return obj instanceof ConfigDelayedMerge;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ConfigDelayedMerge) || !canEqual(obj)) {
            return false;
        }
        List<AbstractConfigValue> list = this.stack;
        List<AbstractConfigValue> list2 = ((ConfigDelayedMerge) obj).stack;
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
        render(this.stack, sb, i, z, str, configRenderOptions);
    }

    /* access modifiers changed from: protected */
    public void render(StringBuilder sb, int i, boolean z, ConfigRenderOptions configRenderOptions) {
        render(sb, i, z, (String) null, configRenderOptions);
    }

    static void render(List<AbstractConfigValue> list, StringBuilder sb, int i, boolean z, String str, ConfigRenderOptions configRenderOptions) {
        boolean comments = configRenderOptions.getComments();
        if (comments) {
            sb.append("# unresolved merge of " + list.size() + " values follows (\n");
            if (str == null) {
                indent(sb, i, configRenderOptions);
                sb.append("# this unresolved merge will not be parseable because it's at the root of the object\n");
                indent(sb, i, configRenderOptions);
                sb.append("# the HOCON format has no way to list multiple root objects in a single file\n");
            }
        }
        ArrayList<AbstractConfigValue> arrayList = new ArrayList<>();
        arrayList.addAll(list);
        Collections.reverse(arrayList);
        int i2 = 0;
        for (AbstractConfigValue abstractConfigValue : arrayList) {
            if (comments) {
                indent(sb, i, configRenderOptions);
                if (str != null) {
                    sb.append("#     unmerged value " + i2 + " for key " + ConfigImplUtil.renderJsonString(str) + " from ");
                } else {
                    sb.append("#     unmerged value " + i2 + " from ");
                }
                i2++;
                sb.append(abstractConfigValue.origin().description());
                sb.append("\n");
                for (String append : abstractConfigValue.origin().comments()) {
                    indent(sb, i, configRenderOptions);
                    sb.append("# ");
                    sb.append(append);
                    sb.append("\n");
                }
            }
            indent(sb, i, configRenderOptions);
            if (str != null) {
                sb.append(ConfigImplUtil.renderJsonString(str));
                if (configRenderOptions.getFormatted()) {
                    sb.append(" : ");
                } else {
                    sb.append(":");
                }
            }
            abstractConfigValue.render(sb, i, z, configRenderOptions);
            sb.append(AnsiRenderer.CODE_LIST_SEPARATOR);
            if (configRenderOptions.getFormatted()) {
                sb.append(10);
            }
        }
        sb.setLength(sb.length() - 1);
        if (configRenderOptions.getFormatted()) {
            sb.setLength(sb.length() - 1);
            sb.append("\n");
        }
        if (comments) {
            indent(sb, i, configRenderOptions);
            sb.append("# ) end of unresolved merge\n");
        }
    }
}
