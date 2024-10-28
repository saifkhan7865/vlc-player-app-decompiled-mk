package j$.util.stream;

import j$.util.Spliterator;
import j$.util.concurrent.ConcurrentHashMap;
import j$.util.function.Consumer$CC;
import java.util.Comparator;
import java.util.function.Consumer;

final class StreamSpliterators$DistinctSpliterator implements Spliterator, Consumer {
    private static final Object NULL_VALUE = new Object();
    private final Spliterator s;
    private final ConcurrentHashMap seen;
    private Object tmpSlot;

    StreamSpliterators$DistinctSpliterator(Spliterator spliterator) {
        this(spliterator, new ConcurrentHashMap());
    }

    private StreamSpliterators$DistinctSpliterator(Spliterator spliterator, ConcurrentHashMap concurrentHashMap) {
        this.s = spliterator;
        this.seen = concurrentHashMap;
    }

    private Object mapNull(Object obj) {
        return obj != null ? obj : NULL_VALUE;
    }

    public void accept(Object obj) {
        this.tmpSlot = obj;
    }

    public /* synthetic */ Consumer andThen(Consumer consumer) {
        return Consumer$CC.$default$andThen(this, consumer);
    }

    public int characteristics() {
        return (this.s.characteristics() & -16469) | 1;
    }

    public long estimateSize() {
        return this.s.estimateSize();
    }

    public void forEachRemaining(Consumer consumer) {
        this.s.forEachRemaining(new StreamSpliterators$DistinctSpliterator$$ExternalSyntheticLambda0(this, consumer));
    }

    public Comparator getComparator() {
        return this.s.getComparator();
    }

    public /* synthetic */ long getExactSizeIfKnown() {
        return Spliterator.CC.$default$getExactSizeIfKnown(this);
    }

    public /* synthetic */ boolean hasCharacteristics(int i) {
        return Spliterator.CC.$default$hasCharacteristics(this, i);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$forEachRemaining$0$java-util-stream-StreamSpliterators$DistinctSpliterator  reason: not valid java name */
    public /* synthetic */ void m1331lambda$forEachRemaining$0$javautilstreamStreamSpliterators$DistinctSpliterator(Consumer consumer, Object obj) {
        if (this.seen.putIfAbsent(mapNull(obj), Boolean.TRUE) == null) {
            consumer.accept(obj);
        }
    }

    public boolean tryAdvance(Consumer consumer) {
        while (this.s.tryAdvance(this)) {
            if (this.seen.putIfAbsent(mapNull(this.tmpSlot), Boolean.TRUE) == null) {
                consumer.accept(this.tmpSlot);
                this.tmpSlot = null;
                return true;
            }
        }
        return false;
    }

    public Spliterator trySplit() {
        Spliterator trySplit = this.s.trySplit();
        if (trySplit != null) {
            return new StreamSpliterators$DistinctSpliterator(trySplit, this.seen);
        }
        return null;
    }
}
