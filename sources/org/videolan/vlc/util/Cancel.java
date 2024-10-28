package org.videolan.vlc.util;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.videolan.libvlc.Dialog;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lorg/videolan/vlc/util/Cancel;", "Lorg/videolan/vlc/util/DialogEvt;", "dialog", "Lorg/videolan/libvlc/Dialog;", "(Lorg/videolan/libvlc/Dialog;)V", "getDialog", "()Lorg/videolan/libvlc/Dialog;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: DialogDelegates.kt */
final class Cancel extends DialogEvt {
    private final Dialog dialog;

    public Cancel(Dialog dialog2) {
        super((DefaultConstructorMarker) null);
        this.dialog = dialog2;
    }

    public final Dialog getDialog() {
        return this.dialog;
    }
}
