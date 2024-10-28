package org.videolan.vlc.interfaces;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\b\u001a\u00020\u0003H&J\b\u0010\t\u001a\u00020\u0003H&¨\u0006\n"}, d2 = {"Lorg/videolan/vlc/interfaces/OnEqualizerBarChangeListener;", "", "onProgressChanged", "", "value", "", "fromUser", "", "onStartTrackingTouch", "onStopTrackingTouch", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: OnEqualizerBarChangeListener.kt */
public interface OnEqualizerBarChangeListener {
    void onProgressChanged(float f, boolean z);

    void onStartTrackingTouch();

    void onStopTrackingTouch();
}
