package j$.util.stream;

import j$.util.Objects;
import j$.util.Optional;
import j$.util.Spliterator;
import j$.util.Spliterators;
import j$.util.function.BinaryOperator$CC;
import j$.util.stream.Collector;
import j$.util.stream.DoublePipeline;
import j$.util.stream.IntPipeline;
import j$.util.stream.LongPipeline;
import j$.util.stream.MatchOps;
import j$.util.stream.Node;
import j$.util.stream.Sink;
import java.util.Comparator;
import java.util.Iterator;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.LongConsumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

abstract class ReferencePipeline extends AbstractPipeline implements Stream {

    static class Head extends ReferencePipeline {
        Head(Spliterator spliterator, int i, boolean z) {
            super(spliterator, i, z);
        }

        Head(Supplier supplier, int i, boolean z) {
            super(supplier, i, z);
        }

        public void forEach(Consumer consumer) {
            if (!isParallel()) {
                sourceStageSpliterator().forEachRemaining(consumer);
            } else {
                ReferencePipeline.super.forEach(consumer);
            }
        }

        public void forEachOrdered(Consumer consumer) {
            if (!isParallel()) {
                sourceStageSpliterator().forEachRemaining(consumer);
            } else {
                ReferencePipeline.super.forEachOrdered(consumer);
            }
        }

        /* access modifiers changed from: package-private */
        public final boolean opIsStateful() {
            throw new UnsupportedOperationException();
        }

        /* access modifiers changed from: package-private */
        public final Sink opWrapSink(int i, Sink sink) {
            throw new UnsupportedOperationException();
        }

        public /* bridge */ /* synthetic */ BaseStream unordered() {
            return ReferencePipeline.super.unordered();
        }
    }

    static abstract class StatefulOp extends ReferencePipeline {
        static {
            Class<ReferencePipeline> cls = ReferencePipeline.class;
        }

        StatefulOp(AbstractPipeline abstractPipeline, StreamShape streamShape, int i) {
            super(abstractPipeline, i);
        }

        /* access modifiers changed from: package-private */
        public final boolean opIsStateful() {
            return true;
        }

        public /* bridge */ /* synthetic */ BaseStream unordered() {
            return ReferencePipeline.super.unordered();
        }
    }

    static abstract class StatelessOp extends ReferencePipeline {
        static {
            Class<ReferencePipeline> cls = ReferencePipeline.class;
        }

        StatelessOp(AbstractPipeline abstractPipeline, StreamShape streamShape, int i) {
            super(abstractPipeline, i);
        }

        /* access modifiers changed from: package-private */
        public final boolean opIsStateful() {
            return false;
        }

        public /* bridge */ /* synthetic */ BaseStream unordered() {
            return ReferencePipeline.super.unordered();
        }
    }

    ReferencePipeline(Spliterator spliterator, int i, boolean z) {
        super(spliterator, i, z);
    }

    ReferencePipeline(AbstractPipeline abstractPipeline, int i) {
        super(abstractPipeline, i);
    }

    ReferencePipeline(Supplier supplier, int i, boolean z) {
        super(supplier, i, z);
    }

    static /* synthetic */ Object[] lambda$toArray$0(int i) {
        return new Object[i];
    }

    public final boolean allMatch(Predicate predicate) {
        return ((Boolean) evaluate(MatchOps.makeRef(predicate, MatchOps.MatchKind.ALL))).booleanValue();
    }

    public final boolean anyMatch(Predicate predicate) {
        return ((Boolean) evaluate(MatchOps.makeRef(predicate, MatchOps.MatchKind.ANY))).booleanValue();
    }

    public final Object collect(Collector collector) {
        Object obj;
        if (!isParallel() || !collector.characteristics().contains(Collector.Characteristics.CONCURRENT) || (isOrdered() && !collector.characteristics().contains(Collector.Characteristics.UNORDERED))) {
            obj = evaluate(ReduceOps.makeRef(collector));
        } else {
            obj = collector.supplier().get();
            forEach(new ReferencePipeline$$ExternalSyntheticLambda0(collector.accumulator(), obj));
        }
        return collector.characteristics().contains(Collector.Characteristics.IDENTITY_FINISH) ? obj : collector.finisher().apply(obj);
    }

    public final Object collect(Supplier supplier, BiConsumer biConsumer, BiConsumer biConsumer2) {
        return evaluate(ReduceOps.makeRef(supplier, biConsumer, biConsumer2));
    }

    public final long count() {
        return ((Long) evaluate(ReduceOps.makeRefCounting())).longValue();
    }

    public final Stream distinct() {
        return DistinctOps.makeRef(this);
    }

