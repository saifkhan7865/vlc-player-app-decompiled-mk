package org.videolan.vlc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.google.android.material.card.MaterialCardView;
import org.videolan.vlc.R;

public abstract class DialogWhatsNewBinding extends ViewDataBinding {
    public final ImageView imageView18;
    public final MaterialCardView materialCardView;
    public final CheckBox neverAgain;
    public final TextView title;
    public final MaterialCardView webserverCard;
    public final Button webserverMore;

    protected DialogWhatsNewBinding(Object obj, View view, int i, ImageView imageView, MaterialCardView materialCardView2, CheckBox checkBox, TextView textView, MaterialCardView materialCardView3, Button button) {
        super(obj, view, i);
        this.imageView18 = imageView;
        this.materialCardView = materialCardView2;
        this.neverAgain = checkBox;
        this.title = textView;
        this.webserverCard = materialCardView3;
        this.webserverMore = button;
    }

    public static DialogWhatsNewBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogWhatsNewBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (DialogWhatsNewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_whats_new, viewGroup, z, obj);
    }

    public static DialogWhatsNewBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogWhatsNewBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (DialogWhatsNewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_whats_new, (ViewGroup) null, false, obj);
    }

    public static DialogWhatsNewBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogWhatsNewBinding bind(View view, Object obj) {
        return (DialogWhatsNewBinding) bind(obj, view, R.layout.dialog_whats_new);
    }
}
