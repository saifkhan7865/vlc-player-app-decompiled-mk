package org.videolan.moviepedia.provider;

import android.content.Context;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.moviepedia.provider.TvShowResolver", f = "MediaScrapingTvshowProvider.kt", i = {0}, l = {152}, m = "getList", n = {"moviepediaId"}, s = {"L$0"})
/* compiled from: MediaScrapingTvshowProvider.kt */
final class TvShowResolver$getList$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ TvShowResolver this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    TvShowResolver$getList$1(TvShowResolver tvShowResolver, Continuation<? super TvShowResolver$getList$1> continuation) {
        super(continuation);
        this.this$0 = tvShowResolver;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.getList((Context) null, (String) null, this);
    }
}
