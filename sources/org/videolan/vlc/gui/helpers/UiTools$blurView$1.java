package org.videolan.vlc.gui.helpers;

import android.graphics.Bitmap;
import android.widget.ImageView;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.helpers.UiTools", f = "UiTools.kt", i = {0}, l = {642, 645}, m = "blurView", n = {"imageView"}, s = {"L$0"})
/* compiled from: UiTools.kt */
final class UiTools$blurView$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ UiTools this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    UiTools$blurView$1(UiTools uiTools, Continuation<? super UiTools$blurView$1> continuation) {
        super(continuation);
        this.this$0 = uiTools;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.blurView((ImageView) null, (Bitmap) null, 0.0f, 0, this);
    }
}
