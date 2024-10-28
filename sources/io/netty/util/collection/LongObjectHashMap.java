package io.netty.util.collection;

import io.netty.util.collection.LongObjectMap;
import io.netty.util.internal.MathUtil;
import java.util.AbstractCollection;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

public class LongObjectHashMap<V> implements LongObjectMap<V> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int DEFAULT_CAPACITY = 8;
    public static final float DEFAULT_LOAD_FACTOR = 0.5f;
    private static final Object NULL_VALUE = new Object();
    private final Iterable<LongObjectMap.PrimitiveEntry<V>> entries;
    /* access modifiers changed from: private */
    public final Set<Map.Entry<Long, V>> entrySet;
    private final Set<Long> keySet;
    /* access modifiers changed from: private */
    public long[] keys;
    private final float loadFactor;
    private int mask;
    private int maxSize;
    /* access modifiers changed from: private */
    public int size;
    /* access modifiers changed from: private */
    public V[] values;

    private static int hashCode(long j) {
        return (int) (j ^ (j >>> 32));
    }

    public LongObjectHashMap() {
        this(8, 0.5f);
    }

    public LongObjectHashMap(int i) {
        this(i, 0.5f);
    }

    public LongObjectHashMap(int i, float f) {
        this.keySet = new KeySet();
        this.entrySet = new EntrySet();
        this.entries = new Iterable<LongObjectMap.PrimitiveEntry<V>>() {
            public Iterator<LongObjectMap.PrimitiveEntry<V>> iterator() {
                return new PrimitiveIterator();
            }
        };
        if (f <= 0.0f || f > 1.0f) {
            throw new IllegalArgumentException("loadFactor must be > 0 and <= 1");
        }
        this.loadFactor = f;
        int safeFindNextPositivePowerOfTwo = MathUtil.safeFindNextPositivePowerOfTwo(i);
        this.mask = safeFindNextPositivePowerOfTwo - 1;
        this.keys = new long[safeFindNextPositivePowerOfTwo];
        this.values = (Object[]) new Object[safeFindNextPositivePowerOfTwo];
        this.maxSize = calcMaxSize(safeFindNextPositivePowerOfTwo);
    }

    /* access modifiers changed from: private */
    public static <T> T toExternal(T t) {
        if (t == NULL_VALUE) {
            return null;
        }
        return t;
    }

    /* access modifiers changed from: private */
    public static <T> T toInternal(T t) {
        return t == null ? NULL_VALUE : t;
    }

    public V get(long j) {
        int indexOf = indexOf(j);
        if (indexOf == -1) {
            return null;
        }
        return toExternal(this.values[indexOf]);
    }

    public V put(long j, V v) {
        int hashIndex = hashIndex(j);
        int i = hashIndex;
        do {
            V[] vArr = this.values;
            V v2 = vArr[i];
            if (v2 == null) {
                this.keys[i] = j;
                vArr[i] = toInternal(v);
                growSize();
                return null;
            } else if (this.keys[i] == j) {
                vArr[i] = toInternal(v);
                return toExternal(v2);
            } else {
                i = probeNext(i);
            }
        } while (i != hashIndex);
        throw new IllegalStateException("Unable to insert");
    }

    public void putAll(Map<? extends Long, ? extends V> map) {
        if (map instanceof LongObjectHashMap) {
            LongObjectHashMap longObjectHashMap = (LongObjectHashMap) map;
            int i = 0;
            while (true) {
                V[] vArr = longObjectHashMap.values;
                if (i < vArr.length) {
                    V v = vArr[i];
                    if (v != null) {
                        put(longObjectHashMap.keys[i], v);
                    }
                    i++;
                } else {
                    return;
                }
            }
        } else {
            for (Map.Entry next : map.entrySet()) {
                put((Long) next.getKey(), next.getValue());
            }
        }
    }

    public V remove(long j) {
        int indexOf = indexOf(j);
        if (indexOf == -1) {
            return null;
        }
        V v = this.values[indexOf];
        removeAt(indexOf);
        return toExternal(v);
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public void clear() {
        Arrays.fill(this.keys, 0);
        Arrays.fill(this.values, (Object) null);
        this.size = 0;
    }

    public boolean containsKey(long j) {
        return indexOf(j) >= 0;
    }

    public boolean containsValue(Object obj) {
        Object internal = toInternal(obj);
        for (V v : this.values) {
            if (v != null && v.equals(internal)) {
                return true;
            }
        }
        return false;
    }

    public Iterable<LongObjectMap.PrimitiveEntry<V>> entries() {
        return this.entries;
    }

    public Collection<V> values() {
        return new AbstractCollection<V>() {
            public Iterator<V> iterator() {
                return new Iterator<V>() {
                    final LongObjectHashMap<V>.PrimitiveIterator iter;

                    {
                        this.iter = new PrimitiveIterator();
                    }

                    public boolean hasNext() {
                        return this.iter.hasNext();
                    }

                    public V next() {
                        return this.iter.next().value();
                    }

                    public void remove() {
                        this.iter.remove();
                    }
                };
            }

            public int size() {
                return LongObjectHashMap.this.size;
            }
        };
    }

    public int hashCode() {
        int i = this.size;
        for (long hashCode : this.keys) {
            i ^= hashCode(hashCode);
        }
        return i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LongObjectMap)) {
            return false;
        }
        LongObjectMap longObjectMap = (LongObjectMap) obj;
        if (this.size != longObjectMap.size()) {
            return false;
        }
        int i = 0;
        while (true) {
            V[] vArr = this.values;
            if (i >= vArr.length) {
                return true;
            }
            V v = vArr[i];
            if (v != null) {
                Object obj2 = longObjectMap.get(this.keys[i]);
                if (v == NULL_VALUE) {
                    if (obj2 != null) {
                        return false;
                    }
                } else if (!v.equals(obj2)) {
                    return false;
                }
            }
            i++;
        }
    }

    public boolean containsKey(Object obj) {
        return containsKey(objectToKey(obj));
    }

    public V get(Object obj) {
        return get(objectToKey(obj));
    }

    public V put(Long l, V v) {
        return put(objectToKey(l), v);
    }

    public V remove(Object obj) {
        return remove(objectToKey(obj));
    }

    public Set<Long> keySet() {
        return this.keySet;
    }

    public Set<Map.Entry<Long, V>> entrySet() {
        return this.entrySet;
    }

    private long objectToKey(Object obj) {
        return ((Long) obj).longValue();
    }

    private int indexOf(long j) {
        int hashIndex = hashIndex(j);
        int i = hashIndex;
        while (this.values[i] != null) {
            if (j == this.keys[i]) {
                return i;
            }
            i = probeNext(i);
            if (i == hashIndex) {
                return -1;
            }
        }
        return -1;
    }

    private int hashIndex(long j) {
        return hashCode(j) & this.mask;
    }

    private int probeNext(int i) {
        return (i + 1) & this.mask;
    }

    private void growSize() {
        int i = this.size + 1;
        this.size = i;
        if (i > this.maxSize) {
            long[] jArr = this.keys;
            if (jArr.length != Integer.MAX_VALUE) {
                rehash(jArr.length << 1);
                return;
            }
            throw new IllegalStateException("Max capacity reached at size=" + this.size);
        }
    }

    /* access modifiers changed from: private */
    public boolean removeAt(int i) {
        this.size--;
        this.keys[i] = 0;
        this.values[i] = null;
        int probeNext = probeNext(i);
        V v = this.values[probeNext];
        int i2 = i;
        while (v != null) {
            long j = this.keys[probeNext];
            int hashIndex = hashIndex(j);
            if ((probeNext < hashIndex && (hashIndex <= i2 || i2 <= probeNext)) || (hashIndex <= i2 && i2 <= probeNext)) {
                long[] jArr = this.keys;
                jArr[i2] = j;
                V[] vArr = this.values;
                vArr[i2] = v;
                jArr[probeNext] = 0;
                vArr[probeNext] = null;
                i2 = probeNext;
            }
            V[] vArr2 = this.values;
            probeNext = probeNext(probeNext);
            v = vArr2[probeNext];
        }
        if (i2 != i) {
            return true;
        }
        return false;
    }

    private int calcMaxSize(int i) {
        return Math.min(i - 1, (int) (((float) i) * this.loadFactor));
    }

    private void rehash(int i) {
        V[] vArr;
        long[] jArr = this.keys;
        V[] vArr2 = this.values;
        this.keys = new long[i];
        this.values = (Object[]) new Object[i];
        this.maxSize = calcMaxSize(i);
        this.mask = i - 1;
        for (int i2 = 0; i2 < vArr2.length; i2++) {
            V v = vArr2[i2];
            if (v != null) {
                long j = jArr[i2];
                int hashIndex = hashIndex(j);
                while (true) {
                    vArr = this.values;
                    if (vArr[hashIndex] == null) {
                        break;
                    }
                    hashIndex = probeNext(hashIndex);
                }
                this.keys[hashIndex] = j;
                vArr[hashIndex] = v;
            }
        }
    }

    public String toString() {
        Object obj;
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.size * 4);
        sb.append(AbstractJsonLexerKt.BEGIN_OBJ);
        boolean z = true;
        int i = 0;
        while (true) {
            V[] vArr = this.values;
            if (i < vArr.length) {
                V v = vArr[i];
                if (v != null) {
                    if (!z) {
                        sb.append(", ");
                    }
                    sb.append(keyToString(this.keys[i]));
                    sb.append('=');
                    if (v == this) {
                        obj = "(this Map)";
                    } else {
                        obj = toExternal(v);
                    }
                    sb.append(obj);
                    z = false;
                }
                i++;
            } else {
                sb.append(AbstractJsonLexerKt.END_OBJ);
                return sb.toString();
            }
        }
    }

    /* access modifiers changed from: protected */
    public String keyToString(long j) {
        return Long.toString(j);
    }

    private final class EntrySet extends AbstractSet<Map.Entry<Long, V>> {
        private EntrySet() {
        }

        public Iterator<Map.Entry<Long, V>> iterator() {
            return new MapIterator();
        }

        public int size() {
            return LongObjectHashMap.this.size();
        }
    }

    private final class KeySet extends AbstractSet<Long> {
        private KeySet() {
        }

        public int size() {
            return LongObjectHashMap.this.size();
        }

        public boolean contains(Object obj) {
            return LongObjectHashMap.this.containsKey(obj);
        }

        public boolean remove(Object obj) {
            return LongObjectHashMap.this.remove(obj) != null;
        }

        public boolean retainAll(Collection<?> collection) {
            Iterator it = LongObjectHashMap.this.entries().iterator();
            boolean z = false;
            while (it.hasNext()) {
                if (!collection.contains(Long.valueOf(((LongObjectMap.PrimitiveEntry) it.next()).key()))) {
                    it.remove();
                    z = true;
                }
            }
            return z;
        }

        public void clear() {
            LongObjectHashMap.this.clear();
        }

        public Iterator<Long> iterator() {
            return new Iterator<Long>() {
                private final Iterator<Map.Entry<Long, V>> iter;

                {
                    this.iter = LongObjectHashMap.this.entrySet.iterator();
                }

                public boolean hasNext() {
                    return this.iter.hasNext();
                }

                public Long next() {
                    return (Long) this.iter.next().getKey();
                }

                public void remove() {
                    this.iter.remove();
                }
            };
        }
    }

    private final class PrimitiveIterator implements Iterator<LongObjectMap.PrimitiveEntry<V>>, LongObjectMap.PrimitiveEntry<V> {
        /* access modifiers changed from: private */
        public int entryIndex;
        private int nextIndex;
        private int prevIndex;

        private PrimitiveIterator() {
            this.prevIndex = -1;
            this.nextIndex = -1;
            this.entryIndex = -1;
        }

        /* JADX WARNING: Removed duplicated region for block: B:0:0x0000 A[LOOP_START, MTH_ENTER_BLOCK] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void scanNext() {
            /*
                r2 = this;
            L_0x0000:
                int r0 = r2.nextIndex
                int r0 = r0 + 1
                r2.nextIndex = r0
                io.netty.util.collection.LongObjectHashMap r1 = io.netty.util.collection.LongObjectHashMap.this
                java.lang.Object[] r1 = r1.values
                int r1 = r1.length
                if (r0 == r1) goto L_0x001c
                io.netty.util.collection.LongObjectHashMap r0 = io.netty.util.collection.LongObjectHashMap.this
                java.lang.Object[] r0 = r0.values
                int r1 = r2.nextIndex
                r0 = r0[r1]
                if (r0 != 0) goto L_0x001c
                goto L_0x0000
            L_0x001c:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.netty.util.collection.LongObjectHashMap.PrimitiveIterator.scanNext():void");
        }

        public boolean hasNext() {
            if (this.nextIndex == -1) {
                scanNext();
            }
            return this.nextIndex != LongObjectHashMap.this.values.length;
        }

        public LongObjectMap.PrimitiveEntry<V> next() {
            if (hasNext()) {
                this.prevIndex = this.nextIndex;
                scanNext();
                this.entryIndex = this.prevIndex;
                return this;
            }
            throw new NoSuchElementException();
        }

        public void remove() {
            int i = this.prevIndex;
            if (i != -1) {
                if (LongObjectHashMap.this.removeAt(i)) {
                    this.nextIndex = this.prevIndex;
                }
                this.prevIndex = -1;
                return;
            }
            throw new IllegalStateException("next must be called before each remove.");
        }

        public long key() {
            return LongObjectHashMap.this.keys[this.entryIndex];
        }

        public V value() {
            return LongObjectHashMap.toExternal(LongObjectHashMap.this.values[this.entryIndex]);
        }

        public void setValue(V v) {
            LongObjectHashMap.this.values[this.entryIndex] = LongObjectHashMap.toInternal(v);
        }
    }

    private final class MapIterator implements Iterator<Map.Entry<Long, V>> {
        private final LongObjectHashMap<V>.PrimitiveIterator iter;

        private MapIterator() {
            this.iter = new PrimitiveIterator();
        }

        public boolean hasNext() {
            return this.iter.hasNext();
        }

        public Map.Entry<Long, V> next() {
            if (hasNext()) {
                this.iter.next();
                return new MapEntry(this.iter.entryIndex);
            }
            throw new NoSuchElementException();
        }

        public void remove() {
            this.iter.remove();
        }
    }

    final class MapEntry implements Map.Entry<Long, V> {
        private final int entryIndex;

        MapEntry(int i) {
            this.entryIndex = i;
        }

        public Long getKey() {
            verifyExists();
            return Long.valueOf(LongObjectHashMap.this.keys[this.entryIndex]);
        }

        public V getValue() {
            verifyExists();
            return LongObjectHashMap.toExternal(LongObjectHashMap.this.values[this.entryIndex]);
        }

        public V setValue(V v) {
            verifyExists();
            V access$900 = LongObjectHashMap.toExternal(LongObjectHashMap.this.values[this.entryIndex]);
            LongObjectHashMap.this.values[this.entryIndex] = LongObjectHashMap.toInternal(v);
            return access$900;
        }

        private void verifyExists() {
            if (LongObjectHashMap.this.values[this.entryIndex] == null) {
                throw new IllegalStateException("The map entry has been removed");
            }
        }
    }
}
