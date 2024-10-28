package org.videolan.vlc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import org.videolan.vlc.R;

public abstract class BrowserItemSeparatorBinding extends ViewDataBinding {
    @Bindable
    protected String mTitle;
    public final TextView separatorTitle;

    public abstract void setTitle(String str);

    protected BrowserItemSeparatorBinding(Object obj, View view, int i, TextView textView) {
        super(obj, view, i);
        this.separatorTitle = textView;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public static BrowserItemSeparatorBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BrowserItemSeparatorBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (BrowserItemSeparatorBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.browser_item_separator, viewGroup, z, obj);
    }

    public static BrowserItemSeparatorBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BrowserItemSeparatorBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (BrowserItemSeparatorBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.browser_item_separator, (ViewGroup) null, false, obj);
    }

    public static BrowserItemSeparatorBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BrowserItemSeparatorBinding bind(View view, Object obj) {
        return (BrowserItemSeparatorBinding) bind(obj, view, R.layout.browser_item_separator);
    }
}
