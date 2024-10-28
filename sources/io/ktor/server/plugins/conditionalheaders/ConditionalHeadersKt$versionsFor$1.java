package io.ktor.server.plugins.conditionalheaders;

import io.ktor.http.content.OutgoingContent;
import io.ktor.server.application.ApplicationCall;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.plugins.conditionalheaders.ConditionalHeadersKt", f = "ConditionalHeaders.kt", i = {0, 0, 0}, l = {48}, m = "versionsFor", n = {"$this$versionsFor", "content", "destination$iv$iv"}, s = {"L$0", "L$1", "L$2"})
/* compiled from: ConditionalHeaders.kt */
final class ConditionalHeadersKt$versionsFor$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    /* synthetic */ Object result;

    ConditionalHeadersKt$versionsFor$1(Continuation<? super ConditionalHeadersKt$versionsFor$1> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return ConditionalHeadersKt.versionsFor((ApplicationCall) null, (OutgoingContent) null, this);
    }
}
