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
import androidx.gridlayout.widget.GridLayout;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import org.videolan.vlc.R;

public abstract class PinCodeActivityBinding extends ViewDataBinding {
    public final Button cancelButton;
    public final ImageView imageView18;
    public final TextView keyboardButton0;
    public final TextView keyboardButton1;
    public final TextView keyboardButton2;
    public final TextView keyboardButton3;
    public final TextView keyboardButton4;
    public final TextView keyboardButton5;
    public final TextView keyboardButton6;
    public final TextView keyboardButton7;
    public final TextView keyboardButton8;
    public final TextView keyboardButton9;
    public final ImageView keyboardButtonClear;
    public final GridLayout keyboardGrid;
    public final Button nextButton;
    public final TextInputEditText pinCode1;
    public final TextInputEditText pinCode2;
    public final TextInputEditText pinCode3;
    public final TextInputEditText pinCode4;
    public final TextInputLayout pinCodeParent1;
    public final TextInputLayout pinCodeParent2;
    public final TextInputLayout pinCodeParent3;
    public final TextInputLayout pinCodeParent4;
    public final TextView pinCodeReason;
    public final TextView pinCodeTitle;
    public final Group pinGroup;
    public final Group sucessGroup;
    public final TextView sucessTitle;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected PinCodeActivityBinding(Object obj, View view, int i, Button button, ImageView imageView, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10, ImageView imageView2, GridLayout gridLayout, Button button2, TextInputEditText textInputEditText, TextInputEditText textInputEditText2, TextInputEditText textInputEditText3, TextInputEditText textInputEditText4, TextInputLayout textInputLayout, TextInputLayout textInputLayout2, TextInputLayout textInputLayout3, TextInputLayout textInputLayout4, TextView textView11, TextView textView12, Group group, Group group2, TextView textView13) {
        super(obj, view, i);
        this.cancelButton = button;
        this.imageView18 = imageView;
        this.keyboardButton0 = textView;
        this.keyboardButton1 = textView2;
        this.keyboardButton2 = textView3;
        this.keyboardButton3 = textView4;
        this.keyboardButton4 = textView5;
        this.keyboardButton5 = textView6;
        this.keyboardButton6 = textView7;
        this.keyboardButton7 = textView8;
        this.keyboardButton8 = textView9;
        this.keyboardButton9 = textView10;
        this.keyboardButtonClear = imageView2;
        this.keyboardGrid = gridLayout;
        this.nextButton = button2;
        this.pinCode1 = textInputEditText;
        this.pinCode2 = textInputEditText2;
        this.pinCode3 = textInputEditText3;
        this.pinCode4 = textInputEditText4;
        this.pinCodeParent1 = textInputLayout;
        this.pinCodeParent2 = textInputLayout2;
        this.pinCodeParent3 = textInputLayout3;
        this.pinCodeParent4 = textInputLayout4;
        this.pinCodeReason = textView11;
        this.pinCodeTitle = textView12;
        this.pinGroup = group;
        this.sucessGroup = group2;
        this.sucessTitle = textView13;
    }

    public static PinCodeActivityBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static PinCodeActivityBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (PinCodeActivityBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.pin_code_activity, viewGroup, z, obj);
    }

    public static PinCodeActivityBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static PinCodeActivityBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (PinCodeActivityBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.pin_code_activity, (ViewGroup) null, false, obj);
    }

    public static PinCodeActivityBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static PinCodeActivityBinding bind(View view, Object obj) {
        return (PinCodeActivityBinding) bind(obj, view, R.layout.pin_code_activity);
    }
}
