package j$.util.stream;

import j$.util.Spliterator;
import j$.util.stream.BaseStream;
import j$.util.stream.DoubleStream;
import j$.util.stream.IntStream;
import j$.util.stream.LongStream;
import j$.util.stream.Stream;
import java.util.Iterator;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public interface BaseStream<T, S extends BaseStream<T, S>> extends AutoCloseable {

    public final /* synthetic */ class VivifiedWrapper implements BaseStream {
        public final /* synthetic */ java.util.stream.BaseStream wrappedValue;

        private /* synthetic */ VivifiedWrapper(java.util.stream.BaseStream baseStream) {
            this.wrappedValue = baseStream;
        }

        public static /* synthetic */ BaseStream convert(java.util.stream.BaseStream baseStream) {
            if (baseStream == null) {
                return null;
            }
            return baseStream instanceof Wrapper ? BaseStream.this : baseStream instanceof DoubleStream ? DoubleStream.VivifiedWrapper.convert((java.util.stream.DoubleStream) baseStream) : baseStream instanceof IntStream ? IntStream.VivifiedWrapper.convert((java.util.stream.IntStream) baseStream) : baseStream instanceof LongStream ? LongStream.VivifiedWrapper.convert((java.util.stream.LongStream) baseStream) : baseStream instanceof Stream ? Stream.VivifiedWrapper.convert((java.util.stream.Stream) baseStream) : new VivifiedWrapper(baseStream);
        }

        public /* synthetic */ void close() {
            this.wrappedValue.close();
        }

        public /* synthetic */ boolean equals(Object obj) {
            java.util.stream.BaseStream baseStream = this.wrappedValue;
            if (obj instanceof VivifiedWrapper) {
                obj = ((VivifiedWrapper) obj).wrappedValue;
            }
            return baseStream.equals(obj);
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

        public /* synthetic */ BaseStream onClose(Runnable runnable) {
            return convert(this.wrappedValue.onClose(runnable));
        }

        public /* synthetic */ BaseStream parallel() {
            return convert(this.wrappedValue.parallel());
        }

        public /* synthetic */ BaseStream sequential() {
            return convert(this.wrappedValue.sequential());
        }

        public /* synthetic */ Spliterator spliterator() {
            return Spliterator.VivifiedWrapper.convert(this.wrappedValue.spliterator());
        }

        public /* synthetic */ BaseStream unordered() {
            return convert(this.wrappedValue.unordered());
        }
    }

    public final /* synthetic */ class Wrapper implements java.util.stream.BaseStream {
        private /* synthetic */ Wrapper() {
        }

        public static /* synthetic */ java.util.stream.BaseStream convert(BaseStream baseStream) {
            if (baseStream == null) {
                return null;
            }
            return baseStream instanceof VivifiedWrapper ? ((VivifiedWrapper) baseStream).wrappedValue : baseStream instanceof DoubleStream ? DoubleStream.Wrapper.convert((DoubleStream) baseStream) : baseStream instanceof IntStream ? IntStream.Wrapper.convert((IntStream) baseStream) : baseStream instanceof LongStream ? LongStream.Wrapper.convert((LongStream) baseStream) : baseStream instanceof Stream ? Stream.Wrapper.convert((Stream) baseStream) : new Wrapper();
        }

        public /* synthetic */ void close() {
            BaseStream.this.close();
        }

        public /* synthetic */ boolean equals(Object obj) {
            BaseStream baseStream = BaseStream.this;
            if (obj instanceof Wrapper) {
                obj = BaseStream.this;
            }
            return baseStream.equals(obj);
        }

        public /* synthetic */ int hashCode() {
            return BaseStream.this.hashCode();
        }

        public /* synthetic */ boolean isParallel() {
            return BaseStream.this.isParallel();
        }

        public /* synthetic */ Iterator iterator() {
            return BaseStream.this.iterator();
        }

        public /* synthetic */ java.util.stream.BaseStream onClose(Runnable runnable) {
            return convert(BaseStream.this.onClose(runnable));
        }

        public /* synthetic */ java.util.stream.BaseStream parallel() {
            return convert(BaseStream.this.parallel());
        }

        public /* synthetic */ java.util.stream.BaseStream sequential() {
            return convert(BaseStream.this.sequential());
        }

        public /* synthetic */ java.util.Spliterator spliterator() {
            return Spliterator.Wrapper.convert(BaseStream.this.spliterator());
        }

        public /* synthetic */ java.util.stream.BaseStream unordered() {
            return convert(BaseStream.this.unordered());
        }
    }

    void close();

    boolean isParallel();

    Iterator<T> iterator();

    BaseStream onClose(Runnable runnable);

    BaseStream parallel();

    BaseStream sequential();

    Spliterator spliterator();

    BaseStream unordered();
}
