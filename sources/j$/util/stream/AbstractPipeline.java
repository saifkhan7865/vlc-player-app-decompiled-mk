package j$.util.stream;

import j$.util.Objects;
import j$.util.Spliterator;
import j$.util.stream.Node;
import java.util.function.IntFunction;
import java.util.function.Supplier;

abstract class AbstractPipeline extends PipelineHelper implements BaseStream {
    private int combinedFlags;
    private int depth;
    private boolean linkedOrConsumed;
    private AbstractPipeline nextStage;
    private boolean parallel;
    private final AbstractPipeline previousStage;
    private boolean sourceAnyStateful;
    private Runnable sourceCloseAction;
    protected final int sourceOrOpFlags;
    private Spliterator sourceSpliterator;
    private final AbstractPipeline sourceStage;
    private Supplier sourceSupplier;

    static {
        Class<AbstractPipeline> cls = AbstractPipeline.class;
    }

    AbstractPipeline(Spliterator spliterator, int i, boolean z) {
        this.previousStage = null;
        this.sourceSpliterator = spliterator;
        this.sourceStage = this;
        int i2 = StreamOpFlag.STREAM_MASK & i;
        this.sourceOrOpFlags = i2;
        this.combinedFlags = ((i2 << 1) ^ -1) & StreamOpFlag.INITIAL_OPS_VALUE;
        this.depth = 0;
        this.parallel = z;
    }

    AbstractPipeline(AbstractPipeline abstractPipeline, int i) {
        if (!abstractPipeline.linkedOrConsumed) {
            abstractPipeline.linkedOrConsumed = true;
            abstractPipeline.nextStage = this;
            this.previousStage = abstractPipeline;
            this.sourceOrOpFlags = StreamOpFlag.OP_MASK & i;
            this.combinedFlags = StreamOpFlag.combineOpFlags(i, abstractPipeline.combinedFlags);
            AbstractPipeline abstractPipeline2 = abstractPipeline.sourceStage;
            this.sourceStage = abstractPipeline2;
            if (opIsStateful()) {
                abstractPipeline2.sourceAnyStateful = true;
            }
            this.depth = abstractPipeline.depth + 1;
            return;
        }
        throw new IllegalStateException("stream has already been operated upon or closed");
    }

    AbstractPipeline(Supplier supplier, int i, boolean z) {
        this.previousStage = null;
        this.sourceSupplier = supplier;
        this.sourceStage = this;
        int i2 = StreamOpFlag.STREAM_MASK & i;
        this.sourceOrOpFlags = i2;
        this.combinedFlags = ((i2 << 1) ^ -1) & StreamOpFlag.INITIAL_OPS_VALUE;
        this.depth = 0;
        this.parallel = z;
    }

    static /* synthetic */ Object[] lambda$opEvaluateParallelLazy$2(int i) {
        return new Object[i];
    }

