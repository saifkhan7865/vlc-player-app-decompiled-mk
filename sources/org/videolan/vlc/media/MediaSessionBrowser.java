package org.videolan.vlc.media;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.media.MediaDescriptionCompat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import org.videolan.medialibrary.interfaces.Medialibrary;
import org.videolan.medialibrary.interfaces.media.Album;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.medialibrary.media.SearchAggregate;
import org.videolan.resources.Constants;
import org.videolan.tools.KotlinExtensionsKt;
import org.videolan.tools.Settings;
import org.videolan.tools.SettingsKt;
import org.videolan.tools.Strings;
import org.videolan.vlc.ArtworkProvider;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.helpers.MediaComparators;
import org.videolan.vlc.util.TextUtils;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lorg/videolan/vlc/media/MediaSessionBrowser;", "", "()V", "Companion", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaSessionBrowser.kt */
public final class MediaSessionBrowser {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String ID_ALBUM = "//org.videolan.vlc/r/l/l";
    public static final String ID_ARTIST = "//org.videolan.vlc/r/l/r";
    public static final String ID_GENRE = "//org.videolan.vlc/r/l/g";
    public static final String ID_HISTORY = "//org.videolan.vlc/r/home/history";
    public static final String ID_HOME = "//org.videolan.vlc/r/home";
    public static final String ID_LAST_ADDED = "//org.videolan.vlc/r/home/last_added";
    private static final String ID_LIBRARY = "//org.videolan.vlc/r/l";
    public static final String ID_MEDIA = "//org.videolan.vlc/r/media";
    public static final String ID_NO_MEDIA = "//org.videolan.vlc/r/error/media";
    public static final String ID_NO_PLAYLIST = "//org.videolan.vlc/r/error/playlist";
    public static final String ID_PLAYLIST = "//org.videolan.vlc/r/playlist";
    public static final String ID_ROOT = "//org.videolan.vlc/r";
    public static final String ID_ROOT_NO_TABS = "//org.videolan.vlc/r?f=1";
    public static final String ID_SEARCH = "//org.videolan.vlc/r/search";
    public static final String ID_SHUFFLE_ALL = "//org.videolan.vlc/r/home/shuffle_all";
    public static final String ID_STREAM = "//org.videolan.vlc/r/stream";
    public static final String ID_SUGGESTED = "//org.videolan.vlc/r/suggested";
    public static final String ID_TRACK = "//org.videolan.vlc/r/l/t";
    public static final int MAX_COVER_ART_ITEMS = 50;
    public static final int MAX_HISTORY_SIZE = 100;
    public static final int MAX_RESULT_SIZE = 800;
    private static final int MAX_SUGGESTED_SIZE = 15;
    private static final String TAG = "VLC/MediaSessionBrowser";
    private static final MediaSessionBrowser instance = new MediaSessionBrowser();

