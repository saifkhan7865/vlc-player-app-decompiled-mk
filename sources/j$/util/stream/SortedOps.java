package j$.util.stream;

import j$.util.Collection;
import j$.util.Comparator;
import j$.util.List;
import j$.util.Objects;
import j$.util.Spliterator;
import j$.util.stream.DoublePipeline;
import j$.util.stream.IntPipeline;
import j$.util.stream.LongPipeline;
import j$.util.stream.Node;
import j$.util.stream.ReferencePipeline;
import j$.util.stream.Sink;
import j$.util.stream.SpinedBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.function.IntFunction;

abstract class SortedOps {

    private static abstract class AbstractDoubleSortingSink extends Sink.ChainedDouble {
        protected boolean cancellationRequestedCalled;

        AbstractDoubleSortingSink(Sink sink) {
            super(sink);
        }

        public final boolean cancellationRequested() {
            this.cancellationRequestedCalled = true;
            return false;
        }
    }

    private static abstract class AbstractIntSortingSink extends Sink.ChainedInt {
        protected boolean cancellationRequestedCalled;

        AbstractIntSortingSink(Sink sink) {
            super(sink);
        }

        public final boolean cancellationRequested() {
            this.cancellationRequestedCalled = true;
            return false;
        }
    }

    private static abstract class AbstractLongSortingSink extends Sink.ChainedLong {
        protected boolean cancellationRequestedCalled;

        AbstractLongSortingSink(Sink sink) {
            super(sink);
        }

        public final boolean cancellationRequested() {
            this.cancellationRequestedCalled = true;
            return false;
        }
    }

    private static abstract class AbstractRefSortingSink extends Sink.ChainedReference {
        protected boolean cancellationRequestedCalled;
        protected final Comparator comparator;

        AbstractRefSortingSink(Sink sink, Comparator comparator2) {
            super(sink);
            this.comparator = comparator2;
        }

        public final boolean cancellationRequested() {
            this.cancellationRequestedCalled = true;
            return false;
        }
    }

    private static final class DoubleSortingSink extends AbstractDoubleSortingSink {
        private SpinedBuffer.OfDouble b;

        DoubleSortingSink(Sink sink) {
            super(sink);
        }

        public void accept(double d) {
            this.b.accept(d);
        }

        public void begin(long j) {
            SpinedBuffer.OfDouble ofDouble;
            if (j < 2147483639) {
                if (j > 0) {
                    int i = (int) j;
                } else {
                    ofDouble = new SpinedBuffer.OfDouble();
                }
                this.b = ofDouble;
                return;
            }
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }

        public void end() {
            double[] dArr = (double[]) this.b.asPrimitiveArray();
            Arrays.sort(dArr);
            this.downstream.begin((long) dArr.length);
            int i = 0;
            if (!this.cancellationRequestedCalled) {
                int length = dArr.length;
                while (i < length) {
                    this.downstream.accept(dArr[i]);
                    i++;
                }
            } else {
                int length2 = dArr.length;
                while (i < length2) {
                    double d = dArr[i];
                    if (this.downstream.cancellationRequested()) {
                        break;
                    }
                    this.downstream.accept(d);
                    i++;
                }
            }
            this.downstream.end();
        }
    }

    private static final class IntSortingSink extends AbstractIntSortingSink {
        private SpinedBuffer.OfInt b;

        IntSortingSink(Sink sink) {
            super(sink);
        }

        public void accept(int i) {
            this.b.accept(i);
        }

        public void begin(long j) {
            SpinedBuffer.OfInt ofInt;
            if (j < 2147483639) {
                if (j > 0) {
                    int i = (int) j;
                } else {
                    ofInt = new SpinedBuffer.OfInt();
                }
                this.b = ofInt;
                return;
            }
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }

        public void end() {
            int[] iArr = (int[]) this.b.asPrimitiveArray();
            Arrays.sort(iArr);
            this.downstream.begin((long) iArr.length);
            int i = 0;
            if (!this.cancellationRequestedCalled) {
                int length = iArr.length;
                while (i < length) {
                    this.downstream.accept(iArr[i]);
                    i++;
                }
            } else {
                int length2 = iArr.length;
                while (i < length2) {
                    int i2 = iArr[i];
                    if (this.downstream.cancellationRequested()) {
                        break;
                    }
                    this.downstream.accept(i2);
                    i++;
                }
            }
            this.downstream.end();
        }
    }

