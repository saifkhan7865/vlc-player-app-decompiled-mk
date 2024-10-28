package org.videolan.vlc.databinding;

import android.text.TextUtils;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ScrollView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import org.videolan.libvlc.Dialog;
import org.videolan.vlc.BR;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.dialogs.VlcQuestionDialog;

public class VlcQuestionDialogBindingImpl extends VlcQuestionDialogBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private OnClickListenerImpl2 mHandlerOnAction1AndroidViewViewOnClickListener;
    private OnClickListenerImpl mHandlerOnAction2AndroidViewViewOnClickListener;
    private OnClickListenerImpl1 mHandlerOnCancelAndroidViewViewOnClickListener;
    private final ScrollView mboundView0;

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    public VlcQuestionDialogBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 5, sIncludes, sViewsWithIds));
    }

    private VlcQuestionDialogBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[4], objArr[3], objArr[2], objArr[1]);
        this.mDirtyFlags = -1;
        this.action1.setTag((Object) null);
        this.action2.setTag((Object) null);
        this.cancel.setTag((Object) null);
        ScrollView scrollView = objArr[0];
        this.mboundView0 = scrollView;
        scrollView.setTag((Object) null);
        this.text.setTag((Object) null);
        setRootTag(view);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 4;
        }
        requestRebind();
    }

    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return false;
        }
    }

    public boolean setVariable(int i, Object obj) {
        if (BR.handler == i) {
            setHandler((VlcQuestionDialog) obj);
        } else if (BR.dialog != i) {
            return false;
        } else {
            setDialog((Dialog.QuestionDialog) obj);
        }
        return true;
    }

    public void setHandler(VlcQuestionDialog vlcQuestionDialog) {
        this.mHandler = vlcQuestionDialog;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.handler);
        super.requestRebind();
    }

    public void setDialog(Dialog.QuestionDialog questionDialog) {
        this.mDialog = questionDialog;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(BR.dialog);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long j;
        OnClickListenerImpl1 onClickListenerImpl1;
        OnClickListenerImpl onClickListenerImpl;
        OnClickListenerImpl2 onClickListenerImpl2;
        String str;
        String str2;
        int i;
        int i2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        VlcQuestionDialog vlcQuestionDialog = this.mHandler;
        Dialog.QuestionDialog questionDialog = this.mDialog;
        if ((j & 5) == 0 || vlcQuestionDialog == null) {
            onClickListenerImpl2 = null;
            onClickListenerImpl = null;
            onClickListenerImpl1 = null;
        } else {
            OnClickListenerImpl onClickListenerImpl3 = this.mHandlerOnAction2AndroidViewViewOnClickListener;
            if (onClickListenerImpl3 == null) {
                onClickListenerImpl3 = new OnClickListenerImpl();
                this.mHandlerOnAction2AndroidViewViewOnClickListener = onClickListenerImpl3;
            }
            onClickListenerImpl = onClickListenerImpl3.setValue(vlcQuestionDialog);
            OnClickListenerImpl1 onClickListenerImpl12 = this.mHandlerOnCancelAndroidViewViewOnClickListener;
            if (onClickListenerImpl12 == null) {
                onClickListenerImpl12 = new OnClickListenerImpl1();
                this.mHandlerOnCancelAndroidViewViewOnClickListener = onClickListenerImpl12;
            }
            onClickListenerImpl1 = onClickListenerImpl12.setValue(vlcQuestionDialog);
            OnClickListenerImpl2 onClickListenerImpl22 = this.mHandlerOnAction1AndroidViewViewOnClickListener;
            if (onClickListenerImpl22 == null) {
                onClickListenerImpl22 = new OnClickListenerImpl2();
                this.mHandlerOnAction1AndroidViewViewOnClickListener = onClickListenerImpl22;
            }
            onClickListenerImpl2 = onClickListenerImpl22.setValue(vlcQuestionDialog);
        }
        long j2 = j & 6;
        int i3 = 0;
        if (j2 != 0) {
            if (questionDialog != null) {
                str7 = questionDialog.getAction1Text();
                str6 = questionDialog.getText();
                str5 = questionDialog.getAction2Text();
                str4 = questionDialog.getCancelText();
            } else {
                str4 = null;
                str7 = null;
                str6 = null;
                str5 = null;
            }
            boolean isEmpty = TextUtils.isEmpty(str7);
            boolean isEmpty2 = TextUtils.isEmpty(str5);
            boolean isEmpty3 = TextUtils.isEmpty(str4);
            if (j2 != 0) {
                j |= isEmpty ? 64 : 32;
            }
            if ((j & 6) != 0) {
                j |= isEmpty2 ? 16 : 8;
            }
            if ((j & 6) != 0) {
                j |= isEmpty3 ? 256 : 128;
            }
            i = isEmpty ? 8 : 0;
            if (isEmpty2) {
                i3 = 8;
            }
            i2 = i3;
            str3 = str7;
            str2 = str6;
            str = str5;
            i3 = isEmpty3;
        } else {
            str4 = null;
            str3 = null;
            i2 = 0;
            i = 0;
            str2 = null;
            str = null;
        }
        long j3 = 6 & j;
        String string = j3 != 0 ? i3 != 0 ? this.cancel.getResources().getString(R.string.cancel) : str4 : null;
        if ((j & 5) != 0) {
            this.action1.setOnClickListener(onClickListenerImpl2);
            this.action2.setOnClickListener(onClickListenerImpl);
            this.cancel.setOnClickListener(onClickListenerImpl1);
        }
        if (j3 != 0) {
            TextViewBindingAdapter.setText(this.action1, str3);
            this.action1.setVisibility(i);
            TextViewBindingAdapter.setText(this.action2, str);
            this.action2.setVisibility(i2);
            TextViewBindingAdapter.setText(this.cancel, string);
            TextViewBindingAdapter.setText(this.text, str2);
        }
    }

    public static class OnClickListenerImpl implements View.OnClickListener {
        private VlcQuestionDialog value;

        public OnClickListenerImpl setValue(VlcQuestionDialog vlcQuestionDialog) {
            this.value = vlcQuestionDialog;
            if (vlcQuestionDialog == null) {
                return null;
            }
            return this;
        }

        public void onClick(View view) {
            this.value.onAction2(view);
        }
    }

    public static class OnClickListenerImpl1 implements View.OnClickListener {
        private VlcQuestionDialog value;

        public OnClickListenerImpl1 setValue(VlcQuestionDialog vlcQuestionDialog) {
            this.value = vlcQuestionDialog;
            if (vlcQuestionDialog == null) {
                return null;
            }
            return this;
        }

        public void onClick(View view) {
            this.value.onCancel(view);
        }
    }

    public static class OnClickListenerImpl2 implements View.OnClickListener {
        private VlcQuestionDialog value;

        public OnClickListenerImpl2 setValue(VlcQuestionDialog vlcQuestionDialog) {
            this.value = vlcQuestionDialog;
            if (vlcQuestionDialog == null) {
                return null;
            }
            return this;
        }

        public void onClick(View view) {
            this.value.onAction1(view);
        }
    }
}
