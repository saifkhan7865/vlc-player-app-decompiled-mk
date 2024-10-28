package j$.util;

import j$.util.Iterator;
import j$.util.PrimitiveIterator;
import j$.util.Spliterator;
import j$.util.function.Consumer$CC;
import j$.util.function.DoubleConsumer$CC;
import j$.util.function.IntConsumer$CC;
import j$.util.function.LongConsumer$CC;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;

public final class Spliterators {
    private static final Spliterator.OfDouble EMPTY_DOUBLE_SPLITERATOR = new EmptySpliterator.OfDouble();
    private static final Spliterator.OfInt EMPTY_INT_SPLITERATOR = new EmptySpliterator.OfInt();
    private static final Spliterator.OfLong EMPTY_LONG_SPLITERATOR = new EmptySpliterator.OfLong();
    private static final Spliterator EMPTY_SPLITERATOR = new EmptySpliterator.OfRef();

    static final class ArraySpliterator implements Spliterator {
        private final Object[] array;
        private final int characteristics;
        private final int fence;
        private int index;

        public ArraySpliterator(Object[] objArr, int i, int i2, int i3) {
            this.array = objArr;
            this.index = i;
            this.fence = i2;
            this.characteristics = i3 | 16448;
        }

        public int characteristics() {
            return this.characteristics;
        }

        public long estimateSize() {
            return (long) (this.fence - this.index);
        }

        public void forEachRemaining(Consumer consumer) {
            int i;
            consumer.getClass();
            Object[] objArr = this.array;
            int length = objArr.length;
            int i2 = this.fence;
            if (length >= i2 && (i = this.index) >= 0) {
                this.index = i2;
                if (i < i2) {
                    do {
                        consumer.accept(objArr[i]);
                        i++;
                    } while (i < i2);
                }
            }
        }

        public Comparator getComparator() {
            if (hasCharacteristics(4)) {
                return null;
            }
            throw new IllegalStateException();
        }

        public /* synthetic */ long getExactSizeIfKnown() {
            return Spliterator.CC.$default$getExactSizeIfKnown(this);
        }

        public /* synthetic */ boolean hasCharacteristics(int i) {
            return Spliterator.CC.$default$hasCharacteristics(this, i);
        }

        public boolean tryAdvance(Consumer consumer) {
            consumer.getClass();
            int i = this.index;
            if (i < 0 || i >= this.fence) {
                return false;
            }
            Object[] objArr = this.array;
            this.index = i + 1;
            consumer.accept(objArr[i]);
            return true;
        }

        public Spliterator trySplit() {
            int i = this.index;
            int i2 = (this.fence + i) >>> 1;
            if (i >= i2) {
                return null;
            }
            Object[] objArr = this.array;
            this.index = i2;
            return new ArraySpliterator(objArr, i, i2, this.characteristics);
        }
    }

    static final class DoubleArraySpliterator implements Spliterator.OfDouble {
        private final double[] array;
        private final int characteristics;
        private final int fence;
        private int index;

        public DoubleArraySpliterator(double[] dArr, int i, int i2, int i3) {
            this.array = dArr;
            this.index = i;
            this.fence = i2;
            this.characteristics = i3 | 16448;
        }

        public int characteristics() {
            return this.characteristics;
        }

        public long estimateSize() {
            return (long) (this.fence - this.index);
        }

        public /* synthetic */ void forEachRemaining(Consumer consumer) {
            Spliterator.OfDouble.CC.$default$forEachRemaining(this, consumer);
        }

        public void forEachRemaining(DoubleConsumer doubleConsumer) {
            int i;
            doubleConsumer.getClass();
            double[] dArr = this.array;
            int length = dArr.length;
            int i2 = this.fence;
            if (length >= i2 && (i = this.index) >= 0) {
                this.index = i2;
                if (i < i2) {
                    do {
                        doubleConsumer.accept(dArr[i]);
                        i++;
                    } while (i < i2);
                }
            }
        }