    private static final class LongSortingSink extends AbstractLongSortingSink {
        private SpinedBuffer.OfLong b;

        LongSortingSink(Sink sink) {
            super(sink);
        }

        public void accept(long j) {
            this.b.accept(j);
        }

        public void begin(long j) {
            SpinedBuffer.OfLong ofLong;
            if (j < 2147483639) {
                if (j > 0) {
                    int i = (int) j;
                } else {
                    ofLong = new SpinedBuffer.OfLong();
                }
                this.b = ofLong;
                return;
            }
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }

        public void end() {
            long[] jArr = (long[]) this.b.asPrimitiveArray();
            Arrays.sort(jArr);
            this.downstream.begin((long) jArr.length);
            int i = 0;
            if (!this.cancellationRequestedCalled) {
                int length = jArr.length;
                while (i < length) {
                    this.downstream.accept(jArr[i]);
                    i++;
                }
            } else {
                int length2 = jArr.length;
                while (i < length2) {
                    long j = jArr[i];
                    if (this.downstream.cancellationRequested()) {
                        break;
                    }
                    this.downstream.accept(j);
                    i++;
                }
            }
            this.downstream.end();
        }
    }

    private static final class OfDouble extends DoublePipeline.StatefulOp {
        OfDouble(AbstractPipeline abstractPipeline) {
            super(abstractPipeline, StreamShape.DOUBLE_VALUE, StreamOpFlag.IS_ORDERED | StreamOpFlag.IS_SORTED);
        }

        public Node opEvaluateParallel(PipelineHelper pipelineHelper, Spliterator spliterator, IntFunction intFunction) {
            if (StreamOpFlag.SORTED.isKnown(pipelineHelper.getStreamAndOpFlags())) {
                return pipelineHelper.evaluate(spliterator, false, intFunction);
            }
            double[] dArr = (double[]) ((Node.OfDouble) pipelineHelper.evaluate(spliterator, true, intFunction)).asPrimitiveArray();
            Arrays.sort(dArr);
            return Nodes.node(dArr);
        }

        public Sink opWrapSink(int i, Sink sink) {
            Objects.requireNonNull(sink);
            return StreamOpFlag.SORTED.isKnown(i) ? sink : StreamOpFlag.SIZED.isKnown(i) ? new SizedDoubleSortingSink(sink) : new DoubleSortingSink(sink);
        }
    }

    private static final class OfInt extends IntPipeline.StatefulOp {
        OfInt(AbstractPipeline abstractPipeline) {
            super(abstractPipeline, StreamShape.INT_VALUE, StreamOpFlag.IS_ORDERED | StreamOpFlag.IS_SORTED);
        }

        public Node opEvaluateParallel(PipelineHelper pipelineHelper, Spliterator spliterator, IntFunction intFunction) {
            if (StreamOpFlag.SORTED.isKnown(pipelineHelper.getStreamAndOpFlags())) {
                return pipelineHelper.evaluate(spliterator, false, intFunction);
            }
            int[] iArr = (int[]) ((Node.OfInt) pipelineHelper.evaluate(spliterator, true, intFunction)).asPrimitiveArray();
            Arrays.sort(iArr);
            return Nodes.node(iArr);
        }

        public Sink opWrapSink(int i, Sink sink) {
            Objects.requireNonNull(sink);
            return StreamOpFlag.SORTED.isKnown(i) ? sink : StreamOpFlag.SIZED.isKnown(i) ? new SizedIntSortingSink(sink) : new IntSortingSink(sink);
        }
    }

    private static final class OfLong extends LongPipeline.StatefulOp {
        OfLong(AbstractPipeline abstractPipeline) {
            super(abstractPipeline, StreamShape.LONG_VALUE, StreamOpFlag.IS_ORDERED | StreamOpFlag.IS_SORTED);
        }

