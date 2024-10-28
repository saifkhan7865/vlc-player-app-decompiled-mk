package j$.util;

import java.io.Serializable;
import java.util.Comparator;
import java.util.function.ToDoubleFunction;

public final /* synthetic */ class Comparator$$ExternalSyntheticLambda2 implements Comparator, Serializable {
    public final /* synthetic */ ToDoubleFunction f$0;

    public /* synthetic */ Comparator$$ExternalSyntheticLambda2(ToDoubleFunction toDoubleFunction) {
        this.f$0 = toDoubleFunction;
    }

    public final int compare(Object obj, Object obj2) {
        return Double.compare(this.f$0.applyAsDouble(obj), this.f$0.applyAsDouble(obj2));
    }
}
