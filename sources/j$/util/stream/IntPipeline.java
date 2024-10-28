package j$.util.stream;

import j$.util.IntSummaryStatistics;
import j$.util.Objects;
import j$.util.OptionalDouble;
import j$.util.OptionalInt;
import j$.util.PrimitiveIterator;
import j$.util.Spliterator;
import j$.util.Spliterators;
import j$.util.stream.DoublePipeline;
import j$.util.stream.LongPipeline;
import j$.util.stream.MatchOps;
import j$.util.stream.Node;
import j$.util.stream.ReferencePipeline;
import j$.util.stream.Sink;
import j$.util.stream.StreamSpliterators$DelegatingSpliterator;
import java.util.Iterator;
import java.util.function.BiConsumer;
import java.util.function.IntBinaryOperator;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.ObjIntConsumer;
import java.util.function.Supplier;

abstract class IntPipeline extends AbstractPipeline implements IntStream {

    static class Head extends IntPipeline {
        Head(Spliterator spliterator, int i, boolean z) {
            super(spliterator, i, z);
        }

        public void forEach(IntConsumer intConsumer) {
            if (!isParallel()) {
                IntPipeline.adapt(sourceStageSpliterator()).forEachRemaining(intConsumer);
            } else {
                IntPipeline.super.forEach(intConsumer);
            }
        }

        public void forEachOrdered(IntConsumer intConsumer) {
            if (!isParallel()) {
                IntPipeline.adapt(sourceStageSpliterator()).forEachRemaining(intConsumer);
            } else {
                IntPipeline.super.forEachOrdered(intConsumer);
            }
        }

        public /* bridge */ /* synthetic */ Iterator iterator() {
            return IntPipeline.super.iterator();
        }

        /* access modifiers changed from: package-private */
        public /* bridge */ /* synthetic */ Spliterator lazySpliterator(Supplier supplier) {
            return IntPipeline.super.lazySpliterator(supplier);
        }

        /* access modifiers changed from: package-private */
        public final boolean opIsStateful() {
            throw new UnsupportedOperationException();
        }

        /* access modifiers changed from: package-private */
        public final Sink opWrapSink(int i, Sink sink) {
            throw new UnsupportedOperationException();
        }

        public /* bridge */ /* synthetic */ IntStream parallel() {
            return (IntStream) super.parallel();
        }

        public /* bridge */ /* synthetic */ IntStream sequential() {
            return (IntStream) super.sequential();
        }

        public /* bridge */ /* synthetic */ Spliterator spliterator() {
            return IntPipeline.super.spliterator();
        }

        public /* bridge */ /* synthetic */ BaseStream unordered() {
            return IntPipeline.super.unordered();
        }
    }

    static abstract class StatefulOp extends IntPipeline {
        static {
            Class<IntPipeline> cls = IntPipeline.class;
        }

        StatefulOp(AbstractPipeline abstractPipeline, StreamShape streamShape, int i) {
            super(abstractPipeline, i);
        }

        public /* bridge */ /* synthetic */ Iterator iterator() {
            return IntPipeline.super.iterator();
        }

        /* access modifiers changed from: package-private */
        public /* bridge */ /* synthetic */ Spliterator lazySpliterator(Supplier supplier) {
            return IntPipeline.super.lazySpliterator(supplier);
        }

        /* access modifiers changed from: package-private */
        public final boolean opIsStateful() {
            return true;
        }

        public /* bridge */ /* synthetic */ IntStream parallel() {
            return (IntStream) super.parallel();
        }

        public /* bridge */ /* synthetic */ IntStream sequential() {
            return (IntStream) super.sequential();
        }

        public /* bridge */ /* synthetic */ Spliterator spliterator() {
            return IntPipeline.super.spliterator();
        }

        public /* bridge */ /* synthetic */ BaseStream unordered() {
            return IntPipeline.super.unordered();
        }
    }

    static abstract class StatelessOp extends IntPipeline {
        static {
            Class<IntPipeline> cls = IntPipeline.class;
        }

        StatelessOp(AbstractPipeline abstractPipeline, StreamShape streamShape, int i) {
            super(abstractPipeline, i);
        }

