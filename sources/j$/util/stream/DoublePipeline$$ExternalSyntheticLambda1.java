package j$.util.stream;

import j$.util.DoubleSummaryStatistics;
import java.util.function.ObjDoubleConsumer;

public final /* synthetic */ class DoublePipeline$$ExternalSyntheticLambda1 implements ObjDoubleConsumer {
    public final void accept(Object obj, double d) {
        ((DoubleSummaryStatistics) obj).accept(d);
    }
}
