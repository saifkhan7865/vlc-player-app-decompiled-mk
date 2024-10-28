package org.videolan.tools;

import android.graphics.Bitmap;
import android.util.LruCache;
import androidx.leanback.preference.LeanbackPreferenceDialogFragment;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0007H\u0002J\u001a\u0010\b\u001a\u00020\t2\b\u0010\r\u001a\u0004\u0018\u00010\u00042\b\u0010\f\u001a\u0004\u0018\u00010\u0007J\u0006\u0010\u000e\u001a\u00020\tJ\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u00072\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u00072\b\u0010\r\u001a\u0004\u0018\u00010\u0004R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00070\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lorg/videolan/tools/BitmapCache;", "", "()V", "TAG", "", "memCache", "Landroid/util/LruCache;", "Landroid/graphics/Bitmap;", "addBitmapToMemCache", "", "resId", "", "bitmap", "key", "clear", "getBitmapFromMemCache", "tools_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: BitmapCache.kt */
public final class BitmapCache {
    public static final BitmapCache INSTANCE = new BitmapCache();
    private static final String TAG = "VLC/BitmapCache";
    private static final LruCache<String, Bitmap> memCache = new LruCache<String, Bitmap>((int) (Runtime.getRuntime().maxMemory() / ((long) 5))) {
        /* access modifiers changed from: protected */
        public int sizeOf(String str, Bitmap bitmap) {
            Intrinsics.checkNotNullParameter(str, LeanbackPreferenceDialogFragment.ARG_KEY);
            Intrinsics.checkNotNullParameter(bitmap, "value");
            return bitmap.getRowBytes() * bitmap.getHeight();
        }
    };

    private BitmapCache() {
    }

    public final synchronized Bitmap getBitmapFromMemCache(String str) {
        if (str == null) {
            return null;
        }
        LruCache<String, Bitmap> lruCache = memCache;
        Bitmap bitmap = lruCache.get(str);
        if (bitmap != null) {
            return bitmap;
        }
        lruCache.remove(str);
        return null;
    }

    public final synchronized void addBitmapToMemCache(String str, Bitmap bitmap) {
        if (!(str == null || bitmap == null)) {
            if (getBitmapFromMemCache(str) == null) {
                memCache.put(str, bitmap);
            }
        }
    }

    private final Bitmap getBitmapFromMemCache(int i) {
        return getBitmapFromMemCache("res:" + i);
    }

    private final void addBitmapToMemCache(int i, Bitmap bitmap) {
        addBitmapToMemCache("res:" + i, bitmap);
    }

    public final synchronized void clear() {
        memCache.evictAll();
    }
}
