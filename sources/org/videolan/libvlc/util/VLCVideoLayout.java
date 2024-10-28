package org.videolan.libvlc.util;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import org.videolan.R;

public class VLCVideoLayout extends FrameLayout {
    public VLCVideoLayout(Context context) {
        super(context);
        setupLayout(context);
    }

    public VLCVideoLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setupLayout(context);
    }

    public VLCVideoLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setupLayout(context);
    }

    public VLCVideoLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        setupLayout(context);
    }

    private void setupLayout(Context context) {
        inflate(context, R.layout.vlc_video_layout, this);
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        setBackgroundResource(R.color.black);
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        layoutParams.height = -1;
        layoutParams.width = -1;
        setLayoutParams(layoutParams);
    }
}
