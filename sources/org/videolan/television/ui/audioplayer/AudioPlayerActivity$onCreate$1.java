package org.videolan.television.ui.audioplayer;

import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u001a\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004 \u0005*\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "mediaWrappers", "", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: AudioPlayerActivity.kt */
final class AudioPlayerActivity$onCreate$1 extends Lambda implements Function1<List<MediaWrapper>, Unit> {
    final /* synthetic */ AudioPlayerActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AudioPlayerActivity$onCreate$1(AudioPlayerActivity audioPlayerActivity) {
        super(1);
        this.this$0 = audioPlayerActivity;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((List<MediaWrapper>) (List) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(List<MediaWrapper> list) {
        if (list != null) {
            PlaylistAdapter access$getAdapter$p = this.this$0.adapter;
            PlaylistAdapter playlistAdapter = null;
            if (access$getAdapter$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                access$getAdapter$p = null;
            }
            access$getAdapter$p.setSelection(-1);
            PlaylistAdapter access$getAdapter$p2 = this.this$0.adapter;
            if (access$getAdapter$p2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
            } else {
                playlistAdapter = access$getAdapter$p2;
            }
            playlistAdapter.update(list);
        }
        this.this$0.updateRepeatMode();
    }
}
