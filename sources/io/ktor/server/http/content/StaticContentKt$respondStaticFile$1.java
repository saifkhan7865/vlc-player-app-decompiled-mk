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
@DebugMetadata(c = "io.ktor.server.http.content.StaticContentKt", f = "StaticContent.kt", i = {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4}, l = {464, 466, 468, 472, 473, 480}, m = "respondStaticFile", n = {"$this$respondStaticFile", "dir", "compressedTypes", "contentType", "cacheControl", "modify", "defaultPath", "$this$respondStaticFile", "dir", "compressedTypes", "contentType", "cacheControl", "modify", "exclude", "extensions", "defaultPath", "requestedFile", "$this$respondStaticFile", "dir", "compressedTypes", "contentType", "cacheControl", "modify", "exclude", "extensions", "defaultPath", "requestedFile", "$this$respondStaticFile", "dir", "compressedTypes", "contentType", "cacheControl", "modify", "exclude", "defaultPath", "requestedFile", "fileWithExtension", "$this$respondStaticFile", "dir", "compressedTypes", "contentType", "cacheControl", "modify", "exclude", "defaultPath", "requestedFile"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$10", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8"})
/* compiled from: StaticContent.kt */
final class StaticContentKt$respondStaticFile$1 extends ContinuationImpl {
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

    StaticContentKt$respondStaticFile$1(Continuation<? super StaticContentKt$respondStaticFile$1> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return StaticContentKt.respondStaticFile((ApplicationCall) null, (String) null, (File) null, (List<? extends CompressedFileType>) null, (Function1<? super File, ContentType>) null, (Function1<? super File, ? extends List<? extends CacheControl>>) null, (Function3<? super File, ? super ApplicationCall, ? super Continuation<? super Unit>, ? extends Object>) null, (Function1<? super File, Boolean>) null, (List<String>) null, (String) null, this);
    }
}
