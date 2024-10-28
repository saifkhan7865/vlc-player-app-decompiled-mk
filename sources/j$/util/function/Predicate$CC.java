package j$.util.function;

import j$.util.Objects;
import java.util.function.Predicate;

/* renamed from: j$.util.function.Predicate$-CC  reason: invalid class name */
public final /* synthetic */ class Predicate$CC {
    public static Predicate $default$and(Predicate predicate, Predicate predicate2) {
        Objects.requireNonNull(predicate2);
        return new Predicate$$ExternalSyntheticLambda0(predicate, predicate2);
    }

    public static Predicate $default$negate(Predicate predicate) {
        return new Predicate$$ExternalSyntheticLambda1(predicate);
    }

    public static Predicate $default$or(Predicate predicate, Predicate predicate2) {
        Objects.requireNonNull(predicate2);
        return new Predicate$$ExternalSyntheticLambda2(predicate, predicate2);
    }

    public static /* synthetic */ boolean $private$lambda$and$0(Predicate predicate, Predicate predicate2, Object obj) {
        return predicate.test(obj) && predicate2.test(obj);
    }

    public static /* synthetic */ boolean $private$lambda$negate$1(Predicate predicate, Object obj) {
        return !predicate.test(obj);
    }

    public static /* synthetic */ boolean $private$lambda$or$2(Predicate predicate, Predicate predicate2, Object obj) {
        return predicate.test(obj) || predicate2.test(obj);
    }
}
