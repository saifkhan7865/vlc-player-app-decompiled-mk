package j$.util;

import j$.time.LocalTime$$ExternalSyntheticBackport0;
import java.io.Serializable;
import java.util.Comparator;
import java.util.function.ToIntFunction;

public final /* synthetic */ class Comparator$$ExternalSyntheticLambda0 implements Comparator, Serializable {
    public final /* synthetic */ ToIntFunction f$0;

    public /* synthetic */ Comparator$$ExternalSyntheticLambda0(ToIntFunction toIntFunction) {
        this.f$0 = toIntFunction;
    }

    public final int compare(Object obj, Object obj2) {
        return LocalTime$$ExternalSyntheticBackport0.m(this.f$0.applyAsInt(obj), this.f$0.applyAsInt(obj2));
    }
}
