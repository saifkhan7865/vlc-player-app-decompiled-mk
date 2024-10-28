package org.videolan.resources.util;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.View;
import androidx.core.content.ContextCompat;
import io.ktor.server.plugins.cors.CORSConfig;
import io.ktor.server.sessions.SessionTransportCookieKt;
import java.util.Calendar;
import java.util.Date;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.libvlc.util.AndroidUtil;
import org.videolan.medialibrary.interfaces.media.Album;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.interfaces.media.VideoGroup;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.resources.AppContextProvider;
import org.videolan.resources.R;
import org.videolan.tools.AppUtils$$ExternalSyntheticApiModelOutline0;

@Metadata(d1 = {"\u0000@\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t\u001a\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000b\u001a\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f\u001a\u0012\u0010\u0010\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000fH\u0007\u001a\u000e\u0010\u0011\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u0013\u001a\u0016\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u0001\u001a\u0006\u0010\u0017\u001a\u00020\r\u001a\n\u0010\u0018\u001a\u00020\u0013*\u00020\u0019\u001a\n\u0010\u001a\u001a\u00020\u0015*\u00020\u0019\u001a\n\u0010\u001b\u001a\u00020\r*\u00020\u0019\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"LENGTH_2_YEAR", "", "LENGTH_DAY", "LENGTH_MONTH", "LENGTH_WEEK", "LENGTH_YEAR", "applyOverscanMargin", "", "activity", "Landroid/app/Activity;", "view", "Landroid/view/View;", "canReadStorage", "", "context", "Landroid/content/Context;", "canWriteStorage", "getTimeCategory", "timestamp", "", "getTimeCategoryString", "", "cat", "isExternalStorageManager", "getLength", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "getYear", "isSpecialItem", "resources_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: Helpers.kt */
public final class HelpersKt {
    public static final int LENGTH_2_YEAR = 62899200;
    public static final int LENGTH_DAY = 86400;
    public static final int LENGTH_MONTH = 2592000;
    public static final int LENGTH_WEEK = 604800;
    public static final int LENGTH_YEAR = 31449600;

    public static final boolean canWriteStorage() {
        return canWriteStorage$default((Context) null, 1, (Object) null);
    }

    public static final int getTimeCategory(long j) {
        long currentTimeMillis = (System.currentTimeMillis() / 1000) - j;
        if (currentTimeMillis < CORSConfig.CORS_DEFAULT_MAX_AGE) {
            return 0;
        }
        if (currentTimeMillis < 172800) {
            return 1;
        }
        if (currentTimeMillis < SessionTransportCookieKt.DEFAULT_SESSION_MAX_AGE) {
            return 2;
        }
        if (currentTimeMillis < 1209600) {
            return 3;
        }
        if (currentTimeMillis < 2592000) {
            return 4;
        }
        if (currentTimeMillis < 31449600) {
            return 5;
        }
        return currentTimeMillis < 62899200 ? 6 : 7;
    }

    public static final String getTimeCategoryString(Context context, int i) {
        String str;
        Intrinsics.checkNotNullParameter(context, "context");
        switch (i) {
            case 0:
                str = context.getString(R.string.time_category_today);
                break;
            case 1:
                str = context.getString(R.string.time_category_yesterday);
                break;
            case 2:
                str = context.getString(R.string.time_category_current_week);
                break;
            case 3:
                str = context.getString(R.string.time_category_last_week);
                break;
            case 4:
                str = context.getString(R.string.time_category_current_month);
                break;
            case 5:
                str = context.getString(R.string.time_category_current_year);
                break;
            case 6:
                str = context.getString(R.string.time_category_last_year);
                break;
            default:
                str = context.getString(R.string.time_category_older);
                break;
        }
        Intrinsics.checkNotNull(str);
        return str;
    }

    public static final boolean isSpecialItem(MediaLibraryItem mediaLibraryItem) {
        Intrinsics.checkNotNullParameter(mediaLibraryItem, "<this>");
        return (mediaLibraryItem.getItemType() == 4 && (mediaLibraryItem.getId() == 1 || mediaLibraryItem.getId() == 2)) || (mediaLibraryItem.getItemType() == 2 && Intrinsics.areEqual((Object) mediaLibraryItem.getTitle(), (Object) Album.SpecialRes.UNKNOWN_ALBUM));
    }

    public static final long getLength(MediaLibraryItem mediaLibraryItem) {
        Intrinsics.checkNotNullParameter(mediaLibraryItem, "<this>");
        if (mediaLibraryItem.getItemType() == 2) {
            return ((Album) mediaLibraryItem).getDuration();
        }
        if (mediaLibraryItem.getItemType() == 32) {
            return ((MediaWrapper) mediaLibraryItem).getLength();
        }
        if (mediaLibraryItem.getItemType() == 2048) {
            return ((VideoGroup) mediaLibraryItem).duration();
        }
        return 0;
    }

    public static final String getYear(MediaLibraryItem mediaLibraryItem) {
        Intrinsics.checkNotNullParameter(mediaLibraryItem, "<this>");
        int itemType = mediaLibraryItem.getItemType();
        if (itemType == 2) {
            Album album = (Album) mediaLibraryItem;
            if (album.getReleaseYear() <= 0) {
                return "-";
            }
            return String.valueOf(album.getReleaseYear());
        } else if (itemType != 32) {
            return "-";
        } else {
            MediaWrapper mediaWrapper = (MediaWrapper) mediaLibraryItem;
            if (mediaWrapper.getReleaseYear() <= 0) {
                return "-";
            }
            Calendar instance = Calendar.getInstance();
            instance.setTime(new Date(((long) mediaWrapper.getReleaseYear()) * ((long) 1000)));
            return String.valueOf(instance.get(1));
        }
    }

    public static final boolean canReadStorage(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return !AndroidUtil.isMarshMallowOrLater || ContextCompat.checkSelfPermission(context, "android.permission.READ_EXTERNAL_STORAGE") == 0 || isExternalStorageManager();
    }

    public static final boolean isExternalStorageManager() {
        return Build.VERSION.SDK_INT >= 30 && AppUtils$$ExternalSyntheticApiModelOutline0.m();
    }

    public static /* synthetic */ boolean canWriteStorage$default(Context context, int i, Object obj) {
        if ((i & 1) != 0) {
            context = AppContextProvider.INSTANCE.getAppContext();
        }
        return canWriteStorage(context);
    }

    public static final boolean canWriteStorage(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return ContextCompat.checkSelfPermission(context, "android.permission.WRITE_EXTERNAL_STORAGE") == 0;
    }

    public static final void applyOverscanMargin(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        int dimensionPixelSize = activity.getResources().getDimensionPixelSize(R.dimen.tv_overscan_horizontal);
        int dimensionPixelSize2 = activity.getResources().getDimensionPixelSize(R.dimen.tv_overscan_vertical);
        activity.findViewById(16908290).setPadding(dimensionPixelSize, dimensionPixelSize2, dimensionPixelSize, dimensionPixelSize2);
    }

    public static final void applyOverscanMargin(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        int dimensionPixelSize = view.getResources().getDimensionPixelSize(R.dimen.tv_overscan_horizontal);
        int dimensionPixelSize2 = view.getResources().getDimensionPixelSize(R.dimen.tv_overscan_vertical);
        view.setPadding(view.getPaddingLeft() + dimensionPixelSize, view.getPaddingTop() + dimensionPixelSize2, dimensionPixelSize + view.getPaddingRight(), dimensionPixelSize2 + view.getPaddingBottom());
    }
}