        public Comparator getComparator() {
            if (hasCharacteristics(4)) {
                return null;
            }
            throw new IllegalStateException();
        }

        public /* synthetic */ long getExactSizeIfKnown() {
            return Spliterator.CC.$default$getExactSizeIfKnown(this);
        }

        public /* synthetic */ boolean hasCharacteristics(int i) {
            return Spliterator.CC.$default$hasCharacteristics(this, i);
        }

        public /* synthetic */ boolean tryAdvance(Consumer consumer) {
            return Spliterator.OfDouble.CC.$default$tryAdvance(this, consumer);
        }

        public boolean tryAdvance(DoubleConsumer doubleConsumer) {
            doubleConsumer.getClass();
            int i = this.index;
            if (i < 0 || i >= this.fence) {
                return false;
            }
            double[] dArr = this.array;
            this.index = i + 1;
            doubleConsumer.accept(dArr[i]);
            return true;
        }

        public Spliterator.OfDouble trySplit() {
            int i = this.index;
            int i2 = (this.fence + i) >>> 1;
            if (i >= i2) {
                return null;
            }
            double[] dArr = this.array;
            this.index = i2;
            return new DoubleArraySpliterator(dArr, i, i2, this.characteristics);
        }
    }

    private static abstract class EmptySpliterator {

        private static final class OfDouble extends EmptySpliterator implements Spliterator.OfDouble {
            OfDouble() {
            }

            public /* synthetic */ void forEachRemaining(Consumer consumer) {
                Spliterator.OfDouble.CC.$default$forEachRemaining(this, consumer);
            }

            public /* bridge */ /* synthetic */ void forEachRemaining(DoubleConsumer doubleConsumer) {
                super.forEachRemaining(doubleConsumer);
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

            public /* synthetic */ boolean tryAdvance(Consumer consumer) {
                return Spliterator.OfDouble.CC.$default$tryAdvance(this, consumer);
            }

            public /* bridge */ /* synthetic */ boolean tryAdvance(DoubleConsumer doubleConsumer) {
                return super.tryAdvance(doubleConsumer);
            }
        }

        private static final class OfInt extends EmptySpliterator implements Spliterator.OfInt {
            OfInt() {
            }

            public /* synthetic */ void forEachRemaining(Consumer consumer) {
                Spliterator.OfInt.CC.$default$forEachRemaining(this, consumer);
            }

            public /* bridge */ /* synthetic */ void forEachRemaining(IntConsumer intConsumer) {
                super.forEachRemaining(intConsumer);
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

            public /* synthetic */ boolean tryAdvance(Consumer consumer) {
                return Spliterator.OfInt.CC.$default$tryAdvance(this, consumer);
            }

            public /* bridge */ /* synthetic */ boolean tryAdvance(IntConsumer intConsumer) {
                return super.tryAdvance(intConsumer);
            }
        }

        private static final class OfLong extends EmptySpliterator implements Spliterator.OfLong {
            OfLong() {
            }

            public /* synthetic */ void forEachRemaining(Consumer consumer) {
                Spliterator.OfLong.CC.$default$forEachRemaining(this, consumer);
            }

            public /* bridge */ /* synthetic */ void forEachRemaining(LongConsumer longConsumer) {
                super.forEachRemaining(longConsumer);
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

            public /* synthetic */ boolean tryAdvance(Consumer consumer) {
                return Spliterator.OfLong.CC.$default$tryAdvance(this, consumer);
            }

            public /* bridge */ /* synthetic */ boolean tryAdvance(LongConsumer longConsumer) {
                return super.tryAdvance(longConsumer);
            }
        }

        private static final class OfRef extends EmptySpliterator implements Spliterator {
            OfRef() {
            }

            public /* bridge */ /* synthetic */ void forEachRemaining(Consumer consumer) {
                super.forEachRemaining(consumer);
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

            public /* bridge */ /* synthetic */ boolean tryAdvance(Consumer consumer) {
                return super.tryAdvance(consumer);
            }
        }

