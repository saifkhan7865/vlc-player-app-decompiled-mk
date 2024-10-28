package org.videolan.vlc;

import android.net.Uri;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.resources.Constants;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\r"}, d2 = {"Lorg/videolan/vlc/MediaUnmounted;", "Lorg/videolan/vlc/DeviceAction;", "uri", "Landroid/net/Uri;", "path", "", "uuid", "(Landroid/net/Uri;Ljava/lang/String;Ljava/lang/String;)V", "getPath", "()Ljava/lang/String;", "getUri", "()Landroid/net/Uri;", "getUuid", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ExternalMonitor.kt */
public final class MediaUnmounted extends DeviceAction {
    private final String path;
    private final Uri uri;
    private final String uuid;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public MediaUnmounted(Uri uri2, String str, String str2) {
        super((DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(uri2, Constants.KEY_URI);
        Intrinsics.checkNotNullParameter(str, ArtworkProvider.PATH);
        Intrinsics.checkNotNullParameter(str2, "uuid");
        this.uri = uri2;
        this.path = str;
        this.uuid = str2;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ MediaUnmounted(android.net.Uri r1, java.lang.String r2, java.lang.String r3, int r4, kotlin.jvm.internal.DefaultConstructorMarker r5) {
        /*
            r0 = this;
            r5 = r4 & 2
            if (r5 == 0) goto L_0x000b
            java.lang.String r2 = r1.getPath()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)
        L_0x000b:
            r4 = r4 & 4
            if (r4 == 0) goto L_0x0016
            java.lang.String r3 = r1.getLastPathSegment()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3)
        L_0x0016:
            r0.<init>(r1, r2, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.MediaUnmounted.<init>(android.net.Uri, java.lang.String, java.lang.String, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final String getPath() {
        return this.path;
    }

    public final Uri getUri() {
        return this.uri;
    }

    public final String getUuid() {
        return this.uuid;
    }
}
