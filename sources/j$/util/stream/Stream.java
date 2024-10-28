package j$.util.stream;

import j$.util.Optional;
import j$.util.OptionalConversions;
import j$.util.Spliterator;
import j$.util.stream.BaseStream;
import j$.util.stream.Collector;
import j$.util.stream.DoubleStream;
import j$.util.stream.IntStream;
import j$.util.stream.LongStream;
import java.util.Comparator;
import java.util.Iterator;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

public interface Stream<T> extends BaseStream<T, Stream<T>> {

    public final /* synthetic */ class VivifiedWrapper implements Stream {
        public final /* synthetic */ java.util.stream.Stream wrappedValue;

        private /* synthetic */ VivifiedWrapper(java.util.stream.Stream stream) {
            this.wrappedValue = stream;
        }

        public static /* synthetic */ Stream convert(java.util.stream.Stream stream) {
            if (stream == null) {
                return null;
            }
            return stream instanceof Wrapper ? Stream.this : new VivifiedWrapper(stream);
        }

        public /* synthetic */ boolean allMatch(Predicate predicate) {
            return this.wrappedValue.allMatch(predicate);
        }

        public /* synthetic */ boolean anyMatch(Predicate predicate) {
            return this.wrappedValue.anyMatch(predicate);
        }

        public /* synthetic */ void close() {
            this.wrappedValue.close();
        }

        public /* synthetic */ Object collect(Collector collector) {
            return this.wrappedValue.collect(Collector.Wrapper.convert(collector));
        }

        public /* synthetic */ Object collect(Supplier supplier, BiConsumer biConsumer, BiConsumer biConsumer2) {
            return this.wrappedValue.collect(supplier, biConsumer, biConsumer2);
        }

        public /* synthetic */ long count() {
            return this.wrappedValue.count();
        }

        public /* synthetic */ Stream distinct() {
            return convert(this.wrappedValue.distinct());
        }

        public /* synthetic */ Stream dropWhile(Predicate predicate) {
            return convert(this.wrappedValue.dropWhile(predicate));
        }

        public /* synthetic */ boolean equals(Object obj) {
            java.util.stream.Stream stream = this.wrappedValue;
            if (obj instanceof VivifiedWrapper) {
                obj = ((VivifiedWrapper) obj).wrappedValue;
            }
            return stream.equals(obj);
        }

        public /* synthetic */ Stream filter(Predicate predicate) {
            return convert(this.wrappedValue.filter(predicate));
        }

        public /* synthetic */ Optional findAny() {
            return OptionalConversions.convert(this.wrappedValue.findAny());
        }

        public /* synthetic */ Optional findFirst() {
            return OptionalConversions.convert(this.wrappedValue.findFirst());
        }

        public /* synthetic */ Stream flatMap(Function function) {
            return convert(this.wrappedValue.flatMap(FlatMapApiFlips.flipFunctionReturningStream(function)));
        }

        public /* synthetic */ DoubleStream flatMapToDouble(Function function) {
            return DoubleStream.VivifiedWrapper.convert(this.wrappedValue.flatMapToDouble(FlatMapApiFlips.flipFunctionReturningStream(function)));
        }

        public /* synthetic */ IntStream flatMapToInt(Function function) {
            return IntStream.VivifiedWrapper.convert(this.wrappedValue.flatMapToInt(FlatMapApiFlips.flipFunctionReturningStream(function)));
        }

        public /* synthetic */ LongStream flatMapToLong(Function function) {
            return LongStream.VivifiedWrapper.convert(this.wrappedValue.flatMapToLong(FlatMapApiFlips.flipFunctionReturningStream(function)));
        }

        public /* synthetic */ void forEach(Consumer consumer) {
            this.wrappedValue.forEach(consumer);
        }

        public /* synthetic */ void forEachOrdered(Consumer consumer) {
            this.wrappedValue.forEachOrdered(consumer);
        }

        public /* synthetic */ int hashCode() {
            return this.wrappedValue.hashCode();
        }

        public /* synthetic */ boolean isParallel() {
            return this.wrappedValue.isParallel();
        }

        public /* synthetic */ Iterator iterator() {
            return this.wrappedValue.iterator();
        }

        public /* synthetic */ Stream limit(long j) {
            return convert(this.wrappedValue.limit(j));
        }

        public /* synthetic */ Stream map(Function function) {
            return convert(this.wrappedValue.map(function));
        }

        public /* synthetic */ DoubleStream mapToDouble(ToDoubleFunction toDoubleFunction) {
            return DoubleStream.VivifiedWrapper.convert(this.wrappedValue.mapToDouble(toDoubleFunction));
        }

        public /* synthetic */ IntStream mapToInt(ToIntFunction toIntFunction) {
            return IntStream.VivifiedWrapper.convert(this.wrappedValue.mapToInt(toIntFunction));
        }

        public /* synthetic */ LongStream mapToLong(ToLongFunction toLongFunction) {
            return LongStream.VivifiedWrapper.convert(this.wrappedValue.mapToLong(toLongFunction));
        }

        public /* synthetic */ Optional max(Comparator comparator) {
            return OptionalConversions.convert(this.wrappedValue.max(comparator));
        }

        public /* synthetic */ Optional min(Comparator comparator) {
            return OptionalConversions.convert(this.wrappedValue.min(comparator));
        }

        public /* synthetic */ boolean noneMatch(Predicate predicate) {
            return this.wrappedValue.noneMatch(predicate);
        }

        public /* synthetic */ BaseStream onClose(Runnable runnable) {
            return BaseStream.VivifiedWrapper.convert(this.wrappedValue.onClose(runnable));
        }

        public /* synthetic */ BaseStream parallel() {
            return BaseStream.VivifiedWrapper.convert(this.wrappedValue.parallel());
        }

        public /* synthetic */ Stream peek(Consumer consumer) {
            return convert(this.wrappedValue.peek(consumer));
        }

        public /* synthetic */ Optional reduce(BinaryOperator binaryOperator) {
            return OptionalConversions.convert(this.wrappedValue.reduce(binaryOperator));
        }

        public /* synthetic */ Object reduce(Object obj, BiFunction biFunction, BinaryOperator binaryOperator) {
            return this.wrappedValue.reduce(obj, biFunction, binaryOperator);
        }

        public /* synthetic */ Object reduce(Object obj, BinaryOperator binaryOperator) {
            return this.wrappedValue.reduce(obj, binaryOperator);
        }

        public /* synthetic */ BaseStream sequential() {
            return BaseStream.VivifiedWrapper.convert(this.wrappedValue.sequential());
        }

        public /* synthetic */ Stream skip(long j) {
            return convert(this.wrappedValue.skip(j));
        }

        public /* synthetic */ Stream sorted() {
            return convert(this.wrappedValue.sorted());
        }

        public /* synthetic */ Stream sorted(Comparator comparator) {
            return convert(this.wrappedValue.sorted(comparator));
        }

        public /* synthetic */ Spliterator spliterator() {
            return Spliterator.VivifiedWrapper.convert(this.wrappedValue.spliterator());
        }

        public /* synthetic */ Stream takeWhile(Predicate predicate) {
            return convert(this.wrappedValue.takeWhile(predicate));
        }

        public /* synthetic */ Object[] toArray() {
            return this.wrappedValue.toArray();
        }

        public /* synthetic */ Object[] toArray(IntFunction intFunction) {
            return this.wrappedValue.toArray(intFunction);
        }

        public /* synthetic */ BaseStream unordered() {
            return BaseStream.VivifiedWrapper.convert(this.wrappedValue.unordered());
        }
    }

