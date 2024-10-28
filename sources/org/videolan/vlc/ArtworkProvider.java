package org.videolan.vlc;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import android.util.LruCache;
import androidx.core.view.ViewCompat;
import androidx.leanback.preference.LeanbackPreferenceDialogFragment;
import java.io.File;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Set;
import java.util.zip.CRC32;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.resources.Constants;
import org.videolan.tools.Strings;
import org.videolan.vlc.ArtworkProvider$dateFormatter$2;
import org.videolan.vlc.gui.helpers.BitmapUtilKt;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006*\u0001\u0006\u0018\u0000 T2\u00020\u0001:\u0001TB\u0005¢\u0006\u0002\u0010\u0002J1\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0010\u0010\u0011\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0010\u0018\u00010\u0012H\u0016¢\u0006\u0002\u0010\u0013J4\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u001bH\u0002J+\u0010\u001d\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u00192\n\b\u0003\u0010\u001e\u001a\u0004\u0018\u00010\fH\u0002¢\u0006\u0002\u0010\u001fJ\u0012\u0010 \u001a\u0004\u0018\u00010!2\u0006\u0010\u0003\u001a\u00020\u0004H\u0002J0\u0010\"\u001a\u0004\u0018\u00010!2\u0006\u0010\u0016\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\u00102\u000e\u0010$\u001a\n\u0012\u0004\u0012\u00020%\u0018\u00010\u0012H@¢\u0006\u0002\u0010&J\u0012\u0010'\u001a\u0004\u0018\u00010!2\u0006\u0010\u0003\u001a\u00020\u0004H\u0002J=\u0010(\u001a\u00020\u00152\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010)\u001a\u00020\u00192\b\b\u0002\u0010*\u001a\u00020\u001b2\n\b\u0003\u0010\u001e\u001a\u0004\u0018\u00010\f2\b\b\u0002\u0010+\u001a\u00020\u001bH\u0002¢\u0006\u0002\u0010,J\u0012\u0010-\u001a\u00020\u00152\b\u0010.\u001a\u0004\u0018\u00010/H\u0002J\u0012\u00100\u001a\u00020\u00152\b\u00101\u001a\u0004\u0018\u00010!H\u0002J(\u00102\u001a\u00020\u00152\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u00103\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u00104\u001a\u00020\u001bH\u0002J+\u00105\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u00192\n\b\u0003\u0010\u001e\u001a\u0004\u0018\u00010\fH\u0002¢\u0006\u0002\u0010\u001fJ\u001c\u00106\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0003\u001a\u00020\u00042\b\u00107\u001a\u0004\u0018\u00010\u0010H\u0002J\u0012\u00108\u001a\u0004\u0018\u00010!2\u0006\u0010\u0003\u001a\u00020\u0004H\u0002J\n\u00109\u001a\u0004\u0018\u00010\u0010H\u0002J\u0010\u0010:\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u001a\u0010;\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010<\u001a\u0004\u0018\u00010=H\u0016J\u0010\u0010>\u001a\u00020\u001b2\u0006\u00107\u001a\u00020\u0010H\u0002J\u0014\u0010?\u001a\u00020@2\n\u0010A\u001a\u00060Bj\u0002`CH\u0002J\b\u0010D\u001a\u00020\u001bH\u0016J\u001a\u0010E\u001a\u0004\u0018\u00010\u00152\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010F\u001a\u00020\u0010H\u0016J\u0010\u0010*\u001a\u00020/2\u0006\u0010G\u001a\u00020/H\u0002JO\u0010H\u001a\u0004\u0018\u00010I2\u0006\u0010\r\u001a\u00020\u000e2\u0010\u0010J\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0010\u0018\u00010\u00122\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0010\u0010\u0011\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0010\u0018\u00010\u00122\b\u0010K\u001a\u0004\u0018\u00010\u0010H\u0016¢\u0006\u0002\u0010LJ\u001c\u0010M\u001a\u0004\u0018\u00010/2\b\u0010N\u001a\u0004\u0018\u00010O2\u0006\u0010P\u001a\u00020\fH\u0002J\u0014\u0010Q\u001a\u0004\u0018\u00010/2\b\u0010G\u001a\u0004\u0018\u00010/H\u0002J;\u0010R\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010<\u001a\u0004\u0018\u00010=2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0010\u0010\u0011\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0010\u0018\u00010\u0012H\u0016¢\u0006\u0002\u0010SR\u000e\u0010\u0003\u001a\u00020\u0004X.¢\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\b¨\u0006U"}, d2 = {"Lorg/videolan/vlc/ArtworkProvider;", "Landroid/content/ContentProvider;", "()V", "ctx", "Landroid/content/Context;", "dateFormatter", "org/videolan/vlc/ArtworkProvider$dateFormatter$2$1", "getDateFormatter", "()Lorg/videolan/vlc/ArtworkProvider$dateFormatter$2$1;", "dateFormatter$delegate", "Lkotlin/Lazy;", "delete", "", "uri", "Landroid/net/Uri;", "selection", "", "selectionArgs", "", "(Landroid/net/Uri;Ljava/lang/String;[Ljava/lang/String;)I", "getCategoryImage", "Landroid/os/ParcelFileDescriptor;", "context", "category", "id", "", "forRemote", "", "bigVariant", "getGenreImage", "fallbackIcon", "(Landroid/content/Context;JLjava/lang/Integer;)Landroid/os/ParcelFileDescriptor;", "getHistory", "", "getHomeImage", "key", "list", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "(Landroid/content/Context;Ljava/lang/String;[Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getLastAdded", "getMediaImage", "mediaId", "padSquare", "isLarge", "(Landroid/content/Context;JZLjava/lang/Integer;Z)Landroid/os/ParcelFileDescriptor;", "getPFDFromBitmap", "bitmap", "Landroid/graphics/Bitmap;", "getPFDFromByteArray", "byteArray", "getPlayAllImage", "type", "shuffle", "getPlaylistImage", "getRemoteImage", "path", "getShuffleAll", "getTimestamp", "getType", "insert", "values", "Landroid/content/ContentValues;", "isImageWithinBounds", "logError", "", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "onCreate", "openFile", "mode", "src", "query", "Landroid/database/Cursor;", "projection", "sortOrder", "(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;", "readEmbeddedArtwork", "mw", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "width", "removeTransparency", "update", "(Landroid/net/Uri;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I", "Companion", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ArtworkProvider.kt */
public final class ArtworkProvider extends ContentProvider {
    public static final String ALBUM = "album";
    public static final String ARTIST = "artist";
    public static final String BIG_VARIANT = "big_variant";
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String GENRE = "genre";
    public static final String HISTORY = "history";
    public static final String LAST_ADDED = "last_added";
    public static final String MEDIA = "media";
    public static final String PATH = "path";
    public static final String PLAYLIST = "playlist";
    public static final String PLAY_ALL = "play_all";
    public static final String REMOTE = "remote";
    public static final String REMOTE_ACCESS = "remote_access";
    public static final String SHUFFLE = "shuffle";
    public static final String SHUFFLE_ALL = "shuffle_all";
    public static final String VIDEO = "video";
    /* access modifiers changed from: private */
    public static final LruCache<String, byte[]> memCache = new LruCache<>(Build.VERSION.SDK_INT == 33 ? 2 : 1);
    private Context ctx;
    private final Lazy dateFormatter$delegate = LazyKt.lazy(ArtworkProvider$dateFormatter$2.INSTANCE);

    public int delete(Uri uri, String str, String[] strArr) {
        Intrinsics.checkNotNullParameter(uri, Constants.KEY_URI);
        return 0;
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        Intrinsics.checkNotNullParameter(uri, Constants.KEY_URI);
        return null;
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        Intrinsics.checkNotNullParameter(uri, Constants.KEY_URI);
        return 0;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v0, resolved type: android.content.Context} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v1, resolved type: android.content.Context} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v2, resolved type: android.content.Context} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v3, resolved type: android.content.Context} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v4, resolved type: android.content.Context} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v6, resolved type: android.content.Context} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v7, resolved type: android.content.Context} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v8, resolved type: android.content.Context} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v4, resolved type: java.lang.Integer} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v13, resolved type: android.content.Context} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v14, resolved type: android.content.Context} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v15, resolved type: android.content.Context} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v16, resolved type: android.content.Context} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v18, resolved type: android.content.Context} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v19, resolved type: android.content.Context} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v20, resolved type: android.content.Context} */
    /* JADX WARNING: type inference failed for: r4v10, types: [java.lang.Integer] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.os.ParcelFileDescriptor openFile(android.net.Uri r13, java.lang.String r14) {
        /*
            r12 = this;
            java.lang.String r0 = "1"
            java.lang.String r1 = "Uri is not supported: "
            java.lang.String r2 = "uri"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r13, r2)
            java.lang.String r2 = "mode"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r14, r2)
            int r14 = android.os.Binder.getCallingUid()
            org.videolan.vlc.util.AccessControl r2 = org.videolan.vlc.util.AccessControl.INSTANCE
            r3 = 2
            r4 = 0
            org.videolan.vlc.util.AccessControl.logCaller$default(r2, r14, r4, r3, r4)
            java.util.List r14 = r13.getPathSegments()
            boolean r2 = r14.isEmpty()
            if (r2 != 0) goto L_0x0237
            java.lang.String r2 = "big_variant"
            java.lang.String r2 = r13.getQueryParameter(r2)     // Catch:{ Exception -> 0x022c }
            boolean r11 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2, (java.lang.Object) r0)     // Catch:{ Exception -> 0x022c }
            java.lang.String r2 = "remote_access"
            java.lang.String r2 = r13.getQueryParameter(r2)     // Catch:{ Exception -> 0x022c }
            boolean r10 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2, (java.lang.Object) r0)     // Catch:{ Exception -> 0x022c }
            r0 = 0
            java.lang.Object r2 = r14.get(r0)     // Catch:{ Exception -> 0x022c }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ Exception -> 0x022c }
            if (r2 == 0) goto L_0x021a
            int r3 = r2.hashCode()     // Catch:{ Exception -> 0x022c }
            java.lang.String r5 = "ctx"
            switch(r3) {
                case -2029646313: goto L_0x0200;
                case -1409097913: goto L_0x01e2;
                case -934610874: goto L_0x01c6;
                case 92896879: goto L_0x01a8;
                case 98240899: goto L_0x0168;
                case 103772132: goto L_0x0138;
                case 112202875: goto L_0x00f0;
                case 424921947: goto L_0x00d5;
                case 926934164: goto L_0x00ba;
                case 1879079446: goto L_0x008c;
                case 1879474642: goto L_0x004c;
                default: goto L_0x004a;
            }
        L_0x004a:
            goto L_0x021a
        L_0x004c:
            java.lang.String r14 = "playlist"
            boolean r14 = r2.equals(r14)     // Catch:{ Exception -> 0x022c }
            if (r14 == 0) goto L_0x021a
            if (r10 == 0) goto L_0x0074
            android.content.Context r14 = r12.ctx     // Catch:{ Exception -> 0x022c }
            if (r14 != 0) goto L_0x005e
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r5)     // Catch:{ Exception -> 0x022c }
            goto L_0x005f
        L_0x005e:
            r4 = r14
        L_0x005f:
            long r13 = android.content.ContentUris.parseId(r13)     // Catch:{ Exception -> 0x022c }
            if (r11 == 0) goto L_0x0068
            int r0 = org.videolan.vlc.R.drawable.ic_remote_playlist_unknown_big     // Catch:{ Exception -> 0x022c }
            goto L_0x006a
        L_0x0068:
            int r0 = org.videolan.vlc.R.drawable.ic_remote_playlist_unknown     // Catch:{ Exception -> 0x022c }
        L_0x006a:
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ Exception -> 0x022c }
            android.os.ParcelFileDescriptor r13 = r12.getPlaylistImage(r4, r13, r0)     // Catch:{ Exception -> 0x022c }
            goto L_0x0219
        L_0x0074:
            android.content.Context r14 = r12.ctx     // Catch:{ Exception -> 0x022c }
            if (r14 != 0) goto L_0x007d
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r5)     // Catch:{ Exception -> 0x022c }
            r1 = r4
            goto L_0x007e
        L_0x007d:
            r1 = r14
        L_0x007e:
            long r2 = android.content.ContentUris.parseId(r13)     // Catch:{ Exception -> 0x022c }
            r5 = 4
            r6 = 0
            r4 = 0
            r0 = r12
            android.os.ParcelFileDescriptor r13 = getPlaylistImage$default(r0, r1, r2, r4, r5, r6)     // Catch:{ Exception -> 0x022c }
            goto L_0x0219
        L_0x008c:
            java.lang.String r3 = "play_all"
            boolean r2 = r2.equals(r3)     // Catch:{ Exception -> 0x022c }
            if (r2 == 0) goto L_0x021a
            android.content.Context r1 = r12.ctx     // Catch:{ Exception -> 0x022c }
            if (r1 != 0) goto L_0x009c
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r5)     // Catch:{ Exception -> 0x022c }
            r1 = r4
        L_0x009c:
            r2 = 1
            java.lang.Object r14 = r14.get(r2)     // Catch:{ Exception -> 0x022c }
            java.lang.String r2 = "get(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r14, r2)     // Catch:{ Exception -> 0x022c }
            r2 = r14
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ Exception -> 0x022c }
            long r3 = android.content.ContentUris.parseId(r13)     // Catch:{ Exception -> 0x022c }
            java.lang.String r14 = "shuffle"
            boolean r5 = r13.getBooleanQueryParameter(r14, r0)     // Catch:{ Exception -> 0x022c }
            r0 = r12
            android.os.ParcelFileDescriptor r13 = r0.getPlayAllImage(r1, r2, r3, r5)     // Catch:{ Exception -> 0x022c }
            goto L_0x0219
        L_0x00ba:
            java.lang.String r14 = "history"
            boolean r14 = r2.equals(r14)     // Catch:{ Exception -> 0x022c }
            if (r14 == 0) goto L_0x021a
            android.content.Context r13 = r12.ctx     // Catch:{ Exception -> 0x022c }
            if (r13 != 0) goto L_0x00ca
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r5)     // Catch:{ Exception -> 0x022c }
            goto L_0x00cb
        L_0x00ca:
            r4 = r13
        L_0x00cb:
            byte[] r13 = r12.getHistory(r4)     // Catch:{ Exception -> 0x022c }
            android.os.ParcelFileDescriptor r13 = r12.getPFDFromByteArray(r13)     // Catch:{ Exception -> 0x022c }
            goto L_0x0219
        L_0x00d5:
            java.lang.String r14 = "shuffle_all"
            boolean r14 = r2.equals(r14)     // Catch:{ Exception -> 0x022c }
            if (r14 == 0) goto L_0x021a
            android.content.Context r13 = r12.ctx     // Catch:{ Exception -> 0x022c }
            if (r13 != 0) goto L_0x00e5
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r5)     // Catch:{ Exception -> 0x022c }
            goto L_0x00e6
        L_0x00e5:
            r4 = r13
        L_0x00e6:
            byte[] r13 = r12.getShuffleAll(r4)     // Catch:{ Exception -> 0x022c }
            android.os.ParcelFileDescriptor r13 = r12.getPFDFromByteArray(r13)     // Catch:{ Exception -> 0x022c }
            goto L_0x0219
        L_0x00f0:
            java.lang.String r14 = "video"
            boolean r14 = r2.equals(r14)     // Catch:{ Exception -> 0x022c }
            if (r14 == 0) goto L_0x021a
            if (r10 == 0) goto L_0x011d
            android.content.Context r14 = r12.ctx     // Catch:{ Exception -> 0x022c }
            if (r14 != 0) goto L_0x0104
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r5)     // Catch:{ Exception -> 0x022c }
            r1 = r4
            goto L_0x0105
        L_0x0104:
            r1 = r14
        L_0x0105:
            long r2 = android.content.ContentUris.parseId(r13)     // Catch:{ Exception -> 0x022c }
            if (r11 == 0) goto L_0x010e
            int r13 = org.videolan.vlc.R.drawable.ic_remote_video_unknown_big     // Catch:{ Exception -> 0x022c }
            goto L_0x0110
        L_0x010e:
            int r13 = org.videolan.vlc.R.drawable.ic_remote_video_unknown     // Catch:{ Exception -> 0x022c }
        L_0x0110:
            java.lang.Integer r5 = java.lang.Integer.valueOf(r13)     // Catch:{ Exception -> 0x022c }
            r6 = 1
            r4 = 0
            r0 = r12
            android.os.ParcelFileDescriptor r13 = r0.getMediaImage(r1, r2, r4, r5, r6)     // Catch:{ Exception -> 0x022c }
            goto L_0x0219
        L_0x011d:
            android.content.Context r14 = r12.ctx     // Catch:{ Exception -> 0x022c }
            if (r14 != 0) goto L_0x0126
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r5)     // Catch:{ Exception -> 0x022c }
            r1 = r4
            goto L_0x0127
        L_0x0126:
            r1 = r14
        L_0x0127:
            long r2 = android.content.ContentUris.parseId(r13)     // Catch:{ Exception -> 0x022c }
            r7 = 24
            r8 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            r0 = r12
            android.os.ParcelFileDescriptor r13 = getMediaImage$default(r0, r1, r2, r4, r5, r6, r7, r8)     // Catch:{ Exception -> 0x022c }
            goto L_0x0219
        L_0x0138:
            java.lang.String r14 = "media"
            boolean r14 = r2.equals(r14)     // Catch:{ Exception -> 0x022c }
            if (r14 == 0) goto L_0x021a
            android.content.Context r14 = r12.ctx     // Catch:{ Exception -> 0x022c }
            if (r14 != 0) goto L_0x0149
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r5)     // Catch:{ Exception -> 0x022c }
            r1 = r4
            goto L_0x014a
        L_0x0149:
            r1 = r14
        L_0x014a:
            long r2 = android.content.ContentUris.parseId(r13)     // Catch:{ Exception -> 0x022c }
            if (r10 == 0) goto L_0x015b
            if (r11 == 0) goto L_0x0155
            int r13 = org.videolan.vlc.R.drawable.ic_remote_song_unknown_big     // Catch:{ Exception -> 0x022c }
            goto L_0x0157
        L_0x0155:
            int r13 = org.videolan.vlc.R.drawable.ic_remote_song_unknown     // Catch:{ Exception -> 0x022c }
        L_0x0157:
            java.lang.Integer r4 = java.lang.Integer.valueOf(r13)     // Catch:{ Exception -> 0x022c }
        L_0x015b:
            r5 = r4
            r7 = 20
            r8 = 0
            r4 = 0
            r6 = 0
            r0 = r12
            android.os.ParcelFileDescriptor r13 = getMediaImage$default(r0, r1, r2, r4, r5, r6, r7, r8)     // Catch:{ Exception -> 0x022c }
            goto L_0x0219
        L_0x0168:
            java.lang.String r14 = "genre"
            boolean r14 = r2.equals(r14)     // Catch:{ Exception -> 0x022c }
            if (r14 == 0) goto L_0x021a
            if (r10 == 0) goto L_0x0190
            android.content.Context r14 = r12.ctx     // Catch:{ Exception -> 0x022c }
            if (r14 != 0) goto L_0x017a
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r5)     // Catch:{ Exception -> 0x022c }
            goto L_0x017b
        L_0x017a:
            r4 = r14
        L_0x017b:
            long r13 = android.content.ContentUris.parseId(r13)     // Catch:{ Exception -> 0x022c }
            if (r11 == 0) goto L_0x0184
            int r0 = org.videolan.vlc.R.drawable.ic_remote_genre_unknown_big     // Catch:{ Exception -> 0x022c }
            goto L_0x0186
        L_0x0184:
            int r0 = org.videolan.vlc.R.drawable.ic_remote_genre_unknown     // Catch:{ Exception -> 0x022c }
        L_0x0186:
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ Exception -> 0x022c }
            android.os.ParcelFileDescriptor r13 = r12.getGenreImage(r4, r13, r0)     // Catch:{ Exception -> 0x022c }
            goto L_0x0219
        L_0x0190:
            android.content.Context r14 = r12.ctx     // Catch:{ Exception -> 0x022c }
            if (r14 != 0) goto L_0x0199
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r5)     // Catch:{ Exception -> 0x022c }
            r1 = r4
            goto L_0x019a
        L_0x0199:
            r1 = r14
        L_0x019a:
            long r2 = android.content.ContentUris.parseId(r13)     // Catch:{ Exception -> 0x022c }
            r5 = 4
            r6 = 0
            r4 = 0
            r0 = r12
            android.os.ParcelFileDescriptor r13 = getGenreImage$default(r0, r1, r2, r4, r5, r6)     // Catch:{ Exception -> 0x022c }
            goto L_0x0219
        L_0x01a8:
            java.lang.String r14 = "album"
            boolean r14 = r2.equals(r14)     // Catch:{ Exception -> 0x022c }
            if (r14 == 0) goto L_0x021a
            android.content.Context r14 = r12.ctx     // Catch:{ Exception -> 0x022c }
            if (r14 != 0) goto L_0x01b9
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r5)     // Catch:{ Exception -> 0x022c }
            r6 = r4
            goto L_0x01ba
        L_0x01b9:
            r6 = r14
        L_0x01ba:
            java.lang.String r7 = "album"
            long r8 = android.content.ContentUris.parseId(r13)     // Catch:{ Exception -> 0x022c }
            r5 = r12
            android.os.ParcelFileDescriptor r13 = r5.getCategoryImage(r6, r7, r8, r10, r11)     // Catch:{ Exception -> 0x022c }
            goto L_0x0219
        L_0x01c6:
            java.lang.String r14 = "remote"
            boolean r14 = r2.equals(r14)     // Catch:{ Exception -> 0x022c }
            if (r14 == 0) goto L_0x021a
            android.content.Context r14 = r12.ctx     // Catch:{ Exception -> 0x022c }
            if (r14 != 0) goto L_0x01d6
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r5)     // Catch:{ Exception -> 0x022c }
            goto L_0x01d7
        L_0x01d6:
            r4 = r14
        L_0x01d7:
            java.lang.String r14 = "path"
            java.lang.String r13 = r13.getQueryParameter(r14)     // Catch:{ Exception -> 0x022c }
            android.os.ParcelFileDescriptor r13 = r12.getRemoteImage(r4, r13)     // Catch:{ Exception -> 0x022c }
            goto L_0x0219
        L_0x01e2:
            java.lang.String r14 = "artist"
            boolean r14 = r2.equals(r14)     // Catch:{ Exception -> 0x022c }
            if (r14 == 0) goto L_0x021a
            android.content.Context r14 = r12.ctx     // Catch:{ Exception -> 0x022c }
            if (r14 != 0) goto L_0x01f3
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r5)     // Catch:{ Exception -> 0x022c }
            r6 = r4
            goto L_0x01f4
        L_0x01f3:
            r6 = r14
        L_0x01f4:
            java.lang.String r7 = "artist"
            long r8 = android.content.ContentUris.parseId(r13)     // Catch:{ Exception -> 0x022c }
            r5 = r12
            android.os.ParcelFileDescriptor r13 = r5.getCategoryImage(r6, r7, r8, r10, r11)     // Catch:{ Exception -> 0x022c }
            goto L_0x0219
        L_0x0200:
            java.lang.String r14 = "last_added"
            boolean r14 = r2.equals(r14)     // Catch:{ Exception -> 0x022c }
            if (r14 == 0) goto L_0x021a
            android.content.Context r13 = r12.ctx     // Catch:{ Exception -> 0x022c }
            if (r13 != 0) goto L_0x0210
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r5)     // Catch:{ Exception -> 0x022c }
            goto L_0x0211
        L_0x0210:
            r4 = r13
        L_0x0211:
            byte[] r13 = r12.getLastAdded(r4)     // Catch:{ Exception -> 0x022c }
            android.os.ParcelFileDescriptor r13 = r12.getPFDFromByteArray(r13)     // Catch:{ Exception -> 0x022c }
        L_0x0219:
            return r13
        L_0x021a:
            java.io.FileNotFoundException r14 = new java.io.FileNotFoundException     // Catch:{ Exception -> 0x022c }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x022c }
            r0.<init>(r1)     // Catch:{ Exception -> 0x022c }
            r0.append(r13)     // Catch:{ Exception -> 0x022c }
            java.lang.String r13 = r0.toString()     // Catch:{ Exception -> 0x022c }
            r14.<init>(r13)     // Catch:{ Exception -> 0x022c }
            throw r14     // Catch:{ Exception -> 0x022c }
        L_0x022c:
            r13 = move-exception
            java.io.FileNotFoundException r14 = new java.io.FileNotFoundException
            java.lang.String r13 = r13.getMessage()
            r14.<init>(r13)
            throw r14
        L_0x0237:
            java.io.FileNotFoundException r13 = new java.io.FileNotFoundException
            java.lang.String r14 = "Path is empty"
            r13.<init>(r14)
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.ArtworkProvider.openFile(android.net.Uri, java.lang.String):android.os.ParcelFileDescriptor");
    }

    private final ParcelFileDescriptor getRemoteImage(Context context, String str) {
        if (str == null) {
            return null;
        }
        return getPFDFromByteArray(Companion.getOrPutImage(str, new ArtworkProvider$getRemoteImage$image$1(str, 512, this, context)));
    }

    static /* synthetic */ ParcelFileDescriptor getCategoryImage$default(ArtworkProvider artworkProvider, Context context, String str, long j, boolean z, boolean z2, int i, Object obj) {
        return artworkProvider.getCategoryImage(context, str, j, (i & 8) != 0 ? false : z, (i & 16) != 0 ? true : z2);
    }

    private final ParcelFileDescriptor getCategoryImage(Context context, String str, long j, boolean z, boolean z2) {
        int i;
        MediaLibraryItem mediaLibraryItem = (MediaLibraryItem) BuildersKt.runBlocking(Dispatchers.getIO(), new ArtworkProvider$getCategoryImage$mw$1(str, context, j, (Continuation<? super ArtworkProvider$getCategoryImage$mw$1>) null));
        if (mediaLibraryItem != null) {
            CharSequence artworkMrl = mediaLibraryItem.getArtworkMrl();
            if (!(artworkMrl == null || artworkMrl.length() == 0)) {
                String decode = Uri.decode(mediaLibraryItem.getArtworkMrl());
                Intrinsics.checkNotNullExpressionValue(decode, "decode(...)");
                File file = new File(Strings.removeFileScheme(decode));
                if (file.exists()) {
                    ParcelFileDescriptor open = ParcelFileDescriptor.open(file, 268435456);
                    Intrinsics.checkNotNullExpressionValue(open, "open(...)");
                    return open;
                }
            }
            byte[] bArr = (byte[]) BuildersKt.runBlocking(Dispatchers.getIO(), new ArtworkProvider$getCategoryImage$1$1(mediaLibraryItem, this, (Continuation<? super ArtworkProvider$getCategoryImage$1$1>) null));
            if (bArr != null) {
                return getPFDFromByteArray(bArr);
            }
        }
        if (Intrinsics.areEqual((Object) str, (Object) ALBUM)) {
            i = z ? z2 ? R.drawable.ic_remote_album_unknown_big : R.drawable.ic_remote_album_unknown : R.drawable.ic_auto_album_unknown;
        } else if (Intrinsics.areEqual((Object) str, (Object) ARTIST)) {
            i = z ? z2 ? R.drawable.ic_remote_artist_unknown_big : R.drawable.ic_remote_artist_unknown : R.drawable.ic_auto_artist_unknown;
        } else {
            i = R.drawable.ic_auto_nothumb;
        }
        return getPFDFromBitmap(BitmapUtilKt.getBitmapFromDrawable$default(context, i, 0, 0, 6, (Object) null));
    }

    static /* synthetic */ ParcelFileDescriptor getMediaImage$default(ArtworkProvider artworkProvider, Context context, long j, boolean z, Integer num, boolean z2, int i, Object obj) {
        boolean z3 = (i & 4) != 0 ? true : z;
        if ((i & 8) != 0) {
            num = null;
        }
        return artworkProvider.getMediaImage(context, j, z3, num, (i & 16) != 0 ? false : z2);
    }

    private final ParcelFileDescriptor getMediaImage(Context context, long j, boolean z, Integer num, boolean z2) {
        CharSequence artworkMrl;
        Integer num2 = num;
        String str = null;
        MediaLibraryItem mediaLibraryItem = (MediaLibraryItem) BuildersKt.runBlocking(Dispatchers.getIO(), new ArtworkProvider$getMediaImage$mw$1(context, j, (Continuation<? super ArtworkProvider$getMediaImage$mw$1>) null));
        if (!(mediaLibraryItem == null || (artworkMrl = mediaLibraryItem.getArtworkMrl()) == null || artworkMrl.length() == 0)) {
            String decode = Uri.decode(mediaLibraryItem.getArtworkMrl());
            Intrinsics.checkNotNullExpressionValue(decode, "decode(...)");
            String removeFileScheme = Strings.removeFileScheme(decode);
            File file = new File(removeFileScheme);
            if (file.canRead() && isImageWithinBounds(removeFileScheme)) {
                ParcelFileDescriptor open = ParcelFileDescriptor.open(file, 268435456);
                Intrinsics.checkNotNullExpressionValue(open, "open(...)");
                return open;
            }
        }
        if (mediaLibraryItem != null) {
            str = mediaLibraryItem.getArtworkMrl();
        }
        if (str == null) {
            str = String.valueOf(j);
        }
        boolean z3 = Build.VERSION.SDK_INT == 33 && Intrinsics.areEqual((Object) "com.android.systemui", (Object) getCallingPackage());
        if (z3) {
            str = str + "_nonTransparent";
        }
        if (num2 != null) {
            str = str + num2;
        }
        return getPFDFromByteArray(Companion.getOrPutImage(str, new ArtworkProvider$getMediaImage$image$1(mediaLibraryItem, 512, this, z, context, num, z2, 960, 540, z3)));
    }

    static /* synthetic */ ParcelFileDescriptor getGenreImage$default(ArtworkProvider artworkProvider, Context context, long j, Integer num, int i, Object obj) {
        if ((i & 4) != 0) {
            num = null;
        }
        return artworkProvider.getGenreImage(context, j, num);
    }

    private final ParcelFileDescriptor getGenreImage(Context context, long j, Integer num) {
        return getPFDFromBitmap((Bitmap) BuildersKt.runBlocking(Dispatchers.getIO(), new ArtworkProvider$getGenreImage$bitmap$1(context, num, j, (Continuation<? super ArtworkProvider$getGenreImage$bitmap$1>) null)));
    }

    static /* synthetic */ ParcelFileDescriptor getPlaylistImage$default(ArtworkProvider artworkProvider, Context context, long j, Integer num, int i, Object obj) {
        if ((i & 4) != 0) {
            num = null;
        }
        return artworkProvider.getPlaylistImage(context, j, num);
    }

    private final ParcelFileDescriptor getPlaylistImage(Context context, long j, Integer num) {
        return getPFDFromBitmap((Bitmap) BuildersKt.runBlocking(Dispatchers.getIO(), new ArtworkProvider$getPlaylistImage$bitmap$1(context, num, j, (Continuation<? super ArtworkProvider$getPlaylistImage$bitmap$1>) null)));
    }

    private final ParcelFileDescriptor getPlayAllImage(Context context, String str, long j, boolean z) {
        return getPFDFromBitmap((Bitmap) BuildersKt.runBlocking(Dispatchers.getIO(), new ArtworkProvider$getPlayAllImage$bitmap$1(str, context, j, z, (Continuation<? super ArtworkProvider$getPlayAllImage$bitmap$1>) null)));
    }

    private final byte[] getHistory(Context context) {
        return (byte[]) BuildersKt.runBlocking(Dispatchers.getIO(), new ArtworkProvider$getHistory$1(context, this, (Continuation<? super ArtworkProvider$getHistory$1>) null));
    }

    private final byte[] getShuffleAll(Context context) {
        return (byte[]) BuildersKt.runBlocking(Dispatchers.getIO(), new ArtworkProvider$getShuffleAll$1(context, this, (Continuation<? super ArtworkProvider$getShuffleAll$1>) null));
    }

    private final byte[] getLastAdded(Context context) {
        return (byte[]) BuildersKt.runBlocking(Dispatchers.getIO(), new ArtworkProvider$getLastAdded$1(context, this, (Continuation<? super ArtworkProvider$getLastAdded$1>) null));
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0044  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x012e A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x012f  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0143  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object getHomeImage(android.content.Context r9, java.lang.String r10, org.videolan.medialibrary.interfaces.media.MediaWrapper[] r11, kotlin.coroutines.Continuation<? super byte[]> r12) {
        /*
            r8 = this;
            boolean r0 = r12 instanceof org.videolan.vlc.ArtworkProvider$getHomeImage$1
            if (r0 == 0) goto L_0x0014
            r0 = r12
            org.videolan.vlc.ArtworkProvider$getHomeImage$1 r0 = (org.videolan.vlc.ArtworkProvider$getHomeImage$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r12 = r0.label
            int r12 = r12 - r2
            r0.label = r12
            goto L_0x0019
        L_0x0014:
            org.videolan.vlc.ArtworkProvider$getHomeImage$1 r0 = new org.videolan.vlc.ArtworkProvider$getHomeImage$1
            r0.<init>(r8, r12)
        L_0x0019:
            r6 = r0
            java.lang.Object r12 = r6.result
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r6.label
            r2 = 1
            if (r1 == 0) goto L_0x0044
            if (r1 != r2) goto L_0x003c
            java.lang.Object r9 = r6.L$3
            kotlin.jvm.internal.Ref$ObjectRef r9 = (kotlin.jvm.internal.Ref.ObjectRef) r9
            java.lang.Object r10 = r6.L$2
            kotlin.jvm.internal.Ref$ObjectRef r10 = (kotlin.jvm.internal.Ref.ObjectRef) r10
            java.lang.Object r11 = r6.L$1
            android.content.Context r11 = (android.content.Context) r11
            java.lang.Object r0 = r6.L$0
            org.videolan.vlc.ArtworkProvider r0 = (org.videolan.vlc.ArtworkProvider) r0
            kotlin.ResultKt.throwOnFailure(r12)
            goto L_0x0134
        L_0x003c:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L_0x0044:
            kotlin.ResultKt.throwOnFailure(r12)
            kotlin.jvm.internal.Ref$ObjectRef r12 = new kotlin.jvm.internal.Ref$ObjectRef
            r12.<init>()
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            if (r11 == 0) goto L_0x0139
            int r3 = r11.length
            r4 = 50
            int r3 = kotlin.ranges.RangesKt.coerceAtMost((int) r3, (int) r4)
            r1.ensureCapacity(r3)
            java.util.Iterator r11 = kotlin.jvm.internal.ArrayIteratorKt.iterator(r11)
        L_0x0061:
            boolean r3 = r11.hasNext()
            if (r3 == 0) goto L_0x0085
            java.lang.Object r3 = r11.next()
            org.videolan.medialibrary.interfaces.media.MediaWrapper r3 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r3
            int r5 = r3.getItemType()
            r7 = 32
            if (r5 != r7) goto L_0x007c
            int r5 = r3.getType()
            if (r5 == r2) goto L_0x007c
            goto L_0x0061
        L_0x007c:
            r1.add(r3)
            int r3 = r1.size()
            if (r3 != r4) goto L_0x0061
        L_0x0085:
            r11 = r1
            java.lang.Iterable r11 = (java.lang.Iterable) r11
            boolean r3 = r11 instanceof java.util.Collection
            if (r3 == 0) goto L_0x0097
            r3 = r11
            java.util.Collection r3 = (java.util.Collection) r3
            boolean r3 = r3.isEmpty()
            if (r3 == 0) goto L_0x0097
            goto L_0x0139
        L_0x0097:
            java.util.Iterator r11 = r11.iterator()
        L_0x009b:
            boolean r3 = r11.hasNext()
            if (r3 == 0) goto L_0x0139
            java.lang.Object r3 = r11.next()
            org.videolan.medialibrary.interfaces.media.MediaWrapper r3 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r3
            java.lang.String r4 = r3.getArtworkMrl()
            if (r4 == 0) goto L_0x009b
            java.lang.String r3 = r3.getArtworkMrl()
            java.lang.String r4 = "getArtworkMrl(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r4)
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3
            int r3 = r3.length()
            if (r3 <= 0) goto L_0x009b
            int r11 = r10.hashCode()
            r3 = -2029646313(0xffffffff87060e17, float:-1.008518E-34)
            if (r11 == r3) goto L_0x00f2
            r3 = 424921947(0x1953cb5b, float:1.0949512E-23)
            if (r11 == r3) goto L_0x00e2
            r3 = 926934164(0x373fe494, float:1.1437707E-5)
            if (r11 == r3) goto L_0x00d2
            goto L_0x00fa
        L_0x00d2:
            java.lang.String r11 = "history"
            boolean r11 = r10.equals(r11)
            if (r11 != 0) goto L_0x00db
            goto L_0x00fa
        L_0x00db:
            int r11 = org.videolan.vlc.R.drawable.ic_auto_history_circle
            android.graphics.Bitmap r11 = org.videolan.vlc.gui.helpers.ImageLoaderKt.getBitmapFromDrawable(r9, r11)
            goto L_0x00fb
        L_0x00e2:
            java.lang.String r11 = "shuffle_all"
            boolean r11 = r10.equals(r11)
            if (r11 != 0) goto L_0x00eb
            goto L_0x00fa
        L_0x00eb:
            int r11 = org.videolan.vlc.R.drawable.ic_auto_shuffle_circle
            android.graphics.Bitmap r11 = org.videolan.vlc.gui.helpers.ImageLoaderKt.getBitmapFromDrawable(r9, r11)
            goto L_0x00fb
        L_0x00f2:
            java.lang.String r11 = "last_added"
            boolean r11 = r10.equals(r11)
            if (r11 != 0) goto L_0x00fd
        L_0x00fa:
            r11 = 0
        L_0x00fb:
            r5 = r11
            goto L_0x0104
        L_0x00fd:
            int r11 = org.videolan.vlc.R.drawable.ic_auto_new_circle
            android.graphics.Bitmap r11 = org.videolan.vlc.gui.helpers.ImageLoaderKt.getBitmapFromDrawable(r9, r11)
            goto L_0x00fb
        L_0x0104:
            org.videolan.vlc.util.ThumbnailsProvider r11 = org.videolan.vlc.util.ThumbnailsProvider.INSTANCE
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r10)
            java.lang.String r10 = "_256"
            r3.append(r10)
            java.lang.String r10 = r3.toString()
            r3 = r1
            java.util.List r3 = (java.util.List) r3
            r6.L$0 = r8
            r6.L$1 = r9
            r6.L$2 = r12
            r6.L$3 = r12
            r6.label = r2
            r4 = 256(0x100, float:3.59E-43)
            r1 = r11
            r2 = r10
            java.lang.Object r10 = r1.getPlaylistOrGenreImage(r2, r3, r4, r5, r6)
            if (r10 != r0) goto L_0x012f
            return r0
        L_0x012f:
            r0 = r8
            r11 = r9
            r9 = r12
            r12 = r10
            r10 = r9
        L_0x0134:
            r9.element = r12
            r12 = r10
            r9 = r11
            goto L_0x013a
        L_0x0139:
            r0 = r8
        L_0x013a:
            r1 = r9
            org.videolan.vlc.gui.helpers.BitmapUtil r9 = org.videolan.vlc.gui.helpers.BitmapUtil.INSTANCE
            T r10 = r12.element
            android.graphics.Bitmap r10 = (android.graphics.Bitmap) r10
            if (r10 != 0) goto L_0x014d
            int r2 = org.videolan.vlc.R.drawable.ic_auto_playall
            r5 = 6
            r6 = 0
            r3 = 0
            r4 = 0
            android.graphics.Bitmap r10 = org.videolan.vlc.gui.helpers.BitmapUtilKt.getBitmapFromDrawable$default(r1, r2, r3, r4, r5, r6)
        L_0x014d:
            org.videolan.vlc.ArtworkProvider$getHomeImage$3 r11 = new org.videolan.vlc.ArtworkProvider$getHomeImage$3
            r11.<init>(r0)
            kotlin.jvm.functions.Function0 r11 = (kotlin.jvm.functions.Function0) r11
            r12 = 0
            byte[] r9 = r9.encodeImage(r10, r12, r11)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.ArtworkProvider.getHomeImage(android.content.Context, java.lang.String, org.videolan.medialibrary.interfaces.media.MediaWrapper[], kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final boolean isImageWithinBounds(String str) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        int i = options.outWidth;
        int i2 = options.outHeight;
        if (i == -1 || i2 == -1 || i > 2000 || i2 > 2000 || i != i2) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public final Bitmap padSquare(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width == height) {
            return bitmap;
        }
        int max = Math.max(width, height);
        float coerceAtLeast = RangesKt.coerceAtLeast(((float) (height - width)) / 2.0f, 0.0f);
        float coerceAtLeast2 = RangesKt.coerceAtLeast(((float) (width - height)) / 2.0f, 0.0f);
        Bitmap createBitmap = Bitmap.createBitmap(max, max, Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(...)");
        new Canvas(createBitmap).drawBitmap(bitmap, coerceAtLeast, coerceAtLeast2, (Paint) null);
        return createBitmap;
    }

    /* access modifiers changed from: private */
    public final Bitmap removeTransparency(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(...)");
        createBitmap.eraseColor(ViewCompat.MEASURED_STATE_MASK);
        new Canvas(createBitmap).drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
        return createBitmap;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x005f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.graphics.Bitmap readEmbeddedArtwork(org.videolan.medialibrary.media.MediaLibraryItem r5, int r6) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof org.videolan.medialibrary.interfaces.media.MediaWrapper
            r1 = 0
            if (r0 == 0) goto L_0x0063
            r0 = r5
            org.videolan.medialibrary.interfaces.media.MediaWrapper r0 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r0
            java.lang.String r2 = r0.getArtworkMrl()
            if (r2 != 0) goto L_0x0063
            android.net.Uri r0 = r0.getUri()
            if (r0 == 0) goto L_0x0063
            org.videolan.resources.VLCInstance r0 = org.videolan.resources.VLCInstance.INSTANCE     // Catch:{ all -> 0x005c }
            android.content.Context r2 = r4.ctx     // Catch:{ all -> 0x005c }
            if (r2 != 0) goto L_0x0020
            java.lang.String r2 = "ctx"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)     // Catch:{ all -> 0x005c }
            r2 = r1
        L_0x0020:
            java.lang.Object r0 = r0.getInstance(r2)     // Catch:{ all -> 0x005c }
            org.videolan.libvlc.interfaces.ILibVLC r0 = (org.videolan.libvlc.interfaces.ILibVLC) r0     // Catch:{ all -> 0x005c }
            java.lang.String r2 = org.videolan.libvlc.interfaces.IMediaFactory.factoryId     // Catch:{ all -> 0x005c }
            org.videolan.libvlc.interfaces.IComponentFactory r2 = org.videolan.libvlc.FactoryManager.getFactory(r2)     // Catch:{ all -> 0x005c }
            java.lang.String r3 = "null cannot be cast to non-null type org.videolan.libvlc.interfaces.IMediaFactory"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2, r3)     // Catch:{ all -> 0x005c }
            org.videolan.libvlc.interfaces.IMediaFactory r2 = (org.videolan.libvlc.interfaces.IMediaFactory) r2     // Catch:{ all -> 0x005c }
            org.videolan.medialibrary.interfaces.media.MediaWrapper r5 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r5     // Catch:{ all -> 0x005c }
            android.net.Uri r5 = r5.getUri()     // Catch:{ all -> 0x005c }
            org.videolan.libvlc.interfaces.IMedia r5 = r2.getFromUri(r0, r5)     // Catch:{ all -> 0x005c }
            r5.parse()     // Catch:{ all -> 0x005c }
            org.videolan.vlc.gui.helpers.AudioUtil r0 = org.videolan.vlc.gui.helpers.AudioUtil.INSTANCE     // Catch:{ all -> 0x0058 }
            org.videolan.medialibrary.interfaces.media.MediaWrapper r1 = org.videolan.medialibrary.MLServiceLocator.getAbstractMediaWrapper((org.videolan.libvlc.interfaces.IMedia) r5)     // Catch:{ all -> 0x0058 }
            java.lang.String r1 = r1.getArtworkMrl()     // Catch:{ all -> 0x0058 }
            java.lang.String r1 = android.net.Uri.decode(r1)     // Catch:{ all -> 0x0058 }
            android.graphics.Bitmap r6 = r0.readCoverBitmap(r1, r6)     // Catch:{ all -> 0x0058 }
            if (r5 == 0) goto L_0x0057
            r5.release()
        L_0x0057:
            return r6
        L_0x0058:
            r6 = move-exception
            r1 = r5
            r5 = r6
            goto L_0x005d
        L_0x005c:
            r5 = move-exception
        L_0x005d:
            if (r1 == 0) goto L_0x0062
            r1.release()
        L_0x0062:
            throw r5
        L_0x0063:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.ArtworkProvider.readEmbeddedArtwork(org.videolan.medialibrary.media.MediaLibraryItem, int):android.graphics.Bitmap");
    }

    private final ParcelFileDescriptor getPFDFromBitmap(Bitmap bitmap) {
        ParcelFileDescriptor openPipeHelper = super.openPipeHelper(Uri.EMPTY, "image/webp", (Bundle) null, bitmap, new ArtworkProvider$$ExternalSyntheticLambda1(this));
        Intrinsics.checkNotNullExpressionValue(openPipeHelper, "openPipeHelper(...)");
        return openPipeHelper;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0034, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        kotlin.io.CloseableKt.closeFinally(r2, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0038, code lost:
        throw r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void getPFDFromBitmap$lambda$8(org.videolan.vlc.ArtworkProvider r0, android.os.ParcelFileDescriptor r1, android.net.Uri r2, java.lang.String r3, android.os.Bundle r4, android.graphics.Bitmap r5) {
        /*
            java.lang.String r4 = "this$0"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r4)
            java.lang.String r4 = "pfd"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r4)
            java.lang.String r4 = "<anonymous parameter 1>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r4)
            java.lang.String r2 = "<anonymous parameter 2>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r2)
            if (r5 == 0) goto L_0x003f
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x0039 }
            java.io.FileDescriptor r1 = r1.getFileDescriptor()     // Catch:{ IOException -> 0x0039 }
            r2.<init>(r1)     // Catch:{ IOException -> 0x0039 }
            java.io.Closeable r2 = (java.io.Closeable) r2     // Catch:{ IOException -> 0x0039 }
            r1 = r2
            java.io.FileOutputStream r1 = (java.io.FileOutputStream) r1     // Catch:{ all -> 0x0032 }
            android.graphics.Bitmap$CompressFormat r3 = android.graphics.Bitmap.CompressFormat.WEBP     // Catch:{ all -> 0x0032 }
            java.io.OutputStream r1 = (java.io.OutputStream) r1     // Catch:{ all -> 0x0032 }
            r4 = 100
            r5.compress(r3, r4, r1)     // Catch:{ all -> 0x0032 }
            r1 = 0
            kotlin.io.CloseableKt.closeFinally(r2, r1)     // Catch:{ IOException -> 0x0039 }
            goto L_0x003f
        L_0x0032:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0034 }
        L_0x0034:
            r3 = move-exception
            kotlin.io.CloseableKt.closeFinally(r2, r1)     // Catch:{ IOException -> 0x0039 }
            throw r3     // Catch:{ IOException -> 0x0039 }
        L_0x0039:
            r1 = move-exception
            java.lang.Exception r1 = (java.lang.Exception) r1
            r0.logError(r1)
        L_0x003f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.ArtworkProvider.getPFDFromBitmap$lambda$8(org.videolan.vlc.ArtworkProvider, android.os.ParcelFileDescriptor, android.net.Uri, java.lang.String, android.os.Bundle, android.graphics.Bitmap):void");
    }

    private final ParcelFileDescriptor getPFDFromByteArray(byte[] bArr) {
        ParcelFileDescriptor openPipeHelper = super.openPipeHelper(Uri.EMPTY, "image/webp", (Bundle) null, bArr, new ArtworkProvider$$ExternalSyntheticLambda2(this));
        Intrinsics.checkNotNullExpressionValue(openPipeHelper, "openPipeHelper(...)");
        return openPipeHelper;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0030, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        kotlin.io.CloseableKt.closeFinally(r2, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0034, code lost:
        throw r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void getPFDFromByteArray$lambda$11(org.videolan.vlc.ArtworkProvider r0, android.os.ParcelFileDescriptor r1, android.net.Uri r2, java.lang.String r3, android.os.Bundle r4, byte[] r5) {
        /*
            java.lang.String r4 = "this$0"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r4)
            java.lang.String r4 = "pfd"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r4)
            java.lang.String r4 = "<anonymous parameter 1>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r4)
            java.lang.String r2 = "<anonymous parameter 2>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r2)
            if (r5 == 0) goto L_0x003b
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x0035 }
            java.io.FileDescriptor r1 = r1.getFileDescriptor()     // Catch:{ IOException -> 0x0035 }
            r2.<init>(r1)     // Catch:{ IOException -> 0x0035 }
            java.io.Closeable r2 = (java.io.Closeable) r2     // Catch:{ IOException -> 0x0035 }
            r1 = r2
            java.io.FileOutputStream r1 = (java.io.FileOutputStream) r1     // Catch:{ all -> 0x002e }
            r1.write(r5)     // Catch:{ all -> 0x002e }
            kotlin.Unit r1 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x002e }
            r1 = 0
            kotlin.io.CloseableKt.closeFinally(r2, r1)     // Catch:{ IOException -> 0x0035 }
            goto L_0x003b
        L_0x002e:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0030 }
        L_0x0030:
            r3 = move-exception
            kotlin.io.CloseableKt.closeFinally(r2, r1)     // Catch:{ IOException -> 0x0035 }
            throw r3     // Catch:{ IOException -> 0x0035 }
        L_0x0035:
            r1 = move-exception
            java.lang.Exception r1 = (java.lang.Exception) r1
            r0.logError(r1)
        L_0x003b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.ArtworkProvider.getPFDFromByteArray$lambda$11(org.videolan.vlc.ArtworkProvider, android.os.ParcelFileDescriptor, android.net.Uri, java.lang.String, android.os.Bundle, byte[]):void");
    }

    private final void logError(Exception exc) {
        if (Build.VERSION.SDK_INT < 19) {
            Log.e("VLC/ArtworkProvider", "Could not transfer cover art", exc);
            return;
        }
        Log.e("VLC/ArtworkProvider", "Could not transfer cover art to caller: " + getCallingPackage(), exc);
    }

    private final ArtworkProvider$dateFormatter$2.AnonymousClass1 getDateFormatter() {
        return (ArtworkProvider$dateFormatter$2.AnonymousClass1) this.dateFormatter$delegate.getValue();
    }

    /* access modifiers changed from: private */
    public final String getTimestamp() {
        SimpleDateFormat simpleDateFormat = (SimpleDateFormat) getDateFormatter().get();
        if (simpleDateFormat != null) {
            return simpleDateFormat.format(Long.valueOf(System.currentTimeMillis()));
        }
        return null;
    }

    public boolean onCreate() {
        Context context = getContext();
        Intrinsics.checkNotNull(context);
        this.ctx = context;
        return true;
    }

    public String getType(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, Constants.KEY_URI);
        return "image/webp";
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        Intrinsics.checkNotNullParameter(uri, Constants.KEY_URI);
        Uri uri2 = Uri.EMPTY;
        Intrinsics.checkNotNull(uri2);
        return uri2;
    }

    @Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bJ\u0018\u0010\u001c\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u001d\u001a\u0004\u0018\u00010\u0017J\u0006\u0010\u001e\u001a\u00020\u001fJ\u001e\u0010 \u001a\u00020!2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u001b0#2\b\b\u0002\u0010$\u001a\u00020%J\u0010\u0010&\u001a\u00020\u00042\b\b\u0002\u0010'\u001a\u00020%J \u0010(\u001a\u0004\u0018\u00010\u00152\u0006\u0010)\u001a\u00020\u00042\u000e\u0010*\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00150+R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u001a\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00150\u0014X\u0004¢\u0006\u0002\n\u0000¨\u0006,"}, d2 = {"Lorg/videolan/vlc/ArtworkProvider$Companion;", "", "()V", "ALBUM", "", "ARTIST", "BIG_VARIANT", "GENRE", "HISTORY", "LAST_ADDED", "MEDIA", "PATH", "PLAYLIST", "PLAY_ALL", "REMOTE", "REMOTE_ACCESS", "SHUFFLE", "SHUFFLE_ALL", "VIDEO", "memCache", "Landroid/util/LruCache;", "", "buildMediaUri", "Landroid/net/Uri;", "ctx", "Landroid/content/Context;", "media", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "buildUri", "path", "clear", "", "computeChecksum", "", "list", "", "detectReordering", "", "computeExpiration", "halfDayExpiration", "getOrPutImage", "key", "defaultValue", "Lkotlin/Function0;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: ArtworkProvider.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final synchronized void clear() {
            ArtworkProvider.memCache.evictAll();
        }

        public final synchronized byte[] getOrPutImage(String str, Function0<byte[]> function0) {
            byte[] bArr;
            Intrinsics.checkNotNullParameter(str, LeanbackPreferenceDialogFragment.ARG_KEY);
            Intrinsics.checkNotNullParameter(function0, "defaultValue");
            bArr = (byte[]) ArtworkProvider.memCache.get(str);
            if (bArr == null) {
                bArr = function0.invoke();
                if (bArr != null) {
                    ArtworkProvider.memCache.put(str, bArr);
                } else {
                    bArr = null;
                }
            }
            return bArr;
        }

        public static /* synthetic */ String computeExpiration$default(Companion companion, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                z = true;
            }
            return companion.computeExpiration(z);
        }

        public final String computeExpiration(boolean z) {
            Calendar instance = Calendar.getInstance();
            if (z) {
                instance.set(11, instance.get(11) < 12 ? 0 : 12);
                instance.set(12, 0);
                instance.set(13, 0);
                instance.set(14, 0);
            }
            return String.valueOf(instance.getTimeInMillis());
        }

        public final Uri buildUri(Context context, Uri uri) {
            Set<String> queryParameterNames;
            List<String> pathSegments;
            Intrinsics.checkNotNullParameter(context, "ctx");
            Uri.Builder scheme = new Uri.Builder().scheme("content");
            Uri.Builder authority = scheme.authority(context.getPackageName() + ".artwork");
            if (!(uri == null || (pathSegments = uri.getPathSegments()) == null)) {
                for (String str : pathSegments) {
                    if (str != null) {
                        Intrinsics.checkNotNull(str);
                        authority.appendPath(str);
                    }
                }
            }
            if (!(uri == null || (queryParameterNames = uri.getQueryParameterNames()) == null)) {
                for (String str2 : queryParameterNames) {
                    if (str2 != null) {
                        Intrinsics.checkNotNull(str2);
                        authority.appendQueryParameter(str2, uri.getQueryParameter(str2));
                    }
                }
            }
            Uri build = authority.build();
            Intrinsics.checkNotNull(build);
            return build;
        }

        public final Uri buildMediaUri(Context context, MediaWrapper mediaWrapper) {
            long j;
            CharSequence artworkMrl;
            Intrinsics.checkNotNullParameter(context, "ctx");
            Intrinsics.checkNotNullParameter(mediaWrapper, "media");
            boolean z = true;
            if (!(mediaWrapper.getType() == 1 && ((artworkMrl = mediaWrapper.getArtworkMrl()) == null || artworkMrl.length() == 0))) {
                z = false;
            }
            Uri.Builder appendPath = new Uri.Builder().appendPath("media");
            long j2 = 0;
            if (z) {
                j = 0;
            } else {
                j = mediaWrapper.getLastModified();
            }
            Uri.Builder appendPath2 = appendPath.appendPath(String.valueOf(j));
            if (!z) {
                j2 = mediaWrapper.getId();
            }
            return buildUri(context, appendPath2.appendPath(String.valueOf(j2)).build());
        }

        public static /* synthetic */ long computeChecksum$default(Companion companion, List list, boolean z, int i, Object obj) {
            if ((i & 2) != 0) {
                z = false;
            }
            return companion.computeChecksum(list, z);
        }

        public final long computeChecksum(List<? extends MediaWrapper> list, boolean z) {
            Intrinsics.checkNotNullParameter(list, "list");
            if (z) {
                CRC32 crc32 = new CRC32();
                ByteBuffer allocate = ByteBuffer.allocate(8);
                for (MediaWrapper lastModified : list) {
                    allocate.putLong(lastModified.getLastModified());
                    crc32.update(allocate.array());
                    allocate.clear();
                }
                return crc32.getValue();
            }
            long j = 0;
            for (MediaWrapper lastModified2 : list) {
                j ^= lastModified2.getLastModified();
            }
            return j;
        }
    }
}
