package j$.util.stream;

import j$.util.stream.MatchOps;
import java.util.function.IntPredicate;
import java.util.function.Supplier;

public final /* synthetic */ class MatchOps$$ExternalSyntheticLambda1 implements Supplier {
    public final /* synthetic */ MatchOps.MatchKind f$0;

    public /* synthetic */ MatchOps$$ExternalSyntheticLambda1(MatchOps.MatchKind matchKind, IntPredicate intPredicate) {
        this.f$0 = matchKind;
    }

    public final Object get() {
        return MatchOps.lambda$makeInt$1(this.f$0, (IntPredicate) null);
    }
}
