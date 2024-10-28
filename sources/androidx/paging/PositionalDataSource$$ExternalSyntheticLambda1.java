package androidx.paging;

import androidx.arch.core.util.Function;
import java.util.List;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class PositionalDataSource$$ExternalSyntheticLambda1 implements Function {
    public final /* synthetic */ Function f$0;

    public /* synthetic */ PositionalDataSource$$ExternalSyntheticLambda1(Function function) {
        this.f$0 = function;
    }

    public final Object apply(Object obj) {
        return PositionalDataSource.map$lambda$4(this.f$0, (List) obj);
    }
}
