package org.videolan.television.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import org.videolan.television.R;

public final class TvVerticalGridBinding implements ViewBinding {
    private final ConstraintLayout rootView;
    public final TextView tvFragmentEmpty;
    public final FrameLayout tvFragmentPlaceholder;
    public final ProgressBar tvFragmentProgress;

    private TvVerticalGridBinding(ConstraintLayout constraintLayout, TextView textView, FrameLayout frameLayout, ProgressBar progressBar) {
        this.rootView = constraintLayout;
        this.tvFragmentEmpty = textView;
        this.tvFragmentPlaceholder = frameLayout;
        this.tvFragmentProgress = progressBar;
    }

    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static TvVerticalGridBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, (ViewGroup) null, false);
    }

    public static TvVerticalGridBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.tv_vertical_grid, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static TvVerticalGridBinding bind(View view) {
        int i = R.id.tv_fragment_empty;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
        if (textView != null) {
            i = R.id.tv_fragment_placeholder;
            FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, i);
            if (frameLayout != null) {
                i = R.id.tv_fragment_progress;
                ProgressBar progressBar = (ProgressBar) ViewBindings.findChildViewById(view, i);
                if (progressBar != null) {
                    return new TvVerticalGridBinding((ConstraintLayout) view, textView, frameLayout, progressBar);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