    public final /* synthetic */ class Wrapper implements java.util.stream.Stream {
        private /* synthetic */ Wrapper() {
        }

        public static /* synthetic */ java.util.stream.Stream convert(Stream stream) {
            if (stream == null) {
                return null;
            }
            return stream instanceof VivifiedWrapper ? ((VivifiedWrapper) stream).wrappedValue : new Wrapper();
        }

        public /* synthetic */ boolean allMatch(Predicate predicate) {
            return Stream.this.allMatch(predicate);
        }

        public /* synthetic */ boolean anyMatch(Predicate predicate) {
            return Stream.this.anyMatch(predicate);
        }

        public /* synthetic */ void close() {
            Stream.this.close();
        }

        public /* synthetic */ Object collect(Supplier supplier, BiConsumer biConsumer, BiConsumer biConsumer2) {
            return Stream.this.collect(supplier, biConsumer, biConsumer2);
        }

        public /* synthetic */ Object collect(java.util.stream.Collector collector) {
            return Stream.this.collect(Collector.VivifiedWrapper.convert(collector));
        }

        public /* synthetic */ long count() {
            return Stream.this.count();
        }

        public /* synthetic */ java.util.stream.Stream distinct() {
            return convert(Stream.this.distinct());
        }

        public /* synthetic */ java.util.stream.Stream dropWhile(Predicate predicate) {
            return convert(Stream.this.dropWhile(predicate));
        }

        public /* synthetic */ boolean equals(Object obj) {
            Stream stream = Stream.this;
            if (obj instanceof Wrapper) {
                obj = Stream.this;
            }
            return stream.equals(obj);
        }

        public /* synthetic */ java.util.stream.Stream filter(Predicate predicate) {
            return convert(Stream.this.filter(predicate));
        }

        public /* synthetic */ java.util.Optional findAny() {
            return OptionalConversions.convert(Stream.this.findAny());
        }

        public /* synthetic */ java.util.Optional findFirst() {
            return OptionalConversions.convert(Stream.this.findFirst());
        }

        public /* synthetic */ java.util.stream.Stream flatMap(Function function) {
            return convert(Stream.this.flatMap(FlatMapApiFlips.flipFunctionReturningStream(function)));
        }

        public /* synthetic */ java.util.stream.DoubleStream flatMapToDouble(Function function) {
            return DoubleStream.Wrapper.convert(Stream.this.flatMapToDouble(FlatMapApiFlips.flipFunctionReturningStream(function)));
        }

        public /* synthetic */ java.util.stream.IntStream flatMapToInt(Function function) {
            return IntStream.Wrapper.convert(Stream.this.flatMapToInt(FlatMapApiFlips.flipFunctionReturningStream(function)));
        }

        public /* synthetic */ java.util.stream.LongStream flatMapToLong(Function function) {
            return LongStream.Wrapper.convert(Stream.this.flatMapToLong(FlatMapApiFlips.flipFunctionReturningStream(function)));
        }

        public /* synthetic */ void forEach(Consumer consumer) {
            Stream.this.forEach(consumer);
        }

        public /* synthetic */ void forEachOrdered(Consumer consumer) {
            Stream.this.forEachOrdered(consumer);
        }

        public /* synthetic */ int hashCode() {
            return Stream.this.hashCode();
        }

        public /* synthetic */ boolean isParallel() {
            return Stream.this.isParallel();
        }

        public /* synthetic */ Iterator iterator() {
            return Stream.this.iterator();
        }

        public /* synthetic */ java.util.stream.Stream limit(long j) {
            return convert(Stream.this.limit(j));
        }

        public /* synthetic */ java.util.stream.Stream map(Function function) {
            return convert(Stream.this.map(function));
        }

        public /* synthetic */ java.util.stream.DoubleStream mapToDouble(ToDoubleFunction toDoubleFunction) {
            return DoubleStream.Wrapper.convert(Stream.this.mapToDouble(toDoubleFunction));
        }

        public /* synthetic */ java.util.stream.IntStream mapToInt(ToIntFunction toIntFunction) {
            return IntStream.Wrapper.convert(Stream.this.mapToInt(toIntFunction));
        }

        public /* synthetic */ java.util.stream.LongStream mapToLong(ToLongFunction toLongFunction) {
            return LongStream.Wrapper.convert(Stream.this.mapToLong(toLongFunction));
        }

        public /* synthetic */ java.util.Optional max(Comparator comparator) {
            return OptionalConversions.convert(Stream.this.max(comparator));
        }

        public /* synthetic */ java.util.Optional min(Comparator comparator) {
            return OptionalConversions.convert(Stream.this.min(comparator));
        }

        public /* synthetic */ boolean noneMatch(Predicate predicate) {
            return Stream.this.noneMatch(predicate);
        }

        public /* synthetic */ java.util.stream.BaseStream onClose(Runnable runnable) {
            return BaseStream.Wrapper.convert(Stream.this.onClose(runnable));
        }

        public /* synthetic */ java.util.stream.BaseStream parallel() {
            return BaseStream.Wrapper.convert(Stream.this.parallel());
        }

        public /* synthetic */ java.util.stream.Stream peek(Consumer consumer) {
            return convert(Stream.this.peek(consumer));
        }

        public /* synthetic */ Object reduce(Object obj, BiFunction biFunction, BinaryOperator binaryOperator) {
            return Stream.this.reduce(obj, biFunction, binaryOperator);
        }

        public /* synthetic */ Object reduce(Object obj, BinaryOperator binaryOperator) {
            return Stream.this.reduce(obj, binaryOperator);
        }

        public /* synthetic */ java.util.Optional reduce(BinaryOperator binaryOperator) {
            return OptionalConversions.convert(Stream.this.reduce(binaryOperator));
        }

        public /* synthetic */ java.util.stream.BaseStream sequential() {
            return BaseStream.Wrapper.convert(Stream.this.sequential());
        }

        public /* synthetic */ java.util.stream.Stream skip(long j) {
            return convert(Stream.this.skip(j));
        }

        public /* synthetic */ java.util.stream.Stream sorted() {
            return convert(Stream.this.sorted());
        }

        public /* synthetic */ java.util.stream.Stream sorted(Comparator comparator) {
            return convert(Stream.this.sorted(comparator));
        }

        public /* synthetic */ java.util.Spliterator spliterator() {
            return Spliterator.Wrapper.convert(Stream.this.spliterator());
        }

        public /* synthetic */ java.util.stream.Stream takeWhile(Predicate predicate) {
            return convert(Stream.this.takeWhile(predicate));
        }

        public /* synthetic */ Object[] toArray() {
            return Stream.this.toArray();
        }

        public /* synthetic */ Object[] toArray(IntFunction intFunction) {
            return Stream.this.toArray(intFunction);
        }

        public /* synthetic */ java.util.stream.BaseStream unordered() {
            return BaseStream.Wrapper.convert(Stream.this.unordered());
        }
    }

