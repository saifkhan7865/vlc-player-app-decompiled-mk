package j$.util.stream;

import j$.util.function.DoubleConsumer$CC;
import j$.util.stream.Node;
import java.util.function.DoubleConsumer;

public final /* synthetic */ class Node$OfDouble$$ExternalSyntheticLambda0 implements DoubleConsumer {
    public final void accept(double d) {
        Node.OfDouble.CC.lambda$truncate$0(d);
    }

    public /* synthetic */ DoubleConsumer andThen(DoubleConsumer doubleConsumer) {
        return DoubleConsumer$CC.$default$andThen(this, doubleConsumer);
    }
}
