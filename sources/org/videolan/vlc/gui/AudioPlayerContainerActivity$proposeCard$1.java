package org.videolan.vlc.gui;

import android.content.Context;
import android.view.View;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.vlc.PlaybackService;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.AudioPlayerContainerActivity$proposeCard$1", f = "AudioPlayerContainerActivity.kt", i = {}, l = {704, 739}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: AudioPlayerContainerActivity.kt */
final class AudioPlayerContainerActivity$proposeCard$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ AudioPlayerContainerActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AudioPlayerContainerActivity$proposeCard$1(AudioPlayerContainerActivity audioPlayerContainerActivity, Continuation<? super AudioPlayerContainerActivity$proposeCard$1> continuation) {
        super(2, continuation);
        this.this$0 = audioPlayerContainerActivity;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AudioPlayerContainerActivity$proposeCard$1(this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AudioPlayerContainerActivity$proposeCard$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0079  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x007c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r8) {
        /*
            r7 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r7.label
            r2 = 2
            r3 = 0
            r4 = 1
            if (r1 == 0) goto L_0x001f
            if (r1 == r4) goto L_0x001b
            if (r1 != r2) goto L_0x0013
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x0075
        L_0x0013:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L_0x001b:
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x0030
        L_0x001f:
            kotlin.ResultKt.throwOnFailure(r8)
            r8 = r7
            kotlin.coroutines.Continuation r8 = (kotlin.coroutines.Continuation) r8
            r7.label = r4
            r5 = 1000(0x3e8, double:4.94E-321)
            java.lang.Object r8 = kotlinx.coroutines.DelayKt.delay(r5, r8)
            if (r8 != r0) goto L_0x0030
            return r0
        L_0x0030:
            org.videolan.vlc.media.PlaylistManager$Companion r8 = org.videolan.vlc.media.PlaylistManager.Companion
            androidx.lifecycle.MutableLiveData r8 = r8.getShowAudioPlayer()
            java.lang.Object r8 = r8.getValue()
            java.lang.Boolean r1 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r4)
            boolean r8 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r8, (java.lang.Object) r1)
            if (r8 == 0) goto L_0x0047
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        L_0x0047:
            org.videolan.vlc.gui.AudioPlayerContainerActivity r8 = r7.this$0
            android.content.SharedPreferences r8 = r8.getSettings()
            java.lang.String r1 = "current_song"
            java.lang.String r8 = r8.getString(r1, r3)
            if (r8 != 0) goto L_0x0058
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        L_0x0058:
            org.videolan.vlc.gui.AudioPlayerContainerActivity r1 = r7.this$0
            android.content.Context r1 = (android.content.Context) r1
            kotlinx.coroutines.CoroutineDispatcher r5 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r5 = (kotlin.coroutines.CoroutineContext) r5
            org.videolan.vlc.gui.AudioPlayerContainerActivity$proposeCard$1$invokeSuspend$$inlined$getFromMl$1 r6 = new org.videolan.vlc.gui.AudioPlayerContainerActivity$proposeCard$1$invokeSuspend$$inlined$getFromMl$1
            r6.<init>(r1, r3, r8)
            kotlin.jvm.functions.Function2 r6 = (kotlin.jvm.functions.Function2) r6
            r8 = r7
            kotlin.coroutines.Continuation r8 = (kotlin.coroutines.Continuation) r8
            r7.label = r2
            java.lang.Object r8 = kotlinx.coroutines.BuildersKt.withContext(r5, r6, r8)
            if (r8 != r0) goto L_0x0075
            return r0
        L_0x0075:
            org.videolan.medialibrary.interfaces.media.MediaWrapper r8 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r8
            if (r8 != 0) goto L_0x007c
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        L_0x007c:
            org.videolan.vlc.gui.AudioPlayerContainerActivity r0 = r7.this$0
            android.content.SharedPreferences r0 = r0.getSettings()
            java.lang.String r1 = "audio_resume_playback"
            boolean r0 = r0.getBoolean(r1, r4)
            if (r0 != 0) goto L_0x008d
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        L_0x008d:
            java.lang.String r8 = r8.getTitle()
            org.videolan.vlc.gui.AudioPlayerContainerActivity r0 = r7.this$0
            r1 = r0
            org.videolan.vlc.gui.BaseActivity r1 = (org.videolan.vlc.gui.BaseActivity) r1
            r2 = 0
            android.view.View r1 = org.videolan.vlc.gui.BaseActivity.getSnackAnchorView$default(r1, r2, r4, r3)
            if (r1 != 0) goto L_0x00a5
            org.videolan.vlc.gui.AudioPlayerContainerActivity r1 = r7.this$0
            com.google.android.material.appbar.AppBarLayout r1 = r1.getAppBarLayout()
            android.view.View r1 = (android.view.View) r1
        L_0x00a5:
            org.videolan.vlc.gui.AudioPlayerContainerActivity r5 = r7.this$0
            int r6 = org.videolan.vlc.R.string.resume_card_message
            java.lang.Object[] r4 = new java.lang.Object[r4]
            r4[r2] = r8
            java.lang.String r8 = r5.getString(r6, r4)
            java.lang.CharSequence r8 = (java.lang.CharSequence) r8
            com.google.android.material.snackbar.Snackbar r8 = com.google.android.material.snackbar.Snackbar.make((android.view.View) r1, (java.lang.CharSequence) r8, (int) r2)
            int r1 = org.videolan.vlc.R.string.play
            org.videolan.vlc.gui.AudioPlayerContainerActivity$proposeCard$1$$ExternalSyntheticLambda0 r2 = new org.videolan.vlc.gui.AudioPlayerContainerActivity$proposeCard$1$$ExternalSyntheticLambda0
            r2.<init>()
            com.google.android.material.snackbar.Snackbar r8 = r8.setAction((int) r1, (android.view.View.OnClickListener) r2)
            java.lang.String r1 = "setAction(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, r1)
            r0.resumeCard = r8
            org.videolan.vlc.gui.AudioPlayerContainerActivity r8 = r7.this$0
            com.google.android.material.snackbar.Snackbar r8 = r8.resumeCard
            if (r8 != 0) goto L_0x00d8
            java.lang.String r8 = "resumeCard"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r8)
            goto L_0x00d9
        L_0x00d8:
            r3 = r8
        L_0x00d9:
            r3.show()
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.AudioPlayerContainerActivity$proposeCard$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    /* access modifiers changed from: private */
    public static final void invokeSuspend$lambda$1(View view) {
        PlaybackService.Companion companion = PlaybackService.Companion;
        Context context = view.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        companion.loadLastAudio(context);
    }
}
