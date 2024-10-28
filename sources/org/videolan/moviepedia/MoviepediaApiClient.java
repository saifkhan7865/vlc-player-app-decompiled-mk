package org.videolan.moviepedia;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\bf\u0018\u0000 \u00022\u00020\u0001:\u0001\u0002¨\u0006\u0003"}, d2 = {"Lorg/videolan/moviepedia/MoviepediaApiClient;", "", "Companion", "moviepedia_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MoviepediaApiService.kt */
public interface MoviepediaApiClient {
    public static final Companion Companion = Companion.$$INSTANCE;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lorg/videolan/moviepedia/MoviepediaApiClient$Companion;", "", "()V", "instance", "Lorg/videolan/moviepedia/IMoviepediaApiService;", "getInstance", "()Lorg/videolan/moviepedia/IMoviepediaApiService;", "moviepedia_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: MoviepediaApiService.kt */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static final IMoviepediaApiService instance = MoviepediaApiServiceKt.buildClient();

        private Companion() {
        }

        public final IMoviepediaApiService getInstance() {
            return instance;
        }
    }
}
