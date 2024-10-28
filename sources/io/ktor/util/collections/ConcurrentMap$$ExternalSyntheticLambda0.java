package io.ktor.util.collections;

import j$.util.function.Function$CC;
import java.util.function.Function;
import kotlin.jvm.functions.Function1;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ConcurrentMap$$ExternalSyntheticLambda0 implements Function {
    public final /* synthetic */ Function1 f$0;

    public /* synthetic */ ConcurrentMap$$ExternalSyntheticLambda0(Function1 function1) {
        this.f$0 = function1;
    }

    public /* synthetic */ Function andThen(Function function) {
        return Function$CC.$default$andThen(this, function);
    }

    public final Object apply(Object obj) {
        return ConcurrentMap.computeIfAbsent$lambda$0(this.f$0, obj);
    }

    public /* synthetic */ Function compose(Function function) {
        return Function$CC.$default$compose(this, function);
    }
}
