package io.ktor.server.http.content;

import io.ktor.http.CacheControl;
import io.ktor.http.ContentType;
import io.ktor.server.application.ApplicationCall;
import java.io.File;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lio/ktor/server/application/ApplicationCall;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.http.content.StaticContentKt$staticFiles$2", f = "StaticContent.kt", i = {}, l = {174}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: StaticContent.kt */
final class StaticContentKt$staticFiles$2 extends SuspendLambda implements Function2<ApplicationCall, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function1<File, List<CacheControl>> $cacheControl;
    final /* synthetic */ List<CompressedFileType> $compressedTypes;
    final /* synthetic */ Function1<File, ContentType> $contentType;
    final /* synthetic */ String $defaultPath;
    final /* synthetic */ File $dir;
    final /* synthetic */ Function1<File, Boolean> $exclude;
    final /* synthetic */ List<String> $extensions;
    final /* synthetic */ String $index;
    final /* synthetic */ Function3<File, ApplicationCall, Continuation<? super Unit>, Object> $modify;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    StaticContentKt$staticFiles$2(String str, File file, List<? extends CompressedFileType> list, Function1<? super File, ContentType> function1, Function1<? super File, ? extends List<? extends CacheControl>> function12, Function3<? super File, ? super ApplicationCall, ? super Continuation<? super Unit>, ? extends Object> function3, Function1<? super File, Boolean> function13, List<String> list2, String str2, Continuation<? super StaticContentKt$staticFiles$2> continuation) {
        super(2, continuation);
        this.$index = str;
        this.$dir = file;
        this.$compressedTypes = list;
        this.$contentType = function1;
        this.$cacheControl = function12;
        this.$modify = function3;
        this.$exclude = function13;
        this.$extensions = list2;
        this.$defaultPath = str2;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        StaticContentKt$staticFiles$2 staticContentKt$staticFiles$2 = new StaticContentKt$staticFiles$2(this.$index, this.$dir, this.$compressedTypes, this.$contentType, this.$cacheControl, this.$modify, this.$exclude, this.$extensions, this.$defaultPath, continuation);
        staticContentKt$staticFiles$2.L$0 = obj;
        return staticContentKt$staticFiles$2;
    }

    public final Object invoke(ApplicationCall applicationCall, Continuation<? super Unit> continuation) {
        return ((StaticContentKt$staticFiles$2) create(applicationCall, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            if (StaticContentKt.respondStaticFile((ApplicationCall) this.L$0, this.$index, this.$dir, this.$compressedTypes, this.$contentType, this.$cacheControl, this.$modify, this.$exclude, this.$extensions, this.$defaultPath, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
