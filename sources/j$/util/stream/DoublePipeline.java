package j$.util.stream;

import j$.util.DoubleSummaryStatistics;
import j$.util.Objects;
import j$.util.OptionalDouble;
import j$.util.PrimitiveIterator;
import j$.util.Spliterator;
import j$.util.Spliterators;
import j$.util.stream.IntPipeline;
import j$.util.stream.LongPipeline;
import j$.util.stream.MatchOps;
import j$.util.stream.Node;
import j$.util.stream.ReferencePipeline;
import j$.util.stream.Sink;
import j$.util.stream.StreamSpliterators$DelegatingSpliterator;
import java.util.Iterator;
import java.util.function.BiConsumer;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleFunction;
import java.util.function.DoublePredicate;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleToLongFunction;
import java.util.function.DoubleUnaryOperator;
import java.util.function.IntFunction;
import java.util.function.ObjDoubleConsumer;
import java.util.function.Supplier;

abstract class DoublePipeline extends AbstractPipeline implements DoubleStream {

    static class Head extends DoublePipeline {
        Head(Spliterator spliterator, int i, boolean z) {
            super(spliterator, i, z);
        }

        public void forEach(DoubleConsumer doubleConsumer) {
            if (!isParallel()) {
                DoublePipeline.adapt(sourceStageSpliterator()).forEachRemaining(doubleConsumer);
            } else {
                DoublePipeline.super.forEach(doubleConsumer);
            }
        }

        public void forEachOrdered(DoubleConsumer doubleConsumer) {
            if (!isParallel()) {
                DoublePipeline.adapt(sourceStageSpliterator()).forEachRemaining(doubleConsumer);
            } else {
                DoublePipeline.super.forEachOrdered(doubleConsumer);
            }
        }

        public /* bridge */ /* synthetic */ Iterator iterator() {
            return DoublePipeline.super.iterator();
        }

        /* access modifiers changed from: package-private */
        public /* bridge */ /* synthetic */ Spliterator lazySpliterator(Supplier supplier) {
            return DoublePipeline.super.lazySpliterator(supplier);
        }

        /* access modifiers changed from: package-private */
        public final boolean opIsStateful() {
            throw new UnsupportedOperationException();
        }

        /* access modifiers changed from: package-private */
        public final Sink opWrapSink(int i, Sink sink) {
            throw new UnsupportedOperationException();
        }

        public /* bridge */ /* synthetic */ DoubleStream parallel() {
            return (DoubleStream) super.parallel();
        }

        public /* bridge */ /* synthetic */ DoubleStream sequential() {
            return (DoubleStream) super.sequential();
        }

        public /* bridge */ /* synthetic */ Spliterator spliterator() {
            return DoublePipeline.super.spliterator();
        }

        public /* bridge */ /* synthetic */ BaseStream unordered() {
            return DoublePipeline.super.unordered();
        }
    }

    static abstract class StatefulOp extends DoublePipeline {
        static {
            Class<DoublePipeline> cls = DoublePipeline.class;
        }

        StatefulOp(AbstractPipeline abstractPipeline, StreamShape streamShape, int i) {
            super(abstractPipeline, i);
        }

        public /* bridge */ /* synthetic */ Iterator iterator() {
            return DoublePipeline.super.iterator();
        }

        /* access modifiers changed from: package-private */
        public /* bridge */ /* synthetic */ Spliterator lazySpliterator(Supplier supplier) {
            return DoublePipeline.super.lazySpliterator(supplier);
        }

        /* access modifiers changed from: package-private */
        public final boolean opIsStateful() {
            return true;
        }

        public /* bridge */ /* synthetic */ DoubleStream parallel() {
            return (DoubleStream) super.parallel();
        }

        public /* bridge */ /* synthetic */ DoubleStream sequential() {
            return (DoubleStream) super.sequential();
        }

        public /* bridge */ /* synthetic */ Spliterator spliterator() {
            return DoublePipeline.super.spliterator();
        }

        public /* bridge */ /* synthetic */ BaseStream unordered() {
            return DoublePipeline.super.unordered();
        }
    }

    static abstract class StatelessOp extends DoublePipeline {
        static {
            Class<DoublePipeline> cls = DoublePipeline.class;
        }

        StatelessOp(AbstractPipeline abstractPipeline, StreamShape streamShape, int i) {
            super(abstractPipeline, i);
        }

        public /* bridge */ /* synthetic */ Iterator iterator() {
            return DoublePipeline.super.iterator();
        }

