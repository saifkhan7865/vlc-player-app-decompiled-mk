package org.videolan.vlc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.helpers.PlayerOption;

public abstract class PlayerOptionItemBinding extends ViewDataBinding {
    @Bindable
    protected PlayerOption mOption;
    public final ImageView optionIcon;
    public final TextView optionTitle;

    public abstract void setOption(PlayerOption playerOption);

    protected PlayerOptionItemBinding(Object obj, View view, int i, ImageView imageView, TextView textView) {
        super(obj, view, i);
        this.optionIcon = imageView;
        this.optionTitle = textView;
    }

    public PlayerOption getOption() {
        return this.mOption;
    }

    public static PlayerOptionItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static PlayerOptionItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (PlayerOptionItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.player_option_item, viewGroup, z, obj);
    }

    public static PlayerOptionItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static PlayerOptionItemBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (PlayerOptionItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.player_option_item, (ViewGroup) null, false, obj);
    }

    public static PlayerOptionItemBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static PlayerOptionItemBinding bind(View view, Object obj) {
        return (PlayerOptionItemBinding) bind(obj, view, R.layout.player_option_item);
    }
}
