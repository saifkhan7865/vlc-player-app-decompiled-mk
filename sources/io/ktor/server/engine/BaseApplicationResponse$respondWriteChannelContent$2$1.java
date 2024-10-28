package io.ktor.server.engine;

import io.ktor.http.content.OutgoingContent;
import io.ktor.utils.io.ByteWriteChannel;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.engine.BaseApplicationResponse$respondWriteChannelContent$2$1", f = "BaseApplicationResponse.kt", i = {}, l = {174}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: BaseApplicationResponse.kt */
final class BaseApplicationResponse$respondWriteChannelContent$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ OutgoingContent.WriteChannelContent $content;
    final /* synthetic */ ByteWriteChannel $this_use;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BaseApplicationResponse$respondWriteChannelContent$2$1(OutgoingContent.WriteChannelContent writeChannelContent, ByteWriteChannel byteWriteChannel, Continuation<? super BaseApplicationResponse$respondWriteChannelContent$2$1> continuation) {
        super(2, continuation);
        this.$content = writeChannelContent;
        this.$this_use = byteWriteChannel;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new BaseApplicationResponse$respondWriteChannelContent$2$1(this.$content, this.$this_use, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((BaseApplicationResponse$respondWriteChannelContent$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            if (this.$content.writeTo(this.$this_use, this) == coroutine_suspended) {
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
