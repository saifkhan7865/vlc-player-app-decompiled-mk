package io.netty.util.collection;

import io.netty.util.collection.IntObjectMap;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

public final class IntCollections {
    private static final IntObjectMap<Object> EMPTY_MAP = new EmptyMap();

    private IntCollections() {
    }

    public static <V> IntObjectMap<V> emptyMap() {
        return EMPTY_MAP;
    }

    public static <V> IntObjectMap<V> unmodifiableMap(IntObjectMap<V> intObjectMap) {
        return new UnmodifiableMap(intObjectMap);
    }

    private static final class EmptyMap implements IntObjectMap<Object> {
        public void clear() {
        }

        public boolean containsKey(int i) {
            return false;
        }

        public boolean containsKey(Object obj) {
            return false;
        }

        public boolean containsValue(Object obj) {
            return false;
        }

        public Object get(int i) {
            return null;
        }

        public Object get(Object obj) {
            return null;
        }

        public boolean isEmpty() {
            return true;
        }

        public Object remove(int i) {
            return null;
        }

        public Object remove(Object obj) {
            return null;
        }

        public int size() {
            return 0;
        }

        private EmptyMap() {
        }

        public Object put(int i, Object obj) {
            throw new UnsupportedOperationException("put");
        }

        public Set<Integer> keySet() {
            return Collections.emptySet();
        }

        public Iterable<IntObjectMap.PrimitiveEntry<Object>> entries() {
            return Collections.emptySet();
        }

        public Object put(Integer num, Object obj) {
            throw new UnsupportedOperationException();
        }

        public void putAll(Map<? extends Integer, ?> map) {
            throw new UnsupportedOperationException();
        }

        public Collection<Object> values() {
            return Collections.emptyList();
        }

        public Set<Map.Entry<Integer, Object>> entrySet() {
            return Collections.emptySet();
        }
    }

    private static final class UnmodifiableMap<V> implements IntObjectMap<V> {
        private Iterable<IntObjectMap.PrimitiveEntry<V>> entries;
        private Set<Map.Entry<Integer, V>> entrySet;
        private Set<Integer> keySet;
        /* access modifiers changed from: private */
        public final IntObjectMap<V> map;
        private Collection<V> values;

        UnmodifiableMap(IntObjectMap<V> intObjectMap) {
            this.map = intObjectMap;
        }

        public V get(int i) {
            return this.map.get(i);
        }

        public V put(int i, V v) {
            throw new UnsupportedOperationException("put");
        }

        public V remove(int i) {
            throw new UnsupportedOperationException("remove");
        }

        public int size() {
            return this.map.size();
        }

        public boolean isEmpty() {
            return this.map.isEmpty();
        }

        public void clear() {
            throw new UnsupportedOperationException("clear");
        }

        public boolean containsKey(int i) {
            return this.map.containsKey(i);
        }

        public boolean containsValue(Object obj) {
            return this.map.containsValue(obj);
        }

        public boolean containsKey(Object obj) {
            return this.map.containsKey(obj);
        }

        public V get(Object obj) {
            return this.map.get(obj);
        }

        public V put(Integer num, V v) {
            throw new UnsupportedOperationException("put");
        }

        public V remove(Object obj) {
            throw new UnsupportedOperationException("remove");
        }

        public void putAll(Map<? extends Integer, ? extends V> map2) {
            throw new UnsupportedOperationException("putAll");
        }

        public Iterable<IntObjectMap.PrimitiveEntry<V>> entries() {
            if (this.entries == null) {
                this.entries = new Iterable<IntObjectMap.PrimitiveEntry<V>>() {
                    public Iterator<IntObjectMap.PrimitiveEntry<V>> iterator() {
                        UnmodifiableMap unmodifiableMap = UnmodifiableMap.this;
                        return new IteratorImpl(unmodifiableMap.map.entries().iterator());
                    }
                };
            }
            return this.entries;
        }

        public Set<Integer> keySet() {
            if (this.keySet == null) {
                this.keySet = Collections.unmodifiableSet(this.map.keySet());
            }
            return this.keySet;
        }

        public Set<Map.Entry<Integer, V>> entrySet() {
            if (this.entrySet == null) {
                this.entrySet = Collections.unmodifiableSet(this.map.entrySet());
            }
            return this.entrySet;
        }

        public Collection<V> values() {
            if (this.values == null) {
                this.values = Collections.unmodifiableCollection(this.map.values());
            }
            return this.values;
        }

        private class IteratorImpl implements Iterator<IntObjectMap.PrimitiveEntry<V>> {
            final Iterator<IntObjectMap.PrimitiveEntry<V>> iter;

            IteratorImpl(Iterator<IntObjectMap.PrimitiveEntry<V>> it) {
                this.iter = it;
            }

            public boolean hasNext() {
                return this.iter.hasNext();
            }

            public IntObjectMap.PrimitiveEntry<V> next() {
                if (hasNext()) {
                    return new EntryImpl(this.iter.next());
                }
                throw new NoSuchElementException();
            }

            public void remove() {
                throw new UnsupportedOperationException("remove");
            }
        }

        private class EntryImpl implements IntObjectMap.PrimitiveEntry<V> {
            private final IntObjectMap.PrimitiveEntry<V> entry;

            EntryImpl(IntObjectMap.PrimitiveEntry<V> primitiveEntry) {
                this.entry = primitiveEntry;
            }

            public int key() {
                return this.entry.key();
            }

            public V value() {
                return this.entry.value();
            }

            public void setValue(V v) {
                throw new UnsupportedOperationException("setValue");
            }
        }
    }
}
