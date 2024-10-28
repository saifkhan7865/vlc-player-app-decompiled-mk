package org.videolan.television.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import org.videolan.television.R;

public final class TvNextBinding implements ViewBinding {
    public final TextView empty;
    public final FrameLayout fragmentPlaceholder;
    private final FrameLayout rootView;

    private TvNextBinding(FrameLayout frameLayout, TextView textView, FrameLayout frameLayout2) {
        this.rootView = frameLayout;
        this.empty = textView;
        this.fragmentPlaceholder = frameLayout2;
    }

    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static TvNextBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, (ViewGroup) null, false);
    }

    public static TvNextBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.tv_next, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static TvNextBinding bind(View view) {
        int i = R.id.empty;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
        if (textView != null) {
            i = R.id.fragment_placeholder;
            FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, i);
            if (frameLayout != null) {
                return new TvNextBinding((FrameLayout) view, textView, frameLayout);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
