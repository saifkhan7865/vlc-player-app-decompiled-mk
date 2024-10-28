package j$.util.stream;

import j$.util.LongSummaryStatistics;
import j$.util.Objects;
import j$.util.OptionalDouble;
import j$.util.OptionalLong;
import j$.util.PrimitiveIterator;
import j$.util.Spliterator;
import j$.util.Spliterators;
import j$.util.stream.DoublePipeline;
import j$.util.stream.IntPipeline;
import j$.util.stream.MatchOps;
import j$.util.stream.Node;
import j$.util.stream.ReferencePipeline;
import j$.util.stream.Sink;
import j$.util.stream.StreamSpliterators$DelegatingSpliterator;
import java.util.Iterator;
import java.util.function.BiConsumer;
import java.util.function.IntFunction;
import java.util.function.LongBinaryOperator;
import java.util.function.LongConsumer;
import java.util.function.LongFunction;
import java.util.function.LongPredicate;
import java.util.function.LongToDoubleFunction;
import java.util.function.LongToIntFunction;
import java.util.function.LongUnaryOperator;
import java.util.function.ObjLongConsumer;
import java.util.function.Supplier;

abstract class LongPipeline extends AbstractPipeline implements LongStream {

    static class Head extends LongPipeline {
        Head(Spliterator spliterator, int i, boolean z) {
            super(spliterator, i, z);
        }

        public void forEach(LongConsumer longConsumer) {
            if (!isParallel()) {
                LongPipeline.adapt(sourceStageSpliterator()).forEachRemaining(longConsumer);
            } else {
                LongPipeline.super.forEach(longConsumer);
            }
        }

        public void forEachOrdered(LongConsumer longConsumer) {
            if (!isParallel()) {
                LongPipeline.adapt(sourceStageSpliterator()).forEachRemaining(longConsumer);
            } else {
                LongPipeline.super.forEachOrdered(longConsumer);
            }
        }

        public /* bridge */ /* synthetic */ Iterator iterator() {
            return LongPipeline.super.iterator();
        }

        /* access modifiers changed from: package-private */
        public /* bridge */ /* synthetic */ Spliterator lazySpliterator(Supplier supplier) {
            return LongPipeline.super.lazySpliterator(supplier);
        }

        /* access modifiers changed from: package-private */
        public final boolean opIsStateful() {
            throw new UnsupportedOperationException();
        }

        /* access modifiers changed from: package-private */
        public final Sink opWrapSink(int i, Sink sink) {
            throw new UnsupportedOperationException();
        }

        public /* bridge */ /* synthetic */ LongStream parallel() {
            return (LongStream) super.parallel();
        }

        public /* bridge */ /* synthetic */ LongStream sequential() {
            return (LongStream) super.sequential();
        }

        public /* bridge */ /* synthetic */ Spliterator spliterator() {
            return LongPipeline.super.spliterator();
        }

        public /* bridge */ /* synthetic */ BaseStream unordered() {
            return LongPipeline.super.unordered();
        }
    }

    static abstract class StatefulOp extends LongPipeline {
        static {
            Class<LongPipeline> cls = LongPipeline.class;
        }

        StatefulOp(AbstractPipeline abstractPipeline, StreamShape streamShape, int i) {
            super(abstractPipeline, i);
        }

        public /* bridge */ /* synthetic */ Iterator iterator() {
            return LongPipeline.super.iterator();
        }

        /* access modifiers changed from: package-private */
        public /* bridge */ /* synthetic */ Spliterator lazySpliterator(Supplier supplier) {
            return LongPipeline.super.lazySpliterator(supplier);
        }

        /* access modifiers changed from: package-private */
        public final boolean opIsStateful() {
            return true;
        }

        public /* bridge */ /* synthetic */ LongStream parallel() {
            return (LongStream) super.parallel();
        }

        public /* bridge */ /* synthetic */ LongStream sequential() {
            return (LongStream) super.sequential();
        }

        public /* bridge */ /* synthetic */ Spliterator spliterator() {
            return LongPipeline.super.spliterator();
        }

