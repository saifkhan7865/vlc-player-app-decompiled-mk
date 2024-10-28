package org.videolan.vlc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import org.videolan.vlc.R;

public abstract class WidgetMiniInitialBinding extends ViewDataBinding {
    public final TextView appName;
    public final ImageButton playPause;
    public final RelativeLayout widgetContainer;

    protected WidgetMiniInitialBinding(Object obj, View view, int i, TextView textView, ImageButton imageButton, RelativeLayout relativeLayout) {
        super(obj, view, i);
        this.appName = textView;
        this.playPause = imageButton;
        this.widgetContainer = relativeLayout;
    }

    public static WidgetMiniInitialBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static WidgetMiniInitialBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (WidgetMiniInitialBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.widget_mini_initial, viewGroup, z, obj);
    }

    public static WidgetMiniInitialBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static WidgetMiniInitialBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (WidgetMiniInitialBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.widget_mini_initial, (ViewGroup) null, false, obj);
    }

    public static WidgetMiniInitialBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static WidgetMiniInitialBinding bind(View view, Object obj) {
        return (WidgetMiniInitialBinding) bind(obj, view, R.layout.widget_mini_initial);
    }
}
