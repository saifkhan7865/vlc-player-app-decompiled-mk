package j$.util;

import java.util.Iterator;
import java.util.PrimitiveIterator;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;

public interface PrimitiveIterator extends Iterator {

    public interface OfDouble extends PrimitiveIterator {

        /* renamed from: j$.util.PrimitiveIterator$OfDouble$-CC  reason: invalid class name */
        public abstract /* synthetic */ class CC {
            public static void $default$forEachRemaining(OfDouble ofDouble, Consumer consumer) {
                if (consumer instanceof DoubleConsumer) {
                    ofDouble.forEachRemaining((DoubleConsumer) consumer);
                    return;
                }
                Objects.requireNonNull(consumer);
                if (Tripwire.ENABLED) {
                    Tripwire.trip(ofDouble.getClass(), "{0} calling PrimitiveIterator.OfDouble.forEachRemainingDouble(action::accept)");
                }
                Objects.requireNonNull(consumer);
                ofDouble.forEachRemaining((DoubleConsumer) new PrimitiveIterator$OfDouble$$ExternalSyntheticLambda0(consumer));
            }

            public static void $default$forEachRemaining(OfDouble ofDouble, DoubleConsumer doubleConsumer) {
                Objects.requireNonNull(doubleConsumer);
                while (ofDouble.hasNext()) {
                    doubleConsumer.accept(ofDouble.nextDouble());
                }
            }

            public static Double $default$next(OfDouble ofDouble) {
                if (Tripwire.ENABLED) {
                    Tripwire.trip(ofDouble.getClass(), "{0} calling PrimitiveIterator.OfDouble.nextLong()");
                }
                return Double.valueOf(ofDouble.nextDouble());
            }
        }

        public final /* synthetic */ class VivifiedWrapper implements OfDouble, Iterator {
            public final /* synthetic */ PrimitiveIterator.OfDouble wrappedValue;

            private /* synthetic */ VivifiedWrapper(PrimitiveIterator.OfDouble ofDouble) {
                this.wrappedValue = ofDouble;
            }

            public static /* synthetic */ OfDouble convert(PrimitiveIterator.OfDouble ofDouble) {
                if (ofDouble == null) {
                    return null;
                }
                return ofDouble instanceof Wrapper ? OfDouble.this : new VivifiedWrapper(ofDouble);
            }

            public /* synthetic */ boolean equals(Object obj) {
                PrimitiveIterator.OfDouble ofDouble = this.wrappedValue;
                if (obj instanceof VivifiedWrapper) {
                    obj = ((VivifiedWrapper) obj).wrappedValue;
                }
                return ofDouble.equals(obj);
            }

            public /* synthetic */ void forEachRemaining(Object obj) {
                this.wrappedValue.forEachRemaining(obj);
            }

            public /* synthetic */ void forEachRemaining(Consumer consumer) {
                this.wrappedValue.forEachRemaining(consumer);
            }

            public /* synthetic */ void forEachRemaining(DoubleConsumer doubleConsumer) {
                this.wrappedValue.forEachRemaining(doubleConsumer);
            }

            public /* synthetic */ boolean hasNext() {
                return this.wrappedValue.hasNext();
            }

            public /* synthetic */ int hashCode() {
                return this.wrappedValue.hashCode();
            }

            public /* synthetic */ double nextDouble() {
                return this.wrappedValue.nextDouble();
            }

            public /* synthetic */ void remove() {
                this.wrappedValue.remove();
            }
        }

        public final /* synthetic */ class Wrapper implements PrimitiveIterator.OfDouble {
            private /* synthetic */ Wrapper() {
            }

            public static /* synthetic */ PrimitiveIterator.OfDouble convert(OfDouble ofDouble) {
                if (ofDouble == null) {
                    return null;
                }
                return ofDouble instanceof VivifiedWrapper ? ((VivifiedWrapper) ofDouble).wrappedValue : new Wrapper();
            }

            public /* synthetic */ boolean equals(Object obj) {
                OfDouble ofDouble = OfDouble.this;
                if (obj instanceof Wrapper) {
                    obj = OfDouble.this;
                }
                return ofDouble.equals(obj);
            }

            public /* synthetic */ void forEachRemaining(Object obj) {
                OfDouble.this.forEachRemaining(obj);
            }

            public /* synthetic */ void forEachRemaining(Consumer consumer) {
                OfDouble.this.forEachRemaining(consumer);
            }

            public /* synthetic */ void forEachRemaining(DoubleConsumer doubleConsumer) {
                OfDouble.this.forEachRemaining(doubleConsumer);
            }

            public /* synthetic */ boolean hasNext() {
                return OfDouble.this.hasNext();
            }

            public /* synthetic */ int hashCode() {
                return OfDouble.this.hashCode();
            }

            public /* synthetic */ double nextDouble() {
                return OfDouble.this.nextDouble();
            }

            public /* synthetic */ void remove() {
                OfDouble.this.remove();
            }
        }

