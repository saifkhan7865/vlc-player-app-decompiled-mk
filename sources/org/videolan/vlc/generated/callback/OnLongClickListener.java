package org.videolan.vlc.generated.callback;

import android.view.View;

public final class OnLongClickListener implements View.OnLongClickListener {
    final Listener mListener;
    final int mSourceId;

    public interface Listener {
        boolean _internalCallbackOnLongClick(int i, View view);
    }

    public OnLongClickListener(Listener listener, int i) {
        this.mListener = listener;
        this.mSourceId = i;
    }

    public boolean onLongClick(View view) {
        return this.mListener._internalCallbackOnLongClick(this.mSourceId, view);
    }
}
