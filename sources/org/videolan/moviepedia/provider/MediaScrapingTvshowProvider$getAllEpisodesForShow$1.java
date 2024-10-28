package org.videolan.moviepedia.provider;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.moviepedia.provider.MediaScrapingTvshowProvider", f = "MediaScrapingTvshowProvider.kt", i = {}, l = {126}, m = "getAllEpisodesForShow", n = {}, s = {})
/* compiled from: MediaScrapingTvshowProvider.kt */
final class MediaScrapingTvshowProvider$getAllEpisodesForShow$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ MediaScrapingTvshowProvider this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MediaScrapingTvshowProvider$getAllEpisodesForShow$1(MediaScrapingTvshowProvider mediaScrapingTvshowProvider, Continuation<? super MediaScrapingTvshowProvider$getAllEpisodesForShow$1> continuation) {
        super(continuation);
        this.this$0 = mediaScrapingTvshowProvider;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.getAllEpisodesForShow((String) null, this);
    }
}
