package org.videolan.vlc.gui.audio;

import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J \u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0005H\u0016J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0005H\u0016¨\u0006\f"}, d2 = {"org/videolan/vlc/gui/audio/AudioBrowserFragment$onViewCreated$5", "Landroidx/viewpager/widget/ViewPager$OnPageChangeListener;", "onPageScrollStateChanged", "", "state", "", "onPageScrolled", "position", "positionOffset", "", "positionOffsetPixels", "onPageSelected", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: AudioBrowserFragment.kt */
public final class AudioBrowserFragment$onViewCreated$5 implements ViewPager.OnPageChangeListener {
    final /* synthetic */ AudioBrowserFragment this$0;

    public void onPageScrollStateChanged(int i) {
    }

    public void onPageScrolled(int i, float f, int i2) {
    }

    AudioBrowserFragment$onViewCreated$5(AudioBrowserFragment audioBrowserFragment) {
        this.this$0 = audioBrowserFragment;
    }

    public void onPageSelected(int i) {
        FragmentActivity activity = this.this$0.getActivity();
        if (activity != null) {
            activity.invalidateOptionsMenu();
        }
    }
}
