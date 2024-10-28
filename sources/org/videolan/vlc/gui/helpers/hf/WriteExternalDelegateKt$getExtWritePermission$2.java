package org.videolan.vlc.gui.helpers.hf;

import android.net.Uri;
import androidx.fragment.app.Fragment;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.helpers.hf.WriteExternalDelegateKt", f = "WriteExternalDelegate.kt", i = {}, l = {126}, m = "getExtWritePermission", n = {}, s = {})
/* compiled from: WriteExternalDelegate.kt */
final class WriteExternalDelegateKt$getExtWritePermission$2 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;

    WriteExternalDelegateKt$getExtWritePermission$2(Continuation<? super WriteExternalDelegateKt$getExtWritePermission$2> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return WriteExternalDelegateKt.getExtWritePermission((Fragment) null, (Uri) null, (Continuation<? super Boolean>) this);
    }
}
