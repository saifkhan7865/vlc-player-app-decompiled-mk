package org.videolan.television.ui;

import androidx.lifecycle.Observer;
import org.videolan.moviepedia.viewmodel.MediaMetadataFull;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaItemDetailsFragment$$ExternalSyntheticLambda0 implements Observer {
    public final /* synthetic */ MediaItemDetailsFragment f$0;

    public /* synthetic */ MediaItemDetailsFragment$$ExternalSyntheticLambda0(MediaItemDetailsFragment mediaItemDetailsFragment) {
        this.f$0 = mediaItemDetailsFragment;
    }

    public final void onChanged(Object obj) {
        MediaItemDetailsFragment.onCreate$lambda$0(this.f$0, (MediaMetadataFull) obj);
    }
}
