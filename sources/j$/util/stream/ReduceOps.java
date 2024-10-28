package j$.util.stream;

import j$.util.Objects;
import j$.util.Optional;
import j$.util.OptionalDouble;
import j$.util.OptionalInt;
import j$.util.OptionalLong;
import j$.util.Spliterator;
import j$.util.function.Consumer$CC;
import j$.util.function.DoubleConsumer$CC;
import j$.util.function.IntConsumer$CC;
import j$.util.function.LongConsumer$CC;
import j$.util.stream.Collector;
import j$.util.stream.Sink;
import j$.util.stream.TerminalOp;
import java.util.concurrent.CountedCompleter;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleConsumer;
import java.util.function.IntBinaryOperator;
import java.util.function.IntConsumer;
import java.util.function.LongBinaryOperator;
import java.util.function.LongConsumer;
import java.util.function.ObjDoubleConsumer;
import java.util.function.ObjIntConsumer;
import java.util.function.ObjLongConsumer;
import java.util.function.Supplier;

abstract class ReduceOps {

    private interface AccumulatingSink extends TerminalSink {
        void combine(AccumulatingSink accumulatingSink);
    }

    private static abstract class Box {
        Object state;

        Box() {
        }

        public Object get() {
            return this.state;
        }
    }

    static abstract class CountingSink extends Box implements AccumulatingSink {
        long count;

        static final class OfDouble extends CountingSink implements Sink.OfDouble {
            OfDouble() {
            }

            public void accept(double d) {
                this.count++;
            }

            public /* synthetic */ void accept(Double d) {
                Sink.OfDouble.CC.$default$accept((Sink.OfDouble) this, d);
            }

            public /* bridge */ /* synthetic */ void accept(Object obj) {
                accept((Double) obj);
            }

            public /* synthetic */ DoubleConsumer andThen(DoubleConsumer doubleConsumer) {
                return DoubleConsumer$CC.$default$andThen(this, doubleConsumer);
            }

            public /* bridge */ /* synthetic */ void combine(AccumulatingSink accumulatingSink) {
                super.combine((CountingSink) accumulatingSink);
            }

            public /* bridge */ /* synthetic */ Object get() {
                return super.get();
            }
        }

        static final class OfInt extends CountingSink implements Sink.OfInt {
            OfInt() {
            }

            public void accept(int i) {
                this.count++;
            }

            public /* synthetic */ void accept(Integer num) {
                Sink.OfInt.CC.$default$accept((Sink.OfInt) this, num);
            }

            public /* bridge */ /* synthetic */ void accept(Object obj) {
                accept((Integer) obj);
            }

            public /* synthetic */ IntConsumer andThen(IntConsumer intConsumer) {
                return IntConsumer$CC.$default$andThen(this, intConsumer);
            }

            public /* bridge */ /* synthetic */ void combine(AccumulatingSink accumulatingSink) {
                super.combine((CountingSink) accumulatingSink);
            }

            public /* bridge */ /* synthetic */ Object get() {
                return super.get();
            }
        }

        static final class OfLong extends CountingSink implements Sink.OfLong {
            OfLong() {
            }

            public void accept(long j) {
                this.count++;
            }

            public /* synthetic */ void accept(Long l) {
                Sink.OfLong.CC.$default$accept((Sink.OfLong) this, l);
            }

            public /* bridge */ /* synthetic */ void accept(Object obj) {
                accept((Long) obj);
            }

            public /* synthetic */ LongConsumer andThen(LongConsumer longConsumer) {
                return LongConsumer$CC.$default$andThen(this, longConsumer);
            }

            public /* bridge */ /* synthetic */ void combine(AccumulatingSink accumulatingSink) {
                super.combine((CountingSink) accumulatingSink);
            }

            public /* bridge */ /* synthetic */ Object get() {
                return super.get();
            }
        }

        static final class OfRef extends CountingSink {
            OfRef() {
            }

            public void accept(Object obj) {
                this.count++;
            }

            public /* bridge */ /* synthetic */ void combine(AccumulatingSink accumulatingSink) {
                super.combine((CountingSink) accumulatingSink);
            }

            public /* bridge */ /* synthetic */ Object get() {
                return super.get();
            }
        }

        CountingSink() {
        }

        public /* synthetic */ void accept(double d) {
            Sink.CC.$default$accept((Sink) this, d);
        }

        public /* synthetic */ void accept(int i) {
            Sink.CC.$default$accept((Sink) this, i);
        }

        public /* synthetic */ void accept(long j) {
            Sink.CC.$default$accept((Sink) this, j);
        }

