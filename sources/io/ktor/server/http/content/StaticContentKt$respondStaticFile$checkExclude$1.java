package io.ktor.server.http.content;

import io.ktor.server.application.ApplicationCall;
import java.io.File;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function1;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.http.content.StaticContentKt", f = "StaticContent.kt", i = {}, l = {548}, m = "respondStaticFile$checkExclude", n = {}, s = {})
/* compiled from: StaticContent.kt */
final class StaticContentKt$respondStaticFile$checkExclude$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;

    StaticContentKt$respondStaticFile$checkExclude$1(Continuation<? super StaticContentKt$respondStaticFile$checkExclude$1> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return StaticContentKt.respondStaticFile$checkExclude((Function1<? super File, Boolean>) null, (ApplicationCall) null, (File) null, this);
    }
}
