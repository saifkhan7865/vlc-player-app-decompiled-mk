package org.videolan.television.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.viewbinding.ViewBinding;
import org.videolan.television.R;

public final class MoviepediaTvshowDetailsBinding implements ViewBinding {
    private final FrameLayout rootView;

    private MoviepediaTvshowDetailsBinding(FrameLayout frameLayout) {
        this.rootView = frameLayout;
    }

    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static MoviepediaTvshowDetailsBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, (ViewGroup) null, false);
    }

    public static MoviepediaTvshowDetailsBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.moviepedia_tvshow_details, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static MoviepediaTvshowDetailsBinding bind(View view) {
        if (view != null) {
            return new MoviepediaTvshowDetailsBinding((FrameLayout) view);
        }
        throw new NullPointerException("rootView");
    }
}
