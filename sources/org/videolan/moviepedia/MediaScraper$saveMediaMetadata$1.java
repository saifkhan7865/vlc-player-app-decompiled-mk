package org.videolan.moviepedia;

import android.content.Context;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.moviepedia.models.resolver.ResolverMedia;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.moviepedia.MediaScraper", f = "MediaScraper.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2}, l = {99, 100, 138}, m = "saveMediaMetadata", n = {"this", "context", "media", "item", "type", "mediaMetadataRepository", "retrieveCast", "removePersonOrphans", "this", "context", "media", "item", "type", "mediaMetadataRepository", "retrieveCast", "removePersonOrphans", "this", "context", "removePersonOrphans"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "Z$0", "Z$1", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "Z$0", "Z$1", "L$0", "L$1", "Z$0"})
/* compiled from: MediaScraper.kt */
final class MediaScraper$saveMediaMetadata$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    boolean Z$0;
    boolean Z$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ MediaScraper this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MediaScraper$saveMediaMetadata$1(MediaScraper mediaScraper, Continuation<? super MediaScraper$saveMediaMetadata$1> continuation) {
        super(continuation);
        this.this$0 = mediaScraper;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.saveMediaMetadata((Context) null, (MediaWrapper) null, (ResolverMedia) null, false, false, this);
    }
}
