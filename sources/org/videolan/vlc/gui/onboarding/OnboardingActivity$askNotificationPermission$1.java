package org.videolan.vlc.gui.onboarding;

import android.content.SharedPreferences;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.tools.Settings;
import org.videolan.tools.SettingsKt;
import org.videolan.vlc.gui.helpers.hf.NotificationDelegate;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.onboarding.OnboardingActivity$askNotificationPermission$1", f = "OnboardingActivity.kt", i = {}, l = {107}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: OnboardingActivity.kt */
final class OnboardingActivity$askNotificationPermission$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ OnboardingActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    OnboardingActivity$askNotificationPermission$1(OnboardingActivity onboardingActivity, Continuation<? super OnboardingActivity$askNotificationPermission$1> continuation) {
        super(2, continuation);
        this.this$0 = onboardingActivity;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new OnboardingActivity$askNotificationPermission$1(this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((OnboardingActivity$askNotificationPermission$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.this$0.getViewModel().setNotificationPermissionAlreadyAsked(true);
            this.label = 1;
            if (NotificationDelegate.Companion.getNotificationPermission(this.this$0, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        SharedPreferences.Editor edit = ((SharedPreferences) Settings.INSTANCE.getInstance(this.this$0)).edit();
        edit.putBoolean(SettingsKt.NOTIFICATION_PERMISSION_ASKED, true);
        edit.apply();
        this.this$0.onNext();
        return Unit.INSTANCE;
    }
}
