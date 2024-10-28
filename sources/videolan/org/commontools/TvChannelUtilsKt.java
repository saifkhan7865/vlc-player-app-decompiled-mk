package videolan.org.commontools;

import android.content.ComponentName;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import androidx.tvprovider.media.tv.Channel;
import androidx.tvprovider.media.tv.ChannelLogoUtils;
import androidx.tvprovider.media.tv.PreviewProgram;
import androidx.tvprovider.media.tv.TvContractCompat;
import androidx.tvprovider.media.tv.WatchNextProgram;
import io.ktor.http.ContentDisposition;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.tools.KotlinExtensionsKt;
import org.videolan.tools.SettingsKt;

@Metadata(d1 = {"\u0000d\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\u001a\u0016\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013\u001a\u0016\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013\u001a(\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u001eH\u0007\u001a\u001a\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u00012\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u0001\u001a\u0018\u0010#\u001a\u00020\u001e2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\"\u001a\u00020\u0017H\u0007\u001a\u0016\u0010$\u001a\u00020\u001e2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\"\u001a\u00020\u0017\u001a\"\u0010%\u001a\f\u0012\u0004\u0012\u00020'0&j\u0002`(2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010)\u001a\u00020\u0017H\u0007\u001a&\u0010*\u001a\u00020+2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0012\u001a\u00020\u00152\u0006\u0010,\u001a\u00020\u00132\u0006\u0010-\u001a\u00020\u0017\u001a\u001c\u0010.\u001a\u00020\u001e*\f\u0012\u0004\u0012\u00020'0&j\u0002`(2\u0006\u0010\"\u001a\u00020\u0017\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u0019\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00010\b¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\n\"\u0019\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00010\b¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\r\u0010\n*\u0016\u0010/\"\b\u0012\u0004\u0012\u00020'0&2\b\u0012\u0004\u0012\u00020'0&¨\u00060"}, d2 = {"KEY_TV_CHANNEL_ID", "", "TAG", "TV_CHANNEL_PATH_APP", "TV_CHANNEL_PATH_VIDEO", "TV_CHANNEL_QUERY_VIDEO_ID", "TV_CHANNEL_SCHEME", "TV_PROGRAMS_MAP_PROJECTION", "", "getTV_PROGRAMS_MAP_PROJECTION", "()[Ljava/lang/String;", "[Ljava/lang/String;", "WATCH_NEXT_MAP_PROJECTION", "getWATCH_NEXT_MAP_PROJECTION", "buildProgram", "Landroidx/tvprovider/media/tv/PreviewProgram;", "cn", "Landroid/content/ComponentName;", "program", "Lvideolan/org/commontools/ProgramDesc;", "buildWatchNextProgram", "Landroidx/tvprovider/media/tv/WatchNextProgram;", "createOrUpdateChannel", "", "prefs", "Landroid/content/SharedPreferences;", "context", "Landroid/content/Context;", "name", "icon", "", "createUri", "Landroid/net/Uri;", "appId", "id", "deleteChannel", "deleteWatchNext", "existingPrograms", "", "Lvideolan/org/commontools/TvPreviewProgram;", "Lvideolan/org/commontools/ProgramsList;", "channelId", "updateWatchNext", "", "pDesc", "watchNextProgramId", "indexOfId", "ProgramsList", "tools_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: TvChannelUtils.kt */
public final class TvChannelUtilsKt {
    public static final String KEY_TV_CHANNEL_ID = "tv_channel_id";
    private static final String TAG = "VLC/TvChannelUtils";
    public static final String TV_CHANNEL_PATH_APP = "startapp";
    public static final String TV_CHANNEL_PATH_VIDEO = "video";
    public static final String TV_CHANNEL_QUERY_VIDEO_ID = "contentId";
    public static final String TV_CHANNEL_SCHEME = "vlclauncher";
    private static final String[] TV_PROGRAMS_MAP_PROJECTION = {"_id", "internal_provider_id", "title"};
    private static final String[] WATCH_NEXT_MAP_PROJECTION = {"_id", "internal_provider_id", "browsable", TvContractCompat.PreviewProgramColumns.COLUMN_CONTENT_ID};

    public static final String[] getTV_PROGRAMS_MAP_PROJECTION() {
        return TV_PROGRAMS_MAP_PROJECTION;
    }

    public static final String[] getWATCH_NEXT_MAP_PROJECTION() {
        return WATCH_NEXT_MAP_PROJECTION;
    }

    public static final long createOrUpdateChannel(SharedPreferences sharedPreferences, Context context, String str, int i) {
        Intrinsics.checkNotNullParameter(sharedPreferences, "prefs");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        long j = sharedPreferences.getLong(KEY_TV_CHANNEL_ID, -1);
        Channel.Builder displayName = new Channel.Builder().setType(TvContractCompat.Channels.TYPE_PREVIEW).setDisplayName(str);
        String packageName = context.getPackageName();
        Intrinsics.checkNotNullExpressionValue(packageName, "getPackageName(...)");
        Channel.Builder appLinkIntentUri = displayName.setAppLinkIntentUri(createUri$default(packageName, (String) null, 2, (Object) null));
        if (j == -1) {
            Uri insert = context.getContentResolver().insert(TvContractCompat.Channels.CONTENT_URI, appLinkIntentUri.build().toContentValues());
            if (insert == null) {
                return -1;
            }
            long parseId = ContentUris.parseId(insert);
            SettingsKt.putSingle(sharedPreferences, KEY_TV_CHANNEL_ID, Long.valueOf(parseId));
            TvContractCompat.requestChannelBrowsable(context, parseId);
            Resources resources = context.getResources();
            Intrinsics.checkNotNullExpressionValue(resources, "getResources(...)");
            ChannelLogoUtils.storeChannelLogo(context, parseId, KotlinExtensionsKt.getResourceUri(resources, i));
            return parseId;
        }
        context.getContentResolver().update(TvContractCompat.buildChannelUri(j), appLinkIntentUri.build().toContentValues(), (String) null, (String[]) null);
        return j;
    }

    public static final int deleteChannel(Context context, long j) {
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            return context.getContentResolver().delete(TvContractCompat.buildChannelUri(j), (String) null, (String[]) null);
        } catch (Exception e) {
            return Log.e(TAG, "faild to delete channel " + j, e);
        }
    }

    public static final List<TvPreviewProgram> existingPrograms(Context context, long j) {
        Intrinsics.checkNotNullParameter(context, "context");
        List<TvPreviewProgram> arrayList = new ArrayList<>();
        Cursor cursor = null;
        try {
            Cursor query = context.getContentResolver().query(TvContractCompat.buildPreviewProgramsUriForChannel(j), TV_PROGRAMS_MAP_PROJECTION, (String) null, (String[]) null, (String) null);
            while (query != null && query.moveToNext()) {
                long j2 = query.getLong(0);
                long j3 = query.getLong(1);
                String string = query.getString(2);
                Intrinsics.checkNotNull(string);
                arrayList.add(new TvPreviewProgram(j3, j2, string));
            }
            if (query != null) {
                query.close();
            }
            return arrayList;
        } catch (Exception e) {
            Log.e(TAG, "fail", e);
            if (cursor != null) {
                cursor.close();
            }
            return arrayList;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    public static /* synthetic */ Uri createUri$default(String str, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = null;
        }
        return createUri(str, str2);
    }

    public static final Uri createUri(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "appId");
        Uri.Builder authority = new Uri.Builder().scheme(TV_CHANNEL_SCHEME).authority(str);
        if (str2 != null) {
            authority.appendPath("video").appendQueryParameter(TV_CHANNEL_QUERY_VIDEO_ID, str2);
        } else {
            authority.appendPath(TV_CHANNEL_PATH_APP);
        }
        Uri build = authority.build();
        Intrinsics.checkNotNullExpressionValue(build, "build(...)");
        return build;
    }

    public static final PreviewProgram buildProgram(ComponentName componentName, ProgramDesc programDesc) {
        Intrinsics.checkNotNullParameter(componentName, "cn");
        Intrinsics.checkNotNullParameter(programDesc, "program");
        Uri build = TvContractCompat.buildPreviewProgramUri(programDesc.getId()).buildUpon().appendQueryParameter(TvContractCompat.PARAM_INPUT, TvContractCompat.buildInputId(componentName)).build();
        String valueOf = String.valueOf(programDesc.getId());
        PreviewProgram build2 = ((PreviewProgram.Builder) ((PreviewProgram.Builder) ((PreviewProgram.Builder) ((PreviewProgram.Builder) ((PreviewProgram.Builder) ((PreviewProgram.Builder) ((PreviewProgram.Builder) ((PreviewProgram.Builder) ((PreviewProgram.Builder) ((PreviewProgram.Builder) ((PreviewProgram.Builder) ((PreviewProgram.Builder) ((PreviewProgram.Builder) new PreviewProgram.Builder().setChannelId(programDesc.getChannelId()).setType(4)).setTitle(programDesc.getTitle())).setDurationMillis(programDesc.getDuration())).setLastPlaybackPositionMillis(programDesc.getTime())).setVideoHeight(programDesc.getHeight())).setVideoWidth(programDesc.getWidth())).setDescription(programDesc.getDescription())).setPosterArtUri(programDesc.getArtUri())).setPosterArtAspectRatio(0)).setIntentUri(createUri(programDesc.getAppId(), valueOf))).setInternalProviderId(valueOf)).setContentId(programDesc.getContentId())).setPreviewVideoUri(build)).build();
        Intrinsics.checkNotNullExpressionValue(build2, "build(...)");
        return build2;
    }

    public static final WatchNextProgram buildWatchNextProgram(ComponentName componentName, ProgramDesc programDesc) {
        Intrinsics.checkNotNullParameter(componentName, "cn");
        Intrinsics.checkNotNullParameter(programDesc, "program");
        Uri build = TvContractCompat.buildPreviewProgramUri(programDesc.getId()).buildUpon().appendQueryParameter(TvContractCompat.PARAM_INPUT, TvContractCompat.buildInputId(componentName)).build();
        String valueOf = String.valueOf(programDesc.getId());
        WatchNextProgram build2 = ((WatchNextProgram.Builder) ((WatchNextProgram.Builder) ((WatchNextProgram.Builder) ((WatchNextProgram.Builder) ((WatchNextProgram.Builder) ((WatchNextProgram.Builder) ((WatchNextProgram.Builder) ((WatchNextProgram.Builder) ((WatchNextProgram.Builder) ((WatchNextProgram.Builder) ((WatchNextProgram.Builder) ((WatchNextProgram.Builder) ((WatchNextProgram.Builder) new WatchNextProgram.Builder().setWatchNextType(0).setLastEngagementTimeUtcMillis(System.currentTimeMillis()).setType(4)).setTitle(programDesc.getTitle())).setDurationMillis(programDesc.getDuration())).setLastPlaybackPositionMillis(programDesc.getTime())).setVideoHeight(programDesc.getHeight())).setVideoWidth(programDesc.getWidth())).setDescription(programDesc.getDescription())).setPosterArtUri(programDesc.getArtUri())).setPosterArtAspectRatio(0)).setIntentUri(createUri(programDesc.getAppId(), valueOf))).setInternalProviderId(valueOf)).setContentId(programDesc.getContentId())).setPreviewVideoUri(build)).build();
        Intrinsics.checkNotNullExpressionValue(build2, "build(...)");
        return build2;
    }

    public static final void updateWatchNext(Context context, WatchNextProgram watchNextProgram, ProgramDesc programDesc, long j) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(watchNextProgram, "program");
        Intrinsics.checkNotNullParameter(programDesc, "pDesc");
        ContentValues contentValues = ((WatchNextProgram.Builder) ((WatchNextProgram.Builder) ((WatchNextProgram.Builder) new WatchNextProgram.Builder(watchNextProgram).setLastEngagementTimeUtcMillis(System.currentTimeMillis()).setLastPlaybackPositionMillis(programDesc.getTime())).setContentId(programDesc.getContentId())).setPosterArtUri(programDesc.getArtUri())).build().toContentValues();
        if (context.getContentResolver().update(TvContractCompat.buildWatchNextProgramUri(j), contentValues, (String) null, (String[]) null) < 1) {
            Log.e(TAG, "Update program failed");
        }
    }

    public static final int deleteWatchNext(Context context, long j) {
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            return context.getContentResolver().delete(TvContractCompat.buildWatchNextProgramUri(j), (String) null, (String[]) null);
        } catch (Exception e) {
            Log.e(TAG, "faild to delete program " + j, e);
            return -42;
        }
    }

    public static final int indexOfId(List<TvPreviewProgram> list, long j) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        int i = 0;
        for (TvPreviewProgram internalId : list) {
            int i2 = i + 1;
            if (internalId.getInternalId() == j) {
                return i;
            }
            i = i2;
        }
        return -1;
    }
}
