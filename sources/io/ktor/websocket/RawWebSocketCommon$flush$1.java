package io.ktor.websocket;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.websocket.RawWebSocketCommon", f = "RawWebSocketCommon.kt", i = {0, 0}, l = {123, 126, 131}, m = "flush", n = {"this", "it"}, s = {"L$0", "L$2"})
/* compiled from: RawWebSocketCommon.kt */
final class RawWebSocketCommon$flush$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ RawWebSocketCommon this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RawWebSocketCommon$flush$1(RawWebSocketCommon rawWebSocketCommon, Continuation<? super RawWebSocketCommon$flush$1> continuation) {
        super(continuation);
        this.this$0 = rawWebSocketCommon;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.flush(this);
    }
}
