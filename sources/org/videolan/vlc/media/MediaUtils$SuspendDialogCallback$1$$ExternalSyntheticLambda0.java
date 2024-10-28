package org.videolan.vlc.media;

import android.content.DialogInterface;
import org.videolan.vlc.media.MediaUtils;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaUtils$SuspendDialogCallback$1$$ExternalSyntheticLambda0 implements DialogInterface.OnCancelListener {
    public final /* synthetic */ MediaUtils.SuspendDialogCallback f$0;

    public /* synthetic */ MediaUtils$SuspendDialogCallback$1$$ExternalSyntheticLambda0(MediaUtils.SuspendDialogCallback suspendDialogCallback) {
        this.f$0 = suspendDialogCallback;
    }

    public final void onCancel(DialogInterface dialogInterface) {
        MediaUtils.SuspendDialogCallback.AnonymousClass1.invokeSuspend$lambda$0(this.f$0, dialogInterface);
    }
}
