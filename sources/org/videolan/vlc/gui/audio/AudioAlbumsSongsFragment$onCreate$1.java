package org.videolan.vlc.gui.audio;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: AudioAlbumsSongsFragment.kt */
final class AudioAlbumsSongsFragment$onCreate$1 extends Lambda implements Function1<MediaWrapper, Unit> {
    final /* synthetic */ AudioAlbumsSongsFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AudioAlbumsSongsFragment$onCreate$1(AudioAlbumsSongsFragment audioAlbumsSongsFragment) {
        super(1);
        this.this$0 = audioAlbumsSongsFragment;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((MediaWrapper) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(MediaWrapper mediaWrapper) {
        AudioBrowserAdapter access$getSongsAdapter$p = this.this$0.songsAdapter;
        if (access$getSongsAdapter$p == null) {
            Intrinsics.throwUninitializedPropertyAccessException("songsAdapter");
            access$getSongsAdapter$p = null;
        }
        access$getSongsAdapter$p.setCurrentMedia(mediaWrapper);
    }
}
