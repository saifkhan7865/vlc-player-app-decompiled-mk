package j$.util;

import java.util.Comparator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;

public interface Spliterator<T> {

    /* renamed from: j$.util.Spliterator$-CC  reason: invalid class name */
    public abstract /* synthetic */ class CC {
        public static void $default$forEachRemaining(Spliterator spliterator, Consumer consumer) {
            do {
            } while (spliterator.tryAdvance(consumer));
        }

        public static Comparator $default$getComparator(Spliterator spliterator) {
            throw new IllegalStateException();
        }

        public static long $default$getExactSizeIfKnown(Spliterator spliterator) {
            if ((spliterator.characteristics() & 64) == 0) {
                return -1;
            }
            return spliterator.estimateSize();
        }

        public static boolean $default$hasCharacteristics(Spliterator spliterator, int i) {
            return (spliterator.characteristics() & i) == i;
        }
    }

    public interface OfDouble extends OfPrimitive {

        /* renamed from: j$.util.Spliterator$OfDouble$-CC  reason: invalid class name */
        public abstract /* synthetic */ class CC {
            public static void $default$forEachRemaining(OfDouble ofDouble, Consumer consumer) {
                if (consumer instanceof DoubleConsumer) {
                    ofDouble.forEachRemaining((DoubleConsumer) consumer);
                    return;
                }
                if (Tripwire.ENABLED) {
                    Tripwire.trip(ofDouble.getClass(), "{0} calling Spliterator.OfDouble.forEachRemaining((DoubleConsumer) action::accept)");
                }
                Objects.requireNonNull(consumer);
                ofDouble.forEachRemaining((DoubleConsumer) new PrimitiveIterator$OfDouble$$ExternalSyntheticLambda0(consumer));
            }

            public static boolean $default$tryAdvance(OfDouble ofDouble, Consumer consumer) {
                if (consumer instanceof DoubleConsumer) {
                    return ofDouble.tryAdvance((DoubleConsumer) consumer);
                }
                if (Tripwire.ENABLED) {
                    Tripwire.trip(ofDouble.getClass(), "{0} calling Spliterator.OfDouble.tryAdvance((DoubleConsumer) action::accept)");
                }
                Objects.requireNonNull(consumer);
                return ofDouble.tryAdvance((DoubleConsumer) new PrimitiveIterator$OfDouble$$ExternalSyntheticLambda0(consumer));
            }
        }

