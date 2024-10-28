package j$.util.stream;

import j$.util.function.Consumer$CC;
import j$.util.stream.Sink;
import java.util.function.Consumer;

public final /* synthetic */ class StreamSpliterators$WrappingSpliterator$$ExternalSyntheticLambda2 implements Sink {
    public final /* synthetic */ Consumer f$0;

    public /* synthetic */ StreamSpliterators$WrappingSpliterator$$ExternalSyntheticLambda2(Consumer consumer) {
        this.f$0 = consumer;
    }

    public /* synthetic */ void accept(double d) {
        Sink.CC.$default$accept((Sink) this, d);
    }

    public /* synthetic */ void accept(int i) {
        Sink.CC.$default$accept((Sink) this, i);
    }

    public /* synthetic */ void accept(long j) {
        Sink.CC.$default$accept((Sink) this, j);
    }

    public final void accept(Object obj) {
        this.f$0.accept(obj);
    }

    public /* synthetic */ Consumer andThen(Consumer consumer) {
        return Consumer$CC.$default$andThen(this, consumer);
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
