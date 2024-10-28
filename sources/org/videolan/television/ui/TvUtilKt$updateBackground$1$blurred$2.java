package org.videolan.television.ui;

import android.graphics.Bitmap;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.vlc.gui.helpers.BitmapUtil;
import org.videolan.vlc.gui.helpers.UiTools;
import org.videolan.vlc.util.ThumbnailsProvider;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "Landroid/graphics/Bitmap;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.television.ui.TvUtilKt$updateBackground$1$blurred$2", f = "TvUtil.kt", i = {}, l = {307}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: TvUtil.kt */
final class TvUtilKt$updateBackground$1$blurred$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Bitmap>, Object> {
    final /* synthetic */ Object $item;
    final /* synthetic */ float $screenRatio;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    TvUtilKt$updateBackground$1$blurred$2(Object obj, float f, Continuation<? super TvUtilKt$updateBackground$1$blurred$2> continuation) {
        super(2, continuation);
        this.$item = obj;
        this.$screenRatio = f;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new TvUtilKt$updateBackground$1$blurred$2(this.$item, this.$screenRatio, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Bitmap> continuation) {
        return ((TvUtilKt$updateBackground$1$blurred$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            MediaWrapper[] tracks = ((MediaLibraryItem) this.$item).getTracks();
            Intrinsics.checkNotNullExpressionValue(tracks, "getTracks(...)");
            this.label = 1;
            obj = ThumbnailsProvider.getPlaylistOrGenreImage$default(ThumbnailsProvider.INSTANCE, "playlist:" + ((MediaLibraryItem) this.$item).getId() + "_512", ArraysKt.toList((T[]) (Object[]) tracks), 512, (Bitmap) null, this, 8, (Object) null);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        Bitmap bitmap = (Bitmap) obj;
        if (bitmap == null) {
            return null;
        }
        return UiTools.INSTANCE.blurBitmap(BitmapUtil.INSTANCE.centerCrop(bitmap, bitmap.getWidth(), (int) (((float) bitmap.getWidth()) / this.$screenRatio)), 10.0f);
    }
}
