package org.videolan.vlc.viewmodels.mobile;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.medialibrary.interfaces.media.Album;
import org.videolan.medialibrary.interfaces.media.Playlist;
import org.videolan.medialibrary.media.MediaLibraryItem;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.viewmodels.mobile.PlaylistViewModel$refreshPlaylistItem$2", f = "PlaylistViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: PlaylistViewModel.kt */
final class PlaylistViewModel$refreshPlaylistItem$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ PlaylistViewModel this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PlaylistViewModel$refreshPlaylistItem$2(PlaylistViewModel playlistViewModel, Continuation<? super PlaylistViewModel$refreshPlaylistItem$2> continuation) {
        super(2, continuation);
        this.this$0 = playlistViewModel;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PlaylistViewModel$refreshPlaylistItem$2(this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PlaylistViewModel$refreshPlaylistItem$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            MediaLibraryItem access$getInitialPlaylist$p = this.this$0.initialPlaylist;
            if (access$getInitialPlaylist$p instanceof Album) {
                this.this$0.getPlaylistLiveData().postValue(this.this$0.getMedialibrary().getAlbum(((Album) this.this$0.initialPlaylist).getId()));
            } else if (access$getInitialPlaylist$p instanceof Playlist) {
                this.this$0.getPlaylistLiveData().postValue(this.this$0.getMedialibrary().getPlaylist(((Playlist) this.this$0.initialPlaylist).getId(), true, false));
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
