package org.videolan.television.ui.details;

import android.view.View;
import org.videolan.television.databinding.ActivityMediaListTvItemBinding;
import org.videolan.television.ui.details.MediaListAdapter;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaListAdapter$MediaListViewHolder$$ExternalSyntheticLambda0 implements View.OnFocusChangeListener {
    public final /* synthetic */ View[] f$0;
    public final /* synthetic */ ActivityMediaListTvItemBinding f$1;
    public final /* synthetic */ MediaListAdapter f$2;
    public final /* synthetic */ MediaListAdapter.MediaListViewHolder f$3;

    public /* synthetic */ MediaListAdapter$MediaListViewHolder$$ExternalSyntheticLambda0(View[] viewArr, ActivityMediaListTvItemBinding activityMediaListTvItemBinding, MediaListAdapter mediaListAdapter, MediaListAdapter.MediaListViewHolder mediaListViewHolder) {
        this.f$0 = viewArr;
        this.f$1 = activityMediaListTvItemBinding;
        this.f$2 = mediaListAdapter;
        this.f$3 = mediaListViewHolder;
    }

    public final void onFocusChange(View view, boolean z) {
        MediaListAdapter.MediaListViewHolder._init_$lambda$1(this.f$0, this.f$1, this.f$2, this.f$3, view, z);
    }
}
