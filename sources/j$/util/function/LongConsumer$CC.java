package j$.util.function;

import j$.util.Objects;
import java.util.function.LongConsumer;

/* renamed from: j$.util.function.LongConsumer$-CC  reason: invalid class name */
public abstract /* synthetic */ class LongConsumer$CC {
    public static LongConsumer $default$andThen(LongConsumer longConsumer, LongConsumer longConsumer2) {
        Objects.requireNonNull(longConsumer2);
        return new LongConsumer$$ExternalSyntheticLambda0(longConsumer, longConsumer2);
    }

    public static /* synthetic */ void $private$lambda$andThen$0(LongConsumer longConsumer, LongConsumer longConsumer2, long j) {
        longConsumer.accept(j);
        longConsumer2.accept(j);
    }
}
