package j$.util.stream;

import j$.util.function.Consumer$CC;
import j$.util.stream.StreamSpliterators$SliceSpliterator;
import java.util.function.Consumer;

public final /* synthetic */ class StreamSpliterators$SliceSpliterator$OfRef$$ExternalSyntheticLambda1 implements Consumer {
    public final void accept(Object obj) {
        StreamSpliterators$SliceSpliterator.OfRef.lambda$forEachRemaining$1(obj);
    }

    public /* synthetic */ Consumer andThen(Consumer consumer) {
        return Consumer$CC.$default$andThen(this, consumer);
    }
}
