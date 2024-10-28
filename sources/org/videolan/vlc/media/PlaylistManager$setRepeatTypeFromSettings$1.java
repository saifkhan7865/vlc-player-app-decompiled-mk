package org.videolan.vlc.media;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.MutableStateFlow;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.media.PlaylistManager$setRepeatTypeFromSettings$1", f = "PlaylistManager.kt", i = {}, l = {459}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: PlaylistManager.kt */
final class PlaylistManager$setRepeatTypeFromSettings$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ PlaylistManager this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PlaylistManager$setRepeatTypeFromSettings$1(PlaylistManager playlistManager, Continuation<? super PlaylistManager$setRepeatTypeFromSettings$1> continuation) {
        super(2, continuation);
        this.this$0 = playlistManager;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PlaylistManager$setRepeatTypeFromSettings$1(this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PlaylistManager$setRepeatTypeFromSettings$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        int i;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            MutableStateFlow<Integer> repeating = PlaylistManager.Companion.getRepeating();
            MediaWrapper currentMedia = this.this$0.getCurrentMedia();
            if (currentMedia == null || currentMedia.getType() != 0) {
                i = this.this$0.getSettings().getInt("audio_repeat_mode", 0);
            } else {
                i = this.this$0.getSettings().getInt("video_repeat_mode", 0);
            }
            this.label = 1;
            if (repeating.emit(Boxing.boxInt(i), this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i2 == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
