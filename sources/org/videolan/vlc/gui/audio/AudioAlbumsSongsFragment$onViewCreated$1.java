package org.videolan.vlc.gui.audio;

import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.DelayKt;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.vlc.viewmodels.PlaylistModel;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u001a\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004 \u0005*\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00030\u0003H@"}, d2 = {"<anonymous>", "", "it", "", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "kotlin.jvm.PlatformType"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.audio.AudioAlbumsSongsFragment$onViewCreated$1", f = "AudioAlbumsSongsFragment.kt", i = {}, l = {121}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: AudioAlbumsSongsFragment.kt */
final class AudioAlbumsSongsFragment$onViewCreated$1 extends SuspendLambda implements Function2<List<MediaWrapper>, Continuation<? super Unit>, Object> {
    final /* synthetic */ PlaylistModel $playlistModel;
    int label;
    final /* synthetic */ AudioAlbumsSongsFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AudioAlbumsSongsFragment$onViewCreated$1(AudioAlbumsSongsFragment audioAlbumsSongsFragment, PlaylistModel playlistModel, Continuation<? super AudioAlbumsSongsFragment$onViewCreated$1> continuation) {
        super(2, continuation);
        this.this$0 = audioAlbumsSongsFragment;
        this.$playlistModel = playlistModel;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AudioAlbumsSongsFragment$onViewCreated$1(this.this$0, this.$playlistModel, continuation);
    }

    public final Object invoke(List<MediaWrapper> list, Continuation<? super Unit> continuation) {
        return ((AudioAlbumsSongsFragment$onViewCreated$1) create(list, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            AudioBrowserAdapter access$getSongsAdapter$p = this.this$0.songsAdapter;
            if (access$getSongsAdapter$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("songsAdapter");
                access$getSongsAdapter$p = null;
            }
            access$getSongsAdapter$p.setCurrentlyPlaying(this.$playlistModel.getPlaying());
            this.label = 1;
            if (DelayKt.delay(50, this) == coroutine_suspended) {
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
