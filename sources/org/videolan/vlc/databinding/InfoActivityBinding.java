package org.videolan.vlc.databinding;

import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.ViewStubCompat;
import androidx.constraintlayout.widget.Group;
import androidx.constraintlayout.widget.Guideline;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.vlc.R;

public abstract class InfoActivityBinding extends ViewDataBinding {
    public final AppBarLayout appbar;
    public final RecyclerView ariane;
    public final ViewStubCompat audioPlayerTips;
    public final CollapsingToolbarLayout collapsingToolbar;
    public final NestedScrollView container;
    public final CoordinatorLayout coordinator;
    public final Button directoryNotScannedButton;
    public final TextView directoryNotScannedText;
    public final ImageView extraIcon;
    public final TextView extraTitle;
    public final TextView extraValue;
    public final FloatingActionButton fab;
    public final Group fileSizeViews;
    public final Guideline guideline10;
    public final Guideline guideline9;
    public final ProgressBar imageProgress;
    public final ImageView infoSubtitles;
    public final ImageView lengthIcon;
    public final TextView lengthTitle;
    public final TextView lengthValue;
    public final RecyclerView list;
    @Bindable
    protected BitmapDrawable mCover;
    @Bindable
    protected String mExtraTitleText;
    @Bindable
    protected String mExtraValueText;
    @Bindable
    protected MediaLibraryItem mItem;
    @Bindable
    protected Long mLength;
    @Bindable
    protected String mPath;
    @Bindable
    protected int mProgress;
    @Bindable
    protected String mResolution;
    @Bindable
    protected boolean mScanned;
    @Bindable
    protected String mSizeTitleText;
    @Bindable
    protected String mSizeValueContentDescription;
    @Bindable
    protected String mSizeValueText;
    public final Toolbar mainToolbar;
    public final TextView mlItemResolution;
    public final ImageView playlistCover;
    public final ImageView sizeIcon;
    public final TextView sizeTitle;
    public final TextView sizeValue;

    public abstract void setCover(BitmapDrawable bitmapDrawable);

    public abstract void setExtraTitleText(String str);

    public abstract void setExtraValueText(String str);

    public abstract void setItem(MediaLibraryItem mediaLibraryItem);

    public abstract void setLength(Long l);

    public abstract void setPath(String str);

    public abstract void setProgress(int i);

    public abstract void setResolution(String str);

    public abstract void setScanned(boolean z);

    public abstract void setSizeTitleText(String str);

    public abstract void setSizeValueContentDescription(String str);

    public abstract void setSizeValueText(String str);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected InfoActivityBinding(Object obj, View view, int i, AppBarLayout appBarLayout, RecyclerView recyclerView, ViewStubCompat viewStubCompat, CollapsingToolbarLayout collapsingToolbarLayout, NestedScrollView nestedScrollView, CoordinatorLayout coordinatorLayout, Button button, TextView textView, ImageView imageView, TextView textView2, TextView textView3, FloatingActionButton floatingActionButton, Group group, Guideline guideline, Guideline guideline2, ProgressBar progressBar, ImageView imageView2, ImageView imageView3, TextView textView4, TextView textView5, RecyclerView recyclerView2, Toolbar toolbar, TextView textView6, ImageView imageView4, ImageView imageView5, TextView textView7, TextView textView8) {
        super(obj, view, i);
        this.appbar = appBarLayout;
        this.ariane = recyclerView;
        this.audioPlayerTips = viewStubCompat;
        this.collapsingToolbar = collapsingToolbarLayout;
        this.container = nestedScrollView;
        this.coordinator = coordinatorLayout;
        this.directoryNotScannedButton = button;
        this.directoryNotScannedText = textView;
        this.extraIcon = imageView;
        this.extraTitle = textView2;
        this.extraValue = textView3;
        this.fab = floatingActionButton;
        this.fileSizeViews = group;
        this.guideline10 = guideline;
        this.guideline9 = guideline2;
        this.imageProgress = progressBar;
        this.infoSubtitles = imageView2;
        this.lengthIcon = imageView3;
        this.lengthTitle = textView4;
        this.lengthValue = textView5;
        this.list = recyclerView2;
        this.mainToolbar = toolbar;
        this.mlItemResolution = textView6;
        this.playlistCover = imageView4;
        this.sizeIcon = imageView5;
        this.sizeTitle = textView7;
        this.sizeValue = textView8;
    }

    public MediaLibraryItem getItem() {
        return this.mItem;
    }

    public BitmapDrawable getCover() {
        return this.mCover;
    }

    public Long getLength() {
        return this.mLength;
    }

    public String getPath() {
        return this.mPath;
    }

    public String getSizeTitleText() {
        return this.mSizeTitleText;
    }

    public String getExtraTitleText() {
        return this.mExtraTitleText;
    }

    public String getSizeValueText() {
        return this.mSizeValueText;
    }

    public String getSizeValueContentDescription() {
        return this.mSizeValueContentDescription;
    }

    public String getExtraValueText() {
        return this.mExtraValueText;
    }

    public int getProgress() {
        return this.mProgress;
    }

    public String getResolution() {
        return this.mResolution;
    }

    public boolean getScanned() {
        return this.mScanned;
    }

    public static InfoActivityBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static InfoActivityBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (InfoActivityBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.info_activity, viewGroup, z, obj);
    }

    public static InfoActivityBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static InfoActivityBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (InfoActivityBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.info_activity, (ViewGroup) null, false, obj);
    }

    public static InfoActivityBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static InfoActivityBinding bind(View view, Object obj) {
        return (InfoActivityBinding) bind(obj, view, R.layout.info_activity);
    }
}
