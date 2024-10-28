package io.ktor.server.http.content;

import io.ktor.http.CacheControl;
import io.ktor.http.ContentType;
import io.ktor.server.application.ApplicationCall;
import java.net.URL;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.http.content.PreCompressedKt", f = "PreCompressed.kt", i = {1, 1, 4, 4}, l = {184, 157, 191, 198, 174, 205}, m = "respondStaticResource", n = {"$this$respondStaticResource", "bestCompressionFit", "$this$respondStaticResource", "content"}, s = {"L$0", "L$1", "L$0", "L$1"})
/* compiled from: PreCompressed.kt */
final class PreCompressedKt$respondStaticResource$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;

    PreCompressedKt$respondStaticResource$1(Continuation<? super PreCompressedKt$respondStaticResource$1> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return PreCompressedKt.respondStaticResource((ApplicationCall) null, (String) null, (String) null, (List<? extends CompressedFileType>) null, (Function1<? super URL, ContentType>) null, (Function1<? super URL, ? extends List<? extends CacheControl>>) null, (Function3<? super URL, ? super ApplicationCall, ? super Continuation<? super Unit>, ? extends Object>) null, (Function1<? super URL, Boolean>) null, this);
    }
}
