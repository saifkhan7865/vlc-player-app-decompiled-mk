package io.ktor.server.engine;

import io.ktor.http.ParametersBuilder;
import io.ktor.http.content.PartData;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H@"}, d2 = {"<anonymous>", "", "part", "Lio/ktor/http/content/PartData;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.engine.DefaultTransformKt$installDefaultTransformations$2$transformed$1$1", f = "DefaultTransform.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: DefaultTransform.kt */
final class DefaultTransformKt$installDefaultTransformations$2$transformed$1$1 extends SuspendLambda implements Function2<PartData, Continuation<? super Unit>, Object> {
    final /* synthetic */ ParametersBuilder $this_build;
    /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DefaultTransformKt$installDefaultTransformations$2$transformed$1$1(ParametersBuilder parametersBuilder, Continuation<? super DefaultTransformKt$installDefaultTransformations$2$transformed$1$1> continuation) {
        super(2, continuation);
        this.$this_build = parametersBuilder;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        DefaultTransformKt$installDefaultTransformations$2$transformed$1$1 defaultTransformKt$installDefaultTransformations$2$transformed$1$1 = new DefaultTransformKt$installDefaultTransformations$2$transformed$1$1(this.$this_build, continuation);
        defaultTransformKt$installDefaultTransformations$2$transformed$1$1.L$0 = obj;
        return defaultTransformKt$installDefaultTransformations$2$transformed$1$1;
    }

    public final Object invoke(PartData partData, Continuation<? super Unit> continuation) {
        return ((DefaultTransformKt$installDefaultTransformations$2$transformed$1$1) create(partData, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        String name;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            PartData partData = (PartData) this.L$0;
            if ((partData instanceof PartData.FormItem) && (name = partData.getName()) != null) {
                this.$this_build.append(name, ((PartData.FormItem) partData).getValue());
            }
            partData.getDispose().invoke();
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
