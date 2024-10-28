package j$.util.stream;

import j$.util.Spliterator;
import java.util.Comparator;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

abstract class StreamSpliterators$AbstractWrappingSpliterator implements Spliterator {
    AbstractSpinedBuffer buffer;
    Sink bufferSink;
    boolean finished;
    final boolean isParallel;
    long nextToConsume;
    final PipelineHelper ph;
    BooleanSupplier pusher;
    Spliterator spliterator;
    private Supplier spliteratorSupplier;

    StreamSpliterators$AbstractWrappingSpliterator(PipelineHelper pipelineHelper, Spliterator spliterator2, boolean z) {
        this.ph = pipelineHelper;
        this.spliteratorSupplier = null;
        this.spliterator = spliterator2;
        this.isParallel = z;
    }

    StreamSpliterators$AbstractWrappingSpliterator(PipelineHelper pipelineHelper, Supplier supplier, boolean z) {
        this.ph = pipelineHelper;
        this.spliteratorSupplier = supplier;
        this.spliterator = null;
        this.isParallel = z;
    }

    private boolean fillBuffer() {
        while (this.buffer.count() == 0) {
            if (this.bufferSink.cancellationRequested() || !this.pusher.getAsBoolean()) {
                if (this.finished) {
                    return false;
                }
                this.bufferSink.end();
                this.finished = true;
            }
        }
        return true;
    }

    public final int characteristics() {
        init();
        int characteristics = StreamOpFlag.toCharacteristics(StreamOpFlag.toStreamFlags(this.ph.getStreamAndOpFlags()));
        return (characteristics & 64) != 0 ? (characteristics & -16449) | (this.spliterator.characteristics() & 16448) : characteristics;
    }

    /* access modifiers changed from: package-private */
    public final boolean doAdvance() {
        AbstractSpinedBuffer abstractSpinedBuffer = this.buffer;
        boolean z = false;
        if (abstractSpinedBuffer != null) {
            long j = this.nextToConsume + 1;
            this.nextToConsume = j;
            if (j < abstractSpinedBuffer.count()) {
                z = true;
            }
            if (z) {
                return z;
            }
            this.nextToConsume = 0;
            this.buffer.clear();
            return fillBuffer();
        } else if (this.finished) {
            return false;
        } else {
            init();
            initPartialTraversalState();
            this.nextToConsume = 0;
            this.bufferSink.begin(this.spliterator.getExactSizeIfKnown());
            return fillBuffer();
        }
    }

    public final long estimateSize() {
        init();
        return this.spliterator.estimateSize();
    }

    public Comparator getComparator() {
        if (hasCharacteristics(4)) {
            return null;
        }
        throw new IllegalStateException();
    }

    public final long getExactSizeIfKnown() {
        init();
        if (StreamOpFlag.SIZED.isKnown(this.ph.getStreamAndOpFlags())) {
            return this.spliterator.getExactSizeIfKnown();
        }
        return -1;
    }

    public /* synthetic */ boolean hasCharacteristics(int i) {
        return Spliterator.CC.$default$hasCharacteristics(this, i);
    }

    /* access modifiers changed from: package-private */
    public final void init() {
        if (this.spliterator == null) {
            this.spliterator = (Spliterator) this.spliteratorSupplier.get();
            this.spliteratorSupplier = null;
        }
    }

    /* access modifiers changed from: package-private */
    public abstract void initPartialTraversalState();

    public final String toString() {
        return String.format("%s[%s]", new Object[]{getClass().getName(), this.spliterator});
    }

    public Spliterator trySplit() {
        if (!this.isParallel || this.buffer != null || this.finished) {
            return null;
        }
        init();
        Spliterator trySplit = this.spliterator.trySplit();
        if (trySplit == null) {
            return null;
        }
        return wrap(trySplit);
    }

    /* access modifiers changed from: package-private */
    public abstract StreamSpliterators$AbstractWrappingSpliterator wrap(Spliterator spliterator2);
}
