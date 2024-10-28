package j$.util.stream;

import j$.util.IntSummaryStatistics;
import j$.util.IntSummaryStatisticsConversions;
import j$.util.OptionalConversions;
import j$.util.OptionalDouble;
import j$.util.OptionalInt;
import j$.util.PrimitiveIterator;
import j$.util.Spliterator;
import j$.util.stream.BaseStream;
import j$.util.stream.DoubleStream;
import j$.util.stream.LongStream;
import j$.util.stream.Stream;
import java.util.function.BiConsumer;
import java.util.function.IntBinaryOperator;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.ObjIntConsumer;
import java.util.function.Supplier;

public interface IntStream extends BaseStream<Integer, IntStream> {

    public final /* synthetic */ class VivifiedWrapper implements IntStream {
        public final /* synthetic */ java.util.stream.IntStream wrappedValue;

        private /* synthetic */ VivifiedWrapper(java.util.stream.IntStream intStream) {
            this.wrappedValue = intStream;
        }

        public static /* synthetic */ IntStream convert(java.util.stream.IntStream intStream) {
            if (intStream == null) {
                return null;
            }
            return intStream instanceof Wrapper ? IntStream.this : new VivifiedWrapper(intStream);
        }

        public /* synthetic */ boolean allMatch(IntPredicate intPredicate) {
            return this.wrappedValue.allMatch(intPredicate);
        }

        public /* synthetic */ boolean anyMatch(IntPredicate intPredicate) {
            return this.wrappedValue.anyMatch(intPredicate);
        }

        public /* synthetic */ DoubleStream asDoubleStream() {
            return DoubleStream.VivifiedWrapper.convert(this.wrappedValue.asDoubleStream());
        }

        public /* synthetic */ LongStream asLongStream() {
            return LongStream.VivifiedWrapper.convert(this.wrappedValue.asLongStream());
        }

        public /* synthetic */ OptionalDouble average() {
            return OptionalConversions.convert(this.wrappedValue.average());
        }

        public /* synthetic */ Stream boxed() {
            return Stream.VivifiedWrapper.convert(this.wrappedValue.boxed());
        }

        public /* synthetic */ void close() {
            this.wrappedValue.close();
        }

        public /* synthetic */ Object collect(Supplier supplier, ObjIntConsumer objIntConsumer, BiConsumer biConsumer) {
            return this.wrappedValue.collect(supplier, objIntConsumer, biConsumer);
        }

        public /* synthetic */ long count() {
            return this.wrappedValue.count();
        }

        public /* synthetic */ IntStream distinct() {
            return convert(this.wrappedValue.distinct());
        }

        public /* synthetic */ boolean equals(Object obj) {
            java.util.stream.IntStream intStream = this.wrappedValue;
            if (obj instanceof VivifiedWrapper) {
                obj = ((VivifiedWrapper) obj).wrappedValue;
            }
            return intStream.equals(obj);
        }

        public /* synthetic */ IntStream filter(IntPredicate intPredicate) {
            return convert(this.wrappedValue.filter(intPredicate));
        }

        public /* synthetic */ OptionalInt findAny() {
            return OptionalConversions.convert(this.wrappedValue.findAny());
        }

        public /* synthetic */ OptionalInt findFirst() {
            return OptionalConversions.convert(this.wrappedValue.findFirst());
        }

        public /* synthetic */ IntStream flatMap(IntFunction intFunction) {
            return convert(this.wrappedValue.flatMap(FlatMapApiFlips.flipFunctionReturningStream(intFunction)));
        }

        public /* synthetic */ void forEach(IntConsumer intConsumer) {
            this.wrappedValue.forEach(intConsumer);
        }

        public /* synthetic */ void forEachOrdered(IntConsumer intConsumer) {
            this.wrappedValue.forEachOrdered(intConsumer);
        }

        public /* synthetic */ int hashCode() {
            return this.wrappedValue.hashCode();
        }

        public /* synthetic */ boolean isParallel() {
            return this.wrappedValue.isParallel();
        }

        public /* synthetic */ IntStream limit(long j) {
            return convert(this.wrappedValue.limit(j));
        }

        public /* synthetic */ IntStream map(IntUnaryOperator intUnaryOperator) {
            return convert(this.wrappedValue.map(intUnaryOperator));
        }

        public /* synthetic */ DoubleStream mapToDouble(IntToDoubleFunction intToDoubleFunction) {
            return DoubleStream.VivifiedWrapper.convert(this.wrappedValue.mapToDouble(intToDoubleFunction));
        }

        public /* synthetic */ LongStream mapToLong(IntToLongFunction intToLongFunction) {
            return LongStream.VivifiedWrapper.convert(this.wrappedValue.mapToLong(intToLongFunction));
        }

        public /* synthetic */ Stream mapToObj(IntFunction intFunction) {
            return Stream.VivifiedWrapper.convert(this.wrappedValue.mapToObj(intFunction));
        }

        public /* synthetic */ OptionalInt max() {
            return OptionalConversions.convert(this.wrappedValue.max());
        }

        public /* synthetic */ OptionalInt min() {
            return OptionalConversions.convert(this.wrappedValue.min());
        }

        public /* synthetic */ boolean noneMatch(IntPredicate intPredicate) {
            return this.wrappedValue.noneMatch(intPredicate);
        }

        public /* synthetic */ BaseStream onClose(Runnable runnable) {
            return BaseStream.VivifiedWrapper.convert(this.wrappedValue.onClose(runnable));
        }

        public /* synthetic */ IntStream peek(IntConsumer intConsumer) {
            return convert(this.wrappedValue.peek(intConsumer));
        }

        public /* synthetic */ int reduce(int i, IntBinaryOperator intBinaryOperator) {
            return this.wrappedValue.reduce(i, intBinaryOperator);
        }

        public /* synthetic */ OptionalInt reduce(IntBinaryOperator intBinaryOperator) {
            return OptionalConversions.convert(this.wrappedValue.reduce(intBinaryOperator));
        }

        public /* synthetic */ IntStream skip(long j) {
            return convert(this.wrappedValue.skip(j));
        }

        public /* synthetic */ IntStream sorted() {
            return convert(this.wrappedValue.sorted());
        }

        public /* synthetic */ int sum() {
            return this.wrappedValue.sum();
        }

        public /* synthetic */ IntSummaryStatistics summaryStatistics() {
            return IntSummaryStatisticsConversions.convert(this.wrappedValue.summaryStatistics());
        }

        public /* synthetic */ int[] toArray() {
            return this.wrappedValue.toArray();
        }

        public /* synthetic */ BaseStream unordered() {
            return BaseStream.VivifiedWrapper.convert(this.wrappedValue.unordered());
        }
    }

