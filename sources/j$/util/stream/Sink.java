package j$.util.stream;

import j$.util.Objects;
import j$.util.function.Consumer$CC;
import j$.util.function.DoubleConsumer$CC;
import j$.util.function.IntConsumer$CC;
import j$.util.function.LongConsumer$CC;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;

interface Sink extends Consumer {

    /* renamed from: j$.util.stream.Sink$-CC  reason: invalid class name */
    public abstract /* synthetic */ class CC {
        public static void $default$accept(Sink sink, double d) {
            throw new IllegalStateException("called wrong accept method");
        }

        public static void $default$accept(Sink sink, int i) {
            throw new IllegalStateException("called wrong accept method");
        }

        public static void $default$accept(Sink sink, long j) {
            throw new IllegalStateException("called wrong accept method");
        }

        public static void $default$begin(Sink sink, long j) {
        }

        public static boolean $default$cancellationRequested(Sink sink) {
            return false;
        }

        public static void $default$end(Sink sink) {
        }
    }

    public static abstract class ChainedDouble implements OfDouble {
        protected final Sink downstream;

        public ChainedDouble(Sink sink) {
            this.downstream = (Sink) Objects.requireNonNull(sink);
        }

        public /* synthetic */ void accept(int i) {
            CC.$default$accept((Sink) this, i);
        }

        public /* synthetic */ void accept(long j) {
            CC.$default$accept((Sink) this, j);
        }

        public /* synthetic */ void accept(Double d) {
            OfDouble.CC.$default$accept((OfDouble) this, d);
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
            this.downstream.begin(j);
        }

        public boolean cancellationRequested() {
            return this.downstream.cancellationRequested();
        }

        public void end() {
            this.downstream.end();
        }
    }

    public static abstract class ChainedInt implements OfInt {
        protected final Sink downstream;

        public ChainedInt(Sink sink) {
            this.downstream = (Sink) Objects.requireNonNull(sink);
        }

        public /* synthetic */ void accept(double d) {
            CC.$default$accept((Sink) this, d);
        }

        public /* synthetic */ void accept(long j) {
            CC.$default$accept((Sink) this, j);
        }

        public /* synthetic */ void accept(Integer num) {
            OfInt.CC.$default$accept((OfInt) this, num);
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
            this.downstream.begin(j);
        }

        public boolean cancellationRequested() {
            return this.downstream.cancellationRequested();
        }

        public void end() {
            this.downstream.end();
        }
    }

    public static abstract class ChainedLong implements OfLong {
        protected final Sink downstream;

        public ChainedLong(Sink sink) {
            this.downstream = (Sink) Objects.requireNonNull(sink);
        }

        public /* synthetic */ void accept(double d) {
            CC.$default$accept((Sink) this, d);
        }

        public /* synthetic */ void accept(int i) {
            CC.$default$accept((Sink) this, i);
        }

        public /* synthetic */ void accept(Long l) {
            OfLong.CC.$default$accept((OfLong) this, l);
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
            this.downstream.begin(j);
        }

        public boolean cancellationRequested() {
            return this.downstream.cancellationRequested();
        }

        public void end() {
            this.downstream.end();
        }
    }

    public static abstract class ChainedReference implements Sink {
        protected final Sink downstream;

        public ChainedReference(Sink sink) {
            this.downstream = (Sink) Objects.requireNonNull(sink);
        }

        public /* synthetic */ void accept(double d) {
            CC.$default$accept((Sink) this, d);
        }

        public /* synthetic */ void accept(int i) {
            CC.$default$accept((Sink) this, i);
        }

        public /* synthetic */ void accept(long j) {
            CC.$default$accept((Sink) this, j);
        }

        public /* synthetic */ Consumer andThen(Consumer consumer) {
            return Consumer$CC.$default$andThen(this, consumer);
        }

        public void begin(long j) {
            this.downstream.begin(j);
        }

        public boolean cancellationRequested() {
            return this.downstream.cancellationRequested();
        }

        public void end() {
            this.downstream.end();
        }
    }

    public interface OfDouble extends Sink, DoubleConsumer {

        /* renamed from: j$.util.stream.Sink$OfDouble$-CC  reason: invalid class name */
        public abstract /* synthetic */ class CC {
            public static void $default$accept(OfDouble ofDouble, Double d) {
                if (Tripwire.ENABLED) {
                    Tripwire.trip(ofDouble.getClass(), "{0} calling Sink.OfDouble.accept(Double)");
                }
                ofDouble.accept(d.doubleValue());
            }
        }

        void accept(double d);

        void accept(Double d);
    }

    public interface OfInt extends Sink, IntConsumer {

        /* renamed from: j$.util.stream.Sink$OfInt$-CC  reason: invalid class name */
        public abstract /* synthetic */ class CC {
            public static void $default$accept(OfInt ofInt, Integer num) {
                if (Tripwire.ENABLED) {
                    Tripwire.trip(ofInt.getClass(), "{0} calling Sink.OfInt.accept(Integer)");
                }
                ofInt.accept(num.intValue());
            }
        }

        void accept(int i);

        void accept(Integer num);
    }

    public interface OfLong extends Sink, LongConsumer {

        /* renamed from: j$.util.stream.Sink$OfLong$-CC  reason: invalid class name */
        public abstract /* synthetic */ class CC {
            public static void $default$accept(OfLong ofLong, Long l) {
                if (Tripwire.ENABLED) {
                    Tripwire.trip(ofLong.getClass(), "{0} calling Sink.OfLong.accept(Long)");
                }
                ofLong.accept(l.longValue());
            }
        }

        void accept(long j);

        void accept(Long l);
    }

    void accept(double d);

    void accept(int i);

    void accept(long j);

    void begin(long j);

    boolean cancellationRequested();

    void end();
}
