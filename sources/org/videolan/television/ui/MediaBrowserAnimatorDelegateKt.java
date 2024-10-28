package org.videolan.television.ui;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.television.ui.browser.BaseBrowserTvFragment;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0016\u0010\u0000\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"setAnimator", "", "Lorg/videolan/television/ui/browser/BaseBrowserTvFragment;", "cl", "Landroidx/constraintlayout/widget/ConstraintLayout;", "television_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaBrowserAnimatorDelegate.kt */
public final class MediaBrowserAnimatorDelegateKt {
    public static final void setAnimator(BaseBrowserTvFragment<?> baseBrowserTvFragment, ConstraintLayout constraintLayout) {
        Intrinsics.checkNotNullParameter(baseBrowserTvFragment, "<this>");
        Intrinsics.checkNotNullParameter(constraintLayout, "cl");
        baseBrowserTvFragment.setAnimationDelegate$television_release(new MediaBrowserAnimatorDelegate(baseBrowserTvFragment.getBinding(), constraintLayout));
        baseBrowserTvFragment.getBinding().headerButton.setOnFocusChangeListener(baseBrowserTvFragment.getAnimationDelegate$television_release());
        baseBrowserTvFragment.getBinding().displayButton.setOnFocusChangeListener(baseBrowserTvFragment.getAnimationDelegate$television_release());
        baseBrowserTvFragment.getBinding().sortButton.setOnFocusChangeListener(baseBrowserTvFragment.getAnimationDelegate$television_release());
        baseBrowserTvFragment.getBinding().favoriteButton.setOnFocusChangeListener(baseBrowserTvFragment.getAnimationDelegate$television_release());
        baseBrowserTvFragment.getBinding().imageButtonSort.setOnFocusChangeListener(baseBrowserTvFragment.getAnimationDelegate$television_release());
        baseBrowserTvFragment.getBinding().imageButtonDisplay.setOnFocusChangeListener(baseBrowserTvFragment.getAnimationDelegate$television_release());
        baseBrowserTvFragment.getBinding().imageButtonHeader.setOnFocusChangeListener(baseBrowserTvFragment.getAnimationDelegate$television_release());
        baseBrowserTvFragment.getBinding().imageButtonSettings.setOnFocusChangeListener(baseBrowserTvFragment.getAnimationDelegate$television_release());
        baseBrowserTvFragment.getBinding().list.addOnScrollListener(baseBrowserTvFragment.getAnimationDelegate$television_release());
    }
}
