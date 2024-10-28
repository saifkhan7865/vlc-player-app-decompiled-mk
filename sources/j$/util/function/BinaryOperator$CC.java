package j$.util.function;

import j$.util.Objects;
import java.util.Comparator;
import java.util.function.BinaryOperator;

/* renamed from: j$.util.function.BinaryOperator$-CC  reason: invalid class name */
public abstract /* synthetic */ class BinaryOperator$CC {
    public static /* synthetic */ Object lambda$maxBy$1(Comparator comparator, Object obj, Object obj2) {
        return comparator.compare(obj, obj2) >= 0 ? obj : obj2;
    }

    public static /* synthetic */ Object lambda$minBy$0(Comparator comparator, Object obj, Object obj2) {
        return comparator.compare(obj, obj2) <= 0 ? obj : obj2;
    }

    public static BinaryOperator maxBy(Comparator comparator) {
        Objects.requireNonNull(comparator);
        return new BinaryOperator$$ExternalSyntheticLambda0(comparator);
    }

    public static BinaryOperator minBy(Comparator comparator) {
        Objects.requireNonNull(comparator);
        return new BinaryOperator$$ExternalSyntheticLambda1(comparator);
    }
}
