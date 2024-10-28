package j$.util.stream;

import j$.util.Objects;
import j$.util.Spliterator;
import j$.util.concurrent.ConcurrentHashMap;
import j$.util.function.Consumer$CC;
import j$.util.function.DoubleConsumer$CC;
import j$.util.function.IntConsumer$CC;
import j$.util.function.LongConsumer$CC;
import j$.util.stream.Node;
import j$.util.stream.Sink;
import java.util.concurrent.CountedCompleter;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;

abstract class ForEachOps {

    static abstract class ForEachOp implements TerminalOp, TerminalSink {
        private final boolean ordered;

        static final class OfDouble extends ForEachOp implements Sink.OfDouble {
            final DoubleConsumer consumer;

            OfDouble(DoubleConsumer doubleConsumer, boolean z) {
                super(z);
                this.consumer = doubleConsumer;
            }

            public void accept(double d) {
                this.consumer.accept(d);
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

            public /* bridge */ /* synthetic */ Object evaluateParallel(PipelineHelper pipelineHelper, Spliterator spliterator) {
                return super.evaluateParallel(pipelineHelper, spliterator);
            }

            public /* bridge */ /* synthetic */ Object evaluateSequential(PipelineHelper pipelineHelper, Spliterator spliterator) {
                return super.evaluateSequential(pipelineHelper, spliterator);
            }

            public /* bridge */ /* synthetic */ Object get() {
                return super.get();
            }
        }

        static final class OfInt extends ForEachOp implements Sink.OfInt {
            final IntConsumer consumer;

            OfInt(IntConsumer intConsumer, boolean z) {
                super(z);
                this.consumer = intConsumer;
            }

            public void accept(int i) {
                this.consumer.accept(i);
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

            public /* bridge */ /* synthetic */ Object evaluateParallel(PipelineHelper pipelineHelper, Spliterator spliterator) {
                return super.evaluateParallel(pipelineHelper, spliterator);
            }

            public /* bridge */ /* synthetic */ Object evaluateSequential(PipelineHelper pipelineHelper, Spliterator spliterator) {
                return super.evaluateSequential(pipelineHelper, spliterator);
            }

            public /* bridge */ /* synthetic */ Object get() {
                return super.get();
            }
        }

        static final class OfLong extends ForEachOp implements Sink.OfLong {
            final LongConsumer consumer;

            OfLong(LongConsumer longConsumer, boolean z) {
                super(z);
                this.consumer = longConsumer;
            }

            public void accept(long j) {
                this.consumer.accept(j);
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

            public /* bridge */ /* synthetic */ Object evaluateParallel(PipelineHelper pipelineHelper, Spliterator spliterator) {
                return super.evaluateParallel(pipelineHelper, spliterator);
            }

            public /* bridge */ /* synthetic */ Object evaluateSequential(PipelineHelper pipelineHelper, Spliterator spliterator) {
                return super.evaluateSequential(pipelineHelper, spliterator);
            }

            public /* bridge */ /* synthetic */ Object get() {
                return super.get();
            }
        }

        static final class OfRef extends ForEachOp {
            final Consumer consumer;

            OfRef(Consumer consumer2, boolean z) {
                super(z);
                this.consumer = consumer2;
            }

            public void accept(Object obj) {
                this.consumer.accept(obj);
            }

            public /* bridge */ /* synthetic */ Object evaluateParallel(PipelineHelper pipelineHelper, Spliterator spliterator) {
                return super.evaluateParallel(pipelineHelper, spliterator);
            }

            public /* bridge */ /* synthetic */ Object evaluateSequential(PipelineHelper pipelineHelper, Spliterator spliterator) {
                return super.evaluateSequential(pipelineHelper, spliterator);
            }

            public /* bridge */ /* synthetic */ Object get() {
                return super.get();
            }
        }

