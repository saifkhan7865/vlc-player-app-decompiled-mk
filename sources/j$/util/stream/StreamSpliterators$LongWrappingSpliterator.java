package j$.util.stream;

import j$.util.Objects;
import j$.util.Spliterator;
import j$.util.stream.SpinedBuffer;
import java.util.function.Consumer;
import java.util.function.LongConsumer;
import java.util.function.Supplier;

final class StreamSpliterators$LongWrappingSpliterator extends StreamSpliterators$AbstractWrappingSpliterator implements Spliterator.OfLong {
    StreamSpliterators$LongWrappingSpliterator(PipelineHelper pipelineHelper, Spliterator spliterator, boolean z) {
        super(pipelineHelper, spliterator, z);
    }

    StreamSpliterators$LongWrappingSpliterator(PipelineHelper pipelineHelper, Supplier supplier, boolean z) {
        super(pipelineHelper, supplier, z);
    }

    public /* synthetic */ void forEachRemaining(Consumer consumer) {
        Spliterator.OfLong.CC.$default$forEachRemaining(this, consumer);
    }

    public void forEachRemaining(LongConsumer longConsumer) {
        if (this.buffer != null || this.finished) {
            do {
            } while (tryAdvance(longConsumer));
            return;
        }
        Objects.requireNonNull(longConsumer);
        init();
        PipelineHelper pipelineHelper = this.ph;
        Objects.requireNonNull(longConsumer);
        pipelineHelper.wrapAndCopyInto(new StreamSpliterators$LongWrappingSpliterator$$ExternalSyntheticLambda0(longConsumer), this.spliterator);
        this.finished = true;
    }

    /* access modifiers changed from: package-private */
    public void initPartialTraversalState() {
        SpinedBuffer.OfLong ofLong = new SpinedBuffer.OfLong();
        this.buffer = ofLong;
        PipelineHelper pipelineHelper = this.ph;
        Objects.requireNonNull(ofLong);
        this.bufferSink = pipelineHelper.wrapSink(new StreamSpliterators$LongWrappingSpliterator$$ExternalSyntheticLambda1(ofLong));
        this.pusher = new StreamSpliterators$LongWrappingSpliterator$$ExternalSyntheticLambda2(this);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initPartialTraversalState$0$java-util-stream-StreamSpliterators$LongWrappingSpliterator  reason: not valid java name */
    public /* synthetic */ boolean m1334lambda$initPartialTraversalState$0$javautilstreamStreamSpliterators$LongWrappingSpliterator() {
        return this.spliterator.tryAdvance(this.bufferSink);
    }

    public /* synthetic */ boolean tryAdvance(Consumer consumer) {
        return Spliterator.OfLong.CC.$default$tryAdvance(this, consumer);
    }

    public boolean tryAdvance(LongConsumer longConsumer) {
        Objects.requireNonNull(longConsumer);
        boolean doAdvance = doAdvance();
        if (doAdvance) {
            longConsumer.accept(((SpinedBuffer.OfLong) this.buffer).get(this.nextToConsume));
        }
        return doAdvance;
    }

    public Spliterator.OfLong trySplit() {
        return (Spliterator.OfLong) super.trySplit();
    }

    /* access modifiers changed from: package-private */
    public StreamSpliterators$AbstractWrappingSpliterator wrap(Spliterator spliterator) {
        return new StreamSpliterators$LongWrappingSpliterator(this.ph, spliterator, this.isParallel);
    }
}
