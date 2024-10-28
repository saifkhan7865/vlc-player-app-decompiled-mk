package androidx.paging;

import androidx.arch.core.util.Function;
import androidx.paging.DataSource;
import java.util.List;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DataSource$Factory$$ExternalSyntheticLambda0 implements Function {
    public final /* synthetic */ Function f$0;

    public /* synthetic */ DataSource$Factory$$ExternalSyntheticLambda0(Function function) {
        this.f$0 = function;
    }

    public final Object apply(Object obj) {
        return DataSource.Factory.map$lambda$1(this.f$0, (List) obj);
    }
}
