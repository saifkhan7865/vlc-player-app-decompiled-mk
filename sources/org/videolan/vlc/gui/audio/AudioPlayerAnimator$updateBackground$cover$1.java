package org.videolan.vlc.gui.audio;

import android.graphics.Bitmap;
import android.net.Uri;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.vlc.gui.helpers.AudioUtil;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "Landroid/graphics/Bitmap;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.audio.AudioPlayerAnimator$updateBackground$cover$1", f = "AudioPlayerAnimator.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: AudioPlayerAnimator.kt */
final class AudioPlayerAnimator$updateBackground$cover$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Bitmap>, Object> {
    final /* synthetic */ MediaWrapper $mw;
    final /* synthetic */ int $width;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AudioPlayerAnimator$updateBackground$cover$1(MediaWrapper mediaWrapper, int i, Continuation<? super AudioPlayerAnimator$updateBackground$cover$1> continuation) {
        super(2, continuation);
        this.$mw = mediaWrapper;
        this.$width = i;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AudioPlayerAnimator$updateBackground$cover$1(this.$mw, this.$width, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Bitmap> continuation) {
        return ((AudioPlayerAnimator$updateBackground$cover$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            return AudioUtil.INSTANCE.readCoverBitmap(Uri.decode(this.$mw.getArtworkMrl()), this.$width);
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