    public final /* synthetic */ class Wrapper implements java.util.stream.IntStream {
        private /* synthetic */ Wrapper() {
        }

        public static /* synthetic */ java.util.stream.IntStream convert(IntStream intStream) {
            if (intStream == null) {
                return null;
            }
            return intStream instanceof VivifiedWrapper ? ((VivifiedWrapper) intStream).wrappedValue : new Wrapper();
        }

        public /* synthetic */ boolean allMatch(IntPredicate intPredicate) {
            return IntStream.this.allMatch(intPredicate);
        }

        public /* synthetic */ boolean anyMatch(IntPredicate intPredicate) {
            return IntStream.this.anyMatch(intPredicate);
        }

        public /* synthetic */ java.util.stream.DoubleStream asDoubleStream() {
            return DoubleStream.Wrapper.convert(IntStream.this.asDoubleStream());
        }

        public /* synthetic */ java.util.stream.LongStream asLongStream() {
            return LongStream.Wrapper.convert(IntStream.this.asLongStream());
        }

        public /* synthetic */ java.util.OptionalDouble average() {
            return OptionalConversions.convert(IntStream.this.average());
        }

        public /* synthetic */ java.util.stream.Stream boxed() {
            return Stream.Wrapper.convert(IntStream.this.boxed());
        }

        public /* synthetic */ void close() {
            IntStream.this.close();
        }

        public /* synthetic */ Object collect(Supplier supplier, ObjIntConsumer objIntConsumer, BiConsumer biConsumer) {
            return IntStream.this.collect(supplier, objIntConsumer, biConsumer);
        }

        public /* synthetic */ long count() {
            return IntStream.this.count();
        }

        public /* synthetic */ java.util.stream.IntStream distinct() {
            return convert(IntStream.this.distinct());
        }

        public /* synthetic */ boolean equals(Object obj) {
            IntStream intStream = IntStream.this;
            if (obj instanceof Wrapper) {
                obj = IntStream.this;
            }
            return intStream.equals(obj);
        }

        public /* synthetic */ java.util.stream.IntStream filter(IntPredicate intPredicate) {
            return convert(IntStream.this.filter(intPredicate));
        }

        public /* synthetic */ java.util.OptionalInt findAny() {
            return OptionalConversions.convert(IntStream.this.findAny());
        }

        public /* synthetic */ java.util.OptionalInt findFirst() {
            return OptionalConversions.convert(IntStream.this.findFirst());
        }

        public /* synthetic */ java.util.stream.IntStream flatMap(IntFunction intFunction) {
            return convert(IntStream.this.flatMap(FlatMapApiFlips.flipFunctionReturningStream(intFunction)));
        }

        public /* synthetic */ void forEach(IntConsumer intConsumer) {
            IntStream.this.forEach(intConsumer);
        }

        public /* synthetic */ void forEachOrdered(IntConsumer intConsumer) {
            IntStream.this.forEachOrdered(intConsumer);
        }

        public /* synthetic */ int hashCode() {
            return IntStream.this.hashCode();
        }

        public /* synthetic */ boolean isParallel() {
            return IntStream.this.isParallel();
        }

        public /* synthetic */ java.util.stream.IntStream limit(long j) {
            return convert(IntStream.this.limit(j));
        }

        public /* synthetic */ java.util.stream.IntStream map(IntUnaryOperator intUnaryOperator) {
            return convert(IntStream.this.map(intUnaryOperator));
        }

        public /* synthetic */ java.util.stream.DoubleStream mapToDouble(IntToDoubleFunction intToDoubleFunction) {
            return DoubleStream.Wrapper.convert(IntStream.this.mapToDouble(intToDoubleFunction));
        }

        public /* synthetic */ java.util.stream.LongStream mapToLong(IntToLongFunction intToLongFunction) {
            return LongStream.Wrapper.convert(IntStream.this.mapToLong(intToLongFunction));
        }

        public /* synthetic */ java.util.stream.Stream mapToObj(IntFunction intFunction) {
            return Stream.Wrapper.convert(IntStream.this.mapToObj(intFunction));
        }

        public /* synthetic */ java.util.OptionalInt max() {
            return OptionalConversions.convert(IntStream.this.max());
        }

        public /* synthetic */ java.util.OptionalInt min() {
            return OptionalConversions.convert(IntStream.this.min());
        }

        public /* synthetic */ boolean noneMatch(IntPredicate intPredicate) {
            return IntStream.this.noneMatch(intPredicate);
        }

        public /* synthetic */ java.util.stream.BaseStream onClose(Runnable runnable) {
            return BaseStream.Wrapper.convert(IntStream.this.onClose(runnable));
        }

        public /* synthetic */ java.util.stream.IntStream peek(IntConsumer intConsumer) {
            return convert(IntStream.this.peek(intConsumer));
        }

        public /* synthetic */ int reduce(int i, IntBinaryOperator intBinaryOperator) {
            return IntStream.this.reduce(i, intBinaryOperator);
        }

        public /* synthetic */ java.util.OptionalInt reduce(IntBinaryOperator intBinaryOperator) {
            return OptionalConversions.convert(IntStream.this.reduce(intBinaryOperator));
        }

        public /* synthetic */ java.util.stream.IntStream skip(long j) {
            return convert(IntStream.this.skip(j));
        }

        public /* synthetic */ java.util.stream.IntStream sorted() {
            return convert(IntStream.this.sorted());
        }

        public /* synthetic */ int sum() {
            return IntStream.this.sum();
        }

        public /* synthetic */ java.util.IntSummaryStatistics summaryStatistics() {
            return IntSummaryStatisticsConversions.convert(IntStream.this.summaryStatistics());
        }

        public /* synthetic */ int[] toArray() {
            return IntStream.this.toArray();
        }

        public /* synthetic */ java.util.stream.BaseStream unordered() {
            return BaseStream.Wrapper.convert(IntStream.this.unordered());
        }
    }

