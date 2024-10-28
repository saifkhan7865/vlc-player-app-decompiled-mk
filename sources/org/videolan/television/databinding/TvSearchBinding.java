package org.videolan.television.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import org.videolan.television.R;

public final class TvSearchBinding implements ViewBinding {
    public final TextView empty;
    private final FrameLayout rootView;

    private TvSearchBinding(FrameLayout frameLayout, TextView textView) {
        this.rootView = frameLayout;
        this.empty = textView;
    }

    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static TvSearchBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, (ViewGroup) null, false);
    }

    public static TvSearchBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.tv_search, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static TvSearchBinding bind(View view) {
        int i = R.id.empty;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
        if (textView != null) {
            return new TvSearchBinding((FrameLayout) view, textView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
