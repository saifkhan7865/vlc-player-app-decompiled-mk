package org.videolan.television.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.viewbinding.ViewBinding;
import org.videolan.television.R;

public final class TvDescriptionRowBinding implements ViewBinding {
    private final FrameLayout rootView;

    private TvDescriptionRowBinding(FrameLayout frameLayout) {
        this.rootView = frameLayout;
    }

    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static TvDescriptionRowBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, (ViewGroup) null, false);
    }

    public static TvDescriptionRowBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.tv_description_row, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static TvDescriptionRowBinding bind(View view) {
        if (view != null) {
            return new TvDescriptionRowBinding((FrameLayout) view);
        }
        throw new NullPointerException("rootView");
    }
}
