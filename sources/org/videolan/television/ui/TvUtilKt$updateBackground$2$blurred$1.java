package org.videolan.television.ui;

import android.graphics.Bitmap;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.moviepedia.database.models.MediaMetadataWithImages;
import org.videolan.tools.HttpImageLoader;
import org.videolan.vlc.gui.helpers.BitmapUtil;
import org.videolan.vlc.gui.helpers.UiTools;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "Landroid/graphics/Bitmap;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.television.ui.TvUtilKt$updateBackground$2$blurred$1", f = "TvUtil.kt", i = {0}, l = {317}, m = "invokeSuspend", n = {"cover"}, s = {"L$0"})
/* compiled from: TvUtil.kt */
final class TvUtilKt$updateBackground$2$blurred$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Bitmap>, Object> {
    final /* synthetic */ Object $item;
    final /* synthetic */ float $screenRatio;
    Object L$0;
    Object L$1;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    TvUtilKt$updateBackground$2$blurred$1(Object obj, float f, Continuation<? super TvUtilKt$updateBackground$2$blurred$1> continuation) {
        super(2, continuation);
        this.$item = obj;
        this.$screenRatio = f;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new TvUtilKt$updateBackground$2$blurred$1(this.$item, this.$screenRatio, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Bitmap> continuation) {
        return ((TvUtilKt$updateBackground$2$blurred$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(T t) {
        Ref.ObjectRef objectRef;
        Ref.ObjectRef objectRef2;
        T coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(t);
            Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
            this.L$0 = objectRef3;
            this.L$1 = objectRef3;
            this.label = 1;
            T downloadBitmap = HttpImageLoader.INSTANCE.downloadBitmap(((MediaMetadataWithImages) this.$item).getMetadata().getCurrentPoster(), this);
            if (downloadBitmap == coroutine_suspended) {
                return coroutine_suspended;
            }
            objectRef2 = objectRef3;
            t = downloadBitmap;
            objectRef = objectRef2;
        } else if (i == 1) {
            objectRef2 = (Ref.ObjectRef) this.L$1;
            objectRef = (Ref.ObjectRef) this.L$0;
            ResultKt.throwOnFailure(t);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        objectRef2.element = t;
        Bitmap bitmap = (Bitmap) objectRef.element;
        if (bitmap != null) {
            objectRef.element = BitmapUtil.INSTANCE.centerCrop(bitmap, bitmap.getWidth(), (int) (((float) bitmap.getWidth()) / this.$screenRatio));
        }
        return UiTools.INSTANCE.blurBitmap((Bitmap) objectRef.element, 10.0f);
    }
}
