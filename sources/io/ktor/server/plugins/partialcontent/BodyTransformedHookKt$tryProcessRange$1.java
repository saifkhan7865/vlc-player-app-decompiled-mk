package io.ktor.server.plugins.partialcontent;

import io.ktor.http.RangesSpecifier;
import io.ktor.http.content.OutgoingContent;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.plugins.partialcontent.BodyTransformedHook;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.plugins.partialcontent.BodyTransformedHookKt", f = "BodyTransformedHook.kt", i = {0, 0, 0, 0, 0}, l = {42, 43}, m = "tryProcessRange", n = {"$this$tryProcessRange", "content", "rangesSpecifier", "length", "maxRangeCount"}, s = {"L$0", "L$1", "L$2", "J$0", "I$0"})
/* compiled from: BodyTransformedHook.kt */
final class BodyTransformedHookKt$tryProcessRange$1 extends ContinuationImpl {
    int I$0;
    long J$0;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;

    BodyTransformedHookKt$tryProcessRange$1(Continuation<? super BodyTransformedHookKt$tryProcessRange$1> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return BodyTransformedHookKt.tryProcessRange((BodyTransformedHook.Context) null, (OutgoingContent.ReadChannelContent) null, (ApplicationCall) null, (RangesSpecifier) null, 0, 0, this);
    }
}