        public /* bridge */ /* synthetic */ BaseStream unordered() {
            return LongPipeline.super.unordered();
        }
    }

    static abstract class StatelessOp extends LongPipeline {
        static {
            Class<LongPipeline> cls = LongPipeline.class;
        }

        StatelessOp(AbstractPipeline abstractPipeline, StreamShape streamShape, int i) {
            super(abstractPipeline, i);
        }

        public /* bridge */ /* synthetic */ Iterator iterator() {
            return LongPipeline.super.iterator();
        }

        /* access modifiers changed from: package-private */
        public /* bridge */ /* synthetic */ Spliterator lazySpliterator(Supplier supplier) {
            return LongPipeline.super.lazySpliterator(supplier);
        }

        /* access modifiers changed from: package-private */
        public final boolean opIsStateful() {
            return false;
        }

        public /* bridge */ /* synthetic */ LongStream parallel() {
            return (LongStream) super.parallel();
        }

        public /* bridge */ /* synthetic */ LongStream sequential() {
            return (LongStream) super.sequential();
        }

        public /* bridge */ /* synthetic */ Spliterator spliterator() {
            return LongPipeline.super.spliterator();
        }

        public /* bridge */ /* synthetic */ BaseStream unordered() {
            return LongPipeline.super.unordered();
        }
    }

    LongPipeline(Spliterator spliterator, int i, boolean z) {
        super(spliterator, i, z);
    }

    LongPipeline(AbstractPipeline abstractPipeline, int i) {
        super(abstractPipeline, i);
    }

    /* access modifiers changed from: private */
    public static Spliterator.OfLong adapt(Spliterator spliterator) {
        if (spliterator instanceof Spliterator.OfLong) {
            return (Spliterator.OfLong) spliterator;
        }
        if (Tripwire.ENABLED) {
            Tripwire.trip(AbstractPipeline.class, "using LongStream.adapt(Spliterator<Long> s)");
        }
        throw new UnsupportedOperationException("LongStream.adapt(Spliterator<Long> s)");
    }

    private static LongConsumer adapt(Sink sink) {
        if (sink instanceof LongConsumer) {
            return (LongConsumer) sink;
        }
        if (Tripwire.ENABLED) {
            Tripwire.trip(AbstractPipeline.class, "using LongStream.adapt(Sink<Long> s)");
        }
        Objects.requireNonNull(sink);
        return new LongPipeline$$ExternalSyntheticLambda12(sink);
    }

    static /* synthetic */ long[] lambda$average$1() {
        return new long[2];
    }

    static /* synthetic */ void lambda$average$2(long[] jArr, long j) {
        jArr[0] = jArr[0] + 1;
        jArr[1] = jArr[1] + j;
    }

    static /* synthetic */ void lambda$average$3(long[] jArr, long[] jArr2) {
        jArr[0] = jArr[0] + jArr2[0];
        jArr[1] = jArr[1] + jArr2[1];
    }

    static /* synthetic */ Long[] lambda$toArray$5(int i) {
        return new Long[i];
    }

    private Stream mapToObj(LongFunction longFunction, int i) {
        final LongFunction longFunction2 = longFunction;
        return new ReferencePipeline.StatelessOp(this, StreamShape.LONG_VALUE, i) {
            /* access modifiers changed from: package-private */
            public Sink opWrapSink(int i, Sink sink) {
                return new Sink.ChainedLong(sink) {
                    public void accept(long j) {
                        this.downstream.accept(longFunction2.apply(j));
                    }
                };
            }
        };
    }

    public final boolean allMatch(LongPredicate longPredicate) {
        return ((Boolean) evaluate(MatchOps.makeLong(longPredicate, MatchOps.MatchKind.ALL))).booleanValue();
    }

    public final boolean anyMatch(LongPredicate longPredicate) {
        return ((Boolean) evaluate(MatchOps.makeLong(longPredicate, MatchOps.MatchKind.ANY))).booleanValue();
    }

