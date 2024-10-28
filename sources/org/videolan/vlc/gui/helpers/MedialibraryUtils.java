package org.videolan.vlc.gui.helpers;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.videolan.medialibrary.Tools;
import org.videolan.medialibrary.interfaces.Medialibrary;
import org.videolan.resources.AppContextProvider;
import org.videolan.resources.Constants;
import org.videolan.resources.util.ExtensionsKt;
import org.videolan.tools.Strings;
import org.videolan.tools.WorkersKt;
import org.videolan.vlc.ArtworkProvider;
import org.videolan.vlc.MediaParsingService;
import org.videolan.vlc.util.FileUtilsKt;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u001a\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bH\u0007J\u000e\u0010\n\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u001c\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00060\u0010J\u001c\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00060\u0010J\u000e\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u0006J\u001c\u0010\u0012\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00060\u0010J\u001c\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00060\u0010J\u000e\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0014"}, d2 = {"Lorg/videolan/vlc/gui/helpers/MedialibraryUtils;", "", "()V", "addDevice", "", "path", "", "context", "Landroid/content/Context;", "addDir", "banDir", "isBanned", "", "uri", "Landroid/net/Uri;", "bannedFolders", "", "isScanned", "isStrictlyBanned", "removeDir", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MedialibraryUtils.kt */
public final class MedialibraryUtils {
    public static final MedialibraryUtils INSTANCE = new MedialibraryUtils();

    public final void addDir(String str) {
        Intrinsics.checkNotNullParameter(str, ArtworkProvider.PATH);
        addDir$default(this, str, (Context) null, 2, (Object) null);
    }

    private MedialibraryUtils() {
    }

    /* access modifiers changed from: private */
    public static final void removeDir$lambda$0(String str) {
        Intrinsics.checkNotNullParameter(str, "$path");
        Medialibrary.getInstance().removeFolder(str);
    }

    public final void removeDir(String str) {
        Intrinsics.checkNotNullParameter(str, ArtworkProvider.PATH);
        WorkersKt.runIO(new MedialibraryUtils$$ExternalSyntheticLambda0(str));
    }

    public final void banDir(String str) {
        Intrinsics.checkNotNullParameter(str, ArtworkProvider.PATH);
        Medialibrary.getInstance().banFolder(str);
    }

    public static /* synthetic */ void addDir$default(MedialibraryUtils medialibraryUtils, String str, Context context, int i, Object obj) {
        if ((i & 2) != 0) {
            context = AppContextProvider.INSTANCE.getAppContext();
        }
        medialibraryUtils.addDir(str, context);
    }

    public final void addDir(String str, Context context) {
        Intrinsics.checkNotNullParameter(str, ArtworkProvider.PATH);
        Intrinsics.checkNotNullParameter(context, "context");
        Intent intent = new Intent(Constants.ACTION_DISCOVER, (Uri) null, context, MediaParsingService.class);
        intent.putExtra("extra_path", str);
        ExtensionsKt.launchForeground$default(context, intent, (Function0) null, 2, (Object) null);
    }

    public final void addDevice(String str, Context context) {
        Intrinsics.checkNotNullParameter(str, ArtworkProvider.PATH);
        Intrinsics.checkNotNullParameter(context, "context");
        Intent intent = new Intent(Constants.ACTION_DISCOVER_DEVICE, (Uri) null, context, MediaParsingService.class);
        intent.putExtra("extra_path", str);
        ExtensionsKt.launchForeground$default(context, intent, (Function0) null, 2, (Object) null);
    }

    public final boolean isScanned(String str) {
        Intrinsics.checkNotNullParameter(str, ArtworkProvider.PATH);
        String[] foldersList = Medialibrary.getInstance().getFoldersList();
        Intrinsics.checkNotNullExpressionValue(foldersList, "getFoldersList(...)");
        boolean z = false;
        for (Object obj : (Object[]) foldersList) {
            String str2 = (String) obj;
            String stripTrailingSlash = Strings.stripTrailingSlash(str);
            Intrinsics.checkNotNull(str2);
            String uri = Uri.parse(str2).toString();
            Intrinsics.checkNotNullExpressionValue(uri, "toString(...)");
            if (StringsKt.startsWith$default(stripTrailingSlash, Strings.stripTrailingSlash(uri), false, 2, (Object) null)) {
                z = true;
            }
        }
        return z;
    }

    public final boolean isBanned(Uri uri, List<String> list) {
        Intrinsics.checkNotNullParameter(uri, Constants.KEY_URI);
        Intrinsics.checkNotNullParameter(list, "bannedFolders");
        String uri2 = uri.toString();
        Intrinsics.checkNotNullExpressionValue(uri2, "toString(...)");
        return isBanned(uri2, list);
    }

    public final boolean isBanned(String str, List<String> list) {
        Intrinsics.checkNotNullParameter(str, ArtworkProvider.PATH);
        Intrinsics.checkNotNullParameter(list, "bannedFolders");
        Iterable<String> iterable = list;
        if ((iterable instanceof Collection) && ((Collection) iterable).isEmpty()) {
            return false;
        }
        for (String valueOf : iterable) {
            String mlEncodeMrl = Tools.mlEncodeMrl(str);
            Intrinsics.checkNotNullExpressionValue(mlEncodeMrl, "mlEncodeMrl(...)");
            if (StringsKt.startsWith$default(FileUtilsKt.encodeMrlWithTrailingSlash(mlEncodeMrl), String.valueOf(valueOf), false, 2, (Object) null)) {
                return true;
            }
        }
        return false;
    }

    public final boolean isStrictlyBanned(Uri uri, List<String> list) {
        Intrinsics.checkNotNullParameter(uri, Constants.KEY_URI);
        Intrinsics.checkNotNullParameter(list, "bannedFolders");
        String uri2 = uri.toString();
        Intrinsics.checkNotNullExpressionValue(uri2, "toString(...)");
        return isStrictlyBanned(uri2, list);
    }

    public final boolean isStrictlyBanned(String str, List<String> list) {
        Intrinsics.checkNotNullParameter(str, ArtworkProvider.PATH);
        Intrinsics.checkNotNullParameter(list, "bannedFolders");
        Iterable<String> iterable = list;
        if ((iterable instanceof Collection) && ((Collection) iterable).isEmpty()) {
            return false;
        }
        for (String valueOf : iterable) {
            String mlEncodeMrl = Tools.mlEncodeMrl(str);
            Intrinsics.checkNotNullExpressionValue(mlEncodeMrl, "mlEncodeMrl(...)");
            if (Intrinsics.areEqual((Object) FileUtilsKt.encodeMrlWithTrailingSlash(mlEncodeMrl), (Object) String.valueOf(valueOf))) {
                return true;
            }
        }
        return false;
    }
}
