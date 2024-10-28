package org.videolan.vlc.gui.browser;

import androidx.fragment.app.FragmentActivity;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.vlc.util.Permissions;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: BaseBrowserFragment.kt */
final class BaseBrowserFragment$removeItem$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ Runnable $deleteAction;
    final /* synthetic */ MediaWrapper $mw;
    final /* synthetic */ BaseBrowserFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BaseBrowserFragment$removeItem$1(BaseBrowserFragment baseBrowserFragment, MediaWrapper mediaWrapper, Runnable runnable) {
        super(0);
        this.this$0 = baseBrowserFragment;
        this.$mw = mediaWrapper;
        this.$deleteAction = runnable;
    }

    public final void invoke() {
        Permissions permissions = Permissions.INSTANCE;
        FragmentActivity requireActivity = this.this$0.requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        if (permissions.checkWritePermission(requireActivity, this.$mw, this.$deleteAction)) {
            this.$deleteAction.run();
        }
    }
}
