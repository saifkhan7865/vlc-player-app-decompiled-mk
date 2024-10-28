package io.ktor.server.engine;

import io.ktor.server.application.ApplicationCall;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.engine.ShutDownUrl", f = "ShutDownUrl.kt", i = {0}, l = {115}, m = "doShutdown", n = {"latch"}, s = {"L$0"})
/* compiled from: ShutDownUrl.kt */
final class ShutDownUrl$doShutdown$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ ShutDownUrl this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ShutDownUrl$doShutdown$1(ShutDownUrl shutDownUrl, Continuation<? super ShutDownUrl$doShutdown$1> continuation) {
        super(continuation);
        this.this$0 = shutDownUrl;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.doShutdown((ApplicationCall) null, this);
    }
}
