package org.videolan.vlc.gui.helpers;

import android.graphics.Bitmap;
import android.widget.ImageView;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.helpers.UiTools$blurView$2", f = "UiTools.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: UiTools.kt */
final class UiTools$blurView$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Bitmap $blurred;
    final /* synthetic */ ImageView $imageView;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    UiTools$blurView$2(ImageView imageView, Bitmap bitmap, Continuation<? super UiTools$blurView$2> continuation) {
        super(2, continuation);
        this.$imageView = imageView;
        this.$blurred = bitmap;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new UiTools$blurView$2(this.$imageView, this.$blurred, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((UiTools$blurView$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            this.$imageView.setImageBitmap(this.$blurred);
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
