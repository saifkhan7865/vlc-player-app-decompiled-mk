package org.videolan.moviepedia.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.textfield.TextInputLayout;
import org.videolan.moviepedia.R;
import org.videolan.moviepedia.ui.MediaScrapingActivity;

public abstract class MoviepediaActivityBinding extends ViewDataBinding {
    public final ImageView imageView8;
    @Bindable
    protected MediaScrapingActivity.ClickHandler mHandler;
    public final RecyclerView nextResults;
    public final TextInputLayout searchEditLayout;
    public final EditText searchEditText;

    public abstract void setHandler(MediaScrapingActivity.ClickHandler clickHandler);

    protected MoviepediaActivityBinding(Object obj, View view, int i, ImageView imageView, RecyclerView recyclerView, TextInputLayout textInputLayout, EditText editText) {
        super(obj, view, i);
        this.imageView8 = imageView;
        this.nextResults = recyclerView;
        this.searchEditLayout = textInputLayout;
        this.searchEditText = editText;
    }

    public MediaScrapingActivity.ClickHandler getHandler() {
        return this.mHandler;
    }

    public static MoviepediaActivityBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MoviepediaActivityBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (MoviepediaActivityBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.moviepedia_activity, viewGroup, z, obj);
    }

    public static MoviepediaActivityBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MoviepediaActivityBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (MoviepediaActivityBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.moviepedia_activity, (ViewGroup) null, false, obj);
    }

    public static MoviepediaActivityBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MoviepediaActivityBinding bind(View view, Object obj) {
        return (MoviepediaActivityBinding) bind(obj, view, R.layout.moviepedia_activity);
    }
}
