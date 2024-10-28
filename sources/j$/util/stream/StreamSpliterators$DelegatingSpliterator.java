package j$.util.stream;

import j$.util.Spliterator;
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;
import java.util.function.Supplier;

class StreamSpliterators$DelegatingSpliterator implements Spliterator {
    private Spliterator s;
    private final Supplier supplier;

    static final class OfDouble extends OfPrimitive implements Spliterator.OfDouble {
        OfDouble(Supplier supplier) {
            super(supplier);
        }

        public /* bridge */ /* synthetic */ void forEachRemaining(DoubleConsumer doubleConsumer) {
            super.forEachRemaining(doubleConsumer);
        }

        public /* bridge */ /* synthetic */ boolean tryAdvance(DoubleConsumer doubleConsumer) {
            return super.tryAdvance(doubleConsumer);
        }

        public /* bridge */ /* synthetic */ Spliterator.OfDouble trySplit() {
            return (Spliterator.OfDouble) StreamSpliterators$DelegatingSpliterator.super.trySplit();
        }
    }

    static final class OfInt extends OfPrimitive implements Spliterator.OfInt {
        OfInt(Supplier supplier) {
            super(supplier);
        }

        public /* bridge */ /* synthetic */ void forEachRemaining(IntConsumer intConsumer) {
            super.forEachRemaining(intConsumer);
        }

        public /* bridge */ /* synthetic */ boolean tryAdvance(IntConsumer intConsumer) {
            return super.tryAdvance(intConsumer);
        }

        public /* bridge */ /* synthetic */ Spliterator.OfInt trySplit() {
            return (Spliterator.OfInt) StreamSpliterators$DelegatingSpliterator.super.trySplit();
        }
    }

    static final class OfLong extends OfPrimitive implements Spliterator.OfLong {
        OfLong(Supplier supplier) {
            super(supplier);
        }

        public /* bridge */ /* synthetic */ void forEachRemaining(LongConsumer longConsumer) {
            super.forEachRemaining(longConsumer);
        }

        public /* bridge */ /* synthetic */ boolean tryAdvance(LongConsumer longConsumer) {
            return super.tryAdvance(longConsumer);
        }

        public /* bridge */ /* synthetic */ Spliterator.OfLong trySplit() {
            return (Spliterator.OfLong) StreamSpliterators$DelegatingSpliterator.super.trySplit();
        }
    }

    static class OfPrimitive extends StreamSpliterators$DelegatingSpliterator implements Spliterator.OfPrimitive {
        OfPrimitive(Supplier supplier) {
            super(supplier);
        }

        public void forEachRemaining(Object obj) {
            ((Spliterator.OfPrimitive) get()).forEachRemaining(obj);
        }

        public boolean tryAdvance(Object obj) {
            return ((Spliterator.OfPrimitive) get()).tryAdvance(obj);
        }

        public /* bridge */ /* synthetic */ Spliterator.OfPrimitive trySplit() {
            return (Spliterator.OfPrimitive) StreamSpliterators$DelegatingSpliterator.super.trySplit();
        }
    }

    StreamSpliterators$DelegatingSpliterator(Supplier supplier2) {
        this.supplier = supplier2;
    }

    public int characteristics() {
        return get().characteristics();
    }

    public long estimateSize() {
        return get().estimateSize();
    }

    public void forEachRemaining(Consumer consumer) {
        get().forEachRemaining(consumer);
    }

    /* access modifiers changed from: package-private */
    public Spliterator get() {
        if (this.s == null) {
            this.s = (Spliterator) this.supplier.get();
        }
        return this.s;
    }

    public Comparator getComparator() {
        return get().getComparator();
    }

    public long getExactSizeIfKnown() {
        return get().getExactSizeIfKnown();
    }

    public /* synthetic */ boolean hasCharacteristics(int i) {
        return Spliterator.CC.$default$hasCharacteristics(this, i);
    }

    public String toString() {
        String name = getClass().getName();
        Spliterator spliterator = get();
        return name + "[" + spliterator + "]";
    }

    public boolean tryAdvance(Consumer consumer) {
        return get().tryAdvance(consumer);
    }

    public Spliterator trySplit() {
        return get().trySplit();
    }
}