    public final DoubleStream asDoubleStream() {
        return new DoublePipeline.StatelessOp(this, StreamShape.LONG_VALUE, StreamOpFlag.NOT_DISTINCT) {
            /* access modifiers changed from: package-private */
            public Sink opWrapSink(int i, Sink sink) {
                return new Sink.ChainedLong(sink) {
                    public void accept(long j) {
                        this.downstream.accept((double) j);
                    }
                };
            }
        };
    }

    public final OptionalDouble average() {
        long[] jArr = (long[]) collect(new LongPipeline$$ExternalSyntheticLambda4(), new LongPipeline$$ExternalSyntheticLambda5(), new LongPipeline$$ExternalSyntheticLambda6());
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
        return mapToObj(new LongPipeline$$ExternalSyntheticLambda3(), 0);
    }

    public final Object collect(Supplier supplier, ObjLongConsumer objLongConsumer, BiConsumer biConsumer) {
        Objects.requireNonNull(biConsumer);
        return evaluate(ReduceOps.makeLong(supplier, objLongConsumer, new LongPipeline$$ExternalSyntheticLambda7(biConsumer)));
    }

    public final long count() {
        return ((Long) evaluate(ReduceOps.makeLongCounting())).longValue();
    }

    public final LongStream distinct() {
        return boxed().distinct().mapToLong(new LongPipeline$$ExternalSyntheticLambda11());
    }

    /* access modifiers changed from: package-private */
    public final Node evaluateToNode(PipelineHelper pipelineHelper, Spliterator spliterator, boolean z, IntFunction intFunction) {
        return Nodes.collectLong(pipelineHelper, spliterator, z);
    }

    public final LongStream filter(LongPredicate longPredicate) {
        Objects.requireNonNull(longPredicate);
        return new StatelessOp(this, StreamShape.LONG_VALUE, StreamOpFlag.NOT_SIZED, longPredicate) {
            /* access modifiers changed from: package-private */
            public Sink opWrapSink(int i, Sink sink) {
                return new Sink.ChainedLong(sink) {
                    /* JADX WARNING: type inference failed for: r0v1, types: [java.lang.Throwable, java.util.function.LongPredicate] */
                    public void accept(long j) {
                        AnonymousClass8.this.getClass();
                        ? r0 = 0;
                        r0.test(j);
                        throw r0;
                    }

                    public void begin(long j) {
                        this.downstream.begin(-1);
                    }
                };
            }
        };
    }

    public final OptionalLong findAny() {
        return (OptionalLong) evaluate(FindOps.makeLong(false));
    }

    public final OptionalLong findFirst() {
        return (OptionalLong) evaluate(FindOps.makeLong(true));
    }

