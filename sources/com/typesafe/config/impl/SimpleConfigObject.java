package com.typesafe.config.impl;

import com.typesafe.config.ConfigException;
import com.typesafe.config.ConfigMergeable;
import com.typesafe.config.ConfigObject;
import com.typesafe.config.ConfigOrigin;
import com.typesafe.config.ConfigRenderOptions;
import com.typesafe.config.ConfigValue;
import com.typesafe.config.impl.AbstractConfigValue;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.fusesource.jansi.AnsiRenderer;

final class SimpleConfigObject extends AbstractConfigObject implements Serializable {
    private static final String EMPTY_NAME = "empty config";
    private static final SimpleConfigObject emptyInstance = empty(SimpleConfigOrigin.newSimple(EMPTY_NAME));
    private static final long serialVersionUID = 2;
    private final boolean ignoresFallbacks;
    private final boolean resolved;
    private final Map<String, AbstractConfigValue> value;

    SimpleConfigObject(ConfigOrigin configOrigin, Map<String, AbstractConfigValue> map, ResolveStatus resolveStatus, boolean z) {
        super(configOrigin);
        if (map != null) {
            this.value = map;
            this.resolved = resolveStatus == ResolveStatus.RESOLVED;
            this.ignoresFallbacks = z;
            if (resolveStatus != ResolveStatus.fromValues(map.values())) {
                throw new ConfigException.BugOrBroken("Wrong resolved status on " + this);
            }
            return;
        }
        throw new ConfigException.BugOrBroken("creating config object with null map");
    }

    SimpleConfigObject(ConfigOrigin configOrigin, Map<String, AbstractConfigValue> map) {
        this(configOrigin, map, ResolveStatus.fromValues(map.values()), false);
    }

    public SimpleConfigObject withOnlyKey(String str) {
        return withOnlyPath(Path.newKey(str));
    }

    public SimpleConfigObject withoutKey(String str) {
        return withoutPath(Path.newKey(str));
    }

    /* access modifiers changed from: protected */
    public SimpleConfigObject withOnlyPathOrNull(Path path) {
        String first = path.first();
        Path remainder = path.remainder();
        AbstractConfigValue abstractConfigValue = this.value.get(first);
        if (remainder != null) {
            abstractConfigValue = (abstractConfigValue == null || !(abstractConfigValue instanceof AbstractConfigObject)) ? null : ((AbstractConfigObject) abstractConfigValue).withOnlyPathOrNull(remainder);
        }
        if (abstractConfigValue == null) {
            return null;
        }
        return new SimpleConfigObject(origin(), Collections.singletonMap(first, abstractConfigValue), abstractConfigValue.resolveStatus(), this.ignoresFallbacks);
    }

    /* access modifiers changed from: package-private */
    public SimpleConfigObject withOnlyPath(Path path) {
        SimpleConfigObject withOnlyPathOrNull = withOnlyPathOrNull(path);
        return withOnlyPathOrNull == null ? new SimpleConfigObject(origin(), Collections.emptyMap(), ResolveStatus.RESOLVED, this.ignoresFallbacks) : withOnlyPathOrNull;
    }

    /* access modifiers changed from: package-private */
    public SimpleConfigObject withoutPath(Path path) {
        String first = path.first();
        Path remainder = path.remainder();
        AbstractConfigValue abstractConfigValue = this.value.get(first);
        if (abstractConfigValue != null && remainder != null && (abstractConfigValue instanceof AbstractConfigObject)) {
            AbstractConfigObject withoutPath = ((AbstractConfigObject) abstractConfigValue).withoutPath(remainder);
            HashMap hashMap = new HashMap(this.value);
            hashMap.put(first, withoutPath);
            return new SimpleConfigObject(origin(), hashMap, ResolveStatus.fromValues(hashMap.values()), this.ignoresFallbacks);
        } else if (remainder != null || abstractConfigValue == null) {
            return this;
        } else {
            HashMap hashMap2 = new HashMap(this.value.size() - 1);
            for (Map.Entry next : this.value.entrySet()) {
                if (!((String) next.getKey()).equals(first)) {
                    hashMap2.put(next.getKey(), next.getValue());
                }
            }
            return new SimpleConfigObject(origin(), hashMap2, ResolveStatus.fromValues(hashMap2.values()), this.ignoresFallbacks);
        }
    }

