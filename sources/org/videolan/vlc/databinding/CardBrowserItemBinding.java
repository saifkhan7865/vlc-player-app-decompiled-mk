package org.videolan.vlc.databinding;

import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.google.android.material.card.MaterialCardView;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.browser.BaseBrowserAdapter;
import org.videolan.vlc.gui.helpers.ThreeStatesCheckbox;
import org.videolan.vlc.gui.view.MiniVisualizer;

public abstract class CardBrowserItemBinding extends ViewDataBinding {
    public final ThreeStatesCheckbox browserCheckbox;
    public final MaterialCardView browserContainer;
    public final TextView dviIcon;
    public final ImageView itemBan;
    public final ImageView itemIcon;
    public final ImageView itemMore;
    @Bindable
    protected int mBgColor;
    @Bindable
    protected boolean mCheckEnabled;
    @Bindable
    protected BitmapDrawable mCover;
    @Bindable
    protected boolean mFavorite;
    @Bindable
    protected String mFilename;
    @Bindable
    protected boolean mForceCoverHiding;
    @Bindable
    protected boolean mHasContextMenu;
    @Bindable
    protected BaseBrowserAdapter.ViewHolder mHolder;
    @Bindable
    protected MediaLibraryItem mItem;
    @Bindable
    protected int mMax;
    @Bindable
    protected boolean mPlayed;
    @Bindable
    protected int mProgress;
    @Bindable
    protected String mProtocol;
    public final ProgressBar mlItemProgress;
    public final ImageView mlItemSeen;
    public final MiniVisualizer playing;
    public final TextView text;
    public final TextView title;

    public abstract void setBgColor(int i);

    public abstract void setCheckEnabled(boolean z);

    public abstract void setCover(BitmapDrawable bitmapDrawable);

    public abstract void setFavorite(boolean z);

    public abstract void setFilename(String str);

    public abstract void setForceCoverHiding(boolean z);

    public abstract void setHasContextMenu(boolean z);

    public abstract void setHolder(BaseBrowserAdapter.ViewHolder viewHolder);

    public abstract void setItem(MediaLibraryItem mediaLibraryItem);

    public abstract void setMax(int i);

    public abstract void setPlayed(boolean z);

    public abstract void setProgress(int i);

    public abstract void setProtocol(String str);

    protected CardBrowserItemBinding(Object obj, View view, int i, ThreeStatesCheckbox threeStatesCheckbox, MaterialCardView materialCardView, TextView textView, ImageView imageView, ImageView imageView2, ImageView imageView3, ProgressBar progressBar, ImageView imageView4, MiniVisualizer miniVisualizer, TextView textView2, TextView textView3) {
        super(obj, view, i);
        this.browserCheckbox = threeStatesCheckbox;
        this.browserContainer = materialCardView;
        this.dviIcon = textView;
        this.itemBan = imageView;
        this.itemIcon = imageView2;
        this.itemMore = imageView3;
        this.mlItemProgress = progressBar;
        this.mlItemSeen = imageView4;
        this.playing = miniVisualizer;
        this.text = textView2;
        this.title = textView3;
    }

    public BaseBrowserAdapter.ViewHolder getHolder() {
        return this.mHolder;
    }

    public MediaLibraryItem getItem() {
        return this.mItem;
    }

    public String getFilename() {
        return this.mFilename;
    }

    public boolean getHasContextMenu() {
        return this.mHasContextMenu;
    }

    public boolean getCheckEnabled() {
        return this.mCheckEnabled;
    }

    public boolean getPlayed() {
        return this.mPlayed;
    }

    public int getMax() {
        return this.mMax;
    }

    public int getProgress() {
        return this.mProgress;
    }

    public boolean getFavorite() {
        return this.mFavorite;
    }

    public String getProtocol() {
        return this.mProtocol;
    }

    public BitmapDrawable getCover() {
        return this.mCover;
    }

    public int getBgColor() {
        return this.mBgColor;
    }

    public boolean getForceCoverHiding() {
        return this.mForceCoverHiding;
    }

    public static CardBrowserItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static CardBrowserItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (CardBrowserItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.card_browser_item, viewGroup, z, obj);
    }

    public static CardBrowserItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static CardBrowserItemBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (CardBrowserItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.card_browser_item, (ViewGroup) null, false, obj);
    }

    public static CardBrowserItemBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static CardBrowserItemBinding bind(View view, Object obj) {
        return (CardBrowserItemBinding) bind(obj, view, R.layout.card_browser_item);
    }
}
