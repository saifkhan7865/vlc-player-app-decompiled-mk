package org.videolan.vlc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.Group;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.view.WidgetHandleView;

public abstract class DialogWidgetExplanationBinding extends ViewDataBinding {
    public final ImageView imageView19;
    public final ImageView resizeLongTapIcon;
    public final Group step1;
    public final Group step2;
    public final Group step3;
    public final TextView textView32;
    public final TextView title;
    public final TextView widgetExplanationStep3Text;
    public final Button widgetNextButton;
    public final ImageView widgetResize;
    public final WidgetHandleView widgetResizeHandle;
    public final TextView widgetResizeText;
    public final ImageView widgetSizes;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected DialogWidgetExplanationBinding(Object obj, View view, int i, ImageView imageView, ImageView imageView2, Group group, Group group2, Group group3, TextView textView, TextView textView2, TextView textView3, Button button, ImageView imageView3, WidgetHandleView widgetHandleView, TextView textView4, ImageView imageView4) {
        super(obj, view, i);
        this.imageView19 = imageView;
        this.resizeLongTapIcon = imageView2;
        this.step1 = group;
        this.step2 = group2;
        this.step3 = group3;
        this.textView32 = textView;
        this.title = textView2;
        this.widgetExplanationStep3Text = textView3;
        this.widgetNextButton = button;
        this.widgetResize = imageView3;
        this.widgetResizeHandle = widgetHandleView;
        this.widgetResizeText = textView4;
        this.widgetSizes = imageView4;
    }

    public static DialogWidgetExplanationBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogWidgetExplanationBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (DialogWidgetExplanationBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_widget_explanation, viewGroup, z, obj);
    }

    public static DialogWidgetExplanationBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogWidgetExplanationBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (DialogWidgetExplanationBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_widget_explanation, (ViewGroup) null, false, obj);
    }

    public static DialogWidgetExplanationBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogWidgetExplanationBinding bind(View view, Object obj) {
        return (DialogWidgetExplanationBinding) bind(obj, view, R.layout.dialog_widget_explanation);
    }
}
