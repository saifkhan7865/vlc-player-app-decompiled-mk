package io.ktor.server.engine.internal;

import java.util.Collection;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.KFunction;
import kotlin.reflect.KParameter;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000f\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "R", "it", "Lkotlin/reflect/KFunction;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: AutoReloadUtils.kt */
final class AutoReloadUtilsKt$bestFunction$2 extends Lambda implements Function1<KFunction<? extends R>, Comparable<?>> {
    public static final AutoReloadUtilsKt$bestFunction$2 INSTANCE = new AutoReloadUtilsKt$bestFunction$2();

    AutoReloadUtilsKt$bestFunction$2() {
        super(1);
    }

    public final Comparable<?> invoke(KFunction<? extends R> kFunction) {
        Intrinsics.checkNotNullParameter(kFunction, "it");
        Iterable<KParameter> parameters = kFunction.getParameters();
        int i = 0;
        if (!(parameters instanceof Collection) || !((Collection) parameters).isEmpty()) {
            for (KParameter isOptional : parameters) {
                if ((!isOptional.isOptional()) && (i = i + 1) < 0) {
                    CollectionsKt.throwCountOverflow();
                }
            }
        }
        return Integer.valueOf(i);
    }
}
