package j$.util.stream;

import j$.util.DoubleSummaryStatistics;
import j$.util.function.BiConsumer$CC;
import java.util.function.BiConsumer;

public final /* synthetic */ class DoublePipeline$$ExternalSyntheticLambda2 implements BiConsumer {
    public final void accept(Object obj, Object obj2) {
        ((DoubleSummaryStatistics) obj).combine((DoubleSummaryStatistics) obj2);
    }

    public /* synthetic */ BiConsumer andThen(BiConsumer biConsumer) {
        return BiConsumer$CC.$default$andThen(this, biConsumer);
    }
}
