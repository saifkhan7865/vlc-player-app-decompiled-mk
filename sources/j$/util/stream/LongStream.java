package j$.util.stream;

import j$.util.LongSummaryStatistics;
import j$.util.LongSummaryStatisticsConversions;
import j$.util.OptionalConversions;
import j$.util.OptionalDouble;
import j$.util.OptionalLong;
import j$.util.PrimitiveIterator;
import j$.util.Spliterator;
import j$.util.stream.BaseStream;
import j$.util.stream.DoubleStream;
import j$.util.stream.IntStream;
import j$.util.stream.Stream;
import java.util.function.BiConsumer;
import java.util.function.LongBinaryOperator;
import java.util.function.LongConsumer;
import java.util.function.LongFunction;
import java.util.function.LongPredicate;
import java.util.function.LongToDoubleFunction;
import java.util.function.LongToIntFunction;
import java.util.function.LongUnaryOperator;
import java.util.function.ObjLongConsumer;
import java.util.function.Supplier;

public interface LongStream extends BaseStream<Long, LongStream> {

    public final /* synthetic */ class VivifiedWrapper implements LongStream {
        public final /* synthetic */ java.util.stream.LongStream wrappedValue;

        private /* synthetic */ VivifiedWrapper(java.util.stream.LongStream longStream) {
            this.wrappedValue = longStream;
        }

        public static /* synthetic */ LongStream convert(java.util.stream.LongStream longStream) {
            if (longStream == null) {
                return null;
            }
            return longStream instanceof Wrapper ? LongStream.this : new VivifiedWrapper(longStream);
        }

        public /* synthetic */ boolean allMatch(LongPredicate longPredicate) {
            return this.wrappedValue.allMatch(longPredicate);
        }

        public /* synthetic */ boolean anyMatch(LongPredicate longPredicate) {
            return this.wrappedValue.anyMatch(longPredicate);
        }

        public /* synthetic */ DoubleStream asDoubleStream() {
            return DoubleStream.VivifiedWrapper.convert(this.wrappedValue.asDoubleStream());
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

        public /* synthetic */ Object collect(Supplier supplier, ObjLongConsumer objLongConsumer, BiConsumer biConsumer) {
            return this.wrappedValue.collect(supplier, objLongConsumer, biConsumer);
        }

        public /* synthetic */ long count() {
            return this.wrappedValue.count();
        }

        public /* synthetic */ LongStream distinct() {
            return convert(this.wrappedValue.distinct());
        }

        public /* synthetic */ boolean equals(Object obj) {
            java.util.stream.LongStream longStream = this.wrappedValue;
            if (obj instanceof VivifiedWrapper) {
                obj = ((VivifiedWrapper) obj).wrappedValue;
            }
            return longStream.equals(obj);
        }

        public /* synthetic */ LongStream filter(LongPredicate longPredicate) {
            return convert(this.wrappedValue.filter(longPredicate));
        }

        public /* synthetic */ OptionalLong findAny() {
            return OptionalConversions.convert(this.wrappedValue.findAny());
        }

        public /* synthetic */ OptionalLong findFirst() {
            return OptionalConversions.convert(this.wrappedValue.findFirst());
        }

        public /* synthetic */ LongStream flatMap(LongFunction longFunction) {
            return convert(this.wrappedValue.flatMap(FlatMapApiFlips.flipFunctionReturningStream(longFunction)));
        }

        public /* synthetic */ void forEach(LongConsumer longConsumer) {
            this.wrappedValue.forEach(longConsumer);
        }

        public /* synthetic */ void forEachOrdered(LongConsumer longConsumer) {
            this.wrappedValue.forEachOrdered(longConsumer);
        }

        public /* synthetic */ int hashCode() {
            return this.wrappedValue.hashCode();
        }

        public /* synthetic */ boolean isParallel() {
            return this.wrappedValue.isParallel();
        }

        public /* synthetic */ LongStream limit(long j) {
            return convert(this.wrappedValue.limit(j));
        }

        public /* synthetic */ LongStream map(LongUnaryOperator longUnaryOperator) {
            return convert(this.wrappedValue.map(longUnaryOperator));
        }

        public /* synthetic */ DoubleStream mapToDouble(LongToDoubleFunction longToDoubleFunction) {
            return DoubleStream.VivifiedWrapper.convert(this.wrappedValue.mapToDouble(longToDoubleFunction));
        }

        public /* synthetic */ IntStream mapToInt(LongToIntFunction longToIntFunction) {
            return IntStream.VivifiedWrapper.convert(this.wrappedValue.mapToInt(longToIntFunction));
        }

        public /* synthetic */ Stream mapToObj(LongFunction longFunction) {
            return Stream.VivifiedWrapper.convert(this.wrappedValue.mapToObj(longFunction));
        }

        public /* synthetic */ OptionalLong max() {
            return OptionalConversions.convert(this.wrappedValue.max());
        }

        public /* synthetic */ OptionalLong min() {
            return OptionalConversions.convert(this.wrappedValue.min());
        }

        public /* synthetic */ boolean noneMatch(LongPredicate longPredicate) {
            return this.wrappedValue.noneMatch(longPredicate);
        }

        public /* synthetic */ BaseStream onClose(Runnable runnable) {
            return BaseStream.VivifiedWrapper.convert(this.wrappedValue.onClose(runnable));
        }

        public /* synthetic */ LongStream peek(LongConsumer longConsumer) {
            return convert(this.wrappedValue.peek(longConsumer));
        }

        public /* synthetic */ long reduce(long j, LongBinaryOperator longBinaryOperator) {
            return this.wrappedValue.reduce(j, longBinaryOperator);
        }

        public /* synthetic */ OptionalLong reduce(LongBinaryOperator longBinaryOperator) {
            return OptionalConversions.convert(this.wrappedValue.reduce(longBinaryOperator));
        }

        public /* synthetic */ LongStream skip(long j) {
            return convert(this.wrappedValue.skip(j));
        }

        public /* synthetic */ LongStream sorted() {
            return convert(this.wrappedValue.sorted());
        }

        public /* synthetic */ long sum() {
            return this.wrappedValue.sum();
        }

        public /* synthetic */ LongSummaryStatistics summaryStatistics() {
            return LongSummaryStatisticsConversions.convert(this.wrappedValue.summaryStatistics());
        }

        public /* synthetic */ long[] toArray() {
            return this.wrappedValue.toArray();
        }

        public /* synthetic */ BaseStream unordered() {
            return BaseStream.VivifiedWrapper.convert(this.wrappedValue.unordered());
        }
    }

    public final /* synthetic */ class Wrapper implements java.util.stream.LongStream {
        private /* synthetic */ Wrapper() {
        }

        public static /* synthetic */ java.util.stream.LongStream convert(LongStream longStream) {
            if (longStream == null) {
                return null;
            }
            return longStream instanceof VivifiedWrapper ? ((VivifiedWrapper) longStream).wrappedValue : new Wrapper();
        }

        public /* synthetic */ boolean allMatch(LongPredicate longPredicate) {
            return LongStream.this.allMatch(longPredicate);
        }

        public /* synthetic */ boolean anyMatch(LongPredicate longPredicate) {
            return LongStream.this.anyMatch(longPredicate);
        }

        public /* synthetic */ java.util.stream.DoubleStream asDoubleStream() {
            return DoubleStream.Wrapper.convert(LongStream.this.asDoubleStream());
        }

        public /* synthetic */ java.util.OptionalDouble average() {
            return OptionalConversions.convert(LongStream.this.average());
        }

        public /* synthetic */ java.util.stream.Stream boxed() {
            return Stream.Wrapper.convert(LongStream.this.boxed());
        }

        public /* synthetic */ void close() {
            LongStream.this.close();
        }

        public /* synthetic */ Object collect(Supplier supplier, ObjLongConsumer objLongConsumer, BiConsumer biConsumer) {
            return LongStream.this.collect(supplier, objLongConsumer, biConsumer);
        }

        public /* synthetic */ long count() {
            return LongStream.this.count();
        }

        public /* synthetic */ java.util.stream.LongStream distinct() {
            return convert(LongStream.this.distinct());
        }

        public /* synthetic */ boolean equals(Object obj) {
            LongStream longStream = LongStream.this;
            if (obj instanceof Wrapper) {
                obj = LongStream.this;
            }
            return longStream.equals(obj);
        }

        public /* synthetic */ java.util.stream.LongStream filter(LongPredicate longPredicate) {
            return convert(LongStream.this.filter(longPredicate));
        }

        public /* synthetic */ java.util.OptionalLong findAny() {
            return OptionalConversions.convert(LongStream.this.findAny());
        }

        public /* synthetic */ java.util.OptionalLong findFirst() {
            return OptionalConversions.convert(LongStream.this.findFirst());
        }

        public /* synthetic */ java.util.stream.LongStream flatMap(LongFunction longFunction) {
            return convert(LongStream.this.flatMap(FlatMapApiFlips.flipFunctionReturningStream(longFunction)));
        }

        public /* synthetic */ void forEach(LongConsumer longConsumer) {
            LongStream.this.forEach(longConsumer);
        }

        public /* synthetic */ void forEachOrdered(LongConsumer longConsumer) {
            LongStream.this.forEachOrdered(longConsumer);
        }

        public /* synthetic */ int hashCode() {
            return LongStream.this.hashCode();
        }

        public /* synthetic */ boolean isParallel() {
            return LongStream.this.isParallel();
        }

        public /* synthetic */ java.util.stream.LongStream limit(long j) {
            return convert(LongStream.this.limit(j));
        }

        public /* synthetic */ java.util.stream.LongStream map(LongUnaryOperator longUnaryOperator) {
            return convert(LongStream.this.map(longUnaryOperator));
        }

        public /* synthetic */ java.util.stream.DoubleStream mapToDouble(LongToDoubleFunction longToDoubleFunction) {
            return DoubleStream.Wrapper.convert(LongStream.this.mapToDouble(longToDoubleFunction));
        }

        public /* synthetic */ java.util.stream.IntStream mapToInt(LongToIntFunction longToIntFunction) {
            return IntStream.Wrapper.convert(LongStream.this.mapToInt(longToIntFunction));
        }

        public /* synthetic */ java.util.stream.Stream mapToObj(LongFunction longFunction) {
            return Stream.Wrapper.convert(LongStream.this.mapToObj(longFunction));
        }

        public /* synthetic */ java.util.OptionalLong max() {
            return OptionalConversions.convert(LongStream.this.max());
        }

        public /* synthetic */ java.util.OptionalLong min() {
            return OptionalConversions.convert(LongStream.this.min());
        }

        public /* synthetic */ boolean noneMatch(LongPredicate longPredicate) {
            return LongStream.this.noneMatch(longPredicate);
        }

        public /* synthetic */ java.util.stream.BaseStream onClose(Runnable runnable) {
            return BaseStream.Wrapper.convert(LongStream.this.onClose(runnable));
        }

        public /* synthetic */ java.util.stream.LongStream peek(LongConsumer longConsumer) {
            return convert(LongStream.this.peek(longConsumer));
        }

        public /* synthetic */ long reduce(long j, LongBinaryOperator longBinaryOperator) {
            return LongStream.this.reduce(j, longBinaryOperator);
        }

        public /* synthetic */ java.util.OptionalLong reduce(LongBinaryOperator longBinaryOperator) {
            return OptionalConversions.convert(LongStream.this.reduce(longBinaryOperator));
        }

        public /* synthetic */ java.util.stream.LongStream skip(long j) {
            return convert(LongStream.this.skip(j));
        }

        public /* synthetic */ java.util.stream.LongStream sorted() {
            return convert(LongStream.this.sorted());
        }

        public /* synthetic */ long sum() {
            return LongStream.this.sum();
        }

        public /* synthetic */ java.util.LongSummaryStatistics summaryStatistics() {
            return LongSummaryStatisticsConversions.convert(LongStream.this.summaryStatistics());
        }

        public /* synthetic */ long[] toArray() {
            return LongStream.this.toArray();
        }

        public /* synthetic */ java.util.stream.BaseStream unordered() {
            return BaseStream.Wrapper.convert(LongStream.this.unordered());
        }
    }

    boolean allMatch(LongPredicate longPredicate);

    boolean anyMatch(LongPredicate longPredicate);

    DoubleStream asDoubleStream();

    OptionalDouble average();

    Stream boxed();

    Object collect(Supplier supplier, ObjLongConsumer objLongConsumer, BiConsumer biConsumer);

    long count();

    LongStream distinct();

    LongStream filter(LongPredicate longPredicate);

    OptionalLong findAny();

    OptionalLong findFirst();

    LongStream flatMap(LongFunction longFunction);

    void forEach(LongConsumer longConsumer);

    void forEachOrdered(LongConsumer longConsumer);

    PrimitiveIterator.OfLong iterator();

    LongStream limit(long j);

    LongStream map(LongUnaryOperator longUnaryOperator);

    DoubleStream mapToDouble(LongToDoubleFunction longToDoubleFunction);

    IntStream mapToInt(LongToIntFunction longToIntFunction);

    Stream mapToObj(LongFunction longFunction);

    OptionalLong max();

    OptionalLong min();

    boolean noneMatch(LongPredicate longPredicate);

    LongStream parallel();

    LongStream peek(LongConsumer longConsumer);

    long reduce(long j, LongBinaryOperator longBinaryOperator);

    OptionalLong reduce(LongBinaryOperator longBinaryOperator);

    LongStream sequential();

    LongStream skip(long j);

    LongStream sorted();

    Spliterator.OfLong spliterator();

    long sum();

    LongSummaryStatistics summaryStatistics();

    long[] toArray();
}
