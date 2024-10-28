package org.videolan.moviepedia;

import kotlin.Metadata;
import org.videolan.resources.AppContextProvider;
import org.videolan.resources.interfaces.IndexingListener;

@Metadata(d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, d2 = {"org/videolan/moviepedia/MediaScraper$indexListener$1", "Lorg/videolan/resources/interfaces/IndexingListener;", "onIndexingDone", "", "moviepedia_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaScraper.kt */
public final class MediaScraper$indexListener$1 implements IndexingListener {
    MediaScraper$indexListener$1() {
    }

    public void onIndexingDone() {
        MediaScraper.INSTANCE.indexMedialib(AppContextProvider.INSTANCE.getAppContext());
    }
}
