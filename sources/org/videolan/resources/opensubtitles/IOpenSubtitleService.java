package org.videolan.resources.opensubtitles;

import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import retrofit2.http.GET;
import retrofit2.http.Path;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001Jd\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0003\u0010\u0005\u001a\u00020\u00062\b\b\u0003\u0010\u0007\u001a\u00020\u00062\b\b\u0003\u0010\b\u001a\u00020\u00062\b\b\u0003\u0010\t\u001a\u00020\u00062\b\b\u0003\u0010\n\u001a\u00020\u00062\b\b\u0003\u0010\u000b\u001a\u00020\f2\b\b\u0003\u0010\r\u001a\u00020\f2\b\b\u0003\u0010\u000e\u001a\u00020\u0006H§@¢\u0006\u0002\u0010\u000f¨\u0006\u0010"}, d2 = {"Lorg/videolan/resources/opensubtitles/IOpenSubtitleService;", "", "query", "", "Lorg/videolan/resources/opensubtitles/OpenSubtitle;", "movieByteSize", "", "movieHash", "name", "imdbId", "tag", "episode", "", "season", "languageId", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IILjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "resources_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: IOpenSubtitleService.kt */
public interface IOpenSubtitleService {
    @GET("episode-{episode}/imdbid-{imdbId}/moviebytesize-{movieByteSize}/moviehash-{movieHash}/query-{name}/season-{season}/sublanguageid-{subLanguageId}/tag_{tag}")
    Object query(@Path("movieByteSize") String str, @Path("movieHash") String str2, @Path("name") String str3, @Path("imdbId") String str4, @Path("tag") String str5, @Path("episode") int i, @Path("season") int i2, @Path("subLanguageId") String str6, Continuation<? super List<OpenSubtitle>> continuation);

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* compiled from: IOpenSubtitleService.kt */
    public static final class DefaultImpls {
        public static /* synthetic */ Object query$default(IOpenSubtitleService iOpenSubtitleService, String str, String str2, String str3, String str4, String str5, int i, int i2, String str6, Continuation continuation, int i3, Object obj) {
            int i4 = i3;
            if (obj == null) {
                return iOpenSubtitleService.query((i4 & 1) != 0 ? "" : str, (i4 & 2) != 0 ? "" : str2, (i4 & 4) != 0 ? "" : str3, (i4 & 8) != 0 ? "" : str4, (i4 & 16) != 0 ? "" : str5, (i4 & 32) != 0 ? 0 : i, (i4 & 64) != 0 ? 0 : i2, (i4 & 128) != 0 ? "" : str6, continuation);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: query");
        }
    }
}
