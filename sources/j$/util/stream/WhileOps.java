package j$.util.stream;

import j$.util.Objects;
import j$.util.Spliterator;
import j$.util.function.Consumer$CC;
import j$.util.stream.Node;
import j$.util.stream.ReferencePipeline;
import j$.util.stream.Sink;
import java.util.Comparator;
import java.util.concurrent.CountedCompleter;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;

abstract class WhileOps {
    static final int DROP_FLAGS;
    static final int TAKE_FLAGS;

    interface DropWhileOp {
        DropWhileSink opWrapSink(Sink sink, boolean z);
    }

    interface DropWhileSink extends Sink {
        long getDropCount();
    }

    private static final class DropWhileTask extends AbstractTask {
        private final IntFunction generator;
        private long index;
        private final boolean isOrdered;
        private final AbstractPipeline op;
        private long thisNodeSize;

        DropWhileTask(AbstractPipeline abstractPipeline, PipelineHelper pipelineHelper, Spliterator spliterator, IntFunction intFunction) {
            super(pipelineHelper, spliterator);
            this.op = abstractPipeline;
            this.generator = intFunction;
            this.isOrdered = StreamOpFlag.ORDERED.isKnown(pipelineHelper.getStreamAndOpFlags());
        }

        DropWhileTask(DropWhileTask dropWhileTask, Spliterator spliterator) {
            super((AbstractTask) dropWhileTask, spliterator);
            this.op = dropWhileTask.op;
            this.generator = dropWhileTask.generator;
            this.isOrdered = dropWhileTask.isOrdered;
        }

        private Node doTruncate(Node node) {
            if (!this.isOrdered) {
                return node;
            }
            return node.truncate(this.index, node.count(), this.generator);
        }

        private Node merge() {
            AbstractTask abstractTask = this.leftChild;
            return ((DropWhileTask) abstractTask).thisNodeSize == 0 ? (Node) ((DropWhileTask) this.rightChild).getLocalResult() : ((DropWhileTask) this.rightChild).thisNodeSize == 0 ? (Node) ((DropWhileTask) abstractTask).getLocalResult() : Nodes.conc(this.op.getOutputShape(), (Node) ((DropWhileTask) this.leftChild).getLocalResult(), (Node) ((DropWhileTask) this.rightChild).getLocalResult());
        }

        /* access modifiers changed from: protected */
        public final Node doLeaf() {
            boolean z = true;
            boolean z2 = !isRoot();
            Node.Builder makeNodeBuilder = this.helper.makeNodeBuilder((!z2 || !this.isOrdered || !StreamOpFlag.SIZED.isPreserved(this.op.sourceOrOpFlags)) ? -1 : this.op.exactOutputSizeIfKnown(this.spliterator), this.generator);
            DropWhileOp dropWhileOp = (DropWhileOp) this.op;
            if (!this.isOrdered || !z2) {
                z = false;
            }
            DropWhileSink opWrapSink = dropWhileOp.opWrapSink(makeNodeBuilder, z);
            this.helper.wrapAndCopyInto(opWrapSink, this.spliterator);
            Node build = makeNodeBuilder.build();
            this.thisNodeSize = build.count();
            this.index = opWrapSink.getDropCount();
            return build;
        }

        /* access modifiers changed from: protected */
        public DropWhileTask makeChild(Spliterator spliterator) {
            return new DropWhileTask(this, spliterator);
        }

        public final void onCompletion(CountedCompleter countedCompleter) {
            if (!isLeaf()) {
                if (this.isOrdered) {
                    AbstractTask abstractTask = this.leftChild;
                    long j = ((DropWhileTask) abstractTask).index;
                    this.index = j;
                    if (j == ((DropWhileTask) abstractTask).thisNodeSize) {
                        this.index = j + ((DropWhileTask) this.rightChild).index;
                    }
                }
                this.thisNodeSize = ((DropWhileTask) this.leftChild).thisNodeSize + ((DropWhileTask) this.rightChild).thisNodeSize;
                Node merge = merge();
                if (isRoot()) {
                    merge = doTruncate(merge);
                }
                setLocalResult(merge);
            }
            super.onCompletion(countedCompleter);
        }
    }

    private static final class TakeWhileTask extends AbstractShortCircuitTask {
        private volatile boolean completed;
        private final IntFunction generator;
        private final boolean isOrdered;
        private final AbstractPipeline op;
        private boolean shortCircuited;
        private long thisNodeSize;