        void forEachRemaining(Consumer consumer);

        void forEachRemaining(DoubleConsumer doubleConsumer);

        Double next();

        double nextDouble();
    }

    public interface OfInt extends PrimitiveIterator {

        /* renamed from: j$.util.PrimitiveIterator$OfInt$-CC  reason: invalid class name */
        public abstract /* synthetic */ class CC {
            public static void $default$forEachRemaining(OfInt ofInt, Consumer consumer) {
                if (consumer instanceof IntConsumer) {
                    ofInt.forEachRemaining((IntConsumer) consumer);
                    return;
                }
                Objects.requireNonNull(consumer);
                if (Tripwire.ENABLED) {
                    Tripwire.trip(ofInt.getClass(), "{0} calling PrimitiveIterator.OfInt.forEachRemainingInt(action::accept)");
                }
                Objects.requireNonNull(consumer);
                ofInt.forEachRemaining((IntConsumer) new PrimitiveIterator$OfInt$$ExternalSyntheticLambda0(consumer));
            }

            public static void $default$forEachRemaining(OfInt ofInt, IntConsumer intConsumer) {
                Objects.requireNonNull(intConsumer);
                while (ofInt.hasNext()) {
                    intConsumer.accept(ofInt.nextInt());
                }
            }

            public static Integer $default$next(OfInt ofInt) {
                if (Tripwire.ENABLED) {
                    Tripwire.trip(ofInt.getClass(), "{0} calling PrimitiveIterator.OfInt.nextInt()");
                }
                return Integer.valueOf(ofInt.nextInt());
            }
        }

        public final /* synthetic */ class VivifiedWrapper implements OfInt, Iterator {
            public final /* synthetic */ PrimitiveIterator.OfInt wrappedValue;

            private /* synthetic */ VivifiedWrapper(PrimitiveIterator.OfInt ofInt) {
                this.wrappedValue = ofInt;
            }

            public static /* synthetic */ OfInt convert(PrimitiveIterator.OfInt ofInt) {
                if (ofInt == null) {
                    return null;
                }
                return ofInt instanceof Wrapper ? OfInt.this : new VivifiedWrapper(ofInt);
            }

            public /* synthetic */ boolean equals(Object obj) {
                PrimitiveIterator.OfInt ofInt = this.wrappedValue;
                if (obj instanceof VivifiedWrapper) {
                    obj = ((VivifiedWrapper) obj).wrappedValue;
                }
                return ofInt.equals(obj);
            }

            public /* synthetic */ void forEachRemaining(Object obj) {
                this.wrappedValue.forEachRemaining(obj);
            }

            public /* synthetic */ void forEachRemaining(Consumer consumer) {
                this.wrappedValue.forEachRemaining(consumer);
            }

            public /* synthetic */ void forEachRemaining(IntConsumer intConsumer) {
                this.wrappedValue.forEachRemaining(intConsumer);
            }

            public /* synthetic */ boolean hasNext() {
                return this.wrappedValue.hasNext();
            }

            public /* synthetic */ int hashCode() {
                return this.wrappedValue.hashCode();
            }

            public /* synthetic */ int nextInt() {
                return this.wrappedValue.nextInt();
            }

            public /* synthetic */ void remove() {
                this.wrappedValue.remove();
            }
        }

        public final /* synthetic */ class Wrapper implements PrimitiveIterator.OfInt {
            private /* synthetic */ Wrapper() {
            }

            public static /* synthetic */ PrimitiveIterator.OfInt convert(OfInt ofInt) {
                if (ofInt == null) {
                    return null;
                }
                return ofInt instanceof VivifiedWrapper ? ((VivifiedWrapper) ofInt).wrappedValue : new Wrapper();
            }

            public /* synthetic */ boolean equals(Object obj) {
                OfInt ofInt = OfInt.this;
                if (obj instanceof Wrapper) {
                    obj = OfInt.this;
                }
                return ofInt.equals(obj);
            }

            public /* synthetic */ void forEachRemaining(Object obj) {
                OfInt.this.forEachRemaining(obj);
            }

            public /* synthetic */ void forEachRemaining(Consumer consumer) {
                OfInt.this.forEachRemaining(consumer);
            }

            public /* synthetic */ void forEachRemaining(IntConsumer intConsumer) {
                OfInt.this.forEachRemaining(intConsumer);
            }

            public /* synthetic */ boolean hasNext() {
                return OfInt.this.hasNext();
            }

            public /* synthetic */ int hashCode() {
                return OfInt.this.hashCode();
            }

            public /* synthetic */ int nextInt() {
                return OfInt.this.nextInt();
            }

            public /* synthetic */ void remove() {
                OfInt.this.remove();
            }
        }

        void forEachRemaining(Consumer consumer);

