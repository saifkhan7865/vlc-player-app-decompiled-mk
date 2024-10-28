package io.ktor.server.engine;

import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.ByteReadChannelJVMKt;
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

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.engine.BaseApplicationResponse$respondFromChannel$2$copied$1", f = "BaseApplicationResponse.kt", i = {}, l = {211}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: BaseApplicationResponse.kt */
final class BaseApplicationResponse$respondFromChannel$2$copied$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Long>, Object> {
    final /* synthetic */ Long $length;
    final /* synthetic */ ByteReadChannel $readChannel;
    final /* synthetic */ ByteWriteChannel $this_use;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BaseApplicationResponse$respondFromChannel$2$copied$1(ByteReadChannel byteReadChannel, ByteWriteChannel byteWriteChannel, Long l, Continuation<? super BaseApplicationResponse$respondFromChannel$2$copied$1> continuation) {
        super(2, continuation);
        this.$readChannel = byteReadChannel;
        this.$this_use = byteWriteChannel;
        this.$length = l;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new BaseApplicationResponse$respondFromChannel$2$copied$1(this.$readChannel, this.$this_use, this.$length, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Long> continuation) {
        return ((BaseApplicationResponse$respondFromChannel$2$copied$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            ByteReadChannel byteReadChannel = this.$readChannel;
            ByteWriteChannel byteWriteChannel = this.$this_use;
            Long l = this.$length;
            long longValue = l != null ? l.longValue() : Long.MAX_VALUE;
            this.label = 1;
            obj = ByteReadChannelJVMKt.copyTo(byteReadChannel, byteWriteChannel, longValue, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return obj;
    }
}