        public Node opEvaluateParallel(PipelineHelper pipelineHelper, Spliterator spliterator, IntFunction intFunction) {
            if (StreamOpFlag.SORTED.isKnown(pipelineHelper.getStreamAndOpFlags())) {
                return pipelineHelper.evaluate(spliterator, false, intFunction);
            }
            long[] jArr = (long[]) ((Node.OfLong) pipelineHelper.evaluate(spliterator, true, intFunction)).asPrimitiveArray();
            Arrays.sort(jArr);
            return Nodes.node(jArr);
        }

        public Sink opWrapSink(int i, Sink sink) {
            Objects.requireNonNull(sink);
            return StreamOpFlag.SORTED.isKnown(i) ? sink : StreamOpFlag.SIZED.isKnown(i) ? new SizedLongSortingSink(sink) : new LongSortingSink(sink);
        }
    }

    private static final class OfRef extends ReferencePipeline.StatefulOp {
        private final Comparator comparator;
        private final boolean isNaturalSort;

        OfRef(AbstractPipeline abstractPipeline) {
            super(abstractPipeline, StreamShape.REFERENCE, StreamOpFlag.IS_ORDERED | StreamOpFlag.IS_SORTED);
            this.isNaturalSort = true;
            this.comparator = Comparator.CC.naturalOrder();
        }

        OfRef(AbstractPipeline abstractPipeline, java.util.Comparator comparator2) {
            super(abstractPipeline, StreamShape.REFERENCE, StreamOpFlag.IS_ORDERED | StreamOpFlag.NOT_SORTED);
            this.isNaturalSort = false;
            this.comparator = (java.util.Comparator) Objects.requireNonNull(comparator2);
        }

        public Node opEvaluateParallel(PipelineHelper pipelineHelper, Spliterator spliterator, IntFunction intFunction) {
            if (StreamOpFlag.SORTED.isKnown(pipelineHelper.getStreamAndOpFlags()) && this.isNaturalSort) {
                return pipelineHelper.evaluate(spliterator, false, intFunction);
            }
            Object[] asArray = pipelineHelper.evaluate(spliterator, true, intFunction).asArray(intFunction);
            Arrays.sort(asArray, this.comparator);
            return Nodes.node(asArray);
        }

        public Sink opWrapSink(int i, Sink sink) {
            Objects.requireNonNull(sink);
            return (!StreamOpFlag.SORTED.isKnown(i) || !this.isNaturalSort) ? StreamOpFlag.SIZED.isKnown(i) ? new SizedRefSortingSink(sink, this.comparator) : new RefSortingSink(sink, this.comparator) : sink;
        }
    }

    private static final class RefSortingSink extends AbstractRefSortingSink {
        private ArrayList list;

        RefSortingSink(Sink sink, java.util.Comparator comparator) {
            super(sink, comparator);
        }

        public void accept(Object obj) {
            this.list.add(obj);
        }

        public void begin(long j) {
            ArrayList arrayList;
            if (j < 2147483639) {
                if (j >= 0) {
                    int i = (int) j;
                } else {
                    arrayList = new ArrayList();
                }
                this.list = arrayList;
                return;
            }
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }

        public void end() {
            List.EL.sort(this.list, this.comparator);
            this.downstream.begin((long) this.list.size());
            if (!this.cancellationRequestedCalled) {
                ArrayList arrayList = this.list;
                Sink sink = this.downstream;
                Objects.requireNonNull(sink);
                Collection.EL.forEach(arrayList, new SortedOps$RefSortingSink$$ExternalSyntheticLambda0(sink));
            } else {
                Iterator it = this.list.iterator();
                while (it.hasNext()) {
                    Object next = it.next();
                    if (this.downstream.cancellationRequested()) {
                        break;
                    }
                    this.downstream.accept(next);
                }
            }
            this.downstream.end();
            this.list = null;
        }
    }

    private static final class SizedDoubleSortingSink extends AbstractDoubleSortingSink {
        private double[] array;
        private int offset;

        SizedDoubleSortingSink(Sink sink) {
            super(sink);
        }

        public void accept(double d) {
            double[] dArr = this.array;
            int i = this.offset;
            this.offset = i + 1;
            dArr[i] = d;
        }

        public void begin(long j) {
            if (j < 2147483639) {
                this.array = new double[((int) j)];
                return;
            }
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }

