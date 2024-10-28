package j$.util.stream;

import j$.util.function.Consumer$CC;
import java.util.function.Consumer;

public final /* synthetic */ class StreamSpliterators$DistinctSpliterator$$ExternalSyntheticLambda0 implements Consumer {
    public final /* synthetic */ StreamSpliterators$DistinctSpliterator f$0;
    public final /* synthetic */ Consumer f$1;

    public /* synthetic */ StreamSpliterators$DistinctSpliterator$$ExternalSyntheticLambda0(StreamSpliterators$DistinctSpliterator streamSpliterators$DistinctSpliterator, Consumer consumer) {
        this.f$0 = streamSpliterators$DistinctSpliterator;
        this.f$1 = consumer;
    }

    public final void accept(Object obj) {
        this.f$0.m1331lambda$forEachRemaining$0$javautilstreamStreamSpliterators$DistinctSpliterator(this.f$1, obj);
    }

    public /* synthetic */ Consumer andThen(Consumer consumer) {
        return Consumer$CC.$default$andThen(this, consumer);
    }
}
