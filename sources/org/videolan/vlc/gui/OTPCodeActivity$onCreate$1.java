package org.videolan.vlc.gui;

import androidx.activity.OnBackPressedCallback;
import androidx.lifecycle.LifecycleOwnerKt;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;

@Metadata(d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, d2 = {"org/videolan/vlc/gui/OTPCodeActivity$onCreate$1", "Landroidx/activity/OnBackPressedCallback;", "handleOnBackPressed", "", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: OTPCodeActivity.kt */
public final class OTPCodeActivity$onCreate$1 extends OnBackPressedCallback {
    final /* synthetic */ OTPCodeActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    OTPCodeActivity$onCreate$1(OTPCodeActivity oTPCodeActivity) {
        super(true);
        this.this$0 = oTPCodeActivity;
    }

    public void handleOnBackPressed() {
        Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this.this$0), (CoroutineContext) null, (CoroutineStart) null, new OTPCodeActivity$onCreate$1$handleOnBackPressed$1((Continuation<? super OTPCodeActivity$onCreate$1$handleOnBackPressed$1>) null), 3, (Object) null);
        this.this$0.finish();
    }
}