    static /* synthetic */ Spliterator lambda$wrapSpliterator$1(Spliterator spliterator) {
        return spliterator;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v11, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v6, resolved type: j$.util.Spliterator} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private j$.util.Spliterator sourceSpliterator(int r8) {
        /*
            r7 = this;
            j$.util.stream.AbstractPipeline r0 = r7.sourceStage
            j$.util.Spliterator r1 = r0.sourceSpliterator
            r2 = 0
            if (r1 == 0) goto L_0x000a
            r0.sourceSpliterator = r2
            goto L_0x0019
        L_0x000a:
            java.util.function.Supplier r0 = r0.sourceSupplier
            if (r0 == 0) goto L_0x007c
            java.lang.Object r0 = r0.get()
            r1 = r0
            j$.util.Spliterator r1 = (j$.util.Spliterator) r1
            j$.util.stream.AbstractPipeline r0 = r7.sourceStage
            r0.sourceSupplier = r2
        L_0x0019:
            boolean r0 = r7.isParallel()
            if (r0 == 0) goto L_0x0071
            j$.util.stream.AbstractPipeline r0 = r7.sourceStage
            boolean r2 = r0.sourceAnyStateful
            if (r2 == 0) goto L_0x0071
            j$.util.stream.AbstractPipeline r2 = r0.nextStage
            r3 = 1
        L_0x0028:
            if (r0 == r7) goto L_0x0071
            int r4 = r2.sourceOrOpFlags
            boolean r5 = r2.opIsStateful()
            if (r5 == 0) goto L_0x005e
            j$.util.stream.StreamOpFlag r3 = j$.util.stream.StreamOpFlag.SHORT_CIRCUIT
            boolean r3 = r3.isKnown(r4)
            if (r3 == 0) goto L_0x003f
            int r3 = j$.util.stream.StreamOpFlag.IS_SHORT_CIRCUIT
            r3 = r3 ^ -1
            r4 = r4 & r3
        L_0x003f:
            j$.util.Spliterator r1 = r2.opEvaluateParallelLazy(r0, r1)
            r3 = 64
            boolean r3 = r1.hasCharacteristics(r3)
            if (r3 == 0) goto L_0x0055
            int r3 = j$.util.stream.StreamOpFlag.NOT_SIZED
            r3 = r3 ^ -1
            r3 = r3 & r4
            int r4 = j$.util.stream.StreamOpFlag.IS_SIZED
        L_0x0052:
            r3 = r3 | r4
            r4 = r3
            goto L_0x005d
        L_0x0055:
            int r3 = j$.util.stream.StreamOpFlag.IS_SIZED
            r3 = r3 ^ -1
            r3 = r3 & r4
            int r4 = j$.util.stream.StreamOpFlag.NOT_SIZED
            goto L_0x0052
        L_0x005d:
            r3 = 0
        L_0x005e:
            int r5 = r3 + 1
            r2.depth = r3
            int r0 = r0.combinedFlags
            int r0 = j$.util.stream.StreamOpFlag.combineOpFlags(r4, r0)
            r2.combinedFlags = r0
            j$.util.stream.AbstractPipeline r0 = r2.nextStage
            r3 = r5
            r6 = r2
            r2 = r0
            r0 = r6
            goto L_0x0028
        L_0x0071:
            if (r8 == 0) goto L_0x007b
            int r0 = r7.combinedFlags
            int r8 = j$.util.stream.StreamOpFlag.combineOpFlags(r8, r0)
            r7.combinedFlags = r8
        L_0x007b:
            return r1
        L_0x007c:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "source already consumed or closed"
            r8.<init>(r0)
            goto L_0x0085
        L_0x0084:
            throw r8
        L_0x0085:
            goto L_0x0084
        */
        throw new UnsupportedOperationException("Method not decompiled: j$.util.stream.AbstractPipeline.sourceSpliterator(int):j$.util.Spliterator");
    }

    public void close() {
        this.linkedOrConsumed = true;
        this.sourceSupplier = null;
        this.sourceSpliterator = null;
        AbstractPipeline abstractPipeline = this.sourceStage;
        Runnable runnable = abstractPipeline.sourceCloseAction;
        if (runnable != null) {
            abstractPipeline.sourceCloseAction = null;
            runnable.run();
        }
    }

    /* access modifiers changed from: package-private */
    public final void copyInto(Sink sink, Spliterator spliterator) {
        Objects.requireNonNull(sink);
        if (!StreamOpFlag.SHORT_CIRCUIT.isKnown(getStreamAndOpFlags())) {
            sink.begin(spliterator.getExactSizeIfKnown());
            spliterator.forEachRemaining(sink);
            sink.end();
            return;
        }
        copyIntoWithCancel(sink, spliterator);
    }

    /* access modifiers changed from: package-private */
    public final boolean copyIntoWithCancel(Sink sink, Spliterator spliterator) {
        AbstractPipeline abstractPipeline = this;
        while (abstractPipeline.depth > 0) {
            abstractPipeline = abstractPipeline.previousStage;
        }
        sink.begin(spliterator.getExactSizeIfKnown());
        boolean forEachWithCancel = abstractPipeline.forEachWithCancel(spliterator, sink);
        sink.end();
        return forEachWithCancel;
    }

