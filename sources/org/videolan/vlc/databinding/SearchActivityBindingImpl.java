package org.videolan.vlc.databinding;

import android.support.v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import org.videolan.medialibrary.Tools;
import org.videolan.medialibrary.interfaces.media.Album;
import org.videolan.medialibrary.interfaces.media.Artist;
import org.videolan.medialibrary.interfaces.media.Genre;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.interfaces.media.Playlist;
import org.videolan.medialibrary.media.SearchAggregate;
import org.videolan.vlc.BR;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.SearchActivity;

public class SearchActivityBindingImpl extends SearchActivityBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private OnClickListenerImpl mHandlerOnBackAndroidViewViewOnClickListener;
    private final LinearLayout mboundView0;
    private final ImageView mboundView1;
    private final View mboundView11;
    private final TextView mboundView12;
    private final View mboundView14;
    private final TextView mboundView15;
    private final View mboundView17;
    private final TextView mboundView18;
    private final NestedScrollView mboundView2;
    private final TextView mboundView20;
    private final TextView mboundView3;
    private final View mboundView5;
    private final TextView mboundView6;
    private final View mboundView8;
    private final TextView mboundView9;

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.search_edit_layout, 21);
        sparseIntArray.put(R.id.search_edit_text, 22);
        sparseIntArray.put(R.id.results_container, 23);
    }

    public SearchActivityBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 24, sIncludes, sViewsWithIds));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private SearchActivityBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[10], objArr[13], objArr[16], objArr[4], objArr[19], objArr[23], objArr[21], objArr[22], objArr[7]);
        this.mDirtyFlags = -1;
        this.albumsResults.setTag((Object) null);
        this.artistsResults.setTag((Object) null);
        this.genresResults.setTag((Object) null);
        LinearLayout linearLayout = objArr[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag((Object) null);
        ImageView imageView = objArr[1];
        this.mboundView1 = imageView;
        imageView.setTag((Object) null);
        View view2 = objArr[11];
        this.mboundView11 = view2;
        view2.setTag((Object) null);
        TextView textView = objArr[12];
        this.mboundView12 = textView;
        textView.setTag((Object) null);
        View view3 = objArr[14];
        this.mboundView14 = view3;
        view3.setTag((Object) null);
        TextView textView2 = objArr[15];
        this.mboundView15 = textView2;
        textView2.setTag((Object) null);
        View view4 = objArr[17];
        this.mboundView17 = view4;
        view4.setTag((Object) null);
        TextView textView3 = objArr[18];
        this.mboundView18 = textView3;
        textView3.setTag((Object) null);
        NestedScrollView nestedScrollView = objArr[2];
        this.mboundView2 = nestedScrollView;
        nestedScrollView.setTag((Object) null);
        TextView textView4 = objArr[20];
        this.mboundView20 = textView4;
        textView4.setTag((Object) null);
        TextView textView5 = objArr[3];
        this.mboundView3 = textView5;
        textView5.setTag((Object) null);
        View view5 = objArr[5];
        this.mboundView5 = view5;
        view5.setTag((Object) null);
        TextView textView6 = objArr[6];
        this.mboundView6 = textView6;
        textView6.setTag((Object) null);
        View view6 = objArr[8];
        this.mboundView8 = view6;
        view6.setTag((Object) null);
        TextView textView7 = objArr[9];
        this.mboundView9 = textView7;
        textView7.setTag((Object) null);
        this.othersResults.setTag((Object) null);
        this.playlistsResults.setTag((Object) null);
        this.songsResults.setTag((Object) null);
        setRootTag(view);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 4;
        }
        requestRebind();
    }

    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return false;
        }
    }

    public boolean setVariable(int i, Object obj) {
        if (BR.searchAggregate == i) {
            setSearchAggregate((SearchAggregate) obj);
        } else if (BR.handler != i) {
            return false;
        } else {
            setHandler((SearchActivity.ClickHandler) obj);
        }
        return true;
    }

    public void setSearchAggregate(SearchAggregate searchAggregate) {
        this.mSearchAggregate = searchAggregate;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.searchAggregate);
        super.requestRebind();
    }

    public void setHandler(SearchActivity.ClickHandler clickHandler) {
        this.mHandler = clickHandler;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(BR.handler);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long j;
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        OnClickListenerImpl onClickListenerImpl;
        Genre[] genreArr;
        Artist[] artistArr;
        MediaWrapper[] mediaWrapperArr;
        MediaWrapper[] mediaWrapperArr2;
        boolean z;
        Playlist[] playlistArr;
        Album[] albumArr;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        SearchAggregate searchAggregate = this.mSearchAggregate;
        SearchActivity.ClickHandler clickHandler = this.mHandler;
        long j2 = j & 5;
        int i8 = 0;
        if (j2 != 0) {
            if (searchAggregate != null) {
                playlistArr = searchAggregate.getPlaylists();
                z = searchAggregate.isEmpty();
                mediaWrapperArr2 = searchAggregate.getTracks();
                mediaWrapperArr = searchAggregate.getVideos();
                artistArr = searchAggregate.getArtists();
                genreArr = searchAggregate.getGenres();
                albumArr = searchAggregate.getAlbums();
            } else {
                albumArr = null;
                playlistArr = null;
                z = false;
                mediaWrapperArr2 = null;
                mediaWrapperArr = null;
                artistArr = null;
                genreArr = null;
            }
            if (j2 != 0) {
                j |= z ? 262400 : 131200;
            }
            boolean isArrayEmpty = Tools.isArrayEmpty(playlistArr);
            i3 = z ? 8 : 0;
            i2 = z ? 0 : 8;
            boolean isArrayEmpty2 = Tools.isArrayEmpty(mediaWrapperArr2);
            boolean isArrayEmpty3 = Tools.isArrayEmpty(mediaWrapperArr);
            boolean isArrayEmpty4 = Tools.isArrayEmpty(artistArr);
            boolean isArrayEmpty5 = Tools.isArrayEmpty(genreArr);
            boolean isArrayEmpty6 = Tools.isArrayEmpty(albumArr);
            if ((j & 5) != 0) {
                j |= isArrayEmpty ? PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID : 512;
            }
            if ((j & 5) != 0) {
                j |= isArrayEmpty2 ? 16 : 8;
            }
            if ((j & 5) != 0) {
                j |= isArrayEmpty3 ? PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM : PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
            }
            if ((j & 5) != 0) {
                j |= isArrayEmpty4 ? 64 : 32;
            }
            if ((j & 5) != 0) {
                j |= isArrayEmpty5 ? 16384 : 8192;
            }
            if ((j & 5) != 0) {
                j |= isArrayEmpty6 ? 65536 : PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
            }
            int i9 = isArrayEmpty ? 8 : 0;
            i = isArrayEmpty2 ? 8 : 0;
            int i10 = isArrayEmpty3 ? 8 : 0;
            int i11 = isArrayEmpty4 ? 8 : 0;
            int i12 = isArrayEmpty5 ? 8 : 0;
            if (isArrayEmpty6) {
                i8 = 8;
            }
            i4 = i9;
            i6 = i8;
            i8 = i10;
            i5 = i11;
            i7 = i12;
        } else {
            i7 = 0;
            i6 = 0;
            i5 = 0;
            i4 = 0;
            i3 = 0;
            i2 = 0;
            i = 0;
        }
        long j3 = j & 6;
        if (j3 == 0 || clickHandler == null) {
            onClickListenerImpl = null;
        } else {
            OnClickListenerImpl onClickListenerImpl2 = this.mHandlerOnBackAndroidViewViewOnClickListener;
            if (onClickListenerImpl2 == null) {
                onClickListenerImpl2 = new OnClickListenerImpl();
                this.mHandlerOnBackAndroidViewViewOnClickListener = onClickListenerImpl2;
            }
            onClickListenerImpl = onClickListenerImpl2.setValue(clickHandler);
        }
        if ((j & 5) != 0) {
            this.albumsResults.setVisibility(i6);
            this.artistsResults.setVisibility(i5);
            this.genresResults.setVisibility(i7);
            this.mboundView11.setVisibility(i5);
            this.mboundView12.setVisibility(i5);
            this.mboundView14.setVisibility(i7);
            this.mboundView15.setVisibility(i7);
            this.mboundView17.setVisibility(i4);
            this.mboundView18.setVisibility(i4);
            this.mboundView2.setVisibility(i3);
            this.mboundView20.setVisibility(i2);
            this.mboundView3.setVisibility(i8);
            this.mboundView5.setVisibility(i);
            this.mboundView6.setVisibility(i);
            this.mboundView8.setVisibility(i6);
            this.mboundView9.setVisibility(i6);
            this.othersResults.setVisibility(i8);
            this.playlistsResults.setVisibility(i4);
            this.songsResults.setVisibility(i);
        }
        if (j3 != 0) {
            this.mboundView1.setOnClickListener(onClickListenerImpl);
        }
    }

    public static class OnClickListenerImpl implements View.OnClickListener {
        private SearchActivity.ClickHandler value;

        public OnClickListenerImpl setValue(SearchActivity.ClickHandler clickHandler) {
            this.value = clickHandler;
            if (clickHandler == null) {
                return null;
            }
            return this;
        }

        public void onClick(View view) {
            this.value.onBack(view);
        }
    }
}