        TakeWhileTask(AbstractPipeline abstractPipeline, PipelineHelper pipelineHelper, Spliterator spliterator, IntFunction intFunction) {
            super(pipelineHelper, spliterator);
            this.op = abstractPipeline;
            this.generator = intFunction;
            this.isOrdered = StreamOpFlag.ORDERED.isKnown(pipelineHelper.getStreamAndOpFlags());
        }

        TakeWhileTask(TakeWhileTask takeWhileTask, Spliterator spliterator) {
            super((AbstractShortCircuitTask) takeWhileTask, spliterator);
            this.op = takeWhileTask.op;
            this.generator = takeWhileTask.generator;
            this.isOrdered = takeWhileTask.isOrdered;
        }

        /* access modifiers changed from: protected */
        public void cancel() {
            super.cancel();
            if (this.isOrdered && this.completed) {
                setLocalResult(getEmptyResult());
            }
        }

        /* access modifiers changed from: protected */
        public final Node doLeaf() {
            Node.Builder makeNodeBuilder = this.helper.makeNodeBuilder(-1, this.generator);
            Sink opWrapSink = this.op.opWrapSink(this.helper.getStreamAndOpFlags(), makeNodeBuilder);
            PipelineHelper pipelineHelper = this.helper;
            boolean copyIntoWithCancel = pipelineHelper.copyIntoWithCancel(pipelineHelper.wrapSink(opWrapSink), this.spliterator);
            this.shortCircuited = copyIntoWithCancel;
            if (copyIntoWithCancel) {
                cancelLaterNodes();
            }
            Node build = makeNodeBuilder.build();
            this.thisNodeSize = build.count();
            return build;
        }

        /* access modifiers changed from: protected */
        public final Node getEmptyResult() {
            return Nodes.emptyNode(this.op.getOutputShape());
        }

        /* access modifiers changed from: protected */
        public TakeWhileTask makeChild(Spliterator spliterator) {
            return new TakeWhileTask(this, spliterator);
        }

        /* access modifiers changed from: package-private */
        public Node merge() {
            AbstractTask abstractTask = this.leftChild;
            return ((TakeWhileTask) abstractTask).thisNodeSize == 0 ? (Node) ((TakeWhileTask) this.rightChild).getLocalResult() : ((TakeWhileTask) this.rightChild).thisNodeSize == 0 ? (Node) ((TakeWhileTask) abstractTask).getLocalResult() : Nodes.conc(this.op.getOutputShape(), (Node) ((TakeWhileTask) this.leftChild).getLocalResult(), (Node) ((TakeWhileTask) this.rightChild).getLocalResult());
        }

        public final void onCompletion(CountedCompleter countedCompleter) {
            Node node;
            if (!isLeaf()) {
                this.shortCircuited = ((TakeWhileTask) this.leftChild).shortCircuited | ((TakeWhileTask) this.rightChild).shortCircuited;
                if (!this.isOrdered || !this.canceled) {
                    if (this.isOrdered) {
                        AbstractTask abstractTask = this.leftChild;
                        if (((TakeWhileTask) abstractTask).shortCircuited) {
                            this.thisNodeSize = ((TakeWhileTask) abstractTask).thisNodeSize;
                            node = (Node) ((TakeWhileTask) abstractTask).getLocalResult();
                        }
                    }
                    this.thisNodeSize = ((TakeWhileTask) this.leftChild).thisNodeSize + ((TakeWhileTask) this.rightChild).thisNodeSize;
                    node = merge();
                } else {
                    this.thisNodeSize = 0;
                    node = getEmptyResult();
                }
                setLocalResult(node);
            }
            this.completed = true;
            super.onCompletion(countedCompleter);
        }
    }

    static abstract class UnorderedWhileSpliterator implements Spliterator {
        final AtomicBoolean cancel;
        int count;
        final boolean noSplitting;
        final Spliterator s;
        boolean takeOrDrop = true;

        static abstract class OfRef extends UnorderedWhileSpliterator implements Consumer {
            final Predicate p;
            Object t;

            static final class Dropping extends OfRef {
                Dropping(Spliterator spliterator, Dropping dropping) {
                    super(spliterator, dropping);
                }

                Dropping(Spliterator spliterator, boolean z, Predicate predicate) {
                    super(spliterator, z, predicate);
                }

                /* access modifiers changed from: package-private */
                public Spliterator makeSpliterator(Spliterator spliterator) {
                    return new Dropping(spliterator, this);
                }

