package org.videolan.vlc.gui.helpers.hf;

import android.content.SharedPreferences;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.resources.AndroidDevices;
import org.videolan.tools.SettingsKt;
import org.videolan.vlc.gui.onboarding.OnboardingActivityKt;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.helpers.hf.StoragePermissionsDelegate$Companion$askStoragePermission$1$onboardingDone$1", f = "StoragePermissionsDelegate.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: StoragePermissionsDelegate.kt */
final class StoragePermissionsDelegate$Companion$askStoragePermission$1$onboardingDone$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
    final /* synthetic */ SharedPreferences $settings;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    StoragePermissionsDelegate$Companion$askStoragePermission$1$onboardingDone$1(SharedPreferences sharedPreferences, Continuation<? super StoragePermissionsDelegate$Companion$askStoragePermission$1$onboardingDone$1> continuation) {
        super(2, continuation);
        this.$settings = sharedPreferences;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new StoragePermissionsDelegate$Companion$askStoragePermission$1$onboardingDone$1(this.$settings, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Boolean> continuation) {
        return ((StoragePermissionsDelegate$Companion$askStoragePermission$1$onboardingDone$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        boolean z;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            if (AndroidDevices.INSTANCE.isTv()) {
                z = this.$settings.getBoolean(SettingsKt.KEY_TV_ONBOARDING_DONE, false);
            } else {
                z = this.$settings.getBoolean(OnboardingActivityKt.ONBOARDING_DONE_KEY, false);
            }
            return Boxing.boxBoolean(z);
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
