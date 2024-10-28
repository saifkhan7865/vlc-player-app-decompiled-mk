package org.videolan.vlc;

import android.content.Intent;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.StartActivity$startPlaybackFromApp$1", f = "StartActivity.kt", i = {}, l = {279, 285, 294}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: StartActivity.kt */
final class StartActivity$startPlaybackFromApp$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Intent $intent;
    int label;
    final /* synthetic */ StartActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    StartActivity$startPlaybackFromApp$1(StartActivity startActivity, Intent intent, Continuation<? super StartActivity$startPlaybackFromApp$1> continuation) {
        super(2, continuation);
        this.this$0 = startActivity;
        this.$intent = intent;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new StartActivity$startPlaybackFromApp$1(this.this$0, this.$intent, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((StartActivity$startPlaybackFromApp$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0088, code lost:
        if (((java.lang.Boolean) r14).booleanValue() == false) goto L_0x0144;
     */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x006a  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x009c A[SYNTHETIC, Splitter:B:30:0x009c] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00ca  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x013f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r14) {
        /*
            r13 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r13.label
            r2 = 3
            r3 = 2
            r4 = 1
            if (r1 == 0) goto L_0x0026
            if (r1 == r4) goto L_0x0022
            if (r1 == r3) goto L_0x001e
            if (r1 != r2) goto L_0x0016
            kotlin.ResultKt.throwOnFailure(r14)
            goto L_0x013b
        L_0x0016:
            java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r14.<init>(r0)
            throw r14
        L_0x001e:
            kotlin.ResultKt.throwOnFailure(r14)
            goto L_0x0082
        L_0x0022:
            kotlin.ResultKt.throwOnFailure(r14)
            goto L_0x003f
        L_0x0026:
            kotlin.ResultKt.throwOnFailure(r14)
            int r14 = android.os.Build.VERSION.SDK_INT
            r1 = 28
            if (r14 != r1) goto L_0x004f
            org.videolan.vlc.StartActivity r14 = r13.this$0
            android.content.Context r14 = (android.content.Context) r14
            r1 = r13
            kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
            r13.label = r4
            java.lang.Object r14 = org.videolan.tools.KotlinExtensionsKt.awaitAppIsForegroung(r14, r1)
            if (r14 != r0) goto L_0x003f
            return r0
        L_0x003f:
            java.lang.Boolean r14 = (java.lang.Boolean) r14
            boolean r14 = r14.booleanValue()
            if (r14 != 0) goto L_0x004f
            org.videolan.vlc.StartActivity r14 = r13.this$0
            r14.finish()
            kotlin.Unit r14 = kotlin.Unit.INSTANCE
            return r14
        L_0x004f:
            android.content.Intent r14 = r13.$intent
            r1 = -33554433(0xfffffffffdffffff, float:-4.2535293E37)
            int r5 = r14.getFlags()
            r1 = r1 & r5
            r14.setFlags(r1)
            org.videolan.vlc.util.Permissions r14 = org.videolan.vlc.util.Permissions.INSTANCE
            org.videolan.vlc.StartActivity r1 = r13.this$0
            android.content.Context r1 = r1.getApplicationContext()
            boolean r14 = r14.canReadStorage(r1)
            if (r14 != 0) goto L_0x008a
            org.videolan.vlc.gui.helpers.hf.StoragePermissionsDelegate$Companion r5 = org.videolan.vlc.gui.helpers.hf.StoragePermissionsDelegate.Companion
            org.videolan.vlc.StartActivity r14 = r13.this$0
            r6 = r14
            androidx.fragment.app.FragmentActivity r6 = (androidx.fragment.app.FragmentActivity) r6
            r10 = r13
            kotlin.coroutines.Continuation r10 = (kotlin.coroutines.Continuation) r10
            r13.label = r3
            r7 = 0
            r8 = 0
            r9 = 0
            r11 = 7
            r12 = 0
            java.lang.Object r14 = org.videolan.vlc.gui.helpers.hf.StoragePermissionsDelegate.Companion.getStoragePermission$default(r5, r6, r7, r8, r9, r10, r11, r12)
            if (r14 != r0) goto L_0x0082
            return r0
        L_0x0082:
            java.lang.Boolean r14 = (java.lang.Boolean) r14
            boolean r14 = r14.booleanValue()
            if (r14 == 0) goto L_0x0144
        L_0x008a:
            android.content.Intent r14 = r13.$intent
            java.lang.String r14 = r14.getType()
            r1 = 0
            r5 = 0
            if (r14 == 0) goto L_0x00ca
            java.lang.String r6 = "video"
            boolean r14 = kotlin.text.StringsKt.startsWith$default(r14, r6, r1, r3, r5)
            if (r14 != r4) goto L_0x00ca
            org.videolan.vlc.StartActivity r14 = r13.this$0     // Catch:{ SecurityException -> 0x00ba }
            android.content.Intent r0 = r13.$intent     // Catch:{ SecurityException -> 0x00ba }
            r1 = r14
            android.content.Context r1 = (android.content.Context) r1     // Catch:{ SecurityException -> 0x00ba }
            java.lang.Class<org.videolan.vlc.gui.video.VideoPlayerActivity> r2 = org.videolan.vlc.gui.video.VideoPlayerActivity.class
            android.content.Intent r0 = r0.setClass(r1, r2)     // Catch:{ SecurityException -> 0x00ba }
            java.lang.String r1 = "from_external"
            r0.putExtra(r1, r4)     // Catch:{ SecurityException -> 0x00ba }
            org.videolan.vlc.util.Util r1 = org.videolan.vlc.util.Util.INSTANCE     // Catch:{ SecurityException -> 0x00ba }
            android.os.Bundle r1 = r1.getFullScreenBundle()     // Catch:{ SecurityException -> 0x00ba }
            r14.startActivityForResult(r0, r4, r1)     // Catch:{ SecurityException -> 0x00ba }
            kotlin.Unit r14 = kotlin.Unit.INSTANCE     // Catch:{ SecurityException -> 0x00ba }
            return r14
        L_0x00ba:
            android.content.Intent r14 = r13.$intent
            android.net.Uri r14 = r14.getData()
            if (r14 == 0) goto L_0x0144
            org.videolan.vlc.media.MediaUtils r0 = org.videolan.vlc.media.MediaUtils.INSTANCE
            r0.openMediaNoUi(r14)
            goto L_0x0144
        L_0x00ca:
            android.content.Intent r14 = r13.$intent
            android.net.Uri r14 = r14.getData()
            if (r14 == 0) goto L_0x00d7
            java.lang.String r14 = r14.getAuthority()
            goto L_0x00d8
        L_0x00d7:
            r14 = r5
        L_0x00d8:
            org.videolan.vlc.StartActivity r4 = r13.this$0
            int r6 = org.videolan.vlc.R.string.tv_provider_authority
            java.lang.String r4 = r4.getString(r6)
            boolean r14 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r14, (java.lang.Object) r4)
            if (r14 == 0) goto L_0x00f6
            org.videolan.vlc.media.MediaUtils r14 = org.videolan.vlc.media.MediaUtils.INSTANCE
            org.videolan.vlc.StartActivity r0 = r13.this$0
            android.content.Context r0 = (android.content.Context) r0
            android.content.Intent r1 = r13.$intent
            android.net.Uri r1 = r1.getData()
            r14.openMediaNoUiFromTvContent(r0, r1)
            goto L_0x0144
        L_0x00f6:
            android.content.Intent r14 = r13.$intent
            android.net.Uri r14 = r14.getData()
            if (r14 == 0) goto L_0x0103
            java.lang.String r14 = r14.getAuthority()
            goto L_0x0104
        L_0x0103:
            r14 = r5
        L_0x0104:
            java.lang.String r4 = "skip_to"
            boolean r14 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r14, (java.lang.Object) r4)
            if (r14 == 0) goto L_0x0120
            org.videolan.vlc.PlaybackService$Companion r14 = org.videolan.vlc.PlaybackService.Companion
            org.videolan.vlc.PlaybackService r14 = r14.getInstance()
            if (r14 == 0) goto L_0x0144
            android.content.Intent r0 = r13.$intent
            java.lang.String r2 = "index"
            int r0 = r0.getIntExtra(r2, r1)
            org.videolan.vlc.PlaybackService.playIndex$default(r14, r0, r1, r3, r5)
            goto L_0x0144
        L_0x0120:
            kotlinx.coroutines.CoroutineDispatcher r14 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r14 = (kotlin.coroutines.CoroutineContext) r14
            org.videolan.vlc.StartActivity$startPlaybackFromApp$1$3 r1 = new org.videolan.vlc.StartActivity$startPlaybackFromApp$1$3
            android.content.Intent r3 = r13.$intent
            r1.<init>(r3, r5)
            kotlin.jvm.functions.Function2 r1 = (kotlin.jvm.functions.Function2) r1
            r3 = r13
            kotlin.coroutines.Continuation r3 = (kotlin.coroutines.Continuation) r3
            r13.label = r2
            java.lang.Object r14 = kotlinx.coroutines.BuildersKt.withContext(r14, r1, r3)
            if (r14 != r0) goto L_0x013b
            return r0
        L_0x013b:
            android.net.Uri r14 = (android.net.Uri) r14
            if (r14 == 0) goto L_0x0144
            org.videolan.vlc.media.MediaUtils r0 = org.videolan.vlc.media.MediaUtils.INSTANCE
            r0.openMediaNoUi(r14)
        L_0x0144:
            org.videolan.vlc.StartActivity r14 = r13.this$0
            r14.finish()
            kotlin.Unit r14 = kotlin.Unit.INSTANCE
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.StartActivity$startPlaybackFromApp$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
