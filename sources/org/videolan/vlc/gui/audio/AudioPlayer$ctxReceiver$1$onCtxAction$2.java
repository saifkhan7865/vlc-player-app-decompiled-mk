package org.videolan.vlc.gui.audio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
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
import org.videolan.vlc.util.KextensionsKt;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.audio.AudioPlayer$ctxReceiver$1$onCtxAction$2", f = "AudioPlayer.kt", i = {}, l = {359}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: AudioPlayer.kt */
final class AudioPlayer$ctxReceiver$1$onCtxAction$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ int $position;
    int label;
    final /* synthetic */ AudioPlayer this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AudioPlayer$ctxReceiver$1$onCtxAction$2(AudioPlayer audioPlayer, int i, Continuation<? super AudioPlayer$ctxReceiver$1$onCtxAction$2> continuation) {
        super(2, continuation);
        this.this$0 = audioPlayer;
        this.$position = i;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AudioPlayer$ctxReceiver$1$onCtxAction$2(this.this$0, this.$position, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AudioPlayer$ctxReceiver$1$onCtxAction$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FragmentActivity requireActivity = this.this$0.requireActivity();
            Intrinsics.checkNotNull(requireActivity, "null cannot be cast to non-null type androidx.appcompat.app.AppCompatActivity");
            AppCompatActivity appCompatActivity = (AppCompatActivity) requireActivity;
            PlaylistAdapter access$getPlaylistAdapter$p = this.this$0.playlistAdapter;
            if (access$getPlaylistAdapter$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("playlistAdapter");
                access$getPlaylistAdapter$p = null;
            }
            this.label = 1;
            if (KextensionsKt.share(appCompatActivity, access$getPlaylistAdapter$p.getItem(this.$position), (Continuation<? super Unit>) this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
