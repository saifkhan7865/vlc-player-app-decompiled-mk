package org.videolan.vlc.gui.dialogs;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.videolan.vlc.PlaybackService;
import org.videolan.vlc.R;
import org.videolan.vlc.media.PlayerController;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0014J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\n"}, d2 = {"Lorg/videolan/vlc/gui/dialogs/JumpToTimeDialog;", "Lorg/videolan/vlc/gui/dialogs/PickTimeFragment;", "()V", "executeAction", "", "getTitle", "", "showTimeOnly", "", "Companion", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: JumpToTimeDialog.kt */
public final class JumpToTimeDialog extends PickTimeFragment {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);

    public boolean showTimeOnly() {
        return true;
    }

    /* access modifiers changed from: protected */
    public void executeAction() {
        long timeInMillis = getTimeInMillis();
        PlaybackService.setTime$default(getPlaybackService(), timeInMillis, false, 2, (Object) null);
        PlayerController.updateProgress$default(getPlaybackService().getPlaylistManager().getPlayer(), timeInMillis, 0, 2, (Object) null);
        dismiss();
    }

    public int getTitle() {
        return R.string.jump_to_time;
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"Lorg/videolan/vlc/gui/dialogs/JumpToTimeDialog$Companion;", "", "()V", "newInstance", "Lorg/videolan/vlc/gui/dialogs/JumpToTimeDialog;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: JumpToTimeDialog.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final JumpToTimeDialog newInstance() {
            return new JumpToTimeDialog();
        }
    }
}
