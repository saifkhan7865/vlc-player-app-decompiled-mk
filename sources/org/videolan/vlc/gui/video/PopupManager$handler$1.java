package org.videolan.vlc.gui.video;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.ImageView;
import androidx.core.app.NotificationCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"org/videolan/vlc/gui/video/PopupManager$handler$1", "Landroid/os/Handler;", "handleMessage", "", "msg", "Landroid/os/Message;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: PopupManager.kt */
public final class PopupManager$handler$1 extends Handler {
    final /* synthetic */ PopupManager this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PopupManager$handler$1(PopupManager popupManager, Looper looper) {
        super(looper);
        this.this$0 = popupManager;
    }

    public void handleMessage(Message message) {
        Intrinsics.checkNotNullParameter(message, NotificationCompat.CATEGORY_MESSAGE);
        int i = message.what == 0 ? 0 : 8;
        ImageView access$getPlayPauseButton$p = this.this$0.playPauseButton;
        ImageView imageView = null;
        if (access$getPlayPauseButton$p == null) {
            Intrinsics.throwUninitializedPropertyAccessException("playPauseButton");
            access$getPlayPauseButton$p = null;
        }
        access$getPlayPauseButton$p.setVisibility(i);
        ImageView access$getCloseButton$p = this.this$0.closeButton;
        if (access$getCloseButton$p == null) {
            Intrinsics.throwUninitializedPropertyAccessException("closeButton");
            access$getCloseButton$p = null;
        }
        access$getCloseButton$p.setVisibility(i);
        ImageView access$getExpandButton$p = this.this$0.expandButton;
        if (access$getExpandButton$p == null) {
            Intrinsics.throwUninitializedPropertyAccessException("expandButton");
        } else {
            imageView = access$getExpandButton$p;
        }
        imageView.setVisibility(i);
    }
}
