package com.typesafe.config.impl;

import com.typesafe.config.ConfigException;
import com.typesafe.config.ConfigList;
import com.typesafe.config.ConfigOrigin;
import com.typesafe.config.ConfigRenderOptions;
import com.typesafe.config.ConfigValue;
import com.typesafe.config.ConfigValueType;
import com.typesafe.config.impl.AbstractConfigValue;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import okhttp3.HttpUrl;
import org.fusesource.jansi.AnsiRenderer;

final class SimpleConfigList extends AbstractConfigValue implements ConfigList, Container, Serializable {
    private static final long serialVersionUID = 2;
    private final boolean resolved;
    private final List<AbstractConfigValue> value;

    SimpleConfigList(ConfigOrigin configOrigin, List<AbstractConfigValue> list) {
        this(configOrigin, list, ResolveStatus.fromValues(list));
    }

    SimpleConfigList(ConfigOrigin configOrigin, List<AbstractConfigValue> list, ResolveStatus resolveStatus) {
        super(configOrigin);
        this.value = list;
        this.resolved = resolveStatus == ResolveStatus.RESOLVED;
        if (resolveStatus != ResolveStatus.fromValues(list)) {
            throw new ConfigException.BugOrBroken("SimpleConfigList created with wrong resolve status: " + this);
        }
    }

    public ConfigValueType valueType() {
        return ConfigValueType.LIST;
    }

    public List<Object> unwrapped() {
        ArrayList arrayList = new ArrayList();
        for (AbstractConfigValue unwrapped : this.value) {
            arrayList.add(unwrapped.unwrapped());
        }
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public ResolveStatus resolveStatus() {
        return ResolveStatus.fromBoolean(this.resolved);
    }

    public SimpleConfigList replaceChild(AbstractConfigValue abstractConfigValue, AbstractConfigValue abstractConfigValue2) {
        List<AbstractConfigValue> replaceChildInList = replaceChildInList(this.value, abstractConfigValue, abstractConfigValue2);
        if (replaceChildInList == null) {
            return null;
        }
        return new SimpleConfigList(origin(), replaceChildInList);
    }

    public boolean hasDescendant(AbstractConfigValue abstractConfigValue) {
        return hasDescendantInList(this.value, abstractConfigValue);
    }

    private SimpleConfigList modify(AbstractConfigValue.NoExceptionsModifier noExceptionsModifier, ResolveStatus resolveStatus) {
        try {
            return modifyMayThrow(noExceptionsModifier, resolveStatus);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e2) {
            throw new ConfigException.BugOrBroken("unexpected checked exception", e2);
        }
    }

    private SimpleConfigList modifyMayThrow(AbstractConfigValue.Modifier modifier, ResolveStatus resolveStatus) throws Exception {
        ArrayList arrayList = null;
        int i = 0;
        for (AbstractConfigValue next : this.value) {
            AbstractConfigValue modifyChildMayThrow = modifier.modifyChildMayThrow((String) null, next);
            if (arrayList == null && modifyChildMayThrow != next) {
                arrayList = new ArrayList();
                for (int i2 = 0; i2 < i; i2++) {
                    arrayList.add(this.value.get(i2));
                }
            }
            if (!(arrayList == null || modifyChildMayThrow == null)) {
                arrayList.add(modifyChildMayThrow);
            }
            i++;
        }
        if (arrayList == null) {
            return this;
        }
        if (resolveStatus != null) {
            return new SimpleConfigList(origin(), arrayList, resolveStatus);
        }
        return new SimpleConfigList(origin(), arrayList);
    }

    private static class ResolveModifier implements AbstractConfigValue.Modifier {
        ResolveContext context;
        final ResolveSource source;

        ResolveModifier(ResolveContext resolveContext, ResolveSource resolveSource) {
            this.context = resolveContext;
            this.source = resolveSource;
        }

        public AbstractConfigValue modifyChildMayThrow(String str, AbstractConfigValue abstractConfigValue) throws AbstractConfigValue.NotPossibleToResolve {
            ResolveResult<? extends AbstractConfigValue> resolve = this.context.resolve(abstractConfigValue, this.source);
            this.context = resolve.context;
            return resolve.value;
        }
    }

    /* access modifiers changed from: package-private */
    public ResolveResult<? extends SimpleConfigList> resolveSubstitutions(ResolveContext resolveContext, ResolveSource resolveSource) throws AbstractConfigValue.NotPossibleToResolve {
        if (this.resolved) {
            return ResolveResult.make(resolveContext, this);
        }
        if (resolveContext.isRestrictedToChild()) {
            return ResolveResult.make(resolveContext, this);
        }
        try {
            ResolveModifier resolveModifier = new ResolveModifier(resolveContext, resolveSource.pushParent(this));
            return ResolveResult.make(resolveModifier.context, modifyMayThrow(resolveModifier, resolveContext.options().getAllowUnresolved() ? null : ResolveStatus.RESOLVED));
        } catch (AbstractConfigValue.NotPossibleToResolve e) {
            throw e;
        } catch (RuntimeException e2) {
            throw e2;
        } catch (Exception e3) {
            throw new ConfigException.BugOrBroken("unexpected checked exception", e3);
        }
    }

    /* access modifiers changed from: package-private */
    public SimpleConfigList relativized(final Path path) {
        return modify(new AbstractConfigValue.NoExceptionsModifier() {
            public AbstractConfigValue modifyChild(String str, AbstractConfigValue abstractConfigValue) {
                return abstractConfigValue.relativized(path);
            }
        }, resolveStatus());
    }

    /* access modifiers changed from: protected */
    public boolean canEqual(Object obj) {
        return obj instanceof SimpleConfigList;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof SimpleConfigList) || !canEqual(obj)) {
            return false;
        }
        List<AbstractConfigValue> list = this.value;
        List<AbstractConfigValue> list2 = ((SimpleConfigList) obj).value;
        if (list == list2 || list.equals(list2)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.value.hashCode();
    }

