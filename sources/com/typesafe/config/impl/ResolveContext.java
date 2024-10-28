package com.typesafe.config.impl;

import com.typesafe.config.ConfigException;
import com.typesafe.config.ConfigResolveOptions;
import com.typesafe.config.impl.AbstractConfigValue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Set;

final class ResolveContext {
    private final Set<AbstractConfigValue> cycleMarkers;
    private final ResolveMemos memos;
    private final ConfigResolveOptions options;
    private final List<AbstractConfigValue> resolveStack;
    private final Path restrictToChild;

    ResolveContext(ResolveMemos resolveMemos, ConfigResolveOptions configResolveOptions, Path path, List<AbstractConfigValue> list, Set<AbstractConfigValue> set) {
        this.memos = resolveMemos;
        this.options = configResolveOptions;
        this.restrictToChild = path;
        this.resolveStack = list;
        this.cycleMarkers = set;
    }

    private static Set<AbstractConfigValue> newCycleMarkers() {
        return Collections.newSetFromMap(new IdentityHashMap());
    }

    ResolveContext(ConfigResolveOptions configResolveOptions, Path path) {
        this(new ResolveMemos(), configResolveOptions, path, new ArrayList(), newCycleMarkers());
        if (ConfigImpl.traceSubstitutionsEnabled()) {
            int depth = depth();
            ConfigImpl.trace(depth, "ResolveContext restrict to child " + path);
        }
    }

    /* access modifiers changed from: package-private */
    public ResolveContext addCycleMarker(AbstractConfigValue abstractConfigValue) {
        if (ConfigImpl.traceSubstitutionsEnabled()) {
            int depth = depth();
            ConfigImpl.trace(depth, "++ Cycle marker " + abstractConfigValue + "@" + System.identityHashCode(abstractConfigValue));
        }
        if (!this.cycleMarkers.contains(abstractConfigValue)) {
            Set<AbstractConfigValue> newCycleMarkers = newCycleMarkers();
            newCycleMarkers.addAll(this.cycleMarkers);
            newCycleMarkers.add(abstractConfigValue);
            return new ResolveContext(this.memos, this.options, this.restrictToChild, this.resolveStack, newCycleMarkers);
        }
        throw new ConfigException.BugOrBroken("Added cycle marker twice " + abstractConfigValue);
    }

    /* access modifiers changed from: package-private */
    public ResolveContext removeCycleMarker(AbstractConfigValue abstractConfigValue) {
        if (ConfigImpl.traceSubstitutionsEnabled()) {
            int depth = depth();
            ConfigImpl.trace(depth, "-- Cycle marker " + abstractConfigValue + "@" + System.identityHashCode(abstractConfigValue));
        }
        Set<AbstractConfigValue> newCycleMarkers = newCycleMarkers();
        newCycleMarkers.addAll(this.cycleMarkers);
        newCycleMarkers.remove(abstractConfigValue);
        return new ResolveContext(this.memos, this.options, this.restrictToChild, this.resolveStack, newCycleMarkers);
    }

    private ResolveContext memoize(MemoKey memoKey, AbstractConfigValue abstractConfigValue) {
        return new ResolveContext(this.memos.put(memoKey, abstractConfigValue), this.options, this.restrictToChild, this.resolveStack, this.cycleMarkers);
    }

    /* access modifiers changed from: package-private */
    public ConfigResolveOptions options() {
        return this.options;
    }

    /* access modifiers changed from: package-private */
    public boolean isRestrictedToChild() {
        return this.restrictToChild != null;
    }

    /* access modifiers changed from: package-private */
    public Path restrictToChild() {
        return this.restrictToChild;
    }

    /* access modifiers changed from: package-private */
    public ResolveContext restrict(Path path) {
        if (path == this.restrictToChild) {
            return this;
        }
        return new ResolveContext(this.memos, this.options, path, this.resolveStack, this.cycleMarkers);
    }

    /* access modifiers changed from: package-private */
    public ResolveContext unrestricted() {
        return restrict((Path) null);
    }