        public /* synthetic */ Consumer andThen(Consumer consumer) {
            return Consumer$CC.$default$andThen(this, consumer);
        }

        public void begin(long j) {
            this.count = 0;
        }

        public /* synthetic */ boolean cancellationRequested() {
            return Sink.CC.$default$cancellationRequested(this);
        }

        public void combine(CountingSink countingSink) {
            this.count += countingSink.count;
        }

        public /* synthetic */ void end() {
            Sink.CC.$default$end(this);
        }

        public Long get() {
            return Long.valueOf(this.count);
        }
    }

    private static abstract class ReduceOp implements TerminalOp {
        private final StreamShape inputShape;

        ReduceOp(StreamShape streamShape) {
            this.inputShape = streamShape;
        }

        public Object evaluateParallel(PipelineHelper pipelineHelper, Spliterator spliterator) {
            return ((AccumulatingSink) new ReduceTask(this, pipelineHelper, spliterator).invoke()).get();
        }

        public Object evaluateSequential(PipelineHelper pipelineHelper, Spliterator spliterator) {
            return ((AccumulatingSink) pipelineHelper.wrapAndCopyInto(makeSink(), spliterator)).get();
        }

        public /* synthetic */ int getOpFlags() {
            return TerminalOp.CC.$default$getOpFlags(this);
        }

        public abstract AccumulatingSink makeSink();
    }

    private static final class ReduceTask extends AbstractTask {
        private final ReduceOp op;

        ReduceTask(ReduceOp reduceOp, PipelineHelper pipelineHelper, Spliterator spliterator) {
            super(pipelineHelper, spliterator);
            this.op = reduceOp;
        }

        ReduceTask(ReduceTask reduceTask, Spliterator spliterator) {
            super((AbstractTask) reduceTask, spliterator);
            this.op = reduceTask.op;
        }

        /* access modifiers changed from: protected */
        public AccumulatingSink doLeaf() {
            return (AccumulatingSink) this.helper.wrapAndCopyInto(this.op.makeSink(), this.spliterator);
        }

        /* access modifiers changed from: protected */
        public ReduceTask makeChild(Spliterator spliterator) {
            return new ReduceTask(this, spliterator);
        }

        public void onCompletion(CountedCompleter countedCompleter) {
            if (!isLeaf()) {
                AccumulatingSink accumulatingSink = (AccumulatingSink) ((ReduceTask) this.leftChild).getLocalResult();
                accumulatingSink.combine((AccumulatingSink) ((ReduceTask) this.rightChild).getLocalResult());
                setLocalResult(accumulatingSink);
            }
            super.onCompletion(countedCompleter);
        }
    }

    public static TerminalOp makeDouble(final double d, final DoubleBinaryOperator doubleBinaryOperator) {
        Objects.requireNonNull(doubleBinaryOperator);
        return new ReduceOp(StreamShape.DOUBLE_VALUE) {
            public AnonymousClass11ReducingSink makeSink() {
                return new Object(d, doubleBinaryOperator) {
                    private double state;
                    final /* synthetic */ double val$identity;
                    final /* synthetic */ DoubleBinaryOperator val$operator;

                    {
                        this.val$identity = r1;
                        this.val$operator = r3;
                    }

                    public void accept(double d) {
                        this.state = this.val$operator.applyAsDouble(this.state, d);
                    }

                    public /* synthetic */ void accept(int i) {
                        Sink.CC.$default$accept((Sink) this, i);
                    }

                    public /* synthetic */ void accept(long j) {
                        Sink.CC.$default$accept((Sink) this, j);
                    }

                    public /* synthetic */ void accept(Double d) {
                        Sink.OfDouble.CC.$default$accept((Sink.OfDouble) this, d);
                    }

                    public /* bridge */ /* synthetic */ void accept(Object obj) {
                        accept((Double) obj);
                    }

                    public /* synthetic */ Consumer andThen(Consumer consumer) {
                        return Consumer$CC.$default$andThen(this, consumer);
                    }

                    public /* synthetic */ DoubleConsumer andThen(DoubleConsumer doubleConsumer) {
                        return DoubleConsumer$CC.$default$andThen(this, doubleConsumer);
                    }

                    public void begin(long j) {
                        this.state = this.val$identity;
                    }

                    public /* synthetic */ boolean cancellationRequested() {
                        return Sink.CC.$default$cancellationRequested(this);
                    }

                    public void combine(AnonymousClass11ReducingSink r3) {
                        accept(r3.state);
                    }

                    public /* synthetic */ void end() {
                        Sink.CC.$default$end(this);
                    }

                    public Double get() {
                        return Double.valueOf(this.state);
                    }
                };
            }
        };
    }

