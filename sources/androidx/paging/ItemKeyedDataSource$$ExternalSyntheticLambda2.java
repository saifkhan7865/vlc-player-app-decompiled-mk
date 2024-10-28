package androidx.paging;

import androidx.arch.core.util.Function;
import java.util.List;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ItemKeyedDataSource$$ExternalSyntheticLambda2 implements Function {
    public final /* synthetic */ Function f$0;

    public /* synthetic */ ItemKeyedDataSource$$ExternalSyntheticLambda2(Function function) {
        this.f$0 = function;
    }

    public final Object apply(Object obj) {
        return ItemKeyedDataSource.map$lambda$7(this.f$0, (List) obj);
    }
}
