package org.videolan.vlc.gui.helpers;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\b\u0010\u0005\u001a\u00020\u0003H&J\b\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\b\u001a\u00020\u0003H&J\b\u0010\t\u001a\u00020\u0003H&J\b\u0010\n\u001a\u00020\u0003H&J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\rH&J\b\u0010\u000e\u001a\u00020\u0003H&J\b\u0010\u000f\u001a\u00020\u0003H&J\b\u0010\u0010\u001a\u00020\u0003H&J\b\u0010\u0011\u001a\u00020\u0003H&¨\u0006\u0012"}, d2 = {"Lorg/videolan/vlc/gui/helpers/KeycodeListener;", "", "bookmark", "", "decreaseRate", "increaseRate", "isReady", "", "next", "previous", "resetRate", "seek", "delta", "", "showAdvancedOptions", "showEqualizer", "stop", "togglePlayPause", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: PlayerKeyListenerDelegate.kt */
public interface KeycodeListener {
    void bookmark();

    void decreaseRate();

    void increaseRate();

    boolean isReady();

    void next();

    void previous();

    void resetRate();

    void seek(int i);

    void showAdvancedOptions();

    void showEqualizer();

    void stop();

    void togglePlayPause();
}
