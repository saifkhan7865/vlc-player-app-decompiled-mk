package j$.util.function;

import j$.util.Objects;
import java.util.function.IntConsumer;

/* renamed from: j$.util.function.IntConsumer$-CC  reason: invalid class name */
public abstract /* synthetic */ class IntConsumer$CC {
    public static IntConsumer $default$andThen(IntConsumer intConsumer, IntConsumer intConsumer2) {
        Objects.requireNonNull(intConsumer2);
        return new IntConsumer$$ExternalSyntheticLambda0(intConsumer, intConsumer2);
    }

    public static /* synthetic */ void $private$lambda$andThen$0(IntConsumer intConsumer, IntConsumer intConsumer2, int i) {
        intConsumer.accept(i);
        intConsumer2.accept(i);
    }
}
