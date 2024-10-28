package org.videolan.moviepedia;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.videolan.moviepedia.repository.MoviepediaApiRepository;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lorg/videolan/moviepedia/repository/MoviepediaApiRepository;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaScraper.kt */
final class MediaScraper$mediaResolverApi$2 extends Lambda implements Function0<MoviepediaApiRepository> {
    public static final MediaScraper$mediaResolverApi$2 INSTANCE = new MediaScraper$mediaResolverApi$2();

    MediaScraper$mediaResolverApi$2() {
        super(0);
    }

    public final MoviepediaApiRepository invoke() {
        return MoviepediaApiRepository.Companion.getInstance();
    }
}
