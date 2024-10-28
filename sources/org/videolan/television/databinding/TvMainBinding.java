package org.videolan.television.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import org.videolan.television.R;

public final class TvMainBinding implements ViewBinding {
    private final ConstraintLayout rootView;
    public final ConstraintLayout tvContainer;
    public final ProgressBar tvMainProgress;

    private TvMainBinding(ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ProgressBar progressBar) {
        this.rootView = constraintLayout;
        this.tvContainer = constraintLayout2;
        this.tvMainProgress = progressBar;
    }

    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static TvMainBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, (ViewGroup) null, false);
    }

    public static TvMainBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.tv_main, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static TvMainBinding bind(View view) {
        ConstraintLayout constraintLayout = (ConstraintLayout) view;
        int i = R.id.tv_main_progress;
        ProgressBar progressBar = (ProgressBar) ViewBindings.findChildViewById(view, i);
        if (progressBar != null) {
            return new TvMainBinding(constraintLayout, constraintLayout, progressBar);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
