package org.videolan.vlc.gui.video;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class VideoBrowserFragment$$ExternalSyntheticLambda0 implements TabLayoutMediator.TabConfigurationStrategy {
    public final /* synthetic */ VideoBrowserFragment f$0;

    public /* synthetic */ VideoBrowserFragment$$ExternalSyntheticLambda0(VideoBrowserFragment videoBrowserFragment) {
        this.f$0 = videoBrowserFragment;
    }

    public final void onConfigureTab(TabLayout.Tab tab, int i) {
        VideoBrowserFragment.setupTabLayout$lambda$1$lambda$0(this.f$0, tab, i);
    }
}