    /* access modifiers changed from: package-private */
    public String traceString() {
        StringBuilder sb = new StringBuilder();
        for (AbstractConfigValue next : this.resolveStack) {
            if (next instanceof ConfigReference) {
                sb.append(((ConfigReference) next).expression().toString());
                sb.append(", ");
            }
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 2);
        }
        return sb.toString();
    }

    private ResolveContext pushTrace(AbstractConfigValue abstractConfigValue) {
        if (ConfigImpl.traceSubstitutionsEnabled()) {
            int depth = depth();
            ConfigImpl.trace(depth, "pushing trace " + abstractConfigValue);
        }
        ArrayList arrayList = new ArrayList(this.resolveStack);
        arrayList.add(abstractConfigValue);
        return new ResolveContext(this.memos, this.options, this.restrictToChild, arrayList, this.cycleMarkers);
    }

    /* access modifiers changed from: package-private */
    public ResolveContext popTrace() {
        ArrayList arrayList = new ArrayList(this.resolveStack);
        AbstractConfigValue abstractConfigValue = (AbstractConfigValue) arrayList.remove(this.resolveStack.size() - 1);
        if (ConfigImpl.traceSubstitutionsEnabled()) {
            ConfigImpl.trace(depth() - 1, "popped trace " + abstractConfigValue);
        }
        return new ResolveContext(this.memos, this.options, this.restrictToChild, arrayList, this.cycleMarkers);
    }

    /* access modifiers changed from: package-private */
    public int depth() {
        if (this.resolveStack.size() <= 30) {
            return this.resolveStack.size();
        }
        throw new ConfigException.BugOrBroken("resolve getting too deep");
    }

    /* access modifiers changed from: package-private */
    public ResolveResult<? extends AbstractConfigValue> resolve(AbstractConfigValue abstractConfigValue, ResolveSource resolveSource) throws AbstractConfigValue.NotPossibleToResolve {
        if (ConfigImpl.traceSubstitutionsEnabled()) {
            int depth = depth();
            ConfigImpl.trace(depth, "resolving " + abstractConfigValue + " restrictToChild=" + this.restrictToChild + " in " + resolveSource);
        }
        return pushTrace(abstractConfigValue).realResolve(abstractConfigValue, resolveSource).popTrace();
    }

    private ResolveResult<? extends AbstractConfigValue> realResolve(AbstractConfigValue abstractConfigValue, ResolveSource resolveSource) throws AbstractConfigValue.NotPossibleToResolve {
        ResolveContext resolveContext;
        MemoKey memoKey = null;
        MemoKey memoKey2 = new MemoKey(abstractConfigValue, (Path) null);
        AbstractConfigValue abstractConfigValue2 = this.memos.get(memoKey2);
        if (abstractConfigValue2 == null && isRestrictedToChild()) {
            memoKey = new MemoKey(abstractConfigValue, restrictToChild());
            abstractConfigValue2 = this.memos.get(memoKey);
        }
        if (abstractConfigValue2 != null) {
            if (ConfigImpl.traceSubstitutionsEnabled()) {
                int depth = depth();
                ConfigImpl.trace(depth, "using cached resolution " + abstractConfigValue2 + " for " + abstractConfigValue + " restrictToChild " + restrictToChild());
            }
            return ResolveResult.make(this, abstractConfigValue2);
        }
        if (ConfigImpl.traceSubstitutionsEnabled()) {
            int depth2 = depth();
            ConfigImpl.trace(depth2, "not found in cache, resolving " + abstractConfigValue + "@" + System.identityHashCode(abstractConfigValue));
        }
        if (this.cycleMarkers.contains(abstractConfigValue)) {
            if (ConfigImpl.traceSubstitutionsEnabled()) {
                int depth3 = depth();
                ConfigImpl.trace(depth3, "Cycle detected, can't resolve; " + abstractConfigValue + "@" + System.identityHashCode(abstractConfigValue));
            }
            throw new AbstractConfigValue.NotPossibleToResolve(this);
        }
        ResolveResult<? extends AbstractConfigValue> resolveSubstitutions = abstractConfigValue.resolveSubstitutions(this, resolveSource);
        V v = resolveSubstitutions.value;
        if (ConfigImpl.traceSubstitutionsEnabled()) {
            int depth4 = depth();
            ConfigImpl.trace(depth4, "resolved to " + v + "@" + System.identityHashCode(v) + " from " + abstractConfigValue + "@" + System.identityHashCode(v));
        }
        ResolveContext resolveContext2 = resolveSubstitutions.context;
        if (v == null || v.resolveStatus() == ResolveStatus.RESOLVED) {
            if (ConfigImpl.traceSubstitutionsEnabled()) {
                int depth5 = depth();
                ConfigImpl.trace(depth5, "caching " + memoKey2 + " result " + v);
            }
            resolveContext = resolveContext2.memoize(memoKey2, v);
        } else if (isRestrictedToChild()) {
            if (memoKey != null) {
                if (ConfigImpl.traceSubstitutionsEnabled()) {
                    int depth6 = depth();
                    ConfigImpl.trace(depth6, "caching " + memoKey + " result " + v);
                }
                resolveContext = resolveContext2.memoize(memoKey, v);
            } else {
                throw new ConfigException.BugOrBroken("restrictedKey should not be null here");
            }
        } else if (options().getAllowUnresolved()) {
            if (ConfigImpl.traceSubstitutionsEnabled()) {
                int depth7 = depth();
                ConfigImpl.trace(depth7, "caching " + memoKey2 + " result " + v);
            }
            resolveContext = resolveContext2.memoize(memoKey2, v);
        } else {
            throw new ConfigException.BugOrBroken("resolveSubstitutions() did not give us a resolved object");
        }
        return ResolveResult.make(resolveContext, v);
    }

    static AbstractConfigValue resolve(AbstractConfigValue abstractConfigValue, AbstractConfigObject abstractConfigObject, ConfigResolveOptions configResolveOptions) {
        try {
            return new ResolveContext(configResolveOptions, (Path) null).resolve(abstractConfigValue, new ResolveSource(abstractConfigObject)).value;
        } catch (AbstractConfigValue.NotPossibleToResolve e) {
            throw new ConfigException.BugOrBroken("NotPossibleToResolve was thrown from an outermost resolve", e);
        }
    }
}
