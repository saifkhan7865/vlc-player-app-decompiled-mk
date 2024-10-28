package j$.util.stream;

import j$.util.Objects;
import j$.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Supplier;

final class StreamSpliterators$WrappingSpliterator extends StreamSpliterators$AbstractWrappingSpliterator {
    StreamSpliterators$WrappingSpliterator(PipelineHelper pipelineHelper, Spliterator spliterator, boolean z) {
        super(pipelineHelper, spliterator, z);
    }

    StreamSpliterators$WrappingSpliterator(PipelineHelper pipelineHelper, Supplier supplier, boolean z) {
        super(pipelineHelper, supplier, z);
    }

    public void forEachRemaining(Consumer consumer) {
        if (this.buffer != null || this.finished) {
            do {
            } while (tryAdvance(consumer));
            return;
        }
        Objects.requireNonNull(consumer);
        init();
        PipelineHelper pipelineHelper = this.ph;
        Objects.requireNonNull(consumer);
        pipelineHelper.wrapAndCopyInto(new StreamSpliterators$WrappingSpliterator$$ExternalSyntheticLambda2(consumer), this.spliterator);
        this.finished = true;
    }

    /* access modifiers changed from: package-private */
    public void initPartialTraversalState() {
        SpinedBuffer spinedBuffer = new SpinedBuffer();
        this.buffer = spinedBuffer;
        PipelineHelper pipelineHelper = this.ph;
        Objects.requireNonNull(spinedBuffer);
        this.bufferSink = pipelineHelper.wrapSink(new StreamSpliterators$WrappingSpliterator$$ExternalSyntheticLambda0(spinedBuffer));
        this.pusher = new StreamSpliterators$WrappingSpliterator$$ExternalSyntheticLambda1(this);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initPartialTraversalState$0$java-util-stream-StreamSpliterators$WrappingSpliterator  reason: not valid java name */
    public /* synthetic */ boolean m1335lambda$initPartialTraversalState$0$javautilstreamStreamSpliterators$WrappingSpliterator() {
        return this.spliterator.tryAdvance(this.bufferSink);
    }

    public boolean tryAdvance(Consumer consumer) {
        Objects.requireNonNull(consumer);
        boolean doAdvance = doAdvance();
        if (doAdvance) {
            consumer.accept(((SpinedBuffer) this.buffer).get(this.nextToConsume));
        }
        return doAdvance;
    }

    /* access modifiers changed from: package-private */
    public StreamSpliterators$WrappingSpliterator wrap(Spliterator spliterator) {
        return new StreamSpliterators$WrappingSpliterator(this.ph, spliterator, this.isParallel);
    }
}
