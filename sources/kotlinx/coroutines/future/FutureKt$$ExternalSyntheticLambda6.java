package kotlinx.coroutines.future;

import j$.util.function.BiFunction$CC;
import java.util.function.BiFunction;
import java.util.function.Function;
import kotlinx.coroutines.Job;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class FutureKt$$ExternalSyntheticLambda6 implements BiFunction {
    public final /* synthetic */ Job f$0;

    public /* synthetic */ FutureKt$$ExternalSyntheticLambda6(Job job) {
        this.f$0 = job;
    }

    public /* synthetic */ BiFunction andThen(Function function) {
        return BiFunction$CC.$default$andThen(this, function);
    }

    public final Object apply(Object obj, Object obj2) {
        return FutureKt.setupCancellation$lambda$2(this.f$0, obj, (Throwable) obj2);
    }
}
