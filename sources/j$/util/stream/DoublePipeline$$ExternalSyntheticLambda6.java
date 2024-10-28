package j$.util.stream;

import j$.util.function.BiFunction$CC;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;

public final /* synthetic */ class DoublePipeline$$ExternalSyntheticLambda6 implements BinaryOperator {
    public final /* synthetic */ BiConsumer f$0;

    public /* synthetic */ DoublePipeline$$ExternalSyntheticLambda6(BiConsumer biConsumer) {
        this.f$0 = biConsumer;
    }

    public /* synthetic */ BiFunction andThen(Function function) {
        return BiFunction$CC.$default$andThen(this, function);
    }

    public final Object apply(Object obj, Object obj2) {
        return this.f$0.accept(obj, obj2);
    }
}
