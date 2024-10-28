package org.videolan.vlc;

import android.net.Uri;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.videolan.resources.AndroidDevices;

@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\u001a\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0001\u001a\u0006\u0010\u0006\u001a\u00020\u0004\u001a\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0001\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"TAG", "", "THUMB_PROVIDER_AUTHORITY", "getFileUri", "Landroid/net/Uri;", "path", "getUpdateUri", "isPathValid", "", "vlc-android_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: FileProvider.kt */
public final class FileProviderKt {
    private static final String TAG = "VLC/FileProvider";
    private static final String THUMB_PROVIDER_AUTHORITY = "org.videolan.vlc.thumbprovider";

    public static final Uri getFileUri(String str) {
        Intrinsics.checkNotNullParameter(str, ArtworkProvider.PATH);
        Uri build = new Uri.Builder().scheme("content").authority(THUMB_PROVIDER_AUTHORITY).path(str).build();
        Intrinsics.checkNotNull(build);
        return build;
    }

    public static final Uri getUpdateUri() {
        Uri build = new Uri.Builder().scheme("content").authority(THUMB_PROVIDER_AUTHORITY).path("app_update").build();
        Intrinsics.checkNotNull(build);
        return build;
    }

    public static final boolean isPathValid(String str) {
        Intrinsics.checkNotNullParameter(str, ArtworkProvider.PATH);
        try {
            File file = new File(str);
            String[] mountBL = AndroidDevices.INSTANCE.getMountBL();
            int length = mountBL.length;
            int i = 0;
            while (i < length) {
                String str2 = mountBL[i];
                String canonicalPath = file.getCanonicalPath();
                Intrinsics.checkNotNullExpressionValue(canonicalPath, "getCanonicalPath(...)");
                if (!StringsKt.startsWith$default(canonicalPath, str2, false, 2, (Object) null)) {
                    i++;
                } else if (file.canRead()) {
                    return true;
                } else {
                    return false;
                }
            }
            return false;
        } catch (IOException e) {
            Log.e(TAG, "Failed to parse path: " + str);
            Log.e(TAG, e.getMessage(), e);
            return false;
        }
    }
}
