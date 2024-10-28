package org.videolan.vlc.gui.helpers.hf;

import androidx.lifecycle.ViewModel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CompletableDeferredKt;
import kotlinx.coroutines.Job;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0005J\u0006\u0010\u0015\u001a\u00020\u0013R \u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\r\u0010\u000bR\u001a\u0010\u000e\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u000b\"\u0004\b\u0010\u0010\u0011¨\u0006\u0016"}, d2 = {"Lorg/videolan/vlc/gui/helpers/hf/PermissionViewmodel;", "Landroidx/lifecycle/ViewModel;", "()V", "deferredGrant", "Lkotlinx/coroutines/CompletableDeferred;", "", "getDeferredGrant", "()Lkotlinx/coroutines/CompletableDeferred;", "setDeferredGrant", "(Lkotlinx/coroutines/CompletableDeferred;)V", "isCompleted", "()Z", "permissionPending", "getPermissionPending", "permissionRationaleShown", "getPermissionRationaleShown", "setPermissionRationaleShown", "(Z)V", "complete", "", "value", "setupDeferred", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: BaseHeadlessFragment.kt */
public final class PermissionViewmodel extends ViewModel {
    public CompletableDeferred<Boolean> deferredGrant;
    private boolean permissionRationaleShown;

    public final boolean getPermissionRationaleShown() {
        return this.permissionRationaleShown;
    }

    public final void setPermissionRationaleShown(boolean z) {
        this.permissionRationaleShown = z;
    }

    public final CompletableDeferred<Boolean> getDeferredGrant() {
        CompletableDeferred<Boolean> completableDeferred = this.deferredGrant;
        if (completableDeferred != null) {
            return completableDeferred;
        }
        Intrinsics.throwUninitializedPropertyAccessException("deferredGrant");
        return null;
    }

    public final void setDeferredGrant(CompletableDeferred<Boolean> completableDeferred) {
        Intrinsics.checkNotNullParameter(completableDeferred, "<set-?>");
        this.deferredGrant = completableDeferred;
    }

    public final boolean getPermissionPending() {
        return this.deferredGrant != null && !getDeferredGrant().isCompleted();
    }

    public final boolean isCompleted() {
        return this.deferredGrant != null && getDeferredGrant().isCompleted();
    }

    public final void complete(boolean z) {
        if (this.deferredGrant != null) {
            getDeferredGrant().complete(Boolean.valueOf(z));
        }
    }

    public final void setupDeferred() {
        CompletableDeferred CompletableDeferred$default = CompletableDeferredKt.CompletableDeferred$default((Job) null, 1, (Object) null);
        CompletableDeferred$default.invokeOnCompletion(new PermissionViewmodel$setupDeferred$1$1(this));
        setDeferredGrant(CompletableDeferred$default);
    }
}