                /* JADX WARNING: Removed duplicated region for block: B:11:0x0024  */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public boolean tryAdvance(java.util.function.Consumer r6) {
                    /*
                        r5 = this;
                        boolean r0 = r5.takeOrDrop
                        if (r0 == 0) goto L_0x0031
                        r0 = 0
                        r5.takeOrDrop = r0
                    L_0x0007:
                        j$.util.Spliterator r1 = r5.s
                        boolean r1 = r1.tryAdvance(r5)
                        r2 = 1
                        if (r1 == 0) goto L_0x0022
                        boolean r3 = r5.checkCancelOnCount()
                        if (r3 == 0) goto L_0x0022
                        java.util.function.Predicate r3 = r5.p
                        java.lang.Object r4 = r5.t
                        boolean r3 = r3.test(r4)
                        if (r3 == 0) goto L_0x0022
                        r0 = 1
                        goto L_0x0007
                    L_0x0022:
                        if (r1 == 0) goto L_0x0030
                        if (r0 == 0) goto L_0x002b
                        java.util.concurrent.atomic.AtomicBoolean r0 = r5.cancel
                        r0.set(r2)
                    L_0x002b:
                        java.lang.Object r0 = r5.t
                        r6.accept(r0)
                    L_0x0030:
                        return r1
                    L_0x0031:
                        j$.util.Spliterator r0 = r5.s
                        boolean r6 = r0.tryAdvance(r6)
                        return r6
                    */
                    throw new UnsupportedOperationException("Method not decompiled: j$.util.stream.WhileOps.UnorderedWhileSpliterator.OfRef.Dropping.tryAdvance(java.util.function.Consumer):boolean");
                }
            }

            static final class Taking extends OfRef {
                Taking(Spliterator spliterator, Taking taking) {
                    super(spliterator, taking);
                }

                Taking(Spliterator spliterator, boolean z, Predicate predicate) {
                    super(spliterator, z, predicate);
                }

                /* access modifiers changed from: package-private */
                public Spliterator makeSpliterator(Spliterator spliterator) {
                    return new Taking(spliterator, this);
                }

                public boolean tryAdvance(Consumer consumer) {
                    boolean z;
                    if (!this.takeOrDrop || !checkCancelOnCount() || !this.s.tryAdvance(this)) {
                        z = true;
                    } else {
                        z = this.p.test(this.t);
                        if (z) {
                            consumer.accept(this.t);
                            return true;
                        }
                    }
                    this.takeOrDrop = false;
                    if (!z) {
                        this.cancel.set(true);
                    }
                    return false;
                }

                public Spliterator trySplit() {
                    if (this.cancel.get()) {
                        return null;
                    }
                    return super.trySplit();
                }
            }

            OfRef(Spliterator spliterator, OfRef ofRef) {
                super(spliterator, (UnorderedWhileSpliterator) ofRef);
                this.p = ofRef.p;
            }

            OfRef(Spliterator spliterator, boolean z, Predicate predicate) {
                super(spliterator, z);
                this.p = predicate;
            }

            public void accept(Object obj) {
                this.count = (this.count + 1) & 63;
                this.t = obj;
            }

            public /* synthetic */ Consumer andThen(Consumer consumer) {
                return Consumer$CC.$default$andThen(this, consumer);
            }
        }

        UnorderedWhileSpliterator(Spliterator spliterator, UnorderedWhileSpliterator unorderedWhileSpliterator) {
            this.s = spliterator;
            this.noSplitting = unorderedWhileSpliterator.noSplitting;
            this.cancel = unorderedWhileSpliterator.cancel;
        }

        UnorderedWhileSpliterator(Spliterator spliterator, boolean z) {
            this.s = spliterator;
            this.noSplitting = z;
            this.cancel = new AtomicBoolean();
        }

        public int characteristics() {
            return this.s.characteristics() & -16449;
        }

        /* access modifiers changed from: package-private */
        public boolean checkCancelOnCount() {
            return this.count != 0 || !this.cancel.get();
        }

        public long estimateSize() {
            return this.s.estimateSize();
        }

        public /* synthetic */ void forEachRemaining(Consumer consumer) {
            Spliterator.CC.$default$forEachRemaining(this, consumer);
        }

        public Comparator getComparator() {
            return this.s.getComparator();
        }

        public long getExactSizeIfKnown() {
            return -1;
        }

        public /* synthetic */ boolean hasCharacteristics(int i) {
            return Spliterator.CC.$default$hasCharacteristics(this, i);
        }

