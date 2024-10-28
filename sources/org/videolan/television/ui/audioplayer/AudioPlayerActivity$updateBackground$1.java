package org.videolan.television.ui.audioplayer;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.television.ui.audioplayer.AudioPlayerActivity$updateBackground$1", f = "AudioPlayerActivity.kt", i = {1}, l = {213, 219}, m = "invokeSuspend", n = {"cover"}, s = {"L$0"})
/* compiled from: AudioPlayerActivity.kt */
final class AudioPlayerActivity$updateBackground$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;
    final /* synthetic */ AudioPlayerActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AudioPlayerActivity$updateBackground$1(AudioPlayerActivity audioPlayerActivity, Continuation<? super AudioPlayerActivity$updateBackground$1> continuation) {
        super(2, continuation);
        this.this$0 = audioPlayerActivity;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AudioPlayerActivity$updateBackground$1(this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AudioPlayerActivity$updateBackground$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:46:0x0100  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0104  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r13) {
        /*
            r12 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r12.label
            r2 = 2
            r3 = 1
            java.lang.String r4 = "binding"
            r5 = 0
            if (r1 == 0) goto L_0x0026
            if (r1 == r3) goto L_0x0022
            if (r1 != r2) goto L_0x001a
            java.lang.Object r0 = r12.L$0
            android.graphics.Bitmap r0 = (android.graphics.Bitmap) r0
            kotlin.ResultKt.throwOnFailure(r13)
            goto L_0x00f8
        L_0x001a:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r0)
            throw r13
        L_0x0022:
            kotlin.ResultKt.throwOnFailure(r13)
            goto L_0x0073
        L_0x0026:
            kotlin.ResultKt.throwOnFailure(r13)
            org.videolan.television.ui.audioplayer.AudioPlayerActivity r13 = r12.this$0
            org.videolan.television.databinding.TvAudioPlayerBinding r13 = r13.binding
            if (r13 != 0) goto L_0x0035
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)
            r13 = r5
        L_0x0035:
            androidx.appcompat.widget.AppCompatImageView r13 = r13.albumCover
            int r13 = r13.getWidth()
            if (r13 <= 0) goto L_0x0050
            org.videolan.television.ui.audioplayer.AudioPlayerActivity r13 = r12.this$0
            org.videolan.television.databinding.TvAudioPlayerBinding r13 = r13.binding
            if (r13 != 0) goto L_0x0049
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)
            r13 = r5
        L_0x0049:
            androidx.appcompat.widget.AppCompatImageView r13 = r13.albumCover
            int r13 = r13.getWidth()
            goto L_0x0058
        L_0x0050:
            org.videolan.television.ui.audioplayer.AudioPlayerActivity r13 = r12.this$0
            android.app.Activity r13 = (android.app.Activity) r13
            int r13 = org.videolan.vlc.util.KextensionsKt.getScreenWidth(r13)
        L_0x0058:
            kotlinx.coroutines.CoroutineDispatcher r1 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r1 = (kotlin.coroutines.CoroutineContext) r1
            org.videolan.television.ui.audioplayer.AudioPlayerActivity$updateBackground$1$cover$1 r6 = new org.videolan.television.ui.audioplayer.AudioPlayerActivity$updateBackground$1$cover$1
            org.videolan.television.ui.audioplayer.AudioPlayerActivity r7 = r12.this$0
            r6.<init>(r7, r13, r5)
            kotlin.jvm.functions.Function2 r6 = (kotlin.jvm.functions.Function2) r6
            r13 = r12
            kotlin.coroutines.Continuation r13 = (kotlin.coroutines.Continuation) r13
            r12.label = r3
            java.lang.Object r13 = kotlinx.coroutines.BuildersKt.withContext(r1, r6, r13)
            if (r13 != r0) goto L_0x0073
            return r0
        L_0x0073:
            android.graphics.Bitmap r13 = (android.graphics.Bitmap) r13
            if (r13 != 0) goto L_0x00af
            org.videolan.television.ui.audioplayer.AudioPlayerActivity r13 = r12.this$0
            org.videolan.television.databinding.TvAudioPlayerBinding r13 = r13.binding
            if (r13 != 0) goto L_0x0083
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)
            r13 = r5
        L_0x0083:
            androidx.appcompat.widget.AppCompatImageView r13 = r13.albumCover
            int r0 = org.videolan.television.R.drawable.ic_song_big
            r13.setImageResource(r0)
            org.videolan.television.ui.audioplayer.AudioPlayerActivity r13 = r12.this$0
            org.videolan.television.databinding.TvAudioPlayerBinding r13 = r13.binding
            if (r13 != 0) goto L_0x0096
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)
            r13 = r5
        L_0x0096:
            androidx.appcompat.widget.AppCompatImageView r13 = r13.background
            r13.clearColorFilter()
            org.videolan.television.ui.audioplayer.AudioPlayerActivity r13 = r12.this$0
            org.videolan.television.databinding.TvAudioPlayerBinding r13 = r13.binding
            if (r13 != 0) goto L_0x00a7
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)
            goto L_0x00a8
        L_0x00a7:
            r5 = r13
        L_0x00a8:
            androidx.appcompat.widget.AppCompatImageView r13 = r5.background
            r0 = 0
            r13.setImageResource(r0)
            goto L_0x010a
        L_0x00af:
            org.videolan.vlc.gui.helpers.UiTools r6 = org.videolan.vlc.gui.helpers.UiTools.INSTANCE
            org.videolan.television.ui.audioplayer.AudioPlayerActivity r1 = r12.this$0
            org.videolan.television.databinding.TvAudioPlayerBinding r1 = r1.binding
            if (r1 != 0) goto L_0x00bd
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)
            r1 = r5
        L_0x00bd:
            androidx.appcompat.widget.AppCompatImageView r1 = r1.background
            java.lang.String r3 = "background"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r3)
            r7 = r1
            android.widget.ImageView r7 = (android.widget.ImageView) r7
            org.videolan.vlc.gui.helpers.UiTools r1 = org.videolan.vlc.gui.helpers.UiTools.INSTANCE
            org.videolan.television.ui.audioplayer.AudioPlayerActivity r3 = r12.this$0
            org.videolan.television.databinding.TvAudioPlayerBinding r3 = r3.binding
            if (r3 != 0) goto L_0x00d5
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)
            r3 = r5
        L_0x00d5:
            androidx.appcompat.widget.AppCompatImageView r3 = r3.background
            android.content.Context r3 = r3.getContext()
            java.lang.String r8 = "getContext(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r8)
            int r8 = org.videolan.television.R.attr.audio_player_background_tint
            int r10 = r1.getColorFromAttribute(r3, r8)
            r11 = r12
            kotlin.coroutines.Continuation r11 = (kotlin.coroutines.Continuation) r11
            r12.L$0 = r13
            r12.label = r2
            r9 = 1097859072(0x41700000, float:15.0)
            r8 = r13
            java.lang.Object r1 = r6.blurView(r7, r8, r9, r10, r11)
            if (r1 != r0) goto L_0x00f7
            return r0
        L_0x00f7:
            r0 = r13
        L_0x00f8:
            org.videolan.television.ui.audioplayer.AudioPlayerActivity r13 = r12.this$0
            org.videolan.television.databinding.TvAudioPlayerBinding r13 = r13.binding
            if (r13 != 0) goto L_0x0104
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)
            goto L_0x0105
        L_0x0104:
            r5 = r13
        L_0x0105:
            androidx.appcompat.widget.AppCompatImageView r13 = r5.albumCover
            r13.setImageBitmap(r0)
        L_0x010a:
            kotlin.Unit r13 = kotlin.Unit.INSTANCE
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.television.ui.audioplayer.AudioPlayerActivity$updateBackground$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
