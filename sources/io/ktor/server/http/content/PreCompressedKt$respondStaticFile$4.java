package io.ktor.server.http.content;

import io.ktor.server.application.ApplicationCall;
import java.io.File;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H@"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Ljava/io/File;", "<anonymous parameter 1>", "Lio/ktor/server/application/ApplicationCall;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.http.content.PreCompressedKt$respondStaticFile$4", f = "PreCompressed.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: PreCompressed.kt */
final class PreCompressedKt$respondStaticFile$4 extends SuspendLambda implements Function3<File, ApplicationCall, Continuation<? super Unit>, Object> {
    int label;

    PreCompressedKt$respondStaticFile$4(Continuation<? super PreCompressedKt$respondStaticFile$4> continuation) {
        super(3, continuation);
    }

    public final Object invoke(File file, ApplicationCall applicationCall, Continuation<? super Unit> continuation) {
        return new PreCompressedKt$respondStaticFile$4(continuation).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
