package org.videolan.vlc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import org.videolan.medialibrary.media.DummyItem;
import org.videolan.vlc.R;

public abstract class AudioBrowserSeparatorBinding extends ViewDataBinding {
    @Bindable
    protected DummyItem mItem;
    public final TextView title;

    public abstract void setItem(DummyItem dummyItem);

    protected AudioBrowserSeparatorBinding(Object obj, View view, int i, TextView textView) {
        super(obj, view, i);
        this.title = textView;
    }

    public DummyItem getItem() {
        return this.mItem;
    }

    public static AudioBrowserSeparatorBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudioBrowserSeparatorBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (AudioBrowserSeparatorBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.audio_browser_separator, viewGroup, z, obj);
    }

    public static AudioBrowserSeparatorBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudioBrowserSeparatorBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (AudioBrowserSeparatorBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.audio_browser_separator, (ViewGroup) null, false, obj);
    }

    public static AudioBrowserSeparatorBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudioBrowserSeparatorBinding bind(View view, Object obj) {
        return (AudioBrowserSeparatorBinding) bind(obj, view, R.layout.audio_browser_separator);
    }
}
