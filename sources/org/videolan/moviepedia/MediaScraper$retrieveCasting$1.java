package org.videolan.moviepedia;

import android.content.Context;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.videolan.moviepedia.database.models.MediaMetadata;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.moviepedia.MediaScraper", f = "MediaScraper.kt", i = {0, 0, 0, 0}, l = {148}, m = "retrieveCasting", n = {"context", "mediaMetadata", "personRepo", "personsToAdd"}, s = {"L$0", "L$1", "L$2", "L$3"})
/* compiled from: MediaScraper.kt */
final class MediaScraper$retrieveCasting$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ MediaScraper this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MediaScraper$retrieveCasting$1(MediaScraper mediaScraper, Continuation<? super MediaScraper$retrieveCasting$1> continuation) {
        super(continuation);
        this.this$0 = mediaScraper;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.retrieveCasting((Context) null, (MediaMetadata) null, this);
    }
}
