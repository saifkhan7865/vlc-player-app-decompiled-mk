package org.videolan.television.ui.preferences;

import android.content.SharedPreferences;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.television.ui.preferences.PreferencesAdvanced$onSharedPreferenceChanged$3", f = "PreferencesAdvanced.kt", i = {}, l = {351, 356, 356, 356, 358}, m = "invokeSuspend", n = {}, s = {})
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
    /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0053 */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x005b A[Catch:{ all -> 0x0034 }] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x007a A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0088 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r8) {
        /*
            r7 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r7.label
            r2 = 5
            r3 = 4
            r4 = 3
            r5 = 2
            r6 = 1
            if (r1 == 0) goto L_0x0036
            if (r1 == r6) goto L_0x0030
            if (r1 == r5) goto L_0x002c
            if (r1 == r4) goto L_0x002c
            if (r1 == r3) goto L_0x0023
            if (r1 != r2) goto L_0x001b
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x0089
        L_0x001b:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L_0x0023:
            java.lang.Object r0 = r7.L$0
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x009b
        L_0x002c:
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x007b
        L_0x0030:
            kotlin.ResultKt.throwOnFailure(r8)     // Catch:{ IllegalStateException -> 0x0053 }
            goto L_0x0047
        L_0x0034:
            r8 = move-exception
            goto L_0x008c
        L_0x0036:
            kotlin.ResultKt.throwOnFailure(r8)
            org.videolan.resources.VLCInstance r8 = org.videolan.resources.VLCInstance.INSTANCE     // Catch:{ IllegalStateException -> 0x0053 }
            r1 = r7
            kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1     // Catch:{ IllegalStateException -> 0x0053 }
            r7.label = r6     // Catch:{ IllegalStateException -> 0x0053 }
            java.lang.Object r8 = r8.restart(r1)     // Catch:{ IllegalStateException -> 0x0053 }
            if (r8 != r0) goto L_0x0047
            return r0
        L_0x0047:
            r8 = r7
            kotlin.coroutines.Continuation r8 = (kotlin.coroutines.Continuation) r8
            r7.label = r5
            java.lang.Object r8 = org.videolan.vlc.gui.helpers.PreferenceUtilsKt.restartMediaPlayer(r8)
            if (r8 != r0) goto L_0x007b
            return r0
        L_0x0053:
            org.videolan.television.ui.preferences.PreferencesAdvanced r8 = r7.this$0     // Catch:{ all -> 0x0034 }
            android.app.Activity r8 = r8.getActivity()     // Catch:{ all -> 0x0034 }
            if (r8 == 0) goto L_0x0066
            android.content.Context r8 = (android.content.Context) r8     // Catch:{ all -> 0x0034 }
            int r1 = org.videolan.vlc.R.string.custom_libvlc_options_invalid     // Catch:{ all -> 0x0034 }
            android.widget.Toast r8 = android.widget.Toast.makeText(r8, r1, r6)     // Catch:{ all -> 0x0034 }
            r8.show()     // Catch:{ all -> 0x0034 }
        L_0x0066:
            android.content.SharedPreferences r8 = r7.$sharedPreferences     // Catch:{ all -> 0x0034 }
            java.lang.String r1 = "custom_libvlc_options"
            java.lang.String r5 = ""
            org.videolan.tools.SettingsKt.putSingle(r8, r1, r5)     // Catch:{ all -> 0x0034 }
            r8 = r7
            kotlin.coroutines.Continuation r8 = (kotlin.coroutines.Continuation) r8
            r7.label = r4
            java.lang.Object r8 = org.videolan.vlc.gui.helpers.PreferenceUtilsKt.restartMediaPlayer(r8)
            if (r8 != r0) goto L_0x007b
            return r0
        L_0x007b:
            org.videolan.television.ui.preferences.PreferencesAdvanced r8 = r7.this$0
            r1 = r7
            kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
            r7.label = r2
            java.lang.Object r8 = r8.restartLibVLC(r1)
            if (r8 != r0) goto L_0x0089
            return r0
        L_0x0089:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        L_0x008c:
            r1 = r7
            kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
            r7.L$0 = r8
            r7.label = r3
            java.lang.Object r1 = org.videolan.vlc.gui.helpers.PreferenceUtilsKt.restartMediaPlayer(r1)
            if (r1 != r0) goto L_0x009a
            return r0
        L_0x009a:
            r0 = r8
        L_0x009b:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.television.ui.preferences.PreferencesAdvanced$onSharedPreferenceChanged$3.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
