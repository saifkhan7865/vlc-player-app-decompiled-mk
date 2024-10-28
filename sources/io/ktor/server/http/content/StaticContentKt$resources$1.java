package io.ktor.server.http.content;

import io.ktor.server.application.ApplicationCall;
import io.ktor.util.pipeline.PipelineContext;
import java.io.File;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u0004\u001a\u00020\u0001H@"}, d2 = {"<anonymous>", "", "Lio/ktor/util/pipeline/PipelineContext;", "Lio/ktor/server/application/ApplicationCall;", "it"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.http.content.StaticContentKt$resources$1", f = "StaticContent.kt", i = {}, l = {392}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: StaticContent.kt */
final class StaticContentKt$resources$1 extends SuspendLambda implements Function3<PipelineContext<Unit, ApplicationCall>, Unit, Continuation<? super Unit>, Object> {
    final /* synthetic */ List<CompressedFileType> $compressedTypes;
    final /* synthetic */ String $packageName;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    StaticContentKt$resources$1(String str, List<? extends CompressedFileType> list, Continuation<? super StaticContentKt$resources$1> continuation) {
        super(3, continuation);
        this.$packageName = str;
        this.$compressedTypes = list;
    }

    public final Object invoke(PipelineContext<Unit, ApplicationCall> pipelineContext, Unit unit, Continuation<? super Unit> continuation) {
        StaticContentKt$resources$1 staticContentKt$resources$1 = new StaticContentKt$resources$1(this.$packageName, this.$compressedTypes, continuation);
        staticContentKt$resources$1.L$0 = pipelineContext;
        return staticContentKt$resources$1.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            PipelineContext pipelineContext = (PipelineContext) this.L$0;
            List<String> all = ((ApplicationCall) pipelineContext.getContext()).getParameters().getAll("static-content-path-parameter");
            if (all != null) {
                String str = File.separator;
                Intrinsics.checkNotNullExpressionValue(str, "separator");
                String joinToString$default = CollectionsKt.joinToString$default(all, str, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null);
                if (joinToString$default != null) {
                    String str2 = this.$packageName;
                    List<CompressedFileType> list = this.$compressedTypes;
                    this.label = 1;
                    if (PreCompressedKt.respondStaticResource$default((ApplicationCall) pipelineContext.getContext(), joinToString$default, str2, list, (Function1) null, (Function1) null, (Function3) null, (Function1) null, this, 120, (Object) null) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
            }
            return Unit.INSTANCE;
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
