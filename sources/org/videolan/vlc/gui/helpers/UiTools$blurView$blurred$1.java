package org.videolan.vlc.gui.helpers;

import android.graphics.Bitmap;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "Landroid/graphics/Bitmap;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.helpers.UiTools$blurView$blurred$1", f = "UiTools.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: UiTools.kt */
final class UiTools$blurView$blurred$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Bitmap>, Object> {
    final /* synthetic */ Bitmap $bitmap;
    final /* synthetic */ float $radius;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    UiTools$blurView$blurred$1(Bitmap bitmap, float f, Continuation<? super UiTools$blurView$blurred$1> continuation) {
        super(2, continuation);
        this.$bitmap = bitmap;
        this.$radius = f;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new UiTools$blurView$blurred$1(this.$bitmap, this.$radius, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Bitmap> continuation) {
        return ((UiTools$blurView$blurred$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            return UiTools.INSTANCE.blurBitmap(this.$bitmap, this.$radius);
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