        /* access modifiers changed from: package-private */
        public /* bridge */ /* synthetic */ Spliterator lazySpliterator(Supplier supplier) {
            return DoublePipeline.super.lazySpliterator(supplier);
        }

        /* access modifiers changed from: package-private */
        public final boolean opIsStateful() {
            return false;
        }

        public /* bridge */ /* synthetic */ DoubleStream parallel() {
            return (DoubleStream) super.parallel();
        }

        public /* bridge */ /* synthetic */ DoubleStream sequential() {
            return (DoubleStream) super.sequential();
        }

        public /* bridge */ /* synthetic */ Spliterator spliterator() {
            return DoublePipeline.super.spliterator();
        }

        public /* bridge */ /* synthetic */ BaseStream unordered() {
            return DoublePipeline.super.unordered();
        }
    }

    DoublePipeline(Spliterator spliterator, int i, boolean z) {
        super(spliterator, i, z);
    }

    DoublePipeline(AbstractPipeline abstractPipeline, int i) {
        super(abstractPipeline, i);
    }

    /* access modifiers changed from: private */
    public static Spliterator.OfDouble adapt(Spliterator spliterator) {
        if (spliterator instanceof Spliterator.OfDouble) {
            return (Spliterator.OfDouble) spliterator;
        }
        if (Tripwire.ENABLED) {
            Tripwire.trip(AbstractPipeline.class, "using DoubleStream.adapt(Spliterator<Double> s)");
        }
        throw new UnsupportedOperationException("DoubleStream.adapt(Spliterator<Double> s)");
    }

    private static DoubleConsumer adapt(Sink sink) {
        if (sink instanceof DoubleConsumer) {
            return (DoubleConsumer) sink;
        }
        if (Tripwire.ENABLED) {
            Tripwire.trip(AbstractPipeline.class, "using DoubleStream.adapt(Sink<Double> s)");
        }
        Objects.requireNonNull(sink);
        return new DoublePipeline$$ExternalSyntheticLambda0(sink);
    }

    static /* synthetic */ double[] lambda$average$4() {
        return new double[4];
    }

    static /* synthetic */ void lambda$average$5(double[] dArr, double d) {
        dArr[2] = dArr[2] + 1.0d;
        Collectors.sumWithCompensation(dArr, d);
        dArr[3] = dArr[3] + d;
    }

    static /* synthetic */ void lambda$average$6(double[] dArr, double[] dArr2) {
        Collectors.sumWithCompensation(dArr, dArr2[0]);
        Collectors.sumWithCompensation(dArr, dArr2[1]);
        dArr[2] = dArr[2] + dArr2[2];
        dArr[3] = dArr[3] + dArr2[3];
    }

    static /* synthetic */ double[] lambda$sum$1() {
        return new double[3];
    }

    static /* synthetic */ void lambda$sum$2(double[] dArr, double d) {
        Collectors.sumWithCompensation(dArr, d);
        dArr[2] = dArr[2] + d;
    }

    static /* synthetic */ void lambda$sum$3(double[] dArr, double[] dArr2) {
        Collectors.sumWithCompensation(dArr, dArr2[0]);
        Collectors.sumWithCompensation(dArr, dArr2[1]);
        dArr[2] = dArr[2] + dArr2[2];
    }

    static /* synthetic */ Double[] lambda$toArray$8(int i) {
        return new Double[i];
    }

    private Stream mapToObj(DoubleFunction doubleFunction, int i) {
        final DoubleFunction doubleFunction2 = doubleFunction;
        return new ReferencePipeline.StatelessOp(this, StreamShape.DOUBLE_VALUE, i) {
            /* access modifiers changed from: package-private */
            public Sink opWrapSink(int i, Sink sink) {
                return new Sink.ChainedDouble(sink) {
                    public void accept(double d) {
                        this.downstream.accept(doubleFunction2.apply(d));
                    }
                };
            }
        };
    }

    public final boolean allMatch(DoublePredicate doublePredicate) {
        return ((Boolean) evaluate(MatchOps.makeDouble(doublePredicate, MatchOps.MatchKind.ALL))).booleanValue();
    }

    public final boolean anyMatch(DoublePredicate doublePredicate) {
        return ((Boolean) evaluate(MatchOps.makeDouble(doublePredicate, MatchOps.MatchKind.ANY))).booleanValue();
    }