    /* access modifiers changed from: protected */
    public void render(StringBuilder sb, int i, boolean z, ConfigRenderOptions configRenderOptions) {
        if (this.value.isEmpty()) {
            sb.append(HttpUrl.PATH_SEGMENT_ENCODE_SET_URI);
            return;
        }
        sb.append("[");
        if (configRenderOptions.getFormatted()) {
            sb.append(10);
        }
        for (AbstractConfigValue next : this.value) {
            if (configRenderOptions.getOriginComments()) {
                for (String str : next.origin().description().split("\n")) {
                    indent(sb, i + 1, configRenderOptions);
                    sb.append('#');
                    if (!str.isEmpty()) {
                        sb.append(' ');
                    }
                    sb.append(str);
                    sb.append("\n");
                }
            }
            if (configRenderOptions.getComments()) {
                for (String append : next.origin().comments()) {
                    indent(sb, i + 1, configRenderOptions);
                    sb.append("# ");
                    sb.append(append);
                    sb.append("\n");
                }
            }
            int i2 = i + 1;
            indent(sb, i2, configRenderOptions);
            next.render(sb, i2, z, configRenderOptions);
            sb.append(AnsiRenderer.CODE_LIST_SEPARATOR);
            if (configRenderOptions.getFormatted()) {
                sb.append(10);
            }
        }
        sb.setLength(sb.length() - 1);
        if (configRenderOptions.getFormatted()) {
            sb.setLength(sb.length() - 1);
            sb.append(10);
            indent(sb, i, configRenderOptions);
        }
        sb.append("]");
    }

    public boolean contains(Object obj) {
        return this.value.contains(obj);
    }

    public boolean containsAll(Collection<?> collection) {
        return this.value.containsAll(collection);
    }

    public AbstractConfigValue get(int i) {
        return this.value.get(i);
    }

    public int indexOf(Object obj) {
        return this.value.indexOf(obj);
    }

    public boolean isEmpty() {
        return this.value.isEmpty();
    }

    public Iterator<ConfigValue> iterator() {
        final Iterator<AbstractConfigValue> it = this.value.iterator();
        return new Iterator<ConfigValue>() {
            public boolean hasNext() {
                return it.hasNext();
            }

            public ConfigValue next() {
                return (ConfigValue) it.next();
            }

            public void remove() {
                throw SimpleConfigList.weAreImmutable("iterator().remove");
            }
        };
    }

