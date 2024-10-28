package org.videolan.vlc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import org.videolan.vlc.R;

public abstract class WidgetMiniBinding extends ViewDataBinding {
    public final ImageView appIcon;
    public final TextView appName;
    public final TextView artist;
    public final WidgetContentFullPlayerBinding controls;
    public final ImageView cover;
    public final RelativeLayout coverParent;
    public final ImageView playerContainerBackground;
    public final ImageView separator;
    public final TextView songName;
    public final LinearLayout textContainer;
    public final ImageView widgetConfigure;
    public final RelativeLayout widgetContainer;

    protected WidgetMiniBinding(Object obj, View view, int i, ImageView imageView, TextView textView, TextView textView2, WidgetContentFullPlayerBinding widgetContentFullPlayerBinding, ImageView imageView2, RelativeLayout relativeLayout, ImageView imageView3, ImageView imageView4, TextView textView3, LinearLayout linearLayout, ImageView imageView5, RelativeLayout relativeLayout2) {
        super(obj, view, i);
        this.appIcon = imageView;
        this.appName = textView;
        this.artist = textView2;
        this.controls = widgetContentFullPlayerBinding;
        this.cover = imageView2;
        this.coverParent = relativeLayout;
        this.playerContainerBackground = imageView3;
        this.separator = imageView4;
        this.songName = textView3;
        this.textContainer = linearLayout;
        this.widgetConfigure = imageView5;
        this.widgetContainer = relativeLayout2;
    }

    public static WidgetMiniBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static WidgetMiniBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (WidgetMiniBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.widget_mini, viewGroup, z, obj);
    }

    public static WidgetMiniBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static WidgetMiniBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (WidgetMiniBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.widget_mini, (ViewGroup) null, false, obj);
    }

    public static WidgetMiniBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static WidgetMiniBinding bind(View view, Object obj) {
        return (WidgetMiniBinding) bind(obj, view, R.layout.widget_mini);
    }
}
