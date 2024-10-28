package j$.util.stream;

import j$.util.function.BiConsumer$CC;
import java.util.List;
import java.util.function.BiConsumer;

public final /* synthetic */ class Collectors$$ExternalSyntheticLambda23 implements BiConsumer {
    public final void accept(Object obj, Object obj2) {
        ((List) obj).add(obj2);
    }

    public /* synthetic */ BiConsumer andThen(BiConsumer biConsumer) {
        return BiConsumer$CC.$default$andThen(this, biConsumer);
    }
}