        EmptySpliterator() {
        }

        public int characteristics() {
            return 16448;
        }

        public long estimateSize() {
            return 0;
        }

        public void forEachRemaining(Object obj) {
            Objects.requireNonNull(obj);
        }

        public boolean tryAdvance(Object obj) {
            Objects.requireNonNull(obj);
            return false;
        }

        public Spliterator trySplit() {
            return null;
        }
    }

    static final class IntArraySpliterator implements Spliterator.OfInt {
        private final int[] array;
        private final int characteristics;
        private final int fence;
        private int index;

        public IntArraySpliterator(int[] iArr, int i, int i2, int i3) {
            this.array = iArr;
            this.index = i;
            this.fence = i2;
            this.characteristics = i3 | 16448;
        }

        public int characteristics() {
            return this.characteristics;
        }

        public long estimateSize() {
            return (long) (this.fence - this.index);
        }

        public /* synthetic */ void forEachRemaining(Consumer consumer) {
            Spliterator.OfInt.CC.$default$forEachRemaining(this, consumer);
        }

        public void forEachRemaining(IntConsumer intConsumer) {
            int i;
            intConsumer.getClass();
            int[] iArr = this.array;
            int length = iArr.length;
            int i2 = this.fence;
            if (length >= i2 && (i = this.index) >= 0) {
                this.index = i2;
                if (i < i2) {
                    do {
                        intConsumer.accept(iArr[i]);
                        i++;
                    } while (i < i2);
                }
            }
        }

        public Comparator getComparator() {
            if (hasCharacteristics(4)) {
                return null;
            }
            throw new IllegalStateException();
        }

        public /* synthetic */ long getExactSizeIfKnown() {
            return Spliterator.CC.$default$getExactSizeIfKnown(this);
        }

        public /* synthetic */ boolean hasCharacteristics(int i) {
            return Spliterator.CC.$default$hasCharacteristics(this, i);
        }

        public /* synthetic */ boolean tryAdvance(Consumer consumer) {
            return Spliterator.OfInt.CC.$default$tryAdvance(this, consumer);
        }

        public boolean tryAdvance(IntConsumer intConsumer) {
            intConsumer.getClass();
            int i = this.index;
            if (i < 0 || i >= this.fence) {
                return false;
            }
            int[] iArr = this.array;
            this.index = i + 1;
            intConsumer.accept(iArr[i]);
            return true;
        }

        public Spliterator.OfInt trySplit() {
            int i = this.index;
            int i2 = (this.fence + i) >>> 1;
            if (i >= i2) {
                return null;
            }
            int[] iArr = this.array;
            this.index = i2;
            return new IntArraySpliterator(iArr, i, i2, this.characteristics);
        }
    }

    static class IteratorSpliterator implements Spliterator {
        private int batch;
        private final int characteristics;
        private final Collection collection;
        private long est;
        private Iterator it;

        public IteratorSpliterator(Collection collection2, int i) {
            this.collection = collection2;
            this.it = null;
            this.characteristics = (i & 4096) == 0 ? i | 16448 : i;
        }

        public IteratorSpliterator(Iterator it2, int i) {
            this.collection = null;
            this.it = it2;
            this.est = Long.MAX_VALUE;
            this.characteristics = i & -16449;
        }

        public int characteristics() {
            return this.characteristics;
        }

        public long estimateSize() {
            if (this.it != null) {
                return this.est;
            }
            this.it = this.collection.iterator();
            long size = (long) this.collection.size();
            this.est = size;
            return size;
        }

        public void forEachRemaining(Consumer consumer) {
            consumer.getClass();
            Iterator it2 = this.it;
            if (it2 == null) {
                it2 = this.collection.iterator();
                this.it = it2;
                this.est = (long) this.collection.size();
            }
            Iterator.EL.forEachRemaining(it2, consumer);
        }