    /* access modifiers changed from: package-private */
    public final Node evaluate(Spliterator spliterator, boolean z, IntFunction intFunction) {
        return isParallel() ? evaluateToNode(this, spliterator, z, intFunction) : ((Node.Builder) wrapAndCopyInto(makeNodeBuilder(exactOutputSizeIfKnown(spliterator), intFunction), spliterator)).build();
    }

    /* access modifiers changed from: package-private */
    public final Object evaluate(TerminalOp terminalOp) {
        if (!this.linkedOrConsumed) {
            this.linkedOrConsumed = true;
            return isParallel() ? terminalOp.evaluateParallel(this, sourceSpliterator(terminalOp.getOpFlags())) : terminalOp.evaluateSequential(this, sourceSpliterator(terminalOp.getOpFlags()));
        }
        throw new IllegalStateException("stream has already been operated upon or closed");
    }

    /* access modifiers changed from: package-private */
    public final Node evaluateToArrayNode(IntFunction intFunction) {
        if (!this.linkedOrConsumed) {
            this.linkedOrConsumed = true;
            if (!isParallel() || this.previousStage == null || !opIsStateful()) {
                return evaluate(sourceSpliterator(0), true, intFunction);
            }
            this.depth = 0;
            AbstractPipeline abstractPipeline = this.previousStage;
            return opEvaluateParallel(abstractPipeline, abstractPipeline.sourceSpliterator(0), intFunction);
        }
        throw new IllegalStateException("stream has already been operated upon or closed");
    }

    /* access modifiers changed from: package-private */
    public abstract Node evaluateToNode(PipelineHelper pipelineHelper, Spliterator spliterator, boolean z, IntFunction intFunction);

    /* access modifiers changed from: package-private */
    public final long exactOutputSizeIfKnown(Spliterator spliterator) {
        if (StreamOpFlag.SIZED.isKnown(getStreamAndOpFlags())) {
            return spliterator.getExactSizeIfKnown();
        }
        return -1;
    }

    /* access modifiers changed from: package-private */
    public abstract boolean forEachWithCancel(Spliterator spliterator, Sink sink);

    /* access modifiers changed from: package-private */
    public abstract StreamShape getOutputShape();

    /* access modifiers changed from: package-private */
    public final StreamShape getSourceShape() {
        AbstractPipeline abstractPipeline = this;
        while (abstractPipeline.depth > 0) {
            abstractPipeline = abstractPipeline.previousStage;
        }
        return abstractPipeline.getOutputShape();
    }

    /* access modifiers changed from: package-private */
    public final int getStreamAndOpFlags() {
        return this.combinedFlags;
    }

    /* access modifiers changed from: package-private */
    public final boolean isOrdered() {
        return StreamOpFlag.ORDERED.isKnown(this.combinedFlags);
    }

