package j$.util.stream;

import j$.util.function.Consumer$CC;
import j$.util.function.IntConsumer$CC;
import j$.util.stream.Sink;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

public final /* synthetic */ class StreamSpliterators$IntWrappingSpliterator$$ExternalSyntheticLambda0 implements Sink.OfInt {
    public final /* synthetic */ IntConsumer f$0;

    public /* synthetic */ StreamSpliterators$IntWrappingSpliterator$$ExternalSyntheticLambda0(IntConsumer intConsumer) {
        this.f$0 = intConsumer;
    }

    public /* synthetic */ void accept(double d) {
        Sink.CC.$default$accept((Sink) this, d);
    }

    public final void accept(int i) {
        this.f$0.accept(i);
    }

    public /* synthetic */ void accept(long j) {
        Sink.CC.$default$accept((Sink) this, j);
    }

    public /* synthetic */ void accept(Integer num) {
        Sink.OfInt.CC.$default$accept((Sink.OfInt) this, num);
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
