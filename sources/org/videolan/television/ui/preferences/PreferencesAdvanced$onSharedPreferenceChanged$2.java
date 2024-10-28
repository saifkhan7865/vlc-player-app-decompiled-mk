package org.videolan.television.ui.preferences;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.television.ui.preferences.PreferencesAdvanced$onSharedPreferenceChanged$2", f = "PreferencesAdvanced.kt", i = {}, l = {345}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: PreferencesAdvanced.kt */
final class PreferencesAdvanced$onSharedPreferenceChanged$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ PreferencesAdvanced this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PreferencesAdvanced$onSharedPreferenceChanged$2(PreferencesAdvanced preferencesAdvanced, Continuation<? super PreferencesAdvanced$onSharedPreferenceChanged$2> continuation) {
        super(2, continuation);
        this.this$0 = preferencesAdvanced;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PreferencesAdvanced$onSharedPreferenceChanged$2(this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PreferencesAdvanced$onSharedPreferenceChanged$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            if (this.this$0.restartLibVLC(this) == coroutine_suspended) {
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
