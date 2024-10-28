package org.videolan.television.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewbinding.ViewBinding;
import org.videolan.television.R;

public final class TvPreferencesActivityBinding implements ViewBinding {
    private final View rootView;

    private TvPreferencesActivityBinding(View view) {
        this.rootView = view;
    }

    public View getRoot() {
        return this.rootView;
    }

    public static TvPreferencesActivityBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, (ViewGroup) null, false);
    }

    public static TvPreferencesActivityBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.tv_preferences_activity, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static TvPreferencesActivityBinding bind(View view) {
        if (view != null) {
            return new TvPreferencesActivityBinding(view);
        }
        throw new NullPointerException("rootView");
    }
}