        public void end() {
            int i = 0;
            Arrays.sort(this.array, 0, this.offset);
            this.downstream.begin((long) this.offset);
            if (!this.cancellationRequestedCalled) {
                while (i < this.offset) {
                    this.downstream.accept(this.array[i]);
                    i++;
                }
            } else {
                while (i < this.offset && !this.downstream.cancellationRequested()) {
                    this.downstream.accept(this.array[i]);
                    i++;
                }
            }
            this.downstream.end();
            this.array = null;
        }
    }

    private static final class SizedIntSortingSink extends AbstractIntSortingSink {
        private int[] array;
        private int offset;

        SizedIntSortingSink(Sink sink) {
            super(sink);
        }

        public void accept(int i) {
            int[] iArr = this.array;
            int i2 = this.offset;
            this.offset = i2 + 1;
            iArr[i2] = i;
        }

        public void begin(long j) {
            if (j < 2147483639) {
                this.array = new int[((int) j)];
                return;
            }
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }

        public void end() {
            int i = 0;
            Arrays.sort(this.array, 0, this.offset);
            this.downstream.begin((long) this.offset);
            if (!this.cancellationRequestedCalled) {
                while (i < this.offset) {
                    this.downstream.accept(this.array[i]);
                    i++;
                }
            } else {
                while (i < this.offset && !this.downstream.cancellationRequested()) {
                    this.downstream.accept(this.array[i]);
                    i++;
                }
            }
            this.downstream.end();
            this.array = null;
        }
    }

    private static final class SizedLongSortingSink extends AbstractLongSortingSink {
        private long[] array;
        private int offset;

        SizedLongSortingSink(Sink sink) {
            super(sink);
        }

        public void accept(long j) {
            long[] jArr = this.array;
            int i = this.offset;
            this.offset = i + 1;
            jArr[i] = j;
        }

        public void begin(long j) {
            if (j < 2147483639) {
                this.array = new long[((int) j)];
                return;
            }
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }

        public void end() {
            int i = 0;
            Arrays.sort(this.array, 0, this.offset);
            this.downstream.begin((long) this.offset);
            if (!this.cancellationRequestedCalled) {
                while (i < this.offset) {
                    this.downstream.accept(this.array[i]);
                    i++;
                }
            } else {
                while (i < this.offset && !this.downstream.cancellationRequested()) {
                    this.downstream.accept(this.array[i]);
                    i++;
                }
            }
            this.downstream.end();
            this.array = null;
        }
    }

    private static final class SizedRefSortingSink extends AbstractRefSortingSink {
        private Object[] array;
        private int offset;

        SizedRefSortingSink(Sink sink, java.util.Comparator comparator) {
            super(sink, comparator);
        }

        public void accept(Object obj) {
            Object[] objArr = this.array;
            int i = this.offset;
            this.offset = i + 1;
            objArr[i] = obj;
        }

        public void begin(long j) {
            if (j < 2147483639) {
                this.array = new Object[((int) j)];
                return;
            }
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }

        public void end() {
            int i = 0;
            Arrays.sort(this.array, 0, this.offset, this.comparator);
            this.downstream.begin((long) this.offset);
            if (!this.cancellationRequestedCalled) {
                while (i < this.offset) {
                    this.downstream.accept(this.array[i]);
                    i++;
                }
            } else {
                while (i < this.offset && !this.downstream.cancellationRequested()) {
                    this.downstream.accept(this.array[i]);
                    i++;
                }
            }
            this.downstream.end();
            this.array = null;
        }
    }

    static DoubleStream makeDouble(AbstractPipeline abstractPipeline) {
        return new OfDouble(abstractPipeline);
    }

    static IntStream makeInt(AbstractPipeline abstractPipeline) {
        return new OfInt(abstractPipeline);
    }

    static LongStream makeLong(AbstractPipeline abstractPipeline) {
        return new OfLong(abstractPipeline);
    }

    static Stream makeRef(AbstractPipeline abstractPipeline) {
        return new OfRef(abstractPipeline);
    }

    static Stream makeRef(AbstractPipeline abstractPipeline, java.util.Comparator comparator) {
        return new OfRef(abstractPipeline, comparator);
    }
}