        public /* bridge */ /* synthetic */ Iterator iterator() {
            return IntPipeline.super.iterator();
        }

        /* access modifiers changed from: package-private */
        public /* bridge */ /* synthetic */ Spliterator lazySpliterator(Supplier supplier) {
            return IntPipeline.super.lazySpliterator(supplier);
        }

        /* access modifiers changed from: package-private */
        public final boolean opIsStateful() {
            return false;
        }

        public /* bridge */ /* synthetic */ IntStream parallel() {
            return (IntStream) super.parallel();
        }

        public /* bridge */ /* synthetic */ IntStream sequential() {
            return (IntStream) super.sequential();
        }

        public /* bridge */ /* synthetic */ Spliterator spliterator() {
            return IntPipeline.super.spliterator();
        }

        public /* bridge */ /* synthetic */ BaseStream unordered() {
            return IntPipeline.super.unordered();
        }
    }

    IntPipeline(Spliterator spliterator, int i, boolean z) {
        super(spliterator, i, z);
    }

    IntPipeline(AbstractPipeline abstractPipeline, int i) {
        super(abstractPipeline, i);
    }

    /* access modifiers changed from: private */
    public static Spliterator.OfInt adapt(Spliterator spliterator) {
        if (spliterator instanceof Spliterator.OfInt) {
            return (Spliterator.OfInt) spliterator;
        }
        if (Tripwire.ENABLED) {
            Tripwire.trip(AbstractPipeline.class, "using IntStream.adapt(Spliterator<Integer> s)");
        }
        throw new UnsupportedOperationException("IntStream.adapt(Spliterator<Integer> s)");
    }

    private static IntConsumer adapt(Sink sink) {
        if (sink instanceof IntConsumer) {
            return (IntConsumer) sink;
        }
        if (Tripwire.ENABLED) {
            Tripwire.trip(AbstractPipeline.class, "using IntStream.adapt(Sink<Integer> s)");
        }
        Objects.requireNonNull(sink);
        return new IntPipeline$$ExternalSyntheticLambda10(sink);
    }

    static /* synthetic */ long[] lambda$average$1() {
        return new long[2];
    }

    static /* synthetic */ void lambda$average$2(long[] jArr, int i) {
        jArr[0] = jArr[0] + 1;
        jArr[1] = jArr[1] + ((long) i);
    }

    static /* synthetic */ void lambda$average$3(long[] jArr, long[] jArr2) {
        jArr[0] = jArr[0] + jArr2[0];
        jArr[1] = jArr[1] + jArr2[1];
    }

    static /* synthetic */ Integer[] lambda$toArray$5(int i) {
        return new Integer[i];
    }

    private Stream mapToObj(IntFunction intFunction, int i) {
        final IntFunction intFunction2 = intFunction;
        return new ReferencePipeline.StatelessOp(this, StreamShape.INT_VALUE, i) {
            /* access modifiers changed from: package-private */
            public Sink opWrapSink(int i, Sink sink) {
                return new Sink.ChainedInt(sink) {
                    public void accept(int i) {
                        this.downstream.accept(intFunction2.apply(i));
                    }
                };
            }
        };
    }

    public final boolean allMatch(IntPredicate intPredicate) {
        return ((Boolean) evaluate(MatchOps.makeInt(intPredicate, MatchOps.MatchKind.ALL))).booleanValue();
    }

    public final boolean anyMatch(IntPredicate intPredicate) {
        return ((Boolean) evaluate(MatchOps.makeInt(intPredicate, MatchOps.MatchKind.ANY))).booleanValue();
    }

    public final DoubleStream asDoubleStream() {
        return new DoublePipeline.StatelessOp(this, StreamShape.INT_VALUE, 0) {
            /* access modifiers changed from: package-private */
            public Sink opWrapSink(int i, Sink sink) {
                return new Sink.ChainedInt(sink) {
                    public void accept(int i) {
                        this.downstream.accept((double) i);
                    }
                };
            }
        };
    }

    public final LongStream asLongStream() {
        return new LongPipeline.StatelessOp(this, StreamShape.INT_VALUE, 0) {
            /* access modifiers changed from: package-private */
            public Sink opWrapSink(int i, Sink sink) {
                return new Sink.ChainedInt(sink) {
                    public void accept(int i) {
                        this.downstream.accept((long) i);
                    }
                };
            }
        };
    }

