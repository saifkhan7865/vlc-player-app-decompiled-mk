package io.netty.util.collection;

import io.netty.util.collection.ShortObjectMap;
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

public class ShortObjectHashMap<V> implements ShortObjectMap<V> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int DEFAULT_CAPACITY = 8;
    public static final float DEFAULT_LOAD_FACTOR = 0.5f;
    private static final Object NULL_VALUE = new Object();
    private final Iterable<ShortObjectMap.PrimitiveEntry<V>> entries;
    /* access modifiers changed from: private */
    public final Set<Map.Entry<Short, V>> entrySet;
    private final Set<Short> keySet;
    /* access modifiers changed from: private */
    public short[] keys;
    private final float loadFactor;
    private int mask;
    private int maxSize;
    /* access modifiers changed from: private */
    public int size;
    /* access modifiers changed from: private */
    public V[] values;

    private static int hashCode(short s) {
        return s;
    }

    public ShortObjectHashMap() {
        this(8, 0.5f);
    }

    public ShortObjectHashMap(int i) {
        this(i, 0.5f);
    }

    public ShortObjectHashMap(int i, float f) {
        this.keySet = new KeySet();
        this.entrySet = new EntrySet();
        this.entries = new Iterable<ShortObjectMap.PrimitiveEntry<V>>() {
            public Iterator<ShortObjectMap.PrimitiveEntry<V>> iterator() {
                return new PrimitiveIterator();
            }
        };
        if (f <= 0.0f || f > 1.0f) {
            throw new IllegalArgumentException("loadFactor must be > 0 and <= 1");
        }
        this.loadFactor = f;
        int safeFindNextPositivePowerOfTwo = MathUtil.safeFindNextPositivePowerOfTwo(i);
        this.mask = safeFindNextPositivePowerOfTwo - 1;
        this.keys = new short[safeFindNextPositivePowerOfTwo];
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

    public V get(short s) {
        int indexOf = indexOf(s);
        if (indexOf == -1) {
            return null;
        }
        return toExternal(this.values[indexOf]);
    }

    public V put(short s, V v) {
        int hashIndex = hashIndex(s);
        int i = hashIndex;
        do {
            V[] vArr = this.values;
            V v2 = vArr[i];
            if (v2 == null) {
                this.keys[i] = s;
                vArr[i] = toInternal(v);
                growSize();
                return null;
            } else if (this.keys[i] == s) {
                vArr[i] = toInternal(v);
                return toExternal(v2);
            } else {
                i = probeNext(i);
            }
        } while (i != hashIndex);
        throw new IllegalStateException("Unable to insert");
    }

    public void putAll(Map<? extends Short, ? extends V> map) {
        if (map instanceof ShortObjectHashMap) {
            ShortObjectHashMap shortObjectHashMap = (ShortObjectHashMap) map;
            int i = 0;
            while (true) {
                V[] vArr = shortObjectHashMap.values;
                if (i < vArr.length) {
                    V v = vArr[i];
                    if (v != null) {
                        put(shortObjectHashMap.keys[i], v);
                    }
                    i++;
                } else {
                    return;
                }
            }
        } else {
            for (Map.Entry next : map.entrySet()) {
                put((Short) next.getKey(), next.getValue());
            }
        }
    }

    public V remove(short s) {
        int indexOf = indexOf(s);
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

    public boolean containsKey(short s) {
        return indexOf(s) >= 0;
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

    public Iterable<ShortObjectMap.PrimitiveEntry<V>> entries() {
        return this.entries;
    }

    public Collection<V> values() {
        return new AbstractCollection<V>() {
            public Iterator<V> iterator() {
                return new Iterator<V>() {
                    final ShortObjectHashMap<V>.PrimitiveIterator iter;

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
                return ShortObjectHashMap.this.size;
            }
        };
    }

    public int hashCode() {
        int i = this.size;
        for (short hashCode : this.keys) {
            i ^= hashCode(hashCode);
        }
        return i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ShortObjectMap)) {
            return false;
        }
        ShortObjectMap shortObjectMap = (ShortObjectMap) obj;
        if (this.size != shortObjectMap.size()) {
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
                Object obj2 = shortObjectMap.get(this.keys[i]);
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

    public V put(Short sh, V v) {
        return put(objectToKey(sh), v);
    }

    public V remove(Object obj) {
        return remove(objectToKey(obj));
    }

    public Set<Short> keySet() {
        return this.keySet;
    }

    public Set<Map.Entry<Short, V>> entrySet() {
        return this.entrySet;
    }

    private short objectToKey(Object obj) {
        return ((Short) obj).shortValue();
    }

    private int indexOf(short s) {
        int hashIndex = hashIndex(s);
        int i = hashIndex;
        while (this.values[i] != null) {
            if (s == this.keys[i]) {
                return i;
            }
            i = probeNext(i);
            if (i == hashIndex) {
                return -1;
            }
        }
        return -1;
    }

    private int hashIndex(short s) {
        return hashCode(s) & this.mask;
    }

    private int probeNext(int i) {
        return (i + 1) & this.mask;
    }

    private void growSize() {
        int i = this.size + 1;
        this.size = i;
        if (i > this.maxSize) {
            short[] sArr = this.keys;
            if (sArr.length != Integer.MAX_VALUE) {
                rehash(sArr.length << 1);
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
            short s = this.keys[probeNext];
            int hashIndex = hashIndex(s);
            if ((probeNext < hashIndex && (hashIndex <= i2 || i2 <= probeNext)) || (hashIndex <= i2 && i2 <= probeNext)) {
                short[] sArr = this.keys;
                sArr[i2] = s;
                V[] vArr = this.values;
                vArr[i2] = v;
                sArr[probeNext] = 0;
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
        short[] sArr = this.keys;
        V[] vArr2 = this.values;
        this.keys = new short[i];
        this.values = (Object[]) new Object[i];
        this.maxSize = calcMaxSize(i);
        this.mask = i - 1;
        for (int i2 = 0; i2 < vArr2.length; i2++) {
            V v = vArr2[i2];
            if (v != null) {
                short s = sArr[i2];
                int hashIndex = hashIndex(s);
                while (true) {
                    vArr = this.values;
                    if (vArr[hashIndex] == null) {
                        break;
                    }
                    hashIndex = probeNext(hashIndex);
                }
                this.keys[hashIndex] = s;
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
    public String keyToString(short s) {
        return Short.toString(s);
    }

    private final class EntrySet extends AbstractSet<Map.Entry<Short, V>> {
        private EntrySet() {
        }

        public Iterator<Map.Entry<Short, V>> iterator() {
            return new MapIterator();
        }

        public int size() {
            return ShortObjectHashMap.this.size();
        }
    }

    private final class KeySet extends AbstractSet<Short> {
        private KeySet() {
        }

        public int size() {
            return ShortObjectHashMap.this.size();
        }

        public boolean contains(Object obj) {
            return ShortObjectHashMap.this.containsKey(obj);
        }

        public boolean remove(Object obj) {
            return ShortObjectHashMap.this.remove(obj) != null;
        }

        public boolean retainAll(Collection<?> collection) {
            Iterator it = ShortObjectHashMap.this.entries().iterator();
            boolean z = false;
            while (it.hasNext()) {
                if (!collection.contains(Short.valueOf(((ShortObjectMap.PrimitiveEntry) it.next()).key()))) {
                    it.remove();
                    z = true;
                }
            }
            return z;
        }

        public void clear() {
            ShortObjectHashMap.this.clear();
        }

        public Iterator<Short> iterator() {
            return new Iterator<Short>() {
                private final Iterator<Map.Entry<Short, V>> iter;

                {
                    this.iter = ShortObjectHashMap.this.entrySet.iterator();
                }

                public boolean hasNext() {
                    return this.iter.hasNext();
                }

                public Short next() {
                    return (Short) this.iter.next().getKey();
                }

                public void remove() {
                    this.iter.remove();
                }
            };
        }
    }

    private final class PrimitiveIterator implements Iterator<ShortObjectMap.PrimitiveEntry<V>>, ShortObjectMap.PrimitiveEntry<V> {
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
                io.netty.util.collection.ShortObjectHashMap r1 = io.netty.util.collection.ShortObjectHashMap.this
                java.lang.Object[] r1 = r1.values
                int r1 = r1.length
                if (r0 == r1) goto L_0x001c
                io.netty.util.collection.ShortObjectHashMap r0 = io.netty.util.collection.ShortObjectHashMap.this
                java.lang.Object[] r0 = r0.values
                int r1 = r2.nextIndex
                r0 = r0[r1]
                if (r0 != 0) goto L_0x001c
                goto L_0x0000
            L_0x001c:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.netty.util.collection.ShortObjectHashMap.PrimitiveIterator.scanNext():void");
        }

        public boolean hasNext() {
            if (this.nextIndex == -1) {
                scanNext();
            }
            return this.nextIndex != ShortObjectHashMap.this.values.length;
        }

        public ShortObjectMap.PrimitiveEntry<V> next() {
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
                if (ShortObjectHashMap.this.removeAt(i)) {
                    this.nextIndex = this.prevIndex;
                }
                this.prevIndex = -1;
                return;
            }
            throw new IllegalStateException("next must be called before each remove.");
        }

        public short key() {
            return ShortObjectHashMap.this.keys[this.entryIndex];
        }

        public V value() {
            return ShortObjectHashMap.toExternal(ShortObjectHashMap.this.values[this.entryIndex]);
        }

        public void setValue(V v) {
            ShortObjectHashMap.this.values[this.entryIndex] = ShortObjectHashMap.toInternal(v);
        }
    }

    private final class MapIterator implements Iterator<Map.Entry<Short, V>> {
        private final ShortObjectHashMap<V>.PrimitiveIterator iter;

        private MapIterator() {
            this.iter = new PrimitiveIterator();
        }

        public boolean hasNext() {
            return this.iter.hasNext();
        }

        public Map.Entry<Short, V> next() {
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

    final class MapEntry implements Map.Entry<Short, V> {
        private final int entryIndex;

        MapEntry(int i) {
            this.entryIndex = i;
        }

        public Short getKey() {
            verifyExists();
            return Short.valueOf(ShortObjectHashMap.this.keys[this.entryIndex]);
        }

        public V getValue() {
            verifyExists();
            return ShortObjectHashMap.toExternal(ShortObjectHashMap.this.values[this.entryIndex]);
        }

        public V setValue(V v) {
            verifyExists();
            V access$900 = ShortObjectHashMap.toExternal(ShortObjectHashMap.this.values[this.entryIndex]);
            ShortObjectHashMap.this.values[this.entryIndex] = ShortObjectHashMap.toInternal(v);
            return access$900;
        }

        private void verifyExists() {
            if (ShortObjectHashMap.this.values[this.entryIndex] == null) {
                throw new IllegalStateException("The map entry has been removed");
            }
        }
    }
}
