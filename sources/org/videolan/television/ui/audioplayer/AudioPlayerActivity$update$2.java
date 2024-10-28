package org.videolan.television.ui.audioplayer;

import androidx.appcompat.widget.AppCompatImageView;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.television.R;
import org.videolan.television.databinding.TvAudioPlayerBinding;
import org.videolan.vlc.viewmodels.PlayerState;
import org.videolan.vlc.viewmodels.PlaylistModel;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.television.ui.audioplayer.AudioPlayerActivity$update$2", f = "AudioPlayerActivity.kt", i = {}, l = {194}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: AudioPlayerActivity.kt */
final class AudioPlayerActivity$update$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MediaWrapper $mw;
    final /* synthetic */ PlayerState $state;
    int label;
    final /* synthetic */ AudioPlayerActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AudioPlayerActivity$update$2(AudioPlayerActivity audioPlayerActivity, PlayerState playerState, MediaWrapper mediaWrapper, Continuation<? super AudioPlayerActivity$update$2> continuation) {
        super(2, continuation);
        this.this$0 = audioPlayerActivity;
        this.$state = playerState;
        this.$mw = mediaWrapper;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AudioPlayerActivity$update$2(this.this$0, this.$state, this.$mw, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AudioPlayerActivity$update$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        int i;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        TvAudioPlayerBinding tvAudioPlayerBinding = null;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            PlaylistModel access$getModel$p = this.this$0.model;
            if (access$getModel$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("model");
                access$getModel$p = null;
            }
            this.label = 1;
            obj = access$getModel$p.switchToVideo(this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i2 == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        if (((Boolean) obj).booleanValue()) {
            this.this$0.finish();
            return Unit.INSTANCE;
        }
        TvAudioPlayerBinding access$getBinding$p = this.this$0.binding;
        if (access$getBinding$p == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            access$getBinding$p = null;
        }
        access$getBinding$p.mediaTitle.setText(this.$state.getTitle());
        TvAudioPlayerBinding access$getBinding$p2 = this.this$0.binding;
        if (access$getBinding$p2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            access$getBinding$p2 = null;
        }
        access$getBinding$p2.mediaArtist.setText(this.$state.getArtist());
        TvAudioPlayerBinding access$getBinding$p3 = this.this$0.binding;
        if (access$getBinding$p3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            access$getBinding$p3 = null;
        }
        AppCompatImageView appCompatImageView = access$getBinding$p3.buttonShuffle;
        if (this.this$0.shuffling) {
            i = R.drawable.ic_shuffle_on;
        } else {
            i = R.drawable.ic_shuffle_audio;
        }
        appCompatImageView.setImageResource(i);
        TvAudioPlayerBinding access$getBinding$p4 = this.this$0.binding;
        if (access$getBinding$p4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            tvAudioPlayerBinding = access$getBinding$p4;
        }
        AppCompatImageView appCompatImageView2 = tvAudioPlayerBinding.buttonShuffle;
        AudioPlayerActivity audioPlayerActivity = this.this$0;
        appCompatImageView2.setContentDescription(audioPlayerActivity.getString(audioPlayerActivity.shuffling ? org.videolan.vlc.R.string.shuffle_on : org.videolan.vlc.R.string.shuffle));
        if (this.$mw == null || Intrinsics.areEqual((Object) this.this$0.currentCoverArt, (Object) this.$mw.getArtworkMrl())) {
            return Unit.INSTANCE;
        }
        this.this$0.currentCoverArt = this.$mw.getArtworkMrl();
        Job unused = this.this$0.updateBackground();
        return Unit.INSTANCE;
    }
}