    public final OptionalDouble average() {
        double[] dArr = (double[]) collect(new DoublePipeline$$ExternalSyntheticLambda12(), new DoublePipeline$$ExternalSyntheticLambda13(), new DoublePipeline$$ExternalSyntheticLambda14());
        return dArr[2] > 0.0d ? OptionalDouble.of(Collectors.computeFinalSum(dArr) / dArr[2]) : OptionalDouble.empty();
    }

    public final Stream boxed() {
        return mapToObj(new DoublePipeline$$ExternalSyntheticLambda3(), 0);
    }

    public final Object collect(Supplier supplier, ObjDoubleConsumer objDoubleConsumer, BiConsumer biConsumer) {
        Objects.requireNonNull(biConsumer);
        return evaluate(ReduceOps.makeDouble(supplier, objDoubleConsumer, new DoublePipeline$$ExternalSyntheticLambda6(biConsumer)));
    }

    public final long count() {
        return ((Long) evaluate(ReduceOps.makeDoubleCounting())).longValue();
    }

    public final DoubleStream distinct() {
        return boxed().distinct().mapToDouble(new DoublePipeline$$ExternalSyntheticLambda4());
    }

    /* access modifiers changed from: package-private */
    public final Node evaluateToNode(PipelineHelper pipelineHelper, Spliterator spliterator, boolean z, IntFunction intFunction) {
        return Nodes.collectDouble(pipelineHelper, spliterator, z);
    }

    public final DoubleStream filter(DoublePredicate doublePredicate) {
        Objects.requireNonNull(doublePredicate);
        return new StatelessOp(this, StreamShape.DOUBLE_VALUE, StreamOpFlag.NOT_SIZED, doublePredicate) {
            /* access modifiers changed from: package-private */
            public Sink opWrapSink(int i, Sink sink) {
                return new Sink.ChainedDouble(sink) {
                    /* JADX WARNING: type inference failed for: r0v1, types: [java.lang.Throwable, java.util.function.DoublePredicate] */
                    public void accept(double d) {
                        AnonymousClass7.this.getClass();
                        ? r0 = 0;
                        r0.test(d);
                        throw r0;
                    }

                    public void begin(long j) {
                        this.downstream.begin(-1);
                    }
                };
            }
        };
    }

    public final OptionalDouble findAny() {
        return (OptionalDouble) evaluate(FindOps.makeDouble(false));
    }

    public final OptionalDouble findFirst() {
        return (OptionalDouble) evaluate(FindOps.makeDouble(true));
    }

