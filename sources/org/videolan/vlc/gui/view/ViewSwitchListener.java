package org.videolan.vlc.gui.view;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH&J\b\u0010\n\u001a\u00020\u0003H&J\b\u0010\u000b\u001a\u00020\u0003H&J\b\u0010\f\u001a\u00020\u0003H&J\b\u0010\r\u001a\u00020\u0003H&¨\u0006\u000e"}, d2 = {"Lorg/videolan/vlc/gui/view/ViewSwitchListener;", "", "onBackSwitched", "", "onSwitched", "position", "", "onSwitching", "progress", "", "onTouchClick", "onTouchDown", "onTouchLongClick", "onTouchUp", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: FlingViewGroup.kt */
public interface ViewSwitchListener {
    void onBackSwitched();

    void onSwitched(int i);

    void onSwitching(float f);

    void onTouchClick();

    void onTouchDown();

    void onTouchLongClick();

    void onTouchUp();
}
