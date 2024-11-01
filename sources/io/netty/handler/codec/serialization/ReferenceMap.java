package io.netty.handler.codec.serialization;

import java.lang.ref.Reference;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

abstract class ReferenceMap<K, V> implements Map<K, V> {
    private final Map<K, Reference<V>> delegate;

    /* access modifiers changed from: package-private */
    public abstract Reference<V> fold(V v);

    protected ReferenceMap(Map<K, Reference<V>> map) {
        this.delegate = map;
    }

    private V unfold(Reference<V> reference) {
        if (reference == null) {
            return null;
        }
        return reference.get();
    }

    public int size() {
        return this.delegate.size();
    }

    public boolean isEmpty() {
        return this.delegate.isEmpty();
    }

    public boolean containsKey(Object obj) {
        return this.delegate.containsKey(obj);
    }

    public boolean containsValue(Object obj) {
        throw new UnsupportedOperationException();
    }

    public V get(Object obj) {
        return unfold(this.delegate.get(obj));
    }

    public V put(K k, V v) {
        return unfold(this.delegate.put(k, fold(v)));
    }

    public V remove(Object obj) {
        return unfold(this.delegate.remove(obj));
    }

    public void putAll(Map<? extends K, ? extends V> map) {
        for (Map.Entry next : map.entrySet()) {
            this.delegate.put(next.getKey(), fold(next.getValue()));
        }
    }

    public void clear() {
        this.delegate.clear();
    }

    public Set<K> keySet() {
        return this.delegate.keySet();
    }

    public Collection<V> values() {
        throw new UnsupportedOperationException();
    }

    public Set<Map.Entry<K, V>> entrySet() {
        throw new UnsupportedOperationException();
    }
}
