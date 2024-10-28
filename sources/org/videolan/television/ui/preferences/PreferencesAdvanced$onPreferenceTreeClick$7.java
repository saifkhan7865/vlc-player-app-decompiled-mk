package org.videolan.television.ui.preferences;

import java.io.File;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.television.ui.preferences.PreferencesAdvanced$onPreferenceTreeClick$7", f = "PreferencesAdvanced.kt", i = {}, l = {259, 260}, m = "invokeSuspend", n = {}, s = {})
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

    /* JADX WARNING: Removed duplicated region for block: B:18:0x007f  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0082  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r8) {
        /*
            r7 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r7.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L_0x001e
            if (r1 == r3) goto L_0x001a
            if (r1 != r2) goto L_0x0012
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x006d
        L_0x0012:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L_0x001a:
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x0047
        L_0x001e:
            kotlin.ResultKt.throwOnFailure(r8)
            org.videolan.vlc.gui.helpers.hf.StoragePermissionsDelegate$Companion r8 = org.videolan.vlc.gui.helpers.hf.StoragePermissionsDelegate.Companion
            org.videolan.television.ui.preferences.PreferencesAdvanced r1 = r7.this$0
            android.app.Activity r1 = r1.getActivity()
            java.lang.String r4 = "null cannot be cast to non-null type androidx.fragment.app.FragmentActivity"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1, r4)
            androidx.fragment.app.FragmentActivity r1 = (androidx.fragment.app.FragmentActivity) r1
            java.io.File r4 = r7.$dst
            android.net.Uri r4 = android.net.Uri.fromFile(r4)
            java.lang.String r5 = "fromFile(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r5)
            r5 = r7
            kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
            r7.label = r3
            java.lang.Object r8 = r8.getWritePermission((androidx.fragment.app.FragmentActivity) r1, (android.net.Uri) r4, (kotlin.coroutines.Continuation<? super java.lang.Boolean>) r5)
            if (r8 != r0) goto L_0x0047
            return r0
        L_0x0047:
            java.lang.Boolean r8 = (java.lang.Boolean) r8
            boolean r8 = r8.booleanValue()
            if (r8 == 0) goto L_0x0091
            kotlinx.coroutines.CoroutineDispatcher r8 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r8 = (kotlin.coroutines.CoroutineContext) r8
            org.videolan.television.ui.preferences.PreferencesAdvanced$onPreferenceTreeClick$7$copied$1 r1 = new org.videolan.television.ui.preferences.PreferencesAdvanced$onPreferenceTreeClick$7$copied$1
            org.videolan.television.ui.preferences.PreferencesAdvanced r4 = r7.this$0
            java.io.File r5 = r7.$dst
            r6 = 0
            r1.<init>(r4, r5, r6)
            kotlin.jvm.functions.Function2 r1 = (kotlin.jvm.functions.Function2) r1
            r4 = r7
            kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
            r7.label = r2
            java.lang.Object r8 = kotlinx.coroutines.BuildersKt.withContext(r8, r1, r4)
            if (r8 != r0) goto L_0x006d
            return r0
        L_0x006d:
            java.lang.Boolean r8 = (java.lang.Boolean) r8
            boolean r8 = r8.booleanValue()
            org.videolan.television.ui.preferences.PreferencesAdvanced r0 = r7.this$0
            android.app.Activity r0 = r0.getActivity()
            android.content.Context r0 = (android.content.Context) r0
            org.videolan.television.ui.preferences.PreferencesAdvanced r1 = r7.this$0
            if (r8 == 0) goto L_0x0082
            int r8 = org.videolan.vlc.R.string.dump_db_succes
            goto L_0x0084
        L_0x0082:
            int r8 = org.videolan.vlc.R.string.dump_db_failure
        L_0x0084:
            java.lang.String r8 = r1.getString(r8)
            java.lang.CharSequence r8 = (java.lang.CharSequence) r8
            android.widget.Toast r8 = android.widget.Toast.makeText(r0, r8, r3)
            r8.show()
        L_0x0091:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.television.ui.preferences.PreferencesAdvanced$onPreferenceTreeClick$7.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
