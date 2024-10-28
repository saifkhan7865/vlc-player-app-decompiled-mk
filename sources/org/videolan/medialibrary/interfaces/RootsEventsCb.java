package org.videolan.medialibrary.interfaces;

public interface RootsEventsCb {
    void onDiscoveryCompleted();

    void onDiscoveryFailed(String str);

    void onDiscoveryProgress(String str);

    void onDiscoveryStarted();

    void onRootAdded(String str, boolean z);

    void onRootBanned(String str, boolean z);

    void onRootRemoved(String str, boolean z);

    void onRootUnbanned(String str, boolean z);
}
