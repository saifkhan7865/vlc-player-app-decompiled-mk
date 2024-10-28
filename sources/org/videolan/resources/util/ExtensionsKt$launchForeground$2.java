package org.videolan.resources.util;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import androidx.core.content.ContextCompat;
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
import org.videolan.tools.AppUtils$$ExternalSyntheticApiModelOutline0;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.resources.util.ExtensionsKt$launchForeground$2", f = "Extensions.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: Extensions.kt */
final class ExtensionsKt$launchForeground$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function0<Unit> $block;
    final /* synthetic */ Context $ctx;
    final /* synthetic */ Intent $intent;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ExtensionsKt$launchForeground$2(Intent intent, Context context, Function0<Unit> function0, Continuation<? super ExtensionsKt$launchForeground$2> continuation) {
        super(2, continuation);
        this.$intent = intent;
        this.$ctx = context;
        this.$block = function0;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ExtensionsKt$launchForeground$2(this.$intent, this.$ctx, this.$block, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ExtensionsKt$launchForeground$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            this.$intent.putExtra("foreground", true);
            try {
                ContextCompat.startForegroundService(this.$ctx, this.$intent);
            } catch (Exception e) {
                if (Build.VERSION.SDK_INT >= 31 && AppUtils$$ExternalSyntheticApiModelOutline0.m$1((Object) e)) {
                    Log.w("MediaParsingService", "ForegroundServiceStartNotAllowedException caught!");
                }
            }
            this.$block.invoke();
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
