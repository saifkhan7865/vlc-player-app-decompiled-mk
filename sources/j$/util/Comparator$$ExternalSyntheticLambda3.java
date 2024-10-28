package j$.util;

import java.io.Serializable;
import java.util.Comparator;
import java.util.function.Function;

public final /* synthetic */ class Comparator$$ExternalSyntheticLambda3 implements Comparator, Serializable {
    public final /* synthetic */ Function f$0;

    public /* synthetic */ Comparator$$ExternalSyntheticLambda3(Function function) {
        this.f$0 = function;
    }

    public final int compare(Object obj, Object obj2) {
        return ((Comparable) this.f$0.apply(obj)).compareTo(this.f$0.apply(obj2));
    }
}
