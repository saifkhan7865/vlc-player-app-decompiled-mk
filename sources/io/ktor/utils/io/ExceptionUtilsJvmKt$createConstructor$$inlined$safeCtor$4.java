package io.ktor.utils.io;

import java.lang.reflect.Constructor;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0003\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0003¨\u0006\u0004"}, d2 = {"<anonymous>", "", "e", "invoke", "io/ktor/utils/io/ExceptionUtilsJvmKt$safeCtor$1"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: ExceptionUtilsJvm.kt */
public final class ExceptionUtilsJvmKt$createConstructor$$inlined$safeCtor$4 extends Lambda implements Function1<Throwable, Throwable> {
    final /* synthetic */ Constructor $constructor$inlined;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ExceptionUtilsJvmKt$createConstructor$$inlined$safeCtor$4(Constructor constructor) {
        super(1);
        this.$constructor$inlined = constructor;
    }

    public final Throwable invoke(Throwable th) {
        Object obj;
        Intrinsics.checkNotNullParameter(th, "e");
        Throwable th2 = null;
        try {
            Result.Companion companion = Result.Companion;
            Object newInstance = this.$constructor$inlined.newInstance((Object[]) null);
            Intrinsics.checkNotNull(newInstance, "null cannot be cast to non-null type kotlin.Throwable");
            Throwable th3 = (Throwable) newInstance;
            th3.initCause(th);
            obj = Result.m1774constructorimpl(th3);
        } catch (Throwable th4) {
            Result.Companion companion2 = Result.Companion;
            obj = Result.m1774constructorimpl(ResultKt.createFailure(th4));
        }
        if (!Result.m1780isFailureimpl(obj)) {
            th2 = obj;
        }
        return th2;
    }
}