    public static TerminalOp makeDouble(final DoubleBinaryOperator doubleBinaryOperator) {
        Objects.requireNonNull(doubleBinaryOperator);
        return new ReduceOp(StreamShape.DOUBLE_VALUE) {
            public AnonymousClass12ReducingSink makeSink() {
                return new Object() {
                    private boolean empty;
                    private double state;

                    public void accept(double d) {
                        if (this.empty) {
                            this.empty = false;
                        } else {
                            d = DoubleBinaryOperator.this.applyAsDouble(this.state, d);
                        }
                        this.state = d;
                    }

                    public /* synthetic */ void accept(int i) {
                        Sink.CC.$default$accept((Sink) this, i);
                    }

                    public /* synthetic */ void accept(long j) {
                        Sink.CC.$default$accept((Sink) this, j);
                    }

                    public /* synthetic */ void accept(Double d) {
                        Sink.OfDouble.CC.$default$accept((Sink.OfDouble) this, d);
                    }

                    public /* bridge */ /* synthetic */ void accept(Object obj) {
                        accept((Double) obj);
                    }

                    public /* synthetic */ Consumer andThen(Consumer consumer) {
                        return Consumer$CC.$default$andThen(this, consumer);
                    }

                    public /* synthetic */ DoubleConsumer andThen(DoubleConsumer doubleConsumer) {
                        return DoubleConsumer$CC.$default$andThen(this, doubleConsumer);
                    }

                    public void begin(long j) {
                        this.empty = true;
                        this.state = 0.0d;
                    }

                    public /* synthetic */ boolean cancellationRequested() {
                        return Sink.CC.$default$cancellationRequested(this);
                    }

                    public void combine(AnonymousClass12ReducingSink r3) {
                        if (!r3.empty) {
                            accept(r3.state);
                        }
                    }

                    public /* synthetic */ void end() {
                        Sink.CC.$default$end(this);
                    }

                    public OptionalDouble get() {
                        return this.empty ? OptionalDouble.empty() : OptionalDouble.of(this.state);
                    }
                };
            }
        };
    }

    public static TerminalOp makeDouble(final Supplier supplier, final ObjDoubleConsumer objDoubleConsumer, final BinaryOperator binaryOperator) {
        Objects.requireNonNull(supplier);
        Objects.requireNonNull(objDoubleConsumer);
        Objects.requireNonNull(binaryOperator);
        return new ReduceOp(StreamShape.DOUBLE_VALUE) {
            public AnonymousClass13ReducingSink makeSink() {
                return new Box(objDoubleConsumer, binaryOperator) {
                    final /* synthetic */ ObjDoubleConsumer val$accumulator;
                    final /* synthetic */ BinaryOperator val$combiner;

                    {
                        this.val$accumulator = r2;
                        this.val$combiner = r3;
                    }

                    public void accept(double d) {
                        this.val$accumulator.accept(this.state, d);
                    }

                    public /* synthetic */ void accept(int i) {
                        Sink.CC.$default$accept((Sink) this, i);
                    }

                    public /* synthetic */ void accept(long j) {
                        Sink.CC.$default$accept((Sink) this, j);
                    }

                    public /* synthetic */ void accept(Double d) {
                        Sink.OfDouble.CC.$default$accept((Sink.OfDouble) this, d);
                    }

                    public /* bridge */ /* synthetic */ void accept(Object obj) {
                        accept((Double) obj);
                    }

                    public /* synthetic */ Consumer andThen(Consumer consumer) {
                        return Consumer$CC.$default$andThen(this, consumer);
                    }

                    public /* synthetic */ DoubleConsumer andThen(DoubleConsumer doubleConsumer) {
                        return DoubleConsumer$CC.$default$andThen(this, doubleConsumer);
                    }

                    public void begin(long j) {
                        this.state = Supplier.this.get();
                    }

                    public /* synthetic */ boolean cancellationRequested() {
                        return Sink.CC.$default$cancellationRequested(this);
                    }

                    public void combine(AnonymousClass13ReducingSink r3) {
                        this.state = this.val$combiner.apply(this.state, r3.state);
                    }

                    public /* synthetic */ void end() {
                        Sink.CC.$default$end(this);
                    }
                };
            }
        };
    }

