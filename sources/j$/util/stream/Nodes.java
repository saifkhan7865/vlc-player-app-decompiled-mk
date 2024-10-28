package j$.util.stream;

import j$.util.Collection;
import j$.util.DesugarArrays;
import j$.util.Objects;
import j$.util.Spliterator;
import j$.util.Spliterators;
import j$.util.function.Consumer$CC;
import j$.util.function.DoubleConsumer$CC;
import j$.util.function.IntConsumer$CC;
import j$.util.function.LongConsumer$CC;
import j$.util.stream.Node;
import j$.util.stream.Sink;
import j$.util.stream.SpinedBuffer;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Deque;
import java.util.concurrent.CountedCompleter;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.LongConsumer;
import java.util.function.LongFunction;

abstract class Nodes {
    /* access modifiers changed from: private */
    public static final double[] EMPTY_DOUBLE_ARRAY = new double[0];
    private static final Node.OfDouble EMPTY_DOUBLE_NODE = new EmptyNode.OfDouble();
    /* access modifiers changed from: private */
    public static final int[] EMPTY_INT_ARRAY = new int[0];
    private static final Node.OfInt EMPTY_INT_NODE = new EmptyNode.OfInt();
    /* access modifiers changed from: private */
    public static final long[] EMPTY_LONG_ARRAY = new long[0];
    private static final Node.OfLong EMPTY_LONG_NODE = new EmptyNode.OfLong();
    private static final Node EMPTY_NODE = new EmptyNode.OfRef();

