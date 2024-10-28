package org.videolan.television.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import org.videolan.television.R;
import org.videolan.television.ui.views.ColorPickerItem;

public abstract class ColorPickerItemBinding extends ViewDataBinding {
    public final ColorPickerItem colorPicker;

    protected ColorPickerItemBinding(Object obj, View view, int i, ColorPickerItem colorPickerItem) {
        super(obj, view, i);
        this.colorPicker = colorPickerItem;
    }

    public static ColorPickerItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ColorPickerItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ColorPickerItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.color_picker_item, viewGroup, z, obj);
    }

    public static ColorPickerItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ColorPickerItemBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ColorPickerItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.color_picker_item, (ViewGroup) null, false, obj);
    }

    public static ColorPickerItemBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ColorPickerItemBinding bind(View view, Object obj) {
        return (ColorPickerItemBinding) bind(obj, view, R.layout.color_picker_item);
    }
}
