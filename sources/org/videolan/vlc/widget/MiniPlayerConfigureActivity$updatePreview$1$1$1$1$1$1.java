package org.videolan.vlc.widget;

import android.graphics.Bitmap;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.vlc.databinding.WidgetMiniPlayerConfigureBinding;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.widget.MiniPlayerConfigureActivity$updatePreview$1$1$1$1$1$1", f = "MiniPlayerConfigureActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: MiniPlayerConfigureActivity.kt */
final class MiniPlayerConfigureActivity$updatePreview$1$1$1$1$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Bitmap $bm;
    int label;
    final /* synthetic */ MiniPlayerConfigureActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MiniPlayerConfigureActivity$updatePreview$1$1$1$1$1$1(MiniPlayerConfigureActivity miniPlayerConfigureActivity, Bitmap bitmap, Continuation<? super MiniPlayerConfigureActivity$updatePreview$1$1$1$1$1$1> continuation) {
        super(2, continuation);
        this.this$0 = miniPlayerConfigureActivity;
        this.$bm = bitmap;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MiniPlayerConfigureActivity$updatePreview$1$1$1$1$1$1(this.this$0, this.$bm, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MiniPlayerConfigureActivity$updatePreview$1$1$1$1$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            WidgetMiniPlayerConfigureBinding access$getBinding$p = this.this$0.binding;
            if (access$getBinding$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                access$getBinding$p = null;
            }
            access$getBinding$p.widgetPreview.setImageBitmap(this.$bm);
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
