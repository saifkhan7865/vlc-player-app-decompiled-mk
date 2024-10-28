package j$.util.function;

import j$.util.Objects;
import java.util.function.BiConsumer;

/* renamed from: j$.util.function.BiConsumer$-CC  reason: invalid class name */
public final /* synthetic */ class BiConsumer$CC {
    public static BiConsumer $default$andThen(BiConsumer biConsumer, BiConsumer biConsumer2) {
        Objects.requireNonNull(biConsumer2);
        return new BiConsumer$$ExternalSyntheticLambda0(biConsumer, biConsumer2);
    }

    public static /* synthetic */ void $private$lambda$andThen$0(BiConsumer biConsumer, BiConsumer biConsumer2, Object obj, Object obj2) {
        biConsumer.accept(obj, obj2);
        biConsumer2.accept(obj, obj2);
    }
}