    public final DoubleStream flatMap(DoubleFunction doubleFunction) {
        Objects.requireNonNull(doubleFunction);
        final DoubleFunction doubleFunction2 = doubleFunction;
        return new StatelessOp(this, StreamShape.DOUBLE_VALUE, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT | StreamOpFlag.NOT_SIZED) {
            /* access modifiers changed from: package-private */
            public Sink opWrapSink(int i, Sink sink) {
                return new Sink.ChainedDouble(sink) {
                    boolean cancellationRequestedCalled;
                    DoubleConsumer downstreamAsDouble;

                    {
                        Sink sink = this.downstream;
                        Objects.requireNonNull(sink);
                        this.downstreamAsDouble = new DoublePipeline$$ExternalSyntheticLambda0(sink);
                    }

                    /* JADX WARNING: Removed duplicated region for block: B:9:0x0024 A[Catch:{ all -> 0x001a, all -> 0x0039 }, LOOP:0: B:9:0x0024->B:12:0x0032, LOOP_START] */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public void accept(double r2) {
                        /*
                            r1 = this;
                            j$.util.stream.DoublePipeline$5 r0 = j$.util.stream.DoublePipeline.AnonymousClass5.this
                            java.util.function.DoubleFunction r0 = r5
                            java.lang.Object r2 = r0.apply(r2)
                            j$.util.stream.DoubleStream r2 = (j$.util.stream.DoubleStream) r2
                            if (r2 == 0) goto L_0x003e
                            boolean r3 = r1.cancellationRequestedCalled     // Catch:{ all -> 0x001a }
                            if (r3 != 0) goto L_0x001c
                            j$.util.stream.DoubleStream r3 = r2.sequential()     // Catch:{ all -> 0x001a }
                            java.util.function.DoubleConsumer r0 = r1.downstreamAsDouble     // Catch:{ all -> 0x001a }
                            r3.forEach(r0)     // Catch:{ all -> 0x001a }
                            goto L_0x003e
                        L_0x001a:
                            r3 = move-exception
                            goto L_0x0035
                        L_0x001c:
                            j$.util.stream.DoubleStream r3 = r2.sequential()     // Catch:{ all -> 0x001a }
                            j$.util.Spliterator$OfDouble r3 = r3.spliterator()     // Catch:{ all -> 0x001a }
                        L_0x0024:
                            j$.util.stream.Sink r0 = r1.downstream     // Catch:{ all -> 0x001a }
                            boolean r0 = r0.cancellationRequested()     // Catch:{ all -> 0x001a }
                            if (r0 != 0) goto L_0x003e
                            java.util.function.DoubleConsumer r0 = r1.downstreamAsDouble     // Catch:{ all -> 0x001a }
                            boolean r0 = r3.tryAdvance((java.util.function.DoubleConsumer) r0)     // Catch:{ all -> 0x001a }
                            if (r0 != 0) goto L_0x0024
                            goto L_0x003e
                        L_0x0035:
                            r2.close()     // Catch:{ all -> 0x0039 }
                            goto L_0x003d
                        L_0x0039:
                            r2 = move-exception
                            j$.util.stream.Collectors$$ExternalSyntheticBackport0.m(r3, r2)
                        L_0x003d:
                            throw r3
                        L_0x003e:
                            if (r2 == 0) goto L_0x0043
                            r2.close()
                        L_0x0043:
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: j$.util.stream.DoublePipeline.AnonymousClass5.AnonymousClass1.accept(double):void");
                    }

                    public void begin(long j) {
                        this.downstream.begin(-1);
                    }

                    public boolean cancellationRequested() {
                        this.cancellationRequestedCalled = true;
                        return this.downstream.cancellationRequested();
                    }
                };
            }
        };
    }

    public void forEach(DoubleConsumer doubleConsumer) {
        evaluate(ForEachOps.makeDouble(doubleConsumer, false));
    }

    public void forEachOrdered(DoubleConsumer doubleConsumer) {
        evaluate(ForEachOps.makeDouble(doubleConsumer, true));
    }

    /* access modifiers changed from: package-private */
    public final boolean forEachWithCancel(Spliterator spliterator, Sink sink) {
        boolean cancellationRequested;
        Spliterator.OfDouble adapt = adapt(spliterator);
        DoubleConsumer adapt2 = adapt(sink);
        do {
            cancellationRequested = sink.cancellationRequested();
            if (cancellationRequested || !adapt.tryAdvance(adapt2)) {
                return cancellationRequested;
            }
            cancellationRequested = sink.cancellationRequested();
            break;
        } while (!adapt.tryAdvance(adapt2));
        return cancellationRequested;
    }

    /* access modifiers changed from: package-private */
    public final StreamShape getOutputShape() {
        return StreamShape.DOUBLE_VALUE;
    }

    public final PrimitiveIterator.OfDouble iterator() {
        return Spliterators.iterator(spliterator());
    }

    /* access modifiers changed from: package-private */
    public final Spliterator.OfDouble lazySpliterator(Supplier supplier) {
        return new StreamSpliterators$DelegatingSpliterator.OfDouble(supplier);
    }

    public final DoubleStream limit(long j) {
        if (j >= 0) {
            return SliceOps.makeDouble(this, 0, j);
        }
        throw new IllegalArgumentException(Long.toString(j));
    }

    /* access modifiers changed from: package-private */
    public final Node.Builder makeNodeBuilder(long j, IntFunction intFunction) {
        return Nodes.doubleBuilder(j);
    }

    public final DoubleStream map(DoubleUnaryOperator doubleUnaryOperator) {
        Objects.requireNonNull(doubleUnaryOperator);
        return new StatelessOp(this, StreamShape.DOUBLE_VALUE, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT, doubleUnaryOperator) {
            /* access modifiers changed from: package-private */
            public Sink opWrapSink(int i, Sink sink) {
                return new Sink.ChainedDouble(sink) {
                    /* JADX WARNING: type inference failed for: r0v1, types: [java.lang.Throwable, java.util.function.DoubleUnaryOperator] */
                    public void accept(double d) {
                        AnonymousClass2.this.getClass();
                        ? r0 = 0;
                        r0.applyAsDouble(d);
                        throw r0;
                    }
                };
            }
        };
    }

    public final IntStream mapToInt(DoubleToIntFunction doubleToIntFunction) {
        Objects.requireNonNull(doubleToIntFunction);
        return new IntPipeline.StatelessOp(this, StreamShape.DOUBLE_VALUE, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT, doubleToIntFunction) {
            /* access modifiers changed from: package-private */
            public Sink opWrapSink(int i, Sink sink) {
                return new Sink.ChainedDouble(sink) {
                    /* JADX WARNING: type inference failed for: r0v1, types: [java.lang.Throwable, java.util.function.DoubleToIntFunction] */
                    public void accept(double d) {
                        AnonymousClass3.this.getClass();
                        ? r0 = 0;
                        r0.applyAsInt(d);
                        throw r0;
                    }
                };
            }
        };
    }

    public final LongStream mapToLong(DoubleToLongFunction doubleToLongFunction) {
        Objects.requireNonNull(doubleToLongFunction);
        return new LongPipeline.StatelessOp(this, StreamShape.DOUBLE_VALUE, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT, doubleToLongFunction) {
            /* access modifiers changed from: package-private */
            public Sink opWrapSink(int i, Sink sink) {
                return new Sink.ChainedDouble(sink) {
                    /* JADX WARNING: type inference failed for: r0v1, types: [java.lang.Throwable, java.util.function.DoubleToLongFunction] */
                    public void accept(double d) {
                        AnonymousClass4.this.getClass();
                        ? r0 = 0;
                        r0.applyAsLong(d);
                        throw r0;
                    }
                };
            }
        };
    }

    public final Stream mapToObj(DoubleFunction doubleFunction) {
        Objects.requireNonNull(doubleFunction);
        return mapToObj(doubleFunction, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT);
    }

    public final OptionalDouble max() {
        return reduce(new DoublePipeline$$ExternalSyntheticLambda7());
    }

    public final OptionalDouble min() {
        return reduce(new DoublePipeline$$ExternalSyntheticLambda11());
    }

    public final boolean noneMatch(DoublePredicate doublePredicate) {
        return ((Boolean) evaluate(MatchOps.makeDouble(doublePredicate, MatchOps.MatchKind.NONE))).booleanValue();
    }

    public final DoubleStream peek(DoubleConsumer doubleConsumer) {
        Objects.requireNonNull(doubleConsumer);
        final DoubleConsumer doubleConsumer2 = doubleConsumer;
        return new StatelessOp(this, StreamShape.DOUBLE_VALUE, 0) {
            /* access modifiers changed from: package-private */
            public Sink opWrapSink(int i, Sink sink) {
                return new Sink.ChainedDouble(sink) {
                    public void accept(double d) {
                        doubleConsumer2.accept(d);
                        this.downstream.accept(d);
                    }
                };
            }
        };
    }

    public final double reduce(double d, DoubleBinaryOperator doubleBinaryOperator) {
        return ((Double) evaluate(ReduceOps.makeDouble(d, doubleBinaryOperator))).doubleValue();
    }

    public final OptionalDouble reduce(DoubleBinaryOperator doubleBinaryOperator) {
        return (OptionalDouble) evaluate(ReduceOps.makeDouble(doubleBinaryOperator));
    }

    public final DoubleStream skip(long j) {
        if (j >= 0) {
            return j == 0 ? this : SliceOps.makeDouble(this, j, -1);
        }
        throw new IllegalArgumentException(Long.toString(j));
    }

    public final DoubleStream sorted() {
        return SortedOps.makeDouble(this);
    }

    public final Spliterator.OfDouble spliterator() {
        return adapt(super.spliterator());
    }

    public final double sum() {
        return Collectors.computeFinalSum((double[]) collect(new DoublePipeline$$ExternalSyntheticLambda8(), new DoublePipeline$$ExternalSyntheticLambda9(), new DoublePipeline$$ExternalSyntheticLambda10()));
    }

    public final DoubleSummaryStatistics summaryStatistics() {
        return (DoubleSummaryStatistics) collect(new Collectors$$ExternalSyntheticLambda16(), new DoublePipeline$$ExternalSyntheticLambda1(), new DoublePipeline$$ExternalSyntheticLambda2());
    }

    public final double[] toArray() {
        return (double[]) Nodes.flattenDouble((Node.OfDouble) evaluateToArrayNode(new DoublePipeline$$ExternalSyntheticLambda5())).asPrimitiveArray();
    }

    public DoubleStream unordered() {
        return !isOrdered() ? this : new StatelessOp(this, StreamShape.DOUBLE_VALUE, StreamOpFlag.NOT_ORDERED) {
            /* access modifiers changed from: package-private */
            public Sink opWrapSink(int i, Sink sink) {
                return sink;
            }
        };
    }

    /* access modifiers changed from: package-private */
    public final Spliterator wrap(PipelineHelper pipelineHelper, Supplier supplier, boolean z) {
        return new StreamSpliterators$DoubleWrappingSpliterator(pipelineHelper, supplier, z);
    }
}
