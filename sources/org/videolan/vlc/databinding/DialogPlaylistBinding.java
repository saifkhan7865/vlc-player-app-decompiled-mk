package org.videolan.vlc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.widget.SwitchCompat;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.textfield.TextInputLayout;
import org.videolan.vlc.R;

public abstract class DialogPlaylistBinding extends ViewDataBinding {
    public final FrameLayout dialogListContainer;
    public final Button dialogPlaylistCreate;
    public final TextInputLayout dialogPlaylistName;
    public final Button dialogPlaylistSave;
    public final TextView empty;
    public final RecyclerView list;
    @Bindable
    protected String mFilesText;
    @Bindable
    protected Boolean mIsLoading;
    public final TextView medias;
    public final ProgressBar progressBar2;
    public final SwitchCompat replaceSwitch;
    public final TextView selectedPlaylistCount;
    public final TextView textView9;

    public abstract void setFilesText(String str);

    public abstract void setIsLoading(Boolean bool);

    protected DialogPlaylistBinding(Object obj, View view, int i, FrameLayout frameLayout, Button button, TextInputLayout textInputLayout, Button button2, TextView textView, RecyclerView recyclerView, TextView textView2, ProgressBar progressBar, SwitchCompat switchCompat, TextView textView3, TextView textView4) {
        super(obj, view, i);
        this.dialogListContainer = frameLayout;
        this.dialogPlaylistCreate = button;
        this.dialogPlaylistName = textInputLayout;
        this.dialogPlaylistSave = button2;
        this.empty = textView;
        this.list = recyclerView;
        this.medias = textView2;
        this.progressBar2 = progressBar;
        this.replaceSwitch = switchCompat;
        this.selectedPlaylistCount = textView3;
        this.textView9 = textView4;
    }

    public String getFilesText() {
        return this.mFilesText;
    }

    public Boolean getIsLoading() {
        return this.mIsLoading;
    }

    public static DialogPlaylistBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogPlaylistBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (DialogPlaylistBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_playlist, viewGroup, z, obj);
    }

    public static DialogPlaylistBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogPlaylistBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (DialogPlaylistBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_playlist, (ViewGroup) null, false, obj);
    }

    public static DialogPlaylistBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogPlaylistBinding bind(View view, Object obj) {
        return (DialogPlaylistBinding) bind(obj, view, R.layout.dialog_playlist);
    }
}
