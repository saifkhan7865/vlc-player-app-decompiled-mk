package androidx.paging;

import androidx.arch.core.util.Function;
import java.util.List;
import kotlin.jvm.functions.Function1;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ItemKeyedDataSource$$ExternalSyntheticLambda0 implements Function {
    public final /* synthetic */ Function1 f$0;

    public /* synthetic */ ItemKeyedDataSource$$ExternalSyntheticLambda0(Function1 function1) {
        this.f$0 = function1;
    }

    public final Object apply(Object obj) {
        return ItemKeyedDataSource.map$lambda$8(this.f$0, (List) obj);
    }
}
