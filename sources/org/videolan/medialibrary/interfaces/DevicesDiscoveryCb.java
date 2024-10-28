package org.videolan.medialibrary.interfaces;

public interface DevicesDiscoveryCb {
    void onDiscoveryCompleted();

    void onDiscoveryFailed(String str);

    void onDiscoveryProgress(String str);

    void onDiscoveryStarted();

    void onParsingStatsUpdated(int i, int i2);

    void onReloadCompleted(String str);

    void onReloadStarted(String str);
}