        /* access modifiers changed from: package-private */
        public abstract Spliterator makeSpliterator(Spliterator spliterator);

        public Spliterator trySplit() {
            Spliterator trySplit = this.noSplitting ? null : this.s.trySplit();
            if (trySplit != null) {
                return makeSpliterator(trySplit);
            }
            return null;
        }
    }

    static {
        int i = StreamOpFlag.NOT_SIZED;
        TAKE_FLAGS = StreamOpFlag.IS_SHORT_CIRCUIT | i;
        DROP_FLAGS = i;
    }

    static Stream makeDropWhileRef(AbstractPipeline abstractPipeline, final Predicate predicate) {
        Objects.requireNonNull(predicate);
        return new DropWhileOp(abstractPipeline, StreamShape.REFERENCE, DROP_FLAGS) {
            /* access modifiers changed from: package-private */
            public Node opEvaluateParallel(PipelineHelper pipelineHelper, Spliterator spliterator, IntFunction intFunction) {
                return (Node) new DropWhileTask(this, pipelineHelper, spliterator, intFunction).invoke();
            }

            /* access modifiers changed from: package-private */
            public Spliterator opEvaluateParallelLazy(PipelineHelper pipelineHelper, Spliterator spliterator) {
                return StreamOpFlag.ORDERED.isKnown(pipelineHelper.getStreamAndOpFlags()) ? opEvaluateParallel(pipelineHelper, spliterator, Nodes.castingArray()).spliterator() : new UnorderedWhileSpliterator.OfRef.Dropping(pipelineHelper.wrapSpliterator(spliterator), false, predicate);
            }

            /* access modifiers changed from: package-private */
            public Sink opWrapSink(int i, Sink sink) {
                return opWrapSink(sink, false);
            }

            public DropWhileSink opWrapSink(Sink sink, boolean z) {
                return new DropWhileSink(sink, z) {
                    long dropCount;
                    boolean take;
                    final /* synthetic */ boolean val$retainAndCountDroppedElements;
                    final /* synthetic */ Sink val$sink;

                    {
                        this.val$sink = r2;
                        this.val$retainAndCountDroppedElements = r3;
                    }

                    public void accept(Object obj) {
                        boolean z = true;
                        if (!this.take) {
                            boolean z2 = !predicate.test(obj);
                            this.take = z2;
                            if (!z2) {
                                z = false;
                            }
                        }
                        boolean z3 = this.val$retainAndCountDroppedElements;
                        if (z3 && !z) {
                            this.dropCount++;
                        }
                        if (z3 || z) {
                            this.downstream.accept(obj);
                        }
                    }

                    public long getDropCount() {
                        return this.dropCount;
                    }
                };
            }
        };
    }

    static Stream makeTakeWhileRef(AbstractPipeline abstractPipeline, final Predicate predicate) {
        Objects.requireNonNull(predicate);
        return new ReferencePipeline.StatefulOp(abstractPipeline, StreamShape.REFERENCE, TAKE_FLAGS) {
            /* access modifiers changed from: package-private */
            public Node opEvaluateParallel(PipelineHelper pipelineHelper, Spliterator spliterator, IntFunction intFunction) {
                return (Node) new TakeWhileTask(this, pipelineHelper, spliterator, intFunction).invoke();
            }

            /* access modifiers changed from: package-private */
            public Spliterator opEvaluateParallelLazy(PipelineHelper pipelineHelper, Spliterator spliterator) {
                return StreamOpFlag.ORDERED.isKnown(pipelineHelper.getStreamAndOpFlags()) ? opEvaluateParallel(pipelineHelper, spliterator, Nodes.castingArray()).spliterator() : new UnorderedWhileSpliterator.OfRef.Taking(pipelineHelper.wrapSpliterator(spliterator), false, predicate);
            }

            /* access modifiers changed from: package-private */
            public Sink opWrapSink(int i, Sink sink) {
                return new Sink.ChainedReference(sink) {
                    boolean take = true;

                    public void accept(Object obj) {
                        if (this.take) {
                            boolean test = predicate.test(obj);
                            this.take = test;
                            if (test) {
                                this.downstream.accept(obj);
                            }
                        }
                    }

                    public void begin(long j) {
                        this.downstream.begin(-1);
                    }

                    public boolean cancellationRequested() {
                        return !this.take || this.downstream.cancellationRequested();
                    }
                };
            }
        };
    }
}
