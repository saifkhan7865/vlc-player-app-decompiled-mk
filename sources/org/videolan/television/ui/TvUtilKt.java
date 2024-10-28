package org.videolan.television.ui;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import androidx.core.content.ContextCompat;
import androidx.leanback.app.BackgroundManager;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.moviepedia.database.models.MediaMetadataWithImages;
import org.videolan.vlc.R;
import org.videolan.vlc.util.KextensionsKt;

@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u001a(\u0010\u0006\u001a\u00020\u0001*\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0007¨\u0006\f"}, d2 = {"clearBackground", "", "context", "Landroid/content/Context;", "bm", "Landroidx/leanback/app/BackgroundManager;", "updateBackground", "Lkotlinx/coroutines/CoroutineScope;", "activity", "Landroid/app/Activity;", "item", "", "television_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: TvUtil.kt */
public final class TvUtilKt {
    public static final void updateBackground(CoroutineScope coroutineScope, Activity activity, BackgroundManager backgroundManager, Object obj) {
        Intrinsics.checkNotNullParameter(coroutineScope, "<this>");
        Intrinsics.checkNotNullParameter(activity, "activity");
        clearBackground(activity, backgroundManager);
        if (backgroundManager != null && obj != null) {
            float screenWidth = ((float) KextensionsKt.getScreenWidth(activity)) / ((float) KextensionsKt.getScreenHeight(activity));
            if (obj instanceof MediaLibraryItem) {
                Job unused = BuildersKt__Builders_commonKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new TvUtilKt$updateBackground$1(obj, screenWidth, backgroundManager, activity, (Continuation<? super TvUtilKt$updateBackground$1>) null), 3, (Object) null);
            } else if (obj instanceof MediaMetadataWithImages) {
                Job unused2 = BuildersKt__Builders_commonKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new TvUtilKt$updateBackground$2(obj, screenWidth, backgroundManager, activity, (Continuation<? super TvUtilKt$updateBackground$2>) null), 3, (Object) null);
            }
        }
    }

    public static final void clearBackground(Context context, BackgroundManager backgroundManager) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (backgroundManager != null) {
            backgroundManager.setColor(ContextCompat.getColor(context, R.color.tv_bg));
            backgroundManager.setDrawable((Drawable) null);
        }
    }
}
