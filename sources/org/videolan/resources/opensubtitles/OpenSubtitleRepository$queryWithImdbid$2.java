package org.videolan.resources.opensubtitles;

import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.resources.opensubtitles.OpenSubtitleRepository", f = "OpenSubtitleRepository.kt", i = {0, 0, 0, 0, 0, 0}, l = {50}, m = "queryWithImdbid", n = {"this", "actualTag", "destination$iv$iv", "imdbId", "actualEpisode", "actualSeason"}, s = {"L$0", "L$1", "L$2", "I$0", "I$1", "I$2"})
/* compiled from: OpenSubtitleRepository.kt */
final class OpenSubtitleRepository$queryWithImdbid$2 extends ContinuationImpl {
    int I$0;
    int I$1;
    int I$2;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ OpenSubtitleRepository this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    OpenSubtitleRepository$queryWithImdbid$2(OpenSubtitleRepository openSubtitleRepository, Continuation<? super OpenSubtitleRepository$queryWithImdbid$2> continuation) {
        super(continuation);
        this.this$0 = openSubtitleRepository;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.queryWithImdbid(0, (String) null, (Integer) null, (Integer) null, (List<String>) null, (Continuation<? super List<OpenSubtitle>>) this);
    }
}