    /* renamed from: j$.util.stream.Nodes$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
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
            throw new UnsupportedOperationException("Method not decompiled: j$.util.stream.Nodes.AnonymousClass1.<clinit>():void");
        }
    }

    private static abstract class AbstractConcNode implements Node {
        protected final Node left;
        protected final Node right;
        private final long size;

        AbstractConcNode(Node node, Node node2) {
            this.left = node;
            this.right = node2;
            this.size = node.count() + node2.count();
        }

        public long count() {
            return this.size;
        }

        public Node getChild(int i) {
            if (i == 0) {
                return this.left;
            }
            if (i == 1) {
                return this.right;
            }
            throw new IndexOutOfBoundsException();
        }

        public int getChildCount() {
            return 2;
        }

        public /* synthetic */ StreamShape getShape() {
            return Node.CC.$default$getShape(this);
        }
    }

    private static class ArrayNode implements Node {
        final Object[] array;
        int curSize;

        ArrayNode(long j, IntFunction intFunction) {
            if (j < 2147483639) {
                this.array = (Object[]) intFunction.apply((int) j);
                this.curSize = 0;
                return;
            }
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }

        ArrayNode(Object[] objArr) {
            this.array = objArr;
            this.curSize = objArr.length;
        }

        public Object[] asArray(IntFunction intFunction) {
            Object[] objArr = this.array;
            if (objArr.length == this.curSize) {
                return objArr;
            }
            throw new IllegalStateException();
        }

        public void copyInto(Object[] objArr, int i) {
            System.arraycopy(this.array, 0, objArr, i, this.curSize);
        }

        public long count() {
            return (long) this.curSize;
        }

        public void forEach(Consumer consumer) {
            for (int i = 0; i < this.curSize; i++) {
                consumer.accept(this.array[i]);
            }
        }

        public /* synthetic */ Node getChild(int i) {
            return Node.CC.$default$getChild(this, i);
        }

        public /* synthetic */ int getChildCount() {
            return Node.CC.$default$getChildCount(this);
        }

        public Spliterator spliterator() {
            return DesugarArrays.spliterator(this.array, 0, this.curSize);
        }

        public String toString() {
            return String.format("ArrayNode[%d][%s]", new Object[]{Integer.valueOf(this.array.length - this.curSize), Arrays.toString(this.array)});
        }

        public /* synthetic */ Node truncate(long j, long j2, IntFunction intFunction) {
            return Node.CC.$default$truncate(this, j, j2, intFunction);
        }
    }

    private static final class CollectionNode implements Node {
        private final Collection c;

        CollectionNode(Collection collection) {
            this.c = collection;
        }

        public Object[] asArray(IntFunction intFunction) {
            Collection collection = this.c;
            return collection.toArray((Object[]) intFunction.apply(collection.size()));
        }

        public void copyInto(Object[] objArr, int i) {
            for (Object obj : this.c) {
                objArr[i] = obj;
                i++;
            }
        }

        public long count() {
            return (long) this.c.size();
        }

        public void forEach(Consumer consumer) {
            Collection.EL.forEach(this.c, consumer);
        }

        public /* synthetic */ Node getChild(int i) {
            return Node.CC.$default$getChild(this, i);
        }

        public /* synthetic */ int getChildCount() {
            return Node.CC.$default$getChildCount(this);
        }

        public Spliterator spliterator() {
            return Collection.EL.stream(this.c).spliterator();
        }

        public String toString() {
            return String.format("CollectionNode[%d][%s]", new Object[]{Integer.valueOf(this.c.size()), this.c});
        }

        public /* synthetic */ Node truncate(long j, long j2, IntFunction intFunction) {
            return Node.CC.$default$truncate(this, j, j2, intFunction);
        }
    }

    private static class CollectorTask extends AbstractTask {
        protected final LongFunction builderFactory;
        protected final BinaryOperator concFactory;
        protected final PipelineHelper helper;

        private static final class OfDouble extends CollectorTask {
            OfDouble(PipelineHelper pipelineHelper, Spliterator spliterator) {
                super(pipelineHelper, spliterator, new Nodes$CollectorTask$OfDouble$$ExternalSyntheticLambda0(), new Nodes$CollectorTask$OfDouble$$ExternalSyntheticLambda1());
            }

            /* access modifiers changed from: protected */
            public /* bridge */ /* synthetic */ Object doLeaf() {
                return super.doLeaf();
            }

            /* access modifiers changed from: protected */
            public /* bridge */ /* synthetic */ AbstractTask makeChild(Spliterator spliterator) {
                return super.makeChild(spliterator);
            }
        }

        private static final class OfInt extends CollectorTask {
            OfInt(PipelineHelper pipelineHelper, Spliterator spliterator) {
                super(pipelineHelper, spliterator, new Nodes$CollectorTask$OfInt$$ExternalSyntheticLambda0(), new Nodes$CollectorTask$OfInt$$ExternalSyntheticLambda1());
            }

            /* access modifiers changed from: protected */
            public /* bridge */ /* synthetic */ Object doLeaf() {
                return super.doLeaf();
            }

            /* access modifiers changed from: protected */
            public /* bridge */ /* synthetic */ AbstractTask makeChild(Spliterator spliterator) {
                return super.makeChild(spliterator);
            }
        }

        private static final class OfLong extends CollectorTask {
            OfLong(PipelineHelper pipelineHelper, Spliterator spliterator) {
                super(pipelineHelper, spliterator, new Nodes$CollectorTask$OfLong$$ExternalSyntheticLambda0(), new Nodes$CollectorTask$OfLong$$ExternalSyntheticLambda1());
            }

            /* access modifiers changed from: protected */
            public /* bridge */ /* synthetic */ Object doLeaf() {
                return super.doLeaf();
            }

            /* access modifiers changed from: protected */
            public /* bridge */ /* synthetic */ AbstractTask makeChild(Spliterator spliterator) {
                return super.makeChild(spliterator);
            }
        }

        private static final class OfRef extends CollectorTask {
            OfRef(PipelineHelper pipelineHelper, IntFunction intFunction, Spliterator spliterator) {
                super(pipelineHelper, spliterator, new Nodes$CollectorTask$OfRef$$ExternalSyntheticLambda0(intFunction), new Nodes$CollectorTask$OfRef$$ExternalSyntheticLambda1());
            }

            /* access modifiers changed from: protected */
            public /* bridge */ /* synthetic */ Object doLeaf() {
                return super.doLeaf();
            }

            /* access modifiers changed from: protected */
            public /* bridge */ /* synthetic */ AbstractTask makeChild(Spliterator spliterator) {
                return super.makeChild(spliterator);
            }
        }

        CollectorTask(CollectorTask collectorTask, Spliterator spliterator) {
            super((AbstractTask) collectorTask, spliterator);
            this.helper = collectorTask.helper;
            this.builderFactory = collectorTask.builderFactory;
            this.concFactory = collectorTask.concFactory;
        }

        CollectorTask(PipelineHelper pipelineHelper, Spliterator spliterator, LongFunction longFunction, BinaryOperator binaryOperator) {
            super(pipelineHelper, spliterator);
            this.helper = pipelineHelper;
            this.builderFactory = longFunction;
            this.concFactory = binaryOperator;
        }

        /* access modifiers changed from: protected */
        public Node doLeaf() {
            return ((Node.Builder) this.helper.wrapAndCopyInto((Node.Builder) this.builderFactory.apply(this.helper.exactOutputSizeIfKnown(this.spliterator)), this.spliterator)).build();
        }

        /* access modifiers changed from: protected */
        public CollectorTask makeChild(Spliterator spliterator) {
            return new CollectorTask(this, spliterator);
        }

        public void onCompletion(CountedCompleter countedCompleter) {
            if (!isLeaf()) {
                setLocalResult((Node) this.concFactory.apply((Node) ((CollectorTask) this.leftChild).getLocalResult(), (Node) ((CollectorTask) this.rightChild).getLocalResult()));
            }
            super.onCompletion(countedCompleter);
        }
    }

    static final class ConcNode extends AbstractConcNode implements Node {

        static final class OfDouble extends OfPrimitive implements Node.OfDouble {
            OfDouble(Node.OfDouble ofDouble, Node.OfDouble ofDouble2) {
                super(ofDouble, ofDouble2);
            }

            public /* synthetic */ void copyInto(Double[] dArr, int i) {
                Node.OfDouble.CC.$default$copyInto((Node.OfDouble) this, dArr, i);
            }

            public /* bridge */ /* synthetic */ void copyInto(Object[] objArr, int i) {
                copyInto((Double[]) objArr, i);
            }

            public /* synthetic */ void forEach(Consumer consumer) {
                Node.OfDouble.CC.$default$forEach(this, consumer);
            }

            public Spliterator.OfDouble spliterator() {
                return new InternalNodeSpliterator.OfDouble(this);
            }
        }

        static final class OfInt extends OfPrimitive implements Node.OfInt {
            OfInt(Node.OfInt ofInt, Node.OfInt ofInt2) {
                super(ofInt, ofInt2);
            }

            public /* synthetic */ void copyInto(Integer[] numArr, int i) {
                Node.OfInt.CC.$default$copyInto((Node.OfInt) this, numArr, i);
            }

            public /* bridge */ /* synthetic */ void copyInto(Object[] objArr, int i) {
                copyInto((Integer[]) objArr, i);
            }

            public /* synthetic */ void forEach(Consumer consumer) {
                Node.OfInt.CC.$default$forEach(this, consumer);
            }

            public Spliterator.OfInt spliterator() {
                return new InternalNodeSpliterator.OfInt(this);
            }
        }

        static final class OfLong extends OfPrimitive implements Node.OfLong {
            OfLong(Node.OfLong ofLong, Node.OfLong ofLong2) {
                super(ofLong, ofLong2);
            }

            public /* synthetic */ void copyInto(Long[] lArr, int i) {
                Node.OfLong.CC.$default$copyInto((Node.OfLong) this, lArr, i);
            }

            public /* bridge */ /* synthetic */ void copyInto(Object[] objArr, int i) {
                copyInto((Long[]) objArr, i);
            }

            public /* synthetic */ void forEach(Consumer consumer) {
                Node.OfLong.CC.$default$forEach(this, consumer);
            }

            public Spliterator.OfLong spliterator() {
                return new InternalNodeSpliterator.OfLong(this);
            }
        }

        private static abstract class OfPrimitive extends AbstractConcNode implements Node.OfPrimitive {
            OfPrimitive(Node.OfPrimitive ofPrimitive, Node.OfPrimitive ofPrimitive2) {
                super(ofPrimitive, ofPrimitive2);
            }

            public /* synthetic */ Object[] asArray(IntFunction intFunction) {
                return Node.OfPrimitive.CC.$default$asArray(this, intFunction);
            }

            public Object asPrimitiveArray() {
                long count = count();
                if (count < 2147483639) {
                    Object newArray = newArray((int) count);
                    copyInto(newArray, 0);
                    return newArray;
                }
                throw new IllegalArgumentException("Stream size exceeds max array size");
            }

            public void copyInto(Object obj, int i) {
                ((Node.OfPrimitive) this.left).copyInto(obj, i);
                ((Node.OfPrimitive) this.right).copyInto(obj, i + ((int) ((Node.OfPrimitive) this.left).count()));
            }

            public void forEach(Object obj) {
                ((Node.OfPrimitive) this.left).forEach(obj);
                ((Node.OfPrimitive) this.right).forEach(obj);
            }

            public /* bridge */ /* synthetic */ Node.OfPrimitive getChild(int i) {
                return (Node.OfPrimitive) super.getChild(i);
            }

            public String toString() {
                int i = (count() > 32 ? 1 : (count() == 32 ? 0 : -1));
                String name = getClass().getName();
                if (i < 0) {
                    return String.format("%s[%s.%s]", new Object[]{name, this.left, this.right});
                }
                return String.format("%s[size=%d]", new Object[]{name, Long.valueOf(count())});
            }
        }

        ConcNode(Node node, Node node2) {
            super(node, node2);
        }

        public Object[] asArray(IntFunction intFunction) {
            long count = count();
            if (count < 2147483639) {
                Object[] objArr = (Object[]) intFunction.apply((int) count);
                copyInto(objArr, 0);
                return objArr;
            }
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }

        public void copyInto(Object[] objArr, int i) {
            Objects.requireNonNull(objArr);
            this.left.copyInto(objArr, i);
            this.right.copyInto(objArr, i + ((int) this.left.count()));
        }

        public void forEach(Consumer consumer) {
            this.left.forEach(consumer);
            this.right.forEach(consumer);
        }

        public Spliterator spliterator() {
            return new InternalNodeSpliterator.OfRef(this);
        }

        public String toString() {
            if (count() < 32) {
                return String.format("ConcNode[%s.%s]", new Object[]{this.left, this.right});
            }
            return String.format("ConcNode[size=%d]", new Object[]{Long.valueOf(count())});
        }

        public Node truncate(long j, long j2, IntFunction intFunction) {
            if (j == 0 && j2 == count()) {
                return this;
            }
            long count = this.left.count();
            if (j >= count) {
                return this.right.truncate(j - count, j2 - count, intFunction);
            }
            if (j2 <= count) {
                return this.left.truncate(j, j2, intFunction);
            }
            IntFunction intFunction2 = intFunction;
            return Nodes.conc(getShape(), this.left.truncate(j, count, intFunction2), this.right.truncate(0, j2 - count, intFunction2));
        }
    }

    private static class DoubleArrayNode implements Node.OfDouble {
        final double[] array;
        int curSize;

        DoubleArrayNode(long j) {
            if (j < 2147483639) {
                this.array = new double[((int) j)];
                this.curSize = 0;
                return;
            }
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }

        DoubleArrayNode(double[] dArr) {
            this.array = dArr;
            this.curSize = dArr.length;
        }

        public /* synthetic */ Object[] asArray(IntFunction intFunction) {
            return Node.OfPrimitive.CC.$default$asArray(this, intFunction);
        }

        public double[] asPrimitiveArray() {
            double[] dArr = this.array;
            int length = dArr.length;
            int i = this.curSize;
            return length == i ? dArr : Arrays.copyOf(dArr, i);
        }

        public void copyInto(double[] dArr, int i) {
            System.arraycopy(this.array, 0, dArr, i, this.curSize);
        }

        public /* synthetic */ void copyInto(Double[] dArr, int i) {
            Node.OfDouble.CC.$default$copyInto((Node.OfDouble) this, dArr, i);
        }

        public /* bridge */ /* synthetic */ void copyInto(Object[] objArr, int i) {
            copyInto((Double[]) objArr, i);
        }

        public long count() {
            return (long) this.curSize;
        }

        public /* synthetic */ void forEach(Consumer consumer) {
            Node.OfDouble.CC.$default$forEach(this, consumer);
        }

        public void forEach(DoubleConsumer doubleConsumer) {
            for (int i = 0; i < this.curSize; i++) {
                doubleConsumer.accept(this.array[i]);
            }
        }

        public /* synthetic */ int getChildCount() {
            return Node.CC.$default$getChildCount(this);
        }

        public /* synthetic */ double[] newArray(int i) {
            return Node.OfDouble.CC.$default$newArray((Node.OfDouble) this, i);
        }

        public Spliterator.OfDouble spliterator() {
            return DesugarArrays.spliterator(this.array, 0, this.curSize);
        }

        public String toString() {
            return String.format("DoubleArrayNode[%d][%s]", new Object[]{Integer.valueOf(this.array.length - this.curSize), Arrays.toString(this.array)});
        }
    }

    private static final class DoubleFixedNodeBuilder extends DoubleArrayNode implements Node.Builder.OfDouble {
        DoubleFixedNodeBuilder(long j) {
            super(j);
        }

        public void accept(double d) {
            int i = this.curSize;
            double[] dArr = this.array;
            if (i < dArr.length) {
                this.curSize = 1 + i;
                dArr[i] = d;
                return;
            }
            throw new IllegalStateException(String.format("Accept exceeded fixed size of %d", new Object[]{Integer.valueOf(this.array.length)}));
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
            if (j == ((long) this.array.length)) {
                this.curSize = 0;
                return;
            }
            throw new IllegalStateException(String.format("Begin size %d is not equal to fixed size %d", new Object[]{Long.valueOf(j), Integer.valueOf(this.array.length)}));
        }

        public Node.OfDouble build() {
            if (this.curSize >= this.array.length) {
                return this;
            }
            throw new IllegalStateException(String.format("Current size %d is less than fixed size %d", new Object[]{Integer.valueOf(this.curSize), Integer.valueOf(this.array.length)}));
        }

        public /* synthetic */ boolean cancellationRequested() {
            return Sink.CC.$default$cancellationRequested(this);
        }

        public void end() {
            if (this.curSize < this.array.length) {
                throw new IllegalStateException(String.format("End size %d is less than fixed size %d", new Object[]{Integer.valueOf(this.curSize), Integer.valueOf(this.array.length)}));
            }
        }

        public String toString() {
            return String.format("DoubleFixedNodeBuilder[%d][%s]", new Object[]{Integer.valueOf(this.array.length - this.curSize), Arrays.toString(this.array)});
        }
    }

    private static final class DoubleSpinedNodeBuilder extends SpinedBuffer.OfDouble implements Node.OfDouble, Node.Builder.OfDouble {
        private boolean building = false;

        DoubleSpinedNodeBuilder() {
        }

        public void accept(double d) {
            super.accept(d);
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

        public /* synthetic */ Object[] asArray(IntFunction intFunction) {
            return Node.OfPrimitive.CC.$default$asArray(this, intFunction);
        }

        public double[] asPrimitiveArray() {
            return (double[]) super.asPrimitiveArray();
        }

        public void begin(long j) {
            this.building = true;
            clear();
            ensureCapacity(j);
        }

        public Node.OfDouble build() {
            return this;
        }

        public /* synthetic */ boolean cancellationRequested() {
            return Sink.CC.$default$cancellationRequested(this);
        }

        public void copyInto(double[] dArr, int i) {
            super.copyInto(dArr, i);
        }

        public /* synthetic */ void copyInto(Double[] dArr, int i) {
            Node.OfDouble.CC.$default$copyInto((Node.OfDouble) this, dArr, i);
        }

        public /* bridge */ /* synthetic */ void copyInto(Object[] objArr, int i) {
            copyInto((Double[]) objArr, i);
        }

        public void end() {
            this.building = false;
        }

        public void forEach(DoubleConsumer doubleConsumer) {
            super.forEach(doubleConsumer);
        }

        public /* synthetic */ int getChildCount() {
            return Node.CC.$default$getChildCount(this);
        }

        public Spliterator.OfDouble spliterator() {
            return super.spliterator();
        }
    }

    private static abstract class EmptyNode implements Node {

        private static final class OfDouble extends EmptyNode implements Node.OfDouble {
            OfDouble() {
            }

            public double[] asPrimitiveArray() {
                return Nodes.EMPTY_DOUBLE_ARRAY;
            }

            public /* synthetic */ void copyInto(Double[] dArr, int i) {
                Node.OfDouble.CC.$default$copyInto((Node.OfDouble) this, dArr, i);
            }

            public /* bridge */ /* synthetic */ void copyInto(Object[] objArr, int i) {
                copyInto((Double[]) objArr, i);
            }

            public /* synthetic */ void forEach(Consumer consumer) {
                Node.OfDouble.CC.$default$forEach(this, consumer);
            }

            public /* synthetic */ double[] newArray(int i) {
                return Node.OfDouble.CC.$default$newArray((Node.OfDouble) this, i);
            }

            public Spliterator.OfDouble spliterator() {
                return Spliterators.emptyDoubleSpliterator();
            }
        }

        private static final class OfInt extends EmptyNode implements Node.OfInt {
            OfInt() {
            }

            public int[] asPrimitiveArray() {
                return Nodes.EMPTY_INT_ARRAY;
            }

            public /* synthetic */ void copyInto(Integer[] numArr, int i) {
                Node.OfInt.CC.$default$copyInto((Node.OfInt) this, numArr, i);
            }

            public /* bridge */ /* synthetic */ void copyInto(Object[] objArr, int i) {
                copyInto((Integer[]) objArr, i);
            }

            public /* synthetic */ void forEach(Consumer consumer) {
                Node.OfInt.CC.$default$forEach(this, consumer);
            }

            public /* synthetic */ int[] newArray(int i) {
                return Node.OfInt.CC.$default$newArray((Node.OfInt) this, i);
            }

            public Spliterator.OfInt spliterator() {
                return Spliterators.emptyIntSpliterator();
            }
        }

        private static final class OfLong extends EmptyNode implements Node.OfLong {
            OfLong() {
            }

            public long[] asPrimitiveArray() {
                return Nodes.EMPTY_LONG_ARRAY;
            }

            public /* synthetic */ void copyInto(Long[] lArr, int i) {
                Node.OfLong.CC.$default$copyInto((Node.OfLong) this, lArr, i);
            }

            public /* bridge */ /* synthetic */ void copyInto(Object[] objArr, int i) {
                copyInto((Long[]) objArr, i);
            }

            public /* synthetic */ void forEach(Consumer consumer) {
                Node.OfLong.CC.$default$forEach(this, consumer);
            }

            public /* synthetic */ long[] newArray(int i) {
                return Node.OfLong.CC.$default$newArray((Node.OfLong) this, i);
            }

            public Spliterator.OfLong spliterator() {
                return Spliterators.emptyLongSpliterator();
            }
        }

        private static class OfRef extends EmptyNode {
            private OfRef() {
            }

            public /* bridge */ /* synthetic */ void copyInto(Object[] objArr, int i) {
                super.copyInto(objArr, i);
            }

            public /* bridge */ /* synthetic */ void forEach(Consumer consumer) {
                super.forEach(consumer);
            }

            public Spliterator spliterator() {
                return Spliterators.emptySpliterator();
            }
        }

        EmptyNode() {
        }

        public Object[] asArray(IntFunction intFunction) {
            return (Object[]) intFunction.apply(0);
        }

        public void copyInto(Object obj, int i) {
        }

        public long count() {
            return 0;
        }

        public void forEach(Object obj) {
        }

        public /* synthetic */ Node getChild(int i) {
            return Node.CC.$default$getChild(this, i);
        }

        public /* synthetic */ int getChildCount() {
            return Node.CC.$default$getChildCount(this);
        }

        public /* synthetic */ Node truncate(long j, long j2, IntFunction intFunction) {
            return Node.CC.$default$truncate(this, j, j2, intFunction);
        }
    }

    private static final class FixedNodeBuilder extends ArrayNode implements Node.Builder {
        FixedNodeBuilder(long j, IntFunction intFunction) {
            super(j, intFunction);
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
            int i = this.curSize;
            Object[] objArr = this.array;
            if (i < objArr.length) {
                this.curSize = 1 + i;
                objArr[i] = obj;
                return;
            }
            throw new IllegalStateException(String.format("Accept exceeded fixed size of %d", new Object[]{Integer.valueOf(this.array.length)}));
        }

        public /* synthetic */ Consumer andThen(Consumer consumer) {
            return Consumer$CC.$default$andThen(this, consumer);
        }

        public void begin(long j) {
            if (j == ((long) this.array.length)) {
                this.curSize = 0;
                return;
            }
            throw new IllegalStateException(String.format("Begin size %d is not equal to fixed size %d", new Object[]{Long.valueOf(j), Integer.valueOf(this.array.length)}));
        }

        public Node build() {
            if (this.curSize >= this.array.length) {
                return this;
            }
            throw new IllegalStateException(String.format("Current size %d is less than fixed size %d", new Object[]{Integer.valueOf(this.curSize), Integer.valueOf(this.array.length)}));
        }

        public /* synthetic */ boolean cancellationRequested() {
            return Sink.CC.$default$cancellationRequested(this);
        }

        public void end() {
            if (this.curSize < this.array.length) {
                throw new IllegalStateException(String.format("End size %d is less than fixed size %d", new Object[]{Integer.valueOf(this.curSize), Integer.valueOf(this.array.length)}));
            }
        }

        public String toString() {
            return String.format("FixedNodeBuilder[%d][%s]", new Object[]{Integer.valueOf(this.array.length - this.curSize), Arrays.toString(this.array)});
        }
    }

    private static class IntArrayNode implements Node.OfInt {
        final int[] array;
        int curSize;

        IntArrayNode(long j) {
            if (j < 2147483639) {
                this.array = new int[((int) j)];
                this.curSize = 0;
                return;
            }
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }

        IntArrayNode(int[] iArr) {
            this.array = iArr;
            this.curSize = iArr.length;
        }

        public /* synthetic */ Object[] asArray(IntFunction intFunction) {
            return Node.OfPrimitive.CC.$default$asArray(this, intFunction);
        }

        public int[] asPrimitiveArray() {
            int[] iArr = this.array;
            int length = iArr.length;
            int i = this.curSize;
            return length == i ? iArr : Arrays.copyOf(iArr, i);
        }

        public void copyInto(int[] iArr, int i) {
            System.arraycopy(this.array, 0, iArr, i, this.curSize);
        }

        public /* synthetic */ void copyInto(Integer[] numArr, int i) {
            Node.OfInt.CC.$default$copyInto((Node.OfInt) this, numArr, i);
        }

        public /* bridge */ /* synthetic */ void copyInto(Object[] objArr, int i) {
            copyInto((Integer[]) objArr, i);
        }

        public long count() {
            return (long) this.curSize;
        }

        public /* synthetic */ void forEach(Consumer consumer) {
            Node.OfInt.CC.$default$forEach(this, consumer);
        }

        public void forEach(IntConsumer intConsumer) {
            for (int i = 0; i < this.curSize; i++) {
                intConsumer.accept(this.array[i]);
            }
        }

        public /* synthetic */ int getChildCount() {
            return Node.CC.$default$getChildCount(this);
        }

        public /* synthetic */ int[] newArray(int i) {
            return Node.OfInt.CC.$default$newArray((Node.OfInt) this, i);
        }

        public Spliterator.OfInt spliterator() {
            return DesugarArrays.spliterator(this.array, 0, this.curSize);
        }

        public String toString() {
            return String.format("IntArrayNode[%d][%s]", new Object[]{Integer.valueOf(this.array.length - this.curSize), Arrays.toString(this.array)});
        }
    }

    private static final class IntFixedNodeBuilder extends IntArrayNode implements Node.Builder.OfInt {
        IntFixedNodeBuilder(long j) {
            super(j);
        }

        public /* synthetic */ void accept(double d) {
            Sink.CC.$default$accept((Sink) this, d);
        }

        public void accept(int i) {
            int i2 = this.curSize;
            int[] iArr = this.array;
            if (i2 < iArr.length) {
                this.curSize = 1 + i2;
                iArr[i2] = i;
                return;
            }
            throw new IllegalStateException(String.format("Accept exceeded fixed size of %d", new Object[]{Integer.valueOf(this.array.length)}));
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
            if (j == ((long) this.array.length)) {
                this.curSize = 0;
                return;
            }
            throw new IllegalStateException(String.format("Begin size %d is not equal to fixed size %d", new Object[]{Long.valueOf(j), Integer.valueOf(this.array.length)}));
        }

        public Node.OfInt build() {
            if (this.curSize >= this.array.length) {
                return this;
            }
            throw new IllegalStateException(String.format("Current size %d is less than fixed size %d", new Object[]{Integer.valueOf(this.curSize), Integer.valueOf(this.array.length)}));
        }

        public /* synthetic */ boolean cancellationRequested() {
            return Sink.CC.$default$cancellationRequested(this);
        }

        public void end() {
            if (this.curSize < this.array.length) {
                throw new IllegalStateException(String.format("End size %d is less than fixed size %d", new Object[]{Integer.valueOf(this.curSize), Integer.valueOf(this.array.length)}));
            }
        }

        public String toString() {
            return String.format("IntFixedNodeBuilder[%d][%s]", new Object[]{Integer.valueOf(this.array.length - this.curSize), Arrays.toString(this.array)});
        }
    }

    private static final class IntSpinedNodeBuilder extends SpinedBuffer.OfInt implements Node.OfInt, Node.Builder.OfInt {
        private boolean building = false;

        IntSpinedNodeBuilder() {
        }

        public /* synthetic */ void accept(double d) {
            Sink.CC.$default$accept((Sink) this, d);
        }

        public void accept(int i) {
            super.accept(i);
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

        public /* synthetic */ Object[] asArray(IntFunction intFunction) {
            return Node.OfPrimitive.CC.$default$asArray(this, intFunction);
        }

        public int[] asPrimitiveArray() {
            return (int[]) super.asPrimitiveArray();
        }

        public void begin(long j) {
            this.building = true;
            clear();
            ensureCapacity(j);
        }

        public Node.OfInt build() {
            return this;
        }

        public /* synthetic */ boolean cancellationRequested() {
            return Sink.CC.$default$cancellationRequested(this);
        }

        public void copyInto(int[] iArr, int i) {
            super.copyInto(iArr, i);
        }

        public /* synthetic */ void copyInto(Integer[] numArr, int i) {
            Node.OfInt.CC.$default$copyInto((Node.OfInt) this, numArr, i);
        }

        public /* bridge */ /* synthetic */ void copyInto(Object[] objArr, int i) {
            copyInto((Integer[]) objArr, i);
        }

        public void end() {
            this.building = false;
        }

        public void forEach(IntConsumer intConsumer) {
            super.forEach(intConsumer);
        }

        public /* synthetic */ int getChildCount() {
            return Node.CC.$default$getChildCount(this);
        }

        public Spliterator.OfInt spliterator() {
            return super.spliterator();
        }
    }

    private static abstract class InternalNodeSpliterator implements Spliterator {
        int curChildIndex;
        Node curNode;
        Spliterator lastNodeSpliterator;
        Spliterator tryAdvanceSpliterator;
        Deque tryAdvanceStack;

        private static final class OfDouble extends OfPrimitive implements Spliterator.OfDouble {
            OfDouble(Node.OfDouble ofDouble) {
                super(ofDouble);
            }

            public /* synthetic */ void forEachRemaining(Consumer consumer) {
                Spliterator.OfDouble.CC.$default$forEachRemaining(this, consumer);
            }

            public /* bridge */ /* synthetic */ void forEachRemaining(DoubleConsumer doubleConsumer) {
                super.forEachRemaining(doubleConsumer);
            }

            public /* synthetic */ boolean tryAdvance(Consumer consumer) {
                return Spliterator.OfDouble.CC.$default$tryAdvance(this, consumer);
            }

            public /* bridge */ /* synthetic */ boolean tryAdvance(DoubleConsumer doubleConsumer) {
                return super.tryAdvance(doubleConsumer);
            }

            public /* bridge */ /* synthetic */ Spliterator.OfDouble trySplit() {
                return (Spliterator.OfDouble) super.trySplit();
            }
        }

        private static final class OfInt extends OfPrimitive implements Spliterator.OfInt {
            OfInt(Node.OfInt ofInt) {
                super(ofInt);
            }

            public /* synthetic */ void forEachRemaining(Consumer consumer) {
                Spliterator.OfInt.CC.$default$forEachRemaining(this, consumer);
            }

            public /* bridge */ /* synthetic */ void forEachRemaining(IntConsumer intConsumer) {
                super.forEachRemaining(intConsumer);
            }

            public /* synthetic */ boolean tryAdvance(Consumer consumer) {
                return Spliterator.OfInt.CC.$default$tryAdvance(this, consumer);
            }

            public /* bridge */ /* synthetic */ boolean tryAdvance(IntConsumer intConsumer) {
                return super.tryAdvance(intConsumer);
            }

            public /* bridge */ /* synthetic */ Spliterator.OfInt trySplit() {
                return (Spliterator.OfInt) super.trySplit();
            }
        }

        private static final class OfLong extends OfPrimitive implements Spliterator.OfLong {
            OfLong(Node.OfLong ofLong) {
                super(ofLong);
            }

            public /* synthetic */ void forEachRemaining(Consumer consumer) {
                Spliterator.OfLong.CC.$default$forEachRemaining(this, consumer);
            }

            public /* bridge */ /* synthetic */ void forEachRemaining(LongConsumer longConsumer) {
                super.forEachRemaining(longConsumer);
            }

            public /* synthetic */ boolean tryAdvance(Consumer consumer) {
                return Spliterator.OfLong.CC.$default$tryAdvance(this, consumer);
            }

            public /* bridge */ /* synthetic */ boolean tryAdvance(LongConsumer longConsumer) {
                return super.tryAdvance(longConsumer);
            }

            public /* bridge */ /* synthetic */ Spliterator.OfLong trySplit() {
                return (Spliterator.OfLong) super.trySplit();
            }
        }

        private static abstract class OfPrimitive extends InternalNodeSpliterator implements Spliterator.OfPrimitive {
            OfPrimitive(Node.OfPrimitive ofPrimitive) {
                super(ofPrimitive);
            }

            public void forEachRemaining(Object obj) {
                if (this.curNode != null) {
                    if (this.tryAdvanceSpliterator == null) {
                        Spliterator spliterator = this.lastNodeSpliterator;
                        if (spliterator == null) {
                            Deque initStack = initStack();
                            while (true) {
                                Node.OfPrimitive ofPrimitive = (Node.OfPrimitive) findNextLeafNode(initStack);
                                if (ofPrimitive != null) {
                                    ofPrimitive.forEach(obj);
                                } else {
                                    this.curNode = null;
                                    return;
                                }
                            }
                        } else {
                            ((Spliterator.OfPrimitive) spliterator).forEachRemaining(obj);
                        }
                    } else {
                        do {
                        } while (tryAdvance(obj));
                    }
                }
            }

            public boolean tryAdvance(Object obj) {
                Node.OfPrimitive ofPrimitive;
                if (!initTryAdvance()) {
                    return false;
                }
                boolean tryAdvance = ((Spliterator.OfPrimitive) this.tryAdvanceSpliterator).tryAdvance(obj);
                if (!tryAdvance) {
                    if (this.lastNodeSpliterator != null || (ofPrimitive = (Node.OfPrimitive) findNextLeafNode(this.tryAdvanceStack)) == null) {
                        this.curNode = null;
                    } else {
                        Spliterator.OfPrimitive spliterator = ofPrimitive.spliterator();
                        this.tryAdvanceSpliterator = spliterator;
                        return spliterator.tryAdvance(obj);
                    }
                }
                return tryAdvance;
            }

            public /* bridge */ /* synthetic */ Spliterator.OfPrimitive trySplit() {
                return (Spliterator.OfPrimitive) super.trySplit();
            }
        }

        private static final class OfRef extends InternalNodeSpliterator {
            OfRef(Node node) {
                super(node);
            }

            public void forEachRemaining(Consumer consumer) {
                if (this.curNode != null) {
                    if (this.tryAdvanceSpliterator == null) {
                        Spliterator spliterator = this.lastNodeSpliterator;
                        if (spliterator == null) {
                            Deque initStack = initStack();
                            while (true) {
                                Node findNextLeafNode = findNextLeafNode(initStack);
                                if (findNextLeafNode != null) {
                                    findNextLeafNode.forEach(consumer);
                                } else {
                                    this.curNode = null;
                                    return;
                                }
                            }
                        } else {
                            spliterator.forEachRemaining(consumer);
                        }
                    } else {
                        do {
                        } while (tryAdvance(consumer));
                    }
                }
            }

            public boolean tryAdvance(Consumer consumer) {
                Node findNextLeafNode;
                if (!initTryAdvance()) {
                    return false;
                }
                boolean tryAdvance = this.tryAdvanceSpliterator.tryAdvance(consumer);
                if (!tryAdvance) {
                    if (this.lastNodeSpliterator != null || (findNextLeafNode = findNextLeafNode(this.tryAdvanceStack)) == null) {
                        this.curNode = null;
                    } else {
                        Spliterator spliterator = findNextLeafNode.spliterator();
                        this.tryAdvanceSpliterator = spliterator;
                        return spliterator.tryAdvance(consumer);
                    }
                }
                return tryAdvance;
            }
        }

        InternalNodeSpliterator(Node node) {
            this.curNode = node;
        }

        public final int characteristics() {
            return 64;
        }

        public final long estimateSize() {
            long j = 0;
            if (this.curNode == null) {
                return 0;
            }
            Spliterator spliterator = this.lastNodeSpliterator;
            if (spliterator != null) {
                return spliterator.estimateSize();
            }
            for (int i = this.curChildIndex; i < this.curNode.getChildCount(); i++) {
                j += this.curNode.getChild(i).count();
            }
            return j;
        }

        /* access modifiers changed from: protected */
        public final Node findNextLeafNode(Deque deque) {
            while (true) {
                Node node = (Node) deque.pollFirst();
                if (node == null) {
                    return null;
                }
                if (node.getChildCount() != 0) {
                    for (int childCount = node.getChildCount() - 1; childCount >= 0; childCount--) {
                        deque.addFirst(node.getChild(childCount));
                    }
                } else if (node.count() > 0) {
                    return node;
                }
            }
        }

        public /* synthetic */ Comparator getComparator() {
            return Spliterator.CC.$default$getComparator(this);
        }

        public /* synthetic */ long getExactSizeIfKnown() {
            return Spliterator.CC.$default$getExactSizeIfKnown(this);
        }

        public /* synthetic */ boolean hasCharacteristics(int i) {
            return Spliterator.CC.$default$hasCharacteristics(this, i);
        }

        /* access modifiers changed from: protected */
        public final Deque initStack() {
            ArrayDeque arrayDeque = new ArrayDeque(8);
            int childCount = this.curNode.getChildCount();
            while (true) {
                childCount--;
                if (childCount < this.curChildIndex) {
                    return arrayDeque;
                }
                arrayDeque.addFirst(this.curNode.getChild(childCount));
            }
        }

        /* access modifiers changed from: protected */
        public final boolean initTryAdvance() {
            if (this.curNode == null) {
                return false;
            }
            if (this.tryAdvanceSpliterator != null) {
                return true;
            }
            Spliterator spliterator = this.lastNodeSpliterator;
            if (spliterator == null) {
                Deque initStack = initStack();
                this.tryAdvanceStack = initStack;
                Node findNextLeafNode = findNextLeafNode(initStack);
                if (findNextLeafNode != null) {
                    spliterator = findNextLeafNode.spliterator();
                } else {
                    this.curNode = null;
                    return false;
                }
            }
            this.tryAdvanceSpliterator = spliterator;
            return true;
        }

        public final Spliterator trySplit() {
            Node node = this.curNode;
            if (node == null || this.tryAdvanceSpliterator != null) {
                return null;
            }
            Spliterator spliterator = this.lastNodeSpliterator;
            if (spliterator != null) {
                return spliterator.trySplit();
            }
            if (this.curChildIndex < node.getChildCount() - 1) {
                Node node2 = this.curNode;
                int i = this.curChildIndex;
                this.curChildIndex = i + 1;
                return node2.getChild(i).spliterator();
            }
            Node child = this.curNode.getChild(this.curChildIndex);
            this.curNode = child;
            if (child.getChildCount() == 0) {
                Spliterator spliterator2 = this.curNode.spliterator();
                this.lastNodeSpliterator = spliterator2;
                return spliterator2.trySplit();
            }
            Node node3 = this.curNode;
            this.curChildIndex = 1;
            return node3.getChild(0).spliterator();
        }
    }

    private static class LongArrayNode implements Node.OfLong {
        final long[] array;
        int curSize;

        LongArrayNode(long j) {
            if (j < 2147483639) {
                this.array = new long[((int) j)];
                this.curSize = 0;
                return;
            }
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }

        LongArrayNode(long[] jArr) {
            this.array = jArr;
            this.curSize = jArr.length;
        }

        public /* synthetic */ Object[] asArray(IntFunction intFunction) {
            return Node.OfPrimitive.CC.$default$asArray(this, intFunction);
        }

        public long[] asPrimitiveArray() {
            long[] jArr = this.array;
            int length = jArr.length;
            int i = this.curSize;
            return length == i ? jArr : Arrays.copyOf(jArr, i);
        }

        public void copyInto(long[] jArr, int i) {
            System.arraycopy(this.array, 0, jArr, i, this.curSize);
        }

        public /* synthetic */ void copyInto(Long[] lArr, int i) {
            Node.OfLong.CC.$default$copyInto((Node.OfLong) this, lArr, i);
        }

        public /* bridge */ /* synthetic */ void copyInto(Object[] objArr, int i) {
            copyInto((Long[]) objArr, i);
        }

        public long count() {
            return (long) this.curSize;
        }

        public /* synthetic */ void forEach(Consumer consumer) {
            Node.OfLong.CC.$default$forEach(this, consumer);
        }

        public void forEach(LongConsumer longConsumer) {
            for (int i = 0; i < this.curSize; i++) {
                longConsumer.accept(this.array[i]);
            }
        }

        public /* synthetic */ int getChildCount() {
            return Node.CC.$default$getChildCount(this);
        }

        public /* synthetic */ long[] newArray(int i) {
            return Node.OfLong.CC.$default$newArray((Node.OfLong) this, i);
        }

        public Spliterator.OfLong spliterator() {
            return DesugarArrays.spliterator(this.array, 0, this.curSize);
        }

        public String toString() {
            return String.format("LongArrayNode[%d][%s]", new Object[]{Integer.valueOf(this.array.length - this.curSize), Arrays.toString(this.array)});
        }
    }

    private static final class LongFixedNodeBuilder extends LongArrayNode implements Node.Builder.OfLong {
        LongFixedNodeBuilder(long j) {
            super(j);
        }

        public /* synthetic */ void accept(double d) {
            Sink.CC.$default$accept((Sink) this, d);
        }

        public /* synthetic */ void accept(int i) {
            Sink.CC.$default$accept((Sink) this, i);
        }

        public void accept(long j) {
            int i = this.curSize;
            long[] jArr = this.array;
            if (i < jArr.length) {
                this.curSize = 1 + i;
                jArr[i] = j;
                return;
            }
            throw new IllegalStateException(String.format("Accept exceeded fixed size of %d", new Object[]{Integer.valueOf(this.array.length)}));
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
            if (j == ((long) this.array.length)) {
                this.curSize = 0;
                return;
            }
            throw new IllegalStateException(String.format("Begin size %d is not equal to fixed size %d", new Object[]{Long.valueOf(j), Integer.valueOf(this.array.length)}));
        }

        public Node.OfLong build() {
            if (this.curSize >= this.array.length) {
                return this;
            }
            throw new IllegalStateException(String.format("Current size %d is less than fixed size %d", new Object[]{Integer.valueOf(this.curSize), Integer.valueOf(this.array.length)}));
        }

        public /* synthetic */ boolean cancellationRequested() {
            return Sink.CC.$default$cancellationRequested(this);
        }

        public void end() {
            if (this.curSize < this.array.length) {
                throw new IllegalStateException(String.format("End size %d is less than fixed size %d", new Object[]{Integer.valueOf(this.curSize), Integer.valueOf(this.array.length)}));
            }
        }

        public String toString() {
            return String.format("LongFixedNodeBuilder[%d][%s]", new Object[]{Integer.valueOf(this.array.length - this.curSize), Arrays.toString(this.array)});
        }
    }

    private static final class LongSpinedNodeBuilder extends SpinedBuffer.OfLong implements Node.OfLong, Node.Builder.OfLong {
        private boolean building = false;

        LongSpinedNodeBuilder() {
        }

        public /* synthetic */ void accept(double d) {
            Sink.CC.$default$accept((Sink) this, d);
        }

        public /* synthetic */ void accept(int i) {
            Sink.CC.$default$accept((Sink) this, i);
        }

        public void accept(long j) {
            super.accept(j);
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

        public /* synthetic */ Object[] asArray(IntFunction intFunction) {
            return Node.OfPrimitive.CC.$default$asArray(this, intFunction);
        }

        public long[] asPrimitiveArray() {
            return (long[]) super.asPrimitiveArray();
        }

        public void begin(long j) {
            this.building = true;
            clear();
            ensureCapacity(j);
        }

        public Node.OfLong build() {
            return this;
        }

        public /* synthetic */ boolean cancellationRequested() {
            return Sink.CC.$default$cancellationRequested(this);
        }

        public void copyInto(long[] jArr, int i) {
            super.copyInto(jArr, i);
        }

        public /* synthetic */ void copyInto(Long[] lArr, int i) {
            Node.OfLong.CC.$default$copyInto((Node.OfLong) this, lArr, i);
        }

        public /* bridge */ /* synthetic */ void copyInto(Object[] objArr, int i) {
            copyInto((Long[]) objArr, i);
        }

        public void end() {
            this.building = false;
        }

        public void forEach(LongConsumer longConsumer) {
            super.forEach(longConsumer);
        }

        public /* synthetic */ int getChildCount() {
            return Node.CC.$default$getChildCount(this);
        }

        public Spliterator.OfLong spliterator() {
            return super.spliterator();
        }
    }

    private static abstract class SizedCollectorTask extends CountedCompleter implements Sink {
        protected int fence;
        protected final PipelineHelper helper;
        protected int index;
        protected long length;
        protected long offset;
        protected final Spliterator spliterator;
        protected final long targetSize;

        static final class OfDouble extends SizedCollectorTask implements Sink.OfDouble {
            private final double[] array;

            OfDouble(Spliterator spliterator, PipelineHelper pipelineHelper, double[] dArr) {
                super(spliterator, pipelineHelper, dArr.length);
                this.array = dArr;
            }

            OfDouble(OfDouble ofDouble, Spliterator spliterator, long j, long j2) {
                super(ofDouble, spliterator, j, j2, ofDouble.array.length);
                this.array = ofDouble.array;
            }

            public void accept(double d) {
                int i = this.index;
                if (i < this.fence) {
                    double[] dArr = this.array;
                    this.index = i + 1;
                    dArr[i] = d;
                    return;
                }
                throw new IndexOutOfBoundsException(Integer.toString(this.index));
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

            /* access modifiers changed from: package-private */
            public OfDouble makeChild(Spliterator spliterator, long j, long j2) {
                return new OfDouble(this, spliterator, j, j2);
            }
        }

        static final class OfInt extends SizedCollectorTask implements Sink.OfInt {
            private final int[] array;

            OfInt(Spliterator spliterator, PipelineHelper pipelineHelper, int[] iArr) {
                super(spliterator, pipelineHelper, iArr.length);
                this.array = iArr;
            }

            OfInt(OfInt ofInt, Spliterator spliterator, long j, long j2) {
                super(ofInt, spliterator, j, j2, ofInt.array.length);
                this.array = ofInt.array;
            }

            public void accept(int i) {
                int i2 = this.index;
                if (i2 < this.fence) {
                    int[] iArr = this.array;
                    this.index = i2 + 1;
                    iArr[i2] = i;
                    return;
                }
                throw new IndexOutOfBoundsException(Integer.toString(this.index));
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

            /* access modifiers changed from: package-private */
            public OfInt makeChild(Spliterator spliterator, long j, long j2) {
                return new OfInt(this, spliterator, j, j2);
            }
        }

        static final class OfLong extends SizedCollectorTask implements Sink.OfLong {
            private final long[] array;

            OfLong(Spliterator spliterator, PipelineHelper pipelineHelper, long[] jArr) {
                super(spliterator, pipelineHelper, jArr.length);
                this.array = jArr;
            }

            OfLong(OfLong ofLong, Spliterator spliterator, long j, long j2) {
                super(ofLong, spliterator, j, j2, ofLong.array.length);
                this.array = ofLong.array;
            }

            public void accept(long j) {
                int i = this.index;
                if (i < this.fence) {
                    long[] jArr = this.array;
                    this.index = i + 1;
                    jArr[i] = j;
                    return;
                }
                throw new IndexOutOfBoundsException(Integer.toString(this.index));
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

            /* access modifiers changed from: package-private */
            public OfLong makeChild(Spliterator spliterator, long j, long j2) {
                return new OfLong(this, spliterator, j, j2);
            }
        }

        static final class OfRef extends SizedCollectorTask implements Sink {
            private final Object[] array;

            OfRef(Spliterator spliterator, PipelineHelper pipelineHelper, Object[] objArr) {
                super(spliterator, pipelineHelper, objArr.length);
                this.array = objArr;
            }

            OfRef(OfRef ofRef, Spliterator spliterator, long j, long j2) {
                super(ofRef, spliterator, j, j2, ofRef.array.length);
                this.array = ofRef.array;
            }

            public void accept(Object obj) {
                int i = this.index;
                if (i < this.fence) {
                    Object[] objArr = this.array;
                    this.index = i + 1;
                    objArr[i] = obj;
                    return;
                }
                throw new IndexOutOfBoundsException(Integer.toString(this.index));
            }

            /* access modifiers changed from: package-private */
            public OfRef makeChild(Spliterator spliterator, long j, long j2) {
                return new OfRef(this, spliterator, j, j2);
            }
        }

        SizedCollectorTask(Spliterator spliterator2, PipelineHelper pipelineHelper, int i) {
            this.spliterator = spliterator2;
            this.helper = pipelineHelper;
            this.targetSize = AbstractTask.suggestTargetSize(spliterator2.estimateSize());
            this.offset = 0;
            this.length = (long) i;
        }

        SizedCollectorTask(SizedCollectorTask sizedCollectorTask, Spliterator spliterator2, long j, long j2, int i) {
            super(sizedCollectorTask);
            this.spliterator = spliterator2;
            this.helper = sizedCollectorTask.helper;
            this.targetSize = sizedCollectorTask.targetSize;
            this.offset = j;
            this.length = j2;
            if (j < 0 || j2 < 0 || (j + j2) - 1 >= ((long) i)) {
                throw new IllegalArgumentException(String.format("offset and length interval [%d, %d + %d) is not within array size interval [0, %d)", new Object[]{Long.valueOf(j), Long.valueOf(j), Long.valueOf(j2), Integer.valueOf(i)}));
            }
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
            long j2 = this.length;
            if (j <= j2) {
                int i = (int) this.offset;
                this.index = i;
                this.fence = i + ((int) j2);
                return;
            }
            throw new IllegalStateException("size passed to Sink.begin exceeds array length");
        }

        public /* synthetic */ boolean cancellationRequested() {
            return Sink.CC.$default$cancellationRequested(this);
        }

        public void compute() {
            Spliterator trySplit;
            Spliterator spliterator2 = this.spliterator;
            SizedCollectorTask sizedCollectorTask = this;
            while (spliterator2.estimateSize() > sizedCollectorTask.targetSize && (trySplit = spliterator2.trySplit()) != null) {
                sizedCollectorTask.setPendingCount(1);
                long estimateSize = trySplit.estimateSize();
                sizedCollectorTask.makeChild(trySplit, sizedCollectorTask.offset, estimateSize).fork();
                sizedCollectorTask = sizedCollectorTask.makeChild(spliterator2, sizedCollectorTask.offset + estimateSize, sizedCollectorTask.length - estimateSize);
            }
            sizedCollectorTask.helper.wrapAndCopyInto(sizedCollectorTask, spliterator2);
            sizedCollectorTask.propagateCompletion();
        }

        public /* synthetic */ void end() {
            Sink.CC.$default$end(this);
        }

        /* access modifiers changed from: package-private */
        public abstract SizedCollectorTask makeChild(Spliterator spliterator2, long j, long j2);
    }

    private static final class SpinedNodeBuilder extends SpinedBuffer implements Node, Node.Builder {
        private boolean building = false;

        SpinedNodeBuilder() {
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
            super.accept(obj);
        }

        public Object[] asArray(IntFunction intFunction) {
            return super.asArray(intFunction);
        }

        public void begin(long j) {
            this.building = true;
            clear();
            ensureCapacity(j);
        }

        public Node build() {
            return this;
        }

        public /* synthetic */ boolean cancellationRequested() {
            return Sink.CC.$default$cancellationRequested(this);
        }

        public void copyInto(Object[] objArr, int i) {
            super.copyInto(objArr, i);
        }

        public void end() {
            this.building = false;
        }

        public void forEach(Consumer consumer) {
            super.forEach(consumer);
        }

        public /* synthetic */ Node getChild(int i) {
            return Node.CC.$default$getChild(this, i);
        }

        public /* synthetic */ int getChildCount() {
            return Node.CC.$default$getChildCount(this);
        }

        public Spliterator spliterator() {
            return super.spliterator();
        }

        public /* synthetic */ Node truncate(long j, long j2, IntFunction intFunction) {
            return Node.CC.$default$truncate(this, j, j2, intFunction);
        }
    }

    private static abstract class ToArrayTask extends CountedCompleter {
        protected final Node node;
        protected final int offset;

        private static final class OfDouble extends OfPrimitive {
            private OfDouble(Node.OfDouble ofDouble, double[] dArr, int i) {
                super(ofDouble, dArr, i);
            }
        }

        private static final class OfInt extends OfPrimitive {
            private OfInt(Node.OfInt ofInt, int[] iArr, int i) {
                super(ofInt, iArr, i);
            }
        }

        private static final class OfLong extends OfPrimitive {
            private OfLong(Node.OfLong ofLong, long[] jArr, int i) {
                super(ofLong, jArr, i);
            }
        }

        private static class OfPrimitive extends ToArrayTask {
            private final Object array;

            private OfPrimitive(Node.OfPrimitive ofPrimitive, Object obj, int i) {
                super(ofPrimitive, i);
                this.array = obj;
            }

            private OfPrimitive(OfPrimitive ofPrimitive, Node.OfPrimitive ofPrimitive2, int i) {
                super(ofPrimitive, ofPrimitive2, i);
                this.array = ofPrimitive.array;
            }

            /* access modifiers changed from: package-private */
            public void copyNodeToArray() {
                ((Node.OfPrimitive) this.node).copyInto(this.array, this.offset);
            }

            /* access modifiers changed from: package-private */
            public OfPrimitive makeChild(int i, int i2) {
                return new OfPrimitive(this, ((Node.OfPrimitive) this.node).getChild(i), i2);
            }
        }

        private static final class OfRef extends ToArrayTask {
            private final Object[] array;

            private OfRef(Node node, Object[] objArr, int i) {
                super(node, i);
                this.array = objArr;
            }

            private OfRef(OfRef ofRef, Node node, int i) {
                super(ofRef, node, i);
                this.array = ofRef.array;
            }

            /* access modifiers changed from: package-private */
            public void copyNodeToArray() {
                this.node.copyInto(this.array, this.offset);
            }

            /* access modifiers changed from: package-private */
            public OfRef makeChild(int i, int i2) {
                return new OfRef(this, this.node.getChild(i), i2);
            }
        }

        ToArrayTask(Node node2, int i) {
            this.node = node2;
            this.offset = i;
        }

        ToArrayTask(ToArrayTask toArrayTask, Node node2, int i) {
            super(toArrayTask);
            this.node = node2;
            this.offset = i;
        }

        public void compute() {
            ToArrayTask toArrayTask = this;
            while (toArrayTask.node.getChildCount() != 0) {
                toArrayTask.setPendingCount(toArrayTask.node.getChildCount() - 1);
                int i = 0;
                int i2 = 0;
                while (i < toArrayTask.node.getChildCount() - 1) {
                    ToArrayTask makeChild = toArrayTask.makeChild(i, toArrayTask.offset + i2);
                    i2 = (int) (((long) i2) + makeChild.node.count());
                    makeChild.fork();
                    i++;
                }
                toArrayTask = toArrayTask.makeChild(i, toArrayTask.offset + i2);
            }
            toArrayTask.copyNodeToArray();
            toArrayTask.propagateCompletion();
        }

        /* access modifiers changed from: package-private */
        public abstract void copyNodeToArray();

        /* access modifiers changed from: package-private */
        public abstract ToArrayTask makeChild(int i, int i2);
    }

    static Node.Builder builder() {
        return new SpinedNodeBuilder();
    }

    /* access modifiers changed from: package-private */
    public static Node.Builder builder(long j, IntFunction intFunction) {
        return (j < 0 || j >= 2147483639) ? builder() : new FixedNodeBuilder(j, intFunction);
    }

    static IntFunction castingArray() {
        return new Nodes$$ExternalSyntheticLambda0();
    }

    public static Node collect(PipelineHelper pipelineHelper, Spliterator spliterator, boolean z, IntFunction intFunction) {
        long exactOutputSizeIfKnown = pipelineHelper.exactOutputSizeIfKnown(spliterator);
        if (exactOutputSizeIfKnown < 0 || !spliterator.hasCharacteristics(16384)) {
            Node node = (Node) new CollectorTask.OfRef(pipelineHelper, intFunction, spliterator).invoke();
            return z ? flatten(node, intFunction) : node;
        } else if (exactOutputSizeIfKnown < 2147483639) {
            Object[] objArr = (Object[]) intFunction.apply((int) exactOutputSizeIfKnown);
            new SizedCollectorTask.OfRef(spliterator, pipelineHelper, objArr).invoke();
            return node(objArr);
        } else {
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
    }

    public static Node.OfDouble collectDouble(PipelineHelper pipelineHelper, Spliterator spliterator, boolean z) {
        long exactOutputSizeIfKnown = pipelineHelper.exactOutputSizeIfKnown(spliterator);
        if (exactOutputSizeIfKnown < 0 || !spliterator.hasCharacteristics(16384)) {
            Node.OfDouble ofDouble = (Node.OfDouble) new CollectorTask.OfDouble(pipelineHelper, spliterator).invoke();
            return z ? flattenDouble(ofDouble) : ofDouble;
        } else if (exactOutputSizeIfKnown < 2147483639) {
            double[] dArr = new double[((int) exactOutputSizeIfKnown)];
            new SizedCollectorTask.OfDouble(spliterator, pipelineHelper, dArr).invoke();
            return node(dArr);
        } else {
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
    }

    public static Node.OfInt collectInt(PipelineHelper pipelineHelper, Spliterator spliterator, boolean z) {
        long exactOutputSizeIfKnown = pipelineHelper.exactOutputSizeIfKnown(spliterator);
        if (exactOutputSizeIfKnown < 0 || !spliterator.hasCharacteristics(16384)) {
            Node.OfInt ofInt = (Node.OfInt) new CollectorTask.OfInt(pipelineHelper, spliterator).invoke();
            return z ? flattenInt(ofInt) : ofInt;
        } else if (exactOutputSizeIfKnown < 2147483639) {
            int[] iArr = new int[((int) exactOutputSizeIfKnown)];
            new SizedCollectorTask.OfInt(spliterator, pipelineHelper, iArr).invoke();
            return node(iArr);
        } else {
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
    }

    public static Node.OfLong collectLong(PipelineHelper pipelineHelper, Spliterator spliterator, boolean z) {
        long exactOutputSizeIfKnown = pipelineHelper.exactOutputSizeIfKnown(spliterator);
        if (exactOutputSizeIfKnown < 0 || !spliterator.hasCharacteristics(16384)) {
            Node.OfLong ofLong = (Node.OfLong) new CollectorTask.OfLong(pipelineHelper, spliterator).invoke();
            return z ? flattenLong(ofLong) : ofLong;
        } else if (exactOutputSizeIfKnown < 2147483639) {
            long[] jArr = new long[((int) exactOutputSizeIfKnown)];
            new SizedCollectorTask.OfLong(spliterator, pipelineHelper, jArr).invoke();
            return node(jArr);
        } else {
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
    }

    static Node conc(StreamShape streamShape, Node node, Node node2) {
        int i = AnonymousClass1.$SwitchMap$java$util$stream$StreamShape[streamShape.ordinal()];
        if (i == 1) {
            return new ConcNode(node, node2);
        }
        if (i == 2) {
            return new ConcNode.OfInt((Node.OfInt) node, (Node.OfInt) node2);
        }
        if (i == 3) {
            return new ConcNode.OfLong((Node.OfLong) node, (Node.OfLong) node2);
        }
        if (i == 4) {
            return new ConcNode.OfDouble((Node.OfDouble) node, (Node.OfDouble) node2);
        }
        throw new IllegalStateException("Unknown shape " + streamShape);
    }

    static Node.Builder.OfDouble doubleBuilder() {
        return new DoubleSpinedNodeBuilder();
    }

    static Node.Builder.OfDouble doubleBuilder(long j) {
        return (j < 0 || j >= 2147483639) ? doubleBuilder() : new DoubleFixedNodeBuilder(j);
    }

    static Node emptyNode(StreamShape streamShape) {
        int i = AnonymousClass1.$SwitchMap$java$util$stream$StreamShape[streamShape.ordinal()];
        if (i == 1) {
            return EMPTY_NODE;
        }
        if (i == 2) {
            return EMPTY_INT_NODE;
        }
        if (i == 3) {
            return EMPTY_LONG_NODE;
        }
        if (i == 4) {
            return EMPTY_DOUBLE_NODE;
        }
        throw new IllegalStateException("Unknown shape " + streamShape);
    }

    public static Node flatten(Node node, IntFunction intFunction) {
        if (node.getChildCount() <= 0) {
            return node;
        }
        long count = node.count();
        if (count < 2147483639) {
            Object[] objArr = (Object[]) intFunction.apply((int) count);
            new ToArrayTask.OfRef(node, objArr, 0).invoke();
            return node(objArr);
        }
        throw new IllegalArgumentException("Stream size exceeds max array size");
    }

    public static Node.OfDouble flattenDouble(Node.OfDouble ofDouble) {
        if (ofDouble.getChildCount() <= 0) {
            return ofDouble;
        }
        long count = ofDouble.count();
        if (count < 2147483639) {
            double[] dArr = new double[((int) count)];
            new ToArrayTask.OfDouble(ofDouble, dArr, 0).invoke();
            return node(dArr);
        }
        throw new IllegalArgumentException("Stream size exceeds max array size");
    }

    public static Node.OfInt flattenInt(Node.OfInt ofInt) {
        if (ofInt.getChildCount() <= 0) {
            return ofInt;
        }
        long count = ofInt.count();
        if (count < 2147483639) {
            int[] iArr = new int[((int) count)];
            new ToArrayTask.OfInt(ofInt, iArr, 0).invoke();
            return node(iArr);
        }
        throw new IllegalArgumentException("Stream size exceeds max array size");
    }

    public static Node.OfLong flattenLong(Node.OfLong ofLong) {
        if (ofLong.getChildCount() <= 0) {
            return ofLong;
        }
        long count = ofLong.count();
        if (count < 2147483639) {
            long[] jArr = new long[((int) count)];
            new ToArrayTask.OfLong(ofLong, jArr, 0).invoke();
            return node(jArr);
        }
        throw new IllegalArgumentException("Stream size exceeds max array size");
    }

    static Node.Builder.OfInt intBuilder() {
        return new IntSpinedNodeBuilder();
    }

    static Node.Builder.OfInt intBuilder(long j) {
        return (j < 0 || j >= 2147483639) ? intBuilder() : new IntFixedNodeBuilder(j);
    }

    static /* synthetic */ Object[] lambda$castingArray$0(int i) {
        return new Object[i];
    }

    static Node.Builder.OfLong longBuilder() {
        return new LongSpinedNodeBuilder();
    }

    static Node.Builder.OfLong longBuilder(long j) {
        return (j < 0 || j >= 2147483639) ? longBuilder() : new LongFixedNodeBuilder(j);
    }

    static Node.OfDouble node(double[] dArr) {
        return new DoubleArrayNode(dArr);
    }

    static Node.OfInt node(int[] iArr) {
        return new IntArrayNode(iArr);
    }

    static Node.OfLong node(long[] jArr) {
        return new LongArrayNode(jArr);
    }

    static Node node(java.util.Collection collection) {
        return new CollectionNode(collection);
    }

    static Node node(Object[] objArr) {
        return new ArrayNode(objArr);
    }
}
