package org.videolan.vlc;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import io.netty.handler.codec.rtsp.RtspHeaders;
import java.io.File;
import java.io.FileNotFoundException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.videolan.medialibrary.interfaces.Medialibrary;
import org.videolan.resources.AndroidDevices;
import org.videolan.resources.AppContextProvider;
import org.videolan.resources.Constants;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J1\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0010\u0010\t\u001a\f\u0012\u0006\b\u0001\u0012\u00020\b\u0018\u00010\nH\u0016¢\u0006\u0002\u0010\u000bJ\u0010\u0010\f\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u001a\u0010\r\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\u0018\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\bH\u0016JM\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0005\u001a\u00020\u00062\u0010\u0010\u0017\u001a\f\u0012\u0006\b\u0001\u0012\u00020\b\u0018\u00010\n2\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0010\u0010\t\u001a\f\u0012\u0006\b\u0001\u0012\u00020\b\u0018\u00010\n2\b\u0010\u0018\u001a\u0004\u0018\u00010\bH\u0016¢\u0006\u0002\u0010\u0019J;\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0010\u0010\t\u001a\f\u0012\u0006\b\u0001\u0012\u00020\b\u0018\u00010\nH\u0016¢\u0006\u0002\u0010\u001b¨\u0006\u001c"}, d2 = {"Lorg/videolan/vlc/FileProvider;", "Landroid/content/ContentProvider;", "()V", "delete", "", "uri", "Landroid/net/Uri;", "selection", "", "selectionArgs", "", "(Landroid/net/Uri;Ljava/lang/String;[Ljava/lang/String;)I", "getType", "insert", "values", "Landroid/content/ContentValues;", "onCreate", "", "openFile", "Landroid/os/ParcelFileDescriptor;", "mode", "query", "Landroid/database/Cursor;", "projection", "sortOrder", "(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;", "update", "(Landroid/net/Uri;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: FileProvider.kt */
public final class FileProvider extends ContentProvider {
    public int delete(Uri uri, String str, String[] strArr) {
        Intrinsics.checkNotNullParameter(uri, Constants.KEY_URI);
        return 0;
    }

    public boolean onCreate() {
        return true;
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        Intrinsics.checkNotNullParameter(uri, Constants.KEY_URI);
        return 0;
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        Intrinsics.checkNotNullParameter(uri, Constants.KEY_URI);
        Uri uri2 = Uri.EMPTY;
        Intrinsics.checkNotNull(uri2);
        return uri2;
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        Intrinsics.checkNotNullParameter(uri, Constants.KEY_URI);
        throw new UnsupportedOperationException("Not supported by this provider");
    }

    public String getType(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, Constants.KEY_URI);
        StringBuilder sb = new StringBuilder("image/");
        String path = uri.getPath();
        String str = null;
        if (path != null) {
            str = StringsKt.substringAfterLast$default(path, '.', (String) null, 2, (Object) null);
        }
        sb.append(str);
        return sb.toString();
    }

    public ParcelFileDescriptor openFile(Uri uri, String str) {
        Intrinsics.checkNotNullParameter(uri, Constants.KEY_URI);
        Intrinsics.checkNotNullParameter(str, RtspHeaders.Values.MODE);
        String path = uri.getPath();
        if (path == null) {
            throw new SecurityException("Illegal access");
        } else if (!StringsKt.contains$default((CharSequence) path, (CharSequence) "..", false, 2, (Object) null)) {
            StringBuilder sb = new StringBuilder();
            File externalFilesDir = AppContextProvider.INSTANCE.getAppContext().getExternalFilesDir((String) null);
            Intrinsics.checkNotNull(externalFilesDir);
            sb.append(externalFilesDir.getAbsolutePath());
            sb.append(Medialibrary.MEDIALIB_FOLDER_NAME);
            if (!StringsKt.startsWith$default(path, sb.toString(), false, 2, (Object) null) && !Intrinsics.areEqual((Object) path, (Object) "/app_update")) {
                throw new SecurityException("Illegal access");
            } else if (Intrinsics.areEqual((Object) path, (Object) "/app_update")) {
                ParcelFileDescriptor open = ParcelFileDescriptor.open(new File(AppContextProvider.INSTANCE.getAppContext().getCacheDir(), "update.apk"), 268435456);
                Intrinsics.checkNotNullExpressionValue(open, "open(...)");
                return open;
            } else {
                File file = new File(path);
                String[] mountBL = AndroidDevices.INSTANCE.getMountBL();
                int length = mountBL.length;
                int i = 0;
                while (i < length) {
                    String str2 = mountBL[i];
                    String canonicalPath = file.getCanonicalPath();
                    Intrinsics.checkNotNullExpressionValue(canonicalPath, "getCanonicalPath(...)");
                    if (!StringsKt.startsWith$default(canonicalPath, str2, false, 2, (Object) null)) {
                        i++;
                    } else if (file.exists()) {
                        ParcelFileDescriptor open2 = ParcelFileDescriptor.open(file, 268435456);
                        Intrinsics.checkNotNullExpressionValue(open2, "open(...)");
                        return open2;
                    } else {
                        throw new FileNotFoundException(path);
                    }
                }
                throw new SecurityException("Illegal access");
            }
        } else {
            throw new SecurityException("Illegal access");
        }
    }
}
