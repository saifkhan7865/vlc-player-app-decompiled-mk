package org.videolan.vlc.gui.audio;

import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.videolan.vlc.databinding.AudioPlayerBinding;
import org.videolan.vlc.viewmodels.PlaybackProgress;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.audio.AudioPlayer$updateProgress$1", f = "AudioPlayer.kt", i = {}, l = {505}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: AudioPlayer.kt */
final class AudioPlayer$updateProgress$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ PlaybackProgress $progress;
    int label;
    final /* synthetic */ AudioPlayer this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AudioPlayer$updateProgress$1(AudioPlayer audioPlayer, PlaybackProgress playbackProgress, Continuation<? super AudioPlayer$updateProgress$1> continuation) {
        super(2, continuation);
        this.this$0 = audioPlayer;
        this.$progress = playbackProgress;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AudioPlayer$updateProgress$1(this.this$0, this.$progress, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AudioPlayer$updateProgress$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        AudioPlayerBinding audioPlayerBinding = null;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            obj = BuildersKt.withContext(Dispatchers.getDefault(), new AudioPlayer$updateProgress$1$text$1(this.this$0, this.$progress, (Continuation<? super AudioPlayer$updateProgress$1$text$1>) null), this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        Pair pair = (Pair) obj;
        AudioPlayerBinding access$getBinding$p = this.this$0.binding;
        if (access$getBinding$p == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            access$getBinding$p = null;
        }
        access$getBinding$p.audioPlayProgress.setText((CharSequence) pair.getFirst());
        AudioPlayerBinding access$getBinding$p2 = this.this$0.binding;
        if (access$getBinding$p2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            audioPlayerBinding = access$getBinding$p2;
        }
        audioPlayerBinding.audioPlayProgress.setContentDescription((CharSequence) pair.getSecond());
        return Unit.INSTANCE;
    }
}