    public static TerminalOp makeDoubleCounting() {
        return new ReduceOp(StreamShape.DOUBLE_VALUE) {
            public Long evaluateParallel(PipelineHelper pipelineHelper, Spliterator spliterator) {
                return StreamOpFlag.SIZED.isKnown(pipelineHelper.getStreamAndOpFlags()) ? Long.valueOf(spliterator.getExactSizeIfKnown()) : (Long) super.evaluateParallel(pipelineHelper, spliterator);
            }

            public Long evaluateSequential(PipelineHelper pipelineHelper, Spliterator spliterator) {
                return StreamOpFlag.SIZED.isKnown(pipelineHelper.getStreamAndOpFlags()) ? Long.valueOf(spliterator.getExactSizeIfKnown()) : (Long) super.evaluateSequential(pipelineHelper, spliterator);
            }

            public int getOpFlags() {
                return StreamOpFlag.NOT_ORDERED;
            }

            public CountingSink makeSink() {
                return new CountingSink.OfDouble();
            }
        };
    }

    public static TerminalOp makeInt(final int i, final IntBinaryOperator intBinaryOperator) {
        Objects.requireNonNull(intBinaryOperator);
        return new ReduceOp(StreamShape.INT_VALUE) {
            public AnonymousClass5ReducingSink makeSink() {
                return new Object(i, intBinaryOperator) {
                    private int state;
                    final /* synthetic */ int val$identity;
                    final /* synthetic */ IntBinaryOperator val$operator;

                    {
                        this.val$identity = r1;
                        this.val$operator = r2;
                    }

                    public /* synthetic */ void accept(double d) {
                        Sink.CC.$default$accept((Sink) this, d);
                    }

                    public void accept(int i) {
                        this.state = this.val$operator.applyAsInt(this.state, i);
                    }

                    public /* synthetic */ void accept(long j) {
                        Sink.CC.$default$accept((Sink) this, j);
                    }

                    public /* synthetic */ void accept(Integer num) {
                        Sink.OfInt.CC.$default$accept((Sink.OfInt) this, num);
                    }

                    public /* bridge */ /* synthetic */ void accept(Object obj) {
                        accept((Integer) obj);
                    }

                    public /* synthetic */ Consumer andThen(Consumer consumer) {
                        return Consumer$CC.$default$andThen(this, consumer);
                    }

                    public /* synthetic */ IntConsumer andThen(IntConsumer intConsumer) {
                        return IntConsumer$CC.$default$andThen(this, intConsumer);
                    }

                    public void begin(long j) {
                        this.state = this.val$identity;
                    }

                    public /* synthetic */ boolean cancellationRequested() {
                        return Sink.CC.$default$cancellationRequested(this);
                    }

                    public void combine(AnonymousClass5ReducingSink r1) {
                        accept(r1.state);
                    }

                    public /* synthetic */ void end() {
                        Sink.CC.$default$end(this);
                    }

                    public Integer get() {
                        return Integer.valueOf(this.state);
                    }
                };
            }
        };
    }

    public static TerminalOp makeInt(final IntBinaryOperator intBinaryOperator) {
        Objects.requireNonNull(intBinaryOperator);
        return new ReduceOp(StreamShape.INT_VALUE) {
            public AnonymousClass6ReducingSink makeSink() {
                return new Object() {
                    private boolean empty;
                    private int state;

                    public /* synthetic */ void accept(double d) {
                        Sink.CC.$default$accept((Sink) this, d);
                    }

                    public void accept(int i) {
                        if (this.empty) {
                            this.empty = false;
                        } else {
                            i = IntBinaryOperator.this.applyAsInt(this.state, i);
                        }
                        this.state = i;
                    }

                    public /* synthetic */ void accept(long j) {
                        Sink.CC.$default$accept((Sink) this, j);
                    }

                    public /* synthetic */ void accept(Integer num) {
                        Sink.OfInt.CC.$default$accept((Sink.OfInt) this, num);
                    }

                    public /* bridge */ /* synthetic */ void accept(Object obj) {
                        accept((Integer) obj);
                    }

                    public /* synthetic */ Consumer andThen(Consumer consumer) {
                        return Consumer$CC.$default$andThen(this, consumer);
                    }

                    public /* synthetic */ IntConsumer andThen(IntConsumer intConsumer) {
                        return IntConsumer$CC.$default$andThen(this, intConsumer);
                    }

                    public void begin(long j) {
                        this.empty = true;
                        this.state = 0;
                    }

                    public /* synthetic */ boolean cancellationRequested() {
                        return Sink.CC.$default$cancellationRequested(this);
                    }

                    public void combine(AnonymousClass6ReducingSink r2) {
                        if (!r2.empty) {
                            accept(r2.state);
                        }
                    }

                    public /* synthetic */ void end() {
                        Sink.CC.$default$end(this);
                    }

                    public OptionalInt get() {
                        return this.empty ? OptionalInt.empty() : OptionalInt.of(this.state);
                    }
                };
            }
        };
    }

