package org.videolan.vlc;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005H&J\u0016\u0010\u0006\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005H&J\b\u0010\u0007\u001a\u00020\u0003H&¨\u0006\b"}, d2 = {"Lorg/videolan/vlc/IMediaBrowserCallback;", "", "registerHistoryCallback", "", "callback", "Lkotlin/Function0;", "registerMediaCallback", "removeCallbacks", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaBrowserCallback.kt */
public interface IMediaBrowserCallback {
    void registerHistoryCallback(Function0<Unit> function0);

    void registerMediaCallback(Function0<Unit> function0);

    void removeCallbacks();
}
