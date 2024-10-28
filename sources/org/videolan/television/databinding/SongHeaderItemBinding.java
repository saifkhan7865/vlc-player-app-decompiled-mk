package org.videolan.television.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import org.videolan.television.R;
import org.videolan.television.ui.MediaHeaderAdapter;

public abstract class SongHeaderItemBinding extends ViewDataBinding {
    public final TextView header;
    @Bindable
    protected Boolean mHasContent;
    @Bindable
    protected String mHeaderText;
    @Bindable
    protected MediaHeaderAdapter.ViewHolder mHolder;

    public abstract void setHasContent(Boolean bool);

    public abstract void setHeaderText(String str);

    public abstract void setHolder(MediaHeaderAdapter.ViewHolder viewHolder);

    protected SongHeaderItemBinding(Object obj, View view, int i, TextView textView) {
        super(obj, view, i);
        this.header = textView;
    }

    public String getHeaderText() {
        return this.mHeaderText;
    }

    public Boolean getHasContent() {
        return this.mHasContent;
    }

    public MediaHeaderAdapter.ViewHolder getHolder() {
        return this.mHolder;
    }

    public static SongHeaderItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SongHeaderItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (SongHeaderItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.song_header_item, viewGroup, z, obj);
    }

    public static SongHeaderItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SongHeaderItemBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (SongHeaderItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.song_header_item, (ViewGroup) null, false, obj);
    }

    public static SongHeaderItemBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SongHeaderItemBinding bind(View view, Object obj) {
        return (SongHeaderItemBinding) bind(obj, view, R.layout.song_header_item);
    }
}