    public static TerminalOp makeInt(final Supplier supplier, final ObjIntConsumer objIntConsumer, final BinaryOperator binaryOperator) {
        Objects.requireNonNull(supplier);
        Objects.requireNonNull(objIntConsumer);
        Objects.requireNonNull(binaryOperator);
        return new ReduceOp(StreamShape.INT_VALUE) {
            public AnonymousClass7ReducingSink makeSink() {
                return new Box(objIntConsumer, binaryOperator) {
                    final /* synthetic */ ObjIntConsumer val$accumulator;
                    final /* synthetic */ BinaryOperator val$combiner;

                    {
                        this.val$accumulator = r2;
                        this.val$combiner = r3;
                    }

                    public /* synthetic */ void accept(double d) {
                        Sink.CC.$default$accept((Sink) this, d);
                    }

                    public void accept(int i) {
                        this.val$accumulator.accept(this.state, i);
                    }

                    public /* synthetic */ void accept(long j) {
                        Sink.CC.$default$accept((Sink) this, j);
                    }

                    public /* synthetic */ void accept(Integer num) {
                        Sink.OfInt.CC.$default$accept((Sink.OfInt) this, num);
                    }

                    public /* bridge */ /* synthetic */ void accept(Object obj) {
                        accept((Integer) obj);
                    }

                    public /* synthetic */ Consumer andThen(Consumer consumer) {
                        return Consumer$CC.$default$andThen(this, consumer);
                    }

                    public /* synthetic */ IntConsumer andThen(IntConsumer intConsumer) {
                        return IntConsumer$CC.$default$andThen(this, intConsumer);
                    }

                    public void begin(long j) {
                        this.state = Supplier.this.get();
                    }

                    public /* synthetic */ boolean cancellationRequested() {
                        return Sink.CC.$default$cancellationRequested(this);
                    }

                    public void combine(AnonymousClass7ReducingSink r3) {
                        this.state = this.val$combiner.apply(this.state, r3.state);
                    }

                    public /* synthetic */ void end() {
                        Sink.CC.$default$end(this);
                    }
                };
            }
        };
    }

    public static TerminalOp makeIntCounting() {
        return new ReduceOp(StreamShape.INT_VALUE) {
            public Long evaluateParallel(PipelineHelper pipelineHelper, Spliterator spliterator) {
                return StreamOpFlag.SIZED.isKnown(pipelineHelper.getStreamAndOpFlags()) ? Long.valueOf(spliterator.getExactSizeIfKnown()) : (Long) super.evaluateParallel(pipelineHelper, spliterator);
            }

            public Long evaluateSequential(PipelineHelper pipelineHelper, Spliterator spliterator) {
                return StreamOpFlag.SIZED.isKnown(pipelineHelper.getStreamAndOpFlags()) ? Long.valueOf(spliterator.getExactSizeIfKnown()) : (Long) super.evaluateSequential(pipelineHelper, spliterator);
            }

            public int getOpFlags() {
                return StreamOpFlag.NOT_ORDERED;
            }

            public CountingSink makeSink() {
                return new CountingSink.OfInt();
            }
        };
    }

    public static TerminalOp makeLong(final long j, final LongBinaryOperator longBinaryOperator) {
        Objects.requireNonNull(longBinaryOperator);
        return new ReduceOp(StreamShape.LONG_VALUE) {
            public AnonymousClass8ReducingSink makeSink() {
                return new Object(j, longBinaryOperator) {
                    private long state;
                    final /* synthetic */ long val$identity;
                    final /* synthetic */ LongBinaryOperator val$operator;

                    {
                        this.val$identity = r1;
                        this.val$operator = r3;
                    }

                    public /* synthetic */ void accept(double d) {
                        Sink.CC.$default$accept((Sink) this, d);
                    }

                    public /* synthetic */ void accept(int i) {
                        Sink.CC.$default$accept((Sink) this, i);
                    }

                    public void accept(long j) {
                        this.state = this.val$operator.applyAsLong(this.state, j);
                    }

                    public /* synthetic */ void accept(Long l) {
                        Sink.OfLong.CC.$default$accept((Sink.OfLong) this, l);
                    }

                    public /* bridge */ /* synthetic */ void accept(Object obj) {
                        accept((Long) obj);
                    }

                    public /* synthetic */ Consumer andThen(Consumer consumer) {
                        return Consumer$CC.$default$andThen(this, consumer);
                    }

                    public /* synthetic */ LongConsumer andThen(LongConsumer longConsumer) {
                        return LongConsumer$CC.$default$andThen(this, longConsumer);
                    }

                    public void begin(long j) {
                        this.state = this.val$identity;
                    }

                    public /* synthetic */ boolean cancellationRequested() {
                        return Sink.CC.$default$cancellationRequested(this);
                    }

                    public void combine(AnonymousClass8ReducingSink r3) {
                        accept(r3.state);
                    }

                    public /* synthetic */ void end() {
                        Sink.CC.$default$end(this);
                    }

                    public Long get() {
                        return Long.valueOf(this.state);
                    }
                };
            }
        };
    }

