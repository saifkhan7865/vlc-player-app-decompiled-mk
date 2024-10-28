package io.ktor.client.network.sockets;

import io.ktor.utils.io.ByteChannel;
import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.ByteReadChannelKt;
import io.ktor.utils.io.WriterScope;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lio/ktor/utils/io/WriterScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.client.network.sockets.TimeoutExceptionsCommonKt$mapEngineExceptions$1", f = "TimeoutExceptionsCommon.kt", i = {}, l = {39}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: TimeoutExceptionsCommon.kt */
final class TimeoutExceptionsCommonKt$mapEngineExceptions$1 extends SuspendLambda implements Function2<WriterScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ ByteReadChannel $input;
    final /* synthetic */ ByteChannel $replacementChannel;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    TimeoutExceptionsCommonKt$mapEngineExceptions$1(ByteReadChannel byteReadChannel, ByteChannel byteChannel, Continuation<? super TimeoutExceptionsCommonKt$mapEngineExceptions$1> continuation) {
        super(2, continuation);
        this.$input = byteReadChannel;
        this.$replacementChannel = byteChannel;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new TimeoutExceptionsCommonKt$mapEngineExceptions$1(this.$input, this.$replacementChannel, continuation);
    }

    public final Object invoke(WriterScope writerScope, Continuation<? super Unit> continuation) {
        return ((TimeoutExceptionsCommonKt$mapEngineExceptions$1) create(writerScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            ByteReadChannel byteReadChannel = this.$input;
            this.label = 1;
            if (ByteReadChannelKt.copyAndClose$default(byteReadChannel, this.$replacementChannel, 0, this, 2, (Object) null) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            try {
                ResultKt.throwOnFailure(obj);
            } catch (Throwable th) {
                this.$input.cancel(th);
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
