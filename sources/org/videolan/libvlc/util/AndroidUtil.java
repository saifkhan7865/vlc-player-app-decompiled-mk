package org.videolan.libvlc.util;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.net.Uri;
import android.os.Build;
import java.io.File;

public class AndroidUtil {
    public static final boolean isJellyBeanMR2OrLater;
    public static final boolean isKitKatOrLater;
    public static final boolean isLolliPopOrLater;
    public static final boolean isMarshMallowOrLater;
    public static final boolean isNougatMR1OrLater;
    public static final boolean isNougatOrLater;
    public static final boolean isOOrLater;
    public static final boolean isPOrLater;
    public static final boolean isROrLater = (Build.VERSION.SDK_INT >= 30);

    static {
        boolean z = true;
        boolean z2 = Build.VERSION.SDK_INT >= 28;
        isPOrLater = z2;
        boolean z3 = z2 || Build.VERSION.SDK_INT >= 26;
        isOOrLater = z3;
        boolean z4 = z3 || Build.VERSION.SDK_INT >= 25;
        isNougatMR1OrLater = z4;
        boolean z5 = z4 || Build.VERSION.SDK_INT >= 24;
        isNougatOrLater = z5;
        boolean z6 = z5 || Build.VERSION.SDK_INT >= 23;
        isMarshMallowOrLater = z6;
        boolean z7 = z6 || Build.VERSION.SDK_INT >= 21;
        isLolliPopOrLater = z7;
        boolean z8 = z7 || Build.VERSION.SDK_INT >= 19;
        isKitKatOrLater = z8;
        if (!z8 && Build.VERSION.SDK_INT < 18) {
            z = false;
        }
        isJellyBeanMR2OrLater = z;
    }

    public static File UriToFile(Uri uri) {
        return new File(uri.getPath().replaceFirst("file://", ""));
    }

    public static Uri PathToUri(String str) {
        return Uri.fromFile(new File(str));
    }

    public static Uri LocationToUri(String str) {
        Uri parse = Uri.parse(str);
        if (parse.getScheme() != null) {
            return parse;
        }
        throw new IllegalArgumentException("location has no scheme");
    }

    public static Uri FileToUri(File file) {
        return Uri.fromFile(file);
    }

    public static Activity resolveActivity(Context context) {
        if (context instanceof Activity) {
            return (Activity) context;
        }
        if (context instanceof ContextWrapper) {
            return resolveActivity(((ContextWrapper) context).getBaseContext());
        }
        return null;
    }
}
