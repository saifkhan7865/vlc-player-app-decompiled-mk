package org.videolan.vlc.viewmodels.mobile;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.viewmodels.mobile.PlaylistViewModel", f = "PlaylistViewModel.kt", i = {0}, l = {92}, m = "rename", n = {"this"}, s = {"L$0"})
/* compiled from: PlaylistViewModel.kt */
final class PlaylistViewModel$rename$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ PlaylistViewModel this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PlaylistViewModel$rename$1(PlaylistViewModel playlistViewModel, Continuation<? super PlaylistViewModel$rename$1> continuation) {
        super(continuation);
        this.this$0 = playlistViewModel;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.rename((MediaWrapper) null, (String) null, this);
    }
}
