package j$.util.stream;

import j$.util.LongSummaryStatistics;
import j$.util.function.BiConsumer$CC;
import java.util.function.BiConsumer;

public final /* synthetic */ class LongPipeline$$ExternalSyntheticLambda1 implements BiConsumer {
    public final void accept(Object obj, Object obj2) {
        ((LongSummaryStatistics) obj).combine((LongSummaryStatistics) obj2);
    }

    public /* synthetic */ BiConsumer andThen(BiConsumer biConsumer) {
        return BiConsumer$CC.$default$andThen(this, biConsumer);
    }
}