        protected ForEachOp(boolean z) {
            this.ordered = z;
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

        public /* synthetic */ boolean cancellationRequested() {
            return Sink.CC.$default$cancellationRequested(this);
        }

        public /* synthetic */ void end() {
            Sink.CC.$default$end(this);
        }

        public Void evaluateParallel(PipelineHelper pipelineHelper, Spliterator spliterator) {
            (this.ordered ? new ForEachOrderedTask(pipelineHelper, spliterator, (Sink) this) : new ForEachTask(pipelineHelper, spliterator, pipelineHelper.wrapSink(this))).invoke();
            return null;
        }

        public Void evaluateSequential(PipelineHelper pipelineHelper, Spliterator spliterator) {
            return ((ForEachOp) pipelineHelper.wrapAndCopyInto(this, spliterator)).get();
        }

        public Void get() {
            return null;
        }

        public int getOpFlags() {
            if (this.ordered) {
                return 0;
            }
            return StreamOpFlag.NOT_ORDERED;
        }
    }

    static final class ForEachOrderedTask extends CountedCompleter {
        private final Sink action;
        private final ConcurrentHashMap completionMap;
        private final PipelineHelper helper;
        private final ForEachOrderedTask leftPredecessor;
        private Node node;
        private Spliterator spliterator;
        private final long targetSize;

        ForEachOrderedTask(ForEachOrderedTask forEachOrderedTask, Spliterator spliterator2, ForEachOrderedTask forEachOrderedTask2) {
            super(forEachOrderedTask);
            this.helper = forEachOrderedTask.helper;
            this.spliterator = spliterator2;
            this.targetSize = forEachOrderedTask.targetSize;
            this.completionMap = forEachOrderedTask.completionMap;
            this.action = forEachOrderedTask.action;
            this.leftPredecessor = forEachOrderedTask2;
        }

        protected ForEachOrderedTask(PipelineHelper pipelineHelper, Spliterator spliterator2, Sink sink) {
            super((CountedCompleter) null);
            this.helper = pipelineHelper;
            this.spliterator = spliterator2;
            this.targetSize = AbstractTask.suggestTargetSize(spliterator2.estimateSize());
            this.completionMap = new ConcurrentHashMap(Math.max(16, AbstractTask.getLeafTarget() << 1));
            this.action = sink;
            this.leftPredecessor = null;
        }

        private static void doCompute(ForEachOrderedTask forEachOrderedTask) {
            Spliterator trySplit;
            Spliterator spliterator2 = forEachOrderedTask.spliterator;
            long j = forEachOrderedTask.targetSize;
            boolean z = false;
            while (spliterator2.estimateSize() > j && (trySplit = spliterator2.trySplit()) != null) {
                ForEachOrderedTask forEachOrderedTask2 = new ForEachOrderedTask(forEachOrderedTask, trySplit, forEachOrderedTask.leftPredecessor);
                ForEachOrderedTask forEachOrderedTask3 = new ForEachOrderedTask(forEachOrderedTask, spliterator2, forEachOrderedTask2);
                forEachOrderedTask.addToPendingCount(1);
                forEachOrderedTask3.addToPendingCount(1);
                forEachOrderedTask.completionMap.put(forEachOrderedTask2, forEachOrderedTask3);
                if (forEachOrderedTask.leftPredecessor != null) {
                    forEachOrderedTask2.addToPendingCount(1);
                    if (forEachOrderedTask.completionMap.replace(forEachOrderedTask.leftPredecessor, forEachOrderedTask, forEachOrderedTask2)) {
                        forEachOrderedTask.addToPendingCount(-1);
                    } else {
                        forEachOrderedTask2.addToPendingCount(-1);
                    }
                }
                if (z) {
                    spliterator2 = trySplit;
                    forEachOrderedTask = forEachOrderedTask2;
                    forEachOrderedTask2 = forEachOrderedTask3;
                } else {
                    forEachOrderedTask = forEachOrderedTask3;
                }
                z = !z;
                forEachOrderedTask2.fork();
            }
            if (forEachOrderedTask.getPendingCount() > 0) {
                ForEachOps$ForEachOrderedTask$$ExternalSyntheticLambda0 forEachOps$ForEachOrderedTask$$ExternalSyntheticLambda0 = new ForEachOps$ForEachOrderedTask$$ExternalSyntheticLambda0();
                PipelineHelper pipelineHelper = forEachOrderedTask.helper;
                forEachOrderedTask.node = ((Node.Builder) forEachOrderedTask.helper.wrapAndCopyInto(pipelineHelper.makeNodeBuilder(pipelineHelper.exactOutputSizeIfKnown(spliterator2), forEachOps$ForEachOrderedTask$$ExternalSyntheticLambda0), spliterator2)).build();
                forEachOrderedTask.spliterator = null;
            }
            forEachOrderedTask.tryComplete();
        }

