package j$.util.function;

import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;

public final /* synthetic */ class BinaryOperator$$ExternalSyntheticLambda1 implements BinaryOperator {
    public final /* synthetic */ Comparator f$0;

    public /* synthetic */ BinaryOperator$$ExternalSyntheticLambda1(Comparator comparator) {
        this.f$0 = comparator;
    }

    public /* synthetic */ BiFunction andThen(Function function) {
        return BiFunction$CC.$default$andThen(this, function);
    }

    public final Object apply(Object obj, Object obj2) {
        return BinaryOperator$CC.lambda$minBy$0(this.f$0, obj, obj2);
    }
}
