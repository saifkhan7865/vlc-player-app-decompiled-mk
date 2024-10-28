package io.ktor.server.http.content;

import io.ktor.http.CacheControl;
import io.ktor.http.ContentType;
import io.ktor.server.application.ApplicationCall;
import java.net.URL;
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
@DebugMetadata(c = "io.ktor.server.http.content.StaticContentKt$staticResources$2", f = "StaticContent.kt", i = {}, l = {214}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: StaticContent.kt */
final class StaticContentKt$staticResources$2 extends SuspendLambda implements Function2<ApplicationCall, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $basePackage;
    final /* synthetic */ Function1<URL, List<CacheControl>> $cacheControl;
    final /* synthetic */ List<CompressedFileType> $compressedTypes;
    final /* synthetic */ Function1<URL, ContentType> $contentType;
    final /* synthetic */ String $defaultPath;
    final /* synthetic */ Function1<URL, Boolean> $exclude;
    final /* synthetic */ List<String> $extensions;
    final /* synthetic */ String $index;
    final /* synthetic */ Function3<URL, ApplicationCall, Continuation<? super Unit>, Object> $modifier;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    StaticContentKt$staticResources$2(String str, String str2, List<? extends CompressedFileType> list, Function1<? super URL, ContentType> function1, Function1<? super URL, ? extends List<? extends CacheControl>> function12, Function3<? super URL, ? super ApplicationCall, ? super Continuation<? super Unit>, ? extends Object> function3, Function1<? super URL, Boolean> function13, List<String> list2, String str3, Continuation<? super StaticContentKt$staticResources$2> continuation) {
        super(2, continuation);
        this.$index = str;
        this.$basePackage = str2;
        this.$compressedTypes = list;
        this.$contentType = function1;
        this.$cacheControl = function12;
        this.$modifier = function3;
        this.$exclude = function13;
        this.$extensions = list2;
        this.$defaultPath = str3;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        StaticContentKt$staticResources$2 staticContentKt$staticResources$2 = new StaticContentKt$staticResources$2(this.$index, this.$basePackage, this.$compressedTypes, this.$contentType, this.$cacheControl, this.$modifier, this.$exclude, this.$extensions, this.$defaultPath, continuation);
        staticContentKt$staticResources$2.L$0 = obj;
        return staticContentKt$staticResources$2;
    }

    public final Object invoke(ApplicationCall applicationCall, Continuation<? super Unit> continuation) {
        return ((StaticContentKt$staticResources$2) create(applicationCall, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            if (StaticContentKt.respondStaticResource((ApplicationCall) this.L$0, this.$index, this.$basePackage, this.$compressedTypes, this.$contentType, this.$cacheControl, this.$modifier, this.$exclude, this.$extensions, this.$defaultPath, this) == coroutine_suspended) {
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