    public final OptionalDouble average() {
        long[] jArr = (long[]) collect(new IntPipeline$$ExternalSyntheticLambda7(), new IntPipeline$$ExternalSyntheticLambda8(), new IntPipeline$$ExternalSyntheticLambda9());
        long j = jArr[0];
        if (j <= 0) {
            return OptionalDouble.empty();
        }
        double d = (double) jArr[1];
        double d2 = (double) j;
        Double.isNaN(d);
        Double.isNaN(d2);
        return OptionalDouble.of(d / d2);
    }

    public final Stream boxed() {
        return mapToObj(new IntPipeline$$ExternalSyntheticLambda12(), 0);
    }

    public final Object collect(Supplier supplier, ObjIntConsumer objIntConsumer, BiConsumer biConsumer) {
        Objects.requireNonNull(biConsumer);
        return evaluate(ReduceOps.makeInt(supplier, objIntConsumer, new IntPipeline$$ExternalSyntheticLambda4(biConsumer)));
    }

    public final long count() {
        return ((Long) evaluate(ReduceOps.makeIntCounting())).longValue();
    }

    public final IntStream distinct() {
        return boxed().distinct().mapToInt(new IntPipeline$$ExternalSyntheticLambda11());
    }

    /* access modifiers changed from: package-private */
    public final Node evaluateToNode(PipelineHelper pipelineHelper, Spliterator spliterator, boolean z, IntFunction intFunction) {
        return Nodes.collectInt(pipelineHelper, spliterator, z);
    }

    public final IntStream filter(IntPredicate intPredicate) {
        Objects.requireNonNull(intPredicate);
        return new StatelessOp(this, StreamShape.INT_VALUE, StreamOpFlag.NOT_SIZED, intPredicate) {
            /* access modifiers changed from: package-private */
            public Sink opWrapSink(int i, Sink sink) {
                return new Sink.ChainedInt(sink) {
                    /* JADX WARNING: type inference failed for: r0v1, types: [java.lang.Throwable, java.util.function.IntPredicate] */
                    public void accept(int i) {
                        AnonymousClass9.this.getClass();
                        ? r0 = 0;
                        r0.test(i);
                        throw r0;
                    }

                    public void begin(long j) {
                        this.downstream.begin(-1);
                    }
                };
            }
        };
    }

    public final OptionalInt findAny() {
        return (OptionalInt) evaluate(FindOps.makeInt(false));
    }

    public final OptionalInt findFirst() {
        return (OptionalInt) evaluate(FindOps.makeInt(true));
    }

