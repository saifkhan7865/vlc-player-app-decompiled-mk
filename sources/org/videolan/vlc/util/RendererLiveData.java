package org.videolan.vlc.util;

import androidx.lifecycle.MutableLiveData;
import kotlin.Metadata;
import org.videolan.libvlc.RendererItem;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0002H\u0016¨\u0006\u0007"}, d2 = {"Lorg/videolan/vlc/util/RendererLiveData;", "Landroidx/lifecycle/MutableLiveData;", "Lorg/videolan/libvlc/RendererItem;", "()V", "setValue", "", "value", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: RendererLiveData.kt */
public final class RendererLiveData extends MutableLiveData<RendererItem> {
    public void setValue(RendererItem rendererItem) {
        RendererItem rendererItem2 = (RendererItem) getValue();
        if (rendererItem2 != null) {
            rendererItem2.release();
        }
        if (rendererItem != null) {
            rendererItem.retain();
        }
        super.setValue(rendererItem);
    }
}
