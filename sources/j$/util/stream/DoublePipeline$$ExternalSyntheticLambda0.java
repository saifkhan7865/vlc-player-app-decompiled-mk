package j$.util.stream;

import j$.util.function.DoubleConsumer$CC;
import java.util.function.DoubleConsumer;

public final /* synthetic */ class DoublePipeline$$ExternalSyntheticLambda0 implements DoubleConsumer {
    public final /* synthetic */ Sink f$0;

    public /* synthetic */ DoublePipeline$$ExternalSyntheticLambda0(Sink sink) {
        this.f$0 = sink;
    }

    public final void accept(double d) {
        this.f$0.accept(d);
    }

    public /* synthetic */ DoubleConsumer andThen(DoubleConsumer doubleConsumer) {
        return DoubleConsumer$CC.$default$andThen(this, doubleConsumer);
    }
}
