package org.videolan.vlc.util;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lorg/videolan/vlc/util/SubDlFailure;", "Lorg/videolan/vlc/util/SubDlResult;", "id", "", "(J)V", "getId", "()J", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: VLCDownloadManager.kt */
final class SubDlFailure extends SubDlResult {
    private final long id;

    public SubDlFailure(long j) {
        super((DefaultConstructorMarker) null);
        this.id = j;
    }

    public final long getId() {
        return this.id;
    }
}