    public final Stream dropWhile(Predicate predicate) {
        return WhileOps.makeDropWhileRef(this, predicate);
    }

    /* access modifiers changed from: package-private */
    public final Node evaluateToNode(PipelineHelper pipelineHelper, Spliterator spliterator, boolean z, IntFunction intFunction) {
        return Nodes.collect(pipelineHelper, spliterator, z, intFunction);
    }

    public final Stream filter(Predicate predicate) {
        Objects.requireNonNull(predicate);
        final Predicate predicate2 = predicate;
        return new StatelessOp(this, StreamShape.REFERENCE, StreamOpFlag.NOT_SIZED) {
            /* access modifiers changed from: package-private */
            public Sink opWrapSink(int i, Sink sink) {
                return new Sink.ChainedReference(sink) {
                    public void accept(Object obj) {
                        if (predicate2.test(obj)) {
                            this.downstream.accept(obj);
                        }
                    }

                    public void begin(long j) {
                        this.downstream.begin(-1);
                    }
                };
            }
        };
    }

    public final Optional findAny() {
        return (Optional) evaluate(FindOps.makeRef(false));
    }

    public final Optional findFirst() {
        return (Optional) evaluate(FindOps.makeRef(true));
    }

    public final Stream flatMap(Function function) {
        Objects.requireNonNull(function);
        final Function function2 = function;
        return new StatelessOp(this, StreamShape.REFERENCE, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT | StreamOpFlag.NOT_SIZED) {
            /* access modifiers changed from: package-private */
            public Sink opWrapSink(int i, Sink sink) {
                return new Sink.ChainedReference(sink) {
                    boolean cancellationRequestedCalled;

                    /* JADX WARNING: Removed duplicated region for block: B:9:0x0028 A[Catch:{ all -> 0x001c, all -> 0x003d }, LOOP:0: B:9:0x0028->B:12:0x0036, LOOP_START] */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public void accept(java.lang.Object r3) {
                        /*
                            r2 = this;
                            j$.util.stream.ReferencePipeline$7 r0 = j$.util.stream.ReferencePipeline.AnonymousClass7.this
                            java.util.function.Function r0 = r5
                            java.lang.Object r3 = r0.apply(r3)
                            j$.util.stream.Stream r3 = (j$.util.stream.Stream) r3
                            if (r3 == 0) goto L_0x0042
                            boolean r0 = r2.cancellationRequestedCalled     // Catch:{ all -> 0x001c }
                            if (r0 != 0) goto L_0x001e
                            j$.util.stream.BaseStream r0 = r3.sequential()     // Catch:{ all -> 0x001c }
                            j$.util.stream.Stream r0 = (j$.util.stream.Stream) r0     // Catch:{ all -> 0x001c }
                            j$.util.stream.Sink r1 = r2.downstream     // Catch:{ all -> 0x001c }
                            r0.forEach(r1)     // Catch:{ all -> 0x001c }
                            goto L_0x0042
                        L_0x001c:
                            r0 = move-exception
                            goto L_0x0039
                        L_0x001e:
                            j$.util.stream.BaseStream r0 = r3.sequential()     // Catch:{ all -> 0x001c }
                            j$.util.stream.Stream r0 = (j$.util.stream.Stream) r0     // Catch:{ all -> 0x001c }
                            j$.util.Spliterator r0 = r0.spliterator()     // Catch:{ all -> 0x001c }
                        L_0x0028:
                            j$.util.stream.Sink r1 = r2.downstream     // Catch:{ all -> 0x001c }
                            boolean r1 = r1.cancellationRequested()     // Catch:{ all -> 0x001c }
                            if (r1 != 0) goto L_0x0042
                            j$.util.stream.Sink r1 = r2.downstream     // Catch:{ all -> 0x001c }
                            boolean r1 = r0.tryAdvance(r1)     // Catch:{ all -> 0x001c }
                            if (r1 != 0) goto L_0x0028
                            goto L_0x0042
                        L_0x0039:
                            r3.close()     // Catch:{ all -> 0x003d }
                            goto L_0x0041
                        L_0x003d:
                            r3 = move-exception
                            j$.util.stream.Collectors$$ExternalSyntheticBackport0.m(r0, r3)
                        L_0x0041:
                            throw r0
                        L_0x0042:
                            if (r3 == 0) goto L_0x0047
                            r3.close()
                        L_0x0047:
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: j$.util.stream.ReferencePipeline.AnonymousClass7.AnonymousClass1.accept(java.lang.Object):void");
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

    public final DoubleStream flatMapToDouble(Function function) {
        Objects.requireNonNull(function);
        final Function function2 = function;
        return new DoublePipeline.StatelessOp(this, StreamShape.REFERENCE, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT | StreamOpFlag.NOT_SIZED) {
            /* access modifiers changed from: package-private */
            public Sink opWrapSink(int i, Sink sink) {
                return new Sink.ChainedReference(sink) {
                    boolean cancellationRequestedCalled;
                    DoubleConsumer downstreamAsDouble;

                    {
                        Sink sink = this.downstream;
                        Objects.requireNonNull(sink);
                        this.downstreamAsDouble = new DoublePipeline$$ExternalSyntheticLambda0(sink);
                    }

                    /* JADX WARNING: Removed duplicated region for block: B:9:0x0024 A[Catch:{ all -> 0x001a, all -> 0x0039 }, LOOP:0: B:9:0x0024->B:12:0x0032, LOOP_START] */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public void accept(java.lang.Object r3) {
                        /*
                            r2 = this;
                            j$.util.stream.ReferencePipeline$9 r0 = j$.util.stream.ReferencePipeline.AnonymousClass9.this
                            java.util.function.Function r0 = r5
                            java.lang.Object r3 = r0.apply(r3)
                            j$.util.stream.DoubleStream r3 = (j$.util.stream.DoubleStream) r3
                            if (r3 == 0) goto L_0x003e
                            boolean r0 = r2.cancellationRequestedCalled     // Catch:{ all -> 0x001a }
                            if (r0 != 0) goto L_0x001c
                            j$.util.stream.DoubleStream r0 = r3.sequential()     // Catch:{ all -> 0x001a }
                            java.util.function.DoubleConsumer r1 = r2.downstreamAsDouble     // Catch:{ all -> 0x001a }
                            r0.forEach(r1)     // Catch:{ all -> 0x001a }
                            goto L_0x003e
                        L_0x001a:
                            r0 = move-exception
                            goto L_0x0035
                        L_0x001c:
                            j$.util.stream.DoubleStream r0 = r3.sequential()     // Catch:{ all -> 0x001a }
                            j$.util.Spliterator$OfDouble r0 = r0.spliterator()     // Catch:{ all -> 0x001a }
                        L_0x0024:
                            j$.util.stream.Sink r1 = r2.downstream     // Catch:{ all -> 0x001a }
                            boolean r1 = r1.cancellationRequested()     // Catch:{ all -> 0x001a }
                            if (r1 != 0) goto L_0x003e
                            java.util.function.DoubleConsumer r1 = r2.downstreamAsDouble     // Catch:{ all -> 0x001a }
                            boolean r1 = r0.tryAdvance((java.util.function.DoubleConsumer) r1)     // Catch:{ all -> 0x001a }
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
                        throw new UnsupportedOperationException("Method not decompiled: j$.util.stream.ReferencePipeline.AnonymousClass9.AnonymousClass1.accept(java.lang.Object):void");
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

    public final IntStream flatMapToInt(Function function) {
        Objects.requireNonNull(function);
        final Function function2 = function;
        return new IntPipeline.StatelessOp(this, StreamShape.REFERENCE, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT | StreamOpFlag.NOT_SIZED) {
            /* access modifiers changed from: package-private */
            public Sink opWrapSink(int i, Sink sink) {
                return new Sink.ChainedReference(sink) {
                    boolean cancellationRequestedCalled;
                    IntConsumer downstreamAsInt;

                    {
                        Sink sink = this.downstream;
                        Objects.requireNonNull(sink);
                        this.downstreamAsInt = new IntPipeline$$ExternalSyntheticLambda10(sink);
                    }

                    /* JADX WARNING: Removed duplicated region for block: B:9:0x0024 A[Catch:{ all -> 0x001a, all -> 0x0039 }, LOOP:0: B:9:0x0024->B:12:0x0032, LOOP_START] */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public void accept(java.lang.Object r3) {
                        /*
                            r2 = this;
                            j$.util.stream.ReferencePipeline$8 r0 = j$.util.stream.ReferencePipeline.AnonymousClass8.this
                            java.util.function.Function r0 = r5
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
                        throw new UnsupportedOperationException("Method not decompiled: j$.util.stream.ReferencePipeline.AnonymousClass8.AnonymousClass1.accept(java.lang.Object):void");
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

    public final LongStream flatMapToLong(Function function) {
        Objects.requireNonNull(function);
        final Function function2 = function;
        return new LongPipeline.StatelessOp(this, StreamShape.REFERENCE, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT | StreamOpFlag.NOT_SIZED) {
            /* access modifiers changed from: package-private */
            public Sink opWrapSink(int i, Sink sink) {
                return new Sink.ChainedReference(sink) {
                    boolean cancellationRequestedCalled;
                    LongConsumer downstreamAsLong;

                    {
                        Sink sink = this.downstream;
                        Objects.requireNonNull(sink);
                        this.downstreamAsLong = new LongPipeline$$ExternalSyntheticLambda12(sink);
                    }

                    /* JADX WARNING: Removed duplicated region for block: B:9:0x0024 A[Catch:{ all -> 0x001a, all -> 0x0039 }, LOOP:0: B:9:0x0024->B:12:0x0032, LOOP_START] */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public void accept(java.lang.Object r3) {
                        /*
                            r2 = this;
                            j$.util.stream.ReferencePipeline$10 r0 = j$.util.stream.ReferencePipeline.AnonymousClass10.this
                            java.util.function.Function r0 = r5
                            java.lang.Object r3 = r0.apply(r3)
                            j$.util.stream.LongStream r3 = (j$.util.stream.LongStream) r3
                            if (r3 == 0) goto L_0x003e
                            boolean r0 = r2.cancellationRequestedCalled     // Catch:{ all -> 0x001a }
                            if (r0 != 0) goto L_0x001c
                            j$.util.stream.LongStream r0 = r3.sequential()     // Catch:{ all -> 0x001a }
                            java.util.function.LongConsumer r1 = r2.downstreamAsLong     // Catch:{ all -> 0x001a }
                            r0.forEach(r1)     // Catch:{ all -> 0x001a }
                            goto L_0x003e
                        L_0x001a:
                            r0 = move-exception
                            goto L_0x0035
                        L_0x001c:
                            j$.util.stream.LongStream r0 = r3.sequential()     // Catch:{ all -> 0x001a }
                            j$.util.Spliterator$OfLong r0 = r0.spliterator()     // Catch:{ all -> 0x001a }
                        L_0x0024:
                            j$.util.stream.Sink r1 = r2.downstream     // Catch:{ all -> 0x001a }
                            boolean r1 = r1.cancellationRequested()     // Catch:{ all -> 0x001a }
                            if (r1 != 0) goto L_0x003e
                            java.util.function.LongConsumer r1 = r2.downstreamAsLong     // Catch:{ all -> 0x001a }
                            boolean r1 = r0.tryAdvance((java.util.function.LongConsumer) r1)     // Catch:{ all -> 0x001a }
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
                        throw new UnsupportedOperationException("Method not decompiled: j$.util.stream.ReferencePipeline.AnonymousClass10.AnonymousClass1.accept(java.lang.Object):void");
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

    public void forEach(Consumer consumer) {
        evaluate(ForEachOps.makeRef(consumer, false));
    }

    public void forEachOrdered(Consumer consumer) {
        evaluate(ForEachOps.makeRef(consumer, true));
    }

    /*  JADX ERROR: StackOverflow in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: 
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    final boolean forEachWithCancel(j$.util.Spliterator r3, j$.util.stream.Sink r4) {
        /*
            r2 = this;
        L_0x0000:
            boolean r0 = r4.cancellationRequested()
            if (r0 != 0) goto L_0x000c
            boolean r1 = r3.tryAdvance(r4)
            if (r1 != 0) goto L_0x0000
        L_0x000c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: j$.util.stream.ReferencePipeline.forEachWithCancel(j$.util.Spliterator, j$.util.stream.Sink):boolean");
    }

    /* access modifiers changed from: package-private */
    public final StreamShape getOutputShape() {
        return StreamShape.REFERENCE;
    }

    public final Iterator iterator() {
        return Spliterators.iterator(spliterator());
    }

    /* access modifiers changed from: package-private */
    public final Spliterator lazySpliterator(Supplier supplier) {
        return new StreamSpliterators$DelegatingSpliterator(supplier);
    }

    public final Stream limit(long j) {
        if (j >= 0) {
            return SliceOps.makeRef(this, 0, j);
        }
        throw new IllegalArgumentException(Long.toString(j));
    }

    /* access modifiers changed from: package-private */
    public final Node.Builder makeNodeBuilder(long j, IntFunction intFunction) {
        return Nodes.builder(j, intFunction);
    }

    public final Stream map(Function function) {
        Objects.requireNonNull(function);
        final Function function2 = function;
        return new StatelessOp(this, StreamShape.REFERENCE, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT) {
            /* access modifiers changed from: package-private */
            public Sink opWrapSink(int i, Sink sink) {
                return new Sink.ChainedReference(sink) {
                    public void accept(Object obj) {
                        this.downstream.accept(function2.apply(obj));
                    }
                };
            }
        };
    }

    public final DoubleStream mapToDouble(ToDoubleFunction toDoubleFunction) {
        Objects.requireNonNull(toDoubleFunction);
        final ToDoubleFunction toDoubleFunction2 = toDoubleFunction;
        return new DoublePipeline.StatelessOp(this, StreamShape.REFERENCE, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT) {
            /* access modifiers changed from: package-private */
            public Sink opWrapSink(int i, Sink sink) {
                return new Sink.ChainedReference(sink) {
                    public void accept(Object obj) {
                        this.downstream.accept(toDoubleFunction2.applyAsDouble(obj));
                    }
                };
            }
        };
    }

    public final IntStream mapToInt(ToIntFunction toIntFunction) {
        Objects.requireNonNull(toIntFunction);
        final ToIntFunction toIntFunction2 = toIntFunction;
        return new IntPipeline.StatelessOp(this, StreamShape.REFERENCE, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT) {
            /* access modifiers changed from: package-private */
            public Sink opWrapSink(int i, Sink sink) {
                return new Sink.ChainedReference(sink) {
                    public void accept(Object obj) {
                        this.downstream.accept(toIntFunction2.applyAsInt(obj));
                    }
                };
            }
        };
    }

    public final LongStream mapToLong(ToLongFunction toLongFunction) {
        Objects.requireNonNull(toLongFunction);
        final ToLongFunction toLongFunction2 = toLongFunction;
        return new LongPipeline.StatelessOp(this, StreamShape.REFERENCE, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT) {
            /* access modifiers changed from: package-private */
            public Sink opWrapSink(int i, Sink sink) {
                return new Sink.ChainedReference(sink) {
                    public void accept(Object obj) {
                        this.downstream.accept(toLongFunction2.applyAsLong(obj));
                    }
                };
            }
        };
    }

    public final Optional max(Comparator comparator) {
        return reduce(BinaryOperator$CC.maxBy(comparator));
    }

    public final Optional min(Comparator comparator) {
        return reduce(BinaryOperator$CC.minBy(comparator));
    }

    public final boolean noneMatch(Predicate predicate) {
        return ((Boolean) evaluate(MatchOps.makeRef(predicate, MatchOps.MatchKind.NONE))).booleanValue();
    }

    public final Stream peek(Consumer consumer) {
        Objects.requireNonNull(consumer);
        final Consumer consumer2 = consumer;
        return new StatelessOp(this, StreamShape.REFERENCE, 0) {
            /* access modifiers changed from: package-private */
            public Sink opWrapSink(int i, Sink sink) {
                return new Sink.ChainedReference(sink) {
                    public void accept(Object obj) {
                        consumer2.accept(obj);
                        this.downstream.accept(obj);
                    }
                };
            }
        };
    }

    public final Optional reduce(BinaryOperator binaryOperator) {
        return (Optional) evaluate(ReduceOps.makeRef(binaryOperator));
    }

    public final Object reduce(Object obj, BiFunction biFunction, BinaryOperator binaryOperator) {
        return evaluate(ReduceOps.makeRef(obj, biFunction, binaryOperator));
    }

    public final Object reduce(Object obj, BinaryOperator binaryOperator) {
        return evaluate(ReduceOps.makeRef(obj, (BiFunction) binaryOperator, binaryOperator));
    }

    public final Stream skip(long j) {
        if (j >= 0) {
            return j == 0 ? this : SliceOps.makeRef(this, j, -1);
        }
        throw new IllegalArgumentException(Long.toString(j));
    }

    public final Stream sorted() {
        return SortedOps.makeRef(this);
    }

    public final Stream sorted(Comparator comparator) {
        return SortedOps.makeRef(this, comparator);
    }

    public final Stream takeWhile(Predicate predicate) {
        return WhileOps.makeTakeWhileRef(this, predicate);
    }

    public final Object[] toArray() {
        return toArray(new ReferencePipeline$$ExternalSyntheticLambda1());
    }

    public final Object[] toArray(IntFunction intFunction) {
        return Nodes.flatten(evaluateToArrayNode(intFunction), intFunction).asArray(intFunction);
    }

    public Stream unordered() {
        return !isOrdered() ? this : new StatelessOp(this, StreamShape.REFERENCE, StreamOpFlag.NOT_ORDERED) {
            /* access modifiers changed from: package-private */
            public Sink opWrapSink(int i, Sink sink) {
                return sink;
            }
        };
    }

    /* access modifiers changed from: package-private */
    public final Spliterator wrap(PipelineHelper pipelineHelper, Supplier supplier, boolean z) {
        return new StreamSpliterators$WrappingSpliterator(pipelineHelper, supplier, z);
    }
}