    public static TerminalOp makeLong(final LongBinaryOperator longBinaryOperator) {
        Objects.requireNonNull(longBinaryOperator);
        return new ReduceOp(StreamShape.LONG_VALUE) {
            public AnonymousClass9ReducingSink makeSink() {
                return new Object() {
                    private boolean empty;
                    private long state;

                    public /* synthetic */ void accept(double d) {
                        Sink.CC.$default$accept((Sink) this, d);
                    }

                    public /* synthetic */ void accept(int i) {
                        Sink.CC.$default$accept((Sink) this, i);
                    }

                    public void accept(long j) {
                        if (this.empty) {
                            this.empty = false;
                        } else {
                            j = LongBinaryOperator.this.applyAsLong(this.state, j);
                        }
                        this.state = j;
                    }

                    public /* synthetic */ void accept(Long l) {
                        Sink.OfLong.CC.$default$accept((Sink.OfLong) this, l);
                    }

                    public /* bridge */ /* synthetic */ void accept(Object obj) {
                        accept((Long) obj);
                    }

                    public /* synthetic */ Consumer andThen(Consumer consumer) {
                        return Consumer$CC.$default$andThen(this, consumer);
                    }

                    public /* synthetic */ LongConsumer andThen(LongConsumer longConsumer) {
                        return LongConsumer$CC.$default$andThen(this, longConsumer);
                    }

                    public void begin(long j) {
                        this.empty = true;
                        this.state = 0;
                    }

                    public /* synthetic */ boolean cancellationRequested() {
                        return Sink.CC.$default$cancellationRequested(this);
                    }

                    public void combine(AnonymousClass9ReducingSink r3) {
                        if (!r3.empty) {
                            accept(r3.state);
                        }
                    }

                    public /* synthetic */ void end() {
                        Sink.CC.$default$end(this);
                    }

                    public OptionalLong get() {
                        return this.empty ? OptionalLong.empty() : OptionalLong.of(this.state);
                    }
                };
            }
        };
    }

    public static TerminalOp makeLong(final Supplier supplier, final ObjLongConsumer objLongConsumer, final BinaryOperator binaryOperator) {
        Objects.requireNonNull(supplier);
        Objects.requireNonNull(objLongConsumer);
        Objects.requireNonNull(binaryOperator);
        return new ReduceOp(StreamShape.LONG_VALUE) {
            public AnonymousClass10ReducingSink makeSink() {
                return new Box(objLongConsumer, binaryOperator) {
                    final /* synthetic */ ObjLongConsumer val$accumulator;
                    final /* synthetic */ BinaryOperator val$combiner;

                    {
                        this.val$accumulator = r2;
                        this.val$combiner = r3;
                    }

                    public /* synthetic */ void accept(double d) {
                        Sink.CC.$default$accept((Sink) this, d);
                    }

                    public /* synthetic */ void accept(int i) {
                        Sink.CC.$default$accept((Sink) this, i);
                    }

                    public void accept(long j) {
                        this.val$accumulator.accept(this.state, j);
                    }

                    public /* synthetic */ void accept(Long l) {
                        Sink.OfLong.CC.$default$accept((Sink.OfLong) this, l);
                    }

                    public /* bridge */ /* synthetic */ void accept(Object obj) {
                        accept((Long) obj);
                    }

                    public /* synthetic */ Consumer andThen(Consumer consumer) {
                        return Consumer$CC.$default$andThen(this, consumer);
                    }

                    public /* synthetic */ LongConsumer andThen(LongConsumer longConsumer) {
                        return LongConsumer$CC.$default$andThen(this, longConsumer);
                    }

                    public void begin(long j) {
                        this.state = Supplier.this.get();
                    }

                    public /* synthetic */ boolean cancellationRequested() {
                        return Sink.CC.$default$cancellationRequested(this);
                    }

                    public void combine(AnonymousClass10ReducingSink r3) {
                        this.state = this.val$combiner.apply(this.state, r3.state);
                    }

                    public /* synthetic */ void end() {
                        Sink.CC.$default$end(this);
                    }
                };
            }
        };
    }

