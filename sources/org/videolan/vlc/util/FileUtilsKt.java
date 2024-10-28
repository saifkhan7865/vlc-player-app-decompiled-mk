package org.videolan.vlc.util;

import android.net.Uri;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.videolan.medialibrary.Tools;
import org.videolan.tools.Strings;

@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0001\u001a\u000e\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u0004\u0018\u00010\u0001\u001a\f\u0010\u0003\u001a\u00020\u0004*\u0004\u0018\u00010\u0005\u001a\f\u0010\u0006\u001a\u00020\u0004*\u0004\u0018\u00010\u0005\u001a\n\u0010\u0007\u001a\u00020\b*\u00020\t¨\u0006\n"}, d2 = {"encodeMrlWithTrailingSlash", "", "getParentFolder", "isSettings", "", "Landroid/net/Uri;", "isSoundFont", "toByteArray", "", "Ljava/io/InputStream;", "vlc-android_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: FileUtils.kt */
public final class FileUtilsKt {
    public static final String getParentFolder(String str) {
        if (str == null || Intrinsics.areEqual((Object) str, (Object) "/")) {
            return str;
        }
        if (StringsKt.endsWith$default(str, "/", false, 2, (Object) null)) {
            str = str.substring(0, str.length() - 1);
            Intrinsics.checkNotNullExpressionValue(str, "substring(...)");
        }
        int lastIndexOf$default = StringsKt.lastIndexOf$default((CharSequence) str, '/', 0, false, 6, (Object) null);
        if (lastIndexOf$default > 0) {
            String substring = str.substring(0, lastIndexOf$default);
            Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
            return substring;
        } else if (lastIndexOf$default == 0) {
            return "/";
        } else {
            return str;
        }
    }

    public static final String encodeMrlWithTrailingSlash(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        String encodeVLCMrl = Tools.encodeVLCMrl(str);
        Intrinsics.checkNotNull(encodeVLCMrl);
        return StringsKt.endsWith$default(encodeVLCMrl, "/", false, 2, (Object) null) ? encodeVLCMrl : Strings.addTrailingSlashIfNeeded(encodeVLCMrl);
    }

    public static final boolean isSoundFont(Uri uri) {
        String lastPathSegment;
        if (!(uri == null || (lastPathSegment = uri.getLastPathSegment()) == null)) {
            String lowerCase = lastPathSegment.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            if (lowerCase != null) {
                for (String endsWith$default : FileUtils.INSTANCE.getSoundFontExtensions()) {
                    if (StringsKt.endsWith$default(lowerCase, endsWith$default, false, 2, (Object) null)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static final boolean isSettings(Uri uri) {
        String lastPathSegment;
        if (!(uri == null || (lastPathSegment = uri.getLastPathSegment()) == null)) {
            String lowerCase = lastPathSegment.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            if (lowerCase != null) {
                String lowerCase2 = lowerCase.toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(lowerCase2, "toLowerCase(...)");
                if (StringsKt.endsWith$default(lowerCase2, ".json", false, 2, (Object) null)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static final byte[] toByteArray(InputStream inputStream) {
        Intrinsics.checkNotNullParameter(inputStream, "<this>");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[16384];
        while (true) {
            int read = inputStream.read(bArr, 0, 16384);
            if (read != -1) {
                byteArrayOutputStream.write(bArr, 0, read);
            } else {
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                Intrinsics.checkNotNullExpressionValue(byteArray, "toByteArray(...)");
                return byteArray;
            }
        }
    }
}