    public final IntStream flatMap(IntFunction intFunction) {
        Objects.requireNonNull(intFunction);
        final IntFunction intFunction2 = intFunction;
        return new StatelessOp(this, StreamShape.INT_VALUE, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT | StreamOpFlag.NOT_SIZED) {
            /* access modifiers changed from: package-private */
            public Sink opWrapSink(int i, Sink sink) {
                return new Sink.ChainedInt(sink) {
                    boolean cancellationRequestedCalled;
                    IntConsumer downstreamAsInt;

                    {
                        Sink sink = this.downstream;
                        Objects.requireNonNull(sink);
                        this.downstreamAsInt = new IntPipeline$$ExternalSyntheticLambda10(sink);
                    }

                    /* JADX WARNING: Removed duplicated region for block: B:9:0x0024 A[Catch:{ all -> 0x001a, all -> 0x0039 }, LOOP:0: B:9:0x0024->B:12:0x0032, LOOP_START] */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public void accept(int r3) {
                        /*
                            r2 = this;
                            j$.util.stream.IntPipeline$7 r0 = j$.util.stream.IntPipeline.AnonymousClass7.this
                            java.util.function.IntFunction r0 = r5
                            java.lang.Object r3 = r0.apply(r3)
                            j$.util.stream.IntStream r3 = (j$.util.stream.IntStream) r3
                            if (r3 == 0) goto L_0x003e
                            boolean r0 = r2.cancellationRequestedCalled     // Catch:{ all -> 0x001a }
                            if (r0 != 0) goto L_0x001c
                            j$.util.stream.IntStream r0 = r3.sequential()     // Catch:{ all -> 0x001a }
                            java.util.function.IntConsumer r1 = r2.downstreamAsInt     // Catch:{ all -> 0x001a }
                            r0.forEach(r1)     // Catch:{ all -> 0x001a }
                            goto L_0x003e
                        L_0x001a:
                            r0 = move-exception
                            goto L_0x0035
                        L_0x001c:
                            j$.util.stream.IntStream r0 = r3.sequential()     // Catch:{ all -> 0x001a }
                            j$.util.Spliterator$OfInt r0 = r0.spliterator()     // Catch:{ all -> 0x001a }
                        L_0x0024:
                            j$.util.stream.Sink r1 = r2.downstream     // Catch:{ all -> 0x001a }
                            boolean r1 = r1.cancellationRequested()     // Catch:{ all -> 0x001a }
                            if (r1 != 0) goto L_0x003e
                            java.util.function.IntConsumer r1 = r2.downstreamAsInt     // Catch:{ all -> 0x001a }
                            boolean r1 = r0.tryAdvance((java.util.function.IntConsumer) r1)     // Catch:{ all -> 0x001a }
                            if (r1 != 0) goto L_0x0024
                            goto L_0x003e
                        L_0x0035:
                            r3.close()     // Catch:{ all -> 0x0039 }
                            goto L_0x003d
                        L_0x0039:
                            r3 = move-exception
                            j$.util.stream.Collectors$$ExternalSyntheticBackport0.m(r0, r3)
                        L_0x003d:
                            throw r0
                        L_0x003e:
                            if (r3 == 0) goto L_0x0043
                            r3.close()
                        L_0x0043:
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: j$.util.stream.IntPipeline.AnonymousClass7.AnonymousClass1.accept(int):void");
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

    public void forEach(IntConsumer intConsumer) {
        evaluate(ForEachOps.makeInt(intConsumer, false));
    }

    public void forEachOrdered(IntConsumer intConsumer) {
        evaluate(ForEachOps.makeInt(intConsumer, true));
    }

    /* access modifiers changed from: package-private */
    public final boolean forEachWithCancel(Spliterator spliterator, Sink sink) {
        boolean cancellationRequested;
        Spliterator.OfInt adapt = adapt(spliterator);
        IntConsumer adapt2 = adapt(sink);
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
        return StreamShape.INT_VALUE;
    }

    public final PrimitiveIterator.OfInt iterator() {
        return Spliterators.iterator(spliterator());
    }

    /* access modifiers changed from: package-private */
    public final Spliterator.OfInt lazySpliterator(Supplier supplier) {
        return new StreamSpliterators$DelegatingSpliterator.OfInt(supplier);
    }

    public final IntStream limit(long j) {
        if (j >= 0) {
            return SliceOps.makeInt(this, 0, j);
        }
        throw new IllegalArgumentException(Long.toString(j));
    }

    /* access modifiers changed from: package-private */
    public final Node.Builder makeNodeBuilder(long j, IntFunction intFunction) {
        return Nodes.intBuilder(j);
    }

    public final IntStream map(IntUnaryOperator intUnaryOperator) {
        Objects.requireNonNull(intUnaryOperator);
        return new StatelessOp(this, StreamShape.INT_VALUE, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT, intUnaryOperator) {
            /* access modifiers changed from: package-private */
            public Sink opWrapSink(int i, Sink sink) {
                return new Sink.ChainedInt(sink) {
                    /* JADX WARNING: type inference failed for: r0v1, types: [java.lang.Throwable, java.util.function.IntUnaryOperator] */
                    public void accept(int i) {
                        AnonymousClass4.this.getClass();
                        ? r0 = 0;
                        r0.applyAsInt(i);
                        throw r0;
                    }
                };
            }
        };
    }

    public final DoubleStream mapToDouble(IntToDoubleFunction intToDoubleFunction) {
        Objects.requireNonNull(intToDoubleFunction);
        return new DoublePipeline.StatelessOp(this, StreamShape.INT_VALUE, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT, intToDoubleFunction) {
            /* access modifiers changed from: package-private */
            public Sink opWrapSink(int i, Sink sink) {
                return new Sink.ChainedInt(sink) {
                    /* JADX WARNING: type inference failed for: r0v1, types: [java.lang.Throwable, java.util.function.IntToDoubleFunction] */
                    public void accept(int i) {
                        AnonymousClass6.this.getClass();
                        ? r0 = 0;
                        r0.applyAsDouble(i);
                        throw r0;
                    }
                };
            }
        };
    }

    public final LongStream mapToLong(IntToLongFunction intToLongFunction) {
        Objects.requireNonNull(intToLongFunction);
        return new LongPipeline.StatelessOp(this, StreamShape.INT_VALUE, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT, intToLongFunction) {
            /* access modifiers changed from: package-private */
            public Sink opWrapSink(int i, Sink sink) {
                return new Sink.ChainedInt(sink) {
                    /* JADX WARNING: type inference failed for: r0v1, types: [java.lang.Throwable, java.util.function.IntToLongFunction] */
                    public void accept(int i) {
                        AnonymousClass5.this.getClass();
                        ? r0 = 0;
                        r0.applyAsLong(i);
                        throw r0;
                    }
                };
            }
        };
    }

    public final Stream mapToObj(IntFunction intFunction) {
        Objects.requireNonNull(intFunction);
        return mapToObj(intFunction, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT);
    }

    public final OptionalInt max() {
        return reduce(new IntPipeline$$ExternalSyntheticLambda6());
    }

    public final OptionalInt min() {
        return reduce(new IntPipeline$$ExternalSyntheticLambda1());
    }

    public final boolean noneMatch(IntPredicate intPredicate) {
        return ((Boolean) evaluate(MatchOps.makeInt(intPredicate, MatchOps.MatchKind.NONE))).booleanValue();
    }

    public final IntStream peek(IntConsumer intConsumer) {
        Objects.requireNonNull(intConsumer);
        final IntConsumer intConsumer2 = intConsumer;
        return new StatelessOp(this, StreamShape.INT_VALUE, 0) {
            /* access modifiers changed from: package-private */
            public Sink opWrapSink(int i, Sink sink) {
                return new Sink.ChainedInt(sink) {
                    public void accept(int i) {
                        intConsumer2.accept(i);
                        this.downstream.accept(i);
                    }
                };
            }
        };
    }

    public final int reduce(int i, IntBinaryOperator intBinaryOperator) {
        return ((Integer) evaluate(ReduceOps.makeInt(i, intBinaryOperator))).intValue();
    }

    public final OptionalInt reduce(IntBinaryOperator intBinaryOperator) {
        return (OptionalInt) evaluate(ReduceOps.makeInt(intBinaryOperator));
    }

    public final IntStream skip(long j) {
        if (j >= 0) {
            return j == 0 ? this : SliceOps.makeInt(this, j, -1);
        }
        throw new IllegalArgumentException(Long.toString(j));
    }

    public final IntStream sorted() {
        return SortedOps.makeInt(this);
    }

    public final Spliterator.OfInt spliterator() {
        return adapt(super.spliterator());
    }

    public final int sum() {
        return reduce(0, new IntPipeline$$ExternalSyntheticLambda5());
    }

    public final IntSummaryStatistics summaryStatistics() {
        return (IntSummaryStatistics) collect(new Collectors$$ExternalSyntheticLambda28(), new IntPipeline$$ExternalSyntheticLambda2(), new IntPipeline$$ExternalSyntheticLambda3());
    }

    public final int[] toArray() {
        return (int[]) Nodes.flattenInt((Node.OfInt) evaluateToArrayNode(new IntPipeline$$ExternalSyntheticLambda0())).asPrimitiveArray();
    }

    public IntStream unordered() {
        return !isOrdered() ? this : new StatelessOp(this, StreamShape.INT_VALUE, StreamOpFlag.NOT_ORDERED) {
            /* access modifiers changed from: package-private */
            public Sink opWrapSink(int i, Sink sink) {
                return sink;
            }
        };
    }

    /* access modifiers changed from: package-private */
    public final Spliterator wrap(PipelineHelper pipelineHelper, Supplier supplier, boolean z) {
        return new StreamSpliterators$IntWrappingSpliterator(pipelineHelper, supplier, z);
    }
}
