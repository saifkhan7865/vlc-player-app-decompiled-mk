package org.videolan.vlc.gui.dialogs;

import android.app.Dialog;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
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
import org.videolan.resources.AndroidDevices;
import org.videolan.vlc.R;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.dialogs.VLCBottomSheetDialogFragment$onViewCreated$1", f = "VLCBottomSheetDialogFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: VLCBottomSheetDialogFragment.kt */
final class VLCBottomSheetDialogFragment$onViewCreated$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ View $view;
    int label;
    final /* synthetic */ VLCBottomSheetDialogFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    VLCBottomSheetDialogFragment$onViewCreated$1(VLCBottomSheetDialogFragment vLCBottomSheetDialogFragment, View view, Continuation<? super VLCBottomSheetDialogFragment$onViewCreated$1> continuation) {
        super(2, continuation);
        this.this$0 = vLCBottomSheetDialogFragment;
        this.$view = view;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new VLCBottomSheetDialogFragment$onViewCreated$1(this.this$0, this.$view, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((VLCBottomSheetDialogFragment$onViewCreated$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        FrameLayout frameLayout;
        Window window;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            Dialog dialog = this.this$0.getDialog();
            if (!(dialog == null || (window = dialog.getWindow()) == null)) {
                window.setLayout(this.this$0.getResources().getDimensionPixelSize(R.dimen.default_context_width), -1);
            }
            Dialog dialog2 = this.this$0.getDialog();
            if (!(dialog2 == null || (frameLayout = (FrameLayout) dialog2.findViewById(com.google.android.material.R.id.design_bottom_sheet)) == null)) {
                VLCBottomSheetDialogFragment vLCBottomSheetDialogFragment = this.this$0;
                BottomSheetBehavior from = BottomSheetBehavior.from(frameLayout);
                Intrinsics.checkNotNullExpressionValue(from, "from(...)");
                if (from.getState() == 4) {
                    from.setState(vLCBottomSheetDialogFragment.getDefaultState());
                }
            }
            Dialog dialog3 = this.this$0.getDialog();
            View view = null;
            View findViewById = dialog3 != null ? dialog3.findViewById(R.id.touch_outside) : null;
            if (findViewById != null) {
                findViewById.setFocusable(false);
            }
            Dialog dialog4 = this.this$0.getDialog();
            if (dialog4 != null) {
                view = dialog4.findViewById(R.id.touch_outside);
            }
            if (view != null) {
                view.setFocusableInTouchMode(false);
            }
            if (AndroidDevices.INSTANCE.isTv()) {
                this.this$0.applyOverscanMargin(this.$view);
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
