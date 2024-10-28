package org.videolan.vlc;

import android.content.SharedPreferences;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.StartActivity$startApplication$1$1", f = "StartActivity.kt", i = {}, l = {257}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: StartActivity.kt */
final class StartActivity$startApplication$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ boolean $firstRun;
    final /* synthetic */ boolean $onboarding;
    final /* synthetic */ boolean $removeDevices;
    final /* synthetic */ SharedPreferences $settings;
    final /* synthetic */ boolean $upgrade;
    int label;
    final /* synthetic */ StartActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    StartActivity$startApplication$1$1(StartActivity startActivity, boolean z, boolean z2, boolean z3, boolean z4, SharedPreferences sharedPreferences, Continuation<? super StartActivity$startApplication$1$1> continuation) {
        super(2, continuation);
        this.this$0 = startActivity;
        this.$firstRun = z;
        this.$upgrade = z2;
        this.$removeDevices = z3;
        this.$onboarding = z4;
        this.$settings = sharedPreferences;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new StartActivity$startApplication$1$1(this.this$0, this.$firstRun, this.$upgrade, this.$removeDevices, this.$onboarding, this.$settings, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((StartActivity$startApplication$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0052  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r12) {
        /*
            r11 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r11.label
            r2 = 1
            if (r1 == 0) goto L_0x0017
            if (r1 != r2) goto L_0x000f
            kotlin.ResultKt.throwOnFailure(r12)
            goto L_0x0030
        L_0x000f:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r0)
            throw r12
        L_0x0017:
            kotlin.ResultKt.throwOnFailure(r12)
            int r12 = android.os.Build.VERSION.SDK_INT
            r1 = 28
            if (r12 != r1) goto L_0x003b
            org.videolan.vlc.StartActivity r12 = r11.this$0
            android.content.Context r12 = (android.content.Context) r12
            r1 = r11
            kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
            r11.label = r2
            java.lang.Object r12 = org.videolan.tools.KotlinExtensionsKt.awaitAppIsForegroung(r12, r1)
            if (r12 != r0) goto L_0x0030
            return r0
        L_0x0030:
            java.lang.Boolean r12 = (java.lang.Boolean) r12
            boolean r12 = r12.booleanValue()
            if (r12 != 0) goto L_0x003b
            kotlin.Unit r12 = kotlin.Unit.INSTANCE
            return r12
        L_0x003b:
            org.videolan.vlc.StartActivity r12 = r11.this$0
            r3 = r12
            android.content.Context r3 = (android.content.Context) r3
            boolean r4 = r11.$firstRun
            boolean r5 = r11.$upgrade
            boolean r7 = r11.$removeDevices
            r9 = 16
            r10 = 0
            r6 = 1
            r8 = 0
            org.videolan.resources.util.ExtensionsKt.startMedialibrary$default(r3, r4, r5, r6, r7, r8, r9, r10)
            boolean r12 = r11.$onboarding
            if (r12 == 0) goto L_0x005d
            android.content.SharedPreferences r12 = r11.$settings
            java.lang.String r0 = "app_onboarding_done"
            java.lang.Boolean r1 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r2)
            org.videolan.tools.SettingsKt.putSingle(r12, r0, r1)
        L_0x005d:
            kotlin.Unit r12 = kotlin.Unit.INSTANCE
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.StartActivity$startApplication$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