        public final /* synthetic */ class VivifiedWrapper implements OfDouble {
            public final /* synthetic */ Spliterator.OfDouble wrappedValue;

            private /* synthetic */ VivifiedWrapper(Spliterator.OfDouble ofDouble) {
                this.wrappedValue = ofDouble;
            }

            public static /* synthetic */ OfDouble convert(Spliterator.OfDouble ofDouble) {
                if (ofDouble == null) {
                    return null;
                }
                return ofDouble instanceof Wrapper ? OfDouble.this : new VivifiedWrapper(ofDouble);
            }

            public /* synthetic */ int characteristics() {
                return this.wrappedValue.characteristics();
            }

            public /* synthetic */ boolean equals(Object obj) {
                Spliterator.OfDouble ofDouble = this.wrappedValue;
                if (obj instanceof VivifiedWrapper) {
                    obj = ((VivifiedWrapper) obj).wrappedValue;
                }
                return ofDouble.equals(obj);
            }

            public /* synthetic */ long estimateSize() {
                return this.wrappedValue.estimateSize();
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

            public /* synthetic */ Comparator getComparator() {
                return this.wrappedValue.getComparator();
            }

            public /* synthetic */ long getExactSizeIfKnown() {
                return this.wrappedValue.getExactSizeIfKnown();
            }

            public /* synthetic */ boolean hasCharacteristics(int i) {
                return this.wrappedValue.hasCharacteristics(i);
            }

            public /* synthetic */ int hashCode() {
                return this.wrappedValue.hashCode();
            }

            public /* synthetic */ boolean tryAdvance(Object obj) {
                return this.wrappedValue.tryAdvance(obj);
            }

            public /* synthetic */ boolean tryAdvance(Consumer consumer) {
                return this.wrappedValue.tryAdvance(consumer);
            }

            public /* synthetic */ boolean tryAdvance(DoubleConsumer doubleConsumer) {
                return this.wrappedValue.tryAdvance(doubleConsumer);
            }
        }

        public final /* synthetic */ class Wrapper implements Spliterator.OfDouble {
            private /* synthetic */ Wrapper() {
            }

            public static /* synthetic */ Spliterator.OfDouble convert(OfDouble ofDouble) {
                if (ofDouble == null) {
                    return null;
                }
                return ofDouble instanceof VivifiedWrapper ? ((VivifiedWrapper) ofDouble).wrappedValue : new Wrapper();
            }

            public /* synthetic */ int characteristics() {
                return OfDouble.this.characteristics();
            }

            public /* synthetic */ boolean equals(Object obj) {
                OfDouble ofDouble = OfDouble.this;
                if (obj instanceof Wrapper) {
                    obj = OfDouble.this;
                }
                return ofDouble.equals(obj);
            }

            public /* synthetic */ long estimateSize() {
                return OfDouble.this.estimateSize();
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

            public /* synthetic */ Comparator getComparator() {
                return OfDouble.this.getComparator();
            }

            public /* synthetic */ long getExactSizeIfKnown() {
                return OfDouble.this.getExactSizeIfKnown();
            }

            public /* synthetic */ boolean hasCharacteristics(int i) {
                return OfDouble.this.hasCharacteristics(i);
            }

            public /* synthetic */ int hashCode() {
                return OfDouble.this.hashCode();
            }

            public /* synthetic */ boolean tryAdvance(Object obj) {
                return OfDouble.this.tryAdvance(obj);
            }

            public /* synthetic */ boolean tryAdvance(Consumer consumer) {
                return OfDouble.this.tryAdvance(consumer);
            }

            public /* synthetic */ boolean tryAdvance(DoubleConsumer doubleConsumer) {
                return OfDouble.this.tryAdvance(doubleConsumer);
            }
        }

        void forEachRemaining(Consumer consumer);

        void forEachRemaining(DoubleConsumer doubleConsumer);

        boolean tryAdvance(Consumer consumer);

        boolean tryAdvance(DoubleConsumer doubleConsumer);

        OfDouble trySplit();
    }

    public interface OfInt extends OfPrimitive {

        /* renamed from: j$.util.Spliterator$OfInt$-CC  reason: invalid class name */
        public abstract /* synthetic */ class CC {
            public static void $default$forEachRemaining(OfInt ofInt, Consumer consumer) {
                if (consumer instanceof IntConsumer) {
                    ofInt.forEachRemaining((IntConsumer) consumer);
                    return;
                }
                if (Tripwire.ENABLED) {
                    Tripwire.trip(ofInt.getClass(), "{0} calling Spliterator.OfInt.forEachRemaining((IntConsumer) action::accept)");
                }
                Objects.requireNonNull(consumer);
                ofInt.forEachRemaining((IntConsumer) new PrimitiveIterator$OfInt$$ExternalSyntheticLambda0(consumer));
            }

            public static boolean $default$tryAdvance(OfInt ofInt, Consumer consumer) {
                if (consumer instanceof IntConsumer) {
                    return ofInt.tryAdvance((IntConsumer) consumer);
                }
                if (Tripwire.ENABLED) {
                    Tripwire.trip(ofInt.getClass(), "{0} calling Spliterator.OfInt.tryAdvance((IntConsumer) action::accept)");
                }
                Objects.requireNonNull(consumer);
                return ofInt.tryAdvance((IntConsumer) new PrimitiveIterator$OfInt$$ExternalSyntheticLambda0(consumer));
            }
        }

        public final /* synthetic */ class VivifiedWrapper implements OfInt {
            public final /* synthetic */ Spliterator.OfInt wrappedValue;

            private /* synthetic */ VivifiedWrapper(Spliterator.OfInt ofInt) {
                this.wrappedValue = ofInt;
            }

            public static /* synthetic */ OfInt convert(Spliterator.OfInt ofInt) {
                if (ofInt == null) {
                    return null;
                }
                return ofInt instanceof Wrapper ? OfInt.this : new VivifiedWrapper(ofInt);
            }

            public /* synthetic */ int characteristics() {
                return this.wrappedValue.characteristics();
            }

            public /* synthetic */ boolean equals(Object obj) {
                Spliterator.OfInt ofInt = this.wrappedValue;
                if (obj instanceof VivifiedWrapper) {
                    obj = ((VivifiedWrapper) obj).wrappedValue;
                }
                return ofInt.equals(obj);
            }

            public /* synthetic */ long estimateSize() {
                return this.wrappedValue.estimateSize();
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

            public /* synthetic */ Comparator getComparator() {
                return this.wrappedValue.getComparator();
            }

            public /* synthetic */ long getExactSizeIfKnown() {
                return this.wrappedValue.getExactSizeIfKnown();
            }

            public /* synthetic */ boolean hasCharacteristics(int i) {
                return this.wrappedValue.hasCharacteristics(i);
            }

            public /* synthetic */ int hashCode() {
                return this.wrappedValue.hashCode();
            }

            public /* synthetic */ boolean tryAdvance(Object obj) {
                return this.wrappedValue.tryAdvance(obj);
            }

            public /* synthetic */ boolean tryAdvance(Consumer consumer) {
                return this.wrappedValue.tryAdvance(consumer);
            }

            public /* synthetic */ boolean tryAdvance(IntConsumer intConsumer) {
                return this.wrappedValue.tryAdvance(intConsumer);
            }
        }

        public final /* synthetic */ class Wrapper implements Spliterator.OfInt {
            private /* synthetic */ Wrapper() {
            }

            public static /* synthetic */ Spliterator.OfInt convert(OfInt ofInt) {
                if (ofInt == null) {
                    return null;
                }
                return ofInt instanceof VivifiedWrapper ? ((VivifiedWrapper) ofInt).wrappedValue : new Wrapper();
            }

            public /* synthetic */ int characteristics() {
                return OfInt.this.characteristics();
            }

            public /* synthetic */ boolean equals(Object obj) {
                OfInt ofInt = OfInt.this;
                if (obj instanceof Wrapper) {
                    obj = OfInt.this;
                }
                return ofInt.equals(obj);
            }

            public /* synthetic */ long estimateSize() {
                return OfInt.this.estimateSize();
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

            public /* synthetic */ Comparator getComparator() {
                return OfInt.this.getComparator();
            }

            public /* synthetic */ long getExactSizeIfKnown() {
                return OfInt.this.getExactSizeIfKnown();
            }

            public /* synthetic */ boolean hasCharacteristics(int i) {
                return OfInt.this.hasCharacteristics(i);
            }

            public /* synthetic */ int hashCode() {
                return OfInt.this.hashCode();
            }

            public /* synthetic */ boolean tryAdvance(Object obj) {
                return OfInt.this.tryAdvance(obj);
            }

            public /* synthetic */ boolean tryAdvance(Consumer consumer) {
                return OfInt.this.tryAdvance(consumer);
            }

            public /* synthetic */ boolean tryAdvance(IntConsumer intConsumer) {
                return OfInt.this.tryAdvance(intConsumer);
            }
        }

        void forEachRemaining(Consumer consumer);

        void forEachRemaining(IntConsumer intConsumer);

        boolean tryAdvance(Consumer consumer);

        boolean tryAdvance(IntConsumer intConsumer);

        OfInt trySplit();
    }

    public interface OfLong extends OfPrimitive {

        /* renamed from: j$.util.Spliterator$OfLong$-CC  reason: invalid class name */
        public abstract /* synthetic */ class CC {
            public static void $default$forEachRemaining(OfLong ofLong, Consumer consumer) {
                if (consumer instanceof LongConsumer) {
                    ofLong.forEachRemaining((LongConsumer) consumer);
                    return;
                }
                if (Tripwire.ENABLED) {
                    Tripwire.trip(ofLong.getClass(), "{0} calling Spliterator.OfLong.forEachRemaining((LongConsumer) action::accept)");
                }
                Objects.requireNonNull(consumer);
                ofLong.forEachRemaining((LongConsumer) new PrimitiveIterator$OfLong$$ExternalSyntheticLambda0(consumer));
            }

            public static boolean $default$tryAdvance(OfLong ofLong, Consumer consumer) {
                if (consumer instanceof LongConsumer) {
                    return ofLong.tryAdvance((LongConsumer) consumer);
                }
                if (Tripwire.ENABLED) {
                    Tripwire.trip(ofLong.getClass(), "{0} calling Spliterator.OfLong.tryAdvance((LongConsumer) action::accept)");
                }
                Objects.requireNonNull(consumer);
                return ofLong.tryAdvance((LongConsumer) new PrimitiveIterator$OfLong$$ExternalSyntheticLambda0(consumer));
            }
        }

        public final /* synthetic */ class VivifiedWrapper implements OfLong {
            public final /* synthetic */ Spliterator.OfLong wrappedValue;

            private /* synthetic */ VivifiedWrapper(Spliterator.OfLong ofLong) {
                this.wrappedValue = ofLong;
            }

            public static /* synthetic */ OfLong convert(Spliterator.OfLong ofLong) {
                if (ofLong == null) {
                    return null;
                }
                return ofLong instanceof Wrapper ? OfLong.this : new VivifiedWrapper(ofLong);
            }

            public /* synthetic */ int characteristics() {
                return this.wrappedValue.characteristics();
            }

            public /* synthetic */ boolean equals(Object obj) {
                Spliterator.OfLong ofLong = this.wrappedValue;
                if (obj instanceof VivifiedWrapper) {
                    obj = ((VivifiedWrapper) obj).wrappedValue;
                }
                return ofLong.equals(obj);
            }

            public /* synthetic */ long estimateSize() {
                return this.wrappedValue.estimateSize();
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

            public /* synthetic */ Comparator getComparator() {
                return this.wrappedValue.getComparator();
            }

            public /* synthetic */ long getExactSizeIfKnown() {
                return this.wrappedValue.getExactSizeIfKnown();
            }

            public /* synthetic */ boolean hasCharacteristics(int i) {
                return this.wrappedValue.hasCharacteristics(i);
            }

            public /* synthetic */ int hashCode() {
                return this.wrappedValue.hashCode();
            }

            public /* synthetic */ boolean tryAdvance(Object obj) {
                return this.wrappedValue.tryAdvance(obj);
            }

            public /* synthetic */ boolean tryAdvance(Consumer consumer) {
                return this.wrappedValue.tryAdvance(consumer);
            }

            public /* synthetic */ boolean tryAdvance(LongConsumer longConsumer) {
                return this.wrappedValue.tryAdvance(longConsumer);
            }
        }

        public final /* synthetic */ class Wrapper implements Spliterator.OfLong {
            private /* synthetic */ Wrapper() {
            }

            public static /* synthetic */ Spliterator.OfLong convert(OfLong ofLong) {
                if (ofLong == null) {
                    return null;
                }
                return ofLong instanceof VivifiedWrapper ? ((VivifiedWrapper) ofLong).wrappedValue : new Wrapper();
            }

            public /* synthetic */ int characteristics() {
                return OfLong.this.characteristics();
            }

            public /* synthetic */ boolean equals(Object obj) {
                OfLong ofLong = OfLong.this;
                if (obj instanceof Wrapper) {
                    obj = OfLong.this;
                }
                return ofLong.equals(obj);
            }

            public /* synthetic */ long estimateSize() {
                return OfLong.this.estimateSize();
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

            public /* synthetic */ Comparator getComparator() {
                return OfLong.this.getComparator();
            }

            public /* synthetic */ long getExactSizeIfKnown() {
                return OfLong.this.getExactSizeIfKnown();
            }

            public /* synthetic */ boolean hasCharacteristics(int i) {
                return OfLong.this.hasCharacteristics(i);
            }

            public /* synthetic */ int hashCode() {
                return OfLong.this.hashCode();
            }

            public /* synthetic */ boolean tryAdvance(Object obj) {
                return OfLong.this.tryAdvance(obj);
            }

            public /* synthetic */ boolean tryAdvance(Consumer consumer) {
                return OfLong.this.tryAdvance(consumer);
            }

            public /* synthetic */ boolean tryAdvance(LongConsumer longConsumer) {
                return OfLong.this.tryAdvance(longConsumer);
            }
        }

        void forEachRemaining(Consumer consumer);

        void forEachRemaining(LongConsumer longConsumer);

        boolean tryAdvance(Consumer consumer);

        boolean tryAdvance(LongConsumer longConsumer);

        OfLong trySplit();
    }

    public interface OfPrimitive extends Spliterator {

        public final /* synthetic */ class VivifiedWrapper implements OfPrimitive {
            public final /* synthetic */ Spliterator.OfPrimitive wrappedValue;

            private /* synthetic */ VivifiedWrapper(Spliterator.OfPrimitive ofPrimitive) {
                this.wrappedValue = ofPrimitive;
            }

            public static /* synthetic */ OfPrimitive convert(Spliterator.OfPrimitive ofPrimitive) {
                if (ofPrimitive == null) {
                    return null;
                }
                return ofPrimitive instanceof Wrapper ? OfPrimitive.this : ofPrimitive instanceof Spliterator.OfDouble ? OfDouble.VivifiedWrapper.convert((Spliterator.OfDouble) ofPrimitive) : ofPrimitive instanceof Spliterator.OfInt ? OfInt.VivifiedWrapper.convert((Spliterator.OfInt) ofPrimitive) : ofPrimitive instanceof Spliterator.OfLong ? OfLong.VivifiedWrapper.convert((Spliterator.OfLong) ofPrimitive) : new VivifiedWrapper(ofPrimitive);
            }

            public /* synthetic */ int characteristics() {
                return this.wrappedValue.characteristics();
            }

            public /* synthetic */ boolean equals(Object obj) {
                Spliterator.OfPrimitive ofPrimitive = this.wrappedValue;
                if (obj instanceof VivifiedWrapper) {
                    obj = ((VivifiedWrapper) obj).wrappedValue;
                }
                return ofPrimitive.equals(obj);
            }

            public /* synthetic */ long estimateSize() {
                return this.wrappedValue.estimateSize();
            }

            public /* synthetic */ void forEachRemaining(Object obj) {
                this.wrappedValue.forEachRemaining(obj);
            }

            public /* synthetic */ void forEachRemaining(Consumer consumer) {
                this.wrappedValue.forEachRemaining(consumer);
            }

            public /* synthetic */ Comparator getComparator() {
                return this.wrappedValue.getComparator();
            }

            public /* synthetic */ long getExactSizeIfKnown() {
                return this.wrappedValue.getExactSizeIfKnown();
            }

            public /* synthetic */ boolean hasCharacteristics(int i) {
                return this.wrappedValue.hasCharacteristics(i);
            }

            public /* synthetic */ int hashCode() {
                return this.wrappedValue.hashCode();
            }

            public /* synthetic */ boolean tryAdvance(Object obj) {
                return this.wrappedValue.tryAdvance(obj);
            }

            public /* synthetic */ boolean tryAdvance(Consumer consumer) {
                return this.wrappedValue.tryAdvance(consumer);
            }
        }

        public final /* synthetic */ class Wrapper implements Spliterator.OfPrimitive {
            private /* synthetic */ Wrapper() {
            }

            public static /* synthetic */ Spliterator.OfPrimitive convert(OfPrimitive ofPrimitive) {
                if (ofPrimitive == null) {
                    return null;
                }
                return ofPrimitive instanceof VivifiedWrapper ? ((VivifiedWrapper) ofPrimitive).wrappedValue : ofPrimitive instanceof OfDouble ? OfDouble.Wrapper.convert((OfDouble) ofPrimitive) : ofPrimitive instanceof OfInt ? OfInt.Wrapper.convert((OfInt) ofPrimitive) : ofPrimitive instanceof OfLong ? OfLong.Wrapper.convert((OfLong) ofPrimitive) : new Wrapper();
            }

            public /* synthetic */ int characteristics() {
                return OfPrimitive.this.characteristics();
            }

            public /* synthetic */ boolean equals(Object obj) {
                OfPrimitive ofPrimitive = OfPrimitive.this;
                if (obj instanceof Wrapper) {
                    obj = OfPrimitive.this;
                }
                return ofPrimitive.equals(obj);
            }

            public /* synthetic */ long estimateSize() {
                return OfPrimitive.this.estimateSize();
            }

            public /* synthetic */ void forEachRemaining(Object obj) {
                OfPrimitive.this.forEachRemaining(obj);
            }

            public /* synthetic */ void forEachRemaining(Consumer consumer) {
                OfPrimitive.this.forEachRemaining(consumer);
            }

            public /* synthetic */ Comparator getComparator() {
                return OfPrimitive.this.getComparator();
            }

            public /* synthetic */ long getExactSizeIfKnown() {
                return OfPrimitive.this.getExactSizeIfKnown();
            }

            public /* synthetic */ boolean hasCharacteristics(int i) {
                return OfPrimitive.this.hasCharacteristics(i);
            }

            public /* synthetic */ int hashCode() {
                return OfPrimitive.this.hashCode();
            }

            public /* synthetic */ boolean tryAdvance(Object obj) {
                return OfPrimitive.this.tryAdvance(obj);
            }

            public /* synthetic */ boolean tryAdvance(Consumer consumer) {
                return OfPrimitive.this.tryAdvance(consumer);
            }
        }

        void forEachRemaining(Object obj);

        boolean tryAdvance(Object obj);

        OfPrimitive trySplit();
    }

    public final /* synthetic */ class VivifiedWrapper implements Spliterator {
        public final /* synthetic */ java.util.Spliterator wrappedValue;

        private /* synthetic */ VivifiedWrapper(java.util.Spliterator spliterator) {
            this.wrappedValue = spliterator;
        }

        public static /* synthetic */ Spliterator convert(java.util.Spliterator spliterator) {
            if (spliterator == null) {
                return null;
            }
            return spliterator instanceof Wrapper ? Spliterator.this : spliterator instanceof Spliterator.OfPrimitive ? OfPrimitive.VivifiedWrapper.convert((Spliterator.OfPrimitive) spliterator) : new VivifiedWrapper(spliterator);
        }

        public /* synthetic */ int characteristics() {
            return this.wrappedValue.characteristics();
        }

        public /* synthetic */ boolean equals(Object obj) {
            java.util.Spliterator spliterator = this.wrappedValue;
            if (obj instanceof VivifiedWrapper) {
                obj = ((VivifiedWrapper) obj).wrappedValue;
            }
            return spliterator.equals(obj);
        }

        public /* synthetic */ long estimateSize() {
            return this.wrappedValue.estimateSize();
        }

        public /* synthetic */ void forEachRemaining(Consumer consumer) {
            this.wrappedValue.forEachRemaining(consumer);
        }

        public /* synthetic */ Comparator getComparator() {
            return this.wrappedValue.getComparator();
        }

        public /* synthetic */ long getExactSizeIfKnown() {
            return this.wrappedValue.getExactSizeIfKnown();
        }

        public /* synthetic */ boolean hasCharacteristics(int i) {
            return this.wrappedValue.hasCharacteristics(i);
        }

        public /* synthetic */ int hashCode() {
            return this.wrappedValue.hashCode();
        }

        public /* synthetic */ boolean tryAdvance(Consumer consumer) {
            return this.wrappedValue.tryAdvance(consumer);
        }

        public /* synthetic */ Spliterator trySplit() {
            return convert(this.wrappedValue.trySplit());
        }
    }

    public final /* synthetic */ class Wrapper implements java.util.Spliterator {
        private /* synthetic */ Wrapper() {
        }

        public static /* synthetic */ java.util.Spliterator convert(Spliterator spliterator) {
            if (spliterator == null) {
                return null;
            }
            return spliterator instanceof VivifiedWrapper ? ((VivifiedWrapper) spliterator).wrappedValue : spliterator instanceof OfPrimitive ? OfPrimitive.Wrapper.convert((OfPrimitive) spliterator) : new Wrapper();
        }

        public /* synthetic */ int characteristics() {
            return Spliterator.this.characteristics();
        }

        public /* synthetic */ boolean equals(Object obj) {
            Spliterator spliterator = Spliterator.this;
            if (obj instanceof Wrapper) {
                obj = Spliterator.this;
            }
            return spliterator.equals(obj);
        }

        public /* synthetic */ long estimateSize() {
            return Spliterator.this.estimateSize();
        }

        public /* synthetic */ void forEachRemaining(Consumer consumer) {
            Spliterator.this.forEachRemaining(consumer);
        }

        public /* synthetic */ Comparator getComparator() {
            return Spliterator.this.getComparator();
        }

        public /* synthetic */ long getExactSizeIfKnown() {
            return Spliterator.this.getExactSizeIfKnown();
        }

        public /* synthetic */ boolean hasCharacteristics(int i) {
            return Spliterator.this.hasCharacteristics(i);
        }

        public /* synthetic */ int hashCode() {
            return Spliterator.this.hashCode();
        }

        public /* synthetic */ boolean tryAdvance(Consumer consumer) {
            return Spliterator.this.tryAdvance(consumer);
        }

        public /* synthetic */ java.util.Spliterator trySplit() {
            return convert(Spliterator.this.trySplit());
        }
    }

    int characteristics();

    long estimateSize();

    void forEachRemaining(Consumer consumer);

    Comparator getComparator();

    long getExactSizeIfKnown();

    boolean hasCharacteristics(int i);

    boolean tryAdvance(Consumer consumer);

    Spliterator trySplit();
}
