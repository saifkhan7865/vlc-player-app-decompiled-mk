package j$.util.stream;

import j$.util.stream.MatchOps;
import java.util.function.DoublePredicate;
import java.util.function.Supplier;

public final /* synthetic */ class MatchOps$$ExternalSyntheticLambda2 implements Supplier {
    public final /* synthetic */ MatchOps.MatchKind f$0;

    public /* synthetic */ MatchOps$$ExternalSyntheticLambda2(MatchOps.MatchKind matchKind, DoublePredicate doublePredicate) {
        this.f$0 = matchKind;
    }

    public final Object get() {
        return MatchOps.lambda$makeDouble$3(this.f$0, (DoublePredicate) null);
    }
}