    public SimpleConfigObject withValue(String str, ConfigValue configValue) {
        Map map;
        if (configValue != null) {
            if (this.value.isEmpty()) {
                map = Collections.singletonMap(str, (AbstractConfigValue) configValue);
            } else {
                HashMap hashMap = new HashMap(this.value);
                hashMap.put(str, (AbstractConfigValue) configValue);
                map = hashMap;
            }
            return new SimpleConfigObject(origin(), map, ResolveStatus.fromValues(map.values()), this.ignoresFallbacks);
        }
        throw new ConfigException.BugOrBroken("Trying to store null ConfigValue in a ConfigObject");
    }

    /* access modifiers changed from: package-private */
    public SimpleConfigObject withValue(Path path, ConfigValue configValue) {
        String first = path.first();
        Path remainder = path.remainder();
        if (remainder == null) {
            return withValue(first, configValue);
        }
        AbstractConfigValue abstractConfigValue = this.value.get(first);
        if (abstractConfigValue != null && (abstractConfigValue instanceof AbstractConfigObject)) {
            return withValue(first, (ConfigValue) ((AbstractConfigObject) abstractConfigValue).withValue(remainder, configValue));
        }
        return withValue(first, (ConfigValue) ((AbstractConfigValue) configValue).atPath(SimpleConfigOrigin.newSimple("withValue(" + remainder.render() + ")"), remainder).root());
    }

    /* access modifiers changed from: protected */
    public AbstractConfigValue attemptPeekWithPartialResolve(String str) {
        return this.value.get(str);
    }

    private SimpleConfigObject newCopy(ResolveStatus resolveStatus, ConfigOrigin configOrigin, boolean z) {
        return new SimpleConfigObject(configOrigin, this.value, resolveStatus, z);
    }

    /* access modifiers changed from: protected */
    public SimpleConfigObject newCopy(ResolveStatus resolveStatus, ConfigOrigin configOrigin) {
        return newCopy(resolveStatus, configOrigin, this.ignoresFallbacks);
    }

    /* access modifiers changed from: protected */
    public SimpleConfigObject withFallbacksIgnored() {
        if (this.ignoresFallbacks) {
            return this;
        }
        return newCopy(resolveStatus(), origin(), true);
    }

    /* access modifiers changed from: package-private */
    public ResolveStatus resolveStatus() {
        return ResolveStatus.fromBoolean(this.resolved);
    }

    public SimpleConfigObject replaceChild(AbstractConfigValue abstractConfigValue, AbstractConfigValue abstractConfigValue2) {
        HashMap hashMap = new HashMap(this.value);
        for (Map.Entry entry : hashMap.entrySet()) {
            if (entry.getValue() == abstractConfigValue) {
                if (abstractConfigValue2 != null) {
                    entry.setValue(abstractConfigValue2);
                } else {
                    hashMap.remove(entry.getKey());
                }
                return new SimpleConfigObject(origin(), hashMap, ResolveStatus.fromValues(hashMap.values()), this.ignoresFallbacks);
            }
        }
        throw new ConfigException.BugOrBroken("SimpleConfigObject.replaceChild did not find " + abstractConfigValue + " in " + this);
    }

