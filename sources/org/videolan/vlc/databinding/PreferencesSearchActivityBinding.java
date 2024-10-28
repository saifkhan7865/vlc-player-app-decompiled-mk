package org.videolan.vlc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import org.videolan.vlc.R;

public abstract class PreferencesSearchActivityBinding extends ViewDataBinding {
    public final ImageView closeButton;
    public final RecyclerView list;
    @Bindable
    protected Boolean mShowTranslation;
    public final EditText searchText;
    public final View separator;
    public final ImageView translateButton;

    public abstract void setShowTranslation(Boolean bool);

    protected PreferencesSearchActivityBinding(Object obj, View view, int i, ImageView imageView, RecyclerView recyclerView, EditText editText, View view2, ImageView imageView2) {
        super(obj, view, i);
        this.closeButton = imageView;
        this.list = recyclerView;
        this.searchText = editText;
        this.separator = view2;
        this.translateButton = imageView2;
    }

    public Boolean getShowTranslation() {
        return this.mShowTranslation;
    }

    public static PreferencesSearchActivityBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static PreferencesSearchActivityBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (PreferencesSearchActivityBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.preferences_search_activity, viewGroup, z, obj);
    }

    public static PreferencesSearchActivityBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static PreferencesSearchActivityBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (PreferencesSearchActivityBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.preferences_search_activity, (ViewGroup) null, false, obj);
    }

    public static PreferencesSearchActivityBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static PreferencesSearchActivityBinding bind(View view, Object obj) {
        return (PreferencesSearchActivityBinding) bind(obj, view, R.layout.preferences_search_activity);
    }
}
