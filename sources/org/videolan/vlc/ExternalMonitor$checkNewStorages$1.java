package org.videolan.vlc;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.resources.Constants;
import org.videolan.resources.util.ExtensionsKt;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.ExternalMonitor$checkNewStorages$1", f = "ExternalMonitor.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: ExternalMonitor.kt */
final class ExternalMonitor$checkNewStorages$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Context $ctx;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ExternalMonitor$checkNewStorages$1(Context context, Continuation<? super ExternalMonitor$checkNewStorages$1> continuation) {
        super(2, continuation);
        this.$ctx = context;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ExternalMonitor$checkNewStorages$1(this.$ctx, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ExternalMonitor$checkNewStorages$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            ExtensionsKt.launchForeground$default(this.$ctx, new Intent(Constants.ACTION_CHECK_STORAGES, (Uri) null, this.$ctx, MediaParsingService.class), (Function0) null, 2, (Object) null);
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
