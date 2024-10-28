package j$.util.stream;

import j$.util.Spliterator;
import j$.util.stream.Sink;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.LongConsumer;

interface Node {

    /* renamed from: j$.util.stream.Node$-CC  reason: invalid class name */
    public abstract /* synthetic */ class CC {
        public static Node $default$getChild(Node node, int i) {
            throw new IndexOutOfBoundsException();
        }

        public static int $default$getChildCount(Node node) {
            return 0;
        }

        public static StreamShape $default$getShape(Node node) {
            return StreamShape.REFERENCE;
        }

        public static Node $default$truncate(Node node, long j, long j2, IntFunction intFunction) {
            if (j == 0 && j2 == node.count()) {
                return node;
            }
            Spliterator spliterator = node.spliterator();
            long j3 = j2 - j;
            Builder builder = Nodes.builder(j3, intFunction);
            builder.begin(j3);
            for (int i = 0; ((long) i) < j && spliterator.tryAdvance(new Node$$ExternalSyntheticLambda0()); i++) {
            }
            if (j2 == node.count()) {
                spliterator.forEachRemaining(builder);
            } else {
                for (int i2 = 0; ((long) i2) < j3 && spliterator.tryAdvance(builder); i2++) {
                }
            }
            builder.end();
            return builder.build();
        }

        public static /* synthetic */ void lambda$truncate$0(Object obj) {
        }
    }

    public interface Builder extends Sink {

        public interface OfDouble extends Builder, Sink.OfDouble {
            OfDouble build();
        }

        public interface OfInt extends Builder, Sink.OfInt {
            OfInt build();
        }

        public interface OfLong extends Builder, Sink.OfLong {
            OfLong build();
        }

        Node build();
    }

    public interface OfDouble extends OfPrimitive {

        /* renamed from: j$.util.stream.Node$OfDouble$-CC  reason: invalid class name */
        public abstract /* synthetic */ class CC {
            public static void $default$copyInto(OfDouble ofDouble, Double[] dArr, int i) {
                if (Tripwire.ENABLED) {
                    Tripwire.trip(ofDouble.getClass(), "{0} calling Node.OfDouble.copyInto(Double[], int)");
                }
                double[] dArr2 = (double[]) ofDouble.asPrimitiveArray();
                for (int i2 = 0; i2 < dArr2.length; i2++) {
                    dArr[i + i2] = Double.valueOf(dArr2[i2]);
                }
            }

            public static void $default$forEach(OfDouble ofDouble, Consumer consumer) {
                if (consumer instanceof DoubleConsumer) {
                    ofDouble.forEach((DoubleConsumer) consumer);
                    return;
                }
                if (Tripwire.ENABLED) {
                    Tripwire.trip(ofDouble.getClass(), "{0} calling Node.OfLong.forEachRemaining(Consumer)");
                }
                ((Spliterator.OfDouble) ofDouble.spliterator()).forEachRemaining(consumer);
            }

            public static double[] $default$newArray(OfDouble ofDouble, int i) {
                return new double[i];
            }

            public static OfDouble $default$truncate(OfDouble ofDouble, long j, long j2, IntFunction intFunction) {
                if (j == 0 && j2 == ofDouble.count()) {
                    return ofDouble;
                }
                long j3 = j2 - j;
                Spliterator.OfDouble ofDouble2 = (Spliterator.OfDouble) ofDouble.spliterator();
                Builder.OfDouble doubleBuilder = Nodes.doubleBuilder(j3);
                doubleBuilder.begin(j3);
                for (int i = 0; ((long) i) < j && ofDouble2.tryAdvance((DoubleConsumer) new Node$OfDouble$$ExternalSyntheticLambda0()); i++) {
                }
                if (j2 == ofDouble.count()) {
                    ofDouble2.forEachRemaining((DoubleConsumer) doubleBuilder);
                } else {
                    for (int i2 = 0; ((long) i2) < j3 && ofDouble2.tryAdvance((DoubleConsumer) doubleBuilder); i2++) {
                    }
                }
                doubleBuilder.end();
                return doubleBuilder.build();
            }

            public static /* synthetic */ void lambda$truncate$0(double d) {
            }
        }

        void copyInto(Double[] dArr, int i);

        double[] newArray(int i);

        OfDouble truncate(long j, long j2, IntFunction intFunction);
    }

    public interface OfInt extends OfPrimitive {

