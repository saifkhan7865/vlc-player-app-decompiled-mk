package j$.util.stream;

import j$.util.function.BiConsumer$CC;
import java.util.LinkedHashSet;
import java.util.function.BiConsumer;

public final /* synthetic */ class DistinctOps$1$$ExternalSyntheticLambda3 implements BiConsumer {
    public final void accept(Object obj, Object obj2) {
        ((LinkedHashSet) obj).addAll((LinkedHashSet) obj2);
    }

    public /* synthetic */ BiConsumer andThen(BiConsumer biConsumer) {
        return BiConsumer$CC.$default$andThen(this, biConsumer);
    }
}
