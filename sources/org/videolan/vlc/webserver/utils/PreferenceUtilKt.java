package org.videolan.vlc.webserver.utils;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.cache.DiskLruCache;
import org.videolan.resources.Constants;
import org.videolan.tools.SettingsKt;
import org.videolan.vlc.R;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0012\u0010\u0005\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0012\u0010\u0006\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0012\u0010\u0007\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\b"}, d2 = {"serveAudios", "", "Landroid/content/SharedPreferences;", "context", "Landroid/content/Context;", "servePlaylists", "serveSearch", "serveVideos", "webserver_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: PreferenceUtil.kt */
public final class PreferenceUtilKt {
    public static final boolean serveVideos(SharedPreferences sharedPreferences, Context context) {
        Intrinsics.checkNotNullParameter(sharedPreferences, "<this>");
        Intrinsics.checkNotNullParameter(context, "context");
        String[] stringArray = context.getResources().getStringArray(R.array.remote_access_content_values);
        Intrinsics.checkNotNullExpressionValue(stringArray, "getStringArray(...)");
        Set<String> stringSet = sharedPreferences.getStringSet(SettingsKt.KEY_REMOTE_ACCESS_ML_CONTENT, ArraysKt.toSet((T[]) (Object[]) stringArray));
        return stringSet != null && stringSet.contains(Constants.GROUP_VIDEOS_FOLDER);
    }

    public static final boolean serveAudios(SharedPreferences sharedPreferences, Context context) {
        Intrinsics.checkNotNullParameter(sharedPreferences, "<this>");
        Intrinsics.checkNotNullParameter(context, "context");
        String[] stringArray = context.getResources().getStringArray(R.array.remote_access_content_values);
        Intrinsics.checkNotNullExpressionValue(stringArray, "getStringArray(...)");
        Set<String> stringSet = sharedPreferences.getStringSet(SettingsKt.KEY_REMOTE_ACCESS_ML_CONTENT, ArraysKt.toSet((T[]) (Object[]) stringArray));
        return stringSet != null && stringSet.contains(DiskLruCache.VERSION_1);
    }

    public static final boolean servePlaylists(SharedPreferences sharedPreferences, Context context) {
        Intrinsics.checkNotNullParameter(sharedPreferences, "<this>");
        Intrinsics.checkNotNullParameter(context, "context");
        String[] stringArray = context.getResources().getStringArray(R.array.remote_access_content_values);
        Intrinsics.checkNotNullExpressionValue(stringArray, "getStringArray(...)");
        Set<String> stringSet = sharedPreferences.getStringSet(SettingsKt.KEY_REMOTE_ACCESS_ML_CONTENT, ArraysKt.toSet((T[]) (Object[]) stringArray));
        return stringSet != null && stringSet.contains("2");
    }

    public static final boolean serveSearch(SharedPreferences sharedPreferences, Context context) {
        Intrinsics.checkNotNullParameter(sharedPreferences, "<this>");
        Intrinsics.checkNotNullParameter(context, "context");
        String[] stringArray = context.getResources().getStringArray(R.array.remote_access_content_values);
        Intrinsics.checkNotNullExpressionValue(stringArray, "getStringArray(...)");
        Set<String> stringSet = sharedPreferences.getStringSet(SettingsKt.KEY_REMOTE_ACCESS_ML_CONTENT, ArraysKt.toSet((T[]) (Object[]) stringArray));
        return stringSet != null && stringSet.contains("3");
    }
}
