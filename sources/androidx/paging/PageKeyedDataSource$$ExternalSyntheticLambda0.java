package androidx.paging;

import androidx.arch.core.util.Function;
import java.util.List;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class PageKeyedDataSource$$ExternalSyntheticLambda0 implements Function {
    public final /* synthetic */ Function f$0;

    public /* synthetic */ PageKeyedDataSource$$ExternalSyntheticLambda0(Function function) {
        this.f$0 = function;
    }

    public final Object apply(Object obj) {
        return PageKeyedDataSource.map$lambda$5(this.f$0, (List) obj);
    }
}
