package j$.util;

import j$.util.Comparator;
import java.io.Serializable;
import java.util.Comparator;
import java.util.function.ToLongFunction;

public final /* synthetic */ class Comparator$$ExternalSyntheticLambda4 implements Comparator, Serializable {
    public final /* synthetic */ ToLongFunction f$0;

    public /* synthetic */ Comparator$$ExternalSyntheticLambda4(ToLongFunction toLongFunction) {
        this.f$0 = toLongFunction;
    }

    public final int compare(Object obj, Object obj2) {
        return Comparator.CC.lambda$comparingLong$6043328a$1(this.f$0, obj, obj2);
    }
}
