package j$.util.function;

import java.util.function.Predicate;

public final /* synthetic */ class Predicate$$ExternalSyntheticLambda0 implements Predicate {
    public final /* synthetic */ Predicate f$0;
    public final /* synthetic */ Predicate f$1;

    public /* synthetic */ Predicate$$ExternalSyntheticLambda0(Predicate predicate, Predicate predicate2) {
        this.f$0 = predicate;
        this.f$1 = predicate2;
    }

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
        return Predicate$CC.$private$lambda$and$0(this.f$0, this.f$1, obj);
    }
}