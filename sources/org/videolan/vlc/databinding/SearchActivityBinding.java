package org.videolan.vlc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.textfield.TextInputLayout;
import org.videolan.medialibrary.media.SearchAggregate;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.SearchActivity;

public abstract class SearchActivityBinding extends ViewDataBinding {
    public final RecyclerView albumsResults;
    public final RecyclerView artistsResults;
    public final RecyclerView genresResults;
    @Bindable
    protected SearchActivity.ClickHandler mHandler;
    @Bindable
    protected SearchAggregate mSearchAggregate;
    public final RecyclerView othersResults;
    public final RecyclerView playlistsResults;
    public final LinearLayout resultsContainer;
    public final TextInputLayout searchEditLayout;
    public final EditText searchEditText;
    public final RecyclerView songsResults;

    public abstract void setHandler(SearchActivity.ClickHandler clickHandler);

    public abstract void setSearchAggregate(SearchAggregate searchAggregate);

    protected SearchActivityBinding(Object obj, View view, int i, RecyclerView recyclerView, RecyclerView recyclerView2, RecyclerView recyclerView3, RecyclerView recyclerView4, RecyclerView recyclerView5, LinearLayout linearLayout, TextInputLayout textInputLayout, EditText editText, RecyclerView recyclerView6) {
        super(obj, view, i);
        this.albumsResults = recyclerView;
        this.artistsResults = recyclerView2;
        this.genresResults = recyclerView3;
        this.othersResults = recyclerView4;
        this.playlistsResults = recyclerView5;
        this.resultsContainer = linearLayout;
        this.searchEditLayout = textInputLayout;
        this.searchEditText = editText;
        this.songsResults = recyclerView6;
    }

    public SearchAggregate getSearchAggregate() {
        return this.mSearchAggregate;
    }

    public SearchActivity.ClickHandler getHandler() {
        return this.mHandler;
    }

    public static SearchActivityBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SearchActivityBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (SearchActivityBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.search_activity, viewGroup, z, obj);
    }

    public static SearchActivityBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SearchActivityBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (SearchActivityBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.search_activity, (ViewGroup) null, false, obj);
    }

    public static SearchActivityBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SearchActivityBinding bind(View view, Object obj) {
        return (SearchActivityBinding) bind(obj, view, R.layout.search_activity);
    }
}