    boolean allMatch(Predicate predicate);

    boolean anyMatch(Predicate predicate);

    <R, A> R collect(Collector<? super T, A, R> collector);

    Object collect(Supplier supplier, BiConsumer biConsumer, BiConsumer biConsumer2);

    long count();

    Stream distinct();

    Stream dropWhile(Predicate predicate);

    Stream filter(Predicate predicate);

    Optional findAny();

    Optional findFirst();

    Stream flatMap(Function function);

    DoubleStream flatMapToDouble(Function function);

    IntStream flatMapToInt(Function function);

    LongStream flatMapToLong(Function function);

    void forEach(Consumer consumer);

    void forEachOrdered(Consumer consumer);

    Stream limit(long j);

    <R> Stream<R> map(Function<? super T, ? extends R> function);

    DoubleStream mapToDouble(ToDoubleFunction toDoubleFunction);

    IntStream mapToInt(ToIntFunction toIntFunction);

    LongStream mapToLong(ToLongFunction toLongFunction);

    Optional max(Comparator comparator);

    Optional min(Comparator comparator);

    boolean noneMatch(Predicate predicate);

    Stream peek(Consumer consumer);

    Optional reduce(BinaryOperator binaryOperator);

    Object reduce(Object obj, BiFunction biFunction, BinaryOperator binaryOperator);

    Object reduce(Object obj, BinaryOperator binaryOperator);

    Stream skip(long j);

    Stream sorted();

    Stream sorted(Comparator comparator);

    Stream takeWhile(Predicate predicate);

    Object[] toArray();

    Object[] toArray(IntFunction intFunction);
}