        static /* synthetic */ Object[] lambda$doCompute$0(int i) {
            return new Object[i];
        }

        public final void compute() {
            doCompute(this);
        }

        public void onCompletion(CountedCompleter countedCompleter) {
            Node node2 = this.node;
            if (node2 != null) {
                node2.forEach(this.action);
                this.node = null;
            } else {
                Spliterator spliterator2 = this.spliterator;
                if (spliterator2 != null) {
                    this.helper.wrapAndCopyInto(this.action, spliterator2);
                    this.spliterator = null;
                }
            }
            ForEachOrderedTask forEachOrderedTask = (ForEachOrderedTask) this.completionMap.remove(this);
            if (forEachOrderedTask != null) {
                forEachOrderedTask.tryComplete();
            }
        }
    }

    static final class ForEachTask extends CountedCompleter {
        private final PipelineHelper helper;
        private final Sink sink;
        private Spliterator spliterator;
        private long targetSize;

        ForEachTask(ForEachTask forEachTask, Spliterator spliterator2) {
            super(forEachTask);
            this.spliterator = spliterator2;
            this.sink = forEachTask.sink;
            this.targetSize = forEachTask.targetSize;
            this.helper = forEachTask.helper;
        }

        ForEachTask(PipelineHelper pipelineHelper, Spliterator spliterator2, Sink sink2) {
            super((CountedCompleter) null);
            this.sink = sink2;
            this.helper = pipelineHelper;
            this.spliterator = spliterator2;
            this.targetSize = 0;
        }

        public void compute() {
            Spliterator trySplit;
            Spliterator spliterator2 = this.spliterator;
            long estimateSize = spliterator2.estimateSize();
            long j = this.targetSize;
            if (j == 0) {
                j = AbstractTask.suggestTargetSize(estimateSize);
                this.targetSize = j;
            }
            boolean isKnown = StreamOpFlag.SHORT_CIRCUIT.isKnown(this.helper.getStreamAndOpFlags());
            Sink sink2 = this.sink;
            boolean z = false;
            ForEachTask forEachTask = this;
            while (true) {
                if (isKnown && sink2.cancellationRequested()) {
                    break;
                } else if (estimateSize <= j || (trySplit = spliterator2.trySplit()) == null) {
                    forEachTask.helper.copyInto(sink2, spliterator2);
                } else {
                    ForEachTask forEachTask2 = new ForEachTask(forEachTask, trySplit);
                    forEachTask.addToPendingCount(1);
                    if (z) {
                        spliterator2 = trySplit;
                    } else {
                        ForEachTask forEachTask3 = forEachTask;
                        forEachTask = forEachTask2;
                        forEachTask2 = forEachTask3;
                    }
                    z = !z;
                    forEachTask.fork();
                    forEachTask = forEachTask2;
                    estimateSize = spliterator2.estimateSize();
                }
            }
            forEachTask.helper.copyInto(sink2, spliterator2);
            forEachTask.spliterator = null;
            forEachTask.propagateCompletion();
        }
    }

    public static TerminalOp makeDouble(DoubleConsumer doubleConsumer, boolean z) {
        Objects.requireNonNull(doubleConsumer);
        return new ForEachOp.OfDouble(doubleConsumer, z);
    }

    public static TerminalOp makeInt(IntConsumer intConsumer, boolean z) {
        Objects.requireNonNull(intConsumer);
        return new ForEachOp.OfInt(intConsumer, z);
    }

    public static TerminalOp makeLong(LongConsumer longConsumer, boolean z) {
        Objects.requireNonNull(longConsumer);
        return new ForEachOp.OfLong(longConsumer, z);
    }

    public static TerminalOp makeRef(Consumer consumer, boolean z) {
        Objects.requireNonNull(consumer);
        return new ForEachOp.OfRef(consumer, z);
    }
}
