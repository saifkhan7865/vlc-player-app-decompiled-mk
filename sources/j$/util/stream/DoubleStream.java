package j$.util.stream;

import j$.util.DoubleSummaryStatistics;
import j$.util.DoubleSummaryStatisticsConversions;
import j$.util.OptionalConversions;
import j$.util.OptionalDouble;
import j$.util.PrimitiveIterator;
import j$.util.Spliterator;
import j$.util.stream.BaseStream;
import j$.util.stream.IntStream;
import j$.util.stream.LongStream;
import j$.util.stream.Stream;
import java.util.function.BiConsumer;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleFunction;
import java.util.function.DoublePredicate;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleToLongFunction;
import java.util.function.DoubleUnaryOperator;
import java.util.function.ObjDoubleConsumer;
import java.util.function.Supplier;

public interface DoubleStream extends BaseStream<Double, DoubleStream> {

    public final /* synthetic */ class VivifiedWrapper implements DoubleStream {
        public final /* synthetic */ java.util.stream.DoubleStream wrappedValue;

        private /* synthetic */ VivifiedWrapper(java.util.stream.DoubleStream doubleStream) {
            this.wrappedValue = doubleStream;
        }

        public static /* synthetic */ DoubleStream convert(java.util.stream.DoubleStream doubleStream) {
            if (doubleStream == null) {
                return null;
            }
            return doubleStream instanceof Wrapper ? DoubleStream.this : new VivifiedWrapper(doubleStream);
        }

        public /* synthetic */ boolean allMatch(DoublePredicate doublePredicate) {
            return this.wrappedValue.allMatch(doublePredicate);
        }

        public /* synthetic */ boolean anyMatch(DoublePredicate doublePredicate) {
            return this.wrappedValue.anyMatch(doublePredicate);
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

        public /* synthetic */ Object collect(Supplier supplier, ObjDoubleConsumer objDoubleConsumer, BiConsumer biConsumer) {
            return this.wrappedValue.collect(supplier, objDoubleConsumer, biConsumer);
        }

        public /* synthetic */ long count() {
            return this.wrappedValue.count();
        }

        public /* synthetic */ DoubleStream distinct() {
            return convert(this.wrappedValue.distinct());
        }

        public /* synthetic */ boolean equals(Object obj) {
            java.util.stream.DoubleStream doubleStream = this.wrappedValue;
            if (obj instanceof VivifiedWrapper) {
                obj = ((VivifiedWrapper) obj).wrappedValue;
            }
            return doubleStream.equals(obj);
        }

        public /* synthetic */ DoubleStream filter(DoublePredicate doublePredicate) {
            return convert(this.wrappedValue.filter(doublePredicate));
        }

        public /* synthetic */ OptionalDouble findAny() {
            return OptionalConversions.convert(this.wrappedValue.findAny());
        }

        public /* synthetic */ OptionalDouble findFirst() {
            return OptionalConversions.convert(this.wrappedValue.findFirst());
        }

        public /* synthetic */ DoubleStream flatMap(DoubleFunction doubleFunction) {
            return convert(this.wrappedValue.flatMap(FlatMapApiFlips.flipFunctionReturningStream(doubleFunction)));
        }

        public /* synthetic */ void forEach(DoubleConsumer doubleConsumer) {
            this.wrappedValue.forEach(doubleConsumer);
        }

        public /* synthetic */ void forEachOrdered(DoubleConsumer doubleConsumer) {
            this.wrappedValue.forEachOrdered(doubleConsumer);
        }

        public /* synthetic */ int hashCode() {
            return this.wrappedValue.hashCode();
        }

        public /* synthetic */ boolean isParallel() {
            return this.wrappedValue.isParallel();
        }

        public /* synthetic */ DoubleStream limit(long j) {
            return convert(this.wrappedValue.limit(j));
        }

        public /* synthetic */ DoubleStream map(DoubleUnaryOperator doubleUnaryOperator) {
            return convert(this.wrappedValue.map(doubleUnaryOperator));
        }

        public /* synthetic */ IntStream mapToInt(DoubleToIntFunction doubleToIntFunction) {
            return IntStream.VivifiedWrapper.convert(this.wrappedValue.mapToInt(doubleToIntFunction));
        }

        public /* synthetic */ LongStream mapToLong(DoubleToLongFunction doubleToLongFunction) {
            return LongStream.VivifiedWrapper.convert(this.wrappedValue.mapToLong(doubleToLongFunction));
        }

        public /* synthetic */ Stream mapToObj(DoubleFunction doubleFunction) {
            return Stream.VivifiedWrapper.convert(this.wrappedValue.mapToObj(doubleFunction));
        }

        public /* synthetic */ OptionalDouble max() {
            return OptionalConversions.convert(this.wrappedValue.max());
        }

        public /* synthetic */ OptionalDouble min() {
            return OptionalConversions.convert(this.wrappedValue.min());
        }

        public /* synthetic */ boolean noneMatch(DoublePredicate doublePredicate) {
            return this.wrappedValue.noneMatch(doublePredicate);
        }

        public /* synthetic */ BaseStream onClose(Runnable runnable) {
            return BaseStream.VivifiedWrapper.convert(this.wrappedValue.onClose(runnable));
        }

        public /* synthetic */ DoubleStream peek(DoubleConsumer doubleConsumer) {
            return convert(this.wrappedValue.peek(doubleConsumer));
        }

        public /* synthetic */ double reduce(double d, DoubleBinaryOperator doubleBinaryOperator) {
            return this.wrappedValue.reduce(d, doubleBinaryOperator);
        }

        public /* synthetic */ OptionalDouble reduce(DoubleBinaryOperator doubleBinaryOperator) {
            return OptionalConversions.convert(this.wrappedValue.reduce(doubleBinaryOperator));
        }

        public /* synthetic */ DoubleStream skip(long j) {
            return convert(this.wrappedValue.skip(j));
        }

        public /* synthetic */ DoubleStream sorted() {
            return convert(this.wrappedValue.sorted());
        }

        public /* synthetic */ double sum() {
            return this.wrappedValue.sum();
        }

        public /* synthetic */ DoubleSummaryStatistics summaryStatistics() {
            return DoubleSummaryStatisticsConversions.convert(this.wrappedValue.summaryStatistics());
        }

        public /* synthetic */ double[] toArray() {
            return this.wrappedValue.toArray();
        }

        public /* synthetic */ BaseStream unordered() {
            return BaseStream.VivifiedWrapper.convert(this.wrappedValue.unordered());
        }
    }

    public final /* synthetic */ class Wrapper implements java.util.stream.DoubleStream {
        private /* synthetic */ Wrapper() {
        }

        public static /* synthetic */ java.util.stream.DoubleStream convert(DoubleStream doubleStream) {
            if (doubleStream == null) {
                return null;
            }
            return doubleStream instanceof VivifiedWrapper ? ((VivifiedWrapper) doubleStream).wrappedValue : new Wrapper();
        }

        public /* synthetic */ boolean allMatch(DoublePredicate doublePredicate) {
            return DoubleStream.this.allMatch(doublePredicate);
        }

        public /* synthetic */ boolean anyMatch(DoublePredicate doublePredicate) {
            return DoubleStream.this.anyMatch(doublePredicate);
        }

        public /* synthetic */ java.util.OptionalDouble average() {
            return OptionalConversions.convert(DoubleStream.this.average());
        }

        public /* synthetic */ java.util.stream.Stream boxed() {
            return Stream.Wrapper.convert(DoubleStream.this.boxed());
        }

        public /* synthetic */ void close() {
            DoubleStream.this.close();
        }

        public /* synthetic */ Object collect(Supplier supplier, ObjDoubleConsumer objDoubleConsumer, BiConsumer biConsumer) {
            return DoubleStream.this.collect(supplier, objDoubleConsumer, biConsumer);
        }

        public /* synthetic */ long count() {
            return DoubleStream.this.count();
        }

        public /* synthetic */ java.util.stream.DoubleStream distinct() {
            return convert(DoubleStream.this.distinct());
        }

        public /* synthetic */ boolean equals(Object obj) {
            DoubleStream doubleStream = DoubleStream.this;
            if (obj instanceof Wrapper) {
                obj = DoubleStream.this;
            }
            return doubleStream.equals(obj);
        }

        public /* synthetic */ java.util.stream.DoubleStream filter(DoublePredicate doublePredicate) {
            return convert(DoubleStream.this.filter(doublePredicate));
        }

        public /* synthetic */ java.util.OptionalDouble findAny() {
            return OptionalConversions.convert(DoubleStream.this.findAny());
        }

        public /* synthetic */ java.util.OptionalDouble findFirst() {
            return OptionalConversions.convert(DoubleStream.this.findFirst());
        }

        public /* synthetic */ java.util.stream.DoubleStream flatMap(DoubleFunction doubleFunction) {
            return convert(DoubleStream.this.flatMap(FlatMapApiFlips.flipFunctionReturningStream(doubleFunction)));
        }

        public /* synthetic */ void forEach(DoubleConsumer doubleConsumer) {
            DoubleStream.this.forEach(doubleConsumer);
        }

        public /* synthetic */ void forEachOrdered(DoubleConsumer doubleConsumer) {
            DoubleStream.this.forEachOrdered(doubleConsumer);
        }

        public /* synthetic */ int hashCode() {
            return DoubleStream.this.hashCode();
        }

        public /* synthetic */ boolean isParallel() {
            return DoubleStream.this.isParallel();
        }

        public /* synthetic */ java.util.stream.DoubleStream limit(long j) {
            return convert(DoubleStream.this.limit(j));
        }

        public /* synthetic */ java.util.stream.DoubleStream map(DoubleUnaryOperator doubleUnaryOperator) {
            return convert(DoubleStream.this.map(doubleUnaryOperator));
        }

        public /* synthetic */ java.util.stream.IntStream mapToInt(DoubleToIntFunction doubleToIntFunction) {
            return IntStream.Wrapper.convert(DoubleStream.this.mapToInt(doubleToIntFunction));
        }

        public /* synthetic */ java.util.stream.LongStream mapToLong(DoubleToLongFunction doubleToLongFunction) {
            return LongStream.Wrapper.convert(DoubleStream.this.mapToLong(doubleToLongFunction));
        }

        public /* synthetic */ java.util.stream.Stream mapToObj(DoubleFunction doubleFunction) {
            return Stream.Wrapper.convert(DoubleStream.this.mapToObj(doubleFunction));
        }

        public /* synthetic */ java.util.OptionalDouble max() {
            return OptionalConversions.convert(DoubleStream.this.max());
        }

        public /* synthetic */ java.util.OptionalDouble min() {
            return OptionalConversions.convert(DoubleStream.this.min());
        }

        public /* synthetic */ boolean noneMatch(DoublePredicate doublePredicate) {
            return DoubleStream.this.noneMatch(doublePredicate);
        }

        public /* synthetic */ java.util.stream.BaseStream onClose(Runnable runnable) {
            return BaseStream.Wrapper.convert(DoubleStream.this.onClose(runnable));
        }

        public /* synthetic */ java.util.stream.DoubleStream peek(DoubleConsumer doubleConsumer) {
            return convert(DoubleStream.this.peek(doubleConsumer));
        }

        public /* synthetic */ double reduce(double d, DoubleBinaryOperator doubleBinaryOperator) {
            return DoubleStream.this.reduce(d, doubleBinaryOperator);
        }

        public /* synthetic */ java.util.OptionalDouble reduce(DoubleBinaryOperator doubleBinaryOperator) {
            return OptionalConversions.convert(DoubleStream.this.reduce(doubleBinaryOperator));
        }

        public /* synthetic */ java.util.stream.DoubleStream skip(long j) {
            return convert(DoubleStream.this.skip(j));
        }

        public /* synthetic */ java.util.stream.DoubleStream sorted() {
            return convert(DoubleStream.this.sorted());
        }

        public /* synthetic */ double sum() {
            return DoubleStream.this.sum();
        }

        public /* synthetic */ java.util.DoubleSummaryStatistics summaryStatistics() {
            return DoubleSummaryStatisticsConversions.convert(DoubleStream.this.summaryStatistics());
        }

        public /* synthetic */ double[] toArray() {
            return DoubleStream.this.toArray();
        }

        public /* synthetic */ java.util.stream.BaseStream unordered() {
            return BaseStream.Wrapper.convert(DoubleStream.this.unordered());
        }
    }

    boolean allMatch(DoublePredicate doublePredicate);

    boolean anyMatch(DoublePredicate doublePredicate);

    OptionalDouble average();

    Stream boxed();

    Object collect(Supplier supplier, ObjDoubleConsumer objDoubleConsumer, BiConsumer biConsumer);

    long count();

    DoubleStream distinct();

    DoubleStream filter(DoublePredicate doublePredicate);

    OptionalDouble findAny();

    OptionalDouble findFirst();

    DoubleStream flatMap(DoubleFunction doubleFunction);

    void forEach(DoubleConsumer doubleConsumer);

    void forEachOrdered(DoubleConsumer doubleConsumer);

    PrimitiveIterator.OfDouble iterator();

    DoubleStream limit(long j);

    DoubleStream map(DoubleUnaryOperator doubleUnaryOperator);

    IntStream mapToInt(DoubleToIntFunction doubleToIntFunction);

    LongStream mapToLong(DoubleToLongFunction doubleToLongFunction);

    Stream mapToObj(DoubleFunction doubleFunction);

    OptionalDouble max();

    OptionalDouble min();

    boolean noneMatch(DoublePredicate doublePredicate);

    DoubleStream parallel();

    DoubleStream peek(DoubleConsumer doubleConsumer);

    double reduce(double d, DoubleBinaryOperator doubleBinaryOperator);

    OptionalDouble reduce(DoubleBinaryOperator doubleBinaryOperator);

    DoubleStream sequential();

    DoubleStream skip(long j);

    DoubleStream sorted();

    Spliterator.OfDouble spliterator();

    double sum();

    DoubleSummaryStatistics summaryStatistics();

    double[] toArray();
}
