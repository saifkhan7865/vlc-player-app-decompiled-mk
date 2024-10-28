package org.videolan.vlc.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.net.Uri;
import androidx.leanback.preference.LeanbackPreferenceDialogFragment;
import java.io.File;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;
import org.videolan.medialibrary.interfaces.media.Folder;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.interfaces.media.VideoGroup;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.tools.BitmapCache;
import org.videolan.tools.PathUtilsKt;
import org.videolan.vlc.gui.helpers.AudioUtil;
import org.videolan.vlc.gui.helpers.BitmapUtil;

@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J3\u0010\f\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\u000f2\u0006\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u0004H\u0002¢\u0006\u0002\u0010\u0013J \u0010\u0014\u001a\u0004\u0018\u00010\r2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u00162\u0006\u0010\u0018\u001a\u00020\u0004H\u0002J0\u0010\u0019\u001a\u0004\u0018\u00010\r2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u00162\u0006\u0010\u001a\u001a\u00020\u00042\b\u0010\u001b\u001a\u0004\u0018\u00010\rH@¢\u0006\u0002\u0010\u001cJ&\u0010\u001d\u001a\u0012\u0012\u0004\u0012\u00020\u00170\u001ej\b\u0012\u0004\u0012\u00020\u0017`\u001f2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016H\u0002J(\u0010 \u001a\u0004\u0018\u00010\r2\u0006\u0010!\u001a\u00020\u00062\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u00162\u0006\u0010\u001a\u001a\u00020\u0004H\u0007J\u001a\u0010\"\u001a\u0004\u0018\u00010\r2\u0006\u0010#\u001a\u00020$2\u0006\u0010\u001a\u001a\u00020\u0004H\u0007J\"\u0010%\u001a\u0004\u0018\u00010\u00062\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020)2\b\b\u0002\u0010\u001a\u001a\u00020\u0006J\u001a\u0010*\u001a\u0004\u0018\u00010\r2\u0006\u0010(\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u0004H\u0007J\u001a\u0010+\u001a\u0004\u0018\u00010\u00062\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020)H\u0002J:\u0010,\u001a\u0004\u0018\u00010\r2\u0006\u0010!\u001a\u00020\u00062\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u00162\u0006\u0010\u001a\u001a\u00020\u00042\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\rH@¢\u0006\u0002\u0010-J\u001a\u0010.\u001a\u0004\u0018\u00010\r2\u0006\u0010/\u001a\u0002002\u0006\u0010\u001a\u001a\u00020\u0004H\u0007J\u001a\u00101\u001a\u0004\u0018\u00010\r2\u0006\u00102\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u0004H\u0007J\u000e\u00103\u001a\u00020'2\u0006\u0010(\u001a\u00020\u0017J \u00104\u001a\u0004\u0018\u00010\r2\u0006\u0010(\u001a\u00020)2\u0006\u0010\u001a\u001a\u00020\u0004H@¢\u0006\u0002\u00105R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u0006XT¢\u0006\b\n\u0000\u0012\u0004\b\u0007\u0010\u0002R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0001X\u0004¢\u0006\u0002\n\u0000¨\u00066"}, d2 = {"Lorg/videolan/vlc/util/ThumbnailsProvider;", "", "()V", "MAX_IMAGES", "", "TAG", "", "getTAG$annotations", "appDir", "Ljava/io/File;", "cacheDir", "lock", "composeCanvas", "Landroid/graphics/Bitmap;", "sourcesImages", "", "count", "minWidth", "minHeight", "([Landroid/graphics/Bitmap;III)Landroid/graphics/Bitmap;", "composeImage", "mediaList", "", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "imageWidth", "composePlaylistOrGenreImage", "width", "iconAddition", "(Ljava/util/List;ILandroid/graphics/Bitmap;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getArtworkListForPlaylistOrGenre", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "getComposedImage", "key", "getFolderThumbnail", "folder", "Lorg/videolan/medialibrary/interfaces/media/Folder;", "getMediaCacheKey", "isMedia", "", "item", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "getMediaThumbnail", "getMediaThumbnailPath", "getPlaylistOrGenreImage", "(Ljava/lang/String;Ljava/util/List;ILandroid/graphics/Bitmap;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getVideoGroupThumbnail", "group", "Lorg/videolan/medialibrary/interfaces/media/VideoGroup;", "getVideoThumbnail", "media", "isMediaVideo", "obtainBitmap", "(Lorg/videolan/medialibrary/media/MediaLibraryItem;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ThumbnailsProvider.kt */
public final class ThumbnailsProvider {
    public static final ThumbnailsProvider INSTANCE = new ThumbnailsProvider();
    private static final int MAX_IMAGES = 4;
    private static final String TAG = "VLC/ThumbnailsProvider";
    private static File appDir;
    private static String cacheDir;
    private static final Object lock = new Object();

    private static /* synthetic */ void getTAG$annotations() {
    }

    private ThumbnailsProvider() {
    }

    public final Bitmap getFolderThumbnail(Folder folder, int i) {
        Intrinsics.checkNotNullParameter(folder, "folder");
        MediaWrapper[] media = folder.media(Folder.TYPE_FOLDER_VIDEO, 0, true, true, false, 4, 0);
        Intrinsics.checkNotNullExpressionValue(media, "media(...)");
        List filterNotNull = ArraysKt.filterNotNull((Object[]) media);
        StringBuilder sb = new StringBuilder("folder:");
        String str = folder.mMrl;
        Intrinsics.checkNotNullExpressionValue(str, "mMrl");
        sb.append(PathUtilsKt.sanitizePath(str));
        return getComposedImage(sb.toString(), filterNotNull, i);
    }

    public final Bitmap getVideoGroupThumbnail(VideoGroup videoGroup, int i) {
        Intrinsics.checkNotNullParameter(videoGroup, "group");
        MediaWrapper[] media = videoGroup.media(0, true, true, false, 4, 0);
        Intrinsics.checkNotNullExpressionValue(media, "media(...)");
        List filterNotNull = ArraysKt.filterNotNull((Object[]) media);
        return getComposedImage("videogroup:" + videoGroup.getTitle(), filterNotNull, i);
    }

    public final Bitmap getMediaThumbnail(MediaWrapper mediaWrapper, int i) {
        Intrinsics.checkNotNullParameter(mediaWrapper, "item");
        if (isMediaVideo(mediaWrapper)) {
            return getVideoThumbnail(mediaWrapper, i);
        }
        return AudioUtil.INSTANCE.readCoverBitmap(Uri.decode(mediaWrapper.getArtworkMrl()), i);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x000b, code lost:
        r2 = r2.getArtworkMrl();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean isMediaVideo(org.videolan.medialibrary.interfaces.media.MediaWrapper r2) {
        /*
            r1 = this;
            java.lang.String r0 = "item"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            int r0 = r2.getType()
            if (r0 != 0) goto L_0x001b
            java.lang.String r2 = r2.getArtworkMrl()
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            if (r2 == 0) goto L_0x0019
            int r2 = r2.length()
            if (r2 != 0) goto L_0x001b
        L_0x0019:
            r2 = 1
            goto L_0x001c
        L_0x001b:
            r2 = 0
        L_0x001c:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.util.ThumbnailsProvider.isMediaVideo(org.videolan.medialibrary.interfaces.media.MediaWrapper):boolean");
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0068  */
    /* JADX WARNING: Removed duplicated region for block: B:25:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.lang.String getMediaThumbnailPath(boolean r5, org.videolan.medialibrary.media.MediaLibraryItem r6) {
        /*
            r4 = this;
            if (r5 == 0) goto L_0x0088
            java.lang.String r5 = "null cannot be cast to non-null type org.videolan.medialibrary.interfaces.media.MediaWrapper"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r6, r5)
            r5 = r6
            org.videolan.medialibrary.interfaces.media.MediaWrapper r5 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r5
            boolean r0 = r4.isMediaVideo(r5)
            if (r0 == 0) goto L_0x0088
            long r0 = r5.getId()
            r2 = 0
            int r6 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r6 != 0) goto L_0x0023
            android.net.Uri r5 = r5.getUri()
            java.lang.String r5 = r5.toString()
            return r5
        L_0x0023:
            java.io.File r6 = appDir
            r0 = 0
            if (r6 != 0) goto L_0x0034
            org.videolan.resources.AppContextProvider r6 = org.videolan.resources.AppContextProvider.INSTANCE
            android.content.Context r6 = r6.getAppContext()
            java.io.File r6 = r6.getExternalFilesDir(r0)
            appDir = r6
        L_0x0034:
            java.io.File r6 = appDir
            if (r6 == 0) goto L_0x0043
            kotlin.jvm.internal.Intrinsics.checkNotNull(r6)
            boolean r6 = r6.exists()
            if (r6 == 0) goto L_0x0043
            r6 = 1
            goto L_0x0044
        L_0x0043:
            r6 = 0
        L_0x0044:
            if (r6 == 0) goto L_0x0066
            java.lang.String r1 = cacheDir
            if (r1 != 0) goto L_0x0066
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.io.File r2 = appDir
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)
            java.lang.String r2 = r2.getAbsolutePath()
            r1.append(r2)
            java.lang.String r2 = "/medialib"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            cacheDir = r1
        L_0x0066:
            if (r6 == 0) goto L_0x0087
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r0 = cacheDir
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
            r6.<init>(r0)
            r0 = 47
            r6.append(r0)
            long r0 = r5.getId()
            r6.append(r0)
            java.lang.String r5 = ".jpg"
            r6.append(r5)
            java.lang.String r0 = r6.toString()
        L_0x0087:
            return r0
        L_0x0088:
            java.lang.String r5 = r6.getArtworkMrl()
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.util.ThumbnailsProvider.getMediaThumbnailPath(boolean, org.videolan.medialibrary.media.MediaLibraryItem):java.lang.String");
    }

    public static /* synthetic */ String getMediaCacheKey$default(ThumbnailsProvider thumbnailsProvider, boolean z, MediaLibraryItem mediaLibraryItem, String str, int i, Object obj) {
        if ((i & 4) != 0) {
            str = "";
        }
        return thumbnailsProvider.getMediaCacheKey(z, mediaLibraryItem, str);
    }

    public final String getMediaCacheKey(boolean z, MediaLibraryItem mediaLibraryItem, String str) {
        Intrinsics.checkNotNullParameter(mediaLibraryItem, "item");
        Intrinsics.checkNotNullParameter(str, "width");
        if (str.length() == 0) {
            return getMediaThumbnailPath(z, mediaLibraryItem);
        }
        return getMediaThumbnailPath(z, mediaLibraryItem) + '_' + str;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:37:0x009b, code lost:
        if (r0.sameAs(r6) != false) goto L_0x009f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.graphics.Bitmap getVideoThumbnail(org.videolan.medialibrary.interfaces.media.MediaWrapper r10, int r11) {
        /*
            r9 = this;
            java.lang.String r0 = "media"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r0)
            android.net.Uri r0 = r10.getUri()
            java.lang.String r0 = r0.getPath()
            r1 = 0
            if (r0 != 0) goto L_0x0011
            return r1
        L_0x0011:
            java.io.File r2 = appDir
            if (r2 != 0) goto L_0x0021
            org.videolan.resources.AppContextProvider r2 = org.videolan.resources.AppContextProvider.INSTANCE
            android.content.Context r2 = r2.getAppContext()
            java.io.File r2 = r2.getExternalFilesDir(r1)
            appDir = r2
        L_0x0021:
            java.io.File r2 = appDir
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L_0x002e
            boolean r2 = r2.exists()
            if (r2 != r4) goto L_0x002e
            r3 = 1
        L_0x002e:
            r2 = r10
            org.videolan.medialibrary.media.MediaLibraryItem r2 = (org.videolan.medialibrary.media.MediaLibraryItem) r2
            java.lang.String r5 = r9.getMediaThumbnailPath(r4, r2)
            if (r5 != 0) goto L_0x0038
            return r1
        L_0x0038:
            if (r3 == 0) goto L_0x0049
            org.videolan.tools.BitmapCache r6 = org.videolan.tools.BitmapCache.INSTANCE
            java.lang.String r7 = java.lang.String.valueOf(r11)
            java.lang.String r7 = r9.getMediaCacheKey(r4, r2, r7)
            android.graphics.Bitmap r6 = r6.getBitmapFromMemCache((java.lang.String) r7)
            goto L_0x004a
        L_0x0049:
            r6 = r1
        L_0x004a:
            if (r6 == 0) goto L_0x004d
            return r6
        L_0x004d:
            if (r3 == 0) goto L_0x0061
            java.io.File r6 = new java.io.File
            r6.<init>(r5)
            boolean r6 = r6.exists()
            if (r6 == 0) goto L_0x0061
            org.videolan.vlc.gui.helpers.AudioUtil r10 = org.videolan.vlc.gui.helpers.AudioUtil.INSTANCE
            android.graphics.Bitmap r10 = r10.readCoverBitmap(r5, r11)
            return r10
        L_0x0061:
            boolean r6 = r10.isThumbnailGenerated()
            if (r6 == 0) goto L_0x0068
            return r1
        L_0x0068:
            java.lang.Object r6 = lock
            monitor-enter(r6)
            android.net.Uri r7 = r10.getUri()     // Catch:{ all -> 0x00d5 }
            java.lang.String r7 = r7.getScheme()     // Catch:{ all -> 0x00d5 }
            boolean r7 = org.videolan.vlc.util.BrowserutilsKt.isSchemeFile(r7)     // Catch:{ all -> 0x00d5 }
            if (r7 == 0) goto L_0x007e
            android.graphics.Bitmap r0 = android.media.ThumbnailUtils.createVideoThumbnail(r0, r4)     // Catch:{ all -> 0x00d5 }
            goto L_0x007f
        L_0x007e:
            r0 = r1
        L_0x007f:
            monitor-exit(r6)
            if (r0 == 0) goto L_0x009e
            int r6 = r0.getWidth()
            int r7 = r0.getHeight()
            android.graphics.Bitmap$Config r8 = r0.getConfig()
            android.graphics.Bitmap r6 = android.graphics.Bitmap.createBitmap(r6, r7, r8)
            java.lang.String r7 = "createBitmap(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r7)
            boolean r6 = r0.sameAs(r6)
            if (r6 == 0) goto L_0x009e
            goto L_0x009f
        L_0x009e:
            r1 = r0
        L_0x009f:
            r6 = 0
            if (r1 == 0) goto L_0x00c6
            org.videolan.tools.BitmapCache r0 = org.videolan.tools.BitmapCache.INSTANCE
            java.lang.String r11 = java.lang.String.valueOf(r11)
            java.lang.String r11 = r9.getMediaCacheKey(r4, r2, r11)
            r0.addBitmapToMemCache((java.lang.String) r11, (android.graphics.Bitmap) r1)
            if (r3 == 0) goto L_0x00d4
            r10.setThumbnail(r5)
            long r2 = r10.getId()
            int r11 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            if (r11 <= 0) goto L_0x00d4
            org.videolan.vlc.gui.helpers.BitmapUtil r11 = org.videolan.vlc.gui.helpers.BitmapUtil.INSTANCE
            r11.saveOnDisk(r1, r5)
            r10.setArtworkURL(r5)
            goto L_0x00d4
        L_0x00c6:
            long r2 = r10.getId()
            int r0 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x00d4
            r0 = 1053609165(0x3ecccccd, float:0.4)
            r10.requestThumbnail(r11, r0)
        L_0x00d4:
            return r1
        L_0x00d5:
            r10 = move-exception
            monitor-exit(r6)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.util.ThumbnailsProvider.getVideoThumbnail(org.videolan.medialibrary.interfaces.media.MediaWrapper, int):android.graphics.Bitmap");
    }

    public static /* synthetic */ Object getPlaylistOrGenreImage$default(ThumbnailsProvider thumbnailsProvider, String str, List list, int i, Bitmap bitmap, Continuation continuation, int i2, Object obj) {
        if ((i2 & 8) != 0) {
            bitmap = null;
        }
        return thumbnailsProvider.getPlaylistOrGenreImage(str, list, i, bitmap, continuation);
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x003c  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0092  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0098 A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object getPlaylistOrGenreImage(java.lang.String r18, java.util.List<? extends org.videolan.medialibrary.interfaces.media.MediaWrapper> r19, int r20, android.graphics.Bitmap r21, kotlin.coroutines.Continuation<? super android.graphics.Bitmap> r22) {
        /*
            r17 = this;
            r0 = r17
            r1 = r19
            r2 = r22
            boolean r3 = r2 instanceof org.videolan.vlc.util.ThumbnailsProvider$getPlaylistOrGenreImage$1
            if (r3 == 0) goto L_0x001a
            r3 = r2
            org.videolan.vlc.util.ThumbnailsProvider$getPlaylistOrGenreImage$1 r3 = (org.videolan.vlc.util.ThumbnailsProvider$getPlaylistOrGenreImage$1) r3
            int r4 = r3.label
            r5 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r4 & r5
            if (r4 == 0) goto L_0x001a
            int r2 = r3.label
            int r2 = r2 - r5
            r3.label = r2
            goto L_0x001f
        L_0x001a:
            org.videolan.vlc.util.ThumbnailsProvider$getPlaylistOrGenreImage$1 r3 = new org.videolan.vlc.util.ThumbnailsProvider$getPlaylistOrGenreImage$1
            r3.<init>(r0, r2)
        L_0x001f:
            java.lang.Object r2 = r3.result
            java.lang.Object r4 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r5 = r3.label
            r6 = 1
            if (r5 == 0) goto L_0x003c
            if (r5 != r6) goto L_0x0034
            java.lang.Object r1 = r3.L$0
            java.lang.String r1 = (java.lang.String) r1
            kotlin.ResultKt.throwOnFailure(r2)
            goto L_0x008c
        L_0x0034:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r1
        L_0x003c:
            kotlin.ResultKt.throwOnFailure(r2)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r5 = r18
            r2.append(r5)
            java.util.ArrayList r5 = r0.getArtworkListForPlaylistOrGenre(r1)
            r7 = r5
            java.lang.Iterable r7 = (java.lang.Iterable) r7
            java.lang.String r5 = "_"
            r8 = r5
            java.lang.CharSequence r8 = (java.lang.CharSequence) r8
            java.lang.String r5 = ":"
            r9 = r5
            java.lang.CharSequence r9 = (java.lang.CharSequence) r9
            org.videolan.vlc.util.ThumbnailsProvider$getPlaylistOrGenreImage$saltedKey$1 r5 = org.videolan.vlc.util.ThumbnailsProvider$getPlaylistOrGenreImage$saltedKey$1.INSTANCE
            r13 = r5
            kotlin.jvm.functions.Function1 r13 = (kotlin.jvm.functions.Function1) r13
            r14 = 28
            r15 = 0
            r10 = 0
            r11 = 0
            r12 = 0
            java.lang.String r5 = kotlin.collections.CollectionsKt.joinToString$default(r7, r8, r9, r10, r11, r12, r13, r14, r15)
            r2.append(r5)
            java.lang.String r2 = r2.toString()
            org.videolan.tools.BitmapCache r5 = org.videolan.tools.BitmapCache.INSTANCE
            android.graphics.Bitmap r5 = r5.getBitmapFromMemCache((java.lang.String) r2)
            if (r5 != 0) goto L_0x0090
            r3.L$0 = r2
            r3.label = r6
            r5 = r20
            r6 = r21
            java.lang.Object r1 = r0.composePlaylistOrGenreImage(r1, r5, r6, r3)
            if (r1 != r4) goto L_0x0087
            return r4
        L_0x0087:
            r16 = r2
            r2 = r1
            r1 = r16
        L_0x008c:
            r5 = r2
            android.graphics.Bitmap r5 = (android.graphics.Bitmap) r5
            r2 = r1
        L_0x0090:
            if (r5 == 0) goto L_0x0098
            org.videolan.tools.BitmapCache r1 = org.videolan.tools.BitmapCache.INSTANCE
            r1.addBitmapToMemCache((java.lang.String) r2, (android.graphics.Bitmap) r5)
            goto L_0x0099
        L_0x0098:
            r5 = 0
        L_0x0099:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.util.ThumbnailsProvider.getPlaylistOrGenreImage(java.lang.String, java.util.List, int, android.graphics.Bitmap, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0049  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0056  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00b4 A[EDGE_INSN: B:48:0x00b4->B:38:0x00b4 ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.util.ArrayList<org.videolan.medialibrary.interfaces.media.MediaWrapper> getArtworkListForPlaylistOrGenre(java.util.List<? extends org.videolan.medialibrary.interfaces.media.MediaWrapper> r9) {
        /*
            r8 = this;
            boolean r0 = r9.isEmpty()
            if (r0 == 0) goto L_0x000c
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
            return r9
        L_0x000c:
            r0 = 0
            java.lang.Object r1 = r9.get(r0)
            org.videolan.medialibrary.interfaces.media.MediaWrapper r1 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r1
            java.lang.String r1 = r1.getArtworkURL()
            r2 = r9
            java.lang.Iterable r2 = (java.lang.Iterable) r2
            boolean r3 = r2 instanceof java.util.Collection
            r4 = 1
            if (r3 == 0) goto L_0x002a
            r3 = r2
            java.util.Collection r3 = (java.util.Collection) r3
            boolean r3 = r3.isEmpty()
            if (r3 == 0) goto L_0x002a
        L_0x0028:
            r1 = 0
            goto L_0x0046
        L_0x002a:
            java.util.Iterator r2 = r2.iterator()
        L_0x002e:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x0028
            java.lang.Object r3 = r2.next()
            org.videolan.medialibrary.interfaces.media.MediaWrapper r3 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r3
            java.lang.String r3 = r3.getArtworkURL()
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r3, (java.lang.Object) r1)
            r3 = r3 ^ r4
            if (r3 == 0) goto L_0x002e
            r1 = 1
        L_0x0046:
            r1 = r1 ^ r4
            if (r1 == 0) goto L_0x0056
            org.videolan.medialibrary.interfaces.media.MediaWrapper[] r1 = new org.videolan.medialibrary.interfaces.media.MediaWrapper[r4]
            java.lang.Object r9 = r9.get(r0)
            r1[r0] = r9
            java.util.ArrayList r9 = kotlin.collections.CollectionsKt.arrayListOf(r1)
            return r9
        L_0x0056:
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            java.util.Iterator r9 = r9.iterator()
        L_0x005f:
            boolean r2 = r9.hasNext()
            r3 = 3
            if (r2 == 0) goto L_0x00b4
            java.lang.Object r2 = r9.next()
            org.videolan.medialibrary.interfaces.media.MediaWrapper r2 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r2
            r5 = r1
            java.lang.Iterable r5 = (java.lang.Iterable) r5
            boolean r6 = r5 instanceof java.util.Collection
            if (r6 == 0) goto L_0x007d
            r6 = r5
            java.util.Collection r6 = (java.util.Collection) r6
            boolean r6 = r6.isEmpty()
            if (r6 == 0) goto L_0x007d
            goto L_0x009c
        L_0x007d:
            java.util.Iterator r5 = r5.iterator()
        L_0x0081:
            boolean r6 = r5.hasNext()
            if (r6 == 0) goto L_0x009c
            java.lang.Object r6 = r5.next()
            org.videolan.medialibrary.interfaces.media.MediaWrapper r6 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r6
            java.lang.String r6 = r6.getArtworkURL()
            java.lang.String r7 = r2.getArtworkURL()
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r6, (java.lang.Object) r7)
            if (r6 == 0) goto L_0x0081
            goto L_0x00ae
        L_0x009c:
            java.lang.String r5 = r2.getArtworkURL()
            java.lang.CharSequence r5 = (java.lang.CharSequence) r5
            if (r5 == 0) goto L_0x00ae
            boolean r5 = kotlin.text.StringsKt.isBlank(r5)
            if (r5 == 0) goto L_0x00ab
            goto L_0x00ae
        L_0x00ab:
            r1.add(r2)
        L_0x00ae:
            int r2 = r1.size()
            if (r2 <= r3) goto L_0x005f
        L_0x00b4:
            int r9 = r1.size()
            r2 = 2
            if (r9 != r2) goto L_0x00ca
            java.lang.Object r9 = r1.get(r4)
            r1.add(r9)
            java.lang.Object r9 = r1.get(r0)
            r1.add(r9)
            goto L_0x00d7
        L_0x00ca:
            int r9 = r1.size()
            if (r9 != r3) goto L_0x00d7
            java.lang.Object r9 = r1.get(r0)
            r1.add(r9)
        L_0x00d7:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.util.ThumbnailsProvider.getArtworkListForPlaylistOrGenre(java.util.List):java.util.ArrayList");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0063  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0097 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00ad  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00c4  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00dc  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0101  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x010d  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x01dd  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object composePlaylistOrGenreImage(java.util.List<? extends org.videolan.medialibrary.interfaces.media.MediaWrapper> r18, int r19, android.graphics.Bitmap r20, kotlin.coroutines.Continuation<? super android.graphics.Bitmap> r21) {
        /*
            r17 = this;
            r0 = r17
            r1 = r19
            r2 = r21
            boolean r3 = r2 instanceof org.videolan.vlc.util.ThumbnailsProvider$composePlaylistOrGenreImage$1
            if (r3 == 0) goto L_0x001a
            r3 = r2
            org.videolan.vlc.util.ThumbnailsProvider$composePlaylistOrGenreImage$1 r3 = (org.videolan.vlc.util.ThumbnailsProvider$composePlaylistOrGenreImage$1) r3
            int r4 = r3.label
            r5 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r4 & r5
            if (r4 == 0) goto L_0x001a
            int r2 = r3.label
            int r2 = r2 - r5
            r3.label = r2
            goto L_0x001f
        L_0x001a:
            org.videolan.vlc.util.ThumbnailsProvider$composePlaylistOrGenreImage$1 r3 = new org.videolan.vlc.util.ThumbnailsProvider$composePlaylistOrGenreImage$1
            r3.<init>(r0, r2)
        L_0x001f:
            java.lang.Object r2 = r3.result
            java.lang.Object r4 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r5 = r3.label
            r6 = 4
            r7 = 1
            r8 = 2
            r9 = 0
            r10 = 0
            if (r5 == 0) goto L_0x0063
            if (r5 == r7) goto L_0x0055
            if (r5 != r8) goto L_0x004d
            int r1 = r3.I$0
            java.lang.Object r5 = r3.L$4
            java.util.Iterator r5 = (java.util.Iterator) r5
            java.lang.Object r11 = r3.L$3
            java.util.ArrayList r11 = (java.util.ArrayList) r11
            java.lang.Object r12 = r3.L$2
            android.graphics.Canvas r12 = (android.graphics.Canvas) r12
            java.lang.Object r13 = r3.L$1
            android.graphics.Bitmap r13 = (android.graphics.Bitmap) r13
            java.lang.Object r14 = r3.L$0
            android.graphics.Bitmap r14 = (android.graphics.Bitmap) r14
            kotlin.ResultKt.throwOnFailure(r2)
            goto L_0x00fd
        L_0x004d:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r1
        L_0x0055:
            int r1 = r3.I$0
            java.lang.Object r5 = r3.L$1
            java.util.ArrayList r5 = (java.util.ArrayList) r5
            java.lang.Object r11 = r3.L$0
            android.graphics.Bitmap r11 = (android.graphics.Bitmap) r11
            kotlin.ResultKt.throwOnFailure(r2)
            goto L_0x0093
        L_0x0063:
            kotlin.ResultKt.throwOnFailure(r2)
            java.util.ArrayList r5 = r17.getArtworkListForPlaylistOrGenre(r18)
            boolean r2 = r5.isEmpty()
            if (r2 == 0) goto L_0x0071
            return r9
        L_0x0071:
            int r2 = r5.size()
            if (r2 != r7) goto L_0x0098
            java.lang.Object r2 = r5.get(r10)
            java.lang.String r11 = "get(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r11)
            org.videolan.medialibrary.media.MediaLibraryItem r2 = (org.videolan.medialibrary.media.MediaLibraryItem) r2
            r11 = r20
            r3.L$0 = r11
            r3.L$1 = r5
            r3.I$0 = r1
            r3.label = r7
            java.lang.Object r2 = r0.obtainBitmap(r2, r1, r3)
            if (r2 != r4) goto L_0x0093
            return r4
        L_0x0093:
            android.graphics.Bitmap r2 = (android.graphics.Bitmap) r2
            if (r2 != 0) goto L_0x009b
            return r9
        L_0x0098:
            r11 = r20
            r2 = r9
        L_0x009b:
            android.graphics.Bitmap$Config r12 = android.graphics.Bitmap.Config.ARGB_8888
            android.graphics.Bitmap r12 = android.graphics.Bitmap.createBitmap(r1, r1, r12)
            java.lang.String r13 = "createBitmap(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r12, r13)
            android.graphics.Canvas r13 = new android.graphics.Canvas
            r13.<init>(r12)
            if (r2 == 0) goto L_0x00c4
            android.graphics.Rect r3 = new android.graphics.Rect
            int r4 = r2.getWidth()
            int r5 = r2.getHeight()
            r3.<init>(r10, r10, r4, r5)
            android.graphics.Rect r4 = new android.graphics.Rect
            r4.<init>(r10, r10, r1, r1)
            r13.drawBitmap(r2, r3, r4, r9)
            goto L_0x01db
        L_0x00c4:
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>(r6)
            java.lang.Iterable r5 = (java.lang.Iterable) r5
            java.util.Iterator r5 = r5.iterator()
            r14 = r11
            r11 = r2
            r16 = r13
            r13 = r12
            r12 = r16
        L_0x00d6:
            boolean r2 = r5.hasNext()
            if (r2 == 0) goto L_0x0109
            java.lang.Object r2 = r5.next()
            org.videolan.medialibrary.interfaces.media.MediaWrapper r2 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r2
            org.videolan.vlc.util.ThumbnailsProvider r15 = INSTANCE
            org.videolan.medialibrary.media.MediaLibraryItem r2 = (org.videolan.medialibrary.media.MediaLibraryItem) r2
            int r7 = r1 / 2
            r3.L$0 = r14
            r3.L$1 = r13
            r3.L$2 = r12
            r3.L$3 = r11
            r3.L$4 = r5
            r3.I$0 = r1
            r3.label = r8
            java.lang.Object r2 = r15.obtainBitmap(r2, r7, r3)
            if (r2 != r4) goto L_0x00fd
            return r4
        L_0x00fd:
            android.graphics.Bitmap r2 = (android.graphics.Bitmap) r2
            if (r2 == 0) goto L_0x0104
            r11.add(r2)
        L_0x0104:
            r11.size()
            r7 = 1
            goto L_0x00d6
        L_0x0109:
            r2 = 0
        L_0x010a:
            r3 = 3
            if (r2 >= r6) goto L_0x0136
            int r4 = r11.size()
            int r2 = r2 + 1
            if (r4 >= r2) goto L_0x010a
            org.videolan.vlc.gui.helpers.UiTools r4 = org.videolan.vlc.gui.helpers.UiTools.INSTANCE
            org.videolan.resources.AppContextProvider r5 = org.videolan.resources.AppContextProvider.INSTANCE
            android.content.Context r5 = r5.getAppContext()
            android.graphics.drawable.BitmapDrawable r4 = r4.getDefaultAudioDrawable(r5)
            android.graphics.Bitmap r4 = r4.getBitmap()
            r11.add(r4)
            int r4 = r11.size()
            if (r4 != r3) goto L_0x010a
            java.lang.Object r3 = r11.get(r10)
            r11.add(r3)
            goto L_0x010a
        L_0x0136:
            java.lang.Object r2 = r11.get(r10)
            android.graphics.Bitmap r2 = (android.graphics.Bitmap) r2
            android.graphics.Rect r4 = new android.graphics.Rect
            java.lang.Object r5 = r11.get(r10)
            android.graphics.Bitmap r5 = (android.graphics.Bitmap) r5
            int r5 = r5.getWidth()
            java.lang.Object r6 = r11.get(r10)
            android.graphics.Bitmap r6 = (android.graphics.Bitmap) r6
            int r6 = r6.getHeight()
            r4.<init>(r10, r10, r5, r6)
            android.graphics.Rect r5 = new android.graphics.Rect
            int r6 = r1 / 2
            r5.<init>(r10, r10, r6, r6)
            r12.drawBitmap(r2, r4, r5, r9)
            r2 = 1
            java.lang.Object r4 = r11.get(r2)
            android.graphics.Bitmap r4 = (android.graphics.Bitmap) r4
            android.graphics.Rect r5 = new android.graphics.Rect
            java.lang.Object r7 = r11.get(r2)
            android.graphics.Bitmap r7 = (android.graphics.Bitmap) r7
            int r7 = r7.getWidth()
            java.lang.Object r2 = r11.get(r2)
            android.graphics.Bitmap r2 = (android.graphics.Bitmap) r2
            int r2 = r2.getHeight()
            r5.<init>(r10, r10, r7, r2)
            android.graphics.Rect r2 = new android.graphics.Rect
            r2.<init>(r6, r10, r1, r6)
            r12.drawBitmap(r4, r5, r2, r9)
            java.lang.Object r2 = r11.get(r8)
            android.graphics.Bitmap r2 = (android.graphics.Bitmap) r2
            android.graphics.Rect r4 = new android.graphics.Rect
            java.lang.Object r5 = r11.get(r8)
            android.graphics.Bitmap r5 = (android.graphics.Bitmap) r5
            int r5 = r5.getWidth()
            java.lang.Object r7 = r11.get(r8)
            android.graphics.Bitmap r7 = (android.graphics.Bitmap) r7
            int r7 = r7.getHeight()
            r4.<init>(r10, r10, r5, r7)
            android.graphics.Rect r5 = new android.graphics.Rect
            r5.<init>(r10, r6, r6, r1)
            r12.drawBitmap(r2, r4, r5, r9)
            java.lang.Object r2 = r11.get(r3)
            android.graphics.Bitmap r2 = (android.graphics.Bitmap) r2
            android.graphics.Rect r4 = new android.graphics.Rect
            java.lang.Object r5 = r11.get(r3)
            android.graphics.Bitmap r5 = (android.graphics.Bitmap) r5
            int r5 = r5.getWidth()
            java.lang.Object r3 = r11.get(r3)
            android.graphics.Bitmap r3 = (android.graphics.Bitmap) r3
            int r3 = r3.getHeight()
            r4.<init>(r10, r10, r5, r3)
            android.graphics.Rect r3 = new android.graphics.Rect
            r3.<init>(r6, r6, r1, r1)
            r12.drawBitmap(r2, r4, r3, r9)
            r11 = r14
            r16 = r13
            r13 = r12
            r12 = r16
        L_0x01db:
            if (r11 == 0) goto L_0x01f9
            int r1 = r13.getWidth()
            float r1 = (float) r1
            int r2 = r11.getWidth()
            float r2 = (float) r2
            float r1 = r1 - r2
            float r2 = (float) r8
            float r1 = r1 / r2
            int r3 = r13.getHeight()
            float r3 = (float) r3
            int r4 = r11.getHeight()
            float r4 = (float) r4
            float r3 = r3 - r4
            float r3 = r3 / r2
            r13.drawBitmap(r11, r1, r3, r9)
        L_0x01f9:
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.util.ThumbnailsProvider.composePlaylistOrGenreImage(java.util.List, int, android.graphics.Bitmap, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final Object obtainBitmap(MediaLibraryItem mediaLibraryItem, int i, Continuation<? super Bitmap> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new ThumbnailsProvider$obtainBitmap$2(mediaLibraryItem, i, (Continuation<? super ThumbnailsProvider$obtainBitmap$2>) null), continuation);
    }

    public final Bitmap getComposedImage(String str, List<? extends MediaWrapper> list, int i) {
        Intrinsics.checkNotNullParameter(str, LeanbackPreferenceDialogFragment.ARG_KEY);
        Intrinsics.checkNotNullParameter(list, "mediaList");
        Bitmap bitmapFromMemCache = BitmapCache.INSTANCE.getBitmapFromMemCache(str);
        if (bitmapFromMemCache == null && (bitmapFromMemCache = composeImage(list, i)) != null) {
            BitmapCache.INSTANCE.addBitmapToMemCache(str, bitmapFromMemCache);
        }
        return bitmapFromMemCache;
    }

    private final Bitmap composeImage(List<? extends MediaWrapper> list, int i) {
        Bitmap[] bitmapArr = new Bitmap[Math.min(4, list.size())];
        int i2 = Integer.MAX_VALUE;
        int i3 = Integer.MAX_VALUE;
        int i4 = 0;
        for (MediaWrapper videoThumbnail : list) {
            Bitmap videoThumbnail2 = getVideoThumbnail(videoThumbnail, i);
            if (videoThumbnail2 != null) {
                int width = videoThumbnail2.getWidth();
                int height = videoThumbnail2.getHeight();
                int i5 = i4 + 1;
                bitmapArr[i4] = videoThumbnail2;
                i2 = Math.min(i2, width);
                i3 = Math.min(i3, height);
                i4 = i5;
                if (i5 == 4) {
                    break;
                }
            }
        }
        if (i4 == 0) {
            return null;
        }
        return i4 == 1 ? bitmapArr[0] : composeCanvas((Bitmap[]) ArraysKt.filterNotNull(bitmapArr).toArray(new Bitmap[0]), i4, i2, i3);
    }

    private final Bitmap composeCanvas(Bitmap[] bitmapArr, int i, int i2, int i3) {
        int i4;
        int i5;
        if (i == 4) {
            i5 = i2 * 2;
            i4 = i3 * 2;
        } else {
            i5 = i2;
            i4 = i3;
        }
        Bitmap createBitmap = Bitmap.createBitmap(i5, i4, bitmapArr[0].getConfig());
        Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(...)");
        Canvas canvas = new Canvas(createBitmap);
        if (i == 2) {
            for (int i6 = 0; i6 < i; i6++) {
                bitmapArr[i6] = BitmapUtil.INSTANCE.centerCrop(bitmapArr[i6], i2 / 2, i3);
            }
            canvas.drawBitmap(bitmapArr[0], 0.0f, 0.0f, (Paint) null);
            canvas.drawBitmap(bitmapArr[1], (float) (i2 / 2), 0.0f, (Paint) null);
        } else if (i == 3) {
            int i7 = i2 / 2;
            int i8 = i3 / 2;
            bitmapArr[0] = BitmapUtil.INSTANCE.centerCrop(bitmapArr[0], i7, i8);
            bitmapArr[1] = BitmapUtil.INSTANCE.centerCrop(bitmapArr[1], i7, i8);
            bitmapArr[2] = BitmapUtil.INSTANCE.centerCrop(bitmapArr[2], i2, i8);
            canvas.drawBitmap(bitmapArr[0], 0.0f, 0.0f, (Paint) null);
            canvas.drawBitmap(bitmapArr[1], (float) i7, 0.0f, (Paint) null);
            canvas.drawBitmap(bitmapArr[2], 0.0f, (float) i8, (Paint) null);
        } else if (i == 4) {
            for (int i9 = 0; i9 < i; i9++) {
                bitmapArr[i9] = BitmapUtil.INSTANCE.centerCrop(bitmapArr[i9], i2, i3);
            }
            canvas.drawBitmap(bitmapArr[0], 0.0f, 0.0f, (Paint) null);
            float f = (float) i2;
            canvas.drawBitmap(bitmapArr[1], f, 0.0f, (Paint) null);
            float f2 = (float) i3;
            canvas.drawBitmap(bitmapArr[2], 0.0f, f2, (Paint) null);
            canvas.drawBitmap(bitmapArr[3], f, f2, (Paint) null);
        }
        return createBitmap;
    }
}