    public int lastIndexOf(Object obj) {
        return this.value.lastIndexOf(obj);
    }

    private static ListIterator<ConfigValue> wrapListIterator(final ListIterator<AbstractConfigValue> listIterator) {
        return new ListIterator<ConfigValue>() {
            public boolean hasNext() {
                return listIterator.hasNext();
            }

            public ConfigValue next() {
                return (ConfigValue) listIterator.next();
            }

            public void remove() {
                throw SimpleConfigList.weAreImmutable("listIterator().remove");
            }

            public void add(ConfigValue configValue) {
                throw SimpleConfigList.weAreImmutable("listIterator().add");
            }

            public boolean hasPrevious() {
                return listIterator.hasPrevious();
            }

            public int nextIndex() {
                return listIterator.nextIndex();
            }

            public ConfigValue previous() {
                return (ConfigValue) listIterator.previous();
            }

            public int previousIndex() {
                return listIterator.previousIndex();
            }

            public void set(ConfigValue configValue) {
                throw SimpleConfigList.weAreImmutable("listIterator().set");
            }
        };
    }

    public ListIterator<ConfigValue> listIterator() {
        return wrapListIterator(this.value.listIterator());
    }

    public ListIterator<ConfigValue> listIterator(int i) {
        return wrapListIterator(this.value.listIterator(i));
    }

    public int size() {
        return this.value.size();
    }

    public List<ConfigValue> subList(int i, int i2) {
        ArrayList arrayList = new ArrayList();
        for (AbstractConfigValue add : this.value.subList(i, i2)) {
            arrayList.add(add);
        }
        return arrayList;
    }

    public Object[] toArray() {
        return this.value.toArray();
    }

    public <T> T[] toArray(T[] tArr) {
        return this.value.toArray(tArr);
    }

    /* access modifiers changed from: private */
    public static UnsupportedOperationException weAreImmutable(String str) {
        return new UnsupportedOperationException("ConfigList is immutable, you can't call List.'" + str + "'");
    }

    public boolean add(ConfigValue configValue) {
        throw weAreImmutable("add");
    }

    public void add(int i, ConfigValue configValue) {
        throw weAreImmutable("add");
    }

    public boolean addAll(Collection<? extends ConfigValue> collection) {
        throw weAreImmutable("addAll");
    }

    public boolean addAll(int i, Collection<? extends ConfigValue> collection) {
        throw weAreImmutable("addAll");
    }

    public void clear() {
        throw weAreImmutable("clear");
    }

    public boolean remove(Object obj) {
        throw weAreImmutable("remove");
    }

    public ConfigValue remove(int i) {
        throw weAreImmutable("remove");
    }

    public boolean removeAll(Collection<?> collection) {
        throw weAreImmutable("removeAll");
    }

    public boolean retainAll(Collection<?> collection) {
        throw weAreImmutable("retainAll");
    }

    public ConfigValue set(int i, ConfigValue configValue) {
        throw weAreImmutable("set");
    }

    /* access modifiers changed from: protected */
    public SimpleConfigList newCopy(ConfigOrigin configOrigin) {
        return new SimpleConfigList(configOrigin, this.value);
    }

    /* access modifiers changed from: package-private */
    public final SimpleConfigList concatenate(SimpleConfigList simpleConfigList) {
        ConfigOrigin mergeOrigins = SimpleConfigOrigin.mergeOrigins(origin(), simpleConfigList.origin());
        ArrayList arrayList = new ArrayList(this.value.size() + simpleConfigList.value.size());
        arrayList.addAll(this.value);
        arrayList.addAll(simpleConfigList.value);
        return new SimpleConfigList(mergeOrigins, arrayList);
    }

    private Object writeReplace() throws ObjectStreamException {
        return new SerializedConfigValue((ConfigValue) this);
    }

    public SimpleConfigList withOrigin(ConfigOrigin configOrigin) {
        return (SimpleConfigList) super.withOrigin(configOrigin);
    }
}
