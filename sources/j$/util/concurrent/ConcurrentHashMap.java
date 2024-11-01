package j$.util.concurrent;

import j$.sun.misc.DesugarUnsafe;
import j$.util.Collection;
import j$.util.Spliterator;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamField;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

public class ConcurrentHashMap<K, V> extends AbstractMap<K, V> implements ConcurrentMap<K, V>, Serializable, ConcurrentMap<K, V> {
    private static final int ABASE;
    private static final int ASHIFT;
    private static final long BASECOUNT;
    private static final long CELLSBUSY;
    private static final long CELLVALUE;
    static final int NCPU = Runtime.getRuntime().availableProcessors();
    private static final long SIZECTL;
    private static final long TRANSFERINDEX;
    private static final DesugarUnsafe U;
    private static final ObjectStreamField[] serialPersistentFields;
    private static final long serialVersionUID = 7249069246763182397L;
    private volatile transient long baseCount;
    private volatile transient int cellsBusy;
    private volatile transient CounterCell[] counterCells;
    private transient EntrySetView entrySet;
    private transient KeySetView keySet;
    private volatile transient Node[] nextTable;
    private volatile transient int sizeCtl;
    volatile transient Node[] table;
    private volatile transient int transferIndex;
    private transient ValuesView values;

    static class BaseIterator extends Traverser {
        Node lastReturned;
        final ConcurrentHashMap map;

        BaseIterator(Node[] nodeArr, int i, int i2, int i3, ConcurrentHashMap concurrentHashMap) {
            super(nodeArr, i, i2, i3);
            this.map = concurrentHashMap;
            advance();
        }

        public final boolean hasMoreElements() {
            return this.next != null;
        }

        public final boolean hasNext() {
            return this.next != null;
        }

        public final void remove() {
            Node node = this.lastReturned;
            if (node != null) {
                this.lastReturned = null;
                this.map.replaceNode(node.key, (Object) null, (Object) null);
                return;
            }
            throw new IllegalStateException();
        }
    }

    static abstract class CollectionView implements Collection, Serializable {
        private static final long serialVersionUID = 7249069246763182397L;
        final ConcurrentHashMap map;

        CollectionView(ConcurrentHashMap concurrentHashMap) {
            this.map = concurrentHashMap;
        }

        public final void clear() {
            this.map.clear();
        }

        public abstract boolean contains(Object obj);

        /* JADX WARNING: Removed duplicated region for block: B:4:0x000c  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final boolean containsAll(java.util.Collection r2) {
            /*
                r1 = this;
                if (r2 == r1) goto L_0x001a
                java.util.Iterator r2 = r2.iterator()
            L_0x0006:
                boolean r0 = r2.hasNext()
                if (r0 == 0) goto L_0x001a
                java.lang.Object r0 = r2.next()
                if (r0 == 0) goto L_0x0018
                boolean r0 = r1.contains(r0)
                if (r0 != 0) goto L_0x0006
            L_0x0018:
                r2 = 0
                return r2
            L_0x001a:
                r2 = 1
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: j$.util.concurrent.ConcurrentHashMap.CollectionView.containsAll(java.util.Collection):boolean");
        }

        public final boolean isEmpty() {
            return this.map.isEmpty();
        }

        public abstract Iterator iterator();

        public abstract boolean remove(Object obj);

        public boolean removeAll(Collection collection) {
            collection.getClass();
            Node[] nodeArr = this.map.table;
            boolean z = false;
            if (nodeArr == null) {
                return false;
            }
            if (!(collection instanceof Set) || collection.size() <= nodeArr.length) {
                for (Object remove : collection) {
                    z |= remove(remove);
                }
            } else {
                Iterator it = iterator();
                while (it.hasNext()) {
                    if (collection.contains(it.next())) {
                        it.remove();
                        z = true;
                    }
                }
            }
            return z;
        }

        public final boolean retainAll(Collection collection) {
            collection.getClass();
            Iterator it = iterator();
            boolean z = false;
            while (it.hasNext()) {
                if (!collection.contains(it.next())) {
                    it.remove();
                    z = true;
                }
            }
            return z;
        }

        public final int size() {
            return this.map.size();
        }

        public final Object[] toArray() {
            long mappingCount = this.map.mappingCount();
            if (mappingCount <= 2147483639) {
                int i = (int) mappingCount;
                Object[] objArr = new Object[i];
                Iterator it = iterator();
                int i2 = 0;
                while (it.hasNext()) {
                    Object next = it.next();
                    if (i2 == i) {
                        int i3 = 2147483639;
                        if (i < 2147483639) {
                            if (i < 1073741819) {
                                i3 = (i >>> 1) + 1 + i;
                            }
                            objArr = Arrays.copyOf(objArr, i3);
                            i = i3;
                        } else {
                            throw new OutOfMemoryError("Required array size too large");
                        }
                    }
                    objArr[i2] = next;
                    i2++;
                }
                return i2 == i ? objArr : Arrays.copyOf(objArr, i2);
            }
            throw new OutOfMemoryError("Required array size too large");
        }

        public final Object[] toArray(Object[] objArr) {
            long mappingCount = this.map.mappingCount();
            if (mappingCount <= 2147483639) {
                int i = (int) mappingCount;
                Object[] objArr2 = objArr.length >= i ? objArr : (Object[]) Array.newInstance(objArr.getClass().getComponentType(), i);
                int length = objArr2.length;
                Iterator it = iterator();
                int i2 = 0;
                while (it.hasNext()) {
                    Object next = it.next();
                    if (i2 == length) {
                        int i3 = 2147483639;
                        if (length < 2147483639) {
                            if (length < 1073741819) {
                                i3 = (length >>> 1) + 1 + length;
                            }
                            objArr2 = Arrays.copyOf(objArr2, i3);
                            length = i3;
                        } else {
                            throw new OutOfMemoryError("Required array size too large");
                        }
                    }
                    objArr2[i2] = next;
                    i2++;
                }
                if (objArr != objArr2 || i2 >= length) {
                    return i2 == length ? objArr2 : Arrays.copyOf(objArr2, i2);
                }
                objArr2[i2] = null;
                return objArr2;
            }
            throw new OutOfMemoryError("Required array size too large");
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(AbstractJsonLexerKt.BEGIN_LIST);
            Iterator it = iterator();
            if (it.hasNext()) {
                while (true) {
                    Object next = it.next();
                    if (next == this) {
                        next = "(this Collection)";
                    }
                    sb.append(next);
                    if (!it.hasNext()) {
                        break;
                    }
                    sb.append(',');
                    sb.append(' ');
                }
            }
            sb.append(AbstractJsonLexerKt.END_LIST);
            return sb.toString();
        }
    }

    static final class CounterCell {
        volatile long value;

        CounterCell(long j) {
            this.value = j;
        }
    }

    static final class EntryIterator extends BaseIterator implements Iterator {
        EntryIterator(Node[] nodeArr, int i, int i2, int i3, ConcurrentHashMap concurrentHashMap) {
            super(nodeArr, i, i2, i3, concurrentHashMap);
        }

        public final Map.Entry next() {
            Node node = this.next;
            if (node != null) {
                Object obj = node.key;
                Object obj2 = node.val;
                this.lastReturned = node;
                advance();
                return new MapEntry(obj, obj2, this.map);
            }
            throw new NoSuchElementException();
        }
    }

    static final class EntrySetView extends CollectionView implements Set, Serializable, j$.util.Collection {
        private static final long serialVersionUID = 2249069246763182397L;

        EntrySetView(ConcurrentHashMap concurrentHashMap) {
            super(concurrentHashMap);
        }

        public boolean add(Map.Entry entry) {
            return this.map.putVal(entry.getKey(), entry.getValue(), false) == null;
        }

        public boolean addAll(Collection collection) {
            Iterator it = collection.iterator();
            boolean z = false;
            while (it.hasNext()) {
                if (add((Map.Entry) it.next())) {
                    z = true;
                }
            }
            return z;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:4:0x000c, code lost:
            r0 = r2.map.get((r0 = r3.getKey()));
         */
        /* JADX WARNING: Code restructure failed: missing block: B:6:0x0014, code lost:
            r3 = (r3 = (java.util.Map.Entry) r3).getValue();
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean contains(java.lang.Object r3) {
            /*
                r2 = this;
                boolean r0 = r3 instanceof java.util.Map.Entry
                if (r0 == 0) goto L_0x0024
                java.util.Map$Entry r3 = (java.util.Map.Entry) r3
                java.lang.Object r0 = r3.getKey()
                if (r0 == 0) goto L_0x0024
                j$.util.concurrent.ConcurrentHashMap r1 = r2.map
                java.lang.Object r0 = r1.get(r0)
                if (r0 == 0) goto L_0x0024
                java.lang.Object r3 = r3.getValue()
                if (r3 == 0) goto L_0x0024
                if (r3 == r0) goto L_0x0022
                boolean r3 = r3.equals(r0)
                if (r3 == 0) goto L_0x0024
            L_0x0022:
                r3 = 1
                goto L_0x0025
            L_0x0024:
                r3 = 0
            L_0x0025:
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: j$.util.concurrent.ConcurrentHashMap.EntrySetView.contains(java.lang.Object):boolean");
        }

        /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
            r2 = (java.util.Set) r2;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final boolean equals(java.lang.Object r2) {
            /*
                r1 = this;
                boolean r0 = r2 instanceof java.util.Set
                if (r0 == 0) goto L_0x0016
                java.util.Set r2 = (java.util.Set) r2
                if (r2 == r1) goto L_0x0014
                boolean r0 = r1.containsAll(r2)
                if (r0 == 0) goto L_0x0016
                boolean r2 = r2.containsAll(r1)
                if (r2 == 0) goto L_0x0016
            L_0x0014:
                r2 = 1
                goto L_0x0017
            L_0x0016:
                r2 = 0
            L_0x0017:
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: j$.util.concurrent.ConcurrentHashMap.EntrySetView.equals(java.lang.Object):boolean");
        }

        public void forEach(Consumer consumer) {
            consumer.getClass();
            Node[] nodeArr = this.map.table;
            if (nodeArr != null) {
                Traverser traverser = new Traverser(nodeArr, nodeArr.length, 0, nodeArr.length);
                while (true) {
                    Node advance = traverser.advance();
                    if (advance != null) {
                        consumer.accept(new MapEntry(advance.key, advance.val, this.map));
                    } else {
                        return;
                    }
                }
            }
        }

        public final int hashCode() {
            Node[] nodeArr = this.map.table;
            int i = 0;
            if (nodeArr != null) {
                Traverser traverser = new Traverser(nodeArr, nodeArr.length, 0, nodeArr.length);
                while (true) {
                    Node advance = traverser.advance();
                    if (advance == null) {
                        break;
                    }
                    i += advance.hashCode();
                }
            }
            return i;
        }

        public Iterator iterator() {
            ConcurrentHashMap concurrentHashMap = this.map;
            Node[] nodeArr = concurrentHashMap.table;
            int length = nodeArr == null ? 0 : nodeArr.length;
            return new EntryIterator(nodeArr, length, 0, length, concurrentHashMap);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
            r3 = (java.util.Map.Entry) r3;
            r0 = r3.getKey();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:4:0x000c, code lost:
            r3 = r3.getValue();
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean remove(java.lang.Object r3) {
            /*
                r2 = this;
                boolean r0 = r3 instanceof java.util.Map.Entry
                if (r0 == 0) goto L_0x001c
                java.util.Map$Entry r3 = (java.util.Map.Entry) r3
                java.lang.Object r0 = r3.getKey()
                if (r0 == 0) goto L_0x001c
                java.lang.Object r3 = r3.getValue()
                if (r3 == 0) goto L_0x001c
                j$.util.concurrent.ConcurrentHashMap r1 = r2.map
                boolean r3 = r1.remove(r0, r3)
                if (r3 == 0) goto L_0x001c
                r3 = 1
                goto L_0x001d
            L_0x001c:
                r3 = 0
            L_0x001d:
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: j$.util.concurrent.ConcurrentHashMap.EntrySetView.remove(java.lang.Object):boolean");
        }

        public boolean removeIf(Predicate predicate) {
            return this.map.removeEntryIf(predicate);
        }

        public Spliterator spliterator() {
            ConcurrentHashMap concurrentHashMap = this.map;
            long sumCount = concurrentHashMap.sumCount();
            Node[] nodeArr = concurrentHashMap.table;
            int length = nodeArr == null ? 0 : nodeArr.length;
            long j = 0;
            if (sumCount >= 0) {
                j = sumCount;
            }
            return new EntrySpliterator(nodeArr, length, 0, length, j, concurrentHashMap);
        }

        public /* synthetic */ Object[] toArray(IntFunction intFunction) {
            return Collection.CC.$default$toArray(this, intFunction);
        }
    }

    static final class EntrySpliterator extends Traverser implements Spliterator {
        long est;
        final ConcurrentHashMap map;

        EntrySpliterator(Node[] nodeArr, int i, int i2, int i3, long j, ConcurrentHashMap concurrentHashMap) {
            super(nodeArr, i, i2, i3);
            this.map = concurrentHashMap;
            this.est = j;
        }

        public int characteristics() {
            return 4353;
        }

        public long estimateSize() {
            return this.est;
        }

        public void forEachRemaining(Consumer consumer) {
            consumer.getClass();
            while (true) {
                Node advance = advance();
                if (advance != null) {
                    consumer.accept(new MapEntry(advance.key, advance.val, this.map));
                } else {
                    return;
                }
            }
        }

        public /* synthetic */ Comparator getComparator() {
            return Spliterator.CC.$default$getComparator(this);
        }

        public /* synthetic */ long getExactSizeIfKnown() {
            return Spliterator.CC.$default$getExactSizeIfKnown(this);
        }

        public /* synthetic */ boolean hasCharacteristics(int i) {
            return Spliterator.CC.$default$hasCharacteristics(this, i);
        }

        public boolean tryAdvance(Consumer consumer) {
            consumer.getClass();
            Node advance = advance();
            if (advance == null) {
                return false;
            }
            consumer.accept(new MapEntry(advance.key, advance.val, this.map));
            return true;
        }

        public EntrySpliterator trySplit() {
            int i = this.baseIndex;
            int i2 = this.baseLimit;
            int i3 = (i + i2) >>> 1;
            if (i3 <= i) {
                return null;
            }
            Node[] nodeArr = this.tab;
            int i4 = this.baseSize;
            this.baseLimit = i3;
            long j = this.est >>> 1;
            this.est = j;
            return new EntrySpliterator(nodeArr, i4, i3, i2, j, this.map);
        }
    }

    static final class ForwardingNode extends Node {
        final Node[] nextTable;

