package org.videolan.television.ui;

import android.graphics.Bitmap;
import android.net.Uri;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.vlc.gui.helpers.AudioUtil;
import org.videolan.vlc.gui.helpers.BitmapUtil;
import org.videolan.vlc.gui.helpers.UiTools;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "Landroid/graphics/Bitmap;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.television.ui.TvUtilKt$updateBackground$1$blurred$1", f = "TvUtil.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: TvUtil.kt */
final class TvUtilKt$updateBackground$1$blurred$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Bitmap>, Object> {
    final /* synthetic */ String $artworkMrl;
    final /* synthetic */ float $screenRatio;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    TvUtilKt$updateBackground$1$blurred$1(String str, float f, Continuation<? super TvUtilKt$updateBackground$1$blurred$1> continuation) {
        super(2, continuation);
        this.$artworkMrl = str;
        this.$screenRatio = f;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new TvUtilKt$updateBackground$1$blurred$1(this.$artworkMrl, this.$screenRatio, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Bitmap> continuation) {
        return ((TvUtilKt$updateBackground$1$blurred$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            Bitmap readCoverBitmap = AudioUtil.INSTANCE.readCoverBitmap(Uri.decode(this.$artworkMrl), 512);
            if (readCoverBitmap == null) {
                return null;
            }
            return UiTools.INSTANCE.blurBitmap(BitmapUtil.INSTANCE.centerCrop(readCoverBitmap, readCoverBitmap.getWidth(), (int) (((float) readCoverBitmap.getWidth()) / this.$screenRatio)), 10.0f);
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
