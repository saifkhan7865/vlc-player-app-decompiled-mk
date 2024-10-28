package j$.util.stream;

import j$.util.IntSummaryStatistics;
import j$.util.function.BiConsumer$CC;
import java.util.function.BiConsumer;

public final /* synthetic */ class IntPipeline$$ExternalSyntheticLambda3 implements BiConsumer {
    public final void accept(Object obj, Object obj2) {
        ((IntSummaryStatistics) obj).combine((IntSummaryStatistics) obj2);
    }

    public /* synthetic */ BiConsumer andThen(BiConsumer biConsumer) {
        return BiConsumer$CC.$default$andThen(this, biConsumer);
    }
}