    public boolean hasDescendant(AbstractConfigValue abstractConfigValue) {
        for (AbstractConfigValue abstractConfigValue2 : this.value.values()) {
            if (abstractConfigValue2 == abstractConfigValue) {
                return true;
            }
        }
        for (AbstractConfigValue next : this.value.values()) {
            if ((next instanceof Container) && ((Container) next).hasDescendant(abstractConfigValue)) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean ignoresFallbacks() {
        return this.ignoresFallbacks;
    }

    public Map<String, Object> unwrapped() {
        HashMap hashMap = new HashMap();
        for (Map.Entry next : this.value.entrySet()) {
            hashMap.put(next.getKey(), ((AbstractConfigValue) next.getValue()).unwrapped());
        }
        return hashMap;
    }

    /* access modifiers changed from: protected */
    public SimpleConfigObject mergedWithObject(AbstractConfigObject abstractConfigObject) {
        requireNotIgnoringFallbacks();
        if (abstractConfigObject instanceof SimpleConfigObject) {
            SimpleConfigObject simpleConfigObject = (SimpleConfigObject) abstractConfigObject;
            HashMap hashMap = new HashMap();
            HashSet<String> hashSet = new HashSet<>();
            hashSet.addAll(keySet());
            hashSet.addAll(simpleConfigObject.keySet());
            boolean z = true;
            boolean z2 = false;
            for (String str : hashSet) {
                AbstractConfigValue abstractConfigValue = this.value.get(str);
                AbstractConfigValue abstractConfigValue2 = simpleConfigObject.value.get(str);
                if (abstractConfigValue != null) {
                    abstractConfigValue2 = abstractConfigValue2 == null ? abstractConfigValue : abstractConfigValue.withFallback((ConfigMergeable) abstractConfigValue2);
                }
                hashMap.put(str, abstractConfigValue2);
                if (abstractConfigValue != abstractConfigValue2) {
                    z2 = true;
                }
                if (abstractConfigValue2.resolveStatus() == ResolveStatus.UNRESOLVED) {
                    z = false;
                }
            }
            ResolveStatus fromBoolean = ResolveStatus.fromBoolean(z);
            boolean ignoresFallbacks2 = simpleConfigObject.ignoresFallbacks();
            if (z2) {
                return new SimpleConfigObject(mergeOrigins(this, simpleConfigObject), hashMap, fromBoolean, ignoresFallbacks2);
            } else if (fromBoolean == resolveStatus() && ignoresFallbacks2 == ignoresFallbacks()) {
                return this;
            } else {
                return newCopy(fromBoolean, origin(), ignoresFallbacks2);
            }
        } else {
            throw new ConfigException.BugOrBroken("should not be reached (merging non-SimpleConfigObject)");
        }
    }

    private SimpleConfigObject modify(AbstractConfigValue.NoExceptionsModifier noExceptionsModifier) {
        try {
            return modifyMayThrow(noExceptionsModifier);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e2) {
            throw new ConfigException.BugOrBroken("unexpected checked exception", e2);
        }
    }

    private SimpleConfigObject modifyMayThrow(AbstractConfigValue.Modifier modifier) throws Exception {
        HashMap hashMap = null;
        for (String next : keySet()) {
            AbstractConfigValue abstractConfigValue = this.value.get(next);
            AbstractConfigValue modifyChildMayThrow = modifier.modifyChildMayThrow(next, abstractConfigValue);
            if (modifyChildMayThrow != abstractConfigValue) {
                if (hashMap == null) {
                    hashMap = new HashMap();
                }
                hashMap.put(next, modifyChildMayThrow);
            }
        }
        if (hashMap == null) {
            return this;
        }
        HashMap hashMap2 = new HashMap();
        boolean z = false;
        for (String next2 : keySet()) {
            if (hashMap.containsKey(next2)) {
                AbstractConfigValue abstractConfigValue2 = (AbstractConfigValue) hashMap.get(next2);
                if (abstractConfigValue2 != null) {
                    hashMap2.put(next2, abstractConfigValue2);
                    if (abstractConfigValue2.resolveStatus() != ResolveStatus.UNRESOLVED) {
                    }
                }
            } else {
                AbstractConfigValue abstractConfigValue3 = this.value.get(next2);
                hashMap2.put(next2, abstractConfigValue3);
                if (abstractConfigValue3.resolveStatus() != ResolveStatus.UNRESOLVED) {
                }
            }
            z = true;
        }
        return new SimpleConfigObject(origin(), hashMap2, z ? ResolveStatus.UNRESOLVED : ResolveStatus.RESOLVED, ignoresFallbacks());
    }

    private static final class ResolveModifier implements AbstractConfigValue.Modifier {
        ResolveContext context;
        final Path originalRestrict;
        final ResolveSource source;

        ResolveModifier(ResolveContext resolveContext, ResolveSource resolveSource) {
            this.context = resolveContext;
            this.source = resolveSource;
            this.originalRestrict = resolveContext.restrictToChild();
        }

        public AbstractConfigValue modifyChildMayThrow(String str, AbstractConfigValue abstractConfigValue) throws AbstractConfigValue.NotPossibleToResolve {
            Path remainder;
            if (!this.context.isRestrictedToChild()) {
                ResolveResult<? extends AbstractConfigValue> resolve = this.context.unrestricted().resolve(abstractConfigValue, this.source);
                this.context = resolve.context.unrestricted().restrict(this.originalRestrict);
                return resolve.value;
            } else if (!str.equals(this.context.restrictToChild().first()) || (remainder = this.context.restrictToChild().remainder()) == null) {
                return abstractConfigValue;
            } else {
                ResolveResult<? extends AbstractConfigValue> resolve2 = this.context.restrict(remainder).resolve(abstractConfigValue, this.source);
                this.context = resolve2.context.unrestricted().restrict(this.originalRestrict);
                return resolve2.value;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public ResolveResult<? extends AbstractConfigObject> resolveSubstitutions(ResolveContext resolveContext, ResolveSource resolveSource) throws AbstractConfigValue.NotPossibleToResolve {
        if (resolveStatus() == ResolveStatus.RESOLVED) {
            return ResolveResult.make(resolveContext, this);
        }
        try {
            ResolveModifier resolveModifier = new ResolveModifier(resolveContext, resolveSource.pushParent(this));
            return ResolveResult.make(resolveModifier.context, modifyMayThrow(resolveModifier)).asObjectResult();
        } catch (AbstractConfigValue.NotPossibleToResolve e) {
            throw e;
        } catch (RuntimeException e2) {
            throw e2;
        } catch (Exception e3) {
            throw new ConfigException.BugOrBroken("unexpected checked exception", e3);
        }
    }

    /* access modifiers changed from: package-private */
    public SimpleConfigObject relativized(final Path path) {
        return modify(new AbstractConfigValue.NoExceptionsModifier() {
            public AbstractConfigValue modifyChild(String str, AbstractConfigValue abstractConfigValue) {
                return abstractConfigValue.relativized(path);
            }
        });
    }

    private static final class RenderComparator implements Comparator<String>, Serializable {
        private static final long serialVersionUID = 1;

        private RenderComparator() {
        }

        private static boolean isAllDigits(String str) {
            int length = str.length();
            if (length == 0) {
                return false;
            }
            for (int i = 0; i < length; i++) {
                if (!Character.isDigit(str.charAt(i))) {
                    return false;
                }
            }
            return true;
        }

        public int compare(String str, String str2) {
            boolean isAllDigits = isAllDigits(str);
            boolean isAllDigits2 = isAllDigits(str2);
            if (isAllDigits && isAllDigits2) {
                return new BigInteger(str).compareTo(new BigInteger(str2));
            }
            if (isAllDigits) {
                return -1;
            }
            if (isAllDigits2) {
                return 1;
            }
            return str.compareTo(str2);
        }
    }

    /* access modifiers changed from: protected */
    public void render(StringBuilder sb, int i, boolean z, ConfigRenderOptions configRenderOptions) {
        int i2;
        StringBuilder sb2 = sb;
        int i3 = i;
        ConfigRenderOptions configRenderOptions2 = configRenderOptions;
        if (isEmpty()) {
            sb2.append("{}");
        } else {
            boolean z2 = configRenderOptions.getJson() || !z;
            if (z2) {
                int i4 = i3 + 1;
                sb2.append("{");
                if (configRenderOptions.getFormatted()) {
                    sb2.append(10);
                }
                i2 = i4;
            } else {
                i2 = i3;
            }
            String[] strArr = (String[]) keySet().toArray(new String[size()]);
            Arrays.sort(strArr, new RenderComparator());
            int length = strArr.length;
            int i5 = 0;
            int i6 = 0;
            while (i6 < length) {
                String str = strArr[i6];
                AbstractConfigValue abstractConfigValue = this.value.get(str);
                if (configRenderOptions.getOriginComments()) {
                    String[] split = abstractConfigValue.origin().description().split("\n");
                    int length2 = split.length;
                    int i7 = 0;
                    while (i7 < length2) {
                        String str2 = split[i7];
                        String[] strArr2 = split;
                        indent(sb2, i3 + 1, configRenderOptions2);
                        sb2.append('#');
                        if (!str2.isEmpty()) {
                            sb2.append(' ');
                        }
                        sb2.append(str2);
                        sb2.append("\n");
                        i7++;
                        split = strArr2;
                    }
                }
                if (configRenderOptions.getComments()) {
                    for (String next : abstractConfigValue.origin().comments()) {
                        indent(sb2, i2, configRenderOptions2);
                        sb2.append("#");
                        if (!next.startsWith(AnsiRenderer.CODE_TEXT_SEPARATOR)) {
                            sb2.append(' ');
                        }
                        sb2.append(next);
                        sb2.append("\n");
                    }
                }
                indent(sb2, i2, configRenderOptions2);
                int i8 = i6;
                abstractConfigValue.render(sb, i2, false, str, configRenderOptions);
                if (configRenderOptions.getFormatted()) {
                    if (configRenderOptions.getJson()) {
                        sb2.append(AnsiRenderer.CODE_LIST_SEPARATOR);
                        i5 = 2;
                    } else {
                        i5 = 1;
                    }
                    sb2.append(10);
                } else {
                    sb2.append(AnsiRenderer.CODE_LIST_SEPARATOR);
                    i5 = 1;
                }
                i6 = i8 + 1;
            }
            sb2.setLength(sb.length() - i5);
            if (z2) {
                if (configRenderOptions.getFormatted()) {
                    sb2.append(10);
                    if (z2) {
                        indent(sb2, i3, configRenderOptions2);
                    }
                }
                sb2.append("}");
            }
        }
        if (z && configRenderOptions.getFormatted()) {
            sb2.append(10);
        }
    }

    public AbstractConfigValue get(Object obj) {
        return this.value.get(obj);
    }

    private static boolean mapEquals(Map<String, ConfigValue> map, Map<String, ConfigValue> map2) {
        if (map == map2) {
            return true;
        }
        Set<String> keySet = map.keySet();
        if (!keySet.equals(map2.keySet())) {
            return false;
        }
        for (String next : keySet) {
            if (!map.get(next).equals(map2.get(next))) {
                return false;
            }
        }
        return true;
    }

    private static int mapHash(Map<String, ConfigValue> map) {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.addAll(map.keySet());
        Collections.sort(arrayList);
        int i = 0;
        for (String str : arrayList) {
            i += map.get(str).hashCode();
        }
        return ((arrayList.hashCode() + 41) * 41) + i;
    }

    /* access modifiers changed from: protected */
    public boolean canEqual(Object obj) {
        return obj instanceof ConfigObject;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ConfigObject) || !canEqual(obj) || !mapEquals(this, (ConfigObject) obj)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return mapHash(this);
    }

    public boolean containsKey(Object obj) {
        return this.value.containsKey(obj);
    }

    public Set<String> keySet() {
        return this.value.keySet();
    }

    public boolean containsValue(Object obj) {
        return this.value.containsValue(obj);
    }

    public Set<Map.Entry<String, ConfigValue>> entrySet() {
        HashSet hashSet = new HashSet();
        for (Map.Entry next : this.value.entrySet()) {
            hashSet.add(new AbstractMap.SimpleImmutableEntry(next.getKey(), next.getValue()));
        }
        return hashSet;
    }

    public boolean isEmpty() {
        return this.value.isEmpty();
    }

    public int size() {
        return this.value.size();
    }

    public Collection<ConfigValue> values() {
        return new HashSet(this.value.values());
    }

    static final SimpleConfigObject empty() {
        return emptyInstance;
    }

    static final SimpleConfigObject empty(ConfigOrigin configOrigin) {
        if (configOrigin == null) {
            return empty();
        }
        return new SimpleConfigObject(configOrigin, Collections.emptyMap());
    }

    static final SimpleConfigObject emptyMissing(ConfigOrigin configOrigin) {
        return new SimpleConfigObject(SimpleConfigOrigin.newSimple(configOrigin.description() + " (not found)"), Collections.emptyMap());
    }

    private Object writeReplace() throws ObjectStreamException {
        return new SerializedConfigValue((ConfigValue) this);
    }
}
