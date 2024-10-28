package org.videolan.vlc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import org.videolan.libvlc.Dialog;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.dialogs.VlcQuestionDialog;

public abstract class VlcQuestionDialogBinding extends ViewDataBinding {
    public final Button action1;
    public final Button action2;
    public final Button cancel;
    @Bindable
    protected Dialog.QuestionDialog mDialog;
    @Bindable
    protected VlcQuestionDialog mHandler;
    public final TextView text;

    public abstract void setDialog(Dialog.QuestionDialog questionDialog);

    public abstract void setHandler(VlcQuestionDialog vlcQuestionDialog);

    protected VlcQuestionDialogBinding(Object obj, View view, int i, Button button, Button button2, Button button3, TextView textView) {
        super(obj, view, i);
        this.action1 = button;
        this.action2 = button2;
        this.cancel = button3;
        this.text = textView;
    }

    public Dialog.QuestionDialog getDialog() {
        return this.mDialog;
    }

    public VlcQuestionDialog getHandler() {
        return this.mHandler;
    }

    public static VlcQuestionDialogBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static VlcQuestionDialogBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (VlcQuestionDialogBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.vlc_question_dialog, viewGroup, z, obj);
    }

    public static VlcQuestionDialogBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static VlcQuestionDialogBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (VlcQuestionDialogBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.vlc_question_dialog, (ViewGroup) null, false, obj);
    }

    public static VlcQuestionDialogBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static VlcQuestionDialogBinding bind(View view, Object obj) {
        return (VlcQuestionDialogBinding) bind(obj, view, R.layout.vlc_question_dialog);
    }
}
