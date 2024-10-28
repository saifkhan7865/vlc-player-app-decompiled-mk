package org.videolan.vlc.gui.preferences;

import android.content.SharedPreferences;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.preferences.PreferencesAdvanced$onSharedPreferenceChanged$3", f = "PreferencesAdvanced.kt", i = {}, l = {344, 349, 349, 349, 351}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: PreferencesAdvanced.kt */
final class PreferencesAdvanced$onSharedPreferenceChanged$3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ SharedPreferences $sharedPreferences;
    Object L$0;
    int label;
    final /* synthetic */ PreferencesAdvanced this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PreferencesAdvanced$onSharedPreferenceChanged$3(PreferencesAdvanced preferencesAdvanced, SharedPreferences sharedPreferences, Continuation<? super PreferencesAdvanced$onSharedPreferenceChanged$3> continuation) {
        super(2, continuation);
        this.this$0 = preferencesAdvanced;
        this.$sharedPreferences = sharedPreferences;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PreferencesAdvanced$onSharedPreferenceChanged$3(this.this$0, this.$sharedPreferences, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PreferencesAdvanced$onSharedPreferenceChanged$3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0054 */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0080 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x008e A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r12) {
        /*
            r11 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r11.label
            r2 = 5
            r3 = 4
            r4 = 3
            r5 = 2
            r6 = 1
            if (r1 == 0) goto L_0x0037
            if (r1 == r6) goto L_0x0031
            if (r1 == r5) goto L_0x002d
            if (r1 == r4) goto L_0x002d
            if (r1 == r3) goto L_0x0024
            if (r1 != r2) goto L_0x001c
            kotlin.ResultKt.throwOnFailure(r12)
            goto L_0x008f
        L_0x001c:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r0)
            throw r12
        L_0x0024:
            java.lang.Object r0 = r11.L$0
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            kotlin.ResultKt.throwOnFailure(r12)
            goto L_0x00a1
        L_0x002d:
            kotlin.ResultKt.throwOnFailure(r12)
            goto L_0x0081
        L_0x0031:
            kotlin.ResultKt.throwOnFailure(r12)     // Catch:{ IllegalStateException -> 0x0054 }
            goto L_0x0048
        L_0x0035:
            r12 = move-exception
            goto L_0x0092
        L_0x0037:
            kotlin.ResultKt.throwOnFailure(r12)
            org.videolan.resources.VLCInstance r12 = org.videolan.resources.VLCInstance.INSTANCE     // Catch:{ IllegalStateException -> 0x0054 }
            r1 = r11
            kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1     // Catch:{ IllegalStateException -> 0x0054 }
            r11.label = r6     // Catch:{ IllegalStateException -> 0x0054 }
            java.lang.Object r12 = r12.restart(r1)     // Catch:{ IllegalStateException -> 0x0054 }
            if (r12 != r0) goto L_0x0048
            return r0
        L_0x0048:
            r12 = r11
            kotlin.coroutines.Continuation r12 = (kotlin.coroutines.Continuation) r12
            r11.label = r5
            java.lang.Object r12 = org.videolan.vlc.gui.helpers.PreferenceUtilsKt.restartMediaPlayer(r12)
            if (r12 != r0) goto L_0x0081
            return r0
        L_0x0054:
            org.videolan.vlc.gui.helpers.UiTools r5 = org.videolan.vlc.gui.helpers.UiTools.INSTANCE     // Catch:{ all -> 0x0035 }
            org.videolan.vlc.gui.preferences.PreferencesAdvanced r12 = r11.this$0     // Catch:{ all -> 0x0035 }
            androidx.fragment.app.FragmentActivity r12 = r12.requireActivity()     // Catch:{ all -> 0x0035 }
            java.lang.String r1 = "requireActivity(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r12, r1)     // Catch:{ all -> 0x0035 }
            r6 = r12
            android.app.Activity r6 = (android.app.Activity) r6     // Catch:{ all -> 0x0035 }
            int r7 = org.videolan.vlc.R.string.custom_libvlc_options_invalid     // Catch:{ all -> 0x0035 }
            r9 = 4
            r10 = 0
            r8 = 0
            org.videolan.vlc.gui.helpers.UiTools.snacker$default(r5, r6, r7, r8, r9, r10)     // Catch:{ all -> 0x0035 }
            android.content.SharedPreferences r12 = r11.$sharedPreferences     // Catch:{ all -> 0x0035 }
            java.lang.String r1 = "custom_libvlc_options"
            java.lang.String r5 = ""
            org.videolan.tools.SettingsKt.putSingle(r12, r1, r5)     // Catch:{ all -> 0x0035 }
            r12 = r11
            kotlin.coroutines.Continuation r12 = (kotlin.coroutines.Continuation) r12
            r11.label = r4
            java.lang.Object r12 = org.videolan.vlc.gui.helpers.PreferenceUtilsKt.restartMediaPlayer(r12)
            if (r12 != r0) goto L_0x0081
            return r0
        L_0x0081:
            org.videolan.vlc.gui.preferences.PreferencesAdvanced r12 = r11.this$0
            r1 = r11
            kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
            r11.label = r2
            java.lang.Object r12 = r12.restartLibVLC(r1)
            if (r12 != r0) goto L_0x008f
            return r0
        L_0x008f:
            kotlin.Unit r12 = kotlin.Unit.INSTANCE
            return r12
        L_0x0092:
            r1 = r11
            kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
            r11.L$0 = r12
            r11.label = r3
            java.lang.Object r1 = org.videolan.vlc.gui.helpers.PreferenceUtilsKt.restartMediaPlayer(r1)
            if (r1 != r0) goto L_0x00a0
            return r0
        L_0x00a0:
            r0 = r12
        L_0x00a1:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.preferences.PreferencesAdvanced$onSharedPreferenceChanged$3.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
