package org.videolan.vlc.gui.helpers.hf;

import android.net.Uri;
import androidx.fragment.app.Fragment;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.videolan.vlc.gui.helpers.hf.StoragePermissionsDelegate;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.helpers.hf.StoragePermissionsDelegate$Companion", f = "StoragePermissionsDelegate.kt", i = {}, l = {214}, m = "getWritePermission", n = {}, s = {})
/* compiled from: StoragePermissionsDelegate.kt */
final class StoragePermissionsDelegate$Companion$getWritePermission$3 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ StoragePermissionsDelegate.Companion this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    StoragePermissionsDelegate$Companion$getWritePermission$3(StoragePermissionsDelegate.Companion companion, Continuation<? super StoragePermissionsDelegate$Companion$getWritePermission$3> continuation) {
        super(continuation);
        this.this$0 = companion;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.getWritePermission((Fragment) null, (Uri) null, (Continuation<? super Boolean>) this);
    }
}
