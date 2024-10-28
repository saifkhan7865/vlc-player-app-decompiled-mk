package org.videolan.vlc.gui.dialogs;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lorg/videolan/vlc/gui/dialogs/SubtitleLongClick;", "Lorg/videolan/vlc/gui/dialogs/SubtitleEvent;", "item", "Lorg/videolan/vlc/gui/dialogs/SubtitleItem;", "(Lorg/videolan/vlc/gui/dialogs/SubtitleItem;)V", "getItem", "()Lorg/videolan/vlc/gui/dialogs/SubtitleItem;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: SubtitlesAdapter.kt */
public final class SubtitleLongClick extends SubtitleEvent {
    private final SubtitleItem item;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SubtitleLongClick(SubtitleItem subtitleItem) {
        super((DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(subtitleItem, "item");
        this.item = subtitleItem;
    }

    public final SubtitleItem getItem() {
        return this.item;
    }
}
