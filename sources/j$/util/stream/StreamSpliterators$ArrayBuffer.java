package j$.util.stream;

import j$.util.function.Consumer$CC;
import j$.util.function.DoubleConsumer$CC;
import j$.util.function.IntConsumer$CC;
import j$.util.function.LongConsumer$CC;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;

abstract class StreamSpliterators$ArrayBuffer {
    int index;

    static final class OfDouble extends OfPrimitive implements DoubleConsumer {
        final double[] array;

        OfDouble(int i) {
            this.array = new double[i];
        }

        public void accept(double d) {
            double[] dArr = this.array;
            int i = this.index;
            this.index = i + 1;
            dArr[i] = d;
        }

        public /* synthetic */ DoubleConsumer andThen(DoubleConsumer doubleConsumer) {
            return DoubleConsumer$CC.$default$andThen(this, doubleConsumer);
        }

        /* access modifiers changed from: package-private */
        public void forEach(DoubleConsumer doubleConsumer, long j) {
            for (int i = 0; ((long) i) < j; i++) {
                doubleConsumer.accept(this.array[i]);
            }
        }
    }

    static final class OfInt extends OfPrimitive implements IntConsumer {
        final int[] array;

        OfInt(int i) {
            this.array = new int[i];
        }

        public void accept(int i) {
            int[] iArr = this.array;
            int i2 = this.index;
            this.index = i2 + 1;
            iArr[i2] = i;
        }

        public /* synthetic */ IntConsumer andThen(IntConsumer intConsumer) {
            return IntConsumer$CC.$default$andThen(this, intConsumer);
        }

        public void forEach(IntConsumer intConsumer, long j) {
            for (int i = 0; ((long) i) < j; i++) {
                intConsumer.accept(this.array[i]);
            }
        }
    }

    static final class OfLong extends OfPrimitive implements LongConsumer {
        final long[] array;

        OfLong(int i) {
            this.array = new long[i];
        }

        public void accept(long j) {
            long[] jArr = this.array;
            int i = this.index;
            this.index = i + 1;
            jArr[i] = j;
        }

        public /* synthetic */ LongConsumer andThen(LongConsumer longConsumer) {
            return LongConsumer$CC.$default$andThen(this, longConsumer);
        }

        public void forEach(LongConsumer longConsumer, long j) {
            for (int i = 0; ((long) i) < j; i++) {
                longConsumer.accept(this.array[i]);
            }
        }
    }

    static abstract class OfPrimitive extends StreamSpliterators$ArrayBuffer {
        int index;

        OfPrimitive() {
        }

        /* access modifiers changed from: package-private */
        public abstract void forEach(Object obj, long j);

        /* access modifiers changed from: package-private */
        public void reset() {
            this.index = 0;
        }
    }

    static final class OfRef extends StreamSpliterators$ArrayBuffer implements Consumer {
        final Object[] array;

        OfRef(int i) {
            this.array = new Object[i];
        }

        public void accept(Object obj) {
            Object[] objArr = this.array;
            int i = this.index;
            this.index = i + 1;
            objArr[i] = obj;
        }

        public /* synthetic */ Consumer andThen(Consumer consumer) {
            return Consumer$CC.$default$andThen(this, consumer);
        }

        public void forEach(Consumer consumer, long j) {
            for (int i = 0; ((long) i) < j; i++) {
                consumer.accept(this.array[i]);
            }
        }
    }

    StreamSpliterators$ArrayBuffer() {
    }

    /* access modifiers changed from: package-private */
    public void reset() {
        this.index = 0;
    }
}
