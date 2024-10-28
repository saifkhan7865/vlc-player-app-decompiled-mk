package org.videolan.vlc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import org.videolan.vlc.R;

public abstract class MlWizardActivityBinding extends ViewDataBinding {
    public final Switch wizardCheckScan;
    public final TextView wizardDescription;
    public final Button wizardValidate;

    protected MlWizardActivityBinding(Object obj, View view, int i, Switch switchR, TextView textView, Button button) {
        super(obj, view, i);
        this.wizardCheckScan = switchR;
        this.wizardDescription = textView;
        this.wizardValidate = button;
    }

    public static MlWizardActivityBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MlWizardActivityBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (MlWizardActivityBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.ml_wizard_activity, viewGroup, z, obj);
    }

    public static MlWizardActivityBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MlWizardActivityBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (MlWizardActivityBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.ml_wizard_activity, (ViewGroup) null, false, obj);
    }

    public static MlWizardActivityBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MlWizardActivityBinding bind(View view, Object obj) {
        return (MlWizardActivityBinding) bind(obj, view, R.layout.ml_wizard_activity);
    }
}
