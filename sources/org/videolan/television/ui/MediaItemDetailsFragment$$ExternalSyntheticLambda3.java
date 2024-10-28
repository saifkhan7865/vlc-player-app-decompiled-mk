package org.videolan.television.ui;

import androidx.fragment.app.FragmentActivity;
import androidx.leanback.widget.Action;
import androidx.leanback.widget.OnActionClickedListener;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaItemDetailsFragment$$ExternalSyntheticLambda3 implements OnActionClickedListener {
    public final /* synthetic */ FragmentActivity f$0;
    public final /* synthetic */ MediaItemDetailsFragment f$1;
    public final /* synthetic */ Action f$2;
    public final /* synthetic */ Action f$3;

    public /* synthetic */ MediaItemDetailsFragment$$ExternalSyntheticLambda3(FragmentActivity fragmentActivity, MediaItemDetailsFragment mediaItemDetailsFragment, Action action, Action action2) {
        this.f$0 = fragmentActivity;
        this.f$1 = mediaItemDetailsFragment;
        this.f$2 = action;
        this.f$3 = action2;
    }

    public final void onActionClicked(Action action) {
        MediaItemDetailsFragment.buildDetails$lambda$14(this.f$0, this.f$1, this.f$2, this.f$3, action);
    }
}
