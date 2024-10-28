package org.videolan.vlc.viewmodels;

import androidx.lifecycle.MediatorLiveData;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Lambda;
import org.videolan.vlc.media.Progress;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lorg/videolan/vlc/media/Progress;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: PlaylistModel.kt */
final class PlaylistModel$onServiceChanged$1$1 extends Lambda implements Function1<Progress, Unit> {
    final /* synthetic */ MediatorLiveData<PlaybackProgress> $this_apply;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PlaylistModel$onServiceChanged$1$1(MediatorLiveData<PlaybackProgress> mediatorLiveData) {
        super(1);
        this.$this_apply = mediatorLiveData;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Progress) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(Progress progress) {
        MediatorLiveData<PlaybackProgress> mediatorLiveData = this.$this_apply;
        long j = 0;
        long time = progress != null ? progress.getTime() : 0;
        if (progress != null) {
            j = progress.getLength();
        }
        mediatorLiveData.setValue(new PlaybackProgress(time, j, (String) null, (String) null, 12, (DefaultConstructorMarker) null));
    }
}
