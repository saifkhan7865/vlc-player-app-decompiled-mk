package j$.util.stream;

import j$.util.function.BiFunction$CC;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;

public final /* synthetic */ class Collectors$$ExternalSyntheticLambda65 implements BinaryOperator {
    public /* synthetic */ BiFunction andThen(Function function) {
        return BiFunction$CC.$default$andThen(this, function);
    }

    public final Object apply(Object obj, Object obj2) {
        return ((List) obj).addAll((List) obj2);
    }
}
