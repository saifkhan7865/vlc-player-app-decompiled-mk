package androidx.paging;

import androidx.arch.core.util.Function;
import java.util.List;
import kotlin.jvm.functions.Function1;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class PositionalDataSource$$ExternalSyntheticLambda2 implements Function {
    public final /* synthetic */ Function1 f$0;

    public /* synthetic */ PositionalDataSource$$ExternalSyntheticLambda2(Function1 function1) {
        this.f$0 = function1;
    }

    public final Object apply(Object obj) {
        return PositionalDataSource.map$lambda$5(this.f$0, (List) obj);
    }
}
