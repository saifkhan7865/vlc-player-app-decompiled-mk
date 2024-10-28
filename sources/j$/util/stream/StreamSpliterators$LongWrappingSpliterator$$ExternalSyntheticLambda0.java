package j$.util.stream;

import j$.util.function.Consumer$CC;
import j$.util.function.LongConsumer$CC;
import j$.util.stream.Sink;
import java.util.function.Consumer;
import java.util.function.LongConsumer;

public final /* synthetic */ class StreamSpliterators$LongWrappingSpliterator$$ExternalSyntheticLambda0 implements Sink.OfLong {
    public final /* synthetic */ LongConsumer f$0;

    public /* synthetic */ StreamSpliterators$LongWrappingSpliterator$$ExternalSyntheticLambda0(LongConsumer longConsumer) {
        this.f$0 = longConsumer;
    }

    public /* synthetic */ void accept(double d) {
        Sink.CC.$default$accept((Sink) this, d);
    }

    public /* synthetic */ void accept(int i) {
        Sink.CC.$default$accept((Sink) this, i);
    }

    public final void accept(long j) {
        this.f$0.accept(j);
    }

    public /* synthetic */ void accept(Long l) {
        Sink.OfLong.CC.$default$accept((Sink.OfLong) this, l);
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

    public /* synthetic */ void begin(long j) {
        Sink.CC.$default$begin(this, j);
    }

    public /* synthetic */ boolean cancellationRequested() {
        return Sink.CC.$default$cancellationRequested(this);
    }

    public /* synthetic */ void end() {
        Sink.CC.$default$end(this);
    }
}
