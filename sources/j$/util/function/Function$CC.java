package j$.util.function;

import j$.util.Objects;
import java.util.function.Function;

/* renamed from: j$.util.function.Function$-CC  reason: invalid class name */
public final /* synthetic */ class Function$CC {
    public static Function $default$andThen(Function function, Function function2) {
        Objects.requireNonNull(function2);
        return new Function$$ExternalSyntheticLambda1(function, function2);
    }

    public static Function $default$compose(Function function, Function function2) {
        Objects.requireNonNull(function2);
        return new Function$$ExternalSyntheticLambda2(function, function2);
    }
}
