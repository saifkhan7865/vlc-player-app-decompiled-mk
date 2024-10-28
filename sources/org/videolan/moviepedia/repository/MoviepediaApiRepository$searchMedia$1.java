package org.videolan.moviepedia.repository;

import android.net.Uri;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.moviepedia.repository.MoviepediaApiRepository", f = "MoviepediaApiRepository.kt", i = {0, 0}, l = {42, 44}, m = "searchMedia", n = {"this", "uri"}, s = {"L$0", "L$1"})
/* compiled from: MoviepediaApiRepository.kt */
final class MoviepediaApiRepository$searchMedia$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ MoviepediaApiRepository this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MoviepediaApiRepository$searchMedia$1(MoviepediaApiRepository moviepediaApiRepository, Continuation<? super MoviepediaApiRepository$searchMedia$1> continuation) {
        super(continuation);
        this.this$0 = moviepediaApiRepository;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.searchMedia((Uri) null, this);
    }
}
