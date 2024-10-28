package org.videolan.television.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import org.videolan.television.R;
import org.videolan.television.ui.views.ColorPickerItem;

public abstract class ActivityColorPickerBinding extends ViewDataBinding {
    public final RecyclerView colorGrid;
    public final Button colorPickerButtonCancel;
    public final Button colorPickerButtonOk;
    public final TextView colorPickerTitle;
    public final ColorPickerItem newColor;
    public final TextView newColorText;
    public final ColorPickerItem oldColor;
    public final TextView previousColorText;

    protected ActivityColorPickerBinding(Object obj, View view, int i, RecyclerView recyclerView, Button button, Button button2, TextView textView, ColorPickerItem colorPickerItem, TextView textView2, ColorPickerItem colorPickerItem2, TextView textView3) {
        super(obj, view, i);
        this.colorGrid = recyclerView;
        this.colorPickerButtonCancel = button;
        this.colorPickerButtonOk = button2;
        this.colorPickerTitle = textView;
        this.newColor = colorPickerItem;
        this.newColorText = textView2;
        this.oldColor = colorPickerItem2;
        this.previousColorText = textView3;
    }

    public static ActivityColorPickerBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityColorPickerBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ActivityColorPickerBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_color_picker, viewGroup, z, obj);
    }

    public static ActivityColorPickerBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityColorPickerBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ActivityColorPickerBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_color_picker, (ViewGroup) null, false, obj);
    }

    public static ActivityColorPickerBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityColorPickerBinding bind(View view, Object obj) {
        return (ActivityColorPickerBinding) bind(obj, view, R.layout.activity_color_picker);
    }
}
