package io.ktor.http.cio;

import io.ktor.http.cio.MultipartEvent;
import io.ktor.utils.io.ByteReadChannelKt;
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
@DebugMetadata(c = "io.ktor.http.cio.MultipartEvent$MultipartPart$release$2", f = "Multipart.kt", i = {}, l = {56}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: Multipart.kt */
final class MultipartEvent$MultipartPart$release$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Long>, Object> {
    int label;
    final /* synthetic */ MultipartEvent.MultipartPart this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MultipartEvent$MultipartPart$release$2(MultipartEvent.MultipartPart multipartPart, Continuation<? super MultipartEvent$MultipartPart$release$2> continuation) {
        super(2, continuation);
        this.this$0 = multipartPart;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MultipartEvent$MultipartPart$release$2(this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Long> continuation) {
        return ((MultipartEvent$MultipartPart$release$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            obj = ByteReadChannelKt.discard(this.this$0.getBody(), this);
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
