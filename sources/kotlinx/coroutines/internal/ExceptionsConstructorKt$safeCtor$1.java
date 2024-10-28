package kotlinx.coroutines.internal;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "e", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: ExceptionsConstructor.kt */
final class ExceptionsConstructorKt$safeCtor$1 extends Lambda implements Function1<Throwable, Throwable> {
    final /* synthetic */ Function1<Throwable, Throwable> $block;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ExceptionsConstructorKt$safeCtor$1(Function1<? super Throwable, ? extends Throwable> function1) {
        super(1);
        this.$block = function1;
    }

    public final Throwable invoke(Throwable th) {
        Object obj;
        Function1<Throwable, Throwable> function1 = this.$block;
        Throwable th2 = null;
        try {
            Result.Companion companion = Result.Companion;
            Throwable invoke = function1.invoke(th);
            if (!Intrinsics.areEqual((Object) th.getMessage(), (Object) invoke.getMessage()) && !Intrinsics.areEqual((Object) invoke.getMessage(), (Object) th.toString())) {
                invoke = null;
            }
            obj = Result.m1774constructorimpl(invoke);
        } catch (Throwable th3) {
            Result.Companion companion2 = Result.Companion;
            obj = Result.m1774constructorimpl(ResultKt.createFailure(th3));
        }
        if (!Result.m1780isFailureimpl(obj)) {
            th2 = obj;
        }
        return th2;
    }
}