        public Comparator getComparator() {
            if (hasCharacteristics(4)) {
                return null;
            }
            throw new IllegalStateException();
        }

        public /* synthetic */ long getExactSizeIfKnown() {
            return Spliterator.CC.$default$getExactSizeIfKnown(this);
        }

        public /* synthetic */ boolean hasCharacteristics(int i) {
            return Spliterator.CC.$default$hasCharacteristics(this, i);
        }

        public boolean tryAdvance(Consumer consumer) {
            consumer.getClass();
            if (this.it == null) {
                this.it = this.collection.iterator();
                this.est = (long) this.collection.size();
            }
            if (!this.it.hasNext()) {
                return false;
            }
            consumer.accept(this.it.next());
            return true;
        }

        public Spliterator trySplit() {
            long j;
            java.util.Iterator it2 = this.it;
            if (it2 == null) {
                it2 = this.collection.iterator();
                this.it = it2;
                j = (long) this.collection.size();
                this.est = j;
            } else {
                j = this.est;
            }
            if (j <= 1 || !it2.hasNext()) {
                return null;
            }
            int i = this.batch + 1024;
            if (((long) i) > j) {
                i = (int) j;
            }
            if (i > 33554432) {
                i = 33554432;
            }
            Object[] objArr = new Object[i];
            int i2 = 0;
            do {
                objArr[i2] = it2.next();
                i2++;
                if (i2 >= i || !it2.hasNext()) {
                    this.batch = i2;
                    long j2 = this.est;
                }
                objArr[i2] = it2.next();
                i2++;
                break;
            } while (!it2.hasNext());
            this.batch = i2;
            long j22 = this.est;
            if (j22 != Long.MAX_VALUE) {
                this.est = j22 - ((long) i2);
            }
            return new ArraySpliterator(objArr, 0, i2, this.characteristics);
        }
    }

    static final class LongArraySpliterator implements Spliterator.OfLong {
        private final long[] array;
        private final int characteristics;
        private final int fence;
        private int index;

        public LongArraySpliterator(long[] jArr, int i, int i2, int i3) {
            this.array = jArr;
            this.index = i;
            this.fence = i2;
            this.characteristics = i3 | 16448;
        }

        public int characteristics() {
            return this.characteristics;
        }

        public long estimateSize() {
            return (long) (this.fence - this.index);
        }

        public /* synthetic */ void forEachRemaining(Consumer consumer) {
            Spliterator.OfLong.CC.$default$forEachRemaining(this, consumer);
        }

        public void forEachRemaining(LongConsumer longConsumer) {
            int i;
            longConsumer.getClass();
            long[] jArr = this.array;
            int length = jArr.length;
            int i2 = this.fence;
            if (length >= i2 && (i = this.index) >= 0) {
                this.index = i2;
                if (i < i2) {
                    do {
                        longConsumer.accept(jArr[i]);
                        i++;
                    } while (i < i2);
                }
            }
        }

        public Comparator getComparator() {
            if (hasCharacteristics(4)) {
                return null;
            }
            throw new IllegalStateException();
        }

        public /* synthetic */ long getExactSizeIfKnown() {
            return Spliterator.CC.$default$getExactSizeIfKnown(this);
        }

        public /* synthetic */ boolean hasCharacteristics(int i) {
            return Spliterator.CC.$default$hasCharacteristics(this, i);
        }

        public /* synthetic */ boolean tryAdvance(Consumer consumer) {
            return Spliterator.OfLong.CC.$default$tryAdvance(this, consumer);
        }

        public boolean tryAdvance(LongConsumer longConsumer) {
            longConsumer.getClass();
            int i = this.index;
            if (i < 0 || i >= this.fence) {
                return false;
            }
            long[] jArr = this.array;
            this.index = i + 1;
            longConsumer.accept(jArr[i]);
            return true;
        }

