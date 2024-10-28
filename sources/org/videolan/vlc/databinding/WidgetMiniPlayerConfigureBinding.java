package org.videolan.vlc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.appcompat.widget.SwitchCompat;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import org.videolan.vlc.R;

public abstract class WidgetMiniPlayerConfigureBinding extends ViewDataBinding {
    public final CoordinatorLayout coordinator;
    public final FrameLayout fragmentPlaceholder;
    public final SwitchCompat previewPlaying;
    public final ImageView widgetPreview;

    protected WidgetMiniPlayerConfigureBinding(Object obj, View view, int i, CoordinatorLayout coordinatorLayout, FrameLayout frameLayout, SwitchCompat switchCompat, ImageView imageView) {
        super(obj, view, i);
        this.coordinator = coordinatorLayout;
        this.fragmentPlaceholder = frameLayout;
        this.previewPlaying = switchCompat;
        this.widgetPreview = imageView;
    }

    public static WidgetMiniPlayerConfigureBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static WidgetMiniPlayerConfigureBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (WidgetMiniPlayerConfigureBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.widget_mini_player_configure, viewGroup, z, obj);
    }

    public static WidgetMiniPlayerConfigureBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static WidgetMiniPlayerConfigureBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (WidgetMiniPlayerConfigureBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.widget_mini_player_configure, (ViewGroup) null, false, obj);
    }

    public static WidgetMiniPlayerConfigureBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static WidgetMiniPlayerConfigureBinding bind(View view, Object obj) {
        return (WidgetMiniPlayerConfigureBinding) bind(obj, view, R.layout.widget_mini_player_configure);
    }
}
