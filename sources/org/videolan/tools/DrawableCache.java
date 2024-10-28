package org.videolan.tools;

import android.content.Context;
import android.util.LruCache;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\b\u001a\u00020\tJ \u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00042\b\b\u0001\u0010\u000e\u001a\u00020\u0007J\u001e\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u00042\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00070\u0012H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00070\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lorg/videolan/tools/DrawableCache;", "", "()V", "TAG", "", "memCache", "Landroid/util/LruCache;", "", "clear", "", "getDrawableFromMemCache", "ctx", "Landroid/content/Context;", "name", "defaultDrawable", "getOrPutDrawable", "key", "defaultValue", "Lkotlin/Function0;", "tools_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: DrawableCache.kt */
public final class DrawableCache {
    public static final DrawableCache INSTANCE = new DrawableCache();
    private static final String TAG = "VLC/DrawableCache";
    private static final LruCache<String, Integer> memCache = new LruCache<>(8);

    private DrawableCache() {
    }

    public final int getDrawableFromMemCache(Context context, String str, int i) {
        Intrinsics.checkNotNullParameter(context, "ctx");
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        return getOrPutDrawable(str, new DrawableCache$getDrawableFromMemCache$1(context, str, i));
    }

    private final synchronized int getOrPutDrawable(String str, Function0<Integer> function0) {
        int i;
        LruCache<String, Integer> lruCache = memCache;
        Integer num = lruCache.get(str);
        if (num == null) {
            i = function0.invoke().intValue();
            lruCache.put(str, function0.invoke());
        } else {
            i = num.intValue();
        }
        return i;
    }

    public final synchronized void clear() {
        memCache.evictAll();
    }
}
