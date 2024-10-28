package org.videolan.vlc.util;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.vlc.gui.dialogs.SubtitleItem;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lorg/videolan/vlc/util/SubDlSuccess;", "Lorg/videolan/vlc/util/SubDlResult;", "id", "", "subtitleItem", "Lorg/videolan/vlc/gui/dialogs/SubtitleItem;", "localUri", "", "(JLorg/videolan/vlc/gui/dialogs/SubtitleItem;Ljava/lang/String;)V", "getId", "()J", "getLocalUri", "()Ljava/lang/String;", "getSubtitleItem", "()Lorg/videolan/vlc/gui/dialogs/SubtitleItem;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: VLCDownloadManager.kt */
final class SubDlSuccess extends SubDlResult {
    private final long id;
    private final String localUri;
    private final SubtitleItem subtitleItem;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SubDlSuccess(long j, SubtitleItem subtitleItem2, String str) {
        super((DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(subtitleItem2, "subtitleItem");
        Intrinsics.checkNotNullParameter(str, "localUri");
        this.id = j;
        this.subtitleItem = subtitleItem2;
        this.localUri = str;
    }

    public final long getId() {
        return this.id;
    }

    public final String getLocalUri() {
        return this.localUri;
    }

    public final SubtitleItem getSubtitleItem() {
        return this.subtitleItem;
    }
}