        /* renamed from: j$.util.stream.Node$OfInt$-CC  reason: invalid class name */
        public abstract /* synthetic */ class CC {
            public static void $default$copyInto(OfInt ofInt, Integer[] numArr, int i) {
                if (Tripwire.ENABLED) {
                    Tripwire.trip(ofInt.getClass(), "{0} calling Node.OfInt.copyInto(Integer[], int)");
                }
                int[] iArr = (int[]) ofInt.asPrimitiveArray();
                for (int i2 = 0; i2 < iArr.length; i2++) {
                    numArr[i + i2] = Integer.valueOf(iArr[i2]);
                }
            }

            public static void $default$forEach(OfInt ofInt, Consumer consumer) {
                if (consumer instanceof IntConsumer) {
                    ofInt.forEach((IntConsumer) consumer);
                    return;
                }
                if (Tripwire.ENABLED) {
                    Tripwire.trip(ofInt.getClass(), "{0} calling Node.OfInt.forEachRemaining(Consumer)");
                }
                ((Spliterator.OfInt) ofInt.spliterator()).forEachRemaining(consumer);
            }

            public static int[] $default$newArray(OfInt ofInt, int i) {
                return new int[i];
            }

            public static OfInt $default$truncate(OfInt ofInt, long j, long j2, IntFunction intFunction) {
                if (j == 0 && j2 == ofInt.count()) {
                    return ofInt;
                }
                long j3 = j2 - j;
                Spliterator.OfInt ofInt2 = (Spliterator.OfInt) ofInt.spliterator();
                Builder.OfInt intBuilder = Nodes.intBuilder(j3);
                intBuilder.begin(j3);
                for (int i = 0; ((long) i) < j && ofInt2.tryAdvance((IntConsumer) new Node$OfInt$$ExternalSyntheticLambda0()); i++) {
                }
                if (j2 == ofInt.count()) {
                    ofInt2.forEachRemaining((IntConsumer) intBuilder);
                } else {
                    for (int i2 = 0; ((long) i2) < j3 && ofInt2.tryAdvance((IntConsumer) intBuilder); i2++) {
                    }
                }
                intBuilder.end();
                return intBuilder.build();
            }

            public static /* synthetic */ void lambda$truncate$0(int i) {
            }
        }

        void copyInto(Integer[] numArr, int i);

        int[] newArray(int i);

        OfInt truncate(long j, long j2, IntFunction intFunction);
    }

    public interface OfLong extends OfPrimitive {

        /* renamed from: j$.util.stream.Node$OfLong$-CC  reason: invalid class name */
        public abstract /* synthetic */ class CC {
            public static void $default$copyInto(OfLong ofLong, Long[] lArr, int i) {
                if (Tripwire.ENABLED) {
                    Tripwire.trip(ofLong.getClass(), "{0} calling Node.OfInt.copyInto(Long[], int)");
                }
                long[] jArr = (long[]) ofLong.asPrimitiveArray();
                for (int i2 = 0; i2 < jArr.length; i2++) {
                    lArr[i + i2] = Long.valueOf(jArr[i2]);
                }
            }

            public static void $default$forEach(OfLong ofLong, Consumer consumer) {
                if (consumer instanceof LongConsumer) {
                    ofLong.forEach((LongConsumer) consumer);
                    return;
                }
                if (Tripwire.ENABLED) {
                    Tripwire.trip(ofLong.getClass(), "{0} calling Node.OfLong.forEachRemaining(Consumer)");
                }
                ((Spliterator.OfLong) ofLong.spliterator()).forEachRemaining(consumer);
            }

            public static long[] $default$newArray(OfLong ofLong, int i) {
                return new long[i];
            }

            public static OfLong $default$truncate(OfLong ofLong, long j, long j2, IntFunction intFunction) {
                if (j == 0 && j2 == ofLong.count()) {
                    return ofLong;
                }
                long j3 = j2 - j;
                Spliterator.OfLong ofLong2 = (Spliterator.OfLong) ofLong.spliterator();
                Builder.OfLong longBuilder = Nodes.longBuilder(j3);
                longBuilder.begin(j3);
                for (int i = 0; ((long) i) < j && ofLong2.tryAdvance((LongConsumer) new Node$OfLong$$ExternalSyntheticLambda0()); i++) {
                }
                if (j2 == ofLong.count()) {
                    ofLong2.forEachRemaining((LongConsumer) longBuilder);
                } else {
                    for (int i2 = 0; ((long) i2) < j3 && ofLong2.tryAdvance((LongConsumer) longBuilder); i2++) {
                    }
                }
                longBuilder.end();
                return longBuilder.build();
            }

            public static /* synthetic */ void lambda$truncate$0(long j) {
            }
        }

        void copyInto(Long[] lArr, int i);

        long[] newArray(int i);

        OfLong truncate(long j, long j2, IntFunction intFunction);
    }

    public interface OfPrimitive extends Node {

        /* renamed from: j$.util.stream.Node$OfPrimitive$-CC  reason: invalid class name */
        public abstract /* synthetic */ class CC {
            public static Object[] $default$asArray(OfPrimitive ofPrimitive, IntFunction intFunction) {
                if (Tripwire.ENABLED) {
                    Tripwire.trip(ofPrimitive.getClass(), "{0} calling Node.OfPrimitive.asArray");
                }
                if (ofPrimitive.count() < 2147483639) {
                    Object[] objArr = (Object[]) intFunction.apply((int) ofPrimitive.count());
                    ofPrimitive.copyInto(objArr, 0);
                    return objArr;
                }
                throw new IllegalArgumentException("Stream size exceeds max array size");
            }

            public static OfPrimitive $default$getChild(OfPrimitive ofPrimitive, int i) {
                throw new IndexOutOfBoundsException();
            }
        }

        Object asPrimitiveArray();

        void copyInto(Object obj, int i);

        void forEach(Object obj);

        OfPrimitive getChild(int i);

        Object newArray(int i);

        Spliterator.OfPrimitive spliterator();
    }

    Object[] asArray(IntFunction intFunction);

    void copyInto(Object[] objArr, int i);

    long count();

    void forEach(Consumer consumer);

    Node getChild(int i);

    int getChildCount();

    Spliterator spliterator();

    Node truncate(long j, long j2, IntFunction intFunction);
}
