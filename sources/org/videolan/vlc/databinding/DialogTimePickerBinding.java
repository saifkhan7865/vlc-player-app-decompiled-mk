package org.videolan.vlc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import org.videolan.vlc.R;

public abstract class DialogTimePickerBinding extends ViewDataBinding {
    public final LinearLayout linearLayout2;
    public final LinearLayout linearLayout3;
    public final LinearLayout linearLayout4;
    public final LinearLayout linearLayout5;
    public final TextView timPic0;
    public final TextView timPic00;
    public final TextView timPic1;
    public final TextView timPic2;
    public final TextView timPic3;
    public final TextView timPic30;
    public final TextView timPic4;
    public final TextView timPic5;
    public final TextView timPic6;
    public final TextView timPic7;
    public final TextView timPic8;
    public final TextView timPic9;
    public final ImageButton timPicDelete;
    public final Button timPicDeleteCurrent;
    public final Button timPicOk;
    public final CheckBox timPicResetCheckbox;
    public final TextView timPicTimetojump;
    public final TextView timPicTitle;
    public final CheckBox timPicWaitCheckbox;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected DialogTimePickerBinding(Object obj, View view, int i, LinearLayout linearLayout, LinearLayout linearLayout6, LinearLayout linearLayout7, LinearLayout linearLayout8, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10, TextView textView11, TextView textView12, ImageButton imageButton, Button button, Button button2, CheckBox checkBox, TextView textView13, TextView textView14, CheckBox checkBox2) {
        super(obj, view, i);
        this.linearLayout2 = linearLayout;
        this.linearLayout3 = linearLayout6;
        this.linearLayout4 = linearLayout7;
        this.linearLayout5 = linearLayout8;
        this.timPic0 = textView;
        this.timPic00 = textView2;
        this.timPic1 = textView3;
        this.timPic2 = textView4;
        this.timPic3 = textView5;
        this.timPic30 = textView6;
        this.timPic4 = textView7;
        this.timPic5 = textView8;
        this.timPic6 = textView9;
        this.timPic7 = textView10;
        this.timPic8 = textView11;
        this.timPic9 = textView12;
        this.timPicDelete = imageButton;
        this.timPicDeleteCurrent = button;
        this.timPicOk = button2;
        this.timPicResetCheckbox = checkBox;
        this.timPicTimetojump = textView13;
        this.timPicTitle = textView14;
        this.timPicWaitCheckbox = checkBox2;
    }

    public static DialogTimePickerBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogTimePickerBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (DialogTimePickerBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_time_picker, viewGroup, z, obj);
    }

    public static DialogTimePickerBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogTimePickerBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (DialogTimePickerBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_time_picker, (ViewGroup) null, false, obj);
    }

    public static DialogTimePickerBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogTimePickerBinding bind(View view, Object obj) {
        return (DialogTimePickerBinding) bind(obj, view, R.layout.dialog_time_picker);
    }
}