    @Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0012\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J2\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020 0\u001f2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u00042\u0006\u0010$\u001a\u00020%2\n\b\u0002\u0010&\u001a\u0004\u0018\u00010'H\u0007J]\u0010(\u001a\b\u0012\u0004\u0012\u00020 0\u001f2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u00042\u0010\u0010)\u001a\f\u0012\u0006\b\u0001\u0012\u00020+\u0018\u00010*2\b\u0010,\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010-\u001a\u00020%2\b\b\u0002\u0010.\u001a\u00020%2\b\b\u0002\u0010/\u001a\u00020%H\u0002¢\u0006\u0002\u00100J\u0018\u00101\u001a\u00020\u00042\u0006\u00102\u001a\u00020\u00042\u0006\u00103\u001a\u00020\u0004H\u0002J&\u00104\u001a\b\u0012\u0004\u0012\u00020 0\u001f2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u00042\u0006\u00105\u001a\u000206H\u0002J\u000e\u00107\u001a\u00020\u00042\u0006\u00108\u001a\u00020+J\u001a\u00109\u001a\u00020'2\b\b\u0002\u0010:\u001a\u00020\u00172\b\b\u0002\u0010;\u001a\u00020\u0017J6\u0010<\u001a\u00020=2\u0006\u0010>\u001a\u00020\"2\u0006\u0010?\u001a\u00020\u00042\b\b\u0001\u0010@\u001a\u00020\u00172\u0006\u0010A\u001a\u00020\u00172\n\b\u0002\u0010B\u001a\u0004\u0018\u00010CH\u0002J\u000e\u0010D\u001a\u00020%2\u0006\u00108\u001a\u00020+J?\u0010E\u001a\b\u0012\u0004\u0012\u00020 0\u001f2\u000e\u0010F\u001a\n\u0012\u0006\b\u0001\u0012\u00020+0*2\u0006\u0010G\u001a\u00020C2\u0006\u0010H\u001a\u00020C2\n\b\u0002\u0010I\u001a\u0004\u0018\u00010'H\u0002¢\u0006\u0002\u0010JJ(\u0010K\u001a\b\u0012\u0004\u0012\u00020 0\u001f2\u0006\u0010!\u001a\u00020\"2\u0006\u0010L\u001a\u00020\u00042\b\u0010&\u001a\u0004\u0018\u00010'H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0017XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0017XT¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0017XT¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX\u0004¢\u0006\u0002\n\u0000¨\u0006M"}, d2 = {"Lorg/videolan/vlc/media/MediaSessionBrowser$Companion;", "", "()V", "ID_ALBUM", "", "ID_ARTIST", "ID_GENRE", "ID_HISTORY", "ID_HOME", "ID_LAST_ADDED", "ID_LIBRARY", "ID_MEDIA", "ID_NO_MEDIA", "ID_NO_PLAYLIST", "ID_PLAYLIST", "ID_ROOT", "ID_ROOT_NO_TABS", "ID_SEARCH", "ID_SHUFFLE_ALL", "ID_STREAM", "ID_SUGGESTED", "ID_TRACK", "MAX_COVER_ART_ITEMS", "", "MAX_HISTORY_SIZE", "MAX_RESULT_SIZE", "MAX_SUGGESTED_SIZE", "TAG", "instance", "Lorg/videolan/vlc/media/MediaSessionBrowser;", "browse", "", "Landroid/support/v4/media/MediaBrowserCompat$MediaItem;", "context", "Landroid/content/Context;", "parentId", "isShuffling", "", "rootHints", "Landroid/os/Bundle;", "buildMediaItems", "list", "", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "groupTitle", "limitSize", "suggestionMode", "androidAuto", "(Landroid/content/Context;Ljava/lang/String;[Lorg/videolan/medialibrary/media/MediaLibraryItem;Ljava/lang/String;ZZZ)Ljava/util/List;", "buildRangeLabel", "firstTitle", "lastTitle", "buildSuggestions", "ml", "Lorg/videolan/medialibrary/interfaces/Medialibrary;", "generateMediaId", "libraryItem", "getContentStyle", "browsableHint", "playableHint", "getPlayAllBuilder", "Landroid/support/v4/media/MediaDescriptionCompat$Builder;", "ctx", "mediaId", "title", "trackCount", "uri", "Landroid/net/Uri;", "isMediaAudio", "paginateLibrary", "mediaList", "parentIdUri", "iconUri", "extras", "([Lorg/videolan/medialibrary/media/MediaLibraryItem;Landroid/net/Uri;Landroid/net/Uri;Landroid/os/Bundle;)Ljava/util/List;", "search", "query", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: MediaSessionBrowser.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public static /* synthetic */ List browse$default(Companion companion, Context context, String str, boolean z, Bundle bundle, int i, Object obj) {
            if ((i & 8) != 0) {
                bundle = null;
            }
            return companion.browse(context, str, z, bundle);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:104:0x0503, code lost:
            r5 = r14;
            r3 = r19;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:105:0x0507, code lost:
            r14 = r28;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:119:0x057b, code lost:
            r14 = r5;
            r3 = r19;
            r5 = r4;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:147:0x06a5, code lost:
            r3 = r0;
            r11 = r5;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:148:0x06a7, code lost:
            r18 = r6;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:149:0x06ab, code lost:
            r0 = android.content.ContentUris.parseId(r3);
            r2 = java.lang.String.valueOf(org.videolan.tools.KotlinExtensionsKt.retrieveParent(r3));
            r3 = r2.hashCode();
            r17 = r5;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:150:0x06c4, code lost:
            if (r3 == -1979024705) goto L_0x07b4;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:152:0x06c9, code lost:
            if (r3 == -1979024700) goto L_0x079c;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:154:0x06ce, code lost:
            if (r3 == -1979024694) goto L_0x06d7;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:155:0x06d0, code lost:
            r18 = r6;
            r11 = r17;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:157:0x06db, code lost:
            if (r2.equals(r6) != false) goto L_0x06de;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:158:0x06de, code lost:
            r2 = r8.getArtist(r0);
            r8 = (org.videolan.medialibrary.media.MediaLibraryItem[]) r2.getAlbums();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:159:0x06e9, code lost:
            if (r8 == null) goto L_0x0794;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:161:0x06ed, code lost:
            if (r8.length <= 1) goto L_0x0794;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:162:0x06ef, code lost:
            r3 = r8.length;
            r11 = 0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:163:0x06f1, code lost:
            if (r11 >= r3) goto L_0x0726;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:164:0x06f3, code lost:
            r18 = (org.videolan.medialibrary.interfaces.media.Album) r8[r11];
            r19 = r18.getArtworkMrl();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:165:0x06fd, code lost:
            if (r19 == null) goto L_0x071b;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:167:0x0703, code lost:
            if (r19.length() != 0) goto L_0x0706;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:168:0x0706, code lost:
            r19 = r3;
            r3 = r18.getArtworkMrl();
            r18 = r6;
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, "getArtworkMrl(...)");
         */
        /* JADX WARNING: Code restructure failed: missing block: B:169:0x0717, code lost:
            if (org.videolan.vlc.FileProviderKt.isPathValid(r3) == false) goto L_0x071f;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:170:0x0719, code lost:
            r3 = true;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:171:0x071b, code lost:
            r19 = r3;
            r18 = r6;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:172:0x071f, code lost:
            r11 = r11 + 1;
            r6 = r18;
            r3 = r19;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:173:0x0726, code lost:
            r18 = r6;
            r3 = false;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:174:0x0729, code lost:
            if (r29 == false) goto L_0x0734;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:176:0x0730, code lost:
            if (r2.getTracksCount() <= 2) goto L_0x0734;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:177:0x0732, code lost:
            r6 = true;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:178:0x0734, code lost:
            r6 = false;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:179:0x0735, code lost:
            if (r3 == false) goto L_0x076c;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:180:0x0737, code lost:
            r5 = new android.net.Uri.Builder().appendPath(org.videolan.vlc.ArtworkProvider.PLAY_ALL).appendPath(org.videolan.vlc.ArtworkProvider.ARTIST).appendPath(java.lang.String.valueOf(r2.getTracksCount())).appendPath(java.lang.String.valueOf(r0)).appendQueryParameter(org.videolan.vlc.ArtworkProvider.SHUFFLE, java.lang.String.valueOf(org.videolan.tools.KotlinExtensionsKt.toInt(r6))).build();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:181:0x076c, code lost:
            r5 = null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:182:0x076d, code lost:
            if (r6 == false) goto L_0x0772;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:183:0x076f, code lost:
            r0 = org.videolan.vlc.R.string.shuffle_all_title;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:184:0x0772, code lost:
            r0 = org.videolan.vlc.R.string.play_all;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:185:0x0774, code lost:
            r11 = r17;
            r12.add(new android.support.v4.media.MediaBrowserCompat.MediaItem(getPlayAllBuilder(r27, r28, r0, r2.getTracksCount(), r5).build(), 2));
         */
        /* JADX WARNING: Code restructure failed: missing block: B:186:0x0794, code lost:
            r18 = r6;
            r11 = r17;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:187:0x0799, code lost:
            r3 = r8;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:188:0x079c, code lost:
            r18 = r6;
            r11 = r17;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:189:0x07a5, code lost:
            if (r2.equals(org.videolan.vlc.media.MediaSessionBrowser.ID_ALBUM) == false) goto L_0x07bf;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:190:0x07a7, code lost:
            r3 = (org.videolan.medialibrary.media.MediaLibraryItem[]) r8.getAlbum(r0).getTracks();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:191:0x07b4, code lost:
            r18 = r6;
            r11 = r17;
            r6 = 0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:192:0x07bd, code lost:
            if (r2.equals(org.videolan.vlc.media.MediaSessionBrowser.ID_GENRE) != false) goto L_0x07c2;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:193:0x07bf, code lost:
            r3 = null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:194:0x07c2, code lost:
            r8 = (org.videolan.medialibrary.media.MediaLibraryItem[]) r8.getGenre(r0).getAlbums();
            r2 = r8.length;
            r3 = 0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:195:0x07cf, code lost:
            if (r3 >= r2) goto L_0x07de;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:196:0x07d1, code lost:
            r6 = r6 + ((org.videolan.medialibrary.interfaces.media.Album) r8[r3]).getTracksCount();
            r3 = r3 + 1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:197:0x07de, code lost:
            if (r8 == null) goto L_0x0799;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:199:0x07e2, code lost:
            if (r8.length <= 1) goto L_0x0799;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:200:0x07e4, code lost:
            if (r29 == false) goto L_0x07eb;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:202:0x07e7, code lost:
            if (r6 <= 2) goto L_0x07eb;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:203:0x07e9, code lost:
            r2 = true;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:204:0x07eb, code lost:
            r2 = false;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:205:0x07ec, code lost:
            r5 = new android.net.Uri.Builder().appendPath(org.videolan.vlc.ArtworkProvider.PLAY_ALL).appendPath("genre").appendPath(java.lang.String.valueOf(r6)).appendPath(java.lang.String.valueOf(r0)).appendQueryParameter(org.videolan.vlc.ArtworkProvider.SHUFFLE, java.lang.String.valueOf(org.videolan.tools.KotlinExtensionsKt.toInt(r2))).build();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:206:0x081b, code lost:
            if (r2 == false) goto L_0x0820;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:207:0x081d, code lost:
            r0 = org.videolan.vlc.R.string.shuffle_all_title;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:208:0x0820, code lost:
            r0 = org.videolan.vlc.R.string.play_all;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:209:0x0822, code lost:
            r12.add(new android.support.v4.media.MediaBrowserCompat.MediaItem(getPlayAllBuilder(r27, r28, r0, r6, r5).build(), 2));
         */
        /* JADX WARNING: Code restructure failed: missing block: B:210:0x083d, code lost:
            r0 = kotlin.Unit.INSTANCE;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:211:0x083f, code lost:
            r5 = false;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:212:0x0840, code lost:
            r24 = r18;
            r10 = org.videolan.vlc.media.MediaSessionBrowser.ID_GENRE;
            r12.addAll(buildMediaItems$default(r26, r27, r28, r3, (java.lang.String) null, r5, false, r16, 32, (java.lang.Object) null));
         */
        /* JADX WARNING: Code restructure failed: missing block: B:213:0x0862, code lost:
            if (r12.isEmpty() == false) goto L_0x0906;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:214:0x0864, code lost:
            r0 = new android.support.v4.media.MediaDescriptionCompat.Builder().setMediaId(org.videolan.vlc.media.MediaSessionBrowser.ID_NO_MEDIA);
            kotlin.jvm.internal.Intrinsics.checkNotNull(r13);
            r0 = r0.setIconUri(org.videolan.tools.KotlinExtensionsKt.getResourceUri(r13, org.videolan.vlc.R.drawable.ic_auto_nothumb)).setTitle(r13.getString(org.videolan.vlc.R.string.search_no_result));
            r1 = new android.os.Bundle();
            r1.putInt("android.media.browse.CONTENT_STYLE_SINGLE_ITEM_HINT", 1);
            r0 = r0.setExtras(r1);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:215:0x089b, code lost:
            switch(r28.hashCode()) {
                case -1979024705: goto L_0x08ee;
                case -1979024700: goto L_0x08dd;
                case -1979024694: goto L_0x08ca;
                case -664866953: goto L_0x08b0;
                case 155640421: goto L_0x089f;
                default: goto L_0x089e;
            };
         */
        /* JADX WARNING: Code restructure failed: missing block: B:217:0x08a3, code lost:
            if (r14.equals(r11) != false) goto L_0x08a6;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:218:0x08a6, code lost:
            r0.setIconUri(org.videolan.tools.KotlinExtensionsKt.getResourceUri(r13, org.videolan.vlc.R.drawable.ic_auto_stream_unknown));
         */
        /* JADX WARNING: Code restructure failed: missing block: B:220:0x08b6, code lost:
            if (r14.equals(r23) != false) goto L_0x08b9;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:221:0x08b9, code lost:
            r0.setMediaId(org.videolan.vlc.media.MediaSessionBrowser.ID_NO_PLAYLIST);
            r0.setTitle(r13.getString(org.videolan.vlc.R.string.noplaylist));
         */
        /* JADX WARNING: Code restructure failed: missing block: B:223:0x08d0, code lost:
            if (r14.equals(r24) != false) goto L_0x08d3;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:224:0x08d3, code lost:
            r0.setIconUri(org.videolan.tools.KotlinExtensionsKt.getResourceUri(r13, org.videolan.vlc.R.drawable.ic_auto_artist_unknown));
         */
        /* JADX WARNING: Code restructure failed: missing block: B:226:0x08e1, code lost:
            if (r14.equals(org.videolan.vlc.media.MediaSessionBrowser.ID_ALBUM) != false) goto L_0x08e4;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:227:0x08e4, code lost:
            r0.setIconUri(org.videolan.tools.KotlinExtensionsKt.getResourceUri(r13, org.videolan.vlc.R.drawable.ic_auto_album_unknown));
         */
        /* JADX WARNING: Code restructure failed: missing block: B:229:0x08f2, code lost:
            if (r14.equals(r10) != false) goto L_0x08f5;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:230:0x08f5, code lost:
            r0.setIconUri((android.net.Uri) null);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:231:0x08f9, code lost:
            r12.add(new android.support.v4.media.MediaBrowserCompat.MediaItem(r0.build(), 2));
         */
        /* JADX WARNING: Code restructure failed: missing block: B:233:0x0908, code lost:
            return r12;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:68:0x037f, code lost:
            r23 = r6;
            r3 = r19;
            r6 = org.videolan.vlc.media.MediaSessionBrowser.ID_ARTIST;
            r5 = r14;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:70:0x03a5, code lost:
            r14 = r28;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:9:0x0079, code lost:
            r14 = r28;
            r23 = r30;
            r3 = r19;
            r25 = org.videolan.vlc.media.MediaSessionBrowser.ID_STREAM;
            r6 = org.videolan.vlc.media.MediaSessionBrowser.ID_ARTIST;
            r5 = r25;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final java.util.List<android.support.v4.media.MediaBrowserCompat.MediaItem> browse(android.content.Context r27, java.lang.String r28, boolean r29, android.os.Bundle r30) {
            /*
                r26 = this;
                r10 = r26
                r7 = r27
                r11 = r28
                r0 = r30
                java.lang.String r1 = "context"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r1)
                java.lang.String r1 = "parentId"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r11, r1)
                java.util.ArrayList r12 = new java.util.ArrayList
                r12.<init>()
                android.content.res.Resources r13 = r27.getResources()
                org.videolan.medialibrary.interfaces.Medialibrary r8 = org.videolan.medialibrary.interfaces.Medialibrary.getInstance()
                java.lang.String r1 = "getInstance(...)"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, r1)
                android.net.Uri r6 = android.net.Uri.parse(r28)
                java.lang.String r1 = "p"
                java.lang.String r9 = r6.getQueryParameter(r1)
                r14 = 800(0x320, float:1.121E-42)
                r15 = 0
                if (r9 == 0) goto L_0x003b
                int r1 = java.lang.Integer.parseInt(r9)
                int r1 = r1 * 800
                r5 = r1
                goto L_0x003c
            L_0x003b:
                r5 = 0
            L_0x003c:
                if (r0 == 0) goto L_0x0047
                java.lang.String r1 = "com.google.android.gms.car.media.BrowserIconSize"
                boolean r0 = r0.containsKey(r1)
                r16 = r0
                goto L_0x0049
            L_0x0047:
                r16 = 0
            L_0x0049:
                java.lang.String r0 = "f"
                boolean r17 = r6.getBooleanQueryParameter(r0, r15)
                android.net.Uri r0 = org.videolan.tools.KotlinExtensionsKt.removeQuery(r6)
                java.lang.String r0 = java.lang.String.valueOf(r0)
                int r1 = r0.hashCode()
                java.lang.String r2 = "artists_show_all"
                java.lang.String r4 = "//org.videolan.vlc/r/l"
                java.lang.String r3 = "//org.videolan.vlc/r/home"
                java.lang.String r14 = "//org.videolan.vlc/r/l/t"
                r19 = r6
                java.lang.String r6 = "//org.videolan.vlc/r/stream"
                r20 = r5
                java.lang.String r5 = "//org.videolan.vlc/r/playlist"
                r30 = r5
                java.lang.String r5 = "//org.videolan.vlc/r/l/r"
                java.lang.String r15 = "//org.videolan.vlc/r/l/l"
                r21 = r9
                java.lang.String r9 = "//org.videolan.vlc/r/l/g"
                r11 = 0
                switch(r1) {
                    case -1979024705: goto L_0x0649;
                    case -1979024700: goto L_0x05ed;
                    case -1979024694: goto L_0x056d;
                    case -1979024692: goto L_0x0512;
                    case -1249447394: goto L_0x04f7;
                    case -1220281084: goto L_0x03a9;
                    case -1122674686: goto L_0x0374;
                    case -664866953: goto L_0x034e;
                    case -339740982: goto L_0x0228;
                    case -73567641: goto L_0x0109;
                    case 10476169: goto L_0x00aa;
                    case 155640421: goto L_0x0087;
                    default: goto L_0x0079;
                }
            L_0x0079:
                r14 = r28
                r23 = r30
                r3 = r19
                r4 = 0
                r25 = r6
                r6 = r5
                r5 = r25
                goto L_0x06ab
            L_0x0087:
                boolean r0 = r0.equals(r6)
                if (r0 != 0) goto L_0x008e
                goto L_0x0079
            L_0x008e:
                r0 = 2
                org.videolan.medialibrary.interfaces.media.MediaWrapper[] r1 = r8.history(r0)
                org.videolan.medialibrary.media.MediaLibraryItem[] r1 = (org.videolan.medialibrary.media.MediaLibraryItem[]) r1
                org.videolan.vlc.gui.helpers.MediaComparators r0 = org.videolan.vlc.gui.helpers.MediaComparators.INSTANCE
                java.util.Comparator r0 = r0.getANDROID_AUTO()
                kotlin.collections.ArraysKt.sortWith(r1, r0)
                kotlin.Unit r0 = kotlin.Unit.INSTANCE
                r14 = r28
                r23 = r30
                r3 = r1
                r18 = r5
                r11 = r6
                goto L_0x083f
            L_0x00aa:
                java.lang.String r1 = "//org.videolan.vlc/r/home/history"
                boolean r0 = r0.equals(r1)
                if (r0 != 0) goto L_0x00b3
                goto L_0x0079
            L_0x00b3:
                r0 = 1
                org.videolan.medialibrary.interfaces.media.MediaWrapper[] r1 = r8.history(r0)
                if (r1 == 0) goto L_0x00f9
                java.util.List r0 = kotlin.collections.ArraysKt.toList((T[]) r1)
                if (r0 == 0) goto L_0x00f9
                java.lang.Iterable r0 = (java.lang.Iterable) r0
                java.util.ArrayList r1 = new java.util.ArrayList
                r1.<init>()
                java.util.Collection r1 = (java.util.Collection) r1
                java.util.Iterator r0 = r0.iterator()
            L_0x00cd:
                boolean r2 = r0.hasNext()
                if (r2 == 0) goto L_0x00eb
                java.lang.Object r2 = r0.next()
                r3 = r2
                org.videolan.medialibrary.interfaces.media.MediaWrapper r3 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r3
                org.videolan.vlc.media.MediaSessionBrowser$Companion r4 = org.videolan.vlc.media.MediaSessionBrowser.Companion
                kotlin.jvm.internal.Intrinsics.checkNotNull(r3)
                org.videolan.medialibrary.media.MediaLibraryItem r3 = (org.videolan.medialibrary.media.MediaLibraryItem) r3
                boolean r3 = r4.isMediaAudio(r3)
                if (r3 == 0) goto L_0x00cd
                r1.add(r2)
                goto L_0x00cd
            L_0x00eb:
                java.util.List r1 = (java.util.List) r1
                java.util.Collection r1 = (java.util.Collection) r1
                r0 = 0
                org.videolan.medialibrary.interfaces.media.MediaWrapper[] r0 = new org.videolan.medialibrary.interfaces.media.MediaWrapper[r0]
                java.lang.Object[] r0 = r1.toArray(r0)
                org.videolan.medialibrary.interfaces.media.MediaWrapper[] r0 = (org.videolan.medialibrary.interfaces.media.MediaWrapper[]) r0
                goto L_0x00fa
            L_0x00f9:
                r0 = r11
            L_0x00fa:
                org.videolan.medialibrary.media.MediaLibraryItem[] r0 = (org.videolan.medialibrary.media.MediaLibraryItem[]) r0
                kotlin.Unit r1 = kotlin.Unit.INSTANCE
                r14 = r28
                r23 = r30
                r3 = r0
                r18 = r5
                r11 = r6
                r5 = 1
                goto L_0x0840
            L_0x0109:
                boolean r0 = r0.equals(r4)
                if (r0 != 0) goto L_0x0111
                goto L_0x0079
            L_0x0111:
                org.videolan.tools.Settings r0 = org.videolan.tools.Settings.INSTANCE
                java.lang.Object r0 = r0.getInstance(r7)
                android.content.SharedPreferences r0 = (android.content.SharedPreferences) r0
                r1 = 0
                boolean r0 = r0.getBoolean(r2, r1)
                int r0 = r8.getArtistsCount((boolean) r0)
                android.support.v4.media.MediaDescriptionCompat$Builder r1 = new android.support.v4.media.MediaDescriptionCompat$Builder
                r1.<init>()
                android.support.v4.media.MediaDescriptionCompat$Builder r1 = r1.setMediaId(r5)
                int r2 = org.videolan.vlc.R.string.artists
                java.lang.String r2 = r13.getString(r2)
                java.lang.CharSequence r2 = (java.lang.CharSequence) r2
                android.support.v4.media.MediaDescriptionCompat$Builder r1 = r1.setTitle(r2)
                kotlin.jvm.internal.Intrinsics.checkNotNull(r13)
                int r2 = org.videolan.vlc.R.drawable.ic_auto_artist
                android.net.Uri r2 = org.videolan.tools.KotlinExtensionsKt.getResourceUri(r13, r2)
                android.support.v4.media.MediaDescriptionCompat$Builder r1 = r1.setIconUri(r2)
                r2 = 800(0x320, float:1.121E-42)
                if (r0 <= r2) goto L_0x0150
                r0 = 3
                r2 = 2
                r3 = 0
                android.os.Bundle r4 = getContentStyle$default(r10, r0, r3, r2, r11)
                goto L_0x0151
            L_0x0150:
                r4 = r11
            L_0x0151:
                android.support.v4.media.MediaDescriptionCompat$Builder r0 = r1.setExtras(r4)
                android.support.v4.media.MediaDescriptionCompat r0 = r0.build()
                android.support.v4.media.MediaBrowserCompat$MediaItem r1 = new android.support.v4.media.MediaBrowserCompat$MediaItem
                r2 = 1
                r1.<init>(r0, r2)
                r12.add(r1)
                android.support.v4.media.MediaDescriptionCompat$Builder r0 = new android.support.v4.media.MediaDescriptionCompat$Builder
                r0.<init>()
                android.support.v4.media.MediaDescriptionCompat$Builder r0 = r0.setMediaId(r15)
                int r1 = org.videolan.vlc.R.string.albums
                java.lang.String r1 = r13.getString(r1)
                java.lang.CharSequence r1 = (java.lang.CharSequence) r1
                android.support.v4.media.MediaDescriptionCompat$Builder r0 = r0.setTitle(r1)
                int r1 = org.videolan.vlc.R.drawable.ic_auto_album
                android.net.Uri r1 = org.videolan.tools.KotlinExtensionsKt.getResourceUri(r13, r1)
                android.support.v4.media.MediaDescriptionCompat$Builder r0 = r0.setIconUri(r1)
                int r1 = r8.getAlbumsCount()
                r2 = 800(0x320, float:1.121E-42)
                if (r1 <= r2) goto L_0x018b
                r1 = 3
                goto L_0x018c
            L_0x018b:
                r1 = 2
            L_0x018c:
                r2 = 2
                r3 = 0
                android.os.Bundle r1 = getContentStyle$default(r10, r1, r3, r2, r11)
                android.support.v4.media.MediaDescriptionCompat$Builder r0 = r0.setExtras(r1)
                android.support.v4.media.MediaDescriptionCompat r0 = r0.build()
                android.support.v4.media.MediaBrowserCompat$MediaItem r1 = new android.support.v4.media.MediaBrowserCompat$MediaItem
                r2 = 1
                r1.<init>(r0, r2)
                r12.add(r1)
                android.support.v4.media.MediaDescriptionCompat$Builder r0 = new android.support.v4.media.MediaDescriptionCompat$Builder
                r0.<init>()
                android.support.v4.media.MediaDescriptionCompat$Builder r0 = r0.setMediaId(r14)
                int r1 = org.videolan.vlc.R.string.tracks
                java.lang.String r1 = r13.getString(r1)
                java.lang.CharSequence r1 = (java.lang.CharSequence) r1
                android.support.v4.media.MediaDescriptionCompat$Builder r0 = r0.setTitle(r1)
                int r1 = org.videolan.vlc.R.drawable.ic_auto_audio
                android.net.Uri r1 = org.videolan.tools.KotlinExtensionsKt.getResourceUri(r13, r1)
                android.support.v4.media.MediaDescriptionCompat$Builder r0 = r0.setIconUri(r1)
                int r1 = r8.getAudioCount()
                r2 = 800(0x320, float:1.121E-42)
                if (r1 <= r2) goto L_0x01cc
                r1 = 3
                goto L_0x01cd
            L_0x01cc:
                r1 = 1
            L_0x01cd:
                r2 = 2
                r3 = 0
                android.os.Bundle r1 = getContentStyle$default(r10, r1, r3, r2, r11)
                android.support.v4.media.MediaDescriptionCompat$Builder r0 = r0.setExtras(r1)
                android.support.v4.media.MediaDescriptionCompat r0 = r0.build()
                android.support.v4.media.MediaBrowserCompat$MediaItem r1 = new android.support.v4.media.MediaBrowserCompat$MediaItem
                r2 = 1
                r1.<init>(r0, r2)
                r12.add(r1)
                android.support.v4.media.MediaDescriptionCompat$Builder r0 = new android.support.v4.media.MediaDescriptionCompat$Builder
                r0.<init>()
                android.support.v4.media.MediaDescriptionCompat$Builder r0 = r0.setMediaId(r9)
                int r1 = org.videolan.vlc.R.string.genres
                java.lang.String r1 = r13.getString(r1)
                java.lang.CharSequence r1 = (java.lang.CharSequence) r1
                android.support.v4.media.MediaDescriptionCompat$Builder r0 = r0.setTitle(r1)
                int r1 = org.videolan.vlc.R.drawable.ic_auto_genre
                android.net.Uri r1 = org.videolan.tools.KotlinExtensionsKt.getResourceUri(r13, r1)
                android.support.v4.media.MediaDescriptionCompat$Builder r0 = r0.setIconUri(r1)
                int r1 = r8.getGenresCount()
                r2 = 800(0x320, float:1.121E-42)
                if (r1 <= r2) goto L_0x020d
                r1 = 3
                goto L_0x020e
            L_0x020d:
                r1 = 1
            L_0x020e:
                r2 = 2
                r3 = 0
                android.os.Bundle r1 = getContentStyle$default(r10, r1, r3, r2, r11)
                android.support.v4.media.MediaDescriptionCompat$Builder r0 = r0.setExtras(r1)
                android.support.v4.media.MediaDescriptionCompat r0 = r0.build()
                android.support.v4.media.MediaBrowserCompat$MediaItem r1 = new android.support.v4.media.MediaBrowserCompat$MediaItem
                r2 = 1
                r1.<init>(r0, r2)
                r12.add(r1)
                java.util.List r12 = (java.util.List) r12
                return r12
            L_0x0228:
                java.lang.String r1 = "//org.videolan.vlc/r"
                boolean r0 = r0.equals(r1)
                if (r0 != 0) goto L_0x0232
                goto L_0x0079
            L_0x0232:
                if (r17 == 0) goto L_0x0256
                r5 = 8
                r8 = 0
                java.lang.String r2 = "//org.videolan.vlc/r/home"
                r9 = 0
                r0 = r26
                r1 = r27
                r3 = r29
                r14 = r4
                r4 = r9
                r9 = r30
                r15 = r6
                r6 = r8
                java.util.List r0 = browse$default(r0, r1, r2, r3, r4, r5, r6)
                if (r0 == 0) goto L_0x0292
                java.util.Collection r0 = (java.util.Collection) r0
                boolean r0 = r12.addAll(r0)
                java.lang.Boolean.valueOf(r0)
                goto L_0x0292
            L_0x0256:
                r9 = r30
                r14 = r4
                r15 = r6
                android.support.v4.media.MediaDescriptionCompat$Builder r0 = new android.support.v4.media.MediaDescriptionCompat$Builder
                r0.<init>()
                android.support.v4.media.MediaDescriptionCompat$Builder r0 = r0.setMediaId(r3)
                int r1 = org.videolan.vlc.R.string.auto_home
                java.lang.String r1 = r13.getString(r1)
                java.lang.CharSequence r1 = (java.lang.CharSequence) r1
                android.support.v4.media.MediaDescriptionCompat$Builder r0 = r0.setTitle(r1)
                kotlin.jvm.internal.Intrinsics.checkNotNull(r13)
                int r1 = org.videolan.vlc.R.drawable.ic_auto_home
                android.net.Uri r1 = org.videolan.tools.KotlinExtensionsKt.getResourceUri(r13, r1)
                android.support.v4.media.MediaDescriptionCompat$Builder r0 = r0.setIconUri(r1)
                r1 = 2
                android.os.Bundle r2 = r10.getContentStyle(r1, r1)
                android.support.v4.media.MediaDescriptionCompat$Builder r0 = r0.setExtras(r2)
                android.support.v4.media.MediaDescriptionCompat r0 = r0.build()
                android.support.v4.media.MediaBrowserCompat$MediaItem r1 = new android.support.v4.media.MediaBrowserCompat$MediaItem
                r2 = 1
                r1.<init>(r0, r2)
                r12.add(r1)
            L_0x0292:
                android.support.v4.media.MediaDescriptionCompat$Builder r0 = new android.support.v4.media.MediaDescriptionCompat$Builder
                r0.<init>()
                android.support.v4.media.MediaDescriptionCompat$Builder r0 = r0.setMediaId(r9)
                int r1 = org.videolan.vlc.R.string.playlists
                java.lang.String r1 = r13.getString(r1)
                java.lang.CharSequence r1 = (java.lang.CharSequence) r1
                android.support.v4.media.MediaDescriptionCompat$Builder r0 = r0.setTitle(r1)
                kotlin.jvm.internal.Intrinsics.checkNotNull(r13)
                int r1 = org.videolan.vlc.R.drawable.ic_auto_playlist
                android.net.Uri r1 = org.videolan.tools.KotlinExtensionsKt.getResourceUri(r13, r1)
                android.support.v4.media.MediaDescriptionCompat$Builder r0 = r0.setIconUri(r1)
                r1 = 2
                android.os.Bundle r2 = r10.getContentStyle(r1, r1)
                android.support.v4.media.MediaDescriptionCompat$Builder r0 = r0.setExtras(r2)
                android.support.v4.media.MediaDescriptionCompat r0 = r0.build()
                android.support.v4.media.MediaBrowserCompat$MediaItem r1 = new android.support.v4.media.MediaBrowserCompat$MediaItem
                r2 = 1
                r1.<init>(r0, r2)
                r12.add(r1)
                if (r17 == 0) goto L_0x02e8
                r5 = 8
                r6 = 0
                java.lang.String r2 = "//org.videolan.vlc/r/l"
                r4 = 0
                r0 = r26
                r1 = r27
                r3 = r29
                java.util.List r0 = browse$default(r0, r1, r2, r3, r4, r5, r6)
                if (r0 == 0) goto L_0x031f
                java.util.Collection r0 = (java.util.Collection) r0
                boolean r0 = r12.addAll(r0)
                java.lang.Boolean.valueOf(r0)
                goto L_0x031f
            L_0x02e8:
                android.support.v4.media.MediaDescriptionCompat$Builder r0 = new android.support.v4.media.MediaDescriptionCompat$Builder
                r0.<init>()
                android.support.v4.media.MediaDescriptionCompat$Builder r0 = r0.setMediaId(r14)
                int r1 = org.videolan.vlc.R.string.auto_my_library
                java.lang.String r1 = r13.getString(r1)
                java.lang.CharSequence r1 = (java.lang.CharSequence) r1
                android.support.v4.media.MediaDescriptionCompat$Builder r0 = r0.setTitle(r1)
                int r1 = org.videolan.vlc.R.drawable.ic_auto_my_library
                android.net.Uri r1 = org.videolan.tools.KotlinExtensionsKt.getResourceUri(r13, r1)
                android.support.v4.media.MediaDescriptionCompat$Builder r0 = r0.setIconUri(r1)
                r1 = 3
                r2 = 2
                r3 = 0
                android.os.Bundle r1 = getContentStyle$default(r10, r1, r3, r2, r11)
                android.support.v4.media.MediaDescriptionCompat$Builder r0 = r0.setExtras(r1)
                android.support.v4.media.MediaDescriptionCompat r0 = r0.build()
                android.support.v4.media.MediaBrowserCompat$MediaItem r1 = new android.support.v4.media.MediaBrowserCompat$MediaItem
                r2 = 1
                r1.<init>(r0, r2)
                r12.add(r1)
            L_0x031f:
                android.support.v4.media.MediaDescriptionCompat$Builder r0 = new android.support.v4.media.MediaDescriptionCompat$Builder
                r0.<init>()
                android.support.v4.media.MediaDescriptionCompat$Builder r0 = r0.setMediaId(r15)
                int r1 = org.videolan.vlc.R.string.streams
                java.lang.String r1 = r13.getString(r1)
                java.lang.CharSequence r1 = (java.lang.CharSequence) r1
                android.support.v4.media.MediaDescriptionCompat$Builder r0 = r0.setTitle(r1)
                int r1 = org.videolan.vlc.R.drawable.ic_auto_stream
                android.net.Uri r1 = org.videolan.tools.KotlinExtensionsKt.getResourceUri(r13, r1)
                android.support.v4.media.MediaDescriptionCompat$Builder r0 = r0.setIconUri(r1)
                android.support.v4.media.MediaDescriptionCompat r0 = r0.build()
                android.support.v4.media.MediaBrowserCompat$MediaItem r1 = new android.support.v4.media.MediaBrowserCompat$MediaItem
                r2 = 1
                r1.<init>(r0, r2)
                r12.add(r1)
                java.util.List r12 = (java.util.List) r12
                return r12
            L_0x034e:
                r14 = r6
                r6 = r30
                boolean r0 = r0.equals(r6)
                if (r0 != 0) goto L_0x0358
                goto L_0x037f
            L_0x0358:
                org.videolan.medialibrary.interfaces.media.Playlist$Type r0 = org.videolan.medialibrary.interfaces.media.Playlist.Type.Audio
                r1 = 0
                org.videolan.medialibrary.interfaces.media.Playlist[] r0 = r8.getPlaylists(r0, r1)
                org.videolan.medialibrary.media.MediaLibraryItem[] r0 = (org.videolan.medialibrary.media.MediaLibraryItem[]) r0
                org.videolan.vlc.gui.helpers.MediaComparators r1 = org.videolan.vlc.gui.helpers.MediaComparators.INSTANCE
                java.util.Comparator r1 = r1.getANDROID_AUTO()
                kotlin.collections.ArraysKt.sortWith(r0, r1)
                kotlin.Unit r1 = kotlin.Unit.INSTANCE
                r3 = r0
                r18 = r5
                r23 = r6
                r11 = r14
                r5 = 0
                goto L_0x03a5
            L_0x0374:
                r14 = r6
                r6 = r30
                java.lang.String r1 = "//org.videolan.vlc/r/home/last_added"
                boolean r0 = r0.equals(r1)
                if (r0 != 0) goto L_0x0388
            L_0x037f:
                r23 = r6
                r3 = r19
                r4 = 0
                r6 = r5
                r5 = r14
                goto L_0x0507
            L_0x0388:
                r17 = 100
                r18 = 0
                r1 = 3
                r2 = 1
                r3 = 0
                r4 = 0
                r0 = r8
                r8 = r5
                r5 = r17
                r23 = r6
                r6 = r18
                org.videolan.medialibrary.interfaces.media.MediaWrapper[] r0 = r0.getPagedAudio(r1, r2, r3, r4, r5, r6)
                org.videolan.medialibrary.media.MediaLibraryItem[] r0 = (org.videolan.medialibrary.media.MediaLibraryItem[]) r0
                kotlin.Unit r1 = kotlin.Unit.INSTANCE
                r3 = r0
                r18 = r8
                r11 = r14
                r5 = 1
            L_0x03a5:
                r14 = r28
                goto L_0x0840
            L_0x03a9:
                r23 = r30
                r14 = r6
                r6 = r5
                boolean r0 = r0.equals(r3)
                if (r0 != 0) goto L_0x03b5
                goto L_0x0503
            L_0x03b5:
                int r4 = r8.getAudioCount()
                if (r4 <= 0) goto L_0x03e0
                android.net.Uri$Builder r0 = new android.net.Uri$Builder
                r0.<init>()
                java.lang.String r1 = "shuffle_all"
                android.net.Uri$Builder r0 = r0.appendPath(r1)
                org.videolan.vlc.ArtworkProvider$Companion r1 = org.videolan.vlc.ArtworkProvider.Companion
                r2 = 1
                r3 = 0
                java.lang.String r1 = org.videolan.vlc.ArtworkProvider.Companion.computeExpiration$default(r1, r3, r2, r11)
                android.net.Uri$Builder r0 = r0.appendPath(r1)
                java.lang.String r1 = java.lang.String.valueOf(r4)
                android.net.Uri$Builder r0 = r0.appendPath(r1)
                android.net.Uri r0 = r0.build()
                r5 = r0
                goto L_0x03e1
            L_0x03e0:
                r5 = r11
            L_0x03e1:
                java.lang.String r2 = "//org.videolan.vlc/r/home/shuffle_all"
                int r3 = org.videolan.vlc.R.string.shuffle_all_title
                r0 = r26
                r1 = r27
                android.support.v4.media.MediaDescriptionCompat$Builder r0 = r0.getPlayAllBuilder(r1, r2, r3, r4, r5)
                android.support.v4.media.MediaDescriptionCompat r0 = r0.build()
                android.support.v4.media.MediaBrowserCompat$MediaItem r1 = new android.support.v4.media.MediaBrowserCompat$MediaItem
                r2 = 2
                r1.<init>(r0, r2)
                r12.add(r1)
                r5 = 100
                r6 = 0
                r1 = 3
                r2 = 1
                r3 = 0
                r4 = 0
                r0 = r8
                org.videolan.medialibrary.interfaces.media.MediaWrapper[] r0 = r0.getPagedAudio(r1, r2, r3, r4, r5, r6)
                int r4 = r0.length
                if (r4 <= 0) goto L_0x0439
                android.net.Uri$Builder r1 = new android.net.Uri$Builder
                r1.<init>()
                java.lang.String r2 = "last_added"
                android.net.Uri$Builder r1 = r1.appendPath(r2)
                org.videolan.vlc.ArtworkProvider$Companion r2 = org.videolan.vlc.ArtworkProvider.Companion
                kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
                java.util.List r0 = kotlin.collections.ArraysKt.toList((T[]) r0)
                r3 = 2
                r5 = 0
                long r13 = org.videolan.vlc.ArtworkProvider.Companion.computeChecksum$default(r2, r0, r5, r3, r11)
                java.lang.String r0 = java.lang.String.valueOf(r13)
                android.net.Uri$Builder r0 = r1.appendPath(r0)
                java.lang.String r1 = java.lang.String.valueOf(r4)
                android.net.Uri$Builder r0 = r0.appendPath(r1)
                android.net.Uri r0 = r0.build()
                r5 = r0
                goto L_0x043a
            L_0x0439:
                r5 = r11
            L_0x043a:
                java.lang.String r2 = "//org.videolan.vlc/r/home/last_added"
                int r3 = org.videolan.vlc.R.string.auto_last_added_media
                r0 = r26
                r1 = r27
                android.support.v4.media.MediaDescriptionCompat$Builder r0 = r0.getPlayAllBuilder(r1, r2, r3, r4, r5)
                android.support.v4.media.MediaDescriptionCompat r0 = r0.build()
                android.support.v4.media.MediaBrowserCompat$MediaItem r1 = new android.support.v4.media.MediaBrowserCompat$MediaItem
                r2 = 1
                r1.<init>(r0, r2)
                r12.add(r1)
                org.videolan.tools.Settings r0 = org.videolan.tools.Settings.INSTANCE
                java.lang.Object r0 = r0.getInstance(r7)
                android.content.SharedPreferences r0 = (android.content.SharedPreferences) r0
                java.lang.String r1 = "playback_history"
                boolean r0 = r0.getBoolean(r1, r2)
                if (r0 == 0) goto L_0x04f4
                org.videolan.medialibrary.interfaces.media.MediaWrapper[] r0 = r8.history(r2)
                if (r0 == 0) goto L_0x049d
                java.util.List r0 = kotlin.collections.ArraysKt.toList((T[]) r0)
                if (r0 == 0) goto L_0x049d
                java.lang.Iterable r0 = (java.lang.Iterable) r0
                java.util.ArrayList r1 = new java.util.ArrayList
                r1.<init>()
                java.util.Collection r1 = (java.util.Collection) r1
                java.util.Iterator r0 = r0.iterator()
            L_0x047c:
                boolean r2 = r0.hasNext()
                if (r2 == 0) goto L_0x049a
                java.lang.Object r2 = r0.next()
                r3 = r2
                org.videolan.medialibrary.interfaces.media.MediaWrapper r3 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r3
                org.videolan.vlc.media.MediaSessionBrowser$Companion r4 = org.videolan.vlc.media.MediaSessionBrowser.Companion
                kotlin.jvm.internal.Intrinsics.checkNotNull(r3)
                org.videolan.medialibrary.media.MediaLibraryItem r3 = (org.videolan.medialibrary.media.MediaLibraryItem) r3
                boolean r3 = r4.isMediaAudio(r3)
                if (r3 == 0) goto L_0x047c
                r1.add(r2)
                goto L_0x047c
            L_0x049a:
                java.util.List r1 = (java.util.List) r1
                goto L_0x049e
            L_0x049d:
                r1 = r11
            L_0x049e:
                r0 = r1
                java.util.Collection r0 = (java.util.Collection) r0
                if (r0 == 0) goto L_0x04f4
                boolean r0 = r0.isEmpty()
                if (r0 == 0) goto L_0x04aa
                goto L_0x04f4
            L_0x04aa:
                int r0 = r1.size()
                r2 = 100
                int r4 = kotlin.ranges.RangesKt.coerceAtMost((int) r0, (int) r2)
                android.net.Uri$Builder r0 = new android.net.Uri$Builder
                r0.<init>()
                java.lang.String r2 = "history"
                android.net.Uri$Builder r0 = r0.appendPath(r2)
                org.videolan.vlc.ArtworkProvider$Companion r2 = org.videolan.vlc.ArtworkProvider.Companion
                r3 = 2
                r5 = 0
                long r1 = org.videolan.vlc.ArtworkProvider.Companion.computeChecksum$default(r2, r1, r5, r3, r11)
                java.lang.String r1 = java.lang.String.valueOf(r1)
                android.net.Uri$Builder r0 = r0.appendPath(r1)
                java.lang.String r1 = java.lang.String.valueOf(r4)
                android.net.Uri$Builder r0 = r0.appendPath(r1)
                android.net.Uri r5 = r0.build()
                java.lang.String r2 = "//org.videolan.vlc/r/home/history"
                int r3 = org.videolan.vlc.R.string.history
                r0 = r26
                r1 = r27
                android.support.v4.media.MediaDescriptionCompat$Builder r0 = r0.getPlayAllBuilder(r1, r2, r3, r4, r5)
                android.support.v4.media.MediaDescriptionCompat r0 = r0.build()
                android.support.v4.media.MediaBrowserCompat$MediaItem r1 = new android.support.v4.media.MediaBrowserCompat$MediaItem
                r2 = 1
                r1.<init>(r0, r2)
                r12.add(r1)
            L_0x04f4:
                java.util.List r12 = (java.util.List) r12
                return r12
            L_0x04f7:
                r23 = r30
                r14 = r6
                r6 = r5
                java.lang.String r1 = "//org.videolan.vlc/r/suggested"
                boolean r0 = r0.equals(r1)
                if (r0 != 0) goto L_0x050b
            L_0x0503:
                r5 = r14
                r3 = r19
                r4 = 0
            L_0x0507:
                r14 = r28
                goto L_0x06ab
            L_0x050b:
                r5 = r28
                java.util.List r0 = r10.buildSuggestions(r7, r5, r8)
                return r0
            L_0x0512:
                r23 = r30
                r4 = r6
                r6 = r5
                r5 = r28
                boolean r0 = r0.equals(r14)
                if (r0 != 0) goto L_0x051f
                goto L_0x057b
            L_0x051f:
                r0 = 1
                r1 = 0
                org.videolan.medialibrary.interfaces.media.MediaWrapper[] r2 = r8.getAudio(r0, r1, r1, r1)
                kotlin.jvm.internal.Intrinsics.checkNotNull(r2)
                org.videolan.vlc.gui.helpers.MediaComparators r0 = org.videolan.vlc.gui.helpers.MediaComparators.INSTANCE
                java.util.Comparator r0 = r0.getANDROID_AUTO()
                kotlin.collections.ArraysKt.sortWith(r2, r0)
                if (r21 != 0) goto L_0x0551
                int r0 = r2.length
                r1 = 800(0x320, float:1.121E-42)
                if (r0 <= r1) goto L_0x0551
                r1 = r2
                org.videolan.medialibrary.media.MediaLibraryItem[] r1 = (org.videolan.medialibrary.media.MediaLibraryItem[]) r1
                kotlin.jvm.internal.Intrinsics.checkNotNull(r13)
                int r0 = org.videolan.vlc.R.drawable.ic_auto_audio
                android.net.Uri r3 = org.videolan.tools.KotlinExtensionsKt.getResourceUri(r13, r0)
                r5 = 8
                r6 = 0
                r4 = 0
                r0 = r26
                r2 = r19
                java.util.List r0 = paginateLibrary$default(r0, r1, r2, r3, r4, r5, r6)
                return r0
            L_0x0551:
                int r0 = r2.length
                r14 = r20
                int r0 = kotlin.ranges.RangesKt.coerceAtMost((int) r14, (int) r0)
                r1 = 800(0x320, float:1.121E-42)
                int r1 = r1 + r14
                int r3 = r2.length
                int r1 = kotlin.ranges.RangesKt.coerceAtMost((int) r1, (int) r3)
                java.lang.Object[] r0 = kotlin.collections.ArraysKt.copyOfRange((T[]) r2, (int) r0, (int) r1)
                org.videolan.medialibrary.media.MediaLibraryItem[] r0 = (org.videolan.medialibrary.media.MediaLibraryItem[]) r0
                kotlin.Unit r1 = kotlin.Unit.INSTANCE
                r3 = r0
                r11 = r4
                r14 = r5
                goto L_0x06a7
            L_0x056d:
                r23 = r30
                r4 = r6
                r14 = r20
                r6 = r5
                r5 = r28
                boolean r0 = r0.equals(r6)
                if (r0 != 0) goto L_0x0581
            L_0x057b:
                r14 = r5
                r3 = r19
                r5 = r4
                goto L_0x065c
            L_0x0581:
                org.videolan.tools.Settings r0 = org.videolan.tools.Settings.INSTANCE
                java.lang.Object r0 = r0.getInstance(r7)
                android.content.SharedPreferences r0 = (android.content.SharedPreferences) r0
                r1 = 0
                boolean r2 = r0.getBoolean(r2, r1)
                r17 = 0
                r20 = 0
                r3 = 1
                r22 = 0
                r0 = r8
                r1 = r2
                r2 = r3
                r3 = r22
                r8 = r4
                r4 = r17
                r11 = r14
                r14 = r5
                r5 = r20
                org.videolan.medialibrary.interfaces.media.Artist[] r0 = r0.getArtists(r1, r2, r3, r4, r5)
                kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
                org.videolan.vlc.gui.helpers.MediaComparators r1 = org.videolan.vlc.gui.helpers.MediaComparators.INSTANCE
                java.util.Comparator r1 = r1.getANDROID_AUTO()
                kotlin.collections.ArraysKt.sortWith(r0, r1)
                if (r21 != 0) goto L_0x05d1
                int r1 = r0.length
                r2 = 800(0x320, float:1.121E-42)
                if (r1 <= r2) goto L_0x05d1
                r1 = r0
                org.videolan.medialibrary.media.MediaLibraryItem[] r1 = (org.videolan.medialibrary.media.MediaLibraryItem[]) r1
                kotlin.jvm.internal.Intrinsics.checkNotNull(r13)
                int r0 = org.videolan.vlc.R.drawable.ic_auto_artist
                android.net.Uri r3 = org.videolan.tools.KotlinExtensionsKt.getResourceUri(r13, r0)
                r5 = 8
                r6 = 0
                r4 = 0
                r0 = r26
                r2 = r19
                java.util.List r0 = paginateLibrary$default(r0, r1, r2, r3, r4, r5, r6)
                return r0
            L_0x05d1:
                int r1 = r0.length
                int r1 = kotlin.ranges.RangesKt.coerceAtMost((int) r11, (int) r1)
                r2 = 800(0x320, float:1.121E-42)
                int r5 = r11 + 800
                int r2 = r0.length
                int r2 = kotlin.ranges.RangesKt.coerceAtMost((int) r5, (int) r2)
                java.lang.Object[] r0 = kotlin.collections.ArraysKt.copyOfRange((T[]) r0, (int) r1, (int) r2)
                org.videolan.medialibrary.media.MediaLibraryItem[] r0 = (org.videolan.medialibrary.media.MediaLibraryItem[]) r0
                kotlin.Unit r1 = kotlin.Unit.INSTANCE
                r3 = r0
                r18 = r6
                r11 = r8
                goto L_0x083f
            L_0x05ed:
                r14 = r28
                r23 = r30
                r11 = r20
                r25 = r6
                r6 = r5
                r5 = r25
                boolean r0 = r0.equals(r15)
                if (r0 != 0) goto L_0x0601
                r3 = r19
                goto L_0x065c
            L_0x0601:
                r0 = 1
                r1 = 0
                org.videolan.medialibrary.interfaces.media.Album[] r2 = r8.getAlbums(r0, r1, r1, r1)
                kotlin.jvm.internal.Intrinsics.checkNotNull(r2)
                org.videolan.vlc.gui.helpers.MediaComparators r0 = org.videolan.vlc.gui.helpers.MediaComparators.INSTANCE
                java.util.Comparator r0 = r0.getANDROID_AUTO()
                kotlin.collections.ArraysKt.sortWith(r2, r0)
                if (r21 != 0) goto L_0x0633
                int r0 = r2.length
                r1 = 800(0x320, float:1.121E-42)
                if (r0 <= r1) goto L_0x0633
                org.videolan.medialibrary.media.MediaLibraryItem[] r2 = (org.videolan.medialibrary.media.MediaLibraryItem[]) r2
                kotlin.jvm.internal.Intrinsics.checkNotNull(r13)
                int r0 = org.videolan.vlc.R.drawable.ic_auto_album
                android.net.Uri r0 = org.videolan.tools.KotlinExtensionsKt.getResourceUri(r13, r0)
                r1 = 2
                r3 = 0
                r4 = 0
                android.os.Bundle r1 = getContentStyle$default(r10, r1, r4, r1, r3)
                r3 = r19
                java.util.List r0 = r10.paginateLibrary(r2, r3, r0, r1)
                return r0
            L_0x0633:
                int r0 = r2.length
                int r0 = kotlin.ranges.RangesKt.coerceAtMost((int) r11, (int) r0)
                r1 = 800(0x320, float:1.121E-42)
                int r1 = r1 + r11
                int r3 = r2.length
                int r1 = kotlin.ranges.RangesKt.coerceAtMost((int) r1, (int) r3)
                java.lang.Object[] r0 = kotlin.collections.ArraysKt.copyOfRange((T[]) r2, (int) r0, (int) r1)
                org.videolan.medialibrary.media.MediaLibraryItem[] r0 = (org.videolan.medialibrary.media.MediaLibraryItem[]) r0
                kotlin.Unit r1 = kotlin.Unit.INSTANCE
                goto L_0x06a5
            L_0x0649:
                r14 = r28
                r23 = r30
                r3 = r19
                r11 = r20
                r25 = r6
                r6 = r5
                r5 = r25
                boolean r0 = r0.equals(r9)
                if (r0 != 0) goto L_0x065e
            L_0x065c:
                r4 = 0
                goto L_0x06ab
            L_0x065e:
                r0 = 1
                r4 = 0
                org.videolan.medialibrary.interfaces.media.Genre[] r1 = r8.getGenres(r0, r4, r4, r4)
                kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
                org.videolan.vlc.gui.helpers.MediaComparators r0 = org.videolan.vlc.gui.helpers.MediaComparators.INSTANCE
                java.util.Comparator r0 = r0.getANDROID_AUTO()
                kotlin.collections.ArraysKt.sortWith(r1, r0)
                if (r21 != 0) goto L_0x0690
                int r0 = r1.length
                r2 = 800(0x320, float:1.121E-42)
                if (r0 <= r2) goto L_0x0690
                org.videolan.medialibrary.media.MediaLibraryItem[] r1 = (org.videolan.medialibrary.media.MediaLibraryItem[]) r1
                kotlin.jvm.internal.Intrinsics.checkNotNull(r13)
                int r0 = org.videolan.vlc.R.drawable.ic_auto_genre
                android.net.Uri r4 = org.videolan.tools.KotlinExtensionsKt.getResourceUri(r13, r0)
                r5 = 8
                r6 = 0
                r7 = 0
                r0 = r26
                r2 = r3
                r3 = r4
                r4 = r7
                java.util.List r0 = paginateLibrary$default(r0, r1, r2, r3, r4, r5, r6)
                return r0
            L_0x0690:
                int r0 = r1.length
                int r0 = kotlin.ranges.RangesKt.coerceAtMost((int) r11, (int) r0)
                r2 = 800(0x320, float:1.121E-42)
                int r2 = r2 + r11
                int r3 = r1.length
                int r2 = kotlin.ranges.RangesKt.coerceAtMost((int) r2, (int) r3)
                java.lang.Object[] r0 = kotlin.collections.ArraysKt.copyOfRange((T[]) r1, (int) r0, (int) r2)
                org.videolan.medialibrary.media.MediaLibraryItem[] r0 = (org.videolan.medialibrary.media.MediaLibraryItem[]) r0
                kotlin.Unit r1 = kotlin.Unit.INSTANCE
            L_0x06a5:
                r3 = r0
                r11 = r5
            L_0x06a7:
                r18 = r6
                goto L_0x083f
            L_0x06ab:
                long r0 = android.content.ContentUris.parseId(r3)
                android.net.Uri r2 = org.videolan.tools.KotlinExtensionsKt.retrieveParent(r3)
                java.lang.String r2 = java.lang.String.valueOf(r2)
                int r3 = r2.hashCode()
                r11 = -1979024705(0xffffffff8a0a7abf, float:-6.667544E-33)
                java.lang.String r4 = "shuffle"
                r17 = r5
                java.lang.String r5 = "play_all"
                if (r3 == r11) goto L_0x07b4
                r11 = -1979024700(0xffffffff8a0a7ac4, float:-6.667548E-33)
                if (r3 == r11) goto L_0x079c
                r11 = -1979024694(0xffffffff8a0a7aca, float:-6.667552E-33)
                if (r3 == r11) goto L_0x06d7
            L_0x06d0:
                r18 = r6
                r11 = r17
                r6 = 0
                goto L_0x07bf
            L_0x06d7:
                boolean r2 = r2.equals(r6)
                if (r2 != 0) goto L_0x06de
                goto L_0x06d0
            L_0x06de:
                org.videolan.medialibrary.interfaces.media.Artist r2 = r8.getArtist(r0)
                org.videolan.medialibrary.interfaces.media.Album[] r3 = r2.getAlbums()
                r8 = r3
                org.videolan.medialibrary.media.MediaLibraryItem[] r8 = (org.videolan.medialibrary.media.MediaLibraryItem[]) r8
                if (r8 == 0) goto L_0x0794
                int r3 = r8.length
                r11 = 1
                if (r3 <= r11) goto L_0x0794
                int r3 = r8.length
                r11 = 0
            L_0x06f1:
                if (r11 >= r3) goto L_0x0726
                r18 = r8[r11]
                org.videolan.medialibrary.interfaces.media.Album r18 = (org.videolan.medialibrary.interfaces.media.Album) r18
                java.lang.String r19 = r18.getArtworkMrl()
                java.lang.CharSequence r19 = (java.lang.CharSequence) r19
                if (r19 == 0) goto L_0x071b
                int r19 = r19.length()
                if (r19 != 0) goto L_0x0706
                goto L_0x071b
            L_0x0706:
                r19 = r3
                java.lang.String r3 = r18.getArtworkMrl()
                r18 = r6
                java.lang.String r6 = "getArtworkMrl(...)"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r6)
                boolean r3 = org.videolan.vlc.FileProviderKt.isPathValid(r3)
                if (r3 == 0) goto L_0x071f
                r3 = 1
                goto L_0x0729
            L_0x071b:
                r19 = r3
                r18 = r6
            L_0x071f:
                int r11 = r11 + 1
                r6 = r18
                r3 = r19
                goto L_0x06f1
            L_0x0726:
                r18 = r6
                r3 = 0
            L_0x0729:
                if (r29 == 0) goto L_0x0734
                int r6 = r2.getTracksCount()
                r11 = 2
                if (r6 <= r11) goto L_0x0734
                r6 = 1
                goto L_0x0735
            L_0x0734:
                r6 = 0
            L_0x0735:
                if (r3 == 0) goto L_0x076c
                android.net.Uri$Builder r3 = new android.net.Uri$Builder
                r3.<init>()
                android.net.Uri$Builder r3 = r3.appendPath(r5)
                java.lang.String r5 = "artist"
                android.net.Uri$Builder r3 = r3.appendPath(r5)
                int r5 = r2.getTracksCount()
                java.lang.String r5 = java.lang.String.valueOf(r5)
                android.net.Uri$Builder r3 = r3.appendPath(r5)
                java.lang.String r0 = java.lang.String.valueOf(r0)
                android.net.Uri$Builder r0 = r3.appendPath(r0)
                int r1 = org.videolan.tools.KotlinExtensionsKt.toInt(r6)
                java.lang.String r1 = java.lang.String.valueOf(r1)
                android.net.Uri$Builder r0 = r0.appendQueryParameter(r4, r1)
                android.net.Uri r0 = r0.build()
                r5 = r0
                goto L_0x076d
            L_0x076c:
                r5 = 0
            L_0x076d:
                if (r6 == 0) goto L_0x0772
                int r0 = org.videolan.vlc.R.string.shuffle_all_title
                goto L_0x0774
            L_0x0772:
                int r0 = org.videolan.vlc.R.string.play_all
            L_0x0774:
                r3 = r0
                int r4 = r2.getTracksCount()
                r0 = r26
                r1 = r27
                r2 = r28
                r6 = 0
                r11 = r17
                android.support.v4.media.MediaDescriptionCompat$Builder r0 = r0.getPlayAllBuilder(r1, r2, r3, r4, r5)
                android.support.v4.media.MediaDescriptionCompat r0 = r0.build()
                android.support.v4.media.MediaBrowserCompat$MediaItem r1 = new android.support.v4.media.MediaBrowserCompat$MediaItem
                r2 = 2
                r1.<init>(r0, r2)
                r12.add(r1)
                goto L_0x0799
            L_0x0794:
                r18 = r6
                r11 = r17
                r6 = 0
            L_0x0799:
                r3 = r8
                goto L_0x083d
            L_0x079c:
                r18 = r6
                r11 = r17
                r6 = 0
                boolean r2 = r2.equals(r15)
                if (r2 == 0) goto L_0x07bf
                org.videolan.medialibrary.interfaces.media.Album r0 = r8.getAlbum(r0)
                org.videolan.medialibrary.interfaces.media.MediaWrapper[] r0 = r0.getTracks()
                r3 = r0
                org.videolan.medialibrary.media.MediaLibraryItem[] r3 = (org.videolan.medialibrary.media.MediaLibraryItem[]) r3
                goto L_0x083d
            L_0x07b4:
                r18 = r6
                r11 = r17
                r6 = 0
                boolean r2 = r2.equals(r9)
                if (r2 != 0) goto L_0x07c2
            L_0x07bf:
                r3 = 0
                goto L_0x083d
            L_0x07c2:
                org.videolan.medialibrary.interfaces.media.Genre r2 = r8.getGenre(r0)
                org.videolan.medialibrary.interfaces.media.Album[] r2 = r2.getAlbums()
                r8 = r2
                org.videolan.medialibrary.media.MediaLibraryItem[] r8 = (org.videolan.medialibrary.media.MediaLibraryItem[]) r8
                int r2 = r8.length
                r3 = 0
            L_0x07cf:
                if (r3 >= r2) goto L_0x07de
                r17 = r8[r3]
                org.videolan.medialibrary.interfaces.media.Album r17 = (org.videolan.medialibrary.interfaces.media.Album) r17
                int r17 = r17.getTracksCount()
                int r6 = r6 + r17
                int r3 = r3 + 1
                goto L_0x07cf
            L_0x07de:
                if (r8 == 0) goto L_0x0799
                int r2 = r8.length
                r3 = 1
                if (r2 <= r3) goto L_0x0799
                if (r29 == 0) goto L_0x07eb
                r2 = 2
                if (r6 <= r2) goto L_0x07eb
                r2 = 1
                goto L_0x07ec
            L_0x07eb:
                r2 = 0
            L_0x07ec:
                android.net.Uri$Builder r3 = new android.net.Uri$Builder
                r3.<init>()
                android.net.Uri$Builder r3 = r3.appendPath(r5)
                java.lang.String r5 = "genre"
                android.net.Uri$Builder r3 = r3.appendPath(r5)
                java.lang.String r5 = java.lang.String.valueOf(r6)
                android.net.Uri$Builder r3 = r3.appendPath(r5)
                java.lang.String r0 = java.lang.String.valueOf(r0)
                android.net.Uri$Builder r0 = r3.appendPath(r0)
                int r1 = org.videolan.tools.KotlinExtensionsKt.toInt(r2)
                java.lang.String r1 = java.lang.String.valueOf(r1)
                android.net.Uri$Builder r0 = r0.appendQueryParameter(r4, r1)
                android.net.Uri r5 = r0.build()
                if (r2 == 0) goto L_0x0820
                int r0 = org.videolan.vlc.R.string.shuffle_all_title
                goto L_0x0822
            L_0x0820:
                int r0 = org.videolan.vlc.R.string.play_all
            L_0x0822:
                r3 = r0
                r0 = r26
                r1 = r27
                r2 = r28
                r4 = r6
                android.support.v4.media.MediaDescriptionCompat$Builder r0 = r0.getPlayAllBuilder(r1, r2, r3, r4, r5)
                android.support.v4.media.MediaDescriptionCompat r0 = r0.build()
                android.support.v4.media.MediaBrowserCompat$MediaItem r1 = new android.support.v4.media.MediaBrowserCompat$MediaItem
                r2 = 2
                r1.<init>(r0, r2)
                r12.add(r1)
                goto L_0x0799
            L_0x083d:
                kotlin.Unit r0 = kotlin.Unit.INSTANCE
            L_0x083f:
                r5 = 0
            L_0x0840:
                r8 = 32
                r17 = 0
                r4 = 0
                r6 = 0
                r0 = r26
                r1 = r27
                r2 = r28
                r7 = r18
                r24 = r7
                r7 = r16
                r10 = r9
                r9 = r17
                java.util.List r0 = buildMediaItems$default(r0, r1, r2, r3, r4, r5, r6, r7, r8, r9)
                java.util.Collection r0 = (java.util.Collection) r0
                r12.addAll(r0)
                boolean r0 = r12.isEmpty()
                if (r0 == 0) goto L_0x0906
                android.support.v4.media.MediaDescriptionCompat$Builder r0 = new android.support.v4.media.MediaDescriptionCompat$Builder
                r0.<init>()
                java.lang.String r1 = "//org.videolan.vlc/r/error/media"
                android.support.v4.media.MediaDescriptionCompat$Builder r0 = r0.setMediaId(r1)
                kotlin.jvm.internal.Intrinsics.checkNotNull(r13)
                int r1 = org.videolan.vlc.R.drawable.ic_auto_nothumb
                android.net.Uri r1 = org.videolan.tools.KotlinExtensionsKt.getResourceUri(r13, r1)
                android.support.v4.media.MediaDescriptionCompat$Builder r0 = r0.setIconUri(r1)
                int r1 = org.videolan.vlc.R.string.search_no_result
                java.lang.String r1 = r13.getString(r1)
                java.lang.CharSequence r1 = (java.lang.CharSequence) r1
                android.support.v4.media.MediaDescriptionCompat$Builder r0 = r0.setTitle(r1)
                android.os.Bundle r1 = new android.os.Bundle
                r1.<init>()
                java.lang.String r2 = "android.media.browse.CONTENT_STYLE_SINGLE_ITEM_HINT"
                r3 = 1
                r1.putInt(r2, r3)
                android.support.v4.media.MediaDescriptionCompat$Builder r0 = r0.setExtras(r1)
                int r1 = r28.hashCode()
                switch(r1) {
                    case -1979024705: goto L_0x08ee;
                    case -1979024700: goto L_0x08dd;
                    case -1979024694: goto L_0x08ca;
                    case -664866953: goto L_0x08b0;
                    case 155640421: goto L_0x089f;
                    default: goto L_0x089e;
                }
            L_0x089e:
                goto L_0x08f9
            L_0x089f:
                boolean r1 = r14.equals(r11)
                if (r1 != 0) goto L_0x08a6
                goto L_0x08f9
            L_0x08a6:
                int r1 = org.videolan.vlc.R.drawable.ic_auto_stream_unknown
                android.net.Uri r1 = org.videolan.tools.KotlinExtensionsKt.getResourceUri(r13, r1)
                r0.setIconUri(r1)
                goto L_0x08f9
            L_0x08b0:
                r1 = r23
                boolean r1 = r14.equals(r1)
                if (r1 != 0) goto L_0x08b9
                goto L_0x08f9
            L_0x08b9:
                java.lang.String r1 = "//org.videolan.vlc/r/error/playlist"
                r0.setMediaId(r1)
                int r1 = org.videolan.vlc.R.string.noplaylist
                java.lang.String r1 = r13.getString(r1)
                java.lang.CharSequence r1 = (java.lang.CharSequence) r1
                r0.setTitle(r1)
                goto L_0x08f9
            L_0x08ca:
                r1 = r24
                boolean r1 = r14.equals(r1)
                if (r1 != 0) goto L_0x08d3
                goto L_0x08f9
            L_0x08d3:
                int r1 = org.videolan.vlc.R.drawable.ic_auto_artist_unknown
                android.net.Uri r1 = org.videolan.tools.KotlinExtensionsKt.getResourceUri(r13, r1)
                r0.setIconUri(r1)
                goto L_0x08f9
            L_0x08dd:
                boolean r1 = r14.equals(r15)
                if (r1 != 0) goto L_0x08e4
                goto L_0x08f9
            L_0x08e4:
                int r1 = org.videolan.vlc.R.drawable.ic_auto_album_unknown
                android.net.Uri r1 = org.videolan.tools.KotlinExtensionsKt.getResourceUri(r13, r1)
                r0.setIconUri(r1)
                goto L_0x08f9
            L_0x08ee:
                boolean r1 = r14.equals(r10)
                if (r1 != 0) goto L_0x08f5
                goto L_0x08f9
            L_0x08f5:
                r1 = 0
                r0.setIconUri(r1)
            L_0x08f9:
                android.support.v4.media.MediaBrowserCompat$MediaItem r1 = new android.support.v4.media.MediaBrowserCompat$MediaItem
                android.support.v4.media.MediaDescriptionCompat r0 = r0.build()
                r2 = 2
                r1.<init>(r0, r2)
                r12.add(r1)
            L_0x0906:
                java.util.List r12 = (java.util.List) r12
                return r12
            */
            throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.media.MediaSessionBrowser.Companion.browse(android.content.Context, java.lang.String, boolean, android.os.Bundle):java.util.List");
        }

        public final List<MediaBrowserCompat.MediaItem> search(Context context, String str, Bundle bundle) {
            String str2 = str;
            Bundle bundle2 = bundle;
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(str2, "query");
            Resources resources = context.getResources();
            List<MediaBrowserCompat.MediaItem> arrayList = new ArrayList<>();
            boolean containsKey = bundle2 != null ? bundle2.containsKey(Constants.EXTRA_BROWSER_ICON_SIZE) : false;
            SearchAggregate search = Medialibrary.getInstance().search(str2, false, false);
            String builder = Uri.parse(MediaSessionBrowser.ID_SEARCH).buildUpon().appendQueryParameter("query", str2).toString();
            Intrinsics.checkNotNullExpressionValue(builder, "toString(...)");
            Context context2 = context;
            arrayList.addAll(buildMediaItems$default(this, context2, MediaSessionBrowser.ID_PLAYLIST, (MediaLibraryItem[]) search.getPlaylists(), resources.getString(R.string.playlists), false, false, false, 112, (Object) null));
            arrayList.addAll(buildMediaItems$default(this, context2, MediaSessionBrowser.ID_GENRE, (MediaLibraryItem[]) search.getGenres(), resources.getString(R.string.genres), false, false, false, 112, (Object) null));
            arrayList.addAll(buildMediaItems$default(this, context2, MediaSessionBrowser.ID_ARTIST, (MediaLibraryItem[]) search.getArtists(), resources.getString(R.string.artists), false, false, false, 112, (Object) null));
            arrayList.addAll(buildMediaItems$default(this, context2, MediaSessionBrowser.ID_ALBUM, (MediaLibraryItem[]) search.getAlbums(), resources.getString(R.string.albums), false, false, false, 112, (Object) null));
            arrayList.addAll(buildMediaItems$default(this, context2, builder, (MediaLibraryItem[]) search.getTracks(), resources.getString(R.string.tracks), false, false, containsKey, 48, (Object) null));
            if (arrayList.isEmpty()) {
                MediaDescriptionCompat.Builder mediaId = new MediaDescriptionCompat.Builder().setMediaId(MediaSessionBrowser.ID_NO_MEDIA);
                Intrinsics.checkNotNull(resources);
                arrayList.add(new MediaBrowserCompat.MediaItem(mediaId.setIconUri(KotlinExtensionsKt.getResourceUri(resources, R.drawable.ic_auto_nothumb)).setTitle(resources.getString(R.string.search_no_result)).build(), 2));
            }
            return arrayList;
        }

        private final List<MediaBrowserCompat.MediaItem> buildSuggestions(Context context, String str, Medialibrary medialibrary) {
            List<MediaWrapper> list;
            List list2;
            Medialibrary medialibrary2 = medialibrary;
            int audioCount = medialibrary.getAudioCount();
            if (audioCount == 0) {
                return CollectionsKt.emptyList();
            }
            Set<String> linkedHashSet = new LinkedHashSet<>();
            if (((SharedPreferences) Settings.INSTANCE.getInstance(context)).getBoolean(SettingsKt.PLAYBACK_HISTORY, true)) {
                MediaWrapper[] history = medialibrary2.history(1);
                if (history == null || (list2 = ArraysKt.toList((T[]) history)) == null) {
                    list = null;
                } else {
                    Collection arrayList = new ArrayList();
                    for (Object next : list2) {
                        MediaWrapper mediaWrapper = (MediaWrapper) next;
                        Companion companion = MediaSessionBrowser.Companion;
                        Intrinsics.checkNotNull(mediaWrapper);
                        if (companion.isMediaAudio(mediaWrapper)) {
                            arrayList.add(next);
                        }
                    }
                    list = (List) arrayList;
                }
                Collection collection = list;
                if (collection != null && !collection.isEmpty()) {
                    for (MediaWrapper album : list) {
                        String album2 = album.getAlbum();
                        if (album2 != null) {
                            linkedHashSet.add(album2);
                        }
                    }
                }
            }
            MediaWrapper[] pagedAudio = medialibrary.getPagedAudio(3, true, false, false, 100, 0);
            if (!(pagedAudio == null || pagedAudio.length == 0)) {
                Intrinsics.checkNotNull(pagedAudio);
                for (MediaWrapper album3 : pagedAudio) {
                    String album4 = album3.getAlbum();
                    if (album4 != null) {
                        linkedHashSet.add(album4);
                    }
                }
            }
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(new MediaBrowserCompat.MediaItem(getPlayAllBuilder(context, MediaSessionBrowser.ID_SHUFFLE_ALL, R.string.shuffle_all_title, audioCount, new Uri.Builder().appendPath(ArtworkProvider.SHUFFLE_ALL).appendPath(ArtworkProvider.Companion.computeExpiration$default(ArtworkProvider.Companion, false, 1, (Object) null)).appendPath(String.valueOf(audioCount)).build()).build(), 2));
            Set linkedHashSet2 = new LinkedHashSet();
            for (String searchAlbum : linkedHashSet) {
                Album[] searchAlbum2 = medialibrary2.searchAlbum(searchAlbum);
                if (searchAlbum2 != null) {
                    linkedHashSet2.addAll(ArraysKt.toList((T[]) searchAlbum2));
                }
            }
            ArrayList arrayList3 = arrayList2;
            arrayList3.addAll(CollectionsKt.take(buildMediaItems$default(this, context, str, (MediaLibraryItem[]) linkedHashSet2.toArray(new Album[0]), (String) null, false, true, false, 64, (Object) null), RangesKt.coerceAtLeast(15 - arrayList3.size(), 0)));
            return arrayList3;
        }

        static /* synthetic */ List buildMediaItems$default(Companion companion, Context context, String str, MediaLibraryItem[] mediaLibraryItemArr, String str2, boolean z, boolean z2, boolean z3, int i, Object obj) {
            return companion.buildMediaItems(context, str, mediaLibraryItemArr, str2, (i & 16) != 0 ? false : z, (i & 32) != 0 ? false : z2, (i & 64) != 0 ? false : z3);
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v4, resolved type: org.videolan.medialibrary.interfaces.media.MediaWrapper} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v3, resolved type: android.net.Uri} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v10, resolved type: android.net.Uri} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v39, resolved type: org.videolan.medialibrary.interfaces.media.MediaWrapper} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v42, resolved type: org.videolan.medialibrary.interfaces.media.MediaWrapper} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v43, resolved type: org.videolan.medialibrary.interfaces.media.MediaWrapper} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v44, resolved type: org.videolan.medialibrary.interfaces.media.MediaWrapper} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v47, resolved type: org.videolan.medialibrary.interfaces.media.MediaWrapper} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v28, resolved type: android.net.Uri} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v29, resolved type: android.net.Uri} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v30, resolved type: android.net.Uri} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v31, resolved type: android.net.Uri} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v32, resolved type: android.net.Uri} */
        /* JADX WARNING: Multi-variable type inference failed */
        /* JADX WARNING: Removed duplicated region for block: B:108:0x02c4  */
        /* JADX WARNING: Removed duplicated region for block: B:113:0x02e4  */
        /* JADX WARNING: Removed duplicated region for block: B:142:0x03b2  */
        /* JADX WARNING: Removed duplicated region for block: B:147:0x03ed  */
        /* JADX WARNING: Removed duplicated region for block: B:148:0x03ef  */
        /* JADX WARNING: Removed duplicated region for block: B:150:0x03f2 A[ADDED_TO_REGION] */
        /* JADX WARNING: Removed duplicated region for block: B:155:0x0409  */
        /* JADX WARNING: Removed duplicated region for block: B:158:0x0414  */
        /* JADX WARNING: Removed duplicated region for block: B:167:0x0424 A[EDGE_INSN: B:167:0x0424->B:162:0x0424 ?: BREAK  , SYNTHETIC] */
        /* JADX WARNING: Removed duplicated region for block: B:21:0x0090  */
        /* JADX WARNING: Removed duplicated region for block: B:22:0x00a3  */
        /* JADX WARNING: Removed duplicated region for block: B:25:0x00b4  */
        /* JADX WARNING: Removed duplicated region for block: B:46:0x0176  */
        /* JADX WARNING: Removed duplicated region for block: B:54:0x01a8  */
        /* JADX WARNING: Removed duplicated region for block: B:55:0x01ae  */
        /* JADX WARNING: Removed duplicated region for block: B:57:0x01b6  */
        /* JADX WARNING: Removed duplicated region for block: B:60:0x01c3  */
        /* JADX WARNING: Removed duplicated region for block: B:91:0x0247  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private final java.util.List<android.support.v4.media.MediaBrowserCompat.MediaItem> buildMediaItems(android.content.Context r29, java.lang.String r30, org.videolan.medialibrary.media.MediaLibraryItem[] r31, java.lang.String r32, boolean r33, boolean r34, boolean r35) {
            /*
                r28 = this;
                r0 = r28
                r1 = r29
                r2 = r30
                r3 = r31
                r4 = r32
                if (r3 == 0) goto L_0x042a
                int r5 = r3.length
                if (r5 != 0) goto L_0x0011
                goto L_0x042a
            L_0x0011:
                android.content.res.Resources r5 = r29.getResources()
                java.util.HashMap r6 = new java.util.HashMap
                r6.<init>()
                java.util.ArrayList r7 = new java.util.ArrayList
                r7.<init>()
                int r8 = r3.length
                r9 = 800(0x320, float:1.121E-42)
                int r8 = kotlin.ranges.RangesKt.coerceAtMost((int) r8, (int) r9)
                r7.ensureCapacity(r8)
                android.net.Uri r8 = android.net.Uri.parse(r30)
                java.lang.Iterable r3 = kotlin.collections.ArraysKt.withIndex((T[]) r31)
                java.util.Iterator r3 = r3.iterator()
            L_0x0035:
                boolean r10 = r3.hasNext()
                if (r10 == 0) goto L_0x0424
                java.lang.Object r10 = r3.next()
                kotlin.collections.IndexedValue r10 = (kotlin.collections.IndexedValue) r10
                int r11 = r10.component1()
                java.lang.Object r10 = r10.component2()
                org.videolan.medialibrary.media.MediaLibraryItem r10 = (org.videolan.medialibrary.media.MediaLibraryItem) r10
                int r12 = r10.getItemType()
                r13 = 6
                java.lang.String r14 = "null cannot be cast to non-null type org.videolan.medialibrary.interfaces.media.MediaWrapper"
                r15 = 32
                if (r12 != r15) goto L_0x0074
                kotlin.jvm.internal.Intrinsics.checkNotNull(r10, r14)
                r12 = r10
                org.videolan.medialibrary.interfaces.media.MediaWrapper r12 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r12
                int r9 = r12.getType()
                if (r9 == r13) goto L_0x0070
                android.net.Uri r9 = r12.getUri()
                java.lang.String r9 = r9.getScheme()
                boolean r9 = org.videolan.vlc.util.BrowserutilsKt.isSchemeStreaming(r9)
                if (r9 == 0) goto L_0x0074
            L_0x0070:
                r12.setType(r13)
                goto L_0x008a
            L_0x0074:
                int r9 = r10.getItemType()
                if (r9 != r15) goto L_0x008a
                kotlin.jvm.internal.Intrinsics.checkNotNull(r10, r14)
                r9 = r10
                org.videolan.medialibrary.interfaces.media.MediaWrapper r9 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r9
                int r9 = r9.getType()
                r12 = 1
                if (r9 == r12) goto L_0x008a
            L_0x0087:
                r9 = 800(0x320, float:1.121E-42)
                goto L_0x0035
            L_0x008a:
                int r9 = r10.getItemType()
                if (r9 != r15) goto L_0x00a3
                android.net.Uri$Builder r9 = r8.buildUpon()
                java.lang.String r12 = "i"
                java.lang.String r11 = java.lang.String.valueOf(r11)
                android.net.Uri$Builder r9 = r9.appendQueryParameter(r12, r11)
                java.lang.String r9 = r9.toString()
                goto L_0x00a7
            L_0x00a3:
                java.lang.String r9 = r0.generateMediaId(r10)
            L_0x00a7:
                kotlin.jvm.internal.Intrinsics.checkNotNull(r9)
                int r11 = r10.getItemType()
                r12 = 8
                r15 = 4
                r13 = 2
                if (r11 == r13) goto L_0x0176
                if (r11 == r15) goto L_0x0150
                if (r11 == r12) goto L_0x012b
                r12 = 16
                if (r11 == r12) goto L_0x0112
                r12 = 32
                if (r11 == r12) goto L_0x00cc
                java.lang.String r11 = r10.getDescription()
            L_0x00c4:
                r18 = r3
            L_0x00c6:
                r19 = r8
                r8 = 0
                r13 = 0
                goto L_0x019d
            L_0x00cc:
                kotlin.jvm.internal.Intrinsics.checkNotNull(r10, r14)
                r11 = r10
                org.videolan.medialibrary.interfaces.media.MediaWrapper r11 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r11
                int r12 = r11.getType()
                r15 = 6
                if (r12 != r15) goto L_0x00e2
                android.net.Uri r11 = r11.getUri()
                java.lang.String r11 = r11.toString()
                goto L_0x00c4
            L_0x00e2:
                java.lang.String r12 = "//org.videolan.vlc/r/l/l"
                r18 = r3
                r3 = 0
                r15 = 0
                boolean r12 = kotlin.text.StringsKt.startsWith$default(r2, r12, r3, r13, r15)
                if (r12 == 0) goto L_0x00f5
                org.videolan.vlc.media.MediaUtils r12 = org.videolan.vlc.media.MediaUtils.INSTANCE
                java.lang.String r11 = r12.getMediaSubtitle(r11)
                goto L_0x00c6
            L_0x00f5:
                org.videolan.vlc.util.TextUtils r12 = org.videolan.vlc.util.TextUtils.INSTANCE
                org.videolan.vlc.media.MediaUtils r15 = org.videolan.vlc.media.MediaUtils.INSTANCE
                java.lang.String r15 = r15.getMediaArtist(r1, r11)
                org.videolan.vlc.media.MediaUtils r3 = org.videolan.vlc.media.MediaUtils.INSTANCE
                java.lang.String r3 = r3.getMediaAlbum(r1, r11)
                java.lang.String[] r11 = new java.lang.String[r13]
                r13 = 0
                r11[r13] = r15
                r15 = 1
                r11[r15] = r3
                r3 = 45
                java.lang.String r11 = r12.separatedStringArgs(r3, r11)
                goto L_0x0128
            L_0x0112:
                r18 = r3
                r13 = 0
                r15 = 1
                int r3 = org.videolan.vlc.R.string.track_number
                int r11 = r10.getTracksCount()
                java.lang.Integer r11 = java.lang.Integer.valueOf(r11)
                java.lang.Object[] r12 = new java.lang.Object[r15]
                r12[r13] = r11
                java.lang.String r11 = r5.getString(r3, r12)
            L_0x0128:
                r19 = r8
                goto L_0x0174
            L_0x012b:
                r18 = r3
                r13 = 0
                r15 = 1
                org.videolan.medialibrary.interfaces.Medialibrary r3 = org.videolan.medialibrary.interfaces.Medialibrary.getInstance()
                long r11 = r10.getId()
                org.videolan.medialibrary.interfaces.media.Genre r3 = r3.getGenre(r11)
                int r3 = r3.getAlbumsCount()
                int r11 = org.videolan.vlc.R.plurals.albums_quantity
                java.lang.Integer r12 = java.lang.Integer.valueOf(r3)
                r19 = r8
                java.lang.Object[] r8 = new java.lang.Object[r15]
                r8[r13] = r12
                java.lang.String r11 = r5.getQuantityString(r11, r3, r8)
                goto L_0x0174
            L_0x0150:
                r18 = r3
                r19 = r8
                r13 = 0
                r15 = 1
                org.videolan.medialibrary.interfaces.Medialibrary r3 = org.videolan.medialibrary.interfaces.Medialibrary.getInstance()
                long r11 = r10.getId()
                org.videolan.medialibrary.interfaces.media.Artist r3 = r3.getArtist(r11)
                int r3 = r3.getAlbumsCount()
                int r8 = org.videolan.vlc.R.plurals.albums_quantity
                java.lang.Integer r11 = java.lang.Integer.valueOf(r3)
                java.lang.Object[] r12 = new java.lang.Object[r15]
                r12[r13] = r11
                java.lang.String r11 = r5.getQuantityString(r8, r3, r12)
            L_0x0174:
                r8 = 0
                goto L_0x019d
            L_0x0176:
                r18 = r3
                r19 = r8
                r13 = 0
                r15 = 1
                java.lang.String r3 = "//org.videolan.vlc/r/l/r"
                r8 = 0
                r11 = 2
                boolean r3 = kotlin.text.StringsKt.startsWith$default(r2, r3, r13, r11, r8)
                if (r3 == 0) goto L_0x0199
                int r3 = org.videolan.vlc.R.string.track_number
                int r11 = r10.getTracksCount()
                java.lang.Integer r11 = java.lang.Integer.valueOf(r11)
                java.lang.Object[] r12 = new java.lang.Object[r15]
                r12[r13] = r11
                java.lang.String r11 = r5.getString(r3, r12)
                goto L_0x019d
            L_0x0199:
                java.lang.String r11 = r10.getDescription()
            L_0x019d:
                int r3 = r10.getItemType()
                r12 = 4
                if (r3 == r12) goto L_0x01ae
                r12 = 8
                if (r3 == r12) goto L_0x01ae
                android.os.Bundle r3 = new android.os.Bundle
                r3.<init>()
                goto L_0x01b4
            L_0x01ae:
                r3 = 2
                android.os.Bundle r12 = r0.getContentStyle(r3, r3)
                r3 = r12
            L_0x01b4:
                if (r4 == 0) goto L_0x01bb
                java.lang.String r12 = "android.media.browse.CONTENT_STYLE_GROUP_TITLE_HINT"
                r3.putString(r12, r4)
            L_0x01bb:
                int r12 = r10.getItemType()
                r15 = 32
                if (r12 != r15) goto L_0x021e
                kotlin.jvm.internal.Intrinsics.checkNotNull(r10, r14)
                r12 = r10
                org.videolan.medialibrary.interfaces.media.MediaWrapper r12 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r12
                boolean r15 = r12.isPodcast()
                if (r15 == 0) goto L_0x021e
                float r15 = r12.getPosition()
                r17 = r14
                double r13 = (double) r15
                r20 = 4606732058837280358(0x3fee666666666666, double:0.95)
                r22 = 4607182418800017408(0x3ff0000000000000, double:1.0)
                r24 = 0
                int r15 = (r13 > r20 ? 1 : (r13 == r20 ? 0 : -1))
                if (r15 < 0) goto L_0x01e6
            L_0x01e3:
                r13 = r22
                goto L_0x01fb
            L_0x01e6:
                int r15 = (r13 > r24 ? 1 : (r13 == r24 ? 0 : -1))
                if (r15 > 0) goto L_0x01f5
                long r20 = r12.getPlayCount()
                r26 = 0
                int r12 = (r20 > r26 ? 1 : (r20 == r26 ? 0 : -1))
                if (r12 <= 0) goto L_0x01f5
                goto L_0x01e3
            L_0x01f5:
                int r12 = (r13 > r24 ? 1 : (r13 == r24 ? 0 : -1))
                if (r12 > 0) goto L_0x01fb
                r13 = r24
            L_0x01fb:
                java.lang.String r12 = "androidx.media.MediaItem.Extras.COMPLETION_PERCENTAGE"
                r3.putDouble(r12, r13)
                int r12 = (r13 > r22 ? 1 : (r13 == r22 ? 0 : -1))
                if (r12 != 0) goto L_0x0206
                r12 = 2
                goto L_0x020d
            L_0x0206:
                int r12 = (r13 > r24 ? 1 : (r13 == r24 ? 0 : -1))
                if (r12 != 0) goto L_0x020c
                r12 = 0
                goto L_0x020d
            L_0x020c:
                r12 = 1
            L_0x020d:
                java.lang.String r13 = "android.media.extra.PLAYBACK_STATUS"
                r3.putInt(r13, r12)
                if (r35 == 0) goto L_0x0220
                java.lang.String r12 = org.videolan.resources.Constants.EXTRA_RELATIVE_MEDIA_ID
                r3.putString(r12, r9)
                java.lang.String r9 = r0.generateMediaId(r10)
                goto L_0x0220
            L_0x021e:
                r17 = r14
            L_0x0220:
                int r12 = r10.getItemType()
                java.lang.String r13 = "getArtworkMrl(...)"
                r14 = 16
                if (r12 == r14) goto L_0x02bc
                java.lang.String r12 = r10.getArtworkMrl()
                java.lang.CharSequence r12 = (java.lang.CharSequence) r12
                if (r12 == 0) goto L_0x02bc
                int r12 = r12.length()
                if (r12 != 0) goto L_0x023a
                goto L_0x02bc
            L_0x023a:
                java.lang.String r12 = r10.getArtworkMrl()
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r12, r13)
                boolean r12 = org.videolan.vlc.FileProviderKt.isPathValid(r12)
                if (r12 == 0) goto L_0x02bc
                android.net.Uri$Builder r12 = new android.net.Uri$Builder
                r12.<init>()
                int r14 = r10.getItemType()
                r15 = 2
                if (r14 == r15) goto L_0x0281
                r15 = 4
                if (r14 == r15) goto L_0x0270
                java.lang.String r14 = "media"
                r12.appendPath(r14)
                boolean r14 = r10 instanceof org.videolan.medialibrary.interfaces.media.MediaWrapper
                if (r14 == 0) goto L_0x0262
                r8 = r10
                org.videolan.medialibrary.interfaces.media.MediaWrapper r8 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r8
            L_0x0262:
                if (r8 == 0) goto L_0x0291
                long r14 = r8.getLastModified()
                java.lang.String r8 = java.lang.String.valueOf(r14)
                r12.appendPath(r8)
                goto L_0x0291
            L_0x0270:
                java.lang.String r8 = "artist"
                r12.appendPath(r8)
                int r8 = r10.getTracksCount()
                java.lang.String r8 = java.lang.String.valueOf(r8)
                r12.appendPath(r8)
                goto L_0x0291
            L_0x0281:
                java.lang.String r8 = "album"
                r12.appendPath(r8)
                int r8 = r10.getTracksCount()
                java.lang.String r8 = java.lang.String.valueOf(r8)
                r12.appendPath(r8)
            L_0x0291:
                long r14 = r10.getId()
                java.lang.String r8 = java.lang.String.valueOf(r14)
                r12.appendPath(r8)
                r8 = r6
                java.util.Map r8 = (java.util.Map) r8
                java.lang.String r14 = r10.getArtworkMrl()
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r14, r13)
                java.lang.Object r13 = r8.get(r14)
                if (r13 != 0) goto L_0x02b9
                org.videolan.vlc.ArtworkProvider$Companion r13 = org.videolan.vlc.ArtworkProvider.Companion
                android.net.Uri r12 = r12.build()
                android.net.Uri r13 = r13.buildUri(r1, r12)
                r8.put(r14, r13)
            L_0x02b9:
                android.net.Uri r13 = (android.net.Uri) r13
                goto L_0x02f8
            L_0x02bc:
                int r12 = r10.getItemType()
                r14 = 32
                if (r12 != r14) goto L_0x02dd
                r12 = r17
                kotlin.jvm.internal.Intrinsics.checkNotNull(r10, r12)
                r12 = r10
                org.videolan.medialibrary.interfaces.media.MediaWrapper r12 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r12
                int r12 = r12.getType()
                r14 = 6
                if (r12 != r14) goto L_0x02dd
                kotlin.jvm.internal.Intrinsics.checkNotNull(r5)
                int r8 = org.videolan.vlc.R.drawable.ic_auto_stream_unknown
                android.net.Uri r13 = org.videolan.tools.KotlinExtensionsKt.getResourceUri(r5, r8)
                goto L_0x02f8
            L_0x02dd:
                int r12 = r10.getItemType()
                r14 = 2
                if (r12 == r14) goto L_0x03b2
                r15 = 4
                if (r12 == r15) goto L_0x03a7
                r15 = 8
                if (r12 == r15) goto L_0x03a4
                r15 = 16
                if (r12 == r15) goto L_0x02fb
                kotlin.jvm.internal.Intrinsics.checkNotNull(r5)
                int r8 = org.videolan.vlc.R.drawable.ic_auto_nothumb
                android.net.Uri r13 = org.videolan.tools.KotlinExtensionsKt.getResourceUri(r5, r8)
            L_0x02f8:
                r14 = 1
                goto L_0x03bc
            L_0x02fb:
                org.videolan.medialibrary.interfaces.media.MediaWrapper[] r8 = r10.getTracks()
                java.lang.String r12 = "getTracks(...)"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, r12)
                java.lang.Object[] r8 = (java.lang.Object[]) r8
                java.util.List r8 = kotlin.collections.ArraysKt.toList((T[]) r8)
                r12 = r8
                java.lang.Iterable r12 = (java.lang.Iterable) r12
                boolean r15 = r12 instanceof java.util.Collection
                if (r15 == 0) goto L_0x031d
                r15 = r12
                java.util.Collection r15 = (java.util.Collection) r15
                boolean r15 = r15.isEmpty()
                if (r15 == 0) goto L_0x031d
            L_0x031a:
                r14 = 1
                goto L_0x039a
            L_0x031d:
                java.util.Iterator r12 = r12.iterator()
            L_0x0321:
                boolean r15 = r12.hasNext()
                if (r15 == 0) goto L_0x031a
                java.lang.Object r15 = r12.next()
                org.videolan.medialibrary.interfaces.media.MediaWrapper r15 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r15
                org.videolan.vlc.util.ThumbnailsProvider r14 = org.videolan.vlc.util.ThumbnailsProvider.INSTANCE
                kotlin.jvm.internal.Intrinsics.checkNotNull(r15)
                boolean r14 = r14.isMediaVideo(r15)
                if (r14 != 0) goto L_0x0357
                java.lang.String r14 = r15.getArtworkMrl()
                java.lang.CharSequence r14 = (java.lang.CharSequence) r14
                if (r14 == 0) goto L_0x0355
                int r14 = r14.length()
                if (r14 != 0) goto L_0x0347
                goto L_0x0355
            L_0x0347:
                java.lang.String r14 = r15.getArtworkMrl()
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r14, r13)
                boolean r14 = org.videolan.vlc.FileProviderKt.isPathValid(r14)
                if (r14 == 0) goto L_0x0355
                goto L_0x0357
            L_0x0355:
                r14 = 2
                goto L_0x0321
            L_0x0357:
                android.net.Uri$Builder r12 = new android.net.Uri$Builder
                r12.<init>()
                java.lang.String r13 = "play_all"
                android.net.Uri$Builder r12 = r12.appendPath(r13)
                java.lang.String r13 = "playlist"
                android.net.Uri$Builder r12 = r12.appendPath(r13)
                org.videolan.vlc.ArtworkProvider$Companion r13 = org.videolan.vlc.ArtworkProvider.Companion
                r14 = 1
                long r16 = r13.computeChecksum(r8, r14)
                java.lang.String r8 = java.lang.String.valueOf(r16)
                android.net.Uri$Builder r8 = r12.appendPath(r8)
                int r12 = r10.getTracksCount()
                java.lang.String r12 = java.lang.String.valueOf(r12)
                android.net.Uri$Builder r8 = r8.appendPath(r12)
                long r12 = r10.getId()
                java.lang.String r12 = java.lang.String.valueOf(r12)
                android.net.Uri$Builder r8 = r8.appendPath(r12)
                android.net.Uri r8 = r8.build()
                org.videolan.vlc.ArtworkProvider$Companion r12 = org.videolan.vlc.ArtworkProvider.Companion
                android.net.Uri r13 = r12.buildUri(r1, r8)
                goto L_0x03bc
            L_0x039a:
                kotlin.jvm.internal.Intrinsics.checkNotNull(r5)
                int r8 = org.videolan.vlc.R.drawable.ic_auto_playlist_unknown
                android.net.Uri r13 = org.videolan.tools.KotlinExtensionsKt.getResourceUri(r5, r8)
                goto L_0x03bc
            L_0x03a4:
                r14 = 1
                r13 = r8
                goto L_0x03bc
            L_0x03a7:
                r14 = 1
                kotlin.jvm.internal.Intrinsics.checkNotNull(r5)
                int r8 = org.videolan.vlc.R.drawable.ic_auto_artist_unknown
                android.net.Uri r13 = org.videolan.tools.KotlinExtensionsKt.getResourceUri(r5, r8)
                goto L_0x03bc
            L_0x03b2:
                r14 = 1
                kotlin.jvm.internal.Intrinsics.checkNotNull(r5)
                int r8 = org.videolan.vlc.R.drawable.ic_auto_album_unknown
                android.net.Uri r13 = org.videolan.tools.KotlinExtensionsKt.getResourceUri(r5, r8)
            L_0x03bc:
                android.support.v4.media.MediaDescriptionCompat$Builder r8 = new android.support.v4.media.MediaDescriptionCompat$Builder
                r8.<init>()
                java.lang.String r12 = r10.getTitle()
                java.lang.CharSequence r12 = (java.lang.CharSequence) r12
                android.support.v4.media.MediaDescriptionCompat$Builder r8 = r8.setTitle(r12)
                java.lang.CharSequence r11 = (java.lang.CharSequence) r11
                android.support.v4.media.MediaDescriptionCompat$Builder r8 = r8.setSubtitle(r11)
                android.support.v4.media.MediaDescriptionCompat$Builder r8 = r8.setIconUri(r13)
                android.support.v4.media.MediaDescriptionCompat$Builder r8 = r8.setMediaId(r9)
                android.support.v4.media.MediaDescriptionCompat$Builder r3 = r8.setExtras(r3)
                android.support.v4.media.MediaDescriptionCompat r3 = r3.build()
                int r8 = r10.getItemType()
                r9 = 16
                if (r8 == r9) goto L_0x03ef
                r9 = 32
                if (r8 == r9) goto L_0x03ef
                r9 = 1
                goto L_0x03f0
            L_0x03ef:
                r9 = 2
            L_0x03f0:
                if (r34 == 0) goto L_0x0409
                if (r13 == 0) goto L_0x0403
                java.lang.String r8 = r13.getScheme()
                java.lang.String r9 = "android.resource"
                boolean r8 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r8, (java.lang.Object) r9)
                if (r8 == 0) goto L_0x0401
                goto L_0x0403
            L_0x0401:
                r13 = 2
                goto L_0x040a
            L_0x0403:
                r3 = r18
                r8 = r19
                goto L_0x0087
            L_0x0409:
                r13 = r9
            L_0x040a:
                android.support.v4.media.MediaBrowserCompat$MediaItem r8 = new android.support.v4.media.MediaBrowserCompat$MediaItem
                r8.<init>(r3, r13)
                r7.add(r8)
                if (r33 == 0) goto L_0x041c
                int r3 = r7.size()
                r8 = 100
                if (r3 == r8) goto L_0x0424
            L_0x041c:
                int r3 = r7.size()
                r8 = 800(0x320, float:1.121E-42)
                if (r3 != r8) goto L_0x0403
            L_0x0424:
                r6.clear()
                java.util.List r7 = (java.util.List) r7
                return r7
            L_0x042a:
                java.util.List r1 = kotlin.collections.CollectionsKt.emptyList()
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.media.MediaSessionBrowser.Companion.buildMediaItems(android.content.Context, java.lang.String, org.videolan.medialibrary.media.MediaLibraryItem[], java.lang.String, boolean, boolean, boolean):java.util.List");
        }

        public static /* synthetic */ Bundle getContentStyle$default(Companion companion, int i, int i2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                i = 1;
            }
            if ((i3 & 2) != 0) {
                i2 = 1;
            }
            return companion.getContentStyle(i, i2);
        }

        public final Bundle getContentStyle(int i, int i2) {
            Bundle bundle = new Bundle();
            bundle.putInt("android.media.browse.CONTENT_STYLE_BROWSABLE_HINT", i);
            bundle.putInt("android.media.browse.CONTENT_STYLE_PLAYABLE_HINT", i2);
            return bundle;
        }

        public final String generateMediaId(MediaLibraryItem mediaLibraryItem) {
            String str;
            Intrinsics.checkNotNullParameter(mediaLibraryItem, "libraryItem");
            int itemType = mediaLibraryItem.getItemType();
            if (itemType == 2) {
                str = MediaSessionBrowser.ID_ALBUM;
            } else if (itemType == 4) {
                str = MediaSessionBrowser.ID_ARTIST;
            } else if (itemType == 8) {
                str = MediaSessionBrowser.ID_GENRE;
            } else if (itemType != 16) {
                str = MediaSessionBrowser.ID_MEDIA;
            } else {
                str = MediaSessionBrowser.ID_PLAYLIST;
            }
            return str + '/' + mediaLibraryItem.getId();
        }

        public final boolean isMediaAudio(MediaLibraryItem mediaLibraryItem) {
            Intrinsics.checkNotNullParameter(mediaLibraryItem, "libraryItem");
            return mediaLibraryItem.getItemType() == 32 && ((MediaWrapper) mediaLibraryItem).getType() == 1;
        }

        static /* synthetic */ List paginateLibrary$default(Companion companion, MediaLibraryItem[] mediaLibraryItemArr, Uri uri, Uri uri2, Bundle bundle, int i, Object obj) {
            if ((i & 8) != 0) {
                bundle = null;
            }
            return companion.paginateLibrary(mediaLibraryItemArr, uri, uri2, bundle);
        }

        private final List<MediaBrowserCompat.MediaItem> paginateLibrary(MediaLibraryItem[] mediaLibraryItemArr, Uri uri, Uri uri2, Bundle bundle) {
            List<MediaBrowserCompat.MediaItem> arrayList = new ArrayList<>();
            int length = mediaLibraryItemArr.length / 800;
            if (length >= 0) {
                int i = 0;
                while (true) {
                    int i2 = i * 800;
                    int coerceAtMost = RangesKt.coerceAtMost(i2 + 799, mediaLibraryItemArr.length - 1);
                    if (i2 >= coerceAtMost) {
                        break;
                    }
                    MediaDescriptionCompat.Builder builder = new MediaDescriptionCompat.Builder();
                    String title = mediaLibraryItemArr[i2].getTitle();
                    Intrinsics.checkNotNullExpressionValue(title, "getTitle(...)");
                    String title2 = mediaLibraryItemArr[coerceAtMost].getTitle();
                    Intrinsics.checkNotNullExpressionValue(title2, "getTitle(...)");
                    arrayList.add(new MediaBrowserCompat.MediaItem(builder.setTitle(buildRangeLabel(title, title2)).setMediaId(uri.buildUpon().appendQueryParameter("p", String.valueOf(i)).toString()).setIconUri(uri2).setExtras(bundle).build(), 1));
                    if (arrayList.size() == 800 || i == length) {
                        break;
                    }
                    i++;
                }
            }
            return arrayList;
        }

        private final String buildRangeLabel(String str, String str2) {
            String formatArticles = MediaComparators.INSTANCE.formatArticles(str, true);
            String formatArticles2 = MediaComparators.INSTANCE.formatArticles(str2, true);
            int length = formatArticles.length();
            int length2 = formatArticles2.length();
            if (length > 10 && length2 > 10) {
                length = 10;
                length2 = 10;
            } else if (length > 10) {
                length = RangesKt.coerceAtMost(20 - length2, length);
            } else if (length2 > 10) {
                length2 = RangesKt.coerceAtMost(20 - length, length2);
            }
            return TextUtils.INSTANCE.separatedStringArgs(Strings.markBidi(Strings.abbreviate(formatArticles, length)), Strings.markBidi(Strings.abbreviate(formatArticles2, length2)));
        }

        static /* synthetic */ MediaDescriptionCompat.Builder getPlayAllBuilder$default(Companion companion, Context context, String str, int i, int i2, Uri uri, int i3, Object obj) {
            if ((i3 & 16) != 0) {
                uri = null;
            }
            return companion.getPlayAllBuilder(context, str, i, i2, uri);
        }

        private final MediaDescriptionCompat.Builder getPlayAllBuilder(Context context, String str, int i, int i2, Uri uri) {
            Uri uri2;
            Resources resources = context.getResources();
            MediaDescriptionCompat.Builder subtitle = new MediaDescriptionCompat.Builder().setMediaId(str).setTitle(resources.getString(i)).setSubtitle(resources.getString(R.string.track_number, new Object[]{Integer.valueOf(i2)}));
            if (uri != null) {
                uri2 = ArtworkProvider.Companion.buildUri(context, uri);
            } else {
                Intrinsics.checkNotNull(resources);
                uri2 = KotlinExtensionsKt.getResourceUri(resources, R.drawable.ic_auto_playall);
            }
            MediaDescriptionCompat.Builder iconUri = subtitle.setIconUri(uri2);
            Intrinsics.checkNotNullExpressionValue(iconUri, "setIconUri(...)");
            return iconUri;
        }
    }
}
