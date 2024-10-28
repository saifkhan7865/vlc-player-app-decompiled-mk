package j$.util.stream;

import j$.util.Spliterator;
import j$.util.stream.Node;
import java.util.function.IntFunction;

abstract class PipelineHelper {
    PipelineHelper() {
    }

    /* access modifiers changed from: package-private */
    public abstract void copyInto(Sink sink, Spliterator spliterator);

    /* access modifiers changed from: package-private */
    public abstract boolean copyIntoWithCancel(Sink sink, Spliterator spliterator);

    /* access modifiers changed from: package-private */
    public abstract Node evaluate(Spliterator spliterator, boolean z, IntFunction intFunction);

    /* access modifiers changed from: package-private */
    public abstract long exactOutputSizeIfKnown(Spliterator spliterator);

    /* access modifiers changed from: package-private */
    public abstract StreamShape getSourceShape();

    /* access modifiers changed from: package-private */
    public abstract int getStreamAndOpFlags();

    /* access modifiers changed from: package-private */
    public abstract Node.Builder makeNodeBuilder(long j, IntFunction intFunction);

    /* access modifiers changed from: package-private */
    public abstract Sink wrapAndCopyInto(Sink sink, Spliterator spliterator);

    /* access modifiers changed from: package-private */
    public abstract Sink wrapSink(Sink sink);

    /* access modifiers changed from: package-private */
    public abstract Spliterator wrapSpliterator(Spliterator spliterator);
}
