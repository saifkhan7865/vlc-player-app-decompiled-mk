package org.videolan.vlc.webserver.viewmodels;

import androidx.lifecycle.ViewModel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.vlc.webserver.gui.remoteaccess.onboarding.FragmentName;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lorg/videolan/vlc/webserver/viewmodels/RemoteAccessOnboardingViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "currentFragment", "Lorg/videolan/vlc/webserver/gui/remoteaccess/onboarding/FragmentName;", "getCurrentFragment", "()Lorg/videolan/vlc/webserver/gui/remoteaccess/onboarding/FragmentName;", "setCurrentFragment", "(Lorg/videolan/vlc/webserver/gui/remoteaccess/onboarding/FragmentName;)V", "webserver_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: RemoteAccessOnboardingViewModel.kt */
public final class RemoteAccessOnboardingViewModel extends ViewModel {
    private FragmentName currentFragment = FragmentName.WELCOME;

    public final FragmentName getCurrentFragment() {
        return this.currentFragment;
    }

    public final void setCurrentFragment(FragmentName fragmentName) {
        Intrinsics.checkNotNullParameter(fragmentName, "<set-?>");
        this.currentFragment = fragmentName;
    }
}