        public Spliterator.OfLong trySplit() {
            int i = this.index;
            int i2 = (this.fence + i) >>> 1;
            if (i >= i2) {
                return null;
            }
            long[] jArr = this.array;
            this.index = i2;
            return new LongArraySpliterator(jArr, i, i2, this.characteristics);
        }
    }

    private static void checkFromToBounds(int i, int i2, int i3) {
        if (i2 > i3) {
            throw new ArrayIndexOutOfBoundsException("origin(" + i2 + ") > fence(" + i3 + ")");
        } else if (i2 < 0) {
            throw new ArrayIndexOutOfBoundsException(i2);
        } else if (i3 > i) {
            throw new ArrayIndexOutOfBoundsException(i3);
        }
    }

    public static Spliterator.OfDouble emptyDoubleSpliterator() {
        return EMPTY_DOUBLE_SPLITERATOR;
    }

    public static Spliterator.OfInt emptyIntSpliterator() {
        return EMPTY_INT_SPLITERATOR;
    }

    public static Spliterator.OfLong emptyLongSpliterator() {
        return EMPTY_LONG_SPLITERATOR;
    }

    public static Spliterator emptySpliterator() {
        return EMPTY_SPLITERATOR;
    }

    public static PrimitiveIterator.OfDouble iterator(final Spliterator.OfDouble ofDouble) {
        Objects.requireNonNull(ofDouble);
        return new Object() {
            double nextElement;
            boolean valueReady = false;

            public void accept(double d) {
                this.valueReady = true;
                this.nextElement = d;
            }

            public /* synthetic */ DoubleConsumer andThen(DoubleConsumer doubleConsumer) {
                return DoubleConsumer$CC.$default$andThen(this, doubleConsumer);
            }

            public /* bridge */ /* synthetic */ void forEachRemaining(Object obj) {
                forEachRemaining((DoubleConsumer) obj);
            }

            public /* synthetic */ void forEachRemaining(Consumer consumer) {
                PrimitiveIterator.OfDouble.CC.$default$forEachRemaining((PrimitiveIterator.OfDouble) this, consumer);
            }

            public /* synthetic */ void forEachRemaining(DoubleConsumer doubleConsumer) {
                PrimitiveIterator.OfDouble.CC.$default$forEachRemaining((PrimitiveIterator.OfDouble) this, doubleConsumer);
            }

            public boolean hasNext() {
                if (!this.valueReady) {
                    Spliterator.OfDouble.this.tryAdvance((DoubleConsumer) this);
                }
                return this.valueReady;
            }

            public double nextDouble() {
                if (this.valueReady || hasNext()) {
                    this.valueReady = false;
                    return this.nextElement;
                }
                throw new NoSuchElementException();
            }
        };
    }

    public static PrimitiveIterator.OfInt iterator(final Spliterator.OfInt ofInt) {
        Objects.requireNonNull(ofInt);
        return new Object() {
            int nextElement;
            boolean valueReady = false;

            public void accept(int i) {
                this.valueReady = true;
                this.nextElement = i;
            }

            public /* synthetic */ IntConsumer andThen(IntConsumer intConsumer) {
                return IntConsumer$CC.$default$andThen(this, intConsumer);
            }

            public /* bridge */ /* synthetic */ void forEachRemaining(Object obj) {
                forEachRemaining((IntConsumer) obj);
            }

            public /* synthetic */ void forEachRemaining(Consumer consumer) {
                PrimitiveIterator.OfInt.CC.$default$forEachRemaining((PrimitiveIterator.OfInt) this, consumer);
            }

            public /* synthetic */ void forEachRemaining(IntConsumer intConsumer) {
                PrimitiveIterator.OfInt.CC.$default$forEachRemaining((PrimitiveIterator.OfInt) this, intConsumer);
            }

            public boolean hasNext() {
                if (!this.valueReady) {
                    Spliterator.OfInt.this.tryAdvance((IntConsumer) this);
                }
                return this.valueReady;
            }

            public int nextInt() {
                if (this.valueReady || hasNext()) {
                    this.valueReady = false;
                    return this.nextElement;
                }
                throw new NoSuchElementException();
            }
        };
    }

