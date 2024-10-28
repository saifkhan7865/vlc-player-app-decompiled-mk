package j$.util.stream;

import j$.util.function.Function$CC;
import java.util.function.Function;

public final /* synthetic */ class Collectors$$ExternalSyntheticLambda66 implements Function {
    public /* synthetic */ Function andThen(Function function) {
        return Function$CC.$default$andThen(this, function);
    }

    public final Object apply(Object obj) {
        return Collectors.lambda$castingIdentity$2(obj);
    }

    public /* synthetic */ Function compose(Function function) {
        return Function$CC.$default$compose(this, function);
    }
}