    boolean allMatch(IntPredicate intPredicate);

    boolean anyMatch(IntPredicate intPredicate);

    DoubleStream asDoubleStream();

    LongStream asLongStream();

    OptionalDouble average();

    Stream boxed();

    Object collect(Supplier supplier, ObjIntConsumer objIntConsumer, BiConsumer biConsumer);

    long count();

    IntStream distinct();

    IntStream filter(IntPredicate intPredicate);

    OptionalInt findAny();

    OptionalInt findFirst();

    IntStream flatMap(IntFunction intFunction);

    void forEach(IntConsumer intConsumer);

    void forEachOrdered(IntConsumer intConsumer);

    PrimitiveIterator.OfInt iterator();

    IntStream limit(long j);

    IntStream map(IntUnaryOperator intUnaryOperator);

    DoubleStream mapToDouble(IntToDoubleFunction intToDoubleFunction);

    LongStream mapToLong(IntToLongFunction intToLongFunction);

    Stream mapToObj(IntFunction intFunction);

    OptionalInt max();

    OptionalInt min();

    boolean noneMatch(IntPredicate intPredicate);

    IntStream parallel();

    IntStream peek(IntConsumer intConsumer);

    int reduce(int i, IntBinaryOperator intBinaryOperator);

    OptionalInt reduce(IntBinaryOperator intBinaryOperator);

    IntStream sequential();

    IntStream skip(long j);

    IntStream sorted();

    Spliterator.OfInt spliterator();

    int sum();

    IntSummaryStatistics summaryStatistics();

    int[] toArray();
}
