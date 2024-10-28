package io.ktor.server.netty;

import io.ktor.util.cio.ChannelWriteException;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "t", "", "c", "Lkotlin/coroutines/Continuation;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: CIO.kt */
final class CIOKt$wrappingErrorHandler$1 extends Lambda implements Function2<Throwable, Continuation<?>, Unit> {
    public static final CIOKt$wrappingErrorHandler$1 INSTANCE = new CIOKt$wrappingErrorHandler$1();

    CIOKt$wrappingErrorHandler$1() {
        super(2);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Throwable) obj, (Continuation<?>) (Continuation) obj2);
        return Unit.INSTANCE;
    }

    public final void invoke(Throwable th, Continuation<?> continuation) {
        Intrinsics.checkNotNullParameter(th, "t");
        Intrinsics.checkNotNullParameter(continuation, "c");
        if (th instanceof IOException) {
            Result.Companion companion = Result.Companion;
            continuation.resumeWith(Result.m1774constructorimpl(ResultKt.createFailure(new ChannelWriteException("Write operation future failed", th))));
            return;
        }
        Result.Companion companion2 = Result.Companion;
        continuation.resumeWith(Result.m1774constructorimpl(ResultKt.createFailure(th)));
    }
}