    public static TerminalOp makeLongCounting() {
        return new ReduceOp(StreamShape.LONG_VALUE) {
            public Long evaluateParallel(PipelineHelper pipelineHelper, Spliterator spliterator) {
                return StreamOpFlag.SIZED.isKnown(pipelineHelper.getStreamAndOpFlags()) ? Long.valueOf(spliterator.getExactSizeIfKnown()) : (Long) super.evaluateParallel(pipelineHelper, spliterator);
            }

            public Long evaluateSequential(PipelineHelper pipelineHelper, Spliterator spliterator) {
                return StreamOpFlag.SIZED.isKnown(pipelineHelper.getStreamAndOpFlags()) ? Long.valueOf(spliterator.getExactSizeIfKnown()) : (Long) super.evaluateSequential(pipelineHelper, spliterator);
            }

            public int getOpFlags() {
                return StreamOpFlag.NOT_ORDERED;
            }

            public CountingSink makeSink() {
                return new CountingSink.OfLong();
            }
        };
    }

    public static TerminalOp makeRef(Collector collector) {
        final Supplier supplier = ((Collector) Objects.requireNonNull(collector)).supplier();
        final BiConsumer accumulator = collector.accumulator();
        final BinaryOperator combiner = collector.combiner();
        final Collector collector2 = collector;
        return new ReduceOp(StreamShape.REFERENCE) {
            public int getOpFlags() {
                if (collector2.characteristics().contains(Collector.Characteristics.UNORDERED)) {
                    return StreamOpFlag.NOT_ORDERED;
                }
                return 0;
            }

            public AnonymousClass3ReducingSink makeSink() {
                return new AccumulatingSink(accumulator, combiner) {
                    final /* synthetic */ BiConsumer val$accumulator;
                    final /* synthetic */ BinaryOperator val$combiner;

                    {
                        this.val$accumulator = r2;
                        this.val$combiner = r3;
                    }

                    public /* synthetic */ void accept(double d) {
                        Sink.CC.$default$accept((Sink) this, d);
                    }

                    public /* synthetic */ void accept(int i) {
                        Sink.CC.$default$accept((Sink) this, i);
                    }

                    public /* synthetic */ void accept(long j) {
                        Sink.CC.$default$accept((Sink) this, j);
                    }

                    public void accept(Object obj) {
                        this.val$accumulator.accept(this.state, obj);
                    }

                    public /* synthetic */ Consumer andThen(Consumer consumer) {
                        return Consumer$CC.$default$andThen(this, consumer);
                    }

                    public void begin(long j) {
                        this.state = Supplier.this.get();
                    }

                    public /* synthetic */ boolean cancellationRequested() {
                        return Sink.CC.$default$cancellationRequested(this);
                    }

                    public void combine(AnonymousClass3ReducingSink r3) {
                        this.state = this.val$combiner.apply(this.state, r3.state);
                    }

                    public /* synthetic */ void end() {
                        Sink.CC.$default$end(this);
                    }
                };
            }
        };
    }

    public static TerminalOp makeRef(final Object obj, final BiFunction biFunction, final BinaryOperator binaryOperator) {
        Objects.requireNonNull(biFunction);
        Objects.requireNonNull(binaryOperator);
        return new ReduceOp(StreamShape.REFERENCE) {
            public AnonymousClass1ReducingSink makeSink() {
                return new AccumulatingSink(obj, biFunction, binaryOperator) {
                    final /* synthetic */ BinaryOperator val$combiner;
                    final /* synthetic */ BiFunction val$reducer;
                    final /* synthetic */ Object val$seed;

                    {
                        this.val$seed = r1;
                        this.val$reducer = r2;
                        this.val$combiner = r3;
                    }

                    public /* synthetic */ void accept(double d) {
                        Sink.CC.$default$accept((Sink) this, d);
                    }

                    public /* synthetic */ void accept(int i) {
                        Sink.CC.$default$accept((Sink) this, i);
                    }

                    public /* synthetic */ void accept(long j) {
                        Sink.CC.$default$accept((Sink) this, j);
                    }

                    public void accept(Object obj) {
                        this.state = this.val$reducer.apply(this.state, obj);
                    }

                    public /* synthetic */ Consumer andThen(Consumer consumer) {
                        return Consumer$CC.$default$andThen(this, consumer);
                    }

                    public void begin(long j) {
                        this.state = this.val$seed;
                    }

                    public /* synthetic */ boolean cancellationRequested() {
                        return Sink.CC.$default$cancellationRequested(this);
                    }

                    public void combine(AnonymousClass1ReducingSink r3) {
                        this.state = this.val$combiner.apply(this.state, r3.state);
                    }

                    public /* synthetic */ void end() {
                        Sink.CC.$default$end(this);
                    }
                };
            }
        };
    }

