package j$.util.stream;

import j$.util.OptionalInt;
import j$.util.function.Predicate$CC;
import java.util.function.Predicate;

public final /* synthetic */ class FindOps$FindSink$OfInt$$ExternalSyntheticLambda0 implements Predicate {
    public /* synthetic */ Predicate and(Predicate predicate) {
        return Predicate$CC.$default$and(this, predicate);
    }

    public /* synthetic */ Predicate negate() {
        return Predicate$CC.$default$negate(this);
    }

    public /* synthetic */ Predicate or(Predicate predicate) {
        return Predicate$CC.$default$or(this, predicate);
    }

    public final boolean test(Object obj) {
        return ((OptionalInt) obj).isPresent();
    }
}
