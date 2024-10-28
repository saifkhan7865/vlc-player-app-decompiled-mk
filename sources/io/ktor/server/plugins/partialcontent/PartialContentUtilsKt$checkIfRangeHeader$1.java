package io.ktor.server.plugins.partialcontent;

import io.ktor.http.content.OutgoingContent;
import io.ktor.server.application.ApplicationCall;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.plugins.partialcontent.PartialContentUtilsKt", f = "PartialContentUtils.kt", i = {0}, l = {37}, m = "checkIfRangeHeader", n = {"ifRange"}, s = {"L$0"})
/* compiled from: PartialContentUtils.kt */
final class PartialContentUtilsKt$checkIfRangeHeader$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;

    PartialContentUtilsKt$checkIfRangeHeader$1(Continuation<? super PartialContentUtilsKt$checkIfRangeHeader$1> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return PartialContentUtilsKt.checkIfRangeHeader((OutgoingContent.ReadChannelContent) null, (ApplicationCall) null, this);
    }
}
