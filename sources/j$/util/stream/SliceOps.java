package j$.util.stream;

import j$.util.Spliterator;
import j$.util.stream.DoublePipeline;
import j$.util.stream.IntPipeline;
import j$.util.stream.LongPipeline;
import j$.util.stream.Node;
import j$.util.stream.ReferencePipeline;
import j$.util.stream.Sink;
import j$.util.stream.StreamSpliterators$SliceSpliterator;
import j$.util.stream.StreamSpliterators$UnorderedSliceSpliterator;
import java.util.function.IntFunction;

abstract class SliceOps {

    /* renamed from: j$.util.stream.SliceOps$5  reason: invalid class name */
    static /* synthetic */ class AnonymousClass5 {
        static final /* synthetic */ int[] $SwitchMap$java$util$stream$StreamShape;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                j$.util.stream.StreamShape[] r0 = j$.util.stream.StreamShape.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$java$util$stream$StreamShape = r0
                j$.util.stream.StreamShape r1 = j$.util.stream.StreamShape.REFERENCE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$java$util$stream$StreamShape     // Catch:{ NoSuchFieldError -> 0x001d }
                j$.util.stream.StreamShape r1 = j$.util.stream.StreamShape.INT_VALUE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$java$util$stream$StreamShape     // Catch:{ NoSuchFieldError -> 0x0028 }
                j$.util.stream.StreamShape r1 = j$.util.stream.StreamShape.LONG_VALUE     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$java$util$stream$StreamShape     // Catch:{ NoSuchFieldError -> 0x0033 }
                j$.util.stream.StreamShape r1 = j$.util.stream.StreamShape.DOUBLE_VALUE     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: j$.util.stream.SliceOps.AnonymousClass5.<clinit>():void");
        }
    }

    private static final class SliceTask extends AbstractShortCircuitTask {
        private volatile boolean completed;
        private final IntFunction generator;
        private final AbstractPipeline op;
        private final long targetOffset;
        private final long targetSize;
        private long thisNodeSize;

        SliceTask(AbstractPipeline abstractPipeline, PipelineHelper pipelineHelper, Spliterator spliterator, IntFunction intFunction, long j, long j2) {
            super(pipelineHelper, spliterator);
            this.op = abstractPipeline;
            this.generator = intFunction;
            this.targetOffset = j;
            this.targetSize = j2;
        }

        SliceTask(SliceTask sliceTask, Spliterator spliterator) {
            super((AbstractShortCircuitTask) sliceTask, spliterator);
            this.op = sliceTask.op;
            this.generator = sliceTask.generator;
            this.targetOffset = sliceTask.targetOffset;
            this.targetSize = sliceTask.targetSize;
        }

        private long completedSize(long j) {
            if (this.completed) {
                return this.thisNodeSize;
            }
            SliceTask sliceTask = (SliceTask) this.leftChild;
            SliceTask sliceTask2 = (SliceTask) this.rightChild;
            if (sliceTask == null || sliceTask2 == null) {
                return this.thisNodeSize;
            }
            long completedSize = sliceTask.completedSize(j);
            return completedSize >= j ? completedSize : completedSize + sliceTask2.completedSize(j);
        }

        private Node doTruncate(Node node) {
            return node.truncate(this.targetOffset, this.targetSize >= 0 ? Math.min(node.count(), this.targetOffset + this.targetSize) : this.thisNodeSize, this.generator);
        }

        private boolean isLeftCompleted(long j) {
            SliceTask sliceTask;
            long completedSize = this.completed ? this.thisNodeSize : completedSize(j);
            if (completedSize >= j) {
                return true;
            }
            SliceTask sliceTask2 = this;
            for (SliceTask sliceTask3 = (SliceTask) getParent(); sliceTask3 != null; sliceTask3 = (SliceTask) sliceTask3.getParent()) {
                if (sliceTask2 == sliceTask3.rightChild && (sliceTask = (SliceTask) sliceTask3.leftChild) != null) {
                    completedSize += sliceTask.completedSize(j);
                    if (completedSize >= j) {
                        return true;
                    }
                }
                sliceTask2 = sliceTask3;
            }
            return completedSize >= j;
        }

        /* access modifiers changed from: protected */
        public void cancel() {
            super.cancel();
            if (this.completed) {
                setLocalResult(getEmptyResult());
            }
        }

        /* access modifiers changed from: protected */
        public final Node doLeaf() {
            long j = -1;
            if (isRoot()) {
                if (StreamOpFlag.SIZED.isPreserved(this.op.sourceOrOpFlags)) {
                    j = this.op.exactOutputSizeIfKnown(this.spliterator);
                }
                Node.Builder makeNodeBuilder = this.op.makeNodeBuilder(j, this.generator);
                Sink opWrapSink = this.op.opWrapSink(this.helper.getStreamAndOpFlags(), makeNodeBuilder);
                PipelineHelper pipelineHelper = this.helper;
                pipelineHelper.copyIntoWithCancel(pipelineHelper.wrapSink(opWrapSink), this.spliterator);
                return makeNodeBuilder.build();
            }
            Node.Builder makeNodeBuilder2 = this.op.makeNodeBuilder(-1, this.generator);
            if (this.targetOffset == 0) {
                Sink opWrapSink2 = this.op.opWrapSink(this.helper.getStreamAndOpFlags(), makeNodeBuilder2);
                PipelineHelper pipelineHelper2 = this.helper;
                pipelineHelper2.copyIntoWithCancel(pipelineHelper2.wrapSink(opWrapSink2), this.spliterator);
            } else {
                this.helper.wrapAndCopyInto(makeNodeBuilder2, this.spliterator);
            }
            Node build = makeNodeBuilder2.build();
            this.thisNodeSize = build.count();
            this.completed = true;
            this.spliterator = null;
            return build;
        }

        /* access modifiers changed from: protected */
        public final Node getEmptyResult() {
            return Nodes.emptyNode(this.op.getOutputShape());
        }

        /* access modifiers changed from: protected */
        public SliceTask makeChild(Spliterator spliterator) {
            return new SliceTask(this, spliterator);
        }

        /* JADX WARNING: Removed duplicated region for block: B:14:0x0062  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void onCompletion(java.util.concurrent.CountedCompleter r8) {
            /*
                r7 = this;
                boolean r0 = r7.isLeaf()
                r1 = 0
                if (r0 != 0) goto L_0x006c
                j$.util.stream.AbstractTask r0 = r7.leftChild
                j$.util.stream.SliceOps$SliceTask r0 = (j$.util.stream.SliceOps.SliceTask) r0
                long r3 = r0.thisNodeSize
                j$.util.stream.AbstractTask r0 = r7.rightChild
                j$.util.stream.SliceOps$SliceTask r0 = (j$.util.stream.SliceOps.SliceTask) r0
                long r5 = r0.thisNodeSize
                long r3 = r3 + r5
                r7.thisNodeSize = r3
                boolean r0 = r7.canceled
                if (r0 == 0) goto L_0x0022
                r7.thisNodeSize = r1
            L_0x001d:
                j$.util.stream.Node r0 = r7.getEmptyResult()
                goto L_0x005c
            L_0x0022:
                long r3 = r7.thisNodeSize
                int r0 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
                if (r0 != 0) goto L_0x0029
                goto L_0x001d
            L_0x0029:
                j$.util.stream.AbstractTask r0 = r7.leftChild
                j$.util.stream.SliceOps$SliceTask r0 = (j$.util.stream.SliceOps.SliceTask) r0
                long r3 = r0.thisNodeSize
                int r0 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
                if (r0 != 0) goto L_0x003e
                j$.util.stream.AbstractTask r0 = r7.rightChild
                j$.util.stream.SliceOps$SliceTask r0 = (j$.util.stream.SliceOps.SliceTask) r0
                java.lang.Object r0 = r0.getLocalResult()
                j$.util.stream.Node r0 = (j$.util.stream.Node) r0
                goto L_0x005c
            L_0x003e:
                j$.util.stream.AbstractPipeline r0 = r7.op
                j$.util.stream.StreamShape r0 = r0.getOutputShape()
                j$.util.stream.AbstractTask r3 = r7.leftChild
                j$.util.stream.SliceOps$SliceTask r3 = (j$.util.stream.SliceOps.SliceTask) r3
                java.lang.Object r3 = r3.getLocalResult()
                j$.util.stream.Node r3 = (j$.util.stream.Node) r3
                j$.util.stream.AbstractTask r4 = r7.rightChild
                j$.util.stream.SliceOps$SliceTask r4 = (j$.util.stream.SliceOps.SliceTask) r4
                java.lang.Object r4 = r4.getLocalResult()
                j$.util.stream.Node r4 = (j$.util.stream.Node) r4
                j$.util.stream.Node r0 = j$.util.stream.Nodes.conc(r0, r3, r4)
            L_0x005c:
                boolean r3 = r7.isRoot()
                if (r3 == 0) goto L_0x0066
                j$.util.stream.Node r0 = r7.doTruncate(r0)
            L_0x0066:
                r7.setLocalResult(r0)
                r0 = 1
                r7.completed = r0
            L_0x006c:
                long r3 = r7.targetSize
                int r0 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
                if (r0 < 0) goto L_0x0086
                boolean r0 = r7.isRoot()
                if (r0 != 0) goto L_0x0086
                long r0 = r7.targetOffset
                long r2 = r7.targetSize
                long r0 = r0 + r2
                boolean r0 = r7.isLeftCompleted(r0)
                if (r0 == 0) goto L_0x0086
                r7.cancelLaterNodes()
            L_0x0086:
                super.onCompletion(r8)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: j$.util.stream.SliceOps.SliceTask.onCompletion(java.util.concurrent.CountedCompleter):void");
        }
    }

    /* access modifiers changed from: private */
    public static long calcSize(long j, long j2, long j3) {
        if (j >= 0) {
            return Math.max(-1, Math.min(j - j2, j3));
        }
        return -1;
    }

    /* access modifiers changed from: private */
    public static long calcSliceFence(long j, long j2) {
        long j3 = j2 >= 0 ? j + j2 : Long.MAX_VALUE;
        if (j3 >= 0) {
            return j3;
        }
        return Long.MAX_VALUE;
    }

    private static int flags(long j) {
        return (j != -1 ? StreamOpFlag.IS_SHORT_CIRCUIT : 0) | StreamOpFlag.NOT_SIZED;
    }

    public static DoubleStream makeDouble(AbstractPipeline abstractPipeline, long j, long j2) {
        if (j >= 0) {
            final long j3 = j;
            final long j4 = j2;
            return new DoublePipeline.StatefulOp(abstractPipeline, StreamShape.DOUBLE_VALUE, flags(j2)) {
                static /* synthetic */ Double[] lambda$opEvaluateParallelLazy$0(int i) {
                    return new Double[i];
                }

                /* access modifiers changed from: package-private */
                public Node opEvaluateParallel(PipelineHelper pipelineHelper, Spliterator spliterator, IntFunction intFunction) {
                    long exactOutputSizeIfKnown = pipelineHelper.exactOutputSizeIfKnown(spliterator);
                    if (exactOutputSizeIfKnown <= 0) {
                        PipelineHelper pipelineHelper2 = pipelineHelper;
                        Spliterator spliterator2 = spliterator;
                    } else if (spliterator.hasCharacteristics(16384)) {
                        return Nodes.collectDouble(pipelineHelper, SliceOps.sliceSpliterator(pipelineHelper.getSourceShape(), spliterator, j3, j4), true);
                    } else {
                        PipelineHelper pipelineHelper3 = pipelineHelper;
                    }
                    if (!StreamOpFlag.ORDERED.isKnown(pipelineHelper.getStreamAndOpFlags())) {
                        return Nodes.collectDouble(this, unorderedSkipLimitSpliterator((Spliterator.OfDouble) pipelineHelper.wrapSpliterator(spliterator), j3, j4, exactOutputSizeIfKnown), true);
                    }
                    return (Node) new SliceTask(this, pipelineHelper, spliterator, intFunction, j3, j4).invoke();
                }

                /* access modifiers changed from: package-private */
                public Spliterator opEvaluateParallelLazy(PipelineHelper pipelineHelper, Spliterator spliterator) {
                    long exactOutputSizeIfKnown = pipelineHelper.exactOutputSizeIfKnown(spliterator);
                    if (exactOutputSizeIfKnown <= 0) {
                        Spliterator spliterator2 = spliterator;
                    } else if (spliterator.hasCharacteristics(16384)) {
                        long j = j3;
                        return new StreamSpliterators$SliceSpliterator.OfDouble((Spliterator.OfDouble) pipelineHelper.wrapSpliterator(spliterator), j, SliceOps.calcSliceFence(j, j4));
                    }
                    if (!StreamOpFlag.ORDERED.isKnown(pipelineHelper.getStreamAndOpFlags())) {
                        return unorderedSkipLimitSpliterator((Spliterator.OfDouble) pipelineHelper.wrapSpliterator(spliterator), j3, j4, exactOutputSizeIfKnown);
                    }
                    return ((Node) new SliceTask(this, pipelineHelper, spliterator, new SliceOps$4$$ExternalSyntheticLambda0(), j3, j4).invoke()).spliterator();
                }

                /* access modifiers changed from: package-private */
                public Sink opWrapSink(int i, Sink sink) {
                    return new Sink.ChainedDouble(sink) {
                        long m;
                        long n;

                        {
                            this.n = j3;
                            long j = j4;
                            this.m = j < 0 ? Long.MAX_VALUE : j;
                        }

                        public void accept(double d) {
                            long j = this.n;
                            if (j == 0) {
                                long j2 = this.m;
                                if (j2 > 0) {
                                    this.m = j2 - 1;
                                    this.downstream.accept(d);
                                    return;
                                }
                                return;
                            }
                            this.n = j - 1;
                        }

                        public void begin(long j) {
                            this.downstream.begin(SliceOps.calcSize(j, j3, this.m));
                        }

                        public boolean cancellationRequested() {
                            return this.m == 0 || this.downstream.cancellationRequested();
                        }
                    };
                }

                /* access modifiers changed from: package-private */
                public Spliterator.OfDouble unorderedSkipLimitSpliterator(Spliterator.OfDouble ofDouble, long j, long j2, long j3) {
                    long j4;
                    long j5;
                    if (j <= j3) {
                        long j6 = j3 - j;
                        j4 = j2 >= 0 ? Math.min(j2, j6) : j6;
                        j5 = 0;
                    } else {
                        j5 = j;
                        j4 = j2;
                    }
                    return new StreamSpliterators$UnorderedSliceSpliterator.OfDouble(ofDouble, j5, j4);
                }
            };
        }
        throw new IllegalArgumentException("Skip must be non-negative: " + j);
    }

    public static IntStream makeInt(AbstractPipeline abstractPipeline, long j, long j2) {
        if (j >= 0) {
            final long j3 = j;
            final long j4 = j2;
            return new IntPipeline.StatefulOp(abstractPipeline, StreamShape.INT_VALUE, flags(j2)) {
                static /* synthetic */ Integer[] lambda$opEvaluateParallelLazy$0(int i) {
                    return new Integer[i];
                }

                /* access modifiers changed from: package-private */
                public Node opEvaluateParallel(PipelineHelper pipelineHelper, Spliterator spliterator, IntFunction intFunction) {
                    long exactOutputSizeIfKnown = pipelineHelper.exactOutputSizeIfKnown(spliterator);
                    if (exactOutputSizeIfKnown <= 0) {
                        PipelineHelper pipelineHelper2 = pipelineHelper;
                        Spliterator spliterator2 = spliterator;
                    } else if (spliterator.hasCharacteristics(16384)) {
                        return Nodes.collectInt(pipelineHelper, SliceOps.sliceSpliterator(pipelineHelper.getSourceShape(), spliterator, j3, j4), true);
                    } else {
                        PipelineHelper pipelineHelper3 = pipelineHelper;
                    }
                    if (!StreamOpFlag.ORDERED.isKnown(pipelineHelper.getStreamAndOpFlags())) {
                        return Nodes.collectInt(this, unorderedSkipLimitSpliterator((Spliterator.OfInt) pipelineHelper.wrapSpliterator(spliterator), j3, j4, exactOutputSizeIfKnown), true);
                    }
                    return (Node) new SliceTask(this, pipelineHelper, spliterator, intFunction, j3, j4).invoke();
                }

                /* access modifiers changed from: package-private */
                public Spliterator opEvaluateParallelLazy(PipelineHelper pipelineHelper, Spliterator spliterator) {
                    long exactOutputSizeIfKnown = pipelineHelper.exactOutputSizeIfKnown(spliterator);
                    if (exactOutputSizeIfKnown <= 0) {
                        Spliterator spliterator2 = spliterator;
                    } else if (spliterator.hasCharacteristics(16384)) {
                        long j = j3;
                        return new StreamSpliterators$SliceSpliterator.OfInt((Spliterator.OfInt) pipelineHelper.wrapSpliterator(spliterator), j, SliceOps.calcSliceFence(j, j4));
                    }
                    if (!StreamOpFlag.ORDERED.isKnown(pipelineHelper.getStreamAndOpFlags())) {
                        return unorderedSkipLimitSpliterator((Spliterator.OfInt) pipelineHelper.wrapSpliterator(spliterator), j3, j4, exactOutputSizeIfKnown);
                    }
                    return ((Node) new SliceTask(this, pipelineHelper, spliterator, new SliceOps$2$$ExternalSyntheticLambda0(), j3, j4).invoke()).spliterator();
                }

                /* access modifiers changed from: package-private */
                public Sink opWrapSink(int i, Sink sink) {
                    return new Sink.ChainedInt(sink) {
                        long m;
                        long n;

                        {
                            this.n = j3;
                            long j = j4;
                            this.m = j < 0 ? Long.MAX_VALUE : j;
                        }

                        public void accept(int i) {
                            long j = this.n;
                            if (j == 0) {
                                long j2 = this.m;
                                if (j2 > 0) {
                                    this.m = j2 - 1;
                                    this.downstream.accept(i);
                                    return;
                                }
                                return;
                            }
                            this.n = j - 1;
                        }

                        public void begin(long j) {
                            this.downstream.begin(SliceOps.calcSize(j, j3, this.m));
                        }

                        public boolean cancellationRequested() {
                            return this.m == 0 || this.downstream.cancellationRequested();
                        }
                    };
                }

                /* access modifiers changed from: package-private */
                public Spliterator.OfInt unorderedSkipLimitSpliterator(Spliterator.OfInt ofInt, long j, long j2, long j3) {
                    long j4;
                    long j5;
                    if (j <= j3) {
                        long j6 = j3 - j;
                        j4 = j2 >= 0 ? Math.min(j2, j6) : j6;
                        j5 = 0;
                    } else {
                        j5 = j;
                        j4 = j2;
                    }
                    return new StreamSpliterators$UnorderedSliceSpliterator.OfInt(ofInt, j5, j4);
                }
            };
        }
        throw new IllegalArgumentException("Skip must be non-negative: " + j);
    }

    public static LongStream makeLong(AbstractPipeline abstractPipeline, long j, long j2) {
        if (j >= 0) {
            final long j3 = j;
            final long j4 = j2;
            return new LongPipeline.StatefulOp(abstractPipeline, StreamShape.LONG_VALUE, flags(j2)) {
                static /* synthetic */ Long[] lambda$opEvaluateParallelLazy$0(int i) {
                    return new Long[i];
                }

                /* access modifiers changed from: package-private */
                public Node opEvaluateParallel(PipelineHelper pipelineHelper, Spliterator spliterator, IntFunction intFunction) {
                    long exactOutputSizeIfKnown = pipelineHelper.exactOutputSizeIfKnown(spliterator);
                    if (exactOutputSizeIfKnown <= 0) {
                        PipelineHelper pipelineHelper2 = pipelineHelper;
                        Spliterator spliterator2 = spliterator;
                    } else if (spliterator.hasCharacteristics(16384)) {
                        return Nodes.collectLong(pipelineHelper, SliceOps.sliceSpliterator(pipelineHelper.getSourceShape(), spliterator, j3, j4), true);
                    } else {
                        PipelineHelper pipelineHelper3 = pipelineHelper;
                    }
                    if (!StreamOpFlag.ORDERED.isKnown(pipelineHelper.getStreamAndOpFlags())) {
                        return Nodes.collectLong(this, unorderedSkipLimitSpliterator((Spliterator.OfLong) pipelineHelper.wrapSpliterator(spliterator), j3, j4, exactOutputSizeIfKnown), true);
                    }
                    return (Node) new SliceTask(this, pipelineHelper, spliterator, intFunction, j3, j4).invoke();
                }

                /* access modifiers changed from: package-private */
                public Spliterator opEvaluateParallelLazy(PipelineHelper pipelineHelper, Spliterator spliterator) {
                    long exactOutputSizeIfKnown = pipelineHelper.exactOutputSizeIfKnown(spliterator);
                    if (exactOutputSizeIfKnown <= 0) {
                        Spliterator spliterator2 = spliterator;
                    } else if (spliterator.hasCharacteristics(16384)) {
                        long j = j3;
                        return new StreamSpliterators$SliceSpliterator.OfLong((Spliterator.OfLong) pipelineHelper.wrapSpliterator(spliterator), j, SliceOps.calcSliceFence(j, j4));
                    }
                    if (!StreamOpFlag.ORDERED.isKnown(pipelineHelper.getStreamAndOpFlags())) {
                        return unorderedSkipLimitSpliterator((Spliterator.OfLong) pipelineHelper.wrapSpliterator(spliterator), j3, j4, exactOutputSizeIfKnown);
                    }
                    return ((Node) new SliceTask(this, pipelineHelper, spliterator, new SliceOps$3$$ExternalSyntheticLambda0(), j3, j4).invoke()).spliterator();
                }

                /* access modifiers changed from: package-private */
                public Sink opWrapSink(int i, Sink sink) {
                    return new Sink.ChainedLong(sink) {
                        long m;
                        long n;

                        {
                            this.n = j3;
                            long j = j4;
                            this.m = j < 0 ? Long.MAX_VALUE : j;
                        }

                        public void accept(long j) {
                            long j2 = this.n;
                            if (j2 == 0) {
                                long j3 = this.m;
                                if (j3 > 0) {
                                    this.m = j3 - 1;
                                    this.downstream.accept(j);
                                    return;
                                }
                                return;
                            }
                            this.n = j2 - 1;
                        }

                        public void begin(long j) {
                            this.downstream.begin(SliceOps.calcSize(j, j3, this.m));
                        }

                        public boolean cancellationRequested() {
                            return this.m == 0 || this.downstream.cancellationRequested();
                        }
                    };
                }

                /* access modifiers changed from: package-private */
                public Spliterator.OfLong unorderedSkipLimitSpliterator(Spliterator.OfLong ofLong, long j, long j2, long j3) {
                    long j4;
                    long j5;
                    if (j <= j3) {
                        long j6 = j3 - j;
                        j4 = j2 >= 0 ? Math.min(j2, j6) : j6;
                        j5 = 0;
                    } else {
                        j5 = j;
                        j4 = j2;
                    }
                    return new StreamSpliterators$UnorderedSliceSpliterator.OfLong(ofLong, j5, j4);
                }
            };
        }
        throw new IllegalArgumentException("Skip must be non-negative: " + j);
    }

    public static Stream makeRef(AbstractPipeline abstractPipeline, long j, long j2) {
        if (j >= 0) {
            final long j3 = j;
            final long j4 = j2;
            return new ReferencePipeline.StatefulOp(abstractPipeline, StreamShape.REFERENCE, flags(j2)) {
                /* access modifiers changed from: package-private */
                public Node opEvaluateParallel(PipelineHelper pipelineHelper, Spliterator spliterator, IntFunction intFunction) {
                    IntFunction intFunction2 = intFunction;
                    long exactOutputSizeIfKnown = pipelineHelper.exactOutputSizeIfKnown(spliterator);
                    if (exactOutputSizeIfKnown <= 0) {
                        PipelineHelper pipelineHelper2 = pipelineHelper;
                        Spliterator spliterator2 = spliterator;
                    } else if (spliterator.hasCharacteristics(16384)) {
                        return Nodes.collect(pipelineHelper, SliceOps.sliceSpliterator(pipelineHelper.getSourceShape(), spliterator, j3, j4), true, intFunction2);
                    } else {
                        PipelineHelper pipelineHelper3 = pipelineHelper;
                    }
                    if (!StreamOpFlag.ORDERED.isKnown(pipelineHelper.getStreamAndOpFlags())) {
                        return Nodes.collect(this, unorderedSkipLimitSpliterator(pipelineHelper.wrapSpliterator(spliterator), j3, j4, exactOutputSizeIfKnown), true, intFunction2);
                    }
                    return (Node) new SliceTask(this, pipelineHelper, spliterator, intFunction, j3, j4).invoke();
                }

                /* access modifiers changed from: package-private */
                public Spliterator opEvaluateParallelLazy(PipelineHelper pipelineHelper, Spliterator spliterator) {
                    long exactOutputSizeIfKnown = pipelineHelper.exactOutputSizeIfKnown(spliterator);
                    if (exactOutputSizeIfKnown <= 0) {
                        Spliterator spliterator2 = spliterator;
                    } else if (spliterator.hasCharacteristics(16384)) {
                        Spliterator wrapSpliterator = pipelineHelper.wrapSpliterator(spliterator);
                        long j = j3;
                        return new StreamSpliterators$SliceSpliterator.OfRef(wrapSpliterator, j, SliceOps.calcSliceFence(j, j4));
                    }
                    if (!StreamOpFlag.ORDERED.isKnown(pipelineHelper.getStreamAndOpFlags())) {
                        return unorderedSkipLimitSpliterator(pipelineHelper.wrapSpliterator(spliterator), j3, j4, exactOutputSizeIfKnown);
                    }
                    return ((Node) new SliceTask(this, pipelineHelper, spliterator, Nodes.castingArray(), j3, j4).invoke()).spliterator();
                }

                /* access modifiers changed from: package-private */
                public Sink opWrapSink(int i, Sink sink) {
                    return new Sink.ChainedReference(sink) {
                        long m;
                        long n;

                        {
                            this.n = j3;
                            long j = j4;
                            this.m = j < 0 ? Long.MAX_VALUE : j;
                        }

                        public void accept(Object obj) {
                            long j = this.n;
                            if (j == 0) {
                                long j2 = this.m;
                                if (j2 > 0) {
                                    this.m = j2 - 1;
                                    this.downstream.accept(obj);
                                    return;
                                }
                                return;
                            }
                            this.n = j - 1;
                        }

                        public void begin(long j) {
                            this.downstream.begin(SliceOps.calcSize(j, j3, this.m));
                        }

                        public boolean cancellationRequested() {
                            return this.m == 0 || this.downstream.cancellationRequested();
                        }
                    };
                }

                /* access modifiers changed from: package-private */
                public Spliterator unorderedSkipLimitSpliterator(Spliterator spliterator, long j, long j2, long j3) {
                    long j4;
                    long j5;
                    if (j <= j3) {
                        long j6 = j3 - j;
                        j4 = j2 >= 0 ? Math.min(j2, j6) : j6;
                        j5 = 0;
                    } else {
                        j5 = j;
                        j4 = j2;
                    }
                    return new StreamSpliterators$UnorderedSliceSpliterator.OfRef(spliterator, j5, j4);
                }
            };
        }
        throw new IllegalArgumentException("Skip must be non-negative: " + j);
    }

    /* access modifiers changed from: private */
    public static Spliterator sliceSpliterator(StreamShape streamShape, Spliterator spliterator, long j, long j2) {
        long calcSliceFence = calcSliceFence(j, j2);
        int i = AnonymousClass5.$SwitchMap$java$util$stream$StreamShape[streamShape.ordinal()];
        if (i == 1) {
            return new StreamSpliterators$SliceSpliterator.OfRef(spliterator, j, calcSliceFence);
        }
        if (i == 2) {
            return new StreamSpliterators$SliceSpliterator.OfInt((Spliterator.OfInt) spliterator, j, calcSliceFence);
        }
        if (i == 3) {
            return new StreamSpliterators$SliceSpliterator.OfLong((Spliterator.OfLong) spliterator, j, calcSliceFence);
        }
        if (i == 4) {
            return new StreamSpliterators$SliceSpliterator.OfDouble((Spliterator.OfDouble) spliterator, j, calcSliceFence);
        }
        throw new IllegalStateException("Unknown shape " + streamShape);
    }
}