        void forEachRemaining(IntConsumer intConsumer);

        Integer next();

        int nextInt();
    }

    public interface OfLong extends PrimitiveIterator {

        /* renamed from: j$.util.PrimitiveIterator$OfLong$-CC  reason: invalid class name */
        public abstract /* synthetic */ class CC {
            public static void $default$forEachRemaining(OfLong ofLong, Consumer consumer) {
                if (consumer instanceof LongConsumer) {
                    ofLong.forEachRemaining((LongConsumer) consumer);
                    return;
                }
                Objects.requireNonNull(consumer);
                if (Tripwire.ENABLED) {
                    Tripwire.trip(ofLong.getClass(), "{0} calling PrimitiveIterator.OfLong.forEachRemainingLong(action::accept)");
                }
                Objects.requireNonNull(consumer);
                ofLong.forEachRemaining((LongConsumer) new PrimitiveIterator$OfLong$$ExternalSyntheticLambda0(consumer));
            }

            public static void $default$forEachRemaining(OfLong ofLong, LongConsumer longConsumer) {
                Objects.requireNonNull(longConsumer);
                while (ofLong.hasNext()) {
                    longConsumer.accept(ofLong.nextLong());
                }
            }

            public static Long $default$next(OfLong ofLong) {
                if (Tripwire.ENABLED) {
                    Tripwire.trip(ofLong.getClass(), "{0} calling PrimitiveIterator.OfLong.nextLong()");
                }
                return Long.valueOf(ofLong.nextLong());
            }
        }

        public final /* synthetic */ class VivifiedWrapper implements OfLong, Iterator {
            public final /* synthetic */ PrimitiveIterator.OfLong wrappedValue;

            private /* synthetic */ VivifiedWrapper(PrimitiveIterator.OfLong ofLong) {
                this.wrappedValue = ofLong;
            }

            public static /* synthetic */ OfLong convert(PrimitiveIterator.OfLong ofLong) {
                if (ofLong == null) {
                    return null;
                }
                return ofLong instanceof Wrapper ? OfLong.this : new VivifiedWrapper(ofLong);
            }

            public /* synthetic */ boolean equals(Object obj) {
                PrimitiveIterator.OfLong ofLong = this.wrappedValue;
                if (obj instanceof VivifiedWrapper) {
                    obj = ((VivifiedWrapper) obj).wrappedValue;
                }
                return ofLong.equals(obj);
            }

            public /* synthetic */ void forEachRemaining(Object obj) {
                this.wrappedValue.forEachRemaining(obj);
            }

            public /* synthetic */ void forEachRemaining(Consumer consumer) {
                this.wrappedValue.forEachRemaining(consumer);
            }

            public /* synthetic */ void forEachRemaining(LongConsumer longConsumer) {
                this.wrappedValue.forEachRemaining(longConsumer);
            }

            public /* synthetic */ boolean hasNext() {
                return this.wrappedValue.hasNext();
            }

            public /* synthetic */ int hashCode() {
                return this.wrappedValue.hashCode();
            }

            public /* synthetic */ long nextLong() {
                return this.wrappedValue.nextLong();
            }

            public /* synthetic */ void remove() {
                this.wrappedValue.remove();
            }
        }

        public final /* synthetic */ class Wrapper implements PrimitiveIterator.OfLong {
            private /* synthetic */ Wrapper() {
            }

            public static /* synthetic */ PrimitiveIterator.OfLong convert(OfLong ofLong) {
                if (ofLong == null) {
                    return null;
                }
                return ofLong instanceof VivifiedWrapper ? ((VivifiedWrapper) ofLong).wrappedValue : new Wrapper();
            }

            public /* synthetic */ boolean equals(Object obj) {
                OfLong ofLong = OfLong.this;
                if (obj instanceof Wrapper) {
                    obj = OfLong.this;
                }
                return ofLong.equals(obj);
            }

            public /* synthetic */ void forEachRemaining(Object obj) {
                OfLong.this.forEachRemaining(obj);
            }

            public /* synthetic */ void forEachRemaining(Consumer consumer) {
                OfLong.this.forEachRemaining(consumer);
            }

            public /* synthetic */ void forEachRemaining(LongConsumer longConsumer) {
                OfLong.this.forEachRemaining(longConsumer);
            }

            public /* synthetic */ boolean hasNext() {
                return OfLong.this.hasNext();
            }

            public /* synthetic */ int hashCode() {
                return OfLong.this.hashCode();
            }

            public /* synthetic */ long nextLong() {
                return OfLong.this.nextLong();
            }

            public /* synthetic */ void remove() {
                OfLong.this.remove();
            }
        }

        void forEachRemaining(Consumer consumer);

        void forEachRemaining(LongConsumer longConsumer);

        Long next();

        long nextLong();
    }

    void forEachRemaining(Object obj);
}
