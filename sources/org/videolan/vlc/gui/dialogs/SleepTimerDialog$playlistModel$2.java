package org.videolan.vlc.gui.dialogs;

import androidx.fragment.app.Fragment;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.videolan.vlc.viewmodels.PlaylistModel;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lorg/videolan/vlc/viewmodels/PlaylistModel;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: SleepTimerDialog.kt */
final class SleepTimerDialog$playlistModel$2 extends Lambda implements Function0<PlaylistModel> {
    final /* synthetic */ SleepTimerDialog this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SleepTimerDialog$playlistModel$2(SleepTimerDialog sleepTimerDialog) {
        super(0);
        this.this$0 = sleepTimerDialog;
    }

    public final PlaylistModel invoke() {
        return PlaylistModel.Companion.get((Fragment) this.this$0);
    }
}
