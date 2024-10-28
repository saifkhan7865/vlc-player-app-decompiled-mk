package j$.util.function;

import j$.util.Objects;
import java.util.function.Consumer;

/* renamed from: j$.util.function.Consumer$-CC  reason: invalid class name */
public final /* synthetic */ class Consumer$CC {
    public static Consumer $default$andThen(Consumer consumer, Consumer consumer2) {
        Objects.requireNonNull(consumer2);
        return new Consumer$$ExternalSyntheticLambda0(consumer, consumer2);
    }

    public static /* synthetic */ void $private$lambda$andThen$0(Consumer consumer, Consumer consumer2, Object obj) {
        consumer.accept(obj);
        consumer2.accept(obj);
    }
}
