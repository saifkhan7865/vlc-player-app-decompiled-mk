package org.videolan.television.ui;

import androidx.lifecycle.Observer;
import org.videolan.moviepedia.database.models.MediaMetadataWithImages;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaItemDetailsFragment$$ExternalSyntheticLambda2 implements Observer {
    public final /* synthetic */ MediaItemDetailsFragment f$0;

    public /* synthetic */ MediaItemDetailsFragment$$ExternalSyntheticLambda2(MediaItemDetailsFragment mediaItemDetailsFragment) {
        this.f$0 = mediaItemDetailsFragment;
    }

    public final void onChanged(Object obj) {
        MediaItemDetailsFragment.onCreate$lambda$2(this.f$0, (MediaMetadataWithImages) obj);
    }
}
