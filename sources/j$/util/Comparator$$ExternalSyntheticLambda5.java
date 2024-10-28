package j$.util;

import java.io.Serializable;
import java.util.Comparator;
import java.util.function.Function;

public final /* synthetic */ class Comparator$$ExternalSyntheticLambda5 implements Comparator, Serializable {
    public final /* synthetic */ Comparator f$0;
    public final /* synthetic */ Function f$1;

    public /* synthetic */ Comparator$$ExternalSyntheticLambda5(Comparator comparator, Function function) {
        this.f$0 = comparator;
        this.f$1 = function;
    }

    public final int compare(Object obj, Object obj2) {
        return this.f$0.compare(this.f$1.apply(obj), this.f$1.apply(obj2));
    }
}
