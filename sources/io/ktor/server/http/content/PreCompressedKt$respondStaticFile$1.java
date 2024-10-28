package io.ktor.server.http.content;

import io.ktor.http.CacheControl;
import io.ktor.http.ContentType;
import io.ktor.server.application.ApplicationCall;
import java.io.File;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.http.content.PreCompressedKt", f = "PreCompressed.kt", i = {0, 0, 0, 2, 2, 2, 2, 2}, l = {115, 184, 125, 191}, m = "respondStaticFile", n = {"$this$respondStaticFile", "requestedFile", "contentType", "$this$respondStaticFile", "requestedFile", "contentType", "bestCompressionFit", "compressedFile"}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3", "L$4"})
/* compiled from: PreCompressed.kt */
final class PreCompressedKt$respondStaticFile$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    /* synthetic */ Object result;

    PreCompressedKt$respondStaticFile$1(Continuation<? super PreCompressedKt$respondStaticFile$1> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return PreCompressedKt.respondStaticFile((ApplicationCall) null, (File) null, (List<? extends CompressedFileType>) null, (Function1<? super File, ContentType>) null, (Function1<? super File, ? extends List<? extends CacheControl>>) null, (Function3<? super File, ? super ApplicationCall, ? super Continuation<? super Unit>, ? extends Object>) null, this);
    }
}
