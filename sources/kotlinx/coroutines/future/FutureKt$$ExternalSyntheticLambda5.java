package kotlinx.coroutines.future;

import j$.util.function.BiFunction$CC;
import java.util.function.BiFunction;
import java.util.function.Function;
import kotlin.jvm.functions.Function2;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class FutureKt$$ExternalSyntheticLambda5 implements BiFunction {
    public final /* synthetic */ Function2 f$0;

    public /* synthetic */ FutureKt$$ExternalSyntheticLambda5(Function2 function2) {
        this.f$0 = function2;
    }

    public /* synthetic */ BiFunction andThen(Function function) {
        return BiFunction$CC.$default$andThen(this, function);
    }

    public final Object apply(Object obj, Object obj2) {
        return FutureKt.asDeferred$lambda$4(this.f$0, obj, (Throwable) obj2);
    }
}
