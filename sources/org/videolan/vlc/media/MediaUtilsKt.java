package org.videolan.vlc.media;

import android.content.Context;
import androidx.collection.SimpleArrayMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.medialibrary.interfaces.media.Album;
import org.videolan.medialibrary.interfaces.media.Artist;
import org.videolan.medialibrary.interfaces.media.Folder;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.interfaces.media.VideoGroup;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.resources.interfaces.IMediaContentResolver;
import org.videolan.tools.AppScope;

@Metadata(d1 = {"\u0000f\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u0003\u001a\"\u0010\u0007\u001a\u00020\b*\u0012\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\n0\tj\u0002`\u000b2\u0006\u0010\f\u001a\u00020\u0001\u001a>\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e*\b\u0012\u0004\u0012\u00020\u00100\u000e2\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00122\b\b\u0002\u0010\u0014\u001a\u00020\b2\b\b\u0002\u0010\u0015\u001a\u00020\b\u001a4\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e*\b\u0012\u0004\u0012\u00020\u00160\u000e2\b\b\u0002\u0010\u0013\u001a\u00020\u00122\b\b\u0002\u0010\u0014\u001a\u00020\b2\b\b\u0002\u0010\u0015\u001a\u00020\b\u001a:\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e*\u00020\u00172\b\b\u0002\u0010\u0013\u001a\u00020\u00122\b\b\u0002\u0010\u0014\u001a\u00020\b2\b\b\u0002\u0010\u0018\u001a\u00020\b2\b\b\u0002\u0010\u0015\u001a\u00020\bH\u0007\u001a:\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e*\u00020\u00192\b\b\u0002\u0010\u0013\u001a\u00020\u00122\b\b\u0002\u0010\u0014\u001a\u00020\b2\b\b\u0002\u0010\u0018\u001a\u00020\b2\b\b\u0002\u0010\u0015\u001a\u00020\bH\u0007\u001aD\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e*\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00122\b\b\u0002\u0010\u0014\u001a\u00020\b2\b\b\u0002\u0010\u0018\u001a\u00020\b2\b\b\u0002\u0010\u0015\u001a\u00020\bH\u0007\u001a:\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e*\u00020\u001a2\b\b\u0002\u0010\u0013\u001a\u00020\u00122\b\b\u0002\u0010\u0014\u001a\u00020\b2\b\b\u0002\u0010\u0018\u001a\u00020\b2\b\b\u0002\u0010\u0015\u001a\u00020\bH\u0007\u001aJ\u0010\u001b\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u001cj\u0002`\u001d*\u0012\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\n0\tj\u0002`\u000b2\u0006\u0010\u001e\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u0001H@¢\u0006\u0002\u0010\u001f\u001a%\u0010 \u001a\u0010\u0012\f\u0012\n !*\u0004\u0018\u00010\u000f0\u000f0\u000e*\b\u0012\u0004\u0012\u00020\u00160\"H\u0002¢\u0006\u0002\u0010#\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u0018\u0010\u0002\u001a\u00020\u0003*\u00020\u00048BX\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006*$\b\u0002\u0010$\"\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\n0\t2\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\n0\t¨\u0006%"}, d2 = {"TAG", "", "scope", "Lkotlinx/coroutines/CoroutineScope;", "Landroid/content/Context;", "getScope", "(Landroid/content/Context;)Lkotlinx/coroutines/CoroutineScope;", "canHandle", "", "Landroidx/collection/SimpleArrayMap;", "Lorg/videolan/resources/interfaces/IMediaContentResolver;", "Lorg/videolan/vlc/media/MediaContentResolver;", "id", "getAll", "", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "Lorg/videolan/medialibrary/interfaces/media/Folder;", "type", "", "sort", "desc", "onlyFavorites", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "Lorg/videolan/medialibrary/interfaces/media/Album;", "includeMissing", "Lorg/videolan/medialibrary/interfaces/media/Artist;", "Lorg/videolan/medialibrary/interfaces/media/VideoGroup;", "getList", "Lkotlin/Pair;", "Lorg/videolan/resources/interfaces/ResumableList;", "context", "(Landroidx/collection/SimpleArrayMap;Landroid/content/Context;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toList", "kotlin.jvm.PlatformType", "", "([Lorg/videolan/medialibrary/media/MediaLibraryItem;)Ljava/util/List;", "MediaContentResolver", "vlc-android_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaUtils.kt */
public final class MediaUtilsKt {
    private static final String TAG = "VLC/MediaUtils";

    public static /* synthetic */ List getAll$default(Folder folder, int i, int i2, boolean z, boolean z2, boolean z3, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = Folder.TYPE_FOLDER_VIDEO;
        }
        boolean z4 = false;
        int i4 = (i3 & 2) != 0 ? 0 : i2;
        boolean z5 = (i3 & 4) != 0 ? false : z;
        boolean z6 = (i3 & 8) != 0 ? true : z2;
        if ((i3 & 16) == 0) {
            z4 = z3;
        }
        return getAll(folder, i, i4, z5, z6, z4);
    }

    public static final List<MediaWrapper> getAll(Folder folder, int i, int i2, boolean z, boolean z2, boolean z3) {
        Folder folder2 = folder;
        Intrinsics.checkNotNullParameter(folder, "<this>");
        int mediaCount = folder.mediaCount(i);
        List<MediaWrapper> arrayList = new ArrayList<>();
        int i3 = 0;
        while (i3 < mediaCount) {
            int min = Math.min(500, mediaCount - i3);
            MediaWrapper[] media = folder.media(i, i2, z, z2, z3, min, i3);
            Intrinsics.checkNotNull(media);
            CollectionsKt.addAll(arrayList, (T[]) media);
            i3 += min;
        }
        return arrayList;
    }

    public static /* synthetic */ List getAll$default(VideoGroup videoGroup, int i, boolean z, boolean z2, boolean z3, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        if ((i2 & 2) != 0) {
            z = false;
        }
        if ((i2 & 4) != 0) {
            z2 = true;
        }
        if ((i2 & 8) != 0) {
            z3 = false;
        }
        return getAll(videoGroup, i, z, z2, z3);
    }

    public static final List<MediaWrapper> getAll(VideoGroup videoGroup, int i, boolean z, boolean z2, boolean z3) {
        Intrinsics.checkNotNullParameter(videoGroup, "<this>");
        int mediaCount = videoGroup.mediaCount();
        List<MediaWrapper> arrayList = new ArrayList<>();
        int i2 = 0;
        while (i2 < mediaCount) {
            int min = Math.min(500, mediaCount - i2);
            MediaWrapper[] media = videoGroup.media(i, z, z2, z3, min, i2);
            Intrinsics.checkNotNull(media);
            CollectionsKt.addAll(arrayList, (T[]) media);
            i2 += min;
        }
        return arrayList;
    }

    public static /* synthetic */ List getAll$default(Album album, int i, boolean z, boolean z2, boolean z3, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        if ((i2 & 2) != 0) {
            z = false;
        }
        if ((i2 & 4) != 0) {
            z2 = true;
        }
        if ((i2 & 8) != 0) {
            z3 = false;
        }
        return getAll(album, i, z, z2, z3);
    }

    public static final List<MediaWrapper> getAll(Album album, int i, boolean z, boolean z2, boolean z3) {
        Intrinsics.checkNotNullParameter(album, "<this>");
        int realTracksCount = album.getRealTracksCount();
        List<MediaWrapper> arrayList = new ArrayList<>();
        int i2 = 0;
        while (i2 < realTracksCount) {
            int min = Math.min(500, realTracksCount - i2);
            MediaWrapper[] pagedTracks = album.getPagedTracks(i, z, z2, z3, min, i2);
            Intrinsics.checkNotNull(pagedTracks);
            CollectionsKt.addAll(arrayList, (T[]) pagedTracks);
            i2 += min;
        }
        return arrayList;
    }

    public static /* synthetic */ List getAll$default(Artist artist, int i, boolean z, boolean z2, boolean z3, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        if ((i2 & 2) != 0) {
            z = false;
        }
        if ((i2 & 4) != 0) {
            z2 = true;
        }
        if ((i2 & 8) != 0) {
            z3 = false;
        }
        return getAll(artist, i, z, z2, z3);
    }

    public static final List<MediaWrapper> getAll(Artist artist, int i, boolean z, boolean z2, boolean z3) {
        Intrinsics.checkNotNullParameter(artist, "<this>");
        int tracksCount = artist.getTracksCount();
        List<MediaWrapper> arrayList = new ArrayList<>();
        int i2 = 0;
        while (i2 < tracksCount) {
            int min = Math.min(500, tracksCount - i2);
            MediaWrapper[] pagedTracks = artist.getPagedTracks(i, z, z2, z3, min, i2);
            Intrinsics.checkNotNull(pagedTracks);
            CollectionsKt.addAll(arrayList, (T[]) pagedTracks);
            i2 += min;
        }
        return arrayList;
    }

    public static final List<MediaWrapper> getAll(List<? extends MediaLibraryItem> list, int i, boolean z, boolean z2) {
        List list2;
        Intrinsics.checkNotNullParameter(list, "<this>");
        Collection arrayList = new ArrayList();
        for (MediaLibraryItem mediaLibraryItem : list) {
            if (mediaLibraryItem instanceof VideoGroup) {
                list2 = getAll$default((VideoGroup) mediaLibraryItem, i, z, z2, false, 8, (Object) null);
            } else if (mediaLibraryItem instanceof MediaWrapper) {
                list2 = CollectionsKt.listOf(mediaLibraryItem);
            } else {
                list2 = CollectionsKt.emptyList();
            }
            CollectionsKt.addAll(arrayList, list2);
        }
        return (List) arrayList;
    }

    public static /* synthetic */ List getAll$default(List list, int i, boolean z, boolean z2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        if ((i2 & 2) != 0) {
            z = false;
        }
        if ((i2 & 4) != 0) {
            z2 = false;
        }
        return getAll(list, i, z, z2);
    }

    public static final List<MediaWrapper> getAll(List<? extends Folder> list, int i, int i2, boolean z, boolean z2) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        Collection arrayList = new ArrayList();
        for (Folder all$default : list) {
            CollectionsKt.addAll(arrayList, getAll$default(all$default, i, i2, z, z2, false, 16, (Object) null));
        }
        return (List) arrayList;
    }

    public static /* synthetic */ List getAll$default(List list, int i, int i2, boolean z, boolean z2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = Folder.TYPE_FOLDER_VIDEO;
        }
        if ((i3 & 2) != 0) {
            i2 = 0;
        }
        if ((i3 & 4) != 0) {
            z = false;
        }
        if ((i3 & 8) != 0) {
            z2 = false;
        }
        return getAll((List<? extends Folder>) list, i, i2, z, z2);
    }

    public static final boolean canHandle(SimpleArrayMap<String, IMediaContentResolver> simpleArrayMap, String str) {
        Intrinsics.checkNotNullParameter(simpleArrayMap, "<this>");
        Intrinsics.checkNotNullParameter(str, "id");
        int size = simpleArrayMap.size();
        for (int i = 0; i < size; i++) {
            String keyAt = simpleArrayMap.keyAt(i);
            Intrinsics.checkNotNullExpressionValue(keyAt, "keyAt(...)");
            if (StringsKt.startsWith$default(str, keyAt, false, 2, (Object) null)) {
                return true;
            }
        }
        return false;
    }

    public static final Object getList(SimpleArrayMap<String, IMediaContentResolver> simpleArrayMap, Context context, String str, Continuation<? super Pair<? extends List<? extends MediaWrapper>, Integer>> continuation) {
        int size = simpleArrayMap.size();
        for (int i = 0; i < size; i++) {
            String keyAt = simpleArrayMap.keyAt(i);
            Intrinsics.checkNotNullExpressionValue(keyAt, "keyAt(...)");
            if (StringsKt.startsWith$default(str, keyAt, false, 2, (Object) null)) {
                return simpleArrayMap.valueAt(i).getList(context, str, continuation);
            }
        }
        return null;
    }

    /* access modifiers changed from: private */
    public static final CoroutineScope getScope(Context context) {
        CoroutineScope coroutineScope = context instanceof CoroutineScope ? (CoroutineScope) context : null;
        return coroutineScope == null ? AppScope.INSTANCE : coroutineScope;
    }

    private static final List<MediaWrapper> toList(MediaLibraryItem[] mediaLibraryItemArr) {
        List list;
        Collection arrayList = new ArrayList();
        for (VideoGroup videoGroup : mediaLibraryItemArr) {
            if (videoGroup instanceof VideoGroup) {
                VideoGroup videoGroup2 = videoGroup;
                MediaWrapper[] media = videoGroup2.media(0, false, true, false, videoGroup2.mediaCount(), 0);
                Intrinsics.checkNotNullExpressionValue(media, "media(...)");
                list = ArraysKt.toList((T[]) (Object[]) media);
            } else {
                Intrinsics.checkNotNull(videoGroup, "null cannot be cast to non-null type org.videolan.medialibrary.interfaces.media.MediaWrapper");
                list = CollectionsKt.listOf((MediaWrapper) videoGroup);
            }
            CollectionsKt.addAll(arrayList, list);
        }
        return (List) arrayList;
    }
}
