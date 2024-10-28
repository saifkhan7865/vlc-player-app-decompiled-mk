package org.videolan.vlc.gui.helpers.hf;

import android.content.SharedPreferences;
import androidx.fragment.app.FragmentActivity;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.helpers.hf.StoragePermissionsDelegate$Companion$askStoragePermission$1", f = "StoragePermissionsDelegate.kt", i = {1}, l = {170, 173}, m = "invokeSuspend", n = {"granted"}, s = {"Z$0"})
/* compiled from: StoragePermissionsDelegate.kt */
final class StoragePermissionsDelegate$Companion$askStoragePermission$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Runnable $cb;
    final /* synthetic */ boolean $firstRun;
    final /* synthetic */ SharedPreferences $settings;
    final /* synthetic */ FragmentActivity $this_askStoragePermission;
    final /* synthetic */ boolean $upgrade;
    final /* synthetic */ boolean $write;
    boolean Z$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    StoragePermissionsDelegate$Companion$askStoragePermission$1(FragmentActivity fragmentActivity, boolean z, Runnable runnable, boolean z2, boolean z3, SharedPreferences sharedPreferences, Continuation<? super StoragePermissionsDelegate$Companion$askStoragePermission$1> continuation) {
        super(2, continuation);
        this.$this_askStoragePermission = fragmentActivity;
        this.$write = z;
        this.$cb = runnable;
        this.$firstRun = z2;
        this.$upgrade = z3;
        this.$settings = sharedPreferences;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new StoragePermissionsDelegate$Companion$askStoragePermission$1(this.$this_askStoragePermission, this.$write, this.$cb, this.$firstRun, this.$upgrade, this.$settings, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((StoragePermissionsDelegate$Companion$askStoragePermission$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x00ae  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r12) {
        /*
            r11 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r11.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L_0x0021
            if (r1 == r3) goto L_0x001d
            if (r1 != r2) goto L_0x0015
            boolean r0 = r11.Z$0
            kotlin.ResultKt.throwOnFailure(r12)
            goto L_0x00a0
        L_0x0015:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r0)
            throw r12
        L_0x001d:
            kotlin.ResultKt.throwOnFailure(r12)
            goto L_0x003b
        L_0x0021:
            kotlin.ResultKt.throwOnFailure(r12)
            org.videolan.vlc.gui.helpers.hf.StoragePermissionsDelegate$Companion r12 = org.videolan.vlc.gui.helpers.hf.StoragePermissionsDelegate.Companion
            androidx.fragment.app.FragmentActivity r4 = r11.$this_askStoragePermission
            boolean r5 = r11.$write
            r8 = r11
            kotlin.coroutines.Continuation r8 = (kotlin.coroutines.Continuation) r8
            r11.label = r3
            r6 = 0
            r7 = 0
            r9 = 6
            r10 = 0
            r3 = r12
            java.lang.Object r12 = org.videolan.vlc.gui.helpers.hf.StoragePermissionsDelegate.Companion.getStoragePermission$default(r3, r4, r5, r6, r7, r8, r9, r10)
            if (r12 != r0) goto L_0x003b
            return r0
        L_0x003b:
            java.lang.Boolean r12 = (java.lang.Boolean) r12
            boolean r12 = r12.booleanValue()
            androidx.fragment.app.FragmentActivity r1 = r11.$this_askStoragePermission
            androidx.activity.ComponentActivity r1 = (androidx.activity.ComponentActivity) r1
            org.videolan.vlc.gui.helpers.hf.StoragePermissionsDelegate$Companion$askStoragePermission$1$invokeSuspend$$inlined$viewModels$default$1 r3 = new org.videolan.vlc.gui.helpers.hf.StoragePermissionsDelegate$Companion$askStoragePermission$1$invokeSuspend$$inlined$viewModels$default$1
            r3.<init>(r1)
            kotlin.jvm.functions.Function0 r3 = (kotlin.jvm.functions.Function0) r3
            androidx.lifecycle.ViewModelLazy r4 = new androidx.lifecycle.ViewModelLazy
            java.lang.Class<org.videolan.vlc.gui.helpers.hf.PermissionViewmodel> r5 = org.videolan.vlc.gui.helpers.hf.PermissionViewmodel.class
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r5)
            org.videolan.vlc.gui.helpers.hf.StoragePermissionsDelegate$Companion$askStoragePermission$1$invokeSuspend$$inlined$viewModels$default$2 r6 = new org.videolan.vlc.gui.helpers.hf.StoragePermissionsDelegate$Companion$askStoragePermission$1$invokeSuspend$$inlined$viewModels$default$2
            r6.<init>(r1)
            kotlin.jvm.functions.Function0 r6 = (kotlin.jvm.functions.Function0) r6
            org.videolan.vlc.gui.helpers.hf.StoragePermissionsDelegate$Companion$askStoragePermission$1$invokeSuspend$$inlined$viewModels$default$3 r7 = new org.videolan.vlc.gui.helpers.hf.StoragePermissionsDelegate$Companion$askStoragePermission$1$invokeSuspend$$inlined$viewModels$default$3
            r8 = 0
            r7.<init>(r8, r1)
            kotlin.jvm.functions.Function0 r7 = (kotlin.jvm.functions.Function0) r7
            r4.<init>(r5, r6, r3, r7)
            kotlin.Lazy r4 = (kotlin.Lazy) r4
            org.videolan.vlc.gui.helpers.hf.PermissionViewmodel r1 = invokeSuspend$lambda$0(r4)
            boolean r1 = r1.getPermissionPending()
            if (r1 == 0) goto L_0x0081
            org.videolan.vlc.gui.helpers.hf.PermissionViewmodel r1 = invokeSuspend$lambda$0(r4)
            kotlinx.coroutines.CompletableDeferred r1 = r1.getDeferredGrant()
            java.lang.Boolean r3 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r12)
            r1.complete(r3)
        L_0x0081:
            kotlinx.coroutines.CoroutineDispatcher r1 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r1 = (kotlin.coroutines.CoroutineContext) r1
            org.videolan.vlc.gui.helpers.hf.StoragePermissionsDelegate$Companion$askStoragePermission$1$onboardingDone$1 r3 = new org.videolan.vlc.gui.helpers.hf.StoragePermissionsDelegate$Companion$askStoragePermission$1$onboardingDone$1
            android.content.SharedPreferences r4 = r11.$settings
            r3.<init>(r4, r8)
            kotlin.jvm.functions.Function2 r3 = (kotlin.jvm.functions.Function2) r3
            r4 = r11
            kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
            r11.Z$0 = r12
            r11.label = r2
            java.lang.Object r1 = kotlinx.coroutines.BuildersKt.withContext(r1, r3, r4)
            if (r1 != r0) goto L_0x009e
            return r0
        L_0x009e:
            r0 = r12
            r12 = r1
        L_0x00a0:
            java.lang.Boolean r12 = (java.lang.Boolean) r12
            boolean r12 = r12.booleanValue()
            if (r0 == 0) goto L_0x00bd
            if (r12 == 0) goto L_0x00bd
            java.lang.Runnable r12 = r11.$cb
            if (r12 != 0) goto L_0x00ba
            org.videolan.vlc.gui.helpers.hf.StoragePermissionsDelegate$Companion r12 = org.videolan.vlc.gui.helpers.hf.StoragePermissionsDelegate.Companion
            androidx.fragment.app.FragmentActivity r0 = r11.$this_askStoragePermission
            boolean r1 = r11.$firstRun
            boolean r2 = r11.$upgrade
            java.lang.Runnable r12 = r12.getAction(r0, r1, r2)
        L_0x00ba:
            r12.run()
        L_0x00bd:
            kotlin.Unit r12 = kotlin.Unit.INSTANCE
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.helpers.hf.StoragePermissionsDelegate$Companion$askStoragePermission$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    private static final PermissionViewmodel invokeSuspend$lambda$0(Lazy<PermissionViewmodel> lazy) {
        return lazy.getValue();
    }
}
