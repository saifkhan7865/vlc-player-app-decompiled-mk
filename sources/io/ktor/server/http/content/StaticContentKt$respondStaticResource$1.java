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
@DebugMetadata(c = "io.ktor.server.http.content.StaticContentKt", f = "StaticContent.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2}, l = {497, 509, 522, 533}, m = "respondStaticResource", n = {"$this$respondStaticResource", "index", "basePackage", "compressedTypes", "contentType", "cacheControl", "modifier", "exclude", "extensions", "defaultPath", "relativePath", "$this$respondStaticResource", "index", "basePackage", "compressedTypes", "contentType", "cacheControl", "modifier", "exclude", "defaultPath", "relativePath", "$this$respondStaticResource", "basePackage", "compressedTypes", "contentType", "cacheControl", "modifier", "defaultPath"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6"})
/* compiled from: StaticContent.kt */
final class StaticContentKt$respondStaticResource$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$10;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    Object L$8;
    Object L$9;
    int label;
    /* synthetic */ Object result;

    StaticContentKt$respondStaticResource$1(Continuation<? super StaticContentKt$respondStaticResource$1> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return StaticContentKt.respondStaticResource((ApplicationCall) null, (String) null, (String) null, (List<? extends CompressedFileType>) null, (Function1<? super URL, ContentType>) null, (Function1<? super URL, ? extends List<? extends CacheControl>>) null, (Function3<? super URL, ? super ApplicationCall, ? super Continuation<? super Unit>, ? extends Object>) null, (Function1<? super URL, Boolean>) null, (List<String>) null, (String) null, this);
    }
}
