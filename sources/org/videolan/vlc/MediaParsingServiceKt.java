package org.videolan.vlc;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.resources.Constants;
import org.videolan.resources.util.ExtensionsKt;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\n\u0010\u0002\u001a\u00020\u0003*\u00020\u0004\u001a\n\u0010\u0005\u001a\u00020\u0003*\u00020\u0004\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"TAG", "", "reloadLibrary", "", "Landroid/content/Context;", "rescan", "vlc-android_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaParsingService.kt */
public final class MediaParsingServiceKt {
    private static final String TAG = "VLC/MediaParsingService";

    public static final void reloadLibrary(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        ExtensionsKt.launchForeground$default(context, new Intent(Constants.ACTION_RELOAD, (Uri) null, context, MediaParsingService.class), (Function0) null, 2, (Object) null);
    }

    public static final void rescan(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        ExtensionsKt.launchForeground$default(context, new Intent(Constants.ACTION_FORCE_RELOAD, (Uri) null, context, MediaParsingService.class), (Function0) null, 2, (Object) null);
    }
}
