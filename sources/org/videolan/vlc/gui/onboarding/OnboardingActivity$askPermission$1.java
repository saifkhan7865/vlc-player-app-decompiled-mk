package org.videolan.vlc.gui.onboarding;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.vlc.gui.helpers.hf.StoragePermissionsDelegate;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.onboarding.OnboardingActivity$askPermission$1", f = "OnboardingActivity.kt", i = {}, l = {99}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: OnboardingActivity.kt */
final class OnboardingActivity$askPermission$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ OnboardingActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    OnboardingActivity$askPermission$1(OnboardingActivity onboardingActivity, Continuation<? super OnboardingActivity$askPermission$1> continuation) {
        super(2, continuation);
        this.this$0 = onboardingActivity;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new OnboardingActivity$askPermission$1(this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((OnboardingActivity$askPermission$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            boolean z = this.this$0.getViewModel().getPermissionType() == PermissionType.MEDIA;
            this.this$0.getViewModel().setPermissionAlreadyAsked(true);
            this.label = 1;
            if (StoragePermissionsDelegate.Companion.getStoragePermission$default(StoragePermissionsDelegate.Companion, this.this$0, false, false, z, this, 1, (Object) null) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        this.this$0.onNext();
        return Unit.INSTANCE;
    }
}
