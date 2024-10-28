package org.videolan.vlc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import org.videolan.vlc.R;

public abstract class WidgetContentFullPlayerBinding extends ViewDataBinding {
    public final ImageButton backward;
    public final ImageButton forward;
    public final ImageButton playPause;
    public final ImageView progressRound;
    public final ImageButton seekForward;
    public final TextView seekForwardText;
    public final ImageButton seekRewind;
    public final TextView seekRewindText;
    public final RelativeLayout widgetLeftSpace;
    public final RelativeLayout widgetRightSpace;

    protected WidgetContentFullPlayerBinding(Object obj, View view, int i, ImageButton imageButton, ImageButton imageButton2, ImageButton imageButton3, ImageView imageView, ImageButton imageButton4, TextView textView, ImageButton imageButton5, TextView textView2, RelativeLayout relativeLayout, RelativeLayout relativeLayout2) {
        super(obj, view, i);
        this.backward = imageButton;
        this.forward = imageButton2;
        this.playPause = imageButton3;
        this.progressRound = imageView;
        this.seekForward = imageButton4;
        this.seekForwardText = textView;
        this.seekRewind = imageButton5;
        this.seekRewindText = textView2;
        this.widgetLeftSpace = relativeLayout;
        this.widgetRightSpace = relativeLayout2;
    }

    public static WidgetContentFullPlayerBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static WidgetContentFullPlayerBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (WidgetContentFullPlayerBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.widget_content_full_player, viewGroup, z, obj);
    }

    public static WidgetContentFullPlayerBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static WidgetContentFullPlayerBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (WidgetContentFullPlayerBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.widget_content_full_player, (ViewGroup) null, false, obj);
    }

    public static WidgetContentFullPlayerBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static WidgetContentFullPlayerBinding bind(View view, Object obj) {
        return (WidgetContentFullPlayerBinding) bind(obj, view, R.layout.widget_content_full_player);
    }
}
