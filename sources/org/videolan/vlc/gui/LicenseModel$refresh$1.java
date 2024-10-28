package org.videolan.vlc.gui;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.LicenseModel", f = "LibrariesActivity.kt", i = {0}, l = {95}, m = "refresh", n = {"this"}, s = {"L$0"})
/* compiled from: LibrariesActivity.kt */
final class LicenseModel$refresh$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ LicenseModel this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    LicenseModel$refresh$1(LicenseModel licenseModel, Continuation<? super LicenseModel$refresh$1> continuation) {
        super(continuation);
        this.this$0 = licenseModel;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.refresh(this);
    }
}
