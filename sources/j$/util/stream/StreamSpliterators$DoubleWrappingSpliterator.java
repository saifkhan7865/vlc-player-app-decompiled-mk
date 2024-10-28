package j$.util.stream;

import j$.util.Objects;
import j$.util.Spliterator;
import j$.util.stream.SpinedBuffer;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.Supplier;

final class StreamSpliterators$DoubleWrappingSpliterator extends StreamSpliterators$AbstractWrappingSpliterator implements Spliterator.OfDouble {
    StreamSpliterators$DoubleWrappingSpliterator(PipelineHelper pipelineHelper, Spliterator spliterator, boolean z) {
        super(pipelineHelper, spliterator, z);
    }

    StreamSpliterators$DoubleWrappingSpliterator(PipelineHelper pipelineHelper, Supplier supplier, boolean z) {
        super(pipelineHelper, supplier, z);
    }

    public /* synthetic */ void forEachRemaining(Consumer consumer) {
        Spliterator.OfDouble.CC.$default$forEachRemaining(this, consumer);
    }

    public void forEachRemaining(DoubleConsumer doubleConsumer) {
        if (this.buffer != null || this.finished) {
            do {
            } while (tryAdvance(doubleConsumer));
            return;
        }
        Objects.requireNonNull(doubleConsumer);
        init();
        PipelineHelper pipelineHelper = this.ph;
        Objects.requireNonNull(doubleConsumer);
        pipelineHelper.wrapAndCopyInto(new StreamSpliterators$DoubleWrappingSpliterator$$ExternalSyntheticLambda0(doubleConsumer), this.spliterator);
        this.finished = true;
    }

    /* access modifiers changed from: package-private */
    public void initPartialTraversalState() {
        SpinedBuffer.OfDouble ofDouble = new SpinedBuffer.OfDouble();
        this.buffer = ofDouble;
        PipelineHelper pipelineHelper = this.ph;
        Objects.requireNonNull(ofDouble);
        this.bufferSink = pipelineHelper.wrapSink(new StreamSpliterators$DoubleWrappingSpliterator$$ExternalSyntheticLambda1(ofDouble));
        this.pusher = new StreamSpliterators$DoubleWrappingSpliterator$$ExternalSyntheticLambda2(this);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initPartialTraversalState$0$java-util-stream-StreamSpliterators$DoubleWrappingSpliterator  reason: not valid java name */
    public /* synthetic */ boolean m1332lambda$initPartialTraversalState$0$javautilstreamStreamSpliterators$DoubleWrappingSpliterator() {
        return this.spliterator.tryAdvance(this.bufferSink);
    }

    public /* synthetic */ boolean tryAdvance(Consumer consumer) {
        return Spliterator.OfDouble.CC.$default$tryAdvance(this, consumer);
    }

    public boolean tryAdvance(DoubleConsumer doubleConsumer) {
        Objects.requireNonNull(doubleConsumer);
        boolean doAdvance = doAdvance();
        if (doAdvance) {
            doubleConsumer.accept(((SpinedBuffer.OfDouble) this.buffer).get(this.nextToConsume));
        }
        return doAdvance;
    }

    public Spliterator.OfDouble trySplit() {
        return (Spliterator.OfDouble) super.trySplit();
    }

    /* access modifiers changed from: package-private */
    public StreamSpliterators$AbstractWrappingSpliterator wrap(Spliterator spliterator) {
        return new StreamSpliterators$DoubleWrappingSpliterator(this.ph, spliterator, this.isParallel);
    }
}
