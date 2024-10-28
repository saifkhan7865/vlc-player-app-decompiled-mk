package androidx.paging;

import androidx.arch.core.util.Function;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\b\b\u0001\u0010\u0004*\u00020\u0003\"\b\b\u0002\u0010\u0005*\u00020\u00032\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0001H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "ToValue", "", "Key", "Value", "list", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: DataSource.kt */
final class DataSource$map$1 extends Lambda implements Function1<List<? extends Value>, List<? extends ToValue>> {
    final /* synthetic */ Function<Value, ToValue> $function;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DataSource$map$1(Function<Value, ToValue> function) {
        super(1);
        this.$function = function;
    }

    public final List<ToValue> invoke(List<? extends Value> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        Iterable<Object> iterable = list;
        Function<Value, ToValue> function = this.$function;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (Object apply : iterable) {
            arrayList.add(function.apply(apply));
        }
        return (List) arrayList;
    }
}
