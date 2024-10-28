package io.ktor.server.engine.internal;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.KFunction;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000f\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "R", "it", "Lkotlin/reflect/KFunction;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: AutoReloadUtils.kt */
final class AutoReloadUtilsKt$bestFunction$1 extends Lambda implements Function1<KFunction<? extends R>, Comparable<?>> {
    public static final AutoReloadUtilsKt$bestFunction$1 INSTANCE = new AutoReloadUtilsKt$bestFunction$1();

    AutoReloadUtilsKt$bestFunction$1() {
        super(1);
    }

    public final Comparable<?> invoke(KFunction<? extends R> kFunction) {
        Intrinsics.checkNotNullParameter(kFunction, "it");
        boolean z = true;
        if (!(!kFunction.getParameters().isEmpty()) || !AutoReloadUtilsKt.isApplication(kFunction.getParameters().get(0))) {
            z = false;
        }
        return Boolean.valueOf(z);
    }
}