    public final boolean isParallel() {
        return this.sourceStage.parallel;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$spliterator$0$java-util-stream-AbstractPipeline  reason: not valid java name */
    public /* synthetic */ Spliterator m1318lambda$spliterator$0$javautilstreamAbstractPipeline() {
        return sourceSpliterator(0);
    }

    /* access modifiers changed from: package-private */
    public abstract Spliterator lazySpliterator(Supplier supplier);

    /* access modifiers changed from: package-private */
    public abstract Node.Builder makeNodeBuilder(long j, IntFunction intFunction);

    public BaseStream onClose(Runnable runnable) {
        if (!this.linkedOrConsumed) {
            Objects.requireNonNull(runnable);
            AbstractPipeline abstractPipeline = this.sourceStage;
            Runnable runnable2 = abstractPipeline.sourceCloseAction;
            if (runnable2 != null) {
                runnable = Streams.composeWithExceptions(runnable2, runnable);
            }
            abstractPipeline.sourceCloseAction = runnable;
            return this;
        }
        throw new IllegalStateException("stream has already been operated upon or closed");
    }

    /* access modifiers changed from: package-private */
    public Node opEvaluateParallel(PipelineHelper pipelineHelper, Spliterator spliterator, IntFunction intFunction) {
        throw new UnsupportedOperationException("Parallel evaluation is not supported");
    }

    /* access modifiers changed from: package-private */
    public Spliterator opEvaluateParallelLazy(PipelineHelper pipelineHelper, Spliterator spliterator) {
        return opEvaluateParallel(pipelineHelper, spliterator, new AbstractPipeline$$ExternalSyntheticLambda2()).spliterator();
    }

    /* access modifiers changed from: package-private */
    public abstract boolean opIsStateful();

    /* access modifiers changed from: package-private */
    public abstract Sink opWrapSink(int i, Sink sink);

    public final BaseStream parallel() {
        this.sourceStage.parallel = true;
        return this;
    }

    public final BaseStream sequential() {
        this.sourceStage.parallel = false;
        return this;
    }

    /* access modifiers changed from: package-private */
    public final Spliterator sourceStageSpliterator() {
        AbstractPipeline abstractPipeline = this.sourceStage;
        if (this != abstractPipeline) {
            throw new IllegalStateException();
        } else if (!this.linkedOrConsumed) {
            this.linkedOrConsumed = true;
            Spliterator spliterator = abstractPipeline.sourceSpliterator;
            if (spliterator != null) {
                abstractPipeline.sourceSpliterator = null;
                return spliterator;
            }
            Supplier supplier = abstractPipeline.sourceSupplier;
            if (supplier != null) {
                Spliterator spliterator2 = (Spliterator) supplier.get();
                this.sourceStage.sourceSupplier = null;
                return spliterator2;
            }
            throw new IllegalStateException("source already consumed or closed");
        } else {
            throw new IllegalStateException("stream has already been operated upon or closed");
        }
    }

    public Spliterator spliterator() {
        if (!this.linkedOrConsumed) {
            this.linkedOrConsumed = true;
            AbstractPipeline abstractPipeline = this.sourceStage;
            if (this != abstractPipeline) {
                return wrap(this, new AbstractPipeline$$ExternalSyntheticLambda0(this), isParallel());
            }
            Spliterator spliterator = abstractPipeline.sourceSpliterator;
            if (spliterator != null) {
                abstractPipeline.sourceSpliterator = null;
                return spliterator;
            }
            Supplier supplier = abstractPipeline.sourceSupplier;
            if (supplier != null) {
                abstractPipeline.sourceSupplier = null;
                return lazySpliterator(supplier);
            }
            throw new IllegalStateException("source already consumed or closed");
        }
        throw new IllegalStateException("stream has already been operated upon or closed");
    }

    /* access modifiers changed from: package-private */
    public abstract Spliterator wrap(PipelineHelper pipelineHelper, Supplier supplier, boolean z);

    /* access modifiers changed from: package-private */
    public final Sink wrapAndCopyInto(Sink sink, Spliterator spliterator) {
        copyInto(wrapSink((Sink) Objects.requireNonNull(sink)), spliterator);
        return sink;
    }

    /* access modifiers changed from: package-private */
    public final Sink wrapSink(Sink sink) {
        Objects.requireNonNull(sink);
        for (AbstractPipeline abstractPipeline = this; abstractPipeline.depth > 0; abstractPipeline = abstractPipeline.previousStage) {
            sink = abstractPipeline.opWrapSink(abstractPipeline.previousStage.combinedFlags, sink);
        }
        return sink;
    }

    /* access modifiers changed from: package-private */
    public final Spliterator wrapSpliterator(Spliterator spliterator) {
        return this.depth == 0 ? spliterator : wrap(this, new AbstractPipeline$$ExternalSyntheticLambda1(spliterator), isParallel());
    }
}
