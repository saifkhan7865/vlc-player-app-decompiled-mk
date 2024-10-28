package org.videolan.vlc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.dialogs.adapters.VlcTrack;

public abstract class VideoTrackItemBinding extends ViewDataBinding {
    public final ImageView imageView11;
    @Bindable
    protected String mContentDescription;
    @Bindable
    protected Boolean mSelected;
    @Bindable
    protected VlcTrack mTrack;
    public final ConstraintLayout trackContainer;
    public final TextView trackTitle;

    public abstract void setContentDescription(String str);

    public abstract void setSelected(Boolean bool);

    public abstract void setTrack(VlcTrack vlcTrack);

    protected VideoTrackItemBinding(Object obj, View view, int i, ImageView imageView, ConstraintLayout constraintLayout, TextView textView) {
        super(obj, view, i);
        this.imageView11 = imageView;
        this.trackContainer = constraintLayout;
        this.trackTitle = textView;
    }

    public VlcTrack getTrack() {
        return this.mTrack;
    }

    public Boolean getSelected() {
        return this.mSelected;
    }

    public String getContentDescription() {
        return this.mContentDescription;
    }

    public static VideoTrackItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static VideoTrackItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (VideoTrackItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.video_track_item, viewGroup, z, obj);
    }

    public static VideoTrackItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static VideoTrackItemBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (VideoTrackItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.video_track_item, (ViewGroup) null, false, obj);
    }

    public static VideoTrackItemBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static VideoTrackItemBinding bind(View view, Object obj) {
        return (VideoTrackItemBinding) bind(obj, view, R.layout.video_track_item);
    }
}
