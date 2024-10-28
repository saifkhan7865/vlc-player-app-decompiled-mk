package org.videolan.vlc.gui.onboarding;

import androidx.lifecycle.ViewModel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.resources.AndroidDevices;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\f\"\u0004\b\u0011\u0010\u000eR\u001a\u0010\u0012\u001a\u00020\u0013X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0018\u001a\u00020\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\f\"\u0004\b\u001a\u0010\u000eR\u001a\u0010\u001b\u001a\u00020\u001cX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 ¨\u0006!"}, d2 = {"Lorg/videolan/vlc/gui/onboarding/OnboardingViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "currentFragment", "Lorg/videolan/vlc/gui/onboarding/FragmentName;", "getCurrentFragment", "()Lorg/videolan/vlc/gui/onboarding/FragmentName;", "setCurrentFragment", "(Lorg/videolan/vlc/gui/onboarding/FragmentName;)V", "notificationPermissionAlreadyAsked", "", "getNotificationPermissionAlreadyAsked", "()Z", "setNotificationPermissionAlreadyAsked", "(Z)V", "permissionAlreadyAsked", "getPermissionAlreadyAsked", "setPermissionAlreadyAsked", "permissionType", "Lorg/videolan/vlc/gui/onboarding/PermissionType;", "getPermissionType", "()Lorg/videolan/vlc/gui/onboarding/PermissionType;", "setPermissionType", "(Lorg/videolan/vlc/gui/onboarding/PermissionType;)V", "scanStorages", "getScanStorages", "setScanStorages", "theme", "", "getTheme", "()I", "setTheme", "(I)V", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: OnboardingViewModel.kt */
public final class OnboardingViewModel extends ViewModel {
    private FragmentName currentFragment;
    private boolean notificationPermissionAlreadyAsked;
    private boolean permissionAlreadyAsked;
    private PermissionType permissionType = PermissionType.ALL;
    private boolean scanStorages = true;
    private int theme;

    public OnboardingViewModel() {
        this.theme = AndroidDevices.INSTANCE.canUseSystemNightMode() ? -1 : 0;
        this.currentFragment = FragmentName.WELCOME;
    }

    public final boolean getPermissionAlreadyAsked() {
        return this.permissionAlreadyAsked;
    }

    public final void setPermissionAlreadyAsked(boolean z) {
        this.permissionAlreadyAsked = z;
    }

    public final boolean getNotificationPermissionAlreadyAsked() {
        return this.notificationPermissionAlreadyAsked;
    }

    public final void setNotificationPermissionAlreadyAsked(boolean z) {
        this.notificationPermissionAlreadyAsked = z;
    }

    public final boolean getScanStorages() {
        return this.scanStorages;
    }

    public final void setScanStorages(boolean z) {
        this.scanStorages = z;
    }

    public final PermissionType getPermissionType() {
        return this.permissionType;
    }

    public final void setPermissionType(PermissionType permissionType2) {
        Intrinsics.checkNotNullParameter(permissionType2, "<set-?>");
        this.permissionType = permissionType2;
    }

    public final int getTheme() {
        return this.theme;
    }

    public final void setTheme(int i) {
        this.theme = i;
    }

    public final FragmentName getCurrentFragment() {
        return this.currentFragment;
    }

    public final void setCurrentFragment(FragmentName fragmentName) {
        Intrinsics.checkNotNullParameter(fragmentName, "<set-?>");
        this.currentFragment = fragmentName;
    }
}
