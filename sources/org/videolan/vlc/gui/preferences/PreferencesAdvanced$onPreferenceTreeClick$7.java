package org.videolan.vlc.gui.preferences;

import java.io.File;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.preferences.PreferencesAdvanced$onPreferenceTreeClick$7", f = "PreferencesAdvanced.kt", i = {}, l = {257, 258}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: PreferencesAdvanced.kt */
final class PreferencesAdvanced$onPreferenceTreeClick$7 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ File $dst;
    int label;
    final /* synthetic */ PreferencesAdvanced this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PreferencesAdvanced$onPreferenceTreeClick$7(PreferencesAdvanced preferencesAdvanced, File file, Continuation<? super PreferencesAdvanced$onPreferenceTreeClick$7> continuation) {
        super(2, continuation);
        this.this$0 = preferencesAdvanced;
        this.$dst = file;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PreferencesAdvanced$onPreferenceTreeClick$7(this.this$0, this.$dst, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PreferencesAdvanced$onPreferenceTreeClick$7) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x006c  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0098  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r11) {
        /*
            r10 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r10.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L_0x001e
            if (r1 == r3) goto L_0x001a
            if (r1 != r2) goto L_0x0012
            kotlin.ResultKt.throwOnFailure(r11)
            goto L_0x0064
        L_0x0012:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r0)
            throw r11
        L_0x001a:
            kotlin.ResultKt.throwOnFailure(r11)
            goto L_0x003e
        L_0x001e:
            kotlin.ResultKt.throwOnFailure(r11)
            org.videolan.vlc.gui.helpers.hf.StoragePermissionsDelegate$Companion r11 = org.videolan.vlc.gui.helpers.hf.StoragePermissionsDelegate.Companion
            org.videolan.vlc.gui.preferences.PreferencesAdvanced r1 = r10.this$0
            androidx.fragment.app.Fragment r1 = (androidx.fragment.app.Fragment) r1
            java.io.File r4 = r10.$dst
            android.net.Uri r4 = android.net.Uri.fromFile(r4)
            java.lang.String r5 = "fromFile(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r5)
            r5 = r10
            kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
            r10.label = r3
            java.lang.Object r11 = r11.getWritePermission((androidx.fragment.app.Fragment) r1, (android.net.Uri) r4, (kotlin.coroutines.Continuation<? super java.lang.Boolean>) r5)
            if (r11 != r0) goto L_0x003e
            return r0
        L_0x003e:
            java.lang.Boolean r11 = (java.lang.Boolean) r11
            boolean r11 = r11.booleanValue()
            if (r11 == 0) goto L_0x00af
            kotlinx.coroutines.CoroutineDispatcher r11 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r11 = (kotlin.coroutines.CoroutineContext) r11
            org.videolan.vlc.gui.preferences.PreferencesAdvanced$onPreferenceTreeClick$7$copied$1 r1 = new org.videolan.vlc.gui.preferences.PreferencesAdvanced$onPreferenceTreeClick$7$copied$1
            org.videolan.vlc.gui.preferences.PreferencesAdvanced r4 = r10.this$0
            java.io.File r5 = r10.$dst
            r6 = 0
            r1.<init>(r4, r5, r6)
            kotlin.jvm.functions.Function2 r1 = (kotlin.jvm.functions.Function2) r1
            r4 = r10
            kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
            r10.label = r2
            java.lang.Object r11 = kotlinx.coroutines.BuildersKt.withContext(r11, r1, r4)
            if (r11 != r0) goto L_0x0064
            return r0
        L_0x0064:
            java.lang.Boolean r11 = (java.lang.Boolean) r11
            boolean r11 = r11.booleanValue()
            if (r11 == 0) goto L_0x0098
            org.videolan.vlc.gui.helpers.UiTools r4 = org.videolan.vlc.gui.helpers.UiTools.INSTANCE
            org.videolan.vlc.gui.preferences.PreferencesAdvanced r11 = r10.this$0
            androidx.fragment.app.FragmentActivity r11 = r11.requireActivity()
            org.videolan.vlc.gui.preferences.PreferencesAdvanced r0 = r10.this$0
            int r1 = org.videolan.vlc.R.string.dump_db_succes
            java.lang.String r6 = r0.getString(r1)
            int r8 = org.videolan.vlc.R.string.share
            kotlin.jvm.internal.Intrinsics.checkNotNull(r11)
            r5 = r11
            android.app.Activity r5 = (android.app.Activity) r5
            kotlin.jvm.internal.Intrinsics.checkNotNull(r6)
            org.videolan.vlc.gui.preferences.PreferencesAdvanced$onPreferenceTreeClick$7$1 r11 = new org.videolan.vlc.gui.preferences.PreferencesAdvanced$onPreferenceTreeClick$7$1
            org.videolan.vlc.gui.preferences.PreferencesAdvanced r0 = r10.this$0
            java.io.File r1 = r10.$dst
            r11.<init>(r0, r1)
            r9 = r11
            kotlin.jvm.functions.Function0 r9 = (kotlin.jvm.functions.Function0) r9
            r7 = 0
            r4.snackerConfirm(r5, r6, r7, r8, r9)
            goto L_0x00af
        L_0x0098:
            org.videolan.vlc.gui.preferences.PreferencesAdvanced r11 = r10.this$0
            android.content.Context r11 = r11.getContext()
            org.videolan.vlc.gui.preferences.PreferencesAdvanced r0 = r10.this$0
            int r1 = org.videolan.vlc.R.string.dump_db_failure
            java.lang.String r0 = r0.getString(r1)
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            android.widget.Toast r11 = android.widget.Toast.makeText(r11, r0, r3)
            r11.show()
        L_0x00af:
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.preferences.PreferencesAdvanced$onPreferenceTreeClick$7.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
