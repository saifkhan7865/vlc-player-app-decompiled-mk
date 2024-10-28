package org.videolan.vlc.util;

import kotlin.Metadata;
import org.videolan.libvlc.Dialog;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0007"}, d2 = {"Lorg/videolan/vlc/util/IDialogManager;", "", "dialogCanceled", "", "dialog", "Lorg/videolan/libvlc/Dialog;", "fireDialog", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: DialogDelegates.kt */
public interface IDialogManager {
    void dialogCanceled(Dialog dialog);

    void fireDialog(Dialog dialog);
}