        ForwardingNode(Node[] nodeArr) {
            super(-1, (Object) null, (Object) null);
            this.nextTable = nodeArr;
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Code restructure failed: missing block: B:18:0x0029, code lost:
            if ((r0 instanceof j$.util.concurrent.ConcurrentHashMap.ForwardingNode) == false) goto L_0x0030;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:0x002b, code lost:
            r0 = ((j$.util.concurrent.ConcurrentHashMap.ForwardingNode) r0).nextTable;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:21:0x0034, code lost:
            return r0.find(r5, r6);
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public j$.util.concurrent.ConcurrentHashMap.Node find(int r5, java.lang.Object r6) {
            /*
                r4 = this;
                j$.util.concurrent.ConcurrentHashMap$Node[] r0 = r4.nextTable
            L_0x0002:
                r1 = 0
                if (r6 == 0) goto L_0x0039
                if (r0 == 0) goto L_0x0039
                int r2 = r0.length
                if (r2 == 0) goto L_0x0039
                int r2 = r2 + -1
                r2 = r2 & r5
                j$.util.concurrent.ConcurrentHashMap$Node r0 = j$.util.concurrent.ConcurrentHashMap.tabAt(r0, r2)
                if (r0 != 0) goto L_0x0014
                goto L_0x0039
            L_0x0014:
                int r2 = r0.hash
                if (r2 != r5) goto L_0x0025
                java.lang.Object r3 = r0.key
                if (r3 == r6) goto L_0x0024
                if (r3 == 0) goto L_0x0025
                boolean r3 = r6.equals(r3)
                if (r3 == 0) goto L_0x0025
            L_0x0024:
                return r0
            L_0x0025:
                if (r2 >= 0) goto L_0x0035
                boolean r1 = r0 instanceof j$.util.concurrent.ConcurrentHashMap.ForwardingNode
                if (r1 == 0) goto L_0x0030
                j$.util.concurrent.ConcurrentHashMap$ForwardingNode r0 = (j$.util.concurrent.ConcurrentHashMap.ForwardingNode) r0
                j$.util.concurrent.ConcurrentHashMap$Node[] r0 = r0.nextTable
                goto L_0x0002
            L_0x0030:
                j$.util.concurrent.ConcurrentHashMap$Node r5 = r0.find(r5, r6)
                return r5
            L_0x0035:
                j$.util.concurrent.ConcurrentHashMap$Node r0 = r0.next
                if (r0 != 0) goto L_0x0014
            L_0x0039:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: j$.util.concurrent.ConcurrentHashMap.ForwardingNode.find(int, java.lang.Object):j$.util.concurrent.ConcurrentHashMap$Node");
        }
    }

    static final class KeyIterator extends BaseIterator implements Iterator, Enumeration {
        KeyIterator(Node[] nodeArr, int i, int i2, int i3, ConcurrentHashMap concurrentHashMap) {
            super(nodeArr, i, i2, i3, concurrentHashMap);
        }

        public final Object next() {
            Node node = this.next;
            if (node != null) {
                Object obj = node.key;
                this.lastReturned = node;
                advance();
                return obj;
            }
            throw new NoSuchElementException();
        }

        public final Object nextElement() {
            return next();
        }
    }

    public static class KeySetView extends CollectionView implements Set, Serializable, j$.util.Collection {
        private static final long serialVersionUID = 7249069246763182397L;
        private final Object value;

        KeySetView(ConcurrentHashMap concurrentHashMap, Object obj) {
            super(concurrentHashMap);
            this.value = obj;
        }

        public boolean add(Object obj) {
            Object obj2 = this.value;
            if (obj2 != null) {
                return this.map.putVal(obj, obj2, true) == null;
            }
            throw new UnsupportedOperationException();
        }

        public boolean addAll(java.util.Collection collection) {
            Object obj = this.value;
            if (obj != null) {
                boolean z = false;
                for (Object putVal : collection) {
                    if (this.map.putVal(putVal, obj, true) == null) {
                        z = true;
                    }
                }
                return z;
            }
            throw new UnsupportedOperationException();
        }

        public boolean contains(Object obj) {
            return this.map.containsKey(obj);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
            r2 = (java.util.Set) r2;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean equals(java.lang.Object r2) {
            /*
                r1 = this;
                boolean r0 = r2 instanceof java.util.Set
                if (r0 == 0) goto L_0x0016
                java.util.Set r2 = (java.util.Set) r2
                if (r2 == r1) goto L_0x0014
                boolean r0 = r1.containsAll(r2)
                if (r0 == 0) goto L_0x0016
                boolean r2 = r2.containsAll(r1)
                if (r2 == 0) goto L_0x0016
            L_0x0014:
                r2 = 1
                goto L_0x0017
            L_0x0016:
                r2 = 0
            L_0x0017:
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: j$.util.concurrent.ConcurrentHashMap.KeySetView.equals(java.lang.Object):boolean");
        }

        public void forEach(Consumer consumer) {
            consumer.getClass();
            Node[] nodeArr = this.map.table;
            if (nodeArr != null) {
                Traverser traverser = new Traverser(nodeArr, nodeArr.length, 0, nodeArr.length);
                while (true) {
                    Node advance = traverser.advance();
                    if (advance != null) {
                        consumer.accept(advance.key);
                    } else {
                        return;
                    }
                }
            }
        }

        public int hashCode() {
            Iterator it = iterator();
            int i = 0;
            while (it.hasNext()) {
                i += it.next().hashCode();
            }
            return i;
        }

        public Iterator iterator() {
            ConcurrentHashMap concurrentHashMap = this.map;
            Node[] nodeArr = concurrentHashMap.table;
            int length = nodeArr == null ? 0 : nodeArr.length;
            return new KeyIterator(nodeArr, length, 0, length, concurrentHashMap);
        }

        public boolean remove(Object obj) {
            return this.map.remove(obj) != null;
        }

        public /* bridge */ /* synthetic */ boolean removeAll(java.util.Collection collection) {
            return super.removeAll(collection);
        }

        public /* synthetic */ boolean removeIf(Predicate predicate) {
            return Collection.CC.$default$removeIf(this, predicate);
        }

        public Spliterator spliterator() {
            ConcurrentHashMap concurrentHashMap = this.map;
            long sumCount = concurrentHashMap.sumCount();
            Node[] nodeArr = concurrentHashMap.table;
            int length = nodeArr == null ? 0 : nodeArr.length;
            return new KeySpliterator(nodeArr, length, 0, length, sumCount < 0 ? 0 : sumCount);
        }

        public /* synthetic */ Object[] toArray(IntFunction intFunction) {
            return Collection.CC.$default$toArray(this, intFunction);
        }
    }

    static final class KeySpliterator extends Traverser implements Spliterator {
        long est;

        KeySpliterator(Node[] nodeArr, int i, int i2, int i3, long j) {
            super(nodeArr, i, i2, i3);
            this.est = j;
        }

        public int characteristics() {
            return 4353;
        }

        public long estimateSize() {
            return this.est;
        }

        public void forEachRemaining(Consumer consumer) {
            consumer.getClass();
            while (true) {
                Node advance = advance();
                if (advance != null) {
                    consumer.accept(advance.key);
                } else {
                    return;
                }
            }
        }

        public /* synthetic */ Comparator getComparator() {
            return Spliterator.CC.$default$getComparator(this);
        }

        public /* synthetic */ long getExactSizeIfKnown() {
            return Spliterator.CC.$default$getExactSizeIfKnown(this);
        }

        public /* synthetic */ boolean hasCharacteristics(int i) {
            return Spliterator.CC.$default$hasCharacteristics(this, i);
        }

        public boolean tryAdvance(Consumer consumer) {
            consumer.getClass();
            Node advance = advance();
            if (advance == null) {
                return false;
            }
            consumer.accept(advance.key);
            return true;
        }

        public KeySpliterator trySplit() {
            int i = this.baseIndex;
            int i2 = this.baseLimit;
            int i3 = (i + i2) >>> 1;
            if (i3 <= i) {
                return null;
            }
            Node[] nodeArr = this.tab;
            int i4 = this.baseSize;
            this.baseLimit = i3;
            long j = this.est >>> 1;
            this.est = j;
            return new KeySpliterator(nodeArr, i4, i3, i2, j);
        }
    }

    static final class MapEntry implements Map.Entry {
        final Object key;
        final ConcurrentHashMap map;
        Object val;

        MapEntry(Object obj, Object obj2, ConcurrentHashMap concurrentHashMap) {
            this.key = obj;
            this.val = obj2;
            this.map = concurrentHashMap;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:10:0x001c, code lost:
            r0 = r2.val;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
            r3 = (java.util.Map.Entry) r3;
            r0 = r3.getKey();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:4:0x000c, code lost:
            r3 = r3.getValue();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:6:0x0012, code lost:
            r1 = r2.key;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean equals(java.lang.Object r3) {
            /*
                r2 = this;
                boolean r0 = r3 instanceof java.util.Map.Entry
                if (r0 == 0) goto L_0x0028
                java.util.Map$Entry r3 = (java.util.Map.Entry) r3
                java.lang.Object r0 = r3.getKey()
                if (r0 == 0) goto L_0x0028
                java.lang.Object r3 = r3.getValue()
                if (r3 == 0) goto L_0x0028
                java.lang.Object r1 = r2.key
                if (r0 == r1) goto L_0x001c
                boolean r0 = r0.equals(r1)
                if (r0 == 0) goto L_0x0028
            L_0x001c:
                java.lang.Object r0 = r2.val
                if (r3 == r0) goto L_0x0026
                boolean r3 = r3.equals(r0)
                if (r3 == 0) goto L_0x0028
            L_0x0026:
                r3 = 1
                goto L_0x0029
            L_0x0028:
                r3 = 0
            L_0x0029:
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: j$.util.concurrent.ConcurrentHashMap.MapEntry.equals(java.lang.Object):boolean");
        }

        public Object getKey() {
            return this.key;
        }

        public Object getValue() {
            return this.val;
        }

        public int hashCode() {
            return this.key.hashCode() ^ this.val.hashCode();
        }

        public Object setValue(Object obj) {
            obj.getClass();
            Object obj2 = this.val;
            this.val = obj;
            this.map.put(this.key, obj);
            return obj2;
        }

        public String toString() {
            return Helpers.mapEntryToString(this.key, this.val);
        }
    }

    static class Node implements Map.Entry {
        final int hash;
        final Object key;
        volatile Node next;
        volatile Object val;

        Node(int i, Object obj, Object obj2) {
            this.hash = i;
            this.key = obj;
            this.val = obj2;
        }

        Node(int i, Object obj, Object obj2, Node node) {
            this(i, obj, obj2);
            this.next = node;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:10:0x001c, code lost:
            r0 = r2.val;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
            r3 = (java.util.Map.Entry) r3;
            r0 = r3.getKey();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:4:0x000c, code lost:
            r3 = r3.getValue();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:6:0x0012, code lost:
            r1 = r2.key;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final boolean equals(java.lang.Object r3) {
            /*
                r2 = this;
                boolean r0 = r3 instanceof java.util.Map.Entry
                if (r0 == 0) goto L_0x0028
                java.util.Map$Entry r3 = (java.util.Map.Entry) r3
                java.lang.Object r0 = r3.getKey()
                if (r0 == 0) goto L_0x0028
                java.lang.Object r3 = r3.getValue()
                if (r3 == 0) goto L_0x0028
                java.lang.Object r1 = r2.key
                if (r0 == r1) goto L_0x001c
                boolean r0 = r0.equals(r1)
                if (r0 == 0) goto L_0x0028
            L_0x001c:
                java.lang.Object r0 = r2.val
                if (r3 == r0) goto L_0x0026
                boolean r3 = r3.equals(r0)
                if (r3 == 0) goto L_0x0028
            L_0x0026:
                r3 = 1
                goto L_0x0029
            L_0x0028:
                r3 = 0
            L_0x0029:
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: j$.util.concurrent.ConcurrentHashMap.Node.equals(java.lang.Object):boolean");
        }

        /* access modifiers changed from: package-private */
        public Node find(int i, Object obj) {
            Object obj2;
            if (obj == null) {
                return null;
            }
            Node node = this;
            do {
                if (node.hash == i && ((obj2 = node.key) == obj || (obj2 != null && obj.equals(obj2)))) {
                    return node;
                }
                node = node.next;
            } while (node != null);
            return null;
        }

        public final Object getKey() {
            return this.key;
        }

        public final Object getValue() {
            return this.val;
        }

        public final int hashCode() {
            return this.key.hashCode() ^ this.val.hashCode();
        }

        public final Object setValue(Object obj) {
            throw new UnsupportedOperationException();
        }

        public final String toString() {
            return Helpers.mapEntryToString(this.key, this.val);
        }
    }

    static final class ReservationNode extends Node {
        ReservationNode() {
            super(-3, (Object) null, (Object) null);
        }

        /* access modifiers changed from: package-private */
        public Node find(int i, Object obj) {
            return null;
        }
    }

    static class Segment extends ReentrantLock implements Serializable {
        private static final long serialVersionUID = 2249069246763182397L;
        final float loadFactor;

        Segment(float f) {
            this.loadFactor = f;
        }
    }

    static final class TableStack {
        int index;
        int length;
        TableStack next;
        Node[] tab;

        TableStack() {
        }
    }

    static class Traverser {
        int baseIndex;
        int baseLimit;
        final int baseSize;
        int index;
        Node next = null;
        TableStack spare;
        TableStack stack;
        Node[] tab;

        Traverser(Node[] nodeArr, int i, int i2, int i3) {
            this.tab = nodeArr;
            this.baseSize = i;
            this.index = i2;
            this.baseIndex = i2;
            this.baseLimit = i3;
        }

        private void pushState(Node[] nodeArr, int i, int i2) {
            TableStack tableStack = this.spare;
            if (tableStack != null) {
                this.spare = tableStack.next;
            } else {
                tableStack = new TableStack();
            }
            tableStack.tab = nodeArr;
            tableStack.length = i2;
            tableStack.index = i;
            tableStack.next = this.stack;
            this.stack = tableStack;
        }

        private void recoverState(int i) {
            TableStack tableStack;
            while (true) {
                tableStack = this.stack;
                if (tableStack == null) {
                    break;
                }
                int i2 = this.index;
                int i3 = tableStack.length;
                int i4 = i2 + i3;
                this.index = i4;
                if (i4 < i) {
                    break;
                }
                this.index = tableStack.index;
                this.tab = tableStack.tab;
                tableStack.tab = null;
                TableStack tableStack2 = tableStack.next;
                tableStack.next = this.spare;
                this.stack = tableStack2;
                this.spare = tableStack;
                i = i3;
            }
            if (tableStack == null) {
                int i5 = this.index + this.baseSize;
                this.index = i5;
                if (i5 >= i) {
                    int i6 = this.baseIndex + 1;
                    this.baseIndex = i6;
                    this.index = i6;
                }
            }
        }

        /* access modifiers changed from: package-private */
        public final Node advance() {
            Node node;
            Node[] nodeArr;
            int length;
            int i;
            Node node2 = this.next;
            if (node2 != null) {
                node2 = node2.next;
            }
            while (node == null) {
                if (this.baseIndex >= this.baseLimit || (nodeArr = this.tab) == null || (length = nodeArr.length) <= (i = this.index) || i < 0) {
                    this.next = null;
                    return null;
                }
                Node tabAt = ConcurrentHashMap.tabAt(nodeArr, i);
                if (tabAt == null || tabAt.hash >= 0) {
                    node = tabAt;
                } else if (tabAt instanceof ForwardingNode) {
                    this.tab = ((ForwardingNode) tabAt).nextTable;
                    pushState(nodeArr, i, length);
                    node = null;
                } else {
                    node = tabAt instanceof TreeBin ? ((TreeBin) tabAt).first : null;
                }
                if (this.stack != null) {
                    recoverState(length);
                } else {
                    int i2 = i + this.baseSize;
                    this.index = i2;
                    if (i2 >= length) {
                        int i3 = this.baseIndex + 1;
                        this.baseIndex = i3;
                        this.index = i3;
                    }
                }
            }
            this.next = node;
            return node;
        }
    }

    static final class TreeBin extends Node {
        private static final long LOCKSTATE;
        private static final DesugarUnsafe U;
        volatile TreeNode first;
        volatile int lockState;
        TreeNode root;
        volatile Thread waiter;

        static {
            DesugarUnsafe unsafe = DesugarUnsafe.getUnsafe();
            U = unsafe;
            LOCKSTATE = unsafe.objectFieldOffset(TreeBin.class, "lockState");
        }

        /* JADX WARNING: Code restructure failed: missing block: B:13:0x002f, code lost:
            r6 = j$.util.concurrent.ConcurrentHashMap.comparableClassFor(r3);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:15:0x0035, code lost:
            r8 = j$.util.concurrent.ConcurrentHashMap.compareComparables(r6, r3, r7);
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        TreeBin(j$.util.concurrent.ConcurrentHashMap.TreeNode r10) {
            /*
                r9 = this;
                r0 = -2
                r1 = 0
                r9.<init>(r0, r1, r1)
                r9.first = r10
                r0 = r1
            L_0x0008:
                if (r10 == 0) goto L_0x005c
                j$.util.concurrent.ConcurrentHashMap$Node r2 = r10.next
                j$.util.concurrent.ConcurrentHashMap$TreeNode r2 = (j$.util.concurrent.ConcurrentHashMap.TreeNode) r2
                r10.right = r1
                r10.left = r1
                if (r0 != 0) goto L_0x001b
                r10.parent = r1
                r0 = 0
                r10.red = r0
            L_0x0019:
                r0 = r10
                goto L_0x0058
            L_0x001b:
                java.lang.Object r3 = r10.key
                int r4 = r10.hash
                r5 = r0
                r6 = r1
            L_0x0021:
                java.lang.Object r7 = r5.key
                int r8 = r5.hash
                if (r8 <= r4) goto L_0x0029
                r7 = -1
                goto L_0x0041
            L_0x0029:
                if (r8 >= r4) goto L_0x002d
                r7 = 1
                goto L_0x0041
            L_0x002d:
                if (r6 != 0) goto L_0x0035
                java.lang.Class r6 = j$.util.concurrent.ConcurrentHashMap.comparableClassFor(r3)
                if (r6 == 0) goto L_0x003b
            L_0x0035:
                int r8 = j$.util.concurrent.ConcurrentHashMap.compareComparables(r6, r3, r7)
                if (r8 != 0) goto L_0x0040
            L_0x003b:
                int r7 = tieBreakOrder(r3, r7)
                goto L_0x0041
            L_0x0040:
                r7 = r8
            L_0x0041:
                if (r7 > 0) goto L_0x0046
                j$.util.concurrent.ConcurrentHashMap$TreeNode r8 = r5.left
                goto L_0x0048
            L_0x0046:
                j$.util.concurrent.ConcurrentHashMap$TreeNode r8 = r5.right
            L_0x0048:
                if (r8 != 0) goto L_0x005a
                r10.parent = r5
                if (r7 > 0) goto L_0x0051
                r5.left = r10
                goto L_0x0053
            L_0x0051:
                r5.right = r10
            L_0x0053:
                j$.util.concurrent.ConcurrentHashMap$TreeNode r10 = balanceInsertion(r0, r10)
                goto L_0x0019
            L_0x0058:
                r10 = r2
                goto L_0x0008
            L_0x005a:
                r5 = r8
                goto L_0x0021
            L_0x005c:
                r9.root = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: j$.util.concurrent.ConcurrentHashMap.TreeBin.<init>(j$.util.concurrent.ConcurrentHashMap$TreeNode):void");
        }

        static TreeNode balanceDeletion(TreeNode treeNode, TreeNode treeNode2) {
            TreeNode treeNode3;
            while (treeNode2 != null && treeNode2 != treeNode) {
                TreeNode treeNode4 = treeNode2.parent;
                if (treeNode4 == null) {
                    treeNode2.red = false;
                    return treeNode2;
                } else if (treeNode2.red) {
                    treeNode2.red = false;
                    return treeNode;
                } else {
                    TreeNode treeNode5 = treeNode4.left;
                    TreeNode treeNode6 = null;
                    if (treeNode5 == treeNode2) {
                        treeNode3 = treeNode4.right;
                        if (treeNode3 != null && treeNode3.red) {
                            treeNode3.red = false;
                            treeNode4.red = true;
                            treeNode = rotateLeft(treeNode, treeNode4);
                            treeNode4 = treeNode2.parent;
                            treeNode3 = treeNode4 == null ? null : treeNode4.right;
                        }
                        if (treeNode3 != null) {
                            TreeNode treeNode7 = treeNode3.left;
                            TreeNode treeNode8 = treeNode3.right;
                            if ((treeNode8 != null && treeNode8.red) || (treeNode7 != null && treeNode7.red)) {
                                if (treeNode8 == null || !treeNode8.red) {
                                    if (treeNode7 != null) {
                                        treeNode7.red = false;
                                    }
                                    treeNode3.red = true;
                                    treeNode = rotateRight(treeNode, treeNode3);
                                    treeNode4 = treeNode2.parent;
                                    if (treeNode4 != null) {
                                        treeNode6 = treeNode4.right;
                                    }
                                    treeNode3 = treeNode6;
                                }
                                if (treeNode3 != null) {
                                    treeNode3.red = treeNode4 == null ? false : treeNode4.red;
                                    TreeNode treeNode9 = treeNode3.right;
                                    if (treeNode9 != null) {
                                        treeNode9.red = false;
                                    }
                                }
                                if (treeNode4 != null) {
                                    treeNode4.red = false;
                                    treeNode = rotateLeft(treeNode, treeNode4);
                                }
                                treeNode2 = treeNode;
                            }
                            treeNode3.red = true;
                        }
                    } else {
                        if (treeNode5 != null && treeNode5.red) {
                            treeNode5.red = false;
                            treeNode4.red = true;
                            treeNode = rotateRight(treeNode, treeNode4);
                            treeNode4 = treeNode2.parent;
                            treeNode5 = treeNode4 == null ? null : treeNode4.left;
                        }
                        if (treeNode3 != null) {
                            TreeNode treeNode10 = treeNode3.left;
                            TreeNode treeNode11 = treeNode3.right;
                            if ((treeNode10 != null && treeNode10.red) || (treeNode11 != null && treeNode11.red)) {
                                if (treeNode10 == null || !treeNode10.red) {
                                    if (treeNode11 != null) {
                                        treeNode11.red = false;
                                    }
                                    treeNode3.red = true;
                                    treeNode = rotateLeft(treeNode, treeNode3);
                                    treeNode4 = treeNode2.parent;
                                    if (treeNode4 != null) {
                                        treeNode6 = treeNode4.left;
                                    }
                                    treeNode3 = treeNode6;
                                }
                                if (treeNode3 != null) {
                                    treeNode3.red = treeNode4 == null ? false : treeNode4.red;
                                    TreeNode treeNode12 = treeNode3.left;
                                    if (treeNode12 != null) {
                                        treeNode12.red = false;
                                    }
                                }
                                if (treeNode4 != null) {
                                    treeNode4.red = false;
                                    treeNode = rotateRight(treeNode, treeNode4);
                                }
                                treeNode2 = treeNode;
                            }
                            treeNode3.red = true;
                        }
                    }
                    treeNode2 = treeNode4;
                }
            }
            return treeNode;
        }

        static TreeNode balanceInsertion(TreeNode treeNode, TreeNode treeNode2) {
            TreeNode treeNode3;
            treeNode2.red = true;
            while (true) {
                TreeNode treeNode4 = treeNode2.parent;
                if (treeNode4 == null) {
                    treeNode2.red = false;
                    return treeNode2;
                } else if (!treeNode4.red || (treeNode3 = treeNode4.parent) == null) {
                    return treeNode;
                } else {
                    TreeNode treeNode5 = treeNode3.left;
                    if (treeNode4 == treeNode5) {
                        treeNode5 = treeNode3.right;
                        if (treeNode5 == null || !treeNode5.red) {
                            if (treeNode2 == treeNode4.right) {
                                treeNode = rotateLeft(treeNode, treeNode4);
                                TreeNode treeNode6 = treeNode4.parent;
                                treeNode3 = treeNode6 == null ? null : treeNode6.parent;
                                TreeNode treeNode7 = treeNode4;
                                treeNode4 = treeNode6;
                                treeNode2 = treeNode7;
                            }
                            if (treeNode4 != null) {
                                treeNode4.red = false;
                                if (treeNode3 != null) {
                                    treeNode3.red = true;
                                    treeNode = rotateRight(treeNode, treeNode3);
                                }
                            }
                        }
                    } else if (treeNode5 == null || !treeNode5.red) {
                        if (treeNode2 == treeNode4.left) {
                            treeNode = rotateRight(treeNode, treeNode4);
                            TreeNode treeNode8 = treeNode4.parent;
                            treeNode3 = treeNode8 == null ? null : treeNode8.parent;
                            TreeNode treeNode9 = treeNode4;
                            treeNode4 = treeNode8;
                            treeNode2 = treeNode9;
                        }
                        if (treeNode4 != null) {
                            treeNode4.red = false;
                            if (treeNode3 != null) {
                                treeNode3.red = true;
                                treeNode = rotateLeft(treeNode, treeNode3);
                            }
                        }
                    }
                    treeNode5.red = false;
                    treeNode4.red = false;
                    treeNode3.red = true;
                    treeNode2 = treeNode3;
                }
            }
            return treeNode;
        }

        private final void contendedLock() {
            boolean z = false;
            while (true) {
                int i = this.lockState;
                if ((i & -3) == 0) {
                    if (U.compareAndSetInt(this, LOCKSTATE, i, 1)) {
                        break;
                    }
                } else if ((i & 2) == 0) {
                    if (U.compareAndSetInt(this, LOCKSTATE, i, i | 2)) {
                        this.waiter = Thread.currentThread();
                        z = true;
                    }
                } else if (z) {
                    LockSupport.park(this);
                }
            }
            if (z) {
                this.waiter = null;
            }
        }

        private final void lockRoot() {
            if (!U.compareAndSetInt(this, LOCKSTATE, 0, 1)) {
                contendedLock();
            }
        }

        static TreeNode rotateLeft(TreeNode treeNode, TreeNode treeNode2) {
            TreeNode treeNode3;
            if (!(treeNode2 == null || (treeNode3 = treeNode2.right) == null)) {
                TreeNode treeNode4 = treeNode3.left;
                treeNode2.right = treeNode4;
                if (treeNode4 != null) {
                    treeNode4.parent = treeNode2;
                }
                TreeNode treeNode5 = treeNode2.parent;
                treeNode3.parent = treeNode5;
                if (treeNode5 == null) {
                    treeNode3.red = false;
                    treeNode = treeNode3;
                } else if (treeNode5.left == treeNode2) {
                    treeNode5.left = treeNode3;
                } else {
                    treeNode5.right = treeNode3;
                }
                treeNode3.left = treeNode2;
                treeNode2.parent = treeNode3;
            }
            return treeNode;
        }

        static TreeNode rotateRight(TreeNode treeNode, TreeNode treeNode2) {
            TreeNode treeNode3;
            if (!(treeNode2 == null || (treeNode3 = treeNode2.left) == null)) {
                TreeNode treeNode4 = treeNode3.right;
                treeNode2.left = treeNode4;
                if (treeNode4 != null) {
                    treeNode4.parent = treeNode2;
                }
                TreeNode treeNode5 = treeNode2.parent;
                treeNode3.parent = treeNode5;
                if (treeNode5 == null) {
                    treeNode3.red = false;
                    treeNode = treeNode3;
                } else if (treeNode5.right == treeNode2) {
                    treeNode5.right = treeNode3;
                } else {
                    treeNode5.left = treeNode3;
                }
                treeNode3.right = treeNode2;
                treeNode2.parent = treeNode3;
            }
            return treeNode;
        }

        static int tieBreakOrder(Object obj, Object obj2) {
            int compareTo;
            return (obj == null || obj2 == null || (compareTo = obj.getClass().getName().compareTo(obj2.getClass().getName())) == 0) ? System.identityHashCode(obj) <= System.identityHashCode(obj2) ? -1 : 1 : compareTo;
        }

        private final void unlockRoot() {
            this.lockState = 0;
        }

        /* access modifiers changed from: package-private */
        public final Node find(int i, Object obj) {
            Thread thread;
            Thread thread2;
            Object obj2;
            TreeNode treeNode = null;
            if (obj != null) {
                Node node = this.first;
                while (node != null) {
                    int i2 = this.lockState;
                    if ((i2 & 3) == 0) {
                        DesugarUnsafe desugarUnsafe = U;
                        long j = LOCKSTATE;
                        if (desugarUnsafe.compareAndSetInt(this, j, i2, i2 + 4)) {
                            try {
                                TreeNode treeNode2 = this.root;
                                if (treeNode2 != null) {
                                    treeNode = treeNode2.findTreeNode(i, obj, (Class) null);
                                }
                                if (desugarUnsafe.getAndAddInt(this, j, -4) == 6 && (thread2 = this.waiter) != null) {
                                    LockSupport.unpark(thread2);
                                }
                                return treeNode;
                            } catch (Throwable th) {
                                if (U.getAndAddInt(this, LOCKSTATE, -4) == 6 && (thread = this.waiter) != null) {
                                    LockSupport.unpark(thread);
                                }
                                throw th;
                            }
                        }
                    } else if (node.hash == i && ((obj2 = node.key) == obj || (obj2 != null && obj.equals(obj2)))) {
                        return node;
                    } else {
                        node = node.next;
                    }
                }
            }
            return null;
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Code restructure failed: missing block: B:27:0x0056, code lost:
            return r2;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:48:0x0097, code lost:
            return null;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final j$.util.concurrent.ConcurrentHashMap.TreeNode putTreeVal(int r13, java.lang.Object r14, java.lang.Object r15) {
            /*
                r12 = this;
                j$.util.concurrent.ConcurrentHashMap$TreeNode r0 = r12.root
                r1 = 0
                r2 = 0
                r3 = r1
            L_0x0005:
                if (r0 != 0) goto L_0x0018
                j$.util.concurrent.ConcurrentHashMap$TreeNode r0 = new j$.util.concurrent.ConcurrentHashMap$TreeNode
                r8 = 0
                r9 = 0
                r4 = r0
                r5 = r13
                r6 = r14
                r7 = r15
                r4.<init>(r5, r6, r7, r8, r9)
                r12.root = r0
                r12.first = r0
                goto L_0x0097
            L_0x0018:
                int r4 = r0.hash
                r9 = 1
                if (r4 <= r13) goto L_0x0020
                r4 = -1
                r10 = -1
                goto L_0x005f
            L_0x0020:
                if (r4 >= r13) goto L_0x0024
                r10 = 1
                goto L_0x005f
            L_0x0024:
                java.lang.Object r4 = r0.key
                if (r4 == r14) goto L_0x00a0
                if (r4 == 0) goto L_0x0032
                boolean r5 = r14.equals(r4)
                if (r5 == 0) goto L_0x0032
                goto L_0x00a0
            L_0x0032:
                if (r3 != 0) goto L_0x003a
                java.lang.Class r3 = j$.util.concurrent.ConcurrentHashMap.comparableClassFor(r14)
                if (r3 == 0) goto L_0x0040
            L_0x003a:
                int r5 = j$.util.concurrent.ConcurrentHashMap.compareComparables(r3, r14, r4)
                if (r5 != 0) goto L_0x005e
            L_0x0040:
                if (r2 != 0) goto L_0x0058
                j$.util.concurrent.ConcurrentHashMap$TreeNode r2 = r0.left
                if (r2 == 0) goto L_0x004c
                j$.util.concurrent.ConcurrentHashMap$TreeNode r2 = r2.findTreeNode(r13, r14, r3)
                if (r2 != 0) goto L_0x0056
            L_0x004c:
                j$.util.concurrent.ConcurrentHashMap$TreeNode r2 = r0.right
                if (r2 == 0) goto L_0x0057
                j$.util.concurrent.ConcurrentHashMap$TreeNode r2 = r2.findTreeNode(r13, r14, r3)
                if (r2 == 0) goto L_0x0057
            L_0x0056:
                return r2
            L_0x0057:
                r2 = 1
            L_0x0058:
                int r4 = tieBreakOrder(r14, r4)
                r10 = r4
                goto L_0x005f
            L_0x005e:
                r10 = r5
            L_0x005f:
                if (r10 > 0) goto L_0x0064
                j$.util.concurrent.ConcurrentHashMap$TreeNode r4 = r0.left
                goto L_0x0066
            L_0x0064:
                j$.util.concurrent.ConcurrentHashMap$TreeNode r4 = r0.right
            L_0x0066:
                if (r4 != 0) goto L_0x009d
                j$.util.concurrent.ConcurrentHashMap$TreeNode r2 = r12.first
                j$.util.concurrent.ConcurrentHashMap$TreeNode r11 = new j$.util.concurrent.ConcurrentHashMap$TreeNode
                r3 = r11
                r4 = r13
                r5 = r14
                r6 = r15
                r7 = r2
                r8 = r0
                r3.<init>(r4, r5, r6, r7, r8)
                r12.first = r11
                if (r2 == 0) goto L_0x007b
                r2.prev = r11
            L_0x007b:
                if (r10 > 0) goto L_0x0080
                r0.left = r11
                goto L_0x0082
            L_0x0080:
                r0.right = r11
            L_0x0082:
                boolean r13 = r0.red
                if (r13 != 0) goto L_0x0089
                r11.red = r9
                goto L_0x0097
            L_0x0089:
                r12.lockRoot()
                j$.util.concurrent.ConcurrentHashMap$TreeNode r13 = r12.root     // Catch:{ all -> 0x0098 }
                j$.util.concurrent.ConcurrentHashMap$TreeNode r13 = balanceInsertion(r13, r11)     // Catch:{ all -> 0x0098 }
                r12.root = r13     // Catch:{ all -> 0x0098 }
                r12.unlockRoot()
            L_0x0097:
                return r1
            L_0x0098:
                r13 = move-exception
                r12.unlockRoot()
                throw r13
            L_0x009d:
                r0 = r4
                goto L_0x0005
            L_0x00a0:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: j$.util.concurrent.ConcurrentHashMap.TreeBin.putTreeVal(int, java.lang.Object, java.lang.Object):j$.util.concurrent.ConcurrentHashMap$TreeNode");
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Removed duplicated region for block: B:57:0x0090 A[Catch:{ all -> 0x0051 }] */
        /* JADX WARNING: Removed duplicated region for block: B:67:0x00ab A[Catch:{ all -> 0x0051 }] */
        /* JADX WARNING: Removed duplicated region for block: B:68:0x00ac A[Catch:{ all -> 0x0051 }] */
        /* JADX WARNING: Removed duplicated region for block: B:75:0x00bc A[Catch:{ all -> 0x0051 }] */
        /* JADX WARNING: Removed duplicated region for block: B:76:0x00bf A[Catch:{ all -> 0x0051 }] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final boolean removeTreeNode(j$.util.concurrent.ConcurrentHashMap.TreeNode r10) {
            /*
                r9 = this;
                j$.util.concurrent.ConcurrentHashMap$Node r0 = r10.next
                j$.util.concurrent.ConcurrentHashMap$TreeNode r0 = (j$.util.concurrent.ConcurrentHashMap.TreeNode) r0
                j$.util.concurrent.ConcurrentHashMap$TreeNode r1 = r10.prev
                if (r1 != 0) goto L_0x000b
                r9.first = r0
                goto L_0x000d
            L_0x000b:
                r1.next = r0
            L_0x000d:
                if (r0 == 0) goto L_0x0011
                r0.prev = r1
            L_0x0011:
                j$.util.concurrent.ConcurrentHashMap$TreeNode r0 = r9.first
                r1 = 1
                r2 = 0
                if (r0 != 0) goto L_0x001a
                r9.root = r2
                return r1
            L_0x001a:
                j$.util.concurrent.ConcurrentHashMap$TreeNode r0 = r9.root
                if (r0 == 0) goto L_0x00d0
                j$.util.concurrent.ConcurrentHashMap$TreeNode r3 = r0.right
                if (r3 == 0) goto L_0x00d0
                j$.util.concurrent.ConcurrentHashMap$TreeNode r3 = r0.left
                if (r3 == 0) goto L_0x00d0
                j$.util.concurrent.ConcurrentHashMap$TreeNode r3 = r3.left
                if (r3 != 0) goto L_0x002c
                goto L_0x00d0
            L_0x002c:
                r9.lockRoot()
                j$.util.concurrent.ConcurrentHashMap$TreeNode r1 = r10.left     // Catch:{ all -> 0x0051 }
                j$.util.concurrent.ConcurrentHashMap$TreeNode r3 = r10.right     // Catch:{ all -> 0x0051 }
                if (r1 == 0) goto L_0x0086
                if (r3 == 0) goto L_0x0086
                r4 = r3
            L_0x0038:
                j$.util.concurrent.ConcurrentHashMap$TreeNode r5 = r4.left     // Catch:{ all -> 0x0051 }
                if (r5 == 0) goto L_0x003e
                r4 = r5
                goto L_0x0038
            L_0x003e:
                boolean r5 = r4.red     // Catch:{ all -> 0x0051 }
                boolean r6 = r10.red     // Catch:{ all -> 0x0051 }
                r4.red = r6     // Catch:{ all -> 0x0051 }
                r10.red = r5     // Catch:{ all -> 0x0051 }
                j$.util.concurrent.ConcurrentHashMap$TreeNode r5 = r4.right     // Catch:{ all -> 0x0051 }
                j$.util.concurrent.ConcurrentHashMap$TreeNode r6 = r10.parent     // Catch:{ all -> 0x0051 }
                if (r4 != r3) goto L_0x0054
                r10.parent = r4     // Catch:{ all -> 0x0051 }
                r4.right = r10     // Catch:{ all -> 0x0051 }
                goto L_0x0067
            L_0x0051:
                r10 = move-exception
                goto L_0x00cc
            L_0x0054:
                j$.util.concurrent.ConcurrentHashMap$TreeNode r7 = r4.parent     // Catch:{ all -> 0x0051 }
                r10.parent = r7     // Catch:{ all -> 0x0051 }
                if (r7 == 0) goto L_0x0063
                j$.util.concurrent.ConcurrentHashMap$TreeNode r8 = r7.left     // Catch:{ all -> 0x0051 }
                if (r4 != r8) goto L_0x0061
                r7.left = r10     // Catch:{ all -> 0x0051 }
                goto L_0x0063
            L_0x0061:
                r7.right = r10     // Catch:{ all -> 0x0051 }
            L_0x0063:
                r4.right = r3     // Catch:{ all -> 0x0051 }
                r3.parent = r4     // Catch:{ all -> 0x0051 }
            L_0x0067:
                r10.left = r2     // Catch:{ all -> 0x0051 }
                r10.right = r5     // Catch:{ all -> 0x0051 }
                if (r5 == 0) goto L_0x006f
                r5.parent = r10     // Catch:{ all -> 0x0051 }
            L_0x006f:
                r4.left = r1     // Catch:{ all -> 0x0051 }
                r1.parent = r4     // Catch:{ all -> 0x0051 }
                r4.parent = r6     // Catch:{ all -> 0x0051 }
                if (r6 != 0) goto L_0x0079
                r0 = r4
                goto L_0x0082
            L_0x0079:
                j$.util.concurrent.ConcurrentHashMap$TreeNode r1 = r6.left     // Catch:{ all -> 0x0051 }
                if (r10 != r1) goto L_0x0080
                r6.left = r4     // Catch:{ all -> 0x0051 }
                goto L_0x0082
            L_0x0080:
                r6.right = r4     // Catch:{ all -> 0x0051 }
            L_0x0082:
                if (r5 == 0) goto L_0x008d
                r1 = r5
                goto L_0x008e
            L_0x0086:
                if (r1 == 0) goto L_0x0089
                goto L_0x008e
            L_0x0089:
                if (r3 == 0) goto L_0x008d
                r1 = r3
                goto L_0x008e
            L_0x008d:
                r1 = r10
            L_0x008e:
                if (r1 == r10) goto L_0x00a7
                j$.util.concurrent.ConcurrentHashMap$TreeNode r3 = r10.parent     // Catch:{ all -> 0x0051 }
                r1.parent = r3     // Catch:{ all -> 0x0051 }
                if (r3 != 0) goto L_0x0098
                r0 = r1
                goto L_0x00a1
            L_0x0098:
                j$.util.concurrent.ConcurrentHashMap$TreeNode r4 = r3.left     // Catch:{ all -> 0x0051 }
                if (r10 != r4) goto L_0x009f
                r3.left = r1     // Catch:{ all -> 0x0051 }
                goto L_0x00a1
            L_0x009f:
                r3.right = r1     // Catch:{ all -> 0x0051 }
            L_0x00a1:
                r10.parent = r2     // Catch:{ all -> 0x0051 }
                r10.right = r2     // Catch:{ all -> 0x0051 }
                r10.left = r2     // Catch:{ all -> 0x0051 }
            L_0x00a7:
                boolean r3 = r10.red     // Catch:{ all -> 0x0051 }
                if (r3 == 0) goto L_0x00ac
                goto L_0x00b0
            L_0x00ac:
                j$.util.concurrent.ConcurrentHashMap$TreeNode r0 = balanceDeletion(r0, r1)     // Catch:{ all -> 0x0051 }
            L_0x00b0:
                r9.root = r0     // Catch:{ all -> 0x0051 }
                if (r10 != r1) goto L_0x00c7
                j$.util.concurrent.ConcurrentHashMap$TreeNode r0 = r10.parent     // Catch:{ all -> 0x0051 }
                if (r0 == 0) goto L_0x00c7
                j$.util.concurrent.ConcurrentHashMap$TreeNode r1 = r0.left     // Catch:{ all -> 0x0051 }
                if (r10 != r1) goto L_0x00bf
                r0.left = r2     // Catch:{ all -> 0x0051 }
                goto L_0x00c5
            L_0x00bf:
                j$.util.concurrent.ConcurrentHashMap$TreeNode r1 = r0.right     // Catch:{ all -> 0x0051 }
                if (r10 != r1) goto L_0x00c5
                r0.right = r2     // Catch:{ all -> 0x0051 }
            L_0x00c5:
                r10.parent = r2     // Catch:{ all -> 0x0051 }
            L_0x00c7:
                r9.unlockRoot()
                r10 = 0
                return r10
            L_0x00cc:
                r9.unlockRoot()
                throw r10
            L_0x00d0:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: j$.util.concurrent.ConcurrentHashMap.TreeBin.removeTreeNode(j$.util.concurrent.ConcurrentHashMap$TreeNode):boolean");
        }
    }

    static final class TreeNode extends Node {
        TreeNode left;
        TreeNode parent;
        TreeNode prev;
        boolean red;
        TreeNode right;

        TreeNode(int i, Object obj, Object obj2, Node node, TreeNode treeNode) {
            super(i, obj, obj2, node);
            this.parent = treeNode;
        }

        /* access modifiers changed from: package-private */
        public Node find(int i, Object obj) {
            return findTreeNode(i, obj, (Class) null);
        }

        /* access modifiers changed from: package-private */
        public final TreeNode findTreeNode(int i, Object obj, Class cls) {
            int compareComparables;
            if (obj == null) {
                return null;
            }
            TreeNode treeNode = this;
            do {
                TreeNode treeNode2 = treeNode.left;
                TreeNode treeNode3 = treeNode.right;
                int i2 = treeNode.hash;
                if (i2 <= i) {
                    if (i2 >= i) {
                        Object obj2 = treeNode.key;
                        if (obj2 == obj || (obj2 != null && obj.equals(obj2))) {
                            return treeNode;
                        }
                        if (treeNode2 != null) {
                            if (treeNode3 != null) {
                                if ((cls == null && (cls = ConcurrentHashMap.comparableClassFor(obj)) == null) || (compareComparables = ConcurrentHashMap.compareComparables(cls, obj, obj2)) == 0) {
                                    TreeNode findTreeNode = treeNode3.findTreeNode(i, obj, cls);
                                    if (findTreeNode != null) {
                                        return findTreeNode;
                                    }
                                } else if (compareComparables >= 0) {
                                    treeNode2 = treeNode3;
                                }
                            }
                        }
                    }
                    treeNode = treeNode3;
                    continue;
                }
                treeNode = treeNode2;
                continue;
            } while (treeNode != null);
            return null;
        }
    }

    static final class ValueIterator extends BaseIterator implements Iterator, Enumeration {
        ValueIterator(Node[] nodeArr, int i, int i2, int i3, ConcurrentHashMap concurrentHashMap) {
            super(nodeArr, i, i2, i3, concurrentHashMap);
        }

        public final Object next() {
            Node node = this.next;
            if (node != null) {
                Object obj = node.val;
                this.lastReturned = node;
                advance();
                return obj;
            }
            throw new NoSuchElementException();
        }

        public final Object nextElement() {
            return next();
        }
    }

    static final class ValueSpliterator extends Traverser implements Spliterator {
        long est;

        ValueSpliterator(Node[] nodeArr, int i, int i2, int i3, long j) {
            super(nodeArr, i, i2, i3);
            this.est = j;
        }

        public int characteristics() {
            return 4352;
        }

        public long estimateSize() {
            return this.est;
        }

        public void forEachRemaining(Consumer consumer) {
            consumer.getClass();
            while (true) {
                Node advance = advance();
                if (advance != null) {
                    consumer.accept(advance.val);
                } else {
                    return;
                }
            }
        }

        public /* synthetic */ Comparator getComparator() {
            return Spliterator.CC.$default$getComparator(this);
        }

        public /* synthetic */ long getExactSizeIfKnown() {
            return Spliterator.CC.$default$getExactSizeIfKnown(this);
        }

        public /* synthetic */ boolean hasCharacteristics(int i) {
            return Spliterator.CC.$default$hasCharacteristics(this, i);
        }

        public boolean tryAdvance(Consumer consumer) {
            consumer.getClass();
            Node advance = advance();
            if (advance == null) {
                return false;
            }
            consumer.accept(advance.val);
            return true;
        }

        public ValueSpliterator trySplit() {
            int i = this.baseIndex;
            int i2 = this.baseLimit;
            int i3 = (i + i2) >>> 1;
            if (i3 <= i) {
                return null;
            }
            Node[] nodeArr = this.tab;
            int i4 = this.baseSize;
            this.baseLimit = i3;
            long j = this.est >>> 1;
            this.est = j;
            return new ValueSpliterator(nodeArr, i4, i3, i2, j);
        }
    }

    static final class ValuesView extends CollectionView implements java.util.Collection, Serializable, j$.util.Collection {
        private static final long serialVersionUID = 2249069246763182397L;

        ValuesView(ConcurrentHashMap concurrentHashMap) {
            super(concurrentHashMap);
        }

        public final boolean add(Object obj) {
            throw new UnsupportedOperationException();
        }

        public final boolean addAll(java.util.Collection collection) {
            throw new UnsupportedOperationException();
        }

        public final boolean contains(Object obj) {
            return this.map.containsValue(obj);
        }

        public void forEach(Consumer consumer) {
            consumer.getClass();
            Node[] nodeArr = this.map.table;
            if (nodeArr != null) {
                Traverser traverser = new Traverser(nodeArr, nodeArr.length, 0, nodeArr.length);
                while (true) {
                    Node advance = traverser.advance();
                    if (advance != null) {
                        consumer.accept(advance.val);
                    } else {
                        return;
                    }
                }
            }
        }

        public final Iterator iterator() {
            ConcurrentHashMap concurrentHashMap = this.map;
            Node[] nodeArr = concurrentHashMap.table;
            int length = nodeArr == null ? 0 : nodeArr.length;
            return new ValueIterator(nodeArr, length, 0, length, concurrentHashMap);
        }

        public final boolean remove(Object obj) {
            if (obj == null) {
                return false;
            }
            Iterator it = iterator();
            while (it.hasNext()) {
                if (obj.equals(it.next())) {
                    it.remove();
                    return true;
                }
            }
            return false;
        }

        public boolean removeAll(java.util.Collection collection) {
            collection.getClass();
            Iterator it = iterator();
            boolean z = false;
            while (it.hasNext()) {
                if (collection.contains(it.next())) {
                    it.remove();
                    z = true;
                }
            }
            return z;
        }

        public boolean removeIf(Predicate predicate) {
            return this.map.removeValueIf(predicate);
        }

        public Spliterator spliterator() {
            ConcurrentHashMap concurrentHashMap = this.map;
            long sumCount = concurrentHashMap.sumCount();
            Node[] nodeArr = concurrentHashMap.table;
            int length = nodeArr == null ? 0 : nodeArr.length;
            return new ValueSpliterator(nodeArr, length, 0, length, sumCount < 0 ? 0 : sumCount);
        }

        public /* synthetic */ Object[] toArray(IntFunction intFunction) {
            return Collection.CC.$default$toArray(this, intFunction);
        }
    }

    static {
        ObjectStreamField objectStreamField = new ObjectStreamField("segments", Segment[].class);
        Class cls = Integer.TYPE;
        serialPersistentFields = new ObjectStreamField[]{objectStreamField, new ObjectStreamField("segmentMask", cls), new ObjectStreamField("segmentShift", cls)};
        DesugarUnsafe unsafe = DesugarUnsafe.getUnsafe();
        U = unsafe;
        Class<ConcurrentHashMap> cls2 = ConcurrentHashMap.class;
        SIZECTL = unsafe.objectFieldOffset(cls2, "sizeCtl");
        TRANSFERINDEX = unsafe.objectFieldOffset(cls2, "transferIndex");
        BASECOUNT = unsafe.objectFieldOffset(cls2, "baseCount");
        CELLSBUSY = unsafe.objectFieldOffset(cls2, "cellsBusy");
        CELLVALUE = unsafe.objectFieldOffset(CounterCell.class, "value");
        Class<Node[]> cls3 = Node[].class;
        ABASE = unsafe.arrayBaseOffset(cls3);
        int arrayIndexScale = unsafe.arrayIndexScale(cls3);
        if (((arrayIndexScale - 1) & arrayIndexScale) == 0) {
            ASHIFT = 31 - Integer.numberOfLeadingZeros(arrayIndexScale);
            return;
        }
        throw new ExceptionInInitializerError("array index scale not a power of two");
    }

    public ConcurrentHashMap() {
    }

    public ConcurrentHashMap(int i) {
        this(i, 0.75f, 1);
    }

    public ConcurrentHashMap(int i, float f) {
        this(i, f, 1);
    }

    public ConcurrentHashMap(int i, float f, int i2) {
        if (f <= 0.0f || i < 0 || i2 <= 0) {
            throw new IllegalArgumentException();
        }
        double d = (double) (((float) ((long) (i < i2 ? i2 : i))) / f);
        Double.isNaN(d);
        long j = (long) (d + 1.0d);
        this.sizeCtl = j >= 1073741824 ? 1073741824 : tableSizeFor((int) j);
    }

    public ConcurrentHashMap(Map<? extends K, ? extends V> map) {
        this.sizeCtl = 16;
        putAll(map);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0012, code lost:
        if (r1.compareAndSetLong(r11, r3, r5, r9) == false) goto L_0x0014;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void addCount(long r12, int r14) {
        /*
            r11 = this;
            j$.util.concurrent.ConcurrentHashMap$CounterCell[] r0 = r11.counterCells
            if (r0 != 0) goto L_0x0014
            j$.sun.misc.DesugarUnsafe r1 = U
            long r3 = BASECOUNT
            long r5 = r11.baseCount
            long r9 = r5 + r12
            r2 = r11
            r7 = r9
            boolean r1 = r1.compareAndSetLong(r2, r3, r5, r7)
            if (r1 != 0) goto L_0x003b
        L_0x0014:
            r1 = 1
            if (r0 == 0) goto L_0x0094
            int r2 = r0.length
            int r2 = r2 - r1
            if (r2 < 0) goto L_0x0094
            int r3 = j$.util.concurrent.ThreadLocalRandom.getProbe()
            r2 = r2 & r3
            r4 = r0[r2]
            if (r4 == 0) goto L_0x0094
            j$.sun.misc.DesugarUnsafe r3 = U
            long r5 = CELLVALUE
            long r7 = r4.value
            long r9 = r7 + r12
            boolean r0 = r3.compareAndSetLong(r4, r5, r7, r9)
            if (r0 != 0) goto L_0x0034
            r1 = r0
            goto L_0x0094
        L_0x0034:
            if (r14 > r1) goto L_0x0037
            return
        L_0x0037:
            long r9 = r11.sumCount()
        L_0x003b:
            if (r14 < 0) goto L_0x0093
        L_0x003d:
            int r4 = r11.sizeCtl
            long r12 = (long) r4
            int r14 = (r9 > r12 ? 1 : (r9 == r12 ? 0 : -1))
            if (r14 < 0) goto L_0x0093
            j$.util.concurrent.ConcurrentHashMap$Node[] r12 = r11.table
            if (r12 == 0) goto L_0x0093
            int r13 = r12.length
            r14 = 1073741824(0x40000000, float:2.0)
            if (r13 >= r14) goto L_0x0093
            int r13 = resizeStamp(r13)
            if (r4 >= 0) goto L_0x007b
            int r14 = r4 >>> 16
            if (r14 != r13) goto L_0x0093
            int r14 = r13 + 1
            if (r4 == r14) goto L_0x0093
            r14 = 65535(0xffff, float:9.1834E-41)
            int r13 = r13 + r14
            if (r4 == r13) goto L_0x0093
            j$.util.concurrent.ConcurrentHashMap$Node[] r13 = r11.nextTable
            if (r13 == 0) goto L_0x0093
            int r14 = r11.transferIndex
            if (r14 > 0) goto L_0x006a
            goto L_0x0093
        L_0x006a:
            j$.sun.misc.DesugarUnsafe r0 = U
            long r2 = SIZECTL
            int r5 = r4 + 1
            r1 = r11
            boolean r14 = r0.compareAndSetInt(r1, r2, r4, r5)
            if (r14 == 0) goto L_0x008e
            r11.transfer(r12, r13)
            goto L_0x008e
        L_0x007b:
            j$.sun.misc.DesugarUnsafe r0 = U
            long r2 = SIZECTL
            int r13 = r13 << 16
            int r5 = r13 + 2
            r1 = r11
            boolean r13 = r0.compareAndSetInt(r1, r2, r4, r5)
            if (r13 == 0) goto L_0x008e
            r13 = 0
            r11.transfer(r12, r13)
        L_0x008e:
            long r9 = r11.sumCount()
            goto L_0x003d
        L_0x0093:
            return
        L_0x0094:
            r11.fullAddCount(r12, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: j$.util.concurrent.ConcurrentHashMap.addCount(long, int):void");
    }

    static final boolean casTabAt(Node[] nodeArr, int i, Node node, Node node2) {
        return U.compareAndSetObject(nodeArr, ((long) ABASE) + (((long) i) << ASHIFT), node, node2);
    }

    static Class comparableClassFor(Object obj) {
        Type[] actualTypeArguments;
        if (!(obj instanceof Comparable)) {
            return null;
        }
        Class<?> cls = obj.getClass();
        if (cls == String.class) {
            return cls;
        }
        Type[] genericInterfaces = cls.getGenericInterfaces();
        if (genericInterfaces == null) {
            return null;
        }
        for (Type type : genericInterfaces) {
            if (type instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) type;
                if (parameterizedType.getRawType() == Comparable.class && (actualTypeArguments = parameterizedType.getActualTypeArguments()) != null && actualTypeArguments.length == 1 && actualTypeArguments[0] == cls) {
                    return cls;
                }
            }
        }
        return null;
    }

    static int compareComparables(Class cls, Object obj, Object obj2) {
        if (obj2 == null || obj2.getClass() != cls) {
            return 0;
        }
        return ((Comparable) obj).compareTo(obj2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:50:0x009e, code lost:
        if (r9.counterCells != r7) goto L_0x00ad;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00a0, code lost:
        r9.counterCells = (j$.util.concurrent.ConcurrentHashMap.CounterCell[]) java.util.Arrays.copyOf(r7, r8 << 1);
     */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x0100 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x001b A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void fullAddCount(long r25, boolean r27) {
        /*
            r24 = this;
            r9 = r24
            r10 = r25
            int r0 = j$.util.concurrent.ThreadLocalRandom.getProbe()
            r12 = 1
            if (r0 != 0) goto L_0x0015
            j$.util.concurrent.ThreadLocalRandom.localInit()
            int r0 = j$.util.concurrent.ThreadLocalRandom.getProbe()
            r1 = r0
            r0 = 1
            goto L_0x0018
        L_0x0015:
            r1 = r0
            r0 = r27
        L_0x0018:
            r13 = 0
            r14 = r1
        L_0x001a:
            r15 = 0
        L_0x001b:
            j$.util.concurrent.ConcurrentHashMap$CounterCell[] r7 = r9.counterCells
            if (r7 == 0) goto L_0x00bb
            int r8 = r7.length
            if (r8 <= 0) goto L_0x00bb
            int r1 = r8 + -1
            r1 = r1 & r14
            r1 = r7[r1]
            if (r1 != 0) goto L_0x0064
            int r1 = r9.cellsBusy
            if (r1 != 0) goto L_0x0062
            j$.util.concurrent.ConcurrentHashMap$CounterCell r7 = new j$.util.concurrent.ConcurrentHashMap$CounterCell
            r7.<init>(r10)
            int r1 = r9.cellsBusy
            if (r1 != 0) goto L_0x0062
            j$.sun.misc.DesugarUnsafe r1 = U
            long r3 = CELLSBUSY
            r5 = 0
            r6 = 1
            r2 = r24
            boolean r1 = r1.compareAndSetInt(r2, r3, r5, r6)
            if (r1 == 0) goto L_0x0062
            j$.util.concurrent.ConcurrentHashMap$CounterCell[] r1 = r9.counterCells     // Catch:{ all -> 0x0056 }
            if (r1 == 0) goto L_0x0058
            int r2 = r1.length     // Catch:{ all -> 0x0056 }
            if (r2 <= 0) goto L_0x0058
            int r2 = r2 + -1
            r2 = r2 & r14
            r3 = r1[r2]     // Catch:{ all -> 0x0056 }
            if (r3 != 0) goto L_0x0058
            r1[r2] = r7     // Catch:{ all -> 0x0056 }
            r1 = 1
            goto L_0x0059
        L_0x0056:
            r0 = move-exception
            goto L_0x005f
        L_0x0058:
            r1 = 0
        L_0x0059:
            r9.cellsBusy = r13
            if (r1 == 0) goto L_0x001b
            goto L_0x0100
        L_0x005f:
            r9.cellsBusy = r13
            throw r0
        L_0x0062:
            r15 = 0
            goto L_0x00b4
        L_0x0064:
            if (r0 != 0) goto L_0x0068
            r0 = 1
            goto L_0x00b4
        L_0x0068:
            j$.sun.misc.DesugarUnsafe r2 = U
            long r18 = CELLVALUE
            long r3 = r1.value
            long r22 = r3 + r10
            r16 = r2
            r17 = r1
            r20 = r3
            boolean r1 = r16.compareAndSetLong(r17, r18, r20, r22)
            if (r1 == 0) goto L_0x007e
            goto L_0x0100
        L_0x007e:
            j$.util.concurrent.ConcurrentHashMap$CounterCell[] r1 = r9.counterCells
            if (r1 != r7) goto L_0x0062
            int r1 = NCPU
            if (r8 < r1) goto L_0x0087
            goto L_0x0062
        L_0x0087:
            if (r15 != 0) goto L_0x008b
            r15 = 1
            goto L_0x00b4
        L_0x008b:
            int r1 = r9.cellsBusy
            if (r1 != 0) goto L_0x00b4
            long r3 = CELLSBUSY
            r5 = 0
            r6 = 1
            r1 = r2
            r2 = r24
            boolean r1 = r1.compareAndSetInt(r2, r3, r5, r6)
            if (r1 == 0) goto L_0x00b4
            j$.util.concurrent.ConcurrentHashMap$CounterCell[] r1 = r9.counterCells     // Catch:{ all -> 0x00ab }
            if (r1 != r7) goto L_0x00ad
            int r1 = r8 << 1
            java.lang.Object[] r1 = java.util.Arrays.copyOf(r7, r1)     // Catch:{ all -> 0x00ab }
            j$.util.concurrent.ConcurrentHashMap$CounterCell[] r1 = (j$.util.concurrent.ConcurrentHashMap.CounterCell[]) r1     // Catch:{ all -> 0x00ab }
            r9.counterCells = r1     // Catch:{ all -> 0x00ab }
            goto L_0x00ad
        L_0x00ab:
            r0 = move-exception
            goto L_0x00b1
        L_0x00ad:
            r9.cellsBusy = r13
            goto L_0x001a
        L_0x00b1:
            r9.cellsBusy = r13
            throw r0
        L_0x00b4:
            int r1 = j$.util.concurrent.ThreadLocalRandom.advanceProbe(r14)
            r14 = r1
            goto L_0x001b
        L_0x00bb:
            int r1 = r9.cellsBusy
            if (r1 != 0) goto L_0x00f0
            j$.util.concurrent.ConcurrentHashMap$CounterCell[] r1 = r9.counterCells
            if (r1 != r7) goto L_0x00f0
            j$.sun.misc.DesugarUnsafe r1 = U
            long r3 = CELLSBUSY
            r5 = 0
            r6 = 1
            r2 = r24
            boolean r1 = r1.compareAndSetInt(r2, r3, r5, r6)
            if (r1 == 0) goto L_0x00f0
            j$.util.concurrent.ConcurrentHashMap$CounterCell[] r1 = r9.counterCells     // Catch:{ all -> 0x00e5 }
            if (r1 != r7) goto L_0x00e7
            r1 = 2
            j$.util.concurrent.ConcurrentHashMap$CounterCell[] r1 = new j$.util.concurrent.ConcurrentHashMap.CounterCell[r1]     // Catch:{ all -> 0x00e5 }
            r2 = r14 & 1
            j$.util.concurrent.ConcurrentHashMap$CounterCell r3 = new j$.util.concurrent.ConcurrentHashMap$CounterCell     // Catch:{ all -> 0x00e5 }
            r3.<init>(r10)     // Catch:{ all -> 0x00e5 }
            r1[r2] = r3     // Catch:{ all -> 0x00e5 }
            r9.counterCells = r1     // Catch:{ all -> 0x00e5 }
            r1 = 1
            goto L_0x00e8
        L_0x00e5:
            r0 = move-exception
            goto L_0x00ed
        L_0x00e7:
            r1 = 0
        L_0x00e8:
            r9.cellsBusy = r13
            if (r1 == 0) goto L_0x001b
            goto L_0x0100
        L_0x00ed:
            r9.cellsBusy = r13
            throw r0
        L_0x00f0:
            j$.sun.misc.DesugarUnsafe r1 = U
            long r3 = BASECOUNT
            long r5 = r9.baseCount
            long r7 = r5 + r10
            r2 = r24
            boolean r1 = r1.compareAndSetLong(r2, r3, r5, r7)
            if (r1 == 0) goto L_0x001b
        L_0x0100:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: j$.util.concurrent.ConcurrentHashMap.fullAddCount(long, boolean):void");
    }

    private final Node[] initTable() {
        while (true) {
            Node[] nodeArr = this.table;
            if (nodeArr != null && nodeArr.length != 0) {
                return nodeArr;
            }
            int i = this.sizeCtl;
            if (i < 0) {
                Thread.yield();
            } else {
                if (U.compareAndSetInt(this, SIZECTL, i, -1)) {
                    try {
                        Node[] nodeArr2 = this.table;
                        if (nodeArr2 != null) {
                            if (nodeArr2.length == 0) {
                            }
                            this.sizeCtl = i;
                            return nodeArr2;
                        }
                        int i2 = i > 0 ? i : 16;
                        Node[] nodeArr3 = new Node[i2];
                        this.table = nodeArr3;
                        i = i2 - (i2 >>> 2);
                        nodeArr2 = nodeArr3;
                        this.sizeCtl = i;
                        return nodeArr2;
                    } catch (Throwable th) {
                        this.sizeCtl = i;
                        throw th;
                    }
                }
            }
        }
    }

    private void readObject(ObjectInputStream objectInputStream) {
        long j;
        boolean z;
        boolean z2;
        Object obj;
        this.sizeCtl = -1;
        objectInputStream.defaultReadObject();
        long j2 = 0;
        long j3 = 0;
        Node node = null;
        while (true) {
            Object readObject = objectInputStream.readObject();
            Object readObject2 = objectInputStream.readObject();
            j = 1;
            if (readObject != null && readObject2 != null) {
                j3++;
                node = new Node(spread(readObject.hashCode()), readObject, readObject2, node);
            }
        }
        if (j3 == 0) {
            this.sizeCtl = 0;
            return;
        }
        double d = (double) (((float) j3) / 0.75f);
        Double.isNaN(d);
        long j4 = (long) (d + 1.0d);
        int tableSizeFor = j4 >= 1073741824 ? 1073741824 : tableSizeFor((int) j4);
        Node[] nodeArr = new Node[tableSizeFor];
        int i = tableSizeFor - 1;
        while (node != null) {
            Node node2 = node.next;
            int i2 = node.hash;
            int i3 = i2 & i;
            Node tabAt = tabAt(nodeArr, i3);
            if (tabAt == null) {
                z = true;
            } else {
                Object obj2 = node.key;
                if (tabAt.hash >= 0) {
                    Node node3 = tabAt;
                    int i4 = 0;
                    while (true) {
                        if (node3 == null) {
                            z2 = true;
                            break;
                        } else if (node3.hash != i2 || ((obj = node3.key) != obj2 && (obj == null || !obj2.equals(obj)))) {
                            i4++;
                            node3 = node3.next;
                        }
                    }
                    z2 = false;
                    if (!z2 || i4 < 8) {
                        z = z2;
                    } else {
                        long j5 = j2 + 1;
                        node.next = tabAt;
                        Node node4 = node;
                        TreeNode treeNode = null;
                        TreeNode treeNode2 = null;
                        while (node4 != null) {
                            long j6 = j5;
                            TreeNode treeNode3 = new TreeNode(node4.hash, node4.key, node4.val, (Node) null, (TreeNode) null);
                            treeNode3.prev = treeNode2;
                            if (treeNode2 == null) {
                                treeNode = treeNode3;
                            } else {
                                treeNode2.next = treeNode3;
                            }
                            node4 = node4.next;
                            treeNode2 = treeNode3;
                            j5 = j6;
                        }
                        setTabAt(nodeArr, i3, new TreeBin(treeNode));
                        j2 = j5;
                    }
                } else if (((TreeBin) tabAt).putTreeVal(i2, obj2, node.val) == null) {
                    j2 += j;
                }
                z = false;
            }
            j = 1;
            if (z) {
                j2++;
                node.next = tabAt;
                setTabAt(nodeArr, i3, node);
            }
            node = node2;
        }
        this.table = nodeArr;
        this.sizeCtl = tableSizeFor - (tableSizeFor >>> 2);
        this.baseCount = j2;
    }

    static final int resizeStamp(int i) {
        return Integer.numberOfLeadingZeros(i) | 32768;
    }

    static final void setTabAt(Node[] nodeArr, int i, Node node) {
        U.putObjectRelease(nodeArr, (((long) i) << ASHIFT) + ((long) ABASE), node);
    }

    static final int spread(int i) {
        return (i ^ (i >>> 16)) & Integer.MAX_VALUE;
    }

    static final Node tabAt(Node[] nodeArr, int i) {
        return (Node) U.getObjectAcquire(nodeArr, (((long) i) << ASHIFT) + ((long) ABASE));
    }

    private static final int tableSizeFor(int i) {
        int numberOfLeadingZeros = -1 >>> Integer.numberOfLeadingZeros(i - 1);
        if (numberOfLeadingZeros < 0) {
            return 1;
        }
        if (numberOfLeadingZeros >= 1073741824) {
            return 1073741824;
        }
        return 1 + numberOfLeadingZeros;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: j$.util.concurrent.ConcurrentHashMap$TreeNode} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v5, resolved type: j$.util.concurrent.ConcurrentHashMap$TreeNode} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v6, resolved type: j$.util.concurrent.ConcurrentHashMap$TreeNode} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v7, resolved type: j$.util.concurrent.ConcurrentHashMap$TreeNode} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v8, resolved type: j$.util.concurrent.ConcurrentHashMap$TreeNode} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v5, resolved type: j$.util.concurrent.ConcurrentHashMap$Node} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v6, resolved type: j$.util.concurrent.ConcurrentHashMap$Node} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v9, resolved type: j$.util.concurrent.ConcurrentHashMap$TreeNode} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v10, resolved type: j$.util.concurrent.ConcurrentHashMap$TreeNode} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v11, resolved type: j$.util.concurrent.ConcurrentHashMap$TreeNode} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v12, resolved type: j$.util.concurrent.ConcurrentHashMap$TreeNode} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v13, resolved type: j$.util.concurrent.ConcurrentHashMap$TreeNode} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v7, resolved type: j$.util.concurrent.ConcurrentHashMap$Node} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v8, resolved type: j$.util.concurrent.ConcurrentHashMap$Node} */
    /* JADX WARNING: type inference failed for: r13v12, types: [j$.util.concurrent.ConcurrentHashMap$Node] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void transfer(j$.util.concurrent.ConcurrentHashMap.Node[] r31, j$.util.concurrent.ConcurrentHashMap.Node[] r32) {
        /*
            r30 = this;
            r7 = r30
            r0 = r31
            int r8 = r0.length
            int r1 = NCPU
            r9 = 1
            if (r1 <= r9) goto L_0x000e
            int r2 = r8 >>> 3
            int r2 = r2 / r1
            goto L_0x000f
        L_0x000e:
            r2 = r8
        L_0x000f:
            r10 = 16
            if (r2 >= r10) goto L_0x0016
            r11 = 16
            goto L_0x0017
        L_0x0016:
            r11 = r2
        L_0x0017:
            if (r32 != 0) goto L_0x0029
            int r1 = r8 << 1
            j$.util.concurrent.ConcurrentHashMap$Node[] r1 = new j$.util.concurrent.ConcurrentHashMap.Node[r1]     // Catch:{ all -> 0x0023 }
            r7.nextTable = r1
            r7.transferIndex = r8
            r12 = r1
            goto L_0x002b
        L_0x0023:
            r0 = 2147483647(0x7fffffff, float:NaN)
            r7.sizeCtl = r0
            return
        L_0x0029:
            r12 = r32
        L_0x002b:
            int r13 = r12.length
            j$.util.concurrent.ConcurrentHashMap$ForwardingNode r14 = new j$.util.concurrent.ConcurrentHashMap$ForwardingNode
            r14.<init>(r12)
            r5 = 0
            r6 = 0
            r16 = 1
            r17 = 0
        L_0x0037:
            r1 = -1
            if (r16 == 0) goto L_0x007e
            int r6 = r6 + -1
            if (r6 >= r5) goto L_0x0040
            if (r17 == 0) goto L_0x0045
        L_0x0040:
            r18 = r5
            r19 = r6
            goto L_0x0079
        L_0x0045:
            int r3 = r7.transferIndex
            if (r3 > 0) goto L_0x004d
            r6 = -1
        L_0x004a:
            r16 = 0
            goto L_0x0037
        L_0x004d:
            j$.sun.misc.DesugarUnsafe r1 = U
            long r18 = TRANSFERINDEX
            if (r3 <= r11) goto L_0x0058
            int r2 = r3 - r11
            r20 = r2
            goto L_0x005a
        L_0x0058:
            r20 = 0
        L_0x005a:
            r2 = r30
            r21 = r3
            r3 = r18
            r18 = r5
            r5 = r21
            r19 = r6
            r6 = r20
            boolean r1 = r1.compareAndSetInt(r2, r3, r5, r6)
            if (r1 == 0) goto L_0x0074
            int r3 = r21 + -1
            r6 = r3
            r5 = r20
            goto L_0x004a
        L_0x0074:
            r5 = r18
            r6 = r19
            goto L_0x0037
        L_0x0079:
            r5 = r18
            r6 = r19
            goto L_0x004a
        L_0x007e:
            r18 = r5
            r2 = 0
            if (r6 < 0) goto L_0x0089
            if (r6 >= r8) goto L_0x0089
            int r3 = r6 + r8
            if (r3 < r13) goto L_0x0090
        L_0x0089:
            r21 = r11
            r22 = r13
            r7 = r14
            goto L_0x01af
        L_0x0090:
            j$.util.concurrent.ConcurrentHashMap$Node r4 = tabAt(r0, r6)
            if (r4 != 0) goto L_0x00a7
            boolean r1 = casTabAt(r0, r6, r2, r14)
            r16 = r1
            r9 = r7
            r21 = r11
            r22 = r13
            r7 = r14
        L_0x00a2:
            r2 = 16
            r10 = 1
            goto L_0x01e9
        L_0x00a7:
            int r5 = r4.hash
            if (r5 != r1) goto L_0x00b8
            r9 = r7
            r21 = r11
            r22 = r13
            r7 = r14
            r2 = 16
            r10 = 1
            r16 = 1
            goto L_0x01e9
        L_0x00b8:
            monitor-enter(r4)
            j$.util.concurrent.ConcurrentHashMap$Node r1 = tabAt(r0, r6)     // Catch:{ all -> 0x00d4 }
            if (r1 != r4) goto L_0x01a3
            if (r5 < 0) goto L_0x0115
            r1 = r5 & r8
            j$.util.concurrent.ConcurrentHashMap$Node r5 = r4.next     // Catch:{ all -> 0x00d4 }
            r15 = r4
        L_0x00c6:
            if (r5 == 0) goto L_0x00d7
            int r10 = r5.hash     // Catch:{ all -> 0x00d4 }
            r10 = r10 & r8
            if (r10 == r1) goto L_0x00cf
            r15 = r5
            r1 = r10
        L_0x00cf:
            j$.util.concurrent.ConcurrentHashMap$Node r5 = r5.next     // Catch:{ all -> 0x00d4 }
            r10 = 16
            goto L_0x00c6
        L_0x00d4:
            r0 = move-exception
            goto L_0x01ad
        L_0x00d7:
            if (r1 != 0) goto L_0x00dc
            r1 = r2
            r2 = r15
            goto L_0x00dd
        L_0x00dc:
            r1 = r15
        L_0x00dd:
            r5 = r4
        L_0x00de:
            if (r5 == r15) goto L_0x0103
            int r10 = r5.hash     // Catch:{ all -> 0x00d4 }
            java.lang.Object r9 = r5.key     // Catch:{ all -> 0x00d4 }
            r21 = r11
            java.lang.Object r11 = r5.val     // Catch:{ all -> 0x00d4 }
            r16 = r10 & r8
            r22 = r13
            if (r16 != 0) goto L_0x00f5
            j$.util.concurrent.ConcurrentHashMap$Node r13 = new j$.util.concurrent.ConcurrentHashMap$Node     // Catch:{ all -> 0x00d4 }
            r13.<init>(r10, r9, r11, r2)     // Catch:{ all -> 0x00d4 }
            r2 = r13
            goto L_0x00fb
        L_0x00f5:
            j$.util.concurrent.ConcurrentHashMap$Node r13 = new j$.util.concurrent.ConcurrentHashMap$Node     // Catch:{ all -> 0x00d4 }
            r13.<init>(r10, r9, r11, r1)     // Catch:{ all -> 0x00d4 }
            r1 = r13
        L_0x00fb:
            j$.util.concurrent.ConcurrentHashMap$Node r5 = r5.next     // Catch:{ all -> 0x00d4 }
            r11 = r21
            r13 = r22
            r9 = 1
            goto L_0x00de
        L_0x0103:
            r21 = r11
            r22 = r13
            setTabAt(r12, r6, r2)     // Catch:{ all -> 0x00d4 }
            setTabAt(r12, r3, r1)     // Catch:{ all -> 0x00d4 }
            setTabAt(r0, r6, r14)     // Catch:{ all -> 0x00d4 }
            r7 = r14
        L_0x0111:
            r16 = 1
            goto L_0x01a8
        L_0x0115:
            r21 = r11
            r22 = r13
            boolean r1 = r4 instanceof j$.util.concurrent.ConcurrentHashMap.TreeBin     // Catch:{ all -> 0x00d4 }
            if (r1 == 0) goto L_0x01a1
            r1 = r4
            j$.util.concurrent.ConcurrentHashMap$TreeBin r1 = (j$.util.concurrent.ConcurrentHashMap.TreeBin) r1     // Catch:{ all -> 0x00d4 }
            j$.util.concurrent.ConcurrentHashMap$TreeNode r5 = r1.first     // Catch:{ all -> 0x00d4 }
            r9 = r2
            r10 = r9
            r11 = r5
            r13 = 0
            r15 = 0
            r5 = r10
        L_0x0128:
            if (r11 == 0) goto L_0x016b
            r16 = r1
            int r1 = r11.hash     // Catch:{ all -> 0x00d4 }
            j$.util.concurrent.ConcurrentHashMap$TreeNode r7 = new j$.util.concurrent.ConcurrentHashMap$TreeNode     // Catch:{ all -> 0x00d4 }
            java.lang.Object r0 = r11.key     // Catch:{ all -> 0x00d4 }
            r29 = r14
            java.lang.Object r14 = r11.val     // Catch:{ all -> 0x00d4 }
            r27 = 0
            r28 = 0
            r23 = r7
            r24 = r1
            r25 = r0
            r26 = r14
            r23.<init>(r24, r25, r26, r27, r28)     // Catch:{ all -> 0x00d4 }
            r0 = r1 & r8
            if (r0 != 0) goto L_0x0155
            r7.prev = r10     // Catch:{ all -> 0x00d4 }
            if (r10 != 0) goto L_0x014f
            r2 = r7
            goto L_0x0151
        L_0x014f:
            r10.next = r7     // Catch:{ all -> 0x00d4 }
        L_0x0151:
            int r13 = r13 + 1
            r10 = r7
            goto L_0x0160
        L_0x0155:
            r7.prev = r9     // Catch:{ all -> 0x00d4 }
            if (r9 != 0) goto L_0x015b
            r5 = r7
            goto L_0x015d
        L_0x015b:
            r9.next = r7     // Catch:{ all -> 0x00d4 }
        L_0x015d:
            int r15 = r15 + 1
            r9 = r7
        L_0x0160:
            j$.util.concurrent.ConcurrentHashMap$Node r11 = r11.next     // Catch:{ all -> 0x00d4 }
            r7 = r30
            r0 = r31
            r1 = r16
            r14 = r29
            goto L_0x0128
        L_0x016b:
            r16 = r1
            r29 = r14
            r0 = 6
            if (r13 > r0) goto L_0x0177
            j$.util.concurrent.ConcurrentHashMap$Node r1 = untreeify(r2)     // Catch:{ all -> 0x00d4 }
            goto L_0x0181
        L_0x0177:
            if (r15 == 0) goto L_0x017f
            j$.util.concurrent.ConcurrentHashMap$TreeBin r1 = new j$.util.concurrent.ConcurrentHashMap$TreeBin     // Catch:{ all -> 0x00d4 }
            r1.<init>(r2)     // Catch:{ all -> 0x00d4 }
            goto L_0x0181
        L_0x017f:
            r1 = r16
        L_0x0181:
            if (r15 > r0) goto L_0x0188
            j$.util.concurrent.ConcurrentHashMap$Node r0 = untreeify(r5)     // Catch:{ all -> 0x00d4 }
            goto L_0x0192
        L_0x0188:
            if (r13 == 0) goto L_0x0190
            j$.util.concurrent.ConcurrentHashMap$TreeBin r0 = new j$.util.concurrent.ConcurrentHashMap$TreeBin     // Catch:{ all -> 0x00d4 }
            r0.<init>(r5)     // Catch:{ all -> 0x00d4 }
            goto L_0x0192
        L_0x0190:
            r0 = r16
        L_0x0192:
            setTabAt(r12, r6, r1)     // Catch:{ all -> 0x00d4 }
            setTabAt(r12, r3, r0)     // Catch:{ all -> 0x00d4 }
            r0 = r31
            r7 = r29
            setTabAt(r0, r6, r7)     // Catch:{ all -> 0x00d4 }
            goto L_0x0111
        L_0x01a1:
            r7 = r14
            goto L_0x01a8
        L_0x01a3:
            r21 = r11
            r22 = r13
            goto L_0x01a1
        L_0x01a8:
            monitor-exit(r4)     // Catch:{ all -> 0x00d4 }
            r9 = r30
            goto L_0x00a2
        L_0x01ad:
            monitor-exit(r4)     // Catch:{ all -> 0x00d4 }
            throw r0
        L_0x01af:
            r9 = r30
            if (r17 == 0) goto L_0x01c0
            r9.nextTable = r2
            r9.table = r12
            int r0 = r8 << 1
            r10 = 1
            int r1 = r8 >>> 1
            int r0 = r0 - r1
            r9.sizeCtl = r0
            return
        L_0x01c0:
            r10 = 1
            j$.sun.misc.DesugarUnsafe r1 = U
            long r3 = SIZECTL
            int r11 = r9.sizeCtl
            int r13 = r11 + -1
            r2 = r30
            r5 = r11
            r15 = r6
            r6 = r13
            boolean r1 = r1.compareAndSetInt(r2, r3, r5, r6)
            if (r1 == 0) goto L_0x01e6
            int r11 = r11 + -2
            int r1 = resizeStamp(r8)
            r2 = 16
            int r1 = r1 << r2
            if (r11 == r1) goto L_0x01e0
            return
        L_0x01e0:
            r6 = r8
            r16 = 1
            r17 = 1
            goto L_0x01e9
        L_0x01e6:
            r2 = 16
            r6 = r15
        L_0x01e9:
            r14 = r7
            r7 = r9
            r5 = r18
            r11 = r21
            r13 = r22
            r9 = 1
            r10 = 16
            goto L_0x0037
        */
        throw new UnsupportedOperationException("Method not decompiled: j$.util.concurrent.ConcurrentHashMap.transfer(j$.util.concurrent.ConcurrentHashMap$Node[], j$.util.concurrent.ConcurrentHashMap$Node[]):void");
    }

    private final void treeifyBin(Node[] nodeArr, int i) {
        if (nodeArr != null) {
            int length = nodeArr.length;
            if (length < 64) {
                tryPresize(length << 1);
                return;
            }
            Node tabAt = tabAt(nodeArr, i);
            if (tabAt != null && tabAt.hash >= 0) {
                synchronized (tabAt) {
                    try {
                        if (tabAt(nodeArr, i) == tabAt) {
                            TreeNode treeNode = null;
                            Node node = tabAt;
                            TreeNode treeNode2 = null;
                            while (node != null) {
                                TreeNode treeNode3 = new TreeNode(node.hash, node.key, node.val, (Node) null, (TreeNode) null);
                                treeNode3.prev = treeNode2;
                                if (treeNode2 == null) {
                                    treeNode = treeNode3;
                                } else {
                                    treeNode2.next = treeNode3;
                                }
                                node = node.next;
                                treeNode2 = treeNode3;
                            }
                            setTabAt(nodeArr, i, new TreeBin(treeNode));
                        }
                    } finally {
                    }
                }
            }
        }
    }

    private final void tryPresize(int i) {
        int length;
        int tableSizeFor = i >= 536870912 ? 1073741824 : tableSizeFor(i + (i >>> 1) + 1);
        while (true) {
            int i2 = this.sizeCtl;
            if (i2 >= 0) {
                Node[] nodeArr = this.table;
                if (nodeArr == null || (length = nodeArr.length) == 0) {
                    int i3 = i2 > tableSizeFor ? i2 : tableSizeFor;
                    if (U.compareAndSetInt(this, SIZECTL, i2, -1)) {
                        try {
                            if (this.table == nodeArr) {
                                this.table = new Node[i3];
                                i2 = i3 - (i3 >>> 2);
                            }
                        } finally {
                            this.sizeCtl = i2;
                        }
                    }
                } else if (tableSizeFor > i2 && length < 1073741824) {
                    if (nodeArr == this.table) {
                        if (U.compareAndSetInt(this, SIZECTL, i2, (resizeStamp(length) << 16) + 2)) {
                            transfer(nodeArr, (Node[]) null);
                        }
                    }
                } else {
                    return;
                }
            } else {
                return;
            }
        }
    }

    static Node untreeify(Node node) {
        Node node2 = null;
        Node node3 = null;
        while (node != null) {
            Node node4 = new Node(node.hash, node.key, node.val);
            if (node3 == null) {
                node2 = node4;
            } else {
                node3.next = node4;
            }
            node = node.next;
            node3 = node4;
        }
        return node2;
    }

    private void writeObject(ObjectOutputStream objectOutputStream) {
        int i = 1;
        int i2 = 0;
        while (i < 16) {
            i2++;
            i <<= 1;
        }
        int i3 = 32 - i2;
        int i4 = i - 1;
        Segment[] segmentArr = new Segment[16];
        for (int i5 = 0; i5 < 16; i5++) {
            segmentArr[i5] = new Segment(0.75f);
        }
        ObjectOutputStream.PutField putFields = objectOutputStream.putFields();
        putFields.put("segments", segmentArr);
        putFields.put("segmentShift", i3);
        putFields.put("segmentMask", i4);
        objectOutputStream.writeFields();
        Node[] nodeArr = this.table;
        if (nodeArr != null) {
            Traverser traverser = new Traverser(nodeArr, nodeArr.length, 0, nodeArr.length);
            while (true) {
                Node advance = traverser.advance();
                if (advance == null) {
                    break;
                }
                objectOutputStream.writeObject(advance.key);
                objectOutputStream.writeObject(advance.val);
            }
        }
        objectOutputStream.writeObject((Object) null);
        objectOutputStream.writeObject((Object) null);
    }

    public void clear() {
        Node[] nodeArr = this.table;
        long j = 0;
        loop0:
        while (true) {
            int i = 0;
            while (nodeArr != null && i < nodeArr.length) {
                Node tabAt = tabAt(nodeArr, i);
                if (tabAt == null) {
                    i++;
                } else {
                    int i2 = tabAt.hash;
                    if (i2 == -1) {
                        nodeArr = helpTransfer(nodeArr, tabAt);
                    } else {
                        synchronized (tabAt) {
                            try {
                                if (tabAt(nodeArr, i) == tabAt) {
                                    for (Node node = i2 >= 0 ? tabAt : tabAt instanceof TreeBin ? ((TreeBin) tabAt).first : null; node != null; node = node.next) {
                                        j--;
                                    }
                                    setTabAt(nodeArr, i, (Node) null);
                                    i++;
                                }
                            } finally {
                            }
                        }
                    }
                }
            }
        }
        if (j != 0) {
            addCount(j, -1);
        }
    }

    public Object compute(Object obj, BiFunction biFunction) {
        Node node;
        if (obj == null || biFunction == null) {
            throw null;
        }
        int spread = spread(obj.hashCode());
        Node[] nodeArr = this.table;
        int i = 0;
        Object obj2 = null;
        int i2 = 0;
        while (true) {
            if (nodeArr != null) {
                int length = nodeArr.length;
                if (length != 0) {
                    int i3 = (length - 1) & spread;
                    Node tabAt = tabAt(nodeArr, i3);
                    if (tabAt == null) {
                        ReservationNode reservationNode = new ReservationNode();
                        synchronized (reservationNode) {
                            try {
                                if (casTabAt(nodeArr, i3, (Node) null, reservationNode)) {
                                    obj2 = biFunction.apply(obj, null);
                                    if (obj2 != null) {
                                        node = new Node(spread, obj, obj2);
                                        i2 = 1;
                                    } else {
                                        node = null;
                                    }
                                    setTabAt(nodeArr, i3, node);
                                    i = 1;
                                }
                            } catch (Throwable th) {
                                throw th;
                            }
                        }
                        if (i != 0) {
                            break;
                        }
                    } else {
                        int i4 = tabAt.hash;
                        if (i4 == -1) {
                            nodeArr = helpTransfer(nodeArr, tabAt);
                        } else {
                            synchronized (tabAt) {
                                try {
                                    if (tabAt(nodeArr, i3) == tabAt) {
                                        if (i4 >= 0) {
                                            Node node2 = null;
                                            Node node3 = tabAt;
                                            i = 1;
                                            while (true) {
                                                if (node3.hash == spread) {
                                                    Object obj3 = node3.key;
                                                    if (obj3 != obj) {
                                                        if (obj3 != null && obj.equals(obj3)) {
                                                            break;
                                                        }
                                                    } else {
                                                        break;
                                                    }
                                                }
                                                Node node4 = node3.next;
                                                if (node4 == null) {
                                                    Object apply = biFunction.apply(obj, null);
                                                    if (apply == null) {
                                                        obj2 = apply;
                                                    } else if (node3.next == null) {
                                                        node3.next = new Node(spread, obj, apply);
                                                        obj2 = apply;
                                                        i2 = 1;
                                                    } else {
                                                        throw new IllegalStateException("Recursive update");
                                                    }
                                                } else {
                                                    i++;
                                                    Node node5 = node4;
                                                    node2 = node3;
                                                    node3 = node5;
                                                }
                                            }
                                            Object apply2 = biFunction.apply(obj, node3.val);
                                            if (apply2 != null) {
                                                node3.val = apply2;
                                                obj2 = apply2;
                                            } else {
                                                Node node6 = node3.next;
                                                if (node2 != null) {
                                                    node2.next = node6;
                                                } else {
                                                    setTabAt(nodeArr, i3, node6);
                                                }
                                                obj2 = apply2;
                                                i2 = -1;
                                            }
                                        } else if (tabAt instanceof TreeBin) {
                                            TreeBin treeBin = (TreeBin) tabAt;
                                            TreeNode treeNode = treeBin.root;
                                            TreeNode findTreeNode = treeNode != null ? treeNode.findTreeNode(spread, obj, (Class) null) : null;
                                            Object apply3 = biFunction.apply(obj, findTreeNode == null ? null : findTreeNode.val);
                                            if (apply3 != null) {
                                                if (findTreeNode != null) {
                                                    findTreeNode.val = apply3;
                                                } else {
                                                    treeBin.putTreeVal(spread, obj, apply3);
                                                    i2 = 1;
                                                }
                                            } else if (findTreeNode != null) {
                                                if (treeBin.removeTreeNode(findTreeNode)) {
                                                    setTabAt(nodeArr, i3, untreeify(treeBin.first));
                                                }
                                                i2 = -1;
                                            }
                                            obj2 = apply3;
                                            i = 1;
                                        } else if (tabAt instanceof ReservationNode) {
                                            throw new IllegalStateException("Recursive update");
                                        }
                                    }
                                } catch (Throwable th2) {
                                    while (true) {
                                        throw th2;
                                    }
                                }
                            }
                            if (i != 0) {
                                if (i >= 8) {
                                    treeifyBin(nodeArr, i3);
                                }
                            }
                        }
                    }
                }
            }
            nodeArr = initTable();
        }
        if (i2 != 0) {
            addCount((long) i2, i);
        }
        return obj2;
    }

    public Object computeIfAbsent(Object obj, Function function) {
        TreeNode findTreeNode;
        Object obj2;
        Object obj3;
        if (obj == null || function == null) {
            throw null;
        }
        int spread = spread(obj.hashCode());
        Node[] nodeArr = this.table;
        Object obj4 = null;
        int i = 0;
        while (true) {
            if (nodeArr != null) {
                int length = nodeArr.length;
                if (length != 0) {
                    int i2 = (length - 1) & spread;
                    Node tabAt = tabAt(nodeArr, i2);
                    boolean z = true;
                    if (tabAt == null) {
                        ReservationNode reservationNode = new ReservationNode();
                        synchronized (reservationNode) {
                            try {
                                if (casTabAt(nodeArr, i2, (Node) null, reservationNode)) {
                                    obj4 = function.apply(obj);
                                    setTabAt(nodeArr, i2, obj4 != null ? new Node(spread, obj, obj4) : null);
                                    i = 1;
                                }
                            } catch (Throwable th) {
                                throw th;
                            }
                        }
                        if (i != 0) {
                            break;
                        }
                    } else {
                        int i3 = tabAt.hash;
                        if (i3 == -1) {
                            nodeArr = helpTransfer(nodeArr, tabAt);
                        } else if (i3 == spread && (((obj2 = tabAt.key) == obj || (obj2 != null && obj.equals(obj2))) && (obj3 = tabAt.val) != null)) {
                            return obj3;
                        } else {
                            synchronized (tabAt) {
                                try {
                                    if (tabAt(nodeArr, i2) == tabAt) {
                                        if (i3 >= 0) {
                                            Node node = tabAt;
                                            i = 1;
                                            while (true) {
                                                if (node.hash == spread) {
                                                    Object obj5 = node.key;
                                                    if (obj5 != obj) {
                                                        if (obj5 != null && obj.equals(obj5)) {
                                                            break;
                                                        }
                                                    } else {
                                                        break;
                                                    }
                                                }
                                                Node node2 = node.next;
                                                if (node2 == null) {
                                                    Object apply = function.apply(obj);
                                                    if (apply == null) {
                                                        obj4 = apply;
                                                    } else if (node.next == null) {
                                                        node.next = new Node(spread, obj, apply);
                                                        obj4 = apply;
                                                    } else {
                                                        throw new IllegalStateException("Recursive update");
                                                    }
                                                } else {
                                                    i++;
                                                    node = node2;
                                                }
                                            }
                                            obj4 = node.val;
                                        } else if (tabAt instanceof TreeBin) {
                                            TreeBin treeBin = (TreeBin) tabAt;
                                            TreeNode treeNode = treeBin.root;
                                            if (treeNode == null || (findTreeNode = treeNode.findTreeNode(spread, obj, (Class) null)) == null) {
                                                obj4 = function.apply(obj);
                                                if (obj4 != null) {
                                                    treeBin.putTreeVal(spread, obj, obj4);
                                                    i = 2;
                                                }
                                            } else {
                                                obj4 = findTreeNode.val;
                                            }
                                            z = false;
                                            i = 2;
                                        } else if (tabAt instanceof ReservationNode) {
                                            throw new IllegalStateException("Recursive update");
                                        }
                                    }
                                    z = false;
                                } catch (Throwable th2) {
                                    while (true) {
                                        throw th2;
                                    }
                                }
                            }
                            if (i != 0) {
                                if (i >= 8) {
                                    treeifyBin(nodeArr, i2);
                                }
                                if (!z) {
                                    return obj4;
                                }
                            }
                        }
                    }
                }
            }
            nodeArr = initTable();
        }
        if (obj4 != null) {
            addCount(1, i);
        }
        return obj4;
    }

    public Object computeIfPresent(Object obj, BiFunction biFunction) {
        TreeNode findTreeNode;
        if (obj == null || biFunction == null) {
            throw null;
        }
        int spread = spread(obj.hashCode());
        Node[] nodeArr = this.table;
        int i = 0;
        Object obj2 = null;
        int i2 = 0;
        while (true) {
            if (nodeArr != null) {
                int length = nodeArr.length;
                if (length != 0) {
                    int i3 = (length - 1) & spread;
                    Node tabAt = tabAt(nodeArr, i3);
                    if (tabAt == null) {
                        break;
                    }
                    int i4 = tabAt.hash;
                    if (i4 == -1) {
                        nodeArr = helpTransfer(nodeArr, tabAt);
                    } else {
                        synchronized (tabAt) {
                            try {
                                if (tabAt(nodeArr, i3) == tabAt) {
                                    if (i4 >= 0) {
                                        i2 = 1;
                                        Node node = null;
                                        Node node2 = tabAt;
                                        while (true) {
                                            if (node2.hash == spread) {
                                                Object obj3 = node2.key;
                                                if (obj3 != obj) {
                                                    if (obj3 != null && obj.equals(obj3)) {
                                                        break;
                                                    }
                                                } else {
                                                    break;
                                                }
                                            }
                                            Node node3 = node2.next;
                                            if (node3 == null) {
                                                break;
                                            }
                                            i2++;
                                            Node node4 = node3;
                                            node = node2;
                                            node2 = node4;
                                        }
                                        obj2 = biFunction.apply(obj, node2.val);
                                        if (obj2 != null) {
                                            node2.val = obj2;
                                        } else {
                                            Node node5 = node2.next;
                                            if (node != null) {
                                                node.next = node5;
                                            } else {
                                                setTabAt(nodeArr, i3, node5);
                                            }
                                            i = -1;
                                        }
                                    } else if (tabAt instanceof TreeBin) {
                                        TreeBin treeBin = (TreeBin) tabAt;
                                        TreeNode treeNode = treeBin.root;
                                        if (!(treeNode == null || (findTreeNode = treeNode.findTreeNode(spread, obj, (Class) null)) == null)) {
                                            obj2 = biFunction.apply(obj, findTreeNode.val);
                                            if (obj2 != null) {
                                                findTreeNode.val = obj2;
                                            } else {
                                                if (treeBin.removeTreeNode(findTreeNode)) {
                                                    setTabAt(nodeArr, i3, untreeify(treeBin.first));
                                                }
                                                i = -1;
                                            }
                                        }
                                        i2 = 2;
                                    } else if (tabAt instanceof ReservationNode) {
                                        throw new IllegalStateException("Recursive update");
                                    }
                                }
                            } catch (Throwable th) {
                                while (true) {
                                    throw th;
                                }
                            }
                        }
                        if (i2 != 0) {
                            break;
                        }
                    }
                }
            }
            nodeArr = initTable();
        }
        if (i != 0) {
            addCount((long) i, i2);
        }
        return obj2;
    }

    public boolean containsKey(Object obj) {
        return get(obj) != null;
    }

    public boolean containsValue(Object obj) {
        obj.getClass();
        Node[] nodeArr = this.table;
        if (nodeArr != null) {
            Traverser traverser = new Traverser(nodeArr, nodeArr.length, 0, nodeArr.length);
            while (true) {
                Node advance = traverser.advance();
                if (advance == null) {
                    break;
                }
                Object obj2 = advance.val;
                if (obj2 == obj) {
                    return true;
                }
                if (obj2 != null && obj.equals(obj2)) {
                    return true;
                }
            }
        }
        return false;
    }

    public Set<Map.Entry<K, V>> entrySet() {
        EntrySetView entrySetView = this.entrySet;
        if (entrySetView != null) {
            return entrySetView;
        }
        EntrySetView entrySetView2 = new EntrySetView(this);
        this.entrySet = entrySetView2;
        return entrySetView2;
    }

    public boolean equals(Object obj) {
        Object value;
        Object obj2;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Map)) {
            return false;
        }
        Map map = (Map) obj;
        Node[] nodeArr = this.table;
        int length = nodeArr == null ? 0 : nodeArr.length;
        Traverser traverser = new Traverser(nodeArr, length, 0, length);
        while (true) {
            Node advance = traverser.advance();
            if (advance != null) {
                Object obj3 = advance.val;
                Object obj4 = map.get(advance.key);
                if (obj4 == null || (obj4 != obj3 && !obj4.equals(obj3))) {
                    return false;
                }
            } else {
                for (Map.Entry entry : map.entrySet()) {
                    Object key = entry.getKey();
                    if (key == null || (value = entry.getValue()) == null || (obj2 = get(key)) == null || (value != obj2 && !value.equals(obj2))) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    public void forEach(BiConsumer biConsumer) {
        biConsumer.getClass();
        Node[] nodeArr = this.table;
        if (nodeArr != null) {
            Traverser traverser = new Traverser(nodeArr, nodeArr.length, 0, nodeArr.length);
            while (true) {
                Node advance = traverser.advance();
                if (advance != null) {
                    biConsumer.accept(advance.key, advance.val);
                } else {
                    return;
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:29:0x004d, code lost:
        return r1.val;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public V get(java.lang.Object r5) {
        /*
            r4 = this;
            int r0 = r5.hashCode()
            int r0 = spread(r0)
            j$.util.concurrent.ConcurrentHashMap$Node[] r1 = r4.table
            r2 = 0
            if (r1 == 0) goto L_0x004e
            int r3 = r1.length
            if (r3 <= 0) goto L_0x004e
            int r3 = r3 + -1
            r3 = r3 & r0
            j$.util.concurrent.ConcurrentHashMap$Node r1 = tabAt(r1, r3)
            if (r1 == 0) goto L_0x004e
            int r3 = r1.hash
            if (r3 != r0) goto L_0x002c
            java.lang.Object r3 = r1.key
            if (r3 == r5) goto L_0x0029
            if (r3 == 0) goto L_0x0037
            boolean r3 = r5.equals(r3)
            if (r3 == 0) goto L_0x0037
        L_0x0029:
            java.lang.Object r5 = r1.val
            return r5
        L_0x002c:
            if (r3 >= 0) goto L_0x0037
            j$.util.concurrent.ConcurrentHashMap$Node r5 = r1.find(r0, r5)
            if (r5 == 0) goto L_0x0036
            java.lang.Object r2 = r5.val
        L_0x0036:
            return r2
        L_0x0037:
            j$.util.concurrent.ConcurrentHashMap$Node r1 = r1.next
            if (r1 == 0) goto L_0x004e
            int r3 = r1.hash
            if (r3 != r0) goto L_0x0037
            java.lang.Object r3 = r1.key
            if (r3 == r5) goto L_0x004b
            if (r3 == 0) goto L_0x0037
            boolean r3 = r5.equals(r3)
            if (r3 == 0) goto L_0x0037
        L_0x004b:
            java.lang.Object r5 = r1.val
            return r5
        L_0x004e:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: j$.util.concurrent.ConcurrentHashMap.get(java.lang.Object):java.lang.Object");
    }

    public Object getOrDefault(Object obj, Object obj2) {
        Object obj3 = get(obj);
        return obj3 == null ? obj2 : obj3;
    }

    public int hashCode() {
        Node[] nodeArr = this.table;
        int i = 0;
        if (nodeArr != null) {
            Traverser traverser = new Traverser(nodeArr, nodeArr.length, 0, nodeArr.length);
            while (true) {
                Node advance = traverser.advance();
                if (advance == null) {
                    break;
                }
                i += advance.val.hashCode() ^ advance.key.hashCode();
            }
        }
        return i;
    }

    /* access modifiers changed from: package-private */
    public final Node[] helpTransfer(Node[] nodeArr, Node node) {
        Node[] nodeArr2;
        int i;
        if (nodeArr == null || !(node instanceof ForwardingNode) || (nodeArr2 = ((ForwardingNode) node).nextTable) == null) {
            return this.table;
        }
        int resizeStamp = resizeStamp(nodeArr.length);
        while (true) {
            if (nodeArr2 != this.nextTable || this.table != nodeArr || (i = this.sizeCtl) >= 0 || (i >>> 16) != resizeStamp || i == resizeStamp + 1 || i == 65535 + resizeStamp || this.transferIndex <= 0) {
                break;
            }
            if (U.compareAndSetInt(this, SIZECTL, i, i + 1)) {
                transfer(nodeArr, nodeArr2);
                break;
            }
        }
        return nodeArr2;
    }

    public boolean isEmpty() {
        return sumCount() <= 0;
    }

    public Set<K> keySet() {
        KeySetView keySetView = this.keySet;
        if (keySetView != null) {
            return keySetView;
        }
        KeySetView keySetView2 = new KeySetView(this, (Object) null);
        this.keySet = keySetView2;
        return keySetView2;
    }

    public long mappingCount() {
        long sumCount = sumCount();
        if (sumCount < 0) {
            return 0;
        }
        return sumCount;
    }

    public Object merge(Object obj, Object obj2, BiFunction biFunction) {
        int i;
        Object obj3 = obj;
        Object obj4 = obj2;
        BiFunction biFunction2 = biFunction;
        if (obj3 == null || obj4 == null || biFunction2 == null) {
            throw null;
        }
        int spread = spread(obj.hashCode());
        Node[] nodeArr = this.table;
        int i2 = 0;
        Object obj5 = null;
        int i3 = 0;
        while (true) {
            if (nodeArr != null) {
                int length = nodeArr.length;
                if (length != 0) {
                    int i4 = (length - 1) & spread;
                    Node tabAt = tabAt(nodeArr, i4);
                    i = 1;
                    if (tabAt != null) {
                        int i5 = tabAt.hash;
                        if (i5 == -1) {
                            nodeArr = helpTransfer(nodeArr, tabAt);
                        } else {
                            synchronized (tabAt) {
                                try {
                                    if (tabAt(nodeArr, i4) == tabAt) {
                                        if (i5 >= 0) {
                                            Node node = null;
                                            Node node2 = tabAt;
                                            i2 = 1;
                                            while (true) {
                                                if (node2.hash == spread) {
                                                    Object obj6 = node2.key;
                                                    if (obj6 != obj3) {
                                                        if (obj6 != null && obj3.equals(obj6)) {
                                                            break;
                                                        }
                                                    } else {
                                                        break;
                                                    }
                                                }
                                                Node node3 = node2.next;
                                                if (node3 == null) {
                                                    node2.next = new Node(spread, obj3, obj4);
                                                    obj5 = obj4;
                                                    i3 = 1;
                                                    break;
                                                }
                                                i2++;
                                                Node node4 = node3;
                                                node = node2;
                                                node2 = node4;
                                            }
                                            Object apply = biFunction2.apply(node2.val, obj4);
                                            if (apply != null) {
                                                node2.val = apply;
                                                obj5 = apply;
                                            } else {
                                                Node node5 = node2.next;
                                                if (node != null) {
                                                    node.next = node5;
                                                } else {
                                                    setTabAt(nodeArr, i4, node5);
                                                }
                                                obj5 = apply;
                                                i3 = -1;
                                            }
                                        } else if (tabAt instanceof TreeBin) {
                                            TreeBin treeBin = (TreeBin) tabAt;
                                            TreeNode treeNode = treeBin.root;
                                            TreeNode findTreeNode = treeNode == null ? null : treeNode.findTreeNode(spread, obj3, (Class) null);
                                            Object apply2 = findTreeNode == null ? obj4 : biFunction2.apply(findTreeNode.val, obj4);
                                            if (apply2 != null) {
                                                if (findTreeNode != null) {
                                                    findTreeNode.val = apply2;
                                                } else {
                                                    treeBin.putTreeVal(spread, obj3, apply2);
                                                    i3 = 1;
                                                }
                                            } else if (findTreeNode != null) {
                                                if (treeBin.removeTreeNode(findTreeNode)) {
                                                    setTabAt(nodeArr, i4, untreeify(treeBin.first));
                                                }
                                                i3 = -1;
                                            }
                                            i2 = 2;
                                            obj5 = apply2;
                                        } else if (tabAt instanceof ReservationNode) {
                                            throw new IllegalStateException("Recursive update");
                                        }
                                    }
                                } catch (Throwable th) {
                                    while (true) {
                                        throw th;
                                    }
                                }
                            }
                            if (i2 != 0) {
                                if (i2 >= 8) {
                                    treeifyBin(nodeArr, i4);
                                }
                                i = i3;
                                obj4 = obj5;
                            }
                        }
                    } else if (casTabAt(nodeArr, i4, (Node) null, new Node(spread, obj3, obj4))) {
                        break;
                    }
                }
            }
            nodeArr = initTable();
        }
        if (i != 0) {
            addCount((long) i, i2);
        }
        return obj4;
    }

    public V put(K k, V v) {
        return putVal(k, v, false);
    }

    public void putAll(Map<? extends K, ? extends V> map) {
        tryPresize(map.size());
        for (Map.Entry next : map.entrySet()) {
            putVal(next.getKey(), next.getValue(), false);
        }
    }

    public V putIfAbsent(K k, V v) {
        return putVal(k, v, true);
    }

    /* access modifiers changed from: package-private */
    public final Object putVal(Object obj, Object obj2, boolean z) {
        Object obj3;
        Object obj4;
        Object obj5;
        if (obj == null || obj2 == null) {
            throw null;
        }
        int spread = spread(obj.hashCode());
        Node[] nodeArr = this.table;
        int i = 0;
        while (true) {
            if (nodeArr != null) {
                int length = nodeArr.length;
                if (length != 0) {
                    int i2 = (length - 1) & spread;
                    Node tabAt = tabAt(nodeArr, i2);
                    if (tabAt != null) {
                        int i3 = tabAt.hash;
                        if (i3 == -1) {
                            nodeArr = helpTransfer(nodeArr, tabAt);
                        } else if (z && i3 == spread && (((obj4 = tabAt.key) == obj || (obj4 != null && obj.equals(obj4))) && (obj5 = tabAt.val) != null)) {
                            return obj5;
                        } else {
                            synchronized (tabAt) {
                                try {
                                    if (tabAt(nodeArr, i2) == tabAt) {
                                        if (i3 >= 0) {
                                            i = 1;
                                            Node node = tabAt;
                                            while (true) {
                                                if (node.hash == spread) {
                                                    Object obj6 = node.key;
                                                    if (obj6 != obj) {
                                                        if (obj6 != null && obj.equals(obj6)) {
                                                            break;
                                                        }
                                                    } else {
                                                        break;
                                                    }
                                                }
                                                Node node2 = node.next;
                                                if (node2 == null) {
                                                    node.next = new Node(spread, obj, obj2);
                                                    break;
                                                }
                                                i++;
                                                node = node2;
                                            }
                                            obj3 = node.val;
                                            if (!z) {
                                                node.val = obj2;
                                            }
                                        } else if (tabAt instanceof TreeBin) {
                                            TreeNode putTreeVal = ((TreeBin) tabAt).putTreeVal(spread, obj, obj2);
                                            if (putTreeVal != null) {
                                                Object obj7 = putTreeVal.val;
                                                if (!z) {
                                                    putTreeVal.val = obj2;
                                                }
                                                obj3 = obj7;
                                            } else {
                                                obj3 = null;
                                            }
                                            i = 2;
                                        } else if (tabAt instanceof ReservationNode) {
                                            throw new IllegalStateException("Recursive update");
                                        }
                                    }
                                    obj3 = null;
                                } catch (Throwable th) {
                                    while (true) {
                                        throw th;
                                    }
                                }
                            }
                            if (i != 0) {
                                if (i >= 8) {
                                    treeifyBin(nodeArr, i2);
                                }
                                if (obj3 != null) {
                                    return obj3;
                                }
                            }
                        }
                    } else if (casTabAt(nodeArr, i2, (Node) null, new Node(spread, obj, obj2))) {
                        break;
                    }
                }
            }
            nodeArr = initTable();
        }
        addCount(1, i);
        return null;
    }

    public V remove(Object obj) {
        return replaceNode(obj, (Object) null, (Object) null);
    }

    public boolean remove(Object obj, Object obj2) {
        obj.getClass();
        return (obj2 == null || replaceNode(obj, (Object) null, obj2) == null) ? false : true;
    }

    /* access modifiers changed from: package-private */
    public boolean removeEntryIf(Predicate predicate) {
        predicate.getClass();
        Node[] nodeArr = this.table;
        boolean z = false;
        if (nodeArr != null) {
            Traverser traverser = new Traverser(nodeArr, nodeArr.length, 0, nodeArr.length);
            while (true) {
                Node advance = traverser.advance();
                if (advance == null) {
                    break;
                }
                Object obj = advance.key;
                Object obj2 = advance.val;
                if (predicate.test(new AbstractMap.SimpleImmutableEntry(obj, obj2)) && replaceNode(obj, (Object) null, obj2) != null) {
                    z = true;
                }
            }
        }
        return z;
    }

    /* access modifiers changed from: package-private */
    public boolean removeValueIf(Predicate predicate) {
        predicate.getClass();
        Node[] nodeArr = this.table;
        boolean z = false;
        if (nodeArr != null) {
            Traverser traverser = new Traverser(nodeArr, nodeArr.length, 0, nodeArr.length);
            while (true) {
                Node advance = traverser.advance();
                if (advance == null) {
                    break;
                }
                Object obj = advance.key;
                Object obj2 = advance.val;
                if (predicate.test(obj2) && replaceNode(obj, (Object) null, obj2) != null) {
                    z = true;
                }
            }
        }
        return z;
    }

    public Object replace(Object obj, Object obj2) {
        if (obj != null && obj2 != null) {
            return replaceNode(obj, obj2, (Object) null);
        }
        throw null;
    }

    public boolean replace(K k, V v, V v2) {
        if (k != null && v != null && v2 != null) {
            return replaceNode(k, v2, v) != null;
        }
        throw null;
    }

    public void replaceAll(BiFunction biFunction) {
        biFunction.getClass();
        Node[] nodeArr = this.table;
        if (nodeArr != null) {
            Traverser traverser = new Traverser(nodeArr, nodeArr.length, 0, nodeArr.length);
            while (true) {
                Node advance = traverser.advance();
                if (advance != null) {
                    Object obj = advance.val;
                    Object obj2 = advance.key;
                    do {
                        Object apply = biFunction.apply(obj2, obj);
                        apply.getClass();
                        if (replaceNode(obj2, apply, obj) != null || (obj = get(obj2)) == null) {
                        }
                        Object apply2 = biFunction.apply(obj2, obj);
                        apply2.getClass();
                        break;
                    } while ((obj = get(obj2)) == null);
                } else {
                    return;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final Object replaceNode(Object obj, Object obj2, Object obj3) {
        int length;
        int i;
        Node tabAt;
        Object obj4;
        boolean z;
        TreeNode findTreeNode;
        Node untreeify;
        int spread = spread(obj.hashCode());
        Node[] nodeArr = this.table;
        while (true) {
            if (nodeArr == null || (length = nodeArr.length) == 0 || (tabAt = tabAt(nodeArr, i)) == null) {
                break;
            }
            int i2 = tabAt.hash;
            if (i2 == -1) {
                nodeArr = helpTransfer(nodeArr, tabAt);
            } else {
                synchronized (tabAt) {
                    try {
                        if (tabAt(nodeArr, (i = (length - 1) & spread)) == tabAt) {
                            z = true;
                            if (i2 >= 0) {
                                Node node = null;
                                Node node2 = tabAt;
                                while (true) {
                                    if (node2.hash == spread) {
                                        Object obj5 = node2.key;
                                        if (obj5 != obj) {
                                            if (obj5 != null && obj.equals(obj5)) {
                                                break;
                                            }
                                        } else {
                                            break;
                                        }
                                    }
                                    Node node3 = node2.next;
                                    if (node3 == null) {
                                        break;
                                    }
                                    Node node4 = node3;
                                    node = node2;
                                    node2 = node4;
                                }
                                obj4 = node2.val;
                                if (obj3 == null || obj3 == obj4 || (obj4 != null && obj3.equals(obj4))) {
                                    if (obj2 != null) {
                                        node2.val = obj2;
                                    } else if (node != null) {
                                        node.next = node2.next;
                                    } else {
                                        untreeify = node2.next;
                                    }
                                }
                                obj4 = null;
                            } else if (tabAt instanceof TreeBin) {
                                TreeBin treeBin = (TreeBin) tabAt;
                                TreeNode treeNode = treeBin.root;
                                if (treeNode != null && (findTreeNode = treeNode.findTreeNode(spread, obj, (Class) null)) != null) {
                                    obj4 = findTreeNode.val;
                                    if (obj3 == null || obj3 == obj4 || (obj4 != null && obj3.equals(obj4))) {
                                        if (obj2 != null) {
                                            findTreeNode.val = obj2;
                                        } else if (treeBin.removeTreeNode(findTreeNode)) {
                                            untreeify = untreeify(treeBin.first);
                                        }
                                    }
                                }
                                obj4 = null;
                            } else if (tabAt instanceof ReservationNode) {
                                throw new IllegalStateException("Recursive update");
                            }
                            setTabAt(nodeArr, i, untreeify);
                        }
                        z = false;
                        obj4 = null;
                    } catch (Throwable th) {
                        while (true) {
                            throw th;
                        }
                    }
                }
                if (z) {
                    if (obj4 != null) {
                        if (obj2 == null) {
                            addCount(-1, -1);
                        }
                        return obj4;
                    }
                }
            }
        }
        return null;
    }

    public int size() {
        long sumCount = sumCount();
        if (sumCount < 0) {
            return 0;
        }
        if (sumCount > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return (int) sumCount;
    }

    /* access modifiers changed from: package-private */
    public final long sumCount() {
        CounterCell[] counterCellArr = this.counterCells;
        long j = this.baseCount;
        if (counterCellArr != null) {
            for (CounterCell counterCell : counterCellArr) {
                if (counterCell != null) {
                    j += counterCell.value;
                }
            }
        }
        return j;
    }

    public String toString() {
        Node[] nodeArr = this.table;
        int length = nodeArr == null ? 0 : nodeArr.length;
        Traverser traverser = new Traverser(nodeArr, length, 0, length);
        StringBuilder sb = new StringBuilder();
        sb.append(AbstractJsonLexerKt.BEGIN_OBJ);
        Node advance = traverser.advance();
        if (advance != null) {
            while (true) {
                Object obj = advance.key;
                Object obj2 = advance.val;
                if (obj == this) {
                    obj = "(this Map)";
                }
                sb.append(obj);
                sb.append('=');
                if (obj2 == this) {
                    obj2 = "(this Map)";
                }
                sb.append(obj2);
                advance = traverser.advance();
                if (advance == null) {
                    break;
                }
                sb.append(',');
                sb.append(' ');
            }
        }
        sb.append(AbstractJsonLexerKt.END_OBJ);
        return sb.toString();
    }

    public java.util.Collection<V> values() {
        ValuesView valuesView = this.values;
        if (valuesView != null) {
            return valuesView;
        }
        ValuesView valuesView2 = new ValuesView(this);
        this.values = valuesView2;
        return valuesView2;
    }
}
