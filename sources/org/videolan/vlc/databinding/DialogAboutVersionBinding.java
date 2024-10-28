package org.videolan.vlc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.constraintlayout.widget.Barrier;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import org.videolan.vlc.R;

public abstract class DialogAboutVersionBinding extends ViewDataBinding {
    public final TextView changelog;
    public final Barrier compilationBarrier;
    public final TextView compiledBy;
    public final View divider;
    public final View divider2;
    public final TextView libvlcRevision;
    public final TextView libvlcVersion;
    public final TextView medias2;
    public final Button moreButton;
    public final TextView remoteAccess;
    public final TextView remoteAccessHashTitle;
    public final TextView remoteAccessRevision;
    public final TextView remoteAccessVersion;
    public final TextView revision;
    public final TextView signedBy;
    public final TextView textView2;
    public final TextView textView28;
    public final TextView textView29;
    public final TextView textView30;
    public final TextView textView31;
    public final TextView textView35;
    public final TextView version;
    public final TextView vlcRevision;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected DialogAboutVersionBinding(Object obj, View view, int i, TextView textView, Barrier barrier, TextView textView3, View view2, View view3, TextView textView4, TextView textView5, TextView textView6, Button button, TextView textView7, TextView textView8, TextView textView9, TextView textView10, TextView textView11, TextView textView12, TextView textView13, TextView textView14, TextView textView15, TextView textView16, TextView textView17, TextView textView18, TextView textView19, TextView textView20) {
        super(obj, view, i);
        this.changelog = textView;
        this.compilationBarrier = barrier;
        this.compiledBy = textView3;
        this.divider = view2;
        this.divider2 = view3;
        this.libvlcRevision = textView4;
        this.libvlcVersion = textView5;
        this.medias2 = textView6;
        this.moreButton = button;
        this.remoteAccess = textView7;
        this.remoteAccessHashTitle = textView8;
        this.remoteAccessRevision = textView9;
        this.remoteAccessVersion = textView10;
        this.revision = textView11;
        this.signedBy = textView12;
        this.textView2 = textView13;
        this.textView28 = textView14;
        this.textView29 = textView15;
        this.textView30 = textView16;
        this.textView31 = textView17;
        this.textView35 = textView18;
        this.version = textView19;
        this.vlcRevision = textView20;
    }

    public static DialogAboutVersionBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogAboutVersionBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (DialogAboutVersionBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_about_version, viewGroup, z, obj);
    }

    public static DialogAboutVersionBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogAboutVersionBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (DialogAboutVersionBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_about_version, (ViewGroup) null, false, obj);
    }

    public static DialogAboutVersionBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogAboutVersionBinding bind(View view, Object obj) {
        return (DialogAboutVersionBinding) bind(obj, view, R.layout.dialog_about_version);
    }
}
