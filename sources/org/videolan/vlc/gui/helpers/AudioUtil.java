package org.videolan.vlc.gui.helpers;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.util.Log;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwnerKt;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;
import org.videolan.libvlc.util.AndroidUtil;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.tools.BitmapCache;
import org.videolan.tools.CloseableUtils;
import org.videolan.tools.Strings;
import org.videolan.vlc.ArtworkProvider;
import org.videolan.vlc.R;
import org.videolan.vlc.util.BrowserutilsKt;
import org.videolan.vlc.util.Permissions;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\u0004J\u001a\u0010\n\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\rH\u0007J\u001a\u0010\u000e\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u00062\b\u0010\u000b\u001a\u0004\u0018\u00010\u00042\u0006\u0010\f\u001a\u00020\rH\u0007J\u001a\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u00062\u0006\u0010\t\u001a\u00020\u0004H\u0002J\u0012\u0010\u0015\u001a\u00020\u0013*\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0010R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lorg/videolan/vlc/gui/helpers/AudioUtil;", "", "()V", "TAG", "", "fetchBitmapFromContentResolver", "Landroid/graphics/Bitmap;", "context", "Landroid/content/Context;", "path", "fetchCoverBitmap", "requestedPath", "width", "", "getCoverFromMediaStore", "media", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "readCoverBitmap", "writeBitmap", "", "bitmap", "setRingtone", "Landroidx/fragment/app/FragmentActivity;", "song", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: AudioUtil.kt */
public final class AudioUtil {
    public static final AudioUtil INSTANCE = new AudioUtil();
    public static final String TAG = "VLC/AudioUtil";

    private AudioUtil() {
    }

