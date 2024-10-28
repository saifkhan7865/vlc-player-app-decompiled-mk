package org.videolan.vlc;

import android.content.Context;
import android.net.Uri;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.resources.Constants;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\b\u0002\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007¢\u0006\u0002\u0010\tR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000b¨\u0006\u000f"}, d2 = {"Lorg/videolan/vlc/Unmount;", "Lorg/videolan/vlc/MediaEvent;", "ctx", "Landroid/content/Context;", "uri", "Landroid/net/Uri;", "path", "", "uuid", "(Landroid/content/Context;Landroid/net/Uri;Ljava/lang/String;Ljava/lang/String;)V", "getPath", "()Ljava/lang/String;", "getUri", "()Landroid/net/Uri;", "getUuid", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: StoragesMonitor.kt */
final class Unmount extends MediaEvent {
    private final String path;
    private final Uri uri;
    private final String uuid;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public Unmount(Context context, Uri uri2, String str, String str2) {
        super(context, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(context, "ctx");
        Intrinsics.checkNotNullParameter(uri2, Constants.KEY_URI);
        Intrinsics.checkNotNullParameter(str, ArtworkProvider.PATH);
        Intrinsics.checkNotNullParameter(str2, "uuid");
        this.uri = uri2;
        this.path = str;
        this.uuid = str2;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ Unmount(android.content.Context r1, android.net.Uri r2, java.lang.String r3, java.lang.String r4, int r5, kotlin.jvm.internal.DefaultConstructorMarker r6) {
        /*
            r0 = this;
            r6 = r5 & 4
            if (r6 == 0) goto L_0x000b
            java.lang.String r3 = r2.getPath()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3)
        L_0x000b:
            r5 = r5 & 8
            if (r5 == 0) goto L_0x0016
            java.lang.String r4 = r2.getLastPathSegment()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4)
        L_0x0016:
            r0.<init>(r1, r2, r3, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.Unmount.<init>(android.content.Context, android.net.Uri, java.lang.String, java.lang.String, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
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