    public final LongStream flatMap(LongFunction longFunction) {
        Objects.requireNonNull(longFunction);
        final LongFunction longFunction2 = longFunction;
        return new StatelessOp(this, StreamShape.LONG_VALUE, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT | StreamOpFlag.NOT_SIZED) {
            /* access modifiers changed from: package-private */
            public Sink opWrapSink(int i, Sink sink) {
                return new Sink.ChainedLong(sink) {
                    boolean cancellationRequestedCalled;
                    LongConsumer downstreamAsLong;

                    {
                        Sink sink = this.downstream;
                        Objects.requireNonNull(sink);
                        this.downstreamAsLong = new LongPipeline$$ExternalSyntheticLambda12(sink);
                    }

                    /* JADX WARNING: Removed duplicated region for block: B:9:0x0024 A[Catch:{ all -> 0x001a, all -> 0x0039 }, LOOP:0: B:9:0x0024->B:12:0x0032, LOOP_START] */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public void accept(long r2) {
                        /*
                            r1 = this;
                            j$.util.stream.LongPipeline$6 r0 = j$.util.stream.LongPipeline.AnonymousClass6.this
                            java.util.function.LongFunction r0 = r5
                            java.lang.Object r2 = r0.apply(r2)
                            j$.util.stream.LongStream r2 = (j$.util.stream.LongStream) r2
                            if (r2 == 0) goto L_0x003e
                            boolean r3 = r1.cancellationRequestedCalled     // Catch:{ all -> 0x001a }
                            if (r3 != 0) goto L_0x001c
                            j$.util.stream.LongStream r3 = r2.sequential()     // Catch:{ all -> 0x001a }
                            java.util.function.LongConsumer r0 = r1.downstreamAsLong     // Catch:{ all -> 0x001a }
                            r3.forEach(r0)     // Catch:{ all -> 0x001a }
                            goto L_0x003e
                        L_0x001a:
                            r3 = move-exception
                            goto L_0x0035
                        L_0x001c:
                            j$.util.stream.LongStream r3 = r2.sequential()     // Catch:{ all -> 0x001a }
                            j$.util.Spliterator$OfLong r3 = r3.spliterator()     // Catch:{ all -> 0x001a }
                        L_0x0024:
                            j$.util.stream.Sink r0 = r1.downstream     // Catch:{ all -> 0x001a }
                            boolean r0 = r0.cancellationRequested()     // Catch:{ all -> 0x001a }
                            if (r0 != 0) goto L_0x003e
                            java.util.function.LongConsumer r0 = r1.downstreamAsLong     // Catch:{ all -> 0x001a }
                            boolean r0 = r3.tryAdvance((java.util.function.LongConsumer) r0)     // Catch:{ all -> 0x001a }
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
                        throw new UnsupportedOperationException("Method not decompiled: j$.util.stream.LongPipeline.AnonymousClass6.AnonymousClass1.accept(long):void");
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

    public void forEach(LongConsumer longConsumer) {
        evaluate(ForEachOps.makeLong(longConsumer, false));
    }

    public void forEachOrdered(LongConsumer longConsumer) {
        evaluate(ForEachOps.makeLong(longConsumer, true));
    }

    /* access modifiers changed from: package-private */
    public final boolean forEachWithCancel(Spliterator spliterator, Sink sink) {
        boolean cancellationRequested;
        Spliterator.OfLong adapt = adapt(spliterator);
        LongConsumer adapt2 = adapt(sink);
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
        return StreamShape.LONG_VALUE;
    }

    public final PrimitiveIterator.OfLong iterator() {
        return Spliterators.iterator(spliterator());
    }

    /* access modifiers changed from: package-private */
    public final Spliterator.OfLong lazySpliterator(Supplier supplier) {
        return new StreamSpliterators$DelegatingSpliterator.OfLong(supplier);
    }

    public final LongStream limit(long j) {
        if (j >= 0) {
            return SliceOps.makeLong(this, 0, j);
        }
        throw new IllegalArgumentException(Long.toString(j));
    }

    /* access modifiers changed from: package-private */
    public final Node.Builder makeNodeBuilder(long j, IntFunction intFunction) {
        return Nodes.longBuilder(j);
    }

    public final LongStream map(LongUnaryOperator longUnaryOperator) {
        Objects.requireNonNull(longUnaryOperator);
        return new StatelessOp(this, StreamShape.LONG_VALUE, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT, longUnaryOperator) {
            /* access modifiers changed from: package-private */
            public Sink opWrapSink(int i, Sink sink) {
                return new Sink.ChainedLong(sink) {
                    /* JADX WARNING: type inference failed for: r0v1, types: [java.lang.Throwable, java.util.function.LongUnaryOperator] */
                    public void accept(long j) {
                        AnonymousClass3.this.getClass();
                        ? r0 = 0;
                        r0.applyAsLong(j);
                        throw r0;
                    }
                };
            }
        };
    }

    public final DoubleStream mapToDouble(LongToDoubleFunction longToDoubleFunction) {
        Objects.requireNonNull(longToDoubleFunction);
        return new DoublePipeline.StatelessOp(this, StreamShape.LONG_VALUE, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT, longToDoubleFunction) {
            /* access modifiers changed from: package-private */
            public Sink opWrapSink(int i, Sink sink) {
                return new Sink.ChainedLong(sink) {
                    /* JADX WARNING: type inference failed for: r0v1, types: [java.lang.Throwable, java.util.function.LongToDoubleFunction] */
                    public void accept(long j) {
                        AnonymousClass5.this.getClass();
                        ? r0 = 0;
                        r0.applyAsDouble(j);
                        throw r0;
                    }
                };
            }
        };
    }

    public final IntStream mapToInt(LongToIntFunction longToIntFunction) {
        Objects.requireNonNull(longToIntFunction);
        return new IntPipeline.StatelessOp(this, StreamShape.LONG_VALUE, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT, longToIntFunction) {
            /* access modifiers changed from: package-private */
            public Sink opWrapSink(int i, Sink sink) {
                return new Sink.ChainedLong(sink) {
                    /* JADX WARNING: type inference failed for: r0v1, types: [java.lang.Throwable, java.util.function.LongToIntFunction] */
                    public void accept(long j) {
                        AnonymousClass4.this.getClass();
                        ? r0 = 0;
                        r0.applyAsInt(j);
                        throw r0;
                    }
                };
            }
        };
    }

    public final Stream mapToObj(LongFunction longFunction) {
        Objects.requireNonNull(longFunction);
        return mapToObj(longFunction, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT);
    }

    public final OptionalLong max() {
        return reduce(new LongPipeline$$ExternalSyntheticLambda8());
    }

    public final OptionalLong min() {
        return reduce(new LongPipeline$$ExternalSyntheticLambda10());
    }

    public final boolean noneMatch(LongPredicate longPredicate) {
        return ((Boolean) evaluate(MatchOps.makeLong(longPredicate, MatchOps.MatchKind.NONE))).booleanValue();
    }

    public final LongStream peek(LongConsumer longConsumer) {
        Objects.requireNonNull(longConsumer);
        final LongConsumer longConsumer2 = longConsumer;
        return new StatelessOp(this, StreamShape.LONG_VALUE, 0) {
            /* access modifiers changed from: package-private */
            public Sink opWrapSink(int i, Sink sink) {
                return new Sink.ChainedLong(sink) {
                    public void accept(long j) {
                        longConsumer2.accept(j);
                        this.downstream.accept(j);
                    }
                };
            }
        };
    }

    public final long reduce(long j, LongBinaryOperator longBinaryOperator) {
        return ((Long) evaluate(ReduceOps.makeLong(j, longBinaryOperator))).longValue();
    }

    public final OptionalLong reduce(LongBinaryOperator longBinaryOperator) {
        return (OptionalLong) evaluate(ReduceOps.makeLong(longBinaryOperator));
    }

    public final LongStream skip(long j) {
        if (j >= 0) {
            return j == 0 ? this : SliceOps.makeLong(this, j, -1);
        }
        throw new IllegalArgumentException(Long.toString(j));
    }

    public final LongStream sorted() {
        return SortedOps.makeLong(this);
    }

    public final Spliterator.OfLong spliterator() {
        return adapt(super.spliterator());
    }

    public final long sum() {
        return reduce(0, new LongPipeline$$ExternalSyntheticLambda9());
    }

    public final LongSummaryStatistics summaryStatistics() {
        return (LongSummaryStatistics) collect(new Collectors$$ExternalSyntheticLambda62(), new LongPipeline$$ExternalSyntheticLambda0(), new LongPipeline$$ExternalSyntheticLambda1());
    }

    public final long[] toArray() {
        return (long[]) Nodes.flattenLong((Node.OfLong) evaluateToArrayNode(new LongPipeline$$ExternalSyntheticLambda2())).asPrimitiveArray();
    }

    public LongStream unordered() {
        return !isOrdered() ? this : new StatelessOp(this, StreamShape.LONG_VALUE, StreamOpFlag.NOT_ORDERED) {
            /* access modifiers changed from: package-private */
            public Sink opWrapSink(int i, Sink sink) {
                return sink;
            }
        };
    }

    /* access modifiers changed from: package-private */
    public final Spliterator wrap(PipelineHelper pipelineHelper, Supplier supplier, boolean z) {
        return new StreamSpliterators$LongWrappingSpliterator(pipelineHelper, supplier, z);
    }
}