    public final void setRingtone(FragmentActivity fragmentActivity, MediaWrapper mediaWrapper) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "<this>");
        Intrinsics.checkNotNullParameter(mediaWrapper, "song");
        if (AndroidUtil.isOOrLater && !Permissions.INSTANCE.canWriteStorage(fragmentActivity)) {
            Permissions.INSTANCE.askWriteStoragePermission(fragmentActivity, false, new AudioUtil$$ExternalSyntheticLambda0(fragmentActivity, mediaWrapper));
        } else if (!Permissions.INSTANCE.canWriteSettings(fragmentActivity)) {
            Permissions.INSTANCE.checkWriteSettingsPermission(fragmentActivity, 42);
        } else {
            String string = fragmentActivity.getString(R.string.set_song_question, new Object[]{mediaWrapper.getTitle()});
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            UiTools.INSTANCE.snackerConfirm(LifecycleOwnerKt.getLifecycleScope(fragmentActivity), fragmentActivity, string, new AudioUtil$setRingtone$2(mediaWrapper, fragmentActivity, (Continuation<? super AudioUtil$setRingtone$2>) null));
        }
    }

    /* access modifiers changed from: private */
    public static final void setRingtone$lambda$0(FragmentActivity fragmentActivity, MediaWrapper mediaWrapper) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "$this_setRingtone");
        Intrinsics.checkNotNullParameter(mediaWrapper, "$song");
        INSTANCE.setRingtone(fragmentActivity, mediaWrapper);
    }

    private final String getCoverFromMediaStore(Context context, MediaWrapper mediaWrapper) {
        Cursor query;
        String album = mediaWrapper.getAlbum();
        if (!(album == null || (query = context.getContentResolver().query(MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI, new String[]{ArtworkProvider.ALBUM, "album_art"}, "album LIKE ?", new String[]{album}, (String) null)) == null)) {
            if (!query.moveToFirst()) {
                query.close();
            } else {
                String string = query.getString(query.getColumnIndex("album_art"));
                query.close();
                return string;
            }
        }
        return null;
    }

    private final void writeBitmap(Bitmap bitmap, String str) throws IOException {
        OutputStream outputStream = null;
        try {
            File file = new File(str);
            if (!file.exists() || file.length() <= 0) {
                OutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file), 4096);
                if (bitmap != null) {
                    try {
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, bufferedOutputStream);
                    } catch (Exception e) {
                        e = e;
                        outputStream = bufferedOutputStream;
                    } catch (Throwable th) {
                        th = th;
                        outputStream = bufferedOutputStream;
                        CloseableUtils.INSTANCE.close(outputStream);
                        throw th;
                    }
                }
                CloseableUtils.INSTANCE.close(bufferedOutputStream);
                return;
            }
            CloseableUtils.INSTANCE.close((Closeable) null);
        } catch (Exception e2) {
            e = e2;
            try {
                Log.e(TAG, "writeBitmap failed : " + e.getMessage());
                CloseableUtils.INSTANCE.close(outputStream);
            } catch (Throwable th2) {
                th = th2;
                CloseableUtils.INSTANCE.close(outputStream);
                throw th;
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0023, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        kotlin.io.CloseableKt.closeFinally(r4, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0027, code lost:
        throw r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.graphics.Bitmap fetchBitmapFromContentResolver(android.content.Context r4, java.lang.String r5) {
        /*
            r3 = this;
            java.lang.String r0 = "context"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            r0 = 0
            android.net.Uri r1 = android.net.Uri.parse(r5)     // Catch:{ Exception -> 0x0028 }
            android.content.ContentResolver r4 = r4.getContentResolver()     // Catch:{ Exception -> 0x0028 }
            java.io.InputStream r4 = r4.openInputStream(r1)     // Catch:{ Exception -> 0x0028 }
            if (r4 == 0) goto L_0x003e
            java.io.Closeable r4 = (java.io.Closeable) r4     // Catch:{ Exception -> 0x0028 }
            r1 = r4
            java.io.InputStream r1 = (java.io.InputStream) r1     // Catch:{ all -> 0x0021 }
            android.graphics.Bitmap r1 = android.graphics.BitmapFactory.decodeStream(r1)     // Catch:{ all -> 0x0021 }
            kotlin.io.CloseableKt.closeFinally(r4, r0)     // Catch:{ Exception -> 0x0028 }
            return r1
        L_0x0021:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0023 }
        L_0x0023:
            r2 = move-exception
            kotlin.io.CloseableKt.closeFinally(r4, r1)     // Catch:{ Exception -> 0x0028 }
            throw r2     // Catch:{ Exception -> 0x0028 }
        L_0x0028:
            r4 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Could not load image from: "
            r1.<init>(r2)
            r1.append(r5)
            java.lang.String r5 = r1.toString()
            java.lang.Throwable r4 = (java.lang.Throwable) r4
            java.lang.String r1 = "VLC/AudioUtil"
            android.util.Log.e(r1, r5, r4)
        L_0x003e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.helpers.AudioUtil.fetchBitmapFromContentResolver(android.content.Context, java.lang.String):android.graphics.Bitmap");
    }

    public final Bitmap readCoverBitmap(String str, int i) {
        if (str == null) {
            return null;
        }
        if (BrowserutilsKt.isSchemeHttpOrHttps(str)) {
            return (Bitmap) BuildersKt.runBlocking(Dispatchers.getMain(), new AudioUtil$readCoverBitmap$1(str, (Continuation<? super AudioUtil$readCoverBitmap$1>) null));
        }
        BitmapCache bitmapCache = BitmapCache.INSTANCE;
        Bitmap bitmapFromMemCache = bitmapCache.getBitmapFromMemCache(Strings.removeFileScheme(str) + '_' + i);
        return bitmapFromMemCache == null ? fetchCoverBitmap(str, i) : bitmapFromMemCache;
    }

    public final Bitmap fetchCoverBitmap(String str, int i) {
        Intrinsics.checkNotNullParameter(str, "requestedPath");
        String removeFileScheme = Strings.removeFileScheme(str);
        if (removeFileScheme.length() == 0 || !new File(removeFileScheme).exists()) {
            return null;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(removeFileScheme, options);
        if (options.outWidth <= 0 || options.outHeight <= 0) {
            return null;
        }
        options.inJustDecodeBounds = false;
        options.inSampleSize = 1;
        if (i > 0) {
            while (options.outWidth / (options.inSampleSize + 1) > i) {
                options.inSampleSize *= 2;
            }
        }
        Bitmap decodeFile = BitmapFactory.decodeFile(removeFileScheme, options);
        BitmapCache.INSTANCE.addBitmapToMemCache(Strings.removeFileScheme(removeFileScheme) + '_' + i, decodeFile);
        return decodeFile;
    }
}
