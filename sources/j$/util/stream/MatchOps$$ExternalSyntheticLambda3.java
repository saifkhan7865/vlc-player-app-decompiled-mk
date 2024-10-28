package j$.util.stream;

import j$.util.stream.MatchOps;
import java.util.function.Predicate;
import java.util.function.Supplier;

public final /* synthetic */ class MatchOps$$ExternalSyntheticLambda3 implements Supplier {
    public final /* synthetic */ MatchOps.MatchKind f$0;
    public final /* synthetic */ Predicate f$1;

    public /* synthetic */ MatchOps$$ExternalSyntheticLambda3(MatchOps.MatchKind matchKind, Predicate predicate) {
        this.f$0 = matchKind;
        this.f$1 = predicate;
    }

    public final Object get() {
        return MatchOps.lambda$makeRef$0(this.f$0, this.f$1);
    }
}
