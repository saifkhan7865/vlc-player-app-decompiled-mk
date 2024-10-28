package org.videolan.vlc.gui.dialogs;

import kotlin.Metadata;
import org.videolan.vlc.util.ContextOption;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\b"}, d2 = {"Lorg/videolan/vlc/gui/dialogs/CtxActionReceiver;", "", "onCtxAction", "", "position", "", "option", "Lorg/videolan/vlc/util/ContextOption;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ContextSheet.kt */
public interface CtxActionReceiver {
    void onCtxAction(int i, ContextOption contextOption);
}
