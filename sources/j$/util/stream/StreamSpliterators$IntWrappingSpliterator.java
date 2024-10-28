package j$.util.stream;

import j$.util.Objects;
import j$.util.Spliterator;
import j$.util.stream.SpinedBuffer;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.function.Supplier;

final class StreamSpliterators$IntWrappingSpliterator extends StreamSpliterators$AbstractWrappingSpliterator implements Spliterator.OfInt {
    StreamSpliterators$IntWrappingSpliterator(PipelineHelper pipelineHelper, Spliterator spliterator, boolean z) {
        super(pipelineHelper, spliterator, z);
    }

    StreamSpliterators$IntWrappingSpliterator(PipelineHelper pipelineHelper, Supplier supplier, boolean z) {
        super(pipelineHelper, supplier, z);
    }

    public /* synthetic */ void forEachRemaining(Consumer consumer) {
        Spliterator.OfInt.CC.$default$forEachRemaining(this, consumer);
    }

    public void forEachRemaining(IntConsumer intConsumer) {
        if (this.buffer != null || this.finished) {
            do {
            } while (tryAdvance(intConsumer));
            return;
        }
        Objects.requireNonNull(intConsumer);
        init();
        PipelineHelper pipelineHelper = this.ph;
        Objects.requireNonNull(intConsumer);
        pipelineHelper.wrapAndCopyInto(new StreamSpliterators$IntWrappingSpliterator$$ExternalSyntheticLambda0(intConsumer), this.spliterator);
        this.finished = true;
    }

    /* access modifiers changed from: package-private */
    public void initPartialTraversalState() {
        SpinedBuffer.OfInt ofInt = new SpinedBuffer.OfInt();
        this.buffer = ofInt;
        PipelineHelper pipelineHelper = this.ph;
        Objects.requireNonNull(ofInt);
        this.bufferSink = pipelineHelper.wrapSink(new StreamSpliterators$IntWrappingSpliterator$$ExternalSyntheticLambda1(ofInt));
        this.pusher = new StreamSpliterators$IntWrappingSpliterator$$ExternalSyntheticLambda2(this);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initPartialTraversalState$0$java-util-stream-StreamSpliterators$IntWrappingSpliterator  reason: not valid java name */
    public /* synthetic */ boolean m1333lambda$initPartialTraversalState$0$javautilstreamStreamSpliterators$IntWrappingSpliterator() {
        return this.spliterator.tryAdvance(this.bufferSink);
    }

    public /* synthetic */ boolean tryAdvance(Consumer consumer) {
        return Spliterator.OfInt.CC.$default$tryAdvance(this, consumer);
    }

    public boolean tryAdvance(IntConsumer intConsumer) {
        Objects.requireNonNull(intConsumer);
        boolean doAdvance = doAdvance();
        if (doAdvance) {
            intConsumer.accept(((SpinedBuffer.OfInt) this.buffer).get(this.nextToConsume));
        }
        return doAdvance;
    }

    public Spliterator.OfInt trySplit() {
        return (Spliterator.OfInt) super.trySplit();
    }

    /* access modifiers changed from: package-private */
    public StreamSpliterators$AbstractWrappingSpliterator wrap(Spliterator spliterator) {
        return new StreamSpliterators$IntWrappingSpliterator(this.ph, spliterator, this.isParallel);
    }
}