    public static TerminalOp makeRef(final BinaryOperator binaryOperator) {
        Objects.requireNonNull(binaryOperator);
        return new ReduceOp(StreamShape.REFERENCE) {
            public AnonymousClass2ReducingSink makeSink() {
                return new AccumulatingSink() {
                    private boolean empty;
                    private Object state;

                    public /* synthetic */ void accept(double d) {
                        Sink.CC.$default$accept((Sink) this, d);
                    }

                    public /* synthetic */ void accept(int i) {
                        Sink.CC.$default$accept((Sink) this, i);
                    }

                    public /* synthetic */ void accept(long j) {
                        Sink.CC.$default$accept((Sink) this, j);
                    }

                    public void accept(Object obj) {
                        if (this.empty) {
                            this.empty = false;
                        } else {
                            obj = BinaryOperator.this.apply(this.state, obj);
                        }
                        this.state = obj;
                    }

                    public /* synthetic */ Consumer andThen(Consumer consumer) {
                        return Consumer$CC.$default$andThen(this, consumer);
                    }

                    public void begin(long j) {
                        this.empty = true;
                        this.state = null;
                    }

                    public /* synthetic */ boolean cancellationRequested() {
                        return Sink.CC.$default$cancellationRequested(this);
                    }

                    public void combine(AnonymousClass2ReducingSink r2) {
                        if (!r2.empty) {
                            accept(r2.state);
                        }
                    }

                    public /* synthetic */ void end() {
                        Sink.CC.$default$end(this);
                    }

                    public Optional get() {
                        return this.empty ? Optional.empty() : Optional.of(this.state);
                    }
                };
            }
        };
    }

    public static TerminalOp makeRef(final Supplier supplier, final BiConsumer biConsumer, final BiConsumer biConsumer2) {
        Objects.requireNonNull(supplier);
        Objects.requireNonNull(biConsumer);
        Objects.requireNonNull(biConsumer2);
        return new ReduceOp(StreamShape.REFERENCE) {
            public AnonymousClass4ReducingSink makeSink() {
                return new AccumulatingSink(biConsumer, biConsumer2) {
                    final /* synthetic */ BiConsumer val$accumulator;
                    final /* synthetic */ BiConsumer val$reducer;

                    {
                        this.val$accumulator = r2;
                        this.val$reducer = r3;
                    }

                    public /* synthetic */ void accept(double d) {
                        Sink.CC.$default$accept((Sink) this, d);
                    }

                    public /* synthetic */ void accept(int i) {
                        Sink.CC.$default$accept((Sink) this, i);
                    }

                    public /* synthetic */ void accept(long j) {
                        Sink.CC.$default$accept((Sink) this, j);
                    }

                    public void accept(Object obj) {
                        this.val$accumulator.accept(this.state, obj);
                    }

                    public /* synthetic */ Consumer andThen(Consumer consumer) {
                        return Consumer$CC.$default$andThen(this, consumer);
                    }

                    public void begin(long j) {
                        this.state = Supplier.this.get();
                    }

                    public /* synthetic */ boolean cancellationRequested() {
                        return Sink.CC.$default$cancellationRequested(this);
                    }

                    public void combine(AnonymousClass4ReducingSink r3) {
                        this.val$reducer.accept(this.state, r3.state);
                    }

                    public /* synthetic */ void end() {
                        Sink.CC.$default$end(this);
                    }
                };
            }
        };
    }

    public static TerminalOp makeRefCounting() {
        return new ReduceOp(StreamShape.REFERENCE) {
            public Long evaluateParallel(PipelineHelper pipelineHelper, Spliterator spliterator) {
                return StreamOpFlag.SIZED.isKnown(pipelineHelper.getStreamAndOpFlags()) ? Long.valueOf(spliterator.getExactSizeIfKnown()) : (Long) super.evaluateParallel(pipelineHelper, spliterator);
            }

            public Long evaluateSequential(PipelineHelper pipelineHelper, Spliterator spliterator) {
                return StreamOpFlag.SIZED.isKnown(pipelineHelper.getStreamAndOpFlags()) ? Long.valueOf(spliterator.getExactSizeIfKnown()) : (Long) super.evaluateSequential(pipelineHelper, spliterator);
            }

            public int getOpFlags() {
                return StreamOpFlag.NOT_ORDERED;
            }

            public CountingSink makeSink() {
                return new CountingSink.OfRef();
            }
        };
    }
}
