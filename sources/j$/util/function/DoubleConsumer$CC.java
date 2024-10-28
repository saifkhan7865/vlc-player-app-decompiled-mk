package j$.util.function;

import j$.util.Objects;
import java.util.function.DoubleConsumer;

/* renamed from: j$.util.function.DoubleConsumer$-CC  reason: invalid class name */
public abstract /* synthetic */ class DoubleConsumer$CC {
    public static DoubleConsumer $default$andThen(DoubleConsumer doubleConsumer, DoubleConsumer doubleConsumer2) {
        Objects.requireNonNull(doubleConsumer2);
        return new DoubleConsumer$$ExternalSyntheticLambda0(doubleConsumer, doubleConsumer2);
    }

    public static /* synthetic */ void $private$lambda$andThen$0(DoubleConsumer doubleConsumer, DoubleConsumer doubleConsumer2, double d) {
        doubleConsumer.accept(d);
        doubleConsumer2.accept(d);
    }
}
