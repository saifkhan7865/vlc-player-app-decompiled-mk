package j$.util.stream;

import j$.util.stream.MatchOps;
import java.util.function.LongPredicate;
import java.util.function.Supplier;

public final /* synthetic */ class MatchOps$$ExternalSyntheticLambda0 implements Supplier {
    public final /* synthetic */ MatchOps.MatchKind f$0;

    public /* synthetic */ MatchOps$$ExternalSyntheticLambda0(MatchOps.MatchKind matchKind, LongPredicate longPredicate) {
        this.f$0 = matchKind;
    }

    public final Object get() {
        return MatchOps.lambda$makeLong$2(this.f$0, (LongPredicate) null);
    }
}
