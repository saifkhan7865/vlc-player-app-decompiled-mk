package org.videolan.vlc.gui;

import android.content.Context;
import androidx.lifecycle.ViewModelProvider;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.videolan.vlc.gui.SafeModeModel;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Landroidx/lifecycle/ViewModelProvider$Factory;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: PinCodeActivity.kt */
final class PinCodeActivity$onCreate$getModel$2 extends Lambda implements Function0<ViewModelProvider.Factory> {
    final /* synthetic */ PinCodeActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PinCodeActivity$onCreate$getModel$2(PinCodeActivity pinCodeActivity) {
        super(0);
        this.this$0 = pinCodeActivity;
    }

    public final ViewModelProvider.Factory invoke() {
        PinCodeActivity pinCodeActivity = this.this$0;
        Context context = pinCodeActivity;
        PinCodeReason access$getReason$p = pinCodeActivity.reason;
        if (access$getReason$p == null) {
            Intrinsics.throwUninitializedPropertyAccessException("reason");
            access$getReason$p = null;
        }
        return new SafeModeModel.Factory(context, access$getReason$p);
    }
}
