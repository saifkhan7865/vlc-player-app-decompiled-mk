package org.videolan.vlc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.preferences.search.PreferenceItem;
import org.videolan.vlc.gui.preferences.search.PreferenceItemAdapter;

public abstract class PreferenceItemBinding extends ViewDataBinding {
    public final TextView categoryText;
    @Bindable
    protected String mCategory;
    @Bindable
    protected String mDescription;
    @Bindable
    protected PreferenceItemAdapter.ClickHandler mHandler;
    @Bindable
    protected PreferenceItem mItem;
    @Bindable
    protected String mQuery;
    @Bindable
    protected String mTitle;
    public final View separator;
    public final TextView subtitle;
    public final TextView textView16;

    public abstract void setCategory(String str);

    public abstract void setDescription(String str);

    public abstract void setHandler(PreferenceItemAdapter.ClickHandler clickHandler);

    public abstract void setItem(PreferenceItem preferenceItem);

    public abstract void setQuery(String str);

    public abstract void setTitle(String str);

    protected PreferenceItemBinding(Object obj, View view, int i, TextView textView, View view2, TextView textView2, TextView textView3) {
        super(obj, view, i);
        this.categoryText = textView;
        this.separator = view2;
        this.subtitle = textView2;
        this.textView16 = textView3;
    }

    public PreferenceItem getItem() {
        return this.mItem;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public String getCategory() {
        return this.mCategory;
    }

    public String getQuery() {
        return this.mQuery;
    }

    public PreferenceItemAdapter.ClickHandler getHandler() {
        return this.mHandler;
    }

    public static PreferenceItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static PreferenceItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (PreferenceItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.preference_item, viewGroup, z, obj);
    }

    public static PreferenceItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static PreferenceItemBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (PreferenceItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.preference_item, (ViewGroup) null, false, obj);
    }

    public static PreferenceItemBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static PreferenceItemBinding bind(View view, Object obj) {
        return (PreferenceItemBinding) bind(obj, view, R.layout.preference_item);
    }
}
