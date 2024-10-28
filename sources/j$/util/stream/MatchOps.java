package j$.util.stream;

import j$.util.Objects;
import j$.util.Spliterator;
import j$.util.function.Consumer$CC;
import j$.util.function.DoubleConsumer$CC;
import j$.util.function.IntConsumer$CC;
import j$.util.function.LongConsumer$CC;
import j$.util.stream.Sink;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.DoublePredicate;
import java.util.function.IntConsumer;
import java.util.function.IntPredicate;
import java.util.function.LongConsumer;
import java.util.function.LongPredicate;
import java.util.function.Predicate;
import java.util.function.Supplier;

abstract class MatchOps {

    private static abstract class BooleanTerminalSink implements Sink {
        boolean stop;
        boolean value;

        BooleanTerminalSink(MatchKind matchKind) {
            this.value = !matchKind.shortCircuitResult;
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

        public /* synthetic */ void begin(long j) {
            Sink.CC.$default$begin(this, j);
        }

        public boolean cancellationRequested() {
            return this.stop;
        }

        public /* synthetic */ void end() {
            Sink.CC.$default$end(this);
        }

        public boolean getAndClearState() {
            return this.value;
        }
    }

    enum MatchKind {
        ANY(true, true),
        ALL(false, false),
        NONE(true, false);
        
        /* access modifiers changed from: private */
        public final boolean shortCircuitResult;
        /* access modifiers changed from: private */
        public final boolean stopOnPredicateMatches;

        private MatchKind(boolean z, boolean z2) {
            this.stopOnPredicateMatches = z;
            this.shortCircuitResult = z2;
        }
    }

    private static final class MatchOp implements TerminalOp {
        private final StreamShape inputShape;
        final MatchKind matchKind;
        final Supplier sinkSupplier;

        MatchOp(StreamShape streamShape, MatchKind matchKind2, Supplier supplier) {
            this.inputShape = streamShape;
            this.matchKind = matchKind2;
            this.sinkSupplier = supplier;
        }

        public Boolean evaluateParallel(PipelineHelper pipelineHelper, Spliterator spliterator) {
            return (Boolean) new MatchTask(this, pipelineHelper, spliterator).invoke();
        }

        public Boolean evaluateSequential(PipelineHelper pipelineHelper, Spliterator spliterator) {
            return Boolean.valueOf(((BooleanTerminalSink) pipelineHelper.wrapAndCopyInto((BooleanTerminalSink) this.sinkSupplier.get(), spliterator)).getAndClearState());
        }

        public int getOpFlags() {
            return StreamOpFlag.IS_SHORT_CIRCUIT | StreamOpFlag.NOT_ORDERED;
        }
    }

    private static final class MatchTask extends AbstractShortCircuitTask {
        private final MatchOp op;

        MatchTask(MatchOp matchOp, PipelineHelper pipelineHelper, Spliterator spliterator) {
            super(pipelineHelper, spliterator);
            this.op = matchOp;
        }

        MatchTask(MatchTask matchTask, Spliterator spliterator) {
            super((AbstractShortCircuitTask) matchTask, spliterator);
            this.op = matchTask.op;
        }

        /* access modifiers changed from: protected */
        public Boolean doLeaf() {
            boolean andClearState = ((BooleanTerminalSink) this.helper.wrapAndCopyInto((BooleanTerminalSink) this.op.sinkSupplier.get(), this.spliterator)).getAndClearState();
            if (andClearState != this.op.matchKind.shortCircuitResult) {
                return null;
            }
            shortCircuit(Boolean.valueOf(andClearState));
            return null;
        }

        /* access modifiers changed from: protected */
        public Boolean getEmptyResult() {
            return Boolean.valueOf(!this.op.matchKind.shortCircuitResult);
        }

        /* access modifiers changed from: protected */
        public MatchTask makeChild(Spliterator spliterator) {
            return new MatchTask(this, spliterator);
        }
    }

    static /* synthetic */ BooleanTerminalSink lambda$makeDouble$3(MatchKind matchKind, DoublePredicate doublePredicate) {
        return new Sink.OfDouble(doublePredicate) {
            /* JADX WARNING: type inference failed for: r0v1, types: [java.lang.Throwable, java.util.function.DoublePredicate] */
            public void accept(double d) {
                if (!this.stop) {
                    ? r0 = 0;
                    r0.test(d);
                    throw r0;
                }
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
        };
    }

    static /* synthetic */ BooleanTerminalSink lambda$makeInt$1(MatchKind matchKind, IntPredicate intPredicate) {
        return new Sink.OfInt(intPredicate) {
            /* JADX WARNING: type inference failed for: r0v1, types: [java.lang.Throwable, java.util.function.IntPredicate] */
            public void accept(int i) {
                if (!this.stop) {
                    ? r0 = 0;
                    r0.test(i);
                    throw r0;
                }
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
        };
    }

    static /* synthetic */ BooleanTerminalSink lambda$makeLong$2(MatchKind matchKind, LongPredicate longPredicate) {
        return new Sink.OfLong(longPredicate) {
            /* JADX WARNING: type inference failed for: r0v1, types: [java.lang.Throwable, java.util.function.LongPredicate] */
            public void accept(long j) {
                if (!this.stop) {
                    ? r0 = 0;
                    r0.test(j);
                    throw r0;
                }
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
        };
    }

    static /* synthetic */ BooleanTerminalSink lambda$makeRef$0(MatchKind matchKind, Predicate predicate) {
        return new BooleanTerminalSink(predicate) {
            final /* synthetic */ Predicate val$predicate;

            {
                this.val$predicate = r2;
            }

            public void accept(Object obj) {
                if (!this.stop && this.val$predicate.test(obj) == MatchKind.this.stopOnPredicateMatches) {
                    this.stop = true;
                    this.value = MatchKind.this.shortCircuitResult;
                }
            }
        };
    }

    public static TerminalOp makeDouble(DoublePredicate doublePredicate, MatchKind matchKind) {
        Objects.requireNonNull(doublePredicate);
        Objects.requireNonNull(matchKind);
        return new MatchOp(StreamShape.DOUBLE_VALUE, matchKind, new MatchOps$$ExternalSyntheticLambda2(matchKind, doublePredicate));
    }

    public static TerminalOp makeInt(IntPredicate intPredicate, MatchKind matchKind) {
        Objects.requireNonNull(intPredicate);
        Objects.requireNonNull(matchKind);
        return new MatchOp(StreamShape.INT_VALUE, matchKind, new MatchOps$$ExternalSyntheticLambda1(matchKind, intPredicate));
    }

    public static TerminalOp makeLong(LongPredicate longPredicate, MatchKind matchKind) {
        Objects.requireNonNull(longPredicate);
        Objects.requireNonNull(matchKind);
        return new MatchOp(StreamShape.LONG_VALUE, matchKind, new MatchOps$$ExternalSyntheticLambda0(matchKind, longPredicate));
    }

    public static TerminalOp makeRef(Predicate predicate, MatchKind matchKind) {
        Objects.requireNonNull(predicate);
        Objects.requireNonNull(matchKind);
        return new MatchOp(StreamShape.REFERENCE, matchKind, new MatchOps$$ExternalSyntheticLambda3(matchKind, predicate));
    }
}