    public static PrimitiveIterator.OfLong iterator(final Spliterator.OfLong ofLong) {
        Objects.requireNonNull(ofLong);
        return new Object() {
            long nextElement;
            boolean valueReady = false;

            public void accept(long j) {
                this.valueReady = true;
                this.nextElement = j;
            }

            public /* synthetic */ LongConsumer andThen(LongConsumer longConsumer) {
                return LongConsumer$CC.$default$andThen(this, longConsumer);
            }

            public /* bridge */ /* synthetic */ void forEachRemaining(Object obj) {
                forEachRemaining((LongConsumer) obj);
            }

            public /* synthetic */ void forEachRemaining(Consumer consumer) {
                PrimitiveIterator.OfLong.CC.$default$forEachRemaining((PrimitiveIterator.OfLong) this, consumer);
            }

            public /* synthetic */ void forEachRemaining(LongConsumer longConsumer) {
                PrimitiveIterator.OfLong.CC.$default$forEachRemaining((PrimitiveIterator.OfLong) this, longConsumer);
            }

            public boolean hasNext() {
                if (!this.valueReady) {
                    Spliterator.OfLong.this.tryAdvance((LongConsumer) this);
                }
                return this.valueReady;
            }

            public long nextLong() {
                if (this.valueReady || hasNext()) {
                    this.valueReady = false;
                    return this.nextElement;
                }
                throw new NoSuchElementException();
            }
        };
    }

    public static java.util.Iterator iterator(final Spliterator spliterator) {
        Objects.requireNonNull(spliterator);
        return new Object() {
            Object nextElement;
            boolean valueReady = false;

            public void accept(Object obj) {
                this.valueReady = true;
                this.nextElement = obj;
            }

            public /* synthetic */ Consumer andThen(Consumer consumer) {
                return Consumer$CC.$default$andThen(this, consumer);
            }

            public boolean hasNext() {
                if (!this.valueReady) {
                    Spliterator.this.tryAdvance(this);
                }
                return this.valueReady;
            }

            public Object next() {
                if (this.valueReady || hasNext()) {
                    this.valueReady = false;
                    return this.nextElement;
                }
                throw new NoSuchElementException();
            }
        };
    }

    public static Spliterator.OfDouble spliterator(double[] dArr, int i, int i2, int i3) {
        checkFromToBounds(((double[]) Objects.requireNonNull(dArr)).length, i, i2);
        return new DoubleArraySpliterator(dArr, i, i2, i3);
    }

    public static Spliterator.OfInt spliterator(int[] iArr, int i, int i2, int i3) {
        checkFromToBounds(((int[]) Objects.requireNonNull(iArr)).length, i, i2);
        return new IntArraySpliterator(iArr, i, i2, i3);
    }

    public static Spliterator.OfLong spliterator(long[] jArr, int i, int i2, int i3) {
        checkFromToBounds(((long[]) Objects.requireNonNull(jArr)).length, i, i2);
        return new LongArraySpliterator(jArr, i, i2, i3);
    }

    public static Spliterator spliterator(Collection collection, int i) {
        return new IteratorSpliterator((Collection) Objects.requireNonNull(collection), i);
    }

    public static Spliterator spliterator(Object[] objArr, int i, int i2, int i3) {
        checkFromToBounds(((Object[]) Objects.requireNonNull(objArr)).length, i, i2);
        return new ArraySpliterator(objArr, i, i2, i3);
    }

    public static <T> Spliterator<T> spliteratorUnknownSize(java.util.Iterator<? extends T> it, int i) {
        return new IteratorSpliterator((java.util.Iterator) Objects.requireNonNull(it), i);
    }
}
